/**
 * Copyright (c) 2009 Tom Schindl
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *  Tom Schindl <tom.schindl@bestsolution.at>
 */
package org.eclipse.emf.databinding.edit.internal;

import org.eclipse.core.databinding.property.list.MultiListProperty;
import org.eclipse.emf.databinding.EMFProperties;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.IEMFValueProperty;
import org.eclipse.emf.databinding.edit.IEMFEditListProperty;
import org.eclipse.emf.databinding.edit.IEMFEditValueProperty;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * @since 2.5
 */
public class EMFEditMultiListProperty extends MultiListProperty implements IEMFEditListProperty
{
  private EditingDomain editingDomain;

  /**
   * Create new property
   * @param editingDomain 
   * @param properties
   */
  public EMFEditMultiListProperty(EditingDomain editingDomain, IEMFEditListProperty[] properties)
  {
    super(properties);
  }

  public IEMFEditListProperty values(EStructuralFeature feature)
  {
    return values(FeaturePath.fromList(feature));
  }

  public IEMFEditListProperty values(FeaturePath featurePath)
  {
    return values(EMFProperties.value(featurePath));
  }

  public IEMFEditListProperty values(IEMFValueProperty property)
  {
    return new EMFEditListPropertyDecorator(editingDomain, super.values(property), property.getStructuralFeature());
  }

  public IEMFEditListProperty values(IEMFEditValueProperty property)
  {
    return new EMFEditListPropertyDecorator(editingDomain, super.values(property), property.getStructuralFeature());
  }

  public EStructuralFeature getStructuralFeature()
  {
    return null;
  }

  public EditingDomain getEditingDomain()
  {
    return editingDomain;
  }

  public IEMFEditValueProperty value(ListElementAccess<?> elementAccess)
  {
    throw new UnsupportedOperationException();
  }
}
