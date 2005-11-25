/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
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
 * $Id: XMLTypeDocumentRootImpl.java,v 1.7 2005/11/25 13:12:13 emerks Exp $
 */
package org.eclipse.emf.ecore.xml.type.impl;


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
    return XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT;
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
      xMLNSPrefixMap = new EcoreEMap(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
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
      xSISchemaLocation = new EcoreEMap(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
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
    return (String)getMixed().get(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setText(String newText)
  {
    ((FeatureMap.Internal)getMixed()).set(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT, newText);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__MIXED:
        return ((InternalEList)getMixed()).basicRemove(otherEnd, msgs);
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        return ((InternalEList)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        return ((InternalEList)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
    }
    return eDynamicInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__MIXED:
        if (coreType) return getMixed();
        return ((FeatureMap.Internal)getMixed()).getWrapper();
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        if (coreType) return getXMLNSPrefixMap();
        else return getXMLNSPrefixMap().map();
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        if (coreType) return getXSISchemaLocation();
        else return getXSISchemaLocation().map();
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__CDATA:
        return getCDATA();
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__COMMENT:
        return getComment();
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__TEXT:
        return getText();
    }
    return eDynamicGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__MIXED:
        ((FeatureMap.Internal)getMixed()).set(newValue);
        return;
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        ((EStructuralFeature.Setting)getXMLNSPrefixMap()).set(newValue);
        return;
      case XMLTypePackage.XML_TYPE_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        ((EStructuralFeature.Setting)getXSISchemaLocation()).set(newValue);
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
    eDynamicSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(int featureID)
  {
    switch (featureID)
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
    eDynamicUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
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
    return eDynamicIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCDATA()
  {
    return (String)getMixed().get(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__CDATA, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCDATA(String newCDATA)
  {
    ((FeatureMap.Internal)getMixed()).set(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__CDATA, newCDATA);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getComment()
  {
    return (String)getMixed().get(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__COMMENT, true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setComment(String newComment)
  {
    ((FeatureMap.Internal)getMixed()).set(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__COMMENT, newComment);
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
