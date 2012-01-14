/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.models.movie.db;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Genre Types</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.models.movie.db.DBPackage#getGenreTypes()
 * @model extendedMetaData="name='genreTypes'"
 * @generated
 */
public enum GenreTypes implements Enumerator
{
  /**
   * The '<em><b>New Release</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NEW_RELEASE_VALUE
   * @generated
   * @ordered
   */
  NEW_RELEASE(0, "NewRelease", "NewRelease"),

  /**
   * The '<em><b>Action</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #ACTION_VALUE
   * @generated
   * @ordered
   */
  ACTION(1, "Action", "Action"),

  /**
   * The '<em><b>Animation</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #ANIMATION_VALUE
   * @generated
   * @ordered
   */
  ANIMATION(2, "Animation", "Animation"),

  /**
   * The '<em><b>Family</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FAMILY_VALUE
   * @generated
   * @ordered
   */
  FAMILY(3, "Family", "Family"),

  /**
   * The '<em><b>Classics</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #CLASSICS_VALUE
   * @generated
   * @ordered
   */
  CLASSICS(4, "Classics", "Classics"),

  /**
   * The '<em><b>Comedy</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #COMEDY_VALUE
   * @generated
   * @ordered
   */
  COMEDY(5, "Comedy", "Comedy"),

  /**
   * The '<em><b>Documentary</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #DOCUMENTARY_VALUE
   * @generated
   * @ordered
   */
  DOCUMENTARY(6, "Documentary", "Documentary"),

  /**
   * The '<em><b>Drama</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #DRAMA_VALUE
   * @generated
   * @ordered
   */
  DRAMA(7, "Drama", "Drama"),

  /**
   * The '<em><b>Horror</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #HORROR_VALUE
   * @generated
   * @ordered
   */
  HORROR(8, "Horror", "Horror"),

  /**
   * The '<em><b>Romance</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #ROMANCE_VALUE
   * @generated
   * @ordered
   */
  ROMANCE(9, "Romance", "Romance"),

  /**
   * The '<em><b>Sci Fi</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SCI_FI_VALUE
   * @generated
   * @ordered
   */
  SCI_FI(10, "SciFi", "SciFi"),

  /**
   * The '<em><b>Thriller</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #THRILLER_VALUE
   * @generated
   * @ordered
   */
  THRILLER(11, "Thriller", "Thriller");

  /**
   * The '<em><b>New Release</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>New Release</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #NEW_RELEASE
   * @model name="NewRelease"
   * @generated
   * @ordered
   */
  public static final int NEW_RELEASE_VALUE = 0;

  /**
   * The '<em><b>Action</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Action</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #ACTION
   * @model name="Action"
   * @generated
   * @ordered
   */
  public static final int ACTION_VALUE = 1;

  /**
   * The '<em><b>Animation</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Animation</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #ANIMATION
   * @model name="Animation"
   * @generated
   * @ordered
   */
  public static final int ANIMATION_VALUE = 2;

  /**
   * The '<em><b>Family</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Family</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FAMILY
   * @model name="Family"
   * @generated
   * @ordered
   */
  public static final int FAMILY_VALUE = 3;

  /**
   * The '<em><b>Classics</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Classics</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #CLASSICS
   * @model name="Classics"
   * @generated
   * @ordered
   */
  public static final int CLASSICS_VALUE = 4;

  /**
   * The '<em><b>Comedy</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Comedy</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #COMEDY
   * @model name="Comedy"
   * @generated
   * @ordered
   */
  public static final int COMEDY_VALUE = 5;

  /**
   * The '<em><b>Documentary</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Documentary</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #DOCUMENTARY
   * @model name="Documentary"
   * @generated
   * @ordered
   */
  public static final int DOCUMENTARY_VALUE = 6;

  /**
   * The '<em><b>Drama</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Drama</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #DRAMA
   * @model name="Drama"
   * @generated
   * @ordered
   */
  public static final int DRAMA_VALUE = 7;

  /**
   * The '<em><b>Horror</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Horror</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #HORROR
   * @model name="Horror"
   * @generated
   * @ordered
   */
  public static final int HORROR_VALUE = 8;

  /**
   * The '<em><b>Romance</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Romance</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #ROMANCE
   * @model name="Romance"
   * @generated
   * @ordered
   */
  public static final int ROMANCE_VALUE = 9;

  /**
   * The '<em><b>Sci Fi</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Sci Fi</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SCI_FI
   * @model name="SciFi"
   * @generated
   * @ordered
   */
  public static final int SCI_FI_VALUE = 10;

  /**
   * The '<em><b>Thriller</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Thriller</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #THRILLER
   * @model name="Thriller"
   * @generated
   * @ordered
   */
  public static final int THRILLER_VALUE = 11;

  /**
   * An array of all the '<em><b>Genre Types</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final GenreTypes[] VALUES_ARRAY =
    new GenreTypes[]
    {
      NEW_RELEASE,
      ACTION,
      ANIMATION,
      FAMILY,
      CLASSICS,
      COMEDY,
      DOCUMENTARY,
      DRAMA,
      HORROR,
      ROMANCE,
      SCI_FI,
      THRILLER,
    };

  /**
   * A public read-only list of all the '<em><b>Genre Types</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<GenreTypes> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Genre Types</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GenreTypes get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenreTypes result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Genre Types</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GenreTypes getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenreTypes result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Genre Types</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GenreTypes get(int value)
  {
    switch (value)
    {
      case NEW_RELEASE_VALUE: return NEW_RELEASE;
      case ACTION_VALUE: return ACTION;
      case ANIMATION_VALUE: return ANIMATION;
      case FAMILY_VALUE: return FAMILY;
      case CLASSICS_VALUE: return CLASSICS;
      case COMEDY_VALUE: return COMEDY;
      case DOCUMENTARY_VALUE: return DOCUMENTARY;
      case DRAMA_VALUE: return DRAMA;
      case HORROR_VALUE: return HORROR;
      case ROMANCE_VALUE: return ROMANCE;
      case SCI_FI_VALUE: return SCI_FI;
      case THRILLER_VALUE: return THRILLER;
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
  private GenreTypes(int value, String name, String literal)
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
  
} //GenreTypes
