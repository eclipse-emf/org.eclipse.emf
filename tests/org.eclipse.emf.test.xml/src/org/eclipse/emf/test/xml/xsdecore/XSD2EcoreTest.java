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
 * $Id: XSD2EcoreTest.java,v 1.2 2005/01/05 20:42:51 marcelop Exp $
 */
package org.eclipse.emf.test.xml.xsdecore;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.test.xml.TestUtil;
import org.eclipse.emf.test.xml.xmi.CompareXML;

import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;

/**
 * Test for conversion from XSD to ECore - compare against expected output
 */
public class XSD2EcoreTest extends TestCase
{
  DocumentBuilder builder = null;

  XSDEcoreBuilder xsdEcoreBuilder;
  Resource resource;

  // list of xsd files to generate .ecore
  Vector xsdfiles;

  // expected output for .ecore files for given xsd files
  Vector ecorefiles;

  // base uri of the xsd and ecore files
  final static String BASE_XSD_URI = TestUtil.getPluginDirectory() + "/data/xsd/";
  final static String BASE_ECORE_URI = TestUtil.getPluginDirectory() + "/data/ecore/";

  // to serialize .ecore files turn this on
  final static boolean SERIALISE_ECORE = false;

  public XSD2EcoreTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("XSD2EcoreTest");
    ts.addTest(new XSD2EcoreTest("xsd2ecore"));
    return ts;
  }

  /**
   * @see junit.framework.TestCase#setUp()
   */
  protected void setUp() throws Exception
  {
    xsdEcoreBuilder = new XSDEcoreBuilder();
    ecorefiles = new Vector();
    xsdfiles = new Vector();

    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    builder = factory.newDocumentBuilder();
    // Add XSD file to convert to ECORE
    xsdfiles.add(BASE_XSD_URI + "order.xsd");

    // Add in the right order the files to compare with the output
    
    ecorefiles.add(BASE_ECORE_URI + "org.eclipse.emf.test.models.order.ecore");
    ecorefiles.add(BASE_ECORE_URI + "org.eclipse.emf.test.models.customer.ecore");
    ecorefiles.add(BASE_ECORE_URI + "org.eclipse.emf.test.models.movie.db.ecore");
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
  protected void tearDown() throws Exception
  {
    builder = null;
    xsdEcoreBuilder = null;
    xsdfiles = null;
    ecorefiles = null;
  }

  public void xsd2ecore() throws Exception
  {

    URI uri = null;

    for (int k = 0; k < xsdfiles.size(); k++)
    {
      
      File file = new File((String)xsdfiles.get(k));
      uri = URI.createFileURI(file.getCanonicalFile().toString());

      // generate resources
      Collection resources = xsdEcoreBuilder.generateResources(uri);
      
      // fix ecore generated resources
      resources = fixEcoreResorces(resources);
      int counter = 0;

      for (Iterator rs = resources.iterator(); rs.hasNext();)
      {
        resource = (Resource)rs.next();
        ByteArrayOutputStream outputstream = new ByteArrayOutputStream(2064);
        resource.save(outputstream, Collections.EMPTY_MAP);

        if (SERIALISE_ECORE)
        {
          FileOutputStream fileoutput = new FileOutputStream(BASE_ECORE_URI+"out" + counter
              + ".xml");
          resource.save(fileoutput, Collections.EMPTY_MAP);
          //resource.save(System.out, Collections.EMPTY_MAP);
        }
        String expectedOutput = (String) ecorefiles.get(counter++);
        CompareXML.compareFiles(builder, expectedOutput ,new ByteArrayInputStream(outputstream.toByteArray()) );
      }
    }

  }
  Collection fixEcoreResorces(Collection generatedResources)
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    for (Iterator i = generatedResources.iterator(); i.hasNext();)
    {
      Resource resource = (Resource)i.next();
      if (resource instanceof XMIResource)
      {
        // fix resource URI
        EPackage ePackage = (EPackage)resource.getContents().get(0);
        String ecoreFileName = ePackage.getName() + ".ecore";
        URI ecoreURI = URI.createFileURI(TestUtil.getPluginDirectory() + "/data/xsd_ecore/" + ecoreFileName);
        Resource newResource = resourceSet.createResource(URI.createURI("*.ecore"));
        newResource.setURI(ecoreURI);

        // fix Name of resource       
        String name = ePackage.getName();
        int index = name.lastIndexOf(".");
        if (index != -1)
        {
          name = name.substring(index + 1);
          ePackage.setName(name);
        }
        newResource.getContents().add(ePackage);
      }

    }
    return resourceSet.getResources();
  }

}