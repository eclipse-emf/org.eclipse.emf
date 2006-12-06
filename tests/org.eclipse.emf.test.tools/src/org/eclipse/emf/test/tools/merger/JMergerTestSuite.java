/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: JMergerTestSuite.java,v 1.2 2006/12/06 03:53:53 marcelop Exp $
 */
package org.eclipse.emf.test.tools.merger;

import static junit.framework.Assert.*;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

import junit.framework.TestSuite;

import org.eclipse.emf.test.tools.TestUtil;


/**
 * Runs JDOM {@link JMergerJDOMTest} or AST {@link JMergerASTTest} or both 
 * for each data directory <code>/data/merge.input/&lt;java version&gt;/merge*</code>.
 * <p>
 * Each test determines if it can be ran for each data directory.
 * 
 * @see JMergerTest#DIRECTORY_NAMES_TO_JAVA_VERSIONS
 * @see JMergerJDOMTest#JMergerJDOMTest(TestSuite, File)
 * @see JMergerASTTest#JMergerASTTest(TestSuite, File)
 */
public class JMergerTestSuite extends TestSuite
{
  /**
   * Filter for the directories that will be used as data directories for tests.
   */
  protected static class JMergerDataDirectoryFilter implements FilenameFilter
  {
    protected static final Pattern DATA_DIRECTORY_NAME_PATTERN = Pattern.compile("^merge.*$");

    public boolean accept(File dir, String name)
    {
      // must be a directory and must match the pattern
      File dataDirectoryCandidate = new File(dir, name);
      if (dataDirectoryCandidate.isDirectory() && DATA_DIRECTORY_NAME_PATTERN.matcher(name).matches())
      {
        return true;
      }
      else
      {
        return false;
      }
    }
  }

  /**
   * Default root data directory containing java versions subdirectories.
   * <p>
   * Default is /data/merge.input
   * @see JMergerTest#DIRECTORY_NAMES_TO_JAVA_VERSIONS
   */
  protected static final File DEFAULT_ROOT_DIRECTORY = new File(TestUtil.getPluginDirectory() + File.separator + "data" + File.separator
    + "merge.input");

  /**
   * @param name
   */
  public JMergerTestSuite(String name)
  {
    super(name);
    populateSuite();
  }

  /**
   * @return
   */
  public static TestSuite suite()
  {
    return new JMergerTestSuite("JMerger Test Suite");
  }

  /**
   * @return root data directory containing subdirectories for different versions of Java 
   * @see JMergerTest#DIRECTORY_NAMES_TO_JAVA_VERSIONS
   */
  protected File determineRootDataDirectory()
  {
    return DEFAULT_ROOT_DIRECTORY;
  }

  /**
   * Populates suite with test cases for each data directory.
   */
  protected void populateSuite()
  {
    File rootDataDirsDirectory = determineRootDataDirectory();
    assertTrue("Directory " + determineRootDataDirectory().getAbsolutePath() + " does not exist.", rootDataDirsDirectory.exists());
    assertTrue("Directory " + determineRootDataDirectory().getAbsolutePath() + " is not a directory.", rootDataDirsDirectory.isDirectory());

    // loop for all directories of all java versions
    for (String javaVersionDirectoryName : JMergerTest.DIRECTORY_NAMES_TO_JAVA_VERSIONS.keySet())
    {
      File dataDirsDirectory = new File(rootDataDirsDirectory, javaVersionDirectoryName);

      if (dataDirsDirectory.isDirectory())
      {
        TestSuite javaVersionTestSuite = new TestSuite(javaVersionDirectoryName);
        // add tests for subdirectories matching the filter      
        String[] dataDirectoriesNames = dataDirsDirectory.list(new JMergerDataDirectoryFilter());
  
        Arrays.sort(dataDirectoriesNames);
  
        for (String dataDirectoryName : dataDirectoriesNames)
        {
          File dataDirectory = new File(dataDirsDirectory, dataDirectoryName);
          javaVersionTestSuite.addTest(createSingleInputTestSuite(dataDirectory));
        }
        addTest(javaVersionTestSuite);
      }
    }

    assertFalse("Subdirectories " + JMergerTest.DIRECTORY_NAMES_TO_JAVA_VERSIONS.keySet().toString() + " under "
      + rootDataDirsDirectory.getAbsolutePath() + " must contain merge* subdirectories.", countTestCases() == 0);
  }

  /**
   * Creates and returns test suite for a single input directory.
   * 
   * @param subDirectoryName
   * @param dataDirsDirectory directory containing directory with subDirectoryName
   * @return
   */
  protected TestSuite createSingleInputTestSuite(File dataDirectory)
  {
    TestSuite ts = new TestSuite(dataDirectory.getName());
    addTestCases(ts, dataDirectory);
    return ts;
  }

  /**
   * Adds tests that can be ran for the given data directory to the test suite. 
   * 
   * @param ts test suite to add tests to
   * @param dataDirectory
   */
  protected void addTestCases(TestSuite ts, File dataDirectory)
  {
    // create and, if possible, add test cases to the suite
    new JMergerASTTest(ts, dataDirectory);
    new JMergerJDOMTest(ts, dataDirectory);
  }
}
