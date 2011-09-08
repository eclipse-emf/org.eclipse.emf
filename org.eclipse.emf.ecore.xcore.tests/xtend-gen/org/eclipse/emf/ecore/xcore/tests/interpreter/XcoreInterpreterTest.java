package org.eclipse.emf.ecore.xcore.tests.interpreter;

import com.google.inject.Inject;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xtend2.lib.StringConcatenation;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(XcoreInjectorProvider.class)
public class XcoreInterpreterTest {
  
  @Inject
  private ParseHelper<XPackage> parse;
  
  @Inject
  private ValidationTestHelper validator;
  
  @Test
  public void testInterpretation() throws Exception, InvocationTargetException {
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo.bar");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class Foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op String doStuff(String msg) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return \"Foo says hi to \"+msg");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      XPackage _parse = this.parse.parse(_builder);
      final XPackage pack = _parse;
      this.validator.assertNoErrors(pack);
      Resource _eResource = pack.eResource();
      EList<EObject> _contents = _eResource.getContents();
      EObject _get = _contents.get(2);
      final EPackage ePackage = ((EPackage) _get);
      EClassifier _eClassifier = ePackage.getEClassifier("Foo");
      final EClass fooClass = ((EClass) _eClassifier);
      EFactory _eFactoryInstance = ePackage.getEFactoryInstance();
      EObject _create = _eFactoryInstance.create(fooClass);
      final EObject foo = _create;
      EList<EOperation> _eOperations = fooClass.getEOperations();
      EOperation _head = IterableExtensions.<EOperation>head(_eOperations);
      ArrayList<String> _newArrayList = CollectionLiterals.<String>newArrayList("Bar");
      BasicEList<String> _basicEList = new BasicEList<String>(_newArrayList);
      Object _eInvoke = foo.eInvoke(_head, _basicEList);
      Assert.assertEquals("Foo says hi to Bar", _eInvoke);
    }
  }
  
  @Test
  public void testInterpretation_2() throws Exception, InvocationTargetException {
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo.bar");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class Foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op String call1(String msg) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return \"call1\"+call2(\"call1\"+msg)");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op String call2(String msg) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return \"call2\"+msg");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      XPackage _parse = this.parse.parse(_builder);
      final XPackage pack = _parse;
      this.validator.assertNoErrors(pack);
      Resource _eResource = pack.eResource();
      EList<EObject> _contents = _eResource.getContents();
      EObject _get = _contents.get(2);
      final EPackage ePackage = ((EPackage) _get);
      EClassifier _eClassifier = ePackage.getEClassifier("Foo");
      final EClass fooClass = ((EClass) _eClassifier);
      EFactory _eFactoryInstance = ePackage.getEFactoryInstance();
      EObject _create = _eFactoryInstance.create(fooClass);
      final EObject foo = _create;
      EList<EOperation> _eOperations = fooClass.getEOperations();
      EOperation _head = IterableExtensions.<EOperation>head(_eOperations);
      ArrayList<String> _newArrayList = CollectionLiterals.<String>newArrayList("Bar");
      BasicEList<String> _basicEList = new BasicEList<String>(_newArrayList);
      Object _eInvoke = foo.eInvoke(_head, _basicEList);
      Assert.assertEquals("call1call2call1Bar", _eInvoke);
    }
  }
  
  @Test
  public void testFeatureAccessors() throws Exception, InvocationTargetException {
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo.bar");
      _builder.newLine();
      _builder.newLine();
      _builder.append("class Foo {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("String value");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op void storeValue(String newValue) {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("value = newValue");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op String fetchValue() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("return value");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      XPackage _parse = this.parse.parse(_builder);
      final XPackage pack = _parse;
      this.validator.assertNoErrors(pack);
      Resource _eResource = pack.eResource();
      EList<EObject> _contents = _eResource.getContents();
      EObject _get = _contents.get(2);
      final EPackage ePackage = ((EPackage) _get);
      EClassifier _eClassifier = ePackage.getEClassifier("Foo");
      final EClass fooClass = ((EClass) _eClassifier);
      EFactory _eFactoryInstance = ePackage.getEFactoryInstance();
      EObject _create = _eFactoryInstance.create(fooClass);
      final EObject foo = _create;
      EList<EOperation> _eOperations = fooClass.getEOperations();
      EOperation _head = IterableExtensions.<EOperation>head(_eOperations);
      ArrayList<String> _newArrayList = CollectionLiterals.<String>newArrayList("Bar");
      BasicEList<String> _basicEList = new BasicEList<String>(_newArrayList);
      foo.eInvoke(_head, _basicEList);
      EList<EOperation> _eOperations_1 = fooClass.getEOperations();
      EOperation _get_1 = _eOperations_1.get(1);
      Object _eInvoke = foo.eInvoke(_get_1, null);
      Assert.assertEquals("Bar", _eInvoke);
    }
  }
  
  @Test
  public void testConversionDelegates() throws Exception {
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo.bar ");
      _builder.newLine();
      _builder.newLine();
      _builder.append("type URI wraps org.eclipse.emf.common.util.URI ");
      _builder.newLine();
      _builder.append("create { if (this == null) null else org::eclipse::emf::common::util::URI::createURI(this) } ");
      _builder.newLine();
      _builder.append("convert { this?.toString  }");
      _builder.newLine();
      XPackage _parse = this.parse.parse(_builder);
      final XPackage pack = _parse;
      this.validator.assertNoErrors(pack);
      Resource _eResource = pack.eResource();
      EList<EObject> _contents = _eResource.getContents();
      EObject _get = _contents.get(2);
      final EPackage ePackage = ((EPackage) _get);
      EClassifier _eClassifier = ePackage.getEClassifier("URI");
      final EDataType uriDataType = ((EDataType) _eClassifier);
      final String literal = "http://www.eclipse.org";
      EFactory _eFactoryInstance = ePackage.getEFactoryInstance();
      Object _createFromString = _eFactoryInstance.createFromString(uriDataType, literal);
      final URI uri = ((URI) _createFromString);
      EFactory _eFactoryInstance_1 = ePackage.getEFactoryInstance();
      String _convertToString = _eFactoryInstance_1.convertToString(uriDataType, uri);
      Assert.assertEquals(literal, _convertToString);
    }
  }
  
  @Test
  public void testSettingDelegates() throws Exception {
    {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo.bar");
      _builder.newLine();
      _builder.append("class Foo");
      _builder.newLine();
      _builder.append("{");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("String name");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("String alias get { name}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      XPackage _parse = this.parse.parse(_builder);
      final XPackage pack = _parse;
      this.validator.assertNoErrors(pack);
      Resource _eResource = pack.eResource();
      EList<EObject> _contents = _eResource.getContents();
      EObject _get = _contents.get(2);
      final EPackage ePackage = ((EPackage) _get);
      EClassifier _eClassifier = ePackage.getEClassifier("Foo");
      final EClass fooClass = ((EClass) _eClassifier);
      EFactory _eFactoryInstance = ePackage.getEFactoryInstance();
      EObject _create = _eFactoryInstance.create(fooClass);
      final EObject foo = _create;
      EStructuralFeature _eStructuralFeature = fooClass.getEStructuralFeature("name");
      foo.eSet(_eStructuralFeature, "Sven");
      EStructuralFeature _eStructuralFeature_1 = fooClass.getEStructuralFeature("alias");
      Object _eGet = foo.eGet(_eStructuralFeature_1);
      Assert.assertEquals("Sven", _eGet);
    }
  }
}