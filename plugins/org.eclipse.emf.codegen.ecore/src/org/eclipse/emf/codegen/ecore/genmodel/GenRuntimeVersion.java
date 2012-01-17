/**
 * Copyright (c) 2008-2009 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Gen Runtime Version</b></em>',
 * and utility methods for working with them.
 * @since 2.4
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenRuntimeVersion()
 * @model
 * @generated
 */
public enum GenRuntimeVersion implements Enumerator
{
  /**
   * The '<em><b>EMF22</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EMF22_VALUE
   * @generated
   * @ordered
   */
  EMF22(0, "EMF22", "2.2"), /**
   * The '<em><b>EMF23</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EMF23_VALUE
   * @generated
   * @ordered
   */
  EMF23(1, "EMF23", "2.3"), /**
   * The '<em><b>EMF24</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EMF24_VALUE
   * @generated
   * @ordered
   */
  EMF24(2, "EMF24", "2.4"), /**
   * The '<em><b>EMF25</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EMF25_VALUE
   * @generated
   * @ordered
   */
  EMF25(3, "EMF25", "2.5"), /**
   * The '<em><b>EMF26</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EMF26_VALUE
   * @generated
   * @ordered
   */
  EMF26(4, "EMF26", "2.6"), /**
   * The '<em><b>EMF27</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EMF27_VALUE
   * @generated
   * @ordered
   */
  EMF27(5, "EMF27", "2.7"), /**
   * The '<em><b>EMF28</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EMF28_VALUE
   * @generated
   * @ordered
   */
  EMF28(6, "EMF28", "2.8");

  /**
   * The '<em><b>EMF22</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EMF22
   * @model literal="2.2"
   * @generated
   * @ordered
   */
  public static final int EMF22_VALUE = 0;

  /**
   * The '<em><b>EMF23</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EMF23
   * @model literal="2.3"
   * @generated
   * @ordered
   */
  public static final int EMF23_VALUE = 1;

  /**
   * The '<em><b>EMF24</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EMF24
   * @model literal="2.4"
   * @generated
   * @ordered
   */
  public static final int EMF24_VALUE = 2;

  /**
   * The '<em><b>EMF25</b></em>' literal value.
   * <!-- begin-user-doc -->
   * @since 2.5
   * <!-- end-user-doc -->
   * @see #EMF25
   * @model literal="2.5"
   * @generated
   * @ordered
   */
  public static final int EMF25_VALUE = 3;

  /**
   * The '<em><b>EMF26</b></em>' literal value.
   * <!-- begin-user-doc -->
   * @since 2.6
   * <!-- end-user-doc -->
   * @see #EMF26
   * @model literal="2.6"
   * @generated
   * @ordered
   */
  public static final int EMF26_VALUE = 4;

  /**
   * The '<em><b>EMF27</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * @since 2.7
   * </p>
   * <!-- end-user-doc -->
   * @see #EMF27
   * @model literal="2.7"
   * @generated
   * @ordered
   */
  public static final int EMF27_VALUE = 5;

  /**
   * The '<em><b>EMF28</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>EMF28</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EMF28
   * @model literal="2.8"
   * @generated
   * @ordered
   */
  public static final int EMF28_VALUE = 6;

  /**
   * An array of all the '<em><b>Gen Runtime Version</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final GenRuntimeVersion[] VALUES_ARRAY =
    new GenRuntimeVersion[]
    {
      EMF22,
      EMF23,
      EMF24,
      EMF25,
      EMF26,
      EMF27,
      EMF28,
    };

  /**
   * A public read-only list of all the '<em><b>Gen Runtime Version</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<GenRuntimeVersion> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Gen Runtime Version</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GenRuntimeVersion get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenRuntimeVersion result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen Runtime Version</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GenRuntimeVersion getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenRuntimeVersion result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen Runtime Version</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GenRuntimeVersion get(int value)
  {
    switch (value)
    {
      case EMF22_VALUE: return EMF22;
      case EMF23_VALUE: return EMF23;
      case EMF24_VALUE: return EMF24;
      case EMF25_VALUE: return EMF25;
      case EMF26_VALUE: return EMF26;
      case EMF27_VALUE: return EMF27;
      case EMF28_VALUE: return EMF28;
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
  private GenRuntimeVersion(int value, String name, String literal)
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
  
} //GenRuntimeVersion
