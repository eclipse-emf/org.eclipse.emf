/**
 * Copyright (c) 2005-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.converter.ui.contribution.base;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IPageChangeProvider;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWizard;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;

import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.converter.ConverterPlugin;
import org.eclipse.emf.converter.ModelConverter;
import org.eclipse.emf.converter.util.ConverterUIUtil;
import org.eclipse.emf.converter.util.ConverterUtil;
import org.eclipse.emf.edit.provider.IDisposable;


/**
 * @since 2.2.0
 */
public abstract class ModelConverterWizard extends Wizard implements IWorkbenchWizard
{
  public static class PageHelper implements IPageChangedListener, IDisposable
  {
    protected boolean attached = false;
    protected Object oldPage;
    protected IPageChangeProvider pageChangeProvider;

    public void dispose()
    {
      oldPage = null;
      pageChangeProvider = null;
    }
    
    public void setAttached(boolean attached)
    {
      this.attached = attached;
    }
    
    public boolean isAttached()
    {
      return attached;
    }
    
    public void pageChanged(PageChangedEvent event)
    {
      pageChangeProvider = event.getPageChangeProvider();
      pageChanged(event.getSelectedPage());
    }

    protected void pageChanged(Object currentPage)
    {
      if (oldPage != currentPage)
      {
        int cause = ModelConverterPage.CAUSE_UNKNOWN;
        if (oldPage instanceof ModelConverterPage)
        {
          ModelConverterPage page = (ModelConverterPage)oldPage;
          cause = page.forwardDirection ? ModelConverterPage.CAUSE_NEXT : ModelConverterPage.CAUSE_BACK;
          page.pageDeactivated(cause);
        }

        oldPage = currentPage;
        if (currentPage instanceof ModelConverterPage)
        {
          ModelConverterPage page = (ModelConverterPage)currentPage;
          page.pageActivated(page.neverVisible, cause);
          page.neverVisible = false;
        }
      }
    }

    public void firePageDeactivated(int cause)
    {
      if (pageChangeProvider != null && pageChangeProvider.getSelectedPage() instanceof ModelConverterPage)
      {
        ((ModelConverterPage)pageChangeProvider.getSelectedPage()).pageDeactivated(cause);
      }
    }
  }

  // WizardContainer variables
  protected static PageHelper pageHelper;
  
  protected IStructuredSelection selection;
  protected IWorkbench workbench;
    
  protected ModelConverter modelConverter;
  
  protected Diagnostic doPerformFinishDiagnostic;
  protected ConverterUIUtil.DiagnosticHandler diagnosticHandler;

  public ModelConverterWizard()
  {
    super();
    setNeedsProgressMonitor(true);
    if (pageHelper == null)
    {
      pageHelper = new PageHelper();
    }
  }

  @Override
  public void dispose()
  {
    selection = null;
    workbench = null;
    doPerformFinishDiagnostic = null;
    diagnosticHandler = null;

    if (modelConverter != null)
    {
      modelConverter.dispose();
      modelConverter = null;
    }

    if (pageHelper != null)
    {
      if (getContainer() instanceof WizardDialog)
      {
        ((WizardDialog)getContainer()).removePageChangedListener(pageHelper);
      }
      pageHelper.dispose();
      pageHelper = null;
    }

    super.dispose();
  }

  protected abstract ModelConverter createModelConverter();

  protected ModelConverter getModelConverter()
  {
    if (modelConverter == null)
    {
      modelConverter = createModelConverter();
    }
    return modelConverter;
  }

  public void init(IWorkbench workbench, IStructuredSelection selection)
  {
    this.workbench = workbench;
    this.selection = selection;
  }

  public IWorkbench getWorkbench()
  {
    return workbench;
  }

  public IStructuredSelection getSelection()
  {
    return selection;
  }
  
