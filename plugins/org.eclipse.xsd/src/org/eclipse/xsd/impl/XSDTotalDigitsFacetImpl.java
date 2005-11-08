/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: XSDTotalDigitsFacetImpl.java,v 1.5 2005/11/08 14:05:36 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.math.BigDecimal;
import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDFixedFacet;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDTotalDigitsFacet;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Total Digits Facet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDTotalDigitsFacetImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDTotalDigitsFacetImpl 
  extends XSDFixedFacetImpl 
  implements XSDTotalDigitsFacet
{
  /**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final int VALUE_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected int value = VALUE_EDEFAULT;

  public static XSDTotalDigitsFacet createTotalDigitsFacet(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.TOTALDIGITS_ELEMENT)
    {
      XSDTotalDigitsFacet xsdTotalDigitsFacet = XSDFactory.eINSTANCE.createXSDTotalDigitsFacet();
      xsdTotalDigitsFacet.setElement((Element)node);
      return xsdTotalDigitsFacet;
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDTotalDigitsFacetImpl()
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
    return XSDPackage.eINSTANCE.getXSDTotalDigitsFacet();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(int newValue)
  {
    int oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_TOTAL_DIGITS_FACET__VALUE, oldValue, value));
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
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__ELEMENT:
        return getElement();
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__LEXICAL_VALUE:
        return getLexicalValue();
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__FACET_NAME:
        return getFacetName();
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__EFFECTIVE_VALUE:
        return getEffectiveValue();
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__ANNOTATION:
        return getAnnotation();
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__SIMPLE_TYPE_DEFINITION:
        return getSimpleTypeDefinition();
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__FIXED:
        return isFixed() ? Boolean.TRUE : Boolean.FALSE;
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__VALUE:
        return new Integer(getValue());
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
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__LEXICAL_VALUE:
        setLexicalValue((String)newValue);
        return;
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__ANNOTATION:
        setAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__FIXED:
        setFixed(((Boolean)newValue).booleanValue());
        return;
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__VALUE:
        setValue(((Integer)newValue).intValue());
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
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__LEXICAL_VALUE:
        setLexicalValue(LEXICAL_VALUE_EDEFAULT);
        return;
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__ANNOTATION:
        setAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__FIXED:
        unsetFixed();
        return;
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__VALUE:
        setValue(VALUE_EDEFAULT);
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
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__LEXICAL_VALUE:
        return LEXICAL_VALUE_EDEFAULT == null ? lexicalValue != null : !LEXICAL_VALUE_EDEFAULT.equals(lexicalValue);
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__FACET_NAME:
        return FACET_NAME_EDEFAULT == null ? getFacetName() != null : !FACET_NAME_EDEFAULT.equals(getFacetName());
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__EFFECTIVE_VALUE:
        return EFFECTIVE_VALUE_EDEFAULT == null ? getEffectiveValue() != null : !EFFECTIVE_VALUE_EDEFAULT.equals(getEffectiveValue());
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__ANNOTATION:
        return annotation != null;
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__SIMPLE_TYPE_DEFINITION:
        return getSimpleTypeDefinition() != null;
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__FIXED:
        return isSetFixed();
      case XSDPackage.XSD_TOTAL_DIGITS_FACET__VALUE:
        return value != VALUE_EDEFAULT;
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
    Element newElement = createElement(XSDConstants.TOTALDIGITS_ELEMENT);
    setElement(newElement);
    return newElement;
  }

  protected void validateValue()
  {
    checkBuiltInTypeConstraint
      ("positiveInteger",
       getLexicalValue(),
       XSDConstants.PART2,
       "element-totalDigits",
       getElement(),
       XSDConstants.VALUE_ATTRIBUTE,
       true);
  }

  protected void validateRestriction(XSDFixedFacet xsdFixedFacet)
  {
    if (getValue() > ((XSDTotalDigitsFacet)xsdFixedFacet).getValue())
    {
      XSDDiagnostic xsdDiagnostic =
        reportConstraintViolation
          (XSDConstants.PART2,
           "totalDigits-valid-restriction",
           getElement(),
           XSDConstants.VALUE_ATTRIBUTE,
           new Object [] { new Integer(getValue()), xsdFixedFacet.getEffectiveValue(), xsdFixedFacet.getSimpleTypeDefinition().getURI() });
      xsdDiagnostic.getComponents().add(xsdFixedFacet);
    }
  }

  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    if (eAttribute == XSDPackage.eINSTANCE.getXSDFacet_LexicalValue())
    {
      if (getLexicalValue() == null)
      {
        setValue(0);
      }
      else
      {
        try
        {
          int newValue = Integer.parseInt(getLexicalValue());
          if (newValue != getValue())
          {
            setValue(newValue);
          }
        }
        catch (NumberFormatException exception)
        {
          setValue(0);
        }
      }
      traverseToRootForAnalysis();
    }
  }

  public boolean isConstraintSatisfied(Object value)
  {
    return value instanceof BigDecimal && ((BigDecimal)value).unscaledValue().abs().toString().length() <= getValue();
  }

  public Object getEffectiveValue()
  {
    return new Integer(getValue());
  }

  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDTotalDigitsFacetImpl clonedTotalDigitsFacet =
      (XSDTotalDigitsFacetImpl)getXSDFactory().createXSDTotalDigitsFacet();
    clonedTotalDigitsFacet.isReconciling = true;

    if (getLexicalValue() != null)
    {
      clonedTotalDigitsFacet.setLexicalValue(getLexicalValue());
    }
    if (isSetFixed())
    {
      clonedTotalDigitsFacet.setFixed(isFixed());
    }

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedTotalDigitsFacet.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedTotalDigitsFacet.setElement(getElement());
    }

    clonedTotalDigitsFacet.isReconciling = shareDOM;
    return clonedTotalDigitsFacet;
  }
} 
