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
 * $Id: ApplicationActionBarAdvisor.java,v 1.1 2009/05/29 15:06:30 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;


/**
 * Advisor creating the tool and menu bar
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor
{

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
  }

  @Override
  protected void fillMenuBar(IMenuManager menuBar)
  {
  }

}
