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

import static org.eclipse.emf.codegen.merge.java.facade.FacadeFlags.FINAL;
import static org.eclipse.emf.codegen.merge.java.facade.FacadeFlags.PROTECTED;

import org.eclipse.emf.codegen.merge.java.facade.FacadeFlags;
import org.eclipse.emf.codegen.merge.java.facade.JAnnotation;
import org.eclipse.emf.codegen.merge.java.facade.JField;
import org.eclipse.emf.codegen.merge.java.facade.JType;
import org.eclipse.emf.test.tools.merger.facade.BaseFacadeTest;

public class SplitFieldsTest extends BaseFacadeTest
{
  AnnotationsTest annotationsTest;
  
  @Override
  protected void setUp() throws Exception
  {
    super.setUp();
    annotationsTest = new AnnotationsTest(this);
  }
  
  public void testAnnotationChange()
  {
    readSplitFields();
    
    // change annotation of the second field
    ((JAnnotation)getSplitField2().getChildren().get(1)).setContents("@DeprecatedForSplit2");

    // note that the order has changed - the field with changed annotation
    // is inserted before the original field    
    annotationsTest.testVersionAndDeprecated(getSplitField2());
    assertEquals("@DeprecatedForSplit2", ((JAnnotation)getSplitField1().getChildren().get(1)).getContents());

    readOriginalField1(getSplitField2());
    readOriginalField2(getSplitField1());    
    
    rewriteAndCompare();
  }
  
  public void testAnnotationAdd()
  {
    readSplitFields();
    JField field1 = getSplitField1();
    
    // clone first annotation and insert it to the first field
    JAnnotation annotation = (JAnnotation)facadeHelper.cloneNode(facadeHelper.getContext(field1), field1.getChildren().get(0));
    insertSibling(field1.getChildren().get(0), annotation, true, 2);
    
    annotationsTest.testVersionAndDeprecated(getSplitField2());
    readOriginalField1(getSplitField1());
    readOriginalField2(getSplitField2());
    
    rewriteAndCompare();    
  }

  public void testAnnotationRemove()
  {
    readSplitFields();
    JField field1 = getSplitField1();    
    JField field2 = getSplitField2();
    
    // remove all annotations of 2nd field
    remove(field2.getChildren().get(1), 2);

    annotationsTest.testVersionAndDeprecated(field1);    
    
    // note that the order has changed - the field with removed annotations
    // is inserted before the original field
    readOriginalField1(getSplitField2());
    readOriginalField2(getSplitField1());
    
    rewriteAndCompare();      
  }
  
  public void testCommentChange()
  {
    readSplitFields();
    JField field1 = getSplitField1();
    
    field1.setComment("/** Javadoc for split1 **/");
    
    annotationsTest.testVersionAndDeprecated(getSplitField1());
    annotationsTest.testVersionAndDeprecated(getSplitField2());
    readOriginalField2(getSplitField2());
    
    rewriteAndCompare();       
  }
  
  public void testInitializerChangeWithNoSplit()
  {
    readSplitFields();
    JField field1 = getSplitField1();
    JField field2 = getSplitField2();
    
    // change initializers only - this should not result in split    
    field1.setInitializer("\"\" +\n// new initializer for split1\n  \"\"");
    field2.setInitializer(null);    
    
    assertEquals("\"\" +\n// new initializer for split1\n  \"\"", getSplitField1().getInitializer());
    assertEquals(null, getSplitField2().getInitializer());
    
    rewriteAndCompare();     
  }
  
  public void testInitializerChangeWithSplit()
  {
    readSplitFields();
    
    // change initializer first - this should not result in split
    JField field2 = getSplitField2();
    field2.setInitializer("\"\" +\n// new initializer for split2\n  \"\"");
    
    // change comment - this should result in split
    field2.setComment("/** Javadoc for split2 **/");
    
    annotationsTest.testVersionAndDeprecated(getSplitField1());
    annotationsTest.testVersionAndDeprecated(getSplitField2());
    
    // note that the order has changed - the field with changed comment
    // is inserted before the original field
    readOriginalField1(getSplitField2());
    
    rewriteAndCompare();     
  }   
  
  public void testChangeAll()
  {
    readSplitFields();

    modifyFields();
    
    readModifiedFields();
    
    rewriteAndCompare();    
  }

