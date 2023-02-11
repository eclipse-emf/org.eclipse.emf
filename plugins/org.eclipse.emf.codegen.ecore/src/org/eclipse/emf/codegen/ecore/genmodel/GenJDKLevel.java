/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Gen JDK Level</b></em>',
 * and utility methods for working with them.
 * @since 2.3
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenJDKLevel()
 * @model
 * @generated
 */
public enum GenJDKLevel implements Enumerator
{
  /**
   * The '<em><b>JDK14</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #JDK14
   * @generated
   * @ordered
   */
  JDK14_LITERAL(0, "JDK14", "1.4"),

  /**
   * The '<em><b>JDK50</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #JDK50
   * @generated
   * @ordered
   */
  JDK50_LITERAL(1, "JDK50", "5.0"),

  /**
   * The '<em><b>JDK60</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #JDK60
   * @generated
   * @ordered
   */
  JDK60_LITERAL(2, "JDK60", "6.0"),

  /**
   * The '<em><b>JDK70</b></em>' literal object.
   * <!-- begin-user-doc -->
   * @since 2.8
   * <!-- end-user-doc -->
   * @see #JDK70
   * @generated
   * @ordered
   */
  JDK70_LITERAL(3, "JDK70", "7.0"),

  /**
   * The '<em><b>JDK80</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #JDK80
   * @generated
   * @ordered
   */
  JDK80_LITERAL(4, "JDK80", "8.0"),

  /**
   * The '<em><b>JDK90</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.14
   * <!-- end-model-doc -->
   * @see #JDK90
   * @generated
   * @ordered
   */
  JDK90_LITERAL(5, "JDK90", "9.0"),

  /**
   * The '<em><b>JDK100</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.14
   * <!-- end-model-doc -->
   * @see #JDK100
   * @generated
   * @ordered
   */
  JDK100_LITERAL(6, "JDK100", "10.0"),

  /**
   * The '<em><b>JDK110</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.16
   * <!-- end-model-doc -->
   * @see #JDK110
   * @generated
   * @ordered
   */
  JDK110_LITERAL(7, "JDK110", "11.0"),

  /**
   * The '<em><b>JDK120</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.17
   * <!-- end-model-doc -->
   * @see #JDK120
   * @generated
   * @ordered
   */

  JDK120_LITERAL(8, "JDK120", "12.0"),
  /**
   * The '<em><b>JDK130</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.19
   * <!-- end-model-doc -->
   * @see #JDK130
   * @generated
   * @ordered
   */
  JDK130_LITERAL(9, "JDK130", "13.0"),

  /**
   * The '<em><b>JDK140</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.21
   * <!-- end-model-doc -->
   * @see #JDK140
   * @generated
   * @ordered
   */
  JDK140_LITERAL(10, "JDK140", "14.0"), /**
   * The '<em><b>JDK150</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.22
   * <!-- end-model-doc -->
   * @see #JDK150
   * @generated
   * @ordered
   */
  JDK150_LITERAL(11, "JDK150", "15.0"),

  /**
   * The '<em><b>JDK160</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.24
   * <!-- end-model-doc -->
   * @see #JDK160
   * @generated
   * @ordered
   */
  JDK160_LITERAL(12, "JDK160", "16.0"),

  /**
   * The '<em><b>JDK170</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.25
   * <!-- end-model-doc -->
   * @see #JDK170
   * @generated
   * @ordered
   */
  JDK170_LITERAL(13, "JDK170", "17.0"),

  /**
   * The '<em><b>JDK180</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.27
   * <!-- end-model-doc -->
   * @see #JDK180
   * @generated
   * @ordered
   */
  JDK180_LITERAL(14, "JDK180", "18.0"),

  /**
   * The '<em><b>JDK190</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.30
   * <!-- end-model-doc -->
   * @see #JDK190
   * @generated
   * @ordered
   */
  JDK190_LITERAL(15, "JDK190", "19.0"),
  /**
   * The '<em><b>JDK200</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.32
   * <!-- end-model-doc -->
   * @see #JDK200
   * @generated
   * @ordered
   */
  JDK200_LITERAL(16, "JDK200", "20.0");

  /**
   * The '<em><b>JDK14</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #JDK14_LITERAL
   * @model literal="1.4"
   * @generated
   * @ordered
   */
  public static final int JDK14 = 0;

  /**
   * The '<em><b>JDK50</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #JDK50_LITERAL
   * @model literal="5.0"
   * @generated
   * @ordered
   */
  public static final int JDK50 = 1;

  /**
   * The '<em><b>JDK60</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #JDK60_LITERAL
   * @model literal="6.0"
   * @generated
   * @ordered
   */
  public static final int JDK60 = 2;

  /**
   * The '<em><b>JDK70</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * @since 2.8
   * </p>
   * <!-- end-user-doc -->
   * @see #JDK70_LITERAL
   * @model literal="7.0"
   * @generated
   * @ordered
   */
  public static final int JDK70 = 3;

  /**
   * The '<em><b>JDK80</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #JDK80_LITERAL
   * @model literal="8.0"
   * @generated
   * @ordered
   */
  public static final int JDK80 = 4;

  /**
   * The '<em><b>JDK90</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * </p>
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.14
   * <!-- end-model-doc -->
   * @see #JDK90_LITERAL
   * @model literal="9.0"
   * @generated
   * @ordered
   */
  public static final int JDK90 = 5;

