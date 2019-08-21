/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.jet;


import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.WeakHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.CodeGenPlugin;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class JETNature implements IJETNature
{
  /**
   * Get a JETNature that corresponds to the supplied project.
   * @return JETNature
   * @param project IProject
   */
  public static JETNature getRuntime(IProject project)
  {
    try
    {
      JETNature a = (JETNature)project.getNature(IJETNature.NATURE_ID);
      return a;
    }
    catch (CoreException e)
    {
      return null;
    }
  }

  protected static final String BUILDER = "JETBuilder";

  protected static final String DEFAULT_TEMPLATE_CONTAINER_NAME = "templates";

  protected static final String JET_NATURE_PROPERTIES_FILE = ".jetproperties";

  protected static final String TEMPLATE_CONTAINER_NODE = "template-container";

  protected static final String SOURCE_CONTAINER_NODE = "source-container";

  protected static final String JET_SETTINGS_NODE = "jet-settings";

  private static final Map<JETNature, Void> JET_NATURES = new WeakHashMap<JETNature, Void>();

  private static final IWorkspace WORKSPACE;

  static
  {
    IWorkspace workspace = null;
    try
    {
      workspace = ResourcesPlugin.getWorkspace();
      workspace.addResourceChangeListener(new IResourceChangeListener()
        {
          public void resourceChanged(IResourceChangeEvent event)
          {
            targetPlatformBundleRedirections = null;
            for (JETNature jetNature : JET_NATURES.keySet())
            {
              jetNature.jetJavaSourceContainer = null;
              jetNature.jetTemplateContainers = null;
              jetNature.jetTemplateSourceContainers = null;
            }
          }
        }, IResourceChangeEvent.PRE_BUILD);
    }
    catch (IllegalStateException exception)
    {
      // Ignore if the workspace is closed.
    }

    WORKSPACE = workspace;
  }

  private static Map<String, URI> targetPlatformBundleRedirections;

  protected IProject jetProject;

  protected List<Object> jetTemplateContainers;

  protected List<Object> jetTemplateSourceContainers;

  protected IContainer jetJavaSourceContainer;

  /**
   * Constructor for JETNature.
   */
  public JETNature()
  {
    super();
    JET_NATURES.put(this, null);
  }

  public List<Object> getTemplateContainers()
  {
    if (jetTemplateContainers == null)
    {
      jetTemplateContainers = getTemplateContainersFromFile();
    }
    return jetTemplateContainers;
  }

  public List<Object> getTemplateSourceContainers()
  {
    if (jetTemplateSourceContainers == null)
    {
      jetTemplateSourceContainers = getTemplateSourceContainersFromFile();
    }
    return jetTemplateSourceContainers;
  }

  public IContainer getJavaSourceContainer()
  {
    if (jetJavaSourceContainer == null)
    {
      jetJavaSourceContainer = getJavaSourceContainerFromFile();
    }
    return jetJavaSourceContainer;
  }

  public void setTemplateContainers(List<Object> templateContainers)
  {
    setTemplateContainers(templateContainers, templateContainers);
  }

  public void setTemplateContainers(List<Object> templateContainers, List<Object> templateSourceContainers)
  {
    jetTemplateContainers = templateContainers;
    jetTemplateSourceContainers = templateSourceContainers;
    try
    {
      setTemplateContainersToFile(templateContainers, templateSourceContainers);
    }
    catch (CoreException e)
    {
      CodeGenPlugin.write(e);
    }
  }

  public void setJavaSourceContainer(IContainer javaSourceContainer)
  {
    jetJavaSourceContainer = javaSourceContainer;
    try
    {
      setJavaSourceContainerToFile(javaSourceContainer);
    }
    catch (CoreException e)
    {
      CodeGenPlugin.write(e);
    }
  }

  public void configure() throws CoreException
  {
    configure(new NullProgressMonitor());
  }

  public void configure(IProgressMonitor monitor) throws CoreException
  {
    setDefaults(monitor);

    // Add JETBuilder
    //
    addToFrontOfBuildSpec(CodeGenPlugin.INSTANCE.getSymbolicName() + "." + BUILDER);
  }

  /**
   * Sets the containers to their defaults.
   */
  public void setDefaults(IProgressMonitor monitor) throws CoreException
  {
    initTemplateContainer(monitor);
    initJavaSourceContainer(monitor);

    // Create .jetproperties file
    //
    try
    {
      createDefaultJETSettingsFile(getTemplateContainers(), getJavaSourceContainer());
    }
    catch (IOException e)
    {
      CodeGenPlugin.write(e);
    }
  }

  public void deconfigure() throws CoreException
  {
    // Do nothing
  }

  public IProject getProject()
  {
    return jetProject;
  }

  public void setProject(IProject project)
  {
    jetProject = project;
  }

  /**
   * Initializes the template container.
   */
  protected void initTemplateContainer(IProgressMonitor monitor) throws CoreException
  {
    IContainer templateFolder = getContainer(getProject(), DEFAULT_TEMPLATE_CONTAINER_NAME);

    if (templateFolder instanceof IFolder && !templateFolder.exists())
    {
      ((IFolder)templateFolder).create(true, true, monitor);
    }

    jetTemplateContainers = new ArrayList<Object>();
    jetTemplateContainers.add(templateFolder);
    jetTemplateSourceContainers = new ArrayList<Object>();
    jetTemplateSourceContainers.add(templateFolder);
  }

  /**
   * Initializes the Java Source Container
   */
  protected void initJavaSourceContainer(IProgressMonitor monitor) throws CoreException
  {
    IProject project = getProject();
    IContainer sourceFolder = getContainer(project, getDefaultSourcePath(project));
    if (sourceFolder instanceof IFolder && !sourceFolder.exists())
    {
      ((IFolder)sourceFolder).create(true, true, monitor);
    }

    jetJavaSourceContainer = sourceFolder;
  }

  /**
   * Returns the project's root directory
   */
  protected IPath getDefaultSourcePath()
  {
    IPath path = new Path("");
    return path;
  }

  /**
   * Returns the project's root directory
   *
   * @since 2.19
   */
  protected IPath getDefaultSourcePath(IProject project)
  {
    IJavaProject javaProject = JavaCore.create(project);
    if (javaProject != null)
    {
      try
      {
        for (IPackageFragmentRoot packageFragmentRoot : javaProject.getPackageFragmentRoots())
        {
          if (packageFragmentRoot.getKind() == IPackageFragmentRoot.K_SOURCE)
          {
            IResource resource = packageFragmentRoot.getResource();
            return resource.getProjectRelativePath();
          }
        }
      }
      catch (Exception ex)
      {
        // Ignore and use the default.
      }
    }
    return getDefaultSourcePath();
  }

  /**
   * Adds a builder to the build specification for the given project.
   */
  protected void addToFrontOfBuildSpec(String builderID) throws CoreException
  {
    IProjectDescription description = getProject().getDescription();
    ICommand[] commands = description.getBuildSpec();
    boolean found = false;
    for (int i = 0; i < commands.length; ++i)
    {
      if (commands[i].getBuilderName().equals(builderID))
      {
        found = true;
        break;
      }
    }
    if (!found)
    {
      ICommand command = description.newCommand();
      command.setBuilderName(builderID);
      ICommand[] newCommands = new ICommand [commands.length + 1];
      System.arraycopy(commands, 0, newCommands, 1, commands.length);
      newCommands[0] = command;
      description.setBuildSpec(newCommands);
      getProject().setDescription(description, null);
    }
  }

  /**
   * Returns the template path from the .jetproperties file.
   */
  public List<Object> getTemplateContainersFromFile()
  {
    List<Object> result = Collections.emptyList();

    try
    {
      Document document = parseJETSettings();
      if (document != null)
      {
        result = getContainerValues(document.getDocumentElement(), TEMPLATE_CONTAINER_NODE, false);
      }
      else
      {
        setDefaults(new NullProgressMonitor());
        result = getTemplateContainers();
      }
    }
    catch (Exception e)
    {
      try
      {
        setDefaults(new NullProgressMonitor());
        result = getTemplateContainers();
      }
      catch (Exception ex)
      {
        CodeGenPlugin.write(ex);
      }
    }

    return result;
  }

  /**
   * Returns the template source path from the .jetproperties file.
   */
  protected List<Object> getTemplateSourceContainersFromFile()
  {
    List<Object> result = Collections.emptyList();

    try
    {
      Document document = parseJETSettings();
      if (document != null)
      {
        result = getContainerValues(document.getDocumentElement(), TEMPLATE_CONTAINER_NODE, true);
      }
      else
      {
        setDefaults(new NullProgressMonitor());
        result = getTemplateContainers();
      }
    }
    catch (Exception e)
    {
      try
      {
        setDefaults(new NullProgressMonitor());
        result = getTemplateContainers();
      }
      catch (Exception ex)
      {
        CodeGenPlugin.write(ex);
      }
    }

    return result;
  }

  /**
   * Returns the template file from the .jetproperties file
   */
  public IContainer getJavaSourceContainerFromFile()
  {
    IContainer result = null;

    try
    {
      Document document = parseJETSettings();
      if (document != null)
      {
        result = getContainerValue(document.getDocumentElement(), SOURCE_CONTAINER_NODE);

      }
      else
      {
        setDefaults(new NullProgressMonitor());
        result = getJavaSourceContainer();
      }
    }
    catch (Exception e)
    {
      try
      {
        setDefaults(new NullProgressMonitor());
        result = getJavaSourceContainer();
      }
      catch (Exception ex)
      {
        CodeGenPlugin.write(ex);
      }
    }

    return result;
  }

  /**
   * Parse the JET settings file for the  root element.
   */
  protected Document parseJETSettings() throws ParserConfigurationException, SAXException, IOException, CoreException
  {
    Document document = null;
    StringReader reader = readJETSettingFile();
    if (reader != null)
    {
      document = fromInputSource(new InputSource(reader));
      if (!document.getDocumentElement().getNodeName().equalsIgnoreCase(JET_SETTINGS_NODE))
      {
        CodeGenPlugin.write(new IOException(CodeGenPlugin.getPlugin().getString("_UI_MalformedJETPropertiesFile_exception")));
      }
      reader.close();
    }
    return document;
  }

  /**
   * Open the JET Settings file and return a StringReader on the contents
   */
  protected StringReader readJETSettingFile() throws CoreException, IOException
  {
    StringReader reader = null;

    IFile jetSettingsFile = getProject().getFile(JET_NATURE_PROPERTIES_FILE);
    if (jetSettingsFile.exists())
    {
      InputStream input = jetSettingsFile.getContents(true);

      String jetSettings = new String(readContentsAsBytes(input));
      reader = new StringReader(jetSettings);
    }
    return reader;
  }

  /**
   * Returns the containers defined from the base Element passed in.
   */
  protected List<Object> getContainerValues(Element element, String localName)
  {
    return getContainerValues(element, localName, false);
  }

  /**
   * Returns the containers defined from the base Element passed in with entries starting with @ filtered out.
   */
  protected List<Object> getContainerValues(Element element, String localName, boolean filter)
  {
    List<Object> result = new ArrayList<Object>();
    Element childElement = getChildWithLocalName(element, localName);
    for (Node node = childElement.getFirstChild(); node != null; node = node.getNextSibling())
    {
      if (node.getNodeType() == Node.TEXT_NODE)
      {
        result = getContainers(getProject(), node.getNodeValue(), filter);
        break;
      }
    }

    if (result.isEmpty())
    {
      result.add(getProject());
    }

    return result;
  }

  /**
   * Returns the container defined from the base Element passed in.
   */
  protected IContainer getContainerValue(Element element, String localName)
  {
    IContainer result = null;
    Element childElement = getChildWithLocalName(element, localName);
    for (Node node = childElement.getFirstChild(); node != null; node = node.getNextSibling())
    {
      if (node.getNodeType() == Node.TEXT_NODE)
      {
        result = getContainer(getProject(), node.getNodeValue());
        break;
      }
    }

    if (result == null)
    {
      result = getProject();
    }

    return result;
  }

  protected void setContainerValues(List<Object> containers, Element element, String localName)
  {
    setContainerValues(containers, containers, element, localName);
  }

  /**
   * Sets the template container locations in the settings file
   */
  protected void setContainerValues(List<Object> containers, List<Object> sourceContainers, Element element, String localName)
  {
    Element childElement = getChildWithLocalName(element, localName);
    Text text = null;
    for (Node node = childElement.getFirstChild(); node != null; node = node.getNextSibling())
    {
      if (node.getNodeType() == Node.TEXT_NODE)
      {
        text = (Text)node;
        break;
      }
    }

    if (text == null)
    {
      text = element.getOwnerDocument().createTextNode(getContainers(jetProject, containers, sourceContainers));
      childElement.appendChild(text);
    }
    else
    {
      text.setNodeValue(getContainers(jetProject, containers, sourceContainers));
    }
  }

  /**
   * Sets the template container location in the settings file
   */
  protected void setContainerValue(IContainer container, Element element, String localName)
  {
    Element childElement = getChildWithLocalName(element, localName);
    Text text = null;
    for (Node node = childElement.getFirstChild(); node != null; node = node.getNextSibling())
    {
      if (node.getNodeType() == Node.TEXT_NODE)
      {
        text = (Text)node;
        break;
      }
    }

    if (text == null)
    {
      text = element.getOwnerDocument().createTextNode(container.getProjectRelativePath().toString());
      childElement.appendChild(text);
    }
    else
    {
      text.setNodeValue(container.getProjectRelativePath().toString());
    }
  }

  /**
   * Returns the child with the given local name.
   */
  protected Element getChildWithLocalName(Element element, String localName)
  {
    for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling())
    {
      if (child.getNodeType() == Node.ELEMENT_NODE)
      {
        Element childElement = (Element)child;
        if (childElement.getLocalName().equals(localName))
        {
          return childElement;
        }
      }
    }

    return null;
  }

  /**
   * Reads an input stream and converts it to bytes
   */
  public static byte[] readContentsAsBytes(InputStream input) throws IOException
  {
    BufferedInputStream bufferedInputStream = null;
    try
    {
      final int BUF_SIZE = 8192;
      byte[] buf = new byte [BUF_SIZE];
      int read;
      int totalRead = 0;
      bufferedInputStream = new BufferedInputStream(input);
      while (totalRead < BUF_SIZE && (read = bufferedInputStream.read(buf, totalRead, BUF_SIZE - totalRead)) != -1)
      {
        totalRead += read;
      }
      if (totalRead < BUF_SIZE)
      {
        byte[] result = new byte [totalRead];
        System.arraycopy(buf, 0, result, 0, totalRead);
        return result;
      }
      ByteArrayOutputStream out = new ByteArrayOutputStream(BUF_SIZE * 2);
      out.write(buf);
      while ((read = bufferedInputStream.read(buf, 0, BUF_SIZE)) != -1)
      {
        out.write(buf, 0, read);
      }
      return out.toByteArray();
    }
    finally
    {
      try
      {
        if (bufferedInputStream != null)
        {
          bufferedInputStream.close();
        }
      }
      catch (IOException e)
      {
        CodeGenPlugin.write(e);
      }
    }
  }

  public void setTemplateContainersToFile(List<Object> templateContainers) throws CoreException
  {
    setTemplateContainersToFile(templateContainers, templateContainers);
  }

  /**
   * Writes the Template Container Location to a file
   */
  public void setTemplateContainersToFile(List<Object> templateContainers, List<Object> templateSourceContainers) throws CoreException
  {
    Document document;
    try
    {
      try
      {
        document = parseJETSettings();
        if (document != null)
        {
          setContainerValues(templateContainers, templateSourceContainers, document.getDocumentElement(), TEMPLATE_CONTAINER_NODE);
          commitXML(document);
        }
        else
        {
          initJavaSourceContainer(new NullProgressMonitor());
          createDefaultJETSettingsFile(templateContainers, getJavaSourceContainer());
        }
      }
      catch (Exception e)
      {
        initJavaSourceContainer(new NullProgressMonitor());
        createDefaultJETSettingsFile(templateContainers, templateSourceContainers, getJavaSourceContainer());
      }
    }
    catch (Exception e)
    {
      CodeGenPlugin.write(e);
    }
  }

  /**
   * Writes the Java Source Container Location to a file
   */
  public void setJavaSourceContainerToFile(IContainer sourceContainer) throws CoreException
  {
    Document document;
    try
    {
      try
      {
        document = parseJETSettings();
        if (document != null)
        {
          setContainerValue(sourceContainer, document.getDocumentElement(), SOURCE_CONTAINER_NODE);
          commitXML(document);
        }
        else
        {
          initTemplateContainer(new NullProgressMonitor());
          createDefaultJETSettingsFile(getTemplateContainers(), sourceContainer);
        }
      }
      catch (Exception e)
      {
        initTemplateContainer(new NullProgressMonitor());
        createDefaultJETSettingsFile(getTemplateContainers(), sourceContainer);
      }
    }
    catch (Exception e)
    {
      CodeGenPlugin.write(e);
    }
  }

  protected void createDefaultJETSettingsFile(List<Object> templateContainers, IContainer sourceContainer) throws CoreException, IOException
  {
    createDefaultJETSettingsFile(templateContainers, templateContainers, sourceContainer);
  }

  /**
   * Writes the default file 
   */
  protected void createDefaultJETSettingsFile(List<Object> templateContainers, List<Object> templateSourceContainers, IContainer sourceContainer) throws CoreException, IOException
  {
    StringWriter writer = new StringWriter();
    // Create a default .jetsettings file file

    writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    writer.write("\n");
    writer.write("<" + JET_SETTINGS_NODE + ">");
    writer.write("\n");
    writer.write("\t<" + TEMPLATE_CONTAINER_NODE + ">" + getContainers(jetProject, templateContainers, templateSourceContainers) + "</" + TEMPLATE_CONTAINER_NODE + ">");
    writer.write("\t<" + SOURCE_CONTAINER_NODE + ">" + sourceContainer.getProjectRelativePath().toString() + "</" + SOURCE_CONTAINER_NODE + ">");
    writer.write("\n");
    writer.write("</" + JET_SETTINGS_NODE + ">");
    writer.write("\n");

    IFile jetSettingsFile = getProject().getFile(JET_NATURE_PROPERTIES_FILE);
    InputStream sourceStream = new ByteArrayInputStream(writer.toString().getBytes("UTF-8"));
    if (jetSettingsFile.exists())
    {
      jetSettingsFile.setContents(sourceStream, true, true, null);
    }
    else
    {
      jetSettingsFile.create(sourceStream, true, null);
    }

    sourceStream.close();
  }

  protected void commitXML(Document document) throws CoreException, ClassNotFoundException, IOException
  {
    String documentXML = toString(document);
    IFile jetSettingsFile = getProject().getFile(JET_NATURE_PROPERTIES_FILE);
    InputStream sourceStream = new ByteArrayInputStream(documentXML.getBytes("UTF-8"));
    if (jetSettingsFile.exists())
    {
      jetSettingsFile.setContents(sourceStream, true, true, null);
    }
    else
    {
      jetSettingsFile.create(sourceStream, true, null);
    }

    sourceStream.close();
  }

  public static IContainer getContainer(IProject project, IPath path)
  {
    IContainer result = project;
    if (!path.isEmpty())
    {
      if (path.getDevice() != null)
      {
        result = null;
      }
      else if (path.isAbsolute())
      {
        IWorkspaceRoot root = project.getWorkspace().getRoot();
        if (path.segmentCount() == 1)
        {
          result = root.getProject(path.segment(0));
        }
        else
        {
          result = root.getFolder(path);
        }
      }
      else
      {
        result = project.getFolder(path);
      }
    }
    return result;
  }

  public static IContainer getContainer(IProject project, String path)
  {
    return getContainer(project, new Path(path));
  }

  public static List<Object> getContainers(IProject project, String paths)
  {
    return getContainers(project, paths, false);
  }

  public static List<Object> getContainers(IProject project, String paths, boolean filter)
  {
    List<Object> result = new ArrayList<Object>();
    for (StringTokenizer stringTokenizer = new StringTokenizer(paths, " ;"); stringTokenizer.hasMoreTokens();)
    {
      String path = stringTokenizer.nextToken();
      if (path.startsWith("@"))
      {
        if (filter)
        {
          continue;
        }
        path = path.substring(1);
      }
      IContainer container = getContainer(project, new Path(path));
      if (container == null)
      {
        URI uri = URI.createURI(path);
        if (!uri.isRelative())
        {
          result.add(uri);
        }
      }
      else
      {
        result.add(container);
      }
    }
    return result;
  }

  public static String getContainers(IProject project, List<Object> containers)
  {
    return getContainers(project, containers, containers);
  }

  public static String getContainers(IProject project, List<Object> containers, List<Object> sourceContainers)
  {
    StringBuilder result = new StringBuilder();
    for (Object container : containers)
    {
      if (result.length() != 0)
      {
        result.append(";");
      }
      if (!sourceContainers.contains(container))
      {
        result.append("@");
      }
      if (container instanceof IContainer)
      {
        result.append(getContainer(project, (IContainer)container));
      }
      else if (container instanceof URI)
      {
        result.append(container);
      }
    }
    return result.toString();
  }

  public static String getContainer(IProject project, IContainer container)
  {
    return (project == container.getProject() ? container.getProjectRelativePath() : container.getFullPath()).toString();
  }

  /**
   * @sine 2.14
   */
  public static URI resolve(URI uri)
  {
    // If the URI is a platform resource denoting something that might exist in the workspace...
    //
    if (WORKSPACE != null && uri.isPlatformResource() && uri.segmentCount() > 1)
    {
      // If the resource doesn't really exist in the workspace, or is inaccessible.
      //
      IResource member = WORKSPACE.getRoot().findMember(new Path(uri.toPlatformString(true)));
      if (member == null || !member.isAccessible())
      {
        // Compute the target platform bundle redirections, if possible.
        //
        if (targetPlatformBundleRedirections == null)
        {
          targetPlatformBundleRedirections = new HashMap<String, URI>();
          Map<String, URI> targetPlatformBundleMappings = CommonPlugin.getTargetPlatformBundleMappings();
          if (targetPlatformBundleMappings != null)
          {
            for (Map.Entry<String, URI> entry : targetPlatformBundleMappings.entrySet())
            {
              String bundleName = entry.getKey();
              URI locationURI = entry.getValue();

              // If the bundle isn't redirected to the workspace already,
              // add a mapping to the bundle's location.
              //
              if (!locationURI.isPlatformResource())
              {
                if ("archive".equals(locationURI.scheme()))
                {
                  locationURI = URI.createURI("jar" + locationURI.toString().substring(7));
                }
                targetPlatformBundleRedirections.put(bundleName, locationURI);
              }
            }
          }
        }

        // If the resource's project name maps to a target platform bundle...
        //
        URI targetPlatformBundleLocation = targetPlatformBundleRedirections.get(URI.decode(uri.segment(1)));
        if (targetPlatformBundleLocation != null)
        {
          // Compute the segments after the project name, append those to the bundle's location,
          // and return that result, which does not need to be further resolved because it will be a file: or jar: URI already.
          //
          List<String> segments = uri.segmentsList().subList(2, uri.segmentCount());
          URI result = targetPlatformBundleLocation.appendSegments(segments.toArray(new String [segments.size()]));
          return result;
        }
      }
    }

    return CommonPlugin.resolve(uri);
  }

  /**
   * @since 2.19
   */
  public static Set<URI> getIncludingTemplates(URI templateURI)
  {
    Set<URI> result = new LinkedHashSet<URI>();
    for (IProject project : WORKSPACE.getRoot().getProjects())
    {
      JETNature jetNature = getRuntime(project);
      if (jetNature != null)
      {
        JETCompileTemplateOperation.State buildState = JETBuilder.getBuildState(project);
        for (List<URI> list : buildState.getJavaFileToTemplateURIs().values())
        {
          int size = list.size();
          for (int i = 1; i < size; ++i)
          {
            if (list.get(i).equals(templateURI))
            {
              result.add(list.get(0));
              break;
            }
          }
        }
      }
    }
    return result;
  }

  static String toString(Document document) throws IOException
  {
    StringWriter writer = new StringWriter();
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    try
    {
      Transformer transformer = transformerFactory.newTransformer();

      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
      transformer.setOutputProperty(OutputKeys.METHOD, "xml");

      transformer.transform(new DOMSource(document), new StreamResult(writer));
    }
    catch (TransformerException exception)
    {
      IOException ioException = new IOException(exception.getLocalizedMessage());
      ioException.initCause(exception);
      throw ioException;
    }
    return writer.toString();
  }

  static Document fromInputSource(InputSource inputSource) throws IOException
  {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    documentBuilderFactory.setNamespaceAware(true);
    documentBuilderFactory.setValidating(false);
    try
    {
      DocumentBuilder parser = documentBuilderFactory.newDocumentBuilder();
      return parser.parse(inputSource);
    }
    catch (Exception exception)
    {
      IOException ioException = new IOException(exception.getLocalizedMessage());
      ioException.initCause(exception);
      throw ioException;
    }
  }
}
