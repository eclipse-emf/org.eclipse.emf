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
 * $Id: XSDCompositor.java,v 1.2 2005/06/08 06:23:01 nickb Exp $
 */
package org.eclipse.xsd;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;


/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Compositor</b></em>'.
 * @see org.eclipse.xsd.XSDModelGroup#getCompositor()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDCompositor()
 * @model
 * @generated
 */
public final class XSDCompositor extends AbstractEnumerator
{
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
   * The '<em><b>All</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #ALL
   * @generated
   * @ordered
   */
  public static final XSDCompositor ALL_LITERAL = new XSDCompositor(ALL, "all");

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
  public static final XSDCompositor CHOICE_LITERAL = new XSDCompositor(CHOICE, "choice");

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
  public static final XSDCompositor SEQUENCE_LITERAL = new XSDCompositor(SEQUENCE, "sequence");

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
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Compositor</b></em>' literal with the specified name.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDCompositor get(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDCompositor result = VALUES_ARRAY[i];
      if (result.toString().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Compositor</b></em>' literal with the specified value.
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
   * Only this class can construct instances.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private XSDCompositor(int value, String name)
  {
    super(value, name);
  }

} 
