/**
 * Copyright (c) 2018 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.releng;


import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.releng.UpdateSiteGenerator.RepositoryAnalyzer;


/**
 * A utility class used by the {@code index.htmljet} template to generate an index.html.
 */
public class UpdateSiteIndexGenerator
{
  /**
   * The ordered folders that will be indexed.
   */
  private static final String[] ROOT_FOLDERS = new String []{ "release", "milestone", "nightly" };

  /**
   * The folder for this generator..
   */
  private final File folder;

  /**
   * The update site generator used by this index generator.
   */
  private final UpdateSiteGenerator updateSiteGenerator = new UpdateSiteGenerator();

  /**
   * The repository analyzer used to analyze the index generator's folder.
   */
  private final RepositoryAnalyzer repositoryAnalyzer;

  /**
   * The list of SDKs in this folder's repository.
   */
  private List<String> sdks;

  /**
   * Creates an instance for the given folder.
   * 
   * @param folder the folder.
   */
  public UpdateSiteIndexGenerator(String folder)
  {
    this.folder = new File(folder);

    List<String> folders = new ArrayList<String>();
    if (isRoot())
    {
      for (String child : ROOT_FOLDERS)
      {
        File childFolder = new File(folder, child);
        if (childFolder.isDirectory())
        {
          folders.add(childFolder.getAbsolutePath());
        }
      }
    }
    else
    {
      folders.add(folder);
    }
    repositoryAnalyzer = updateSiteGenerator.getRepositoryAnalyzer(folders);
  }

  /**
   * Returns whether this folder is the build root folder.
   * @return whether this folder is the build root folder.
   */
  public boolean isRoot()
  {
    return folder.equals(new File(UpdateSiteGenerator.BUILDS_ROOT_FOLDER));
  }

  /**
   * Returns the build type of this folder.
   * It's not appropriate to call this method on the root folder.
   * @return the build type of this folder.
   */
  public String getBuildType()
  {
    for (String buildType : UpdateSiteGenerator.BUILD_TYPES)
    {
      if (folder.getPath().replace('\\', '/').contains("/" + buildType))
      {
        return buildType;
      }
    }
    throw new IllegalStateException("The folder '" + folder + "' is not a build-type folder");
  }

  /**
   * Returns whether is a latest folder.
   * @return whether is a latest folder.
   */
  public boolean isLatest()
  {
    return folder.getPath().endsWith("latest");
  }

  /**
   * Returns whether this folder has a download archive along with associated SHA digests.
   * @return
   */
  public boolean hasArchive()
  {
    File archiveFile = UpdateSiteGenerator.getArchiveFile(folder.getPath());
    return archiveFile.isFile() && UpdateSiteGenerator.getDigestFile(archiveFile.getPath(), "SHA-256").isFile()
      && UpdateSiteGenerator.getDigestFile(archiveFile.getPath(), "SHA-512").isFile();
  }

  /**
   * Returns the download archive location.
   * @return the download archive location.
   */
  public String getArchive()
  {
    File archiveFile = UpdateSiteGenerator.getArchiveFile(folder.getPath());
    return archiveFile.getPath();
  }

  /**
   * Returns the URL to be used for loading the archive.
   * @return the URL to be used for loading the archive.
   */
  public String getArchiveDownloadURL()
  {
    File archiveFile = UpdateSiteGenerator.getArchiveFile(folder.getPath());
    return "https://www.eclipse.org/downloads/download.php?file=/" + archiveFile.getPath().substring(UpdateSiteGenerator.DOWLOAD_ROOT_FOLDER.length()).replace('\\', '/');
  }

  /**
   * Returns the digest location for the given algorithm.
   * @param algorithm the algorithm of the digest.
   * @return the digest location for the given algorithm.
   */
  public String getDigest(String algorithm)
  {
    File archiveFile = UpdateSiteGenerator.getArchiveFile(folder.getPath());
    File digestFile = UpdateSiteGenerator.getDigestFile(archiveFile.getPath(), algorithm);
    return digestFile.getPath();
  }

