/**
 * Copyright (c) 2018 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.releng;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.internal.p2.metadata.BasicVersion;
import org.eclipse.equinox.internal.p2.metadata.IRequiredCapability;
import org.eclipse.equinox.p2.core.ProvisionException;
import org.eclipse.equinox.p2.internal.repository.tools.AbstractApplication;
import org.eclipse.equinox.p2.internal.repository.tools.CompositeRepositoryApplication;
import org.eclipse.equinox.p2.internal.repository.tools.MirrorApplication;
import org.eclipse.equinox.p2.internal.repository.tools.RepositoryDescriptor;
import org.eclipse.equinox.p2.internal.repository.tools.SlicingOptions;
import org.eclipse.equinox.p2.metadata.IArtifactKey;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.IProvidedCapability;
import org.eclipse.equinox.p2.metadata.IRequirement;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.equinox.p2.query.IQueryResult;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.equinox.p2.repository.ICompositeRepository;
import org.eclipse.equinox.p2.repository.IRepository;
import org.eclipse.equinox.p2.repository.artifact.IArtifactDescriptor;
import org.eclipse.equinox.p2.repository.artifact.IArtifactRepository;
import org.eclipse.equinox.p2.repository.artifact.spi.AbstractArtifactRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepository;
import org.eclipse.equinox.p2.repository.metadata.IMetadataRepositoryManager;
import org.eclipse.equinox.p2.repository.metadata.spi.AbstractMetadataRepository;
import org.osgi.framework.Bundle;


/**
 * A utility class that uses p2's repository tools to manage the update sites for EMF's builds.
 *
 */
@SuppressWarnings("restriction")
public class UpdateSiteGenerator
{
  /**
   * The root-relative location of the builds folder.
   */
  private static final String RELATIVE_BUILDS_FOLDER = "modeling/emf/emf/builds";

  /**
   * The files and folders that comprise a simple update site.
   */
  private static final List<String> UPDATE_SITE_CONTENT = Arrays.asList(
    new String []{ ".blobstore", "binary", "features", "plugins", "artifacts.jar", "artifacts.xml.xz", "content.jar", "content.xml.xz", "p2.index" });

  /**
   * The number of nightly builds to maintain in the nightly composite.
   */
  public static final int RETAINED_NIGHTLY_BUILDS = 7;

  /**
   * The valid values for {@code publish.build.type}.
   */
  public static final List<String> BUILD_TYPES = Arrays.asList(new String []{ "nightly", "milestone", "release" });

  /**
   * The prefix qualifier associated with the {@link #BUILD_TYPES}.
   */
  public static final List<String> BUILD_TYPE_QUALIFIERS = Arrays.asList(new String []{ "N", "S", "" });

  /**
   * The value of {@code publish.download.root.folder} with normalized path segment separators.
   */
  private static final String DOWLOAD_ROOT_FOLDER_PROPERTY = System.getProperty("publish.download.root.folder").replace('\\', '/');

  /**
   * The expected value of the {@link #DOWLOAD_ROOT_FOLDER} on the {@code build.eclipse.org} host.
   */
  public static final String DOWNLOAD_ECLIPSE_ORG_FOLDER = "/home/data/httpd/download.eclipse.org/";

  /**
   * The root folder in which the update sites for EMF's builds are managed.
   */
  public static final String DOWLOAD_ROOT_FOLDER = DOWLOAD_ROOT_FOLDER_PROPERTY == null || DOWLOAD_ROOT_FOLDER_PROPERTY.trim().length() == 0
    ? DOWNLOAD_ECLIPSE_ORG_FOLDER : (!DOWLOAD_ROOT_FOLDER_PROPERTY.endsWith("/") ? DOWLOAD_ROOT_FOLDER_PROPERTY + "/" : DOWLOAD_ROOT_FOLDER_PROPERTY);

  /**
   * The location of the builds folder with in the root folder.
   */
  public static final String BUILDS_ROOT_FOLDER = DOWLOAD_ROOT_FOLDER + RELATIVE_BUILDS_FOLDER;

  /**
   * Returns the destination location at which to promote the give type of build with the given name.
   *
   * @param buildType the {@code publish.build.type}.
   * @param name the name of the folder, i.e., the {@code publish.build.timestamp} or the {@link #getVersion(String) version}.
   * @return the destination location at which to promote the give type of build with the given name.
   *
   * @throws Exception
   */
  public String getPromoteUpdateSiteDestination(String buildType, String name) throws Exception
  {
    File destinationBuildsTypeFolder = new File(BUILDS_ROOT_FOLDER, buildType);
    return new File(destinationBuildsTypeFolder, getTargetFolder(buildType, name)).getCanonicalPath().replace('\\', '/');
  }

  /**
   * Mirrors the source repository location to the destination location.
   * This is used for nightly and milestone promotion.
   *
   * @param source the source repository.
   * @param buildType the {@code publish.build.type}.
   * @param buildTimestamp the {@code publish.build.timestamp}.
   * @return the destination location at the repository was promoted.
   * @throws Exception
   */
  public File promoteUpdateSite(final String source, final String buildType, String buildTimestamp) throws Exception
  {
    final String destination = getPromoteUpdateSiteDestination(buildType, buildTimestamp);
    return mirrorUpdateSite(source, destination, buildType);
  }

