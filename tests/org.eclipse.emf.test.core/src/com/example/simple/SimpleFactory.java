/**
 * <copyright>
 * </copyright>
 *
 * $Id: SimpleFactory.java,v 1.1 2004/06/30 21:11:29 marcelop Exp $
 */
package com.example.simple;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see com.example.simple.SimplePackage
 * @generated
 */
public interface SimpleFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SimpleFactory eINSTANCE = new com.example.simple.impl.SimpleFactoryImpl();

  /**
   * Returns a new object of class '<em>Quote</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Quote</em>'.
   * @generated
   */
  Quote createQuote();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  SimplePackage getSimplePackage();

} //SimpleFactory
