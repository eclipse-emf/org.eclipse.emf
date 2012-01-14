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


import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EObjectEList;

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
  protected EList<XSDAnnotation> annotations;

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

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDRepeatableFacetImpl()
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
    return XSDPackage.Literals.XSD_REPEATABLE_FACET;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDAnnotation> getAnnotations()
  {
    if (annotations == null)
    {
      annotations = new EObjectEList<XSDAnnotation>(XSDAnnotation.class, this, XSDPackage.XSD_REPEATABLE_FACET__ANNOTATIONS);
    }
    return annotations;
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
      case XSDPackage.XSD_REPEATABLE_FACET__ANNOTATIONS:
        return getAnnotations();
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
      case XSDPackage.XSD_REPEATABLE_FACET__ANNOTATIONS:
        getAnnotations().clear();
        getAnnotations().addAll((Collection<? extends XSDAnnotation>)newValue);
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
      case XSDPackage.XSD_REPEATABLE_FACET__ANNOTATIONS:
        getAnnotations().clear();
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
      case XSDPackage.XSD_REPEATABLE_FACET__ANNOTATIONS:
        return annotations != null && !annotations.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  @Override
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

    checkBuiltInTypeConstraint
      ("ID",
       null,
       XSDConstants.PART2,
       "element-" + getFacetName(),
       theElement,
       XSDConstants.ID_ATTRIBUTE,
       false);
  }

  @Override
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
