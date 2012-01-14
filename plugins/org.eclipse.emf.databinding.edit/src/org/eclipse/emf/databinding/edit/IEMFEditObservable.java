/**
 * Copyright (c) 2007 Brad Reynolds and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Brad Reynolds - initial API and implementation
 *   Brad Reynolds - bug 147515
 *   Tom Schindl <tom.schindl@bestsolution.at> - port to EMF in 262160
 */
package org.eclipse.emf.databinding.edit;


import org.eclipse.emf.databinding.IEMFObservable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * Provides access to details of {@link EObject} observables.
 * <p>
 * This interface is not meant to be implemented by clients.
 * </p>
 * 
 * @since 2.5
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface IEMFEditObservable extends IEMFObservable
{
  /**
   * @return the editing domain to execute commands
   */
  public EditingDomain getEditingDomain();
}
