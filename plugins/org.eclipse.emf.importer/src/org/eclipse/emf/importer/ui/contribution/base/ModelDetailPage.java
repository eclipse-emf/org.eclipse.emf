/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * ModelDetailPage.java,v 1.1 2005/05/12 17:10:24 marcelop Exp
 */
package org.eclipse.emf.importer.ui.contribution.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;

import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.importer.ImporterPlugin;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.util.ImporterUtil;


/**
 * @since 2.1.0
 */
public class ModelDetailPage extends ModelImporterPage
{
  protected Text modelLocationText;
  protected Button loadButton;
  protected Text genModelNameText;
  protected Button modelLocationBrowseFileSystemButton;
  protected Button modelLocationBrowseWorkspaceButton;

  protected String[] filterExtensions;

  protected boolean showGenModel = false;
  protected boolean usingInternalSetName = true;
  

  public ModelDetailPage(ModelImporter modelImporter, String pageName)
  {
    super(modelImporter, pageName);
  }

  public void dispose()
  {
    if (modelLocationText != null)
    {
      modelLocationText.removeListener(SWT.Modify, this);
      modelLocationText = null;
    }
    if (loadButton != null)
    {
      loadButton.removeListener(SWT.Selection, this);
      loadButton = null;
    }    
    if (genModelNameText != null)
    {
      genModelNameText.removeListener(SWT.Modify, this);
      genModelNameText = null;
    }
    if (modelLocationBrowseFileSystemButton != null)
    {
      modelLocationBrowseFileSystemButton.removeListener(SWT.Selection, this);
      modelLocationBrowseFileSystemButton = null;
    }
    if (modelLocationBrowseWorkspaceButton != null)
    {
      modelLocationBrowseWorkspaceButton.removeListener(SWT.Selection, this);
      modelLocationBrowseWorkspaceButton = null;
    }

    super.dispose();
  }

  public void setShowGenModel(boolean showGenModel)
  {
    this.showGenModel = showGenModel;
  }

  public boolean showGenModel()
  {
    return showGenModel || getModelImporter().getGenModelFileName() == null;
  }

  protected void pageActivated(boolean firstTime, int cause)
  {
    if (firstTime)
    {
      if (getModelImporter().getOriginalGenModelPath() != null)
      {
        getControl().getDisplay().asyncExec(new Runnable()
          {
            public void run()
            {
              handleOriginalModelFile();
            }
          });
      }
    }
  }

  protected void handleOriginalModelFile()
  {
    refreshModel();
  }

