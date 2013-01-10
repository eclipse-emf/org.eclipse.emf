/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.generator;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.Collections;
import java.util.Set;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.XcoreExtensions;
import org.eclipse.emf.ecore.xcore.generator.XcoreAppendable;
import org.eclipse.emf.ecore.xcore.generator.XcoreGeneratorImpl;
import org.eclipse.emf.ecore.xcore.mappings.XDataTypeMapping;
import org.eclipse.emf.ecore.xcore.mappings.XFeatureMapping;
import org.eclipse.emf.ecore.xcore.mappings.XOperationMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;

@SuppressWarnings("all")
public class XcoreGenerator implements IGenerator {
  @Inject
  private XcoreMapper mappings;
  
  @Inject
  private XbaseCompiler compiler;
  
  @Inject
  private Provider<XcoreGeneratorImpl> xcoreGeneratorImplProvider;
  
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    EList<EObject> _contents = resource.getContents();
    EObject _head = IterableExtensions.<EObject>head(_contents);
    final XPackage pack = ((XPackage) _head);
    Iterable<EObject> _allContentsIterable = XcoreExtensions.allContentsIterable(pack);
    Iterable<XOperation> _filter = Iterables.<XOperation>filter(_allContentsIterable, XOperation.class);
    for (final XOperation op : _filter) {
      {
        XOperationMapping _mapping = this.mappings.getMapping(op);
        final EOperation eOperation = _mapping.getEOperation();
        final XBlockExpression body = op.getBody();
        boolean _notEquals = ObjectExtensions.operator_notEquals(body, null);
        if (_notEquals) {
          XOperationMapping _mapping_1 = this.mappings.getMapping(op);
          final JvmOperation jvmOperation = _mapping_1.getJvmOperation();
          boolean _notEquals_1 = ObjectExtensions.operator_notEquals(jvmOperation, null);
          if (_notEquals_1) {
            final XcoreAppendable appendable = this.createAppendable();
            appendable.declareVariable(jvmOperation, "this");
            JvmTypeReference _returnType = jvmOperation.getReturnType();
            Set<JvmTypeReference> _emptySet = Collections.<JvmTypeReference>emptySet();
            this.compiler.compile(body, appendable, _returnType, _emptySet);
            String _string = appendable.toString();
            String _extractBody = this.extractBody(_string);
            EcoreUtil.setAnnotation(eOperation, GenModelPackage.eNS_URI, "body", _extractBody);
          }
        }
      }
    }
    Iterable<EObject> _allContentsIterable_1 = XcoreExtensions.allContentsIterable(pack);
    Iterable<XStructuralFeature> _filter_1 = Iterables.<XStructuralFeature>filter(_allContentsIterable_1, XStructuralFeature.class);
    for (final XStructuralFeature feature : _filter_1) {
      {
        XFeatureMapping _mapping = this.mappings.getMapping(feature);
        final EStructuralFeature eStructuralFeature = _mapping.getEStructuralFeature();
        final XBlockExpression getBody = feature.getGetBody();
        boolean _notEquals = ObjectExtensions.operator_notEquals(getBody, null);
        if (_notEquals) {
          XFeatureMapping _mapping_1 = this.mappings.getMapping(feature);
          final JvmOperation getter = _mapping_1.getGetter();
          final XcoreAppendable appendable = this.createAppendable();
          JvmDeclaredType _declaringType = getter.getDeclaringType();
          appendable.declareVariable(_declaringType, "this");
          JvmTypeReference _returnType = getter.getReturnType();
          Set<JvmTypeReference> _emptySet = Collections.<JvmTypeReference>emptySet();
          this.compiler.compile(getBody, appendable, _returnType, _emptySet);
          String _string = appendable.toString();
          String _extractBody = this.extractBody(_string);
          EcoreUtil.setAnnotation(eStructuralFeature, GenModelPackage.eNS_URI, "get", _extractBody);
        }
      }
    }
    Iterable<EObject> _allContentsIterable_2 = XcoreExtensions.allContentsIterable(pack);
    Iterable<XDataType> _filter_2 = Iterables.<XDataType>filter(_allContentsIterable_2, XDataType.class);
    for (final XDataType dataType : _filter_2) {
      {
        XDataTypeMapping _mapping = this.mappings.getMapping(dataType);
        final EDataType eDataType = _mapping.getEDataType();
        final XBlockExpression createBody = dataType.getCreateBody();
        XDataTypeMapping _mapping_1 = this.mappings.getMapping(dataType);
        final JvmOperation creator = _mapping_1.getCreator();
        boolean _and = false;
        boolean _notEquals = ObjectExtensions.operator_notEquals(createBody, null);
        if (!_notEquals) {
          _and = false;
        } else {
          boolean _notEquals_1 = ObjectExtensions.operator_notEquals(creator, null);
          _and = (_notEquals && _notEquals_1);
        }
        if (_and) {
          final XcoreAppendable appendable = this.createAppendable();
          EList<JvmFormalParameter> _parameters = creator.getParameters();
          JvmFormalParameter _get = _parameters.get(0);
          appendable.declareVariable(_get, "it");
          JvmTypeReference _returnType = creator.getReturnType();
          Set<JvmTypeReference> _emptySet = Collections.<JvmTypeReference>emptySet();
          this.compiler.compile(createBody, appendable, _returnType, _emptySet);
          String _string = appendable.toString();
          String _extractBody = this.extractBody(_string);
          EcoreUtil.setAnnotation(eDataType, GenModelPackage.eNS_URI, "create", _extractBody);
        }
        final XBlockExpression convertBody = dataType.getConvertBody();
        XDataTypeMapping _mapping_2 = this.mappings.getMapping(dataType);
        final JvmOperation converter = _mapping_2.getConverter();
        boolean _and_1 = false;
        boolean _notEquals_2 = ObjectExtensions.operator_notEquals(convertBody, null);
        if (!_notEquals_2) {
          _and_1 = false;
        } else {
          boolean _notEquals_3 = ObjectExtensions.operator_notEquals(converter, null);
          _and_1 = (_notEquals_2 && _notEquals_3);
        }
        if (_and_1) {
          final XcoreAppendable appendable_1 = this.createAppendable();
          EList<JvmFormalParameter> _parameters_1 = converter.getParameters();
          JvmFormalParameter _get_1 = _parameters_1.get(0);
          appendable_1.declareVariable(_get_1, "it");
          JvmTypeReference _returnType_1 = converter.getReturnType();
          Set<JvmTypeReference> _emptySet_1 = Collections.<JvmTypeReference>emptySet();
          this.compiler.compile(convertBody, appendable_1, _returnType_1, _emptySet_1);
          String _string_1 = appendable_1.toString();
          String _extractBody_1 = this.extractBody(_string_1);
          EcoreUtil.setAnnotation(eDataType, GenModelPackage.eNS_URI, "convert", _extractBody_1);
        }
      }
    }
    EList<EObject> _contents_1 = resource.getContents();
    Iterable<GenModel> _filter_3 = Iterables.<GenModel>filter(_contents_1, GenModel.class);
    GenModel _head_1 = IterableExtensions.<GenModel>head(_filter_3);
    this.generateGenModel(_head_1, fsa);
  }
  
  public XcoreAppendable createAppendable() {
    XcoreAppendable _xcoreAppendable = new XcoreAppendable();
    return _xcoreAppendable;
  }
  
  public String extractBody(final String body) {
    String _xblockexpression = null;
    {
      String _xifexpression = null;
      boolean _startsWith = body.startsWith("\n");
      if (_startsWith) {
        String _substring = body.substring(1);
        _xifexpression = _substring;
      } else {
        _xifexpression = body;
      }
      String result = _xifexpression;
      String _xifexpression_1 = null;
      boolean _startsWith_1 = result.startsWith("{\n");
      if (_startsWith_1) {
        String _xblockexpression_1 = null;
        {
          String _replace = result.replace("\n\t", "\n");
          result = _replace;
          int _length = result.length();
          int _minus = (_length - 2);
          String _substring_1 = result.substring(1, _minus);
          _xblockexpression_1 = (_substring_1);
        }
        _xifexpression_1 = _xblockexpression_1;
      } else {
        _xifexpression_1 = result;
      }
      _xblockexpression = (_xifexpression_1);
    }
    return _xblockexpression;
  }
  
  public Diagnostic generateGenModel(final GenModel genModel, final IFileSystemAccess fsa) {
    Diagnostic _xifexpression = null;
    String _modelDirectory = genModel.getModelDirectory();
    boolean _notEquals = ObjectExtensions.operator_notEquals(_modelDirectory, null);
    if (_notEquals) {
      Diagnostic _xblockexpression = null;
      {
        genModel.setCanGenerate(true);
        final XcoreGeneratorImpl generator = this.xcoreGeneratorImplProvider.get();
        generator.setInput(genModel);
        generator.setFileSystemAccess(fsa);
        String _modelDirectory_1 = genModel.getModelDirectory();
        generator.setModelDirectory(_modelDirectory_1);
        BasicMonitor _basicMonitor = new BasicMonitor();
        generator.generate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, _basicMonitor);
        BasicMonitor _basicMonitor_1 = new BasicMonitor();
        generator.generate(genModel, GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE, _basicMonitor_1);
        BasicMonitor _basicMonitor_2 = new BasicMonitor();
        generator.generate(genModel, GenBaseGeneratorAdapter.EDITOR_PROJECT_TYPE, _basicMonitor_2);
        BasicMonitor _basicMonitor_3 = new BasicMonitor();
        Diagnostic _generate = generator.generate(genModel, GenBaseGeneratorAdapter.TESTS_PROJECT_TYPE, _basicMonitor_3);
        _xblockexpression = (_generate);
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
}
