/**
 * <copyright>
 * </copyright>
 *
 * $Id: ResourceChangeItemProvider.java,v 1.5 2005/04/20 03:00:18 davidms Exp $
 */
package org.eclipse.emf.ecore.change.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.change.ChangePackage;
import org.eclipse.emf.ecore.change.ResourceChange;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.eclipse.emf.ecore.change.ResourceChange} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ResourceChangeItemProvider
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
  public ResourceChangeItemProvider(AdapterFactory adapterFactory)
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

      addResourceURIPropertyDescriptor(object);
      addResourcePropertyDescriptor(object);
      addValuePropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Resource URI feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addResourceURIPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ResourceChange_resourceURI_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_ResourceChange_resourceURI_feature", "_UI_ResourceChange_type"),
         ChangePackage.eINSTANCE.getResourceChange_ResourceURI(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
  }

  /**
   * This adds a property descriptor for the Resource feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addResourcePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ResourceChange_resource_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_ResourceChange_resource_feature", "_UI_ResourceChange_type"),
         ChangePackage.eINSTANCE.getResourceChange_Resource(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
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
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_ResourceChange_value_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_ResourceChange_value_feature", "_UI_ResourceChange_type"),
         ChangePackage.eINSTANCE.getResourceChange_Value(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE));
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
      childrenFeatures.add(ChangePackage.eINSTANCE.getResourceChange_ListChanges());
    }
    return childrenFeatures;
  }

  /**
   * This returns ResourceChange.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getImage(Object object)
  {
    return getResourceLocator().getImage("full/obj16/ResourceChange");
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getText(Object object)
  {
    String label = ((ResourceChange)object).getResourceURI();
    return label == null || label.length() == 0 ?
      getString("_UI_ResourceChange_type") :
      getString("_UI_ResourceChange_type") + " " + label;
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

    switch (notification.getFeatureID(ResourceChange.class))
    {
      case ChangePackage.RESOURCE_CHANGE__RESOURCE_URI:
      case ChangePackage.RESOURCE_CHANGE__RESOURCE:
      case ChangePackage.RESOURCE_CHANGE__VALUE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case ChangePackage.RESOURCE_CHANGE__LIST_CHANGES:
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
  public ResourceLocator getResourceLocator()
  {
    return ChangeEditPlugin.INSTANCE;
  }

}
