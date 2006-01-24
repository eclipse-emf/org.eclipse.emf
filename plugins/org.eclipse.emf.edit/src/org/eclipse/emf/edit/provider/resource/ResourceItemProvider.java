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
 * $Id: ResourceItemProvider.java,v 1.7 2006/01/24 22:18:50 davidms Exp $
 */
package org.eclipse.emf.edit.provider.resource;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;


/**
 * This is the item provider adpater for a {@link org.eclipse.emf.ecore.resource.Resource} object.
 * @generated
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
   * @generated
   */
  public ResourceItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * @generated
   */
  public List getPropertyDescriptors(Object object)
  {
    return super.getPropertyDescriptors(object);
  }

  public Collection getChildren(Object object)
  {
    // Don't include controlled children here, they'll show up under their container.
    //
    List contents = ((Resource)object).getContents();
    Collection result = new ArrayList(contents.size());
    for (Iterator i = contents.iterator(); i.hasNext(); )
    {
      Object o = i.next();
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
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Collection getChildrenFeatures(Object object)
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
   * @generated
   */
  public Object getParent(Object object)
  {
    return ((Resource)object).getResourceSet();
  }

  /**
   * This returns Resource.gif.
   * @generated
   */
  public Object getImage(Object object)
  {
    Resource resource = (Resource)object;
    Object image = URI.createURI(getResourceLocator().getImage("full/obj16/Resource").toString() + "#" + resource.getURI().fileExtension());

    // Overlay if the resource is the target for any controlled objects. 
    //
    for (Iterator i = resource.getContents().iterator(); i.hasNext(); )
    {
      if (AdapterFactoryEditingDomain.isControlled(i.next()))
      {
        List images = new ArrayList(2);
        images.add(image);
        images.add(getImage("full/obj16/ControlledObjectTarget"));
        image = new ComposedImage(images);
        break;
      }
    }
    return image;
  }

  /**
   * This returns the label text for the adapted class.
   * @generated
   */
  public String getText(Object object)
  {
    Resource resource = (Resource)object;
    return resource.getURI() == null ? "" : resource.getURI().toString();
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   * @generated NOT
   */
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
        fireNotifyChanged(notification);
        return;
      }
      // When an object is controlled, the only change will be in the new resource's contents, so it must
      // refresh the whole viewer to hit the object's label.
      //
      case Resource.RESOURCE__CONTENTS:
      {
        fireNotifyChanged(new ViewerNotification(notification));
        return;
      }
    }
    super.notifyChanged(notification);
  }

  public Collection getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling)
  {
    return Collections.EMPTY_LIST;
  }

  /**
   * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s 
   * describing all of the children that can be created under this object.
   * @generated
   */
  protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);
/*
    Resource resource = (Resource)object;

    newChildDescriptors.add
      (createChildParameter
        (ResourcePackage.eINSTANCE.getResource_Contents(),
         ResourcePackage.eINSTANCE.getResourceFactory().createEObject()));
*/
  }

  /**
   * Return the resource locator for this item provider's resources.
   * @generated
   */
  public ResourceLocator getResourceLocator()
  {
    return EMFEditPlugin.INSTANCE;
  }
}
