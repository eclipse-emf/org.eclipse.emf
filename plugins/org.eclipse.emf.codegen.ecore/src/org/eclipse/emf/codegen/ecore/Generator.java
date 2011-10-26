/**
 * <copyright> 
 *
 * Copyright (c) 2002-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: Generator.java,v 1.41 2011/10/26 08:36:45 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore;


import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceDescription;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;

import org.eclipse.emf.codegen.CodeGen;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;


/**
 * This implements the method {@link #run}, 
 * which is called just like main during headless workbench invocation.
 */
public class Generator extends CodeGen
{
  /**
   * This supports a non-headless invocation.
   * The variable VABASE or ECLIPSE.
   * @deprecated It is not possible to generate code without using Eclipse.  If
   * you are invoking this method, you should instantiate a Generator and call
   * {@link #run(Object)}.  This method will be removed in a future release.
   */
  @Deprecated
  public static void main(String args[]) 
  {
    new Generator().run(args);
  }

  protected String basePackage;

  public void printGenerateUsage()
  {
    System.out.println("Usage arguments:");
    System.out.println("  [-projects <project-root-directory>]");
    System.out.println("  [-dynamicTemplates] [-forceOverwrite | -diff]");
    System.out.println("  [-generateSchema] [-nonNLSMarkers]");
    System.out.println("  [-codeFormatting { default | <profile-file> } ]");
    System.out.println("  [-model] [-edit] [-editor] [-tests]");
    System.out.println("  [-autoBuild <true|false>]");
    System.out.println("  <genmodel-file>");
    System.out.println("  [ <target-root-directory> ]");
    System.out.println("");
    System.out.println("For example:");
    System.out.println("");
    System.out.println("  generate result/model/Extended.genmodel");
  }

  /**
   * This is called with the command line arguments of a headless workbench invocation.
   */
  @Override
  public Object run(Object object) 
  {
    return PlatformRunnable.run(this, object);
  }
    
  public static class PlatformRunnable extends Generator implements IApplication, DeprecatedPlatformRunnable
  {
    public Object start(IApplicationContext context) throws Exception
    {
      String [] args = (String[])context.getArguments().get(IApplicationContext.APPLICATION_ARGS);
      return run(args == null ? new String[0] : args);
    }
    
    public void stop()
    {
      // Do nothing
    }
    
    /**
     * This is called with the command line arguments of a headless workbench invocation.
     */
    @Override
    public Object run(Object object) 
    {
       return run(this, object);
    }
    
