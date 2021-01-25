/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.ecore.xcore;

import com.google.common.collect.Iterators;
import com.google.inject.Inject;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XMember;
import org.eclipse.emf.ecore.xcore.XReference;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.util.StringInputStream;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(XcoreStandaloneInjectorProvider.class)
@SuppressWarnings("all")
public class MultiFileTest {
  @Inject
  private XtextResourceSet resourceSet;
  
  @Inject
  private ValidationTestHelper validator;
  
  @Inject
  @Extension
  private XcoreMapper mapper;
  
  @Test
  public void testReferenceBetweenTwoModels() {
    try {
      final Resource resourceA = this.resourceSet.createResource(URI.createURI("file:/modelA.xcore"));
      final Resource resourceB = this.resourceSet.createResource(URI.createURI("file:/modelB.xcore"));
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package packB");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class TypeB {");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      String _string = _builder.toString();
      StringInputStream _stringInputStream = new StringInputStream(_string);
      resourceB.load(_stringInputStream, null);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package packA");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("class TypeA {");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("refers packB.TypeB refToB ");
      _builder_1.newLine();
      _builder_1.append("}");
      _builder_1.newLine();
      String _string_1 = _builder_1.toString();
      StringInputStream _stringInputStream_1 = new StringInputStream(_string_1);
      resourceA.load(_stringInputStream_1, null);
      this.validator.assertNoErrors(IterableExtensions.<EObject>head(resourceA.getContents()));
      this.validator.assertNoErrors(IterableExtensions.<EObject>head(resourceB.getContents()));
      final TreeIterator<EObject> allContents = resourceA.getAllContents();
      final XClass xclass = IteratorExtensions.<XClass>head(Iterators.<XClass>filter(allContents, XClass.class));
      final GenBase referencedGenClass = IterableExtensions.<XMember>head(xclass.getMembers()).getType().getType();
      Assert.assertEquals("TypeB", ((GenClass) referencedGenClass).getName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testBidirectionalReferenceBetweenTwoModels() {
    try {
      final Resource resourceA = this.resourceSet.createResource(URI.createURI("file:/modelA.xcore"));
      final Resource resourceB = this.resourceSet.createResource(URI.createURI("file:/modelB.xcore"));
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package packB");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class TypeB {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("refers packA.TypeA refToA opposite refToB");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      String _string = _builder.toString();
      StringInputStream _stringInputStream = new StringInputStream(_string);
      resourceB.load(_stringInputStream, null);
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("package packA");
      _builder_1.newLine();
      _builder_1.newLine();
      _builder_1.append("class TypeA {");
      _builder_1.newLine();
      _builder_1.append("\t");
      _builder_1.append("refers packB.TypeB refToB opposite refToA");
      _builder_1.newLine();
      _builder_1.append("}");
      _builder_1.newLine();
      String _string_1 = _builder_1.toString();
      StringInputStream _stringInputStream_1 = new StringInputStream(_string_1);
      resourceA.load(_stringInputStream_1, null);
      this.validator.assertNoErrors(IterableExtensions.<EObject>head(resourceA.getContents()));
      this.validator.assertNoErrors(IterableExtensions.<EObject>head(resourceB.getContents()));
      final TreeIterator<EObject> allContents = resourceA.getAllContents();
      final XClass xclass = IteratorExtensions.<XClass>head(Iterators.<XClass>filter(allContents, XClass.class));
      final GenBase referencedGenClass = IterableExtensions.<XMember>head(xclass.getMembers()).getType().getType();
      Assert.assertEquals("TypeB", ((GenClass) referencedGenClass).getName());
      XMember _head = IterableExtensions.<XMember>head(xclass.getMembers());
      final XReference ref = ((XReference) _head);
      XStructuralFeature _xFeature = this.mapper.getXFeature(ref.getOpposite());
      Assert.assertEquals(ref, this.mapper.getXFeature(((XReference) _xFeature).getOpposite()));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
