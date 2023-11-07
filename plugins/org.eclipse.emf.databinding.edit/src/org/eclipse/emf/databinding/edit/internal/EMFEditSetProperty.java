/**
 * Copyright (c) 2008 Tom Schindl and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Tom Schindl <tom.schindl@bestsolution.at> - port to EMF in 295683
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

  public EMFEditSetProperty(EditingDomain editingDomain, EStructuralFeature eStructuralFeature)
  {
    super(eStructuralFeature);
    this.editingDomain = editingDomain;
  }

  @Override
  protected void doSetSet(Object source, Set set, SetDiff diff)
  {
    if( diff.getAdditions() != null && ! diff.getAdditions().isEmpty() ) {
      execute(AddCommand.create(editingDomain, source, getFeature(), diff.getAdditions()));
    }

    if( diff.getRemovals() != null && ! diff.getRemovals().isEmpty() ) {
      execute(RemoveCommand.create(editingDomain, source, getFeature(), diff.getRemovals()));
    }
  }

  private void execute(Command command)
  {
    editingDomain.getCommandStack().execute(command);
  }
}
