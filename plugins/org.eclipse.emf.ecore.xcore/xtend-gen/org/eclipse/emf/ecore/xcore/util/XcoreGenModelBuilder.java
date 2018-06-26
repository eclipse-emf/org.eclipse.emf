/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.xcore.util;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
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
    final EPackage ePackage = this.mapper.getMapping(pack).getEPackage();
    final GenModel genModel = GenModelFactory.eINSTANCE.createGenModel();
    genModel.setComplianceLevel(GenJDKLevel.JDK50_LITERAL);
    genModel.initialize(Collections.<EPackage>singleton(ePackage));
    pack.eResource().getContents().add(1, genModel);
    this.buildMap(genModel);
    return genModel;
  }
  
  public void buildMap(final GenModel genModel) {
    Iterable<EObject> _allContentsIterable = XcoreExtensions.allContentsIterable(genModel);
    for (final EObject genElement : _allContentsIterable) {
      boolean _matched = false;
      if (genElement instanceof GenPackage) {
        _matched=true;
        XNamedElement _xcoreElement = this.mapper.getToXcoreMapping(((GenPackage)genElement).getEcorePackage()).getXcoreElement();
        final XPackage xPackage = ((XPackage) _xcoreElement);
        if ((xPackage != null)) {
          XPackageMapping _mapping = this.mapper.getMapping(xPackage);
          _mapping.setGenPackage(((GenPackage)genElement));
          ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(genElement);
          _toXcoreMapping.setXcoreElement(xPackage);
        }
      }
      if (!_matched) {
        if (genElement instanceof GenClass) {
          _matched=true;
          XNamedElement _xcoreElement = this.mapper.getToXcoreMapping(((GenClass)genElement).getEcoreClass()).getXcoreElement();
          final XClass xClass = ((XClass) _xcoreElement);
          if ((xClass != null)) {
            XClassMapping _mapping = this.mapper.getMapping(xClass);
            _mapping.setGenClass(((GenClass)genElement));
            ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(genElement);
            _toXcoreMapping.setXcoreElement(xClass);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenDataType) {
          _matched=true;
          XNamedElement _xcoreElement = this.mapper.getToXcoreMapping(((GenDataType)genElement).getEcoreDataType()).getXcoreElement();
          final XDataType xDataType = ((XDataType) _xcoreElement);
          if ((xDataType != null)) {
            XDataTypeMapping _mapping = this.mapper.getMapping(xDataType);
            _mapping.setGenDataType(((GenDataType)genElement));
            ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(genElement);
            _toXcoreMapping.setXcoreElement(xDataType);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenFeature) {
          _matched=true;
          XNamedElement _xcoreElement = this.mapper.getToXcoreMapping(((GenFeature)genElement).getEcoreFeature()).getXcoreElement();
          final XStructuralFeature xFeature = ((XStructuralFeature) _xcoreElement);
          if ((xFeature != null)) {
            XFeatureMapping _mapping = this.mapper.getMapping(xFeature);
            _mapping.setGenFeature(((GenFeature)genElement));
            ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(genElement);
            _toXcoreMapping.setXcoreElement(xFeature);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenOperation) {
          _matched=true;
          XNamedElement _xcoreElement = this.mapper.getToXcoreMapping(((GenOperation)genElement).getEcoreOperation()).getXcoreElement();
          final XOperation xOperation = ((XOperation) _xcoreElement);
          if ((xOperation != null)) {
            XOperationMapping _mapping = this.mapper.getMapping(xOperation);
            _mapping.setGenOperation(((GenOperation)genElement));
            ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(genElement);
            _toXcoreMapping.setXcoreElement(xOperation);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenParameter) {
          _matched=true;
          XNamedElement _xcoreElement = this.mapper.getToXcoreMapping(((GenParameter)genElement).getEcoreParameter()).getXcoreElement();
          final XParameter xParameter = ((XParameter) _xcoreElement);
          if ((xParameter != null)) {
            XParameterMapping _mapping = this.mapper.getMapping(xParameter);
            _mapping.setGenParameter(((GenParameter)genElement));
            ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(genElement);
            _toXcoreMapping.setXcoreElement(xParameter);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenTypeParameter) {
          _matched=true;
          XNamedElement _xcoreElement = this.mapper.getToXcoreMapping(((GenTypeParameter)genElement).getEcoreTypeParameter()).getXcoreElement();
          final XTypeParameter xTypeParameter = ((XTypeParameter) _xcoreElement);
          if ((xTypeParameter != null)) {
            XTypeParameterMapping _mapping = this.mapper.getMapping(xTypeParameter);
            _mapping.setGenTypeParameter(((GenTypeParameter)genElement));
            ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(genElement);
            _toXcoreMapping.setXcoreElement(xTypeParameter);
          }
        }
      }
      if (!_matched) {
        if (genElement instanceof GenEnumLiteral) {
          _matched=true;
          XNamedElement _xcoreElement = this.mapper.getToXcoreMapping(((GenEnumLiteral)genElement).getEcoreEnumLiteral()).getXcoreElement();
          final XEnumLiteral xEnumLiteral = ((XEnumLiteral) _xcoreElement);
          if ((xEnumLiteral != null)) {
            XEnumLiteralMapping _mapping = this.mapper.getMapping(xEnumLiteral);
            _mapping.setGenEnumLiteral(((GenEnumLiteral)genElement));
            ToXcoreMapping _toXcoreMapping = this.mapper.getToXcoreMapping(genElement);
            _toXcoreMapping.setXcoreElement(xEnumLiteral);
          }
        }
      }
    }
  }
  
  public void initializeUsedGenPackages(final GenModel genModel) {
    final LinkedHashSet<EPackage> referencedEPackages = new LinkedHashSet<EPackage>();
    final List<EPackage> ePackages = new UniqueEList<EPackage>();
    EList<GenPackage> _genPackages = genModel.getGenPackages();
    for (final GenPackage genPackage : _genPackages) {
      {
        final EPackage ePackage = genPackage.getEcorePackage();
        if ((ePackage != null)) {
          ePackages.add(genPackage.getEcorePackage());
        }
      }
    }
    int i = 0;
    while ((i < ePackages.size())) {
      {
        final EPackage ePackage = ePackages.get(i);
        i = (i + 1);
        final TreeIterator<EObject> allContents = ePackage.eAllContents();
        while (allContents.hasNext()) {
          {
            final EObject eObject = allContents.next();
            if ((eObject instanceof EPackage)) {
              allContents.prune();
            } else {
              EList<EObject> _eCrossReferences = eObject.eCrossReferences();
              for (final EObject eCrossReference : _eCrossReferences) {
                boolean _matched = false;
                if (eCrossReference instanceof EClassifier) {
                  _matched=true;
                  final EPackage referencedEPackage = ((EClassifier)eCrossReference).getEPackage();
                  if ((referencedEPackage != null)) {
                    boolean _add = ePackages.add(referencedEPackage);
                    if (_add) {
                      referencedEPackages.add(referencedEPackage);
                    }
                  }
                }
                if (!_matched) {
                  if (eCrossReference instanceof EStructuralFeature) {
                    _matched=true;
                    final EClass eContainingClass = ((EStructuralFeature)eCrossReference).getEContainingClass();
                    if ((eContainingClass != null)) {
                      final EPackage referencedEPackage = eContainingClass.getEPackage();
                      if ((referencedEPackage != null)) {
                        boolean _add = ePackages.add(referencedEPackage);
                        if (_add) {
                          referencedEPackages.add(referencedEPackage);
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    for (final EPackage referencedEPackage : referencedEPackages) {
      {
        GenPackage usedGenPackage = genModel.findGenPackage(referencedEPackage);
        if ((usedGenPackage == null)) {
          GenBase _gen = this.mapper.getGen(this.mapper.getToXcoreMapping(referencedEPackage).getXcoreElement());
          usedGenPackage = ((GenPackage) _gen);
          if ((usedGenPackage == null)) {
            usedGenPackage = this.findLocalGenPackage(referencedEPackage);
          }
        }
        if ((usedGenPackage != null)) {
          Resource _eResource = usedGenPackage.eResource();
          boolean _tripleNotEquals = (_eResource != null);
          if (_tripleNotEquals) {
            genModel.getUsedGenPackages().add(usedGenPackage);
          }
        } else {
          final Resource genModelResource = genModel.eResource();
          final EList<Resource> resources = genModelResource.getResourceSet().getResources();
          i = 0;
          boolean found = false;
          while (((i < resources.size()) && (!found))) {
            {
              final Resource resource = resources.get(i);
              boolean _notEquals = (!Objects.equal(resource, genModelResource));
              if (_notEquals) {
                final String fileExtension = resource.getURI().fileExtension();
                boolean _equals = "xcore".equals(fileExtension);
                if (_equals) {
                  final EList<EObject> contents = resource.getContents();
                  boolean _isEmpty = contents.isEmpty();
                  boolean _not = (!_isEmpty);
                  if (_not) {
                    EObject _get = resource.getContents().get(1);
                    final GenModel usedGenModel = ((GenModel) _get);
                    usedGenPackage = usedGenModel.findGenPackage(referencedEPackage);
                    if ((usedGenPackage != null)) {
                      genModel.getUsedGenPackages().add(usedGenPackage);
                      found = true;
                    }
                  }
                } else {
                  boolean _equals_1 = "genmodel".equals(fileExtension);
                  if (_equals_1) {
                    final EList<EObject> contents_1 = resource.getContents();
                    boolean _isEmpty_1 = contents_1.isEmpty();
                    boolean _not_1 = (!_isEmpty_1);
                    if (_not_1) {
                      EObject _get_1 = resource.getContents().get(0);
                      final GenModel usedGenModel_1 = ((GenModel) _get_1);
                      usedGenModel_1.reconcile();
                      usedGenPackage = usedGenModel_1.findGenPackage(referencedEPackage);
                      if ((usedGenPackage != null)) {
                        genModel.getUsedGenPackages().add(usedGenPackage);
                        found = true;
                      }
                    }
                  }
                }
              }
              i = (i + 1);
            }
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
    boolean _tripleNotEquals = (_eResource != null);
    if (_tripleNotEquals) {
      EList<EObject> _contents = ePackage.eResource().getContents();
      for (final EObject content : _contents) {
        if ((content instanceof GenModel)) {
          final GenPackage genPackage = ((GenModel)content).findGenPackage(ePackage);
          if ((genPackage != null)) {
            return genPackage;
          }
        }
      }
    }
    return null;
  }
}
