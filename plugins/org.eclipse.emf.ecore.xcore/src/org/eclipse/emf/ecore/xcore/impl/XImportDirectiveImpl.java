/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.impl;


import java.util.List;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.xcore.XImportDirective;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XImport Directive</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XImportDirectiveImpl#getImportedNamespace <em>Imported Namespace</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XImportDirectiveImpl#getImportedObject <em>Imported Object</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XImportDirectiveImpl#getPackage <em>Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XImportDirectiveImpl extends XModelElementImpl implements XImportDirective
{
  /**
   * The default value of the '{@link #getImportedNamespace() <em>Imported Namespace</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImportedNamespace()
   * @generated
   * @ordered
   */
  protected static final String IMPORTED_NAMESPACE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getImportedNamespace() <em>Imported Namespace</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImportedNamespace()
   * @generated
   * @ordered
   */
  protected String importedNamespace = IMPORTED_NAMESPACE_EDEFAULT;

  /**
   * The cached value of the '{@link #getImportedObject() <em>Imported Object</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImportedObject()
   * @generated
   * @ordered
   */
  protected EObject importedObject;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XImportDirectiveImpl()
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
    return XcorePackage.Literals.XIMPORT_DIRECTIVE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getImportedNamespaceGen()
  {
    return importedNamespace;
  }

  public String getImportedNamespace()
  {
    String result = getImportedNamespaceGen();
    if (result != null)
    {
      return result;
    }
    else
    {
      List<INode> nodes = NodeModelUtils.findNodesForFeature(this, XcorePackage.Literals.XIMPORT_DIRECTIVE__IMPORTED_OBJECT);
      StringBuilder builder = new StringBuilder();
      for (INode node : nodes)
      {
        builder.append(NodeModelUtils.getTokenText(node));
      }
      return builder.toString().replace(" ", "").replace("^", "");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setImportedNamespace(String newImportedNamespace)
  {
    String oldImportedNamespace = importedNamespace;
    importedNamespace = newImportedNamespace;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XIMPORT_DIRECTIVE__IMPORTED_NAMESPACE, oldImportedNamespace, importedNamespace));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject getImportedObject()
  {
    if (importedObject != null && importedObject.eIsProxy())
    {
      InternalEObject oldImportedObject = (InternalEObject)importedObject;
      importedObject = eResolveProxy(oldImportedObject);
      if (importedObject != oldImportedObject)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, XcorePackage.XIMPORT_DIRECTIVE__IMPORTED_OBJECT, oldImportedObject, importedObject));
      }
    }
    return importedObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EObject basicGetImportedObject()
  {
    return importedObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setImportedObject(EObject newImportedObject)
  {
    EObject oldImportedObject = importedObject;
    importedObject = newImportedObject;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XIMPORT_DIRECTIVE__IMPORTED_OBJECT, oldImportedObject, importedObject));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XPackage getPackage()
  {
    if (eContainerFeatureID() != XcorePackage.XIMPORT_DIRECTIVE__PACKAGE) return null;
    return (XPackage)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case XcorePackage.XIMPORT_DIRECTIVE__PACKAGE:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return eBasicSetContainer(otherEnd, XcorePackage.XIMPORT_DIRECTIVE__PACKAGE, msgs);
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
      case XcorePackage.XIMPORT_DIRECTIVE__PACKAGE:
        return eBasicSetContainer(null, XcorePackage.XIMPORT_DIRECTIVE__PACKAGE, msgs);
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
      case XcorePackage.XIMPORT_DIRECTIVE__PACKAGE:
        return eInternalContainer().eInverseRemove(this, XcorePackage.XPACKAGE__IMPORT_DIRECTIVES, XPackage.class, msgs);
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
      case XcorePackage.XIMPORT_DIRECTIVE__IMPORTED_NAMESPACE:
        return getImportedNamespace();
      case XcorePackage.XIMPORT_DIRECTIVE__IMPORTED_OBJECT:
        if (resolve) return getImportedObject();
        return basicGetImportedObject();
      case XcorePackage.XIMPORT_DIRECTIVE__PACKAGE:
        return getPackage();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case XcorePackage.XIMPORT_DIRECTIVE__IMPORTED_NAMESPACE:
        setImportedNamespace((String)newValue);
        return;
      case XcorePackage.XIMPORT_DIRECTIVE__IMPORTED_OBJECT:
        setImportedObject((EObject)newValue);
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
      case XcorePackage.XIMPORT_DIRECTIVE__IMPORTED_NAMESPACE:
        setImportedNamespace(IMPORTED_NAMESPACE_EDEFAULT);
        return;
      case XcorePackage.XIMPORT_DIRECTIVE__IMPORTED_OBJECT:
        setImportedObject((EObject)null);
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
      case XcorePackage.XIMPORT_DIRECTIVE__IMPORTED_NAMESPACE:
        return IMPORTED_NAMESPACE_EDEFAULT == null ? importedNamespace != null : !IMPORTED_NAMESPACE_EDEFAULT.equals(importedNamespace);
      case XcorePackage.XIMPORT_DIRECTIVE__IMPORTED_OBJECT:
        return importedObject != null;
      case XcorePackage.XIMPORT_DIRECTIVE__PACKAGE:
        return getPackage() != null;
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
    result.append(" (importedNamespace: ");
    result.append(importedNamespace);
    result.append(')');
    return result.toString();
  }

} //XImportDirectiveImpl
