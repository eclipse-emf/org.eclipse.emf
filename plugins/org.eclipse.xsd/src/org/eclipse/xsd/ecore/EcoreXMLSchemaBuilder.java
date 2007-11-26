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
 * $Id: EcoreXMLSchemaBuilder.java,v 1.13 2007/11/26 15:38:57 emerks Exp $
 */
package org.eclipse.xsd.ecore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

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
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;
import org.eclipse.xsd.util.XSDParser;


/**
 * This class produces an XSDSchema given an Ecore EPackage.
 * The XSDSchema is an XML schema.
 */
public class EcoreXMLSchemaBuilder extends MapBuilder
{
  public interface QNameMap
  {
    String getName(ENamedElement element);
  }
  public static final String OPTION_TO_ENFORCE_LOWERBOUND = "ENFORCE_LOWERBOUND";
  public static final String OPTION_USE_ENCODED_ATTRIBUTE_STYLE = "USE_ENCODE_ATTRIBUTE_STYLE";

  protected static final String EMF_SCHEMA_URI = "http://org.eclipse.emf/xsd";
  protected static final String EMF_SCHEMA_PREFIX = "emf";
  protected static final String EMF_SCHEMA_NAME = "EMF.xsd";
  protected static final String REFERENCE_TYPE_NAME = "string";

  protected XSDSchema xsdSchema;
  protected XSDSchema emfSchema;
  protected EPackage ePackage;
  protected Map<String, String> ecoreToSchemaName;
  protected Map<EPackage, XSDSchema> ePackageToXSDSchemaMap = new HashMap<EPackage, XSDSchema>();
  protected Map<EPackage, String> ePackageToNsPrefixMap = new HashMap<EPackage, String>();
  protected QNameMap qNameMap;

  protected boolean minimizedXMI;
  protected boolean useEncodedAttributeStyle;
  protected boolean enforceLowerBound;
  protected List<EClass> rootClasses;
  protected XSDParser xsdParser;

  public EcoreXMLSchemaBuilder()
  {
    // Make sure the xsd prefix isn't used by a package.
    //
    ePackageToNsPrefixMap.put(null, "xsd");
  }

  public Collection<EObject> generate(EPackage ePackage)
  {
    return generate(ePackage, null);
  }

  public Collection<EObject> generate(EPackage ePackage, QNameMap qNameMap)
  {
    this.qNameMap = qNameMap;

    addInput(ePackage);

    this.ePackage = ePackage;
    createSchema();
    processEnums();
    processClasses();
    Collection<EObject> result = new ArrayList<EObject>();
    result.add(xsdSchema);
    if (emfSchema != null)
    {
      result.add(emfSchema);
    }
    if (mapper != null)
    {
      result.add(mapper.getRoot());
    }

    // Add model documentation.
    //
    for (Map.Entry<XSDComponent, EModelElement> entry : xsdComponentToEModelElementMap.entrySet())
    {
      addDocumentation(entry.getKey(), entry.getValue());
    }

    return result;
  }

  protected String getUniqueNsPrefix(EPackage ePackage)
  {
    if (ePackageToNsPrefixMap.containsKey(ePackage))
    {
      return ePackageToNsPrefixMap.get(ePackage);
    }
    else
    {
      String nsPrefix = ePackage.getNsPrefix();
      String uniqueNsPrefix = nsPrefix;
      for (int i = 1; ePackageToNsPrefixMap.values().contains(uniqueNsPrefix); ++i)
      {
        uniqueNsPrefix = nsPrefix + "_" + i;
      }
      ePackageToNsPrefixMap.put(ePackage, uniqueNsPrefix);
      return uniqueNsPrefix;
    }
  }

  protected void createSchema()
  {
    xsdSchema = XSDFactory.eINSTANCE.createXSDSchema();
    addOutput(xsdSchema);
    // addInput(xsdSchema);

    xsdSchema.setTargetNamespace(ePackage.getNsURI());
    xsdSchema.setSchemaForSchemaQNamePrefix("xsd");

    Map<String, String> namespaces = xsdSchema.getQNamePrefixToNamespaceMap();
    namespaces.put(getUniqueNsPrefix(ePackage), xsdSchema.getTargetNamespace());
    namespaces.put(xsdSchema.getSchemaForSchemaQNamePrefix(), XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);

    map(xsdSchema, ePackage);
  }