  @Override
  public void setContainer(IWizardContainer wizardContainer)
  {
    super.setContainer(wizardContainer);
    if (wizardContainer instanceof WizardDialog && pageHelper != null && !pageHelper.isAttached())
    {      
      pageHelper.setAttached(true);
      ((WizardDialog)wizardContainer).addPageChangedListener(pageHelper);
    }
  }

  @Override
  public boolean performCancel()
  {
    pageHelper.firePageDeactivated(ModelConverterPage.CAUSE_CANCEL);
    return true;
  }

  @Override
  public boolean performFinish()
  {
    pageHelper.firePageDeactivated(ModelConverterPage.CAUSE_FINISH);

    try
    {
      WorkspaceModifyOperation operation = new WorkspaceModifyOperation()
        {
          @Override
          protected void execute(IProgressMonitor progressMonitor) throws CoreException
          {
            Monitor monitor = BasicMonitor.toMonitor(progressMonitor);
            try
            {
              doPerformFinishDiagnostic = null;
              doPerformFinishDiagnostic = doPerformFinish(monitor);
            }
            catch (Exception exception)
            {
              DiagnosticException diagnosticException = exception instanceof DiagnosticException ?
                (DiagnosticException)exception 
                : new DiagnosticException(ConverterUtil.createErrorDiagnostic(exception, true));
              throw DiagnosticException.toCoreException(diagnosticException);
            }
            finally
            {
              monitor.done();
            }
          }
        };

      try
      {
        getContainer().run(false, false, operation);
      }
      catch (Exception exception)
      {
        ConverterPlugin.INSTANCE.log(exception);
        DiagnosticDialog.open(getShell(), ConverterPlugin.INSTANCE.getString("_UI_DialogError_title"), null, ConverterUtil.createErrorDiagnostic(exception, true));
        return false;
      }
      
      handleConvertDiagnostic(doPerformFinishDiagnostic);
      return true;
    }
    catch (Exception exception)
    {
      ConverterPlugin.INSTANCE.log(exception);
      return false;
    }
  }
  
  protected void handleConvertDiagnostic(Diagnostic diagnostic)
  {
    if (diagnostic != null)
    {
      if (diagnosticHandler == null)
      {
        diagnosticHandler = new ConverterUIUtil.DiagnosticHandler();
      }
      diagnosticHandler.handleDiagnostic(diagnostic);
    }
  }
    
  /**
   * <p>Subclasses should overwrite this method, adding the code that performs the
   * actions required when this wizard is &quot;finished&quot;.</p>
   * <p>The {@link Diagnostic} returned by this method should be used to provide
   * the user some information regarding a <b>successful</b> conversion.  If the
   * conversion fails, an exception should be thrown.<p>
   * @param monitor
   * @return {@link Diagnostic}
   * @throws Exception
   */
  protected Diagnostic doPerformFinish(Monitor monitor) throws Exception
  {
    return Diagnostic.OK_INSTANCE;
  }

  protected IFile getFile(IPath path)
  {
    return ResourcesPlugin.getWorkspace().getRoot().getFile(path);
  }

  protected void selectFile(IFile file)
  {
    IWorkbenchPage page = getWorkbench().getActiveWorkbenchWindow().getActivePage();
    final IWorkbenchPart activePart = page.getActivePart();
    if (activePart instanceof ISetSelectionTarget)
    {
      final ISelection targetSelection = new StructuredSelection(file);
      getShell().getDisplay().asyncExec(new Runnable()
        {
          public void run()
          {
            ((ISetSelectionTarget)activePart).selectReveal(targetSelection);
          }
        });
    }
  }

  protected void openEditor(IFile file) throws PartInitException
  {
    IWorkbenchPage page = getWorkbench().getActiveWorkbenchWindow().getActivePage();
    IEditorDescriptor defaultEditor = getWorkbench().getEditorRegistry().getDefaultEditor(file.getFullPath().toString());
    page.openEditor
      (new FileEditorInput(file), 
       defaultEditor == null ? "org.eclipse.emf.codegen.ecore.genmodel.presentation.GenModelEditorID" : defaultEditor.getId());
  }
}
