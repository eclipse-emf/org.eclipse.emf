/**
 * <copyright>
 * </copyright>
 *
 * $Id: Items.java,v 1.1 2007/01/18 15:50:24 marcelop Exp $
 */
package org.eclipse.emf.test.models.ipo;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Items</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ipo.Items#getItem <em>Item</em>}</li>
 * </ul>
 * </p>
 *
 * @model extendedMetaData="name='Items' kind='elementOnly'"
 * @generated
 */
public interface Items
{
  /**
   * Returns the value of the '<em><b>Item</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.ipo.ItemType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Item</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Item</em>' containment reference list.
   * @model type="org.eclipse.emf.test.models.ipo.ItemType" containment="true"
   *        extendedMetaData="kind='element' name='item'"
   * @generated
   */
  List<ItemType> getItem();

} // Items
