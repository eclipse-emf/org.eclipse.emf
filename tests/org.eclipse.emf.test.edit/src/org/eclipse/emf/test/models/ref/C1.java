/**
 * <copyright>
 * </copyright>
 *
 * $Id: C1.java,v 1.1 2004/11/04 05:52:03 marcelop Exp $
 */
package org.eclipse.emf.test.models.ref;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>C1</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.C1#getA <em>A</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.C1#getB <em>B</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.ref.RefPackage#getC1()
 * @model 
 * @generated
 */
public interface C1 extends EObject
{
  /**
   * Returns the value of the '<em><b>A</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>A</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>A</em>' containment reference.
   * @see #setA(A)
   * @see org.eclipse.emf.test.models.ref.RefPackage#getC1_A()
   * @model containment="true" required="true"
   * @generated
   */
  A getA();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.C1#getA <em>A</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>A</em>' containment reference.
   * @see #getA()
   * @generated
   */
  void setA(A value);

  /**
   * Returns the value of the '<em><b>B</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.ref.B}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>B</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>B</em>' containment reference list.
   * @see org.eclipse.emf.test.models.ref.RefPackage#getC1_B()
   * @model type="org.eclipse.emf.test.models.ref.B" containment="true"
   * @generated
   */
  EList getB();

} // C1
