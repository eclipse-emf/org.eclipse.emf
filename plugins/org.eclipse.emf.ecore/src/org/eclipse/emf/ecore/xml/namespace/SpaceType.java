/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
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
 * $Id: SpaceType.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.ecore.xml.namespace;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Space Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage#getSpaceType()
 * @model
 * @generated
 */
public final class SpaceType extends AbstractEnumerator
{
  /**
   * The '<em><b>Default</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #DEFAULT_LITERAL
   * @model name="default"
   * @generated
   * @ordered
   */
  public static final int DEFAULT = 0;

  /**
   * The '<em><b>Preserve</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #PRESERVE_LITERAL
   * @model name="preserve"
   * @generated
   * @ordered
   */
  public static final int PRESERVE = 1;

  /**
   * The '<em><b>Default</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Default</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #DEFAULT
   * @generated
   * @ordered
   */
  public static final SpaceType DEFAULT_LITERAL = new SpaceType(DEFAULT, "default");

  /**
   * The '<em><b>Preserve</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Preserve</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #PRESERVE
   * @generated
   * @ordered
   */
  public static final SpaceType PRESERVE_LITERAL = new SpaceType(PRESERVE, "preserve");

  /**
   * An array of all the '<em><b>Space Type</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final SpaceType[] VALUES_ARRAY =
    new SpaceType[]
    {
      DEFAULT_LITERAL,
      PRESERVE_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Space Type</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Space Type</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SpaceType get(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      SpaceType result = VALUES_ARRAY[i];
      if (result.toString().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Space Type</b></em>' literal with the specified value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static SpaceType get(int value)
  {
    switch (value)
    {
      case DEFAULT: return DEFAULT_LITERAL;
      case PRESERVE: return PRESERVE_LITERAL;
    }
    return null;	
  }

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private SpaceType(int value, String name)
  {
    super(value, name);
  }

} //SpaceType
