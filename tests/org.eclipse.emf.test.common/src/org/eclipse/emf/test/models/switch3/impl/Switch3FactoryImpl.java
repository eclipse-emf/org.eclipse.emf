/**
 * Copyright (c) 2009 TIBCO Software Inc. and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Adrian Price
 */
package org.eclipse.emf.test.models.switch3.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.test.models.switch3.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Switch3FactoryImpl extends EFactoryImpl implements Switch3Factory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static Switch3Factory init()
  {
    try
    {
      Switch3Factory theSwitch3Factory = (Switch3Factory)EPackage.Registry.INSTANCE.getEFactory("http:///org.eclipse.emf.test.models/switch3"); 
      if (theSwitch3Factory != null)
      {
        return theSwitch3Factory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new Switch3FactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Switch3FactoryImpl()
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
      case Switch3Package.ECLASS4: return createEClass4();
      case Switch3Package.ECLASS5: return createEClass5();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass4 createEClass4()
  {
    EClass4Impl eClass4 = new EClass4Impl();
    return eClass4;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass5 createEClass5()
  {
    EClass5Impl eClass5 = new EClass5Impl();
    return eClass5;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Switch3Package getSwitch3Package()
  {
    return (Switch3Package)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static Switch3Package getPackage()
  {
    return Switch3Package.eINSTANCE;
  }

} //Switch3FactoryImpl
