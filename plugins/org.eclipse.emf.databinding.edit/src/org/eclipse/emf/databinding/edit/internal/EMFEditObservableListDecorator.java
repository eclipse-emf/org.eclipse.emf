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
 * $Id: EMFEditObservableListDecorator.java,v 1.1 2009/05/23 11:11:30 tschindl Exp $
 */
package org.eclipse.emf.databinding.edit.internal;

import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.emf.databinding.edit.IEMFEditObservable;
import org.eclipse.emf.databinding.internal.EMFObservableListDecorator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * {@link IEMFEditObservable} decorator for an {@link IObservableList}.
 * 
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * @since 2.5
 */
public class EMFEditObservableListDecorator extends EMFObservableListDecorator implements IEMFEditObservable
{

  private final EditingDomain editingDomain;

  /**
   * @param editingDomain
   * @param decorated
   * @param eStructuralFeature
   */
  public EMFEditObservableListDecorator(EditingDomain editingDomain, IObservableList decorated, EStructuralFeature eStructuralFeature)
  {
    super(decorated, eStructuralFeature);
    this.editingDomain = editingDomain;
  }

  public EditingDomain getEditingDomain()
  {
    return editingDomain;
  }

}
