/**
 * <copyright>
 * </copyright>
 *
 * $Id: Library.java,v 1.1 2005/03/11 22:29:57 bportier Exp $
 */
package com.example.sdo.library;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link com.example.sdo.library.Library#getName <em>Name</em>}</li>
 *   <li>{@link com.example.sdo.library.Library#getWriters <em>Writers</em>}</li>
 *   <li>{@link com.example.sdo.library.Library#getBooks <em>Books</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.example.sdo.library.LibraryPackage#getLibrary()
 * @model
 * @generated
 */
public interface Library
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
   * @see com.example.sdo.library.LibraryPackage#getLibrary_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link com.example.sdo.library.Library#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Writers</b></em>' containment reference list.
   * The list contents are of type {@link com.example.sdo.library.Writer}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Writers</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Writers</em>' containment reference list.
   * @see com.example.sdo.library.LibraryPackage#getLibrary_Writers()
   * @model type="org.eclipse.example.library.Writer" containment="true"
   * @generated
   */
  List getWriters();

  /**
   * Returns the value of the '<em><b>Books</b></em>' containment reference list.
   * The list contents are of type {@link com.example.sdo.library.Book}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Books</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Books</em>' containment reference list.
   * @see com.example.sdo.library.LibraryPackage#getLibrary_Books()
   * @model type="org.eclipse.example.library.Book" containment="true"
   * @generated
   */
  List getBooks();

} // Library
