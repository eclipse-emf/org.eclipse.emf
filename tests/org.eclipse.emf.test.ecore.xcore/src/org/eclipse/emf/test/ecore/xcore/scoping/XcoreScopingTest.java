/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.ecore.xcore.scoping;


import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.InjectParameter;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.Offset;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.ParameterSyntax;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.ParameterizedXtextRunner;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.ResourceURIs;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.XpectCommaSeparatedValues;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.util.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.common.collect.Lists;
import com.google.inject.Inject;


@SuppressWarnings("restriction")
@InjectWith(XcoreInjectorProvider.class)
@RunWith(ParameterizedXtextRunner.class)
@ResourceURIs(baseDir = "src/org/eclipse/emf/test/ecore/xcore/scoping", fileExtensions = "xcore")
public class XcoreScopingTest
{

  @InjectParameter
  private XtextResource resource;

  @InjectParameter
  private Offset offset;

  @Inject
  private ValidationTestHelper validationHelper;

  @Inject
  private IScopeProvider scopeProvider;

  @Test
  public void noValidationIssues()
  {
    validationHelper.assertNoIssues(resource.getContents().get(0));
  }

  @XpectCommaSeparatedValues()
  @ParameterSyntax("'at' offset=OFFSET")
  public List<String> scopeAllElements()
  {
    Pair<EObject, EStructuralFeature> objAndFeature = offset.getEStructuralFeatureByParent();
    Assert.assertTrue(objAndFeature.getSecond() instanceof EReference);
    Assert.assertFalse(((EReference)objAndFeature.getSecond()).isContainment());
    IScope scope = scopeProvider.getScope(objAndFeature.getFirst(), (EReference)objAndFeature.getSecond());
    List<String> result = Lists.newArrayList();
    for (IEObjectDescription desc : scope.getAllElements())
      result.add(desc.getName().toString());
    return result;
  }

}
