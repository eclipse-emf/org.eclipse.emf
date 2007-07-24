/**
 * <copyright>
 *
 * Copyright (c) 2005-2007 IBM Corporation and others.
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
 * $Id: EcoreSchemaBuilder.java,v 1.24 2007/07/24 19:38:44 emerks Exp $
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
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
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
import org.eclipse.xsd.XSDMaxExclusiveFacet;
import org.eclipse.xsd.XSDMaxInclusiveFacet;
import org.eclipse.xsd.XSDMaxLengthFacet;
import org.eclipse.xsd.XSDMinExclusiveFacet;
import org.eclipse.xsd.XSDMinInclusiveFacet;
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
  protected Map<EModelElement, XSDComponent> eModelElementToXSDComponentMap = new HashMap<EModelElement, XSDComponent>();

  public EcoreSchemaBuilder(ExtendedMetaData extendedMetaData)
  {
    this.extendedMetaData = extendedMetaData;
  }

  @Override
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
    Map<String, String> namespaces = xsdSchema.getQNamePrefixToNamespaceMap();
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
      for (EClassifier eClassifier : ePackage.getEClassifiers())
      {
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
      if (!"DocumentRoot".equals(documentRootEClass.getName()))
      {
        createEcoreAnnotation(xsdSchema, "documentRoot", documentRootEClass.getName());
      }

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
    for (EClassifier eClassifier : ePackage.getEClassifiers())
    {
      if (eClassifier != documentRootEClass)
      {
        buildTypeDefinition(xsdSchema, eClassifier);
      }
    }

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

    EClassifier eType = eStructuralFeature.getEType();
    XSDTypeDefinition xsdTypeDefinition = xsdSchema.resolveTypeDefinitionURI(getURI(eType));
    handleImport(xsdSchema, xsdTypeDefinition);
    xsdElementDeclaration.setTypeDefinition(xsdTypeDefinition);

    if (isWrapperType(eType))
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

    if (eStructuralFeature.eIsSet(EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE))
    {
      createEcoreAnnotation(xsdElementDeclaration, "type", getGenericType(xsdSchema, eStructuralFeature.getEGenericType()));
    }

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

    if (eStructuralFeature.eIsSet(EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE))
    {
      createEcoreAnnotation(xsdAttributeDeclaration, "type", getGenericType(xsdSchema, eStructuralFeature.getEGenericType()));
    }

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
        instanceClass = eDataType.getInstanceTypeName();
        if (instanceClass != null && instanceClass.equals(baseType.getInstanceTypeName()))
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
          List<EDataType> memberTypes = extendedMetaData.getMemberTypes(eDataType);
          if (!memberTypes.isEmpty())
          {
            for (EDataType memberType : memberTypes)
            {
              XSDSimpleTypeDefinition memberTypeDefinition =  xsdSchema.resolveSimpleTypeDefinitionURI(getURI(memberType));
              handleImport(xsdSchema, memberTypeDefinition);
              xsdSimpleTypeDefinition.getMemberTypeDefinitions().add(memberTypeDefinition);
            }
          }
          else
          {
            xsdSimpleTypeDefinition.setBaseTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("string"));
            if (!(eDataType instanceof EEnum))
            {
              instanceClass = eDataType.getInstanceTypeName();
            }
          }
        }
      }

      xsdSchema.getContents().add(xsdSimpleTypeDefinition);
      map(xsdSimpleTypeDefinition, eDataType);

      buildTypeParameters(xsdSimpleTypeDefinition, eDataType.getETypeParameters());

      if (instanceClass != null)
      {
        createEcoreAnnotation(xsdSimpleTypeDefinition, "instanceClass", instanceClass.replace('<', '{').replace('>', '}'));
      }

      String minInclusive = extendedMetaData.getMinInclusiveFacet(eDataType);
      if (minInclusive != null)
      {
        XSDMinInclusiveFacet xsdMinInclusiveFacet = XSDFactory.eINSTANCE.createXSDMinInclusiveFacet();
        xsdMinInclusiveFacet.setLexicalValue(minInclusive);
        xsdSimpleTypeDefinition.getFacetContents().add(xsdMinInclusiveFacet);
      }

      String maxInclusive = extendedMetaData.getMaxInclusiveFacet(eDataType);
      if (maxInclusive != null)
      {
        XSDMaxInclusiveFacet xsdMaxInclusiveFacet = XSDFactory.eINSTANCE.createXSDMaxInclusiveFacet();
        xsdMaxInclusiveFacet.setLexicalValue(maxInclusive);
        xsdSimpleTypeDefinition.getFacetContents().add(xsdMaxInclusiveFacet);
      }

      String minExclusive = extendedMetaData.getMinExclusiveFacet(eDataType);
      if (minExclusive != null)
      {
        XSDMinExclusiveFacet xsdMinExclusiveFacet = XSDFactory.eINSTANCE.createXSDMinExclusiveFacet();
        xsdMinExclusiveFacet.setLexicalValue(minExclusive);
        xsdSimpleTypeDefinition.getFacetContents().add(xsdMinExclusiveFacet);
      }

      String maxExclusive = extendedMetaData.getMaxExclusiveFacet(eDataType);
      if (maxExclusive != null)
      {
        XSDMaxExclusiveFacet xsdMaxExclusiveFacet = XSDFactory.eINSTANCE.createXSDMaxExclusiveFacet();
        xsdMaxExclusiveFacet.setLexicalValue(maxExclusive);
        xsdSimpleTypeDefinition.getFacetContents().add(xsdMaxExclusiveFacet);
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

        for (ListIterator<EEnumLiteral> literals = eEnum.getELiterals().listIterator(); literals.hasNext();)
        {
          EEnumLiteral literal = literals.next();
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

    List<EGenericType> genericTypeTypes = eClass.getEGenericSuperTypes();
    if (!genericTypeTypes.isEmpty())
    {
      Iterator<EGenericType> i = genericTypeTypes.iterator();
      EGenericType genericSuperType = i.next();
      XSDTypeDefinition baseType = xsdSchema.resolveTypeDefinitionURI(getURI(genericSuperType.getERawType()));
      if (!XSDConstants.isURType(baseType))
      {
        handleImport(xsdSchema, baseType);
        xsdComplexTypeDefinition.setDerivationMethod(XSDDerivationMethod.EXTENSION_LITERAL);
        xsdComplexTypeDefinition.setBaseTypeDefinition(baseType);
      }
  
      xsdSchema.getContents().add(xsdComplexTypeDefinition);
  
      if (!genericSuperType.getETypeArguments().isEmpty())
      {
        createEcoreAnnotation(xsdComplexTypeDefinition, "extends", getGenericType(xsdSchema, genericSuperType));
      }

      if (i.hasNext())
      {
        StringBuilder additionalSuperTypes = new StringBuilder();
        do
        {
          additionalSuperTypes.append(getGenericType(xsdSchema, i.next()));
          additionalSuperTypes.append(' ');
        }
        while (i.hasNext());
        createEcoreAnnotation(xsdComplexTypeDefinition, "implements", additionalSuperTypes.toString().trim());
      }
    }
    else
    {
      xsdSchema.getContents().add(xsdComplexTypeDefinition);
    }

    buildTypeParameters(xsdComplexTypeDefinition, eClass.getETypeParameters());

    map(xsdComplexTypeDefinition, eClass);

    String instanceClass =  eClass.getInstanceTypeName();
    if (instanceClass != null)
    {
      createEcoreAnnotation(xsdComplexTypeDefinition, "instanceClass", instanceClass.replace('<', '{').replace('>', '}'));
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

    for (Iterator<EStructuralFeature> i = eClass.getEStructuralFeatures().iterator(); i.hasNext(); )
    {
      EStructuralFeature eStructuralFeature = i.next();
      buildContentFeature(xsdComplexTypeDefinition, eStructuralFeature);
    }

    List<EOperation> eOperations = eClass.getEOperations();
    if (!eOperations.isEmpty())
    {
      XSDAnnotation xsdAnnotation = xsdComplexTypeDefinition.getAnnotation();
      if (xsdAnnotation == null)
      {
        xsdAnnotation = XSDFactory.eINSTANCE.createXSDAnnotation();
        xsdComplexTypeDefinition.setAnnotation(xsdAnnotation);
      }
      Element applicationInformation = xsdAnnotation.createApplicationInformation(EcorePackage.eNS_URI);
      xsdAnnotation.getElement().appendChild(applicationInformation);
      createEcoreAnnotation(xsdSchema.getQNamePrefixToNamespaceMap(), applicationInformation, "key", "operations");
      Document document = xsdSchema.getDocument();
      for (EOperation eOperation : eOperations)
      {
        Element operation = document.createElementNS(null, "operation");
        operation.setAttributeNS(null, "name", eOperation.getName());
        applicationInformation.appendChild(operation);
        buildAnnotation(eOperation, operation);
        buildTypeParameters(xsdAnnotation, eOperation.getETypeParameters());
        EGenericType returnEGenericType = eOperation.getEGenericType();
        if (returnEGenericType != null)
        {
          operation.setAttributeNS(null, "type", getGenericType(xsdSchema, returnEGenericType));
        }
        List<EClassifier> exceptions = eOperation.getEExceptions();
        if (!exceptions.isEmpty())
        {
          StringBuilder stringBuilder = new StringBuilder();
          for (EGenericType eGenericType : eOperation.getEGenericExceptions())
          {
            stringBuilder.append(getGenericType(xsdSchema, eGenericType));
            stringBuilder.append(' ');
          }

          operation.setAttributeNS(null, "exceptions", stringBuilder.substring(0, stringBuilder.length() - 1));
        }

        for (EParameter eParameter : eOperation.getEParameters())
        {
          Element parameter = document.createElementNS(null, "parameter");
          parameter.setAttributeNS(null, "name", eParameter.getName());
          operation.appendChild(parameter);
          buildAnnotation(eParameter, parameter);
          parameter.setAttributeNS(null, "type", getGenericType(xsdSchema, eParameter.getEGenericType()));

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

  protected void buildTypeParameters(XSDComponent xsdComponent, List<ETypeParameter> eTypeParameters)
  {
    if (!eTypeParameters.isEmpty())
    {
      XSDSchema xsdSchema = xsdComponent.getSchema();
      XSDAnnotation xsdAnnotation;
      Element applicationInformation;
      if (xsdComponent instanceof XSDTypeDefinition)
      {
        xsdAnnotation = XSDFactory.eINSTANCE.createXSDAnnotation();
        ((XSDTypeDefinition)xsdComponent).setAnnotation(xsdAnnotation);
        applicationInformation = xsdAnnotation.createApplicationInformation(EcorePackage.eNS_URI);
        xsdAnnotation.getElement().appendChild(applicationInformation);
        createEcoreAnnotation(xsdSchema.getQNamePrefixToNamespaceMap(), applicationInformation, "key", "typeParameters");
      }
      else
      {
        xsdAnnotation = (XSDAnnotation)xsdComponent;
        applicationInformation = (Element)xsdAnnotation.getApplicationInformation().get(xsdAnnotation.getApplicationInformation().size() - 1).getLastChild();
      }
      Document document = xsdSchema.getDocument();
      for (ETypeParameter eTypeParameter : eTypeParameters)
      {
        Element typeParameter = document.createElementNS(null, "typeParameter");
        typeParameter.setAttributeNS(null, "name", eTypeParameter.getName());
        applicationInformation.appendChild(typeParameter);
        buildAnnotation(eTypeParameter, typeParameter);
        List<EGenericType> eBounds = eTypeParameter.getEBounds();
        if (!eBounds.isEmpty())
        {
          StringBuilder stringBuilder = new StringBuilder();
          for (Iterator<EGenericType> i = eBounds.iterator(); i.hasNext(); )
          {
            String bound = getGenericType(xsdSchema, i.next());
            stringBuilder.append(bound);
            if (i.hasNext())
            {
              stringBuilder.append(' ');
            }
          }

          typeParameter.setAttributeNS(null, "bounds", stringBuilder.toString());
        }
      }
    }
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

    createEcoreAnnotation(xsdParticle, "featureMap", eStructuralFeature.getName());

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
    XSDSchema xsdSchema = xsdComplexTypeDefinition.getSchema();
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = XSDFactory.eINSTANCE.createXSDSimpleTypeDefinition();
    EClassifier eType = eStructuralFeature.getEType();
    EClassifier referenceType = null;
    XSDSimpleTypeDefinition baseType;
    if (eStructuralFeature instanceof EReference)
    {
      referenceType = eType;
      baseType =
        xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition
          (((EReference)eStructuralFeature).isResolveProxies() ?  "anyURI" : "IDREF");
      handleMultiplicity(xsdSchema, eStructuralFeature, xsdSimpleTypeDefinition, baseType);
    }
    else
    {
      baseType = xsdComplexTypeDefinition.resolveSimpleTypeDefinitionURI(getURI(eType));
      handleImport(xsdSchema, baseType);
      handleMultiplicity(xsdSchema, eStructuralFeature, xsdSimpleTypeDefinition, baseType);
    }
    xsdComplexTypeDefinition.setBaseTypeDefinition(xsdSimpleTypeDefinition.getBaseType());
    xsdComplexTypeDefinition.setDerivationMethod(xsdSimpleTypeDefinition.getContents().isEmpty() ? XSDDerivationMethod.EXTENSION_LITERAL : XSDDerivationMethod.RESTRICTION_LITERAL);
    xsdComplexTypeDefinition.setContent(xsdSimpleTypeDefinition);

    map(xsdSimpleTypeDefinition, eStructuralFeature);

    buildAttributeInformation(xsdComplexTypeDefinition, "value", false, referenceType, xsdSimpleTypeDefinition, eStructuralFeature);

    if (eStructuralFeature instanceof EAttribute && 
          ((EAttribute)eStructuralFeature).isID() && 
          !XSDConstants.isOrIsDerivedFromID(xsdSimpleTypeDefinition))
    {
      createEcoreAnnotation(xsdSimpleTypeDefinition, "id", "true");
    }

    if (eStructuralFeature.eIsSet(EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE))
    {
      createEcoreAnnotation(xsdSimpleTypeDefinition, referenceType == null ? "type" : "reference", getGenericType(xsdSchema, eStructuralFeature.getEGenericType()));
    }

    return xsdSimpleTypeDefinition;
  }

  protected XSDAttributeUse buildAttributeUse(XSDComplexTypeDefinition xsdComplexTypeDefinition, EStructuralFeature eStructuralFeature)
  {
    XSDSchema xsdSchema = xsdComplexTypeDefinition.getSchema();
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
      isRef = globalFeature != null && EcoreUtil.equals(globalFeature.getEGenericType(), eStructuralFeature.getEGenericType());
    }

    XSDAttributeUse xsdAttributeUse = XSDFactory.eINSTANCE.createXSDAttributeUse();
    XSDAttributeDeclaration xsdAttributeDeclaration = XSDFactory.eINSTANCE.createXSDAttributeDeclaration();

    EClassifier referenceType = null;
    if (isRef)
    {
      XSDAttributeDeclaration referencedXSDAttributeDeclaration =  xsdComplexTypeDefinition.resolveAttributeDeclaration(namespace, name);
      handleImport(xsdSchema, referencedXSDAttributeDeclaration);
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
            xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition
              (((EReference)eStructuralFeature).isResolveProxies() ?  "anyURI" : "IDREF");
        handleMultiplicity(xsdSchema, eStructuralFeature, xsdAttributeDeclaration, xsdSimpleTypeDefinition);
      }
      else
      {
        XSDSimpleTypeDefinition xsdSimpleTypeDefinition =  xsdComplexTypeDefinition.resolveSimpleTypeDefinitionURI(getURI(eType));
        handleImport(xsdSchema, xsdSimpleTypeDefinition);
        handleMultiplicity(xsdSchema, eStructuralFeature, xsdAttributeDeclaration, xsdSimpleTypeDefinition);
      }
    }

    xsdAttributeUse.setContent(xsdAttributeDeclaration);
    xsdComplexTypeDefinition.getAttributeContents().add(xsdAttributeUse);
    map(xsdAttributeUse, eStructuralFeature);

    buildAttributeInformation(xsdComplexTypeDefinition, name, isRef, referenceType, xsdAttributeUse, eStructuralFeature);

    if (eStructuralFeature instanceof EAttribute && 
          ((EAttribute)eStructuralFeature).isID() && 
          !XSDConstants.isOrIsDerivedFromID(xsdAttributeDeclaration.getTypeDefinition()))
    {
      createEcoreAnnotation(xsdAttributeUse, "id", "true");
    }

    if (eStructuralFeature.eIsSet(EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE))
    {
      createEcoreAnnotation(xsdAttributeUse, referenceType == null ? "type" : "reference", getGenericType(xsdSchema, eStructuralFeature.getEGenericType()));
    }

    return xsdAttributeUse;
  }

  protected void buildAttributeInformation
    (XSDComplexTypeDefinition xsdComplexTypeDefinition,
     String name,
     boolean isRef,
     EClassifier referenceType,
     XSDComponent xsdComponent,
     EStructuralFeature eStructuralFeature)
  {
    if (referenceType == null && eStructuralFeature.isMany())
    {
      createEcoreAnnotation(xsdComponent, "many", "true");
    }

    if (eStructuralFeature instanceof EReference)
    {
      EReference eReference = (EReference)eStructuralFeature;
      EReference eOpposite = eReference.getEOpposite();
      if (eOpposite != null)
      {
        createEcoreAnnotation(xsdComponent, "opposite", eOpposite.getName());
      }
    }

    XSDAttributeUse xsdAttriuteUse = xsdComponent instanceof XSDAttributeUse ? (XSDAttributeUse)xsdComponent : null;

    boolean canHaveDefault = xsdAttriuteUse != null;
    if (eStructuralFeature.isRequired())
    {
      if (eStructuralFeature.isTransient() || xsdAttriuteUse == null)
      {
        createEcoreAnnotation(xsdComponent, "lowerBound", Integer.toString(eStructuralFeature.getLowerBound()));
      }
      else
      {
        xsdAttriuteUse.setUse(XSDAttributeUseCategory.REQUIRED_LITERAL);
        canHaveDefault = false;
      }
    }

    String defaultValue = eStructuralFeature.getDefaultValueLiteral();
    if (defaultValue != null)
    {
      if (canHaveDefault && xsdAttriuteUse != null)
      {
        if (isRef)
        {
          xsdAttriuteUse.setConstraint(XSDConstraint.DEFAULT_LITERAL);
          xsdAttriuteUse.setLexicalValue(defaultValue);
        }
        else
        {
          XSDAttributeDeclaration xsdAttributeDeclaration = xsdAttriuteUse.getAttributeDeclaration();
          xsdAttributeDeclaration.setConstraint(XSDConstraint.DEFAULT_LITERAL);
          xsdAttributeDeclaration.setLexicalValue(defaultValue);
        }
      }
      else
      {
        createEcoreAnnotation(xsdComponent, "default", defaultValue);
      }
    }

    if (eStructuralFeature.isMany())
    {
      if (eStructuralFeature.isUnsettable())
      {
        createEcoreAnnotation(xsdComponent, "unsettable", "true");
      }
    }
    else if ((eStructuralFeature.getEType().getDefaultValue() != null || eStructuralFeature.getDefaultValueLiteral() != null) !=
                eStructuralFeature.isUnsettable())
    {
      createEcoreAnnotation(xsdComponent, "unsettable", Boolean.toString(eStructuralFeature.isUnsettable()));
    }

    String ecoreName = eStructuralFeature.getName();
    if (!name.equals(ecoreName) || Character.isUpperCase(ecoreName.charAt(0)) || ecoreName.indexOf('_') != -1)
    {
      createEcoreAnnotation(xsdComponent, "name", ecoreName);
    }

    if (eStructuralFeature.isMany() && !eStructuralFeature.isOrdered())
    {
      createEcoreAnnotation(xsdComponent, "ordered", "false");
    }

    if (eStructuralFeature.isMany() && eStructuralFeature.isUnique() && eStructuralFeature instanceof EAttribute)
    {
      createEcoreAnnotation(xsdComponent, "unique", "true");
    }

    if (!eStructuralFeature.isChangeable())
    {
      createEcoreAnnotation(xsdComponent, "changeable", "false");
    }

    if (extendedMetaData.getGroup(eStructuralFeature) == null)
    {
      if (eStructuralFeature.isDerived())
      {
        createEcoreAnnotation(xsdComponent, "derived", "true");
      }

      if (eStructuralFeature.isTransient())
      {
        createEcoreAnnotation(xsdComponent, "transient", "true");
      }

      if (eStructuralFeature.isVolatile())
      {
        createEcoreAnnotation(xsdComponent, "volatile", "true");
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
      createEcoreAnnotation(xsdComponent, "reference", prefix + ":" + xsdTypeDefinition.getName());
    }

    if (EcoreUtil.isSuppressedVisibility(eStructuralFeature, EcoreUtil.GET))
    {
      createEcoreAnnotation(xsdComponent, "suppressedGetVisibility", "true");
    }
    if (EcoreUtil.isSuppressedVisibility(eStructuralFeature, EcoreUtil.SET))
    {
      createEcoreAnnotation(xsdComponent, "suppressedSetVisibility", "true");
    }
    if (EcoreUtil.isSuppressedVisibility(eStructuralFeature, EcoreUtil.IS_SET))
    {
      createEcoreAnnotation(xsdComponent, "suppressedIsSetVisibility", "true");
    }
    if (EcoreUtil.isSuppressedVisibility(eStructuralFeature, EcoreUtil.UNSET))
    {
      createEcoreAnnotation(xsdComponent, "suppressedUnsetVisibility", "true");
    }

    buildAnnotations(xsdComponent, eStructuralFeature);
  }

  protected void handleMultiplicity
    (XSDSchema xsdSchema,
     EStructuralFeature eStructuralFeature,
     XSDAttributeDeclaration xsdAttributeDeclaration,
     XSDSimpleTypeDefinition xsdSimpleTypeDefinition)
  {
    handleMultiplicity(xsdSchema, eStructuralFeature, (XSDComponent)xsdAttributeDeclaration, xsdSimpleTypeDefinition);
  }

  protected void handleMultiplicity
    (XSDSchema xsdSchema,
     EStructuralFeature eStructuralFeature,
     XSDComponent xsdComponent,
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
        if (xsdComponent instanceof XSDAttributeDeclaration)
        {
          ((XSDAttributeDeclaration)xsdComponent).setAnonymousTypeDefinition(xsdRestrictedTypeDefinition);
        }
        else
        {
          ((XSDSimpleTypeDefinition)xsdComponent).getContents().add(xsdRestrictedTypeDefinition);
        }
      }
      else
      {
        if (xsdListTypeDefinition.getContainer() == null)
        {
          if (xsdComponent instanceof XSDAttributeDeclaration)
          {
            ((XSDAttributeDeclaration)xsdComponent).setAnonymousTypeDefinition(xsdListTypeDefinition);
          }
          else
          {
            ((XSDSimpleTypeDefinition)xsdComponent).getContents().add(xsdListTypeDefinition);
          }
        }
        else
        {
          if (xsdComponent instanceof XSDAttributeDeclaration)
          {
            ((XSDAttributeDeclaration)xsdComponent).setTypeDefinition(xsdListTypeDefinition);
          }
          else
          {
            ((XSDSimpleTypeDefinition)xsdComponent).setBaseTypeDefinition(xsdListTypeDefinition);
          }
        }
      }
    }
    else
    {
      if (xsdComponent instanceof XSDAttributeDeclaration)
      {
        ((XSDAttributeDeclaration)xsdComponent).setTypeDefinition(xsdSimpleTypeDefinition);
      }
      else
      {
        ((XSDSimpleTypeDefinition)xsdComponent).setBaseTypeDefinition(xsdSimpleTypeDefinition);
      }
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
      isRef = globalFeature != null && EcoreUtil.equals(globalFeature.getEGenericType(), eStructuralFeature.getEGenericType());
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
    List<EAttribute> eKeys = null;
    EClassifier eType = eStructuralFeature.getEType();
    if (isRef)
    {
      XSDElementDeclaration referencedXSDElementDeclaration = xsdComplexTypeDefinition.resolveElementDeclaration(namespace, name);
      handleImport(xsdComplexTypeDefinition.getSchema(), referencedXSDElementDeclaration);
      xsdElementDeclaration.setResolvedElementDeclaration(referencedXSDElementDeclaration);

      if (eStructuralFeature instanceof EReference)
      {
        EReference eReference = (EReference)eStructuralFeature;
        eOpposite = eReference.getEOpposite();
        eKeys = eReference.getEKeys();
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
        xsdComplexTypeDefinition.resolveTypeDefinitionURI(getURI(eType));

      if (eStructuralFeature instanceof EReference)
      {
        EReference eReference = (EReference)eStructuralFeature;
        eOpposite = eReference.getEOpposite();
        eKeys = eReference.getEKeys();
        if (!eReference.isContainment() && !eReference.isContainer())
        {
          referenceType = eType;
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
      if (isWrapperType(eType) ||
            (eStructuralFeature instanceof EAttribute &&
             eType.getDefaultValue() == null &&
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
               (eType.getDefaultValue() != null || eStructuralFeature.getDefaultValueLiteral() != null) !=
                  eStructuralFeature.isUnsettable())
    {
      createEcoreAnnotation(xsdParticle, "unsettable", Boolean.toString(eStructuralFeature.isUnsettable()));
    }

    if (eOpposite != null)
    {
      createEcoreAnnotation(xsdParticle, "opposite", eOpposite.getName());
    }

    if (eKeys != null && !eKeys.isEmpty())
    {
      StringBuilder keys = new StringBuilder();
      for (EAttribute eKey : eKeys)
      {
        keys.append(eKey.getName());
        keys.append(' ');
      }
      createEcoreAnnotation(xsdParticle, "keys", keys.toString().trim());
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

    if (eStructuralFeature.eIsSet(EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE))
    {
      createEcoreAnnotation(xsdParticle, referenceType == null ? "type" : "reference", getGenericType(xsdComplexTypeDefinition.getSchema(), eStructuralFeature.getEGenericType()));
    }
    else if (referenceType != null)
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

    if (eStructuralFeature instanceof EAttribute && 
          ((EAttribute)eStructuralFeature).isID() && 
          xsdElementDeclaration.getTypeDefinition() instanceof XSDSimpleTypeDefinition &&
          !XSDConstants.isOrIsDerivedFromID((XSDSimpleTypeDefinition)xsdElementDeclaration.getTypeDefinition()))
    {
      createEcoreAnnotation(xsdParticle, "id", "true");
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

  protected String getGenericType(XSDSchema xsdSchema, EGenericType eGenericType)
  {
    Map<String, String> qNamePrefixToNamespaceMap = xsdSchema.getQNamePrefixToNamespaceMap();
    StringBuilder result = new StringBuilder();
    EClassifier eClassifier = eGenericType.getEClassifier();
    if (eClassifier != null)
    {
       XSDTypeDefinition type = xsdSchema.resolveTypeDefinitionURI(getURI(eClassifier));
       handleImport(xsdSchema, type);
       String prefix =
         handlePrefix
           (qNamePrefixToNamespaceMap,
             eClassifier.getEPackage().getNsPrefix(),
             type.getTargetNamespace());
       result.append(prefix);
       result.append(':');
       result.append(type.getName());
       List<EGenericType> eTypeArguments = eGenericType.getETypeArguments();
       if (!eTypeArguments.isEmpty())
       {
         result.append('{');
         for (Iterator<EGenericType> i = eTypeArguments.iterator(); i.hasNext(); )
         {
           result.append(getGenericType(xsdSchema, i.next()));
           if (i.hasNext())
           {
             result.append(", ");
           }
         }
         result.append('}');
       }
    }
    else
    {
      ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
      if (eTypeParameter != null)
      {
        result.append(eTypeParameter.getName());
      }
      else
      {
        result.append('?');
        EGenericType eUpperBound = eGenericType.getEUpperBound();
        if (eUpperBound != null)
        {
          result.append(" extends ");
          result.append(getGenericType(xsdSchema, eUpperBound));
        }
        else
        {
          EGenericType eLowerBound = eGenericType.getELowerBound();
          if (eLowerBound != null)
          {
            result.append(" super ");
            result.append(getGenericType(xsdSchema, eLowerBound));
          }
        }
      }
    }
    return result.toString();
  }

  protected void createEcoreAnnotation(XSDComponent xsdComponent, String key, String value)
  {
    Element element = xsdComponent.getElement();
    if (element != null)
    {
      createEcoreAnnotation(xsdComponent.getSchema().getQNamePrefixToNamespaceMap(), element, key, value);
    }
  }

  protected void createEcoreAnnotation(Map<String, String> qNamePrefixToNamespaceMap, Element element, String key, String value)
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

  protected void createAnnotation(Map<String, String> qNamePrefixToNamespaceMap, Element element, String namespace, String key, String value)
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
      for (Object content : xsdSchema.getContents())
      {
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

  protected String handlePrefix(Map<String, String> namespaces, String preferredPrefix, String namespace)
  {
    if (XMLNamespacePackage.eNS_URI.equals(namespace))
    {
      return "xml";
    }

    String value = namespaces.get(preferredPrefix);
    if (namespace == null ? value == null : namespace.equals(value))
    {
      return preferredPrefix;
    }

    // If there is a non-null value, i.e., if the prefix is in use, see if there is already a prefix chosen.
    //
    if (value != null || defaultXMLSchemaNamespace.equals(namespace))
    {
      for (Map.Entry<String, String> entry : namespaces.entrySet())
      {
        if (namespace == null ? entry.getValue() == null : namespace.equals(entry.getValue()))
        {
          // Return the previously assigned prefix; it may not match the preferred one.
          //
          return entry.getKey();
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
    for (EAnnotation eAnnotation : eModelElement.getEAnnotations())
    {
      String source = eAnnotation.getSource();
      if (!isIgnoredAnnotationSource(source))
      {
        XSDAnnotation xsdAnnotation = null;
        for (Map.Entry<String, String> entry : eAnnotation.getDetails().entrySet())
        {
          String key = entry.getKey();
          String value = entry.getValue();
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
      XSDTypeDefinition xsdTypeDefinition = ((XSDTypeDefinition)xsdComponent);
      if ((xsdAnnotation = xsdTypeDefinition.getAnnotation()) == null)
      {
        xsdTypeDefinition.setAnnotation(xsdAnnotation = XSDFactory.eINSTANCE.createXSDAnnotation());
      }
    }
    else if (xsdComponent instanceof XSDFacet)
    {
      XSDFacet xsdFacet = ((XSDFacet)xsdComponent);
      if ((xsdAnnotation = xsdFacet.getAnnotation()) == null)
      {
        xsdFacet.setAnnotation(xsdAnnotation = XSDFactory.eINSTANCE.createXSDAnnotation());
      }
    }
    else if (xsdComponent instanceof XSDElementDeclaration)
    {
      XSDElementDeclaration xsdElementDeclaration = ((XSDElementDeclaration)xsdComponent);
      if ((xsdAnnotation = xsdElementDeclaration.getAnnotation()) == null)
      {
        xsdElementDeclaration.setAnnotation(xsdAnnotation = XSDFactory.eINSTANCE.createXSDAnnotation());
      }
    }
    else if (xsdComponent instanceof XSDAttributeDeclaration)
    {
      XSDAttributeDeclaration xsdAttributeDeclaration = ((XSDAttributeDeclaration)xsdComponent);
      if ((xsdAnnotation = xsdAttributeDeclaration.getAnnotation()) == null)
      {
        xsdAttributeDeclaration.setAnnotation(xsdAnnotation = XSDFactory.eINSTANCE.createXSDAnnotation());
      }
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
    for (EAnnotation eAnnotation : eModelElement.getEAnnotations())
    {
      String source = eAnnotation.getSource();
      if (!isIgnoredAnnotationSource(source))
      {
        Element annotation = document.createElementNS(null, "annotation");
        if (source != null)
        {
          annotation.setAttributeNS(null, "source", source);
        }
        for (Map.Entry<String, String> entry : eAnnotation.getDetails())
        {
          String key = entry.getKey();
          String value = entry.getValue();
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
