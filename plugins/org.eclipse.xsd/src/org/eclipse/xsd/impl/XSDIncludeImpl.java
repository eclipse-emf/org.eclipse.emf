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
 * $Id: XSDIncludeImpl.java,v 1.2 2004/03/17 13:05:18 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDInclude;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Include</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDIncludeImpl#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDIncludeImpl 
  extends XSDSchemaCompositorImpl 
  implements XSDInclude
{
  /**
   * The cached value of the '{@link #getAnnotation() <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotation()
   * @generated
   * @ordered
   */
  protected XSDAnnotation annotation = null;

  public static XSDInclude createInclude(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.INCLUDE_ELEMENT)
    {
      XSDInclude xsdInclude = XSDFactory.eINSTANCE.createXSDInclude();
      xsdInclude.setElement((Element)node);
      return xsdInclude;
    }

    return null;
  }

  // protected XSDSchema includedSchema;

  protected XSDIncludeImpl() 
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
    return XSDPackage.eINSTANCE.getXSDInclude();
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
        msgs = ((InternalEObject)annotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_INCLUDE__ANNOTATION, null, msgs);
      if (newAnnotation != null)
        msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_INCLUDE__ANNOTATION, null, msgs);
      msgs = basicSetAnnotation(newAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_INCLUDE__ANNOTATION, newAnnotation, newAnnotation));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_INCLUDE__ANNOTATION, oldAnnotation, newAnnotation);
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
        case XSDPackage.XSD_INCLUDE__DIAGNOSTICS:
          return ((InternalEList)getDiagnostics()).basicRemove(otherEnd, msgs);
        case XSDPackage.XSD_INCLUDE__ANNOTATION:
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
      case XSDPackage.XSD_INCLUDE__ELEMENT:
        return getElement();
      case XSDPackage.XSD_INCLUDE__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_INCLUDE__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_INCLUDE__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_INCLUDE__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_INCLUDE__SCHEMA_LOCATION:
        return getSchemaLocation();
      case XSDPackage.XSD_INCLUDE__RESOLVED_SCHEMA:
        return getResolvedSchema();
      case XSDPackage.XSD_INCLUDE__INCORPORATED_SCHEMA:
        return getIncorporatedSchema();
      case XSDPackage.XSD_INCLUDE__ANNOTATION:
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
      case XSDPackage.XSD_INCLUDE__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_INCLUDE__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_INCLUDE__SCHEMA_LOCATION:
        setSchemaLocation((String)newValue);
        return;
      case XSDPackage.XSD_INCLUDE__RESOLVED_SCHEMA:
        setResolvedSchema((XSDSchema)newValue);
        return;
      case XSDPackage.XSD_INCLUDE__INCORPORATED_SCHEMA:
        setIncorporatedSchema((XSDSchema)newValue);
        return;
      case XSDPackage.XSD_INCLUDE__ANNOTATION:
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
      case XSDPackage.XSD_INCLUDE__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_INCLUDE__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_INCLUDE__SCHEMA_LOCATION:
        setSchemaLocation(SCHEMA_LOCATION_EDEFAULT);
        return;
      case XSDPackage.XSD_INCLUDE__RESOLVED_SCHEMA:
        setResolvedSchema((XSDSchema)null);
        return;
      case XSDPackage.XSD_INCLUDE__INCORPORATED_SCHEMA:
        setIncorporatedSchema((XSDSchema)null);
        return;
      case XSDPackage.XSD_INCLUDE__ANNOTATION:
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
      case XSDPackage.XSD_INCLUDE__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_INCLUDE__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_INCLUDE__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_INCLUDE__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_INCLUDE__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_INCLUDE__SCHEMA_LOCATION:
        return SCHEMA_LOCATION_EDEFAULT == null ? schemaLocation != null : !SCHEMA_LOCATION_EDEFAULT.equals(schemaLocation);
      case XSDPackage.XSD_INCLUDE__RESOLVED_SCHEMA:
        return resolvedSchema != null;
      case XSDPackage.XSD_INCLUDE__INCORPORATED_SCHEMA:
        return incorporatedSchema != null;
      case XSDPackage.XSD_INCLUDE__ANNOTATION:
        return annotation != null;
    }
    return eDynamicIsSet(eFeature);
  }

  public Element createElement()
  {
    Element newElement = createElement(XSDConstants.INCLUDE_ELEMENT);
    setElement(newElement);
    return newElement;
  }

  protected void patch()
  {
    if (getSchemaLocation() != null && getSchemaLocation().length() > 0)
    {
      resolve("", getSchemaLocation());
    }
    super.patch();
  }

  protected void handleResolvedSchema(XSDSchema xsdSchema)
  {
    if (xsdSchema == null)
    {
      setResolvedSchema(null);
      setIncorporatedSchema(null);
    }
    else
    {
      ((XSDSchemaImpl)xsdSchema).included(this);
    }
  }

  public void validate()
  {
    super.validate();

    Element theElement = getElement();
    if (theElement != null)
    {
      checkAttributes
        (XSDConstants.PART1,
         "element-include",
         theElement,
         new String []
         {
           XSDConstants.ID_ATTRIBUTE,
           XSDConstants.SCHEMALOCATION_ATTRIBUTE,
         });

      checkComplexContent("annotated", XSDConstants.PART1, "element-include", theElement);

      checkBuiltInTypeConstraint
        ("ID",
         null,
         XSDConstants.PART1,
         "element-include",
         theElement,
         XSDConstants.ID_ATTRIBUTE,
         false);
    }

    checkBuiltInTypeConstraint
      ("anyURI",
       getSchemaLocation(),
       XSDConstants.PART1,
       "element-include",
       theElement,
       XSDConstants.SCHEMALOCATION_ATTRIBUTE,
       true);

    XSDSchema theResolvedSchema = getResolvedSchema();
    if (theResolvedSchema == null)
    {
      createDiagnostic
        (XSDDiagnosticSeverity.WARNING_LITERAL,
         "src-include.0",
         getSchemaLocation() == null ? "" : getSchemaLocation());
    }
    else
    {
      if (theResolvedSchema.eResource() == null || 
            theResolvedSchema.getElement() == null ||
            XSDConstants.nodeType(theResolvedSchema.getElement()) != XSDConstants.SCHEMA_ELEMENT)
      {
        createDiagnostic
          (XSDDiagnosticSeverity.ERROR_LITERAL,
           "src-include.1",
           getSchemaLocation() == null ? "" : getSchemaLocation());
      }
      else
      {
        XSDSchema theSchema = getSchema();
        if (theSchema.getTargetNamespace() == null ? 
              theResolvedSchema.getTargetNamespace() != null :
               !theSchema.getTargetNamespace().equals(theResolvedSchema.getTargetNamespace()) && 
                 theResolvedSchema.getTargetNamespace() != null)
        {
          createDiagnostic
            (XSDDiagnosticSeverity.ERROR_LITERAL, 
             "src-include.2", 
             theResolvedSchema.getTargetNamespace() == null ? "" : theResolvedSchema.getTargetNamespace(),
             theSchema.getTargetNamespace() == null ? "" : theSchema.getTargetNamespace());
        }
      }
    }
  }

  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDIncludeImpl clonedInclude =
      (XSDIncludeImpl)getXSDFactory().createXSDInclude();
    clonedInclude.isReconciling = true;

    clonedInclude.setSchemaLocation(getSchemaLocation());

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedInclude.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedInclude.setElement(getElement());
    }

    clonedInclude.isReconciling = shareDOM;
    return clonedInclude;
  }
}
