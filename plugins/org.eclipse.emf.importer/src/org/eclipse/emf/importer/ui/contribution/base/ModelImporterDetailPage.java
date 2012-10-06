/**
 * Copyright (c) 2005-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.importer.ui.contribution.base;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.ProgressMonitorWrapper;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.ProgressMonitorPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.converter.ui.contribution.base.ModelConverterURIPage;
import org.eclipse.emf.converter.util.ConverterUtil;
import org.eclipse.emf.importer.ImporterPlugin;
import org.eclipse.emf.importer.ModelImporter;


/**
 * @since 2.2.0
 */
public class ModelImporterDetailPage extends ModelConverterURIPage implements IModelImporterPage
{
  protected Button loadButton;
  protected Text genModelNameText;

  protected String[] filterExtensions;

  protected boolean showGenModel = false;
  protected boolean usingInternalSetName = true;

  public ModelImporterDetailPage(ModelImporter modelImporter, String pageName)
  {
    super(modelImporter, pageName);
    showGenModel = getModelImporter().getGenModelFileName() == null;
  }

  public ModelImporter getModelImporter()
  {
    return (ModelImporter)getModelConverter();
  }

  @Override
  public void dispose()
  {
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
    super.dispose();
  }

  public boolean showGenModel()
  {
    return showGenModel;
  }

  @Override
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

  @Override
  protected void addControl(Composite parent)
  {
    addDetailControl(parent);
    if (showGenModel())
    {
      createGenModelNameControl(parent);
    }
    adjustLoadButton();
  }

  protected void addDetailControl(Composite parent)
  {
    // Subclasses may override
  }

  @Override
  protected String getURITextInitialValue()
  {
    String uri = getModelImporter().getModelLocation();
    return uri != null ? uri : super.getURITextInitialValue();
  }

