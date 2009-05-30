/**
 * <copyright>
 *
 * Copyright (c) 2009 Tom Schindl and others.
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
 * $Id: ApplicationActionBarAdvisor.java,v 1.2 2009/05/30 10:25:49 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;


/**
 * Advisor creating the tool and menu bar
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor
{
  private IWorkbenchAction save;
  private IWorkbenchAction exit;

  /**
   * Create a new advisor
   * @param configurer the configurer
   */
  public ApplicationActionBarAdvisor(IActionBarConfigurer configurer)
  {
    super(configurer);
  }

  @Override
  protected void makeActions(IWorkbenchWindow window)
  {
    save = ActionFactory.SAVE.create(window);
    register(save);

    exit = ActionFactory.CLOSE.create(window);
    register(exit);
  }

  @Override
  protected void fillMenuBar(IMenuManager menuBar)
  {
    MenuManager file = new MenuManager("&File");
    file.add(exit);
    file.add(new Separator());
    file.add(save);

    menuBar.add(file);
  }
}