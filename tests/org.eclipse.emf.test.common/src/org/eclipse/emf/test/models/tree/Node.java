/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: Node.java,v 1.3 2007/06/12 15:08:11 emerks Exp $
 */
package org.eclipse.emf.test.models.tree;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.tree.Node#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.tree.Node#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.tree.Node#getChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.tree.Node#getData <em>Data</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.tree.Node#getRelatedNodes <em>Related Nodes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.tree.TreePackage#getNode()
 * @model
 * @generated
 */
public interface Node extends EObject
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
   * @see org.eclipse.emf.test.models.tree.TreePackage#getNode_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.tree.Node#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Parent</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.tree.Node#getChildren <em>Children</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parent</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parent</em>' container reference.
   * @see #setParent(Node)
   * @see org.eclipse.emf.test.models.tree.TreePackage#getNode_Parent()
   * @see org.eclipse.emf.test.models.tree.Node#getChildren
   * @model opposite="children"
   * @generated
   */
  Node getParent();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.tree.Node#getParent <em>Parent</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parent</em>' container reference.
   * @see #getParent()
   * @generated
   */
  void setParent(Node value);

  /**
   * Returns the value of the '<em><b>Children</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.tree.Node}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.tree.Node#getParent <em>Parent</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Children</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Children</em>' containment reference list.
   * @see org.eclipse.emf.test.models.tree.TreePackage#getNode_Children()
   * @see org.eclipse.emf.test.models.tree.Node#getParent
   * @model opposite="parent" containment="true"
   * @generated
   */
  EList<Node> getChildren();

  /**
   * Returns the value of the '<em><b>Data</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.test.models.tree.Data#getNode <em>Node</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Data</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data</em>' reference.
   * @see #setData(Data)
   * @see org.eclipse.emf.test.models.tree.TreePackage#getNode_Data()
   * @see org.eclipse.emf.test.models.tree.Data#getNode
   * @model opposite="node"
   * @generated
   */
  Data getData();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.tree.Node#getData <em>Data</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data</em>' reference.
   * @see #getData()
   * @generated
   */
  void setData(Data value);

  /**
   * Returns the value of the '<em><b>Related Nodes</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.tree.Node}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Related Nodes</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Related Nodes</em>' reference list.
   * @see org.eclipse.emf.test.models.tree.TreePackage#getNode_RelatedNodes()
   * @model
   * @generated
   */
  EList<Node> getRelatedNodes();

} // Node
