/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: TreeNode.java,v 1.1 2010/04/28 14:48:45 emerks Exp $
 */
package org.eclipse.emf.edit.tree;


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
 *   <li>{@link org.eclipse.emf.edit.tree.TreeNode#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipse.emf.edit.tree.TreeNode#getChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipse.emf.edit.tree.TreeNode#getData <em>Data</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.edit.tree.TreePackage#getTreeNode()
 * @model
 * @generated
 */
public interface TreeNode extends EObject
{
  /**
   * Returns the value of the '<em><b>Parent</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.edit.tree.TreeNode#getChildren <em>Children</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parent</em>' container reference.
   * @see #setParent(TreeNode)
   * @see org.eclipse.emf.edit.tree.TreePackage#getTreeNode_Parent()
   * @see org.eclipse.emf.edit.tree.TreeNode#getChildren
   * @model opposite="children"
   * @generated
   */
  TreeNode getParent();

  /**
   * Sets the value of the '{@link org.eclipse.emf.edit.tree.TreeNode#getParent <em>Parent</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parent</em>' container reference.
   * @see #getParent()
   * @generated
   */
  void setParent(TreeNode value);

  /**
   * Returns the value of the '<em><b>Children</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.edit.tree.TreeNode}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.edit.tree.TreeNode#getParent <em>Parent</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Children</em>' containment reference list.
   * @see org.eclipse.emf.edit.tree.TreePackage#getTreeNode_Children()
   * @see org.eclipse.emf.edit.tree.TreeNode#getParent
   * @model opposite="parent" containment="true"
   * @generated
   */
  EList<TreeNode> getChildren();

  /**
   * Returns the value of the '<em><b>Data</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data</em>' reference.
   * @see #setData(EObject)
   * @see org.eclipse.emf.edit.tree.TreePackage#getTreeNode_Data()
   * @model required="true"
   * @generated
   */
  EObject getData();

  /**
   * Sets the value of the '{@link org.eclipse.emf.edit.tree.TreeNode#getData <em>Data</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data</em>' reference.
   * @see #getData()
   * @generated
   */
  void setData(EObject value);

} // TreeNode
