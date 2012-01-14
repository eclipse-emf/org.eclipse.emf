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
package org.eclipse.xsd.ecore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeGroupContent;
import org.eclipse.xsd.XSDAttributeGroupDefinition;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDAttributeUseCategory;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDConstraint;
import org.eclipse.xsd.XSDDerivationMethod;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDForm;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDProcessContents;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDWildcard;
import org.eclipse.xsd.util.XSDConstants;

/**
 * This class produces an XSDSchema given an Ecore EPackage.
 * The XSDSchema is an XMI schema.
 */
public class EcoreXMISchemaBuilder extends EcoreXMLSchemaBuilder
{
  protected static final String XMI_PREFIX = "xmi";
  protected static final String XMI_URI = "http://www.omg.org/XMI";
  protected static final String XMI_SCHEMA_LOCATION = "XMI.xsd";

  public EcoreXMISchemaBuilder()
  {
    super();
  }

  public Collection<EObject> generate(EPackage ePackage, QNameMap qNameMap, boolean minimized, List<EClass> rootList, Map<?, ?> options)
  {
    minimizedXMI = minimized;
    rootClasses = rootList;
    if (options != null)
    {
      useEncodedAttributeStyle = Boolean.TRUE.equals(options.get(EcoreXMLSchemaBuilder.OPTION_USE_ENCODED_ATTRIBUTE_STYLE));
      enforceLowerBound = Boolean.TRUE.equals(options.get(EcoreXMLSchemaBuilder.OPTION_TO_ENFORCE_LOWERBOUND));
    }

    if (rootList != null && !rootList.isEmpty())
    {
      for (EClass root : rootList)
      {
        if (root.getEPackage() != ePackage)
        {
          throw new IllegalArgumentException("Inappropriate root class " + root.getName());
        }
      }
    }

    return generate(ePackage, qNameMap);
  }

  @Override
  public Collection<EObject> generate(EPackage ePackage, QNameMap qNameMap)
  {
    List<EObject> result = new ArrayList<EObject>(super.generate(ePackage, qNameMap));
    result.add(1, createXMISchema());
    return result;
  }

