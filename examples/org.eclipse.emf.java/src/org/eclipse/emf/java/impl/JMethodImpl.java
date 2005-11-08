/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: JMethodImpl.java,v 1.5 2005/11/08 14:15:26 emerks Exp $
 */
package org.eclipse.emf.java.impl;


import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.jdom.IDOMMethod;
import org.eclipse.jdt.core.jdom.IDOMType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.java.JClass;
import org.eclipse.emf.java.JMethod;
import org.eclipse.emf.java.JParameter;
import org.eclipse.emf.java.JVisibility;
import org.eclipse.emf.java.JavaFactory;
import org.eclipse.emf.java.JavaPackage;
import org.eclipse.emf.java.util.JavaUtil;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>JMethod</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.java.impl.JMethodImpl#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JMethodImpl#isFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JMethodImpl#isNative <em>Native</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JMethodImpl#isSynchronized <em>Synchronized</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JMethodImpl#getJavaMethod <em>Java Method</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JMethodImpl#isConstructor <em>Constructor</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JMethodImpl#getJavaConstructor <em>Java Constructor</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JMethodImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JMethodImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JMethodImpl#getExceptions <em>Exceptions</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JMethodImpl#getReturnType <em>Return Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JMethodImpl extends JMemberImpl implements JMethod
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
   * The default value of the '{@link #isNative() <em>Native</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNative()
   * @generated
   * @ordered
   */
  protected static final boolean NATIVE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isNative() <em>Native</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNative()
   * @generated
   * @ordered
   */
  protected boolean native_ = NATIVE_EDEFAULT;

  /**
   * The default value of the '{@link #isSynchronized() <em>Synchronized</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSynchronized()
   * @generated
   * @ordered
   */
  protected static final boolean SYNCHRONIZED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSynchronized() <em>Synchronized</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSynchronized()
   * @generated
   * @ordered
   */
  protected boolean synchronized_ = SYNCHRONIZED_EDEFAULT;

  /**
   * The default value of the '{@link #getJavaMethod() <em>Java Method</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJavaMethod()
   * @generated
   * @ordered
   */
  protected static final Method JAVA_METHOD_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getJavaMethod() <em>Java Method</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJavaMethod()
   * @generated
   * @ordered
   */
  protected Method javaMethod = JAVA_METHOD_EDEFAULT;

  /**
   * The default value of the '{@link #isConstructor() <em>Constructor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isConstructor()
   * @generated
   * @ordered
   */
  protected static final boolean CONSTRUCTOR_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isConstructor() <em>Constructor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isConstructor()
   * @generated
   * @ordered
   */
  protected boolean constructor = CONSTRUCTOR_EDEFAULT;

  /**
   * The default value of the '{@link #getJavaConstructor() <em>Java Constructor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJavaConstructor()
   * @generated
   * @ordered
   */
  protected static final Constructor JAVA_CONSTRUCTOR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getJavaConstructor() <em>Java Constructor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJavaConstructor()
   * @generated
   * @ordered
   */
  protected Constructor javaConstructor = JAVA_CONSTRUCTOR_EDEFAULT;

  /**
   * The default value of the '{@link #getBody() <em>Body</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBody()
   * @generated
   * @ordered
   */
  protected static final String BODY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getBody() <em>Body</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBody()
   * @generated
   * @ordered
   */
  protected String body = BODY_EDEFAULT;

  /**
   * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameters()
   * @generated
   * @ordered
   */
  protected EList parameters = null;

  /**
   * The cached value of the '{@link #getExceptions() <em>Exceptions</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExceptions()
   * @generated
   * @ordered
   */
  protected EList exceptions = null;

  /**
   * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReturnType()
   * @generated
   * @ordered
   */
  protected JClass returnType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected JMethodImpl()
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
    return JavaPackage.eINSTANCE.getJMethod();
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
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JMETHOD__ABSTRACT, oldAbstract, abstract_));
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
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JMETHOD__FINAL, oldFinal, final_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isNative()
  {
    return native_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNative(boolean newNative)
  {
    boolean oldNative = native_;
    native_ = newNative;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JMETHOD__NATIVE, oldNative, native_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSynchronized()
  {
    return synchronized_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSynchronized(boolean newSynchronized)
  {
    boolean oldSynchronized = synchronized_;
    synchronized_ = newSynchronized;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JMETHOD__SYNCHRONIZED, oldSynchronized, synchronized_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Method getJavaMethod()
  {
    return javaMethod;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setJavaMethod(Method newJavaMethod)
  {
    Method oldJavaMethod = javaMethod;
    javaMethod = newJavaMethod;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JMETHOD__JAVA_METHOD, oldJavaMethod, javaMethod));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isConstructor()
  {
    return constructor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstructor(boolean newConstructor)
  {
    boolean oldConstructor = constructor;
    constructor = newConstructor;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JMETHOD__CONSTRUCTOR, oldConstructor, constructor));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Constructor getJavaConstructor()
  {
    return javaConstructor;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setJavaConstructor(Constructor newJavaConstructor)
  {
    Constructor oldJavaConstructor = javaConstructor;
    javaConstructor = newJavaConstructor;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JMETHOD__JAVA_CONSTRUCTOR, oldJavaConstructor, javaConstructor));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getBody()
  {
    return body;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBody(String newBody)
  {
    String oldBody = body;
    body = newBody;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JMETHOD__BODY, oldBody, body));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getParameters()
  {
    if (parameters == null)
    {
      parameters = new EObjectContainmentWithInverseEList(JParameter.class, this, JavaPackage.JMETHOD__PARAMETERS, JavaPackage.JPARAMETER__METHOD);
    }
    return parameters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getExceptions()
  {
    if (exceptions == null)
    {
      exceptions = new EObjectResolvingEList(JClass.class, this, JavaPackage.JMETHOD__EXCEPTIONS);
    }
    return exceptions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JClass getReturnType()
  {
    if (returnType != null && returnType.eIsProxy())
    {
      JClass oldReturnType = returnType;
      returnType = (JClass)eResolveProxy((InternalEObject)returnType);
      if (returnType != oldReturnType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, JavaPackage.JMETHOD__RETURN_TYPE, oldReturnType, returnType));
      }
    }
    return returnType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JClass basicGetReturnType()
  {
    return returnType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReturnType(JClass newReturnType)
  {
    JClass oldReturnType = returnType;
    returnType = newReturnType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JMETHOD__RETURN_TYPE, oldReturnType, returnType));
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
        case JavaPackage.JMETHOD__CONTAINING_TYPE:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, JavaPackage.JMETHOD__CONTAINING_TYPE, msgs);
        case JavaPackage.JMETHOD__PARAMETERS:
          return ((InternalEList)getParameters()).basicAdd(otherEnd, msgs);
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
        case JavaPackage.JMETHOD__CONTAINING_TYPE:
          return eBasicSetContainer(null, JavaPackage.JMETHOD__CONTAINING_TYPE, msgs);
        case JavaPackage.JMETHOD__PARAMETERS:
          return ((InternalEList)getParameters()).basicRemove(otherEnd, msgs);
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
      case JavaPackage.JMETHOD__NAME:
        return getName();
      case JavaPackage.JMETHOD__JNODE:
        return getJNode();
      case JavaPackage.JMETHOD__STATIC:
        return isStatic() ? Boolean.TRUE : Boolean.FALSE;
      case JavaPackage.JMETHOD__VISIBILITY:
        return getVisibility();
      case JavaPackage.JMETHOD__COMMENT:
        return getComment();
      case JavaPackage.JMETHOD__CONTAINING_TYPE:
        return getContainingType();
      case JavaPackage.JMETHOD__ABSTRACT:
        return isAbstract() ? Boolean.TRUE : Boolean.FALSE;
      case JavaPackage.JMETHOD__FINAL:
        return isFinal() ? Boolean.TRUE : Boolean.FALSE;
      case JavaPackage.JMETHOD__NATIVE:
        return isNative() ? Boolean.TRUE : Boolean.FALSE;
      case JavaPackage.JMETHOD__SYNCHRONIZED:
        return isSynchronized() ? Boolean.TRUE : Boolean.FALSE;
      case JavaPackage.JMETHOD__JAVA_METHOD:
        return getJavaMethod();
      case JavaPackage.JMETHOD__CONSTRUCTOR:
        return isConstructor() ? Boolean.TRUE : Boolean.FALSE;
      case JavaPackage.JMETHOD__JAVA_CONSTRUCTOR:
        return getJavaConstructor();
      case JavaPackage.JMETHOD__BODY:
        return getBody();
      case JavaPackage.JMETHOD__PARAMETERS:
        return getParameters();
      case JavaPackage.JMETHOD__EXCEPTIONS:
        return getExceptions();
      case JavaPackage.JMETHOD__RETURN_TYPE:
        if (resolve) return getReturnType();
        return basicGetReturnType();
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
      case JavaPackage.JMETHOD__NAME:
        setName((String)newValue);
        return;
      case JavaPackage.JMETHOD__JNODE:
        setJNode((Object)newValue);
        return;
      case JavaPackage.JMETHOD__STATIC:
        setStatic(((Boolean)newValue).booleanValue());
        return;
      case JavaPackage.JMETHOD__VISIBILITY:
        setVisibility((JVisibility)newValue);
        return;
      case JavaPackage.JMETHOD__COMMENT:
        setComment((String)newValue);
        return;
      case JavaPackage.JMETHOD__CONTAINING_TYPE:
        setContainingType((JClass)newValue);
        return;
      case JavaPackage.JMETHOD__ABSTRACT:
        setAbstract(((Boolean)newValue).booleanValue());
        return;
      case JavaPackage.JMETHOD__FINAL:
        setFinal(((Boolean)newValue).booleanValue());
        return;
      case JavaPackage.JMETHOD__NATIVE:
        setNative(((Boolean)newValue).booleanValue());
        return;
      case JavaPackage.JMETHOD__SYNCHRONIZED:
        setSynchronized(((Boolean)newValue).booleanValue());
        return;
      case JavaPackage.JMETHOD__JAVA_METHOD:
        setJavaMethod((Method)newValue);
        return;
      case JavaPackage.JMETHOD__CONSTRUCTOR:
        setConstructor(((Boolean)newValue).booleanValue());
        return;
      case JavaPackage.JMETHOD__JAVA_CONSTRUCTOR:
        setJavaConstructor((Constructor)newValue);
        return;
      case JavaPackage.JMETHOD__BODY:
        setBody((String)newValue);
        return;
      case JavaPackage.JMETHOD__PARAMETERS:
        getParameters().clear();
        getParameters().addAll((Collection)newValue);
        return;
      case JavaPackage.JMETHOD__EXCEPTIONS:
        getExceptions().clear();
        getExceptions().addAll((Collection)newValue);
        return;
      case JavaPackage.JMETHOD__RETURN_TYPE:
        setReturnType((JClass)newValue);
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
      case JavaPackage.JMETHOD__NAME:
        setName(NAME_EDEFAULT);
        return;
      case JavaPackage.JMETHOD__JNODE:
        setJNode(JNODE_EDEFAULT);
        return;
      case JavaPackage.JMETHOD__STATIC:
        setStatic(STATIC_EDEFAULT);
        return;
      case JavaPackage.JMETHOD__VISIBILITY:
        setVisibility(VISIBILITY_EDEFAULT);
        return;
      case JavaPackage.JMETHOD__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case JavaPackage.JMETHOD__CONTAINING_TYPE:
        setContainingType((JClass)null);
        return;
      case JavaPackage.JMETHOD__ABSTRACT:
        setAbstract(ABSTRACT_EDEFAULT);
        return;
      case JavaPackage.JMETHOD__FINAL:
        setFinal(FINAL_EDEFAULT);
        return;
      case JavaPackage.JMETHOD__NATIVE:
        setNative(NATIVE_EDEFAULT);
        return;
      case JavaPackage.JMETHOD__SYNCHRONIZED:
        setSynchronized(SYNCHRONIZED_EDEFAULT);
        return;
      case JavaPackage.JMETHOD__JAVA_METHOD:
        setJavaMethod(JAVA_METHOD_EDEFAULT);
        return;
      case JavaPackage.JMETHOD__CONSTRUCTOR:
        setConstructor(CONSTRUCTOR_EDEFAULT);
        return;
      case JavaPackage.JMETHOD__JAVA_CONSTRUCTOR:
        setJavaConstructor(JAVA_CONSTRUCTOR_EDEFAULT);
        return;
      case JavaPackage.JMETHOD__BODY:
        setBody(BODY_EDEFAULT);
        return;
      case JavaPackage.JMETHOD__PARAMETERS:
        getParameters().clear();
        return;
      case JavaPackage.JMETHOD__EXCEPTIONS:
        getExceptions().clear();
        return;
      case JavaPackage.JMETHOD__RETURN_TYPE:
        setReturnType((JClass)null);
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
      case JavaPackage.JMETHOD__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case JavaPackage.JMETHOD__JNODE:
        return JNODE_EDEFAULT == null ? jNode != null : !JNODE_EDEFAULT.equals(jNode);
      case JavaPackage.JMETHOD__STATIC:
        return static_ != STATIC_EDEFAULT;
      case JavaPackage.JMETHOD__VISIBILITY:
        return visibility != VISIBILITY_EDEFAULT;
      case JavaPackage.JMETHOD__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case JavaPackage.JMETHOD__CONTAINING_TYPE:
        return getContainingType() != null;
      case JavaPackage.JMETHOD__ABSTRACT:
        return abstract_ != ABSTRACT_EDEFAULT;
      case JavaPackage.JMETHOD__FINAL:
        return final_ != FINAL_EDEFAULT;
      case JavaPackage.JMETHOD__NATIVE:
        return native_ != NATIVE_EDEFAULT;
      case JavaPackage.JMETHOD__SYNCHRONIZED:
        return synchronized_ != SYNCHRONIZED_EDEFAULT;
      case JavaPackage.JMETHOD__JAVA_METHOD:
        return JAVA_METHOD_EDEFAULT == null ? javaMethod != null : !JAVA_METHOD_EDEFAULT.equals(javaMethod);
      case JavaPackage.JMETHOD__CONSTRUCTOR:
        return constructor != CONSTRUCTOR_EDEFAULT;
      case JavaPackage.JMETHOD__JAVA_CONSTRUCTOR:
        return JAVA_CONSTRUCTOR_EDEFAULT == null ? javaConstructor != null : !JAVA_CONSTRUCTOR_EDEFAULT.equals(javaConstructor);
      case JavaPackage.JMETHOD__BODY:
        return BODY_EDEFAULT == null ? body != null : !BODY_EDEFAULT.equals(body);
      case JavaPackage.JMETHOD__PARAMETERS:
        return parameters != null && !parameters.isEmpty();
      case JavaPackage.JMETHOD__EXCEPTIONS:
        return exceptions != null && !exceptions.isEmpty();
      case JavaPackage.JMETHOD__RETURN_TYPE:
        return returnType != null;
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
    result.append(", native: ");
    result.append(native_);
    result.append(", synchronized: ");
    result.append(synchronized_);
    result.append(", javaMethod: ");
    result.append(javaMethod);
    result.append(", constructor: ");
    result.append(constructor);
    result.append(", javaConstructor: ");
    result.append(javaConstructor);
    result.append(", body: ");
    result.append(body);
    result.append(')');
    return result.toString();
  }

  protected void changeAttribute(Notification notification)
  {
    switch (notification.getFeatureID(JMethod.class))
    {
      case JavaPackage.JMETHOD__JNODE:
      {
          JDOMHelper.handleJNode(this);

        break;
      }
      case JavaPackage.JMETHOD__JAVA_METHOD:
      {
        Method theJavaMethod = getJavaMethod();
        if (theJavaMethod != null)
        {
          setName(theJavaMethod.getName());

          Collection theParameters = new ArrayList();
          Class [] parameterTypes = theJavaMethod.getParameterTypes();
          for (int i = 0; i < parameterTypes.length; ++i)
          {
            Class parameterType = parameterTypes[i];
            JParameter jParameter = JavaFactory.eINSTANCE.createJParameter();
            jParameter.setType(JavaUtil.createJClassProxy(parameterType));
            theParameters.add(jParameter);
          }
          getParameters().addAll(theParameters);
  
          Collection theExceptions = new ArrayList();
          Class [] exceptionTypes = theJavaMethod.getExceptionTypes();
          for (int i = 0; i < exceptionTypes.length; ++i)
          {
            Class exceptionType = exceptionTypes[i];
            theExceptions.add(JavaUtil.createJClassProxy(exceptionType));
          }
          getExceptions().addAll(theExceptions);
  
          setReturnType(JavaUtil.createJClassProxy(theJavaMethod.getReturnType()));

          int modifiers = theJavaMethod.getModifiers();
          setFinal(Modifier.isInterface(modifiers));
          setAbstract(Modifier.isAbstract(modifiers));
          setStatic(Modifier.isStatic(modifiers));
          setVisibility(JavaUtil.getModifierVisibility(modifiers));
          setNative(Modifier.isNative(modifiers));
          setSynchronized(Modifier.isSynchronized(modifiers));
        }

        break;
      }
      case JavaPackage.JMETHOD__JAVA_CONSTRUCTOR:
      {
        Constructor theJavaConstructor = getJavaConstructor();
        if (theJavaConstructor != null)
        {
          String uri = JavaUtil.createJClassProxyURI(theJavaConstructor.getDeclaringClass()).fragment();
          setName(uri.substring(uri.lastIndexOf("/") + 1));
          setConstructor(true);
  
          Collection theParameters = new ArrayList();
          Class [] parameterTypes = theJavaConstructor.getParameterTypes();
          for (int i = 0; i < parameterTypes.length; ++i)
          {
            Class parameterType = parameterTypes[i];
            JParameter jParameter = JavaFactory.eINSTANCE.createJParameter();
            jParameter.setType(JavaUtil.createJClassProxy(parameterType));
            theParameters.add(jParameter);
          }
          getParameters().addAll(theParameters);
  
          Collection theExceptions = new ArrayList();
          Class [] exceptionTypes = theJavaConstructor.getExceptionTypes();
          for (int i = 0; i < exceptionTypes.length; ++i)
          {
            Class exceptionType = exceptionTypes[i];
            theExceptions.add(JavaUtil.createJClassProxy(exceptionType));
          }
          getExceptions().addAll(theExceptions);
  
          setReturnType(JavaUtil.createJClassProxy(theJavaConstructor.getDeclaringClass()));

          int modifiers = theJavaConstructor.getModifiers();
          setFinal(Modifier.isInterface(modifiers));
          setAbstract(Modifier.isAbstract(modifiers));
          setStatic(Modifier.isStatic(modifiers));
          setVisibility(JavaUtil.getModifierVisibility(modifiers));
          setNative(Modifier.isNative(modifiers));
          setSynchronized(Modifier.isSynchronized(modifiers));
        }

        break;
      }
    }
  }

  protected static class JDOMHelper
  {
    protected static void handleJNode(JMethod jMethod)
    {
      IDOMMethod iDOMMethod = (IDOMMethod)jMethod.getJNode();
      if (iDOMMethod != null)
      {
        if (iDOMMethod.getName() == null && iDOMMethod.getParent() instanceof IDOMType)
        {
          jMethod.setName(((IDOMType)iDOMMethod.getParent()).getName());
          jMethod.setConstructor(true);
        }
        else
        {
          jMethod.setName(iDOMMethod.getName());
          jMethod.setConstructor(false);
        }

        jMethod.setComment(iDOMMethod.getComment());
        jMethod.setBody(iDOMMethod.getBody());

        int flags = iDOMMethod.getFlags();
        jMethod.setFinal((flags & Flags.AccFinal) != 0);
        jMethod.setAbstract((flags & Flags.AccAbstract) != 0);
        jMethod.setStatic((flags & Flags.AccStatic) != 0);
        jMethod.setVisibility(JavaUtil.getFlagVisibility(flags));
        jMethod.setNative((flags & Flags.AccNative) != 0);
        jMethod.setSynchronized((flags & Flags.AccSynchronized) != 0);

        Collection theParameters = jMethod.getParameters();
        String [] parameterNames = iDOMMethod.getParameterNames();
        if (parameterNames != null)
        {
          for (int i = 0; i < parameterNames.length; ++i)
          {
            JParameter jParameter = JavaFactory.eINSTANCE.createJParameter();
            jParameter.setName(parameterNames[i]);
            theParameters.add(jParameter);
          }
        }
      }
    }
  }

  protected void resolveIdentifiers()
  {
    if (getJNode() != null)
    {
      IDOMMethod iDOMMethod = (IDOMMethod)getJNode();
      String returnType = iDOMMethod.getReturnType();
      if (returnType != null)
      {
        setReturnType(getContainingType().resolveJClass(iDOMMethod.getReturnType()));
      }

      EList theParameters = getParameters();
      String [] parameterTypes = iDOMMethod.getParameterTypes();
      if (parameterTypes != null)
      {
        for (int i = 0; i < parameterTypes.length; ++i)
        {
          JParameter jParameter = (JParameter)theParameters.get(i);
          jParameter.setType(getContainingType().resolveJClass(parameterTypes[i]));
        }
      }
    }
  }
} //JMethodImpl
