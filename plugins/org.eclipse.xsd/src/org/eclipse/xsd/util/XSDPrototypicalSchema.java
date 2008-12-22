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
 * $Id: XSDPrototypicalSchema.java,v 1.10 2008/12/22 14:25:48 emerks Exp $
 */
package org.eclipse.xsd.util;


import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeGroupDefinition;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDAttributeUseCategory;
import org.eclipse.xsd.XSDComplexFinal;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDConstraint;
import org.eclipse.xsd.XSDDerivationMethod;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDEnumerationFacet;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDForm;
import org.eclipse.xsd.XSDFractionDigitsFacet;
import org.eclipse.xsd.XSDIdentityConstraintCategory;
import org.eclipse.xsd.XSDIdentityConstraintDefinition;
import org.eclipse.xsd.XSDImport;
import org.eclipse.xsd.XSDMaxExclusiveFacet;
import org.eclipse.xsd.XSDMaxInclusiveFacet;
import org.eclipse.xsd.XSDMaxLengthFacet;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDModelGroupDefinition;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDPatternFacet;
import org.eclipse.xsd.XSDPlugin;
import org.eclipse.xsd.XSDProcessContents;
import org.eclipse.xsd.XSDProhibitedSubstitutions;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSchemaContent;
import org.eclipse.xsd.XSDSchemaDirective;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDWildcard;
import org.eclipse.xsd.XSDXPathDefinition;
import org.eclipse.xsd.XSDXPathVariety;


/**
 * The <b>Sample Code</b> to construct
 * the {@link #initializePurchaseOrderSchema Purchase Order Schema} 
 * and the {@link #initializePrototypeSchema Prototype Schema}
 * using the concrete APIs.
 * <p>
 * Additional examples include:
 * <ol>
 *   <li>
 *     {@link #savePurchaseOrderSchema saving} the Purchase Order Schema to a URI,
 *   </li>
 *   <li>
 *     {@link #printSchema printing a schema} loaded from a URI,
 *   </li>
 *   <li>
 *     {@link #printComponent printing a component's element} using Xerces,
 *   </li>
 *   <li>
 *     {@link #createSchema creating} a schema from an element,
 *   </li>
 *   <li>
 *     {@link #traceLoading tracing} a resource set's loading behavior when loading a URI
 *   </li>
 *   <li>
 *     {@link #cloneComponent cloning} a component.
 *   </li>
 *   <li>
 *     and {@link #crossReferenceTest cross referencing}. 
 *   </li>
 * </ol>
 * </p>
 */
public class XSDPrototypicalSchema 
{
  /**
   * The one static instance.
   */
  protected static XSDPrototypicalSchema instance;  

  /**
   * Return the one static instance.
   * @return the one static instance.
   */
  public static XSDPrototypicalSchema getInstance()
  {
    // This is the script that will copy and encode the body of each method into the javadoc of each method.
    // It uses bash and sed.
    //
    // cp XSDPrototypicalSchema.java .XSDPrototypicalSchema.java.$$.bak;
    // for snippet in $(sed '{ /insert-begin[-]/!d; s/.*insert-begin[-]\([-a-z]*\).*/\1/; }' < XSDPrototypicalSchema.java); do
    // (
    //   echo $snippet;
    //   sed '{ 1,/insert-begin[-]'$snippet'/!d; }' < XSDPrototypicalSchema.java > result;
    //   sed '{ 1,/<[!]-- begin-'$snippet' -->/d;
    //          /<!-- end-'$snippet' -->/,$d;
    //          s/<" + "!--/\&lt;!--/;
    //          s/<\([^A-Za-z!/]\)/\&lt;\1/g;
    //          s/\/\*//g;
    //          s/\*\///g;
    //          /\/\/\//s/</\&lt;/g;
    //          s/\/\/\/\(.*\)/\/\/<b>\1<\/b>/;
    //          s/  /   */;
    //          s/^$/   */; }' < XSDPrototypicalSchema.java | tee junk.$$ >> result;
    //   sed '{ /insert-end[-]'$snippet'/,$!d; }' < XSDPrototypicalSchema.java >> result;
    //   cp result XSDPrototypicalSchema.java;
    // );
    // done
    //
    if (instance == null)
    {
      instance = new XSDPrototypicalSchema();
    }
    return instance;
  }

  /**
   * Returns the {@link #initializePrototypeSchema Prototype Schema} instance.
   * @return the Prototype Schema instance.
   */
  public static XSDSchema getPrototypicalSchema()
  {
    return getInstance().prototypeSchema;
  }

  /**
   * Returns the {@link #initializePurchaseOrderSchema Purchase Order Schema} instance.
   * @return the Purchase Order Schema instance.
   */
  public static XSDSchema getPurchaseOrderSchema()
  {
    return getInstance().purchaseOrderSchema;
  }

  /**
   * A cached XSDPackage.
   */

  /**
   * A cached XSDFactory.
   */
  public XSDFactory xsdFactory = XSDFactory.eINSTANCE;

  /**
   * The {@link #initializePrototypeSchema Prototype Schema}.
   */
  public XSDSchema prototypeSchema;

  /**
   * The {@link #initializePurchaseOrderSchema Purchase Order Schema}.
   */
  public XSDSchema purchaseOrderSchema;

  /**
   * The value <code>"http://www.example.com/SomeOtherSchema"</code>.
   */
  protected String someOtherSchemaURI = "http://www.example.com/SomeOtherSchema";

  /**
   * Create the {@link #initializePrototypeSchema Prototype Schema} and the {@link #initializePurchaseOrderSchema Purchase Order Schema}.
   */
  public XSDPrototypicalSchema()
  {
    purchaseOrderSchema = initializePurchaseOrderSchema();

    initializePrototypeSchema();

/*
    // These test whether DOM event notifications work.
    //
    for (Iterator components = prototypeSchema.getComponentsWithApplicationInformation("http://www.example.com/appinfo").iterator(); 
         components.hasNext(); )
    {
      XSDConcreteComponent xsdConcreteComponent = (XSDConcreteComponent)components.next();
      System.out.println("# " + xsdConcreteComponent);
    }

    simpleTypeDefinition.getElement().setAttribute("name", "abc");
    System.out.println("---??? " + simpleTypeDefinition.getName());


    simpleRecursiveModelGroupDefinition.getElement().setAttribute("name", "xyz");
*/
  }

  /**
   *<pre>
   *<!-- insert-begin-initialize-prototype-schema -->
   *{
   *  // Create the schema, give it a target namespace, and set its form defaults.
   *  //
   *  prototypeSchema =  xsdFactory.{@link XSDFactory#createXSDSchema createXSDSchema}();
   *
   *  prototypeSchema.{@link XSDSchema#setTargetNamespace setTargetNamespace}("http://www.example.com/PrototypicalSchema");
   *  prototypeSchema.{@link XSDSchema#setElementFormDefault setElementFormDefault}(XSDForm.QUALIFIED_LITERAL);
   *  prototypeSchema.{@link XSDSchema#setAttributeFormDefault setAttributeFormDefault}(XSDForm.QUALIFIED_LITERAL);
   *
   *  // If you want schema tags and references to schema types to be qualified,
   *  // which is recommend, this is the recommended qualifier.
   *  //
   *  prototypeSchema.{@link XSDSchema#setSchemaForSchemaQNamePrefix setSchemaForSchemaQNamePrefix}("xsd");
   *
   *  // Choose the prefix used for this schema's namespace,
   *  // the {@link XSDSchema#getSchemaForSchema schema for schema}'s namespace,
   *  // and some other imported namespace.
   *  //
   *  Map qNamePrefixToNamespaceMap = prototypeSchema.{@link XSDSchema#getQNamePrefixToNamespaceMap getQNamePrefixToNamespaceMap}();
   *  qNamePrefixToNamespaceMap.put("PTS", prototypeSchema.getTargetNamespace());
   *  qNamePrefixToNamespaceMap.put(prototypeSchema.getSchemaForSchemaQNamePrefix(), {@link XSDConstants}.SCHEMA_FOR_SCHEMA_URI_2001);
   *  qNamePrefixToNamespaceMap.put("EXT", {@link #someOtherSchemaURI});
   *
   *  // Initialize the other parts of this examples.
   *  //
   *  {@link #initializeSomeOtherImport()};
   *  {@link #initializeSimpleAttributeGroupDefinition()};
   *  {@link #initializeSimpleRecursiveModelGroupDefinition()};
   *  {@link #initializeSimpleRecursiveComplexTypeDefinition()};
   *  {@link #initializeSimpleRecursiveElementDeclaration()};
   *  {@link #initializeSimpleAttributeDeclaration()};
   *  {@link #initializeSimpleElementDeclarationWithAnonymousType()};
   *  {@link #initializeSimpleTypeDefinition()};
   *  {@link #initializeSimpleListTypeDefinition()};
   *  {@link #initializeSimpleUnionTypeDefinition()};
   *  {@link #initializeFancyListTypeDefinition()};
   *  {@link #initializeSimpleContentComplexTypeDefinition()};
   *
   *  // This is the <!-- SAMPLE --> <a name="prototype-end">result</a> that is produced.
   *  //
   *  //<b> &lt;?xml version="1.0" encoding="UTF-8"?></b>
   *  //<b> &lt;xsd:schema attributeFormDefault="qualified"</b>
   *  //<b>     elementFormDefault="qualified"</b>
   *  //<b>     targetNamespace="http://www.example.com/PrototypicalSchema"</b>
   *  //<b>     xmlns:EXT="http://www.example.com/SomeOtherSchema"</b>
   *  //<b>     xmlns:PTS="http://www.example.com/PrototypicalSchema" xmlns:xsd="http://www.w3.org/2001/XMLSchema"></b>
   *  //<b>     &lt;xsd:import namespace="http://www.example.com/SomeOtherSchema" schemaLocation="http://www.example.com/SomeOtherSchema"/></b>
   *  //<b>     &lt;xsd:attributeGroup name="simpleAttributeGroupDefinition"></b>
   *  //<b>         &lt;xsd:attribute default="defaultValue"</b>
   *  //<b>             name="simpleAttributeDeclarationGroupMember" type="EXT:someOtherTypeDefinition"/></b>
   *  //<b>     &lt;/xsd:attributeGroup></b>
   *  //<b>     &lt;xsd:group name="simpleRecursiveModelGroupDefinition"></b>
   *  //<b>         &lt;xsd:annotation/></b>
   *  //<b>         &lt;xsd:sequence></b>
   *  //<b>             &lt;xsd:element maxOccurs="unbounded" minOccurs="0" ref="PTS:simpleRecursiveElementDeclaration"/></b>
   *  //<b>             &lt;xsd:any namespace="##other" processContents="lax"/></b>
   *  //<b>         &lt;/xsd:sequence></b>
   *  //<b>     &lt;/xsd:group></b>
   *  //<b>     &lt;xsd:complexType abstract="false" block="#all" final="#all"</b>
   *  //<b>         mixed="true" name="SimpleRecursiveComplexTypeDefinition"></b>
   *  //<b>         &lt;xsd:annotation></b>
   *  //<b>             &lt;xsd:appinfo source="http://www.example.com/appinfo"/></b>
   *  //<b>             &lt;xsd:documentation</b>
   *  //<b>                 source="http://www.example.com/documentation">A simple</b>
   *  //<b>                 recursive complex type definition.&lt;/xsd:documentation></b>
   *  //<b>         &lt;/xsd:annotation></b>
   *  //<b>         &lt;xsd:group ref="PTS:simpleRecursiveModelGroupDefinition"/></b>
   *  //<b>         &lt;xsd:attribute ref="PTS:simpleAttributeDeclaration" use="optional"/></b>
   *  //<b>         &lt;xsd:attributeGroup ref="PTS:simpleAttributeGroupDefinition"/></b>
   *  //<b>         &lt;xsd:anyAttribute namespace="##other"/></b>
   *  //<b>     &lt;/xsd:complexType></b>
   *  //<b>     &lt;xsd:element name="simpleRecursiveElementDeclaration" type="PTS:SimpleRecursiveComplexTypeDefinition"></b>
   *  //<b>         &lt;xsd:annotation/></b>
   *  //<b>         &lt;xsd:unique name="unique"></b>
   *  //<b>             &lt;xsd:selector xpath="simpleRecursiveElementDeclaration"/></b>
   *  //<b>             &lt;xsd:field xpath="simpleAttributeDeclarationGroupMember"/></b>
   *  //<b>         &lt;/xsd:unique></b>
   *  //<b>         &lt;xsd:key name="key"></b>
   *  //<b>             &lt;xsd:selector xpath="simpleRecursiveElementDeclaration"/></b>
   *  //<b>             &lt;xsd:field xpath="simpleAttributeDeclarationGroupMember"/></b>
   *  //<b>         &lt;/xsd:key></b>
   *  //<b>         &lt;xsd:keyref name="keyref" refer="PTS:key"></b>
   *  //<b>             &lt;xsd:selector xpath="simpleRecursiveElementDeclaration"/></b>
   *  //<b>             &lt;xsd:field xpath="simpleAttributeDeclaration"/></b>
   *  //<b>         &lt;/xsd:keyref></b>
   *  //<b>     &lt;/xsd:element></b>
   *  //<b>     &lt;xsd:attribute default="defaultValue"</b>
   *  //<b>         name="simpleAttributeDeclaration" type="EXT:someOtherTypeDefinition"/></b>
   *  //<b>     &lt;xsd:element fixed="12ab" name="simpleElementWithAnonymousType"></b>
   *  //<b>         &lt;xsd:annotation/></b>
   *  //<b>         &lt;xsd:simpleType></b>
   *  //<b>             &lt;xsd:restriction base="xsd:string"></b>
   *  //<b>                 &lt;xsd:pattern value="\d\d.."/></b>
   *  //<b>             &lt;/xsd:restriction></b>
   *  //<b>         &lt;/xsd:simpleType></b>
   *  //<b>     &lt;/xsd:element></b>
   *  //<b>     &lt;xsd:simpleType name="SimpleTypeDefinition"></b>
   *  //<b>         &lt;xsd:annotation/></b>
   *  //<b>         &lt;xsd:restriction base="xsd:positiveInteger"></b>
   *  //<b>             &lt;xsd:maxInclusive value="100"/></b>
   *  //<b>         &lt;/xsd:restriction></b>
   *  //<b>     &lt;/xsd:simpleType></b>
   *  //<b>     &lt;xsd:simpleType name="SimpleListTypeDefinition"></b>
   *  //<b>         &lt;xsd:annotation/></b>
   *  //<b>         &lt;xsd:list itemType="xsd:integer"/></b>
   *  //<b>     &lt;/xsd:simpleType></b>
   *  //<b>     &lt;xsd:simpleType name="SimpleUnionTypeDefinition"></b>
   *  //<b>         &lt;xsd:annotation/></b>
   *  //<b>         &lt;xsd:union memberTypes="xsd:integer"/></b>
   *  //<b>     &lt;/xsd:simpleType></b>
   *  //<b>     &lt;xsd:simpleType name="FancyListTypeDefinition"></b>
   *  //<b>         &lt;xsd:restriction></b>
   *  //<b>             &lt;xsd:simpleType></b>
   *  //<b>                 &lt;xsd:list></b>
   *  //<b>                     &lt;xsd:simpleType></b>
   *  //<b>                         &lt;xsd:union></b>
   *  //<b>                             &lt;xsd:simpleType></b>
   *  //<b>                                 &lt;xsd:restriction base="xsd:string"></b>
   *  //<b>                                     &lt;xsd:enumeration value="unknown"/></b>
   *  //<b>                                 &lt;/xsd:restriction></b>
   *  //<b>                             &lt;/xsd:simpleType></b>
   *  //<b>                             &lt;xsd:simpleType></b>
   *  //<b>                                 &lt;xsd:restriction base="xsd:decimal"></b>
   *  //<b>                                     &lt;xsd:fractionDigits value="2"/></b>
   *  //<b>                                 &lt;/xsd:restriction></b>
   *  //<b>                             &lt;/xsd:simpleType></b>
   *  //<b>                         &lt;/xsd:union></b>
   *  //<b>                     &lt;/xsd:simpleType></b>
   *  //<b>                 &lt;/xsd:list></b>
   *  //<b>             &lt;/xsd:simpleType></b>
   *  //<b>             &lt;xsd:maxLength value="4"/></b>
   *  //<b>         &lt;/xsd:restriction></b>
   *  //<b>     &lt;/xsd:simpleType></b>
   *  //<b>     &lt;xsd:complexType name="SimpleContentComplexTypeDefinition"></b>
   *  //<b>         &lt;xsd:annotation/></b>
   *  //<b>         &lt;xsd:simpleContent></b>
   *  //<b>             &lt;xsd:extension base="xsd:string"></b>
   *  //<b>                 &lt;xsd:attribute ref="PTS:simpleAttributeDeclaration" use="optional"/></b>
   *  //<b>             &lt;/xsd:extension></b>
   *  //<b>         &lt;/xsd:simpleContent></b>
   *  //<b>     &lt;/xsd:complexType></b>
   *  //<b> &lt;/xsd:schema></b>
   *
   *  return prototypeSchema;
   *}
   *<!-- insert-end-initialize-prototype-schema -->
   *</pre>
   */
  public XSDSchema initializePrototypeSchema()
  // <!-- begin-initialize-prototype-schema -->
  {
    // Create the schema, give it a target namespace, and set its form defaults.
    //
    prototypeSchema =  xsdFactory./*{@link XSDFactory#createXSDSchema */createXSDSchema/*}*/();

    prototypeSchema./*{@link XSDSchema#setTargetNamespace */setTargetNamespace/*}*/("http://www.example.com/PrototypicalSchema");
    prototypeSchema./*{@link XSDSchema#setElementFormDefault */setElementFormDefault/*}*/(XSDForm.QUALIFIED_LITERAL);
    prototypeSchema./*{@link XSDSchema#setAttributeFormDefault */setAttributeFormDefault/*}*/(XSDForm.QUALIFIED_LITERAL);

    // If you want schema tags and references to schema types to be qualified,
    // which is recommend, this is the recommended qualifier.
    //
    prototypeSchema./*{@link XSDSchema#setSchemaForSchemaQNamePrefix */setSchemaForSchemaQNamePrefix/*}*/("xsd");

    // Choose the prefix used for this schema's namespace,
    // the {@link XSDSchema#getSchemaForSchema schema for schema}'s namespace,
    // and some other imported namespace.
    //
    Map<String, String> qNamePrefixToNamespaceMap = prototypeSchema./*{@link XSDSchema#getQNamePrefixToNamespaceMap */getQNamePrefixToNamespaceMap/*}*/();
    qNamePrefixToNamespaceMap.put("PTS", prototypeSchema.getTargetNamespace());
    qNamePrefixToNamespaceMap.put(prototypeSchema.getSchemaForSchemaQNamePrefix(), /*{@link */XSDConstants/*}*/.SCHEMA_FOR_SCHEMA_URI_2001);
    qNamePrefixToNamespaceMap.put("EXT", /*{@link #*/someOtherSchemaURI/*}*/);

    // Initialize the other parts of this examples.
    //
    /*{@link #*/initializeSomeOtherImport()/*}*/;
    /*{@link #*/initializeSimpleAttributeGroupDefinition()/*}*/;
    /*{@link #*/initializeSimpleRecursiveModelGroupDefinition()/*}*/;
    /*{@link #*/initializeSimpleRecursiveComplexTypeDefinition()/*}*/;
    /*{@link #*/initializeSimpleRecursiveElementDeclaration()/*}*/;
    /*{@link #*/initializeSimpleAttributeDeclaration()/*}*/;
    /*{@link #*/initializeSimpleElementDeclarationWithAnonymousType()/*}*/;
    /*{@link #*/initializeSimpleTypeDefinition()/*}*/;
    /*{@link #*/initializeSimpleListTypeDefinition()/*}*/;
    /*{@link #*/initializeSimpleUnionTypeDefinition()/*}*/;
    /*{@link #*/initializeFancyListTypeDefinition()/*}*/;
    /*{@link #*/initializeSimpleContentComplexTypeDefinition()/*}*/;

    // This is the <!-- SAMPLE --> <a name="prototype-end">result</a> that is produced.
    //
    /// <?xml version="1.0" encoding="UTF-8"?>
    /// <xsd:schema attributeFormDefault="qualified"
    ///     elementFormDefault="qualified"
    ///     targetNamespace="http://www.example.com/PrototypicalSchema"
    ///     xmlns:EXT="http://www.example.com/SomeOtherSchema"
    ///     xmlns:PTS="http://www.example.com/PrototypicalSchema" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    ///     <xsd:import namespace="http://www.example.com/SomeOtherSchema" schemaLocation="http://www.example.com/SomeOtherSchema"/>
    ///     <xsd:attributeGroup name="simpleAttributeGroupDefinition">
    ///         <xsd:attribute default="defaultValue"
    ///             name="simpleAttributeDeclarationGroupMember" type="EXT:someOtherTypeDefinition"/>
    ///     </xsd:attributeGroup>
    ///     <xsd:group name="simpleRecursiveModelGroupDefinition">
    ///         <xsd:annotation/>
    ///         <xsd:sequence>
    ///             <xsd:element maxOccurs="unbounded" minOccurs="0" ref="PTS:simpleRecursiveElementDeclaration"/>
    ///             <xsd:any namespace="##other" processContents="lax"/>
    ///         </xsd:sequence>
    ///     </xsd:group>
    ///     <xsd:complexType abstract="false" block="#all" final="#all"
    ///         mixed="true" name="SimpleRecursiveComplexTypeDefinition">
    ///         <xsd:annotation>
    ///             <xsd:appinfo source="http://www.example.com/appinfo"/>
    ///             <xsd:documentation
    ///                 source="http://www.example.com/documentation">A simple
    ///                 recursive complex type definition.</xsd:documentation>
    ///         </xsd:annotation>
    ///         <xsd:group ref="PTS:simpleRecursiveModelGroupDefinition"/>
    ///         <xsd:attribute ref="PTS:simpleAttributeDeclaration" use="optional"/>
    ///         <xsd:attributeGroup ref="PTS:simpleAttributeGroupDefinition"/>
    ///         <xsd:anyAttribute namespace="##other"/>
    ///     </xsd:complexType>
    ///     <xsd:element name="simpleRecursiveElementDeclaration" type="PTS:SimpleRecursiveComplexTypeDefinition">
    ///         <xsd:annotation/>
    ///         <xsd:unique name="unique">
    ///             <xsd:selector xpath="simpleRecursiveElementDeclaration"/>
    ///             <xsd:field xpath="simpleAttributeDeclarationGroupMember"/>
    ///         </xsd:unique>
    ///         <xsd:key name="key">
    ///             <xsd:selector xpath="simpleRecursiveElementDeclaration"/>
    ///             <xsd:field xpath="simpleAttributeDeclarationGroupMember"/>
    ///         </xsd:key>
    ///         <xsd:keyref name="keyref" refer="PTS:key">
    ///             <xsd:selector xpath="simpleRecursiveElementDeclaration"/>
    ///             <xsd:field xpath="simpleAttributeDeclaration"/>
    ///         </xsd:keyref>
    ///     </xsd:element>
    ///     <xsd:attribute default="defaultValue"
    ///         name="simpleAttributeDeclaration" type="EXT:someOtherTypeDefinition"/>
    ///     <xsd:element fixed="12ab" name="simpleElementWithAnonymousType">
    ///         <xsd:annotation/>
    ///         <xsd:simpleType>
    ///             <xsd:restriction base="xsd:string">
    ///                 <xsd:pattern value="\d\d.."/>
    ///             </xsd:restriction>
    ///         </xsd:simpleType>
    ///     </xsd:element>
    ///     <xsd:simpleType name="SimpleTypeDefinition">
    ///         <xsd:annotation/>
    ///         <xsd:restriction base="xsd:positiveInteger">
    ///             <xsd:maxInclusive value="100"/>
    ///         </xsd:restriction>
    ///     </xsd:simpleType>
    ///     <xsd:simpleType name="SimpleListTypeDefinition">
    ///         <xsd:annotation/>
    ///         <xsd:list itemType="xsd:integer"/>
    ///     </xsd:simpleType>
    ///     <xsd:simpleType name="SimpleUnionTypeDefinition">
    ///         <xsd:annotation/>
    ///         <xsd:union memberTypes="xsd:integer"/>
    ///     </xsd:simpleType>
    ///     <xsd:simpleType name="FancyListTypeDefinition">
    ///         <xsd:restriction>
    ///             <xsd:simpleType>
    ///                 <xsd:list>
    ///                     <xsd:simpleType>
    ///                         <xsd:union>
    ///                             <xsd:simpleType>
    ///                                 <xsd:restriction base="xsd:string">
    ///                                     <xsd:enumeration value="unknown"/>
    ///                                 </xsd:restriction>
    ///                             </xsd:simpleType>
    ///                             <xsd:simpleType>
    ///                                 <xsd:restriction base="xsd:decimal">
    ///                                     <xsd:fractionDigits value="2"/>
    ///                                 </xsd:restriction>
    ///                             </xsd:simpleType>
    ///                         </xsd:union>
    ///                     </xsd:simpleType>
    ///                 </xsd:list>
    ///             </xsd:simpleType>
    ///             <xsd:maxLength value="4"/>
    ///         </xsd:restriction>
    ///     </xsd:simpleType>
    ///     <xsd:complexType name="SimpleContentComplexTypeDefinition">
    ///         <xsd:annotation/>
    ///         <xsd:simpleContent>
    ///             <xsd:extension base="xsd:string">
    ///                 <xsd:attribute ref="PTS:simpleAttributeDeclaration" use="optional"/>
    ///             </xsd:extension>
    ///         </xsd:simpleContent>
    ///     </xsd:complexType>
    /// </xsd:schema>

    return prototypeSchema;
  }
  // <!-- end-initialize-prototype-schema -->

