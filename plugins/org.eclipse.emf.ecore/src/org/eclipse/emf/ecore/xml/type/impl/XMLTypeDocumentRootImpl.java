/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XMLTypeDocumentRootImpl.java,v 1.3 2004/06/13 11:53:58 emerks Exp $
 */
package org.eclipse.emf.ecore.xml.type.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xml.type.impl.XMLTypeDocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xml.type.impl.XMLTypeDocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xml.type.impl.XMLTypeDocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xml.type.impl.XMLTypeDocumentRootImpl#getCDATA <em>CDATA</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xml.type.impl.XMLTypeDocumentRootImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xml.type.impl.XMLTypeDocumentRootImpl#getText <em>Text</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLTypeDocumentRootImpl extends EObjectImpl implements XMLTypeDocumentRoot
{
  /**
   * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMixed()
   * @generated
   * @ordered
   */
  protected FeatureMap mixed = null;

  /**
   * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXMLNSPrefixMap()
   * @generated
   * @ordered
   */
  protected EMap xMLNSPrefixMap = null;

  /**
   * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXSISchemaLocation()
   * @generated
   * @ordered
   */
  protected EMap xSISchemaLocation = null;

  /**
   * The default value of the '{@link #getCDATA() <em>CDATA</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCDATA()
   * @generated
   * @ordered
   */
  protected static final String CDATA_EDEFAULT = null;

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
   * The default value of the '{@link #getText() <em>Text</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getText()
   * @generated
   * @ordered
   */
  protected static final String TEXT_EDEFAULT = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XMLTypeDocumentRootImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureMap getMixed()
  {
    if (mixed == null)
    {
      mixed = new BasicFeatureMap(this, XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__MIXED);
    }
    return mixed;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap getXMLNSPrefixMap()
  {
    if (xMLNSPrefixMap == null)
    {
      xMLNSPrefixMap = new EcoreEMap(EcorePackage.eINSTANCE.getEStringToStringMapEntry(), EStringToStringMapEntryImpl.class, this, XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
    }
    return xMLNSPrefixMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap getXSISchemaLocation()
  {
    if (xSISchemaLocation == null)
    {
      xSISchemaLocation = new EcoreEMap(EcorePackage.eINSTANCE.getEStringToStringMapEntry(), EStringToStringMapEntryImpl.class, this, XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
    }
    return xSISchemaLocation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getText()
  {
    return (String)getMixed().get(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setText(String newText)
  {
    ((FeatureMap.Internal)getMixed()).set(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text(), newText);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__MIXED:
          return ((InternalEList)getMixed()).basicRemove(otherEnd, msgs);
        case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
          return ((InternalEList)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
        case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
          return ((InternalEList)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCDATA()
  {
    return (String)getMixed().get(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_CDATA(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCDATA(String newCDATA)
  {
    ((FeatureMap.Internal)getMixed()).set(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_CDATA(), newCDATA);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getComment()
  {
    return (String)getMixed().get(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Comment(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComment(String newComment)
  {
    ((FeatureMap.Internal)getMixed()).set(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Comment(), newComment);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__MIXED:
        return getMixed();
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        return getXMLNSPrefixMap();
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        return getXSISchemaLocation();
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__CDATA:
        return getCDATA();
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__COMMENT:
        return getComment();
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__TEXT:
        return getText();
    }
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(EStructuralFeature eFeature, Object newValue)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__MIXED:
        getMixed().clear();
        getMixed().addAll((Collection)newValue);
        return;
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        getXMLNSPrefixMap().clear();
        getXMLNSPrefixMap().addAll((Collection)newValue);
        return;
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        getXSISchemaLocation().clear();
        getXSISchemaLocation().addAll((Collection)newValue);
        return;
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__CDATA:
        setCDATA((String)newValue);
        return;
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__COMMENT:
        setComment((String)newValue);
        return;
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__TEXT:
        setText((String)newValue);
        return;
    }
    eDynamicSet(eFeature, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__MIXED:
        getMixed().clear();
        return;
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        getXMLNSPrefixMap().clear();
        return;
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        getXSISchemaLocation().clear();
        return;
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__CDATA:
        setCDATA(CDATA_EDEFAULT);
        return;
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__COMMENT:
        setComment(COMMENT_EDEFAULT);
        return;
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__TEXT:
        setText(TEXT_EDEFAULT);
        return;
    }
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__MIXED:
        return mixed != null && !mixed.isEmpty();
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__CDATA:
        return CDATA_EDEFAULT == null ? getCDATA() != null : !CDATA_EDEFAULT.equals(getCDATA());
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__COMMENT:
        return COMMENT_EDEFAULT == null ? getComment() != null : !COMMENT_EDEFAULT.equals(getComment());
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__TEXT:
        return TEXT_EDEFAULT == null ? getText() != null : !TEXT_EDEFAULT.equals(getText());
    }
    return eDynamicIsSet(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
