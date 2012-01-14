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
 * A representation of the literals of the enumeration '<em><b>Prohibited Substitutions</b></em>'.
 * @see org.eclipse.xsd.XSDComplexTypeDefinition#getProhibitedSubstitutions()
 * @see org.eclipse.xsd.XSDSchema#getFinalDefault()
 * @see org.eclipse.xsd.XSDElementDeclaration#getLexicalFinal()
 * @see org.eclipse.xsd.XSDComplexTypeDefinition#getBlock()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDProhibitedSubstitutions()
 * @model
 * @generated
 */
public enum XSDProhibitedSubstitutions implements Enumerator
{
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
  EXTENSION_LITERAL(0, "extension", "extension"),

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
  RESTRICTION_LITERAL(1, "restriction", "restriction"),

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
  ALL_LITERAL(2, "all", "all");

  /**
   * The '<em><b>Extension</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that types derived via extension will not be permitted to validate content.
   * </p>
   * <!-- end-user-doc --> 
   * @see #EXTENSION_LITERAL
   * @model name="extension"
   * @generated
   * @ordered
   */
  public static final int EXTENSION = 0;

  /**
   * The '<em><b>Restriction</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that types derived via restriction will not be permitted to validate content.
   * </p>
   * <!-- end-user-doc --> 
   * @see #RESTRICTION_LITERAL
   * @model name="restriction"
   * @generated
   * @ordered
   */
  public static final int RESTRICTION = 1;

  /**
   * The '<em><b>All</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that derived types will not be permitted to validate content.
   * It is literally expanded to extension and restriction in the infoset model.
   * </p>
   * <!-- end-user-doc --> 
   * @see #ALL_LITERAL
   * @model name="all"
   * @generated
   * @ordered
   */
  public static final int ALL = 2;

  /**
   * An array of all the '<em><b>Prohibited Substitutions</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDProhibitedSubstitutions[] VALUES_ARRAY =
    new XSDProhibitedSubstitutions[]
    {
      EXTENSION_LITERAL,
      RESTRICTION_LITERAL,
      ALL_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Prohibited Substitutions</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List<XSDProhibitedSubstitutions> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Prohibited Substitutions</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDProhibitedSubstitutions get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDProhibitedSubstitutions result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Prohibited Substitutions</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XSDProhibitedSubstitutions getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDProhibitedSubstitutions result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Prohibited Substitutions</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDProhibitedSubstitutions get(int value)
  {
    switch (value)
    {
      case EXTENSION: return EXTENSION_LITERAL;
      case RESTRICTION: return RESTRICTION_LITERAL;
      case ALL: return ALL_LITERAL;
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
  private XSDProhibitedSubstitutions(int value, String name, String literal)
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
