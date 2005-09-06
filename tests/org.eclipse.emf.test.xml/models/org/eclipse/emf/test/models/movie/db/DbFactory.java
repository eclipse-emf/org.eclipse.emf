/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.test.models.movie.db;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.movie.db.DbPackage
 * @generated
 */
public interface DbFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  DbFactory eINSTANCE = new org.eclipse.emf.test.models.movie.db.impl.DbFactoryImpl();

  /**
   * Returns a new object of class '<em>Critics Review Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Critics Review Type</em>'.
   * @generated
   */
  CriticsReviewType createCriticsReviewType();

  /**
   * Returns a new object of class '<em>Customer Review Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Customer Review Type</em>'.
   * @generated
   */
  CustomerReviewType createCustomerReviewType();

  /**
   * Returns a new object of class '<em>Document Root</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Document Root</em>'.
   * @generated
   */
  DocumentRoot createDocumentRoot();

  /**
   * Returns a new object of class '<em>Movie DB Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Movie DB Type</em>'.
   * @generated
   */
  MovieDBType createMovieDBType();

  /**
   * Returns a new object of class '<em>Movie Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Movie Type</em>'.
   * @generated
   */
  MovieType createMovieType();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  DbPackage getDbPackage();

} //DbFactory
