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
 * $Id: XSDMinFacetImpl.java,v 1.3 2004/08/11 15:08:55 marcelop Exp $
 */
package org.eclipse.xsd.impl;


import java.util.Collection;
import java.util.Iterator;

import org.w3c.dom.Element;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDFixedFacet;
import org.eclipse.xsd.XSDMaxExclusiveFacet;
import org.eclipse.xsd.XSDMaxFacet;
import org.eclipse.xsd.XSDMinExclusiveFacet;
import org.eclipse.xsd.XSDMinFacet;
import org.eclipse.xsd.XSDMinInclusiveFacet;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Min Facet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDMinFacetImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDMinFacetImpl#isInclusive <em>Inclusive</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDMinFacetImpl#isExclusive <em>Exclusive</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class XSDMinFacetImpl 
  extends XSDFixedFacetImpl 
  implements XSDMinFacet
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

  protected XSDMinFacetImpl() 
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
    return XSDPackage.eINSTANCE.getXSDMinFacet();
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
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_MIN_FACET__VALUE, oldValue, value));
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
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case XSDPackage.XSD_MIN_FACET__DIAGNOSTICS:
          return ((InternalEList)getDiagnostics()).basicRemove(otherEnd, msgs);
        case XSDPackage.XSD_MIN_FACET__ANNOTATION:
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
      case XSDPackage.XSD_MIN_FACET__ELEMENT:
        return getElement();
      case XSDPackage.XSD_MIN_FACET__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_MIN_FACET__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_MIN_FACET__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_MIN_FACET__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_MIN_FACET__LEXICAL_VALUE:
        return getLexicalValue();
      case XSDPackage.XSD_MIN_FACET__FACET_NAME:
        return getFacetName();
      case XSDPackage.XSD_MIN_FACET__EFFECTIVE_VALUE:
        return getEffectiveValue();
      case XSDPackage.XSD_MIN_FACET__ANNOTATION:
        return getAnnotation();
      case XSDPackage.XSD_MIN_FACET__SIMPLE_TYPE_DEFINITION:
        return getSimpleTypeDefinition();
      case XSDPackage.XSD_MIN_FACET__FIXED:
        return isFixed() ? Boolean.TRUE : Boolean.FALSE;
      case XSDPackage.XSD_MIN_FACET__VALUE:
        return getValue();
      case XSDPackage.XSD_MIN_FACET__INCLUSIVE:
        return isInclusive() ? Boolean.TRUE : Boolean.FALSE;
      case XSDPackage.XSD_MIN_FACET__EXCLUSIVE:
        return isExclusive() ? Boolean.TRUE : Boolean.FALSE;
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
      case XSDPackage.XSD_MIN_FACET__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_MIN_FACET__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_MIN_FACET__LEXICAL_VALUE:
        setLexicalValue((String)newValue);
        return;
      case XSDPackage.XSD_MIN_FACET__ANNOTATION:
        setAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_MIN_FACET__FIXED:
        setFixed(((Boolean)newValue).booleanValue());
        return;
      case XSDPackage.XSD_MIN_FACET__VALUE:
        setValue((Object)newValue);
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
      case XSDPackage.XSD_MIN_FACET__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_MIN_FACET__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_MIN_FACET__LEXICAL_VALUE:
        setLexicalValue(LEXICAL_VALUE_EDEFAULT);
        return;
      case XSDPackage.XSD_MIN_FACET__ANNOTATION:
        setAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_MIN_FACET__FIXED:
        unsetFixed();
        return;
      case XSDPackage.XSD_MIN_FACET__VALUE:
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
      case XSDPackage.XSD_MIN_FACET__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_MIN_FACET__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_MIN_FACET__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_MIN_FACET__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_MIN_FACET__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_MIN_FACET__LEXICAL_VALUE:
        return LEXICAL_VALUE_EDEFAULT == null ? lexicalValue != null : !LEXICAL_VALUE_EDEFAULT.equals(lexicalValue);
      case XSDPackage.XSD_MIN_FACET__FACET_NAME:
        return FACET_NAME_EDEFAULT == null ? getFacetName() != null : !FACET_NAME_EDEFAULT.equals(getFacetName());
      case XSDPackage.XSD_MIN_FACET__EFFECTIVE_VALUE:
        return EFFECTIVE_VALUE_EDEFAULT == null ? getEffectiveValue() != null : !EFFECTIVE_VALUE_EDEFAULT.equals(getEffectiveValue());
      case XSDPackage.XSD_MIN_FACET__ANNOTATION:
        return annotation != null;
      case XSDPackage.XSD_MIN_FACET__SIMPLE_TYPE_DEFINITION:
        return getSimpleTypeDefinition() != null;
      case XSDPackage.XSD_MIN_FACET__FIXED:
        return isSetFixed();
      case XSDPackage.XSD_MIN_FACET__VALUE:
        return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
      case XSDPackage.XSD_MIN_FACET__INCLUSIVE:
        return isInclusive() != INCLUSIVE_EDEFAULT;
      case XSDPackage.XSD_MIN_FACET__EXCLUSIVE:
        return isExclusive() != EXCLUSIVE_EDEFAULT;
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

  public void validate()
  {
    super.validate();

    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = getSimpleTypeDefinition();
    XSDMaxFacet xsdMaxFacet = xsdSimpleTypeDefinition.getMaxFacet();

    if (xsdMaxFacet != null)
    {
      int comparison = xsdSimpleTypeDefinition.compareValues(getValue(), xsdMaxFacet.getValue());
      if (xsdMaxFacet.isInclusive() == isInclusive() ? comparison > 0 : comparison >= 0)
      {
        XSDDiagnostic xsdDiagnostic =
          reportConstraintViolation
            (XSDConstants.PART2,
             getFacetName() + (xsdMaxFacet.isInclusive() == isInclusive() ? "-less-than-equal-to-" : "-less-than-") + xsdMaxFacet.getFacetName(),
             getElement(),
             XSDConstants.VALUE_ATTRIBUTE,
             new Object [] { getLexicalValue(), xsdMaxFacet.getLexicalValue() });
        xsdDiagnostic.getComponents().add(xsdMaxFacet);
      }
    }

    if (isExclusive())
    {
      XSDMinInclusiveFacet xsdMinInclusiveFacet = xsdSimpleTypeDefinition.getMinInclusiveFacet();
      if (xsdMinInclusiveFacet != null)
      {
        XSDDiagnostic xsdDiagnostic =
          reportConstraintViolation
            (XSDConstants.PART2,
             "minInclusive-minExclusive",
             getElement(),
             XSDConstants.VALUE_ATTRIBUTE,
             new Object [] { });
        xsdDiagnostic.getComponents().add(xsdMinInclusiveFacet);
      }
    }
  }

  protected void validateValue()
  {
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)getContainer();
    XSDSimpleTypeDefinition baseTypeDefinition = xsdSimpleTypeDefinition.getBaseTypeDefinition();
    if (baseTypeDefinition != null)
    {
      if (getLexicalValue() == null)
      {
        createRequiredAttributeDiagnostic(XSDConstants.PART2, "element-" + getFacetName(), getElement(), XSDConstants.VALUE_ATTRIBUTE);
      }
      else
      {
        XSDSimpleTypeDefinitionImpl.AssessmentImpl assessment =
          (XSDSimpleTypeDefinitionImpl.AssessmentImpl)baseTypeDefinition.assess(getLexicalValue());

        Collection allDiagnostics = assessment.getDiagnostics();
        if (!allDiagnostics.isEmpty())
        {
          // This is to ignores exclusive violations which should be caught via restriction validation.
          //
          if (isExclusive())
          {
            for (Iterator i = allDiagnostics.iterator(); i.hasNext(); )
            {
              XSDDiagnostic xsdDiagnostic = (XSDDiagnostic)i.next();
              XSDConcreteComponent primaryComponent = xsdDiagnostic.getPrimaryComponent();
              if (primaryComponent instanceof XSDMinExclusiveFacet && 
                   baseTypeDefinition.equalLiterals
                     (getLexicalValue(), ((XSDMinExclusiveFacet)primaryComponent).getLexicalValue()))
              {
                i.remove();
              }
            }
          }

          assessment.assignDiagnostics(this, getElement(), XSDConstants.VALUE_ATTRIBUTE);
          getDiagnostics().addAll(allDiagnostics);
        }
      }
    }
  }

  protected boolean restrictionMatch(XSDFixedFacet xsdFixedFacet)
  {
    return xsdFixedFacet instanceof XSDMaxExclusiveFacet || xsdFixedFacet instanceof XSDMinExclusiveFacet;
  }

  protected void validateRestriction(XSDFixedFacet xsdFixedFacet)
  {
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = getSimpleTypeDefinition();
    int comparison = xsdSimpleTypeDefinition.compareValues(getValue(), xsdFixedFacet.getEffectiveValue());
    if (xsdFixedFacet instanceof XSDMinExclusiveFacet ? comparison < 0 : comparison >= 0)
    {
      XSDDiagnostic xsdDiagnostic =
        reportConstraintViolation
          (XSDConstants.PART2,
           getFacetName() + (xsdFixedFacet instanceof XSDMinExclusiveFacet ? "-valid-restriction.2" : "-valid-restriction.4"),
           getElement(),
           XSDConstants.VALUE_ATTRIBUTE,
           new Object [] { getLexicalValue(), xsdFixedFacet.getLexicalValue(), xsdFixedFacet.getSimpleTypeDefinition().getURI() });
      xsdDiagnostic.getComponents().add(xsdFixedFacet);
    }
  }
}
