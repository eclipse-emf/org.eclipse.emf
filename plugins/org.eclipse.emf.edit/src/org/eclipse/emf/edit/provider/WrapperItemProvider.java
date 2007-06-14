/**
 * <copyright> 
 *
 * Copyright (c) 2004-2007 IBM Corporation and others.
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
 * $Id: WrapperItemProvider.java,v 1.12 2007/06/14 18:32:42 emerks Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.command.AbstractOverrideableCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.command.DragAndDropCommand;
import org.eclipse.emf.edit.command.SetCommand;
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
   * The structural feature, if applicable, through which the value can be set and retrieved.
   */
  protected EStructuralFeature feature;

  /**
   * The index at which the value is located. If {@link #feature} is non-null, this index is within that feature.
   */
  protected int index;

  /**
   * The adapter factory for the owner's item provider.
   */
  protected AdapterFactory adapterFactory;

  /**
   * Creates an instance. The adapter factory of the owner's item provider may be needed for echoing notifications and
   * providing property descriptors.
   */
  public WrapperItemProvider(Object value, Object owner, EStructuralFeature feature, int index, AdapterFactory adapterFactory)
  {
    this.value = value;
    this.owner = owner;
    this.feature = feature;
    this.index = index;
    this.adapterFactory = adapterFactory;
  }

  /**
   * Disposes the wrapper by deactivating any notification that this wrapper may provide. Since this implementation
   * does not provide any notification, this method does nothing. 
   */
  public void dispose()
  {
    // Do nothing.
  }

  /**
   * Returns the wrapped value.
   */
  public Object getValue()
  {
    return value;
  }

  /**
   * Returns the object that owns the value.
   */
  public Object getOwner()
  {
    return owner;
  }

  /**
   * Returns the structural feature through which the value can be set and retrieved, or null if the feature is
   * unknown or not applicable.
   */
  public EStructuralFeature getFeature()
  {
    return feature;
  }

  /**
   * The index at which the value is located, or {@link org.eclipse.emf.edit.command.CommandParameter#NO_INDEX} if the
   * index isn't known to the wrapper. If {@link #feature} is non-null, this index is within that feature. 
   */
  public int getIndex()
  {
    return index;
  }

  /**
   * Sets the index. Has no effect if the index isn't known to the wrapper.
   */
  public void setIndex(int index)
  {
    this.index = index;
  }

  /**
   * {@link IStructuredItemContentProvider#getElements IStructuredItemContentProvider.getElements} is implemented by
   * forwarding the call to {@link #getChildren getChildren}.
   */
  public Collection<?> getElements(Object object)
  {
    return getChildren(object);
  }
 
  /**
   * {@link ITreeItemContentProvider#getChildren ITreeItemContentProvider.getChildren} is implemented to return an
   * empty list. Subclasses may override it to return something else.
   */
  public Collection<?> getChildren(Object object)
  {
    return Collections.emptyList();
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
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    return Collections.emptyList();
  }

  /**
   * {@link IItemPropertySource#getPropertyDescriptor IItemPropertySource.getPropertyDescriptor} is implemented by
   * iterating over the descriptors returned by {@link #getPropertyDescriptors getPropertyDescriptors}, and returning
   * the first descriptor whose ID matches the specified ID, or null if none match.
   */
  public IItemPropertyDescriptor getPropertyDescriptor(Object object, Object propertyId)
  {
    for (IItemPropertyDescriptor descriptor : getPropertyDescriptors(object))
    {
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
   * Returns whether a value's single property is settable. By default, this returns whether the structural feature is
   * {@link org.eclipse.emf.ecore.EStructuralFeature#isChangeable changeable}. Subclasses may use this in creating a
   * property descriptor, and user subclasses may override it to restrict or allow setting of the property.
   */
  protected boolean isPropertySettable()
  {
    return feature.isChangeable();
  }

  /**
   * Returns whether value's single property consists of multi-line text. By default, false is returned. Subclasses may use this in
   * creating a property descriptor, and user subclasses may override it to enable multi-line text editing.
   * @since 2.2.0
   */
  protected boolean isPropertyMultiLine()
  {
    return false;
  }

  /**
   * Returns whether value's single property should sort its choices for selection. By default, false is returned. Subclasses
   * may use this in creating a property descriptor, and user subclasses may override it to enable sorting.
   * @since 2.2.0
   */
  protected boolean isPropertySortChoices()
  {
    return false;
  }

  /**
   * Returns an image for a value's single property. By default, a standard property icon is selected based on the type
   * of the structural feature. Subclasses may use this in creating a property descriptor, and user subclasses may
   * override it to select a different icon. 
   */
  protected Object getPropertyImage()
  {
    return getPropertyImage(feature.getEType().getInstanceClass());
  }

  /**
   * Returns the property image for the specified type. Implementations of {@link #getPropertyImage getPropertyImage}
   * typically call this method.
   */
  protected Object getPropertyImage(Class<?> typeClass)
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
   * Returns a category for a value's single property. By default, null is returned. Subclasses may use this in
   * creating a property descriptor, and user subclasses may override it to actually provide a category.
   */
  protected String getPropertyCategory()
  {
    return null;
  }

  /**
   * Returns filter flags for a value's single property. By default, null is returned. Subclasses may use this in
   * creating a property descriptor, and user subclasses may override it to actually provide filter flags.
   */
  protected String[] getPropertyFilterFlags()
  {
    return null;
  }

  /**
   * {@link IEditingDomainItemProvider#getNewChildDescriptors IEditingDomainItemProvider.getNewChildDescriptors} is
   * implemented to return an empty list. Subclasses may override it to return something else.
   */
  public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling)
  {
    return Collections.emptyList();
  }

  /**
   * {IEditingDomainItemProvider#createCommand IEditingDomainItemProvider.createCommand} is implemented via {@link
   * #baseCreateCommand baseCreateCommand} to create set, copy, and drag-and-drop commands, only.
   */
  public Command createCommand(Object object, EditingDomain domain, Class<? extends Command> commandClass, CommandParameter commandParameter)
  {
    return baseCreateCommand(object, domain, commandClass, commandParameter);
  }

  /**
   * Implements creation of a set, copy, or drag-and-drop command by calling out to {@link #createSetCommand
   * createSetCommand}, {@link #createCopyCommand createCopyCommand}, or {@link #createDragAndDropCommand
   * createDragAndDropCommand}.
   */
  public Command baseCreateCommand(Object object, EditingDomain domain, Class<? extends Command> commandClass, CommandParameter commandParameter)
  {
    if (commandClass == SetCommand.class)
    {
      return 
        createSetCommand
          (domain, commandParameter.getOwner(), commandParameter.getFeature(), commandParameter.getValue(), commandParameter.getIndex());
    }
    else if (commandClass == CopyCommand.class)
    {
      return createCopyCommand(domain, commandParameter.getOwner(), (CopyCommand.Helper)commandParameter.getValue());
    }
    else if (commandClass == DragAndDropCommand.class)
    {
      DragAndDropCommand.Detail detail = (DragAndDropCommand.Detail)commandParameter.getFeature();
      return 
        createDragAndDropCommand
          (domain, commandParameter.getOwner(), detail.location, detail.operations, detail.operation, commandParameter.getCollection());
    }
    else
    {
      return UnexecutableCommand.INSTANCE;
    }
  }

  /**
   * Return an {@link org.eclipse.emf.common.command.UnexecutableCommand}. Subclasses should override this to map this
   * into a real set on a model object.
   */
  protected Command createSetCommand(EditingDomain domain, Object owner, Object feature, Object value, int index) 
  {
    return UnexecutableCommand.INSTANCE;
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
  protected Command createDragAndDropCommand
    (EditingDomain domain, Object owner, float location, int operations, int operation, Collection<?> collection)
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
  protected abstract class SimpleCopyCommand extends AbstractOverrideableCommand
  {
    protected Collection<?> result = Collections.EMPTY_LIST;
    protected Collection<?> affectedObjects;

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
    @Override
    protected boolean prepare()
    {
      return true;
    }

    /**
     * Calls {@link #copy} to do the copying, {@link IDisposable#dispose disposes} the copy, and sets it to 
     * be the result of the command. Since the copy has not been created within the viewed model, it should never do
     * any kind of notification, which is why it is immediately disposed.
     */
    @Override
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
    @Override
    public void doUndo()
    {
      // no-op
    }

    /**
     * Does nothing.
     */
    @Override
    public void doRedo()
    {
      // no-op
    }

    /**
     * If the command has executed, returns a list containing only the copy of the wrapper.
     */
    @Override
    public Collection<?> doGetResult()
    {
      return result;
    }

    /**
     * Returns a list containing only the original wrapper itself.
     */
    @Override
    public Collection<?> doGetAffectedObjects()
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
  protected abstract class WrappingCopyCommand extends CommandWrapper
  {
    protected Collection<?> result = Collections.EMPTY_LIST;
    protected Collection<?> affectedObjects;

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
    @Override
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
    @Override
    public Collection<?> getResult()
    {
      return result;
    }

    /**
     * Returns a list containing only the original wrapper itself.
     */
    @Override
    public Collection<?> getAffectedObjects()
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

  /**
   * An item property descriptor for the single property of a wrapper for a simple value. This extends the base
   * implementation and substitutes the wrapper's owner for the selected object (the wrapper itself) in the call to {@link
   * #getPropertyValue getPropertyValue}. Thus, the owner must be an EObject to use this class. The property's name,
   * description, settable flag, static image, category, and filter flags are obtained by calling out to various
   * template methods, so can be easily changed by deriving a subclass.
   */
  protected class WrapperItemPropertyDescriptor extends ItemPropertyDescriptor
  {
    public WrapperItemPropertyDescriptor(ResourceLocator resourceLocator, EStructuralFeature feature)
    {
      super(
        WrapperItemProvider.this.adapterFactory,
        resourceLocator,
        getPropertyName(),
        getPropertyDescription(),
        feature,
        isPropertySettable(),
        isPropertyMultiLine(),
        isPropertySortChoices(),
        getPropertyImage(),
        getPropertyCategory(),
        getPropertyFilterFlags());
    }

    /**
     * Substitutes the wrapper owner for the selected object and invokes the base implementation. The actual value
     * returned depends on the implementation of {@link #getValue getValue}.
     */
    @Override
    public Object getPropertyValue(Object object)
    {
      return super.getPropertyValue(owner);
    }

    /**
     * Substitutes the wrapper owner for the selected object and invokes the base implementation.
     */
    @Override
    public boolean canSetProperty(Object object)
    {
      return super.canSetProperty(owner);
    }

    /**
     * Returns <code>true</code>, as the property of a value wrapper is always considered to be set. 
     */
    @Override
    public boolean isPropertySet(Object object)
    {
      return true;
    }

    /**
     * Does nothing, as resetting the property of a value wrapper is not meaningful.
     */
    @Override
    public void resetPropertyValue(Object object)
    {
      // Do nothing
    }

    /**
     * Sets the property value. If an editing domain can be obtained, the command returned by {@link #createSetCommand
     * createSetcommand} is executed; otherwise, {@link #setValue setValue} is called to set the value.
     */
    @Override
    public void setPropertyValue(Object object, Object value)
    {
      EObject eObject = (EObject)owner;
      EditingDomain editingDomain = getEditingDomain(owner);

      if (editingDomain == null)
      {
        setValue(eObject, feature, value);
      }
      else
      {
        editingDomain.getCommandStack().execute(createSetCommand(editingDomain, eObject, feature, value));
      }
    }

    /**
     * Returns a value from a model object. If the feature is multi-valued, only the single value that the wrapper
     * represents is returned.
     */
    @Override
    protected Object getValue(EObject object, EStructuralFeature feature)
    {
      // When the value is changed, the property sheet page doesn't update the property sheet viewer input
      // before refreshing, and this gets called on the obsolete wrapper. So, we need to read directly from the
      // model object.
      //
      //return value;

      Object result = ((EObject)owner).eGet(feature);
      if (feature.isMany())
      {
        // If the last object was deleted and the selection was in the property sheet view, the obsolete wrapper will
        // reference past the end of the list.
        //
        List<?> list = (List<?>)result;
        result = index >= 0 && index < list.size() ? list.get(index) : value;
      }
      return result;
    }

    /**
     * Sets a value on a model object. If the feature is multi-valued, only the single value that the wrapper
     * represents is set.
     */
    protected void setValue(EObject object, EStructuralFeature feature, Object value)
    {
      if (feature.isMany())
      {
        @SuppressWarnings("unchecked")
        List<Object> list = ((List<Object>)object.eGet(feature));
        list.set(index, value);
      }
      else
      {
        object.eSet(feature, value);
      }
    }

    /**
     * Returns a command that will set the value on the model object. The wrapper is used as the owner of the command,
     * unless overridden, so that it can specialize the command that eventually gets created.
     */
    protected Command createSetCommand(EditingDomain domain, Object owner, Object feature, Object value)
    {
      return SetCommand.create(domain, getCommandOwner(WrapperItemProvider.this), null, value);
    }

    /**
     * Returns <code>false</code>, as the property only represents a single value, even if the feature is multi-valued.
     */
    @Override
    public boolean isMany(Object object)
    {
      return false;
    }

    /**
     * Substitutes the wrapper owner for the selected object and invokes the base implementation.
     */
    @Override
    public Collection<?> getChoiceOfValues(Object object)
    {
      return super.getChoiceOfValues(owner);
    }
  }

  /**
   * A <code>ReplacementAffectedObjectCommand</code> wraps another command to return as its affected objects the single
   * wrapper that replaces this wrapper. That is, it obtains the children of the wrapper's owner, and returns a
   * collection containing the first wrapper whose feature and index match this one's.
   */
  protected class ReplacementAffectedObjectCommand extends CommandWrapper
  {
    public ReplacementAffectedObjectCommand(Command command)
    {
      super(command);
    }

    /**
     * Obtains the children of the wrapper's owner, and returns a collection containing the first wrapper whose feature
     * and index match this one's. 
     */
    @Override
    public Collection<?> getAffectedObjects()
    {
      Collection<?> children = Collections.EMPTY_LIST;

      // Either the IEditingDomainItemProvider or ITreeItemContentProvider item provider interface can give us
      // the children.
      //
      Object adapter = adapterFactory.adapt(owner, IEditingDomainItemProvider.class);
      if (adapter instanceof IEditingDomainItemProvider)
      {
        children = ((IEditingDomainItemProvider)adapter).getChildren(owner);
      }
      else
      {
        adapter = adapterFactory.adapt(owner, ITreeItemContentProvider.class);
        if (adapter instanceof ITreeItemContentProvider)
        {
          children = ((ITreeItemContentProvider)adapter).getChildren(owner);
        }
      }

      for (Object child : children)
      {
        if (child instanceof IWrapperItemProvider)
        {
          IWrapperItemProvider wrapper = (IWrapperItemProvider)child;
          if (wrapper.getFeature() == feature && wrapper.getIndex() == index)
          {
            return Collections.singletonList(child);
          }
        }
      }
      return Collections.EMPTY_LIST;
    }
  }
}
