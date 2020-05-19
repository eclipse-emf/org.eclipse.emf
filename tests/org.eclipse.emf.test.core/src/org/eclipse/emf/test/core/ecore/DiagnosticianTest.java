/**
 * Copyright (c) 2018 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.core.ecore;


import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;


/**
 * Tests for <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=521642">Bug 521642</a>.
 */
public class DiagnosticianTest
{
  /**
   * A base class that counts how many times {@link #doValidateContents(EObject, DiagnosticChain, Map)} is called.
   * It does this by virtue of how many times {@link #validate(EClass, EObject, DiagnosticChain, Map)} is called,
   * so it is always one less than the actual count depending on the number of leaf nodes, i.e., nodes with no children.
   */
  public static class InstrumentedDiagnostician extends Diagnostician
  {
    public int validateCount;

    public int doValidateContentsCount;

    @Override
    public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
      ++validateCount;
      StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
      Assume.assumeTrue(stackTrace.length > 4);
      if ("doValidateContents".equals(stackTrace[3].getMethodName()))
      {
        ++doValidateContentsCount;
      }
      return super.validate(eClass, eObject, diagnostics, context);
    }
  }

  /**
   * The default behavior as of EMF 2.14 is to use iteration.
   */
  public static class IterativeDiagnostician extends InstrumentedDiagnostician
  {
  }

  /**
   * The default iteration behavior can be explicitly specialized to use recursion.
   *
   */
  public static class RecursiveDiagnostician extends InstrumentedDiagnostician
  {
    @Override
    protected boolean isValidateContentsRecursively()
    {
      return true;
    }
  }

  /**
   * The default implementation will use recursion 
   * if the {@link #doValidateContents(EObject, DiagnosticChain, Map)} is specialized by a subclass
   * as is the case for this subclass.
   */
  public static class SpecializedDiagnostician extends InstrumentedDiagnostician
  {
    @Override
    protected boolean doValidateContents(EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context)
    {
      return super.doValidateContents(eObject, diagnostics, context);
    }
  }

  /**
   * The Node class.
   */
  private static final EClass NODE_CLASS;

  /**
   * The Node class' multi-valued containment reference.
   */
  private static final EReference NODES_REFERENCE;

  static
  {
    EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
    ePackage.setName("nodes");
    ePackage.setNsPrefix("nodes");
    ePackage.setNsURI("nodes");

    EClass eClass = EcoreFactory.eINSTANCE.createEClass();
    eClass.setName("Node");
    ePackage.getEClassifiers().add(eClass);
    NODE_CLASS = eClass;

    EReference eReference = EcoreFactory.eINSTANCE.createEReference();
    eReference.setName("nodes");
    eReference.setEType(eClass);
    eReference.setContainment(true);
    eReference.setUpperBound(-1);
    eClass.getEStructuralFeatures().add(eReference);
    NODES_REFERENCE = eReference;
  }

  protected InstrumentedDiagnostician instrumentedDiagnostician;

  protected IterativeDiagnostician iterativeDiagnostician;

  protected RecursiveDiagnostician recursiveDiagnostician;

  protected SpecializedDiagnostician specializedDiagnostician;

  @Before
  public void setUp()
  {
    instrumentedDiagnostician = new InstrumentedDiagnostician();
    recursiveDiagnostician = new RecursiveDiagnostician();
    iterativeDiagnostician = new IterativeDiagnostician();
    specializedDiagnostician = new SpecializedDiagnostician();
  }

  private static EObject createNode()
  {
    return EcoreUtil.create(NODE_CLASS);
  }

  private static void addChild(EObject node, EObject child)
  {
    @SuppressWarnings("unchecked")
    List<EObject> nodes = (List<EObject>)node.eGet(NODES_REFERENCE);
    nodes.add(child);
  }

  /**
   * This tests that recursive diagnosticians will stack overflow on very deep trees.
   */
  @Test
  public void testStackOverflow()
  {
    EObject rootNode = createNode();
    EObject node = rootNode;
    for (int i = 1; i < 10000; ++i)
    {
      EObject childNode = createNode();
      addChild(node, childNode);
      node = childNode;
    }

    testStackOverflow(instrumentedDiagnostician, rootNode, false);
    testStackOverflow(iterativeDiagnostician, rootNode, false);
    testStackOverflow(recursiveDiagnostician, rootNode, true);
    testStackOverflow(specializedDiagnostician, rootNode, true);
  }

  private void testStackOverflow(Diagnostician diagnostician, EObject eObject, boolean expectStackOverflow)
  {
    boolean stackOverflow = false;
    try
    {
      diagnostician.validate(eObject);
    }
    catch (StackOverflowError stackOverflowError)
    {
      stackOverflow = true;
    }

    Assert.assertEquals("Unsatisified stack overflow expectation", expectStackOverflow, stackOverflow);
  }

  /**
   * This tests that only explicitly recursive diagnosticians
   * and implicitly recursive diagnosticians, because they override doValidatorContents,
   * will actually call doValidateContents.
   */
  @Test
  public void testDoValidateContents()
  {
    EObject rootNode = createNode();
    EObject node = rootNode;
    int count = 100;
    for (int i = 1; i < count; ++i)
    {
      EObject childNode = createNode();
      addChild(node, childNode);
      node = childNode;
    }

    testDoValidateContents(instrumentedDiagnostician, rootNode, 0);
    testDoValidateContents(iterativeDiagnostician, rootNode, 0);
    testDoValidateContents(recursiveDiagnostician, rootNode, count - 1);
    testDoValidateContents(specializedDiagnostician, rootNode, count - 1);
  }

  private void testDoValidateContents(InstrumentedDiagnostician diagnostician, EObject eObject, int expectedCount)
  {
    diagnostician.validate(eObject);
    Assert.assertEquals("Unsatisified stack overflow expectation", expectedCount, diagnostician.doValidateContentsCount);
  }

  /**
   * This tests that direct calls to {@link Diagnostician#validate(EClass, EObject, DiagnosticChain, Map)}
   * will continue to call {@code doValidateContents} in all cases to validate the entire content tree.
   */
  @Test
  public void testDoValidateContentsViaDirectCall()
  {
    EObject rootNode = createNode();
    EObject node = rootNode;
    int count = 100;
    for (int i = 1; i < count; ++i)
    {
      EObject childNode = createNode();
      addChild(node, childNode);
      node = childNode;
    }

    testDoValidateContentsViaDirectCall(instrumentedDiagnostician, rootNode, count - 1);
    testDoValidateContentsViaDirectCall(iterativeDiagnostician, rootNode, count - 1);
    testDoValidateContentsViaDirectCall(recursiveDiagnostician, rootNode, count - 1);
    testDoValidateContentsViaDirectCall(specializedDiagnostician, rootNode, count - 1);
  }

  private void testDoValidateContentsViaDirectCall(InstrumentedDiagnostician diagnostician, EObject eObject, int expectedCount)
  {
    Map<Object, Object> context = diagnostician.createDefaultContext();
    BasicDiagnostic diagnostic = diagnostician.createDefaultDiagnostic(eObject);
    diagnostician.validate(eObject.eClass(), eObject, diagnostic, context);
    Assert.assertEquals("Unsatisified stack overflow expectation", expectedCount, diagnostician.doValidateContentsCount);
  }

  /**
   * This tests that all diagnosticians avoid infinite recursion and infinite iteration on a circular containment.
   */
  @Test
  public void testCircularity()
  {
    EObject rootNode = createNode();

    EObject childNode1 = createNode();
    addChild(childNode1, rootNode);
    addChild(rootNode, childNode1);

    EObject childNode2 = createNode();
    addChild(rootNode, childNode2);

    testCircularity(instrumentedDiagnostician, rootNode);
    testCircularity(iterativeDiagnostician, rootNode);
    testCircularity(recursiveDiagnostician, rootNode);
    testCircularity(specializedDiagnostician, rootNode);
  }

  private void testCircularity(InstrumentedDiagnostician diagnostician, EObject eObject)
  {
    diagnostician.validate(eObject);
    Assert.assertEquals("Unsatisified validate count expectation", 4, diagnostician.validateCount);
  }

  @Test
  public void testExceptionHandlingRecordedNPE()
  {
    final NullPointerException exception = new NullPointerException();
    EClass eClass = new EClassImpl()
      {
        @Override
        public EList<EClass> getEAllSuperTypes()
        {
          throw exception;
        }
      };

    Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
    Assert.assertEquals("Expected an error", Diagnostic.ERROR, diagnostic.getSeverity());
    Assert.assertEquals("Expected one child", 1, diagnostic.getChildren().size());
    Diagnostic child = diagnostic.getChildren().get(0);
    Throwable diagnosticException = child.getException();
    Assert.assertSame("Expected the specific exception to be thrown", exception, diagnosticException);
  }

  @Test
  public void testExceptionHandlingRecordedAE()
  {
    final AssertionError exception = new AssertionError();
    EClass eClass = new EClassImpl()
      {
        @Override
        public EList<EClass> getEAllSuperTypes()
        {
          throw exception;
        }
      };

    Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
    Assert.assertEquals("Expected an error", Diagnostic.ERROR, diagnostic.getSeverity());
    Assert.assertEquals("Expected one child", 1, diagnostic.getChildren().size());
    Diagnostic child = diagnostic.getChildren().get(0);
    Throwable diagnosticException = child.getException();
    Assert.assertSame("Expected the specific exception to be thrown", exception, diagnosticException);
  }

  @Test
  public void testExceptionHandlingNotRecordedNPE()
  {
    final NullPointerException exception = new NullPointerException();
    EClass eClass = new EClassImpl()
      {
        @Override
        public EList<EClass> getEAllSuperTypes()
        {
          throw exception;
        }
      };

    try
    {
      @SuppressWarnings("unused")
      boolean isValid = Diagnostician.INSTANCE.validate(eClass, (DiagnosticChain)null);
      Assert.fail("Exected an exception to be thrown");
    }
    catch (Throwable throwable)
    {
      Assert.assertSame("Expected the specific exception to be thrown", exception, throwable);
    }
  }
  
  @Test
  public void testExceptionHandlingNotRecordedAE()
  {
    final AssertionError exception = new AssertionError();
    EClass eClass = new EClassImpl()
      {
        @Override
        public EList<EClass> getEAllSuperTypes()
        {
          throw exception;
        }
      };

    try
    {
      @SuppressWarnings("unused")
      boolean isValid = Diagnostician.INSTANCE.validate(eClass, (DiagnosticChain)null);
      Assert.fail("Exected an exception to be thrown");
    }
    catch (Throwable throwable)
    {
      Assert.assertSame("Expected the specific exception to be thrown", exception, throwable);
    }
  }

  @Test
  public void testExceptionHandlingNotHandledException()
  {
    final OutOfMemoryError exception = new OutOfMemoryError();
    EClass eClass = new EClassImpl()
      {
        @Override
        public EList<EClass> getEAllSuperTypes()
        {
          throw exception;
        }
      };

    try
    {
      @SuppressWarnings("unused")
      Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eClass);
      Assert.fail("Exected an exception to be thrown");
    }
    catch (Throwable throwable)
    {
      Assert.assertSame("Expected the specific exception to be thrown", exception, throwable);
    }
  }

//  /**
//   * A simple test for profiling.
//   */
//  public static void main(String[] args)
//  {
//    EObject rootNode = createNode();
//    EObject node = rootNode;
//    int count = 100;
//    for (int i = 1; i < count; ++i)
//    {
//      EObject childNode = createNode();
//      addChild(node, childNode);
//      node = childNode;
//    }
//
//    class ContextMap extends java.util.HashMap<Object, Object>
//    {
//      private static final long serialVersionUID = 1L;
//
//      public Object get(Object key)
//      {
//        return super.get(key);
//      }
//
//      public Object put(Object key, Object value)
//      {
//        return super.put(key, value);
//      }
//    }
//
//    new Diagnostician()
//      {
//        public java.util.Map<Object, Object> createDefaultContext()
//        {
//          ContextMap contextMap = new ContextMap();
//          contextMap.putAll(super.createDefaultContext());
//          return contextMap;
//        }
//      }.validate(rootNode, (DiagnosticChain)null);
//  }
}
