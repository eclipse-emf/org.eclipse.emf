/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */

package org.eclipse.emf.test.xml.xmi;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLResource.ResourceEntityHandler;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.ResourceEntityHandlerImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.models.library.Book;
import org.eclipse.emf.test.models.library.Library;
import org.eclipse.emf.test.models.library.LibraryFactory;
import org.eclipse.emf.test.models.library.LibraryPackage;
import org.eclipse.emf.test.models.library.Writer;
import org.eclipse.emf.test.xml.AllSuites;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;


public class URIHandlerTest
{
  final static String BASE_XMI_URI = TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/xmi/";
  final static String BASE_XML_URI = TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/xml/";
  final static String LF = System.getProperty("line.separator");

  protected ResourceSet resourceSet;

  @Before
  public void setUp() throws Exception
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

  @After
  public void tearDown() throws Exception
  {
    resourceSet = null;
  }

  @Test
  public void testEntityHandler() throws Exception
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
         "<!DOCTYPE org.eclipse.emf.test.models.library:Library [" + LF +
         "<!ENTITY first \"../first.library\">" + LF +
         "<!ENTITY second \"../second.library\">" + LF +
         "]>" + LF +
         "<org.eclipse.emf.test.models.library:Library xmlns:org.eclipse.emf.test.models.library=\"http:///org.eclipse.emf.test.models/Library\"" + LF +
         "    name=\"main\">" + LF +
         "  <writers name=\"First Author\" books=\"&first;#//@books.0\"/>" + LF +
         "  <writers name=\"Second Author\" books=\"&second;#//@books.0\"/>" + LF +
         "</org.eclipse.emf.test.models.library:Library>" + LF,
        mainWriter.toString());

    Document document = mainLibraryResource.save(null, null, null);
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer transformer = transformerFactory.newTransformer();
    mainWriter = new StringWriter();
    transformer.transform(new DOMSource(document), new StreamResult(mainWriter));
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    StringReader stringReader =
      new StringReader
        ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
           "<org.eclipse.emf.test.models.library:Library xmlns:org.eclipse.emf.test.models.library=\"http:///org.eclipse.emf.test.models/Library\" name=\"main\">" +
           "<writers books=\"../first.library#//@books.0\" name=\"First Author\"/>" +
           "<writers books=\"../second.library#//@books.0\" name=\"Second Author\"/>" +
           "</org.eclipse.emf.test.models.library:Library>");
    Document document2 = builder.parse(new InputSource(stringReader));
    CompareXML.compareDocuments
      ("",
       document2,
       document);

    Resource reloadedMainLibraryResource = resourceSet.createResource(URI.createFileURI(BASE_XML_URI + "/vault/main2.library"));
    reloadedMainLibraryResource.load(new URIConverter.ReadableInputStream(mainWriter.toString()), null);
    Library reloadedMainLibrary = ((Library)reloadedMainLibraryResource.getContents().get(0));
    Writer reloadedFirstAuthor = reloadedMainLibrary.getWriters().get(0);
    Book reloadedFirstBook = reloadedFirstAuthor.getBooks().get(0);
    assertEquals(firstLibraryResource.getURI(), reloadedFirstBook.eResource().getURI());
  }

  @Test
  public void testEntityResolver() throws Exception
  {
    // Expecting external entities not to be resolved by default.
    // So the redirection of that external reference to a non-existent location should not result in a file not found exception.
    //
    resourceSet.getURIConverter().getURIMap().put(URI.createFileURI(BASE_XMI_URI + "/root.dtd"), URI.createFileURI(BASE_XMI_URI + "/not-found.dtd"));
    Resource resource = resourceSet.getResource(URI.createFileURI(BASE_XMI_URI + "/test-with-external-entity.xmi"), true);

    try
    {
      // Load it again, but resolving external entities.
      //
      resource.unload();
      resource.load(Collections.singletonMap(XMLResource.OPTION_RESOLVE_ENTITIES, Boolean.TRUE));

      // We should not get here, we should get an exception.
      //
      fail("Expecting the URI to be loaded to the redirected bogus location, causing a FileNotFoundException");
    }
    catch (FileNotFoundException ex)
    {
      // The exception should refer to the redirected URI.
      //
      assertTrue("Expecting the entity reference to be loaded to the redirected bogus location", ex.toString().contains("not-found.dtd"));
    }
  }
}