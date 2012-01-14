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
 * A representation of the literals of the enumeration '<em><b>Compositor</b></em>'.
 * @see org.eclipse.xsd.XSDModelGroup#getCompositor()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDCompositor()
 * @model
 * @generated
 */
public enum XSDCompositor implements Enumerator
{
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
  ALL_LITERAL(0, "all", "all"),

  /**
   * The '<em><b>Choice</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #CHOICE
   * @generated
   * @ordered
   */
  CHOICE_LITERAL(1, "choice", "choice"),

  /**
   * The '<em><b>Sequence</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #SEQUENCE
   * @generated
   * @ordered
   */
  SEQUENCE_LITERAL(2, "sequence", "sequence");

  /**
   * The '<em><b>All</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that a model group is an <a href="http://www.w3.org/TR/xmlschema-1/#element-all">all</a>.
   * </p>
   * <!-- end-user-doc --> 
   * @see #ALL_LITERAL
   * @model name="all"
   * @generated
   * @ordered
   */
  public static final int ALL = 0;

  /**
   * The '<em><b>Choice</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that a model group is a <a href="http://www.w3.org/TR/xmlschema-1/#element-choice">choice</a>.
   * </p>
   * <!-- end-user-doc --> 
   * @see #CHOICE_LITERAL
   * @model name="choice"
   * @generated
   * @ordered
   */
  public static final int CHOICE = 1;

  /**
   * The '<em><b>Sequence</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that a model group is a <a href="http://www.w3.org/TR/xmlschema-1/#element-sequence">sequence</a>.
   * </p>
   * <!-- end-user-doc --> 
   * @see #SEQUENCE_LITERAL
   * @model name="sequence"
   * @generated
   * @ordered
   */
  public static final int SEQUENCE = 2;

  /**
   * An array of all the '<em><b>Compositor</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDCompositor[] VALUES_ARRAY =
    new XSDCompositor[]
    {
      ALL_LITERAL,
      CHOICE_LITERAL,
      SEQUENCE_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Compositor</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List<XSDCompositor> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Compositor</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDCompositor get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDCompositor result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Compositor</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XSDCompositor getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDCompositor result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Compositor</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDCompositor get(int value)
  {
    switch (value)
    {
      case ALL: return ALL_LITERAL;
      case CHOICE: return CHOICE_LITERAL;
      case SEQUENCE: return SEQUENCE_LITERAL;
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
  private XSDCompositor(int value, String name, String literal)
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
