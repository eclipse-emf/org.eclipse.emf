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
 * $Id: MappedObjectItemProvider.java,v 1.2 2005/06/08 06:21:43 nickb Exp $
 */
package org.eclipse.emf.mapping.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationWrapper;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptorDecorator;
import org.eclipse.emf.edit.provider.ItemProviderDecorator;
import org.eclipse.emf.mapping.MappedObjectState;
import org.eclipse.emf.mapping.Mapping;


/**
 * This is the item provider adpater for a {@link org.eclipse.emf.mapping.Mapping} object.
 */
public class MappedObjectItemProvider
  extends 
    ItemProviderDecorator
  implements
    IEditingDomainItemProvider,
    IStructuredItemContentProvider,
    ITreeItemContentProvider,
    IItemLabelProvider,
    IItemPropertySource
{
  protected Mapping mapping;
  protected EObject mappedObject;
  protected MappedObjectState mappedObjectState;

  /**
   * This constructs an instance from a factory and a notifier.
   */
  public MappedObjectItemProvider(AdapterFactory adapterFactory, EObject mappedObject, Mapping mapping)
  {
    super(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory());
    setDecoratedItemProvider((IChangeNotifier)this.adapterFactory.adapt(mappedObject, IItemLabelProvider.class));
    this.mappedObject = mappedObject;
    this.mapping = mapping;

    mappedObjectState = mapping.getMappingRoot().getMappedObjectState(mappedObject);
    mappedObjectState.addListener(this);
  }

  /**
   * This returns the nestedIn of the Mapping.
   */
  public Object getParent(Object object)
  {
    // Returning null here causes RemoveCommand.create(domain, mappedObjectItemProvider) to be delegated
    //  to MappedObjectItemProvider.createCommand() and there we return a command to remove mappedObject instead.
    //
    // return null;
    return mapping;
  }

  public Collection getElements(Object object)
  {
    return Collections.EMPTY_LIST;
  }

  public Collection getChildren(Object object)
  {
    return Collections.EMPTY_LIST;
  }

  public boolean hasChildren(Object object)
  {
    return false;
  }

  public Object getImage(Object object)
  {
    return super.getImage(mappedObject);
  }

  public String getText(Object object)
  {
    return super.getText(mappedObject);
  }

  public List getPropertyDescriptors(Object object)
  {
    List descriptors = super.getPropertyDescriptors(mappedObject);

    List result = new ArrayList();
    for (int i = 0; i < descriptors.size(); ++i)
    {
      result.add(new ItemPropertyDescriptorDecorator(mappedObject, (IItemPropertyDescriptor)descriptors.get(i)));
    }
    return result;
  }

  public IItemPropertyDescriptor getPropertyDescriptor(Object object, Object propertyId)
  {
    IItemPropertyDescriptor descriptor = super.getPropertyDescriptor(mappedObject, propertyId);
    if (descriptor != null)
    {
      return new ItemPropertyDescriptorDecorator(mappedObject, descriptor);
    }
    return null;
  }

  /**
   * This handles notification by delegating to {@link #fireNotifyChanged fireNotifyChanged}.
   */
  public void notifyChanged(Notification msg)
  {
    if (msg.getFeature() == null)
    {
      Notification notification = new NotificationWrapper(this, msg);
      super.notifyChanged(notification);
    }
    else
    {
      super.notifyChanged(msg);
    }
  }

  public Command createCommand(Object object, EditingDomain editingDomain, Class commandClass, CommandParameter commandParameter)
  {
    if (commandClass == org.eclipse.emf.edit.command.DragAndDropCommand.class)
    {
      return super.createCommand(object, editingDomain, commandClass, commandParameter);
    }
    else
    {
      return UnexecutableCommand.INSTANCE;
    }
  }

  public EObject getMappedObject()
  {
    return mappedObject;
  }

  public Mapping getMapping()
  {
    return mapping;
  }

  public void dispose()
  {
    mappedObjectState.removeListener(this);
    super.dispose();
  }
}
