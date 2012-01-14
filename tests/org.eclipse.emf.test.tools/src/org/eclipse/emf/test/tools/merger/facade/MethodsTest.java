/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 */
package org.eclipse.emf.test.tools.merger.facade;

import static org.eclipse.emf.test.tools.merger.facade.BaseFacadeTest.Operation.*;

import java.util.Arrays;

import org.eclipse.emf.codegen.merge.java.facade.FacadeFlags;
import org.eclipse.emf.codegen.merge.java.facade.JEnum;
import org.eclipse.emf.codegen.merge.java.facade.JMethod;
import org.eclipse.emf.codegen.merge.java.facade.JType;
import org.eclipse.emf.test.tools.merger.facade.BaseFacadeTest;

public class MethodsTest extends BaseFacadeTest
{
  /**
   * Moves method1 from class to the enum. Modifies all properties and tests them during move.
   */
  public void testMoveAndModify1()
  {
    moveAndModifyMethod1();
    
    rewriteAndCompare();
  }
  
  /**
   * Does all modifications of {@link #testMoveAndModify1()},
   * switches the order of annotations of method1 in enum, modifies these annotations, 
   * then clones the method and inserts cloned method back into class 1.
   */
  public void testMoveAndModify2()
  {
    moveAndModifyMethod2();
    
    rewriteAndCompare();
  }  

  /**
   * Does all modifications of {@link #testMoveAndModify2()},
   * and at the end switches the order of enum and class.
   */  
  public void testMoveClasses() 
  {
    moveAndModifyMethod2();
    
    JType type = (JType)compilationUnit.getChildren().get(0);
    
    // switch class and enum
    assertTrue(facadeHelper.remove(type));
    assertFalse(facadeHelper.remove(type));
    updateNoChildren(compilationUnit, type, REMOVE, 2);

    assertTrue(facadeHelper.addChild(compilationUnit, type));
    assertFalse(facadeHelper.addChild(compilationUnit, type));
    assertEquals(compilationUnit.getChildren().indexOf(type), 1);
    assertTrue(compilationUnit.getChildren().get(0) instanceof JEnum);
    updateNoChildren(compilationUnit, type, ADD, 1);
    
    rewriteAndCompare();
  }
  
  /**
   * Moves method1 from class to the enum. Modifies all properties and tests them during move.
   */  
  protected void moveAndModifyMethod1()
  {
    testNoChildren(compilationUnit, 2);
    
    JType type1 = (JType)compilationUnit.getChildren().get(0);
    JEnum enum1 = (JEnum)compilationUnit.getChildren().get(1);
    
    testNoChildren(type1, 2);
    testNoChildren(enum1, 0);
    
    JMethod method1 = (JMethod)type1.getChildren().get(0);
    
    modifyMethod(method1, "1", 2, 1, 2, FacadeFlags.PUBLIC, true);
//    readModifiedMethod(method1, "1_1", 1, 2, FacadeFlags.PUBLIC);
    
    assertTrue(facadeHelper.remove(method1));
    assertFalse(facadeHelper.remove(method1));
    updateNoChildren(type1, method1, REMOVE, 2);
    
    modifyMethod(method1, "2", 2, 1, 2, FacadeFlags.PUBLIC, true);
//    readModifiedMethod(method1, "1_2", 1, 2, FacadeFlags.PUBLIC);
    
    assertTrue(facadeHelper.addChild(enum1, method1));
    assertFalse(facadeHelper.addChild(enum1, method1));    
    updateNoChildren(enum1, method1, ADD, 0);
    
    modifyMethod(method1, "3", 2, 1, 2, FacadeFlags.PUBLIC, true);
//    readModifiedMethod(method1, "1_3", 1, 2, FacadeFlags.PUBLIC);  
  }
  
