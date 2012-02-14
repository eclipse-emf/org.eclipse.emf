/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.test.databinding.emfdb;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>D</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.databinding.emfdb.D#getElist <em>Elist</em>}</li>
 *   <li>{@link org.eclipse.emf.test.databinding.emfdb.D#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.databinding.emfdb.EmfdbPackage#getD()
 * @model
 * @generated
 */
public interface D extends EObject
{
  /**
   * Returns the value of the '<em><b>Elist</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.databinding.emfdb.E}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Elist</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Elist</em>' containment reference list.
   * @see org.eclipse.emf.test.databinding.emfdb.EmfdbPackage#getD_Elist()
   * @model containment="true"
   * @generated
   */
  EList<E> getElist();

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.emf.test.databinding.emfdb.EmfdbPackage#getD_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.databinding.emfdb.D#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

} // D
