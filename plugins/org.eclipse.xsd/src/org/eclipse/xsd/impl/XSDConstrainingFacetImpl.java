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
 * $Id: XSDConstrainingFacetImpl.java,v 1.1 2004/03/06 18:00:10 marcelop Exp $
 */
package org.eclipse.xsd.impl;


import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDConstrainingFacet;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constraining Facet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public abstract class XSDConstrainingFacetImpl 
  extends XSDFacetImpl 
  implements XSDConstrainingFacet
{
  public static XSDConstrainingFacet createConstrainingFacet(Node node)
  {
    XSDConstrainingFacet result = XSDFixedFacetImpl.createFixedFacet(node);
    if (result == null)
    {
      result = XSDRepeatableFacetImpl.createRepeatableFacet(node);
    }
    return result;
  }

  protected XSDConstrainingFacetImpl() 
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
    return XSDPackage.eINSTANCE.getXSDConstrainingFacet();
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
        case XSDPackage.XSD_CONSTRAINING_FACET__DIAGNOSTICS:
          return ((InternalEList)getDiagnostics()).basicRemove(otherEnd, msgs);
        case XSDPackage.XSD_CONSTRAINING_FACET__ANNOTATION:
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
      case XSDPackage.XSD_CONSTRAINING_FACET__ELEMENT:
        return getElement();
      case XSDPackage.XSD_CONSTRAINING_FACET__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_CONSTRAINING_FACET__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_CONSTRAINING_FACET__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_CONSTRAINING_FACET__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_CONSTRAINING_FACET__LEXICAL_VALUE:
        return getLexicalValue();
      case XSDPackage.XSD_CONSTRAINING_FACET__FACET_NAME:
        return getFacetName();
      case XSDPackage.XSD_CONSTRAINING_FACET__EFFECTIVE_VALUE:
        return getEffectiveValue();
      case XSDPackage.XSD_CONSTRAINING_FACET__ANNOTATION:
        return getAnnotation();
      case XSDPackage.XSD_CONSTRAINING_FACET__SIMPLE_TYPE_DEFINITION:
        return getSimpleTypeDefinition();
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
      case XSDPackage.XSD_CONSTRAINING_FACET__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_CONSTRAINING_FACET__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_CONSTRAINING_FACET__LEXICAL_VALUE:
        setLexicalValue((String)newValue);
        return;
      case XSDPackage.XSD_CONSTRAINING_FACET__ANNOTATION:
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
      case XSDPackage.XSD_CONSTRAINING_FACET__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_CONSTRAINING_FACET__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_CONSTRAINING_FACET__LEXICAL_VALUE:
        setLexicalValue(LEXICAL_VALUE_EDEFAULT);
        return;
      case XSDPackage.XSD_CONSTRAINING_FACET__ANNOTATION:
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
      case XSDPackage.XSD_CONSTRAINING_FACET__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_CONSTRAINING_FACET__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_CONSTRAINING_FACET__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_CONSTRAINING_FACET__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_CONSTRAINING_FACET__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_CONSTRAINING_FACET__LEXICAL_VALUE:
        return LEXICAL_VALUE_EDEFAULT == null ? lexicalValue != null : !LEXICAL_VALUE_EDEFAULT.equals(lexicalValue);
      case XSDPackage.XSD_CONSTRAINING_FACET__FACET_NAME:
        return getFacetName() != null;
      case XSDPackage.XSD_CONSTRAINING_FACET__EFFECTIVE_VALUE:
        return getEffectiveValue() != null;
      case XSDPackage.XSD_CONSTRAINING_FACET__ANNOTATION:
        return annotation != null;
      case XSDPackage.XSD_CONSTRAINING_FACET__SIMPLE_TYPE_DEFINITION:
        return getSimpleTypeDefinition() != null;
    }
    return eDynamicIsSet(eFeature);
  }

  public void validate()
  {
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)getContainer();
    if (!xsdSimpleTypeDefinition.getValidFacets().contains(getFacetName()) && !xsdSimpleTypeDefinition.isCircular())
    {
      XSDSimpleTypeDefinition rootTypeDefinition = xsdSimpleTypeDefinition.getRootTypeDefinition();
      reportConstraintViolation
        (XSDConstants.PART2,
         "cos-applicable-facets",
         getElement(),
         null,
         new Object [] { getFacetName(), rootTypeDefinition.getURI() });
    }
    else
    {
      super.validate();
    }
  }

  public boolean isConstraintSatisfied(Object value)
  {
    return false;
  }
}
