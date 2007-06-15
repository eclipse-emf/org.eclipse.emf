/**
 * This is my code.
 *
 * $Id: Library.java,v 1.4 2007/06/15 21:22:34 emerks Exp $
 */
package org.examples.library;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.examples.library.elements.Book;
import org.examples.library.elements.Writer;
import org.examples.library.hr.Person;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.examples.library.Library#getName <em>Name</em>}</li>
 *   <li>{@link org.examples.library.Library#getWriters <em>Writers</em>}</li>
 *   <li>{@link org.examples.library.Library#getBooks <em>Books</em>}</li>
 *   <li>{@link org.examples.library.Library#getEmployees <em>Employees</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.examples.library.LibraryPackage#getLibrary()
 * @model extendedMetaData="name='Library' kind='elementOnly'"
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
   * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='element' name='name'"
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
   * Returns the value of the '<em><b>Writers</b></em>' containment reference list.
   * The list contents are of type {@link org.examples.library.elements.Writer}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Writers</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Writers</em>' containment reference list.
   * @see org.examples.library.LibraryPackage#getLibrary_Writers()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='writers'"
   * @generated
   */
  EList<Writer> getWriters();

  /**
   * Returns the value of the '<em><b>Books</b></em>' containment reference list.
   * The list contents are of type {@link org.examples.library.elements.Book}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Books</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Books</em>' containment reference list.
   * @see org.examples.library.LibraryPackage#getLibrary_Books()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='books'"
   * @generated
   */
  EList<Book> getBooks();

  /**
   * Returns the value of the '<em><b>Employees</b></em>' reference list.
   * The list contents are of type {@link org.examples.library.hr.Person}.
   * It is bidirectional and its opposite is '{@link org.examples.library.hr.Person#getLibrary <em>Library</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Employees</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Employees</em>' reference list.
   * @see org.examples.library.LibraryPackage#getLibrary_Employees()
   * @see org.examples.library.hr.Person#getLibrary
   * @model opposite="library"
   *        extendedMetaData="kind='element' name='employees'"
   * @generated
   */
  EList<Person> getEmployees();

} // Library
