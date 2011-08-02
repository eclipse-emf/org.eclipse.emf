/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.ecore.xcore;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.xbase.XBlockExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XStructural Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isReadonly <em>Readonly</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isVolatile <em>Volatile</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isTransient <em>Transient</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#getDefaultValueLiteral <em>Default Value Literal</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isUnsettable <em>Unsettable</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isDerived <em>Derived</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isID <em>ID</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isContainment <em>Containment</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isResolveProxies <em>Resolve Proxies</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isLocal <em>Local</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#getOpposite <em>Opposite</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#getKeys <em>Keys</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#getGetBody <em>Get Body</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#getSetBody <em>Set Body</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#getIsSetBody <em>Is Set Body</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#getUnsetBody <em>Unset Body</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXStructuralFeature()
 * @model
 * @generated
 */
public interface XStructuralFeature extends XMember
{
  /**
   * Returns the value of the '<em><b>Readonly</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Readonly</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Readonly</em>' attribute.
   * @see #setReadonly(boolean)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXStructuralFeature_Readonly()
   * @model
   * @generated
   */
  boolean isReadonly();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isReadonly <em>Readonly</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Readonly</em>' attribute.
   * @see #isReadonly()
   * @generated
   */
  void setReadonly(boolean value);

  /**
   * Returns the value of the '<em><b>Volatile</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Volatile</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Volatile</em>' attribute.
   * @see #setVolatile(boolean)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXStructuralFeature_Volatile()
   * @model
   * @generated
   */
  boolean isVolatile();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isVolatile <em>Volatile</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Volatile</em>' attribute.
   * @see #isVolatile()
   * @generated
   */
  void setVolatile(boolean value);

  /**
   * Returns the value of the '<em><b>Transient</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Transient</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Transient</em>' attribute.
   * @see #setTransient(boolean)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXStructuralFeature_Transient()
   * @model
   * @generated
   */
  boolean isTransient();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isTransient <em>Transient</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Transient</em>' attribute.
   * @see #isTransient()
   * @generated
   */
  void setTransient(boolean value);

  /**
   * Returns the value of the '<em><b>Default Value Literal</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Default Value Literal</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Default Value Literal</em>' attribute.
   * @see #setDefaultValueLiteral(String)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXStructuralFeature_DefaultValueLiteral()
   * @model
   * @generated
   */
  String getDefaultValueLiteral();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#getDefaultValueLiteral <em>Default Value Literal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Default Value Literal</em>' attribute.
   * @see #getDefaultValueLiteral()
   * @generated
   */
  void setDefaultValueLiteral(String value);

  /**
   * Returns the value of the '<em><b>Unsettable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unsettable</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unsettable</em>' attribute.
   * @see #setUnsettable(boolean)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXStructuralFeature_Unsettable()
   * @model
   * @generated
   */
  boolean isUnsettable();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isUnsettable <em>Unsettable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unsettable</em>' attribute.
   * @see #isUnsettable()
   * @generated
   */
  void setUnsettable(boolean value);

  /**
   * Returns the value of the '<em><b>Derived</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Derived</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Derived</em>' attribute.
   * @see #setDerived(boolean)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXStructuralFeature_Derived()
   * @model
   * @generated
   */
  boolean isDerived();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isDerived <em>Derived</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Derived</em>' attribute.
   * @see #isDerived()
   * @generated
   */
  void setDerived(boolean value);

  /**
   * Returns the value of the '<em><b>ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>ID</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>ID</em>' attribute.
   * @see #setID(boolean)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXStructuralFeature_ID()
   * @model
   * @generated
   */
  boolean isID();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isID <em>ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>ID</em>' attribute.
   * @see #isID()
   * @generated
   */
  void setID(boolean value);

  /**
   * Returns the value of the '<em><b>Containment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Containment</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Containment</em>' attribute.
   * @see #setContainment(boolean)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXStructuralFeature_Containment()
   * @model
   * @generated
   */
  boolean isContainment();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isContainment <em>Containment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Containment</em>' attribute.
   * @see #isContainment()
   * @generated
   */
  void setContainment(boolean value);

