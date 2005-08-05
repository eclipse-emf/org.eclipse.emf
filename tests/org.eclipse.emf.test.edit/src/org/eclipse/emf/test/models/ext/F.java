/**
 * <copyright>
 * </copyright>
 *
 * $Id: F.java,v 1.1.2.1 2005/08/05 20:36:34 nickb Exp $
 */
package org.eclipse.emf.test.models.ext;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>F</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ext.F#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ext.F#getE <em>E</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.ext.ExtPackage#getF()
 * @model
 * @generated
 */
public interface F extends EObject
{
  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see org.eclipse.emf.test.models.ext.ExtPackage#getF_Id()
   * @model
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ext.F#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>E</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.ext.ExtE#getF <em>F</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>E</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>E</em>' container reference.
   * @see #setE(ExtE)
   * @see org.eclipse.emf.test.models.ext.ExtPackage#getF_E()
   * @see org.eclipse.emf.test.models.ext.ExtE#getF
   * @model opposite="f" required="true"
   * @generated
   */
  ExtE getE();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.ext.F#getE <em>E</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>E</em>' container reference.
   * @see #getE()
   * @generated
   */
  void setE(ExtE value);

} // F
