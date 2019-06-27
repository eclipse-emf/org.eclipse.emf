/**
 * Copyright (c) 2019 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package pommod;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Calendar;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Developer;
import org.apache.maven.model.License;
import org.apache.maven.model.Model;
import org.apache.maven.model.Scm;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.eclipse.jdt.annotation.NonNull;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;


public class POMEnhancer
{
  private static final String YEAR;

  static
  {
    Calendar now = Calendar.getInstance();
    int year = now.get(Calendar.YEAR);
    YEAR = String.valueOf(year);
  }

  private File dir;

  private boolean dryRun;

  public static void main(String[] args)
  {
    POMEnhancer instance = new POMEnhancer();

    CmdLineParser parser = new CmdLineParser(instance);
    try
    {
      parser.parseArgument(args);
      instance.run();
    }
    catch (CmdLineException | IOException e)
    {
      // handling of wrong arguments
      System.err.println(e.getMessage());
      parser.printUsage(System.err);
    }
  }

  @Option(name = "-dir", usage = "Sets the base directory to scan for POM files", required = true)
  public void setDir(@NonNull File dir)
  {
    this.dir = dir;
  }

  @Option(name = "-dryRun", usage = "When set, files are not modified and result is dumped to sysout")
  public void setDryRun(boolean dryRun)
  {
    this.dryRun = dryRun;
  }

  protected void run() throws IOException
  {
    System.out.println("Making POMs compliant for Maven Central.");
    System.out.println("Processing *.pom files in " + dir.getAbsolutePath() + "...");
    Files.find(dir.toPath(), 20, (path, basicFileAttributes) -> path.toFile().getPath().endsWith(".pom")).forEach(path -> enhancePOMFile(path));
    System.out.println("Done.");
  }

  private void enhancePOMFile(Path path)
  {
    boolean modified = false;
    Model model = load(path.toFile());

    if (model.getName() == null)
    {
      model.setName(model.getArtifactId());
      modified = true;
    }

    if (model.getUrl() == null)
    {
      model.setUrl("https://www.eclipse.org/emf");
      modified = true;
    }

    if (model.getLicenses().isEmpty())
    {
      License license = new License();
      license.setName("The Eclipse Public License Version 2.0");
      license.setUrl("https://www.eclipse.org/legal/epl-v20.html");
      license.setDistribution("repo");
      model.addLicense(license);
      modified = true;
    }

    if (model.getScm() == null)
    {
      Scm scm = new Scm();
      scm.setUrl("https://git.eclipse.org/c/emf/org.eclipse.emf.git/");
      scm.setConnection("git://git.eclipse.org/gitroot/emf/org.eclipse.emf.git/");
      model.setScm(scm);
      modified = true;
    }

    if (model.getDevelopers().isEmpty())
    {
      Developer developer = new Developer();
      developer.setId("eclipse");
      developer.setName("Eclipse.org");
      developer.setEmail("info@eclipse.org");
      model.addDeveloper(developer);
      modified = true;
    }

    // fix ANTLR Runtime Dependency, see Bug#536882
    // version must be strictly 3.2.0 for Xtext
    for (Dependency dependency : model.getDependencies())
    {
      if ("antlr-runtime".equals(dependency.getArtifactId()))
      {
        if (!"[3.2.0, 3.2.1)".equals(dependency.getVersion()))
        {
          dependency.setVersion("[3.2.0, 3.2.1)");
          modified = true;
        }
      }
    }

    String groupID = model.getGroupId();
    String artifactID = model.getArtifactId();
    String version = model.getVersion();
    String description = model.getDescription();

    boolean exists = exists(groupID, artifactID, version);

    if (modified || dryRun)
    {
      if (modified)
      {
        System.out.println("--------------- Modified: " + path + " exists: " + exists);
      }
      else
      {
        System.out.println("--------------- Unmodified: " + path + " exists " + exists);
      }

      save(model, path.toFile());

      if (!exists)
      {
        String pomFileName = path.getFileName().toString();
        Path publishPath = path.getParent().resolve(pomFileName.substring(0, pomFileName.length() - 3) + "publish");

        String javadocWindowTitle = getJavadocWindowTitle(description, version);
        String javadocFooter = getJavadocFooter(description);
        savePublish(publishPath.toFile(), javadocWindowTitle, javadocFooter);
      }

      System.out.println();
    }
  }

  protected String getJavadocWindowTitle(String string, String version)
  {
    version = version.substring(0, version.lastIndexOf('.'));
    if (string.startsWith("EMF"))
    {
      return "Eclipse Modeling Framework" + string.substring(3) + " " + version + " API Specification";
    }
    else if (string.startsWith("XSD"))
    {
      return "Eclipse XML Schema Definition" + string.substring(3) + " " + version + " API Specification";
    }
    else
    {
      return string + " " + version + " API Specification";
    }
  }

  protected String getJavadocFooter(String string)
  {
    String product = string.startsWith("XSD") ? "MDT.XSD" : "EMF";
    return "<font size=\"-1\">Copyright &copy; " + YEAR
        + ". Licensed under the <a href=\"https://www.eclipse.org/legal/epl-2.0/\">Eclipse Public License v2.0</a>. All rights reserved.<br/><a href=\"https://bugs.eclipse.org/bugs/enter_bug.cgi?product="
        + product + "\">Submit a bug or feature</a><br/></font>";
  }

  protected Model load(File file)
  {
    MavenXpp3Reader reader = new MavenXpp3Reader();
    try
    {
      return reader.read(new FileReader(file));
    }
    catch (IOException | XmlPullParserException e)
    {
      throw new RuntimeException(e);
    }
  }

  protected void save(Model model, File target)
  {
    MavenXpp3Writer writer = new MavenXpp3Writer();
    try
    {
      if (dryRun)
      {
        writer.write(System.out, model);
      }
      else
      {
        writer.write(new FileWriter(target), model);
      }
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }
  
  protected void savePublish(File target, String javadocWindowTitle, String javadocFooter)
  {
    try
    {
      if (dryRun)
      {
        System.out.println(javadocWindowTitle);
        System.out.println(javadocFooter);
      }
      else
      {
        FileOutputStream fileOutputStream = new FileOutputStream(target);
        PrintStream out = new PrintStream(fileOutputStream, false, "UTF-8");
        out.println(javadocWindowTitle);
        out.println(javadocFooter);
        fileOutputStream.close();
      }
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }

  protected boolean exists(String groupID, String artifactID, String version)
  {
    try
    {
      URL url = new URL("https://oss.sonatype.org/service/local/lucene/search?g=" + groupID + "&a=" + artifactID + "&v=" + version + "&repositoryId=releases");
      InputStream result = url.openStream();
      return scanQueryResult(result);
    }
    catch (IOException ex)
    {
      return false;
    }
  }

  protected boolean scanQueryResult(InputStream in) throws IOException
  {
    Reader reader = null;
    BufferedReader bufferedReader = null;

    try
    {
      reader = new InputStreamReader(in, "UTF-8");
      bufferedReader = new BufferedReader(reader);

      String line;
      while ((line = bufferedReader.readLine()) != null)
      {
        // System.out.println(line);
        if (line.contains("<totalCount>"))
        {
          return !line.contains("<totalCount>0</totalCount>");
        }
      }
    }
    finally
    {
      in.close();
      if (bufferedReader != null)
      {
        bufferedReader.close();
      }
    }

    return false;
  }
}
