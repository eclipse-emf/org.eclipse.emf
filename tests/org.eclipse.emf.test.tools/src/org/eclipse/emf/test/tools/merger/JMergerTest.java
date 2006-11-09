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
 * $Id: JMergerTest.java,v 1.15 2006/11/09 14:49:50 marcelop Exp $
 */
package org.eclipse.emf.test.tools.merger;

import java.io.FileInputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jdt.core.JavaCore;

import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.codegen.merge.java.JMerger;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.jdom.JDOMFacadeHelper;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.test.tools.TestUtil;

/**
 * Each test method in this class works with a data/mergeN directory.
 */
public class JMergerTest extends DeprecatedJMergerTest
{
  /**
   * @param name
   */
  public JMergerTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite superTS = (TestSuite)DeprecatedJMergerTest.suite();
    TestSuite ts = new TestSuite("JMergerTest");
    
    for (int i=0; i<superTS.testCount(); i++)
    {
      String name = ((TestCase)superTS.testAt(i)).getName();
      ts.addTest(new JMergerTest(name));
    }
    ts.addTest(new JMergerTest("merge4"));
    return ts;
  }  
  
  protected FacadeHelper instanciateFacadeHelper()
  {
    FacadeHelper facadeHelper = CodeGenUtil.instantiateFacadeHelper(JDOMFacadeHelper.class.getCanonicalName());
    return facadeHelper;
  }
  
  protected void instanceTest(FacadeHelper facadeHelper)
  {
  	assertTrue(JDOMFacadeHelper.class.isInstance(facadeHelper));
  }
  
  /*
   * Bugzilla 163856
   */
  public void merge4() throws Exception
  {  
    adjustSourceCompatibility("1.5");
    applyGenModelEditorFormatting = true;
    verifyMerge(mergeFiles());
  }  
  
  protected String mergeFiles() throws Exception
  {
    String sourceCompatibility = JavaCore.getOption(JavaCore.COMPILER_SOURCE); 
    
    FacadeHelper facadeHelper = instanciateFacadeHelper();
    instanceTest(facadeHelper);

    JControlModel controlModel = new JControlModel();
    assertFalse(controlModel.canMerge());
    controlModel.initialize(facadeHelper, mergeXML.getAbsolutePath());
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
}