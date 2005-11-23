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
 * $Id: XSDImportImpl.java,v 1.5 2005/11/23 18:09:40 emerks Exp $
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
  protected XSDAnnotation annotation = null;

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
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case XSDPackage.XSD_IMPORT__DIAGNOSTICS:
          return ((InternalEList)getDiagnostics()).basicRemove(otherEnd, msgs);
        case XSDPackage.XSD_IMPORT__ANNOTATION:
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
      case XSDPackage.XSD_IMPORT__ELEMENT:
        return getElement();
      case XSDPackage.XSD_IMPORT__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_IMPORT__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_IMPORT__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_IMPORT__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_IMPORT__SCHEMA_LOCATION:
        return getSchemaLocation();
      case XSDPackage.XSD_IMPORT__RESOLVED_SCHEMA:
        return getResolvedSchema();
      case XSDPackage.XSD_IMPORT__NAMESPACE:
        return getNamespace();
      case XSDPackage.XSD_IMPORT__ANNOTATION:
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
      case XSDPackage.XSD_IMPORT__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_IMPORT__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_IMPORT__SCHEMA_LOCATION:
        setSchemaLocation((String)newValue);
        return;
      case XSDPackage.XSD_IMPORT__RESOLVED_SCHEMA:
        setResolvedSchema((XSDSchema)newValue);
        return;
      case XSDPackage.XSD_IMPORT__NAMESPACE:
        setNamespace((String)newValue);
        return;
      case XSDPackage.XSD_IMPORT__ANNOTATION:
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
      case XSDPackage.XSD_IMPORT__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_IMPORT__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_IMPORT__SCHEMA_LOCATION:
        setSchemaLocation(SCHEMA_LOCATION_EDEFAULT);
        return;
      case XSDPackage.XSD_IMPORT__RESOLVED_SCHEMA:
        setResolvedSchema((XSDSchema)null);
        return;
      case XSDPackage.XSD_IMPORT__NAMESPACE:
        setNamespace(NAMESPACE_EDEFAULT);
        return;
      case XSDPackage.XSD_IMPORT__ANNOTATION:
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
      case XSDPackage.XSD_IMPORT__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_IMPORT__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_IMPORT__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_IMPORT__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_IMPORT__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_IMPORT__SCHEMA_LOCATION:
        return SCHEMA_LOCATION_EDEFAULT == null ? schemaLocation != null : !SCHEMA_LOCATION_EDEFAULT.equals(schemaLocation);
      case XSDPackage.XSD_IMPORT__RESOLVED_SCHEMA:
        return resolvedSchema != null;
      case XSDPackage.XSD_IMPORT__NAMESPACE:
        return NAMESPACE_EDEFAULT == null ? namespace != null : !NAMESPACE_EDEFAULT.equals(namespace);
      case XSDPackage.XSD_IMPORT__ANNOTATION:
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
    result.append(" (namespace: ");
    result.append(namespace);
    result.append(')');
    return result.toString();
  }

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
         "src-import.0",
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

  protected void changeAttribute(EAttribute eAttribute)
  {
    if (isReconciling)
    {
      return;
    }

    super.changeAttribute(eAttribute);
    if (eAttribute == null || eAttribute == XSDPackage.eINSTANCE.getXSDImport_Namespace())
    {
      Element theElement = getElement();
      if (theElement != null)
      {
        niceSetAttribute(theElement, XSDConstants.NAMESPACE_ATTRIBUTE, getNamespace());
      }
    }
  }

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
