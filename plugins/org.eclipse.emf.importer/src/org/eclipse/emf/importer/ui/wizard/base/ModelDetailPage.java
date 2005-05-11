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
 * $Id: ModelDetailPage.java,v 1.2 2005/05/11 14:53:52 marcelop Exp $
 */
package org.eclipse.emf.importer.ui.wizard.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.importer.ImporterPlugin;
import org.eclipse.emf.importer.ModelImporter;


/**
 * @since 2.1.0
 */
public class ModelDetailPage extends ModelImporterPage
{
  protected Text modelLocationText;
  protected Text genModelNameText;
  protected Button modelLocationBrowseFileSystemButton;
  protected Button modelLocationBrowseWorkspaceButton;

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

  protected void pageActivated(boolean firstTime)
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

    GridLayout layout = new GridLayout();
    layout.numColumns = 2;
    layout.verticalSpacing = 12;
    composite.setLayout(layout);

    GridData data = new GridData();
    data.verticalAlignment = GridData.FILL;
    data.grabExcessVerticalSpace = true;
    data.horizontalAlignment = GridData.FILL;
    composite.setLayoutData(data);

    createModelLocationControl(composite);
    addControl(composite);
    if (showGenModel())
    {
      createGenModelNameControl(composite);
    }

    setControl(composite);
  }

  protected void createModelLocationControl(Composite parent)
  {
    Label modelLocationLabel = new Label(parent, SWT.LEFT);
    modelLocationLabel.setText(getModelLocationTextLabel());
    {
      GridData data = new GridData();
      data.horizontalAlignment = GridData.FILL;
      modelLocationLabel.setLayoutData(data);
    }

    Composite buttonComposite = new Composite(parent, SWT.NONE);
    {
      GridData data = new GridData();
      data.horizontalAlignment = GridData.END;
      buttonComposite.setLayoutData(data);

      RowLayout layout = new RowLayout();
      layout.justify = true;
      layout.pack = true;
      layout.spacing = 15;
      buttonComposite.setLayout(layout);
    }

    modelLocationBrowseFileSystemButton = new Button(buttonComposite, SWT.PUSH);
    modelLocationBrowseFileSystemButton.setText(getBrowseFileSystemButtonLabel());
    modelLocationBrowseFileSystemButton.addListener(SWT.Selection, this);

    modelLocationBrowseWorkspaceButton = new Button(buttonComposite, SWT.PUSH);
    modelLocationBrowseWorkspaceButton.setText(getBrowseWorkspaceButtonLabel());
    modelLocationBrowseWorkspaceButton.addListener(SWT.Selection, this);

    modelLocationText = new Text(parent, SWT.SINGLE | SWT.BORDER);
    {
      GridData data = new GridData();
      data.horizontalAlignment = GridData.FILL;
      data.grabExcessHorizontalSpace = true;
      data.horizontalSpan = 3;
      modelLocationText.setLayoutData(data);
    }

    if (getModelImporter().getModelLocation() != null)
    {
      modelLocationText.setText(getModelImporter().getModelLocation());
    }
    modelLocationText.addListener(SWT.Modify, this);
    modelLocationText.setFocus();
  }

  protected void createGenModelNameControl(Composite parent)
  {
    Label genModelNameLabel = new Label(parent, SWT.LEFT);
    {
      genModelNameLabel.setText(ImporterPlugin.INSTANCE.getString("_UI_GeneratorModelName_label"));

      GridData data = new GridData();
      data.horizontalAlignment = GridData.FILL;
      data.horizontalSpan = 2;
      genModelNameLabel.setLayoutData(data);
    }

    genModelNameText = new Text(parent, SWT.SINGLE | SWT.BORDER);
    {
      GridData data = new GridData();
      data.horizontalAlignment = GridData.FILL;
      data.grabExcessHorizontalSpace = true;
      data.horizontalSpan = 2;
      genModelNameText.setLayoutData(data);
    }

    genModelNameText.addListener(SWT.Modify, this);
  }

  protected void addControl(Composite parent)
  {
  }

  protected void doHandleEvent(Event event)
  {
    if (event.type == SWT.Modify && event.widget == genModelNameText)
    {
      usingInternalSetName = false;
      getModelImporter().setGenModelFileName(genModelNameText.getText());
      IStatus status = getModelImporter().checkGenModelFileName();
      setErrorMessage(status.isOK() ? null : status.getMessage());
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
      super.handleEvent(event);
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

  protected boolean singleModelLocationSelection()
  {
    return true;
  }

  protected String[] getFilterExtensions()
  {
    return new String []{ "*.*" };
  }

  protected boolean isValidWorkspaceResource(IResource resource)
  {
    if (resource.getType() == IResource.FILE && !isInJavaOutput(resource))
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
      if (!singleModelLocationSelection())
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
    FileDialog fileDialog = new FileDialog(getShell(), SWT.OPEN | (singleModelLocationSelection() ? SWT.SINGLE : SWT.MULTI));
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
      int length = singleModelLocationSelection() ? 1 : result.length;
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
          
          try
          {
            refreshModel(progressMonitor);
          }
          catch (Exception exception)
          {
            ImporterPlugin.INSTANCE.log(exception);
            
            String message = exception.getLocalizedMessage();
            if (message == null)
            {
              message = exception.getMessage();
            }
            if (message == null)
            {
              String exceptionName = exception.getClass().getName();
              int index = exceptionName.lastIndexOf('.');
              if (index >= 0)
              {
                exceptionName = exceptionName.substring(index+1);
              }
              message = ImporterPlugin.INSTANCE.getString("_UI_GenericException_message", new Object[]{exceptionName});
            }

            errorStatus = new Status(
              IStatus.ERROR,
              ImporterPlugin.getPlugin().getBundle().getSymbolicName(),
              0,
              message,
              exception);            
          }
          finally
          {
            progressMonitor.done();
          }
          
          if (errorStatus != null)
          {
            setErrorMessage(errorStatus.getMessage());
            ErrorDialog.openError(
              getShell(),
              ImporterPlugin.INSTANCE.getString("_UI_LoadProblem_title"),
              ImporterPlugin.INSTANCE.getString("_UI_ProblemsEncounteredProcessing_message"),
              errorStatus);
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

  protected void refreshModel(IProgressMonitor progressMonitor) throws Exception
  {
    IStatus status = null;
    try
    {
      status = getModelImporter().computeEPackages(progressMonitor);
    }
    catch (WrappedException wrappedException)
    {
      if (wrappedException.exception() instanceof FileNotFoundException)
      {
        setErrorMessage(ImporterPlugin.INSTANCE.getString("_UI_SpecifyAValidModel_message"));
        return;
      }
      else
      {
        throw wrappedException.exception();
      }
    }
    
    internalSetGenModelFileName(getModelImporter().computeDefaultGenModelFileName());
    IStatus nameStatus = getModelImporter().checkGenModelFileName();
    if (!nameStatus.isOK())
    {
      if (status.isOK())
      {
        status = nameStatus;
      }
      else if (status.isMultiStatus() && status instanceof MultiStatus)
      {
        ((MultiStatus)status).add(nameStatus);
      }
    }
    
    if (status.isOK())
    {
      setErrorMessage(null);
    }
    else
    {
      setErrorMessage(status.getMessage());
      ErrorDialog.openError(
        getShell(),
        ImporterPlugin.INSTANCE.getString("_UI_LoadProblem_title"),
        ImporterPlugin.INSTANCE.getString("_UI_ProblemsEncounteredProcessing_message"),
        status);
    }
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
