/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.ecore.xcore;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.Iterator;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XAnnotation;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XAttribute;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XGenericType;
import org.eclipse.emf.ecore.xcore.XMember;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XReference;
import org.eclipse.emf.ecore.xcore.XcoreExtensions;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(XcoreStandaloneInjectorProvider.class)
@SuppressWarnings("all")
public class ParsingTest {
  @Inject
  private ParseHelper<XPackage> parser;
  
  @Inject
  @Extension
  private XcoreExtensions exts;
  
  @Inject
  @Extension
  private ValidationTestHelper vth;
  
  @Test
  public void parseSimpleFile() {
    try {
      final XPackage parse = this.parser.parse("package foo");
      Assert.assertEquals("foo", parse.getName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testJvmTypes() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo ");
      _builder.newLine();
      _builder.append("class A ");
      _builder.newLine();
      _builder.append("{ ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("refers A a");
      _builder.newLine();
      _builder.append("} ");
      _builder.newLine();
      final XPackage pack = this.parser.parse(_builder);
      EcoreUtil.resolveAll(pack.eResource());
      this.vth.assertNoErrors(pack);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testSuperTypeLinking_1() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo ");
      _builder.newLine();
      _builder.append("class A {} ");
      _builder.newLine();
      _builder.append("class B extends A {}");
      _builder.newLine();
      final XPackage pack = this.parser.parse(_builder);
      XClassifier _get = pack.getClassifiers().get(1);
      final XClass clazz = ((XClass) _get);
      Assert.assertEquals("A", this.exts.getGenClass(IterableExtensions.<XGenericType>head(clazz.getSuperTypes())).getName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testSuperTypeLinking_2() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo ");
      _builder.newLine();
      _builder.append("class A {} ");
      _builder.newLine();
      _builder.append("class B extends foo.A {}");
      _builder.newLine();
      final XPackage pack = this.parser.parse(_builder);
      XClassifier _get = pack.getClassifiers().get(1);
      final XClass clazz = ((XClass) _get);
      Assert.assertEquals("A", this.exts.getGenClass(IterableExtensions.<XGenericType>head(clazz.getSuperTypes())).getName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testReferenceToAnnotation() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo ");
      _builder.newLine();
      _builder.append("annotation \'foo/bar\' as foo");
      _builder.newLine();
      _builder.append("@foo(holla=\'bar\')");
      _builder.newLine();
      _builder.append("class A {} ");
      _builder.newLine();
      final XPackage pack = this.parser.parse(_builder);
      XClassifier _get = pack.getClassifiers().get(0);
      final XClass clazz = ((XClass) _get);
      Assert.assertEquals(IterableExtensions.<XAnnotationDirective>head(pack.getAnnotationDirectives()), IterableExtensions.<XAnnotation>head(clazz.getAnnotations()).getSource());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testReferenceToOpposite() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package  foo");
      _builder.newLine();
      _builder.append("class X");
      _builder.newLine();
      _builder.append("{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("refers X x opposite y   ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("refers X y opposite x");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op void foo() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("val X x = null");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final String text = _builder.toString();
      final XPackage pack = this.parser.parse(text);
      {
        XClassifier _get = pack.getClassifiers().get(0);
        final XClass clazz = ((XClass) _get);
        final Iterator<XReference> refs = Iterables.<XReference>filter(clazz.getMembers(), XReference.class).iterator();
        XReference refX = refs.next();
        XReference refY = refs.next();
        Assert.assertEquals(refY.getName(), refX.getOpposite().getName());
        Assert.assertEquals(refX.getName(), refY.getOpposite().getName());
      }
      Resource _eResource = pack.eResource();
      final XtextResource resource = ((XtextResource) _eResource);
      final int elements = resource.getContents().size();
      resource.update(0, text.length(), text);
      {
        EObject _get = resource.getContents().get(0);
        XClassifier _get_1 = ((XPackage) _get).getClassifiers().get(0);
        final XClass clazz = ((XClass) _get_1);
        final Iterator<XReference> refs = Iterables.<XReference>filter(clazz.getMembers(), XReference.class).iterator();
        XReference refX = refs.next();
        XReference refY = refs.next();
        Assert.assertEquals(refY.getName(), refX.getOpposite().getName());
        Assert.assertEquals(refX.getName(), refY.getOpposite().getName());
        Assert.assertEquals(elements, resource.getContents().size());
      }
      resource.reparse(text);
      {
        EObject _get = resource.getContents().get(0);
        XClassifier _get_1 = ((XPackage) _get).getClassifiers().get(0);
        final XClass clazz = ((XClass) _get_1);
        final Iterator<XReference> refs = Iterables.<XReference>filter(clazz.getMembers(), XReference.class).iterator();
        XReference refX = refs.next();
        XReference refY = refs.next();
        Assert.assertEquals(refY.getName(), refX.getOpposite().getName());
        Assert.assertEquals(refX.getName(), refY.getOpposite().getName());
        Assert.assertEquals(elements, resource.getContents().size());
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void operationReturnsVoid() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.append("class Bar {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op void operation() {}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final XPackage pack = this.parser.parse(_builder);
      XClassifier _head = IterableExtensions.<XClassifier>head(pack.getClassifiers());
      final XClass clazz = ((XClass) _head);
      XMember _head_1 = IterableExtensions.<XMember>head(clazz.getMembers());
      final XOperation operation = ((XOperation) _head_1);
      Assert.assertTrue(clazz.eResource().getErrors().isEmpty());
      Assert.assertNull(operation.getType());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void referenceMayNotBeVoid() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.append("class Bar {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("refers void referenceName");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final XPackage pack = this.parser.parse(_builder);
      String _string = pack.eResource().getErrors().toString();
      int _size = pack.eResource().getErrors().size();
      boolean _lessEqualsThan = (1 <= _size);
      Assert.assertTrue(_string, _lessEqualsThan);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void stringResolvesToEString() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.append("class Bar {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("String value");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final XPackage pack = this.parser.parse(_builder);
      XClassifier _head = IterableExtensions.<XClassifier>head(pack.getClassifiers());
      final XClass clazz = ((XClass) _head);
      XMember _head_1 = IterableExtensions.<XMember>head(clazz.getMembers());
      final XAttribute attribute = ((XAttribute) _head_1);
      GenBase _type = attribute.getType().getType();
      Assert.assertTrue((_type instanceof GenClassifier));
      GenBase _type_1 = attribute.getType().getType();
      Assert.assertEquals("EString", ((GenClassifier) _type_1).getName());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testEcoreDataTypeAliases() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo ");
      _builder.newLine();
      _builder.append("class A ");
      _builder.newLine();
      _builder.append("{ ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("java.math.BigDecimal _bigDecimal");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("java.math.BigInteger _bigInteger");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("boolean _boolean");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Boolean _Boolean");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("byte _byte");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Byte _Byte");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("char _char");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Character _Character");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("java.util.Date _date");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("double _double");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Double _Double");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("float _float");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Float _Float");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("int _int");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Integer _Integer");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Class _class");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Object _object");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("long _long");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Long _Long");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("short _short");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("Short _Short");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("String _String");
      _builder.newLine();
      _builder.append("} ");
      _builder.newLine();
      final XPackage pack = this.parser.parse(_builder);
      EcoreUtil.resolveAll(pack.eResource());
      this.vth.assertNoErrors(pack);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testEnumMembersAvailable() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("@GenModel(complianceLevel=\"6.0\")");
      _builder.newLine();
      _builder.append("package p ");
      _builder.newLine();
      _builder.append("class A ");
      _builder.newLine();
      _builder.append("{ ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op void m() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("if (E.ONE != E.TWO && E.ONE_VALUE != E.TWO_VALUE) {");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("var E e = E.valueOf(\'\')");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("var E[] all = E.values()");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("all = E.VALUES");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("e = E.get(1)");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("e = E.get(\'\')");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("e = E.getByName(\'\')");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("var String s = E.ONE.name()");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("s = E.ONE.getName()");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("var int i = E.ONE.value");
      _builder.newLine();
      _builder.append("\t\t\t");
      _builder.append("s = E.ONE.literal");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.append("enum E {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("one = 1");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("two = 2");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final XPackage pack = this.parser.parse(_builder);
      EcoreUtil.resolveAll(pack.eResource());
      this.vth.assertNoErrors(pack);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
