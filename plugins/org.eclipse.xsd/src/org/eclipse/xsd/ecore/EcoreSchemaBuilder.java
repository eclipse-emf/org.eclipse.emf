/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: EcoreSchemaBuilder.java,v 1.13 2006/08/28 12:25:42 emerks Exp $
 */
package org.eclipse.xsd.ecore;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDAttributeUseCategory;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDComponent;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDConstraint;
import org.eclipse.xsd.XSDDerivationMethod;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDEnumerationFacet;
import org.eclipse.xsd.XSDFacet;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDFeature;
import org.eclipse.xsd.XSDForm;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDLengthFacet;
import org.eclipse.xsd.XSDMaxInclusiveFacet;
import org.eclipse.xsd.XSDMaxLengthFacet;
import org.eclipse.xsd.XSDMinLengthFacet;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.XSDWildcard;
import org.eclipse.xsd.ecore.MapBuilder;
import org.eclipse.xsd.util.XSDConstants;
import org.eclipse.xsd.util.XSDParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;


/**
 * This class produces an XSDSchema given an Ecore EPackage.
 */
public class EcoreSchemaBuilder extends MapBuilder
{
  /**
   * The schema for schema namespace version to be used for new schemas.
   */
  protected String defaultXMLSchemaNamespace = XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001;
  
  /**
   * The prefix to be used for the schema for schema namespace version in new schemas.
   */
  protected String defaultXMLSchemaNamespacePrefix = "xsd";
  
  /**
   * The extended meta data used to determine the schema structure.
   */
  protected ExtendedMetaData extendedMetaData;
  
  /**
   * Store the reverse map too.
   */
  protected Map eModelElementToXSDComponentMap = new HashMap();

  public EcoreSchemaBuilder(ExtendedMetaData extendedMetaData)
  {
    this.extendedMetaData = extendedMetaData;
  }
  
  protected void map(XSDComponent xsdComponent, EModelElement eModelElement)
  {
    super.map(xsdComponent, eModelElement);
    
    // Store the reverse map as well.
    //
    eModelElementToXSDComponentMap.put(eModelElement, xsdComponent);
  }
  
  /*
  public Collection generate(EPackage ePackage)
  {
    XSDSchema xsdSchema = getSchema(ePackage);
    
    Collection result = new ArrayList();
    result.add(xsdSchema);
    if (mapper != null)
    {
      result.add(mapper.getRoot());
    }

    return result;
  }
  */
  
  public XSDSchema getSchema(EPackage ePackage)
  {
    XSDSchema xsdSchema = (XSDSchema)eModelElementToXSDComponentMap.get(ePackage);
    if (xsdSchema == null)
    {
      xsdSchema = buildSchema(ePackage);
    }
    return xsdSchema;
  }
  
  protected String getQualifiedPackageName(EPackage ePackage)
  {
    return ePackage.getName();
  }
  
  protected XSDSchema buildSchema(EPackage ePackage)
  {
    XSDSchema xsdSchema = XSDFactory.eINSTANCE.createXSDSchema();

    addInput(ePackage);
    addOutput(xsdSchema);
    map(xsdSchema, ePackage);

    // Set the namespace.
    //
    String targetNamespace = extendedMetaData.getNamespace(ePackage);
    xsdSchema.setTargetNamespace(targetNamespace);
    
    // Choose the schema for schema namespace and prefix.
    //
    Map namespaces = xsdSchema.getQNamePrefixToNamespaceMap();
    xsdSchema.setSchemaForSchemaQNamePrefix(defaultXMLSchemaNamespacePrefix);
    namespaces.put(defaultXMLSchemaNamespacePrefix, defaultXMLSchemaNamespace);

    // Ensure that a prefix is assigned for the target namespace.
    //
    handlePrefix(namespaces, ePackage.getNsPrefix(), targetNamespace);
    
    // Create the node so that annotations can be added to the DOM.
    //
    xsdSchema.updateElement();
    
    buildAnnotations(xsdSchema, ePackage);

    // Remember the package name and prefix.
    //
    createEcoreAnnotation(xsdSchema, "package", getQualifiedPackageName(ePackage));
    createEcoreAnnotation(xsdSchema, "nsPrefix", ePackage.getNsPrefix());
    
    // Generate the global elements and attributes.
    //
    EClass documentRootEClass = extendedMetaData.getDocumentRoot(ePackage);
    if (documentRootEClass == null)
    {
      for (Iterator i = ePackage.getEClassifiers().iterator(); i.hasNext(); )
      {
        EClassifier eClassifier = (EClassifier)i.next();
        if (eClassifier instanceof EClass)
        {
          EClass eClass = (EClass)eClassifier;
          if (extendedMetaData.getContentKind(eClass) == ExtendedMetaData.UNSPECIFIED_CONTENT)
          {
            buildGlobalElement(xsdSchema, eClass);
          }
        }
      }
    }
    else
    {
      createEcoreAnnotation(xsdSchema, "documentRoot", documentRootEClass.getName());
      
      for (int i = 0, size = documentRootEClass.getFeatureCount(); i < size; ++i)
      {
        EStructuralFeature eStructuralFeature = documentRootEClass.getEStructuralFeature(i);
        if (extendedMetaData.getName(eStructuralFeature).indexOf(':') == -1)
        {
          buildGlobalFeature(xsdSchema, eStructuralFeature);
        }
      }
    }
    
    // Generate the type definitions.
    //
    for (Iterator i = ePackage.getEClassifiers().iterator(); i.hasNext(); )
    {
      EClassifier eClassifier = (EClassifier)i.next();
      if (eClassifier != documentRootEClass)
      {
        buildTypeDefinition(xsdSchema, eClassifier);
      }
    }
    
    buildAnnotations(xsdSchema, ePackage);

    return xsdSchema;
  }

  protected XSDElementDeclaration buildGlobalElement(XSDSchema xsdSchema, EClass eClass)
  {
    XSDElementDeclaration xsdElementDeclaration = XSDFactory.eINSTANCE.createXSDElementDeclaration();
    xsdElementDeclaration.setName(extendedMetaData.getName(eClass));

    XSDTypeDefinition xsdTypeDefinition = xsdSchema.resolveTypeDefinitionURI(getURI(eClass));
    handleImport(xsdSchema, xsdTypeDefinition);
    xsdElementDeclaration.setTypeDefinition(xsdTypeDefinition);
 
    xsdSchema.getContents().add(xsdElementDeclaration);
    map(xsdElementDeclaration, eClass);

    createEcoreAnnotation(xsdElementDeclaration, "ignore", "true");

    return xsdElementDeclaration;
  }
  
  protected XSDFeature buildGlobalFeature(XSDSchema xsdSchema, EStructuralFeature eStructuralFeature)
  {
    switch (extendedMetaData.getFeatureKind(eStructuralFeature))
    {
      case ExtendedMetaData.ELEMENT_FEATURE:
      {
        XSDElementDeclaration xsdElementDeclaration = buildGlobalElement(xsdSchema, eStructuralFeature);
        return xsdElementDeclaration;
      }
      case ExtendedMetaData.ATTRIBUTE_FEATURE:
      {
        XSDAttributeDeclaration xsdAttributeDeclaration = buildGlobalAttribute(xsdSchema, eStructuralFeature);
        return xsdAttributeDeclaration;
      }
      case ExtendedMetaData.ELEMENT_WILDCARD_FEATURE:
      case ExtendedMetaData.ATTRIBUTE_WILDCARD_FEATURE:
      case ExtendedMetaData.SIMPLE_FEATURE:
      case ExtendedMetaData.GROUP_FEATURE:
      default:
      {
        return null;
      }
    }
  }
  
