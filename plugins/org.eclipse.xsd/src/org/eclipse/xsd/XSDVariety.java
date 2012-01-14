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
 * A representation of the literals of the enumeration '<em><b>Variety</b></em>'.
 * @see org.eclipse.xsd.XSDSimpleTypeDefinition#getVariety()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDVariety()
 * @model
 * @generated
 */
public enum XSDVariety implements Enumerator
{
  /**
   * The '<em><b>Atomic</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #ATOMIC
   * @generated
   * @ordered
   */
  ATOMIC_LITERAL(0, "atomic", "atomic"),

  /**
   * The '<em><b>List</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #LIST
   * @generated
   * @ordered
   */
  LIST_LITERAL(1, "list", "list"),

  /**
   * The '<em><b>Union</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #UNION
   * @generated
   * @ordered
   */
  UNION_LITERAL(2, "union", "union");

  /**
   * The '<em><b>Atomic</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that a simple type definition is <a href="http://www.w3.org/TR/xmlschema-2/#dt-atomic">atomic</a>.
   * </p>
   * <!-- end-user-doc --> 
   * @see #ATOMIC_LITERAL
   * @model name="atomic"
   * @generated
   * @ordered
   */
  public static final int ATOMIC = 0;

  /**
   * The '<em><b>List</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that a simple type definition is a <a href="http://www.w3.org/TR/xmlschema-2/#dt-list">list</a>.
   * </p>
   * <!-- end-user-doc --> 
   * @see #LIST_LITERAL
   * @model name="list"
   * @generated
   * @ordered
   */
  public static final int LIST = 1;

  /**
   * The '<em><b>Union</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that a simple type definition is a <a href="http://www.w3.org/TR/xmlschema-2/#dt-union">union</a>.
   * </p>
   * <!-- end-user-doc --> 
   * @see #UNION_LITERAL
   * @model name="union"
   * @generated
   * @ordered
   */
  public static final int UNION = 2;

  /**
   * An array of all the '<em><b>Variety</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDVariety[] VALUES_ARRAY =
    new XSDVariety[]
    {
      ATOMIC_LITERAL,
      LIST_LITERAL,
      UNION_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Variety</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List<XSDVariety> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Variety</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDVariety get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDVariety result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Variety</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XSDVariety getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDVariety result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Variety</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDVariety get(int value)
  {
    switch (value)
    {
      case ATOMIC: return ATOMIC_LITERAL;
      case LIST: return LIST_LITERAL;
      case UNION: return UNION_LITERAL;
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
  private XSDVariety(int value, String name, String literal)
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
