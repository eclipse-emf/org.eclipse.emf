/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDEcoreBuilder.java,v 1.18 2004/06/30 20:36:37 marcelop Exp $
 */
package org.eclipse.xsd.ecore;


import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.w3c.dom.Element;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeGroupDefinition;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDComponent;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDContentTypeCategory;
import org.eclipse.xsd.XSDDerivationMethod;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDEnumerationFacet;
import org.eclipse.xsd.XSDFacet;
import org.eclipse.xsd.XSDFeature;
import org.eclipse.xsd.XSDIdentityConstraintDefinition;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDMaxLengthFacet;
import org.eclipse.xsd.XSDMinLengthFacet;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDModelGroupDefinition;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDNamespaceConstraintCategory;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDPlugin;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTerm;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.XSDVariety;
import org.eclipse.xsd.XSDWildcard;
import org.eclipse.xsd.*;
import org.eclipse.xsd.util.XSDConstants;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;
import org.eclipse.xsd.util.XSDResourceImpl;
import org.eclipse.xsd.util.XSDSwitch;


public class XSDEcoreBuilder extends MapBuilder
{
  protected XSDSchema rootSchema;
  protected List simpleDiagnostics;
  protected List diagnostics;
  protected List xsdSchemas = new UniqueEList();
  protected Map targetNamespaceToEPackageMap = new HashMap();
  protected ExtendedMetaData extendedMetaData;
  protected Map eReferenceToOppositeNameMap = new HashMap();

  public XSDEcoreBuilder()
  {
    extendedMetaData = new BasicExtendedMetaData(new EPackageRegistryImpl());
  }

  public XSDEcoreBuilder(ExtendedMetaData extendedMetaData)
  {
    this.extendedMetaData = extendedMetaData;
  }

  public XSDSchema getSchema()
  {
    return rootSchema;
  }

  public void setValidate(boolean validate)
  {
    diagnostics = validate ? new ArrayList() : null;
  }

  public List getDiagnostics()
  {
    return diagnostics;
  }

  public XSDSchema getRootSchema()
  {
    return rootSchema;
  }

  public Map getTargetNamespaceToEPackageMap()
  {
     return targetNamespaceToEPackageMap;
  }

  public Map getXSDComponentToEModelElementMap()
  {
    return xsdComponentToEModelElementMap;
  }

  protected static final List DOMAINS = Arrays.asList(new String [] {"COM", "com", "ORG", "org" });

  public EPackage getEPackage(XSDNamedComponent xsdNamedComponent)
  {
    XSDSchema containingXSDSchema = xsdNamedComponent.getSchema();
    if (containingXSDSchema != null && !xsdSchemas.contains(containingXSDSchema))
    {
      xsdSchemas.add(containingXSDSchema);
      addInput(containingXSDSchema);
      validate(containingXSDSchema);
    }

    String targetNamespace = 
      containingXSDSchema == null ? 
        xsdNamedComponent.getTargetNamespace() : 
        containingXSDSchema.getTargetNamespace();
    EPackage ePackage = (EPackage)targetNamespaceToEPackageMap.get(targetNamespace);
    if (ePackage == null)
    {
      if (XMLNamespacePackage.eNS_URI.equals(targetNamespace) && xsdSchemas.indexOf(containingXSDSchema) != 0)
      {
        ePackage = XMLNamespacePackage.eINSTANCE;
        for (Iterator i = containingXSDSchema.getAttributeDeclarations().iterator(); i.hasNext(); )
        {
          XSDAttributeDeclaration xsdAttributeDeclaration = (XSDAttributeDeclaration)i.next();
          if (!XSDConstants.isSchemaInstanceNamespace(xsdAttributeDeclaration.getTargetNamespace()))
          {
            EStructuralFeature eStructuralFeature = 
              ExtendedMetaData.INSTANCE.getAttribute(XMLNamespacePackage.eNS_URI, xsdAttributeDeclaration.getName());
            map(xsdAttributeDeclaration, eStructuralFeature);
            XSDTypeDefinition xsdTypeDefinition = xsdAttributeDeclaration.getAnonymousTypeDefinition();
            if (xsdTypeDefinition != null)
            {
              map(xsdTypeDefinition, eStructuralFeature.getEType());
            }
          }
        }
      }
      else
      {
        ePackage = EcoreFactory.eINSTANCE.createEPackage();
        setAnnotations(ePackage, containingXSDSchema);
        addOutput(ePackage);
        if (targetNamespace == null)
        {
          if (containingXSDSchema == null)
          {
            containingXSDSchema = rootSchema;
          }
          ePackage.setName(validName(containingXSDSchema.eResource().getURI().trimFileExtension().lastSegment(), true));
          ePackage.setNsURI(containingXSDSchema.eResource().getURI().toString());

          // Also register against the nsURI for the case that the target namespace is null.
          //
          // extendedMetaData.putPackage(ePackage.getNsURI(), ePackage);
        }
        else
        {
          URI uri = URI.createURI(targetNamespace);
          List parsedName;
          if (uri.isHierarchical())
          {
            String host = uri.host();
            if (host != null && host.startsWith("www."))
            {
              host = host.substring(4);
            }
            parsedName = parseName(host, '.');
            Collections.reverse(parsedName);
            if (!parsedName.isEmpty())
            {
              parsedName.set(0, ((String)parsedName.get(0)).toLowerCase());
            }
    
            parsedName.addAll(parseName(uri.trimFileExtension().path(), '/'));
          }
          else
          {
            String opaquePart = uri.opaquePart();
            int index = opaquePart.indexOf(":");
            if (index != -1 && "urn".equalsIgnoreCase(uri.scheme()))
            {
              parsedName = parseName(opaquePart.substring(0, index), '-');
              if (parsedName.size() > 0 && DOMAINS.contains(parsedName.get(parsedName.size() - 1))) 
              {
                Collections.reverse(parsedName);
                parsedName.set(0, ((String)parsedName.get(0)).toLowerCase());
              }
              parsedName.addAll(parseName(opaquePart.substring(index + 1), '/'));
            }
            else
            {
              parsedName = parseName(opaquePart, '/');
            }
          }

          StringBuffer qualifiedPackageName = new StringBuffer();
          for (Iterator i = parsedName.iterator(); i.hasNext(); )
          {
            String packageName = (String)i.next();
            if (packageName.length() > 0)
            {
              if (qualifiedPackageName.length() > 0)
              {
                qualifiedPackageName.append('.');
              }
              qualifiedPackageName.append(validName(packageName, false));
            }
          }
          ePackage.setName(qualifiedPackageName.toString());
          ePackage.setNsURI(targetNamespace);
        }

        String nsPrefix = ePackage.getName();
        int index = nsPrefix.lastIndexOf('.');
        ePackage.setNsPrefix(index == -1 ? nsPrefix : nsPrefix.substring(index + 1));

        extendedMetaData.setQualified(ePackage, targetNamespace != null);
        extendedMetaData.putPackage(targetNamespace, ePackage);
      }

      targetNamespaceToEPackageMap.put(targetNamespace, ePackage);
    }

    return ePackage;
  }


  public EClassifier getEClassifier(XSDTypeDefinition xsdTypeDefinition)
  {
    EClassifier eClassifier = (EClassifier)xsdComponentToEModelElementMap.get(xsdTypeDefinition);
    if (eClassifier == null)
    {
      eClassifier = computeEClassifier(xsdTypeDefinition);
      map(xsdTypeDefinition, eClassifier);
    }
    return eClassifier;
  }

  public EDataType getEDataType(XSDSimpleTypeDefinition xsdSimpleTypeDefinition)
  {
    EDataType eDataType = (EDataType)xsdComponentToEModelElementMap.get(xsdSimpleTypeDefinition);
    if (eDataType == null)
    {
      eDataType = computeEDataType(xsdSimpleTypeDefinition);
      map(xsdSimpleTypeDefinition, eDataType);
    }
    return eDataType;
  }

  public EClass getEClass(XSDComplexTypeDefinition xsdComplexTypeDefinition)
  {
    EClass eClass = (EClass)xsdComponentToEModelElementMap.get(xsdComplexTypeDefinition);
    if (eClass == null)
    {
      eClass = computeEClass(xsdComplexTypeDefinition);
      map(xsdComplexTypeDefinition, eClass);
    }
    return eClass;
  }

  protected EClassifier getBuiltInEClassifier(String namespace, String name)
  {
    return
      "anyType".equals(name) ?
        EcorePackage.eINSTANCE.getEObject() :
        extendedMetaData.getType(XMLTypePackage.eINSTANCE, name);
  }

  protected EClassifier computeEClassifier(XSDTypeDefinition xsdTypeDefinition)
  {
    if (xsdTypeDefinition == null)
    {
      return getBuiltInEClassifier(rootSchema.getSchemaForSchemaNamespace(), "anySimpleType");
    }
    else if (xsdTypeDefinition instanceof XSDSimpleTypeDefinition)
    {
      return computeEDataType((XSDSimpleTypeDefinition)xsdTypeDefinition);
    }
    else
    {
      return computeEClass((XSDComplexTypeDefinition)xsdTypeDefinition);
    }
  }

