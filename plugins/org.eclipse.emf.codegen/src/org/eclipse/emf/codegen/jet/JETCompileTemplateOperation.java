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


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.CodeGenPlugin;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class JETCompileTemplateOperation implements IWorkspaceRunnable
{
  /**
   * @since 2.19
   */
  public static final String JET_PROBLEM = "org.eclipse.emf.codegen.jetProblem";

  protected static final String JET_EXTENSION = "jet";

  protected IProject project;

  protected Collection<?> containers;

  protected List<Object> files = new ArrayList<Object>();

  protected boolean inBuild;

  /**
   * @since 2.19
   */
  protected JETCompilationMonitor compilationMonitor;

  private final State newState = new State();

  /**
   * Creates an instance given the collection of resources.
   */
  public JETCompileTemplateOperation(IProject project, Collection<?> containers) throws CoreException
  {
    this.project = project;
    this.containers = containers;
    for (Object container : containers)
    {
      if (container instanceof IContainer)
      {
        consider((IContainer)container);
      }
      else
      {
        consider(container.toString());
      }
    }
  }

  /**
   * Creates an instance given the collection of resources.
   */
  public JETCompileTemplateOperation(IProject project, Collection<?> containers, Collection<?> resources) throws CoreException
  {
    this.project = project;
    this.containers = containers;
    for (Object object : resources)
    {
      if (object instanceof IFile)
      {
        IFile file = (IFile)object;
        for (IContainer container = file.getParent(); container != null; container = container.getParent())
        {
          if (containers.contains(container))
          {
            consider(file);
            break;
          }
        }
      }
      else if (object instanceof IContainer)
      {
        for (IContainer container = (IContainer)object; container != null; container = container.getParent())
        {
          if (containers.contains(container))
          {
            consider(container);
            break;
          }
        }
      }
    }
  }

  /**
   * @since 2.19
   */
  public JETCompileTemplateOperation(IFile file, JETCompilationMonitor compilationMonitor) throws CoreException
  {
    this(file.getProject(), getTemplateContainers(file.getProject()), Collections.singleton(file));
    this.compilationMonitor = compilationMonitor;
  }

  private static List<Object> getTemplateContainers(IProject project)
  {
    JETNature nature = JETNature.getRuntime(project);
    return nature == null ? Collections.emptyList() : nature.getTemplateContainers();
  }

  State getNewState()
  {
    return newState;
  }

  /**
   * @since 2.19
   */
  public List<Object> getFiles()
  {
    return Collections.unmodifiableList(files);
  }

  /**
   * Returns true if there are files to compile.
   */
  public boolean shouldCompile()
  {
    return !files.isEmpty();
  }

  /**
   * Adds the URI.
   */
  protected void consider(String uri)
  {
    URI baseURI = URI.createURI(uri);
    URI localURI = CommonPlugin.asLocalURI(baseURI);
    if (localURI.isFile() && !localURI.isRelative())
    {
      File file = new File(localURI.toFileString());
      if (file.isDirectory() && !uri.endsWith("/"))
      {
        baseURI = URI.createURI(uri + "/");
      }
      consider(baseURI, localURI, new File(localURI.toFileString()));
    }
  }

  protected void consider(URI baseURI, URI localURI, File file)
  {
    if (file.isDirectory())
    {
      File[] files = file.listFiles();
      for (int i = 0; i < files.length; ++i)
      {
        consider(baseURI, localURI, files[i]);
      }
    }
    else if (file.isFile() && file.getName().endsWith(JET_EXTENSION) && file.getName().indexOf('.') != -1)
    {
      files.add(URI.createFileURI(file.getAbsolutePath()).deresolve(localURI).resolve(baseURI));
    }
  }

  /**
   * Adds the file to {@link #files} the file ends with the {@link #JET_EXTENSION} extension.
   */
  protected void consider(IFile file)
  {
    if (file.getFileExtension() != null && file.getFileExtension().endsWith(JET_EXTENSION))
    {
      files.add(file);
    }
  }

  /**
   * Considers all the files of a container and all its subcontainer.
   */
  protected void consider(IContainer container) throws CoreException
  {
    if (container.isAccessible())
    {
      IResource[] children = container.members();
      if (children != null)
      {
        for (int i = 0; i < children.length; ++i)
        {
          IResource resource = children[i];
          if (resource instanceof IFile)
          {
            consider((IFile)resource);
          }
          else if (resource instanceof IContainer)
          {
            consider((IContainer)resource);
          }
        }
      }
    }
  }

  /**
   */
  public void run(IProgressMonitor progressMonitor) throws CoreException
  {
    try
    {
      progressMonitor.beginTask("", 3 * files.size());
      progressMonitor.subTask(CodeGenPlugin.getPlugin().getString("_UI_JETCompilingTemplates_message"));

      IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
      Collection<IProject> jetProjects = new HashSet<IProject>();

      for (Object file : files)
      {
        IFile workspaceFile = file instanceof IFile ? (IFile)file : null;
        String fileName = workspaceFile != null ? workspaceFile.getName() : file.toString();

        progressMonitor.subTask(CodeGenPlugin.getPlugin().getString("_UI_JETCompile_message", new Object []{ fileName }));

        JETNature nature = JETNature.getRuntime(project);
        if (nature == null)
        {
          break;
        }

        IContainer directory = nature.getJavaSourceContainer();

        // Only create the source container if we are not monitoring compilation and the folder doesn't already exist.
        if (compilationMonitor == null && !directory.exists())
        {
          IFolder sourceFolder = project.getFolder(directory.getProjectRelativePath());
          sourceFolder.create(true, true, BasicMonitor.subProgress(progressMonitor, 1));

          IJavaProject javaProject = JavaCore.create(sourceFolder.getProject());
          ArrayList<IClasspathEntry> classPath = new ArrayList<IClasspathEntry>(Arrays.asList(javaProject.getRawClasspath()));
          classPath.add(JavaCore.newSourceEntry(sourceFolder.getFullPath()));
          javaProject.setRawClasspath(classPath.toArray(new IClasspathEntry [classPath.size()]), BasicMonitor.subProgress(progressMonitor, 1));
        }

        IPath filePath = workspaceFile != null ? workspaceFile.getFullPath() : new Path(file.toString());
        List<Object> templateContainers = nature.getTemplateContainers();
        List<Object> templateSourceContainers = nature.getTemplateSourceContainers();

        List<String> containerLocations = new ArrayList<String>();
        for (Object container : templateContainers)
        {
          if (container instanceof IContainer)
          {
            containerLocations.add(URI.createPlatformResourceURI(((IContainer)container).getFullPath().toString(), true).toString());
          }
          else
          {
            containerLocations.add(container.toString());
          }
        }

        // Locate the first source container that contains the file.
        Set<String> relativePaths = new LinkedHashSet<String>();
        for (Object container : templateSourceContainers)
        {
          IPath containerPath = container instanceof IContainer ? ((IContainer)container).getFullPath() : new Path(container.toString());
          if (containerPath.isPrefixOf(filePath))
          {
            String relativePath = filePath.removeFirstSegments(containerPath.segmentCount()).setDevice(null).toString();
            if (relativePaths.add(relativePath))
            {
              break;
            }
          }
        }

        // If we are monitoring compilation, always compile the file, so synthesize the necessary information.
        boolean isNotOnContainerPath = relativePaths.isEmpty();
        if (isNotOnContainerPath && compilationMonitor != null)
        {
          // The relative path will be the last segment.
          String relativePath = filePath.lastSegment();
          relativePaths.add(relativePath);

          // Ensure that the base path is in the container locations, if not already present.
          String containerLocation = workspaceFile != null
            ? URI.createPlatformResourceURI(workspaceFile.getParent().getFullPath().toString(), true).toString() : filePath.removeLastSegments(1).toString();
          if (!containerLocations.contains(containerLocation))
          {
            containerLocations.add(0, containerLocation);
          }
        }

        // At this point there can be at most one relative path in the set.
        for (String relativePath : relativePaths)
        {
          if (compilationMonitor == null && workspaceFile != null)
          {
            workspaceFile.deleteMarkers(JET_PROBLEM, true, 0);
          }

          JETCompiler compiler = null;
          JETProblemListener problemListener = new JETProblemListener();
          StringWriter stringWriter = new StringWriter();
          JETException parseException = null;
          try
          {
            compiler = new JETCompiler(containerLocations.toArray(new String [containerLocations.size()]), relativePath, "UTF8", compilationMonitor, problemListener);

            boolean parseExceptionHandled = false;
            try
            {
              compiler.parse();
            }
            catch (JETException exception)
            {
              parseException = exception;
              if (compilationMonitor == null)
              {
                parseExceptionHandled = handleException(exception, workspaceFile);
              }
              else
              {
                compilationMonitor.setException(exception);
              }

              parseException = exception;
            }

            compiler.generate(stringWriter);

            if (compilationMonitor == null && parseException != null && !parseExceptionHandled)
            {
              throw DiagnosticException.toCoreException(parseException);
            }
          }
          catch (JETException exception)
          {
            // Gather as much result as possible if we are monitoring the compiler result;
            //
            if (compilationMonitor == null)
            {
              if (!handleException(exception, workspaceFile))
              {
                throw DiagnosticException.toCoreException(exception);
              }
            }
            else if (parseException == null)
            {
              compilationMonitor.setException(exception);
            }

            if (compiler == null || compiler.getSkeleton() == null)
            {
              continue;
            }
          }

          JETSkeleton skeleton = compiler.getSkeleton();
          if (skeleton != null)
          {
            if (skeleton.getClassName().equals(""))
            {
              skeleton.setClassName(fileName.substring(0, fileName.indexOf('.')));
            }
          }

          if (compilationMonitor != null)
          {
            if (isNotOnContainerPath)
            {
              JETMark start = compiler.reader.start;
              try
              {
                problemListener.handleProblem(start, start, IStatus.WARNING, null, JETProblemListener.FILE_NOT_ON_TEMPLATE_SOURCE_PATH, start.format("jet.mark.file.line.column"));
              }
              catch (JETException exception)
              {
                if (compilationMonitor.getException() != null)
                {
                  compilationMonitor.setException(exception);
                }
              }
            }

            break;
          }

          if (skeleton.getPackageName() != null)
          {
            directory = getPackageContainer(directory, skeleton.getPackageName(), BasicMonitor.subProgress(progressMonitor, 1));
          }
          else
          {
            progressMonitor.worked(1);
          }

          IFile outputFile = workspaceRoot.getFile(directory.getFullPath().append(skeleton.getClassName() + ".java"));

          progressMonitor.subTask(CodeGenPlugin.getPlugin().getString("_UI_JETUpdate_message", new Object []{ fileName }));

          String encoding = outputFile.getCharset();
          String result = stringWriter.getBuffer().toString();
          byte[] bytes;
          try
          {
            bytes = encoding == null ? result.getBytes() : result.getBytes(encoding);
          }
          catch (UnsupportedEncodingException exception)
          {
            bytes = result.getBytes();
          }

          if (!outputFile.exists())
          {
            outputFile.create(new ByteArrayInputStream(bytes), true, progressMonitor);
          }
          else
          {
            boolean changed = true;
            try
            {
              InputStream inputStream = outputFile.getContents();
              byte[] oldBytes = new byte [inputStream.available()];
              inputStream.read(oldBytes);
              inputStream.close();
              changed = !Arrays.equals(oldBytes, bytes);
            }
            catch (IOException exception)
            {
              // Ignore
            }

            if (changed)
            {
              if (outputFile.isReadOnly())
              {
                // This call should get the files checked out from version control if the project is a 'team' project.
                //
                IStatus status = ResourcesPlugin.getWorkspace().validateEdit(new IFile []{ outputFile }, null);
                if (!status.isOK())
                {
                  throw new CoreException(status);
                }
              }
              outputFile.setContents(new ByteArrayInputStream(bytes), true, true, progressMonitor);
            }
          }

          newState.addResult(outputFile, compiler.getResult().getTemplateURIs());

          jetProjects.add(outputFile.getProject());

          progressMonitor.worked(1);

          for (JETException jetException : problemListener.getProblems())
          {
            handleException(jetException, workspaceFile);
          }
        }
      }

      // Kick off a Java build if not already in a build.
      //
      if (!isInBuild())
      {
        for (IProject project : jetProjects)
        {
          progressMonitor.subTask(CodeGenPlugin.getPlugin().getString("_UI_JETJavaCompileProject_message", new Object []{ project.getFullPath() }));
          project.build(IncrementalProjectBuilder.INCREMENTAL_BUILD, BasicMonitor.subProgress(progressMonitor, 1));
        }
      }
    }
    finally
    {
      progressMonitor.done();
    }
  }

  protected IContainer getPackageContainer(IContainer root, String packagename, IProgressMonitor monitor) throws CoreException
  {
    for (StringTokenizer stringTokenizer = new StringTokenizer(packagename, "."); stringTokenizer.hasMoreTokens();)
    {
      IFolder newContainer = root.getFolder(new Path(stringTokenizer.nextToken()));
      if (!newContainer.exists())
      {
        newContainer.create(true, true, monitor);
      }
      root = newContainer;
    }

    return root;
  }

  public boolean isInBuild()
  {
    return inBuild;
  }

  public void setInBuild(boolean build)
  {
    inBuild = build;
  }

  private boolean handleException(JETException exception, IFile file)
  {
    JETMark start = exception.getStart();
    if (start != null && file != null)
    {
      // Walk up until we get to the root file.
      JETMark source = start;
      while (source != null && source.getFileId() != 0)
      {
        source = source.getParentMark();
      }

      try
      {
        IMarker marker = file.createMarker(JET_PROBLEM);
        String message = exception.getMessage();

        // If the location is within the file itself, strip off the location information from the message.
        if (start == source)
        {
          int index = message.indexOf(" in ");
          if (index != -1)
          {
            // Trim off the location information.
            message = message.substring(0, index);
          }
        }

        marker.setAttribute(IMarker.MESSAGE, message);
        int severity = exception.getStatus().getSeverity();
        marker.setAttribute(IMarker.SEVERITY, severity == IStatus.INFO ? IMarker.SEVERITY_INFO : severity == IStatus.WARNING ? IMarker.SEVERITY_WARNING : IMarker.SEVERITY_ERROR);
        marker.setAttribute(IMarker.LINE_NUMBER, start.line + 1);

        // If the location is within the file itself, we can use the cursor range.
        if (start == source)
        {
          JETMark stop = exception.getStop();
          marker.setAttribute(IMarker.CHAR_START, start.getCursor());
          marker.setAttribute(IMarker.CHAR_END, stop.getCursor());
        }

        return true;
      }
      catch (CoreException e)
      {
      }
    }

    return false;
  }

  /**
   * @since 2.19
   */
  public static class JETCompilationMonitor extends JETCompiler.JETInputStreamHandler implements JETCompiler.JETCompilerResultMonitor
  {
    private JETCompilationUnit result;

    private JETException exception;

    public JETCompilationUnit getResult()
    {
      return result;
    }

    public void setResult(JETCompilationUnit result)
    {
      this.result = result;
    }

    public JETException getException()
    {
      return exception;
    }

    public void setException(JETException exception)
    {
      this.exception = exception;
    }
  }

  static final class State
  {
    private Map<URI, List<URI>> javaFileToTemplateURIs = new LinkedHashMap<URI, List<URI>>();

    State()
    {
    }

    State(File stateFile)
    {
      loadState(stateFile);
    }

    Map<URI, List<URI>> getJavaFileToTemplateURIs()
    {
      return new LinkedHashMap<URI, List<URI>>(javaFileToTemplateURIs);
    }

    void addResult(IFile javaFile, List<String> templateURIs)
    {
      List<URI> uris = new ArrayList<URI>(templateURIs.size());
      for (String templateURI : templateURIs)
      {
        uris.add(URI.createURI(templateURI));
      }
      javaFileToTemplateURIs.put(URI.createPlatformResourceURI(javaFile.getFullPath().toString(), true), Collections.unmodifiableList(uris));
    }

    void saveState(File stateFile)
    {
      FileOutputStream out = null;
      StringWriter writer = new StringWriter();
      writer.write("\n");
      writer.write("<state/>\n");
      try
      {
        Document document = JETNature.fromInputSource(new InputSource(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<state/>\n")));
        Element root = document.getDocumentElement();
        for (Map.Entry<URI, List<URI>> entry : javaFileToTemplateURIs.entrySet())
        {
          Element javaElement = document.createElement("java");
          javaElement.setAttribute("target", entry.getKey().toString());
          root.appendChild(javaElement);

          for (URI uri : entry.getValue())
          {
            Element templateElement = document.createElement("template");
            templateElement.setAttribute("source", uri.toString());
            javaElement.appendChild(templateElement);
          }
        }

        String string = JETNature.toString(document);
        string.getBytes("UTF-8");
        out = new FileOutputStream(stateFile);
        out.write(string.getBytes("UTF-8"));
        out.close();
      }
      catch (IOException exception)
      {
        if (out != null)
        {
          try
          {
            out.close();
          }
          catch (IOException closeException)
          {
            // Ignore.
          }
        }

        // Ignore.
      }
    }

    private void loadState(File stateFile)
    {
      try
      {
        Document document = JETNature.fromInputSource(new InputSource(stateFile.toURI().toString()));
        Element root = document.getDocumentElement();
        NodeList javaElements = root.getElementsByTagName("java");
        for (int i = 0, javaCount = javaElements.getLength(); i < javaCount; ++i)
        {
          Element javaElement = (Element)javaElements.item(i);
          String target = javaElement.getAttribute("target");
          NodeList templateElements = javaElement.getElementsByTagName("template");
          ArrayList<URI> templateURIs = new ArrayList<URI>();
          for (int j = 0, templateCount = templateElements.getLength(); j < templateCount; ++j)
          {
            Element templateElement = (Element)templateElements.item(j);
            String source = templateElement.getAttribute("source");
            templateURIs.add(URI.createURI(source));
          }
          javaFileToTemplateURIs.put(URI.createURI(target), templateURIs);
        }
      }
      catch (IOException exception)
      {
        // Ignore.
      }
    }
  }
}
