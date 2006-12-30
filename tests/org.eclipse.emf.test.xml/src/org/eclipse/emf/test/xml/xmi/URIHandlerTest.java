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
 * $Id: URIHandlerTest.java,v 1.2 2006/12/30 03:43:43 marcelop Exp $
 */

package org.eclipse.emf.test.xml.xmi;


import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLResource.ResourceEntityHandler;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.ResourceEntityHandlerImpl;
import org.eclipse.emf.test.xml.TestUtil;
import org.eclipse.example.library.Book;
import org.eclipse.example.library.Library;
import org.eclipse.example.library.LibraryFactory;
import org.eclipse.example.library.LibraryPackage;
import org.eclipse.example.library.Writer;
import org.w3c.dom.Document;


public class URIHandlerTest extends TestCase
{
  final static String BASE_XML_URI = TestUtil.getPluginDirectory() + "/data/xml/";
  final static String LF = System.getProperty("line.separator");

  ResourceSet resourceSet; 

  public URIHandlerTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("URITest");
    ts.addTest(new URIHandlerTest("testEntityHandler"));
    return ts;
  }

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception
  {
    resourceSet = new ResourceSetImpl();
    LibraryPackage.eINSTANCE.eClass();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
      ("*", 
        new GenericXMLResourceFactoryImpl()
        {
          @Override
          public Resource createResource(URI uri)
          {
            XMLResource result = (XMLResource)super.createResource(uri);
            ResourceEntityHandlerImpl resourceEntityHandler = new ResourceEntityHandlerImpl("_");
            result.getDefaultSaveOptions().put(XMLResource.OPTION_RESOURCE_ENTITY_HANDLER, resourceEntityHandler);
            result.getDefaultLoadOptions().put(XMLResource.OPTION_RESOURCE_ENTITY_HANDLER, resourceEntityHandler);
            return result;
          }
        });
  }

  /**
   * @see junit.framework.TestCase#tearDown()
   */
  @Override
  protected void tearDown() throws Exception
  {
    resourceSet = null;
  }

  public void testEntityHandler() throws MalformedURLException, IOException, TransformerException
  {
    Resource firstLibraryResource = resourceSet.createResource(URI.createFileURI(BASE_XML_URI + "first.library"));
    Library firstLibrary = LibraryFactory.eINSTANCE.createLibrary();
    firstLibrary.setName("first");
    Book firstBook = LibraryFactory.eINSTANCE.createBook();
    firstBook.setTitle("First Book");
    firstLibrary.getBooks().add(firstBook);
    firstLibraryResource.getContents().add(firstLibrary);
    StringWriter firstWriter = new StringWriter();
    firstLibraryResource.save(new URIConverter.WriteableOutputStream(firstWriter, "UTF-8"), null);
    
    Resource secondLibraryResource = resourceSet.createResource(URI.createFileURI(BASE_XML_URI + "second.library"));
    Library secondLibrary = LibraryFactory.eINSTANCE.createLibrary();
    secondLibrary.setName("second");
    Book secondBook = LibraryFactory.eINSTANCE.createBook();
    secondBook.setTitle("Second Book");
    secondLibrary.getBooks().add(secondBook);
    secondLibraryResource.getContents().add(secondLibrary);
    StringWriter secondWriter = new StringWriter();
    secondLibraryResource.save(new URIConverter.WriteableOutputStream(secondWriter, "UTF-8"), null);
    
    XMLResource mainLibraryResource = (XMLResource)resourceSet.createResource(URI.createFileURI(BASE_XML_URI + "main.library"));
    Library mainLibrary = LibraryFactory.eINSTANCE.createLibrary();
    mainLibrary.setName("main");
    Writer firstAuthor = LibraryFactory.eINSTANCE.createWriter();
    firstAuthor.setName("First Author");
    firstAuthor.getBooks().add(firstBook);
    mainLibrary.getWriters().add(firstAuthor);
    Writer secondAuthor = LibraryFactory.eINSTANCE.createWriter();
    secondAuthor.setName("Second Author");
    secondAuthor.getBooks().add(secondBook);
    mainLibrary.getWriters().add(secondAuthor);
    mainLibraryResource.getContents().add(mainLibrary);
    
    ResourceEntityHandler resourceEntityHandler = 
      (ResourceEntityHandler)(mainLibraryResource).getDefaultLoadOptions().get(XMLResource.OPTION_RESOURCE_ENTITY_HANDLER);
    resourceEntityHandler.handleEntity("first", firstLibraryResource.getURI().toString());
    resourceEntityHandler.handleEntity("second", secondLibraryResource.getURI().toString());
    
    StringWriter mainWriter = new StringWriter();
    mainLibraryResource.save(new URIConverter.WriteableOutputStream(mainWriter, "UTF-8"), null);
    
    mainLibraryResource.setURI(URI.createFileURI(BASE_XML_URI + "/vault/main.library"));
    mainWriter = new StringWriter();
    mainLibraryResource.save(new URIConverter.WriteableOutputStream(mainWriter, "UTF-8"), null);
    assertEquals
      ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + LF +
         "<!DOCTYPE org.eclipse.example.library:Library [" + LF +
         "<!ENTITY first \"../first.library\">" + LF +
         "<!ENTITY second \"../second.library\">" + LF +
         "]>" + LF +
         "<org.eclipse.example.library:Library xmlns:org.eclipse.example.library=\"http:///org/eclipse/example/library.ecore\" name=\"main\">" + LF +
         "  <writers name=\"First Author\" books=\"&first;#//@books.0\"/>" + LF +
         "  <writers name=\"Second Author\" books=\"&second;#//@books.0\"/>" + LF +
         "</org.eclipse.example.library:Library>" + LF,
        mainWriter.toString());
    
    Document document = mainLibraryResource.save(null, null, null);
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    mainWriter = new StringWriter();
    transformer.transform(new DOMSource(document), new StreamResult(mainWriter));
    assertEquals
      ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
         "<org.eclipse.example.library:Library xmlns:org.eclipse.example.library=\"http:///org/eclipse/example/library.ecore\" name=\"main\">" +
         "<writers books=\"../first.library#//@books.0\" name=\"First Author\"/>" +
         "<writers books=\"../second.library#//@books.0\" name=\"Second Author\"/>" +
         "</org.eclipse.example.library:Library>",
        mainWriter.toString());
    
    Resource reloadedMainLibraryResource = resourceSet.createResource(URI.createFileURI(BASE_XML_URI + "/vault/main2.library"));
    reloadedMainLibraryResource.load(new URIConverter.ReadableInputStream(mainWriter.toString()), null);
    Library reloadedMainLibrary = ((Library)reloadedMainLibraryResource.getContents().get(0));
    Writer reloadedFirstAuthor = reloadedMainLibrary.getWriters().get(0);
    Book reloadedFirstBook = reloadedFirstAuthor.getBooks().get(0);
    assertEquals(firstLibraryResource.getURI(), reloadedFirstBook.eResource().getURI());
  }
}