  /**
   *<pre>
   *<!-- insert-begin-initialize-some-other-import -->
   *{
   *  // Create an import and set its namespace and schema location.
   *  //
   *  XSDImport someOtherImport = xsdFactory.{@link XSDFactory#createXSDImport createXSDImport}();
   *  someOtherImport.{@link XSDImport#setNamespace setNamespace}({@link #someOtherSchemaURI});
   *  someOtherImport.{@link XSDImport#setSchemaLocation setSchemaLocation}(someOtherSchemaURI);
   *
   *  // Add the import to the schema contents.
   *  //
   *  prototypeSchema.{@link XSDSchema#getContents getContents}().add(someOtherImport);
   *
   *  // This is the <!-- SAMPLE --> result that is produced.
   *  //
   *  //<b>     &lt;xsd:import namespace="http://www.example.com/SomeOtherSchema" schemaLocation="http://www.example.com/SomeOtherSchema"/></b>
   *}
   *<!-- insert-end-initialize-some-other-import -->
   *</pre>
   */
  public void initializeSomeOtherImport()
  // <!-- begin-initialize-some-other-import -->
  {
    // Create an import and set its namespace and schema location.
    //
    XSDImport someOtherImport = xsdFactory./*{@link XSDFactory#createXSDImport */createXSDImport/*}*/();
    someOtherImport./*{@link XSDImport#setNamespace */setNamespace/*}*/(/*{@link #*/someOtherSchemaURI/*}*/);
    someOtherImport./*{@link XSDImport#setSchemaLocation */setSchemaLocation/*}*/(someOtherSchemaURI);

    // Add the import to the schema contents.
    //
    prototypeSchema./*{@link XSDSchema#getContents */getContents/*}*/().add(someOtherImport);

    // This is the <!-- SAMPLE --> result that is produced.
    //
    ///     <xsd:import namespace="http://www.example.com/SomeOtherSchema" schemaLocation="http://www.example.com/SomeOtherSchema"/>
  }
  // <!-- end-initialize-some-other-import -->

  /**
   *<pre>
   *<!-- insert-begin-initialize-simple-attribute-group-definition -->
   *{
   *  // Create an attribute group definition and set it's name to simpleAttributeGroupDefinition.
   *  //
   *  {@link XSDAttributeGroupDefinition} simpleAttributeGroupDefinition = xsdFactory.createXSDAttributeGroupDefinition();
   *  simpleAttributeGroupDefinition.{@link org.eclipse.xsd.XSDNamedComponent#getName setName}("simpleAttributeGroupDefinition");
   *
   *  // Create an attribute, name it simpleAttributeDeclarationGroupMember, 
   *  // set it's type to someOtherTypeDefinition in some other schema.
   *  // set its constraints to be default, set the lexical value of the constraint to be "defaultValue".
   *  //
   *  {@link XSDAttributeDeclaration} simpleAttributeDeclarationGroupMember = xsdFactory.createXSDAttributeDeclaration();
   *  simpleAttributeDeclarationGroupMember.{@link org.eclipse.xsd.XSDNamedComponent#setName setName}("simpleAttributeDeclarationGroupMember");
   *  simpleAttributeDeclarationGroupMember.{@link org.eclipse.xsd.XSDAttributeDeclaration#setTypeDefinition setTypeDefinition}
   *    (prototypeSchema.{@link XSDConcreteComponent#resolveSimpleTypeDefinition(String, String) resolveSimpleTypeDefinition}({@link #someOtherSchemaURI}, "someOtherTypeDefinition"));
   *
   *  // Create an attribute use to contain the attribute and add it the attribute group.
   *  //
   *  {@link XSDAttributeUse} simpleAttributeUseGroupMember = xsdFactory.createXSDAttributeUse();
   *  simpleAttributeUseGroupMember.{@link XSDAttributeUse#setContent setContent}(simpleAttributeDeclarationGroupMember);
   *  simpleAttributeUseGroupMember.{@link XSDAttributeDeclaration#setConstraint setConstraint}({@link XSDConstraint}.DEFAULT_LITERAL);
   *  simpleAttributeUseGroupMember.{@link XSDAttributeDeclaration#setLexicalValue setLexicalValue}("defaultValue");
   *  simpleAttributeGroupDefinition.{@link XSDAttributeGroupDefinition#getContents getContents}().add(simpleAttributeUseGroupMember);
   *
   *  // Add the attribute group to the schema.
   *  //
   *  prototypeSchema.{@link XSDSchema#getContents getContents}().add(simpleAttributeGroupDefinition);
   *
   *  // This is the <!-- SAMPLE --> result that is produced.
   *  //
   *  //<b>     &lt;xsd:attributeGroup name="simpleAttributeGroupDefinition"></b>
   *  //<b>         &lt;xsd:attribute default="defaultValue"</b>
   *  //<b>             name="simpleAttributeDeclarationGroupMember" type="EXT:someOtherTypeDefinition"/></b>
   *  //<b>     &lt;/xsd:attributeGroup></b>
   *}
   *<!-- insert-end-initialize-simple-attribute-group-definition -->
   *</pre>
   */
  public void initializeSimpleAttributeGroupDefinition()
  // <!-- begin-initialize-simple-attribute-group-definition -->
  {
    // Create an attribute group definition and set it's name to simpleAttributeGroupDefinition.
    //
    /*{@link */XSDAttributeGroupDefinition/*}*/ simpleAttributeGroupDefinition = xsdFactory.createXSDAttributeGroupDefinition();
    simpleAttributeGroupDefinition./*{@link XSDNamedComponent#getName */setName/*}*/("simpleAttributeGroupDefinition");

    // Create an attribute, name it simpleAttributeDeclarationGroupMember, 
    // set it's type to someOtherTypeDefinition in some other schema.
    // set its constraints to be default, set the lexical value of the constraint to be "defaultValue".
    //
    /*{@link */XSDAttributeDeclaration/*}*/ simpleAttributeDeclarationGroupMember = xsdFactory.createXSDAttributeDeclaration();
    simpleAttributeDeclarationGroupMember./*{@link XSDNamedComponent#setName */setName/*}*/("simpleAttributeDeclarationGroupMember");
    simpleAttributeDeclarationGroupMember./*{@link XSDAttributeDeclaration#setTypeDefinition */setTypeDefinition/*}*/
      (prototypeSchema./*{@link XSDConcreteComponent#resolveSimpleTypeDefinition(String, String) */resolveSimpleTypeDefinition/*}*/(/*{@link #*/someOtherSchemaURI/*}*/, "someOtherTypeDefinition"));

    // Create an attribute use to contain the attribute and add it the attribute group.
    //
    /*{@link */XSDAttributeUse/*}*/ simpleAttributeUseGroupMember = xsdFactory.createXSDAttributeUse();
    simpleAttributeUseGroupMember./*{@link XSDAttributeUse#setContent */setContent/*}*/(simpleAttributeDeclarationGroupMember);
    simpleAttributeUseGroupMember./*{@link XSDAttributeDeclaration#setConstraint */setConstraint/*}*/(/*{@link */XSDConstraint/*}*/.DEFAULT_LITERAL);
    simpleAttributeUseGroupMember./*{@link XSDAttributeDeclaration#setLexicalValue */setLexicalValue/*}*/("defaultValue");
    simpleAttributeGroupDefinition./*{@link XSDAttributeGroupDefinition#getContents */getContents/*}*/().add(simpleAttributeUseGroupMember);

    // Add the attribute group to the schema.
    //
    prototypeSchema./*{@link XSDSchema#getContents */getContents/*}*/().add(simpleAttributeGroupDefinition);

    // This is the <!-- SAMPLE --> result that is produced.
    //
    ///     <xsd:attributeGroup name="simpleAttributeGroupDefinition">
    ///         <xsd:attribute default="defaultValue"
    ///             name="simpleAttributeDeclarationGroupMember" type="EXT:someOtherTypeDefinition"/>
    ///     </xsd:attributeGroup>
  }
  // <!-- end-initialize-simple-attribute-group-definition -->

  /**
   *<pre>
   *<!-- insert-begin-initialize-simple-recursive-model-group-definition -->
   *{
   *  // Create a model group definition and name it simpleRecursiveModelGroupDefinition.
   *  //
   *  {@link XSDModelGroupDefinition} simpleRecursiveModelGroupDefinition = xsdFactory.createXSDModelGroupDefinition();
   *  simpleRecursiveModelGroupDefinition.{@link org.eclipse.xsd.XSDNamedComponent#setName setName}("simpleRecursiveModelGroupDefinition");
   *
   *  // Create an annotation placeholder and add it to the model group definition.
   *  //
   *  XSDAnnotation annotation = xsdFactory.createXSDAnnotation();
   *  simpleRecursiveModelGroupDefinition.setAnnotation(annotation);
   *
   *  // Create a sequence model group.
   *  //
   *  {@link XSDModelGroup} modelGroup = xsdFactory.createXSDModelGroup();
   *  modelGroup.{@link XSDModelGroup#setCompositor(XSDCompositor) setCompositor}(XSDCompositor.SEQUENCE_LITERAL);
   *
   *  // Create an element reference to the simpleRecursiveElementDeclaration of this schema.
   *  //
   *  {@link XSDElementDeclaration} simpleRecursiveElementDeclarationReference = xsdFactory.createXSDElementDeclaration();
   *  simpleRecursiveElementDeclarationReference.{@link XSDElementDeclaration#setResolvedElementDeclaration setResolvedElementDeclaration}
   *    (prototypeSchema.{@link XSDConcreteComponent#resolveElementDeclaration(String) resolveElementDeclaration}("simpleRecursiveElementDeclaration"));
   *
   *  // Create a particle to hold the element and set it to be optional and unbounded.
   *  //
   *  {@link XSDParticle} simpleRecursiveElementParticle = xsdFactory.createXSDParticle();
   *  simpleRecursiveElementParticle.{@link XSDParticle#setContent setContent}(simpleRecursiveElementDeclarationReference);
   *  simpleRecursiveElementParticle.{@link XSDParticle#setMinOccurs(int) setMinOccurs}(0);
   *  simpleRecursiveElementParticle.{@link XSDParticle#setMaxOccurs(int) setMaxOccurs}(-1);
   *
   *  // Add the element particle to the model group.
   *  //
   *  modelGroup.{@link XSDModelGroup#getContents getContents}().add(simpleRecursiveElementParticle);
   *
   *  // Create an element wildcard, set the namespace constraint to be other, and the processing to be lax.
   *  //
   *  {@link XSDWildcard} elementWildcard = xsdFactory.createXSDWildcard();
   *  elementWildcard.{@link XSDWildcard#getLexicalNamespaceConstraint getLexicalNamespaceConstraint}().add("##other");
   *  elementWildcard.{@link XSDWildcard#setProcessContents setProcessContents}(XSDProcessContents.LAX_LITERAL);
   *
   *  // Create a particle to hold the wildcard.
   *  //
   *  XSDParticle wildcardParticle = xsdFactory.createXSDParticle();
   *  wildcardParticle.setContent(elementWildcard);
   *
   *  // Add the wildcard particle to the model group.
   *  //
   *  modelGroup.getContents().add(wildcardParticle);
   *
   *  // Set the model group to be that of the simpleRecursiveModelGroupDefinition.
   *  //
   *  simpleRecursiveModelGroupDefinition.{@link XSDModelGroupDefinition#setModelGroup setModelGroup}(modelGroup);
   *
   *  // Add simpleRecursiveModelGroupDefinition to the schema.
   *  //
   *  prototypeSchema.{@link XSDSchema#getContents getContents}().add(simpleRecursiveModelGroupDefinition);
   *
   *  // This is the <!-- SAMPLE --> result that is produced.
   *  //
   *  //<b>     &lt;xsd:group name="simpleRecursiveModelGroupDefinition"></b>
   *  //<b>         &lt;xsd:annotation/></b>
   *  //<b>         &lt;xsd:sequence></b>
   *  //<b>             &lt;xsd:element maxOccurs="unbounded" minOccurs="0" ref="PTS:simpleRecursiveElementDeclaration"/></b>
   *  //<b>             &lt;xsd:any namespace="##other" processContents="lax"/></b>
   *  //<b>         &lt;/xsd:sequence></b>
   *  //<b>     &lt;/xsd:group></b>
   *}
   *<!-- insert-end-initialize-simple-recursive-model-group-definition -->
   *</pre>
   */
  public void initializeSimpleRecursiveModelGroupDefinition()
  // <!-- begin-initialize-simple-recursive-model-group-definition -->
  {
    // Create a model group definition and name it simpleRecursiveModelGroupDefinition.
    //
    /*{@link */XSDModelGroupDefinition/*}*/ simpleRecursiveModelGroupDefinition = xsdFactory.createXSDModelGroupDefinition();
    simpleRecursiveModelGroupDefinition./*{@link XSDNamedComponent#setName */setName/*}*/("simpleRecursiveModelGroupDefinition");

    // Create an annotation placeholder and add it to the model group definition.
    //
    XSDAnnotation annotation = xsdFactory.createXSDAnnotation();
    simpleRecursiveModelGroupDefinition.setAnnotation(annotation);

    // Create a sequence model group.
    //
    /*{@link */XSDModelGroup/*}*/ modelGroup = xsdFactory.createXSDModelGroup();
    modelGroup./*{@link XSDModelGroup#setCompositor(XSDCompositor) */setCompositor/*}*/(XSDCompositor.SEQUENCE_LITERAL);

    // Create an element reference to the simpleRecursiveElementDeclaration of this schema.
    //
    /*{@link */XSDElementDeclaration/*}*/ simpleRecursiveElementDeclarationReference = xsdFactory.createXSDElementDeclaration();
    simpleRecursiveElementDeclarationReference./*{@link XSDElementDeclaration#setResolvedElementDeclaration */setResolvedElementDeclaration/*}*/
      (prototypeSchema./*{@link XSDConcreteComponent#resolveElementDeclaration(String) */resolveElementDeclaration/*}*/("simpleRecursiveElementDeclaration"));

    // Create a particle to hold the element and set it to be optional and unbounded.
    //
    /*{@link */XSDParticle/*}*/ simpleRecursiveElementParticle = xsdFactory.createXSDParticle();
    simpleRecursiveElementParticle./*{@link XSDParticle#setContent */setContent/*}*/(simpleRecursiveElementDeclarationReference);
    simpleRecursiveElementParticle./*{@link XSDParticle#setMinOccurs(int) */setMinOccurs/*}*/(0);
    simpleRecursiveElementParticle./*{@link XSDParticle#setMaxOccurs(int) */setMaxOccurs/*}*/(-1);

    // Add the element particle to the model group.
    //
    modelGroup./*{@link XSDModelGroup#getContents */getContents/*}*/().add(simpleRecursiveElementParticle);

    // Create an element wildcard, set the namespace constraint to be other, and the processing to be lax.
    //
    /*{@link */XSDWildcard/*}*/ elementWildcard = xsdFactory.createXSDWildcard();
    elementWildcard./*{@link XSDWildcard#getLexicalNamespaceConstraint */getLexicalNamespaceConstraint/*}*/().add("##other");
    elementWildcard./*{@link XSDWildcard#setProcessContents */setProcessContents/*}*/(XSDProcessContents.LAX_LITERAL);

    // Create a particle to hold the wildcard.
    //
    XSDParticle wildcardParticle = xsdFactory.createXSDParticle();
    wildcardParticle.setContent(elementWildcard);

    // Add the wildcard particle to the model group.
    //
    modelGroup.getContents().add(wildcardParticle);

    // Set the model group to be that of the simpleRecursiveModelGroupDefinition.
    //
    simpleRecursiveModelGroupDefinition./*{@link XSDModelGroupDefinition#setModelGroup */setModelGroup/*}*/(modelGroup);

    // Add simpleRecursiveModelGroupDefinition to the schema.
    //
    prototypeSchema./*{@link XSDSchema#getContents */getContents/*}*/().add(simpleRecursiveModelGroupDefinition);

    // This is the <!-- SAMPLE --> result that is produced.
    //
    ///     <xsd:group name="simpleRecursiveModelGroupDefinition">
    ///         <xsd:annotation/>
    ///         <xsd:sequence>
    ///             <xsd:element maxOccurs="unbounded" minOccurs="0" ref="PTS:simpleRecursiveElementDeclaration"/>
    ///             <xsd:any namespace="##other" processContents="lax"/>
    ///         </xsd:sequence>
    ///     </xsd:group>
  }
  // <!-- end-initialize-simple-recursive-model-group-definition -->


