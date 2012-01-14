/**
 * Copyright (c) 2008 Matthew Hall and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *    Matthew Hall - initial API and implementation (bug 195222)
 *   Tom Schindl <tom.schindl@bestsolution.at> - port to EMF in 262160
 */
package org.eclipse.emf.databinding;

import java.util.Map;

import org.eclipse.core.databinding.property.map.IMapProperty;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * An {@link IMapProperty} extension interface with convenience methods for
 * creating nested {@link EStructuralFeature}s
 * 
 * @since 2.5
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface IEMFMapProperty extends IEMFProperty, IMapProperty
{

  /**
   * Returns a master-detail combination of this property and the specified
   * nested value feature.
   * 
   * @param featurePath
   *            the nested value feature to observe
   * @return a master-detail combination of this property and the specified
   *         nested value feature.
   * @see #values(IEMFValueProperty)
   */
  public IEMFMapProperty values(FeaturePath featurePath);

  /**
   * Returns a master-detail combination of this property and the specified
   * value feature.
   * 
   * @param feature
   *            the value feature to observe
   * @return a master-detail combination of this property and the specified
   *         nested value feature.
   * @see #values(IEMFValueProperty)
   */
  public IEMFMapProperty values(EStructuralFeature feature);

  /**
   * Returns a master-detail combination of this property and the specified
   * value property. The returned property will observe the specified value
   * property for all {@link Map#values() values} observed by this map
   * property, mapping from this map property's {@link Map#keySet() key set}
   * to the specified value property's value for each element in the master
   * property's {@link Map#values() values} collection.
   * 
   * @param property
   *            the detail property to observe
   * @return a master-detail combination of this property and the specified
   *         value property.
   */
  public IEMFMapProperty values(IEMFValueProperty property);
}