  protected XSDSchema createXMISchema()
  {
    XSDSchema xmiSchema = XSDFactory.eINSTANCE.createXSDSchema();
    xmiSchema.setTargetNamespace(XMI_URI);
    xmiSchema.setSchemaForSchemaQNamePrefix("xsd");

    Map<String, String> namespaces = xmiSchema.getQNamePrefixToNamespaceMap();
    namespaces.put(XMI_PREFIX, XMI_URI);
    namespaces.put("xsd", XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);

    // <xsd:attribute name="id" type="xsd:ID"/>
    XSDAttributeDeclaration xmiIdAttribute = XSDFactory.eINSTANCE.createXSDAttributeDeclaration();
    xmiIdAttribute.setName("id");
    xmiIdAttribute.setTypeDefinition(xmiSchema.getSchemaForSchema().resolveSimpleTypeDefinition("ID"));
    xmiSchema.getContents().add(xmiIdAttribute);

    // <xsd:attributeGroup name="IdentityAttribs">
    //   <xsd:attribute name="label" type="xsd:string" use="optional" form="qualified"/>
    //   <xsd:attribute name="uuid" type="xsd:string" use="optional" form="qualified"/>
    // </xsd:attributeGroup>
    XSDAttributeGroupDefinition xmiIdentityAttribs = XSDFactory.eINSTANCE.createXSDAttributeGroupDefinition();
    xmiIdentityAttribs.setName("IdentityAttribs");
    List<XSDAttributeGroupContent> contents = xmiIdentityAttribs.getContents();
    contents.add(createAttributeUse(xmiSchema, "label", "string", "optional", "qualified", null));
    contents.add(createAttributeUse(xmiSchema, "uuid", "string", "optional", "qualified", null));
    xmiSchema.getContents().add(xmiIdentityAttribs);

    // <xsd:attributeGroup name="LinkAttribs">
    //   <xsd:attribute name="href" type="xsd:string" use="optional"/>
    //   <xsd:attribute name="idref" type="xsd:IDREF" use="optional" form="qualified"/>
    // </xsd:attributeGroup>
    XSDAttributeGroupDefinition xmiLinkAttribs = XSDFactory.eINSTANCE.createXSDAttributeGroupDefinition();
    xmiLinkAttribs.setName("LinkAttribs");
    contents = xmiLinkAttribs.getContents();
    contents.add(createAttributeUse(xmiSchema, "href", "string", "optional", null, null));
    contents.add(createAttributeUse(xmiSchema, "idref", "IDREF", "optional", "qualified", null));
    xmiSchema.getContents().add(xmiLinkAttribs);

    // <xsd:attributeGroup name="ObjectAttribs">
    //   <xsd:attributeGroup ref="IdentityAttribs"/>
    //   <xsd:attributeGroup ref="LinkAttribs"/>
    //   <xsd:attribute name="version" type="xsd:string" use="optional" fixed="2.0" form="qualified"/>
    //   <xsd:attribute name="type" type="xsd:QName" use="optional" form="qualified"/>
    // </xsd:attributeGroup>
    XSDAttributeGroupDefinition xmiObjectAttribs = XSDFactory.eINSTANCE.createXSDAttributeGroupDefinition();
    xmiObjectAttribs.setName("ObjectAttribs");
    contents = xmiObjectAttribs.getContents();
    contents.add(createAttributeGroupReference(xmiSchema, "IdentityAttribs"));
    contents.add(createAttributeGroupReference(xmiSchema, "LinkAttribs"));
    contents.add(createAttributeUse(xmiSchema, "version", "string", "optional", "qualified", "2.0"));
    contents.add(createAttributeUse(xmiSchema, "type", "QName", "optional", "qualified", null));
    xmiSchema.getContents().add(xmiObjectAttribs);

    // <xsd:complexType name="XMI">
    //   <xsd:choice minOccurs="0" maxOccurs="unbounded">
    //     <xsd:any processContents="strict"/>
    //   </xsd:choice>
    //   <xsd:attribute ref="id"/>
    //   <xsd:attributeGroup ref="IdentityAttribs"/>
    //   <xsd:attributeGroup ref="LinkAttribs"/>
    //   <xsd:attribute name="type" type="xsd:QName" use="optional" form="qualified"/>
    //   <xsd:attribute name="version" type="xsd:string" use="required" fixed="2.0" form="qualified"/>
    // </xsd:complexType>
    XSDComplexTypeDefinition xmiComplexTypeDefinition = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
    xmiComplexTypeDefinition.setName("XMI");
    // choice model group
    XSDParticle particle = XSDFactory.eINSTANCE.createXSDParticle();
    particle.setMinOccurs(0);
    particle.setMaxOccurs(-1);
    XSDModelGroup modelGroup = createAnyModelGroup("strict");
    particle.setContent(modelGroup);
    xmiComplexTypeDefinition.setContent(particle);
    contents = xmiComplexTypeDefinition.getAttributeContents();
    contents.add(createAttributeGroupReference(xmiSchema, "IdentityAttribs"));
    contents.add(createAttributeGroupReference(xmiSchema, "LinkAttribs"));
    contents.add(createAttributeUse(xmiSchema, "type", "QName", "optional", "qualified", null));
    contents.add(createAttributeUse(xmiSchema, "version", "string", "required", "qualified", "2.0"));
    xmiSchema.getContents().add(xmiComplexTypeDefinition);

    xmiSchema.getContents().add(createElementDeclaration(xmiSchema, "XMI", "XMI", false));

    // <xsd:complexType name="PackageReference">
    //   <xsd:choice minOccurs="0" maxOccurs="unbounded">
    //     <xsd:element name="name" type="xsd:string"/>
    //     <xsd:element name="version" type="xsd:string"/>
    //     <xsd:element ref="Extension"/>
    //   </xsd:choice>
    //   <xsd:attribute ref="id"/>
    //   <xsd:attributeGroup ref="ObjectAttribs"/>
    //   <xsd:attribute name="name" type="xsd:string" use="optional"/>
    //   <xsd:attribute name="version" type="xsd:string" use="optional"/>
    // </xsd:complexType>
    XSDComplexTypeDefinition prComplexTypeDefinition = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
    prComplexTypeDefinition.setName("PackageReference");
    // choice model group
    particle = XSDFactory.eINSTANCE.createXSDParticle();
    particle.setMinOccurs(0);
    particle.setMaxOccurs(-1);
    modelGroup = XSDFactory.eINSTANCE.createXSDModelGroup();
    modelGroup.setCompositor(XSDCompositor.CHOICE_LITERAL);
    addElementDeclaration(xmiSchema, modelGroup, "name", "string");
    addElementDeclaration(xmiSchema, modelGroup, "version", "string");
    particle.setContent(modelGroup);
    prComplexTypeDefinition.setContent(particle);
    contents = prComplexTypeDefinition.getAttributeContents();
    contents.add(createAttributeGroupReference(xmiSchema, "ObjectAttribs"));
    contents.add(createAttributeUse(xmiSchema, "name", "string", "optional", null, null));
    // EATM contents.add(createAttributeUse(xmiSchema, "version", "string", "optional", null, null));
    xmiSchema.getContents().add(prComplexTypeDefinition);

    xmiSchema.getContents().add(createElementDeclaration(xmiSchema, "PackageReference", "PackageReference", false));

    // <xsd:complexType name="Model">
    //   <xsd:complexContent>
    //     <xsd:extension base="PackageReference"/>
    //   </xsd:complexContent>
    // </xsd:complexType>
    xmiSchema.getContents().add(createExtendedComplexTypeDefinition(xmiSchema, "Model", "PackageReference"));

    xmiSchema.getContents().add(createElementDeclaration(xmiSchema, "Model", "Model", false));

    // <xsd:complexType name="Import">
    //   <xsd:complexContent>
    //     <xsd:extension base="PackageReference"/>
    //   </xsd:complexContent>
    // </xsd:complexType>
    xmiSchema.getContents().add(createExtendedComplexTypeDefinition(xmiSchema, "Import", "PackageReference"));

    xmiSchema.getContents().add(createElementDeclaration(xmiSchema, "Import", "Import", false));

    // <xsd:complexType name="MetaModel">
    //   <xsd:complexContent>
    //     <xsd:extension base="PackageReference"/>
    //   </xsd:complexContent>
    // </xsd:complexType>
    xmiSchema.getContents().add(createExtendedComplexTypeDefinition(xmiSchema, "MetaModel", "PackageReference"));

    xmiSchema.getContents().add(createElementDeclaration(xmiSchema, "MetaModel", "MetaModel", false));

    // <xsd:complexType name="Documentation">
    //   <xsd:choice minOccurs="0" maxOccurs="unbounded">
    //     <xsd:element name="contact" type="xsd:string"/>
    //     <xsd:element name="exporter" type="xsd:string"/>
    //     <xsd:element name="exporterVersion" type="xsd:string"/>
    //     <xsd:element name="longDescription" type="xsd:string"/>
    //     <xsd:element name="shortDescription" type="xsd:string"/>
    //     <xsd:element name="notice" type="xsd:string"/>
    //     <xsd:element name="owner" type="xsd:string"/>
    //     <xsd:element ref="Extension"/>
    //   </xsd:choice>
    //   <xsd:attribute ref="id"/>
    //   <xsd:attributeGroup ref="ObjectAttribs"/>
    //   <xsd:attribute name="contact" type="xsd:string" use="optional"/>
    //   <xsd:attribute name="exporter" type="xsd:string" use="optional"/>
    //   <xsd:attribute name="exporterVersion" type="xsd:string" use="optional"/>
    //   <xsd:attribute name="longDescription" type="xsd:string" use="optional"/>
    //   <xsd:attribute name="shortDescription" type="xsd:string" use="optional"/>
    //   <xsd:attribute name="notice" type="xsd:string" use="optional"/>
    //   <xsd:attribute name="owner" type="xsd:string" use="optional"/>
    // </xsd:complexType>
    XSDComplexTypeDefinition documentationComplexType = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
    documentationComplexType.setName("Documentation");
    // choice model group
    particle = XSDFactory.eINSTANCE.createXSDParticle();
    particle.setMinOccurs(0);
    particle.setMaxOccurs(-1);
    modelGroup = XSDFactory.eINSTANCE.createXSDModelGroup();
    modelGroup.setCompositor(XSDCompositor.CHOICE_LITERAL);
    addElementDeclaration(xmiSchema, modelGroup, "contact", "string");
    addElementDeclaration(xmiSchema, modelGroup, "exporter", "string");
    addElementDeclaration(xmiSchema, modelGroup, "exporterVersion", "string");
    addElementDeclaration(xmiSchema, modelGroup, "longDescription", "string");
    addElementDeclaration(xmiSchema, modelGroup, "shortDescription", "string");
    addElementDeclaration(xmiSchema, modelGroup, "notice", "string");
    addElementDeclaration(xmiSchema, modelGroup, "owner", "string");
    particle.setContent(modelGroup);
    documentationComplexType.setContent(particle);
    contents = documentationComplexType.getAttributeContents();
    contents.add(createAttributeGroupReference(xmiSchema, "ObjectAttribs"));
    contents.add(createAttributeUse(xmiSchema, "contact", "string", "optional", null, null));
    contents.add(createAttributeUse(xmiSchema, "exporter", "string", "optional", null, null));
    contents.add(createAttributeUse(xmiSchema, "exporterVersion", "string", "optional", null, null));
    contents.add(createAttributeUse(xmiSchema, "longDescription", "string", "optional", null, null));
    contents.add(createAttributeUse(xmiSchema, "shortDescription", "string", "optional", null, null));
    contents.add(createAttributeUse(xmiSchema, "notice", "string", "optional", null, null));
    contents.add(createAttributeUse(xmiSchema, "owner", "string", "optional", null, null));
    xmiSchema.getContents().add(documentationComplexType);

    xmiSchema.getContents().add(createElementDeclaration(xmiSchema, "Documentation", "Documentation", false));

    // <xsd:complexType name="Extension">
    //   <xsd:choice minOccurs="0" maxOccurs="unbounded">
    //     <xsd:any processContents="lax"/>
    //   </xsd:choice>
    //   <xsd:attribute ref="id"/>
    //   <xsd:attributeGroup ref="ObjectAttribs"/>
    //   <xsd:attribute name="extender" type="xsd:string" use="optional"/>
    //   <xsd:attribute name="extenderID" type="xsd:string" use="optional"/>
    // </xsd:complexType>
    XSDComplexTypeDefinition extensionComplexType = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
    extensionComplexType.setName("Extension");
    // choice model group
    particle = XSDFactory.eINSTANCE.createXSDParticle();
    particle.setMinOccurs(0);
    particle.setMaxOccurs(-1);
    modelGroup = createAnyModelGroup("lax");
    particle.setContent(modelGroup);
    extensionComplexType.setContent(particle);
    contents = extensionComplexType.getAttributeContents();
    contents.add(createAttributeGroupReference(xmiSchema, "ObjectAttribs"));
    contents.add(createAttributeUse(xmiSchema, "extender", "string", "optional", null, null));
    contents.add(createAttributeUse(xmiSchema, "extenderID", "string", "optional", null, null));
    xmiSchema.getContents().add(extensionComplexType);

    xmiSchema.getContents().add(createElementDeclaration(xmiSchema, "Extension", "Extension", false));

    // <xsd:complexType name="Difference">
    //   <xsd:choice minOccurs="0" maxOccurs="unbounded">
    //     <xsd:element name="target">
    //       <xsd:complexType>
    //         <xsd:choice minOccurs="0" maxOccurs="unbounded">
    //           <xsd:any processContents="skip"/>
    //         </xsd:choice>
    //         <xsd:anyAttribute processContents="skip"/>
    //       </xsd:complexType>
    //     </xsd:element>
    //     <xsd:element name="difference" type="Difference"/>
    //     <xsd:element name="container" type="Difference"/>
    //     <xsd:element ref="Extension"/>
    //   </xsd:choice>
    //   <xsd:attribute ref="id"/>
    //   <xsd:attributeGroup ref="ObjectAttribs"/>
    //   <xsd:attribute name="target" type="xsd:IDREFS" use="optional"/>
    //   <xsd:attribute name="container" type="xsd:IDREFS" use="optional"/>
    // </xsd:complexType>
    XSDComplexTypeDefinition differenceComplexType = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
    differenceComplexType.setName("Difference");
    particle = XSDFactory.eINSTANCE.createXSDParticle();
    particle.setMinOccurs(0);
    particle.setMaxOccurs(-1);
    modelGroup = XSDFactory.eINSTANCE.createXSDModelGroup();
    modelGroup.setCompositor(XSDCompositor.CHOICE_LITERAL);
    XSDParticle particle2 = XSDFactory.eINSTANCE.createXSDParticle();
    XSDElementDeclaration target = XSDFactory.eINSTANCE.createXSDElementDeclaration();
    target.setName("target");
    XSDComplexTypeDefinition anonymous = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
    XSDParticle particle3 = XSDFactory.eINSTANCE.createXSDParticle();
    particle3.setMinOccurs(0);
    particle3.setMaxOccurs(-1);
    particle3.setContent(createAnyModelGroup("skip"));
    anonymous.setContent(particle3);
    anonymous.setAttributeWildcardContent(createAny("skip"));
    target.setAnonymousTypeDefinition(anonymous);
    particle2.setContent(target);
    modelGroup.getContents().add(particle2);
    addElementDeclaration(xmiSchema, modelGroup, "difference", "Difference", false);
    addElementDeclaration(xmiSchema, modelGroup, "container", "Difference", false);
    particle.setContent(modelGroup);
    differenceComplexType.setContent(particle);
    contents = differenceComplexType.getAttributeContents();
    contents.add(createAttributeGroupReference(xmiSchema, "ObjectAttribs"));
    contents.add(createAttributeUse(xmiSchema, "target", "IDREFS", "optional", null, null));
    contents.add(createAttributeUse(xmiSchema, "container", "IDREFS", "optional", null, null));
    xmiSchema.getContents().add(differenceComplexType);

    xmiSchema.getContents().add(createElementDeclaration(xmiSchema, "Difference", "Difference", false));

    // <xsd:complexType name="Add">
    //   <xsd:complexContent>
    //     <xsd:extension base="Difference">
    //       <xsd:attribute name="position" type="xsd:string" use="optional"/>
    //       <xsd:attribute name="addition" type="xsd:IDREFS" use="optional"/>
    //    </xsd:extension>
    //   </xsd:complexContent>
    // </xsd:complexType>
    XSDComplexTypeDefinition addComplexType = createExtendedComplexTypeDefinition(xmiSchema, "Add", "Difference");
    contents = addComplexType.getAttributeContents();
    contents.add(createAttributeUse(xmiSchema, "position", "string", "optional", null, null));
    contents.add(createAttributeUse(xmiSchema, "addition", "IDREFS", "optional", null, null));
    xmiSchema.getContents().add(addComplexType);

    xmiSchema.getContents().add(createElementDeclaration(xmiSchema, "Add", "Add", false));

    // <xsd:complexType name="Replace">
    //   <xsd:complexContent>
    //     <xsd:extension base="Difference">
    //       <xsd:attribute name="position" type="xsd:string" use="optional"/>
    //       <xsd:attribute name="replacement" type="xsd:IDREFS" use="optional"/>
    //     </xsd:extension>
    //   </xsd:complexContent>
    // </xsd:complexType>
    XSDComplexTypeDefinition replaceComplexType = createExtendedComplexTypeDefinition(xmiSchema, "Replace", "Difference");
    contents = replaceComplexType.getAttributeContents();
    contents.add(createAttributeUse(xmiSchema, "position", "string", "optional", null, null));
    contents.add(createAttributeUse(xmiSchema, "replacement", "IDREFS", "optional", null, null));
    xmiSchema.getContents().add(replaceComplexType);

    xmiSchema.getContents().add(createElementDeclaration(xmiSchema, "Replace", "Replace", false));

    // <xsd:complexType name="Delete">
    //   <xsd:complexContent>
    //     <xsd:extension base="Difference"/>
    //   </xsd:complexContent>
    // </xsd:complexType>
    XSDComplexTypeDefinition deleteComplexType = createExtendedComplexTypeDefinition(xmiSchema, "Delete", "Difference");
    xmiSchema.getContents().add(deleteComplexType);

    xmiSchema.getContents().add(createElementDeclaration(xmiSchema, "Delete", "Delete", false));

    // <xsd:complexType name="Any">
    //   <xsd:choice minOccurs="0" maxOccurs="unbounded">
    //     <xsd:any processContents="skip"/>
    //   </xsd:choice>
    //   <xsd:anyAttribute processContents="skip"/>
    // </xsd:complexType>
    XSDComplexTypeDefinition xmiAny = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
    xmiAny.setName("Any");
    particle = XSDFactory.eINSTANCE.createXSDParticle();
    particle.setMinOccurs(0);
    particle.setMaxOccurs(-1);
    particle.setContent(createAnyModelGroup("skip"));
    xmiAny.setContent(particle);
    xmiAny.setAttributeWildcardContent(createAny("skip"));
    xmiSchema.getContents().add(xmiAny);

    return xmiSchema;
  }

