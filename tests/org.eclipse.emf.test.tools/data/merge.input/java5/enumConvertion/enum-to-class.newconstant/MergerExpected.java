/**
 *
 */
package org.eclipse.example.library;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Book Category</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.example.library.LibraryPackage#getBookCategory()
 * @model
 * @generated
 */
public final class BookCategory extends AbstractEnumerator
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String copyright = "";

  /**
   * The '<em><b>Mystery</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Mystery</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #MYSTERY_LITERAL
   * @model name="Mystery"
   * @generated
   * @ordered
   */
  public static final int MYSTERY = 0;

  /**
   * The '<em><b>Science Fiction</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Science Fiction</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SCIENCE_FICTION_LITERAL
   * @model name="ScienceFiction"
   * @generated
   * @ordered
   */
  public static final int SCIENCE_FICTION = 1;

  /**
   * The '<em><b>Biography</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Biography</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #BIOGRAPHY_LITERAL
   * @model name="Biography"
   * @generated
   * @ordered
   */
  public static final int BIOGRAPHY = 2;

  /**
   * The '<em><b>Biography</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Biography</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #BIOGRAPHY2_LITERAL
   * @model name="Biography"
   * @generated NOT
   * @ordered
   */
  @Deprecated
  @RequestForEnhancement(
    "Biography2" // line comment
  )
  public static final int BIOGRAPHY2 = 3;

  /**
   * The '<em><b>Mystery</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MYSTERY
   * @generated
   * @ordered
   */
  public static final BookCategory MYSTERY_LITERAL = new BookCategory(MYSTERY, "Mystery", "Mystery");

  /**
   * The '<em><b>Science Fiction</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SCIENCE_FICTION
   * @generated
   * @ordered
   */
  public static final BookCategory SCIENCE_FICTION_LITERAL = new BookCategory(SCIENCE_FICTION, "ScienceFiction", "ScienceFiction");

  /**
   * The '<em><b>Biography</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #BIOGRAPHY
   * @generated
   * @ordered
   */
  public static final BookCategory BIOGRAPHY_LITERAL = new BookCategory(BIOGRAPHY, "Biography", "Biography");

  /**
   * The '<em><b>Biography2</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #BIOGRAPHY2
   * @generated NOT
   * @ordered
   */
  @Deprecated
  @RequestForEnhancement(
    "Biography2 Literal" // line comment
  )
  public static final BookCategory BIOGRAPHY2_LITERAL = new BookCategory(BIOGRAPHY2, "Biography", "Biography") {
    // line comment in the body of the BIOGRAPHY2_LITERAL
    // another line comment
  };

  /**
   * An array of all the '<em><b>Book Category</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final BookCategory[] VALUES_ARRAY =
    new BookCategory[]
    {
      MYSTERY_LITERAL,
      SCIENCE_FICTION_LITERAL,
      BIOGRAPHY_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Book Category</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Book Category</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static BookCategory get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      BookCategory result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Book Category</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static BookCategory getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      BookCategory result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Book Category</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static BookCategory get(int value)
  {
    switch (value)
    {
      case MYSTERY: return MYSTERY_LITERAL;
      case SCIENCE_FICTION: return SCIENCE_FICTION_LITERAL;
      case BIOGRAPHY: return BIOGRAPHY_LITERAL;
    }
    return null;  
  }

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private BookCategory(int value, String name, String literal)
  {
    super(value, name, literal);
  }
}
