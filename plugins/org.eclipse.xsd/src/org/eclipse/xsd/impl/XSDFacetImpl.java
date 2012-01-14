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


import java.util.List;

import org.w3c.dom.Element;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDFacet;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Facet</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDFacetImpl#getLexicalValue <em>Lexical Value</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDFacetImpl#getFacetName <em>Facet Name</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDFacetImpl#getEffectiveValue <em>Effective Value</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDFacetImpl#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDFacetImpl#getSimpleTypeDefinition <em>Simple Type Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class XSDFacetImpl 
  extends XSDComponentImpl 
  implements XSDFacet
{
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
   * The default value of the '{@link #getFacetName() <em>Facet Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFacetName()
   * @generated
   * @ordered
   */
  protected static final String FACET_NAME_EDEFAULT = null;

  /**
   * The default value of the '{@link #getEffectiveValue() <em>Effective Value</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEffectiveValue()
   * @generated
   * @ordered
   */
  protected static final Object EFFECTIVE_VALUE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAnnotation() <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotation()
   * @generated
   * @ordered
   */
  protected XSDAnnotation annotation;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDFacetImpl()
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
    return XSDPackage.Literals.XSD_FACET;
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
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_FACET__LEXICAL_VALUE, oldLexicalValue, lexicalValue));
  }

  @Override
  public void validate()
  {
    super.validate();

    Element theElement = getElement();
    if (theElement != null)
    {
      checkComplexContent("annotated", XSDConstants.PART2, "element-" + getFacetName(), theElement);
    }
    validateValue();
  }

  protected void validateValue()
  {
    // Do nothing.
  }

  @Override
  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    if (changedElement.hasAttributeNS(null, XSDConstants.VALUE_ATTRIBUTE))
    {
      String newLexicalValue = changedElement.getAttributeNS(null, XSDConstants.VALUE_ATTRIBUTE);
      if (newLexicalValue == null || !newLexicalValue.equals(getLexicalValue()))
      {
        setLexicalValue(newLexicalValue);
      }
    }
    else if (getLexicalValue() != null)
    {
      setLexicalValue(null);
    }
  }

  @Override
  protected void handleUnreconciledElement(Element child, List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    if (XSDConstants.nodeType(child) == XSDConstants.ANNOTATION_ELEMENT)
    {
      XSDAnnotation xsdAnnotation = XSDAnnotationImpl.createAnnotation(child);
      newContents.add(xsdAnnotation);
    }
  }

  @Override
  protected void handleReconciliation(List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    handleAnnotationReconciliation(XSDPackage.Literals.XSD_FACET__ANNOTATION, newContents, remainingContents);
  }

  @Override
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
      if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_FACET__LEXICAL_VALUE)
      {
        niceSetAttribute(theElement, XSDConstants.VALUE_ATTRIBUTE, getLexicalValue());
      }
    }
  }

  public String getFacetName()
  {
    String name = eClass().getName();
    return Character.toLowerCase(name.charAt(3)) + name.substring(4, name.length() - 5);
  }

  public Object getEffectiveValue()
  {
    return null;
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
        msgs = ((InternalEObject)annotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_FACET__ANNOTATION, null, msgs);
      if (newAnnotation != null)
        msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_FACET__ANNOTATION, null, msgs);
      msgs = basicSetAnnotation(newAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_FACET__ANNOTATION, newAnnotation, newAnnotation));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_FACET__ANNOTATION, oldAnnotation, newAnnotation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public XSDSimpleTypeDefinition getSimpleTypeDefinition() 
  {
    XSDConcreteComponent result = getContainer();
    return result instanceof XSDSimpleTypeDefinition ? (XSDSimpleTypeDefinition)result : null;
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
      case XSDPackage.XSD_FACET__ANNOTATION:
        return basicSetAnnotation(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case XSDPackage.XSD_FACET__LEXICAL_VALUE:
        return getLexicalValue();
      case XSDPackage.XSD_FACET__FACET_NAME:
        return getFacetName();
      case XSDPackage.XSD_FACET__EFFECTIVE_VALUE:
        return getEffectiveValue();
      case XSDPackage.XSD_FACET__ANNOTATION:
        return getAnnotation();
      case XSDPackage.XSD_FACET__SIMPLE_TYPE_DEFINITION:
        return getSimpleTypeDefinition();
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
      case XSDPackage.XSD_FACET__LEXICAL_VALUE:
        setLexicalValue((String)newValue);
        return;
      case XSDPackage.XSD_FACET__ANNOTATION:
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
      case XSDPackage.XSD_FACET__LEXICAL_VALUE:
        setLexicalValue(LEXICAL_VALUE_EDEFAULT);
        return;
      case XSDPackage.XSD_FACET__ANNOTATION:
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
      case XSDPackage.XSD_FACET__LEXICAL_VALUE:
        return LEXICAL_VALUE_EDEFAULT == null ? lexicalValue != null : !LEXICAL_VALUE_EDEFAULT.equals(lexicalValue);
      case XSDPackage.XSD_FACET__FACET_NAME:
        return FACET_NAME_EDEFAULT == null ? getFacetName() != null : !FACET_NAME_EDEFAULT.equals(getFacetName());
      case XSDPackage.XSD_FACET__EFFECTIVE_VALUE:
        return EFFECTIVE_VALUE_EDEFAULT == null ? getEffectiveValue() != null : !EFFECTIVE_VALUE_EDEFAULT.equals(getEffectiveValue());
      case XSDPackage.XSD_FACET__ANNOTATION:
        return annotation != null;
      case XSDPackage.XSD_FACET__SIMPLE_TYPE_DEFINITION:
        return getSimpleTypeDefinition() != null;
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
    result.append(" (lexicalValue: ");
    result.append(lexicalValue);
    result.append(')');
    return result.toString();
  }

}
