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
 * $Id: JMergerTest.java,v 1.10 2006/04/03 21:06:45 marcelop Exp $
 */
package org.eclipse.emf.test.tools.merger;

import java.io.FileInputStream;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
    return ts;
  }  
  
  protected String mergeFiles() throws Exception
  {
    FacadeHelper facadeHelper = CodeGenUtil.instantiateFacadeHelper(JMerger.DEFAULT_FACADE_HELPER_CLASS);
    assertTrue(facadeHelper instanceof JDOMFacadeHelper);
    
    JControlModel controlModel = new JControlModel();
    assertFalse(controlModel.canMerge());
    controlModel.initialize(facadeHelper, mergeXML.getAbsolutePath());
    assertTrue(controlModel.canMerge());
    JMerger jMerger = new JMerger(controlModel);

    // set source
    jMerger.setSourceCompilationUnit(jMerger.createCompilationUnitForContents(TestUtil.readFile(source, false)));
    
    // set target
    if (target.isFile())
    {
      jMerger.setTargetCompilationUnit(jMerger.createCompilationUnitForInputStream(new FileInputStream(target)));
    }
    
    // merge source and target
    jMerger.merge();
    
    return jMerger.getTargetCompilationUnitContents();
  }  
}