  /**
   * Mirrors the source repository location to the destination location.
   * The folder name is determined by the {@link #getVersion(String) version} of the source.
   * This is used for release promotion.
   *
   * @param source the source repository.
   * @param buildType the {@code publish.build.type}.
   * @return the destination location at the repository was promoted.
   * @throws Exception
   */
  public File mirrorUpdateSite(final String source, final String buildType) throws Exception
  {
    String version = getVersion(source);
    final String destination = getPromoteUpdateSiteDestination(buildType, version);
    return mirrorUpdateSite(source, destination, buildType);
  }

  /**
   * Mirrors the source repository to the destination repository.
   * The name of the repository is computed from the content metadata of the source and from the build type,
   * the <a href="https://wiki.eclipse.org/Equinox/p2/p2.mirrorsURL">p2.mirrorsURL</a> property configured,
   * and {@code .xml.xz} formats are produced along with a {@code p2.index}.
   *
   * @param source the source repository location.
   * @param destination the destination repository location.
   * @param buildType the {@code publish.build.type}.
   * @return the destination location at the repository was mirrored.
   * @throws Exception
   */
  private File mirrorUpdateSite(final String source, final String destination, final String buildType) throws Exception
  {
    MirrorApplication mirrorApplication = new MirrorApplication()
      {
        @Override
        protected void finalizeRepositories()
        {
          if (destinationMetadataRepository instanceof AbstractMetadataRepository)
          {
            String repositoryName = getRepositoryName(destinationMetadataRepository) + ' ' + getLabel(buildType);
            destinationMetadataRepository.setProperty(IRepository.PROP_NAME, repositoryName);

            if (destinationArtifactRepository instanceof AbstractArtifactRepository)
            {
              destinationArtifactRepository.setProperty(IRepository.PROP_NAME, repositoryName);
              if (destination.startsWith(DOWLOAD_ROOT_FOLDER))
              {
                String mirrorsURL = "http://www.eclipse.org/downloads/download.php?file=/" + destination.substring(DOWLOAD_ROOT_FOLDER.length());
                destinationArtifactRepository.setProperty("p2.mirrorsURL", mirrorsURL);
              }
            }
          }

          super.finalizeRepositories();
        }
      };

    File artifacts = new File(destination, "artifacts.xml.xz");
    if (artifacts.isFile())
    {
      artifacts.delete();
    }

    File content = new File(destination, "content.xml.xz");
    if (content.isFile())
    {
      content.delete();
    }

    File p2Index = new File(destination, "p2.index");
    if (p2Index.isFile())
    {
      p2Index.delete();
    }

    mirrorApplication.initializeFromArguments(new String []{ "-source", source, "-destination", destination, "-writeMode", "clean" });

    SlicingOptions slicingOptions = new SlicingOptions();
    slicingOptions.latestVersionOnly(true);
    mirrorApplication.setSlicingOptions(slicingOptions);

    IStatus status = mirrorApplication.run(new NullProgressMonitor());
    if (!status.isOK())
    {
      throw new CoreException(status);
    }

    xzCompress(destination);

    return new File(destination);
  }

  /**
   * Returns the version of the {@code org.eclipse.emf.sdk.feature.group} installable unit in the target repository.
   * @param targetRepository the repository location.
   * @return the associated semantic version of the repository.
   * @throws Exception
   */
  public String getVersion(String targetRepository) throws Exception
  {
    RepositoryAnalyzer repositoryAnalyzer = new RepositoryAnalyzer();
    RepositoryDescriptor repositoryDescriptor = new RepositoryDescriptor();
    repositoryDescriptor.setLocation(createURI(targetRepository));
    repositoryAnalyzer.addSource(repositoryDescriptor);
    String version = repositoryAnalyzer.getVersion();
    return version;
  }

  /**
   * Returns the first child of the target repository.
   * @param targetRepository the repository location.
   * @return the first child of the target repository.
   * @throws Exception
   */
  public String getFirstChild(String targetRepository) throws Exception
  {
    RepositoryAnalyzer repositoryAnalyzer = new RepositoryAnalyzer();
    RepositoryDescriptor repositoryDescriptor = new RepositoryDescriptor();
    repositoryDescriptor.setLocation(createURI(targetRepository));
    repositoryAnalyzer.addSource(repositoryDescriptor);
    return repositoryAnalyzer.getChildren().get(0);
  }

  /**
   * Creates and returns the repository analyzer for the give repositories.
   * @param repositories a list of repository location.
   *
   * @return the repository analyzer.
   */
  public RepositoryAnalyzer getRepositoryAnalyzer(List<String> repositories)
  {
    RepositoryAnalyzer repositoryAnalyzer = new RepositoryAnalyzer();
    RepositoryDescriptor repositoryDescriptor = new RepositoryDescriptor();
    for (String repository : repositories)
    {
      repositoryDescriptor.setLocation(createURI(repository));
      repositoryAnalyzer.addSource(repositoryDescriptor);
    }

    return repositoryAnalyzer;
  }

