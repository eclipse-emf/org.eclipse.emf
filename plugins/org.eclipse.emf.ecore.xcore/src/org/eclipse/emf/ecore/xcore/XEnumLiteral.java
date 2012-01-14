/**
 *
 * $Id$
 */
package org.eclipse.emf.ecore.xcore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XEnum Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XEnumLiteral#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XEnumLiteral#getLiteral <em>Literal</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XEnumLiteral#getEnum <em>Enum</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXEnumLiteral()
 * @model
 * @generated
 */
public interface XEnumLiteral extends XNamedElement
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
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXEnumLiteral_Value()
   * @model
   * @generated
   */
  int getValue();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XEnumLiteral#getValue <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' attribute.
   * @see #getValue()
   * @generated
   */
  void setValue(int value);

  /**
   * Returns the value of the '<em><b>Literal</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Literal</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Literal</em>' attribute.
   * @see #setLiteral(String)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXEnumLiteral_Literal()
   * @model
   * @generated
   */
  String getLiteral();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XEnumLiteral#getLiteral <em>Literal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Literal</em>' attribute.
   * @see #getLiteral()
   * @generated
   */
  void setLiteral(String value);

  /**
   * Returns the value of the '<em><b>Enum</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.ecore.xcore.XEnum#getLiterals <em>Literals</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Enum</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Enum</em>' container reference.
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXEnumLiteral_Enum()
   * @see org.eclipse.emf.ecore.xcore.XEnum#getLiterals
   * @model opposite="literals" resolveProxies="false" changeable="false"
   * @generated
   */
  XEnum getEnum();

} // XEnumLiteral
