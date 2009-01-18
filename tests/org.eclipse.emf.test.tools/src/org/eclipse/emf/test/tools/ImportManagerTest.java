/**
 * <copyright>
 *
 * Copyright (c) 2009 IBM Corporation and others.
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
 * $Id: ImportManagerTest.java,v 1.1 2009/01/18 03:51:50 davidms Exp $
 */
package org.eclipse.emf.test.tools;


import java.util.Collections;

// Comment this out to test old ImportManager.
//
import org.eclipse.emf.codegen.util.ImportManager;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Tests for {@link org.eclipse.emf.codegen.util.ImportManager ImportManager}.
 * These tests were added when <code>ImportManager</code> underwent major changes in EMF 2.5.
 * They are divided into those that worked on the old implementation and those that only work on the new one.
 * The old implementation is replicated, and extended with new APIs, in {@link org.eclipse.emf.test.tools.ImportManager}.
 * <p>To run the appropriate tests on the old implementation, simply comment out the <code>ImportManager</code> import.
 */
public class ImportManagerTest extends TestCase
{
  private static final boolean NEW = "org.eclipse.emf.codegen.util.ImportManager".equals(ImportManager.class.getName());
  private static final String NL = System.getProperty("line.separator");

  private ImportManager importManager;

  public ImportManagerTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("ImportManagerTest");
    ts.addTest(new ImportManagerTest("testSimpleImport"));
    ts.addTest(new ImportManagerTest("testSimpleImport2"));
    if (NEW)
    {
      ts.addTest(new ImportManagerTest("testWhitespaceImport"));
      ts.addTest(new ImportManagerTest("testWhitespaceImport2"));
      ts.addTest(new ImportManagerTest("testNestedImport"));
    }
    ts.addTest(new ImportManagerTest("testNestedImport2"));
    if (NEW)
    {
      ts.addTest(new ImportManagerTest("testFullNestedImport"));
      ts.addTest(new ImportManagerTest("testNestedWildcardImport"));
    }
    ts.addTest(new ImportManagerTest("testConflictingImport"));
    ts.addTest(new ImportManagerTest("testPseudoImport"));
    ts.addTest(new ImportManagerTest("testMasterImport"));
    ts.addTest(new ImportManagerTest("testImplicitJavaImport"));
    ts.addTest(new ImportManagerTest("testUnqualifiedTypeImport"));
    ts.addTest(new ImportManagerTest("testConflictingImplicitJavaImport"));
    ts.addTest(new ImportManagerTest("testExplicitJavaImport"));
    if (NEW)
    {
      ts.addTest(new ImportManagerTest("testWildcardImport"));
      ts.addTest(new ImportManagerTest("testGenericTypeImport"));
    }
    ts.addTest(new ImportManagerTest("testAutoImport"));
    ts.addTest(new ImportManagerTest("testAutoGenericTypeImport"));
    if (NEW)
    {
      ts.addTest(new ImportManagerTest("testAutoGenericTypeWhitespaceImport"));
    }
    return ts;
  }

  @Override
  protected void setUp() throws Exception
  {
    importManager = new ImportManager("org.eclipse.example.test", "GeneratingClass");
  }

  public void testSimpleImport()
  {
    importManager.addImport("a.MyClass");
    importManager.addImport("a.b.c.MyOtherClass");
    importManager.addImport("a.b.c.ItemClass[]");
    assertSimpleImport();
  }

  public void testSimpleImport2()
  {
    importManager.addImport("a", "MyClass");
    importManager.addImport("a.b.c", "MyOtherClass");
    importManager.addImport("a.b.c", "ItemClass[]");
    assertSimpleImport();
  }

  private void assertSimpleImport()
  {
    assertEquals(3, importManager.getImports().size());
    assertTrue(importManager.hasImport("MyClass"));
    assertTrue(importManager.hasImport("MyOtherClass"));
    assertTrue(importManager.hasImport("ItemClass"));

    assertEquals("MyClass", importManager.getImportedName("a.MyClass"));
    assertEquals("MyOtherClass", importManager.getImportedName("a.b.c.MyOtherClass"));
    assertEquals("ItemClass", importManager.getImportedName("a.b.c.ItemClass"));
    assertEquals("ItemClass[]", importManager.getImportedName("a.b.c.ItemClass[]"));
    assertEquals("ItemClass[][]", importManager.getImportedName("a.b.c.ItemClass[][]"));
    assertEquals("a.b.Foo", importManager.getImportedName("a.b.Foo"));

    assertEquals(NL + "import a.MyClass;" + NL + NL + "import a.b.c.ItemClass;" + NL + "import a.b.c.MyOtherClass;", importManager.computeSortedImports());
  }

  public void testWhitespaceImport()
  {
    importManager.addImport("a. b. c. MyThirdClass");
    assertWhitespaceImport();
  }

  public void testWhitespaceImport2()
  {
    importManager.addImport("a. b. c", " MyThirdClass");
    assertWhitespaceImport();
  }

  private void assertWhitespaceImport()
  {
    assertEquals(1, importManager.getImports().size());
    assertTrue(importManager.hasImport("MyThirdClass"));

    assertEquals("MyThirdClass", importManager.getImportedName("a.b.c.MyThirdClass"));
    assertEquals(" MyThirdClass", importManager.getImportedName("a. b. c. MyThirdClass"));
    assertEquals("a.b . Foo", importManager.getImportedName("a.b . Foo"));

    assertEquals(NL + "import a.b.c.MyThirdClass;", importManager.computeSortedImports());
  }

  public void testNestedImport()
  {
    importManager.addImport("a.b.c.OuterClass$InnerClass");
    importManager.addImport("a.b.c.C1$C2$C3");
    assertNestedImport();
  }

  public void testNestedImport2()
  {
    importManager.addImport("a.b.c", "OuterClass");
    importManager.addImport("a.b.c", "C1");
    assertNestedImport();
  }

  private void assertNestedImport()
  {
    assertEquals(2, importManager.getImports().size());
    assertTrue(importManager.hasImport("OuterClass"));
    assertFalse(importManager.hasImport("InnerClass"));
    assertTrue(importManager.hasImport("C1"));
    assertFalse(importManager.hasImport("C2"));
    assertFalse(importManager.hasImport("C3"));

    assertEquals("OuterClass", importManager.getImportedName("a.b.c.OuterClass"));
    assertEquals("OuterClass.InnerClass", importManager.getImportedName("a.b.c.OuterClass$InnerClass"));
    assertEquals("C1", importManager.getImportedName("a.b.c.C1"));
    assertEquals("C1.C2", importManager.getImportedName("a.b.c.C1$C2"));
    assertEquals("C1.C2.C3", importManager.getImportedName("a.b.c.C1$C2$C3"));
    
    assertEquals(NL + "import a.b.c.C1;" + NL + "import a.b.c.OuterClass;", importManager.computeSortedImports());
  }

  public void testFullNestedImport()
  {
    importManager.addImport("a.b.c.Foo.Bar.Baz");
    assertTrue(importManager.hasImport("Baz"));
    assertFalse(importManager.hasImport("Bar"));
    assertFalse(importManager.hasImport("Foo"));
    assertEquals("Baz", importManager.getImportedName("a.b.c.Foo$Bar$Baz"));
    assertEquals("Baz", importManager.getImportedName("a.b.c.Foo.Bar.Baz"));
    assertEquals("a.b.c.Foo.Bar", importManager.getImportedName("a.b.c.Foo$Bar"));
    assertEquals("a.b.c.Foo.Bar", importManager.getImportedName("a.b.c.Foo.Bar"));
    assertEquals("a.b.c.Foo", importManager.getImportedName("a.b.c.Foo"));

    importManager.addImport("a.b.c.Foo.Bar$Baz");
    assertTrue(importManager.hasImport("Baz"));
    assertTrue(importManager.hasImport("Bar"));
    assertFalse(importManager.hasImport("Foo"));
    assertEquals("Bar.Baz", importManager.getImportedName("a.b.c.Foo$Bar$Baz"));
    assertEquals("Baz", importManager.getImportedName("a.b.c.Foo.Bar.Baz"));
    assertEquals("Bar", importManager.getImportedName("a.b.c.Foo$Bar"));
    assertEquals("Bar", importManager.getImportedName("a.b.c.Foo.Bar"));
    assertEquals("a.b.c.Foo", importManager.getImportedName("a.b.c.Foo"));

    importManager.addImport("a.b.c.Foo$Bar$Baz");
    assertTrue(importManager.hasImport("Baz"));
    assertTrue(importManager.hasImport("Bar"));
    assertTrue(importManager.hasImport("Foo"));
    assertEquals("Foo.Bar.Baz", importManager.getImportedName("a.b.c.Foo$Bar$Baz"));
    assertEquals("Baz", importManager.getImportedName("a.b.c.Foo.Bar.Baz"));
    assertEquals("Foo.Bar", importManager.getImportedName("a.b.c.Foo$Bar"));
    assertEquals("Bar", importManager.getImportedName("a.b.c.Foo.Bar"));
    assertEquals("Foo", importManager.getImportedName("a.b.c.Foo"));
  }

  public void testNestedWildcardImport()
  {
    importManager.addImport("a.b.c.Foo.Bar.*");
    assertEquals("Baz", importManager.getImportedName("a.b.c.Foo$Bar$Baz"));
  }

  public void testUnqualifiedTypeImport()
  {
    assertEquals("Foo", importManager.getImportedName("Foo"));
  }

  public void testConflictingImport()
  {
    importManager.addImport("a.b.c.Foo");
    importManager.addImport("d.e.Foo");
    importManager.addImport("a.Foo");

    assertEquals(1, importManager.getImports().size());
    assertTrue(importManager.hasImport("Foo"));
    assertEquals("Foo", importManager.getImportedName("a.b.c.Foo"));
    assertEquals("d.e.Foo", importManager.getImportedName("d.e.Foo"));
    assertEquals("a.Foo", importManager.getImportedName("a.Foo"));
  }

  public void testPseudoImport()
  {
    importManager.addPseudoImport("a.b.c.Foo");
    importManager.addImport("d.e.Foo");
    importManager.addImport("a.Foo");

    assertEquals(0, importManager.getImports().size());
    assertTrue(importManager.hasImport("Foo"));
    assertEquals("Foo", importManager.getImportedName("a.b.c.Foo"));
    assertEquals("d.e.Foo", importManager.getImportedName("d.e.Foo"));
    assertEquals("a.Foo", importManager.getImportedName("a.Foo"));
  }

  public void testMasterImport()
  {
    importManager.addImport("a.b.c.GeneratingClass");

    assertEquals(0, importManager.getImports().size());
    assertTrue(importManager.hasImport("GeneratingClass"));
    assertEquals("GeneratingClass", importManager.getImportedName("org.eclipse.example.test.GeneratingClass"));
    assertEquals("a.b.c.GeneratingClass", importManager.getImportedName("a.b.c.GeneratingClass"));
  }

  public void testImplicitJavaImport()
  {
    assertEquals("String", importManager.getImportedName("java.lang.String"));
  }

  public void testConflictingImplicitJavaImport()
  {
    importManager.addImport("a.b.c.String");

    assertEquals("a.b.c.String", importManager.getImportedName("a.b.c.String"));
    assertEquals("String", importManager.getImportedName("java.lang.String"));
    assertEquals(0, importManager.getImports().size());
    assertFalse(importManager.hasImport("String"));
  }

  public void testExplicitJavaImport()
  {
    importManager.addJavaLangImports(Collections.singletonList("String"));
    importManager.addImport("a.b.c.String");

    assertEquals("a.b.c.String", importManager.getImportedName("a.b.c.String"));
    assertEquals("String", importManager.getImportedName("java.lang.String"));
    assertEquals(1, importManager.getImports().size());
    assertFalse(importManager.hasImport("String"));
  }

  public void testWildcardImport()
  {
    importManager.addImport("a.b.c.Foo");
    importManager.addImport("a.b.c.*");
    importManager.addImport("a.b.c.Bar");

    assertTrue(importManager.hasImport("Foo"));
    assertTrue(importManager.hasImport("Bar"));
    assertFalse(importManager.hasImport("Baz"));
    assertEquals(1, importManager.getImports().size());
    assertEquals("Baz", importManager.getImportedName("a.b.c.Baz"));

    importManager.addImport("a.b.Baz");
    assertTrue(importManager.hasImport("Baz"));
    assertEquals(2, importManager.getImports().size());
    assertEquals("a.b.c.Baz", importManager.getImportedName("a.b.c.Baz"));
    assertEquals("Baz", importManager.getImportedName("a.b.Baz"));
  }

  public void testGenericTypeImport()
  {
    importManager.addImport("a.b.c.MyClass");
    importManager.addImport("java.util.Map");

    assertEquals("MyClass<Map<String,a.Baz>>", importManager.getImportedName("a.b.c.MyClass<java.util.Map<java.lang.String,a.Baz>>"));
    assertEquals("MyClass<Map<a.Baz, String>>", importManager.getImportedName("a.b.c.MyClass<java.util.Map<a.Baz, java.lang.String>>"));
    assertEquals("MyClass <Map <a.Baz, String> >", importManager.getImportedName("a.b.c.MyClass <java.util.Map <a.Baz, java.lang.String> >"));
    assertEquals("Map  < MyClass < a.Baz > , String >", importManager.getImportedName("java.util.Map  < a.b.c.MyClass < a.Baz > , java.lang.String >"));
    assertEquals("  Map  <   MyClass < a .  Baz > ,   String >", importManager.getImportedName("java . util .  Map  < a . b . c .  MyClass < a .  Baz > , java . lang .  String >"));
  }

  public void testAutoImport()
  {
    assertEquals("Foo", importManager.getImportedName("a.b.c.Foo", true));
    assertEquals("d.e.f.Foo", importManager.getImportedName("d.e.f.Foo", true));
    assertEquals("Bar", importManager.getImportedName("d.e.f.Bar", true));
  }

  public void testAutoGenericTypeImport()
  {
    assertEquals("MyClass<Map<String,Baz>>", importManager.getImportedName("a.b.c.MyClass<java.util.Map<java.lang.String,a.b.Baz>>", true));
    assertEquals("MyClass<Map<a.Baz, String>>", importManager.getImportedName("a.b.c.MyClass<java.util.Map<a.Baz, java.lang.String>>", true));
    assertEquals("Map  < MyClass < a.Baz > , String >", importManager.getImportedName("java.util.Map  < a.b.c.MyClass < a.Baz > , java.lang.String >", true));
  }

  public void testAutoGenericTypeWhitespaceImport()
  {
    assertEquals("  Map  <   MyClass <   Baz > ,   String >", importManager.getImportedName("java . util .  Map  < a . b . c .  MyClass < a .  Baz > , java . lang .  String >", true));
  }
}
