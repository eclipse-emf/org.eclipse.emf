/**
 * <copyright>
 *
 * Copyright (c) 2002-2010 IBM Corporation and others.
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
 * $Id: EDataType.java,v 1.2 2010/04/28 20:39:41 khussey Exp $
 */
package org.eclipse.emf.ecore;



/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EData Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.EDataType#isSerializable <em>Serializable</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.EcorePackage#getEDataType()
 * @model
 * @generated
 */
public interface EDataType extends EClassifier
{
  /**
   * Returns the value of the '<em><b>Serializable</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * It represents whether values of this type will be {@link org.eclipse.emf.ecore.resource.Resource#save(java.util.Map) serialized}.
   * For a serializable data type,
   * there will be factory methods of the form:
   *<pre>
   *  String convertXyzToString(EDataType metaObject, Object instanceValue)
   *  Object createXyzFromString(EDataType metaObject, String initialValue)
   *</pre>
   * in the generated factory implementation.
   * Clients will typically need to hand write the bodies of these generated methods.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Serializable</em>' attribute.
   * @see #setSerializable(boolean)
   * @see org.eclipse.emf.ecore.EcorePackage#getEDataType_Serializable()
   * @model default="true"
   * @generated
   */
  boolean isSerializable();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.EDataType#isSerializable <em>Serializable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Serializable</em>' attribute.
   * @see #isSerializable()
   * @generated
   */
  void setSerializable(boolean value);

} //EDataType
