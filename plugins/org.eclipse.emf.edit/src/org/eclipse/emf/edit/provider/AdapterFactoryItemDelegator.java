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
 * $Id: AdapterFactoryItemDelegator.java,v 1.1.2.1 2005/06/08 18:27:42 nickb Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 */
public class AdapterFactoryItemDelegator 
  implements 
    IEditingDomainItemProvider,
    IItemLabelProvider,
    IItemPropertySource,
    IStructuredItemContentProvider,
    ITableItemLabelProvider,
    ITreeItemContentProvider
{
  /**
   * This keeps track of the factory used to generate the adapters.
   */
  protected AdapterFactory  adapterFactory;

  /**
   */
  public AdapterFactoryItemDelegator(AdapterFactory adapterFactory)
  {
    this.adapterFactory = adapterFactory;
  }

  public AdapterFactory getAdapterFactory()
  {
    return adapterFactory;
  }

  /**
   * This does the same thing as ILabelProvider.getText, 
   * it fetches the label text specific to this object instance.
   */
  public String getText(Object object)
  {
    if (object instanceof EList)
    {
      StringBuffer result = new StringBuffer();
      for (Iterator i = ((List)object).iterator(); i.hasNext(); )
      {
        Object child = i.next();
        if (result.length() != 0)
        {
          result.append(", ");
        }
        result.append(getText(child));
      }
      return result.toString();
    }
    else
    {
      //
      IItemLabelProvider itemLabelProvider = (IItemLabelProvider)adapterFactory.adapt(object, IItemLabelProvider.class);
  
      return
        itemLabelProvider != null ?
          itemLabelProvider.getText(object) :
          object == null ?
            "" :
            object.toString();
    }
  }

  /**
   * This does the same thing as ILabelProvider.getImage, 
   * it fetches the label image specific to this object instance.
   */
  public Object getImage(Object object)
  {
    if (object instanceof EList)
    {
      for (Iterator i = ((List)object).iterator(); i.hasNext(); )
      {
        Object child = i.next();
        return getImage(child);
      }

      return null;
    }
    else
    {
      //
      IItemLabelProvider itemLabelProvider = (IItemLabelProvider)adapterFactory.adapt(object, IItemLabelProvider.class);
  
      return
        itemLabelProvider != null ?
          itemLabelProvider.getImage(object) :
          null;
    }
  }

  /**
   * This does the same thing as ITableLabelProvider.getColumnText.
   */
  public String getColumnText(Object object, int columnIndex)
  {
    // Get the adapter from the factory.
    //
    ITableItemLabelProvider tableItemLabelProvider = (ITableItemLabelProvider)adapterFactory.adapt(object, ITableItemLabelProvider.class);

    // Now we could check that the adapter implements interface ITableItemLabelProvider.
    //
    if (tableItemLabelProvider != null)
    {
      // And delegate the call.
      //
      return tableItemLabelProvider.getColumnText(object, columnIndex);
    }
    // Otherwise, we could check that the adapter implements interface IItemLabelProvider.
    //
    else
    {
      IItemLabelProvider itemLabelProvider = (IItemLabelProvider)adapterFactory.adapt(object, IItemLabelProvider.class);
      if (itemLabelProvider != null)
      {
        // And delegate the call.
        //
        return itemLabelProvider.getText(object);
      }
      // If there is a column object, just convert it to a string.
      //
      else if (object != null)
      {
        return object.toString();
      }
      else
      {
      return "";
      }
    }
  }

  /**
   * This does the same thing as ITableLabelProvider.getColumnImage.
   */
  public Object getColumnImage(Object object, int columnIndex)
  {
    // Get the adapter from the factory.
    //
    ITableItemLabelProvider tableItemLabelProvider = (ITableItemLabelProvider)adapterFactory.adapt(object, ITableItemLabelProvider.class);

    // No image is a good default.
    //
    Object result = null;

    // Now we could check that the adapter implements interface ITableItemLabelProvider.
    //
    if (tableItemLabelProvider  != null)
    {
      // And delegate the call.
      //
      result = tableItemLabelProvider.getColumnImage(object, columnIndex);
    }
    // Otherwise, we could check that the adapter implements interface IItemLabelProvider.
    //
    else
    {
      IItemLabelProvider itemLabelProvider = (IItemLabelProvider)adapterFactory.adapt(object, IItemLabelProvider.class);
      if (itemLabelProvider != null)
      {
        // And delegate the call.
        //
        result = itemLabelProvider.getImage(object);
      }
    }

    return result;
  }


  public List getPropertyDescriptors(Object object)
  {
    //
    IItemPropertySource itemPropertySource = (IItemPropertySource)adapterFactory.adapt(object, IItemPropertySource.class);

    return
      itemPropertySource != null ?
        itemPropertySource.getPropertyDescriptors(object) :
        null;
  }

  public IItemPropertyDescriptor getPropertyDescriptor(Object object, Object propertyId)
  {
    //
    IItemPropertySource itemPropertySource = (IItemPropertySource)adapterFactory.adapt(object, IItemPropertySource.class);

    return
      itemPropertySource != null ?
        itemPropertySource.getPropertyDescriptor(object, propertyId) :
        null;
  }

  public Object getEditableValue(Object object)
  {
    //
    IItemPropertySource itemPropertySource = (IItemPropertySource)adapterFactory.adapt(object, IItemPropertySource.class);

    return
      itemPropertySource != null ?
        itemPropertySource.getEditableValue(object) :
        object;
  }

  /**
   * This does the same thing as IStructuredContentProvider.getElements.
   */
  public Collection getElements(Object object)
  {
    // Get the adapter from the factory.
    //
    IStructuredItemContentProvider structuredItemContentProvider =
      (IStructuredItemContentProvider)adapterFactory.adapt(object, IStructuredItemContentProvider.class);

    // Either delegate the call or return nothing.
    //
    return
      structuredItemContentProvider != null ?
        structuredItemContentProvider.getElements(object) :
        Collections.EMPTY_LIST;
  }


  /**
   * This does the same thing as ITreeContentProvider.getChildren.
   */
  public Collection getChildren(Object object)
  {
    // Get the adapter from the factory.
    //
    ITreeItemContentProvider treeItemContentProvider =
      (ITreeItemContentProvider)adapterFactory.adapt(object, ITreeItemContentProvider.class);

    // Either delegate the call or return nothing.
    //
    return
      treeItemContentProvider != null ?
        treeItemContentProvider.getChildren(object) :
        Collections.EMPTY_LIST;
  }

  /**
   * This does the same thing as ITreeContentProvider.hasChildren.
   */
  public boolean hasChildren(Object object)
  {
    // Get the adapter from the factory.
    //
    ITreeItemContentProvider treeItemContentProvider =
      (ITreeItemContentProvider)adapterFactory.adapt(object, ITreeItemContentProvider.class);

    // Either delegate the call or return nothing.
    //
    return
      treeItemContentProvider != null  &&
        treeItemContentProvider.hasChildren(object);
  }

  /**
   * This does the same thing as ITreeContentProvider.getParent.
   */
  public Object getParent(Object object)
  {
    // Get the adapter from the factory.
    //
    ITreeItemContentProvider treeItemContentProvider =
      (ITreeItemContentProvider)adapterFactory.adapt(object, ITreeItemContentProvider.class);

    // Either delegate the call or return nothing.
    //
    return
      treeItemContentProvider != null ?
        treeItemContentProvider.getParent(object) :
        null;
  }

  /**
   * This sets the given object's parent to be parent.
   */
  //public void setParent(Object object, Object parent)


  /**
   * This returns the text that will be displayed when editing begins.
   */
  //public String getUpdateableText(Object object)

  /**
   * This sets the given object's label text to the given text. 
   */
  //public void setText(Object object, String text)

  /**
   * This returns a list of objects describing the different children that
   * can be added under the specified object in the editing domain,
   * following the specified sibling as closely as possible (if non-null).
   */
  public Collection getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling)
  {
    // Get the adapter from the factory.
    //
    IEditingDomainItemProvider editingDomainItemProvider =
      (IEditingDomainItemProvider)adapterFactory.adapt(object, IEditingDomainItemProvider.class);

    // Either delegate the call or return nothing.
    //
    return
      editingDomainItemProvider != null ?
        editingDomainItemProvider.getNewChildDescriptors(object, editingDomain, sibling) :
        Collections.EMPTY_LIST;
  }

  /**
   * This does the same thing as {@link org.eclipse.emf.edit.domain.EditingDomain#createCommand EditingDomain.createCommand},
   * i.e., it creates commands for a domain's model objects.
   */
  public Command createCommand(Object object, EditingDomain editingDomain, Class commandClass, CommandParameter commandParameter)
  {
    // Get the adapter from the factory.
    //
    IEditingDomainItemProvider editingDomainItemProvider =
      (IEditingDomainItemProvider)adapterFactory.adapt(object, IEditingDomainItemProvider.class);

    // Either delegate the call or return nothing.
    //
    return
      editingDomainItemProvider != null ?
        editingDomainItemProvider.createCommand(object, editingDomain, commandClass, commandParameter) :
        UnexecutableCommand.INSTANCE;
  }
}
