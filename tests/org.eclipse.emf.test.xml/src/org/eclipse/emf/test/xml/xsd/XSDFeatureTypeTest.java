/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.xml.xsd;

import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;
import org.eclipse.xsd.util.XSDResourceImpl;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

public class XSDFeatureTypeTest extends TestCase
{
  private static final String ANY_TYPE = "anyType";
  private static final String ANY_SIMPLE_TYPE = "anySimpleType";
  private static final String STRING = "string";
  
  public XSDFeatureTypeTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("XSDFeatureTypeTest");
    ts.addTest(new XSDFeatureTypeTest("testAttributeWithAnyType"));
    ts.addTest(new XSDFeatureTypeTest("testElementWithAnyType"));
    return ts;
  }

  XSDSchema schema;
  XSDSimpleTypeDefinition stringType;
  XSDSimpleTypeDefinition anySimpleType;
  XSDTypeDefinition anyType;

  @Override
  protected void setUp() throws Exception
  {
    super.setUp();

    ResourceSet resourceSet = new ResourceSetImpl();
    XSDResourceImpl resource = new XSDResourceImpl();
    resourceSet.getResources().add(resource);
    resource.setURI(URI.createFileURI(TestUtil.getPluginDirectory("org.eclipse.emf.test.xml") + "/data/xsd/TestSchema.xsd"));

    try
    {
      resource.load(null);
      schema = resource.getSchema();
      stringType = (XSDSimpleTypeDefinition) schema.getSchemaForSchema().resolveTypeDefinition(STRING);
      anySimpleType = (XSDSimpleTypeDefinition) schema.getSchemaForSchema().resolveTypeDefinition(ANY_SIMPLE_TYPE);
      anyType =  schema.getSchemaForSchema().resolveTypeDefinition(ANY_TYPE);
    }
    catch (IOException e)
    {
      e.printStackTrace();
      fail();
    }
  }

  /**
   * See https://bugs.eclipse.org/bugs/show_bug.cgi?id=174092 <br />
   * Testing attribute declarations.
   */
  public void testAttributeWithAnyType()
  {
    XSDAttributeDeclaration attributeDeclaration = schema.resolveAttributeDeclaration("testAttribute");

    // In the beginning the attribute's type should be anySimpleType

    assertEquals(anySimpleType, attributeDeclaration.getTypeDefinition());

    // Setting an attribute's type from anySimpleType to string

    attributeDeclaration.setTypeDefinition(stringType);

    Element attributeDeclarationElement = attributeDeclaration.getElement();
    Attr typeAttribute = attributeDeclarationElement.getAttributeNode(XSDConstants.TYPE_ATTRIBUTE);
    assertNotNull(typeAttribute);
    assertEquals(STRING, typeAttribute.getValue());

    // Setting the attribute's type from string to null which should result in
    // the type attribute being removed

    attributeDeclaration.setTypeDefinition(null);
    typeAttribute = attributeDeclarationElement.getAttributeNode(XSDConstants.TYPE_ATTRIBUTE);
    assertNull(typeAttribute);

    // Setting the type attribute through the DOM to string

    attributeDeclarationElement.setAttribute(XSDConstants.TYPE_ATTRIBUTE, STRING);
    assertEquals(stringType, attributeDeclaration.getTypeDefinition());

    // Setting an element's type from string to anySimpleType

    attributeDeclaration.setTypeDefinition(anySimpleType);
    typeAttribute = attributeDeclarationElement.getAttributeNode(XSDConstants.TYPE_ATTRIBUTE);
    assertNotNull(typeAttribute);
    assertEquals(ANY_SIMPLE_TYPE, typeAttribute.getValue());

    // Removing the type attribute through the DOM

    attributeDeclarationElement.removeAttribute(XSDConstants.TYPE_ATTRIBUTE);
    assertEquals(anySimpleType, attributeDeclaration.getTypeDefinition());
  }

  /**
   * See https://bugs.eclipse.org/bugs/show_bug.cgi?id=174092 <br />
   * Testing element declarations
   */
  public void testElementWithAnyType()
  {
    XSDElementDeclaration elementDeclaration = schema.resolveElementDeclaration("testElement");

    // In the beginning the element's type should be anyType

    assertEquals(anyType, elementDeclaration.getTypeDefinition());

    // Setting the element's type from anyType to string

    elementDeclaration.setTypeDefinition(stringType);

    Element elementDeclarationElement = elementDeclaration.getElement();
    Attr typeAttribute = elementDeclarationElement.getAttributeNode(XSDConstants.TYPE_ATTRIBUTE);
    assertNotNull(typeAttribute);
    assertEquals(STRING, typeAttribute.getValue());

    // Setting the element's type from string to null which should result in the
    // type attribute being removed

    elementDeclaration.setTypeDefinition(null);
    typeAttribute = elementDeclarationElement.getAttributeNode(XSDConstants.TYPE_ATTRIBUTE);
    assertNull(typeAttribute);

    // Setting the type attribute through the DOM to string

    elementDeclarationElement.setAttribute(XSDConstants.TYPE_ATTRIBUTE, STRING);
    assertEquals(stringType, elementDeclaration.getTypeDefinition());

    // Setting the element's type from string to anySimpleType

    elementDeclaration.setTypeDefinition(anySimpleType);
    typeAttribute = elementDeclarationElement.getAttributeNode(XSDConstants.TYPE_ATTRIBUTE);
    assertNotNull(typeAttribute);
    assertEquals(ANY_SIMPLE_TYPE, typeAttribute.getValue());

    // Removing the type attribute through the DOM

    elementDeclarationElement.removeAttribute(XSDConstants.TYPE_ATTRIBUTE);
    assertEquals(anyType, elementDeclaration.getTypeDefinition());
  }
}
