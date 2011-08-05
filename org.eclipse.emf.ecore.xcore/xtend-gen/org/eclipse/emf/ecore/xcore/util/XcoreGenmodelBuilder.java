package org.eclipse.emf.ecore.xcore.util;

import com.google.inject.Inject;
import java.util.Collections;
import java.util.Set;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.mappings.ToXcoreMapping;
import org.eclipse.emf.ecore.xcore.mappings.XClassMapping;
import org.eclipse.emf.ecore.xcore.mappings.XFeatureMapping;
import org.eclipse.emf.ecore.xcore.mappings.XOperationMapping;
import org.eclipse.emf.ecore.xcore.mappings.XPackageMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.xtend2.lib.EObjectExtensions;

@SuppressWarnings("all")
public class XcoreGenmodelBuilder {
  
  @Inject
  private XcoreMapper mapper;
  
  public void getGenModel(final XPackage pack) {
    {
      XPackageMapping _mapping = this.mapper.getMapping(pack);
      EPackage _ePackage = _mapping.getePackage();
      final EPackage ePackage = _ePackage;
      GenModel _createGenModel = GenModelFactory.eINSTANCE.createGenModel();
      final GenModel genModel = _createGenModel;
      Set<EPackage> _singleton = Collections.<EPackage>singleton(ePackage);
      genModel.initialize(_singleton);
      Resource _eResource = pack.eResource();
      EList<EObject> _contents = _eResource.getContents();
      _contents.add(1, genModel);
      genModel.initialize();
      Iterable<EObject> _allContentsIterable = EObjectExtensions.allContentsIterable(genModel);
      for (EObject genElement : _allContentsIterable) {
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
              XPackageMapping _mapping_1 = this.mapper.getMapping(xPack);
              _mapping_1.setGenPackage(genElement_2);
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
              XClassMapping _mapping_2 = this.mapper.getMapping(xClass);
              _mapping_2.setGenClass(genElement_3);
              ToXcoreMapping _xcoreMapping_3 = this.mapper.getToXcoreMapping(genElement_3);
              _xcoreMapping_3.setXcoreElement(xClass);
            }
          }
        }
        if (!matched) {
          if (genElement_1 instanceof GenFeature) {
            final GenFeature genElement_4 = (GenFeature) genElement_1;
            matched=true;
            {
              EStructuralFeature _ecoreFeature = genElement_4.getEcoreFeature();
              ToXcoreMapping _xcoreMapping_4 = this.mapper.getToXcoreMapping(_ecoreFeature);
              XNamedElement _xcoreElement_2 = _xcoreMapping_4.getXcoreElement();
              final XStructuralFeature xFeature = ((XStructuralFeature) _xcoreElement_2);
              XFeatureMapping _mapping_3 = this.mapper.getMapping(xFeature);
              _mapping_3.setGenFeature(genElement_4);
              ToXcoreMapping _xcoreMapping_5 = this.mapper.getToXcoreMapping(genElement_4);
              _xcoreMapping_5.setXcoreElement(xFeature);
            }
          }
        }
        if (!matched) {
          if (genElement_1 instanceof GenOperation) {
            final GenOperation genElement_5 = (GenOperation) genElement_1;
            matched=true;
            {
              EOperation _ecoreOperation = genElement_5.getEcoreOperation();
              ToXcoreMapping _xcoreMapping_6 = this.mapper.getToXcoreMapping(_ecoreOperation);
              XNamedElement _xcoreElement_3 = _xcoreMapping_6.getXcoreElement();
              final XOperation xOperation = ((XOperation) _xcoreElement_3);
              XOperationMapping _mapping_4 = this.mapper.getMapping(xOperation);
              _mapping_4.setGenOperation(genElement_5);
              ToXcoreMapping _xcoreMapping_7 = this.mapper.getToXcoreMapping(genElement_5);
              _xcoreMapping_7.setXcoreElement(xOperation);
            }
          }
        }
      }
    }
  }
}