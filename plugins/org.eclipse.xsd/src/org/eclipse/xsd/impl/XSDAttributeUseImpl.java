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
 * $Id: XSDAttributeUseImpl.java,v 1.5 2005/11/23 18:09:40 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.text.Collator;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDAttributeUseCategory;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDConstraint;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute Use</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeUseImpl#isRequired <em>Required</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeUseImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeUseImpl#getConstraint <em>Constraint</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeUseImpl#getUse <em>Use</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeUseImpl#getLexicalValue <em>Lexical Value</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeUseImpl#getAttributeDeclaration <em>Attribute Declaration</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDAttributeUseImpl#getContent <em>Content</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDAttributeUseImpl 
  extends XSDComponentImpl 
  implements XSDAttributeUse 
{
  /**
   * The default value of the '{@link #isRequired() <em>Required</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isRequired()
   * @generated
   * @ordered
   */
  protected static final boolean REQUIRED_EDEFAULT = false;

  /**
   * The flag representing the value of the '{@link #isRequired() <em>Required</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isRequired()
   * @generated
   * @ordered
   */
  protected static final int REQUIRED_EFLAG = 1 << 8;

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
  protected static final int CONSTRAINT_ESETFLAG = 1 << 9;

  /**
   * The default value of the '{@link #getUse() <em>Use</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUse()
   * @generated
   * @ordered
   */
  protected static final XSDAttributeUseCategory USE_EDEFAULT = XSDAttributeUseCategory.OPTIONAL_LITERAL;

  /**
   * The cached value of the '{@link #getUse() <em>Use</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUse()
   * @generated
   * @ordered
   */
  protected XSDAttributeUseCategory use = USE_EDEFAULT;

  /**
   * The flag representing whether the Use attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int USE_ESETFLAG = 1 << 10;

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
   * The cached value of the '{@link #getAttributeDeclaration() <em>Attribute Declaration</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttributeDeclaration()
   * @generated
   * @ordered
   */
  protected XSDAttributeDeclaration attributeDeclaration = null;

  /**
   * The cached value of the '{@link #getContent() <em>Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContent()
   * @generated
   * @ordered
   */
  protected XSDAttributeDeclaration content = null;

  public static XSDAttributeUse createAttributeUse(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.ATTRIBUTE_ELEMENT)
    {
      XSDAttributeUse xsdAttributeUse = XSDFactory.eINSTANCE.createXSDAttributeUse();
      xsdAttributeUse.setElement((Element)node);

      XSDAttributeDeclaration xsdAttributeDeclaration = XSDAttributeDeclarationImpl.createAttributeDeclaration(node);
      xsdAttributeUse.setContent(xsdAttributeDeclaration);
      return xsdAttributeUse;
    }

    return null;
  }

  public static List sortAttributeUses(Collection xsdAttributeUses)
  {
    Object [] objects = xsdAttributeUses.toArray();
    Arrays.sort
      (objects,
       new Comparator()
       {
         public boolean equals(Object that)
         {
           return this == that;
         }
         public int compare(Object o1, Object o2)
         {
           String name1 = ((XSDAttributeUse)o1).getContent().getResolvedAttributeDeclaration().getName();
           String name2 = ((XSDAttributeUse)o2).getContent().getResolvedAttributeDeclaration().getName();
           if (name1 == null && name2 == null)
           {
             return 0;
           }
           else if (name1 == null)
           {
             return 1;
           }
           else if (name2 == null)
           {
             return -1;
           }
           return
             Collator.getInstance().compare(name1, name2);
         }
       });

    return Arrays.asList(objects);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDAttributeUseImpl()
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
    return XSDPackage.Literals.XSD_ATTRIBUTE_USE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isRequired()
  {
    return (eFlags & REQUIRED_EFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRequired(boolean newRequired)
  {
    boolean oldRequired = (eFlags & REQUIRED_EFLAG) != 0;
    if (newRequired) eFlags |= REQUIRED_EFLAG; else eFlags &= ~REQUIRED_EFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_USE__REQUIRED, oldRequired, newRequired));
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
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_USE__VALUE, oldValue, value));
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
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_USE__CONSTRAINT, oldConstraint, constraint, !oldConstraintESet));
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
      eNotify(new ENotificationImpl(this, Notification.UNSET, XSDPackage.XSD_ATTRIBUTE_USE__CONSTRAINT, oldConstraint, CONSTRAINT_EDEFAULT, oldConstraintESet));
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
  public XSDAttributeUseCategory getUse()
  {
    return use;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUse(XSDAttributeUseCategory newUse)
  {
    XSDAttributeUseCategory oldUse = use;
    use = newUse == null ? USE_EDEFAULT : newUse;
    boolean oldUseESet = (eFlags & USE_ESETFLAG) != 0;
    eFlags |= USE_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_USE__USE, oldUse, use, !oldUseESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetUse()
  {
    XSDAttributeUseCategory oldUse = use;
    boolean oldUseESet = (eFlags & USE_ESETFLAG) != 0;
    use = USE_EDEFAULT;
    eFlags &= ~USE_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, XSDPackage.XSD_ATTRIBUTE_USE__USE, oldUse, USE_EDEFAULT, oldUseESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetUse()
  {
    return (eFlags & USE_ESETFLAG) != 0;
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
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_USE__LEXICAL_VALUE, oldLexicalValue, lexicalValue));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDAttributeDeclaration getAttributeDeclaration()
  {
    return attributeDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAttributeDeclaration(XSDAttributeDeclaration newAttributeDeclaration)
  {
    XSDAttributeDeclaration oldAttributeDeclaration = attributeDeclaration;
    attributeDeclaration = newAttributeDeclaration;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_USE__ATTRIBUTE_DECLARATION, oldAttributeDeclaration, attributeDeclaration));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDAttributeDeclaration getContent()
  {
    return content;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContent(XSDAttributeDeclaration newContent)
  {
    if (newContent != content)
    {
      NotificationChain msgs = null;
      if (content != null)
        msgs = ((InternalEObject)content).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_ATTRIBUTE_USE__CONTENT, null, msgs);
      if (newContent != null)
        msgs = ((InternalEObject)newContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_ATTRIBUTE_USE__CONTENT, null, msgs);
      msgs = basicSetContent(newContent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_USE__CONTENT, newContent, newContent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetContent(XSDAttributeDeclaration newContent, NotificationChain msgs)
  {
    XSDAttributeDeclaration oldContent = content;
    content = newContent;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_ATTRIBUTE_USE__CONTENT, oldContent, newContent);
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
        case XSDPackage.XSD_ATTRIBUTE_USE__DIAGNOSTICS:
          return ((InternalEList)getDiagnostics()).basicRemove(otherEnd, msgs);
        case XSDPackage.XSD_ATTRIBUTE_USE__CONTENT:
          return basicSetContent(null, msgs);
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
      case XSDPackage.XSD_ATTRIBUTE_USE__ELEMENT:
        return getElement();
      case XSDPackage.XSD_ATTRIBUTE_USE__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_ATTRIBUTE_USE__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_ATTRIBUTE_USE__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_ATTRIBUTE_USE__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_ATTRIBUTE_USE__REQUIRED:
        return isRequired() ? Boolean.TRUE : Boolean.FALSE;
      case XSDPackage.XSD_ATTRIBUTE_USE__VALUE:
        return getValue();
      case XSDPackage.XSD_ATTRIBUTE_USE__CONSTRAINT:
        return getConstraint();
      case XSDPackage.XSD_ATTRIBUTE_USE__USE:
        return getUse();
      case XSDPackage.XSD_ATTRIBUTE_USE__LEXICAL_VALUE:
        return getLexicalValue();
      case XSDPackage.XSD_ATTRIBUTE_USE__ATTRIBUTE_DECLARATION:
        return getAttributeDeclaration();
      case XSDPackage.XSD_ATTRIBUTE_USE__CONTENT:
        return getContent();
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
      case XSDPackage.XSD_ATTRIBUTE_USE__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_ATTRIBUTE_USE__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_ATTRIBUTE_USE__REQUIRED:
        setRequired(((Boolean)newValue).booleanValue());
        return;
      case XSDPackage.XSD_ATTRIBUTE_USE__VALUE:
        setValue((Object)newValue);
        return;
      case XSDPackage.XSD_ATTRIBUTE_USE__CONSTRAINT:
        setConstraint((XSDConstraint)newValue);
        return;
      case XSDPackage.XSD_ATTRIBUTE_USE__USE:
        setUse((XSDAttributeUseCategory)newValue);
        return;
      case XSDPackage.XSD_ATTRIBUTE_USE__LEXICAL_VALUE:
        setLexicalValue((String)newValue);
        return;
      case XSDPackage.XSD_ATTRIBUTE_USE__ATTRIBUTE_DECLARATION:
        setAttributeDeclaration((XSDAttributeDeclaration)newValue);
        return;
      case XSDPackage.XSD_ATTRIBUTE_USE__CONTENT:
        setContent((XSDAttributeDeclaration)newValue);
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
      case XSDPackage.XSD_ATTRIBUTE_USE__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_ATTRIBUTE_USE__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_ATTRIBUTE_USE__REQUIRED:
        setRequired(REQUIRED_EDEFAULT);
        return;
      case XSDPackage.XSD_ATTRIBUTE_USE__VALUE:
        setValue(VALUE_EDEFAULT);
        return;
      case XSDPackage.XSD_ATTRIBUTE_USE__CONSTRAINT:
        unsetConstraint();
        return;
      case XSDPackage.XSD_ATTRIBUTE_USE__USE:
        unsetUse();
        return;
      case XSDPackage.XSD_ATTRIBUTE_USE__LEXICAL_VALUE:
        setLexicalValue(LEXICAL_VALUE_EDEFAULT);
        return;
      case XSDPackage.XSD_ATTRIBUTE_USE__ATTRIBUTE_DECLARATION:
        setAttributeDeclaration((XSDAttributeDeclaration)null);
        return;
      case XSDPackage.XSD_ATTRIBUTE_USE__CONTENT:
        setContent((XSDAttributeDeclaration)null);
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
      case XSDPackage.XSD_ATTRIBUTE_USE__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_ATTRIBUTE_USE__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_ATTRIBUTE_USE__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_ATTRIBUTE_USE__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_ATTRIBUTE_USE__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_ATTRIBUTE_USE__REQUIRED:
        return ((eFlags & REQUIRED_EFLAG) != 0) != REQUIRED_EDEFAULT;
      case XSDPackage.XSD_ATTRIBUTE_USE__VALUE:
        return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
      case XSDPackage.XSD_ATTRIBUTE_USE__CONSTRAINT:
        return isSetConstraint();
      case XSDPackage.XSD_ATTRIBUTE_USE__USE:
        return isSetUse();
      case XSDPackage.XSD_ATTRIBUTE_USE__LEXICAL_VALUE:
        return LEXICAL_VALUE_EDEFAULT == null ? lexicalValue != null : !LEXICAL_VALUE_EDEFAULT.equals(lexicalValue);
      case XSDPackage.XSD_ATTRIBUTE_USE__ATTRIBUTE_DECLARATION:
        return attributeDeclaration != null;
      case XSDPackage.XSD_ATTRIBUTE_USE__CONTENT:
        return content != null;
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
    result.append(" (required: ");
    result.append((eFlags & REQUIRED_EFLAG) != 0);
    result.append(", value: ");
    result.append(value);
    result.append(", constraint: ");
    if ((eFlags & CONSTRAINT_ESETFLAG) != 0) result.append(constraint); else result.append("<unset>");
    result.append(", use: ");
    if ((eFlags & USE_ESETFLAG) != 0) result.append(use); else result.append("<unset>");
    result.append(", lexicalValue: ");
    result.append(lexicalValue);
    result.append(')');
    return result.toString();
  }

  public Element createElement()
  {
    Element newElement = null;
    XSDAttributeDeclaration theContent = getContent();
    if (theContent != null)
    {
      newElement = ((XSDConcreteComponentImpl)theContent).createElement();
      setElement(newElement);
    }
    return newElement;
  }

  protected void patch()
  {
    super.patch();
    patchHelper();
  }

  protected void patchHelper()
  {
    XSDAttributeDeclaration newAttributeDeclaration = getContent();
    if (newAttributeDeclaration != null)
    {
      newAttributeDeclaration = newAttributeDeclaration.getResolvedAttributeDeclaration();
    }

    if (newAttributeDeclaration != getAttributeDeclaration())
    {
      setAttributeDeclaration(newAttributeDeclaration);
    }
  }

  protected boolean analyze()
  {
    super.analyze();
    Object newValue = null;
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = getAttributeDeclaration().getTypeDefinition();
    if (xsdSimpleTypeDefinition != null)
    {
      String theLexicalValue = getLexicalValue();
      if (theLexicalValue != null)
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

  public void validate()
  {
    super.validate();

    if (getLexicalValue() != null)
    {
      XSDSimpleTypeDefinition theTypeDefinition = getAttributeDeclaration().getTypeDefinition();
      if (theTypeDefinition != null)
      {
        if (XSDConstants.isOrIsDerivedFromID(theTypeDefinition))
        {
          createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "coss-attribute.3");
        }
        else
        {
          checkSimpleTypeConstraint
            (theTypeDefinition,
             getLexicalValue(),
             XSDConstants.PART1,
             "element-attribute",
             getElement(),
             getConstraint() == XSDConstraint.FIXED_LITERAL ? XSDConstants.FIXED_ATTRIBUTE : XSDConstants.DEFAULT_ATTRIBUTE,
             false);
        }
      }

      if (getAttributeDeclaration().getConstraint() == XSDConstraint.FIXED_LITERAL &&
            getAttributeDeclaration().getLexicalValue() != null &&
            (getConstraint() != XSDConstraint.FIXED_LITERAL || 
               !theTypeDefinition.equalLiterals(getAttributeDeclaration().getLexicalValue(), getLexicalValue())))
      {
        createDiagnostic
          (XSDDiagnosticSeverity.ERROR_LITERAL, "coss-attruse.2", getLexicalValue(), getAttributeDeclaration().getLexicalValue());
      }

      if (getConstraint() == XSDConstraint.DEFAULT_LITERAL && 
            isSetUse() && getUse() != XSDAttributeUseCategory.OPTIONAL_LITERAL)
      {
        createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "src-attribute.2");
      }
    }
  }

  protected Collection getContentNodes(Element changedElement)
  {
    return Collections.singleton(getElement());
  }

  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    if (changedElement == getElement())
    {
      XSDAttributeUseCategory newUse = null;
      if (getElement().hasAttributeNS(null, XSDConstants.USE_ATTRIBUTE))
      {
        newUse = XSDAttributeUseCategory.get(getElement().getAttributeNS(null, XSDConstants.USE_ATTRIBUTE));
      }
  
      boolean newRequired = false;
      XSDConstraint newConstraint = null;
      String newLexicalValue = null;

      if (newUse != XSDAttributeUseCategory.PROHIBITED_LITERAL)
      {
        newRequired = newUse == XSDAttributeUseCategory.REQUIRED_LITERAL;
        if (getElement().hasAttributeNS(null, XSDConstants.FIXED_ATTRIBUTE))
        {
          newLexicalValue = getElement().getAttributeNS(null, XSDConstants.FIXED_ATTRIBUTE);
          if (newLexicalValue != null)
          {
            newConstraint = XSDConstraint.FIXED_LITERAL;
          }
        }
        else if (getElement().hasAttributeNS(null, XSDConstants.DEFAULT_ATTRIBUTE))
        {
          newLexicalValue = getElement().getAttributeNS(null, XSDConstants.DEFAULT_ATTRIBUTE);
          if (newLexicalValue != null)
          {
            newConstraint = XSDConstraint.DEFAULT_LITERAL;
          }
        }
      }

      if (newUse == null)
      {
        if (isSetUse())
        {
          unsetUse();
        }
      }
      else if (!isSetUse() || newUse != getUse())
      {
        setUse(newUse);
      }

      if (newRequired != isRequired())
      {
        setRequired(newRequired);
      }

      if (newConstraint == null)
      {
        if (isSetConstraint())
        {
          unsetConstraint();
        }
      }
      else if (!isSetConstraint() || newConstraint != getConstraint())
      {
        setConstraint(newConstraint);
      }

      if (newLexicalValue == null ? getLexicalValue() != null : !newLexicalValue.equals(getLexicalValue()))
      {
        setLexicalValue(newLexicalValue);
      }
  
      XSDAttributeDeclaration theContent = getContent();
      if (theContent != null)
      {
        theContent.elementAttributesChanged(changedElement);
      }
    }
  }

  protected void reconcileContents(Element changedElement) 
  {
    super.reconcileContents(changedElement);
    if (changedElement == getElement())
    {
      XSDAttributeDeclaration theContent = getContent();
      if (theContent != null)
      {
        theContent.elementContentsChanged(changedElement);
        XSDAttributeDeclaration newAttributeDeclaration = theContent.getResolvedAttributeDeclaration();
        if (newAttributeDeclaration != getAttributeDeclaration())
        {
          setAttributeDeclaration(newAttributeDeclaration);
        }
      }
    }
  }

  protected void changeAttribute(EAttribute eAttribute)
  {
    if (isReconciling)
    {
      return;
    }

    super.changeAttribute(eAttribute);
    Element theElement = getElement();
    if (theElement != null)
    {
      if (eAttribute == null || eAttribute == XSDPackage.eINSTANCE.getXSDAttributeUse_Use())
      {
        niceSetAttribute(theElement, XSDConstants.USE_ATTRIBUTE, isSetUse() ? getUse().getName() : null);
      }

      if (eAttribute == null || 
            eAttribute == XSDPackage.eINSTANCE.getXSDFeature_LexicalValue() || 
            eAttribute == XSDPackage.eINSTANCE.getXSDAttributeUse_Constraint())
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
        else
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

  protected void adoptContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.adoptContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.eINSTANCE.getXSDAttributeUse_Content())
    {
      patchHelper();
      traverseToRootForPatching();
    }
  }

  protected void orphanContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.orphanContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.eINSTANCE.getXSDAttributeUse_Content())
    {
      patchHelper();
      traverseToRootForPatching();
    }
  }

  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDAttributeUseImpl clonedAttributeUse =
      (XSDAttributeUseImpl)getXSDFactory().createXSDAttributeUse();
    clonedAttributeUse.isReconciling = true;

    if (isSetUse())
    {
      clonedAttributeUse.setUse(getUse());
    }

    clonedAttributeUse.setRequired(isRequired());

    if (isSetConstraint())
    {
      clonedAttributeUse.setConstraint(getConstraint());
    }
    if (getLexicalValue() != null)
    {
      clonedAttributeUse.setLexicalValue(getLexicalValue());
    }

    clonedAttributeUse.setContent((XSDAttributeDeclaration)getContent().cloneConcreteComponent(deep, shareDOM));

    if (shareDOM && getElement() != null)
    {
      clonedAttributeUse.setElement(getElement());
    }

    clonedAttributeUse.isReconciling = shareDOM;
    return clonedAttributeUse;
  }
}
