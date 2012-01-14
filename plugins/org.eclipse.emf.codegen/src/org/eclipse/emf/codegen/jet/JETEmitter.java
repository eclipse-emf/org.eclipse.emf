/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.jet;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.osgi.framework.Bundle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

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
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;

import org.eclipse.emf.codegen.CodeGenPlugin;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;


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
  protected List<IClasspathEntry> classpathEntries = new ArrayList<IClasspathEntry>();
  protected Map<String, String> javaOptions = new HashMap<String, String>();

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
   * {@link #initialize(Monitor) initialize} or {@link #generate(Monitor, Object[]) generate}
   * are called.
   * @return a list of classpath entries.
   */
  public List<IClasspathEntry> getClasspathEntries()
  {
    return classpathEntries;
  }

  /**
   * Returns options that will be {@link IJavaProject#setOption(String, String) applied} to the Java project the first time it's created.
   * @return the Java options.
   */
  public Map<String, String> getJavaOptions()
  {
    return javaOptions;
  }

  /**
   * Returns the object used as the target for the template.
   * @return the object used as target for the template.
   */
  public Object getObject()
  {
    return object;
  }

  /**
   * Returns the object used as the target for the template;
   * it ensures that the returned object is using the given lineDelimiter 
   * and creates a new one if necessary.
   * @return the object used as target for the template.
   * @since 2.3
   */
  public Object getObject(String lineDelimiter)
  {
    if (lineDelimiter != null)
    {
      if (object != null)
      {
        Class<?> javaClass = object.getClass();
        try
        {
          Field field = javaClass.getField("NL");
          Object nl = field.get(object);
          if (lineDelimiter.equals(nl))
          {
            return object;
          }
          else
          {
            object = null;
          }
        }
        catch (Throwable exception)
        {
          CodeGenPlugin.INSTANCE.log(exception);
        }
      }
      
      if (object == null && method != null)
      {
        Class<?> javaClass = method.getDeclaringClass();
        try
        {
          Method method = javaClass.getMethod("create", String.class);
          object = method.invoke(null, lineDelimiter);
        }
        catch (Throwable exception)
        {
          CodeGenPlugin.INSTANCE.log(exception);
        }
      }
    }
    return object;
  }

  /**
   * Sets the object used as the target of the template.
   * @param object the object used as target of the template.
   */
  public void setObject(Object object)
  {
    this.object = object;
  }

  /**
   * Returns the method that will be invoked when {@link #generate(Monitor, Object[]) generate} called.
   * @return the generator method.
   */
  public Method getMethod()
  {
    return method;
  }

  /**
   * Set the method that will be invoked when {@link #generate(Monitor, Object[]) generate} called.
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
        CodeGenPlugin.INSTANCE.log(exception);
      }
      catch (InstantiationException exception)
      {
        CodeGenPlugin.INSTANCE.log(exception);
      }
    }
  }

  protected static class MyBaseJETCompiler extends JETCompiler
  {
    protected ClassLoader classLoader;

    public MyBaseJETCompiler(String templateURI, ClassLoader classLoader) throws JETException
    {
      super(templateURI);
      this.classLoader = classLoader;
    }

    public MyBaseJETCompiler(String templateURI, String encoding, ClassLoader classLoader) throws JETException
    {
      super(templateURI, encoding);
      this.classLoader = classLoader;
    }

    public MyBaseJETCompiler(String [] templateURIPath, String relativeTemplateURI, ClassLoader classLoader) throws JETException
    {
      super(templateURIPath, relativeTemplateURI);
      this.classLoader = classLoader;
    }

    public MyBaseJETCompiler(String [] templateURIPath, String relativeTemplateURI, String encoding, ClassLoader classLoader) throws JETException
    {
      super(templateURIPath, relativeTemplateURI, encoding);
      this.classLoader = classLoader;
    }

    @Override
    protected void handleNewSkeleton()
    {
      String packageName = skeleton.getPackageName();
      String skeletonClassName = skeleton.getClassName();
      String qualifiedSkeletonClassName = (packageName.length() == 0 ? "" : packageName + ".") + skeletonClassName;

      if (classLoader != null)
      {
        try
        {
          Class<?> theClass = classLoader.loadClass(qualifiedSkeletonClassName);
          if (theClass != null)
          {
            skeleton.setClassName(skeletonClassName += "_");
          }
        }
        catch (Exception exception)
        {
          // Ignore
        }
      }
    }
  }
  
  protected class MyJETCompiler extends MyBaseJETCompiler
  {
    public MyJETCompiler(String templateURI) throws JETException
    {
      super(templateURI, JETEmitter.this.classLoader);
    }

    public MyJETCompiler(String templateURI, String encoding) throws JETException
    {
      super(templateURI, encoding, JETEmitter.this.classLoader);
    }

    public MyJETCompiler(String [] templateURIPath, String relativeTemplateURI) throws JETException
    {
      super(templateURIPath, relativeTemplateURI, JETEmitter.this.classLoader);
    }

    public MyJETCompiler(String [] templateURIPath, String relativeTemplateURI, String encoding) throws JETException
    {
      super(templateURIPath, relativeTemplateURI, encoding, JETEmitter.this.classLoader);
    }
  }

  /**
   * Compiles the template to {@link #setMethod set} the method will be invoked to generate template results.
   * @param progressMonitor the progress monitor for tracking progress.
   */
  public void initialize(IProgressMonitor progressMonitor) throws JETException
  {
    initialize(BasicMonitor.toMonitor(progressMonitor));
  }
  
  /**
   * Compiles the template to {@link #setMethod set} the method will be invoked to generate template results.
   * @param progressMonitor the progress monitor for tracking progress.
   */
  public void initialize(Monitor progressMonitor) throws JETException
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      EclipseHelper.initialize(progressMonitor, this);
    }
  }
  
  /**
   * Registers the specified classpath variable in the workspace  
   * and adds a classpath entry to the {@link #getClasspathEntries() classpath entry list}.
   * The variable is bound to the first runtime library JAR file in the list
   * of runtime libraries of the specified plugin.
   * When {@link #generate(Monitor, Object[]) generate} is called 
   * and it needs to generate the {@link #getMethod method} to invoke,
   * it will call {@link #initialize(Monitor) initialize} 
   * which will add the classpath entries to the {@link #getProjectName project} created to hold and compile the emitted template.
   * <p>
   * This method must be called <b>before</b>
   * {@link #initialize(Monitor) initialize} or {@link #generate(Monitor, Object[]) generate}
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
    if (EMFPlugin.IS_ECLIPSE_RUNNING && method == null)
    {
      EclipseHelper.addVariable(this, variableName, pluginID);
    }
  }

  /**
   * Invokes the emitter method on the compiled template and returns the result.
   * @return the template result.
   */
  public String generate(IProgressMonitor progressMonitor, Object [] arguments) throws JETException
  {
    return generate(BasicMonitor.toMonitor(progressMonitor), arguments);
  }
  
  /**
   * Invokes the emitter method on the compiled template and returns the result.
   * @return the template result.
   */
  public String generate(Monitor progressMonitor, Object [] arguments) throws JETException
  {
    return generate(progressMonitor, arguments, null);
  }

  /**
   * Invokes the emitter method on the compiled template and returns the result.
   * @return the template result.
   * @since 2.3
   */
  public String generate(Monitor progressMonitor, Object [] arguments, String lineDelimiter) throws JETException
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
        result = (String)method.invoke(getObject(lineDelimiter), arguments);
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

  private static class EclipseHelper
  {
    public static void initialize(Monitor monitor, final JETEmitter jetEmitter) throws JETException
    {
      IProgressMonitor progressMonitor = BasicMonitor.toIProgressMonitor(monitor);
      progressMonitor.beginTask("", 10);
      progressMonitor.subTask(CodeGenPlugin.getPlugin().getString("_UI_GeneratingJETEmitterFor_message", new Object [] { jetEmitter.templateURI }));

      final IWorkspace workspace = ResourcesPlugin.getWorkspace();
      IJavaModel javaModel = JavaCore.create(ResourcesPlugin.getWorkspace().getRoot());
  
      try
      {
        final JETCompiler jetCompiler = 
          jetEmitter.templateURIPath == null ? 
            new MyBaseJETCompiler(jetEmitter.templateURI, jetEmitter.encoding, jetEmitter.classLoader) :
            new MyBaseJETCompiler(jetEmitter.templateURIPath, jetEmitter.templateURI, jetEmitter.encoding, jetEmitter.classLoader);
  
        progressMonitor.subTask
          (CodeGenPlugin.getPlugin().getString("_UI_JETParsing_message", new Object [] { jetCompiler.getResolvedTemplateURI() }));
        jetCompiler.parse();
        progressMonitor.worked(1);

        String packageName = jetCompiler.getSkeleton().getPackageName();

        if (jetEmitter.templateURIPath != null)
        {
          URI templateURI = URI.createURI(jetEmitter.templateURIPath[0]);
          URLClassLoader theClassLoader = null;
          if (templateURI.isPlatformResource())
          {
            // If the template path points at a project with a JET Nature, 
            // then we will assume that the templates we want to use are already compiled in this plugin Java project.
            // 
            IProject project = workspace.getRoot().getProject(templateURI.segment(1));
            if (JETNature.getRuntime(project) != null)
            {
                List<URL> urls = new ArrayList<URL>();
                
                // Compute the URL for where the classes for this project will be located.
                //
                IJavaProject javaProject = JavaCore.create(project);
                urls.add(new File(project.getLocation() + "/" + javaProject.getOutputLocation().removeFirstSegments(1) + "/").toURI().toURL());
                
                // Compute the URLs for all the output folder of all the project dependencies.
                //
                for (IClasspathEntry classpathEntry :  javaProject.getResolvedClasspath(true))
                {
                  if (classpathEntry.getEntryKind() == IClasspathEntry.CPE_PROJECT)
                  {
                    IPath projectPath = classpathEntry.getPath();
                    IProject otherProject = workspace.getRoot().getProject(projectPath.segment(0));
                    IJavaProject otherJavaProject = JavaCore.create(otherProject);
                    urls.add(new File(otherProject.getLocation() + "/" + otherJavaProject.getOutputLocation().removeFirstSegments(1) + "/").toURI().toURL());
                  }
                }
  
              // Define a class loader that will look in the URLs first, 
              // and if it doesn't find the class there, uses the emitter's loader.
              //
              theClassLoader = 
                new URLClassLoader(urls.toArray(new URL [0]))
                {
                  @Override
                  public Class<?> loadClass(String className) throws ClassNotFoundException
                  {
                    try
                    {
                      return super.loadClass(className);
                    }
                    catch (ClassNotFoundException classNotFoundException)
                    {
                      return jetEmitter.classLoader.loadClass(className);
                    }
                  }
                };
            }
          }
          else if (templateURI.isPlatformPlugin())
          {
            final Bundle bundle = Platform.getBundle(templateURI.segment(1));
            if (bundle != null)
            {
              // Define a class loader that will look up the class in the bundle,
              // and if it doesn't find it there, will look in the parent.
              //
              theClassLoader = 
                new URLClassLoader(new URL [0], jetEmitter.classLoader)
                {
                  @Override
                  public Class<?> loadClass(String className) throws ClassNotFoundException
                  {
                    try
                    {
                      return bundle.loadClass(className);
                    }
                    catch (ClassNotFoundException classNotFoundException)
                    {
                      return super.loadClass(className);
                    }
                  }
                };
            }
          }

          if (theClassLoader != null)
          {
            // Strip off the trailing "_" and load that class.
            //
            String className = (packageName.length() == 0 ? "" : packageName + ".") + jetCompiler.getSkeleton().getClassName();
            if (className.endsWith("_"))
            {
              className = className.substring(0, className.length() - 1);
            }

            try
            {
              Class<?> theClass = theClassLoader.loadClass(className);

              // Check that the class is actually different from the one that's directly visible to the JETEmitter.
              //
              Class<?> theOtherClass = null;
              try
              {
                 theOtherClass = jetEmitter.classLoader.loadClass(className);
              }
              catch (ClassNotFoundException exception)
              {
                // Ignore.
              }
              if (theClass != theOtherClass)
              {
                String methodName = jetCompiler.getSkeleton().getMethodName();
                Method [] methods = theClass.getDeclaredMethods();
                for (int i = 0; i < methods.length; ++i)
                {
                  if (methods[i].getName().equals(methodName))
                  {
                    jetEmitter.setMethod(methods[i]);
                    break;
                  }
                }
  
                // Don't do any of the other normally dynamic JETEmitter project processing.
                //
                return;
              }
            }
            catch (ClassNotFoundException exception)
            {
              // Continue processing dynamically as normal.
            }
          }
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        jetCompiler.generate(outputStream);
        final InputStream contents = new ByteArrayInputStream(outputStream.toByteArray());
  
        if (!javaModel.isOpen())
        {
          javaModel.open(new SubProgressMonitor(progressMonitor, 1));
        }
        else
        {
          progressMonitor.worked(1);
        }
  
        final IProject project = workspace.getRoot().getProject(jetEmitter.getProjectName());
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

          for (Map.Entry<String, String> option : jetEmitter.getJavaOptions().entrySet())
          {
            javaProject.setOption(option.getKey(), option.getValue());
          }
        }
        else
        {
          project.open(new SubProgressMonitor(progressMonitor, 5));
          IProjectDescription description = project.getDescription();
          description.setNatureIds(new String [] { JavaCore.NATURE_ID });
          project.setDescription(description, new SubProgressMonitor(progressMonitor, 1));
          javaProject = JavaCore.create(project);
        }

        // Get the existing classpath and remove the project root if necessary.
        // Any new non-duplicate entries will be added to this.
        //
        List<IClasspathEntry> classpath = new UniqueEList<IClasspathEntry>(Arrays.asList(javaProject.getRawClasspath()));
        for (int i = 0, len = classpath.size(); i < len; i++)
        {
          IClasspathEntry entry = classpath.get(i);
          if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE && ("/" + project.getName()).equals(entry.getPath().toString()))
          {
            classpath.remove(i);
          }
        }

        // Add the new entries, including source, JRE container, and added variables and classpath containers. 
        //
        progressMonitor.subTask
          (CodeGenPlugin.getPlugin().getString("_UI_JETInitializingProject_message", new Object [] { project.getName() }));
        IClasspathEntry classpathEntry = 
          JavaCore.newSourceEntry(new Path("/" + project.getName() + "/src"));

        IClasspathEntry jreClasspathEntry = JavaCore.newContainerEntry(new Path("org.eclipse.jdt.launching.JRE_CONTAINER"));

        classpath.add(classpathEntry);
        classpath.add(jreClasspathEntry);
        classpath.addAll(jetEmitter.classpathEntries);
  
        IFolder sourceFolder = project.getFolder(new Path("src"));
        if (!sourceFolder.exists())
        {
          sourceFolder.create(false, true, new SubProgressMonitor(progressMonitor, 1));
        }
        IFolder runtimeFolder = project.getFolder(new Path("bin"));
        if (!runtimeFolder.exists())
        {
          runtimeFolder.create(false, true, new SubProgressMonitor(progressMonitor, 1));
        }
  
        javaProject.setRawClasspath(classpath.toArray(new IClasspathEntry[classpath.size()]), new SubProgressMonitor(progressMonitor, 1));
  
        javaProject.setOutputLocation(new Path("/" + project.getName() + "/bin"), new SubProgressMonitor(progressMonitor, 1));
  
        javaProject.close();
  
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
  
        StringTokenizer stringTokenizer = new StringTokenizer(packageName, ".");
        IProgressMonitor subProgressMonitor = new SubProgressMonitor(progressMonitor, 1);
        subProgressMonitor.beginTask("", stringTokenizer.countTokens() + 4);
        subProgressMonitor.subTask(CodeGenPlugin.getPlugin().getString("_UI_CreateTargetFile_message"));
        IContainer sourceContainer = sourcePackageFragmentRoot == null ? project : (IContainer)sourcePackageFragmentRoot.getCorrespondingResource();
        while (stringTokenizer.hasMoreElements())
        {
          String folderName = stringTokenizer.nextToken();
          sourceContainer = sourceContainer.getFolder(new Path(folderName));
          if (!sourceContainer.exists())
          {
            ((IFolder)sourceContainer).create(false, true, new SubProgressMonitor(subProgressMonitor, 1));
          }
        }
        IFile targetFile = sourceContainer.getFile(new Path(jetCompiler.getSkeleton().getClassName() + ".java"));
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
          subProgressMonitor.subTask
            (CodeGenPlugin.getPlugin().getString
               ("_UI_JETLoadingClass_message", new Object [] { jetCompiler.getSkeleton().getClassName() + ".class" }));
  
          // Construct a proper URL for relative lookup.
          //
          List<URL> urls = new ArrayList<URL>();
          urls.add(new File(project.getLocation() + "/" + javaProject.getOutputLocation().removeFirstSegments(1) + "/").toURI().toURL());
          
          // Determine all the bundles that this project depends on.
          //
          final Set<Bundle> bundles = new HashSet<Bundle>();
          LOOP:
          for (IClasspathEntry jetEmitterClasspathEntry :  jetEmitter.getClasspathEntries())
          {
            IClasspathAttribute [] classpathAttributes = jetEmitterClasspathEntry.getExtraAttributes();
            if (classpathAttributes != null)
            {
              for (IClasspathAttribute classpathAttribute : classpathAttributes)
              {
                if (classpathAttribute.getName().equals(CodeGenUtil.EclipseUtil.PLUGIN_ID_CLASSPATH_ATTRIBUTE_NAME))
                {
                  Bundle bundle = Platform.getBundle(classpathAttribute.getValue());
                  if (bundle != null)
                  {
                    bundles.add(bundle);
                    continue LOOP;
                  }
                }
              }
            }
            // For any entry that doesn't correspond to a plugin in the running JVM, compute a URL for the classes.
            //
            urls.add(new URL("platform:/resource" + jetEmitterClasspathEntry.getPath() + "/"));
          }
          
          // Define a class loader that looks up classes using the URLs or the parent class loader,
          // and failing those, tries to look up the class in each bundle in the running JVM.
          //
          URLClassLoader theClassLoader = 
            new URLClassLoader(urls.toArray(new URL [0]), jetEmitter.classLoader)
            {
              @Override
              public Class<?> loadClass(String className) throws ClassNotFoundException
              {
                try
                {
                  return super.loadClass(className);
                }
                catch (ClassNotFoundException exception)
                {
                  for (Bundle bundle : bundles)
                  {
                    try
                    {
                      return bundle.loadClass(className);
                    }
                    catch (ClassNotFoundException exception2)
                    {
                      // Ignore because we'll rethrow the original exception eventually.
                    }
                  }
                  throw exception;
                }
              }
            };
          Class<?> theClass = 
            theClassLoader.loadClass
              ((packageName.length() == 0 ? "" : packageName + ".") + jetCompiler.getSkeleton().getClassName());
          String methodName = jetCompiler.getSkeleton().getMethodName();
          Method [] methods = theClass.getDeclaredMethods();
          for (int i = 0; i < methods.length; ++i)
          {
            if (methods[i].getName().equals(methodName))
            {
              jetEmitter.setMethod(methods[i]);
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
    
    public static void addVariable(JETEmitter jetEmitter, String variableName, String pluginID) throws JETException
    {
      Bundle bundle = Platform.getBundle(pluginID);
      URL classpathURL = bundle != null && Platform.inDevelopmentMode() ? bundle.getEntry(".classpath") : null;

      // If it doesn't correspond to a bundle, then try to process it as a project in the workspace.
      //
      if (bundle == null)
      {
        try
        {
          IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(pluginID);
          if (project != null)
          {
            classpathURL = new File(project.getLocation()+ "/.classpath").toURI().toURL();
          }
        }
        catch (MalformedURLException exception)
        {
          throw new JETException(exception);
        }
      }
      boolean addClasspathEntries = true;
      if (classpathURL != null)
      {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        documentBuilderFactory.setValidating(false);
        try
        {
          DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
          Document document = documentBuilder.parse(new InputSource(classpathURL.toString()));
          for (Node child = document.getDocumentElement().getFirstChild(); child != null; child = child.getNextSibling())
          {
            if (child.getNodeType() == Node.ELEMENT_NODE)
            {
              Element classpathEntryElement = (Element)child;
              if ("classpathentry".equals(classpathEntryElement.getNodeName()) &&
                  "output".equals(classpathEntryElement.getAttribute("kind")))
              {
                URI uri = URI.createURI(classpathEntryElement.getAttribute("path")).resolve(URI.createURI(classpathURL.toString()));
                IWorkspace workspace = ResourcesPlugin.getWorkspace();
                IProject project = workspace.getRoot().getProject(jetEmitter.getProjectName());
                if (!project.exists())
                {
                  project.create(new NullProgressMonitor());
                }
                if (!project.isOpen())
                {
                  project.open(new NullProgressMonitor());
                }
                IFolder folder = project.getFolder("." + pluginID);
                if (!folder.exists())
                {
                  folder.createLink
                    (new Path(CommonPlugin.asLocalURI(uri).toFileString()).removeTrailingSeparator(),
                     IResource.ALLOW_MISSING_LOCAL, 
                     new NullProgressMonitor());
                }
                folder.refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());
                IPath path = folder.getFullPath();
                IClasspathEntry newLibraryEntry = 
                  JavaCore.newLibraryEntry
                    (path, 
                     null, 
                     null, 
                     null, 
                     bundle == null ? 
                        new IClasspathAttribute[0] : 
                        new IClasspathAttribute[] { JavaCore.newClasspathAttribute(CodeGenUtil.EclipseUtil.PLUGIN_ID_CLASSPATH_ATTRIBUTE_NAME, pluginID) }, 
                     true);
                jetEmitter.getClasspathEntries().add(newLibraryEntry);
                addClasspathEntries = false;
                break;
              }
            }
          }
        }
        catch (Exception exception)
        {
          CodeGenPlugin.INSTANCE.log(exception);
        }
      }
      if (addClasspathEntries)
      {
        CodeGenUtil.EclipseUtil.addClasspathEntries(jetEmitter.getClasspathEntries(), variableName, pluginID);
      }
    }
  }
}
