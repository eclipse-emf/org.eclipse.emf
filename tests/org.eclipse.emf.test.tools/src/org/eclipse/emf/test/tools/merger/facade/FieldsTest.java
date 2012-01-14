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

import static org.eclipse.emf.codegen.merge.java.facade.FacadeFlags.*;
import static org.eclipse.emf.test.tools.merger.facade.BaseFacadeTest.Operation.*;

import org.eclipse.emf.codegen.merge.java.facade.JEnum;
import org.eclipse.emf.codegen.merge.java.facade.JField;
import org.eclipse.emf.codegen.merge.java.facade.JType;
import org.eclipse.emf.test.tools.merger.facade.BaseFacadeTest;

public class FieldsTest extends BaseFacadeTest
{
  /**
   * Moves field1 from class to the enum. Modifies all properties and tests them during move.
   */
  public void testMoveAndModify1()
  {
    moveAndModifyField1();
    
    rewriteAndCompare();
  }
  
  /**
   * Does all modifications of {@link #testMoveAndModify1()},
   * switches the order of annotations of field1 in enum, modifies these annotations, 
   * then clones the field and inserts cloned field back into class 1.
   */
  public void testMoveAndModify2()
  {
    moveAndModifyField2();
    
    rewriteAndCompare();
  }  

  /**
   * Does all modifications of {@link #testMoveAndModify2()},
   * and at the end switches the order of enum and class.
   */  
  public void testMoveClasses() 
  {
    moveAndModifyField2();
    
    JType type = (JType)compilationUnit.getChildren().get(0);
    
    // switch class and enum
    assertTrue(facadeHelper.remove(type));
    assertFalse(facadeHelper.remove(type));
    updateNoChildren(compilationUnit, type, REMOVE, 2);

    assertTrue(facadeHelper.addChild(compilationUnit, type));
    assertFalse(facadeHelper.addChild(compilationUnit, type));
    updateNoChildren(compilationUnit, type, ADD, 1);
    
    rewriteAndCompare();
  }
  
  /**
   * Moves field1 from class to the enum. Modifies all properties and tests them during move.
   */  
  protected void moveAndModifyField1()
  {
    testNoChildren(compilationUnit, 2);
    
    JType type1 = (JType)compilationUnit.getChildren().get(0);
    JEnum enum1 = (JEnum)compilationUnit.getChildren().get(1);
    
    testNoChildren(type1, 2);
    testNoChildren(enum1, 0);
    
    JField field1 = (JField)type1.getChildren().get(0);
    
    readOriginalField1(field1);
    
    modifyField(field1, "1_1");
    
    facadeHelper.remove(field1);
    updateNoChildren(type1, field1, REMOVE, 2);
    
    modifyField(field1, "1_2");    
    readModifiedField1(field1, "1_2");
    
    facadeHelper.addChild(enum1, field1);
    updateNoChildren(enum1, field1, ADD, 0);
    
    modifyField(field1, "1_3");
    readModifiedField1(field1, "1_3");     
  }
  
  /**
   * Does all modifications of {@link #testMoveAndModify1()},
   * switches the order of annotations of field1 in enum, modifies these annotations, 
   * then clones the field and inserts cloned field back into class 1.
   */
  protected void moveAndModifyField2()
  {
    moveAndModifyField1();
    
    // get field that was moved from class to enum
    JType type = (JType)compilationUnit.getChildren().get(0);
    JField field1 = (JField)compilationUnit.getChildren().get(1).getChildren().get(0);
    
    // switch annotations
    AnnotationsTest annotationsTest = new AnnotationsTest(this);
    annotationsTest.switchVersionAndDeprecatedAnnotations(field1);
    
    // clone field
    Object context = facadeHelper.getContext(type);
    JField clonedField = (JField)facadeHelper.cloneNode(context, field1);
    
    // insert cloned field back in the class
    assertTrue(facadeHelper.addChild(type, clonedField));
    assertFalse(facadeHelper.addChild(type, clonedField));
    updateNoChildren(type, clonedField, ADD, 1);
  }
  
  protected void modifyField(JField field, String modificationId)
  {
    field.setComment("/** Javadoc " + modificationId + " **/");
    field.setInitializer("new Object() {\n      // line comment " + modificationId + "\n      // line comment\n    }");
    field.setType("Type_" + modificationId);
    field.setName("field_modified_" + modificationId);
  }

  protected void readOriginalField1(JField field)
  {
    testNoChildren(field, 2);
    assertEquals("/**\n   * Javadoc for field1\n   * Another line of javadoc\n   */", field.getComment());
    assertEquals(PUBLIC | STATIC | TRANSIENT | VOLATILE, field.getFlags());
    assertEquals("new Object() {\n      // line comment\n      // line comment\n    }", field.getInitializer());
    assertEquals("List< ? extends Serializable >", field.getType());
    assertEquals("field1", field.getName());
  }
  
  protected void readOriginalField2(JField field)
  {
    testNoChildren(field, 2);
    assertEquals("/**\n   * Javadoc for field2\n   * Another line of javadoc\n   */", field.getComment());
    assertEquals(PROTECTED | FINAL, field.getFlags());
    assertEquals(null, field.getInitializer());
    assertEquals("T", field.getType());
    assertEquals("field2", field.getName());
  } 
  
  protected void readModifiedField1(JField field2, String modificationId)
  {
    testNoChildren(field2, 2);
    assertEquals("/** Javadoc " + modificationId + " **/", field2.getComment());
    assertEquals(PUBLIC | STATIC | TRANSIENT | VOLATILE, field2.getFlags());
    assertEquals("new Object() {\n      // line comment " + modificationId + "\n      // line comment\n    }", field2.getInitializer());
    assertEquals("Type_" + modificationId, field2.getType());
    assertEquals("field_modified_" + modificationId, field2.getName());
  }
  
  protected void readModifiedField2(JField field2, String modificationId)
  {
    testNoChildren(field2, 2);
    assertEquals("/** Javadoc " + modificationId + " **/", field2.getComment());
    assertEquals(PROTECTED | FINAL, field2.getFlags());
    assertEquals("new Object() {\n      // line comment " + modificationId + "\n      // line comment\n    }", field2.getInitializer());
    assertEquals("Type_" + modificationId, field2.getType());
    assertEquals("field_modified_" + modificationId, field2.getName());
  }
}
