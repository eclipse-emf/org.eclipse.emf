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
 * $Id: XSDMaxInclusiveFacetImpl.java,v 1.7 2005/11/24 19:06:02 emerks Exp $
 */
package org.eclipse.xsd.impl; 


import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDMaxInclusiveFacet;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Max Inclusive Facet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class XSDMaxInclusiveFacetImpl 
  extends XSDMaxFacetImpl 
  implements XSDMaxInclusiveFacet
{
  public static XSDMaxInclusiveFacet createMaxInclusiveFacet(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.MAXINCLUSIVE_ELEMENT)
    {
      XSDMaxInclusiveFacet xsdMaxInclusiveFacet = XSDFactory.eINSTANCE.createXSDMaxInclusiveFacet();
      xsdMaxInclusiveFacet.setElement((Element)node);
      return xsdMaxInclusiveFacet;
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDMaxInclusiveFacetImpl()
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
    return XSDPackage.Literals.XSD_MAX_INCLUSIVE_FACET;
  }

  public Element createElement()
  {
    Element newElement = createElement(XSDConstants.MAXINCLUSIVE_ELEMENT);
    setElement(newElement);
    return newElement;
  }

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
          newValue = xsdSimpleTypeDefinition.getValue(theLexicalValue);
        }
        catch (RuntimeException exception)
        {
        }
      }
    }

    if (newValue == null ? getValue() != null : !newValue.equals(getValue()))
    {
      setValue(newValue);
    }

    return true;
  }

  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    if (eAttribute == XSDPackage.Literals.XSD_FACET__LEXICAL_VALUE)
    {
      traverseToRootForAnalysis();
    }
  }

  public boolean isConstraintSatisfied(Object value)
  {
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)getContainer();
    if (xsdSimpleTypeDefinition != null)
    {
      return xsdSimpleTypeDefinition.compareValues(value,  getValue()) <= 0;
    }

    return false;
  }

  public Object getEffectiveValue()
  {
    return getValue();
  }

  public boolean isInclusive()
  {
    return true;
  }

  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDMaxInclusiveFacetImpl clonedMaxInclusiveFacet =
      (XSDMaxInclusiveFacetImpl)getXSDFactory().createXSDMaxInclusiveFacet();
    clonedMaxInclusiveFacet.isReconciling = true;

    if (getLexicalValue() != null)
    {
      clonedMaxInclusiveFacet.setLexicalValue(getLexicalValue());
    }
    if (isSetFixed())
    {
      clonedMaxInclusiveFacet.setFixed(isFixed());
    }

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedMaxInclusiveFacet.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedMaxInclusiveFacet.setElement(getElement());
    }

    clonedMaxInclusiveFacet.isReconciling = shareDOM;
    return clonedMaxInclusiveFacet;
  }
}
