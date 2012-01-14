/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.xsd.impl;


import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDMinExclusiveFacet;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Min Exclusive Facet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class XSDMinExclusiveFacetImpl 
  extends XSDMinFacetImpl 
  implements XSDMinExclusiveFacet
{
  public static XSDMinExclusiveFacet createMinExclusiveFacet(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.MINEXCLUSIVE_ELEMENT)
    {
      XSDMinExclusiveFacet xsdMinExclusiveFacet = XSDFactory.eINSTANCE.createXSDMinExclusiveFacet();
      xsdMinExclusiveFacet.setElement((Element)node);
      return xsdMinExclusiveFacet;
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDMinExclusiveFacetImpl()
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
    return XSDPackage.Literals.XSD_MIN_EXCLUSIVE_FACET;
  }

  @Override
  public Element createElement()
  {
    Element newElement = createElement(XSDConstants.MINEXCLUSIVE_ELEMENT);
    setElement(newElement);
    return newElement;
  }

  @Override
  protected boolean analyze()
  {
    super.analyze();
    String theLexicalValue = getLexicalValue();
    Object newValue = null;
    if (theLexicalValue != null)
    {
      XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)getContainer();
      if (xsdSimpleTypeDefinition != null)
      {
        try
        {
          newValue = xsdSimpleTypeDefinition.getValue(getElement(), theLexicalValue);
        }
        catch (RuntimeException exception)
        {
          // Ignore
        }
      }
    }

    if (newValue == null ? getValue() != null : !newValue.equals(getValue()))
    {
      setValue(newValue);
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
    }
  }

  @Override
  public boolean isConstraintSatisfied(Object value)
  {
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)getContainer();
    if (xsdSimpleTypeDefinition != null)
    {
      return xsdSimpleTypeDefinition.compareValues(value, getValue()) > 0;
    }

    return false;
  }

  @Override
  public Object getEffectiveValue()
  {
    return getValue();
  }

  @Override
  public boolean isExclusive()
  {
    return true;
  }

  @Override
  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDMinExclusiveFacetImpl clonedMinExclusiveFacet =
      (XSDMinExclusiveFacetImpl)getXSDFactory().createXSDMinExclusiveFacet();
    clonedMinExclusiveFacet.isReconciling = true;

    if (getLexicalValue() != null)
    {
      clonedMinExclusiveFacet.setLexicalValue(getLexicalValue());
    }
    if (isSetFixed())
    {
      clonedMinExclusiveFacet.setFixed(isFixed());
    }

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedMinExclusiveFacet.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedMinExclusiveFacet.setElement(getElement());
    }

    clonedMinExclusiveFacet.isReconciling = shareDOM;
    return clonedMinExclusiveFacet;
  }
} 
