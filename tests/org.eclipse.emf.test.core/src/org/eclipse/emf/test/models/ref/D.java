/**
 * <copyright>
 * </copyright>
 *
 * $Id: D.java,v 1.1 2004/08/22 23:34:56 davidms Exp $
 */
package org.eclipse.emf.test.models.ref;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>D</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ref.D#getC <em>C</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ref.D#getE <em>E</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.ref.RefPackage#getD()
 * @model 
 * @generated
 */
public interface D extends EObject
{
  /**
   * Returns the value of the '<em><b>C</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.C#getD <em>D</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>C</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>C</em>' reference.
   * @see #setC(C)
   * @see org.eclipse.emf.test.models.ref.RefPackage#getD_C()
   * @see org.eclipse.emf.test.models.ref.C#getD
   * @model opposite="d" required="true"
   * @generated
   */
  C getC();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ref.D#getC <em>C</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>C</em>' reference.
   * @see #getC()
   * @generated
   */
  void setC(C value);

  /**
   * Returns the value of the '<em><b>E</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.ref.E}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ref.E#getD <em>D</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>E</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>E</em>' reference list.
   * @see org.eclipse.emf.test.models.ref.RefPackage#getD_E()
   * @see org.eclipse.emf.test.models.ref.E#getD
   * @model type="org.eclipse.emf.test.models.ref.E" opposite="d"
   * @generated
   */
  EList getE();

} // D
