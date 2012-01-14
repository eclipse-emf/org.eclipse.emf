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
package org.eclipse.emf.edit.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * This implementation provides a convenient reusable base for item providers that will be used as decorators of other item providers.
 * Default implementations for the following interfaces are provided: 
 * {@link IEditingDomainItemProvider}, {@link IItemLabelProvider}, {@link IItemPropertySource}, {@link IStructuredItemContentProvider},  
 * {@link IItemFontProvider}, {@link IItemColorProvider},
 * {@link ITableItemFontProvider}, {@link ITableItemColorProvider},
 * {@link ITableItemLabelProvider}, and {@link ITreeItemContentProvider}, and {@link IUpdateableItemText#getUpdateableText}.
 */
public class ItemProviderDecorator 
  implements 
    INotifyChangedListener, 
    IItemProviderDecorator, 
    IChangeNotifier,
    IDisposable
{
  /**
   * This keeps track of the adapter factory that created this adaptor.
   * It is also used as the key/type for this adapter.
   */
  protected AdapterFactory adapterFactory;

  /**
   * This keeps track of the item provider being decorated.
   */
  protected IChangeNotifier decoratedItemProvider;

  /**
   * This is used to implement {@link IChangeNotifier}.
   */
  protected IChangeNotifier changeNotifier;

  /**
   * An instance is created from an adapter factory.
   * The factory is used as a key so that we always know which factory created this adapter.
   */
  public ItemProviderDecorator(AdapterFactory adapterFactory)
  {
    this.adapterFactory = adapterFactory;
  }

  /**
   * This returns true, only if this adapter was created by the given factory; the adapter factory is used as the type key.
   */
  public boolean isAdapterForType(Object type)
  {
    return type == adapterFactory;
  }

  /**
   * This provides access to the factory.
   */
  public AdapterFactory getAdapterFactory()
  {
    return adapterFactory;
  }

  /**
   * This returns the item provider that this decorates.
   */
  public IChangeNotifier getDecoratedItemProvider()
  {
    return decoratedItemProvider;
  }

  /**
   * This sets the item provider that this decorates; 
   * it also hooks up the {@link org.eclipse.emf.edit.provider.INotifyChangedListener}.
   */
  public void setDecoratedItemProvider(IChangeNotifier decoratedItemProvider)
  {
    if (this.decoratedItemProvider != null)
    {
      this.decoratedItemProvider.removeListener(this);
    }

    this.decoratedItemProvider = decoratedItemProvider;

    if (decoratedItemProvider != null)
    {
      decoratedItemProvider.addListener(this);
    }
  }

  public void addListener(INotifyChangedListener listener)
  {
    if (changeNotifier == null)
    {
      changeNotifier = new ChangeNotifier();
    }
    changeNotifier.addListener(listener);
  }

  public void removeListener(INotifyChangedListener listener)
  {
    if (changeNotifier != null)
    {
      changeNotifier.removeListener(listener);
    }
  }

  /**
   * This delegates to the {@link #adapterFactory}.
   * The event type is a value from the static constants in {@link org.eclipse.emf.common.notify.Notifier}.
   */
  public void fireNotifyChanged(Notification notification)
  {
    if (adapterFactory instanceof IChangeNotifier)
    {
      IChangeNotifier adapterFactoryChangeNotifier = (IChangeNotifier)adapterFactory;
      adapterFactoryChangeNotifier.fireNotifyChanged(notification);
    }
    if (changeNotifier != null)
    {
      changeNotifier.fireNotifyChanged(notification);
    }
  }

  /**
   * This implements {@link IItemPropertySource#getPropertyDescriptors IItemPropertySource.getPropertyDescriptors} 
   * by delegating to <code>(IItemPropertySource)</code>{@link #decoratedItemProvider}.
   */
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) 
  {
    return ((IItemPropertySource)decoratedItemProvider).getPropertyDescriptors(object);
  }

  /**
   * This implements {@link IItemPropertySource#getPropertyDescriptors IItemPropertySource.getPropertyDescriptors} 
   * by delegating to <code>(IItemPropertySource)</code>{@link #decoratedItemProvider}.
   */
  public IItemPropertyDescriptor getPropertyDescriptor(Object object, Object propertyId) 
  {
    return ((IItemPropertySource)decoratedItemProvider).getPropertyDescriptor(object, propertyId);
  }

  /**
   * This implements {@link IItemPropertySource#getEditableValue IItemPropertySource.getEditableValue} 
   * by delegating to <code>(IItemPropertySource)</code>{@link #decoratedItemProvider}.
   */
  public Object getEditableValue(Object object) 
  {
    return ((IItemPropertySource)decoratedItemProvider).getEditableValue(object);
  }

  /**
   * This implements {@link IStructuredItemContentProvider#getElements IStructuredItemContentProvider.getElements} 
   * by delegating to <code>(IStructuredItemContentProvider)</code>{@link #decoratedItemProvider}.
   */
  public Collection<?> getElements(Object object)
  {
    return ((IStructuredItemContentProvider)decoratedItemProvider).getElements(object);
  }

  /**
   * This implements {@link ITreeItemContentProvider#getChildren ITreeItemContentProvider.getChildren} 
   * by delegating to <code>(ITreeItemContentProvider)</code>{@link #decoratedItemProvider}.
   */
  public Collection<?> getChildren(Object object)
  {
    return ((ITreeItemContentProvider)decoratedItemProvider).getChildren(object);
  }

  /**
   * This implements {@link ITreeItemContentProvider#hasChildren ITreeItemContentProvider.hasChildren} 
   * by delegating to <code>(ITreeItemContentProvider)</code>{@link #decoratedItemProvider}.
   */
  public boolean hasChildren(Object object)
  {
    return ((ITreeItemContentProvider)decoratedItemProvider).hasChildren(object);
  }

  /**
   * This implements {@link ITreeItemContentProvider#getParent ITreeItemContentProvider.getParent} 
   * by delegating to <code>(ITreeItemContentProvider)</code>{@link #decoratedItemProvider}.
   */
  public Object getParent(Object object)
  {
    return ((ITreeItemContentProvider)decoratedItemProvider).getParent(object);
  }

  /**
   * This implements {@link IItemLabelProvider#getImage IItemLabelProvider.getImage} 
   * by delegating to <code>(IItemLabelProvider)</code>{@link #decoratedItemProvider}.
   */
  public Object getImage(Object object)
  {
    return ((IItemLabelProvider)decoratedItemProvider).getImage(object);
  }

  /**
   * This implements {@link ITableItemLabelProvider#getColumnImage ITableItemLabelProvider.getColumnImage} 
   * by delegating to <code>(ITableItemLabelProvider)</code>{@link #decoratedItemProvider}.
   */
  public Object getColumnImage(Object object, int columnIndex)
  {
    return ((ITableItemLabelProvider)decoratedItemProvider).getColumnImage(object, columnIndex);
  }

  /**
   * This implements {@link IItemLabelProvider#getText IItemLabelProvider.getText} 
   * by delegating to <code>(IItemLabelProvider)</code>{@link #decoratedItemProvider}.
   */
  public String getText(Object object)
  {
    return ((IItemLabelProvider)decoratedItemProvider).getText(object);
  }

  /**
   * This implements {@link IItemFontProvider#getFont IItemFontProvider.getFont} 
   * by delegating to <code>(IItemFontProvider)</code>{@link #decoratedItemProvider}.
   */
  public Object getFont(Object object)
  {
    return ((IItemFontProvider)decoratedItemProvider).getFont(object);
  }

  /**
   * This implements {@link ITableItemFontProvider#getFont ITableItemFontProvider.getFont} 
   * by delegating to <code>(IItemFontProvider)</code>{@link #decoratedItemProvider}.
   */
  public Object getFont(Object object, int columnIndex)
  {
    return ((ITableItemFontProvider)decoratedItemProvider).getFont(object, columnIndex);
  }

  /**
   * This implements {@link IItemColorProvider#getForeground IItemColorProvider.getForeground} 
   * by delegating to <code>(IItemColorProvider)</code>{@link #decoratedItemProvider}.
   */
  public Object getForeground(Object object)
  {
    return ((IItemColorProvider)decoratedItemProvider).getForeground(object);
  }

  /**
   * This implements {@link ITableItemColorProvider#getForeground ITableItemColorProvider.getForeground} 
   * by delegating to <code>(ITableItemColorProvider)</code>{@link #decoratedItemProvider}.
   */
  public Object getForeground(Object object, int columnIndex)
  {
    return ((ITableItemColorProvider)decoratedItemProvider).getForeground(object, columnIndex);
  }

  /**
   * This implements {@link IItemColorProvider#getBackground IItemColorProvider.getBackground} 
   * by delegating to <code>(IItemColorProvider)</code>{@link #decoratedItemProvider}.
   */
  public Object getBackground(Object object)
  {
    return ((IItemColorProvider)decoratedItemProvider).getBackground(object);
  }

  /**
   * This implements {@link ITableItemColorProvider#getBackground ITableItemColorProvider.getBackground} 
   * by delegating to <code>(ITableItemColorProvider)</code>{@link #decoratedItemProvider}.
   */
  public Object getBackground(Object object, int columnIndex)
  {
    return ((ITableItemColorProvider)decoratedItemProvider).getBackground(object, columnIndex);
  }

  /**
   * This implements {@link ITableItemLabelProvider#getColumnText ITableItemLabelProvider.getColumnText} 
   * by delegating to <code>(ITableItemLabelProvider)</code>{@link #decoratedItemProvider}.
   */
  public String getColumnText(Object object, int columnIndex)
  {
    return ((ITableItemLabelProvider)decoratedItemProvider).getColumnText(object, columnIndex);
  }

  /**
   * This implements {@link IUpdateableItemText#getUpdateableText IUpdateableItemText.getUpdateableText} 
   * by delegating to <code>(IUpdateableItemText)</code>{@link #decoratedItemProvider}.
   */
  public String getUpdateableText(Object object)
  {
    return ((IUpdateableItemText)decoratedItemProvider).getUpdateableText(object);
  }

  /**
   * This implements {@link IEditingDomainItemProvider#getNewChildDescriptors
   * IEditingDomainItemProvider.getNewChildDescriptors} by delegating to
   * <code>(IEditingDomainItemProvider)</code>{@link #decoratedItemProvider}.
   */
  public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling)
  {
    return ((IEditingDomainItemProvider)decoratedItemProvider).getNewChildDescriptors(object, editingDomain, sibling);
  }

  /**
   * This implements {@link IEditingDomainItemProvider#createCommand IEditingDomainItemProvider.createCommand} 
   * by delegating to <code>(IEditingDomainItemProvider)</code>{@link #decoratedItemProvider}.
   */
  public Command createCommand(Object object, EditingDomain domain, Class<? extends Command> commandClass, CommandParameter commandParameter)
  {
    return ((IEditingDomainItemProvider)decoratedItemProvider).createCommand(object, domain, commandClass, commandParameter);
/*
    //  For convenience of overrides, this class delegates to the various createXyzCommand methods first.
    if (commandClass == SetCommand.class)
    {
      return 
        createSetCommand(domain, commandParameter.getOwner(), commandParameter.getFeature(), commandParameter.getValue());
    }
    else if (commandClass == CopyCommand.class)
    {
      return 
        createCopyCommand(domain, commandParameter.getOwner(), (CopyCommand.Helper)commandParameter.getValue());
    }
    else if (commandClass == CreateCopyCommand.class)
    {
      return 
        createCreateCopyCommand(domain, commandParameter.getOwner(), (CopyCommand.Helper)commandParameter.getValue());
    }
    else if (commandClass == InitializeCopyCommand.class)
    {
      return 
        createInitializeCopyCommand(domain, commandParameter.getOwner(), (CopyCommand.Helper)commandParameter.getValue());
    }
    else if (commandClass == RemoveCommand.class)
    {
      return 
        createRemoveCommand(domain, commandParameter.getOwner(), commandParameter.getFeature(), commandParameter.getCollection());
    }
    else if (commandClass == AddCommand.class)
    {
      return 
        createAddCommand
          (domain, 
           commandParameter.getOwner(), 
           commandParameter.getFeature(), 
           commandParameter.getCollection(),
           commandParameter.getIndex());
    }
    else if (commandClass == MoveCommand.class)
    {
      return 
        createMoveCommand
          (domain, 
           commandParameter.getOwner(), 
           commandParameter.getFeature(), 
           commandParameter.getValue(), 
           commandParameter.getIndex());
    }
    else if (commandClass == ReplaceCommand.class)
    {
      return 
        createReplaceCommand
          (domain, 
           commandParameter.getOwner(), 
           commandParameter.getFeature(), 
           commandParameter.getValue(), 
           commandParameter.getCollection());
    }
    else if (commandClass == DragAndDropCommand.class)
    {
      DragAndDropCommand.Detail detail = (DragAndDropCommand.Detail)commandParameter.getFeature();
      return 
        createDragAndDropCommand
          (domain, 
           commandParameter.getOwner(), 
           detail.location, 
           detail.operations, 
           detail.operation, 
           commandParameter.getCollection());
    }
    else
    {
      return ((IEditingDomainItemProvider)decoratedItemProvider).createCommand(object, domain, commandClass, commandParameter);
    }
*/
  }

