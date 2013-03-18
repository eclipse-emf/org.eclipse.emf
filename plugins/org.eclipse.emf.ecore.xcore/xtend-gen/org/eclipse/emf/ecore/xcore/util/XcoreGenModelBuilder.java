/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.util;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XEnumLiteral;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XParameter;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.XTypeParameter;
import org.eclipse.emf.ecore.xcore.XcoreExtensions;
import org.eclipse.emf.ecore.xcore.mappings.ToXcoreMapping;
import org.eclipse.emf.ecore.xcore.mappings.XClassMapping;
import org.eclipse.emf.ecore.xcore.mappings.XDataTypeMapping;
import org.eclipse.emf.ecore.xcore.mappings.XEnumLiteralMapping;
import org.eclipse.emf.ecore.xcore.mappings.XFeatureMapping;
import org.eclipse.emf.ecore.xcore.mappings.XOperationMapping;
import org.eclipse.emf.ecore.xcore.mappings.XPackageMapping;
import org.eclipse.emf.ecore.xcore.mappings.XParameterMapping;
import org.eclipse.emf.ecore.xcore.mappings.XTypeParameterMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class XcoreGenModelBuilder {
  @Inject
  @Extension
  private XcoreMapper mapper;
  
  public GenModel getGenModel(final XPackage pack) {
    XPackageMapping _mapping = this.mapper.getMapping(pack);
    final EPackage ePackage = _mapping.getEPackage();
    final GenModel genModel = GenModelFactory.eINSTANCE.createGenModel();
    Set<EPackage> _singleton = Collections.<EPackage>singleton(ePackage);
    genModel.initialize(_singleton);
    Resource _eResource = pack.eResource();
    EList<EObject> _contents = _eResource.getContents();
    _contents.add(1, genModel);
    this.buildMap(genModel);
    return genModel;
  }
  
  public void buildMap(final GenModel genModel) {
    Iterable<EObject> _allContentsIterable = XcoreExtensions.allContentsIterable(genModel);
    for (final EObject genElement : _allContentsIterable) {
      boolean _matched = false;
      if (!_matched) {
        if (genElement instanceof GenPackage) {
          final GenPackage _genPackage = (GenPackage)genElement;
          _matched=true;
          EPackage _ecorePackage = _genPackage.getEcorePackage();
          ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(_ecorePackage);
          XNamedElement _xcoreElement = _toXcoreMapping.getXcoreElement();
          final XPackage xPackage = ((XPackage) _xcoreElement);
          boolean _notEquals = (!Objects.equal(xPackage, null));
          if (_notEquals) {
            XPackageMapping _mapping = this.mapper.getMapping(xPackage);
            _mapping.setGenPackage(_genPackage);
            ToXcoreMapping _toXcoreMapping_1 = this.mapper.getToXcoreMapping(_genPackage);
            _toXcoreMapping_1.setXcoreElement(xPackage);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenClass) {
          final GenClass _genClass = (GenClass)genElement;
          _matched=true;
          EClass _ecoreClass = _genClass.getEcoreClass();
          ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(_ecoreClass);
          XNamedElement _xcoreElement = _toXcoreMapping.getXcoreElement();
          final XClass xClass = ((XClass) _xcoreElement);
          boolean _notEquals = (!Objects.equal(xClass, null));
          if (_notEquals) {
            XClassMapping _mapping = this.mapper.getMapping(xClass);
            _mapping.setGenClass(_genClass);
            ToXcoreMapping _toXcoreMapping_1 = this.mapper.getToXcoreMapping(_genClass);
            _toXcoreMapping_1.setXcoreElement(xClass);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenDataType) {
          final GenDataType _genDataType = (GenDataType)genElement;
          _matched=true;
          EDataType _ecoreDataType = _genDataType.getEcoreDataType();
          ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(_ecoreDataType);
          XNamedElement _xcoreElement = _toXcoreMapping.getXcoreElement();
          final XDataType xDataType = ((XDataType) _xcoreElement);
          boolean _notEquals = (!Objects.equal(xDataType, null));
          if (_notEquals) {
            XDataTypeMapping _mapping = this.mapper.getMapping(xDataType);
            _mapping.setGenDataType(_genDataType);
            ToXcoreMapping _toXcoreMapping_1 = this.mapper.getToXcoreMapping(_genDataType);
            _toXcoreMapping_1.setXcoreElement(xDataType);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenFeature) {
          final GenFeature _genFeature = (GenFeature)genElement;
          _matched=true;
          EStructuralFeature _ecoreFeature = _genFeature.getEcoreFeature();
          ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(_ecoreFeature);
          XNamedElement _xcoreElement = _toXcoreMapping.getXcoreElement();
          final XStructuralFeature xFeature = ((XStructuralFeature) _xcoreElement);
          boolean _notEquals = (!Objects.equal(xFeature, null));
          if (_notEquals) {
            XFeatureMapping _mapping = this.mapper.getMapping(xFeature);
            _mapping.setGenFeature(_genFeature);
            ToXcoreMapping _toXcoreMapping_1 = this.mapper.getToXcoreMapping(_genFeature);
            _toXcoreMapping_1.setXcoreElement(xFeature);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenOperation) {
          final GenOperation _genOperation = (GenOperation)genElement;
          _matched=true;
          EOperation _ecoreOperation = _genOperation.getEcoreOperation();
          ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(_ecoreOperation);
          XNamedElement _xcoreElement = _toXcoreMapping.getXcoreElement();
          final XOperation xOperation = ((XOperation) _xcoreElement);
          boolean _notEquals = (!Objects.equal(xOperation, null));
          if (_notEquals) {
            XOperationMapping _mapping = this.mapper.getMapping(xOperation);
            _mapping.setGenOperation(_genOperation);
            ToXcoreMapping _toXcoreMapping_1 = this.mapper.getToXcoreMapping(_genOperation);
            _toXcoreMapping_1.setXcoreElement(xOperation);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenParameter) {
          final GenParameter _genParameter = (GenParameter)genElement;
          _matched=true;
          EParameter _ecoreParameter = _genParameter.getEcoreParameter();
          ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(_ecoreParameter);
          XNamedElement _xcoreElement = _toXcoreMapping.getXcoreElement();
          final XParameter xParameter = ((XParameter) _xcoreElement);
          boolean _notEquals = (!Objects.equal(xParameter, null));
          if (_notEquals) {
            XParameterMapping _mapping = this.mapper.getMapping(xParameter);
            _mapping.setGenParameter(_genParameter);
            ToXcoreMapping _toXcoreMapping_1 = this.mapper.getToXcoreMapping(_genParameter);
            _toXcoreMapping_1.setXcoreElement(xParameter);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenTypeParameter) {
          final GenTypeParameter _genTypeParameter = (GenTypeParameter)genElement;
          _matched=true;
          ETypeParameter _ecoreTypeParameter = _genTypeParameter.getEcoreTypeParameter();
          ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(_ecoreTypeParameter);
          XNamedElement _xcoreElement = _toXcoreMapping.getXcoreElement();
          final XTypeParameter xTypeParameter = ((XTypeParameter) _xcoreElement);
          boolean _notEquals = (!Objects.equal(xTypeParameter, null));
          if (_notEquals) {
            XTypeParameterMapping _mapping = this.mapper.getMapping(xTypeParameter);
            _mapping.setGenTypeParameter(_genTypeParameter);
            ToXcoreMapping _toXcoreMapping_1 = this.mapper.getToXcoreMapping(_genTypeParameter);
            _toXcoreMapping_1.setXcoreElement(xTypeParameter);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenEnumLiteral) {
          final GenEnumLiteral _genEnumLiteral = (GenEnumLiteral)genElement;
          _matched=true;
          EEnumLiteral _ecoreEnumLiteral = _genEnumLiteral.getEcoreEnumLiteral();
          ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(_ecoreEnumLiteral);
          XNamedElement _xcoreElement = _toXcoreMapping.getXcoreElement();
          final XEnumLiteral xEnumLiteral = ((XEnumLiteral) _xcoreElement);
          boolean _notEquals = (!Objects.equal(xEnumLiteral, null));
          if (_notEquals) {
            XEnumLiteralMapping _mapping = this.mapper.getMapping(xEnumLiteral);
            _mapping.setGenEnumLiteral(_genEnumLiteral);
            ToXcoreMapping _toXcoreMapping_1 = this.mapper.getToXcoreMapping(_genEnumLiteral);
            _toXcoreMapping_1.setXcoreElement(xEnumLiteral);
          }
        }
      }
    }
  }
  
  public void initializeUsedGenPackages(final GenModel genModel) {
    HashSet<EPackage> _hashSet = new HashSet<EPackage>();
    final HashSet<EPackage> referencedEPackages = _hashSet;
    UniqueEList<EPackage> _uniqueEList = new UniqueEList<EPackage>();
    final List<EPackage> ePackages = _uniqueEList;
    EList<GenPackage> _genPackages = genModel.getGenPackages();
    for (final GenPackage genPackage : _genPackages) {
      {
        final EPackage ePackage = genPackage.getEcorePackage();
        boolean _notEquals = (!Objects.equal(ePackage, null));
        if (_notEquals) {
          EPackage _ecorePackage = genPackage.getEcorePackage();
          ePackages.add(_ecorePackage);
        }
      }
    }
    int i = 0;
    int _size = ePackages.size();
    boolean _lessThan = (i < _size);
    boolean _while = _lessThan;
    while (_while) {
      {
        final EPackage ePackage = ePackages.get(i);
        int _plus = (i + 1);
        i = _plus;
        final TreeIterator<EObject> allContents = ePackage.eAllContents();
        boolean _hasNext = allContents.hasNext();
        boolean _while_1 = _hasNext;
        while (_while_1) {
          {
            final EObject eObject = allContents.next();
            if ((eObject instanceof EPackage)) {
              allContents.prune();
            } else {
              EList<EObject> _eCrossReferences = eObject.eCrossReferences();
              for (final EObject eCrossReference : _eCrossReferences) {
                boolean _matched = false;
                if (!_matched) {
                  if (eCrossReference instanceof EClassifier) {
                    final EClassifier _eClassifier = (EClassifier)eCrossReference;
                    _matched=true;
                    final EPackage referencedEPackage = _eClassifier.getEPackage();
                    ePackages.add(referencedEPackage);
                    referencedEPackages.add(referencedEPackage);
                  }
                }
                if (!_matched) {
                  if (eCrossReference instanceof EStructuralFeature) {
                    final EStructuralFeature _eStructuralFeature = (EStructuralFeature)eCrossReference;
                    _matched=true;
                    EClass _eContainingClass = _eStructuralFeature.getEContainingClass();
                    final EPackage referencedEPackage = _eContainingClass.getEPackage();
                    ePackages.add(referencedEPackage);
                    referencedEPackages.add(referencedEPackage);
                  }
                }
              }
            }
          }
          boolean _hasNext_1 = allContents.hasNext();
          _while_1 = _hasNext_1;
        }
      }
      int _size_1 = ePackages.size();
      boolean _lessThan_1 = (i < _size_1);
      _while = _lessThan_1;
    }
    for (final EPackage referencedEPackage : referencedEPackages) {
      GenPackage _findGenPackage = genModel.findGenPackage(referencedEPackage);
      boolean _equals = Objects.equal(_findGenPackage, null);
      if (_equals) {
        ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(referencedEPackage);
        XNamedElement _xcoreElement = _toXcoreMapping.getXcoreElement();
        GenBase _gen = this.mapper.getGen(_xcoreElement);
        GenPackage usedGenPackage = ((GenPackage) _gen);
        boolean _equals_1 = Objects.equal(usedGenPackage, null);
        if (_equals_1) {
          GenPackage _findLocalGenPackage = this.findLocalGenPackage(referencedEPackage);
          usedGenPackage = _findLocalGenPackage;
        }
        boolean _notEquals = (!Objects.equal(usedGenPackage, null));
        if (_notEquals) {
          EList<GenPackage> _usedGenPackages = genModel.getUsedGenPackages();
          _usedGenPackages.add(usedGenPackage);
        } else {
          Resource _eResource = genModel.eResource();
          ResourceSet _resourceSet = _eResource.getResourceSet();
          final EList<Resource> resources = _resourceSet.getResources();
          i = 0;
          boolean found = false;
          boolean _and = false;
          int _size_1 = resources.size();
          boolean _lessThan_1 = (i < _size_1);
          if (!_lessThan_1) {
            _and = false;
          } else {
            boolean _not = (!found);
            _and = (_lessThan_1 && _not);
          }
          boolean _while_1 = _and;
          while (_while_1) {
            {
              final Resource resource = resources.get(i);
              boolean _and_1 = false;
              URI _uRI = resource.getURI();
              String _fileExtension = _uRI.fileExtension();
              boolean _equals_2 = "genmodel".equals(_fileExtension);
              if (!_equals_2) {
                _and_1 = false;
              } else {
                EList<EObject> _contents = resource.getContents();
                boolean _isEmpty = _contents.isEmpty();
                boolean _not_1 = (!_isEmpty);
                _and_1 = (_equals_2 && _not_1);
              }
              if (_and_1) {
                EList<EObject> _contents_1 = resource.getContents();
                EObject _get = _contents_1.get(0);
                final GenModel usedGenModel = ((GenModel) _get);
                usedGenModel.reconcile();
                GenPackage _findGenPackage_1 = usedGenModel.findGenPackage(referencedEPackage);
                usedGenPackage = _findGenPackage_1;
                boolean _notEquals_1 = (!Objects.equal(usedGenPackage, null));
                if (_notEquals_1) {
                  EList<GenPackage> _usedGenPackages_1 = genModel.getUsedGenPackages();
                  _usedGenPackages_1.add(usedGenPackage);
                  found = true;
                }
              }
              int _plus = (i + 1);
              i = _plus;
            }
            boolean _and_1 = false;
            int _size_2 = resources.size();
            boolean _lessThan_2 = (i < _size_2);
            if (!_lessThan_2) {
              _and_1 = false;
            } else {
              boolean _not_1 = (!found);
              _and_1 = (_lessThan_2 && _not_1);
            }
            _while_1 = _and_1;
          }
          boolean _not_1 = (!found);
          if (_not_1) {
            String _plus = ("No GenPackage found for " + referencedEPackage);
            RuntimeException _runtimeException = new RuntimeException(_plus);
            throw _runtimeException;
          }
        }
      }
    }
  }
  
  public GenPackage findLocalGenPackage(final EPackage ePackage) {
    GenPackage _xifexpression = null;
    Resource _eResource = ePackage.eResource();
    boolean _notEquals = (!Objects.equal(_eResource, null));
    if (_notEquals) {
      Resource _eResource_1 = ePackage.eResource();
      EList<EObject> _contents = _eResource_1.getContents();
      for (final EObject content : _contents) {
        if ((content instanceof GenModel)) {
          final GenPackage genPackage = ((GenModel) content).findGenPackage(ePackage);
          boolean _notEquals_1 = (!Objects.equal(genPackage, null));
          if (_notEquals_1) {
            return genPackage;
          }
        }
      }
    }
    return _xifexpression;
  }
}
