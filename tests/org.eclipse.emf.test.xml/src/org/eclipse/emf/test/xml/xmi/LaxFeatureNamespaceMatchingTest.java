/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */

package org.eclipse.emf.test.xml.xmi;


import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.models.library.Book;
import org.eclipse.emf.test.models.library.Library;
import org.eclipse.emf.test.models.library.LibraryFactory;
import org.eclipse.emf.test.models.library.LibraryPackage;
import org.eclipse.emf.test.models.library.Writer;
import org.eclipse.emf.test.xml.AllSuites;


public class LaxFeatureNamespaceMatchingTest extends TestCase
{
  final static String BASE_XML_URI = TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/xml/";
  final static String LF = System.getProperty("line.separator");

  ResourceSet resourceSet; 

  public LaxFeatureNamespaceMatchingTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("LaxFeatureNamespaceMatchingTest");
    ts.addTest(new LaxFeatureNamespaceMatchingTest("test"));
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
        new XMLResourceFactoryImpl()
        {
          @Override
          public Resource createResource(URI uri)
          {
            XMLResource result = (XMLResource)super.createResource(uri);

            ExtendedMetaData extendedMetaData = 
              new BasicExtendedMetaData(new EPackageRegistryImpl(EPackage.Registry.INSTANCE))
              {
                @Override
                protected boolean isFeatureNamespaceMatchingLax()
                {
                  return true;
                }
              };
            extendedMetaData.putPackage(null, LibraryPackage.eINSTANCE);
            result.getDefaultLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
            result.getDefaultSaveOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);

            result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
            result.getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);

            result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);
            
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

  public void test() throws MalformedURLException, IOException
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
    
    StringWriter mainWriter = new StringWriter();
    mainLibraryResource.save(new URIConverter.WriteableOutputStream(mainWriter, "UTF-8"), null);
    String expectedResult = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + LF +
       "<org.eclipse.emf.test.models.library:Library xmlns:org.eclipse.emf.test.models.library=\"http:///org.eclipse.emf.test.models/Library\" name=\"main\">" + LF +
       
       "  <writers name=\"First Author\" books=\"first.library#//@books.0\"/>" + LF +
       "  <writers name=\"Second Author\" books=\"second.library#//@books.0\"/>" + LF +
       "</org.eclipse.emf.test.models.library:Library>" + LF;
    String result = mainWriter.toString();
    assertEquals(expectedResult, result);
    
    Resource reloadedMainLibraryResource = resourceSet.createResource(URI.createFileURI(BASE_XML_URI + "/main2.library"));
    result = result.replaceAll(":?org\\.eclipse\\.emf\\.test\\.models\\.library:?", "");
    assertFalse(expectedResult.equals(result));
    // System.err.println("###" + result);
    reloadedMainLibraryResource.load(new URIConverter.ReadableInputStream(result), null);
    mainWriter = new StringWriter();
    mainLibraryResource.save(new URIConverter.WriteableOutputStream(mainWriter, "UTF-8"), null);
    result = mainWriter.toString();
    assertEquals(expectedResult, result);

    reloadedMainLibraryResource = resourceSet.createResource(URI.createFileURI(BASE_XML_URI + "/main3.library"));
    result = result.replaceAll("<writers ", "<org.eclipse.emf.test.models.library:writers ");
    assertFalse(expectedResult.equals(result));
    // System.err.println("###" + result);
    reloadedMainLibraryResource.load(new URIConverter.ReadableInputStream(result), null);
    mainWriter = new StringWriter();
    mainLibraryResource.save(new URIConverter.WriteableOutputStream(mainWriter, "UTF-8"), null);
    assertEquals(expectedResult, mainWriter.toString());

    reloadedMainLibraryResource = resourceSet.createResource(URI.createFileURI(BASE_XML_URI + "/main4.library"));
    result = result.replaceAll(":?org.eclipse.emf.test.models.library:?", "").replaceAll("xmlns=[^ ]* ", "");
    // System.err.println("###" + result);
    assertFalse(expectedResult.equals(result));
    reloadedMainLibraryResource.load(new URIConverter.ReadableInputStream(result), null);
    mainWriter = new StringWriter();
    mainLibraryResource.save(new URIConverter.WriteableOutputStream(mainWriter, "UTF-8"), null);
    result = mainWriter.toString();
    assertEquals(expectedResult, result);

  }
}
