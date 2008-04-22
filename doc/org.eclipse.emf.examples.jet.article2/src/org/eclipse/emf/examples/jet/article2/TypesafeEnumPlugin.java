package org.eclipse.emf.examples.jet.article2;


import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;


/**
 * The plug-in runtime class for the TypesafeEnum plug-in.
 * 
 * @author Remko Popma
 * @version $Revision: 1.3 $ ($Date: 2008/04/22 13:35:56 $)
 */
public class TypesafeEnumPlugin extends AbstractUIPlugin
{

  /**
   * The single instance of this plug-in runtime class.
   */
  private static TypesafeEnumPlugin sPlugin = null;

  /**
   */
  public TypesafeEnumPlugin()
  {
    sPlugin = this;
  }

  public static ImageDescriptor getImageDescriptor(String name)
  {
    try
    {
      String base = getDefault().getBundle().getEntry("/").toString();
      String uri = base + name;
      return ImageDescriptor.createFromURL(new URL(uri));

    }
    catch (MalformedURLException e)
    {
      return ImageDescriptor.getMissingImageDescriptor();
    }
  }

  public static TypesafeEnumPlugin getDefault()
  {
    return sPlugin;
  }

  public static Shell getActiveWorkbenchShell()
  {
    IWorkbenchWindow workBenchWindow = getActiveWorkbenchWindow();
    if (workBenchWindow == null)
    {
      return null;
    }
    return workBenchWindow.getShell();
  }

  /**
   * Returns the active workbench window
   * 
   * @return the active workbench window
   */
  public static IWorkbenchWindow getActiveWorkbenchWindow()
  {
    if (sPlugin == null)
    {
      return null;
    }
    IWorkbench workBench = sPlugin.getWorkbench();
    if (workBench == null)
    {
      return null;
    }
    return workBench.getActiveWorkbenchWindow();
  }

  public static IWorkbenchPage getActivePage()
  {
    IWorkbenchWindow activeWorkbenchWindow = getActiveWorkbenchWindow();
    if (activeWorkbenchWindow == null)
    {
      return null;
    }
    return activeWorkbenchWindow.getActivePage();
  }

  public static String getPluginId()
  {
    return getDefault().getBundle().getSymbolicName();
  }

  public static void log(Throwable e)
  {
    log(BasicDiagnostic.toDiagnostic(e));
  }

  public static void log(Diagnostic diagnostic)
  {
    log(BasicDiagnostic.toIStatus(diagnostic));
  }

  public static void log(IStatus status)
  {
    getDefault().getLog().log(status);
  }
}