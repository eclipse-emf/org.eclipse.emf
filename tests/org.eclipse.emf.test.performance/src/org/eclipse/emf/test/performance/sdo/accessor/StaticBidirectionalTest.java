/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: StaticBidirectionalTest.java,v 1.16.2.1 2005/05/30 19:26:59 nickb Exp $
 */
package org.eclipse.emf.test.performance.sdo.accessor;


import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;
import org.eclipse.emf.test.performance.EMFPerformanceTestCase;

import com.example.sdo.library.Book;
import com.example.sdo.library.Library;
import com.example.sdo.library.LibraryFactory;
import com.example.sdo.library.LibraryPackage;
import com.example.sdo.library.Writer;
import com.example.sdo.library.util.DoNothingAdapter;
import commonj.sdo.DataObject;
import commonj.sdo.Property;


public class StaticBidirectionalTest extends EMFPerformanceTestCase
{
  protected static final int REPETITIONS_20 = 70;

  protected static final int ITERATIONS_25K = 500000;

  protected static final int ITERATIONS_8K = 2400000;

  protected LibraryFactory libFactoryInstance = LibraryFactory.eINSTANCE;

  static final int NUMBER = 20000;

  protected DoNothingAdapter adapter = DoNothingAdapter.INSTANCE;

  DataObject lib;

  static Writer[] writers;

  static Book[] books;

  // model

  Property authorProp;

  EStructuralFeature authorFeat;

  Property bookProp;

  EStructuralFeature bookFeat;

  public StaticBidirectionalTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite testSuite = new TestSuite();

    testSuite.addTest(new StaticBidirectionalTest("setAdaptedByGenerated").setWarmUp(0).setRepetitions(35));
    //HOLD
    testSuite.addTest(new StaticBidirectionalTest("setByGenerated").setWarmUp(1).setRepetitions(REPETITIONS_20));
    //HOLD
    testSuite.addTest(new StaticBidirectionalTest("setWithESet").setWarmUp(1).setRepetitions(REPETITIONS_20));

    return testSuite;
  }

  protected void setUp() throws Exception
  {
    super.setUp();
    libSetUp();
  }

  protected void libSetUp()
  {
    initLib();
    initModel();
  }

  protected void initLib()
  {
    Library lib = libFactoryInstance.createLibrary();
    this.lib = (DataObject)lib;
    lib.setName("The Open Library");

    if (writers == null)
    {
      Writer writer = null;
      writers = new Writer [NUMBER];
      for (int i = 0; i < NUMBER; i++)
      {
        writer = libFactoryInstance.createWriter();
        writers[i] = writer;
        writer.setName("Mr. writer " + i);
      }
    }

    List libWriters = lib.getWriters();
    libWriters.add(0, writers[0]);
    libWriters.add(1, writers[1]);

    if (books == null)
    {
      Book book = null;
      books = new Book [NUMBER];
      for (int i = 0; i < NUMBER; i++)
      {
        book = libFactoryInstance.createBook();
        books[i] = book;
        book.setTitle("The Title " + i);
        book.setAuthor((Writer)writers[i]);
      }
    }

    List libBooks = lib.getBooks();
    libBooks.add(0, books[0]);
    libBooks.add(1, books[1]);
  }

  private void initModel()
  {
    LibraryPackage libPackageInst = LibraryPackage.eINSTANCE;

    authorFeat = libPackageInst.getBook_Author();
    authorProp = SDOUtil.adaptProperty(authorFeat);

    bookFeat = libPackageInst.getWriter_Books();
    bookProp = SDOUtil.adaptProperty(bookFeat);
  }

  public void setByGenerated()
  {
    Library lib = (Library)this.lib;
    Book book0 = (Book)lib.getBooks().get(0);
    List writers = lib.getWriters();
    Writer writer0 = (Writer)writers.get(0);
    Writer writer1 = (Writer)writers.get(1);

    startMeasuring();
    for (int i = 0; i < ITERATIONS_25K; i++)
    {
      book0.setAuthor(writer1);
      book0.setAuthor(writer0);
    }
    stopMeasuring();
  }

  public void setAdaptedByGenerated()
  {
    Library lib = (Library)this.lib;
    List books = lib.getBooks();
    Book book0 = (Book)lib.getBooks().get(0);
    Book book1 = (Book)lib.getBooks().get(1);
    List writers = lib.getWriters();
    Writer writer0 = (Writer)writers.get(0);
    Writer writer1 = (Writer)writers.get(1);

    ((Notifier)book0).eAdapters().add(adapter);
    ((Notifier)book0).eAdapters().add(adapter);
    ((Notifier)writer0).eAdapters().add(adapter);
    ((Notifier)writer1).eAdapters().add(adapter);

    startMeasuring();
    for (int i = 0; i < ITERATIONS_8K; i++)
    {
      book0.setAuthor(writer1);
      book0.setAuthor(writer0);
    }
    stopMeasuring();
  }

  public void setWithESet()
  {
    Library lib = (Library)this.lib;
    List writers = lib.getWriters();
    EObject book0 = (EObject)lib.getBooks().get(0);
    Object writer0 = writers.get(0);
    Object writer1 = writers.get(1);
    EStructuralFeature authorFeat = this.authorFeat;

    startMeasuring();
    for (int i = 0; i < ITERATIONS_25K; i++)
    {
      book0.eSet(authorFeat, writer1);
      book0.eSet(authorFeat, writer0);
    }
    stopMeasuring();
  }

}
