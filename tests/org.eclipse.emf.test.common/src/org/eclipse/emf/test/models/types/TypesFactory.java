/**
 * <copyright>
 * </copyright>
 *
 * $Id: TypesFactory.java,v 1.1 2007/01/18 15:50:24 marcelop Exp $
 */
package org.eclipse.emf.test.models.types;


/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @generated
 */
public interface TypesFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  TypesFactory INSTANCE = org.eclipse.emf.test.models.types.impl.TypesFactoryImpl.eINSTANCE;

  /**
   * Returns a new object of class '<em>AThing</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>AThing</em>'.
   * @generated
   */
  AThing createAThing();

} //TypesFactory
