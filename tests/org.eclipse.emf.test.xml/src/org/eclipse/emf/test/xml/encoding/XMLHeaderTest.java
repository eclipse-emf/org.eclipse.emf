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

package org.eclipse.emf.test.xml.encoding;


import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLOptions;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLOptionsImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;


public class XMLHeaderTest extends TestCase
{
  public XMLHeaderTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("XMLHeadterTest");
    ts.addTest(new XMLHeaderTest("testDeprecatedMethods"));
    ts.addTest(new XMLHeaderTest("testNonDeprecatedMethods"));
    return ts;
  }
  
  public void testDeprecatedMethods() throws Exception
  {
    test("1.0", "ASCII", true);
    test("1.1", "UTF-8", true);
    test("1.1", "UTF-16BE", true);
    test("1.1", "UTF-16LE", true);
  }

  public void testNonDeprecatedMethods() throws Exception
  {
    test("1.0", "ASCII", false);
    test("1.1", "UTF-8", false);
    test("1.1", "UTF-16BE", false);
    test("1.1", "UTF-16LE", false);
  }
  
  protected void test(String xmlVersion, String encoding, boolean useNonDeprecatedMethods) throws Exception
  {
    XMLResource resource = (XMLResource) new XMLResourceFactoryImpl().createResource(URI.createURI("encoding.xml"));
    resource.setEncoding("junk");

    XMLOptions option = new XMLOptionsImpl();
    option.setProcessAnyXML(true);
    Map<Object, Object> options = new HashMap<Object, Object>();
    options.put(XMLResource.OPTION_XML_OPTIONS, option);
    options.put(XMLResource.OPTION_USE_DEPRECATED_METHODS, useNonDeprecatedMethods ? Boolean.FALSE : Boolean.TRUE);

    String source = "<?xml version=\""+ xmlVersion + "\" encoding=\"" + encoding + "\"?><any/>";
    ByteArrayInputStream in = new ByteArrayInputStream(source.getBytes(encoding));
    resource.load(in, options);
    assertEquals(xmlVersion, resource.getXMLVersion());
    assertEquals(encoding, resource.getEncoding());
  }
}