  /**
   * Returns the value of the '<em><b>Resolve Proxies</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Resolve Proxies</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resolve Proxies</em>' attribute.
   * @see #setResolveProxies(boolean)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXStructuralFeature_ResolveProxies()
   * @model
   * @generated
   */
  boolean isResolveProxies();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isResolveProxies <em>Resolve Proxies</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Resolve Proxies</em>' attribute.
   * @see #isResolveProxies()
   * @generated
   */
  void setResolveProxies(boolean value);

  /**
   * Returns the value of the '<em><b>Local</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Local</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Local</em>' attribute.
   * @see #setLocal(boolean)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXStructuralFeature_Local()
   * @model
   * @generated
   */
  boolean isLocal();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isLocal <em>Local</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Local</em>' attribute.
   * @see #isLocal()
   * @generated
   */
  void setLocal(boolean value);

  /**
   * Returns the value of the '<em><b>Opposite</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Opposite</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Opposite</em>' reference.
   * @see #setOpposite(EReference)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXStructuralFeature_Opposite()
   * @model
   * @generated
   */
  EReference getOpposite();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#getOpposite <em>Opposite</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Opposite</em>' reference.
   * @see #getOpposite()
   * @generated
   */
  void setOpposite(EReference value);

  /**
   * Returns the value of the '<em><b>Keys</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EAttribute}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Keys</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Keys</em>' reference list.
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXStructuralFeature_Keys()
   * @model
   * @generated
   */
  EList<EAttribute> getKeys();

  /**
   * Returns the value of the '<em><b>Get Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Get Body</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Get Body</em>' containment reference.
   * @see #setGetBody(XBlockExpression)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXStructuralFeature_GetBody()
   * @model containment="true"
   * @generated
   */
  XBlockExpression getGetBody();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#getGetBody <em>Get Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Get Body</em>' containment reference.
   * @see #getGetBody()
   * @generated
   */
  void setGetBody(XBlockExpression value);

  /**
   * Returns the value of the '<em><b>Set Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Set Body</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Set Body</em>' containment reference.
   * @see #setSetBody(XBlockExpression)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXStructuralFeature_SetBody()
   * @model containment="true"
   * @generated
   */
  XBlockExpression getSetBody();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#getSetBody <em>Set Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Set Body</em>' containment reference.
   * @see #getSetBody()
   * @generated
   */
  void setSetBody(XBlockExpression value);

  /**
   * Returns the value of the '<em><b>Is Set Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Is Set Body</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Is Set Body</em>' containment reference.
   * @see #setIsSetBody(XBlockExpression)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXStructuralFeature_IsSetBody()
   * @model containment="true"
   * @generated
   */
  XBlockExpression getIsSetBody();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#getIsSetBody <em>Is Set Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Is Set Body</em>' containment reference.
   * @see #getIsSetBody()
   * @generated
   */
  void setIsSetBody(XBlockExpression value);

  /**
   * Returns the value of the '<em><b>Unset Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unset Body</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unset Body</em>' containment reference.
   * @see #setUnsetBody(XBlockExpression)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXStructuralFeature_UnsetBody()
   * @model containment="true"
   * @generated
   */
  XBlockExpression getUnsetBody();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#getUnsetBody <em>Unset Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unset Body</em>' containment reference.
   * @see #getUnsetBody()
   * @generated
   */
  void setUnsetBody(XBlockExpression value);

  /**
   * Returns the value of the '<em><b>Reference</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Reference</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Reference</em>' attribute.
   * @see #setReference(boolean)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXStructuralFeature_Reference()
   * @model
   * @generated
   */
  boolean isReference();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isReference <em>Reference</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Reference</em>' attribute.
   * @see #isReference()
   * @generated
   */
  void setReference(boolean value);

} // XStructuralFeature
