/**
 * <copyright> 
 *
 * Copyright (c) 2004-2010 IBM Corporation and others.
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
 * $Id: AttributeValueWrapperItemProvider.java,v 1.2 2010/04/28 20:38:36 khussey Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * A wrapper implementation for simple attribute values.
 */
public class AttributeValueWrapperItemProvider extends WrapperItemProvider
  implements
    IStructuredItemContentProvider,
    ITreeItemContentProvider,
    IItemLabelProvider,
    IItemPropertySource,
    IEditingDomainItemProvider
{
  /**
   * The resource locator from the owner's item provider.
   */
  protected ResourceLocator resourceLocator;

  /**
   * The single property descriptor for the value is cached here as a singleton list.
   */
  protected List<IItemPropertyDescriptor> propertyDescriptors;

  /**
   * Creates an instance for a single-valued attribute.
   */
  public AttributeValueWrapperItemProvider(Object value, EObject owner, EAttribute attribute, AdapterFactory adapterFactory, ResourceLocator resourceLocator)
  {
    super(value, owner, attribute, CommandParameter.NO_INDEX, adapterFactory);
    this.resourceLocator = resourceLocator;
  }

  /**
   * Creates an instance for a value within a multi-valued attribute.
   */
  public AttributeValueWrapperItemProvider(Object value, EObject owner, EAttribute attribute, int index, AdapterFactory adapterFactory, ResourceLocator resourceLocator)
  {
    super(value, owner, attribute, index, adapterFactory);
    this.resourceLocator = resourceLocator;
  }

  /**
   * Creates an instance for a single-valued attribute. Because the item property descriptor that will be created for
   * the value should get a resource locator, this constructor has been deprecated. 
   * 
   * @deprecated As of EMF 2.0.1, replaced by {@link #AttributeValueWrapperItemProvider(Object, EObject, EAttribute, AdapterFactory, ResourceLocator)
   * this form}.
   */
  @Deprecated
  public AttributeValueWrapperItemProvider(Object value, EObject owner, EAttribute attribute, AdapterFactory adapterFactory)
  {
    this(value, owner, attribute, adapterFactory, null);
  }

  /**
   * Creates an instance for a value within a multi-valued attribute.  Because the item property descriptor that will be
   * created for the value should get a resource locator, this constructor has been deprecated.
   * 
   * @deprecated As of EMF 2.0.1, replaced by {@link #AttributeValueWrapperItemProvider(Object, EObject, EAttribute, int, AdapterFactory, ResourceLocator)
   * this form}.
   */
  @Deprecated
  public AttributeValueWrapperItemProvider(Object value, EObject owner, EAttribute attribute, int index, AdapterFactory adapterFactory)
  {
    this(value, owner, attribute, index, adapterFactory, null);
  }

  /**
   * If non-null, the value is converted to a string, using the type of its attribute and the appropriate factory.
   */
  @Override
  public String getText(Object object)
  {
    return value != null ? EcoreUtil.convertToString(((EAttribute)feature).getEAttributeType(), value) : "null";
  }

  /**
   * Creates, caches and returns an item property descriptor for the value.
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (propertyDescriptors == null)
    {
      propertyDescriptors = Collections.<IItemPropertyDescriptor>singletonList(new WrapperItemPropertyDescriptor(resourceLocator, feature));
    }
    return propertyDescriptors;
  }

  /**
   * Returns a wrapped set command that returns as its affected object the replacement wrapper for the value.
   */
  @Override
  protected Command createSetCommand(EditingDomain domain, Object owner, Object feature, Object value, int index) 
  {
    return new ReplacementAffectedObjectCommand(SetCommand.create(domain, this.owner, this.feature, value, this.index));
  }

  /**
   * Returns a {@link WrapperItemProvider.SimpleCopyCommand} that copies the value by converting it into a string and
   * back, using the factory methods.
   */
  @Override
  protected Command createCopyCommand(EditingDomain domain, Object owner, CopyCommand.Helper helper)
  {
    return new SimpleCopyCommand(domain)
    {
      @Override
      public IWrapperItemProvider copy()
      {
        Object valueCopy = null;
        
        if (value != null)
        {
          EDataType dataType = ((EAttribute)feature).getEAttributeType();
          String serialization = EcoreUtil.convertToString(dataType, value);
          valueCopy = EcoreUtil.createFromString(dataType, serialization);
          if (serialization == value && serialization == valueCopy)
          {
            valueCopy = new String((String)value);
          }
        }
        return new AttributeValueWrapperItemProvider(
          valueCopy, (EObject)AttributeValueWrapperItemProvider.this.owner, (EAttribute)feature, index, adapterFactory);
      }
    };
  }
}
