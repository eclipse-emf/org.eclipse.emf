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
 * $Id: XSDDiagnosticSeverity.java,v 1.2 2005/06/08 06:23:01 nickb Exp $
 */
package org.eclipse.xsd;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;


/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Diagnostic Severity</b></em>'.
 * @see org.eclipse.xsd.XSDDiagnostic#getSeverity()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDDiagnosticSeverity()
 * @model
 * @generated
 */
public final class XSDDiagnosticSeverity extends AbstractEnumerator
{
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
   * The '<em><b>Fatal</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #FATAL
   * @generated
   * @ordered
   */
  public static final XSDDiagnosticSeverity FATAL_LITERAL = new XSDDiagnosticSeverity(FATAL, "fatal");

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
  public static final XSDDiagnosticSeverity ERROR_LITERAL = new XSDDiagnosticSeverity(ERROR, "error");

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
  public static final XSDDiagnosticSeverity WARNING_LITERAL = new XSDDiagnosticSeverity(WARNING, "warning");

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
  public static final XSDDiagnosticSeverity INFORMATION_LITERAL = new XSDDiagnosticSeverity(INFORMATION, "information");

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
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Diagnostic Severity</b></em>' literal with the specified name.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDDiagnosticSeverity get(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDDiagnosticSeverity result = VALUES_ARRAY[i];
      if (result.toString().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Diagnostic Severity</b></em>' literal with the specified value.
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
   * Only this class can construct instances.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private XSDDiagnosticSeverity(int value, String name)
  {
    super(value, name);
  }

}
