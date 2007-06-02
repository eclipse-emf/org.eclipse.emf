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
 * $Id: ItemTypeImpl.java,v 1.3 2007/06/02 19:35:32 emerks Exp $
 */
package org.eclipse.emf.test.models.ipo.impl;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.datatype.XMLGregorianCalendar;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

import org.eclipse.emf.test.models.ipo.ItemType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Item Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.ItemTypeImpl#getProductName <em>Product Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.ItemTypeImpl#getQuantity <em>Quantity</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.ItemTypeImpl#getUSPrice <em>US Price</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.ItemTypeImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.ItemTypeImpl#getShipDate <em>Ship Date</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.ItemTypeImpl#getPartNum <em>Part Num</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ItemTypeImpl extends EDataObjectImpl implements ItemType
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final long serialVersionUID = 1L;

  /**
   * The default value of the '{@link #getProductName() <em>Product Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProductName()
   * @generated
   * @ordered
   */
  protected static final String PRODUCT_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getProductName() <em>Product Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProductName()
   * @generated
   * @ordered
   */
  protected String productName = PRODUCT_NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getQuantity() <em>Quantity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQuantity()
   * @generated
   * @ordered
   */
  protected static final BigInteger QUANTITY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getQuantity() <em>Quantity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQuantity()
   * @generated
   * @ordered
   */
  protected BigInteger quantity = QUANTITY_EDEFAULT;

  /**
   * The default value of the '{@link #getUSPrice() <em>US Price</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUSPrice()
   * @generated
   * @ordered
   */
  protected static final BigDecimal US_PRICE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getUSPrice() <em>US Price</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUSPrice()
   * @generated
   * @ordered
   */
  protected BigDecimal uSPrice = US_PRICE_EDEFAULT;

  /**
   * The default value of the '{@link #getComment() <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComment()
   * @generated
   * @ordered
   */
  protected static final String COMMENT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getComment() <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComment()
   * @generated
   * @ordered
   */
  protected String comment = COMMENT_EDEFAULT;

  /**
   * The default value of the '{@link #getShipDate() <em>Ship Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getShipDate()
   * @generated
   * @ordered
   */
  protected static final XMLGregorianCalendar SHIP_DATE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getShipDate() <em>Ship Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getShipDate()
   * @generated
   * @ordered
   */
  protected XMLGregorianCalendar shipDate = SHIP_DATE_EDEFAULT;

  /**
   * The default value of the '{@link #getPartNum() <em>Part Num</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPartNum()
   * @generated
   * @ordered
   */
  protected static final String PART_NUM_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPartNum() <em>Part Num</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPartNum()
   * @generated
   * @ordered
   */
  protected String partNum = PART_NUM_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ItemTypeImpl()
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
    return IPOPackageImpl.Literals.ITEM_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getProductName()
  {
    return productName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProductName(String newProductName)
  {
    String oldProductName = productName;
    productName = newProductName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IPOPackageImpl.ITEM_TYPE__PRODUCT_NAME, oldProductName, productName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigInteger getQuantity()
  {
    return quantity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setQuantity(BigInteger newQuantity)
  {
    BigInteger oldQuantity = quantity;
    quantity = newQuantity;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IPOPackageImpl.ITEM_TYPE__QUANTITY, oldQuantity, quantity));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BigDecimal getUSPrice()
  {
    return uSPrice;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUSPrice(BigDecimal newUSPrice)
  {
    BigDecimal oldUSPrice = uSPrice;
    uSPrice = newUSPrice;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IPOPackageImpl.ITEM_TYPE__US_PRICE, oldUSPrice, uSPrice));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getComment()
  {
    return comment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComment(String newComment)
  {
    String oldComment = comment;
    comment = newComment;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IPOPackageImpl.ITEM_TYPE__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XMLGregorianCalendar getShipDate()
  {
    return shipDate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setShipDate(XMLGregorianCalendar newShipDate)
  {
    XMLGregorianCalendar oldShipDate = shipDate;
    shipDate = newShipDate;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IPOPackageImpl.ITEM_TYPE__SHIP_DATE, oldShipDate, shipDate));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPartNum()
  {
    return partNum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPartNum(String newPartNum)
  {
    String oldPartNum = partNum;
    partNum = newPartNum;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IPOPackageImpl.ITEM_TYPE__PART_NUM, oldPartNum, partNum));
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
      case IPOPackageImpl.ITEM_TYPE__PRODUCT_NAME:
        return getProductName();
      case IPOPackageImpl.ITEM_TYPE__QUANTITY:
        return getQuantity();
      case IPOPackageImpl.ITEM_TYPE__US_PRICE:
        return getUSPrice();
      case IPOPackageImpl.ITEM_TYPE__COMMENT:
        return getComment();
      case IPOPackageImpl.ITEM_TYPE__SHIP_DATE:
        return getShipDate();
      case IPOPackageImpl.ITEM_TYPE__PART_NUM:
        return getPartNum();
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
      case IPOPackageImpl.ITEM_TYPE__PRODUCT_NAME:
        setProductName((String)newValue);
        return;
      case IPOPackageImpl.ITEM_TYPE__QUANTITY:
        setQuantity((BigInteger)newValue);
        return;
      case IPOPackageImpl.ITEM_TYPE__US_PRICE:
        setUSPrice((BigDecimal)newValue);
        return;
      case IPOPackageImpl.ITEM_TYPE__COMMENT:
        setComment((String)newValue);
        return;
      case IPOPackageImpl.ITEM_TYPE__SHIP_DATE:
        setShipDate((XMLGregorianCalendar)newValue);
        return;
      case IPOPackageImpl.ITEM_TYPE__PART_NUM:
        setPartNum((String)newValue);
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
      case IPOPackageImpl.ITEM_TYPE__PRODUCT_NAME:
        setProductName(PRODUCT_NAME_EDEFAULT);
        return;
      case IPOPackageImpl.ITEM_TYPE__QUANTITY:
        setQuantity(QUANTITY_EDEFAULT);
        return;
      case IPOPackageImpl.ITEM_TYPE__US_PRICE:
        setUSPrice(US_PRICE_EDEFAULT);
        return;
      case IPOPackageImpl.ITEM_TYPE__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case IPOPackageImpl.ITEM_TYPE__SHIP_DATE:
        setShipDate(SHIP_DATE_EDEFAULT);
        return;
      case IPOPackageImpl.ITEM_TYPE__PART_NUM:
        setPartNum(PART_NUM_EDEFAULT);
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
      case IPOPackageImpl.ITEM_TYPE__PRODUCT_NAME:
        return PRODUCT_NAME_EDEFAULT == null ? productName != null : !PRODUCT_NAME_EDEFAULT.equals(productName);
      case IPOPackageImpl.ITEM_TYPE__QUANTITY:
        return QUANTITY_EDEFAULT == null ? quantity != null : !QUANTITY_EDEFAULT.equals(quantity);
      case IPOPackageImpl.ITEM_TYPE__US_PRICE:
        return US_PRICE_EDEFAULT == null ? uSPrice != null : !US_PRICE_EDEFAULT.equals(uSPrice);
      case IPOPackageImpl.ITEM_TYPE__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case IPOPackageImpl.ITEM_TYPE__SHIP_DATE:
        return SHIP_DATE_EDEFAULT == null ? shipDate != null : !SHIP_DATE_EDEFAULT.equals(shipDate);
      case IPOPackageImpl.ITEM_TYPE__PART_NUM:
        return PART_NUM_EDEFAULT == null ? partNum != null : !PART_NUM_EDEFAULT.equals(partNum);
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
    result.append(" (productName: ");
    result.append(productName);
    result.append(", quantity: ");
    result.append(quantity);
    result.append(", uSPrice: ");
    result.append(uSPrice);
    result.append(", comment: ");
    result.append(comment);
    result.append(", shipDate: ");
    result.append(shipDate);
    result.append(", partNum: ");
    result.append(partNum);
    result.append(')');
    return result.toString();
  }

} //ItemTypeImpl
