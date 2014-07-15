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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.codegen.merge.java.JMerger;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.tools.AllSuites;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants;
import org.junit.Before;
import org.junit.ComparisonFailure;
import org.junit.Test;


/**
 * Base class for all JMerger tests.
 * <p>
 * For special test cases, default data directory is determined by {@link #getDefaultDataDirectory()}.
 * For tests created by <code>JMergerTestSuite</code>, see {@link JMergerTestSuite}.
 * <p>
 * Merge source and target files are respectively <code>MergerSource.java</code> and <code>MergerTarget.java</code> in data directory.
 * <p>
 * Expected output file is either test specific output file (determined by {@link #getTestSpecificExpectedOutput()} or
 * <code>MergerExpected.java</code> in data directory.
 * <p>
 * Merge rules are used from <code>merge.xml</code> file in data directory if it exists, otherwise default EMF merge rules are used.
 */
public abstract class JMergerTest
{
  /**
   * Default root data directory containing java versions subdirectories.
   * <p>
   * Default is /data/merge.input
   * @see JMergerTest#DIRECTORY_NAMES_TO_JAVA_VERSIONS
   */
  protected static final File DEFAULT_ROOT_DIRECTORY = new File(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + File.separator + "data" + File.separator + "merge.input");

  /**
   * Default name of the expected output file.
   */
  public static final String DEFAULT_EXPECTED_OUTPUT_FILENAME = "MergerExpected.java";

  /**
   * Map of directory names to versions of Java to be used to overwrite settings in JavaCore.
   */
  public static final Map<String, String> DIRECTORY_NAMES_TO_JAVA_VERSIONS;

  static
  {
    Map<String, String> directoryNamesMap = new HashMap<String, String>(4);
    directoryNamesMap.put("java1.4", JavaCore.VERSION_1_4);
    directoryNamesMap.put("java5", JavaCore.VERSION_1_5);
    DIRECTORY_NAMES_TO_JAVA_VERSIONS = Collections.unmodifiableMap(directoryNamesMap);
  }

  public static Collection<Object[]> parameters(String prefix)
  {
    return populateSuite(prefix);
  }

  /**
   * Verifies that target contents matches the contents of expected output file.
   *
   * @param expectedOutput
   * @param targetContents
   */
  protected static void verifyMerge(File expectedOutput, String targetContents)
  {
    // extract merged contents
    StringBuilder mergeResult = new StringBuilder(targetContents);

    // The merge expected file was saved without any '\r' so
    // we need to remove it from the mergedResult
    for (int i = mergeResult.length() - 1; i >= 0; i--)
    {
      if ('\r' == mergeResult.charAt(i))
      {
        mergeResult.deleteCharAt(i);
      }
    }

    String expectedMerge = TestUtil.readFile(expectedOutput, false);
    String actualMerge = mergeResult.toString();
    try
    {
      assertEquals("Make sure the line breaks are OK.  The expected merge should have no '\\r'", expectedMerge, actualMerge);
    }
    catch (ComparisonFailure exception)
    {
      File alternative = new File(expectedOutput.toString().replace(".java", "Alt.java"));
      if (alternative.exists())
      {
        expectedMerge = TestUtil.readFile(alternative, false);
        assertEquals("Make sure the line breaks are OK.  The expected merge should have no '\\r'", expectedMerge, actualMerge);
      }
      else
      {
        throw exception;
      }
    }
  }

  /**
   * If <code>true</code>, editor options are set from default options in genmodel.
   */
  protected boolean applyGenModelEditorFormatting = false;

  protected File dataDirectory = null;

  protected File expectedOutput = null;

  /**
   * URI of merge rules file to be used by {@link JControlModel#initialize(FacadeHelper, String)}
   */
  protected String mergeRulesURI = null;

  protected File source = null;

  protected File target = null;

  public JMergerTest(File dataDirectory)
  {
    this.dataDirectory = dataDirectory;
  }

  /**
   * @param javaVersion (one of {@link JavaCore#VERSION_1_1} to {@link JavaCore#VERSION_1_6}
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  protected void adjustSourceCompatibility(String javaVersion)
  {
    Hashtable map = JavaCore.getOptions();
    map.put(JavaCore.COMPILER_SOURCE, javaVersion);
    JavaCore.setOptions(map);
  }

  /**
   * Adjusts JavaCore source compatibility options based on {@link #computeJavaVersion()}.
   *
   * @see #adjustSourceCompatibility(String)
   */
  protected void adjustSourceCompatibility()
  {
    String javaVersion = computeJavaVersion();
    assertNotNull("Unable to determine Java version from directory name. ", javaVersion);
    adjustSourceCompatibility(javaVersion);
  }

