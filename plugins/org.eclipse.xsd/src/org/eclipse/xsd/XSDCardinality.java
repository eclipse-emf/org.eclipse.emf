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
 * $Id: XSDCardinality.java,v 1.3 2005/11/08 13:52:50 emerks Exp $
 */
package org.eclipse.xsd;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;


/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Cardinality</b></em>'.
 * @see org.eclipse.xsd.XSDCardinalityFacet#getValue()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDCardinality()
 * @model
 * @generated
 */
public final class XSDCardinality extends AbstractEnumerator
{
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
   * The '<em><b>Finite</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #FINITE
   * @generated
   * @ordered
   */
  public static final XSDCardinality FINITE_LITERAL = new XSDCardinality(FINITE, "finite", "finite");

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
  public static final XSDCardinality COUNTABLY_INFINITE_LITERAL = new XSDCardinality(COUNTABLY_INFINITE, "countablyInfinite", "countablyInfinite");

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
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

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
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private XSDCardinality(int value, String name, String literal)
  {
    super(value, name, literal);
  }

}
