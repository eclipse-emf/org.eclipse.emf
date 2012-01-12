package org.eclipse.emf.ecore.xcore.util;

import com.google.inject.Inject;
import java.util.Collections;
import java.util.HashSet;
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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XEnumLiteral;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.mappings.ToXcoreMapping;
import org.eclipse.emf.ecore.xcore.mappings.XClassMapping;
import org.eclipse.emf.ecore.xcore.mappings.XDataTypeMapping;
import org.eclipse.emf.ecore.xcore.mappings.XEnumLiteralMapping;
import org.eclipse.emf.ecore.xcore.mappings.XFeatureMapping;
import org.eclipse.emf.ecore.xcore.mappings.XOperationMapping;
import org.eclipse.emf.ecore.xcore.mappings.XPackageMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.util.XcoreGenModelInitializer;
import org.eclipse.xtext.xbase.lib.IntegerExtensions;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xtend2.lib.EObjectExtensions;

@SuppressWarnings("all")
public class XcoreGenmodelBuilder {
  @Inject
  private XcoreMapper mapper;
  
  @Inject
  private XcoreGenModelInitializer genModelInitializer;
  
  public GenModel getGenModel(final XPackage pack) {
      XPackageMapping _mapping = this.mapper.getMapping(pack);
      EPackage _ePackage = _mapping.getEPackage();
      final EPackage ePackage = _ePackage;
      GenModel _createGenModel = GenModelFactory.eINSTANCE.createGenModel();
      final GenModel genModel = _createGenModel;
      Set<EPackage> _singleton = Collections.<EPackage>singleton(ePackage);
      genModel.initialize(_singleton);
      Resource _eResource = pack.eResource();
      EList<EObject> _contents = _eResource.getContents();
      _contents.add(1, genModel);
      this.genModelInitializer.initialize(genModel);
      this.buildMap(genModel);
      return genModel;
  }
  
