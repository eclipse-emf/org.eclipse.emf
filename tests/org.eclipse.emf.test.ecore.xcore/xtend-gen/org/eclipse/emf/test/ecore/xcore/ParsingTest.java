/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.ecore.xcore;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.util.Iterator;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
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
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(XcoreInjectorProvider.class)
@SuppressWarnings("all")
public class ParsingTest {
  @Inject
  private ParseHelper<XPackage> parser;
  
  @Inject
  private XcoreExtensions exts;
  
  @Inject
  private ValidationTestHelper vth;
  
  @Test
  public void parseSimpleFile() {
    try {
      final XPackage parse = this.parser.parse("package foo");
      String _name = parse.getName();
      Assert.assertEquals("foo", _name);
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
      Resource _eResource = pack.eResource();
      EcoreUtil.resolveAll(_eResource);
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
      EList<XClassifier> _classifiers = pack.getClassifiers();
      XClassifier _get = _classifiers.get(1);
      final XClass clazz = ((XClass) _get);
      EList<XGenericType> _superTypes = clazz.getSuperTypes();
      XGenericType _head = IterableExtensions.<XGenericType>head(_superTypes);
      GenClass _genClass = this.exts.getGenClass(_head);
      String _name = _genClass.getName();
      Assert.assertEquals("A", _name);
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
      EList<XClassifier> _classifiers = pack.getClassifiers();
      XClassifier _get = _classifiers.get(1);
      final XClass clazz = ((XClass) _get);
      EList<XGenericType> _superTypes = clazz.getSuperTypes();
      XGenericType _head = IterableExtensions.<XGenericType>head(_superTypes);
      GenClass _genClass = this.exts.getGenClass(_head);
      String _name = _genClass.getName();
      Assert.assertEquals("A", _name);
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
      EList<XClassifier> _classifiers = pack.getClassifiers();
      XClassifier _get = _classifiers.get(0);
      final XClass clazz = ((XClass) _get);
      EList<XAnnotationDirective> _annotationDirectives = pack.getAnnotationDirectives();
      XAnnotationDirective _head = IterableExtensions.<XAnnotationDirective>head(_annotationDirectives);
      EList<XAnnotation> _annotations = clazz.getAnnotations();
      XAnnotation _head_1 = IterableExtensions.<XAnnotation>head(_annotations);
      XAnnotationDirective _source = _head_1.getSource();
      Assert.assertEquals(_head, _source);
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
        EList<XClassifier> _classifiers = pack.getClassifiers();
        XClassifier _get = _classifiers.get(0);
        final XClass clazz = ((XClass) _get);
        EList<XMember> _members = clazz.getMembers();
        Iterable<XReference> _filter = Iterables.<XReference>filter(_members, XReference.class);
        final Iterator<XReference> refs = _filter.iterator();
        XReference refX = refs.next();
        XReference refY = refs.next();
        String _name = refY.getName();
        GenFeature _opposite = refX.getOpposite();
        String _name_1 = _opposite.getName();
        Assert.assertEquals(_name, _name_1);
        String _name_2 = refX.getName();
        GenFeature _opposite_1 = refY.getOpposite();
        String _name_3 = _opposite_1.getName();
        Assert.assertEquals(_name_2, _name_3);
      }
      Resource _eResource = pack.eResource();
      final XtextResource resource = ((XtextResource) _eResource);
      EList<EObject> _contents = resource.getContents();
      final int elements = _contents.size();
      int _length = text.length();
      resource.update(0, _length, text);
      {
        EList<EObject> _contents_1 = resource.getContents();
        EObject _get = _contents_1.get(0);
        EList<XClassifier> _classifiers = ((XPackage) _get).getClassifiers();
        XClassifier _get_1 = _classifiers.get(0);
        final XClass clazz = ((XClass) _get_1);
        EList<XMember> _members = clazz.getMembers();
        Iterable<XReference> _filter = Iterables.<XReference>filter(_members, XReference.class);
        final Iterator<XReference> refs = _filter.iterator();
        XReference refX = refs.next();
        XReference refY = refs.next();
        String _name = refY.getName();
        GenFeature _opposite = refX.getOpposite();
        String _name_1 = _opposite.getName();
        Assert.assertEquals(_name, _name_1);
        String _name_2 = refX.getName();
        GenFeature _opposite_1 = refY.getOpposite();
        String _name_3 = _opposite_1.getName();
        Assert.assertEquals(_name_2, _name_3);
        EList<EObject> _contents_2 = resource.getContents();
        int _size = _contents_2.size();
        Assert.assertEquals(elements, _size);
      }
      resource.reparse(text);
      {
        EList<EObject> _contents_1 = resource.getContents();
        EObject _get = _contents_1.get(0);
        EList<XClassifier> _classifiers = ((XPackage) _get).getClassifiers();
        XClassifier _get_1 = _classifiers.get(0);
        final XClass clazz = ((XClass) _get_1);
        EList<XMember> _members = clazz.getMembers();
        Iterable<XReference> _filter = Iterables.<XReference>filter(_members, XReference.class);
        final Iterator<XReference> refs = _filter.iterator();
        XReference refX = refs.next();
        XReference refY = refs.next();
        String _name = refY.getName();
        GenFeature _opposite = refX.getOpposite();
        String _name_1 = _opposite.getName();
        Assert.assertEquals(_name, _name_1);
        String _name_2 = refX.getName();
        GenFeature _opposite_1 = refY.getOpposite();
        String _name_3 = _opposite_1.getName();
        Assert.assertEquals(_name_2, _name_3);
        EList<EObject> _contents_2 = resource.getContents();
        int _size = _contents_2.size();
        Assert.assertEquals(elements, _size);
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
      EList<XClassifier> _classifiers = pack.getClassifiers();
      XClassifier _head = IterableExtensions.<XClassifier>head(_classifiers);
      final XClass clazz = ((XClass) _head);
      EList<XMember> _members = clazz.getMembers();
      XMember _head_1 = IterableExtensions.<XMember>head(_members);
      final XOperation operation = ((XOperation) _head_1);
      Resource _eResource = clazz.eResource();
      EList<Diagnostic> _errors = _eResource.getErrors();
      boolean _isEmpty = _errors.isEmpty();
      Assert.assertTrue(_isEmpty);
      XGenericType _type = operation.getType();
      Assert.assertNull(_type);
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
      Resource _eResource = pack.eResource();
      EList<Diagnostic> _errors = _eResource.getErrors();
      String _string = _errors.toString();
      Resource _eResource_1 = pack.eResource();
      EList<Diagnostic> _errors_1 = _eResource_1.getErrors();
      int _size = _errors_1.size();
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
      EList<XClassifier> _classifiers = pack.getClassifiers();
      XClassifier _head = IterableExtensions.<XClassifier>head(_classifiers);
      final XClass clazz = ((XClass) _head);
      EList<XMember> _members = clazz.getMembers();
      XMember _head_1 = IterableExtensions.<XMember>head(_members);
      final XAttribute attribute = ((XAttribute) _head_1);
      XGenericType _type = attribute.getType();
      GenBase _type_1 = _type.getType();
      Assert.assertTrue((_type_1 instanceof GenClassifier));
      XGenericType _type_2 = attribute.getType();
      GenBase _type_3 = _type_2.getType();
      String _name = ((GenClassifier) _type_3).getName();
      Assert.assertEquals("EString", _name);
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
      Resource _eResource = pack.eResource();
      EcoreUtil.resolveAll(_eResource);
      this.vth.assertNoErrors(pack);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
