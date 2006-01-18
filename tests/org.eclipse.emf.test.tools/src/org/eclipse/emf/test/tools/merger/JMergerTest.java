/**
 * <copyright>
 *
 * Copyright (c) 2004-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: JMergerTest.java,v 1.8 2006/01/18 20:38:31 marcelop Exp $
 */
package org.eclipse.emf.test.tools.merger;

import java.io.File;
import java.io.FileInputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.codegen.merge.java.JMerger;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.jdom.JDOMFacadeHelper;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.test.tools.TestUtil;

/**
 * This test case was based on the excellent article written by Adrian Powell 
 * Model with the Eclipse Modeling Framework, Part 3 
 * @see http://www-106.ibm.com/developerworks/library/os-ecemf3/.
 */
public class JMergerTest extends TestCase
{
  /**
   * @param name
   */
  public JMergerTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("JMergerTest");
    ts.addTest(new JMergerTest("testMerge0"));
    ts.addTest(new JMergerTest("testMerge1"));
    return ts;
  }  
  
  public void testMerge0() throws Exception
  {
    mergetTest(TestUtil.getPluginDirectory() + "/data/merge0");
  }

  public void testMerge1() throws Exception
  {
    mergetTest(TestUtil.getPluginDirectory() + "/data/merge1");
  }
  
  protected void mergetTest(String dir) throws Exception
  {
    File mergeXML = new File(dir + "/merge.xml").getAbsoluteFile();
    File source = new File(dir + "/MergerSource.java").getAbsoluteFile();
    File target = new File(dir + "/MergerTarget.java").getAbsoluteFile();
    File expected = new File(dir + "/MergerExpected.java").getAbsoluteFile();

    assertTrue("Merge xml file is not available - " + mergeXML.getAbsolutePath(), mergeXML.isFile());
    assertTrue("Merge Source file is not available - " + source.getAbsolutePath(), source.isFile());
    assertTrue("Merge Result file is not available - " + expected.getAbsolutePath(), expected.isFile());
    
    FacadeHelper facadeHelper = CodeGenUtil.instantiateFacadeHelper(JMerger.DEFAULT_FACADE_HELPER_CLASS);
    assertTrue(facadeHelper instanceof JDOMFacadeHelper);
    
    JControlModel controlModel = new JControlModel();
    assertFalse(controlModel.canMerge());
    controlModel.initialize(facadeHelper, mergeXML.getAbsolutePath());
    assertTrue(controlModel.canMerge());
    JMerger jMerger = new JMerger(controlModel);

    // set source
    jMerger.setSourceCompilationUnit(jMerger.createCompilationUnitForContents(TestUtil.readFile(source, false)));
    
    // set target
    if (target.isFile())
    {
      jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForInputStream(new FileInputStream(target)));
    }
    
    // merge source and target
    jMerger.merge();
    
    // extract merged contents
    StringBuffer mergeResult = new StringBuffer(jMerger.getTargetCompilationUnitContents());
    
    // The merge expected file was saved without any '\r' so
    // we need to remove it from the mergedResult
    for (int i=mergeResult.length()-1; i >= 0; i--)
    {
      if ('\r' == mergeResult.charAt(i))
      {
        mergeResult.deleteCharAt(i);
      }
    }
    
    String expectedMerge = TestUtil.readFile(expected, false);
    assertEquals("Make sure the line breaks are OK.  The expected merge should have no '\r'", expectedMerge, mergeResult.toString());
  }  
}