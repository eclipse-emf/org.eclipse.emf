/**
 * <copyright> 
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: ItemPropertyDescriptorDecorator.java,v 1.6 2007/06/14 18:32:42 emerks Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.Collection;


/**
 */
public class ItemPropertyDescriptorDecorator implements IItemPropertyDescriptor
{
  protected Object object;
  protected IItemPropertyDescriptor itemPropertyDescriptor;

  /**
   */
  public ItemPropertyDescriptorDecorator(Object object, IItemPropertyDescriptor itemPropertyDescriptor)
  {
    this.object = object;
    this.itemPropertyDescriptor = itemPropertyDescriptor;
  }

  /**
   * This returns the group of propertiesk into which this one should be placed.
   */
  public String getCategory(Object thisObject) 
  {
    return itemPropertyDescriptor.getCategory(object);
  }

  /**
   * This returns the description to be displayed in the property sheet when this property is selected.
   */
  public String getDescription(Object thisObject) 
  {
    return itemPropertyDescriptor.getDescription(object);
  }

  /**
   * This returns the name of the property to be displayed in the property sheet.
   */
  public String getDisplayName(Object thisObject) 
  {
    return itemPropertyDescriptor.getDisplayName(object);
  }

  /**
   * This returns the flags used as filters in the property sheet.
   */
  public String[] getFilterFlags(Object thisObject) 
  {
    return itemPropertyDescriptor.getFilterFlags(object);
  }

  /**
   * This returns the interface name of this property.
   * This is the key that is passed around and must uniquely identifiy this descriptor.
   */
  public String getId(Object thisObject) 
  {
    return itemPropertyDescriptor.getId(object);
  }

  public Object getHelpContextIds(Object thisObject)
  {
    return itemPropertyDescriptor.getHelpContextIds(object);
  }

  /**
   * This does the delegated job of getting the label provider for  the given object 
   */
  public IItemLabelProvider getLabelProvider(Object thisObject) 
  {
    return itemPropertyDescriptor.getLabelProvider(object);
  }

  /**
   * This indicates whether these two property descriptors are equal.
   * It's not really clear to me how this is meant to be used, 
   * but it's a little bit like an equals test.
   */
  public boolean isCompatibleWith(Object object, Object anotherObject, IItemPropertyDescriptor anotherItemPropertyDescriptor) 
  {
    return itemPropertyDescriptor.isCompatibleWith(object, anotherObject, anotherItemPropertyDescriptor);
  }

  /**
   * This does the delegated job of getting the property value from the given object 
   */
  public Object getPropertyValue(Object thisObject)
  {
    return itemPropertyDescriptor.getPropertyValue(object);
  }

  /**
   * This does the delegated job of determining whether the property value from the given object is set.
   */
  public boolean isPropertySet(Object thisObject)
  {
    return itemPropertyDescriptor.isPropertySet(object);
  }

  /**
   * This does the delegated job of determining whether the property value from the given object supports set (and reset).
   */
  public boolean canSetProperty(Object thisObject)
  {
    return itemPropertyDescriptor.canSetProperty(object);
  }

  /**
   * This does the delegated job of resetting property value back to it's default value.
   */
  public void resetPropertyValue(Object thisObject)
  {
    itemPropertyDescriptor.resetPropertyValue(object);
  }

  /**
   * This does the delegated job of setting the property to the given value.
   */
  public void setPropertyValue(Object thisObject, Object value)
  {
    itemPropertyDescriptor.setPropertyValue(object, value);
  }

  public Object getFeature(Object thisObject)
  {
    return itemPropertyDescriptor.getFeature(object);

  }

  public Collection<?> getChoiceOfValues(Object thisObject)
  {
    return itemPropertyDescriptor.getChoiceOfValues(object);
  }

  /**
   * This does the delegated job of determining whether the  property represents multiple values.
   */
  public boolean isMany(Object thisObject)
  {
    return itemPropertyDescriptor.isMany(thisObject);
  }
  
  /**
   * This does the delegated job of determining whether the property's value consists of multi-line text.
   * @since 2.2.0
   */
  public boolean isMultiLine(Object object)
  {
    return itemPropertyDescriptor.isMultiLine(object);
  }

  /**
   * This does the delegated job of determining the choices for this property should be sorted for display.
   * @since 2.2.0
   */
  public boolean isSortChoices(Object object)
  {
    return itemPropertyDescriptor.isSortChoices(object);
  }
}
