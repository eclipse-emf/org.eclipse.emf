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
 * A representation of the literals of the enumeration '<em><b>Attribute Use Category</b></em>'.
 * @see org.eclipse.xsd.XSDAttributeUse#getUse() 
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDAttributeUseCategory()
 * @model
 * @generated
 */
public enum XSDAttributeUseCategory implements Enumerator
{
  /**
   * The '<em><b>Optional</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #OPTIONAL
   * @generated
   * @ordered
   */
  OPTIONAL_LITERAL(0, "optional", "optional"),

  /**
   * The '<em><b>Prohibited</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #PROHIBITED
   * @generated
   * @ordered
   */
  PROHIBITED_LITERAL(1, "prohibited", "prohibited"),

  /**
   * The '<em><b>Required</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #REQUIRED
   * @generated
   * @ordered
   */
  REQUIRED_LITERAL(2, "required", "required");

  /**
   * The '<em><b>Optional</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that an attribute use <b>may</b> appear.
   * </p>
   * <!-- end-user-doc --> 
   * @see #OPTIONAL_LITERAL
   * @model name="optional"
   * @generated
   * @ordered
   */

  public static final int OPTIONAL = 0;

  /**
   * The '<em><b>Prohibited</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that an attribute use <b>must not</b> appear;
   * it acts to hide attribute uses that may have been inherited.
   * </p>
   * <!-- end-user-doc --> 
   * @see #PROHIBITED_LITERAL
   * @model name="prohibited"
   * @generated
   * @ordered
   */
  public static final int PROHIBITED = 1;

  /**
   * The '<em><b>Required</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that an attribute use <b>must</b> appear.
   * </p>
   * <!-- end-user-doc --> 
   * @see #REQUIRED_LITERAL
   * @model name="required"
   * @generated
   * @ordered
   */
  public static final int REQUIRED = 2;

  /**
   * An array of all the '<em><b>Attribute Use Category</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDAttributeUseCategory[] VALUES_ARRAY =
    new XSDAttributeUseCategory[]
    {
      OPTIONAL_LITERAL,
      PROHIBITED_LITERAL,
      REQUIRED_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Attribute Use Category</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List<XSDAttributeUseCategory> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Attribute Use Category</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDAttributeUseCategory get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDAttributeUseCategory result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Attribute Use Category</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XSDAttributeUseCategory getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDAttributeUseCategory result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Attribute Use Category</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDAttributeUseCategory get(int value)
  {
    switch (value)
    {
      case OPTIONAL: return OPTIONAL_LITERAL;
      case PROHIBITED: return PROHIBITED_LITERAL;
      case REQUIRED: return REQUIRED_LITERAL;
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
  private XSDAttributeUseCategory(int value, String name, String literal)
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
