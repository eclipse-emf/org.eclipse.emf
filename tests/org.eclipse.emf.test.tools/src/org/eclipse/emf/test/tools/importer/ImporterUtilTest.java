/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * ImporterUtilTest.java,v 1.1 2005/05/16 14:16:30 marcelop Exp
 */
package org.eclipse.emf.test.tools.importer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.importer.util.ImporterUtil;

public class ImporterUtilTest extends TestCase
{
  /**
   * @param name
   */
  public ImporterUtilTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("ImporterUtilTest");
    ts.addTest(new ImporterUtilTest("testValidPluginID"));
    return ts;
  }
  
  public void testValidPluginID()
  {
    assertEquals("ab.c.12", ImporterUtil.validPluginID("ab.c.12"));
    assertEquals("AB-c_DE", ImporterUtil.validPluginID("AB-c_DE"));
    assertEquals("a_b._c._1_2", ImporterUtil.validPluginID("a b. c. 1 2"));
    assertEquals("a-b_.c.-1_2", ImporterUtil.validPluginID("a-b .c.-1 2"));
    assertEquals("a-b.c.-12", ImporterUtil.validPluginID("a-b�.c.-1�2"));
    assertEquals("_", ImporterUtil.validPluginID("� �"));
  }
}