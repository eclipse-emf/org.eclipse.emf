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
 * $Id: XSDIdentityConstraintDefinitionImpl.java,v 1.14 2007/07/10 14:33:34 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.Collection;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDIdentityConstraintCategory;
import org.eclipse.xsd.XSDIdentityConstraintDefinition;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDXPathDefinition;
import org.eclipse.xsd.XSDXPathVariety;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Identity Constraint Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDIdentityConstraintDefinitionImpl#getIdentityConstraintCategory <em>Identity Constraint Category</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDIdentityConstraintDefinitionImpl#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDIdentityConstraintDefinitionImpl#getReferencedKey <em>Referenced Key</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDIdentityConstraintDefinitionImpl#getSelector <em>Selector</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDIdentityConstraintDefinitionImpl#getFields <em>Fields</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDIdentityConstraintDefinitionImpl 
  extends XSDNamedComponentImpl 
  implements XSDIdentityConstraintDefinition
{
  /**
   * The default value of the '{@link #getIdentityConstraintCategory() <em>Identity Constraint Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdentityConstraintCategory()
   * @generated
   * @ordered
   */
  protected static final XSDIdentityConstraintCategory IDENTITY_CONSTRAINT_CATEGORY_EDEFAULT = XSDIdentityConstraintCategory.KEY_LITERAL;

  /**
   * The cached value of the '{@link #getIdentityConstraintCategory() <em>Identity Constraint Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdentityConstraintCategory()
   * @generated
   * @ordered
   */
  protected XSDIdentityConstraintCategory identityConstraintCategory = IDENTITY_CONSTRAINT_CATEGORY_EDEFAULT;

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
   * The cached value of the '{@link #getReferencedKey() <em>Referenced Key</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferencedKey()
   * @generated
   * @ordered
   */
  protected XSDIdentityConstraintDefinition referencedKey;

  /**
   * The cached value of the '{@link #getSelector() <em>Selector</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSelector()
   * @generated
   * @ordered
   */
  protected XSDXPathDefinition selector;

  /**
   * The cached value of the '{@link #getFields() <em>Fields</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFields()
   * @generated
   * @ordered
   */
  protected EList<XSDXPathDefinition> fields;

  public static XSDIdentityConstraintDefinition createIdentityConstraintDefinition(Node node)
  {
    switch (XSDConstants.nodeType(node))
    {
      case XSDConstants.KEY_ELEMENT:
      case XSDConstants.KEYREF_ELEMENT:
      case XSDConstants.UNIQUE_ELEMENT:
      {
        XSDIdentityConstraintDefinition xsdIdentityConstraintDefinition = 
          XSDFactory.eINSTANCE.createXSDIdentityConstraintDefinition();
        xsdIdentityConstraintDefinition.setElement((Element)node);
        return xsdIdentityConstraintDefinition;
      }
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDIdentityConstraintDefinitionImpl()
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
    return XSDPackage.Literals.XSD_IDENTITY_CONSTRAINT_DEFINITION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDIdentityConstraintCategory getIdentityConstraintCategory()
  {
    return identityConstraintCategory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIdentityConstraintCategory(XSDIdentityConstraintCategory newIdentityConstraintCategory)
  {
    XSDIdentityConstraintCategory oldIdentityConstraintCategory = identityConstraintCategory;
    identityConstraintCategory = newIdentityConstraintCategory == null ? IDENTITY_CONSTRAINT_CATEGORY_EDEFAULT : newIdentityConstraintCategory;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__IDENTITY_CONSTRAINT_CATEGORY, oldIdentityConstraintCategory, identityConstraintCategory));
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
        msgs = ((InternalEObject)annotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__ANNOTATION, null, msgs);
      if (newAnnotation != null)
        msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__ANNOTATION, null, msgs);
      msgs = basicSetAnnotation(newAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__ANNOTATION, newAnnotation, newAnnotation));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__ANNOTATION, oldAnnotation, newAnnotation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDIdentityConstraintDefinition getReferencedKey()
  {
    return referencedKey;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReferencedKey(XSDIdentityConstraintDefinition newReferencedKey)
  {
    XSDIdentityConstraintDefinition oldReferencedKey = referencedKey;
    referencedKey = newReferencedKey;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__REFERENCED_KEY, oldReferencedKey, referencedKey));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDXPathDefinition getSelector()
  {
    return selector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSelector(XSDXPathDefinition newSelector)
  {
    if (newSelector != selector)
    {
      NotificationChain msgs = null;
      if (selector != null)
        msgs = ((InternalEObject)selector).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__SELECTOR, null, msgs);
      if (newSelector != null)
        msgs = ((InternalEObject)newSelector).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__SELECTOR, null, msgs);
      msgs = basicSetSelector(newSelector, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__SELECTOR, newSelector, newSelector));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSelector(XSDXPathDefinition newSelector, NotificationChain msgs)
  {
    XSDXPathDefinition oldSelector = selector;
    selector = newSelector;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__SELECTOR, oldSelector, newSelector);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDXPathDefinition> getFields()
  {
    if (fields == null)
    {
      fields = new EObjectContainmentEList<XSDXPathDefinition>(XSDXPathDefinition.class, this, XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__FIELDS);
    }
    return fields;
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
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__ANNOTATION:
        return basicSetAnnotation(null, msgs);
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__SELECTOR:
        return basicSetSelector(null, msgs);
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__FIELDS:
        return ((InternalEList<?>)getFields()).basicRemove(otherEnd, msgs);
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
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__IDENTITY_CONSTRAINT_CATEGORY:
        return getIdentityConstraintCategory();
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__ANNOTATION:
        return getAnnotation();
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__REFERENCED_KEY:
        return getReferencedKey();
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__SELECTOR:
        return getSelector();
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__FIELDS:
        return getFields();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__IDENTITY_CONSTRAINT_CATEGORY:
        setIdentityConstraintCategory((XSDIdentityConstraintCategory)newValue);
        return;
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__ANNOTATION:
        setAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__REFERENCED_KEY:
        setReferencedKey((XSDIdentityConstraintDefinition)newValue);
        return;
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__SELECTOR:
        setSelector((XSDXPathDefinition)newValue);
        return;
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__FIELDS:
        getFields().clear();
        getFields().addAll((Collection<? extends XSDXPathDefinition>)newValue);
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
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__IDENTITY_CONSTRAINT_CATEGORY:
        setIdentityConstraintCategory(IDENTITY_CONSTRAINT_CATEGORY_EDEFAULT);
        return;
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__ANNOTATION:
        setAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__REFERENCED_KEY:
        setReferencedKey((XSDIdentityConstraintDefinition)null);
        return;
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__SELECTOR:
        setSelector((XSDXPathDefinition)null);
        return;
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__FIELDS:
        getFields().clear();
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
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__IDENTITY_CONSTRAINT_CATEGORY:
        return identityConstraintCategory != IDENTITY_CONSTRAINT_CATEGORY_EDEFAULT;
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__ANNOTATION:
        return annotation != null;
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__REFERENCED_KEY:
        return referencedKey != null;
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__SELECTOR:
        return selector != null;
      case XSDPackage.XSD_IDENTITY_CONSTRAINT_DEFINITION__FIELDS:
        return fields != null && !fields.isEmpty();
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
    result.append(" (identityConstraintCategory: ");
    result.append(identityConstraintCategory);
    result.append(')');
    return result.toString();
  }

  @Override
  public Element createElement()
  {
    Element newElement = 
      createElement
        (XSDIdentityConstraintCategory.UNIQUE_LITERAL == getIdentityConstraintCategory() ?
           XSDConstants.UNIQUE_ELEMENT :
           XSDIdentityConstraintCategory.KEYREF_LITERAL == getIdentityConstraintCategory() ?
           XSDConstants.KEYREF_ELEMENT :
           XSDConstants.KEY_ELEMENT);
    setElement(newElement);
    return newElement;
  }

  @Override
  protected void patch()
  {
    super.patch();
    XSDIdentityConstraintDefinition theReferencedKey = getReferencedKey();
    if (theReferencedKey != null)
    {
      XSDIdentityConstraintDefinition newReferencedKey =
        resolveIdentityConstraintDefinition(theReferencedKey.getTargetNamespace(), theReferencedKey.getName());
      if (newReferencedKey != theReferencedKey)
      {
        setReferencedKey(newReferencedKey);
      }
    }
  }

  @Override
  public void validate()
  {
    super.validate();

    String anchor = null;
    String [] attributes = null;
    XSDComplexTypeDefinition xsdComplexTypeDefinition = null;
    switch (getIdentityConstraintCategory().getValue())
    {
      case XSDIdentityConstraintCategory.KEY:
      {
        anchor = "element-key";
        attributes =
          new String []
          {
            XSDConstants.ID_ATTRIBUTE,
            XSDConstants.NAME_ATTRIBUTE
          };

        xsdComplexTypeDefinition = getSchema().getSchemaForSchema().resolveComplexTypeDefinition("keybase");

        break;
      }
      case XSDIdentityConstraintCategory.KEYREF:
      {
        anchor = "element-keyRef";
        attributes =
          new String []
          {
            XSDConstants.ID_ATTRIBUTE,
            XSDConstants.NAME_ATTRIBUTE,
            XSDConstants.REFER_ATTRIBUTE
          };

        xsdComplexTypeDefinition = 
          (XSDComplexTypeDefinition)getSchema().getSchemaForSchema().resolveElementDeclaration("keyref").getTypeDefinition();

        XSDIdentityConstraintDefinition theReferencedKey = getReferencedKey();
        if (theReferencedKey == null)
        {
          createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "_UI_UnresolvedIdentityConstraintDefinition_message", "");
        }
        else if (theReferencedKey.getContainer() == null)
        {
          createDiagnostic
            (XSDDiagnosticSeverity.ERROR_LITERAL, "_UI_UnresolvedIdentityConstraintDefinition_message", theReferencedKey.getURI());
        }
        else
        {
          if (theReferencedKey.getFields().size() != getFields().size())
          {
            createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "c-props-correct.2");
          }
        }

        break;
      }
      case XSDIdentityConstraintCategory.UNIQUE:
      {
        anchor = "element-unique";
        attributes =
          new String []
          {
            XSDConstants.ID_ATTRIBUTE,
            XSDConstants.NAME_ATTRIBUTE
          };

        xsdComplexTypeDefinition = getSchema().getSchemaForSchema().resolveComplexTypeDefinition("keybase");
        break;
      }
    }
    Element theElement = getElement();
    if (theElement != null)
    {
      checkAttributes(XSDConstants.PART1, anchor, theElement, attributes);
      checkComplexContent(xsdComplexTypeDefinition, XSDConstants.PART1, anchor, theElement);
    }

    checkBuiltInTypeConstraint
      ("ID",
       null,
       XSDConstants.PART1,
       anchor,
       theElement,
       XSDConstants.ID_ATTRIBUTE,
       false);

    checkBuiltInTypeConstraint
      ("NCName",
       getName(),
       XSDConstants.PART1,
       anchor,
       theElement,
       XSDConstants.NAME_ATTRIBUTE,
       true);
  }

  @Override
  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    if (changedElement == getElement())
    {
      XSDIdentityConstraintCategory newIdentityConstraintCategory = XSDIdentityConstraintCategory.get(changedElement.getLocalName());
      if (newIdentityConstraintCategory != getIdentityConstraintCategory())
      {
        setIdentityConstraintCategory(newIdentityConstraintCategory);
      }

      if (newIdentityConstraintCategory == XSDIdentityConstraintCategory.KEYREF_LITERAL)
      {
        XSDIdentityConstraintDefinition newReferencedKey = null;
        if (changedElement.hasAttributeNS(null, XSDConstants.REFER_ATTRIBUTE))
        {
          newReferencedKey = 
            resolveIdentityConstraintDefinitionURI(XSDConstants.lookupQNameForAttribute(changedElement, XSDConstants.REFER_ATTRIBUTE));
        }

        if (newReferencedKey != getReferencedKey())
        {
          setReferencedKey(newReferencedKey);
        }
      }
    }
  }

  @Override
  protected void handleUnreconciledElement(Element child, List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    XSDXPathDefinition xsdXPathDefinition = XSDXPathDefinitionImpl.createXPathDefinition(child);
    if (xsdXPathDefinition != null)
    {
      newContents.add(xsdXPathDefinition);
    }
    else
    {
      XSDAnnotation xsdAnnotation = XSDAnnotationImpl.createAnnotation(child);
      if (xsdAnnotation != null && newContents.isEmpty())
      {
        newContents.add(xsdAnnotation);
      }
    }
  }

  @Override
  protected void handleReconciliation(List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    handleAnnotationReconciliation(XSDPackage.Literals.XSD_IDENTITY_CONSTRAINT_DEFINITION__ANNOTATION, newContents, remainingContents);

    XSDXPathDefinition newSelector = null;
    if (!newContents.isEmpty())
    {
      newSelector = (XSDXPathDefinition)newContents.get(0);
      if (XSDXPathVariety.SELECTOR_LITERAL == newSelector.getVariety())
      {
        newContents.remove(0);
      }
      else
      {
        newSelector = null;
      }
    }

    if (newSelector != getSelector())
    {
      remainingContents.remove(getSelector());
      setSelector(newSelector);
    }

    if (!remainingContents.isEmpty())
    {
      getFields().removeAll(remainingContents);
    }

    if (!newContents.isEmpty())
    {
      @SuppressWarnings("unchecked") List<XSDXPathDefinition> list = (List<XSDXPathDefinition>)(List<?>)newContents;
      setListContentAndOrder(getFields(), list);
    }
  }

  @Override
  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    Element theElement = getElement();
    if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_IDENTITY_CONSTRAINT_DEFINITION__IDENTITY_CONSTRAINT_CATEGORY)
    {
      if (theElement != null && eAttribute != null && !isReconciling)
      {
        Element newElement = 
          createElement
            (XSDIdentityConstraintCategory.UNIQUE_LITERAL == getIdentityConstraintCategory() ?
               XSDConstants.UNIQUE_ELEMENT :
               XSDIdentityConstraintCategory.KEYREF_LITERAL == getIdentityConstraintCategory() ?
               XSDConstants.KEYREF_ELEMENT :
               XSDConstants.KEY_ELEMENT);

        forceReplace(newElement, theElement);
        setElement(newElement);
      }
    }
  }

  @Override
  protected void adoptBy(XSDSchema xsdSchema)
  {
    super.adoptBy(xsdSchema);
    if (!xsdSchema.getIdentityConstraintDefinitions().contains(this))
    {
      XSDNamedComponentImpl.addToSortedList(xsdSchema.getIdentityConstraintDefinitions(), this);
    }
  }

  @Override
  protected void orphanBy(XSDSchema xsdSchema)
  {
    xsdSchema.getIdentityConstraintDefinitions().remove(this);
    super.orphanBy(xsdSchema);
  }

  @Override
  protected void changeReference(EReference eReference)
  {
    super.changeReference(eReference);
    Element theElement = getElement();
    if (XSDIdentityConstraintCategory.KEYREF_LITERAL == getIdentityConstraintCategory())
    {
      if (eReference == null || eReference == XSDPackage.Literals.XSD_IDENTITY_CONSTRAINT_DEFINITION__REFERENCED_KEY)
      {
        XSDIdentityConstraintDefinition theReferencedKey = getReferencedKey();
        if (theElement != null)
        {
          niceSetAttributeURIValue(theElement, XSDConstants.REFER_ATTRIBUTE, theReferencedKey == null ? null : theReferencedKey.getURI());
        }
      }
    }
  }

  @Override
  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDIdentityConstraintDefinitionImpl clonedIdentityConstraintDefinition =
      (XSDIdentityConstraintDefinitionImpl)getXSDFactory().createXSDIdentityConstraintDefinition();
    clonedIdentityConstraintDefinition.isReconciling = true;

    if (getName() != null)
    {
      clonedIdentityConstraintDefinition.setName(getName());
    }
    clonedIdentityConstraintDefinition.setIdentityConstraintCategory(getIdentityConstraintCategory());

    XSDIdentityConstraintDefinition theReferencedKey = getReferencedKey();
    if (theReferencedKey != null)
    {
      clonedIdentityConstraintDefinition.setReferencedKey
        (createUnresolvedIdentityConstraintDefinition
           (theReferencedKey.getTargetNamespace(), theReferencedKey.getName()));
    }

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedIdentityConstraintDefinition.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
      if (getSelector() != null)
      {
        clonedIdentityConstraintDefinition.setSelector((XSDXPathDefinition)getSelector().cloneConcreteComponent(deep, shareDOM));
      }
      if (!getFields().isEmpty())
      {
        clonedIdentityConstraintDefinition.getFields().addAll(cloneConcreteComponents(getFields(), deep, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedIdentityConstraintDefinition.setElement(getElement());
    }

    clonedIdentityConstraintDefinition.isReconciling = shareDOM;
    return clonedIdentityConstraintDefinition;
  }
}
