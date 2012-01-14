/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.xsd2ecore.impl;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

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
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XSD2EcoreFactory init()
  {
    try
    {
      XSD2EcoreFactory theXSD2EcoreFactory = (XSD2EcoreFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/emf/2002/XSD2Ecore"); 
      if (theXSD2EcoreFactory != null)
      {
        return theXSD2EcoreFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new XSD2EcoreFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
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
  @Override
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
    XSD2EcoreMappingRootImpl xsd2EcoreMappingRoot = new XSD2EcoreMappingRootImpl();
    return xsd2EcoreMappingRoot;
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
  @Deprecated
  public static XSD2EcorePackage getPackage()
  {
    return XSD2EcorePackage.eINSTANCE;
  }

} //XSD2EcoreFactoryImpl
