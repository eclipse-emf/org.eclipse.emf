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
 * $Id: XSDFixedFacetImpl.java,v 1.13 2008/01/30 19:26:53 emerks Exp $
 */
package org.eclipse.xsd.impl;


import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

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
  @Override
  protected EClass eStaticClass()
  {
    return XSDPackage.Literals.XSD_FIXED_FACET;
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
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_FIXED_FACET__FIXED:
        return isFixed() ? Boolean.TRUE : Boolean.FALSE;
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_FIXED_FACET__FIXED:
        setFixed(((Boolean)newValue).booleanValue());
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
      case XSDPackage.XSD_FIXED_FACET__FIXED:
        unsetFixed();
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
      case XSDPackage.XSD_FIXED_FACET__FIXED:
        return isSetFixed();
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
    result.append(" (fixed: ");
    if ((eFlags & FIXED_ESETFLAG) != 0) result.append((eFlags & FIXED_EFLAG) != 0); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

  @Override
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

    checkBuiltInTypeConstraint
      ("ID",
       null,
       XSDConstants.PART2,
       "element-" + getFacetName(),
       theElement,
       XSDConstants.ID_ATTRIBUTE,
       false);

    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = getSimpleTypeDefinition();
    for (XSDFacet xsdFacet : xsdSimpleTypeDefinition.getFacetContents())
    {
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
      for (XSDConstrainingFacet xsdConstrainingFacet : baseTypeDefinition.getFacets())
      {
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

  @Override
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
    // Do nothing.
  }

  @Override
  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    if (changedElement == getElement())
    {
      if (changedElement.hasAttributeNS(null, XSDConstants.FIXED_ATTRIBUTE))
      {
        boolean newFixed = convertToBoolean(changedElement.getAttributeNS(null, XSDConstants.FIXED_ATTRIBUTE));
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

  @Override
  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    if (!isReconciling)
    {
      if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_FIXED_FACET__FIXED)
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