  /**
   * Returns the title case label for the give build type.
   * @param buildType the {@code publish.build.type}.
   * o
   * @return the title case label for the give build type.
   */
  private String getLabel(String buildType)
  {
    return Character.toUpperCase(buildType.charAt(0)) + buildType.substring(1);
  }

  /**
   * Returns the computed name for the repository.
   * This will be 'EMF' followed the range of versions of the {@code org.eclipse.emf.sdk.feature.group} installable units in the repository.
   * If there is only one version then it will be followed by only that one version.
   * If there are none, then it's just 'EMF'.
   * @param repository
   * @return the computed name for the repository.
   */
  private String getRepositoryName(IMetadataRepository repository)
  {
    IQueryResult<IInstallableUnit> groups = repository.query(QueryUtil.createIUQuery("org.eclipse.emf.sdk.feature.group"), new NullProgressMonitor());
    List<BasicVersion> versions = new ArrayList<BasicVersion>();
    for (Iterator<IInstallableUnit> i = groups.iterator(); i.hasNext();)
    {
      IInstallableUnit group = i.next();
      Version iuVersion = group.getVersion();
      if (iuVersion.isOSGiCompatible() && iuVersion instanceof BasicVersion)
      {
        BasicVersion basicVersion = (BasicVersion)iuVersion;
        versions.add(basicVersion);
      }
    }

    if (versions.isEmpty())
    {
      return "EMF";
    }
    else if (versions.size() == 1)
    {
      BasicVersion version = versions.get(0);
      return "EMF " + version.getMajor() + "." + version.getMinor();
    }
    else
    {
      Collections.sort(versions);
      BasicVersion minVersion = versions.get(0);
      BasicVersion maxVersion = versions.get(versions.size() - 1);
      return "EMF " + minVersion.getMajor() + "." + minVersion.getMinor() + "-" + maxVersion.getMajor() + "." + maxVersion.getMinor();
    }
  }

  /**
   * Returns the destination folder for the given build type and whether it is a latest composite or not.
   *
   * @param buildType the {@code publish.build.type}.
   * @param latest whether this is a composite for the latest build.
   * @return the destination folder.
   *
   * @throws Exception
   */
  public String getCompositeUpdateSiteDestination(String buildType, boolean latest) throws Exception
  {
    File destinationBuildsTypeFolder = new File(BUILDS_ROOT_FOLDER, buildType);
    if (latest)
    {
      destinationBuildsTypeFolder = new File(destinationBuildsTypeFolder, "latest");
    }
    return getCanonicalPath(destinationBuildsTypeFolder);
  }

  /**
   * Creates a composite update that references the given source repositories, at a location determined by the build type and the latest indicator.
   * @param sources the source repositories.
   * @param buildType the {@code publish.build.type}.
   * @param latest whether is is a composite refering to the latest.
   *
   * @throws Exception
   */
  public String composeUpdateSites(List<String> sources, final String buildType, final boolean latest) throws Exception
  {
    String destination = getCompositeUpdateSiteDestination(buildType, latest);
    final URI destinationURI = createURI(destination);

    // We must set the user dir to ensure that we produce a composite that uses relative URIs!
    //
    System.setProperty("user.dir", new File(destination).getPath());

    CompositeRepositoryApplication compositeRepositoryApplication = new CompositeRepositoryApplication()
      {
        @Override
        protected void finalizeRepositories()
        {
          // Compute an appropriate name for the repository after it has been populated.
          if (destinationMetadataRepository instanceof ICompositeRepository<?>)
          {
            String repositoryName = getRepositoryName(destinationMetadataRepository) + ' ' + getLabel(buildType) + (latest ? " Latest" : " Composite");
            destinationMetadataRepository.setProperty(IRepository.PROP_NAME, repositoryName);
            save((ICompositeRepository<?>)destinationMetadataRepository);

            if (destinationArtifactRepository instanceof ICompositeRepository<?>)
            {
              destinationArtifactRepository.setProperty(IRepository.PROP_NAME, repositoryName);
              save((ICompositeRepository<?>)destinationArtifactRepository);
            }
          }

          super.finalizeRepositories();
        }

        private void save(ICompositeRepository<?> repository)
        {
          // Unfortunately p2 provides no API for saving the repository after changing the name.
          // The repository is saved only when children are added or removed.
          // So this a good opportunity to double check that all the children are usin a relative URI.
          List<URI> children = repository.getChildren();
          repository.removeAllChildren();
          for (URI child : children)
          {
            URI relativeSourceURI = relativize(child, destinationURI);
            if (relativeSourceURI == child)
            {
              throw new IllegalArgumentException("The URI '" + child + "' cannot be made relative to '" + destinationURI + "'");
            }
            repository.addChild(relativeSourceURI);
          }
        }
      };
    compositeRepositoryApplication.setRemoveAll(true);

    RepositoryDescriptor destinationRepositoryDescriptor = new RepositoryDescriptor();
    destinationRepositoryDescriptor.setLocation(destinationURI);
    compositeRepositoryApplication.addDestination(destinationRepositoryDescriptor);

    for (String source : sources)
    {
      RepositoryDescriptor childRepositoryDescriptor = new RepositoryDescriptor();
      URI sourceURI = createURI(source);
      URI relativeSourceURI = relativize(sourceURI, destinationURI);
      if (relativeSourceURI == sourceURI)
      {
        // We must use relative URIs!
        throw new IllegalArgumentException("The URI '" + sourceURI + "' cannot be made relative to '" + destinationURI + "'");
      }

      childRepositoryDescriptor.setLocation(relativeSourceURI);
      compositeRepositoryApplication.addChild(childRepositoryDescriptor);
    }

    IStatus status = compositeRepositoryApplication.run(new NullProgressMonitor());
    if (!status.isOK())
    {
      throw new CoreException(status);
    }

    return destination;
  }

