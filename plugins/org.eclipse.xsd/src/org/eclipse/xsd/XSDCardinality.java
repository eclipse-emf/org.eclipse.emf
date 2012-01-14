/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.xsd;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;


/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Cardinality</b></em>'.
 * @see org.eclipse.xsd.XSDCardinalityFacet#getValue()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDCardinality()
 * @model
 * @generated
 */
public enum XSDCardinality implements Enumerator
{
  /**
   * The '<em><b>Finite</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #FINITE
   * @generated
   * @ordered
   */
  FINITE_LITERAL(0, "finite", "finite"),

  /**
   * The '<em><b>Countably Infinite</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #COUNTABLY_INFINITE
   * @generated
   * @ordered
   */
  COUNTABLY_INFINITE_LITERAL(1, "countablyInfinite", "countablyInfinite");

  /**
   * The '<em><b>Finite</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that the value space of a simple type definition is finite.
   * </p>
   * <!-- end-user-doc --> 
   * @see #FINITE_LITERAL
   * @model name="finite"
   * @generated
   * @ordered
   */
  public static final int FINITE = 0;

  /**
   * The '<em><b>Countably Infinite</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that the value space of a simple type definition is countably infinite.
   * </p>
   * <!-- end-user-doc --> 
   * @see #COUNTABLY_INFINITE_LITERAL
   * @model name="countablyInfinite"
   * @generated
   * @ordered
   */
  public static final int COUNTABLY_INFINITE = 1;

  /**
   * An array of all the '<em><b>Cardinality</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDCardinality[] VALUES_ARRAY =
    new XSDCardinality[]
    {
      FINITE_LITERAL,
      COUNTABLY_INFINITE_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Cardinality</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List<XSDCardinality> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Cardinality</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDCardinality get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDCardinality result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Cardinality</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XSDCardinality getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDCardinality result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Cardinality</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDCardinality get(int value)
  {
    switch (value)
    {
      case FINITE: return FINITE_LITERAL;
      case COUNTABLY_INFINITE: return COUNTABLY_INFINITE_LITERAL;
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
  private XSDCardinality(int value, String name, String literal)
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
