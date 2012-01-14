/**
 * Copyright (c) 2008-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Gen Provider Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenProviderKind()
 * @model
 * @generated
 */
public enum GenProviderKind implements Enumerator
{
  /**
   * The '<em><b>Singleton</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @see #SINGLETON
   * @generated
   * @ordered
   */
  SINGLETON_LITERAL(0, "Singleton", "Singleton"),
  /**
   * The '<em><b>Stateful</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @see #STATEFUL
   * @generated
   * @ordered
   */
  STATEFUL_LITERAL(1, "Stateful", "Stateful"),
  /**
   * The '<em><b>None</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @see #NONE
   * @generated
   * @ordered
   */
  NONE_LITERAL(2, "None", "None");
  /**
   * The '<em><b>Singleton</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @see #SINGLETON_LITERAL
   * @model name="Singleton"
   * @generated
   * @ordered
   */
  public static final int SINGLETON = 0;

  /**
   * The '<em><b>Stateful</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @see #STATEFUL_LITERAL
   * @model name="Stateful"
   * @generated
   * @ordered
   */
  public static final int STATEFUL = 1;

  /**
   * The '<em><b>None</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @see #NONE_LITERAL
   * @model name="None"
   * @generated
   * @ordered
   */
  public static final int NONE = 2;

  /**
   * An array of all the '<em><b>Gen Provider Kind</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final GenProviderKind[] VALUES_ARRAY =
    new GenProviderKind[]
    {
      SINGLETON_LITERAL,
      STATEFUL_LITERAL,
      NONE_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Gen Provider Kind</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List<GenProviderKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Gen Provider Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static GenProviderKind get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenProviderKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen Provider Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GenProviderKind getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenProviderKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen Provider Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static GenProviderKind get(int value)
  {
    switch (value)
    {
      case SINGLETON: return SINGLETON_LITERAL;
      case STATEFUL: return STATEFUL_LITERAL;
      case NONE: return NONE_LITERAL;
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
  private GenProviderKind(int value, String name, String literal)
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
