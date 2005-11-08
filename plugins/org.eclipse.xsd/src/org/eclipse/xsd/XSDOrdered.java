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
 * $Id: XSDOrdered.java,v 1.3 2005/11/08 13:52:50 emerks Exp $
 */
package org.eclipse.xsd;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;


/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Ordered</b></em>'.
 * @see org.eclipse.xsd.XSDOrderedFacet#getValue()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDOrdered()
 * @model
 * @generated
 */
public final class XSDOrdered extends AbstractEnumerator
{
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
   * The '<em><b>False</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #FALSE
   * @generated
   * @ordered
   */
  public static final XSDOrdered FALSE_LITERAL = new XSDOrdered(FALSE, "false", "false");

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
  public static final XSDOrdered PARTIAL_LITERAL = new XSDOrdered(PARTIAL, "partial", "partial");

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
  public static final XSDOrdered TOTAL_LITERAL = new XSDOrdered(TOTAL, "total", "total");

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
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

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
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private XSDOrdered(int value, String name, String literal)
  {
    super(value, name, literal);
  }

}
