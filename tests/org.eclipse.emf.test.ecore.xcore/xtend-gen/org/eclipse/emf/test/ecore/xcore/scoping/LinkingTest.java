/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.ecore.xcore.scoping;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XMember;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.test.ecore.xcore.XcoreStandaloneInjectorProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.XBinaryOperation;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(XcoreStandaloneInjectorProvider.class)
@SuppressWarnings("all")
public class LinkingTest {
  @Inject
  private ParseHelper<XPackage> parser;
  
  @Test
  public void linkVoidReturnType() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.append("class Bar {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op void operation() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("val int i = 0");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final XPackage pack = this.parser.parse(_builder);
      final XVariableDeclaration declaration = this.firstVariableDeclaration(pack);
      final JvmType intType = declaration.getType().getType();
      Assert.assertFalse(intType.eIsProxy());
      Assert.assertEquals("int", intType.getQualifiedName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void linkQualifiedStringType() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.append("class Bar {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op void operation() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("val java.lang.String s = null");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final XPackage pack = this.parser.parse(_builder);
      final XVariableDeclaration declaration = this.firstVariableDeclaration(pack);
      final JvmType stringType = declaration.getType().getType();
      Assert.assertFalse(stringType.eIsProxy());
      Assert.assertEquals("java.lang.String", stringType.getQualifiedName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void linkInternalDefinedType() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.append("class Bar {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op void operation() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("val Bar b = null");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final XPackage pack = this.parser.parse(_builder);
      final XVariableDeclaration declaration = this.firstVariableDeclaration(pack);
      final JvmType stringType = declaration.getType().getType();
      Assert.assertFalse(stringType.eIsProxy());
      Assert.assertEquals("foo.Bar", stringType.getQualifiedName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void linkFeatureCallType() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.append("class Bar {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op void operation() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("val s = \'a\' + \'b\'");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final XPackage pack = this.parser.parse(_builder);
      final XVariableDeclaration declaration = this.firstVariableDeclaration(pack);
      XExpression _right = declaration.getRight();
      final XBinaryOperation binaryOperation = ((XBinaryOperation) _right);
      final JvmIdentifiableElement feature = binaryOperation.getFeature();
      Assert.assertFalse(feature.eIsProxy());
      Assert.assertEquals("operator_plus", feature.getSimpleName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public XVariableDeclaration firstVariableDeclaration(final XPackage pack) {
    XVariableDeclaration _xblockexpression = null;
    {
      XClassifier _head = IterableExtensions.<XClassifier>head(pack.getClassifiers());
      final XClass clazz = ((XClass) _head);
      XMember _head_1 = IterableExtensions.<XMember>head(clazz.getMembers());
      final XOperation operation = ((XOperation) _head_1);
      final XBlockExpression block = operation.getBody();
      XExpression _head_2 = IterableExtensions.<XExpression>head(block.getExpressions());
      final XVariableDeclaration declaration = ((XVariableDeclaration) _head_2);
      _xblockexpression = declaration;
    }
    return _xblockexpression;
  }
}
