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
 * $Id: XSDMaxFacetImpl.java,v 1.12 2007/11/26 12:20:54 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDFixedFacet;
import org.eclipse.xsd.XSDMaxExclusiveFacet;
import org.eclipse.xsd.XSDMaxFacet;
import org.eclipse.xsd.XSDMaxInclusiveFacet;
import org.eclipse.xsd.XSDMinExclusiveFacet;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;
import org.w3c.dom.Element;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Max Facet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDMaxFacetImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDMaxFacetImpl#isInclusive <em>Inclusive</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDMaxFacetImpl#isExclusive <em>Exclusive</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class XSDMaxFacetImpl 
  extends XSDFixedFacetImpl 
  implements XSDMaxFacet
{
  /**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final Object VALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected Object value = VALUE_EDEFAULT;

  /**
   * The default value of the '{@link #isInclusive() <em>Inclusive</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isInclusive()
   * @generated
   * @ordered
   */
  protected static final boolean INCLUSIVE_EDEFAULT = false;

  /**
   * The default value of the '{@link #isExclusive() <em>Exclusive</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isExclusive()
   * @generated
   * @ordered
   */
  protected static final boolean EXCLUSIVE_EDEFAULT = false;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDMaxFacetImpl()
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
    return XSDPackage.Literals.XSD_MAX_FACET;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(Object newValue)
  {
    Object oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_MAX_FACET__VALUE, oldValue, value));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public Boolean getInclusive() 
  {
    return isInclusive() ? Boolean.TRUE : Boolean.FALSE;
  }

  public boolean isInclusive()
  {
    return false;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public Boolean getExclusive() 
  {
    return isExclusive() ? Boolean.TRUE : Boolean.FALSE;
  }

  public boolean isExclusive()
  {
    return false;
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
      case XSDPackage.XSD_MAX_FACET__VALUE:
        return getValue();
      case XSDPackage.XSD_MAX_FACET__INCLUSIVE:
        return isInclusive() ? Boolean.TRUE : Boolean.FALSE;
      case XSDPackage.XSD_MAX_FACET__EXCLUSIVE:
        return isExclusive() ? Boolean.TRUE : Boolean.FALSE;
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
      case XSDPackage.XSD_MAX_FACET__VALUE:
        setValue(newValue);
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
      case XSDPackage.XSD_MAX_FACET__VALUE:
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
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_MAX_FACET__VALUE:
        return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
      case XSDPackage.XSD_MAX_FACET__INCLUSIVE:
        return isInclusive() != INCLUSIVE_EDEFAULT;
      case XSDPackage.XSD_MAX_FACET__EXCLUSIVE:
        return isExclusive() != EXCLUSIVE_EDEFAULT;
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
  public void validate()
  {
    super.validate();

    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = getSimpleTypeDefinition();
    if (isExclusive())
    {
      XSDMaxInclusiveFacet xsdMaxInclusiveFacet = xsdSimpleTypeDefinition.getMaxInclusiveFacet();
      if (xsdMaxInclusiveFacet != null)
      {
        XSDDiagnostic xsdDiagnostic =
          reportConstraintViolation
            (XSDConstants.PART2,
             "maxInclusive-maxExclusive",
             getElement(),
             XSDConstants.VALUE_ATTRIBUTE,
             new Object [] { });
        xsdDiagnostic.getComponents().add(xsdMaxInclusiveFacet);
      }
    }
  }

  @Override
  protected void validateValue()
  {
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)getContainer();
    XSDSimpleTypeDefinition baseTypeDefinition = xsdSimpleTypeDefinition.getBaseTypeDefinition();
    if (baseTypeDefinition != null)
    {
      Element theElement = getElement();
      if (getLexicalValue() == null)
      {
        createRequiredAttributeDiagnostic(XSDConstants.PART2, "element-" + getFacetName(), theElement, XSDConstants.VALUE_ATTRIBUTE);
      }
      else
      {
        XSDSimpleTypeDefinitionImpl.AssessmentImpl assessment =
          (XSDSimpleTypeDefinitionImpl.AssessmentImpl)baseTypeDefinition.assess(theElement, getLexicalValue());

        Collection<XSDDiagnostic> allDiagnostics = assessment.getDiagnostics();
        if (!allDiagnostics.isEmpty())
        {
          // This is to ignores exclusive violations which should be caught via restriction validation.
          //
          if (isExclusive())
          {
            for (Iterator<XSDDiagnostic> i = allDiagnostics.iterator(); i.hasNext(); )
            {
              XSDDiagnostic xsdDiagnostic = i.next();
              XSDConcreteComponent primaryComponent = xsdDiagnostic.getPrimaryComponent();
              if (primaryComponent instanceof XSDMaxExclusiveFacet &&
                    baseTypeDefinition.equalLiterals
                      (theElement, getLexicalValue(), theElement, ((XSDMaxExclusiveFacet)primaryComponent).getLexicalValue()))
              {
                i.remove();
              }
            }
          }
          assessment.assignDiagnostics(this, theElement, XSDConstants.VALUE_ATTRIBUTE);
          getDiagnostics().addAll(allDiagnostics);
        }
      }
    }
  }

  @Override
  protected boolean restrictionMatch(XSDFixedFacet xsdFixedFacet)
  {
    return xsdFixedFacet instanceof XSDMaxExclusiveFacet || xsdFixedFacet instanceof XSDMinExclusiveFacet;
  }

  @Override
  protected void validateRestriction(XSDFixedFacet xsdFixedFacet)
  {
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = getSimpleTypeDefinition();
    int compare = xsdSimpleTypeDefinition.compareValues(getValue(), xsdFixedFacet.getEffectiveValue());
    if (xsdFixedFacet instanceof XSDMaxExclusiveFacet ? compare > 0 : compare <= 0)
    {
      XSDDiagnostic xsdDiagnostic =
        reportConstraintViolation
          (XSDConstants.PART2,
           getFacetName() + (xsdFixedFacet instanceof XSDMaxExclusiveFacet ? "-valid-restriction.2" : "-valid-restriction.4"),
           getElement(),
           XSDConstants.VALUE_ATTRIBUTE,
           new Object [] { getLexicalValue(), xsdFixedFacet.getLexicalValue(), xsdFixedFacet.getSimpleTypeDefinition().getURI() });
      xsdDiagnostic.getComponents().add(xsdFixedFacet);
    }
  }
}
