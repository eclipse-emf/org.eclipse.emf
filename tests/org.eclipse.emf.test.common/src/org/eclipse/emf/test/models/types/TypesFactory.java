/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: TypesFactory.java,v 1.2 2007/01/18 22:06:48 marcelop Exp $
 */
package org.eclipse.emf.test.models.types;


/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @generated
 */
public interface TypesFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  TypesFactory INSTANCE = org.eclipse.emf.test.models.types.impl.TypesFactoryImpl.eINSTANCE;

  /**
   * Returns a new object of class '<em>AThing</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>AThing</em>'.
   * @generated
   */
  AThing createAThing();

} //TypesFactory