  protected XSDModelGroup createAnyModelGroup(String processContents)
  {
    XSDModelGroup modelGroup = XSDFactory.eINSTANCE.createXSDModelGroup();
    modelGroup.setCompositor(XSDCompositor.CHOICE_LITERAL);
    XSDParticle particle = XSDFactory.eINSTANCE.createXSDParticle();
    particle.setContent(createAny(processContents));
    modelGroup.getContents().add(particle);
    return modelGroup;
  }

  protected XSDWildcard createAny(String processContents)
  {
    XSDWildcard any = XSDFactory.eINSTANCE.createXSDWildcard();

    if ("strict".equals(processContents))
    {
      any.setProcessContents(XSDProcessContents.STRICT_LITERAL);
    }
    else
      if ("skip".equals(processContents))
      {
        any.setProcessContents(XSDProcessContents.SKIP_LITERAL);
      }
      else
        if ("lax".equals(processContents))
        {
          any.setProcessContents(XSDProcessContents.LAX_LITERAL);
        }

    return any;
  }

  protected XSDComplexTypeDefinition createExtendedComplexTypeDefinition(XSDSchema schema, String name, String extension)
  {
    XSDComplexTypeDefinition complexType = XSDFactory.eINSTANCE.createXSDComplexTypeDefinition();
    complexType.setName(name);
    complexType.setDerivationMethod(XSDDerivationMethod.EXTENSION_LITERAL);
    complexType.setBaseTypeDefinition(schema.resolveTypeDefinition(extension));
    return complexType;
  }

