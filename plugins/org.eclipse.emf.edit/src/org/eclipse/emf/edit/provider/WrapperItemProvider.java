/**
 * <copyright> 
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: WrapperItemProvider.java,v 1.5 2004/08/05 15:42:11 marcelop Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.command.AbstractOverrideableCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.command.DragAndDropCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * A basic implementation of <code>IWrapperProvider</code> from which others can extend. This class provides
 * all the methods required to implement the following item provider iterfaces:
 * <ul>
 * <li>{@link IStructuredItemContentProvider}
 * <li>{@link ITreeItemContentProvider}
 * <li>{@link IItemLabelProvider}
 * <li>{@link IItemPropertySource}
 * <li>{@link IEditingDomainItemProvider}
 * </ul>
 * <p>Subclasses should declare which of these interfaces they are meant to implement, and override methods as needed.
 * In addition, a partial implementation for {@link IUpdateableItemText} is provided, along with additional methods and
 * classes that are useful in implementing multiple subclasses.
 */
public class WrapperItemProvider implements IWrapperItemProvider
{
  /**
   * The wrapped value.
   */
  protected Object value;

  /**
   * The object that owns the value.
   */
  protected Object owner;

  /**
   * The adapter factory for the owner's item provider.
   */
  protected AdapterFactory adapterFactory;

  /**
   * Creates an instance. The adapter factory of the owner's item provider may be needed for echoing notifications and
   * providing property descriptors.
   */
  public WrapperItemProvider(Object value, Object owner, AdapterFactory adapterFactory)
  {
    this.value = value;
    this.owner = owner;
    this.adapterFactory = adapterFactory;
  }

  /**
   * Disposes the wrapper by deactivating any notification that this wrapper may provide. Since this implementation
   * does not provide any notification, this method does nothing. 
   */
  public void dispose()
  {
  }

  /**
   * Returns the wrapped value.
   */
  public Object getValue()
  {
    return value;
  }

  /**
   * Returns {@link org.eclipse.emf.edit.command.CommandParameter#NO_INDEX}, indicating that the index isn't known to
   * this base wrapper implementation.
   */
  public int getIndex()
  {
    return CommandParameter.NO_INDEX;
  }

  /**
   * Has no effect, as the index isn't known to this base wrapper implementation.
   */
  public void setIndex(int index)
  {
  }

  /**
   * {@link IStructuredItemContentProvider#getElements IStructuredItemContentProvider.getElements} is implemented by
   * forwarding the call to {@link #getChildren getChildren}.
   */
  public Collection getElements(Object object)
  {
    return getChildren(object);
  }
 
  /**
   * {@link ITreeItemContentProvider#getChildren ITreeItemContentProvider.getChildren} is implemented to return an
   * empty list. Subclasses may override it to return something else.
   */
  public Collection getChildren(Object object)
  {
    return Collections.EMPTY_LIST;
  }

  /**
   * {@link ITreeItemContentProvider#hasChildren ITreeItemContentProvider.hasChildren} is implemented by testing
   * whether the collection returned by {@link #getChildren getChildren} is non-empty.
   */
  public boolean hasChildren(Object object)
  {
    return !getChildren(object).isEmpty();
  }

  /**
   * {@link ITreeItemContentProvider#getParent ITreeItemContentProvider.getParent} is implemented by returning the
   * {@link #owner}.
   */
  public Object getParent(Object object)
  {
    return owner;
  }

  /**
   * {@link org.eclipse.emf.edit.provider.IItemLabelProvider#getText IItemLabelProvider.getText} is implemented by returning a non-null value, as a
   * string, or "null".
   */
  public String getText(Object object)
  {
    return value != null ? value.toString() : "null";
  }

  /**
   * {@link org.eclipse.emf.edit.provider.IItemLabelProvider#getImage IItemLabelProvider.getImage} is implemented by returning the default icon for
   * an EMF.Edit item.
   */
  public Object getImage(Object object)
  {
    return EMFEditPlugin.INSTANCE.getImage("full/obj16/Item");
  }

  /**
   * {@link IUpdateableItemText#getUpdateableText IUpdateableItemText.getUpdateableText} is implemented by forwarding
   * the call to {@link #getText getText}.
   */
  public String getUpdateableText(Object object)
  {
    return getText(object);
  }

  /**
   * {@link IItemPropertySource#getPropertyDescriptors IItemPropertySource.getPropertyDescriptors} is implemented to
   * return an empty list. Subclasses may override it to return something else.
   */
  public List getPropertyDescriptors(Object object)
  {
    return Collections.EMPTY_LIST;
  }

