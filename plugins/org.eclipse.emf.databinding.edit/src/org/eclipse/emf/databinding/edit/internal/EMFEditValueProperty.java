/**
 * <copyright> 
 *
 * Copyright (c) 2009 BestSolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl <tom.schindl@bestsolution.at> - Initial API and implementation (bug 262160)
 * </copyright>
 *
 * $Id: EMFEditValueProperty.java,v 1.1 2009/05/23 11:11:30 tschindl Exp $
 */
package org.eclipse.emf.databinding.edit.internal;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.internal.EMFValueProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * @since 2.5
 */
public class EMFEditValueProperty extends EMFValueProperty
{
  private final EditingDomain editingDomain;

  /**
   * @param editingDomain
   * @param eStructuralFeature
   */
  public EMFEditValueProperty(EditingDomain editingDomain, EStructuralFeature eStructuralFeature)
  {
    super(eStructuralFeature);
    this.editingDomain = editingDomain;
  }

  @Override
  protected void doSetValue(Object source, Object value)
  {
    EObject eObject = (EObject)source;
    Command command = SetCommand.create(editingDomain, eObject, getFeature(), value);
    editingDomain.getCommandStack().execute(command);
  }
}