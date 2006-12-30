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
 * $Id: OrderTest.java,v 1.5 2006/12/30 03:43:43 marcelop Exp $
 */
package org.eclipse.emf.test.xml.xmi;


import java.util.HashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.test.models.customer.CustomerPackage;
import org.eclipse.emf.test.models.movie.db.DbPackage;
import org.eclipse.emf.test.models.order.OrderPackage;
import org.eclipse.emf.test.xml.TestUtil;

/**
 * Test for XMI package: loading data/order.xml 
 */
public class OrderTest extends TestCase
{
  final static String BASE_XML_URI = TestUtil.getPluginDirectory() + "/data/xml/";

  String inputXML;

  HashMap<String, Object> options;

  public OrderTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("OrderTest");
    ts.addTestSuite(OrderTest.class);
    return ts;
  }

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception
  {
    OrderPackage.eINSTANCE.getName();
    CustomerPackage.eINSTANCE.getName();
    DbPackage.eINSTANCE.getName();
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xml", new XMLResourceFactoryImpl());
    inputXML = BASE_XML_URI + "order.xml";
    options = new HashMap<String, Object>();
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception
  {
    options = null;
  }

  public void testQname() throws Exception
  {
    URI uri = URI.createFileURI(inputXML);
    ResourceSet resourceSet = new ResourceSetImpl();
    Resource resource = resourceSet.createResource(uri);
    options.put(XMLResource.OPTION_EXTENDED_META_DATA, ExtendedMetaData.INSTANCE);
    resource.load(options);
  }

}