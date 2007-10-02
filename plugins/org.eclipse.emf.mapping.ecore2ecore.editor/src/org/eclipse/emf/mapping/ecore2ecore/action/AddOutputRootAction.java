/**
 * <copyright>
 *
 * Copyright (c) 2004-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: AddOutputRootAction.java,v 1.8 2007/10/02 17:55:39 emerks Exp $
 */
package org.eclipse.emf.mapping.ecore2ecore.action;


import java.util.Collection;

import org.eclipse.emf.mapping.action.AddRootBottomAction;


/**
 *  
 */
public class AddOutputRootAction extends AddRootBottomAction
{
  /**
   *  
   */
  public AddOutputRootAction()
  {
    super();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.mapping.action.AddRootBottomAction#getBottomsToAdd()
   */
  @Override
  protected Collection<?> getBottomsToAdd()
  {
    return AddInputRootAction.getRootsToAdd(workbenchPart, editingDomain);
  }
}