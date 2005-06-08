/**b
 * <copyright> 
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: AttributeValueWrapperItemProvider.java,v 1.1.2.1 2005/06/08 18:27:42 nickb Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CopyCommand;
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
   * The attribute through which the value can be set and retrieved.
   */
  protected EAttribute attribute;

  /**
   * The index within the feature at which the value is located.
   */
  protected int index;

  /**
   * The single property descriptor for the value is cached here as a singleton list.
   */
  protected List propertyDescriptors;


  /**
   * Creates an instance for a single-valued attribute.
   */
  public AttributeValueWrapperItemProvider(Object value, EObject owner, EAttribute attribute, AdapterFactory adapterFactory)
  {
    super(value, owner, adapterFactory);
    this.attribute = attribute;
    this.index = CommandParameter.NO_INDEX;
  }

  /**
   * Creates an instance for a value within a multi-valued attribute.
   */
  public AttributeValueWrapperItemProvider(Object value, EObject owner, EAttribute attribute, int index, AdapterFactory adapterFactory)
  {
    super(value, owner, adapterFactory);
    this.attribute = attribute;
    this.index = index;
  }

  /**
   * Returns the index within the attribute at which the value is located.
   */
  public int getIndex()
  {
    return index;
  }

  /**
   * Sets the index.
   */
  public void setIndex(int index)
  {
    this.index = index;
  }

  /**
   * If non-null, the value is converted to a string, using the type of its attribute and the appropriate factory.
   */
  public String getText(Object object)
  {
    return value != null ? EcoreUtil.convertToString(attribute.getEAttributeType(), value) : "null";
  }

  /**
   * Creates, caches and returns a property descriptor decorator for the attribute of the owner. Calls out to {@link
   * #getPropertyName getPropertyName}, {@link #getPropertyDescription getPropertyDescription}, {@link
   * #isPropertySettable isPropertySettable}, and {@link #getPropertyImage getPropertyImage}, giving subclasses an
   * opportunity to override them and control these aspects of the property.
   */
  public List getPropertyDescriptors(Object object)
  {
    if (propertyDescriptors == null)
    {
      IItemPropertyDescriptor descriptor = new ItemPropertyDescriptor(
        getRootAdapterFactory(),
        getPropertyName(),
        getPropertyDescription(),
        attribute,
        isPropertySettable(),
        getPropertyImage());
      descriptor = new ItemPropertyDescriptorDecorator(owner, descriptor);
      propertyDescriptors = Collections.singletonList(descriptor);
    }
    return propertyDescriptors;
  }

  /**
   * Returns whether the attribute is {@link org.eclipse.emf.ecore.EStructuralFeature#isChangeable changeable}.
   */
  protected boolean isPropertySettable()
  {
    return attribute.isChangeable();
  }

  /**
   * Calls {@link WrapperItemProvider#getPropertyImage(Class) getPropertyImage} to obtain the property image for
   * the attribute's type.
   */
  protected Object getPropertyImage()
  {
    return getPropertyImage(attribute.getEType().getInstanceClass());
  }

  /**
   * Returns a {@link WrapperItemProvider.SimpleCopyCommand} that copies the value by converting it into a string and
   * back, using the factory methods.
   */
  protected Command createCopyCommand(EditingDomain domain, Object owner, CopyCommand.Helper helper)
  {
    return new SimpleCopyCommand(domain)
    {
      public IWrapperItemProvider copy()
      {
        Object valueCopy = null;
        
        if (value != null)
        {
          EDataType dataType = attribute.getEAttributeType();
          String serialization = EcoreUtil.convertToString(dataType, value);
          valueCopy = EcoreUtil.createFromString(dataType, serialization);
          if (serialization == value && serialization == valueCopy)
          {
            valueCopy = new String((String)value);
          }
        }
        return new AttributeValueWrapperItemProvider(
          valueCopy, (EObject)AttributeValueWrapperItemProvider.this.owner, attribute, index, adapterFactory);
      }
    };
  }
}
