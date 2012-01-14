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

import org.eclipse.emf.codegen.merge.java.facade.JAnnotation;
import org.eclipse.emf.codegen.merge.java.facade.JNode;

public class AnnotationsTest extends BaseFacadeTest
{
  public AnnotationsTest()
  {
    super();
  }

  public AnnotationsTest(BaseFacadeTest anotherTest)
  {
    super(anotherTest);
  }

  /**
   * Tests that the parent node has 2 annotations, version and deprecated,
   * then switches the order of them
   */
  protected void switchVersionAndDeprecatedAnnotations(JNode parentNode)
  {
    testNoChildren(parentNode, 2, JAnnotation.class);

    JAnnotation versionAnnotation = facadeHelper.getChildren(parentNode, JAnnotation.class).get(0);
    JAnnotation deprecatedAnnotation = facadeHelper.getChildren(parentNode, JAnnotation.class).get(1);

    testVersionAnnotation(versionAnnotation);
    testDeprecatedAnnotation(deprecatedAnnotation);

    assertTrue(facadeHelper.remove(versionAnnotation));
    assertFalse(facadeHelper.remove(versionAnnotation));
    updateNoChildren(parentNode, versionAnnotation, Operation.REMOVE, 2, JAnnotation.class);

    testVersionAnnotation(versionAnnotation);
    testDeprecatedAnnotation(deprecatedAnnotation);

    assertTrue(facadeHelper.insertSibling(deprecatedAnnotation, versionAnnotation, false));
    assertFalse(facadeHelper.insertSibling(deprecatedAnnotation, versionAnnotation, false));
    updateNoChildren(parentNode, versionAnnotation, Operation.ADD, 1, JAnnotation.class);

    versionAnnotation = facadeHelper.getChildren(parentNode, JAnnotation.class).get(1);
    deprecatedAnnotation = facadeHelper.getChildren(parentNode, JAnnotation.class).get(0);

    testVersionAnnotation(versionAnnotation);
    testDeprecatedAnnotation(deprecatedAnnotation);    
  }
  
  protected void testDeprecatedAnnotation(JAnnotation annotation)
  {
    assertEquals("@Deprecated", annotation.getName());
    assertEquals("@Deprecated", annotation.getContents());
  }

  protected void testVersionAnnotation(JAnnotation annotation)
  {
    assertEquals("@Version", annotation.getName());
    assertEquals("@Version(\"1.1\"\n// line comment\n// line comment\n  )", annotation.getContents());
  }

  protected void testVersionAndDeprecated(JNode parentNode, int versionIndex, int deprecatedIndex)
  {
    JAnnotation versionAnnotation = facadeHelper.getChildren(parentNode, JAnnotation.class).get(versionIndex);
    JAnnotation deprecatedAnnotation = facadeHelper.getChildren(parentNode, JAnnotation.class).get(deprecatedIndex);

    testVersionAnnotation(versionAnnotation);
    testDeprecatedAnnotation(deprecatedAnnotation);
  } 
  
  protected void testVersionAndDeprecated(JNode parentNode)
  {
    testVersionAndDeprecated(parentNode, 0, 1);
  }  
  
  protected void testSwitchedVersionAndDeprecated(JNode parentNode)
  {
    testVersionAndDeprecated(parentNode, 1, 0);
  }    
}
