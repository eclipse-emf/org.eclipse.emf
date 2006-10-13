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
 * $Id: DeprecatedJMergerTest.java,v 1.5 2006/10/13 22:55:09 marcelop Exp $
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
 * Each test method in this class works with a data/mergeN directory.
 */
public class DeprecatedJMergerTest extends TestCase
{
  protected File mergeXML;
  protected File source;
  protected File target; 
  protected File expected;
  
  /**
   * @param name
   */
  public DeprecatedJMergerTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("JMergerTest");
    ts.addTest(new DeprecatedJMergerTest("merge0"));
    ts.addTest(new DeprecatedJMergerTest("merge1"));
    ts.addTest(new DeprecatedJMergerTest("merge2"));
    ts.addTest(new DeprecatedJMergerTest("merge3"));
    return ts;
  }
  
  protected void setUp() throws Exception
  {
    String dir = TestUtil.getPluginDirectory() + "/data/" + getName();

    mergeXML = new File(dir + "/merge.xml").getAbsoluteFile();
    assertTrue("Merge xml file is not available - " + mergeXML.getAbsolutePath(), mergeXML.isFile());

    source = new File(dir + "/MergerSource.java").getAbsoluteFile();
    assertTrue("Merge Source file is not available - " + source.getAbsolutePath(), source.isFile());

    target = new File(dir + "/MergerTarget.java").getAbsoluteFile();

    expected = new File(dir + "/MergerExpected.java").getAbsoluteFile();
    assertTrue("Merge Result file is not available - " + expected.getAbsolutePath(), expected.isFile());
  }
  
  public void merge0() throws Exception
  {
    verifyMerge(mergeFiles());
  }

  /**
   * This test case was based on the excellent article written by Adrian Powell 
   * Model with the Eclipse Modeling Framework, Part 3 
   * @see http://www-106.ibm.com/developerworks/library/os-ecemf3/.
   */  
  public void merge1() throws Exception
  {
    verifyMerge(mergeFiles());
  }
  
  /*
   * Bugzilla 126175
   */
  public void merge2() throws Exception
  {
    verifyMerge(mergeFiles());
  }
  
  public void merge3() throws Exception
  {
    verifyMerge(mergeFiles());
  }

  protected void verifyMerge(String targetContents)
  {
    // extract merged contents
    StringBuffer mergeResult = new StringBuffer(targetContents);
    
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
    String actualMerge = mergeResult.toString();
    if (expectedMerge == null ? actualMerge == null : expectedMerge.equals(actualMerge))
    {
      System.out.println("are the same on " + getName());
    }
    else
    {
      System.out.println("are NOT the same on " + getName());
      System.out.println("============================\nexpectedMerge\n============================\n" + expectedMerge);
      System.out.println("============================\nactualMerge\n============================\n" + actualMerge);
    }
    assertEquals("Make sure the line breaks are OK.  The expected merge should have no '\r'", expectedMerge, actualMerge);    
  }
  
  protected String mergeFiles() throws Exception
  {        
    JMerger jMerger = new JMerger();
    JControlModel controlModel = new JControlModel(mergeXML.getAbsolutePath());
    jMerger.setControlModel(controlModel);

    // set source
    jMerger.setSourceCompilationUnit(jMerger.createCompilationUnitForContents(TestUtil.readFile(source, false)));
    
    // set target
    if (target.isFile())
    {
      jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForInputStream(new FileInputStream(target)));
    }
    
    // merge source and target
    jMerger.merge();
    
    return jMerger.getTargetCompilationUnitContents();
  }  
}