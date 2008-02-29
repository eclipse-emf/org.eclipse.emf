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
 * $Id: EStringToStringMapEntryItemProvider.java,v 1.16 2008/02/29 20:50:33 emerks Exp $
 */
package org.eclipse.emf.ecore.provider;


import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link java.util.Map.Entry} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EStringToStringMapEntryItemProvider
  extends ItemProviderAdapter
  implements
    IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EStringToStringMapEntryItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      addKeyPropertyDescriptor(object);
      addValuePropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Key feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addKeyPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_EStringToStringMapEntry_key_feature"),
         getString("_UI_EStringToStringMapEntry_key_description"),
         EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__KEY,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Value feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addValuePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_EStringToStringMapEntry_value_feature"),
         getString("_UI_EStringToStringMapEntry_value_description"),
         EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__VALUE,
         true,
         true,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This returns EStringToStringMapEntry.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(Object object)
  {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/EStringToStringMapEntry"));
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getText(Object object)
  {
    Map.Entry<?, ?> eStringToStringMapEntry = (Map.Entry<?, ?>)object;
    String key = "" + eStringToStringMapEntry.getKey();
    String value = crop("" + eStringToStringMapEntry.getValue());
    return key + " -> " + value;
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    switch (notification.getFeatureID(Map.Entry.class))
    {
      case EcorePackage.ESTRING_TO_STRING_MAP_ENTRY__KEY:
      case EcorePackage.ESTRING_TO_STRING_MAP_ENTRY__VALUE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);
  }

  /**
   * When setting the key attribute, use a command wrapper that reindexes the attribute's details map.
   */
  @Override
  protected Command createSetCommand(EditingDomain domain, final EObject owner, EStructuralFeature feature, Object value, int index) 
  {
    Command result = super.createSetCommand(domain, owner, feature, value, index);
    if (feature == EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__KEY)
    {
      result = new
        CommandWrapper(result)
        {
          @Override
          public void execute() 
          {
            super.execute();
            reindex();
          }
  
          @Override
          public void undo() 
          {
            super.undo();
            reindex();
          }
  
          @Override
          public void redo() 
          {
            super.redo();
            reindex();
          }
  
          private void reindex()
          {
            EObject parent = owner.eContainer();
            if (parent != null)
            {
              EStructuralFeature feature = owner.eContainmentFeature();
              @SuppressWarnings("unchecked")
              List<Object> list = (List<Object>)parent.eGet(feature);
              list.set(list.indexOf(owner), owner);
            }
          }
        };
    }
    return result;
  }

  /**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ResourceLocator getResourceLocator()
  {
    return EcoreEditPlugin.INSTANCE;
  }

}
