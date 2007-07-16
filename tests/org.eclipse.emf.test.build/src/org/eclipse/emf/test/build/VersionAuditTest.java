/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
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
 * $Id: VersionAuditTest.java,v 1.3.4.2 2007/07/16 23:05:34 nickb Exp $
 */
package org.eclipse.emf.test.build;

import java.io.File;
import java.net.HttpURLConnection;
import java.util.HashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class VersionAuditTest extends TestCase
{

  private static File buildConfigFile = new File(TestUtil.getPluginDirectory() + "/../../../../../../../build.cfg");
  
  private static String[] URLs = {
    "http://build.eclipse.org/modeling/emf/emf/versionaudit.php?branch=",
    "http://build.eclipse.org/modeling/emf/sdo/versionaudit.php?branch=",
    "http://build.eclipse.org/modeling/mdt/xsd/versionaudit.php?branch="
  };
  private static String branch = "";
  
  private HashMap pairs = new HashMap();
  
  public static Test suite()
  {
    TestSuite ts = new TestSuite("EMF Plugin / Feature Version Auditing");
    //ts.addTest(new VersionAuditTest("testWhereAmI")); /* for debugging only */
    ts.addTest(new VersionAuditTest("testVersionEMF"));
    ts.addTest(new VersionAuditTest("testVersionSDO"));
    ts.addTest(new VersionAuditTest("testVersionXSD"));
    return ts;
  }

  public VersionAuditTest(String name)
  {
    super(name);
  }

  /* 
   * Use to get absolute plugin directory; test will fail and echo path into JUnit log
   */
  public void testWhereAmI() throws Exception
  {
    assertEquals("where am I?",TestUtil.getPluginDirectory());
  }
  
  public void testLoadBuildConfigFile() throws Exception
  {
    assertTrue("Could not find " + buildConfigFile.toString(), buildConfigFile.isFile());
    pairs = TestUtil.readFileAsHash(buildConfigFile);
    assertNotNull("No data found in buildConfigFile!",pairs);
  }
  
  public void testGetBranch() throws Exception
  {
    assertNotNull("No value found for branch in " + buildConfigFile.toString() + "!", pairs.get("branch"));
    branch = (String)pairs.get("branch");
  }
  
  public void testVersionEMF() throws Exception
  {
    
    testVersion(URLs[0], branch);
  }
  
  public void testVersionSDO() throws Exception
  {
    testVersion(URLs[1], branch);
  }
  
  public void testVersionXSD() throws Exception
  {
    testVersion(URLs[2], branch);
  }
  
  private void testVersion(final String URL, final String branch) throws Exception
  {
    String data = TestUtil.slurpStream((HttpURLConnection)TestUtil.getConn(URL + branch, "GET", true, false, ""));
    assertTrue("\n\n" + data,data.equals(branch + ": ok\n\n"));
  }

  /**
   * @see TestCase#setUp()
   * 
   * Use testWhereAmI to get absolute plugin directory, eg., 
   * .../downloads/drops/2.3.1/N200707131702/testing/N200707131702/testing/target/eclipse/plugins/org.eclipse.emf.test.build_2.3.0.v200707131702
   */
  protected void setUp() throws Exception
  {
    // verify we can load build.cfg
    testLoadBuildConfigFile();
    // get a new value for branch from build.cfg HashMap
    testGetBranch();
  }
}