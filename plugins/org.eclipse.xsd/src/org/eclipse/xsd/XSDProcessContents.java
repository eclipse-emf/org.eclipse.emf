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
 * A representation of the literals of the enumeration '<em><b>Process Contents</b></em>'.
 * @see org.eclipse.xsd.XSDWildcard#getProcessContents()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDProcessContents()
 * @model
 * @generated
 */
public enum XSDProcessContents implements Enumerator
{
  /**
   * The '<em><b>Strict</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #STRICT
   * @generated
   * @ordered
   */
  STRICT_LITERAL(0, "strict", "strict"),

  /**
   * The '<em><b>Lax</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #LAX
   * @generated
   * @ordered
   */
  LAX_LITERAL(1, "lax", "lax"),

  /**
   * The '<em><b>Skip</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #SKIP
   * @generated
   * @ordered
   */
  SKIP_LITERAL(2, "skip", "skip");

  /**
   * The '<em><b>Strict</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that content must be strictly valid.
   * </p>
   * <!-- end-user-doc --> 
   * @see #STRICT_LITERAL
   * @model name="strict"
   * @generated
   * @ordered
   */
  public static final int STRICT = 0;

  /**
   * The '<em><b>Lax</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that content may be strictly valid but need not be.
   * </p>
   * <!-- end-user-doc --> 
   * @see #LAX_LITERAL
   * @model name="lax"
   * @generated
   * @ordered
   */
  public static final int LAX = 1;

  /**
   * The '<em><b>Skip</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that content is not validated at all.
   * </p>
   * <!-- end-user-doc --> 
   * @see #SKIP_LITERAL
   * @model name="skip"
   * @generated
   * @ordered
   */
  public static final int SKIP = 2;

  /**
   * An array of all the '<em><b>Process Contents</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDProcessContents[] VALUES_ARRAY =
    new XSDProcessContents[]
    {
      STRICT_LITERAL,
      LAX_LITERAL,
      SKIP_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Process Contents</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List<XSDProcessContents> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Process Contents</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDProcessContents get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDProcessContents result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Process Contents</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XSDProcessContents getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDProcessContents result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Process Contents</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDProcessContents get(int value)
  {
    switch (value)
    {
      case STRICT: return STRICT_LITERAL;
      case LAX: return LAX_LITERAL;
      case SKIP: return SKIP_LITERAL;
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
  private XSDProcessContents(int value, String name, String literal)
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
