/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Peter Nehrer IBM - Initial API and implementation
 */
package org.eclipse.emf.test.xml;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;

public class DTDTest extends TestCase
{
  static final String BASE_XML_URI = TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/dtd/";
  static final String LF = System.getProperty("line.separator");

  static final String XML = 
    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + LF +
    "<!DOCTYPE Library SYSTEM \"library.dtd\">" + LF +
    "<Library>" + LF +
    "  <Book ISBN=\"8763-343-2343\"><Title>Professional JINI</Title><Author>Sing Li</Author><Publisher>Wrox Publications</Publisher><Date_Published>22/10/1999</Date_Published></Book>" + LF +
    "</Library>";

  ResourceSet resourceSet; 

  public DTDTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("DTDTest");
    ts.addTestSuite(DTDTest.class);
    return ts;
  }

  @Override
  protected void setUp() throws Exception
  {
    resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
      (Resource.Factory.Registry.DEFAULT_EXTENSION, new GenericXMLResourceFactoryImpl());
  }

  public void testLoadDocumentWithDTD() throws Exception
  {
    Resource resource = resourceSet.getResource(URI.createFileURI(BASE_XML_URI + "library.xml"), true);
    Map<Object, Object> options = new HashMap<Object, Object>();
    options.put(XMLResource.OPTION_SAVE_DOCTYPE, Boolean.TRUE);
    StringWriter out = new StringWriter();
    resource.save(new URIConverter.WriteableOutputStream(out, "UTF-8"), options);
    out.close();
    assertEquals(XML, out.toString());
  }
}
