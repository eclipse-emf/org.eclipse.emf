/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: ASTTest.java,v 1.4 2004/11/26 11:32:31 marcelop Exp $
 */
package org.eclipse.emf.test.tools.merger;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.LineComment;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TextElement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import org.eclipse.emf.test.tools.EMFTestToolsPlugin;

/*
 * MP:
 * I know we are not responsible for the AST code, but since we had to study it, why not
 * add here a test with the APIs we will be using ;-)
 */
public class ASTTest extends TestCase
{
  private static final File CLASS_FILE = 
     new File(EMFTestToolsPlugin.getPluginDirectory() + "/data/Example.java").getAbsoluteFile();
  
  /**
   * @param name
   */
  public ASTTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("ASTTest");
    ts.addTest(new ASTTest("testVisitor"));
    ts.addTest(new ASTTest("testRead"));
    return ts;
  }
  
  protected void setUp() throws Exception
  {
    assertTrue("Source code is not available - " + CLASS_FILE.getAbsolutePath(), CLASS_FILE.isFile());
  }
  
  public void testRead()
  {
    String content = EMFTestToolsPlugin.readFile(CLASS_FILE);
    
    ASTParser astParser = ASTParser.newParser(AST.JLS3);
    astParser.setSource(content.toCharArray());
    CompilationUnit compilationUnit = (CompilationUnit)astParser.createAST(null);
    
    for (Iterator i = compilationUnit.getCommentList().iterator(); i.hasNext();)
    {
      Comment comment = (Comment)i.next();
      System.out.println(comment);
    }
    
    //** Package
    PackageDeclaration packageDeclaration = compilationUnit.getPackage();
    assertNotNull(packageDeclaration);
    assertTrue(packageDeclaration.getName().isQualifiedName());
    assertEquals("org.eclipse.emf.test.tools.merger", packageDeclaration.getName().getFullyQualifiedName());
    
    //** Imports
    List importDeclarations = compilationUnit.imports();
    assertEquals(5, importDeclarations.size());
    assertEquals("java.util.Collections", ((ImportDeclaration)importDeclarations.get(0)).getName().getFullyQualifiedName());
    assertEquals("java.util.List", ((ImportDeclaration)importDeclarations.get(1)).getName().toString());
    assertEquals("java.util.Map", ((ImportDeclaration)importDeclarations.get(2)).getName().toString());
    assertEquals("org.eclipse.emf.common.notify.Notification", ((ImportDeclaration)importDeclarations.get(3)).getName().getFullyQualifiedName());
    assertEquals("org.eclipse.emf.ecore.impl.EObjectImpl", ((ImportDeclaration)importDeclarations.get(4)).getName().getFullyQualifiedName());
    
    //** Types
    List typeDeclarations = (List)compilationUnit.types();
    assertEquals(2, typeDeclarations.size());
    
    //** Class Example
    TypeDeclaration exampleClass = (TypeDeclaration)typeDeclarations.get(1);
    assertEquals("Example", exampleClass.getName().getFullyQualifiedName());
    assertFalse(exampleClass.isInterface());
    //Javadoc
    {
      Javadoc typeJavadoc = exampleClass.getJavadoc();
      assertEquals(4, typeJavadoc.tags().size());
      TagElement[] tagElements = (TagElement[])typeJavadoc.tags().toArray(new TagElement [typeJavadoc.tags().size()]);
      //Tag[0]: " This is an example to be parsed by the ASTTests.\n Not really important"
      assertNull(tagElements[0].getTagName());
      assertEquals(2, tagElements[0].fragments().size());
      assertEquals("This is an example of a fairly complete Java file.", ((TextElement)tagElements[0].fragments().get(0)).getText());
      assertEquals("Its content is not really important", ((TextElement)tagElements[0].fragments().get(1)).getText());
      //Tag[1]: "@author EMF team"
      assertEquals("@author", tagElements[1].getTagName());
      assertEquals(1, tagElements[1].fragments().size());
      assertEquals(" EMF team", ((TextElement)tagElements[1].fragments().get(0)).getText());
      //Tag[2]: "@generated"
      assertEquals("@generated", tagElements[2].getTagName());
      assertTrue(tagElements[2].fragments().isEmpty());
      //Tag[3]: "@generated NOT"
      assertEquals("@generated", tagElements[3].getTagName());
      assertEquals(1, tagElements[3].fragments().size());
      assertEquals(" NOT", ((TextElement)tagElements[3].fragments().get(0)).getText());
    }
    //Super Class
    assertTrue(exampleClass.getSuperclassType().isSimpleType());
    assertEquals("EObjectImpl", ((SimpleType)exampleClass.getSuperclassType()).getName().getFullyQualifiedName());
    //Interfaces
    assertTrue(exampleClass.superInterfaceTypes().isEmpty());
    //Modifiers
    assertEquals(Modifier.PUBLIC, exampleClass.getModifiers());
    
    //** Content of the Example class
    assertEquals(12, exampleClass.bodyDeclarations().size());
    assertEquals(2, exampleClass.getTypes().length);
    assertEquals(5, exampleClass.getFields().length);
    assertEquals(5, exampleClass.getMethods().length);
    
    //** Inner Class
    TypeDeclaration innerClass = exampleClass.getTypes()[0];
    assertFalse(innerClass.isInterface());
    assertTrue(innerClass.bodyDeclarations().isEmpty());
    assertEquals(Modifier.PUBLIC | Modifier.ABSTRACT, innerClass.getModifiers());
    assertNull(innerClass.getSuperclassType());
    assertEquals(2, innerClass.superInterfaceTypes().size());
    assertTrue(((Type)innerClass.superInterfaceTypes().get(0)).isSimpleType());
    assertEquals("Notification", ((SimpleType)innerClass.superInterfaceTypes().get(0)).getName().getFullyQualifiedName());
    assertTrue(((Type)innerClass.superInterfaceTypes().get(1)).isSimpleType());
    assertEquals("org.eclipse.emf.common.notify.Notifier", ((SimpleType)innerClass.superInterfaceTypes().get(1)).getName().getFullyQualifiedName());
    assertNull(innerClass.getJavadoc());

    //** Inner Interface
    TypeDeclaration innerInterface = exampleClass.getTypes()[1];
    assertTrue(innerInterface.isInterface());
    assertTrue(innerInterface.bodyDeclarations().isEmpty());
    assertEquals(Modifier.PRIVATE | Modifier.STATIC, innerInterface.getModifiers());
    assertNull(innerInterface.getSuperclassType());
    assertEquals(1, innerInterface.superInterfaceTypes().size());
    assertTrue(((Type)innerInterface.superInterfaceTypes().get(0)).isSimpleType());
    assertEquals("Notification", ((SimpleType)innerInterface.superInterfaceTypes().get(0)).getName().getFullyQualifiedName());
    assertNull(innerClass.getJavadoc());
    
    //** Fields
    FieldDeclaration[] fieldDeclarations = exampleClass.getFields();
    //fieldDeclarations[0]: public static final String STR_CONST = "something"
    {
      Javadoc javadoc = fieldDeclarations[0].getJavadoc();
      assertEquals(1, javadoc.tags().size());
      assertEquals(1, ((TagElement)javadoc.tags().get(0)).fragments().size());
      assertEquals("public String constant.", ((TextElement)((TagElement)javadoc.tags().get(0)).fragments().get(0)).getText());
      //
      assertEquals(Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL, fieldDeclarations[0].getModifiers());
      //
      assertTrue(fieldDeclarations[0].getType().isSimpleType());
      assertEquals("String", ((SimpleType)fieldDeclarations[0].getType()).getName().getFullyQualifiedName());
      //
      assertEquals(1, fieldDeclarations[0].fragments().size());
      VariableDeclarationFragment[] variableDeclarationFragments = (VariableDeclarationFragment[])fieldDeclarations[0].fragments().toArray(new VariableDeclarationFragment[fieldDeclarations[0].fragments().size()]);
      assertEquals(0, variableDeclarationFragments[0].getExtraDimensions());
      assertEquals("STR_CONST", variableDeclarationFragments[0].getName().getFullyQualifiedName());
      //
      assertNotNull(variableDeclarationFragments[0].getInitializer());
      assertTrue(variableDeclarationFragments[0].getInitializer() instanceof StringLiteral);
      assertEquals("something", ((StringLiteral)variableDeclarationFragments[0].getInitializer()).getLiteralValue());
    }
    //fieldDeclarations[1]: protected static long longStatic = 1l
    {
      Javadoc javadoc = fieldDeclarations[1].getJavadoc();
      assertEquals(1, javadoc.tags().size());
      assertEquals(2, ((TagElement)javadoc.tags().get(0)).fragments().size());
      assertEquals("protected static long field.", ((TextElement)((TagElement)javadoc.tags().get(0)).fragments().get(0)).getText());
      assertEquals("This is a multiline comment.", ((TextElement)((TagElement)javadoc.tags().get(0)).fragments().get(1)).getText());
      //
      assertEquals(Modifier.PROTECTED | Modifier.STATIC, fieldDeclarations[1].getModifiers());
      //
      assertTrue(fieldDeclarations[1].getType().isPrimitiveType());
      assertEquals(PrimitiveType.LONG, ((PrimitiveType)fieldDeclarations[1].getType()).getPrimitiveTypeCode());
      //
      assertEquals(1, fieldDeclarations[1].fragments().size());
      VariableDeclarationFragment[] variableDeclarationFragments = (VariableDeclarationFragment[])fieldDeclarations[1].fragments().toArray(new VariableDeclarationFragment[fieldDeclarations[1].fragments().size()]);
      assertEquals(0, variableDeclarationFragments[0].getExtraDimensions());
      assertEquals("longStatic", variableDeclarationFragments[0].getName().getFullyQualifiedName());
      //
      assertNotNull(variableDeclarationFragments[0].getInitializer());
      assertTrue(variableDeclarationFragments[0].getInitializer() instanceof NumberLiteral);
      assertEquals("1l", ((NumberLiteral)variableDeclarationFragments[0].getInitializer()).getToken());
    }
    //fieldDeclarations[2]: Boolean booleanInstance
    {
      assertNull(fieldDeclarations[2].getJavadoc());
      //
      assertEquals(0, fieldDeclarations[2].getModifiers());
      //
      assertTrue(fieldDeclarations[2].getType().isSimpleType());
      assertEquals("Boolean", ((SimpleType)fieldDeclarations[2].getType()).getName().getFullyQualifiedName());
      //
      assertEquals(1, fieldDeclarations[2].fragments().size());
      VariableDeclarationFragment[] variableDeclarationFragments = (VariableDeclarationFragment[])fieldDeclarations[2].fragments().toArray(new VariableDeclarationFragment[fieldDeclarations[2].fragments().size()]);
      assertEquals(0, variableDeclarationFragments[0].getExtraDimensions());
      assertEquals("booleanInstance", variableDeclarationFragments[0].getName().getFullyQualifiedName());
      //
      assertNull(variableDeclarationFragments[0].getInitializer());
    }
    //fieldDeclarations[3]: private Map.Entry myEntry
    {
      assertNull(fieldDeclarations[3].getJavadoc());
      //
      assertEquals(Modifier.PRIVATE, fieldDeclarations[3].getModifiers());
      //
      assertTrue(fieldDeclarations[3].getType().isSimpleType());
      assertEquals("Map.Entry", ((SimpleType)fieldDeclarations[3].getType()).getName().getFullyQualifiedName());
      //
      assertEquals(1, fieldDeclarations[3].fragments().size());
      VariableDeclarationFragment[] variableDeclarationFragments = (VariableDeclarationFragment[])fieldDeclarations[3].fragments().toArray(new VariableDeclarationFragment[fieldDeclarations[3].fragments().size()]);
      assertEquals(0, variableDeclarationFragments[0].getExtraDimensions());
      assertEquals("myEntry", variableDeclarationFragments[0].getName().getFullyQualifiedName());
      //
      assertNull(variableDeclarationFragments[0].getInitializer());
    }
    //fieldDeclarations[4]: private int[][] myMatrix = new int[4][5]
    {
      assertNull(fieldDeclarations[4].getJavadoc());
      //
      assertEquals(Modifier.PRIVATE, fieldDeclarations[4].getModifiers());
      //
      assertTrue(fieldDeclarations[4].getType().isArrayType());
      assertEquals(2, ((ArrayType)fieldDeclarations[4].getType()).getDimensions());
      assertTrue(((ArrayType)fieldDeclarations[4].getType()).getElementType().isPrimitiveType());
      assertEquals(PrimitiveType.INT, ((PrimitiveType)((ArrayType)fieldDeclarations[4].getType()).getElementType()).getPrimitiveTypeCode());
      //
      assertEquals(1, fieldDeclarations[4].fragments().size());
      VariableDeclarationFragment[] variableDeclarationFragments = (VariableDeclarationFragment[])fieldDeclarations[4].fragments().toArray(new VariableDeclarationFragment[fieldDeclarations[4].fragments().size()]);
      assertEquals(0, variableDeclarationFragments[0].getExtraDimensions());
      assertEquals("myMatrix", variableDeclarationFragments[0].getName().getFullyQualifiedName());
      //
      assertNotNull(variableDeclarationFragments[0].getInitializer());
      assertTrue(variableDeclarationFragments[0].getInitializer() instanceof ArrayCreation);
      ArrayCreation arrayCreation = (ArrayCreation)variableDeclarationFragments[0].getInitializer();
      assertEquals(2, arrayCreation.dimensions().size());
      assertEquals("4", ((NumberLiteral)arrayCreation.dimensions().get(0)).getToken());
      assertEquals("5", ((NumberLiteral)arrayCreation.dimensions().get(1)).getToken());
    }
    
    //** Methods
    MethodDeclaration[] methodDeclarations = exampleClass.getMethods();
    //methodDeclarations[0]: public Example()
    {
      Javadoc javadoc = methodDeclarations[0].getJavadoc();
      assertEquals(1, javadoc.tags().size());
      assertEquals(1, ((TagElement)javadoc.tags().get(0)).fragments().size());
      assertEquals("This is a contructor", ((TextElement)((TagElement)javadoc.tags().get(0)).fragments().get(0)).getText());
      //
      assertTrue(methodDeclarations[0].isConstructor());
      //
      assertEquals(Modifier.PUBLIC, methodDeclarations[0].getModifiers());
      //
      assertNull(methodDeclarations[0].getReturnType2());
      //
      assertEquals("Example", methodDeclarations[0].getName().getFullyQualifiedName());
      //
      assertTrue(methodDeclarations[0].parameters().isEmpty());
      //
      assertNotNull(methodDeclarations[0].getBody());
      assertEquals(1, methodDeclarations[0].getBody().statements().size());
      Statement statement = (Statement)methodDeclarations[0].getBody().statements().get(0);
      assertEquals(ASTNode.SUPER_CONSTRUCTOR_INVOCATION, statement.getNodeType());
      assertTrue(((SuperConstructorInvocation)statement).arguments().isEmpty());
    }
    //methodDeclarations[1]: public void setBooleanInstance(Boolean b)
    {
      Javadoc javadoc = methodDeclarations[1].getJavadoc();
      assertEquals(3, javadoc.tags().size());
      assertNull(((TagElement)javadoc.tags().get(0)).getTagName());
      assertEquals(1, ((TagElement)javadoc.tags().get(0)).fragments().size());
      assertEquals("Sets the boolean instance.", ((TextElement)((TagElement)javadoc.tags().get(0)).fragments().get(0)).getText());
      assertEquals("@param", ((TagElement)javadoc.tags().get(1)).getTagName());
      assertEquals(1, ((TagElement)javadoc.tags().get(0)).fragments().size());
      assertEquals("b", ((SimpleName)((TagElement)javadoc.tags().get(1)).fragments().get(0)).getFullyQualifiedName());
      assertEquals("@generated", ((TagElement)javadoc.tags().get(2)).getTagName());
      assertTrue(((TagElement)javadoc.tags().get(2)).fragments().isEmpty());
      //
      assertFalse(methodDeclarations[1].isConstructor());
      //
      assertEquals(Modifier.PUBLIC, methodDeclarations[1].getModifiers());
      //
      assertNotNull(methodDeclarations[1].getReturnType2());
      assertTrue(methodDeclarations[1].getReturnType2().isPrimitiveType());
      assertEquals(PrimitiveType.VOID, ((PrimitiveType)methodDeclarations[1].getReturnType2()).getPrimitiveTypeCode());
      //
      assertEquals("setBooleanInstance", methodDeclarations[1].getName().getFullyQualifiedName());
      //
      assertEquals(1, methodDeclarations[1].parameters().size());
      assertTrue(((SingleVariableDeclaration)methodDeclarations[1].parameters().get(0)).getType().isSimpleType());
      assertEquals("Boolean", ((SimpleType)((SingleVariableDeclaration)methodDeclarations[1].parameters().get(0)).getType()).getName().getFullyQualifiedName());
      assertEquals("b", ((SingleVariableDeclaration)methodDeclarations[1].parameters().get(0)).getName().getFullyQualifiedName());  
      //
      assertNotNull(methodDeclarations[1].getBody());
      assertEquals(1, methodDeclarations[1].getBody().statements().size());
    }
  }
  
  public void testVisitor() throws Exception
  {
    String content = EMFTestToolsPlugin.readFile(CLASS_FILE);
    
    ASTParser astParser = ASTParser.newParser(AST.JLS3);
    astParser.setSource(content.toCharArray());
    CompilationUnit compilationUnit = (CompilationUnit)astParser.createAST(null);

    ASTVisitor commentVisitor = new ASTVisitor(true)
    {
      public boolean visit(BlockComment node)
      {
        System.out.println(node);
        return true;
      }
      public boolean visit(LineComment node)
      {
        System.out.println(node);
        return true;
      }
      public boolean visit(TypeDeclaration node)
      {
        System.out.println(node);
        return true;        
      }
    };
    compilationUnit.accept(commentVisitor);
    
    System.out.println("Here!!!!");
  }
}
