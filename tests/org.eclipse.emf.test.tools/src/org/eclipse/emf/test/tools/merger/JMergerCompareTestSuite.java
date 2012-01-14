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
package org.eclipse.emf.test.tools.merger;

import java.io.File;

import junit.framework.TestCase;
import junit.framework.TestSuite;


/**
 * Tests and finds differences between 
 * output of JMerger when JDOM or AST Facade is used.
 * <p>
 * This test should <b>NOT</b> be included in the build.
 * <p>
 * Since there are differences in most cases, this test is expected to fail.
 * <p>  
 * This test uses same input files and tests as {@link JMergerTestSuite}.
 * <p>
 * When both JDOM and AST can not be ran for the given input (i.e. Java 5), 
 * empty test suites are created for such directories.
 */
public class JMergerCompareTestSuite extends JMergerTestSuite
{
  /**
   * Test that runs merge on 2 tests and compares the output.
   */
  protected static class JMergerCompareTest extends TestCase
  {
    /**
     * Tests to run merge on.
     */
    JMergerTest test1, test2;

    /**
     * @param test1
     * @param test2
     */
    public JMergerCompareTest(JMergerTest test1, JMergerTest test2)
    {
      super("mergeAndCompare");
      this.test1 = test1;
      this.test2 = test2;
    }

    @Override
    protected void setUp() throws Exception
    {
      super.setUp();
      test1.setUp();
      test2.setUp();
    }

    /**
     * Run merge on both tests and compare result.
     * 
     * @throws Exception
     */
    public void mergeAndCompare() throws Exception
    {
      test1.adjustSourceCompatibility();
      String test1Output = test1.mergeFiles();

      test2.adjustSourceCompatibility();
      String test2Output = test2.mergeFiles();

      assertEquals("Output differs (expected produced by " + test1.getClass().getSimpleName() + ", actual by "
        + test2.getClass().getSimpleName() + ") ", test1Output, test2Output);
    }
  }

  public JMergerCompareTestSuite(String name)
  {
    super(name);
  }

  public static TestSuite suite()
  {
    return new JMergerCompareTestSuite("JMerger Compare Test");
  }

  @Override
  protected void addTestCases(TestSuite ts, File dataDirectory)
  {
    TestSuite dummySuite = new TestSuite();
    JMergerASTTest astTest = new JMergerASTTest(dummySuite, dataDirectory);
    JMergerJDOMTest jdomTest = new JMergerJDOMTest(dummySuite, dataDirectory);

    // if both tests can be ran, add both
    if (dummySuite.countTestCases() == 2)
    {
      ts.addTest(new JMergerCompareTest(jdomTest, astTest));
    }
  }
}
