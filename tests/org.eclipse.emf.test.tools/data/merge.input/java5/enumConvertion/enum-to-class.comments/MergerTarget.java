/**
 *
 */
package org.eclipse.example.library;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * User comment will be removed.
 * <!-- begin-user-doc -->
 * User comment will be kept.
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
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
   * <!-- end-user-doc -->
   * @see #MYSTERY
   * @generated
   * @ordered
   */
  MYSTERY_LITERAL(0, "Mystery", "Mystery"),

  /**
   * The '<em><b>Science Fiction</b></em>' literal object.
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
   * <!-- end-user-doc -->
   * @see #SCIENCE_FICTION
   * @generated
   * @ordered
   */
  SCIENCE_FICTION_LITERAL(1, "ScienceFiction", "ScienceFiction"),

  /**
   * The '<em><b>Biography</b></em>' literal object.
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
   * <!-- end-user-doc -->
   * @see #BIOGRAPHY
   * @generated
   * @ordered
   */
  BIOGRAPHY_LITERAL(2, "Biography", "Biography");

  /**
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String copyright = "";

  /**
   * The '<em><b>Mystery</b></em>' literal value.
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
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
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
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
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
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
   * An array of all the '<em><b>Book Category</b></em>' enumerators.
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
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
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<BookCategory> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Book Category</b></em>' literal with the specified literal value.
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
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
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
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
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
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
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
   * <!-- end-user-doc -->
   * @generated
   */
  private final int value;

  /**
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
   * <!-- end-user-doc -->
   * @generated
   */
  private final String name;

  /**
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
   * <!-- end-user-doc -->
   * @generated
   */
  private final String literal;

  /**
   * Only this class can construct instances.
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
   * <!-- end-user-doc -->
   * @generated
   */
  private BookCategory(int value, String name, String literal)
  {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

  /**
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
   * <!-- end-user-doc -->
   * @generated
   */
  public int getValue()
  {
    return value;
  }

  /**
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLiteral()
  {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation.
   * User comment will be removed.
   * <!-- begin-user-doc -->
   * User comment will be kept.
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    return literal;
  }
  
} //BookCategory
