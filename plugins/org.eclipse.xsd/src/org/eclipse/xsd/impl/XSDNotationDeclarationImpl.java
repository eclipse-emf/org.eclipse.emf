/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: XSDNotationDeclarationImpl.java,v 1.2 2004/06/13 11:52:18 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDNotationDeclaration;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Notation Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDNotationDeclarationImpl#getSystemIdentifier <em>System Identifier</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDNotationDeclarationImpl#getPublicIdentifier <em>Public Identifier</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDNotationDeclarationImpl#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDNotationDeclarationImpl 
  extends XSDNamedComponentImpl 
  implements XSDNotationDeclaration
{
  /**
   * The default value of the '{@link #getSystemIdentifier() <em>System Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSystemIdentifier()
   * @generated
   * @ordered
   */
  protected static final String SYSTEM_IDENTIFIER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSystemIdentifier() <em>System Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSystemIdentifier()
   * @generated
   * @ordered
   */
  protected String systemIdentifier = SYSTEM_IDENTIFIER_EDEFAULT;

  /**
   * The default value of the '{@link #getPublicIdentifier() <em>Public Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPublicIdentifier()
   * @generated
   * @ordered
   */
  protected static final String PUBLIC_IDENTIFIER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPublicIdentifier() <em>Public Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPublicIdentifier()
   * @generated
   * @ordered
   */
  protected String publicIdentifier = PUBLIC_IDENTIFIER_EDEFAULT;

  /**
   * The cached value of the '{@link #getAnnotation() <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotation()
   * @generated
   * @ordered
   */
  protected XSDAnnotation annotation = null;

  public static XSDNotationDeclaration createNotationDeclaration(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.NOTATION_ELEMENT)
    {
      XSDNotationDeclaration xsdNotationDeclaration = XSDFactory.eINSTANCE.createXSDNotationDeclaration();
      xsdNotationDeclaration.setElement((Element)node);
      return xsdNotationDeclaration;
    }

    return null;
  }

  protected XSDNotationDeclarationImpl() 
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
    return XSDPackage.eINSTANCE.getXSDNotationDeclaration();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSystemIdentifier()
  {
    return systemIdentifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSystemIdentifier(String newSystemIdentifier)
  {
    String oldSystemIdentifier = systemIdentifier;
    systemIdentifier = newSystemIdentifier;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_NOTATION_DECLARATION__SYSTEM_IDENTIFIER, oldSystemIdentifier, systemIdentifier));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPublicIdentifier()
  {
    return publicIdentifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPublicIdentifier(String newPublicIdentifier)
  {
    String oldPublicIdentifier = publicIdentifier;
    publicIdentifier = newPublicIdentifier;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_NOTATION_DECLARATION__PUBLIC_IDENTIFIER, oldPublicIdentifier, publicIdentifier));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDAnnotation getAnnotation()
  {
    return annotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnnotation(XSDAnnotation newAnnotation)
  {
    if (newAnnotation != annotation)
    {
      NotificationChain msgs = null;
      if (annotation != null)
        msgs = ((InternalEObject)annotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_NOTATION_DECLARATION__ANNOTATION, null, msgs);
      if (newAnnotation != null)
        msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_NOTATION_DECLARATION__ANNOTATION, null, msgs);
      msgs = basicSetAnnotation(newAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_NOTATION_DECLARATION__ANNOTATION, newAnnotation, newAnnotation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAnnotation(XSDAnnotation newAnnotation, NotificationChain msgs)
  {
    XSDAnnotation oldAnnotation = annotation;
    annotation = newAnnotation;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_NOTATION_DECLARATION__ANNOTATION, oldAnnotation, newAnnotation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
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
        case XSDPackage.XSD_NOTATION_DECLARATION__DIAGNOSTICS:
          return ((InternalEList)getDiagnostics()).basicRemove(otherEnd, msgs);
        case XSDPackage.XSD_NOTATION_DECLARATION__ANNOTATION:
          return basicSetAnnotation(null, msgs);
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
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case XSDPackage.XSD_NOTATION_DECLARATION__ELEMENT:
        return getElement();
      case XSDPackage.XSD_NOTATION_DECLARATION__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_NOTATION_DECLARATION__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_NOTATION_DECLARATION__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_NOTATION_DECLARATION__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_NOTATION_DECLARATION__NAME:
        return getName();
      case XSDPackage.XSD_NOTATION_DECLARATION__TARGET_NAMESPACE:
        return getTargetNamespace();
      case XSDPackage.XSD_NOTATION_DECLARATION__ALIAS_NAME:
        return getAliasName();
      case XSDPackage.XSD_NOTATION_DECLARATION__URI:
        return getURI();
      case XSDPackage.XSD_NOTATION_DECLARATION__ALIAS_URI:
        return getAliasURI();
      case XSDPackage.XSD_NOTATION_DECLARATION__QNAME:
        return getQName();
      case XSDPackage.XSD_NOTATION_DECLARATION__SYSTEM_IDENTIFIER:
        return getSystemIdentifier();
      case XSDPackage.XSD_NOTATION_DECLARATION__PUBLIC_IDENTIFIER:
        return getPublicIdentifier();
      case XSDPackage.XSD_NOTATION_DECLARATION__ANNOTATION:
        return getAnnotation();
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
      case XSDPackage.XSD_NOTATION_DECLARATION__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_NOTATION_DECLARATION__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_NOTATION_DECLARATION__NAME:
        setName((String)newValue);
        return;
      case XSDPackage.XSD_NOTATION_DECLARATION__TARGET_NAMESPACE:
        setTargetNamespace((String)newValue);
        return;
      case XSDPackage.XSD_NOTATION_DECLARATION__SYSTEM_IDENTIFIER:
        setSystemIdentifier((String)newValue);
        return;
      case XSDPackage.XSD_NOTATION_DECLARATION__PUBLIC_IDENTIFIER:
        setPublicIdentifier((String)newValue);
        return;
      case XSDPackage.XSD_NOTATION_DECLARATION__ANNOTATION:
        setAnnotation((XSDAnnotation)newValue);
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
      case XSDPackage.XSD_NOTATION_DECLARATION__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_NOTATION_DECLARATION__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_NOTATION_DECLARATION__NAME:
        setName(NAME_EDEFAULT);
        return;
      case XSDPackage.XSD_NOTATION_DECLARATION__TARGET_NAMESPACE:
        setTargetNamespace(TARGET_NAMESPACE_EDEFAULT);
        return;
      case XSDPackage.XSD_NOTATION_DECLARATION__SYSTEM_IDENTIFIER:
        setSystemIdentifier(SYSTEM_IDENTIFIER_EDEFAULT);
        return;
      case XSDPackage.XSD_NOTATION_DECLARATION__PUBLIC_IDENTIFIER:
        setPublicIdentifier(PUBLIC_IDENTIFIER_EDEFAULT);
        return;
      case XSDPackage.XSD_NOTATION_DECLARATION__ANNOTATION:
        setAnnotation((XSDAnnotation)null);
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
      case XSDPackage.XSD_NOTATION_DECLARATION__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_NOTATION_DECLARATION__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_NOTATION_DECLARATION__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_NOTATION_DECLARATION__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_NOTATION_DECLARATION__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_NOTATION_DECLARATION__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case XSDPackage.XSD_NOTATION_DECLARATION__TARGET_NAMESPACE:
        return TARGET_NAMESPACE_EDEFAULT == null ? targetNamespace != null : !TARGET_NAMESPACE_EDEFAULT.equals(targetNamespace);
      case XSDPackage.XSD_NOTATION_DECLARATION__ALIAS_NAME:
        return ALIAS_NAME_EDEFAULT == null ? getAliasName() != null : !ALIAS_NAME_EDEFAULT.equals(getAliasName());
      case XSDPackage.XSD_NOTATION_DECLARATION__URI:
        return URI_EDEFAULT == null ? getURI() != null : !URI_EDEFAULT.equals(getURI());
      case XSDPackage.XSD_NOTATION_DECLARATION__ALIAS_URI:
        return ALIAS_URI_EDEFAULT == null ? getAliasURI() != null : !ALIAS_URI_EDEFAULT.equals(getAliasURI());
      case XSDPackage.XSD_NOTATION_DECLARATION__QNAME:
        return QNAME_EDEFAULT == null ? getQName() != null : !QNAME_EDEFAULT.equals(getQName());
      case XSDPackage.XSD_NOTATION_DECLARATION__SYSTEM_IDENTIFIER:
        return SYSTEM_IDENTIFIER_EDEFAULT == null ? systemIdentifier != null : !SYSTEM_IDENTIFIER_EDEFAULT.equals(systemIdentifier);
      case XSDPackage.XSD_NOTATION_DECLARATION__PUBLIC_IDENTIFIER:
        return PUBLIC_IDENTIFIER_EDEFAULT == null ? publicIdentifier != null : !PUBLIC_IDENTIFIER_EDEFAULT.equals(publicIdentifier);
      case XSDPackage.XSD_NOTATION_DECLARATION__ANNOTATION:
        return annotation != null;
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
    result.append(" (systemIdentifier: ");
    result.append(systemIdentifier);
    result.append(", publicIdentifier: ");
    result.append(publicIdentifier);
    result.append(')');
    return result.toString();
  }

  public Element createElement()
  {
    Element newElement = createElement(XSDConstants.NOTATION_ELEMENT);
    setElement(newElement);
    return newElement;
  }

  public void validate()
  {
    super.validate();

    Element theElement = getElement();
    if (theElement != null)
    {
      checkAttributes
        (XSDConstants.PART1,
         "element-notation",
         theElement,
         new String []
         {
           XSDConstants.ID_ATTRIBUTE,
           XSDConstants.NAME_ATTRIBUTE,
           XSDConstants.PUBLIC_ATTRIBUTE,
           XSDConstants.SYSTEM_ATTRIBUTE
         });

      checkComplexContent("annotated", XSDConstants.PART1, "element-notation", theElement);

      checkBuiltInTypeConstraint
        ("ID",
         null,
         XSDConstants.PART1,
         "element-notation",
         theElement,
         XSDConstants.ID_ATTRIBUTE,
         false);
    }

    checkBuiltInTypeConstraint
      ("NCName",
       getName(),
       XSDConstants.PART1,
       "element-notation",
       theElement,
       XSDConstants.NAME_ATTRIBUTE,
       true);

    checkBuiltInTypeConstraint
      ("anyURI",
       getPublicIdentifier(),
       XSDConstants.PART1,
       "element-notation",
       theElement,
       XSDConstants.PUBLIC_ATTRIBUTE,
       true);

    checkBuiltInTypeConstraint
      ("anyURI",
       getSystemIdentifier(),
       XSDConstants.PART1,
       "element-notation",
       theElement,
       XSDConstants.SYSTEM_ATTRIBUTE,
       false);
  }

  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    if (changedElement.hasAttributeNS(null, XSDConstants.PUBLIC_ATTRIBUTE))
    {
      String newPublicIdentifier = changedElement.getAttributeNS(null, XSDConstants.PUBLIC_ATTRIBUTE);

      if (newPublicIdentifier == null || !newPublicIdentifier.equals(getPublicIdentifier()))
      {
        setPublicIdentifier(newPublicIdentifier);
      }
    }
    else if (getPublicIdentifier() != null)
    {
      setPublicIdentifier(null);
    }

    if (changedElement.hasAttributeNS(null, XSDConstants.SYSTEM_ATTRIBUTE))
    {
      String newSystemIdentifier = changedElement.getAttributeNS(null, XSDConstants.SYSTEM_ATTRIBUTE);

      if (newSystemIdentifier == null || !newSystemIdentifier.equals(getSystemIdentifier()))
      {
        setSystemIdentifier(newSystemIdentifier);
      }
    }
    else if (getSystemIdentifier() != null)
    {
      setSystemIdentifier(null);
    }
  }

  protected void changeAttribute(EAttribute eAttribute)
  {
    if (isReconciling)
    {
      return;
    }

    super.changeAttribute(eAttribute);
    if (eAttribute == null || eAttribute == XSDPackage.eINSTANCE.getXSDNotationDeclaration_PublicIdentifier())
    {
      Element theElement = getElement();
      if (theElement != null)
      {
        niceSetAttribute(theElement, XSDConstants.PUBLIC_ATTRIBUTE, getPublicIdentifier());
      }
    }
    if (eAttribute == null || eAttribute == XSDPackage.eINSTANCE.getXSDNotationDeclaration_SystemIdentifier())
    {
      Element theElement = getElement();
      if (theElement != null)
      {
        niceSetAttribute(theElement, XSDConstants.SYSTEM_ATTRIBUTE, getSystemIdentifier());
      }
    }
  }

  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDNotationDeclarationImpl clonedNotationDeclaration =
      (XSDNotationDeclarationImpl)getXSDFactory().createXSDNotationDeclaration();
    clonedNotationDeclaration.isReconciling = true;

    if (getName() != null)
    {
      clonedNotationDeclaration.setName(getName());
    }
    clonedNotationDeclaration.setPublicIdentifier(getPublicIdentifier());
    clonedNotationDeclaration.setSystemIdentifier(getSystemIdentifier());

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedNotationDeclaration.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedNotationDeclaration.setElement(getElement());
    }

    clonedNotationDeclaration.isReconciling = shareDOM;
    return clonedNotationDeclaration;
  }
} 