  protected EDataType computeEDataType(XSDSimpleTypeDefinition xsdSimpleTypeDefinition)
  {
    if (xsdSimpleTypeDefinition == null)
    {
      return (EDataType)getBuiltInEClassifier(rootSchema.getSchemaForSchemaNamespace(), "anySimpleType");
    }
    else if (XSDConstants.isSchemaForSchemaNamespace(xsdSimpleTypeDefinition.getTargetNamespace()))
    {
      String name = xsdSimpleTypeDefinition.getName();
      if (name != null)
      {
        EDataType result = (EDataType)getBuiltInEClassifier(xsdSimpleTypeDefinition.getTargetNamespace(), name);
        if (result != null)
        {
          return result;
        }
      }
    }
    else if (xsdSimpleTypeDefinition.getContainer() == null)
    {
      return (EDataType)getBuiltInEClassifier(rootSchema.getSchemaForSchemaNamespace(), "anySimpleType");
    }

    String explicitInstanceClassName = getEcoreAttribute(xsdSimpleTypeDefinition, "instanceClass");
    if (explicitInstanceClassName != null)
    {
      EDataType eDataType = EcoreFactory.eINSTANCE.createEDataType();
      setAnnotations(eDataType, xsdSimpleTypeDefinition);

      String aliasName = getEcoreAttribute(xsdSimpleTypeDefinition, "name");
      if (aliasName == null)
      {
         aliasName = validName(xsdSimpleTypeDefinition.getAliasName(), true);
      }
      eDataType.setName(aliasName);
      extendedMetaData.setName(eDataType, xsdSimpleTypeDefinition.getAliasName());

      eDataType.setInstanceClassName(explicitInstanceClassName);

      EPackage ePackage = getEPackage(xsdSimpleTypeDefinition);
      addToSortedList(ePackage.getEClassifiers(), eDataType);

      checkForPrimitive(eDataType);

      handleFacets(xsdSimpleTypeDefinition, eDataType);

      return eDataType;
    }
    else
    {
      EEnum eEnum = computeEEnum(xsdSimpleTypeDefinition);
      if (eEnum != null)
      {
        return eEnum;
      }
      else
      {
        XSDSimpleTypeDefinition baseTypeDefinition = xsdSimpleTypeDefinition.getBaseTypeDefinition();
        if (baseTypeDefinition != null)
        {
          EDataType eDataType = EcoreFactory.eINSTANCE.createEDataType();
          setAnnotations(eDataType, xsdSimpleTypeDefinition);

          String name = getEcoreAttribute(xsdSimpleTypeDefinition, "name");
          if (name == null)
          {
            name = validName(xsdSimpleTypeDefinition.getAliasName(), true);
          }

          eDataType.setName(name);
          extendedMetaData.setName(eDataType, xsdSimpleTypeDefinition.getAliasName());

          EPackage ePackage = getEPackage(xsdSimpleTypeDefinition);
          addToSortedList(ePackage.getEClassifiers(), eDataType);

          if (baseTypeDefinition.getVariety() != xsdSimpleTypeDefinition.getVariety())
          {
            if (xsdSimpleTypeDefinition.getVariety() == XSDVariety.LIST_LITERAL)
            {
              EDataType itemEDataType = getEDataType(xsdSimpleTypeDefinition.getItemTypeDefinition());
              extendedMetaData.setItemType(eDataType, itemEDataType);
              eDataType.setInstanceClassName("java.util.List");
            }
            else
            {
              String instanceClassName = null;
              List memberTypes = new ArrayList();
              for (Iterator i = xsdSimpleTypeDefinition.getMemberTypeDefinitions().iterator(); i.hasNext(); )
              {
                XSDSimpleTypeDefinition memberTypeDefinition = (XSDSimpleTypeDefinition)i.next();
                EDataType memberEDataType = getEDataType(memberTypeDefinition);
                memberTypes.add(memberEDataType);
                String memberInstanceClassName = memberEDataType.getInstanceClassName();
                if (memberInstanceClassName == null && memberEDataType instanceof EEnum)
                {
                  memberInstanceClassName = "org.eclipse.emf.common.util.AbstractEnumerator";
                }
                if (instanceClassName == null)
                {
                  instanceClassName = memberInstanceClassName;
                }
                else if (instanceClassName != memberInstanceClassName)
                {
                  instanceClassName = "java.lang.Object";
                }
              }
              extendedMetaData.setMemberTypes(eDataType, memberTypes);
              eDataType.setInstanceClassName(instanceClassName);
            }
          }
          else
          {
            EDataType baseEDataType = getEDataType(baseTypeDefinition);
            extendedMetaData.setBaseType(eDataType, baseEDataType);
            String instanceClassName = baseEDataType.getInstanceClassName();
            eDataType.setInstanceClassName
              (instanceClassName == null ? 
                 "org.eclipse.emf.common.util.AbstractEnumerator" : 
                 instanceClassName);
          }

          checkForPrimitive(eDataType);
          handleFacets(xsdSimpleTypeDefinition, eDataType);

          return eDataType;
        }
      }
      return (EDataType)getBuiltInEClassifier(rootSchema.getSchemaForSchemaNamespace(), "anySimpleType");
    }
  }

  protected static final List PRIMITIVES = 
    Arrays.asList
      (new String [] 
       {
        "boolean", 
        "byte", 
        "char", 
        "double",
        "float", 
        "int", 
        "long", 
        "short"
       });

  protected static final String [] PRIMITIVE_WRAPPERS = 
    new String [] 
    {
     "java.lang.Boolean", 
     "java.lang.Byte", 
     "java.lang.Character", 
     "java.lang.Double",
     "java.lang.Float", 
     "java.lang.Integer", 
     "java.lang.Long", 
     "java.lang.Short" 
    };

  protected static boolean canSupportNull(EDataType eDataType)
  {
    return !(eDataType instanceof EEnum) && PRIMITIVES.indexOf(eDataType.getInstanceClassName()) == -1;
  }

  protected void checkForPrimitive(EDataType eDataType)
  {
    int index = PRIMITIVES.indexOf(eDataType.getInstanceClassName());
    if (index != -1 || eDataType instanceof EEnum)
    {
      EDataType eDataTypeObject = EcoreFactory.eINSTANCE.createEDataType();
      eDataTypeObject.setName(eDataType.getName() + "Object");
      if (index != -1)
      {
        eDataTypeObject.setInstanceClassName(PRIMITIVE_WRAPPERS[index]);
      }
      else
      {
        eDataTypeObject.setInstanceClassName("org.eclipse.emf.common.util.AbstractEnumerator");
      }
      extendedMetaData.setName(eDataTypeObject, extendedMetaData.getName(eDataType) + ":Object");
      addToSortedList(eDataType.getEPackage().getEClassifiers(), eDataTypeObject);
      extendedMetaData.setBaseType(eDataTypeObject, eDataType);
    }
  }

  protected void handleFacets(final XSDSimpleTypeDefinition xsdSimpleTypeDefinition, final EDataType eDataType)
  {
    final List enumeration = new ArrayList();
    final List pattern = new ArrayList();
    for (Iterator i = xsdSimpleTypeDefinition.getFacetContents().iterator(); i.hasNext(); )
    {
      final XSDFacet xsdFacet = (XSDFacet)i.next();
      String ignore = getEcoreAttribute(xsdFacet, "ignore");
      if (!"true".equalsIgnoreCase(ignore))
      {
        new XSDSwitch ()
        {
          public Object caseXSDEnumerationFacet(XSDEnumerationFacet xsdEnumerationFacet)
          {
            enumeration.add(xsdEnumerationFacet.getLexicalValue());
            return this;
          }

          public Object caseXSDFractionDigitsFacet(XSDFractionDigitsFacet xsdFractionDigitsFacet)
          {
            extendedMetaData.setFractionDigitsFacet(eDataType, xsdFractionDigitsFacet.getValue());
            return this;
          }

          public Object caseXSDLengthFacet(XSDLengthFacet xsdLengthFacet)
          {
            extendedMetaData.setLengthFacet(eDataType, xsdLengthFacet.getValue());
            return this;
          }
          public Object caseXSDMaxExclusiveFacet(XSDMaxExclusiveFacet xsdMaxExclusiveFacet)
          {
            extendedMetaData.setMaxExclusiveFacet(eDataType, xsdMaxExclusiveFacet.getLexicalValue());
            return this;
          }

          public Object caseXSDMaxInclusiveFacet(XSDMaxInclusiveFacet xsdMaxInclusiveFacet)
          {
            extendedMetaData.setMaxInclusiveFacet(eDataType, xsdMaxInclusiveFacet.getLexicalValue());
            return this;
          }

          public Object caseXSDMaxLengthFacet(XSDMaxLengthFacet xsdMaxLengthFacet)
          {
            extendedMetaData.setMaxLengthFacet(eDataType, xsdMaxLengthFacet.getValue());
            return this;
          }

          public Object caseXSDMinExclusiveFacet(XSDMinExclusiveFacet xsdMinExclusiveFacet)
          {
            extendedMetaData.setMinExclusiveFacet(eDataType, xsdMinExclusiveFacet.getLexicalValue());
            return this;
          }

          public Object caseXSDMinInclusiveFacet(XSDMinInclusiveFacet xsdMinInclusiveFacet)
          {
            extendedMetaData.setMinInclusiveFacet(eDataType, xsdMinInclusiveFacet.getLexicalValue());
            return this;
          }

          public Object caseXSDMinLengthFacet(XSDMinLengthFacet xsdMinLengthFacet)
          {
            extendedMetaData.setMinLengthFacet(eDataType, xsdMinLengthFacet.getValue());
            return this;
          }

          public Object caseXSDPatternFacet(XSDPatternFacet xsdPatternFacet)
          {
            pattern.add(xsdPatternFacet.getLexicalValue());
            return this;
          }

          public Object caseXSDTotalDigitsFacet(XSDTotalDigitsFacet xsdTotalDigitsFacet)
          {
            extendedMetaData.setTotalDigitsFacet(eDataType, xsdTotalDigitsFacet.getValue());
            return this;
          }

          public Object caseXSDWhiteSpaceFacet(XSDWhiteSpaceFacet xsdWhiteSpaceFacet)
          {
            extendedMetaData.setWhiteSpaceFacet(eDataType, xsdWhiteSpaceFacet.getValue().getValue() + 1);
            return this;
          }
        }.doSwitch(xsdFacet);
      }
    }
    extendedMetaData.setEnumerationFacet(eDataType, enumeration);
    extendedMetaData.setPatternFacet(eDataType, pattern);
  }

  protected EEnum computeEEnum(XSDSimpleTypeDefinition xsdSimpleTypeDefinition)
  {
    // If it has enumerators and each one is a valid Java identifier.
    //
    if (!xsdSimpleTypeDefinition.getEnumerationFacets().isEmpty())
    {
      List enumerators = new ArrayList();
      LOOP:
      for (Iterator i = xsdSimpleTypeDefinition.getEnumerationFacets().iterator(); 
           i.hasNext(); )
      {
        XSDEnumerationFacet xsdEnumerationFacet = (XSDEnumerationFacet)i.next();
        String enumerator = xsdEnumerationFacet.getLexicalValue();
        if (enumerator != null && enumerator.length() != 0)
        {
          if (Character.isJavaIdentifierStart(enumerator.charAt(0)))
          {
            for (int j = enumerator.length() - 1; j > 0; --j)
            {
              if (!Character.isJavaIdentifierPart(enumerator.charAt(j)))
              {
                enumerators.clear();
                break LOOP;
              }
            }
          }
          else
          {
            enumerators.clear();
            break;
          }

          enumerators.add(xsdEnumerationFacet);
          continue;
        }

        enumerators.clear();
        break;
      }

      if (!enumerators.isEmpty())
      {
        EEnum eEnum = EcoreFactory.eINSTANCE.createEEnum();
        setAnnotations(eEnum, xsdSimpleTypeDefinition);
        eEnum.setName(validName(xsdSimpleTypeDefinition.getAliasName(), true));
        extendedMetaData.setName(eEnum, xsdSimpleTypeDefinition.getAliasName());

        EPackage ePackage = getEPackage(xsdSimpleTypeDefinition);
        addToSortedList(ePackage.getEClassifiers(), eEnum);

        for (ListIterator i = enumerators.listIterator(); i.hasNext(); )
        {
          XSDEnumerationFacet xsdEnumerationFacet = (XSDEnumerationFacet)i.next();
          String enumerator = xsdEnumerationFacet.getLexicalValue();
          EEnumLiteral eEnumLiteral = EcoreFactory.eINSTANCE.createEEnumLiteral();
          setAnnotations(eEnumLiteral, xsdEnumerationFacet);
          eEnumLiteral.setName(enumerator);
          eEnumLiteral.setValue(i.previousIndex());
          eEnum.getELiterals().add(eEnumLiteral);
          map(xsdEnumerationFacet, eEnumLiteral);
        }

        checkForPrimitive(eEnum);

        return eEnum;
      }
    }

    return null;
  }

