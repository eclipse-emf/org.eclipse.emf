/**
 * <copyright>
 *
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ApplicationActionBarAdvisor.java,v 1.4 2009/06/01 17:19:26 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp;

import org.eclipse.jface.action.GroupMarker;
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
  private IWorkbenchAction undo;
  private IWorkbenchAction redo;

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

    exit = ActionFactory.QUIT.create(window);
    register(exit);

    undo = ActionFactory.UNDO.create(window);
    register(undo);

    redo = ActionFactory.REDO.create(window);
    register(redo);
  }

  @Override
  protected void fillMenuBar(IMenuManager menuBar)
  {
    MenuManager file = new MenuManager("&File", "file");
    MenuManager subnew = new MenuManager("&New", "new");
    subnew.add(new Separator("after_resource"));
    file.add(subnew);
    MenuManager subopen = new MenuManager("&Open", "open");
    subopen.add(new GroupMarker("additions"));
    file.add(subopen);
    file.add(new Separator());
    file.add(exit);
    file.add(new Separator());
    file.add(save);

    menuBar.add(file);

    MenuManager edit = new MenuManager("&Edit", "edit");
    edit.add(undo);
    edit.add(redo);

    menuBar.add(edit);
  }
}