  protected XSDElementDeclaration buildGlobalElement(XSDSchema xsdSchema, EStructuralFeature eStructuralFeature)
  {
    XSDElementDeclaration xsdElementDeclaration = XSDFactory.eINSTANCE.createXSDElementDeclaration();
    xsdElementDeclaration.setName(extendedMetaData.getName(eStructuralFeature));

    XSDTypeDefinition xsdTypeDefinition = xsdSchema.resolveTypeDefinitionURI(getURI(eStructuralFeature.getEType()));
    handleImport(xsdSchema, xsdTypeDefinition);
    xsdElementDeclaration.setTypeDefinition(xsdTypeDefinition);
        
    if (isWrapperType(eStructuralFeature.getEType()))
    {
      xsdElementDeclaration.setNillable(true);
    }
    
    String defaultValue = eStructuralFeature.getDefaultValueLiteral();
    if (defaultValue != null)
    {
      xsdElementDeclaration.setConstraint(XSDConstraint.DEFAULT_LITERAL);
      xsdElementDeclaration.setLexicalValue(defaultValue);
    }
        
    xsdSchema.getContents().add(xsdElementDeclaration);
    map(xsdElementDeclaration, eStructuralFeature);
        
    buildAnnotations(xsdElementDeclaration, eStructuralFeature);

    return xsdElementDeclaration;
  }
  
  protected XSDAttributeDeclaration buildGlobalAttribute(XSDSchema xsdSchema, EStructuralFeature eStructuralFeature)
  {
    XSDAttributeDeclaration xsdAttributeDeclaration = XSDFactory.eINSTANCE.createXSDAttributeDeclaration();
    xsdAttributeDeclaration.setName(extendedMetaData.getName(eStructuralFeature));
 
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = xsdSchema.resolveSimpleTypeDefinitionURI(getURI(eStructuralFeature.getEType()));
    handleImport(xsdSchema, xsdSimpleTypeDefinition);
    xsdAttributeDeclaration.setTypeDefinition(xsdSimpleTypeDefinition);
         
    String defaultValue = eStructuralFeature.getDefaultValueLiteral();
    if (defaultValue != null)
    {
      xsdAttributeDeclaration.setConstraint(XSDConstraint.DEFAULT_LITERAL);
      xsdAttributeDeclaration.setLexicalValue(defaultValue);
    }
         
    xsdSchema.getContents().add(xsdAttributeDeclaration);
    map(xsdAttributeDeclaration, eStructuralFeature);

    buildAnnotations(xsdAttributeDeclaration, eStructuralFeature);

    return xsdAttributeDeclaration;
  }
  
  protected XSDTypeDefinition buildTypeDefinition(XSDSchema xsdSchema, EClassifier eClassifier)
  {
    if (eClassifier instanceof EClass)
    {
      return buildComplexTypeDefinition(xsdSchema, (EClass)eClassifier);
    }
    else 
    {
      return buildSimpleTypeDefinition(xsdSchema, (EDataType)eClassifier);
    }
  }
  
