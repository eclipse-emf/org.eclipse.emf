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
 * $Id: IEMFEditValueProperty.java,v 1.2 2009/11/25 09:15:02 tschindl Exp $
 */
package org.eclipse.emf.databinding.edit;

import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.IEMFListProperty;
import org.eclipse.emf.databinding.IEMFMapProperty;
import org.eclipse.emf.databinding.IEMFSetProperty;
import org.eclipse.emf.databinding.IEMFValueProperty;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 *
 * An {@link IValueProperty} extension interface with convenience methods for
 * creating nested {@link EStructuralFeature} properties and does changes through the {@link EditingDomain}
 *
 * @since 2.5
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface IEMFEditValueProperty extends IEMFEditProperty, IEMFValueProperty
{
  /**
   * Returns a master-detail combination of this property and the specified
   * nested value feature.
   *
   * @param featurePath
   *            the nested value property to observe.
   * @return a master-detail combination of this property and the specified
   *         nested value feature.
   * @see #value(IEMFEditValueProperty)
   */
  public IEMFEditValueProperty value(FeaturePath featurePath);

  /**
   * Returns a master-detail combination of this property and the specified
   * value feature.
   *
   * @param feature
   *            the nested value property to observe.
   * @return a master-detail combination of this property and the specified
   *         value feature.
   * @see #value(IEMFEditValueProperty)
   */
  public IEMFEditValueProperty value(EStructuralFeature feature);

  /**
   * Returns a master-detail combination of this property and the specified
   * value property. The returned property will observe the specified detail
   * value property for the value of the master value property.
   * <p>
   * Example:
   *
   * <pre>
   * // Observes the Node-typed &quot;parent&quot; property of a Node object
   * IEMFValueProperty parent = EMFProperties.value(MyPackage.Literals.NODE_PARENT);
   * // Observes the string-typed &quot;name&quot; property of a Node object
   * IEMFValueProperty name = EMFProperties.value(MyPackage.Literals.NODE_NAME);
   * // Observes the name of the parent of a Node object.
   * IEMFValueProperty parentName = parent.value(name);
   * </pre>
   *
   * @param property
   *            the detail property to observe
   * @return a master-detail combination of this property and the specified
   *         value property.
   */
  public IEMFEditValueProperty value(IEMFEditValueProperty property);

  /**
   * Returns a master-detail combination of this property and the specified
   * list feature.
   *
   * @param feature
   *            the list feature to observe
   * @return a master-detail combination of this property and the specified
   *         list feature.
   * @see #list(IEMFEditListProperty)
   */
  public IEMFEditListProperty list(EStructuralFeature feature);

  /**
   * Returns a master-detail combination of this property and the specified
   * list property. The returned property will observe the specified list
   * property for the value of the master property.
   * <p>
   * Example:
   *
   * <pre>
   * // Observes the Node-typed &quot;parent&quot; property of a Node object.
   * IEMFValueProperty parent = EMFProperties.value(MyPackage.Literals.NODE_PARENT);
   * // Observes the List-typed &quot;children&quot; property of a Node object
   * // where the elements are Node objects
   * IEMFListProperty children = EMFProperties.list(MyPackage.Literals.NODE_CHILDREN);
   * // Observes the children of the parent (siblings) of a Node object.
   * IEMFListProperty siblings = parent.list(children);
   * </pre>
   *
   * @param property
   *            the detail property to observe
   * @return a master-detail combination of this property and the specified
   *         list property.
   */
  public IEMFEditListProperty list(IEMFEditListProperty property);

  /**
   * Returns a master-detail combination of this property and the specified
   * map feature.
   *
   * @param feature
   *            the map property to observe
   * @return a master-detail combination of this property and the specified
   *         map feature.
   * @see #map(IEMFEditMapProperty)
   */
  public IEMFEditMapProperty map(EStructuralFeature feature);

  /**
   * Returns a master-detail combination of this property and the specified
   * map property. The returned property will observe the specified map
   * property for the value of the master property.
   * <p>
   * Example:
   *
   * <pre>
   * // Observes the Contact-typed &quot;supervisor&quot; property of a
   * // Contact class
   * IEMFValueProperty supervisor = EMFProperties.value(MyPackage.Literals.CONTACT_SUPERVISOR);
   * // Observes the property &quot;phoneNumbers&quot; of a Contact object--a property mapping
   * // from PhoneNumberType to PhoneNumber &quot;set-typed &quot;children&quot;,
   * IEMFMapProperty phoneNumbers = EMFProperties.map(MyPackage.Literals.CONTACT_PHONENUMBERS);
   * // Observes the phone numbers of a contact's supervisor:
   * IEMFMapProperty supervisorPhoneNumbers = supervisor.map(phoneNumbers);
   * </pre>
   *
   * @param property
   *            the detail property to observe
   * @return a master-detail combination of this property and the specified
   *         map property.
   */
  public IEMFEditMapProperty map(IEMFEditMapProperty property);

  public IEMFEditListProperty list(IEMFListProperty property);

  public IEMFEditMapProperty map(IEMFMapProperty property);

  public IEMFEditValueProperty value(IEMFValueProperty property);

  /**
   * Returns a master-detail combination of this property and the specified
   * set property.
   *
   * @param feature
   *            the set feature to observe
   * @return a master-detail combination of this property and the specified
   *         set property.
   * @see #set(IEMFSetProperty)
   */
  public IEMFEditSetProperty set(EStructuralFeature feature);

  /**
   * Returns a master-detail combination of this property and the specified
   * set property. The returned property will observe the specified set
   * property for the value of the master property.
   * <p>
   * Example:
   *
   * <pre>
   * // Observes the Node-typed &quot;parent&quot; property of a Node object.
   * IEMFValueProperty parent = EMFProperties.value(MyPackage.Literals.NODE__PARENT);
   * // Observes the Set-typed &quot;children&quot; property of a Node object
   * // where the elements are Node objects
   * IEMFSetProperty children = EMFProperties.set(MyPackage.Literals.NODE__CHILDREN);
   * // Observes the children of the parent (siblings) of a Node object.
   * IEMFSetProperty siblings = parent.set(children);
   * </pre>
   *
   * @param property
   *            the detail property to observe
   * @return a master-detail combination of this property and the specified
   *         set property.
   */
  public IEMFEditSetProperty set(IEMFEditSetProperty property);
}
