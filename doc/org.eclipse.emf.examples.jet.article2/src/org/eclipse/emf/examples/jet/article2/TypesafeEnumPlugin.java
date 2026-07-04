/**
 * Copyright (c) 2005-2026 Remko Popma and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.examples.jet.article2;


import java.net.MalformedURLException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.CommonUtil;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;


/**
 * The plug-in runtime class for the TypesafeEnum plug-in.
 * 
 * @author Remko Popma
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
      return ImageDescriptor.createFromURL(CommonUtil.newURL(uri));

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
    IWorkbench workBench = PlatformUI.getWorkbench();
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