/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.labeling;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.xbase.ui.labeling.XbaseLabelProvider;

import com.google.inject.Inject;


/**
 * Provides labels for a EObjects.
 *
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#labelProvider
 */
class XcoreLabelProvider extends XbaseLabelProvider
{
  @Inject
  private IQualifiedNameProvider nameProvider;

  @Inject
  private IQualifiedNameConverter nameConverter;

  @Inject
  new(AdapterFactoryLabelProvider delegate)
  {
    super(delegate);
  }

  override String getText(Object element)
  {
    val result = super.getText(element);
    if (result == null && element instanceof EObject)
    {
      val name = nameProvider.getFullyQualifiedName(element as EObject);
      if (name != null)
      {
        return nameConverter.toString(name);
      }
      else
      {
        return getDefaultText();
      }

    }
    return result;
  }
}
