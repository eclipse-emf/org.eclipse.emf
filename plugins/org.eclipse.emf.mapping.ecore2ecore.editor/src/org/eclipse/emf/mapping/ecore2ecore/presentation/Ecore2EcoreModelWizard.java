/*
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * $Id: Ecore2EcoreModelWizard.java,v 1.6 2004/12/16 21:54:47 emerks Exp $
 */
package org.eclipse.emf.mapping.ecore2ecore.presentation;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.StringTokenizer;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.xmi.XMLResource;

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;

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

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Combo;
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

import org.eclipse.emf.mapping.ecore2ecore.Ecore2EcoreFactory;
import org.eclipse.emf.mapping.ecore2ecore.Ecore2EcoreMappingRoot;
import org.eclipse.emf.mapping.ecore2ecore.Ecore2EcorePackage;


import org.eclipse.emf.mapping.ecore2ecore.Ecore2EcorePlugin;


/**
 * This is a simple wizard for creating a new model file.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class Ecore2EcoreModelWizard extends Wizard implements INewWizard
{
  /**
   * This caches an instance of the model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Ecore2EcorePackage ecore2EcorePackage = Ecore2EcorePackage.eINSTANCE;

  /**
   * This caches an instance of the model factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Ecore2EcoreFactory ecore2EcoreFactory = ecore2EcorePackage.getEcore2EcoreFactory();

  /**
   * This is the file creation page.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Ecore2EcoreModelWizardNewFileCreationPage newFileCreationPage;

  /**
   * This is the file creation page.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Ecore2EcoreModelWizardInitialObjectCreationPage initialObjectCreationPage;

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
    setWindowTitle(Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_Wizard_label"));
    setDefaultPageImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(Ecore2EcoreEditorPlugin.INSTANCE.getImage("full/wizban/NewEcore2Ecore")));
  }

  /**
   * Create a new model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  EObject createInitialModelGen()
  {
    EClass eClass = (EClass)ecore2EcorePackage.getEClassifier(initialObjectCreationPage.getInitialObjectName());
    EObject rootObject = ecore2EcoreFactory.create(eClass);
    return rootObject;
  }

  EObject createInitialModel()
  {
    EObject rootObject = createInitialModelGen();

    if (Ecore2EcoreMappingRoot.class.isInstance(rootObject))
    {
      ((Ecore2EcoreMappingRoot)rootObject).setTopToBottom(true);
    }

    return rootObject;
  }

  /**
   * Do the work after everything is specified.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
          protected void execute(IProgressMonitor progressMonitor)
          {
            try
            {
              // Create a resource set
              //
              ResourceSet resourceSet = new ResourceSetImpl();

              // Get the URI of the model file.
              //
              URI fileURI = URI.createPlatformResourceURI(modelFile.getFullPath().toString());

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
              Map options = new HashMap();
              options.put(XMLResource.OPTION_ENCODING, initialObjectCreationPage.getEncoding());
              resource.save(options);
            }
            catch (Exception exception)
            {
              Ecore2EcoreEditorPlugin.INSTANCE.log(exception);
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
        MessageDialog.openError(workbenchWindow.getShell(), Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_OpenEditorError_label"), exception.getMessage());
        return false;
      }

      return true;
    }
    catch (Exception exception)
    {
      Ecore2EcoreEditorPlugin.INSTANCE.log(exception);
      return false;
    }
  }

  /**
   * This is the one page of the wizard.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public class Ecore2EcoreModelWizardNewFileCreationPage extends WizardNewFileCreationPage
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
    public Ecore2EcoreModelWizardNewFileCreationPage(String pageId, IStructuredSelection selection)
    {
      super(pageId, selection);
    }

    /**
     * The framework calls this to see if the file is correct.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected boolean validatePage()
    {
      if (super.validatePage())
      {
        // Make sure the file ends in ".ecore2ecore".
        //
        String requiredExt = Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_Ecore2EcoreEditorFilenameExtension");
        String enteredExt = new Path(getFileName()).getFileExtension();
        if (enteredExt == null || !enteredExt.equals(requiredExt))
        {
          setErrorMessage(Ecore2EcoreEditorPlugin.INSTANCE.getString("_WARN_FilenameExtension", new Object [] { requiredExt }));
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
  public class Ecore2EcoreModelWizardInitialObjectCreationPage extends WizardPage
  {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected String initialObjectName;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Combo initialObjectField;

    /**
     * @generated
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     */
    protected String encoding;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected Combo encodingField;

    /**
     * Pass in the selection.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Ecore2EcoreModelWizardInitialObjectCreationPage(String pageId)
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
        containerLabel.setText(Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_ModelObject"));

        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        containerLabel.setLayoutData(data);
      }

      initialObjectField = new Combo(composite, SWT.BORDER);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        initialObjectField.setLayoutData(data);
      }

      List objectNames = new ArrayList();
      for (Iterator classifier = ecore2EcorePackage.getEClassifiers().iterator(); classifier.hasNext(); )
      {
        EClassifier eClassifier = (EClassifier)classifier.next();
        if (eClassifier instanceof EClass)
        {
          EClass eClass = (EClass)eClassifier;
          if (!eClass.isAbstract())
          {
            objectNames.add(getLabel(eClass));
          }
        }
      }

      Collections.sort(objectNames, java.text.Collator.getInstance());
      for (Iterator i = objectNames.iterator(); i.hasNext(); )
      {
        String objectName = (String)i.next();
        initialObjectField.add(objectName);
      }

      initialObjectField.addSelectionListener
        (new SelectionAdapter()
         {
           public void widgetSelected(SelectionEvent e)
           {
             setPageComplete(isPageComplete());
           }
         });

      Label encodingLabel = new Label(composite, SWT.LEFT);
      {
        encodingLabel.setText(Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_XMLEncoding"));

        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        encodingLabel.setLayoutData(data);
      }
      encodingField = new Combo(composite, SWT.BORDER);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        encodingField.setLayoutData(data);
      }

      for (StringTokenizer stringTokenizer = new StringTokenizer(Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_XMLEncodingChoices")); stringTokenizer.hasMoreTokens(); )
      {
        encodingField.add(stringTokenizer.nextToken());
      }
      encodingField.select(0);

      setControl(composite);
    }

    /**
     * The framework calls this to see if the file is correct.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
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
      initialObjectName = getInitialObjectName();
      encoding = getEncoding();
      return true;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getInitialObjectName()
    {
      if (initialObjectName != null)
      {
        return initialObjectName;
      }
      else
      {
        String label = initialObjectField.getText();
        for (Iterator classifier = ecore2EcorePackage.getEClassifiers().iterator(); classifier.hasNext(); )
        {
          EClassifier eClassifier = (EClassifier)classifier.next();
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
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getEncoding()
    {
      return
        encoding == null ?
          encodingField.getText() :
          encoding;
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
        return Ecore2EcorePlugin.INSTANCE.getString("_UI_" + name + "_type");
      }
      catch(MissingResourceException mre)
      {
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
  public void addPages()
  {
    // Create a page, set the title, and the initial model file name.
    //
    newFileCreationPage = new Ecore2EcoreModelWizardNewFileCreationPage("Whatever", selection);
    newFileCreationPage.setTitle(Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_Ecore2EcoreModelWizard_label"));
    newFileCreationPage.setDescription(Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_Ecore2EcoreModelWizard_description"));
    newFileCreationPage.setFileName(Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_Ecore2EcoreEditorFilenameDefaultBase") + "." + Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_Ecore2EcoreEditorFilenameExtension"));
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
          String defaultModelBaseFilename = Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_Ecore2EcoreEditorFilenameDefaultBase");
          String defaultModelFilenameExtension = Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_Ecore2EcoreEditorFilenameExtension");
          String modelFilename = defaultModelBaseFilename + "." + defaultModelFilenameExtension;
          for (int i = 1; ((IContainer)selectedResource).findMember(modelFilename) != null; ++i)
          {
            modelFilename = defaultModelBaseFilename + i + "." + defaultModelFilenameExtension;
          }
          newFileCreationPage.setFileName(modelFilename);
        }
      }
    }
    initialObjectCreationPage = new Ecore2EcoreModelWizardInitialObjectCreationPage("Whatever2");
    initialObjectCreationPage.setTitle(Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_Ecore2EcoreModelWizard_label"));
    initialObjectCreationPage.setDescription(Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_Wizard_initial_object_description"));
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