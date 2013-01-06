/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.common.reification;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.common.reification.Root#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.Root#getContainer <em>Container</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.Root#getUnboundedGenericContainerWithRawType <em>Unbounded Generic Container With Raw Type</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.Root#getUnboundedGenericContainerWithWildcard <em>Unbounded Generic Container With Wildcard</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.Root#getUnboundedGenericContainerWithSuper <em>Unbounded Generic Container With Super</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.Root#getUnboundedGenericContainerWithExtends <em>Unbounded Generic Container With Extends</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.Root#getBoundedGenericContainerWithRawType <em>Bounded Generic Container With Raw Type</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.Root#getBoundedGenericContainerWithWildcard <em>Bounded Generic Container With Wildcard</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.Root#getBoundedGenericContainerWithSuper <em>Bounded Generic Container With Super</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.Root#getBoundedGenericContainerWithExtends <em>Bounded Generic Container With Extends</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.common.reification.ReificationPackage#getRoot()
 * @model
 * @generated
 */
public interface Root extends EObject
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
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getRoot_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.common.reification.Root#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Container</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Container</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Container</em>' containment reference.
   * @see #setContainer(Container)
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getRoot_Container()
   * @model containment="true"
   * @generated
   */
  Container getContainer();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.common.reification.Root#getContainer <em>Container</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Container</em>' containment reference.
   * @see #getContainer()
   * @generated
   */
  void setContainer(Container value);

  /**
   * Returns the value of the '<em><b>Unbounded Generic Container With Raw Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unbounded Generic Container With Raw Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unbounded Generic Container With Raw Type</em>' containment reference.
   * @see #setUnboundedGenericContainerWithRawType(UnboundedGenericContainer)
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getRoot_UnboundedGenericContainerWithRawType()
   * @model containment="true"
   * @generated
   */
  @SuppressWarnings("rawtypes")
  UnboundedGenericContainer getUnboundedGenericContainerWithRawType();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.common.reification.Root#getUnboundedGenericContainerWithRawType <em>Unbounded Generic Container With Raw Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unbounded Generic Container With Raw Type</em>' containment reference.
   * @see #getUnboundedGenericContainerWithRawType()
   * @generated
   */
  @SuppressWarnings("rawtypes")
  void setUnboundedGenericContainerWithRawType(UnboundedGenericContainer value);

  /**
   * Returns the value of the '<em><b>Unbounded Generic Container With Wildcard</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unbounded Generic Container With Wildcard</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unbounded Generic Container With Wildcard</em>' containment reference.
   * @see #setUnboundedGenericContainerWithWildcard(UnboundedGenericContainer)
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getRoot_UnboundedGenericContainerWithWildcard()
   * @model containment="true"
   * @generated
   */
  UnboundedGenericContainer<?> getUnboundedGenericContainerWithWildcard();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.common.reification.Root#getUnboundedGenericContainerWithWildcard <em>Unbounded Generic Container With Wildcard</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unbounded Generic Container With Wildcard</em>' containment reference.
   * @see #getUnboundedGenericContainerWithWildcard()
   * @generated
   */
  void setUnboundedGenericContainerWithWildcard(UnboundedGenericContainer<?> value);

  /**
   * Returns the value of the '<em><b>Unbounded Generic Container With Super</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unbounded Generic Container With Super</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unbounded Generic Container With Super</em>' containment reference.
   * @see #setUnboundedGenericContainerWithSuper(UnboundedGenericContainer)
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getRoot_UnboundedGenericContainerWithSuper()
   * @model containment="true"
   * @generated
   */
  UnboundedGenericContainer<? super Medium> getUnboundedGenericContainerWithSuper();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.common.reification.Root#getUnboundedGenericContainerWithSuper <em>Unbounded Generic Container With Super</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unbounded Generic Container With Super</em>' containment reference.
   * @see #getUnboundedGenericContainerWithSuper()
   * @generated
   */
  void setUnboundedGenericContainerWithSuper(UnboundedGenericContainer<? super Medium> value);

  /**
   * Returns the value of the '<em><b>Unbounded Generic Container With Extends</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unbounded Generic Container With Extends</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unbounded Generic Container With Extends</em>' containment reference.
   * @see #setUnboundedGenericContainerWithExtends(UnboundedGenericContainer)
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getRoot_UnboundedGenericContainerWithExtends()
   * @model containment="true"
   * @generated
   */
  UnboundedGenericContainer<? extends Medium> getUnboundedGenericContainerWithExtends();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.common.reification.Root#getUnboundedGenericContainerWithExtends <em>Unbounded Generic Container With Extends</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unbounded Generic Container With Extends</em>' containment reference.
   * @see #getUnboundedGenericContainerWithExtends()
   * @generated
   */
  void setUnboundedGenericContainerWithExtends(UnboundedGenericContainer<? extends Medium> value);

  /**
   * Returns the value of the '<em><b>Bounded Generic Container With Raw Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bounded Generic Container With Raw Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bounded Generic Container With Raw Type</em>' containment reference.
   * @see #setBoundedGenericContainerWithRawType(BoundedGenericContainer)
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getRoot_BoundedGenericContainerWithRawType()
   * @model containment="true"
   * @generated
   */
  @SuppressWarnings("rawtypes")
  BoundedGenericContainer getBoundedGenericContainerWithRawType();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.common.reification.Root#getBoundedGenericContainerWithRawType <em>Bounded Generic Container With Raw Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bounded Generic Container With Raw Type</em>' containment reference.
   * @see #getBoundedGenericContainerWithRawType()
   * @generated
   */
  @SuppressWarnings("rawtypes")
  void setBoundedGenericContainerWithRawType(BoundedGenericContainer value);

  /**
   * Returns the value of the '<em><b>Bounded Generic Container With Wildcard</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bounded Generic Container With Wildcard</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bounded Generic Container With Wildcard</em>' containment reference.
   * @see #setBoundedGenericContainerWithWildcard(BoundedGenericContainer)
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getRoot_BoundedGenericContainerWithWildcard()
   * @model containment="true"
   * @generated
   */
  BoundedGenericContainer<?> getBoundedGenericContainerWithWildcard();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.common.reification.Root#getBoundedGenericContainerWithWildcard <em>Bounded Generic Container With Wildcard</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bounded Generic Container With Wildcard</em>' containment reference.
   * @see #getBoundedGenericContainerWithWildcard()
   * @generated
   */
  void setBoundedGenericContainerWithWildcard(BoundedGenericContainer<?> value);

  /**
   * Returns the value of the '<em><b>Bounded Generic Container With Super</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bounded Generic Container With Super</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bounded Generic Container With Super</em>' containment reference.
   * @see #setBoundedGenericContainerWithSuper(BoundedGenericContainer)
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getRoot_BoundedGenericContainerWithSuper()
   * @model containment="true"
   * @generated
   */
  BoundedGenericContainer<? super Medium> getBoundedGenericContainerWithSuper();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.common.reification.Root#getBoundedGenericContainerWithSuper <em>Bounded Generic Container With Super</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bounded Generic Container With Super</em>' containment reference.
   * @see #getBoundedGenericContainerWithSuper()
   * @generated
   */
  void setBoundedGenericContainerWithSuper(BoundedGenericContainer<? super Medium> value);

  /**
   * Returns the value of the '<em><b>Bounded Generic Container With Extends</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Bounded Generic Container With Extends</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Bounded Generic Container With Extends</em>' containment reference.
   * @see #setBoundedGenericContainerWithExtends(BoundedGenericContainer)
   * @see org.eclipse.emf.test.common.reification.ReificationPackage#getRoot_BoundedGenericContainerWithExtends()
   * @model containment="true"
   * @generated
   */
  BoundedGenericContainer<? extends Medium> getBoundedGenericContainerWithExtends();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.common.reification.Root#getBoundedGenericContainerWithExtends <em>Bounded Generic Container With Extends</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Bounded Generic Container With Extends</em>' containment reference.
   * @see #getBoundedGenericContainerWithExtends()
   * @generated
   */
  void setBoundedGenericContainerWithExtends(BoundedGenericContainer<? extends Medium> value);

} // Root
