/**
 * <copyright>
 * </copyright>
 *
 * $Id: AddressImpl.java,v 1.1 2007/01/18 15:50:15 marcelop Exp $
 */
package org.eclipse.emf.test.models.ipo.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

import org.eclipse.emf.test.models.ipo.Address;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Address</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.AddressImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.AddressImpl#getStreet <em>Street</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.AddressImpl#getCity <em>City</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AddressImpl extends EDataObjectImpl implements Address
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final long serialVersionUID = 1L;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getStreet() <em>Street</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStreet()
   * @generated
   * @ordered
   */
  protected static final String STREET_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getStreet() <em>Street</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStreet()
   * @generated
   * @ordered
   */
  protected String street = STREET_EDEFAULT;

  /**
   * The default value of the '{@link #getCity() <em>City</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCity()
   * @generated
   * @ordered
   */
  protected static final String CITY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCity() <em>City</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCity()
   * @generated
   * @ordered
   */
  protected String city = CITY_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AddressImpl()
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
    return IPOPackageImpl.Literals.ADDRESS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IPOPackageImpl.ADDRESS__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getStreet()
  {
    return street;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStreet(String newStreet)
  {
    String oldStreet = street;
    street = newStreet;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IPOPackageImpl.ADDRESS__STREET, oldStreet, street));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCity()
  {
    return city;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCity(String newCity)
  {
    String oldCity = city;
    city = newCity;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IPOPackageImpl.ADDRESS__CITY, oldCity, city));
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
      case IPOPackageImpl.ADDRESS__NAME:
        return getName();
      case IPOPackageImpl.ADDRESS__STREET:
        return getStreet();
      case IPOPackageImpl.ADDRESS__CITY:
        return getCity();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case IPOPackageImpl.ADDRESS__NAME:
        setName((String)newValue);
        return;
      case IPOPackageImpl.ADDRESS__STREET:
        setStreet((String)newValue);
        return;
      case IPOPackageImpl.ADDRESS__CITY:
        setCity((String)newValue);
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
      case IPOPackageImpl.ADDRESS__NAME:
        setName(NAME_EDEFAULT);
        return;
      case IPOPackageImpl.ADDRESS__STREET:
        setStreet(STREET_EDEFAULT);
        return;
      case IPOPackageImpl.ADDRESS__CITY:
        setCity(CITY_EDEFAULT);
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
      case IPOPackageImpl.ADDRESS__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case IPOPackageImpl.ADDRESS__STREET:
        return STREET_EDEFAULT == null ? street != null : !STREET_EDEFAULT.equals(street);
      case IPOPackageImpl.ADDRESS__CITY:
        return CITY_EDEFAULT == null ? city != null : !CITY_EDEFAULT.equals(city);
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
    result.append(" (name: ");
    result.append(name);
    result.append(", street: ");
    result.append(street);
    result.append(", city: ");
    result.append(city);
    result.append(')');
    return result.toString();
  }

} //AddressImpl
