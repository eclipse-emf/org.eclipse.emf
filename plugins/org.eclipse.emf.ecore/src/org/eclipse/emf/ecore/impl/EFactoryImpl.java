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
 * $Id: EFactoryImpl.java,v 1.7 2004/10/25 17:08:08 elena Exp $
 */
package org.eclipse.emf.ecore.impl;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EFactory</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.impl.EFactoryImpl#getEPackage <em>EPackage</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EFactoryImpl extends EModelElementImpl implements EFactory
{
  /**
   * The cached value of the '{@link #getEPackage() <em>EPackage</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEPackage()
   * @generated
   * @ordered
   */
  protected EPackage ePackage = null;

  protected EFactoryImpl()
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
    return EcorePackage.eINSTANCE.getEFactory();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EPackage getEPackage()
  {
    return ePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEPackage(EPackage newEPackage)
  {
    if (newEPackage != ePackage)
    {
      NotificationChain msgs = null;
      if (ePackage != null)
        msgs = ((InternalEObject)ePackage).eInverseRemove(this, EcorePackage.EPACKAGE__EFACTORY_INSTANCE, EPackage.class, msgs);
      if (newEPackage != null)
        msgs = ((InternalEObject)newEPackage).eInverseAdd(this, EcorePackage.EPACKAGE__EFACTORY_INSTANCE, EPackage.class, msgs);
      msgs = basicSetEPackage(newEPackage, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.EFACTORY__EPACKAGE, newEPackage, newEPackage));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEPackage(EPackage newEPackage, NotificationChain msgs)
  {
    EPackage oldEPackage = ePackage;
    ePackage = newEPackage;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EcorePackage.EFACTORY__EPACKAGE, oldEPackage, newEPackage);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
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
        case EcorePackage.EFACTORY__EANNOTATIONS:
          return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
        case EcorePackage.EFACTORY__EPACKAGE:
          if (ePackage != null)
            msgs = ((InternalEObject)ePackage).eInverseRemove(this, EcorePackage.EPACKAGE__EFACTORY_INSTANCE, EPackage.class, msgs);
          return basicSetEPackage((EPackage)otherEnd, msgs);
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
        case EcorePackage.EFACTORY__EANNOTATIONS:
          return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
        case EcorePackage.EFACTORY__EPACKAGE:
          return basicSetEPackage(null, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
  }

  /**
   * @generated modifiable
   */
  public EObject create(EClass eClass) 
  {
    if (getEPackage() != eClass.getEPackage())
    {
      throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }

    for (List eSuperTypes = eClass.getESuperTypes(); !eSuperTypes.isEmpty(); )
    {
      EClass eSuperType = (EClass)eSuperTypes.get(0);
      if (eSuperType.getInstanceClass() != null)
      {
        EObject result = eSuperType.getEPackage().getEFactoryInstance().create(eSuperType);
        ((InternalEObject)result).eSetClass(eClass);
        return result;
      }
      eSuperTypes = eSuperType.getESuperTypes();
    }

    return basicCreate(eClass);
  }

  protected EObject basicCreate(EClass eClass) 
  {
    EObjectImpl result = new EObjectImpl();
    result.eSetClass(eClass);
    return result;
  }

  /**
   * @generated NOT
   */
  public Object createFromString(EDataType eDataType, String stringValue) 
  {
    if (stringValue == null)
    {
      return null;
    }

    if (getEPackage() != eDataType.getEPackage())
    {
      throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }

    if (eDataType instanceof EEnum)
    {
      return ((EEnum)eDataType).getEEnumLiteral(stringValue);
    }

    switch (ExtendedMetaData.INSTANCE.getWhiteSpaceFacet(eDataType))
    {
      case ExtendedMetaData.REPLACE_WHITE_SPACE:
      {
        stringValue = replaceWhiteSpace(stringValue);
        break;
      }
      case ExtendedMetaData.COLLAPSE_WHITE_SPACE:
      {
        stringValue = collapseWhiteSpace(stringValue);
        break;
      }
    }
    
    EDataType baseType = ExtendedMetaData.INSTANCE.getBaseType(eDataType);
    if (baseType != null)
    {
      return EcoreUtil.createFromString(baseType, stringValue);
    }

    EDataType itemType = ExtendedMetaData.INSTANCE.getItemType(eDataType);
    if (itemType != null)
    {
      List result = new ArrayList();
      for (StringTokenizer stringTokenizer = new StringTokenizer(stringValue); stringTokenizer.hasMoreTokens(); )
      {
        String item = stringTokenizer.nextToken();
        result.add(EcoreUtil.createFromString(itemType, item));
      }
      return result;
    }

    List memberTypes = ExtendedMetaData.INSTANCE.getMemberTypes(eDataType);
    if (!memberTypes.isEmpty())
    {
      for (Iterator i = memberTypes.iterator(); i.hasNext(); )
      {
        EDataType memberType = (EDataType)i.next();
        try
        {
          Object result = EcoreUtil.createFromString(memberType, stringValue);
          if (result != null)
          {
            return result;
          }
        }
        catch (RuntimeException exception)
        {
        }
      }
      throw new IllegalArgumentException("The value '"+stringValue+"' does not match any member types of the union datatype '" + eDataType.getName() + "'");
    }

    Class c = EcoreUtil.wrapperClassFor(eDataType.getInstanceClass());
    if (c == null) return null;

    if (c == Character.class)
    {
      char charValue = 0;
      try
      {
        Integer value = new Integer(stringValue);
        charValue = (char) value.intValue();
      }
      catch (NumberFormatException e)
      {
        char[] carray = stringValue.toCharArray();
        charValue = carray[0];
      }

      return new Character(charValue);
    }

    if (c == Date.class)
    {
      Exception exception = null;
      for (int i = 0; i < EDATE_FORMATS.length; ++i)
      {
        try
        {
          return EDATE_FORMATS[i].parse(stringValue);
        }
        catch (ParseException parseException)
        {
          exception = parseException;
        }
      }
      throw new IllegalArgumentException("The value '" + stringValue + "' is not a date formatted string of the form yyyy-MM-dd'T'HH:mm:ss'.'SSSZ or a valid subset thereof");
    }

    Class stringClass = String.class;
    Class[] signature = { stringClass };

    Constructor ctor = null;
    try
    {
      ctor = c.getConstructor(signature);
    }
    catch (NoSuchMethodException e)
    {
    }
    Exception formatException = null;
    try
    {
      if (ctor != null)
      {
        Object[] ctorArgs = {stringValue};
        return ctor.newInstance(ctorArgs);
      }
    }
    catch (InstantiationException e)
    {
      formatException = e;
    }
    catch (InvocationTargetException e)
    {
      formatException = e;
    }
    catch (IllegalAccessException e)
    {
      formatException = e;
    }
    
    Method valueOf = null;
    try
    {
      valueOf = c.getMethod("valueOf", signature);
    }
    catch (NoSuchMethodException e)
    {
    }

    try
    {
      if (valueOf != null)
      {
        Object[] valueOfArgs = {stringValue};
        return valueOf.invoke(null, valueOfArgs);
      }
    }
    catch (IllegalArgumentException e)
    {
      formatException = e;
    }
    catch (InvocationTargetException e)
    {
      formatException = e;
    }
    catch (IllegalAccessException e)
    {
      formatException = e;
    }
    String exceptionString = (formatException != null) ? formatException.toString() : "";
    throw new IllegalArgumentException("The value '" + stringValue + "' is invalid. " + exceptionString);
  }

  /**
   * @generated modifiable
   */
  public String convertToString(EDataType eDataType, Object objectValue)
  {
    if (getEPackage() != eDataType.getEPackage())
    {
      throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }

    EDataType baseType = ExtendedMetaData.INSTANCE.getBaseType(eDataType);
    if (baseType != null)
    {
      return EcoreUtil.convertToString(baseType, objectValue);
    }

    EDataType itemType = ExtendedMetaData.INSTANCE.getItemType(eDataType);
    if (itemType != null)
    {
      if (objectValue == null)
      {
        return null;
      }
      List list = (List)objectValue;
      if (list.isEmpty())
      {
        return "";
      }
      StringBuffer result = new StringBuffer();
      for (Iterator i = list.iterator(); i.hasNext(); )
      {
        Object item = i.next();
        result.append(EcoreUtil.convertToString(itemType, item));
        result.append(' ');
      }
      return result.substring(0, result.length() - 1);
    }

    List memberTypes = ExtendedMetaData.INSTANCE.getMemberTypes(eDataType);
    if (!memberTypes.isEmpty())
    {
      for (Iterator i = memberTypes.iterator(); i.hasNext(); )
      {
        EDataType memberType = (EDataType)i.next();
        if (memberType.isInstance(objectValue))
        {
          return EcoreUtil.convertToString(memberType, objectValue);
        }
      }
      return null;
    }

    if (objectValue instanceof Character)
    {
      int charInt = ((Character) objectValue).charValue();
      Integer value = new Integer(charInt);
      return value.toString();
    }
    else if (objectValue instanceof Date)
    {
      return EDATE_FORMATS[0].format((Date)objectValue);
    }
    else if (objectValue != null)
    {
      return objectValue.toString();
    }
    else
    {
      return null;
    }
  }

  protected String replaceWhiteSpace(String value)
  {
    return XMLTypeUtil.normalize(value, false);
  }

  protected String collapseWhiteSpace(String value)
  {
    return XMLTypeUtil.normalize(value, true);
  }

  protected static final DateFormat [] EDATE_FORMATS = 
  {
    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSSZ"),
    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSS"),
    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),
    new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"),
    new SimpleDateFormat("yyyy-MM-dd")
  };

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case EcorePackage.EFACTORY__EANNOTATIONS:
        return getEAnnotations();
      case EcorePackage.EFACTORY__EPACKAGE:
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
      case EcorePackage.EFACTORY__EANNOTATIONS:
        return eAnnotations != null && !eAnnotations.isEmpty();
      case EcorePackage.EFACTORY__EPACKAGE:
        return ePackage != null;
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
      case EcorePackage.EFACTORY__EANNOTATIONS:
        getEAnnotations().clear();
        getEAnnotations().addAll((Collection)newValue);
        return;
      case EcorePackage.EFACTORY__EPACKAGE:
        setEPackage((EPackage)newValue);
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
      case EcorePackage.EFACTORY__EANNOTATIONS:
        getEAnnotations().clear();
        return;
      case EcorePackage.EFACTORY__EPACKAGE:
        setEPackage((EPackage)null);
        return;
    }
    eDynamicUnset(eFeature);
  }

}