/*
  protected Command createSetCommand(EditingDomain domain, Object owner, Object feature, Object value) 
  {
    return 
      ((IEditingDomainItemProvider)decoratedItemProvider).createCommand
        (owner, domain, SetCommand.class, new CommandParameter(owner, feature, value));
  }

  protected Command createCopyCommand(EditingDomain domain, Object owner, CopyCommand.Helper helper)
  {
    return 
      ((IEditingDomainItemProvider)decoratedItemProvider).createCommand
        (owner, domain, CopyCommand.class, new CommandParameter(owner, null, helper));
  }

  protected Command createCreateCopyCommand(EditingDomain domain, Object owner, CopyCommand.Helper helper) 
  {
    return 
      ((IEditingDomainItemProvider)decoratedItemProvider).createCommand
        (owner, domain, CreateCopyCommand.class, new CommandParameter(owner, null, helper));
  }

  protected Command createInitializeCopyCommand(EditingDomain domain, Object owner, CopyCommand.Helper helper) 
  {
    return 
      ((IEditingDomainItemProvider)decoratedItemProvider).createCommand
        (owner, domain, InitializeCopyCommand.class, new CommandParameter(owner, null, helper));
  }

  protected Command createRemoveCommand(EditingDomain domain, Object owner, Object feature, Collection collection) 
  {
    return 
      ((IEditingDomainItemProvider)decoratedItemProvider).createCommand
        (owner, domain, RemoveCommand.class, new CommandParameter(owner, feature, collection));
  }

  protected Command createReplaceCommand(EditingDomain domain, Object owner, Object feature, Object value, Collection collection) 
  {
    return 
      ((IEditingDomainItemProvider)decoratedItemProvider).createCommand
        (owner, domain, ReplaceCommand.class, new CommandParameter(owner, feature, value, collection));
  }

  protected Command createAddCommand(EditingDomain domain, Object owner, Object feature, Collection collection, int index) 
  {
    return 
      ((IEditingDomainItemProvider)decoratedItemProvider).createCommand
        (owner, domain, ReplaceCommand.class, new CommandParameter(owner, feature, collection, index));
  }

  protected Command createMoveCommand(EditingDomain domain, Object owner, Object feature, Object value, int index) 
  {
    return 
      ((IEditingDomainItemProvider)decoratedItemProvider).createCommand
        (owner, domain, ReplaceCommand.class, new CommandParameter(owner, feature, value, index));
  }

  protected Command createDragAndDropCommand
    (EditingDomain domain, Object owner, float location, int operations, int operation, Collection collection)
  {
    DragAndDropCommand.Detail detail = new DragAndDropCommand.Detail(location, operations, operation);
    return 
      ((IEditingDomainItemProvider)decoratedItemProvider).createCommand
        (owner, domain, DragAndDropCommand.class, new CommandParameter(owner, detail, collection));
  }
*/

  /**
   * This will be called by the {@link #decoratedItemProvider} when it normally fires a notification to it's adapter factory.
   * This listener method is hooked up in {@link #setDecoratedItemProvider}.
   */
  public void notifyChanged(Notification notification)
  {
    fireNotifyChanged(notification);
  }

  public void dispose()
  {
    if (decoratedItemProvider != null)
    {
      decoratedItemProvider.removeListener(this);
    }
  }

  @Override
  public String toString()
  {
    return  getClass().getName() + '@' + Integer.toHexString(hashCode());
  }
}