  /**
   * Determines java version based on the name of the parent of data directory.
   * <p>
   * Parent directory must match one of {@link #DIRECTORY_NAMES_TO_JAVA_VERSIONS}
   * @return java version or <code>null</code> if can not be determined
   */
  protected String computeJavaVersion()
  {
    return computeJavaVersion(dataDirectory);
  }

  protected static String computeJavaVersion(File directory)
  {
    File parentDirectory = directory;
    String javaVersion = null;
    do
    {
      javaVersion = DIRECTORY_NAMES_TO_JAVA_VERSIONS.get(parentDirectory.getName());
      parentDirectory = parentDirectory.getParentFile();
    }
    while (javaVersion == null && parentDirectory != null);
    return javaVersion;
  }

  /**
   * @return test specific expected output file or default expected output file, but never <code>null</code>.
   * @see #getTestSpecificExpectedOutput()
   */
  protected File computeExpectedOutputFile()
  {
    File expectedOutput = getTestSpecificExpectedOutput();
    if (expectedOutput != null && expectedOutput.exists())
    {
      return expectedOutput;
    }
    else
    {
      return new File(dataDirectory, DEFAULT_EXPECTED_OUTPUT_FILENAME).getAbsoluteFile();
    }
  }

  /**
   * Returns unique name for the expected output file.
   * <p>
   * Expected to be overwritten by subclasses.
   * <p>
   * This implementation returns <code>null</code>.
   * @return expected output file, or <code>null</code> if only default file should be used
   */
  protected File getTestSpecificExpectedOutput()
  {
    return null;
  }

  /**
   * @param jControlModel
   */
  @SuppressWarnings("rawtypes")
  protected void applyGenModelEditorFormattingSettings(org.eclipse.emf.codegen.merge.java.JControlModel jControlModel)
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
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

  /**
   * @return URI of EMF merge rules resource
   */
  protected String getEMFMergeRulesURI()
  {
    // create model
    GenModel genModel = GenModelFactory.eINSTANCE.createGenModel();

    // create adapter factory
    GeneratorAdapterFactory adapterFactory = GenModelGeneratorAdapterFactory.DESCRIPTOR.createAdapterFactory();
    adapterFactory.setGenerator(new Generator());
    adapterFactory.initialize(genModel);

    // get merge rules URI
    return adapterFactory.getGenerator().getOptions().mergeRulesURI;
  }

  /**
   * @return the expectedOutput
   */
  public File getExpectedOutput()
  {
    return expectedOutput;
  }

  /**
   * @return the mergeRulesURI
   */
  public String getMergeRulesURI()
  {
    return mergeRulesURI;
  }

  /**
   * @param expectedOutput the expectedOutput to set
   */
  public void setExpectedOutput(File expectedOutput)
  {
    this.expectedOutput = expectedOutput;
  }

  /**
   * @param mergeRulesURI the mergeRulesURI to set
   */
  public void setMergeRulesURI(String mergeRulesURI)
  {
    this.mergeRulesURI = mergeRulesURI;
  }

  /**
   * Tests whether the facade helper is of correct type
   * @param facadeHelper
   */
  protected abstract void instanceTest(FacadeHelper facadeHelper);

  /**
   * @return facade helper instance
   */
  protected abstract FacadeHelper instanciateFacadeHelper();

  /**
   * Perform and verify merge. To be used in merge tests by subclasses.
   * <b>
   * Before performing merge, java compiler source version is set based on data directory.
   *
   * @throws Exception
   * @see #adjustSourceCompatibility()
   */
  @Test
  public void merge() throws Exception
  {
    adjustSourceCompatibility();
    verifyMerge(expectedOutput, mergeFiles());
  }

  /**
   * @return contents after merging contents of source and target files
   * @throws Exception
   */
  protected String mergeFiles() throws Exception
  {
    String sourceCompatibility = JavaCore.getOption(JavaCore.COMPILER_SOURCE);

    FacadeHelper facadeHelper = instanciateFacadeHelper();
    instanceTest(facadeHelper);

    JControlModel controlModel = new JControlModel();
    assertFalse(controlModel.canMerge());
    controlModel.initialize(facadeHelper, mergeRulesURI);
    if (applyGenModelEditorFormatting)
    {
      applyGenModelEditorFormattingSettings(controlModel);
    }
    assertTrue(controlModel.canMerge());
    JMerger jMerger = new JMerger(controlModel);

    // set source
    jMerger.setSourceCompilationUnit(jMerger.createCompilationUnitForContents(TestUtil.readFile(source, true)));

    // set target
    if (target.isFile())
    {
      jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForInputStream(new FileInputStream(target)));
    }

    // merge source and target
    jMerger.merge();

    // Ensure the facade is returning the COMPILER_SOURCE to the original value.
    assertEquals(sourceCompatibility, JavaCore.getOption(JavaCore.COMPILER_SOURCE));

