/**
 * <copyright>
 * </copyright>
 *
 * $Id: TypesFactory.java,v 1.1 2004/11/03 23:07:10 marcelop Exp $
 */
package org.eclipse.emf.test.core.sdo.types.model.types;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.core.sdo.types.model.types.TypesPackage
 * @generated
 */
public interface TypesFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  TypesFactory eINSTANCE = new org.eclipse.emf.test.core.sdo.types.model.types.impl.TypesFactoryImpl();

  /**
   * Returns a new object of class '<em>AThing</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>AThing</em>'.
   * @generated
   */
  AThing createAThing();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  TypesPackage getTypesPackage();

} //TypesFactory
