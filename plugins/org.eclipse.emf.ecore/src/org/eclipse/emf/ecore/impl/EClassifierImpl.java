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
 * $Id: EClassifierImpl.java,v 1.3 2004/05/28 19:32:38 emerks Exp $
 */
package org.eclipse.emf.ecore.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EMeta Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.impl.EClassifierImpl#getInstanceClassName <em>Instance Class Name</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EClassifierImpl#getInstanceClass <em>Instance Class</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EClassifierImpl#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EClassifierImpl#getEPackage <em>EPackage</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class EClassifierImpl extends ENamedElementImpl implements EClassifier
{
  protected int metaObjectID = -1;

  protected EClassifierImpl()
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
    return EcorePackage.eINSTANCE.getEClassifier();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @modifiable
   */
  public int getClassifierID()
  {
    return metaObjectID;
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
        case EcorePackage.ECLASSIFIER__EANNOTATIONS:
          return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
        case EcorePackage.ECLASSIFIER__EPACKAGE:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, EcorePackage.ECLASSIFIER__EPACKAGE, msgs);
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
        case EcorePackage.ECLASSIFIER__EANNOTATIONS:
          return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
        case EcorePackage.ECLASSIFIER__EPACKAGE:
          return eBasicSetContainer(null, EcorePackage.ECLASSIFIER__EPACKAGE, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
  }

  public void setClassifierID(int id)
  {
    metaObjectID = id;
  }

  /**
   * Returns whether the object is an instance of this classifier.
   * @param object the object in question.
   * @return whether the object is an instance.
   * @see Class#isInstance
   */
  public boolean isInstance(Object object)
  {
    if (object != null)
    {
      Class instanceClass = getInstanceClass();
      if (instanceClass != null)
      {
        if (instanceClass.isPrimitive())
        {
          if (instanceClass == Boolean.TYPE)
          {
            return object instanceof Boolean;
          }
          else if (instanceClass == Integer.TYPE)
          {
            return object instanceof Integer;
          }
          else if (instanceClass == Float.TYPE)
          {
            return object instanceof Float;
          }
          else if (instanceClass == Byte.TYPE)
          {
            return object instanceof Byte;
          }
          else if (instanceClass == Character.TYPE)
          {
            return object instanceof Character;
          }
          else if (instanceClass == Double.TYPE)
          {
            return object instanceof Double;
          }
          else if (instanceClass == Short.TYPE)
          {
            return object instanceof Short;
          }
          else if (instanceClass == Long.TYPE)
          {
            return object instanceof Long;
          }
        }
        else
        {
          return instanceClass.isInstance(object);
        }
      }
      else if (object instanceof EObject)
      {
        return dynamicIsInstance((EObject)object);
      }
    }

    return false;
  }

  protected boolean dynamicIsInstance(EObject eObject)
  {
    return eObject.eClass() == this;
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
      case EcorePackage.ECLASSIFIER__EANNOTATIONS:
        return getEAnnotations();
      case EcorePackage.ECLASSIFIER__NAME:
        return getName();
      case EcorePackage.ECLASSIFIER__INSTANCE_CLASS_NAME:
        return getInstanceClassName();
      case EcorePackage.ECLASSIFIER__INSTANCE_CLASS:
        return getInstanceClass();
      case EcorePackage.ECLASSIFIER__DEFAULT_VALUE:
        return getDefaultValue();
      case EcorePackage.ECLASSIFIER__EPACKAGE:
        return getEPackage();
    }
    return eDynamicGet(eFeature, resolve);
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
      case EcorePackage.ECLASSIFIER__EANNOTATIONS:
        return eAnnotations != null && !eAnnotations.isEmpty();
      case EcorePackage.ECLASSIFIER__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EcorePackage.ECLASSIFIER__INSTANCE_CLASS_NAME:
        return INSTANCE_CLASS_NAME_EDEFAULT == null ? instanceClassName != null : !INSTANCE_CLASS_NAME_EDEFAULT.equals(instanceClassName);
      case EcorePackage.ECLASSIFIER__INSTANCE_CLASS:
        return getInstanceClass() != null;
      case EcorePackage.ECLASSIFIER__DEFAULT_VALUE:
        return getDefaultValue() != null;
      case EcorePackage.ECLASSIFIER__EPACKAGE:
        return getEPackage() != null;
    }
    return eDynamicIsSet(eFeature);
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
      case EcorePackage.ECLASSIFIER__EANNOTATIONS:
        getEAnnotations().clear();
        getEAnnotations().addAll((Collection)newValue);
        return;
      case EcorePackage.ECLASSIFIER__NAME:
        setName((String)newValue);
        return;
      case EcorePackage.ECLASSIFIER__INSTANCE_CLASS_NAME:
        setInstanceClassName((String)newValue);
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
      case EcorePackage.ECLASSIFIER__EANNOTATIONS:
        getEAnnotations().clear();
        return;
      case EcorePackage.ECLASSIFIER__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EcorePackage.ECLASSIFIER__INSTANCE_CLASS_NAME:
        setInstanceClassName(INSTANCE_CLASS_NAME_EDEFAULT);
        return;
    }
    eDynamicUnset(eFeature);
  }

  /**
   * The default value of the '{@link #getInstanceClassName() <em>Instance Class Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInstanceClassName()
   * @generated
   * @ordered
   */
  protected static final String INSTANCE_CLASS_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getInstanceClassName() <em>Instance Class Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInstanceClassName()
   * @generated
   * @ordered
   */
  protected String instanceClassName = INSTANCE_CLASS_NAME_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getInstanceClassNameGen()
  {
    return instanceClassName;
  }

  public String getInstanceClassName()
  {
    return getInstanceClassNameGen() != null ? getInstanceClassNameGen() : generatedInstanceClassName;
  }

  protected String generatedInstanceClassName;

  public void setGeneratedInstanceClass(boolean isGenerated)
  {
    if (isGenerated)
    {
      if (generatedInstanceClassName == null)
      {
        generatedInstanceClassName = instanceClassName;
        instanceClassName = null;
      }
    }
    else if (generatedInstanceClassName != null)
    {
      instanceClassName = generatedInstanceClassName;
      generatedInstanceClassName = null;
    }
  }

  public void setInstanceClassName(String value)
  {
    if (instanceClassName == null && generatedInstanceClassName != null)
    {
      instanceClassName = generatedInstanceClassName;
      generatedInstanceClassName = null;
    }
    setInstanceClassNameGen(value == null ? null : value.intern());
    if (instanceClass != null)
    {
      setInstanceClassGen(null);
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInstanceClassNameGen(String newInstanceClassName)
  {
    String oldInstanceClassName = instanceClassName;
    instanceClassName = newInstanceClassName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.ECLASSIFIER__INSTANCE_CLASS_NAME, oldInstanceClassName, instanceClassName));
  }

  /**
   * The default value of the '{@link #getInstanceClass() <em>Instance Class</em>}' attribute.
   * @see #getInstanceClass()
   */
  protected static final Class INSTANCE_CLASS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getInstanceClass() <em>Instance Class</em>}' attribute.
   * @see #getInstanceClass()
   */
  protected Class instanceClass = INSTANCE_CLASS_EDEFAULT;

  public Class getInstanceClass() 
  {
    if (instanceClass == null && (instanceClassName != null || generatedInstanceClassName != null))
    {
      try
      {
        setInstanceClassGen(getClassForName(getInstanceClassName()));
      }
      catch (ClassNotFoundException e)
      {
        Class primitiveClass = getPrimitiveOrArrayClass();
        if (primitiveClass != null)
          setInstanceClassGen(primitiveClass);
        else
          throw new WrappedException(e);
      }
    }
    return getInstanceClassGen();
  }

  /**
   * Returns the <code>Class</code> object associated with the class or interface with the given name, as from a {@link
   * java.lang.Class#forName} call; however, if this classifier belongs to a package, that package's class loader is
   * used. Since the package may be model-specific code in another plug-in, its class loader may be able to see classes
   * that Ecore's can't.
   */
  protected Class getClassForName(String name) throws ClassNotFoundException
  {
    EPackage p = getEPackage();
    return p != null ? Class.forName(name, true, p.getClass().getClassLoader()) : Class.forName(name);
  }
  
  protected Class getPrimitiveOrArrayClass() 
  {
    String className = getInstanceClassName();
    int arrayIndex = className.indexOf('[');
    if (arrayIndex != -1)
    {
      String componentClassName = className.substring(0, arrayIndex);
      StringBuffer result = new StringBuffer();
      do result.append('['); while ((arrayIndex = className.indexOf('[', ++arrayIndex)) != -1);
      if (componentClassName.equals("boolean"))
        result.append('Z');
      else if (componentClassName.equals("byte"))
        result.append('B');
      else if (componentClassName.equals("char"))
        result.append('C');
      else if (componentClassName.equals("double"))
        result.append('D');
      else if (componentClassName.equals("float"))
        result.append('F');
      else if (componentClassName.equals("int"))
        result.append('I');
      else if (componentClassName.equals("long"))
        result.append('J');
      else if (componentClassName.equals("short"))
        result.append('S');
      else {
        result.append('L');
        result.append(componentClassName);
        result.append(';');
      }
      try
      {
        return getClassForName(result.toString());
      }
      catch (ClassNotFoundException e) {}
    }
    else
    {
      if (className.equals("boolean"))
        return java.lang.Boolean.TYPE;
      else if (className.equals("byte"))
        return java.lang.Byte.TYPE;
      else if (className.equals("char"))
        return java.lang.Character.TYPE;
      else if (className.equals("double"))
        return java.lang.Double.TYPE;
      else if (className.equals("float"))
        return java.lang.Float.TYPE;
      else if (className.equals("int"))
        return java.lang.Integer.TYPE;
      else if (className.equals("long"))
        return java.lang.Long.TYPE;
      else if (className.equals("short"))
        return java.lang.Short.TYPE;
    }
    return null;
  }

  /**
   */
  public Class getInstanceClassGen()
  {
    return instanceClass;
  }

  public void setInstanceClass(Class value)
  {
    if (value == null)
    {
      setInstanceClassNameGen(null);
    }
    else if (value.isArray())
    {
      String indices = "[]";
      for (Class component = value.getComponentType(); ; component = component.getComponentType())
      {
        if (!component.isArray())
        {
          setInstanceClassNameGen((component.getName() + indices).intern());
          break;
        }
        indices += "[]";
      }
    }
    else
    {
      setInstanceClassNameGen(value.getName().intern());
    }

    setInstanceClassGen(value);
  }

  /**
   */
  public void setInstanceClassGen(Class newInstanceClass)
  {
    Class oldInstanceClass = instanceClass;
    instanceClass = newInstanceClass;
  }

  /**
   */
  public Object getDefaultValue()
  {
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public EPackage getEPackage()
  {
    return (eContainerFeatureID == EcorePackage.ECLASSIFIER__EPACKAGE) ? (EPackage)eContainer : null;
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
    result.append(" (instanceClassName: ");
    result.append(instanceClassName);
    result.append(')');
    return result.toString();
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
        case EcorePackage.ECLASSIFIER__EPACKAGE:
          return ((InternalEObject)eContainer).eInverseRemove(this, EcorePackage.EPACKAGE__ECLASSIFIERS, EPackage.class, msgs);
        default:
          return eDynamicBasicRemoveFromContainer(msgs);
      }
    }
    return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
  }

}
