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
 * $Id: NamespaceTest.java,v 1.1 2004/11/04 05:52:46 marcelop Exp $
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
import org.eclipse.emf.test.models.dbitem.DbitemPackage;
import org.eclipse.emf.test.models.dbitem.util.DbitemResourceFactoryImpl;
import org.eclipse.emf.test.models.dbprice.DbpricePackage;
import org.eclipse.emf.test.xml.EMFTestXMLPlugin;


public class NamespaceTest extends TestCase
{
  
  final static String BASE_XML_URI = EMFTestXMLPlugin.getPluginDirectory() + "/data/xml/";
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
  protected void setUp() throws Exception
  {
    rs = new ResourceSetImpl();
    DbpricePackage p1 = DbpricePackage.eINSTANCE;
    DbitemPackage p2 = DbitemPackage.eINSTANCE;
    rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new DbitemResourceFactoryImpl());
    file = BASE_XML_URI + "dbitemtest.xml";
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
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