  protected void createEMFSchema()
  {
    emfSchema = XSDFactory.eINSTANCE.createXSDSchema();

    emfSchema.setTargetNamespace(EMF_SCHEMA_URI);
    emfSchema.setSchemaForSchemaQNamePrefix("xsd");

    Map<String, String> namespaces = emfSchema.getQNamePrefixToNamespaceMap();
    namespaces.put(EMF_SCHEMA_PREFIX, emfSchema.getTargetNamespace());
    namespaces.put(emfSchema.getSchemaForSchemaQNamePrefix(), XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);

    XSDSimpleTypeDefinition list = XSDFactory.eINSTANCE.createXSDSimpleTypeDefinition();
    list.setName(REFERENCE_TYPE_NAME);

    XSDSimpleTypeDefinition union = XSDFactory.eINSTANCE.createXSDSimpleTypeDefinition();
    union.getMemberTypeDefinitions().add(emfSchema.getSchemaForSchema().resolveSimpleTypeDefinition("IDREF"));
    union.getMemberTypeDefinitions().add(emfSchema.getSchemaForSchema().resolveSimpleTypeDefinition("QName"));
    union.getMemberTypeDefinitions().add(emfSchema.getSchemaForSchema().resolveSimpleTypeDefinition("anyURI"));

    list.setItemTypeDefinition(union);
    list.getContents().add(union);

    emfSchema.getContents().add(list);
  }

  protected void addEMFSchema()
  {
    // Do nothing.
  }

  protected XSDTypeDefinition typeInOtherSchema(EClassifier classifier)
  {
    EPackage typePkg = classifier.getEPackage();
    Map<String, String> namespaces = xsdSchema.getQNamePrefixToNamespaceMap();

    String nsPrefix = getUniqueNsPrefix(typePkg);
    if (namespaces.get(nsPrefix) == null)
    {
      namespaces.put(nsPrefix, typePkg.getNsURI());
      addImport(typePkg.getNsURI(), getName(typePkg) + ".xsd");
      createOtherSchema(typePkg);
    }

    XSDSchema otherXSDSchema = ePackageToXSDSchemaMap.get(typePkg);
    return otherXSDSchema.resolveTypeDefinition(getName(classifier));
  }

  protected void addImport(String namespace, String schemaLocation)
  {
    XSDImport xsdImport = XSDFactory.eINSTANCE.createXSDImport();
    xsdImport.setNamespace(namespace);
    xsdImport.setSchemaLocation(schemaLocation);
    xsdSchema.getContents().add(0, xsdImport);
  }

  protected void createOtherSchema(EPackage ePackage)
  {
    XSDSchema otherSchema = XSDFactory.eINSTANCE.createXSDSchema();

    otherSchema.setTargetNamespace(ePackage.getNsURI());
    otherSchema.setSchemaForSchemaQNamePrefix("xsd");

    Map<String, String> namespaces = otherSchema.getQNamePrefixToNamespaceMap();
    namespaces.put(getUniqueNsPrefix(ePackage), otherSchema.getTargetNamespace());
    namespaces.put(otherSchema.getSchemaForSchemaQNamePrefix(), XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);

    ePackageToXSDSchemaMap.put(ePackage, otherSchema);

    // map(otherSchema, ePackage);
  }

  protected void processClasses()
  {
    for (EClassifier classifier : ePackage.getEClassifiers())
    {
      if (classifier instanceof EClass)
      {
        processClass((EClass)classifier);
      }
    }
  }

  protected void processEnums()
  {
    for (EClassifier classifier : ePackage.getEClassifiers())
    {
      if (classifier instanceof EEnum)
      {
        processEnum((EEnum)classifier);
      }
    }
  }

  /**
   * Return the given name, or the name from the XMLMap, if there is one.
   */
  protected String getName(ENamedElement element)
  {
    if (qNameMap == null)
    {
      return element.getName();
    }
    else
    {
      return qNameMap.getName(element);
    }
  }

  /**
   * Each EEnum is mapped to a schema simple type that restricts the string simple type;
   * the schema type has an enumeration facet for each EEnumLiteral.
   */
  protected void processEnum(EEnum eEnum)
  {
    XSDSimpleTypeDefinition enumType = XSDFactory.eINSTANCE.createXSDSimpleTypeDefinition();
    enumType.setName(getName(eEnum));
    enumType.setBaseTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("NCName"));
    xsdSchema.getContents().add(enumType);

    map(enumType, eEnum);

