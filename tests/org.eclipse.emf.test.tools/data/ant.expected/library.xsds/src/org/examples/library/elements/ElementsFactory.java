/**
 * <copyright>
 * </copyright>
 *
 * $Id: ElementsFactory.java,v 1.1 2005/02/10 22:11:11 marcelop Exp $
 */
package org.examples.library.elements;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.examples.library.elements.ElementsPackage
 * @generated
 */
public interface ElementsFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  ElementsFactory eINSTANCE = new org.examples.library.elements.impl.ElementsFactoryImpl();

  /**
   * Returns a new object of class '<em>Book</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Book</em>'.
   * @generated
   */
  Book createBook();

  /**
   * Returns a new object of class '<em>Writer</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Writer</em>'.
   * @generated
   */
  Writer createWriter();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  ElementsPackage getElementsPackage();

} //ElementsFactory
