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
 * $Id: XSDGenerateHTML.java,v 1.5 2008/12/22 14:25:33 emerks Exp $
 */
package org.eclipse.xsd.example;


import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDCardinality;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDEnumerationFacet;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDFractionDigitsFacet;
import org.eclipse.xsd.XSDLengthFacet;
import org.eclipse.xsd.XSDMaxExclusiveFacet;
import org.eclipse.xsd.XSDMaxInclusiveFacet;
import org.eclipse.xsd.XSDMaxLengthFacet;
import org.eclipse.xsd.XSDMinExclusiveFacet;
import org.eclipse.xsd.XSDMinInclusiveFacet;
import org.eclipse.xsd.XSDMinLengthFacet;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTerm;
import org.eclipse.xsd.XSDTotalDigitsFacet;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.XSDVariety;
import org.eclipse.xsd.XSDWhiteSpaceFacet;
import org.eclipse.xsd.XSDWildcard;
import org.eclipse.xsd.impl.XSDNamedComponentImpl;
import org.eclipse.xsd.util.XSDConstants;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;
import org.eclipse.xsd.util.XSDResourceImpl;


/**
 * Generates HTML annotated documentation that summarizes the built-in simple type hierarchy.
 * It implements the method {@link #run run}, 
 * which is called just like main during headless workbench invocation.
 * <p>
 * You can execute this example by running 
 *<pre>
 *  xsd-generate-schema-for-schema-html.bat
 *</pre>
 * from the directory:
 *<pre>
 *  plugins/org.eclipse.xsd.example/data/
 *</pre>
 * This script uses the file 
 *<pre>
 *  plugins/org.eclipse.xsd.example/SampleMarkup.xml
 *</pre>
 * for annotations. 
 * The resultin HTML document is stored in <code>SchemaForSchema.html</code> 
 * and an <code>index.html</code> is provided frame-based viewing of the document.
 * The script
 *<pre>
 *  xsd-generate-html.bat
 *</pre>
 * allows you to pass in your own annotations.
 * </p>
 */
