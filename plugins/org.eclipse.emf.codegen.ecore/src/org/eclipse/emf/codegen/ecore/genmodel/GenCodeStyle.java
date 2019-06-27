/**
 * Copyright (c) 2019 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.ecore.genmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Code Style</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * @since 2.19
 * <!-- end-model-doc -->
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenCodeStyle()
 * @model
 * @generated
 */
public enum GenCodeStyle implements Enumerator
{
  /**
   * The '<em><b>Unnecessary Else</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #UNNECESSARY_ELSE_VALUE
   * @generated
   * @ordered
   */
  UNNECESSARY_ELSE(0, "UnnecessaryElse", "UnnecessaryElse"),

  /**
   * The '<em><b>Switch Missing Default Case</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SWITCH_MISSING_DEFAULT_CASE_VALUE
   * @generated
   * @ordered
   */
  SWITCH_MISSING_DEFAULT_CASE(1, "SwitchMissingDefaultCase", "SwitchMissingDefaultCase"),

  /**
   * The '<em><b>Unnecessary Assignment Before Return</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #UNNECESSARY_ASSIGNMENT_BEFORE_RETURN_VALUE
   * @generated
   * @ordered
   */
  UNNECESSARY_ASSIGNMENT_BEFORE_RETURN(2, "UnnecessaryAssignmentBeforeReturn", "UnnecessaryAssignmentBeforeReturn"), 

  /**
   * The '<em><b>Unnecessary Deprecated Method</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #UNNECESSARY_DEPRECATED_METHOD_VALUE
   * @generated
   * @ordered
   */
  UNNECESSARY_DEPRECATED_METHOD(3, "UnnecessaryDeprecatedMethod", "UnnecessaryDeprecatedMethod");

  /**
   * The '<em><b>Unnecessary Else</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #UNNECESSARY_ELSE
   * @model name="UnnecessaryElse"
   * @generated
   * @ordered
   */
  public static final int UNNECESSARY_ELSE_VALUE = 0;

  /**
   * The '<em><b>Switch Missing Default Case</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SWITCH_MISSING_DEFAULT_CASE
   * @model name="SwitchMissingDefaultCase"
   * @generated
   * @ordered
   */
  public static final int SWITCH_MISSING_DEFAULT_CASE_VALUE = 1;

  /**
   * The '<em><b>Unnecessary Assignment Before Return</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #UNNECESSARY_ASSIGNMENT_BEFORE_RETURN
   * @model name="UnnecessaryAssignmentBeforeReturn"
   * @generated
   * @ordered
   */
  public static final int UNNECESSARY_ASSIGNMENT_BEFORE_RETURN_VALUE = 2;

  /**
   * The '<em><b>Unnecessary Deprecated Method</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #UNNECESSARY_DEPRECATED_METHOD
   * @model name="UnnecessaryDeprecatedMethod"
   * @generated
   * @ordered
   */
  public static final int UNNECESSARY_DEPRECATED_METHOD_VALUE = 3;

  /**
   * An array of all the '<em><b>Gen Code Style</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final GenCodeStyle[] VALUES_ARRAY =
    new GenCodeStyle[]
    {
      UNNECESSARY_ELSE,
      SWITCH_MISSING_DEFAULT_CASE,
      UNNECESSARY_ASSIGNMENT_BEFORE_RETURN,
      UNNECESSARY_DEPRECATED_METHOD,
    };

  /**
   * A public read-only list of all the '<em><b>Gen Code Style</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<GenCodeStyle> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Gen Code Style</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static GenCodeStyle get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenCodeStyle result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen Code Style</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static GenCodeStyle getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenCodeStyle result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen Code Style</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static GenCodeStyle get(int value)
  {
    switch (value)
    {
      case UNNECESSARY_ELSE_VALUE: return UNNECESSARY_ELSE;
      case SWITCH_MISSING_DEFAULT_CASE_VALUE: return SWITCH_MISSING_DEFAULT_CASE;
      case UNNECESSARY_ASSIGNMENT_BEFORE_RETURN_VALUE: return UNNECESSARY_ASSIGNMENT_BEFORE_RETURN;
      case UNNECESSARY_DEPRECATED_METHOD_VALUE: return UNNECESSARY_DEPRECATED_METHOD;
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
  private GenCodeStyle(int value, String name, String literal)
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
  
} //GenCodeStyle
