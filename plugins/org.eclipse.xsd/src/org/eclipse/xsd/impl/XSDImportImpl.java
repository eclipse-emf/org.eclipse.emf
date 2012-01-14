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
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Import</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDImportImpl#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDImportImpl#getAnnotation <em>Annotation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDImportImpl 
  extends XSDSchemaDirectiveImpl 
  implements XSDImport
{
  /**
   * The default value of the '{@link #getNamespace() <em>Namespace</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNamespace()
   * @generated
   * @ordered
   */
  protected static final String NAMESPACE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNamespace() <em>Namespace</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNamespace()
   * @generated
   * @ordered
   */
  protected String namespace = NAMESPACE_EDEFAULT;

  /**
   * The cached value of the '{@link #getAnnotation() <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotation()
   * @generated
   * @ordered
   */
  protected XSDAnnotation annotation;

  public static XSDImport createImport(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.IMPORT_ELEMENT)
    {
      XSDImport xsdImport = XSDFactory.eINSTANCE.createXSDImport();
      xsdImport.setElement((Element)node);
      return xsdImport;
    }

    return null;
  }

  // protected XSDSchema importedSchema;

  @Override
  public Element createElement()
  {
    Element newElement = createElement(XSDConstants.IMPORT_ELEMENT);
    setElement(newElement);
    return newElement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDImportImpl()
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
    return XSDPackage.Literals.XSD_IMPORT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNamespace()
  {
    return namespace;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNamespace(String newNamespace)
  {
    String oldNamespace = namespace;
    namespace = newNamespace;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_IMPORT__NAMESPACE, oldNamespace, namespace));
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
        msgs = ((InternalEObject)annotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_IMPORT__ANNOTATION, null, msgs);
      if (newAnnotation != null)
        msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_IMPORT__ANNOTATION, null, msgs);
      msgs = basicSetAnnotation(newAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_IMPORT__ANNOTATION, newAnnotation, newAnnotation));
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
      case XSDPackage.XSD_IMPORT__ANNOTATION:
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_IMPORT__ANNOTATION, oldAnnotation, newAnnotation);
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
      case XSDPackage.XSD_IMPORT__NAMESPACE:
        return getNamespace();
      case XSDPackage.XSD_IMPORT__ANNOTATION:
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
      case XSDPackage.XSD_IMPORT__NAMESPACE:
        setNamespace((String)newValue);
        return;
      case XSDPackage.XSD_IMPORT__ANNOTATION:
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
      case XSDPackage.XSD_IMPORT__NAMESPACE:
        setNamespace(NAMESPACE_EDEFAULT);
        return;
      case XSDPackage.XSD_IMPORT__ANNOTATION:
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
      case XSDPackage.XSD_IMPORT__NAMESPACE:
        return NAMESPACE_EDEFAULT == null ? namespace != null : !NAMESPACE_EDEFAULT.equals(namespace);
      case XSDPackage.XSD_IMPORT__ANNOTATION:
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
    result.append(" (namespace: ");
    result.append(namespace);
    result.append(')');
    return result.toString();
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
         "element-import",
         theElement,
         new String []
         {
           XSDConstants.ID_ATTRIBUTE,
           XSDConstants.NAMESPACE_ATTRIBUTE,
           XSDConstants.SCHEMALOCATION_ATTRIBUTE,
         });

      XSDComplexTypeDefinition xsdComplexTypeDefinition =
        (XSDComplexTypeDefinition)getSchema().getSchemaForSchema().resolveElementDeclaration("import").getTypeDefinition();

      checkComplexContent(xsdComplexTypeDefinition, XSDConstants.PART1, "element-import", theElement);

      checkBuiltInTypeConstraint
        ("ID",
         null,
         XSDConstants.PART1,
         "element-import",
         theElement,
         XSDConstants.ID_ATTRIBUTE,
         false);
    }

    checkBuiltInTypeConstraint
      ("anyURI",
       getNamespace(),
       XSDConstants.PART1,
       "element-import",
       theElement,
       XSDConstants.NAMESPACE_ATTRIBUTE,
       false);

    checkBuiltInTypeConstraint
      ("anyURI",
       getSchemaLocation(),
       XSDConstants.PART1,
       "element-import",
       theElement,
       XSDConstants.SCHEMALOCATION_ATTRIBUTE,
       false);


    XSDSchema xsdSchema = getSchema();
    if (getNamespace() == null ?  xsdSchema.getTargetNamespace() == null : getNamespace().equals(xsdSchema.getTargetNamespace()))
    {
      createDiagnostic
        (XSDDiagnosticSeverity.ERROR_LITERAL,
         "src-import.1",
         getNamespace() == null ? "" : getNamespace());
    }

    XSDSchema theResolvedSchema = getResolvedSchema();
    if (theResolvedSchema == null)
    {
      createDiagnostic
        (XSDDiagnosticSeverity.WARNING_LITERAL,
         resolved ? "src-import.0" : "src-import.0.2",
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
           "src-import.2",
           getSchemaLocation() == null ? "" : getSchemaLocation());
      }
      else
      {
        if (getNamespace() == null ?
              theResolvedSchema.getTargetNamespace() != null :
              !getNamespace().equals(theResolvedSchema.getTargetNamespace()))
        {
          createDiagnostic
            (XSDDiagnosticSeverity.ERROR_LITERAL,
             "src-import.3",
             theResolvedSchema.getTargetNamespace() == null ? "" : theResolvedSchema.getTargetNamespace(),
             getNamespace() == null ? "" : getNamespace());
        }
      }
    }

    if (getNamespace() != null &&
          !getSchema().getQNamePrefixToNamespaceMap().containsValue(getNamespace()) && 
          !XSDConstants.isXMLNamespace(getNamespace()))
    {
      createDiagnostic
        (XSDDiagnosticSeverity.WARNING_LITERAL,
         "src-import.0.1",
         getNamespace() == null ? "" : getNamespace());
    }
  }

  public XSDSchema importSchema()
  {
    resolve(getNamespace(), getSchemaLocation());

    return getResolvedSchema();
  }

  @Override
  protected void handleResolvedSchema(XSDSchema xsdSchema)
  {
    if (xsdSchema == null)
    {
      setResolvedSchema(null);
    }
    else
    {
      ((XSDSchemaImpl)xsdSchema).imported(this);
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
    handleAnnotationReconciliation(XSDPackage.Literals.XSD_IMPORT__ANNOTATION, newContents, remainingContents);
  }

  @Override
  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    String newNamespace = null;
    if (changedElement.hasAttributeNS(null, XSDConstants.NAMESPACE_ATTRIBUTE))
    {
      newNamespace = changedElement.getAttributeNS(null, XSDConstants.NAMESPACE_ATTRIBUTE);
    }

    if (newNamespace == null ? getNamespace() != null : !newNamespace.equals(getNamespace()))
    {
      setNamespace(newNamespace);
    }
  }

  @Override
  protected void changeAttribute(EAttribute eAttribute)
  {
    if (isReconciling)
    {
      return;
    }

    super.changeAttribute(eAttribute);
    if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_IMPORT__NAMESPACE)
    {
      Element theElement = getElement();
      if (theElement != null)
      {
        niceSetAttribute(theElement, XSDConstants.NAMESPACE_ATTRIBUTE, getNamespace());
      }
      if (eAttribute != null && getResolvedSchema() != null && getSchema().isIncrementalUpdate())
      {
        getSchema().reset();
      }
    }
  }

  @Override
  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDImportImpl clonedImport =
      (XSDImportImpl)getXSDFactory().createXSDImport();
    clonedImport.isReconciling = true;

    clonedImport.setNamespace(getNamespace());
    clonedImport.setSchemaLocation(getSchemaLocation());

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedImport.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedImport.setElement(getElement());
    }

    clonedImport.isReconciling = shareDOM;
    return clonedImport;
  }
} 
