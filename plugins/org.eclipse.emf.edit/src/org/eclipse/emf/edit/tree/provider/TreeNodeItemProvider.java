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
 * $Id: TreeNodeItemProvider.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.edit.tree.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.ecore.EObject;
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
import org.eclipse.emf.edit.tree.TreeNode;
import org.eclipse.emf.edit.tree.TreePackage;


/**
 * This is the item provider adpater for a {@link org.eclipse.emf.edit.tree.TreeNode} object.
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
  public List getPropertyDescriptors(Object object)
  {
    TreeNode treeNode = (TreeNode)object;
    itemPropertyDescriptors = new ArrayList();
    for (Iterator propertyDescriptors = itemDelegator.getPropertyDescriptors(treeNode.getData()).iterator(); 
         propertyDescriptors.hasNext(); )
    {
      IItemPropertyDescriptor itemPropertyDescriptor = (IItemPropertyDescriptor)propertyDescriptors.next();
      itemPropertyDescriptors.add(new ItemPropertyDescriptorDecorator(treeNode.getData(), itemPropertyDescriptor));
    }
    return itemPropertyDescriptors;
  }

  /**
   * This specifies how to implement {@link #getChildren} and 
   * {@link org.eclipse.emf.edit.command.AddCommand} and 
   * {@link org.eclipse.emf.edit.command.RemoveCommand} support in 
   * {@link #createCommand}.
   */
  public Collection getChildrenReferences(Object object)
  {
    if (childrenReferences == null)
    {
      super.getChildrenReferences(object);
      TreeNode treeNode = ((TreeNode)object);
      childrenReferences.add(TreePackage.eINSTANCE.getTreeNode_Children());
    }
    return childrenReferences;
  }

  /**
   * This returns the parent of the TreeNode.
   */
  public Object getParent(Object object)
  {
    return ((EObject)object).eContainer();
  }

  public Object getImage(Object object)
  {
    TreeNode treeNode = ((TreeNode)object);
    return itemDelegator.getImage(treeNode.getData());
  }

  public String getText(Object object)
  {
    TreeNode treeNode = ((TreeNode)object);
    return itemDelegator.getText(treeNode.getData());
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   * This will also be called by the {@link #delegateItemProvider} when it normally fires a notification to it's adapter factory;
   * the listener method is hooked up in {@link #setTarget setTarget}.
   */
  public void notifyChanged(final Notification notification)
  {
    fireNotifyChanged
      (new NotificationImpl
        (notification.getEventType(), 
         notification.getOldValue(), 
         notification.getNewValue(), 
         notification.getPosition())
       {
         public Object getNotifier()
         {
           return target;
         }
         public Object getFeature()
         {
           return notification.getFeature();
         }
       });
/*
    Object feature = notification.getFeature();
    TreePackage treePackage = ((TreeNode)notification.getNotifier()).ePackageTree();
    if ( 
         feature == treePackage.getTreeNode_Children() || 
         feature == treePackage.getTreeNode_Parent() || 
         feature == treePackage.getTreeNode_Data()
       )
    {
      fireNotifyChanged(notification);
      return;
    }
    super.notifyChanged(notification);
*/
  }

  /**
   * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s 
   * describing all of the children that can be created under this object.
   * @generated NOT
   */
  protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object)
  {
/*
    super.collectNewChildDescriptors(newChildDescriptors, object);
    TreeNode treeNode = (TreeNode)object;

    newChildDescriptors.add
      (createChildParameter
        (treeNode.ePackageTree().getTreeNode_Children(),
         treeNode.ePackageTree().getTreeFactory().createTreeNode()));
*/
  }

  public void dispose()
  {
    super.dispose();
    if (delegateItemProvider != null)
    {
      delegateItemProvider.removeListener(this);
    }
  }
}
