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
 * $Id: XSDDisallowedSubstitutions.java,v 1.3 2005/11/08 13:52:50 emerks Exp $
 */
package org.eclipse.xsd;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;


/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Disallowed Substitutions</b></em>'.
 * @see org.eclipse.xsd.XSDElementDeclaration#getDisallowedSubstitutions()
 * @see org.eclipse.xsd.XSDElementDeclaration#getBlock()
 * @see org.eclipse.xsd.XSDSchema#getBlockDefault()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDDisallowedSubstitutions()
 * @model
 * @generated
 */
public final class XSDDisallowedSubstitutions extends AbstractEnumerator
{
  /**
   * The '<em><b>Substitution</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that no element declaration may be substituted,
   * i.e., no substitution is permitted even for an element declaration with the same type.
   * </p>
   * <!-- end-user-doc --> 
   * @see #SUBSTITUTION_LITERAL
   * @model name="substitution"
   * @generated
   * @ordered
   */
  public static final int SUBSTITUTION = 0;

  /**
   * The '<em><b>Extension</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that an element declaration with a type definition derived via extension may not be substituted.
   * </p>
   * <!-- end-user-doc --> 
   * @see #EXTENSION_LITERAL
   * @model name="extension"
   * @generated
   * @ordered
   */
  public static final int EXTENSION = 1;

  /**
   * The '<em><b>Restriction</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that an element declaration with a type definition derived via restriction may not be substituted.
   * </p>
   * <!-- end-user-doc --> 
   * @see #RESTRICTION_LITERAL
   * @model name="restriction"
   * @generated
   * @ordered
   */
  public static final int RESTRICTION = 2;

  /**
   * The '<em><b>All</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that an element declaration with a derived type definition may not be substituted.
   * It is literally expanded to extension and restriction in the infoset model.
   * </p>
   * <!-- end-user-doc --> 
   * @see #ALL_LITERAL
   * @model name="all"
   * @generated
   * @ordered
   */
  public static final int ALL = 3;
  /**
   * The '<em><b>Substitution</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #SUBSTITUTION
   * @generated
   * @ordered
   */
  public static final XSDDisallowedSubstitutions SUBSTITUTION_LITERAL = new XSDDisallowedSubstitutions(SUBSTITUTION, "substitution", "substitution");

  /**
   * The '<em><b>Extension</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #EXTENSION
   * @generated
   * @ordered
   */
  public static final XSDDisallowedSubstitutions EXTENSION_LITERAL = new XSDDisallowedSubstitutions(EXTENSION, "extension", "extension");

  /**
   * The '<em><b>Restriction</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #RESTRICTION
   * @generated
   * @ordered
   */
  public static final XSDDisallowedSubstitutions RESTRICTION_LITERAL = new XSDDisallowedSubstitutions(RESTRICTION, "restriction", "restriction");

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
  public static final XSDDisallowedSubstitutions ALL_LITERAL = new XSDDisallowedSubstitutions(ALL, "all", "all");

  /**
   * An array of all the '<em><b>Disallowed Substitutions</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDDisallowedSubstitutions[] VALUES_ARRAY =
    new XSDDisallowedSubstitutions[]
    {
      SUBSTITUTION_LITERAL,
      EXTENSION_LITERAL,
      RESTRICTION_LITERAL,
      ALL_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Disallowed Substitutions</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Disallowed Substitutions</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDDisallowedSubstitutions get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDDisallowedSubstitutions result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Disallowed Substitutions</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XSDDisallowedSubstitutions getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDDisallowedSubstitutions result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Disallowed Substitutions</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDDisallowedSubstitutions get(int value)
  {
    switch (value)
    {
      case SUBSTITUTION: return SUBSTITUTION_LITERAL;
      case EXTENSION: return EXTENSION_LITERAL;
      case RESTRICTION: return RESTRICTION_LITERAL;
      case ALL: return ALL_LITERAL;
    }
    return null;	
  }

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private XSDDisallowedSubstitutions(int value, String name, String literal)
  {
    super(value, name, literal);
  }

} 
