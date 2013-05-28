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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.ui.labeling.XbaseLabelProvider;

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
}
