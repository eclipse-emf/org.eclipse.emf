/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: IItemPropertyDescriptor.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.Collection;


/**
 * This interface extends IPropertyDescriptor so that the methods of {@link IItemPropertySource} can be delegated to the descriptor.
 * This allows the implementing class to completely encapsulate the work associated with supporting a particular property sheet property.
 */
public interface IItemPropertyDescriptor 
{
  /**
   * This fetches this descriptor's property from the object.
   * Sometimes it's necessary to update the contents of the cell editor during this call,
   * i.e., the call is used as a notification that this descriptor is being used to edit another object.
   */
  public Object getPropertyValue(Object object);

  /**
   * This determines whether this descriptor's property for the object is set.
   * I'm not sure right now what this is used for?  I should find out.
   */
  public boolean isPropertySet(Object object);

  /**
   * This determines whether this descriptor's property for the object supports set (and reset).
   */
  public boolean canSetProperty(Object object);

  /**
   * This resets this descriptor's property for the object.
   */
  public void resetPropertyValue(Object object);

  /**
   * This sets this descriptor's property for the object to the given value.
   */
  public void setPropertyValue(Object object, Object value);

  /**
   * Returns the name of the category to which this property belongs.
   */ 
  String getCategory(Object object);

  /**
   * Returns a brief description of this property.
   */
  String getDescription(Object object);

  /**
   * Returns the display name for this property.
   */
  String getDisplayName(Object object);

  /**
   * Returns a list of filter types to which this property belongs.
   */
  String[] getFilterFlags(Object object);

  /*
   * Returns the help context ids for this property.
   */
  Object getHelpContextIds(Object object);

  /**
   * Returns the id for this property.
   */
  String getId(Object object);

  /**
   * Returns the label provider for this property.
   */
  IItemLabelProvider getLabelProvider(Object object);

  /**
   * Returns whether this property descriptor and the given one are compatible.
   */
  boolean isCompatibleWith(Object object, Object anotherObject,  IItemPropertyDescriptor anotherPropertyDescriptor);

  /**
   * Returns the feature.
   */
  public Object getFeature(Object object);

  public Collection getChoiceOfValues(Object object);
}
