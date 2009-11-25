/**
 * <copyright>
 *
 * Copyright (c) 2008 Tom Schindl and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Tom Schindl <tom.schindl@bestsolution.at> - port to EMF in 295683
 * </copyright>
 *
 * $Id: EMFEditSetProperty.java,v 1.1 2009/11/25 09:15:02 tschindl Exp $
 */
package org.eclipse.emf.databinding.edit.internal;

import java.util.Set;

import org.eclipse.core.databinding.observable.set.SetDiff;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.internal.EMFSetProperty;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 *
 * @since 2.6
 */
public class EMFEditSetProperty extends EMFSetProperty
{
  private final EditingDomain editingDomain;

  /**
   * @param editingDomain
   * @param eStructuralFeature
   */
  public EMFEditSetProperty(EditingDomain editingDomain, EStructuralFeature eStructuralFeature)
  {
    super(eStructuralFeature);
    this.editingDomain = editingDomain;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected void doSetSet(Object source, Set set, SetDiff diff)
  {
    if( diff.getAdditions() != null && ! diff.getAdditions().isEmpty() ) {
      execute(AddCommand.create(editingDomain, source, getFeature(), diff.getAdditions()));
    }

    if( diff.getRemovals() != null && ! diff.getRemovals().isEmpty() ) {
      System.err.println(diff.getRemovals());
      execute(RemoveCommand.create(editingDomain, source, getFeature(), diff.getRemovals()));
    }
  }

  private boolean execute(Command command)
  {
    if (command.canExecute())
    {
      editingDomain.getCommandStack().execute(command);
      return true;
    }
    else
    {
      return false;
    }
  }
}