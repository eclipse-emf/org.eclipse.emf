/**
 * <copyright>
 *
 * Copyright (c) 2006-2007 IBM Corporation and others.
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
 * $Id: FacadeAPITest.java,v 1.4 2008/12/22 14:25:59 emerks Exp $
 */

package org.eclipse.emf.test.tools.merger.facade;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.jdt.core.Flags;

import org.eclipse.emf.codegen.ecore.genmodel.impl.GenModelImpl;
import org.eclipse.emf.codegen.merge.java.JMerger;
import org.eclipse.emf.codegen.merge.java.facade.FacadeFlags;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.FacadeVisitor;
import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.JNode;
import org.eclipse.emf.codegen.merge.java.facade.ast.ASTFacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.jdom.JDOMFacadeHelper;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.tools.AllSuites;

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
    ts.addTest(new FacadeAPITest("testFacadeHelperReset"));
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
  
  /*
   * Bugzilla 169251
   */
  public void testFacadeHelperReset()
  {
    File javaFile = new File(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/Example1.java").getAbsoluteFile();
    assertTrue(javaFile.isFile());
    String contents = TestUtil.readFile(javaFile, true);
    
    facadeHelperResetTest(new JDOMFacadeHelper(), contents);
    facadeHelperResetTest(new ASTFacadeHelper(), contents);
  }
  
  protected void facadeHelperResetTest(final FacadeHelper facadeHelper, String contents)
  {
    final List<Boolean> bool = new ArrayList<Boolean>();
    bool.add(false);

    FacadeVisitor facadeVisitor = new FacadeVisitor()
    {
      @Override
     protected boolean basicVisit(JNode node)
     {
       String message = "bool:" + bool.get(0)
         + " node:" + node.getClass().getName() 
         + " disposed: " + facadeHelper.isDisposed(node);
       assertTrue(message, bool.get(0) == facadeHelper.isDisposed(node));
       return true;
     }
    };

    JCompilationUnit compilationUnit = facadeHelper.createCompilationUnit("", contents);    
    assertFalse(compilationUnit.getChildren().isEmpty());
    facadeVisitor.start(compilationUnit);
    
    Map<Object, JNode> objectToNodeMap = getObjectToNodeMap(facadeHelper);
    assertNotNull(objectToNodeMap);
    assertFalse(objectToNodeMap.isEmpty());    
    for (JNode node : objectToNodeMap.values())
    {
      assertFalse(facadeHelper.isDisposed(node));
    }
     
    Map<Object, JNode> copy = new HashMap<Object, JNode>(objectToNodeMap);
    facadeHelper.reset();
    assertTrue(objectToNodeMap.isEmpty());
    bool.set(0, true);
    facadeVisitor.start(compilationUnit);

    assertFalse(copy.isEmpty());
    for (JNode node : copy.values())
    {
      assertTrue(facadeHelper.isDisposed(node));
    }
  }
  
  protected Map<Object, JNode> getObjectToNodeMap(FacadeHelper facadeHelper)
  {
    try
    {
      Field mapField = FacadeHelper.class.getDeclaredField("objectToNodeMap");
      mapField.setAccessible(true);
      @SuppressWarnings("unchecked")
      Map<Object, JNode> map = (Map<Object, JNode>)mapField.get(facadeHelper);
      return map;
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }    
    return null;
  }
}
