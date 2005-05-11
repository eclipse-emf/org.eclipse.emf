/**
 * <copyright> 
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: Generator.java,v 1.13 2005/05/11 16:53:18 emerks Exp $
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
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.launching.JavaRuntime;

import org.eclipse.emf.codegen.CodeGen;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
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
   */
  public static void main(String args[]) 
  {
    new Generator().run(args);
  }

  /**
   * This creates an instance.
   */
  public Generator() 
  {
  }

  protected String basePackage;

  public void printGenerateUsage()
  {
    System.out.println("Usage arguments:");
    System.out.println("  [-platform | -data] <workspace-directory> ");
    System.out.println("  [-projects ] <project-root-directory> ");
    System.out.println("  [-dynamicTemplates] [-forceOverwrite | -diff]");
    System.out.println("  [-generateSchema] [-nonNLSMarkers]");
    System.out.println("  [-codeFormatting { default | <profile-file> } ]");
    System.out.println("  [-model] [-edit] [-editor]");
    System.out.println("  <genmodel-file>");
    System.out.println("  [ <target-root-directory> ]");
    System.out.println("");
    System.out.println("For example:");
    System.out.println("");
    System.out.println("  generate result/src/model/Extended.genmodel");
  }

  /**
   * This is called with the command line arguments of a headless workbench invocation.
   */
  public Object run(Object object) 
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
            try
            {
              if (arguments.length == 0)
              {
                printGenerateUsage();
              }
              else if ("-ecore2GenModel".equalsIgnoreCase(arguments[0]))
              {
                IPath ecorePath = new Path(arguments[1]);
                basePackage = arguments[2];
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
                GenPackage genPackage = (GenPackage)genModel.getGenPackages().get(0);
                genModel.setModelName(genModelURI.trimFileExtension().lastSegment());

                genPackage.setPrefix(prefix);
                genPackage.setBasePackage(basePackage);

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
                
                int index = 0;
                for (; index < arguments.length && arguments[index].startsWith("-"); ++index)
                {
                  if (arguments[index].equalsIgnoreCase("-projects"))
                  {
                    rootLocation = new File(arguments[++index]).getAbsoluteFile().getCanonicalPath();
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
                  printStatus("", status);
                }
                else
                {
                  if (dynamicTemplates)
                  {
                    genModel.setDynamicTemplates(dynamicTemplates);
                  }
                  genModel.setForceOverwrite(forceOverwrite);
                  genModel.setRedirection(diff ? ".{0}.new" : "");
  
                  if (index < arguments.length)
                  {
                    IPath path = new Path(genModel.getModelDirectory());
                    // This is the path of the target directory.
                    //
                    IPath targetRootDirectory = new Path(arguments[index]);
                    targetRootDirectory = new Path(targetRootDirectory.toFile().getAbsoluteFile().getCanonicalPath());
                    CodeGen.findOrCreateContainer
                      (new Path(path.segment(0)), true, targetRootDirectory, new SubProgressMonitor(progressMonitor, 1));
                  }
                  // This is to handle a genmodel produced by rose2genmodel.
                  //
                  else
                  {
                    String modelDirectory = genModel.getModelDirectory();
                    genModel.setModelDirectory(findOrCreateContainerHelper(rootLocation, modelDirectory, progressMonitor));
  
                    String editDirectory = genModel.getEditDirectory();
                    if (edit && editDirectory != null)
                    {
                      genModel.setEditDirectory(findOrCreateContainerHelper(rootLocation, editDirectory, progressMonitor));
                    }
  
                    String editorDirectory = genModel.getEditorDirectory();
                    if (editor && editorDirectory != null)
                    {
                      genModel.setEditorDirectory(findOrCreateContainerHelper(rootLocation, editorDirectory, progressMonitor));
                    }

                    String testsDirectory = genModel.getTestsDirectory();
                    if (tests && testsDirectory != null)
                    {
                      genModel.setTestsDirectory(findOrCreateContainerHelper(rootLocation, testsDirectory, progressMonitor));
                    }
                  }
  
                  genModel.setCanGenerate(true);
                  genModel.setUpdateClasspath(false);
  
                  genModel.setGenerateSchema(generateSchema);
                  genModel.setNonNLSMarkers(nonNLSMarkers);
                  genModel.setCodeFormatting(codeFormatting);

                  if (profileFile != null)
                  {
                    Map options = CodeFormatterProfileParser.parse(profileFile);
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
                    genModel.setCodeFormatterOptions(options);
                  }

                  if (model)
                  {
                    genModel.generate(new SubProgressMonitor(progressMonitor, 1));
                  }
                  if (edit)
                  {
                    genModel.generateEdit(new SubProgressMonitor(progressMonitor, 1));
                  }
                  if (editor)
                  {
                    genModel.generateEditor(new SubProgressMonitor(progressMonitor, 1));
                  }
                  if (tests)
                  {
                    genModel.generateTests(new SubProgressMonitor(progressMonitor, 1));
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
      workspace.run(runnable, new StreamProgressMonitor(System.out));

      return new Integer(0);
    }
    catch (Exception exception)
    {
      printGenerateUsage();
      exception.printStackTrace();
      CodeGenEcorePlugin.INSTANCE.log(exception);
      return new Integer(1);
    }
  }

  protected String findOrCreateContainerHelper
    (String rootLocation, String encodedPath, IProgressMonitor progressMonitor) throws CoreException
  {
    int index = encodedPath.indexOf("/./");
    if (encodedPath.endsWith("/.") && index != -1)
    {
      IPath modelProjectLocation = new Path(encodedPath.substring(0, index));
      IPath fragmentPath = new Path(encodedPath.substring(index + 3, encodedPath.length() - 2));

      IPath projectRelativePath =  new Path(modelProjectLocation.lastSegment()).append(fragmentPath);

      CodeGen.findOrCreateContainer
        (projectRelativePath,
         true, 
         modelProjectLocation, 
         new SubProgressMonitor(progressMonitor, 1));

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
  
          CodeGen.findOrCreateContainer
            (projectRelativePath,
             true, 
             modelProjectLocation, 
             new SubProgressMonitor(progressMonitor, 1));
  
          return projectRelativePath.makeAbsolute().toString();
        }
      }
    }

    return encodedPath;
  }

  public static int EMF_MODEL_PROJECT_STYLE  = 0x0001;
  public static int EMF_EDIT_PROJECT_STYLE   = 0x0002;
  public static int EMF_EDITOR_PROJECT_STYLE = 0x0004;
  public static int EMF_XML_PROJECT_STYLE    = 0x0008;
  public static int EMF_PLUGIN_PROJECT_STYLE = 0x0010;
  public static int EMF_EMPTY_PROJECT_STYLE  = 0x0020;
  public static int EMF_TESTS_PROJECT_STYLE  = 0x0040;

  public static IProject createEMFProject
    (IPath javaSource,
     IPath projectLocationPath,
     List referencedProjects,
     IProgressMonitor progressMonitor,
     int style)
  {
    return createEMFProject(javaSource, projectLocationPath, referencedProjects, progressMonitor, style, Collections.EMPTY_LIST);
  }

  public static IProject createEMFProject
    (IPath javaSource,
     IPath projectLocationPath,
     List referencedProjects,
     IProgressMonitor progressMonitor,
     int style,
     List pluginVariables)
  {
    String projectName = javaSource.segment(0);
    IProject project = null;
    try
    {
      List classpathEntries = new UniqueEList();

      progressMonitor.beginTask("", 10);
      progressMonitor.subTask(CodeGenEcorePlugin.INSTANCE.getString("_UI_CreatingEMFProject_message", new Object [] { projectName }));
      IWorkspace workspace = ResourcesPlugin.getWorkspace();
      project = workspace.getRoot().getProject(projectName);

      // Clean up any old project information.
      //
      if (!project.exists())
      {
        IPath location = projectLocationPath;
        if (location == null)
        {
          location = workspace.getRoot().getLocation().append(projectName);
        }
        location = location.append(".project");
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
        projectDescription.setLocation(projectLocationPath);
        project.create(projectDescription, new SubProgressMonitor(progressMonitor, 1));
      }
      else 
      {
        projectDescription = project.getDescription();
        classpathEntries.addAll(Arrays.asList(javaProject.getRawClasspath()));
      }

      boolean isInitiallyEmpty = classpathEntries.isEmpty();

      {
        if (referencedProjects.size() != 0 && (style & (EMF_PLUGIN_PROJECT_STYLE | EMF_EMPTY_PROJECT_STYLE)) == 0)
        {
          projectDescription.setReferencedProjects
            ((IProject [])referencedProjects.toArray(new IProject [referencedProjects.size()]));
          for (Iterator i = referencedProjects.iterator(); i.hasNext(); )
          {
            IProject referencedProject = (IProject)i.next();
            IClasspathEntry referencedProjectClasspathEntry = JavaCore.newProjectEntry(referencedProject.getFullPath());
            classpathEntries.add(referencedProjectClasspathEntry);
          }
        }

        String [] natureIds = projectDescription.getNatureIds();
        if (natureIds == null)
        {
          natureIds = new String [] { JavaCore.NATURE_ID };
        }
        else
        {
          boolean hasJavaNature = false;
          boolean hasPDENature = false;
          for (int i = 0; i < natureIds.length; ++i)
          {
            if (JavaCore.NATURE_ID.equals(natureIds[i]))
            {
              hasJavaNature = true;
            }
            if ("org.eclipse.pde.PluginNature".equals(natureIds[i]))
            {
              hasPDENature = true;
            }
          }
          if (!hasJavaNature)
          {
            String [] oldNatureIds = natureIds;
            natureIds = new String [oldNatureIds.length + 1];
            System.arraycopy(oldNatureIds, 0, natureIds, 0, oldNatureIds.length);
            natureIds[oldNatureIds.length] = JavaCore.NATURE_ID;
          }
          if (!hasPDENature)
          {
            String [] oldNatureIds = natureIds;
            natureIds = new String [oldNatureIds.length + 1];
            System.arraycopy(oldNatureIds, 0, natureIds, 0, oldNatureIds.length);
            natureIds[oldNatureIds.length] = "org.eclipse.pde.PluginNature";
          }
        }
        projectDescription.setNatureIds(natureIds);

        ICommand [] builders = projectDescription.getBuildSpec();
        if (builders == null)
        {
          builders = new ICommand [0];
        }
        boolean hasManifestBuilder = false;
        boolean hasSchemaBuilder = false;
        for (int i = 0; i < builders.length; ++i)
        {
          if ("org.eclipse.pde.ManifestBuilder".equals(builders[i].getBuilderName()))
          {
            hasManifestBuilder = true;
          }
          if ("org.eclipse.pde.SchemaBuilder".equals(builders[i].getBuilderName()))
          {
            hasSchemaBuilder = true;
          }
        }
        if (!hasManifestBuilder)
        {
          ICommand [] oldBuilders = builders;
          builders = new ICommand [oldBuilders.length + 1];
          System.arraycopy(oldBuilders, 0, builders, 0, oldBuilders.length);
          builders[oldBuilders.length] = projectDescription.newCommand();
          builders[oldBuilders.length].setBuilderName("org.eclipse.pde.ManifestBuilder");
        }
        if (!hasSchemaBuilder)
        {
          ICommand [] oldBuilders = builders;
          builders = new ICommand [oldBuilders.length + 1];
          System.arraycopy(oldBuilders, 0, builders, 0, oldBuilders.length);
          builders[oldBuilders.length] = projectDescription.newCommand();
          builders[oldBuilders.length].setBuilderName("org.eclipse.pde.SchemaBuilder");
        }
        projectDescription.setBuildSpec(builders);

        project.open(new SubProgressMonitor(progressMonitor, 1));
        project.setDescription(projectDescription, new SubProgressMonitor(progressMonitor, 1));

        IContainer sourceContainer = project;
        if (javaSource.segmentCount() > 1)
        {
          sourceContainer = project.getFolder(javaSource.removeFirstSegments(1).makeAbsolute());
          if (!sourceContainer.exists())
          {
            ((IFolder)sourceContainer).create(false, true, new SubProgressMonitor(progressMonitor, 1));
          }
        }

        if (isInitiallyEmpty)
        {
          IClasspathEntry sourceClasspathEntry = 
            JavaCore.newSourceEntry(javaSource);
          for (Iterator i = classpathEntries.iterator(); i.hasNext(); )
          {
            IClasspathEntry classpathEntry = (IClasspathEntry)i.next();
            if (classpathEntry.getPath().isPrefixOf(javaSource))
            {
              i.remove();
            }
          }
          classpathEntries.add(0, sourceClasspathEntry);

          IClasspathEntry jreClasspathEntry =
            JavaCore.newVariableEntry
              (new Path(JavaRuntime.JRELIB_VARIABLE), new Path(JavaRuntime.JRESRC_VARIABLE), new Path(JavaRuntime.JRESRCROOT_VARIABLE));
          for (Iterator i = classpathEntries.iterator(); i.hasNext(); )
          {
            IClasspathEntry classpathEntry = (IClasspathEntry)i.next();
            if (classpathEntry.getPath().isPrefixOf(jreClasspathEntry.getPath()))
            {
              i.remove();
            }
          }
          classpathEntries.add(jreClasspathEntry);
        }

        if ((style & EMF_EMPTY_PROJECT_STYLE) == 0)
        {
          if ((style & EMF_PLUGIN_PROJECT_STYLE) != 0)
          {
            classpathEntries.add(JavaCore.newContainerEntry(new Path("org.eclipse.pde.core.requiredPlugins")));

            // Remove variables since the plugin.xml should provide the complete path information.
            //
            for (Iterator i = classpathEntries.iterator(); i.hasNext(); )
            {
              IClasspathEntry classpathEntry = (IClasspathEntry)i.next();
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
            addClasspathEntries(classpathEntries, "ECLIPSE_CORE_RUNTIME", "org.eclipse.core.runtime");
            addClasspathEntries(classpathEntries, "ECLIPSE_CORE_RESOURCES", "org.eclipse.core.resources");
            addClasspathEntries(classpathEntries, "EMF_COMMON", "org.eclipse.emf.common");
            addClasspathEntries(classpathEntries, "EMF_ECORE", "org.eclipse.emf.ecore");

            if ((style & EMF_XML_PROJECT_STYLE) != 0)
            {
              addClasspathEntries(classpathEntries, "EMF_ECORE_XMI", "org.eclipse.emf.ecore.xmi");
            }

            if ((style & EMF_MODEL_PROJECT_STYLE) == 0)
            {
              addClasspathEntries(classpathEntries, "EMF_EDIT", "org.eclipse.emf.edit");

              if ((style & EMF_EDIT_PROJECT_STYLE) == 0)
              {
                addClasspathEntries(classpathEntries, "ECLIPSE_SWT", "org.eclipse.swt");
                addClasspathEntries(classpathEntries, "ECLIPSE_JFACE", "org.eclipse.jface");
                addClasspathEntries(classpathEntries, "ECLIPSE_UI_VIEWS", "org.eclipse.ui.views");
                addClasspathEntries(classpathEntries, "ECLIPSE_UI_EDITORS", "org.eclipse.ui.editors");
                addClasspathEntries(classpathEntries, "ECLIPSE_UI_IDE", "org.eclipse.ui.ide");
                addClasspathEntries(classpathEntries, "ECLIPSE_UI_WORKBENCH", "org.eclipse.ui.workbench");
                addClasspathEntries(classpathEntries, "EMF_COMMON_UI", "org.eclipse.emf.common.ui");
                addClasspathEntries(classpathEntries, "EMF_EDIT_UI", "org.eclipse.emf.edit.ui");
                if ((style & EMF_XML_PROJECT_STYLE) == 0)
                {
                  addClasspathEntries(classpathEntries, "EMF_ECORE_XMI", "org.eclipse.emf.ecore.xmi");
                }
              }
            }

            if ((style & EMF_TESTS_PROJECT_STYLE) != 0)
            {
              addClasspathEntries(classpathEntries, "JUNIT", "org.junit");
            }

            if (pluginVariables != null)
            {
              for (Iterator i = pluginVariables.iterator(); i.hasNext(); )
              {
                Object variable = i.next();
                if (variable instanceof IClasspathEntry)
                {
                  classpathEntries.add(variable);
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
                  addClasspathEntries(classpathEntries, name, id);
                }
              }
            }
          }
        }

        javaProject.setRawClasspath
          ((IClasspathEntry [])classpathEntries.toArray(new IClasspathEntry [classpathEntries.size()]),
           new SubProgressMonitor(progressMonitor, 1));
      }

      if (isInitiallyEmpty)
      {
        javaProject.setOutputLocation(new Path("/" + javaSource.segment(0) + "/runtime"), new SubProgressMonitor(progressMonitor, 1));
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

    genModel.getModelPluginVariables().add("EMF_COMMONJ_SDO=org.eclipse.emf.commonj.sdo");
    genModel.getModelPluginVariables().add("EMF_ECORE_SDO=org.eclipse.emf.ecore.sdo");

    genModel.getStaticPackages().add("http://www.eclipse.org/emf/2003/SDO");
  }
  
  /**
   * This parses a code formatter profile file, recording the options it sepecifies in a map.
   */
  static class CodeFormatterProfileParser extends DefaultHandler
  {
    private Map options = null;

    private String SETTING = "setting";
    private String ID = "id";
    private String VALUE = "value";
    private String EMPTY = "";

    public void startDocument()
    {
      options = new HashMap();
    }

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

   public Map getOptions()
   {
     return options;
   }

   public static Map parse(String systemID)
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
     }
     return null;
   }
  }
}
