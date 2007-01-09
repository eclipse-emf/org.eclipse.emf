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
 * $Id: EcoreTest.java,v 1.1.2.2 2007/01/09 06:10:43 davidms Exp $
 */
package org.eclipse.emf.test.core.ecore;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EcoreFactory;

public class EcoreTest extends TestCase
{
  public EcoreTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("EcoreTest");
    ts.addTest(new EcoreTest("testCreateAnnotationOnInitialization"));
    return ts;
  }

  /**
   * Bug 169780
   * This must be run before any other tests using Ecore, since it will always pass if EcorePackage has been initialized.
   */
  public void testCreateAnnotationOnInitialization()
  {
    EAnnotation annotation = EcoreFactory.eINSTANCE.createEAnnotation();
    annotation.setSource("XTest");
    annotation.getDetails().put("Test", "true");
    assertEquals("true", annotation.getDetails().get("Test"));
  }
}
