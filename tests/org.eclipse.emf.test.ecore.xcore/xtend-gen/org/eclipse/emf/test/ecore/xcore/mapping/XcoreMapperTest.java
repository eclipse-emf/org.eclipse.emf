package org.eclipse.emf.test.ecore.xcore.mapping;

import com.google.inject.Inject;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XMember;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XParameter;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.XTypeParameter;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.emf.ecore.xcore.mappings.ToXcoreMapping;
import org.eclipse.emf.ecore.xcore.mappings.XClassMapping;
import org.eclipse.emf.ecore.xcore.mappings.XFeatureMapping;
import org.eclipse.emf.ecore.xcore.mappings.XOperationMapping;
import org.eclipse.emf.ecore.xcore.mappings.XPackageMapping;
import org.eclipse.emf.ecore.xcore.mappings.XParameterMapping;
import org.eclipse.emf.ecore.xcore.mappings.XTypeParameterMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(XcoreInjectorProvider.class)
public class XcoreMapperTest {
  @Inject
  private ParseHelper<XPackage> parser;
  
  @Inject
  private XcoreMapper mapper;
  
  @Test
  public void testMapping() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo.bar");
      _builder.newLine();
      _builder.newLine();
      _builder.append("type String wraps java.lang.String");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class X {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("attr String name");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("refers Y reference");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class Y<T> extends X {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op String toString(X x) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return null");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      XPackage _parse = this.parser.parse(_builder);
      final XPackage pack = _parse;
      XPackageMapping _mapping = this.mapper.getMapping(pack);
      EPackage _ePackage = _mapping.getEPackage();
      Assert.assertNotNull(_ePackage);
      XPackageMapping _mapping_1 = this.mapper.getMapping(pack);
      EPackage _ePackage_1 = _mapping_1.getEPackage();
      XPackageMapping _mapping_2 = this.mapper.getMapping(pack);
      GenPackage _genPackage = _mapping_2.getGenPackage();
      EPackage _ecorePackage = _genPackage.getEcorePackage();
      Assert.assertEquals(_ePackage_1, _ecorePackage);
      XPackageMapping _mapping_3 = this.mapper.getMapping(pack);
      GenPackage _genPackage_1 = _mapping_3.getGenPackage();
      ToXcoreMapping _xcoreMapping = this.mapper.getToXcoreMapping(_genPackage_1);
      XNamedElement _xcoreElement = _xcoreMapping.getXcoreElement();
      Assert.assertEquals(pack, _xcoreElement);
      XPackageMapping _mapping_4 = this.mapper.getMapping(pack);
      EPackage _ePackage_2 = _mapping_4.getEPackage();
      ToXcoreMapping _xcoreMapping_1 = this.mapper.getToXcoreMapping(_ePackage_2);
      XNamedElement _xcoreElement_1 = _xcoreMapping_1.getXcoreElement();
      Assert.assertEquals(pack, _xcoreElement_1);
      EList<XClassifier> _classifiers = pack.getClassifiers();
      Iterable<XClass> _filter = IterableExtensions.<XClass>filter(_classifiers, org.eclipse.emf.ecore.xcore.XClass.class);
      for (final XClass clazz : _filter) {
        {
          XClassMapping _mapping_5 = this.mapper.getMapping(clazz);
          EClass _eClass = _mapping_5.getEClass();
          Assert.assertNotNull(_eClass);
          XClassMapping _mapping_6 = this.mapper.getMapping(clazz);
          EClass _eClass_1 = _mapping_6.getEClass();
          XClassMapping _mapping_7 = this.mapper.getMapping(clazz);
          GenClass _genClass = _mapping_7.getGenClass();
          EClass _ecoreClass = _genClass.getEcoreClass();
          Assert.assertEquals(_eClass_1, _ecoreClass);
          XClassMapping _mapping_8 = this.mapper.getMapping(clazz);
          GenClass _genClass_1 = _mapping_8.getGenClass();
          ToXcoreMapping _xcoreMapping_2 = this.mapper.getToXcoreMapping(_genClass_1);
          XNamedElement _xcoreElement_2 = _xcoreMapping_2.getXcoreElement();
          Assert.assertEquals(clazz, _xcoreElement_2);
          XClassMapping _mapping_9 = this.mapper.getMapping(clazz);
          EClass _eClass_2 = _mapping_9.getEClass();
          ToXcoreMapping _xcoreMapping_3 = this.mapper.getToXcoreMapping(_eClass_2);
          XNamedElement _xcoreElement_3 = _xcoreMapping_3.getXcoreElement();
          Assert.assertEquals(clazz, _xcoreElement_3);
          EList<XMember> _members = clazz.getMembers();
          for (final XMember member : _members) {
            boolean matched = false;
            if (!matched) {
              if (member instanceof XStructuralFeature) {
                final XStructuralFeature _xStructuralFeature = (XStructuralFeature)member;
                matched=true;
                XFeatureMapping _mapping_10 = this.mapper.getMapping(_xStructuralFeature);
                EStructuralFeature _eStructuralFeature = _mapping_10.getEStructuralFeature();
                Assert.assertNotNull(_eStructuralFeature);
                XFeatureMapping _mapping_11 = this.mapper.getMapping(_xStructuralFeature);
                EStructuralFeature _eStructuralFeature_1 = _mapping_11.getEStructuralFeature();
                XFeatureMapping _mapping_12 = this.mapper.getMapping(_xStructuralFeature);
                GenFeature _genFeature = _mapping_12.getGenFeature();
                EStructuralFeature _ecoreFeature = _genFeature.getEcoreFeature();
                Assert.assertEquals(_eStructuralFeature_1, _ecoreFeature);
                XFeatureMapping _mapping_13 = this.mapper.getMapping(_xStructuralFeature);
                EStructuralFeature _eStructuralFeature_2 = _mapping_13.getEStructuralFeature();
                ToXcoreMapping _xcoreMapping_4 = this.mapper.getToXcoreMapping(_eStructuralFeature_2);
                XNamedElement _xcoreElement_4 = _xcoreMapping_4.getXcoreElement();
                Assert.assertEquals(_xStructuralFeature, _xcoreElement_4);
                XFeatureMapping _mapping_14 = this.mapper.getMapping(_xStructuralFeature);
                GenFeature _genFeature_1 = _mapping_14.getGenFeature();
                ToXcoreMapping _xcoreMapping_5 = this.mapper.getToXcoreMapping(_genFeature_1);
                XNamedElement _xcoreElement_5 = _xcoreMapping_5.getXcoreElement();
                Assert.assertEquals(_xStructuralFeature, _xcoreElement_5);
              }
            }
            if (!matched) {
              if (member instanceof XOperation) {
                final XOperation _xOperation = (XOperation)member;
                matched=true;
                XOperationMapping _mapping_10 = this.mapper.getMapping(_xOperation);
                EOperation _eOperation = _mapping_10.getEOperation();
                Assert.assertNotNull(_eOperation);
                XOperationMapping _mapping_11 = this.mapper.getMapping(_xOperation);
                EOperation _eOperation_1 = _mapping_11.getEOperation();
                XOperationMapping _mapping_12 = this.mapper.getMapping(_xOperation);
                GenOperation _genOperation = _mapping_12.getGenOperation();
                EOperation _ecoreOperation = _genOperation.getEcoreOperation();
                Assert.assertEquals(_eOperation_1, _ecoreOperation);
                XOperationMapping _mapping_13 = this.mapper.getMapping(_xOperation);
                EOperation _eOperation_2 = _mapping_13.getEOperation();
                ToXcoreMapping _xcoreMapping_4 = this.mapper.getToXcoreMapping(_eOperation_2);
                XNamedElement _xcoreElement_4 = _xcoreMapping_4.getXcoreElement();
                Assert.assertEquals(_xOperation, _xcoreElement_4);
                XOperationMapping _mapping_14 = this.mapper.getMapping(_xOperation);
                GenOperation _genOperation_1 = _mapping_14.getGenOperation();
                ToXcoreMapping _xcoreMapping_5 = this.mapper.getToXcoreMapping(_genOperation_1);
                XNamedElement _xcoreElement_5 = _xcoreMapping_5.getXcoreElement();
                Assert.assertEquals(_xOperation, _xcoreElement_5);
                EList<XParameter> _parameters = _xOperation.getParameters();
                for (final XParameter parameter : _parameters) {
                  {
                    XParameterMapping _mapping_15 = this.mapper.getMapping(parameter);
                    EParameter _eParameter = _mapping_15.getEParameter();
                    Assert.assertNotNull(_eParameter);
                    XParameterMapping _mapping_16 = this.mapper.getMapping(parameter);
                    EParameter _eParameter_1 = _mapping_16.getEParameter();
                    XParameterMapping _mapping_17 = this.mapper.getMapping(parameter);
                    GenParameter _genParameter = _mapping_17.getGenParameter();
                    EParameter _ecoreParameter = _genParameter.getEcoreParameter();
                    Assert.assertEquals(_eParameter_1, _ecoreParameter);
                    XParameterMapping _mapping_18 = this.mapper.getMapping(parameter);
                    EParameter _eParameter_2 = _mapping_18.getEParameter();
                    ToXcoreMapping _xcoreMapping_6 = this.mapper.getToXcoreMapping(_eParameter_2);
                    XNamedElement _xcoreElement_6 = _xcoreMapping_6.getXcoreElement();
                    Assert.assertEquals(parameter, _xcoreElement_6);
                    XParameterMapping _mapping_19 = this.mapper.getMapping(parameter);
                    GenParameter _genParameter_1 = _mapping_19.getGenParameter();
                    ToXcoreMapping _xcoreMapping_7 = this.mapper.getToXcoreMapping(_genParameter_1);
                    XNamedElement _xcoreElement_7 = _xcoreMapping_7.getXcoreElement();
                    Assert.assertEquals(parameter, _xcoreElement_7);
                  }
                }
              }
            }
          }
          EList<XTypeParameter> _typeParameters = clazz.getTypeParameters();
          for (final XTypeParameter typeParameter : _typeParameters) {
            {
              XTypeParameterMapping _mapping_10 = this.mapper.getMapping(typeParameter);
              ETypeParameter _eTypeParameter = _mapping_10.getETypeParameter();
              Assert.assertNotNull(_eTypeParameter);
              XTypeParameterMapping _mapping_11 = this.mapper.getMapping(typeParameter);
              ETypeParameter _eTypeParameter_1 = _mapping_11.getETypeParameter();
              XTypeParameterMapping _mapping_12 = this.mapper.getMapping(typeParameter);
              GenTypeParameter _genTypeParameter = _mapping_12.getGenTypeParameter();
              ETypeParameter _ecoreTypeParameter = _genTypeParameter.getEcoreTypeParameter();
              Assert.assertEquals(_eTypeParameter_1, _ecoreTypeParameter);
              XTypeParameterMapping _mapping_13 = this.mapper.getMapping(typeParameter);
              ETypeParameter _eTypeParameter_2 = _mapping_13.getETypeParameter();
              ToXcoreMapping _xcoreMapping_4 = this.mapper.getToXcoreMapping(_eTypeParameter_2);
              XNamedElement _xcoreElement_4 = _xcoreMapping_4.getXcoreElement();
              Assert.assertEquals(typeParameter, _xcoreElement_4);
              XTypeParameterMapping _mapping_14 = this.mapper.getMapping(typeParameter);
              GenTypeParameter _genTypeParameter_1 = _mapping_14.getGenTypeParameter();
              ToXcoreMapping _xcoreMapping_5 = this.mapper.getToXcoreMapping(_genTypeParameter_1);
              XNamedElement _xcoreElement_5 = _xcoreMapping_5.getXcoreElement();
              Assert.assertEquals(typeParameter, _xcoreElement_5);
            }
          }
        }
      }
    } catch (Exception _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