  public void testChangeAllAndMove()
  {
    readSplitFields();

    modifyFields();
    
    JField field1 = getSplitField1();
    JField field2 = getSplitField2();
    
    remove(field1, 3);
    
    readField1(field1, "split1");
    readField2(field2, "split2");
    
    // insert field1 at the beginning of the class
    insertSibling(facadeHelper.getFirstChild(compilationUnit.getChildren().get(0)), field1, true, 2);
    
    readField1(field1, "split1");
    readField2(field2, "split2");    
    
    rewriteAndCompare();      
  }  
  
  public void testChangeAllAndRemoveAll()
  {
    readSplitFields();

    modifyFields();
    
    JField field1 = getSplitField1();
    JField field2 = getSplitField2();
    
    removeAllChildren(compilationUnit.getChildren().get(0), 3);
    
    readField1(field1, "split1");
    readField2(field2, "split2");
    
    rewriteAndCompare();      
  }   
  
  protected JField getSplitField1()
  {
    JType type = facadeHelper.getChildren(compilationUnit, JType.class).get(0);
    return facadeHelper.getChildren(type, JField.class).get(1);
  }
  
  protected JField getSplitField2()
  {
    JType type = facadeHelper.getChildren(compilationUnit, JType.class).get(0);
    return facadeHelper.getChildren(type, JField.class).get(2);    
  }
  
  protected void readSplitFields()
  {
    annotationsTest.testVersionAndDeprecated(getSplitField1());
    annotationsTest.testVersionAndDeprecated(getSplitField2());
    readOriginalField1(getSplitField1());
    readOriginalField2(getSplitField2());
  }
  
  private void modifyFields()
  {
    modifyField1("split1");
    modifyField2("split2");
  }  
  
  protected void modifyField1(String modificationId)
  {
    JField field1 = getSplitField1();
    field1.setInitializer("\"\" +\n// new initializer for split1\n  \"\"");
    field1.setComment("/** Javadoc for " + modificationId + " **/");
    field1.setFlags(FacadeFlags.PRIVATE | FacadeFlags.TRANSIENT);
    field1.setType("T_" + modificationId);
    field1.setName("renamed_" + modificationId);
  }  
  
  protected void modifyField2(String modificationId)
  {
    JField field2 = getSplitField2();
    field2.setInitializer(null);
    field2.setComment("/** Javadoc for " + modificationId + " **/");
    field2.setFlags(FacadeFlags.DEFAULT);
    field2.setType("T_" + modificationId);
    field2.setName("renamed_" + modificationId);    
  }    

  protected void readModifiedFields()
  {
    annotationsTest.testVersionAndDeprecated(getSplitField1());
    annotationsTest.testVersionAndDeprecated(getSplitField2());
    readField1(getSplitField1(), "split1");
    readField2(getSplitField2(), "split2");
  }  
  
  protected void readOriginalField1(JField field1)
  {
    assertEquals("/**\n   * Javadoc for split1 and split2\n   * Another line of javadoc\n   */", field1.getComment());
    assertEquals(PROTECTED | FINAL, field1.getFlags());
    assertEquals(null, field1.getInitializer());
    assertEquals("T< ? >", field1.getType());
    assertEquals("split1", field1.getName());
  }
  
  protected void readOriginalField2(JField field2)
  {
    assertEquals("/**\n   * Javadoc for split1 and split2\n   * Another line of javadoc\n   */", field2.getComment());
    assertEquals(PROTECTED | FINAL, field2.getFlags());
    assertEquals("\"\" +\n    // line comment in initializer\n    \"\"", field2.getInitializer());
    assertEquals("T< ? >", field2.getType());
    assertEquals("split2", field2.getName());    
  }  
  
  protected void readField1(JField field1, String modificationId)
  {
    assertEquals("/** Javadoc for " + modificationId + " **/", field1.getComment());
    assertEquals(PROTECTED | FINAL, field1.getFlags());
    assertEquals("\"\" +\n// new initializer for split1\n  \"\"", field1.getInitializer());
    assertEquals("T_" + modificationId, field1.getType());
    assertEquals("renamed_" + modificationId, field1.getName());
  }
  
  protected void readField2(JField field2, String modificationId)
  {
    assertEquals("/** Javadoc for " + modificationId + " **/", field2.getComment());
    assertEquals(PROTECTED | FINAL, field2.getFlags());
    assertEquals(null, field2.getInitializer());
    assertEquals("T_" + modificationId, field2.getType());
  }
}
