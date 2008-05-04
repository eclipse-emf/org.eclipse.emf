/**
 * <copyright>
 * </copyright>
 *
 * $Id: GenPropertyKind.java,v 1.4 2008/05/04 17:03:27 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Gen Property Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPropertyKind()
 * @model
 * @generated
 */
public enum GenPropertyKind implements Enumerator
{
  /**
   * The '<em><b>Editable</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @see #EDITABLE
   * @generated
   * @ordered
   */
  EDITABLE_LITERAL(0, "Editable", "Editable"),
  /**
   * The '<em><b>Readonly</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @see #READONLY
   * @generated
   * @ordered
   */
  READONLY_LITERAL(1, "Readonly", "Readonly"),
  /**
   * The '<em><b>None</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @see #NONE
   * @generated
   * @ordered
   */
  NONE_LITERAL(2, "None", "None");
  /**
   * The '<em><b>Editable</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @see #EDITABLE_LITERAL
   * @model name="Editable"
   * @generated
   * @ordered
   */
  public static final int EDITABLE = 0;

  /**
   * The '<em><b>Readonly</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @see #READONLY_LITERAL
   * @model name="Readonly"
   * @generated
   * @ordered
   */
  public static final int READONLY = 1;

  /**
   * The '<em><b>None</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @see #NONE_LITERAL
   * @model name="None"
   * @generated
   * @ordered
   */
  public static final int NONE = 2;

  /**
   * An array of all the '<em><b>Gen Property Kind</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final GenPropertyKind[] VALUES_ARRAY =
    new GenPropertyKind[]
    {
      EDITABLE_LITERAL,
      READONLY_LITERAL,
      NONE_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Gen Property Kind</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List<GenPropertyKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Gen Property Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static GenPropertyKind get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenPropertyKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen Property Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GenPropertyKind getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenPropertyKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen Property Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static GenPropertyKind get(int value)
  {
    switch (value)
    {
      case EDITABLE: return EDITABLE_LITERAL;
      case READONLY: return READONLY_LITERAL;
      case NONE: return NONE_LITERAL;
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
  private GenPropertyKind(int value, String name, String literal)
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
