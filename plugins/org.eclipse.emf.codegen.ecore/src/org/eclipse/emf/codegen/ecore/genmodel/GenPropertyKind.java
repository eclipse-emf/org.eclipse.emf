/**
 * <copyright>
 * </copyright>
 *
 * $Id: GenPropertyKind.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Gen Property Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenPropertyKind()
 * @model
 * @generated
 */
public final class GenPropertyKind extends AbstractEnumerator
{
  /**
   * The '<em><b>Editable</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @see #EDITABLE_LITERAL
   * @model name="Editable"
   * @generated
   * @ordered
   */
  public static final int EDITABLE = 0;

  /**
   * The '<em><b>Readonly</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @see #READONLY_LITERAL
   * @model name="Readonly"
   * @generated
   * @ordered
   */
  public static final int READONLY = 1;

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
   * The '<em><b>Editable</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * If the meaning of '<em><b>EDITABLE</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc --> 
   * @see #EDITABLE
   * @generated
   * @ordered
   */
  public static final GenPropertyKind EDITABLE_LITERAL = new GenPropertyKind(EDITABLE, "Editable");

  /**
   * The '<em><b>Readonly</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * If the meaning of '<em><b>READONLY</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc --> 
   * @see #READONLY
   * @generated
   * @ordered
   */
  public static final GenPropertyKind READONLY_LITERAL = new GenPropertyKind(READONLY, "Readonly");

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
  public static final GenPropertyKind NONE_LITERAL = new GenPropertyKind(NONE, "None");

  /**
   * An array of all the '<em><b>Gen Property Kind</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final GenPropertyKind[] VALUES_ARRAY =
    new GenPropertyKind[]
    {
      EDITABLE_LITERAL,
      READONLY_LITERAL,
      NONE_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Gen Property Kind</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Gen Property Kind</b></em>' literal with the specified name.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static GenPropertyKind get(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      GenPropertyKind result = VALUES_ARRAY[i];
      if (result.toString().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Gen Property Kind</b></em>' literal with the specified value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static GenPropertyKind get(int value)
  {
    switch (value)
    {
      case EDITABLE: return EDITABLE_LITERAL;
      case READONLY: return READONLY_LITERAL;
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
  private GenPropertyKind(int value, String name)
  {
    super(value, name);
  }

} //GenPropertyKind