  protected XSDAttributeGroupDefinition createAttributeGroupReference(XSDSchema schema, String name)
  {
    XSDAttributeGroupDefinition reference = XSDFactory.eINSTANCE.createXSDAttributeGroupDefinition();
    reference.setResolvedAttributeGroupDefinition(schema.resolveAttributeGroupDefinition(name));
    return reference;
  }

  protected XSDElementDeclaration createElementDeclaration(XSDSchema schema, String name, String type, boolean schemaType)
  {
    XSDElementDeclaration elementDeclaration = XSDFactory.eINSTANCE.createXSDElementDeclaration();
    elementDeclaration.setName(name);

    if (schemaType)
    {
      elementDeclaration.setTypeDefinition(schema.getSchemaForSchema().resolveSimpleTypeDefinition(type));
    }
    else
    {
      elementDeclaration.setTypeDefinition(schema.resolveSimpleTypeDefinition(type));
    }

    return elementDeclaration;
  }

  protected void addElementDeclaration(XSDSchema schema, XSDModelGroup modelGroup, String name, String type)
  {
    addElementDeclaration(schema, modelGroup, name, type, true);
  }

  protected void addElementDeclaration(XSDSchema schema, XSDModelGroup modelGroup, String name, String type, boolean schemaType)
  {
    XSDParticle particle = XSDFactory.eINSTANCE.createXSDParticle();
    particle.setContent(createElementDeclaration(schema, name, type, schemaType));
    modelGroup.getContents().add(particle);
  }

