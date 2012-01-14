/**
 * Copyright (c) 2002-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.provider;


import java.util.List;


/**
 * This is the interface is needed to populate property sheet items; 
 * it is the same as IPropertySource 
 * except that the object is passed as the first parameter for each method.
 */
public interface IItemPropertySource
{
  /**
   * This does the same thing as 
   * IPropertySource.getPropertyDescriptors.
   */
  List<IItemPropertyDescriptor> getPropertyDescriptors(Object object);

  /**
   * This returns the property descriptor
   * with an {@link IItemPropertyDescriptor#getId(Object) ID} or {@link IItemPropertyDescriptor#getFeature(Object) feature} 
   * that matches the given ID.
   */
  IItemPropertyDescriptor getPropertyDescriptor(Object object, Object propertyID);

  /**
   * This returns the value to be edited.
   */
  Object getEditableValue(Object object);
}
