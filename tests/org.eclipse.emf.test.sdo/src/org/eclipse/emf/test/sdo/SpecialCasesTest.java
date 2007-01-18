/**
 * <copyright>
 *
 * Copyright (c) 2005-2007 IBM Corporation and others.
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
 * $Id: SpecialCasesTest.java,v 1.6 2007/01/18 15:53:25 marcelop Exp $
 */
package org.eclipse.emf.test.sdo;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.test.models.sdo.library.Book;
import org.eclipse.emf.test.models.sdo.library.Library;
import org.eclipse.emf.test.models.sdo.library.SDOLibraryFactory;
import org.eclipse.emf.test.models.sdo.library.Writer;

import org.eclipse.emf.ecore.sdo.EChangeSummary;
import org.eclipse.emf.ecore.sdo.EDataGraph;
import org.eclipse.emf.ecore.sdo.SDOFactory;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;

import commonj.sdo.DataGraph;


public class SpecialCasesTest extends TestCase
{
  public SpecialCasesTest(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite testSuite = new TestSuite("SDO - Special Cases");
    testSuite.addTest(new SpecialCasesTest("testClassCastExceptionProblem"));
    return testSuite;
  }
  
  /*
   * Bugzilla 81569
   */
  public void testClassCastExceptionProblem() throws Exception
  {
    // 1. set up a library with one writer
    Library library = SDOLibraryFactory.INSTANCE.createLibrary();
    library.setName("Test Library");

    Writer writer = SDOLibraryFactory.INSTANCE.createWriter();
    writer.setName("Test Writer");
    library.getWriters().add(writer);

    DataGraph dataGraph = SDOFactory.eINSTANCE.createEDataGraph();
    ((EDataGraph)dataGraph).setERootObject((EObject)library);

    // 2. add new book with reference to the writer,
    // which you set before or after adding the book to the library
    dataGraph.getChangeSummary().beginLogging();
    Book book = SDOLibraryFactory.INSTANCE.createBook();

    library.getBooks().add(book);
    book.setAuthor(writer);

    // simple changes work even after the book is added
    book.setTitle("New Book");

    // 3. reverse the changes
    dataGraph.getChangeSummary().endLogging();
    ((EChangeSummary)dataGraph.getChangeSummary()).applyAndReverse();

    // 4. clone it
    ByteArrayOutputStream buf = new ByteArrayOutputStream();
    ((EDataGraph)dataGraph).getDataGraphResource().save(buf, null);
    DataGraph dataGraph2 = SDOUtil.loadDataGraph(new ByteArrayInputStream(buf.toByteArray()), null);

    // 5. copy the changes
    EChangeSummary newChangeSummary;
    buf = new ByteArrayOutputStream();
    ((EChangeSummary)dataGraph.getChangeSummary()).eResource().save(buf, null);
    Resource resource = ((EChangeSummary)dataGraph2.getChangeSummary()).eResource();
    resource.unload();
    resource.load(new ByteArrayInputStream(buf.toByteArray()), null);
    newChangeSummary = (EChangeSummary)resource.getContents().get(0);
    ((EDataGraph)dataGraph2).setEChangeSummary(newChangeSummary);

    newChangeSummary.apply();
  }
}