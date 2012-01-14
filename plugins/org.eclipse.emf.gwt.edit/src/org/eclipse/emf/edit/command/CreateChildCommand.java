/**
 * Copyright (c) 2002-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.command;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * This command wraps an {@link AddCommand} or {@link SetCommand} to
 * provide the higher-level operation of "creating" an appropriate child
 * object and adding it to a owner object.  In fact, all of the possible
 * child objects are created by the owner's item provider before this
 * command is created, and specified in the <code>newChildDescriptor</code>
 * argument to {@link #create create()} -- they must be, so that the user
 * can specify which he actually wishes to create.  As a result, this
 * command essentially just creates and executes the appropriate lower-level
 * EMF command, and delegates matters of appearance (text, icon, result) to
 * the appropriate item provider, so that it may be handled correctly for
 * the given model.
 *
 * <p>Note that this command makes no assumptions about the relationship
 * between the <code>owner</code>, to which the new child will be added, and
 * the selected object.  This allows the command to be reused for sibling
 * creation.  As a result, the <code>selection</code> be explicitly
 * specified, so that it can be restored when the command is undone.
 */
public class CreateChildCommand extends CommandWrapper
  implements CommandActionDelegate
{
  /**
   * This returns a command created by the editing domain to add the
   * child described by <code>newChildDescriptor</code> to the given
   * <code>object</code>.
   */
  public static Command create(EditingDomain domain, Object owner,
                               Object newChildDescriptor,
                               Collection<?> selection)
  {
    return 
      domain.createCommand
        (CreateChildCommand.class, 
         new CommandParameter(owner, null, newChildDescriptor, new ArrayList<Object>(selection)));
  }

  /**
   * This value is used to indicate that an optional positional index
   * indicator is unspecified.
   * @deprecated As of EMF 2.0, use {@link CommandParameter#NO_INDEX}, whose
   * value is equal to this, instead.
   */
  @Deprecated
  protected static final int NO_INDEX = CommandParameter.NO_INDEX;

  /**
   * This is the editing domain in which this command operates.
   */
  protected EditingDomain domain;

  /**
   * This is the object to which the child will be added.
   */
  protected EObject owner;

  /**
   * This is the feature of the owner to which the child will be added.
   */
  protected EStructuralFeature feature;

  /**
   * This is the child object to be added.
   */
  protected Object child;

  /**
   * This is the index for the new object's position under the feature.
   */
  protected int index;

  /**
   * This is the helper object, usually the item provider for {@link #owner},
   * to which parts of this command's functionality are delegated.
   */
  protected CreateChildCommand.Helper helper;

  /**
   * This is the value to be returned by {@link #getAffectedObjects}. 
   * The affected objects are different after an execute or redo from after
   * an undo, so we record them.
   */
  protected Collection<?> affectedObjects;

  /**
   * This is the collection of objects that were selected when this command
   * was created.  After an undo, these are considered the affected objects.
   */
  protected Collection<?> selection;

  /**
   * This constructor initializes an instance that adds the specified
   * <code>child</code> object to the <code>feature</code> of the
   * <code>owner</code> object.  If any of <code>owner</code>,
   * <code>feature</code>, or <code>child</code> are <code>null</code>,
   * {@link #createCommand} will return {@link UnexecutableCommand#INSTANCE}
   * and, hence, {@link
   * org.eclipse.emf.common.command.AbstractCommand#canExecute} will return
   * <code>false</code>.  If non-null, <code>selection</code> is the
   * collection of selected objects.  An internal default {@link Helper
   * Helper} will provide generic implmentations for the delegated command
   * methods.
   */
  public CreateChildCommand(EditingDomain domain,
                            EObject owner,
                            EStructuralFeature feature,
                            Object child,
                            Collection<?> selection)
  {
    this(domain, owner, feature, child, CommandParameter.NO_INDEX, selection, null);
  }

  /**
   * This constructor initializes an instance, as above, but the command
   * delegates functionality to the specified {@link Helper Helper}.
   * If <code>helper</code> is <code>null</code>, the internal default
   * helper is used.
   */
  public CreateChildCommand(EditingDomain domain,
                            EObject owner,
                            EStructuralFeature feature,
                            Object child,
                            Collection<?> selection,
                            CreateChildCommand.Helper helper)
  {
    this(domain, owner, feature, child, CommandParameter.NO_INDEX, selection, helper);
  }

  /**
   * This constructor initializes an instance that adds the specified
   * <code>child</code> object to the <code>owner</code> object, at the
   * specified <code>index</code> of its <code>feature</code> feature, if it
   * is multi-valued.  If any of <code>owner</code>, <code>feature</code>,
   * or <code>child</code> are <code>null</code>, {@link #createCommand}
   * will return {@link UnexecutableCommand#INSTANCE} and, hence, {@link
   * org.eclipse.emf.common.command.AbstractCommand#canExecute} will return
   * <code>false</code>.  If non-null, <code>selection</code> is the
   * collection of selected objects.  The internal default helper is used by
   * the command.  If <code>index</code> is {@link CommandParameter#NO_INDEX},
   * this behaves just like the first constructor form. 
   */
  public CreateChildCommand(EditingDomain domain,
                            EObject owner,
                            EStructuralFeature feature,
                            Object child,
                            int index,
                            Collection<?> selection)
  {
    this(domain, owner, feature, child, index, selection, null);
  }

  /**
   * This constructor initializes an instance, as above, but the command
   * delegates functionality to the specified {@link Helper Helper}.
   * If <code>helper</code> is <code>null</code>, the internal default
   * helper is used.
   */
  public CreateChildCommand(EditingDomain domain,
                            EObject owner,
                            EStructuralFeature feature,
                            Object child,
                            int index,
                            Collection<?> selection,
                            CreateChildCommand.Helper helper)
  {
    super();
    this.domain = domain;
    this.owner = owner;
    this.feature = feature;
    this.child = child;
    this.index = index;
    this.selection = selection == null ? Collections.EMPTY_LIST : selection;
    this.helper = helper == null ? defaultHelper : helper;

    // If we're creating a child under an object in a feature map, the selection will be the feature map entry.
    // We want to replace it with the model object.
    //
    if (this.selection.size() == 1)
    {
      Object selObject = this.selection.iterator().next();
      if (selObject instanceof FeatureMap.Entry && ((FeatureMap.Entry)selObject).getValue() == owner)
      {
        this.selection = Collections.singletonList(owner);
      }
    }

    String text = this.helper.getCreateChildText(owner, feature, child, selection);
    setLabel(EMFEditPlugin.INSTANCE.getString(
      "_UI_CreateChildCommand_label", new Object[] { text }));
    setDescription(EMFEditPlugin.INSTANCE.getString(
      "_UI_CreateChildCommand_description"));
  }

  /**
   * This creates the wrapped {@link AddCommand} or {@link SetCommand} that
   * adds the child object to the model.  If the owner, feature, or child
   * is <code>null</code>, or if the feature is single-valued and has
   * already been set to an object, {@link UnexecutableCommand#INSTANCE}
   * will be returned.
   */
  @Override
  protected Command createCommand()
  {
    if (owner == null || feature == null || child == null)
    {
      return UnexecutableCommand.INSTANCE;
    }

    if (feature.isMany())
    {
      return AddCommand.create(domain, owner, feature, child, index);
    }
    else if (owner.eGet(feature) == null)
    {
      return SetCommand.create(domain, owner, feature, child);
    }
    else
    {
      return UnexecutableCommand.INSTANCE;
    }
  }

  /**
   * This executes the wrapped command and sets the affected objects to the
   * collection returned by <code>helper.getCreateChildResult()</code>.
   */
  @Override
  public void execute()
  {
    super.execute();
    affectedObjects = helper.getCreateChildResult(child);
  }

  /**
   * This undoes the wrapped command and sets the affected objects to
   * the original selection.
   */
  @Override
  public void undo()
  {
    super.undo();
    affectedObjects = selection;
  }

  /**
   * This redoes the wrapped command and sets the affected objects to the
   * collection returned by <code>helper.getCreateChildResult()</code>.
   */
  @Override
  public void redo()
  {
    super.redo();
    affectedObjects = helper.getCreateChildResult(child);
  }

  /**
   * This returns the affected objects.
   */
  @Override
  public Collection<?> getAffectedObjects()
  {
    return affectedObjects == null ?
      Collections.EMPTY_LIST : affectedObjects;
  }

  /**
   * This returns the result of this command by delegating to
   * <code>helper.getCreateChildResult()</code>.
   */
  @Override
  public Collection<?> getResult()
  {
    Collection<?> result = helper.getCreateChildResult(child);
    return result == null ? Collections.EMPTY_LIST : result;
  }

  /**
   * This returns the label by delegating to
   * <code>helper.getCreateChildText()</code>.
   */
  public String getText()
  {
    return helper.getCreateChildText(owner, feature, child, selection);
  }

  /**
   * This returns the description by delegating to
   * <code>helper.getCreateChildDescription()</code>.
   */
  @Override
  public String getDescription()
  {
    return helper.getCreateChildDescription(owner, feature, child, selection);
  }

  /**
   * This returns the tool tip text by delegating to
   * <code>helper.getCreateChildToolTipText()</code>.
   */
  public String getToolTipText()
  {
    return helper.getCreateChildToolTipText(owner, feature, child, selection);
  }

  /**
   * This returns the icon by delegating to
   * <code>helper.getCreateChildImage()</code>.
   */
  public Object getImage()
  {
    return helper.getCreateChildImage(owner, feature, child, selection);
  }

  /**
   * This is the helper interface to which <code>CreateChildCommand</code>
   * functionality is delegated.
   */
  public static interface Helper
  {
    /**
     * For a given child object, this returns the complete collection of
     * objects to be presented as the command's result.
     */
    public Collection<?> getCreateChildResult(Object child);
    
    /**
     * This returns the text for the action of creating the specified
     * <code>child</code> under the specified <code>feature</code> of the
     * <code>owner</code>.  The <code>selection</code> is given as context,
     * from which the <code>Helper</code> can determine whether the object
     * is being added as a child or a sibling, if it wishes.
     */
    public String getCreateChildText(Object owner, Object feature,
                                     Object child, Collection<?> selection);

    /**
     * This returns the description of the action of creating the specified
     * <code>child</code> under the specified <code>feature</code> of the
     * <code>owner</code>.  The <code>selection</code> is given as context,
     * from which the <code>Helper</code> can determine whether the object
     * is being added as a child or a sibling, if it wishes.
     */
    public String getCreateChildDescription(Object owner,
                                            Object feature,
                                            Object child,
                                            Collection<?> selection);

    /**
     * This returns the tool tip text for the action of creating the
     * specified <code>child</code> under the specified <code>feature</code>
     * of the <code>owner</code>.  The <code>selection</code> is given as
     * context, from which the <code>Helper</code> can determine whether the
     * object is being added as a child or a sibling, if it wishes.
     */
    public String getCreateChildToolTipText(Object owner,
                                            Object feature,
                                            Object child,
                                            Collection<?> selection);

    /**
     * This returns the icon for the action of creating the specified
     * <code>child</code> under the specified <code>feature</code> of the
     * <code>owner</code>.  The <code>selection</code> is given as context,
     * from which the <code>Helper</code> can determine whether the object
     * is being added as a child or a sibling, if it wishes.
     */
    public Object getCreateChildImage(Object owner, Object feature,
                                      Object child, Collection<?> selection);
  }

  /**
   * This is the default helper.  It returns a singleton list of the child
   * itself as a result; default text, description and tool-tip text; and
   * no image.
   */
  private static final Helper defaultHelper = new Helper()
  {
    public Collection<?> getCreateChildResult(Object child)
    {
      return Collections.singletonList(child);
    }

    public String getCreateChildText(Object owner, Object feature,
                                     Object child, Collection<?> selection)
    {
      return EMFEditPlugin.INSTANCE.getString(
        "_UI_CreateChild_text",
        new Object[] {
          EMFEditPlugin.INSTANCE.getString("_UI_Unknown_type"),
          EMFEditPlugin.INSTANCE.getString("_UI_Unknown_feature"),
          EMFEditPlugin.INSTANCE.getString("_UI_Unknown_type")
        });
    }
    
    public String getCreateChildDescription(Object owner, Object feature,
                                            Object child, Collection<?> selection)
    {
      return EMFEditPlugin.INSTANCE.getString(
        "_UI_CreateChildCommand_description");
    }

    public String getCreateChildToolTipText(Object owner, Object feature,
                                            Object child, Collection<?> selection)
    {
      return EMFEditPlugin.INSTANCE.getString(
        "_UI_CreateChild_tooltip",
        new Object[] {
          EMFEditPlugin.INSTANCE.getString("_UI_Unknown_type"),
          EMFEditPlugin.INSTANCE.getString("_UI_Unknown_feature"),
          EMFEditPlugin.INSTANCE.getString("_UI_Unknown_type")
        });      
    }

    public Object getCreateChildImage(Object owner, Object feature,
                                      Object child, Collection<?> selection)
    {
      return null;
    }
  };

  /**
   * This gives an abbreviated name using this object's own class name,
   * without package qualification, followed by a space-separated list of
   * <code>field:value</code> pairs.
   */
  @Override
  public String toString()
  {
    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (domain: " + domain + ")");
    result.append(" (owner: " + owner + ")");
    result.append(" (feature: " + feature + ")");
    result.append(" (child: " + child + ")");
    result.append(" (index: " + index + ")");
    result.append(" (helper: " + helper + ")");
    result.append(" (affectedObjects: " + affectedObjects + ")");
    result.append(" (selection: " + selection + ")");
    return result.toString();
  }
}
