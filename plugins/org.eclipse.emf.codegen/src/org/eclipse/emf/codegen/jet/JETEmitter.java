/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: JETEmitter.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.jet;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.ILibrary;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;

import org.eclipse.emf.codegen.CodeGenPlugin;


/**
 * A convenience class for compiling and invoking a template dynamically.
 */
public class JETEmitter
{
  protected String projectName = ".JETEmitters";
  protected Method method;
  protected Object object;
  protected String [] templateURIPath;
  protected String templateURI;
  protected ClassLoader classLoader;
  protected String encoding;
  protected List classpathEntries = new ArrayList();

  /**
   * Creates an instance with the specified template URI.
   * @param templateURI the URI of a JET template.
   */
  public JETEmitter(String templateURI)
  {
    this.templateURI = templateURI;
    this.classLoader = JETEmitter.this.getClass().getClassLoader();
  }

  /**
   * Creates an instance with the specified template URI path and relative template URI.
   * The relative URI will be resolved against each path URI until a JET template is found.
   * @param templateURIPath a sequence of URIs that will be searched.
   * @param relativeTemplateURI the relative URI of a JET template.
   */
  public JETEmitter(String [] templateURIPath, String relativeTemplateURI)
  {
    this.templateURIPath = templateURIPath;
    this.templateURI = relativeTemplateURI;
    this.classLoader = JETEmitter.this.getClass().getClassLoader();
  }

  /**
   * Creates an instance with the specified template URI and class loader.
   * @param templateURI the URI of a JET template.
   * @param classLoader the class loader used to load classes when compiling the template.
   */
  public JETEmitter(String templateURI, ClassLoader classLoader)
  {
    this.templateURI = templateURI;
    this.classLoader = classLoader;
  }

  /**
   * Creates an instance with the specified template URI path, relative template URI, and class loader.
   * The relative URI will be resolved against each path URI until a JET template is found.
   * @param templateURIPath a sequence of URIs that will be searched.
   * @param relativeTemplateURI the relative URI of a JET template.
   * @param classLoader the class loader used to load classes when compiling the template.
   */
  public JETEmitter(String [] templateURIPath, String relativeTemplateURI, ClassLoader classLoader)
  {
    this.templateURIPath = templateURIPath;
    this.templateURI = relativeTemplateURI;
    this.classLoader = classLoader;
  }

  /**
   * Creates an instance with the specified template URI path, relative template URI, class loader, and encoding.
   * The relative URI will be resolved against each path URI until a JET template is found.
   * @param templateURIPath a sequence of URIs that will be searched.
   * @param relativeTemplateURI the relative URI of a JET template.
   * @param classLoader the class loader used to load classes when compiling the template.
   * @param encoding the encoding that will be used to read the templates.
   */
  public JETEmitter(String [] templateURIPath, String relativeTemplateURI, ClassLoader classLoader, String encoding)
  {
    this.templateURIPath = templateURIPath;
    this.templateURI = relativeTemplateURI;
    this.classLoader = classLoader;
    this.encoding = encoding;
  }

  /**
   * Returns the name of the project where JET templates will be compiled.
   * @return the name of the project where JET templates will be compiled.
   */
  public String getProjectName()
  {
    return projectName;
  }

  /**
   * Sets the name of the project where JET templates will be compiled.
   * @param projectName the name of the project.
   */
  public void setProjectName(String projectName)
  {
    this.projectName = projectName;
  }

  /**
   * Returns a list of classpath entries that will be added to the classpath of the internal {@link #getProjectName project}
   * where emitted JET templates are compiled.
   * <p>
   * This method must be called <b>before</b>
   * {@link #initialize initialize} or {@link #generate generate}
   * are called.
   * @param entry the entry to add to the classpath.
   * @return a list of classpath entries.
   */
  public List getClasspathEntries()
  {
    return classpathEntries;
  }

  /**
   * Returns the object used as input to the template.
   * @return the object used as input to the template.
   */
  public Object getObject()
  {
    return object;
  }

  /**
   * Sets the object used as input to the template.
   * @param object the object used as input to the template.
   */
  public void setObject(Object object)
  {
    this.object = object;
  }

  /**
   * Returns the method that will be invoked when {@link #generate generate} called.
   * @return the generator method.
   */
  public Method getMethod()
  {
    return method;
  }

  /**
   * Set the method that will be invoked when {@link #generate generate} called.
   * @param method the generator method.
   */
  public void setMethod(Method method)
  {
    this.method = method;
    if ((method.getModifiers() & Modifier.STATIC) == 0 && object == null)
    {
      try
      {
        object = method.getDeclaringClass().newInstance();
      }
      catch (IllegalAccessException exception)
      {
        exception.printStackTrace();
      }
      catch (InstantiationException exception)
      {
        exception.printStackTrace();
      }
    }
  }

