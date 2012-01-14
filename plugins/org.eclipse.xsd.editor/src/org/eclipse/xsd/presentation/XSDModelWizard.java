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
package org.eclipse.xsd.presentation;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

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
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;

import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDConstants;
import org.eclipse.xsd.util.XSDResourceImpl;


/**
 * This is a simple wizard for creating a new model file.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class XSDModelWizard extends Wizard implements INewWizard
{
  /**
   *    * The file extensions supported by this editor.
   * <!-- begin-user-doc -->
   * @since 2.4
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<String> FILE_EXTENSIONS =
    Collections.unmodifiableList(Arrays.asList(XSDEditorPlugin.INSTANCE.getString("_UI_XSDEditorFilenameExtensions").split("\\s*,\\s*")));

  /**
   * A formatted list of supported file extensions, suitable for display.
   * <!-- begin-user-doc -->
   * @since 2.4
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String FORMATTED_FILE_EXTENSIONS =
    XSDEditorPlugin.INSTANCE.getString("_UI_XSDEditorFilenameExtensions").replaceAll("\\s*,\\s*", ", ");

  /**
This caches an instance of the model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDPackage xsdPackage = XSDPackage.eINSTANCE;

  /**
   * This caches an instance of the model factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDFactory xsdFactory = xsdPackage.getXSDFactory();

  /**
   * This is the file creation page.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDModelWizardNewFileCreationPage newFileCreationPage;

  /**
   * This is the file creation page.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDModelWizardInitialObjectCreationPage initialObjectCreationPage;

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
   * 
   */
  protected List<String> encodings;

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
    setDefaultPageImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(XSDEditorPlugin.INSTANCE.getImage("full/wizban/NewXSD")));
  }

  /**
   * Create a new model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected EObject createInitialModel()
  {
    return initialObjectCreationPage.createInitialModel(); 
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
              Resource resource = resourceSet.createResource(fileURI, XSDPackage.eCONTENT_TYPE);

              // Add the initial model object to the contents.
              //
              EObject rootObject = createInitialModel();
              if (rootObject != null)
              {
                resource.getContents().add(rootObject);
              }

              // Save the contents of the resource to the file system.
              //
              Map<Object, Object> options = new HashMap<Object, Object>();
              options.put(XSDResourceImpl.XSD_ENCODING, initialObjectCreationPage.getEncoding());
              resource.save(options);
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
        MessageDialog.openError(workbenchWindow.getShell(), XSDEditorPlugin.INSTANCE.getString("_UI_OpenEditorError_label"), exception.getMessage());
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
  public class XSDModelWizardNewFileCreationPage extends WizardNewFileCreationPage
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
    public XSDModelWizardNewFileCreationPage(String pageId, IStructuredSelection selection)
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
        String extension = new Path(getFileName()).getFileExtension();
        if (extension == null || !FILE_EXTENSIONS.contains(extension))
        {
          String key = FILE_EXTENSIONS.size() > 1 ? "_WARN_FilenameExtensions" : "_WARN_FilenameExtension";
          setErrorMessage(XSDEditorPlugin.INSTANCE.getString(key, new Object [] { FORMATTED_FILE_EXTENSIONS }));
          return false;
        }
        return true;
      }
      return false;
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
   * @generated NOT
   */
  public class XSDModelWizardInitialObjectCreationPage extends WizardPage
  {
    /**
     */
    protected Text schemaForSchemaPrefixText;

    /**
     */
    protected Text schemaForSchemaNamespaceText;

    /**
     */
    protected Text schemaPrefixText;

    /**
     */
    protected Text schemaNamespaceText;

    /**
     */
    protected Combo encodingField;

    protected boolean isCustomSchemaNamespace = false; 

    /**
     */
    public XSDModelWizardInitialObjectCreationPage(String pageId)
    {
      super(pageId);
    }

    /**
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

      Label schemaPrefixLabel = new Label(composite, SWT.LEFT);
      {
        schemaPrefixLabel.setText(XSDEditorPlugin.INSTANCE.getString("_UI_SchemaPrefix_label"));

        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        schemaPrefixLabel.setLayoutData(data);
      }

      schemaPrefixText = new Text(composite, SWT.SINGLE | SWT.BORDER);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        schemaPrefixText.setLayoutData(data);
        schemaPrefixText.setText("this");
      }

      Label schemaNamespaceLabel = new Label(composite, SWT.LEFT);
      {
        schemaNamespaceLabel.setText(XSDEditorPlugin.INSTANCE.getString("_UI_SchemaNamespaceURI_label"));

        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        schemaNamespaceLabel.setLayoutData(data);
      }

      schemaNamespaceText = new Text(composite, SWT.SINGLE | SWT.BORDER);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        schemaNamespaceText.setLayoutData(data);
        schemaNamespaceText.addKeyListener(new KeyListener()
          {
            public void keyReleased(KeyEvent e)
            {
              isCustomSchemaNamespace = true;
            }
        
            public void keyPressed(KeyEvent e)
            {
              // Ignore
            }
          });
      }

      Label schemaForSchemaPrefixLabel = new Label(composite, SWT.LEFT);
      {
        schemaForSchemaPrefixLabel.setText(XSDEditorPlugin.INSTANCE.getString("_UI_SchemaForSchemaPrefix_label"));

        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        schemaForSchemaPrefixLabel.setLayoutData(data);
      }

      schemaForSchemaPrefixText = new Text(composite, SWT.SINGLE | SWT.BORDER);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        schemaForSchemaPrefixText.setLayoutData(data);
        schemaForSchemaPrefixText.setText("xsd");
      }

      Label schemaForSchemaNamespaceLabel = new Label(composite, SWT.LEFT);
      {
        schemaForSchemaNamespaceLabel.setText(XSDEditorPlugin.INSTANCE.getString("_UI_SchemaForSchemaNamespaceURI_label"));

        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        schemaForSchemaNamespaceLabel.setLayoutData(data);
      }

      schemaForSchemaNamespaceText = new Text(composite, SWT.SINGLE | SWT.BORDER);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        schemaForSchemaNamespaceText.setLayoutData(data);
        schemaForSchemaNamespaceText.setText(XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);
      }

      Label encodingLabel = new Label(composite, SWT.LEFT);
      {
        encodingLabel.setText(XSDEditorPlugin.INSTANCE.getString("_UI_XMLEncoding"));

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

      for (String encoding : getEncodings())
      {
        encodingField.add(encoding);
      }

      encodingField.select(0);

      setControl(composite);
    }

    /**
     * Store the dialog field settings upon completion.
     */
    public boolean performFinish()
    {
      return true;
    }

    public EObject createInitialModel()
    {
      XSDSchema xsdSchema = XSDFactory.eINSTANCE.createXSDSchema();

      Map<String, String> map = xsdSchema.getQNamePrefixToNamespaceMap();
      map.put(schemaForSchemaPrefixText.getText(), schemaForSchemaNamespaceText.getText());
      xsdSchema.setSchemaForSchemaQNamePrefix(schemaForSchemaPrefixText.getText());
      if (schemaNamespaceText.getText() != null && schemaNamespaceText.getText().trim().length() != 0)
      {
        xsdSchema.setTargetNamespace(schemaNamespaceText.getText());
        map.put(schemaPrefixText.getText(), schemaNamespaceText.getText());
      }
      return xsdSchema;
    }

    @Override
    public void setVisible(boolean visible)
    {
      if (visible && !isCustomSchemaNamespace)
      {
        schemaNamespaceText.setText("http://" + getModelFile().getFullPath());
      }
      super.setVisible(visible);
    }

    public String getEncoding()
    {
      return encodingField.getText();
    }
  }

  protected Collection<String> getEncodings()
  {
    if (encodings == null)
    {
      encodings = new ArrayList<String>();
      for (StringTokenizer stringTokenizer = new StringTokenizer(XSDEditorPlugin.INSTANCE.getString("_UI_XMLEncodingChoices")); stringTokenizer.hasMoreTokens(); )
      {
        encodings.add(stringTokenizer.nextToken());
      }
    }
    return encodings;
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
    newFileCreationPage = new XSDModelWizardNewFileCreationPage("Whatever", selection);
    newFileCreationPage.setTitle(XSDEditorPlugin.INSTANCE.getString("_UI_XSDModelWizard_label"));
    newFileCreationPage.setDescription(XSDEditorPlugin.INSTANCE.getString("_UI_XSDModelWizard_description"));
    newFileCreationPage.setFileName(XSDEditorPlugin.INSTANCE.getString("_UI_XSDEditorFilenameDefaultBase") + "." + FILE_EXTENSIONS.get(0));
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
          String defaultModelBaseFilename = XSDEditorPlugin.INSTANCE.getString("_UI_XSDEditorFilenameDefaultBase");
          String defaultModelFilenameExtension = FILE_EXTENSIONS.get(0);
          String modelFilename = defaultModelBaseFilename + "." + defaultModelFilenameExtension;
          for (int i = 1; ((IContainer)selectedResource).findMember(modelFilename) != null; ++i)
          {
            modelFilename = defaultModelBaseFilename + i + "." + defaultModelFilenameExtension;
          }
          newFileCreationPage.setFileName(modelFilename);
        }
      }
    }
    initialObjectCreationPage = new XSDModelWizardInitialObjectCreationPage("Whatever2");
    initialObjectCreationPage.setTitle(XSDEditorPlugin.INSTANCE.getString("_UI_XSDModelWizard_label"));
    initialObjectCreationPage.setDescription(XSDEditorPlugin.INSTANCE.getString("_UI_Wizard_initial_object_description"));
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
