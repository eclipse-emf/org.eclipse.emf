/**
 *
 * $Id$
 */
package org.eclipse.emf.ecore.xcore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XParameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XParameter#getOperation <em>Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXParameter()
 * @model
 * @generated
 */
public interface XParameter extends XTypedElement
{
  /**
   * Returns the value of the '<em><b>Operation</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.ecore.xcore.XOperation#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Operation</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Operation</em>' container reference.
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXParameter_Operation()
   * @see org.eclipse.emf.ecore.xcore.XOperation#getParameters
   * @model opposite="parameters" resolveProxies="false" changeable="false"
   * @generated
   */
  XOperation getOperation();

} // XParameter
