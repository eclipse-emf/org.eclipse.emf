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
          _matched=true;
          EPackage _ecorePackage = ((GenPackage)genElement).getEcorePackage();
          ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(_ecorePackage);
          XNamedElement _xcoreElement = _toXcoreMapping.getXcoreElement();
          final XPackage xPackage = ((XPackage) _xcoreElement);
          boolean _notEquals = (!Objects.equal(xPackage, null));
          if (_notEquals) {
            XPackageMapping _mapping = this.mapper.getMapping(xPackage);
            _mapping.setGenPackage(((GenPackage)genElement));
            ToXcoreMapping _toXcoreMapping_1 = this.mapper.getToXcoreMapping(genElement);
            _toXcoreMapping_1.setXcoreElement(xPackage);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenClass) {
          _matched=true;
          EClass _ecoreClass = ((GenClass)genElement).getEcoreClass();
          ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(_ecoreClass);
          XNamedElement _xcoreElement = _toXcoreMapping.getXcoreElement();
          final XClass xClass = ((XClass) _xcoreElement);
          boolean _notEquals = (!Objects.equal(xClass, null));
          if (_notEquals) {
            XClassMapping _mapping = this.mapper.getMapping(xClass);
            _mapping.setGenClass(((GenClass)genElement));
            ToXcoreMapping _toXcoreMapping_1 = this.mapper.getToXcoreMapping(genElement);
            _toXcoreMapping_1.setXcoreElement(xClass);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenDataType) {
          _matched=true;
          EDataType _ecoreDataType = ((GenDataType)genElement).getEcoreDataType();
          ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(_ecoreDataType);
          XNamedElement _xcoreElement = _toXcoreMapping.getXcoreElement();
          final XDataType xDataType = ((XDataType) _xcoreElement);
          boolean _notEquals = (!Objects.equal(xDataType, null));
          if (_notEquals) {
            XDataTypeMapping _mapping = this.mapper.getMapping(xDataType);
            _mapping.setGenDataType(((GenDataType)genElement));
            ToXcoreMapping _toXcoreMapping_1 = this.mapper.getToXcoreMapping(genElement);
            _toXcoreMapping_1.setXcoreElement(xDataType);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenFeature) {
          _matched=true;
          EStructuralFeature _ecoreFeature = ((GenFeature)genElement).getEcoreFeature();
          ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(_ecoreFeature);
          XNamedElement _xcoreElement = _toXcoreMapping.getXcoreElement();
          final XStructuralFeature xFeature = ((XStructuralFeature) _xcoreElement);
          boolean _notEquals = (!Objects.equal(xFeature, null));
          if (_notEquals) {
            XFeatureMapping _mapping = this.mapper.getMapping(xFeature);
            _mapping.setGenFeature(((GenFeature)genElement));
            ToXcoreMapping _toXcoreMapping_1 = this.mapper.getToXcoreMapping(genElement);
            _toXcoreMapping_1.setXcoreElement(xFeature);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenOperation) {
          _matched=true;
          EOperation _ecoreOperation = ((GenOperation)genElement).getEcoreOperation();
          ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(_ecoreOperation);
          XNamedElement _xcoreElement = _toXcoreMapping.getXcoreElement();
          final XOperation xOperation = ((XOperation) _xcoreElement);
          boolean _notEquals = (!Objects.equal(xOperation, null));
          if (_notEquals) {
            XOperationMapping _mapping = this.mapper.getMapping(xOperation);
            _mapping.setGenOperation(((GenOperation)genElement));
            ToXcoreMapping _toXcoreMapping_1 = this.mapper.getToXcoreMapping(genElement);
            _toXcoreMapping_1.setXcoreElement(xOperation);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenParameter) {
          _matched=true;
          EParameter _ecoreParameter = ((GenParameter)genElement).getEcoreParameter();
          ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(_ecoreParameter);
          XNamedElement _xcoreElement = _toXcoreMapping.getXcoreElement();
          final XParameter xParameter = ((XParameter) _xcoreElement);
          boolean _notEquals = (!Objects.equal(xParameter, null));
          if (_notEquals) {
            XParameterMapping _mapping = this.mapper.getMapping(xParameter);
            _mapping.setGenParameter(((GenParameter)genElement));
            ToXcoreMapping _toXcoreMapping_1 = this.mapper.getToXcoreMapping(genElement);
            _toXcoreMapping_1.setXcoreElement(xParameter);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenTypeParameter) {
          _matched=true;
          ETypeParameter _ecoreTypeParameter = ((GenTypeParameter)genElement).getEcoreTypeParameter();
          ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(_ecoreTypeParameter);
          XNamedElement _xcoreElement = _toXcoreMapping.getXcoreElement();
          final XTypeParameter xTypeParameter = ((XTypeParameter) _xcoreElement);
          boolean _notEquals = (!Objects.equal(xTypeParameter, null));
          if (_notEquals) {
            XTypeParameterMapping _mapping = this.mapper.getMapping(xTypeParameter);
            _mapping.setGenTypeParameter(((GenTypeParameter)genElement));
            ToXcoreMapping _toXcoreMapping_1 = this.mapper.getToXcoreMapping(genElement);
            _toXcoreMapping_1.setXcoreElement(xTypeParameter);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenEnumLiteral) {
          _matched=true;
          EEnumLiteral _ecoreEnumLiteral = ((GenEnumLiteral)genElement).getEcoreEnumLiteral();
          ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(_ecoreEnumLiteral);
          XNamedElement _xcoreElement = _toXcoreMapping.getXcoreElement();
          final XEnumLiteral xEnumLiteral = ((XEnumLiteral) _xcoreElement);
          boolean _notEquals = (!Objects.equal(xEnumLiteral, null));
          if (_notEquals) {
            XEnumLiteralMapping _mapping = this.mapper.getMapping(xEnumLiteral);
            _mapping.setGenEnumLiteral(((GenEnumLiteral)genElement));
            ToXcoreMapping _toXcoreMapping_1 = this.mapper.getToXcoreMapping(genElement);
            _toXcoreMapping_1.setXcoreElement(xEnumLiteral);
          }
        }
      }
    }
  }
  
  public void initializeUsedGenPackages(final GenModel genModel) {
    final HashSet<EPackage> referencedEPackages = new HashSet<EPackage>();
    final List<EPackage> ePackages = new UniqueEList<EPackage>();
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
        i = (i + 1);
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
                    _matched=true;
                    final EPackage referencedEPackage = ((EClassifier)eCrossReference).getEPackage();
                    ePackages.add(referencedEPackage);
                    referencedEPackages.add(referencedEPackage);
                  }
                }
                if (!_matched) {
                  if (eCrossReference instanceof EStructuralFeature) {
                    _matched=true;
                    final EClass eContainingClass = ((EStructuralFeature)eCrossReference).getEContainingClass();
                    boolean _notEquals = (!Objects.equal(eContainingClass, null));
                    if (_notEquals) {
                      final EPackage referencedEPackage = eContainingClass.getEPackage();
                      ePackages.add(referencedEPackage);
                      referencedEPackages.add(referencedEPackage);
                    }
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
          final Resource genModelResource = genModel.eResource();
          ResourceSet _resourceSet = genModelResource.getResourceSet();
          final EList<Resource> resources = _resourceSet.getResources();
          i = 0;
          boolean found = false;
          boolean _and = false;
          int _size_1 = resources.size();
          boolean _lessThan_1 = (i < _size_1);
          if (!_lessThan_1) {
            _and = false;
          } else {
            _and = (!found);
          }
          boolean _while_1 = _and;
          while (_while_1) {
            {
              final Resource resource = resources.get(i);
              boolean _notEquals_1 = (!Objects.equal(resource, genModelResource));
              if (_notEquals_1) {
                URI _uRI = resource.getURI();
                final String fileExtension = _uRI.fileExtension();
                boolean _equals_2 = "xcore".equals(fileExtension);
                if (_equals_2) {
                  final EList<EObject> contents = resource.getContents();
                  boolean _isEmpty = contents.isEmpty();
                  boolean _not = (!_isEmpty);
                  if (_not) {
                    EList<EObject> _contents = resource.getContents();
                    EObject _get = _contents.get(1);
                    final GenModel usedGenModel = ((GenModel) _get);
                    GenPackage _findGenPackage_1 = usedGenModel.findGenPackage(referencedEPackage);
                    usedGenPackage = _findGenPackage_1;
                    boolean _notEquals_2 = (!Objects.equal(usedGenPackage, null));
                    if (_notEquals_2) {
                      EList<GenPackage> _usedGenPackages_1 = genModel.getUsedGenPackages();
                      _usedGenPackages_1.add(usedGenPackage);
                      found = true;
                    }
                  }
                } else {
                  boolean _equals_3 = "genmodel".equals(fileExtension);
                  if (_equals_3) {
                    final EList<EObject> contents_1 = resource.getContents();
                    boolean _isEmpty_1 = contents_1.isEmpty();
                    boolean _not_1 = (!_isEmpty_1);
                    if (_not_1) {
                      EList<EObject> _contents_1 = resource.getContents();
                      EObject _get_1 = _contents_1.get(0);
                      final GenModel usedGenModel_1 = ((GenModel) _get_1);
                      usedGenModel_1.reconcile();
                      GenPackage _findGenPackage_2 = usedGenModel_1.findGenPackage(referencedEPackage);
                      usedGenPackage = _findGenPackage_2;
                      boolean _notEquals_3 = (!Objects.equal(usedGenPackage, null));
                      if (_notEquals_3) {
                        EList<GenPackage> _usedGenPackages_2 = genModel.getUsedGenPackages();
                        _usedGenPackages_2.add(usedGenPackage);
                        found = true;
                      }
                    }
                  }
                }
              }
              i = (i + 1);
            }
            boolean _and_1 = false;
            int _size_2 = resources.size();
            boolean _lessThan_2 = (i < _size_2);
            if (!_lessThan_2) {
              _and_1 = false;
            } else {
              _and_1 = (!found);
            }
            _while_1 = _and_1;
          }
          if ((!found)) {
            throw new RuntimeException(("No GenPackage found for " + referencedEPackage));
          }
        }
      }
    }
  }
  
  public GenPackage findLocalGenPackage(final EPackage ePackage) {
    Resource _eResource = ePackage.eResource();
    boolean _notEquals = (!Objects.equal(_eResource, null));
    if (_notEquals) {
      Resource _eResource_1 = ePackage.eResource();
      EList<EObject> _contents = _eResource_1.getContents();
      for (final EObject content : _contents) {
        if ((content instanceof GenModel)) {
          final GenPackage genPackage = ((GenModel)content).findGenPackage(ePackage);
          boolean _notEquals_1 = (!Objects.equal(genPackage, null));
          if (_notEquals_1) {
            return genPackage;
          }
        }
      }
    }
    return null;
  }
}