  public void createControl(Composite parent)
  {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL));

    GridLayout layout = new GridLayout();
    layout.verticalSpacing = 8;
    composite.setLayout(layout);

    createModelLocationControl(composite);
    addControl(composite);
    if (showGenModel())
    {
      createGenModelNameControl(composite);
    }

    adjustLoadButton();
    setControl(composite);
  }

  protected void createModelLocationControl(Composite parent)
  {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));
    {
      GridLayout layout = new GridLayout(2, false);
      layout.marginLeft = -5;
      layout.marginRight = -5;
      composite.setLayout(layout);
    }
    
    Label modelLocationLabel = new Label(composite, SWT.LEFT);
    modelLocationLabel.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
    modelLocationLabel.setText(getModelLocationTextLabel());

    Composite buttonComposite = new Composite(composite, SWT.NONE);
    buttonComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.HORIZONTAL_ALIGN_END));
    {
      RowLayout layout = new RowLayout();
      layout.justify = true;
      layout.pack = true;
      layout.spacing = 5;
      layout.marginRight = 0;
      buttonComposite.setLayout(layout);
    }

    modelLocationBrowseFileSystemButton = new Button(buttonComposite, SWT.PUSH);
    modelLocationBrowseFileSystemButton.setText(getBrowseFileSystemButtonLabel());
    modelLocationBrowseFileSystemButton.addListener(SWT.Selection, this);

    modelLocationBrowseWorkspaceButton = new Button(buttonComposite, SWT.PUSH);
    modelLocationBrowseWorkspaceButton.setText(getBrowseWorkspaceButtonLabel());
    modelLocationBrowseWorkspaceButton.addListener(SWT.Selection, this);

    Composite modelLocationComposite = new Composite(parent, SWT.NONE);
    modelLocationComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    {
      GridLayout layout = new GridLayout(2, false);
      layout.marginTop = -5;
      layout.marginLeft = -5;
      layout.marginRight = -5;
      modelLocationComposite.setLayout(layout);
    }

    modelLocationText = new Text(modelLocationComposite, SWT.SINGLE | SWT.BORDER);
    modelLocationText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    if (getModelImporter().getModelLocation() != null)
    {
      modelLocationText.setText(getModelImporter().getModelLocation());
    }
    modelLocationText.addListener(SWT.Modify, this);
    
    createLoadButton(modelLocationComposite);
  }
  
  protected void createLoadButton(Composite parent)
  {
    loadButton = new Button(parent, SWT.PUSH);
    loadButton.setText(ImporterPlugin.INSTANCE.getString("_UI_Load_label"));
    GridData data = new GridData(GridData.END);
    data.widthHint = 50;
    loadButton.setLayoutData(data);
    loadButton.addListener(SWT.Selection, this);
  }

  protected void createGenModelNameControl(Composite parent)
  {
    Label genModelNameLabel = new Label(parent, SWT.LEFT);
    genModelNameLabel.setText(ImporterPlugin.INSTANCE.getString("_UI_GeneratorModelName_label"));

    genModelNameText = new Text(parent, SWT.SINGLE | SWT.BORDER);
    genModelNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    genModelNameText.addListener(SWT.Modify, this);
  }

  protected void addControl(Composite parent)
  {
  }
  
  protected void doHandleEvent(Event event)
  {
    if (event.type == SWT.Modify && event.widget == modelLocationText)
    {
      setErrorMessage(null);
      setMessage(null);
      getModelImporter().setModelLocation(null);
      getModelImporter().clearEPackagesCollections();
      adjustLoadButton();
    }
    else if (event.type == SWT.Selection && event.widget == loadButton)
    {
      refreshModel();
    }    
    else if (event.type == SWT.Modify && event.widget == genModelNameText)
    {
      usingInternalSetName = false;
      getModelImporter().setGenModelFileName(genModelNameText.getText());
      IStatus status = getModelImporter().checkGenModelFileName();
      handleStatus(status);
    }
    else if (event.type == SWT.Selection && event.widget == modelLocationBrowseFileSystemButton)
    {
      browseFileSystem();
    }
    else if (event.type == SWT.Selection && event.widget == modelLocationBrowseWorkspaceButton)
    {
      browseWorkspace();
    }
    else
    {
      super.doHandleEvent(event);
    }   
    getContainer().updateButtons();
  }
  
  protected void adjustLoadButton()
  {
    if (loadButton != null)
    {
      String text = modelLocationText.getText();
      loadButton.setEnabled(text != null && text.trim().length() > 0);
    }    
  }

  protected String getModelLocationTextLabel()
  {
    return ImporterPlugin.INSTANCE.getString("_UI_ModelLocation_label");
  }

  protected String getBrowseFileSystemButtonLabel()
  {
    return ImporterPlugin.INSTANCE.getString("_UI_BrowseFileSystemFile_label");
  }

  protected String getBrowseWorkspaceButtonLabel()
  {
    return ImporterPlugin.INSTANCE.getString("_UI_BrowseWorkspace_label");
  }

  protected String getSelectModelLabel()
  {
    return ImporterPlugin.INSTANCE.getString("_UI_SelectModel_label");
  }

  protected boolean supportMultipleModelLocation()
  {
    return true;
  }

  protected String[] getFilterExtensions()
  {
    if (filterExtensions == null)
    {
      List fileExtensions = getModelImporter().getFileExtensions();
      if (fileExtensions.isEmpty())
      {
        filterExtensions = new String []{ "*.*" };
      }
      else if (fileExtensions.size() == 1)
      {
        filterExtensions = new String[]{"*." + (String)fileExtensions.get(0)};
      }
      else
      {
        StringBuffer allFilterExtensions = new StringBuffer();
        String[] extensions = new String [fileExtensions.size() + 1];
        for (int i = 1, lenght=extensions.length; i < lenght; i++)
        {
          extensions[i] = "*." + (String)fileExtensions.get(i-1);
          allFilterExtensions.append(";").append(extensions[i]);
        }
        allFilterExtensions.deleteCharAt(0);
        extensions[0] = allFilterExtensions.toString();
        filterExtensions = extensions;
      }
    }
    return filterExtensions;
  }

  protected boolean isValidWorkspaceResource(IResource resource)
  {
    if (resource.getType() == IResource.FILE && !CodeGenUtil.isInJavaOutput(resource))
    {
      String[] filterExtensions = getFilterExtensions();
      if (filterExtensions.length > 0)
      {
        for (int i = 0; i < filterExtensions.length; i++)
        {
          if (filterExtensions[i].endsWith(".*") || filterExtensions[i].endsWith("." + resource.getFileExtension()))
          {
            return true;
          }
        }
      }

    }
    return false;
  }

  protected void setModelLocationText(String location)
  {
    location = location.trim();
    StringBuffer text = new StringBuffer(modelLocationText.getText());
    if (!location.equals(text))
    {
      if (supportMultipleModelLocation())
      {
        Point textSelection = modelLocationText.getSelection();
        text.delete(textSelection.x, textSelection.y);
        location = text.append(" ").append(location).toString();
      }
      modelLocationText.setText(location.trim());
    }
  }

  protected boolean browseFileSystem()
  {
    FileDialog fileDialog = new FileDialog(getShell(), SWT.OPEN | (supportMultipleModelLocation() ? SWT.MULTI : SWT.SINGLE));
    fileDialog.setFilterExtensions(getFilterExtensions());

    URI modelURI = getModelImporter().getFirstModelLocationURI(true);
    if (modelURI != null)
    {
      fileDialog.setFileName(modelURI.toFileString());
    }    
    
    if (fileDialog.open() != null && fileDialog.getFileNames().length > 0)
    {
      String[] fileNames = fileDialog.getFileNames();
      StringBuffer text = new StringBuffer();
      for (int i = 0; i < fileNames.length; ++i)
      {
        String filePath = fileDialog.getFilterPath() + File.separator + fileNames[i];
        text.append(URI.createFileURI(filePath).toString());
        text.append(" ");
      }
      setModelLocationText(text.toString());
      refreshModel();
      return true;
    }
    return false;
  }

  protected boolean browseWorkspace()
  {
    Collection resources = new ArrayList();
    ResourceSelectionDialog resourceSelectionDialog = new ResourceSelectionDialog(
      getShell(),
      ResourcesPlugin.getWorkspace().getRoot(),
      getSelectModelLabel());

    resourceSelectionDialog.open();
    Object[] result = resourceSelectionDialog.getResult();
    if (result != null)
    {
      StringBuffer text = new StringBuffer();
      int length = supportMultipleModelLocation() ? result.length : 1;
      for (int i = 0; i < length; ++i)
      {
        IResource resource = (IResource)result[i];
        if (isValidWorkspaceResource(resource))
        {
          text.append(URI.createURI(URI.createPlatformResourceURI(resource.getFullPath().toString()).toString(), true));
          text.append("  ");
        }
      }
      setModelLocationText(text.toString());
      refreshModel();
      return true;
    }
    return false;
  }

  protected void refreshModel()
  {
    WorkspaceModifyOperation initializeOperation = new WorkspaceModifyOperation()
      {
        protected void execute(IProgressMonitor progressMonitor) throws CoreException
        {
          IStatus errorStatus = null;
          setErrorMessage(null);
          setMessage(null);
          
          try
          {
            refreshModel(progressMonitor);
          }
          catch (Exception exception)
          {
            ImporterPlugin.INSTANCE.log(exception);
            errorStatus = ImporterUtil.createErrorStatus(exception, true);
          }
          finally
          {
            progressMonitor.done();
          }
          
          if (errorStatus != null)
          {
            handleStatus(errorStatus, errorStatus.getMessage(), ImporterPlugin.INSTANCE.getString("_UI_LoadProblem_title"), ImporterPlugin.INSTANCE.getString("_UI_ProblemsEncounteredProcessing_message"));
          }
        }
      };

    getModelImporter().setModelLocation(modelLocationText.getText());

    try
    {
      getContainer().run(false, false, initializeOperation);
    }
    catch (Exception exception)
    {
      ImporterPlugin.INSTANCE.log(exception);
    }

    if (isPageComplete())
    {
      setPageComplete(true);
    }
    else
    {
      setPageComplete(false);
      modelLocationText.selectAll();
      modelLocationText.setFocus();
    }
  }
  
  public boolean isPageComplete()
  {
    return super.isPageComplete() 
      && !getModelImporter().getEPackages().isEmpty() 
      && !getModelImporter().getModelLocationURIs().isEmpty();
  }  

  protected void refreshModel(IProgressMonitor progressMonitor) throws Exception
  {
    IStatus status = null;
    try
    {
      status = getModelImporter().computeEPackages(progressMonitor);
      getModelImporter().adjustEPackages(progressMonitor);
    }
    catch (WrappedException wrappedException)
    {
      if (wrappedException.exception() instanceof FileNotFoundException)
      {
        setMessage(null);
        setErrorMessage(ImporterPlugin.INSTANCE.getString("_UI_SpecifyAValidModel_message"));
        return;
      }
      else
      {
        throw wrappedException.exception();
      }
    }
    
    internalSetGenModelFileName(getDefaultGenModelFileName());
    IStatus nameStatus = getModelImporter().checkGenModelFileName();
    if (!nameStatus.isOK())
    {
      if (status.isOK())
      {
        status = nameStatus;
      }
      else
      {
        status = ImporterUtil.mergeStatus(status, nameStatus);
      }
    }
    
    String message = ImporterPlugin.INSTANCE.getString("_UI_ProblemsEncounteredProcessing_message");
    handleStatus(status);
  }
  
  protected String getDefaultGenModelFileName()
  {
    return getModelImporter().computeDefaultGenModelFileName();
  }
  
  protected void internalSetGenModelFileName(String name)
  {
    if (usingInternalSetName && showGenModel() && name != null)
    {
      getModelImporter().setGenModelFileName(name);
      setHandlingEvent(false);
      genModelNameText.setText(getModelImporter().getGenModelFileName());
      setHandlingEvent(true);
    }     
  }
}
