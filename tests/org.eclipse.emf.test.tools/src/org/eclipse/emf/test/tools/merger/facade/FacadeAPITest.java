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
 * $Id: FacadeAPITest.java,v 1.1 2006/12/06 03:54:34 marcelop Exp $
 */

package org.eclipse.emf.test.tools.merger.facade;

import org.eclipse.jdt.core.Flags;

import org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl;
import org.eclipse.emf.codegen.merge.java.JMerger;
import org.eclipse.emf.codegen.merge.java.facade.FacadeFlags;
import org.eclipse.emf.codegen.merge.java.facade.ast.ASTFacadeHelper;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @since 2.2.0
 */
public class FacadeAPITest extends TestCase
{
  public FacadeAPITest(String name)
  {
    super(name);
  }
  
  public static Test suite()
  {
    TestSuite ts = new TestSuite("FacadeAPITest");
    ts.addTest(new FacadeAPITest("testFacadeFlags"));
    ts.addTest(new FacadeAPITest("testGenModelDefaultFacadeClass"));
    ts.addTest(new FacadeAPITest("testASTFacadeHelperDebugFlag"));
    return ts;
  }
  
  /*
   * Ensures that the JDT and Facade flags have the same value 
   */
  public void testFacadeFlags()
  {
    assertEquals(Flags.AccAbstract, FacadeFlags.ABSTRACT);
    assertEquals(Flags.AccAnnotation, FacadeFlags.ANNOTATION);
    assertEquals(Flags.AccBridge, FacadeFlags.BRIDGE);
    assertEquals(Flags.AccDefault, FacadeFlags.DEFAULT);
    assertEquals(Flags.AccDeprecated, FacadeFlags.DEPRECATED);
    assertEquals(Flags.AccEnum, FacadeFlags.ENUM);
    assertEquals(Flags.AccFinal, FacadeFlags.FINAL);
    assertEquals(Flags.AccInterface, FacadeFlags.INTERFACE);
    assertEquals(Flags.AccNative, FacadeFlags.NATIVE);
    assertEquals(Flags.AccPrivate, FacadeFlags.PRIVATE);
    assertEquals(Flags.AccProtected, FacadeFlags.PROTECTED);
    assertEquals(Flags.AccPublic, FacadeFlags.PUBLIC);
    assertEquals(Flags.AccStatic, FacadeFlags.STATIC);
    assertEquals(Flags.AccStrictfp, FacadeFlags.STRICTFP);
    assertEquals(Flags.AccSuper, FacadeFlags.SUPER);
    assertEquals(Flags.AccSynchronized, FacadeFlags.SYNCHRONIZED);
    assertEquals(Flags.AccSynthetic, FacadeFlags.SYNTHETIC);
    assertEquals(Flags.AccTransient, FacadeFlags.TRANSIENT);
    assertEquals(Flags.AccVarargs, FacadeFlags.VARARGS);
    assertEquals(Flags.AccVolatile, FacadeFlags.VOLATILE);
  }

  private static class MyGenModel extends GenModelImpl
  {
    public static final String PUBLIC_FACADE_HELPER = FACADE_HELPER_CLASS_EDEFAULT;
  }
 
  /*
   * Ensures that the GenModel's and JMerger facade helper are the same 
   */
  public void testGenModelDefaultFacadeClass()
  {
    assertEquals(JMerger.DEFAULT_FACADE_HELPER_CLASS, MyGenModel.PUBLIC_FACADE_HELPER);
  }
  
  public void testASTFacadeHelperDebugFlag()
  {
    boolean debug = new ASTFacadeHelper()
    {
      public boolean getDebugFlag()
      {
        return DEBUG;
      }
    }.getDebugFlag();
    
    assertFalse(debug);
  }
}