  protected void addElementDeclarationReference(XSDSchema schema, XSDModelGroup modelGroup, String reference)
  {
    XSDParticle particle = XSDFactory.eINSTANCE.createXSDParticle();
    XSDElementDeclaration elementDeclaration = XSDFactory.eINSTANCE.createXSDElementDeclaration();
    elementDeclaration.setResolvedElementDeclaration(schema.resolveElementDeclaration(reference));
    particle.setContent(elementDeclaration);
    modelGroup.getContents().add(particle);
  }

  protected XSDAttributeUse createAttributeReference(XSDSchema schema, String name)
  {
    XSDAttributeDeclaration attributeDeclaration = XSDFactory.eINSTANCE.createXSDAttributeDeclaration();
    attributeDeclaration.setResolvedAttributeDeclaration(schema.resolveAttributeDeclaration(name));
    XSDAttributeUse attributeUse = XSDFactory.eINSTANCE.createXSDAttributeUse();
    attributeUse.setContent(attributeDeclaration);
    return attributeUse;
  }

  protected XSDAttributeUse createAttributeUse(XSDSchema schema, String name, String type, String use, String form, String fixed)
  {
    XSDAttributeDeclaration attributeDeclaration = XSDFactory.eINSTANCE.createXSDAttributeDeclaration();
    attributeDeclaration.setName(name);
    attributeDeclaration.setTypeDefinition(schema.getSchemaForSchema().resolveSimpleTypeDefinition(type));

    if ("qualified".equals(form))
    {
      attributeDeclaration.setForm(XSDForm.QUALIFIED_LITERAL);
    }

    XSDAttributeUse attributeUse = XSDFactory.eINSTANCE.createXSDAttributeUse();
    attributeUse.setContent(attributeDeclaration);

    if ("optional".equals(use))
    {
      attributeUse.setUse(XSDAttributeUseCategory.OPTIONAL_LITERAL);
    }

    if ("required".equals(use))
    {
      attributeUse.setUse(XSDAttributeUseCategory.REQUIRED_LITERAL);
    }

    if (fixed != null)
    {
      attributeUse.setConstraint(XSDConstraint.FIXED_LITERAL);
      attributeUse.setLexicalValue("2.0");
    }

    return attributeUse;
  }

