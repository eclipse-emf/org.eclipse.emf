package org.eclipse.emf.ecore.xcore.tests;

import com.google.inject.Inject;
import java.util.Iterator;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xcore.XAnnotation;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XGenericType;
import org.eclipse.emf.ecore.xcore.XMember;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XReference;
import org.eclipse.emf.ecore.xcore.XcoreExtensions;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.resource.XtextResource;
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
  
  @Test
  public void testReferenceToOpposite() throws Exception {
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
      _builder.append("}");
      _builder.newLine();
      XPackage _parse = this.parser.parse(_builder);
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
      resource.update(0, 0, " ");
      {
        EList<EObject> _contents = resource.getContents();
        EObject _get_1 = _contents.get(0);
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
      }
    }
  }
}