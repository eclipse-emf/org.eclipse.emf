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
 * $Id: XSDProcessContents.java,v 1.2 2005/06/08 06:23:01 nickb Exp $
 */
package org.eclipse.xsd;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;


/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Process Contents</b></em>'.
 * @see org.eclipse.xsd.XSDWildcard#getProcessContents()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDProcessContents()
 * @model
 * @generated
 */
public final class XSDProcessContents extends AbstractEnumerator
{
  /**
   * The '<em><b>Strict</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that content must be strictly valid.
   * </p>
   * <!-- end-user-doc --> 
   * @see #STRICT_LITERAL
   * @model name="strict"
   * @generated
   * @ordered
   */
  public static final int STRICT = 0;

  /**
   * The '<em><b>Lax</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that content may be strictly valid but need not be.
   * </p>
   * <!-- end-user-doc --> 
   * @see #LAX_LITERAL
   * @model name="lax"
   * @generated
   * @ordered
   */
  public static final int LAX = 1;

  /**
   * The '<em><b>Skip</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that content is not validated at all.
   * </p>
   * <!-- end-user-doc --> 
   * @see #SKIP_LITERAL
   * @model name="skip"
   * @generated
   * @ordered
   */
  public static final int SKIP = 2;
  /**
   * The '<em><b>Strict</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #STRICT
   * @generated
   * @ordered
   */
  public static final XSDProcessContents STRICT_LITERAL = new XSDProcessContents(STRICT, "strict");

  /**
   * The '<em><b>Lax</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #LAX
   * @generated
   * @ordered
   */
  public static final XSDProcessContents LAX_LITERAL = new XSDProcessContents(LAX, "lax");

  /**
   * The '<em><b>Skip</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #SKIP
   * @generated
   * @ordered
   */
  public static final XSDProcessContents SKIP_LITERAL = new XSDProcessContents(SKIP, "skip");

  /**
   * An array of all the '<em><b>Process Contents</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDProcessContents[] VALUES_ARRAY =
    new XSDProcessContents[]
    {
      STRICT_LITERAL,
      LAX_LITERAL,
      SKIP_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Process Contents</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Process Contents</b></em>' literal with the specified name.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDProcessContents get(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDProcessContents result = VALUES_ARRAY[i];
      if (result.toString().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Process Contents</b></em>' literal with the specified value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDProcessContents get(int value)
  {
    switch (value)
    {
      case STRICT: return STRICT_LITERAL;
      case LAX: return LAX_LITERAL;
      case SKIP: return SKIP_LITERAL;
    }
    return null;	
  }

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private XSDProcessContents(int value, String name)
  {
    super(value, name);
  }

}
