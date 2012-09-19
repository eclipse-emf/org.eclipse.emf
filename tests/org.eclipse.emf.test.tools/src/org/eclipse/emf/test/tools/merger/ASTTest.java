/**
 * Copyright (c) 2004-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.tools.merger;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.Javadoc;
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
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;

import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.tools.AllSuites;

/*
 * MP:
 * I know we are not responsible for the AST code, but since we had to study it, why not
 * add here a test with the APIs we will be using ;-)
 */
public class ASTTest extends TestCase
{
  private static final File CLASS_FILE = 
    new File(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/Example1.java").getAbsoluteFile();
 
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
   ts.addTest(new ASTTest("testRead"));
   ts.addTest(new ASTTest("testWrite"));   
   return ts;
 }
 
 @Override
protected void setUp() throws Exception
 {
   assertTrue("Source code is not available - " + CLASS_FILE.getAbsolutePath(), CLASS_FILE.isFile());
 }
 
 public void testRead()
 {
   String content = TestUtil.readFile(CLASS_FILE, false);
   
   ASTParser astParser = CodeGenUtil.EclipseUtil.newASTParser();
   astParser.setSource(content.toCharArray());
   CompilationUnit compilationUnit = (CompilationUnit)astParser.createAST(null);
   
   {
     Javadoc javadoc = (Javadoc)compilationUnit.getCommentList().get(0);
     assertEquals(1, javadoc.tags().size());
     TagElement tagElement = (TagElement)javadoc.tags().get(0);
     assertEquals(7, tagElement.fragments().size());
//     for (Iterator i = tagElement.fragments().iterator(); i.hasNext();)
//    {
//      TextElement element = (TextElement)i.next();
//      System.out.println(element.getText());
//    }
   }
   
   
   //** Package
   PackageDeclaration packageDeclaration = compilationUnit.getPackage();
   assertNotNull(packageDeclaration);
   assertTrue(packageDeclaration.getName().isQualifiedName());
   assertEquals("org.eclipse.emf.test.tools.merger", packageDeclaration.getName().getFullyQualifiedName());
   
   //** Imports
   List<?> importDeclarations = compilationUnit.imports();
   assertEquals(6, importDeclarations.size());
   assertEquals("java.util.Collections", ((ImportDeclaration)importDeclarations.get(0)).getName().getFullyQualifiedName());
   assertFalse(((ImportDeclaration)importDeclarations.get(0)).isOnDemand());
   assertEquals("java.util.List", ((ImportDeclaration)importDeclarations.get(1)).getName().getFullyQualifiedName());
   assertFalse(((ImportDeclaration)importDeclarations.get(1)).isOnDemand());
   assertEquals("java.util.Map", ((ImportDeclaration)importDeclarations.get(2)).getName().getFullyQualifiedName());
   assertFalse(((ImportDeclaration)importDeclarations.get(2)).isOnDemand());
   assertEquals("org.eclipse.emf.common", ((ImportDeclaration)importDeclarations.get(3)).getName().getFullyQualifiedName());
   assertTrue(((ImportDeclaration)importDeclarations.get(3)).isOnDemand());
   assertEquals("org.eclipse.emf.common.notify.Notification", ((ImportDeclaration)importDeclarations.get(4)).getName().getFullyQualifiedName());
   assertFalse(((ImportDeclaration)importDeclarations.get(4)).isOnDemand());
   assertEquals("org.eclipse.emf.ecore.impl.EObjectImpl", ((ImportDeclaration)importDeclarations.get(5)).getName().getFullyQualifiedName());
   assertFalse(((ImportDeclaration)importDeclarations.get(5)).isOnDemand());
   
   //** Types
   List<?> typeDeclarations = compilationUnit.types();
   assertEquals(2, typeDeclarations.size());
   
   //** Class Example1
   TypeDeclaration exampleClass = (TypeDeclaration)typeDeclarations.get(1);
   assertEquals("Example1", exampleClass.getName().getFullyQualifiedName());
   assertFalse(exampleClass.isInterface());
   //Javadoc
   {
     Javadoc typeJavadoc = exampleClass.getJavadoc();
     assertEquals(4, typeJavadoc.tags().size());
     @SuppressWarnings("unchecked")
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
   
   //** Content of the Example1 class
   assertEquals(19, exampleClass.bodyDeclarations().size());
   assertEquals(2, exampleClass.getTypes().length);
   assertEquals(7, exampleClass.getFields().length);
   assertEquals(7, exampleClass.getMethods().length);
   
   // Tests the order of the contents
   List<?> bodyDeclarations = exampleClass.bodyDeclarations();
   assertTrue(bodyDeclarations.get(0).toString(), bodyDeclarations.get(0) instanceof TypeDeclaration);
   assertTrue(bodyDeclarations.get(1).toString(), bodyDeclarations.get(1) instanceof Initializer);
   assertTrue(bodyDeclarations.get(2).toString(), bodyDeclarations.get(2) instanceof FieldDeclaration);
   assertTrue(bodyDeclarations.get(3).toString(), bodyDeclarations.get(3) instanceof TypeDeclaration);
   assertTrue(bodyDeclarations.get(4).toString(), bodyDeclarations.get(4) instanceof FieldDeclaration);
   assertTrue(bodyDeclarations.get(5).toString(), bodyDeclarations.get(5) instanceof FieldDeclaration);
   assertTrue(bodyDeclarations.get(6).toString(), bodyDeclarations.get(6) instanceof FieldDeclaration);
   assertTrue(bodyDeclarations.get(7).toString(), bodyDeclarations.get(7) instanceof Initializer);
   assertTrue(bodyDeclarations.get(8).toString(), bodyDeclarations.get(8) instanceof MethodDeclaration);
   assertTrue(bodyDeclarations.get(8).toString(), bodyDeclarations.get(9) instanceof MethodDeclaration);
   assertTrue(bodyDeclarations.get(8).toString(), bodyDeclarations.get(10) instanceof MethodDeclaration);
   assertTrue(bodyDeclarations.get(9).toString(), bodyDeclarations.get(11) instanceof MethodDeclaration);
   assertTrue(bodyDeclarations.get(10).toString(), bodyDeclarations.get(12) instanceof FieldDeclaration);
   assertTrue(bodyDeclarations.get(11).toString(), bodyDeclarations.get(13) instanceof MethodDeclaration);
   assertTrue(bodyDeclarations.get(12).toString(), bodyDeclarations.get(14) instanceof MethodDeclaration);
   assertTrue(bodyDeclarations.get(13).toString(), bodyDeclarations.get(15) instanceof MethodDeclaration);
   assertTrue(bodyDeclarations.get(14).toString(), bodyDeclarations.get(16) instanceof Initializer);

   //** Initializers
   {
     Initializer initializer = (Initializer)bodyDeclarations.get(1);
     assertFalse(Modifier.isStatic(initializer.getModifiers()));
     assertNull(initializer.getJavadoc());
   }
   //
   {
     Initializer initializer = (Initializer)bodyDeclarations.get(7);
     assertTrue(Modifier.isStatic(initializer.getModifiers()));
     assertNotNull(initializer.getJavadoc());
     Javadoc javadoc = initializer.getJavadoc();
     assertEquals(1, javadoc.tags().size());
     assertEquals(1, ((TagElement)javadoc.tags().get(0)).fragments().size());
     assertEquals("An static initializer", ((TextElement)((TagElement)javadoc.tags().get(0)).fragments().get(0)).getText());     
   }
   //
   {
     Initializer initializer = (Initializer)bodyDeclarations.get(16);
     assertFalse(Modifier.isStatic(initializer.getModifiers()));
     assertNotNull(initializer.getJavadoc());
     Javadoc javadoc = initializer.getJavadoc();
     assertEquals(1, javadoc.tags().size());
     assertEquals(2, ((TagElement)javadoc.tags().get(0)).fragments().size());
     assertEquals("Another initializer with 2 lines", ((TextElement)((TagElement)javadoc.tags().get(0)).fragments().get(0)).getText());
     assertEquals("of javadoc.", ((TextElement)((TagElement)javadoc.tags().get(0)).fragments().get(1)).getText());
   }
   
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
     @SuppressWarnings("unchecked")
     VariableDeclarationFragment[] variableDeclarationFragments = (VariableDeclarationFragment[])fieldDeclarations[0].fragments().toArray(new VariableDeclarationFragment[fieldDeclarations[0].fragments().size()]);
     assertEquals(0, variableDeclarationFragments[0].getExtraDimensions());
     assertEquals("STR_CONST", variableDeclarationFragments[0].getName().getFullyQualifiedName());
     //
     assertNotNull(variableDeclarationFragments[0].getInitializer());
     assertTrue(variableDeclarationFragments[0].getInitializer().getClass().getName(), variableDeclarationFragments[0].getInitializer() instanceof InfixExpression);
     InfixExpression infixExpression = (InfixExpression)variableDeclarationFragments[0].getInitializer();
     assertTrue(infixExpression.getLeftOperand() instanceof StringLiteral);
     assertEquals("something is ; different \"//; /*;*/", ((StringLiteral)infixExpression.getLeftOperand()).getLiteralValue());
     assertTrue(infixExpression.getRightOperand() instanceof StringLiteral);
     assertEquals(" !!;;", ((StringLiteral)infixExpression.getRightOperand()).getLiteralValue());
     assertEquals("+", infixExpression.getOperator().toString());
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
     @SuppressWarnings("unchecked")
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
     @SuppressWarnings("unchecked")
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
     @SuppressWarnings("unchecked")
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
     @SuppressWarnings("unchecked")
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
   //methodDeclarations[0]: public Example1()
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
     assertEquals("Example1", methodDeclarations[0].getName().getFullyQualifiedName());
     //
     assertTrue(methodDeclarations[0].parameters().isEmpty());
     //
     assertNotNull(methodDeclarations[0].getBody());
     assertEquals(1, methodDeclarations[0].getBody().statements().size());
     Statement statement = (Statement)methodDeclarations[0].getBody().statements().get(0);
     assertEquals(ASTNode.SUPER_CONSTRUCTOR_INVOCATION, statement.getNodeType());
     assertTrue(((SuperConstructorInvocation)statement).arguments().isEmpty());
   }
   //methodDeclarations[2]: public void setBooleanInstance(Boolean b)
   {
     Javadoc javadoc = methodDeclarations[2].getJavadoc();
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
     assertFalse(methodDeclarations[2].isConstructor());
     //
     assertEquals(Modifier.PUBLIC, methodDeclarations[2].getModifiers());
     //
     assertNotNull(methodDeclarations[2].getReturnType2());
     assertTrue(methodDeclarations[2].getReturnType2().isPrimitiveType());
     assertEquals(PrimitiveType.VOID, ((PrimitiveType)methodDeclarations[2].getReturnType2()).getPrimitiveTypeCode());
     //
     assertEquals("setBooleanInstance", methodDeclarations[2].getName().getFullyQualifiedName());
     //
     assertEquals(1, methodDeclarations[2].parameters().size());
     assertTrue(((SingleVariableDeclaration)methodDeclarations[2].parameters().get(0)).getType().isSimpleType());
     assertEquals("Boolean", ((SimpleType)((SingleVariableDeclaration)methodDeclarations[2].parameters().get(0)).getType()).getName().getFullyQualifiedName());
     assertEquals("b", ((SingleVariableDeclaration)methodDeclarations[2].parameters().get(0)).getName().getFullyQualifiedName());  
     //
     assertNotNull(methodDeclarations[2].getBody());
     assertEquals(1, methodDeclarations[2].getBody().statements().size());
   }
 }  
 