  /**
   *<pre>
   *<!-- insert-begin-initialize-simple-recursive-complex-type-definition -->
   *{
   *  // Create a complex type definition and set its name to SimpleRecursiveComplexTypeDefinition.
   *  // Set the complex type to be abstract, block all substitution, make it final in all regards, 
   *  // set it to be a restriction, and set it's content type to be mixed.
   *  //
   *  {@link XSDComplexTypeDefinition} simpleRecursiveComplexTypeDefinition = xsdFactory.createXSDComplexTypeDefinition();
   *  simpleRecursiveComplexTypeDefinition.{@link org.eclipse.xsd.XSDNamedComponent#getName setName}("SimpleRecursiveComplexTypeDefinition");
   *  simpleRecursiveComplexTypeDefinition.{@link XSDComplexTypeDefinition#setAbstract setAbstract}(false);
   *  simpleRecursiveComplexTypeDefinition.{@link XSDComplexTypeDefinition#getBlock getBlock}().add({@link XSDProhibitedSubstitutions}.ALL_LITERAL);
   *  simpleRecursiveComplexTypeDefinition.{@link XSDComplexTypeDefinition#getLexicalFinal getLexicalFinal}().add({@link XSDComplexFinal}.ALL_LITERAL);
   *  simpleRecursiveComplexTypeDefinition.{@link XSDComplexTypeDefinition#setDerivationMethod setDerivationMethod}({@link XSDDerivationMethod}.RESTRICTION_LITERAL);
   *  simpleRecursiveComplexTypeDefinition.{@link XSDComplexTypeDefinition#setMixed setMixed}(true);
   *
   *  // Create an annotation placeholder and add it to the complex type
   *  //
   *  {@link XSDAnnotation} annotation = xsdFactory.createXSDAnnotation();
   *  simpleRecursiveComplexTypeDefinition.{@link XSDComplexTypeDefinition#setAnnotation setAnnotation}(annotation);
   *
   *  // Create a model group definition reference to simpleRecursiveModelGroupDefinition.
   *  // Also create particle to hold it and set the particle to be the content of the complex type.
   *  //
   *  {@link XSDModelGroupDefinition} simpleRecursiveModelGroupDefinitionReference = xsdFactory.createXSDModelGroupDefinition();
   *  simpleRecursiveModelGroupDefinitionReference.{@link XSDModelGroupDefinition#setResolvedModelGroupDefinition setResolvedModelGroupDefinition}
   *    (prototypeSchema.{@link XSDConcreteComponent#resolveModelGroupDefinition(String) resolveModelGroupDefinition}("simpleRecursiveModelGroupDefinition"));
   *  {@link XSDParticle} modelGroupParticle = xsdFactory.createXSDParticle();
   *  modelGroupParticle.{@link XSDParticle#setContent setContent}(simpleRecursiveModelGroupDefinitionReference);
   *  simpleRecursiveComplexTypeDefinition.{@link XSDComplexTypeDefinition#setContent setContent}(modelGroupParticle);
   *
   *  // Create an attribute reference to simpleAttributeDeclaration.
   *  // Also create an attribute use to hold it, set the use to be optional, 
   *  // and add it to the complex type's attribute contents.
   *  //
   *  {@link XSDAttributeDeclaration} simpleAttributeDeclarationReference = xsdFactory.createXSDAttributeDeclaration();
   *  simpleAttributeDeclarationReference.{@link XSDAttributeDeclaration#setResolvedAttributeDeclaration setResolvedAttributeDeclaration}
   *    (prototypeSchema.{@link XSDConcreteComponent#resolveAttributeDeclaration resolveAttributeDeclaration}("simpleAttributeDeclaration"));
   *  {@link XSDAttributeUse} simpleAttributeUse = xsdFactory.createXSDAttributeUse();
   *  simpleAttributeUse.{@link XSDAttributeUse#setContent setContent}(simpleAttributeDeclarationReference);
   *  simpleAttributeUse.{@link XSDAttributeUse#setUse setUse}({@link XSDAttributeUseCategory}.OPTIONAL_LITERAL);
   *  simpleRecursiveComplexTypeDefinition.{@link XSDComplexTypeDefinition#getAttributeContents getAttributeContents}().add(simpleAttributeUse);
   *
   *  // Create an attribute group reference to simpleAttributeGroupDefinition.
   *  // And add it to the complex type's attribute contents.
   *  //
   *  {@link XSDAttributeGroupDefinition} simpleAttributeGroupDefinitionReference = xsdFactory.createXSDAttributeGroupDefinition();
   *  simpleAttributeGroupDefinitionReference.{@link XSDAttributeGroupDefinition#setResolvedAttributeGroupDefinition setResolvedAttributeGroupDefinition}
   *    (prototypeSchema.{@link XSDConcreteComponent#resolveAttributeGroupDefinition(String) resolveAttributeGroupDefinition}("simpleAttributeGroupDefinition"));
   *  simpleRecursiveComplexTypeDefinition.getAttributeContents().add(simpleAttributeGroupDefinitionReference);
   *
   *  // Create an attribute wildcard and set its constraint to other.
   *  // Also add it as the complex type's attribute wildcard.
   *  //
   *  {@link XSDWildcard} attributeWildcard = xsdFactory.createXSDWildcard();
   *  attributeWildcard.{@link XSDWildcard#getLexicalNamespaceConstraint getLexicalNamespaceConstraint}().add("##other");
   *  simpleRecursiveComplexTypeDefinition.{@link XSDComplexTypeDefinition#getAttributeWildcardContent setAttributeWildcardContent}(attributeWildcard);
   *
   *  prototypeSchema.getContents().add(simpleRecursiveComplexTypeDefinition);
   *
   *  // Create an appinfo DOM element with the given sourceURI attribute.
   *  // Also add the element as the annotation's child.
   *  // <b>NOTE</b>
   *  // Working with the contents of an annotation requires dropping down into the DOM model.
   *  // This imposes the additional requirement that the annotation must be {@link XSDSchema#getSchema attached} to a schema.
   *  // It also has the effect of calling {@link XSDSchema#updateElement()},
   *  // if the schema does not have an {@link XSDConcreteComponent#getElement element} or {@link XSDSchema#getDocument document} yet.
   *  //
   *  {@link Element <em>Element</em>} appinfo = annotation.{@link XSDAnnotation#createApplicationInformation createApplicationInformation}("http://www.example.com/appinfo");
   *  annotation.{@link XSDConcreteComponent#getElement getElement}().{@link org.w3c.dom.Node#appendChild <em>appendChild</em>}(appinfo);
   *
   *  // Create a documentation DOM element with the given sourceURI attribute.
   *  // Also add the element as the annotation's child.
   *  //
   *  Element documentation = annotation.{@link XSDAnnotation#createUserInformation createUserInformation}("http://www.example.com/documentation");
   *  annotation.getElement().appendChild(documentation);
   *  documentation.{@link org.w3c.dom.Node#appendChild <em>appendChild</em>}
   *    (documentation.getOwnerDocument().createTextNode
   *      ("A simple recursive complex type definition."));
   *
   *  // This is the <!-- SAMPLE --> result that is produced.
   *  //
   *  //<b>     &lt;xsd:complexType abstract="false" block="#all" final="#all"</b>
   *  //<b>         mixed="true" name="SimpleRecursiveComplexTypeDefinition"></b>
   *  //<b>         &lt;xsd:annotation></b>
   *  //<b>             &lt;xsd:appinfo source="http://www.example.com/appinfo"/></b>
   *  //<b>             &lt;xsd:documentation</b>
   *  //<b>                 source="http://www.example.com/documentation">A simple</b>
   *  //<b>                 recursive complex type definition.&lt;/xsd:documentation></b>
   *  //<b>         &lt;/xsd:annotation></b>
   *  //<b>         &lt;xsd:group ref="PTS:simpleRecursiveModelGroupDefinition"/></b>
   *  //<b>         &lt;xsd:attribute ref="PTS:simpleAttributeDeclaration" use="optional"/></b>
   *  //<b>         &lt;xsd:attributeGroup ref="PTS:simpleAttributeGroupDefinition"/></b>
   *  //<b>         &lt;xsd:anyAttribute namespace="##other"/></b>
   *  //<b>     &lt;/xsd:complexType></b>
   *}
   *<!-- insert-end-initialize-simple-recursive-complex-type-definition -->
   *</pre>
   */
  public void initializeSimpleRecursiveComplexTypeDefinition()
  // <!-- begin-initialize-simple-recursive-complex-type-definition -->
  {
    // Create a complex type definition and set its name to SimpleRecursiveComplexTypeDefinition.
    // Set the complex type to be abstract, block all substitution, make it final in all regards, 
    // set it to be a restriction, and set it's content type to be mixed.
    //
    /*{@link */XSDComplexTypeDefinition/*}*/ simpleRecursiveComplexTypeDefinition = xsdFactory.createXSDComplexTypeDefinition();
    simpleRecursiveComplexTypeDefinition./*{@link XSDNamedComponent#getName */setName/*}*/("SimpleRecursiveComplexTypeDefinition");
    simpleRecursiveComplexTypeDefinition./*{@link XSDComplexTypeDefinition#setAbstract */setAbstract/*}*/(false);
    simpleRecursiveComplexTypeDefinition./*{@link XSDComplexTypeDefinition#getBlock */getBlock/*}*/().add(/*{@link */XSDProhibitedSubstitutions/*}*/.ALL_LITERAL);
    simpleRecursiveComplexTypeDefinition./*{@link XSDComplexTypeDefinition#getLexicalFinal */getLexicalFinal/*}*/().add(/*{@link */XSDComplexFinal/*}*/.ALL_LITERAL);
    simpleRecursiveComplexTypeDefinition./*{@link XSDComplexTypeDefinition#setDerivationMethod */setDerivationMethod/*}*/(/*{@link */XSDDerivationMethod/*}*/.RESTRICTION_LITERAL);
    simpleRecursiveComplexTypeDefinition./*{@link XSDComplexTypeDefinition#setMixed */setMixed/*}*/(true);

    // Create an annotation placeholder and add it to the complex type
    //
    /*{@link */XSDAnnotation/*}*/ annotation = xsdFactory.createXSDAnnotation();
    simpleRecursiveComplexTypeDefinition./*{@link XSDComplexTypeDefinition#setAnnotation */setAnnotation/*}*/(annotation);

    // Create a model group definition reference to simpleRecursiveModelGroupDefinition.
    // Also create particle to hold it and set the particle to be the content of the complex type.
    //
    /*{@link */XSDModelGroupDefinition/*}*/ simpleRecursiveModelGroupDefinitionReference = xsdFactory.createXSDModelGroupDefinition();
    simpleRecursiveModelGroupDefinitionReference./*{@link XSDModelGroupDefinition#setResolvedModelGroupDefinition */setResolvedModelGroupDefinition/*}*/
      (prototypeSchema./*{@link XSDConcreteComponent#resolveModelGroupDefinition(String) */resolveModelGroupDefinition/*}*/("simpleRecursiveModelGroupDefinition"));
    /*{@link */XSDParticle/*}*/ modelGroupParticle = xsdFactory.createXSDParticle();
    modelGroupParticle./*{@link XSDParticle#setContent */setContent/*}*/(simpleRecursiveModelGroupDefinitionReference);
    simpleRecursiveComplexTypeDefinition./*{@link XSDComplexTypeDefinition#setContent */setContent/*}*/(modelGroupParticle);

    // Create an attribute reference to simpleAttributeDeclaration.
    // Also create an attribute use to hold it, set the use to be optional, 
    // and add it to the complex type's attribute contents.
    //
    /*{@link */XSDAttributeDeclaration/*}*/ simpleAttributeDeclarationReference = xsdFactory.createXSDAttributeDeclaration();
    simpleAttributeDeclarationReference./*{@link XSDAttributeDeclaration#setResolvedAttributeDeclaration */setResolvedAttributeDeclaration/*}*/
      (prototypeSchema./*{@link XSDConcreteComponent#resolveAttributeDeclaration */resolveAttributeDeclaration/*}*/("simpleAttributeDeclaration"));
    /*{@link */XSDAttributeUse/*}*/ simpleAttributeUse = xsdFactory.createXSDAttributeUse();
    simpleAttributeUse./*{@link XSDAttributeUse#setContent */setContent/*}*/(simpleAttributeDeclarationReference);
    simpleAttributeUse./*{@link XSDAttributeUse#setUse */setUse/*}*/(/*{@link */XSDAttributeUseCategory/*}*/.OPTIONAL_LITERAL);
    simpleRecursiveComplexTypeDefinition./*{@link XSDComplexTypeDefinition#getAttributeContents */getAttributeContents/*}*/().add(simpleAttributeUse);

    // Create an attribute group reference to simpleAttributeGroupDefinition.
    // And add it to the complex type's attribute contents.
    //
    /*{@link */XSDAttributeGroupDefinition/*}*/ simpleAttributeGroupDefinitionReference = xsdFactory.createXSDAttributeGroupDefinition();
    simpleAttributeGroupDefinitionReference./*{@link XSDAttributeGroupDefinition#setResolvedAttributeGroupDefinition */setResolvedAttributeGroupDefinition/*}*/
      (prototypeSchema./*{@link XSDConcreteComponent#resolveAttributeGroupDefinition(String) */resolveAttributeGroupDefinition/*}*/("simpleAttributeGroupDefinition"));
    simpleRecursiveComplexTypeDefinition.getAttributeContents().add(simpleAttributeGroupDefinitionReference);

    // Create an attribute wildcard and set its constraint to other.
    // Also add it as the complex type's attribute wildcard.
    //
    /*{@link */XSDWildcard/*}*/ attributeWildcard = xsdFactory.createXSDWildcard();
    attributeWildcard./*{@link XSDWildcard#getLexicalNamespaceConstraint */getLexicalNamespaceConstraint/*}*/().add("##other");
    simpleRecursiveComplexTypeDefinition./*{@link XSDComplexTypeDefinition#getAttributeWildcardContent */setAttributeWildcardContent/*}*/(attributeWildcard);

    prototypeSchema.getContents().add(simpleRecursiveComplexTypeDefinition);

    // Create an appinfo DOM element with the given sourceURI attribute.
    // Also add the element as the annotation's child.
    // <b>NOTE</b>
    // Working with the contents of an annotation requires dropping down into the DOM model.
    // This imposes the additional requirement that the annotation must be {@link XSDSchema#getSchema attached} to a schema.
    // It also has the effect of calling {@link XSDSchema#updateElement()},
    // if the schema does not have an {@link XSDConcreteComponent#getElement element} or {@link XSDSchema#getDocument document} yet.
    //
    /*{@link */Element/* <em>Element</em>}*/ appinfo = annotation./*{@link XSDAnnotation#createApplicationInformation */createApplicationInformation/*}*/("http://www.example.com/appinfo");
    annotation./*{@link XSDConcreteComponent#getElement */getElement/*}*/()./*{@link Element#appendChild <em>*/appendChild/*</em>}*/(appinfo);

    // Create a documentation DOM element with the given sourceURI attribute.
    // Also add the element as the annotation's child.
    //
    Element documentation = annotation./*{@link XSDAnnotation#createUserInformation */createUserInformation/*}*/("http://www.example.com/documentation");
    annotation.getElement().appendChild(documentation);
    documentation./*{@link Element#appendChild <em>*/appendChild/*</em>}*/
      (documentation.getOwnerDocument().createTextNode
        ("A simple recursive complex type definition."));

    // This is the <!-- SAMPLE --> result that is produced.
    //
    ///     <xsd:complexType abstract="false" block="#all" final="#all"
    ///         mixed="true" name="SimpleRecursiveComplexTypeDefinition">
    ///         <xsd:annotation>
    ///             <xsd:appinfo source="http://www.example.com/appinfo"/>
    ///             <xsd:documentation
    ///                 source="http://www.example.com/documentation">A simple
    ///                 recursive complex type definition.</xsd:documentation>
    ///         </xsd:annotation>
    ///         <xsd:group ref="PTS:simpleRecursiveModelGroupDefinition"/>
    ///         <xsd:attribute ref="PTS:simpleAttributeDeclaration" use="optional"/>
    ///         <xsd:attributeGroup ref="PTS:simpleAttributeGroupDefinition"/>
    ///         <xsd:anyAttribute namespace="##other"/>
    ///     </xsd:complexType>
  }
  // <!-- end-initialize-simple-recursive-complex-type-definition -->

  /**
   *<pre>
   *<!-- insert-begin-initialize-simple-recursive-element-declaration -->
   *{
   *  // Create an element declaration and name it simpleRecursiveElementDeclaration.
   *  //
   *  {@link XSDElementDeclaration} simpleRecursiveElementDeclaration = xsdFactory.createXSDElementDeclaration();
   *  simpleRecursiveElementDeclaration.{@link org.eclipse.xsd.XSDNamedComponent#setName setName}("simpleRecursiveElementDeclaration");
   *
   *  // Create an annotation placeholder and set it to the element.
   *  //
   *  {@link XSDAnnotation} annotation = xsdFactory.createXSDAnnotation();
   *  simpleRecursiveElementDeclaration.{@link XSDElementDeclaration#setAnnotation setAnnotation}(annotation);
   *
   *  // Set the element type definition to complex SimpleRecursiveComplexTypeDefinition from this schema.
   *  //
   *  simpleRecursiveElementDeclaration.{@link XSDElementDeclaration#setTypeDefinition setTypeDefinition}
   *    (prototypeSchema.{@link XSDConcreteComponent#resolveComplexTypeDefinition(String) resolveComplexTypeDefinition}("SimpleRecursiveComplexTypeDefinition"));
   *
   *  // Create a unique identity constraint called unique.
   *  //
   *  XSDIdentityConstraintDefinition unique = xsdFactory.{@link XSDFactory#createXSDIdentityConstraintDefinition createXSDIdentityConstraintDefinition}();
   *  unique.{@link XSDIdentityConstraintDefinition#setIdentityConstraintCategory setIdentityConstraintCategory}({@link XSDIdentityConstraintCategory}.UNIQUE_LITERAL);
   *  unique.setName("unique");
   *  XSDXPathDefinition uniqueSelector = xsdFactory.{@link XSDFactory#createXSDXPathDefinition createXSDXPathDefinition}();
   *  uniqueSelector.{@link XSDXPathDefinition#setVariety setVariety}({@link XSDXPathVariety}.SELECTOR_LITERAL);
   *  uniqueSelector.{@link XSDXPathDefinition#setValue setValue}("simpleRecursiveElementDeclaration");
   *  unique.{@link XSDIdentityConstraintDefinition#setSelector setSelector}(uniqueSelector);
   *  XSDXPathDefinition uniqueField = xsdFactory.createXSDXPathDefinition();
   *  uniqueField.setVariety(XSDXPathVariety.FIELD_LITERAL);
   *  uniqueField.setValue("simpleAttributeDeclarationGroupMember");
   *  unique.{@link XSDIdentityConstraintDefinition#getFields getFields}().add(uniqueField);
   *  simpleRecursiveElementDeclaration.{@link XSDElementDeclaration#getIdentityConstraintDefinitions getIdentityConstraintDefinitions}().add(unique);
   *
   *  // Create a key identity constraint called key.
   *  //
   *  XSDIdentityConstraintDefinition key = xsdFactory.createXSDIdentityConstraintDefinition();
   *  key.setIdentityConstraintCategory(XSDIdentityConstraintCategory.KEY_LITERAL);
   *  key.setName("key");
   *  XSDXPathDefinition keySelector = xsdFactory.createXSDXPathDefinition();
   *  keySelector.setVariety(XSDXPathVariety.SELECTOR_LITERAL);
   *  keySelector.setValue("simpleRecursiveElementDeclaration");
   *  key.setSelector(keySelector);
   *  XSDXPathDefinition keyField = xsdFactory.createXSDXPathDefinition();
   *  keyField.setVariety(XSDXPathVariety.FIELD_LITERAL);
   *  keyField.setValue("simpleAttributeDeclarationGroupMember");
   *  key.getFields().add(keyField);
   *  simpleRecursiveElementDeclaration.getIdentityConstraintDefinitions().add(key);
   *
   *  // Create a keyref identity constraint called keyref that references key.
   *  //
   *  XSDIdentityConstraintDefinition keyref = xsdFactory.createXSDIdentityConstraintDefinition();
   *  keyref.setIdentityConstraintCategory(XSDIdentityConstraintCategory.KEYREF_LITERAL);
   *  keyref.setName("keyref");
   *  XSDXPathDefinition keyrefSelector = xsdFactory.createXSDXPathDefinition();
   *  keyrefSelector.setVariety(XSDXPathVariety.SELECTOR_LITERAL);
   *  keyrefSelector.setValue("simpleRecursiveElementDeclaration");
   *  keyref.setSelector(keyrefSelector);
   *  XSDXPathDefinition keyrefField = xsdFactory.createXSDXPathDefinition();
   *  keyrefField.setVariety(XSDXPathVariety.FIELD_LITERAL);
   *  keyrefField.setValue("simpleAttributeDeclaration");
   *  keyref.{@link XSDIdentityConstraintDefinition#setReferencedKey setReferencedKey}(key);
   *  keyref.getFields().add(keyrefField);
   *  simpleRecursiveElementDeclaration.getIdentityConstraintDefinitions().add(keyref);
   *
   *  // Add the element to the schema.
   *  // 
   *  prototypeSchema.{@link XSDSchema#getContents getContents}().add(simpleRecursiveElementDeclaration);
   *
   *  // This is the <!-- SAMPLE --> result that is produced.
   *  //
   *  //<b>     &lt;xsd:element name="simpleRecursiveElementDeclaration" type="PTS:SimpleRecursiveComplexTypeDefinition"></b>
   *  //<b>         &lt;xsd:annotation/></b>
   *  //<b>         &lt;xsd:unique name="unique"></b>
   *  //<b>             &lt;xsd:selector xpath="simpleRecursiveElementDeclaration"/></b>
   *  //<b>             &lt;xsd:field xpath="simpleAttributeDeclarationGroupMember"/></b>
   *  //<b>         &lt;/xsd:unique></b>
   *  //<b>         &lt;xsd:key name="key"></b>
   *  //<b>             &lt;xsd:selector xpath="simpleRecursiveElementDeclaration"/></b>
   *  //<b>             &lt;xsd:field xpath="simpleAttributeDeclarationGroupMember"/></b>
   *  //<b>         &lt;/xsd:key></b>
   *  //<b>         &lt;xsd:keyref name="keyref" refer="PTS:key"></b>
   *  //<b>             &lt;xsd:selector xpath="simpleRecursiveElementDeclaration"/></b>
   *  //<b>             &lt;xsd:field xpath="simpleAttributeDeclaration"/></b>
   *  //<b>         &lt;/xsd:keyref></b>
   *  //<b>     &lt;/xsd:element></b>
   *}
   *<!-- insert-end-initialize-simple-recursive-element-declaration -->
   *</pre>
   */
  public void initializeSimpleRecursiveElementDeclaration()
  // <!-- begin-initialize-simple-recursive-element-declaration -->
  {
    // Create an element declaration and name it simpleRecursiveElementDeclaration.
    //
    /*{@link */XSDElementDeclaration/*}*/ simpleRecursiveElementDeclaration = xsdFactory.createXSDElementDeclaration();
    simpleRecursiveElementDeclaration./*{@link XSDNamedComponent#setName */setName/*}*/("simpleRecursiveElementDeclaration");

    // Create an annotation placeholder and set it to the element.
    //
    /*{@link */XSDAnnotation/*}*/ annotation = xsdFactory.createXSDAnnotation();
    simpleRecursiveElementDeclaration./*{@link XSDElementDeclaration#setAnnotation */setAnnotation/*}*/(annotation);

    // Set the element type definition to complex SimpleRecursiveComplexTypeDefinition from this schema.
    //
    simpleRecursiveElementDeclaration./*{@link XSDElementDeclaration#setTypeDefinition */setTypeDefinition/*}*/
      (prototypeSchema./*{@link XSDConcreteComponent#resolveComplexTypeDefinition(String) */resolveComplexTypeDefinition/*}*/("SimpleRecursiveComplexTypeDefinition"));

    // Create a unique identity constraint called unique.
    //
    XSDIdentityConstraintDefinition unique = xsdFactory./*{@link XSDFactory#createXSDIdentityConstraintDefinition */createXSDIdentityConstraintDefinition/*}*/();
    unique./*{@link XSDIdentityConstraintDefinition#setIdentityConstraintCategory */setIdentityConstraintCategory/*}*/(/*{@link */XSDIdentityConstraintCategory/*}*/.UNIQUE_LITERAL);
    unique.setName("unique");
    XSDXPathDefinition uniqueSelector = xsdFactory./*{@link XSDFactory#createXSDXPathDefinition */createXSDXPathDefinition/*}*/();
    uniqueSelector./*{@link XSDXPathDefinition#setVariety */setVariety/*}*/(/*{@link */XSDXPathVariety/*}*/.SELECTOR_LITERAL);
    uniqueSelector./*{@link XSDXPathDefinition#setValue */setValue/*}*/("simpleRecursiveElementDeclaration");
    unique./*{@link XSDIdentityConstraintDefinition#setSelector */setSelector/*}*/(uniqueSelector);
    XSDXPathDefinition uniqueField = xsdFactory.createXSDXPathDefinition();
    uniqueField.setVariety(XSDXPathVariety.FIELD_LITERAL);
    uniqueField.setValue("simpleAttributeDeclarationGroupMember");
    unique./*{@link XSDIdentityConstraintDefinition#getFields */getFields/*}*/().add(uniqueField);
    simpleRecursiveElementDeclaration./*{@link XSDElementDeclaration#getIdentityConstraintDefinitions */getIdentityConstraintDefinitions/*}*/().add(unique);

    // Create a key identity constraint called key.
    //
    XSDIdentityConstraintDefinition key = xsdFactory.createXSDIdentityConstraintDefinition();
    key.setIdentityConstraintCategory(XSDIdentityConstraintCategory.KEY_LITERAL);
    key.setName("key");
    XSDXPathDefinition keySelector = xsdFactory.createXSDXPathDefinition();
    keySelector.setVariety(XSDXPathVariety.SELECTOR_LITERAL);
    keySelector.setValue("simpleRecursiveElementDeclaration");
    key.setSelector(keySelector);
    XSDXPathDefinition keyField = xsdFactory.createXSDXPathDefinition();
    keyField.setVariety(XSDXPathVariety.FIELD_LITERAL);
    keyField.setValue("simpleAttributeDeclarationGroupMember");
    key.getFields().add(keyField);
    simpleRecursiveElementDeclaration.getIdentityConstraintDefinitions().add(key);

    // Create a keyref identity constraint called keyref that references key.
    //
    XSDIdentityConstraintDefinition keyref = xsdFactory.createXSDIdentityConstraintDefinition();
    keyref.setIdentityConstraintCategory(XSDIdentityConstraintCategory.KEYREF_LITERAL);
    keyref.setName("keyref");
    XSDXPathDefinition keyrefSelector = xsdFactory.createXSDXPathDefinition();
    keyrefSelector.setVariety(XSDXPathVariety.SELECTOR_LITERAL);
    keyrefSelector.setValue("simpleRecursiveElementDeclaration");
    keyref.setSelector(keyrefSelector);
    XSDXPathDefinition keyrefField = xsdFactory.createXSDXPathDefinition();
    keyrefField.setVariety(XSDXPathVariety.FIELD_LITERAL);
    keyrefField.setValue("simpleAttributeDeclaration");
    keyref./*{@link XSDIdentityConstraintDefinition#setReferencedKey */setReferencedKey/*}*/(key);
    keyref.getFields().add(keyrefField);
    simpleRecursiveElementDeclaration.getIdentityConstraintDefinitions().add(keyref);

    // Add the element to the schema.
    // 
    prototypeSchema./*{@link XSDSchema#getContents */getContents/*}*/().add(simpleRecursiveElementDeclaration);

    // This is the <!-- SAMPLE --> result that is produced.
    //
    ///     <xsd:element name="simpleRecursiveElementDeclaration" type="PTS:SimpleRecursiveComplexTypeDefinition">
    ///         <xsd:annotation/>
    ///         <xsd:unique name="unique">
    ///             <xsd:selector xpath="simpleRecursiveElementDeclaration"/>
    ///             <xsd:field xpath="simpleAttributeDeclarationGroupMember"/>
    ///         </xsd:unique>
    ///         <xsd:key name="key">
    ///             <xsd:selector xpath="simpleRecursiveElementDeclaration"/>
    ///             <xsd:field xpath="simpleAttributeDeclarationGroupMember"/>
    ///         </xsd:key>
    ///         <xsd:keyref name="keyref" refer="PTS:key">
    ///             <xsd:selector xpath="simpleRecursiveElementDeclaration"/>
    ///             <xsd:field xpath="simpleAttributeDeclaration"/>
    ///         </xsd:keyref>
    ///     </xsd:element>
  }
  // <!-- end-initialize-simple-recursive-element-declaration -->

