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
 * A representation of the literals of the enumeration '<em><b>Simple Final</b></em>'.
 * @see org.eclipse.xsd.XSDSimpleTypeDefinition#getFinal()
 * @see org.eclipse.xsd.XSDSimpleTypeDefinition#getLexicalFinal()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDSimpleFinal()
 * @model
 * @generated
 */
public enum XSDSimpleFinal implements Enumerator
{
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
  LIST_LITERAL(0, "list", "list"),

  /**
   * The '<em><b>Restriction</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #RESTRICTION
   * @generated
   * @ordered
   */
  RESTRICTION_LITERAL(1, "restriction", "restriction"),

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
  UNION_LITERAL(2, "union", "union"),

  /**
   * The '<em><b>All</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #ALL
   * @generated
   * @ordered
   */
  ALL_LITERAL(3, "all", "all");

  /**
   * The '<em><b>List</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that no derivation via list is allowed for a simple type definition.
   * </p>
   * <!-- end-user-doc --> 
   * @see #LIST_LITERAL
   * @model name="list"
   * @generated
   * @ordered
   */
  public static final int LIST = 0;

  /**
   * The '<em><b>Restriction</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that no derivation via restriction is allowed for a simple type definition.
   * </p>
   * <!-- end-user-doc --> 
   * @see #RESTRICTION_LITERAL
   * @model name="restriction"
   * @generated
   * @ordered
   */
  public static final int RESTRICTION = 1;

  /**
   * The '<em><b>Union</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that no derivation via union is allowed for a simple type definition.
   * </p>
   * <!-- end-user-doc --> 
   * @see #UNION_LITERAL
   * @model name="union"
   * @generated
   * @ordered
   */
  public static final int UNION = 2;

  /**
   * The '<em><b>All</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that no derivation is allowed for a simple type definition.
   * It is literally expanded to list, restriction, and union in the infoset model.
   * </p>
   * <!-- end-user-doc --> 
   * @see #ALL_LITERAL
   * @model name="all"
   * @generated
   * @ordered
   */
  public static final int ALL = 3;

  /**
   * An array of all the '<em><b>Simple Final</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDSimpleFinal[] VALUES_ARRAY =
    new XSDSimpleFinal[]
    {
      LIST_LITERAL,
      RESTRICTION_LITERAL,
      UNION_LITERAL,
      ALL_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Simple Final</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List<XSDSimpleFinal> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Simple Final</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDSimpleFinal get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDSimpleFinal result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Simple Final</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XSDSimpleFinal getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDSimpleFinal result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Simple Final</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDSimpleFinal get(int value)
  {
    switch (value)
    {
      case LIST: return LIST_LITERAL;
      case RESTRICTION: return RESTRICTION_LITERAL;
      case UNION: return UNION_LITERAL;
      case ALL: return ALL_LITERAL;
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
  private XSDSimpleFinal(int value, String name, String literal)
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