    /**
     * This is called with the command line arguments of a headless workbench invocation.
     */
    public static Object run(final Generator generator, Object object) 
    {
      try
      {
        final String[] arguments = (String[])object;
        final IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRunnable runnable = 
          new IWorkspaceRunnable()
          {
            public void run(IProgressMonitor progressMonitor) throws CoreException
            {
              Monitor monitor = BasicMonitor.toMonitor(progressMonitor);
              try
              {
                if (arguments.length == 0)
                {
                  generator.printGenerateUsage();
                }
                else if ("-ecore2GenModel".equalsIgnoreCase(arguments[0]))
                {
                  IPath ecorePath = new Path(arguments[1]);
                  generator.basePackage = arguments[2];
                  String prefix = arguments[3];
  
                  ResourceSet resourceSet = new ResourceSetImpl();
                  resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap()); 
                  URI ecoreURI = URI.createFileURI(ecorePath.toString());
                  Resource resource = resourceSet.getResource(ecoreURI, true);
                  EPackage ePackage = (EPackage)resource.getContents().get(0);
  
                  IPath genModelPath = ecorePath.removeFileExtension().addFileExtension("genmodel");
                  progressMonitor.beginTask("", 2);
                  progressMonitor.subTask("Creating " + genModelPath);
  
                  URI genModelURI = URI.createFileURI(genModelPath.toString());
                  Resource genModelResource = 
                    Resource.Factory.Registry.INSTANCE.getFactory(genModelURI).createResource(genModelURI);
                  GenModel genModel = GenModelFactory.eINSTANCE.createGenModel();
                  genModelResource.getContents().add(genModel);
                  resourceSet.getResources().add(genModelResource);
                  genModel.setModelDirectory("/TargetProject/src");
                  genModel.getForeignModel().add(ecorePath.toString());
                  genModel.initialize(Collections.singleton(ePackage));
                  GenPackage genPackage = genModel.getGenPackages().get(0);
                  genModel.setModelName(genModelURI.trimFileExtension().lastSegment());
  
                  genPackage.setPrefix(prefix);
                  genPackage.setBasePackage(generator.basePackage);
  
                  progressMonitor.worked(1);
  
                  if (arguments.length > 4 && "-sdo".equals(arguments[4]))
                  {
                    setSDODefaults(genModel);
                  }
  
                  genModelResource.save(Collections.EMPTY_MAP);
                }
                else
                {
                  String rootLocation = null;
                  boolean dynamicTemplates = false;
                  boolean diff = false;
                  boolean forceOverwrite = false;
                  boolean generateSchema = false;
                  boolean nonNLSMarkers = false;
                  boolean codeFormatting = false;
                  String profileFile = null;
                  boolean model = false;
                  boolean edit = false;
                  boolean editor = false;
                  boolean tests = false;
                  Boolean autoBuild = null;
                  
                  int index = 0;
                  for (; index < arguments.length && arguments[index].startsWith("-"); ++index)
                  {
                    if (arguments[index].equalsIgnoreCase("-projects"))
                    {
                      rootLocation = new File(arguments[++index]).getAbsoluteFile().getCanonicalPath();
                    }
                    else if (arguments[index].equalsIgnoreCase("-autoBuild"))
                    {
                      autoBuild = Boolean.valueOf(arguments[++index]);
                    }
                    else if (arguments[index].equalsIgnoreCase("-dynamicTemplates"))
                    {
                      dynamicTemplates = true;
                    }
                    else if (arguments[index].equalsIgnoreCase("-diff"))
                    {
                      diff = true;
                    }
                    else if (arguments[index].equalsIgnoreCase("-forceOverwrite"))
                    {
                      forceOverwrite = true;
                    }
                    else if (arguments[index].equalsIgnoreCase("-generateSchema"))
                    {
                      generateSchema = true;
                    }
                    else if (arguments[index].equalsIgnoreCase("-nonNLSMarkers"))
                    {
                      nonNLSMarkers = true;
                    }
                    else if (arguments[index].equalsIgnoreCase("-codeFormatting"))
                    {
                      codeFormatting = true;
                      profileFile = arguments[++index];
                      if ("default".equals(profileFile))
                      {
                        profileFile = null;
                      }
                    }
                    else if (arguments[index].equalsIgnoreCase("-model"))
                    {
                      model = true;
                    }
                    else if (arguments[index].equalsIgnoreCase("-edit"))
                    {
                      edit = true;
                    }
                    else if (arguments[index].equalsIgnoreCase("-editor"))
                    {
                      editor = true;
                    }
                    else if (arguments[index].equalsIgnoreCase("-tests"))
                    {
                      tests = true;
                    }
                    else
                    {
                      throw new CoreException(
                        new Status(
                          IStatus.ERROR,
                          CodeGenEcorePlugin.getPlugin().getBundle().getSymbolicName(),
                          0,
                          "Unrecognized argument: '" + arguments[index] + "'",
                          null));
                    }
                  }
  
                  if (!model && !edit && !editor && !tests)
                  {
                    model = true;
                  }
  
                  // This is the name of the model.
                  //
                  String genModelName = arguments[index++];
  
                  progressMonitor.beginTask("Generating " + genModelName, 2);
                  
                  if (autoBuild != null)
                  {
                    IWorkspaceDescription description = workspace.getDescription();
                    if (description.isAutoBuilding() != autoBuild)
                    {
                      description.setAutoBuilding(autoBuild);
                      try
                      {
                        workspace.setDescription(description);
                      }
                      catch (CoreException coreException)
                      {
                        generator.printStatus(
                          "Unable to set autoBuild to " + autoBuild.toString() + ".  Code generation will proceed normally.", 
                          coreException.getStatus());
                      }
                    }
                  }
            
                  // Create a resource set and load the model file into it.
                  //
                  ResourceSet resourceSet = new ResourceSetImpl();
                  resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap());
                  URI genModelURI = URI.createFileURI(new File(genModelName).getAbsoluteFile().getCanonicalPath());
                  Resource genModelResource = resourceSet.getResource(genModelURI, true);
                  GenModel genModel = (GenModel)genModelResource.getContents().get(0);
  
                  IStatus status = genModel.validate();
                  if (!status.isOK())
                  {
                    generator.printStatus("", status);
                  }
                  else
                  {
                    org.eclipse.emf.codegen.ecore.generator.Generator gen = new org.eclipse.emf.codegen.ecore.generator.Generator();
                    gen.setInput(genModel);

                    if (dynamicTemplates)
                    {
                      genModel.setDynamicTemplates(true);
                    }
                    if (forceOverwrite)
                    {
                      genModel.setForceOverwrite(true);
                    }
                    if (diff)
                    {
                      genModel.setRedirection(".{0}.new");
                    }
    
                    if (index < arguments.length)
                    {
                      IPath path = new Path(genModel.getModelDirectory());
                      // This is the path of the target directory.
                      //
                      IPath targetRootDirectory = new Path(arguments[index]);
                      targetRootDirectory = new Path(targetRootDirectory.toFile().getAbsoluteFile().getCanonicalPath());
                      CodeGenUtil.EclipseUtil.findOrCreateContainer
                        (new Path(path.segment(0)),
                         true,
                         targetRootDirectory,
                         //DMS Why not this?
                         //new SubProgressMonitor(progressMonitor, 1));
                         BasicMonitor.toIProgressMonitor(CodeGenUtil.EclipseUtil.createMonitor(progressMonitor, 1)));
                    }
                    // This is to handle a genmodel produced by rose2genmodel.
                    //
                    else
                    {
                      String modelDirectory = genModel.getModelDirectory();
                      genModel.setModelDirectory(generator.findOrCreateContainerHelper(rootLocation, modelDirectory, monitor));
    
                      String editDirectory = genModel.getEditDirectory();
                      if (edit && editDirectory != null)
                      {
                        genModel.setEditDirectory(generator.findOrCreateContainerHelper(rootLocation, editDirectory, monitor));
                      }
    
                      String editorDirectory = genModel.getEditorDirectory();
                      if (editor && editorDirectory != null)
                      {
                        genModel.setEditorDirectory(generator.findOrCreateContainerHelper(rootLocation, editorDirectory, monitor));
                      }
  
                      String testsDirectory = genModel.getTestsDirectory();
                      if (tests && testsDirectory != null)
                      {
                        genModel.setTestsDirectory(generator.findOrCreateContainerHelper(rootLocation, testsDirectory, monitor));
                      }
                    }
    
                    genModel.setCanGenerate(true);
                    genModel.setUpdateClasspath(false);
    
                    if (generateSchema)
                    {
                      genModel.setGenerateSchema(true);
                    }
                    if (nonNLSMarkers)
                    {
                      genModel.setNonNLSMarkers(true);
                    }
                    if (codeFormatting)
                    {
                      genModel.setCodeFormatting(true);
                    }
  
                    if (profileFile != null)
                    {
                      Map<String, String> options = CodeFormatterProfileParser.parse(profileFile);
                      if (options == null)
                      {
                        throw new CoreException
                          (new Status
                            (IStatus.ERROR,
                             CodeGenEcorePlugin.getPlugin().getBundle().getSymbolicName(),
                             0,
                             "Unable to read profile file: '" + profileFile + "'",
                             null));
                      }
                      gen.getOptions().codeFormatterOptions = options;
                      
                    }
  
                    if (model)
                    {
                      gen.generate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, CodeGenUtil.EclipseUtil.createMonitor(progressMonitor, 1));
                    }
                    if (edit)
                    {
                      gen.generate(genModel, GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE, CodeGenUtil.EclipseUtil.createMonitor(progressMonitor, 1));
                    }
                    if (editor)
                    {
                      gen.generate(genModel, GenBaseGeneratorAdapter.EDITOR_PROJECT_TYPE, CodeGenUtil.EclipseUtil.createMonitor(progressMonitor, 1));
                    }
                    if (tests)
                    {
                      gen.generate(genModel, GenBaseGeneratorAdapter.TESTS_PROJECT_TYPE, CodeGenUtil.EclipseUtil.createMonitor(progressMonitor, 1));
                    }
                  }
                }
              }
              catch (CoreException exception)
              {
                throw exception;
              }
              catch (Exception exception)
              {
                throw 
                  new CoreException
                    (new Status
                      (IStatus.ERROR, CodeGenEcorePlugin.getPlugin().getBundle().getSymbolicName(), 0, "EMF Error", exception));
              }
              finally
              {
                progressMonitor.done();
              }
            }
          };
        workspace.run(runnable, new CodeGenUtil.EclipseUtil.StreamProgressMonitor(System.out));
  
