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
 * $Id: JVisibility.java,v 1.3 2005/11/08 14:14:40 emerks Exp $
 */
package org.eclipse.emf.java;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;


/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>JVisibility</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.java.JavaPackage#getJVisibility()
 * @model
 * @generated
 */
public final class JVisibility extends AbstractEnumerator
{
  /**
   * The '<em><b>Public</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @see #PUBLIC_LITERAL
   * @model name="public"
   * @generated
   * @ordered
   */
  public static final int PUBLIC = 0;

  /**
   * The '<em><b>Protected</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @see #PROTECTED_LITERAL
   * @model name="protected"
   * @generated
   * @ordered
   */
  public static final int PROTECTED = 1;

  /**
   * The '<em><b>Private</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @see #PRIVATE_LITERAL
   * @model name="private"
   * @generated
   * @ordered
   */
  public static final int PRIVATE = 2;

  /**
   * The '<em><b>Package</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @see #PACKAGE_LITERAL
   * @model name="package"
   * @generated
   * @ordered
   */
  public static final int PACKAGE = 3;

  /**
   * The '<em><b>Public</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * If the meaning of '<em><b>Public</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc --> 
   * @see #PUBLIC
   * @generated
   * @ordered
   */
  public static final JVisibility PUBLIC_LITERAL = new JVisibility(PUBLIC, "public", "public");

  /**
   * The '<em><b>Protected</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * If the meaning of '<em><b>Protected</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc --> 
   * @see #PROTECTED
   * @generated
   * @ordered
   */
  public static final JVisibility PROTECTED_LITERAL = new JVisibility(PROTECTED, "protected", "protected");

  /**
   * The '<em><b>Private</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * If the meaning of '<em><b>Private</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc --> 
   * @see #PRIVATE
   * @generated
   * @ordered
   */
  public static final JVisibility PRIVATE_LITERAL = new JVisibility(PRIVATE, "private", "private");

  /**
   * The '<em><b>Package</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * If the meaning of '<em><b>Package</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc --> 
   * @see #PACKAGE
   * @generated
   * @ordered
   */
  public static final JVisibility PACKAGE_LITERAL = new JVisibility(PACKAGE, "package", "package");

  /**
   * An array of all the '<em><b>JVisibility</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final JVisibility[] VALUES_ARRAY =
    new JVisibility[]
    {
      PUBLIC_LITERAL,
      PROTECTED_LITERAL,
      PRIVATE_LITERAL,
      PACKAGE_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>JVisibility</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>JVisibility</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static JVisibility get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      JVisibility result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>JVisibility</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static JVisibility getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      JVisibility result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>JVisibility</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static JVisibility get(int value)
  {
    switch (value)
    {
      case PUBLIC: return PUBLIC_LITERAL;
      case PROTECTED: return PROTECTED_LITERAL;
      case PRIVATE: return PRIVATE_LITERAL;
      case PACKAGE: return PACKAGE_LITERAL;
    }
    return null;	
  }

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private JVisibility(int value, String name, String literal)
  {
    super(value, name, literal);
  }

} //JVisibility