  /**
   * {@link IItemPropertySource#getPropertyDescriptor IItemPropertySource.getPropertyDescriptor} is implemented by
   * iterating over the descriptors returned by {@link #getPropertyDescriptors getPropertyDescriptors}, and returning
   * the first descriptor whose ID matches the specified ID, or null if none match.
   */
  public IItemPropertyDescriptor getPropertyDescriptor(Object object, Object propertyId)
  {
    for (Iterator i = getPropertyDescriptors(object).iterator(); i.hasNext(); )
    {
      IItemPropertyDescriptor descriptor = (IItemPropertyDescriptor)i.next();
      if (propertyId.equals(descriptor.getId(object)))
      {
        return descriptor;
      }
    }
    return null;
  }

  /**
   * {@link IItemPropertySource#getEditableValue IItemPropertySource.getEditableValue} is implemented to return the
   * value, itself.
   */
  public Object getEditableValue(Object object) 
  {
    return value;
  }

  /**
   * Returns a name for a value's single property. Subclasses may use this in creating a property descriptor, and user
   * subclasses may override it to provide a specific name.
   */
  protected String getPropertyName()
  {
    return EMFEditPlugin.INSTANCE.getString("_UI_ValueProperty_name");
  }

  /**
   * Returns a description for a value's single property. Subclasses may use this in creating a property descriptor,
   * and user subclasses may override it to provide a specific name.
   */
  protected String getPropertyDescription()
  {
    return EMFEditPlugin.INSTANCE.getString("_UI_ValueProperty_description");
  }

  /**
   * Returns whether a value's single property is settable. Subclasses may use this in creating a property descriptor
   * and override it to provide a specific name.
   */
  protected boolean isPropertySettable()
  {
    return false;
  }

  /**
   * Returns an image for a value's single property. By default, the generic property icon is returned. Subclasses
   * may use this in creating a property descriptor and override it to provide a more specific icon. 
   */
  protected Object getPropertyImage()
  {
    return getPropertyImage(null);
  }

  /**
   * Returns the property image for the specified type. Overrides for {@link #getPropertyImage getPropertyImage}
   * typically call this method.
   */
  protected Object getPropertyImage(Class typeClass)
  {
    if (typeClass == Boolean.TYPE || typeClass == Boolean.class)
    {
      return ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE;
    }
    else if (typeClass == Byte.TYPE || typeClass == Byte.class || 
             typeClass == Integer.TYPE || typeClass == Integer.class ||
             typeClass == Long.TYPE || typeClass == Long.class ||
             typeClass == Short.TYPE || typeClass == Short.class)
    {
      return ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE;
    }
    else if (typeClass == Character.TYPE || typeClass == Character.class ||
             typeClass == String.class)
    {
      return ItemPropertyDescriptor.TEXT_VALUE_IMAGE;
    }
    else if (typeClass == Double.TYPE ||  typeClass == Double.class ||
             typeClass == Float.TYPE || typeClass == Float.class)
    {
      return ItemPropertyDescriptor.REAL_VALUE_IMAGE;
    }

    return ItemPropertyDescriptor.GENERIC_VALUE_IMAGE;
  }