	/**
	 * Some test examples on using ASTRewrite to rewrite the code
	 */
	public void testWrite() throws Exception {
		 // read
     String source = TestUtil.readFile(CLASS_FILE, false);    
	   ASTParser astParser = CodeGenUtil.EclipseUtil.newASTParser();
	   astParser.setSource(source.toCharArray());
	   CompilationUnit sourceCu = (CompilationUnit)astParser.createAST(null);
	   astParser.setSource(source.toCharArray());
	   CompilationUnit targetCu = (CompilationUnit)astParser.createAST(null);
	   
	   // make modifications
	   TypeDeclaration sourceClass = (TypeDeclaration)sourceCu.types().get(1);
	   TypeDeclaration targetClass = (TypeDeclaration)targetCu.types().get(1);
	   
		 ASTRewrite rewriter = ASTRewrite.create(targetCu.getAST());	
     
		 IDocument targetDoc = new Document(new String(source.toCharArray())); 	   

		 // copy whole method using strings and placeholder rewrite
		 ASTNode sourceMethodToCopy = sourceClass.getMethods()[4];
		 ASTNode targetMethodToCopy = rewriter.createStringPlaceholder(
       source.substring(
         sourceMethodToCopy.getStartPosition(), 
         sourceMethodToCopy.getStartPosition() + sourceMethodToCopy.getLength()),
       ASTNode.METHOD_DECLARATION);		 
		 ListRewrite lrw = rewriter.getListRewrite(targetClass, TypeDeclaration.BODY_DECLARATIONS_PROPERTY);
		 lrw.insertFirst(targetMethodToCopy, null);	   
	   
		 // setBody() - replace body of the method
		 ASTNode sourceMethodBodyToCopy = sourceClass.getMethods()[5].getBody();
		 ASTNode targetMethodBodyToCopy = rewriter.createStringPlaceholder(
       source.substring(
         sourceMethodBodyToCopy.getStartPosition(), 
         sourceMethodBodyToCopy.getStartPosition() + sourceMethodBodyToCopy.getLength()),
       ASTNode.BLOCK);
		 rewriter.replace(targetClass.getMethods()[6].getBody(), targetMethodBodyToCopy, null);
		 
		 // setExceptions() replace all exceptions
		 List<?> exceptionsToSet = sourceClass.getMethods()[5].thrownExceptions();
		 List<?> targetExceptins = targetClass.getMethods()[6].thrownExceptions();
		 lrw = rewriter.getListRewrite(targetClass.getMethods()[6], MethodDeclaration.THROWN_EXCEPTIONS_PROPERTY);
		 // remove all exceptions
		 for(Iterator<?> it = targetExceptins.iterator(); it.hasNext();)
			 lrw.remove((ASTNode) it.next(), null);
		 // add all exceptions
		 for(Iterator<?> it = exceptionsToSet.iterator(); it.hasNext();)
			 lrw.insertLast((ASTNode) it.next(), null);

		 // copy comment
		 ASTNode comment = sourceClass.getMethods()[3].getJavadoc();
		 rewriter.set(targetClass.getMethods()[6], MethodDeclaration.JAVADOC_PROPERTY, comment, null);
		 
		 // apply changes
	   TextEdit editsInWriter = rewriter.rewriteAST(targetDoc, null);
	   editsInWriter.apply(targetDoc);
	   String result = targetDoc.get();		 

     File expectedOutputFile = new File(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/Example1Changed.java").getAbsoluteFile();
     
     String expectedResult = TestUtil.readFile(expectedOutputFile, false);
     
     assertEquals(expectedResult, result);
	 }
}