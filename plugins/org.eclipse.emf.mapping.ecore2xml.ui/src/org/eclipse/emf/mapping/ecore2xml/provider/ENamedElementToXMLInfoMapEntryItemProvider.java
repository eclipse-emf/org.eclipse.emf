/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: ENamedElementToXMLInfoMapEntryItemProvider.java,v 1.6 2006/05/03 20:34:37 davidms Exp $
 */
package org.eclipse.emf.mapping.ecore2xml.provider;


import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
//import org.eclipse.emf.ecore.EObject;
//import org.eclipse.emf.ecore.EReference;
//import org.eclipse.emf.ecore.EStructuralFeature;

//import org.eclipse.emf.ecore.util.FeatureMap;
//import org.eclipse.emf.ecore.util.FeatureMapUtil;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
//import org.eclipse.emf.mapping.MappingPlugin;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLFactory;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage;
import org.eclipse.emf.mapping.ecore2xml.XMLInfo;
import org.eclipse.emf.mapping.ecore2xml.ui.Ecore2XMLUIPlugin;

/**
 * This is the item provider adapter for a {@link java.util.Map.Entry} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ENamedElementToXMLInfoMapEntryItemProvider
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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ENamedElementToXMLInfoMapEntryItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      addKeyPropertyDescriptor(object);
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
         getString("_UI_ENamedElementToXMLInfoMapEntry_key_feature"), //$NON-NLS-1$
         getString("_UI_PropertyDescriptor_description", "_UI_ENamedElementToXMLInfoMapEntry_key_feature", "_UI_ENamedElementToXMLInfoMapEntry_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
         Ecore2XMLPackage.Literals.ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY__KEY,
         true,
         null,
         null,
         null));
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
      childrenFeatures.add(Ecore2XMLPackage.Literals.ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY__VALUE);
    }
    return childrenFeatures;
  }

  /**
   * This returns ENamedElementToXMLInfoMapEntry.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getImage(Object object)
  {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/ENamedElementToXMLInfoMapEntry")); //$NON-NLS-1$
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String getText(Object object)
  {
    Map.Entry eNamedElementToXMLInfoMapEntry = (Map.Entry)object;
    Object key = eNamedElementToXMLInfoMapEntry.getKey();
    Object value = eNamedElementToXMLInfoMapEntry.getValue();
    return "" + (key instanceof ENamedElement ? ((ENamedElement)key).getName() : String.valueOf(key)) + " -> " + (value instanceof XMLInfo ? ((XMLInfo)value).getName() : String.valueOf(value)); //$NON-NLS-1$ //$NON-NLS-2$
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    switch (notification.getFeatureID(Map.Entry.class))
    {
      case Ecore2XMLPackage.ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY__KEY:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case Ecore2XMLPackage.ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY__VALUE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
        return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
   * describing all of the children that can be created under this object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);

    newChildDescriptors.add
      (createChildParameter
        (Ecore2XMLPackage.Literals.ENAMED_ELEMENT_TO_XML_INFO_MAP_ENTRY__VALUE,
         Ecore2XMLFactory.eINSTANCE.createXMLInfo()));
  }

  /**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResourceLocator getResourceLocator()
  {
    return Ecore2XMLUIPlugin.INSTANCE;
  }

}
