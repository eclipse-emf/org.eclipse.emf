/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: GenBaseItemProvider.java,v 1.2 2004/04/03 20:43:55 davidms Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.provider;


import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;


/**
 * This is the item provider adpater for a {@link org.eclipse.emf.codegen.ecore.genmodel.GenBase} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GenBaseItemProvider
  extends ItemProviderAdapter
  implements 
    IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
  public static class UnderlayedImage extends ComposedImage
  {
    public UnderlayedImage(Object imageDescriptor)
    {
      super
        (Arrays.asList
          (new Object[] 
           { GenModelEditPlugin.INSTANCE.getImage("full/obj16/Underlay"), 
             imageDescriptor
           }));
    }

    public UnderlayedImage(Object imageDescriptor, Object multiplicity)
    {
      super
        (Arrays.asList
          (new Object[] 
           { GenModelEditPlugin.INSTANCE.getImage("full/obj16/Underlay"), 
             imageDescriptor,
             multiplicity
           }));
    }

    public List getDrawPoints(Size size)
    {
      List result = super.getDrawPoints(size);
      if (result.size() > 2)
      {
        ((Point)result.get(1)).y = -2;
      }
      return result;
    }
  }

  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenBaseItemProvider(AdapterFactory adapterFactory)
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

    }
    return itemPropertyDescriptors;
  }


  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getText(Object object)
  {
    return getString("_UI_GenBase_type");
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
    super.notifyChanged(notification);
  }

  /**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResourceLocator getResourceLocator()
  {
    return GenModelEditPlugin.INSTANCE;
  }
  /**
   * Returns whether changes to the object will be persisted.
   */
  protected static boolean canEdit(Object object)
  {
    if (object instanceof GenModel) return true;
    if (object instanceof GenPackage)
    {
      return ((GenPackage)object).canGenerate();
    }
    if (object instanceof GenBase)
    {
      return canEdit(((GenBase)object).eContainer());
    }
    return false;
  }

  /**
   * A simple extension of ItemPropertyDescriptor that forbids editing of
   * items that will not be persisted.
   */
  protected static class GenItemPropertyDescriptor
    extends ItemPropertyDescriptor
  {
    public GenItemPropertyDescriptor(
      AdapterFactory adapterFactory, String displayName, String description,
      EStructuralFeature feature,  boolean isSettable, String category)
    {
      super(adapterFactory, displayName, description, feature, isSettable,
            category);

    }

    public GenItemPropertyDescriptor(
      AdapterFactory adapterFactory, String displayName, String description,
      EStructuralFeature feature,  boolean isSettable, Object staticImage,
      String category)
    {
      super(adapterFactory, displayName, description, feature, isSettable,
            staticImage, category);
    }

    public boolean canSetProperty(Object object)
    {
      return isSettable && canEdit(object);
    }
  }
}
