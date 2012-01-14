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
package org.eclipse.emf.test.examples;

import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jdt.core.JavaCore;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.java.JClass;
import org.eclipse.emf.java.JCompilationUnit;
import org.eclipse.emf.java.JField;
import org.eclipse.emf.java.JInitializer;
import org.eclipse.emf.java.JMethod;
import org.eclipse.emf.java.JPackage;
import org.eclipse.emf.java.JVisibility;
import org.eclipse.emf.java.JavaPackage;
import org.eclipse.emf.java.util.JavaPackageResourceFactoryImpl;
import org.eclipse.emf.java.util.JavaPackageResourceImpl;
import org.eclipse.emf.java.util.JavaResourceFactoryImpl;
import org.eclipse.emf.java.util.JavaUtil;
import org.eclipse.emf.test.common.TestUtil;

/**
 * @author marcelop
 */
public class JavaTest extends TestCase
{
  protected JCompilationUnit compilationUnit;
  protected JClass mainType;
  
  private boolean testHeader = true;
  private boolean testComment = true;
  private boolean testBody = true;
  private boolean testContentWithComments = false;
  
  private boolean trimContents = true;
  
  /**
   * @param name
   */
  public JavaTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("JavaTest");
    ts.addTest(new JavaTest("testRead"));
    return ts;
  }
  
  @SuppressWarnings("unchecked")
  @Override
  protected void setUp() throws Exception
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      Hashtable<Object, Object> map = JavaCore.getOptions();
      map.put(JavaCore.COMPILER_SOURCE, "1.5");
      JavaCore.setOptions(map);
    }
    else
    {
      Map<Object, Object> options = new HashMap<Object, Object>();
      JavaCore.setComplianceOptions("1.5", options);
      JavaCore.getOptions().putAll(options);
    }
  }
  
  public void testRead() throws Exception
  {
    loadCompilationUnitAndMainType();
    assertNotNull(compilationUnit);

    readTest(compilationUnit);
    readTest(compilationUnit.getPackage());
    
    readTestImports(compilationUnit.getImports());
    readTestTypes(compilationUnit.getTypes());
    
    readTestMainType();
    readTestNestedTypes(mainType.getTypes());
    readTestFields(mainType.getFields());
    readTestMethods(mainType.getMethods());
    
    List<JInitializer> initializers = (List<JInitializer>)EcoreUtil.<JInitializer>getObjectsByType(mainType.getMembers(), JavaPackage.Literals.JINITIALIZER);
    readTestInitializers(initializers);
  }
  
  protected void loadCompilationUnitAndMainType() throws Exception
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("packages", new JavaPackageResourceFactoryImpl());
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("java", new JavaResourceFactoryImpl());

    JavaPackageResourceImpl javaPackageResource = (JavaPackageResourceImpl)resourceSet.getResource(JavaUtil.JAVA_PACKAGE_RESOURCE_URI, true);
    javaPackageResource.setClassLoader(getClass().getClassLoader());    
    assertFalse(javaPackageResource.getContents().isEmpty());

    File javaFile = new File(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/Example1.java");
    assertTrue(javaFile.isFile());

    Resource javaResource = resourceSet.getResource(URI.createFileURI(javaFile.getAbsolutePath()), true);
    assertFalse(javaResource.getContents().isEmpty());

    compilationUnit = (JCompilationUnit)javaResource.getContents().get(0);

    for (JClass cls : compilationUnit.getTypes())
    {
      if (cls.getVisibility() == JVisibility.PUBLIC_LITERAL)
      {
        mainType = cls;
        break;
      }
    }
    assertNotNull(mainType);
  }
  
  protected void readTest(JCompilationUnit compilationUnit) throws Exception
  {
    assertNotNull(compilationUnit);
    assertNull(compilationUnit.eContainer());
    assertContentEquals("Example1.java", compilationUnit.getName());
    assertContentEquals("Example1.java", compilationUnit.getQualifiedName());
    
    assertFalse(compilationUnit.eContents().isEmpty());
    assertEquals(2, compilationUnit.eContents().size());
    int index = 0;
    testInstanceOf(compilationUnit.eContents().get(index++), JavaPackage.Literals.JCLASS);
    testInstanceOf(compilationUnit.eContents().get(index++), JavaPackage.Literals.JCLASS);
    
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
    if (testHeader) assertContentEquals(expectedHeader.toString(), compilationUnit.getComment());
  }
  
  protected void readTest(JPackage jPackage) throws Exception
  {
    assertNotNull(jPackage);
    assertContentEquals("org.eclipse.emf.test.examples", jPackage.getName());
    assertContentEquals("org.eclipse.emf.test.examples", jPackage.getQualifiedName());
  }  
  
  protected void readTestImports(List<?> imports) throws Exception
  {
    assertNotNull(imports);
    assertEquals(6, imports.size());    
    {
      String jImport = (String)imports.get(0);
      assertContentEquals("java.util.Collections", jImport);
    }
    {
      String jImport = (String)imports.get(1);
      assertContentEquals("java.util.List", jImport);
    }
    {
      String jImport = (String)imports.get(2);
      assertContentEquals("java.util.Map", jImport);
    }
    {
      String jImport = (String)imports.get(3);
      assertContentEquals("org.eclipse.emf.common.*", jImport);
    }
    {
      String jImport = (String)imports.get(4);
      assertContentEquals("org.eclipse.emf.common.notify.Notification", jImport);
    }
    {
      String jImport = (String)imports.get(5);
      assertContentEquals("org.eclipse.emf.ecore.impl.EObjectImpl", jImport);
    }
  }

  protected void readTestTypes(List<?> types) throws Exception
  {
    assertNotNull(types);
    assertEquals(2, types.size());
    {
      JClass type = (JClass)types.get(0);
      assertEquals(compilationUnit, type.eContainer());
      assertContentEquals("AnotherClass", type.getName());
      assertContentEquals("org.eclipse.emf.test.examples.AnotherClass", type.getQualifiedName());
      assertEquals(JVisibility.PACKAGE_LITERAL, type.getVisibility());

      assertEquals(0, type.getSuperTypes().size());

      assertNull(type.getComment());
    }
    {
      JClass type = (JClass)types.get(1);
      assertEquals(compilationUnit, type.eContainer());
      assertContentEquals("Example1", type.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1", type.getQualifiedName());
      assertEquals(JVisibility.PUBLIC_LITERAL, type.getVisibility());
      
      assertEquals(1, type.getSuperTypes().size());
      assertEquals("EObjectImpl", type.getSuperTypes().get(0).getName());

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
            
      assertEquals(21, type.eContents().size());
      int index = 0;
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JCLASS);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JINITIALIZER);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JFIELD);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JCLASS);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JFIELD);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JFIELD);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JFIELD);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JINITIALIZER);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JMETHOD);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JMETHOD);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JMETHOD);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JMETHOD);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JFIELD);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JMETHOD);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JMETHOD);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JMETHOD);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JINITIALIZER);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JFIELD);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JFIELD);
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JFIELD);  
      testInstanceOf(type.eContents().get(index++), JavaPackage.Literals.JFIELD);    
    }
  }
  
  protected void readTestMainType() throws Exception
  {
    assertNotNull(mainType);
    assertEquals(compilationUnit, mainType.eContainer());
    assertEquals(compilationUnit.eContents().get(1), mainType);
  }
  
  protected void readTestNestedTypes(List<?> types) throws Exception
  {
    assertNotNull(types);
    assertEquals(2, types.size());
    {
      JClass type = (JClass)types.get(0);
      assertEquals(mainType, type.eContainer());
      assertContentEquals("InnerClass", type.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.InnerClass", type.getQualifiedName());
      assertTrue(type.isAbstract());
      assertEquals(JVisibility.PUBLIC_LITERAL, type.getVisibility());

      assertEquals(2, type.getSuperTypes().size());
      assertContentEquals("Notification", type.getSuperTypes().get(0).getName());
      assertContentEquals("org.eclipse.emf.common.notify.Notifier", type.getSuperTypes().get(1).getQualifiedName());

      assertNull(type.getComment());
    }
    {
      JClass type = (JClass)types.get(1);
      assertEquals(mainType, type.eContainer());
      assertContentEquals("InnerInterface", type.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.InnerInterface", type.getQualifiedName());
      assertTrue(type.isStatic());
      assertTrue(type.isInterface());
      assertEquals(JVisibility.PRIVATE_LITERAL, type.getVisibility());

      assertEquals(2, type.getSuperTypes().size());
      assertContentEquals("Notification", type.getSuperTypes().get(0).getName());
      assertContentEquals("List", type.getSuperTypes().get(1).getName());

      assertNull(type.getComment());
    }
  }
      
  protected void readTestFields(List<?> fields) throws Exception
  {
    assertNotNull(fields);
    assertEquals(9, fields.size());
    {
      JField field = (JField)fields.get(0);
      assertEquals(mainType, field.eContainer());
      assertContentEquals("STR_CONST", field.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.STR_CONST", field.getQualifiedName());
      assertTrue(field.isStatic());
      assertTrue(field.isFinal());
      assertEquals(JVisibility.PUBLIC_LITERAL, field.getVisibility());

      assertContentEquals("String", field.getType().getName());
      assertContentEquals(" \"something is ; different \\\"//; /*;*/\" /*inte;res;ting*/ + \" !!;;\"", field.getInitializer());
      
      StringBuffer expectedComment = new StringBuffer();
      expectedComment.append("  /**");
      expectedComment.append("\n").append("   * public String constant.");
      expectedComment.append("\n").append("   */");
      if (testComment) assertContentEquals(expectedComment.toString(), field.getComment());
    }
    {
      JField field = (JField)fields.get(1);
      assertEquals(mainType, field.eContainer());
      assertContentEquals("longStatic", field.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.longStatic", field.getQualifiedName());
      assertTrue(field.isStatic());
      assertEquals(JVisibility.PROTECTED_LITERAL, field.getVisibility());

      assertContentEquals("long", field.getType().getName());
      assertContentEquals("1l", field.getInitializer());
      
      StringBuffer expectedComment = new StringBuffer();
      expectedComment.append("  /**");
      expectedComment.append("\n").append("   * protected static long field.");
      expectedComment.append("\n").append("   * This is a multiline comment.");
      expectedComment.append("\n").append("   */");
      if (testComment) assertContentEquals(expectedComment.toString(), field.getComment());
    }
    {
      JField field = (JField)fields.get(2);
      assertEquals(mainType, field.eContainer());
      assertContentEquals("booleanInstance", field.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.booleanInstance", field.getQualifiedName());
      assertEquals(JVisibility.PACKAGE_LITERAL, field.getVisibility());

      assertContentEquals("Boolean", field.getType().getName());
      assertNull(field.getInitializer());

      assertNull(field.getComment());
    }
    {
      JField field = (JField)fields.get(3);
      assertEquals(mainType, field.eContainer());
      assertContentEquals("myEntry", field.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.myEntry", field.getQualifiedName());
      assertEquals(JVisibility.PRIVATE_LITERAL, field.getVisibility());

      assertContentEquals("java.util.Map.Entry", field.getType().getQualifiedName());
      assertNull(field.getInitializer());
      
      assertNull(field.getComment());
    }
    {
      JField field = (JField)fields.get(4);
      assertEquals(mainType, field.eContainer());
      assertContentEquals("myMatrix", field.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.myMatrix", field.getQualifiedName());
      assertEquals(JVisibility.PRIVATE_LITERAL, field.getVisibility());
      
      assertContentEquals("int[][]", field.getType().getName());
      assertContentEquals("new int[4][5]", field.getInitializer());
      
      assertNull(field.getComment());
    }
    {
      JField field = (JField)fields.get(5);
      assertEquals(mainType, field.eContainer());
      assertContentEquals("a", field.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.a", field.getQualifiedName());
      assertEquals(JVisibility.PUBLIC_LITERAL, field.getVisibility());
      
      assertContentEquals("int", field.getType().getName());
      assertContentEquals(" 1", field.getInitializer());
      
      assertNull(field.getComment());
    }     
    {
      JField field = (JField)fields.get(6);
      assertEquals(mainType, field.eContainer());
      assertContentEquals("b", field.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.b", field.getQualifiedName());
      assertEquals(JVisibility.PUBLIC_LITERAL, field.getVisibility());
      
      assertContentEquals("int", field.getType().getName());
      assertContentEquals(" 2", field.getInitializer());
      
      assertNull(field.getComment());
    }     
    {
      JField field = (JField)fields.get(7);
      assertEquals(mainType, field.eContainer());
      assertContentEquals("c", field.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.c", field.getQualifiedName());
      assertEquals(JVisibility.PUBLIC_LITERAL, field.getVisibility());
      
      assertContentEquals("int", field.getType().getName());
      assertContentEquals(" 3", field.getInitializer());
      
      assertNull(field.getComment());
    }    
    {
      JField field = (JField)fields.get(8);
      assertEquals(mainType, field.eContainer());
      assertContentEquals("floatArray", field.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.floatArray", field.getQualifiedName());
      assertEquals(JVisibility.PACKAGE_LITERAL, field.getVisibility());
      
      assertContentEquals("float[][][][]", field.getType().getName());
      assertNull(field.getInitializer());
      
      assertNull(field.getComment());
    }    
  }

  protected void readTestMethods(List<?> methods) throws Exception
  {
    assertNotNull(methods);
    assertEquals(7, methods.size());
    {
      JMethod method = (JMethod)methods.get(0);
      assertEquals(mainType, method.eContainer());
      assertTrue(method.isConstructor());
      assertEquals("Example1", method.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1." + mainType.getName(), method.getQualifiedName());
      assertEquals(JVisibility.PUBLIC_LITERAL, method.getVisibility());

      assertNull(method.getReturnType());
      assertEquals(0, method.getParameters().size());
      assertEquals(0, method.getExceptions().size());
      
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
    }
    {
      JMethod method = (JMethod)methods.get(1);
      assertEquals(mainType, method.eContainer());
      assertTrue(method.isConstructor());
      assertEquals("Example1", method.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.Example1", method.getQualifiedName());
      assertEquals(JVisibility.PRIVATE_LITERAL, method.getVisibility());

      assertNull(method.getReturnType());
      assertEquals(2, method.getParameters().size());
      assertEquals("aString", method.getParameters().get(0).getName());
      assertEquals("String", method.getParameters().get(0).getType().getName());
      assertEquals("bol", method.getParameters().get(1).getName());
      assertEquals("boolean", method.getParameters().get(1).getType().getName());
      assertEquals(0, method.getExceptions().size());
      
      assertNull(method.getComment());

      StringBuffer expectedBody = new StringBuffer();
      expectedBody.append("\n").append("  {");
      expectedBody.append("\n").append("    super();");
      expectedBody.append("\n").append("  }");
      expectedBody.append("\n").append("\n");
      if (testBody) assertContentEquals(expectedBody.toString(), method.getBody());
    }
    {
      JMethod method = (JMethod)methods.get(2);
      assertFalse(method.isConstructor());
      assertEquals(mainType, method.eContainer());
      assertContentEquals("setBooleanInstance", method.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.setBooleanInstance", method.getQualifiedName());
      assertEquals(JVisibility.PUBLIC_LITERAL, method.getVisibility());

      assertContentEquals("void", method.getReturnType().getName());
      assertEquals(1, method.getParameters().size());
      assertEquals("b", method.getParameters().get(0).getName());
      assertEquals("Boolean", method.getParameters().get(0).getType().getName());
      assertEquals(0, method.getExceptions().size());
      
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
    }
    {
      JMethod method = (JMethod)methods.get(3);
      assertFalse(method.isConstructor());
      assertEquals(mainType, method.eContainer());
      assertContentEquals("setBooleanInstance", method.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.setBooleanInstance", method.getQualifiedName());
      assertEquals(JVisibility.PACKAGE_LITERAL, method.getVisibility());

      assertContentEquals("void", method.getReturnType().getName());
      assertEquals(1, method.getParameters().size());
      assertEquals("a", method.getParameters().get(0).getName());
      assertEquals("int", method.getParameters().get(0).getType().getName());
      assertEquals(0, method.getExceptions().size());

      assertNull(method.getComment());

      StringBuffer expectedBody = new StringBuffer();
      expectedBody.append("\n").append("  {");
      expectedBody.append("\n").append("    setBooleanInstance(a > 0 ? Boolean.TRUE : Boolean.FALSE);");
      expectedBody.append("\n").append("  }");
      expectedBody.append("\n").append("  \n");
      if (testBody) assertContentEquals(expectedBody.toString(), method.getBody());
    }
    {
      JMethod method = (JMethod)methods.get(4);
      assertFalse(method.isConstructor());
      assertEquals(mainType, method.eContainer());
      assertContentEquals("getBooleanInstance", method.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.getBooleanInstance", method.getQualifiedName());
      assertEquals(JVisibility.PUBLIC_LITERAL, method.getVisibility());

      assertContentEquals("Boolean", method.getReturnType().getName());
      assertEquals(1, method.getParameters().size());
      assertEquals("Class", method.getParameters().get(0).getType().getName());
      assertEquals("aClass", method.getParameters().get(0).getName());
      assertEquals(1, method.getExceptions().size());
      assertEquals("Exception", method.getExceptions().get(0).getName());

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
    }
    {
      JMethod method = (JMethod)methods.get(5);
      assertFalse(method.isConstructor());
      assertEquals(mainType, method.eContainer());
      assertContentEquals("aMethodWithComments", method.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.aMethodWithComments", method.getQualifiedName());
      assertEquals(JVisibility.PROTECTED_LITERAL, method.getVisibility());

      assertContentEquals("List", method.getReturnType().getName());
      assertEquals(0, method.getParameters().size());
      assertEquals(3, method.getExceptions().size());
      assertEquals("RuntimeException", method.getExceptions().get(0).getName());
      assertEquals("IllegalAccessError", method.getExceptions().get(1).getName());
      assertEquals("java.lang.NullPointerException", method.getExceptions().get(2).getQualifiedName());

      assertNull(method.getComment());

      StringBuffer expectedBody = new StringBuffer();
      expectedBody.append("\n").append("  {");
      expectedBody.append("\n").append("    return Collections.EMPTY_LIST;");
      expectedBody.append("\n").append("  }");
      expectedBody.append("\n");
      if (testBody) assertContentEquals(expectedBody.toString(), method.getBody());
    }
    {
      JMethod method = (JMethod)methods.get(6);
      assertFalse(method.isConstructor());
      assertEquals(mainType, method.eContainer());
      assertContentEquals("aMethodWithNoComments", method.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.aMethodWithNoComments", method.getQualifiedName());
      assertTrue(method.isStatic());
      assertEquals(JVisibility.PRIVATE_LITERAL, method.getVisibility());

      assertContentEquals("long[][]", method.getReturnType().getName());
      assertEquals(1, method.getParameters().size());
      assertEquals("a", method.getParameters().get(0).getName());
      assertEquals("int[]", method.getParameters().get(0).getType().getName());
      assertEquals(0, method.getExceptions().size());

      assertNull(method.getComment());

      StringBuffer expectedBody = new StringBuffer();
      expectedBody.append("\n").append("  {");
      expectedBody.append("\n").append("     System.out.println(\"I don't do anything\");");
      expectedBody.append("\n").append("     return null;");
      expectedBody.append("\n").append("  }");
      expectedBody.append("\n").append("  \n");
      if (testBody) assertContentEquals(expectedBody.toString(), method.getBody());
    }    
  }
  
  protected void readTestInitializers(List<?> initializers) throws Exception
  {
    assertNotNull(initializers);
    assertEquals(3, initializers.size());
    {
      JInitializer initializer = (JInitializer)initializers.get(0);
      assertEquals(mainType, initializer.eContainer());
      assertContentEquals(mainType.getName() + ".0", initializer.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.0", initializer.getQualifiedName());
      assertEquals(JVisibility.PACKAGE_LITERAL, initializer.getVisibility());
      
      assertNull(initializer.getComment());
      
      StringBuffer expectedBody = new StringBuffer();
      expectedBody.append("{");
      expectedBody.append("\n").append((char)9).append("System.out.println(\"A initializer with Comments\");");
      expectedBody.append("\n").append((char)9).append("}");
      if (testBody) assertContentEquals(expectedBody.toString(), initializer.getBody());
      
      StringBuffer expectedContent = new StringBuffer();
      expectedContent.append((char)9).append("// An initializer.  It is indented with TABs");
      expectedContent.append("\n").append((char)9).append(expectedBody);
      if (testContentWithComments) assertContentEquals(expectedContent.toString(), initializer.getBody());
    }
    {
      JInitializer initializer = (JInitializer)initializers.get(1);
      assertEquals(mainType, initializer.eContainer());
      assertContentEquals(mainType.getName() + ".1", initializer.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.1", initializer.getQualifiedName());
      assertTrue(initializer.isStatic());
      
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
      if (testBody) assertContentEquals(expectedBody.toString(), initializer.getBody());
    }
    {
      JInitializer initializer = (JInitializer)initializers.get(2);
      assertEquals(mainType, initializer.eContainer());
      assertContentEquals(mainType.getName() + ".2", initializer.getName());
      assertContentEquals("org.eclipse.emf.test.examples.Example1.2", initializer.getQualifiedName());
      assertEquals(JVisibility.PACKAGE_LITERAL, initializer.getVisibility());

      StringBuffer expectedComment = new StringBuffer();
      expectedComment.append("  /**");
      expectedComment.append("\n").append("   * Another initializer with 2 lines");
      expectedComment.append("\n").append("   * of javadoc.");
      expectedComment.append("\n").append("   */\n");
      if (testComment) assertContentEquals(expectedComment.toString(), initializer.getComment());
      
      StringBuffer expectedBody = new StringBuffer();
      expectedBody.append("{");
      expectedBody.append("\n").append("    System.out.println(\"Another initializer with JavaDoc\");");
      expectedBody.append("\n").append("  }");
      if (testBody) assertContentEquals(expectedBody.toString(), initializer.getBody());
    }
  }
  
  protected void testInstanceOf(Object object, EClass cls)
  {
    assertTrue(object.toString(), cls.isInstance(object));
  }

  protected void testInstanceOf(Object object, Class<?> cls)
  {
    assertTrue(object.toString(), cls.isInstance(object));
  }
  
  public void assertContentEquals(String string1, String string2)
  {
    if (string2 != null)
    {
      string2 = string2.replaceAll("\r\n", "\n");
      string2 = string2.replace('\r', '\n');
      
      if (trimContents)
      {
        Assert.assertEquals(string1.trim(), string2.trim());
        return;
      }
    }
    Assert.assertEquals(string1, string2);
  }
  
  public static void main(String arguments[])
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("packages", new JavaPackageResourceFactoryImpl());

    JavaPackageResourceImpl javaPackageResource = (JavaPackageResourceImpl)resourceSet.getResource(JavaUtil.JAVA_PACKAGE_RESOURCE_URI, true);
    javaPackageResource.setClassLoader(JavaTest.class.getClassLoader());

    JClass thisClass = 
      (JClass)resourceSet.getEObject
        (JavaUtil.JAVA_PACKAGE_RESOURCE_URI.appendFragment("/org.eclipse.emf.test.examples/JavaTest"), true);

    System.out.println("All Methods of " + thisClass.getQualifiedName());
    for (JMethod jMethod : thisClass.getAllMethods())
    {
      System.out.println("  " + jMethod.getQualifiedName());
    }

    System.exit(1);
  }  
}