  /**
   * Returns the relative URI that the target can use to reference the source.
   * It would be better to use EMF's URI.
   *
   * @param sourceURI the source URI.
   * @param targetURI the target URI.
   * @return the relative URI.
   */
  private static URI relativize(URI sourceURI, URI targetURI)
  {
    URI relativeSourceURI = targetURI.relativize(sourceURI);
    if (relativeSourceURI == sourceURI)
    {
      URI parentTargetURI = targetURI.resolve("..");
      if (parentTargetURI != targetURI)
      {
        URI parentRelativeSourceURI = relativize(relativeSourceURI, parentTargetURI);
        if (parentRelativeSourceURI != relativeSourceURI)
        {
          String string = parentRelativeSourceURI.toString();
          int index = string.indexOf('/');
          relativeSourceURI = URI.create(".." + string.substring(index));
        }
      }
    }
    return relativeSourceURI;
  }

  /**
   * Produces the {@code .xml.zx} forms as well as the {@code p2.index}.
   *
   * @param targetRepository
   */
  private void xzCompress(String targetRepository)
  {
    try
    {
      // We do this reflectively so that we can compile against Helios.
      //
      Bundle bundle = Platform.getBundle("org.eclipse.equinox.p2.repository.tools");
      Class<?> xzCompressorClass = bundle.loadClass("org.eclipse.equinox.p2.internal.repository.tools.XZCompressor");
      Object xzCompressor = xzCompressorClass.getDeclaredConstructor().newInstance();
      xzCompressorClass.getMethod("setRepoFolder", String.class).invoke(xzCompressor, targetRepository);
      xzCompressorClass.getMethod("compressRepo").invoke(xzCompressor);
    }
    catch (Exception exception)
    {
      // Ignore.
    }
  }

  /**
   * Returns the qualified target folder depending on the build type and the give target subfolder.
   *
   * @param buildType the {@code publis.build.type}.
   * @param targetSubfolder the target subfolder.
   * @return the qualified target folder depending on the build type and the give target subfolder.
   */
  private String getTargetFolder(String buildType, String targetSubfolder)
  {
    return BUILD_TYPE_QUALIFIERS.get(BUILD_TYPES.indexOf(buildType)) + targetSubfolder;
  }

  public static URI createURI(String path)
  {
    return createURI(new File(path));
  }

  public static URI createURI(File file)
  {
    try
    {
      // Java has a bad habit of adding a trailing "/" if the file exists as a directory.
      // We never want that because it gives different results depending on existence.
      URI uri = file.getCanonicalFile().toURI();
      String literal = uri.toString();
      return literal.endsWith("/") ? new URI(literal.substring(0, literal.length() - 1)) : uri;
    }
    catch (Exception exception)
    {
      throw new IllegalArgumentException(exception);
    }
  }

  /**
   * Generates the index.html for the target repository.
   * @param target the target repository.
   *
   * @throws Exception
   */
  public void generateIndex(String target) throws Exception
  {
    String indexHTML = new UpdateSiteIndex().generate(new UpdateSiteIndexGenerator(target));
    FileOutputStream out = new FileOutputStream(new File(target, "index.html"));
    try
    {
      byte[] bytes = indexHTML.getBytes("UTF-8");
      out.write(bytes);
    }
    finally
    {
      out.close();
    }

    File targetFile = new File(target);
    if (targetFile.isDirectory())
    {
      for (File child : targetFile.listFiles())
      {
        if (child.isDirectory() && (new File(child, "compositeContent.jar").isFile() || new File(child, "content.jar").isFile()))
        {
          generateIndex(child.getPath());
        }
      }
    }
  }

  /**
   * Generates a zip and SHA hashes for all simple repositories nested in the target folder.
   *
   * @param target the target folder.
   * @throws Exception
   */
  public void generateDownloads(String target) throws Exception
  {
    File targetFile = new File(target);
    if (targetFile.isDirectory())
    {
      if (new File(targetFile, "content.jar").isFile())
      {
        File archiveFile = getArchiveFile(target);
        if (!archiveFile.exists())
        {
          createArchive(target);
        }

        if (!getDigestFile(archiveFile.getPath(), "SHA-256").isFile())
        {
          createDigest(archiveFile, "SHA-256");
        }

        if (!getDigestFile(archiveFile.getPath(), "SHA-512").isFile())
        {
          createDigest(archiveFile, "SHA-512");
        }
      }
      else
      {
        for (File child : targetFile.listFiles())
        {
          if (child.isDirectory())
          {
            generateDownloads(child.getPath());
          }
        }
      }
    }
  }

