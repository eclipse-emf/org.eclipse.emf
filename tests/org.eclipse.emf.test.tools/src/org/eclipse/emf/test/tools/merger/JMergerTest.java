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
 * $Id: JMergerTest.java,v 1.3 2005/01/05 20:42:52 marcelop Exp $
 */
package org.eclipse.emf.test.tools.merger;

import java.io.File;
import java.io.FileInputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.codegen.jmerge.JControlModel;
import org.eclipse.emf.codegen.jmerge.JMerger;
import org.eclipse.emf.test.tools.TestUtil;

/**
 * This test case was based on the excellent article written by Adrian Powell 
 * Model with the Eclipse Modeling Framework, Part 3 
 * @see http://www-106.ibm.com/developerworks/library/os-ecemf3/.
 */
public class JMergerTest extends TestCase
{
  private static final File MERGE_XML = 
     new File(TestUtil.getPluginDirectory() + "/data/merge.xml").getAbsoluteFile();
  private static final File MERGE_SOURCE = 
    new File(TestUtil.getPluginDirectory() + "/data/MergerSource.java").getAbsoluteFile();
  private static final File MERGE_TARGET = 
    new File(TestUtil.getPluginDirectory() + "/data/MergerTarget.java").getAbsoluteFile();
  private static final File MERGE_EXPECTED = 
    new File(TestUtil.getPluginDirectory() + "/data/MergerExpected.java").getAbsoluteFile();
  
  private JMerger jMerger;
  
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
    ts.addTest(new JMergerTest("testMerge"));
    return ts;
  }  
  
  public void testMerge() throws Exception
  {
    assertTrue("Merge xml file is not available - " + MERGE_XML.getAbsolutePath(), MERGE_XML.isFile());
    assertTrue("Merge Source file is not available - " + MERGE_SOURCE.getAbsolutePath(), MERGE_SOURCE.isFile());
    assertTrue("Merge Target file is not available - " + MERGE_TARGET.getAbsolutePath(), MERGE_TARGET.isFile());
    assertTrue("Merge Result file is not available - " + MERGE_EXPECTED.getAbsolutePath(), MERGE_TARGET.isFile());
    
    jMerger = new JMerger();
    JControlModel controlModel = new JControlModel(MERGE_XML.getAbsolutePath());
    jMerger.setControlModel(controlModel);

    // set source
    jMerger.setSourceCompilationUnit(jMerger.createCompilationUnitForContents(TestUtil.readFile(MERGE_SOURCE)));
    
    // set target
    jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForInputStream(new FileInputStream(MERGE_TARGET)));
    
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
    
    String expectedMerge = TestUtil.readFile(MERGE_EXPECTED);
    assertEquals("Make sure the line breaks are OK.  The expected merge should have no '\r'", expectedMerge, mergeResult.toString());
  }  
}