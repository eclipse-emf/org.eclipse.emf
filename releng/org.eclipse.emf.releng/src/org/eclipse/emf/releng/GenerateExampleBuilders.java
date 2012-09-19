/*
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.releng;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateExampleBuilders
{
  private static final String TOKEN_EXAMPLE_PROJECT = "#EXAMPLE_PROJECT#";

  private static final String TOKEN_INSTALLER_PROJECT = "#INSTALLER_PROJECT#";

  private static final String TOKEN_RELEVANT_RESOURCES = "#RELEVANT_RESOURCES#";

  private static final String TOKEN_REFRESH_RESOURCES = "#REFRESH_RESOURCES#";

  private static final String NL = System.getProperty("line.separator");

  public static void main(String[] args) throws Exception
  {
    if (true)
    {
      System.err.println("##");
      return;
    }
    File installerPoject = new File(args[0]).getCanonicalFile();
    File pluginXML = new File(installerPoject, "plugin.xml");
    System.out.println("Analyzing " + pluginXML);

    SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
    XMLHandler handler = new XMLHandler(installerPoject);
    InputStream in = new FileInputStream(pluginXML);

    try
    {
      parser.parse(in, handler);
    }
    finally
    {
      in.close();
    }
  }

  private static void processProjectDescriptor(File exampleProject, File installerPoject, String targetFolder)
      throws Exception
  {
    String exampleProjectName = exampleProject.getName();
    String targetPath = installerPoject.getName() + "/" + targetFolder;

    StringBuilder relevantResources = new StringBuilder();
    relevantResources.append(getItemPath(targetPath, 2));

    for (File file : exampleProject.listFiles())
    {
      String name = file.getName();
      int type = file.isDirectory() ? 2 : 1;

      if (!"bin".equals(name))
      {
        String path = exampleProjectName + "/" + name;
        relevantResources.append(getItemPath(path, type));
      }
    }

    Map<String, String> substitutes = new HashMap<String, String>();
    addSubtitute(substitutes, TOKEN_EXAMPLE_PROJECT, exampleProject.getName());
    addSubtitute(substitutes, TOKEN_INSTALLER_PROJECT, installerPoject.getName());
    addSubtitute(substitutes, TOKEN_RELEVANT_RESOURCES, relevantResources.toString());
    addSubtitute(substitutes, TOKEN_REFRESH_RESOURCES, getItemPath(new File(targetPath).getParent(), 2));

    copy(exampleProject, substitutes, "copyExample.ant", ".externalToolBuilders/copyExample.ant");
    copy(exampleProject, substitutes, "template.launch", ".externalToolBuilders/" + exampleProjectName + ".launch");

    updateProjectDescription(exampleProject, substitutes);
  }

  private static void copy(File targetProject, Map<String, String> substitutes, String template, String targetPath)
      throws IOException
  {
    File source = getTemplate(targetProject.getParentFile(), template);
    String content = substitute(readFile(source), substitutes);

    File target = new File(targetProject, targetPath);
    System.out.println("      Generating " + target.getCanonicalPath());
    writeFile(target, content);
  }

  private static void updateProjectDescription(File targetProject, Map<String, String> substitutes) throws IOException
  {
    File snippetTemplate = getTemplate(targetProject.getParentFile(), "template.project");
    String snippet = substitute(readFile(snippetTemplate), substitutes);

    File descriptionFile = new File(targetProject, ".project");
    String description = readFile(descriptionFile);
    System.out.println("      Updating " + descriptionFile.getCanonicalPath());

    if (description.indexOf(snippet) != -1)
    {
      // Nothing to update
      return;
    }

    String newDescription = description;

    Pattern pattern = Pattern.compile(
        "(\\s*<buildCommand>.*?</buildCommand>)*(\\s*<buildCommand>.*?<value>&lt;project&gt;/\\.externalToolBuilders/"
            + targetProject.getName().replace(".", "\\.") + "\\.launch</value>.*?</buildCommand>)", Pattern.DOTALL);
    Matcher matcher = pattern.matcher(description);
    if (matcher.find())
    {
      String remove = matcher.group(2);
      newDescription = newDescription.replace(remove, "");
    }

    newDescription = newDescription.replaceFirst("[ \\t]*</buildSpec>", snippet + "\t</buildSpec>");
    if (!newDescription.equals(description))
    {
      writeFile(descriptionFile, newDescription);
    }
  }

  private static String readFile(File file) throws IOException
  {
    StringBuilder result = new StringBuilder();
    FileReader in = null;

    try
    {
      in = new FileReader(file);
      BufferedReader reader = new BufferedReader(in);

      String line;
      while ((line = reader.readLine()) != null)
      {
        result.append(line);
        result.append(NL);
      }

      return result.toString();
    }
    finally
    {
      if (in != null)
      {
        in.close();
      }
    }
  }

  private static void writeFile(File file, String content) throws IOException
  {
    FileWriter out = null;

    try
    {
      out = new FileWriter(file);
      @SuppressWarnings("resource")
      BufferedWriter writer = new BufferedWriter(out);

      writer.write(content);
      writer.flush();
    }
    finally
    {
      if (out != null)
      {
        out.close();
      }
    }
  }

  private static String getItemPath(String resource, int type)
  {
    return "&lt;item path=&quot;/" + resource.replace('\\', '/') + "&quot; type=&quot;" + type
        + "&quot;/&gt;&#13;&#10;";
  }

  private static File getTemplate(File root, String template)
  {
    return new File(root, "org.eclipse.emf.releng/exampleBuilderTemplates/" + template);
  }

  private static void addSubtitute(Map<String, String> substitutes, String token, String substitute)
  {
    System.out.println("      " + token + " = " + substitute);
    substitutes.put(token, substitute);
  }

  private static String substitute(String content, Map<String, String> substitutes)
  {
    for (Entry<String, String> entry : substitutes.entrySet())
    {
      content = content.replace(entry.getKey(), entry.getValue());
    }

    return content;
  }

  private static class XMLHandler extends DefaultHandler
  {
    private File installerPoject;

    public XMLHandler(File installerPoject)
    {
      this.installerPoject = installerPoject;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
      if ("projectDescriptor".equalsIgnoreCase(qName))
      {
        try
        {
          String name = attributes.getValue("name");
          String contentURI = attributes.getValue("contentURI");
          File exampleProject = new File(installerPoject, "../" + name).getCanonicalFile();

          System.out.println("   Processing " + name + " --> " + contentURI);
          processProjectDescriptor(exampleProject, installerPoject, contentURI);
        }
        catch (Exception ex)
        {
          ex.printStackTrace();
        }
      }
    }
  }
}
