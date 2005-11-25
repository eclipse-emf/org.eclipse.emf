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
 * $Id: EFactoryImpl.java,v 1.17 2005/11/25 13:12:13 emerks Exp $
 */
package org.eclipse.emf.ecore.impl;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.FieldPosition;
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

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
    return EcorePackage.Literals.EFACTORY;
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
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case EcorePackage.EFACTORY__EANNOTATIONS:
        return getEAnnotations();
      case EcorePackage.EFACTORY__EPACKAGE:
        return getEPackage();
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
      case EcorePackage.EFACTORY__EANNOTATIONS:
        getEAnnotations().clear();
        getEAnnotations().addAll((Collection)newValue);
        return;
      case EcorePackage.EFACTORY__EPACKAGE:
        setEPackage((EPackage)newValue);
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
      case EcorePackage.EFACTORY__EANNOTATIONS:
        getEAnnotations().clear();
        return;
      case EcorePackage.EFACTORY__EPACKAGE:
        setEPackage((EPackage)null);
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
      case EcorePackage.EFACTORY__EANNOTATIONS:
        return eAnnotations != null && !eAnnotations.isEmpty();
      case EcorePackage.EFACTORY__EPACKAGE:
        return ePackage != null;
    }
    return eDynamicIsSet(featureID);
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
      return ((EEnum)eDataType).getEEnumLiteralByLiteral(stringValue);
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
      for (int i = 0; i < EDATE_FORMATS.length; ++i)
      {
        try
        {
          return EDATE_FORMATS[i].parse(stringValue);
        }
        catch (ParseException parseException)
        {
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
          try
          {
            String result = EcoreUtil.convertToString(memberType, objectValue);
            if (result != null)
            {
              return result;
            }
          }
          catch (Exception e)
          {
          }
        }
      }
      throw new IllegalArgumentException("Invalid value: '"+objectValue+"' for datatype :"+eDataType.getName());
    }

    if (objectValue == null)
    {
      return null;
    }
    else if (objectValue instanceof Character)
    {
      int charInt = ((Character) objectValue).charValue();
      Integer value = new Integer(charInt);
      return value.toString();
    }
    else if (objectValue.getClass() == Date.class)
    {
      return EDATE_FORMATS[0].format((Date)objectValue);
    }
    else
    {
      return objectValue.toString();
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case EcorePackage.EFACTORY__EANNOTATIONS:
        return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
      case EcorePackage.EFACTORY__EPACKAGE:
        if (ePackage != null)
          msgs = ((InternalEObject)ePackage).eInverseRemove(this, EcorePackage.EPACKAGE__EFACTORY_INSTANCE, EPackage.class, msgs);
        return basicSetEPackage((EPackage)otherEnd, msgs);
    }
    return eDynamicInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case EcorePackage.EFACTORY__EANNOTATIONS:
        return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
      case EcorePackage.EFACTORY__EPACKAGE:
        return basicSetEPackage(null, msgs);
    }
    return eDynamicInverseRemove(otherEnd, featureID, msgs);
  }

  protected String replaceWhiteSpace(String value)
  {
    return XMLTypeUtil.normalize(value, false);
  }

  protected String collapseWhiteSpace(String value)
  {
    return XMLTypeUtil.normalize(value, true);
  }

  private static class SafeSimpleDateFormat extends SimpleDateFormat
  {
    public SafeSimpleDateFormat(String pattern)
    {
      super(pattern);
    }
    
    public synchronized Date parse(String source) throws ParseException
    {
      return super.parse(source);
    }
    
    public synchronized StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition)
    {
      return super.format(date, toAppendTo, fieldPosition);
    }
  }
  
  protected static final DateFormat [] EDATE_FORMATS = 
  {
    new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSSZ"),
    new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSS"),
    new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),
    new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm"),
    new SafeSimpleDateFormat("yyyy-MM-dd")
  };

}
