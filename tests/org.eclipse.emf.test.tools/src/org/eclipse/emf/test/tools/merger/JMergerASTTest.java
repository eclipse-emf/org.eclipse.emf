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
 * $Id: JMergerASTTest.java,v 1.3 2006/11/09 03:36:39 marcelop Exp $
 */
package org.eclipse.emf.test.tools.merger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.ast.ASTFacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.jdom.JDOMFacadeHelper;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.test.tools.TestUtil;

/**
 * Each test method in this class works with a data/ast_merge/mergeN directory.
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

  public static Test suite()
  {
    TestSuite superTS = (TestSuite)JMergerTest.suite();
    TestSuite ts = new TestSuite("JMergerASTTest");
    
    for (int i=0; i<superTS.testCount(); i++)
    {
      String name = ((TestCase)superTS.testAt(i)).getName();
      ts.addTest(new JMergerASTTest(name));
    }
    ts.addTest(new JMergerASTTest("merge4"));
    ts.addTest(new JMergerASTTest("merge5"));
    ts.addTest(new JMergerASTTest("merge6"));
    ts.addTest(new JMergerASTTest("merge7"));
    ts.addTest(new JMergerASTTest("merge8"));
    return ts;
  }  
  
  protected String getDataDirectory()
  {
    return TestUtil.getPluginDirectory() + "/data/ast_merge/" + getName();
  }
  
  protected FacadeHelper instanciateFacadeHelper()
  {
  	return CodeGenUtil.instantiateFacadeHelper(ASTFacadeHelper.class.getCanonicalName());
  }
  
  protected void instanceTest(FacadeHelper facadeHelper)
  {
  	assertTrue(ASTFacadeHelper.class.isInstance(facadeHelper));
  }
  
  public void merge4() throws Exception
  {
    verifyMerge(mergeFiles());
  }

  public void merge5() throws Exception
  {
    verifyMerge(mergeFiles());
  }
  
  public void merge6() throws Exception
  {
    verifyMerge(mergeFiles());
  }
  
  public void merge7() throws Exception
  {
    verifyMerge(mergeFiles());
  }
  
  /*
   * Bugzilla 163856
   */
  public void merge8() throws Exception
  {
    applyGenModelEditorFormatting = true;
    verifyMerge(mergeFiles());
  }     
}
