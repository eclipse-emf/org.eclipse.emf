/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.models.tree.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.tree.Data;
import org.eclipse.emf.test.models.tree.Node;
import org.eclipse.emf.test.models.tree.TreePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.tree.impl.NodeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.tree.impl.NodeImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.tree.impl.NodeImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.tree.impl.NodeImpl#getData <em>Data</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.tree.impl.NodeImpl#getRelatedNodes <em>Related Nodes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NodeImpl extends EObjectImpl implements Node
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getChildren()
   * @generated
   * @ordered
   */
  protected EList<Node> children;

  /**
   * The cached value of the '{@link #getData() <em>Data</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getData()
   * @generated
   * @ordered
   */
  protected Data data;

  /**
   * The cached value of the '{@link #getRelatedNodes() <em>Related Nodes</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRelatedNodes()
   * @generated
   * @ordered
   */
  protected EList<Node> relatedNodes;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NodeImpl()
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
    return TreePackage.Literals.NODE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.NODE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Node getParent()
  {
    if (eContainerFeatureID() != TreePackage.NODE__PARENT) return null;
    return (Node)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParent(Node newParent, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newParent, TreePackage.NODE__PARENT, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParent(Node newParent)
  {
    if (newParent != eInternalContainer() || (eContainerFeatureID() != TreePackage.NODE__PARENT && newParent != null))
    {
      if (EcoreUtil.isAncestor(this, newParent))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newParent != null)
        msgs = ((InternalEObject)newParent).eInverseAdd(this, TreePackage.NODE__CHILDREN, Node.class, msgs);
      msgs = basicSetParent(newParent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.NODE__PARENT, newParent, newParent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Node> getChildren()
  {
    if (children == null)
    {
      children = new EObjectContainmentWithInverseEList<Node>(Node.class, this, TreePackage.NODE__CHILDREN, TreePackage.NODE__PARENT);
    }
    return children;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Data getData()
  {
    if (data != null && data.eIsProxy())
    {
      InternalEObject oldData = (InternalEObject)data;
      data = (Data)eResolveProxy(oldData);
      if (data != oldData)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, TreePackage.NODE__DATA, oldData, data));
      }
    }
    return data;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Data basicGetData()
  {
    return data;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetData(Data newData, NotificationChain msgs)
  {
    Data oldData = data;
    data = newData;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TreePackage.NODE__DATA, oldData, newData);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setData(Data newData)
  {
    if (newData != data)
    {
      NotificationChain msgs = null;
      if (data != null)
        msgs = ((InternalEObject)data).eInverseRemove(this, TreePackage.DATA__NODE, Data.class, msgs);
      if (newData != null)
        msgs = ((InternalEObject)newData).eInverseAdd(this, TreePackage.DATA__NODE, Data.class, msgs);
      msgs = basicSetData(newData, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TreePackage.NODE__DATA, newData, newData));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Node> getRelatedNodes()
  {
    if (relatedNodes == null)
    {
      relatedNodes = new EObjectResolvingEList<Node>(Node.class, this, TreePackage.NODE__RELATED_NODES);
    }
    return relatedNodes;
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
      case TreePackage.NODE__PARENT:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetParent((Node)otherEnd, msgs);
      case TreePackage.NODE__CHILDREN:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildren()).basicAdd(otherEnd, msgs);
      case TreePackage.NODE__DATA:
        if (data != null)
          msgs = ((InternalEObject)data).eInverseRemove(this, TreePackage.DATA__NODE, Data.class, msgs);
        return basicSetData((Data)otherEnd, msgs);
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
      case TreePackage.NODE__PARENT:
        return basicSetParent(null, msgs);
      case TreePackage.NODE__CHILDREN:
        return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
      case TreePackage.NODE__DATA:
        return basicSetData(null, msgs);
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
      case TreePackage.NODE__PARENT:
        return eInternalContainer().eInverseRemove(this, TreePackage.NODE__CHILDREN, Node.class, msgs);
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
      case TreePackage.NODE__NAME:
        return getName();
      case TreePackage.NODE__PARENT:
        return getParent();
      case TreePackage.NODE__CHILDREN:
        return getChildren();
      case TreePackage.NODE__DATA:
        if (resolve) return getData();
        return basicGetData();
      case TreePackage.NODE__RELATED_NODES:
        return getRelatedNodes();
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
      case TreePackage.NODE__NAME:
        setName((String)newValue);
        return;
      case TreePackage.NODE__PARENT:
        setParent((Node)newValue);
        return;
      case TreePackage.NODE__CHILDREN:
        getChildren().clear();
        getChildren().addAll((Collection<? extends Node>)newValue);
        return;
      case TreePackage.NODE__DATA:
        setData((Data)newValue);
        return;
      case TreePackage.NODE__RELATED_NODES:
        getRelatedNodes().clear();
        getRelatedNodes().addAll((Collection<? extends Node>)newValue);
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
      case TreePackage.NODE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case TreePackage.NODE__PARENT:
        setParent((Node)null);
        return;
      case TreePackage.NODE__CHILDREN:
        getChildren().clear();
        return;
      case TreePackage.NODE__DATA:
        setData((Data)null);
        return;
      case TreePackage.NODE__RELATED_NODES:
        getRelatedNodes().clear();
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
      case TreePackage.NODE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case TreePackage.NODE__PARENT:
        return getParent() != null;
      case TreePackage.NODE__CHILDREN:
        return children != null && !children.isEmpty();
      case TreePackage.NODE__DATA:
        return data != null;
      case TreePackage.NODE__RELATED_NODES:
        return relatedNodes != null && !relatedNodes.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //NodeImpl
