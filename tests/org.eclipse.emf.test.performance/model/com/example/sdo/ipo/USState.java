/**
 * <copyright>
 * </copyright>
 *
 * $Id: USState.java,v 1.1.2.1 2005/05/30 19:29:31 nickb Exp $
 */
package com.example.sdo.ipo;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>US State</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see com.example.sdo.ipo.IpoPackage#getUSState()
 * @model
 * @generated
 */
public final class USState extends AbstractEnumerator
{
  /**
   * The '<em><b>AK</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>AK</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #AK_LITERAL
   * @model
   * @generated
   * @ordered
   */
  public static final int AK = 0;

  /**
   * The '<em><b>AL</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>AL</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #AL_LITERAL
   * @model
   * @generated
   * @ordered
   */
  public static final int AL = 1;

  /**
   * The '<em><b>AR</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>AR</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #AR_LITERAL
   * @model
   * @generated
   * @ordered
   */
  public static final int AR = 2;

  /**
   * The '<em><b>PA</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>PA</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #PA_LITERAL
   * @model
   * @generated
   * @ordered
   */
  public static final int PA = 3;

  /**
   * The '<em><b>AK</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #AK
   * @generated
   * @ordered
   */
  public static final USState AK_LITERAL = new USState(AK, "AK");

  /**
   * The '<em><b>AL</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #AL
   * @generated
   * @ordered
   */
  public static final USState AL_LITERAL = new USState(AL, "AL");

  /**
   * The '<em><b>AR</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #AR
   * @generated
   * @ordered
   */
  public static final USState AR_LITERAL = new USState(AR, "AR");

  /**
   * The '<em><b>PA</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #PA
   * @generated
   * @ordered
   */
  public static final USState PA_LITERAL = new USState(PA, "PA");

  /**
   * An array of all the '<em><b>US State</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final USState[] VALUES_ARRAY =
    new USState[]
    {
      AK_LITERAL,
      AL_LITERAL,
      AR_LITERAL,
      PA_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>US State</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>US State</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static USState get(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      USState result = VALUES_ARRAY[i];
      if (result.toString().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>US State</b></em>' literal with the specified value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static USState get(int value)
  {
    switch (value)
    {
      case AK: return AK_LITERAL;
      case AL: return AL_LITERAL;
      case AR: return AR_LITERAL;
      case PA: return PA_LITERAL;
    }
    return null;	
  }

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private USState(int value, String name)
  {
    super(value, name);
  }

} //USState