  @Override
  protected boolean makeClassElementDeclaration(EClass eClass)
  {
    // if rootList == null, then generate all classes
    // if rootList.isEmpty, then generate no classes
    // if rootList.contains(), then generate only those contained classes 

    return rootClasses == null || (!rootClasses.isEmpty() && rootClasses.contains(eClass));
  }

  @Override
  protected XSDModelGroup createModelGroup(XSDComplexTypeDefinition xsdComplexTypeDefinition)
  {
    XSDModelGroup modelGroup = XSDFactory.eINSTANCE.createXSDModelGroup();
    modelGroup.setCompositor(XSDCompositor.CHOICE_LITERAL);
    XSDParticle particle = XSDFactory.eINSTANCE.createXSDParticle();
    particle.setMinOccurs(0);
    particle.setMaxOccurs(-1);
    particle.setContent(modelGroup);
    xsdComplexTypeDefinition.setContent(particle);
    return modelGroup;
  }

  @Override
  protected void setAttributeElementMultiplicity(EAttribute attribute, XSDParticle particle)
  {
    // Do nothing
  }

  @Override
  protected void additionalProcessing(EClass eClass, XSDComplexTypeDefinition xsdComplexTypeDefinition)
  {
    if (eClass.getESuperTypes().size() == 0)
    {
      addXMIExtension(getModelGroup(xsdComplexTypeDefinition));
      addXMIAttributes(xsdComplexTypeDefinition);
    }
  }

