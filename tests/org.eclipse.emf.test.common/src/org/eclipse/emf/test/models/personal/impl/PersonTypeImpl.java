/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: PersonTypeImpl.java,v 1.2 2007/01/18 22:06:41 marcelop Exp $
 */
package org.eclipse.emf.test.models.personal.impl;

import java.math.BigInteger;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.personal.ContrType;
import org.eclipse.emf.test.models.personal.LinkType;
import org.eclipse.emf.test.models.personal.NameType;
import org.eclipse.emf.test.models.personal.PersonType;
import org.eclipse.emf.test.models.personal.UrlType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Person Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.personal.impl.PersonTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.impl.PersonTypeImpl#getEmail <em>Email</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.impl.PersonTypeImpl#getUrl <em>Url</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.impl.PersonTypeImpl#getLink <em>Link</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.impl.PersonTypeImpl#getContr <em>Contr</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.impl.PersonTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.impl.PersonTypeImpl#getSalary <em>Salary</em>}</li>
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
   * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected NameType name = null;

  /**
   * The cached value of the '{@link #getEmail() <em>Email</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEmail()
   * @generated
   * @ordered
   */
  protected EList<String> email = null;

  /**
   * The cached value of the '{@link #getUrl() <em>Url</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUrl()
   * @generated
   * @ordered
   */
  protected EList<UrlType> url = null;

  /**
   * The cached value of the '{@link #getLink() <em>Link</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLink()
   * @generated
   * @ordered
   */
  protected LinkType link = null;

  /**
   * The default value of the '{@link #getContr() <em>Contr</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContr()
   * @generated
   * @ordered
   */
  protected static final ContrType CONTR_EDEFAULT = ContrType.FALSE;

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
    return PersonalPackageImpl.Literals.PERSON_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NameType getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetName(NameType newName, NotificationChain msgs)
  {
    NameType oldName = name;
    name = newName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PersonalPackageImpl.PERSON_TYPE__NAME, oldName, newName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(NameType newName)
  {
    if (newName != name)
    {
      NotificationChain msgs = null;
      if (name != null)
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PersonalPackageImpl.PERSON_TYPE__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PersonalPackageImpl.PERSON_TYPE__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PersonalPackageImpl.PERSON_TYPE__NAME, newName, newName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List<String> getEmail()
  {
    if (email == null)
    {
      email = new EDataTypeEList<String>(String.class, this, PersonalPackageImpl.PERSON_TYPE__EMAIL);
    }
    return email;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List<UrlType> getUrl()
  {
    if (url == null)
    {
      url = new EObjectContainmentEList<UrlType>(UrlType.class, this, PersonalPackageImpl.PERSON_TYPE__URL);
    }
    return url;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LinkType getLink()
  {
    return link;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLink(LinkType newLink, NotificationChain msgs)
  {
    LinkType oldLink = link;
    link = newLink;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PersonalPackageImpl.PERSON_TYPE__LINK, oldLink, newLink);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLink(LinkType newLink)
  {
    if (newLink != link)
    {
      NotificationChain msgs = null;
      if (link != null)
        msgs = ((InternalEObject)link).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PersonalPackageImpl.PERSON_TYPE__LINK, null, msgs);
      if (newLink != null)
        msgs = ((InternalEObject)newLink).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PersonalPackageImpl.PERSON_TYPE__LINK, null, msgs);
      msgs = basicSetLink(newLink, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PersonalPackageImpl.PERSON_TYPE__LINK, newLink, newLink));
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
      eNotify(new ENotificationImpl(this, Notification.SET, PersonalPackageImpl.PERSON_TYPE__CONTR, oldContr, contr, !oldContrESet));
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
      eNotify(new ENotificationImpl(this, Notification.UNSET, PersonalPackageImpl.PERSON_TYPE__CONTR, oldContr, CONTR_EDEFAULT, oldContrESet));
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
      eNotify(new ENotificationImpl(this, Notification.SET, PersonalPackageImpl.PERSON_TYPE__ID, oldId, id));
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
      eNotify(new ENotificationImpl(this, Notification.SET, PersonalPackageImpl.PERSON_TYPE__SALARY, oldSalary, salary));
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
      case PersonalPackageImpl.PERSON_TYPE__NAME:
        return basicSetName(null, msgs);
      case PersonalPackageImpl.PERSON_TYPE__URL:
        return ((InternalEList<?>)getUrl()).basicRemove(otherEnd, msgs);
      case PersonalPackageImpl.PERSON_TYPE__LINK:
        return basicSetLink(null, msgs);
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
      case PersonalPackageImpl.PERSON_TYPE__NAME:
        return getName();
      case PersonalPackageImpl.PERSON_TYPE__EMAIL:
        return getEmail();
      case PersonalPackageImpl.PERSON_TYPE__URL:
        return getUrl();
      case PersonalPackageImpl.PERSON_TYPE__LINK:
        return getLink();
      case PersonalPackageImpl.PERSON_TYPE__CONTR:
        return getContr();
      case PersonalPackageImpl.PERSON_TYPE__ID:
        return getId();
      case PersonalPackageImpl.PERSON_TYPE__SALARY:
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
      case PersonalPackageImpl.PERSON_TYPE__NAME:
        setName((NameType)newValue);
        return;
      case PersonalPackageImpl.PERSON_TYPE__EMAIL:
        getEmail().clear();
        getEmail().addAll((Collection<? extends String>)newValue);
        return;
      case PersonalPackageImpl.PERSON_TYPE__URL:
        getUrl().clear();
        getUrl().addAll((Collection<? extends UrlType>)newValue);
        return;
      case PersonalPackageImpl.PERSON_TYPE__LINK:
        setLink((LinkType)newValue);
        return;
      case PersonalPackageImpl.PERSON_TYPE__CONTR:
        setContr((ContrType)newValue);
        return;
      case PersonalPackageImpl.PERSON_TYPE__ID:
        setId((String)newValue);
        return;
      case PersonalPackageImpl.PERSON_TYPE__SALARY:
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
      case PersonalPackageImpl.PERSON_TYPE__NAME:
        setName((NameType)null);
        return;
      case PersonalPackageImpl.PERSON_TYPE__EMAIL:
        getEmail().clear();
        return;
      case PersonalPackageImpl.PERSON_TYPE__URL:
        getUrl().clear();
        return;
      case PersonalPackageImpl.PERSON_TYPE__LINK:
        setLink((LinkType)null);
        return;
      case PersonalPackageImpl.PERSON_TYPE__CONTR:
        unsetContr();
        return;
      case PersonalPackageImpl.PERSON_TYPE__ID:
        setId(ID_EDEFAULT);
        return;
      case PersonalPackageImpl.PERSON_TYPE__SALARY:
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
      case PersonalPackageImpl.PERSON_TYPE__NAME:
        return name != null;
      case PersonalPackageImpl.PERSON_TYPE__EMAIL:
        return email != null && !email.isEmpty();
      case PersonalPackageImpl.PERSON_TYPE__URL:
        return url != null && !url.isEmpty();
      case PersonalPackageImpl.PERSON_TYPE__LINK:
        return link != null;
      case PersonalPackageImpl.PERSON_TYPE__CONTR:
        return isSetContr();
      case PersonalPackageImpl.PERSON_TYPE__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case PersonalPackageImpl.PERSON_TYPE__SALARY:
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
    result.append(" (email: ");
    result.append(email);
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
