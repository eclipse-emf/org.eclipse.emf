/**
 * This is my code.
 *
 * $Id: Person.java,v 1.2 2007/04/26 20:57:15 emerks Exp $
 */
package org.examples.library.hr;

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
 *   <li>{@link org.examples.library.hr.Person#getName <em>Name</em>}</li>
 *   <li>{@link org.examples.library.hr.Person#getLibrary <em>Library</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.examples.library.hr.HrPackage#getPerson()
 * @model extendedMetaData="name='Person' kind='elementOnly'"
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
   * @see org.examples.library.hr.HrPackage#getPerson_Name()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='element' name='name'"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.examples.library.hr.Person#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Library</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.examples.library.Library#getEmployees <em>Employees</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Library</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Library</em>' reference.
   * @see #setLibrary(Library)
   * @see org.examples.library.hr.HrPackage#getPerson_Library()
   * @see org.examples.library.Library#getEmployees
   * @model opposite="employees" required="true"
   *        extendedMetaData="kind='element' name='library'"
   * @generated
   */
  Library getLibrary();

  /**
   * Sets the value of the '{@link org.examples.library.hr.Person#getLibrary <em>Library</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Library</em>' reference.
   * @see #getLibrary()
   * @generated
   */
  void setLibrary(Library value);

} // Person