  protected void addXMIExtension(XSDModelGroup modelGroup)
  {
    if (!minimizedXMI)
    {
      importXMI();
      XSDParticle particle = XSDFactory.eINSTANCE.createXSDParticle();
      XSDElementDeclaration xsdElementDeclaration = XSDFactory.eINSTANCE.createXSDElementDeclaration();
      xsdElementDeclaration.setResolvedElementDeclaration(xsdElementDeclaration.resolveElementDeclaration(XMI_URI, "Extension"));
      particle.setContent(xsdElementDeclaration);
      modelGroup.getContents().add(particle);
    }
  }

  protected void addXMIAttributes(XSDComplexTypeDefinition xsdComplexTypeDefinition)
  {

    if (!minimizedXMI)
    {
      importXMI();
      XSDAttributeGroupDefinition objAttribs = XSDFactory.eINSTANCE.createXSDAttributeGroupDefinition();
      objAttribs.setResolvedAttributeGroupDefinition(objAttribs.resolveAttributeGroupDefinition(XMI_URI, "ObjectAttribs"));
      xsdComplexTypeDefinition.getAttributeContents().add(0, objAttribs);
    }
    else
    {
      if (!useEncodedAttributeStyle)
      {
        importXMI();
        XSDAttributeGroupDefinition linkAttribs = XSDFactory.eINSTANCE.createXSDAttributeGroupDefinition();
        linkAttribs.setResolvedAttributeGroupDefinition(linkAttribs.resolveAttributeGroupDefinition(XMI_URI, "LinkAttribs"));
        xsdComplexTypeDefinition.getAttributeContents().add(0, linkAttribs);
      }
    }

    if (!minimizedXMI)
    {
      importXMI();
      XSDAttributeDeclaration attrDecl = XSDFactory.eINSTANCE.createXSDAttributeDeclaration();
      attrDecl.setResolvedAttributeDeclaration(attrDecl.resolveAttributeDeclaration(XMI_URI, "id"));
      XSDAttributeUse attrUse = XSDFactory.eINSTANCE.createXSDAttributeUse();
      attrUse.setContent(attrDecl);
      xsdComplexTypeDefinition.getAttributeContents().add(0, attrUse);
    }
  }

