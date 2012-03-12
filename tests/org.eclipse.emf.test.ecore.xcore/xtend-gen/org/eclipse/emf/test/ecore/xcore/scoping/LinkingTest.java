package org.eclipse.emf.test.ecore.xcore.scoping;

import com.google.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.xcore.XClass;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XMember;
import org.eclipse.emf.ecore.xcore.XOperation;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.xbase.XBinaryOperation;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XExpression;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@SuppressWarnings("all")
@RunWith(XtextRunner.class)
@InjectWith(XcoreInjectorProvider.class)
public class LinkingTest {
  @Inject
  private ParseHelper<XPackage> parser;
  
  @Test
  public void linkVoidReturnType() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.append("class Bar {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op void operation() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("val int i = 0");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final XPackage pack = this.parser.parse(_builder);
      final XVariableDeclaration declaration = this.firstVariableDeclaration(pack);
      JvmTypeReference _type = declaration.getType();
      final JvmType intType = _type.getType();
      boolean _eIsProxy = intType.eIsProxy();
      Assert.assertFalse(_eIsProxy);
      String _qualifiedName = intType.getQualifiedName();
      Assert.assertEquals("int", _qualifiedName);
    } catch (Exception _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void linkQualifiedStringType() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.append("class Bar {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op void operation() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("val java.lang.String s = null");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final XPackage pack = this.parser.parse(_builder);
      final XVariableDeclaration declaration = this.firstVariableDeclaration(pack);
      JvmTypeReference _type = declaration.getType();
      final JvmType stringType = _type.getType();
      boolean _eIsProxy = stringType.eIsProxy();
      Assert.assertFalse(_eIsProxy);
      String _qualifiedName = stringType.getQualifiedName();
      Assert.assertEquals("java.lang.String", _qualifiedName);
    } catch (Exception _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void linkInternalDefinedType() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.append("class Bar {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op void operation() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("val Bar b = null");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final XPackage pack = this.parser.parse(_builder);
      final XVariableDeclaration declaration = this.firstVariableDeclaration(pack);
      JvmTypeReference _type = declaration.getType();
      final JvmType stringType = _type.getType();
      boolean _eIsProxy = stringType.eIsProxy();
      Assert.assertFalse(_eIsProxy);
      String _qualifiedName = stringType.getQualifiedName();
      Assert.assertEquals("foo.Bar", _qualifiedName);
    } catch (Exception _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Test
  public void linkFeatureCallType() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("package foo");
      _builder.newLine();
      _builder.append("class Bar {");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("op void operation() {");
      _builder.newLine();
      _builder.append("\t\t");
      _builder.append("val s = \'a\' + \'b\'");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final XPackage pack = this.parser.parse(_builder);
      final XVariableDeclaration declaration = this.firstVariableDeclaration(pack);
      XExpression _right = declaration.getRight();
      final XBinaryOperation binaryOperation = ((XBinaryOperation) _right);
      final JvmIdentifiableElement feature = binaryOperation.getFeature();
      boolean _eIsProxy = feature.eIsProxy();
      Assert.assertFalse(_eIsProxy);
      String _simpleName = feature.getSimpleName();
      Assert.assertEquals("operator_plus", _simpleName);
    } catch (Exception _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public XVariableDeclaration firstVariableDeclaration(final XPackage pack) {
    XVariableDeclaration _xblockexpression = null;
    {
      EList<XClassifier> _classifiers = pack.getClassifiers();
      XClassifier _head = IterableExtensions.<XClassifier>head(_classifiers);
      final XClass clazz = ((XClass) _head);
      EList<XMember> _members = clazz.getMembers();
      XMember _head_1 = IterableExtensions.<XMember>head(_members);
      final XOperation operation = ((XOperation) _head_1);
      XBlockExpression _body = operation.getBody();
      final XBlockExpression block = ((XBlockExpression) _body);
      EList<XExpression> _expressions = block.getExpressions();
      XExpression _head_2 = IterableExtensions.<XExpression>head(_expressions);
      final XVariableDeclaration declaration = ((XVariableDeclaration) _head_2);
      _xblockexpression = (declaration);
    }
    return _xblockexpression;
  }
}
