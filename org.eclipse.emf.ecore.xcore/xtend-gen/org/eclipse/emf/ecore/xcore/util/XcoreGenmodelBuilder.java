package org.eclipse.emf.ecore.xcore.util;

import com.google.inject.Inject;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XDataType;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.mappings.ToXcoreMapping;
import org.eclipse.emf.ecore.xcore.mappings.XClassMapping;
import org.eclipse.emf.ecore.xcore.mappings.XDataTypeMapping;
import org.eclipse.emf.ecore.xcore.mappings.XFeatureMapping;
import org.eclipse.emf.ecore.xcore.mappings.XOperationMapping;
import org.eclipse.emf.ecore.xcore.mappings.XPackageMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.xbase.lib.ObjectExtensions;
import org.eclipse.xtext.xtend2.lib.EObjectExtensions;

@SuppressWarnings("all")
public class XcoreGenmodelBuilder {
  
  @Inject
  private XcoreMapper mapper;
  
  public GenModel getGenModel(final XPackage pack) {
    {
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
      genModel.initialize();
      this.buildMap(genModel);
      return genModel;
    }
  }
  
  public void buildMap(final GenModel genModel) {
    Iterable<EObject> _allContentsIterable = EObjectExtensions.allContentsIterable(genModel);
    for (final EObject genElement : _allContentsIterable) {
      final EObject genElement_1 = genElement;
      boolean matched = false;
      if (!matched) {
        if (genElement_1 instanceof GenPackage) {
          final GenPackage genElement_2 = (GenPackage) genElement_1;
          matched=true;
          {
            EPackage _ecorePackage = genElement_2.getEcorePackage();
            ToXcoreMapping _xcoreMapping = this.mapper.getToXcoreMapping(_ecorePackage);
            XNamedElement _xcoreElement = _xcoreMapping.getXcoreElement();
            final XPackage xPack = ((XPackage) _xcoreElement);
            XPackageMapping _mapping = this.mapper.getMapping(xPack);
            _mapping.setGenPackage(genElement_2);
            ToXcoreMapping _xcoreMapping_1 = this.mapper.getToXcoreMapping(genElement_2);
            _xcoreMapping_1.setXcoreElement(xPack);
          }
        }
      }
      if (!matched) {
        if (genElement_1 instanceof GenClass) {
          final GenClass genElement_3 = (GenClass) genElement_1;
          matched=true;
          {
            EClass _ecoreClass = genElement_3.getEcoreClass();
            ToXcoreMapping _xcoreMapping_2 = this.mapper.getToXcoreMapping(_ecoreClass);
            XNamedElement _xcoreElement_1 = _xcoreMapping_2.getXcoreElement();
            final XClass xClass = ((XClass) _xcoreElement_1);
            XClassMapping _mapping_1 = this.mapper.getMapping(xClass);
            _mapping_1.setGenClass(genElement_3);
            ToXcoreMapping _xcoreMapping_3 = this.mapper.getToXcoreMapping(genElement_3);
            _xcoreMapping_3.setXcoreElement(xClass);
          }
        }
      }
      if (!matched) {
        if (genElement_1 instanceof GenDataType) {
          final GenDataType genElement_4 = (GenDataType) genElement_1;
          matched=true;
          {
            EDataType _ecoreDataType = genElement_4.getEcoreDataType();
            ToXcoreMapping _xcoreMapping_4 = this.mapper.getToXcoreMapping(_ecoreDataType);
            XNamedElement _xcoreElement_2 = _xcoreMapping_4.getXcoreElement();
            final XDataType xDataType = ((XDataType) _xcoreElement_2);
            XDataTypeMapping _mapping_2 = this.mapper.getMapping(xDataType);
            _mapping_2.setGenDataType(genElement_4);
            ToXcoreMapping _xcoreMapping_5 = this.mapper.getToXcoreMapping(genElement_4);
            _xcoreMapping_5.setXcoreElement(xDataType);
          }
        }
      }
      if (!matched) {
        if (genElement_1 instanceof GenFeature) {
          final GenFeature genElement_5 = (GenFeature) genElement_1;
          matched=true;
          {
            EStructuralFeature _ecoreFeature = genElement_5.getEcoreFeature();
            ToXcoreMapping _xcoreMapping_6 = this.mapper.getToXcoreMapping(_ecoreFeature);
            XNamedElement _xcoreElement_3 = _xcoreMapping_6.getXcoreElement();
            final XStructuralFeature xFeature = ((XStructuralFeature) _xcoreElement_3);
            XFeatureMapping _mapping_3 = this.mapper.getMapping(xFeature);
            _mapping_3.setGenFeature(genElement_5);
            ToXcoreMapping _xcoreMapping_7 = this.mapper.getToXcoreMapping(genElement_5);
            _xcoreMapping_7.setXcoreElement(xFeature);
          }
        }
      }
      if (!matched) {
        if (genElement_1 instanceof GenOperation) {
          final GenOperation genElement_6 = (GenOperation) genElement_1;
          matched=true;
          {
            EOperation _ecoreOperation = genElement_6.getEcoreOperation();
            ToXcoreMapping _xcoreMapping_8 = this.mapper.getToXcoreMapping(_ecoreOperation);
            XNamedElement _xcoreElement_4 = _xcoreMapping_8.getXcoreElement();
            final XOperation xOperation = ((XOperation) _xcoreElement_4);
            XOperationMapping _mapping_4 = this.mapper.getMapping(xOperation);
            _mapping_4.setGenOperation(genElement_6);
            ToXcoreMapping _xcoreMapping_9 = this.mapper.getToXcoreMapping(genElement_6);
            _xcoreMapping_9.setXcoreElement(xOperation);
          }
        }
      }
    }
  }
  
