/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.ecore.xcore.interpreter;


import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.junit.evaluation.AbstractXbaseEvaluationTest;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;


@SuppressWarnings("restriction")
@RunWith(XtextRunner.class)
@InjectWith(XcoreInjectorProvider.class)
public class XcoreInterpreterXbaseIntegrationTest extends AbstractXbaseEvaluationTest
{

  @Inject
  private ParseHelper<XPackage> parser;

  @Inject
  private ValidationTestHelper validator;

  @Override
  protected Object invokeXbaseExpression(String expression) throws Exception
  {
    XPackage pack = parser.parse("package foo type Exception wraps Exception class Bar { op Object foo() throws Exception { " + expression + " } }");
    validator.assertNoErrors(pack);
    EPackage ePack = (EPackage)pack.eResource().getContents().get(2);
    EClass barClass = (EClass)ePack.getEClassifier("Bar");
    EObject eObject = ePack.getEFactoryInstance().create(barClass);
    return eObject.eInvoke(barClass.getEOperations().get(0), new BasicEList<Object>());
  }

  @Override
  public void testShortCircuitBooleanExpression_03() throws Exception
  {
    // Ignore
  }

  @Override
  public void testShortCircuitBooleanExpression_04() throws Exception
  {
    // Ignore
  }

  @Override
  public void testFunctionConversion_00() throws Exception
  {
    // Ignore
  }

  @Override
  public void testMapConstruction_01() throws Exception
  {
    // Ignore
  }

  @Override
  @Ignore
  @Test
  public void testArrays_01() throws Exception
  {
    super.testReservedWordEnum();
  }

  @Override
  @Ignore
  @Test
  public void testArrays_02() throws Exception
  {
    super.testReservedWordEnum();
  }

  @Override
  @Ignore
  @Test
  public void testReservedWordEnum() throws Exception
  {
    super.testReservedWordEnum();
  }

  @Override
  @Ignore
  @Test
  public void testReservedWordInterface() throws Exception
  {
    super.testReservedWordInterface();
  }

  @Override
  @Ignore
  @Test
  public void testReservedWordAnnotation() throws Exception
  {
    super.testReservedWordAnnotation();
  }

  @Override
  @Ignore
  @Test
  public void testReservedWordClass() throws Exception
  {
    super.testReservedWordClass();
  }

  @Override
  @Test
  @Ignore("Fails with old type system")
  public void testBlock_02() throws Exception
  {
    // TODO Re-activate when we start using the new type system 
    super.testBlock_02();
  }

  @Override
  @Test
  @Ignore("Fails with old type system")
  public void testForLoop_16() throws Exception
  {
    // TODO Re-activate when we start using the new type system 
    super.testForLoop_16();
  }

  @Override
  @Test
  @Ignore("Fails with old type system")
  public void testSwitchExpression_24() throws Exception
  {
    // TODO Re-activate when we start using the new type system 
    super.testSwitchExpression_24();
  }

  @Override
  @Test
  @Ignore("Fails with old type system")
  public void testReduceWithPlusOperator() throws Exception
  {
    // TODO Re-activate when we start using the new type system 
    super.testReduceWithPlusOperator();
  }

  @Override
  @Test
  @Ignore("Fails with old type system")
  public void testCollectionExtensions_08() throws Exception
  {
    // TODO Re-activate when we start using the new type system 
    super.testCollectionExtensions_08();
  }

  @Override
  @Test
  @Ignore("Fails with old type system")
  public void testCollectionExtensions_10() throws Exception
  {
    // TODO Re-activate when we start using the new type system 
    super.testCollectionExtensions_10();
  }

  @Override
  @Test
  @Ignore("Fails with old type system")
  public void testExceptionInClosure_01() throws Exception
  {
    // TODO Re-activate when we start using the new type system 
    super.testExceptionInClosure_01();
  }

  @Override
  @Test
  @Ignore("Fails with old type system")
  public void testExceptionInClosure_02() throws Exception
  {
    // TODO Re-activate when we start using the new type system 
    super.testExceptionInClosure_02();
  }

  @Override
  @Test
  @Ignore("Fails with old type system")
  public void testExceptionInClosure_03() throws Exception
  {
    // TODO Re-activate when we start using the new type system 
    super.testExceptionInClosure_03();
  }

  @Override
  @Test
  @Ignore("Fails with old type system")
  public void testExceptionInClosure_04() throws Exception
  {
    // TODO Re-activate when we start using the new type system 
    super.testExceptionInClosure_04();
  }
}
