/**
 * Copyright (c) 2013 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.xml.xmi;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.test.models.library.Book;
import org.eclipse.emf.test.models.library.Library;
import org.eclipse.emf.test.models.library.LibraryFactory;
import org.eclipse.emf.test.models.library.LibraryPackage;
import org.eclipse.emf.test.models.library.Writer;


/**
 * XMI tests: unloading preserves XMI IDs in proxy URIs.
 * @see <a href="data/library.mdl">data/library.mdl</a>
 * @see <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=412753">Bugzilla 412753</a>
 */
public class UnloadXMIResourceTest extends TestCase
{

  public UnloadXMIResourceTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("UnloadXMIResourceTest");
    ts.addTestSuite(UnloadXMIResourceTest.class);
    return ts;
  }

  public void testHREF() throws Exception
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    LibraryPackage.eINSTANCE.getName();
    LibraryFactory libFactory = LibraryFactory.eINSTANCE;

    Library library = libFactory.createLibrary();

    Book book = libFactory.createBook();
    book.setTitle("Eclipse Modeling Framework");

    Writer writer = libFactory.createWriter();
    writer.setName("Frank Budinsky");

    book.setAuthor(writer);

    library.getBooks().add(book);
    library.getWriters().add(writer);

    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
      ("xmi",
       new XMIResourceFactoryImpl()
       {
         @Override
         public Resource createResource(URI uri)
         {
           return
             new XMIResourceImpl(uri)
             {
               @Override
               protected boolean useUUIDs()
               {
                 return true;
               }
             };
         }
       });

    Resource resource = resourceSet.createResource(URI.createURI("library.xmi"));
    resource.getContents().add(library);

    Map<EObject, URI> proxyURIMap = new HashMap<EObject, URI>();
    for (Iterator<EObject> i = resource.getAllContents(); i.hasNext(); )
    {
      EObject eObject = i.next();
      URI proxyURI = EcoreUtil.getURI(eObject);
      assertTrue(proxyURI.fragment().indexOf('/') == -1);
      proxyURIMap.put(eObject, proxyURI);
    }

    resource.unload();

    for (Map.Entry<EObject, URI> entry : proxyURIMap.entrySet())
    {
      EObject eObject = entry.getKey();
      URI proxyURI = entry.getValue();
      assertEquals(proxyURI, ((InternalEObject)eObject).eProxyURI());
    }
  }

}