  /**
   *<pre>
   *<!-- insert-begin-initialize-simple-attribute-declaration -->
   *{
   *  // Create an attribute declaration and set it's name to simpleAttributeDeclaration.
   *  // Set it's type definition to be someOtherTypeDefinition in someOtherSchemaURI,
   *  // and set the default constraint to "defaultValue".
   *  //
   *  {@link XSDAttributeDeclaration} simpleAttributeDeclaration = xsdFactory.createXSDAttributeDeclaration();
   *  simpleAttributeDeclaration.{@link org.eclipse.xsd.XSDNamedComponent#setName setName}("simpleAttributeDeclaration");
   *  simpleAttributeDeclaration.{@link XSDAttributeDeclaration#setTypeDefinition setTypeDefinition}
   *    (prototypeSchema.resolveSimpleTypeDefinition({@link #someOtherSchemaURI}, "someOtherTypeDefinition"));
   *  simpleAttributeDeclaration.{@link XSDAttributeDeclaration#setConstraint setConstraint}({@link XSDConstraint}.DEFAULT_LITERAL);
   *  simpleAttributeDeclaration.{@link XSDAttributeDeclaration#setLexicalValue setLexicalValue}("defaultValue");
   *
   *  // Add the attribute to the schema.
   *  //
   *  prototypeSchema.{@link XSDSchema#getContents getContents}().add(simpleAttributeDeclaration);
   *
   *  // This is the <!-- SAMPLE --> result that is produced.
   *  //
   *  //<b>     &lt;xsd:attribute default="defaultValue"</b>
   *  //<b>         name="simpleAttributeDeclaration" type="EXT:someOtherTypeDefinition"/></b>
   *}
   *<!-- insert-end-initialize-simple-attribute-declaration -->
   *</pre>
   */
  public void initializeSimpleAttributeDeclaration()
  // <!-- begin-initialize-simple-attribute-declaration -->
  {
    // Create an attribute declaration and set it's name to simpleAttributeDeclaration.
    // Set it's type definition to be someOtherTypeDefinition in someOtherSchemaURI,
    // and set the default constraint to "defaultValue".
    //
    /*{@link */XSDAttributeDeclaration/*}*/ simpleAttributeDeclaration = xsdFactory.createXSDAttributeDeclaration();
    simpleAttributeDeclaration./*{@link XSDNamedComponent#setName */setName/*}*/("simpleAttributeDeclaration");
    simpleAttributeDeclaration./*{@link XSDAttributeDeclaration#setTypeDefinition */setTypeDefinition/*}*/
      (prototypeSchema.resolveSimpleTypeDefinition(/*{@link #*/someOtherSchemaURI/*}*/, "someOtherTypeDefinition"));
    simpleAttributeDeclaration./*{@link XSDAttributeDeclaration#setConstraint */setConstraint/*}*/(/*{@link */XSDConstraint/*}*/.DEFAULT_LITERAL);
    simpleAttributeDeclaration./*{@link XSDAttributeDeclaration#setLexicalValue */setLexicalValue/*}*/("defaultValue");

    // Add the attribute to the schema.
    //
    prototypeSchema./*{@link XSDSchema#getContents */getContents/*}*/().add(simpleAttributeDeclaration);

    // This is the <!-- SAMPLE --> result that is produced.
    //
    ///     <xsd:attribute default="defaultValue"
    ///         name="simpleAttributeDeclaration" type="EXT:someOtherTypeDefinition"/>
  }
  // <!-- end-initialize-simple-attribute-declaration -->

  /**
   *<pre>
   *<!-- insert-begin-initialize-simple-element-declaration-with-anonymous-type -->
   *{
   *  // Create an element declaration and set it's name to simpleElementWithAnonymousType.
   *  //
   *  {@link XSDElementDeclaration} simpleElementDeclarationWithAnonymousType = xsdFactory.createXSDElementDeclaration();
   *  simpleElementDeclarationWithAnonymousType.{@link org.eclipse.xsd.XSDNamedComponent#setName setName}("simpleElementWithAnonymousType");
   *
   *  // Create an annotation placeholder and add it to the element.
   *  //
   *  {@link XSDAnnotation} annotation = xsdFactory.createXSDAnnotation();
   *  simpleElementDeclarationWithAnonymousType.{@link XSDElementDeclaration#setAnnotation setAnnotation}(annotation);
   *
   *  // Create an anonymous simple type definition and set it's base type to the built-in string.
   *  //
   *  {@link XSDSimpleTypeDefinition} anonymousSimpleTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
   *  anonymousSimpleTypeDefinition.{@link XSDSimpleTypeDefinition#setBaseTypeDefinition setBaseTypeDefinition}
   *    (prototypeSchema.{@link XSDSchema#getSchemaForSchema getSchemaForSchema}().{@link XSDConcreteComponent#resolveSimpleTypeDefinition resolveSimpleTypeDefinition}("string"));
   *
   *  // Create pattern facet.
   *  // Set it to match two digits followed by any two characters.
   *  // And add it to the anonymous type.
   *  //
   *  {@link XSDPatternFacet} xsdPatternFacet = xsdFactory.createXSDPatternFacet();
   *  xsdPatternFacet.{@link XSDPatternFacet#setLexicalValue setLexicalValue}("\\d\\d..");
   *  anonymousSimpleTypeDefinition.{@link XSDSimpleTypeDefinition#getFacetContents getFacetContents}().add(xsdPatternFacet);
   *
   *  // Set the element's annotation and anonymous type, 
   *  // and set the fixed constraint to "12ab".
   *  //
   *  simpleElementDeclarationWithAnonymousType.{@link XSDElementDeclaration#setAnnotation setAnnotation}(annotation);
   *  simpleElementDeclarationWithAnonymousType.{@link XSDElementDeclaration#setAnonymousTypeDefinition setAnonymousTypeDefinition}(anonymousSimpleTypeDefinition);
   *  simpleElementDeclarationWithAnonymousType.{@link XSDElementDeclaration#setConstraint setConstraint}({@link XSDConstraint}.FIXED_LITERAL);
   *  simpleElementDeclarationWithAnonymousType.{@link XSDElementDeclaration#setLexicalValue setLexicalValue}("12ab");
   *
   *  // Add the element to the schema.
   *  //
   *  prototypeSchema.{@link XSDSchema#getContents getContents}().add(simpleElementDeclarationWithAnonymousType);
   *
   *  // This is the <!-- SAMPLE --> result that is produced.
   *  //
   *  //<b>     &lt;xsd:element fixed="12ab" name="simpleElementWithAnonymousType"></b>
   *  //<b>         &lt;xsd:annotation/></b>
   *  //<b>         &lt;xsd:simpleType></b>
   *  //<b>             &lt;xsd:restriction base="xsd:string"></b>
   *  //<b>                 &lt;xsd:pattern value="\d\d.."/></b>
   *  //<b>             &lt;/xsd:restriction></b>
   *  //<b>         &lt;/xsd:simpleType></b>
   *  //<b>     &lt;/xsd:element></b>
   *}
   *<!-- insert-end-initialize-simple-element-declaration-with-anonymous-type -->
   *</pre>
   */
  public void initializeSimpleElementDeclarationWithAnonymousType()
  // <!-- begin-initialize-simple-element-declaration-with-anonymous-type -->
  {
    // Create an element declaration and set it's name to simpleElementWithAnonymousType.
    //
    /*{@link */XSDElementDeclaration/*}*/ simpleElementDeclarationWithAnonymousType = xsdFactory.createXSDElementDeclaration();
    simpleElementDeclarationWithAnonymousType./*{@link XSDNamedComponent#setName */setName/*}*/("simpleElementWithAnonymousType");

    // Create an annotation placeholder and add it to the element.
    //
    /*{@link */XSDAnnotation/*}*/ annotation = xsdFactory.createXSDAnnotation();
    simpleElementDeclarationWithAnonymousType./*{@link XSDElementDeclaration#setAnnotation */setAnnotation/*}*/(annotation);

    // Create an anonymous simple type definition and set it's base type to the built-in string.
    //
    /*{@link */XSDSimpleTypeDefinition/*}*/ anonymousSimpleTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
    anonymousSimpleTypeDefinition./*{@link XSDSimpleTypeDefinition#setBaseTypeDefinition */setBaseTypeDefinition/*}*/
      (prototypeSchema./*{@link XSDSchema#getSchemaForSchema */getSchemaForSchema/*}*/()./*{@link XSDConcreteComponent#resolveSimpleTypeDefinition */resolveSimpleTypeDefinition/*}*/("string"));

    // Create pattern facet.
    // Set it to match two digits followed by any two characters.
    // And add it to the anonymous type.
    //
    /*{@link */XSDPatternFacet/*}*/ xsdPatternFacet = xsdFactory.createXSDPatternFacet();
    xsdPatternFacet./*{@link XSDPatternFacet#setLexicalValue */setLexicalValue/*}*/("\\d\\d..");
    anonymousSimpleTypeDefinition./*{@link XSDSimpleTypeDefinition#getFacetContents */getFacetContents/*}*/().add(xsdPatternFacet);

    // Set the element's annotation and anonymous type, 
    // and set the fixed constraint to "12ab".
    //
    simpleElementDeclarationWithAnonymousType./*{@link XSDElementDeclaration#setAnnotation */setAnnotation/*}*/(annotation);
    simpleElementDeclarationWithAnonymousType./*{@link XSDElementDeclaration#setAnonymousTypeDefinition */setAnonymousTypeDefinition/*}*/(anonymousSimpleTypeDefinition);
    simpleElementDeclarationWithAnonymousType./*{@link XSDElementDeclaration#setConstraint */setConstraint/*}*/(/*{@link */XSDConstraint/*}*/.FIXED_LITERAL);
    simpleElementDeclarationWithAnonymousType./*{@link XSDElementDeclaration#setLexicalValue */setLexicalValue/*}*/("12ab");

    // Add the element to the schema.
    //
    prototypeSchema./*{@link XSDSchema#getContents */getContents/*}*/().add(simpleElementDeclarationWithAnonymousType);

    // This is the <!-- SAMPLE --> result that is produced.
    //
    ///     <xsd:element fixed="12ab" name="simpleElementWithAnonymousType">
    ///         <xsd:annotation/>
    ///         <xsd:simpleType>
    ///             <xsd:restriction base="xsd:string">
    ///                 <xsd:pattern value="\d\d.."/>
    ///             </xsd:restriction>
    ///         </xsd:simpleType>
    ///     </xsd:element>
  }
  // <!-- end-initialize-simple-element-declaration-with-anonymous-type -->

  /**
   *<pre>
   *<!-- insert-begin-initialize-simple-type-definition -->
   *{
   *  // Create a simple type definition and set it's name to SimpleTypeDefinition.
   *  // Also set it's base type to be the built-in positiveInteger.
   *  //
   *  {@link XSDSimpleTypeDefinition} simpleTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
   *  simpleTypeDefinition.{@link org.eclipse.xsd.XSDNamedComponent#setName setName}("SimpleTypeDefinition");
   *  simpleTypeDefinition.{@link XSDSimpleTypeDefinition#setBaseTypeDefinition setBaseTypeDefinition}
   *    (prototypeSchema.{@link XSDSchema#getSchemaForSchema getSchemaForSchema}().{@link XSDConcreteComponent#resolveSimpleTypeDefinition resolveSimpleTypeDefinition}("positiveInteger"));
   *
   *  // Create an annotation placeholder and add it to the type.
   *  //
   *  {@link XSDAnnotation} annotation = xsdFactory.createXSDAnnotation();
   *  simpleTypeDefinition.{@link XSDElementDeclaration#setAnnotation setAnnotation}(annotation);
   *
   *  // Create max inclusive facet, set it to 100, and add it to the simple type's facet contents.
   *  //
   *  {@link XSDMaxInclusiveFacet} xsdMaxInclusiveFacet = xsdFactory.createXSDMaxInclusiveFacet();
   *  xsdMaxInclusiveFacet.{@link XSDMaxInclusiveFacet#setLexicalValue setLexicalValue}("100");
   *  simpleTypeDefinition.{@link XSDSimpleTypeDefinition#getFacetContents getFacetContents}().add(xsdMaxInclusiveFacet);
   *
   *  // Add the simple type to the schema
   *  //
   *  prototypeSchema.{@link XSDSchema#getContents getContents}().add(simpleTypeDefinition);
   *
   *  // This is the <!-- SAMPLE --> result that is produced.
   *  //
   *  //<b>     &lt;xsd:simpleType name="SimpleTypeDefinition"></b>
   *  //<b>         &lt;xsd:annotation/></b>
   *  //<b>         &lt;xsd:restriction base="xsd:positiveInteger"></b>
   *  //<b>             &lt;xsd:maxInclusive value="100"/></b>
   *  //<b>         &lt;/xsd:restriction></b>
   *  //<b>     &lt;/xsd:simpleType></b>
   *}
   *<!-- insert-end-initialize-simple-type-definition -->
   *</pre>
   */
  public void initializeSimpleTypeDefinition()
  // <!-- begin-initialize-simple-type-definition -->
  {
    // Create a simple type definition and set it's name to SimpleTypeDefinition.
    // Also set it's base type to be the built-in positiveInteger.
    //
    /*{@link */XSDSimpleTypeDefinition/*}*/ simpleTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
    simpleTypeDefinition./*{@link XSDNamedComponent#setName */setName/*}*/("SimpleTypeDefinition");
    simpleTypeDefinition./*{@link XSDSimpleTypeDefinition#setBaseTypeDefinition */setBaseTypeDefinition/*}*/
      (prototypeSchema./*{@link XSDSchema#getSchemaForSchema */getSchemaForSchema/*}*/()./*{@link XSDConcreteComponent#resolveSimpleTypeDefinition */resolveSimpleTypeDefinition/*}*/("positiveInteger"));

    // Create an annotation placeholder and add it to the type.
    //
    /*{@link */XSDAnnotation/*}*/ annotation = xsdFactory.createXSDAnnotation();
    simpleTypeDefinition./*{@link XSDElementDeclaration#setAnnotation */setAnnotation/*}*/(annotation);

    // Create max inclusive facet, set it to 100, and add it to the simple type's facet contents.
    //
    /*{@link */XSDMaxInclusiveFacet/*}*/ xsdMaxInclusiveFacet = xsdFactory.createXSDMaxInclusiveFacet();
    xsdMaxInclusiveFacet./*{@link XSDMaxInclusiveFacet#setLexicalValue */setLexicalValue/*}*/("100");
    simpleTypeDefinition./*{@link XSDSimpleTypeDefinition#getFacetContents */getFacetContents/*}*/().add(xsdMaxInclusiveFacet);

    // Add the simple type to the schema
    //
    prototypeSchema./*{@link XSDSchema#getContents */getContents/*}*/().add(simpleTypeDefinition);

    // This is the <!-- SAMPLE --> result that is produced.
    //
    ///     <xsd:simpleType name="SimpleTypeDefinition">
    ///         <xsd:annotation/>
    ///         <xsd:restriction base="xsd:positiveInteger">
    ///             <xsd:maxInclusive value="100"/>
    ///         </xsd:restriction>
    ///     </xsd:simpleType>
  }
  // <!-- end-initialize-simple-type-definition -->

  /**
   *<pre>
   *<!-- insert-begin-initialize-simple-list-type-definition -->
   *{
   *  // Create a simple list type definition and set it's name to SimpleListTypeDefinition.
   *  // Also set its item type to the built-in integer.
   *  //
   *  {@link XSDSimpleTypeDefinition} simpleListTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
   *  simpleListTypeDefinition.{@link org.eclipse.xsd.XSDNamedComponent#setName setName}("SimpleListTypeDefinition");
   *  simpleListTypeDefinition.{@link XSDSimpleTypeDefinition#setItemTypeDefinition setItemTypeDefinition}
   *    (prototypeSchema.{@link XSDSchema#getSchemaForSchema getSchemaForSchema}().{@link XSDConcreteComponent#resolveSimpleTypeDefinition resolveSimpleTypeDefinition}("integer"));
   *
   *  // Create an annotation placeholder and add it to the type.
   *  //
   *  {@link XSDAnnotation} annotation = xsdFactory.createXSDAnnotation();
   *  simpleListTypeDefinition.{@link XSDElementDeclaration#setAnnotation setAnnotation}(annotation);
   *
   *  // Add the simple list type to the schema
   *  //
   *  prototypeSchema.{@link XSDSchema#getContents getContents}().add(simpleListTypeDefinition);
   *
   *  // This is the <!-- SAMPLE --> result that is produced.
   *  //
   *  //<b>     &lt;xsd:simpleType name="SimpleListTypeDefinition"></b>
   *  //<b>         &lt;xsd:annotation/></b>
   *  //<b>         &lt;xsd:list itemType="xsd:integer"/></b>
   *  //<b>     &lt;/xsd:simpleType></b>
   *}
   *<!-- insert-end-initialize-simple-list-type-definition -->
   *</pre>
   */
  public void initializeSimpleListTypeDefinition()
  // <!-- begin-initialize-simple-list-type-definition -->
  {
    // Create a simple list type definition and set it's name to SimpleListTypeDefinition.
    // Also set its item type to the built-in integer.
    //
    /*{@link */XSDSimpleTypeDefinition/*}*/ simpleListTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
    simpleListTypeDefinition./*{@link XSDNamedComponent#setName */setName/*}*/("SimpleListTypeDefinition");
    simpleListTypeDefinition./*{@link XSDSimpleTypeDefinition#setItemTypeDefinition */setItemTypeDefinition/*}*/
      (prototypeSchema./*{@link XSDSchema#getSchemaForSchema */getSchemaForSchema/*}*/()./*{@link XSDConcreteComponent#resolveSimpleTypeDefinition */resolveSimpleTypeDefinition/*}*/("integer"));

    // Create an annotation placeholder and add it to the type.
    //
    /*{@link */XSDAnnotation/*}*/ annotation = xsdFactory.createXSDAnnotation();
    simpleListTypeDefinition./*{@link XSDElementDeclaration#setAnnotation */setAnnotation/*}*/(annotation);

    // Add the simple list type to the schema
    //
    prototypeSchema./*{@link XSDSchema#getContents */getContents/*}*/().add(simpleListTypeDefinition);

    // This is the <!-- SAMPLE --> result that is produced.
    //
    ///     <xsd:simpleType name="SimpleListTypeDefinition">
    ///         <xsd:annotation/>
    ///         <xsd:list itemType="xsd:integer"/>
    ///     </xsd:simpleType>
  }
  // <!-- end-initialize-simple-list-type-definition -->

  /**
   *<pre>
   *<!-- insert-begin-initialize-simple-union-type-definition -->
   *{
   *  // Create a simple list type definition and set it's name to SimpleUnionTypeDefinition.
   *  // Also set its item type to the built-in integer.
   *  //
   *  {@link XSDSimpleTypeDefinition} simpleUnionTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
   *  simpleUnionTypeDefinition.{@link org.eclipse.xsd.XSDNamedComponent#setName setName}("SimpleUnionTypeDefinition");
   *  simpleUnionTypeDefinition.{@link XSDSimpleTypeDefinition#getMemberTypeDefinitions getMemberTypeDefinitions}().add
   *    (prototypeSchema.{@link XSDSchema#getSchemaForSchema getSchemaForSchema}().{@link XSDConcreteComponent#resolveSimpleTypeDefinition resolveSimpleTypeDefinition}("integer"));
   *
   *  // Create an annotation placeholder and add it to the type.
   *  //
   *  {@link XSDAnnotation} annotation = xsdFactory.createXSDAnnotation();
   *  simpleUnionTypeDefinition.{@link XSDElementDeclaration#setAnnotation setAnnotation}(annotation);
   *
   *  // Add the simple list type to the schema
   *  //
   *  prototypeSchema.{@link XSDSchema#getContents getContents}().add(simpleUnionTypeDefinition);
   *
   *  // This is the <!-- SAMPLE --> result that is produced.
   *  //
   *  //<b>     &lt;xsd:simpleType name="SimpleUnionTypeDefinition"></b>
   *  //<b>         &lt;xsd:annotation/></b>
   *  //<b>         &lt;xsd:union memberTypes="xsd:integer"/></b>
   *  //<b>     &lt;/xsd:simpleType></b>
   *}
   *<!-- insert-end-initialize-simple-union-type-definition -->
   *</pre>
   */
  public void initializeSimpleUnionTypeDefinition()
  // <!-- begin-initialize-simple-union-type-definition -->
  {
    // Create a simple list type definition and set it's name to SimpleUnionTypeDefinition.
    // Also set its item type to the built-in integer.
    //
    /*{@link */XSDSimpleTypeDefinition/*}*/ simpleUnionTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
    simpleUnionTypeDefinition./*{@link XSDNamedComponent#setName */setName/*}*/("SimpleUnionTypeDefinition");
    simpleUnionTypeDefinition./*{@link XSDSimpleTypeDefinition#getMemberTypeDefinitions */getMemberTypeDefinitions/*}*/().add
      (prototypeSchema./*{@link XSDSchema#getSchemaForSchema */getSchemaForSchema/*}*/()./*{@link XSDConcreteComponent#resolveSimpleTypeDefinition */resolveSimpleTypeDefinition/*}*/("integer"));

    // Create an annotation placeholder and add it to the type.
    //
    /*{@link */XSDAnnotation/*}*/ annotation = xsdFactory.createXSDAnnotation();
    simpleUnionTypeDefinition./*{@link XSDElementDeclaration#setAnnotation */setAnnotation/*}*/(annotation);

    // Add the simple list type to the schema
    //
    prototypeSchema./*{@link XSDSchema#getContents */getContents/*}*/().add(simpleUnionTypeDefinition);

    // This is the <!-- SAMPLE --> result that is produced.
    //
    ///     <xsd:simpleType name="SimpleUnionTypeDefinition">
    ///         <xsd:annotation/>
    ///         <xsd:union memberTypes="xsd:integer"/>
    ///     </xsd:simpleType>
  }
  // <!-- end-initialize-simple-union-type-definition -->