  public void initializeUsedGenPackages(final GenModel genModel) {
    {
      HashSet<EPackage> _hashSet = new HashSet<EPackage>();
      final HashSet<EPackage> referencedEPackages = _hashSet;
      EList<GenPackage> _genPackages = genModel.getGenPackages();
      for (final GenPackage genPackage : _genPackages) {
        EPackage _ecorePackage = genPackage.getEcorePackage();
        Iterable<EObject> _allContentsIterable = EObjectExtensions.allContentsIterable(_ecorePackage);
        for (final EObject eObject : _allContentsIterable) {
          EList<EObject> _eCrossReferences = eObject.eCrossReferences();
          for (final EObject eCrossReference : _eCrossReferences) {
            final EObject eCrossReference_1 = eCrossReference;
            boolean matched = false;
            if (!matched) {
              if (eCrossReference_1 instanceof EClassifier) {
                final EClassifier eCrossReference_2 = (EClassifier) eCrossReference_1;
                matched=true;
                EPackage _ePackage = eCrossReference_2.getEPackage();
                referencedEPackages.add(_ePackage);
              }
            }
            if (!matched) {
              if (eCrossReference_1 instanceof EStructuralFeature) {
                final EStructuralFeature eCrossReference_3 = (EStructuralFeature) eCrossReference_1;
                matched=true;
                EClass _eContainingClass = eCrossReference_3.getEContainingClass();
                EPackage _ePackage_1 = _eContainingClass.getEPackage();
                referencedEPackages.add(_ePackage_1);
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
            boolean _operator_notEquals = ObjectExtensions.operator_notEquals(usedGenPackage, null);
            if (_operator_notEquals) {
              EList<GenPackage> _usedGenPackages = genModel.getUsedGenPackages();
              _usedGenPackages.add(usedGenPackage);
            } else {
              Resource _eResource = genModel.eResource();
              ResourceSet _resourceSet = _eResource.getResourceSet();
              EList<Resource> _resources = _resourceSet.getResources();
              for (final Resource resource : _resources) {
                URI _uRI = resource.getURI();
                String _fileExtension = _uRI.fileExtension();
                boolean _equals = "genmodel".equals(_fileExtension);
                if (_equals) {
                  {
                    EList<EObject> _contents = resource.getContents();
                    EObject _get = _contents.get(0);
                    GenPackage _findGenPackage_1 = ((GenModel) _get).findGenPackage(referencedEPackage);
                    usedGenPackage = _findGenPackage_1;
                    boolean _operator_notEquals_1 = ObjectExtensions.operator_notEquals(usedGenPackage, null);
                    if (_operator_notEquals_1) {
                      EList<GenPackage> _usedGenPackages_1 = genModel.getUsedGenPackages();
                      _usedGenPackages_1.add(usedGenPackage);
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