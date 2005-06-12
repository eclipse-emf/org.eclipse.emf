/**
 * <copyright>
 * </copyright>
 *
 * $Id: Book.java,v 1.2 2005/06/12 14:07:23 emerks Exp $
 */
package org.eclipse.example.library;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Book</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.example.library.Book#getTitle <em>Title</em>}</li>
 *   <li>{@link org.eclipse.example.library.Book#getPages <em>Pages</em>}</li>
 *   <li>{@link org.eclipse.example.library.Book#getCategory <em>Category</em>}</li>
 *   <li>{@link org.eclipse.example.library.Book#getAuthor <em>Author</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.example.library.LibraryPackage#getBook()
 * @model
 * @generated
 */
public interface Book
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
   * @see org.eclipse.example.library.LibraryPackage#getBook_Title()
   * @model
   * @generated
   */
  String getTitle();

  /**
   * Sets the value of the '{@link org.eclipse.example.library.Book#getTitle <em>Title</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Title</em>' attribute.
   * @see #getTitle()
   * @generated
   */
  void setTitle(String value);

  /**
   * Returns the value of the '<em><b>Pages</b></em>' attribute.
   * The default value is <code>"100"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Pages</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Pages</em>' attribute.
   * @see #setPages(int)
   * @see org.eclipse.example.library.LibraryPackage#getBook_Pages()
   * @model default="100"
   * @generated
   */
  int getPages();

  /**
   * Sets the value of the '{@link org.eclipse.example.library.Book#getPages <em>Pages</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Pages</em>' attribute.
   * @see #getPages()
   * @generated
   */
  void setPages(int value);

  /**
   * Returns the value of the '<em><b>Category</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.example.library.BookCategory}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Category</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Category</em>' attribute.
   * @see org.eclipse.example.library.BookCategory
   * @see #setCategory(BookCategory)
   * @see org.eclipse.example.library.LibraryPackage#getBook_Category()
   * @model
   * @generated
   */
  BookCategory getCategory();

  /**
   * Sets the value of the '{@link org.eclipse.example.library.Book#getCategory <em>Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Category</em>' attribute.
   * @see org.eclipse.example.library.BookCategory
   * @see #getCategory()
   * @generated
   */
  void setCategory(BookCategory value);

  /**
   * Returns the value of the '<em><b>Author</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.example.library.Writer#getBooks <em>Books</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Author</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Author</em>' reference.
   * @see #setAuthor(Writer)
   * @see org.eclipse.example.library.LibraryPackage#getBook_Author()
   * @see org.eclipse.example.library.Writer#getBooks
   * @model opposite="books" required="true"
   * @generated
   */
  Writer getAuthor();

  /**
   * Sets the value of the '{@link org.eclipse.example.library.Book#getAuthor <em>Author</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Author</em>' reference.
   * @see #getAuthor()
   * @generated
   */
  void setAuthor(Writer value);

} // Book
