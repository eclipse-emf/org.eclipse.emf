/**
 * This is my code.
 */
package org.examples.library.elements;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Writer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.examples.library.elements.Writer#getName <em>Name</em>}</li>
 *   <li>{@link org.examples.library.elements.Writer#getBooks <em>Books</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.examples.library.elements.ElementsPackage#getWriter()
 * @model extendedMetaData="name='Writer' kind='elementOnly'"
 * @generated
 */
public interface Writer extends EObject
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
   * @see org.examples.library.elements.ElementsPackage#getWriter_Name()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='element' name='name'"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.examples.library.elements.Writer#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Books</b></em>' reference list.
   * The list contents are of type {@link org.examples.library.elements.Book}.
   * It is bidirectional and its opposite is '{@link org.examples.library.elements.Book#getAuthor <em>Author</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Books</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Books</em>' reference list.
   * @see org.examples.library.elements.ElementsPackage#getWriter_Books()
   * @see org.examples.library.elements.Book#getAuthor
   * @model type="org.examples.library.elements.Book" opposite="author"
   *        extendedMetaData="kind='element' name='books'"
   * @generated
   */
  EList getBooks();

} // Writer