  protected class MyJETCompiler extends JETCompiler
  {
    public MyJETCompiler(String templateURI) throws JETException
    {
      super(templateURI);
    }

    public MyJETCompiler(String templateURI, String encoding) throws JETException
    {
      super(templateURI, encoding);
    }

    public MyJETCompiler(String [] templateURIPath, String relativeTemplateURI) throws JETException
    {
      super(templateURIPath, relativeTemplateURI);
    }

    public MyJETCompiler(String [] templateURIPath, String relativeTemplateURI, String encoding) throws JETException
    {
      super(templateURIPath, relativeTemplateURI, encoding);
    }

    protected void handleNewSkeleton()
    {
      String packageName = skeleton.getPackageName();
      String skeletonClassName = skeleton.getClassName();
      String qualifiedSkeletonClassName = (packageName.length() == 0 ? "" : packageName + ".") + skeletonClassName;

      try
      {
        Class theClass = classLoader.loadClass(qualifiedSkeletonClassName);
        if (theClass != null)
        {
          skeleton.setClassName(skeletonClassName += "_");
        }
      }
      catch (Exception exception)
      {
      }
    }
  }

  /**
   * Compiles the template to {@link #setMethod set} the method will be invoked to generate template results.
   * @param progressMonitor the progress monitor for tracking progress.
   */
  public void initialize(IProgressMonitor progressMonitor) throws JETException
  {
    progressMonitor.beginTask("", 10);
    progressMonitor.subTask(CodeGenPlugin.getPlugin().getString("_UI_GeneratingJETEmitterFor_message", new Object [] { templateURI }));

    try
    {
      // This ensures that the JRE variables are initialized.
      //
      try
      {
        JavaRuntime.getDefaultVMInstall();
      }
      catch (Throwable throwable)
      {
        // This is kind of nasty to come here.
        //
        URL jreURL = 
          new URL(Platform.getPlugin("org.eclipse.emf.codegen").getDescriptor().getInstallURL(), "plugin.xml");
        IPath jrePath = new Path(Platform.asLocalURL(jreURL).getFile());
        jrePath = jrePath.removeLastSegments(1).append(new Path("../../jre/lib/rt.jar"));
        if (!jrePath.equals(JavaCore.getClasspathVariable(JavaRuntime.JRELIB_VARIABLE)))
        {
          JavaCore.setClasspathVariable(JavaRuntime.JRELIB_VARIABLE, jrePath, null);
        }
      }

      final JETCompiler jetCompiler = 
        templateURIPath == null ? 
          new MyJETCompiler(templateURI, encoding) :
          new MyJETCompiler(templateURIPath, templateURI, encoding);

      progressMonitor.subTask
        (CodeGenPlugin.getPlugin().getString("_UI_JETParsing_message", new Object [] { jetCompiler.getResolvedTemplateURI() }));
      jetCompiler.parse();
      progressMonitor.worked(1);

      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      jetCompiler.generate(outputStream);
      final InputStream contents = new ByteArrayInputStream(outputStream.toByteArray());

      final IWorkspace workspace = ResourcesPlugin.getWorkspace();
      IJavaModel javaModel= JavaCore.create(ResourcesPlugin.getWorkspace().getRoot());
      if (!javaModel.isOpen())
      {
        javaModel.open(new SubProgressMonitor(progressMonitor, 1));
      }
      else
      {
        progressMonitor.worked(1);
      }

      final IProject project = workspace.getRoot().getProject(getProjectName());
      progressMonitor.subTask
        (CodeGenPlugin.getPlugin().getString("_UI_JETPreparingProject_message", new Object [] { project.getName() }));

      IJavaProject javaProject;
      if (!project.exists())
      {
        progressMonitor.subTask("JET creating project " + project.getName());
        project.create(new SubProgressMonitor(progressMonitor, 1));
        progressMonitor.subTask
          (CodeGenPlugin.getPlugin().getString("_UI_JETCreatingProject_message", new Object [] { project.getName() }));
        IProjectDescription description = workspace.newProjectDescription(project.getName());
        description.setNatureIds(new String [] { JavaCore.NATURE_ID });
        description.setLocation(null);
        project.open(new SubProgressMonitor(progressMonitor, 1));
        project.setDescription(description, new SubProgressMonitor(progressMonitor, 1));

        javaProject = JavaCore.create(project);

        progressMonitor.subTask
          (CodeGenPlugin.getPlugin().getString("_UI_JETInitializingProject_message", new Object [] { project.getName() }));
        IClasspathEntry classpathEntry = 
          JavaCore.newSourceEntry(new Path("/" + project.getName() + "/src"));

        IClasspathEntry jreClasspathEntry = 
          JavaCore.newVariableEntry
            (new Path(JavaRuntime.JRELIB_VARIABLE), 
             new Path(JavaRuntime.JRESRC_VARIABLE), 
             new Path(JavaRuntime.JRESRCROOT_VARIABLE));

        List classpath = new ArrayList();
        classpath.add(classpathEntry);
        classpath.add(jreClasspathEntry);
        classpath.addAll(classpathEntries);

        IFolder sourceFolder = project.getFolder(new Path("src"));
        if (!sourceFolder.exists())
        {
          sourceFolder.create(false, true, new SubProgressMonitor(progressMonitor, 1));
        }
        IFolder runtimeFolder = project.getFolder(new Path("runtime"));
        if (!runtimeFolder.exists())
        {
          runtimeFolder.create(false, true, new SubProgressMonitor(progressMonitor, 1));
        }

        IClasspathEntry [] classpathEntryArray = (IClasspathEntry[])classpath.toArray(new IClasspathEntry [classpath.size()]);

        javaProject.setRawClasspath(classpathEntryArray, new SubProgressMonitor(progressMonitor, 1));

        javaProject.setOutputLocation(new Path("/" + project.getName() + "/runtime"), new SubProgressMonitor(progressMonitor, 1));

        javaProject.close();
      }
      else
      {
        project.open(new SubProgressMonitor(progressMonitor, 5));
        javaProject = JavaCore.create(project);
      }

      progressMonitor.subTask
        (CodeGenPlugin.getPlugin().getString("_UI_JETOpeningJavaProject_message", new Object [] { project.getName() }));
      javaProject.open(new SubProgressMonitor(progressMonitor, 1));

      IPackageFragmentRoot [] packageFragmentRoots = javaProject.getPackageFragmentRoots();
      IPackageFragmentRoot sourcePackageFragmentRoot = null;
      for (int j = 0; j < packageFragmentRoots.length; ++j)
      {
        IPackageFragmentRoot packageFragmentRoot = packageFragmentRoots[j];
        if (packageFragmentRoot.getKind() == IPackageFragmentRoot.K_SOURCE)
        {
          sourcePackageFragmentRoot = packageFragmentRoot;
          break;
        }
      }

      String packageName = jetCompiler.getSkeleton().getPackageName();
      StringTokenizer stringTokenizer = new StringTokenizer(packageName, ".");
      IProgressMonitor subProgressMonitor = new SubProgressMonitor(progressMonitor, 1);
      subProgressMonitor.beginTask("", stringTokenizer.countTokens() + 4);
      subProgressMonitor.subTask(CodeGenPlugin.getPlugin().getString("_UI_CreateTargetFile_message"));
      IContainer sourceContainer = (IContainer)sourcePackageFragmentRoot.getCorrespondingResource();
      while (stringTokenizer.hasMoreElements())
      {
        String folderName = stringTokenizer.nextToken();
        sourceContainer = sourceContainer.getFolder(new Path(folderName));
        if (!sourceContainer.exists())
        {
          ((IFolder)sourceContainer).create(false, true, new SubProgressMonitor(subProgressMonitor, 1));
        }
      }
      IFile targetFile = ((IContainer)sourceContainer).getFile(new Path(jetCompiler.getSkeleton().getClassName() + ".java"));
      if (!targetFile.exists())
      {
        subProgressMonitor.subTask
          (CodeGenPlugin.getPlugin().getString("_UI_JETCreating_message", new Object [] { targetFile.getFullPath() }));
        targetFile.create(contents, true, new SubProgressMonitor(subProgressMonitor, 1));
      }
      else
      {
        subProgressMonitor.subTask
          (CodeGenPlugin.getPlugin().getString("_UI_JETUpdating_message", new Object [] { targetFile.getFullPath() }));
        targetFile.setContents(contents, true, true, new SubProgressMonitor(subProgressMonitor, 1));
      }

      subProgressMonitor.subTask
        (CodeGenPlugin.getPlugin().getString("_UI_JETBuilding_message", new Object [] { project.getName() }));
      project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, new SubProgressMonitor(subProgressMonitor, 1));

      IMarker [] markers = targetFile.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
      boolean errors = false;
      for (int i = 0; i < markers.length; ++i)
      {
        IMarker marker = markers[i];
        if (marker.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO) == IMarker.SEVERITY_ERROR)
        {
          errors = true;
          subProgressMonitor.subTask
            (marker.getAttribute(IMarker.MESSAGE) + " : " + 
               (CodeGenPlugin.getPlugin().getString
                 ("jet.mark.file.line", 
                  new Object []
                  {
                    targetFile.getLocation(), 
                    marker.getAttribute(IMarker.LINE_NUMBER)
                  })));
        }
      }

