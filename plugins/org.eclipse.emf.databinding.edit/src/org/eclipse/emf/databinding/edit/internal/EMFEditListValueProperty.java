/**
 * <copyright> 
 *
 * Copyright (c) 2011 BestSolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - initial API and implementation
 * </copyright>
 *
 * $Id: EMFEditListValueProperty.java,v 1.1 2011/04/22 07:22:41 tschindl Exp $
 */
package org.eclipse.emf.databinding.edit.internal;

import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.IEMFListProperty.ListElementAccess;
import org.eclipse.emf.databinding.IEMFListProperty.ListElementAccess.WriteData;
import org.eclipse.emf.databinding.internal.EMFListValueProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


public class EMFEditListValueProperty extends EMFListValueProperty
{
  private EditingDomain editingDomain;

  public EMFEditListValueProperty(EditingDomain editingDomain, EStructuralFeature eStructuralFeature, ListElementAccess elementAccess)
  {
    super(eStructuralFeature, elementAccess);
    this.editingDomain = editingDomain;
  }

  @Override
  protected void doSetListValue(EObject source, List<Object> targetList, WriteData data, Object value)
  {
    final Command cmd;
    if (data.insert)
    {
      if (data.index == WriteData.NO_INDEX)
      {
        cmd = AddCommand.create(editingDomain, source, getValueType(), value);
      }
      else
      {
        cmd = AddCommand.create(editingDomain, source, getValueType(), data.index);
      }
    }
    else
    {
      cmd = SetCommand.create(editingDomain, source, getValueType(), value, data.index);
    }

    editingDomain.getCommandStack().execute(cmd);
  }
}