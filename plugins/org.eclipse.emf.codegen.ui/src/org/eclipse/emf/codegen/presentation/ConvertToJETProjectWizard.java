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
 * $Id: ConvertToJETProjectWizard.java,v 1.7 2007/05/29 20:28:43 marcelop Exp $
 */
package org.eclipse.emf.codegen.presentation;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.ide.IDE;

import org.eclipse.emf.codegen.jet.JETAddNatureOperation;
import org.eclipse.emf.codegen.jet.JETNature;


public class ConvertToJETProjectWizard extends Wizard implements INewWizard
{
  protected IWorkbench workbench;
  protected List<IProject> projectsToConvert = new ArrayList<IProject>();
  protected ConversionPage conversionPage;

  public ConvertToJETProjectWizard() 
  {
    setWindowTitle(CodeGenUIPlugin.getPlugin().getString("_UI_JETConvertProject_label"));
    setNeedsProgressMonitor(true);
  }

  public void init(IWorkbench workbench, IStructuredSelection selection)
  {
    this.workbench = workbench;
    for (Object object : selection.toList())
    {
      if (object instanceof IProject)
      {
        IProject project = (IProject)object;
        if (project.isOpen() && JETNature.getRuntime(project) == null)
        {
          projectsToConvert.add(project);
        }
      }
    }
  }

  @Override
  public void addPages() 
  {
    super.addPages();
    conversionPage = new ConversionPage();
    addPage(conversionPage);
  }

  @Override
  public boolean performFinish() 
  {
    IRunnableWithProgress operation = 
      new WorkspaceModifyOperation() 
      {
        @Override
        public void execute(IProgressMonitor monitor) 
        {
          try 
          {
            JETAddNatureOperation addNature = new JETAddNatureOperation(projectsToConvert);
            addNature.run(monitor);
          } 
          catch (CoreException e) 
          {
            CodeGenUIPlugin.write(e);
          } 
        }
      };

    try 
    {
      getContainer().run(false, true, operation);
    } 
    catch (InterruptedException exception)
    {
      CodeGenUIPlugin.write(exception);
    }
    catch (InvocationTargetException exception)
    {
      CodeGenUIPlugin.write(exception);
    }
    return true;
  }

  public class ConversionPage extends WizardPage
  {
    public ConversionPage()
    {
      super("JETConversionPage");

      setTitle(CodeGenUIPlugin.getPlugin().getString("_UI_JETConvertProject_label"));
      setDefaultPageImageDescriptor(CodeGenUIPlugin.getPlugin().getImage("full/wizban/ConvertToJETProjectWizard"));
      setDescription(CodeGenUIPlugin.getPlugin().getString("_UI_JETConvertProject_message"));
    }

    @Override
    public boolean isPageComplete()
    {
      return !projectsToConvert.isEmpty();
    }

