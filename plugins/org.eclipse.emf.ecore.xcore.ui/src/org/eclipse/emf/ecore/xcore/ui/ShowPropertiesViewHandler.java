/**
 * Copyright (c) 2020 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.xcore.ui;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.xcore.XcorePlugin;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;


public class ShowPropertiesViewHandler extends AbstractHandler
{
  public Object execute(ExecutionEvent event) throws ExecutionException
  {
    IWorkbenchWindow activeWorkbenchWindow = HandlerUtil.getActiveWorkbenchWindow(event);
    if (activeWorkbenchWindow != null)
    {
      IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
      if (activePage != null)
      {
        try
        {
          activePage.showView("org.eclipse.ui.views.PropertySheet");
        }
        catch (PartInitException exception)
        {
          XcorePlugin.INSTANCE.log(exception);
        }
      }
    }
    
    return null;
  }
}