    for (EEnumLiteral literal : eEnum.getELiterals())
    {
      XSDEnumerationFacet facet = XSDFactory.eINSTANCE.createXSDEnumerationFacet();
      facet.setLexicalValue(getName(literal));
      enumType.getFacetContents().add(facet);

      map(facet, literal);
    }
  }

  /**
   * Map each EClass to a schema complex type and an element declaration of that type;
   * the names of the complex type and element declaration are the name of the class.
   * If the class is abstract, only a complex type is created from it; there is no
   * corresponding element declaration.
   */
  protected void processClass(EClass eClass)
  {
    XSDComplexTypeDefinition xsdComplexTypeDefinition = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
    xsdComplexTypeDefinition.setName(getName(eClass));
    if (eClass.isAbstract())
    {
      xsdComplexTypeDefinition.setAbstract(true);
    }
    List<EClass> superClasses = eClass.getESuperTypes();

    if (superClasses.size() > 0)
    {
      xsdComplexTypeDefinition.setDerivationMethod(XSDDerivationMethod.EXTENSION_LITERAL);
      EClass superClass = superClasses.get(0);

      if (superClass.getEPackage() == ePackage)
      {
        xsdComplexTypeDefinition.setBaseTypeDefinition(xsdSchema.resolveTypeDefinition(getName(superClass)));
      }
      else
      {
        xsdComplexTypeDefinition.setBaseTypeDefinition(typeInOtherSchema(superClass));
      }
    }

    xsdSchema.getContents().add(xsdComplexTypeDefinition);
    map(xsdComplexTypeDefinition, eClass);

    List<EStructuralFeature> features = getFeatures(eClass, superClasses);
    for (EStructuralFeature f : features)
    {
      if (f instanceof EAttribute)
      {
        processAttribute((EAttribute)f, xsdComplexTypeDefinition);
      }
      else if (f instanceof EReference)
      {
        processReference((EReference)f, xsdComplexTypeDefinition);
      }
    }

    if (makeClassElementDeclaration(eClass))
    {
      XSDElementDeclaration xsdElementDeclaration = XSDFactory.eINSTANCE.createXSDElementDeclaration();
      xsdElementDeclaration.setName(getName(eClass));
      xsdElementDeclaration.setTypeDefinition(xsdComplexTypeDefinition);
      xsdSchema.getContents().add(xsdElementDeclaration);

      map(xsdElementDeclaration, eClass);
    }

    additionalProcessing(eClass, xsdComplexTypeDefinition);
  }

  protected boolean makeClassElementDeclaration(EClass eClass)
  {
    return !eClass.isAbstract();
  }

  protected void additionalProcessing(EClass cls, XSDComplexTypeDefinition xsdComplexTypeDefinition)
  {
    // Do nothing
  }

  /**
   * Return the inherited features from the super classes (except the first one, which
   * is the base type for the generated schema type for the class), and the local
   * features.
   */
  protected List<EStructuralFeature> getFeatures(EClass eClass, List<EClass> superClasses)
  {
    List<EStructuralFeature> features = new ArrayList<EStructuralFeature>();

    if (superClasses.size() > 0)
    {
      List<EClass> allSupers = superClasses.get(0).getEAllSuperTypes();
      Set<EClass> allSuperClasses = new HashSet<EClass>();
      allSuperClasses.addAll(allSupers);
      allSuperClasses.add(superClasses.get(0));

      for (int i = 1; i < superClasses.size(); i++)
      {
        EClass superClass = superClasses.get(i);
        if (!allSuperClasses.contains(superClass))
        {
          features.addAll(getAllFeatures(superClass, allSuperClasses));
        }
      }
    }

    features.addAll(eClass.getEAttributes());
    features.addAll(eClass.getEReferences());
    return features;
  }

  /**
   * This method returns the list of features for the given class and all inherited
   * features; ignoring features from classes in the classesToIgnore set.
   */
  protected List<EStructuralFeature> getAllFeatures(EClass eClass, Set<EClass> classesToIgnore)
  {
    List<EStructuralFeature> features = new ArrayList<EStructuralFeature>();
    List<EClass> superClasses = eClass.getESuperTypes();
    
    for (int i = 0; i < superClasses.size(); i++)
    {
      if (!classesToIgnore.contains(superClasses.get(i)))
      {
        features.addAll(getAllFeatures(superClasses.get(i), classesToIgnore));
      }
    }

    features.addAll(eClass.getEAttributes());
    features.addAll(eClass.getEReferences());
    
    // the class has been processed and should be ignored if seen again in the hierarchy
    classesToIgnore.add(eClass);
    return features;
  }

  /**
   * Map each EAttribute to an XML attribute declaration in the complex type
   * corresponding to the class, or an XML element declaration, depending on
   * the multiplicity of the EAttribute.
   * <p>
   * The types are mapped as follows:
   *    Ecore type             Schema type
   *   ------------           -------------
   *     EBoolean                boolean
   *     EBooleanObject          boolean
   *     EInt                    int
   *     EIntegerObject          int
   *     ELong                   long
   *     ELongObject             long
   *     EFloat                  float
   *     EFloatObject            float
   *     EDouble                 double
   *     EDoubleObject           double
   *     EString                 string
   * <p>
   * If the attribute has no type or if the type is an EDataType that is not
   * an EEnum, the schema string type is used.
   * <p>
   * If the EAttribute is required, the attribute declaration includes
   * use="required".
   * <p>
   * If the EAttribute has a defaultValueLiteral, the attribute declaration
   * has a default attribute whose value is the defaultValueLiteral.
   * <p>
   * If the EAttribute is transient, or its type is not serializable, an
   * attribute declaration is not generated.
   * <p>
   * If the EAttribute can have many values, an element declaration is created
   * rather than an attribute declaration. The lower bound of the attribute is set
   * to the minOccurs value of the element declaration. The upper bound of the
   * attribute is set to the maxOccurs value of the element declaration (converting
   * -1 to unbounded as appropriate). The type of the element is set as above for
   * attribute declarations.
   */
  protected void processAttribute(EAttribute attribute, XSDComplexTypeDefinition xsdComplexTypeDefinition)
  {
    if (processAttribute(attribute))
    {
      if (makeAttributeDeclaration(attribute))
      {
        createAttributeDeclaration(attribute, xsdComplexTypeDefinition);
      }

      if (makeAttributeElementDeclaration(attribute))
      {
        createAttributeElementDeclaration(attribute, xsdComplexTypeDefinition);
      }
    }
  }

  protected void createAttributeDeclaration(EAttribute attribute, XSDComplexTypeDefinition xsdComplexTypeDefinition)
  {
    XSDAttributeDeclaration attrDecl = XSDFactory.eINSTANCE.createXSDAttributeDeclaration();
    attrDecl.setName(getName(attribute));
    setAttributeType(attribute, attrDecl);
    setDefaultValue(attribute, attrDecl);

    XSDAttributeUse attrUse = XSDFactory.eINSTANCE.createXSDAttributeUse();
    setUseToRequired(attribute, attrUse);
    attrUse.setContent(attrDecl);
    xsdComplexTypeDefinition.getAttributeContents().add(attrUse);

    map(attrUse, attribute);
  }

  protected void createAttributeElementDeclaration(EAttribute attribute, XSDComplexTypeDefinition xsdComplexTypeDefinition)
  {
    XSDModelGroup modelGroup = getModelGroup(xsdComplexTypeDefinition);
    XSDElementDeclaration xsdElementDeclaration = XSDFactory.eINSTANCE.createXSDElementDeclaration();
    xsdElementDeclaration.setName(getName(attribute));

    // If the attribute can have a null value (primitives and enums can't), and
    // if the attribute can hold many nulls or
    // it can hold only a single value that is allowed to be null 
    // and it can be considered set when it has the null value...
    //
    if (attribute.getEType().getDefaultValue() == null
      && (attribute.isMany() || (!attribute.isRequired() && (attribute.getDefaultValueLiteral() != null || attribute.isUnsettable()))))
    {
      xsdElementDeclaration.setNillable(true);
    }

    if (xsdElementDeclaration.isNillable() || attribute.isMany() || !minimizedXMI)
    {
      XSDSimpleTypeDefinition attrType = getType(attribute.getEAttributeType());

      if (attrType != null)
      {
        xsdElementDeclaration.setTypeDefinition(attrType);
      }

      XSDParticle particle = XSDFactory.eINSTANCE.createXSDParticle();
      particle.setContent(xsdElementDeclaration);
      setAttributeElementMultiplicity(attribute, particle);
      modelGroup.getContents().add(particle);
      map(particle, attribute);
    }
  }

  protected void setAttributeElementMultiplicity(EAttribute attribute, XSDParticle particle)
  {
    if (enforceLowerBound)
    {
      particle.setMinOccurs(attribute.getLowerBound());
    }
    else
    {
      particle.setMinOccurs(0);
    }

    particle.setMaxOccurs(attribute.getUpperBound());
  }

  protected boolean processAttribute(EAttribute attribute)
  {
    if (attribute.isTransient())
    {
      return false;
    }

    EDataType type = attribute.getEAttributeType();

    if (type != null && !type.isSerializable())
    {
      return false;
    }

    return true;
  }

  protected boolean makeAttributeDeclaration(EAttribute attribute)
  {
    return !attribute.isMany();
  }

  protected boolean makeAttributeElementDeclaration(EAttribute attribute)
  {
    return attribute.isMany();
  }

  /**
   * Returns the model group for the given complex type definition. If there
   * is no model group, a model group is created and added to the complex
   * type definition.
   */
  protected XSDModelGroup getModelGroup(XSDComplexTypeDefinition xsdComplexTypeDefinition)
  {
    if (xsdComplexTypeDefinition.getContent() == null)
    {
      return createModelGroup(xsdComplexTypeDefinition);
    }
    else
    {
      XSDParticle particle = (XSDParticle)xsdComplexTypeDefinition.getContent();
      return (XSDModelGroup)particle.getContent();
    }
  }

  protected XSDModelGroup createModelGroup(XSDComplexTypeDefinition xsdComplexTypeDefinition)
  {
    XSDModelGroup modelGroup = XSDFactory.eINSTANCE.createXSDModelGroup();
    modelGroup.setCompositor(XSDCompositor.SEQUENCE_LITERAL);
    XSDParticle particle = XSDFactory.eINSTANCE.createXSDParticle();
    particle.setContent(modelGroup);
    xsdComplexTypeDefinition.setContent(particle);
    return modelGroup;
  }

  protected void setAttributeType(EAttribute attribute, XSDAttributeDeclaration attrDecl)
  {
    XSDSimpleTypeDefinition attrType = getType(attribute.getEAttributeType());

    if (attrType != null)
    {
      attrDecl.setTypeDefinition(attrType);
    }
  }

  protected void setUseToRequired(EAttribute attribute, XSDAttributeUse attrUse)
  {
    if (attribute.isRequired())  
    {
      attrUse.setUse(XSDAttributeUseCategory.REQUIRED_LITERAL);
    }
  }

  protected void setDefaultValue(EAttribute attribute, XSDAttributeDeclaration attrDecl)
  {
    if (attribute.getDefaultValueLiteral() != null)
    {
      attrDecl.setConstraint(XSDConstraint.DEFAULT_LITERAL);
      attrDecl.setLexicalValue(attribute.getDefaultValueLiteral());
    }
  }

  protected void processReference(EReference reference, XSDComplexTypeDefinition xsdComplexTypeDefinition)
  {
    if (!skipReference(reference))
    {
      if (makeReferenceAttribute(reference))
      {
        makeReferenceAttribute(reference, xsdComplexTypeDefinition);
      }

      if (makeReferenceElement(reference))
      {
        makeReferenceElement(reference, xsdComplexTypeDefinition);
      }
    }
  }

  protected boolean makeReferenceAttribute(EReference reference)
  {
    return !reference.isContainment();
  }

  protected boolean makeReferenceElement(EReference reference)
  {
    return reference.isContainment() && !reference.isContainer();
  }

  protected boolean skipReference(EReference reference)
  {
    return reference.isTransient();
  }

  protected void makeReferenceAttribute(EReference reference, XSDComplexTypeDefinition xsdComplexTypeDefinition)
  {
    XSDAttributeDeclaration attrDecl = XSDFactory.eINSTANCE.createXSDAttributeDeclaration();
    attrDecl.setName(getName(reference));
    setReferenceAttribType(attrDecl);
    XSDAttributeUse attrUse = XSDFactory.eINSTANCE.createXSDAttributeUse();
    attrUse.setContent(attrDecl);
    xsdComplexTypeDefinition.getAttributeContents().add(attrUse);

    map(attrUse, reference);
  }

  protected void setReferenceAttribType(XSDAttributeDeclaration xsdAttributeDeclaration)
  {
    addEMFSchema();
    xsdAttributeDeclaration.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition(REFERENCE_TYPE_NAME));
  }

  protected void makeReferenceElement(EReference reference, XSDComplexTypeDefinition xsdComplexTypeDefinition)
  {
    XSDModelGroup modelGroup = getModelGroup(xsdComplexTypeDefinition);
    XSDElementDeclaration xsdElementDeclaration = XSDFactory.eINSTANCE.createXSDElementDeclaration();
    xsdElementDeclaration.setName(getName(reference));

    if (reference.isUnsettable() && !reference.isMany())
    {
      xsdElementDeclaration.setNillable(true);
    }

    setReferenceElementType(reference, xsdElementDeclaration);

    XSDParticle particle = XSDFactory.eINSTANCE.createXSDParticle();
    particle.setContent(xsdElementDeclaration);

    setReferenceElementMultiplicity(reference, particle);

    modelGroup.getContents().add(particle);
    map(particle, reference);
  }

  protected void setReferenceElementType(EReference reference, XSDElementDeclaration xsdElementDeclaration)
  {
    if (reference.getEType() != null)
    {
      XSDTypeDefinition type;

      if (reference.getEType().getEPackage() == ePackage)
      {
        type = xsdSchema.resolveTypeDefinition(getName(reference.getEType()));
      }
      else
      {
        type = typeInOtherSchema(reference.getEType());
      }

      if (type != null)
      {
        xsdElementDeclaration.setTypeDefinition(type);
      }
    }
  }

  protected void setReferenceElementMultiplicity(EReference reference, XSDParticle particle)
  {
    particle.setMinOccurs(reference.getLowerBound());
    particle.setMaxOccurs(reference.getUpperBound());
  }

  protected XSDSimpleTypeDefinition getType(EDataType dataType)
  {
    if (dataType instanceof EEnum)
    {
      EPackage typePkg = dataType.getEPackage();

      if (typePkg == ePackage)
      {
        return xsdSchema.resolveSimpleTypeDefinition(getName(dataType));
      }
      else
      {
        return (XSDSimpleTypeDefinition)typeInOtherSchema(dataType);
      }
    }

    String name = null;

    if (dataType != null)
    {
      name = getName(dataType);
    }

    return xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition(getSchemaName(name));
  }

  private String getSchemaName(String name)
  {
    if (ecoreToSchemaName == null)
    {
      ecoreToSchemaName = new HashMap<String, String>();
      ecoreToSchemaName.put("EBoolean", "boolean");
      ecoreToSchemaName.put("EBooleanObject", "boolean");
      ecoreToSchemaName.put("EInt", "int");
      ecoreToSchemaName.put("EIntegerObject", "int");
      ecoreToSchemaName.put("ELong", "long");
      ecoreToSchemaName.put("ELongObject", "long");
      ecoreToSchemaName.put("EFloat", "float");
      ecoreToSchemaName.put("EFloatObject", "float");
      ecoreToSchemaName.put("EDouble", "double");
      ecoreToSchemaName.put("EDoubleObject", "double");
      ecoreToSchemaName.put("EString", "string");
    }

    String schemaName = ecoreToSchemaName.get(name);

    if (schemaName != null)
    {
      return schemaName;
    }
    else
    {
      return "string";
    }
  }

  protected void addDocumentation(XSDConcreteComponent xsdComponent, EModelElement eModelElement)
  {
    if (EcoreUtil.isAncestor(ePackage, eModelElement))
    {
      String documentation = EcoreUtil.getDocumentation(eModelElement);
      if (documentation != null)
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

        if (xsdAnnotation != null)
        {
          Element userInformation = xsdAnnotation.createUserInformation(null);

          // Try to parse the documentation and use the parsed version if it's successful.
          //
          if (xsdParser == null)
          {
            xsdParser = new XSDParser(null);
          }
          xsdParser.parseString("<documentation>" + documentation + "</documentation>");
          Document document = xsdParser.getDocument();
          Document xsdDocument = userInformation.getOwnerDocument();
          if (xsdParser.getDiagnostics().isEmpty() && document.getDocumentElement().getFirstChild() != null)
          {
            for (Node node = document.getDocumentElement().getFirstChild(); node != null; node = node.getNextSibling())
            {
              userInformation.appendChild(xsdDocument.importNode(node, true));
            }
          }
          else
          {
            userInformation.appendChild(xsdDocument.createTextNode(documentation));
          }

          xsdAnnotation.getElement().appendChild(userInformation);
        }
      }
    }
  }
}
