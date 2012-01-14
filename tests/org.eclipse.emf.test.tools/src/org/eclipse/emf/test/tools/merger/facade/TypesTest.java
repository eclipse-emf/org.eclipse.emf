/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.tools.merger.facade;

import static org.eclipse.emf.test.tools.merger.facade.BaseFacadeTest.Operation.ADD;
import static org.eclipse.emf.test.tools.merger.facade.BaseFacadeTest.Operation.REMOVE;

import java.util.Arrays;

import org.eclipse.emf.codegen.merge.java.facade.FacadeFlags;
import org.eclipse.emf.codegen.merge.java.facade.JAbstractType;
import org.eclipse.emf.codegen.merge.java.facade.JMember;
import org.eclipse.emf.codegen.merge.java.facade.JType;
import org.eclipse.emf.test.tools.merger.facade.BaseFacadeTest;


public class TypesTest extends BaseFacadeTest
{
  protected int numberOfChildren = 9;

  public void testCompilationUnitName()
  {
    assertEquals("Name does not match", "Example1.java", compilationUnit.getName());
  }

  public void testMoveAndModify1() 
  {
    testNoChildren(compilationUnit, numberOfChildren);

    moveAndModifyTypes();

    rewriteAndCompare();
  }

  public void testMoveAndModify2()
  {
    testNoChildren(compilationUnit, numberOfChildren);

    clearAllPropertiesOfType(moveAndModifyTypes());

    rewriteAndCompare();
  }

  /**
   * Performs everything on AnotherClass that {@link #moveAndModifyExistingType(JAbstractType)}
   * then clones AnotherClass, adds, removes and adds cloned AnotherClass.
   */
  protected JAbstractType moveAndModifyTypes()
  {
    JAbstractType type = (JAbstractType)compilationUnit.getChildren().get(7);
    
    moveAndModifyExistingType(type);

    // clone AnotherClass
    Object context = facadeHelper.getContext(type);
    JAbstractType clonedType = (JAbstractType)facadeHelper.cloneNode(context, type);
    facadeHelper.addChild(compilationUnit, clonedType);

    numberOfChildren = updateNoChildren(compilationUnit, type, ADD, numberOfChildren);

    // modifying cloned type is not permitted - will fail during rewrite
    //clonedType.setComment("/** Changed after cloning\n// linecomment\n */");

    facadeHelper.remove(clonedType);
    numberOfChildren = updateNoChildren(compilationUnit, clonedType, REMOVE, numberOfChildren);

    facadeHelper.insertSibling(type, clonedType, true);
    numberOfChildren = updateNoChildren(compilationUnit, clonedType, ADD, numberOfChildren);

    return type;
  }

  /**
   * Moves existing type twice while testing and modifying all properties.
   * During the last move, switches the order of annotations, testing their contents.
   */
  protected JAbstractType moveAndModifyExistingType(JAbstractType type)
  {
    AnnotationsTest annotationsTest = new AnnotationsTest(this);
    
    //
    // modify and remove type
    readOriginalType(type);
    annotationsTest.testVersionAndDeprecated(type);
    modifyType(type, "1", FacadeFlags.PRIVATE | FacadeFlags.STATIC);
    
    readType(type, "1", FacadeFlags.PRIVATE | FacadeFlags.STATIC);

    assertTrue(facadeHelper.remove(type));
    assertFalse(facadeHelper.remove(type));
    numberOfChildren = updateNoChildren(compilationUnit, type, REMOVE, numberOfChildren);

    //
    // modify and insert type
    readType(type, "1", FacadeFlags.PRIVATE | FacadeFlags.STATIC);
    annotationsTest.testVersionAndDeprecated(type);

    modifyType(type, "2", FacadeFlags.PUBLIC);

    readType(type, "2", FacadeFlags.PUBLIC);

    assertTrue(facadeHelper.addChild(compilationUnit, type));
    assertFalse(facadeHelper.addChild(compilationUnit, type));
    numberOfChildren = updateNoChildren(compilationUnit, type, ADD, numberOfChildren);

    //
    // modify and remove type second time
    readType(type, "2", FacadeFlags.PUBLIC);
    annotationsTest.testVersionAndDeprecated(type);

    modifyType(type, "3", FacadeFlags.FINAL | FacadeFlags.PROTECTED);

    readType(type, "3", FacadeFlags.FINAL | FacadeFlags.PROTECTED);
    
    assertTrue(facadeHelper.remove(type));
    assertFalse(facadeHelper.remove(type));
    numberOfChildren = updateNoChildren(compilationUnit, type, REMOVE, numberOfChildren);

    //
    // switch annotations
    annotationsTest.switchVersionAndDeprecatedAnnotations(type);

    //
    // insert type again
    assertTrue(facadeHelper.addChild(compilationUnit, type));
    assertFalse(facadeHelper.addChild(compilationUnit, type));
    numberOfChildren = updateNoChildren(compilationUnit, type, ADD, numberOfChildren);

    readType(type, "3", FacadeFlags.FINAL | FacadeFlags.PROTECTED);
    annotationsTest.testSwitchedVersionAndDeprecated(type);

    return type;
  }

