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
 * $Id: JPackageImpl.java,v 1.1 2004/04/13 02:50:33 marcelop Exp $
 */
package org.eclipse.emf.java.impl;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.java.JClass;
import org.eclipse.emf.java.JPackage;
import org.eclipse.emf.java.JavaPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>JPackage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.java.impl.JPackageImpl#getJavaPackage <em>Java Package</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JPackageImpl#getTypes <em>Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JPackageImpl extends JModelElementImpl implements JPackage
{
  /**
   * The default value of the '{@link #getJavaPackage() <em>Java Package</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJavaPackage()
   * @generated
   * @ordered
   */
  protected static final Package JAVA_PACKAGE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getJavaPackage() <em>Java Package</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJavaPackage()
   * @generated
   * @ordered
   */
  protected Package javaPackage = JAVA_PACKAGE_EDEFAULT;

  /**
   * The cached value of the '{@link #getTypes() <em>Types</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypes()
   * @generated
   * @ordered
   */
  protected EList types = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected JPackageImpl()
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
    return JavaPackage.eINSTANCE.getJPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Package getJavaPackage()
  {
    return javaPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setJavaPackage(Package newJavaPackage)
  {
    Package oldJavaPackage = javaPackage;
    javaPackage = newJavaPackage;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JPACKAGE__JAVA_PACKAGE, oldJavaPackage, javaPackage));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getTypes()
  {
    if (types == null)
    {
      types = new EObjectWithInverseResolvingEList(JClass.class, this, JavaPackage.JPACKAGE__TYPES, JavaPackage.JCLASS__PACKAGE);
    }
    return types;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case JavaPackage.JPACKAGE__TYPES:
          return ((InternalEList)getTypes()).basicAdd(otherEnd, msgs);
        default:
          return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
      }
    }
    if (eContainer != null)
      msgs = eBasicRemoveFromContainer(msgs);
    return eBasicSetContainer(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case JavaPackage.JPACKAGE__TYPES:
          return ((InternalEList)getTypes()).basicRemove(otherEnd, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
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
      case JavaPackage.JPACKAGE__NAME:
        return getName();
      case JavaPackage.JPACKAGE__JNODE:
        return getJNode();
      case JavaPackage.JPACKAGE__JAVA_PACKAGE:
        return getJavaPackage();
      case JavaPackage.JPACKAGE__TYPES:
        return getTypes();
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
      case JavaPackage.JPACKAGE__NAME:
        setName((String)newValue);
        return;
      case JavaPackage.JPACKAGE__JNODE:
        setJNode((Object)newValue);
        return;
      case JavaPackage.JPACKAGE__JAVA_PACKAGE:
        setJavaPackage((Package)newValue);
        return;
      case JavaPackage.JPACKAGE__TYPES:
        getTypes().clear();
        getTypes().addAll((Collection)newValue);
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
      case JavaPackage.JPACKAGE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case JavaPackage.JPACKAGE__JNODE:
        setJNode(JNODE_EDEFAULT);
        return;
      case JavaPackage.JPACKAGE__JAVA_PACKAGE:
        setJavaPackage(JAVA_PACKAGE_EDEFAULT);
        return;
      case JavaPackage.JPACKAGE__TYPES:
        getTypes().clear();
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
      case JavaPackage.JPACKAGE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case JavaPackage.JPACKAGE__JNODE:
        return JNODE_EDEFAULT == null ? jNode != null : !JNODE_EDEFAULT.equals(jNode);
      case JavaPackage.JPACKAGE__JAVA_PACKAGE:
        return JAVA_PACKAGE_EDEFAULT == null ? javaPackage != null : !JAVA_PACKAGE_EDEFAULT.equals(javaPackage);
      case JavaPackage.JPACKAGE__TYPES:
        return types != null && !types.isEmpty();
    }
    return eDynamicIsSet(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (javaPackage: ");
    result.append(javaPackage);
    result.append(')');
    return result.toString();
  }

  public EObject eObjectForURIFragmentSegment(String uriFragmentSegment)
  {
    if (uriFragmentSegment.startsWith("@"))
    {
      return super.eObjectForURIFragmentSegment(uriFragmentSegment);
    }
    else
    {
      JClass componentJClass = null;
      List theTypes = getTypes();
      int uriFragmentSegmentLength = uriFragmentSegment.length();
      // EATM resolve problems
      for (int i = 0, size = theTypes.size(); i < size; ++i)
      {
        JClass jClass = (JClass)theTypes.get(i);
        String name = jClass.getName();
        if (uriFragmentSegment.equals(name))
        {
          return jClass;
        }
      }

      return null;
    }
  }

} //JPackageImpl
