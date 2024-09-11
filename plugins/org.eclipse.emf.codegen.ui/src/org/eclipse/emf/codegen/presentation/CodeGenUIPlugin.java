/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.presentation;


import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.CommonUtil;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.plugin.AbstractUIPlugin;


/**
 * This is the central singleton for the emf.codegen plugin.
 */
public class CodeGenUIPlugin extends AbstractUIPlugin
{
  /**
   * Get the singleton instance.
   */
  public static CodeGenUIPlugin getPlugin()
  {
    return plugin;
  }

  /**
   * Returns the resource bundle for the plugin.properties.
   *
   * @since 2.19
   */
  public static ResourceBundle getResourceBundle()
  {
    return Platform.getResourceBundle(plugin.getBundle());
  }

  /**
   * This keeps track of the one instance of this class.
   */
  private static CodeGenUIPlugin plugin;

  /**
   * Create the instance.
   */
  public CodeGenUIPlugin()
  {
    super();

    // Remember the static instance.
    //
    plugin = this;
  }

  /**
   * @since 2.19
   */
  public IDialogSettings getDialogSettingsSection(String name)
  {
    IDialogSettings dialogSettings = getDialogSettings();
    IDialogSettings section = dialogSettings.getSection(name);
    if (section == null)
    {
      section = dialogSettings.addNewSection(name);
    }
    return section;
  }

  /**
   * This fetches a resource string from the plugin.properites file.
   */
  public String getString(String key)
  {
    return Platform.getResourceBundle(plugin.getBundle()).getString(key);
  }

  public ImageDescriptor getImage(String key)
  {
    try
    {
      String suffix = key.endsWith(".png") ? "" : ".gif";
      return ImageDescriptor.createFromURL(CommonUtil.newURL(getBundle().getEntry("/") + "icons/" + key + suffix));
    }
    catch (MalformedURLException exception)
    {
      write(exception);
      return null;
    }
  }

  public Image getActualImage(String key)
  {
    try
    {
      String suffix = key.endsWith(".png") ? "" : ".gif";
      URL url = CommonUtil.newURL(getBundle().getEntry("/") + "icons/" + key + suffix);
      InputStream in = null;
      try
      {
        in = url.openStream();
        return new Image(Display.getDefault(), in);
      }
      finally
      {
        if (in != null)
        {
          in.close();
        }
      }
    }
    catch (Exception exception)
    {
      throw new RuntimeException(exception);
    }
  }

  public void log(Object logEntry)
  {
    IStatus status;
    if (logEntry instanceof IStatus)
    {
      status = (IStatus)logEntry;
      getLog().log(status);
    }
    else
    {
      if (logEntry == null)
      {
        logEntry = new RuntimeException(getString("_UI_NullLogEntry_exception")).fillInStackTrace();
      }

      if (logEntry instanceof Throwable)
      {
        getLog().log(toStatus(IStatus.WARNING, (Throwable)logEntry));
      }
      else
      {
        getLog().log(new Status(IStatus.WARNING, getBundle().getSymbolicName(), 0, logEntry.toString(), null));
      }
    }
  }

  /**
   * @since 2.19
   */
  public static IStatus toStatus(int severity, Throwable throwable)
  {
    String message = throwable.getLocalizedMessage();
    if (message == null)
    {
      Throwable cause = throwable.getCause();
      if (cause != null)
      {
        message = cause.getLocalizedMessage();
      }
      if (message == null)
      {
        message = "";
      }
    }
    return new Status(severity, getPlugin().getBundle().getSymbolicName(), 0, message, throwable);
  }

  /**
   * This fetches a resource string from the plugin.properites file and performs message substitution.
   */
  public String getString(String key, Object[] objects)
  {
    return MessageFormat.format(getString(key), objects);
  }

  public static void write(Exception exception)
  {
    DiagnosticDialog.open(
      PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
      getPlugin().getString("_UI_JETProblem_title"),
      exception instanceof JETException ? getPlugin().getString("_UI_JETCompileProblem_message") : null,
      BasicDiagnostic.toDiagnostic(exception));
  }
}