      if (!errors)
      {
        IContainer targetContainer = workspace.getRoot().getFolder(javaProject.getOutputLocation());

        subProgressMonitor.subTask
          (CodeGenPlugin.getPlugin().getString
             ("_UI_JETLoadingClass_message", new Object [] { jetCompiler.getSkeleton().getClassName() + ".class" }));

        // Construct a proper URL for relative lookup.
        //
        URL url = new File(project.getLocation() + "/" + javaProject.getOutputLocation().removeFirstSegments(1) + "/").toURL();
        URLClassLoader theClassLoader = new URLClassLoader(new URL [] { url }, classLoader);
        Class theClass = 
          theClassLoader.loadClass
            ((packageName.length() == 0 ? "" : packageName + ".") + jetCompiler.getSkeleton().getClassName());
        String methodName = jetCompiler.getSkeleton().getMethodName();
        Method [] methods = theClass.getDeclaredMethods();
        for (int i = 0; i < methods.length; ++i)
        {
          if (methods[i].getName().equals(methodName))
          {
            setMethod(methods[i]);
            break;
          }
        }
      }

      subProgressMonitor.done();
    }
    catch (CoreException exception)
    {
      throw new JETException(exception);
    }
    catch (Exception exception)
    {
      throw new JETException(exception);
    }
    finally
    {
      progressMonitor.done();
    }
  }

  /**
   * Registers the specified classpath variable in the workspace  
   * and adds a classpath entry to the {@link #getClasspathEntries() classpath entry list}.
   * The variable is bound to the first runtime library JAR file in the list
   * of runtime libraries of the specified plugin.
   * When {@link #generate generate} is called 
   * and it needs to generate the {@link #getMethod method} to invoke,
   * it will call {@link #initialize initialize} 
   * which will add the classpath entries to the {@link #getProjectName project} created to hold and compile the emitted template.
   * <p>
   * This method must be called <b>before</b>
   * {@link #initialize initialize} or {@link #generate generate}
   * are called.
   * <p>
   * The specified plugin ID must be the ID of an existing plugin.
   * The referenced plugin must have at least one 
   * runtime library JAR file in its plugin descriptor.
   * If the plugin descriptor's list of runtime libraries contains more than one JAR file, 
   * the classpath variable will be bound to the <b>first</b>
   * library in the list.
   * @param variableName name of the classpath variable
   * @param pluginID the ID of an existing plugin 
   */
  public void addVariable(String variableName, String pluginID) throws JETException
  {
    IPluginDescriptor descriptor = Platform.getPlugin(pluginID).getDescriptor();
    ILibrary[] libraries = descriptor.getRuntimeLibraries();
    for (int i = 0; i < libraries.length; ++i)
    {
      if (libraries[i].getType().equals(ILibrary.CODE))
      {
        try
        {
          URL url = new URL(descriptor.getInstallURL(), libraries[i].getPath().toString());
          IPath path = new Path(Platform.asLocalURL(url).getFile());
          if (!path.equals(JavaCore.getClasspathVariable(variableName)))
          {
            JavaCore.setClasspathVariable(variableName, path, null);
          }
        } 
        catch (MalformedURLException exception)
        {
          throw new JETException(exception);
        }
        catch (JavaModelException exception)
        {
          throw new JETException(exception);
        } 
        catch (IOException exception)
        {
          throw new JETException(exception);
        }
        break;
      }
    }

    IClasspathEntry entry = JavaCore.newVariableEntry(new Path(variableName), null, null);
    getClasspathEntries().add(entry);
  }

  /**
   * Invokes the emitter method on the compiled template and returns the result.
   * @return the template result.
   */
  public String generate(IProgressMonitor progressMonitor, Object [] arguments) throws JETException
  {
    if (method == null)
    {
      initialize(progressMonitor);
    }

    String result = "";
    if (method != null)
    {
      try
      {
        result = (String)method.invoke(object, arguments);
      }
      catch (IllegalAccessException exception)
      {
        throw new JETException(exception);
      }
      catch (InvocationTargetException exception)
      {
        throw new JETException(exception);
      }
    }
    return result;
  }
}
