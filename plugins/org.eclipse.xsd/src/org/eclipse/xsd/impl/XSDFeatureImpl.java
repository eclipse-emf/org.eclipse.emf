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
 * $Id: XSDFeatureImpl.java,v 1.4 2005/04/13 19:19:34 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.Collection;

import org.w3c.dom.Element;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xsd.XSDAttributeGroupDefinition;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDConstraint;
import org.eclipse.xsd.XSDFeature;
import org.eclipse.xsd.XSDForm;
import org.eclipse.xsd.XSDModelGroupDefinition;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDScope;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDFeatureImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDFeatureImpl#getConstraint <em>Constraint</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDFeatureImpl#getForm <em>Form</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDFeatureImpl#getLexicalValue <em>Lexical Value</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDFeatureImpl#isGlobal <em>Global</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDFeatureImpl#isFeatureReference <em>Feature Reference</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDFeatureImpl#getScope <em>Scope</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDFeatureImpl#getResolvedFeature <em>Resolved Feature</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDFeatureImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class XSDFeatureImpl 
  extends XSDNamedComponentImpl 
  implements XSDFeature
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
   * The default value of the '{@link #getConstraint() <em>Constraint</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstraint()
   * @generated
   * @ordered
   */
  protected static final XSDConstraint CONSTRAINT_EDEFAULT = XSDConstraint.DEFAULT_LITERAL;

  /**
   * The cached value of the '{@link #getConstraint() <em>Constraint</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstraint()
   * @generated
   * @ordered
   */
  protected XSDConstraint constraint = CONSTRAINT_EDEFAULT;

  /**
   * The flag representing whether the Constraint attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int CONSTRAINT_ESETFLAG = 1 << 8;

  /**
   * The default value of the '{@link #getForm() <em>Form</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getForm()
   * @generated
   * @ordered
   */
  protected static final XSDForm FORM_EDEFAULT = XSDForm.QUALIFIED_LITERAL;

  /**
   * The cached value of the '{@link #getForm() <em>Form</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getForm()
   * @generated
   * @ordered
   */
  protected XSDForm form = FORM_EDEFAULT;

  /**
   * The flag representing whether the Form attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int FORM_ESETFLAG = 1 << 9;

  /**
   * The default value of the '{@link #getLexicalValue() <em>Lexical Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLexicalValue()
   * @generated
   * @ordered
   */
  protected static final String LEXICAL_VALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getLexicalValue() <em>Lexical Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLexicalValue()
   * @generated
   * @ordered
   */
  protected String lexicalValue = LEXICAL_VALUE_EDEFAULT;

  /**
   * The default value of the '{@link #isGlobal() <em>Global</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isGlobal()
   * @generated
   * @ordered
   */
  protected static final boolean GLOBAL_EDEFAULT = false;

  /**
   * The default value of the '{@link #isFeatureReference() <em>Feature Reference</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isFeatureReference()
   * @generated
   * @ordered
   */
  protected static final boolean FEATURE_REFERENCE_EDEFAULT = false;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDFeatureImpl()
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
    return XSDPackage.eINSTANCE.getXSDFeature();
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
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_FEATURE__VALUE, oldValue, value));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDConstraint getConstraint()
  {
    return constraint;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstraint(XSDConstraint newConstraint)
  {
    XSDConstraint oldConstraint = constraint;
    constraint = newConstraint == null ? CONSTRAINT_EDEFAULT : newConstraint;
    boolean oldConstraintESet = (eFlags & CONSTRAINT_ESETFLAG) != 0;
    eFlags |= CONSTRAINT_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_FEATURE__CONSTRAINT, oldConstraint, constraint, !oldConstraintESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetConstraint()
  {
    XSDConstraint oldConstraint = constraint;
    boolean oldConstraintESet = (eFlags & CONSTRAINT_ESETFLAG) != 0;
    constraint = CONSTRAINT_EDEFAULT;
    eFlags &= ~CONSTRAINT_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, XSDPackage.XSD_FEATURE__CONSTRAINT, oldConstraint, CONSTRAINT_EDEFAULT, oldConstraintESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetConstraint()
  {
    return (eFlags & CONSTRAINT_ESETFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDForm getForm()
  {
    return form;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setForm(XSDForm newForm)
  {
    XSDForm oldForm = form;
    form = newForm == null ? FORM_EDEFAULT : newForm;
    boolean oldFormESet = (eFlags & FORM_ESETFLAG) != 0;
    eFlags |= FORM_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_FEATURE__FORM, oldForm, form, !oldFormESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetForm()
  {
    XSDForm oldForm = form;
    boolean oldFormESet = (eFlags & FORM_ESETFLAG) != 0;
    form = FORM_EDEFAULT;
    eFlags &= ~FORM_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, XSDPackage.XSD_FEATURE__FORM, oldForm, FORM_EDEFAULT, oldFormESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetForm()
  {
    return (eFlags & FORM_ESETFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLexicalValue()
  {
    return lexicalValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLexicalValue(String newLexicalValue)
  {
    String oldLexicalValue = lexicalValue;
    lexicalValue = newLexicalValue;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_FEATURE__LEXICAL_VALUE, oldLexicalValue, lexicalValue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public Boolean getGlobal() 
  {
    return isGlobal() ? Boolean.TRUE : Boolean.FALSE;
  }

  protected boolean analyze()
  {
    super.analyze();
    Object newValue = null;
    if (!isFeatureReference())
    {
      XSDSimpleTypeDefinition xsdSimpleTypeDefinition = getType().getSimpleType();
      if (xsdSimpleTypeDefinition != null)
      {
        String theLexicalValue = getLexicalValue();
        if (theLexicalValue != null)
        {
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
      }
    }
  
    if (newValue == null ? getValue() != null : !newValue.equals(getValue()))
    {
      setValue(newValue);
    }

    return true;
  }

  protected boolean isUpdatingDOM()
  {
    return 
      super.isUpdatingDOM() ||
        getContainer() instanceof XSDConcreteComponentImpl &&
          ((XSDConcreteComponentImpl)getContainer()).isUpdatingDOM();
  }

  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    if (changedElement == getElement())
    {
      if (XSDConstants.nodeType(changedElement) != XSDConstants.ATTRIBUTE_ELEMENT ||
            changedElement.getParentNode() == null || 
               XSDConstants.nodeType(changedElement.getParentNode()) == XSDConstants.SCHEMA_ELEMENT)
      {
        if (changedElement.hasAttributeNS(null, XSDConstants.FIXED_ATTRIBUTE))
        {
          String newLexicalValue = changedElement.getAttributeNS(null, XSDConstants.FIXED_ATTRIBUTE);
          if (newLexicalValue == null || !newLexicalValue.equals(getLexicalValue()))
          {
            setLexicalValue(newLexicalValue);
          }
          if (!isSetConstraint() || XSDConstraint.FIXED_LITERAL != getConstraint())
          {
            setConstraint(XSDConstraint.FIXED_LITERAL);
          }
        }
        else if (changedElement.hasAttributeNS(null, XSDConstants.DEFAULT_ATTRIBUTE))
        {
          String newLexicalValue = changedElement.getAttributeNS(null, XSDConstants.DEFAULT_ATTRIBUTE);
          if (newLexicalValue == null || !newLexicalValue.equals(getLexicalValue()))
          {
            setLexicalValue(newLexicalValue);
          }
          if (!isSetConstraint() || XSDConstraint.DEFAULT_LITERAL != getConstraint())
          {
            setConstraint(XSDConstraint.DEFAULT_LITERAL);
          }
        }
        else if (getLexicalValue() != null)
        {
          unsetConstraint();
          setLexicalValue(null);
        }
      }
    }
  }

  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    if (!isFeatureReference() && !isReconciling)
    {
      if (eAttribute == null || 
            eAttribute == XSDPackage.eINSTANCE.getXSDFeature_LexicalValue() || 
            eAttribute == XSDPackage.eINSTANCE.getXSDFeature_Constraint())
      {
        Element theElement = getElement();
        if (theElement != null)
        {
          if (getLexicalValue() != null)
          {
            switch (getConstraint().getValue())
            {
              case XSDConstraint.FIXED:
              {
                niceSetAttribute(theElement, XSDConstants.FIXED_ATTRIBUTE, getLexicalValue());
                if (theElement.hasAttributeNS(null, XSDConstants.DEFAULT_ATTRIBUTE))
                {
                  niceSetAttribute(theElement, XSDConstants.DEFAULT_ATTRIBUTE, null);
                }
                break;
              }
              case XSDConstraint.DEFAULT:
              {
                niceSetAttribute(theElement, XSDConstants.DEFAULT_ATTRIBUTE, getLexicalValue());
                if (theElement.hasAttributeNS(null, XSDConstants.FIXED_ATTRIBUTE))
                {
                  niceSetAttribute(theElement, XSDConstants.FIXED_ATTRIBUTE, null);
                }
                break;
              }
            }
          }
          else if (!(getContainer() instanceof XSDAttributeUse))
          {
            if (theElement.hasAttributeNS(null, XSDConstants.FIXED_ATTRIBUTE))
            {
              niceSetAttribute(theElement, XSDConstants.FIXED_ATTRIBUTE, null);
            }
            if (theElement.hasAttributeNS(null, XSDConstants.DEFAULT_ATTRIBUTE))
            {
              niceSetAttribute(theElement, XSDConstants.DEFAULT_ATTRIBUTE, null);
            }
          }
        }
      }
    }
  }

  public XSDScope getScope()
  {
    for (XSDConcreteComponent container = getContainer(); container != null; container = container.getContainer())
    {
      if (container instanceof XSDScope)
      {
        return (XSDScope)container;
      }
      else if (container instanceof XSDModelGroupDefinition || container instanceof XSDAttributeGroupDefinition)
      {
        return null;
      }
    }

    return null;
  }

  public boolean isFeatureReference()
  {
    return isNamedComponentReference();
  }

  public XSDFeature getResolvedFeature()
  {
    return (XSDFeature)getResolvedNamedComponent();
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
    result.append(", constraint: ");
    if ((eFlags & CONSTRAINT_ESETFLAG) != 0) result.append(constraint); else result.append("<unset>");
    result.append(", form: ");
    if ((eFlags & FORM_ESETFLAG) != 0) result.append(form); else result.append("<unset>");
    result.append(", lexicalValue: ");
    result.append(lexicalValue);
    result.append(')');
    return result.toString();
  }

  public boolean isGlobal()
  {
    for (XSDConcreteComponent container = getContainer(); container != null; container = container.getContainer())
    {
      if (container instanceof XSDScope)
      {
        return container instanceof XSDSchema;
      }
      else if (container instanceof XSDModelGroupDefinition || container instanceof XSDAttributeGroupDefinition)
      {
        return false;
      }
    }

    return true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public Boolean getFeatureReference() 
  {
    return isFeatureReference() ? Boolean.TRUE : Boolean.FALSE;
  }

  public XSDTypeDefinition getType()
  {
    return null;
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
        case XSDPackage.XSD_FEATURE__DIAGNOSTICS:
          return ((InternalEList)getDiagnostics()).basicRemove(otherEnd, msgs);
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
      case XSDPackage.XSD_FEATURE__ELEMENT:
        return getElement();
      case XSDPackage.XSD_FEATURE__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_FEATURE__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_FEATURE__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_FEATURE__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_FEATURE__NAME:
        return getName();
      case XSDPackage.XSD_FEATURE__TARGET_NAMESPACE:
        return getTargetNamespace();
      case XSDPackage.XSD_FEATURE__ALIAS_NAME:
        return getAliasName();
      case XSDPackage.XSD_FEATURE__URI:
        return getURI();
      case XSDPackage.XSD_FEATURE__ALIAS_URI:
        return getAliasURI();
      case XSDPackage.XSD_FEATURE__QNAME:
        return getQName();
      case XSDPackage.XSD_FEATURE__VALUE:
        return getValue();
      case XSDPackage.XSD_FEATURE__CONSTRAINT:
        return getConstraint();
      case XSDPackage.XSD_FEATURE__FORM:
        return getForm();
      case XSDPackage.XSD_FEATURE__LEXICAL_VALUE:
        return getLexicalValue();
      case XSDPackage.XSD_FEATURE__GLOBAL:
        return isGlobal() ? Boolean.TRUE : Boolean.FALSE;
      case XSDPackage.XSD_FEATURE__FEATURE_REFERENCE:
        return isFeatureReference() ? Boolean.TRUE : Boolean.FALSE;
      case XSDPackage.XSD_FEATURE__SCOPE:
        return getScope();
      case XSDPackage.XSD_FEATURE__RESOLVED_FEATURE:
        return getResolvedFeature();
      case XSDPackage.XSD_FEATURE__TYPE:
        return getType();
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
      case XSDPackage.XSD_FEATURE__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_FEATURE__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_FEATURE__NAME:
        setName((String)newValue);
        return;
      case XSDPackage.XSD_FEATURE__TARGET_NAMESPACE:
        setTargetNamespace((String)newValue);
        return;
      case XSDPackage.XSD_FEATURE__VALUE:
        setValue((Object)newValue);
        return;
      case XSDPackage.XSD_FEATURE__CONSTRAINT:
        setConstraint((XSDConstraint)newValue);
        return;
      case XSDPackage.XSD_FEATURE__FORM:
        setForm((XSDForm)newValue);
        return;
      case XSDPackage.XSD_FEATURE__LEXICAL_VALUE:
        setLexicalValue((String)newValue);
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
      case XSDPackage.XSD_FEATURE__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_FEATURE__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_FEATURE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case XSDPackage.XSD_FEATURE__TARGET_NAMESPACE:
        setTargetNamespace(TARGET_NAMESPACE_EDEFAULT);
        return;
      case XSDPackage.XSD_FEATURE__VALUE:
        setValue(VALUE_EDEFAULT);
        return;
      case XSDPackage.XSD_FEATURE__CONSTRAINT:
        unsetConstraint();
        return;
      case XSDPackage.XSD_FEATURE__FORM:
        unsetForm();
        return;
      case XSDPackage.XSD_FEATURE__LEXICAL_VALUE:
        setLexicalValue(LEXICAL_VALUE_EDEFAULT);
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
      case XSDPackage.XSD_FEATURE__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_FEATURE__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_FEATURE__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_FEATURE__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_FEATURE__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_FEATURE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case XSDPackage.XSD_FEATURE__TARGET_NAMESPACE:
        return TARGET_NAMESPACE_EDEFAULT == null ? targetNamespace != null : !TARGET_NAMESPACE_EDEFAULT.equals(targetNamespace);
      case XSDPackage.XSD_FEATURE__ALIAS_NAME:
        return ALIAS_NAME_EDEFAULT == null ? getAliasName() != null : !ALIAS_NAME_EDEFAULT.equals(getAliasName());
      case XSDPackage.XSD_FEATURE__URI:
        return URI_EDEFAULT == null ? getURI() != null : !URI_EDEFAULT.equals(getURI());
      case XSDPackage.XSD_FEATURE__ALIAS_URI:
        return ALIAS_URI_EDEFAULT == null ? getAliasURI() != null : !ALIAS_URI_EDEFAULT.equals(getAliasURI());
      case XSDPackage.XSD_FEATURE__QNAME:
        return QNAME_EDEFAULT == null ? getQName() != null : !QNAME_EDEFAULT.equals(getQName());
      case XSDPackage.XSD_FEATURE__VALUE:
        return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
      case XSDPackage.XSD_FEATURE__CONSTRAINT:
        return isSetConstraint();
      case XSDPackage.XSD_FEATURE__FORM:
        return isSetForm();
      case XSDPackage.XSD_FEATURE__LEXICAL_VALUE:
        return LEXICAL_VALUE_EDEFAULT == null ? lexicalValue != null : !LEXICAL_VALUE_EDEFAULT.equals(lexicalValue);
      case XSDPackage.XSD_FEATURE__GLOBAL:
        return isGlobal() != GLOBAL_EDEFAULT;
      case XSDPackage.XSD_FEATURE__FEATURE_REFERENCE:
        return isFeatureReference() != FEATURE_REFERENCE_EDEFAULT;
      case XSDPackage.XSD_FEATURE__SCOPE:
        return getScope() != null;
      case XSDPackage.XSD_FEATURE__RESOLVED_FEATURE:
        return getResolvedFeature() != null;
      case XSDPackage.XSD_FEATURE__TYPE:
        return getType() != null;
    }
    return eDynamicIsSet(eFeature);
  }

} 
