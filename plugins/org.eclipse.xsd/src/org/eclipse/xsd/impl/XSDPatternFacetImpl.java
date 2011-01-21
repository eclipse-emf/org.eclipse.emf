/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: XSDPatternFacetImpl.java,v 1.18 2011/01/21 01:33:20 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
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
  protected EList<String> value;

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

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDPatternFacetImpl()
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
    return XSDPackage.Literals.XSD_PATTERN_FACET;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getValue()
  {
    if (value == null)
    {
      value = new EDataTypeUniqueEList<String>(String.class, this, XSDPackage.XSD_PATTERN_FACET__VALUE);
    }
    return value;
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
      case XSDPackage.XSD_PATTERN_FACET__VALUE:
        return getValue();
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
      case XSDPackage.XSD_PATTERN_FACET__VALUE:
        getValue().clear();
        getValue().addAll((Collection<? extends String>)newValue);
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
      case XSDPackage.XSD_PATTERN_FACET__VALUE:
        getValue().clear();
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
      case XSDPackage.XSD_PATTERN_FACET__VALUE:
        return value != null && !value.isEmpty();
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
    result.append(" (value: ");
    result.append(value);
    result.append(')');
    return result.toString();
  }

  @Override
  public Element createElement()
  {
    Element newElement = createElement(XSDConstants.PATTERN_ELEMENT);
    setElement(newElement);
    return newElement;
  }

  protected Collection<RegularExpression> patterns;
  public Collection<RegularExpression> getPatterns(boolean withDiagnostics)
  {
    if (patterns == null || withDiagnostics)
    {
      ArrayList<RegularExpression> result = new ArrayList<RegularExpression>();
      Collection<String> theValues = getValue();
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
        for (String value : theValues)
        {
          try
          {
            result.add(new RegularExpression(value, "X"));
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
      patterns = result;
    }
    return patterns;
  }

  @Override
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
            populateDiagnostic(result, "dt-regex", new Object [] { getLexicalValue(), location, parseError })
          }));
    result.setAnnotationURI(XSDConstants.PART1 + "#dt-regex");
    result.setPrimaryComponent(this);
    result.setNode(getElement());
    getDiagnostics().add(result);
    return result;
  }

  @Override
  protected boolean analyze()
  {
    super.analyze();
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)getContainer();
    if (xsdSimpleTypeDefinition != null && !xsdSimpleTypeDefinition.getSyntheticFacets().contains(this))
    {
      String newValue = getLexicalValue();
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

  @Override
  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    if (eAttribute == XSDPackage.Literals.XSD_FACET__LEXICAL_VALUE)
    {
      traverseToRootForAnalysis();
      patterns = null;
    }
  }
  @Override
  public boolean isConstraintSatisfied(Object value)
  {
    if (value == null)
    {
      return false;
    }
    else
    {
      for (RegularExpression pattern : getPatterns(false))
      {
        if (!pattern.matches((String)value))
        {
          return false;
        }
      }
      return true;
    }
  }

  @Override
  public Object getEffectiveValue()
  {
    return getValue();
  }

  @Override
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
