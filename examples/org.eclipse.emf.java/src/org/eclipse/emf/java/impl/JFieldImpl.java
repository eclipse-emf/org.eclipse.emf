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
 * $Id: JFieldImpl.java,v 1.5 2005/11/23 13:57:05 emerks Exp $
 */
package org.eclipse.emf.java.impl;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.eclipse.jdt.core.Flags;
import org.eclipse.jdt.core.jdom.IDOMField;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.java.JClass;
import org.eclipse.emf.java.JField;
import org.eclipse.emf.java.JVisibility;
import org.eclipse.emf.java.JavaPackage;
import org.eclipse.emf.java.util.JavaUtil;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>JField</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.java.impl.JFieldImpl#isFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JFieldImpl#isTransient <em>Transient</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JFieldImpl#isVolatile <em>Volatile</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JFieldImpl#getJavaField <em>Java Field</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JFieldImpl#getInitializer <em>Initializer</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JFieldImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JFieldImpl extends JMemberImpl implements JField
{
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
   * The default value of the '{@link #isTransient() <em>Transient</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTransient()
   * @generated
   * @ordered
   */
  protected static final boolean TRANSIENT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isTransient() <em>Transient</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isTransient()
   * @generated
   * @ordered
   */
  protected boolean transient_ = TRANSIENT_EDEFAULT;

  /**
   * The default value of the '{@link #isVolatile() <em>Volatile</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isVolatile()
   * @generated
   * @ordered
   */
  protected static final boolean VOLATILE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isVolatile() <em>Volatile</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isVolatile()
   * @generated
   * @ordered
   */
  protected boolean volatile_ = VOLATILE_EDEFAULT;

  /**
   * The default value of the '{@link #getJavaField() <em>Java Field</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJavaField()
   * @generated
   * @ordered
   */
  protected static final Field JAVA_FIELD_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getJavaField() <em>Java Field</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJavaField()
   * @generated
   * @ordered
   */
  protected Field javaField = JAVA_FIELD_EDEFAULT;

  /**
   * The default value of the '{@link #getInitializer() <em>Initializer</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInitializer()
   * @generated
   * @ordered
   */
  protected static final String INITIALIZER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getInitializer() <em>Initializer</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInitializer()
   * @generated
   * @ordered
   */
  protected String initializer = INITIALIZER_EDEFAULT;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected JClass type = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected JFieldImpl()
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
    return JavaPackage.eINSTANCE.getJField();
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
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JFIELD__FINAL, oldFinal, final_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isTransient()
  {
    return transient_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTransient(boolean newTransient)
  {
    boolean oldTransient = transient_;
    transient_ = newTransient;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JFIELD__TRANSIENT, oldTransient, transient_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isVolatile()
  {
    return volatile_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVolatile(boolean newVolatile)
  {
    boolean oldVolatile = volatile_;
    volatile_ = newVolatile;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JFIELD__VOLATILE, oldVolatile, volatile_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Field getJavaField()
  {
    return javaField;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setJavaField(Field newJavaField)
  {
    Field oldJavaField = javaField;
    javaField = newJavaField;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JFIELD__JAVA_FIELD, oldJavaField, javaField));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getInitializer()
  {
    return initializer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInitializer(String newInitializer)
  {
    String oldInitializer = initializer;
    initializer = newInitializer;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JFIELD__INITIALIZER, oldInitializer, initializer));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JClass getType()
  {
    if (type != null && type.eIsProxy())
    {
      InternalEObject oldType = (InternalEObject)type;
      type = (JClass)eResolveProxy(oldType);
      if (type != oldType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, JavaPackage.JFIELD__TYPE, oldType, type));
      }
    }
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JClass basicGetType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(JClass newType)
  {
    JClass oldType = type;
    type = newType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JFIELD__TYPE, oldType, type));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case JavaPackage.JFIELD__NAME:
        return getName();
      case JavaPackage.JFIELD__JNODE:
        return getJNode();
      case JavaPackage.JFIELD__STATIC:
        return isStatic() ? Boolean.TRUE : Boolean.FALSE;
      case JavaPackage.JFIELD__VISIBILITY:
        return getVisibility();
      case JavaPackage.JFIELD__COMMENT:
        return getComment();
      case JavaPackage.JFIELD__CONTAINING_TYPE:
        return getContainingType();
      case JavaPackage.JFIELD__FINAL:
        return isFinal() ? Boolean.TRUE : Boolean.FALSE;
      case JavaPackage.JFIELD__TRANSIENT:
        return isTransient() ? Boolean.TRUE : Boolean.FALSE;
      case JavaPackage.JFIELD__VOLATILE:
        return isVolatile() ? Boolean.TRUE : Boolean.FALSE;
      case JavaPackage.JFIELD__JAVA_FIELD:
        return getJavaField();
      case JavaPackage.JFIELD__INITIALIZER:
        return getInitializer();
      case JavaPackage.JFIELD__TYPE:
        if (resolve) return getType();
        return basicGetType();
    }
    return eDynamicGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case JavaPackage.JFIELD__NAME:
        setName((String)newValue);
        return;
      case JavaPackage.JFIELD__JNODE:
        setJNode((Object)newValue);
        return;
      case JavaPackage.JFIELD__STATIC:
        setStatic(((Boolean)newValue).booleanValue());
        return;
      case JavaPackage.JFIELD__VISIBILITY:
        setVisibility((JVisibility)newValue);
        return;
      case JavaPackage.JFIELD__COMMENT:
        setComment((String)newValue);
        return;
      case JavaPackage.JFIELD__CONTAINING_TYPE:
        setContainingType((JClass)newValue);
        return;
      case JavaPackage.JFIELD__FINAL:
        setFinal(((Boolean)newValue).booleanValue());
        return;
      case JavaPackage.JFIELD__TRANSIENT:
        setTransient(((Boolean)newValue).booleanValue());
        return;
      case JavaPackage.JFIELD__VOLATILE:
        setVolatile(((Boolean)newValue).booleanValue());
        return;
      case JavaPackage.JFIELD__JAVA_FIELD:
        setJavaField((Field)newValue);
        return;
      case JavaPackage.JFIELD__INITIALIZER:
        setInitializer((String)newValue);
        return;
      case JavaPackage.JFIELD__TYPE:
        setType((JClass)newValue);
        return;
    }
    eDynamicSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case JavaPackage.JFIELD__NAME:
        setName(NAME_EDEFAULT);
        return;
      case JavaPackage.JFIELD__JNODE:
        setJNode(JNODE_EDEFAULT);
        return;
      case JavaPackage.JFIELD__STATIC:
        setStatic(STATIC_EDEFAULT);
        return;
      case JavaPackage.JFIELD__VISIBILITY:
        setVisibility(VISIBILITY_EDEFAULT);
        return;
      case JavaPackage.JFIELD__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case JavaPackage.JFIELD__CONTAINING_TYPE:
        setContainingType((JClass)null);
        return;
      case JavaPackage.JFIELD__FINAL:
        setFinal(FINAL_EDEFAULT);
        return;
      case JavaPackage.JFIELD__TRANSIENT:
        setTransient(TRANSIENT_EDEFAULT);
        return;
      case JavaPackage.JFIELD__VOLATILE:
        setVolatile(VOLATILE_EDEFAULT);
        return;
      case JavaPackage.JFIELD__JAVA_FIELD:
        setJavaField(JAVA_FIELD_EDEFAULT);
        return;
      case JavaPackage.JFIELD__INITIALIZER:
        setInitializer(INITIALIZER_EDEFAULT);
        return;
      case JavaPackage.JFIELD__TYPE:
        setType((JClass)null);
        return;
    }
    eDynamicUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case JavaPackage.JFIELD__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case JavaPackage.JFIELD__JNODE:
        return JNODE_EDEFAULT == null ? jNode != null : !JNODE_EDEFAULT.equals(jNode);
      case JavaPackage.JFIELD__STATIC:
        return static_ != STATIC_EDEFAULT;
      case JavaPackage.JFIELD__VISIBILITY:
        return visibility != VISIBILITY_EDEFAULT;
      case JavaPackage.JFIELD__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case JavaPackage.JFIELD__CONTAINING_TYPE:
        return getContainingType() != null;
      case JavaPackage.JFIELD__FINAL:
        return final_ != FINAL_EDEFAULT;
      case JavaPackage.JFIELD__TRANSIENT:
        return transient_ != TRANSIENT_EDEFAULT;
      case JavaPackage.JFIELD__VOLATILE:
        return volatile_ != VOLATILE_EDEFAULT;
      case JavaPackage.JFIELD__JAVA_FIELD:
        return JAVA_FIELD_EDEFAULT == null ? javaField != null : !JAVA_FIELD_EDEFAULT.equals(javaField);
      case JavaPackage.JFIELD__INITIALIZER:
        return INITIALIZER_EDEFAULT == null ? initializer != null : !INITIALIZER_EDEFAULT.equals(initializer);
      case JavaPackage.JFIELD__TYPE:
        return type != null;
    }
    return eDynamicIsSet(featureID);
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
    result.append(" (final: ");
    result.append(final_);
    result.append(", transient: ");
    result.append(transient_);
    result.append(", volatile: ");
    result.append(volatile_);
    result.append(", javaField: ");
    result.append(javaField);
    result.append(", initializer: ");
    result.append(initializer);
    result.append(')');
    return result.toString();
  }

  protected void changeAttribute(Notification notification)
  {
    switch (notification.getFeatureID(JField.class))
    {
      case JavaPackage.JFIELD__JNODE:
      {
        JDOMHelper.handleJNode(this);

        break;
      }
      case JavaPackage.JFIELD__JAVA_FIELD:
      {
        Field theJavaField = getJavaField();
        if (theJavaField != null)
        {
          setName(theJavaField.getName());
          setType(JavaUtil.createJClassProxy(theJavaField.getType()));

          int modifiers = theJavaField.getModifiers();
          setFinal(Modifier.isFinal(modifiers));
          setStatic(Modifier.isStatic(modifiers));
          setVisibility(JavaUtil.getModifierVisibility(modifiers));
          setTransient(Modifier.isTransient(modifiers));
          setVolatile(Modifier.isVolatile(modifiers));
        }
        break;
      }
    }
  }

  protected static class JDOMHelper
  {
    protected static void handleJNode(JField jField)
    {
      IDOMField iDOMField = (IDOMField)jField.getJNode();
      if (iDOMField != null)
      {
        jField.setName(iDOMField.getName());
        jField.setComment(iDOMField.getComment());
        jField.setInitializer(iDOMField.getInitializer());

        int flags = iDOMField.getFlags();
        jField.setFinal((flags & Flags.AccFinal) != 0);
        jField.setStatic((flags & Flags.AccStatic) != 0);
        jField.setVisibility(JavaUtil.getFlagVisibility(flags));
        jField.setTransient((flags & Flags.AccTransient) != 0);
        jField.setVolatile((flags & Flags.AccVolatile) != 0);
      }
    }
  }

  protected void resolveIdentifiers()
  {
    if (getJNode() != null)
    {
      IDOMField iDOMField = (IDOMField)getJNode();
      setType(getContainingType().resolveJClass(iDOMField.getType()));
    }
  }

} //JFieldImpl
