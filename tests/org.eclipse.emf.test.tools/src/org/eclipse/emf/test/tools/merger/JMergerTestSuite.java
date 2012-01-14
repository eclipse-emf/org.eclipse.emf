/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.tools.merger;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

import junit.framework.TestSuite;

import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.tools.AllSuites;


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
    protected static final Pattern EXCLUDE_DATA_DIRECTORY_NAME_PATTERN = Pattern.compile("^(?:CVS)|(?:\\..*)$");
    protected static final Pattern INCLUDE_DATA_DIRECTORY_NAME_PATTERN = Pattern.compile(".*");
    // use something like this line to restrict the test
    // protected static final Pattern INCLUDE_DATA_DIRECTORY_NAME_PATTERN = Pattern.compile("(bugzilla|178183)");

    public boolean accept(File dir, String name)
    {
      // must be a directory and must match the pattern
      File dataDirectoryCandidate = new File(dir, name);
      return dataDirectoryCandidate.isDirectory() &&
        !EXCLUDE_DATA_DIRECTORY_NAME_PATTERN.matcher(name).matches() &&
        INCLUDE_DATA_DIRECTORY_NAME_PATTERN.matcher(name).matches();
    }
  }

  /**
   * Default root data directory containing java versions subdirectories.
   * <p>
   * Default is /data/merge.input
   * @see JMergerTest#DIRECTORY_NAMES_TO_JAVA_VERSIONS
   */
  protected static final File DEFAULT_ROOT_DIRECTORY = new File(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + File.separator + "data" + File.separator
    + "merge.input");

  /**
   * @param name
   */
  public JMergerTestSuite(String name)
  {
    super(name);
    populateSuite();
  }

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
        addTest(createTestSuiteRecursively(dataDirsDirectory));
      }
    }

    assertFalse("Subdirectories " + JMergerTest.DIRECTORY_NAMES_TO_JAVA_VERSIONS.keySet().toString() + " under "
      + rootDataDirsDirectory.getAbsolutePath() + " must contain subdirectories with source, target and output files.", countTestCases() == 0);
  }
  
  /**
   * Creates a test suite recursively for all directories in the directory tree.
   * Directories used as input must be accepted by {@link JMergerDataDirectoryFilter}.
   * @param directory root directory to create test suite for
   * @return resulting test suite
   */
  protected TestSuite createTestSuiteRecursively(File directory)
  {
    TestSuite thisDirectoryTest = createSingleInputTestSuite(directory);
    
    // if there are no test cases for this directory, try to create test suites from subdirectories
    if (thisDirectoryTest.countTestCases() == 0)
    {
      String[] dataDirectoriesNames = directory.list(new JMergerDataDirectoryFilter());
      
      if (dataDirectoriesNames.length > 0)
      {
        TestSuite testSuite = new TestSuite(directory.getName());
        
        Arrays.sort(dataDirectoriesNames);
  
        for (String dataDirectoryName : dataDirectoriesNames)
        {
          File dataDirectory = new File(directory, dataDirectoryName);
          testSuite.addTest(createTestSuiteRecursively(dataDirectory));
        }
        return testSuite;
      }
    }
    return thisDirectoryTest;
  }

  /**
   * Creates and returns test suite for a single input directory.
   * 
   * @param dataDirectory directory containing directory with subDirectoryName
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
