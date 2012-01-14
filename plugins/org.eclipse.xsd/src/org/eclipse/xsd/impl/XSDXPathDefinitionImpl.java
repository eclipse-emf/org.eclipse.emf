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

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
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
   * The offset of the flags representing the value of the '{@link #getVariety() <em>Variety</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int VARIETY_EFLAG_OFFSET = 8;

  /**
   * The flags representing the default value of the '{@link #getVariety() <em>Variety</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int VARIETY_EFLAG_DEFAULT = VARIETY_EDEFAULT.ordinal() << VARIETY_EFLAG_OFFSET;

  /**
   * The array of enumeration values for '{@link XSDXPathVariety XPath Variety}'
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  private static final XSDXPathVariety[] VARIETY_EFLAG_VALUES = XSDXPathVariety.values();

  /**
   * The flag representing the value of the '{@link #getVariety() <em>Variety</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariety()
   * @generated
   * @ordered
   */
  protected static final int VARIETY_EFLAG = 1 << VARIETY_EFLAG_OFFSET;

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
  protected XSDAnnotation annotation;

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
  @Override
  protected EClass eStaticClass()
  {
    return XSDPackage.Literals.XSD_XPATH_DEFINITION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDXPathVariety getVariety()
  {
    return VARIETY_EFLAG_VALUES[(eFlags & VARIETY_EFLAG) >>> VARIETY_EFLAG_OFFSET];
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVariety(XSDXPathVariety newVariety)
  {
    XSDXPathVariety oldVariety = VARIETY_EFLAG_VALUES[(eFlags & VARIETY_EFLAG) >>> VARIETY_EFLAG_OFFSET];
    if (newVariety == null) newVariety = VARIETY_EDEFAULT;
    eFlags = eFlags & ~VARIETY_EFLAG | newVariety.ordinal() << VARIETY_EFLAG_OFFSET;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_XPATH_DEFINITION__VARIETY, oldVariety, newVariety));
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
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_XPATH_DEFINITION__ANNOTATION:
        return basicSetAnnotation(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_XPATH_DEFINITION__VARIETY:
        return getVariety();
      case XSDPackage.XSD_XPATH_DEFINITION__VALUE:
        return getValue();
      case XSDPackage.XSD_XPATH_DEFINITION__ANNOTATION:
        return getAnnotation();
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
      case XSDPackage.XSD_XPATH_DEFINITION__VARIETY:
        return (eFlags & VARIETY_EFLAG) != VARIETY_EFLAG_DEFAULT;
      case XSDPackage.XSD_XPATH_DEFINITION__VALUE:
        return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
      case XSDPackage.XSD_XPATH_DEFINITION__ANNOTATION:
        return annotation != null;
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
    result.append(" (variety: ");
    result.append(VARIETY_EFLAG_VALUES[(eFlags & VARIETY_EFLAG) >>> VARIETY_EFLAG_OFFSET]);
    result.append(", value: ");
    result.append(value);
    result.append(')');
    return result.toString();
  }

  @Override
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

  @Override
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

  @Override
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

  @Override
  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    Element theElement = getElement();
    if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_XPATH_DEFINITION__VARIETY)
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

  @Override
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
