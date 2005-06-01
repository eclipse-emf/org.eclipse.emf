/**
 * <copyright>
 * </copyright>
 *
 * $Id: PersonTypeImpl.java,v 1.1 2005/06/01 22:28:12 elena Exp $
 */
package org.eclipse.emf.test.models.personal.mixed.impl;

import commonj.sdo.Sequence;

import java.math.BigInteger;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

import org.eclipse.emf.ecore.sdo.util.BasicESequence;
import org.eclipse.emf.ecore.sdo.util.ESequence;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.personal.mixed.ContrType;
import org.eclipse.emf.test.models.personal.mixed.LinkType;
import org.eclipse.emf.test.models.personal.mixed.MixedPackage;
import org.eclipse.emf.test.models.personal.mixed.NameType;
import org.eclipse.emf.test.models.personal.mixed.PersonType;

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
  protected EClass eStaticClass()
  {
    return MixedPackage.eINSTANCE.getPersonType();
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
    return (NameType)((ESequence)getMixed()).featureMap().get(MixedPackage.eINSTANCE.getPersonType_Name(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetName(NameType newName, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).basicAdd(MixedPackage.eINSTANCE.getPersonType_Name(), newName, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(NameType newName)
  {
    ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).set(MixedPackage.eINSTANCE.getPersonType_Name(), newName);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getEmail()
  {
    return ((ESequence)getMixed()).featureMap().list(MixedPackage.eINSTANCE.getPersonType_Email());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getUrl()
  {
    return ((ESequence)getMixed()).featureMap().list(MixedPackage.eINSTANCE.getPersonType_Url());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LinkType getLink()
  {
    return (LinkType)((ESequence)getMixed()).featureMap().get(MixedPackage.eINSTANCE.getPersonType_Link(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLink(LinkType newLink, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).basicAdd(MixedPackage.eINSTANCE.getPersonType_Link(), newLink, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLink(LinkType newLink)
  {
    ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).set(MixedPackage.eINSTANCE.getPersonType_Link(), newLink);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Sequence getAny()
  {
    return new BasicESequence((FeatureMap.Internal)((ESequence)getMixed()).featureMap().list(MixedPackage.eINSTANCE.getPersonType_Any()));
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
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case MixedPackage.PERSON_TYPE__MIXED:
        return ((InternalEList)((ESequence)getMixed()).featureMap()).basicRemove(otherEnd, msgs);
        case MixedPackage.PERSON_TYPE__NAME:
          return basicSetName(null, msgs);
        case MixedPackage.PERSON_TYPE__URL:
          return ((InternalEList)getUrl()).basicRemove(otherEnd, msgs);
        case MixedPackage.PERSON_TYPE__LINK:
          return basicSetLink(null, msgs);
        case MixedPackage.PERSON_TYPE__ANY:
        return ((InternalEList)((ESequence)getAny()).featureMap()).basicRemove(otherEnd, msgs);
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
      case MixedPackage.PERSON_TYPE__MIXED:
        return ((ESequence)getMixed()).featureMap();
      case MixedPackage.PERSON_TYPE__NAME:
        return getName();
      case MixedPackage.PERSON_TYPE__EMAIL:
        return getEmail();
      case MixedPackage.PERSON_TYPE__URL:
        return getUrl();
      case MixedPackage.PERSON_TYPE__LINK:
        return getLink();
      case MixedPackage.PERSON_TYPE__ANY:
        return ((ESequence)getAny()).featureMap();
      case MixedPackage.PERSON_TYPE__CONTR:
        return getContr();
      case MixedPackage.PERSON_TYPE__ID:
        return getId();
      case MixedPackage.PERSON_TYPE__SALARY:
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
      case MixedPackage.PERSON_TYPE__MIXED:
        ((ESequence)getMixed()).featureMap().clear();
        ((ESequence)getMixed()).featureMap().addAll((Collection)newValue);
        return;
      case MixedPackage.PERSON_TYPE__NAME:
        setName((NameType)newValue);
        return;
      case MixedPackage.PERSON_TYPE__EMAIL:
        getEmail().clear();
        getEmail().addAll((Collection)newValue);
        return;
      case MixedPackage.PERSON_TYPE__URL:
        getUrl().clear();
        getUrl().addAll((Collection)newValue);
        return;
      case MixedPackage.PERSON_TYPE__LINK:
        setLink((LinkType)newValue);
        return;
      case MixedPackage.PERSON_TYPE__ANY:
        ((ESequence)getAny()).featureMap().clear();
        ((ESequence)getAny()).featureMap().addAll((Collection)newValue);
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
      case MixedPackage.PERSON_TYPE__MIXED:
        ((ESequence)getMixed()).featureMap().clear();
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
        ((ESequence)getAny()).featureMap().clear();
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
        return !((ESequence)getAny()).featureMap().isEmpty();
      case MixedPackage.PERSON_TYPE__CONTR:
        return isSetContr();
      case MixedPackage.PERSON_TYPE__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case MixedPackage.PERSON_TYPE__SALARY:
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
