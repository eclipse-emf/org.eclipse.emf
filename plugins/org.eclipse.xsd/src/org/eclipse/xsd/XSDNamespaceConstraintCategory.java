/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDNamespaceConstraintCategory.java,v 1.1 2004/03/06 18:00:10 marcelop Exp $
 */
package org.eclipse.xsd;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;


/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Namespace Constraint Category</b></em>'.
 * @see org.eclipse.xsd.XSDWildcard#getNamespaceConstraintCategory()
 * @see org.eclipse.xsd.XSDWildcard#getNamespaceConstraint()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDNamespaceConstraintCategory()
 * @model
 * @generated
 */
public final class XSDNamespaceConstraintCategory extends AbstractEnumerator
{
  /**
   * The '<em><b>Any</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that any namespace is matched.
   * </p>
   * <!-- end-user-doc --> 
   * @see #ANY_LITERAL
   * @model name="any"
   * @generated
   * @ordered
   */
  public static final int ANY = 0;

  /**
   * The '<em><b>Not</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that any namespace not in the (singleton) set is matched.
   * </p>
   * <!-- end-user-doc --> 
   * @see #NOT_LITERAL
   * @model name="not"
   * @generated
   * @ordered
   */
  public static final int NOT = 1;

  /**
   * The '<em><b>Set</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that any namespace in the set is matched.
   * </p>
   * <!-- end-user-doc --> 
   * @see #SET_LITERAL
   * @model name="set"
   * @generated
   * @ordered
   */
  public static final int SET = 2;
  /**
   * The '<em><b>Any</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #ANY
   * @generated
   * @ordered
   */
  public static final XSDNamespaceConstraintCategory ANY_LITERAL = new XSDNamespaceConstraintCategory(ANY, "any");

  /**
   * The '<em><b>Not</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #NOT
   * @generated
   * @ordered
   */
  public static final XSDNamespaceConstraintCategory NOT_LITERAL = new XSDNamespaceConstraintCategory(NOT, "not");

  /**
   * The '<em><b>Set</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #SET
   * @generated
   * @ordered
   */
  public static final XSDNamespaceConstraintCategory SET_LITERAL = new XSDNamespaceConstraintCategory(SET, "set");

  /**
   * An array of all the '<em><b>Namespace Constraint Category</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDNamespaceConstraintCategory[] VALUES_ARRAY =
    new XSDNamespaceConstraintCategory[]
    {
      ANY_LITERAL,
      NOT_LITERAL,
      SET_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Namespace Constraint Category</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Namespace Constraint Category</b></em>' literal with the specified name.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDNamespaceConstraintCategory get(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDNamespaceConstraintCategory result = VALUES_ARRAY[i];
      if (result.toString().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Namespace Constraint Category</b></em>' literal with the specified value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDNamespaceConstraintCategory get(int value)
  {
    switch (value)
    {
      case ANY: return ANY_LITERAL;
      case NOT: return NOT_LITERAL;
      case SET: return SET_LITERAL;
    }
    return null;	
  }

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private XSDNamespaceConstraintCategory(int value, String name)
  {
    super(value, name);
  }

} 
