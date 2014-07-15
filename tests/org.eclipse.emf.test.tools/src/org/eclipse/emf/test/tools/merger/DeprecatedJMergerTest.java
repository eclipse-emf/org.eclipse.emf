/**
 * Copyright (c) 2004-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.tools.merger;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;

import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.tools.AllSuites;
import org.eclipse.jdt.core.JavaCore;
import org.junit.Test;


/**
 * Each test method in this class works with a data/mergeN directory.
 */
public class DeprecatedJMergerTest
{
  protected File mergeXML;
  protected File source;
  protected File target;
  protected File expected;

  protected String getDataDirectory(String name)
  {
    return TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/merge.input/java1.4/deprecated/" + name;
  }

  protected void setUp(String name) throws Exception
  {
    String dir = getDataDirectory(name);

    mergeXML = new File(dir + "/merge.xml").getAbsoluteFile();
    assertTrue("Merge xml file is not available - " + mergeXML.getAbsolutePath(), mergeXML.isFile());

    source = new File(dir + "/MergerSource.java").getAbsoluteFile();
    assertTrue("Merge Source file is not available - " + source.getAbsolutePath(), source.isFile());

    target = new File(dir + "/MergerTarget.java").getAbsoluteFile();

    expected = new File(dir + "/DeprecatedMergerExpected.java").getAbsoluteFile();
    if (!expected.isFile())
    {
      expected = new File(dir + "/MergerExpected.java").getAbsoluteFile();
      assertTrue("Merge Result file is not available - " + expected.getAbsolutePath(), expected.isFile());
    }
  }

  @Test
  public void merge0() throws Exception
  {
    setUp("merge0");
    verifyMerge(mergeFiles());
  }

  /**
   * This test case was based on the excellent article written by Adrian Powell
   * Model with the Eclipse Modeling Framework, Part 3
   * @see <a href="http://www.ibm.com/developerworks/library/os-ecemf3/">JMerge</a>
   */
  @Test
  public void merge1() throws Exception
  {
    setUp("merge2");
    verifyMerge(mergeFiles());
  }

  /*
   * Bugzilla 126175
   */
  @Test
  public void merge2() throws Exception
  {
    setUp("merge2");
    verifyMerge(mergeFiles());
  }

  @Test
  public void merge3() throws Exception
  {
    setUp("merge3");
    verifyMerge(mergeFiles());
  }

  protected void verifyMerge(String targetContents)
  {
    JMergerTest.verifyMerge(expected, targetContents);
  }

  @SuppressWarnings({"unchecked", "deprecation", "rawtypes"})
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

    org.eclipse.emf.codegen.jmerge.JMerger jMerger = new org.eclipse.emf.codegen.jmerge.JMerger();
    org.eclipse.emf.codegen.jmerge.JControlModel controlModel = new org.eclipse.emf.codegen.jmerge.JControlModel(mergeXML.getAbsolutePath());
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

    if (sourceCompatibility != null)
    {
      Hashtable map = JavaCore.getOptions();
      map.put(JavaCore.COMPILER_SOURCE, sourceCompatibility);
      JavaCore.setOptions(map);
    }
    return jMerger.getTargetCompilationUnitContents();
  }
}