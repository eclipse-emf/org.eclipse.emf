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
 * $Id: XSDPatternFacetImpl.java,v 1.2 2004/05/22 19:07:19 marcelop Exp $
 */
package org.eclipse.xsd.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xml.type.internal.RegEx.ParseException;
import org.eclipse.emf.ecore.xml.type.internal.RegEx.RegularExpression;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDPatternFacet;
import org.eclipse.xsd.XSDPlugin;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pattern Facet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDPatternFacetImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDPatternFacetImpl 
  extends XSDRepeatableFacetImpl 
  implements XSDPatternFacet
{
  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected EList value = null;

  public static XSDPatternFacet createPatternFacet(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.PATTERN_ELEMENT)
    {
      XSDPatternFacet xsdPatternFacet = XSDFactory.eINSTANCE.createXSDPatternFacet();
      xsdPatternFacet.setElement((Element)node);
      return xsdPatternFacet;
    }

    return null;
  }


  protected XSDPatternFacetImpl() 
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
    return XSDPackage.eINSTANCE.getXSDPatternFacet();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getValue()
  {
    if (value == null)
    {
      value = new EDataTypeUniqueEList(String.class, this, XSDPackage.XSD_PATTERN_FACET__VALUE);
    }
    return value;
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
        case XSDPackage.XSD_PATTERN_FACET__DIAGNOSTICS:
          return ((InternalEList)getDiagnostics()).basicRemove(otherEnd, msgs);
        case XSDPackage.XSD_PATTERN_FACET__ANNOTATION:
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
      case XSDPackage.XSD_PATTERN_FACET__ELEMENT:
        return getElement();
      case XSDPackage.XSD_PATTERN_FACET__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_PATTERN_FACET__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_PATTERN_FACET__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_PATTERN_FACET__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_PATTERN_FACET__LEXICAL_VALUE:
        return getLexicalValue();
      case XSDPackage.XSD_PATTERN_FACET__FACET_NAME:
        return getFacetName();
      case XSDPackage.XSD_PATTERN_FACET__EFFECTIVE_VALUE:
        return getEffectiveValue();
      case XSDPackage.XSD_PATTERN_FACET__ANNOTATION:
        return getAnnotation();
      case XSDPackage.XSD_PATTERN_FACET__SIMPLE_TYPE_DEFINITION:
        return getSimpleTypeDefinition();
      case XSDPackage.XSD_PATTERN_FACET__ANNOTATIONS:
        return getAnnotations();
      case XSDPackage.XSD_PATTERN_FACET__VALUE:
        return getValue();
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
      case XSDPackage.XSD_PATTERN_FACET__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_PATTERN_FACET__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_PATTERN_FACET__LEXICAL_VALUE:
        setLexicalValue((String)newValue);
        return;
      case XSDPackage.XSD_PATTERN_FACET__ANNOTATION:
        setAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_PATTERN_FACET__ANNOTATIONS:
        getAnnotations().clear();
        getAnnotations().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_PATTERN_FACET__VALUE:
        getValue().clear();
        getValue().addAll((Collection)newValue);
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
      case XSDPackage.XSD_PATTERN_FACET__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_PATTERN_FACET__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_PATTERN_FACET__LEXICAL_VALUE:
        setLexicalValue(LEXICAL_VALUE_EDEFAULT);
        return;
      case XSDPackage.XSD_PATTERN_FACET__ANNOTATION:
        setAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_PATTERN_FACET__ANNOTATIONS:
        getAnnotations().clear();
        return;
      case XSDPackage.XSD_PATTERN_FACET__VALUE:
        getValue().clear();
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
      case XSDPackage.XSD_PATTERN_FACET__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_PATTERN_FACET__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_PATTERN_FACET__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_PATTERN_FACET__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_PATTERN_FACET__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_PATTERN_FACET__LEXICAL_VALUE:
        return LEXICAL_VALUE_EDEFAULT == null ? lexicalValue != null : !LEXICAL_VALUE_EDEFAULT.equals(lexicalValue);
      case XSDPackage.XSD_PATTERN_FACET__FACET_NAME:
        return getFacetName() != null;
      case XSDPackage.XSD_PATTERN_FACET__EFFECTIVE_VALUE:
        return getEffectiveValue() != null;
      case XSDPackage.XSD_PATTERN_FACET__ANNOTATION:
        return annotation != null;
      case XSDPackage.XSD_PATTERN_FACET__SIMPLE_TYPE_DEFINITION:
        return getSimpleTypeDefinition() != null;
      case XSDPackage.XSD_PATTERN_FACET__ANNOTATIONS:
        return annotations != null && !annotations.isEmpty();
      case XSDPackage.XSD_PATTERN_FACET__VALUE:
        return value != null && !value.isEmpty();
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
    result.append(" (value: ");
    result.append(value);
    result.append(')');
    return result.toString();
  }

  public Element createElement()
  {
    Element newElement = createElement(XSDConstants.PATTERN_ELEMENT);
    setElement(newElement);
    return newElement;
  }

  protected Collection patterns;
  public Collection getPatterns(boolean withDiagnostics)
  {
    if (patterns == null || withDiagnostics)
    {
      patterns = new ArrayList();
      Collection theValues = getValue();
      if (theValues.isEmpty())
      {
        XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)getContainer();
        if (xsdSimpleTypeDefinition != null && !xsdSimpleTypeDefinition.getSyntheticFacets().contains(this))
        {
          createRequiredAttributeDiagnostic(XSDConstants.PART1, "element-pattern", getElement(), XSDConstants.VALUE_ATTRIBUTE);
        }
      }
      else
      {
        for (Iterator values = theValues.iterator(); values.hasNext(); )
        {
          String value = (String)values.next();
          try
          {
            patterns.add(new RegularExpression(value, "X"));
          }
          catch (ParseException parseException)
          {
            if (withDiagnostics)
            {
              createPatternDiagnostic(parseException.getMessage(), parseException.getLocation());
            }
          }
        }
      }
    }
    return patterns;
  }

  public void validateValue()
  {
    getPatterns(true);
  }

  protected XSDDiagnostic createPatternDiagnostic(String parseError, int location)
  {
    XSDDiagnostic result = getXSDFactory().createXSDDiagnostic();
    result.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
    result.setMessage
      (XSDPlugin.INSTANCE.getString
         ("_UI_XSDError_message", 
          new Object [] 
          { 
            XSDPlugin.INSTANCE.getString("dt-regex", new Object [] { getLexicalValue(), new Integer(location), parseError })
          }));
    result.setAnnotationURI(XSDConstants.PART1 + "#dt-regex");
    result.setPrimaryComponent(this);
    result.setNode(getElement());
    getDiagnostics().add(result);
    return result;
  }

  protected boolean analyze()
  {
    super.analyze();
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)getContainer();
    if (xsdSimpleTypeDefinition != null && !xsdSimpleTypeDefinition.getSyntheticFacets().contains(this))
    {
      Object newValue = getLexicalValue();
      if (!getValue().contains(newValue))
      {
        getValue().clear();
        if (newValue != null)
        {
          getValue().add(newValue);
        }
        patterns = null;
      }
    }
    return true;
  }

  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    if (eAttribute == XSDPackage.eINSTANCE.getXSDFacet_LexicalValue())
    {
      traverseToRootForAnalysis();
      patterns = null;
    }
  }
  public boolean isConstraintSatisfied(Object value)
  {
    for (Iterator thePatterns = getPatterns(false).iterator(); thePatterns.hasNext(); )
    {
      RegularExpression pattern = (RegularExpression)thePatterns.next();
      if (!pattern.matches((String)value))
      {
        return false;
      }
    }
    return true;
  }

  public Object getEffectiveValue()
  {
    return getValue();
  }

  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDPatternFacetImpl clonedPatternFacet =
      (XSDPatternFacetImpl)getXSDFactory().createXSDPatternFacet();
    clonedPatternFacet.isReconciling = true;

    if (getLexicalValue() != null)
    {
      clonedPatternFacet.setLexicalValue(getLexicalValue());
    }

    if (shareDOM && getElement() != null)
    {
      clonedPatternFacet.setElement(getElement());
    }

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedPatternFacet.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
    }

    clonedPatternFacet.isReconciling = shareDOM;
    return clonedPatternFacet;
  }
}
