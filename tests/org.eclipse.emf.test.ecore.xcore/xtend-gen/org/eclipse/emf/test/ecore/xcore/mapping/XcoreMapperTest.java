/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.ecore.xcore.mapping;

import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XMember;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XParameter;
import org.eclipse.emf.ecore.xcore.XStructuralFeature;
import org.eclipse.emf.ecore.xcore.XTypeParameter;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.test.ecore.xcore.XcoreStandaloneInjectorProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(XcoreStandaloneInjectorProvider.class)
@SuppressWarnings("all")
public class XcoreMapperTest {
  @Inject
  private ParseHelper<XPackage> parser;
  
  @Inject
  @Extension
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
      final XPackage pack = this.parser.parse(_builder);
      Assert.assertNotNull(this.mapper.getMapping(pack).getEPackage());
      Assert.assertEquals(this.mapper.getMapping(pack).getEPackage(), this.mapper.getMapping(pack).getGenPackage().getEcorePackage());
      Assert.assertEquals(pack, this.mapper.getToXcoreMapping(this.mapper.getMapping(pack).getGenPackage()).getXcoreElement());
      Assert.assertEquals(pack, this.mapper.getToXcoreMapping(this.mapper.getMapping(pack).getEPackage()).getXcoreElement());
      Iterable<XClass> _filter = Iterables.<XClass>filter(pack.getClassifiers(), XClass.class);
      for (final XClass clazz : _filter) {
        {
          Assert.assertNotNull(this.mapper.getMapping(clazz).getEClass());
          Assert.assertEquals(this.mapper.getMapping(clazz).getEClass(), this.mapper.getMapping(clazz).getGenClass().getEcoreClass());
          Assert.assertEquals(clazz, this.mapper.getToXcoreMapping(this.mapper.getMapping(clazz).getGenClass()).getXcoreElement());
          Assert.assertEquals(clazz, this.mapper.getToXcoreMapping(this.mapper.getMapping(clazz).getEClass()).getXcoreElement());
          EList<XMember> _members = clazz.getMembers();
          for (final XMember member : _members) {
            boolean _matched = false;
            if (member instanceof XStructuralFeature) {
              _matched=true;
              Assert.assertNotNull(this.mapper.getMapping(((XStructuralFeature)member)).getEStructuralFeature());
              Assert.assertEquals(this.mapper.getMapping(((XStructuralFeature)member)).getEStructuralFeature(), this.mapper.getMapping(((XStructuralFeature)member)).getGenFeature().getEcoreFeature());
              Assert.assertEquals(member, this.mapper.getToXcoreMapping(this.mapper.getMapping(((XStructuralFeature)member)).getEStructuralFeature()).getXcoreElement());
              Assert.assertEquals(member, this.mapper.getToXcoreMapping(this.mapper.getMapping(((XStructuralFeature)member)).getGenFeature()).getXcoreElement());
            }
            if (!_matched) {
              if (member instanceof XOperation) {
                _matched=true;
                Assert.assertNotNull(this.mapper.getMapping(((XOperation)member)).getEOperation());
                Assert.assertEquals(this.mapper.getMapping(((XOperation)member)).getEOperation(), this.mapper.getMapping(((XOperation)member)).getGenOperation().getEcoreOperation());
                Assert.assertEquals(member, this.mapper.getToXcoreMapping(this.mapper.getMapping(((XOperation)member)).getEOperation()).getXcoreElement());
                Assert.assertEquals(member, this.mapper.getToXcoreMapping(this.mapper.getMapping(((XOperation)member)).getGenOperation()).getXcoreElement());
                EList<XParameter> _parameters = ((XOperation)member).getParameters();
                for (final XParameter parameter : _parameters) {
                  {
                    Assert.assertNotNull(this.mapper.getMapping(parameter).getEParameter());
                    Assert.assertEquals(this.mapper.getMapping(parameter).getEParameter(), this.mapper.getMapping(parameter).getGenParameter().getEcoreParameter());
                    Assert.assertEquals(parameter, this.mapper.getToXcoreMapping(this.mapper.getMapping(parameter).getEParameter()).getXcoreElement());
                    Assert.assertEquals(parameter, this.mapper.getToXcoreMapping(this.mapper.getMapping(parameter).getGenParameter()).getXcoreElement());
                  }
                }
              }
            }
          }
          EList<XTypeParameter> _typeParameters = clazz.getTypeParameters();
          for (final XTypeParameter typeParameter : _typeParameters) {
            {
              Assert.assertNotNull(this.mapper.getMapping(typeParameter).getETypeParameter());
              Assert.assertEquals(this.mapper.getMapping(typeParameter).getETypeParameter(), this.mapper.getMapping(typeParameter).getGenTypeParameter().getEcoreTypeParameter());
              Assert.assertEquals(typeParameter, this.mapper.getToXcoreMapping(this.mapper.getMapping(typeParameter).getETypeParameter()).getXcoreElement());
              Assert.assertEquals(typeParameter, this.mapper.getToXcoreMapping(this.mapper.getMapping(typeParameter).getGenTypeParameter()).getXcoreElement());
            }
          }
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
