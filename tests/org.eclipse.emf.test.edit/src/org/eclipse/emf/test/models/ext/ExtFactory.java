/**
 * <copyright>
 * </copyright>
 *
 * $Id: ExtFactory.java,v 1.1 2005/07/14 19:35:16 davidms Exp $
 */
package org.eclipse.emf.test.models.ext;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.ext.ExtPackage
 * @generated
 */
public interface ExtFactory extends EFactory{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ExtFactory eINSTANCE = new org.eclipse.emf.test.models.ext.impl.ExtFactoryImpl();

  /**
   * Returns a new object of class '<em>E</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>E</em>'.
   * @generated
   */
  ExtE createExtE();

  /**
   * Returns a new object of class '<em>F</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>F</em>'.
   * @generated
   */
  F createF();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  ExtPackage getExtPackage();

} //ExtFactory
