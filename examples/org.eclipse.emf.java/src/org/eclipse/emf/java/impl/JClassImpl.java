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
 * $Id: JClassImpl.java,v 1.4 2004/12/16 21:54:04 emerks Exp $
 */
package org.eclipse.emf.java.impl;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.jdom.IDOMNode;
import org.eclipse.jdt.core.jdom.IDOMType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.java.JClass;
import org.eclipse.emf.java.JCompilationUnit;
import org.eclipse.emf.java.JField;
import org.eclipse.emf.java.JInitializer;
import org.eclipse.emf.java.JMember;
import org.eclipse.emf.java.JMethod;
import org.eclipse.emf.java.JPackage;
import org.eclipse.emf.java.JVisibility;
import org.eclipse.emf.java.JavaFactory;
import org.eclipse.emf.java.JavaPackage;
import org.eclipse.emf.java.util.JavaUtil;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>JClass</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.java.impl.JClassImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JClassImpl#isFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JClassImpl#isInterface <em>Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JClassImpl#isThrowable <em>Throwable</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JClassImpl#getJavaClass <em>Java Class</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JClassImpl#getFields <em>Fields</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JClassImpl#getMethods <em>Methods</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JClassImpl#getSuperTypes <em>Super Types</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JClassImpl#getAllSuperTypes <em>All Super Types</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JClassImpl#getMembers <em>Members</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JClassImpl#getComponentType <em>Component Type</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JClassImpl#getArrayType <em>Array Type</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JClassImpl#getUnit <em>Unit</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JClassImpl#getAllMethods <em>All Methods</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JClassImpl#getAllFields <em>All Fields</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JClassImpl#getPackage <em>Package</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JClassImpl#getTypes <em>Types</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JClassImpl#getAllTypes <em>All Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JClassImpl extends JMemberImpl implements JClass
{
  /**
   * The default value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAbstract()
   * @generated
   * @ordered
   */
  protected static final boolean ABSTRACT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isAbstract() <em>Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isAbstract()
   * @generated
   * @ordered
   */
  protected boolean abstract_ = ABSTRACT_EDEFAULT;

  /**
   * The default value of the '{@link #isFinal() <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isFinal()
   * @generated
   * @ordered
   */
  protected static final boolean FINAL_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isFinal() <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isFinal()
   * @generated
   * @ordered
   */
  protected boolean final_ = FINAL_EDEFAULT;

  /**
   * The default value of the '{@link #isInterface() <em>Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isInterface()
   * @generated
   * @ordered
   */
  protected static final boolean INTERFACE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isInterface() <em>Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isInterface()
   * @generated
   * @ordered
   */
  protected boolean interface_ = INTERFACE_EDEFAULT;

  /**
   * The default value of the '{@link #isThrowable() <em>Throwable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isThrowable()
   * @generated
   * @ordered
   */
  protected static final boolean THROWABLE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isThrowable() <em>Throwable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isThrowable()
   * @generated
   * @ordered
   */
  protected boolean throwable = THROWABLE_EDEFAULT;

  /**
   * The default value of the '{@link #getJavaClass() <em>Java Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJavaClass()
   * @generated
   * @ordered
   */
  protected static final Class JAVA_CLASS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getJavaClass() <em>Java Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJavaClass()
   * @generated
   * @ordered
   */
  protected Class javaClass = JAVA_CLASS_EDEFAULT;

  /**
   * The cached value of the '{@link #getSuperTypes() <em>Super Types</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSuperTypes()
   * @generated
   * @ordered
   */
  protected EList superTypes = null;

  /**
   * The cached value of the '{@link #getMembers() <em>Members</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMembers()
   * @generated
   * @ordered
   */
  protected EList members = null;

  /**
   * The cached value of the '{@link #getArrayType() <em>Array Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArrayType()
   * @generated
   * @ordered
   */
  protected JClass arrayType = null;

  /**
   * The cached value of the '{@link #getPackage() <em>Package</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPackage()
   * @generated
   * @ordered
   */
  protected JPackage package_ = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected JClassImpl()
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
    return JavaPackage.eINSTANCE.getJClass();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isAbstract()
  {
    return abstract_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAbstract(boolean newAbstract)
  {
    boolean oldAbstract = abstract_;
    abstract_ = newAbstract;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JCLASS__ABSTRACT, oldAbstract, abstract_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isFinal()
  {
    return final_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFinal(boolean newFinal)
  {
    boolean oldFinal = final_;
    final_ = newFinal;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JCLASS__FINAL, oldFinal, final_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isInterface()
  {
    return interface_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInterface(boolean newInterface)
  {
    boolean oldInterface = interface_;
    interface_ = newInterface;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JCLASS__INTERFACE, oldInterface, interface_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isThrowable()
  {
    return throwable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setThrowable(boolean newThrowable)
  {
    boolean oldThrowable = throwable;
    throwable = newThrowable;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JCLASS__THROWABLE, oldThrowable, throwable));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Class getJavaClass()
  {
    return javaClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setJavaClass(Class newJavaClass)
  {
    Class oldJavaClass = javaClass;
    javaClass = newJavaClass;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JCLASS__JAVA_CLASS, oldJavaClass, javaClass));
  }

  EList fields;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated EATM
   */
  public EList getFields()
  {
    if (fields == null)
    {
      fields = new EObjectResolvingEList(JField.class, this, JavaPackage.JCLASS__FIELDS);
      fields.addAll(EcoreUtil.getObjectsByType(getMembers(), JavaPackage.eINSTANCE.getJField()));
    }
    return fields;
  }

  EList methods;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated EATM
   */
  public EList getMethods()
  {
    if (methods == null)
    {
      methods = new EObjectResolvingEList(JMethod.class, this, JavaPackage.JCLASS__METHODS);
      methods.addAll(EcoreUtil.getObjectsByType(getMembers(), JavaPackage.eINSTANCE.getJMethod()));
    }
    return methods;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getSuperTypes()
  {
    if (superTypes == null)
    {
      superTypes = new EObjectResolvingEList(JClass.class, this, JavaPackage.JCLASS__SUPER_TYPES);
    }
    return superTypes;
  }

  EList allSuperTypes;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated EATM
   */
  public EList getAllSuperTypes()
  {
    if (allSuperTypes == null)
    {
      allSuperTypes = new EObjectResolvingEList(JClass.class, this, JavaPackage.JCLASS__ALL_SUPER_TYPES);
      collectAll(JavaPackage.eINSTANCE.getJClass_AllSuperTypes(), allSuperTypes);
      allSuperTypes.addAll(getSuperTypes());
    }

    return allSuperTypes;
  }

  protected void collectAll(EStructuralFeature eStructuralFeature, EList target)
  {
    for (Iterator i = getSuperTypes().iterator(); i.hasNext(); )
    {
      JClass jClass = (JClass)i.next();
      target.addAll((EList)jClass.eGet(eStructuralFeature));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getMembers()
  {
    if (members == null)
    {
      members = new EObjectContainmentWithInverseEList(JMember.class, this, JavaPackage.JCLASS__MEMBERS, JavaPackage.JMEMBER__CONTAINING_TYPE);
    }
    return members;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JClass getComponentType()
  {
    if (eContainerFeatureID != JavaPackage.JCLASS__COMPONENT_TYPE) return null;
    return (JClass)eContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComponentType(JClass newComponentType)
  {
    if (newComponentType != eContainer || (eContainerFeatureID != JavaPackage.JCLASS__COMPONENT_TYPE && newComponentType != null))
    {
      if (EcoreUtil.isAncestor(this, newComponentType))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eContainer != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newComponentType != null)
        msgs = ((InternalEObject)newComponentType).eInverseAdd(this, JavaPackage.JCLASS__ARRAY_TYPE, JClass.class, msgs);
      msgs = eBasicSetContainer((InternalEObject)newComponentType, JavaPackage.JCLASS__COMPONENT_TYPE, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JCLASS__COMPONENT_TYPE, newComponentType, newComponentType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JClass getArrayType()
  {
    return arrayType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetArrayType(JClass newArrayType, NotificationChain msgs)
  {
    JClass oldArrayType = arrayType;
    arrayType = newArrayType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.JCLASS__ARRAY_TYPE, oldArrayType, newArrayType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setArrayType(JClass newArrayType)
  {
    if (newArrayType != arrayType)
    {
      NotificationChain msgs = null;
      if (arrayType != null)
        msgs = ((InternalEObject)arrayType).eInverseRemove(this, JavaPackage.JCLASS__COMPONENT_TYPE, JClass.class, msgs);
      if (newArrayType != null)
        msgs = ((InternalEObject)newArrayType).eInverseAdd(this, JavaPackage.JCLASS__COMPONENT_TYPE, JClass.class, msgs);
      msgs = basicSetArrayType(newArrayType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JCLASS__ARRAY_TYPE, newArrayType, newArrayType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JCompilationUnit getUnit()
  {
    if (eContainerFeatureID != JavaPackage.JCLASS__UNIT) return null;
    return (JCompilationUnit)eContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUnit(JCompilationUnit newUnit)
  {
    if (newUnit != eContainer || (eContainerFeatureID != JavaPackage.JCLASS__UNIT && newUnit != null))
    {
      if (EcoreUtil.isAncestor(this, newUnit))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eContainer != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newUnit != null)
        msgs = ((InternalEObject)newUnit).eInverseAdd(this, JavaPackage.JCOMPILATION_UNIT__TYPES, JCompilationUnit.class, msgs);
      msgs = eBasicSetContainer((InternalEObject)newUnit, JavaPackage.JCLASS__UNIT, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JCLASS__UNIT, newUnit, newUnit));
  }

  EList allMethods;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated EATM
   */
  public EList getAllMethods()
  {
    if (allMethods == null)
    {
      allMethods = new EObjectResolvingEList(JMethod.class, this, JavaPackage.JCLASS__ALL_METHODS);
      collectAll(JavaPackage.eINSTANCE.getJClass_AllMethods(), allMethods);
      allMethods.addAll(getMethods());
    }

    return allMethods;
  }

  EList allFields;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated EATM
   */
  public EList getAllFields()
  {
    if (allFields == null)
    {
      allFields = new EObjectResolvingEList(JField.class, this, JavaPackage.JCLASS__ALL_FIELDS);
      collectAll(JavaPackage.eINSTANCE.getJClass_AllFields(), allFields);
      allFields.addAll(getFields());
    }
    return allFields;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JPackage getPackage()
  {
    if (package_ != null && package_.eIsProxy())
    {
      JPackage oldPackage = package_;
      package_ = (JPackage)eResolveProxy((InternalEObject)package_);
      if (package_ != oldPackage)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, JavaPackage.JCLASS__PACKAGE, oldPackage, package_));
      }
    }
    return package_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JPackage basicGetPackage()
  {
    return package_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPackage(JPackage newPackage, NotificationChain msgs)
  {
    JPackage oldPackage = package_;
    package_ = newPackage;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JavaPackage.JCLASS__PACKAGE, oldPackage, newPackage);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPackage(JPackage newPackage)
  {
    if (newPackage != package_)
    {
      NotificationChain msgs = null;
      if (package_ != null)
        msgs = ((InternalEObject)package_).eInverseRemove(this, JavaPackage.JPACKAGE__TYPES, JPackage.class, msgs);
      if (newPackage != null)
        msgs = ((InternalEObject)newPackage).eInverseAdd(this, JavaPackage.JPACKAGE__TYPES, JPackage.class, msgs);
      msgs = basicSetPackage(newPackage, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JCLASS__PACKAGE, newPackage, newPackage));
  }

  protected EList types;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated EATM
   */
  public EList getTypes()
  {
     if (types == null)
     {
       types = new EObjectResolvingEList(JClass.class, this, JavaPackage.JCLASS__TYPES);
       types.addAll(EcoreUtil.getObjectsByType(getMembers(), JavaPackage.eINSTANCE.getJClass()));
     }

     return types;
  }

  protected EList allTypes;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated EATM
   */
  public EList getAllTypes()
  {
    if (allTypes == null)
    {
      allTypes = new EObjectResolvingEList(JClass.class, this, JavaPackage.JCLASS__ALL_TYPES);
      collectAll(JavaPackage.eINSTANCE.getJClass_AllTypes(), allTypes);
      allTypes.addAll(getTypes());
    }
    return allTypes;
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
        case JavaPackage.JCLASS__CONTAINING_TYPE:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, JavaPackage.JCLASS__CONTAINING_TYPE, msgs);
        case JavaPackage.JCLASS__MEMBERS:
          return ((InternalEList)getMembers()).basicAdd(otherEnd, msgs);
        case JavaPackage.JCLASS__COMPONENT_TYPE:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, JavaPackage.JCLASS__COMPONENT_TYPE, msgs);
        case JavaPackage.JCLASS__ARRAY_TYPE:
          if (arrayType != null)
            msgs = ((InternalEObject)arrayType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.JCLASS__ARRAY_TYPE, null, msgs);
          return basicSetArrayType((JClass)otherEnd, msgs);
        case JavaPackage.JCLASS__UNIT:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, JavaPackage.JCLASS__UNIT, msgs);
        case JavaPackage.JCLASS__PACKAGE:
          if (package_ != null)
            msgs = ((InternalEObject)package_).eInverseRemove(this, JavaPackage.JPACKAGE__TYPES, JPackage.class, msgs);
          return basicSetPackage((JPackage)otherEnd, msgs);
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
        case JavaPackage.JCLASS__CONTAINING_TYPE:
          return eBasicSetContainer(null, JavaPackage.JCLASS__CONTAINING_TYPE, msgs);
        case JavaPackage.JCLASS__MEMBERS:
          return ((InternalEList)getMembers()).basicRemove(otherEnd, msgs);
        case JavaPackage.JCLASS__COMPONENT_TYPE:
          return eBasicSetContainer(null, JavaPackage.JCLASS__COMPONENT_TYPE, msgs);
        case JavaPackage.JCLASS__ARRAY_TYPE:
          return basicSetArrayType(null, msgs);
        case JavaPackage.JCLASS__UNIT:
          return eBasicSetContainer(null, JavaPackage.JCLASS__UNIT, msgs);
        case JavaPackage.JCLASS__PACKAGE:
          return basicSetPackage(null, msgs);
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
  public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs)
  {
    if (eContainerFeatureID >= 0)
    {
      switch (eContainerFeatureID)
      {
        case JavaPackage.JCLASS__CONTAINING_TYPE:
          return eContainer.eInverseRemove(this, JavaPackage.JCLASS__MEMBERS, JClass.class, msgs);
        case JavaPackage.JCLASS__COMPONENT_TYPE:
          return eContainer.eInverseRemove(this, JavaPackage.JCLASS__ARRAY_TYPE, JClass.class, msgs);
        case JavaPackage.JCLASS__UNIT:
          return eContainer.eInverseRemove(this, JavaPackage.JCOMPILATION_UNIT__TYPES, JCompilationUnit.class, msgs);
        default:
          return eDynamicBasicRemoveFromContainer(msgs);
      }
    }
    return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
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
      case JavaPackage.JCLASS__NAME:
        return getName();
      case JavaPackage.JCLASS__JNODE:
        return getJNode();
      case JavaPackage.JCLASS__STATIC:
        return isStatic() ? Boolean.TRUE : Boolean.FALSE;
      case JavaPackage.JCLASS__VISIBILITY:
        return getVisibility();
      case JavaPackage.JCLASS__COMMENT:
        return getComment();
      case JavaPackage.JCLASS__CONTAINING_TYPE:
        return getContainingType();
      case JavaPackage.JCLASS__ABSTRACT:
        return isAbstract() ? Boolean.TRUE : Boolean.FALSE;
      case JavaPackage.JCLASS__FINAL:
        return isFinal() ? Boolean.TRUE : Boolean.FALSE;
      case JavaPackage.JCLASS__INTERFACE:
        return isInterface() ? Boolean.TRUE : Boolean.FALSE;
      case JavaPackage.JCLASS__THROWABLE:
        return isThrowable() ? Boolean.TRUE : Boolean.FALSE;
      case JavaPackage.JCLASS__JAVA_CLASS:
        return getJavaClass();
      case JavaPackage.JCLASS__FIELDS:
        return getFields();
      case JavaPackage.JCLASS__METHODS:
        return getMethods();
      case JavaPackage.JCLASS__SUPER_TYPES:
        return getSuperTypes();
      case JavaPackage.JCLASS__ALL_SUPER_TYPES:
        return getAllSuperTypes();
      case JavaPackage.JCLASS__MEMBERS:
        return getMembers();
      case JavaPackage.JCLASS__COMPONENT_TYPE:
        return getComponentType();
      case JavaPackage.JCLASS__ARRAY_TYPE:
        return getArrayType();
      case JavaPackage.JCLASS__UNIT:
        return getUnit();
      case JavaPackage.JCLASS__ALL_METHODS:
        return getAllMethods();
      case JavaPackage.JCLASS__ALL_FIELDS:
        return getAllFields();
      case JavaPackage.JCLASS__PACKAGE:
        if (resolve) return getPackage();
        return basicGetPackage();
      case JavaPackage.JCLASS__TYPES:
        return getTypes();
      case JavaPackage.JCLASS__ALL_TYPES:
        return getAllTypes();
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
      case JavaPackage.JCLASS__NAME:
        setName((String)newValue);
        return;
      case JavaPackage.JCLASS__JNODE:
        setJNode((Object)newValue);
        return;
      case JavaPackage.JCLASS__STATIC:
        setStatic(((Boolean)newValue).booleanValue());
        return;
      case JavaPackage.JCLASS__VISIBILITY:
        setVisibility((JVisibility)newValue);
        return;
      case JavaPackage.JCLASS__COMMENT:
        setComment((String)newValue);
        return;
      case JavaPackage.JCLASS__CONTAINING_TYPE:
        setContainingType((JClass)newValue);
        return;
      case JavaPackage.JCLASS__ABSTRACT:
        setAbstract(((Boolean)newValue).booleanValue());
        return;
      case JavaPackage.JCLASS__FINAL:
        setFinal(((Boolean)newValue).booleanValue());
        return;
      case JavaPackage.JCLASS__INTERFACE:
        setInterface(((Boolean)newValue).booleanValue());
        return;
      case JavaPackage.JCLASS__THROWABLE:
        setThrowable(((Boolean)newValue).booleanValue());
        return;
      case JavaPackage.JCLASS__JAVA_CLASS:
        setJavaClass((Class)newValue);
        return;
      case JavaPackage.JCLASS__FIELDS:
        getFields().clear();
        getFields().addAll((Collection)newValue);
        return;
      case JavaPackage.JCLASS__METHODS:
        getMethods().clear();
        getMethods().addAll((Collection)newValue);
        return;
      case JavaPackage.JCLASS__SUPER_TYPES:
        getSuperTypes().clear();
        getSuperTypes().addAll((Collection)newValue);
        return;
      case JavaPackage.JCLASS__ALL_SUPER_TYPES:
        getAllSuperTypes().clear();
        getAllSuperTypes().addAll((Collection)newValue);
        return;
      case JavaPackage.JCLASS__MEMBERS:
        getMembers().clear();
        getMembers().addAll((Collection)newValue);
        return;
      case JavaPackage.JCLASS__COMPONENT_TYPE:
        setComponentType((JClass)newValue);
        return;
      case JavaPackage.JCLASS__ARRAY_TYPE:
        setArrayType((JClass)newValue);
        return;
      case JavaPackage.JCLASS__UNIT:
        setUnit((JCompilationUnit)newValue);
        return;
      case JavaPackage.JCLASS__ALL_METHODS:
        getAllMethods().clear();
        getAllMethods().addAll((Collection)newValue);
        return;
      case JavaPackage.JCLASS__ALL_FIELDS:
        getAllFields().clear();
        getAllFields().addAll((Collection)newValue);
        return;
      case JavaPackage.JCLASS__PACKAGE:
        setPackage((JPackage)newValue);
        return;
      case JavaPackage.JCLASS__TYPES:
        getTypes().clear();
        getTypes().addAll((Collection)newValue);
        return;
      case JavaPackage.JCLASS__ALL_TYPES:
        getAllTypes().clear();
        getAllTypes().addAll((Collection)newValue);
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
      case JavaPackage.JCLASS__NAME:
        setName(NAME_EDEFAULT);
        return;
      case JavaPackage.JCLASS__JNODE:
        setJNode(JNODE_EDEFAULT);
        return;
      case JavaPackage.JCLASS__STATIC:
        setStatic(STATIC_EDEFAULT);
        return;
      case JavaPackage.JCLASS__VISIBILITY:
        setVisibility(VISIBILITY_EDEFAULT);
        return;
      case JavaPackage.JCLASS__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case JavaPackage.JCLASS__CONTAINING_TYPE:
        setContainingType((JClass)null);
        return;
      case JavaPackage.JCLASS__ABSTRACT:
        setAbstract(ABSTRACT_EDEFAULT);
        return;
      case JavaPackage.JCLASS__FINAL:
        setFinal(FINAL_EDEFAULT);
        return;
      case JavaPackage.JCLASS__INTERFACE:
        setInterface(INTERFACE_EDEFAULT);
        return;
      case JavaPackage.JCLASS__THROWABLE:
        setThrowable(THROWABLE_EDEFAULT);
        return;
      case JavaPackage.JCLASS__JAVA_CLASS:
        setJavaClass(JAVA_CLASS_EDEFAULT);
        return;
      case JavaPackage.JCLASS__FIELDS:
        getFields().clear();
        return;
      case JavaPackage.JCLASS__METHODS:
        getMethods().clear();
        return;
      case JavaPackage.JCLASS__SUPER_TYPES:
        getSuperTypes().clear();
        return;
      case JavaPackage.JCLASS__ALL_SUPER_TYPES:
        getAllSuperTypes().clear();
        return;
      case JavaPackage.JCLASS__MEMBERS:
        getMembers().clear();
        return;
      case JavaPackage.JCLASS__COMPONENT_TYPE:
        setComponentType((JClass)null);
        return;
      case JavaPackage.JCLASS__ARRAY_TYPE:
        setArrayType((JClass)null);
        return;
      case JavaPackage.JCLASS__UNIT:
        setUnit((JCompilationUnit)null);
        return;
      case JavaPackage.JCLASS__ALL_METHODS:
        getAllMethods().clear();
        return;
      case JavaPackage.JCLASS__ALL_FIELDS:
        getAllFields().clear();
        return;
      case JavaPackage.JCLASS__PACKAGE:
        setPackage((JPackage)null);
        return;
      case JavaPackage.JCLASS__TYPES:
        getTypes().clear();
        return;
      case JavaPackage.JCLASS__ALL_TYPES:
        getAllTypes().clear();
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
      case JavaPackage.JCLASS__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case JavaPackage.JCLASS__JNODE:
        return JNODE_EDEFAULT == null ? jNode != null : !JNODE_EDEFAULT.equals(jNode);
      case JavaPackage.JCLASS__STATIC:
        return static_ != STATIC_EDEFAULT;
      case JavaPackage.JCLASS__VISIBILITY:
        return visibility != VISIBILITY_EDEFAULT;
      case JavaPackage.JCLASS__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case JavaPackage.JCLASS__CONTAINING_TYPE:
        return getContainingType() != null;
      case JavaPackage.JCLASS__ABSTRACT:
        return abstract_ != ABSTRACT_EDEFAULT;
      case JavaPackage.JCLASS__FINAL:
        return final_ != FINAL_EDEFAULT;
      case JavaPackage.JCLASS__INTERFACE:
        return interface_ != INTERFACE_EDEFAULT;
      case JavaPackage.JCLASS__THROWABLE:
        return throwable != THROWABLE_EDEFAULT;
      case JavaPackage.JCLASS__JAVA_CLASS:
        return JAVA_CLASS_EDEFAULT == null ? javaClass != null : !JAVA_CLASS_EDEFAULT.equals(javaClass);
      case JavaPackage.JCLASS__FIELDS:
        return !getFields().isEmpty();
      case JavaPackage.JCLASS__METHODS:
        return !getMethods().isEmpty();
      case JavaPackage.JCLASS__SUPER_TYPES:
        return superTypes != null && !superTypes.isEmpty();
      case JavaPackage.JCLASS__ALL_SUPER_TYPES:
        return !getAllSuperTypes().isEmpty();
      case JavaPackage.JCLASS__MEMBERS:
        return members != null && !members.isEmpty();
      case JavaPackage.JCLASS__COMPONENT_TYPE:
        return getComponentType() != null;
      case JavaPackage.JCLASS__ARRAY_TYPE:
        return arrayType != null;
      case JavaPackage.JCLASS__UNIT:
        return getUnit() != null;
      case JavaPackage.JCLASS__ALL_METHODS:
        return !getAllMethods().isEmpty();
      case JavaPackage.JCLASS__ALL_FIELDS:
        return !getAllFields().isEmpty();
      case JavaPackage.JCLASS__PACKAGE:
        return package_ != null;
      case JavaPackage.JCLASS__TYPES:
        return types != null && !types.isEmpty();
      case JavaPackage.JCLASS__ALL_TYPES:
        return allTypes != null && !allTypes.isEmpty();
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
    result.append(" (abstract: ");
    result.append(abstract_);
    result.append(", final: ");
    result.append(final_);
    result.append(", interface: ");
    result.append(interface_);
    result.append(", throwable: ");
    result.append(throwable);
    result.append(", javaClass: ");
    result.append(javaClass);
    result.append(')');
    return result.toString();
  }

  protected void changeAttribute(Notification notification)
  {
    switch (notification.getFeatureID(JClass.class))
    {
      case JavaPackage.JCLASS__JNODE:
      {
        JDOMHelper.handleJNode(this);

        break;
      }
      case JavaPackage.JCLASS__JAVA_CLASS:
      {
        Class theJavaClass = getJavaClass();
        if (theJavaClass != null)
        {
          String uri = JavaUtil.createJClassProxyURI(theJavaClass).fragment();
          setName(uri.substring(uri.lastIndexOf("/") + 1));
          Collection theMembers = new ArrayList();

          try
          {
            Field [] memberFields = theJavaClass.getDeclaredFields();
            for (int i = 0; i < memberFields.length; ++i)
            {
              JField memberJField = JavaFactory.eINSTANCE.createJField();
              theMembers.add(memberJField);
              memberJField.setJavaField(memberFields[i]);
            }

            Method [] memberMethods = theJavaClass.getDeclaredMethods();
            for (int i = 0; i < memberMethods.length; ++i)
            {
              JMethod memberJMethod = JavaFactory.eINSTANCE.createJMethod();
              theMembers.add(memberJMethod);
              memberJMethod.setJavaMethod(memberMethods[i]);
            }

            Constructor [] constructors = theJavaClass.getConstructors();
            for (int i = 0; i < constructors.length; ++i)
            {
              JMethod memberJMethod = JavaFactory.eINSTANCE.createJMethod();
              theMembers.add(memberJMethod);
              memberJMethod.setJavaConstructor(constructors[i]);
            }

            Class [] memberTypes = theJavaClass.getDeclaredClasses();
            for (int i = 0; i < memberTypes.length; ++i)
            {
              JClass memberJClass = JavaFactory.eINSTANCE.createJClass();
              theMembers.add(memberJClass);
              memberJClass.setJavaClass(memberTypes[i]);
            }

            getMembers().addAll(theMembers);

            Collection theSuperTypes = new ArrayList();
            if (theJavaClass.getSuperclass() != null)
            {
              theSuperTypes.add(JavaUtil.createJClassProxy(theJavaClass.getSuperclass()));
            }
            Class [] interfaces = theJavaClass.getInterfaces();
            for (int i = 0; i < interfaces.length; ++i)
            {
              theSuperTypes.add(JavaUtil.createJClassProxy(interfaces[i]));
            }
            getSuperTypes().addAll(theSuperTypes);
          }
          catch (NoClassDefFoundError exception)
          {
          }

          if (theJavaClass.getComponentType() != null)
          {
            setComponentType(JavaUtil.createJClassProxy(theJavaClass.getComponentType()));
          }

          int modifiers = theJavaClass.getModifiers();
          setInterface(Modifier.isInterface(modifiers));
          setFinal(Modifier.isInterface(modifiers));
          setAbstract(Modifier.isAbstract(modifiers));
          setStatic(Modifier.isStatic(modifiers));
          setVisibility(JavaUtil.getModifierVisibility(modifiers));
        }

        break;
      }
    }
  }


  protected static class JDOMHelper
  {
    protected static void handleJNode(JClass jClass)
    { 
      IDOMType iDOMType = (IDOMType)jClass.getJNode();
      if (iDOMType != null)
      {
        jClass.setName(iDOMType.getName());
        jClass.setComment(iDOMType.getComment());
        int flags = iDOMType.getFlags();
        jClass.setInterface((flags & Flags.AccInterface) != 0);
        jClass.setFinal((flags & Flags.AccFinal) != 0);
        jClass.setAbstract((flags & Flags.AccAbstract) != 0);
        jClass.setStatic((flags & Flags.AccStatic) != 0);
        jClass.setVisibility(JavaUtil.getFlagVisibility(flags));

        Collection theMembers = jClass.getMembers();
        for (IDOMNode child = iDOMType.getFirstChild(); child != null; child = child.getNextNode())
        {
          if (child.getNodeType() == IDOMNode.FIELD)
          {
            JField jField = JavaFactory.eINSTANCE.createJField();
            jField.setJNode(child);
            theMembers.add(jField);
          }
          else if (child.getNodeType() == IDOMNode.METHOD)
          {
            JMethod jMethod = JavaFactory.eINSTANCE.createJMethod();
            jMethod.setJNode(child);
            theMembers.add(jMethod);
          }
          else if (child.getNodeType() == IDOMNode.INITIALIZER)
          {
            JInitializer jInitializer = JavaFactory.eINSTANCE.createJInitializer();
            jInitializer.setJNode(child);
            theMembers.add(jInitializer);
          }
          else if (child.getNodeType() == IDOMNode.TYPE)
          {
            JClass nestedJClass = JavaFactory.eINSTANCE.createJClass();
            nestedJClass.setJNode(child);
            theMembers.add(nestedJClass);
          }
        }
      }
    } 
  }

  protected void resolveIdentifiers()
  {
    if (jNode != null)
    {
      List theSuperTypes = new ArrayList();
      IDOMType iDOMType = (IDOMType)jNode;
      String superClass = iDOMType.getSuperclass();
      if (superClass != null)
      {
        JClass jClass = 
          getUnit() != null ? 
            getUnit().resolveJClass(superClass.trim()) : 
            getContainingType().resolveJClass(superClass.trim());
        if (jClass != null)
        {
          theSuperTypes.add(jClass);
        }
      }

      String superInterfaces[] = iDOMType.getSuperInterfaces();
      if (superInterfaces != null)
      {
        for (int i = 0; i < superInterfaces.length; ++i)
        {
          JClass jClass = 
            getUnit() != null ? 
              getUnit().resolveJClass(superInterfaces[i].trim()) :
              getContainingType().resolveJClass(superInterfaces[i].trim());
          if (jClass != null)
          {
            theSuperTypes.add(jClass);
          }
        }
      }

      EcoreUtil.setEList(getSuperTypes(), theSuperTypes);
    }

    for (Iterator i = getMembers().iterator(); i.hasNext(); )
    {
      JModelElementImpl jModelElement = (JModelElementImpl)i.next();
      jModelElement.resolveIdentifiers();
    }
  }

  public JClass resolveJClass(String fullName)
  {
    for (Iterator i = getAllTypes().iterator(); i.hasNext(); )
    {
      JClass jClass = (JClass)i.next();
      if (JavaUtil.isPrefixOf(jClass.getName(), fullName))
      {
        if (fullName.length() > jClass.getName().length())
        {
          return jClass.resolveJClass(fullName.substring(jClass.getName().length() + 1));
        }
        else
        {
          return jClass;
        }
      }
    }

    if (getContainingType() != null)
    {
      return getContainingType().resolveJClass(fullName);
    }
    else if (getUnit() != null)
    {
      return getUnit().resolveJClass(fullName);
    }
    else
    {
      return null;
    }
  }

  public EObject eObjectForURIFragmentSegment(String uriFragmentSegment)
  {
    if (uriFragmentSegment.startsWith("@"))
    {
      return super.eObjectForURIFragmentSegment(uriFragmentSegment);
    }
    else if (uriFragmentSegment.equals("[]"))
    {
      JClass result = getArrayType();
      if (result == null)
      {
        result = JavaFactory.eINSTANCE.createJClass();
        result.setName(getName() + "[]");
        setArrayType(result);
      }
      return result;
    }
    else
    {
      for (Iterator i = getMembers().iterator(); i.hasNext(); )
      {
        JMember jMember = (JMember)i.next();
        if (uriFragmentSegment.equals(jMember.getName()) && jMember instanceof JClass)
        {
          return jMember;
        }
      }

      return null;
    }
  }

  public String getQualifiedName()
  {
    if (getComponentType() != null)
    {
      return getComponentType().getQualifiedName() + "[]";
    }
    else if (getContainingType() != null)
    {
      return getContainingType().getQualifiedName() + "." + getName();
    }
    else if (getPackage() != null)
    {
      String containerQualifiedName = getPackage().getQualifiedName();
      if (containerQualifiedName.length() > 0)
      {
        return containerQualifiedName + "." + getName();
      }
    }

    return getName();
  }
} //JClassImpl
