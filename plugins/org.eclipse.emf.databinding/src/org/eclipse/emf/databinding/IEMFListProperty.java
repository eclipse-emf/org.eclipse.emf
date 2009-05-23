/**
 * <copyright> 
 *
 * Copyright (c) 2008 Matthew Hall and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Matthew Hall - initial API and implementation (bug 195222)
 *   Tom Schindl <tom.schindl@bestsolution.at> - port to EMF in 262160
 * </copyright>
 *
 * $Id: IEMFListProperty.java,v 1.1 2009/05/23 11:11:33 tschindl Exp $
 */
package org.eclipse.emf.databinding;

import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * An {@link IListProperty} extension interface with convenience methods for
 * creating nested {@link EStructuralFeature}s
 * 
 * @since 2.5
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface IEMFListProperty extends IEMFProperty, IListProperty
{
  /**
   * Returns a master-detail combination of this property and the specified
   * value nested feature.
   * 
   * @param featurePath
   *            the nested feature to observe
   * @return a nested combination of this property and the specified nested
   *         feature.
   * @see #values(IEMFValueProperty)
   */
  public IEMFListProperty values(FeaturePath featurePath);

  /**
   * Returns a master-detail combination of this property and the specified
   * value property.
   * 
   * @param feature
   *            the feature
   * @return a nested combination of this property and the specified nested
   *         feature.
   */
  public IEMFListProperty values(EStructuralFeature feature);

  /**
   * Returns a master-detail combination of this property and the specified
   * value property. The returned property will observe the specified value
   * property for all elements observed by this list property.
   * <p>
   * Example:
   * 
   * <pre>
   * // Observes the list-typed &quot;children&quot; property of a Person object,
   * // where the elements are Person objects
   * IEMFListProperty children = EMFProperties
   * 		.list(MyPackage.Literals.PERSON_CHILDREN);
   * // Observes the string-typed &quot;name&quot; property of a Person object
   * IEMFValueProperty name = EMFProperties.value(MyPackage.Literals.PERSON_NAME);
   * // Observes the names of children of a Person object.
   * IEMFListProperty childrenNames = children.values(name);
   * </pre>
   * 
   * @param property
   *            the detail property to observe
   * @return a master-detail combination of this property and the specified
   *         value property.
   */
  public IEMFListProperty values(IEMFValueProperty property);
}