        return 0;
      }
      catch (Exception exception)
      {
        generator.printGenerateUsage();
        exception.printStackTrace();
        CodeGenEcorePlugin.INSTANCE.log(exception);
        return 1;
      }
    }
  }

  protected String findOrCreateContainerHelper
    (String rootLocation, String encodedPath, Monitor progressMonitor) throws CoreException
  {
    return EclipseHelper.findOrCreateContainerHelper(rootLocation, encodedPath, progressMonitor);
  }

  public static int EMF_MODEL_PROJECT_STYLE  = 0x0001;
  public static int EMF_EDIT_PROJECT_STYLE   = 0x0002;
  public static int EMF_EDITOR_PROJECT_STYLE = 0x0004;
  public static int EMF_XML_PROJECT_STYLE    = 0x0008;
  public static int EMF_PLUGIN_PROJECT_STYLE = 0x0010;
  public static int EMF_EMPTY_PROJECT_STYLE  = 0x0020;
  public static int EMF_TESTS_PROJECT_STYLE  = 0x0040;
  public static int EMF_GWT_PROJECT_STYLE    = 0x0080;

  public static IProject createEMFProject
    (IPath javaSource,
     IPath projectLocationPath,
     List<IProject> referencedProjects,
     IProgressMonitor progressMonitor,
     int style)
  {
    return createEMFProject(javaSource, projectLocationPath, referencedProjects, progressMonitor, style, Collections.emptyList());
  }

  public static IProject createEMFProject
    (IPath javaSource,
     IPath projectLocationPath,
     List<IProject> referencedProjects,
     IProgressMonitor progressMonitor,
     int style,
     List<?> pluginVariables)
  {
    return 
      EclipseHelper.createEMFProject
        (javaSource, projectLocationPath, referencedProjects, BasicMonitor.toMonitor(progressMonitor), style, pluginVariables);
  }
  
  public static IProject createEMFProject
    (IPath javaSource,
     IPath projectLocationPath,
     List<IProject> referencedProjects,
     Monitor progressMonitor,
     int style)
  {
    return createEMFProject(javaSource, projectLocationPath, referencedProjects, progressMonitor, style, Collections.emptyList());
  }

  public static IProject createEMFProject
    (IPath javaSource,
     IPath projectLocationPath,
     List<IProject> referencedProjects,
     Monitor progressMonitor,
     int style,
     List<?> pluginVariables)
  {
    return EclipseHelper.createEMFProject(javaSource, projectLocationPath, referencedProjects, progressMonitor, style, pluginVariables);
  }

  public static IProject createEMFProject
    (IPath javaSource,
     URI projectLocationURI,
     List<IProject> referencedProjects,
     Monitor progressMonitor,
     int style,
     List<?> pluginVariables)
  {
    return EclipseHelper.createEMFProject(javaSource, projectLocationURI, referencedProjects, progressMonitor, style, pluginVariables);
  }

  public void printStatus(String prefix, IStatus status)
  {
    System.err.print(prefix);
    System.err.println(status.getMessage());
    IStatus [] children = status.getChildren();
    String childPrefix = "  " + prefix;
    for (int i = 0; i < children.length; ++i)
    {
      printStatus(childPrefix, children[i]);
    }
  }

  public static void setSDODefaults(GenModel genModel)
  {
    genModel.setRootExtendsInterface("");
    genModel.setRootImplementsInterface("org.eclipse.emf.ecore.sdo.InternalEDataObject");
    genModel.setRootExtendsClass("org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl");
    genModel.setFeatureMapWrapperInterface("commonj.sdo.Sequence");
    genModel.setFeatureMapWrapperInternalInterface("org.eclipse.emf.ecore.sdo.util.ESequence");
    genModel.setFeatureMapWrapperClass("org.eclipse.emf.ecore.sdo.util.BasicESequence");
    genModel.setSuppressEMFTypes(true);
    genModel.setSuppressEMFMetaData(true);

    genModel.getModelPluginVariables().add("EMF_COMMONJ_SDO=org.eclipse.emf.commonj.sdo");
    genModel.getModelPluginVariables().add("EMF_ECORE_SDO=org.eclipse.emf.ecore.sdo");

    genModel.getStaticPackages().add("http://www.eclipse.org/emf/2003/SDO");
  }
  
  /**
   * This parses a code formatter profile file, recording the options it sepecifies in a map.
   */
  static class CodeFormatterProfileParser extends DefaultHandler
  {
    private Map<String, String> options = null;

    private String SETTING = "setting";
    private String ID = "id";
    private String VALUE = "value";
    private String EMPTY = "";

    @Override
    public void startDocument()
    {
      options = new HashMap<String, String>();
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qualifiedName, Attributes atts)
    {
      if (EMPTY.equals(namespaceURI) && SETTING.equals(localName))
      {
        String id = atts.getValue(EMPTY, ID); 
        String value = atts.getValue(EMPTY, VALUE);

        if (id != null && value != null)
        {
          options.put(id, value);
        }
      }
    }

    public Map<String, String> getOptions()
    {
      return options;
    }
 
    public static Map<String, String> parse(String systemID)
    {
      try
      {
        XMLReader parser = XMLReaderFactory.createXMLReader();
        CodeFormatterProfileParser handler = new CodeFormatterProfileParser();
        parser.setContentHandler(handler);
        parser.parse(systemID);
        return handler.getOptions();
      }
      catch (Exception e)
      {
        // Ignore
      }
      return null;
    }
  }
  
  private static class EclipseHelper
  {
    public static IProject createEMFProject
      (IPath javaSource,
       IPath projectLocationPath,
       List<IProject> referencedProjects,
       Monitor monitor,
       int style,
       List<?> pluginVariables)
    {
      return createEMFProject(javaSource, projectLocationPath == null ? null : URI.createFileURI(projectLocationPath.toOSString()), referencedProjects, monitor, style, pluginVariables);
    }

    public static IProject createEMFProject
      (IPath javaSource,
       URI projectLocationURI,
       List<IProject> referencedProjects,
       Monitor monitor,
       int style,
       List<?> pluginVariables)
    {
      IProgressMonitor progressMonitor = BasicMonitor.toIProgressMonitor(monitor);
      String projectName = javaSource.segment(0);
      IProject project = null;
      try
      {
        List<IClasspathEntry> classpathEntries = new UniqueEList<IClasspathEntry>();
  
        progressMonitor.beginTask("", 10);
        progressMonitor.subTask
          (CodeGenEcorePlugin.INSTANCE.getString
             ("_UI_CreatingEMFProject_message", 
              new Object [] { projectName, projectLocationURI != null ? projectLocationURI.toString() : projectName }));
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        project = workspace.getRoot().getProject(projectName);
  
        // Clean up any old project information.
        //
        if (!project.exists())
        {
          URI location = projectLocationURI;
          if (location == null)
          {
            location = URI.createFileURI(workspace.getRoot().getLocation().append(projectName).toOSString());
          }
          location = location.appendSegment(".project");
          File projectFile = new File(location.toString());
          if (projectFile.exists())
          {
            projectFile.renameTo(new File(location.toString() + ".old"));
          }
        }
  
        IJavaProject javaProject = JavaCore.create(project);
        IProjectDescription projectDescription = null;
        if (!project.exists())
        {
          projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(projectName);
          if (projectLocationURI != null)
          {
            projectDescription.setLocationURI(new java.net.URI(projectLocationURI.toString()));
          }
          project.create(projectDescription, new SubProgressMonitor(progressMonitor, 1));
          project.open(new SubProgressMonitor(progressMonitor, 1));
        }
        else 
        {
          projectDescription = project.getDescription();
          project.open(new SubProgressMonitor(progressMonitor, 1));
          if (project.hasNature(JavaCore.NATURE_ID))
          {
            classpathEntries.addAll(Arrays.asList(javaProject.getRawClasspath()));
          }
        }

        boolean isInitiallyEmpty = classpathEntries.isEmpty();
  
        {
          if (referencedProjects.size() != 0 && (style & (EMF_PLUGIN_PROJECT_STYLE | EMF_EMPTY_PROJECT_STYLE)) == 0)
          {
            projectDescription.setReferencedProjects
              (referencedProjects.toArray(new IProject[referencedProjects.size()]));
            for (IProject referencedProject : referencedProjects)
            {
              IClasspathEntry referencedProjectClasspathEntry = JavaCore.newProjectEntry(referencedProject.getFullPath());
              classpathEntries.add(referencedProjectClasspathEntry);
            }
          }
  
          String [] natureIds = projectDescription.getNatureIds();
          if (natureIds == null)
          {
            natureIds = 
              ((style & EMF_GWT_PROJECT_STYLE) == 0 ? 
                  new String [] { JavaCore.NATURE_ID, "org.eclipse.pde.PluginNature" } :
                  (style & EMF_EDITOR_PROJECT_STYLE) == 0 ? 
                    new String [] { JavaCore.NATURE_ID, "org.eclipse.pde.PluginNature", "com.google.gwt.eclipse.core.gwtNature" } :
                    new String [] 
                      { JavaCore.NATURE_ID, 
                        "org.eclipse.pde.PluginNature", 
                        "com.google.gwt.eclipse.core.gwtNature", 
                        "com.google.appengine.eclipse.core.gaeNature" });
          }
          else
          {
            if (!project.hasNature(JavaCore.NATURE_ID))
            {
              String [] oldNatureIds = natureIds;
              natureIds = new String [oldNatureIds.length + 1];
              System.arraycopy(oldNatureIds, 0, natureIds, 0, oldNatureIds.length);
              natureIds[oldNatureIds.length] = JavaCore.NATURE_ID;
            }
            if (!project.hasNature("org.eclipse.pde.PluginNature"))
            {
              String [] oldNatureIds = natureIds;
              natureIds = new String [oldNatureIds.length + 1];
              System.arraycopy(oldNatureIds, 0, natureIds, 0, oldNatureIds.length);
              natureIds[oldNatureIds.length] = "org.eclipse.pde.PluginNature";
            }
            if ((style & EMF_GWT_PROJECT_STYLE) != 0 && !project.hasNature("com.google.gwt.eclipse.core.gwtNature"))
            {
              String [] oldNatureIds = natureIds;
              natureIds = new String [oldNatureIds.length + 1];
              System.arraycopy(oldNatureIds, 0, natureIds, 0, oldNatureIds.length);
              natureIds[oldNatureIds.length] = "com.google.gwt.eclipse.core.gwtNature";
            }
            if ((style & EMF_GWT_PROJECT_STYLE) != 0 && (style & EMF_EDITOR_PROJECT_STYLE) != 0 && !project.hasNature("com.google.appengine.eclipse.core.gaeNature"))
            {
              String [] oldNatureIds = natureIds;
              natureIds = new String [oldNatureIds.length + 1];
              System.arraycopy(oldNatureIds, 0, natureIds, 0, oldNatureIds.length);
              natureIds[oldNatureIds.length] = "com.google.appengine.eclipse.core.gaeNature";
            }
          }
          projectDescription.setNatureIds(natureIds);
  
          ICommand [] builders = projectDescription.getBuildSpec();
          if (builders == null)
          {
            builders = new ICommand [0];
          }
          boolean hasGWTBuilder = false;
          int enhancerBuilderIndex = -1;
          for (int i = 0; i < builders.length; ++i)
          {
            if ("org.eclipse.emf.codegen.ecore.GWTBuilder".equals(builders[i].getBuilderName()))
            {
              hasGWTBuilder = true;
            }
            if ("com.google.appengine.eclipse.core.enhancerbuilder".equals(builders[i].getBuilderName()))
            {
              enhancerBuilderIndex = i;
            }
          }
          if ((style & EMF_GWT_PROJECT_STYLE) != 0 && (style & EMF_EDITOR_PROJECT_STYLE) != 0 && !hasGWTBuilder)
          {
            ICommand [] oldBuilders = builders;
            builders = new ICommand [oldBuilders.length + 1];
            System.arraycopy(oldBuilders, 0, builders, 0, oldBuilders.length);
            builders[oldBuilders.length] = projectDescription.newCommand();
            builders[oldBuilders.length].setBuilderName("org.eclipse.emf.codegen.ecore.GWTBuilder");
          }
          if (enhancerBuilderIndex != -1)
          {
            ICommand [] oldBuilders = builders;
            builders = new ICommand [oldBuilders.length - 1];
            System.arraycopy(oldBuilders, 0, builders, 0, enhancerBuilderIndex);
            System.arraycopy(oldBuilders, enhancerBuilderIndex + 1, builders, enhancerBuilderIndex, oldBuilders.length - enhancerBuilderIndex - 1);
          }
          projectDescription.setBuildSpec(builders);
          project.setDescription(projectDescription, new SubProgressMonitor(progressMonitor, 1));
  
          IContainer sourceContainer = project;
          if (javaSource.segmentCount() > 1)
          {
        	IPath sourceContainerPath = javaSource.removeFirstSegments(1).makeAbsolute();
        	sourceContainer = project.getFolder(sourceContainerPath);
        	if (!sourceContainer.exists())
        	{
        	  for (int i = sourceContainerPath.segmentCount() - 1; i >= 0; i--)
        	  {
	            sourceContainer = project.getFolder(sourceContainerPath.removeLastSegments(i));
	            if (!sourceContainer.exists())
	            {
	              ((IFolder)sourceContainer).create(false, true, new SubProgressMonitor(progressMonitor, 1));  
	            }
	          }
            }

            IClasspathEntry sourceClasspathEntry = JavaCore.newSourceEntry(javaSource);
            for (Iterator<IClasspathEntry> i = classpathEntries.iterator(); i.hasNext(); )
            {
              IClasspathEntry classpathEntry = i.next();
              if (classpathEntry.getPath().isPrefixOf(javaSource))
              {
                i.remove();
              }
            }
            classpathEntries.add(0, sourceClasspathEntry);
          }
  
          if (isInitiallyEmpty)
          {
            IClasspathEntry jreClasspathEntry =
              JavaCore.newVariableEntry
                (new Path(JavaRuntime.JRELIB_VARIABLE), new Path(JavaRuntime.JRESRC_VARIABLE), new Path(JavaRuntime.JRESRCROOT_VARIABLE));
            for (Iterator<IClasspathEntry> i = classpathEntries.iterator(); i.hasNext(); )
            {
              IClasspathEntry classpathEntry = i.next();
              if (classpathEntry.getPath().isPrefixOf(jreClasspathEntry.getPath()))
              {
                i.remove();
              }
            }
  
            String jreContainer = JavaRuntime.JRE_CONTAINER;
            String complianceLevel = CodeGenUtil.EclipseUtil.getJavaComplianceLevel(project);
            if (!"1.4".equals(complianceLevel))
            {
              jreContainer += "/org.eclipse.jdt.internal.debug.ui.launcher.StandardVMType/J2SE-" + complianceLevel;
            }
            classpathEntries.add(JavaCore.newContainerEntry(new Path(jreContainer)));
          }
  
          if ((style & EMF_EMPTY_PROJECT_STYLE) == 0)
          {
            if ((style & EMF_PLUGIN_PROJECT_STYLE) != 0)
            {
              classpathEntries.add(JavaCore.newContainerEntry(new Path("org.eclipse.pde.core.requiredPlugins")));

              if ((style & EMF_GWT_PROJECT_STYLE) != 0)
              {
                classpathEntries.add(JavaCore.newContainerEntry(new Path("com.google.gwt.eclipse.core.GWT_CONTAINER")));
                if ((style & EMF_EDITOR_PROJECT_STYLE) != 0)
                {
                  classpathEntries.add(JavaCore.newContainerEntry(new Path("com.google.appengine.eclipse.core.GAE_CONTAINER")));
                }
              }
  
              // Remove variables since the plugin.xml should provide the complete path information.
              //
              for (Iterator<IClasspathEntry> i = classpathEntries.iterator(); i.hasNext(); )
              {
                IClasspathEntry classpathEntry = i.next();
                if (classpathEntry.getEntryKind() == IClasspathEntry.CPE_VARIABLE && 
                      !JavaRuntime.JRELIB_VARIABLE.equals(classpathEntry.getPath().toString()) ||
                      classpathEntry.getEntryKind() == IClasspathEntry.CPE_PROJECT)
                {
                  i.remove();
                }
              }
            }
            else
            {
              CodeGenUtil.EclipseUtil.addClasspathEntries(classpathEntries, "ECLIPSE_CORE_RUNTIME", "org.eclipse.core.runtime");
              CodeGenUtil.EclipseUtil.addClasspathEntries(classpathEntries, "ECLIPSE_CORE_RESOURCES", "org.eclipse.core.resources");
              CodeGenUtil.EclipseUtil.addClasspathEntries(classpathEntries, "EMF_COMMON", "org.eclipse.emf.common");
              CodeGenUtil.EclipseUtil.addClasspathEntries(classpathEntries, "EMF_ECORE", "org.eclipse.emf.ecore");
  
              if ((style & EMF_XML_PROJECT_STYLE) != 0)
              {
                CodeGenUtil.EclipseUtil.addClasspathEntries(classpathEntries, "EMF_ECORE_XMI", "org.eclipse.emf.ecore.xmi");
              }
  
              if ((style & EMF_MODEL_PROJECT_STYLE) == 0)
              {
                CodeGenUtil.EclipseUtil.addClasspathEntries(classpathEntries, "EMF_EDIT", "org.eclipse.emf.edit");
  
                if ((style & EMF_EDIT_PROJECT_STYLE) == 0)
                {
                  CodeGenUtil.EclipseUtil.addClasspathEntries(classpathEntries, "ECLIPSE_SWT", "org.eclipse.swt");
                  CodeGenUtil.EclipseUtil.addClasspathEntries(classpathEntries, "ECLIPSE_JFACE", "org.eclipse.jface");
                  CodeGenUtil.EclipseUtil.addClasspathEntries(classpathEntries, "ECLIPSE_UI_VIEWS", "org.eclipse.ui.views");
                  CodeGenUtil.EclipseUtil.addClasspathEntries(classpathEntries, "ECLIPSE_UI_EDITORS", "org.eclipse.ui.editors");
                  CodeGenUtil.EclipseUtil.addClasspathEntries(classpathEntries, "ECLIPSE_UI_IDE", "org.eclipse.ui.ide");
                  CodeGenUtil.EclipseUtil.addClasspathEntries(classpathEntries, "ECLIPSE_UI_WORKBENCH", "org.eclipse.ui.workbench");
                  CodeGenUtil.EclipseUtil.addClasspathEntries(classpathEntries, "EMF_COMMON_UI", "org.eclipse.emf.common.ui");
                  CodeGenUtil.EclipseUtil.addClasspathEntries(classpathEntries, "EMF_EDIT_UI", "org.eclipse.emf.edit.ui");
                  if ((style & EMF_XML_PROJECT_STYLE) == 0)
                  {
                    CodeGenUtil.EclipseUtil.addClasspathEntries(classpathEntries, "EMF_ECORE_XMI", "org.eclipse.emf.ecore.xmi");
                  }
                }
              }
  
              if ((style & EMF_TESTS_PROJECT_STYLE) != 0)
              {
                CodeGenUtil.EclipseUtil.addClasspathEntries(classpathEntries, "JUNIT", "org.junit");
              }

              if (pluginVariables != null)
              {
                for (Iterator<?> i = pluginVariables.iterator(); i.hasNext(); )
                {
                  Object variable = i.next();
                  if (variable instanceof IClasspathEntry)
                  {
                    classpathEntries.add((IClasspathEntry)variable);
                  }
                  else if (variable instanceof String)
                  {
                    String pluginVariable = (String)variable;
                    String name;
                    String id;
                    int index = pluginVariable.indexOf("=");
                    if (index == -1)
                    {
                      name = pluginVariable.replace('.','_').toUpperCase();
                      id = pluginVariable;
                    }
                    else
                    {
                      name = pluginVariable.substring(0, index);
                      id = pluginVariable.substring(index + 1);
                    }
                    CodeGenUtil.EclipseUtil.addClasspathEntries(classpathEntries, name, id);
                  }
                }
              }
            }
          }
  
          javaProject.setRawClasspath
            (classpathEntries.toArray(new IClasspathEntry[classpathEntries.size()]),
             new SubProgressMonitor(progressMonitor, 1));
        }
  
        if (isInitiallyEmpty)
        {
          javaProject.setOutputLocation
            (new Path("/" + javaSource.segment(0) + (((style & EMF_GWT_PROJECT_STYLE) != 0) && ((style & EMF_EDITOR_PROJECT_STYLE) != 0) ? "/war/WEB-INF/classes" : "/bin")), 
             new SubProgressMonitor(progressMonitor, 1));
        }
      }
      catch (Exception exception)
      {
        exception.printStackTrace();
        CodeGenEcorePlugin.INSTANCE.log(exception);
      }
      finally
      {
        progressMonitor.done();
      }
  
      return project;
    }
    
    public static String findOrCreateContainerHelper
      (String rootLocation, String encodedPath, Monitor progressMonitor) throws CoreException
    {
      int index = encodedPath.indexOf("/./");
      if (encodedPath.endsWith("/.") && index != -1)
      {
        IPath modelProjectLocation = new Path(encodedPath.substring(0, index));
        IPath fragmentPath = new Path(encodedPath.substring(index + 3, encodedPath.length() - 2));
  
        IPath projectRelativePath =  new Path(modelProjectLocation.lastSegment()).append(fragmentPath);
  
        CodeGenUtil.EclipseUtil.findOrCreateContainer
          (projectRelativePath,
           true,
           modelProjectLocation,
           //DMS Why not this?
           //new SubProgressMonitor(progressMonitor, 1));
           BasicMonitor.toIProgressMonitor(CodeGenUtil.createMonitor(progressMonitor, 1)));
  
        return projectRelativePath.makeAbsolute().toString();
      }
      else if (rootLocation != null)
      {
        // Look for a likely plugin name.
        //
        index = encodedPath.indexOf("/org.");
        if (index == -1)
        {
          index = encodedPath.indexOf("/com.");
        }
        if (index == -1)
        {
          index = encodedPath.indexOf("/javax.");
        }
        if (index != -1)
        {
          IPath projectRelativePath = new Path(encodedPath.substring(index, encodedPath.length()));
          index = encodedPath.indexOf("/", index + 5);
          if (index != -1)
          {
            IPath modelProjectLocation = new Path(rootLocation + "/" + encodedPath.substring(0, index));
    
            CodeGenUtil.EclipseUtil.findOrCreateContainer
              (projectRelativePath,
               true, 
               modelProjectLocation, 
               //DMS Why not this?
               //new SubProgressMonitor(progressMonitor, 1));
               BasicMonitor.toIProgressMonitor(CodeGenUtil.createMonitor(progressMonitor, 1)));
    
            return projectRelativePath.makeAbsolute().toString();
          }
        }
      }
  
      return encodedPath;
    }
  }
}

@SuppressWarnings("deprecation")
interface DeprecatedPlatformRunnable extends org.eclipse.core.runtime.IPlatformRunnable
{
  // Empty extension to limit the effect of suppressing the deprecation warning.
}
