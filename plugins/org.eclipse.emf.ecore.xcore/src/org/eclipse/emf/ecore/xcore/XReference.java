/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore;


import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XReference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XReference#isContainer <em>Container</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XReference#isContainment <em>Containment</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XReference#isResolveProxies <em>Resolve Proxies</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XReference#isLocal <em>Local</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XReference#getOpposite <em>Opposite</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XReference#getKeys <em>Keys</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXReference()
 * @model
 * @generated
 */
public interface XReference extends XStructuralFeature
{
  /**
   * Returns the value of the '<em><b>Container</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Container</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Container</em>' attribute.
   * @see #setContainer(boolean)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXReference_Container()
   * @model
   * @generated
   */
  boolean isContainer();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XReference#isContainer <em>Container</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Container</em>' attribute.
   * @see #isContainer()
   * @generated
   */
  void setContainer(boolean value);

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
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXReference_Containment()
   * @model
   * @generated
   */
  boolean isContainment();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XReference#isContainment <em>Containment</em>}' attribute.
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
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXReference_ResolveProxies()
   * @model
   * @generated
   */
  boolean isResolveProxies();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XReference#isResolveProxies <em>Resolve Proxies</em>}' attribute.
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
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXReference_Local()
   * @model
   * @generated
   */
  boolean isLocal();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XReference#isLocal <em>Local</em>}' attribute.
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
   * @see #setOpposite(GenFeature)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXReference_Opposite()
   * @model
   * @generated
   */
  GenFeature getOpposite();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XReference#getOpposite <em>Opposite</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Opposite</em>' reference.
   * @see #getOpposite()
   * @generated
   */
  void setOpposite(GenFeature value);

  /**
   * Returns the value of the '<em><b>Keys</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Keys</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Keys</em>' reference list.
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXReference_Keys()
   * @model
   * @generated
   */
  EList<GenFeature> getKeys();

} // XReference
