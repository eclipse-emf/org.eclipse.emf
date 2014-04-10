/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.ecore.xcore.genmodel;


import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.test.ecore.xcore.GenModelFormatter;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.InjectParameter;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.Offset;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.ParameterSyntax;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.ParameterizedXtextRunner;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.ResourceURIs;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.XpectString;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;


@SuppressWarnings("restriction")
@InjectWith(XcoreInjectorProvider.class)
@RunWith(ParameterizedXtextRunner.class)
@ResourceURIs(baseDir = "src/org/eclipse/emf/test/ecore/xcore/genmodel", fileExtensions = "xcore")
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
    return new GenModelFormatter().resolveCrossReferences().format(gen);
  }

}