    return jMerger.getTargetCompilationUnitContents();
  }

  /**
   * Sets up data directory, source, target, expected output, and merge rules attributes.
   * <p>
   * If <code>merge.xml</code> is not available in data directory, default EMF merge rules are used.
   *
   * @see #getDataDirectory()
   * @see #computeExpectedOutputFile()
   * @see #getEMFMergeRulesURI()
   */
  @Before
  public void setUp() throws Exception
  {
    assertTrue("Merge Data directory is not available - " + dataDirectory.getAbsolutePath(), dataDirectory.isDirectory());

    if (mergeRulesURI == null)
    {
      File mergeXML = new File(dataDirectory, "merge.xml").getAbsoluteFile();
      if (!mergeXML.exists())
      {
        mergeRulesURI = getEMFMergeRulesURI();
      }
      else
      {
        mergeRulesURI = mergeXML.getAbsolutePath();
      }
    }

    source = new File(dataDirectory, "MergerSource.java").getAbsoluteFile();
    assertTrue("Merge Source file is not available - " + source.getAbsolutePath(), source.isFile());

    target = new File(dataDirectory, "MergerTarget.java").getAbsoluteFile();

    if (expectedOutput == null)
    {
      expectedOutput = computeExpectedOutputFile();
    }
    assertTrue("Merge Result file is not available - " + expectedOutput.getAbsolutePath(), expectedOutput.isFile());
  }

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

  private static final FilenameFilter FILTER = new JMergerDataDirectoryFilter();

  /**
   * Populates suite with test cases for each data directory.
   * @param prefix
   */
  private static Collection<Object[]> populateSuite(String prefix)
  {
    assertTrue("Directory " + DEFAULT_ROOT_DIRECTORY.getAbsolutePath() + " does not exist.", DEFAULT_ROOT_DIRECTORY.exists());
    assertTrue("Directory " + DEFAULT_ROOT_DIRECTORY.getAbsolutePath() + " is not a directory.", DEFAULT_ROOT_DIRECTORY.isDirectory());

    Collection<Object[]> tests = new ArrayList<Object[]>();
    // Loop for all directories of all java versions.
    for (String javaVersionDirectoryName : JMergerTest.DIRECTORY_NAMES_TO_JAVA_VERSIONS.keySet())
    {
      File dataDirsDirectory = new File(DEFAULT_ROOT_DIRECTORY, javaVersionDirectoryName);
      if (dataDirsDirectory.isDirectory())
      {
        createTestSuiteRecursively(prefix, tests, dataDirsDirectory, "");
      }
    }

    assertFalse("Subdirectories " + JMergerTest.DIRECTORY_NAMES_TO_JAVA_VERSIONS.keySet().toString() + " under "
      + DEFAULT_ROOT_DIRECTORY.getAbsolutePath() + " must contain subdirectories with source, target and output files.", tests.isEmpty());

    return tests;
  }

  /**
   * Creates a test suite recursively for all directories in the directory tree.
   * Directories used as input must be accepted by {@link JMergerDataDirectoryFilter}.
   * @param prefix
   * @param rootDirectory root directory to create test suite for
   * @return resulting test suite
   */
  private static void createTestSuiteRecursively(String prefix, Collection<Object[]> result, File rootDirectory, String subDirectory)
  {
    // If there are no test cases for this directory, try to create test suites from subdirectories.
    Object[] test = createSingleInputTestSuite(prefix, rootDirectory, subDirectory);
    if (test == null)
    {
      String[] dataDirectoriesNames = new File(rootDirectory, subDirectory).list(FILTER);
      if (dataDirectoriesNames.length > 0)
      {
        Arrays.sort(dataDirectoriesNames);
        for (String dataDirectoryName : dataDirectoriesNames)
        {
          String subSubDirectory = subDirectory.length() == 0 ? dataDirectoryName : subDirectory + "/" + dataDirectoryName;
          File dataDirectory = new File(rootDirectory, subSubDirectory);
          if (!prefix.equals("JDOM") || !"1.5".equals(computeJavaVersion(dataDirectory)))
          {
            createTestSuiteRecursively(prefix, result, rootDirectory, subSubDirectory);
          }
        }
      }
    }
    else
    {
      result.add(test);
    }
  }

  /**
   * Creates and returns test suite for a single input directory, or null if it's not appropriate for the prefix.
   */
  private static Object[] createSingleInputTestSuite(String prefix, File rootDirectory, String subDirectory)
  {
    File dataDirectory = new File(rootDirectory, subDirectory);
    if (new File(dataDirectory, "MergerExpected.java").isFile() || new File(dataDirectory, prefix + "MergerExpected.java").isFile())
    {
      return new Object[] { subDirectory, dataDirectory };
    }
    else
    {
      return null;
    }
  }
}