/**
 * <copyright>
 * </copyright>
 *
 * $Id: GenProviderKind.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Gen Provider Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenProviderKind()
 * @model
 * @generated
 */
public final class GenProviderKind extends AbstractEnumerator
{
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
   * The '<em><b>Singleton</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * If the meaning of '<em><b>SINGLETON</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc --> 
   * @see #SINGLETON
   * @generated
   * @ordered
   */
  public static final GenProviderKind SINGLETON_LITERAL = new GenProviderKind(SINGLETON, "Singleton");

  /**
   * The '<em><b>Stateful</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * If the meaning of '<em><b>STATEFUL</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc --> 
   * @see #STATEFUL
   * @generated
   * @ordered
   */
  public static final GenProviderKind STATEFUL_LITERAL = new GenProviderKind(STATEFUL, "Stateful");

  /**
   * The '<em><b>None</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * If the meaning of '<em><b>NONE</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc --> 
   * @see #NONE
   * @generated
   * @ordered
   */
  public static final GenProviderKind NONE_LITERAL = new GenProviderKind(NONE, "None");

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
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Gen Provider Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static GenProviderKind get(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenProviderKind result = VALUES_ARRAY[i];
      if (result.toString().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen Provider Kind</b></em>' literal with the specified value.
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
   * Only this class can construct instances.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private GenProviderKind(int value, String name)
  {
    super(value, name);
  }

} //GenProviderKind