    public void createControl(Composite parent)
    {
      Composite composite = new Composite(parent, SWT.NONE);
      {
        FormLayout layout = new FormLayout();
        layout.marginTop = 10;
        layout.marginLeft = 10;
        layout.marginRight = 10;
        layout.spacing = 10;
        composite.setLayout(layout);

        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.grabExcessVerticalSpace = true;
        data.horizontalAlignment = GridData.FILL;
        composite.setLayoutData(data);
      }

      Label projectsLabel = new Label(composite, SWT.LEFT);
      projectsLabel.setText(CodeGenUIPlugin.getPlugin().getString("_UI_Projects_label"));
      {
        FormData data = new FormData();
        data.left = new FormAttachment(0);
        projectsLabel.setLayoutData(data);
      }    

      Table projectsTable = new Table(composite, SWT.CHECK | SWT.BORDER);
      {
        FormData data = new FormData(SWT.DEFAULT, 250);
        data.top = new FormAttachment(projectsLabel, 5);
        data.bottom = new FormAttachment(100);
        data.left = new FormAttachment(0);
        data.right = new FormAttachment(100);
        data.height = 90;
        projectsTable.setLayoutData(data);
      }
      
      Composite selectionComposite = new Composite(composite, SWT.NONE);
      {
        FormData data = new FormData();
        data.top = new FormAttachment(projectsLabel, 0, SWT.CENTER);
        data.right = new FormAttachment(100);
        selectionComposite.setLayoutData(data);      

        RowLayout layout = new RowLayout();
        layout.justify = true;
        layout.pack = true;
        layout.spacing = 15;
        layout.marginBottom = layout.marginTop = 0;
        layout.marginLeft = layout.marginRight = 0;
        selectionComposite.setLayout(layout);
      }

      Button selectAllButton = new Button(selectionComposite, SWT.PUSH);
      selectAllButton.setText(CodeGenUIPlugin.getPlugin().getString("_UI_SelectAll_label"));

      Button deselectAllButton = new Button(selectionComposite, SWT.PUSH);
      deselectAllButton.setText(CodeGenUIPlugin.getPlugin().getString("_UI_DeselectAll_label"));

      final CheckboxTableViewer projectsCheckboxTableViewer = new CheckboxTableViewer(projectsTable);
      {
        TableLayout layout = new TableLayout();
        TableColumn projectColumn = new TableColumn(projectsTable, SWT.NONE);
        layout.addColumnData(new ColumnWeightData(1, true));
        projectColumn.setResizable(true);
        projectsTable.setLayout(layout);
      }

      projectsCheckboxTableViewer.setColumnProperties(new String [] {"a"});
      projectsCheckboxTableViewer.setContentProvider(new ArrayContentProvider());
      projectsCheckboxTableViewer.setLabelProvider
        (new LabelProvider()
         {
           @Override
          public Image getImage(Object o)
           {
             return workbench.getSharedImages().getImage(IDE.SharedImages.IMG_OBJ_PROJECT);
           }
           
           @Override
          public String getText(Object o)
           {
             return ((IProject)o).getName();
           }
         });

      projectsCheckboxTableViewer.addCheckStateListener
        (new ICheckStateListener()
         {
           public void checkStateChanged(CheckStateChangedEvent event)
           {
             projectsToConvert.clear();
             @SuppressWarnings("unchecked")
             List<IProject> list = (List<IProject>)(List<?>)Arrays.asList(projectsCheckboxTableViewer.getCheckedElements());
            projectsToConvert.addAll(list);
             setPageComplete(isPageComplete());
           }
         });

      final List<IProject> projects = new ArrayList<IProject>(Arrays.asList(ResourcesPlugin.getWorkspace().getRoot().getProjects()));
      for (Iterator<IProject> i = projects.iterator(); i.hasNext(); )
      {
        IProject project = i.next();
        boolean isJavaProject = false;
        try
        {
          isJavaProject = project.getNature(JavaCore.NATURE_ID) != null;
        }
        catch (CoreException e)
        {
          // Ignore
        }
        
        if (!project.isOpen() || JETNature.getRuntime(project) != null || !isJavaProject)
        {
          i.remove();
        }
      }
      projectsCheckboxTableViewer.setInput(projects.toArray());
      projectsCheckboxTableViewer.setCheckedElements(projectsToConvert.toArray());

      selectAllButton.addSelectionListener
        (new SelectionAdapter()
         {
           @Override
          public void widgetSelected(SelectionEvent event)
           {
             projectsToConvert.addAll(projects);
             projectsCheckboxTableViewer.setCheckedElements(projects.toArray());
             setPageComplete(isPageComplete());
           }
         });
      deselectAllButton.addSelectionListener
        (new SelectionAdapter()
         {
           @Override
          public void widgetSelected(SelectionEvent event)
           {
             projectsCheckboxTableViewer.setCheckedElements(new Object [0]);
             projectsToConvert.clear();
             setPageComplete(isPageComplete());
           }
         });

      setControl(composite);

      setPageComplete(isPageComplete());
    }
  }
}
