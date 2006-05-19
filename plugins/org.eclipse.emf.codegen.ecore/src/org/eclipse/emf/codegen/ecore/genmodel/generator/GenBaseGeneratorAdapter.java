/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: GenBaseGeneratorAdapter.java,v 1.2 2006/05/19 22:34:06 davidms Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.generator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;

/**
 * @since 2.2.0
 */
public class GenBaseGeneratorAdapter extends AbstractGeneratorAdapter
{
  //DMS values? location?
  public static final String MODEL_PROJECT_TYPE = "org.eclipse.emf.codegen.ecore.genmodel.generator.ModelProject";
  public static final String EDIT_PROJECT_TYPE = "org.eclipse.emf.codegen.ecore.genmodel.generator.EditProject";
  public static final String EDITOR_PROJECT_TYPE = "org.eclipse.emf.codegen.ecore.genmodel.generator.EditorProject";
  public static final String TESTS_PROJECT_TYPE = "org.eclipse.emf.codegen.ecore.genmodel.generator.TestsProject";

  public GenBaseGeneratorAdapter()
  {    
  }

  public GenBaseGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory)
  {
    super(generatorAdapterFactory);
  }

  public boolean canGenerate(Object object, Object projectType)
  {
    if (MODEL_PROJECT_TYPE.equals(projectType))
    {
      return canGenerateModel(object);
    }
    else if (EDIT_PROJECT_TYPE.equals(projectType))
    {
      return canGenerateEdit(object);
    }
    else if (EDITOR_PROJECT_TYPE.equals(projectType))
    {
      return canGenerateEditor(object);
    }
    else if (TESTS_PROJECT_TYPE.equals(projectType))
    {
      return canGenerateTests(object);
    }
    return false;
  }

  public boolean canGenerateModel(Object object)
  {
    return ((GenBase)object).canGenerate();
  }

  public boolean canGenerateEdit(Object object)
  {
    return ((GenBase)object).canGenerateEdit();
  }

  public boolean canGenerateEditor(Object object)
  {
    return ((GenBase)object).canGenerateEditor();
  }

  public boolean canGenerateTests(Object object)
  {
    return ((GenBase)object).canGenerateTests();
  }

  public Collection getGenerateChildren(Object object, Object projectType)
  {
    if (MODEL_PROJECT_TYPE.equals(projectType))
    {
      return getGenerateModelChildren(object);
    }
    else if (EDIT_PROJECT_TYPE.equals(projectType))
    {
      return getGenerateEditChildren(object);
    }
    else if (EDITOR_PROJECT_TYPE.equals(projectType))
    {
      return getGenerateEditorChildren(object);
    }
    else if (TESTS_PROJECT_TYPE.equals(projectType))
    {
      return getGenerateTestsChildren(object);
    }
    return Collections.EMPTY_LIST;
  }

  protected Collection getGenerateModelChildren(Object object)
  {
    return Collections.EMPTY_LIST;
  }

  protected Collection getGenerateEditChildren(Object object)
  {
    return Collections.EMPTY_LIST;
  }

  protected Collection getGenerateEditorChildren(Object object)
  {
    return Collections.EMPTY_LIST;
  }

  protected Collection getGenerateTestsChildren(Object object)
  {
    return Collections.EMPTY_LIST;
  }

  protected Object getParent(Object object)
  {
    Object result = ((GenBase)object).eContainer();
    return result instanceof GenBase ? result : null;
  }

  public Diagnostic doGenerate(Object object, Object projectType, Monitor monitor)
  {
    if (MODEL_PROJECT_TYPE.equals(projectType))
    {
      return generateModel(object, monitor);
    }
    else if (EDIT_PROJECT_TYPE.equals(projectType))
    {
      return generateEdit(object, monitor);
    }
    else if (EDITOR_PROJECT_TYPE.equals(projectType))
    {
      return generateEditor(object, monitor);
    }
    else if (TESTS_PROJECT_TYPE.equals(projectType))
    {
      return generateTests(object, monitor);
    }
    throw new IllegalArgumentException("Invalid projectType: " + projectType.toString());
  }

  protected Diagnostic generateModel(Object object, Monitor monitor)
  {
    return Diagnostic.OK_INSTANCE;
  }

  protected Diagnostic generateEdit(Object object, Monitor monitor)
  {
    return Diagnostic.OK_INSTANCE;
  }

  protected Diagnostic generateEditor(Object object, Monitor monitor)
  {
    return Diagnostic.OK_INSTANCE;
  }

  protected Diagnostic generateTests(Object object, Monitor monitor)
  {
    return Diagnostic.OK_INSTANCE;
  }

  protected void addClasspathEntries(JETEmitter jetEmitter) throws JETException
  {
    jetEmitter.addVariable("EMF_CODEGEN", "org.eclipse.emf.codegen");
    jetEmitter.addVariable("EMF_CODEGEN_ECORE", "org.eclipse.emf.codegen.ecore");
    jetEmitter.addVariable("EMF_COMMON", "org.eclipse.emf.common");
    jetEmitter.addVariable("EMF_ECORE", "org.eclipse.emf.ecore");
  }

  protected void createImportManager(String packageName, String className)
  {
    super.createImportManager(packageName, className);
    updateImportManager();
  }

  protected void clearImportManager()
  {
    super.clearImportManager();
    updateImportManager();
  }

  private void updateImportManager()
  {
    if (generatingObject != null)
    {
      ((GenBase)generatingObject).getGenModel().setImportManager(importManager);
    }    
  }

  protected boolean ensureProjectExists(URI workspacePath, Object object, Object projectType, boolean force, Monitor monitor)
  {
    try
    {
      if (EMFPlugin.IS_ECLIPSE_RUNNING)
      {
        return EclipseHelper.ensureProjectExists(workspacePath.toString(), object, projectType, force, monitor);
      }
      return true;
    }
    finally
    {
      monitor.done();
    }
  }

  private static class EclipseHelper
  {
    public static boolean ensureProjectExists(String workspacePath, Object object, Object projectType, boolean force, Monitor monitor)
    {
      try
      {
        IPath path = new Path(workspacePath);

        if (path.isAbsolute())
        {
          GenModel genModel = ((GenBase)object).getGenModel();
          IWorkspace workspace = ResourcesPlugin.getWorkspace();
          IProject project = workspace.getRoot().getProject(path.segment(0));

          if (!project.exists() || force)
          {
            IPath projectLocation = null;
            List referencedProjects = new UniqueEList();

            if (project.exists())
            {
              referencedProjects.addAll(Arrays.asList(project.getDescription().getReferencedProjects()));
              projectLocation = project.getDescription().getLocation();
            }
            else
            {
              URI genModelURI = genModel.eResource().getURI();
              if (genModelURI.toString().startsWith("platform:/resource/"))
              {
                IProject genModelProject = workspace.getRoot().getProject(genModelURI.segments()[1]);
                projectLocation = genModelProject.getDescription().getLocation();
              }
            }

            IProject modelProject = workspace.getRoot().getProject(genModel.getModelProjectDirectory());
            IPath javaSource = new Path(genModel.getModelDirectory());

            //DMS factor this into a method? Use a non-static subclass? 
            int style = 0;
            if (MODEL_PROJECT_TYPE.equals(projectType))  style = Generator.EMF_MODEL_PROJECT_STYLE;
            if (EDIT_PROJECT_TYPE.equals(projectType))   style = Generator.EMF_EDIT_PROJECT_STYLE;
            if (EDITOR_PROJECT_TYPE.equals(projectType)) style = Generator.EMF_EDITOR_PROJECT_STYLE;
            if (TESTS_PROJECT_TYPE.equals(projectType))  style = Generator.EMF_TESTS_PROJECT_STYLE;

            if ((style & Generator.EMF_TESTS_PROJECT_STYLE) != 0)
            {
              IProject testsProject = workspace.getRoot().getProject(genModel.getTestsProjectDirectory());

              if (!genModel.sameModelTestsProject()) 
              {
                javaSource = new Path(genModel.getTestsDirectory());
  
                if (testsProject.exists())
                {
                  projectLocation = testsProject.getDescription().getLocation();
                }

                referencedProjects.add(modelProject);
                referencedProjects.addAll(Arrays.asList(modelProject.getDescription().getReferencedProjects()));
              }
            }
            else if ((style & Generator.EMF_MODEL_PROJECT_STYLE) == 0 && genModel.hasEditSupport())
            {
              IProject editProject = workspace.getRoot().getProject(genModel.getEditProjectDirectory());

              if (!genModel.sameModelEditProject())
              {
                javaSource = new Path(genModel.getEditDirectory());
                if (editProject.exists())
                {
                  projectLocation = editProject.getDescription().getLocation();
                }

                referencedProjects.add(modelProject);
              }
  
              for (Iterator i = genModel.getUsedGenPackages().iterator(); i.hasNext(); )
              {
                GenModel otherGenModel = ((GenPackage)i.next()).getGenModel();
                if (otherGenModel.hasEditSupport())
                {
                  IProject otherEditProject = workspace.getRoot().getProject(otherGenModel.getEditProjectDirectory());
                  if (otherEditProject.exists())
                  {
                    referencedProjects.add(otherEditProject);
                    referencedProjects.addAll(Arrays.asList(otherEditProject.getDescription().getReferencedProjects()));
                  }
                }
              }

              if ((style & Generator.EMF_EDIT_PROJECT_STYLE) == 0 && genModel.hasEditorSupport())
              {
                javaSource = new Path(genModel.getEditorDirectory());
                if (!genModel.sameEditEditorProject())
                {
                  referencedProjects.add(editProject);
                  referencedProjects.addAll(Arrays.asList(editProject.getDescription().getReferencedProjects()));
                }
              }
            }

            //  Remove any non-Java dependencies from being added.
            //
            for (Iterator i = referencedProjects.iterator(); i.hasNext(); )
            {
              IProject referencedProject = (IProject)i.next();
              IJavaProject referencedJavaProject = JavaCore.create(referencedProject);
              if (!referencedJavaProject.exists())
              {
                i.remove();
              }
            }

            if (projectLocation != null)
            {
              projectLocation = projectLocation.removeLastSegments(1).append(javaSource.segment(0));
            }

            if (genModel.hasXMLDependency())
            {
              style |= Generator.EMF_XML_PROJECT_STYLE;
            }

            if ((style & Generator.EMF_MODEL_PROJECT_STYLE) == 0 || genModel.hasPluginSupport())
            {
              style |= Generator.EMF_PLUGIN_PROJECT_STYLE;
            }

            Generator.createEMFProject
              (javaSource,
               projectLocation, 
               referencedProjects, 
               monitor, 
               style,
               genModel.getEffectiveModelPluginVariables());
          }
          return workspace.getRoot().getProject(path.segment(0)).exists();
        }
      }
      catch (Exception exception)
      { 
        //DMS should we let this exception out?
        CodeGenEcorePlugin.INSTANCE.log(exception);
      }
      return false;
    }
  }
}
