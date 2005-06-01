/**
 * <copyright>
 * </copyright>
 *
 * $Id: PersonTypeImpl.java,v 1.1 2005/06/01 22:28:12 elena Exp $
 */
package org.eclipse.emf.test.models.personal.impl;

import java.math.BigInteger;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
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
import org.eclipse.emf.test.models.personal.PersonalPackage;
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
  protected EList email = null;

  /**
   * The cached value of the '{@link #getUrl() <em>Url</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUrl()
   * @generated
   * @ordered
   */
  protected EList url = null;

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
  protected EClass eStaticClass()
  {
    return PersonalPackage.eINSTANCE.getPersonType();
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PersonalPackage.PERSON_TYPE__NAME, oldName, newName);
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
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PersonalPackage.PERSON_TYPE__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PersonalPackage.PERSON_TYPE__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PersonalPackage.PERSON_TYPE__NAME, newName, newName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getEmail()
  {
    if (email == null)
    {
      email = new EDataTypeEList(String.class, this, PersonalPackage.PERSON_TYPE__EMAIL);
    }
    return email;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getUrl()
  {
    if (url == null)
    {
      url = new EObjectContainmentEList(UrlType.class, this, PersonalPackage.PERSON_TYPE__URL);
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PersonalPackage.PERSON_TYPE__LINK, oldLink, newLink);
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
        msgs = ((InternalEObject)link).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PersonalPackage.PERSON_TYPE__LINK, null, msgs);
      if (newLink != null)
        msgs = ((InternalEObject)newLink).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PersonalPackage.PERSON_TYPE__LINK, null, msgs);
      msgs = basicSetLink(newLink, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PersonalPackage.PERSON_TYPE__LINK, newLink, newLink));
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
      eNotify(new ENotificationImpl(this, Notification.SET, PersonalPackage.PERSON_TYPE__CONTR, oldContr, contr, !oldContrESet));
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
      eNotify(new ENotificationImpl(this, Notification.UNSET, PersonalPackage.PERSON_TYPE__CONTR, oldContr, CONTR_EDEFAULT, oldContrESet));
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
      eNotify(new ENotificationImpl(this, Notification.SET, PersonalPackage.PERSON_TYPE__ID, oldId, id));
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
      eNotify(new ENotificationImpl(this, Notification.SET, PersonalPackage.PERSON_TYPE__SALARY, oldSalary, salary));
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
        case PersonalPackage.PERSON_TYPE__NAME:
          return basicSetName(null, msgs);
        case PersonalPackage.PERSON_TYPE__URL:
          return ((InternalEList)getUrl()).basicRemove(otherEnd, msgs);
        case PersonalPackage.PERSON_TYPE__LINK:
          return basicSetLink(null, msgs);
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
      case PersonalPackage.PERSON_TYPE__NAME:
        return getName();
      case PersonalPackage.PERSON_TYPE__EMAIL:
        return getEmail();
      case PersonalPackage.PERSON_TYPE__URL:
        return getUrl();
      case PersonalPackage.PERSON_TYPE__LINK:
        return getLink();
      case PersonalPackage.PERSON_TYPE__CONTR:
        return getContr();
      case PersonalPackage.PERSON_TYPE__ID:
        return getId();
      case PersonalPackage.PERSON_TYPE__SALARY:
        return getSalary();
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
      case PersonalPackage.PERSON_TYPE__NAME:
        setName((NameType)newValue);
        return;
      case PersonalPackage.PERSON_TYPE__EMAIL:
        getEmail().clear();
        getEmail().addAll((Collection)newValue);
        return;
      case PersonalPackage.PERSON_TYPE__URL:
        getUrl().clear();
        getUrl().addAll((Collection)newValue);
        return;
      case PersonalPackage.PERSON_TYPE__LINK:
        setLink((LinkType)newValue);
        return;
      case PersonalPackage.PERSON_TYPE__CONTR:
        setContr((ContrType)newValue);
        return;
      case PersonalPackage.PERSON_TYPE__ID:
        setId((String)newValue);
        return;
      case PersonalPackage.PERSON_TYPE__SALARY:
        setSalary((BigInteger)newValue);
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
      case PersonalPackage.PERSON_TYPE__NAME:
        setName((NameType)null);
        return;
      case PersonalPackage.PERSON_TYPE__EMAIL:
        getEmail().clear();
        return;
      case PersonalPackage.PERSON_TYPE__URL:
        getUrl().clear();
        return;
      case PersonalPackage.PERSON_TYPE__LINK:
        setLink((LinkType)null);
        return;
      case PersonalPackage.PERSON_TYPE__CONTR:
        unsetContr();
        return;
      case PersonalPackage.PERSON_TYPE__ID:
        setId(ID_EDEFAULT);
        return;
      case PersonalPackage.PERSON_TYPE__SALARY:
        setSalary(SALARY_EDEFAULT);
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
      case PersonalPackage.PERSON_TYPE__NAME:
        return name != null;
      case PersonalPackage.PERSON_TYPE__EMAIL:
        return email != null && !email.isEmpty();
      case PersonalPackage.PERSON_TYPE__URL:
        return url != null && !url.isEmpty();
      case PersonalPackage.PERSON_TYPE__LINK:
        return link != null;
      case PersonalPackage.PERSON_TYPE__CONTR:
        return isSetContr();
      case PersonalPackage.PERSON_TYPE__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case PersonalPackage.PERSON_TYPE__SALARY:
        return SALARY_EDEFAULT == null ? salary != null : !SALARY_EDEFAULT.equals(salary);
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
