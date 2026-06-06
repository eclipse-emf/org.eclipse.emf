/**
 * Copyright (c) 2026 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.releng.updater;


import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;


/**
 * This is used at the start of a release cycle generate all the changes needed for that cycle.
 * This includes deleting the release.xml files to reset the version baseline. 
 * Change the values below for the value of the new cycle.
 * The GenModel.genmodel needs to be regenerated, and special care is needed to fix the poor formatting of the generate enum classes and to fix the generated properties in the edit plug-in.
 * 
 * The application takes a single argument that is the root of the EMF Git repository.
 */
public class UpdaterApplication implements IApplication
{
  private static final Pattern PLATFORM_VERSION_PATTERN = Pattern.compile("4\\.([0-9]+)(\\..*)?");

  /**
   * Change this to the new version of the platform.
   */
  private final String platformVersion = "4.41";

  /**
   * Change this to the final release repository of the SDK for the current release.
   */
  private final String sdkURL = "https://download.eclipse.org/eclipse/updates/4.40/R-4.40-202606010713";

  /**
   * Change this to the new version of EMF for the new release cycle.
   */
  private final String emfVersion = "2.47";

  /**
   * Change this to the new version of the JDK that you want to add because it will be released or become a new beta during the release cycle.
   */
  private final String jdkVersion = "27.0";

  /**
   * Change this to the Java version needed to run the Tycho/Maven build.
   */
  private final String buildJRE = "21";

  private final String simrelVersion = getSimRelVersion(platformVersion);

  private final Map<Path, String> contents = new LinkedHashMap<>();

  private final String previousSimrelVersion = getPreviousSimrelVersion(simrelVersion);

  private Path root;

  public Object start(IApplicationContext context) throws Exception
  {
    var arguments = List.of((String[])context.getArguments().get(IApplicationContext.APPLICATION_ARGS));
    root = Path.of(arguments.get(0)).toRealPath();
    if (!Files.isDirectory(root.resolve("releng/org.eclipse.emf.releng/org.eclipse.emf.releng.updater")))
    {
      throw new RuntimeException("Expecting to run this for the EMF Git clone");
    }
    update();
    return null;
  }

  public void stop()
  {
  }

  private String getContent(Path path) throws IOException
  {
    return contents.containsKey(path) ? contents.get(path) : Files.readString(path);
  }

  private void apply(Path path, String pattern, String... replacements) throws IOException
  {
    var content = getContent(path);
    var matcher = Pattern.compile(pattern).matcher(content);
    if (matcher.find())
    {
      var modifiedContent = new StringBuilder(content);
      var offset = 0;
      do
      {
        var delta = modifiedContent.length();
        for (var group = matcher.groupCount(); group >= 1; --group)
        {
          var replacement = replacements[group - 1].replace("$1", matcher.group(1));
          modifiedContent.replace(offset + matcher.start(group), offset + matcher.end(group), replacement);
        }
        delta -= modifiedContent.length();
        offset -= delta;
      }
      while (matcher.find());
      if (!modifiedContent.toString().equals(content))
      {
        contents.put(path, modifiedContent.toString());
      }
    }
  }

  private void addEEnumLiteral(EEnumLiteral eEnumLiteral, String name, String literal)
  {
    var newEEnumLiteral = EcoreUtil.copy(eEnumLiteral);
    newEEnumLiteral.setName(name);
    newEEnumLiteral.setLiteral(literal);
    newEEnumLiteral.setValue(eEnumLiteral.getValue() + 1);
    EcoreUtil.setDocumentation(newEEnumLiteral, "@since " + emfVersion);
    eEnumLiteral.getEEnum().getELiterals().add(newEEnumLiteral);
  }

