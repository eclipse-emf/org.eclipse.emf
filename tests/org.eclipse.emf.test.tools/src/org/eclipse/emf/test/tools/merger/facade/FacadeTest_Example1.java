/**
 * Copyright (c) 2006-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */

package org.eclipse.emf.test.tools.merger.facade;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.codegen.merge.java.facade.FacadeFlags;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.JField;
import org.eclipse.emf.codegen.merge.java.facade.JImport;
import org.eclipse.emf.codegen.merge.java.facade.JInitializer;
import org.eclipse.emf.codegen.merge.java.facade.JMethod;
import org.eclipse.emf.codegen.merge.java.facade.JPackage;
import org.eclipse.emf.codegen.merge.java.facade.JType;
import org.eclipse.emf.codegen.merge.java.facade.ast.ASTFacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.jdom.JDOMFacadeHelper;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.tools.AllSuites;

/**
 * @since 2.2.0
 */
public class FacadeTest_Example1 extends TestCase
{
  protected boolean testHeader = true;
  protected boolean testComment = true;
  protected boolean testBody = true;
  protected boolean testContent = true;
  
  /**
   * Determines if contents with surrounding comments must be tested.
   */
  protected boolean testContentWithComments = true;
  
  /**
   * Determines if contents must be trimmed (leading and trailing whitespace removed)
   * when comparing contents.
   */
  protected boolean trimContents = false;  
  
  protected JCompilationUnit compilationUnit;
  protected JType mainType;

  public FacadeTest_Example1(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite ts = new TestSuite("FacadeTest_Example1");
    ts.addTest(new FacadeTest_Example1("testJDOM"));
    ts.addTest(new FacadeTest_Example1("testAST"));
    return ts;
  }
  
  protected void setTestActualText(boolean test)
  {
    testHeader = test;
    testComment = test;
    testBody = test;
    testContent = test;
    testContentWithComments = test;
  }
  
