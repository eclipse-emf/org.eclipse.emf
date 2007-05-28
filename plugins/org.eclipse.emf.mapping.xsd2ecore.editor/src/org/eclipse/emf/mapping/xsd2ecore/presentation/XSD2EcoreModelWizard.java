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
 * $Id: XSD2EcoreModelWizard.java,v 1.7 2007/05/28 19:13:06 emerks Exp $
 */
package org.eclipse.emf.mapping.xsd2ecore.presentation;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.MissingResourceException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.mapping.xsd2ecore.XSD2EcoreFactory;
import org.eclipse.emf.mapping.xsd2ecore.XSD2EcorePackage;
import org.eclipse.emf.mapping.xsd2ecore.XSD2EcorePlugin;


/**
 * This is a simple wizard for creating a new model file.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class XSD2EcoreModelWizard extends Wizard implements INewWizard
{
  /**
   * This caches an instance of the model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSD2EcorePackage xsD2EcorePackage = XSD2EcorePackage.eINSTANCE;

  /**
   * This caches an instance of the model factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSD2EcoreFactory xsD2EcoreFactory = xsD2EcorePackage.getXSD2EcoreFactory();

  /**
   * This is the file creation page.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSD2EcoreModelWizardNewFileCreationPage newFileCreationPage;

  /**
   * This is the file creation page.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSD2EcoreModelWizardInitialObjectCreationPage initialObjectCreationPage;

  /**
   * Remember the selection during initialization for populating the default container.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IStructuredSelection selection;

  /**
   * Remember the workbench during initialization.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IWorkbench workbench;

  /**
   * This just records the information.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void init(IWorkbench workbench, IStructuredSelection selection)
  {
    this.workbench = workbench;
    this.selection = selection;
    setDefaultPageImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(XSD2EcoreEditorPlugin.INSTANCE.getImage("full/wizban/NewXSD2Ecore")));
  }

  /**
   * Create a new model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  EObject createInitialModel()
  {
    EClass eClass = (EClass)xsD2EcorePackage.getEClassifier(initialObjectCreationPage.getInitialEClassName());
    EObject rootObject = xsD2EcoreFactory.create(eClass);
    return rootObject;
  }

  /**
   * Do the work after everything is specified.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean performFinish()
  {
    try
    {
      // Remember the file.
      //
      final IFile modelFile = getModelFile();

      // Do the work within an operation.
      //
      WorkspaceModifyOperation operation =
        new WorkspaceModifyOperation()
        {
          @Override
          protected void execute(IProgressMonitor progressMonitor)
          {
            try
            {
              // Create a resource set
              //
              ResourceSet resourceSet = new ResourceSetImpl();

              // Get the URI of the model file.
              //
              URI fileURI = URI.createPlatformResourceURI(modelFile.getFullPath().toString(), true);

              // Create a resource for this file.
              //
              Resource resource = resourceSet.createResource(fileURI);

              // Add the initial model object to the contents.
              //
              EObject rootObject = createInitialModel();
              if (rootObject != null)
              {
                resource.getContents().add(rootObject);
              }

              // Save the contents of the resource to the file system.
              //
              resource.save(Collections.EMPTY_MAP);
            }
            catch (Exception exception)
            {
              exception.printStackTrace();
            }
            finally
            {
              progressMonitor.done();
            }
          }
        };

      getContainer().run(false, false, operation);

      // Select the new file resource in the current view.
      //
      IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
      IWorkbenchPage page = workbenchWindow.getActivePage();
      final IWorkbenchPart activePart = page.getActivePart();
      if (activePart instanceof ISetSelectionTarget)
      {
        final ISelection targetSelection = new StructuredSelection(modelFile);
        getShell().getDisplay().asyncExec
          (new Runnable()
           {
             public void run()
             {
               ((ISetSelectionTarget)activePart).selectReveal(targetSelection);
             }
           });
      }

      // Open an editor on the new file.
      //
      try
      {
        page.openEditor
          (new FileEditorInput(modelFile),
           workbench.getEditorRegistry().getDefaultEditor(modelFile.getFullPath().toString()).getId());
      }
      catch (PartInitException exception)
      {
        MessageDialog.openError(workbenchWindow.getShell(), XSD2EcoreEditorPlugin.INSTANCE.getString("_UI_OpenEditorError_label"), exception.getMessage());
        return false;
      }

      return true;
    }
    catch (Exception exception)
    {
      exception.printStackTrace();
      return false;
    }
  }

  /**
   * This is the one page of the wizard.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public class XSD2EcoreModelWizardNewFileCreationPage extends WizardNewFileCreationPage
  {
    /**
     * Remember the model file.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected IFile modelFile;

    /**
     * Pass in the selection.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XSD2EcoreModelWizardNewFileCreationPage(String pageId, IStructuredSelection selection)
    {
      super(pageId, selection);
    }

    /**
     * The framework calls this to see if the file is correct.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected boolean validatePage()
    {
      if (super.validatePage())
      {
        // Make sure the file ends in ".xsd2ecore".
        //
        String requiredExt = XSD2EcoreEditorPlugin.INSTANCE.getString("_UI_XSD2EcoreEditorFilenameExtension");
        String enteredExt = new Path(getFileName()).getFileExtension();
        if (enteredExt == null || !enteredExt.equals(requiredExt))
        {
          setErrorMessage(XSD2EcoreEditorPlugin.INSTANCE.getString("_WARN_FilenameExtension", new Object [] { requiredExt }));
          return false;
        }
        else
        {
          return true;
        }
      }
      else
      {
        return false;
      }
    }

    /**
     * Store the dialog field settings upon completion.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean performFinish()
    {
      modelFile = getModelFile();
      return true;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public IFile getModelFile()
    {
      return
        modelFile == null ?
          ResourcesPlugin.getWorkspace().getRoot().getFile(getContainerFullPath().append(getFileName())) :
          modelFile;
    }
  }

  /**
   * This is the page where the type of object to create is selected.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public class XSD2EcoreModelWizardInitialObjectCreationPage extends WizardPage
  {
    /**
     * @generated
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    protected String initialEClassName;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected CCombo initialObjectField;

    /**
     * Pass in the selection.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XSD2EcoreModelWizardInitialObjectCreationPage(String pageId)
    {
      super(pageId);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void createControl(Composite parent)
    {
      Composite composite = new Composite(parent, SWT.NONE);
      {
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        layout.verticalSpacing = 12;
        composite.setLayout(layout);

        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.grabExcessVerticalSpace = true;
        data.horizontalAlignment = GridData.FILL;
        composite.setLayoutData(data);
      }

      Label containerLabel = new Label(composite, SWT.LEFT);
      {
        containerLabel.setText(XSD2EcoreEditorPlugin.INSTANCE.getString("_UI_ModelObject"));

        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        containerLabel.setLayoutData(data);
      }

      initialObjectField = new CCombo(composite, SWT.BORDER);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        // data.horizontalSpan = 2;
        initialObjectField.setLayoutData(data);
      }

      List<String> eClasses = new ArrayList<String>();
      for (EClassifier eClassifier : xsD2EcorePackage.getEClassifiers())
      {
        if (eClassifier instanceof EClass)
        {
          EClass eClass = (EClass)eClassifier;
          if (!eClass.isAbstract())
          {
            eClasses.add(getLabel(eClass));
          }
        }
      }

      Collections.sort(eClasses, CommonPlugin.INSTANCE.getComparator());
      for (String eClassName : eClasses)
      {
        initialObjectField.add(eClassName);
      }

      initialObjectField.addSelectionListener
        (new SelectionAdapter()
         {
           @Override
          public void widgetSelected(SelectionEvent e)
           {
             setPageComplete(isPageComplete());
           }
         });

      setControl(composite);
    }

    /**
     * The framework calls this to see if the file is correct.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean isPageComplete()
    {
      if (super.isPageComplete())
      {
        return initialObjectField.getSelectionIndex() != -1;
      }
      else
      {
        return false;
      }
    }

    /**
     * Store the dialog field settings upon completion.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean performFinish()
    {
      initialEClassName = getInitialEClassName();
      return true;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getInitialEClassName()
    {
      if (initialEClassName != null)
      {
        return initialEClassName;
      }
      else
      {
        String label = initialObjectField.getText();
        for (EClassifier eClassifier : xsD2EcorePackage.getEClassifiers())
        {
          if (eClassifier instanceof EClass)
          {
            EClass eClass = (EClass)eClassifier;
            if (!eClass.isAbstract() && getLabel(eClass).equals(label))
            {
              return eClass.getName();
            }
          }
        }
        return label;
      }
    }
    
    /**
     * Returns the label of the specified element.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected String getLabel(EClass eClass)
    {
      String name = eClass.getName();
      try
      {
        return XSD2EcorePlugin.INSTANCE.getString("_UI_" + name + "_type");
      }
      catch(MissingResourceException mre)
      {
        // Ignore
      }
      return name;
    }    
  }

  /**
   * The framework calls this to create the contents of the wizard.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void addPages()
  {
    // Create a page, set the title, and the initial model file name.
    //
    newFileCreationPage = new XSD2EcoreModelWizardNewFileCreationPage("Whatever", selection);
    newFileCreationPage.setTitle(XSD2EcoreEditorPlugin.INSTANCE.getString("_UI_XSD2EcoreModelWizard_label"));
    newFileCreationPage.setDescription(XSD2EcoreEditorPlugin.INSTANCE.getString("_UI_XSD2EcoreModelWizard_description"));
    newFileCreationPage.setFileName(XSD2EcoreEditorPlugin.INSTANCE.getString("_UI_XSD2EcoreEditorFilenameDefaultBase") + "." + XSD2EcoreEditorPlugin.INSTANCE.getString("_UI_XSD2EcoreEditorFilenameExtension"));
    addPage(newFileCreationPage);

    // Try and get the resource selection to determine a current directory for the file dialog.
    //
    if (selection != null && !selection.isEmpty())
    {
      // Get the resource...
      //
      Object selectedElement = selection.iterator().next();
      if (selectedElement instanceof IResource)
      {
        // Get the resource parent, if its a file.
        //
        IResource selectedResource = (IResource)selectedElement;
        if (selectedResource.getType() == IResource.FILE)
        {
          selectedResource = selectedResource.getParent();
        }

        // This gives us a directory...
        //
        if (selectedResource instanceof IFolder || selectedResource instanceof IProject)
        {
          // Set this for the container.
          //
          newFileCreationPage.setContainerFullPath(selectedResource.getFullPath());

          // Make up a unique new name here.
          //
          String defaultModelBaseFilename = XSD2EcoreEditorPlugin.INSTANCE.getString("_UI_XSD2EcoreEditorFilenameDefaultBase");
          String defaultModelFilenameExtension = XSD2EcoreEditorPlugin.INSTANCE.getString("_UI_XSD2EcoreEditorFilenameExtension");
          String modelFilename = defaultModelBaseFilename + "." + defaultModelFilenameExtension;
          for (int i = 1; ((IContainer)selectedResource).findMember(modelFilename) != null; ++i)
          {
            modelFilename = defaultModelBaseFilename + i + "." + defaultModelFilenameExtension;
          }
          newFileCreationPage.setFileName(modelFilename);
        }
      }
    }
    initialObjectCreationPage = new XSD2EcoreModelWizardInitialObjectCreationPage("Whatever2");
    initialObjectCreationPage.setTitle(XSD2EcoreEditorPlugin.INSTANCE.getString("_UI_XSD2EcoreModelWizard_label"));
    initialObjectCreationPage.setDescription(XSD2EcoreEditorPlugin.INSTANCE.getString("_UI_Wizard_initial_object_description"));
    addPage(initialObjectCreationPage);
  }

  /**
   * Get the file from the page.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IFile getModelFile()
  {
    return newFileCreationPage.getModelFile();
  }
}
