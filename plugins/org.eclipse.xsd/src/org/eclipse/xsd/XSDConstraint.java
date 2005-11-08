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
 * $Id: XSDConstraint.java,v 1.3 2005/11/08 13:52:50 emerks Exp $
 */
package org.eclipse.xsd;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;


/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Constraint</b></em>'.
 * @see org.eclipse.xsd.XSDAttributeUse#getConstraint()
 * @see org.eclipse.xsd.XSDAttributeDeclaration#getConstraint()
 * @see org.eclipse.xsd.XSDElementDeclaration#getConstraint()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDConstraint()
 * @model
 * @generated
 */
public final class XSDConstraint extends AbstractEnumerator
{
  /**
   * The '<em><b>Default</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that an attribute use defaults to its constrained value.
   * </p>
   * <!-- end-user-doc --> 
   * @see #DEFAULT_LITERAL
   * @model name="default"
   * @generated
   * @ordered
   */
  public static final int DEFAULT = 0;

  /**
   * The '<em><b>Fixed</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that an attribute use is fixed to its constrained value.
   * </p>
   * <!-- end-user-doc --> 
   * @see #FIXED_LITERAL
   * @model name="fixed"
   * @generated
   * @ordered
   */
  public static final int FIXED = 1;
  /**
   * The '<em><b>Default</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #DEFAULT
   * @generated
   * @ordered
   */
  public static final XSDConstraint DEFAULT_LITERAL = new XSDConstraint(DEFAULT, "default", "default");

  /**
   * The '<em><b>Fixed</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #FIXED
   * @generated
   * @ordered
   */
  public static final XSDConstraint FIXED_LITERAL = new XSDConstraint(FIXED, "fixed", "fixed");

  /**
   * An array of all the '<em><b>Constraint</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDConstraint[] VALUES_ARRAY =
    new XSDConstraint[]
    {
      DEFAULT_LITERAL,
      FIXED_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Constraint</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Constraint</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDConstraint get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDConstraint result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Constraint</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XSDConstraint getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDConstraint result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Constraint</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDConstraint get(int value)
  {
    switch (value)
    {
      case DEFAULT: return DEFAULT_LITERAL;
      case FIXED: return FIXED_LITERAL;
    }
    return null;	
  }

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private XSDConstraint(int value, String name, String literal)
  {
    super(value, name, literal);
  }

} 