  public void buildMap(final GenModel genModel) {
    Iterable<EObject> _allContentsIterable = EObjectExtensions.allContentsIterable(genModel);
    for (final EObject genElement : _allContentsIterable) {
      boolean matched = false;
      if (!matched) {
        if (genElement instanceof GenPackage) {
          final GenPackage _genPackage = (GenPackage)genElement;
          matched=true;
          {
            EPackage _ecorePackage = _genPackage.getEcorePackage();
            ToXcoreMapping _xcoreMapping = this.mapper.getToXcoreMapping(_ecorePackage);
            XNamedElement _xcoreElement = _xcoreMapping.getXcoreElement();
            final XPackage xPack = ((XPackage) _xcoreElement);
            XPackageMapping _mapping = this.mapper.getMapping(xPack);
            _mapping.setGenPackage(_genPackage);
            ToXcoreMapping _xcoreMapping_1 = this.mapper.getToXcoreMapping(_genPackage);
            _xcoreMapping_1.setXcoreElement(xPack);
          }
        }
      }
      if (!matched) {
        if (genElement instanceof GenClass) {
          final GenClass _genClass = (GenClass)genElement;
          matched=true;
          {
            EClass _ecoreClass = _genClass.getEcoreClass();
            ToXcoreMapping _xcoreMapping = this.mapper.getToXcoreMapping(_ecoreClass);
            XNamedElement _xcoreElement = _xcoreMapping.getXcoreElement();
            final XClass xClass = ((XClass) _xcoreElement);
            XClassMapping _mapping = this.mapper.getMapping(xClass);
            _mapping.setGenClass(_genClass);
            ToXcoreMapping _xcoreMapping_1 = this.mapper.getToXcoreMapping(_genClass);
            _xcoreMapping_1.setXcoreElement(xClass);
          }
        }
      }
      if (!matched) {
        if (genElement instanceof GenDataType) {
          final GenDataType _genDataType = (GenDataType)genElement;
          matched=true;
          {
            EDataType _ecoreDataType = _genDataType.getEcoreDataType();
            ToXcoreMapping _xcoreMapping = this.mapper.getToXcoreMapping(_ecoreDataType);
            XNamedElement _xcoreElement = _xcoreMapping.getXcoreElement();
            final XDataType xDataType = ((XDataType) _xcoreElement);
            XDataTypeMapping _mapping = this.mapper.getMapping(xDataType);
            _mapping.setGenDataType(_genDataType);
            ToXcoreMapping _xcoreMapping_1 = this.mapper.getToXcoreMapping(_genDataType);
            _xcoreMapping_1.setXcoreElement(xDataType);
          }
        }
      }
      if (!matched) {
        if (genElement instanceof GenFeature) {
          final GenFeature _genFeature = (GenFeature)genElement;
          matched=true;
          {
            EStructuralFeature _ecoreFeature = _genFeature.getEcoreFeature();
            ToXcoreMapping _xcoreMapping = this.mapper.getToXcoreMapping(_ecoreFeature);
            XNamedElement _xcoreElement = _xcoreMapping.getXcoreElement();
            final XStructuralFeature xFeature = ((XStructuralFeature) _xcoreElement);
            XFeatureMapping _mapping = this.mapper.getMapping(xFeature);
            _mapping.setGenFeature(_genFeature);
            ToXcoreMapping _xcoreMapping_1 = this.mapper.getToXcoreMapping(_genFeature);
            _xcoreMapping_1.setXcoreElement(xFeature);
          }
        }
      }
      if (!matched) {
        if (genElement instanceof GenOperation) {
          final GenOperation _genOperation = (GenOperation)genElement;
          matched=true;
          {
            EOperation _ecoreOperation = _genOperation.getEcoreOperation();
            ToXcoreMapping _xcoreMapping = this.mapper.getToXcoreMapping(_ecoreOperation);
            XNamedElement _xcoreElement = _xcoreMapping.getXcoreElement();
            final XOperation xOperation = ((XOperation) _xcoreElement);
            XOperationMapping _mapping = this.mapper.getMapping(xOperation);
            _mapping.setGenOperation(_genOperation);
            ToXcoreMapping _xcoreMapping_1 = this.mapper.getToXcoreMapping(_genOperation);
            _xcoreMapping_1.setXcoreElement(xOperation);
          }
        }
      }
      if (!matched) {
        if (genElement instanceof GenEnumLiteral) {
          final GenEnumLiteral _genEnumLiteral = (GenEnumLiteral)genElement;
          matched=true;
          {
            EEnumLiteral _ecoreEnumLiteral = _genEnumLiteral.getEcoreEnumLiteral();
            ToXcoreMapping _xcoreMapping = this.mapper.getToXcoreMapping(_ecoreEnumLiteral);
            XNamedElement _xcoreElement = _xcoreMapping.getXcoreElement();
            final XEnumLiteral xEnumLiteral = ((XEnumLiteral) _xcoreElement);
            XEnumLiteralMapping _mapping = this.mapper.getMapping(xEnumLiteral);
            _mapping.setGenEnumLiteral(_genEnumLiteral);
            ToXcoreMapping _xcoreMapping_1 = this.mapper.getToXcoreMapping(_genEnumLiteral);
            _xcoreMapping_1.setXcoreElement(xEnumLiteral);
          }
        }
      }
    }
  }
  