  public EClass computeEClass(XSDComplexTypeDefinition xsdComplexTypeDefinition)
  {
    if (xsdComplexTypeDefinition == null)
    {
      return (EClass)getBuiltInEClassifier(rootSchema.getSchemaForSchemaNamespace(), "anyType");
    }
    else if (XSDConstants.isSchemaForSchemaNamespace(xsdComplexTypeDefinition.getTargetNamespace()))
    {
      String name = xsdComplexTypeDefinition.getName();
      if (name != null)
      {
        EClass result = (EClass)getBuiltInEClassifier(xsdComplexTypeDefinition.getTargetNamespace(), name);
        if (result != null)
        {
          return result;
        }
      }
    }
    else if (xsdComplexTypeDefinition.getContainer() == null)
    {
      return (EClass)getBuiltInEClassifier(rootSchema.getSchemaForSchemaNamespace(), "anyType");
    }

    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    setAnnotations(eClass, xsdComplexTypeDefinition);
    // Do this early to prevent recursive loop.
    xsdComponentToEModelElementMap.put(xsdComplexTypeDefinition, eClass);

    String aliasName = getEcoreAttribute(xsdComplexTypeDefinition, "name");
    if (aliasName == null)
    {
      aliasName = validName(xsdComplexTypeDefinition.getAliasName(), true);
    }
    eClass.setName(aliasName);
    extendedMetaData.setName(eClass, xsdComplexTypeDefinition.getAliasName());

    EPackage ePackage = getEPackage(xsdComplexTypeDefinition);
    addToSortedList(ePackage.getEClassifiers(), eClass);

    if (xsdComplexTypeDefinition.isAbstract())
    {
      eClass.setAbstract(true);
    }

    XSDTypeDefinition baseTypeDefinition = xsdComplexTypeDefinition.getBaseTypeDefinition();
    if (!XSDConstants.isURType(baseTypeDefinition))
    {
      EClassifier baseType = getEClassifier(baseTypeDefinition);
      if (baseType instanceof EClass && baseType != EcorePackage.eINSTANCE.getEObject())
      {
        eClass.getESuperTypes().add(baseType);
      }
    }

    if (eClass.getESuperTypes().isEmpty() ||
          xsdComplexTypeDefinition.getDerivationMethod() == XSDDerivationMethod.EXTENSION_LITERAL)
    {
      if (xsdComplexTypeDefinition.getContentTypeCategory() == XSDContentTypeCategory.SIMPLE_LITERAL)
      {
        extendedMetaData.setContentKind(eClass, ExtendedMetaData.SIMPLE_CONTENT);
        if (eClass.getEAllStructuralFeatures().isEmpty())
        {
          EStructuralFeature eStructuralFeature =
            createFeature
              (eClass,
               "value",
               getEClassifier(xsdComplexTypeDefinition.getSimpleType()),
               null,
               0,
               1);
        }
      }
      else 
      {
        EStructuralFeature globalGroup = null;
        boolean isMixed = xsdComplexTypeDefinition.getContentTypeCategory() == XSDContentTypeCategory.MIXED_LITERAL;
        String featureMapName = getEcoreAttribute(xsdComplexTypeDefinition, "featureMap");
        if (eClass.getESuperTypes().isEmpty() ?
              "true".equals(getEcoreAttribute(xsdComplexTypeDefinition, "mixed")) :
              extendedMetaData.getMixedFeature((EClass)eClass.getESuperTypes().get(0)) != null)
        {
          isMixed = true;
        }
        extendedMetaData.setContentKind
          (eClass, 
           isMixed ? 
             ExtendedMetaData.MIXED_CONTENT : 
             xsdComplexTypeDefinition.getContentTypeCategory() == XSDContentTypeCategory.EMPTY_LITERAL ?
               ExtendedMetaData.EMPTY_CONTENT :
               ExtendedMetaData.ELEMENT_ONLY_CONTENT);
        if (isMixed)
        {
          EStructuralFeature mixedFeature = extendedMetaData.getMixedFeature(eClass);
          if (mixedFeature == null)
          {
            if (featureMapName == null)
            {
              featureMapName = "mixed";
            }
            mixedFeature =
              createFeature
                (eClass,
                 featureMapName,
                 EcorePackage.eINSTANCE.getEFeatureMapEntry(),
                 null,
                 0,
                 -1);
            extendedMetaData.setName(mixedFeature, ":mixed");
          }
        }
        else 
        {
          globalGroup = extendedMetaData.getElement(eClass, null, ":group");
          if (globalGroup == null && featureMapName != null && eClass.getESuperTypes().isEmpty())
          {
            globalGroup =
              createFeature
                (eClass,
                 featureMapName,
                 EcorePackage.eINSTANCE.getEFeatureMapEntry(),
                 null,
                 0,
                 -1);
            extendedMetaData.setName(globalGroup, ":group");
            extendedMetaData.setFeatureKind(globalGroup, ExtendedMetaData.GROUP_FEATURE);
          }
        }

        if (xsdComplexTypeDefinition.getContent() != null)
        {
          Map groups = new HashMap();
          List particleInformation = collectParticles((XSDParticle)xsdComplexTypeDefinition.getContent());
          for (Iterator i = particleInformation.iterator(); i.hasNext(); )
          {
            EffectiveOccurrence effectiveOccurrence = (EffectiveOccurrence)i.next();
            XSDParticle xsdParticle = effectiveOccurrence.xsdParticle;
            EStructuralFeature group = (EStructuralFeature)groups.get(effectiveOccurrence.xsdModelGroup);
            XSDTerm xsdTerm = xsdParticle.getTerm();
            EStructuralFeature eStructuralFeature;
            String name = getEcoreAttribute(xsdParticle, "name");
            if (xsdTerm instanceof XSDModelGroup)
            {
              XSDModelGroup xsdModelGroup = (XSDModelGroup)xsdTerm;
              if (name == null)
              {
                name = getEcoreAttribute(xsdParticle, "featureMap");
                if (name == null)
                {
                  name = getEcoreAttribute(xsdModelGroup, "name");
                  if (name == null)
                  {
                    name = getEcoreAttribute(xsdModelGroup, "featureMap");
                    if (name == null)
                    {
                      if (xsdModelGroup.getContainer() instanceof XSDModelGroupDefinition)
                      {
                        XSDModelGroupDefinition xsdModelGroupDefinition = (XSDModelGroupDefinition)xsdModelGroup.getContainer();
                        name =  getEcoreAttribute(xsdModelGroupDefinition, "name");
                        if (name == null)
                        {
                          name = validName(xsdModelGroupDefinition.getName(), true);
                        }
                      }
                      else
                      {
                        name = "group";
                      }
                    }
                  }
                }
              }

              eStructuralFeature =
                createFeature
                  (eClass,
                   name,
                   EcorePackage.eINSTANCE.getEFeatureMapEntry(),
                   xsdParticle,
                   0,
                   -1);
              groups.put(xsdTerm, eStructuralFeature);
              extendedMetaData.setName(eStructuralFeature, name + ":" + eClass.getEAllStructuralFeatures().indexOf(eStructuralFeature));
            }
            else if (xsdTerm instanceof XSDWildcard)
            {
              if (name == null)
              {
                name = getEcoreAttribute(xsdTerm, "name");
                if (name == null)
                {
                  name = "any";
                }
              }
              eStructuralFeature =
                createFeature
                  (eClass,
                   name,
                   EcorePackage.eINSTANCE.getEFeatureMapEntry(),
                   xsdParticle,
                   effectiveOccurrence.minOccurs,
                   effectiveOccurrence.maxOccurs);
            }
            else
            {
              XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration)xsdTerm;

              if (name == null)
              {
                name = getEcoreAttribute(xsdElementDeclaration, "name");
                if (name == null)
                {
                  name = validName(xsdElementDeclaration.getName(), true);
                }
              }

              String groupName = getEcoreAttribute(xsdParticle, "featureMap");
              if (groupName == null)
              {
                groupName = getEcoreAttribute(xsdElementDeclaration, "featureMap");
              }

              if (!"".equals(groupName) &&
                   (groupName != null  ||
                      xsdElementDeclaration.isAbstract() || 
                      xsdElementDeclaration.getSubstitutionGroup().size() > 1))
              {
                if (groupName == null)
                {
                  groupName = name + "Group";
                }
                eStructuralFeature = 
                  createFeature
                    (eClass,
                     groupName,
                     EcorePackage.eINSTANCE.getEFeatureMapEntry(),
                     xsdParticle,
                     effectiveOccurrence.minOccurs,
                     effectiveOccurrence.maxOccurs);

                eStructuralFeature.setChangeable(true);

                extendedMetaData.setFeatureKind(eStructuralFeature, ExtendedMetaData.GROUP_FEATURE);
                extendedMetaData.setName(eStructuralFeature, xsdElementDeclaration.getName() + ":group");

                if (group != null)
                {
                  extendedMetaData.setGroup(eStructuralFeature, group);
                  eStructuralFeature.setDerived(true);
                  eStructuralFeature.setTransient(true);
                  eStructuralFeature.setVolatile(true);
                }
                else if (isMixed)
                {
                  eStructuralFeature.setDerived(true);
                  eStructuralFeature.setTransient(true);
                  eStructuralFeature.setVolatile(true);
                }
                else if (globalGroup != null)
                {
                  extendedMetaData.setGroup(eStructuralFeature, globalGroup);
                  eStructuralFeature.setDerived(true);
                  eStructuralFeature.setTransient(true);
                  eStructuralFeature.setVolatile(true);
                }

                group = eStructuralFeature;
              }

              XSDTypeDefinition elementTypeDefinition = xsdElementDeclaration.getTypeDefinition();
              EClassifier eClassifier = getEClassifier(elementTypeDefinition);

              XSDTypeDefinition referenceType = getEcoreTypeQNameAttribute(xsdElementDeclaration, "reference");
              if (referenceType != null)
              {
                EClassifier referenceClassifier = getEClassifier(referenceType);

                boolean needsHolder = false;
                if (elementTypeDefinition instanceof XSDSimpleTypeDefinition)
                {
                  XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)elementTypeDefinition;
                  if (xsdSimpleTypeDefinition.getVariety() == XSDVariety.LIST_LITERAL)
                  {
                    needsHolder = true;
                    // Create a holder class like an anonymous complex type.
                    //
                    EClass holderClass = EcoreFactory.eINSTANCE.createEClass();
                    setAnnotations(holderClass, xsdElementDeclaration);
                    String holderName = xsdElementDeclaration.getName() + ":holder";
                    holderClass.setName(validName(holderName, true));
                    extendedMetaData.setName(holderClass, holderName);
                    extendedMetaData.setContentKind(holderClass, ExtendedMetaData.SIMPLE_CONTENT);
              
                    EPackage holderPackage = getEPackage(xsdElementDeclaration);
                    addToSortedList(holderPackage.getEClassifiers(), holderClass);

                    EReference holderReference =
                      (EReference)createFeature
                        (holderClass,
                         "value",
                         referenceClassifier,
                         null,
                         0,
                         -1);

                    holderReference.setResolveProxies(!isLocalReferenceType(xsdSimpleTypeDefinition));

                    referenceClassifier = holderClass;
                  }
                }

                eStructuralFeature =
                  createFeature
                    (eClass,
                     name,
                     referenceClassifier,
                     xsdParticle,
                     effectiveOccurrence.minOccurs,
                     effectiveOccurrence.maxOccurs);

                ((EReference)eStructuralFeature).setContainment(needsHolder);
                if (needsHolder)
                {
                  ((EReference)eStructuralFeature).setUnsettable(false);
                  ((EReference)eStructuralFeature).setResolveProxies(false);
                }
              }
              else
              {
                eStructuralFeature =
                  createFeature
                    (eClass,
                     name,
                     eClassifier,
                     xsdParticle,
                     effectiveOccurrence.minOccurs,
                     effectiveOccurrence.maxOccurs);
              }

              // If the group is turned off, we better make the feature changeable.
              //
              if (!eStructuralFeature.isChangeable() && group == null)
              {
                eStructuralFeature.setChangeable(true);
              }
            }

            if (group != null)
            {
              extendedMetaData.setGroup(eStructuralFeature, group);
              eStructuralFeature.setDerived(true);
              eStructuralFeature.setTransient(true);
              eStructuralFeature.setVolatile(true);
            }
            else if (isMixed)
            {
              eStructuralFeature.setDerived(true);
              eStructuralFeature.setTransient(true);
              eStructuralFeature.setVolatile(true);
            }
            else if (globalGroup != null)
            {
              extendedMetaData.setGroup(eStructuralFeature, globalGroup);
              eStructuralFeature.setDerived(true);
              eStructuralFeature.setTransient(true);
              eStructuralFeature.setVolatile(true);
            }
          }
        }
      }