  protected File getJavaFile()
  {
    return new File(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/Example1.java").getAbsoluteFile();
  }
  
  @Override
  protected void setUp() throws Exception
  {
    File javaFile = getJavaFile();
    assertTrue(javaFile.getPath(), javaFile.isFile() && javaFile.canRead());
  }
  
  public void testJDOM() throws Exception
  {
    JDOMFacadeHelper facadeHelper = new JDOMFacadeHelper();
    facadeHelper.setForcedSourceCompatibility(true);
    readTest(facadeHelper, getJavaFile());
  }
  
  public void testAST() throws Exception
  {
    // do not test contents with surrounding comments since AST assigns comments differently
    testContentWithComments = false;
    trimContents = true;
    readTest(new ASTFacadeHelper(), getJavaFile());
  }

  @Override
  protected void tearDown() throws Exception
  {
    compilationUnit = null;
  }
  
  protected void readTest(FacadeHelper facadeHelper, File file) throws Exception
  {
    assertNotNull(facadeHelper);
    facadeHelper.reset();
    
    String content = TestUtil.readFile(file, false);
    compilationUnit = facadeHelper.createCompilationUnit("emf team", content);
    readTest(compilationUnit);
    readTest(facadeHelper.getPackage(compilationUnit));
    
    readTestImports(facadeHelper.getChildren(compilationUnit, JImport.class));
    readTestTypes(facadeHelper.getChildren(compilationUnit, JType.class));
    
    mainType = facadeHelper.getMainType(compilationUnit);
    readTestMainType();
    readTestNestedTypes(facadeHelper.getChildren(mainType, JType.class));
    readTestFields(facadeHelper.getChildren(mainType, JField.class));
    readTestMethods(facadeHelper.getChildren(mainType, JMethod.class));
    readTestInitializers(facadeHelper.getChildren(mainType, JInitializer.class));
  }
  
  protected void readTest(JCompilationUnit compilationUnit) throws Exception
  {
    assertNotNull(compilationUnit);
    assertNull(compilationUnit.getParent());
    assertContentEquals("Example1.java", compilationUnit.getName());
    assertContentEquals("Example1.java", compilationUnit.getQualifiedName());
    assertEquals(FacadeFlags.DEFAULT, compilationUnit.getFlags());
    
    assertFalse(compilationUnit.getChildren().isEmpty());
    assertEquals(9, compilationUnit.getChildren().size());
    int index = 0;
    testInstanceOf(compilationUnit.getChildren().get(index++), JPackage.class);
    testInstanceOf(compilationUnit.getChildren().get(index++), JImport.class);
    testInstanceOf(compilationUnit.getChildren().get(index++), JImport.class);
    testInstanceOf(compilationUnit.getChildren().get(index++), JImport.class);
    testInstanceOf(compilationUnit.getChildren().get(index++), JImport.class);
    testInstanceOf(compilationUnit.getChildren().get(index++), JImport.class);
    testInstanceOf(compilationUnit.getChildren().get(index++), JImport.class);
    testInstanceOf(compilationUnit.getChildren().get(index++), JType.class);
    testInstanceOf(compilationUnit.getChildren().get(index++), JType.class);
    
    StringBuffer expectedHeader = new StringBuffer();
    expectedHeader.append("/**");
    expectedHeader.append("\n").append(" * Copyright (c) 2004-2006 IBM Corporation and others.");
    expectedHeader.append("\n").append(" * All rights reserved.   This program and the accompanying materials");
    expectedHeader.append("\n").append(" * are made available under the terms of the Eclipse Public License v1.0");
    expectedHeader.append("\n").append(" * which accompanies this distribution, and is available at");
    expectedHeader.append("\n").append(" * http://www.eclipse.org/legal/epl-v10.html");
    expectedHeader.append("\n").append(" * ");
    expectedHeader.append("\n").append(" * Contributors: "); 
    expectedHeader.append("\n").append(" *   IBM - Initial API and implementation");
    expectedHeader.append("\n").append(" */");
    expectedHeader.append("\n").append("\n  // line comment1 ");
    expectedHeader.append("\n").append("\n/**");
    expectedHeader.append("\n").append(" * A javadoc");
    expectedHeader.append("\n").append(" */");
    expectedHeader.append("\n").append("\n  ");
    expectedHeader.append("\n");
    if (testHeader) assertContentEquals(expectedHeader.toString(), compilationUnit.getHeader());
  }
  
  protected void readTest(JPackage jPackage) throws Exception
  {
    assertNotNull(jPackage);
    assertEquals(compilationUnit, jPackage.getParent());
    assertContentEquals("org.eclipse.emf.test.tools.merger", jPackage.getName());
    assertContentEquals("org.eclipse.emf.test.tools.merger", jPackage.getQualifiedName());
    assertEquals(FacadeFlags.DEFAULT, jPackage.getFlags());

    if (testContent) assertContentEquals("package org.eclipse.emf.test.tools.merger;\n\n", jPackage.getContents());
  }  
  
  protected void readTestImports(List<?> imports) throws Exception
  {
    assertNotNull(imports);
    assertEquals(6, imports.size());    
    {
      JImport jImport = (JImport)imports.get(0);
      assertEquals(compilationUnit, jImport.getParent());
      assertContentEquals("java.util.Collections", jImport.getName());
      assertContentEquals("java.util.Collections", jImport.getQualifiedName());
      assertEquals(FacadeFlags.DEFAULT, jImport.getFlags());
  
      assertFalse(jImport.isOnDemand());
  
      if (testContent) assertContentEquals("import java.util.Collections;\n", jImport.getContents());
    }
    {
      JImport jImport = (JImport)imports.get(1);
      assertEquals(compilationUnit, jImport.getParent());
      assertContentEquals("java.util.List", jImport.getName());
      assertContentEquals("java.util.List", jImport.getQualifiedName());
      assertEquals(FacadeFlags.DEFAULT, jImport.getFlags());
  
      assertFalse(jImport.isOnDemand());
  
      if (testContent) assertContentEquals("import java.util.List;\n", jImport.getContents());
    }
    {
      JImport jImport = (JImport)imports.get(2);
      assertEquals(compilationUnit, jImport.getParent());
      assertContentEquals("java.util.Map", jImport.getName());
      assertContentEquals("java.util.Map", jImport.getQualifiedName());
      assertEquals(FacadeFlags.DEFAULT, jImport.getFlags());
  
      assertFalse(jImport.isOnDemand());
  
      if (testContent) assertContentEquals("import java.util.Map;\n\n", jImport.getContents());
    }
    {
      JImport jImport = (JImport)imports.get(3);
      assertEquals(compilationUnit, jImport.getParent());
      assertContentEquals("org.eclipse.emf.common.*", jImport.getName());
      assertContentEquals("org.eclipse.emf.common.*", jImport.getQualifiedName());
      assertEquals(FacadeFlags.DEFAULT, jImport.getFlags());
  
      assertTrue(jImport.isOnDemand());
  
      if (testContent) assertContentEquals("import org.eclipse.emf.common.*;\n", jImport.getContents());
    }
    {
      JImport jImport = (JImport)imports.get(4);
      assertEquals(compilationUnit, jImport.getParent());
      assertContentEquals("org.eclipse.emf.common.notify.Notification", jImport.getName());
      assertContentEquals("org.eclipse.emf.common.notify.Notification", jImport.getQualifiedName());
      assertEquals(FacadeFlags.DEFAULT, jImport.getFlags());
  
      assertFalse(jImport.isOnDemand());
  
      if (testContentWithComments) assertContentEquals("import org.eclipse.emf.common.notify.Notification;\n// This is importing the EObjectImpl\n", jImport.getContents());
    }
    {
      JImport jImport = (JImport)imports.get(5);
      assertEquals(compilationUnit, jImport.getParent());
      assertContentEquals("org.eclipse.emf.ecore.impl.EObjectImpl", jImport.getName());
      assertContentEquals("org.eclipse.emf.ecore.impl.EObjectImpl", jImport.getQualifiedName());
      assertEquals(FacadeFlags.DEFAULT, jImport.getFlags());
  
      assertFalse(jImport.isOnDemand());
  
      if (testContent) assertContentEquals("import org.eclipse.emf.ecore.impl.EObjectImpl;\n\n", jImport.getContents());
    }
  }

  protected void readTestTypes(List<?> types) throws Exception
  {
    assertNotNull(types);
    assertEquals(2, types.size());
    {
      JType type = (JType)types.get(0);
      assertEquals(compilationUnit, type.getParent());
      assertContentEquals("AnotherClass", type.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.AnotherClass", type.getQualifiedName());
      assertEquals(FacadeFlags.DEFAULT, type.getFlags());

      assertEquals(0, type.getTypeParameters().length);
      assertNull(type.getSuperclass());
      assertEquals(0, type.getSuperInterfaces().length);

      assertNull(type.getComment());
      
      StringBuffer expectedContents = new StringBuffer();
      expectedContents.append("class AnotherClass");
      expectedContents.append("\n").append("{");
      expectedContents.append("\n").append("  ");
      expectedContents.append("\n").append("}");
      expectedContents.append("\n\n");
      if (testContent) assertContentEquals(expectedContents.toString(), type.getContents());
    }
    {
      JType type = (JType)types.get(1);
      assertEquals(compilationUnit, type.getParent());
      assertContentEquals("Example1", type.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1", type.getQualifiedName());
      assertEquals(FacadeFlags.PUBLIC, type.getFlags());
      
      assertEquals(0, type.getTypeParameters().length);
      assertContentEquals("EObjectImpl", type.getSuperclass());
      assertEquals(0, type.getSuperInterfaces().length);

      StringBuffer expectedComment = new StringBuffer();
      expectedComment.append("/**");
      expectedComment.append("\n").append(" * This is an example of a fairly complete Java file.");
      expectedComment.append("\n").append(" * Its content is not really important");
      expectedComment.append("\n").append(" * ");
      expectedComment.append("\n").append(" * @author EMF team");
      expectedComment.append("\n").append(" * @generated");
      expectedComment.append("\n").append(" * @generated NOT");
      expectedComment.append("\n").append(" */");
      if (testComment) assertContentEquals(expectedComment.toString(), type.getComment());
            
      assertEquals(21, type.getChildren().size());
      int index = 0;
      testInstanceOf(type.getChildren().get(index++), JType.class);
      testInstanceOf(type.getChildren().get(index++), JInitializer.class);
      testInstanceOf(type.getChildren().get(index++), JField.class);
      testInstanceOf(type.getChildren().get(index++), JType.class);
      testInstanceOf(type.getChildren().get(index++), JField.class);
      testInstanceOf(type.getChildren().get(index++), JField.class);
      testInstanceOf(type.getChildren().get(index++), JField.class);
      testInstanceOf(type.getChildren().get(index++), JInitializer.class);
      testInstanceOf(type.getChildren().get(index++), JMethod.class);
      testInstanceOf(type.getChildren().get(index++), JMethod.class);
      testInstanceOf(type.getChildren().get(index++), JMethod.class);
      testInstanceOf(type.getChildren().get(index++), JMethod.class);
      testInstanceOf(type.getChildren().get(index++), JField.class);
      testInstanceOf(type.getChildren().get(index++), JMethod.class);
      testInstanceOf(type.getChildren().get(index++), JMethod.class);
      testInstanceOf(type.getChildren().get(index++), JMethod.class);
      testInstanceOf(type.getChildren().get(index++), JInitializer.class);
      testInstanceOf(type.getChildren().get(index++), JField.class);
      testInstanceOf(type.getChildren().get(index++), JField.class);
      testInstanceOf(type.getChildren().get(index++), JField.class);  
      testInstanceOf(type.getChildren().get(index++), JField.class);    
    }
  }
  
  protected void readTestMainType() throws Exception
  {
    assertNotNull(mainType);
    assertEquals(compilationUnit, mainType.getParent());
    assertEquals(compilationUnit.getChildren().get(8), mainType);
  }
  
  protected void readTestNestedTypes(List<?> types) throws Exception
  {
    assertNotNull(types);
    assertEquals(2, types.size());
    {
      JType type = (JType)types.get(0);
      assertEquals(mainType, type.getParent());
      assertContentEquals("InnerClass", type.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.InnerClass", type.getQualifiedName());
      assertEquals(FacadeFlags.PUBLIC | FacadeFlags.ABSTRACT, type.getFlags());

      assertEquals(0, type.getTypeParameters().length);
      assertNull(type.getSuperclass());
      assertEquals(2, type.getSuperInterfaces().length);
      assertContentEquals("Notification", type.getSuperInterfaces()[0]);
      assertContentEquals("org.eclipse.emf.common.notify.Notifier", type.getSuperInterfaces()[1]);

      assertNull(type.getComment());
      
      StringBuffer expectedContents = new StringBuffer();
      expectedContents.append((char)9).append("// A public inner class.  It is indented with TABs");
      expectedContents.append("\n").append((char)9).append("public abstract class InnerClass implements Notification, org.eclipse.emf.common.notify.Notifier ");
      expectedContents.append("\n").append((char)9).append("{");
      expectedContents.append("\n").append((char)9);
      expectedContents.append("\n").append((char)9).append("}");
      expectedContents.append("\n").append("  \n");
      if (testContentWithComments) assertContentEquals(expectedContents.toString(), type.getContents());
    }
    {
      JType type = (JType)types.get(1);
      assertEquals(mainType, type.getParent());
      assertContentEquals("InnerInterface", type.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.InnerInterface", type.getQualifiedName());
      assertEquals(FacadeFlags.STATIC | FacadeFlags.INTERFACE | FacadeFlags.PRIVATE, type.getFlags());

      assertEquals(0, type.getTypeParameters().length);
      assertNull(type.getSuperclass());
      assertEquals(1, type.getSuperInterfaces().length);
      assertContentEquals("Notification", type.getSuperInterfaces()[0]);

      assertNull(type.getComment());
      
      StringBuffer expectedContents = new StringBuffer();
      expectedContents.append("  /*");
      expectedContents.append("\n").append("   * A private static inner interface");
      expectedContents.append("\n").append("   */");
      expectedContents.append("\n").append("  private static interface InnerInterface extends Notification");
      expectedContents.append("\n").append("  {");
      expectedContents.append("\n").append("    ");
      expectedContents.append("\n").append("  }");
      expectedContents.append("\n").append("  \n");
      if (testContentWithComments) assertContentEquals(expectedContents.toString(), type.getContents());
    }
  }
      
  protected void readTestFields(List<?> fields) throws Exception
  {
    assertNotNull(fields);
    assertEquals(9, fields.size());
    {
      JField field = (JField)fields.get(0);
      assertEquals(mainType, field.getParent());
      assertContentEquals("STR_CONST", field.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.STR_CONST", field.getQualifiedName());
      assertEquals(FacadeFlags.PUBLIC | FacadeFlags.STATIC | FacadeFlags.FINAL, field.getFlags());

      assertContentEquals("String", field.getType());
      assertContentEquals(" \"something is ; different \\\"//; /*;*/\" /*inte;res;ting*/ + \" !!;;\"", field.getInitializer());
      
      StringBuffer expectedComment = new StringBuffer();
      expectedComment.append("  /**");
      expectedComment.append("\n").append("   * public String constant.");
      expectedComment.append("\n").append("   */");
      if (testComment) assertContentEquals(expectedComment.toString(), field.getComment());
      
      StringBuffer expectedContents = new StringBuffer();
      expectedContents.append(expectedComment);
      expectedContents.append("\n").append("  public static final String STR_CONST = \"something is ; different \\\"//; /*;*/\" /*inte;res;ting*/ + \" !!;;\" ;  // = \"original text\";");
      expectedContents.append("\n").append("    \n");
      if (testContentWithComments) assertContentEquals(expectedContents.toString(), field.getContents());
    }
    {
      JField field = (JField)fields.get(1);
      assertEquals(mainType, field.getParent());
      assertContentEquals("longStatic", field.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.longStatic", field.getQualifiedName());
      assertEquals(FacadeFlags.PROTECTED | FacadeFlags.STATIC, field.getFlags());

      assertContentEquals("long", field.getType());
      assertContentEquals("1l", field.getInitializer());
      
      StringBuffer expectedComment = new StringBuffer();
      expectedComment.append("  /**");
      expectedComment.append("\n").append("   * protected static long field.");
      expectedComment.append("\n").append("   * This is a multiline comment.");
      expectedComment.append("\n").append("   */");
      if (testComment) assertContentEquals(expectedComment.toString(), field.getComment());
      
      StringBuffer expectedContents = new StringBuffer();
      expectedContents.append(expectedComment);
      expectedContents.append("\n").append("  protected static long longStatic=1l; //A field");
      expectedContents.append("\n").append("  \n");
      if (testContentWithComments) assertContentEquals(expectedContents.toString(), field.getContents());
    }
    {
      JField field = (JField)fields.get(2);
      assertEquals(mainType, field.getParent());
      assertContentEquals("booleanInstance", field.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.booleanInstance", field.getQualifiedName());
      assertEquals(FacadeFlags.DEFAULT, field.getFlags());

      assertContentEquals("Boolean", field.getType());
      assertNull(field.getInitializer());

      assertNull(field.getComment());
      
      StringBuffer expectedContents = new StringBuffer();
      expectedContents.append("  /*");
      expectedContents.append("\n").append("   * package protected boolean field.");
      expectedContents.append("\n").append("   */");
      expectedContents.append("\n").append("  Boolean booleanInstance;");
      expectedContents.append("\n").append("  \n");
      if (testContentWithComments) assertContentEquals(expectedContents.toString(), field.getContents());
    }
    {
      JField field = (JField)fields.get(3);
      assertEquals(mainType, field.getParent());
      assertContentEquals("myEntry", field.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.myEntry", field.getQualifiedName());
      assertEquals(FacadeFlags.PRIVATE, field.getFlags());

      assertContentEquals("Map.Entry", field.getType());
      assertNull(field.getInitializer());
      
      assertNull(field.getComment());
      
      StringBuffer expectedContents = new StringBuffer();
      expectedContents.append("  private Map.Entry myEntry;");
      expectedContents.append("\n").append("      \n");
      if (testContent) assertContentEquals(expectedContents.toString(), field.getContents());
    }
    {
      JField field = (JField)fields.get(4);
      assertEquals(mainType, field.getParent());
      assertContentEquals("myMatrix", field.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.myMatrix", field.getQualifiedName());
      assertEquals(FacadeFlags.PRIVATE, field.getFlags());
      
      assertContentEquals("int[][]", field.getType());
      assertContentEquals("new int[4][5]", field.getInitializer());
      
      assertNull(field.getComment());
      
      StringBuffer expectedContents = new StringBuffer();
      expectedContents.append("  private int[][] myMatrix =new int[4][5];");
      expectedContents.append("\n").append("\n");
      if (testContent) assertContentEquals(expectedContents.toString(), field.getContents());
    }
    {
      JField field = (JField)fields.get(5);
      assertEquals(mainType, field.getParent());
      assertContentEquals("a", field.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.a", field.getQualifiedName());
      assertEquals(FacadeFlags.PUBLIC, field.getFlags());
      
      assertContentEquals("int", field.getType());
      assertContentEquals(" 1", field.getInitializer());
      
      assertNull(field.getComment());
    }     
    {
      JField field = (JField)fields.get(6);
      assertEquals(mainType, field.getParent());
      assertContentEquals("b", field.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.b", field.getQualifiedName());
      assertEquals(FacadeFlags.PUBLIC, field.getFlags());
      
      assertContentEquals("int", field.getType());
      assertContentEquals(" 2", field.getInitializer());
      
      assertNull(field.getComment());
    }     
    {
      JField field = (JField)fields.get(7);
      assertEquals(mainType, field.getParent());
      assertContentEquals("c", field.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.c", field.getQualifiedName());
      assertEquals(FacadeFlags.PUBLIC, field.getFlags());
      
      assertContentEquals("int", field.getType());
      assertContentEquals(" 3", field.getInitializer());
      
      assertNull(field.getComment());
    }    
    {
      JField field = (JField)fields.get(8);
      assertEquals(mainType, field.getParent());
      assertContentEquals("floatArray", field.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.floatArray", field.getQualifiedName());
      assertEquals(FacadeFlags.DEFAULT, field.getFlags());
      
      assertContentEquals("float[][][][]", field.getType());
      assertNull(field.getInitializer());
      
      assertNull(field.getComment());
      
      StringBuffer expectedContents = new StringBuffer();
      expectedContents.append("  float[][] floatArray[][];");
      expectedContents.append("\n");
      if (testContent) assertContentEquals(expectedContents.toString(), field.getContents());
    }    
  }

  protected void readTestMethods(List<?> methods) throws Exception
  {
    assertNotNull(methods);
    assertEquals(7, methods.size());
    {
      JMethod method = (JMethod)methods.get(0);
      assertEquals(mainType, method.getParent());
      assertTrue(method.isConstructor());
      assertNull(method.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1." + mainType.getName() + "()", method.getQualifiedName());
      assertEquals(FacadeFlags.PUBLIC, method.getFlags());

      assertNull(method.getReturnType());
      assertEquals(0, method.getTypeParameters().length);
      assertEquals(0, method.getParameterNames().length);
      assertEquals(0, method.getParameterTypes().length);
      assertEquals(0, method.getExceptions().length);
      
      StringBuffer expectedComment = new StringBuffer();
      expectedComment.append("  /**");
      expectedComment.append("\n").append("   * This is a contructor");
      expectedComment.append("\n").append("   */");
      if (testComment) assertContentEquals(expectedComment.toString(), method.getComment());
      
      StringBuffer expectedBody = new StringBuffer();
      expectedBody.append("\n").append("  {");
      expectedBody.append("\n").append("    super();");
      expectedBody.append("\n").append("  }");
      expectedBody.append("\n").append("  \n");
      if (testBody) assertContentEquals(expectedBody.toString(), method.getBody());
      
      StringBuffer expectedContents = new StringBuffer();
      expectedContents.append(expectedComment);
      expectedContents.append("\n").append("  public Example1()");
      expectedContents.append(expectedBody);      
      if (testContent) assertContentEquals(expectedContents.toString(), method.getContents());      
    }
    {
      JMethod method = (JMethod)methods.get(1);
      assertEquals(mainType, method.getParent());
      assertTrue(method.isConstructor());
      assertNull(method.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.Example1(String, boolean)", method.getQualifiedName());
      assertEquals(FacadeFlags.PRIVATE, method.getFlags());

      assertNull(method.getReturnType());
      assertEquals(0, method.getTypeParameters().length);
      assertTrue(Arrays.equals(new String[]{"aString","bol"}, method.getParameterNames()));
      assertTrue(Arrays.equals(new String[]{"String","boolean"}, method.getParameterTypes()));
      assertEquals(0, method.getExceptions().length);
      
      assertNull(method.getComment());

      StringBuffer expectedBody = new StringBuffer();
      expectedBody.append("\n").append("  {");
      expectedBody.append("\n").append("    super();");
      expectedBody.append("\n").append("  }");
      expectedBody.append("\n").append("\n");
      if (testBody) assertContentEquals(expectedBody.toString(), method.getBody());
      
      StringBuffer expectedContents = new StringBuffer();
      expectedContents.append("  private Example1(String aString, boolean bol)");
      expectedContents.append(expectedBody);      
      if (testContent) assertContentEquals(expectedContents.toString(), method.getContents());      
    }
    {
      JMethod method = (JMethod)methods.get(2);
      assertFalse(method.isConstructor());
      assertEquals(mainType, method.getParent());
      assertContentEquals("setBooleanInstance", method.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.setBooleanInstance(Boolean)", method.getQualifiedName());
      assertEquals(FacadeFlags.PUBLIC, method.getFlags());

      assertContentEquals("void", method.getReturnType());
      assertEquals(0, method.getTypeParameters().length);
      assertTrue(Arrays.equals(new String[]{"b"}, method.getParameterNames()));
      assertTrue(Arrays.equals(new String[]{"Boolean"}, method.getParameterTypes()));
      assertEquals(0, method.getExceptions().length);
      
      StringBuffer expectedComment = new StringBuffer();
      expectedComment.append("  /**");
      expectedComment.append("\n").append("   * Sets the boolean instance.");
      expectedComment.append("\n").append("   * @param b");
      expectedComment.append("\n").append("   * @generated");
      expectedComment.append("\n").append("   */");
      if (testComment) assertContentEquals(expectedComment.toString(), method.getComment());
      
      StringBuffer expectedBody = new StringBuffer();
      expectedBody.append("\n").append("  {");
      expectedBody.append("\n").append("    if (b != null)");
      expectedBody.append("\n").append("    {");
      expectedBody.append("\n").append("      booleanInstance = b;");
      expectedBody.append("\n").append("    }");
      expectedBody.append("\n").append("    else");
      expectedBody.append("\n").append("    {");
      expectedBody.append("\n").append("      booleanInstance = Boolean.FALSE;");
      expectedBody.append("\n").append("    }");
      expectedBody.append("\n").append("  }");
      expectedBody.append("\n").append("\n");
      if (testBody) assertContentEquals(expectedBody.toString(), method.getBody());
      
      StringBuffer expectedContents = new StringBuffer();
      expectedContents.append(expectedComment);
      expectedContents.append("\n").append("  public void setBooleanInstance(Boolean b)");
      expectedContents.append(expectedBody);      
      if (testContent) assertContentEquals(expectedContents.toString(), method.getContents());            
    }
    {
      JMethod method = (JMethod)methods.get(3);
      assertFalse(method.isConstructor());
      assertEquals(mainType, method.getParent());
      assertContentEquals("setBooleanInstance", method.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.setBooleanInstance(int)", method.getQualifiedName());
      assertEquals(FacadeFlags.DEFAULT, method.getFlags());

      assertContentEquals("void", method.getReturnType());
      assertEquals(0, method.getTypeParameters().length);
      assertTrue(Arrays.equals(new String[]{"a"}, method.getParameterNames()));
      assertTrue(Arrays.equals(new String[]{"int"}, method.getParameterTypes()));
      assertEquals(0, method.getExceptions().length);

      assertNull(method.getComment());

      StringBuffer expectedBody = new StringBuffer();
      expectedBody.append("\n").append("  {");
      expectedBody.append("\n").append("    setBooleanInstance(a > 0 ? Boolean.TRUE : Boolean.FALSE);");
      expectedBody.append("\n").append("  }");
      expectedBody.append("\n").append("  \n");
      if (testBody) assertContentEquals(expectedBody.toString(), method.getBody());
      
      StringBuffer expectedContents = new StringBuffer();
      expectedContents.append("  void setBooleanInstance(int a)");
      expectedContents.append(expectedBody);      
      if (testContent) assertContentEquals(expectedContents.toString(), method.getContents());            
    }
    {
      JMethod method = (JMethod)methods.get(4);
      assertFalse(method.isConstructor());
      assertEquals(mainType, method.getParent());
      assertContentEquals("getBooleanInstance", method.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.getBooleanInstance()", method.getQualifiedName());
      assertEquals(FacadeFlags.PUBLIC, method.getFlags());

      assertContentEquals("Boolean", method.getReturnType());
      assertEquals(0, method.getTypeParameters().length);
      assertEquals(0, method.getParameterNames().length);
      assertEquals(0, method.getParameterTypes().length);
      assertTrue(Arrays.equals(new String[]{"Exception"}, method.getExceptions()));

      StringBuffer expectedComment = new StringBuffer();
      expectedComment.append("  /**");
      expectedComment.append("\n").append("   * Gets the boolean instance.  Now I will ask you to see ");
      expectedComment.append("\n").append("   * something {@link EObjectImpl#eAdapters()}.  ");
      expectedComment.append("\n").append("   * @param b");
      expectedComment.append("\n").append("   * @generated NOT");
      expectedComment.append("\n").append("   */");
      if (testComment) assertContentEquals(expectedComment.toString(), method.getComment());      

      StringBuffer expectedBody = new StringBuffer();
      expectedBody.append("\n").append("  {");
      expectedBody.append("\n").append("    return booleanInstance == null ? Boolean.FALSE : booleanInstance;");
      expectedBody.append("\n").append("  }");
      expectedBody.append("\n").append("  \n");
      if (testBody) assertContentEquals(expectedBody.toString(), method.getBody());
      
      StringBuffer expectedContents = new StringBuffer();
      expectedContents.append(expectedComment);
      expectedContents.append("\n").append("  public Boolean getBooleanInstance() throws Exception");
      expectedContents.append(expectedBody);      
      if (testContent) assertContentEquals(expectedContents.toString(), method.getContents());            
    }
    {
      JMethod method = (JMethod)methods.get(5);
      assertFalse(method.isConstructor());
      assertEquals(mainType, method.getParent());
      assertContentEquals("aMethodWithComments", method.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.aMethodWithComments()", method.getQualifiedName());
      assertEquals(FacadeFlags.PROTECTED, method.getFlags());

      assertContentEquals("List", method.getReturnType());
      assertEquals(0, method.getTypeParameters().length);
      assertEquals(0, method.getParameterNames().length);
      assertEquals(0, method.getParameterTypes().length);      
      assertTrue(Arrays.equals(new String[]{"RuntimeException","IllegalAccessError", "java.lang.NullPointerException"}, method.getExceptions())); 

      assertNull(method.getComment());

      StringBuffer expectedBody = new StringBuffer();
      expectedBody.append("\n").append("  {");
      expectedBody.append("\n").append("    return Collections.EMPTY_LIST;");
      expectedBody.append("\n").append("  }");
      expectedBody.append("\n");
      if (testBody) assertContentEquals(expectedBody.toString(), method.getBody());
      
      StringBuffer expectedContents = new StringBuffer();
      expectedContents.append("  /*");
      expectedContents.append("\n").append("   * This method returns an empty list.");
      expectedContents.append("\n").append("   */");      
      expectedContents.append("\n").append("  protected List aMethodWithComments() throws RuntimeException, IllegalAccessError, java.lang.NullPointerException");
      expectedContents.append(expectedBody);      
      if (testContentWithComments) assertContentEquals(expectedContents.toString(), method.getContents());            
    }
    {
      JMethod method = (JMethod)methods.get(6);
      assertFalse(method.isConstructor());
      assertEquals(mainType, method.getParent());
      assertContentEquals("aMethodWithNoComments", method.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.aMethodWithNoComments(int[])", method.getQualifiedName());
      assertEquals(FacadeFlags.PRIVATE | FacadeFlags.STATIC, method.getFlags());

      assertContentEquals("long[][]", method.getReturnType());
      assertEquals(0, method.getTypeParameters().length);
      assertTrue(Arrays.equals(new String[]{"a"}, method.getParameterNames()));
      assertTrue(Arrays.equals(new String[]{"int[]"}, method.getParameterTypes()));
      assertEquals(0, method.getExceptions().length);

      assertNull(method.getComment());

      StringBuffer expectedBody = new StringBuffer();
      expectedBody.append("\n").append("  {");
      expectedBody.append("\n").append("     System.out.println(\"I don't do anything\");");
      expectedBody.append("\n").append("     return null;");
      expectedBody.append("\n").append("  }");
      expectedBody.append("\n").append("  \n");
      if (testBody) assertContentEquals(expectedBody.toString(), method.getBody());
      
      StringBuffer expectedContents = new StringBuffer();
      expectedContents.append("  // This is a simple comment");
      expectedContents.append("\n\n").append("  //This is another simple comment");
      expectedContents.append("\n").append("  private static long[][] aMethodWithNoComments(int[] a)");
      expectedContents.append(expectedBody);      
      if (testContentWithComments) assertContentEquals(expectedContents.toString(), method.getContents());            
    }    
  }
  
  protected void readTestInitializers(List<?> initializers) throws Exception
  {
    assertNotNull(initializers);
    assertEquals(3, initializers.size());
    {
      JInitializer initializer = (JInitializer)initializers.get(0);
      assertEquals(mainType, initializer.getParent());
      assertContentEquals(mainType.getName() + ".0", initializer.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.0", initializer.getQualifiedName());
      assertEquals(FacadeFlags.DEFAULT, initializer.getFlags());
      
      assertNull(initializer.getComment());
      
      StringBuffer expectedBody = new StringBuffer();
      expectedBody.append("{");
      expectedBody.append("\n").append((char)9).append("System.out.println(\"A initializer with Comments\");");
      expectedBody.append("\n").append((char)9).append("}");
      expectedBody.append("\n").append("  \n");
      if (testBody) assertContentEquals(expectedBody.toString(), initializer.getBody());
      
      StringBuffer expectedContent = new StringBuffer();
      expectedContent.append((char)9).append("// An initializer.  It is indented with TABs");
      expectedContent.append("\n").append((char)9).append(expectedBody);
      if (testContentWithComments) assertContentEquals(expectedContent.toString(), initializer.getContents());
    }
    {
      JInitializer initializer = (JInitializer)initializers.get(1);
      assertEquals(mainType, initializer.getParent());
      assertContentEquals(mainType.getName() + ".1", initializer.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.1", initializer.getQualifiedName());
      assertEquals(FacadeFlags.STATIC, initializer.getFlags());
      
      StringBuffer expectedComment = new StringBuffer();
      expectedComment.append("  /**");
      expectedComment.append("\n").append("   * An static initializer");
      expectedComment.append("\n").append("   */");
      if (testComment) assertContentEquals(expectedComment.toString(), initializer.getComment());

      StringBuffer expectedBody = new StringBuffer();
      expectedBody.append("{");
      expectedBody.append("\n").append("    System.out.println(\"A initializer with JavaDoc - line1\");");
      expectedBody.append("\n").append("    System.out.println(\"A initializer with JavaDoc - line2\");");
      expectedBody.append("\n").append("  }");
      expectedBody.append("\n").append("\n");
      if (testBody) assertContentEquals(expectedBody.toString(), initializer.getBody());

      StringBuffer expectedContent = new StringBuffer();
      expectedContent.append(expectedComment);
      expectedContent.append("\n").append("  static");
      expectedContent.append("\n  ").append(expectedBody);
      if (testContent) assertContentEquals(expectedContent.toString(), initializer.getContents());      
    }
    {
      JInitializer initializer = (JInitializer)initializers.get(2);
      assertEquals(mainType, initializer.getParent());
      assertContentEquals(mainType.getName() + ".2", initializer.getName());
      assertContentEquals("org.eclipse.emf.test.tools.merger.Example1.2", initializer.getQualifiedName());
      assertEquals(FacadeFlags.DEFAULT, initializer.getFlags());

      StringBuffer expectedComment = new StringBuffer();
      expectedComment.append("  /**");
      expectedComment.append("\n").append("   * Another initializer with 2 lines");
      expectedComment.append("\n").append("   * of javadoc.");
      expectedComment.append("\n").append("   */");
      if (testComment) assertContentEquals(expectedComment.toString(), initializer.getComment());

      StringBuffer expectedBody = new StringBuffer();
      expectedBody.append("{");
      expectedBody.append("\n").append("    System.out.println(\"Another initializer with JavaDoc\");");
      expectedBody.append("\n").append("  }  ");
      expectedBody.append("\n\n");
      if (testBody) assertContentEquals(expectedBody.toString(), initializer.getBody());

      StringBuffer expectedContent = new StringBuffer();
      expectedContent.append(expectedComment);
      expectedContent.append("\n  ").append(expectedBody);
      if (testContent) assertContentEquals(expectedContent.toString(), initializer.getContents());      
    }
  }
  
  protected void testInstanceOf(Object object, Class<?> cls)
  {
    assertTrue(object.toString(), cls.isInstance(object));
  }
  
  public void assertContentEquals(String string1, String string2)
  {
    if (trimContents)
    {
      Assert.assertEquals(string1.trim(), string2.trim());
    }
    else
    {
      Assert.assertEquals(string1, string2);
    }
  }
}
