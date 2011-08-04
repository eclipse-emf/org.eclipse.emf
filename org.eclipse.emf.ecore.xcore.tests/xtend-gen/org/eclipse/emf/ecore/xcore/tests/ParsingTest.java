package org.eclipse.emf.ecore.xcore.tests;

import com.google.inject.Inject;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.xcore.XAnnotation;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XGenericType;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcoreExtensions;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xtend2.lib.StringConcatenation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(XcoreInjectorProvider.class)
public class ParsingTest {
  
  @Inject
  private ParseHelper<XPackage> parser;
  
  @Inject
  private XcoreExtensions exts;
  
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
}