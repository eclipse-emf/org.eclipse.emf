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


import java.io.File;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;


public class JETBuilder extends IncrementalProjectBuilder
{
  private static final String WORKING_LOCATION = "org.eclipse.emf.codegen.jet";

  /**
   * Creates a new instances.
   */
  public JETBuilder()
  {
    super();
  }

  @SuppressWarnings("rawtypes")
  @Override
  protected IProject[] build(int kind, Map arguments, IProgressMonitor monitor) throws CoreException
  {
    IProject project = getProject();
    JETNature jetNature = JETNature.getRuntime(project);
    if (!project.exists() || jetNature == null)
    {
      return new IProject [0];
    }
    else
    {
      try
      {
        if (kind == FULL_BUILD)
        {
          fullBuild(monitor);
        }
        else
        {
          IResourceDelta delta = getDelta(project);
          if (delta == null)
          {
            fullBuild(monitor);
          }
          else
          {
            incrementalBuild(delta, monitor);
          }
        }
      }
      catch (OperationCanceledException exception)
      {
        // Do nothing for now, and avoid propagating the exception.
        // How should builders handle cancel?
      }

      Set<IProject> interestingProjects = getInterestingProjects(jetNature);
      return interestingProjects.toArray(new IProject [interestingProjects.size()]);
    }
  }

  @Override
  protected void clean(IProgressMonitor monitor) throws CoreException
  {
    File buildStateLocation = getBuildStateLocation();
    JETCompileTemplateOperation.State oldState = new JETCompileTemplateOperation.State(buildStateLocation);
    reconcile(oldState, new JETCompileTemplateOperation.State(), monitor);
  }

  private void reconcile(JETCompileTemplateOperation.State oldState, JETCompileTemplateOperation.State newState, IProgressMonitor monitor) throws CoreException
  {
    Map<URI, List<URI>> oldJavaFileToTemplateURIs = oldState.getJavaFileToTemplateURIs();
    Set<URI> javaFileURIs = oldJavaFileToTemplateURIs.keySet();
    javaFileURIs.removeAll(newState.getJavaFileToTemplateURIs().keySet());
    IWorkspaceRoot workspaceRoot = (IWorkspaceRoot)getProject().getParent();
    for (URI javaURI : javaFileURIs)
    {
      IResource javaResource = workspaceRoot.findMember(new Path(javaURI.toPlatformString(true)));
      if (javaResource != null && javaResource.exists() && javaResource.getType() == IResource.FILE)
      {
        javaResource.delete(true, monitor);
      }
    }
  }

  /**
   * Does a full build.
   */
  protected void fullBuild(IProgressMonitor monitor) throws CoreException
  {
    JETNature nature = JETNature.getRuntime(getProject());
    if (nature != null)
    {
      JETCompileTemplateOperation compileTemplateOperation = new JETCompileTemplateOperation(getProject(), nature.getTemplateContainers());
      compileTemplateOperation.setInBuild(true);
      if (compileTemplateOperation.shouldCompile())
      {
        File buildStateLocation = getBuildStateLocation();
        JETCompileTemplateOperation.State oldState = new JETCompileTemplateOperation.State(buildStateLocation);
        compileTemplateOperation.run(monitor);
        JETCompileTemplateOperation.State newState = compileTemplateOperation.getNewState();
        newState.saveState(buildStateLocation);
        reconcile(oldState, newState, monitor);
      }
    }
  }

  /**
   * Does an incremental build.
   */
  protected void incrementalBuild(IResourceDelta delta, IProgressMonitor monitor) throws CoreException
  {
    IProject project = getProject();
    JETNature nature = JETNature.getRuntime(project);
    if (nature != null)
    {
      if (delta.getKind() == IResourceDelta.ADDED || delta.getKind() == IResourceDelta.CHANGED || delta.getKind() == IResourceDelta.CONTENT
          || delta.getKind() == IResourceDelta.OPEN)
      {
        final Set<IResource> containers = getInterestingResourceContainers(nature);
        final AtomicBoolean found = new AtomicBoolean();
        delta.accept(new IResourceDeltaVisitor()
          {
            public boolean visit(IResourceDelta delta) throws CoreException
            {
              if (containers.contains(delta.getResource()))
              {
                found.set(true);
              }
              return !found.get();
            }
          }, false);

        if (found.get())
        {
          JETCompileTemplateOperation compileTemplateOperation = new JETCompileTemplateOperation(project, nature.getTemplateContainers());
          compileTemplateOperation.setInBuild(true);
          if (compileTemplateOperation.shouldCompile())
          {
            File buildStateLocation = getBuildStateLocation();
            JETCompileTemplateOperation.State oldState = new JETCompileTemplateOperation.State(buildStateLocation);
            compileTemplateOperation.run(monitor);
            JETCompileTemplateOperation.State newState = compileTemplateOperation.getNewState();
            newState.saveState(buildStateLocation);
            reconcile(oldState, newState, monitor);
          }
        }
      }
    }
  }

  private static Set<IProject> getInterestingProjects(JETNature jetNature)
  {
    Set<IProject> projects = new LinkedHashSet<IProject>();
    for (IResource resource : getInterestingResourceContainers(jetNature))
    {
      projects.add(resource.getProject());
    }
    return projects;
  }

  private static Set<IResource> getInterestingResourceContainers(JETNature jetNature)
  {
    List<Object> templateContainers = jetNature.getTemplateContainers();
    Set<IResource> containers = new LinkedHashSet<IResource>();
    IWorkspaceRoot workspaceRoot = jetNature.getProject().getWorkspace().getRoot();
    for (Object object : templateContainers)
    {
      if (object instanceof IResource)
      {
        containers.add((IResource)object);
      }
      else if (object instanceof URI)
      {
        URI uri = (URI)object;
        if (uri.isPlatformResource())
        {
          IResource resource = workspaceRoot.findMember(new Path(uri.toPlatformString(true)));
          if (resource != null)
          {
            containers.add(resource);
          }
        }
      }
    }

    IContainer javaSourceContainer = jetNature.getJavaSourceContainer();
    if (javaSourceContainer != null)
    {
      containers.add(javaSourceContainer);
    }

    return containers;
  }

  private File getBuildStateLocation()
  {
    return new File(getWorkingLocation(), "build-state.xml");
  }

  private File getWorkingLocation()
  {
    IPath workingLocation = getProject().getWorkingLocation(WORKING_LOCATION);
    File file = workingLocation.toFile();
    return file;
  }

  static JETCompileTemplateOperation.State getBuildState(IProject project)
  {
    IPath workingLocation = project.getWorkingLocation(WORKING_LOCATION);
    return new JETCompileTemplateOperation.State(new File(workingLocation.toFile(), "build-state.xml"));
  }
}
