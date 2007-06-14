/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 */
package org.eclipse.emf.edit.domain;



/**
 * This is the interface specified by an object that is able to yield its {@link EditingDomain}.
 * See {@link AdapterFactoryEditingDomain#getEditingDomainFor(Object) AdapterFactoryEditingDomain.getEditingDomainFor} 
 * for one use of this.
 */
public interface IEditingDomainProvider
{
  /**
   * This returns the editing domain.
   * A typical usage is 
   * <pre>
   *   ((IEditingDomainProvider)((EObject)object).eResource().getResourceSet()).getEditingDomain();
   * </pre>
   */
  public EditingDomain getEditingDomain();
}
