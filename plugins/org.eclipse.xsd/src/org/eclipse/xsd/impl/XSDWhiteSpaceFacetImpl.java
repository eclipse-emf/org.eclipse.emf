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
 * $Id: XSDWhiteSpaceFacetImpl.java,v 1.9 2005/11/25 13:14:00 emerks Exp $
 */
package org.eclipse.xsd.impl;


import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

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
    return XSDPackage.Literals.XSD_WHITE_SPACE_FACET;
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
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_WHITE_SPACE_FACET__VALUE:
        return getValue();
    }
    return super.eGet(featureID, resolve, coreType);
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
      case XSDPackage.XSD_WHITE_SPACE_FACET__VALUE:
        setValue((XSDWhiteSpace)newValue);
        return;
    }
    super.eSet(featureID, newValue);
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
      case XSDPackage.XSD_WHITE_SPACE_FACET__VALUE:
        setValue(VALUE_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
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
      case XSDPackage.XSD_WHITE_SPACE_FACET__VALUE:
        return value != VALUE_EDEFAULT;
    }
    return super.eIsSet(featureID);
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
    if (eAttribute == XSDPackage.Literals.XSD_FACET__LEXICAL_VALUE)
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
