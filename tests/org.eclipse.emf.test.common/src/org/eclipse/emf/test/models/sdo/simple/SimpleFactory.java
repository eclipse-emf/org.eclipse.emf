/**
 * <copyright>
 * </copyright>
 *
 * $Id: SimpleFactory.java,v 1.1 2007/01/18 15:50:24 marcelop Exp $
 */
package org.eclipse.emf.test.models.sdo.simple;


/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @generated
 */
public interface SimpleFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SimpleFactory INSTANCE = org.eclipse.emf.test.models.sdo.simple.impl.SimpleFactoryImpl.eINSTANCE;

  /**
   * Returns a new object of class '<em>Quote</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Quote</em>'.
   * @generated
   */
  Quote createQuote();

} //SimpleFactory