public class XSDGenerateHTML implements IApplication
{
  {
    // This is needed because we can't have the following in the plugin.xml
    //
    //   <extension point = "com.ibm.etools.emf.extension_parser">
    //     <parser type="xsd" class="org.eclipse.xsd.util.XSDResourceFactoryImpl"/>
    //   </extension>
    //
    // The com.ibm.etools.xsdmodel plugin, shipped with WSAD, has a conflicting registration for this.
    //
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());
  }

  /**
   * The map from schema type to Java class.
   */
  public Map<String, String> schemaTypeToJavaClassMap = new HashMap<String, String>();

  /**
   * The map from String keys to documentation.
   * @see #readMarkup
   * @see #handleMarkup
   */
  public Map<String, String> contentDocumentationMap = new HashMap<String, String>();

  /**
   * The map from String keys to documentation for {@link XSDElementDeclaration}.
   * @see #readMarkup
   * @see #handleMarkup
   */
  public Map<String, String> elementDeclarationMarkupMap = new HashMap<String, String>();

  /**
   * The map from String keys to documentation for {@link XSDAttributeDeclaration}s.
   * @see #readMarkup
   * @see #handleMarkup
   */
  public Map<String, String> attributeDeclarationMarkupMap = new HashMap<String, String>();

  /**
   * The map from {@link XSDElementDeclaration} to an anchor string.
   */
  public Map<XSDElementDeclaration, XSDElementDeclaration> specialAnchorMap = new HashMap<XSDElementDeclaration, XSDElementDeclaration>();

  /**
   * The list of anchors in <a href="http://www.w3.org/TR/xmlschema-1/">Part 1</a>.
   */
  protected List<String> part1Anchors =
    Arrays.asList
     (new String []
      {
        "all",
        "annotation",
        "any",
        "anyAttribute",
        "appinfo",
        "attribute",
        "attributeGroup",
        "choice",
        "complexContent",
        "complexContent::extension",
        "complexContent::restriction",
        "complexType",
        "documentation",
        "element",
        "field",
        "group",
        "import",
        "include",
        "key",
        "keyref",
        "list",
        "notation",
        "redefine",
        "restriction",
        "schema",
        "selector",
        "sequence",
        "simpleContent",
        // "simpleContent::attributeGroup",
        "simpleContent::extension",
        "simpleContent::restriction",
        "simpleType",
        "union",
        "unique",
      });

  /**
   * The list of components in <a href="http://www.w3.org/TR/xmlschema-1/">Part 1</a>.
   */
  List<String> part1Components =
    Arrays.asList
     (new String []
      {
        "all", "Model_Group_details Particle_details", 
        "annotation", "Annotation_details",
        "any", "Wildcard_details",
        "anyAttribute", "Wildcard_details",
        "appinfo", "Annotation_details",
        "attribute", "Attribute_Declaration_details AU_details",
        "attributeGroup", "Attribute_Group_Definition_details",
        "choice", "Model_Group_details Particle_details",
        "complexContent", "Complex_Type_Definition_details",
        "complexContent::extension", "Complex_Type_Definition_details",
        "complexContent::restriction", "Complex_Type_Definition_details",
        "complexType", "Complex_Type_Definition_details",
        "documentation", "Annotation_details",
        "element", "Element_Declaration_details Particle_details",
        "field", "Identity-constraint_Definition_details",
        "group", "Model_Group_Definition_details Particle_details",
        "import", "",
        "include", "",
        "key", "Identity-constraint_Definition_details",
        "keyref", "Identity-constraint_Definition_details",
        "list", "Simple_Type_Definition_details",
        "notation", "Notation_Declaration_details",
        "redefine", "",
        "restriction", "Simple_Type_Definition_details",
        "schema", "Schema_details",
        "selector", "Identity-constraint_Definition_details",
        "sequence", "Model_Group_details Particle_details",
        "simpleContent", "Complex_Type_Definition_details",
        // "simpleContent::attributeGroup", "",
        "simpleContent::extension", "Complex_Type_Definition_details",
        "simpleContent::restriction", "Complex_Type_Definition_details",
        "simpleType", "Simple_Type_Definition_details",
        "union", "Simple_Type_Definition_details",
        "unique", "Identity-constraint_Definition_details",
      });

  /**
   * The list of anchors in <a href="http://www.w3.org/TR/xmlschema-2/">Part 2</a>.
   */
  protected List<String> part2Anchors =
    Arrays.asList
      (new String []
       {
        "enumeration",
        "fractionDigits",
        "length",
        "list",
        "maxExclusive",
        "maxInclusive",
        "maxLength",
        "minExclusive",
        "minInclusive",
        "minLength",
        "pattern",
        "restriction",
        "simpleType",
        "totalDigits",
        "union",
        "whiteSpace",
       });

  /**
   * The list of components in <a href="http://www.w3.org/TR/xmlschema-2/">Part 2</a>.
   */
  protected List<String> part2Components =
    Arrays.asList
      (new String []
       {
        "enumeration", "dc-enumeration",
        "fractionDigits", "dc-fractionDigits",
        "length", "dc-length",
        "list", "dc-defn",
        "maxExclusive", "dc-maxExclusive",
        "maxInclusive", "dc-maxInclusive",
        "maxLength", "dc-maxLength",
        "minExclusive", "dc-minExclusive",
        "minInclusive", "dc-minInclusive",
        "minLength", "dc-minLength",
        "pattern", "dc-pattern",
        "restriction", "dc-defn",
        "simpleType", "dc-defn",
        "totalDigits", "dc-totalDigits",
        "union", "dc-defn",
        "whiteSpace", "dc-whiteSpace",
       });

  /**
   * The URL for errata.
   */
  protected String errata = "http://www.w3.org/2001/05/xmlschema-rec-comments";

  /**
   * A markup style indicating the feature is required to be supported.
   */
  public static final String REQUIRES = "requires";

  /**
   * A markup style indicating the feature not required to be supported.
   */
  public static final String ALLOWS = "allows";

  /**
   * A markup style indicating the feature will eventually be required to be supported.
   */
  public static final String FUTURE = "future";

  /**
   * A markup style indicating the feature will never be required to be supported.
   */
  public static final String NEVER = "never";

  /**
   * Creates an instance.
   */
  public XSDGenerateHTML() 
  {
    super();
  }

  /**
   * Read the markup from the .xml input.
   * @param fileName the name of an XML file.
   */
  public void readMarkup(String fileName)
  {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    documentBuilderFactory.setNamespaceAware(true);
    documentBuilderFactory.setValidating(false);
    try
    {
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
      Document document = documentBuilder.parse(fileName);
      for (Node child = document.getDocumentElement().getFirstChild(); child != null; child = child.getNextSibling())
      {
        if ("elementAnnotation".equals(child.getLocalName()))
        {
          handleMarkup(elementDeclarationMarkupMap, (Element)child);
        }
        else if ("attributeAnnotation".equals(child.getLocalName()))
        {
          handleMarkup(attributeDeclarationMarkupMap, (Element)child);
        }
        else if ("content".equals(child.getLocalName()))
        {
          handleMarkup(contentDocumentationMap, (Element)child);
        }
        else if ("typeMap".equals(child.getLocalName()))
        {
          Element markupElement = (Element)child;
          String schemaType = markupElement.getAttribute("schemaType");
          String javaClass = markupElement.getAttribute("javaClass");
          schemaTypeToJavaClassMap.put(schemaType, javaClass);
        }
      }
    }
    catch (Exception exception)
    {
      exception.printStackTrace(System.err);
    }
  }

  /**
   * Handle a markup element by caching information in a map.
   * @param markupMap the map to contain the markup.
   * @param markupElement the element specifying the markup.
   */
  public void handleMarkup(Map<String, String> markupMap, Element markupElement)
  {
    String keyList = markupElement.getAttribute("key");
    for (StringTokenizer stringTokenizer = new StringTokenizer(keyList); stringTokenizer.hasMoreTokens(); )
    {
      String key = stringTokenizer.nextToken();
      String markup = markupElement.getAttribute("markup");
      if (markup.length() > 0)
      {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try 
        {
          TransformerFactory transformerFactory = TransformerFactory.newInstance();
          Transformer transformer = transformerFactory.newTransformer();
          
          transformer.setOutputProperty(OutputKeys.INDENT, "yes");
          transformer.setOutputProperty(OutputKeys.METHOD, "xml");
          transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");

          for (Node grandChild = markupElement.getFirstChild(); grandChild != null; grandChild = grandChild.getNextSibling())
          {
            if (grandChild.getNodeType() == Node.ELEMENT_NODE)
            {
              transformer.transform(new DOMSource(grandChild), new StreamResult(out));
            }
          }
          String serialization = out.toString();
          serialization = serialization.substring(serialization.indexOf("<div>"));
          markupMap.put(key, markup + "@" + serialization);
        }
        catch (Exception exception)
        {
          exception.printStackTrace(System.err);
        }
      }
    }
  }

  /**
   * Returns the content documentation associated with the key.
   * @param key the key to look up.
   * @return the associated content documentation.
   * @see #handleMarkup
   */
  public String getContentDocumentation(String key)
  {
    String result = contentDocumentationMap.get(key);
    if (result != null)
    {
      result = result.substring(result.indexOf("@") + 1);
      if (result.length() == 0)
      {
        result = null;
      }
    }
    return result;
  }

  /**
   * Returns the element markup associated with the key.
   * @param key the key to look up.
   * @return the associated element markup.
   * @see #handleMarkup
   */
  public String getElementDeclarationMarkup(String key)
  {
    String result = elementDeclarationMarkupMap.get(key);
    if (result != null)
    {
      result = result.substring(0, result.indexOf("@"));
      if (result.length() == 0)
      {
        result = null;
      }
    }
    return result;
  }

  /**
   * Returns the element documentation associated with the key.
   * @param key the key to look up.
   * @return the associated element documentation.
   * @see #handleMarkup
   */
  public String getElementDeclarationDocumentation(String key)
  {
    String result = elementDeclarationMarkupMap.get(key);
    if (result != null)
    {
      result = result.substring(result.indexOf("@") + 1);
      if (result.length() == 0)
      {
        result = null;
      }
    }
    return result;
  }

  /**
   * Returns the attribute markup associated with the key.
   * @param key the key to look up.
   * @return the associated attribute markup.
   * @see #handleMarkup
   */
  public String getAttributeDeclarationMarkup(String key)
  {
    String result = attributeDeclarationMarkupMap.get(key);
    if (result != null)
    {
      result = result.substring(0, result.indexOf("@"));
      if (result.length() == 0)
      {
        result = null;
      }
    }
    return result;
  }

  /**
   * Returns the attribute documentation associated with the key.
   * @param key the key to look up.
   * @return the associated attribute documentation.
   * @see #handleMarkup
   */
  public String getAttributeDeclarationDocumentation(String key)
  {
    String result = attributeDeclarationMarkupMap.get(key);
    if (result != null)
    {
      result = result.substring(result.indexOf("@") + 1);
      if (result.length() == 0)
      {
        result = null;
      }
    }
    return result;
  }

  /**
   * Returns an href in Part 1 or Part 2 of the XML specification for the given element.
   * @param xsdElementDeclaration an element declaration in the schema for schema.
   * @return an href.
   */
  public String getStandardLink(XSDElementDeclaration xsdElementDeclaration)
  {
    String result = xsdElementDeclaration.getName();
    XSDElementDeclaration parentElementDeclaration = specialAnchorMap.get(xsdElementDeclaration);
    if (parentElementDeclaration != null)
    {
      result = "<a target='Part1' href='" + XSDConstants.PART1 + "#element-" + parentElementDeclaration.getName() + "::" + result;
    }
    else if (part2Anchors.contains(result))
    {
      result = "<a target='Part2' href='" + XSDConstants.PART2 + "#element-" + result;
    }
    else 
    {
      result = "<a target='Part1' href='" + XSDConstants.PART1 + "#element-" + result;
    }

    return result + "'>";
  }

  /**
   * Returns an href in Part 1 or Part 2 of the component specification for the given element.
   * @param xsdElementDeclaration a simple type defintion in the schema for schema.
   * @return an href.
   */
  public String getComponentLinks(XSDElementDeclaration xsdElementDeclaration)
  {
    String name = xsdElementDeclaration.getName();
    XSDElementDeclaration parentElementDeclaration = specialAnchorMap.get(xsdElementDeclaration);
    if (parentElementDeclaration != null)
    {
      name = parentElementDeclaration.getName() + "::" + name;
    }

    int part = 0;
    String anchors = null;
    int index = part2Components.indexOf(name);
    if (index != -1)
    {
      part = 2;
      anchors = part2Components.get(index + 1);
    }
    else
    {
      index = part1Components.indexOf(name);
      if (index != -1)
      {
        part = 1;
        anchors = part1Components.get(index + 1);
      }
    }

    if (part != 0)
    {
      StringBuffer result = new StringBuffer();
      int count = 0;
      for (StringTokenizer stringTokenizer = new StringTokenizer(anchors); stringTokenizer.hasMoreTokens(); )
      {
        String anchor = stringTokenizer.nextToken();
        result.append("&nbsp;&nbsp;<a target='Part");
        result.append(part);
        result.append("' href='");
        result.append(part == 1 ? XSDConstants.PART1 : XSDConstants.PART2);
        result.append("#");
        result.append(anchor);
        result.append("'><font size=-2>");
        result.append(++count);
        result.append("</font></a>");
      }
      return result.length() > 0 ? result.toString() : null;
    }
    else
    {
      return null;
    }
  }

  /**
   * Returns an href in Part 2 of the component specification for the given simple type definition.
   * @param xsdSimpleTypeDefinition an element declaration in the schema for schema.
   * @return an href.
   */
  public String getSimpleTypeDefinitionLink(XSDSimpleTypeDefinition xsdSimpleTypeDefinition)
  {
    String reference = xsdSimpleTypeDefinition.getName();
    StringBuffer result = new StringBuffer();
    if ("anyType".equals(reference))
    {
      result.append("<a target='Part1' href='");
      result.append(XSDConstants.PART1);
    }
    else
    {
      result.append("<a target='Part2' href='");
      result.append(XSDConstants.PART2);
    }
    result.append("#");
    if ("anyType".equals(reference))
    {
      reference = "ur-type-itself";
    }
    else if ("anySimpleType".equals(reference))
    {
      reference = "anySimpleType-component";
    }
    else if ("anyListType".equals(reference))
    {
      reference = "element-list";
    }
    else if ("anyUnionType".equals(reference))
    {
      reference = "element-union";
    }
    result.append(reference);
    result.append("'>");
    result.append(xsdSimpleTypeDefinition.getName());
    result.append("</a>");
    return result.toString();
  }

  /**
   * Returns an anchor that can be used locally for the given element declaration.
   * @param xsdElementDeclaration an element declaration in the schema for schema.
   * @return an anchor.
   */
  public String getLocalAnchor(XSDElementDeclaration xsdElementDeclaration)
  {
    String result = xsdElementDeclaration.getName();
    XSDElementDeclaration parentElementDeclaration = specialAnchorMap.get(xsdElementDeclaration);
    if (parentElementDeclaration != null)
    {
      result = "element-" + parentElementDeclaration.getName() + "::" + result;
    }
    else if (part2Anchors.contains(result))
    {
      result = "element-2-" + result;
    }
    else 
    {
      result = "element-" + result;
    }

    return result;
  }

  public Object start(IApplicationContext context) throws Exception
  {
    String [] arguments = (String[])context.getArguments().get(IApplicationContext.APPLICATION_ARGS);
    return run(arguments == null ? new String [0] : arguments);
  }

  public void stop()
  {
    // Subclasses may override
  }

  /**
   * Generate HTML annotated documentation that summarizes the built-in simple type hierarchy.
   * @param object an array of Strings.
   * @return <code>0</code> indicating success, or <code>1</code> indicating failure.
   */
  public Object run(Object object) 
  {
    try
    {
      String [] arguments = (String [])object;

      // Filter out inappropriate arguments added by the PDE.
      //
      if (arguments.length > 0 && "-pdelaunch".equals(arguments[0]))
      {
        String [] oldArguments = arguments;
        arguments = new String [arguments.length - 1];
        System.arraycopy(oldArguments, 1, arguments, 0, arguments.length);
      }

      readMarkup(arguments[0]);

      printHeader();

      // Iterate over the schema arguments.
      //
      for (int i = 1; i < arguments.length; ++i)
      {
        System.out.println("<!-- << " + arguments[i] + " >> -->");
        loadAndPrint(arguments[i]);
      }
      printFooter();

      return 0;
    }
    catch (Exception exception)
    {
      exception.printStackTrace();
      return 1;
    }
  }

  /**
   * Print the start of the document.
   */
  public void printHeader()
  {
    System.out.println("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.0 Transitional//EN\">");
    System.out.println("<html>");

    System.out.println("<style type='text/css'>");
    System.out.println("  code { font-family: monospace; font-size: 100%}");
    System.out.println("  div.reprdef { border: 4px double gray; margin: 0em 1em; padding: 0em }");
    System.out.println("  span.reprdef { color: #A52A2A }");

    System.out.println("  div.reprHeader { margin: 4px; font-weight: bold }");
    System.out.println("  div.reprBody { border-top-width: 4px; border-top-style: double; border-top-color: #d3d3d3; padding: 4px ; margin: 0em}");

    System.out.println("  div.never, span.never { color : #7F7F7F }");
    // System.out.println("  div.future, span.future { color : #AF7F7F }");
    System.out.println("  div.allows, span.allows { color : #7FAF7F }");
    System.out.println("  div.future, span.future { color : #7F7FAF }");

    System.out.println("</style>");
  }

  /**
   * Print the end of the document.
   */
  public void printFooter()
  {
    System.out.println("</html>");
  }

  /**
   * Load the XML Schema file and print the documentation based on it.
   * @param xsdFile the name of an XML Schema file.
   */
  public void loadAndPrint(String xsdFile) throws Exception
  {
    XSDFactory xsdFactory = XSDFactory.eINSTANCE;

    // Create a resource set and load the main schema file into it.
    //
    ResourceSet resourceSet = new ResourceSetImpl();
    XSDResourceImpl xsdResource = (XSDResourceImpl)resourceSet.getResource(URI.createFileURI(xsdFile), true);
    XSDSchema xsdSchema = xsdResource.getSchema();

    String elementContentHeaderDocumentation = getContentDocumentation("element-header");
    if (elementContentHeaderDocumentation != null)
    {
      System.out.println(elementContentHeaderDocumentation);
    }

    List<XSDElementDeclaration> all = new ArrayList<XSDElementDeclaration>(xsdSchema.getElementDeclarations());

    XSDElementDeclaration simpleContent = xsdSchema.resolveElementDeclaration("simpleContent");
    XSDElementDeclaration complexContent = xsdSchema.resolveElementDeclaration("complexContent");
    for (int i = 0; i <= 1; ++i) 
    {
      for (int j = 0; j <= 1; ++j)
      {
        XSDElementDeclaration parentElement = (i == 0 ? complexContent : simpleContent);
        XSDComplexTypeDefinition xsdComplexTypeDefinition = (XSDComplexTypeDefinition)parentElement.getTypeDefinition();
        XSDElementDeclaration specialElementDeclaration = 
          (XSDElementDeclaration)
            ((XSDModelGroup)
              ((XSDModelGroup)
                ((XSDParticle)(xsdComplexTypeDefinition.getContentType())).
                  getTerm()).
                getParticles().get(1).
                getTerm()).
              getParticles().get(j).
              getTerm();
        all.add(specialElementDeclaration);
        specialAnchorMap.put(specialElementDeclaration, parentElement);
      }
    }

    @SuppressWarnings("unchecked")
    List<XSDElementDeclaration> aux = (List)XSDNamedComponentImpl.sortNamedComponents((List)all);
    all = aux;

    for (XSDElementDeclaration xsdElementDeclaration : all)
    {
      XSDElementDeclaration parentElementDeclaration = specialAnchorMap.get(xsdElementDeclaration);
      String elementDeclarationName = xsdElementDeclaration.getName();
      String key = (parentElementDeclaration == null ? "" : parentElementDeclaration.getName() + "::") + elementDeclarationName;
      String elementDeclarationMarkup = getElementDeclarationMarkup(key);
      System.out.print("<h2>");
      System.out.print(elementDeclarationName.substring(0, 1).toUpperCase());
      System.out.print(elementDeclarationName.substring(1));
      System.out.println("</h2>");
      System.out.println("<div class='reprdef'>");
      System.out.println("<table cols=1 width='100%'><tr><td>");
      System.out.print("<div class='reprHeader'><span class='reprdef'>XML&nbsp;Representation&nbsp;Summary:&nbsp;</span><code>");
      System.out.print("<a name='" + getLocalAnchor(xsdElementDeclaration) + "'>");
      System.out.print(getStandardLink(xsdElementDeclaration));
      System.out.print(elementDeclarationName);
      System.out.print("</a></a></code>");
      System.out.print("&nbsp;Element&nbsp;Information&nbsp;Item&nbsp;");
      if (parentElementDeclaration != null)
      {
        System.out.print("<small>(");
        System.out.print("<a href='#" + getLocalAnchor(parentElementDeclaration) + "'>");
        System.out.print(parentElementDeclaration.getName());
        System.out.print("</a>)</small>");
      }
      else if ("restriction".equals(elementDeclarationName))
      {
        System.out.print("<small>(simpleType)</small>");
      }
      System.out.println("</div>");

      System.out.println("<div class='reprBody'>");

      if (elementDeclarationMarkup != null)
      {
        System.out.print("<div class='" + elementDeclarationMarkup + "'>");
      }
      System.out.print("<tt>&lt;");
      System.out.print(elementDeclarationName);
      System.out.print("</tt>");

      String componentLinks = getComponentLinks(xsdElementDeclaration);
      if (componentLinks != null)
      {
        System.out.print(componentLinks);
      }

      System.out.println("<br>");

      StringBuffer attributeDocumentationBuffer = new StringBuffer();
      Map<String, Integer> repeatedDocumentationMap = new HashMap<String, Integer>();

      XSDTypeDefinition xsdTypeDefinition = xsdElementDeclaration.getTypeDefinition();
      XSDComplexTypeDefinition generalType = xsdSchema.resolveComplexTypeDefinitionURI(xsdElementDeclaration.getURI());
      if (generalType.getContainer() != null)
      {
        xsdTypeDefinition = generalType;
      }

      if (xsdTypeDefinition instanceof XSDSimpleTypeDefinition)
      {
        System.out.println("<tt>></tt><br>");
      }
      else if (xsdTypeDefinition instanceof XSDComplexTypeDefinition)
      {
        XSDComplexTypeDefinition xsdComplexTypeDefinition = (XSDComplexTypeDefinition)xsdTypeDefinition;
        for (Iterator<XSDAttributeUse> attributeUses = xsdComplexTypeDefinition.getAttributeUses().iterator(); attributeUses.hasNext(); )
        {
          XSDAttributeUse xsdAttributeUse = attributeUses.next();
          XSDAttributeDeclaration xsdAttributeDeclaration = xsdAttributeUse.getAttributeDeclaration();
          String attributeDeclarationName = xsdAttributeDeclaration.getName();
          System.out.print("<tt>&nbsp;&nbsp;");
          if (xsdAttributeDeclaration.getTargetNamespace() != null)
          {
            System.out.print("xml:");
          }
          String attributeDeclarationMarkup = null; 
          String attributeDeclarationDocumentation = null;
          if (!"ignored".equals(elementDeclarationMarkup))
          {
            attributeDeclarationMarkup = getAttributeDeclarationMarkup(attributeDeclarationName);
            if (attributeDeclarationMarkup == null)
            {
              attributeDeclarationMarkup = 
                getAttributeDeclarationMarkup(elementDeclarationName + "." + attributeDeclarationName);
            }

            attributeDeclarationDocumentation = getAttributeDeclarationDocumentation(attributeDeclarationName);
            if (attributeDeclarationDocumentation == null)
            {
              attributeDeclarationDocumentation = 
                getAttributeDeclarationDocumentation(elementDeclarationName + "." + attributeDeclarationName);
            }
          }

          if (attributeDeclarationDocumentation != null)
          {
            Integer oldInsertIndex = repeatedDocumentationMap.get(attributeDeclarationDocumentation);
            if (oldInsertIndex != null)
            {
              String insertion = "<br>" + attributeDeclarationName;
              attributeDocumentationBuffer.insert(oldInsertIndex, insertion);
              repeatedDocumentationMap.put(attributeDeclarationDocumentation, oldInsertIndex + insertion.length());
            }
            else
            {
              if (attributeDocumentationBuffer.length() == 0)
              {
                attributeDocumentationBuffer.append("<table cols=2 width='100%'>\n"); 
                attributeDocumentationBuffer.append("<tr>\n<th width=25% valign=top align=left><b>Attribute</b></th>\n");
                attributeDocumentationBuffer.append("<th width=75% valign=top align=left><b>Description</b></th>\n</tr>\n");
              }
              attributeDocumentationBuffer.append("<tr><td><b>");
              if (attributeDeclarationMarkup != null)
              {
                attributeDocumentationBuffer.append("<span class='" + attributeDeclarationMarkup + "'>");
              }
              attributeDocumentationBuffer.append(attributeDeclarationName);
              int insertIndex = attributeDocumentationBuffer.length();
              if (attributeDeclarationMarkup != null)
              {
                attributeDocumentationBuffer.append("</span>");
              }
              attributeDocumentationBuffer.append("</b></td>\n<td valign=top>\n");
              attributeDocumentationBuffer.append(attributeDeclarationDocumentation);
              attributeDocumentationBuffer.append("</td></tr>"); 
              repeatedDocumentationMap.put(attributeDeclarationDocumentation, insertIndex);
            }
          }

          if (attributeDeclarationMarkup != null)
          {
            System.out.print("<span class='" + attributeDeclarationMarkup + "'>");
          }
          if (xsdAttributeUse.isRequired())
          {
            System.out.print("<b>");
            System.out.print(attributeDeclarationName);
            System.out.print("</b>");
          }
          else
          {
            System.out.print(attributeDeclarationName);
          }
          if (attributeDeclarationMarkup != null)
          {
            System.out.print("</span>");
          }
          System.out.print("&nbsp;=&nbsp;</tt>");
          XSDSimpleTypeDefinition xsdSimpleTypeDefinition = xsdAttributeDeclaration.getTypeDefinition();
          printSimpleTypeDefinition(xsdSimpleTypeDefinition);
          if (xsdAttributeUse.getLexicalValue() != null)
          {
            System.out.print("&nbsp;:&nbsp;");
            if ("".equals(xsdAttributeUse.getLexicalValue()))
            {
              System.out.print("\"\"");
            }
            else
            {
              System.out.print(xsdAttributeUse.getLexicalValue());
            }
          }
          if (attributeUses.hasNext())
          {
            System.out.println("<br>");
          }
        }
        if (xsdComplexTypeDefinition.getAttributeWildcard() != null)
        {
          System.out.println("<br>");
          System.out.println("<span class='" + ALLOWS + "'><tt><em>&nbsp;&nbsp;{&nbsp;any&nbsp;attributes&nbsp;with&nbsp;non-schema&nbsp;namespace&nbsp;.&nbsp;.&nbsp;.&nbsp;}</em></tt></span>");
        }

        System.out.println("<tt>></tt><br>");

        if (xsdComplexTypeDefinition.getContentType() instanceof XSDParticle)
        {
          System.out.print("<tt><em>&nbsp;&nbsp;Content:</em>&nbsp;"); 

          printParticle((XSDParticle)xsdComplexTypeDefinition.getContentType(), elementDeclarationMarkup);

          System.out.print("</tt>");
        }
        else if (xsdComplexTypeDefinition.getContentType() instanceof XSDSimpleTypeDefinition)
        {
          System.out.print("<b>***** simple</b>");
        }
        else
        {
          System.out.print("<b>{ **** empty }</b>");
        }
        System.out.println("<br>");
      }
      System.out.print("<tt>&lt;/");
      System.out.print(elementDeclarationName);
      System.out.println("></tt>");

      if (elementDeclarationMarkup != null)
      {
        System.out.print("</div>");
      }
      System.out.println("</div>");

      String elementDeclarationDocumentation = getElementDeclarationDocumentation(key);
      if (elementDeclarationDocumentation != null)
      {
        System.out.println("<div class='reprBody'>");
        System.out.println(elementDeclarationDocumentation);
        System.out.println("</div>");
      }
      if (attributeDocumentationBuffer.length() > 0)
      {
        System.out.println("<div class='reprBody'>");
        System.out.print(attributeDocumentationBuffer);
        System.out.println("</table>");
        System.out.println("</div>");
      }
      System.out.println("</td></tr></table>");
      System.out.println("</div>");
    }

    // System.out.println("<h1>Built-in Datatypes</h1>");
    String typeContentHeaderDocumentation = getContentDocumentation("type-header");
    if (typeContentHeaderDocumentation != null)
    {
      System.out.println(typeContentHeaderDocumentation);
    }
    System.out.println("<table border=true cols=3 width='100%'>");
    System.out.println("<tr>");
    System.out.println("<th width=33% valign=top align=left><b>Type</b></th>");
    System.out.println("<th width=33% valign=top align=left><b>Properties&nbsp;&amp;&nbsp;Facets</b></th>");
    System.out.println("<th width=34% valign=top align=left><b>Effective&nbsp;Value</b></th>");
    System.out.println("</tr>");

    XSDSimpleTypeDefinition anyTypeDefinition = xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("anyType");
    XSDSimpleTypeDefinition anySimpleTypeDefinition = xsdSchema.getSchemaForSchema().resolveSimpleTypeDefinition("anySimpleType");

    XSDSimpleTypeDefinition anyListTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
    anyListTypeDefinition.setVariety(XSDVariety.LIST_LITERAL);
    anyListTypeDefinition.setName("anyListType");
    anyListTypeDefinition.setItemTypeDefinition(anySimpleTypeDefinition);
    xsdSchema.getContents().add(anyListTypeDefinition);
    anyListTypeDefinition.getElement().setAttribute(XSDConstants.ID_ATTRIBUTE, "anyListType");

    XSDSimpleTypeDefinition anyUnionTypeDefinition = xsdFactory.createXSDSimpleTypeDefinition();
    anyUnionTypeDefinition.setVariety(XSDVariety.UNION_LITERAL);
    anyUnionTypeDefinition.setName("anyUnionType");
    anyUnionTypeDefinition.getMemberTypeDefinitions().add(anySimpleTypeDefinition);
    xsdSchema.getContents().add(anyUnionTypeDefinition);
    anyUnionTypeDefinition.getElement().setAttribute(XSDConstants.ID_ATTRIBUTE, "anyUnionType");

    List<XSDTypeDefinition> allTypeDefinitions = new ArrayList<XSDTypeDefinition>(xsdSchema.getTypeDefinitions());
    allTypeDefinitions.add(0, anySimpleTypeDefinition);
    allTypeDefinitions.add(0, anyTypeDefinition);
    for (XSDTypeDefinition xsdTypeDefinition : allTypeDefinitions)
    {
      if (xsdTypeDefinition instanceof XSDSimpleTypeDefinition && 
            xsdTypeDefinition.getElement() != null && 
            xsdTypeDefinition.getName().equals(xsdTypeDefinition.getElement().getAttribute(XSDConstants.ID_ATTRIBUTE)))
      {
        XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)xsdTypeDefinition;
        System.out.println("<tr>");
        System.out.println("<a name='" + xsdSimpleTypeDefinition.getName() + "-simple-type'>");
        System.out.println("<td>");
        boolean isPrimitive = 
          XSDVariety.ATOMIC_LITERAL == xsdSimpleTypeDefinition.getVariety() && 
            xsdSimpleTypeDefinition.getBaseTypeDefinition() == anySimpleTypeDefinition;
        if (isPrimitive)
        {
          System.out.print("<b>");
        }
        System.out.print(getSimpleTypeDefinitionLink(xsdSimpleTypeDefinition));
        if (isPrimitive)
        {
          System.out.print("</b>");
        }
        for (XSDSimpleTypeDefinition baseTypeDefinition = xsdSimpleTypeDefinition;; 
             baseTypeDefinition = baseTypeDefinition.getBaseTypeDefinition())
        {
          String javaClass = schemaTypeToJavaClassMap.get(baseTypeDefinition.getName());
          if (javaClass != null)
          {
            System.out.println("<br>&nbsp;<br>");
            if (baseTypeDefinition == xsdSimpleTypeDefinition)
            {
              System.out.print("<b>"); // )
            }
            int dotIndex = javaClass.lastIndexOf(".");
            System.out.print("<font size=-2>");
            System.out.print(javaClass.substring(0, dotIndex + 1));
            System.out.print("</font><br>&nbsp;&nbsp;");
            System.out.print(javaClass.substring(dotIndex + 1));
            if (baseTypeDefinition == xsdSimpleTypeDefinition)
            {
              // (
              System.out.print("<b>");
            }
            System.out.println();
            break;
          }
        }
        System.out.println("<br>");
        System.out.println("</td>");
        System.out.println("</a>");

        StringBuffer validFacets = new StringBuffer();
        StringBuffer effectiveFacetValues = new StringBuffer();

        validFacets.append("variety<br>\n");
        effectiveFacetValues.append
          (xsdSimpleTypeDefinition.isSetVariety() ? "<b>" + xsdSimpleTypeDefinition.getVariety() + "</b>" : "<em>absent</em>");
        effectiveFacetValues.append("<br>\n");

        validFacets.append("base type definition<br>\n");
        XSDSimpleTypeDefinition baseTypeDefinition = xsdSimpleTypeDefinition.getBaseTypeDefinition();
        while (baseTypeDefinition.getName() == null)
        {
          baseTypeDefinition = baseTypeDefinition.getBaseTypeDefinition();
        }
        effectiveFacetValues.append("<a href='#");
        effectiveFacetValues.append(baseTypeDefinition.getName());
        effectiveFacetValues.append("-simple-type'>");
        effectiveFacetValues.append(baseTypeDefinition.getName());
        effectiveFacetValues.append("</a><br>\n");

        validFacets.append("ordered<br>\n");
        effectiveFacetValues.append
          ("anyUnionType".equals(xsdSimpleTypeDefinition.getName()) ?
             "*" :
             xsdSimpleTypeDefinition.getOrderedFacet().getValue().getName());
        effectiveFacetValues.append("<br>\n");

        validFacets.append("bounded<br>\n");
        effectiveFacetValues.append
          ("anyUnionType".equals(xsdSimpleTypeDefinition.getName()) ?
             "*" :
             xsdSimpleTypeDefinition.getBoundedFacet().isValue() ? "true" : "false");
        effectiveFacetValues.append("<br>\n");

        validFacets.append("cardinality<br>\n");
        effectiveFacetValues.append
          ("anyUnionType".equals(xsdSimpleTypeDefinition.getName()) ?
             "*" :
             XSDCardinality.COUNTABLY_INFINITE_LITERAL == xsdSimpleTypeDefinition.getCardinalityFacet().getValue() ? 
               "countably infinite" :
               xsdSimpleTypeDefinition.getCardinalityFacet().getValue().getName());
        effectiveFacetValues.append("<br>\n");

        validFacets.append("numeric<br>\n");
        effectiveFacetValues.append
          ("anyUnionType".equals(xsdSimpleTypeDefinition.getName()) ?
             "*" :
             xsdSimpleTypeDefinition.getNumericFacet().isValue() ?  "true" : "false");
        effectiveFacetValues.append("<br>\n");

        if (xsdSimpleTypeDefinition.getValidFacets().contains("length"))
        {
          validFacets.append("length<br>\n");
          if (xsdSimpleTypeDefinition.getEffectiveLengthFacet() != null)
          {
            XSDLengthFacet xsdLengthFacet = xsdSimpleTypeDefinition.getEffectiveLengthFacet();
            if (xsdLengthFacet.isFixed())
            {
              effectiveFacetValues.append("<b>");
            }
            effectiveFacetValues.append(xsdLengthFacet.getValue());
            if (xsdLengthFacet.isFixed())
            {
              effectiveFacetValues.append("</b>");
            }
          }
          effectiveFacetValues.append("<br>\n");
        }

        if (xsdSimpleTypeDefinition.getValidFacets().contains("minLength"))
        {
          validFacets.append("minLength<br>\n");
          if (xsdSimpleTypeDefinition.getEffectiveMinLengthFacet() != null)
          {
            XSDMinLengthFacet xsdMinLengthFacet = xsdSimpleTypeDefinition.getEffectiveMinLengthFacet();
            if (xsdMinLengthFacet.isFixed())
            {
              effectiveFacetValues.append("<b>");
            }
            effectiveFacetValues.append(xsdMinLengthFacet.getValue());
            if (xsdMinLengthFacet.isFixed())
            {
              effectiveFacetValues.append("</b>");
            }
          }
          effectiveFacetValues.append("<br>\n");
        }

        if (xsdSimpleTypeDefinition.getValidFacets().contains("maxLength"))
        {
          validFacets.append("maxLength<br>\n");
          if (xsdSimpleTypeDefinition.getEffectiveMaxLengthFacet() != null)
          {
            XSDMaxLengthFacet xsdMaxLengthFacet = xsdSimpleTypeDefinition.getEffectiveMaxLengthFacet();
            if (xsdMaxLengthFacet.isFixed())
            {
              effectiveFacetValues.append("<b>");
            }
            effectiveFacetValues.append(xsdMaxLengthFacet.getValue());
            if (xsdMaxLengthFacet.isFixed())
            {
              effectiveFacetValues.append("</b>");
            }
          }
          effectiveFacetValues.append("<br>\n");
        }

        if (xsdSimpleTypeDefinition.getValidFacets().contains("pattern"))
        {
          validFacets.append("pattern<br>\n");
          if (xsdSimpleTypeDefinition.getEffectivePatternFacet() != null)
          {
            // XSDPatternFacet xsdPatternFacet = xsdSimpleTypeDefinition.getEffectivePatternFacet();
            effectiveFacetValues.append("*");
          }
          effectiveFacetValues.append("<br>\n");
        }

        if (xsdSimpleTypeDefinition.getValidFacets().contains("enumeration"))
        {
          validFacets.append("enumeration<br>\n");
          if (xsdSimpleTypeDefinition.getEffectiveEnumerationFacet() != null)
          {
            XSDEnumerationFacet xsdEnumerationFacet = xsdSimpleTypeDefinition.getEffectiveEnumerationFacet();
            for (Iterator<?> enumerators = xsdEnumerationFacet.getValue().iterator(); enumerators.hasNext(); )
            {
              String enumerator = (String)enumerators.next();
              effectiveFacetValues.append(enumerator);
              effectiveFacetValues.append("&nbsp;");
            }
          }
          effectiveFacetValues.append("<br>\n");
        }

        if (xsdSimpleTypeDefinition.getValidFacets().contains("whiteSpace"))
        {
          validFacets.append("whiteSpace<br>\n");
          if (xsdSimpleTypeDefinition.getEffectiveWhiteSpaceFacet() != null)
          {
            XSDWhiteSpaceFacet xsdWhiteSpaceFacet = xsdSimpleTypeDefinition.getEffectiveWhiteSpaceFacet();
            if (xsdWhiteSpaceFacet.isFixed())
            {
              effectiveFacetValues.append("<b>");
            }
            effectiveFacetValues.append(xsdWhiteSpaceFacet.getValue());
            if (xsdWhiteSpaceFacet.isFixed())
            {
              effectiveFacetValues.append("</b>");
            }
          }
          effectiveFacetValues.append("<br>\n");
        }

        if (xsdSimpleTypeDefinition.getValidFacets().contains("maxInclusive"))
        {
          validFacets.append("maxInclusive<br>\n");
          if (xsdSimpleTypeDefinition.getEffectiveMaxFacet() instanceof XSDMaxInclusiveFacet)
          {
            XSDMaxInclusiveFacet xsdMaxInclusiveFacet = (XSDMaxInclusiveFacet)xsdSimpleTypeDefinition.getEffectiveMaxFacet();
            if (xsdMaxInclusiveFacet.isFixed())
            {
              effectiveFacetValues.append("<b>");
            }
            effectiveFacetValues.append(xsdMaxInclusiveFacet.getValue());
            if (xsdMaxInclusiveFacet.isFixed())
            {
              effectiveFacetValues.append("</b>");
            }
          }
          effectiveFacetValues.append("<br>\n");
        }

        if (xsdSimpleTypeDefinition.getValidFacets().contains("maxExclusive"))
        {
          validFacets.append("maxExclusive<br>\n");
          if (xsdSimpleTypeDefinition.getEffectiveMaxFacet() instanceof XSDMaxExclusiveFacet)
          {
            XSDMaxExclusiveFacet xsdMaxExclusiveFacet = (XSDMaxExclusiveFacet)xsdSimpleTypeDefinition.getEffectiveMaxFacet();
            if (xsdMaxExclusiveFacet.isFixed())
            {
              effectiveFacetValues.append("<b>");
            }
            effectiveFacetValues.append(xsdMaxExclusiveFacet.getValue());
            if (xsdMaxExclusiveFacet.isFixed())
            {
              effectiveFacetValues.append("</b>");
            }
          }
          effectiveFacetValues.append("<br>\n");
        }

        if (xsdSimpleTypeDefinition.getValidFacets().contains("minInclusive"))
        {
          validFacets.append("maxInclusive<br>\n");
          if (xsdSimpleTypeDefinition.getEffectiveMinFacet() instanceof XSDMinInclusiveFacet)
          {
            XSDMinInclusiveFacet xsdMinInclusiveFacet = (XSDMinInclusiveFacet)xsdSimpleTypeDefinition.getEffectiveMinFacet();
            if (xsdMinInclusiveFacet.isFixed())
            {
              effectiveFacetValues.append("<b>");
            }
            effectiveFacetValues.append(xsdMinInclusiveFacet.getValue());
            if (xsdMinInclusiveFacet.isFixed())
            {
              effectiveFacetValues.append("</b>");
            }
          }
          effectiveFacetValues.append("<br>\n");
        }

        if (xsdSimpleTypeDefinition.getValidFacets().contains("minExclusive"))
        {
          validFacets.append("maxExclusive<br>\n");
          if (xsdSimpleTypeDefinition.getEffectiveMinFacet() instanceof XSDMinExclusiveFacet)
          {
            XSDMinExclusiveFacet xsdMinExclusiveFacet = (XSDMinExclusiveFacet)xsdSimpleTypeDefinition.getEffectiveMinFacet();
            if (xsdMinExclusiveFacet.isFixed())
            {
              effectiveFacetValues.append("<b>");
            }
            effectiveFacetValues.append(xsdMinExclusiveFacet.getValue());
            if (xsdMinExclusiveFacet.isFixed())
            {
              effectiveFacetValues.append("</b>");
            }
          }
          effectiveFacetValues.append("<br>\n");
        }

        if (xsdSimpleTypeDefinition.getValidFacets().contains("totalDigits"))
        {
          validFacets.append("totalDigits<br>\n");
          if (xsdSimpleTypeDefinition.getEffectiveTotalDigitsFacet() != null)
          {
            XSDTotalDigitsFacet xsdTotalDigitsFacet = xsdSimpleTypeDefinition.getEffectiveTotalDigitsFacet();
            if (xsdTotalDigitsFacet.isFixed())
            {
              effectiveFacetValues.append("<b>");
            }
            effectiveFacetValues.append(xsdTotalDigitsFacet.getValue());
            if (xsdTotalDigitsFacet.isFixed())
            {
              effectiveFacetValues.append("</b>");
            }
          }
          effectiveFacetValues.append("<br>\n");
        }

        if (xsdSimpleTypeDefinition.getValidFacets().contains("fractionDigits"))
        {
          validFacets.append("fractionDigits<br>\n");
          if (xsdSimpleTypeDefinition.getEffectiveFractionDigitsFacet() != null)
          {
            XSDFractionDigitsFacet xsdFractionDigitsFacet = xsdSimpleTypeDefinition.getEffectiveFractionDigitsFacet();
            if (xsdFractionDigitsFacet.isFixed())
            {
              effectiveFacetValues.append("<b>");
            }
            effectiveFacetValues.append(xsdFractionDigitsFacet.getValue());
            if (xsdFractionDigitsFacet.isFixed())
            {
              effectiveFacetValues.append("</b>");
            }
          }
          effectiveFacetValues.append("<br>\n");
        }

        System.out.println("<td>");
        System.out.print(validFacets);
        System.out.println("</td>");
        System.out.print("<td>");
        System.out.println(effectiveFacetValues);
        System.out.println("</td>");
        System.out.println("</tr>");
      }
    }
    System.out.println("</table>");

    String appendixContentHeaderDocumentation = getContentDocumentation("appendix-header");
    if (appendixContentHeaderDocumentation != null)
    {
      System.out.println(appendixContentHeaderDocumentation);
    }
  }

  /**
   * Print a particle with markup for the document.
   * @param xsdParticle a particle.
   * @param rootElementDeclarationMarkup the markup.
   */
  public void printParticle(XSDParticle xsdParticle, String rootElementDeclarationMarkup)
  {
    int minOccurs = xsdParticle.getMinOccurs();
    int maxOccurs = xsdParticle.getMaxOccurs();
    XSDTerm xsdTerm = xsdParticle.getTerm();
    if (xsdTerm instanceof XSDElementDeclaration)
    {
      XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration)xsdTerm;
      String elementDeclarationName = xsdElementDeclaration.getName();
      String elementDeclarationMarkup = null;
      if (rootElementDeclarationMarkup == null)
      {
        elementDeclarationMarkup = getElementDeclarationMarkup(elementDeclarationName);
      }
      if (elementDeclarationMarkup != null)
      {
        System.out.print("<span class='");
        System.out.print(elementDeclarationMarkup);
        System.out.print("'>");
      }
      System.out.print("<a href='#" + getLocalAnchor(xsdElementDeclaration) + "'>");
      System.out.print(elementDeclarationName.charAt(0));
      System.out.print("</a>");
      System.out.print(elementDeclarationName.substring(1));
      if (elementDeclarationMarkup != null)
      {
        System.out.print("</span>");
      }
      if (minOccurs == 0)
      {
        if (maxOccurs == 1)
        {
          System.out.print("?");
        }
        else
        {
          System.out.print("*");
        }
      }
      else if (maxOccurs == -1)
      {
        System.out.print("+");
      }
    }
    else if (xsdTerm instanceof XSDModelGroup)
    {
      XSDModelGroup xsdModelGroup = (XSDModelGroup)xsdTerm;
      List<XSDParticle> particles = xsdModelGroup.getParticles();
      boolean isRedundant = 
        particles.size() == 1 && 
          minOccurs == 1 && 
          maxOccurs == 1 &&
          particles.get(0).getTerm() instanceof XSDModelGroup;
      if (!isRedundant)
      {
        System.out.print("(");  // )
      }

      String separator = 
        XSDCompositor.CHOICE_LITERAL == xsdModelGroup.getCompositor()  ?
          " | " : 
          XSDCompositor.SEQUENCE_LITERAL == xsdModelGroup.getCompositor()  ?
            ",  " :
            "  &  ";

      for (Iterator<XSDParticle> i = xsdModelGroup.getParticles().iterator(); i.hasNext(); )
      {
        XSDParticle childParticle = i.next();
        printParticle(childParticle, rootElementDeclarationMarkup);
        if (i.hasNext())
        {
          System.out.print(separator);
        }
      }

      if (!isRedundant)
      {
        // (
        System.out.print(")");
        if (minOccurs == 0)
        {
          if (maxOccurs == 1)
          {
            System.out.print("?");
          }
          else
          {
            System.out.print("*");
          }
        }
        else if (maxOccurs == -1)
        {
          System.out.print("+");
        }
      }
    }
    else if (xsdTerm instanceof XSDWildcard)
    {
      System.out.print("<em>{any}</em>");
    }
  }

  /**
   * Print a simple type definition for the document.
   * @param xsdSimpleTypeDefinition a simple type definition in the schema for schema.
   */
  public void printSimpleTypeDefinition(XSDSimpleTypeDefinition xsdSimpleTypeDefinition)
  {
    if (xsdSimpleTypeDefinition == null)
    {
      // Do nothing
    }
    else if (xsdSimpleTypeDefinition.getEffectiveEnumerationFacet() != null)
    {
      List<?> value = xsdSimpleTypeDefinition.getEffectiveEnumerationFacet().getValue();
      if (value.size() > 1)
      {
        System.out.print("(");
      }
      for (Iterator<?> enumerators = value.iterator(); enumerators.hasNext(); )
      {
        String enumerator = enumerators.next().toString();
        System.out.print("<em>");
        System.out.print(enumerator);
        System.out.print("</em>");
        if (enumerators.hasNext())
        {
          System.out.print("&nbsp;|&nbsp;");
        }
      }
      if (value.size() > 1)
      {
        System.out.print(")");
      }
    }
    else if (xsdSimpleTypeDefinition.getElement() != null && 
               xsdSimpleTypeDefinition.getElement().hasAttribute(XSDConstants.ID_ATTRIBUTE))
    {
      System.out.print("<a href='#" + xsdSimpleTypeDefinition.getName() + "-simple-type'>");
      System.out.print(xsdSimpleTypeDefinition.getName());
      System.out.print("</a>");
    }
    else if (XSDVariety.UNION_LITERAL == xsdSimpleTypeDefinition.getVariety())
    {
      System.out.print("(");
      for (Iterator<XSDSimpleTypeDefinition> members = xsdSimpleTypeDefinition.getMemberTypeDefinitions().iterator(); members.hasNext(); )
      {
        XSDSimpleTypeDefinition memberTypeDefinition = members.next();
        printSimpleTypeDefinition(memberTypeDefinition);
        if (members.hasNext())
        {
          System.out.print("&nbsp;|&nbsp;");
        }
      }
      System.out.print(")");
    }
    else if (XSDVariety.UNION_LITERAL == xsdSimpleTypeDefinition.getVariety())
    {
      System.out.print("List&nbsp;of&nbsp;");
      printSimpleTypeDefinition(xsdSimpleTypeDefinition.getItemTypeDefinition());
    }
    else if (xsdSimpleTypeDefinition.getName() != null)
    {
      if ("public".equals(xsdSimpleTypeDefinition.getName()))
      {
        System.out.print("<a target='Part2' href='" + XSDConstants.PART2 + "#anyURI'>anyURI</a>&nbsp;&nbsp;");
        System.out.print("<a target='Errata' href='" + errata + "#pfipublic'><em>public</em></a>");
      }
      else
      {
        System.out.print("<b><em>");
        System.out.print(xsdSimpleTypeDefinition.getName());
        System.out.print("</em></b>");
      }
    }
    else if (xsdSimpleTypeDefinition.getEffectivePatternFacet() != null)
    {
      // System.out.print(xsdSimpleTypeDefinition.getEffectivePatternFacet().getLexicalValue());

      System.out.print("<em>");
      System.out.print("<a target='Part1' href='" + XSDConstants.PART1 + "#coss-identity-constraint'>");
      System.out.print("a restricted xpath expression");
      System.out.print("</a>");
      System.out.print("</em>");
    }
    else
    {
      System.out.print("***");
    }
  }
}