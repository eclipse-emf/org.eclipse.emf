/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSD2EcoreFactoryImpl.java,v 1.2 2004/03/12 23:36:34 emerks Exp $
 */
package org.eclipse.emf.mapping.xsd2ecore.impl;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.mapping.xsd2ecore.*;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XSD2EcoreFactoryImpl extends EFactoryImpl implements XSD2EcoreFactory
{
  /**
   * Creates and instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSD2EcoreFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT: return createXSD2EcoreMappingRoot();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSD2EcoreMappingRoot createXSD2EcoreMappingRoot()
  {
    XSD2EcoreMappingRootImpl xsD2EcoreMappingRoot = new XSD2EcoreMappingRootImpl();
    return xsD2EcoreMappingRoot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSD2EcorePackage getXSD2EcorePackage()
  {
    return (XSD2EcorePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  public static XSD2EcorePackage getPackage()
  {
    return XSD2EcorePackage.eINSTANCE;
  }
} //XSD2EcoreFactoryImpl
