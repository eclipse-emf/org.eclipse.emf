/**
 * <copyright>
 *
 * Copyright (c) 2004-2005 IBM Corporation and others.
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
 * $Id: Ecore2EcoreActionBarContributor.java,v 1.5 2005/05/09 14:45:45 khussey Exp $
 */
package org.eclipse.emf.mapping.ecore2ecore.presentation;

import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.emf.edit.ui.action.ValidateAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.Separator;

/**
 * This is the action bar contributor for the Ecore2Ecore model editor.
 * @generated NOT
 */
public class Ecore2EcoreActionBarContributor extends EditingDomainActionBarContributor
{
  /**
   * This creates an instance of the contributor.
   */
  public Ecore2EcoreActionBarContributor()
  {
    loadResourceAction = new LoadResourceAction();
    validateAction = new ValidateAction();
  }

  /**
   * This adds Separators for editor additions to the tool bar.
   */
  public void contributeToToolBar(IToolBarManager toolBarManager)
  {
    toolBarManager.add(new Separator("ecore2ecore-settings"));
    toolBarManager.add(new Separator("ecore2ecore-additions"));
  }

}