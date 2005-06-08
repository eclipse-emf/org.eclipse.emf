/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: IItemPropertySource.java,v 1.1.2.1 2005/06/08 18:27:42 nickb Exp $
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
  List getPropertyDescriptors(Object object);

  /**
   * This returns the property descriptor with the given ID.
   */
  IItemPropertyDescriptor getPropertyDescriptor(Object object, Object propertyID);

  /**
   * This returns the value to be edited.
   */
  Object getEditableValue(Object object);
}
