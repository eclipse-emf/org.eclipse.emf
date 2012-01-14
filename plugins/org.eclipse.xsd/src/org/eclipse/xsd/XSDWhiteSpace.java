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
 * A representation of the literals of the enumeration '<em><b>White Space</b></em>'.
 * @see org.eclipse.xsd.XSDWhiteSpaceFacet#getValue()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDWhiteSpace()
 * @model
 * @generated
 */
public enum XSDWhiteSpace implements Enumerator
{
  /**
   * The '<em><b>Preserve</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #PRESERVE
   * @generated
   * @ordered
   */
  PRESERVE_LITERAL(0, "preserve", "preserve"),

  /**
   * The '<em><b>Replace</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #REPLACE
   * @generated
   * @ordered
   */
  REPLACE_LITERAL(1, "replace", "replace"),

  /**
   * The '<em><b>Collapse</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #COLLAPSE
   * @generated
   * @ordered
   */
  COLLAPSE_LITERAL(2, "collapse", "collapse");

  /**
   * The '<em><b>Preserve</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate the white space is <a href="http://www.w3.org/TR/xmlschema-2/#dt-whitespace">preserved</a>.
   * </p>
   * <!-- end-user-doc --> 
   * @see #PRESERVE_LITERAL
   * @model name="preserve"
   * @generated
   * @ordered
   */
  public static final int PRESERVE = 0;

  /**
   * The '<em><b>Replace</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate the white space is <a href="http://www.w3.org/TR/xmlschema-2/#dt-whitespace">replaced</a>.
   * </p>
   * <!-- end-user-doc --> 
   * @see #REPLACE_LITERAL
   * @model name="replace"
   * @generated
   * @ordered
   */
  public static final int REPLACE = 1;

  /**
   * The '<em><b>Collapse</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate the white space is <a href="http://www.w3.org/TR/xmlschema-2/#dt-whitespace">collapsed</a>.
   * </p>
   * <!-- end-user-doc --> 
   * @see #COLLAPSE_LITERAL
   * @model name="collapse"
   * @generated
   * @ordered
   */
  public static final int COLLAPSE = 2;

  /**
   * An array of all the '<em><b>White Space</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDWhiteSpace[] VALUES_ARRAY =
    new XSDWhiteSpace[]
    {
      PRESERVE_LITERAL,
      REPLACE_LITERAL,
      COLLAPSE_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>White Space</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List<XSDWhiteSpace> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>White Space</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDWhiteSpace get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDWhiteSpace result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>White Space</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XSDWhiteSpace getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDWhiteSpace result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>White Space</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDWhiteSpace get(int value)
  {
    switch (value)
    {
      case PRESERVE: return PRESERVE_LITERAL;
      case REPLACE: return REPLACE_LITERAL;
      case COLLAPSE: return COLLAPSE_LITERAL;
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
  private XSDWhiteSpace(int value, String name, String literal)
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
