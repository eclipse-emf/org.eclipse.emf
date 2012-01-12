package org.eclipse.emf.ecore.xcore.tests;

import com.google.inject.Inject;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XGenericType;
import org.eclipse.emf.ecore.xcore.XMember;
import org.eclipse.emf.ecore.xcore.XReference;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.StringInputStream;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xtend2.lib.ResourceExtensions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(XcoreInjectorProvider.class)
public class MultiFileTest {
  @Inject
  private XtextResourceSet resourceSet;
  
  @Inject
  private ValidationTestHelper validator;
  
  @Inject
  private IResourceDescriptions descriptions;
  
  @Inject
  private XcoreMapper mapper;
  
  @Test
  public void testReferenceBetweenTwoModels() {
    try {
      {
        URI _createURI = URI.createURI("file:/modelA.xcore");
        Resource _createResource = this.resourceSet.createResource(_createURI);
        final Resource resourceA = _createResource;
        URI _createURI_1 = URI.createURI("file:/modelB.xcore");
        Resource _createResource_1 = this.resourceSet.createResource(_createURI_1);
        final Resource resourceB = _createResource_1;
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
        EList<EObject> _contents = resourceA.getContents();
        EObject _head = IterableExtensions.<EObject>head(_contents);
        this.validator.assertNoErrors(_head);
        EList<EObject> _contents_1 = resourceB.getContents();
        EObject _head_1 = IterableExtensions.<EObject>head(_contents_1);
        this.validator.assertNoErrors(_head_1);
        Iterable<EObject> _allContentsIterable = ResourceExtensions.allContentsIterable(resourceA);
        final Iterable<EObject> allContents = _allContentsIterable;
        Iterable<XClass> _filter = IterableExtensions.<XClass>filter(allContents, org.eclipse.emf.ecore.xcore.XClass.class);
        XClass _head_2 = IterableExtensions.<XClass>head(_filter);
        final XClass xclass = _head_2;
        EList<XMember> _members = xclass.getMembers();
        XMember _head_3 = IterableExtensions.<XMember>head(_members);
        XGenericType _type = _head_3.getType();
        GenBase _type_1 = _type.getType();
        final GenBase referencedGenClass = _type_1;
        String _name = ((GenClass) referencedGenClass).getName();
        Assert.assertEquals("TypeB", _name);
      }
    } catch (Exception _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void testBidirectionalReferenceBetweenTwoModels() {
    try {
      {
        URI _createURI = URI.createURI("file:/modelA.xcore");
        Resource _createResource = this.resourceSet.createResource(_createURI);
        final Resource resourceA = _createResource;
        URI _createURI_1 = URI.createURI("file:/modelB.xcore");
        Resource _createResource_1 = this.resourceSet.createResource(_createURI_1);
        final Resource resourceB = _createResource_1;
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
        EList<EObject> _contents = resourceA.getContents();
        EObject _head = IterableExtensions.<EObject>head(_contents);
        this.validator.assertNoErrors(_head);
        EList<EObject> _contents_1 = resourceB.getContents();
        EObject _head_1 = IterableExtensions.<EObject>head(_contents_1);
        this.validator.assertNoErrors(_head_1);
        Iterable<EObject> _allContentsIterable = ResourceExtensions.allContentsIterable(resourceA);
        final Iterable<EObject> allContents = _allContentsIterable;
        Iterable<XClass> _filter = IterableExtensions.<XClass>filter(allContents, org.eclipse.emf.ecore.xcore.XClass.class);
        XClass _head_2 = IterableExtensions.<XClass>head(_filter);
        final XClass xclass = _head_2;
        EList<XMember> _members = xclass.getMembers();
        XMember _head_3 = IterableExtensions.<XMember>head(_members);
        XGenericType _type = _head_3.getType();
        GenBase _type_1 = _type.getType();
        final GenBase referencedGenClass = _type_1;
        String _name = ((GenClass) referencedGenClass).getName();
        Assert.assertEquals("TypeB", _name);
        EList<XMember> _members_1 = xclass.getMembers();
        XMember _head_4 = IterableExtensions.<XMember>head(_members_1);
        final XReference ref = ((XReference) _head_4);
        GenFeature _opposite = ref.getOpposite();
        XStructuralFeature _xFeature = this.mapper.getXFeature(_opposite);
        GenFeature _opposite_1 = ((XReference) _xFeature).getOpposite();
        XStructuralFeature _xFeature_1 = this.mapper.getXFeature(_opposite_1);
        Assert.assertEquals(ref, _xFeature_1);
      }
    } catch (Exception _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