  /**
   * Sorts the list of repository folders to ensure that they are semantically order.
   *
   * @param repositories the repository folders.
   */
  public static void sort(List<String> repositories)
  {
    Map<Long, String> orderedRepositories = new TreeMap<Long, String>();
    for (String repository : repositories)
    {
      String name = new File(repository).getName();
      if ("release".equals(name))
      {
        orderedRepositories.put(0L, repository);
      }
      else if ("milestone".equals(name))
      {
        orderedRepositories.put(1L, repository);
      }
      else if ("nightly".equals(name))
      {
        orderedRepositories.put(2L, repository);
      }
      else if ("latest".equals(name))
      {
        orderedRepositories.put(Long.MIN_VALUE, repository);
      }
      else if (name.charAt(0) == 'N' || name.charAt(0) == 'S')
      {
        orderedRepositories.put(-Long.parseLong(name.substring(1)), repository);
      }
      else if (name.startsWith("2."))
      {
        orderedRepositories.put(-Long.parseLong(name.substring(2)), repository);
      }
      else
      {
        throw new IllegalArgumentException(repository);
      }
    }

    repositories.clear();
    repositories.addAll(orderedRepositories.values());
  }

  /**
   * Returns the {@link File#getCanonicalPath() canonical path} with normalized path separators, i.e., always '/'.
   * @param file the file.
   * @return the canonical path.
   *
   * @throws IOException
   */
  public static String getCanonicalPath(File file) throws IOException
  {
    return file.getCanonicalPath().replace('\\', '/');
  }

  /**
   * Returns the name of the zipped archive for the give simple repository.
   *
   * @param repository the simple repository.
   * @return
   */
  public static File getArchiveFile(String repository)
  {
    File file = new File(repository);
    String name = "EMF-Updates-" + file.getName() + ".zip";
    return new File(file, name);
  }

  /**
   * Creates a zip archive for the simple repository.
   *
   * @param repository the simple repository
   * @return the created archive.
   *
   * @throws IOException
   */
  public static File createArchive(String repository) throws IOException
  {
    File archiveFile = getArchiveFile(repository);
    File folder = archiveFile.getParentFile();
    if (!folder.isDirectory() || !new File(folder, "content.jar").isFile())
    {
      throw new IllegalStateException(repository + "is not a valid p2 repository");
    }

    boolean delete = false;
    FileOutputStream fileOutputStream = null;
    ZipOutputStream zipOutputStream = null;
    try
    {
      fileOutputStream = new FileOutputStream(archiveFile);
      zipOutputStream = new ZipOutputStream(fileOutputStream);
      for (File file : folder.listFiles())
      {
        String name = file.getName();
        if (UPDATE_SITE_CONTENT.contains(name))
        {
          visit(zipOutputStream, folder, name);
        }
      }
    }
    catch (IOException exception)
    {
      delete = true;
    }
    finally
    {
      if (zipOutputStream != null)
      {
        zipOutputStream.close();
      }
      if (fileOutputStream != null)
      {
        fileOutputStream.close();
      }
      if (delete)
      {
        archiveFile.delete();
      }
    }

    return archiveFile;
  }

  /**
   * Visits the folders recursively to zip all files.
   *
   * @param zipOutputStream the target archive.
   * @param root the root at which we started visiting.
   * @param path the relative path we are currently visiting.
   *
   * @throws IOException
   */
  private static void visit(ZipOutputStream zipOutputStream, File root, String path) throws IOException
  {
    File file = new File(root, path);
    if (file.isFile())
    {
      ZipEntry zipEntry = new ZipEntry(path);
      zipOutputStream.putNextEntry(zipEntry);
      FileInputStream fileInputStream = null;
      try
      {
        fileInputStream = new FileInputStream(file);
        byte[] bytes = new byte [10000];
        for (int length = fileInputStream.read(bytes); length != -1; length = fileInputStream.read(bytes))
        {
          zipOutputStream.write(bytes, 0, length);
        }
        zipOutputStream.closeEntry();
      }
      finally
      {
        if (fileInputStream != null)
        {
          fileInputStream.close();
        }
      }
    }
    else
    {
      for (String name : file.list())
      {
        visit(zipOutputStream, root, path + "/" + name);
      }
    }
  }

  /**
   * Returns the file location at which a digest for the given algorithm will be generated.
   * @param target the target file for the digest.
   * @param algorithm the algorithm used to compute the digest.
   * @return
   */
  public static File getDigestFile(String target, String algorithm)
  {
    File file = new File(target);
    File result = new File(file.getParentFile(), file.getName() + "." + algorithm.toLowerCase().replace("-", ""));
    return result;
  }