  public void initializeUsedGenPackages(final GenModel genModel) {
      HashSet<EPackage> _hashSet = new HashSet<EPackage>();
      final HashSet<EPackage> referencedEPackages = _hashSet;
      EList<GenPackage> _genPackages = genModel.getGenPackages();
      for (final GenPackage genPackage : _genPackages) {
        EPackage _ecorePackage = genPackage.getEcorePackage();
        Iterable<EObject> _allContentsIterable = EObjectExtensions.allContentsIterable(_ecorePackage);
        for (final EObject eObject : _allContentsIterable) {
          EList<EObject> _eCrossReferences = eObject.eCrossReferences();
          for (final EObject eCrossReference : _eCrossReferences) {
            boolean matched = false;
            if (!matched) {
              if (eCrossReference instanceof EClassifier) {
                final EClassifier _eClassifier = (EClassifier)eCrossReference;
                matched=true;
                EPackage _ePackage = _eClassifier.getEPackage();
                referencedEPackages.add(_ePackage);
              }
            }
            if (!matched) {
              if (eCrossReference instanceof EStructuralFeature) {
                final EStructuralFeature _eStructuralFeature = (EStructuralFeature)eCrossReference;
                matched=true;
                EClass _eContainingClass = _eStructuralFeature.getEContainingClass();
                EPackage _ePackage = _eContainingClass.getEPackage();
                referencedEPackages.add(_ePackage);
              }
            }
          }
        }
      }
      for (final EPackage referencedEPackage : referencedEPackages) {
        GenPackage _findGenPackage = genModel.findGenPackage(referencedEPackage);
        boolean _operator_equals = ObjectExtensions.operator_equals(_findGenPackage, null);
        if (_operator_equals) {
          {
            ToXcoreMapping _xcoreMapping = this.mapper.getToXcoreMapping(referencedEPackage);
            XNamedElement _xcoreElement = _xcoreMapping.getXcoreElement();
            GenBase _gen = this.mapper.getGen(_xcoreElement);
            GenPackage usedGenPackage = ((GenPackage) _gen);
            boolean _operator_equals_1 = ObjectExtensions.operator_equals(usedGenPackage, null);
            if (_operator_equals_1) {
              GenPackage _findLocalGenPackage = this.findLocalGenPackage(referencedEPackage);
              usedGenPackage = _findLocalGenPackage;
            }
            boolean _operator_notEquals = ObjectExtensions.operator_notEquals(usedGenPackage, null);
            if (_operator_notEquals) {
              EList<GenPackage> _usedGenPackages = genModel.getUsedGenPackages();
              _usedGenPackages.add(usedGenPackage);
            } else {
              {
                Resource _eResource = genModel.eResource();
                ResourceSet _resourceSet = _eResource.getResourceSet();
                EList<Resource> _resources = _resourceSet.getResources();
                final EList<Resource> resources = _resources;
                int i = 0;
                boolean found = false;
                int _size = resources.size();
                boolean _operator_lessThan = IntegerExtensions.operator_lessThan(i, _size);
                boolean _while = _operator_lessThan;
                while (_while) {
                  {
                    Resource _get = resources.get(i);
                    final Resource resource = _get;
                    URI _uRI = resource.getURI();
                    String _fileExtension = _uRI.fileExtension();
                    boolean _equals = "genmodel".equals(_fileExtension);
                    if (_equals) {
                      {
                        EList<EObject> _contents = resource.getContents();
                        EObject _get_1 = _contents.get(0);
                        GenPackage _findGenPackage_1 = ((GenModel) _get_1).findGenPackage(referencedEPackage);
                        usedGenPackage = _findGenPackage_1;
                        boolean _operator_notEquals_1 = ObjectExtensions.operator_notEquals(usedGenPackage, null);
                        if (_operator_notEquals_1) {
                          {
                            EList<GenPackage> _usedGenPackages_1 = genModel.getUsedGenPackages();
                            _usedGenPackages_1.add(usedGenPackage);
                            found = true;
                          }
                        }
                      }
                    }
                    int _operator_plus = IntegerExtensions.operator_plus(i, 1);
                    i = _operator_plus;
                  }
                  int _size_1 = resources.size();
                  boolean _operator_lessThan_1 = IntegerExtensions.operator_lessThan(i, _size_1);
                  _while = _operator_lessThan_1;
                }
              }
            }
          }
        }
      }
  }
  
  public GenPackage findLocalGenPackage(final EPackage ePackage) {
    GenPackage _xifexpression = null;
    Resource _eResource = ePackage.eResource();
    boolean _operator_notEquals = ObjectExtensions.operator_notEquals(_eResource, null);
    if (_operator_notEquals) {
      Resource _eResource_1 = ePackage.eResource();
      EList<EObject> _contents = _eResource_1.getContents();
      for (final EObject content : _contents) {
        if ((content instanceof GenModel)) {
          {
            GenPackage _findGenPackage = ((GenModel) content).findGenPackage(ePackage);
            final GenPackage genPackage = _findGenPackage;
            boolean _operator_notEquals_1 = ObjectExtensions.operator_notEquals(genPackage, null);
            if (_operator_notEquals_1) {
              return genPackage;
            }
          }
        }
      }
    }
    return _xifexpression;
  }
}
