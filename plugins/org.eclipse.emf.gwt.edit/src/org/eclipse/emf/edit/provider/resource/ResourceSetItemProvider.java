/**
 * <copyright>
 *
 * Copyright (c) 2002-2010 IBM Corporation and others.
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
 * $Id: ResourceSetItemProvider.java,v 1.3 2010/12/12 20:29:46 emerks Exp $
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
      itemPropertyDescriptors.add
        (new IItemPropertyDescriptor()
         {
           public void setPropertyValue(Object object, Object value)
           {
             throw new UnsupportedOperationException();
           }

           public void resetPropertyValue(Object object)
           {
             throw new UnsupportedOperationException();
           }

           public boolean isSortChoices(Object object)
           {
             return false;
           }

           public boolean isPropertySet(Object object)
           {
             return !((ResourceSet)object).getResources().isEmpty();
           }

           public boolean isMultiLine(Object object)
           {
             return false;
           }

           public boolean isMany(Object object)
           {
             return false;
           }

           public boolean isCompatibleWith(Object object, Object anotherObject, IItemPropertyDescriptor anotherPropertyDescriptor)
           {
             return false;
           }

           public Object getPropertyValue(Object object)
           {
             return ((ResourceSet)object).getResources().size();
           }

           public IItemLabelProvider getLabelProvider(Object object)
           {
             return
               new IItemLabelProvider()
               {
                 public String getText(Object object)
                 {
                   return object.toString();
                 }

                 public Object getImage(Object object)
                 {
                   return null;
                 }
               };
           }

           public String getId(Object object)
           {
             return "resourceCount";
           }

           public Object getHelpContextIds(Object object)
           {
             return null;
           }

           public String[] getFilterFlags(Object object)
           {
             return null;
           }

           public Object getFeature(Object object)
           {
             return ResourceSet.RESOURCE_SET__RESOURCES;
           }

           public String getDisplayName(Object object)
           {
             // TODO
             return "Size";
           }

           public String getDescription(Object object)
           {
             // TODO
             return "The number of resources";
           }

           public Collection<?> getChoiceOfValues(Object object)
           {
             return null;
           }

           public String getCategory(Object object)
           {
             return null;
           }

           public boolean canSetProperty(Object object)
           {
             return false;
           }
         });
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
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand(Object, EditingDomain, Class, org.eclipse.emf.edit.command.CommandParameter) createCommand}.
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

  /**
   * Return the resource locator for this item provider's resources.
   */
  @Override
  public ResourceLocator getResourceLocator()
  {
    return EMFEditPlugin.INSTANCE;
  }
}
