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
 * $Id: XSDFixedFacetImpl.java,v 1.3 2005/04/13 19:19:34 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.Collection;
import java.util.Iterator;

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
import org.eclipse.xsd.XSDConstrainingFacet;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDFacet;
import org.eclipse.xsd.XSDFixedFacet;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fixed Facet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDFixedFacetImpl#isFixed <em>Fixed</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class XSDFixedFacetImpl 
  extends XSDConstrainingFacetImpl 
  implements XSDFixedFacet
{
  /**
   * The default value of the '{@link #isFixed() <em>Fixed</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isFixed()
   * @generated
   * @ordered
   */
  protected static final boolean FIXED_EDEFAULT = false;

  /**
   * The flag representing the value of the '{@link #isFixed() <em>Fixed</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isFixed()
   * @generated
   * @ordered
   */
  protected static final int FIXED_EFLAG = 1 << 8;

  /**
   * The flag representing whether the Fixed attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int FIXED_ESETFLAG = 1 << 9;

  public static XSDFixedFacet createFixedFacet(Node node)
  {
    switch (XSDConstants.nodeType(node))
    {
      case XSDConstants.FRACTIONDIGITS_ELEMENT:
      {
        return XSDFractionDigitsFacetImpl.createFractionDigitsFacet(node);
      }
      case XSDConstants.LENGTH_ELEMENT:
      {
        return XSDLengthFacetImpl.createLengthFacet(node);
      }
      case XSDConstants.MAXEXCLUSIVE_ELEMENT:
      {
        return XSDMaxExclusiveFacetImpl.createMaxExclusiveFacet(node);
      }
      case XSDConstants.MAXINCLUSIVE_ELEMENT:
      {
        return XSDMaxInclusiveFacetImpl.createMaxInclusiveFacet(node);
      }
      case XSDConstants.MAXLENGTH_ELEMENT:
      {
        return XSDMaxLengthFacetImpl.createMaxLengthFacet(node);
      }
      case XSDConstants.MINEXCLUSIVE_ELEMENT:
      {
        return XSDMinExclusiveFacetImpl.createMinExclusiveFacet(node);
      }
      case XSDConstants.MININCLUSIVE_ELEMENT:
      {
        return XSDMinInclusiveFacetImpl.createMinInclusiveFacet(node);
      }
      case XSDConstants.MINLENGTH_ELEMENT:
      {
        return XSDMinLengthFacetImpl.createMinLengthFacet(node);
      }
      case XSDConstants.TOTALDIGITS_ELEMENT:
      {
        return XSDTotalDigitsFacetImpl.createTotalDigitsFacet(node);
      }
      case XSDConstants.WHITESPACE_ELEMENT:
      {
        return XSDWhiteSpaceFacetImpl.createWhiteSpaceFacet(node);
      }
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDFixedFacetImpl()
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
    return XSDPackage.eINSTANCE.getXSDFixedFacet();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isFixed()
  {
    return (eFlags & FIXED_EFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFixed(boolean newFixed)
  {
    boolean oldFixed = (eFlags & FIXED_EFLAG) != 0;
    if (newFixed) eFlags |= FIXED_EFLAG; else eFlags &= ~FIXED_EFLAG;
    boolean oldFixedESet = (eFlags & FIXED_ESETFLAG) != 0;
    eFlags |= FIXED_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_FIXED_FACET__FIXED, oldFixed, newFixed, !oldFixedESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetFixed()
  {
    boolean oldFixed = (eFlags & FIXED_EFLAG) != 0;
    boolean oldFixedESet = (eFlags & FIXED_ESETFLAG) != 0;
    if (FIXED_EDEFAULT) eFlags |= FIXED_EFLAG; else eFlags &= ~FIXED_EFLAG;
    eFlags &= ~FIXED_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, XSDPackage.XSD_FIXED_FACET__FIXED, oldFixed, FIXED_EDEFAULT, oldFixedESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetFixed()
  {
    return (eFlags & FIXED_ESETFLAG) != 0;
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
        case XSDPackage.XSD_FIXED_FACET__DIAGNOSTICS:
          return ((InternalEList)getDiagnostics()).basicRemove(otherEnd, msgs);
        case XSDPackage.XSD_FIXED_FACET__ANNOTATION:
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
      case XSDPackage.XSD_FIXED_FACET__ELEMENT:
        return getElement();
      case XSDPackage.XSD_FIXED_FACET__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_FIXED_FACET__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_FIXED_FACET__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_FIXED_FACET__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_FIXED_FACET__LEXICAL_VALUE:
        return getLexicalValue();
      case XSDPackage.XSD_FIXED_FACET__FACET_NAME:
        return getFacetName();
      case XSDPackage.XSD_FIXED_FACET__EFFECTIVE_VALUE:
        return getEffectiveValue();
      case XSDPackage.XSD_FIXED_FACET__ANNOTATION:
        return getAnnotation();
      case XSDPackage.XSD_FIXED_FACET__SIMPLE_TYPE_DEFINITION:
        return getSimpleTypeDefinition();
      case XSDPackage.XSD_FIXED_FACET__FIXED:
        return isFixed() ? Boolean.TRUE : Boolean.FALSE;
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
      case XSDPackage.XSD_FIXED_FACET__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_FIXED_FACET__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_FIXED_FACET__LEXICAL_VALUE:
        setLexicalValue((String)newValue);
        return;
      case XSDPackage.XSD_FIXED_FACET__ANNOTATION:
        setAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_FIXED_FACET__FIXED:
        setFixed(((Boolean)newValue).booleanValue());
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
      case XSDPackage.XSD_FIXED_FACET__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_FIXED_FACET__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_FIXED_FACET__LEXICAL_VALUE:
        setLexicalValue(LEXICAL_VALUE_EDEFAULT);
        return;
      case XSDPackage.XSD_FIXED_FACET__ANNOTATION:
        setAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_FIXED_FACET__FIXED:
        unsetFixed();
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
      case XSDPackage.XSD_FIXED_FACET__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_FIXED_FACET__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_FIXED_FACET__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_FIXED_FACET__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_FIXED_FACET__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_FIXED_FACET__LEXICAL_VALUE:
        return LEXICAL_VALUE_EDEFAULT == null ? lexicalValue != null : !LEXICAL_VALUE_EDEFAULT.equals(lexicalValue);
      case XSDPackage.XSD_FIXED_FACET__FACET_NAME:
        return FACET_NAME_EDEFAULT == null ? getFacetName() != null : !FACET_NAME_EDEFAULT.equals(getFacetName());
      case XSDPackage.XSD_FIXED_FACET__EFFECTIVE_VALUE:
        return EFFECTIVE_VALUE_EDEFAULT == null ? getEffectiveValue() != null : !EFFECTIVE_VALUE_EDEFAULT.equals(getEffectiveValue());
      case XSDPackage.XSD_FIXED_FACET__ANNOTATION:
        return annotation != null;
      case XSDPackage.XSD_FIXED_FACET__SIMPLE_TYPE_DEFINITION:
        return getSimpleTypeDefinition() != null;
      case XSDPackage.XSD_FIXED_FACET__FIXED:
        return isSetFixed();
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
    result.append(" (fixed: ");
    if ((eFlags & FIXED_ESETFLAG) != 0) result.append((eFlags & FIXED_EFLAG) != 0); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

  public void validate()
  {
    super.validate();

    Element theElement = getElement();

    checkBuiltInTypeConstraint
      ("boolean",
       null,
       XSDConstants.PART2,
       "element-" + getFacetName(),
       theElement,
       XSDConstants.FIXED_ATTRIBUTE,
       false);

    checkAttributes
      (XSDConstants.PART2,
       "element-" + getFacetName(),
       theElement,
       new String []
       {
         XSDConstants.FIXED_ATTRIBUTE,
         XSDConstants.VALUE_ATTRIBUTE,
         XSDConstants.ID_ATTRIBUTE
       });

    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = getSimpleTypeDefinition();
    for (Iterator facets = xsdSimpleTypeDefinition.getFacetContents().iterator(); facets.hasNext(); )
    {
      XSDFacet xsdFacet = (XSDFacet)facets.next();
      if (xsdFacet == this)
      {
        break;
      }
      else if (xsdFacet.eClass() == eClass())
      {
        XSDDiagnostic xsdDiagnostic =
          reportConstraintViolation
            (XSDConstants.PART2,
             "src-single-facet-value",
             theElement,
             XSDConstants.VALUE_ATTRIBUTE,
             new Object [] { getFacetName() });
        xsdDiagnostic.getComponents().add(xsdFacet);
        break;
      }
    }

    XSDSimpleTypeDefinition baseTypeDefinition = xsdSimpleTypeDefinition.getBaseTypeDefinition();
    if (baseTypeDefinition != null)
    {
      for (Iterator facets = baseTypeDefinition.getFacets().iterator(); facets.hasNext(); )
      {
        XSDConstrainingFacet xsdConstrainingFacet = (XSDConstrainingFacet)facets.next();

        if (xsdConstrainingFacet instanceof XSDFixedFacet)
        {
          XSDFixedFacet xsdFixedFacet = (XSDFixedFacet)xsdConstrainingFacet;

          if (eClass() == xsdFixedFacet.eClass())
          {
            if (xsdFixedFacet.isFixed() && !baseTypeDefinition.equalValues(getEffectiveValue(), xsdFixedFacet.getEffectiveValue()))
            {
              XSDDiagnostic xsdDiagnostic =
                reportConstraintViolation
                  (XSDConstants.PART2,
                   "facet-fixed-valid-restriction",
                   theElement,
                   XSDConstants.VALUE_ATTRIBUTE,
                   new Object [] { getLexicalValue(), xsdFixedFacet.getLexicalValue(), getFacetName(), baseTypeDefinition.getURI() });
              xsdDiagnostic.getComponents().add(xsdFixedFacet);

              // The spec has no anchor for this constraint
              //
              xsdDiagnostic.setAnnotationURI(XSDConstants.PART2 + "#" + "dc-" + getFacetName());

              break;
            }
          }

          if (restrictionMatch(xsdFixedFacet))
          {
            validateRestriction(xsdFixedFacet);
            break;
          }
        }
      }
    }
  }

  protected void validateValue()
  {
    checkBuiltInTypeConstraint
      ("nonNegativeInteger",
       getLexicalValue(),
       XSDConstants.PART2,
       "element" + getFacetName(),
       getElement(),
       XSDConstants.VALUE_ATTRIBUTE,
       true);
  }

  protected boolean restrictionMatch(XSDFixedFacet xsdFixedFacet)
  {
    return xsdFixedFacet.eClass() == eClass();
  }

  protected void validateRestriction(XSDFixedFacet xsdFixedFacet)
  {
  }

  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    if (changedElement == getElement())
    {
      if (changedElement.hasAttributeNS(null, XSDConstants.FIXED_ATTRIBUTE))
      {
        boolean newFixed = "true".equals(changedElement.getAttributeNS(null, XSDConstants.FIXED_ATTRIBUTE));
        if (!isSetFixed() || newFixed != isFixed())
        {
          setFixed(newFixed);
        }
      }
      else
      {
        unsetFixed();
      }
    }
  }

  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    if (!isReconciling)
    {
      if (eAttribute == null || eAttribute == XSDPackage.eINSTANCE.getXSDFixedFacet_Fixed())
      {
        Element theElement = getElement();
        if (theElement != null)
        {
          niceSetAttribute(theElement, XSDConstants.FIXED_ATTRIBUTE, isSetFixed() ? (isFixed() ? "true" : "false") : null);
        }
      }
    }
  }
} 
