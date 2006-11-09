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
 * $Id: DeprecatedJMergerTest.java,v 1.9 2006/11/09 03:37:38 marcelop Exp $
 */
package org.eclipse.emf.test.tools.merger;

import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants;

import org.eclipse.emf.codegen.jmerge.JControlModel;
import org.eclipse.emf.codegen.jmerge.JMerger;
import org.eclipse.emf.common.CommonPlugin;
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
  
  protected boolean applyGenModelEditorFormatting = false;
  
  /**
   * @param name
   */
  public DeprecatedJMergerTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("DeprecatedJMergerTest");
    ts.addTest(new DeprecatedJMergerTest("merge0"));
    ts.addTest(new DeprecatedJMergerTest("merge1"));
    ts.addTest(new DeprecatedJMergerTest("merge2"));
    ts.addTest(new DeprecatedJMergerTest("merge3"));
    return ts;
  }
  
  protected String getDataDirectory()
  {
    return TestUtil.getPluginDirectory() + "/data/" + getName();
  }
  
  protected void setUp() throws Exception
  {
    String dir = getDataDirectory(); 

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
  
  protected void applyGenModelEditorFormattingSettings(org.eclipse.emf.codegen.merge.java.JControlModel jControlModel)
  {
    if (CommonPlugin.IS_ECLIPSE_RUNNING)
    {
      Map options = JavaCore.getOptions();
      String tabSize = (String)options.get(DefaultCodeFormatterConstants.FORMATTER_TAB_SIZE);
      String braceStyle = (String)options.get(DefaultCodeFormatterConstants.FORMATTER_BRACE_POSITION_FOR_TYPE_DECLARATION);
      String tabCharacter = (String)options.get(DefaultCodeFormatterConstants.FORMATTER_TAB_CHAR);
      if (JavaCore.TAB.equals(tabCharacter))
      {
         jControlModel.setLeadingTabReplacement("\t");
      }
      else
      {
        String spaces = "";
        for (int i = Integer.parseInt(tabSize); i > 0; --i)
        {
          spaces += " ";
        }
        jControlModel.setLeadingTabReplacement(spaces);
      }
      jControlModel.setConvertToStandardBraceStyle(DefaultCodeFormatterConstants.END_OF_LINE.equals(braceStyle));
    }
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
    assertEquals("Make sure the line breaks are OK.  The expected merge should have no '\r'", expectedMerge, actualMerge);    
  }
  
  protected String mergeFiles() throws Exception
  {   
    String sourceCompatibility = JavaCore.getOption(JavaCore.COMPILER_SOURCE);
    if ("1.3".compareTo(sourceCompatibility) < 0)
    {
      Hashtable map = JavaCore.getOptions();
      map.put(JavaCore.COMPILER_SOURCE, "1.3");
      JavaCore.setOptions(map);    
    }
    else
    {
      sourceCompatibility = null;
    }
    
    JMerger jMerger = new JMerger();
    JControlModel controlModel = new JControlModel(mergeXML.getAbsolutePath());
    jMerger.setControlModel(controlModel);
    if (applyGenModelEditorFormatting)
    {
      applyGenModelEditorFormattingSettings(controlModel);
    }

    // set source
    jMerger.setSourceCompilationUnit(jMerger.createCompilationUnitForContents(TestUtil.readFile(source, false)));
    
    // set target
    if (target.isFile())
    {
      jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForInputStream(new FileInputStream(target)));
    }
    
    // merge source and target
    jMerger.merge();
    
    if (sourceCompatibility != null)
    {
      Hashtable map = JavaCore.getOptions();
      map.put(JavaCore.COMPILER_SOURCE, sourceCompatibility);
      JavaCore.setOptions(map);    
    }    
    return jMerger.getTargetCompilationUnitContents();
  }  
}