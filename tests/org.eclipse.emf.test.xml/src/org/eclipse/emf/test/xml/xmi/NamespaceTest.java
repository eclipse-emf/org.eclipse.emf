/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */

package org.eclipse.emf.test.xml.xmi;


import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Collections;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.models.dbitem.DBItemPackage;
import org.eclipse.emf.test.models.dbitem.util.DBItemResourceFactoryImpl;
import org.eclipse.emf.test.models.dbprice.DBPricePackage;
import org.eclipse.emf.test.xml.AllSuites;


public class NamespaceTest extends TestCase
{
  
  final static String BASE_XML_URI = TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/xml/";
  String file = null;
  ResourceSet rs; 

  public NamespaceTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("NamespaceTest");
    ts.addTest(new NamespaceTest("fileisLoadXML"));
    ts.addTest(new NamespaceTest("byteisLoadXML"));
    return ts;
  }

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception
  {
    rs = new ResourceSetImpl();
    DBPricePackage.eINSTANCE.getName();
    DBItemPackage.eINSTANCE.getName();
    rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new DBItemResourceFactoryImpl());
    file = BASE_XML_URI + "dbitemtest.xml";
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception
  {
    rs = null;
  }

  public void fileisLoadXML() throws MalformedURLException, IOException
  {
    FileInputStream fi = null;
    Resource r = rs.createResource(URI.createURI(".xml"));

    fi = new FileInputStream(file);
    r.load(fi, Collections.EMPTY_MAP);
  }

  public void byteisLoadXML() throws MalformedURLException, IOException
  {
    byte[] bbuffer = new byte [2064];
    int length = 0;

    java.net.URL u = new java.net.URL("file:///" + file);
    java.io.InputStream i = u.openStream();
    length = i.read(bbuffer, 0, 2064);
    i.close();

    ByteArrayInputStream bi = new ByteArrayInputStream(bbuffer, 0, length);
    Resource r = rs.createResource(URI.createURI(".xml"));

    r.load(bi, Collections.EMPTY_MAP);
  }
}