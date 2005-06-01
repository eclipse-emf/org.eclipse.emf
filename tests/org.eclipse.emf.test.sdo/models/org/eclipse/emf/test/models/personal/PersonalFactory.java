/**
 * <copyright>
 * </copyright>
 *
 * $Id: PersonalFactory.java,v 1.1 2005/06/01 22:28:12 elena Exp $
 */
package org.eclipse.emf.test.models.personal;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.personal.PersonalPackage
 * @generated
 */
public interface PersonalFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  PersonalFactory eINSTANCE = new org.eclipse.emf.test.models.personal.impl.PersonalFactoryImpl();

  /**
   * Returns a new object of class '<em>Document Root</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Document Root</em>'.
   * @generated
   */
  DocumentRoot createDocumentRoot();

  /**
   * Returns a new object of class '<em>Link Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Link Type</em>'.
   * @generated
   */
  LinkType createLinkType();

  /**
   * Returns a new object of class '<em>Name Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Name Type</em>'.
   * @generated
   */
  NameType createNameType();

  /**
   * Returns a new object of class '<em>Personnel Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Personnel Type</em>'.
   * @generated
   */
  PersonnelType createPersonnelType();

  /**
   * Returns a new object of class '<em>Person Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Person Type</em>'.
   * @generated
   */
  PersonType createPersonType();

  /**
   * Returns a new object of class '<em>Url Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Url Type</em>'.
   * @generated
   */
  UrlType createUrlType();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  PersonalPackage getPersonalPackage();

} //PersonalFactory
