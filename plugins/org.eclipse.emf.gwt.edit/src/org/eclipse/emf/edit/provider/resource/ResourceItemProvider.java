/**
 * <copyright>
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: ResourceItemProvider.java,v 1.1 2010/04/28 14:48:46 emerks Exp $
 */
package org.eclipse.emf.edit.provider.resource;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;


/**
 * This is the item provider adapter for a {@link org.eclipse.emf.ecore.resource.Resource} object.
 */
public class ResourceItemProvider
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
  public ResourceItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    return super.getPropertyDescriptors(object);
  }

  @Override
  public Collection<?> getChildren(Object object)
  {
    // Don't include controlled children here, they'll show up under their container.
    //
    List<EObject> contents = ((Resource)object).getContents();
    Collection<Object> result = new ArrayList<Object>(contents.size());
    for (Object o : contents)
    {
      if (!AdapterFactoryEditingDomain.isControlled(o))
      {
        result.add(o);
      }
    }
    return result;
  }

  /**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand(Object, EditingDomain, Class, org.eclipse.emf.edit.command.CommandParameter) createCommand}.
   */
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      // Resource resource = (Resource)object;
      // childrenFeatures.add(ResourcePackage.eINSTANCE.getResource_Contents());
    }
    return childrenFeatures;
  }

  /**
   * This returns the parent of the Resource.
   */
  @Override
  public Object getParent(Object object)
  {
    return ((Resource)object).getResourceSet();
  }

  /**
   * This returns Resource.gif.
   */
  @Override
  public Object getImage(Object object)
  {
    Resource resource = (Resource)object;
    Object image = URI.createURI(getResourceLocator().getImage("full/obj16/Resource").toString() + "#" + resource.getURI().fileExtension());

    // Overlay if the resource is the target for any controlled objects. 
    //
    for (Object o : resource.getContents())
    {
      if (AdapterFactoryEditingDomain.isControlled(o))
      {
        List<Object> images = new ArrayList<Object>(2);
        images.add(image);
        images.add(getImage("full/ovr16/ControlledObjectTarget"));
        image = new ComposedImage(images);
        break;
      }
    }
    return image;
  }

  /**
   * This returns the label text for the adapted class.
   */
  @Override
  public String getText(Object object)
  {
    Resource resource = (Resource)object;
    return resource.getURI() == null ? "" : resource.getURI().toString();
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged(Notification) fireNotifyChanged}.
   */
  @Override
  public void notifyChanged(Notification notification) 
  {
    switch (notification.getFeatureID(Resource.class))
    {
      case Resource.RESOURCE__URI:
      // case Resource.RESOURCE__IS_MODIFIED:
      // case Resource.RESOURCE__IS_LOADED:
      // case Resource.RESOURCE__IS_TRACKING_MODIFICATION:
      // case Resource.RESOURCE__RESOURCE_SET:
      {
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      }
      // When an object is controlled, the only change will be in the new resource's contents, so it must
      // refresh the whole viewer to hit the object's label.
      //
      case Resource.RESOURCE__CONTENTS:
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

  /**
   * Return the resource locator for this item provider's resources.
   */
  @Override
  public ResourceLocator getResourceLocator()
  {
    return EMFEditPlugin.INSTANCE;
  }
}
