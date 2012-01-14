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
 * A representation of the literals of the enumeration '<em><b>Diagnostic Severity</b></em>'.
 * @see org.eclipse.xsd.XSDDiagnostic#getSeverity()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDDiagnosticSeverity()
 * @model
 * @generated
 */
public enum XSDDiagnosticSeverity implements Enumerator
{
  /**
   * The '<em><b>Fatal</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #FATAL
   * @generated
   * @ordered
   */
  FATAL_LITERAL(0, "fatal", "fatal"),

  /**
   * The '<em><b>Error</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #ERROR
   * @generated
   * @ordered
   */
  ERROR_LITERAL(1, "error", "error"),

  /**
   * The '<em><b>Warning</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #WARNING
   * @generated
   * @ordered
   */
  WARNING_LITERAL(2, "warning", "warning"),

  /**
   * The '<em><b>Information</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #INFORMATION
   * @generated
   * @ordered
   */
  INFORMATION_LITERAL(3, "information", "information");

  /**
   * The '<em><b>Fatal</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that the problem is really bad and that further processing is likely not possible.
   * </p>
   * <!-- end-user-doc --> 
   * @see #FATAL_LITERAL
   * @model name="fatal"
   * @generated
   * @ordered
   */
  public static final int FATAL = 0;

  /**
   * The '<em><b>Error</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that the problem is bad and that the schema is likely not suitable for use.
   * </p>
   * <!-- end-user-doc --> 
   * @see #ERROR_LITERAL
   * @model name="error"
   * @generated
   * @ordered
   */
  public static final int ERROR = 1;

  /**
   * The '<em><b>Warning</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that the problem is not too bad and that the schema is likely suitable for use.
   * </p>
   * <!-- end-user-doc --> 
   * @see #WARNING_LITERAL
   * @model name="warning"
   * @generated
   * @ordered
   */
  public static final int WARNING = 2;

  /**
   * The '<em><b>Information</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that the problem is only potential and that the schema is very likely suitable for use.
   * </p>
   * <!-- end-user-doc --> 
   * @see #INFORMATION_LITERAL
   * @model name="information"
   * @generated
   * @ordered
   */
  public static final int INFORMATION = 3;

  /**
   * An array of all the '<em><b>Diagnostic Severity</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDDiagnosticSeverity[] VALUES_ARRAY =
    new XSDDiagnosticSeverity[]
    {
      FATAL_LITERAL,
      ERROR_LITERAL,
      WARNING_LITERAL,
      INFORMATION_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Diagnostic Severity</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List<XSDDiagnosticSeverity> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Diagnostic Severity</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDDiagnosticSeverity get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDDiagnosticSeverity result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Diagnostic Severity</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XSDDiagnosticSeverity getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDDiagnosticSeverity result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Diagnostic Severity</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDDiagnosticSeverity get(int value)
  {
    switch (value)
    {
      case FATAL: return FATAL_LITERAL;
      case ERROR: return ERROR_LITERAL;
      case WARNING: return WARNING_LITERAL;
      case INFORMATION: return INFORMATION_LITERAL;
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
  private XSDDiagnosticSeverity(int value, String name, String literal)
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