  private void visit(Path file) throws IOException
  {
    var relativePathName = root.relativize(file).toString().replace('\\', '/');
    var fileName = file.getFileName().toString();
    if (fileName.equals("release.xml"))
    {
      contents.put(file, null);
    }
    else if (relativePathName.equals("Jenkinsfile"))
    {
      apply(file, "def targetPlatformToJavaVersionMap = \\[(\r?\n) +'" + previousSimrelVersion + "' : '\\d\\d',", "$1  '" + simrelVersion + "' : '" + buildJRE + "',$1");
      apply(file, "def targetPlatformToTychoVersionMap = \\[(\r?\n) +'" + previousSimrelVersion + "' : '[^']*',", "$1  '" + simrelVersion + "' : '',$1");
    }
    else if (fileName.equals(previousSimrelVersion + ".target"))
    {
      String content = getContent(file);
      apply(file, "\"(https://download.eclipse.org/eclipse/updates/[0-9.]+-I-builds)\"", sdkURL);

      // Create a new target that is a modified version of the current/older target.
      var newTarget = file.resolveSibling(simrelVersion + ".target");
      if (!Files.isRegularFile(newTarget))
      {
        var newTargetContent = content.replace(previousSimrelVersion, simrelVersion).replaceAll(
          "(https://download.eclipse.org/eclipse/updates/)[0-9.]+(-I-builds)",
          "$1" + platformVersion + "$2");
        contents.put(newTarget, newTargetContent);
      }
    }
    else if (fileName.equals("Build EMF.launch"))
    {
      apply(file, previousSimrelVersion + "((?:\\&#13;)?(?:\\&#10;))(?:\\&#9;|\\&#10;|\\&#13;)+Change -DskipTests=false", "$1" + "&#9;&#9;" + simrelVersion + "$1");
      apply(file, ":-Dtarget-platform=(" + previousSimrelVersion + ")", simrelVersion);
    }
    else if (fileName.equals("GenModel.ecore"))
    {
      var resourceSet = new ResourceSetImpl();
      var genModelEcoreURI = org.eclipse.emf.common.util.URI.createFileURI(file.toString());
      var genModelEcore = resourceSet.getResource(genModelEcoreURI, true);
      EcoreUtil.resolveAll(resourceSet);
      EEnumLiteral lastEMF = null;
      EEnumLiteral lastEclipse = null;
      EEnumLiteral lastJDK = null;

      for (var it = genModelEcore.getAllContents(); it.hasNext();)
      {
        var eObject = it.next();
        if (eObject instanceof EEnumLiteral eEnumLiteral)
        {
          var literal = eEnumLiteral.getLiteral();
          var name = eEnumLiteral.getName();
          if (literal.matches("2\\.\\d\\d") && name.matches("EMF\\d\\d\\d"))
          {
            lastEMF = eEnumLiteral;
          }
          else if (literal.matches("2\\d\\d\\d-\\d\\d") && name.matches("Eclipse_\\d\\d\\d\\d_\\d\\d"))
          {
            lastEclipse = eEnumLiteral;
          }
          else if (literal.matches("[0-9.]+") && name.matches("JDK\\d\\d\\d"))
          {
            lastJDK = eEnumLiteral;
          }
        }
      }

      if (!lastJDK.getLiteral().equals(jdkVersion))
      {
        addEEnumLiteral(lastJDK, "JDK" + jdkVersion.replace(".", ""), jdkVersion);
      }

      if (!lastEMF.getLiteral().equals(emfVersion))
      {
        addEEnumLiteral(lastEMF, "EMF" + emfVersion.replace(".", ""), emfVersion);
      }

      if (!lastEclipse.getLiteral().equals(simrelVersion))
      {
        addEEnumLiteral(lastEclipse, "Eclipse_" + simrelVersion.replace("-", "_"), simrelVersion);
      }

      genModelEcore.save(Map.of());

      Resource genModelGenModel = resourceSet.getResource(genModelEcoreURI.trimSegments(1).appendSegment("GenModel.genmodel"), true);
      EcoreUtil.resolveAll(genModelGenModel);
      ((GenModel)genModelGenModel.getContents().get(0)).reconcile();
      genModelGenModel.save(Map.of());
    }
    else if (fileName.equals("EMF.setup"))
    {
      var setup = getContent(file);
      var matcher = Pattern.compile("[\r\n ]+<repositoryList[\r\n ]+name=\"(?<version>[0-9-]+)\">.*?</repositoryList>", Pattern.DOTALL).matcher(setup);
      if (!matcher.find())
      {
        throw new IllegalStateException("Expecting a repository list with a simrel version as the name in the setup.");
      }

      var version = matcher.group("version");
      var section = matcher.group();
      if (!version.equals(simrelVersion))
      {
        // Copy the section, replacing the old simrel version with the current one.
        var modifiedContent = matcher.replaceFirst(section.replace(version, simrelVersion) + section);
        contents.put(file, modifiedContent);
      }
    }
  }

  private void update() throws IOException
  {
    Files.walkFileTree(root, new SimpleFileVisitor<Path>()
      {
        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
        {
          visit(file);
          return super.visitFile(file, attrs);
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException
        {
          String fileName = dir.getFileName().toString();
          if ("target".equals(fileName) //
              || "sanity-check".equals(fileName) //
              || (fileName.startsWith(".") && !".settings".equals(fileName) && !".classpath".equals(fileName) && !".github".equals(fileName)) //
              || dir.endsWith("META-INF/maven"))
          {
            return FileVisitResult.SKIP_SUBTREE;
          }
          Path relativePath = root.relativize(dir);
          if (relativePath.startsWith("updates"))
          {
            return FileVisitResult.SKIP_SUBTREE;
          }
          return super.preVisitDirectory(dir, attrs);
        }
      });

    saveModifiedContents();
  }

  private void saveModifiedContents() throws IOException
  {
    for (var entry : contents.entrySet())
    {
      String value = entry.getValue();
      Path file = entry.getKey();
      if (value == null)
      {
        Files.delete(file);
      }
      else
      {
        Files.writeString(file, value);
      }
    }
  }

  private static String getSimRelVersion(String version)
  {
    var matcher = PLATFORM_VERSION_PATTERN.matcher(version);
    if (matcher.matches())
    {
      var offset = Integer.valueOf(matcher.group(1)) - 11;
      var year = 2019 + offset / 4;
      String quarter;
      switch (offset % 4)
      {
        case 0:
        {
          quarter = "03";
          break;
        }
        case 1:
        {
          quarter = "06";
          break;
        }
        case 2:
        {
          quarter = "09";
          break;
        }
        default:
        {
          quarter = "12";
          break;
        }
      }
      return year + "-" + quarter;
    }
    else
    {
      throw new RuntimeException("Bad version");
    }
  }

  private static String getPreviousSimrelVersion(String version)
  {
    var matcher = Pattern.compile("(?<year>\\d+)-(?<quarter>\\d+)").matcher(version);
    matcher.matches();
    String year = matcher.group("year");
    String quarter = matcher.group("quarter");
    switch (quarter)
    {
      case "03":
      {
        return (Integer.parseInt(year) - 1) + "-" + "12";
      }
      case "06":
      {
        return year + "-" + "03";
      }
      case "09":
      {
        return year + "-" + "06";
      }
      default:
      case "12":
      {
        return year + "-" + "09";
      }
    }
  }
}
