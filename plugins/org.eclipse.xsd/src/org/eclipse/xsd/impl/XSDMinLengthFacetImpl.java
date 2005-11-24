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
 * $Id: XSDMinLengthFacetImpl.java,v 1.9 2005/11/24 19:06:02 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.Collection;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDFixedFacet;
import org.eclipse.xsd.XSDMaxLengthFacet;
import org.eclipse.xsd.XSDMinLengthFacet;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;
import org.eclipse.xsd.util.XSDUtil;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Min Length Facet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDMinLengthFacetImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDMinLengthFacetImpl 
  extends XSDFixedFacetImpl 
  implements XSDMinLengthFacet
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

  public static XSDMinLengthFacet createMinLengthFacet(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.MINLENGTH_ELEMENT)
    {
      XSDMinLengthFacet xsdMinLengthFacet = XSDFactory.eINSTANCE.createXSDMinLengthFacet();
      xsdMinLengthFacet.setElement((Element)node);
      return xsdMinLengthFacet;
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDMinLengthFacetImpl()
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
    return XSDPackage.Literals.XSD_MIN_LENGTH_FACET;
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
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_MIN_LENGTH_FACET__VALUE, oldValue, value));
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
      case XSDPackage.XSD_MIN_LENGTH_FACET__ELEMENT:
        return getElement();
      case XSDPackage.XSD_MIN_LENGTH_FACET__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_MIN_LENGTH_FACET__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_MIN_LENGTH_FACET__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_MIN_LENGTH_FACET__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_MIN_LENGTH_FACET__LEXICAL_VALUE:
        return getLexicalValue();
      case XSDPackage.XSD_MIN_LENGTH_FACET__FACET_NAME:
        return getFacetName();
      case XSDPackage.XSD_MIN_LENGTH_FACET__EFFECTIVE_VALUE:
        return getEffectiveValue();
      case XSDPackage.XSD_MIN_LENGTH_FACET__ANNOTATION:
        return getAnnotation();
      case XSDPackage.XSD_MIN_LENGTH_FACET__SIMPLE_TYPE_DEFINITION:
        return getSimpleTypeDefinition();
      case XSDPackage.XSD_MIN_LENGTH_FACET__FIXED:
        return isFixed() ? Boolean.TRUE : Boolean.FALSE;
      case XSDPackage.XSD_MIN_LENGTH_FACET__VALUE:
        return new Integer(getValue());
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
      case XSDPackage.XSD_MIN_LENGTH_FACET__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_MIN_LENGTH_FACET__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_MIN_LENGTH_FACET__LEXICAL_VALUE:
        setLexicalValue((String)newValue);
        return;
      case XSDPackage.XSD_MIN_LENGTH_FACET__ANNOTATION:
        setAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_MIN_LENGTH_FACET__FIXED:
        setFixed(((Boolean)newValue).booleanValue());
        return;
      case XSDPackage.XSD_MIN_LENGTH_FACET__VALUE:
        setValue(((Integer)newValue).intValue());
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
      case XSDPackage.XSD_MIN_LENGTH_FACET__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_MIN_LENGTH_FACET__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_MIN_LENGTH_FACET__LEXICAL_VALUE:
        setLexicalValue(LEXICAL_VALUE_EDEFAULT);
        return;
      case XSDPackage.XSD_MIN_LENGTH_FACET__ANNOTATION:
        setAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_MIN_LENGTH_FACET__FIXED:
        unsetFixed();
        return;
      case XSDPackage.XSD_MIN_LENGTH_FACET__VALUE:
        setValue(VALUE_EDEFAULT);
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
      case XSDPackage.XSD_MIN_LENGTH_FACET__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_MIN_LENGTH_FACET__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_MIN_LENGTH_FACET__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_MIN_LENGTH_FACET__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_MIN_LENGTH_FACET__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_MIN_LENGTH_FACET__LEXICAL_VALUE:
        return LEXICAL_VALUE_EDEFAULT == null ? lexicalValue != null : !LEXICAL_VALUE_EDEFAULT.equals(lexicalValue);
      case XSDPackage.XSD_MIN_LENGTH_FACET__FACET_NAME:
        return FACET_NAME_EDEFAULT == null ? getFacetName() != null : !FACET_NAME_EDEFAULT.equals(getFacetName());
      case XSDPackage.XSD_MIN_LENGTH_FACET__EFFECTIVE_VALUE:
        return EFFECTIVE_VALUE_EDEFAULT == null ? getEffectiveValue() != null : !EFFECTIVE_VALUE_EDEFAULT.equals(getEffectiveValue());
      case XSDPackage.XSD_MIN_LENGTH_FACET__ANNOTATION:
        return annotation != null;
      case XSDPackage.XSD_MIN_LENGTH_FACET__SIMPLE_TYPE_DEFINITION:
        return getSimpleTypeDefinition() != null;
      case XSDPackage.XSD_MIN_LENGTH_FACET__FIXED:
        return isSetFixed();
      case XSDPackage.XSD_MIN_LENGTH_FACET__VALUE:
        return value != VALUE_EDEFAULT;
    }
    return eDynamicIsSet(featureID);
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
    Element newElement = createElement(XSDConstants.MINLENGTH_ELEMENT);
    setElement(newElement);
    return newElement;
  }

  public void validate()
  {
    super.validate();
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = getSimpleTypeDefinition();
    XSDMaxLengthFacet xsdMaxLengthFacet = xsdSimpleTypeDefinition.getEffectiveMaxLengthFacet();
    if (xsdMaxLengthFacet != null && getValue() > xsdMaxLengthFacet.getValue())
    {
      XSDDiagnostic xsdDiagnostic =
        reportConstraintViolation
          (XSDConstants.PART2,
           "minLength-less-than-equal-to-maxLength",
           getElement(),
           null,
           new Object [] { new Integer(getValue()), xsdMaxLengthFacet.getEffectiveValue()});
      xsdDiagnostic.getComponents().add(xsdMaxLengthFacet);
    }
  }

  protected void validateRestriction(XSDFixedFacet xsdFixedFacet)
  {
    if (getValue() < ((XSDMinLengthFacet)xsdFixedFacet).getValue())
    {
      XSDDiagnostic xsdDiagnostic =
        reportConstraintViolation
          (XSDConstants.PART2,
           "minLength-valid-restriction",
           getElement(),
           XSDConstants.VALUE_ATTRIBUTE,
           new Object [] { new Integer(getValue()), xsdFixedFacet.getEffectiveValue(), xsdFixedFacet.getSimpleTypeDefinition().getURI() });
      xsdDiagnostic.getComponents().add(xsdFixedFacet);
    }
  }

  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    if (eAttribute == XSDPackage.Literals.XSD_FACET__LEXICAL_VALUE)
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
    if (value instanceof List)
    {
      return ((List)value).size() >= getValue();
    }
    else if (value instanceof String)
    {
      return ((String)value).length() >= getValue();
    }
    else if (value instanceof XSDUtil.ByteSequence)
    {
      return ((XSDUtil.ByteSequence)value).getBytes().length >= getValue();
    }
    else
    {
      return false;
    }
  }

  public Object getEffectiveValue()
  {
    return new Integer(getValue());
  }

  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDMinLengthFacetImpl clonedMinLengthFacet =
      (XSDMinLengthFacetImpl)getXSDFactory().createXSDMinLengthFacet();
    clonedMinLengthFacet.isReconciling = true;

    if (getLexicalValue() != null)
    {
      clonedMinLengthFacet.setLexicalValue(getLexicalValue());
    }
    if (isSetFixed())
    {
      clonedMinLengthFacet.setFixed(isFixed());
    }

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedMinLengthFacet.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedMinLengthFacet.setElement(getElement());
    }

    clonedMinLengthFacet.isReconciling = shareDOM;
    return clonedMinLengthFacet;
  }
}
