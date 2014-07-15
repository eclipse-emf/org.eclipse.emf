/**
 * Copyright (c) 2014 CEA and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   CEA - Initial API and implementation
 *
 */
package org.eclipse.emf.test.core.xrefsmodel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.test.core.xrefsmodel.A;
import org.eclipse.emf.test.core.xrefsmodel.XRefsModelFactory;
import org.eclipse.emf.test.core.xrefsmodel.XRefsModelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XRefsModelFactoryImpl extends EFactoryImpl implements XRefsModelFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XRefsModelFactory init()
  {
    try
    {
      XRefsModelFactory theXRefsModelFactory = (XRefsModelFactory)EPackage.Registry.INSTANCE.getEFactory(XRefsModelPackage.eNS_URI);
      if (theXRefsModelFactory != null)
      {
        return theXRefsModelFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new XRefsModelFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XRefsModelFactoryImpl()
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
      case XRefsModelPackage.A: return createA();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public A createA()
  {
    AImpl a = new AImpl();
    return a;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XRefsModelPackage getXRefsModelPackage()
  {
    return (XRefsModelPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static XRefsModelPackage getPackage()
  {
    return XRefsModelPackage.eINSTANCE;
  }

} //XRefsModelFactoryImpl
