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
 * $Id: XSDIdentityConstraintCategory.java,v 1.3 2005/11/08 13:52:50 emerks Exp $
 */
package org.eclipse.xsd;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;


/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Identity Constraint Category</b></em>'.
 * @see org.eclipse.xsd.XSDIdentityConstraintDefinition#getIdentityConstraintCategory()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDIdentityConstraintCategory()
 * @model
 * @generated
 */
public final class XSDIdentityConstraintCategory extends AbstractEnumerator
{
  /**
   * The '<em><b>Key</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that an identity constraint definition is a 
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-key">key</a>.
   * </p>
   * <!-- end-user-doc --> 
   * @see #KEY_LITERAL
   * @model name="key"
   * @generated
   * @ordered
   */
  public static final int KEY = 0;

  /**
   * The '<em><b>Keyref</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that an identity constraint definition is a 
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-keyRef">keyRef</a>.
   * </p>
   * <!-- end-user-doc --> 
   * @see #KEYREF_LITERAL
   * @model name="keyref"
   * @generated
   * @ordered
   */
  public static final int KEYREF = 1;

  /**
   * The '<em><b>Unique</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that an identity constraint definition is a 
   * <a href="http://www.w3.org/TR/xmlschema-1/#element-unique">unique</a>.
   * </p>
   * <!-- end-user-doc --> 
   * @see #UNIQUE_LITERAL
   * @model name="unique"
   * @generated
   * @ordered
   */
  public static final int UNIQUE = 2;
  /**
   * The '<em><b>Key</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #KEY
   * @generated
   * @ordered
   */
  public static final XSDIdentityConstraintCategory KEY_LITERAL = new XSDIdentityConstraintCategory(KEY, "key", "key");

  /**
   * The '<em><b>Keyref</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #KEYREF
   * @generated
   * @ordered
   */
  public static final XSDIdentityConstraintCategory KEYREF_LITERAL = new XSDIdentityConstraintCategory(KEYREF, "keyref", "keyref");

  /**
   * The '<em><b>Unique</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #UNIQUE
   * @generated
   * @ordered
   */
  public static final XSDIdentityConstraintCategory UNIQUE_LITERAL = new XSDIdentityConstraintCategory(UNIQUE, "unique", "unique");

  /**
   * An array of all the '<em><b>Identity Constraint Category</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDIdentityConstraintCategory[] VALUES_ARRAY =
    new XSDIdentityConstraintCategory[]
    {
      KEY_LITERAL,
      KEYREF_LITERAL,
      UNIQUE_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Identity Constraint Category</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Identity Constraint Category</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDIdentityConstraintCategory get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDIdentityConstraintCategory result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Identity Constraint Category</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XSDIdentityConstraintCategory getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDIdentityConstraintCategory result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Identity Constraint Category</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDIdentityConstraintCategory get(int value)
  {
    switch (value)
    {
      case KEY: return KEY_LITERAL;
      case KEYREF: return KEYREF_LITERAL;
      case UNIQUE: return UNIQUE_LITERAL;
    }
    return null;	
  }

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private XSDIdentityConstraintCategory(int value, String name, String literal)
  {
    super(value, name, literal);
  }

}
