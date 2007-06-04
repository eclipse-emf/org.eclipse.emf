/**
 * This is my code.
 *
 * $Id: Writer.java,v 1.3 2007/06/04 18:49:09 emerks Exp $
 */
package org.examples.library.elements;

import org.eclipse.emf.common.util.EList;

import org.examples.library.hr.Person;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Writer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.examples.library.elements.Writer#getBooks <em>Books</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.examples.library.elements.ElementsPackage#getWriter()
 * @model extendedMetaData="name='Writer' kind='elementOnly'"
 * @generated
 */
public interface Writer extends Person
{
  /**
   * Returns the value of the '<em><b>Books</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Books</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Books</em>' reference list.
   * @see org.examples.library.elements.ElementsPackage#getWriter_Books()
   * @model type="org.eclipse.emf.ecore.EObject"
   *        extendedMetaData="kind='element' name='books'"
   * @generated
   */
  EList getBooks();

} // Writer
