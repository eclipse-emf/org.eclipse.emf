/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.provider;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposedImage;
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
 * This is the item provider adapter for a {@link org.eclipse.emf.codegen.ecore.genmodel.GenBase} object.
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

    @Override
    public List<ComposedImage.Point> getDrawPoints(Size size)
    {
      List<ComposedImage.Point> result = super.getDrawPoints(size);
      if (result.size() > 2)
      {
        result.get(1).y = -2;
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
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

    }
    return itemPropertyDescriptors;
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
      childrenFeatures.add(GenModelPackage.Literals.GEN_BASE__GEN_ANNOTATIONS);
    }
    return childrenFeatures;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EStructuralFeature getChildFeature(Object object, Object child)
  {
    // Check the type of the specified child object and return the proper feature to use for
    // adding (see {@link AddCommand}) it as a child.

    return super.getChildFeature(object, child);
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
    return getString("_UI_GenBase_type");
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

    switch (notification.getFeatureID(GenBase.class))
    {
      case GenModelPackage.GEN_BASE__GEN_ANNOTATIONS:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
        return;
    }
    super.notifyChanged(notification);
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

  @Override
  protected ItemPropertyDescriptor createItemPropertyDescriptor
    (AdapterFactory adapterFactory, 
     ResourceLocator resourceLocator, 
     String displayName, 
     String description, 
     EStructuralFeature feature, 
     boolean isSettable, 
     boolean multiLine,
     boolean sortChoices,
     Object staticImage, 
     String category, 
     String[] filterFlags)
  {
    return new GenItemPropertyDescriptor(
      adapterFactory,
      resourceLocator,
      displayName,
      description,
      feature,
      isSettable,
      multiLine,
      sortChoices,
      staticImage,
      category,
      filterFlags);
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

    public GenItemPropertyDescriptor
       (AdapterFactory adapterFactory,
        ResourceLocator resourceLocator,
        String displayName,
        String description,
        EStructuralFeature feature, 
        boolean isSettable,
        boolean multiLine,
        boolean sortChoices,
        Object staticImage,
        String category,
        String [] filterFlags)
    {
      super(adapterFactory, resourceLocator, displayName, description, feature, isSettable, multiLine, sortChoices, staticImage, category, filterFlags);
    }

    @Override
    public boolean canSetProperty(Object object)
    {
      return super.canSetProperty(object) && canEdit(object);
    }
    
    @Override
    protected Object createPropertyValueWrapper(Object object, Object propertyValue)
    {
      return propertyValue instanceof EModelElement ?
        new PropertyValueWrapper(adapterFactory, object, propertyValue, propertyValue) :
        super.createPropertyValueWrapper(object, propertyValue);
    }
  }

}
