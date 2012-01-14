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
 * A representation of the literals of the enumeration '<em><b>Content Type Category</b></em>'.
 * @see org.eclipse.xsd.XSDComplexTypeDefinition#getContentTypeCategory()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDContentTypeCategory()
 * @model
 * @generated
 */
public enum XSDContentTypeCategory implements Enumerator
{
  /**
   * The '<em><b>Empty</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #EMPTY
   * @generated
   * @ordered
   */
  EMPTY_LITERAL(0, "empty", "empty"),

  /**
   * The '<em><b>Simple</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #SIMPLE
   * @generated
   * @ordered
   */
  SIMPLE_LITERAL(1, "simple", "simple"),

  /**
   * The '<em><b>Mixed</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #MIXED
   * @generated
   * @ordered
   */
  MIXED_LITERAL(2, "mixed", "mixed"),

  /**
   * The '<em><b>Element Only</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #ELEMENT_ONLY
   * @generated
   * @ordered
   */
  ELEMENT_ONLY_LITERAL(3, "elementOnly", "elementOnly");
  /**
   * The '<em><b>Empty</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that a complex type definition has empty content.
   * </p>
   * <!-- end-user-doc --> 
   * @see #EMPTY_LITERAL
   * @model name="empty"
   * @generated
   * @ordered
   */
  public static final int EMPTY = 0;

  /**
   * The '<em><b>Simple</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that a complex type definition has simple content.
   * </p>
   * <!-- end-user-doc --> 
   * @see #SIMPLE_LITERAL
   * @model name="simple"
   * @generated
   * @ordered
   */
  public static final int SIMPLE = 1;

  /**
   * The '<em><b>Mixed</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that a complex type definition has mixed content.
   * </p>
   * <!-- end-user-doc --> 
   * @see #MIXED_LITERAL
   * @model name="mixed"
   * @generated
   * @ordered
   */
  public static final int MIXED = 2;

  /**
   * The '<em><b>Element Only</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that a complex type definition has element-only content.
   * </p>
   * <!-- end-user-doc --> 
   * @see #ELEMENT_ONLY_LITERAL
   * @model name="elementOnly"
   * @generated
   * @ordered
   */
  public static final int ELEMENT_ONLY = 3;

  /**
   * An array of all the '<em><b>Content Type Category</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDContentTypeCategory[] VALUES_ARRAY =
    new XSDContentTypeCategory[]
    {
      EMPTY_LITERAL,
      SIMPLE_LITERAL,
      MIXED_LITERAL,
      ELEMENT_ONLY_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Content Type Category</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List<XSDContentTypeCategory> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Content Type Category</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDContentTypeCategory get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDContentTypeCategory result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Content Type Category</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XSDContentTypeCategory getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDContentTypeCategory result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Content Type Category</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDContentTypeCategory get(int value)
  {
    switch (value)
    {
      case EMPTY: return EMPTY_LITERAL;
      case SIMPLE: return SIMPLE_LITERAL;
      case MIXED: return MIXED_LITERAL;
      case ELEMENT_ONLY: return ELEMENT_ONLY_LITERAL;
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
  private XSDContentTypeCategory(int value, String name, String literal)
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
