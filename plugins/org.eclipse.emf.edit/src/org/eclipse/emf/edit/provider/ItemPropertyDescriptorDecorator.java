/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.provider;


import java.util.Collection;


public class ItemPropertyDescriptorDecorator implements IItemPropertyDescriptor, IItemPropertyDescriptor.ValueHandlerProvider, IPropertyEditorFactory.Provider
{
  protected Object object;
  protected IItemPropertyDescriptor itemPropertyDescriptor;

  public ItemPropertyDescriptorDecorator(Object object, IItemPropertyDescriptor itemPropertyDescriptor)
  {
    this.object = object;
    this.itemPropertyDescriptor = itemPropertyDescriptor;
  }

  /**
   * Returns the object of the decorated item property descriptor.
   *
   * @since 2.14
   */
  public Object getDecoratedObject()
  {
    return object;
  }

  /**
   * Returns the decorated item property descriptor.
   *
   * @since 2.14
   */
  public IItemPropertyDescriptor getDecoratedItemPropertyDescriptor()
  {
    return itemPropertyDescriptor;
  }

  /**
   * This returns the group of properties into which this one should be placed.
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
   * This is the key that is passed around and must uniquely identify this descriptor.
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
  public boolean isCompatibleWith(Object thisObject, Object anotherObject, IItemPropertyDescriptor anotherItemPropertyDescriptor) 
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
   * This does the delegated job of determining whether the property supports the concept of an unset state.
   *
   * @since 2.14
   */
  public boolean isPropertyUnsettable(Object thisObject)
  {
    return itemPropertyDescriptor instanceof IItemPropertyDescriptor.ValueHandlerProvider && ((IItemPropertyDescriptor.ValueHandlerProvider)itemPropertyDescriptor).isPropertyUnsettable(object);
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
  public boolean isMultiLine(Object thisObject)
  {
    return itemPropertyDescriptor.isMultiLine(object);
  }

  /**
   * This does the delegated job of determining the choices for this property should be sorted for display.
   * @since 2.2.0
   */
  public boolean isSortChoices(Object thisObject)
  {
    return itemPropertyDescriptor.isSortChoices(object);
  }

  /**
   * @since 2.14
   */
  public boolean isChoiceArbitrary(Object thisObject)
  {
    return itemPropertyDescriptor instanceof ValueHandlerProvider && ((ValueHandlerProvider)itemPropertyDescriptor).isChoiceArbitrary(object);
  }

  /**
   * @since 2.14
   */
  public ValueHandler getValueHandler(Object thisObject)
  {
    return itemPropertyDescriptor instanceof ValueHandlerProvider ? ((ValueHandlerProvider)itemPropertyDescriptor).getValueHandler(object) : null;
  }

  /**
   * @since 2.14
   */
  public Object getEditorFactory(Object thisObject)
  {
    return itemPropertyDescriptor instanceof IPropertyEditorFactory.Provider ? ((IPropertyEditorFactory.Provider)itemPropertyDescriptor).getEditorFactory(object) : null;
  }
}

