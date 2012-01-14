/**
 * Copyright (c) 2008, 2009 Matthew Hall and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 195222)
 *     Tom Schindl - port to EMF in bug 295683
 */

package org.eclipse.emf.databinding.edit;

import org.eclipse.core.databinding.property.set.ISetProperty;

import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.IEMFSetProperty;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * An {@link ISetProperty} extension interface with convenience methods for
 * creating nested {@link EStructuralFeature} properties.
 *
 * @since 2.6
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface IEMFEditSetProperty extends IEMFEditProperty, IEMFSetProperty {
	/**
	 * Returns a master-detail combination of this property and the specified
	 * value property.
	 *
	 * @param feature
	 *            the value property to observe.
	 * @return a master-detail combination of this property and the specified
	 *         value property.
	 * @see #values(IEMFEditValueProperty)
	 */
	public IEMFEditMapProperty values(EStructuralFeature feature);

	/**
     * Returns a master-detail combination of this property and the specified
     * value property.
     *
     * @param featurePath
     *            the value property to observe. May be nested e.g.
     *            "parent.name"
     * @return a master-detail combination of this property and the specified
     *         value property.
     * @see #values(IEMFEditValueProperty)
     */
	public IEMFEditMapProperty values(FeaturePath featurePath);

	/**
	 * Returns a master-detail combination of this property and the specified
	 * value property. The returned property will observe the specified value
	 * property for all elements observed by this set property, mapping from
	 * this set property's elements (keys) to the specified value property's
	 * value for each element (values).
	 * <p>
	 * Example:
	 *
	 * <pre>
	 * // Observes the set-typed &quot;children&quot; property of a Person object,
	 * // where the elements are Person objects
	 * IEMFEditSetProperty children = EMFEditProperties.set(domain, MyPackage.Literals.PERSON_CHILDREN);
	 * // Observes the string-typed &quot;name&quot; property of a Person object
	 * IEMFEditValueProperty name = EMFEditProperties.value(domain, MyPackage.Literals.PERSON_NAME);
	 * // Observes a map of children objects to their respective names.
	 * IEMFEditMapProperty childrenNames = children.values(name);
	 * </pre>
	 *
	 * @param property
	 *            the detail property to observe
	 * @return a master-detail combination of this property and the specified
	 *         value property.
	 */
	public IEMFEditMapProperty values(IEMFEditValueProperty property);
}
