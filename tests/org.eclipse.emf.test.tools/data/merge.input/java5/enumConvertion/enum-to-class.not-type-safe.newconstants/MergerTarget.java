/**
 *
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
   * @see #MYSTERY_VALUE
   * @generated
   * @ordered
   */
  MYSTERY(0, "Mystery", "Mystery"),
  /**
   * The '<em><b>NewNovel</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NEW_NOVEL
   * @generated NOT
   * @ordered
   */
  @Deprecated
  @RequestForEnhancement(
    "NewNovel Literal" // line comment
  )  
  NEW_NOVEL(4, "NewNovel", "NewNovel") {
    // line comment in the body of the NEW_NOVEL
    // another line comment
  },
  /**
   * The '<em><b>Science Fiction</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SCIENCE_FICTION_VALUE
   * @generated
   * @ordered
   */
  SCIENCE_FICTION(1, "ScienceFiction", "ScienceFiction"),

  /**
   * The '<em><b>Biography</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #BIOGRAPHY_VALUE
   * @generated
   * @ordered
   */
  BIOGRAPHY(2, "Biography", "Biography"),
  
  /**
   * The '<em><b>NewArt</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NEW_ART
   * @generated NOT
   * @ordered
   */
  @Deprecated
  @RequestForEnhancement(
    "NewArt Literal" // line comment
  )  
  NEW_ART(3, "NewArt", "NewArt") {
    // line comment in the body of the NEW_ART
    // another line comment
  };

  /**
   * The '<em><b>Mystery</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Mystery</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #MYSTERY
   * @model name="Mystery"
   * @generated
   * @ordered
   */
  public static final int MYSTERY_VALUE = 0;

  /**
   * The '<em><b>NewNovel</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>NewNovel</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #NEW_NOVEL
   * @model name="NewNovel"
   * @generated NOT
   * @ordered
   */
  @Deprecated
  @RequestForEnhancement(
    "NewNovel" // line comment
  )  
  public static final int NEW_NOVEL_VALUE = NEW_NOVEL.getValue();  
  
  /**
   * The '<em><b>Science Fiction</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Science Fiction</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SCIENCE_FICTION
   * @model name="ScienceFiction"
   * @generated
   * @ordered
   */
  public static final int SCIENCE_FICTION_VALUE = 1;

  /**
   * The '<em><b>Biography</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Biography</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #BIOGRAPHY
   * @model name="Biography"
   * @generated
   * @ordered
   */
  public static final int BIOGRAPHY_VALUE = 2;
  
  /**
   * The '<em><b>Biography</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Biography</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #NEW_ART
   * @model name="Biography"
   * @generated NOT
   * @ordered
   */
  @Deprecated
  @RequestForEnhancement(
    "NewArt" // line comment
  )
  public static final int NEW_ART_VALUE = NEW_ART.getValue();      
  
  /**
   * An array of all the '<em><b>Book Category</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final BookCategory[] VALUES_ARRAY =
    new BookCategory[]
    {
      MYSTERY,
      SCIENCE_FICTION,
      BIOGRAPHY,
    };

  /**
   * A public read-only list of all the '<em><b>Book Category</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<BookCategory> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

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
      case MYSTERY_VALUE: return MYSTERY;
      case SCIENCE_FICTION_VALUE: return SCIENCE_FICTION;
      case BIOGRAPHY_VALUE: return BIOGRAPHY;
    }
    return null;  
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final int value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String name;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String literal;

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLiteral()
  {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    return literal;
  }
  
} //BookCategory
