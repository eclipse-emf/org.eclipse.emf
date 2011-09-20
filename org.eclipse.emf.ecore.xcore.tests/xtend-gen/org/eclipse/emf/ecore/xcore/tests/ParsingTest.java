package org.eclipse.emf.ecore.xcore.tests;

import com.google.inject.Inject;
import java.io.IOException;
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
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.lib.ComparableExtensions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xtend2.lib.StringConcatenation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(XcoreInjectorProvider.class)
public class ParsingTest extends Object {
  
  @Inject
  private ParseHelper<XPackage> parser;
  
  @Inject
  private XcoreExtensions exts;
  
  @Inject
  private ValidationTestHelper vth;
  
  @Test
  public void parseSimpleFile() throws Exception {
    {
      XPackage _parse = this.parser.parse("package foo");
      final XPackage parse = _parse;
      String _name = parse.getName();
      Assert.assertEquals("foo", _name);
    }
  }
  
  @Test
  public void testJvmTypes() throws Exception {
    {
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
      XPackage _parse = this.parser.parse(_builder);
      final XPackage pack = _parse;
      Resource _eResource = pack.eResource();
      EcoreUtil.resolveAll(_eResource);
      this.vth.assertNoErrors(pack);
    }
  }
  
  @Test
  public void testSuperTypeLinking_1() throws Exception {
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo ");
      _builder.newLine();
      _builder.append("class A {} ");
      _builder.newLine();
      _builder.append("class B extends A {}");
      _builder.newLine();
      XPackage _parse = this.parser.parse(_builder);
      final XPackage pack = _parse;
      EList<XClassifier> _classifiers = pack.getClassifiers();
      XClassifier _get = _classifiers.get(1);
      final XClass clazz = ((XClass) _get);
      EList<XGenericType> _superTypes = clazz.getSuperTypes();
      XGenericType _head = IterableExtensions.<XGenericType>head(_superTypes);
      GenClass _genClass = this.exts.getGenClass(_head);
      String _name = _genClass.getName();
      Assert.assertEquals("A", _name);
    }
  }
  
  @Test
  public void testSuperTypeLinking_2() throws Exception {
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo ");
      _builder.newLine();
      _builder.append("class A {} ");
      _builder.newLine();
      _builder.append("class B extends foo.A {}");
      _builder.newLine();
      XPackage _parse = this.parser.parse(_builder);
      final XPackage pack = _parse;
      EList<XClassifier> _classifiers = pack.getClassifiers();
      XClassifier _get = _classifiers.get(1);
      final XClass clazz = ((XClass) _get);
      EList<XGenericType> _superTypes = clazz.getSuperTypes();
      XGenericType _head = IterableExtensions.<XGenericType>head(_superTypes);
      GenClass _genClass = this.exts.getGenClass(_head);
      String _name = _genClass.getName();
      Assert.assertEquals("A", _name);
    }
  }
  
  @Test
  public void testReferenceToAnnotation() throws Exception {
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo ");
      _builder.newLine();
      _builder.append("annotation \'foo/bar\' as foo");
      _builder.newLine();
      _builder.append("@foo(holla=\'bar\')");
      _builder.newLine();
      _builder.append("class A {} ");
      _builder.newLine();
      XPackage _parse = this.parser.parse(_builder);
      final XPackage pack = _parse;
      EList<XClassifier> _classifiers = pack.getClassifiers();
      XClassifier _get = _classifiers.get(0);
      final XClass clazz = ((XClass) _get);
      EList<XAnnotationDirective> _annotationDirectives = pack.getAnnotationDirectives();
      XAnnotationDirective _head = IterableExtensions.<XAnnotationDirective>head(_annotationDirectives);
      EList<XAnnotation> _annotations = clazz.getAnnotations();
      XAnnotation _head_1 = IterableExtensions.<XAnnotation>head(_annotations);
      XAnnotationDirective _source = _head_1.getSource();
      Assert.assertEquals(_head, _source);
    }
  }
  
  @Test
  public void testReferenceToOpposite() throws IOException, Exception {
    {
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
      String _string = _builder.toString();
      final String text = _string;
      XPackage _parse = this.parser.parse(text);
      final XPackage pack = _parse;
      {
        EList<XClassifier> _classifiers = pack.getClassifiers();
        XClassifier _get = _classifiers.get(0);
        final XClass clazz = ((XClass) _get);
        EList<XMember> _members = clazz.getMembers();
        Iterable<XReference> _filter = IterableExtensions.<XReference>filter(_members, org.eclipse.emf.ecore.xcore.XReference.class);
        Iterator<XReference> _iterator = _filter.iterator();
        final Iterator<XReference> refs = _iterator;
        XReference _next = refs.next();
        XReference refX = _next;
        XReference _next_1 = refs.next();
        XReference refY = _next_1;
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
      int _size = _contents.size();
      final int elements = _size;
      int _length = text.length();
      resource.update(0, _length, text);
      {
        EList<EObject> _contents_1 = resource.getContents();
        EObject _get_1 = _contents_1.get(0);
        EList<XClassifier> _classifiers_1 = ((XPackage) _get_1).getClassifiers();
        XClassifier _get_2 = _classifiers_1.get(0);
        final XClass clazz_1 = ((XClass) _get_2);
        EList<XMember> _members_1 = clazz_1.getMembers();
        Iterable<XReference> _filter_1 = IterableExtensions.<XReference>filter(_members_1, org.eclipse.emf.ecore.xcore.XReference.class);
        Iterator<XReference> _iterator_1 = _filter_1.iterator();
        final Iterator<XReference> refs_1 = _iterator_1;
        XReference _next_2 = refs_1.next();
        XReference refX_1 = _next_2;
        XReference _next_3 = refs_1.next();
        XReference refY_1 = _next_3;
        String _name_4 = refY_1.getName();
        GenFeature _opposite_2 = refX_1.getOpposite();
        String _name_5 = _opposite_2.getName();
        Assert.assertEquals(_name_4, _name_5);
        String _name_6 = refX_1.getName();
        GenFeature _opposite_3 = refY_1.getOpposite();
        String _name_7 = _opposite_3.getName();
        Assert.assertEquals(_name_6, _name_7);
        EList<EObject> _contents_2 = resource.getContents();
        int _size_1 = _contents_2.size();
        Assert.assertEquals(elements, _size_1);
      }
      resource.reparse(text);
      {
        EList<EObject> _contents_3 = resource.getContents();
        EObject _get_3 = _contents_3.get(0);
        EList<XClassifier> _classifiers_2 = ((XPackage) _get_3).getClassifiers();
        XClassifier _get_4 = _classifiers_2.get(0);
        final XClass clazz_2 = ((XClass) _get_4);
        EList<XMember> _members_2 = clazz_2.getMembers();
        Iterable<XReference> _filter_2 = IterableExtensions.<XReference>filter(_members_2, org.eclipse.emf.ecore.xcore.XReference.class);
        Iterator<XReference> _iterator_2 = _filter_2.iterator();
        final Iterator<XReference> refs_2 = _iterator_2;
        XReference _next_4 = refs_2.next();
        XReference refX_2 = _next_4;
        XReference _next_5 = refs_2.next();
        XReference refY_2 = _next_5;
        String _name_8 = refY_2.getName();
        GenFeature _opposite_4 = refX_2.getOpposite();
        String _name_9 = _opposite_4.getName();
        Assert.assertEquals(_name_8, _name_9);
        String _name_10 = refX_2.getName();
        GenFeature _opposite_5 = refY_2.getOpposite();
        String _name_11 = _opposite_5.getName();
        Assert.assertEquals(_name_10, _name_11);
        EList<EObject> _contents_4 = resource.getContents();
        int _size_2 = _contents_4.size();
        Assert.assertEquals(elements, _size_2);
      }
    }
  }
  
  @Test
  public void operationReturnsVoid() throws Exception {
    {
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
      XPackage _parse = this.parser.parse(_builder);
      final XPackage pack = _parse;
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
    }
  }
  
  @Test
  public void referenceMayNotBeVoid() throws Exception {
    {
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
      XPackage _parse = this.parser.parse(_builder);
      final XPackage pack = _parse;
      Resource _eResource = pack.eResource();
      EList<Diagnostic> _errors = _eResource.getErrors();
      String _string = _errors.toString();
      Resource _eResource_1 = pack.eResource();
      EList<Diagnostic> _errors_1 = _eResource_1.getErrors();
      int _size = _errors_1.size();
      boolean _operator_lessEqualsThan = ComparableExtensions.<Integer>operator_lessEqualsThan(((Integer)1), ((Integer)_size));
      Assert.assertTrue(_string, _operator_lessEqualsThan);
    }
  }
  
  @Test
  public void stringResolvesToEString() throws Exception {
    {
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
      XPackage _parse = this.parser.parse(_builder);
      final XPackage pack = _parse;
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
    }
  }
  
  @Test
  public void testEcoreDataTypeAliases() throws Exception {
    {
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
      XPackage _parse = this.parser.parse(_builder);
      final XPackage pack = _parse;
      Resource _eResource = pack.eResource();
      EcoreUtil.resolveAll(_eResource);
      this.vth.assertNoErrors(pack);
    }
  }
}