  /**
   * Does all modifications of {@link #testMoveAndModify1()},
   * switches the order of annotations of method1 in enum, modifies these annotations, 
   * then clones the method and inserts cloned method back into class 1.
   */
  protected void moveAndModifyMethod2()
  {
    moveAndModifyMethod1();
    
    // get method that was moved from class to enum
    JType type = (JType)compilationUnit.getChildren().get(0);
    JMethod method1 = (JMethod)compilationUnit.getChildren().get(1).getChildren().get(0);
    
    // switch annotations
    AnnotationsTest annotationsTest = new AnnotationsTest(this);
    annotationsTest.switchVersionAndDeprecatedAnnotations(method1);
    
    // clone method
    Object context = facadeHelper.getContext(type);
    JMethod clonedMethod = (JMethod)facadeHelper.cloneNode(context, method1);
    
    // insert cloned method back in the class
    assertTrue(facadeHelper.addChild(type, clonedMethod));
    assertFalse(facadeHelper.addChild(type, clonedMethod));
    updateNoChildren(type, clonedMethod, ADD, 1);
  }
  
  protected void modifyMethod(JMethod method, String modificationId, int noParameters, int noExceptions, int noTypeParameters, int flags, boolean enableSetParameters)
  {
    method.setComment("/**\n   * Javadoc " + modificationId + "\n   */");
    method.setBody("{\n  // body " + modificationId + "\n  }");
    
    String[] exceptions = new String[noExceptions];
    for (int i = 0; i < noExceptions; i++)
    {
      exceptions[i] = "Exception" + i + "_" + modificationId;
    }
    method.setExceptions(exceptions);
    
    method.setName("method_" + modificationId);
    method.setFlags(flags);
    
    if (enableSetParameters)
    {
      String[] parameters = new String[noParameters];
      for (int i = 0; i < noParameters; i++)
      {
        parameters[i] = "Type" + i + "_" + modificationId + " param" + i + "_" + modificationId;
      }
      method.setParameters(parameters);      
    }
    else
    {
      String[] parameterNames = method.getParameterNames();
      for (int i = 0; i < parameterNames.length; i++)
      {
        parameterNames[i] = "param" + i + "_" + modificationId;
      }
      method.setParameterNames(parameterNames);      
    }
    
    method.setReturnType("ReturnType" + modificationId);
    
    String[] typeParameters = new String[noTypeParameters];
    for (int i = 0; i < noTypeParameters; i++)
    {
      typeParameters[i] = "T" + i + "_" + modificationId;
    }
    method.setTypeParameters(typeParameters);
  }

  protected void readModifiedMethod(JMethod method, String modificationId, int noParameters, int noExceptions, int noTypeParameters, int flags, boolean enableSetParameters)
  {
    assertEquals("/**\n   * Javadoc " + modificationId + "\n   */", method.getComment());
    assertEquals("{\n  // body " + modificationId + "\n  }", method.getBody());
    
    String[] exceptions = new String[noExceptions];
    for (int i = 0; i < noExceptions; i++)
    {
      exceptions[i] = "Exception" + i + "_" + modificationId;
    }
    assertTrue(Arrays.equals(exceptions, method.getExceptions()));
    
    assertEquals("method_" + modificationId, method.getName());
    assertEquals(flags, method.getFlags());
    
    if (enableSetParameters)
    {
      String[] parameters = new String[noParameters];
      for (int i = 0; i < noParameters; i++)
      {
        parameters[i] = "Type" + i + "_" + modificationId + " param" + i + "_" + modificationId;
      }
      method.setParameters(parameters);      
    }
    else
    {
      String[] parameterNames = method.getParameterNames();
      for (int i = 0; i < parameterNames.length; i++)
      {
        parameterNames[i] = "param" + i + "_" + modificationId;
      }
      method.setParameterNames(parameterNames);      
    }
    
    assertEquals("ReturnType" + modificationId, method.getReturnType());
    
    String[] typeParameters = new String[noTypeParameters];
    for (int i = 0; i < noTypeParameters; i++)
    {
      typeParameters[i] = "T" + i + "_" + modificationId;
    }
    assertEquals(typeParameters, method.getTypeParameters());
  }
}
