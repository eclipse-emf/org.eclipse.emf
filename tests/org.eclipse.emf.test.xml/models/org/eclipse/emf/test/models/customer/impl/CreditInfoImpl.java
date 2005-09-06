/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.test.models.customer.impl;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.test.models.customer.CreditInfo;
import org.eclipse.emf.test.models.customer.CustomerPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Credit Info</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.customer.impl.CreditInfoImpl#getHolder <em>Holder</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.impl.CreditInfoImpl#getCcNumber <em>Cc Number</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.customer.impl.CreditInfoImpl#getExpireDate <em>Expire Date</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CreditInfoImpl extends EObjectImpl implements CreditInfo
{
  /**
   * The default value of the '{@link #getHolder() <em>Holder</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHolder()
   * @generated
   * @ordered
   */
  protected static final String HOLDER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getHolder() <em>Holder</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHolder()
   * @generated
   * @ordered
   */
  protected String holder = HOLDER_EDEFAULT;

  /**
   * The default value of the '{@link #getCcNumber() <em>Cc Number</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCcNumber()
   * @generated
   * @ordered
   */
  protected static final BigInteger CC_NUMBER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCcNumber() <em>Cc Number</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCcNumber()
   * @generated
   * @ordered
   */
  protected BigInteger ccNumber = CC_NUMBER_EDEFAULT;

  /**
   * The default value of the '{@link #getExpireDate() <em>Expire Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpireDate()
   * @generated
   * @ordered
   */
  protected static final Object EXPIRE_DATE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getExpireDate() <em>Expire Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpireDate()
   * @generated
   * @ordered
   */
  protected Object expireDate = EXPIRE_DATE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CreditInfoImpl()
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
    return CustomerPackage.eINSTANCE.getCreditInfo();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getHolder()
  {
    return holder;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHolder(String newHolder)
  {
    String oldHolder = holder;
    holder = newHolder;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackage.CREDIT_INFO__HOLDER, oldHolder, holder));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigInteger getCcNumber()
  {
    return ccNumber;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCcNumber(BigInteger newCcNumber)
  {
    BigInteger oldCcNumber = ccNumber;
    ccNumber = newCcNumber;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackage.CREDIT_INFO__CC_NUMBER, oldCcNumber, ccNumber));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getExpireDate()
  {
    return expireDate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpireDate(Object newExpireDate)
  {
    Object oldExpireDate = expireDate;
    expireDate = newExpireDate;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, CustomerPackage.CREDIT_INFO__EXPIRE_DATE, oldExpireDate, expireDate));
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
      case CustomerPackage.CREDIT_INFO__HOLDER:
        return getHolder();
      case CustomerPackage.CREDIT_INFO__CC_NUMBER:
        return getCcNumber();
      case CustomerPackage.CREDIT_INFO__EXPIRE_DATE:
        return getExpireDate();
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
      case CustomerPackage.CREDIT_INFO__HOLDER:
        setHolder((String)newValue);
        return;
      case CustomerPackage.CREDIT_INFO__CC_NUMBER:
        setCcNumber((BigInteger)newValue);
        return;
      case CustomerPackage.CREDIT_INFO__EXPIRE_DATE:
        setExpireDate((Object)newValue);
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
      case CustomerPackage.CREDIT_INFO__HOLDER:
        setHolder(HOLDER_EDEFAULT);
        return;
      case CustomerPackage.CREDIT_INFO__CC_NUMBER:
        setCcNumber(CC_NUMBER_EDEFAULT);
        return;
      case CustomerPackage.CREDIT_INFO__EXPIRE_DATE:
        setExpireDate(EXPIRE_DATE_EDEFAULT);
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
      case CustomerPackage.CREDIT_INFO__HOLDER:
        return HOLDER_EDEFAULT == null ? holder != null : !HOLDER_EDEFAULT.equals(holder);
      case CustomerPackage.CREDIT_INFO__CC_NUMBER:
        return CC_NUMBER_EDEFAULT == null ? ccNumber != null : !CC_NUMBER_EDEFAULT.equals(ccNumber);
      case CustomerPackage.CREDIT_INFO__EXPIRE_DATE:
        return EXPIRE_DATE_EDEFAULT == null ? expireDate != null : !EXPIRE_DATE_EDEFAULT.equals(expireDate);
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
    result.append(" (holder: ");
    result.append(holder);
    result.append(", ccNumber: ");
    result.append(ccNumber);
    result.append(", expireDate: ");
    result.append(expireDate);
    result.append(')');
    return result.toString();
  }

} //CreditInfoImpl
