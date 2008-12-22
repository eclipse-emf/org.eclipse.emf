/**
 * <copyright>
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: XSDFeatureImpl.java,v 1.16 2008/12/22 14:25:48 emerks Exp $
 */
package org.eclipse.xsd.impl;


import org.w3c.dom.Element;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

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
   * The offset of the flags representing the value of the '{@link #getConstraint() <em>Constraint</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int CONSTRAINT_EFLAG_OFFSET = 8;

  /**
   * The flags representing the default value of the '{@link #getConstraint() <em>Constraint</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int CONSTRAINT_EFLAG_DEFAULT = CONSTRAINT_EDEFAULT.ordinal() << CONSTRAINT_EFLAG_OFFSET;

  /**
   * The array of enumeration values for '{@link XSDConstraint Constraint}'
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  private static final XSDConstraint[] CONSTRAINT_EFLAG_VALUES = XSDConstraint.values();

  /**
   * The flag representing the value of the '{@link #getConstraint() <em>Constraint</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstraint()
   * @generated
   * @ordered
   */
  protected static final int CONSTRAINT_EFLAG = 1 << CONSTRAINT_EFLAG_OFFSET;

  /**
   * The flag representing whether the Constraint attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int CONSTRAINT_ESETFLAG = 1 << 9;

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
   * The offset of the flags representing the value of the '{@link #getForm() <em>Form</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int FORM_EFLAG_OFFSET = 10;

  /**
   * The flags representing the default value of the '{@link #getForm() <em>Form</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int FORM_EFLAG_DEFAULT = FORM_EDEFAULT.ordinal() << FORM_EFLAG_OFFSET;

  /**
   * The array of enumeration values for '{@link XSDForm Form}'
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  private static final XSDForm[] FORM_EFLAG_VALUES = XSDForm.values();

  /**
   * The flag representing the value of the '{@link #getForm() <em>Form</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getForm()
   * @generated
   * @ordered
   */
  protected static final int FORM_EFLAG = 1 << FORM_EFLAG_OFFSET;

  /**
   * The flag representing whether the Form attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int FORM_ESETFLAG = 1 << 11;

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
  @Override
  protected EClass eStaticClass()
  {
    return XSDPackage.Literals.XSD_FEATURE;
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
    return CONSTRAINT_EFLAG_VALUES[(eFlags & CONSTRAINT_EFLAG) >>> CONSTRAINT_EFLAG_OFFSET];
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstraint(XSDConstraint newConstraint)
  {
    XSDConstraint oldConstraint = CONSTRAINT_EFLAG_VALUES[(eFlags & CONSTRAINT_EFLAG) >>> CONSTRAINT_EFLAG_OFFSET];
    if (newConstraint == null) newConstraint = CONSTRAINT_EDEFAULT;
    eFlags = eFlags & ~CONSTRAINT_EFLAG | newConstraint.ordinal() << CONSTRAINT_EFLAG_OFFSET;
    boolean oldConstraintESet = (eFlags & CONSTRAINT_ESETFLAG) != 0;
    eFlags |= CONSTRAINT_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_FEATURE__CONSTRAINT, oldConstraint, newConstraint, !oldConstraintESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetConstraint()
  {
    XSDConstraint oldConstraint = CONSTRAINT_EFLAG_VALUES[(eFlags & CONSTRAINT_EFLAG) >>> CONSTRAINT_EFLAG_OFFSET];
    boolean oldConstraintESet = (eFlags & CONSTRAINT_ESETFLAG) != 0;
    eFlags = eFlags & ~CONSTRAINT_EFLAG | CONSTRAINT_EFLAG_DEFAULT;
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
    return FORM_EFLAG_VALUES[(eFlags & FORM_EFLAG) >>> FORM_EFLAG_OFFSET];
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setForm(XSDForm newForm)
  {
    XSDForm oldForm = FORM_EFLAG_VALUES[(eFlags & FORM_EFLAG) >>> FORM_EFLAG_OFFSET];
    if (newForm == null) newForm = FORM_EDEFAULT;
    eFlags = eFlags & ~FORM_EFLAG | newForm.ordinal() << FORM_EFLAG_OFFSET;
    boolean oldFormESet = (eFlags & FORM_ESETFLAG) != 0;
    eFlags |= FORM_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_FEATURE__FORM, oldForm, newForm, !oldFormESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetForm()
  {
    XSDForm oldForm = FORM_EFLAG_VALUES[(eFlags & FORM_EFLAG) >>> FORM_EFLAG_OFFSET];
    boolean oldFormESet = (eFlags & FORM_ESETFLAG) != 0;
    eFlags = eFlags & ~FORM_EFLAG | FORM_EFLAG_DEFAULT;
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

  @Override
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
    }
  
    if (newValue == null ? getValue() != null : !newValue.equals(getValue()))
    {
      setValue(newValue);
    }

    return true;
  }

  @Override
  protected boolean isUpdatingDOM()
  {
    return 
      super.isUpdatingDOM() ||
        getContainer() instanceof XSDConcreteComponentImpl &&
          ((XSDConcreteComponentImpl)getContainer()).isUpdatingDOM();
  }

  @Override
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

  @Override
  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    if (!isFeatureReference() && !isReconciling)
    {
      if (eAttribute == null || 
            eAttribute == XSDPackage.Literals.XSD_FEATURE__LEXICAL_VALUE || 
            eAttribute == XSDPackage.Literals.XSD_FEATURE__CONSTRAINT)
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
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (value: ");
    result.append(value);
    result.append(", constraint: ");
    if ((eFlags & CONSTRAINT_ESETFLAG) != 0) result.append(CONSTRAINT_EFLAG_VALUES[(eFlags & CONSTRAINT_EFLAG) >>> CONSTRAINT_EFLAG_OFFSET]); else result.append("<unset>");
    result.append(", form: ");
    if ((eFlags & FORM_ESETFLAG) != 0) result.append(FORM_EFLAG_VALUES[(eFlags & FORM_EFLAG) >>> FORM_EFLAG_OFFSET]); else result.append("<unset>");
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
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_FEATURE__VALUE:
        return getValue();
      case XSDPackage.XSD_FEATURE__CONSTRAINT:
        return getConstraint();
      case XSDPackage.XSD_FEATURE__FORM:
        return getForm();
      case XSDPackage.XSD_FEATURE__LEXICAL_VALUE:
        return getLexicalValue();
      case XSDPackage.XSD_FEATURE__GLOBAL:
        return isGlobal();
      case XSDPackage.XSD_FEATURE__FEATURE_REFERENCE:
        return isFeatureReference();
      case XSDPackage.XSD_FEATURE__SCOPE:
        return getScope();
      case XSDPackage.XSD_FEATURE__RESOLVED_FEATURE:
        return getResolvedFeature();
      case XSDPackage.XSD_FEATURE__TYPE:
        return getType();
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
      case XSDPackage.XSD_FEATURE__VALUE:
        setValue(newValue);
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
    return super.eIsSet(featureID);
  }

} 
