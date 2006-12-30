/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: TypesFactoryImpl.java,v 1.3 2006/12/30 03:44:07 marcelop Exp $
 */
package org.eclipse.emf.test.sdo.types.model.types.impl;

import java.util.Date;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.test.sdo.types.model.types.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TypesFactoryImpl extends EFactoryImpl implements TypesFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static TypesFactory init()
  {
    try
    {
      TypesFactory theTypesFactory = (TypesFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.example.com/types"); 
      if (theTypesFactory != null)
      {
        return theTypesFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new TypesFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypesFactoryImpl()
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
      case TypesPackage.ATHING: return (EObject)createAThing();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case TypesPackage.MY_BYTES:
        return createMyBytesFromString(eDataType, initialValue);
      case TypesPackage.MY_CHAR:
        return createMyCharFromString(eDataType, initialValue);
      case TypesPackage.MY_CHAR_OBJECT:
        return createMyCharObjectFromString(eDataType, initialValue);
      case TypesPackage.MY_DATE:
        return createMyDateFromString(eDataType, initialValue);
      case TypesPackage.MY_NUMBER:
        return createMyNumberFromString(eDataType, initialValue);
      case TypesPackage.MY_OBJECT:
        return createMyObjectFromString(eDataType, initialValue);
      case TypesPackage.MY_THREAD:
        return createMyThreadFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case TypesPackage.MY_BYTES:
        return convertMyBytesToString(eDataType, instanceValue);
      case TypesPackage.MY_CHAR:
        return convertMyCharToString(eDataType, instanceValue);
      case TypesPackage.MY_CHAR_OBJECT:
        return convertMyCharObjectToString(eDataType, instanceValue);
      case TypesPackage.MY_DATE:
        return convertMyDateToString(eDataType, instanceValue);
      case TypesPackage.MY_NUMBER:
        return convertMyNumberToString(eDataType, instanceValue);
      case TypesPackage.MY_OBJECT:
        return convertMyObjectToString(eDataType, instanceValue);
      case TypesPackage.MY_THREAD:
        return convertMyThreadToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AThing createAThing()
  {
    AThingImpl aThing = new AThingImpl();
    return aThing;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public byte[] createMyBytesFromString(EDataType eDataType, String initialValue)
  {
    return (byte[])super.createFromString(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertMyBytesToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Character createMyCharFromString(EDataType eDataType, String initialValue)
  {
    return (Character)super.createFromString(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertMyCharToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Character createMyCharObjectFromString(EDataType eDataType, String initialValue)
  {
    return createMyCharFromString(TypesPackage.Literals.MY_CHAR, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertMyCharObjectToString(EDataType eDataType, Object instanceValue)
  {
    return convertMyCharToString(TypesPackage.Literals.MY_CHAR, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Date createMyDateFromString(EDataType eDataType, String initialValue)
  {
    return (Date)super.createFromString(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertMyDateToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Number createMyNumberFromString(EDataType eDataType, String initialValue)
  {
    return (Number)super.createFromString(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertMyNumberToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object createMyObjectFromString(EDataType eDataType, String initialValue)
  {
    return super.createFromString(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertMyObjectToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Thread createMyThreadFromString(EDataType eDataType, String initialValue)
  {
    return (Thread)super.createFromString(initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertMyThreadToString(EDataType eDataType, Object instanceValue)
  {
    return super.convertToString(instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypesPackage getTypesPackage()
  {
    return (TypesPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static TypesPackage getPackage()
  {
    return TypesPackage.eINSTANCE;
  }

} //TypesFactoryImpl