  /**
   * Creates a file containing the digest for the given file using the given algorithm.
   *
   * @param file the file to digest.
   * @param algorithm the algorithm to use for digesting.
   * @return the location of the digest file.
   *
   * @throws IOException
   */
  private static File createDigest(File file, String algorithm) throws IOException
  {
    File result = getDigestFile(file.getPath(), algorithm);
    try
    {
      MessageDigest instance = MessageDigest.getInstance(algorithm);
      FileInputStream in = null;
      FileOutputStream out = null;
      try
      {
        in = new FileInputStream(file);
        byte[] bytes = new byte [10000];
        for (int length = in.read(bytes); length != -1; length = in.read(bytes))
        {
          instance.update(bytes, 0, length);
        }
        byte[] digest = instance.digest();

        StringBuilder body = new StringBuilder();
        for (int i = 0; i < digest.length; ++i)
        {
          body.append(Integer.toHexString((digest[i] & 0xFF) | 0x100).substring(1, 3));
        }

        body.append(" *");
        body.append(file.getName());
        out = new FileOutputStream(result);
        out.write(body.toString().getBytes("UTF-8"));
      }
      finally
      {
        if (in != null)
        {
          in.close();
        }

        if (out != null)
        {
          out.close();
        }
      }
    }
    catch (NoSuchAlgorithmException exception)
    {
      throw new IllegalArgumentException(exception);
    }

    return result;
  }

  /**
   * A utility class used to load a repository in order to analyze its contents.
   */
  public static class RepositoryAnalyzer extends AbstractApplication
  {
    /**
     * The pattern for finding the commit ID in a branding plugin's {@code about.mappings}.
     */
    private static final Pattern COMMIT_ID_PATTERN = Pattern.compile("^1=(.*)$", Pattern.MULTILINE);

    /**
     * The pattern for a well-formed Git commit ID.
     */
    private static final Pattern VALID_COMMIT_ID_PATTERN = Pattern.compile("^([0-9a-fA-F]+)$");

    /**
     * The pattern for finding the commit ID in a branding plugin's {@code about.mappings}.
     */
    private static final Pattern BUILD_ID_PATTERN = Pattern.compile("^0=(.*)$", Pattern.MULTILINE);

    /**
     * The pattern for a well-formed build ID.
     */
    private static final Pattern VALID_BUILD_ID_PATTERN = Pattern.compile("^[MNRS]?(\\d{12})$");

    /**
     * The pattern for finding a child location in a {@code compositeContent.xml}.
     */
    private static final Pattern CHILD_LOCATION_PATTERN = Pattern.compile("<child location='([^']*)'");

    /**
     * Returns the repository name.
     *
     * @return the repository name.
     */
    public String getName()
    {
      return getMetadataRepository().getName();
    }

    /**
     * Returns the two-segment version of the largest version of the {@code org.eclipse.emf.sdk.feature.group} in the repository.
     *
     * @return the version of the EMF SDK.
     *
     * @throws ProvisionException
     */
    public String getVersion() throws ProvisionException
    {
      IMetadataRepository repository = getCompositeMetadataRepository();
      IQueryResult<IInstallableUnit> query = repository.query(QueryUtil.createIUQuery("org.eclipse.emf.sdk.feature.group"), new NullProgressMonitor());
      BasicVersion maxVersion = null;
      for (Iterator<IInstallableUnit> i = query.iterator(); i.hasNext();)
      {
        IInstallableUnit iu = i.next();
        Version version = iu.getVersion();
        if (version instanceof BasicVersion)
        {
          BasicVersion basicVersion = (BasicVersion)version;
          if (maxVersion == null || maxVersion.compareTo(basicVersion) < 0)
          {
            maxVersion = basicVersion;
          }
        }
      }

      return maxVersion.getMajor() + "." + maxVersion.getMinor();
    }

    /**
     * Returns the list of child locations in the composite, or {@code null}, if the repository isn't a composite.
     * The URIs in the repository must be file locations and those are the locations returned.
     *
     * @return the list of child locations in the composite, or {@code null}, if the repository isn't a composite.
     */
    public List<String> getChildren()
    {
      IMetadataRepository metadataRepository = getMetadataRepository();
      URI location = metadataRepository.getLocation();
      File file = new File(new File(location), "compositeContent.jar");
      if (file.isFile() && metadataRepository instanceof ICompositeRepository<?>)
      {
        List<String> result = new ArrayList<String>();
        ICompositeRepository<?> compositeRepository = (ICompositeRepository<?>)metadataRepository;
        List<URI> children = compositeRepository.getChildren();
        for (URI uri : children)
        {
          try
          {
            result.add(getCanonicalPath(new File(uri)));
          }
          catch (IOException exception)
          {
            throw new IllegalStateException("The child '" + uri + "' of '" + location + "' is not a file in the file system.", exception);
          }
        }
        return result;
      }
      else
      {
        return null;
      }
    }

    /**
     * Returns a list of the actual value of each child element's location attribute in the composite, or {@code null} if the repository is not a composite.
     *
     * @return a list of the actual value of each child element's location attribute in the composite, or {@code null} if the repository is not a composite.
     */
    public List<String> getRawChildren()
    {
      IMetadataRepository metadataRepository = getMetadataRepository();
      URI location = metadataRepository.getLocation();
      File file = new File(new File(location), "compositeContent.jar");
      if (file.isFile())
      {
        List<String> result = new ArrayList<String>();
        ZipFile zipFile = null;
        try
        {
          zipFile = new ZipFile(file);
          ZipEntry entry = zipFile.getEntry("compositeContent.xml");
          InputStream inputStream = zipFile.getInputStream(entry);
          ByteArrayOutputStream out = new ByteArrayOutputStream();
          byte[] bytes = new byte [10000];
          for (int length = inputStream.read(bytes); length != -1; length = inputStream.read(bytes))
          {
            out.write(bytes, 0, length);
          }

          String content = new String(bytes, "UTF-8");
          for (Matcher matcher = CHILD_LOCATION_PATTERN.matcher(content); matcher.find();)
          {
            result.add(matcher.group(1));
          }
        }
        catch (Exception exception)
        {
          throw new IllegalStateException("Problems with " + file, exception);
        }
        finally
        {
          try
          {
            zipFile.close();
          }
          catch (IOException exception)
          {
            throw new IllegalStateException("Problems closing" + file, exception);
          }
        }

        return result;
      }
      else
      {
        return null;
      }
    }

