/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
  JDK60_LITERAL(2, "JDK60", "6.0"), /**
   * The '<em><b>JDK70</b></em>' literal object.
   * <!-- begin-user-doc -->
   * @since 2.8
   * <!-- end-user-doc -->
   * @see #JDK70
   * @generated
   * @ordered
   */
  JDK70_LITERAL(3, "JDK70", "7.0");
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
