/**
 * <copyright>
 * </copyright>
 *
 * $Id: USState.java,v 1.2 2006/12/29 21:49:53 marcelop Exp $
 */
package org.eclipse.emf.test.models.customer;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>US State</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.customer.CustomerPackage#getUSState()
 * @model
 * @generated
 */
public enum USState implements Enumerator
{
  /**
   * The '<em><b>AK</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #AK
   * @generated
   * @ordered
   */
  AK_LITERAL(0, "AK", "AK"),
  /**
   * The '<em><b>AL</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #AL
   * @generated
   * @ordered
   */
  AL_LITERAL(1, "AL", "AL"),
  /**
   * The '<em><b>AR</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #AR
   * @generated
   * @ordered
   */
  AR_LITERAL(2, "AR", "AR");
  /**
   * The '<em><b>AK</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>AK</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #AK_LITERAL
   * @model
   * @generated
   * @ordered
   */
  public static final int AK = 0;

  /**
   * The '<em><b>AL</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>AL</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #AL_LITERAL
   * @model
   * @generated
   * @ordered
   */
  public static final int AL = 1;

  /**
   * The '<em><b>AR</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>AR</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #AR_LITERAL
   * @model
   * @generated
   * @ordered
   */
  public static final int AR = 2;

  /**
   * An array of all the '<em><b>US State</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final USState[] VALUES_ARRAY =
    new USState[]
    {
      AK_LITERAL,
      AL_LITERAL,
      AR_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>US State</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<USState> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>US State</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static USState get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      USState result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>US State</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static USState getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      USState result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>US State</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static USState get(int value)
  {
    switch (value)
    {
      case AK: return AK_LITERAL;
      case AL: return AL_LITERAL;
      case AR: return AR_LITERAL;
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
  private USState(int value, String name, String literal)
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
}