    /**
     * Returns the sorted list of all the SDK features in the repository.
     * @return the sorted list of all the SDK features in the repository.
     */
    public List<String> getSDKs()
    {
      List<String> result = new ArrayList<String>();
      IMetadataRepository repository = getCompositeMetadataRepository();
      IQueryResult<IInstallableUnit> query = repository.query(QueryUtil.createIUGroupQuery(), new NullProgressMonitor());
      for (Iterator<IInstallableUnit> i = query.iterator(); i.hasNext();)
      {
        IInstallableUnit iu = i.next();
        if (iu.getId().endsWith(".sdk.feature.group"))
        {
          String name = iu.getProperty("org.eclipse.equinox.p2.name", null);
          if (!result.contains(name))
          {
            result.add(name);
          }
        }
      }
      Collections.sort(result);
      return result;
    }

    /**
     * Returns a sorted list of all the features in the repository.
     * @return a sorted list of all the features in the repository.
     */
    public List<String> getFeatures()
    {
      List<String> result = new ArrayList<String>();
      IMetadataRepository repository = getCompositeMetadataRepository();
      IQueryResult<IInstallableUnit> query = repository.query(QueryUtil.createIUGroupQuery(), new NullProgressMonitor());
      for (Iterator<IInstallableUnit> i = query.iterator(); i.hasNext();)
      {
        IInstallableUnit iu = i.next();
        if (!iu.getId().endsWith(".source.feature.group"))
        {
          String name = iu.getProperty("org.eclipse.equinox.p2.name", null);
          name += " " + iu.getVersion();
          name = name.substring(0, name.lastIndexOf('.'));
          if (!result.contains(name))
          {
            result.add(name);
          }
        }
      }
      Collections.sort(result);
      return result;
    }

    /**
     * Returns a map from bundle name to a list of information for that bundle for each bundle in the repository.
     * @return a map from bundle name to a list of information for that bundle for each bundle in the repository.
     */
    public Map<String, List<String>> getBundles()
    {
      Map<String, List<String>> result = new TreeMap<String, List<String>>();
      IMetadataRepository repository = getCompositeMetadataRepository();
      IQueryResult<IInstallableUnit> query = repository.query(QueryUtil.createIUAnyQuery(), new NullProgressMonitor());
      for (Iterator<IInstallableUnit> i = query.iterator(); i.hasNext();)
      {
        IInstallableUnit iu = i.next();
        String id = iu.getId();
        if (!id.endsWith(".source"))
        {
          List<String> lines = new ArrayList<String>();
          for (IProvidedCapability providedCapability : iu.getProvidedCapabilities())
          {
            String namespace = providedCapability.getNamespace();
            String name = providedCapability.getName();
            if ("org.eclipse.equinox.p2.eclipse.type".equals(namespace) && "bundle".equals(name))
            {
              String iuName = iu.getProperty("org.eclipse.equinox.p2.name", null);
              iuName += " " + iu.getVersion();
              iuName = iuName.substring(0, iuName.lastIndexOf('.'));
              if (!result.containsKey(iuName))
              {
                lines.add(0, "\u21D6 " + id + " " + iu.getVersion());
                result.put(iuName, lines);
              }
            }
            else if ("java.package".equals(namespace))
            {
              Version version = providedCapability.getVersion();
              lines.add("\u2196 " + name + (Version.emptyVersion.equals(version) ? "" : " " + version));
            }
          }

          for (IRequirement requirement : iu.getRequirements())
          {
            if (requirement instanceof IRequiredCapability)
            {
              IRequiredCapability requiredCapability = (IRequiredCapability)requirement;
              String namespace = requiredCapability.getNamespace();
              String line = null;
              if ("osgi.bundle".equals(namespace))
              {
                line = "\u21D8 ";
              }
              else if ("java.package".equals(namespace))
              {
                line = "\u2198 ";
              }
              if (line != null)
              {
                String name = requiredCapability.getName();
                VersionRange range = requiredCapability.getRange();

                line += name;
                if (!VersionRange.emptyRange.equals(range))
                {
                  line += " " + range;
                }

                if (requiredCapability.getMin() == 0)
                {
                  line += " optional";
                  if (requiredCapability.isGreedy())
                  {
                    line += " greedy";
                  }
                }

                lines.add(line);
              }
            }
          }
        }
      }
      return result;
    }

