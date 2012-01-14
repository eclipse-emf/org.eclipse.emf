/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.databinding.edit;

import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.EObjectObservableMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 */
public class EditingDomainEObjectObservableMap extends EObjectObservableMap
{
  /**
   * The editing domain
   */
  protected EditingDomain domain;

  /**
   * Create a new observable for the set of features
   * @param domain
   *            the editing domain
   * @param objects
   *            the objects to observe
   * @param eStructuralFeature
   *            the feature
   */
  public EditingDomainEObjectObservableMap(EditingDomain domain, IObservableSet objects, EStructuralFeature eStructuralFeature)
  {
    super(objects, eStructuralFeature);
    this.domain = domain;
  }

  @Override
  protected Object doPut(Object key, Object value)
  {
    EObject eObject = (EObject)key;
    Object result = eObject.eGet(eStructuralFeature);
    Command command = SetCommand.create(domain, eObject, eStructuralFeature, value);
    domain.getCommandStack().execute(command);

    eObject.eSet(eStructuralFeature, value);
    return result;
  }
}
