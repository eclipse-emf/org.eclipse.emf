/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.xcore.generator;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
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
import org.eclipse.emf.ecore.xcore.mappings.XOperationMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.linking.impl.XtextLinkingDiagnostic;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.compiler.XbaseCompiler;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
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
  
  public void generateBodyAnnotations(final XPackage pack) {
    final LinkedHashMap<EObject, String> errors = this.getErrors(pack);
    final HashSet<ETypedElement> processed = CollectionLiterals.<ETypedElement>newHashSet();
    EList<XClassifier> _classifiers = pack.getClassifiers();
    for (final XClassifier xClassifier : _classifiers) {
      if ((xClassifier instanceof XDataType)) {
        final XDataType xDataType = ((XDataType)xClassifier);
        final EDataType eDataType = this.mappings.getMapping(xDataType).getEDataType();
        final XBlockExpression createBody = xDataType.getCreateBody();
        final JvmOperation creator = this.mappings.getMapping(xDataType).getCreator();
        if (((createBody != null) && (creator != null))) {
          final XcoreAppendable appendable = this.createAppendable();
          appendable.declareVariable(creator.getParameters().get(0), "it");
          this.compile(eDataType, "create", appendable, errors, createBody, creator.getReturnType(), Collections.<JvmTypeReference>emptySet());
        }
        final XBlockExpression convertBody = xDataType.getConvertBody();
        final JvmOperation converter = this.mappings.getMapping(xDataType).getConverter();
        if (((convertBody != null) && (converter != null))) {
          final XcoreAppendable appendable_1 = this.createAppendable();
          appendable_1.declareVariable(converter.getParameters().get(0), "it");
          this.compile(eDataType, "convert", appendable_1, errors, convertBody, converter.getReturnType(), Collections.<JvmTypeReference>emptySet());
        }
      } else {
        final XClass xClass = ((XClass) xClassifier);
        final EClass eClass = this.mappings.getMapping(xClass).getEClass();
        EList<EStructuralFeature> _eAllStructuralFeatures = eClass.getEAllStructuralFeatures();
        for (final EStructuralFeature eStructuralFeature : _eAllStructuralFeatures) {
          boolean _add = processed.add(eStructuralFeature);
          if (_add) {
            final XStructuralFeature xFeature = this.mappings.getXFeature(eStructuralFeature);
            if ((xFeature != null)) {
              final XBlockExpression getBody = xFeature.getGetBody();
              if ((getBody != null)) {
                final JvmOperation getter = this.mappings.getMapping(xFeature).getGetter();
                final XcoreAppendable appendable_2 = this.createAppendable();
                appendable_2.declareVariable(getter.getDeclaringType(), "this");
                final JvmTypeReference superType = IterableExtensions.<JvmTypeReference>head(getter.getDeclaringType().getSuperTypes());
                if ((superType != null)) {
                  appendable_2.declareVariable(superType.getType(), "super");
                }
                this.compile(eStructuralFeature, "get", appendable_2, errors, getBody, getter.getReturnType(), Collections.<JvmTypeReference>emptySet());
              }
            }
          }
        }
        EList<EOperation> _eAllOperations = eClass.getEAllOperations();
        for (final EOperation eOperation : _eAllOperations) {
          boolean _add_1 = processed.add(eOperation);
          if (_add_1) {
            final XOperation xOperation = this.mappings.getXOperation(eOperation);
            if ((xOperation != null)) {
              final XBlockExpression body = xOperation.getBody();
              if ((body != null)) {
                final XOperationMapping xOperationMapping = this.mappings.getMapping(xOperation);
                final JvmOperation jvmOperation = xOperationMapping.getJvmOperation();
                if ((jvmOperation != null)) {
                  final XcoreAppendable appendable_3 = this.createAppendable();
                  JvmDeclaredType declaringType = jvmOperation.getDeclaringType();
                  boolean _isExternalInterface = xOperationMapping.getGenOperation().getGenClass().isExternalInterface();
                  if (_isExternalInterface) {
                    final EList<JvmTypeReference> superTypes = declaringType.getSuperTypes();
                    final JvmTypeReference effectiveTypeReference = IterableExtensions.<JvmTypeReference>head(superTypes);
                    if ((effectiveTypeReference != null)) {
                      appendable_3.declareVariable(effectiveTypeReference.getType(), "this");
                    }
                  } else {
                    appendable_3.declareVariable(declaringType, "this");
                    final JvmTypeReference superType_1 = IterableExtensions.<JvmTypeReference>head(declaringType.getSuperTypes());
                    if ((superType_1 != null)) {
                      appendable_3.declareVariable(superType_1.getType(), "super");
                    }
                  }
                  EList<JvmFormalParameter> _parameters = jvmOperation.getParameters();
                  for (final JvmFormalParameter parameter : _parameters) {
                    appendable_3.declareVariable(parameter, parameter.getName());
                  }
                  JvmTypeReference _returnType = jvmOperation.getReturnType();
                  EList<JvmTypeReference> _exceptions = jvmOperation.getExceptions();
                  HashSet<JvmTypeReference> _hashSet = new HashSet<JvmTypeReference>(_exceptions);
                  this.compile(eOperation, "body", appendable_3, errors, body, _returnType, _hashSet);
                }
              }
            }
          }
        }
      }
    }
  }
  
  @Override
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    EObject _head = IterableExtensions.<EObject>head(resource.getContents());
    this.generateBodyAnnotations(((XPackage) _head));
    this.generateGenModel(IterableExtensions.<GenModel>head(Iterables.<GenModel>filter(resource.getContents(), GenModel.class)), fsa);
  }
  
  public void compile(final EModelElement target, final String key, final ITreeAppendable appendable, final Map<EObject, String> errors, final XBlockExpression body, final JvmTypeReference returnType, final Set<JvmTypeReference> exceptions) {
    try {
      Set<Map.Entry<EObject, String>> _entrySet = errors.entrySet();
      for (final Map.Entry<EObject, String> error : _entrySet) {
        boolean _isAncestor = EcoreUtil.isAncestor(body, error.getKey());
        if (_isAncestor) {
          String _value = error.getValue();
          throw new RuntimeException(_value);
        }
      }
      this.compiler.compile(body, appendable, returnType, exceptions);
      EcoreUtil.setAnnotation(target, GenModelPackage.eNS_URI, key, this.extractBody(appendable.toString()));
    } catch (final Throwable _t) {
      if (_t instanceof Throwable) {
        final Throwable throwable = (Throwable)_t;
        String _xifexpression = null;
        String _message = throwable.getMessage();
        boolean _tripleEquals = (_message == null);
        if (_tripleEquals) {
          _xifexpression = "throw new <%java.lang.Error%>(\"Unresolved compilation problem\");";
        } else {
          String _unicodeEscapeEncode = CodeGenUtil.unicodeEscapeEncode(throwable.getMessage());
          String _plus = ("throw new <%java.lang.Error%>(\"Unresolved compilation problems: " + _unicodeEscapeEncode);
          _xifexpression = (_plus + "\");");
        }
        final String message = _xifexpression;
        EcoreUtil.setAnnotation(target, GenModelPackage.eNS_URI, key, message);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  public LinkedHashMap<EObject, String> getErrors(final XPackage xPackage) {
    final LinkedHashMap<EObject, String> result = Maps.<EObject, String>newLinkedHashMap();
    final Resource resource = xPackage.eResource();
    EList<Resource.Diagnostic> _errors = resource.getErrors();
    for (final Resource.Diagnostic diagnostic : _errors) {
      if ((diagnostic instanceof XtextLinkingDiagnostic)) {
        final URI uri = ((XtextLinkingDiagnostic)diagnostic).getUriToProblem();
        if ((uri != null)) {
          final EObject eObject = resource.getEObject(uri.fragment());
          if ((eObject != null)) {
            result.put(eObject, ((XtextLinkingDiagnostic)diagnostic).getMessage());
          }
        }
      }
    }
    return result;
  }
  
  public XcoreAppendable createAppendable() {
    return new XcoreAppendable();
  }
  
  public String extractBody(final String body) {
    String _xblockexpression = null;
    {
      String _xifexpression = null;
      boolean _startsWith = body.startsWith("\n");
      if (_startsWith) {
        _xifexpression = body.substring(1);
      } else {
        _xifexpression = body;
      }
      String result = _xifexpression;
      String _xifexpression_1 = null;
      boolean _startsWith_1 = result.startsWith("{\n");
      if (_startsWith_1) {
        String _xblockexpression_1 = null;
        {
          result = result.replace("\n\t", "\n");
          int _length = result.length();
          int _minus = (_length - 2);
          _xblockexpression_1 = result.substring(1, _minus);
        }
        _xifexpression_1 = _xblockexpression_1;
      } else {
        _xifexpression_1 = result;
      }
      _xblockexpression = _xifexpression_1;
    }
    return _xblockexpression;
  }
  
  public Diagnostic generateGenModel(final GenModel genModel, final IFileSystemAccess fsa) {
    Diagnostic _xifexpression = null;
    String _modelDirectory = genModel.getModelDirectory();
    boolean _tripleNotEquals = (_modelDirectory != null);
    if (_tripleNotEquals) {
      Diagnostic _xblockexpression = null;
      {
        genModel.setCanGenerate(true);
        final XcoreGeneratorImpl generator = this.xcoreGeneratorImplProvider.get();
        generator.setInput(genModel);
        generator.setFileSystemAccess(fsa);
        generator.setModelDirectory(genModel.getModelDirectory());
        BasicMonitor _basicMonitor = new BasicMonitor();
        generator.generate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, _basicMonitor);
        BasicMonitor _basicMonitor_1 = new BasicMonitor();
        generator.generate(genModel, GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE, _basicMonitor_1);
        BasicMonitor _basicMonitor_2 = new BasicMonitor();
        generator.generate(genModel, GenBaseGeneratorAdapter.EDITOR_PROJECT_TYPE, _basicMonitor_2);
        BasicMonitor _basicMonitor_3 = new BasicMonitor();
        _xblockexpression = generator.generate(genModel, GenBaseGeneratorAdapter.TESTS_PROJECT_TYPE, _basicMonitor_3);
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
}
