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
package org.eclipse.emf.edit.ui.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;


/**
 * This is used to encapsulate an {@link IItemPropertySource} along with the object for which it is an item property source
 * and make it behave like an {@link org.eclipse.ui.views.properties.IPropertySource}.
 */
public class PropertySource implements ExtendedPropertySheetPage.IUnsettablePropertySource
{
  /**
   * This is the object for which this class is a property source.
   */
  protected Object object;

  /**
   * This is the descriptor to which we will delegate all the {@link org.eclipse.ui.views.properties.IPropertySource} methods.
   */
  protected IItemPropertySource itemPropertySource;

  /**
   * An instance is constructed from an object and its item property source.
   */
  public PropertySource(Object object, IItemPropertySource itemPropertySource)
  {
    this.object = object;
    this.itemPropertySource = itemPropertySource;
  }

  /**
   * Returns the object for which this is a property source.
   *
   * @since 2.14
   */
  public Object getObject()
  {
    return object;
  }

  /**
   * Returns the underlying item property source of this property source.
   *
   * @since 2.14
   */
  public IItemPropertySource getItemPropertySource()
  {
    return itemPropertySource;
  }

  /**
   * This delegates to {@link IItemPropertySource#getEditableValue IItemPropertySource.getEditableValue}.
   */
  public Object getEditableValue()
  {
    return itemPropertySource.getEditableValue(object);
  }

  /**
   * This delegates to {@link IItemPropertySource#getPropertyDescriptors IItemPropertySource.getPropertyDescriptors}.
   */
  public IPropertyDescriptor [] getPropertyDescriptors()
  {
    Collection<IPropertyDescriptor> result = new ArrayList<IPropertyDescriptor>();
    List<IItemPropertyDescriptor> itemPropertyDescriptors = itemPropertySource.getPropertyDescriptors(object);
    if (itemPropertyDescriptors != null)
    {
      for (IItemPropertyDescriptor itemPropertyDescriptor : itemPropertyDescriptors)
      {
        result.add(createPropertyDescriptor(itemPropertyDescriptor));
      }
    }

    return result.toArray(new IPropertyDescriptor [result.size()]);
  }

  protected IPropertyDescriptor createPropertyDescriptor(IItemPropertyDescriptor itemPropertyDescriptor)
  {
    return new PropertyDescriptor(object, itemPropertyDescriptor);
  }

  /**
   * @since 2.14
   */
  protected IItemPropertyDescriptor getItemPropertyDescriptor(Object propertyId)
  {
    return itemPropertySource.getPropertyDescriptor(object, propertyId);
  }

  /**
   * This delegates to {@link IItemPropertyDescriptor#getPropertyValue IItemPropertyDescriptor.getPropertyValue}.
   */
  public Object getPropertyValue(Object propertyId)
  {
    return getItemPropertyDescriptor(propertyId).getPropertyValue(object);
  }

  /**
   * This delegates to {@link IItemPropertyDescriptor#isPropertySet IItemPropertyDescriptor.isPropertySet}.
   */
  public boolean isPropertySet(Object propertyId)
  {
    return getItemPropertyDescriptor(propertyId).isPropertySet(object);
  }

  /**
   * This returns <code>true</code> only when {@link IItemPropertyDescriptor#isPropertySet IItemPropertyDescriptor.isPropertySet}
   * and {@link IItemPropertyDescriptor#canSetProperty IItemPropertyDescriptor.canSetProperty} are <code>true</code>.
   * @since 2.10
   */
  public boolean isPropertyResettable(Object propertyId)
  {
    IItemPropertyDescriptor propertyDescriptor = getItemPropertyDescriptor(propertyId);
    return propertyDescriptor.canSetProperty(object) && propertyDescriptor.isPropertySet(object);
  }

  /**
   * This delegates to {@link IItemPropertyDescriptor#resetPropertyValue IItemPropertyDescriptor.resetPropertyValue}.
   */
  public void resetPropertyValue(Object propertyId)
  {
    getItemPropertyDescriptor(propertyId).resetPropertyValue(object);
  }

  /**
   * This delegates to {@link IItemPropertyDescriptor#setPropertyValue IItemPropertyDescriptor.setPropertyValue}.
   */
  public void setPropertyValue(Object propertyId, Object value)
  {
    getItemPropertyDescriptor(propertyId).setPropertyValue(object, value);
  }

  /**
   * This returns <code>true</code> only when {@link IItemPropertyDescriptor.ValueHandlerProvider#isPropertyUnsettable IItemPropertyDescriptor.ValueHandlerProvider.isPropertyUnsettable} 
   * and {@link IItemPropertyDescriptor#canSetProperty IItemPropertyDescriptor.canSetProperty} are <code>true</code>.
   * @since 2.14
   */
  public boolean isPropertyUnsettable(Object propertyId)
  {
    IItemPropertyDescriptor propertyDescriptor = getItemPropertyDescriptor(propertyId);
    return 
      propertyDescriptor.canSetProperty(object) && 
        propertyDescriptor instanceof IItemPropertyDescriptor.ValueHandlerProvider &&
        ((IItemPropertyDescriptor.ValueHandlerProvider)propertyDescriptor).isPropertyUnsettable(object);
  }
}
