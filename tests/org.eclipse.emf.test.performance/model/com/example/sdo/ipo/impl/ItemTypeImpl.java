/**
 * <copyright>
 * </copyright>
 *
 * $Id: ItemTypeImpl.java,v 1.1.2.1 2005/05/30 19:26:59 nickb Exp $
 */
package com.example.sdo.ipo.impl;

import com.example.sdo.ipo.IpoPackage;
import com.example.sdo.ipo.ItemType;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Item Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link com.example.sdo.ipo.impl.ItemTypeImpl#getProductName <em>Product Name</em>}</li>
 *   <li>{@link com.example.sdo.ipo.impl.ItemTypeImpl#getQuantity <em>Quantity</em>}</li>
 *   <li>{@link com.example.sdo.ipo.impl.ItemTypeImpl#getUSPrice <em>US Price</em>}</li>
 *   <li>{@link com.example.sdo.ipo.impl.ItemTypeImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link com.example.sdo.ipo.impl.ItemTypeImpl#getShipDate <em>Ship Date</em>}</li>
 *   <li>{@link com.example.sdo.ipo.impl.ItemTypeImpl#getPartNum <em>Part Num</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ItemTypeImpl extends EDataObjectImpl implements ItemType
{
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
  protected static final BigDecimal USPRICE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getUSPrice() <em>US Price</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUSPrice()
   * @generated
   * @ordered
   */
  protected BigDecimal uSPrice = USPRICE_EDEFAULT;

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
  protected static final Object SHIP_DATE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getShipDate() <em>Ship Date</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getShipDate()
   * @generated
   * @ordered
   */
  protected Object shipDate = SHIP_DATE_EDEFAULT;

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
  protected EClass eStaticClass()
  {
    return IpoPackage.eINSTANCE.getItemType();
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
      eNotify(new ENotificationImpl(this, Notification.SET, IpoPackage.ITEM_TYPE__PRODUCT_NAME, oldProductName, productName));
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
      eNotify(new ENotificationImpl(this, Notification.SET, IpoPackage.ITEM_TYPE__QUANTITY, oldQuantity, quantity));
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
      eNotify(new ENotificationImpl(this, Notification.SET, IpoPackage.ITEM_TYPE__USPRICE, oldUSPrice, uSPrice));
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
      eNotify(new ENotificationImpl(this, Notification.SET, IpoPackage.ITEM_TYPE__COMMENT, oldComment, comment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getShipDate()
  {
    return shipDate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setShipDate(Object newShipDate)
  {
    Object oldShipDate = shipDate;
    shipDate = newShipDate;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, IpoPackage.ITEM_TYPE__SHIP_DATE, oldShipDate, shipDate));
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
      eNotify(new ENotificationImpl(this, Notification.SET, IpoPackage.ITEM_TYPE__PART_NUM, oldPartNum, partNum));
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
      case IpoPackage.ITEM_TYPE__PRODUCT_NAME:
        return getProductName();
      case IpoPackage.ITEM_TYPE__QUANTITY:
        return getQuantity();
      case IpoPackage.ITEM_TYPE__USPRICE:
        return getUSPrice();
      case IpoPackage.ITEM_TYPE__COMMENT:
        return getComment();
      case IpoPackage.ITEM_TYPE__SHIP_DATE:
        return getShipDate();
      case IpoPackage.ITEM_TYPE__PART_NUM:
        return getPartNum();
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
      case IpoPackage.ITEM_TYPE__PRODUCT_NAME:
        setProductName((String)newValue);
        return;
      case IpoPackage.ITEM_TYPE__QUANTITY:
        setQuantity((BigInteger)newValue);
        return;
      case IpoPackage.ITEM_TYPE__USPRICE:
        setUSPrice((BigDecimal)newValue);
        return;
      case IpoPackage.ITEM_TYPE__COMMENT:
        setComment((String)newValue);
        return;
      case IpoPackage.ITEM_TYPE__SHIP_DATE:
        setShipDate((Object)newValue);
        return;
      case IpoPackage.ITEM_TYPE__PART_NUM:
        setPartNum((String)newValue);
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
      case IpoPackage.ITEM_TYPE__PRODUCT_NAME:
        setProductName(PRODUCT_NAME_EDEFAULT);
        return;
      case IpoPackage.ITEM_TYPE__QUANTITY:
        setQuantity(QUANTITY_EDEFAULT);
        return;
      case IpoPackage.ITEM_TYPE__USPRICE:
        setUSPrice(USPRICE_EDEFAULT);
        return;
      case IpoPackage.ITEM_TYPE__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case IpoPackage.ITEM_TYPE__SHIP_DATE:
        setShipDate(SHIP_DATE_EDEFAULT);
        return;
      case IpoPackage.ITEM_TYPE__PART_NUM:
        setPartNum(PART_NUM_EDEFAULT);
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
      case IpoPackage.ITEM_TYPE__PRODUCT_NAME:
        return PRODUCT_NAME_EDEFAULT == null ? productName != null : !PRODUCT_NAME_EDEFAULT.equals(productName);
      case IpoPackage.ITEM_TYPE__QUANTITY:
        return QUANTITY_EDEFAULT == null ? quantity != null : !QUANTITY_EDEFAULT.equals(quantity);
      case IpoPackage.ITEM_TYPE__USPRICE:
        return USPRICE_EDEFAULT == null ? uSPrice != null : !USPRICE_EDEFAULT.equals(uSPrice);
      case IpoPackage.ITEM_TYPE__COMMENT:
        return COMMENT_EDEFAULT == null ? comment != null : !COMMENT_EDEFAULT.equals(comment);
      case IpoPackage.ITEM_TYPE__SHIP_DATE:
        return SHIP_DATE_EDEFAULT == null ? shipDate != null : !SHIP_DATE_EDEFAULT.equals(shipDate);
      case IpoPackage.ITEM_TYPE__PART_NUM:
        return PART_NUM_EDEFAULT == null ? partNum != null : !PART_NUM_EDEFAULT.equals(partNum);
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
