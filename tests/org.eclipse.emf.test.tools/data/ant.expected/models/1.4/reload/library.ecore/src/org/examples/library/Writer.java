/**
 * This is my code.
 *
 * $Id: Writer.java,v 1.2 2007/04/26 20:57:13 emerks Exp $
 */
package org.examples.library;

import org.eclipse.emf.common.util.EList;

import org.examples.hr.Person;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Writer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.examples.library.Writer#getBooks <em>Books</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.examples.library.LibraryPackage#getWriter()
 * @model
 * @generated
 */
public interface Writer extends Person
{
  /**
   * Returns the value of the '<em><b>Books</b></em>' reference list.
   * The list contents are of type {@link org.examples.library.Book}.
   * It is bidirectional and its opposite is '{@link org.examples.library.Book#getAuthor <em>Author</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Books</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Books</em>' reference list.
   * @see org.examples.library.LibraryPackage#getWriter_Books()
   * @see org.examples.library.Book#getAuthor
   * @model type="org.examples.library.Book" opposite="author"
   * @generated
   */
  EList getBooks();

} // Writer
