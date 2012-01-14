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
 * A representation of the literals of the enumeration '<em><b>XPath Variety</b></em>'.
 * @see org.eclipse.xsd.XSDXPathDefinition#getVariety()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDXPathVariety()
 * @model
 * @generated
 */
public enum XSDXPathVariety implements Enumerator
{
  /**
   * The '<em><b>Selector</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #SELECTOR
   * @generated
   * @ordered
   */
  SELECTOR_LITERAL(0, "selector", "selector"),

  /**
   * The '<em><b>Field</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #FIELD
   * @generated
   * @ordered
   */
  FIELD_LITERAL(1, "field", "field");

  /**
   * The '<em><b>Selector</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that the XPath definition is a <a href="http://www.w3.org/TR/xmlschema-1/#element-selector">selector</a>.
   * </p>
   * <!-- end-user-doc --> 
   * @see #SELECTOR_LITERAL
   * @model name="selector"
   * @generated
   * @ordered
   */
  public static final int SELECTOR = 0;

  /**
   * The '<em><b>Field</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that the XPath definition is a <a href="http://www.w3.org/TR/xmlschema-1/#element-field">field</a>.
   * </p>
   * <!-- end-user-doc --> 
   * @see #FIELD_LITERAL
   * @model name="field"
   * @generated
   * @ordered
   */
  public static final int FIELD = 1;

  /**
   * An array of all the '<em><b>XPath Variety</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDXPathVariety[] VALUES_ARRAY =
    new XSDXPathVariety[]
    {
      SELECTOR_LITERAL,
      FIELD_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>XPath Variety</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List<XSDXPathVariety> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>XPath Variety</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDXPathVariety get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDXPathVariety result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>XPath Variety</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XSDXPathVariety getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDXPathVariety result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>XPath Variety</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDXPathVariety get(int value)
  {
    switch (value)
    {
      case SELECTOR: return SELECTOR_LITERAL;
      case FIELD: return FIELD_LITERAL;
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
  private XSDXPathVariety(int value, String name, String literal)
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
