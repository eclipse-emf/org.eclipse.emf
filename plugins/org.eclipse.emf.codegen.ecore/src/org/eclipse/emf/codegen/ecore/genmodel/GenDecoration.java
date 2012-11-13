/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.codegen.ecore.genmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Gen Decoration</b></em>',
 * and utility methods for working with them.
 * @since 2.9
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenDecoration()
 * @model
 * @generated
 */
public enum GenDecoration implements Enumerator
{
  /**
   * The '<em><b>None</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NONE_VALUE
   * @generated
   * @ordered
   */
  NONE(0, "None", "None"),

  /**
   * The '<em><b>Manual</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MANUAL_VALUE
   * @generated
   * @ordered
   */
  MANUAL(1, "Manual", "Manual"),

  /**
   * The '<em><b>Live</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #LIVE_VALUE
   * @generated
   * @ordered
   */
  LIVE(2, "Live", "Live");

  /**
   * The '<em><b>None</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>None</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #NONE
   * @model name="None"
   * @generated
   * @ordered
   */
  public static final int NONE_VALUE = 0;

  /**
   * The '<em><b>Manual</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Manual</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #MANUAL
   * @model name="Manual"
   * @generated
   * @ordered
   */
  public static final int MANUAL_VALUE = 1;

  /**
   * The '<em><b>Live</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Live</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #LIVE
   * @model name="Live"
   * @generated
   * @ordered
   */
  public static final int LIVE_VALUE = 2;

  /**
   * An array of all the '<em><b>Gen Decoration</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final GenDecoration[] VALUES_ARRAY =
    new GenDecoration[]
    {
      NONE,
      MANUAL,
      LIVE,
    };

  /**
   * A public read-only list of all the '<em><b>Gen Decoration</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<GenDecoration> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Gen Decoration</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GenDecoration get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenDecoration result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen Decoration</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GenDecoration getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenDecoration result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen Decoration</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GenDecoration get(int value)
  {
    switch (value)
    {
      case NONE_VALUE: return NONE;
      case MANUAL_VALUE: return MANUAL;
      case LIVE_VALUE: return LIVE;
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
  private GenDecoration(int value, String name, String literal)
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
  
} //GenDecoration
