/**
 * <copyright>
 * </copyright>
 *
 * $Id: PenTypeImpl.java,v 1.1.2.1 2005/01/14 22:56:18 nickb Exp $
 */
package org.eclipse.emf.test.models.dbprice.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.test.models.dbprice.DbpricePackage;
import org.eclipse.emf.test.models.dbprice.PenType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pen Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.dbprice.impl.PenTypeImpl#getPrice <em>Price</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PenTypeImpl extends EObjectImpl implements PenType
{
  /**
   * The default value of the '{@link #getPrice() <em>Price</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrice()
   * @generated
   * @ordered
   */
  protected static final int PRICE_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getPrice() <em>Price</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrice()
   * @generated
   * @ordered
   */
  protected int price = PRICE_EDEFAULT;

  /**
   * This is true if the Price attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean priceESet = false;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PenTypeImpl()
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
    return DbpricePackage.eINSTANCE.getPenType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getPrice()
  {
    return price;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPrice(int newPrice)
  {
    int oldPrice = price;
    price = newPrice;
    boolean oldPriceESet = priceESet;
    priceESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, DbpricePackage.PEN_TYPE__PRICE, oldPrice, price, !oldPriceESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetPrice()
  {
    int oldPrice = price;
    boolean oldPriceESet = priceESet;
    price = PRICE_EDEFAULT;
    priceESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, DbpricePackage.PEN_TYPE__PRICE, oldPrice, PRICE_EDEFAULT, oldPriceESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetPrice()
  {
    return priceESet;
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
      case DbpricePackage.PEN_TYPE__PRICE:
        return new Integer(getPrice());
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
      case DbpricePackage.PEN_TYPE__PRICE:
        setPrice(((Integer)newValue).intValue());
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
      case DbpricePackage.PEN_TYPE__PRICE:
        unsetPrice();
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
      case DbpricePackage.PEN_TYPE__PRICE:
        return isSetPrice();
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
    result.append(" (price: ");
    if (priceESet) result.append(price); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

} //PenTypeImpl
