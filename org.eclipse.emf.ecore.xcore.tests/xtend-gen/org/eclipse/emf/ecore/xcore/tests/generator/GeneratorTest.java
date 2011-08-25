package org.eclipse.emf.ecore.xcore.tests.generator;

import com.google.inject.Inject;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.emf.ecore.xcore.generator.XcoreGenerator;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.generator.InMemoryFileSystemAccess;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.xtend2.lib.StringConcatenation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(XcoreInjectorProvider.class)
public class GeneratorTest {
  
  @Inject
  private ParseHelper<XPackage> parser;
  
  @Inject
  private ValidationTestHelper validator;
  
  @Inject
  private XcoreMapper mapper;
  
  @Inject
  private XcoreGenerator xcoreGenerator;
  
  @Test
  public void testGenerator() throws Exception {
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package test");
      _builder.newLine();
      _builder.append("class X {}");
      _builder.newLine();
      XPackage _parse = this.parser.parse(_builder);
      final XPackage xPackage = _parse;
      InMemoryFileSystemAccess _inMemoryFileSystemAccess = new InMemoryFileSystemAccess();
      final InMemoryFileSystemAccess inmemFsa = _inMemoryFileSystemAccess;
      Resource _eResource = xPackage.eResource();
      this.xcoreGenerator.doGenerate(_eResource, inmemFsa);
      Map<String,CharSequence> _files = inmemFsa.getFiles();
      Set<String> _keySet = _files.keySet();
      String _string = _keySet.toString();
      Map<String,CharSequence> _files_1 = inmemFsa.getFiles();
      int _size = _files_1.size();
      Assert.assertEquals(_string, 8, _size);
      Map<String,CharSequence> _files_2 = inmemFsa.getFiles();
      CharSequence _get = _files_2.get("test/util/TestSwitch.java");
      Assert.assertNotNull(_get);
    }
  }
}