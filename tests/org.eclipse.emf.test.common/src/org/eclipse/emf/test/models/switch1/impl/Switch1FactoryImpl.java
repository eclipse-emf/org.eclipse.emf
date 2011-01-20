/**
 * <copyright>
 *
 * Copyright (c) 2009 TIBCO Software Inc. and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Adrian Price
 *
 * </copyright>
 *
 * $Id: Switch1FactoryImpl.java,v 1.1 2011/01/20 01:10:22 emerks Exp $
 */
package org.eclipse.emf.test.models.switch1.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.test.models.switch1.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Switch1FactoryImpl extends EFactoryImpl implements Switch1Factory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static Switch1Factory init()
  {
    try
    {
      Switch1Factory theSwitch1Factory = (Switch1Factory)EPackage.Registry.INSTANCE.getEFactory("http:///org.eclipse.emf.test.models/switch1"); 
      if (theSwitch1Factory != null)
      {
        return theSwitch1Factory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new Switch1FactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Switch1FactoryImpl()
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
      case Switch1Package.ECLASS0: return createEClass0();
      case Switch1Package.ECLASS1: return createEClass1();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass0 createEClass0()
  {
    EClass0Impl eClass0 = new EClass0Impl();
    return eClass0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass1 createEClass1()
  {
    EClass1Impl eClass1 = new EClass1Impl();
    return eClass1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Switch1Package getSwitch1Package()
  {
    return (Switch1Package)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static Switch1Package getPackage()
  {
    return Switch1Package.eINSTANCE;
  }

} //Switch1FactoryImpl