  /**
   * Returns the map used to populate the navigation sidebar.
   * @return the map used to populate the navigation sidebar.
   */
  public Map<String, String> getNavigation()
  {
    String siteURL = getSiteURL();
    UpdateSiteIndexGenerator root = new UpdateSiteIndexGenerator(UpdateSiteGenerator.BUILDS_ROOT_FOLDER);
    String rootSiteURL = root.getSiteURL();

    StringBuilder prefix = new StringBuilder();
    for (int index = siteURL.indexOf('/', rootSiteURL.length()); index != -1; index = siteURL.indexOf('/', index + 1))
    {
      prefix.append("../");
    }

    Map<String, String> result = new LinkedHashMap<String, String>();
    result.put(prefix.length() == 0 ? "." : prefix.toString().substring(0, prefix.length() - 1), isRoot() ? "Builds@" : "Builds");
    for (UpdateSiteIndexGenerator child : root.getChildren())
    {
      child.visit(result, prefix.toString(), 0);
    }

    URI rootSiteURI = URI.create(siteURL + "/");
    for (Map.Entry<String, String> entry : result.entrySet())
    {
      String url = entry.getKey();
      URI resolvedURI = rootSiteURI.resolve(url);
      if (resolvedURI.toString().equals(siteURL))
      {
        entry.setValue(entry.getValue() + "@");
      }
    }

    return result;
  }

  /**
   * Used to composite the {@link #getNavigation() navigation} sidebar.
   * @param navigation the map to populate.
   * @param prefix the prefix used to build relative URL.
   * @param depth the depth at which we're currently visiting.
   */
  private void visit(Map<String, String> navigation, String prefix, int depth)
  {
    StringBuilder label = new StringBuilder();
    for (int i = 0; i < depth; ++i)
    {
      label.append('-');
    }
    label.append(getLabel());

    String siteURL = getSiteURL();
    String rootSiteURL = getSiteURL(new File(UpdateSiteGenerator.BUILDS_ROOT_FOLDER));
    siteURL = siteURL.substring(rootSiteURL.length() + 1);
    siteURL = prefix + siteURL;

    navigation.put(siteURL, label.toString());
    for (UpdateSiteIndexGenerator child : getChildren())
    {
      child.visit(navigation, prefix, depth + 1);
    }
  }

  /**
   * Returns the site URL of this folder.
   * @return the site URL of this folder.
   */
  public String getSiteURL()
  {
    return getSiteURL(folder);
  }

  /**
   * This is specialized to ensure that on the real build host we use the https: URL not the file: URL that we use locally when testing.
   * @param folder the folder for which we want the site URL.
   * @return the site URL.
   */
  private static String getSiteURL(File folder)
  {
    String path = folder.getPath();
    if (UpdateSiteGenerator.DOWLOAD_ROOT_FOLDER.equals(UpdateSiteGenerator.DOWNLOAD_ECLIPSE_ORG_FOLDER))
    {
      return "https://download.eclipse.org/" + path.substring(UpdateSiteGenerator.DOWLOAD_ROOT_FOLDER.length());
    }
    else
    {
      return UpdateSiteGenerator.createURI(folder).toString();
    }
  }

  /**
   * The parent-relative URL for this folder's index.
   * @return the parent-relative URL for this folder's index.
   */
  public String getRelativeIndexURL()
  {
    return folder.getName() + "/index.html";
  }

  /**
   * This is used to populate the breadcumbs.
   * @return the breadcumbs.
   */
  public Map<String, String> getBreadcrumbs()
  {
    File root = new File(UpdateSiteGenerator.BUILDS_ROOT_FOLDER);

    // Compute the labels in the right order.
    List<String> labels = new ArrayList<String>();
    for (File file = folder; file.getParentFile() != null; file = file.getParentFile())
    {
      if (file.equals(root))
      {
        labels.add(0, "Builds");
        break;
      }
      else
      {
        String name = file.getName();
        labels.add(0, Character.toUpperCase(name.charAt(0)) + name.substring(1));
      }
    }

    // Compute the uplinks in the reverse order.
    Map<String, String> result = new LinkedHashMap<String, String>();
    String link = null;
    for (int i = labels.size() - 1; i >= 0; --i)
    {
      String label = labels.get(i);
      result.put(label, link);
      if (link == null)
      {
        link = "../index.html";
      }
      else
      {
        link = "../" + link;
      }
    }

    // Build another map in the right order.
    Map<String, String> breadcumbs = new LinkedHashMap<String, String>();
    for (String label : labels)
    {
      breadcumbs.put(label, result.get(label));
    }
    return breadcumbs;
  }

