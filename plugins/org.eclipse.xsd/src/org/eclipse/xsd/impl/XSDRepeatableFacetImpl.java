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
 * $Id: XSDRepeatableFacetImpl.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 */
package org.eclipse.xsd.impl;


import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDRepeatableFacet;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Repeatable Facet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDRepeatableFacetImpl#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class XSDRepeatableFacetImpl 
  extends XSDConstrainingFacetImpl 
  implements XSDRepeatableFacet
{
  /**
   * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotations()
   * @generated
   * @ordered
   */
  protected EList annotations = null;

  public static XSDRepeatableFacet createRepeatableFacet(Node node)
  {
    switch (XSDConstants.nodeType(node))
    {
      case XSDConstants.PATTERN_ELEMENT:
      {
        return XSDPatternFacetImpl.createPatternFacet(node);
      }
      case XSDConstants.ENUMERATION_ELEMENT:
      {
        return XSDEnumerationFacetImpl.createEnumerationFacet(node);
      }
    }

    return null;
  }

  protected XSDRepeatableFacetImpl() 
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
    return XSDPackage.eINSTANCE.getXSDRepeatableFacet();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getAnnotations()
  {
    if (annotations == null)
    {
      annotations = new EObjectEList(XSDAnnotation.class, this, XSDPackage.XSD_REPEATABLE_FACET__ANNOTATIONS);
    }
    return annotations;
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
        case XSDPackage.XSD_REPEATABLE_FACET__DIAGNOSTICS:
          return ((InternalEList)getDiagnostics()).basicRemove(otherEnd, msgs);
        case XSDPackage.XSD_REPEATABLE_FACET__ANNOTATION:
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
      case XSDPackage.XSD_REPEATABLE_FACET__ELEMENT:
        return getElement();
      case XSDPackage.XSD_REPEATABLE_FACET__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_REPEATABLE_FACET__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_REPEATABLE_FACET__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_REPEATABLE_FACET__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_REPEATABLE_FACET__LEXICAL_VALUE:
        return getLexicalValue();
      case XSDPackage.XSD_REPEATABLE_FACET__FACET_NAME:
        return getFacetName();
      case XSDPackage.XSD_REPEATABLE_FACET__EFFECTIVE_VALUE:
        return getEffectiveValue();
      case XSDPackage.XSD_REPEATABLE_FACET__ANNOTATION:
        return getAnnotation();
      case XSDPackage.XSD_REPEATABLE_FACET__SIMPLE_TYPE_DEFINITION:
        return getSimpleTypeDefinition();
      case XSDPackage.XSD_REPEATABLE_FACET__ANNOTATIONS:
        return getAnnotations();
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
      case XSDPackage.XSD_REPEATABLE_FACET__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_REPEATABLE_FACET__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_REPEATABLE_FACET__LEXICAL_VALUE:
        setLexicalValue((String)newValue);
        return;
      case XSDPackage.XSD_REPEATABLE_FACET__ANNOTATION:
        setAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_REPEATABLE_FACET__ANNOTATIONS:
        getAnnotations().clear();
        getAnnotations().addAll((Collection)newValue);
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
      case XSDPackage.XSD_REPEATABLE_FACET__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_REPEATABLE_FACET__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_REPEATABLE_FACET__LEXICAL_VALUE:
        setLexicalValue(LEXICAL_VALUE_EDEFAULT);
        return;
      case XSDPackage.XSD_REPEATABLE_FACET__ANNOTATION:
        setAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_REPEATABLE_FACET__ANNOTATIONS:
        getAnnotations().clear();
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
      case XSDPackage.XSD_REPEATABLE_FACET__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_REPEATABLE_FACET__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_REPEATABLE_FACET__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_REPEATABLE_FACET__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_REPEATABLE_FACET__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_REPEATABLE_FACET__LEXICAL_VALUE:
        return LEXICAL_VALUE_EDEFAULT == null ? lexicalValue != null : !LEXICAL_VALUE_EDEFAULT.equals(lexicalValue);
      case XSDPackage.XSD_REPEATABLE_FACET__FACET_NAME:
        return getFacetName() != null;
      case XSDPackage.XSD_REPEATABLE_FACET__EFFECTIVE_VALUE:
        return getEffectiveValue() != null;
      case XSDPackage.XSD_REPEATABLE_FACET__ANNOTATION:
        return annotation != null;
      case XSDPackage.XSD_REPEATABLE_FACET__SIMPLE_TYPE_DEFINITION:
        return getSimpleTypeDefinition() != null;
      case XSDPackage.XSD_REPEATABLE_FACET__ANNOTATIONS:
        return annotations != null && !annotations.isEmpty();
    }
    return eDynamicIsSet(eFeature);
  }

  public void validate()
  {
    super.validate();

    Element theElement = getElement();

    checkAttributes
      (XSDConstants.PART2,
       "element-length",
       theElement,
       new String []
       {
         XSDConstants.VALUE_ATTRIBUTE,
         XSDConstants.ID_ATTRIBUTE
       });
  }

  protected void validateValue()
  {
    checkBuiltInTypeConstraint
      ("nonNegativeInteger",
       getLexicalValue(),
       XSDConstants.PART2,
       "element-" + getFacetName(),
       getElement(),
       XSDConstants.VALUE_ATTRIBUTE,
       true);
  }
} 