  /**
   * {@link IEditingDomainItemProvider#getNewChildDescriptors IEditingDomainItemProvider.getNewChildDescriptors} is
   * implemented to return an empty list. Subclasses may override it to return something else.
   */
  public Collection getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling)
  {
    return Collections.EMPTY_LIST;
  }

  /**
   * {IEditingDomainItemProvider#createCommand IEditingDomainItemProvider.createCommand} is implemented via {@link
   * #baseCreateCommand baseCreateCommand} to create copy and drag-and-drop commands, only.
   */
  public Command createCommand(Object object, EditingDomain domain, Class commandClass, CommandParameter commandParameter)
  {
    return baseCreateCommand(object, domain, commandClass, commandParameter);
  }

  /**
   * Implements creation of copy and drag-and-drop commands by calling out to {@link #createCopyCommand
   * createCopyCommand} or {@link #createDragAndDropCommand createDragAndDropCommand}.
   */
  public Command baseCreateCommand(Object object, EditingDomain domain, Class commandClass, CommandParameter commandParameter)
  {
    if (commandClass == CopyCommand.class)
    {
      return createCopyCommand(domain, commandParameter.getOwner(), (CopyCommand.Helper)commandParameter.getValue());
    }
    else if (commandClass == DragAndDropCommand.class)
    {
      DragAndDropCommand.Detail detail = (DragAndDropCommand.Detail)commandParameter.getFeature();
      return createDragAndDropCommand(domain, commandParameter.getOwner(), detail.location, detail.operations, detail.operation, commandParameter.getCollection());
    }
    else
    {
      return UnexecutableCommand.INSTANCE;
    }
  }

  /**
   * Returns an {@link org.eclipse.emf.common.command.UnexecutableCommand}. An ordinary {@link
   * org.eclipse.emf.edit.command.CopyCommand} is only useful for copying model objects, so it would be inappropriate
   * here. Subclasses should override it to return something more useful, like a concrete subclass of a {@link
   * SimpleCopyCommand} or {@link WrappingCopyCommand}.
   */
  protected Command createCopyCommand(EditingDomain domain, Object owner, CopyCommand.Helper helper)
  {
    return UnexecutableCommand.INSTANCE;
  }

  /**
   * Creates a {@link org.eclipse.emf.edit.command.DragAndDropCommand}.
   */
  protected Command createDragAndDropCommand(EditingDomain domain, Object owner, float location, int operations, int operation, Collection collection)
  {
    return new DragAndDropCommand(domain, owner, location, operations, operation, collection);
  }

  /**
   * A label for copy command inner classes, the same one used by {@link org.eclipse.emf.edit.command.CopyCommand}.
   */
  protected static final String COPY_COMMAND_LABEL = EMFEditPlugin.INSTANCE.getString("_UI_CopyCommand_label");

  /**
   * A description for copy command inner classes, the same as in {@link org.eclipse.emf.edit.command.CopyCommand}.
   */
  protected static final String COPY_COMMAND_DESCRIPTION = EMFEditPlugin.INSTANCE.getString("_UI_CopyCommand_description");

  /**
   * A command base class for copying a simple value and the wrapper. This is useful when the value isn't able provide
   * an adapter to return a copy command, itself. This class just provides the scaffolding; concrete subclasses must
   * implement {@link #copy copy} to do the copying.
   */
  public abstract class SimpleCopyCommand extends AbstractOverrideableCommand
  {
    protected Collection result = Collections.EMPTY_LIST;
    protected Collection affectedObjects;

    /**
     * Creates an instance for the given domain.
     */
    public SimpleCopyCommand(EditingDomain domain)
    {
      super(domain, COPY_COMMAND_LABEL, COPY_COMMAND_DESCRIPTION);
    }

    /**
     * Returns true; this command can requires now preparation and can always be executed.
     */
    protected boolean prepare()
    {
      return true;
    }

    /**
     * Calls {@link #copy} to do the copying, {@link IDisposable#dispose disposes} the copy, and sets it to 
     * be the result of the command. Since the copy has not been created within the viewed model, it should never do
     * any kind of notification, which is why it is immediately disposed.
     */
    public void doExecute()
    {
      IWrapperItemProvider copy = copy();
      copy.dispose();
      result = Collections.singletonList(copy);
    }

    /**
     * Concrete subclasses must implement this to copy and return the value and wrapper.
     */
    public abstract IWrapperItemProvider copy();

    /**
     * Does nothing.
     */
    public void doUndo()
    {
      // no-op
    }

    /**
     * Does nothing.
     */
    public void doRedo()
    {
      // no-op
    }

    /**
     * If the command has executed, returns a list containing only the copy of the wrapper.
     */
    public Collection doGetResult()
    {
      return result;
    }

    /**
     * Returns a list containing only the original wrapper itself.
     */
    public Collection doGetAffectedObjects()
    {
      if (affectedObjects == null)
      {
        affectedObjects = Collections.singletonList(WrapperItemProvider.this);
      }
      return affectedObjects;
    }
  }

  /**
   * A command base class for copying the wrapper for a value that is partly copied by another command. This is useful
   * when the value includes a model object that is able provide an adapter to return a copy command, but also includes
   * an element that is not adaptable, such as a feature map entry. This command copies the non-adapter element and the
   * wrapper, which ensures the copy can be copied again.
   */
  public abstract class WrappingCopyCommand extends CommandWrapper
  {
    protected Collection result = Collections.EMPTY_LIST;
    protected Collection affectedObjects;

    /**
     * Creates an instance where some adaptable value is copied by the given command.
     */
    public WrappingCopyCommand(Command command)
    {
      super(command);
    }

    /**
     * Executes the adaptable-value-copying command, then calls {@link #copy copy} to copy the rest of the value and
     * the wrapper, {@link IDisposable#dispose disposes} the copy, and sets it to be the result of the
     * command. Since the copy has not been created within the viewed model, it should never do any kind of
     * notification, which is why it is immediately disposed.
     */
    public void execute()
    {
      super.execute();
      IWrapperItemProvider copy = copy();
      copy.dispose();
      result = Collections.singletonList(copy);
    }

    /**
     * Concrete subclasses must implement this to copy and return the value and wrapper. The result of the
     * adaptable-value-copying command is available from <code>getCommand().getResult()</code>.
     */
    public abstract IWrapperItemProvider copy();

    /**
     * If the command has executed, returns a list containing only the copy of the wrapper.
     */
    public Collection getResult()
    {
      return result;
    }

    /**
     * Returns a list containing only the original wrapper itself.
     */
    public Collection getAffectedObjects()
    {
      if (affectedObjects == null)
      {
        affectedObjects = Collections.singletonList(WrapperItemProvider.this);
      }
      return affectedObjects;
    }
  }

  /**
   * Returns the {@link #adapterFactory}, if non-composeable, otherwise, returns its root adapter factory.
   */
  protected AdapterFactory getRootAdapterFactory()
  {
    return adapterFactory instanceof ComposeableAdapterFactory ?
      ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory() :
      adapterFactory;
  }
}
