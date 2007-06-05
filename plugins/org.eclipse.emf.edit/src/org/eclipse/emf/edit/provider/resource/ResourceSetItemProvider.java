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
 * $Id: ResourceSetItemProvider.java,v 1.7 2007/06/05 18:20:55 emerks Exp $
 */
package org.eclipse.emf.edit.provider.resource;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.EMFEditPlugin;
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
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
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
  @Override
  public Object getParent(Object object)
  {
    return null;
  }

  /**
   * This returns ResourceSet.gif.
   * @generated
   */
  @Override
  public Object getImage(Object object)
  {
    return getResourceLocator().getImage("full/obj16/ResourceSet");
  }

  /**
   * This returns the label text for the adapted class.
   * @generated
   */
  @Override
  public String getText(Object object)
  {
    return EMFEditPlugin.INSTANCE.getString("_UI_ResourceSet_label");
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   * @generated
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
   * @generated
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
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
  @Override
  public ResourceLocator getResourceLocator()
  {
    return EMFEditPlugin.INSTANCE;
  }
}
