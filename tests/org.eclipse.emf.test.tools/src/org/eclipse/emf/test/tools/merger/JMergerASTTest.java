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
 * $Id: JMergerASTTest.java,v 1.9 2008/05/29 14:56:37 marcelop Exp $
 */
package org.eclipse.emf.test.tools.merger;

import java.io.File;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.ast.ASTFacadeHelper;
import org.eclipse.emf.codegen.util.CodeGenUtil;

/**
 * Each test method in this class works with same directory as {@link JMergerTest}.
 * <p>
 * This test should contain special test cases that require special code executed for them.
 * Special cases will use directory returned by {@link #getDefaultDataDirectory()}.
 * <p>
 * In addition, this test is ran automatically by {@link JMergerTestSuite} for all input directories.
 *  
 * @see #JMergerASTTest(TestSuite, File)
 */
public class JMergerASTTest extends JMergerTest
{
  /**
   * @param name
   */
  public JMergerASTTest(String name)
  {
    super(name);
  }
  
  /**
   * Adds itself to test suite if possible by {@link #addItself(TestSuite)}.
   * <p>
   * Sets test name to be <code>mergeAST</code>.
   * 
   * @param ts
   * @param dataDirectory
   * @see #mergeAST()
   */
  public JMergerASTTest(TestSuite ts, File dataDirectory)
  {
    super(ts, dataDirectory);
    setName("mergeAST");
  }

  /**
   * Name of the expected output file when AST facade implementation is used.
   * @see #getTestSpecificExpectedOutput()
   */  
  public static final String AST_EXPECTED_OUTPUT_FILENAME = "ASTMergerExpected.java";
  
  /**
   * Special test cases that are not in {@link JMergerTestSuite}
   */
  public static Test suite()
  {
    TestSuite ts = new TestSuite("JMerger AST Test");
    ts.addTest(new JMergerJDOMTest("merge4"));
    return ts;
  }    
  
  /*
   * Bugzilla 163856
   */
  public void merge4() throws Exception
  {
    applyGenModelEditorFormatting = true;
    verifyMerge(expectedOutput, mergeFiles());
  }  
  
  /**
   * Method to be used in tests created based on data directories.
   * 
   * @throws Exception
   * @see #addItself(TestSuite)
   * @see JMergerTestSuite
   */
  public void mergeAST() throws Exception
  {
    merge();
  }

  @Override
  protected void instanceTest(FacadeHelper facadeHelper)
  {
    assertTrue(ASTFacadeHelper.class.isInstance(facadeHelper));
  }

  @Override
  protected FacadeHelper instanciateFacadeHelper()
  {
    FacadeHelper facadeHelper = CodeGenUtil.instantiateFacadeHelper(ASTFacadeHelper.class.getCanonicalName());
    return facadeHelper;
  }

  @Override
  protected File getTestSpecificExpectedOutput()
  {
    return new File(getDataDirectory(), AST_EXPECTED_OUTPUT_FILENAME);
  }
}