  @Override
  protected void addURIControl(Composite parent)
  {
    loadButton = new Button(parent, SWT.PUSH);
    loadButton.setText(ImporterPlugin.INSTANCE.getString("_UI_Load_label"));
    loadButton.setLayoutData(new GridData(GridData.END));
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

  @Override
  protected void doHandleEvent(Event event)
  {
    if (event.type == SWT.Selection && event.widget == loadButton)
    {
      refreshModel();
      getContainer().updateButtons();
    }
    else if (event.type == SWT.Modify && event.widget == genModelNameText)
    {
      usingInternalSetName = false;
      getModelImporter().setGenModelFileName(genModelNameText.getText());
      Diagnostic diagnostic = getModelImporter().checkGenModelFileName();
      handleDiagnostic(diagnostic);
      getContainer().updateButtons();
    }
    else
    {
      super.doHandleEvent(event);
    }
  }

  @Override
  protected void uriTextModified(String text)
  {
    super.uriTextModified(text);
    getModelImporter().setModelLocation(null);
    getModelImporter().clearEPackagesCollections();
    adjustLoadButton();
  }

  protected void adjustLoadButton()
  {
    if (loadButton != null)
    {
      String text = uriText.getText();
      loadButton.setEnabled(text != null && text.trim().length() > 0);
    }
  }

  @Override
  protected String getURITextLabel()
  {
    return supportMultipleURIs() ?
      ImporterPlugin.INSTANCE.getString("_UI_ModelURIs_label") :
      ImporterPlugin.INSTANCE.getString("_UI_ModelURI_label");
  }

  protected String getSelectModelLabel()
  {
    return ImporterPlugin.INSTANCE.getString("_UI_SelectModel_label");
  }

  @Override
  protected boolean supportMultipleURIs()
  {
    return true;
  }

  protected String[] getFilterExtensions()
  {
    if (filterExtensions == null)
    {
      List<String> fileExtensions = getModelImporter().getFileExtensions();
      if (fileExtensions.isEmpty())
      {
        filterExtensions = new String []{ "*.*" };
      }
      else if (fileExtensions.size() == 1)
      {
        filterExtensions = new String[]{"*." + fileExtensions.get(0)};
      }
      else
      {
        StringBuffer allFilterExtensions = new StringBuffer();
        String[] extensions = new String [fileExtensions.size() + 1];
        for (int i = 1, lenght=extensions.length; i < lenght; i++)
        {
          extensions[i] = "*." + fileExtensions.get(i-1);
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
    if (resource.getType() == IResource.FILE)
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

  @Override
  protected boolean browseFileSystem()
  {
    FileDialog fileDialog = new FileDialog(getShell(), SWT.OPEN | (supportMultipleURIs() ? SWT.MULTI : SWT.SINGLE));
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
      setURIText(text.toString());
      refreshModel();
      return true;
    }
    return false;
  }

  @Override
  protected boolean browseWorkspace()
  {
    ViewerFilter extensionFilter = null;
    if (!getModelImporter().getFileExtensions().isEmpty())
    {
      extensionFilter = new ViewerFilter()
      {
        @Override
        public boolean select(Viewer viewer, Object parentElement, Object element)
        {
          return !(element instanceof IFile) || getModelImporter().getFileExtensions().contains(((IFile)element).getFileExtension());
        }
      };
    }

    IFile[] files = WorkspaceResourceDialog.openFileSelection(getShell(), null, null, supportMultipleURIs(), null, extensionFilter == null ? null : Collections.singletonList(extensionFilter));
    if (files.length > 0)
    {
      StringBuffer text = new StringBuffer();
      for (int i = 0; i < files.length; ++i)
      {
        if (isValidWorkspaceResource(files[i]))
        {
          text.append(URI.createPlatformResourceURI(files[i].getFullPath().toString(), true));
          text.append("  ");
        }
      }
      setURIText(text.toString());
      refreshModel();
      return true;
    }
    return false;
  }

  protected void refreshModel()
  {
    class InitializeOperation extends WorkspaceModifyOperation
    {
      boolean cancelButtonEnabled;
      boolean canceled;

      @Override
      protected void execute(final IProgressMonitor progressMonitor) throws CoreException
      {
        Diagnostic errorDiagnostic = null;
        setErrorMessage(null);
        setMessage(null);

        Monitor monitor = BasicMonitor.toMonitor
          (new ProgressMonitorWrapper(progressMonitor)
           {
             @Override
             public boolean isCanceled()
             {
               if (!cancelButtonEnabled)
               {
                 if (progressMonitor instanceof ProgressMonitorWrapper)
                 {
                   IProgressMonitor wrappedProgressMonitor = ((ProgressMonitorWrapper)progressMonitor).getWrappedProgressMonitor();
                   if (wrappedProgressMonitor instanceof ProgressMonitorPart)
                   {
                     try
                     {
                       ((ProgressMonitorPart)wrappedProgressMonitor).attachToCancelComponent(null);
                     }
                     catch (NullPointerException exception)
                     {
                       // Ignore the fact that this wizard container doesn't support a stop button.
                     }
                   }
                 }
                 cancelButtonEnabled = true;
               }
               while (getShell().getDisplay().readAndDispatch())
               {
                 // Process events so that the cancel button can be pushed.
               }
               return super.isCanceled();
             }
           });
        try
        {
          refreshModel(monitor);
        }
        catch (OperationCanceledException exception)
        {
          canceled = true;
        }
        catch (Exception exception)
        {
          ImporterPlugin.INSTANCE.log(exception);
          errorDiagnostic = ConverterUtil.createErrorDiagnostic(exception, true);
        }
        finally
        {
          monitor.done();
        }

        if (errorDiagnostic != null)
        {
          handleDiagnostic(errorDiagnostic, errorDiagnostic.getMessage(), ImporterPlugin.INSTANCE.getString("_UI_LoadProblem_title"), ImporterPlugin.INSTANCE.getString("_UI_ProblemsEncounteredProcessing_message"));
        }
      }
    }

    InitializeOperation initializeOperation = new InitializeOperation();

    getModelImporter().setModelLocation(uriText.getText());

    try
    {
      getContainer().run(false, true, initializeOperation);
    }
    catch (Exception exception)
    {
      ImporterPlugin.INSTANCE.log(exception);
    }

    if (!initializeOperation.canceled && isPageComplete())
    {
      setPageComplete(true);
    }
    else
    {
      setPageComplete(false);
      uriText.selectAll();
      uriText.setFocus();
    }
  }

  @Override
  public boolean isPageComplete()
  {
    return super.isPageComplete()
      && !getModelImporter().getEPackages().isEmpty()
      && !getModelImporter().getModelLocationURIs().isEmpty();
  }

  protected void refreshModel(Monitor monitor) throws Exception
  {
    Diagnostic diagnostic = null;
    try
    {
      diagnostic = getModelImporter().computeEPackages(monitor);
      getModelImporter().adjustEPackages(monitor);
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
    Diagnostic nameDiagnostic = getModelImporter().checkGenModelFileName();
    if (nameDiagnostic.getSeverity() != Diagnostic.OK)
    {
      if (diagnostic.getSeverity() == Diagnostic.OK)
      {
        diagnostic = nameDiagnostic;
      }
      else
      {
        diagnostic = ConverterUtil.mergeDiagnostic(diagnostic, nameDiagnostic);
      }
    }

    handleDiagnostic(diagnostic);
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
