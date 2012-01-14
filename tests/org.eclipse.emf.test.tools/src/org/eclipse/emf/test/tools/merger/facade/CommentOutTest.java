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

import org.eclipse.emf.codegen.merge.java.facade.FacadeFlags;
import org.eclipse.emf.codegen.merge.java.facade.JAnnotation;
import org.eclipse.emf.codegen.merge.java.facade.JField;
import org.eclipse.emf.codegen.merge.java.facade.JMethod;
import org.eclipse.emf.codegen.merge.java.facade.JType;

/**
 * Tests comment out feature by modifying and commenting out nodes.
 * @see org.eclipse.emf.codegen.merge.java.facade.FacadeHelper#commentOut(org.eclipse.emf.codegen.merge.java.facade.JNode)
 */
public class CommentOutTest extends BaseFacadeTest
{
  protected JType getType()
  {
    return (JType)compilationUnit.getChildren().get(1);
  }
  
  public void testMethod()
  {
    JType type = getType();
    JMethod method = (JMethod)type.getChildren().get(3);
    
    (new MethodsTest()).modifyMethod(method, "1", 1, 2, 3, FacadeFlags.PRIVATE, true);
    
    facadeHelper.commentOut(method);
    
    rewriteAndCompare();
  }
  
  public void testField()
  {
    JType type = getType();
    JField field = (JField)type.getChildren().get(2);
    
    (new FieldsTest()).modifyField(field, "1");
    
    facadeHelper.commentOut(field);
    
    rewriteAndCompare();
  }
  
  public void testType()
  {
    JType type = getType();
    
    (new TypesTest()).modifyType(type, "1", FacadeFlags.PRIVATE);
    
    facadeHelper.commentOut(type);
    
    rewriteAndCompare();
  }  

  public void testAnnotation()
  {
    JType type = getType();
    JAnnotation annotation = (JAnnotation)type.getChildren().get(1);

    annotation.setContents("@NewAnnotation(\n// line comment inside\n)");
    
    facadeHelper.commentOut(annotation);
    
    rewriteAndCompare();
  }    
  
  public void testNestedCommentOut()
  {
    JType type = getType();
    JField field = (JField)type.getChildren().get(2);
    
    facadeHelper.commentOut(field);
    facadeHelper.commentOut(type);
    
    rewriteAndCompare();
  }
  
  public void testCommentOutAndRemove()
  {
    JType type = getType();
    JField field = (JField)type.getChildren().get(2);
    
    facadeHelper.commentOut(field);
    facadeHelper.remove(field);
    
    rewriteAndCompare();
  }  
}
