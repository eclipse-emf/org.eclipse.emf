/**
 */
package org.eclipse.emf.codegen.ecore.genmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Gen Eclipse Platform Version</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * @since 2.14
 * <!-- end-model-doc -->
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenEclipsePlatformVersion()
 * @model
 * @generated
 */
public enum GenEclipsePlatformVersion implements Enumerator
{
  /**
   * The '<em><b>Juno</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #JUNO_VALUE
   * @generated
   * @ordered
   */
  JUNO(0, "Juno", "Juno"),

  /**
   * The '<em><b>Kepler</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #KEPLER_VALUE
   * @generated
   * @ordered
   */
  KEPLER(1, "Kepler", "Kepler"),

  /**
   * The '<em><b>Luna</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #LUNA_VALUE
   * @generated
   * @ordered
   */
  LUNA(2, "Luna", "Luna"),

  /**
   * The '<em><b>Mars</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MARS_VALUE
   * @generated
   * @ordered
   */
  MARS(3, "Mars", "Mars"),

  /**
   * The '<em><b>Neon</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NEON_VALUE
   * @generated
   * @ordered
   */
  NEON(4, "Neon", "Neon"),

  /**
   * The '<em><b>Oxygen</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #OXYGEN_VALUE
   * @generated
   * @ordered
   */
  OXYGEN(5, "Oxygen", "Oxygen"),

  /**
   * The '<em><b>Photon</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #PHOTON_VALUE
   * @generated
   * @ordered
   */
  PHOTON(6, "Photon", "Photon");

  /**
   * The '<em><b>Juno</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #JUNO
   * @model name="Juno"
   * @generated
   * @ordered
   */
  public static final int JUNO_VALUE = 0;

  /**
   * The '<em><b>Kepler</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #KEPLER
   * @model name="Kepler"
   * @generated
   * @ordered
   */
  public static final int KEPLER_VALUE = 1;

  /**
   * The '<em><b>Luna</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #LUNA
   * @model name="Luna"
   * @generated
   * @ordered
   */
  public static final int LUNA_VALUE = 2;

  /**
   * The '<em><b>Mars</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MARS
   * @model name="Mars"
   * @generated
   * @ordered
   */
  public static final int MARS_VALUE = 3;

  /**
   * The '<em><b>Neon</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NEON
   * @model name="Neon"
   * @generated
   * @ordered
   */
  public static final int NEON_VALUE = 4;

  /**
   * The '<em><b>Oxygen</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #OXYGEN
   * @model name="Oxygen"
   * @generated
   * @ordered
   */
  public static final int OXYGEN_VALUE = 5;

  /**
   * The '<em><b>Photon</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #PHOTON
   * @model name="Photon"
   * @generated
   * @ordered
   */
  public static final int PHOTON_VALUE = 6;

  /**
   * An array of all the '<em><b>Gen Eclipse Platform Version</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final GenEclipsePlatformVersion[] VALUES_ARRAY =
    new GenEclipsePlatformVersion[]
    {
      JUNO,
      KEPLER,
      LUNA,
      MARS,
      NEON,
      OXYGEN,
      PHOTON,
    };

  /**
   * A public read-only list of all the '<em><b>Gen Eclipse Platform Version</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<GenEclipsePlatformVersion> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Gen Eclipse Platform Version</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param literal the literal.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static GenEclipsePlatformVersion get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenEclipsePlatformVersion result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen Eclipse Platform Version</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param name the name.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static GenEclipsePlatformVersion getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenEclipsePlatformVersion result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen Eclipse Platform Version</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the integer value.
   * @return the matching enumerator or <code>null</code>.
   * @generated
   */
  public static GenEclipsePlatformVersion get(int value)
  {
    switch (value)
    {
      case JUNO_VALUE: return JUNO;
      case KEPLER_VALUE: return KEPLER;
      case LUNA_VALUE: return LUNA;
      case MARS_VALUE: return MARS;
      case NEON_VALUE: return NEON;
      case OXYGEN_VALUE: return OXYGEN;
      case PHOTON_VALUE: return PHOTON;
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
  private GenEclipsePlatformVersion(int value, String name, String literal)
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
  
} //GenEclipsePlatformVersion
