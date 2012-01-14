/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.tree.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptorDecorator;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.emf.edit.tree.TreeNode;
import org.eclipse.emf.edit.tree.TreePackage;


/**
 * This is the item provider adapter for a {@link org.eclipse.emf.edit.tree.TreeNode} object.
 *
 * @generated modifiable
 */
public class TreeNodeItemProvider
  extends ItemProviderAdapter
  implements 
    IEditingDomainItemProvider,
    INotifyChangedListener,
    IStructuredItemContentProvider, 
    ITreeItemContentProvider, 
    IItemLabelProvider, 
    IItemPropertySource
{
  protected IChangeNotifier delegateItemProvider;

  protected AdapterFactoryItemDelegator itemDelegator;

  /**
   * This constructs an instance from a factory and a notifier.
   */
  public TreeNodeItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
    itemDelegator = new AdapterFactoryItemDelegator(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory());
  }

  @Override
  public void setTarget(Notifier target)
  {
    super.setTarget(target);
    if (target == null)
    {
      if (delegateItemProvider != null)
      {
        delegateItemProvider.removeListener(this);
        delegateItemProvider = null;
      }
    }
    else
    {
      TreeNode treeNode = (TreeNode)target;
      delegateItemProvider = 
        (IChangeNotifier)
          ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory().adapt(treeNode.getData(), IItemLabelProvider.class);
      delegateItemProvider.addListener(this);
    }
  }

  /**
   * This returns the property descriptors for the adapted class.
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    TreeNode treeNode = (TreeNode)object;
    itemPropertyDescriptors = new ArrayList<IItemPropertyDescriptor>();
    for (IItemPropertyDescriptor itemPropertyDescriptor : itemDelegator.getPropertyDescriptors(treeNode.getData()))
    {
      itemPropertyDescriptors.add(new ItemPropertyDescriptorDecorator(treeNode.getData(), itemPropertyDescriptor));
    }
    return itemPropertyDescriptors;
  }

  /**
   * This specifies how to implement {@link #getChildren(Object) getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand(Object, org.eclipse.emf.edit.domain.EditingDomain, Class, org.eclipse.emf.edit.command.CommandParameter) createCommand}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   */
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(TreePackage.eINSTANCE.getTreeNode_Children());
    }
    return childrenFeatures;
  }

  /**
   * This returns the parent of the TreeNode.
   */
  @Override
  public Object getParent(Object object)
  {
    return ((EObject)object).eContainer();
  }

  @Override
  public Object getImage(Object object)
  {
    TreeNode treeNode = ((TreeNode)object);
    return itemDelegator.getImage(treeNode.getData());
  }

  @Override
  public String getText(Object object)
  {
    TreeNode treeNode = ((TreeNode)object);
    return itemDelegator.getText(treeNode.getData());
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged(Notification) fireNotifyChanged}.
   * This will also be called by the {@link #delegateItemProvider} when it normally fires a notification to its adapter factory;
   * the listener method is hooked up in {@link #setTarget setTarget}. Notifications are wrapped to look like they originate from
   * the target.
   */
  @Override
  public void notifyChanged(final Notification notification)
  {
    fireNotifyChanged(ViewerNotification.wrapNotification(notification, target));
  }

  /**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   * @generated NOT
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
  {
/*
    super.collectNewChildDescriptors(newChildDescriptors, object);
*/
  }

  @Override
  public void dispose()
  {
    super.dispose();
    if (delegateItemProvider != null)
    {
      delegateItemProvider.removeListener(this);
    }
  }
}
