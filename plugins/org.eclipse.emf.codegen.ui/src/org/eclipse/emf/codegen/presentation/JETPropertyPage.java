/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: JETPropertyPage.java,v 1.7 2007/06/06 13:10:25 emerks Exp $
 */
package org.eclipse.emf.codegen.presentation;


import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;

import org.eclipse.emf.codegen.jet.IJETNature;
import org.eclipse.emf.codegen.jet.JETNature;


public class JETPropertyPage extends PropertyPage implements Listener 
{
  protected IProject project;

  protected Text templateContainerField;
  protected Text javaSourceField;

  protected List<Object> oldTemplateContainers;
  protected List<Object> oldTemplateSourceContainers;
  protected List<Object> newTemplateContainers;
  protected List<Object> newTemplateSourceContainers;
  protected IContainer oldJavaSoureContainer;
  protected IContainer newJavaSourceContainer;

  @Override
  protected Control createContents(Composite parent) 
  {
    Control control = null;

    project = getJETProject();
    if (project != null) 
    {
      IJETNature jetNature = JETNature.getRuntime(project);
      if (jetNature != null) 
      {
        // container specification group
        Composite containerGroup = new Composite(parent, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        containerGroup.setLayout(layout);
        containerGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));

        // New Template Container Label
        Label templateContainerLabel = new Label(containerGroup, SWT.CHECK);
        templateContainerLabel.setText(CodeGenUIPlugin.getPlugin().getString("_UI_TemplateContainer_label"));

        // New Template Container Entryfield
        templateContainerField = new Text(containerGroup, SWT.BORDER);
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
        templateContainerField.setLayoutData(data);

        oldTemplateContainers = jetNature.getTemplateContainers();
        oldTemplateSourceContainers = jetNature.getTemplateSourceContainers();
        templateContainerField.setText(JETNature.getContainers(project, oldTemplateContainers, oldTemplateSourceContainers));
        templateContainerField.addListener(SWT.Modify, this);

        // New Template Container Label
        Label sourceContainerLabel = new Label(containerGroup, SWT.CHECK);
        sourceContainerLabel.setText(CodeGenUIPlugin.getPlugin().getString("_UI_SourceContainer_label"));

        // New Template Container Entryfield
        javaSourceField = new Text(containerGroup, SWT.BORDER);
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
        javaSourceField.setLayoutData(data);

        oldJavaSoureContainer = jetNature.getJavaSourceContainer();
        javaSourceField.setText( oldJavaSoureContainer.getProjectRelativePath().toString());
        javaSourceField.addListener(SWT.Modify, this);

        control = containerGroup;
      } 
      else 
      {
        Label closedProjectLabel = new Label(parent, SWT.NONE);
        closedProjectLabel.setText(CodeGenUIPlugin.getPlugin().getString("_UI_WebSettingsNotAvaiable_message"));
        control = closedProjectLabel;
      }
    }
    return control;
  }

  /**
   * Returns the highlighted project in the workbench.
   */
  protected IProject getJETProject() 
  {
    Object element = getElement();

    if (element instanceof IProject) 
    {
      return isJETProject((IProject) element) ? (IProject) element : null;
    }
    else if (element instanceof IJavaProject) 
    {
      return isJETProject(((IJavaProject) element).getProject()) ? ((IJavaProject) element).getProject() : null;
    }

    return null;
  }

  /**
   * Return whether this is a JET project.
   */
  protected static boolean isJETProject(IProject project) 
  {
    try 
    {
      return project.hasNature(IJETNature.NATURE_ID);
    } 
    catch (CoreException e) 
    {
      CodeGenUIPlugin.write(e);
    }

    return false;
  }

  public void handleEvent(Event event) 
  {
    if (event.widget == templateContainerField) 
    {
      IJETNature jetNature = JETNature.getRuntime(project);
      if (jetNature != null) 
      {
        try 
        {
          newTemplateContainers = JETNature.getContainers(getJETProject(), templateContainerField.getText());
          newTemplateSourceContainers = JETNature.getContainers(getJETProject(), templateContainerField.getText(), true);
          // jetNature.setTemplateContainers(newTemplateContainers);
          setErrorMessage(null);
        } 
        catch (Exception exception) 
        {
          setErrorMessage
            (CodeGenUIPlugin.getPlugin().getString
               ("_UI_CannotSetTemplateContainer_message", new String [] { exception.getLocalizedMessage() }));
        }
      }
    }

    if (event.widget == javaSourceField) 
    {
      IJETNature jetNature = JETNature.getRuntime(project);
      if (jetNature != null) 
      {
        newJavaSourceContainer = JETNature.getContainer(getJETProject(), javaSourceField.getText());
        if (newJavaSourceContainer.exists()) 
        {
          // jetNature.setJavaSourceContainer(newJavaSourceContainer);
          setErrorMessage(null);
        }
        else 
        {
          setErrorMessage
            (CodeGenUIPlugin.getPlugin().getString
               ("_UI_ContainerDoesNotExist_message", new String [] { newJavaSourceContainer.toString() }));
        }
      }
    }
  }

  @Override
  public boolean performOk() 
  {
    performApply();
    return super.performOk();
  }

  @Override
  protected void performApply() 
  {
    IJETNature jetNature = JETNature.getRuntime(project);
    if (jetNature != null) 
    {
      if (newTemplateContainers != null)
      {
        jetNature.setTemplateContainers
          (newTemplateContainers, newTemplateSourceContainers == null ? newTemplateContainers : newTemplateSourceContainers);
      }
      if (newJavaSourceContainer != null)
      {
        jetNature.setJavaSourceContainer(newJavaSourceContainer);
      }
    }
  }

  @Override
  protected void performDefaults() 
  {
    IJETNature jetNature = JETNature.getRuntime(project);
    if (jetNature != null) 
    {
      try
      {
        ((JETNature)jetNature).setDefaults(new NullProgressMonitor());
      }
      catch(CoreException e)
      {
        CodeGenUIPlugin.write(e);  
      }
    }
  }
}
