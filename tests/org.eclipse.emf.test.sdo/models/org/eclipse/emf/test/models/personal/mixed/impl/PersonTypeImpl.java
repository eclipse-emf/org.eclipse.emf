/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: PersonTypeImpl.java,v 1.3 2006/12/30 03:44:07 marcelop Exp $
 */
package org.eclipse.emf.test.models.personal.mixed.impl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.test.models.personal.mixed.ContrType;
import org.eclipse.emf.test.models.personal.mixed.LinkType;
import org.eclipse.emf.test.models.personal.mixed.MixedPackage;
import org.eclipse.emf.test.models.personal.mixed.NameType;
import org.eclipse.emf.test.models.personal.mixed.PersonType;
import org.eclipse.emf.test.models.personal.mixed.UrlType;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;
import org.eclipse.emf.ecore.sdo.util.BasicESequence;
import org.eclipse.emf.ecore.sdo.util.ESequence;

import commonj.sdo.Sequence;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Person Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.PersonTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.PersonTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.PersonTypeImpl#getEmail <em>Email</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.PersonTypeImpl#getUrl <em>Url</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.PersonTypeImpl#getLink <em>Link</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.PersonTypeImpl#getAny <em>Any</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.PersonTypeImpl#getContr <em>Contr</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.PersonTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.PersonTypeImpl#getSalary <em>Salary</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PersonTypeImpl extends EDataObjectImpl implements PersonType
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final long serialVersionUID = 1L;

  /**
   * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMixed()
   * @generated
   * @ordered
   */
  protected ESequence mixed = null;

  /**
   * The default value of the '{@link #getContr() <em>Contr</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContr()
   * @generated
   * @ordered
   */
  protected static final ContrType CONTR_EDEFAULT = ContrType.FALSE_LITERAL;

  /**
   * The cached value of the '{@link #getContr() <em>Contr</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContr()
   * @generated
   * @ordered
   */
  protected ContrType contr = CONTR_EDEFAULT;

  /**
   * This is true if the Contr attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean contrESet = false;

  /**
   * The default value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected static final String ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected String id = ID_EDEFAULT;

  /**
   * The default value of the '{@link #getSalary() <em>Salary</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSalary()
   * @generated
   * @ordered
   */
  protected static final BigInteger SALARY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSalary() <em>Salary</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSalary()
   * @generated
   * @ordered
   */
  protected BigInteger salary = SALARY_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PersonTypeImpl()
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
    return MixedPackage.Literals.PERSON_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Sequence getMixed()
  {
    if (mixed == null)
    {
      mixed = new BasicESequence(new BasicFeatureMap(this, MixedPackage.PERSON_TYPE__MIXED));
    }
    return mixed;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NameType getName()
  {
    return (NameType)((FeatureMap.Internal.Wrapper)getMixed()).featureMap().get(MixedPackage.Literals.PERSON_TYPE__NAME, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetName(NameType newName, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)((FeatureMap.Internal.Wrapper)getMixed()).featureMap()).basicAdd(MixedPackage.Literals.PERSON_TYPE__NAME, newName, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(NameType newName)
  {
    ((FeatureMap.Internal)((FeatureMap.Internal.Wrapper)getMixed()).featureMap()).set(MixedPackage.Literals.PERSON_TYPE__NAME, newName);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List<String> getEmail()
  {
    return ((FeatureMap.Internal.Wrapper)getMixed()).featureMap().list(MixedPackage.Literals.PERSON_TYPE__EMAIL);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List<UrlType> getUrl()
  {
    return ((FeatureMap.Internal.Wrapper)getMixed()).featureMap().list(MixedPackage.Literals.PERSON_TYPE__URL);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LinkType getLink()
  {
    return (LinkType)((FeatureMap.Internal.Wrapper)getMixed()).featureMap().get(MixedPackage.Literals.PERSON_TYPE__LINK, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLink(LinkType newLink, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)((FeatureMap.Internal.Wrapper)getMixed()).featureMap()).basicAdd(MixedPackage.Literals.PERSON_TYPE__LINK, newLink, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLink(LinkType newLink)
  {
    ((FeatureMap.Internal)((FeatureMap.Internal.Wrapper)getMixed()).featureMap()).set(MixedPackage.Literals.PERSON_TYPE__LINK, newLink);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Sequence getAny()
  {
    return new BasicESequence((FeatureMap.Internal)((FeatureMap.Internal.Wrapper)getMixed()).featureMap().<FeatureMap.Entry>list(MixedPackage.Literals.PERSON_TYPE__ANY));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ContrType getContr()
  {
    return contr;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContr(ContrType newContr)
  {
    ContrType oldContr = contr;
    contr = newContr == null ? CONTR_EDEFAULT : newContr;
    boolean oldContrESet = contrESet;
    contrESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MixedPackage.PERSON_TYPE__CONTR, oldContr, contr, !oldContrESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetContr()
  {
    ContrType oldContr = contr;
    boolean oldContrESet = contrESet;
    contr = CONTR_EDEFAULT;
    contrESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, MixedPackage.PERSON_TYPE__CONTR, oldContr, CONTR_EDEFAULT, oldContrESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetContr()
  {
    return contrESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getId()
  {
    return id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setId(String newId)
  {
    String oldId = id;
    id = newId;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MixedPackage.PERSON_TYPE__ID, oldId, id));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigInteger getSalary()
  {
    return salary;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSalary(BigInteger newSalary)
  {
    BigInteger oldSalary = salary;
    salary = newSalary;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MixedPackage.PERSON_TYPE__SALARY, oldSalary, salary));
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
      case MixedPackage.PERSON_TYPE__MIXED:
        return ((InternalEList<?>)((FeatureMap.Internal.Wrapper)getMixed()).featureMap()).basicRemove(otherEnd, msgs);
      case MixedPackage.PERSON_TYPE__NAME:
        return basicSetName(null, msgs);
      case MixedPackage.PERSON_TYPE__URL:
        return ((InternalEList<?>)getUrl()).basicRemove(otherEnd, msgs);
      case MixedPackage.PERSON_TYPE__LINK:
        return basicSetLink(null, msgs);
      case MixedPackage.PERSON_TYPE__ANY:
        return ((InternalEList<?>)((FeatureMap.Internal.Wrapper)getAny()).featureMap()).basicRemove(otherEnd, msgs);
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
      case MixedPackage.PERSON_TYPE__MIXED:
        if (coreType) return ((FeatureMap.Internal.Wrapper)getMixed()).featureMap();
        return getMixed();
      case MixedPackage.PERSON_TYPE__NAME:
        return getName();
      case MixedPackage.PERSON_TYPE__EMAIL:
        return getEmail();
      case MixedPackage.PERSON_TYPE__URL:
        return getUrl();
      case MixedPackage.PERSON_TYPE__LINK:
        return getLink();
      case MixedPackage.PERSON_TYPE__ANY:
        if (coreType) return ((FeatureMap.Internal.Wrapper)getAny()).featureMap();
        return getAny();
      case MixedPackage.PERSON_TYPE__CONTR:
        return getContr();
      case MixedPackage.PERSON_TYPE__ID:
        return getId();
      case MixedPackage.PERSON_TYPE__SALARY:
        return getSalary();
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
      case MixedPackage.PERSON_TYPE__MIXED:
        ((FeatureMap.Internal)((FeatureMap.Internal.Wrapper)getMixed()).featureMap()).set(newValue);
        return;
      case MixedPackage.PERSON_TYPE__NAME:
        setName((NameType)newValue);
        return;
      case MixedPackage.PERSON_TYPE__EMAIL:
        getEmail().clear();
        getEmail().addAll((Collection<? extends String>)newValue);
        return;
      case MixedPackage.PERSON_TYPE__URL:
        getUrl().clear();
        getUrl().addAll((Collection<? extends UrlType>)newValue);
        return;
      case MixedPackage.PERSON_TYPE__LINK:
        setLink((LinkType)newValue);
        return;
      case MixedPackage.PERSON_TYPE__ANY:
        ((FeatureMap.Internal)((FeatureMap.Internal.Wrapper)getAny()).featureMap()).set(newValue);
        return;
      case MixedPackage.PERSON_TYPE__CONTR:
        setContr((ContrType)newValue);
        return;
      case MixedPackage.PERSON_TYPE__ID:
        setId((String)newValue);
        return;
      case MixedPackage.PERSON_TYPE__SALARY:
        setSalary((BigInteger)newValue);
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
      case MixedPackage.PERSON_TYPE__MIXED:
        ((FeatureMap.Internal.Wrapper)getMixed()).featureMap().clear();
        return;
      case MixedPackage.PERSON_TYPE__NAME:
        setName((NameType)null);
        return;
      case MixedPackage.PERSON_TYPE__EMAIL:
        getEmail().clear();
        return;
      case MixedPackage.PERSON_TYPE__URL:
        getUrl().clear();
        return;
      case MixedPackage.PERSON_TYPE__LINK:
        setLink((LinkType)null);
        return;
      case MixedPackage.PERSON_TYPE__ANY:
        ((FeatureMap.Internal.Wrapper)getAny()).featureMap().clear();
        return;
      case MixedPackage.PERSON_TYPE__CONTR:
        unsetContr();
        return;
      case MixedPackage.PERSON_TYPE__ID:
        setId(ID_EDEFAULT);
        return;
      case MixedPackage.PERSON_TYPE__SALARY:
        setSalary(SALARY_EDEFAULT);
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
      case MixedPackage.PERSON_TYPE__MIXED:
        return mixed != null && !mixed.featureMap().isEmpty();
      case MixedPackage.PERSON_TYPE__NAME:
        return getName() != null;
      case MixedPackage.PERSON_TYPE__EMAIL:
        return !getEmail().isEmpty();
      case MixedPackage.PERSON_TYPE__URL:
        return !getUrl().isEmpty();
      case MixedPackage.PERSON_TYPE__LINK:
        return getLink() != null;
      case MixedPackage.PERSON_TYPE__ANY:
        return !((FeatureMap.Internal.Wrapper)getAny()).featureMap().isEmpty();
      case MixedPackage.PERSON_TYPE__CONTR:
        return isSetContr();
      case MixedPackage.PERSON_TYPE__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case MixedPackage.PERSON_TYPE__SALARY:
        return SALARY_EDEFAULT == null ? salary != null : !SALARY_EDEFAULT.equals(salary);
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
    result.append(" (mixed: ");
    result.append(mixed);
    result.append(", contr: ");
    if (contrESet) result.append(contr); else result.append("<unset>");
    result.append(", id: ");
    result.append(id);
    result.append(", salary: ");
    result.append(salary);
    result.append(')');
    return result.toString();
  }

} //PersonTypeImpl
