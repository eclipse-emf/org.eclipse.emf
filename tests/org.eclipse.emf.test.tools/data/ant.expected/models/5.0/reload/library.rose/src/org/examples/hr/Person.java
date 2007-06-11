/**
 * This is my code.
 *
 * $Id: Person.java,v 1.3 2007/06/11 21:12:18 emerks Exp $
 */
package org.examples.hr;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.examples.library.Library;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Person</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.examples.hr.Person#getName <em>Name</em>}</li>
 *   <li>{@link org.examples.hr.Person#getLibrary <em>Library</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.examples.hr.HRPackage#getPerson()
 * @model
 * @generated
 */
public interface Person extends EObject
{
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
   * @see org.examples.hr.HRPackage#getPerson_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.examples.hr.Person#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Library</b></em>' reference list.
   * The list contents are of type {@link org.examples.library.Library}.
   * It is bidirectional and its opposite is '{@link org.examples.library.Library#getEmployees <em>Employees</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Library</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Library</em>' reference list.
   * @see org.examples.hr.HRPackage#getPerson_Library()
   * @see org.examples.library.Library#getEmployees
   * @model opposite="employees"
   * @generated
   */
  EList<Library> getLibrary();

} // Person
