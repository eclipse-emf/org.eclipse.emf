/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: CodeGenUIPlugin.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.presentation;


import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import org.eclipse.emf.codegen.jet.JETException;


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
   * This keeps track of the one instance of this class.
   */
  private static CodeGenUIPlugin plugin;

  /**
   * Create the instance.
   */
  public CodeGenUIPlugin(IPluginDescriptor descriptor) 
  {
    super(descriptor);

    // Remember the static instance.
    //
    plugin = this;
  }

  /**
   * This fetches a resource string from the plugin.properites file.
   */
  public String getString(String key) 
  {
    return plugin.getDescriptor().getResourceBundle().getString(key);
  }

  public ImageDescriptor getImage(String key)
  {
    try
    {
      return ImageDescriptor.createFromURL(new URL(getDescriptor().getInstallURL() + "icons/" + key + ".gif"));
    }
    catch (MalformedURLException exception)
    {
      write(exception);
      return null;
    }
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
    IStatus status;
    String message;
    if (exception instanceof JETException)
    {
      status = ((JETException)exception).getStatus();
      message = getPlugin().getString("_UI_JETCompileProblem_message");
    }
    else if (exception instanceof CoreException)
    {
      status = ((CoreException)exception).getStatus();
      message = exception.getLocalizedMessage();
    }
    else
    {
      status = 
        new Status
          (IStatus.ERROR, 
           getPlugin().getDescriptor().getUniqueIdentifier(),
           0,
           exception.getLocalizedMessage(),
           exception);

      message = exception.getLocalizedMessage();
    }

    ErrorDialog.openError
      (getPlugin().getWorkbench().getActiveWorkbenchWindow().getShell(),
       getPlugin().getString("_UI_JETProblem_title"),
       message,
       status);
  }
}
