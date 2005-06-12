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
 * $Id: ResourceSetItemProvider.java,v 1.4 2005/06/12 13:32:37 emerks Exp $
 */
package org.eclipse.emf.edit.provider.resource;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;


/**
 * This is the item provider adpater for a {@link org.eclipse.emf.ecore.resource.ResourceSet} object.
 * @generated
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
   * @generated
   */
  public ResourceSetItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * @generated
   */
  public List getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);
    }
    return itemPropertyDescriptors;
  }

  public Collection getChildren(Object object)
  {
    ResourceSet resourceSet = (ResourceSet)object;
    return resourceSet.getResources();
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
/*
      ResourceSet resourceSet = (ResourceSet)object;
      childrenFeatures.add(ResourcePackage.eINSTANCE.getResourceSet_Resources());
*/
    }
    return childrenFeatures;
  }

  /**
   * This returns the parent of the ResourceSet.
   * @generated
   */
  public Object getParent(Object object)
  {
    return null;
  }

  /**
   * This returns ResourceSet.gif.
   * @generated
   */
  public Object getImage(Object object)
  {
    return getResourceLocator().getImage("full/obj16/ResourceSet");
  }

  /**
   * This returns the label text for the adapted class.
   * @generated
   */
  public String getText(Object object)
  {
    return EMFEditPlugin.INSTANCE.getString("_UI_ResourceSet_label");
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   * @generated
   */
  public void notifyChanged(Notification notification) 
  {
    switch (notification.getFeatureID(ResourceSet.class))
    {
      case ResourceSet.RESOURCE_SET__RESOURCES:
      {
        fireNotifyChanged(notification);
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
    ResourceSet resourceSet = (ResourceSet)object;

    newChildDescriptors.add
      (createChildParameter
        (ResourcePackage.eINSTANCE.getResourceSet_Resources(),
         ResourcePackage.eINSTANCE.getResourceFactory().createResource()));
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
