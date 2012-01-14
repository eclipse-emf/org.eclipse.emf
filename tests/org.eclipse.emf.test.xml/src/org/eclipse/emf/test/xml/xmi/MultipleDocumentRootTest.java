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
package org.eclipse.emf.test.xml.xmi;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.test.models.movie.db.DBFactory;
import org.eclipse.emf.test.models.movie.db.DBPackage;
import org.eclipse.emf.test.models.movie.db.DocumentRoot;
import org.eclipse.emf.test.models.movie.db.MovieDBType;


/**
 * XMI tests: loading and serializing model with multiple document root instances.
 */
public class MultipleDocumentRootTest extends TestCase
{

  public MultipleDocumentRootTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("MultipleDocumentRootTest");
    ts.addTestSuite(MultipleDocumentRootTest.class);
    return ts;
  }
  
  
  static final String LF = System.getProperty("line.separator");
  static final String [] EXPECTED = 
  {
    "<?xml version=\"1.0\" encoding=\"ASCII\"?>" + LF +
    "<xmi:XMI xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:db=\"http:///org.eclipse.emf.test.models/MovieDB\">" + LF +
    "  <db:DocumentRoot>" + LF +
    "    <movieDB>" + LF +
    "      <comment>1</comment>" + LF +
    "    </movieDB>" + LF +
    "  </db:DocumentRoot>" + LF +
    "  <db:DocumentRoot>" + LF +
    "    <movieDB>" + LF +
    "      <comment>2</comment>" + LF +
    "    </movieDB>" + LF +
    "  </db:DocumentRoot>" + LF +
    "</xmi:XMI>" + LF,

    "<?xml version=\"1.0\" encoding=\"ASCII\"?>" + LF +
    "<xmi:XMI xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:db=\"http:///org.eclipse.emf.test.models/MovieDB\">" + LF +
    "  <db:movieDB>" + LF +
    "    <comment>1</comment>" + LF +
    "  </db:movieDB>" + LF +
    "  <db:movieDB>" + LF +
    "    <comment>2</comment>" + LF +
    "  </db:movieDB>" + LF +
    "</xmi:XMI>" + LF
  };

  public void testMultipleDocumentRoot() throws Exception
  {
    ResourceSet rs = new ResourceSetImpl();
    DBPackage.eINSTANCE.getName();
    
    DocumentRoot documentRoot1 = DBFactory.eINSTANCE.createDocumentRoot();
    MovieDBType movieDB1 = DBFactory.eINSTANCE.createMovieDBType();
    movieDB1.setComment("1");
    documentRoot1.setMovieDB(movieDB1);

    DocumentRoot documentRoot2 = DBFactory.eINSTANCE.createDocumentRoot();
    MovieDBType movieDB2 = DBFactory.eINSTANCE.createMovieDBType();
    movieDB2.setComment("2");
    documentRoot2.setMovieDB(movieDB2);
    
    Map<Object, Object> options = new HashMap<Object, Object>();
    
    for (int i = 0; i < 2; ++i)
    {
      rs.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new XMIResourceFactoryImpl());
      
      if (i == 1)
      {
        options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
      }
      Resource r1 = rs.createResource(URI.createURI("movieDB.xml"));
      r1.getContents().add(documentRoot1);
      r1.getContents().add(documentRoot2);
      
      ByteArrayOutputStream outputstream1 = new ByteArrayOutputStream();
      
      r1.save(outputstream1, options);
      
      Resource r2 = rs.createResource(URI.createURI("movieDB2.xml"));
      r2.load(new ByteArrayInputStream(outputstream1.toByteArray()), options);    
      
      assertEquals(2, r2.getContents().size());
      ByteArrayOutputStream outputstream2 = new ByteArrayOutputStream();
      r2.save(outputstream2, options);
      assertEquals(EXPECTED[i], new String(outputstream2.toByteArray()));
    }
  }
}