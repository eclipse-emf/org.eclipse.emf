/**
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   Thales - initial API and implementation
 */
package org.eclipse.emf.test.core.xrefsopposite;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>A</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.test.core.xrefsopposite.A#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.xrefsopposite.A#getRef1 <em>Ref1</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.xrefsopposite.A#getOref1 <em>Oref1</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.xrefsopposite.A#getRef2 <em>Ref2</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.xrefsopposite.A#getOref2 <em>Oref2</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.xrefsopposite.A#getRef3 <em>Ref3</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.xrefsopposite.A#getOwningAPkg <em>Owning APkg</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.test.core.xrefsopposite.XRefsOppositePackage#getA()
 * @model
 * @generated
 */
public interface A extends AbstractA
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.eclipse.emf.test.core.xrefsopposite.XRefsOppositePackage#getA_Name()
   * @model required="true"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.core.xrefsopposite.A#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Ref1</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.core.xrefsopposite.A}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.core.xrefsopposite.A#getOref1 <em>Oref1</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ref1</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ref1</em>' reference list.
   * @see org.eclipse.emf.test.core.xrefsopposite.XRefsOppositePackage#getA_Ref1()
   * @see org.eclipse.emf.test.core.xrefsopposite.A#getOref1
   * @model opposite="oref1"
   * @generated
   */
  EList<A> getRef1();

  /**
   * Returns the value of the '<em><b>Oref1</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.core.xrefsopposite.A}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.core.xrefsopposite.A#getRef1 <em>Ref1</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Oref1</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Oref1</em>' reference list.
   * @see org.eclipse.emf.test.core.xrefsopposite.XRefsOppositePackage#getA_Oref1()
   * @see org.eclipse.emf.test.core.xrefsopposite.A#getRef1
   * @model opposite="ref1"
   * @generated
   */
  EList<A> getOref1();

  /**
   * Returns the value of the '<em><b>Ref2</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.core.xrefsopposite.A}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.core.xrefsopposite.A#getOref2 <em>Oref2</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ref2</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ref2</em>' reference list.
   * @see org.eclipse.emf.test.core.xrefsopposite.XRefsOppositePackage#getA_Ref2()
   * @see org.eclipse.emf.test.core.xrefsopposite.A#getOref2
   * @model opposite="oref2"
   * @generated
   */
  EList<A> getRef2();

  /**
   * Returns the value of the '<em><b>Oref2</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.core.xrefsopposite.A}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.core.xrefsopposite.A#getRef2 <em>Ref2</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Oref2</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Oref2</em>' reference list.
   * @see org.eclipse.emf.test.core.xrefsopposite.XRefsOppositePackage#getA_Oref2()
   * @see org.eclipse.emf.test.core.xrefsopposite.A#getRef2
   * @model opposite="ref2"
   * @generated
   */
  EList<A> getOref2();

  /**
   * Returns the value of the '<em><b>Ref3</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.core.xrefsopposite.A}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ref3</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ref3</em>' reference list.
   * @see org.eclipse.emf.test.core.xrefsopposite.XRefsOppositePackage#getA_Ref3()
   * @model
   * @generated
   */
  EList<A> getRef3();

  /**
   * Returns the value of the '<em><b>Owning APkg</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.core.xrefsopposite.APkg#getOwnedAs <em>Owned As</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Owning APkg</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Owning APkg</em>' container reference.
   * @see #setOwningAPkg(APkg)
   * @see org.eclipse.emf.test.core.xrefsopposite.XRefsOppositePackage#getA_OwningAPkg()
   * @see org.eclipse.emf.test.core.xrefsopposite.APkg#getOwnedAs
   * @model opposite="ownedAs" transient="false"
   * @generated
   */
  APkg getOwningAPkg();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.core.xrefsopposite.A#getOwningAPkg <em>Owning APkg</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Owning APkg</em>' container reference.
   * @see #getOwningAPkg()
   * @generated
   */
  void setOwningAPkg(APkg value);

} // A
