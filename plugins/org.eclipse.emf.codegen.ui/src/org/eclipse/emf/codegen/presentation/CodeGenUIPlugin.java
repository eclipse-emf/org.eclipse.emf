/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.presentation;


import java.net.MalformedURLException;
import java.net.URL;
import java.text.MessageFormat;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.util.BasicDiagnostic;


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
  public CodeGenUIPlugin() 
  {
    super();

    // Remember the static instance.
    //
    plugin = this;
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
      return ImageDescriptor.createFromURL(new URL(getBundle().getEntry("/") + "icons/" + key + ".gif"));
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
    DiagnosticDialog.open
      (getPlugin().getWorkbench().getActiveWorkbenchWindow().getShell(),
       getPlugin().getString("_UI_JETProblem_title"),
       exception instanceof JETException ? 
         getPlugin().getString("_UI_JETCompileProblem_message") : 
         null,
       BasicDiagnostic.toDiagnostic(exception));
  }
}