  /**
   *<pre>
   *<!-- insert-begin-initialize-fancy-list-type-definition -->
   *{
   *  // Create a fancy list type definition and set it's name to FancyListTypeDefinition.
   *  //
   *  {@link XSDSimpleTypeDefinition} fancyListTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
   *  fancyListTypeDefinition.{@link org.eclipse.xsd.XSDNamedComponent#setName setName}("FancyListTypeDefinition");
   *
   *  // Create an anonymous list type definition for fancyListTypeDefinition to restrict.
   *  //
   *  XSDSimpleTypeDefinition listTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
   *
   *  // Create an anonymous union type definition for the item type of the listTypeDefiniton.
   *  //
   *  XSDSimpleTypeDefinition unionTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
   *
   *  // Create an anonymous "member" type definition for the unionTypeDefinition,
   *  // and set it to restrict the built-in string.
   *  //
   *  XSDSimpleTypeDefinition firstMemberTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
   *  firstMemberTypeDefinition.{@link XSDSimpleTypeDefinition#setBaseTypeDefinition setBaseTypeDefinition}
   *    (prototypeSchema.{@link XSDSchema#getSchemaForSchema getSchemaForSchema}().{@link XSDConcreteComponent#resolveSimpleTypeDefinition resolveSimpleTypeDefinition}("string"));
   *
   *  // Create an enumeration facet to specify the value "unknown" and add it to the member.
   *  //
   *  {@link XSDEnumerationFacet} xsdEnumerationFacet = xsdFactory.createXSDEnumerationFacet();
   *  xsdEnumerationFacet.{@link org.eclipse.xsd.XSDConstrainingFacet#setLexicalValue setLexicalValue}("unknown");
   *  firstMemberTypeDefinition.{@link XSDSimpleTypeDefinition#getFacetContents getFacetContents}().add(xsdEnumerationFacet);
   *
   *  // Add the anonymous member to the contents <b>and</b> the member type definitions of unionTypeDefinition.
   *  //
   *  unionTypeDefinition.{@link XSDSimpleTypeDefinition#getContents getContents}().add(firstMemberTypeDefinition);
   *  unionTypeDefinition.{@link XSDSimpleTypeDefinition#getMemberTypeDefinitions getMemberTypeDefinitions}().add(firstMemberTypeDefinition);
   *
   *  // Create another anonymous "member" type definition for the unionTypeDefinition,
   *  // and set it to restrict the built-in decimal.
   *  //
   *  XSDSimpleTypeDefinition secondMemberTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
   *  secondMemberTypeDefinition.setBaseTypeDefinition
   *    (prototypeSchema.getSchemaForSchema().resolveSimpleTypeDefinition("decimal"));
   *
   *  // Create a fraction digits facet to specify the value "2" and add it to the member.
   *  //
   *  {@link XSDFractionDigitsFacet} xsdFractionDigitsFacet = xsdFactory.createXSDFractionDigitsFacet();
   *  xsdFractionDigitsFacet.setLexicalValue("2");
   *  secondMemberTypeDefinition.getFacetContents().add(xsdFractionDigitsFacet);
   *
   *  // Add the anonymous member to the contents <b>and</b> the member type definitions of unionTypeDefinition.
   *  //
   *  unionTypeDefinition.getContents().add(secondMemberTypeDefinition);
   *  unionTypeDefinition.getMemberTypeDefinitions().add(secondMemberTypeDefinition);
   *
   *  // Add the anonymous item to the contents <b>and</b> set the item type definition.
   *  //
   *  listTypeDefinition.getContents().add(unionTypeDefinition);
   *  listTypeDefinition.setItemTypeDefinition(unionTypeDefinition);
   *
   *  // Add the anonymous restriction to the contents <b>and</b> set the base type definition.
   *  //
   *  fancyListTypeDefinition.getContents().add(listTypeDefinition);
   *  fancyListTypeDefinition.setBaseTypeDefinition(listTypeDefinition);
   *
   *  // Create a max length facet to specify the value "4" and add it to the fancy list.
   *  //
   *  XSDMaxLengthFacet xsdMaxLengthFacet = xsdFactory.createXSDMaxLengthFacet();
   *  xsdMaxLengthFacet.setLexicalValue("4");
   *  fancyListTypeDefinition.getFacetContents().add(xsdMaxLengthFacet);
   *
   *  // Add the fancy list type to the schema.
   *  //
   *  prototypeSchema.{@link XSDSchema#getContents getContents}().add(fancyListTypeDefinition);
   *
   *  // This is the <!-- SAMPLE --> result that is produced.
   *  //
   *  //<b>     &lt;xsd:simpleType name="FancyListTypeDefinition"></b>
   *  //<b>         &lt;xsd:restriction></b>
   *  //<b>             &lt;xsd:simpleType></b>
   *  //<b>                 &lt;xsd:list></b>
   *  //<b>                     &lt;xsd:simpleType></b>
   *  //<b>                         &lt;xsd:union></b>
   *  //<b>                             &lt;xsd:simpleType></b>
   *  //<b>                                 &lt;xsd:restriction base="xsd:string"></b>
   *  //<b>                                     &lt;xsd:enumeration value="unknown"/></b>
   *  //<b>                                 &lt;/xsd:restriction></b>
   *  //<b>                             &lt;/xsd:simpleType></b>
   *  //<b>                             &lt;xsd:simpleType></b>
   *  //<b>                                 &lt;xsd:restriction base="xsd:decimal"></b>
   *  //<b>                                     &lt;xsd:fractionDigits value="2"/></b>
   *  //<b>                                 &lt;/xsd:restriction></b>
   *  //<b>                             &lt;/xsd:simpleType></b>
   *  //<b>                         &lt;/xsd:union></b>
   *  //<b>                     &lt;/xsd:simpleType></b>
   *  //<b>                 &lt;/xsd:list></b>
   *  //<b>             &lt;/xsd:simpleType></b>
   *  //<b>             &lt;xsd:maxLength value="4"/></b>
   *  //<b>         &lt;/xsd:restriction></b>
   *  //<b>     &lt;/xsd:simpleType></b>
   *}
   *<!-- insert-end-initialize-fancy-list-type-definition -->
   *</pre>
   */
  public void initializeFancyListTypeDefinition()
  // <!-- begin-initialize-fancy-list-type-definition -->
  {
    // Create a fancy list type definition and set it's name to FancyListTypeDefinition.
    //
    /*{@link */XSDSimpleTypeDefinition/*}*/ fancyListTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
    fancyListTypeDefinition./*{@link XSDNamedComponent#setName */setName/*}*/("FancyListTypeDefinition");

    // Create an anonymous list type definition for fancyListTypeDefinition to restrict.
    //
    XSDSimpleTypeDefinition listTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();

    // Create an anonymous union type definition for the item type of the listTypeDefiniton.
    //
    XSDSimpleTypeDefinition unionTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();

    // Create an anonymous "member" type definition for the unionTypeDefinition,
    // and set it to restrict the built-in string.
    //
    XSDSimpleTypeDefinition firstMemberTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
    firstMemberTypeDefinition./*{@link XSDSimpleTypeDefinition#setBaseTypeDefinition */setBaseTypeDefinition/*}*/
      (prototypeSchema./*{@link XSDSchema#getSchemaForSchema */getSchemaForSchema/*}*/()./*{@link XSDConcreteComponent#resolveSimpleTypeDefinition */resolveSimpleTypeDefinition/*}*/("string"));

    // Create an enumeration facet to specify the value "unknown" and add it to the member.
    //
    /*{@link */XSDEnumerationFacet/*}*/ xsdEnumerationFacet = xsdFactory.createXSDEnumerationFacet();
    xsdEnumerationFacet./*{@link XSDConstrainingFacet#setLexicalValue */setLexicalValue/*}*/("unknown");
    firstMemberTypeDefinition./*{@link XSDSimpleTypeDefinition#getFacetContents */getFacetContents/*}*/().add(xsdEnumerationFacet);

    // Add the anonymous member to the contents <b>and</b> the member type definitions of unionTypeDefinition.
    //
    unionTypeDefinition./*{@link XSDSimpleTypeDefinition#getContents */getContents/*}*/().add(firstMemberTypeDefinition);
    unionTypeDefinition./*{@link XSDSimpleTypeDefinition#getMemberTypeDefinitions */getMemberTypeDefinitions/*}*/().add(firstMemberTypeDefinition);

    // Create another anonymous "member" type definition for the unionTypeDefinition,
    // and set it to restrict the built-in decimal.
    //
    XSDSimpleTypeDefinition secondMemberTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
    secondMemberTypeDefinition.setBaseTypeDefinition
      (prototypeSchema.getSchemaForSchema().resolveSimpleTypeDefinition("decimal"));

    // Create a fraction digits facet to specify the value "2" and add it to the member.
    //
    /*{@link */XSDFractionDigitsFacet/*}*/ xsdFractionDigitsFacet = xsdFactory.createXSDFractionDigitsFacet();
    xsdFractionDigitsFacet.setLexicalValue("2");
    secondMemberTypeDefinition.getFacetContents().add(xsdFractionDigitsFacet);

    // Add the anonymous member to the contents <b>and</b> the member type definitions of unionTypeDefinition.
    //
    unionTypeDefinition.getContents().add(secondMemberTypeDefinition);
    unionTypeDefinition.getMemberTypeDefinitions().add(secondMemberTypeDefinition);

    // Add the anonymous item to the contents <b>and</b> set the item type definition.
    //
    listTypeDefinition.getContents().add(unionTypeDefinition);
    listTypeDefinition.setItemTypeDefinition(unionTypeDefinition);

    // Add the anonymous restriction to the contents <b>and</b> set the base type definition.
    //
    fancyListTypeDefinition.getContents().add(listTypeDefinition);
    fancyListTypeDefinition.setBaseTypeDefinition(listTypeDefinition);

    // Create a max length facet to specify the value "4" and add it to the fancy list.
    //
    XSDMaxLengthFacet xsdMaxLengthFacet = xsdFactory.createXSDMaxLengthFacet();
    xsdMaxLengthFacet.setLexicalValue("4");
    fancyListTypeDefinition.getFacetContents().add(xsdMaxLengthFacet);

    // Add the fancy list type to the schema.
    //
    prototypeSchema./*{@link XSDSchema#getContents */getContents/*}*/().add(fancyListTypeDefinition);

    // This is the <!-- SAMPLE --> result that is produced.
    //
    ///     <xsd:simpleType name="FancyListTypeDefinition">
    ///         <xsd:restriction>
    ///             <xsd:simpleType>
    ///                 <xsd:list>
    ///                     <xsd:simpleType>
    ///                         <xsd:union>
    ///                             <xsd:simpleType>
    ///                                 <xsd:restriction base="xsd:string">
    ///                                     <xsd:enumeration value="unknown"/>
    ///                                 </xsd:restriction>
    ///                             </xsd:simpleType>
    ///                             <xsd:simpleType>
    ///                                 <xsd:restriction base="xsd:decimal">
    ///                                     <xsd:fractionDigits value="2"/>
    ///                                 </xsd:restriction>
    ///                             </xsd:simpleType>
    ///                         </xsd:union>
    ///                     </xsd:simpleType>
    ///                 </xsd:list>
    ///             </xsd:simpleType>
    ///             <xsd:maxLength value="4"/>
    ///         </xsd:restriction>
    ///     </xsd:simpleType>
  }
  // <!-- end-initialize-fancy-list-type-definition -->

  /**
   *<pre>
   *<!-- insert-begin-initialize-simple-content-complex-type-definition -->
   *{
   *  // Create a complex type definition and set its name to SimpleContentComplexTypeDefinition.
   *  // Set it to be an extension
   *  //  
   *  {@link XSDComplexTypeDefinition} simpleContentComplexTypeDefinition = xsdFactory.createXSDComplexTypeDefinition();
   *  simpleContentComplexTypeDefinition.{@link org.eclipse.xsd.XSDNamedComponent#setName setName}("SimpleContentComplexTypeDefinition");
   *  simpleContentComplexTypeDefinition.{@link XSDComplexTypeDefinition#setDerivationMethod setDerivationMethod}({@link XSDDerivationMethod}.EXTENSION_LITERAL);
   *
   *  // Create an annotation placeholder and add it to the complex type.
   *  //
   *  {@link XSDAnnotation} annotation = xsdFactory.createXSDAnnotation();
   *  simpleContentComplexTypeDefinition.{@link XSDElementDeclaration#setAnnotation setAnnotation}(annotation);
   *
   *  // Create an anonymous simple type definition, set its base type to be the built-in string,
   *  // and set it to be the content type of the complex type.
   *  //
   *  XSDSimpleTypeDefinition anonymousSimpleTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
   *  simpleContentComplexTypeDefinition.{@link XSDSimpleTypeDefinition#setBaseTypeDefinition setBaseTypeDefinition}
   *    (prototypeSchema.{@link XSDSchema#getSchemaForSchema getSchemaForSchema}().{@link XSDConcreteComponent#resolveSimpleTypeDefinition resolveSimpleTypeDefinition}("string"));
   *  simpleContentComplexTypeDefinition.{@link XSDComplexTypeDefinition#setContent setContent}(anonymousSimpleTypeDefinition);
   *
   *  // Create an attribute reference to simpleAttributeDeclaration in this schema.
   *  //
   *  {@link XSDAttributeDeclaration} simpleAttributeDeclarationReference = xsdFactory.createXSDAttributeDeclaration();
   *  simpleAttributeDeclarationReference.{@link XSDAttributeDeclaration#setResolvedAttributeDeclaration setResolvedAttributeDeclaration}
   *    (prototypeSchema.{@link XSDConcreteComponent#resolveAttributeDeclaration(String) resolveAttributeDeclaration}("simpleAttributeDeclaration"));
   *
   *  // Create an attribute use to hold the reference, set its use to be optional,
   *  // and add it to the complex type's attribute contents
   *  //
   *  {@link XSDAttributeUse} simpleAttributeUse = xsdFactory.createXSDAttributeUse();
   *  simpleAttributeUse.{@link XSDAttributeUse#setContent setContent}(simpleAttributeDeclarationReference);
   *  simpleAttributeUse.setUse({@link XSDAttributeUseCategory}.OPTIONAL_LITERAL);
   *  simpleContentComplexTypeDefinition.{@link XSDComplexTypeDefinition#getAttributeContents getAttributeContents}().add(simpleAttributeUse);
   *
   *  // Add the complex type to the schema.
   *  //
   *  prototypeSchema.{@link XSDSchema#getContents getContents}().add(simpleContentComplexTypeDefinition);
   *
   *  // This is the <!-- SAMPLE --> result that is produced.
   *  //
   *  //<b>     &lt;xsd:complexType name="SimpleContentComplexTypeDefinition"></b>
   *  //<b>         &lt;xsd:annotation/></b>
   *  //<b>         &lt;xsd:simpleContent></b>
   *  //<b>             &lt;xsd:extension base="xsd:string"></b>
   *  //<b>                 &lt;xsd:attribute ref="PTS:simpleAttributeDeclaration" use="optional"/></b>
   *  //<b>             &lt;/xsd:extension></b>
   *  //<b>         &lt;/xsd:simpleContent></b>
   *  //<b>     &lt;/xsd:complexType></b>
   *}
   *<!-- insert-end-initialize-simple-content-complex-type-definition -->
   *</pre>
   */
  public void initializeSimpleContentComplexTypeDefinition()
  // <!-- begin-initialize-simple-content-complex-type-definition -->
  {
    // Create a complex type definition and set its name to SimpleContentComplexTypeDefinition.
    // Set it to be an extension
    //  
    /*{@link */XSDComplexTypeDefinition/*}*/ simpleContentComplexTypeDefinition = xsdFactory.createXSDComplexTypeDefinition();
    simpleContentComplexTypeDefinition./*{@link XSDNamedComponent#setName */setName/*}*/("SimpleContentComplexTypeDefinition");
    simpleContentComplexTypeDefinition./*{@link XSDComplexTypeDefinition#setDerivationMethod */setDerivationMethod/*}*/(/*{@link */XSDDerivationMethod/*}*/.EXTENSION_LITERAL);

    // Create an annotation placeholder and add it to the complex type.
    //
    /*{@link */XSDAnnotation/*}*/ annotation = xsdFactory.createXSDAnnotation();
    simpleContentComplexTypeDefinition./*{@link XSDElementDeclaration#setAnnotation */setAnnotation/*}*/(annotation);

    // Create an anonymous simple type definition, set its base type to be the built-in string,
    // and set it to be the content type of the complex type.
    //
    XSDSimpleTypeDefinition anonymousSimpleTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
    simpleContentComplexTypeDefinition./*{@link XSDSimpleTypeDefinition#setBaseTypeDefinition */setBaseTypeDefinition/*}*/
      (prototypeSchema./*{@link XSDSchema#getSchemaForSchema */getSchemaForSchema/*}*/()./*{@link XSDConcreteComponent#resolveSimpleTypeDefinition */resolveSimpleTypeDefinition/*}*/("string"));
    simpleContentComplexTypeDefinition./*{@link XSDComplexTypeDefinition#setContent */setContent/*}*/(anonymousSimpleTypeDefinition);

    // Create an attribute reference to simpleAttributeDeclaration in this schema.
    //
    /*{@link */XSDAttributeDeclaration/*}*/ simpleAttributeDeclarationReference = xsdFactory.createXSDAttributeDeclaration();
    simpleAttributeDeclarationReference./*{@link XSDAttributeDeclaration#setResolvedAttributeDeclaration */setResolvedAttributeDeclaration/*}*/
      (prototypeSchema./*{@link XSDConcreteComponent#resolveAttributeDeclaration(String) */resolveAttributeDeclaration/*}*/("simpleAttributeDeclaration"));

    // Create an attribute use to hold the reference, set its use to be optional,
    // and add it to the complex type's attribute contents
    //
    /*{@link */XSDAttributeUse/*}*/ simpleAttributeUse = xsdFactory.createXSDAttributeUse();
    simpleAttributeUse./*{@link XSDAttributeUse#setContent */setContent/*}*/(simpleAttributeDeclarationReference);
    simpleAttributeUse.setUse(/*{@link */XSDAttributeUseCategory/*}*/.OPTIONAL_LITERAL);
    simpleContentComplexTypeDefinition./*{@link XSDComplexTypeDefinition#getAttributeContents */getAttributeContents/*}*/().add(simpleAttributeUse);

    // Add the complex type to the schema.
    //
    prototypeSchema./*{@link XSDSchema#getContents */getContents/*}*/().add(simpleContentComplexTypeDefinition);

    // This is the <!-- SAMPLE --> result that is produced.
    //
    ///     <xsd:complexType name="SimpleContentComplexTypeDefinition">
    ///         <xsd:annotation/>
    ///         <xsd:simpleContent>
    ///             <xsd:extension base="xsd:string">
    ///                 <xsd:attribute ref="PTS:simpleAttributeDeclaration" use="optional"/>
    ///             </xsd:extension>
    ///         </xsd:simpleContent>
    ///     </xsd:complexType>
  }
  // <!-- end-initialize-simple-content-complex-type-definition -->

