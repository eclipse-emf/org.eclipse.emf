package org.eclipse.emf.ecore.xcore.tests.mapping;

import com.google.inject.Inject;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XMember;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.emf.ecore.xcore.mappings.ToXcoreMapping;
import org.eclipse.emf.ecore.xcore.mappings.XClassMapping;
import org.eclipse.emf.ecore.xcore.mappings.XFeatureMapping;
import org.eclipse.emf.ecore.xcore.mappings.XOperationMapping;
import org.eclipse.emf.ecore.xcore.mappings.XPackageMapping;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xtend2.lib.StringConcatenation;
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
  public void testMapping() throws Exception {
    {
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
      _builder.append("class Y extends X {");
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
          EClass _eclass = _mapping_5.getEclass();
          Assert.assertNotNull(_eclass);
          XClassMapping _mapping_6 = this.mapper.getMapping(clazz);
          EClass _eclass_1 = _mapping_6.getEclass();
          XClassMapping _mapping_7 = this.mapper.getMapping(clazz);
          GenClass _genClass = _mapping_7.getGenClass();
          EClass _ecoreClass = _genClass.getEcoreClass();
          Assert.assertEquals(_eclass_1, _ecoreClass);
          XClassMapping _mapping_8 = this.mapper.getMapping(clazz);
          GenClass _genClass_1 = _mapping_8.getGenClass();
          ToXcoreMapping _xcoreMapping_2 = this.mapper.getToXcoreMapping(_genClass_1);
          XNamedElement _xcoreElement_2 = _xcoreMapping_2.getXcoreElement();
          Assert.assertEquals(clazz, _xcoreElement_2);
          XClassMapping _mapping_9 = this.mapper.getMapping(clazz);
          EClass _eclass_2 = _mapping_9.getEclass();
          ToXcoreMapping _xcoreMapping_3 = this.mapper.getToXcoreMapping(_eclass_2);
          XNamedElement _xcoreElement_3 = _xcoreMapping_3.getXcoreElement();
          Assert.assertEquals(clazz, _xcoreElement_3);
          EList<XMember> _members = clazz.getMembers();
          for (final XMember member : _members) {
            final XMember member_1 = member;
            boolean matched = false;
            if (!matched) {
              if (member_1 instanceof XStructuralFeature) {
                final XStructuralFeature member_2 = (XStructuralFeature) member_1;
                matched=true;
                {
                  XFeatureMapping _mapping_10 = this.mapper.getMapping(member_2);
                  EStructuralFeature _eStructuralFeature = _mapping_10.getEStructuralFeature();
                  Assert.assertNotNull(_eStructuralFeature);
                  XFeatureMapping _mapping_11 = this.mapper.getMapping(member_2);
                  EStructuralFeature _eStructuralFeature_1 = _mapping_11.getEStructuralFeature();
                  XFeatureMapping _mapping_12 = this.mapper.getMapping(member_2);
                  GenFeature _genFeature = _mapping_12.getGenFeature();
                  EStructuralFeature _ecoreFeature = _genFeature.getEcoreFeature();
                  Assert.assertEquals(_eStructuralFeature_1, _ecoreFeature);
                  XFeatureMapping _mapping_13 = this.mapper.getMapping(member_2);
                  EStructuralFeature _eStructuralFeature_2 = _mapping_13.getEStructuralFeature();
                  ToXcoreMapping _xcoreMapping_4 = this.mapper.getToXcoreMapping(_eStructuralFeature_2);
                  XNamedElement _xcoreElement_4 = _xcoreMapping_4.getXcoreElement();
                  Assert.assertEquals(member_2, _xcoreElement_4);
                  XFeatureMapping _mapping_14 = this.mapper.getMapping(member_2);
                  GenFeature _genFeature_1 = _mapping_14.getGenFeature();
                  ToXcoreMapping _xcoreMapping_5 = this.mapper.getToXcoreMapping(_genFeature_1);
                  XNamedElement _xcoreElement_5 = _xcoreMapping_5.getXcoreElement();
                  Assert.assertEquals(member_2, _xcoreElement_5);
                }
              }
            }
            if (!matched) {
              if (member_1 instanceof XOperation) {
                final XOperation member_3 = (XOperation) member_1;
                matched=true;
                {
                  XOperationMapping _mapping_15 = this.mapper.getMapping(member_3);
                  EOperation _eOperation = _mapping_15.getEOperation();
                  Assert.assertNotNull(_eOperation);
                  XOperationMapping _mapping_16 = this.mapper.getMapping(member_3);
                  EOperation _eOperation_1 = _mapping_16.getEOperation();
                  XOperationMapping _mapping_17 = this.mapper.getMapping(member_3);
                  GenOperation _genOperation = _mapping_17.getGenOperation();
                  EOperation _ecoreOperation = _genOperation.getEcoreOperation();
                  Assert.assertEquals(_eOperation_1, _ecoreOperation);
                  XOperationMapping _mapping_18 = this.mapper.getMapping(member_3);
                  EOperation _eOperation_2 = _mapping_18.getEOperation();
                  ToXcoreMapping _xcoreMapping_6 = this.mapper.getToXcoreMapping(_eOperation_2);
                  XNamedElement _xcoreElement_6 = _xcoreMapping_6.getXcoreElement();
                  Assert.assertEquals(member_3, _xcoreElement_6);
                  XOperationMapping _mapping_19 = this.mapper.getMapping(member_3);
                  GenOperation _genOperation_1 = _mapping_19.getGenOperation();
                  ToXcoreMapping _xcoreMapping_7 = this.mapper.getToXcoreMapping(_genOperation_1);
                  XNamedElement _xcoreElement_7 = _xcoreMapping_7.getXcoreElement();
                  Assert.assertEquals(member_3, _xcoreElement_7);
                }
              }
            }
          }
        }
      }
    }
  }
}