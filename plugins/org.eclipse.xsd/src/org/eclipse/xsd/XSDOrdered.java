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
 * A representation of the literals of the enumeration '<em><b>Ordered</b></em>'.
 * @see org.eclipse.xsd.XSDOrderedFacet#getValue()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDOrdered()
 * @model
 * @generated
 */
public enum XSDOrdered implements Enumerator
{
  /**
   * The '<em><b>False</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #FALSE
   * @generated
   * @ordered
   */
  FALSE_LITERAL(0, "false", "false"),
  /**
   * The '<em><b>Partial</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #PARTIAL
   * @generated
   * @ordered
   */
  PARTIAL_LITERAL(1, "partial", "partial"),
  /**
   * The '<em><b>Total</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #TOTAL
   * @generated
   * @ordered
   */
  TOTAL_LITERAL(2, "total", "total");
  /**
   * The '<em><b>False</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that a simple type definition's value space is unordered.
   * </p>
   * <!-- end-user-doc --> 
   * @see #FALSE_LITERAL
   * @model name="false"
   * @generated
   * @ordered
   */
  public static final int FALSE = 0;

  /**
   * The '<em><b>Partial</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that a simple type definition's value space is partially ordered.
   * </p>
   * <!-- end-user-doc --> 
   * @see #PARTIAL_LITERAL
   * @model name="partial"
   * @generated
   * @ordered
   */
  public static final int PARTIAL = 1;

  /**
   * The '<em><b>Total</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that a simple type definition's value space is ordered.
   * </p>
   * <!-- end-user-doc --> 
   * @see #TOTAL_LITERAL
   * @model name="total"
   * @generated
   * @ordered
   */
  public static final int TOTAL = 2;

  /**
   * An array of all the '<em><b>Ordered</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDOrdered[] VALUES_ARRAY =
    new XSDOrdered[]
    {
      FALSE_LITERAL,
      PARTIAL_LITERAL,
      TOTAL_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Ordered</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List<XSDOrdered> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Ordered</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDOrdered get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDOrdered result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Ordered</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XSDOrdered getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDOrdered result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Ordered</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDOrdered get(int value)
  {
    switch (value)
    {
      case FALSE: return FALSE_LITERAL;
      case PARTIAL: return PARTIAL_LITERAL;
      case TOTAL: return TOTAL_LITERAL;
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
  private XSDOrdered(int value, String name, String literal)
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
