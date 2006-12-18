/**
 * <copyright>
 * </copyright>
 *
 * $Id: KeyFactory.java,v 1.1 2006/12/18 22:02:03 marcelop Exp $
 */
package org.eclipse.emf.test.models.key;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.key.KeyPackage
 * @generated
 */
public interface KeyFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  KeyFactory eINSTANCE = org.eclipse.emf.test.models.key.impl.KeyFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Item</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Item</em>'.
   * @generated
   */
  Item createItem();

  /**
   * Returns a new object of class '<em>Root</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Root</em>'.
   * @generated
   */
  Root createRoot();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  KeyPackage getKeyPackage();

} //KeyFactory
