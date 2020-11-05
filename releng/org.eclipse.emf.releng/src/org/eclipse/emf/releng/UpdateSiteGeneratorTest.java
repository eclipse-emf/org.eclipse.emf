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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.releng.UpdateSiteGenerator.RepositoryAnalyzer;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


/**
 * <p>
 * This test class is more than just a test.
 * It is used to maintain, update, and manage the integrity of EMF's public update site:
 * <pre><a href="https://download.eclipse.org/modeling/emf/emf/builds/index.html">https://download.eclipse.org/modeling/emf/emf/builds/index.html</a></pre>
 * It can also be used locally to test the promotion process.
 * The physical file location that will be updated by these tests is rooted at at {@link UpdateSiteGenerator#BUILDS_ROOT_FOLDER}.
 * </p>
 * <p>
 * The base location on the local file system for testing local Tycho builds is specified as a system property, e.g.,
 * </p>
 * <pre>-Dpublish.download.root.folder=D:/develop/</pre>
 * The subfolder {@code modeling/emf/emf/builds} will be appended to produce the {@code BUILDS_ROOT_FOLDER} value, e.g.,
 * <pre>D:/develop/modeling/emf/emf/builds</pre>
 * <p>
 * EMF's public Tycho build is hosted at the following Jenkins site:
 * </p>
 * <pre><a href="https://ci.eclipse.org/emf/job/integration/">https://ci.eclipse.org/emf/job/integration/</a></pre>
 * <p>
 * This build job uses the following path on the build host {@code build.eclipse.org} for the value of {@code BUILDS_ROOT_FOLDER}:
 * </p>
 * <pre>/home/data/httpd/download.eclipse.org/modeling/emf/emf/builds</pre>
 * <p>
 * Because the tests produce side effects, it's important that they be performed in a deterministic order so the methods are named to produce the correct lexicographic ordering,
 * i.e., the order in which they appear in the source file.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UpdateSiteGeneratorTest
{
  /**
   * Promotes the update site produced by the Maven Tycho build.
   * <p>
   * The source repository is located in the workspace path for {@code org.eclipse.emf.site/target/repository} as produced by the Maven Tycho build of EMF's root POM.
   * The target is located under the folder {@link UpdateSiteGenerator#BUILDS_ROOT_FOLDER}
   * using a name that depends on the system property for {@code publish.build.timestamp} and the system property for {@code publish.build.type}.
   * The source repository is tested for existence,
   * the {@code publish.build.timestamp} is tested to be of the form yyyMMddHHmm,
   * the {@code publish.build.type} is test to be one {@link UpdateSiteGenerator#BUILD_TYPES}.
   * If {@code publish.repository} is not the value 'true',
   * or {@code publish.build.type} is 'release',
   * no build will be promoted, i.e., the test will be skipped;
   * releases are promoted by {@link #step02AggregateReleaseUpdateSite()}.
   * </p>
   * <p>
   * The update site will be mirrored to <tt>${publish.build.type}/{N|S}${publish.build.timestamp}}</tt>,
   * the composite <tt>${publish.build.type}</tt> will be updated to add it as a reference,
   * and the composite <tt>${publish.build.type}/latest</tt> will be updated to replace it as the one and only reference.
   * </p>
   *
   * @throws Exception
   */
  @Test
  public void step01PromoteUpdateSite() throws Exception
  {
    String userDir = System.getProperty("user.dir");
    String sourceRepository = UpdateSiteGenerator.getCanonicalPath(new File(userDir, "../org.eclipse.emf.site/target/repository"));
    System.out.println("sourceRepository=" + sourceRepository);
    Assert.assertTrue("Update site is invalid: " + sourceRepository, new File(sourceRepository, "content.jar").isFile());

    String buildTimestamp = System.getProperty("publish.build.timestamp");
    Assert.assertNotNull("publish.build.timestamp is null", buildTimestamp);
    try
    {
      new SimpleDateFormat("yyyyMMddHHmm").parse(buildTimestamp);
    }
    catch (ParseException exception)
    {
      Assert.fail("The publish.build.timestamp value '" + buildTimestamp + "'must be of the form yyyMMddHHmm");
    }

    System.out.println("publish.build.timestamp=" + buildTimestamp);
    Assert.assertTrue(
      "publish.build.timestamp value '" + buildTimestamp + "'must be of the form 'yyyyMMddHHmm' and must be in the century 2000",
      buildTimestamp.matches("20\\d{10}"));

    String buildType = System.getProperty("publish.build.type");
    System.out.println("publish.build.type=" + buildType);
    Assert.assertTrue("publish.build.type must be one of " + UpdateSiteGenerator.BUILD_TYPES, UpdateSiteGenerator.BUILD_TYPES.contains(buildType));

    Assume.assumeTrue("true".equals(System.getProperty("publish.repository")));
    Assume.assumeTrue(!"release".equals(System.getProperty("publish.build.type")));

    UpdateSiteGenerator updateSiteGenerator = new UpdateSiteGenerator();
    System.out.println("Promoting update site to " + updateSiteGenerator.getPromoteUpdateSiteDestination(buildType, buildTimestamp));
    File promotedUpdateSite = updateSiteGenerator.promoteUpdateSite(sourceRepository, buildType, buildTimestamp);

    // Gather the existing simple repository children.
    List<String> children = new ArrayList<String>();
    File promotedUpdateSiteParent = promotedUpdateSite.getParentFile();
    File[] compositeChildren = promotedUpdateSiteParent.listFiles();
    if (compositeChildren != null)
    {
      for (File child : compositeChildren)
      {
        if (child.isDirectory() && new File(child, "content.jar").exists())
        {
          children.add(UpdateSiteGenerator.getCanonicalPath(child));
        }
      }
    }

    // Sort to ensure that the most recent update site is first in the list.
    UpdateSiteGenerator.sort(children);

    String compositePath = updateSiteGenerator.getCompositeUpdateSiteDestination(buildType, false);
    System.out.println("Composing update site " + compositePath);
    updateSiteGenerator.composeUpdateSites(children, buildType, false);

    String latestCompositePath = updateSiteGenerator.getCompositeUpdateSiteDestination(buildType, true);
    System.out.println("Composing update site " + latestCompositePath);
    updateSiteGenerator.composeUpdateSites(Collections.singletonList(UpdateSiteGenerator.getCanonicalPath(promotedUpdateSite)), buildType, true);
  }

  /**
   * Promotes (mirrors) the latest milestone build to be the release build.
   * The source is {@code milestone/latest} and the target is {@code release/x.y},
   * where {@code x.y} is computed from the version of the {@code org.eclipse.emf.sdk.feature.group} installable unit in the source repository.
   * <p>
   * This test is skipped if {@code publish.repository} is not 'true", or {@code publish.build.type} is not 'release'.
   * This test fails if the release already exists.
   * </p>
   *
   * @throws Exception
   */
  @Test
  public void step02AggregateReleaseUpdateSite() throws Exception
  {
    Assume.assumeTrue("true".equals(System.getProperty("publish.repository")));
    Assume.assumeTrue("release".equals(System.getProperty("publish.build.type")));

    UpdateSiteGenerator updateSiteGenerator = new UpdateSiteGenerator();
    String source = updateSiteGenerator.getFirstChild(UpdateSiteGenerator.BUILDS_ROOT_FOLDER + "/milestone/latest");
    String version = updateSiteGenerator.getVersion(source);
    final String destination = updateSiteGenerator.getPromoteUpdateSiteDestination("release", version);
    Assert.assertFalse("The release '" + destination + "' already exists", new File(destination).exists());
    updateSiteGenerator.mirrorUpdateSite(source, "release");
  }

  /**
   * Manages the integrity of the the composites {@code release}, {@code release/latest}, {@code milestone}, {@code milestone/latest}, {@code nightly}, and {@code nightly/latest}.
   * This test always runs and will fail with an exception if any site can't be loaded.
   *
   * @throws Exception
   */
  @Test
  public void step03RecomposeComposites() throws Exception
  {
    UpdateSiteGenerator updateSiteGenerator = new UpdateSiteGenerator();
    File buildsRootFolder = new File(UpdateSiteGenerator.BUILDS_ROOT_FOLDER);
    Assert.assertTrue(buildsRootFolder.isDirectory());

    for (File child : buildsRootFolder.listFiles())
    {
      String buildType = child.getName();
      if (child.isDirectory() && UpdateSiteGenerator.BUILD_TYPES.contains(buildType))
      {
        List<String> children = new ArrayList<String>();
        for (File grandChild : child.listFiles())
        {
          String name = grandChild.getName();
          if (!"latest".equals(name) && grandChild.isDirectory() && new File(grandChild, "content.jar").isFile())
          {
            children.add(UpdateSiteGenerator.getCanonicalPath(grandChild));
          }
        }

        UpdateSiteGenerator.sort(children);

        String compositePath = updateSiteGenerator.getCompositeUpdateSiteDestination(buildType, false);
        System.out.println("Composing update site " + compositePath);
        cleanupComposite(compositePath);
        updateSiteGenerator.composeUpdateSites(children, buildType, false);

        List<String> latestUpdateSite = new ArrayList<String>();
        if (!children.isEmpty())
        {
          latestUpdateSite.add(children.get(0));
        }

        String latestCompositePath = updateSiteGenerator.getCompositeUpdateSiteDestination(buildType, true);
        System.out.println("Composing update site " + latestCompositePath);
        cleanupComposite(latestCompositePath);
        updateSiteGenerator.composeUpdateSites(latestUpdateSite, buildType, true);
      }
    }
  }

  /**
   * Tests that the {@code nightly} composite is well formed.
   * This test is skipped if {@code publish.repository} is not 'true'.
   * If {@code publish.clean} is 'true",
   * it deletes older builds to ensure that there are at most {@link UpdateSiteGenerator#RETAINED_NIGHTLY_BUILDS} nightly builds,
   * updating the composite to reference only remaining builds.
   * Otherwise the test fails if there are too many nightly builds.
   *
   * @throws Exception
   */
  @Test
  public void step04CleanNightlyComposite() throws Exception
  {
    Assume.assumeTrue("true".equals(System.getProperty("publish.repository")));

    UpdateSiteGenerator updateSiteGenerator = new UpdateSiteGenerator();
    String nightlyRepository = updateSiteGenerator.getCompositeUpdateSiteDestination("nightly", false);
    RepositoryAnalyzer repositoryAnalyzer = updateSiteGenerator.getRepositoryAnalyzer(Collections.singletonList(nightlyRepository));
    List<String> children = repositoryAnalyzer.getChildren();
    Assert.assertNotNull("The folder '" + nightlyRepository + "' is not a valid composite. You must do at least one nightly build.", children);
    Assert.assertNotEquals("The folder '" + nightlyRepository + "' is an empty composite. You must do at least one nightly build.", 0, children.size());

    for (String child : children)
    {
      Assert.assertEquals("The folder '" + child + "' is expected to be a direct child of '" + nightlyRepository, new File(nightlyRepository), new File(child).getParentFile());
    }

    for (String child : repositoryAnalyzer.getRawChildren())
    {
      Assert.assertTrue("The raw child '" + child + "' is expected to be a relative URI", !URI.create(child).isAbsolute());
    }

    if ("true".equals(System.getProperty("publish.clean")))
    {
      if (children.size() > UpdateSiteGenerator.RETAINED_NIGHTLY_BUILDS)
      {
        UpdateSiteGenerator.sort(children);
        List<String> childrenToDelete = children.subList(UpdateSiteGenerator.RETAINED_NIGHTLY_BUILDS, children.size());
        for (String childToDelete : childrenToDelete)
        {
          System.out.println("Deleting old nightly build: " + childToDelete);
          Assert.assertTrue("Could not delete '" + childToDelete + "'", delete(new File(childToDelete)));
        }

        cleanupComposite(nightlyRepository);
        updateSiteGenerator.composeUpdateSites(children.subList(0, UpdateSiteGenerator.RETAINED_NIGHTLY_BUILDS), "nightly", false);
      }
    }
    else
    {
      Assert.assertTrue(
        "The folder '" + nightlyRepository + "' contains too many children. You must enable publish.clean.",
        children.size() <= UpdateSiteGenerator.RETAINED_NIGHTLY_BUILDS);
    }
  }

  /**
   * Tests that the {@code milestone} composite is well formed.
   * This test is skipped if {@code publish.repository} is not 'true'.
   * If {@code publish.clean} is 'true",
   * it deletes older builds to ensure that each milestone build has the same newest version of the {@code org.eclipse.emf.sdk.feature.group} installable unit.
   * In other words, milestone builds are delete as soon as there is a milestome build a newer version of EMF,
   * as opposed to deleting all the milestone builds after a release build, which would leave the {@code milestone} and {@code milestone/latest} empty.
   *
   * @throws Exception
   */
  @Test
  public void step05CleanMilestoneComposite() throws Exception
  {
    Assume.assumeTrue("true".equals(System.getProperty("publish.repository")));

    UpdateSiteGenerator updateSiteGenerator = new UpdateSiteGenerator();
    String milestoneRepository = updateSiteGenerator.getCompositeUpdateSiteDestination("milestone", false);
    Assume.assumeTrue(new File(milestoneRepository).isDirectory());

    RepositoryAnalyzer repositoryAnalyzer = updateSiteGenerator.getRepositoryAnalyzer(Collections.singletonList(milestoneRepository));
    List<String> children = repositoryAnalyzer.getChildren();
    Assert.assertNotNull("The folder '" + milestoneRepository + "' is not a valid composite. You must do at least one milestone build.", children);
    Assert.assertNotEquals("The folder '" + milestoneRepository + "' is an empty composite. You must do at least one milestone build.", 0, children.size());

    String version = repositoryAnalyzer.getVersion();

    boolean publishClean = "true".equals(System.getProperty("publish.clean"));
    List<String> childrenToDelete = new ArrayList<String>();
    for (String child : children)
    {
      RepositoryAnalyzer childRepositoryAnalyzer = updateSiteGenerator.getRepositoryAnalyzer(Collections.singletonList(child));
      String childVersion = childRepositoryAnalyzer.getVersion();
      if (publishClean)
      {
        if (!version.equals(childVersion))
        {
          childrenToDelete.add(child);
        }
      }
      else
      {
        Assert.assertEquals(
          "The repository '" + child + "' is stale, i.e., has an older version than the latest version. You must enable publish.clean to remove it.",
          version,
          childVersion);
      }
    }

    for (String child : children)
    {
      Assert.assertEquals("The folder '" + child + "' is expected to be a direct child of '" + milestoneRepository, new File(milestoneRepository), new File(child).getParentFile());
    }

    for (String child : repositoryAnalyzer.getRawChildren())
    {
      Assert.assertTrue("The raw child '" + child + "' is expected to be a relative URI", !URI.create(child).isAbsolute());
    }

    if (!childrenToDelete.isEmpty())
    {
      for (String childToDelete : childrenToDelete)
      {
        System.out.println("Deleting old milestone build: " + childToDelete);
        Assert.assertTrue("Could not delete '" + childToDelete + "'", delete(new File(childToDelete)));
        children.remove(childToDelete);
      }

      cleanupComposite(milestoneRepository);
      updateSiteGenerator.composeUpdateSites(children, "milestone", false);
    }
  }

  /**
   * Produces zipped downloads for each simple repository, along with associated SHA-256 and SHA-512 hashes.
   * This test is skipped if {@code publish.repository} is not 'true'.
   *
   * @throws Exception
   */
  @Test
  public void step06GenerateDownloads() throws Exception
  {
    Assume.assumeTrue("true".equals(System.getProperty("publish.repository")));

    UpdateSiteGenerator updateSiteGenerator = new UpdateSiteGenerator();
    updateSiteGenerator.generateDownloads(UpdateSiteGenerator.BUILDS_ROOT_FOLDER);
  }

  /**
   * Generates an index.html is each folder.
   *
   * @throws Exception
   */
  @Test
  public void step07GenerateUpdateSiteIndex() throws Exception
  {
    UpdateSiteGenerator updateSiteGenerator = new UpdateSiteGenerator();
    updateSiteGenerator.generateIndex(UpdateSiteGenerator.BUILDS_ROOT_FOLDER);
  }

  /**
   * Removes the metadata for a composite, otherwise p2 tries to load it and at this point these files are not valid.
   *
   * @param target the repository composite folder to clean.
   */
  private static void cleanupComposite(String target)
  {
    new File(target, "compositeContent.jar").delete();
    new File(target, "compositeContent.xml").delete();
    new File(target, "compositeArtifacts.jar").delete();
    new File(target, "compositeArtifacts.xml").delete();
  }

  /**
   * Deletes a folder and all of it's children.
   *
   * @param folder the folder to delete.
   * @return whether the delete was successful.
   */
  private static boolean delete(File folder)
  {
    boolean deleted = true;
    if (folder != null)
    {
      if (folder.isDirectory())
      {
        File[] children = folder.listFiles();
        if (children != null)
        {
          for (File child : children)
          {
            deleted &= delete(child);
          }
        }
      }

      if (!folder.delete())
      {
        deleted = false;
        folder.deleteOnExit();
      }
    }

    return deleted;
  }

  /**
   * Aggregates the releases of the old update site to the new update site.
   * This test is disabled.
   *
   * @throws Exception
   */
  //@Test
  public void aggregateOldReleases() throws Exception
  {
    Assume.assumeTrue("true".equals(System.getProperty("publish.repository")));

    UpdateSiteGenerator updateSiteGenerator = new UpdateSiteGenerator();
    String[] oldReleases = new String []{ //

      // UpdateSiteGenerator.BUILDS_ROOT_FOLDER + "/milestone",

      "/home/data/httpd/download.eclipse.org/modeling/emf/emf/updates/2.6",
      "/home/data/httpd/download.eclipse.org/modeling/emf/emf/updates/2.7.x",
      "/home/data/httpd/download.eclipse.org/modeling/emf/emf/updates/2.8.x",
      "/home/data/httpd/download.eclipse.org/modeling/emf/emf/updates/2.9.x",
      "/home/data/httpd/download.eclipse.org/modeling/emf/emf/updates/2.10.x",
      "/home/data/httpd/download.eclipse.org/modeling/emf/emf/updates/2.11.x",
      "/home/data/httpd/download.eclipse.org/modeling/emf/emf/updates/2.12",
      "/home/data/httpd/download.eclipse.org/modeling/emf/emf/updates/2.13" //
    };

    List<String> destinations = new ArrayList<String>();
    for (String source : oldReleases)
    {
      destinations.add(UpdateSiteGenerator.getCanonicalPath(updateSiteGenerator.mirrorUpdateSite(source, "release")));
    }

    String compositePath = updateSiteGenerator.getCompositeUpdateSiteDestination("release", false);
    System.out.println("Composing update site " + compositePath);
    updateSiteGenerator.composeUpdateSites(destinations, "release", false);

    String latestCompositePath = updateSiteGenerator.getCompositeUpdateSiteDestination("release", true);
    System.out.println("Composing update site " + latestCompositePath);
    updateSiteGenerator.composeUpdateSites(Collections.singletonList(destinations.get(destinations.size() - 1)), "release", true);
  }
}
