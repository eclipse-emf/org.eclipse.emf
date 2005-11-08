/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDXPathVariety.java,v 1.3 2005/11/08 13:52:50 emerks Exp $
 */
package org.eclipse.xsd;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;


/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>XPath Variety</b></em>'.
 * @see org.eclipse.xsd.XSDXPathDefinition#getVariety()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDXPathVariety()
 * @model
 * @generated
 */
public final class XSDXPathVariety extends AbstractEnumerator
{
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
   * The '<em><b>Selector</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #SELECTOR
   * @generated
   * @ordered
   */
  public static final XSDXPathVariety SELECTOR_LITERAL = new XSDXPathVariety(SELECTOR, "selector", "selector");

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
  public static final XSDXPathVariety FIELD_LITERAL = new XSDXPathVariety(FIELD, "field", "field");

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
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

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
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private XSDXPathVariety(int value, String name, String literal)
  {
    super(value, name, literal);
  }

}
