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
 * $Id: XSDSchemaDirectiveImpl.java,v 1.21 2008/09/15 15:10:30 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.io.IOException;
import java.io.InputStream;
import org.w3c.dom.Element;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDPlugin;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSchemaDirective;
import org.eclipse.xsd.util.XSDConstants;
import org.eclipse.xsd.util.XSDResourceImpl;
import org.eclipse.xsd.util.XSDSchemaLocationResolver;
import org.eclipse.xsd.util.XSDSchemaLocator;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Schema Directive</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaDirectiveImpl#getSchemaLocation <em>Schema Location</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaDirectiveImpl#getResolvedSchema <em>Resolved Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class XSDSchemaDirectiveImpl 
  extends XSDSchemaContentImpl 
  implements XSDSchemaDirective
{
  /**
   * The default value of the '{@link #getSchemaLocation() <em>Schema Location</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSchemaLocation()
   * @generated
   * @ordered
   */
  protected static final String SCHEMA_LOCATION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSchemaLocation() <em>Schema Location</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSchemaLocation()
   * @generated
   * @ordered
   */
  protected String schemaLocation = SCHEMA_LOCATION_EDEFAULT;

  /**
   * The cached value of the '{@link #getResolvedSchema() <em>Resolved Schema</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResolvedSchema()
   * @generated
   * @ordered
   */
  protected XSDSchema resolvedSchema;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDSchemaDirectiveImpl()
  {
    super();
  }

  @Override
  public void reset()
  {
    super.reset();
    resolved = false;
    setResolvedSchema(null);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return XSDPackage.Literals.XSD_SCHEMA_DIRECTIVE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSchemaLocation()
  {
    return schemaLocation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSchemaLocation(String newSchemaLocation)
  {
    String oldSchemaLocation = schemaLocation;
    schemaLocation = newSchemaLocation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_SCHEMA_DIRECTIVE__SCHEMA_LOCATION, oldSchemaLocation, schemaLocation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDSchema getResolvedSchema()
  {
    return resolvedSchema;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResolvedSchema(XSDSchema newResolvedSchema)
  {
    XSDSchema oldResolvedSchema = resolvedSchema;
    resolvedSchema = newResolvedSchema;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_SCHEMA_DIRECTIVE__RESOLVED_SCHEMA, oldResolvedSchema, resolvedSchema));
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
      case XSDPackage.XSD_SCHEMA_DIRECTIVE__SCHEMA_LOCATION:
        return getSchemaLocation();
      case XSDPackage.XSD_SCHEMA_DIRECTIVE__RESOLVED_SCHEMA:
        return getResolvedSchema();
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
      case XSDPackage.XSD_SCHEMA_DIRECTIVE__SCHEMA_LOCATION:
        setSchemaLocation((String)newValue);
        return;
      case XSDPackage.XSD_SCHEMA_DIRECTIVE__RESOLVED_SCHEMA:
        setResolvedSchema((XSDSchema)newValue);
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
      case XSDPackage.XSD_SCHEMA_DIRECTIVE__SCHEMA_LOCATION:
        setSchemaLocation(SCHEMA_LOCATION_EDEFAULT);
        return;
      case XSDPackage.XSD_SCHEMA_DIRECTIVE__RESOLVED_SCHEMA:
        setResolvedSchema((XSDSchema)null);
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
      case XSDPackage.XSD_SCHEMA_DIRECTIVE__SCHEMA_LOCATION:
        return SCHEMA_LOCATION_EDEFAULT == null ? schemaLocation != null : !SCHEMA_LOCATION_EDEFAULT.equals(schemaLocation);
      case XSDPackage.XSD_SCHEMA_DIRECTIVE__RESOLVED_SCHEMA:
        return resolvedSchema != null;
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
    result.append(" (schemaLocation: ");
    result.append(schemaLocation);
    result.append(')');
    return result.toString();
  }

  @Override
  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    String newSchemaLocation = null;
    if (changedElement.hasAttributeNS(null, XSDConstants.SCHEMALOCATION_ATTRIBUTE))
    {
      newSchemaLocation = changedElement.getAttributeNS(null, XSDConstants.SCHEMALOCATION_ATTRIBUTE);
    }

    if (newSchemaLocation == null ? getSchemaLocation() != null : !newSchemaLocation.equals(getSchemaLocation()))
    {
      setSchemaLocation(newSchemaLocation);
    }
  }

  @Override
  protected void changeAttribute(EAttribute eAttribute)
  {
    // Try to resolve again if the schema location changes and we previously failed.
    //
    if (resolved && getResolvedSchema() == null && eAttribute == XSDPackage.Literals.XSD_SCHEMA_DIRECTIVE__SCHEMA_LOCATION)
    {
      resolved = false;
    }

    if (!isReconciling)
    {
      super.changeAttribute(eAttribute);
      if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_SCHEMA_DIRECTIVE__SCHEMA_LOCATION)
      {
        Element theElement = getElement();
        if (theElement != null)
        {
          niceSetAttribute(theElement, XSDConstants.SCHEMALOCATION_ATTRIBUTE, getSchemaLocation());
        }
      }
    }

    if (eAttribute == XSDPackage.Literals.XSD_SCHEMA_DIRECTIVE__SCHEMA_LOCATION && getResolvedSchema() != null && getSchema().isIncrementalUpdate())
    {
      getSchema().reset();
    }
  }
  
  @Override
  protected void orphanBy(XSDSchema xsdSchema)
  {
    super.orphanBy(xsdSchema);
    if (resolved && xsdSchema.isIncrementalUpdate())
    {
      xsdSchema.reset();
    }
  }

  protected boolean resolved;
  protected void resolve(String namespace, String schemaLocation)
  {
    if (!resolved)
    {
      XSDSchema xsdSchema = getSchema();
      if (xsdSchema != null && 
            (xsdSchema.getSchemaLocation() != null ||
              ((XSDSchemaImpl)xsdSchema).getPendingSchemaLocation() != null))
      {
        Resource resource = xsdSchema.eResource();
        if (resource != null)
        {
          ResourceSet resourceSet = resource.getResourceSet();
          if (resourceSet != null)
          {
            if ("".equals(namespace))
            {
              namespace = xsdSchema.getTargetNamespace();
            }

            String resolvedSchemaLocation = resolveSchemaLocation(xsdSchema, namespace, schemaLocation);
            if (schemaLocation == null)
            {
              if (XSDConstants.isXMLNamespace(resolvedSchemaLocation))
              {
                resolvedSchemaLocation = XSDPlugin.INSTANCE.getBaseURL().toString() + "cache/www.w3.org/2001/xml.xsd";
              } 
              else if (XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001.equals(resolvedSchemaLocation))
              {
                resolvedSchemaLocation = XSDPlugin.INSTANCE.getBaseURL().toString() + "cache/www.w3.org/2001/XMLSchema.xsd";
              }
            }

            XSDSchema resolvedSchema = locateSchema(xsdSchema, namespace, schemaLocation, resolvedSchemaLocation);
            if (resolvedSchema == null)
            {
              URI uri = URI.createURI(resolvedSchemaLocation == null ? "" : resolvedSchemaLocation);
              Resource resolvedResource = resourceSet.getResource(uri, false);
              if (resolvedResource == null)
              {
                try
                {
                  InputStream inputStream = resourceSet.getURIConverter().createInputStream(uri);
                  resolvedResource = resourceSet.createResource(URI.createURI("*.xsd"));
                  resolvedResource.setURI(uri);
                  resolvedResource.load(inputStream, resourceSet.getLoadOptions());
                }
                catch (IOException exception)
                {
                  // It is generally not an error to fail to resolve.
                  // If a resource is actually created, 
                  // which happens only when we can create an input stream,
                  // then it's an error if it's not a good schema
                }
              }
              else if (!resolvedResource.isLoaded())
              {
                try
                {
                  resolvedResource.load(resourceSet.getLoadOptions());
                }
                catch (IOException exception)
                {
                  // Ignore.
                }
              }
  
              if (resolvedResource != null)
              {
                if (resolvedResource instanceof XSDResourceImpl)
                {
                  resolvedSchema = ((XSDResourceImpl)resolvedResource).getSchema();
                }
                else
                {
                  resolvedSchema = XSDFactory.eINSTANCE.createXSDSchema();
                }
              }
            }

            resolved = true;
            handleResolvedSchema(resolvedSchema);
          }
        }
      }
    }
  }

  protected XSDSchema locateSchema(XSDSchema xsdSchema, String namespace, String rawSchemaLocation, String resolvedSchemaLocation)
  {
    XSDSchemaLocator xsdSchemaLocator = (XSDSchemaLocator)EcoreUtil.getRegisteredAdapter(xsdSchema.eResource(), XSDSchemaLocator.class);
    return 
      xsdSchemaLocator == null ? 
        null : 
        xsdSchemaLocator.locateSchema(xsdSchema, namespace, rawSchemaLocation, resolvedSchemaLocation);
  }

  protected String resolveSchemaLocation(XSDSchema xsdSchema, String namespace, String schemaLocation)
  {
    for (;;)
    {
      XSDSchemaLocationResolver xsdSchemaLocationResolver = 
        (XSDSchemaLocationResolver)EcoreUtil.getRegisteredAdapter(xsdSchema, XSDSchemaLocationResolver.class);
      if (xsdSchemaLocationResolver != null)
      {
        return xsdSchemaLocationResolver.resolveSchemaLocation(xsdSchema, namespace, schemaLocation);
      }
      
      XSDConcreteComponent container = xsdSchema.getContainer();
      if (container == null)
      {
        break;
      }
      else
      {
        XSDSchema containingXSDSchema = container.getSchema();
        if (containingXSDSchema == null)
        {
          break;
        }
        else
        {
          xsdSchema = containingXSDSchema;
        }
      }
    }

    return XSDConstants.resolveSchemaLocation(xsdSchema.getSchemaLocation(), namespace, schemaLocation);
  }

  protected void handleResolvedSchema(XSDSchema xsdSchema)
  {
    // Ignore
  }
} 
