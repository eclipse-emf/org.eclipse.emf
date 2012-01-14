/**
 *
 * $Id$
 */
package org.eclipse.emf.ecore.xcore;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XEnum</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XEnum#getLiterals <em>Literals</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXEnum()
 * @model
 * @generated
 */
public interface XEnum extends XDataType
{
  /**
   * Returns the value of the '<em><b>Literals</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.xcore.XEnumLiteral}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.ecore.xcore.XEnumLiteral#getEnum <em>Enum</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Literals</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Literals</em>' containment reference list.
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXEnum_Literals()
   * @see org.eclipse.emf.ecore.xcore.XEnumLiteral#getEnum
   * @model opposite="enum" containment="true"
   * @generated
   */
  EList<XEnumLiteral> getLiterals();

} // XEnum
