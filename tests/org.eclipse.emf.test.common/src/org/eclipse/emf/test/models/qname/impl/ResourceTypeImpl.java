/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.models.qname.impl;

import java.util.List;

import javax.xml.namespace.QName;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.qname.QNamePackage;
import org.eclipse.emf.test.models.qname.ResourceType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.ResourceTypeImpl#getUnionvalue <em>Unionvalue</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.ResourceTypeImpl#getQnamelist <em>Qnamelist</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.ResourceTypeImpl#getIntvalue <em>Intvalue</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.ResourceTypeImpl#getAny <em>Any</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.ResourceTypeImpl#getMyQname <em>My Qname</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.ResourceTypeImpl#getAnyAttribute <em>Any Attribute</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceTypeImpl extends EObjectImpl implements ResourceType
{
  /**
   * The default value of the '{@link #getUnionvalue() <em>Unionvalue</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUnionvalue()
   * @generated
   * @ordered
   */
  protected static final Object UNIONVALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getUnionvalue() <em>Unionvalue</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUnionvalue()
   * @generated
   * @ordered
   */
  protected Object unionvalue = UNIONVALUE_EDEFAULT;

  /**
   * The default value of the '{@link #getQnamelist() <em>Qnamelist</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQnamelist()
   * @generated
   * @ordered
   */
  protected static final List<QName> QNAMELIST_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getQnamelist() <em>Qnamelist</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQnamelist()
   * @generated
   * @ordered
   */
  protected List<QName> qnamelist = QNAMELIST_EDEFAULT;

  /**
   * The default value of the '{@link #getIntvalue() <em>Intvalue</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIntvalue()
   * @generated
   * @ordered
   */
  protected static final int INTVALUE_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getIntvalue() <em>Intvalue</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIntvalue()
   * @generated
   * @ordered
   */
  protected int intvalue = INTVALUE_EDEFAULT;

  /**
   * This is true if the Intvalue attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean intvalueESet;

  /**
   * The cached value of the '{@link #getAny() <em>Any</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAny()
   * @generated
   * @ordered
   */
  protected FeatureMap any;

  /**
   * The default value of the '{@link #getMyQname() <em>My Qname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMyQname()
   * @generated
   * @ordered
   */
  protected static final List<QName> MY_QNAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMyQname() <em>My Qname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMyQname()
   * @generated
   * @ordered
   */
  protected List<QName> myQname = MY_QNAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getAnyAttribute() <em>Any Attribute</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnyAttribute()
   * @generated
   * @ordered
   */
  protected FeatureMap anyAttribute;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ResourceTypeImpl()
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
    return QNamePackage.Literals.RESOURCE_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getUnionvalue()
  {
    return unionvalue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUnionvalue(Object newUnionvalue)
  {
    Object oldUnionvalue = unionvalue;
    unionvalue = newUnionvalue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, QNamePackage.RESOURCE_TYPE__UNIONVALUE, oldUnionvalue, unionvalue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List<QName> getQnamelist()
  {
    return qnamelist;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setQnamelist(List<QName> newQnamelist)
  {
    List<QName> oldQnamelist = qnamelist;
    qnamelist = newQnamelist;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, QNamePackage.RESOURCE_TYPE__QNAMELIST, oldQnamelist, qnamelist));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getIntvalue()
  {
    return intvalue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIntvalue(int newIntvalue)
  {
    int oldIntvalue = intvalue;
    intvalue = newIntvalue;
    boolean oldIntvalueESet = intvalueESet;
    intvalueESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, QNamePackage.RESOURCE_TYPE__INTVALUE, oldIntvalue, intvalue, !oldIntvalueESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetIntvalue()
  {
    int oldIntvalue = intvalue;
    boolean oldIntvalueESet = intvalueESet;
    intvalue = INTVALUE_EDEFAULT;
    intvalueESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, QNamePackage.RESOURCE_TYPE__INTVALUE, oldIntvalue, INTVALUE_EDEFAULT, oldIntvalueESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetIntvalue()
  {
    return intvalueESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureMap getAny()
  {
    if (any == null)
    {
      any = new BasicFeatureMap(this, QNamePackage.RESOURCE_TYPE__ANY);
    }
    return any;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List<QName> getMyQname()
  {
    return myQname;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMyQname(List<QName> newMyQname)
  {
    List<QName> oldMyQname = myQname;
    myQname = newMyQname;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, QNamePackage.RESOURCE_TYPE__MY_QNAME, oldMyQname, myQname));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureMap getAnyAttribute()
  {
    if (anyAttribute == null)
    {
      anyAttribute = new BasicFeatureMap(this, QNamePackage.RESOURCE_TYPE__ANY_ATTRIBUTE);
    }
    return anyAttribute;
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
      case QNamePackage.RESOURCE_TYPE__ANY:
        return ((InternalEList<?>)getAny()).basicRemove(otherEnd, msgs);
      case QNamePackage.RESOURCE_TYPE__ANY_ATTRIBUTE:
        return ((InternalEList<?>)getAnyAttribute()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case QNamePackage.RESOURCE_TYPE__UNIONVALUE:
        return getUnionvalue();
      case QNamePackage.RESOURCE_TYPE__QNAMELIST:
        return getQnamelist();
      case QNamePackage.RESOURCE_TYPE__INTVALUE:
        return getIntvalue();
      case QNamePackage.RESOURCE_TYPE__ANY:
        if (coreType) return getAny();
        return ((FeatureMap.Internal)getAny()).getWrapper();
      case QNamePackage.RESOURCE_TYPE__MY_QNAME:
        return getMyQname();
      case QNamePackage.RESOURCE_TYPE__ANY_ATTRIBUTE:
        if (coreType) return getAnyAttribute();
        return ((FeatureMap.Internal)getAnyAttribute()).getWrapper();
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
      case QNamePackage.RESOURCE_TYPE__UNIONVALUE:
        setUnionvalue(newValue);
        return;
      case QNamePackage.RESOURCE_TYPE__QNAMELIST:
        setQnamelist((List<QName>)newValue);
        return;
      case QNamePackage.RESOURCE_TYPE__INTVALUE:
        setIntvalue((Integer)newValue);
        return;
      case QNamePackage.RESOURCE_TYPE__ANY:
        ((FeatureMap.Internal)getAny()).set(newValue);
        return;
      case QNamePackage.RESOURCE_TYPE__MY_QNAME:
        setMyQname((List<QName>)newValue);
        return;
      case QNamePackage.RESOURCE_TYPE__ANY_ATTRIBUTE:
        ((FeatureMap.Internal)getAnyAttribute()).set(newValue);
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
      case QNamePackage.RESOURCE_TYPE__UNIONVALUE:
        setUnionvalue(UNIONVALUE_EDEFAULT);
        return;
      case QNamePackage.RESOURCE_TYPE__QNAMELIST:
        setQnamelist(QNAMELIST_EDEFAULT);
        return;
      case QNamePackage.RESOURCE_TYPE__INTVALUE:
        unsetIntvalue();
        return;
      case QNamePackage.RESOURCE_TYPE__ANY:
        getAny().clear();
        return;
      case QNamePackage.RESOURCE_TYPE__MY_QNAME:
        setMyQname(MY_QNAME_EDEFAULT);
        return;
      case QNamePackage.RESOURCE_TYPE__ANY_ATTRIBUTE:
        getAnyAttribute().clear();
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
      case QNamePackage.RESOURCE_TYPE__UNIONVALUE:
        return UNIONVALUE_EDEFAULT == null ? unionvalue != null : !UNIONVALUE_EDEFAULT.equals(unionvalue);
      case QNamePackage.RESOURCE_TYPE__QNAMELIST:
        return QNAMELIST_EDEFAULT == null ? qnamelist != null : !QNAMELIST_EDEFAULT.equals(qnamelist);
      case QNamePackage.RESOURCE_TYPE__INTVALUE:
        return isSetIntvalue();
      case QNamePackage.RESOURCE_TYPE__ANY:
        return any != null && !any.isEmpty();
      case QNamePackage.RESOURCE_TYPE__MY_QNAME:
        return MY_QNAME_EDEFAULT == null ? myQname != null : !MY_QNAME_EDEFAULT.equals(myQname);
      case QNamePackage.RESOURCE_TYPE__ANY_ATTRIBUTE:
        return anyAttribute != null && !anyAttribute.isEmpty();
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
    result.append(" (unionvalue: ");
    result.append(unionvalue);
    result.append(", qnamelist: ");
    result.append(qnamelist);
    result.append(", intvalue: ");
    if (intvalueESet) result.append(intvalue); else result.append("<unset>");
    result.append(", any: ");
    result.append(any);
    result.append(", myQname: ");
    result.append(myQname);
    result.append(", anyAttribute: ");
    result.append(anyAttribute);
    result.append(')');
    return result.toString();
  }

} //ResourceTypeImpl
