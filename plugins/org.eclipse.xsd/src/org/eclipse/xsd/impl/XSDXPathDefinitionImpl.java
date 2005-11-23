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
 * $Id: XSDXPathDefinitionImpl.java,v 1.4 2005/11/23 13:56:55 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.Collection;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDXPathDefinition;
import org.eclipse.xsd.XSDXPathVariety;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XPath Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDXPathDefinitionImpl#getVariety <em>Variety</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDXPathDefinitionImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDXPathDefinitionImpl#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDXPathDefinitionImpl 
  extends XSDComponentImpl 
  implements XSDXPathDefinition
{
  /**
   * The default value of the '{@link #getVariety() <em>Variety</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariety()
   * @generated
   * @ordered
   */
  protected static final XSDXPathVariety VARIETY_EDEFAULT = XSDXPathVariety.SELECTOR_LITERAL;

  /**
   * The cached value of the '{@link #getVariety() <em>Variety</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariety()
   * @generated
   * @ordered
   */
  protected XSDXPathVariety variety = VARIETY_EDEFAULT;

  /**
   * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected static final String VALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValue()
   * @generated
   * @ordered
   */
  protected String value = VALUE_EDEFAULT;

  /**
   * The cached value of the '{@link #getAnnotation() <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotation()
   * @generated
   * @ordered
   */
  protected XSDAnnotation annotation = null;

  public static XSDXPathDefinition createXPathDefinition(Node node)
  {
    switch (XSDConstants.nodeType(node))
    {
      case XSDConstants.SELECTOR_ELEMENT:
      case XSDConstants.FIELD_ELEMENT:
      {
        XSDXPathDefinition xsdXPathDefinition = XSDFactory.eINSTANCE.createXSDXPathDefinition();
        xsdXPathDefinition.setElement((Element)node);
        return xsdXPathDefinition;
      }
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDXPathDefinitionImpl()
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
    return XSDPackage.eINSTANCE.getXSDXPathDefinition();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDXPathVariety getVariety()
  {
    return variety;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVariety(XSDXPathVariety newVariety)
  {
    XSDXPathVariety oldVariety = variety;
    variety = newVariety == null ? VARIETY_EDEFAULT : newVariety;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_XPATH_DEFINITION__VARIETY, oldVariety, variety));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getValue()
  {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setValue(String newValue)
  {
    String oldValue = value;
    value = newValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_XPATH_DEFINITION__VALUE, oldValue, value));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDAnnotation getAnnotation()
  {
    return annotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnnotation(XSDAnnotation newAnnotation)
  {
    if (newAnnotation != annotation)
    {
      NotificationChain msgs = null;
      if (annotation != null)
        msgs = ((InternalEObject)annotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_XPATH_DEFINITION__ANNOTATION, null, msgs);
      if (newAnnotation != null)
        msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_XPATH_DEFINITION__ANNOTATION, null, msgs);
      msgs = basicSetAnnotation(newAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_XPATH_DEFINITION__ANNOTATION, newAnnotation, newAnnotation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAnnotation(XSDAnnotation newAnnotation, NotificationChain msgs)
  {
    XSDAnnotation oldAnnotation = annotation;
    annotation = newAnnotation;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_XPATH_DEFINITION__ANNOTATION, oldAnnotation, newAnnotation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
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
        case XSDPackage.XSD_XPATH_DEFINITION__DIAGNOSTICS:
          return ((InternalEList)getDiagnostics()).basicRemove(otherEnd, msgs);
        case XSDPackage.XSD_XPATH_DEFINITION__ANNOTATION:
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
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_XPATH_DEFINITION__ELEMENT:
        return getElement();
      case XSDPackage.XSD_XPATH_DEFINITION__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_XPATH_DEFINITION__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_XPATH_DEFINITION__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_XPATH_DEFINITION__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_XPATH_DEFINITION__VARIETY:
        return getVariety();
      case XSDPackage.XSD_XPATH_DEFINITION__VALUE:
        return getValue();
      case XSDPackage.XSD_XPATH_DEFINITION__ANNOTATION:
        return getAnnotation();
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
      case XSDPackage.XSD_XPATH_DEFINITION__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_XPATH_DEFINITION__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_XPATH_DEFINITION__VARIETY:
        setVariety((XSDXPathVariety)newValue);
        return;
      case XSDPackage.XSD_XPATH_DEFINITION__VALUE:
        setValue((String)newValue);
        return;
      case XSDPackage.XSD_XPATH_DEFINITION__ANNOTATION:
        setAnnotation((XSDAnnotation)newValue);
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
      case XSDPackage.XSD_XPATH_DEFINITION__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_XPATH_DEFINITION__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_XPATH_DEFINITION__VARIETY:
        setVariety(VARIETY_EDEFAULT);
        return;
      case XSDPackage.XSD_XPATH_DEFINITION__VALUE:
        setValue(VALUE_EDEFAULT);
        return;
      case XSDPackage.XSD_XPATH_DEFINITION__ANNOTATION:
        setAnnotation((XSDAnnotation)null);
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
      case XSDPackage.XSD_XPATH_DEFINITION__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_XPATH_DEFINITION__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_XPATH_DEFINITION__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_XPATH_DEFINITION__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_XPATH_DEFINITION__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_XPATH_DEFINITION__VARIETY:
        return variety != VARIETY_EDEFAULT;
      case XSDPackage.XSD_XPATH_DEFINITION__VALUE:
        return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
      case XSDPackage.XSD_XPATH_DEFINITION__ANNOTATION:
        return annotation != null;
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
    result.append(" (variety: ");
    result.append(variety);
    result.append(", value: ");
    result.append(value);
    result.append(')');
    return result.toString();
  }

  public Element createElement()
  {
    Element newElement = 
      createElement
        (XSDXPathVariety.SELECTOR_LITERAL == getVariety() ? 
           XSDConstants.SELECTOR_ELEMENT :
           XSDConstants.FIELD_ELEMENT);
    if (newElement != null)
    {
      newElement.setAttributeNS(null, XSDConstants.XPATH_ATTRIBUTE, getValue());

      setElement(newElement);
    }
    return newElement;
  }

  public void validate()
  {
    super.validate();

    String anchor = null;
    String elementName = null;
    switch (getVariety().getValue())
    {
      case XSDXPathVariety.FIELD:
      {
        anchor = "element-field";
        elementName = "field";
        break;
      }
      case XSDXPathVariety.SELECTOR:
      {
        anchor = "element-selector";
        elementName = "selector";
        break;
      }
    }

    Element theElement = getElement();
    if (theElement != null)
    {
      checkAttributes
        (XSDConstants.PART1,
         anchor,
         theElement,
         new String []
         {
           XSDConstants.ID_ATTRIBUTE,
           XSDConstants.XPATH_ATTRIBUTE
         });

      checkBuiltInTypeConstraint
        ("ID",
         null,
         XSDConstants.PART1,
         anchor,
         theElement,
         XSDConstants.ID_ATTRIBUTE,
         false);

      checkComplexContent("annotated", XSDConstants.PART1, anchor, theElement);
    }

    checkAttributeTypeConstraint
      ((XSDComplexTypeDefinition)getSchema().getSchemaForSchema().resolveElementDeclaration(elementName).getTypeDefinition(),
       XSDConstants.XPATH_ATTRIBUTE,
       getValue(),
       XSDConstants.PART1,
       anchor,
       theElement,
       XSDConstants.XPATH_ATTRIBUTE,
       true);
  }

  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    if (changedElement == getElement())
    {
      XSDXPathVariety newVariety = XSDXPathVariety.get(changedElement.getLocalName());
      if (newVariety != getVariety())
      {
        setVariety(newVariety);
      }

      if (changedElement.hasAttributeNS(null, XSDConstants.XPATH_ATTRIBUTE))
      {
        String newValue = changedElement.getAttributeNS(null, XSDConstants.XPATH_ATTRIBUTE);
        if (newValue == null || !newValue.equals(getValue()))
        {
          setValue(newValue);
        }
      }
      else if (getValue() != null)
      {
        setValue(null);
      }
    }
  }

  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    Element theElement = getElement();
    if (eAttribute == null || eAttribute == XSDPackage.eINSTANCE.getXSDXPathDefinition_Variety())
    {
      if (theElement != null && eAttribute != null && !isReconciling)
      {
        Element newElement = 
          createElement
            (XSDXPathVariety.SELECTOR_LITERAL == getVariety() ? 
               XSDConstants.SELECTOR_ELEMENT :
               XSDConstants.FIELD_ELEMENT);

        forceReplace(newElement, theElement);
        setElement(newElement);
      }
    }
  }

  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDXPathDefinitionImpl clonedXPathDefinition =
      (XSDXPathDefinitionImpl)getXSDFactory().createXSDXPathDefinition();
    clonedXPathDefinition.isReconciling = true;

    clonedXPathDefinition.setVariety(getVariety());
    clonedXPathDefinition.setValue(getValue());

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedXPathDefinition.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedXPathDefinition.setElement(getElement());
    }

    clonedXPathDefinition.isReconciling = shareDOM;
    return clonedXPathDefinition;
  }
} 
