/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.tree;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;


/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent 
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each literal of each enum</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.edit.tree.TreeFactory
 * @model kind="package"
 * @generated
 */
public interface TreePackage extends EPackage{

  /**
   * The package name.
   * @generated
   */
  String eNAME = "tree";

  /**
   * The package namespace URI.
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/emf/2002/Tree";

  /**
   * The package namespace name.
   * @generated
   */
  String eNS_PREFIX = "tree";

  /**
   * The singleton instance of the package.
   * @generated
   */
  TreePackage eINSTANCE = org.eclipse.emf.edit.tree.impl.TreePackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.edit.tree.impl.TreeNodeImpl <em>Node</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.edit.tree.impl.TreeNodeImpl
   * @see org.eclipse.emf.edit.tree.impl.TreePackageImpl#getTreeNode()
   * @generated
   */
  int TREE_NODE = 0;

  /**
   * The feature id for the '<em><b>Parent</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TREE_NODE__PARENT = 0;

  /**
   * The feature id for the '<em><b>Children</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TREE_NODE__CHILDREN = 1;

  /**
   * The feature id for the '<em><b>Data</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TREE_NODE__DATA = 2;


  /**
   * The number of structural features of the '<em>Node</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TREE_NODE_FEATURE_COUNT = 3;


  /**
   * Returns the meta object for class '{@link org.eclipse.emf.edit.tree.TreeNode <em>Node</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Node</em>'.
   * @see org.eclipse.emf.edit.tree.TreeNode
   * @generated
   */
  EClass getTreeNode();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.edit.tree.TreeNode#getParent <em>Parent</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Parent</em>'.
   * @see org.eclipse.emf.edit.tree.TreeNode#getParent()
   * @see #getTreeNode()
   * @generated
   */
  EReference getTreeNode_Parent();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.edit.tree.TreeNode#getChildren <em>Children</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Children</em>'.
   * @see org.eclipse.emf.edit.tree.TreeNode#getChildren()
   * @see #getTreeNode()
   * @generated
   */
  EReference getTreeNode_Children();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.edit.tree.TreeNode#getData <em>Data</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Data</em>'.
   * @see org.eclipse.emf.edit.tree.TreeNode#getData()
   * @see #getTreeNode()
   * @generated
   */
  EReference getTreeNode_Data();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  TreeFactory getTreeFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.eclipse.emf.edit.tree.impl.TreeNodeImpl <em>Node</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.edit.tree.impl.TreeNodeImpl
     * @see org.eclipse.emf.edit.tree.impl.TreePackageImpl#getTreeNode()
     * @generated
     */
    EClass TREE_NODE = eINSTANCE.getTreeNode();

    /**
     * The meta object literal for the '<em><b>Parent</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TREE_NODE__PARENT = eINSTANCE.getTreeNode_Parent();

    /**
     * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TREE_NODE__CHILDREN = eINSTANCE.getTreeNode_Children();

    /**
     * The meta object literal for the '<em><b>Data</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TREE_NODE__DATA = eINSTANCE.getTreeNode_Data();

  }

} //TreePackage