      Collection baseAttributeUses = new HashSet();
      if (baseTypeDefinition instanceof XSDComplexTypeDefinition)
      {
        for (Iterator i = ((XSDComplexTypeDefinition)baseTypeDefinition).getAttributeUses().iterator(); i.hasNext(); )
        {
          baseAttributeUses.add(((XSDAttributeUse)i.next()).getAttributeDeclaration().getURI());
        }
      }

      for (Iterator i = xsdComplexTypeDefinition.getAttributeUses().iterator(); i.hasNext(); )
      {
        XSDAttributeUse xsdAttributeUse = (XSDAttributeUse)i.next();
        XSDAttributeDeclaration xsdAttributeDeclaration = xsdAttributeUse.getAttributeDeclaration();
        if (!baseAttributeUses.contains(xsdAttributeDeclaration.getURI()))
        {
          String name = getEcoreAttribute(xsdAttributeUse, "name");
          if (name == null)
          {
            name = getEcoreAttribute(xsdAttributeDeclaration, "name");
          }
          if (name == null)
          {
            name = validName(xsdAttributeDeclaration.getName(), true);
          }

          XSDSimpleTypeDefinition attributeTypeDefinition = xsdAttributeDeclaration.getTypeDefinition();
          EDataType eDataType = getEDataType(attributeTypeDefinition);

          XSDTypeDefinition referenceType = getEcoreTypeQNameAttribute(xsdAttributeDeclaration, "reference");
          if (referenceType != null)
          {
            int lowerBound = xsdAttributeUse.isRequired() ? 1 : 0;
            int upperBound = 1;
            if (attributeTypeDefinition.getVariety() == XSDVariety.LIST_LITERAL)
            {
              if (xsdAttributeUse.isRequired())
              {
                XSDMinLengthFacet xsdMinLengthFacet = attributeTypeDefinition.getEffectiveMinLengthFacet();
                if (xsdMinLengthFacet != null)
                {
                  lowerBound = xsdMinLengthFacet.getValue();
                }
              }
              XSDMaxLengthFacet xsdMaxLengthFacet = attributeTypeDefinition.getEffectiveMaxLengthFacet();
              if (xsdMaxLengthFacet != null)
              {
                upperBound = xsdMaxLengthFacet.getValue();
              }
              else
              {
                upperBound = -1;
              }
            }

            EClassifier referenceClassifier = getEClassifier(referenceType);
            EStructuralFeature eStructuralFeature =
              createFeature
                (eClass,
                 name,
                 referenceClassifier,
                 xsdAttributeUse,
                 lowerBound,
                 upperBound);
          }
          else
          {
            EStructuralFeature eStructuralFeature =
              createFeature
                (eClass,
                 name,
                 eDataType,
                 xsdAttributeUse,
                 xsdAttributeUse.isRequired() ? 1 : 0,
                 1);
          }
        }
      }

