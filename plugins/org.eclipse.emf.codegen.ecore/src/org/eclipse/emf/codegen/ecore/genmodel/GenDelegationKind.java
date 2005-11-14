/**
 * <copyright>
 * </copyright>
 *
 * $Id: GenDelegationKind.java,v 1.1 2005/11/14 16:47:10 khussey Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Gen Delegation Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenDelegationKind()
 * @model
 * @generated
 */
public final class GenDelegationKind extends AbstractEnumerator
{
  /**
   * The '<em><b>None</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>None</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #NONE_LITERAL
   * @model name="None"
   * @generated
   * @ordered
   */
  public static final int NONE = 0;

  /**
   * The '<em><b>Reflective</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Reflective</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #REFLECTIVE_LITERAL
   * @model name="Reflective"
   * @generated
   * @ordered
   */
  public static final int REFLECTIVE = 1;

  /**
   * The '<em><b>Virtual</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Virtual</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #VIRTUAL_LITERAL
   * @model name="Virtual"
   * @generated
   * @ordered
   */
  public static final int VIRTUAL = 2;

  /**
   * The '<em><b>None</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NONE
   * @generated
   * @ordered
   */
  public static final GenDelegationKind NONE_LITERAL = new GenDelegationKind(NONE, "None", "None");

  /**
   * The '<em><b>Reflective</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #REFLECTIVE
   * @generated
   * @ordered
   */
  public static final GenDelegationKind REFLECTIVE_LITERAL = new GenDelegationKind(REFLECTIVE, "Reflective", "Reflective");

  /**
   * The '<em><b>Virtual</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #VIRTUAL
   * @generated
   * @ordered
   */
  public static final GenDelegationKind VIRTUAL_LITERAL = new GenDelegationKind(VIRTUAL, "Virtual", "Virtual");

  /**
   * An array of all the '<em><b>Gen Delegation Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final GenDelegationKind[] VALUES_ARRAY =
    new GenDelegationKind[]
    {
      NONE_LITERAL,
      REFLECTIVE_LITERAL,
      VIRTUAL_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Gen Delegation Kind</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Gen Delegation Kind</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GenDelegationKind get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenDelegationKind result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen Delegation Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GenDelegationKind getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenDelegationKind result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen Delegation Kind</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static GenDelegationKind get(int value)
  {
    switch (value)
    {
      case NONE: return NONE_LITERAL;
      case REFLECTIVE: return REFLECTIVE_LITERAL;
      case VIRTUAL: return VIRTUAL_LITERAL;
    }
    return null;	
  }

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private GenDelegationKind(int value, String name, String literal)
  {
    super(value, name, literal);
  }

} //GenDelegationKind
