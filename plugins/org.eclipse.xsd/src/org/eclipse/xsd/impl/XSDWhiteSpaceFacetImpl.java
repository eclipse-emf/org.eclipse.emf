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
 * $Id: XSDWhiteSpaceFacetImpl.java,v 1.4 2005/06/08 06:23:01 nickb Exp $
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
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDFixedFacet;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDWhiteSpace;
import org.eclipse.xsd.XSDWhiteSpaceFacet;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>White Space Facet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDWhiteSpaceFacetImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDWhiteSpaceFacetImpl 
  extends XSDFixedFacetImpl 
  implements XSDWhiteSpaceFacet
{
  /**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final XSDWhiteSpace VALUE_EDEFAULT = XSDWhiteSpace.PRESERVE_LITERAL;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected XSDWhiteSpace value = VALUE_EDEFAULT;

  public static XSDWhiteSpaceFacet createWhiteSpaceFacet(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.WHITESPACE_ELEMENT)
    {
      XSDWhiteSpaceFacet xsdWhiteSpaceFacet = XSDFactory.eINSTANCE.createXSDWhiteSpaceFacet();
      xsdWhiteSpaceFacet.setElement((Element)node);
      return xsdWhiteSpaceFacet;
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDWhiteSpaceFacetImpl()
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
    return XSDPackage.eINSTANCE.getXSDWhiteSpaceFacet();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDWhiteSpace getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(XSDWhiteSpace newValue)
  {
    XSDWhiteSpace oldValue = value;
    value = newValue == null ? VALUE_EDEFAULT : newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_WHITE_SPACE_FACET__VALUE, oldValue, value));
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
        case XSDPackage.XSD_WHITE_SPACE_FACET__DIAGNOSTICS:
          return ((InternalEList)getDiagnostics()).basicRemove(otherEnd, msgs);
        case XSDPackage.XSD_WHITE_SPACE_FACET__ANNOTATION:
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
      case XSDPackage.XSD_WHITE_SPACE_FACET__ELEMENT:
        return getElement();
      case XSDPackage.XSD_WHITE_SPACE_FACET__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_WHITE_SPACE_FACET__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_WHITE_SPACE_FACET__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_WHITE_SPACE_FACET__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_WHITE_SPACE_FACET__LEXICAL_VALUE:
        return getLexicalValue();
      case XSDPackage.XSD_WHITE_SPACE_FACET__FACET_NAME:
        return getFacetName();
      case XSDPackage.XSD_WHITE_SPACE_FACET__EFFECTIVE_VALUE:
        return getEffectiveValue();
      case XSDPackage.XSD_WHITE_SPACE_FACET__ANNOTATION:
        return getAnnotation();
      case XSDPackage.XSD_WHITE_SPACE_FACET__SIMPLE_TYPE_DEFINITION:
        return getSimpleTypeDefinition();
      case XSDPackage.XSD_WHITE_SPACE_FACET__FIXED:
        return isFixed() ? Boolean.TRUE : Boolean.FALSE;
      case XSDPackage.XSD_WHITE_SPACE_FACET__VALUE:
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
      case XSDPackage.XSD_WHITE_SPACE_FACET__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_WHITE_SPACE_FACET__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_WHITE_SPACE_FACET__LEXICAL_VALUE:
        setLexicalValue((String)newValue);
        return;
      case XSDPackage.XSD_WHITE_SPACE_FACET__ANNOTATION:
        setAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_WHITE_SPACE_FACET__FIXED:
        setFixed(((Boolean)newValue).booleanValue());
        return;
      case XSDPackage.XSD_WHITE_SPACE_FACET__VALUE:
        setValue((XSDWhiteSpace)newValue);
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
      case XSDPackage.XSD_WHITE_SPACE_FACET__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_WHITE_SPACE_FACET__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_WHITE_SPACE_FACET__LEXICAL_VALUE:
        setLexicalValue(LEXICAL_VALUE_EDEFAULT);
        return;
      case XSDPackage.XSD_WHITE_SPACE_FACET__ANNOTATION:
        setAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_WHITE_SPACE_FACET__FIXED:
        unsetFixed();
        return;
      case XSDPackage.XSD_WHITE_SPACE_FACET__VALUE:
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
      case XSDPackage.XSD_WHITE_SPACE_FACET__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_WHITE_SPACE_FACET__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_WHITE_SPACE_FACET__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_WHITE_SPACE_FACET__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_WHITE_SPACE_FACET__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_WHITE_SPACE_FACET__LEXICAL_VALUE:
        return LEXICAL_VALUE_EDEFAULT == null ? lexicalValue != null : !LEXICAL_VALUE_EDEFAULT.equals(lexicalValue);
      case XSDPackage.XSD_WHITE_SPACE_FACET__FACET_NAME:
        return FACET_NAME_EDEFAULT == null ? getFacetName() != null : !FACET_NAME_EDEFAULT.equals(getFacetName());
      case XSDPackage.XSD_WHITE_SPACE_FACET__EFFECTIVE_VALUE:
        return EFFECTIVE_VALUE_EDEFAULT == null ? getEffectiveValue() != null : !EFFECTIVE_VALUE_EDEFAULT.equals(getEffectiveValue());
      case XSDPackage.XSD_WHITE_SPACE_FACET__ANNOTATION:
        return annotation != null;
      case XSDPackage.XSD_WHITE_SPACE_FACET__SIMPLE_TYPE_DEFINITION:
        return getSimpleTypeDefinition() != null;
      case XSDPackage.XSD_WHITE_SPACE_FACET__FIXED:
        return isSetFixed();
      case XSDPackage.XSD_WHITE_SPACE_FACET__VALUE:
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
    Element newElement = createElement(XSDConstants.WHITESPACE_ELEMENT);
    setElement(newElement);
    return newElement;
  }

  protected void validateRestriction(XSDFixedFacet xsdFixedFacet)
  {
    if (getValue().getValue() < ((XSDWhiteSpaceFacet)xsdFixedFacet).getValue().getValue())
    {
      XSDDiagnostic xsdDiagnostic =
        reportConstraintViolation
          (XSDConstants.PART2,
           "whiteSpace-valid-restriction",
           getElement(),
           XSDConstants.VALUE_ATTRIBUTE,
           new Object [] 
           { 
             getLexicalValue(), 
             xsdFixedFacet.getLexicalValue(), 
             xsdFixedFacet.getSimpleTypeDefinition().getURI() 
           });
      xsdDiagnostic.getComponents().add(xsdFixedFacet);
    }
  }

  protected void validateValue()
  {
    XSDSimpleTypeDefinition whiteSpaceEnumeration = 
      ((XSDAttributeUse)
        ((XSDComplexTypeDefinition)
            getSimpleTypeDefinition().
            getSchema().
            getSchemaForSchema().  
            resolveElementDeclaration("whiteSpace").
            getTypeDefinition()).
          getAttributeContents().get(0)).
        getAttributeDeclaration().getTypeDefinition();

    checkSimpleTypeConstraint
      (whiteSpaceEnumeration,
       getLexicalValue(),
       XSDConstants.PART2,
       "element-whiteSpace",
       getElement(),
       XSDConstants.VALUE_ATTRIBUTE,
       true);
  }

  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    if (eAttribute == XSDPackage.eINSTANCE.getXSDFacet_LexicalValue())
    {
      String newValue = getLexicalValue();
      XSDWhiteSpace literal = XSDWhiteSpace.get(newValue);
      if (literal == null)
      {
        if (getValue() != XSDWhiteSpace.PRESERVE_LITERAL)
        {
          setValue(XSDWhiteSpace.PRESERVE_LITERAL);
        }
      } 
      else if (literal != getValue())
      {
        setValue(literal);
      }
    }
  }
  public String getNormalizedLiteral(String literal)
  {
    switch (getValue().getValue())
    {
      case XSDWhiteSpace.COLLAPSE:
      {
        StringBuffer stringBuffer = new StringBuffer();
        boolean holdWhitespace = false;
        for (int i = 0, length = literal.length(); i < length; ++i)
        {
          char character = literal.charAt(i);
          if (Character.isWhitespace(character))
          {
            holdWhitespace = true;
          }
          else 
          {
            if (stringBuffer.length() == 0)
            {
              holdWhitespace = false;
            }
            if (holdWhitespace)
            {
              holdWhitespace = false;
              stringBuffer.append(" ");
            }
            stringBuffer.append(character);
          }
        }
        return stringBuffer.toString();
      }
      case XSDWhiteSpace.REPLACE:
      {
        StringBuffer stringBuffer = new StringBuffer(literal);
        for (int i = 0, length = literal.length(); i < length; ++i)
        {
          char character = literal.charAt(i);
          if (Character.isWhitespace(character))
          {
           stringBuffer.setCharAt(i, Character.isWhitespace(character) ? ' ' : character);
          }
        }
        return stringBuffer.toString();
      }
      default:
      {
        return literal;
      }
    }
  }

  public Object getEffectiveValue()
  {
    return getValue();
  }

  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDWhiteSpaceFacetImpl clonedWhiteSpaceFacet =
      (XSDWhiteSpaceFacetImpl)getXSDFactory().createXSDWhiteSpaceFacet();
    clonedWhiteSpaceFacet.isReconciling = true;

    if (getLexicalValue() != null)
    {
      clonedWhiteSpaceFacet.setLexicalValue(getLexicalValue());
    }
    if (isSetFixed())
    {
      clonedWhiteSpaceFacet.setFixed(isFixed());
    }

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedWhiteSpaceFacet.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedWhiteSpaceFacet.setElement(getElement());
    }

    clonedWhiteSpaceFacet.isReconciling = shareDOM;
    return clonedWhiteSpaceFacet;
  }
}
