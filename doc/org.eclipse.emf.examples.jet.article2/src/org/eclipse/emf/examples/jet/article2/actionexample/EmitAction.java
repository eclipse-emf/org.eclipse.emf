package org.eclipse.emf.examples.jet.article2.actionexample;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.examples.jet.article2.TypesafeEnumPlugin;



/**
 * Example action that translates and generates text with selected JET
 * templates.
 * 
 * @author Remko Popma
 * @version $Revision: 1.6 $ ($Date: 2008/04/22 13:35:56 $)
 */
public class EmitAction implements IActionDelegate
{
  protected ISelection selection;

  public void selectionChanged(IAction action, ISelection selection)
  {
    this.selection = selection;
    action.setEnabled(true);
  }

  public void run(IAction action)
  {
    List<?> files = (selection instanceof IStructuredSelection) ? ((IStructuredSelection)selection).toList() : Collections.EMPTY_LIST;

    for (Iterator<?> i = files.iterator(); i.hasNext();)
    {
      IFile file = (IFile)i.next();
      IPath fullPath = file.getFullPath();

      String templateURI = URI.createPlatformResourceURI(fullPath.toString(), true).toString();
      ClassLoader classloader = getClass().getClassLoader();
      JETEmitter emitter = new JETEmitter(templateURI, classloader);

      // or: use anonymous subclass
      //JETEmitter emitter = new JETEmitter(templateURI) {}; // notice the
      // brackets

      try
      {
        String[] arguments = new String []{ "hi" };
        generate(emitter, arguments, file);

      }
      catch (Exception e)
      {
        handleException(e);
      }
    }
  }

  /**
   * Wraps text generation and save in a <code>WorkspaceModifyOperation</code>,
   * and runs this operation in a <code>ProgressMonitorDialog</code>.
   * 
   * @param emitter
   *          generates text to save
   * @param arguments
   *          arguments to pass to the emitter
   * @param file
   *          the original template file
   * @throws CoreException
   * @throws InvocationTargetException
   * @throws InterruptedException
   */
  private void generate(final JETEmitter emitter, final Object[] arguments, final IFile file) throws CoreException,
    InvocationTargetException,
    InterruptedException
  {

    WorkspaceModifyOperation op = new WorkspaceModifyOperation()
      {
        @Override
        public void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException
        {
          try
          {
            String generated = emitter.generate(monitor, arguments);
            saveGenerated(generated, file, monitor);
          }
          catch (JETException exception)
          {
            throw DiagnosticException.toCoreException(exception);
          }
          catch (IOException ioe)
          {
            throw new InvocationTargetException(ioe);
          }
        }
      };

    Shell shell = TypesafeEnumPlugin.getActiveWorkbenchShell();
    ProgressMonitorDialog dialog = new ProgressMonitorDialog(shell);
    dialog.run(false, true, op);
  }

  /**
   * Save the generated text to a file in the same location as the specified
   * file.
   * 
   * @param generated
   *          the generated text to save
   * @param file
   *          the original template file
   * @param monitor
   * @throws IOException
   * @throws CoreException
   */
  private void saveGenerated(String generated, IFile file, IProgressMonitor monitor) throws IOException, CoreException
  {

    InputStream contents = new ByteArrayInputStream(generated.getBytes());

    IContainer parent = file.getParent();
    IFile target = parent.getFile(new Path(file.getName() + ".txt"));

    if (target.exists())
    {
      target.setContents(contents, true, false, monitor);
    }
    else
    {
      File systemFile = target.getLocation().toFile();
      if (systemFile.exists())
      { // check if out of sync
        parent.refreshLocal(1, monitor); // not user-friendly: user did not
                                         // request Refresh...
        target.setContents(contents, true, false, monitor);
      }
      else
      {
        target.create(contents, false, monitor);
      }
    }
  }

  /**
   * Logs the exception and shows an error dialog.
   * 
   * @param t the exception to handle
   */
  private void handleException(Throwable t)
  {
    if (t instanceof InvocationTargetException)
    {
      t = ((InvocationTargetException)t).getTargetException();
    }
    TypesafeEnumPlugin.log(t);

    Shell shell = TypesafeEnumPlugin.getActiveWorkbenchShell();
    String title = "Error while emitting template";
    DiagnosticDialog.open(shell, title, null, BasicDiagnostic.toDiagnostic(t));
  }
}