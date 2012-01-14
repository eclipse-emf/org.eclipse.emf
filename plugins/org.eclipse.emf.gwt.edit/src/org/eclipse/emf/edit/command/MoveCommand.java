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


import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * The move command logically acts upon an owner object that has a collection-based feature 
 * containing an object that is to be moved to a new position within the collection.
 * The static create method delegates command creation to {@link EditingDomain#createCommand EditingDomain.createCommand},
 * which may or may not result in the actual creation of an instance of this class.
 * Like all the low level commands in this package, the move command is undoable.
 *
 * <p>
 * The implementation of this class is low-level and EMF specific;
 * it allows an object to be moved to a new position within a many-valued feature of an owner,
 * i.e., it is equivalent of the call
 * <pre>
 *   ((EList)((EObject)owner).eGet((EStructuralFeature)feature)).move(index, object);
 * </pre>
 *
 * <p>
 * It can also be used as an equivalent to the call
 * <pre>
 *   ((EList)extent).move(index, object);
 * </pre>
 * which is how root objects are moved within the contents of a resource.
 * Like all the low-level commands in this package, the move command is undoable.
 *
 * <p>
 * A move command is an {@link OverrideableCommand}.
 */
public class MoveCommand extends AbstractOverrideableCommand
{
  /**
   * This creates a command to move particular value to a particular index in the specified feature of the owner.
   * The feature will often be null because the domain will deduce it.
   */
  public static Command create(EditingDomain domain, Object owner, Object feature, Object value, int index)
  {
    return domain.createCommand(MoveCommand.class, new CommandParameter(owner, feature, value, index));
  }


  /**
   * This caches the label.
   */
  protected static final String LABEL = EMFEditPlugin.INSTANCE.getString("_UI_MoveCommand_label");

  /**
   * This caches the description.
   */
  protected static final String DESCRIPTION = EMFEditPlugin.INSTANCE.getString("_UI_MoveCommand_description");

  /**
   * This caches the description for a list-based command.
   */
  protected static final String DESCRIPTION_FOR_LIST = EMFEditPlugin.INSTANCE.getString("_UI_MoveCommand_description_for_list");

  /**
   * This is the owner object upon which the command will act.
   * It could be null in the case that we are dealing with an {@link org.eclipse.emf.common.util.EList}.
   */
  protected EObject owner;

  /**
   * This is the feature of the owner object upon the command will act.
   * It could be null, in the case that we are dealing with an {@link org.eclipse.emf.common.util.EList}.
   */
  protected EStructuralFeature feature;

  /**
   * This is the list in which the command will move an object.
   */
  protected EList<Object> ownerList;

  /**
   * This is the value being moved within the owner list.
   */
  protected Object value;

  /**
   * This is the position to which the object will be moved.
   */
  protected int index;

  /**
   * This is the original position to which the object will be moved upon undo.
   */
  protected int oldIndex;

  /**
   * This constructs a primitive command to move a particular value to a particular index 
   * of the specified many-valued feature of the owner.
   */
  public MoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value, int index)
  {
    super(domain, LABEL, DESCRIPTION);

    this.owner = owner;
    this.feature = feature;
    this.value = value;
    this.index = index;
 
    ownerList = getOwnerList(this.owner, feature);
    oldIndex = -1;
  }

  /**
   * This constructs a primitive command to move a value at a particular index to another particular index 
   * of the specified many-valued feature of the owner.
   * @since 2.7
   */
  public MoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, int sourceIndex, int targetIndex)
  {
    super(domain, LABEL, DESCRIPTION);

    this.owner = owner;
    this.feature = feature;

    index = targetIndex;
    oldIndex = sourceIndex;

    ownerList = getOwnerList(this.owner, feature);
    if (ownerList != null && ownerList.size() > sourceIndex && sourceIndex >= 0)
    {
      value = ownerList.get(sourceIndex);
    }
  }

  /**
   * This constructs a primitive command to move a particular value to a particular index of the specified extent.
   */
  public MoveCommand(EditingDomain domain, EList<?> list, Object value, int index)
  {
    super(domain, LABEL, DESCRIPTION_FOR_LIST);

    this.value = value;
    this.index = index;

    oldIndex = -1;

    @SuppressWarnings("unchecked")
    EList<Object> untypedList = (EList<Object>)list;
    ownerList = untypedList;
  }

  /**
   * This constructs a primitive command to move a value at a particular index to another particular index of the specified extent.
   * @since 2.7
   */
  public MoveCommand(EditingDomain domain, EList<?> list, int sourceIndex, int targetIndex)
  {
    super(domain, LABEL, DESCRIPTION_FOR_LIST);

    if (list != null && list.size() > sourceIndex && sourceIndex >= 0)
    {
      value = list.get(sourceIndex);
    }
    index = targetIndex;
    oldIndex = sourceIndex;

    @SuppressWarnings("unchecked")
    EList<Object> untypedList = (EList<Object>)list;
    ownerList = untypedList;
  }

  /**
   * This returns the owner object upon which the command will act.
   * It could be null in the case that we are dealing with an {@link org.eclipse.emf.common.util.EList}.
   */
  public EObject getOwner()
  {
    return owner;
  }

  /**
   * This returns the feature of the owner object upon the command will act.
   * It could be null, in the case that we are dealing with an {@link org.eclipse.emf.common.util.EList}.
   */
  public EStructuralFeature getFeature()
  {
    return feature;
  }

  /**
   * This returns the list in which the command will move an object.
   */
  public EList<Object> getOwnerList()
  {
    return ownerList;
  }

  /**
   * This returns the value being moved.
   */
  public Object getValue()
  {
    return value;
  }

  /**
   * This returns the position to which the value will be moved.
   */
  public int getIndex()
  {
    return index;
  }

  /**
   * This returns the original position to which the object will be moved upon undo.
   */
  public int getOldIndex()
  {
    return oldIndex;
  }

  @Override
  protected boolean prepare()
  {
    // Return whether there is a list and the indices are within range.
    //
    boolean result =
      ownerList != null  && 
         index >= 0 && 
         index < ownerList.size() &&
         (oldIndex == -1 ? ownerList.contains(value) : oldIndex >= 0 && oldIndex < ownerList.size()) && 
         (owner == null || !domain.isReadOnly(owner.eResource()));

    return result;
  }

  @Override
  public void doExecute() 
  {
    if (oldIndex == -1)
    {
      oldIndex = ownerList.indexOf(value);
    }
    ownerList.move(index, oldIndex);
  }

  @Override
  public void doUndo() 
  {
    ownerList.move(oldIndex, index);
  }

  @Override
  public void doRedo()
  {
    ownerList.move(index, oldIndex);
  }

  @Override
  public Collection<?> doGetResult()
  {
    return Collections.singleton(value);
  }

  @Override
  public Collection<?> doGetAffectedObjects()
  {
    return Collections.singleton(value);
  }

  /**
   * This gives an abbreviated name using this object's own class' name, without package qualification,
   * followed by a space separated list of <tt>field:value</tt> pairs.
   */
  @Override
  public String toString()
  {
    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (owner: " + owner + ")");
    result.append(" (feature: " + feature + ")");
    result.append(" (ownerList: " + ownerList + ")");
    result.append(" (value: " + value + ")");
    result.append(" (index: " + index + ")");
    result.append(" (oldIndex: " + oldIndex + ")");

    return result.toString();
  }
}
