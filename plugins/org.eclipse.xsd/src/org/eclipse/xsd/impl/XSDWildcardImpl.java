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
 * $Id: XSDWildcardImpl.java,v 1.7 2005/11/23 13:56:55 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

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
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDNamespaceConstraintCategory;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDProcessContents;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDWildcard;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Wildcard</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDWildcardImpl#getNamespaceConstraintCategory <em>Namespace Constraint Category</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDWildcardImpl#getNamespaceConstraint <em>Namespace Constraint</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDWildcardImpl#getProcessContents <em>Process Contents</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDWildcardImpl#getLexicalNamespaceConstraint <em>Lexical Namespace Constraint</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDWildcardImpl#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDWildcardImpl#getAnnotations <em>Annotations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDWildcardImpl 
  extends XSDTermImpl 
  implements XSDWildcard 
{
  /**
   * The default value of the '{@link #getNamespaceConstraintCategory() <em>Namespace Constraint Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNamespaceConstraintCategory()
   * @generated
   * @ordered
   */
  protected static final XSDNamespaceConstraintCategory NAMESPACE_CONSTRAINT_CATEGORY_EDEFAULT = XSDNamespaceConstraintCategory.ANY_LITERAL;

  /**
   * The cached value of the '{@link #getNamespaceConstraintCategory() <em>Namespace Constraint Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNamespaceConstraintCategory()
   * @generated
   * @ordered
   */
  protected XSDNamespaceConstraintCategory namespaceConstraintCategory = NAMESPACE_CONSTRAINT_CATEGORY_EDEFAULT;

  /**
   * The cached value of the '{@link #getNamespaceConstraint() <em>Namespace Constraint</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNamespaceConstraint()
   * @generated
   * @ordered
   */
  protected EList namespaceConstraint = null;

  /**
   * The default value of the '{@link #getProcessContents() <em>Process Contents</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProcessContents()
   * @generated
   * @ordered
   */
  protected static final XSDProcessContents PROCESS_CONTENTS_EDEFAULT = XSDProcessContents.STRICT_LITERAL;

  /**
   * The cached value of the '{@link #getProcessContents() <em>Process Contents</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProcessContents()
   * @generated
   * @ordered
   */
  protected XSDProcessContents processContents = PROCESS_CONTENTS_EDEFAULT;

  /**
   * The flag representing whether the Process Contents attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int PROCESS_CONTENTS_ESETFLAG = 1 << 8;

  /**
   * The cached value of the '{@link #getLexicalNamespaceConstraint() <em>Lexical Namespace Constraint</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLexicalNamespaceConstraint()
   * @generated
   * @ordered
   */
  protected EList lexicalNamespaceConstraint = null;

  /**
   * The cached value of the '{@link #getAnnotation() <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotation()
   * @generated
   * @ordered
   */
  protected XSDAnnotation annotation = null;

  /**
   * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotations()
   * @generated
   * @ordered
   */
  protected EList annotations = null;

  public static XSDWildcard createWildcard(Node node)
  {
    switch (XSDConstants.nodeType(node))
    {
      case XSDConstants.ANY_ELEMENT:
      case XSDConstants.ANYATTRIBUTE_ELEMENT:
      {
        XSDWildcard xsdWildcard = XSDFactory.eINSTANCE.createXSDWildcard();
        xsdWildcard.setElement((Element)node);
        return xsdWildcard;
      }
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDWildcardImpl()
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
    return XSDPackage.eINSTANCE.getXSDWildcard();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDNamespaceConstraintCategory getNamespaceConstraintCategory()
  {
    return namespaceConstraintCategory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNamespaceConstraintCategory(XSDNamespaceConstraintCategory newNamespaceConstraintCategory)
  {
    XSDNamespaceConstraintCategory oldNamespaceConstraintCategory = namespaceConstraintCategory;
    namespaceConstraintCategory = newNamespaceConstraintCategory == null ? NAMESPACE_CONSTRAINT_CATEGORY_EDEFAULT : newNamespaceConstraintCategory;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_WILDCARD__NAMESPACE_CONSTRAINT_CATEGORY, oldNamespaceConstraintCategory, namespaceConstraintCategory));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getNamespaceConstraint()
  {
    if (namespaceConstraint == null)
    {
      namespaceConstraint = new EDataTypeUniqueEList(String.class, this, XSDPackage.XSD_WILDCARD__NAMESPACE_CONSTRAINT);
    }
    return namespaceConstraint;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDProcessContents getProcessContents()
  {
    return processContents;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProcessContents(XSDProcessContents newProcessContents)
  {
    XSDProcessContents oldProcessContents = processContents;
    processContents = newProcessContents == null ? PROCESS_CONTENTS_EDEFAULT : newProcessContents;
    boolean oldProcessContentsESet = (eFlags & PROCESS_CONTENTS_ESETFLAG) != 0;
    eFlags |= PROCESS_CONTENTS_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_WILDCARD__PROCESS_CONTENTS, oldProcessContents, processContents, !oldProcessContentsESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetProcessContents()
  {
    XSDProcessContents oldProcessContents = processContents;
    boolean oldProcessContentsESet = (eFlags & PROCESS_CONTENTS_ESETFLAG) != 0;
    processContents = PROCESS_CONTENTS_EDEFAULT;
    eFlags &= ~PROCESS_CONTENTS_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, XSDPackage.XSD_WILDCARD__PROCESS_CONTENTS, oldProcessContents, PROCESS_CONTENTS_EDEFAULT, oldProcessContentsESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetProcessContents()
  {
    return (eFlags & PROCESS_CONTENTS_ESETFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getLexicalNamespaceConstraint()
  {
    if (lexicalNamespaceConstraint == null)
    {
      lexicalNamespaceConstraint = new EDataTypeUniqueEList.Unsettable(String.class, this, XSDPackage.XSD_WILDCARD__LEXICAL_NAMESPACE_CONSTRAINT);
    }
    return lexicalNamespaceConstraint;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetLexicalNamespaceConstraint()
  {
    ((InternalEList.Unsettable)getLexicalNamespaceConstraint()).unset();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetLexicalNamespaceConstraint()
  {
    return lexicalNamespaceConstraint != null && ((InternalEList.Unsettable)lexicalNamespaceConstraint).isSet();
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
        msgs = ((InternalEObject)annotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_WILDCARD__ANNOTATION, null, msgs);
      if (newAnnotation != null)
        msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_WILDCARD__ANNOTATION, null, msgs);
      msgs = basicSetAnnotation(newAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_WILDCARD__ANNOTATION, newAnnotation, newAnnotation));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_WILDCARD__ANNOTATION, oldAnnotation, newAnnotation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getAnnotations()
  {
    if (annotations == null)
    {
      annotations = new EObjectEList(XSDAnnotation.class, this, XSDPackage.XSD_WILDCARD__ANNOTATIONS);
    }
    return annotations;
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
        case XSDPackage.XSD_WILDCARD__DIAGNOSTICS:
          return ((InternalEList)getDiagnostics()).basicRemove(otherEnd, msgs);
        case XSDPackage.XSD_WILDCARD__ANNOTATION:
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
      case XSDPackage.XSD_WILDCARD__ELEMENT:
        return getElement();
      case XSDPackage.XSD_WILDCARD__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_WILDCARD__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_WILDCARD__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_WILDCARD__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_WILDCARD__NAMESPACE_CONSTRAINT_CATEGORY:
        return getNamespaceConstraintCategory();
      case XSDPackage.XSD_WILDCARD__NAMESPACE_CONSTRAINT:
        return getNamespaceConstraint();
      case XSDPackage.XSD_WILDCARD__PROCESS_CONTENTS:
        return getProcessContents();
      case XSDPackage.XSD_WILDCARD__LEXICAL_NAMESPACE_CONSTRAINT:
        return getLexicalNamespaceConstraint();
      case XSDPackage.XSD_WILDCARD__ANNOTATION:
        return getAnnotation();
      case XSDPackage.XSD_WILDCARD__ANNOTATIONS:
        return getAnnotations();
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
      case XSDPackage.XSD_WILDCARD__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_WILDCARD__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_WILDCARD__NAMESPACE_CONSTRAINT_CATEGORY:
        setNamespaceConstraintCategory((XSDNamespaceConstraintCategory)newValue);
        return;
      case XSDPackage.XSD_WILDCARD__NAMESPACE_CONSTRAINT:
        getNamespaceConstraint().clear();
        getNamespaceConstraint().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_WILDCARD__PROCESS_CONTENTS:
        setProcessContents((XSDProcessContents)newValue);
        return;
      case XSDPackage.XSD_WILDCARD__LEXICAL_NAMESPACE_CONSTRAINT:
        getLexicalNamespaceConstraint().clear();
        getLexicalNamespaceConstraint().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_WILDCARD__ANNOTATION:
        setAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_WILDCARD__ANNOTATIONS:
        getAnnotations().clear();
        getAnnotations().addAll((Collection)newValue);
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
      case XSDPackage.XSD_WILDCARD__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_WILDCARD__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_WILDCARD__NAMESPACE_CONSTRAINT_CATEGORY:
        setNamespaceConstraintCategory(NAMESPACE_CONSTRAINT_CATEGORY_EDEFAULT);
        return;
      case XSDPackage.XSD_WILDCARD__NAMESPACE_CONSTRAINT:
        getNamespaceConstraint().clear();
        return;
      case XSDPackage.XSD_WILDCARD__PROCESS_CONTENTS:
        unsetProcessContents();
        return;
      case XSDPackage.XSD_WILDCARD__LEXICAL_NAMESPACE_CONSTRAINT:
        unsetLexicalNamespaceConstraint();
        return;
      case XSDPackage.XSD_WILDCARD__ANNOTATION:
        setAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_WILDCARD__ANNOTATIONS:
        getAnnotations().clear();
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
      case XSDPackage.XSD_WILDCARD__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_WILDCARD__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_WILDCARD__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_WILDCARD__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_WILDCARD__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_WILDCARD__NAMESPACE_CONSTRAINT_CATEGORY:
        return namespaceConstraintCategory != NAMESPACE_CONSTRAINT_CATEGORY_EDEFAULT;
      case XSDPackage.XSD_WILDCARD__NAMESPACE_CONSTRAINT:
        return namespaceConstraint != null && !namespaceConstraint.isEmpty();
      case XSDPackage.XSD_WILDCARD__PROCESS_CONTENTS:
        return isSetProcessContents();
      case XSDPackage.XSD_WILDCARD__LEXICAL_NAMESPACE_CONSTRAINT:
        return isSetLexicalNamespaceConstraint();
      case XSDPackage.XSD_WILDCARD__ANNOTATION:
        return annotation != null;
      case XSDPackage.XSD_WILDCARD__ANNOTATIONS:
        return annotations != null && !annotations.isEmpty();
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
    result.append(" (namespaceConstraintCategory: ");
    result.append(namespaceConstraintCategory);
    result.append(", namespaceConstraint: ");
    result.append(namespaceConstraint);
    result.append(", processContents: ");
    if ((eFlags & PROCESS_CONTENTS_ESETFLAG) != 0) result.append(processContents); else result.append("<unset>");
    result.append(", lexicalNamespaceConstraint: ");
    result.append(lexicalNamespaceConstraint);
    result.append(')');
    return result.toString();
  }

  public Element createElement()
  {
    Element newElement = 
      createElement(getContainer() instanceof XSDParticle ? XSDConstants.ANY_ELEMENT : XSDConstants.ANYATTRIBUTE_ELEMENT);
    setElement(newElement);
    return newElement;
  }

  public void patch()
  {
    super.patch();
    for (Iterator strings = getLexicalNamespaceConstraint().iterator(); strings.hasNext(); )
    {
      String string = (String)strings.next();
      if (!string.startsWith("##"))
      {
        ((XSDSchemaImpl)getSchema()).resolveSchema(string);
      }
    }
  }

  protected boolean analyze()
  {
    super.analyze();

    XSDSchema xsdSchema = getSchema();
    XSDNamespaceConstraintCategory newNamespaceConstraintCategory = XSDNamespaceConstraintCategory.ANY_LITERAL;
    List newNamespaceConstraint = new ArrayList();

    for (Iterator strings = getLexicalNamespaceConstraint().iterator(); strings.hasNext(); )
    {
      String string = (String)strings.next();
      if (string.equals("##any"))
      {
        break;
      }
      else if (string.equals("##other"))
      {
        newNamespaceConstraintCategory = XSDNamespaceConstraintCategory.NOT_LITERAL;
        String targetNamespace = xsdSchema.getTargetNamespace();
        newNamespaceConstraint.add(targetNamespace);
        break;
      }
      else if (string.equals("##targetNamespace"))
      {
        newNamespaceConstraintCategory = XSDNamespaceConstraintCategory.SET_LITERAL;
        String targetNamespace = xsdSchema.getTargetNamespace();
        newNamespaceConstraint.add(targetNamespace);
      }
      else if (string.equals("##local"))
      {
        newNamespaceConstraintCategory = XSDNamespaceConstraintCategory.SET_LITERAL;
        newNamespaceConstraint.add(null);
      }
      else 
      {
        newNamespaceConstraintCategory = XSDNamespaceConstraintCategory.SET_LITERAL;
        newNamespaceConstraint.add(string);
      }
    }

    if (getNamespaceConstraintCategory() != newNamespaceConstraintCategory)
    {
      setNamespaceConstraintCategory(newNamespaceConstraintCategory);
    }

    EList theNamespaceConstraint = getNamespaceConstraint();
    List remainingNamespaceConstraint = new ArrayList(theNamespaceConstraint);
    remainingNamespaceConstraint.removeAll(newNamespaceConstraint);
    if (!remainingNamespaceConstraint.isEmpty())
    {
      theNamespaceConstraint.removeAll(remainingNamespaceConstraint);
    }
    if (!newNamespaceConstraint.isEmpty())
    {
      setListContentAndOrder(theNamespaceConstraint, newNamespaceConstraint);
    }

    return true;
  }

  public void validate()
  {
    super.validate();

    String anchor = null;
    String contentType = null;

    Element theElement = getElement();
    if (theElement != null)
    {
      String [] attributes = null;
      if (getContainer() instanceof XSDParticle)
      {
        anchor = "element-any";
        contentType = "wildcard";
        attributes =
          new String []
          {
            XSDConstants.ID_ATTRIBUTE,
            XSDConstants.MAXOCCURS_ATTRIBUTE,
            XSDConstants.MINOCCURS_ATTRIBUTE,
            XSDConstants.NAMESPACE_ATTRIBUTE,
            XSDConstants.PROCESSCONTENTS_ATTRIBUTE,
          };

        checkAttributeTypeConstraint
          (contentType,
           "maxOccurs",
           null,
           XSDConstants.PART1,
           anchor,
           theElement,
           XSDConstants.MAXOCCURS_ATTRIBUTE,
           false);

        checkAttributeTypeConstraint
          (contentType,
           "minOccurs",
           null,
           XSDConstants.PART1,
           anchor,
           theElement,
           XSDConstants.MINOCCURS_ATTRIBUTE,
           false);
      }
      else
      {
        anchor = "element-anyAttribute";
        contentType = "wildcard";
        attributes =
          new String []
          {
            XSDConstants.ID_ATTRIBUTE,
            XSDConstants.NAMESPACE_ATTRIBUTE,
            XSDConstants.PROCESSCONTENTS_ATTRIBUTE,
          };
      }

      checkAttributes(XSDConstants.PART1, anchor, theElement, attributes);
      checkComplexContent(contentType, XSDConstants.PART1, anchor, theElement);

      checkBuiltInTypeConstraint
        ("ID",
         null,
         XSDConstants.PART1,
         anchor,
         theElement,
         XSDConstants.ID_ATTRIBUTE,
         false);

      checkAttributeTypeConstraint
        (contentType,
         "namespace",
         null,
         XSDConstants.PART1,
         anchor,
         theElement,
         XSDConstants.NAMESPACE_ATTRIBUTE,
         false);

      checkAttributeTypeConstraint
        (contentType,
         "processContents",
         null,
         XSDConstants.PART1,
         anchor,
         theElement,
         XSDConstants.PROCESSCONTENTS_ATTRIBUTE,
         false);
    }
  }

  protected boolean isUpdatingDOM()
  {
    // EATM I think this is needed.
    return
      super.isUpdatingDOM() ||
        getContainer() instanceof XSDParticle &&
          ((XSDConcreteComponentImpl)getContainer()).isUpdatingDOM();
  }

  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    if (changedElement == getElement())
    {
      if (changedElement.hasAttributeNS(null, XSDConstants.PROCESSCONTENTS_ATTRIBUTE))
      {
        XSDProcessContents newProcessContents = 
          XSDProcessContents.get(changedElement.getAttributeNS(null, XSDConstants.PROCESSCONTENTS_ATTRIBUTE));
        if (!isSetProcessContents() || newProcessContents != getProcessContents())
        {
          setProcessContents(newProcessContents);
        }
      }
      else if (isSetProcessContents())
      {
        unsetProcessContents();
      }

      if (changedElement.hasAttributeNS(null, XSDConstants.NAMESPACE_ATTRIBUTE))
      {
        String newLexicalNamespaceConstraint = changedElement.getAttributeNS(null, XSDConstants.NAMESPACE_ATTRIBUTE);
        if (!newLexicalNamespaceConstraint.equals(getStringLexicalNamespaceConstraint()))
        {
          setStringLexicalNamespaceConstraint(newLexicalNamespaceConstraint);
        }
      }
      else if (isSetLexicalNamespaceConstraint())
      {
        unsetLexicalNamespaceConstraint();
      }
    }
  }

  protected void handleUnreconciledElement(Element child, List newContents, List remainingContents)
  {
    if (XSDConstants.nodeType(child) == XSDConstants.ANNOTATION_ELEMENT)
    {
      XSDAnnotation xsdAnnotation = XSDAnnotationImpl.createAnnotation(child);
      newContents.add(xsdAnnotation);
    }
  }

  protected void handleReconciliation(List newContents, List remainingContents)
  {
    handleAnnotationReconciliation(XSDPackage.eINSTANCE.getXSDWildcard_Annotation(), newContents, remainingContents);
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
      if (eAttribute == null || eAttribute == XSDPackage.eINSTANCE.getXSDWildcard_LexicalNamespaceConstraint())
      {
        if (!isSetLexicalNamespaceConstraint())
        {
          niceSetAttribute(theElement, XSDConstants.NAMESPACE_ATTRIBUTE, null);
        }
        else
        {
          List theLexicalNamespaceConstraint = getLexicalNamespaceConstraint();
          StringBuffer result = new StringBuffer();
          for (Iterator values = theLexicalNamespaceConstraint.iterator(); values.hasNext(); )
          {
            if (result.length() != 0)
            {
              result.append(' ');
            }
            result.append((String)values.next());
          }
  
          niceSetAttribute(theElement, XSDConstants.NAMESPACE_ATTRIBUTE, result.toString());
        }
        if (eAttribute != null)
        {
          traverseToRootForPatching();
        }
      }
      if (eAttribute == null || eAttribute == XSDPackage.eINSTANCE.getXSDWildcard_ProcessContents())
      {
        XSDProcessContents theProcessContents = getProcessContents();
        niceSetAttribute
          (theElement, XSDConstants.PROCESSCONTENTS_ATTRIBUTE, isSetProcessContents() ? theProcessContents.getName() : null);
      }
    }
  }

  protected void adoptContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.adoptContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.eINSTANCE.getXSDWildcard_Annotation())
    {
      getAnnotations().add(xsdConcreteComponent);
    }
  }

  protected void orphanContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.orphanContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.eINSTANCE.getXSDWildcard_Annotation())
    {
      int index = getAnnotations().indexOf(xsdConcreteComponent);
      if (index >= 0)
      {
        getAnnotations().remove(index);
      }
    }
  }

  public String getStringNamespaceConstraint()
  {
    StringBuffer result = new StringBuffer();
    for (Iterator values = getNamespaceConstraint().iterator(); values.hasNext(); )
    {
      if (result.length() != 0)
      {
        result.append(' ');
      }
      String value = (String)values.next();
      if (value == null || value.length() == 0)
      {
        result.append("'absent'");
      }
      else
      {
        result.append(value);
      }
    }

    return result.toString();
  }

  public String getStringLexicalNamespaceConstraint()
  {
    if (isSetLexicalNamespaceConstraint())
    {
      StringBuffer result = new StringBuffer();
      for (Iterator values = getLexicalNamespaceConstraint().iterator(); values.hasNext(); )
      {
        if (result.length() != 0)
        {
          result.append(' ');
        }
        result.append((String)values.next());
      }
  
      return result.toString();
    }
    else
    {
      return null;
    }
  }

  public void setStringLexicalNamespaceConstraint(String lexicalNamespaceConstraint)
  {
    if (lexicalNamespaceConstraint == null)
    {
      unsetLexicalNamespaceConstraint();
    }
    else
    {
      List newLexicalNamespaceConstraint = new ArrayList();
      for (StringTokenizer stringTokenizer = new StringTokenizer(lexicalNamespaceConstraint); stringTokenizer.hasMoreTokens(); )
      {
        String token = stringTokenizer.nextToken();
        newLexicalNamespaceConstraint.add(token);
      }
      if (!newLexicalNamespaceConstraint.equals(getLexicalNamespaceConstraint()))
      {
        Collection oldContents = new ArrayList(getLexicalNamespaceConstraint());
        oldContents.removeAll(newLexicalNamespaceConstraint);
        if (!oldContents.isEmpty())
        {
          getLexicalNamespaceConstraint().removeAll(oldContents);
        }
        setListContentAndOrder(getLexicalNamespaceConstraint(), newLexicalNamespaceConstraint);
      }
      else if (newLexicalNamespaceConstraint.isEmpty() && !isSetLexicalNamespaceConstraint())
      {
        getLexicalNamespaceConstraint().clear();
      }
    }
  }

  public boolean isWildcardSubset(XSDWildcard superSetWildcard)

  {
    //  Clause 1
    //
    if (XSDNamespaceConstraintCategory.ANY_LITERAL == superSetWildcard.getNamespaceConstraintCategory())
    {
      return true;
    }
    //  Clause 2
    //
    else if (XSDNamespaceConstraintCategory.NOT_LITERAL == getNamespaceConstraintCategory() && 
               XSDNamespaceConstraintCategory.NOT_LITERAL == superSetWildcard.getNamespaceConstraintCategory() &&
               getNamespaceConstraint().equals(superSetWildcard.getNamespaceConstraint()))
    {
      return true;
    }
    //  Clauses 3.1 and 3.2
    //
    else if (XSDNamespaceConstraintCategory.SET_LITERAL == getNamespaceConstraintCategory() &&
               (XSDNamespaceConstraintCategory.SET_LITERAL == superSetWildcard.getNamespaceConstraintCategory() &&  
                  superSetWildcard.getNamespaceConstraint().containsAll(getNamespaceConstraint()) ||
                XSDNamespaceConstraintCategory.NOT_LITERAL == superSetWildcard.getNamespaceConstraintCategory() &&  
                    !getNamespaceConstraint().containsAll(superSetWildcard.getNamespaceConstraint())))
    {
      return true;
    }
    //  Failure
    //
    else
    {
      return false;
    }
  }

  public XSDWildcard attributeWildcardUnion(XSDWildcard otherWildcard)
  {
    //  Clause 1
    //
    if (getNamespaceConstraintCategory() == otherWildcard.getNamespaceConstraintCategory() && 
          getNamespaceConstraint().containsAll(otherWildcard.getNamespaceConstraint()) &&
          otherWildcard.getNamespaceConstraint().containsAll(getNamespaceConstraint()))
    {
      return this;
    }
    //  Clause 2
    //
    else if (XSDNamespaceConstraintCategory.ANY_LITERAL == getNamespaceConstraintCategory())
    {
      return this;
    }
    //  Clause 2
    //
    else if (XSDNamespaceConstraintCategory.ANY_LITERAL == otherWildcard.getNamespaceConstraintCategory())
    {
      return  otherWildcard;
    }
    //  Clause 3.
    //
    else if (XSDNamespaceConstraintCategory.SET_LITERAL == getNamespaceConstraintCategory() &&
               XSDNamespaceConstraintCategory.SET_LITERAL == otherWildcard.getNamespaceConstraintCategory())
    {
      XSDWildcard result = getXSDFactory().createXSDWildcard();
      if (isSetProcessContents())
      {
        result.setProcessContents(getProcessContents());
      }
      result.setNamespaceConstraintCategory(XSDNamespaceConstraintCategory.SET_LITERAL);
      List newNamespaceConstraint = new ArrayList(getNamespaceConstraint());
      for (Iterator values = otherWildcard.getNamespaceConstraint().iterator(); values.hasNext(); )
      {
        Object value = values.next();
        if (!newNamespaceConstraint.contains(value))
        {
          newNamespaceConstraint.add(value);
        }
      }
      result.getNamespaceConstraint().addAll(newNamespaceConstraint);
      return result;
    }
    //  Clause 4
    //
    else if (XSDNamespaceConstraintCategory.NOT_LITERAL == getNamespaceConstraintCategory() &&
               XSDNamespaceConstraintCategory.NOT_LITERAL == otherWildcard.getNamespaceConstraintCategory() &&
               !getNamespaceConstraint().equals(otherWildcard.getNamespaceConstraint()))
    {
      return null;
    }
    //  Clause 5
    //
    else if (XSDNamespaceConstraintCategory.SET_LITERAL == getNamespaceConstraintCategory() &&
               XSDNamespaceConstraintCategory.NOT_LITERAL == otherWildcard.getNamespaceConstraintCategory())

    {
      //  Clause 5.2
      //
      if (getNamespaceConstraint().containsAll(otherWildcard.getNamespaceConstraint()))
      {
        XSDWildcard result = getXSDFactory().createXSDWildcard();
        if (isSetProcessContents())
        {
          result.setProcessContents(getProcessContents());
        }
        result.setNamespaceConstraintCategory(XSDNamespaceConstraintCategory.ANY_LITERAL);
        return result;
      }
      //  Clause 5.1
      //
      else
      {
        return this;
      }
    }
    //  Clause 5
    //
    else if (XSDNamespaceConstraintCategory.SET_LITERAL == otherWildcard.getNamespaceConstraintCategory() &&
               XSDNamespaceConstraintCategory.NOT_LITERAL == getNamespaceConstraintCategory())
    {
      //  Clause 5.2
      //
      if (otherWildcard.getNamespaceConstraint().containsAll(getNamespaceConstraint()))
      {
        XSDWildcard result = getXSDFactory().createXSDWildcard();
        if (isSetProcessContents())
        {
          result.setProcessContents(getProcessContents());
        }
        result.setNamespaceConstraintCategory(XSDNamespaceConstraintCategory.ANY_LITERAL);
        return result;
      }
      //  Clause 5.1
      //
      else
      {
        return otherWildcard;
      }
    }
    //  Failure
    //
    else
    {
      // EATM return a bad placeholder.
      XSDWildcard result = getXSDFactory().createXSDWildcard();
      result.setNamespaceConstraintCategory(XSDNamespaceConstraintCategory.NOT_LITERAL);
      return result;
      // return null;
    }
  }

  public XSDWildcard attributeWildcardIntersection(XSDWildcard otherWildcard)
  {
    //  Clause 1
    //
    if (getNamespaceConstraintCategory() == otherWildcard.getNamespaceConstraintCategory() && 
          getNamespaceConstraint().containsAll(otherWildcard.getNamespaceConstraint()) &&
          otherWildcard.getNamespaceConstraint().containsAll(getNamespaceConstraint()))
    {
      return this;
    }
    //  Clause 2
    //
    else if (XSDNamespaceConstraintCategory.ANY_LITERAL == getNamespaceConstraintCategory())
    {
      return otherWildcard;
    }
    //  Clause 2
    //
    else if (XSDNamespaceConstraintCategory.ANY_LITERAL == otherWildcard.getNamespaceConstraintCategory())
    {
      return  this;
    }
    //  Clause 3
    //
    else if (XSDNamespaceConstraintCategory.SET_LITERAL == getNamespaceConstraintCategory() &&
               XSDNamespaceConstraintCategory.NOT_LITERAL == otherWildcard.getNamespaceConstraintCategory())

    {
      //  Clause 3 minus
      //
      if (getNamespaceConstraint().containsAll(otherWildcard.getNamespaceConstraint()))
      {
        XSDWildcard result = getXSDFactory().createXSDWildcard();
        if (isSetProcessContents())
        {
          result.setProcessContents(getProcessContents());
        }
        result.setNamespaceConstraintCategory(XSDNamespaceConstraintCategory.SET_LITERAL);
        List newNamespaceConstraint = new ArrayList(getNamespaceConstraint());
        newNamespaceConstraint.remove(otherWildcard.getNamespaceConstraint());
        result.getNamespaceConstraint().addAll(newNamespaceConstraint);
        return result;
      }
      //  Clause 3 without minus
      //
      else
      {
        return this;
      }
    }
    //  Clause 3
    //
    else if (XSDNamespaceConstraintCategory.SET_LITERAL == otherWildcard.getNamespaceConstraintCategory() &&
               XSDNamespaceConstraintCategory.NOT_LITERAL == getNamespaceConstraintCategory())
    {
      //  Clause 3 with minus
      //
      if (otherWildcard.getNamespaceConstraint().containsAll(getNamespaceConstraint()))
      {
        XSDWildcard result = getXSDFactory().createXSDWildcard();
        if (isSetProcessContents())
        {
          result.setProcessContents(getProcessContents());
        }
        result.setNamespaceConstraintCategory(XSDNamespaceConstraintCategory.SET_LITERAL);
        List newNamespaceConstraint = new ArrayList(otherWildcard.getNamespaceConstraint());
        newNamespaceConstraint.remove(getNamespaceConstraint());
        result.getNamespaceConstraint().addAll(newNamespaceConstraint);
        return result;
      }
      //  Clause 3 without minus
      //
      else
      {
        return otherWildcard;
      }
    }
    //  Clause 4
    //
    else if (XSDNamespaceConstraintCategory.SET_LITERAL == getNamespaceConstraintCategory() &&
               XSDNamespaceConstraintCategory.SET_LITERAL == otherWildcard.getNamespaceConstraintCategory())
    {
      XSDWildcard result = getXSDFactory().createXSDWildcard();
      if (isSetProcessContents())
      {
        result.setProcessContents(getProcessContents());
      }
      result.setNamespaceConstraintCategory(XSDNamespaceConstraintCategory.SET_LITERAL);
      List newNamespaceConstraint = new ArrayList(getNamespaceConstraint());
      newNamespaceConstraint.retainAll(otherWildcard.getNamespaceConstraint());
      result.getNamespaceConstraint().addAll(newNamespaceConstraint);
      return result;
    }
    //  Clause 5
    //
    else  if (XSDNamespaceConstraintCategory.NOT_LITERAL == getNamespaceConstraintCategory() &&
                XSDNamespaceConstraintCategory.NOT_LITERAL == otherWildcard.getNamespaceConstraintCategory() &&
                !getNamespaceConstraint().equals(otherWildcard.getNamespaceConstraint()))
    {
      // EATM return a bad placeholder.
      XSDWildcard result = getXSDFactory().createXSDWildcard();
      result.setNamespaceConstraintCategory(XSDNamespaceConstraintCategory.NOT_LITERAL);
      return result;
      // return null;
    }
    //  Failure
    //
    else
    {
      // EATM return a bad placeholder.
      XSDWildcard result = getXSDFactory().createXSDWildcard();
      result.setNamespaceConstraintCategory(XSDNamespaceConstraintCategory.NOT_LITERAL);
      return result;
      // return null;
    }
  }

  public void setLike(XSDWildcard xsdWildcard)
  {
    if (getNamespaceConstraintCategory() != xsdWildcard.getNamespaceConstraintCategory())
    {
      setNamespaceConstraintCategory(xsdWildcard.getNamespaceConstraintCategory());
    }

    EList theNamespaceConstraint = getNamespaceConstraint();
    EList newNamespaceConstraint = xsdWildcard.getNamespaceConstraint();
    if (!theNamespaceConstraint.containsAll(newNamespaceConstraint) ||
          !newNamespaceConstraint.containsAll(theNamespaceConstraint))
    {
      List remainingNamespaceConstraint = new ArrayList(theNamespaceConstraint);
      remainingNamespaceConstraint.removeAll(newNamespaceConstraint);
      if (!remainingNamespaceConstraint.isEmpty())
      {
        theNamespaceConstraint.removeAll(remainingNamespaceConstraint);
      }
      if (!newNamespaceConstraint.isEmpty())
      {
        setListContentAndOrder(theNamespaceConstraint, newNamespaceConstraint);
      }
    }

    if (xsdWildcard.isSetProcessContents())
    {
      if (getProcessContents() != xsdWildcard.getProcessContents())
      {
        setProcessContents(xsdWildcard.getProcessContents());
      }
    }
    else
    {
      unsetProcessContents();
    }
  }

  public boolean allows(String namespace)
  {
    switch (getNamespaceConstraintCategory().getValue())
    {
      case XSDNamespaceConstraintCategory.ANY:
      {
        return true;
      }
      case XSDNamespaceConstraintCategory.NOT:
      {
        return namespace != null && !getNamespaceConstraint().contains(namespace);
      }
      case XSDNamespaceConstraintCategory.SET:
      {
        return getNamespaceConstraint().contains(namespace);
      }
      default:
      {
        return false;
      }
    }
  }

  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDWildcardImpl clonedWildcard =
      (XSDWildcardImpl)getXSDFactory().createXSDWildcard();
    clonedWildcard.isReconciling = true;

    if (isSetLexicalNamespaceConstraint())
    {
      if (!getLexicalNamespaceConstraint().isEmpty())
      {
        clonedWildcard.getLexicalNamespaceConstraint().addAll(getLexicalNamespaceConstraint());
      }
      else
      {
        clonedWildcard.getLexicalNamespaceConstraint().clear();
      }
    }

    if (isSetProcessContents())
    {  
      clonedWildcard.setProcessContents(getProcessContents());
    }

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedWildcard.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedWildcard.setElement(getElement());
    }

    clonedWildcard.isReconciling = shareDOM;
    return clonedWildcard;
  }
} 
