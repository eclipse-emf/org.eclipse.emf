/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.xcore.ui.labeling;

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
  
  @Override
  public String getText(final Object element) {
    final String result = super.getText(element);
    if (((result == null) && (element instanceof EObject))) {
      final QualifiedName name = this.nameProvider.getFullyQualifiedName(((EObject) element));
      if ((name != null)) {
        return this.nameConverter.toString(name);
      } else {
        return this.getDefaultText();
      }
    }
    return result;
  }
}
