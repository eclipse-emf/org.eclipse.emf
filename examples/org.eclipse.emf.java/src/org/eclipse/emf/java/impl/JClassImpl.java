/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: JClassImpl.java,v 1.14 2008/12/22 14:26:08 emerks Exp $
 */
package org.eclipse.emf.java.impl;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.core.Flags;

import org.eclipse.emf.codegen.merge.java.facade.FacadeVisitor;
import org.eclipse.emf.codegen.merge.java.facade.JAbstractType;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
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
   * The cached value of the '{@link #getJavaClass() <em>Java Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJavaClass()
   * @generated
   * @ordered
   */
  protected Class<?> javaClass;

  /**
   * The cached value of the '{@link #getSuperTypes() <em>Super Types</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSuperTypes()
   * @generated
   * @ordered
   */
  protected EList<JClass> superTypes;

  /**
   * The cached value of the '{@link #getMembers() <em>Members</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMembers()
   * @generated
   * @ordered
   */
  protected EList<JMember> members;

  /**
   * The cached value of the '{@link #getArrayType() <em>Array Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getArrayType()
   * @generated
   * @ordered
   */
  protected JClass arrayType;

  /**
   * The cached value of the '{@link #getPackage() <em>Package</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPackage()
   * @generated
   * @ordered
   */
  protected JPackage package_;

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
  @Override
  protected EClass eStaticClass()
  {
    return JavaPackage.Literals.JCLASS;
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
  public Class<?> getJavaClass()
  {
    return javaClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setJavaClass(Class<?> newJavaClass)
  {
    Class<?> oldJavaClass = javaClass;
    javaClass = newJavaClass;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JCLASS__JAVA_CLASS, oldJavaClass, javaClass));
  }

  EList<JField> fields;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated EATM
   */
  public EList<JField> getFields()
  {
    if (fields == null)
    {
      fields = new EObjectResolvingEList<JField>(JField.class, this, JavaPackage.JCLASS__FIELDS);
      @SuppressWarnings("unchecked")
      List<JField> list = (List<JField>)(List<?>)EcoreUtil.getObjectsByType(getMembers(), JavaPackage.eINSTANCE.getJField());
      fields.addAll(list);
    }
    return fields;
  }

  EList<JMethod> methods;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated EATM
   */
  public EList<JMethod> getMethods()
  {
    if (methods == null)
    {
      methods = new EObjectResolvingEList<JMethod>(JMethod.class, this, JavaPackage.JCLASS__METHODS);
      @SuppressWarnings("unchecked")
      List<JMethod> list = (List<JMethod>)(List<?>)EcoreUtil.getObjectsByType(getMembers(), JavaPackage.eINSTANCE.getJMethod());
      methods.addAll(list);
    }
    return methods;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<JClass> getSuperTypes()
  {
    if (superTypes == null)
    {
      superTypes = new EObjectResolvingEList<JClass>(JClass.class, this, JavaPackage.JCLASS__SUPER_TYPES);
    }
    return superTypes;
  }

  EList<JClass> allSuperTypes;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated EATM
   */
  public EList<JClass> getAllSuperTypes()
  {
    if (allSuperTypes == null)
    {
      allSuperTypes = new EObjectResolvingEList<JClass>(JClass.class, this, JavaPackage.JCLASS__ALL_SUPER_TYPES);
      collectAll(JavaPackage.eINSTANCE.getJClass_AllSuperTypes(), allSuperTypes);
      allSuperTypes.addAll(getSuperTypes());
    }

    return allSuperTypes;
  }

  protected <T> void collectAll(EStructuralFeature eStructuralFeature, EList<T> target)
  {
    for (JClass jClass : getSuperTypes())
    {
      @SuppressWarnings("unchecked")
      EList<T> list = (EList<T>)jClass.eGet(eStructuralFeature);
      target.addAll(list);
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<JMember> getMembers()
  {
    if (members == null)
    {
      members = new EObjectContainmentWithInverseEList<JMember>(JMember.class, this, JavaPackage.JCLASS__MEMBERS, JavaPackage.JMEMBER__CONTAINING_TYPE);
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
    return (JClass)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetComponentType(JClass newComponentType, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newComponentType, JavaPackage.JCLASS__COMPONENT_TYPE, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComponentType(JClass newComponentType)
  {
    if (newComponentType != eInternalContainer() || (eContainerFeatureID != JavaPackage.JCLASS__COMPONENT_TYPE && newComponentType != null))
    {
      if (EcoreUtil.isAncestor(this, newComponentType))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newComponentType != null)
        msgs = ((InternalEObject)newComponentType).eInverseAdd(this, JavaPackage.JCLASS__ARRAY_TYPE, JClass.class, msgs);
      msgs = basicSetComponentType(newComponentType, msgs);
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
    return (JCompilationUnit)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUnit(JCompilationUnit newUnit, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newUnit, JavaPackage.JCLASS__UNIT, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUnit(JCompilationUnit newUnit)
  {
    if (newUnit != eInternalContainer() || (eContainerFeatureID != JavaPackage.JCLASS__UNIT && newUnit != null))
    {
      if (EcoreUtil.isAncestor(this, newUnit))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newUnit != null)
        msgs = ((InternalEObject)newUnit).eInverseAdd(this, JavaPackage.JCOMPILATION_UNIT__TYPES, JCompilationUnit.class, msgs);
      msgs = basicSetUnit(newUnit, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JCLASS__UNIT, newUnit, newUnit));
  }

  EList<JMethod> allMethods;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated EATM
   */
  public EList<JMethod> getAllMethods()
  {
    if (allMethods == null)
    {
      allMethods = new EObjectResolvingEList<JMethod>(JMethod.class, this, JavaPackage.JCLASS__ALL_METHODS);
      collectAll(JavaPackage.eINSTANCE.getJClass_AllMethods(), allMethods);
      allMethods.addAll(getMethods());
    }

    return allMethods;
  }

  EList<JField> allFields;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated EATM
   */
  public EList<JField> getAllFields()
  {
    if (allFields == null)
    {
      allFields = new EObjectResolvingEList<JField>(JField.class, this, JavaPackage.JCLASS__ALL_FIELDS);
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
      InternalEObject oldPackage = (InternalEObject)package_;
      package_ = (JPackage)eResolveProxy(oldPackage);
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

  protected EList<JClass> types;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated EATM
   */
  public EList<JClass> getTypes()
  {
     if (types == null)
     {
       types = new EObjectResolvingEList<JClass>(JClass.class, this, JavaPackage.JCLASS__TYPES);
       @SuppressWarnings("unchecked")
       List<JClass> list = (List<JClass>)(List<?>)EcoreUtil.getObjectsByType(getMembers(), JavaPackage.eINSTANCE.getJClass());
       types.addAll(list);
     }

     return types;
  }

  protected EList<JClass> allTypes;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated EATM
   */
  public EList<JClass> getAllTypes()
  {
    if (allTypes == null)
    {
      allTypes = new EObjectResolvingEList<JClass>(JClass.class, this, JavaPackage.JCLASS__ALL_TYPES);
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
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case JavaPackage.JCLASS__MEMBERS:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getMembers()).basicAdd(otherEnd, msgs);
      case JavaPackage.JCLASS__COMPONENT_TYPE:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetComponentType((JClass)otherEnd, msgs);
      case JavaPackage.JCLASS__ARRAY_TYPE:
        if (arrayType != null)
          msgs = ((InternalEObject)arrayType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JavaPackage.JCLASS__ARRAY_TYPE, null, msgs);
        return basicSetArrayType((JClass)otherEnd, msgs);
      case JavaPackage.JCLASS__UNIT:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetUnit((JCompilationUnit)otherEnd, msgs);
      case JavaPackage.JCLASS__PACKAGE:
        if (package_ != null)
          msgs = ((InternalEObject)package_).eInverseRemove(this, JavaPackage.JPACKAGE__TYPES, JPackage.class, msgs);
        return basicSetPackage((JPackage)otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case JavaPackage.JCLASS__MEMBERS:
        return ((InternalEList<?>)getMembers()).basicRemove(otherEnd, msgs);
      case JavaPackage.JCLASS__COMPONENT_TYPE:
        return basicSetComponentType(null, msgs);
      case JavaPackage.JCLASS__ARRAY_TYPE:
        return basicSetArrayType(null, msgs);
      case JavaPackage.JCLASS__UNIT:
        return basicSetUnit(null, msgs);
      case JavaPackage.JCLASS__PACKAGE:
        return basicSetPackage(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
  {
    switch (eContainerFeatureID)
    {
      case JavaPackage.JCLASS__COMPONENT_TYPE:
        return eInternalContainer().eInverseRemove(this, JavaPackage.JCLASS__ARRAY_TYPE, JClass.class, msgs);
      case JavaPackage.JCLASS__UNIT:
        return eInternalContainer().eInverseRemove(this, JavaPackage.JCOMPILATION_UNIT__TYPES, JCompilationUnit.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case JavaPackage.JCLASS__ABSTRACT:
        return isAbstract();
      case JavaPackage.JCLASS__FINAL:
        return isFinal();
      case JavaPackage.JCLASS__INTERFACE:
        return isInterface();
      case JavaPackage.JCLASS__THROWABLE:
        return isThrowable();
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
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case JavaPackage.JCLASS__ABSTRACT:
        setAbstract((Boolean)newValue);
        return;
      case JavaPackage.JCLASS__FINAL:
        setFinal((Boolean)newValue);
        return;
      case JavaPackage.JCLASS__INTERFACE:
        setInterface((Boolean)newValue);
        return;
      case JavaPackage.JCLASS__THROWABLE:
        setThrowable((Boolean)newValue);
        return;
      case JavaPackage.JCLASS__JAVA_CLASS:
        setJavaClass((Class<?>)newValue);
        return;
      case JavaPackage.JCLASS__FIELDS:
        getFields().clear();
        getFields().addAll((Collection<? extends JField>)newValue);
        return;
      case JavaPackage.JCLASS__METHODS:
        getMethods().clear();
        getMethods().addAll((Collection<? extends JMethod>)newValue);
        return;
      case JavaPackage.JCLASS__SUPER_TYPES:
        getSuperTypes().clear();
        getSuperTypes().addAll((Collection<? extends JClass>)newValue);
        return;
      case JavaPackage.JCLASS__ALL_SUPER_TYPES:
        getAllSuperTypes().clear();
        getAllSuperTypes().addAll((Collection<? extends JClass>)newValue);
        return;
      case JavaPackage.JCLASS__MEMBERS:
        getMembers().clear();
        getMembers().addAll((Collection<? extends JMember>)newValue);
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
        getAllMethods().addAll((Collection<? extends JMethod>)newValue);
        return;
      case JavaPackage.JCLASS__ALL_FIELDS:
        getAllFields().clear();
        getAllFields().addAll((Collection<? extends JField>)newValue);
        return;
      case JavaPackage.JCLASS__PACKAGE:
        setPackage((JPackage)newValue);
        return;
      case JavaPackage.JCLASS__TYPES:
        getTypes().clear();
        getTypes().addAll((Collection<? extends JClass>)newValue);
        return;
      case JavaPackage.JCLASS__ALL_TYPES:
        getAllTypes().clear();
        getAllTypes().addAll((Collection<? extends JClass>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
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
        setJavaClass((Class<?>)null);
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
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case JavaPackage.JCLASS__ABSTRACT:
        return abstract_ != ABSTRACT_EDEFAULT;
      case JavaPackage.JCLASS__FINAL:
        return final_ != FINAL_EDEFAULT;
      case JavaPackage.JCLASS__INTERFACE:
        return interface_ != INTERFACE_EDEFAULT;
      case JavaPackage.JCLASS__THROWABLE:
        return throwable != THROWABLE_EDEFAULT;
      case JavaPackage.JCLASS__JAVA_CLASS:
        return javaClass != null;
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
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
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

  @Override
  protected void changeAttribute(Notification notification)
  {
    switch (notification.getFeatureID(JClass.class))
    {
      case JavaPackage.JCLASS__JNODE:
      {
        JHelper.handleJNode(this);

        break;
      }
      case JavaPackage.JCLASS__JAVA_CLASS:
      {
        Class<?> theJavaClass = getJavaClass();
        if (theJavaClass != null)
        {
          String uri = JavaUtil.createJClassProxyURI(theJavaClass).fragment();
          setName(uri.substring(uri.lastIndexOf("/") + 1));
          Collection<JMember> theMembers = new ArrayList<JMember>();

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

            Constructor<?> [] constructors = theJavaClass.getConstructors();
            for (int i = 0; i < constructors.length; ++i)
            {
              JMethod memberJMethod = JavaFactory.eINSTANCE.createJMethod();
              theMembers.add(memberJMethod);
              memberJMethod.setJavaConstructor(constructors[i]);
            }

            Class<?> [] memberTypes = theJavaClass.getDeclaredClasses();
            for (int i = 0; i < memberTypes.length; ++i)
            {
              JClass memberJClass = JavaFactory.eINSTANCE.createJClass();
              theMembers.add(memberJClass);
              memberJClass.setJavaClass(memberTypes[i]);
            }

            getMembers().addAll(theMembers);

            Collection<JClass> theSuperTypes = new ArrayList<JClass>();
            if (theJavaClass.getSuperclass() != null)
            {
              theSuperTypes.add(JavaUtil.createJClassProxy(theJavaClass.getSuperclass()));
            }
            Class<?> [] interfaces = theJavaClass.getInterfaces();
            for (int i = 0; i < interfaces.length; ++i)
            {
              theSuperTypes.add(JavaUtil.createJClassProxy(interfaces[i]));
            }
            getSuperTypes().addAll(theSuperTypes);
          }
          catch (NoClassDefFoundError exception)
          {
            // Ignore
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


  protected static class JHelper
  {
    protected static void handleJNode(JClass cls)
    { 
      org.eclipse.emf.codegen.merge.java.facade.JType jType = (org.eclipse.emf.codegen.merge.java.facade.JType)cls.getJNode();
      if (jType != null)
      {
        cls.setName(JavaUtil.separateTypeArgument(jType.getName())[0]);
        cls.setComment(jType.getComment());
        int flags = jType.getFlags();
        cls.setInterface((flags & Flags.AccInterface) != 0);
        cls.setFinal((flags & Flags.AccFinal) != 0);
        cls.setAbstract((flags & Flags.AccAbstract) != 0);
        cls.setStatic((flags & Flags.AccStatic) != 0);
        cls.setVisibility(JavaUtil.getFlagVisibility(flags));

        final Collection<JMember> theMembers = cls.getMembers();
        FacadeVisitor facadeVisitor = new FacadeVisitor()
        {
          @Override
          protected boolean visit(org.eclipse.emf.codegen.merge.java.facade.JField jField)
          {
            JField field = JavaFactory.eINSTANCE.createJField();
            field.setJNode(jField);
            theMembers.add(field);
            return false;
          }
          
          @Override
          protected boolean visit(org.eclipse.emf.codegen.merge.java.facade.JMethod jMethod)
          {
            JMethod method = JavaFactory.eINSTANCE.createJMethod();
            method.setJNode(jMethod);
            theMembers.add(method);
            return false;
          }
          
          @Override
          protected boolean visit(org.eclipse.emf.codegen.merge.java.facade.JInitializer jInitializer)
          {
            JInitializer initializer = JavaFactory.eINSTANCE.createJInitializer();
            initializer.setJNode(jInitializer);
            theMembers.add(initializer);
            return false;
          }
          
          @Override
          protected boolean visit(JAbstractType jAbstractType)
          {
            if (jAbstractType == getRootNode())
            {
              return true;
            }
            else
            {
              if (jAbstractType instanceof org.eclipse.emf.codegen.merge.java.facade.JType)
              {
                JClass nestedClass = JavaFactory.eINSTANCE.createJClass();
                nestedClass.setJNode(jAbstractType);
                theMembers.add(nestedClass);
              }
              return false;
            }
          }
        };
        facadeVisitor.start(jType);
      }
    } 
  }

  @Override
  protected void resolveIdentifiers()
  {
    if (jNode != null)
    {
      List<JClass> theSuperTypes = new ArrayList<JClass>();
      org.eclipse.emf.codegen.merge.java.facade.JType jType = (org.eclipse.emf.codegen.merge.java.facade.JType)jNode;
      String superClass = jType.getSuperclass();
      if (superClass != null)
      {
        superClass = JavaUtil.separateTypeArgument(jType.getSuperclass())[0];
        JClass jClass = 
          getUnit() != null ? 
            getUnit().resolveJClass(superClass.trim()) : 
            getContainingType().resolveJClass(superClass.trim());
        if (jClass != null)
        {
          theSuperTypes.add(jClass);
        }
      }

      String superInterfaces[] = jType.getSuperInterfaces();
      if (superInterfaces != null)
      {
        for (int i = 0; i < superInterfaces.length; ++i)
        {
          String superInterface = JavaUtil.separateTypeArgument(superInterfaces[i])[0];
          JClass jClass = 
            getUnit() != null ? 
              getUnit().resolveJClass(superInterface) :
              getContainingType().resolveJClass(superInterface);
          if (jClass != null)
          {
            theSuperTypes.add(jClass);
          }
        }
      }

      ECollections.setEList(getSuperTypes(), theSuperTypes);
    }

    for (JMember jMember : getMembers())
    {
      JModelElementImpl jModelElement = (JModelElementImpl)jMember;
      jModelElement.resolveIdentifiers();
    }
  }

  public JClass resolveJClass(String fullName)
  {
    for (JClass jClass : getAllTypes())
    {
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

  @Override
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
      for (JMember jMember : getMembers())
      {
        if (uriFragmentSegment.equals(jMember.getName()) && jMember instanceof JClass)
        {
          return jMember;
        }
      }

      return null;
    }
  }

  @Override
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