  protected XSDSimpleTypeDefinition buildSimpleTypeDefinition(XSDSchema xsdSchema, EDataType eDataType)
  {
    String name = extendedMetaData.getName(eDataType);
    boolean isAnonymousWrapperType =  name.endsWith(":Object");
    if (isAnonymousWrapperType && EcorePackage.eNS_URI.equals(xsdSchema.getTargetNamespace()))
    {
      name = eDataType.getName();
      isAnonymousWrapperType = false;
    }
    if (!isAnonymousWrapperType)
    {
      XSDSimpleTypeDefinition xsdSimpleTypeDefinition = XSDFactory.eINSTANCE.createXSDSimpleTypeDefinition();
    
      xsdSimpleTypeDefinition.setName(name);
      
      String instanceClass = null;
      EDataType baseType = extendedMetaData.getBaseType(eDataType);
      if (baseType != null)
      {
        XSDSimpleTypeDefinition baseTypeDefinition =  xsdSchema.resolveSimpleTypeDefinitionURI(getURI(baseType));
        handleImport(xsdSchema, baseTypeDefinition);
        xsdSimpleTypeDefinition.setBaseTypeDefinition(baseTypeDefinition);
        
        // Produce an instance class declaration if the type is different from that of the base.
        //
        instanceClass = eDataType.getInstanceClassName();
        if (instanceClass != null && instanceClass.equals(baseType.getInstanceClassName()))
        {
          instanceClass = null;
        }
      }
      else
      {
        EDataType itemType = extendedMetaData.getItemType(eDataType);
        if (itemType != null)
        {
          XSDSimpleTypeDefinition itemTypeDefinition =  xsdSchema.resolveSimpleTypeDefinitionURI(getURI(itemType));
          handleImport(xsdSchema, itemTypeDefinition);
          xsdSimpleTypeDefinition.setItemTypeDefinition(itemTypeDefinition);
        }
        else
        {
          List memberTypes = extendedMetaData.getMemberTypes(eDataType);
          if (!memberTypes.isEmpty())
          {
            for (Iterator i = memberTypes.iterator(); i.hasNext(); )
            {
              EDataType memberType = (EDataType)i.next();
              XSDSimpleTypeDefinition memberTypeDefinition =  xsdSchema.resolveSimpleTypeDefinitionURI(getURI(memberType));
              handleImport(xsdSchema, memberTypeDefinition);
              xsdSimpleTypeDefinition.getMemberTypeDefinitions().add(memberTypeDefinition);
            }
          }
          else
          {
            if (eDataType instanceof EEnum)
            {
              xsdSimpleTypeDefinition.setBaseTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("string"));
            }
            else
            {
              instanceClass = eDataType.getInstanceClassName();
            }
          }
        }
      }
      
      xsdSchema.getContents().add(xsdSimpleTypeDefinition);
      map(xsdSimpleTypeDefinition, eDataType);
      
      if (instanceClass != null)
      {
        createEcoreAnnotation(xsdSimpleTypeDefinition, "instanceClass", instanceClass);
      }
      
      String maxInclusive = extendedMetaData.getMaxInclusiveFacet(eDataType);
      if (maxInclusive != null)
      {
        XSDMaxInclusiveFacet xsdMaxInclusiveFacet = XSDFactory.eINSTANCE.createXSDMaxInclusiveFacet();
        xsdMaxInclusiveFacet.setLexicalValue(maxInclusive);
        xsdSimpleTypeDefinition.getFacetContents().add(xsdMaxInclusiveFacet);
      }
      
      String ecoreName = eDataType.getName();
      if (!name.equals(ecoreName) || Character.isLowerCase(ecoreName.charAt(0)) || ecoreName.indexOf('_') != -1)
      {
        createEcoreAnnotation(xsdSimpleTypeDefinition, "name", ecoreName);
      }
      
      String constraints = EcoreUtil.getAnnotation(eDataType, EcorePackage.eNS_URI, "constraints");
      if (constraints != null)
      {
        createEcoreAnnotation(xsdSimpleTypeDefinition, "constraints", constraints);
      }

      if (!eDataType.isSerializable())
      {
        createEcoreAnnotation(xsdSimpleTypeDefinition, "serializable", "false");
      }
        
      if (eDataType instanceof EEnum)
      {
        EEnum eEnum = (EEnum)eDataType;
        
        for (ListIterator literals = eEnum.getELiterals().listIterator(); literals.hasNext();)
        {
          EEnumLiteral literal = (EEnumLiteral)literals.next();
          XSDEnumerationFacet facet = XSDFactory.eINSTANCE.createXSDEnumerationFacet();
          facet.setLexicalValue(literal.getLiteral());
          xsdSimpleTypeDefinition.getFacetContents().add(facet);
          map(facet, literal);
          
          if (!literal.getLiteral().equals(literal.getName()))
          {
            createEcoreAnnotation(facet, "name", literal.getName());
          }
          
          if (literal.getValue() != literals.previousIndex())
          {
            createEcoreAnnotation(facet, "value", Integer.toString(literal.getValue()));
          }
        }
      }
      
      buildAnnotations(xsdSimpleTypeDefinition, eDataType);

      return xsdSimpleTypeDefinition;
    }
    else
    {
      return null;
    }
  }
  
  protected XSDComplexTypeDefinition buildComplexTypeDefinition(XSDSchema xsdSchema, EClass eClass)
  {
    XSDComplexTypeDefinition xsdComplexTypeDefinition = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
    
    String name = extendedMetaData.getName(eClass);
    xsdComplexTypeDefinition.setName(name);
    
    if (eClass.isAbstract())
    {
      xsdComplexTypeDefinition.setAbstract(true);
    }
      
    List superClasses = eClass.getESuperTypes();
    if (!superClasses.isEmpty())
    {
      Iterator i = superClasses.iterator();
      EClass superClass = (EClass)i.next();
      XSDTypeDefinition baseType = xsdSchema.resolveTypeDefinitionURI(getURI(superClass));
      if (!XSDConstants.isURType(baseType))
      {
        handleImport(xsdSchema, baseType);
        xsdComplexTypeDefinition.setDerivationMethod(XSDDerivationMethod.EXTENSION_LITERAL);
        xsdComplexTypeDefinition.setBaseTypeDefinition(baseType);
      }
      
      xsdSchema.getContents().add(xsdComplexTypeDefinition);
      
      if (i.hasNext())
      {
        StringBuffer additionalSuperTypes = new StringBuffer();
        do
        {
          superClass = (EClass)i.next();
          baseType = xsdSchema.resolveTypeDefinitionURI(getURI(superClass));
          if (!XSDConstants.isURType(baseType))
          {
            handleImport(xsdSchema, baseType);
            String prefix = 
              handlePrefix
                (xsdSchema.getQNamePrefixToNamespaceMap(),
                  superClass.getEPackage().getNsPrefix(),
                  baseType.getTargetNamespace());
            additionalSuperTypes.append(prefix);
            additionalSuperTypes.append(':');
            additionalSuperTypes.append(baseType.getName());
            additionalSuperTypes.append(' ');
          }
        }
        while (i.hasNext());
        createEcoreAnnotation(xsdComplexTypeDefinition, "implements", additionalSuperTypes.toString().trim());
      }
    }
    else
    {
      xsdSchema.getContents().add(xsdComplexTypeDefinition);
    }
        
    map(xsdComplexTypeDefinition, eClass);

    String instanceClass =  eClass.getInstanceClassName();
    if (instanceClass != null)
    {
      createEcoreAnnotation(xsdComplexTypeDefinition, "instanceClass", instanceClass);
    }
    
    String ecoreName = eClass.getName();
    if (!name.equals(ecoreName) || Character.isLowerCase(ecoreName.charAt(0)) || ecoreName.indexOf('_') != -1)
    {
      createEcoreAnnotation(xsdComplexTypeDefinition, "name", ecoreName);
    }
    
    if (eClass.isInterface())
    {
      createEcoreAnnotation(xsdComplexTypeDefinition, "interface", "true");
    }
        
    String constraints = EcoreUtil.getAnnotation(eClass, EcorePackage.eNS_URI, "constraints");
    if (constraints != null)
    {
      createEcoreAnnotation(xsdComplexTypeDefinition, "constraints", constraints);
    }

    for (Iterator i = eClass.getEStructuralFeatures().iterator(); i.hasNext(); )
    {
      EStructuralFeature eStructuralFeature = (EStructuralFeature)i.next();
      buildContentFeature(xsdComplexTypeDefinition, eStructuralFeature);
    }
    
    List eOperations = eClass.getEOperations();
    if (!eOperations.isEmpty())
    {
      XSDAnnotation xsdAnnotation = XSDFactory.eINSTANCE.createXSDAnnotation();
      xsdComplexTypeDefinition.setAnnotation(xsdAnnotation);
      Element applicationInformation = xsdAnnotation.createApplicationInformation(EcorePackage.eNS_URI);
      xsdAnnotation.getElement().appendChild(applicationInformation);
      createEcoreAnnotation(xsdSchema.getQNamePrefixToNamespaceMap(), applicationInformation, "key", "operations");
      Document document = xsdSchema.getDocument();
      Map qNamePrefixToNamespaceMap = xsdSchema.getQNamePrefixToNamespaceMap();
      for (Iterator i = eOperations.iterator(); i.hasNext(); )
      {
        EOperation eOperation = (EOperation)i.next();
        Element operation = document.createElementNS(null, "operation");
        operation.setAttributeNS(null, "name", eOperation.getName());
        applicationInformation.appendChild(operation);
        buildAnnotation(eOperation, operation);
        EClassifier returnEType = eOperation.getEType();
        if (returnEType != null)
        {
          XSDTypeDefinition returnType = xsdSchema.resolveTypeDefinitionURI(getURI(returnEType));
          if (!XSDConstants.isURType(returnType))
          {
            handleImport(xsdSchema, returnType);
            String prefix = 
              handlePrefix
                (qNamePrefixToNamespaceMap,
                  returnEType.getEPackage().getNsPrefix(),
                  returnType.getTargetNamespace());
            operation.setAttributeNS(null, "type", prefix + ":" + returnType.getName());
          }
        }
        List exceptions = eOperation.getEExceptions();
        if (!exceptions.isEmpty())
        {
          StringBuffer stringBuffer = new StringBuffer();
          for (Iterator j = eOperation.getEExceptions().iterator(); j.hasNext(); )
          {
            EClassifier eClassifier = (EClassifier)j.next();
            XSDTypeDefinition exceptionType = xsdSchema.resolveTypeDefinitionURI(getURI(eClassifier));
            handleImport(xsdSchema, exceptionType);
            String prefix = 
              handlePrefix
                (qNamePrefixToNamespaceMap,
                  eClassifier.getEPackage().getNsPrefix(),
                  exceptionType.getTargetNamespace());
            stringBuffer.append(prefix);
            stringBuffer.append(':');
            stringBuffer.append(exceptionType.getName());
            stringBuffer.append(' ');
          }
          
          operation.setAttributeNS(null, "exceptions", stringBuffer.substring(0, stringBuffer.length() - 1));
        }
        
        for (Iterator j = eOperation.getEParameters().iterator(); j.hasNext(); )
        {
          EParameter eParameter = (EParameter)j.next();
          Element parameter = document.createElementNS(null, "parameter");
          parameter.setAttributeNS(null, "name", eParameter.getName());
          operation.appendChild(parameter);
          buildAnnotation(eParameter, parameter);
          EClassifier parameterEType = eParameter.getEType();
          XSDTypeDefinition parameterType = xsdSchema.resolveTypeDefinitionURI(getURI(parameterEType));
          if (!XSDConstants.isURType(parameterType))
          {
            handleImport(xsdSchema, parameterType);
            String prefix = 
              handlePrefix
                (qNamePrefixToNamespaceMap,
                  parameterEType.getEPackage().getNsPrefix(),
                  parameterType.getTargetNamespace());
            parameter.setAttributeNS(null, "type", prefix + ":" + parameterType.getName());
          }

          if (eParameter.getLowerBound() != 0)
          {
            parameter.setAttributeNS(null, "lowerBound", Integer.toString(eParameter.getLowerBound()));
          }
          if (eParameter.getUpperBound() != 1)
          {
            parameter.setAttributeNS(null, "upperBound", Integer.toString(eParameter.getUpperBound()));
          }
          if (!eParameter.isOrdered())
          {
            parameter.setAttributeNS(null, "ordered", "false");
          }
          if (!eParameter.isUnique())
          {
            parameter.setAttributeNS(null, "unique", "false");
          }
        }
        
        if (eOperation.getLowerBound() != 0)
        {
          operation.setAttributeNS(null, "lowerBound", Integer.toString(eOperation.getLowerBound()));
        }
        if (eOperation.getUpperBound() != 1)
        {
          operation.setAttributeNS(null, "upperBound", Integer.toString(eOperation.getUpperBound()));
        }
        if (!eOperation.isOrdered())
        {
          operation.setAttributeNS(null, "ordered", "false");
        }
        if (!eOperation.isUnique())
        {
          operation.setAttributeNS(null, "unique", "false");
        }

        String body = EcoreUtil.getAnnotation(eOperation, GEN_MODEL_PACKAGE_NS_URI, "body");
        if (body != null)
        {
          Element bodyElement = document.createElementNS(null, "body");
          bodyElement.appendChild(document.createTextNode(body));
          operation.appendChild(bodyElement);
        }
      }
    }
    
    buildAnnotations(xsdComplexTypeDefinition, eClass);

    return xsdComplexTypeDefinition;
  }
  
  protected XSDComponent buildContentFeature(XSDComplexTypeDefinition xsdComplexTypeDefinition, EStructuralFeature eStructuralFeature)
  {
    switch (extendedMetaData.getFeatureKind(eStructuralFeature))
    {
      case ExtendedMetaData.ELEMENT_FEATURE:
      {
        XSDParticle xsdParticle = buildElementParticle(xsdComplexTypeDefinition, eStructuralFeature);
        return xsdParticle;
      }
      case ExtendedMetaData.ATTRIBUTE_FEATURE:
      {
        XSDAttributeUse xsdAttributeUse = buildAttributeUse(xsdComplexTypeDefinition, eStructuralFeature);
        return xsdAttributeUse;
      }
      case ExtendedMetaData.GROUP_FEATURE:
      {
        XSDParticle xsdParticle = buildModelGroupParticle(xsdComplexTypeDefinition, eStructuralFeature);
        return xsdParticle;
      }
      case ExtendedMetaData.ELEMENT_WILDCARD_FEATURE:
      {
        XSDWildcard xsdWildcard = buildElementWildcard(xsdComplexTypeDefinition, eStructuralFeature);
        return xsdWildcard;
      }
      case ExtendedMetaData.ATTRIBUTE_WILDCARD_FEATURE:
      {
        XSDWildcard xsdWildcard = buildAttributeWildcard(xsdComplexTypeDefinition, eStructuralFeature);
        return xsdWildcard;
      }
      case ExtendedMetaData.SIMPLE_FEATURE:
      {
        XSDSimpleTypeDefinition xsdSimpleTypeDefinition = buildSimpleContent(xsdComplexTypeDefinition, eStructuralFeature);
        return xsdSimpleTypeDefinition;
      }
      default:
      {
        if (eStructuralFeature instanceof EAttribute)
        {
          EAttribute eAttribute = (EAttribute)eStructuralFeature;
          if (eAttribute.isMany())
          {
            return buildElementParticle(xsdComplexTypeDefinition, eAttribute);
          }
          else
          {
            return buildAttributeUse(xsdComplexTypeDefinition, eAttribute);
          }
        }
        else
        {
          EReference eReference = (EReference)eStructuralFeature;
          if (eReference.isContainment())
          {
            return buildElementParticle(xsdComplexTypeDefinition, eReference);
          }
          else if (eReference.isContainer() && eReference.isTransient())
          {
            return null;
          }
          else
          {
            return buildAttributeUse(xsdComplexTypeDefinition, eReference);
          }
        }
      }
    }
  }
  
  protected XSDParticle buildModelGroupParticle(XSDComplexTypeDefinition xsdComplexTypeDefinition, EStructuralFeature eStructuralFeature)
  {
    XSDModelGroup xsdModelGroup = findOrCreateModelGroup(xsdComplexTypeDefinition);
   
    XSDModelGroup modelGroup = XSDFactory.eINSTANCE.createXSDModelGroup();
    modelGroup.setCompositor(XSDCompositor.CHOICE_LITERAL);
    XSDParticle xsdParticle = XSDFactory.eINSTANCE.createXSDParticle();
    xsdParticle.setContent(modelGroup);
        
    if (eStructuralFeature.getUpperBound() != 1)
    {
      xsdParticle.setMaxOccurs(eStructuralFeature.getUpperBound());
    }
       
    xsdModelGroup.getContents().add(xsdParticle);
    map(xsdParticle, eStructuralFeature);
        
    createEcoreAnnotation(xsdParticle, "featureName", eStructuralFeature.getName());

    buildAnnotations(xsdModelGroup, eStructuralFeature);

    return xsdParticle;
  }
  
  protected XSDWildcard buildElementWildcard(XSDComplexTypeDefinition xsdComplexTypeDefinition, EStructuralFeature eStructuralFeature)
  {
    String name = extendedMetaData.getName(eStructuralFeature);
    if (":mixed".equals(name))
    {
      xsdComplexTypeDefinition.setMixed(true);
      return null;
    }
    else
    {
      XSDWildcard xsdWildcard = XSDFactory.eINSTANCE.createXSDWildcard();
      xsdWildcard.setStringLexicalNamespaceConstraint
        (BasicExtendedMetaData.getEncodedWildcards
           (extendedMetaData.getNamespace(eStructuralFeature.getEContainingClass()),
            extendedMetaData.getWildcards(eStructuralFeature)));
      XSDModelGroup xsdModelGroup = findOrCreateModelGroup(xsdComplexTypeDefinition);
      XSDParticle particle = XSDFactory.eINSTANCE.createXSDParticle();
      particle.setContent(xsdWildcard);
      xsdModelGroup.getContents().add(particle);
      map(particle, eStructuralFeature);
          
      createEcoreAnnotation(xsdWildcard, "name", eStructuralFeature.getName());
          
      buildAnnotations(xsdWildcard, eStructuralFeature);

      return xsdWildcard;
    }
  }
  
  protected XSDWildcard buildAttributeWildcard(XSDComplexTypeDefinition xsdComplexTypeDefinition, EStructuralFeature eStructuralFeature)
  {
    XSDWildcard xsdWildcard = XSDFactory.eINSTANCE.createXSDWildcard();
    xsdWildcard.setStringLexicalNamespaceConstraint
      (BasicExtendedMetaData.getEncodedWildcards
         (extendedMetaData.getNamespace(eStructuralFeature.getEContainingClass()),
          extendedMetaData.getWildcards(eStructuralFeature)));
    xsdComplexTypeDefinition.setAttributeWildcardContent(xsdWildcard);
        
    map(xsdWildcard, eStructuralFeature);
          
    createEcoreAnnotation(xsdWildcard, "name", eStructuralFeature.getName());
        
    buildAnnotations(xsdWildcard, eStructuralFeature);

    return xsdWildcard;
  }
  
  protected XSDSimpleTypeDefinition buildSimpleContent(XSDComplexTypeDefinition xsdComplexTypeDefinition, EStructuralFeature eStructuralFeature)
  {
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = XSDFactory.eINSTANCE.createXSDSimpleTypeDefinition();
    XSDSimpleTypeDefinition baseType =  xsdComplexTypeDefinition.resolveSimpleTypeDefinitionURI(getURI(eStructuralFeature.getEType()));
    handleImport(xsdComplexTypeDefinition.getSchema(), baseType);
    xsdComplexTypeDefinition.setBaseTypeDefinition(baseType);
    xsdComplexTypeDefinition.setDerivationMethod(XSDDerivationMethod.EXTENSION_LITERAL);
    xsdComplexTypeDefinition.setContent(xsdSimpleTypeDefinition);
        
    buildAnnotations(xsdSimpleTypeDefinition, eStructuralFeature);

    return xsdSimpleTypeDefinition;
  }
  
  protected XSDAttributeUse buildAttributeUse(XSDComplexTypeDefinition xsdComplexTypeDefinition, EStructuralFeature eStructuralFeature)
  {
    String namespace = extendedMetaData.getNamespace(eStructuralFeature);
    String name = extendedMetaData.getName(eStructuralFeature);
    
    // It must be a ref if it refers to another namespace.
    // If it refers to this namespace, 
    // it's treated as a ref,
    // if this feature has the same type as the feature for matching global attribute.
    //
    String typeNamespace = extendedMetaData.getNamespace(eStructuralFeature.getEContainingClass());
    boolean isRef = namespace != null && !namespace.equals(typeNamespace);
    if (namespace == null ? typeNamespace == null : namespace.equals(typeNamespace))
    {
      EStructuralFeature globalFeature = extendedMetaData.getAttribute(namespace, name);
      isRef = globalFeature != null && globalFeature.getEType() == eStructuralFeature.getEType();
    }
    
    XSDAttributeUse xsdAttributeUse = XSDFactory.eINSTANCE.createXSDAttributeUse();
    XSDAttributeDeclaration xsdAttributeDeclaration = XSDFactory.eINSTANCE.createXSDAttributeDeclaration();
    
    EClassifier referenceType = null;
    if (isRef)
    {
      XSDAttributeDeclaration referencedXSDAttributeDeclaration =  xsdComplexTypeDefinition.resolveAttributeDeclaration(namespace, name);
      handleImport(xsdComplexTypeDefinition.getSchema(), referencedXSDAttributeDeclaration);
      xsdAttributeDeclaration.setResolvedAttributeDeclaration(referencedXSDAttributeDeclaration);
    }
    else
    {
      xsdAttributeDeclaration.setName(name);
      
      if (namespace != null)
      {
        xsdAttributeDeclaration.setForm(XSDForm.QUALIFIED_LITERAL);
      }
      
      EClassifier eType = eStructuralFeature.getEType();
      if (eStructuralFeature instanceof EReference)
      {
        referenceType = eType;
        XSDSimpleTypeDefinition xsdSimpleTypeDefinition =  
            xsdComplexTypeDefinition.getSchema().getSchemaForSchema().resolveSimpleTypeDefinition
              (((EReference)eStructuralFeature).isResolveProxies() ?  "anyURI" : "IDREF");
        handleMultiplicity(xsdComplexTypeDefinition.getSchema(), eStructuralFeature, xsdAttributeDeclaration, xsdSimpleTypeDefinition);
      }
      else
      {
        XSDSimpleTypeDefinition xsdSimpleTypeDefinition =  xsdComplexTypeDefinition.resolveSimpleTypeDefinitionURI(getURI(eType));
        handleImport(xsdComplexTypeDefinition.getSchema(), xsdSimpleTypeDefinition);
        handleMultiplicity(xsdComplexTypeDefinition.getSchema(), eStructuralFeature, xsdAttributeDeclaration, xsdSimpleTypeDefinition);
      }
    }
    
    xsdAttributeUse.setContent(xsdAttributeDeclaration); 
    xsdComplexTypeDefinition.getAttributeContents().add(xsdAttributeUse);
    map(xsdAttributeUse, eStructuralFeature);
    
    if (referenceType == null && eStructuralFeature.isMany())
    {
      createEcoreAnnotation(xsdAttributeUse, "many", "true");
    }

    if (eStructuralFeature instanceof EReference)
    {
      EReference eReference = (EReference)eStructuralFeature;
      EReference eOpposite = eReference.getEOpposite();
      if (eOpposite != null)
      {
        createEcoreAnnotation(xsdAttributeUse, "opposite", eOpposite.getName());
      }
    }

    boolean canHaveDefault = true;
    if (eStructuralFeature.isRequired())
    {
      if (eStructuralFeature.isTransient())
      {
        createEcoreAnnotation(xsdAttributeUse, "lowerBound", Integer.toString(eStructuralFeature.getLowerBound()));
      }
      else
      {
        xsdAttributeUse.setUse(XSDAttributeUseCategory.REQUIRED_LITERAL);
        canHaveDefault = false;
      }
    }

    String defaultValue = eStructuralFeature.getDefaultValueLiteral();
    if (defaultValue != null)
    {
      if (canHaveDefault)
      {
        xsdAttributeUse.setConstraint(XSDConstraint.DEFAULT_LITERAL);
        xsdAttributeUse.setLexicalValue(defaultValue);
      }
      else
      {
        createEcoreAnnotation(xsdAttributeUse, "default", defaultValue);
      }
    }

    if (eStructuralFeature.isMany())
    {
      if (eStructuralFeature.isUnsettable())
      {
        createEcoreAnnotation(xsdAttributeUse, "unsettable", "true");
      }
    }
    else if ((eStructuralFeature.getEType().getDefaultValue() != null || eStructuralFeature.getDefaultValueLiteral() != null) !=
                eStructuralFeature.isUnsettable())
    {
      createEcoreAnnotation(xsdAttributeUse, "unsettable", Boolean.toString(eStructuralFeature.isUnsettable()));
    }

    String ecoreName = eStructuralFeature.getName();
    if (!name.equals(ecoreName) || Character.isUpperCase(ecoreName.charAt(0)) || ecoreName.indexOf('_') != -1)
    {
      createEcoreAnnotation(xsdAttributeUse, "name", ecoreName);
    }
    
    if (eStructuralFeature.isMany() && !eStructuralFeature.isOrdered())
    {
      createEcoreAnnotation(xsdAttributeUse, "ordered", "false");
    }
    
    if (eStructuralFeature.isMany() && eStructuralFeature.isUnique() && eStructuralFeature instanceof EAttribute)
    {
      createEcoreAnnotation(xsdAttributeUse, "unique", "true");
    }
    
    if (!eStructuralFeature.isChangeable())
    {
      createEcoreAnnotation(xsdAttributeUse, "changeable", "false");
    }
    
    if (extendedMetaData.getGroup(eStructuralFeature) == null)
    {
      if (eStructuralFeature.isDerived())
      {
        createEcoreAnnotation(xsdAttributeUse, "derived", "true");
      }
      
      if (eStructuralFeature.isTransient())
      {
        createEcoreAnnotation(xsdAttributeUse, "transient", "true");
      }
      
      if (eStructuralFeature.isVolatile())
      {
        createEcoreAnnotation(xsdAttributeUse, "volatile", "true");
      }
    }
    
    if (referenceType != null)
    {
      XSDTypeDefinition xsdTypeDefinition = xsdComplexTypeDefinition.resolveTypeDefinitionURI(getURI(referenceType));
      handleImport(xsdComplexTypeDefinition.getSchema(), xsdTypeDefinition);
      String prefix = 
        handlePrefix
          (xsdComplexTypeDefinition.getSchema().getQNamePrefixToNamespaceMap(), 
            referenceType.getEPackage().getNsPrefix(),
            xsdTypeDefinition.getTargetNamespace());
      createEcoreAnnotation(xsdAttributeUse, "reference", prefix + ":" + xsdTypeDefinition.getName());
    }
    
    if (EcoreUtil.isSuppressedVisibility(eStructuralFeature, EcoreUtil.GET))
    {
      createEcoreAnnotation(xsdAttributeUse, "suppressedGetVisibility", "true");
    }
    if (EcoreUtil.isSuppressedVisibility(eStructuralFeature, EcoreUtil.SET))
    {
      createEcoreAnnotation(xsdAttributeUse, "suppressedSetVisibility", "true");
    }
    if (EcoreUtil.isSuppressedVisibility(eStructuralFeature, EcoreUtil.IS_SET))
    {
      createEcoreAnnotation(xsdAttributeUse, "suppressedIsSetVisibility", "true");
    }
    if (EcoreUtil.isSuppressedVisibility(eStructuralFeature, EcoreUtil.UNSET))
    {
      createEcoreAnnotation(xsdAttributeUse, "suppressedUnsetVisibility", "true");
    }
    
    buildAnnotations(xsdAttributeUse, eStructuralFeature);

    return xsdAttributeUse;
  }
  
  protected void handleMultiplicity
    (XSDSchema xsdSchema,
     EStructuralFeature eStructuralFeature, 
     XSDAttributeDeclaration xsdAttributeDeclaration, 
     XSDSimpleTypeDefinition xsdSimpleTypeDefinition)
  {
    if (eStructuralFeature.isMany())
    {
      XSDSimpleTypeDefinition xsdListTypeDefinition;
      if (xsdSimpleTypeDefinition.hasNameAndTargetNamespace("IDREF", defaultXMLSchemaNamespace))
      {
        xsdListTypeDefinition = xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("IDREFS");
      }
      else
      {
        xsdListTypeDefinition = XSDFactory.eINSTANCE.createXSDSimpleTypeDefinition();
        xsdListTypeDefinition.setItemTypeDefinition(xsdSimpleTypeDefinition);
      }
      if (eStructuralFeature.getLowerBound() > 1 || eStructuralFeature.getUpperBound() > 1)
      {
        XSDSimpleTypeDefinition xsdRestrictedTypeDefinition = XSDFactory.eINSTANCE.createXSDSimpleTypeDefinition();
        if (xsdListTypeDefinition.getContainer() == null)
        {
          xsdRestrictedTypeDefinition.getContents().add(xsdListTypeDefinition);
        }
        else
        {
          xsdRestrictedTypeDefinition.setBaseTypeDefinition(xsdListTypeDefinition);
        }
        if (eStructuralFeature.getLowerBound() == eStructuralFeature.getUpperBound())
        {
          XSDLengthFacet xsdLengthFacet = XSDFactory.eINSTANCE.createXSDLengthFacet();
          xsdLengthFacet.setLexicalValue(Integer.toString(eStructuralFeature.getLowerBound()));
          xsdRestrictedTypeDefinition.getFacetContents().add(xsdLengthFacet);
        }
        else
        {
          if (eStructuralFeature.getLowerBound() > 1)
          {
            XSDMinLengthFacet xsdMinLengthFacet = XSDFactory.eINSTANCE.createXSDMinLengthFacet();
            xsdMinLengthFacet.setLexicalValue(Integer.toString(eStructuralFeature.getLowerBound()));
            xsdRestrictedTypeDefinition.getFacetContents().add(xsdMinLengthFacet);
          }
          if (eStructuralFeature.getUpperBound() > 1)
          {
            XSDMaxLengthFacet xsdMaxLengthFacet = XSDFactory.eINSTANCE.createXSDMaxLengthFacet();
            xsdMaxLengthFacet.setLexicalValue(Integer.toString(eStructuralFeature.getUpperBound()));
            xsdRestrictedTypeDefinition.getFacetContents().add(xsdMaxLengthFacet);
          }
        }
        xsdAttributeDeclaration.setAnonymousTypeDefinition(xsdRestrictedTypeDefinition);
      }
      else
      {
        if (xsdListTypeDefinition.getContainer() == null)
        {
          xsdAttributeDeclaration.setAnonymousTypeDefinition(xsdListTypeDefinition);
        }
        else
        {
          xsdAttributeDeclaration.setTypeDefinition(xsdListTypeDefinition);
        }
      }
    }
    else
    {
      xsdAttributeDeclaration.setTypeDefinition(xsdSimpleTypeDefinition);
    }
  }

  protected XSDParticle buildElementParticle(XSDComplexTypeDefinition xsdComplexTypeDefinition, EStructuralFeature eStructuralFeature)
  {
    String namespace = extendedMetaData.getNamespace(eStructuralFeature);
    String name = extendedMetaData.getName(eStructuralFeature);
    
    // It must be a ref if it refers to another namespace.
    // If it refers to this namespace, 
    // it's treated as a ref,
    // if this feature has the same type as the feature for matching global element.
    //
    String typeNamespace = extendedMetaData.getNamespace(eStructuralFeature.getEContainingClass());
    boolean isRef = namespace != null && !namespace.equals(typeNamespace);
    if (namespace == null ? typeNamespace == null : namespace.equals(typeNamespace))
    {
      EStructuralFeature globalFeature = extendedMetaData.getElement(namespace, name);
      isRef = globalFeature != null && globalFeature.getEType() == eStructuralFeature.getEType();
    }
    
    XSDModelGroup xsdModelGroup = findOrCreateModelGroup(xsdComplexTypeDefinition);
    
    EStructuralFeature group = extendedMetaData.getGroup(eStructuralFeature);
    if (group != null)
    {
      XSDParticle xsdParticle = (XSDParticle)eModelElementToXSDComponentMap.get(group);
      if (xsdParticle != null)
      {
        xsdModelGroup = (XSDModelGroup)xsdParticle.getContent();
      }
    }
    
    XSDElementDeclaration xsdElementDeclaration = XSDFactory.eINSTANCE.createXSDElementDeclaration();
    
    EClassifier referenceType = null;
    EReference eOpposite = null;
    if (isRef)
    {
      XSDElementDeclaration referencedXSDElementDeclaration = xsdComplexTypeDefinition.resolveElementDeclaration(namespace, name);
      handleImport(xsdComplexTypeDefinition.getSchema(), referencedXSDElementDeclaration);
      xsdElementDeclaration.setResolvedElementDeclaration(referencedXSDElementDeclaration);
      
      if (eStructuralFeature instanceof EReference)
      {
        eOpposite = ((EReference)eStructuralFeature).getEOpposite();
      }
    }
    else
    {
      xsdElementDeclaration.setName(name);
      if (namespace != null)
      {
        xsdElementDeclaration.setForm(XSDForm.QUALIFIED_LITERAL);
      }
      
      String defaultValue = eStructuralFeature.getDefaultValueLiteral();
      if (defaultValue != null)
      {
        xsdElementDeclaration.setConstraint(XSDConstraint.DEFAULT_LITERAL);
        xsdElementDeclaration.setLexicalValue(defaultValue);
      }
        
      XSDTypeDefinition xsdTypeDefinition = 
        xsdComplexTypeDefinition.resolveTypeDefinitionURI(getURI(eStructuralFeature.getEType()));
      
      if (eStructuralFeature instanceof EReference)
      {
        EReference eReference = (EReference)eStructuralFeature;
        eOpposite = eReference.getEOpposite();
        if (!eReference.isContainment() && !eReference.isContainer())
        {
          referenceType = eStructuralFeature.getEType();
          xsdTypeDefinition = 
            xsdComplexTypeDefinition.getSchema().getSchemaForSchema().resolveTypeDefinition 
            (eReference.isResolveProxies() ? "anyURI" : "IDREF");
        }
      }
       
      handleImport(xsdComplexTypeDefinition.getSchema(), xsdTypeDefinition);
      xsdElementDeclaration.setTypeDefinition(xsdTypeDefinition);
    
      // If the attribute can have a null value (primitives and enums can't), and
      // if the attribute can hold many nulls or
      // it can hold only a single value that is allowed to be null 
      // and it can be considered set when it has the null value...
      //
      if (isWrapperType(eStructuralFeature.getEType()) || 
            (eStructuralFeature instanceof EAttribute && 
             eStructuralFeature.getEType().getDefaultValue() == null && 
             (eStructuralFeature.isMany() || 
                (!eStructuralFeature.isRequired() && 
                   (eStructuralFeature.getDefaultValueLiteral() != null || eStructuralFeature.isUnsettable())))))
      {
        xsdElementDeclaration.setNillable(true);
      }
    }
    
    XSDParticle xsdParticle = XSDFactory.eINSTANCE.createXSDParticle();
    xsdParticle.setContent(xsdElementDeclaration);
    
    xsdModelGroup.getContents().add(xsdParticle);
    map(xsdParticle, eStructuralFeature);
    
    if (eStructuralFeature.isMany())
    {
      if (eStructuralFeature.isUnsettable())
      {
        createEcoreAnnotation(xsdParticle, "unsettable", "true");
      }
    }
    else if (xsdElementDeclaration.isNillable() ?
               !eStructuralFeature.isUnsettable() :
               (eStructuralFeature.getEType().getDefaultValue() != null || eStructuralFeature.getDefaultValueLiteral() != null) !=
                  eStructuralFeature.isUnsettable())
    {
      createEcoreAnnotation(xsdParticle, "unsettable", Boolean.toString(eStructuralFeature.isUnsettable()));
    }

    if (eOpposite != null)
    {
      createEcoreAnnotation(xsdParticle, "opposite", eOpposite.getName());
    }
    
    if (group == null)
    {
      if (eStructuralFeature.isTransient() && !xsdComplexTypeDefinition.isMixed())
      {
        xsdParticle.setMinOccurs(0);
        if (eStructuralFeature.getLowerBound() != 0)
        {
          createEcoreAnnotation(xsdParticle, "lowerBound", Integer.toString(eStructuralFeature.getLowerBound()));
        }
      }
      else if (eStructuralFeature.getLowerBound() != 1)
      {
        xsdParticle.setMinOccurs(eStructuralFeature.getLowerBound());
      }
      
      if (eStructuralFeature.getUpperBound() != 1)
      {
        xsdParticle.setMaxOccurs(eStructuralFeature.getUpperBound());
      }
    }
    else
    {
      if (eStructuralFeature.isTransient() && !xsdComplexTypeDefinition.isMixed())
      {
        xsdParticle.setMinOccurs(0);
        if (eStructuralFeature.getLowerBound() != 0)
        {
          createEcoreAnnotation(xsdParticle, "lowerBound", Integer.toString(eStructuralFeature.getLowerBound()));
        }
      }
      else if (eStructuralFeature.getLowerBound() != 1)
      {
        createEcoreAnnotation(xsdParticle, "lowerBound", Integer.toString(eStructuralFeature.getLowerBound()));
      }

      if (eStructuralFeature.getUpperBound() != 1)
      {
        createEcoreAnnotation(xsdParticle, "upperBound", Integer.toString(eStructuralFeature.getUpperBound()));
      }
    }
    
    String ecoreName = eStructuralFeature.getName();
    if (!name.equals(ecoreName) || Character.isUpperCase(ecoreName.charAt(0)) || ecoreName.indexOf('_') != -1)
    {
      createEcoreAnnotation(xsdParticle, "name", ecoreName);
    }
    
    if (eStructuralFeature.isMany() && !eStructuralFeature.isOrdered())
    {
      createEcoreAnnotation(xsdParticle, "ordered", "false");
    }
    
    if (eStructuralFeature.isMany() && eStructuralFeature.isUnique() && eStructuralFeature instanceof EAttribute)
    {
      createEcoreAnnotation(xsdParticle, "unique", "true");
    }
    
    if (!eStructuralFeature.isChangeable())
    {
      createEcoreAnnotation(xsdParticle, "changeable", "false");
    }
    
    if (extendedMetaData.getContentKind(eStructuralFeature.getEContainingClass()) != ExtendedMetaData.MIXED_CONTENT &&
          extendedMetaData.getGroup(eStructuralFeature) == null)
    {
      if (eStructuralFeature.isDerived())
      {
        createEcoreAnnotation(xsdParticle, "derived", "true");
      }
      
      if (eStructuralFeature.isTransient())
      {
        createEcoreAnnotation(xsdParticle, "transient", "true");
      }
      
      if (eStructuralFeature.isVolatile())
      {
        createEcoreAnnotation(xsdParticle, "volatile", "true");
      }
    }
    
    if (eStructuralFeature instanceof EReference && 
          ((EReference)eStructuralFeature).isResolveProxies() && 
          ((EReference)eStructuralFeature).isContainment())
    {
      createEcoreAnnotation(xsdParticle, "resolveProxies", "true");
    }
    
    if (referenceType != null)
    {
      XSDTypeDefinition xsdTypeDefinition = xsdComplexTypeDefinition.resolveTypeDefinitionURI(getURI(referenceType));
      handleImport(xsdComplexTypeDefinition.getSchema(), xsdTypeDefinition);
      String prefix = 
        handlePrefix
          (xsdComplexTypeDefinition.getSchema().getQNamePrefixToNamespaceMap(), 
            referenceType.getEPackage().getNsPrefix(),
            xsdTypeDefinition.getTargetNamespace());
      createEcoreAnnotation(xsdElementDeclaration, "reference", prefix + ":" + xsdTypeDefinition.getName());
    }
    
    if (EcoreUtil.isSuppressedVisibility(eStructuralFeature, EcoreUtil.GET))
    {
      createEcoreAnnotation(xsdElementDeclaration, "suppressedGetVisibility", "true");
    }
    if (EcoreUtil.isSuppressedVisibility(eStructuralFeature, EcoreUtil.SET))
    {
      createEcoreAnnotation(xsdElementDeclaration, "suppressedSetVisibility", "true");
    }
    if (EcoreUtil.isSuppressedVisibility(eStructuralFeature, EcoreUtil.IS_SET))
    {
      createEcoreAnnotation(xsdElementDeclaration, "suppressedIsSetVisibility", "true");
    }
    if (EcoreUtil.isSuppressedVisibility(eStructuralFeature, EcoreUtil.UNSET))
    {
      createEcoreAnnotation(xsdElementDeclaration, "suppressedUnsetVisibility", "true");
    }
    
    buildAnnotations(xsdParticle, eStructuralFeature);

    return xsdParticle;
  }

  protected XSDModelGroup findOrCreateModelGroup(XSDComplexTypeDefinition xsdComplexTypeDefinition)
  {
    if (xsdComplexTypeDefinition.getContent() == null)
    {
      return buildModelGroup(xsdComplexTypeDefinition);
    }
    else
    {
      XSDParticle xsdParticle = (XSDParticle)xsdComplexTypeDefinition.getContent();
      return (XSDModelGroup)xsdParticle.getContent();
    }
  }

  protected XSDModelGroup buildModelGroup(XSDComplexTypeDefinition xsdComplexTypeDefinition)
  {
    XSDModelGroup xsdModelGroup = XSDFactory.eINSTANCE.createXSDModelGroup();
    xsdModelGroup.setCompositor(XSDCompositor.SEQUENCE_LITERAL);
    XSDParticle xsdParticle = XSDFactory.eINSTANCE.createXSDParticle();
    xsdParticle.setContent(xsdModelGroup);
    xsdComplexTypeDefinition.setContent(xsdParticle);
    return xsdModelGroup;
  }

  protected boolean isWrapperType(EClassifier eClassifier)
  {
    String name = extendedMetaData.getName(eClassifier);
    return name.endsWith(":Object") && !EcorePackage.eNS_URI.equals(extendedMetaData.getNamespace(eClassifier));
  }
  
  protected String getURI(EClassifier eClassifier)
  {
    String namespace = extendedMetaData.getNamespace(eClassifier);
    String name = extendedMetaData.getName(eClassifier);
    if (XMLTypePackage.eNS_URI.equals(namespace))
    {
      namespace = defaultXMLSchemaNamespace;
      if (name.endsWith(":Object"))
      {
        name = name.substring(0, name.length() - 7);
      }
    }
    else if (EcorePackage.eNS_URI.equals(namespace))
    {
      if ("EObject".equals(name))
      {
        namespace = defaultXMLSchemaNamespace;
        name = "anyType";
      }
      else if (name.endsWith(":Object"))
      {
        name = eClassifier.getName();
      }
    }
    else if (name.endsWith(":Object"))
    {
      name = name.substring(0, name.length() - 7);
    }
    String result = 
      namespace == null ?
        name :
        namespace + "#" + name;
    return result;
  }
  
  protected void createEcoreAnnotation(XSDComponent xsdComponent, String key, String value)
  {
    Element element = xsdComponent.getElement();
    if (element != null)
    {
      createEcoreAnnotation(xsdComponent.getSchema().getQNamePrefixToNamespaceMap(), element, key, value);
    }
  }
  
  protected void createEcoreAnnotation(Map qNamePrefixToNamespaceMap, Element element, String key, String value)
  {
    String ecorePrefix = handlePrefix(qNamePrefixToNamespaceMap, "ecore", EcorePackage.eNS_URI);
    element.setAttributeNS(EcorePackage.eNS_URI, ecorePrefix + ':' + key, value);
  }

  protected void createAnnotation(XSDComponent xsdComponent, String namespace, String key, String value)
  {
    Element element = xsdComponent.getElement();
    if (element != null)
    {
      createAnnotation(xsdComponent.getSchema().getQNamePrefixToNamespaceMap(), element, namespace, key, value);
    }
  }

  protected void createAnnotation(Map qNamePrefixToNamespaceMap, Element element, String namespace, String key, String value)
  {
    String prefix = handlePrefix(qNamePrefixToNamespaceMap, qualifiedPackageName(namespace), namespace);
    element.setAttributeNS(namespace, prefix + ':' + key, value);
  }

  protected void handleImport(XSDSchema xsdSchema, XSDNamedComponent xsdNamedComponent)
  {
    String namespace = xsdNamedComponent.getTargetNamespace();
    
    if (!defaultXMLSchemaNamespace.equals(namespace) &&
         (namespace == null ? xsdSchema.getTargetNamespace() != null : !namespace.equals(xsdSchema.getTargetNamespace())))
    {
      for (Iterator i = xsdSchema.getContents().iterator(); i.hasNext(); )
      {
        Object content = i.next();
        if (content instanceof XSDImport)
        {
          XSDImport xsdImport = (XSDImport)content;
          if (namespace == null ? xsdImport.getNamespace() == null : namespace.equals(xsdImport.getNamespace()))
          {
            return;
          }
        }
        else if (!(content instanceof XSDAnnotation))
        {
          break;
        }
      }
      
      XSDImport xsdImport = XSDFactory.eINSTANCE.createXSDImport();
      xsdImport.setNamespace(namespace);
      
      EPackage ePackage = extendedMetaData.getPackage(namespace);
      if (ePackage != null)
      {
        handlePrefix(xsdSchema.getQNamePrefixToNamespaceMap(), ePackage.getNsPrefix(), namespace);
        //xsdImport.setSchemaLocation(ePackage.eResource().getURI().trimFileExtension().appendFileExtension("xsd").toString());
        xsdImport.setSchemaLocation(ePackage.getNsURI());
      }
      
      xsdSchema.getContents().add(0, xsdImport);
    }
  }
  
  protected String handlePrefix(Map namespaces, String preferredPrefix, String namespace)
  {
    if (XMLNamespacePackage.eNS_URI.equals(namespace))
    {
      return "xml";
    }
    
    String value = (String)namespaces.get(preferredPrefix);
    if (namespace == null ? value == null : namespace.equals(value))
    {
      return preferredPrefix;
    }
    
    // If there is a non-null value, i.e., if the prefix is in use, see if there is already a prefix chosen.
    //
    if (value != null || defaultXMLSchemaNamespace.equals(namespace))
    {
      for (Iterator i = namespaces.entrySet().iterator(); i.hasNext(); )
      {
        Map.Entry entry = (Map.Entry)i.next();
        if (namespace == null ? entry.getValue() == null : namespace.equals(entry.getValue()))
        {
          // Return the previously assigned prefix; it may not match the preferred one.
          //
          return (String)entry.getKey();
        }
      }
    }
    
    String uniquePrefix = preferredPrefix;
    for (int i = 0; namespaces.containsKey(uniquePrefix); ++i)
    {
      uniquePrefix = preferredPrefix + "_" + i;
    }
    namespaces.put(uniquePrefix, namespace);
    return uniquePrefix;
  }

  protected static final String GEN_MODEL_PACKAGE_NS_URI = "http://www.eclipse.org/emf/2002/GenModel";

  protected boolean isIgnoredAnnotationSource(String sourceURI)
  {
    return 
      EcorePackage.eNS_URI.equals(sourceURI) ||
        ExtendedMetaData.ANNOTATION_URI.equals(sourceURI) ||
        GEN_MODEL_PACKAGE_NS_URI.equals(sourceURI);
  }

  protected void buildAnnotations(XSDComponent xsdComponent, EModelElement eModelElement)
  {
    for (Iterator i = eModelElement.getEAnnotations().iterator(); i.hasNext(); )
    {
      EAnnotation eAnnotation = (EAnnotation)i.next();
      String source = eAnnotation.getSource();
      if (!isIgnoredAnnotationSource(source))
      {
        XSDAnnotation xsdAnnotation = null;
        for (Iterator j = eAnnotation.getDetails().entrySet().iterator(); j.hasNext(); )
        {
          Map.Entry entry = (Map.Entry)j.next();
          String key = (String)entry.getKey();
          String value = (String)entry.getValue();
          if (key != null && value.indexOf('\n') == -1 && value.indexOf('\r') == -1)
          {
            createAnnotation(xsdComponent, source, key, value);
          }
          else 
          {
            if (xsdAnnotation == null)
            {
              xsdAnnotation = buildAnnotation(xsdComponent, eModelElement);
            }
            if (xsdAnnotation != null)
            {
              Element applicationInformation = xsdAnnotation.createApplicationInformation(source);
              if (key != null)
              {
                createEcoreAnnotation(xsdComponent.getSchema().getQNamePrefixToNamespaceMap(), applicationInformation, "key", key);
              }
              if (value != null)
              {
                applicationInformation.appendChild(xsdAnnotation.getSchema().getDocument().createTextNode(value));
              }
              xsdAnnotation.getElement().appendChild(applicationInformation);
            }
          }
        }
      }
    }

    String documentation = EcoreUtil.getDocumentation(eModelElement);
    if (documentation != null)
    {
      XSDAnnotation xsdAnnotation = buildAnnotation(xsdComponent, eModelElement);
      if (xsdAnnotation != null)
      {
        createUserInfo(xsdAnnotation, documentation);
      }
    }
  }

  protected XSDAnnotation buildAnnotation(XSDConcreteComponent xsdComponent, EModelElement eModelElement)
  {
    XSDAnnotation xsdAnnotation = null;
    if (xsdComponent instanceof XSDAttributeUse)
    {
      xsdComponent = ((XSDAttributeUse)xsdComponent).getContent();
    }
    else if (xsdComponent instanceof XSDParticle)
    {
      xsdComponent = ((XSDParticle)xsdComponent).getContent();
    }

    if (xsdComponent instanceof XSDSchema)
    {
      ((XSDSchema)xsdComponent).getContents().add(0, xsdAnnotation = XSDFactory.eINSTANCE.createXSDAnnotation());
    }
    else if (xsdComponent instanceof XSDTypeDefinition)
    {
      ((XSDTypeDefinition)xsdComponent).setAnnotation(xsdAnnotation = XSDFactory.eINSTANCE.createXSDAnnotation());
    }
    else if (xsdComponent instanceof XSDFacet)
    {
      ((XSDFacet)xsdComponent).setAnnotation(xsdAnnotation = XSDFactory.eINSTANCE.createXSDAnnotation());
    }
    else if (xsdComponent instanceof XSDElementDeclaration)
    {
      ((XSDElementDeclaration)xsdComponent).setAnnotation(xsdAnnotation = XSDFactory.eINSTANCE.createXSDAnnotation());
    }
    else if (xsdComponent instanceof XSDAttributeDeclaration)
    {
      ((XSDAttributeDeclaration)xsdComponent).setAnnotation(xsdAnnotation = XSDFactory.eINSTANCE.createXSDAnnotation());
    }

    return xsdAnnotation;
  }

  protected void createUserInfo(XSDAnnotation xsdAnnotation, String documentation)
  {
    Element userInformation = xsdAnnotation.createUserInformation(null);

    // Try to parse the documentation and use the parsed version if it's successful.
    //
    XSDParser xsdParser = new XSDParser(null);
    xsdParser.parseString("<documentation>" + documentation + "</documentation>");
    Document document = xsdParser.getDocument();
    if (xsdParser.getDiagnostics().isEmpty() && document.getDocumentElement().getFirstChild() != null)
    {
      Document xsdDocument = xsdAnnotation.getSchema().getDocument();
      for (Node node = document.getDocumentElement().getFirstChild(); node != null; node = node.getNextSibling())
      {
        userInformation.appendChild(xsdDocument.importNode(node, true));
      }
    }
    else
    {
      userInformation.appendChild(xsdAnnotation.getSchema().getDocument().createTextNode(documentation));
    }

    xsdAnnotation.getElement().appendChild(userInformation);
  }

  protected void buildAnnotation(EModelElement eModelElement, Element parent)
  {
    Document document = parent.getOwnerDocument();
    for (Iterator j = eModelElement.getEAnnotations().iterator();  j.hasNext(); )
    {
      EAnnotation eAnnotation = (EAnnotation)j.next();
      String source = eAnnotation.getSource();
      if (!isIgnoredAnnotationSource(source))
      {
        Element annotation = document.createElementNS(null, "annotation");
        if (source != null)
        {
          annotation.setAttributeNS(null, "source", source);
        }
        for (Iterator k = eAnnotation.getDetails().iterator(); k.hasNext(); )
        {
          Map.Entry entry = (Map.Entry)k.next();
          String key = (String)entry.getKey();
          String value = (String)entry.getValue();
          Element detail = document.createElementNS(null, "detail");
          if (key != null)
          {
            detail.setAttributeNS(null, "key", key);
          }
          if (value != null)
          {
            detail.appendChild(document.createTextNode(value));
          }
          annotation.appendChild(detail);
        }
        parent.appendChild(annotation);
      }
      else if (GEN_MODEL_PACKAGE_NS_URI.equals(source) && eAnnotation.getDetails().containsKey("documentation"))
      {
        String documentation = EcoreUtil.getDocumentation(eModelElement);
        if (documentation != null)
        {
          Element annotation = document.createElementNS(null, "annotation");
          annotation.setAttributeNS(null, "source", GEN_MODEL_PACKAGE_NS_URI);
          Element detail = document.createElementNS(null, "detail");
          detail.setAttributeNS(null, "key", "documentation");
          detail.appendChild(document.createTextNode(documentation));
          annotation.appendChild(detail);
          parent.appendChild(annotation);
        }
      }
    }
  }
}