  /**
   * Returns the title for this folder's index.
   * @return the title for this folder's index.
   */
  public String getTitle()
  {
    if (isRoot())
    {
      return "EMF Update Sites";
    }
    else
    {
      String name = repositoryAnalyzer.getName();
      String folderName = folder.getName();
      if (!name.toLowerCase().contains(folderName.toLowerCase()))
      {
        name += " - " + Character.toUpperCase(folderName.charAt(0)) + folderName.substring(1);
      }
      return name;
    }
  }

  /**
   * Returns the title case label for this folder.
   * @return the title case label for this folder.
   */
  public String getLabel()
  {
    String name = getFolderName();
    return Character.toUpperCase(name.charAt(0)) + name.substring(1);
  }

  /**
   * Returns a map from repository URIs to relative site URLs of that repository.
   * @return
   */
  public Map<String, String> getRepositoryChildren()
  {
    List<String> repositories = repositoryAnalyzer.getRawChildren();
    if (repositories != null)
    {
      Map<String, String> result = new LinkedHashMap<String, String>();
      URI siteURL = URI.create(getSiteURL() + "/");
      for (String repository : repositories)
      {
        URI uri = URI.create(repository);
        if (uri.isAbsolute() || uri.isOpaque())
        {
          throw new IllegalStateException("Bad non-relative URI" + uri);
        }

        URI resolvedURI = siteURL.resolve(uri);
        result.put(resolvedURI.toString(), repository);
      }
      return result;
    }
    else
    {
      return null;
    }
  }

  /**
   * Returns the name of this folder.
   * @return the name of this folder.
   */
  public String getFolderName()
  {
    return folder.getName();
  }

  /**
   * Returns the SDKs in this folder's repository.
   * @return the SDKs in this folder's repository.
   */
  public List<String> getSDKs()
  {
    if (sdks == null)
    {
      sdks = repositoryAnalyzer.getSDKs();
    }
    return sdks;
  }

  /**
   * Returns whether the given feature is an SDK.
   * @param feature the feature.
   * @return whether the given feature is an SDK.
   */
  public boolean isSDK(String feature)
  {
    for (String sdk : getSDKs())
    {
      if (feature.startsWith(sdk))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the feature names in this folder's repository.
   * @return the feature names in this folder's repository.
   */
  public List<String> getFeatures()
  {
    return repositoryAnalyzer.getFeatures();
  }

  /**
   * Returns the bundle information for this folder's repository.
   * @return the bundle information for this folder's repository.
   */
  public Map<String, List<String>> getBundles()
  {
    return repositoryAnalyzer.getBundles();
  }

  public String getFolderID(String folder)
  {
    return "_" + new File(folder).getName().replace('.', '_');
  }

  /**
   * Returns the ordered index generators for the children of this folder.
   * @return the ordered index generators for the children of this folder.
   */
  public List<UpdateSiteIndexGenerator> getChildren()
  {
    List<UpdateSiteIndexGenerator> result = new ArrayList<UpdateSiteIndexGenerator>();
    File[] listFiles = folder.listFiles();
    List<String> children = new ArrayList<String>();
    if (listFiles != null)
    {
      for (File child : listFiles)
      {
        if (child.isDirectory() && (new File(child, "compositeContent.jar").isFile() || new File(child, "content.jar").isFile()))
        {
          children.add(child.getAbsolutePath());
        }
      }
    }

    UpdateSiteGenerator.sort(children);

    for (String child : children)
    {
      result.add(new UpdateSiteIndexGenerator(child));
    }

    return result;
  }

  /**
   * Returns a map from project name to the URL for the commit ID URL in that project's branding plugin.
   * @return a map from project name to the URL for the commit ID URL in that project's branding plugin.
   */
  public Map<String, String> getCommits()
  {
    return repositoryAnalyzer.getCommits();
  }

  /**
   * Returns the date string for when the IUs in the repository were built.
   * @return the date string for when the IUs in the repository were built.
   */
  public String getDate()
  {
    return repositoryAnalyzer.getDate();
  }
}
