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
 * $Id: XSDDerivationMethod.java,v 1.1 2004/03/06 18:00:10 marcelop Exp $
 */
package org.eclipse.xsd;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;


/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Derivation Method</b></em>'.
 * @see org.eclipse.xsd.XSDComplexTypeDefinition#getDerivationMethod()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDDerivationMethod()
 * @model
 * @generated
 */
public final class XSDDerivationMethod extends AbstractEnumerator
{
  /**
   * The '<em><b>Extension</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that a complex type definition is derived via extension.
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
   * This is used to indicate that a complex type definition is derived via restriction.
   * </p>
   * <!-- end-user-doc --> 
   * @see #RESTRICTION_LITERAL
   * @model name="restriction"
   * @generated
   * @ordered
   */
  public static final int RESTRICTION = 1;
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
  public static final XSDDerivationMethod EXTENSION_LITERAL = new XSDDerivationMethod(EXTENSION, "extension");

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
  public static final XSDDerivationMethod RESTRICTION_LITERAL = new XSDDerivationMethod(RESTRICTION, "restriction");

  /**
   * An array of all the '<em><b>Derivation Method</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDDerivationMethod[] VALUES_ARRAY =
    new XSDDerivationMethod[]
    {
      EXTENSION_LITERAL,
      RESTRICTION_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Derivation Method</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Derivation Method</b></em>' literal with the specified name.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDDerivationMethod get(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDDerivationMethod result = VALUES_ARRAY[i];
      if (result.toString().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Derivation Method</b></em>' literal with the specified value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDDerivationMethod get(int value)
  {
    switch (value)
    {
      case EXTENSION: return EXTENSION_LITERAL;
      case RESTRICTION: return RESTRICTION_LITERAL;
    }
    return null;	
  }

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private XSDDerivationMethod(int value, String name)
  {
    super(value, name);
  }

}
