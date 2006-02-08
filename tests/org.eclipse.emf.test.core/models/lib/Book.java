/**
 * <copyright>
 * </copyright>
 *
 * $Id: Book.java,v 1.1 2006/02/08 21:30:38 marcelop Exp $
 */
package lib;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Book</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link lib.Book#getTitle <em>Title</em>}</li>
 * </ul>
 * </p>
 *
 * @see lib.LibPackage#getBook()
 * @model
 * @generated
 */
public interface Book extends EObject
{
  /**
   * Returns the value of the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Title</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Title</em>' attribute.
   * @see #setTitle(String)
   * @see lib.LibPackage#getBook_Title()
   * @model
   * @generated
   */
  String getTitle();

  /**
   * Sets the value of the '{@link lib.Book#getTitle <em>Title</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Title</em>' attribute.
   * @see #getTitle()
   * @generated
   */
  void setTitle(String value);

} // Book