  protected void readOriginalType(JAbstractType abstractType)
  {
    JType type = (JType)abstractType;
    assertEquals("/**\n * \n * AnotherClass javadoc\n * Second line of javadoc\n */", type.getComment());
    assertEquals(FacadeFlags.DEFAULT, type.getFlags());
    assertEquals(null, type.getSuperclass());
    assertTrue(Arrays.equals(new String [0], type.getSuperInterfaces()));
    assertTrue(Arrays.equals(new String [0], type.getTypeParameters()));
    assertEquals("AnotherClass", type.getName());
  }  
  
  protected void modifyType(JAbstractType abstractType, String modificationId, int flags)
  {
    JType type = (JType)abstractType;
    type.setComment("/** Javadoc " + modificationId + "\n\n **/");
    type.setComment("/** Javadoc " + modificationId + "_1\n// linecomment\n */");
    type.setFlags(flags);
    type.setSuperclass("Superclass" + modificationId);
    type.setSuperclass("Superclass" + modificationId + "_1");
    type.setSuperInterfaces(new String []{ "Interface" + modificationId, "Interface" + modificationId + "_1" });
    type.addSuperInterface("Interface" + modificationId + "_2");
    type.addSuperInterface("Interface" + modificationId + "_3");
    type.setTypeParameters(new String []{ "Type" + modificationId, "Type" + modificationId + "_1" });
    type.setName("TypeName" + modificationId);    
  }
  
  protected void readType(JAbstractType abstractType, String modificationId, int flags)
  {
    JType type = (JType)abstractType;
    assertEquals("/** Javadoc " + modificationId + "_1\n// linecomment\n */", type.getComment());
    assertEquals(FacadeFlags.DEFAULT, type.getFlags());
    assertEquals("Superclass" + modificationId + "_1", type.getSuperclass());
    assertTrue(Arrays.equals(new String []{
      "Interface" + modificationId,
      "Interface" + modificationId + "_1",
      "Interface" + modificationId + "_2",
      "Interface" + modificationId + "_3" }, type.getSuperInterfaces()));
    assertTrue(Arrays.equals(new String []{ "Type" + modificationId, "Type" + modificationId + "_1" }, type.getTypeParameters()));
    assertEquals("TypeName" + modificationId, type.getName());
  }   
  
  protected void clearAllPropertiesOfType(JAbstractType abstractType)
  {
    JType type = (JType)abstractType;
    type.setComment(null);
    type.setFlags(FacadeFlags.DEFAULT);
    type.setSuperclass(null);
    type.setSuperInterfaces(new String [0]);
    type.setTypeParameters(new String [0]);
    type.setName("ClearedName");

    // keep annotations
    removeAllChildren(type, 2, JMember.class);
  }
}