      XSDWildcard xsdWildcard = xsdComplexTypeDefinition.getAttributeWildcardContent();
      if (xsdWildcard != null)
      {
        String name = getEcoreAttribute(xsdWildcard, "name");
        if (name == null)
        {
          name = "anyAttribute";
        }
        EStructuralFeature eStructuralFeature =
          createFeature
            (eClass,
             name,
             EcorePackage.eINSTANCE.getEFeatureMapEntry(),
             xsdWildcard,
             0,
             -1);
      }
    }
    else
    {
      int baseContentKind = extendedMetaData.getContentKind((EClass)eClass.getESuperTypes().get(0));
      if (baseContentKind == ExtendedMetaData.MIXED_CONTENT && 
            xsdComplexTypeDefinition.getContentTypeCategory() == XSDContentTypeCategory.SIMPLE_LITERAL)
      {
        extendedMetaData.setContentKind(eClass, ExtendedMetaData.SIMPLE_CONTENT);
        EStructuralFeature eStructuralFeature =
          createFeature
            (eClass,
             "rawValue",
             getBuiltInEClassifier(xsdComplexTypeDefinition.getSchema().getSchemaForSchemaNamespace(), "string"),
             null,
             0,
             1);
        eStructuralFeature.setDerived(true);
        eStructuralFeature.setTransient(true);
        eStructuralFeature.setVolatile(true);

        eStructuralFeature =
          createFeature
            (eClass,
             "value",
             getBuiltInEClassifier(xsdComplexTypeDefinition.getSchema().getSchemaForSchemaNamespace(), "anySimpleType"),
             null,
             0,
             1);
        eStructuralFeature.setDerived(true);
        eStructuralFeature.setTransient(true);
        eStructuralFeature.setVolatile(true);

        if ("SimpleAnyType".equals(eClass.getName()) && XMLTypePackage.eNS_URI.equals(eClass.getEPackage().getNsURI()))
        {
          eStructuralFeature =
            createFeature
              (eClass,
               "instanceType",
               EcorePackage.eINSTANCE.getEDataType(),
               null,
               1,
               1);

          ((EReference)eStructuralFeature).setResolveProxies(false);
        }
      }
      else
      {
        extendedMetaData.setContentKind(eClass, baseContentKind);
      }
    }

    return eClass;
  }

  protected final List ANY_NAMESPACE_WILDCARD = Arrays.asList(new String [] { "##any" });
  protected final List NOT_NULL_WILDCARD = Arrays.asList(new String [] { "!##" });
  protected List getWildcards(XSDWildcard xsdWildcard)
  {
    switch (xsdWildcard.getNamespaceConstraintCategory().getValue())
    {
      case XSDNamespaceConstraintCategory.ANY:
      {
        return ANY_NAMESPACE_WILDCARD;
      }
      case XSDNamespaceConstraintCategory.NOT:
      {
        String targetNamespace = null;
        XSDSchema containingXSDSchema = xsdWildcard.getSchema();
        if (containingXSDSchema != null)
        {
          targetNamespace = containingXSDSchema.getTargetNamespace();
        }
        return targetNamespace == null ? NOT_NULL_WILDCARD : Collections.singletonList("!##" + targetNamespace);
      }
      case XSDNamespaceConstraintCategory.SET:
      {
        return xsdWildcard.getNamespaceConstraint();
      }
      default:
      {
        throw 
          new UnsupportedOperationException
            ("Unknown XSDNamespaceConstraintCategory: " + xsdWildcard.getNamespaceConstraintCategory().getName());
      }
    }
  }

  protected EStructuralFeature createFeature(EClass eClass, String name, EClassifier type, XSDComponent xsdComponent)
  {
    return createFeature(eClass, name, type, xsdComponent, 0, 1);
  }

  protected EStructuralFeature createFeature
    (EClass eClass, String name, EClassifier type, XSDComponent xsdComponent, int minOccurs, int maxOccurs)
  {
    if (xsdComponent != null)
    {
      XSDSchema containingXSDSchema = xsdComponent.getSchema();
      if (containingXSDSchema != null && !xsdSchemas.contains(containingXSDSchema))
      {
        xsdSchemas.add(containingXSDSchema);
        addInput(containingXSDSchema);
        validate(containingXSDSchema);
      }
    }
    else if (extendedMetaData.getContentKind(eClass) == ExtendedMetaData.MIXED_CONTENT)
    {
      if (type == EcorePackage.eINSTANCE.getEFeatureMapEntry())
      {
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        setAnnotations(eAttribute, xsdComponent);
        eAttribute.setName(Character.toLowerCase(name.charAt(0)) + name.substring(1));
        eAttribute.setUnique(false);
        eAttribute.setEType(type);
        eAttribute.setLowerBound(minOccurs);
        eAttribute.setUpperBound(maxOccurs);
        eClass.getEStructuralFeatures().add(eAttribute);
        extendedMetaData.setFeatureKind(eAttribute, ExtendedMetaData.ELEMENT_WILDCARD_FEATURE);
        extendedMetaData.setName(eAttribute, ":" + eAttribute.getName());
        return eAttribute;
      }
      else
      {
        EReference eReference = EcoreFactory.eINSTANCE.createEReference();
        setAnnotations(eReference, xsdComponent);
        eReference.setName(name);
        eReference.setUnique(false);
        eReference.setEType(EcorePackage.eINSTANCE.getEStringToStringMapEntry());
        eReference.setLowerBound(0);
        eReference.setUpperBound(-1);
        eReference.setContainment(true);
        eReference.setResolveProxies(false);
        eReference.setTransient(true);
        eClass.getEStructuralFeatures().add(eReference);
        extendedMetaData.setFeatureKind(eReference, ExtendedMetaData.ATTRIBUTE_FEATURE);
        return eReference;
      }
    }

    if (type instanceof EClass)
    {
      EReference eReference = EcoreFactory.eINSTANCE.createEReference();
      setAnnotations(eReference, xsdComponent);
      eReference.setName(Character.toLowerCase(name.charAt(0)) + name.substring(1));
      eReference.setEType(type);
      eReference.setLowerBound(minOccurs);
      eReference.setUpperBound(maxOccurs);

      eClass.getEStructuralFeatures().add(eReference);
      if (xsdComponent == null)
      {
        extendedMetaData.setName(eReference, ":" + eClass.getEAllStructuralFeatures().indexOf(eReference));
        extendedMetaData.setFeatureKind(eReference, ExtendedMetaData.SIMPLE_FEATURE);
      }
      else 
      {
        map(xsdComponent, eReference);
        if (xsdComponent instanceof XSDParticle)
        {
          eReference.setContainment(true);
          eReference.setResolveProxies(false);

          XSDParticle xsdParticle = (XSDParticle)xsdComponent;

          XSDTerm xsdTerm = ((XSDParticle)xsdComponent).getTerm();
          if (xsdTerm instanceof XSDElementDeclaration)
          {
            XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration)xsdTerm;
            extendedMetaData.setFeatureKind(eReference, ExtendedMetaData.ELEMENT_FEATURE);
            extendedMetaData.setName(eReference, xsdElementDeclaration.getName());
            extendedMetaData.setNamespace(eReference, xsdElementDeclaration.getTargetNamespace());

            if (xsdElementDeclaration.getTypeDefinition() instanceof XSDSimpleTypeDefinition)
            {
              eReference.setContainment(false);
              eReference.setResolveProxies(!isLocalReferenceType((XSDSimpleTypeDefinition)xsdElementDeclaration.getTypeDefinition()));

            }

            if (maxOccurs == 1 && xsdElementDeclaration.isNillable())
            {
              eReference.setUnsettable(true);
            }

            if (xsdElementDeclaration.isAbstract())
            {
              eReference.setChangeable(false);
            }

            String opposite = getEcoreAttribute(xsdParticle, "opposite");
            if (opposite != null)
            {
              eReferenceToOppositeNameMap.put(eReference, opposite);
            }
          }
          else if (xsdTerm instanceof XSDWildcard)
          {
            // EATM shouldn't happen
            XSDWildcard xsdWildcard = (XSDWildcard)xsdTerm;
            extendedMetaData.setFeatureKind(eReference, ExtendedMetaData.ELEMENT_WILDCARD_FEATURE);
            extendedMetaData.setWildcards(eReference, getWildcards(xsdWildcard));
            extendedMetaData.setProcessingKind(eReference, xsdWildcard.getProcessContents().getValue() + 1);
            extendedMetaData.setName(eReference, ":" + eClass.getEAllStructuralFeatures().indexOf(eReference));
          }
          else
          {
            extendedMetaData.setFeatureKind(eReference, ExtendedMetaData.GROUP_FEATURE);
            extendedMetaData.setName(eReference, ":" + eClass.getEAllStructuralFeatures().indexOf(eReference));
          }
        }
        else if (xsdComponent instanceof XSDElementDeclaration)
        {
          XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration)xsdComponent;
          eReference.setContainment(true);
          eReference.setResolveProxies(false);
          extendedMetaData.setFeatureKind(eReference, ExtendedMetaData.ELEMENT_FEATURE);
          extendedMetaData.setName(eReference, xsdElementDeclaration.getName());
          extendedMetaData.setNamespace(eReference, xsdElementDeclaration.getTargetNamespace());

          XSDElementDeclaration substitutionGroupAffiliation = xsdElementDeclaration.getSubstitutionGroupAffiliation();
          if (substitutionGroupAffiliation != null)
          {
            EStructuralFeature affiliation = getEStructuralFeature(substitutionGroupAffiliation);
            extendedMetaData.setAffiliation(eReference, affiliation);
          }
          if (xsdElementDeclaration.getTypeDefinition() instanceof XSDSimpleTypeDefinition)
          {
            eReference.setResolveProxies(!isLocalReferenceType((XSDSimpleTypeDefinition)xsdElementDeclaration.getTypeDefinition()));
          }

          if (maxOccurs == 1 && xsdElementDeclaration.isNillable())
          {
            eReference.setUnsettable(true);
          }

          if (xsdElementDeclaration.isAbstract())
          {
            eReference.setChangeable(false);
          }
        }
        else if (xsdComponent instanceof XSDAttributeUse)
        {
          XSDAttributeUse xsdAttributeUse = (XSDAttributeUse)xsdComponent;
          XSDAttributeDeclaration xsdAttributeDeclaration = xsdAttributeUse.getAttributeDeclaration();
          extendedMetaData.setFeatureKind(eReference, ExtendedMetaData.ATTRIBUTE_FEATURE);
          extendedMetaData.setName(eReference, xsdAttributeDeclaration.getName());
          extendedMetaData.setNamespace(eReference, xsdAttributeDeclaration.getTargetNamespace());
          eReference.setResolveProxies(!isLocalReferenceType(xsdAttributeDeclaration.getTypeDefinition()));
        }
      }

      return eReference;
    }
    else
    {
      EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
      setAnnotations(eAttribute, xsdComponent);
      eAttribute.setName(Character.toLowerCase(name.charAt(0)) + name.substring(1));
      eAttribute.setUnique(false);
      eAttribute.setEType(type);
      eAttribute.setLowerBound(minOccurs);
      eAttribute.setUpperBound(maxOccurs);
      eClass.getEStructuralFeatures().add(eAttribute);

      if (xsdComponent == null)
      {
        extendedMetaData.setName(eAttribute, ":" + eClass.getEAllStructuralFeatures().indexOf(eAttribute));
        extendedMetaData.setFeatureKind(eAttribute, ExtendedMetaData.SIMPLE_FEATURE);
      }
      else
      {
        map(xsdComponent, eAttribute);
        if (xsdComponent instanceof XSDAttributeUse)
        {
          XSDAttributeUse xsdAttributeUse = (XSDAttributeUse)xsdComponent;
          XSDAttributeDeclaration xsdAttributeDeclaration = xsdAttributeUse.getAttributeDeclaration();
          extendedMetaData.setFeatureKind(eAttribute, ExtendedMetaData.ATTRIBUTE_FEATURE);
          extendedMetaData.setName(eAttribute, xsdAttributeDeclaration.getName());
          extendedMetaData.setNamespace(eAttribute, xsdAttributeDeclaration.getTargetNamespace());

          eAttribute.setDefaultValueLiteral(xsdAttributeUse.getLexicalValue());
          initialize(eAttribute, xsdAttributeDeclaration.getTypeDefinition());
        }
        else if (xsdComponent instanceof XSDAttributeDeclaration)
        {
          XSDAttributeDeclaration xsdAttributeDeclaration = (XSDAttributeDeclaration)xsdComponent;
          extendedMetaData.setFeatureKind(eAttribute, ExtendedMetaData.ATTRIBUTE_FEATURE);
          extendedMetaData.setName(eAttribute, xsdAttributeDeclaration.getName());
          extendedMetaData.setNamespace(eAttribute, xsdAttributeDeclaration.getTargetNamespace());

          eAttribute.setDefaultValueLiteral(xsdAttributeDeclaration.getLexicalValue());
          initialize(eAttribute, xsdAttributeDeclaration.getTypeDefinition());
        }
        else if (xsdComponent instanceof XSDParticle)
        {
          XSDParticle xsdParticle = (XSDParticle)xsdComponent;
          XSDTerm xsdTerm = ((XSDParticle)xsdComponent).getTerm();
          if (xsdTerm instanceof XSDElementDeclaration)
          {
            XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration)xsdTerm;
            extendedMetaData.setFeatureKind(eAttribute, ExtendedMetaData.ELEMENT_FEATURE);
            extendedMetaData.setName(eAttribute, xsdElementDeclaration.getName());
            extendedMetaData.setNamespace(eAttribute, xsdElementDeclaration.getTargetNamespace());

            eAttribute.setDefaultValueLiteral(xsdElementDeclaration.getLexicalValue());
            if (xsdElementDeclaration.getTypeDefinition() instanceof XSDSimpleTypeDefinition)
            {
              initialize(eAttribute, (XSDSimpleTypeDefinition)xsdElementDeclaration.getTypeDefinition());
            }

            if (xsdElementDeclaration.isNillable())
            {
              if (!canSupportNull((EDataType)type))
              {
                eAttribute.setEType(type = extendedMetaData.getType(type.getEPackage(), extendedMetaData.getName(type) + ":Object"));
              }
              if (maxOccurs == 1)
              {
                eAttribute.setUnsettable(true);
              }
            }

            if (xsdElementDeclaration.isAbstract())
            {
              eAttribute.setChangeable(false);
            }
          }
          else if (xsdTerm instanceof XSDWildcard)
          {
            XSDWildcard xsdWildcard = (XSDWildcard)xsdTerm;
            extendedMetaData.setFeatureKind(eAttribute, ExtendedMetaData.ELEMENT_WILDCARD_FEATURE);
            extendedMetaData.setWildcards(eAttribute, getWildcards(xsdWildcard));
            extendedMetaData.setProcessingKind(eAttribute, xsdWildcard.getProcessContents().getValue() + 1);
            extendedMetaData.setName(eAttribute, ":" + eClass.getEAllStructuralFeatures().indexOf(eAttribute));
          }
          else
          {
            extendedMetaData.setFeatureKind(eAttribute, ExtendedMetaData.GROUP_FEATURE);
          }
        }
        else if (xsdComponent instanceof XSDWildcard)
        {
          XSDWildcard xsdWildcard = (XSDWildcard)xsdComponent;
          extendedMetaData.setFeatureKind(eAttribute, ExtendedMetaData.ATTRIBUTE_WILDCARD_FEATURE);
          extendedMetaData.setWildcards(eAttribute, getWildcards(xsdWildcard));
          extendedMetaData.setProcessingKind(eAttribute, xsdWildcard.getProcessContents().getValue() + 1);
          extendedMetaData.setName(eAttribute, ":" + eClass.getEAllStructuralFeatures().indexOf(eAttribute));
        }
        else if (xsdComponent instanceof XSDElementDeclaration)
        {
          XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration)xsdComponent;
          extendedMetaData.setFeatureKind(eAttribute, ExtendedMetaData.ELEMENT_FEATURE);
          extendedMetaData.setName(eAttribute, xsdElementDeclaration.getName());
          extendedMetaData.setNamespace(eAttribute, xsdElementDeclaration.getTargetNamespace());

          eAttribute.setDefaultValueLiteral(xsdElementDeclaration.getLexicalValue());
          if (xsdElementDeclaration.getTypeDefinition() instanceof XSDSimpleTypeDefinition)
          {
            initialize(eAttribute, (XSDSimpleTypeDefinition)xsdElementDeclaration.getTypeDefinition());
          }

          XSDElementDeclaration substitutionGroupAffiliation = xsdElementDeclaration.getSubstitutionGroupAffiliation();
          if (substitutionGroupAffiliation != null)
          {
            EStructuralFeature affiliation = getEStructuralFeature(substitutionGroupAffiliation);
            extendedMetaData.setAffiliation(eAttribute, affiliation);
          }

          if (xsdElementDeclaration.isNillable() && !canSupportNull((EDataType)type))
          {
            eAttribute.setEType(type = extendedMetaData.getType(type.getEPackage(), extendedMetaData.getName(type) + ":Object"));
            if (maxOccurs == 1)
            {
              eAttribute.setUnsettable(true);
            }
          }

          if (xsdElementDeclaration.isAbstract())
          {
            eAttribute.setChangeable(false);
          }
        }
      }

      if (maxOccurs == 1 && (type.getDefaultValue() != null || eAttribute.getDefaultValueLiteral() != null))
      {
        eAttribute.setUnsettable(true);
      }

      return eAttribute;
    }
  }

  protected void initialize(EAttribute eAttribute, XSDSimpleTypeDefinition xsdSimpleTypeDefinition)
  {
    if (XSDConstants.isOrIsDerivedFromID(xsdSimpleTypeDefinition))
    {
      eAttribute.setID(true);
    }

    // If there is no default value but the type has enumeration facets and the value is a primitive...
    //
    if (eAttribute.getDefaultValueLiteral() == null && 
          xsdSimpleTypeDefinition.getEffectiveEnumerationFacet() != null &&
          eAttribute.getEType().getDefaultValue() != null)
    {
      // Set the default to the first enumeration's value.
      //
      eAttribute.setDefaultValueLiteral
        (((XSDEnumerationFacet)xsdSimpleTypeDefinition.
           getEffectiveEnumerationFacet().
           getSimpleTypeDefinition().
           getEnumerationFacets().
           get(0)).getLexicalValue());
    }
  }

  public static class EffectiveOccurrence
  {
    public EffectiveOccurrence(int minOccurs, int maxOccurs, XSDParticle xsdParticle, XSDModelGroup xsdModelGroup)
    {
      this.minOccurs = minOccurs;
      this.maxOccurs = maxOccurs;
      this.xsdParticle = xsdParticle;
      this.xsdModelGroup = xsdModelGroup;
    }

    public int minOccurs;
    public int maxOccurs;
    public XSDParticle xsdParticle;
    public XSDModelGroup xsdModelGroup;
  }

  public List collectParticles(XSDParticle xsdParticle)
  {
    List result = new ArrayList();
    collectParticlesHelper(result, xsdParticle, 1, 1, null);
    return result;
  }

  public void collectParticlesHelper(List result, XSDParticle xsdParticle, int minOccurs, int maxOccurs, XSDModelGroup target)
  {
    int particleMaxOccurs = xsdParticle.getMaxOccurs();
    int effectiveMinOccurs = minOccurs * xsdParticle.getMinOccurs();
    int effectiveMaxOccurs =  maxOccurs == -1 || particleMaxOccurs == -1 ? -1 : maxOccurs * particleMaxOccurs;

    XSDTerm xsdTerm = xsdParticle.getTerm();
    if (xsdTerm instanceof XSDModelGroup)
    {
      XSDModelGroup xsdModelGroup = (XSDModelGroup)xsdTerm;
      List particles = xsdModelGroup.getParticles();
      if (particles.size() == 0)
      {
        return;
      }
      else
      {
        boolean isIgnored = 
          particleMaxOccurs == 1 || 
          particles.size() == 1 && ((XSDParticle)particles.get(0)).getTerm() instanceof XSDModelGroup;

        String featureMapName = getEcoreAttribute(xsdParticle, "name");
        if (featureMapName == null)
        {
          featureMapName = getEcoreAttribute(xsdParticle, "featureMap");
          if (featureMapName == null)
          {
            featureMapName = getEcoreAttribute(xsdTerm, "name");
            if (featureMapName == null)
            {
              featureMapName = getEcoreAttribute(xsdTerm, "featureMap");
            }
          }
        }

        if ("".equals(featureMapName))
        {
          isIgnored = true;
        }
        else if (featureMapName != null)
        {
          isIgnored = false;
        }
        else if (target != null)
        {
          isIgnored = true;
        }

        if (!isIgnored)
        {
          EffectiveOccurrence effectiveOccurrence = new EffectiveOccurrence(effectiveMinOccurs, effectiveMaxOccurs, xsdParticle, null);
          result.add(effectiveOccurrence);
          target = xsdModelGroup;
        }

        if (xsdModelGroup.getCompositor() == XSDCompositor.CHOICE_LITERAL)
        {
          effectiveMinOccurs = 0;
        }
        for (Iterator i = ((XSDModelGroup)xsdTerm).getParticles().iterator(); i.hasNext(); )
        {
          XSDParticle childXSDParticle = (XSDParticle)i.next();
          collectParticlesHelper(result, childXSDParticle, effectiveMinOccurs, effectiveMaxOccurs, target);
        }
      }
    }
    else
    {
      EffectiveOccurrence effectiveOccurrence = new EffectiveOccurrence(effectiveMinOccurs, effectiveMaxOccurs, xsdParticle, target);
      result.add(effectiveOccurrence);
    }
  }

  protected void resolveNameConflicts()
  {
    for (Iterator i = targetNamespaceToEPackageMap.values().iterator(); i.hasNext(); )
    {
      EPackage ePackage = (EPackage)i.next();
      Map eClassifierMap = new HashMap();
      Map xmlNameMap = new HashMap();
      for (Iterator j = ePackage.getEClassifiers().iterator(); j.hasNext(); )
      {
        EClassifier eClassifier = (EClassifier)j.next();
        EClassifier otherEClassifier = (EClassifier)eClassifierMap.get(eClassifier.getName().toLowerCase());
        if (otherEClassifier != null)
        {
          resolveNameConflict(eClassifierMap, eClassifier, "");
        }
        eClassifierMap.put(eClassifier.getName().toLowerCase(), eClassifier);

        String xmlName = extendedMetaData.getName(eClassifier);
        otherEClassifier = extendedMetaData.getType(ePackage, xmlName);
        if (otherEClassifier != eClassifier)
        {
          if (xmlName.endsWith("_._type"))
          {
            String baseName = xmlName.substring(0, xmlName.length() - 7);
            int index = 1;
            while (extendedMetaData.getType(ePackage, baseName + "_._" + index + "_._type") != null)
            {
              ++index;
            }
            extendedMetaData.setName(eClassifier, baseName + "_._" + index + "_._type");
          }
        }

        if (eClassifier instanceof EClass)
        {
          Map eFeatureMap = new HashMap();
          for (Iterator k = ((EClass)eClassifier).getEAllStructuralFeatures().iterator(); k.hasNext(); )
          {
            EStructuralFeature eStructuralFeature = (EStructuralFeature)k.next();
            resolveNameConflict(eFeatureMap, eStructuralFeature, "");
            eFeatureMap.put(eStructuralFeature.getName(), eStructuralFeature);
          }
        }
      }
    }
  }

  protected void resolveNameConflict(Map map, ENamedElement eNamedElement, String suffix)
  {
    String name = eNamedElement.getName();
    if (!name.endsWith(suffix))
    {
      name += suffix;
    }
    boolean isCaseSensitive = !(eNamedElement instanceof EClassifier);
    if (map.containsKey(isCaseSensitive ? name : name.toLowerCase()))
    {
      int index = 0;
      while (map.containsKey((isCaseSensitive ? name : name.toLowerCase()) + ++index))
      {
      }
      eNamedElement.setName(name + index);
    }
    else
    {
      eNamedElement.setName(name);
    }
  }

  protected ResourceSet createResourceSet()
  {
    ResourceSet result = new ResourceSetImpl();
    result.getLoadOptions().put(XSDResourceImpl.XSD_TRACK_LOCATION, Boolean.TRUE);
    result.getResourceFactoryRegistry().getExtensionToFactoryMap().put("wsdl", new XSDResourceFactoryImpl());
    return result;
  }

  public Collection generateResources(URI uri)
  {
    return generateResources(Collections.singleton(uri));
  }

  public Collection generateResources(Collection uris)
  {
    ResourceSet resourceSet = createResourceSet();
    for (Iterator i = uris.iterator(); i.hasNext(); )
    {
      URI uri = (URI)i.next();
      Resource resource = resourceSet.getResource(uri, true);
      if (!resource.getContents().isEmpty() && resource.getContents().get(0) instanceof XSDSchema)
      {
        generate((XSDSchema)resource.getContents().get(0));
      }
    }

    for (Iterator i = targetNamespaceToEPackageMap.values().iterator(); i.hasNext(); )
    {
      EPackage ePackage = (EPackage)i.next();
      if (ePackage.eResource() == null)
      {
        Resource ecoreResource = resourceSet.createResource(URI.createURI("*.ecore"));
        ecoreResource.setURI(URI.createURI(ePackage.getNsURI()));
        ecoreResource.getContents().add(ePackage);
      }
    }

    return resourceSet.getResources();
  }

  public EStructuralFeature getEStructuralFeature(XSDFeature xsdFeature)
  {
    EStructuralFeature eStructuralFeature = (EStructuralFeature)xsdComponentToEModelElementMap.get(xsdFeature);
    if (eStructuralFeature == null)
    {
      EPackage ePackage = getEPackage(xsdFeature);
      EClass documentEClass = extendedMetaData.getDocumentRoot(ePackage);
      if (documentEClass == null)
      {
        // documentEClass = extendedMetaData.demandDocumentRoot(ePackage);
        documentEClass = EcoreFactory.eINSTANCE.createEClass();
        String name = getEcoreAttribute(xsdFeature.getSchema(), "documentRoot");
        if (name == null)
        {
          name = "DocumentRoot";
        }
        documentEClass.setName(name);

        extendedMetaData.setDocumentRoot(documentEClass);

        ePackage.getEClassifiers().add(documentEClass);

        EStructuralFeature mixedFeature =
          createFeature
            (documentEClass,
             "mixed",
             EcorePackage.eINSTANCE.getEFeatureMapEntry(),
             null,
             0,
             -1);

        EStructuralFeature xmlnsPrefixMapFeature =
          createFeature
            (documentEClass,
             "xMLNSPrefixMap",
             EcorePackage.eINSTANCE.getEStringToStringMapEntry(),
             null,
             0,
             -1);
        extendedMetaData.setName(xmlnsPrefixMapFeature, "xmlns:prefix");

        EStructuralFeature xsiSchemaLocationMapFeature =
          createFeature
            (documentEClass,
             "xSISchemaLocation",
             EcorePackage.eINSTANCE.getEStringToStringMapEntry(),
             null,
             0,
             -1);
        extendedMetaData.setName(xsiSchemaLocationMapFeature, "xsi:schemaLocation");
      }

      eStructuralFeature =
        createFeature
          (documentEClass,
           validName(xsdFeature.getName(), false),
           getEClassifier(xsdFeature.getType()),
           xsdFeature);

      if (xsdFeature instanceof XSDElementDeclaration)
      {
        eStructuralFeature.setDerived(true);
        eStructuralFeature.setTransient(true);
        eStructuralFeature.setVolatile(true);

        // Mark the bound as unspecified so that it won't be considered many 
        // but can nevertheless be recognized as being unspecified and perhaps still be treat as many.
        //
        eStructuralFeature.setUpperBound(ETypedElement.UNSPECIFIED_MULTIPLICITY);
      }
    }

    return eStructuralFeature;
  }

  public Collection generate(URI uri)
  {
    ResourceSet resourceSet = createResourceSet();
    Resource resource = resourceSet.getResource(uri, true);
    if (!resource.getContents().isEmpty() && resource.getContents().get(0) instanceof XSDSchema)
    {
      generate((XSDSchema)resource.getContents().get(0));
    }

    List result = new ArrayList(targetNamespaceToEPackageMap.values());
    result.remove(XMLNamespacePackage.eINSTANCE);
    if (mapper != null)
    {
      result.add(mapper.getRoot());
    }
    return result;
  }

  public Collection generate(Collection uris)
  {
    if (simpleDiagnostics == null)
    {
      simpleDiagnostics = new ArrayList();
    }
    ResourceSet resourceSet = createResourceSet();
    for (Iterator i = uris.iterator(); i.hasNext(); )
    {
      Resource resource = resourceSet.getResource((URI)i.next(), true);
      for (Iterator j = resource.getContents().iterator(); j.hasNext(); )
      {
        Object object = j.next();
        if (object instanceof XSDSchema)
        {
          generate((XSDSchema)object);
        }
      }
    }

    List result = new ArrayList(targetNamespaceToEPackageMap.values());
    result.remove(XMLNamespacePackage.eINSTANCE);
    if (mapper != null)
    {
      result.add(mapper.getRoot());
    }

    result.add(simpleDiagnostics);
    return result;
  }

  public void generate(XSDSchema xsdSchema)
  {
    this.rootSchema = xsdSchema;
    if (xsdSchemas.add(xsdSchema))
    {
      addInput(xsdSchema);
      validate(xsdSchema);
    }

    // Make sure to populate the mapping for the special XML namespace schema if it isn't the one we are trying to map.
    //
    if (xsdSchemas.size() == 1 && !XMLNamespacePackage.eNS_URI.equals(xsdSchema.getTargetNamespace()))
    {
      XSDAttributeDeclaration xmlLangAttribute = xsdSchema.resolveAttributeDeclaration(XMLNamespacePackage.eNS_URI, "lang");
      if (xmlLangAttribute.getContainer() != null)
      {
        getEPackage(xmlLangAttribute);
      }
    }

    Collection visitedElementDeclarations = new ArrayList();
    Collection elementDeclarations = new ArrayList(xsdSchema.getElementDeclarations());

    Collection visitedAttributeDeclarations = new ArrayList();
    Collection attributeDeclarations = new ArrayList(xsdSchema.getAttributeDeclarations());

    Collection visitedTypeDefinitions = new ArrayList();
    Collection typeDefinitions = new ArrayList(xsdSchema.getTypeDefinitions());

    while (!elementDeclarations.isEmpty() || !attributeDeclarations.isEmpty() || !typeDefinitions.isEmpty())
    {
      for (Iterator i = elementDeclarations.iterator(); i.hasNext(); )
      {
        XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration)i.next();
        getEStructuralFeature(xsdElementDeclaration);
      }
      visitedElementDeclarations.addAll(elementDeclarations);
      elementDeclarations = new ArrayList(xsdSchema.getElementDeclarations());
      elementDeclarations.removeAll(visitedElementDeclarations);

      for (Iterator i = attributeDeclarations.iterator(); i.hasNext(); )
      {
        XSDAttributeDeclaration xsdAttributeDeclaration = (XSDAttributeDeclaration)i.next();
        if (!XSDConstants.isSchemaInstanceNamespace(xsdAttributeDeclaration.getTargetNamespace()))
        {
          getEStructuralFeature(xsdAttributeDeclaration);
        }
      }
      visitedAttributeDeclarations.addAll(attributeDeclarations);
      attributeDeclarations = new ArrayList(xsdSchema.getAttributeDeclarations());
      attributeDeclarations.removeAll(visitedAttributeDeclarations);

      for (Iterator i = typeDefinitions.iterator(); i.hasNext(); )
      {
        XSDTypeDefinition xsdTypeDefinition = (XSDTypeDefinition)i.next();
        EClassifier eClassifier = getEClassifier(xsdTypeDefinition);
      }
      visitedTypeDefinitions.addAll(typeDefinitions);
      typeDefinitions = new ArrayList(xsdSchema.getTypeDefinitions());
      typeDefinitions.removeAll(visitedTypeDefinitions);
    }

    resolveNameConflicts();

    for (Iterator i = xsdSchemas.iterator(); i.hasNext(); )
    {
      XSDSchema generatedXSDSchema = (XSDSchema)i.next();
      EPackage ePackage = (EPackage)targetNamespaceToEPackageMap.get(generatedXSDSchema.getTargetNamespace());
      if (ePackage != null)
      {
        String packageName= getEcoreAttribute(generatedXSDSchema, "package");
        if (packageName != null)
        {
          ePackage.setName(packageName);
        }
        String packageNsPrefix= getEcoreAttribute(generatedXSDSchema, "nsPrefix");
        if (packageNsPrefix != null)
        {
          ePackage.setNsPrefix(packageNsPrefix);
        }
      }
    }

    for (Iterator i = eReferenceToOppositeNameMap.entrySet().iterator(); i.hasNext(); )
    {
      Map.Entry entry = (Map.Entry)i.next();
      EReference eReference = (EReference)entry.getKey();
      String opposite = (String)entry.getValue();
      EClass oppositeEClass = eReference.getEReferenceType();
      if (eReference.isContainment())
      {
        EReference eOpposite = EcoreFactory.eINSTANCE.createEReference();
        eOpposite.setName(opposite);
        eOpposite.setEType(eReference.getEContainingClass());
        eOpposite.setLowerBound(0);
        eOpposite.setEOpposite(eReference);
        eReference.setEOpposite(eOpposite);
        eOpposite.setTransient(true);
        oppositeEClass.getEStructuralFeatures().add(eOpposite);
      }
      else if (eReference.getEOpposite() == null)
      {
        EStructuralFeature eOppositeFeature =  oppositeEClass.getEStructuralFeature(opposite);
        if (eOppositeFeature instanceof EReference)
        {
          EReference eOpposite = (EReference)eOppositeFeature;
          eOpposite.setEOpposite(eReference);
          eReference.setEOpposite(eOpposite);
        }
      }
    }

    eReferenceToOppositeNameMap.clear();
  }

  protected String validName(String name, boolean isUpperCase)
  {
    List parsedName = parseName(name, '_');
    StringBuffer result = new StringBuffer();
    for (Iterator i = parsedName.iterator(); i.hasNext(); )
    {
      String nameComponent = (String)i.next();
      if (nameComponent.length() > 0)
      {
        if (result.length() > 0 || isUpperCase)
        {
          result.append(Character.toUpperCase(nameComponent.charAt(0)));
          result.append(nameComponent.substring(1));
        }
        else
        {
          result.append(nameComponent);
        }
      }
    }

    return
      result.length() == 0 ?
        "_" :
        Character.isJavaIdentifierStart(result.charAt(0)) ?
          isUpperCase ?
            result.toString() :
            uncapName(result.toString()) :
          "_" + result;
  }

  protected List parseName(String sourceName, char separator)
  {
    List result = new ArrayList();
    if (sourceName != null)
    {
      StringBuffer currentWord = new StringBuffer();
      boolean lastIsLower = false;
      for (int index = 0, length = sourceName.length(); index < length; ++index)
      {
        char curChar = sourceName.charAt(index);
        if (!Character.isJavaIdentifierPart(curChar))
        {
          curChar = separator;
        }
        if (Character.isUpperCase(curChar) || (!lastIsLower && Character.isDigit(curChar)) || curChar == separator)
        {
          if (lastIsLower && currentWord.length() > 1 || curChar == separator && currentWord.length() > 0)
          {
            result.add(currentWord.toString());
            currentWord = new StringBuffer();
          }
          lastIsLower = false;
        }
        else
        {
          if (!lastIsLower)
          {
            int currentWordLength = currentWord.length();
            if (currentWordLength > 1)
            {
              char lastChar = currentWord.charAt(--currentWordLength);
              currentWord.setLength(currentWordLength);
              result.add(currentWord.toString());
              currentWord = new StringBuffer();
              currentWord.append(lastChar);
            }
          }
          lastIsLower = true;
        }

        if (curChar != separator)
        {
          currentWord.append(curChar);
        }
      }

      result.add(currentWord.toString());
    }
    return result;
  }

  public String uncapName(String name)
  {
    if (name.length() == 0)
    {
      return name;
    }
    else
    {
      String lowerName = name.toLowerCase();
      int i;
      for (i = 0; i < name.length(); i++)
      {
        if (name.charAt(i) == lowerName.charAt(i))
        {
          break;
        }
      }
      if (i > 1 && i < name.length())
      {
        --i;
      }
      return name.substring(0, i).toLowerCase() + name.substring(i);
    }
  }

  protected String getEcoreAttribute(XSDConcreteComponent xsdConcreteComponent, String attribute)
  {
    if (xsdConcreteComponent == null)
    {
      return null;
    }
    else
    {
      Element element = xsdConcreteComponent.getElement();
      return getEcoreAttribute(element, attribute);
    }
  }

  protected String getEcoreAttribute(Element element, String attribute)
  {
    return 
      element != null && element.hasAttributeNS(EcorePackage.eNS_URI, attribute) ?
        element.getAttributeNS(EcorePackage.eNS_URI, attribute) :
        null;
  }

  protected XSDTypeDefinition getEcoreTypeQNameAttribute(XSDConcreteComponent xsdConcreteComponent, String attribute)
  {
    Element element = xsdConcreteComponent.getElement();
    if (element != null && element.hasAttributeNS(EcorePackage.eNS_URI, attribute))
    {
      String qName = element.getAttributeNS(EcorePackage.eNS_URI, attribute);
      XSDTypeDefinition result = xsdConcreteComponent.resolveTypeDefinitionURI(XSDConstants.lookupQName(element, qName));
      if (result.getContainer() != null)
      {
        return result;
      }
    }

    return null;
  }

  public static List sortNamedComponents(Collection eNamedElements)
  {
    Object [] objects = eNamedElements.toArray();
    Arrays.sort(objects, Comparator.INSTANCE);
    return Arrays.asList(objects);
  }

  public static void addToSortedList(List eNamedElements, ENamedElement eNamedElement)
  {
    int index = Collections.binarySearch(eNamedElements, eNamedElement, Comparator.INSTANCE);
    if (index < 0)
    {
      eNamedElements.add(-(index + 1), eNamedElement);
    }
    else if (eNamedElements.get(index) != eNamedElement)
    {
      eNamedElements.add(index, eNamedElement);
    }
  }

  public static class Comparator implements java.util.Comparator
  {
    public static Comparator INSTANCE = new Comparator();

    protected Collator collator = Collator.getInstance();
  
    public Comparator()
    {
    }
  
    public boolean equals(Object that)
    {
      return this == that;
    }
  
    public int compare(Object o1, Object o2)
    {
      ENamedElement eNamedElement1 = (ENamedElement)o1;
      ENamedElement eNamedElement2 = (ENamedElement)o2;
      String name1 = eNamedElement1.getName();
      String name2 = eNamedElement2.getName();
      if (name1 == null && name2 == null)
      {
        return 0;
      }
      else if (name1 == null)
      {
        return 1;
      }
      else if (name2 == null)
      {
        return -1;
      }
      else
      {
        int result = collator.compare(name1, name2);
/*
        if (result == 0)
        {
          String namespace1 = eNamedElement1.getTargetNamespace();
          String namespace2 = eNamedElement2.getTargetNamespace();
          if (namespace1 == null && namespace2 == null)
          {
            return 0;
          }
          else if (namespace1 == null)
          {
            return 1;
          }
          else if (namespace2 == null)
          {
            return -1;
          }
          else
          {
            return collator.compare(namespace1, namespace2);
          }
        }
        else
*/
        {
          return result;
        }
      }
    }
  }

  public static boolean isLocalReferenceType(XSDSimpleTypeDefinition xsdSimpleTypeDefinition)
  {
    while (xsdSimpleTypeDefinition != null)
    {
      if (XSDConstants.isAnySimpleType(xsdSimpleTypeDefinition))
      {
        return false;
      }

      String name = xsdSimpleTypeDefinition.getName();
      if (XSDConstants.isSchemaForSchemaNamespace(xsdSimpleTypeDefinition.getTargetNamespace()) &&
           ("IDREF".equals(name) || "IDREFS".equals(name)))
      {
        return true;
      }

      xsdSimpleTypeDefinition = xsdSimpleTypeDefinition.getBaseTypeDefinition();
    }

    return false;
  }

  protected void setAnnotations(EModelElement eModelElement, XSDConcreteComponent xsdComponent)
  {
    List xsdAnnotations = new ArrayList();

    if (xsdComponent instanceof XSDParticle)
    {
      xsdComponent = ((XSDParticle)xsdComponent).getContent();
    }

    if (xsdComponent instanceof XSDAttributeDeclaration)
    {
      xsdAnnotations.add(((XSDAttributeDeclaration)xsdComponent).getAnnotation());
    }
    else if (xsdComponent instanceof XSDAttributeUse)
    {
      xsdAnnotations.add(((XSDAttributeUse)xsdComponent).getContent().getAnnotation());
    }
    else if (xsdComponent instanceof XSDElementDeclaration)
    {
      xsdAnnotations.add(((XSDElementDeclaration)xsdComponent).getAnnotation());
    }
    else if (xsdComponent instanceof XSDAttributeGroupDefinition)
    {
      xsdAnnotations.add(((XSDAttributeGroupDefinition)xsdComponent).getAnnotation());
    }
    else if (xsdComponent instanceof XSDElementDeclaration)
    {
      xsdAnnotations.add(((XSDElementDeclaration)xsdComponent).getAnnotation());
    }
    else if (xsdComponent instanceof XSDFacet)
    {
      xsdAnnotations.add(((XSDFacet)xsdComponent).getAnnotation());
    }
    else if (xsdComponent instanceof XSDIdentityConstraintDefinition)
    {
      xsdAnnotations.add(((XSDIdentityConstraintDefinition)xsdComponent).getAnnotation());
    }
    else if (xsdComponent instanceof XSDModelGroup)
    {
      xsdAnnotations.add(((XSDModelGroup)xsdComponent).getAnnotation());
    }
    else if (xsdComponent instanceof XSDModelGroupDefinition)
    {
      xsdAnnotations.add(((XSDModelGroupDefinition)xsdComponent).getAnnotation());
    }
    else if (xsdComponent instanceof XSDSchema)
    {
      xsdAnnotations.addAll(((XSDSchema)xsdComponent).getAnnotations());
    }
    else if (xsdComponent instanceof XSDTypeDefinition)
    {
      xsdAnnotations.addAll(((XSDTypeDefinition)xsdComponent).getAnnotations());
    }
    else if (xsdComponent instanceof XSDWildcard)
    {
      xsdAnnotations.add(((XSDWildcard)xsdComponent).getAnnotation());
    }

    for (Iterator i = xsdAnnotations.iterator(); i.hasNext(); )
    {
      XSDAnnotation xsdAnnotation = (XSDAnnotation)i.next();
      if (xsdAnnotation != null && !"true".equals(getEcoreAttribute(xsdAnnotation, "ignore")))
      {
        for (Iterator j = xsdAnnotation.getUserInformation().iterator(); j.hasNext(); )
        {
          Element element = (Element)j.next();
          if (element.getFirstChild() != null && !"true".equals(getEcoreAttribute(element, "ignore")))
          {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            XSDResourceImpl.serialize(byteArrayOutputStream, element, "UTF-8");
            try
            {
              String documentation = byteArrayOutputStream.toString("UTF8");
              int start = documentation.indexOf("?>");
              start = documentation.indexOf(">", start + 2);
              int end = documentation.lastIndexOf("</");
              String documentationBody = documentation.substring(start + 1, end);
              String existingDocumentation =  EcoreUtil.getDocumentation(eModelElement);
              if (existingDocumentation != null)
              {
                documentationBody = existingDocumentation + System.getProperty("line.separator") + documentationBody;
              }
              EcoreUtil.setDocumentation(eModelElement, documentationBody);
            }
            catch (UnsupportedEncodingException exception)
            {
              throw new WrappedException(exception);
            }
          }
        }

        for (Iterator j = xsdAnnotation.getApplicationInformation().iterator(); j.hasNext(); )
        {
          Element element = (Element)j.next();
          if (element.getFirstChild() != null && !"true".equals(getEcoreAttribute(element, "ignore")))
          {
            String sourceURI = element.hasAttributeNS(null, "source") ? element.getAttributeNS(null, "source") : null;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            XSDResourceImpl.serialize(byteArrayOutputStream, element, "UTF-8");
            try
            {
              String applicationInformation = byteArrayOutputStream.toString("UTF8");
              int start = applicationInformation.indexOf("?>");
              start = applicationInformation.indexOf(">", start + 2);
              int end = applicationInformation.lastIndexOf("</");
              String applicationInformationBody = applicationInformation.substring(start + 1, end);

              EAnnotation eAnnotation = eModelElement.getEAnnotation(sourceURI);
              String existingApplicationInformation =
                eAnnotation == null ?
                  null :
                  (String)eAnnotation.getDetails().get("appinfo");

              if (existingApplicationInformation != null)
              {
                applicationInformationBody = 
                  existingApplicationInformation + System.getProperty("line.separator") + applicationInformationBody;
              }

              if (eAnnotation == null)
              {
                eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
                eAnnotation.setSource(sourceURI);
                eModelElement.getEAnnotations().add(eAnnotation);
              }

              eAnnotation.getDetails().put("appinfo", applicationInformationBody);
            }
            catch (UnsupportedEncodingException exception)
            {
              throw new WrappedException(exception);
            }
          }
        }
      }
    }
  }

  protected void validate(XSDSchema xsdSchema)
  {
    for (Iterator i = xsdSchema.getContents().iterator(); i.hasNext(); )
    {
      Object content = i.next();
      if (content instanceof XSDImport)
      {
        XSDImport xsdImport = (XSDImport)content;
        xsdImport.resolveTypeDefinition(xsdImport.getNamespace(), "");
      }
    }

    if (simpleDiagnostics != null || diagnostics != null)
    {
      xsdSchema.validate();
      if (!xsdSchema.getAllDiagnostics().isEmpty())
      {
        if (simpleDiagnostics != null)
        {
          for (Iterator i = xsdSchema.getAllDiagnostics().iterator(); i.hasNext(); )
          {
            XSDDiagnostic xsdDiagnostic = (XSDDiagnostic)i.next();

            List tuple = new ArrayList();
            tuple.add(xsdDiagnostic.getSeverity().toString());

            String localizedSeverity = XSDPlugin.INSTANCE.getString("_UI_XSDDiagnosticSeverity_" + xsdDiagnostic.getSeverity());
            tuple.add
              (XSDPlugin.INSTANCE.getString
                ("_UI_DiagnosticFileLineColumn_message",
                 new Object []
                 {
                   localizedSeverity + ": " + xsdDiagnostic.getMessage() + " ",
                   xsdDiagnostic.getLocationURI(),
                   new Integer(xsdDiagnostic.getLine()),
                   new Integer(xsdDiagnostic.getColumn())
                 }));

            simpleDiagnostics.add(tuple); 
          }
        }
        if (diagnostics != null)
        {
          diagnostics.addAll(xsdSchema.getAllDiagnostics());
        }
      }
    }
  }
}
