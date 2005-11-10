/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: EmployeeImpl.java,v 1.1 2005/11/10 18:55:52 marcelop Exp $
 */
package org.eclipse.emf.examples.extlibrary.impl;


import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.examples.extlibrary.EXTLibraryPackage;
import org.eclipse.emf.examples.extlibrary.Employee;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Employee</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.examples.extlibrary.impl.EmployeeImpl#getManager <em>Manager</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EmployeeImpl extends PersonImpl implements Employee
{
  /**
   * The cached value of the '{@link #getManager() <em>Manager</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getManager()
   * @generated
   * @ordered
   */
  protected Employee manager = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EmployeeImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return EXTLibraryPackage.eINSTANCE.getEmployee();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Employee getManager()
  {
    if (manager != null && manager.eIsProxy())
    {
      Employee oldManager = manager;
      manager = (Employee)eResolveProxy((InternalEObject)manager);
      if (manager != oldManager)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, EXTLibraryPackage.EMPLOYEE__MANAGER, oldManager, manager));
      }
    }
    return manager;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Employee basicGetManager()
  {
    return manager;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setManager(Employee newManager)
  {
    Employee oldManager = manager;
    manager = newManager;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EXTLibraryPackage.EMPLOYEE__MANAGER, oldManager, manager));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case EXTLibraryPackage.EMPLOYEE__ADDRESS:
        return getAddress();
      case EXTLibraryPackage.EMPLOYEE__FIRST_NAME:
        return getFirstName();
      case EXTLibraryPackage.EMPLOYEE__LAST_NAME:
        return getLastName();
      case EXTLibraryPackage.EMPLOYEE__MANAGER:
        if (resolve) return getManager();
        return basicGetManager();
    }
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(EStructuralFeature eFeature, Object newValue)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case EXTLibraryPackage.EMPLOYEE__ADDRESS:
        setAddress((String)newValue);
        return;
      case EXTLibraryPackage.EMPLOYEE__FIRST_NAME:
        setFirstName((String)newValue);
        return;
      case EXTLibraryPackage.EMPLOYEE__LAST_NAME:
        setLastName((String)newValue);
        return;
      case EXTLibraryPackage.EMPLOYEE__MANAGER:
        setManager((Employee)newValue);
        return;
    }
    eDynamicSet(eFeature, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case EXTLibraryPackage.EMPLOYEE__ADDRESS:
        setAddress(ADDRESS_EDEFAULT);
        return;
      case EXTLibraryPackage.EMPLOYEE__FIRST_NAME:
        setFirstName(FIRST_NAME_EDEFAULT);
        return;
      case EXTLibraryPackage.EMPLOYEE__LAST_NAME:
        setLastName(LAST_NAME_EDEFAULT);
        return;
      case EXTLibraryPackage.EMPLOYEE__MANAGER:
        setManager((Employee)null);
        return;
    }
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case EXTLibraryPackage.EMPLOYEE__ADDRESS:
        return ADDRESS_EDEFAULT == null ? address != null : !ADDRESS_EDEFAULT.equals(address);
      case EXTLibraryPackage.EMPLOYEE__FIRST_NAME:
        return FIRST_NAME_EDEFAULT == null ? firstName != null : !FIRST_NAME_EDEFAULT.equals(firstName);
      case EXTLibraryPackage.EMPLOYEE__LAST_NAME:
        return LAST_NAME_EDEFAULT == null ? lastName != null : !LAST_NAME_EDEFAULT.equals(lastName);
      case EXTLibraryPackage.EMPLOYEE__MANAGER:
        return manager != null;
    }
    return eDynamicIsSet(eFeature);
  }

} //EmployeeImpl
