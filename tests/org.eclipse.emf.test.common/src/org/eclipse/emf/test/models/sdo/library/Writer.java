/**
 * <copyright>
 * </copyright>
 *
 * $Id: Writer.java,v 1.1 2007/01/18 15:50:17 marcelop Exp $
 */
package org.eclipse.emf.test.models.sdo.library;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Writer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.sdo.library.Writer#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.sdo.library.Writer#getBooks <em>Books</em>}</li>
 * </ul>
 * </p>
 *
 * @model
 * @generated
 */
public interface Writer
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
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.sdo.library.Writer#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Books</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.sdo.library.Book}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.sdo.library.Book#getAuthor <em>Author</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Books</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Books</em>' reference list.
   * @see org.eclipse.emf.test.models.sdo.library.Book#getAuthor
   * @model type="org.eclipse.emf.test.models.sdo.library.Book" opposite="author"
   * @generated
   */
  List<Book> getBooks();

} // Writer
