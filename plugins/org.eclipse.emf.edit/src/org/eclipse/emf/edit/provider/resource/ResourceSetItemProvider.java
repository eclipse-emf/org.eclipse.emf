/**
 * Copyright (c) 2002-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.provider.resource;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.command.AbstractOverrideableCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;


/**
 * This is the item provider adapter for a {@link org.eclipse.emf.ecore.resource.ResourceSet} object.
 */
public class ResourceSetItemProvider
  extends ItemProviderAdapter
  implements 
    IEditingDomainItemProvider,
    IStructuredItemContentProvider, 
    ITreeItemContentProvider, 
    IItemLabelProvider, 
    IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   */
  public ResourceSetItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);
    }
    return itemPropertyDescriptors;
  }

  @Override
  public Collection<?> getChildren(Object object)
  {
    ResourceSet resourceSet = (ResourceSet)object;
    synchronized (resourceSet)
    {
      return new ArrayList<Resource>(resourceSet.getResources());
    }
  }

  /**
   * This returns the parent of the ResourceSet.
   */
  @Override
  public Object getParent(Object object)
  {
    return null;
  }

  /**
   * This returns ResourceSet.gif.
   */
  @Override
  public Object getImage(Object object)
  {
    return getResourceLocator().getImage("full/obj16/ResourceSet");
  }

  /**
   * This returns the label text for the adapted class.
   */
  @Override
  public String getText(Object object)
  {
    return EMFEditPlugin.INSTANCE.getString("_UI_ResourceSet_label");
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged(Notification) fireNotifyChanged}.
   */
  @Override
  public void notifyChanged(Notification notification) 
  {
    switch (notification.getFeatureID(ResourceSet.class))
    {
      case ResourceSet.RESOURCE_SET__RESOURCES:
      {
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
        return;
      }
    }
    super.notifyChanged(notification);
  }

  @Override
  public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling)
  {
    return Collections.emptyList();
  }

  /**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);
  }

  @Override
  protected Command createDragAndDropCommand(EditingDomain domain, Object owner, float location, int operations, int operation, final Collection<?> collection)
  {
    final ResourceSet resourceSet = (ResourceSet)owner;

    class LoadResourceCommand extends AbstractOverrideableCommand implements AbstractCommand.NonDirtying
    {
      protected LoadResourceCommand(EditingDomain domain)
      {
        super(domain);
      }

      protected List<Resource> resources;

      @Override
      protected boolean prepare()
      {
        for (Object object : collection)
        {
          if (!(object instanceof URI))
          {
            return false;
          }
        }
        return true;
      }

      @Override
      public void doExecute()
      {
        resources = new ArrayList<Resource>();
        for (Object object : collection)
        {
          URI uri = (URI)object;
          Resource resource = resourceSet.getResource(uri, false);
          if (resource == null)
          {
            try
            {
              resource = resourceSet.getResource(uri, true);
            }
            catch (RuntimeException exception)
            {
              resource = resourceSet.getResource(uri, false);
              EMFEditPlugin.INSTANCE.log(exception);
            }
          }
          if (resource != null)
          {
            resources.add(resource);
          }
        }
      }

      @Override
      public void doUndo()
      {
        resourceSet.getResources().removeAll(resources);
        resources = null;
      }

      @Override
      public void doRedo()
      {
        doExecute();
      }

      @Override
      public Collection<?> doGetAffectedObjects()
      {
        return resources == null ? Collections.singleton(resourceSet) : resources;
      }

      @Override
      public String doGetDescription()
      {
        return EMFEditPlugin.INSTANCE.getString("_UI_LoadResources_description");
      }

      @Override
      public String doGetLabel()
      {
        return EMFEditPlugin.INSTANCE.getString("_UI_LoadResources_label");
      }
    }

    return new LoadResourceCommand(domain);
  }

  /**
   * Return the resource locator for this item provider's resources.
   */
  @Override
  public ResourceLocator getResourceLocator()
  {
    return EMFEditPlugin.INSTANCE;
  }
}