  @Override
  protected void setDefaultValue(EAttribute attribute, XSDAttributeDeclaration attrDecl)
  {
    // Do nothing
  }

  @Override
  protected boolean makeReferenceElement(EReference reference)
  {
    return (reference.isContainment() && !reference.isContainer()) || !useEncodedAttributeStyle;
  }

  @Override
  protected void setReferenceElementType(EReference reference, XSDElementDeclaration xsdElementDeclaration)
  {
    if (reference.getEType() != null)
    {
      super.setReferenceElementType(reference, xsdElementDeclaration);
    }
    else
    {
      importXMI();
      xsdElementDeclaration.setTypeDefinition(xsdElementDeclaration.resolveSimpleTypeDefinition(XMI_URI, "Any"));
    }
  }

  @Override
  protected void setReferenceElementMultiplicity(EReference reference, XSDParticle particle)
  {
    // Do nothing
  }
  
  protected void importXMI()
  {
    Map<String, String> namespaces = this.xsdSchema.getQNamePrefixToNamespaceMap();
    if (namespaces.put(XMI_PREFIX, XMI_URI) == null)
    {
      XSDImport xmiImport = XSDFactory.eINSTANCE.createXSDImport();
      xmiImport.setNamespace(XMI_URI);
      xmiImport.setSchemaLocation(EcorePackage.eNS_URI.equals(xsdSchema.getTargetNamespace()) ? XMI_SCHEMA_LOCATION : "platform:/plugin/org.eclipse.emf.ecore/model/" + XMI_SCHEMA_LOCATION);
      this.xsdSchema.getContents().add(0, xmiImport);
    }
  }
}