  /**
   *<pre>
   *<!-- insert-begin-purchase-order -->
   *{
   *  // Build the <a href="http://www.w3.org/TR/xmlschema-0/#POSchema">Purchase Order Schema</a> from Primer 0.
   *  // The general approach, for efficiency, is to attach objects as late as possible.
   *  // The XML serialization is at the <a href="#purchase-order-end">end</a>.
   *
   *  // Create the schema.
   *  //
   *  {@link XSDSchema} xsdSchema = XSDFactory.eINSTANCE.createXSDSchema();
   *
   *  // If you want schema tags and references to schema types to be qualified, 
   *  // which is recommend, this is the recommended qualifier.
   *  //
   *  xsdSchema.{@link XSDSchema#setSchemaForSchemaQNamePrefix setSchemaForSchemaQNamePrefix}("xsd");
   *
   *  // Set the target namespace.
   *  //
   *  xsdSchema.{@link XSDSchema#setTargetNamespace setTargetNamespace}("http://nist.gov/po.xsd");
   *
   *  // Choose the prefix used for this schema's namespace and the {@link XSDSchema#getSchemaForSchema schema for schema}'s namespace.
   *  //
   *  Map qNamePrefixToNamespaceMap = xsdSchema.{@link XSDSchema#getQNamePrefixToNamespaceMap getQNamePrefixToNamespaceMap}();
   *  qNamePrefixToNamespaceMap.put("po", xsdSchema.getTargetNamespace());
   *  qNamePrefixToNamespaceMap.put(xsdSchema.getSchemaForSchemaQNamePrefix(), {@link XSDConstants}.SCHEMA_FOR_SCHEMA_URI_2001);
   *
   *  // Create an annotation and add it to the schema.
   *  //
   *  {@link XSDAnnotation} xsdAnnotation = xsdFactory.createXSDAnnotation();
   *  xsdSchema.{@link XSDSchema#getContents getContents}().add(xsdAnnotation);
   *
   *  // Create a documentation DOM element with no sourceURI attribute.
   *  // <b>NOTE</b>
   *  // Working with the contents of an annotation requires dropping down into the DOM model.
   *  // This imposes the additional requirement that the annotation must be {@link XSDSchema#getSchema attached} to a schema.
   *  // It also has the effect of calling {@link XSDSchema#updateElement()},
   *  // if the schema does not have an {@link XSDConcreteComponent#getElement element} or {@link XSDSchema#getDocument document} yet.
   *  //
   *  {@link Element <em>Element</em>} documentation = xsdAnnotation.{@link XSDAnnotation#createUserInformation createUserInformation}(null);
   *
   *  // Use the DOM API to set the lang attribute and content of the documentation.
   *  //
   *  documentation.{@link Element#setAttributeNS <em>setAttributeNS</em>}(XSDConstants.XML_NAMESPACE_URI_1998, "xml:lang", "en");
   *  String text = "Purchase order schema for Example.com.\nCopyright 2000 Example.com. All rights reserved.";
   *  documentation.{@link Element#appendChild <em>appendChild</em>}(documentation.getOwnerDocument().createTextNode(text));
   *
   *  // Use the DOM API to add the documentation to the element of the annotation.
   *  //
   *  xsdAnnotation.{@link XSDAnnotation#getElement getElement}().appendChild(documentation);
   *
   *  // Create an element, name it purchaseOrder, 
   *  // and set it's type to the purchaseOrderType of this schema.
   *  // Add the purchaseOrder element to the schema.
   *  //
   *  {@link XSDElementDeclaration} purchaseOrder = xsdFactory.createXSDElementDeclaration();
   *  purchaseOrder.{@link org.eclipse.xsd.XSDNamedComponent#setName setName}("purchaseOrder");
   *  purchaseOrder.{@link XSDElementDeclaration#setTypeDefinition setTypeDefinition}(xsdSchema.{@link XSDConcreteComponent#resolveComplexTypeDefinition(String) resolveComplexTypeDefinition}("PurchaseOrderType"));
   *  xsdSchema.getContents().add(purchaseOrder);
   *
   *  // Create an element named command of the build-in type string.
   *  //
   *  XSDElementDeclaration comment = xsdFactory.createXSDElementDeclaration();
   *  comment.setName("comment");
   *  comment.setTypeDefinition(xsdSchema.getSchemaForSchema().{@link XSDConcreteComponent#resolveSimpleTypeDefinition(String) resolveSimpleTypeDefinition}("string"));
   *  xsdSchema.getContents().add(comment);
   *
   *  // Create a complex type and name it PurchaseOrderType.
   *  //
   *  {@link XSDComplexTypeDefinition} purchaseOrderType = xsdFactory.createXSDComplexTypeDefinition();
   *  purchaseOrderType.{@link org.eclipse.xsd.XSDNamedComponent#setName setName}("PurchaseOrderType");
   *
   *  // Create a sequence model group, and a particle to contain it.
   *  //
   *  {@link XSDModelGroup} purchaseOrderTypeSequence = xsdFactory.createXSDModelGroup();
   *  purchaseOrderTypeSequence.{@link XSDModelGroup#setCompositor setCompositor}(XSDCompositor.SEQUENCE_LITERAL);
   *  {@link XSDParticle} purchaseOrderTypeParticle = xsdFactory.createXSDParticle();
   *  purchaseOrderTypeParticle.{@link XSDParticle#setContent setContent}(purchaseOrderTypeSequence);
   *
   *  // Create an element named shipTo of complex type USAddress, a particle to contain it,
   *  // and add the particle to the sequence.
   *  //
   *  XSDElementDeclaration shipTo = xsdFactory.createXSDElementDeclaration();
   *  shipTo.setName("shipTo");
   *  shipTo.setTypeDefinition(xsdSchema.resolveComplexTypeDefinition("USAddress"));
   *  XSDParticle shipToParticle = xsdFactory.createXSDParticle();
   *  shipToParticle.setContent(shipTo);
   *  purchaseOrderTypeSequence.{@link XSDModelGroup#getContents getContents}().add(shipToParticle);
   *
   *  // Create an element named billTo of complex type USAddress, a particle to contain it,
   *  // and add the particle to the sequence.
   *  //
   *  XSDElementDeclaration billTo = xsdFactory.createXSDElementDeclaration();
   *  billTo.setName("billTo");
   *  billTo.setTypeDefinition(xsdSchema.resolveComplexTypeDefinition("USAddress"));
   *  XSDParticle billToParticle = xsdFactory.createXSDParticle();
   *  billToParticle.setContent(billTo);
   *  purchaseOrderTypeSequence.getContents().add(billToParticle);
   *
   *  // Create an element reference to comment, a particle to contain it,
   *  // set the particle to be optional, and add the particle to the sequence.
   *  //
   *  XSDElementDeclaration commentRef = xsdFactory.createXSDElementDeclaration();
   *  commentRef.{@link XSDElementDeclaration#setResolvedElementDeclaration setResolvedElementDeclaration}(xsdSchema.{@link XSDConcreteComponent#resolveElementDeclaration(String) resolveElementDeclaration}("comment"));
   *  XSDParticle commentRefParticle = xsdFactory.createXSDParticle();
   *  commentRefParticle.{@link XSDParticle#setMinOccurs(int) setMinOccurs}(0);
   *  commentRefParticle.setContent(commentRef);
   *  purchaseOrderTypeSequence.getContents().add(commentRefParticle);
   *
   *  // Create an element named items of complex type Items, a particle to contain it,
   *  // and add the particle to the sequence.
   *  //
   *  XSDElementDeclaration items = xsdFactory.createXSDElementDeclaration();
   *  items.setName("items");
   *  items.setTypeDefinition(xsdSchema.resolveComplexTypeDefinition("Items"));
   *  XSDParticle itemsParticle = xsdFactory.createXSDParticle();
   *  itemsParticle.setContent(items);
   *  purchaseOrderTypeSequence.getContents().add(billToParticle);
   *
   *  // Set the completed content particle to be the content of the PurchaseOrderType.
   *  //
   *  purchaseOrderType.{@link XSDComplexTypeDefinition#setContent setContent}(purchaseOrderTypeParticle);
   *
   *  // Create an attribute, name it orderDate, and set it's type to the built-in Date type.
   *  // Also create an attribute use to contain the attribute and add it to PurchaseOrderType's attribute contents.
   *  //
   *  {@link XSDAttributeDeclaration} orderDate = xsdFactory.createXSDAttributeDeclaration();
   *  orderDate.{@link org.eclipse.xsd.XSDNamedComponent#setName setName}("orderDate");
   *  orderDate.{@link XSDAttributeDeclaration#setTypeDefinition setTypeDefinition}(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("date"));
   *  {@link XSDAttributeUse} orderDateAttributeUse = xsdFactory.createXSDAttributeUse();
   *  orderDateAttributeUse.{@link XSDAttributeUse#setContent setContent}(orderDate);
   *  purchaseOrderType.{@link XSDComplexTypeDefinition#getAttributeContents getAttributeContents}().add(orderDateAttributeUse);
   *
   *  // Add the completed complex PurchaseOrderType to the schema.
   *  //
   *  xsdSchema.getContents().add(purchaseOrderType);
   *   
   *  // Create a complex type named USAddress.
   *  //
   *  XSDComplexTypeDefinition usAddress = xsdFactory.createXSDComplexTypeDefinition();
   *  usAddress.setName("USAddress");
   *
   *  // Create a sequence model group, and a particle to contain it.
   *  //
   *  XSDModelGroup usAddressSequence = xsdFactory.createXSDModelGroup();
   *  usAddressSequence.setCompositor(XSDCompositor.SEQUENCE_LITERAL);
   *  XSDParticle usAddressParticle = xsdFactory.createXSDParticle();
   *  usAddressParticle.setContent(usAddressSequence);
   *
   *  // Create an element named name of built-in type string, a particle to contain it,
   *  // and add the particle to the sequence.
   *  //
   *  XSDElementDeclaration name = xsdFactory.createXSDElementDeclaration();
   *  name.setName("name");
   *  name.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("string"));
   *  XSDParticle nameParticle = xsdFactory.createXSDParticle();
   *  nameParticle.setContent(name);
   *  usAddressSequence.getContents().add(nameParticle);
   *
   *  // Create an element named street of built-in type string, a particle to contain it,
   *  // and add the particle to the sequence.
   *  //
   *  XSDElementDeclaration street = xsdFactory.createXSDElementDeclaration();
   *  street.setName("street");
   *  street.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("string"));
   *  XSDParticle streeParticle = xsdFactory.createXSDParticle();
   *  streeParticle.setContent(street);
   *  usAddressSequence.getContents().add(streeParticle);
   *
   *  // Create an element named city of built-in type string, a particle to contain it,
   *  // and add the particle to the sequence.
   *  //
   *  XSDElementDeclaration city = xsdFactory.createXSDElementDeclaration();
   *  city.setName("city");
   *  city.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("string"));
   *  XSDParticle cityParticle = xsdFactory.createXSDParticle();
   *  cityParticle.setContent(city);
   *  usAddressSequence.getContents().add(cityParticle);
   *
   *  // Create an element named state of built-in type string, a particle to contain it,
   *  // and add the particle to the sequence.
   *  //
   *  XSDElementDeclaration state = xsdFactory.createXSDElementDeclaration();
   *  state.setName("state");
   *  state.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("string"));
   *  XSDParticle stateParticle = xsdFactory.createXSDParticle();
   *  stateParticle.setContent(state);
   *  usAddressSequence.getContents().add(stateParticle);
   *
   *  // Create an element named zip of built-in type string, a particle to contain it,
   *  // and add the particle to the sequence.
   *  //
   *  XSDElementDeclaration zip = xsdFactory.createXSDElementDeclaration();
   *  zip.setName("zip");
   *  zip.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("string"));
   *  XSDParticle zipParticle = xsdFactory.createXSDParticle();
   *  zipParticle.setContent(zip);
   *  usAddressSequence.getContents().add(zipParticle);
   *
   *  // Set the completed content particle to be the content of USAddress.
   *  //
   *  usAddress.setContent(usAddressParticle);
   *
   *  // Create an attribute, name it country, set it's type to the built-in NMTOKEN type,
   *  // set its constraints to be fixed, set the lexical value of the constraint to be "US".
   *  // Also create an attribute use to contain the attribute and add it to USAddress' attribute contents.
   *  //
   *  XSDAttributeDeclaration country = xsdFactory.createXSDAttributeDeclaration();
   *  country.setName("country");
   *  country.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("NMTOKEN"));
   *  XSDAttributeUse countryAttributeUse = xsdFactory.createXSDAttributeUse();
   *  countryAttributeUse.setContent(country);
   *  countryAttributeUse.{@link XSDAttributeDeclaration#setConstraint(XSDConstraint) setConstraint}({@link XSDConstraint}.FIXED_LITERAL);
   *  countryAttributeUse.{@link XSDAttributeDeclaration#setLexicalValue setLexicalValue}("US");
   *  usAddress.getAttributeContents().add(countryAttributeUse);
   *
   *  // Add the completed complex USAddress to the schema.
   *  //
   *  xsdSchema.getContents().add(usAddress);
   *
   *  // Create a complex type named Items.
   *  //
   *  XSDComplexTypeDefinition itemsType = xsdFactory.createXSDComplexTypeDefinition();
   *  itemsType.setName("Items");
   *
   *  // Create a sequence model group, and a particle to contain it.
   *  //
   *  XSDModelGroup itemsTypeSequence = xsdFactory.createXSDModelGroup();
   *  itemsTypeSequence.setCompositor(XSDCompositor.SEQUENCE_LITERAL);
   *  XSDParticle itemsTypeParticle = xsdFactory.createXSDParticle();
   *  itemsTypeParticle.setContent(itemsTypeSequence);
   *
   *  // Create an element and name it item.
   *  //
   *  XSDElementDeclaration item = xsdFactory.createXSDElementDeclaration();
   *  item.setName("item");
   *
   *  // Create an anonymous complex type.
   *  //
   *  XSDComplexTypeDefinition anonymousComplexTypeDefinition = xsdFactory.createXSDComplexTypeDefinition();
   *
   *  // Create a sequence model group, and a particle to contain it.
   *  //
   *  XSDModelGroup anonymousComplexTypeDefinitionSequence = xsdFactory.createXSDModelGroup();
   *  anonymousComplexTypeDefinitionSequence.setCompositor(XSDCompositor.SEQUENCE_LITERAL);
   *  XSDParticle anonymousTypeDefinitionParticle = xsdFactory.createXSDParticle();
   *  anonymousTypeDefinitionParticle.setContent(anonymousComplexTypeDefinitionSequence);
   *
   *  // Create an element productName of built-in type string, a particle to contain it,
   *  // and add the particle to the sequence.
   *  //
   *  XSDElementDeclaration productName = xsdFactory.createXSDElementDeclaration();
   *  productName.setName("productName");
   *  productName.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("string"));
   *  XSDParticle productNameParticle = xsdFactory.createXSDParticle();
   *  productNameParticle.setContent(productName);
   *  anonymousComplexTypeDefinitionSequence.getContents().add(productNameParticle);
   *
   *  // Create a particle to contain the item declaration and add it to the sequence.
   *  //
   *  XSDParticle itemParticle = xsdFactory.createXSDParticle();
   *  itemParticle.setContent(item);
   *  itemsTypeSequence.getContents().add(itemParticle);
   *
   *  // Create an attribute, name it partNum, set its type to the SKU type,
   *  // Also create an attribute use to contain the attribute, set it to be required, 
   *  // and add it to Items' attribute contents.
   *  //
   *  XSDAttributeDeclaration partNum = xsdFactory.createXSDAttributeDeclaration();
   *  partNum.setName("partNum");
   *  partNum.setTypeDefinition(xsdSchema.resolveSimpleTypeDefinition("SKU"));
   *  XSDAttributeUse partNumAttributeUse = xsdFactory.createXSDAttributeUse();
   *  partNumAttributeUse.setContent(partNum);
   *  partNumAttributeUse.setUse(XSDAttributeUseCategory.REQUIRED_LITERAL);
   *  itemsType.getAttributeContents().add(partNumAttributeUse);
   *
   *  // Set the completed content particle to be the content of the anonymous complex type.
   *  //
   *  anonymousComplexTypeDefinition.setContent(anonymousTypeDefinitionParticle);
   *
   *  // Create an element  and name it quantity.
   *  //
   *  XSDElementDeclaration quantity = xsdFactory.createXSDElementDeclaration();
   *  quantity.setName("quantity");
   *
   *  // Create an anonymous simple type and set it to restrict the built-in positiveInteger type.
   *  //
   *  {@link XSDSimpleTypeDefinition} anonymousSimpleTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
   *  anonymousSimpleTypeDefinition.{@link XSDSimpleTypeDefinition#setBaseTypeDefinition setBaseTypeDefinition}
   *    (xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("positiveInteger"));
   *
   *  // Create max exclusive facet, set its lexical value to be 100, and add it to the facet contents of the anonymous simple type.
   *  //
   *  XSDMaxExclusiveFacet quantityMaxExclusiveFacet = xsdFactory.createXSDMaxExclusiveFacet();
   *  quantityMaxExclusiveFacet.setLexicalValue("100");
   *  anonymousSimpleTypeDefinition.getFacetContents().add(quantityMaxExclusiveFacet);
   *
   *  // Set the completed anonymous type of the quantity element.
   *  //
   *  quantity.setAnonymousTypeDefinition(anonymousSimpleTypeDefinition);
   *
   *  // Create a particle to hold the quantity element and add it to the sequence.
   *  //
   *  XSDParticle quantityParticle = xsdFactory.createXSDParticle();
   *  quantityParticle.setContent(quantity);
   *  anonymousComplexTypeDefinitionSequence.getContents().add(quantityParticle);
   *
   *  // Create an element USPrice of built-in type decimal, a particle to contain it,
   *  // and add the particle to the sequence.
   *  //
   *  XSDElementDeclaration usPrice = xsdFactory.createXSDElementDeclaration();
   *  usPrice.setName("USPrice");
   *  usPrice.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("decimal"));
   *  XSDParticle usPriceParticle = xsdFactory.createXSDParticle();
   *  usPriceParticle.setContent(usPrice);
   *  anonymousComplexTypeDefinitionSequence.getContents().add(usPriceParticle);
   *
   *  // Create an element reference to comment, a particle to contain it,
   *  // set the particle to be optional, and add the particle to the sequence.
   *  //
   *  XSDElementDeclaration anotherCommentRef = xsdFactory.createXSDElementDeclaration();
   *  anotherCommentRef.setResolvedElementDeclaration(xsdSchema.resolveElementDeclaration("comment"));
   *  XSDParticle anotherCommentRefParticle = xsdFactory.createXSDParticle();
   *  anotherCommentRefParticle.setMinOccurs(0);
   *  anotherCommentRefParticle.setContent(anotherCommentRef);
   *  anonymousComplexTypeDefinitionSequence.getContents().add(anotherCommentRefParticle);
   *
   *  // Create an element USPrice of built-in type Date, a particle to contain it,
   *  // set the particle to be optional, and add it to the sequence.
   *  //
   *  XSDElementDeclaration shipDate = xsdFactory.createXSDElementDeclaration();
   *  shipDate.setName("shipDate");
   *  shipDate.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("decimal"));
   *  XSDParticle shipDateParticle = xsdFactory.createXSDParticle();
   *  shipDateParticle.setContent(shipDate);
   *  shipDateParticle.setMinOccurs(0);
   *  anonymousComplexTypeDefinitionSequence.getContents().add(shipDateParticle);
   *
   *  // Set the completed content particle to be the content of Items.
   *  //
   *  itemsType.setContent(itemsTypeParticle);
   *
   *  // Set the completed anonymous type of the item element.
   *  //
   *  item.{@link XSDElementDeclaration#setAnonymousTypeDefinition setAnonymousTypeDefinition}(anonymousComplexTypeDefinition);
   *
   *  // Add the completed complex Items to the schema.
   *  //
   *  xsdSchema.getContents().add(itemsType);
   *
   *  // Create a simple type, name it Sku, and set it to restrict the built-in type string.
   *  //
   *  XSDSimpleTypeDefinition sku = xsdFactory.createXSDSimpleTypeDefinition();
   *  sku.setName("Sku");
   *  sku.setBaseTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("string"));
   *
   *  // Create max exclusive facet, set its lexical value to be 100, and add it to the facet contents of Sku.
   *  //
   *  XSDPatternFacet skuPatternFacet = xsdFactory.createXSDPatternFacet();
   *  skuPatternFacet.setLexicalValue("/d{3}-[A-Z]{2}");
   *  sku.getFacetContents().add(skuPatternFacet);
   *
   *  // Add the completed simple Sku to the schema.
   *  //
   *  xsdSchema.getContents().add(sku);
   *
   *  // This is the <!-- SAMPLE --> <a name="purchase-order-end">result</a> that is produced.
   *  //
   *  //<b> &lt;?xml version="1.0" encoding="UTF-8"?></b>
   *  //<b> &lt;xsd:schema targetNamespace="http://nist.gov/po.xsd"</b>
   *  //<b>     xmlns:po="http://nist.gov/po.xsd" xmlns:xsd="http://www.w3.org/2001/XMLSchema"></b>
   *  //<b>     &lt;xsd:annotation></b>
   *  //<b>         &lt;xsd:documentation xml:lang="en">Purchase order schema for</b>
   *  //<b>             Example.com. Copyright 2000 Example.com. All rights reserved.&lt;/xsd:documentation></b>
   *  //<b>     &lt;/xsd:annotation></b>
   *  //<b>     &lt;xsd:element name="purchaseOrder" type="po:PurchaseOrderType"/></b>
   *  //<b>     &lt;xsd:element name="comment" type="xsd:string"/></b>
   *  //<b>     &lt;xsd:complexType name="PurchaseOrderType"></b>
   *  //<b>         &lt;xsd:sequence></b>
   *  //<b>             &lt;xsd:element name="shipTo" type="po:USAddress"/></b>
   *  //<b>             &lt;xsd:element name="billTo" type="po:USAddress"/></b>
   *  //<b>             &lt;xsd:element minOccurs="0" ref="po:comment"/></b>
   *  //<b>         &lt;/xsd:sequence></b>
   *  //<b>         &lt;xsd:attribute name="orderDate" type="xsd:Date"/></b>
   *  //<b>     &lt;/xsd:complexType></b>
   *  //<b>     &lt;xsd:complexType name="USAddress"></b>
   *  //<b>         &lt;xsd:sequence></b>
   *  //<b>             &lt;xsd:element name="name" type="xsd:string"/></b>
   *  //<b>             &lt;xsd:element name="street" type="xsd:string"/></b>
   *  //<b>             &lt;xsd:element name="city" type="xsd:string"/></b>
   *  //<b>             &lt;xsd:element name="state" type="xsd:string"/></b>
   *  //<b>             &lt;xsd:element name="zip" type="xsd:string"/></b>
   *  //<b>         &lt;/xsd:sequence></b>
   *  //<b>         &lt;xsd:attribute fixed="US" name="country" type="xsd:NMTOKEN"/></b>
   *  //<b>     &lt;/xsd:complexType></b>
   *  //<b>     &lt;xsd:complexType name="Items"></b>
   *  //<b>         &lt;xsd:sequence></b>
   *  //<b>             &lt;xsd:element name="item"></b>
   *  //<b>                 &lt;xsd:complexType></b>
   *  //<b>                     &lt;xsd:sequence></b>
   *  //<b>                         &lt;xsd:element name="productName" type="xsd:string"/></b>
   *  //<b>                         &lt;xsd:element name="quantity"></b>
   *  //<b>                             &lt;xsd:simpleType></b>
   *  //<b>                                 &lt;xsd:restriction base="xsd:positiveInteger"></b>
   *  //<b>                                     &lt;xsd:maxExclusive value="100"/></b>
   *  //<b>                                 &lt;/xsd:restriction></b>
   *  //<b>                             &lt;/xsd:simpleType></b>
   *  //<b>                         &lt;/xsd:element></b>
   *  //<b>                         &lt;xsd:element name="USPrice" type="xsd:decimal"/></b>
   *  //<b>                         &lt;xsd:element minOccurs="0" ref="po:comment"/></b>
   *  //<b>                         &lt;xsd:element minOccurs="0" name="shipDate" type="xsd:decimal"/></b>
   *  //<b>                     &lt;/xsd:sequence></b>
   *  //<b>                 &lt;/xsd:complexType></b>
   *  //<b>             &lt;/xsd:element></b>
   *  //<b>         &lt;/xsd:sequence></b>
   *  //<b>         &lt;xsd:attribute name="partNum" type="po:SKU" use="required"/></b>
   *  //<b>     &lt;/xsd:complexType></b>
   *  //<b>     &lt;xsd:simpleType name="Sku"></b>
   *  //<b>         &lt;xsd:restriction base="xsd:string"></b>
   *  //<b>             &lt;xsd:pattern value="/d{3}-[A-Z]{2}"/></b>
   *  //<b>         &lt;/xsd:restriction></b>
   *  //<b>     &lt;/xsd:simpleType></b>
   *  //<b> &lt;/xsd:schema></b>
   *
   *  return xsdSchema;
   *}
   *<!-- insert-end-purchase-order -->
   *</pre>
   */
  public XSDSchema initializePurchaseOrderSchema()
  // <!-- begin-purchase-order -->
  {
    // Build the <a href="http://www.w3.org/TR/xmlschema-0/#POSchema">Purchase Order Schema</a> from Primer 0.
    // The general approach, for efficiency, is to attach objects as late as possible.
    // The XML serialization is at the <a href="#purchase-order-end">end</a>.

    // Create the schema.
    //
    /*{@link */XSDSchema/*}*/ xsdSchema = XSDFactory.eINSTANCE.createXSDSchema();

    // If you want schema tags and references to schema types to be qualified, 
    // which is recommend, this is the recommended qualifier.
    //
    xsdSchema./*{@link XSDSchema#setSchemaForSchemaQNamePrefix */setSchemaForSchemaQNamePrefix/*}*/("xsd");

    // Set the target namespace.
    //
    xsdSchema./*{@link XSDSchema#setTargetNamespace */setTargetNamespace/*}*/("http://nist.gov/po.xsd");

    // Choose the prefix used for this schema's namespace and the {@link XSDSchema#getSchemaForSchema schema for schema}'s namespace.
    //
    Map<String, String> qNamePrefixToNamespaceMap = xsdSchema./*{@link XSDSchema#getQNamePrefixToNamespaceMap */getQNamePrefixToNamespaceMap/*}*/();
    qNamePrefixToNamespaceMap.put("po", xsdSchema.getTargetNamespace());
    qNamePrefixToNamespaceMap.put(xsdSchema.getSchemaForSchemaQNamePrefix(), /*{@link */XSDConstants/*}*/.SCHEMA_FOR_SCHEMA_URI_2001);

    // Create an annotation and add it to the schema.
    //
    /*{@link */XSDAnnotation/*}*/ xsdAnnotation = xsdFactory.createXSDAnnotation();
    xsdSchema./*{@link XSDSchema#getContents */getContents/*}*/().add(xsdAnnotation);

    // Create a documentation DOM element with no sourceURI attribute.
    // <b>NOTE</b>
    // Working with the contents of an annotation requires dropping down into the DOM model.
    // This imposes the additional requirement that the annotation must be {@link XSDSchema#getSchema attached} to a schema.
    // It also has the effect of calling {@link XSDSchema#updateElement()},
    // if the schema does not have an {@link XSDConcreteComponent#getElement element} or {@link XSDSchema#getDocument document} yet.
    //
    /*{@link */Element/* <em>Element</em>}*/ documentation = xsdAnnotation./*{@link XSDAnnotation#createUserInformation */createUserInformation/*}*/(null);

    // Use the DOM API to set the lang attribute and content of the documentation.
    //
    documentation./*{@link Element#setAttributeNS <em>*/setAttributeNS/*</em>}*/(XSDConstants.XML_NAMESPACE_URI_1998, "xml:lang", "en");
    String text = "Purchase order schema for Example.com.\nCopyright 2000 Example.com. All rights reserved.";
    documentation./*{@link Element#appendChild <em>*/appendChild/*</em>}*/(documentation.getOwnerDocument().createTextNode(text));

    // Use the DOM API to add the documentation to the element of the annotation.
    //
    xsdAnnotation./*{@link XSDAnnotation#getElement */getElement/*}*/().appendChild(documentation);

    // Create an element, name it purchaseOrder, 
    // and set it's type to the purchaseOrderType of this schema.
    // Add the purchaseOrder element to the schema.
    //
    /*{@link */XSDElementDeclaration/*}*/ purchaseOrder = xsdFactory.createXSDElementDeclaration();
    purchaseOrder./*{@link XSDNamedComponent#setName */setName/*}*/("purchaseOrder");
    purchaseOrder./*{@link XSDElementDeclaration#setTypeDefinition */setTypeDefinition/*}*/(xsdSchema./*{@link XSDConcreteComponent#resolveComplexTypeDefinition(String) */resolveComplexTypeDefinition/*}*/("PurchaseOrderType"));
    xsdSchema.getContents().add(purchaseOrder);

    // Create an element named command of the build-in type string.
    //
    XSDElementDeclaration comment = xsdFactory.createXSDElementDeclaration();
    comment.setName("comment");
    comment.setTypeDefinition(xsdSchema.getSchemaForSchema()./*{@link XSDConcreteComponent#resolveSimpleTypeDefinition(String) */resolveSimpleTypeDefinition/*}*/("string"));
    xsdSchema.getContents().add(comment);

    // Create a complex type and name it PurchaseOrderType.
    //
    /*{@link */XSDComplexTypeDefinition/*}*/ purchaseOrderType = xsdFactory.createXSDComplexTypeDefinition();
    purchaseOrderType./*{@link XSDNamedComponent#setName */setName/*}*/("PurchaseOrderType");

    // Create a sequence model group, and a particle to contain it.
    //
    /*{@link */XSDModelGroup/*}*/ purchaseOrderTypeSequence = xsdFactory.createXSDModelGroup();
    purchaseOrderTypeSequence./*{@link XSDModelGroup#setCompositor */setCompositor/*}*/(XSDCompositor.SEQUENCE_LITERAL);
    /*{@link */XSDParticle/*}*/ purchaseOrderTypeParticle = xsdFactory.createXSDParticle();
    purchaseOrderTypeParticle./*{@link XSDParticle#setContent */setContent/*}*/(purchaseOrderTypeSequence);

    // Create an element named shipTo of complex type USAddress, a particle to contain it,
    // and add the particle to the sequence.
    //
    XSDElementDeclaration shipTo = xsdFactory.createXSDElementDeclaration();
    shipTo.setName("shipTo");
    shipTo.setTypeDefinition(xsdSchema.resolveComplexTypeDefinition("USAddress"));
    XSDParticle shipToParticle = xsdFactory.createXSDParticle();
    shipToParticle.setContent(shipTo);
    purchaseOrderTypeSequence./*{@link XSDModelGroup#getContents */getContents/*}*/().add(shipToParticle);

    // Create an element named billTo of complex type USAddress, a particle to contain it,
    // and add the particle to the sequence.
    //
    XSDElementDeclaration billTo = xsdFactory.createXSDElementDeclaration();
    billTo.setName("billTo");
    billTo.setTypeDefinition(xsdSchema.resolveComplexTypeDefinition("USAddress"));
    XSDParticle billToParticle = xsdFactory.createXSDParticle();
    billToParticle.setContent(billTo);
    purchaseOrderTypeSequence.getContents().add(billToParticle);

    // Create an element reference to comment, a particle to contain it,
    // set the particle to be optional, and add the particle to the sequence.
    //
    XSDElementDeclaration commentRef = xsdFactory.createXSDElementDeclaration();
    commentRef./*{@link XSDElementDeclaration#setResolvedElementDeclaration */setResolvedElementDeclaration/*}*/(xsdSchema./*{@link XSDConcreteComponent#resolveElementDeclaration(String) */resolveElementDeclaration/*}*/("comment"));
    XSDParticle commentRefParticle = xsdFactory.createXSDParticle();
    commentRefParticle./*{@link XSDParticle#setMinOccurs(int) */setMinOccurs/*}*/(0);
    commentRefParticle.setContent(commentRef);
    purchaseOrderTypeSequence.getContents().add(commentRefParticle);

    // Create an element named items of complex type Items, a particle to contain it,
    // and add the particle to the sequence.
    //
    XSDElementDeclaration items = xsdFactory.createXSDElementDeclaration();
    items.setName("items");
    items.setTypeDefinition(xsdSchema.resolveComplexTypeDefinition("Items"));
    XSDParticle itemsParticle = xsdFactory.createXSDParticle();
    itemsParticle.setContent(items);
    purchaseOrderTypeSequence.getContents().add(billToParticle);

    // Set the completed content particle to be the content of the PurchaseOrderType.
    //
    purchaseOrderType./*{@link XSDComplexTypeDefinition#setContent */setContent/*}*/(purchaseOrderTypeParticle);

    // Create an attribute, name it orderDate, and set it's type to the built-in Date type.
    // Also create an attribute use to contain the attribute and add it to PurchaseOrderType's attribute contents.
    //
    /*{@link */XSDAttributeDeclaration/*}*/ orderDate = xsdFactory.createXSDAttributeDeclaration();
    orderDate./*{@link XSDNamedComponent#setName */setName/*}*/("orderDate");
    orderDate./*{@link XSDAttributeDeclaration#setTypeDefinition */setTypeDefinition/*}*/(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("date"));
    /*{@link */XSDAttributeUse/*}*/ orderDateAttributeUse = xsdFactory.createXSDAttributeUse();
    orderDateAttributeUse./*{@link XSDAttributeUse#setContent */setContent/*}*/(orderDate);
    purchaseOrderType./*{@link XSDComplexTypeDefinition#getAttributeContents */getAttributeContents/*}*/().add(orderDateAttributeUse);

    // Add the completed complex PurchaseOrderType to the schema.
    //
    xsdSchema.getContents().add(purchaseOrderType);
     
    // Create a complex type named USAddress.
    //
    XSDComplexTypeDefinition usAddress = xsdFactory.createXSDComplexTypeDefinition();
    usAddress.setName("USAddress");

    // Create a sequence model group, and a particle to contain it.
    //
    XSDModelGroup usAddressSequence = xsdFactory.createXSDModelGroup();
    usAddressSequence.setCompositor(XSDCompositor.SEQUENCE_LITERAL);
    XSDParticle usAddressParticle = xsdFactory.createXSDParticle();
    usAddressParticle.setContent(usAddressSequence);

    // Create an element named name of built-in type string, a particle to contain it,
    // and add the particle to the sequence.
    //
    XSDElementDeclaration name = xsdFactory.createXSDElementDeclaration();
    name.setName("name");
    name.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("string"));
    XSDParticle nameParticle = xsdFactory.createXSDParticle();
    nameParticle.setContent(name);
    usAddressSequence.getContents().add(nameParticle);

