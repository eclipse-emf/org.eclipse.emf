/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
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
 * $Id: ChangeKind.java,v 1.3 2005/06/08 06:16:16 nickb Exp $
 */
package org.eclipse.emf.ecore.change;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;


/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.ecore.change.ChangePackage#getChangeKind()
 * @model
 * @generated
 */
public final class ChangeKind extends AbstractEnumerator
{
  /**
   * The '<em><b>ADD</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #ADD_LITERAL
   * @model
   * @generated
   * @ordered
   */
  public static final int ADD = 0;

  /**
   * The '<em><b>REMOVE</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #REMOVE_LITERAL
   * @model
   * @generated
   * @ordered
   */
  public static final int REMOVE = 1;

  /**
   * The '<em><b>MOVE</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MOVE_LITERAL
   * @model
   * @generated
   * @ordered
   */
  public static final int MOVE = 2;

  /**
   * The '<em><b>ADD</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>ADD</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #ADD
   * @generated
   * @ordered
   */
  public static final ChangeKind ADD_LITERAL = new ChangeKind(ADD, "ADD");

  /**
   * The '<em><b>REMOVE</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>REMOVE</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #REMOVE
   * @generated
   * @ordered
   */
  public static final ChangeKind REMOVE_LITERAL = new ChangeKind(REMOVE, "REMOVE");

  /**
   * The '<em><b>MOVE</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>MOVE</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #MOVE
   * @generated
   * @ordered
   */
  public static final ChangeKind MOVE_LITERAL = new ChangeKind(MOVE, "MOVE");

  /**
   * An array of all the '<em><b>Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final ChangeKind[] VALUES_ARRAY =
    new ChangeKind[]
    {
      ADD_LITERAL,
      REMOVE_LITERAL,
      MOVE_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ChangeKind get(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      ChangeKind result = VALUES_ARRAY[i];
      if (result.toString().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Kind</b></em>' literal with the specified value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ChangeKind get(int value)
  {
    switch (value)
    {
      case ADD: return ADD_LITERAL;
      case REMOVE: return REMOVE_LITERAL;
      case MOVE: return MOVE_LITERAL;
    }
    return null;	
  }

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private ChangeKind(int value, String name)
  {
    super(value, name);
  }

} //ChangeKind
