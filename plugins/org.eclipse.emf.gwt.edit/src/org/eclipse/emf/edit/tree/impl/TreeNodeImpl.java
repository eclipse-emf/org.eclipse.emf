/**
 * <copyright>
 *
 * Copyright (c) 2002-2010 IBM Corporation and others.
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
 * $Id: TreeNodeImpl.java,v 1.3 2010/05/17 13:17:43 emerks Exp $
 */
package org.eclipse.emf.edit.tree.impl;


import com.google.gwt.user.client.rpc.GwtTransient;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.edit.tree.TreeNode;
import org.eclipse.emf.edit.tree.TreePackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.edit.tree.impl.TreeNodeImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipse.emf.edit.tree.impl.TreeNodeImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipse.emf.edit.tree.impl.TreeNodeImpl#getData <em>Data</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TreeNodeImpl extends EObjectImpl implements TreeNode
{
  /**
   * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getChildren()
   * @generated
   * @ordered
   */
  @GwtTransient
  protected EList<TreeNode> children;

  /**
   * The cached value of the '{@link #getData() <em>Data</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getData()
   * @generated
   * @ordered
   */
  @GwtTransient
  protected EObject data;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  protected TreeNodeImpl() 
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return TreePackage.Literals.TREE_NODE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TreeNode getParent()
  {
    if (eContainerFeatureID() != TreePackage.TREE_NODE__PARENT) return null;
    return (TreeNode)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParent(TreeNode newParent, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newParent, TreePackage.TREE_NODE__PARENT, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParent(TreeNode newParent)
  {
    if (newParent != eInternalContainer() || (eContainerFeatureID() != TreePackage.TREE_NODE__PARENT && newParent != null))
    {
      if (EcoreUtil.isAncestor(this, newParent))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newParent != null)
        msgs = ((InternalEObject)newParent).eInverseAdd(this, TreePackage.TREE_NODE__CHILDREN, TreeNode.class, msgs);
      msgs = basicSetParent(newParent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.TREE_NODE__PARENT, newParent, newParent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<TreeNode> getChildren()
  {
    if (children == null)
    {
      children = new EObjectContainmentWithInverseEList<TreeNode>(TreeNode.class, this, TreePackage.TREE_NODE__CHILDREN, TreePackage.TREE_NODE__PARENT);
    }
    return children;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getData()
  {
    if (data != null && data.eIsProxy())
    {
      InternalEObject oldData = (InternalEObject)data;
      data = eResolveProxy(oldData);
      if (data != oldData)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, TreePackage.TREE_NODE__DATA, oldData, data));
      }
    }
    return data;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject basicGetData()
  {
    return data;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setData(EObject newData)
  {
    EObject oldData = data;
    data = newData;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.TREE_NODE__DATA, oldData, data));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case TreePackage.TREE_NODE__PARENT:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetParent((TreeNode)otherEnd, msgs);
      case TreePackage.TREE_NODE__CHILDREN:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case TreePackage.TREE_NODE__PARENT:
        return basicSetParent(null, msgs);
      case TreePackage.TREE_NODE__CHILDREN:
        return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
  {
    switch (eContainerFeatureID())
    {
      case TreePackage.TREE_NODE__PARENT:
        return eInternalContainer().eInverseRemove(this, TreePackage.TREE_NODE__CHILDREN, TreeNode.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case TreePackage.TREE_NODE__PARENT:
        return getParent();
      case TreePackage.TREE_NODE__CHILDREN:
        return getChildren();
      case TreePackage.TREE_NODE__DATA:
        if (resolve) return getData();
        return basicGetData();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case TreePackage.TREE_NODE__PARENT:
        setParent((TreeNode)newValue);
        return;
      case TreePackage.TREE_NODE__CHILDREN:
        getChildren().clear();
        getChildren().addAll((Collection<? extends TreeNode>)newValue);
        return;
      case TreePackage.TREE_NODE__DATA:
        setData((EObject)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case TreePackage.TREE_NODE__PARENT:
        setParent((TreeNode)null);
        return;
      case TreePackage.TREE_NODE__CHILDREN:
        getChildren().clear();
        return;
      case TreePackage.TREE_NODE__DATA:
        setData((EObject)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case TreePackage.TREE_NODE__PARENT:
        return getParent() != null;
      case TreePackage.TREE_NODE__CHILDREN:
        return children != null && !children.isEmpty();
      case TreePackage.TREE_NODE__DATA:
        return data != null;
    }
    return super.eIsSet(featureID);
  }

} //TreeNodeImpl
