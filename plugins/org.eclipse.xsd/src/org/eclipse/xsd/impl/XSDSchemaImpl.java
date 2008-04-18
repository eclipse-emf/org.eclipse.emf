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
 * $Id: XSDSchemaImpl.java,v 1.42 2008/04/18 15:44:13 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MutationEvent;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeGroupDefinition;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDComponent;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDDisallowedSubstitutions;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDForm;
import org.eclipse.xsd.XSDIdentityConstraintDefinition;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDInclude;
import org.eclipse.xsd.XSDModelGroupDefinition;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDNotationDeclaration;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDPlugin;
import org.eclipse.xsd.XSDProhibitedSubstitutions;
import org.eclipse.xsd.XSDRedefine;
import org.eclipse.xsd.XSDRedefineContent;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSchemaCompositor;
import org.eclipse.xsd.XSDSchemaContent;
import org.eclipse.xsd.XSDSchemaDirective;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;
import org.eclipse.xsd.util.XSDParser;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;
import org.eclipse.xsd.util.XSDResourceImpl;
import org.eclipse.xsd.util.XSDSwitch;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Schema</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getDocument <em>Document</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getSchemaLocation <em>Schema Location</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getTargetNamespace <em>Target Namespace</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getAttributeFormDefault <em>Attribute Form Default</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getElementFormDefault <em>Element Form Default</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getFinalDefault <em>Final Default</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getBlockDefault <em>Block Default</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getContents <em>Contents</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getElementDeclarations <em>Element Declarations</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getAttributeDeclarations <em>Attribute Declarations</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getAttributeGroupDefinitions <em>Attribute Group Definitions</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getTypeDefinitions <em>Type Definitions</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getModelGroupDefinitions <em>Model Group Definitions</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getIdentityConstraintDefinitions <em>Identity Constraint Definitions</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getNotationDeclarations <em>Notation Declarations</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getAllDiagnostics <em>All Diagnostics</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getReferencingDirectives <em>Referencing Directives</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getRootVersion <em>Root Version</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getOriginalVersion <em>Original Version</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getIncorporatedVersions <em>Incorporated Versions</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDSchemaImpl#getSchemaForSchema <em>Schema For Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDSchemaImpl 
  extends XSDScopeImpl 
  implements XSDSchema
{
  /**
   * The default value of the '{@link #getDocument() <em>Document</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDocument()
   * @generated
   * @ordered
   */
  protected static final Document DOCUMENT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDocument() <em>Document</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDocument()
   * @generated
   * @ordered
   */
  protected Document document = DOCUMENT_EDEFAULT;

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
   * The default value of the '{@link #getTargetNamespace() <em>Target Namespace</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetNamespace()
   * @generated
   * @ordered
   */
  protected static final String TARGET_NAMESPACE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTargetNamespace() <em>Target Namespace</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetNamespace()
   * @generated
   * @ordered
   */
  protected String targetNamespace = TARGET_NAMESPACE_EDEFAULT;

  /**
   * The default value of the '{@link #getAttributeFormDefault() <em>Attribute Form Default</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttributeFormDefault()
   * @generated
   * @ordered
   */
  protected static final XSDForm ATTRIBUTE_FORM_DEFAULT_EDEFAULT = XSDForm.UNQUALIFIED_LITERAL;

  /**
   * The offset of the flags representing the value of the '{@link #getAttributeFormDefault() <em>Attribute Form Default</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int ATTRIBUTE_FORM_DEFAULT_EFLAG_OFFSET = 8;

  /**
   * The flags representing the default value of the '{@link #getAttributeFormDefault() <em>Attribute Form Default</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int ATTRIBUTE_FORM_DEFAULT_EFLAG_DEFAULT = ATTRIBUTE_FORM_DEFAULT_EDEFAULT.ordinal() << ATTRIBUTE_FORM_DEFAULT_EFLAG_OFFSET;

  /**
   * The array of enumeration values for '{@link XSDForm Form}'
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  private static final XSDForm[] ATTRIBUTE_FORM_DEFAULT_EFLAG_VALUES = XSDForm.values();

  /**
   * The flag representing the value of the '{@link #getAttributeFormDefault() <em>Attribute Form Default</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttributeFormDefault()
   * @generated
   * @ordered
   */
  protected static final int ATTRIBUTE_FORM_DEFAULT_EFLAG = 1 << ATTRIBUTE_FORM_DEFAULT_EFLAG_OFFSET;

  /**
   * The flag representing whether the Attribute Form Default attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int ATTRIBUTE_FORM_DEFAULT_ESETFLAG = 1 << 9;

  /**
   * The default value of the '{@link #getElementFormDefault() <em>Element Form Default</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElementFormDefault()
   * @generated
   * @ordered
   */
  protected static final XSDForm ELEMENT_FORM_DEFAULT_EDEFAULT = XSDForm.UNQUALIFIED_LITERAL;

  /**
   * The offset of the flags representing the value of the '{@link #getElementFormDefault() <em>Element Form Default</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int ELEMENT_FORM_DEFAULT_EFLAG_OFFSET = 10;

  /**
   * The flags representing the default value of the '{@link #getElementFormDefault() <em>Element Form Default</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int ELEMENT_FORM_DEFAULT_EFLAG_DEFAULT = ELEMENT_FORM_DEFAULT_EDEFAULT.ordinal() << ELEMENT_FORM_DEFAULT_EFLAG_OFFSET;

  /**
   * The array of enumeration values for '{@link XSDForm Form}'
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  private static final XSDForm[] ELEMENT_FORM_DEFAULT_EFLAG_VALUES = XSDForm.values();

  /**
   * The flag representing the value of the '{@link #getElementFormDefault() <em>Element Form Default</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElementFormDefault()
   * @generated
   * @ordered
   */
  protected static final int ELEMENT_FORM_DEFAULT_EFLAG = 1 << ELEMENT_FORM_DEFAULT_EFLAG_OFFSET;

  /**
   * The flag representing whether the Element Form Default attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int ELEMENT_FORM_DEFAULT_ESETFLAG = 1 << 11;

  /**
   * The cached value of the '{@link #getFinalDefault() <em>Final Default</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFinalDefault()
   * @generated
   * @ordered
   */
  protected EList<XSDProhibitedSubstitutions> finalDefault;

  /**
   * The cached value of the '{@link #getBlockDefault() <em>Block Default</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBlockDefault()
   * @generated
   * @ordered
   */
  protected EList<XSDDisallowedSubstitutions> blockDefault;

  /**
   * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVersion()
   * @generated
   * @ordered
   */
  protected static final String VERSION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVersion()
   * @generated
   * @ordered
   */
  protected String version = VERSION_EDEFAULT;

  /**
   * The cached value of the '{@link #getContents() <em>Contents</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContents()
   * @generated
   * @ordered
   */
  protected EList<XSDSchemaContent> contents;

  /**
   * The cached value of the '{@link #getElementDeclarations() <em>Element Declarations</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElementDeclarations()
   * @generated
   * @ordered
   */
  protected EList<XSDElementDeclaration> elementDeclarations;

  /**
   * The cached value of the '{@link #getAttributeDeclarations() <em>Attribute Declarations</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttributeDeclarations()
   * @generated
   * @ordered
   */
  protected EList<XSDAttributeDeclaration> attributeDeclarations;

  /**
   * The cached value of the '{@link #getAttributeGroupDefinitions() <em>Attribute Group Definitions</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAttributeGroupDefinitions()
   * @generated
   * @ordered
   */
  protected EList<XSDAttributeGroupDefinition> attributeGroupDefinitions;

  /**
   * The cached value of the '{@link #getTypeDefinitions() <em>Type Definitions</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeDefinitions()
   * @generated
   * @ordered
   */
  protected EList<XSDTypeDefinition> typeDefinitions;

  /**
   * The cached value of the '{@link #getModelGroupDefinitions() <em>Model Group Definitions</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelGroupDefinitions()
   * @generated
   * @ordered
   */
  protected EList<XSDModelGroupDefinition> modelGroupDefinitions;

  /**
   * The cached value of the '{@link #getIdentityConstraintDefinitions() <em>Identity Constraint Definitions</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdentityConstraintDefinitions()
   * @generated
   * @ordered
   */
  protected EList<XSDIdentityConstraintDefinition> identityConstraintDefinitions;

  /**
   * The cached value of the '{@link #getNotationDeclarations() <em>Notation Declarations</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNotationDeclarations()
   * @generated
   * @ordered
   */
  protected EList<XSDNotationDeclaration> notationDeclarations;

  /**
   * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotations()
   * @generated
   * @ordered
   */
  protected EList<XSDAnnotation> annotations;

  /**
   * The cached value of the '{@link #getAllDiagnostics() <em>All Diagnostics</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAllDiagnostics()
   * @generated
   * @ordered
   */
  protected EList<XSDDiagnostic> allDiagnostics;

  /**
   * The cached value of the '{@link #getReferencingDirectives() <em>Referencing Directives</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferencingDirectives()
   * @generated
   * @ordered
   */
  protected EList<XSDSchemaDirective> referencingDirectives;

  /**
   * The cached value of the '{@link #getIncorporatedVersions() <em>Incorporated Versions</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIncorporatedVersions()
   * @generated
   * @ordered
   */
  protected EList<XSDSchema> incorporatedVersions;

  public static XSDSchema createSchema(Node node)
  {
    XSDSchema xsdSchema = XSDFactory.eINSTANCE.createXSDSchema();

    // if (XSDConstants.nodeType(node) == XSDConstants.SCHEMA_ELEMENT)
    {
      // Force the loading of the "meta" schema for schema instance instance.
      //
      String schemaForSchemaNamespace = node.getNamespaceURI();
      getSchemaForSchema(schemaForSchemaNamespace);

      xsdSchema.setElement((Element)node);
    }

    return xsdSchema;
  }

  public static synchronized XSDSchema createMetaSchema(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.SCHEMA_ELEMENT)
    {
      XSDSchema xsdSchema = XSDFactory.eINSTANCE.createXSDSchema();

      String schemaForSchemaNamespace = node.getNamespaceURI();
      if (XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001.equals(schemaForSchemaNamespace))
      {
        if (xsdMagicSchemaForSchema2001 == null)
        {
          xsdMagicSchemaForSchema2001 = xsdSchema;
        }
        else if (xsdSchemaForSchema2001 == null)
        {
          xsdSchemaForSchema2001 = xsdSchema;
        }
        else
        {
          // System.out.println("Bad meta loading.");
        }
      }
      else if (XSDConstants.SCHEMA_FOR_SCHEMA_URI_2000_10.equals(schemaForSchemaNamespace))
      {
        if (xsdMagicSchemaForSchema2000_10 == null)
        {
          xsdMagicSchemaForSchema2000_10 = xsdSchema;
        }
        else if (xsdSchemaForSchema2000_10 == null)
        {
          xsdSchemaForSchema2000_10 = xsdSchema;
        }
        else
        {
          // System.out.println("Bad meta loading.");
        }
      }
      else if (XSDConstants.SCHEMA_FOR_SCHEMA_URI_1999.equals(schemaForSchemaNamespace))
      {
        if (xsdMagicSchemaForSchema1999 == null)
        {
          xsdMagicSchemaForSchema1999 = xsdSchema;
        }
        else if (xsdSchemaForSchema1999 == null)
        {
          xsdSchemaForSchema1999 = xsdSchema;
        }
        else
        {
          // System.out.println("Bad meta loading.");
        }
      }


      xsdSchema.setElement((Element)node);
      return xsdSchema;
    }

    return null;
  }

  protected static ResourceSet globalResourceSet;

  protected static XSDSchema xsdMagicSchemaForSchema2001;
  protected static XSDSchema xsdSchemaForSchema2001;
  protected static XSDSchema xsdSchemaInstance2001;

  protected static XSDSchema xsdMagicSchemaForSchema2000_10;
  protected static XSDSchema xsdSchemaForSchema2000_10;

  protected static XSDSchema xsdMagicSchemaForSchema1999;
  protected static XSDSchema xsdSchemaForSchema1999;

  public static synchronized ResourceSet getGlobalResourceSet()
  {
    if (globalResourceSet == null)
    {
      globalResourceSet = createResourceSet();

      String baseURL = XSDPlugin.INSTANCE.getBaseURL().toString();
      globalResourceSet.getURIConverter().getURIMap().put
        (URI.createURI("http://www.w3.org/2001/xml.xsd"), 
         URI.createURI(baseURL + "cache/www.w3.org/2001/xml.xsd"));
    }

    return globalResourceSet;
  }

  public static ResourceSet createResourceSet()
  {
    ResourceSet result = new ResourceSetImpl();
    result.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());
    return result;
  }

  public static synchronized XSDSchema getMagicSchemaForSchema(String namespace)
  {
    if (XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001.equals(namespace))
    {
      if (xsdMagicSchemaForSchema2001 == null)
      {
        try
        {
          String baseURL = XSDPlugin.INSTANCE.getBaseURL().toString();
          getGlobalResourceSet().getLoadOptions().put("XSD_MAGIC_XML_SCHEMA", XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);
          getGlobalResourceSet().getResource
            (URI.createURI(baseURL + "cache/www.w3.org/2001/MagicXMLSchema.xsd"), true);
        }
        catch (Exception exception)
        {
          exception.printStackTrace();
        }
        finally
        {
          getGlobalResourceSet().getLoadOptions().remove("XSD_MAGIC_XML_SCHEMA");
        }
      }

      return xsdMagicSchemaForSchema2001;
    }
    else if (XSDConstants.SCHEMA_FOR_SCHEMA_URI_2000_10.equals(namespace))
    {
      if (xsdMagicSchemaForSchema2000_10 == null)
      {
        try
        {
          String baseURL = XSDPlugin.INSTANCE.getBaseURL().toString();
          getGlobalResourceSet().getLoadOptions().put("XSD_MAGIC_XML_SCHEMA", XSDConstants.SCHEMA_FOR_SCHEMA_URI_2000_10);
          getGlobalResourceSet().getResource
            (URI.createURI(baseURL + "cache/www.w3.org/2000/10/MagicXMLSchema.xsd"), true);
        }
        catch (Exception exception)
        {
          exception.printStackTrace();
        }
        finally
        {
          getGlobalResourceSet().getLoadOptions().remove("XSD_MAGIC_XML_SCHEMA");
        }
      }

      return xsdMagicSchemaForSchema2000_10;
    }
    else if (XSDConstants.SCHEMA_FOR_SCHEMA_URI_1999.equals(namespace))
    {
      if (xsdMagicSchemaForSchema1999 == null)
      {
        try
        {
          String baseURL = XSDPlugin.INSTANCE.getBaseURL().toString();
          getGlobalResourceSet().getLoadOptions().put("XSD_MAGIC_XML_SCHEMA", XSDConstants.SCHEMA_FOR_SCHEMA_URI_1999);
          getGlobalResourceSet().getResource
            (URI.createURI(baseURL + "cache/www.w3.org/1999/MagicXMLSchema.xsd"), true);
        }
        catch (Exception exception)
        {
          exception.printStackTrace();
        }
        finally
        {
          getGlobalResourceSet().getLoadOptions().remove("XSD_MAGIC_XML_SCHEMA");
        }
      }

      return xsdMagicSchemaForSchema1999;
    }

    return null;
  }

  public static synchronized XSDSchema getSchemaForSchema(String namespace)
  {
    if (XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001.equals(namespace))
    {
      if (xsdSchemaForSchema2001 == null)
      {
        try
        {
          String baseURL = XSDPlugin.INSTANCE.getBaseURL().toString();
          getMagicSchemaForSchema(namespace);
          getGlobalResourceSet().getLoadOptions().put("XSD_XML_SCHEMA", XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);
          getGlobalResourceSet().getResource
            (URI.createURI(baseURL + "cache/www.w3.org/2001/XMLSchema.xsd"), true);
        }
        catch (Exception exception)
        {
          exception.printStackTrace();
        }
        finally
        {
          getGlobalResourceSet().getLoadOptions().remove("XSD_XML_SCHEMA");
        }
      }
      return xsdSchemaForSchema2001;
    }
    else if (XSDConstants.SCHEMA_FOR_SCHEMA_URI_2000_10.equals(namespace))
    {
      if (xsdSchemaForSchema2000_10 == null)
      {
        try
        {
          String baseURL = XSDPlugin.INSTANCE.getBaseURL().toString();
          getMagicSchemaForSchema(namespace);
          getGlobalResourceSet().getLoadOptions().put("XSD_XML_SCHEMA", XSDConstants.SCHEMA_FOR_SCHEMA_URI_2000_10);
          getGlobalResourceSet().getResource
            (URI.createURI(baseURL + "cache/www.w3.org/2000/10/XMLSchema.xsd"), true);
        }
        catch (Exception exception)
        {
          exception.printStackTrace();
        }
        finally
        {
          getGlobalResourceSet().getLoadOptions().remove("XSD_XML_SCHEMA");
        }
      }
      return xsdSchemaForSchema2000_10;
    }
    else if (XSDConstants.SCHEMA_FOR_SCHEMA_URI_1999.equals(namespace))
    {
      if (xsdSchemaForSchema1999 == null)
      {
        try
        {
          String baseURL = XSDPlugin.INSTANCE.getBaseURL().toString();
          getMagicSchemaForSchema(namespace);
          getGlobalResourceSet().getLoadOptions().put("XSD_XML_SCHEMA", XSDConstants.SCHEMA_FOR_SCHEMA_URI_1999);
          getGlobalResourceSet().getResource
            (URI.createURI(baseURL + "cache/www.w3.org/1999/XMLSchema.xsd"), true);
        }
        catch (Exception exception)
        {
          exception.printStackTrace();
        }
        finally
        {
          getGlobalResourceSet().getLoadOptions().remove("XSD_XML_SCHEMA");
        }
      }
      return xsdSchemaForSchema1999;
    }
    return null;
  }

  public static synchronized XSDSchema getSchemaInstance(String namespace)
  {
    if (XSDConstants.SCHEMA_INSTANCE_URI_2001.equals(namespace))
    {
      if (xsdSchemaInstance2001 == null)
      {
        try
        {
          String baseURL = XSDPlugin.INSTANCE.getBaseURL().toString();
          getSchemaForSchema(XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);
          getGlobalResourceSet().getLoadOptions().put("XSD_SCHEMA_INSTANCE", XSDConstants.SCHEMA_INSTANCE_URI_2001);
          Resource schemaInstance2001Resource = 
            getGlobalResourceSet().getResource
              (URI.createURI(baseURL + "cache/www.w3.org/2001/XMLSchema-instance.xsd"), true);
          xsdSchemaInstance2001 = ((XSDResourceImpl)schemaInstance2001Resource).getSchema();
        }
        catch (Exception exception)
        {
          exception.printStackTrace();
        }
        finally
        {
          getGlobalResourceSet().getLoadOptions().remove("XSD_SCHEMA_INSTANCE");
        }
      }
      return xsdSchemaInstance2001;
    }
    return null;
  }

  protected Map<String, String> qNamePrefixToNamespaceMap = 
    new HashMap<String, String>()
    {
      private static final long serialVersionUID = 1L;

      @Override
      public String put(String key, String value)
      {
        if ("".equals(key))
        {
          throw new IllegalArgumentException("The default prefix should represented as null rather than as a zero length String");
        }

        if (value == null)
        {
          value = "";
        }
        String result = super.put(key, value);
        if (!isReconciling)
        {
          Element theElement = getElement();
          if (theElement != null)
          {
              String xmlnsAttribute = "xmlns";
              if (key != null)
              {
                xmlnsAttribute += ":" + key;
              }
              theElement.setAttributeNS(XSDConstants.XMLNS_URI_2000, xmlnsAttribute, value);
          }
        }
        return result;
      }

      @Override
      public String get(Object key) 
      {
        String result = super.get(key);
        return "".equals(result) ? null : result;
      }

      @Override
      public String remove(Object key) 
      {
        String result = super.remove(key);
        if (!isReconciling)
        {
          if (result != null)
          {
            if (key == null)
            {
              key = "xmlns";
            }
            Element theElement = getElement();
            if (theElement != null &&
                  theElement.hasAttributeNS(XSDConstants.XMLNS_URI_2000, (String)key))
            {
              theElement.removeAttributeNS(XSDConstants.XMLNS_URI_2000, (String)key);
            }
          }
        }
        return result;
      }
    };

  protected String schemaForSchemaQNamePrefix;

  protected boolean hasRetargetedNamespace = false;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDSchemaImpl()
  {
    super();
    eFlags |= ATTRIBUTE_FORM_DEFAULT_EFLAG_DEFAULT;
    eFlags |= ELEMENT_FORM_DEFAULT_EFLAG_DEFAULT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return XSDPackage.Literals.XSD_SCHEMA;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Document getDocument()
  {
    return document;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDocument(Document newDocument)
  {
    Document oldDocument = document;
    document = newDocument;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_SCHEMA__DOCUMENT, oldDocument, document));
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
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_SCHEMA__SCHEMA_LOCATION, oldSchemaLocation, schemaLocation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTargetNamespace()
  {
    return targetNamespace;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetNamespace(String newTargetNamespace)
  {
    String oldTargetNamespace = targetNamespace;
    targetNamespace = newTargetNamespace;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_SCHEMA__TARGET_NAMESPACE, oldTargetNamespace, targetNamespace));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDForm getAttributeFormDefault()
  {
    return ATTRIBUTE_FORM_DEFAULT_EFLAG_VALUES[(eFlags & ATTRIBUTE_FORM_DEFAULT_EFLAG) >>> ATTRIBUTE_FORM_DEFAULT_EFLAG_OFFSET];
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAttributeFormDefault(XSDForm newAttributeFormDefault)
  {
    XSDForm oldAttributeFormDefault = ATTRIBUTE_FORM_DEFAULT_EFLAG_VALUES[(eFlags & ATTRIBUTE_FORM_DEFAULT_EFLAG) >>> ATTRIBUTE_FORM_DEFAULT_EFLAG_OFFSET];
    if (newAttributeFormDefault == null) newAttributeFormDefault = ATTRIBUTE_FORM_DEFAULT_EDEFAULT;
    eFlags = eFlags & ~ATTRIBUTE_FORM_DEFAULT_EFLAG | newAttributeFormDefault.ordinal() << ATTRIBUTE_FORM_DEFAULT_EFLAG_OFFSET;
    boolean oldAttributeFormDefaultESet = (eFlags & ATTRIBUTE_FORM_DEFAULT_ESETFLAG) != 0;
    eFlags |= ATTRIBUTE_FORM_DEFAULT_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_SCHEMA__ATTRIBUTE_FORM_DEFAULT, oldAttributeFormDefault, newAttributeFormDefault, !oldAttributeFormDefaultESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetAttributeFormDefault()
  {
    XSDForm oldAttributeFormDefault = ATTRIBUTE_FORM_DEFAULT_EFLAG_VALUES[(eFlags & ATTRIBUTE_FORM_DEFAULT_EFLAG) >>> ATTRIBUTE_FORM_DEFAULT_EFLAG_OFFSET];
    boolean oldAttributeFormDefaultESet = (eFlags & ATTRIBUTE_FORM_DEFAULT_ESETFLAG) != 0;
    eFlags = eFlags & ~ATTRIBUTE_FORM_DEFAULT_EFLAG | ATTRIBUTE_FORM_DEFAULT_EFLAG_DEFAULT;
    eFlags &= ~ATTRIBUTE_FORM_DEFAULT_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, XSDPackage.XSD_SCHEMA__ATTRIBUTE_FORM_DEFAULT, oldAttributeFormDefault, ATTRIBUTE_FORM_DEFAULT_EDEFAULT, oldAttributeFormDefaultESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetAttributeFormDefault()
  {
    return (eFlags & ATTRIBUTE_FORM_DEFAULT_ESETFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDForm getElementFormDefault()
  {
    return ELEMENT_FORM_DEFAULT_EFLAG_VALUES[(eFlags & ELEMENT_FORM_DEFAULT_EFLAG) >>> ELEMENT_FORM_DEFAULT_EFLAG_OFFSET];
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setElementFormDefault(XSDForm newElementFormDefault)
  {
    XSDForm oldElementFormDefault = ELEMENT_FORM_DEFAULT_EFLAG_VALUES[(eFlags & ELEMENT_FORM_DEFAULT_EFLAG) >>> ELEMENT_FORM_DEFAULT_EFLAG_OFFSET];
    if (newElementFormDefault == null) newElementFormDefault = ELEMENT_FORM_DEFAULT_EDEFAULT;
    eFlags = eFlags & ~ELEMENT_FORM_DEFAULT_EFLAG | newElementFormDefault.ordinal() << ELEMENT_FORM_DEFAULT_EFLAG_OFFSET;
    boolean oldElementFormDefaultESet = (eFlags & ELEMENT_FORM_DEFAULT_ESETFLAG) != 0;
    eFlags |= ELEMENT_FORM_DEFAULT_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_SCHEMA__ELEMENT_FORM_DEFAULT, oldElementFormDefault, newElementFormDefault, !oldElementFormDefaultESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetElementFormDefault()
  {
    XSDForm oldElementFormDefault = ELEMENT_FORM_DEFAULT_EFLAG_VALUES[(eFlags & ELEMENT_FORM_DEFAULT_EFLAG) >>> ELEMENT_FORM_DEFAULT_EFLAG_OFFSET];
    boolean oldElementFormDefaultESet = (eFlags & ELEMENT_FORM_DEFAULT_ESETFLAG) != 0;
    eFlags = eFlags & ~ELEMENT_FORM_DEFAULT_EFLAG | ELEMENT_FORM_DEFAULT_EFLAG_DEFAULT;
    eFlags &= ~ELEMENT_FORM_DEFAULT_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, XSDPackage.XSD_SCHEMA__ELEMENT_FORM_DEFAULT, oldElementFormDefault, ELEMENT_FORM_DEFAULT_EDEFAULT, oldElementFormDefaultESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetElementFormDefault()
  {
    return (eFlags & ELEMENT_FORM_DEFAULT_ESETFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDProhibitedSubstitutions> getFinalDefault()
  {
    if (finalDefault == null)
    {
      finalDefault = new EDataTypeUniqueEList.Unsettable<XSDProhibitedSubstitutions>(XSDProhibitedSubstitutions.class, this, XSDPackage.XSD_SCHEMA__FINAL_DEFAULT);
    }
    return finalDefault;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetFinalDefault()
  {
    if (finalDefault != null) ((InternalEList.Unsettable<?>)finalDefault).unset();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetFinalDefault()
  {
    return finalDefault != null && ((InternalEList.Unsettable<?>)finalDefault).isSet();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDDisallowedSubstitutions> getBlockDefault()
  {
    if (blockDefault == null)
    {
      blockDefault = new EDataTypeUniqueEList.Unsettable<XSDDisallowedSubstitutions>(XSDDisallowedSubstitutions.class, this, XSDPackage.XSD_SCHEMA__BLOCK_DEFAULT);
    }
    return blockDefault;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetBlockDefault()
  {
    if (blockDefault != null) ((InternalEList.Unsettable<?>)blockDefault).unset();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetBlockDefault()
  {
    return blockDefault != null && ((InternalEList.Unsettable<?>)blockDefault).isSet();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getVersion()
  {
    return version;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVersion(String newVersion)
  {
    String oldVersion = version;
    version = newVersion;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_SCHEMA__VERSION, oldVersion, version));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDSchemaContent> getContents()
  {
    if (contents == null)
    {
      contents = new EObjectContainmentEList<XSDSchemaContent>(XSDSchemaContent.class, this, XSDPackage.XSD_SCHEMA__CONTENTS);
    }
    return contents;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList<XSDElementDeclaration> getElementDeclarations()
  {
    if (elementDeclarations == null)
    {
      elementDeclarations = new XSDNamedComponentImpl.XSDNamedComponentList<XSDElementDeclaration>(XSDElementDeclaration.class, this, XSDPackage.XSD_SCHEMA__ELEMENT_DECLARATIONS);
    }
    return elementDeclarations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList<XSDAttributeDeclaration> getAttributeDeclarations()
  {
    if (attributeDeclarations == null)
    {
      attributeDeclarations = new XSDNamedComponentImpl.XSDNamedComponentList<XSDAttributeDeclaration>(XSDAttributeDeclaration.class, this, XSDPackage.XSD_SCHEMA__ATTRIBUTE_DECLARATIONS);
    }
    return attributeDeclarations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList<XSDAttributeGroupDefinition> getAttributeGroupDefinitions()
  {
    if (attributeGroupDefinitions == null)
    {
      attributeGroupDefinitions = new XSDNamedComponentImpl.XSDNamedComponentList<XSDAttributeGroupDefinition>(XSDAttributeGroupDefinition.class, this, XSDPackage.XSD_SCHEMA__ATTRIBUTE_GROUP_DEFINITIONS);
    }
    return attributeGroupDefinitions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList<XSDTypeDefinition> getTypeDefinitions()
  {
    if (typeDefinitions == null)
    {
      typeDefinitions = new XSDNamedComponentImpl.XSDNamedComponentList<XSDTypeDefinition>(XSDTypeDefinition.class, this, XSDPackage.XSD_SCHEMA__TYPE_DEFINITIONS);
    }
    return typeDefinitions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList<XSDModelGroupDefinition> getModelGroupDefinitions()
  {
    if (modelGroupDefinitions == null)
    {
      modelGroupDefinitions = new XSDNamedComponentImpl.XSDNamedComponentList<XSDModelGroupDefinition>(XSDModelGroupDefinition.class, this, XSDPackage.XSD_SCHEMA__MODEL_GROUP_DEFINITIONS);
    }
    return modelGroupDefinitions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList<XSDIdentityConstraintDefinition> getIdentityConstraintDefinitions()
  {
    if (identityConstraintDefinitions == null)
    {
      identityConstraintDefinitions = new XSDNamedComponentImpl.XSDNamedComponentList<XSDIdentityConstraintDefinition>(XSDIdentityConstraintDefinition.class, this, XSDPackage.XSD_SCHEMA__IDENTITY_CONSTRAINT_DEFINITIONS);
    }
    return identityConstraintDefinitions;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList<XSDNotationDeclaration> getNotationDeclarations()
  {
    if (notationDeclarations == null)
    {
      notationDeclarations = new XSDNamedComponentImpl.XSDNamedComponentList<XSDNotationDeclaration>(XSDNotationDeclaration.class, this, XSDPackage.XSD_SCHEMA__NOTATION_DECLARATIONS);
    }
    return notationDeclarations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDAnnotation> getAnnotations()
  {
    if (annotations == null)
    {
      annotations = new EObjectEList<XSDAnnotation>(XSDAnnotation.class, this, XSDPackage.XSD_SCHEMA__ANNOTATIONS);
    }
    return annotations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDDiagnostic> getAllDiagnostics()
  {
    if (allDiagnostics == null)
    {
      allDiagnostics = new EObjectEList<XSDDiagnostic>(XSDDiagnostic.class, this, XSDPackage.XSD_SCHEMA__ALL_DIAGNOSTICS);
    }
    return allDiagnostics;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDSchemaDirective> getReferencingDirectives()
  {
    if (referencingDirectives == null)
    {
      referencingDirectives = new EObjectEList<XSDSchemaDirective>(XSDSchemaDirective.class, this, XSDPackage.XSD_SCHEMA__REFERENCING_DIRECTIVES);
    }
    return referencingDirectives;
  }

  public boolean hasRetargetedNamespace()
  {
    return hasRetargetedNamespace;
  }

  public XSDSchema getOriginalVersion()
  {
    for (XSDSchemaDirective xsdSchemaDirective : getReferencingDirectives())
    {
      if (xsdSchemaDirective instanceof XSDSchemaCompositor)
      {
        XSDSchemaCompositor xsdSchemaCompositor = (XSDSchemaCompositor)xsdSchemaDirective;
        if (xsdSchemaCompositor.getResolvedSchema() != xsdSchemaCompositor.getIncorporatedSchema())
        {
          return xsdSchemaCompositor.getResolvedSchema();
        }
      }
    }
    return this;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDSchema> getIncorporatedVersions()
  {
    if (incorporatedVersions == null)
    {
      incorporatedVersions = new EObjectContainmentEList<XSDSchema>(XSDSchema.class, this, XSDPackage.XSD_SCHEMA__INCORPORATED_VERSIONS);
    }
    return incorporatedVersions;
  }

/*
  public Set getIncorporatedVersions()
  {
    Set result = new HashSet();
    for (Iterator i = getReferencingDirectives().iterator(); i.hasNext(); )
    {
      XSDSchemaDirective xsdSchemaDirective = (XSDSchemaDirective)i.next();
      if (xsdSchemaDirective instanceof XSDSchemaCompositor)
      {
        XSDSchemaCompositor xsdSchemaCompositor = (XSDSchemaCompositor)xsdSchemaDirective;
        if (xsdSchemaCompositor.getResolvedSchema() != xsdSchemaCompositor.getIncorporatedSchema())
        {
          result.add(xsdSchemaCompositor.getIncorporatedSchema());
        }
      }
    }
    return result;
  }
*/

  public XSDSchema getRootVersion()
  {
    XSDSchema result = this;
    for (XSDSchema originalVersion = result.getOriginalVersion(); 
         result != originalVersion;
         originalVersion = result.getOriginalVersion())
    {
      result = originalVersion;
    }
    return result;
  }

  /**
   * This returns the map defined by the xmlns attributes of the underlying XML representation.
   */
  public Map<String, String> getQNamePrefixToNamespaceMap()
  {
    return qNamePrefixToNamespaceMap;
  }

  /**
   * This returns the QName prefix used to reference the schema for schema;
   * it must be a key in {@link #getQNamePrefixToNamespaceMap}.
   */
  public String getSchemaForSchemaQNamePrefix()
  {
    return schemaForSchemaQNamePrefix;
  }

  /**
   * This sets the QName prefix used to reference the schema for schema;
   * it must be a key in {@link #getQNamePrefixToNamespaceMap} that maps to a valid schema for schema namespace.
   * It can also be used to rename the prefix, one it's established.
   */
  public void setSchemaForSchemaQNamePrefix(String qNamePrefix)
  {
    String oldSchemaForSchemaQNamePrefix = schemaForSchemaQNamePrefix;
    String oldSchemaForSchemaNamespace = getSchemaForSchemaNamespace();
    this.schemaForSchemaQNamePrefix = qNamePrefix;
    String newSchemaForSchemaNamespace = getSchemaForSchemaNamespace();

    // Rename it if there was an old one but no new one.
    //
    if (XSDConstants.isSchemaForSchemaNamespace(oldSchemaForSchemaNamespace) && 
        !XSDConstants.isSchemaForSchemaNamespace(newSchemaForSchemaNamespace))
    {
      getQNamePrefixToNamespaceMap().remove(oldSchemaForSchemaQNamePrefix);
      getQNamePrefixToNamespaceMap().put(qNamePrefix, oldSchemaForSchemaNamespace);

      if (getElement() != null && XSDConstants.isSchemaForSchemaNamespace(getSchemaForSchemaNamespace()))
      {
        updatePrefix(getElement(), getSchemaForSchemaNamespace(), oldSchemaForSchemaQNamePrefix, qNamePrefix);
        updateElement();
      }
    }
  }

  /**
   * This returns (String)getQNamePrefixToNamespaceMap().get(getSchemaForSchemaQNamePrefix());
   * you can't set this directly.
   */
  public String getSchemaForSchemaNamespace()
  {
    return getQNamePrefixToNamespaceMap().get(getSchemaForSchemaQNamePrefix());
  }

  public String getStringBlockDefault()
  {
    if (isSetBlockDefault())
    {
      StringBuffer result = new StringBuffer();
      for (Object literal  : getBlockDefault())
      {
        if (result.length() != 0)
        {
          result.append(' ');
        }
        if (literal.toString().equals("all"))
        {
          result.append("#all");
        }
        else
        {
          result.append(literal);
        }
      }
      return result.toString();
    }
    else
    {
      return null;
    }
  }

  public void setStringBlockDefault(String blockDefault)
  {
    if (blockDefault == null)
    {
      unsetBlockDefault();
    }
    else
    {
      List<XSDDisallowedSubstitutions> newBlockDefault = new ArrayList<XSDDisallowedSubstitutions>();
      for (StringTokenizer stringTokenizer = new StringTokenizer(blockDefault); stringTokenizer.hasMoreTokens(); )
      {
        String token = stringTokenizer.nextToken();
        if (token.equals("#all"))
        {
          token = "all";
        }
        XSDDisallowedSubstitutions literal = XSDDisallowedSubstitutions.get(token);
        if (literal != null)
        {
          newBlockDefault.add(literal);
        }
      }
      if (!newBlockDefault.equals(getBlockDefault()))
      {
        Collection<XSDDisallowedSubstitutions> oldContents = new ArrayList<XSDDisallowedSubstitutions>(getBlockDefault());
        oldContents.removeAll(newBlockDefault);
        getBlockDefault().removeAll(oldContents);
        setListContentAndOrder(getBlockDefault(), newBlockDefault);
      }
      else if (newBlockDefault.isEmpty() && !isSetBlockDefault())
      {
        getBlockDefault().clear();
      }
    }
  }

  public String getStringFinalDefault()
  {
    if (isSetFinalDefault())
    {
      StringBuffer result = new StringBuffer();
      for (Object literal : getFinalDefault())
      {
        if (result.length() != 0)
        {
          result.append(' ');
        }
        if (literal.toString().equals("all"))
        {
          result.append("#all");
        }
        else
        {
          result.append(literal);
        }
      }
      return result.toString();
    }
    else
    {
      return null;
    }
  }

  public void setStringFinalDefault(String finalDefault)
  {
    if (finalDefault == null)
    {
      unsetFinalDefault();
    }
    else
    {
      List<XSDProhibitedSubstitutions> newFinalDefault = new ArrayList<XSDProhibitedSubstitutions>();
      for (StringTokenizer stringTokenizer = new StringTokenizer(finalDefault); stringTokenizer.hasMoreTokens(); )
      {
        String token = stringTokenizer.nextToken();
        if (token.equals("#all"))
        {
          token = "all";
        }
        XSDProhibitedSubstitutions literal = XSDProhibitedSubstitutions.get(token);
        if (literal != null)
        {
          newFinalDefault.add(literal);
        }
      }
      if (!newFinalDefault.equals(getFinalDefault()))
      {
        Collection<XSDProhibitedSubstitutions> oldContents = new ArrayList<XSDProhibitedSubstitutions>(getFinalDefault());
        oldContents.removeAll(newFinalDefault);
        getFinalDefault().removeAll(oldContents);
        setListContentAndOrder(getFinalDefault(), newFinalDefault);
      }
      else if (newFinalDefault.isEmpty() && !isSetFinalDefault())
      {
        getFinalDefault().clear();
      }
    }
  }

  @Override
  public Element createElement()
  {
    Element newElement = createElement(XSDConstants.SCHEMA_ELEMENT);
    setElement(newElement);
    return newElement;
  }

  @Override
  protected void traverseToRootForPatching()
  {
    if (!isReconciling && isIncrementalUpdate)
    {
      isReconciling = true;
      patch();
      isReconciling = false;
    }
  }

  protected Collection<XSDSchemaImpl> circularResolveDependencies;

  protected void computeSchemasToPatch(XSDSchema root, List<XSDSchema> schemasToPatch)
  {
    schemasToPatch.add(root);
    for (int i = 0; i < schemasToPatch.size(); ++i)
    {
      XSDSchema xsdSchema = schemasToPatch.get(i); 
      for (XSDSchemaContent content :  xsdSchema.getContents())
      {
        if (content instanceof XSDSchemaDirective)
        {
          if (content instanceof XSDSchemaCompositor)
          {
            XSDSchemaCompositor xsdSchemaCompositor = (XSDSchemaCompositor)content;
            XSDSchemaImpl xsdIncorporatedSchema =  (XSDSchemaImpl)xsdSchemaCompositor.getIncorporatedSchema();
            if (xsdIncorporatedSchema != null && !schemasToPatch.contains(xsdIncorporatedSchema))
            {
              xsdIncorporatedSchema.patchContents();
              computeSchemasToPatch(xsdIncorporatedSchema, schemasToPatch);
            }
          }
        }
        else if (!(content instanceof XSDAnnotation))
        {
          break;
        }
      }
    }
  }

  @Override
  protected void patch()
  {
    circularResolveDependencies = new HashSet<XSDSchemaImpl>();
    
    if (XSDConstants.isSchemaForSchemaNamespace(getTargetNamespace()))
    {
      XSDSchema magicSchemaForSchema = getMagicSchemaForSchema(getTargetNamespace());
      if (magicSchemaForSchema != this)
      {
        XSDNamedComponentImpl.addToSortedList
          (getTypeDefinitions(), magicSchemaForSchema.getTypeDefinitions().get(0));
      }
    }
    else
    {
      Collection<XSDSchema> xsiSchemas = resolveSchema(XSDConstants.SCHEMA_INSTANCE_URI_2001);
      if (xsiSchemas.size() == 1)
      {
        XSDNamedComponentImpl.mergeToSortedList
          (getAttributeDeclarations(), 
           xsiSchemas.iterator().next().getAttributeDeclarations());
      }
    }

    for (Iterator<XSDSchemaDirective> i = getReferencingDirectives().iterator(); i.hasNext(); )
    {
      XSDSchemaDirective xsdSchemaDirective = i.next();
      if (xsdSchemaDirective.getContainer() == null ||
            xsdSchemaDirective.getResolvedSchema() != this &&
            (!(xsdSchemaDirective instanceof XSDSchemaCompositor) ||
                ((XSDSchemaCompositor)xsdSchemaDirective).getIncorporatedSchema() != this))
      {
        i.remove();
      }
    }

    super.patch();
    
    List<XSDSchema> schemasToPatch = new ArrayList<XSDSchema>();
    computeSchemasToPatch(this, schemasToPatch);
    
    if (circularResolveDependencies != null)
    {
      Collection<XSDSchemaImpl> localCircularResolveDependencies = circularResolveDependencies;
      circularResolveDependencies = null;
      for (XSDSchemaImpl circularSchema : localCircularResolveDependencies)
      {
        circularSchema.patch();
      }
    }

    if (schemaLocation != null)
    {
      for (int i = 0, size = schemasToPatch.size(); i < size; ++i)
      {
        XSDSchemaImpl xsdSchema = (XSDSchemaImpl)schemasToPatch.get(i); 
        xsdSchema.analyze();
      }
    }
  }
  
  protected void patchContents()
  {
    incorporatingSchemas = null;
    super.patch();
  }

  @Override
  protected void traverseToRootForAnalysis()
  {
    if (!isReconciling && isIncrementalUpdate)
    {
      isReconciling = true;
      analyze();
      isReconciling = false;
    }
  }

  @Override
  protected boolean analyze()
  {
    return super.analyze();
  }

  @Override
  public void validate()
  {
    super.validate();

    Element theElement = getElement();
    if (theElement != null)
    {
      if (XSDConstants.nodeType(element) != XSDConstants.SCHEMA_ELEMENT)
      {
        createDiagnostic
          (XSDDiagnosticSeverity.ERROR_LITERAL, 
           "content-valid.1",
           (element.getNamespaceURI() == null ? "" : element.getNamespaceURI()) + "#" + element.getLocalName(),
           XSDPlugin.INSTANCE.getString("_UI_XML_SCHEMA_name"),
           "schema");
      }
      else
      {
        if (getContainer() == null || !eContainmentFeature().isTransient())
        {
          checkAttributes
            (XSDConstants.PART1,
             "element-complexType",
             theElement,
             new String []
             {
               XSDConstants.ATTRIBUTEFORMDEFAULT_ATTRIBUTE,
               XSDConstants.BLOCKDEFAULT_ATTRIBUTE,
               XSDConstants.ELEMENTFORMDEFAULT_ATTRIBUTE,
               XSDConstants.FINALDEFAULT_ATTRIBUTE,
               XSDConstants.ID_ATTRIBUTE,
               XSDConstants.TARGETNAMESPACE_ATTRIBUTE,
               XSDConstants.VERSION_ATTRIBUTE
             });

          XSDComplexTypeDefinition xsdComplexTypeDefinition = 
            (XSDComplexTypeDefinition)getSchemaForSchema().resolveElementDeclaration("schema").getTypeDefinition();

          checkComplexContent
            (xsdComplexTypeDefinition,
             XSDConstants.PART1, 
             "element-schema", 
             theElement);

          checkAttributeTypeConstraint
            (xsdComplexTypeDefinition,
             "attributeFormDefault",
             null,
             XSDConstants.PART1,
             "element-schema",
             theElement,
             XSDConstants.ATTRIBUTEFORMDEFAULT_ATTRIBUTE,
             false);

          checkAttributeTypeConstraint
            (xsdComplexTypeDefinition,
             "blockDefault",
             null,
             XSDConstants.PART1,
             "element-schema",
             theElement,
             XSDConstants.BLOCKDEFAULT_ATTRIBUTE,
             false);

          checkAttributeTypeConstraint
            (xsdComplexTypeDefinition,
             "elementFormDefault",
             null,
             XSDConstants.PART1,
             "element-schema",
             theElement,
             XSDConstants.ELEMENTFORMDEFAULT_ATTRIBUTE,
             false);

          checkAttributeTypeConstraint
            (xsdComplexTypeDefinition,
             "finalDefault",
             null,
             XSDConstants.PART1,
             "element-schema",
             theElement,
             XSDConstants.FINALDEFAULT_ATTRIBUTE,
             false);

           checkBuiltInTypeConstraint
             ("ID",
              null,
              XSDConstants.PART1,
              "element-schema",
              theElement,
              XSDConstants.ID_ATTRIBUTE,
              false);
        }
      }
    }

    if (getSchemaForSchema() != null)
    {
      checkBuiltInTypeConstraint
        ("anyURI",
         getTargetNamespace(),
         XSDConstants.PART1,
         "element-schema",
         theElement,
         XSDConstants.TARGETNAMESPACE_ATTRIBUTE,
         false);
  
      checkBuiltInTypeConstraint
        ("token",
         getVersion(),
         XSDConstants.PART1,
         "element-schema",
         theElement,
         XSDConstants.VERSION_ATTRIBUTE,
         false);
  
      validateNoDuplicates("attribute_noun", getAttributeDeclarations());
      validateNoDuplicates("attributeGroup_noun", getAttributeGroupDefinitions());
      validateNoDuplicates("modelGroup_noun", getModelGroupDefinitions());
      validateNoDuplicates("element_noun", getElementDeclarations());
      validateNoDuplicates("type_noun", getTypeDefinitions());
      validateNoDuplicates("identityConstraint_noun", getIdentityConstraintDefinitions());
      validateNoDuplicates("notation_noun", getNotationDeclarations());
    }
  }

  public void validateNoDuplicates(String componentNoun, EList<? extends XSDNamedComponent> xsdNamedComponents)
  {
    String name = null;
    String targetNamespace = null;


    for (XSDNamedComponent xsdNamedComponent :  xsdNamedComponents)
    {
      if (xsdNamedComponent.hasNameAndTargetNamespace(name, targetNamespace))
      {
        getDiagnosticTarget(xsdNamedComponent).createDiagnostic
          (XSDDiagnosticSeverity.ERROR_LITERAL, 
           "coss-schema.2",
           XSDPlugin.INSTANCE.getString(componentNoun),
           xsdNamedComponent.getURI());
      }

      name = xsdNamedComponent.getName();
      targetNamespace = xsdNamedComponent.getTargetNamespace();
    }
  }

  protected void updateSortedList(XSDNamedComponent xsdNamedComponent)
  {
    new XSDSwitch<Object>()
    {
      @Override
      public Object caseXSDElementDeclaration(XSDElementDeclaration xsdElementDeclaration)
      {
        if (getElementDeclarations().remove(xsdElementDeclaration))
        {
          XSDNamedComponentImpl.addToSortedList(getElementDeclarations(), xsdElementDeclaration);
        }
        return this;
      }
      @Override
      public Object caseXSDAttributeDeclaration(XSDAttributeDeclaration xsdAttributeDeclaration)
      {
        if (getAttributeDeclarations().remove(xsdAttributeDeclaration))
        {
          XSDNamedComponentImpl.addToSortedList(getAttributeDeclarations(), xsdAttributeDeclaration);
        }
        return this;
      }
      @Override
      public Object caseXSDAttributeGroupDefinition(XSDAttributeGroupDefinition xsdAttributeGroupDefinition)
      {
        if (getAttributeGroupDefinitions().remove(xsdAttributeGroupDefinition))
        {
          XSDNamedComponentImpl.addToSortedList(getAttributeGroupDefinitions(), xsdAttributeGroupDefinition);
        }
        return this;
      }
      @Override
      public Object caseXSDTypeDefinition(XSDTypeDefinition xsdTypeDefinition)
      {
        if (getTypeDefinitions().remove(xsdTypeDefinition))
        {
          XSDNamedComponentImpl.addToSortedList(getTypeDefinitions(), xsdTypeDefinition);
        }
        return this;
      }
      @Override
      public Object caseXSDModelGroupDefinition(XSDModelGroupDefinition xsdModelGroupDefinition)
      {
        if (getModelGroupDefinitions().remove(xsdModelGroupDefinition))
        {
          XSDNamedComponentImpl.addToSortedList(getModelGroupDefinitions(), xsdModelGroupDefinition);
        }
        return this;
      }
      @Override
      public Object caseXSDNotationDeclaration(XSDNotationDeclaration xsdNotationDeclaration)
      {
        if (getNotationDeclarations().remove(xsdNotationDeclaration))
        {
          XSDNamedComponentImpl.addToSortedList(getNotationDeclarations(), xsdNotationDeclaration);
        }
        return this;
      }
      @Override
      public Object caseXSDIdentityConstraintDefinition(XSDIdentityConstraintDefinition xsdIdentityConstraintDefinition)
      {
        if (getIdentityConstraintDefinitions().remove(xsdIdentityConstraintDefinition))
        {
          XSDNamedComponentImpl.addToSortedList(getIdentityConstraintDefinitions(), xsdIdentityConstraintDefinition);
        }
        return this;
      }
    }.doSwitch(xsdNamedComponent);
  }

  @Override
  protected void adoptContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.adoptContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.Literals.XSD_SCHEMA__CONTENTS)
    {
      new XSDSwitch<Object>()
      {
        @Override
        public Object caseXSDElementDeclaration(XSDElementDeclaration xsdElementDeclaration)
        {
          XSDNamedComponentImpl.addToSortedList(getElementDeclarations(), xsdElementDeclaration);
          return this;
        }
        @Override
        public Object caseXSDAttributeDeclaration(XSDAttributeDeclaration xsdAttributeDeclaration)
        {
          XSDNamedComponentImpl.addToSortedList(getAttributeDeclarations(), xsdAttributeDeclaration);
          return this;
        }
        @Override
        public Object caseXSDAttributeGroupDefinition(XSDAttributeGroupDefinition xsdAttributeGroupDefinition)
        {
          XSDNamedComponentImpl.addToSortedList(getAttributeGroupDefinitions(), xsdAttributeGroupDefinition);
          return this;
        }
        @Override
        public Object caseXSDTypeDefinition(XSDTypeDefinition xsdTypeDefinition)
        {
          XSDNamedComponentImpl.addToSortedList(getTypeDefinitions(), xsdTypeDefinition);
          return this;
        }
        @Override
        public Object caseXSDModelGroupDefinition(XSDModelGroupDefinition xsdModelGroupDefinition)
        {
          XSDNamedComponentImpl.addToSortedList(getModelGroupDefinitions(), xsdModelGroupDefinition);
          return this;
        }
        @Override
        public Object caseXSDNotationDeclaration(XSDNotationDeclaration xsdNotationDeclaration)
        {
          XSDNamedComponentImpl.addToSortedList(getNotationDeclarations(), xsdNotationDeclaration);
          return this;
        }
        @Override
        public Object caseXSDAnnotation(XSDAnnotation xsdAnnotation)
        {
          getAnnotations().add(xsdAnnotation);
          return this;
        }
      }.doSwitch(xsdConcreteComponent);

      traverseToRootForPatching();
    }
  }

  @Override
  protected void orphanContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.orphanContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.Literals.XSD_SCHEMA__CONTENTS)
    {
      new XSDSwitch<Object>()
      {
        @Override
        public Object caseXSDElementDeclaration(XSDElementDeclaration xsdElementDeclaration)
        {
          getElementDeclarations().remove(xsdElementDeclaration);
          return this;
        }
        @Override
        public Object caseXSDAttributeDeclaration(XSDAttributeDeclaration xsdAttributeDeclaration)
        {
          getAttributeDeclarations().remove(xsdAttributeDeclaration);
          return this;
        }
        @Override
        public Object caseXSDAttributeGroupDefinition(XSDAttributeGroupDefinition xsdAttributeGroupDefinition)
        {
          getAttributeGroupDefinitions().remove(xsdAttributeGroupDefinition);
          return this;
        }
        @Override
        public Object caseXSDTypeDefinition(XSDTypeDefinition xsdTypeDefinition)
        {
          getTypeDefinitions().remove(xsdTypeDefinition);
          return this;
        }
        @Override
        public Object caseXSDModelGroupDefinition(XSDModelGroupDefinition xsdModelGroupDefinition)
        {
          getModelGroupDefinitions().remove(xsdModelGroupDefinition);
          return this;
        }
        @Override
        public Object caseXSDNotationDeclaration(XSDNotationDeclaration xsdNotationDeclaration)
        {
          getNotationDeclarations().remove(xsdNotationDeclaration);
          return this;
        }
        @Override
        public Object caseXSDAnnotation(XSDAnnotation xsdAnnotation)
        {
          getAnnotations().remove(xsdAnnotation);
          return this;
        }
      }.doSwitch(xsdConcreteComponent);

      traverseToRootForPatching();
    }
  }

  @Override
  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    if (changedElement == getElement())
    {
      if (changedElement.hasAttributeNS(null, XSDConstants.VERSION_ATTRIBUTE))
      {
        String newVersion = changedElement.getAttributeNS(null, XSDConstants.VERSION_ATTRIBUTE);
        if (!newVersion.equals(getVersion()))
        {
          setVersion(newVersion);
        }
      }
      else if (getVersion() != null)
      {
        setVersion(null);
      }

      if (changedElement.hasAttributeNS(null, XSDConstants.TARGETNAMESPACE_ATTRIBUTE))
      {
        String newTargetNamespace = changedElement.getAttributeNS(null, XSDConstants.TARGETNAMESPACE_ATTRIBUTE);
        if (!newTargetNamespace.equals(getTargetNamespace()))
        {
          setTargetNamespace(newTargetNamespace);
        }
      }
      else if (getTargetNamespace() != null)
      {
        setTargetNamespace(null);
      }

      if (changedElement.hasAttributeNS(null, XSDConstants.ATTRIBUTEFORMDEFAULT_ATTRIBUTE))
      {
        XSDForm newAttributeFormDefault = XSDForm.get(changedElement.getAttributeNS(null, XSDConstants.ATTRIBUTEFORMDEFAULT_ATTRIBUTE));
        if (!isSetAttributeFormDefault() || newAttributeFormDefault != getAttributeFormDefault())
        {
          setAttributeFormDefault(newAttributeFormDefault);
        }
      }
      else if (isSetAttributeFormDefault())
      {
        unsetAttributeFormDefault();
      }
      if (changedElement.hasAttributeNS(null, XSDConstants.ELEMENTFORMDEFAULT_ATTRIBUTE))
      {
        XSDForm newElementFormDefault = XSDForm.get(changedElement.getAttributeNS(null, XSDConstants.ELEMENTFORMDEFAULT_ATTRIBUTE));
        if (!isSetElementFormDefault() || newElementFormDefault != getElementFormDefault())
        {
          setElementFormDefault(newElementFormDefault);
        }
      }
      else if (isSetElementFormDefault())
      {
        unsetElementFormDefault();
      }

      if (changedElement.hasAttributeNS(null, XSDConstants.BLOCKDEFAULT_ATTRIBUTE))
      {
        setStringBlockDefault(changedElement.getAttributeNS(null, XSDConstants.BLOCKDEFAULT_ATTRIBUTE));
      }
      else if (isSetBlockDefault())
      {
        unsetBlockDefault();
      }

      if (changedElement.hasAttributeNS(null, XSDConstants.FINALDEFAULT_ATTRIBUTE))
      {
        setStringFinalDefault(changedElement.getAttributeNS(null, XSDConstants.FINALDEFAULT_ATTRIBUTE));
      }
      else if (isSetFinalDefault())
      {
        unsetFinalDefault();
      }

      Map<String, String> theQNamePrefixToNamespaceMap = getQNamePrefixToNamespaceMap();
      theQNamePrefixToNamespaceMap.clear();
      for (Element currentElement = changedElement; ; ) 
      {
        NamedNodeMap attributes = currentElement.getAttributes(); 
        for (int i = 0, size = attributes.getLength(); i < size; ++i)
        {
          Attr attr = (Attr)attributes.item(i);
          String name = attr.getNodeName();
          if (name.startsWith("xmlns"))
          {
            String value = attr.getNodeValue();
            int index = name.indexOf(":");
            String key = index == -1 || index == name.length() - 1? null : name.substring(index + 1);
            if (!theQNamePrefixToNamespaceMap.containsKey(key)) 
            {
              theQNamePrefixToNamespaceMap.put(key, value);
            }
          }
        }
        Node parent = currentElement.getParentNode(); 
        if (parent instanceof Element)
        {
          currentElement = (Element)parent;
        }
        else
        {
          break;
        }
      } 

      String nodeName =  changedElement.getNodeName();
      int index = nodeName.indexOf(":");
      setSchemaForSchemaQNamePrefix(index == -1 ? null : nodeName.substring(0, index));
    }
  }

  @Override
  protected void handleUnreconciledElement(Element child, List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    XSDSchemaContent xsdSchemaContent = XSDSchemaContentImpl.createSchemaContent(child);
    if (xsdSchemaContent != null)
    {
      newContents.add(xsdSchemaContent);
    }
  }

  @Override
  protected void handleReconciliation(List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    if (!remainingContents.isEmpty())
    {
      getContents().removeAll(remainingContents);
    }

    @SuppressWarnings("unchecked") List<XSDSchemaContent> list = (List<XSDSchemaContent>)(List<?>)newContents;
    setListContentAndOrder(getContents(), list);
  }

  public XSDConcreteComponent getCorrespondingComponent(Node node)
  {
    // We consider all parents so that they can handle other contained nodes that otherwise don't correspond to a component.
    //
    List<Element> parents = new ArrayList<Element>();
  
    if (node.getNodeType() == Node.ATTRIBUTE_NODE)
    {
      node = ((Attr)node).getOwnerElement();
    }
    else
    {
      // Skip ahead to an element.
      //
      for (Node scanNode = node; scanNode != null; scanNode = scanNode.getNextSibling())
      {
        if (scanNode.getNodeType() == Node.ELEMENT_NODE)
        {
          node = scanNode;
          break;
        }
      }
  
      // Skip back to an element.
      //
      for (Node scanNode = node; scanNode != null; scanNode = scanNode.getPreviousSibling())
      {
        if (scanNode.getNodeType() == Node.ELEMENT_NODE)
        {
          node = scanNode;
          break;
        }
      }
    }
  
    // Navigate out through the elements.
    //
    for (Node parent = node; parent != null; parent = parent.getParentNode())
    {
      if (parent.getNodeType() == Node.ELEMENT_NODE)
      {
        parents.add((Element)parent);
      }
    }

    XSDConcreteComponent bestXSDConcreteComponent = getBestConcreteComponent(parents);
    return bestXSDConcreteComponent;
  }

  protected Map<String, XSDSimpleTypeDefinition> simpleTypeIdMap;
  public Map<String, XSDSimpleTypeDefinition> getSimpleTypeIdMap()
  {
    if (simpleTypeIdMap == null)
    {
      simpleTypeIdMap = new HashMap<String, XSDSimpleTypeDefinition>();
      for (XSDTypeDefinition xsdTypeDefinition : getTypeDefinitions())
      {
        if (xsdTypeDefinition  instanceof XSDSimpleTypeDefinition)
        {
          Element theElement = xsdTypeDefinition.getElement();
          if (theElement != null)
          {
            if (theElement.hasAttributeNS(null, XSDConstants.ID_ATTRIBUTE) && xsdTypeDefinition instanceof XSDSimpleTypeDefinition)
            {
              simpleTypeIdMap.put(theElement.getAttributeNS(null, XSDConstants.ID_ATTRIBUTE), (XSDSimpleTypeDefinition)xsdTypeDefinition);
            }
          }
        }
      }
    }
    return simpleTypeIdMap;
  }

  protected List<XSDSchema> incorporatingSchemas;

  protected List<XSDSchema> getIncorporatingSchemas()
  {
    if (incorporatingSchemas == null)
    {
      List<XSDSchema> visited = new UniqueEList.FastCompare<XSDSchema>();
      visited.add(this);
      incorporatingSchemas = new ArrayList<XSDSchema>();
      for (int i = 0; i < visited.size(); ++i)
      {
        XSDSchemaImpl xsdSchema = (XSDSchemaImpl)visited.get(i);
        incorporatingSchemas.add(xsdSchema);
        for (XSDSchemaDirective xsdSchemaDirective : xsdSchema.getReferencingDirectives())
        {
          if (xsdSchemaDirective instanceof XSDSchemaCompositor && ((XSDSchemaCompositor)xsdSchemaDirective).getIncorporatedSchema() == xsdSchema)
          {
            XSDSchemaImpl incorporatingSchema = (XSDSchemaImpl)xsdSchemaDirective.getSchema();
            if (incorporatingSchema != null && visited.add(incorporatingSchema))
            {
              incorporatingSchemas.add(incorporatingSchema);
              visited.addAll(incorporatingSchema.getIncorporatingSchemas());
            }
          }
        }
      }
    }
    return incorporatingSchemas;
  }
  
  /**
   * This returns set of schemas with the given namespace as it's target namespace.
   */
  public Collection<XSDSchema> resolveSchema(String namespace)
  {
    if ("".equals(namespace))
    {
      namespace = null;
    }

    if (namespace == null ? getTargetNamespace() == null || "".equals(getTargetNamespace()) || hasRetargetedNamespace() : namespace.equals(getTargetNamespace()))
    {
      return getIncorporatingSchemas();
    }
    else if (XSDConstants.isSchemaInstanceNamespace(namespace))
    {
      return Collections.singleton(getSchemaInstance(namespace));
    }
    else
    {
      Collection<XSDSchema> result = new ArrayList<XSDSchema>();
      for (XSDSchemaContent xsdSchemaContent : getContents())
      {
        if (xsdSchemaContent instanceof XSDSchemaDirective)
        {
          if (xsdSchemaContent instanceof XSDImport)
          {
            XSDImport xsdImport = (XSDImport)xsdSchemaContent;
            String importNamespace = xsdImport.getNamespace();
            if (namespace == null ? importNamespace == null || "".equals(importNamespace): namespace.equals(importNamespace))
            {
              XSDSchema importedSchema = ((XSDImportImpl)xsdImport).importSchema();
              if (importedSchema != null)
              {
                result.add(importedSchema);
                Collection<XSDSchemaImpl> circular = ((XSDSchemaImpl)importedSchema).circularResolveDependencies;
                if (circular != null)
                {
                  circular.add(this);
                }
              }
            }
          }
        }
        else if (!(xsdSchemaContent instanceof XSDAnnotation))
        {
          break;
        }
      }

      return result;
    }
  }

  /**
   * This is a helper method for searching for a name in one of the schemas named component relations.
   */
  protected XSDNamedComponent resolveNamedComponent(EReference namedComponentsRefReference, String namespace, String localName)
  {
    if ("".equals(namespace))
    {
      namespace = null;
    }

    Collection<XSDSchema> resolvedSchemas = resolveSchema(namespace);
    for (XSDSchema resolvedSchema : resolvedSchemas)
    {
      @SuppressWarnings("unchecked") List<XSDNamedComponent> list = (List<XSDNamedComponent>)resolvedSchema.eGet(namedComponentsRefReference);
      XSDNamedComponent xsdNamedComponent = XSDNamedComponentImpl.findInSortedList(list, namespace, localName);
      if (xsdNamedComponent == null && namespace == null && resolvedSchemas.contains(this) && resolvedSchema.getTargetNamespace() != null)
      {
        xsdNamedComponent = XSDNamedComponentImpl.findInSortedList(list, getTargetNamespace(), localName);
        
      }
      if (xsdNamedComponent != null)
      {
        return xsdNamedComponent;
      }
    }
    return null;
  }

  @Override
  public XSDAttributeDeclaration resolveAttributeDeclaration(String namespace, String localName)
  {
    XSDAttributeDeclaration result = 
      (XSDAttributeDeclaration)resolveNamedComponent(XSDPackage.Literals.XSD_SCHEMA__ATTRIBUTE_DECLARATIONS, namespace, localName);
    if (result == null)
    {
      result = createUnresolvedAttributeDeclaration(namespace, localName);
    }
    return result;
  }

  @Override
  public XSDAttributeGroupDefinition resolveAttributeGroupDefinition(String namespace, String localName)
  {
    XSDAttributeGroupDefinition result = 
      (XSDAttributeGroupDefinition)resolveNamedComponent
        (XSDPackage.Literals.XSD_SCHEMA__ATTRIBUTE_GROUP_DEFINITIONS, namespace, localName);
    if (result == null)
    {
      result = createUnresolvedAttributeGroupDefinition(namespace, localName);
    }
    return result;
  }

  @Override
  public XSDElementDeclaration resolveElementDeclaration(String namespace, String localName)
  {
    XSDElementDeclaration result = 
      (XSDElementDeclaration)resolveNamedComponent(XSDPackage.Literals.XSD_SCHEMA__ELEMENT_DECLARATIONS, namespace, localName);
    if (result == null)
    {
      result = createUnresolvedElementDeclaration(namespace, localName);
    }
    return result;
  }

  @Override
  public XSDTypeDefinition resolveTypeDefinition(String namespace, String localName)
  {
    XSDTypeDefinition result = 
      (XSDTypeDefinition)resolveNamedComponent(XSDPackage.Literals.XSD_SCHEMA__TYPE_DEFINITIONS, namespace, localName);
    if (result == null && 
          XSDConstants.isSchemaForSchemaNamespace(namespace) &&
          !XSDConstants.isSchemaForSchemaNamespace(getTargetNamespace()))
    {
      result = getSchemaForSchema(namespace).resolveTypeDefinition(namespace, localName);
    }
    if (result == null)
    {
      result =  createUnresolvedTypeDefinition(namespace, localName);
    }
    return result;
  }

  @Override
  public XSDSimpleTypeDefinition resolveSimpleTypeDefinition(String namespace, String localName)
  {
    XSDTypeDefinition xsdTypeDefinition = 
      (XSDTypeDefinition)resolveNamedComponent(XSDPackage.Literals.XSD_SCHEMA__TYPE_DEFINITIONS, namespace, localName);
    XSDSimpleTypeDefinition result =
      xsdTypeDefinition instanceof XSDSimpleTypeDefinition ? (XSDSimpleTypeDefinition)xsdTypeDefinition : null;
    if (result == null)
    {
      if (XSDConstants.isSchemaForSchemaNamespace(namespace))
      {
        if  ("anyType".equals(localName) || "anySimpleType".equals(localName))
        {
          XSDSchema magicSchemaForSchema = getMagicSchemaForSchema(namespace);
          if (magicSchemaForSchema == this)
          {
            EList<XSDTypeDefinition> typeDefinitions = magicSchemaForSchema.getTypeDefinitions();
            if (typeDefinitions.size() > 0)
            {
              result = (XSDSimpleTypeDefinition)typeDefinitions.get(0);
            }
          }
          else 
          {
            result = magicSchemaForSchema.resolveSimpleTypeDefinition(namespace, localName);
          }
        }
        else if (!XSDConstants.isSchemaForSchemaNamespace(getTargetNamespace()))
        {
          XSDSchema schemaForSchema = getSchemaForSchema(namespace);
          result = schemaForSchema.resolveSimpleTypeDefinition(namespace, localName);
        }
      }
    }

    if (result == null)
    {
      result =  createUnresolvedSimpleTypeDefinition(namespace, localName);
    }
    return result;
  }

  @Override
  public XSDComplexTypeDefinition resolveComplexTypeDefinition(String namespace, String localName)
  {
    XSDTypeDefinition xsdTypeDefinition = 
      (XSDTypeDefinition)resolveNamedComponent(XSDPackage.Literals.XSD_SCHEMA__TYPE_DEFINITIONS, namespace, localName);
    XSDComplexTypeDefinition result =
      xsdTypeDefinition instanceof XSDComplexTypeDefinition ? (XSDComplexTypeDefinition)xsdTypeDefinition : null;
    if (result == null &&
          XSDConstants.isSchemaForSchemaNamespace(namespace) && 
          !XSDConstants.isSchemaForSchemaNamespace(getTargetNamespace()))
    {
      result = getSchemaForSchema(namespace).resolveComplexTypeDefinition(namespace, localName);
    }
    if (result == null)
    {
      result =  createUnresolvedComplexTypeDefinition(namespace, localName);
    }
    return result;
  }

  @Override
  public XSDModelGroupDefinition resolveModelGroupDefinition(String namespace, String localName)
  {
    XSDModelGroupDefinition result = 
      (XSDModelGroupDefinition)resolveNamedComponent(XSDPackage.Literals.XSD_SCHEMA__MODEL_GROUP_DEFINITIONS, namespace, localName);
    if (result == null)
    {
      result = createUnresolvedModelGroupDefinition(namespace, localName);
    }

    return result;
  }

  @Override
  public XSDIdentityConstraintDefinition resolveIdentityConstraintDefinition(String namespace, String localName)
  {
    XSDIdentityConstraintDefinition result = 
      (XSDIdentityConstraintDefinition)resolveNamedComponent(XSDPackage.Literals.XSD_SCHEMA__IDENTITY_CONSTRAINT_DEFINITIONS, namespace, localName);
    if (result == null)
    {
      result = createUnresolvedIdentityConstraintDefinition(namespace, localName);
    }
    return result;
  }

  @Override
  public XSDNotationDeclaration resolveNotationDeclaration(String namespace, String localName)
  {
    XSDNotationDeclaration result =
      (XSDNotationDeclaration) resolveNamedComponent(XSDPackage.Literals.XSD_SCHEMA__NOTATION_DECLARATIONS, namespace, localName);
    if (result == null)
    {
      result = createUnresolvedNotationDeclaration(namespace, localName);
    }
    return result;
  }

  @Override
  protected void changeAttribute(EAttribute eAttribute)
  {
    if (eAttribute == XSDPackage.Literals.XSD_SCHEMA__SCHEMA_LOCATION)
    {
      patch();
    }
    else
    {
      super.changeAttribute(eAttribute);
      Element theElement = getElement();
      if (theElement != null)
      {
        if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_SCHEMA__VERSION)
        {
          niceSetAttribute
            (theElement, 
             XSDConstants.VERSION_ATTRIBUTE, 
             getVersion());
          if (eAttribute != null)
          {
            traverseToRootForPatching();
          }
        }
        if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_SCHEMA__TARGET_NAMESPACE)
        {
          niceSetAttribute
            (theElement, 
             XSDConstants.TARGETNAMESPACE_ATTRIBUTE, 
             getTargetNamespace());
          if (eAttribute != null)
          {
            if (!isReconciling && isIncrementalUpdate)
            {
              for (Object content : getContents())
              {
                if (content instanceof XSDNamedComponentImpl)
                {
                  ((XSDNamedComponentImpl)content).patchTargetNamespaceAttribute();
                }
              }
              reset();
            }
          }
        }
        if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_SCHEMA__ATTRIBUTE_FORM_DEFAULT)
        {
          niceSetAttribute
            (theElement, 
             XSDConstants.ATTRIBUTEFORMDEFAULT_ATTRIBUTE, 
             isSetAttributeFormDefault() ? getAttributeFormDefault().getName() : null);
          if (eAttribute != null)
          {
            traverseToRootForPatching();
          }
        }
        if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_SCHEMA__ELEMENT_FORM_DEFAULT)
        {
          niceSetAttribute
            (theElement, 
             XSDConstants.ELEMENTFORMDEFAULT_ATTRIBUTE, 
             isSetElementFormDefault() ? getElementFormDefault().getName() : null);
          if (eAttribute != null)
          {
            traverseToRootForPatching();
          }
        }
        if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_SCHEMA__BLOCK_DEFAULT)
        {
          niceSetAttribute
            (theElement, 
             XSDConstants.BLOCKDEFAULT_ATTRIBUTE, 
             getStringBlockDefault());
          if (eAttribute != null)
          {
            traverseToRootForPatching();
          }
        }
        if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_SCHEMA__FINAL_DEFAULT)
        {
          niceSetAttribute
            (theElement, 
             XSDConstants.FINALDEFAULT_ATTRIBUTE, 
             getStringFinalDefault());
          if (eAttribute != null)
          {
            traverseToRootForPatching();
          }
        }
      }
    }
  }

  @Override
  protected void changeReference(EReference eReference)
  {
    super.changeReference(eReference);
    if (eReference == XSDPackage.Literals.XSD_SCHEMA__REFERENCING_DIRECTIVES)
    {
      boolean newHasRetargetedNamespace = false;
      for (XSDSchemaDirective xsdSchemaDirective : getReferencingDirectives())
      {
        if (xsdSchemaDirective instanceof XSDSchemaCompositor)
        {
          XSDSchemaCompositor xsdSchemaCompositor = (XSDSchemaCompositor)xsdSchemaDirective;
          if (xsdSchemaCompositor.getResolvedSchema() != xsdSchemaCompositor.getIncorporatedSchema() &&
                xsdSchemaCompositor.getResolvedSchema().getTargetNamespace() == null)
          {
            newHasRetargetedNamespace = true;
            break;
          }
        }
      }
      hasRetargetedNamespace = newHasRetargetedNamespace;
    }
  }

/*
  public void unsetElement()
  {
    Element oldElement = getElement();
    if (oldElement instanceof EventTarget)
    {
      EventTarget oldEventTarget = ((EventTarget)oldElement);
      oldEventTarget.removeEventListener("DOMNodeInserted", getEventListener(), true);
      oldEventTarget.removeEventListener("DOMNodeRemoved", getEventListener(), true);
      oldEventTarget.removeEventListener("DOMAttrModified", getEventListener(), true);
    }
    super.unsetElement();
  }
*/

  @Override
  public void setElement(Element element)
  {
    Element oldElement = getElement();
    if (oldElement instanceof EventTarget)
    {
      EventTarget oldEventTarget = ((EventTarget)oldElement);
      oldEventTarget.removeEventListener("DOMNodeInserted", getEventListener(), true);
      oldEventTarget.removeEventListener("DOMNodeRemoved", getEventListener(), true);
      oldEventTarget.removeEventListener("DOMAttrModified", getEventListener(), true);
    }
    super.setElement(element);
    if (element instanceof EventTarget)
    {
      EventTarget eventTarget = ((EventTarget)element);
      eventTarget.addEventListener("DOMNodeInserted", getEventListener(), true);
      eventTarget.addEventListener("DOMNodeRemoved", getEventListener(), true);
      eventTarget.addEventListener("DOMAttrModified", getEventListener(), true);
    }
    if (element != null)
    {
      document = element.getOwnerDocument();
    }
  }

  public XSDSchema getSchemaForSchema()
  {
    return getSchemaForSchema(getSchemaForSchemaNamespace());
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
      case XSDPackage.XSD_SCHEMA__CONTENTS:
        return ((InternalEList<?>)getContents()).basicRemove(otherEnd, msgs);
      case XSDPackage.XSD_SCHEMA__INCORPORATED_VERSIONS:
        return ((InternalEList<?>)getIncorporatedVersions()).basicRemove(otherEnd, msgs);
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
      case XSDPackage.XSD_SCHEMA__DOCUMENT:
        return getDocument();
      case XSDPackage.XSD_SCHEMA__SCHEMA_LOCATION:
        return getSchemaLocation();
      case XSDPackage.XSD_SCHEMA__TARGET_NAMESPACE:
        return getTargetNamespace();
      case XSDPackage.XSD_SCHEMA__ATTRIBUTE_FORM_DEFAULT:
        return getAttributeFormDefault();
      case XSDPackage.XSD_SCHEMA__ELEMENT_FORM_DEFAULT:
        return getElementFormDefault();
      case XSDPackage.XSD_SCHEMA__FINAL_DEFAULT:
        return getFinalDefault();
      case XSDPackage.XSD_SCHEMA__BLOCK_DEFAULT:
        return getBlockDefault();
      case XSDPackage.XSD_SCHEMA__VERSION:
        return getVersion();
      case XSDPackage.XSD_SCHEMA__CONTENTS:
        return getContents();
      case XSDPackage.XSD_SCHEMA__ELEMENT_DECLARATIONS:
        return getElementDeclarations();
      case XSDPackage.XSD_SCHEMA__ATTRIBUTE_DECLARATIONS:
        return getAttributeDeclarations();
      case XSDPackage.XSD_SCHEMA__ATTRIBUTE_GROUP_DEFINITIONS:
        return getAttributeGroupDefinitions();
      case XSDPackage.XSD_SCHEMA__TYPE_DEFINITIONS:
        return getTypeDefinitions();
      case XSDPackage.XSD_SCHEMA__MODEL_GROUP_DEFINITIONS:
        return getModelGroupDefinitions();
      case XSDPackage.XSD_SCHEMA__IDENTITY_CONSTRAINT_DEFINITIONS:
        return getIdentityConstraintDefinitions();
      case XSDPackage.XSD_SCHEMA__NOTATION_DECLARATIONS:
        return getNotationDeclarations();
      case XSDPackage.XSD_SCHEMA__ANNOTATIONS:
        return getAnnotations();
      case XSDPackage.XSD_SCHEMA__ALL_DIAGNOSTICS:
        return getAllDiagnostics();
      case XSDPackage.XSD_SCHEMA__REFERENCING_DIRECTIVES:
        return getReferencingDirectives();
      case XSDPackage.XSD_SCHEMA__ROOT_VERSION:
        return getRootVersion();
      case XSDPackage.XSD_SCHEMA__ORIGINAL_VERSION:
        return getOriginalVersion();
      case XSDPackage.XSD_SCHEMA__INCORPORATED_VERSIONS:
        return getIncorporatedVersions();
      case XSDPackage.XSD_SCHEMA__SCHEMA_FOR_SCHEMA:
        return getSchemaForSchema();
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
      case XSDPackage.XSD_SCHEMA__DOCUMENT:
        setDocument((Document)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__SCHEMA_LOCATION:
        setSchemaLocation((String)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__TARGET_NAMESPACE:
        setTargetNamespace((String)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__ATTRIBUTE_FORM_DEFAULT:
        setAttributeFormDefault((XSDForm)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__ELEMENT_FORM_DEFAULT:
        setElementFormDefault((XSDForm)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__FINAL_DEFAULT:
        getFinalDefault().clear();
        getFinalDefault().addAll((Collection<? extends XSDProhibitedSubstitutions>)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__BLOCK_DEFAULT:
        getBlockDefault().clear();
        getBlockDefault().addAll((Collection<? extends XSDDisallowedSubstitutions>)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__VERSION:
        setVersion((String)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__CONTENTS:
        getContents().clear();
        getContents().addAll((Collection<? extends XSDSchemaContent>)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__ELEMENT_DECLARATIONS:
        getElementDeclarations().clear();
        getElementDeclarations().addAll((Collection<? extends XSDElementDeclaration>)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__ATTRIBUTE_DECLARATIONS:
        getAttributeDeclarations().clear();
        getAttributeDeclarations().addAll((Collection<? extends XSDAttributeDeclaration>)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__ATTRIBUTE_GROUP_DEFINITIONS:
        getAttributeGroupDefinitions().clear();
        getAttributeGroupDefinitions().addAll((Collection<? extends XSDAttributeGroupDefinition>)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__TYPE_DEFINITIONS:
        getTypeDefinitions().clear();
        getTypeDefinitions().addAll((Collection<? extends XSDTypeDefinition>)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__MODEL_GROUP_DEFINITIONS:
        getModelGroupDefinitions().clear();
        getModelGroupDefinitions().addAll((Collection<? extends XSDModelGroupDefinition>)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__IDENTITY_CONSTRAINT_DEFINITIONS:
        getIdentityConstraintDefinitions().clear();
        getIdentityConstraintDefinitions().addAll((Collection<? extends XSDIdentityConstraintDefinition>)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__NOTATION_DECLARATIONS:
        getNotationDeclarations().clear();
        getNotationDeclarations().addAll((Collection<? extends XSDNotationDeclaration>)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__ANNOTATIONS:
        getAnnotations().clear();
        getAnnotations().addAll((Collection<? extends XSDAnnotation>)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__ALL_DIAGNOSTICS:
        getAllDiagnostics().clear();
        getAllDiagnostics().addAll((Collection<? extends XSDDiagnostic>)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__REFERENCING_DIRECTIVES:
        getReferencingDirectives().clear();
        getReferencingDirectives().addAll((Collection<? extends XSDSchemaDirective>)newValue);
        return;
      case XSDPackage.XSD_SCHEMA__INCORPORATED_VERSIONS:
        getIncorporatedVersions().clear();
        getIncorporatedVersions().addAll((Collection<? extends XSDSchema>)newValue);
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
      case XSDPackage.XSD_SCHEMA__DOCUMENT:
        setDocument(DOCUMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_SCHEMA__SCHEMA_LOCATION:
        setSchemaLocation(SCHEMA_LOCATION_EDEFAULT);
        return;
      case XSDPackage.XSD_SCHEMA__TARGET_NAMESPACE:
        setTargetNamespace(TARGET_NAMESPACE_EDEFAULT);
        return;
      case XSDPackage.XSD_SCHEMA__ATTRIBUTE_FORM_DEFAULT:
        unsetAttributeFormDefault();
        return;
      case XSDPackage.XSD_SCHEMA__ELEMENT_FORM_DEFAULT:
        unsetElementFormDefault();
        return;
      case XSDPackage.XSD_SCHEMA__FINAL_DEFAULT:
        unsetFinalDefault();
        return;
      case XSDPackage.XSD_SCHEMA__BLOCK_DEFAULT:
        unsetBlockDefault();
        return;
      case XSDPackage.XSD_SCHEMA__VERSION:
        setVersion(VERSION_EDEFAULT);
        return;
      case XSDPackage.XSD_SCHEMA__CONTENTS:
        getContents().clear();
        return;
      case XSDPackage.XSD_SCHEMA__ELEMENT_DECLARATIONS:
        getElementDeclarations().clear();
        return;
      case XSDPackage.XSD_SCHEMA__ATTRIBUTE_DECLARATIONS:
        getAttributeDeclarations().clear();
        return;
      case XSDPackage.XSD_SCHEMA__ATTRIBUTE_GROUP_DEFINITIONS:
        getAttributeGroupDefinitions().clear();
        return;
      case XSDPackage.XSD_SCHEMA__TYPE_DEFINITIONS:
        getTypeDefinitions().clear();
        return;
      case XSDPackage.XSD_SCHEMA__MODEL_GROUP_DEFINITIONS:
        getModelGroupDefinitions().clear();
        return;
      case XSDPackage.XSD_SCHEMA__IDENTITY_CONSTRAINT_DEFINITIONS:
        getIdentityConstraintDefinitions().clear();
        return;
      case XSDPackage.XSD_SCHEMA__NOTATION_DECLARATIONS:
        getNotationDeclarations().clear();
        return;
      case XSDPackage.XSD_SCHEMA__ANNOTATIONS:
        getAnnotations().clear();
        return;
      case XSDPackage.XSD_SCHEMA__ALL_DIAGNOSTICS:
        getAllDiagnostics().clear();
        return;
      case XSDPackage.XSD_SCHEMA__REFERENCING_DIRECTIVES:
        getReferencingDirectives().clear();
        return;
      case XSDPackage.XSD_SCHEMA__INCORPORATED_VERSIONS:
        getIncorporatedVersions().clear();
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
      case XSDPackage.XSD_SCHEMA__DOCUMENT:
        return DOCUMENT_EDEFAULT == null ? document != null : !DOCUMENT_EDEFAULT.equals(document);
      case XSDPackage.XSD_SCHEMA__SCHEMA_LOCATION:
        return SCHEMA_LOCATION_EDEFAULT == null ? schemaLocation != null : !SCHEMA_LOCATION_EDEFAULT.equals(schemaLocation);
      case XSDPackage.XSD_SCHEMA__TARGET_NAMESPACE:
        return TARGET_NAMESPACE_EDEFAULT == null ? targetNamespace != null : !TARGET_NAMESPACE_EDEFAULT.equals(targetNamespace);
      case XSDPackage.XSD_SCHEMA__ATTRIBUTE_FORM_DEFAULT:
        return isSetAttributeFormDefault();
      case XSDPackage.XSD_SCHEMA__ELEMENT_FORM_DEFAULT:
        return isSetElementFormDefault();
      case XSDPackage.XSD_SCHEMA__FINAL_DEFAULT:
        return isSetFinalDefault();
      case XSDPackage.XSD_SCHEMA__BLOCK_DEFAULT:
        return isSetBlockDefault();
      case XSDPackage.XSD_SCHEMA__VERSION:
        return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
      case XSDPackage.XSD_SCHEMA__CONTENTS:
        return contents != null && !contents.isEmpty();
      case XSDPackage.XSD_SCHEMA__ELEMENT_DECLARATIONS:
        return elementDeclarations != null && !elementDeclarations.isEmpty();
      case XSDPackage.XSD_SCHEMA__ATTRIBUTE_DECLARATIONS:
        return attributeDeclarations != null && !attributeDeclarations.isEmpty();
      case XSDPackage.XSD_SCHEMA__ATTRIBUTE_GROUP_DEFINITIONS:
        return attributeGroupDefinitions != null && !attributeGroupDefinitions.isEmpty();
      case XSDPackage.XSD_SCHEMA__TYPE_DEFINITIONS:
        return typeDefinitions != null && !typeDefinitions.isEmpty();
      case XSDPackage.XSD_SCHEMA__MODEL_GROUP_DEFINITIONS:
        return modelGroupDefinitions != null && !modelGroupDefinitions.isEmpty();
      case XSDPackage.XSD_SCHEMA__IDENTITY_CONSTRAINT_DEFINITIONS:
        return identityConstraintDefinitions != null && !identityConstraintDefinitions.isEmpty();
      case XSDPackage.XSD_SCHEMA__NOTATION_DECLARATIONS:
        return notationDeclarations != null && !notationDeclarations.isEmpty();
      case XSDPackage.XSD_SCHEMA__ANNOTATIONS:
        return annotations != null && !annotations.isEmpty();
      case XSDPackage.XSD_SCHEMA__ALL_DIAGNOSTICS:
        return allDiagnostics != null && !allDiagnostics.isEmpty();
      case XSDPackage.XSD_SCHEMA__REFERENCING_DIRECTIVES:
        return referencingDirectives != null && !referencingDirectives.isEmpty();
      case XSDPackage.XSD_SCHEMA__ROOT_VERSION:
        return getRootVersion() != null;
      case XSDPackage.XSD_SCHEMA__ORIGINAL_VERSION:
        return getOriginalVersion() != null;
      case XSDPackage.XSD_SCHEMA__INCORPORATED_VERSIONS:
        return incorporatedVersions != null && !incorporatedVersions.isEmpty();
      case XSDPackage.XSD_SCHEMA__SCHEMA_FOR_SCHEMA:
        return getSchemaForSchema() != null;
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
    result.append(" (document: ");
    result.append(document);
    result.append(", schemaLocation: ");
    result.append(schemaLocation);
    result.append(", targetNamespace: ");
    result.append(targetNamespace);
    result.append(", attributeFormDefault: ");
    if ((eFlags & ATTRIBUTE_FORM_DEFAULT_ESETFLAG) != 0) result.append(ATTRIBUTE_FORM_DEFAULT_EFLAG_VALUES[(eFlags & ATTRIBUTE_FORM_DEFAULT_EFLAG) >>> ATTRIBUTE_FORM_DEFAULT_EFLAG_OFFSET]); else result.append("<unset>");
    result.append(", elementFormDefault: ");
    if ((eFlags & ELEMENT_FORM_DEFAULT_ESETFLAG) != 0) result.append(ELEMENT_FORM_DEFAULT_EFLAG_VALUES[(eFlags & ELEMENT_FORM_DEFAULT_EFLAG) >>> ELEMENT_FORM_DEFAULT_EFLAG_OFFSET]); else result.append("<unset>");
    result.append(", finalDefault: ");
    result.append(finalDefault);
    result.append(", blockDefault: ");
    result.append(blockDefault);
    result.append(", version: ");
    result.append(version);
    result.append(')');
    return result.toString();
  }

  public XSDSchema getMagicSchemaForSchema()
  {
    return getMagicSchemaForSchema(getSchemaForSchemaNamespace());
  }

  protected EventListener eventListener;
  protected Node deletionNode;

  public Node getDeletionNode()
  {
    return deletionNode;
  }

  private static class DummyEventListener implements EventListener, Serializable
  {
    private static final long serialVersionUID = 1L;

    public void handleEvent(Event arg0)
    {
      // Ignore.
    }
  }

  protected EventListener getEventListener()
  {
    if (eventListener == null)
    {
      class XSDSchemaEventListener implements EventListener, Serializable
      {
        private static final long serialVersionUID = 1L;

        public void handleEvent(Event event) 
        {
          if (event instanceof MutationEvent)
          {
            MutationEvent mutationEvent = (MutationEvent)event;
            if (mutationEvent.getTarget() instanceof Node)
            {
              Node node = (Node)mutationEvent.getTarget();
              while (node.getNodeType() != Node.ELEMENT_NODE)
              {
                node = node.getParentNode();
              }
              if (mutationEvent.getAttrChange() == 0)
              {
                XSDConcreteComponent listener = getCorrespondingComponent(node.getParentNode());
                if (listener != null)
                {
                  if (event.getType().equals("DOMNodeRemoved"))
                  {
                    deletionNode = (Node)event.getTarget();
                  }
                  Node parent = node.getParentNode();
                  if (parent.getNodeType() == Node.ELEMENT_NODE)
                  {
                    listener.elementContentsChanged((Element)parent);
                  }
                  deletionNode = null;
                }
              }
              else
              {
                XSDConcreteComponent listener = getCorrespondingComponent(node);
                if (listener != null)
                {
                  listener.elementAttributesChanged((Element)node);
                }
              }
            }
          }
        }

        public Object writeReplace()
        {
          return new DummyEventListener();
        }
      }
      eventListener = new XSDSchemaEventListener();
    }
    return eventListener;
  }

  public Document updateDocument()
  {
    document = XSDParser.createDocument(); 
    return document;
  }

  protected boolean isIncrementalUpdate = true;

  public void setIncrementalUpdate(boolean isIncrementalUpdate)
  {
    this.isIncrementalUpdate = isIncrementalUpdate;
    traverseToRootForPatching();
  }

  public boolean isIncrementalUpdate()
  {
    return isIncrementalUpdate;
  }

  public void update()
  {
    boolean oldIsIncrementalUpdate = isIncrementalUpdate;
    isIncrementalUpdate = true;
    traverseToRootForPatching();
    isIncrementalUpdate = oldIsIncrementalUpdate;
  }

  public void update(boolean force)
  {
    forceResolve = force;
    update();
    forceResolve = false;
  }

  @Override
  public void reset()
  {
    super.reset();

    redefinitionMap.clear();

    reset(getAttributeDeclarations());
    reset(getAttributeGroupDefinitions());
    reset(getElementDeclarations());
    reset(getModelGroupDefinitions());
    reset(getTypeDefinitions());
    reset(getNotationDeclarations());
    reset(getIdentityConstraintDefinitions());

    forceResolve = true;
    update();
    forceResolve = false;
  }

  protected void reset(List<? extends XSDConcreteComponent> components)
  {
    for (Iterator<? extends XSDConcreteComponent> i = components.iterator(); i.hasNext(); )
    {
      XSDConcreteComponent xsdConcreteComponent = i.next();
      if (xsdConcreteComponent.getSchema() != this)
      {
        i.remove();
      }
    }
  }

  protected String pendingSchemaLocation;
  public String getPendingSchemaLocation()
  {
    return pendingSchemaLocation;
  }

  protected List<XSDSchemaImpl> schemasToRedefine;
  public List<XSDSchemaImpl> getSchemasToRedefine()
  {
    if (schemasToRedefine == null)
    {
      schemasToRedefine = new UniqueEList<XSDSchemaImpl>();
      schemasToRedefine.add(this);
    }
    return schemasToRedefine;
  }

  protected Map<XSDComponent, XSDComponent> redefinitionMap = new HashMap<XSDComponent, XSDComponent>();
  public Map<XSDComponent, XSDComponent> getRedefinitionMap()
  {
    return redefinitionMap;
  }

  public XSDSchema imported(XSDImport xsdImport)
  {
    xsdImport.setResolvedSchema(this);
    getReferencingDirectives().add(xsdImport);
    propogateComponents(xsdImport.getSchema());

    return this;
  }

  public void propogateComponents(XSDSchema otherXSDSchema)
  {
    otherXSDSchema.getAnnotations().addAll(getAnnotations());

    XSDNamedComponentImpl.mergeToSortedList
      (otherXSDSchema.getAttributeDeclarations(), getAttributeDeclarations());
    XSDNamedComponentImpl.mergeToSortedList
      (otherXSDSchema.getAttributeGroupDefinitions(), getAttributeGroupDefinitions());
    XSDNamedComponentImpl.mergeToSortedList
      (otherXSDSchema.getElementDeclarations(), getElementDeclarations());
    XSDNamedComponentImpl.mergeToSortedList
      (otherXSDSchema.getModelGroupDefinitions(), getModelGroupDefinitions());
    XSDNamedComponentImpl.mergeToSortedList
      (otherXSDSchema.getTypeDefinitions(), getTypeDefinitions());
    XSDNamedComponentImpl.mergeToSortedList
      (otherXSDSchema.getNotationDeclarations(), getNotationDeclarations());
    XSDNamedComponentImpl.mergeToSortedList
      (otherXSDSchema.getIdentityConstraintDefinitions(), getIdentityConstraintDefinitions());
  }

  public XSDSchema redefined(XSDRedefine xsdRedefine)
  {
    XSDSchema redefiningSchema = xsdRedefine.getSchema();
    xsdRedefine.setResolvedSchema(this);
    getReferencingDirectives().add(xsdRedefine);
    if (redefiningSchema != this &&
          (getTargetNamespace() == null || getTargetNamespace().equals(redefiningSchema.getTargetNamespace())))
    {
      for (XSDSchema incorporatedVersion : getIncorporatedVersions())
      {
        if (incorporatedVersion.getTargetNamespace() == null ? 
              redefiningSchema.getTargetNamespace() == null :
              incorporatedVersion.getTargetNamespace().equals(redefiningSchema.getTargetNamespace()))
        {
          for (ListIterator<XSDSchemaDirective> i = incorporatedVersion.getReferencingDirectives().listIterator(); i.hasNext(); )
          {
            XSDSchemaDirective xsdSchemaDirective = i.next();
            // This was commented out to fix 72109, i.e., to prevent stack overflow.
            // There really does need to be some kind of guard here in the general case.
            // But it's very challenging to fix this, so it's better to not overflow 
            // and to have some other unreported corner case be wrong.
            // Returning a previous results in the same thing being redefined multiple times, which is also no good.
            // I'll add a guard to avoid overflow.
            if (xsdRedefine.getSchema() == xsdSchemaDirective.getSchema() || getIncorporatedVersions().size() > 10)
            {
              ((XSDSchemaImpl)incorporatedVersion).incorporate(xsdRedefine);
              return incorporatedVersion;
            }
          }
        }
      }

      XSDSchemaImpl redefinedSchema = (XSDSchemaImpl)cloneConcreteComponent(true, true);

      if (redefinedSchema.getTargetNamespace() == null && redefiningSchema.getTargetNamespace() != null)
      {
        redefinedSchema.patch();
        redefinedSchema.setTargetNamespace(redefiningSchema.getTargetNamespace());
        redefinedSchema.patch();
      }

      // Change includes to redefines so that clones are created.
      //
      for (ListIterator<XSDSchemaContent> i = redefinedSchema.getContents().listIterator(); i.hasNext(); )
      {
        Object component = i.next();
        if (component instanceof XSDInclude)
        {
          redefinedSchema.isReconciling = true;
          i.remove();
          XSDRedefine xsdRedefineReplaced = XSDFactory.eINSTANCE.createXSDRedefine();
          xsdRedefineReplaced.setSchemaLocation(((XSDInclude)component).getSchemaLocation());
          i.add(xsdRedefineReplaced);
          redefinedSchema.isReconciling = false;
        }
      }

      redefinedSchema.pendingSchemaLocation = getSchemaLocation();
      getIncorporatedVersions().add(redefinedSchema);
      redefinedSchema.incorporate(xsdRedefine);
      return redefinedSchema;
    }

    return this;
  }

  public XSDSchema included(XSDInclude xsdInclude)
  {
    XSDSchema includingSchema = xsdInclude.getSchema();
    xsdInclude.setResolvedSchema(this);
    getReferencingDirectives().add(xsdInclude);
    if (getTargetNamespace() == null || getTargetNamespace().equals(includingSchema.getTargetNamespace()))
    {
      if (includingSchema.getTargetNamespace() != null && getTargetNamespace() == null)
      {
        for (XSDSchema xsdSchema : getIncorporatedVersions())
        {
          XSDSchemaImpl incorporatedVersion = (XSDSchemaImpl)xsdSchema;
          if (includingSchema.getTargetNamespace().equals(incorporatedVersion.getTargetNamespace()))
          {
            if (incorporatedVersion.getReferencingDirectives().isEmpty())
            {
              incorporatedVersion.incorporate(xsdInclude);
              return incorporatedVersion;
            }
            for (XSDSchemaDirective xsdSchemaDirective : incorporatedVersion.getReferencingDirectives())
            {
              if (xsdSchemaDirective instanceof XSDInclude)
              {
                incorporatedVersion.incorporate(xsdInclude);
                return incorporatedVersion;
              }
            }
          }
        }
        XSDSchemaImpl includedSchema = (XSDSchemaImpl)cloneConcreteComponent(true, true);
        includedSchema.patch();
        includedSchema.setTargetNamespace(includingSchema.getTargetNamespace());
        includedSchema.patch();
        getIncorporatedVersions().add(includedSchema);
        includedSchema.incorporate(xsdInclude);
        return includedSchema;
      }
      else
      {
        incorporate(xsdInclude);
      }
    }
    return this;
  }

  protected void incorporate(XSDSchemaCompositor xsdSchemaCompositor)
  {
    xsdSchemaCompositor.setIncorporatedSchema(this);
    XSDSchema redefiningSchema = xsdSchemaCompositor.getSchema();
    getReferencingDirectives().add(xsdSchemaCompositor);

    if (getTargetNamespace() == null && redefiningSchema.getTargetNamespace() != null)
    {
      setTargetNamespace(redefiningSchema.getTargetNamespace());
    }

    if (getPendingSchemaLocation() != null)
    {
      for (Object component : getContents())
      {
        if (component instanceof XSDSchemaDirective)
        {
          ((XSDConcreteComponentImpl)component).patch();
        }
      }
    }

    if (xsdSchemaCompositor instanceof XSDRedefine)
    {
      XSDSwitch<Object> xsdSwitch =
        new XSDSwitch<Object>()
        {
          @Override
          public Object caseXSDAttributeGroupDefinition(XSDAttributeGroupDefinition xsdAttributeGroupDefinition)
          {
            XSDAttributeGroupDefinition redefinedAttributeGroupDefinition = 
              resolveAttributeGroupDefinition(xsdAttributeGroupDefinition.getName());
            for (XSDSchemaImpl schemaToRedefine : getSchemasToRedefine())
            {
              int index = schemaToRedefine.getAttributeGroupDefinitions().indexOf(redefinedAttributeGroupDefinition);
              if (index != -1)
              {
                schemaToRedefine.getAttributeGroupDefinitions().set(index, xsdAttributeGroupDefinition);
                schemaToRedefine.redefinitionMap.put(xsdAttributeGroupDefinition, redefinedAttributeGroupDefinition);
              }
            }
            return this;
          }
          @Override
          public Object caseXSDSimpleTypeDefinition(XSDSimpleTypeDefinition xsdSimpleTypeDefinition)
          {
            XSDSimpleTypeDefinition redefinedSimpleTypeDefinition = 
              resolveSimpleTypeDefinition(xsdSimpleTypeDefinition.getName());
            for (XSDSchemaImpl schemaToRedefine : getSchemasToRedefine())
            {
              int index = schemaToRedefine.getTypeDefinitions().indexOf(redefinedSimpleTypeDefinition);
              if (index != -1)
              {
                schemaToRedefine.getTypeDefinitions().set(index, xsdSimpleTypeDefinition);
                schemaToRedefine.redefinitionMap.put(xsdSimpleTypeDefinition, redefinedSimpleTypeDefinition);
              }
            }
            return this;
          }
          @Override
          public Object caseXSDComplexTypeDefinition(XSDComplexTypeDefinition xsdComplexTypeDefinition)
          {
            XSDComplexTypeDefinition redefinedComplexTypeDefinition = 
              resolveComplexTypeDefinition(xsdComplexTypeDefinition.getName());
            if (xsdComplexTypeDefinition != redefinedComplexTypeDefinition)
            {
              for (XSDSchemaImpl schemaToRedefine : getSchemasToRedefine())
              {
                int index = schemaToRedefine.getTypeDefinitions().indexOf(redefinedComplexTypeDefinition);
                if (index != -1)
                {
                  schemaToRedefine.getTypeDefinitions().set(index, xsdComplexTypeDefinition);
                  schemaToRedefine.redefinitionMap.put(xsdComplexTypeDefinition, redefinedComplexTypeDefinition);
                }
              }
            }
            return this;
          }
          @Override
          public Object caseXSDModelGroupDefinition(XSDModelGroupDefinition xsdModelGroupDefinition)
          {
            XSDModelGroupDefinition redefinedModelGroupDefinition = 
              resolveModelGroupDefinition(xsdModelGroupDefinition.getName());
            for (XSDSchemaImpl schemaToRedefine : getSchemasToRedefine())
            {
              int index = schemaToRedefine.getModelGroupDefinitions().indexOf(redefinedModelGroupDefinition);
              if (index != -1)
              {
                schemaToRedefine.getModelGroupDefinitions().set(index, xsdModelGroupDefinition);
                schemaToRedefine.redefinitionMap.put(xsdModelGroupDefinition, redefinedModelGroupDefinition);
              }
            }
            return this;
          }
        };

      for (XSDRedefineContent xsdRedefineContent : ((XSDRedefine)xsdSchemaCompositor).getContents())
      {
        xsdSwitch.doSwitch(xsdRedefineContent);
      }
    }

    if (((XSDSchemaImpl)redefiningSchema).getPendingSchemaLocation() != null)
    {
      ((XSDSchemaImpl)redefiningSchema).getSchemasToRedefine().addAll(getSchemasToRedefine());
    }

    propogateComponents(redefiningSchema);

    if (getPendingSchemaLocation() == null)
    {
      patch();
    }

    ((XSDSchemaImpl)redefiningSchema).getRedefinitionMap().putAll(getRedefinitionMap());
  }

  @Override
  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDSchemaImpl clonedSchema = (XSDSchemaImpl)getXSDFactory().createXSDSchema();
    clonedSchema.isReconciling = true;

    clonedSchema.setTargetNamespace(getTargetNamespace());

    if (isSetAttributeFormDefault())
    {
      clonedSchema.setAttributeFormDefault(getAttributeFormDefault());
    }
    if (isSetElementFormDefault())
    {
      clonedSchema.setElementFormDefault(getElementFormDefault());
    }
    if (isSetFinalDefault())
    {
      if (!getFinalDefault().isEmpty())
      {
        clonedSchema.getFinalDefault().addAll(getFinalDefault());
      }
      else
      {
        clonedSchema.getFinalDefault().clear();
      }
    }
    if (isSetBlockDefault())
    {
      if (!getBlockDefault().isEmpty())
      {
        clonedSchema.getBlockDefault().addAll(getBlockDefault());
      }
      else
      {
        clonedSchema.getBlockDefault().clear();
      }
    }

    // clonedSchema.setSchemaLocation(getSchemaLocation());

    clonedSchema.setSchemaForSchemaQNamePrefix(getSchemaForSchemaQNamePrefix());
    clonedSchema.getQNamePrefixToNamespaceMap().putAll(getQNamePrefixToNamespaceMap());

    if (deep)
    {
      if (!getContents().isEmpty())
      {
        clonedSchema.getContents().addAll(cloneConcreteComponents(getContents(), true, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedSchema.setElement(getElement());
    }

    clonedSchema.isReconciling = shareDOM;
    return clonedSchema;
  }
}
