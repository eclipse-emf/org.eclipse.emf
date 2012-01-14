/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.core.change;

import java.util.Arrays;
import java.util.Collections;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.test.models.library.Book;
import org.eclipse.emf.test.models.library.Library;
import org.eclipse.emf.test.models.library.LibraryFactory;
import org.eclipse.emf.test.models.library.LibraryPackage;
import org.eclipse.emf.test.models.library.Writer;

/**
 * @since 2.3.0
 */
public class ChangeDescriptionBuilderTest extends TestCase
{
  public ChangeDescriptionBuilderTest(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite ts = new TestSuite("ManualRecorderTest");
    ts.addTest(new ChangeDescriptionBuilderTest("testRecord1"));
    ts.addTest(new ChangeDescriptionBuilderTest("testRecord2"));
    return ts;
  }
  
  /*
   * Basic recording, very simple model, no resources
   */
  public void testRecord1() throws Exception
  {
    final Library library = LibraryFactory.eINSTANCE.createLibrary();
    final Writer writer = LibraryFactory.eINSTANCE.createWriter();
    final Book book1 = LibraryFactory.eINSTANCE.createBook();
    final Book book2 = LibraryFactory.eINSTANCE.createBook();

    class Helper
    {
      public void setStateA()
      {
        library.setName("LibraryName2");
        library.getBooks().add(book1);
        library.getWriters().add(writer);
        book1.setAuthor(writer);
        writer.setName("Joe");
      }

      // Tests to verify if the objects are in stateA
      public void assertStateA()
      {
        assertEquals("LibraryName2", library.getName());
        assertEquals(1, library.getBooks().size());
        assertEquals(book1, library.getBooks().get(0));
        assertEquals(writer, book1.getAuthor());
        assertEquals(1, library.getWriters().size());
        assertEquals(writer, library.getWriters().get(0));
        assertEquals(1, writer.getBooks().size());
        assertEquals("Joe", writer.getName());
        assertNull(book2.getAuthor());        
        
        // This is the case after an applyAndReverse
        if (book2.eContainer() != null)
        {
          assertTrue(book2.eContainer() instanceof ChangeDescription);
        }
      }

      // Tests to verify if the objects are in stateB
      public void assertStateB()
      {
        assertEquals("LibraryName1", library.getName());
        assertEquals(1, library.getBooks().size());
        assertEquals(book2, library.getBooks().get(0));
        assertNull(book2.getAuthor());        
        assertNull(book1.getAuthor());        
        assertTrue(library.getWriters().isEmpty());
        assertTrue(writer.getBooks().isEmpty());
        assertEquals("John", writer.getName());
      }      
    }
        
    Helper helper = new Helper();
    helper.setStateA();
    helper.assertStateA();

    // The descriptions below should describe the stateB
    ChangeDescriptionBuilder manualDescriber = new ChangeDescriptionBuilder();
    manualDescriber.recordSetFeature(library, LibraryPackage.Literals.LIBRARY__NAME, "LibraryName1");
    manualDescriber.recordSetFeature(library, LibraryPackage.Literals.LIBRARY__BOOKS, Collections.singleton(book2));
    manualDescriber.recordUnsetFeature(library, LibraryPackage.Literals.LIBRARY__WRITERS);
    manualDescriber.recordUnsetFeature(book1, LibraryPackage.Literals.BOOK__AUTHOR);
    manualDescriber.recordSetFeature(writer, LibraryPackage.Literals.WRITER__NAME, "John");
    manualDescriber.recordUnsetFeature(writer, LibraryPackage.Literals.WRITER__BOOKS);
    ChangeDescription changeDescription = manualDescriber.endRecording();
    
    helper.assertStateA();
    changeDescription.applyAndReverse();
    helper.assertStateB();
    changeDescription.applyAndReverse();
    helper.assertStateA();
    changeDescription.apply();
    helper.assertStateB();
  }

  /*
   * Playing with resources, very simple model
   */
  public void testRecord2() throws Exception
  {
    final Library library = LibraryFactory.eINSTANCE.createLibrary();
    final Writer writer = LibraryFactory.eINSTANCE.createWriter();
    final Book book1 = LibraryFactory.eINSTANCE.createBook();
    final Book book2 = LibraryFactory.eINSTANCE.createBook();
    
    final Resource resource1 = new ResourceImpl(URI.createFileURI("/home/res1"));
    final Resource resource2 = new ResourceImpl(URI.createFileURI("/home/res2"));

    class Helper
    {
      public void setStateA()
      {
        resource1.getContents().add(writer);
        resource2.getContents().add(library);
        library.getBooks().add(book1);
      } 
      
      // Tests to verify if the objects are in stateA
      public void assertStateA()
      {
        assertEquals(1, resource1.getContents().size());
        assertEquals(1, resource2.getContents().size());

        assertEquals(resource1, writer.eResource());
        assertEquals(resource2, library.eResource());
        assertEquals(resource2, book1.eResource());
        assertNull(book2.eResource());
        
        assertEquals(1, library.getBooks().size());
        assertEquals(book1, library.getBooks().get(0));
      }
      
      // Tests to verify if the objects are in stateB
      public void assertStateB()
      {
        assertEquals(2, resource1.getContents().size());
        assertEquals(1, resource2.getContents().size());

        assertEquals(resource1, library.eResource());
        assertEquals(resource1, book2.eResource());
        assertEquals(resource2, writer.eResource());
        assertNull(book1.eResource());
        
        assertTrue(library.getBooks().isEmpty());        
      }      
    }
        
    Helper helper = new Helper();
    helper.setStateA();
    helper.assertStateA();

    // The descriptions below should describe the stateB 
    ChangeDescriptionBuilder manualDescriber = new ChangeDescriptionBuilder();
    manualDescriber.recordResourceContents(resource1, Arrays.asList(library, book2));
    manualDescriber.recordResourceContents(resource2, Arrays.asList(writer));
    manualDescriber.recordUnsetFeature(library, LibraryPackage.Literals.LIBRARY__BOOKS);
    ChangeDescription changeDescription = manualDescriber.endRecording();
    
    helper.assertStateA();
    changeDescription.applyAndReverse();
    helper.assertStateB();
    changeDescription.applyAndReverse();
    helper.assertStateA();
    changeDescription.apply();
    helper.assertStateB();
  }  
}
