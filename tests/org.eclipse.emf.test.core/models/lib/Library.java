/**
 * <copyright>
 * </copyright>
 *
 * $Id: Library.java,v 1.1 2006/02/08 21:30:38 marcelop Exp $
 */
package lib;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link lib.Library#getName <em>Name</em>}</li>
 *   <li>{@link lib.Library#getBooks <em>Books</em>}</li>
 *   <li>{@link lib.Library#getAddress <em>Address</em>}</li>
 *   <li>{@link lib.Library#getWriters <em>Writers</em>}</li>
 *   <li>{@link lib.Library#getCafeteria <em>Cafeteria</em>}</li>
 * </ul>
 * </p>
 *
 * @see lib.LibPackage#getLibrary()
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
   * @see lib.LibPackage#getLibrary_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link lib.Library#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Books</b></em>' containment reference list.
   * The list contents are of type {@link lib.Book}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Books</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Books</em>' containment reference list.
   * @see lib.LibPackage#getLibrary_Books()
   * @model type="lib.Book" containment="true"
   * @generated
   */
  EList getBooks();

  /**
   * Returns the value of the '<em><b>Address</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Address</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Address</em>' containment reference.
   * @see #setAddress(Address)
   * @see lib.LibPackage#getLibrary_Address()
   * @model containment="true" required="true"
   * @generated
   */
  Address getAddress();

  /**
   * Sets the value of the '{@link lib.Library#getAddress <em>Address</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Address</em>' containment reference.
   * @see #getAddress()
   * @generated
   */
  void setAddress(Address value);

  /**
   * Returns the value of the '<em><b>Writers</b></em>' containment reference list.
   * The list contents are of type {@link lib.Person}.
   * It is bidirectional and its opposite is '{@link lib.Person#getLibrary <em>Library</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Writers</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Writers</em>' containment reference list.
   * @see lib.LibPackage#getLibrary_Writers()
   * @see lib.Person#getLibrary
   * @model type="lib.Person" opposite="library" containment="true"
   * @generated
   */
  EList getWriters();

  /**
   * Returns the value of the '<em><b>Cafeteria</b></em>' containment reference.
   * It is bidirectional and its opposite is '{@link lib.Cafeteria#getLibrary <em>Library</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Cafeteria</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Cafeteria</em>' containment reference.
   * @see #setCafeteria(Cafeteria)
   * @see lib.LibPackage#getLibrary_Cafeteria()
   * @see lib.Cafeteria#getLibrary
   * @model opposite="library" containment="true"
   * @generated
   */
  Cafeteria getCafeteria();

  /**
   * Sets the value of the '{@link lib.Library#getCafeteria <em>Cafeteria</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Cafeteria</em>' containment reference.
   * @see #getCafeteria()
   * @generated
   */
  void setCafeteria(Cafeteria value);

} // Library
