/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.ecore.xcore.genmodel;


import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.test.ecore.xcore.GenModelFormatter;
import org.eclipse.emf.test.ecore.xcore.XcoreStandaloneInjectorProvider;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.InjectParameter;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.Offset;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.ParameterSyntax;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.ParameterizedXtextRunner;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.ResourceURIs;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.XpectString;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;


@InjectWith(XcoreStandaloneInjectorProvider.class)
@RunWith(ParameterizedXtextRunner.class)
@ResourceURIs(baseDir = "test-models/org/eclipse/emf/test/ecore/xcore/genmodel", fileExtensions = "xcore")
public class XcoreGenModelTest
{

  @Inject
  private XcoreMapper mapper;

  @InjectParameter
  private Offset offset;

  @InjectParameter
  private XtextResource resource;

  @Inject
  private ValidationTestHelper validationHelper;

  @Test
  public void noValidationIssues()
  {
    validationHelper.assertNoIssues(resource.getContents().get(0));
  }

  @XpectString
  @ParameterSyntax("'at' offset=OFFSET")
  public String genBase()
  {
    EcoreUtil.resolveAll(resource);
    GenBase gen = mapper.getGen((XNamedElement)offset.getEObject());
    EStructuralFeature eStructuralFeature = gen.eClass().getEStructuralFeature("documentation");
    if (eStructuralFeature != null)
    {
      // Clear the documentation feature because this will have the Xpect documentation tag which we don't want to format.
      //
      gen.eSet(eStructuralFeature, null);
    }
    return new GenModelFormatter().resolveCrossReferences().format(gen);
  }

}
