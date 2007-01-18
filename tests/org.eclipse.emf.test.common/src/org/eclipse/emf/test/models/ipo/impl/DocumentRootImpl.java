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
 * $Id: DocumentRootImpl.java,v 1.2 2007/01/18 22:06:39 marcelop Exp $
 */
package org.eclipse.emf.test.models.ipo.impl;

import commonj.sdo.Sequence;

import java.util.Map;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

import org.eclipse.emf.ecore.sdo.util.BasicESequence;
import org.eclipse.emf.ecore.sdo.util.ESequence;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.ipo.DocumentRoot;
import org.eclipse.emf.test.models.ipo.PurchaseOrderType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.DocumentRootImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.ipo.impl.DocumentRootImpl#getPurchaseOrder <em>Purchase Order</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EDataObjectImpl implements DocumentRoot
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final long serialVersionUID = 1L;

  /**
   * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMixed()
   * @generated
   * @ordered
   */
  protected ESequence mixed = null;

  /**
   * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXMLNSPrefixMap()
   * @generated
   * @ordered
   */
  protected EMap<String, String> xMLNSPrefixMap = null;

  /**
   * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXSISchemaLocation()
   * @generated
   * @ordered
   */
  protected EMap<String, String> xSISchemaLocation = null;

  /**
   * The default value of the '{@link #getComment() <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getComment()
   * @generated
   * @ordered
   */
  protected static final String COMMENT_EDEFAULT = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DocumentRootImpl()
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
    return IPOPackageImpl.Literals.DOCUMENT_ROOT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Sequence getMixed()
  {
    if (mixed == null)
    {
      mixed = new BasicESequence(new BasicFeatureMap(this, IPOPackageImpl.DOCUMENT_ROOT__MIXED));
    }
    return mixed;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map<String, String> getXMLNSPrefixMap()
  {
    if (xMLNSPrefixMap == null)
    {
      xMLNSPrefixMap = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, IPOPackageImpl.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
    }
    return xMLNSPrefixMap.map();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map<String, String> getXSISchemaLocation()
  {
    if (xSISchemaLocation == null)
    {
      xSISchemaLocation = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, IPOPackageImpl.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
    }
    return xSISchemaLocation.map();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getComment()
  {
    return (String)((FeatureMap.Internal.Wrapper)getMixed()).featureMap().get(IPOPackageImpl.Literals.DOCUMENT_ROOT__COMMENT, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComment(String newComment)
  {
    ((FeatureMap.Internal)((FeatureMap.Internal.Wrapper)getMixed()).featureMap()).set(IPOPackageImpl.Literals.DOCUMENT_ROOT__COMMENT, newComment);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PurchaseOrderType getPurchaseOrder()
  {
    return (PurchaseOrderType)((FeatureMap.Internal.Wrapper)getMixed()).featureMap().get(IPOPackageImpl.Literals.DOCUMENT_ROOT__PURCHASE_ORDER, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPurchaseOrder(PurchaseOrderType newPurchaseOrder, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)((FeatureMap.Internal.Wrapper)getMixed()).featureMap()).basicAdd(IPOPackageImpl.Literals.DOCUMENT_ROOT__PURCHASE_ORDER, newPurchaseOrder, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPurchaseOrder(PurchaseOrderType newPurchaseOrder)
  {
    ((FeatureMap.Internal)((FeatureMap.Internal.Wrapper)getMixed()).featureMap()).set(IPOPackageImpl.Literals.DOCUMENT_ROOT__PURCHASE_ORDER, newPurchaseOrder);
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
      case IPOPackageImpl.DOCUMENT_ROOT__MIXED:
        return ((InternalEList<?>)((FeatureMap.Internal.Wrapper)getMixed()).featureMap()).basicRemove(otherEnd, msgs);
      case IPOPackageImpl.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        return ((InternalEList<?>)((EMap.InternalMapView<String, String>)getXMLNSPrefixMap()).eMap()).basicRemove(otherEnd, msgs);
      case IPOPackageImpl.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        return ((InternalEList<?>)((EMap.InternalMapView<String, String>)getXSISchemaLocation()).eMap()).basicRemove(otherEnd, msgs);
      case IPOPackageImpl.DOCUMENT_ROOT__PURCHASE_ORDER:
        return basicSetPurchaseOrder(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case IPOPackageImpl.DOCUMENT_ROOT__MIXED:
        if (coreType) return ((FeatureMap.Internal.Wrapper)getMixed()).featureMap();
        return getMixed();
      case IPOPackageImpl.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        if (coreType) return ((EMap.InternalMapView<String, String>)getXMLNSPrefixMap()).eMap();
        else return getXMLNSPrefixMap();
      case IPOPackageImpl.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        if (coreType) return ((EMap.InternalMapView<String, String>)getXSISchemaLocation()).eMap();
        else return getXSISchemaLocation();
      case IPOPackageImpl.DOCUMENT_ROOT__COMMENT:
        return getComment();
      case IPOPackageImpl.DOCUMENT_ROOT__PURCHASE_ORDER:
        return getPurchaseOrder();
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
      case IPOPackageImpl.DOCUMENT_ROOT__MIXED:
        ((FeatureMap.Internal)((FeatureMap.Internal.Wrapper)getMixed()).featureMap()).set(newValue);
        return;
      case IPOPackageImpl.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        ((EStructuralFeature.Setting)((EMap.InternalMapView<String, String>)getXMLNSPrefixMap()).eMap()).set(newValue);
        return;
      case IPOPackageImpl.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        ((EStructuralFeature.Setting)((EMap.InternalMapView<String, String>)getXSISchemaLocation()).eMap()).set(newValue);
        return;
      case IPOPackageImpl.DOCUMENT_ROOT__COMMENT:
        setComment((String)newValue);
        return;
      case IPOPackageImpl.DOCUMENT_ROOT__PURCHASE_ORDER:
        setPurchaseOrder((PurchaseOrderType)newValue);
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
      case IPOPackageImpl.DOCUMENT_ROOT__MIXED:
        ((FeatureMap.Internal.Wrapper)getMixed()).featureMap().clear();
        return;
      case IPOPackageImpl.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        getXMLNSPrefixMap().clear();
        return;
      case IPOPackageImpl.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        getXSISchemaLocation().clear();
        return;
      case IPOPackageImpl.DOCUMENT_ROOT__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case IPOPackageImpl.DOCUMENT_ROOT__PURCHASE_ORDER:
        setPurchaseOrder((PurchaseOrderType)null);
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
      case IPOPackageImpl.DOCUMENT_ROOT__MIXED:
        return mixed != null && !mixed.featureMap().isEmpty();
      case IPOPackageImpl.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
      case IPOPackageImpl.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
      case IPOPackageImpl.DOCUMENT_ROOT__COMMENT:
        return COMMENT_EDEFAULT == null ? getComment() != null : !COMMENT_EDEFAULT.equals(getComment());
      case IPOPackageImpl.DOCUMENT_ROOT__PURCHASE_ORDER:
        return getPurchaseOrder() != null;
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
    result.append(" (mixed: ");
    result.append(mixed);
    result.append(')');
    return result.toString();
  }

} //DocumentRootImpl
