package org.eclipse.emf.examples.jet.article2.ui;


import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.wizards.newresource.BasicNewResourceWizard;

import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.examples.jet.article2.TypesafeEnumPlugin;
import org.eclipse.emf.examples.jet.article2.codegen.Config;
import org.eclipse.emf.examples.jet.article2.codegen.JETGateway;


/**
 * This <code>Wizard</code> sets up the wizard pages for the typesafe
 * enumeration
 * 
 * @author Remko Popma
 * @version $Revision: 1.3 $ ($Date: 2007/01/26 06:13:24 $)
 */
public class NewTypesafeEnumCreationWizard extends Wizard implements INewWizard
{

  private IWorkbench mWorkbench;

  private IStructuredSelection mSelection;

  private static final String DIALOG_SETTINGS_KEY = "NewTypesafeEnumCreationWizard";

  private NewTypesafeEnumCreationWizardPage mPage1;

  private NewTypesafeEnumCreationWizardPageAttributes mPage2;

  private NewTypesafeEnumCreationWizardPageInstances mPage3;

  /**
   * Constructs a <code>NewTypesafeEnumCreationWizard</code>.
   */
  public NewTypesafeEnumCreationWizard()
  {
    super();
    setNeedsProgressMonitor(true);
    setDefaultPageImageDescriptor(TypesafeEnumPlugin.getImageDescriptor("icons/newclass_wiz.gif"));
    initDialogSettings();
    setWindowTitle(WizardMessages.getString("Wizard.title.new")); //$NON-NLS-1$
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
   *      org.eclipse.jface.viewers.IStructuredSelection)
   */
  public void init(IWorkbench workbench, IStructuredSelection currentSelection)
  {
    mWorkbench = workbench;
    mSelection = currentSelection;
  }

  /*
   * @see Wizard#addPages
   */
  @Override
  public void addPages()
  {
    super.addPages();

    mPage1 = new NewTypesafeEnumCreationWizardPage();
    addPage(mPage1);
    mPage1.init(getSelection());

    mPage2 = new NewTypesafeEnumCreationWizardPageAttributes();
    addPage(mPage2);

    mPage3 = new NewTypesafeEnumCreationWizardPageInstances();
    addPage(mPage3);
  }

  /**
   * Obtains the user input from the wizard pages, creates a <code>Config</code>
   * object that contains all this information, and delegates the source code
   * generation to a <code>JETGateway</code> object. Finally, the generated
   * Java source code file is opened in an editor.
   */
  protected void finishPage(IProgressMonitor monitor) 
  throws InterruptedException, CoreException
  {
    Config config = new Config();
    config.setModel(mPage1.getTypesafeEnumModel());
    config.setPackageName(mPage1.getPackageText());
    config.setTargetFile(mPage1.getTypeName() + ".java");
    config.setTargetFolder(mPage1.getPackageFragmentRootText());

    config.setClasspathVariable("JET_TUTORIAL");
    config.setPluginId(TypesafeEnumPlugin.getPluginId());
    config.setTemplateRelativeUri("templates/TypeSafeEnumeration.javajet");

    JETGateway gateway = new JETGateway(config);
    String content = gateway.generate(monitor);
    IFile file = gateway.save(monitor, content.getBytes());

    selectAndReveal(file);
    openResource(file);
  }

  /*
   * @see Wizard#performFinish
   */
  @Override
  public boolean performFinish()
  {
    IWorkspaceRunnable op = new IWorkspaceRunnable()
      {
        public void run(IProgressMonitor monitor) throws CoreException, OperationCanceledException
        {
          try
          {
            finishPage(monitor);
          }
          catch (InterruptedException e)
          {
            throw new OperationCanceledException(e.getMessage());
          }
        }
      };

    try
    {
      getContainer().run(false, true, new WorkbenchRunnableAdapter(op));
    }
    catch (InvocationTargetException e)
    {
      handleFinishException(getShell(), e);
      return false;
    }
    catch (InterruptedException e)
    {
      return false;
    }
    return true;
  }

  /**
   * An <code>IRunnableWithProgress</code> that adapts
   * <code>IWorkspaceRunnable</code> so that it can be executed inside
   * <code>IRunnableContext</code>.<code>OperationCanceledException</code>
   * thrown by the apapted runnabled are caught and rethrown as a
   * <code>InterruptedException</code>.
   */
  private static class WorkbenchRunnableAdapter implements IRunnableWithProgress
  {

    private IWorkspaceRunnable fWorkspaceRunnable;

    public WorkbenchRunnableAdapter(IWorkspaceRunnable runnable)
    {
      fWorkspaceRunnable = runnable;
    }

    /*
     * @see IRunnableWithProgress#run(IProgressMonitor)
     */
    public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
    {
      try
      {
        JavaCore.run(fWorkspaceRunnable, monitor);
      }
      catch (OperationCanceledException e)
      {
        throw new InterruptedException(e.getMessage());
      }
      catch (CoreException e)
      {
        throw new InvocationTargetException(e);
      }
    }
  }

  protected void handleFinishException(Shell shell, InvocationTargetException e)
  {
    Diagnostic diagnostic = BasicDiagnostic.toDiagnostic(e);
    TypesafeEnumPlugin.log(diagnostic);

    String title = WizardMessages.getString("Wizard.op_error.title"); //$NON-NLS-1$
    String message = WizardMessages.getString("Wizard.op_error.message"); //$NON-NLS-1$
    DiagnosticDialog.open(shell, title, message, diagnostic);
  }

  protected void openResource(final IResource resource)
  {
    if (resource.getType() == IResource.FILE)
    {
      final IWorkbenchPage activePage = TypesafeEnumPlugin.getActivePage();
      if (activePage != null)
      {
        final Display display = getShell().getDisplay();
        if (display != null)
        {
          display.asyncExec(new Runnable()
            {
              public void run()
              {
                try
                {
                  IDE.openEditor(activePage, (IFile)resource, true);
                }
                catch (PartInitException e)
                {
                  TypesafeEnumPlugin.log(e);
                }
              }
            });
        }
      }
    }
  }

  protected void selectAndReveal(IResource newResource)
  {
    BasicNewResourceWizard.selectAndReveal(newResource, mWorkbench.getActiveWorkbenchWindow());
  }

  public IStructuredSelection getSelection()
  {
    return mSelection;
  }

  protected void initDialogSettings()
  {
    IDialogSettings pluginSettings = TypesafeEnumPlugin.getDefault().getDialogSettings();
    IDialogSettings wizardSettings = pluginSettings.getSection(DIALOG_SETTINGS_KEY);
    if (wizardSettings == null)
    {
      wizardSettings = new DialogSettings(DIALOG_SETTINGS_KEY);
      pluginSettings.addSection(wizardSettings);
    }
    setDialogSettings(wizardSettings);
  }
}