    // Create an element named street of built-in type string, a particle to contain it,
    // and add the particle to the sequence.
    //
    XSDElementDeclaration street = xsdFactory.createXSDElementDeclaration();
    street.setName("street");
    street.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("string"));
    XSDParticle streeParticle = xsdFactory.createXSDParticle();
    streeParticle.setContent(street);
    usAddressSequence.getContents().add(streeParticle);

    // Create an element named city of built-in type string, a particle to contain it,
    // and add the particle to the sequence.
    //
    XSDElementDeclaration city = xsdFactory.createXSDElementDeclaration();
    city.setName("city");
    city.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("string"));
    XSDParticle cityParticle = xsdFactory.createXSDParticle();
    cityParticle.setContent(city);
    usAddressSequence.getContents().add(cityParticle);

    // Create an element named state of built-in type string, a particle to contain it,
    // and add the particle to the sequence.
    //
    XSDElementDeclaration state = xsdFactory.createXSDElementDeclaration();
    state.setName("state");
    state.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("string"));
    XSDParticle stateParticle = xsdFactory.createXSDParticle();
    stateParticle.setContent(state);
    usAddressSequence.getContents().add(stateParticle);

    // Create an element named zip of built-in type string, a particle to contain it,
    // and add the particle to the sequence.
    //
    XSDElementDeclaration zip = xsdFactory.createXSDElementDeclaration();
    zip.setName("zip");
    zip.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("string"));
    XSDParticle zipParticle = xsdFactory.createXSDParticle();
    zipParticle.setContent(zip);
    usAddressSequence.getContents().add(zipParticle);

    // Set the completed content particle to be the content of USAddress.
    //
    usAddress.setContent(usAddressParticle);

    // Create an attribute, name it country, set it's type to the built-in NMTOKEN type,
    // set its constraints to be fixed, set the lexical value of the constraint to be "US".
    // Also create an attribute use to contain the attribute and add it to USAddress' attribute contents.
    //
    XSDAttributeDeclaration country = xsdFactory.createXSDAttributeDeclaration();
    country.setName("country");
    country.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("NMTOKEN"));
    XSDAttributeUse countryAttributeUse = xsdFactory.createXSDAttributeUse();
    countryAttributeUse.setContent(country);
    countryAttributeUse./*{@link XSDAttributeDeclaration#setConstraint(XSDConstraint) */setConstraint/*}*/(/*{@link */XSDConstraint/*}*/.FIXED_LITERAL);
    countryAttributeUse./*{@link XSDAttributeDeclaration#setLexicalValue */setLexicalValue/*}*/("US");
    usAddress.getAttributeContents().add(countryAttributeUse);

    // Add the completed complex USAddress to the schema.
    //
    xsdSchema.getContents().add(usAddress);

    // Create a complex type named Items.
    //
    XSDComplexTypeDefinition itemsType = xsdFactory.createXSDComplexTypeDefinition();
    itemsType.setName("Items");

    // Create a sequence model group, and a particle to contain it.
    //
    XSDModelGroup itemsTypeSequence = xsdFactory.createXSDModelGroup();
    itemsTypeSequence.setCompositor(XSDCompositor.SEQUENCE_LITERAL);
    XSDParticle itemsTypeParticle = xsdFactory.createXSDParticle();
    itemsTypeParticle.setContent(itemsTypeSequence);

    // Create an element and name it item.
    //
    XSDElementDeclaration item = xsdFactory.createXSDElementDeclaration();
    item.setName("item");

    // Create an anonymous complex type.
    //
    XSDComplexTypeDefinition anonymousComplexTypeDefinition = xsdFactory.createXSDComplexTypeDefinition();

    // Create a sequence model group, and a particle to contain it.
    //
    XSDModelGroup anonymousComplexTypeDefinitionSequence = xsdFactory.createXSDModelGroup();
    anonymousComplexTypeDefinitionSequence.setCompositor(XSDCompositor.SEQUENCE_LITERAL);
    XSDParticle anonymousTypeDefinitionParticle = xsdFactory.createXSDParticle();
    anonymousTypeDefinitionParticle.setContent(anonymousComplexTypeDefinitionSequence);

    // Create an element productName of built-in type string, a particle to contain it,
    // and add the particle to the sequence.
    //
    XSDElementDeclaration productName = xsdFactory.createXSDElementDeclaration();
    productName.setName("productName");
    productName.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("string"));
    XSDParticle productNameParticle = xsdFactory.createXSDParticle();
    productNameParticle.setContent(productName);
    anonymousComplexTypeDefinitionSequence.getContents().add(productNameParticle);

    // Create a particle to contain the item declaration and add it to the sequence.
    //
    XSDParticle itemParticle = xsdFactory.createXSDParticle();
    itemParticle.setContent(item);
    itemsTypeSequence.getContents().add(itemParticle);

    // Create an attribute, name it partNum, set its type to the SKU type,
    // Also create an attribute use to contain the attribute, set it to be required, 
    // and add it to Items' attribute contents.
    //
    XSDAttributeDeclaration partNum = xsdFactory.createXSDAttributeDeclaration();
    partNum.setName("partNum");
    partNum.setTypeDefinition(xsdSchema.resolveSimpleTypeDefinition("SKU"));
    XSDAttributeUse partNumAttributeUse = xsdFactory.createXSDAttributeUse();
    partNumAttributeUse.setContent(partNum);
    partNumAttributeUse.setUse(XSDAttributeUseCategory.REQUIRED_LITERAL);
    itemsType.getAttributeContents().add(partNumAttributeUse);

    // Set the completed content particle to be the content of the anonymous complex type.
    //
    anonymousComplexTypeDefinition.setContent(anonymousTypeDefinitionParticle);

    // Create an element  and name it quantity.
    //
    XSDElementDeclaration quantity = xsdFactory.createXSDElementDeclaration();
    quantity.setName("quantity");

    // Create an anonymous simple type and set it to restrict the built-in positiveInteger type.
    //
    /*{@link */XSDSimpleTypeDefinition/*}*/ anonymousSimpleTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
    anonymousSimpleTypeDefinition./*{@link XSDSimpleTypeDefinition#setBaseTypeDefinition */setBaseTypeDefinition/*}*/
      (xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("positiveInteger"));

    // Create max exclusive facet, set its lexical value to be 100, and add it to the facet contents of the anonymous simple type.
    //
    XSDMaxExclusiveFacet quantityMaxExclusiveFacet = xsdFactory.createXSDMaxExclusiveFacet();
    quantityMaxExclusiveFacet.setLexicalValue("100");
    anonymousSimpleTypeDefinition.getFacetContents().add(quantityMaxExclusiveFacet);

    // Set the completed anonymous type of the quantity element.
    //
    quantity.setAnonymousTypeDefinition(anonymousSimpleTypeDefinition);

    // Create a particle to hold the quantity element and add it to the sequence.
    //
    XSDParticle quantityParticle = xsdFactory.createXSDParticle();
    quantityParticle.setContent(quantity);
    anonymousComplexTypeDefinitionSequence.getContents().add(quantityParticle);

    // Create an element USPrice of built-in type decimal, a particle to contain it,
    // and add the particle to the sequence.
    //
    XSDElementDeclaration usPrice = xsdFactory.createXSDElementDeclaration();
    usPrice.setName("USPrice");
    usPrice.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("decimal"));
    XSDParticle usPriceParticle = xsdFactory.createXSDParticle();
    usPriceParticle.setContent(usPrice);
    anonymousComplexTypeDefinitionSequence.getContents().add(usPriceParticle);

    // Create an element reference to comment, a particle to contain it,
    // set the particle to be optional, and add the particle to the sequence.
    //
    XSDElementDeclaration anotherCommentRef = xsdFactory.createXSDElementDeclaration();
    anotherCommentRef.setResolvedElementDeclaration(xsdSchema.resolveElementDeclaration("comment"));
    XSDParticle anotherCommentRefParticle = xsdFactory.createXSDParticle();
    anotherCommentRefParticle.setMinOccurs(0);
    anotherCommentRefParticle.setContent(anotherCommentRef);
    anonymousComplexTypeDefinitionSequence.getContents().add(anotherCommentRefParticle);

    // Create an element USPrice of built-in type Date, a particle to contain it,
    // set the particle to be optional, and add it to the sequence.
    //
    XSDElementDeclaration shipDate = xsdFactory.createXSDElementDeclaration();
    shipDate.setName("shipDate");
    shipDate.setTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("decimal"));
    XSDParticle shipDateParticle = xsdFactory.createXSDParticle();
    shipDateParticle.setContent(shipDate);
    shipDateParticle.setMinOccurs(0);
    anonymousComplexTypeDefinitionSequence.getContents().add(shipDateParticle);

    // Set the completed content particle to be the content of Items.
    //
    itemsType.setContent(itemsTypeParticle);

    // Set the completed anonymous type of the item element.
    //
    item./*{@link XSDElementDeclaration#setAnonymousTypeDefinition */setAnonymousTypeDefinition/*}*/(anonymousComplexTypeDefinition);

    // Add the completed complex Items to the schema.
    //
    xsdSchema.getContents().add(itemsType);

    // Create a simple type, name it Sku, and set it to restrict the built-in type string.
    //
    XSDSimpleTypeDefinition sku = xsdFactory.createXSDSimpleTypeDefinition();
    sku.setName("Sku");
    sku.setBaseTypeDefinition(xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("string"));

    // Create max exclusive facet, set its lexical value to be 100, and add it to the facet contents of Sku.
    //
    XSDPatternFacet skuPatternFacet = xsdFactory.createXSDPatternFacet();
    skuPatternFacet.setLexicalValue("/d{3}-[A-Z]{2}");
    sku.getFacetContents().add(skuPatternFacet);

    // Add the completed simple Sku to the schema.
    //
    xsdSchema.getContents().add(sku);

    // This is the <!-- SAMPLE --> <a name="purchase-order-end">result</a> that is produced.
    //
    /// <?xml version="1.0" encoding="UTF-8"?>
    /// <xsd:schema targetNamespace="http://nist.gov/po.xsd"
    ///     xmlns:po="http://nist.gov/po.xsd" xmlns:xsd="http://www.w3.org/2001/XMLSchema">
    ///     <xsd:annotation>
    ///         <xsd:documentation xml:lang="en">Purchase order schema for
    ///             Example.com. Copyright 2000 Example.com. All rights reserved.</xsd:documentation>
    ///     </xsd:annotation>
    ///     <xsd:element name="purchaseOrder" type="po:PurchaseOrderType"/>
    ///     <xsd:element name="comment" type="xsd:string"/>
    ///     <xsd:complexType name="PurchaseOrderType">
    ///         <xsd:sequence>
    ///             <xsd:element name="shipTo" type="po:USAddress"/>
    ///             <xsd:element name="billTo" type="po:USAddress"/>
    ///             <xsd:element minOccurs="0" ref="po:comment"/>
    ///         </xsd:sequence>
    ///         <xsd:attribute name="orderDate" type="xsd:Date"/>
    ///     </xsd:complexType>
    ///     <xsd:complexType name="USAddress">
    ///         <xsd:sequence>
    ///             <xsd:element name="name" type="xsd:string"/>
    ///             <xsd:element name="street" type="xsd:string"/>
    ///             <xsd:element name="city" type="xsd:string"/>
    ///             <xsd:element name="state" type="xsd:string"/>
    ///             <xsd:element name="zip" type="xsd:string"/>
    ///         </xsd:sequence>
    ///         <xsd:attribute fixed="US" name="country" type="xsd:NMTOKEN"/>
    ///     </xsd:complexType>
    ///     <xsd:complexType name="Items">
    ///         <xsd:sequence>
    ///             <xsd:element name="item">
    ///                 <xsd:complexType>
    ///                     <xsd:sequence>
    ///                         <xsd:element name="productName" type="xsd:string"/>
    ///                         <xsd:element name="quantity">
    ///                             <xsd:simpleType>
    ///                                 <xsd:restriction base="xsd:positiveInteger">
    ///                                     <xsd:maxExclusive value="100"/>
    ///                                 </xsd:restriction>
    ///                             </xsd:simpleType>
    ///                         </xsd:element>
    ///                         <xsd:element name="USPrice" type="xsd:decimal"/>
    ///                         <xsd:element minOccurs="0" ref="po:comment"/>
    ///                         <xsd:element minOccurs="0" name="shipDate" type="xsd:decimal"/>
    ///                     </xsd:sequence>
    ///                 </xsd:complexType>
    ///             </xsd:element>
    ///         </xsd:sequence>
    ///         <xsd:attribute name="partNum" type="po:SKU" use="required"/>
    ///     </xsd:complexType>
    ///     <xsd:simpleType name="Sku">
    ///         <xsd:restriction base="xsd:string">
    ///             <xsd:pattern value="/d{3}-[A-Z]{2}"/>
    ///         </xsd:restriction>
    ///     </xsd:simpleType>
    /// </xsd:schema>

    return xsdSchema;
  }
  // <!-- end-purchase-order -->

  /**
   *<pre>
   *<!-- insert-begin-save-purchase-order -->
   *{
   *  // Saves the {@link #initializePurchaseOrderSchema Purchase Order Schema} to the given URI.
   *  //
   *  try
   *  {
   *    // Create a resource set and a resource with an extent.
   *    // Add the schema to the extent, add the resource to the set,
   *    // and save the resource.
   *    //
   *    {@link ResourceSet} resourceSet = new {@link ResourceSetImpl}();
   *    {@link Resource} resource = new {@link XSDResourceImpl}(URI.createURI(schemaURI));
   *    resource.{@link Resource#getContents getContents}().add(purchaseOrderSchema);
   *    resourceSet.{@link ResourceSet#getResources() getResources()}.add(resource);
   *    resource.{@link Resource#save(java.util.Map) save}(Collections.EMPTY_MAP);
   *  }
   *  catch (Exception exception)
   *  {
   *    System.out.println(exception.getLocalizedMessage());
   *    exception.printStackTrace();
   *  }
   *}
   *<!-- insert-end-save-purchase-order -->
   *</pre>
   */
  public void savePurchaseOrderSchema(String schemaURI)
  // <!-- begin-save-purchase-order -->
  {
    // Saves the {@link #initializePurchaseOrderSchema Purchase Order Schema} to the given URI.
    //
    try
    {
      // Create a resource set and a resource with an extent.
      // Add the schema to the extent, add the resource to the set,
      // and save the resource.
      //
      /*{@link */ResourceSet/*}*/ resourceSet = new /*{@link */ResourceSetImpl/*}*/();
      /*{@link */Resource/*}*/ resource = new /*{@link */XSDResourceImpl/*}*/(URI.createURI(schemaURI));
      resource./*{@link Resource#getContents */getContents/*}*/().add(purchaseOrderSchema);
      resourceSet./*{@link ResourceSet#getResources() */getResources()/*}*/.add(resource);
      resource./*{@link Resource#save(java.util.Map) */save/*}*/(Collections.EMPTY_MAP);
    }
    catch (Exception exception)
    {
      System.out.println(exception.getLocalizedMessage());
      exception.printStackTrace();
    }
  }
  // <!-- end-save-purchase-order -->

  /**
   *<pre>
   *<!-- insert-begin-print-schema -->
   *{
   *  // Prints the schema loaded from the given URI.
   *  //
   *
   *  // The code assumes that the following registration has taken place.
   *  // Currently, unless special handling is implemented in the resource set, 
   *  // only files ending with a .xsd extension will be loaded as schemas.
   *  //
   *  // Resource.Factory.Registry.{@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#INSTANCE INSTANCE}.{@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#getExtensionToFactoryMap() getExtensionToFactoryMap}().put("xsd", new {@link XSDResourceFactoryImpl}());
   *
   *  try
   *  {
   *    // Create a resource set, 
   *    // turn on the ability of the {@link XSDParser} to associate line and column information with the DOM,
   *    // and then load the main schema file.
   *    //
   *    {@link ResourceSet} resourceSet = new {@link ResourceSetImpl}();
   *    resourceSet.getLoadOptions().put(XSDResourceImpl.XSD_TRACK_LOCATION, Boolean.TRUE);
   *    {@link XSDResourceImpl} xsdSchemaResource = (XSDResourceImpl)resourceSet.{@link ResourceSet#getResource getResource}(URI.createURI(xsdSchemaURI), true);
   *
   *    // Iterate over all the resources, i.e., the main resource 
   *    // and those that have been included, imported, or redefined.
   *    //
   *    for (Iterator resources = resourceSet.{@link ResourceSet#getResources getResources}().iterator(); resources.hasNext(); )
   *    {
   *      // Check for schema resources.
   *      //
   *      {@link Resource} resource = (Resource)resources.next();
   *      if (resource instanceof XSDResourceImpl)
   *      {
   *        XSDResourceImpl xsdResource = (XSDResourceImpl)resource;
   *
   *        // Get the schema's element and serialized it to System.out.
   *        //
   *        {@link XSDSchema} xsdSchema = xsdResource.{@link XSDResourceImpl#getSchema getSchema}();
   *        System.out.println("\n&lt;!-- *** " + xsdResource.{@link Resource#getURI getURI}() + " *** -->\n");
   *        {@link #printComponent printComponent}(System.out, xsdSchema);
   *
   *        // If there are no errors from just trying to parse a DOM, 
   *        // validate the schema.
   *        //
   *        if (xsdSchema.{@link XSDSchema#getAllDiagnostics getAllDiagnostics}().isEmpty())
   *        {
   *          xsdSchema.validate();
   *        }
   *
   *        // Process each of the diagnostics.
   *        //
   *        for (Iterator diagnostics = xsdSchema.getAllDiagnostics().iterator(); diagnostics.hasNext(); )
   *        {
   *          {@link XSDDiagnostic} xsdDiagnostic = (XSDDiagnostic)diagnostics.next();
   *          String localizedSeverity = 
   *            XSDPlugin.INSTANCE.{@link XSDPlugin#getString(String,Object[]) getString}("_UI_XSDDiagnosticSeverity_" + xsdDiagnostic.{@link XSDDiagnostic#getSeverity getSeverity}());
   *          System.out.println
   *            (XSDPlugin.INSTANCE.getString
   *              ("_UI_DiagnosticFileLineColumn_message", 
   *               new Object [] 
   *               { 
   *                 localizedSeverity, 
   *                 xsdDiagnostic.{@link XSDDiagnostic#getLocationURI getLocationURI}(), 
   *                 new Integer(xsdDiagnostic.{@link XSDDiagnostic#getLine getLine}()), 
   *                 new Integer(xsdDiagnostic.{@link XSDDiagnostic#getColumn getColumn}()) 
   *               }));
   *
   *          System.out.println(xsdDiagnostic.{@link XSDDiagnostic#getMessage getMessage}());
   *        }
   *      }
   *    }
   *  }
   *  catch (Exception exception)
   *  {
   *    System.out.println(exception.getLocalizedMessage());
   *    exception.printStackTrace();
   *  }
   *}
   *<!-- insert-end-print-schema -->
   *</pre>
   */
  public void printSchema(String xsdSchemaURI)
  // <!-- begin-print-schema -->
  {
    // Prints the schema loaded from the given URI.
    //

    // The code assumes that the following registration has taken place.
    // Currently, unless special handling is implemented in the resource set, 
    // only files ending with a .xsd extension will be loaded as schemas.
    //
    // Resource.Factory.Registry./*{@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#INSTANCE */INSTANCE/*}*/./*{@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#getExtensionToFactoryMap() */getExtensionToFactoryMap/*}*/().put("xsd", new /*{@link */XSDResourceFactoryImpl/*}*/());

    try
    {
      // Create a resource set, 
      // turn on the ability of the {@link XSDParser} to associate line and column information with the DOM,
      // and then load the main schema file.
      //
      /*{@link */ResourceSet/*}*/ resourceSet = new /*{@link */ResourceSetImpl/*}*/();
      resourceSet.getLoadOptions().put(XSDResourceImpl.XSD_TRACK_LOCATION, Boolean.TRUE);
      resourceSet./*{@link ResourceSet#getResource */getResource/*}*/(URI.createURI(xsdSchemaURI), true);

      // Iterate over all the resources, i.e., the main resource 
      // and those that have been included, imported, or redefined.
      //
      for (/*{@link */Resource/*}*/ resource : resourceSet./*{@link ResourceSet#getResources */getResources/*}*/())
      {
        // Check for schema resources.
        //
        if (resource instanceof XSDResourceImpl)
        {
          XSDResourceImpl xsdResource = (XSDResourceImpl)resource;

          // Get the schema's element and serialized it to System.out.
          //
          /*{@link */XSDSchema/*}*/ xsdSchema = xsdResource./*{@link XSDResourceImpl#getSchema */getSchema/*}*/();
          System.out.println("\n<" + "!-- *** " + xsdResource./*{@link Resource#getURI */getURI/*}*/() + " *** -->\n");
          /*{@link #printComponent */printComponent/*}*/(System.out, xsdSchema);

          // If there are no errors from just trying to parse a DOM, 
          // validate the schema.
          //
          if (xsdSchema./*{@link XSDSchema#getAllDiagnostics */getAllDiagnostics/*}*/().isEmpty())
          {
            xsdSchema.validate();
          }

          // Process each of the diagnostics.
          //
          for (/*{@link */XSDDiagnostic/*}*/ xsdDiagnostic : xsdSchema.getAllDiagnostics())
          {
            String localizedSeverity = 
              XSDPlugin.INSTANCE./*{@link XSDPlugin#getString(String,Object[]) */getString/*}*/("_UI_XSDDiagnosticSeverity_" + xsdDiagnostic./*{@link XSDDiagnostic#getSeverity */getSeverity/*}*/());
            System.out.println
              (XSDPlugin.INSTANCE.getString
                ("_UI_DiagnosticFileLineColumn_message", 
                 new Object [] 
                 { 
                   localizedSeverity, 
                   xsdDiagnostic./*{@link XSDDiagnostic#getLocationURI */getLocationURI/*}*/(), 
                   xsdDiagnostic./*{@link XSDDiagnostic#getLine */getLine/*}*/(), 
                   xsdDiagnostic./*{@link XSDDiagnostic#getColumn */getColumn/*}*/() 
                 }));

            System.out.println(xsdDiagnostic./*{@link XSDDiagnostic#getMessage */getMessage/*}*/());
          }
        }
      }
    }
    catch (Exception exception)
    {
      System.out.println(exception.getLocalizedMessage());
      exception.printStackTrace();
    }
  }
  // <!-- end-print-schema -->

  /**
   *<pre>
   *<!-- insert-begin-print-component -->
   *{
   *  // Print a component's element using Xerces.
   *  //
   *
   *  // Get the component's element and create one if there isn't one already.
   *  //
   *  {@link Element} element = xsdConcreteComponent.{@link XSDConcreteComponent#getElement getElement}();
   *  if (element == null)
   *  {
   *    xsdConcreteComponent.{@link XSDConcreteComponent#updateElement updateElement}();
   *    element = xsdConcreteComponent.getElement();
   *  }
   *
   *  if (element != null)
   *  {
   *    try
   *    {
   *      // OutputFormat outputFormat = new OutputFormat(element.getOwnerDocument());
   *      // outputFormat.setLineWidth(80);
   *      // outputFormat.setIndenting(true);
   *      // outputFormat.setIndent(4);
   *      // outputFormat.setPreserveSpace(false);
   *      // XMLSerializer serializer = new XMLSerializer(outputStream, outputFormat);
   *      // serializer.serialize(element);
   *
   *      TransformerFactory transformerFactory = TransformerFactory.newInstance();
   *      Transformer transformer = transformerFactory.newTransformer();
   *
   *      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
   *      transformer.setOutputProperty(OutputKeys.METHOD, "xml");
   *
   *      transformer.transform(new DOMSource(element.getOwnerDocument()), new StreamResult(outputStream));
   *    }
   *    catch (TransformerException exception)
   *    {
   *      System.out.println(exception.getLocalizedMessage());
   *      exception.printStackTrace();
   *    }
   *  }
   *}
   *<!-- insert-end-print-component -->
   *</pre>
   */
  public void printComponent(OutputStream outputStream, XSDConcreteComponent xsdConcreteComponent)
  // <!-- begin-print-component -->
  {
    // Print a component's element using Xerces.
    //

    // Get the component's element and create one if there isn't one already.
    //
    /*{@link */Element/*}*/ element = xsdConcreteComponent./*{@link XSDConcreteComponent#getElement */getElement/*}*/();
    if (element == null)
    {
      xsdConcreteComponent./*{@link XSDConcreteComponent#updateElement */updateElement/*}*/();
      element = xsdConcreteComponent.getElement();
    }

    if (element != null)
    {
      try
      {
        // /*{@link */OutputFormat/*}*/ outputFormat = new OutputFormat(element.getOwnerDocument());
        // outputFormat.setLineWidth(80);
        // outputFormat.setIndenting(true);
        // outputFormat.setIndent(4);
        // outputFormat.setPreserveSpace(false);
        // /*{@link */XMLSerializer/*}*/ serializer = new XMLSerializer(outputStream, outputFormat);
        // serializer.serialize(element);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
  
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.METHOD, "xml");
  
        transformer.transform(new DOMSource(element.getOwnerDocument()), new StreamResult(outputStream));
      }
      catch (TransformerException exception)
      {
        System.out.println(exception.getLocalizedMessage());
        exception.printStackTrace();
      }
    }
  }
  // <!-- end-print-component -->

  /**
   *<pre>
   *<!-- insert-begin-create-schema -->
   *{
   *  // Create a schema from an element.
   *  //
   *  // Test if the element is really a schema element.
   *  //
   *  if (element.getLocalName().equals("schema") && 
   *        {@link XSDConstants}.{@link XSDConstants#isSchemaForSchemaNamespace isSchemaForSchemaNamespace}(element.getNamespaceURI()))
   *  {
   *    // Create the schema.
   *    //
   *    {@link XSDSchema} xsdSchema = XSDFactory.eINSTANCE.{@link XSDFactory#createXSDSchema createXSDSchema}();
   *
   *    // Set the element to the schema.
   *    // This it will build the corresponding component structure.
   *    //
   *    xsdSchema.{@link XSDConcreteComponent#setElement setElement}(element);
   *
   *    return xsdSchema;
   *  }
   *  else
   *  {
   *    return null;
   *  }
   *}
   *<!-- insert-end-create-schema -->
   *</pre>
   */
  public XSDSchema createSchema(Element element)
  // <!-- begin-create-schema -->
  {
    // Create a schema from an element.
    //
    // Test if the element is really a schema element.
    //
    if (element.getLocalName().equals("schema") && 
          /*{@link */XSDConstants/*}*/./*{@link XSDConstants#isSchemaForSchemaNamespace */isSchemaForSchemaNamespace/*}*/(element.getNamespaceURI()))
    {
      // Create the schema.
      //
      /*{@link */XSDSchema/*}*/ xsdSchema = XSDFactory.eINSTANCE./*{@link XSDFactory#createXSDSchema */createXSDSchema/*}*/();

      // Set the element to the schema.
      // This it will build the corresponding component structure.
      //
      xsdSchema./*{@link XSDConcreteComponent#setElement */setElement/*}*/(element);

      return xsdSchema;
    }
    else
    {
      return null;
    }
  }
  // <!-- end-create-schema -->

  /**
   *<pre>
   *<!-- insert-begin-trace-loading -->
   *{
   *  // Traces a resource set's loading behavior when loading the given URI.
   *  //
   *  // The code assumes that the following registration has taken place.
   *  // Currently, unless special handling is implemented in the resource set, 
   *  // only files ending with a .xsd extension will be loaded as schemas.
   *  //
   *  // Resource.Factory.Registry.{@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#INSTANCE INSTANCE}.{@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#getExtensionToFactoryMap() getExtensionToFactoryMap}().put("xsd", new {@link XSDResourceFactoryImpl}());
   *
   *  try
   *  {
   *    // Create a resource set.
   *    //
   *    {@link ResourceSet} resourceSet = 
   *      new {@link ResourceSetImpl}()
   *      {
   *        public Resource getResource({@link URI} uri, boolean loadOnDemand) 
   *        {
   *          Resource result = super.{@link ResourceSet#getResource getResource}(uri, true);
   *          System.out.println("&lt;!-- loaded: " + uri + " --> " + result);
   *          return result;
   *        }
   *      };
   *
   *    // Create a derived URIConverter to track normalization.
   *    //
   *    resourceSet.{@link ResourceSet#getURIConverter getURIConverter}().{@link org.eclipse.emf.ecore.resource.URIConverter#getURIMap getURIMap}().put
   *      (URI.createURI("http://www.example.com/logical.xsd"), 
   *       URI.createURI("file://c:/physical.xsd"));
   *
   *    // Load the schema from the URI.
   *    //
   *    {@link XSDResourceImpl} xsdSchemaResource = (XSDResourceImpl)resourceSet.getResource(URI.createURI(xsdSchemaURI), true);
   *
   *    // Iterate over all the resources, i.e., the main resource 
   *    // and those that have been included, imported, or redefined.
   *    //
   *    for (Iterator resources = resourceSet.{@link ResourceSet#getResources getResources}().iterator(); resources.hasNext(); )
   *    {
   *      // Check for schema resources.
   *      //
   *      {@link Resource} resource = (Resource)resources.next();
   *      if (resource instanceof XSDResourceImpl)
   *      {
   *        XSDResourceImpl xsdResource = (XSDResourceImpl)resource;
   *
   *        // Iterate over the schema's content's looking for directives.
   *        //
   *        {@link XSDSchema} xsdSchema = xsdResource.{@link XSDResourceImpl#getSchema getSchema}();
   *        for (Iterator contents = xsdSchema.{@link XSDSchema#getContents getContents}().iterator(); contents.hasNext(); )
   *        {
   *          {@link XSDSchemaContent} xsdSchemaContent = (XSDSchemaContent)contents.next();
   *          if (xsdSchemaContent instanceof {@link XSDSchemaDirective})
   *          {
   *            // Check if the directive resolved to a schema.
   *            //
   *            XSDSchemaDirective xsdSchemaDirective = (XSDSchemaDirective)xsdSchemaContent;
   *            if (xsdSchemaDirective.{@link XSDSchemaDirective#getResolvedSchema getResolvedSchema}() == null)
   *            {
   *              System.out.println("Unresolved schema in " + xsdResource.{@link Resource#getURI getURI}());
   *              {@link #printComponent printComponent}(System.out, xsdSchemaDirective);
   *            }
   *          }
   *        }
   *      }
   *    }
   *  }
   *  catch (Exception exception)
   *  {
   *    System.out.println(exception.getLocalizedMessage());
   *    exception.printStackTrace();
   *  }
   *}
   *<!-- insert-end-trace-loading -->
   *</pre>
   */
  public void traceLoading(String xsdSchemaURI)
  // <!-- begin-trace-loading -->
  {
    // Traces a resource set's loading behavior when loading the given URI.
    //
    // The code assumes that the following registration has taken place.
    // Currently, unless special handling is implemented in the resource set, 
    // only files ending with a .xsd extension will be loaded as schemas.
    //
    // Resource.Factory.Registry./*{@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#INSTANCE */INSTANCE/*}*/./*{@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#getExtensionToFactoryMap() */getExtensionToFactoryMap/*}*/().put("xsd", new /*{@link */XSDResourceFactoryImpl/*}*/());

    try
    {
      // Create a resource set.
      //
      /*{@link */ResourceSet/*}*/ resourceSet = 
        new /*{@link */ResourceSetImpl/*}*/()
        {
          @Override
          public Resource getResource(/*{@link */URI/*}*/ uri, boolean loadOnDemand) 
          {
            Resource result = super./*{@link ResourceSet#getResource */getResource/*}*/(uri, true);
            System.out.println("<" + "!-- loaded: " + uri + " --> " + result);
            return result;
          }
        };

      // Create a derived URIConverter to track normalization.
      //
      resourceSet./*{@link ResourceSet#getURIConverter */getURIConverter/*}*/()./*{@link URIConverter#getURIMap */getURIMap/*}*/().put
        (URI.createURI("http://www.example.com/logical.xsd"), 
         URI.createURI("file://c:/physical.xsd"));

      // Load the schema from the URI.
      //
      resourceSet.getResource(URI.createURI(xsdSchemaURI), true);

      // Iterate over all the resources, i.e., the main resource 
      // and those that have been included, imported, or redefined.
      //
      for (/*{@link */Resource/*}*/ resource : resourceSet./*{@link ResourceSet#getResources */getResources/*}*/())
      {
        // Check for schema resources.
        //
        if (resource instanceof XSDResourceImpl)
        {
          XSDResourceImpl xsdResource = (XSDResourceImpl)resource;

          // Iterate over the schema's content's looking for directives.
          //
          /*{@link */XSDSchema/*}*/ xsdSchema = xsdResource./*{@link XSDResourceImpl#getSchema */getSchema/*}*/();
          for (/*{@link */XSDSchemaContent/*}*/ xsdSchemaContent  : xsdSchema./*{@link XSDSchema#getContents */getContents/*}*/())
          {
            if (xsdSchemaContent instanceof /*{@link */XSDSchemaDirective/*}*/)
            {
              // Check if the directive resolved to a schema.
              //
              XSDSchemaDirective xsdSchemaDirective = (XSDSchemaDirective)xsdSchemaContent;
              if (xsdSchemaDirective./*{@link XSDSchemaDirective#getResolvedSchema */getResolvedSchema/*}*/() == null)
              {
                System.out.println("Unresolved schema in " + xsdResource./*{@link Resource#getURI */getURI/*}*/());
                /*{@link #printComponent */printComponent/*}*/(System.out, xsdSchemaDirective);
              }
            }
          }
        }
      }
    }
    catch (Exception exception)
    {
      System.out.println(exception.getLocalizedMessage());
      exception.printStackTrace();
    }
  }
  // <!-- end-trace-loading -->

  /**
   *<pre>
   *<!-- insert-begin-clone-component -->
   *{
   *  // A component can be cloned directly or by cloning the underlying DOM.
   *  // By cloning the DOM you ensure that &lt;annotation>s, non-schema namespace attributes, and formatting are preserved.
   *  //
   *  if (preserveDOM)
   *  {
   *    // If there is an element to clone.
   *    //
   *    Element element = xsdConcreteComponent.{@link XSDConcreteComponent#getElement() }getElement();
   *    if (element != null)
   *    {
   *      // Clone the DOM using the DOM API, and create the same type of component to hold it.
   *      //
   *      Element clonedElement = (Element)element.cloneNode(true);
   *      XSDConcreteComponent result = (XSDConcreteComponent)XSDFactory.eINSTANCE.create(xsdConcreteComponent.eClass());
   *      result.setElement(clonedElement);
   *      return result;
   *    }
   *  }
   *
   *  // Clone just the model itself so that a new DOM will need to be created to serialize it.
   *  //
   *  XSDConcreteComponent result = xsdConcreteComponent.cloneConcreteComponent(true, false);
   *  return result;
   *}
   *<!-- insert-end-clone-component -->
   *</pre>
   */
  public XSDConcreteComponent cloneComponent(XSDConcreteComponent xsdConcreteComponent, boolean preserveDOM)
  // <!-- begin-clone-component -->
  {
    // A component can be cloned directly or by cloning the underlying DOM.
    // By cloning the DOM you ensure that &lt;annotation>s, non-schema namespace attributes, and formatting are preserved.
    //
    if (preserveDOM)
    {
      // If there is an element to clone.
      //
      Element element = xsdConcreteComponent./*{@link XSDConcreteComponent#getElement() /*}*/getElement();
      if (element != null)
      {
        // Clone the DOM using the DOM API, and create the same type of component to hold it.
        //
        Element clonedElement = (Element)element.cloneNode(true);
        XSDConcreteComponent result = (XSDConcreteComponent)XSDFactory.eINSTANCE.create(xsdConcreteComponent.eClass());
        result.setElement(clonedElement);
        return result;
      }
    }

    // Clone just the model itself so that a new DOM will need to be created to serialize it.
    //
    XSDConcreteComponent result = xsdConcreteComponent.cloneConcreteComponent(true, false);
    return result;
  }
  // <!-- end-clone-component -->

  /**
   *<pre>
   *<!-- insert-begin-cross-reference-test -->
   *{
   *  // Test cross references for the meta schemas.
   *  //
   *  XSDSchema xsdSchemaForSchema = XSDUtil.{@link XSDUtil#getSchemaForSchema getSchemaForSchema}(XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);
   *  ResourceSet resourceSet = xsdSchemaForSchema.{@link org.eclipse.emf.ecore.EObject#eResource() eResource}().{@link Resource#getResourceSet getResourceSet}();
   *
   *  out.println("Show uses of the string datatype within the meta schemas themselves.");
   *  Collection usages = {@link XSDUtil.UsageCrossReferencer XSDUtil.UsageCrossReferencer}.find(xsdSchemaForSchema.resolveSimpleTypeDefinition("string"), resourceSet);
   *  XSDUtil.UsageCrossReferencer.print(out, usages);
   *
   *  out.println("Show uses of the ur-type URI");
   *  Map xsdURICrossReferences = {@link XSDUtil.URICrossReferencer XSDUtil.URICrossReferencer}.find(XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001 + "#anyType",  resourceSet);
   *  XSDUtil.URICrossReferencer.print(out, xsdURICrossReferences);
   *
   *  out.println("Show all named components and their uses in the schema of the anySimpleType.");
   *  Map xsdNamedComponentUsage = 
   *    {@link XSDUtil.XSDNamedComponentCrossReferencer XSDUtil.XSDNamedComponentCrossReferencer}.find(xsdSchemaForSchema.resolveSimpleTypeDefinition("anySimpleType").getSchema());
   *  XSDUtil.XSDNamedComponentCrossReferencer.print(out, xsdNamedComponentUsage);
   *
   *  out.println("Test that the URI of evey object in the schema for schemas can be resolved.");
   *  for (Iterator contents = xsdSchemaForSchema.{@link org.eclipse.emf.ecore.EObject#eAllContents() eAllContents}(); contents.hasNext(); )
   *  {
   *    XSDConcreteComponent xsdConcreteComponent = (XSDConcreteComponent)contents.next();
   *    URI uri = EcoreUtil.{@link EcoreUtil#getURI getURI}(xsdConcreteComponent);
   *    if (resourceSet.{@link ResourceSet#getEObject getEObject}(uri, false) != xsdConcreteComponent)
   *    {
   *      out.println("BAD URI: " + uri);
   *    }
   *  }
   *}
   *<!-- insert-end-cross-reference-test -->
   *</pre>
   */
  public void crossReferenceTest(PrintStream out)
  // <!-- begin-cross-reference-test -->
  {
    // Test cross references for the meta schemas.
    //
    XSDSchema xsdSchemaForSchema = XSDUtil./*{@link XSDUtil#getSchemaForSchema */getSchemaForSchema/*}*/(XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001);
    ResourceSet resourceSet = xsdSchemaForSchema./*{@link EObject#eResource */eResource/*}*/()./*{@link Resource#getResourceSet */getResourceSet/*}*/();

    out.println("Show uses of the string datatype within the meta schemas themselves.");
    Collection<EStructuralFeature.Setting> usages = /*{@link XSDUtil.UsageCrossReferencer */XSDUtil.UsageCrossReferencer/*}*/.find(xsdSchemaForSchema.resolveSimpleTypeDefinition("string"), resourceSet);
    EcoreUtil.CrossReferencer.print(out, usages);

    out.println("Show uses of the ur-type URI");
    Map<EObject, Collection<EStructuralFeature.Setting>> xsdURICrossReferences = /*{@link XSDUtil.URICrossReferencer */XSDUtil.URICrossReferencer/*}*/.find(XSDConstants.SCHEMA_FOR_SCHEMA_URI_2001 + "#anyType",  resourceSet);
    EcoreUtil.CrossReferencer.print(out, xsdURICrossReferences);

    out.println("Show all named components and their uses in the schema of the anySimpleType.");
    Map<EObject, Collection<EStructuralFeature.Setting>> xsdNamedComponentUsage = 
      /*{@link XSDUtil.XSDNamedComponentCrossReferencer */XSDUtil.XSDNamedComponentCrossReferencer/*}*/.find(xsdSchemaForSchema.resolveSimpleTypeDefinition("anySimpleType").getSchema());
    EcoreUtil.CrossReferencer.print(out, xsdNamedComponentUsage);

    out.println("Test that the URI of evey object in the schema for schemas can be resolved.");
    for (Iterator<EObject> contents = xsdSchemaForSchema./*{@link EObject#eAllContents */eAllContents/*}*/(); contents.hasNext(); )
    {
      XSDConcreteComponent xsdConcreteComponent = (XSDConcreteComponent)contents.next();
      URI uri = EcoreUtil./*{@link EcoreUtil#getURI */getURI/*}*/(xsdConcreteComponent);
      if (resourceSet./*{@link ResourceSet#getEObject */getEObject/*}*/(uri, false) != xsdConcreteComponent)
      {
        out.println("BAD URI: " + uri);
      }
    }
  }
  // <!-- end-cross-reference-test -->
}
