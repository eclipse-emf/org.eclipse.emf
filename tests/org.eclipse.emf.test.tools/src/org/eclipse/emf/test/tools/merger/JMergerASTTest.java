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
 * $Id: JMergerASTTest.java,v 1.6 2006/11/16 20:13:07 marcelop Exp $
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
    TestSuite ts = new TestSuite("JMergerASTTest");
    
    ts.addTest(new JMergerASTTest("merge0"));
    ts.addTest(new JMergerASTTest("merge1"));
    ts.addTest(new JMergerASTTest("merge2"));
    ts.addTest(new JMergerASTTest("merge3"));
    ts.addTest(new JMergerASTTest("merge4"));
    ts.addTest(new JMergerASTTest("merge5"));
    ts.addTest(new JMergerASTTest("merge6"));
    ts.addTest(new JMergerASTTest("merge7"));
    ts.addTest(new JMergerASTTest("merge8"));
    ts.addTest(new JMergerASTTest("merge9"));
    ts.addTest(new JMergerASTTest("merge10"));
    ts.addTest(new JMergerASTTest("merge11"));
    return ts;
  }  
  
  @Override
  protected String getDataDirectory()
  {
    return TestUtil.getPluginDirectory() + "/data/ast_merge/" + getName();
  }
  
  @Override
  protected FacadeHelper instanciateFacadeHelper()
  {
  	return CodeGenUtil.instantiateFacadeHelper(ASTFacadeHelper.class.getCanonicalName());
  }
  
  @Override
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
  
  /*
   * Bugzilla 164683
   */
  public void merge9() throws Exception
  {
    verifyMerge(mergeFiles());
  }  

  public void merge10() throws Exception
  {
    verifyMerge(mergeFiles());
  }  

  /*
   * Bugzilla 164872
   */
  public void merge11() throws Exception
  {
    verifyMerge(mergeFiles());
  }  
}