  /**
   * The '<em><b>JDK100</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.14
   * <!-- end-model-doc -->
   * @see #JDK100_LITERAL
   * @model literal="10.0"
   * @generated
   * @ordered
   */
  public static final int JDK100 = 6;

  /**
   * The '<em><b>JDK110</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.16
   * <!-- end-model-doc -->
   * @see #JDK110_LITERAL
   * @model literal="11.0"
   * @generated
   * @ordered
   */
  public static final int JDK110 = 7;

  /**
   * The '<em><b>JDK120</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.17
   * <!-- end-model-doc -->
   * @see #JDK120_LITERAL
   * @model literal="12.0"
   * @generated
   * @ordered
   */
  public static final int JDK120 = 8;

  /**
   * The '<em><b>JDK130</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.19
   * <!-- end-model-doc -->
   * @see #JDK130_LITERAL
   * @model literal="13.0"
   * @generated
   * @ordered
   */
  public static final int JDK130 = 9;

  /**
   * The '<em><b>JDK140</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.21
   * <!-- end-model-doc -->
   * @see #JDK140_LITERAL
   * @model literal="14.0"
   * @generated
   * @ordered
   */
  public static final int JDK140 = 10;

  /**
   * The '<em><b>JDK150</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.22
   * <!-- end-model-doc -->
   * @see #JDK150_LITERAL
   * @model literal="15.0"
   * @generated
   * @ordered
   */
  public static final int JDK150 = 11;

  /**
   * The '<em><b>JDK160</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.24
   * <!-- end-model-doc -->
   * @see #JDK160_LITERAL
   * @model literal="16.0"
   * @generated
   * @ordered
   */
  public static final int JDK160 = 12;

  /**
   * The '<em><b>JDK170</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.25
   * <!-- end-model-doc -->
   * @see #JDK170_LITERAL
   * @model literal="17.0"
   * @generated
   * @ordered
   */
  public static final int JDK170 = 13;

  /**
   * The '<em><b>JDK180</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.27
   * <!-- end-model-doc -->
   * @see #JDK180_LITERAL
   * @model literal="18.0"
   * @generated
   * @ordered
   */
  public static final int JDK180 = 14;

  /**
   * The '<em><b>JDK190</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.30
   * <!-- end-model-doc -->
   * @see #JDK190_LITERAL
   * @model literal="19.0"
   * @generated
   * @ordered
   */
  public static final int JDK190 = 15;

  /**
   * The '<em><b>JDK200</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.32
   * <!-- end-model-doc -->
   * @see #JDK200_LITERAL
   * @model literal="20.0"
   * @generated
   * @ordered
   */
  public static final int JDK200 = 16;

  /**
   * An array of all the '<em><b>Gen JDK Level</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final GenJDKLevel[] VALUES_ARRAY =
    new GenJDKLevel[]
    {
      JDK14_LITERAL,
      JDK50_LITERAL,
      JDK60_LITERAL,
      JDK70_LITERAL,
      JDK80_LITERAL,
      JDK90_LITERAL,
      JDK100_LITERAL,
      JDK110_LITERAL,
      JDK120_LITERAL,
      JDK130_LITERAL,
      JDK140_LITERAL,
      JDK150_LITERAL,
      JDK160_LITERAL,
      JDK170_LITERAL,
      JDK180_LITERAL,
      JDK190_LITERAL,
      JDK200_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Gen JDK Level</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<GenJDKLevel> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Gen JDK Level</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static GenJDKLevel get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenJDKLevel result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen JDK Level</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static GenJDKLevel getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenJDKLevel result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen JDK Level</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static GenJDKLevel get(int value)
  {
    switch (value)
    {
      case JDK14: return JDK14_LITERAL;
      case JDK50: return JDK50_LITERAL;
      case JDK60: return JDK60_LITERAL;
      case JDK70: return JDK70_LITERAL;
      case JDK80: return JDK80_LITERAL;
      case JDK90: return JDK90_LITERAL;
      case JDK100: return JDK100_LITERAL;
      case JDK110: return JDK110_LITERAL;
      case JDK120: return JDK120_LITERAL;
      case JDK130: return JDK130_LITERAL;
      case JDK140: return JDK140_LITERAL;
      case JDK150: return JDK150_LITERAL;
      case JDK160: return JDK160_LITERAL;
      case JDK170: return JDK170_LITERAL;
      case JDK180: return JDK180_LITERAL;
      case JDK190: return JDK190_LITERAL;
      case JDK200: return JDK200_LITERAL;
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
  private GenJDKLevel(int value, String name, String literal)
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
   * Returns the corresponding OSGi Bundle-RequiredExecutionEnvironment value
   * @since 2.33
   */
  public String getExecutionEnvironment()
  {
    switch (this)
    {
      case JDK14_LITERAL:
      {
        return "J2SE-1.4";
      }
      case JDK50_LITERAL:
      {
        return "J2SE-1.5";
      }
      case JDK60_LITERAL:
      {
        return "JavaSE-1.6";
      }
      case JDK70_LITERAL:
      {
        return "JavaSE-1.7";
      }
      case JDK80_LITERAL:
      {
        return "JavaSE-1.8";
      }
      default:
      {
        return "JavaSE-" + (ordinal() - JDK90_LITERAL.ordinal() + 9);
      }
    }
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
