/**
 * <copyright>
 * </copyright>
 *
 * $Id: Items.java,v 1.1.2.1 2005/05/30 19:26:59 nickb Exp $
 */
package com.example.ipo;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Items</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.example.ipo.Items#getItem <em>Item</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.example.ipo.IpoPackage#getItems()
 * @model extendedMetaData="name='Items' kind='elementOnly'"
 * @generated
 */
public interface Items extends EObject
{
  /**
   * Returns the value of the '<em><b>Item</b></em>' containment reference list.
   * The list contents are of type {@link com.example.ipo.ItemType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Item</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Item</em>' containment reference list.
   * @see com.example.ipo.IpoPackage#getItems_Item()
   * @model type="com.example.ipo.ItemType" containment="true" resolveProxies="false"
   *        extendedMetaData="kind='element' name='item'"
   * @generated
   */
  EList getItem();

} // Items
