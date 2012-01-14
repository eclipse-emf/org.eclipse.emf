/**
 * Copyright (c) 2009 BestSolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl <tom.schindl@bestsolution.at> - Initial API and implementation (bug 262160)
 */
package org.eclipse.emf.databinding.edit.internal;

import java.util.Map;

import org.eclipse.core.databinding.observable.map.MapDiff;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.internal.EMFMapProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * @since 2.5
 */
public class EMFEditMapProperty extends EMFMapProperty
{
  private final EditingDomain editingDomain;

  /**
   * @param editingDomain
   * @param eStructuralFeature
   */
  public EMFEditMapProperty(EditingDomain editingDomain, EStructuralFeature eStructuralFeature)
  {
    super(eStructuralFeature);
    this.editingDomain = editingDomain;
  }

  @SuppressWarnings("rawtypes")
  @Override
  protected void doSetMap(Object source, Map map, MapDiff diff)
  {
    EObject eObject = (EObject)source;
    Command command = SetCommand.create(editingDomain, eObject, getFeature(), map);
    editingDomain.getCommandStack().execute(command);
  }

}
