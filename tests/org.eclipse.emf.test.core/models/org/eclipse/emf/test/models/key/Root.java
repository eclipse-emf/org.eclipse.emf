/**
 * <copyright>
 * </copyright>
 *
 * $Id: Root.java,v 1.1 2006/12/18 22:02:03 marcelop Exp $
 */
package org.eclipse.emf.test.models.key;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.key.Root#getItems <em>Items</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.key.KeyPackage#getRoot()
 * @model
 * @generated
 */
public interface Root extends EObject
{
  /**
   * Returns the value of the '<em><b>Items</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.key.Item}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Items</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Items</em>' containment reference list.
   * @see org.eclipse.emf.test.models.key.KeyPackage#getRoot_Items()
   * @model type="org.eclipse.emf.test.models.key.Item" containment="true"
   * @generated
   */
  EList<Item> getItems();

} // Root