    /**
     * Returns a map from project name to the URL for the commit ID URL in that project's branding plugin.
     * @return a map from project name to the URL for the commit ID URL in that project's branding plugin.
     */
    public Map<String, String> getCommits()
    {
      Map<String, String> result = new LinkedHashMap<String, String>();
      getIDs(result, "org.eclipse.emf", COMMIT_ID_PATTERN, VALID_COMMIT_ID_PATTERN, "https://git.eclipse.org/c/emf/org.eclipse.emf.git/commit/?id=");
      getIDs(result, "org.eclipse.xsd", COMMIT_ID_PATTERN, VALID_COMMIT_ID_PATTERN, "https://git.eclipse.org/c/xsd/org.eclipse.xsd.git/commit/?id=");
      getDate();
      return result;
    }

    /**
     * Returns the build date as determined from the EMF project's branding plugin.
     * @return the build date as determined from the EMF project's branding plugin.
     */
    public String getDate()
    {
      Map<String, String> result = new LinkedHashMap<String, String>();
      getIDs(result, "org.eclipse.emf", BUILD_ID_PATTERN, VALID_BUILD_ID_PATTERN, "");
      if (!result.isEmpty())
      {
        String value = result.values().iterator().next();
        try
        {
          Date date = new SimpleDateFormat("yyyyMMddHHmm").parse(value);
          return new SimpleDateFormat("yyyy'-'MM'-'dd' at 'HH':'mm ").format(date);
        }
        catch (ParseException e)
        {
          // Ignore.
        }
      }

      return null;
    }

    /**
     * Populates the IDs with the information from {@code about.mappings} for the branding plugin with the give UI ID.
     * @param ids the IDs to populate.
     * @param iuID the ID of a branding plugin.
     * @param idPattern the pattern for finding the ID in the {@code about.mappings}
     * @param validIDPattern the pattern for validating and extracting the ID.
     * @param prefix the prefix that will be prepended to the ID.
     */
    private void getIDs(Map<String, String> ids, String iuID, Pattern idPattern, Pattern validIDPattern, String prefix)
    {
      IMetadataRepository metadataRepository = getCompositeMetadataRepository();
      IArtifactRepository artifactRepository = getCompositeArtifactRepository();
      IQueryResult<IInstallableUnit> ius = metadataRepository.query(QueryUtil.createIUQuery(iuID), new NullProgressMonitor());
      for (Iterator<IInstallableUnit> i = ius.iterator(); i.hasNext();)
      {
        IInstallableUnit iu = i.next();
        Collection<IArtifactKey> artifacts = iu.getArtifacts();
        for (IArtifactKey artifactKey : artifacts)
        {
          IArtifactDescriptor[] artifactDescriptors = artifactRepository.getArtifactDescriptors(artifactKey);
          for (IArtifactDescriptor artifactDescriptor : artifactDescriptors)
          {
            ByteArrayOutputStream artifact = new ByteArrayOutputStream();
            artifactRepository.getArtifact(artifactDescriptor, artifact, new NullProgressMonitor());
            ZipInputStream zipInputStream = null;
            try
            {
              zipInputStream = new ZipInputStream(new ByteArrayInputStream(artifact.toByteArray()));
              for (ZipEntry entry = zipInputStream.getNextEntry(); entry != null; entry = zipInputStream.getNextEntry())
              {
                String name = entry.getName();
                if ("about.mappings".equals(name))
                {
                  ByteArrayOutputStream content = new ByteArrayOutputStream();
                  byte[] bytes = new byte [1024];
                  for (int length = zipInputStream.read(bytes); length != -1; length = zipInputStream.read(bytes))
                  {
                    content.write(bytes, 0, length);
                  }

                  content.close();
                  String value = new String(content.toByteArray(), "UTF-8");
                  for (Matcher matcher = idPattern.matcher(value); matcher.find();)
                  {
                    String id = matcher.group(1);
                    Matcher idMatcher = validIDPattern.matcher(id);
                    if (idMatcher.matches())
                    {
                      ids.put(iuID.substring(iuID.lastIndexOf('.') + 1).toUpperCase(), prefix + idMatcher.group(1));
                    }
                    break;
                  }
                  break;
                }
              }
            }
            catch (IOException exception)
            {
              // Ignore.
            }
            finally
            {
              if (zipInputStream != null)
              {
                try
                {
                  zipInputStream.close();
                }
                catch (IOException e)
                {
                  // Ignore.
                }
              }
            }
            break;
          }
        }

        break;
      }
    }

    /**
     * Returns the metadata repository of this analyzer.
     * @return the metadata repository of this analyzer.
     */
    private IMetadataRepository getMetadataRepository()
    {
      ICompositeRepository<?> compositeMetadataRepository = (ICompositeRepository<?>)getCompositeMetadataRepository();
      IMetadataRepositoryManager metadataRepositoryManager = getMetadataRepositoryManager();
      try
      {
        return metadataRepositoryManager.loadRepository(compositeMetadataRepository.getChildren().get(0), new NullProgressMonitor());
      }
      catch (Exception exception)
      {
        throw new IllegalStateException(exception);
      }
    }

    @Override
    public IStatus run(IProgressMonitor monitor) throws ProvisionException
    {
      return null;
    }
  }
}
