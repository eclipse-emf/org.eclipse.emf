/**
 */
package org.eclipse.example.library;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Book Category</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.example.library.LibraryPackage#getBookCategory()
 * @model
 * @generated
 */
public enum BookCategory implements Enumerator
{
  /**
   * The '<em><b>Mystery</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MYSTERY
   * @generated
   * @ordered
   */
  MYSTERY_LITERAL(0, "Mystery", "Mystery"),

  /**
   * The '<em><b>Science Fiction</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SCIENCE_FICTION
   * @generated
   * @ordered
   */
  SCIENCE_FICTION_LITERAL(1, "ScienceFiction", "ScienceFiction"),

  /**
   * The '<em><b>Biography</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #BIOGRAPHY
   * @generated
   * @ordered
   */
  BIOGRAPHY_LITERAL(2, "Biography", "Biography"), /**
   * The '<em><b>Short Stories</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SHORT_STORIES
   * @generated
   * @ordered
   */
  SHORT_STORIES_LITERAL(3, "ShortStories", "ShortStories"), /**
   * The '<em><b>Long Stories</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #LONG_STORIES
   * @generated
   * @ordered
   */
  LONG_STORIES_LITERAL(4, "LongStories", "LongStories");
}
