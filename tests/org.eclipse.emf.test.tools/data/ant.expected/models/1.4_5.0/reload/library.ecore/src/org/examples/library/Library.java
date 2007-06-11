/**
 * This is my code.
 *
 * $Id: Library.java,v 1.3 2007/06/11 21:12:18 emerks Exp $
 */
package org.examples.library;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.examples.hr.Person;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.examples.library.Library#getName <em>Name</em>}</li>
 *   <li>{@link org.examples.library.Library#getSite <em>Site</em>}</li>
 *   <li>{@link org.examples.library.Library#getWriters <em>Writers</em>}</li>
 *   <li>{@link org.examples.library.Library#getBooks <em>Books</em>}</li>
 *   <li>{@link org.examples.library.Library#getEmployees <em>Employees</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.examples.library.LibraryPackage#getLibrary()
 * @model
 * @generated
 */
public interface Library extends EObject
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
   * @see org.examples.library.LibraryPackage#getLibrary_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.examples.library.Library#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Site</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Site</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Site</em>' attribute.
   * @see #setSite(String)
   * @see org.examples.library.LibraryPackage#getLibrary_Site()
   * @model
   * @generated
   */
  String getSite();

  /**
   * Sets the value of the '{@link org.examples.library.Library#getSite <em>Site</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Site</em>' attribute.
   * @see #getSite()
   * @generated
   */
  void setSite(String value);

  /**
   * Returns the value of the '<em><b>Writers</b></em>' containment reference list.
   * The list contents are of type {@link org.examples.library.Writer}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Writers</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Writers</em>' containment reference list.
   * @see org.examples.library.LibraryPackage#getLibrary_Writers()
   * @model containment="true"
   * @generated
   */
  EList<Writer> getWriters();

  /**
   * Returns the value of the '<em><b>Books</b></em>' containment reference list.
   * The list contents are of type {@link org.examples.library.Book}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Books</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Books</em>' containment reference list.
   * @see org.examples.library.LibraryPackage#getLibrary_Books()
   * @model containment="true"
   * @generated
   */
  EList<Book> getBooks();

  /**
   * Returns the value of the '<em><b>Employees</b></em>' reference list.
   * The list contents are of type {@link org.examples.hr.Person}.
   * It is bidirectional and its opposite is '{@link org.examples.hr.Person#getLibrary <em>Library</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Employees</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Employees</em>' reference list.
   * @see org.examples.library.LibraryPackage#getLibrary_Employees()
   * @see org.examples.hr.Person#getLibrary
   * @model opposite="Library"
   * @generated
   */
  EList<Person> getEmployees();

} // Library
