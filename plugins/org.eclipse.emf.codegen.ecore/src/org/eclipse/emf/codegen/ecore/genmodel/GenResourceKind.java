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
 * $Id: GenResourceKind.java,v 1.3 2005/06/08 06:18:44 nickb Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Gen Resource Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenResourceKind()
 * @model
 * @generated
 */
public final class GenResourceKind extends AbstractEnumerator
{
  /**
   * The '<em><b>None</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NONE_LITERAL
   * @model name="None"
   * @generated
   * @ordered
   */
  public static final int NONE = 0;

  /**
   * The '<em><b>Basic</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #BASIC_LITERAL
   * @model name="Basic"
   * @generated
   * @ordered
   */
  public static final int BASIC = 1;

  /**
   * The '<em><b>XMI</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #XMI_LITERAL
   * @model
   * @generated
   * @ordered
   */
  public static final int XMI = 2;

  /**
   * The '<em><b>XML</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #XML_LITERAL
   * @model
   * @generated
   * @ordered
   */
  public static final int XML = 3;

  /**
   * The '<em><b>None</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>None</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #NONE
   * @generated
   * @ordered
   */
  public static final GenResourceKind NONE_LITERAL = new GenResourceKind(NONE, "None");

  /**
   * The '<em><b>Basic</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Basic</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #BASIC
   * @generated
   * @ordered
   */
  public static final GenResourceKind BASIC_LITERAL = new GenResourceKind(BASIC, "Basic");

  /**
   * The '<em><b>XMI</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>XMI</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #XMI
   * @generated
   * @ordered
   */
  public static final GenResourceKind XMI_LITERAL = new GenResourceKind(XMI, "XMI");

  /**
   * The '<em><b>XML</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>XML</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #XML
   * @generated
   * @ordered
   */
  public static final GenResourceKind XML_LITERAL = new GenResourceKind(XML, "XML");

  /**
   * An array of all the '<em><b>Gen Resource Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final GenResourceKind[] VALUES_ARRAY =
    new GenResourceKind[]
    {
      NONE_LITERAL,
      BASIC_LITERAL,
      XMI_LITERAL,
      XML_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Gen Resource Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Gen Resource Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GenResourceKind get(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenResourceKind result = VALUES_ARRAY[i];
      if (result.toString().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen Resource Kind</b></em>' literal with the specified value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GenResourceKind get(int value)
  {
    switch (value)
    {
      case NONE: return NONE_LITERAL;
      case BASIC: return BASIC_LITERAL;
      case XMI: return XMI_LITERAL;
      case XML: return XML_LITERAL;
    }
    return null;	
  }

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private GenResourceKind(int value, String name)
  {
    super(value, name);
  }

} //GenResourceKind
