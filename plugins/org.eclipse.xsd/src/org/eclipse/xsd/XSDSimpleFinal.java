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
 * $Id: XSDSimpleFinal.java,v 1.1 2004/03/06 18:00:10 marcelop Exp $
 */
package org.eclipse.xsd;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;


/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Simple Final</b></em>'.
 * @see org.eclipse.xsd.XSDSimpleTypeDefinition#getFinal()
 * @see org.eclipse.xsd.XSDSimpleTypeDefinition#getLexicalFinal()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDSimpleFinal()
 * @model
 * @generated
 */
public final class XSDSimpleFinal extends AbstractEnumerator
{
  /**
   * The '<em><b>List</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that no derivation via list is allowed for a simple type definition.
   * </p>
   * <!-- end-user-doc --> 
   * @see #LIST_LITERAL
   * @model name="list"
   * @generated
   * @ordered
   */
  public static final int LIST = 0;

  /**
   * The '<em><b>Restriction</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that no derivation via restriction is allowed for a simple type definition.
   * </p>
   * <!-- end-user-doc --> 
   * @see #RESTRICTION_LITERAL
   * @model name="restriction"
   * @generated
   * @ordered
   */
  public static final int RESTRICTION = 1;

  /**
   * The '<em><b>Union</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that no derivation via union is allowed for a simple type definition.
   * </p>
   * <!-- end-user-doc --> 
   * @see #UNION_LITERAL
   * @model name="union"
   * @generated
   * @ordered
   */
  public static final int UNION = 2;

  /**
   * The '<em><b>All</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that no derivation is allowed for a simple type definition.
   * It is literally expanded to list, restriction, and union in the infoset model.
   * </p>
   * <!-- end-user-doc --> 
   * @see #ALL_LITERAL
   * @model name="all"
   * @generated
   * @ordered
   */
  public static final int ALL = 3;
  /**
   * The '<em><b>List</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #LIST
   * @generated
   * @ordered
   */
  public static final XSDSimpleFinal LIST_LITERAL = new XSDSimpleFinal(LIST, "list");

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
  public static final XSDSimpleFinal RESTRICTION_LITERAL = new XSDSimpleFinal(RESTRICTION, "restriction");

  /**
   * The '<em><b>Union</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #UNION
   * @generated
   * @ordered
   */
  public static final XSDSimpleFinal UNION_LITERAL = new XSDSimpleFinal(UNION, "union");

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
  public static final XSDSimpleFinal ALL_LITERAL = new XSDSimpleFinal(ALL, "all");

  /**
   * An array of all the '<em><b>Simple Final</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDSimpleFinal[] VALUES_ARRAY =
    new XSDSimpleFinal[]
    {
      LIST_LITERAL,
      RESTRICTION_LITERAL,
      UNION_LITERAL,
      ALL_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Simple Final</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Simple Final</b></em>' literal with the specified name.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDSimpleFinal get(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDSimpleFinal result = VALUES_ARRAY[i];
      if (result.toString().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Simple Final</b></em>' literal with the specified value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDSimpleFinal get(int value)
  {
    switch (value)
    {
      case LIST: return LIST_LITERAL;
      case RESTRICTION: return RESTRICTION_LITERAL;
      case UNION: return UNION_LITERAL;
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
  private XSDSimpleFinal(int value, String name)
  {
    super(value, name);
  }

}
