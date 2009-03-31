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
 * $Id: XSDIncludeImpl.java,v 1.12 2009/03/31 13:55:44 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDInclude;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Include</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDIncludeImpl#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDIncludeImpl 
  extends XSDSchemaCompositorImpl 
  implements XSDInclude
{
  /**
   * The cached value of the '{@link #getAnnotation() <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotation()
   * @generated
   * @ordered
   */
  protected XSDAnnotation annotation;

  public static XSDInclude createInclude(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.INCLUDE_ELEMENT)
    {
      XSDInclude xsdInclude = XSDFactory.eINSTANCE.createXSDInclude();
      xsdInclude.setElement((Element)node);
      return xsdInclude;
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDIncludeImpl()
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
    return XSDPackage.Literals.XSD_INCLUDE;
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
        msgs = ((InternalEObject)annotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_INCLUDE__ANNOTATION, null, msgs);
      if (newAnnotation != null)
        msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_INCLUDE__ANNOTATION, null, msgs);
      msgs = basicSetAnnotation(newAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_INCLUDE__ANNOTATION, newAnnotation, newAnnotation));
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
      case XSDPackage.XSD_INCLUDE__ANNOTATION:
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_INCLUDE__ANNOTATION, oldAnnotation, newAnnotation);
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
      case XSDPackage.XSD_INCLUDE__ANNOTATION:
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
      case XSDPackage.XSD_INCLUDE__ANNOTATION:
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
      case XSDPackage.XSD_INCLUDE__ANNOTATION:
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
      case XSDPackage.XSD_INCLUDE__ANNOTATION:
        return annotation != null;
    }
    return super.eIsSet(featureID);
  }

  @Override
  public Element createElement()
  {
    Element newElement = createElement(XSDConstants.INCLUDE_ELEMENT);
    setElement(newElement);
    return newElement;
  }

  @Override
  protected void patch()
  {
    if (getSchemaLocation() != null && getSchemaLocation().length() > 0)
    {
      resolve("", getSchemaLocation());
    }
    super.patch();
  }

  @Override
  protected void handleResolvedSchema(XSDSchema xsdSchema)
  {
    if (xsdSchema == null)
    {
      setResolvedSchema(null);
      setIncorporatedSchema(null);
    }
    else
    {
      ((XSDSchemaImpl)xsdSchema).included(this);
    }
  }

  @Override
  protected void handleUnreconciledElement(Element child, List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    XSDAnnotation xsdAnnotation = XSDAnnotationImpl.createAnnotation(child);
    if (xsdAnnotation != null)
    {
      newContents.add(xsdAnnotation);
    }
  }

  @Override
  protected void handleReconciliation(List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    handleAnnotationReconciliation(XSDPackage.Literals.XSD_INCLUDE__ANNOTATION, newContents, remainingContents);
  }

  @Override
  public void validate()
  {
    super.validate();

    Element theElement = getElement();
    if (theElement != null)
    {
      checkAttributes
        (XSDConstants.PART1,
         "element-include",
         theElement,
         new String []
         {
           XSDConstants.ID_ATTRIBUTE,
           XSDConstants.SCHEMALOCATION_ATTRIBUTE,
         });

      checkComplexContent("annotated", XSDConstants.PART1, "element-include", theElement);

      checkBuiltInTypeConstraint
        ("ID",
         null,
         XSDConstants.PART1,
         "element-include",
         theElement,
         XSDConstants.ID_ATTRIBUTE,
         false);
    }

    checkBuiltInTypeConstraint
      ("anyURI",
       getSchemaLocation(),
       XSDConstants.PART1,
       "element-include",
       theElement,
       XSDConstants.SCHEMALOCATION_ATTRIBUTE,
       true);

    XSDSchema theResolvedSchema = getResolvedSchema();
    if (theResolvedSchema == null)
    {
      createDiagnostic
        (XSDDiagnosticSeverity.WARNING_LITERAL,
         "src-include.0",
         getSchemaLocation() == null ? "" : getSchemaLocation());
    }
    else
    {
      if (theResolvedSchema.eResource() == null || 
            theResolvedSchema.getElement() == null ||
            XSDConstants.nodeType(theResolvedSchema.getElement()) != XSDConstants.SCHEMA_ELEMENT)
      {
        createDiagnostic
          (XSDDiagnosticSeverity.ERROR_LITERAL,
           "src-include.1",
           getSchemaLocation() == null ? "" : getSchemaLocation());
      }
      else
      {
        XSDSchema theSchema = getSchema();
        if (theSchema.getTargetNamespace() == null ? 
              theResolvedSchema.getTargetNamespace() != null :
               !theSchema.getTargetNamespace().equals(theResolvedSchema.getTargetNamespace()) && 
                 theResolvedSchema.getTargetNamespace() != null)
        {
          createDiagnostic
            (XSDDiagnosticSeverity.ERROR_LITERAL, 
             "src-include.2", 
             theResolvedSchema.getTargetNamespace() == null ? "" : theResolvedSchema.getTargetNamespace(),
             theSchema.getTargetNamespace() == null ? "" : theSchema.getTargetNamespace());
        }
      }
    }
  }

  @Override
  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDIncludeImpl clonedInclude =
      (XSDIncludeImpl)getXSDFactory().createXSDInclude();
    clonedInclude.isReconciling = true;

    clonedInclude.setSchemaLocation(getSchemaLocation());

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedInclude.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedInclude.setElement(getElement());
    }

    clonedInclude.isReconciling = shareDOM;
    return clonedInclude;
  }
}
