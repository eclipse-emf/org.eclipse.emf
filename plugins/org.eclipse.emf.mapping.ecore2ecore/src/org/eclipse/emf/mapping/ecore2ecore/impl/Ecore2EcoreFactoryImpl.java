/**
 * Copyright (c) 2004-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.ecore2ecore.impl;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.mapping.ecore2ecore.*;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ecore2EcoreFactoryImpl extends EFactoryImpl implements Ecore2EcoreFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static Ecore2EcoreFactory init()
  {
    try
    {
      Ecore2EcoreFactory theEcore2EcoreFactory = (Ecore2EcoreFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/emf/2004/Ecore2Ecore"); 
      if (theEcore2EcoreFactory != null)
      {
        return theEcore2EcoreFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new Ecore2EcoreFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Ecore2EcoreFactoryImpl()
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
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT: return createEcore2EcoreMappingRoot();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Ecore2EcoreMappingRoot createEcore2EcoreMappingRoot()
  {
    Ecore2EcoreMappingRootImpl ecore2EcoreMappingRoot = new Ecore2EcoreMappingRootImpl();
    return ecore2EcoreMappingRoot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Ecore2EcorePackage getEcore2EcorePackage()
  {
    return (Ecore2EcorePackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static Ecore2EcorePackage getPackage()
  {
    return Ecore2EcorePackage.eINSTANCE;
  }

} //Ecore2EcoreFactoryImpl
