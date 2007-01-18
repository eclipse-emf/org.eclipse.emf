/**
 * <copyright>
 * </copyright>
 *
 * $Id: ExtE.java,v 1.1 2007/01/18 15:50:14 marcelop Exp $
 */
package org.eclipse.emf.test.models.ext;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.test.models.ref.E;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>E</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ext.ExtE#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ext.ExtE#getF <em>F</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.ext.ExtPackage#getExtE()
 * @model
 * @generated
 */
public interface ExtE extends E
{
  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see #setValue(int)
   * @see org.eclipse.emf.test.models.ext.ExtPackage#getExtE_Value()
   * @model
   * @generated
   */
  int getValue();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ext.ExtE#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
  void setValue(int value);

  /**
   * Returns the value of the '<em><b>F</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.ext.F}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ext.F#getE <em>E</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>F</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>F</em>' containment reference list.
   * @see org.eclipse.emf.test.models.ext.ExtPackage#getExtE_F()
   * @see org.eclipse.emf.test.models.ext.F#getE
   * @model type="org.eclipse.emf.test.models.ext.F" opposite="e" containment="true"
   * @generated
   */
  EList<F> getF();

} // ExtE
