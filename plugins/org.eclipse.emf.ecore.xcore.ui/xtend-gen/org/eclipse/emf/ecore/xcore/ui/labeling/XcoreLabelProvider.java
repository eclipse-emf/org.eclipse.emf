/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.labeling;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.util.Arrays;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.common.types.JvmConstructor;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmFormalParameter;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.XBlockExpression;
import org.eclipse.xtext.xbase.XCasePart;
import org.eclipse.xtext.xbase.XVariableDeclaration;
import org.eclipse.xtext.xbase.ui.labeling.XbaseLabelProvider;
import org.eclipse.xtext.xtype.XImportDeclaration;

/**
 * Provides labels for a EObjects.
 * 
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#labelProvider
 */
@SuppressWarnings("all")
public class XcoreLabelProvider extends XbaseLabelProvider {
  @Inject
  private IQualifiedNameProvider nameProvider;
  
  @Inject
  private IQualifiedNameConverter nameConverter;
  
  @Inject
  public XcoreLabelProvider(final AdapterFactoryLabelProvider delegate) {
    super(delegate);
  }
  
  public String getText(final Object element) {
    final String result = super.getText(element);
    boolean _and = false;
    boolean _equals = Objects.equal(result, null);
    if (!_equals) {
      _and = false;
    } else {
      _and = (_equals && (element instanceof EObject));
    }
    if (_and) {
      final QualifiedName name = this.nameProvider.getFullyQualifiedName(((EObject) element));
      boolean _notEquals = (!Objects.equal(name, null));
      if (_notEquals) {
        return this.nameConverter.toString(name);
      } else {
        return this.getDefaultText();
      }
    }
    return result;
  }
  
  protected String _text(final XBlockExpression xBlockExpression) {
    EReference _eContainmentFeature = xBlockExpression.eContainmentFeature();
    final String role = _eContainmentFeature.getName();
    int _length = role.length();
    int _minus = (_length - 4);
    String _substring = role.substring(0, _minus);
    return (_substring + " {}");
  }
  
  public Object text(final Object constructor) {
    if (constructor instanceof JvmConstructor) {
      return _text((JvmConstructor)constructor);
    } else if (constructor instanceof JvmOperation) {
      return _text((JvmOperation)constructor);
    } else if (constructor instanceof JvmField) {
      return _text((JvmField)constructor);
    } else if (constructor instanceof JvmGenericType) {
      return _text((JvmGenericType)constructor);
    } else if (constructor instanceof JvmFormalParameter) {
      return _text((JvmFormalParameter)constructor);
    } else if (constructor instanceof XBlockExpression) {
      return _text((XBlockExpression)constructor);
    } else if (constructor instanceof XVariableDeclaration) {
      return _text((XVariableDeclaration)constructor);
    } else if (constructor instanceof XCasePart) {
      return _text((XCasePart)constructor);
    } else if (constructor instanceof XImportDeclaration) {
      return _text((XImportDeclaration)constructor);
    } else if (constructor != null) {
      return _text(constructor);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(constructor).toString());
    }
  }
}
