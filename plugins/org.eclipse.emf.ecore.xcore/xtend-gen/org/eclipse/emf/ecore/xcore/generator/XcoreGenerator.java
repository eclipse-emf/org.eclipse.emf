/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.generator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.generator.XcoreAppendable;
import org.eclipse.emf.ecore.xcore.generator.XcoreGeneratorImpl;
import org.eclipse.emf.ecore.xcore.mappings.XClassMapping;
import org.eclipse.emf.ecore.xcore.mappings.XDataTypeMapping;
import org.eclipse.emf.ecore.xcore.mappings.XFeatureMapping;
import org.eclipse.emf.ecore.xcore.mappings.XOperationMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class XcoreGenerator implements IGenerator {
  @Inject
  @Extension
  private XcoreMapper mappings;
  
  @Inject
  private XbaseCompiler compiler;
  
  @Inject
  private Provider<XcoreGeneratorImpl> xcoreGeneratorImplProvider;
  
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    EList<EObject> _contents = resource.getContents();
    EObject _head = IterableExtensions.<EObject>head(_contents);
    final XPackage pack = ((XPackage) _head);
    final HashSet<ETypedElement> processed = CollectionLiterals.<ETypedElement>newHashSet();
    EList<XClassifier> _classifiers = pack.getClassifiers();
    for (final XClassifier xClassifier : _classifiers) {
      if ((xClassifier instanceof XDataType)) {
        final XDataType xDataType = ((XDataType) xClassifier);
        XDataTypeMapping _mapping = this.mappings.getMapping(xDataType);
        final EDataType eDataType = _mapping.getEDataType();
        final XBlockExpression createBody = xDataType.getCreateBody();
        XDataTypeMapping _mapping_1 = this.mappings.getMapping(xDataType);
        final JvmOperation creator = _mapping_1.getCreator();
        boolean _and = false;
        boolean _notEquals = (!Objects.equal(createBody, null));
        if (!_notEquals) {
          _and = false;
        } else {
          boolean _notEquals_1 = (!Objects.equal(creator, null));
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
        final XBlockExpression convertBody = xDataType.getConvertBody();
        XDataTypeMapping _mapping_2 = this.mappings.getMapping(xDataType);
        final JvmOperation converter = _mapping_2.getConverter();
        boolean _and_1 = false;
        boolean _notEquals_2 = (!Objects.equal(convertBody, null));
        if (!_notEquals_2) {
          _and_1 = false;
        } else {
          boolean _notEquals_3 = (!Objects.equal(converter, null));
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
      } else {
        final XClass xClass = ((XClass) xClassifier);
        XClassMapping _mapping_3 = this.mappings.getMapping(xClass);
        final EClass eClass = _mapping_3.getEClass();
        EList<EStructuralFeature> _eAllStructuralFeatures = eClass.getEAllStructuralFeatures();
        for (final EStructuralFeature eStructuralFeature : _eAllStructuralFeatures) {
          boolean _add = processed.add(eStructuralFeature);
          if (_add) {
            final XStructuralFeature xFeature = this.mappings.getXFeature(eStructuralFeature);
            boolean _notEquals_4 = (!Objects.equal(xFeature, null));
            if (_notEquals_4) {
              final XBlockExpression getBody = xFeature.getGetBody();
              boolean _notEquals_5 = (!Objects.equal(getBody, null));
              if (_notEquals_5) {
                XFeatureMapping _mapping_4 = this.mappings.getMapping(xFeature);
                final JvmOperation getter = _mapping_4.getGetter();
                final XcoreAppendable appendable_2 = this.createAppendable();
                JvmTypeReference _returnType_2 = getter.getReturnType();
                Set<JvmTypeReference> _emptySet_2 = Collections.<JvmTypeReference>emptySet();
                this.compiler.compile(getBody, appendable_2, _returnType_2, _emptySet_2);
                String _string_2 = appendable_2.toString();
                String _extractBody_2 = this.extractBody(_string_2);
                EcoreUtil.setAnnotation(eStructuralFeature, GenModelPackage.eNS_URI, "get", _extractBody_2);
              }
            }
          }
        }
        EList<EOperation> _eAllOperations = eClass.getEAllOperations();
        for (final EOperation eOperation : _eAllOperations) {
          boolean _add_1 = processed.add(eOperation);
          if (_add_1) {
            final XOperation xOperation = this.mappings.getXOperation(eOperation);
            boolean _notEquals_6 = (!Objects.equal(xOperation, null));
            if (_notEquals_6) {
              final XBlockExpression body = xOperation.getBody();
              boolean _notEquals_7 = (!Objects.equal(body, null));
              if (_notEquals_7) {
                XOperationMapping _mapping_5 = this.mappings.getMapping(xOperation);
                final JvmOperation jvmOperation = _mapping_5.getJvmOperation();
                boolean _notEquals_8 = (!Objects.equal(jvmOperation, null));
                if (_notEquals_8) {
                  final XcoreAppendable appendable_3 = this.createAppendable();
                  EList<JvmFormalParameter> _parameters_2 = jvmOperation.getParameters();
                  for (final JvmFormalParameter parameter : _parameters_2) {
                    String _name = parameter.getName();
                    appendable_3.declareVariable(parameter, _name);
                  }
                  JvmTypeReference _returnType_3 = jvmOperation.getReturnType();
                  EList<JvmTypeReference> _exceptions = jvmOperation.getExceptions();
                  HashSet<JvmTypeReference> _hashSet = new HashSet<JvmTypeReference>(_exceptions);
                  this.compiler.compile(body, appendable_3, _returnType_3, _hashSet);
                  String _string_3 = appendable_3.toString();
                  String _extractBody_3 = this.extractBody(_string_3);
                  EcoreUtil.setAnnotation(eOperation, GenModelPackage.eNS_URI, "body", _extractBody_3);
                }
              }
            }
          }
        }
      }
    }
    EList<EObject> _contents_1 = resource.getContents();
    Iterable<GenModel> _filter = Iterables.<GenModel>filter(_contents_1, GenModel.class);
    GenModel _head_1 = IterableExtensions.<GenModel>head(_filter);
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
    boolean _notEquals = (!Objects.equal(_modelDirectory, null));
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
