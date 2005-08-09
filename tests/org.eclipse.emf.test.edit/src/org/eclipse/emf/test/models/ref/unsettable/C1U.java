/**
 * <copyright>
 * </copyright>
 *
 * $Id: C1U.java,v 1.1 2005/08/09 04:43:09 davidms Exp $
 */
package org.eclipse.emf.test.models.ref.unsettable;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>C1U</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.C1U#getAu <em>Au</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.unsettable.C1U#getBu <em>Bu</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getC1U()
 * @model
 * @generated
 */
public interface C1U extends EObject
{
  /**
   * Returns the value of the '<em><b>Au</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Au</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Au</em>' containment reference.
   * @see #isSetAu()
   * @see #unsetAu()
   * @see #setAu(AU)
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getC1U_Au()
   * @model containment="true" unsettable="true" required="true"
   * @generated
   */
  AU getAu();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.C1U#getAu <em>Au</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Au</em>' containment reference.
   * @see #isSetAu()
   * @see #unsetAu()
   * @see #getAu()
   * @generated
   */
  void setAu(AU value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.C1U#getAu <em>Au</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetAu()
   * @see #getAu()
   * @see #setAu(AU)
   * @generated
   */
  void unsetAu();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.C1U#getAu <em>Au</em>}' containment reference is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Au</em>' containment reference is set.
   * @see #unsetAu()
   * @see #getAu()
   * @see #setAu(AU)
   * @generated
   */
  boolean isSetAu();

  /**
   * Returns the value of the '<em><b>Bu</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.ref.unsettable.BU}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bu</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bu</em>' containment reference list.
   * @see #isSetBu()
   * @see #unsetBu()
   * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage#getC1U_Bu()
   * @model type="org.eclipse.emf.test.models.ref.unsettable.BU" containment="true" unsettable="true"
   * @generated
   */
  EList getBu();

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.C1U#getBu <em>Bu</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetBu()
   * @see #getBu()
   * @generated
   */
  void unsetBu();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.ref.unsettable.C1U#getBu <em>Bu</em>}' containment reference list is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Bu</em>' containment reference list is set.
   * @see #unsetBu()
   * @see #getBu()
   * @generated
   */
  boolean isSetBu();

} // C1U
