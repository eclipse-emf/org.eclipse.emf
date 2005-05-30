/**
 * <copyright>
 * </copyright>
 *
 * $Id: Items.java,v 1.1.2.1 2005/05/30 19:29:31 nickb Exp $
 */
package com.example.sdo.ipo;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Items</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.example.sdo.ipo.Items#getItem <em>Item</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.example.sdo.ipo.IpoPackage#getItems()
 * @model extendedMetaData="name='Items' kind='elementOnly'"
 * @generated
 */
public interface Items
{
  /**
   * Returns the value of the '<em><b>Item</b></em>' containment reference list.
   * The list contents are of type {@link com.example.sdo.ipo.ItemType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Item</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Item</em>' containment reference list.
   * @see com.example.sdo.ipo.IpoPackage#getItems_Item()
   * @model type="com.example.sdo.ipo.ItemType" containment="true" resolveProxies="false"
   *        extendedMetaData="kind='element' name='item'"
   * @generated
   */
  List getItem();

} // Items
