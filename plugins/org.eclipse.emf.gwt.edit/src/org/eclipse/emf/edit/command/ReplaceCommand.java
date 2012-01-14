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
 * The replace command logically acts upon an owner object that has a collection-type feature 
 * in which an object can be replaced by a collection of other objects.
 * The static create methods delegate command creation to {@link EditingDomain#createCommand EditingDomain.createCommand},
 * which may or may not result in the actual creation of an instance of this class.
 *
 * <p>
 * The implementation of this class is low-level and EMF specific;
 * it allows an object from a many-valued feature of an owner to be replaced by a collection of other objects.
 * i.e., it is equivalent of the call
 * <pre>
 *   int index = ((EList)((EObject)owner).eGet((EStructuralFeature)feature)).indexOf(value);
 *   ((EList)((EObject)owner).eGet((EStructuralFeature)feature)).remove(value);
 *   ((EList)((EObject)owner).eGet((EStructuralFeature)feature)).addAll(index, (Collection)collection);
 * </pre>
 *
 * <p>
 * It can also be used as an equivalent to the call
 * <pre>
 *   int index = ((EList)extent).indexOf(value);
 *   ((EList)extent).remove(value);
 *   ((EList)extent).addAll(index, (Collection)collection);
 * </pre>
 * which is how root objects are replaced in the contents of a resource.
 * Like all the low level commands in this package, the replace command is undoable.
 *
 * <p>
 * A replace command is an {@link OverrideableCommand}.
 */
public class ReplaceCommand extends AbstractOverrideableCommand
{
  /**
   * This creates a command to replace an object with a collection of replacements.
   */
  public static Command create(EditingDomain domain, Object value, Collection<?> collection) 
  {
    return create(domain, null, null, value, collection);
  }

  /**
   * This creates a command to replace a particular value in the specified feature of the owner with a collection replacements objects.
   */
  public static Command create(EditingDomain domain, Object owner, Object feature, Object value, Collection<?> collection) 
  {
    return domain.createCommand(ReplaceCommand.class, new CommandParameter(owner, feature, value, collection));
  }

  /**
   * This caches the label.
   */
  protected static final String LABEL = EMFEditPlugin.INSTANCE.getString("_UI_ReplaceCommand_label");

  /**
   * This caches the description.
   */
  protected static final String DESCRIPTION = EMFEditPlugin.INSTANCE.getString("_UI_ReplaceCommand_description");

  /**
   * This is the owner object upon which the command will act.
   * It could be null, in the case that we are dealing with an {@link org.eclipse.emf.common.util.EList}.
   */
  protected EObject owner;

  /**
   * This is the feature of the owner object upon the command will act.
   * It could be null, in the case that we are dealing with an {@link org.eclipse.emf.common.util.EList}.
   */
  protected EStructuralFeature feature;

  /**
   * This is the list from which the command will replace.
   */
  protected EList<Object> ownerList;

  /**
   * This is value that is being replaced.
   */
  protected Object value;

  /**
   * This is the collection of replacements.
   */
  protected Collection<?> collection;

  /**
   * This is the index at which to reinsert the replaced object during an undo so as to achieve the original list order.
   */ 
  protected int index;

  /**
   * The is the value returned by {@link Command#getAffectedObjects}.
   * The affected objects are different after an execute than after an undo, so we record it.
   */
  protected Collection<?> affectedObjects;

  /**
   * This constructs a primitive command to replace a particular value in the specified feature of the owner 
   * with the specified replacement.
   */
  public ReplaceCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value, Object replacement) 
  {
    this(domain, owner, feature, value, Collections.singleton(replacement));
  }

  /**
   * This constructs a primitive command to replace a particular value in the specified feature of the owner
   * with the specified collection of replacements.
   */
  public ReplaceCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value, Collection<?> collection)
  {
    super(domain, LABEL, DESCRIPTION);

    // Initialize all the fields from the command parameter.
    //
    this.owner = owner;
    this.feature = feature;
    this.value = value;
    this.collection = collection;

    ownerList = getOwnerList(this.owner, feature);
  }

  /**
   * This constructs a primitive command to replace a particular value in the specified extent with the given replacement.
   */
  public ReplaceCommand(EditingDomain domain, EList<?> list, Object value, Object replacement) 
  {
    this(domain, list, value, Collections.singleton(replacement));
  }

  /**
   * This constructs a primitive command to replace a particular value in the specified extent with the given collection of replacements.
   */
  public ReplaceCommand(EditingDomain domain, EList<?> list, Object value, Collection<?> collection)
  {
    super(domain, LABEL, DESCRIPTION);

    // Initialize all the fields from the command parameter.
    //
    this.value = value;
    this.collection = collection;

    @SuppressWarnings("unchecked")
    EList<Object> untypedList = (EList<Object>)list;
    ownerList = untypedList;
  }

  /**
   * This returns the owner object upon which the command will act.
   * It could be null, in the case that we are dealing with an {@link org.eclipse.emf.common.util.EList}.
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
   * This returns the list in which the command will replace.
   */
  public EList<Object> getOwnerList()
  {
    return ownerList;
  }

  /**
   * This returns the value being replaced.
   */
  public Object getValue()
  {
    return value;
  }

  /**
   * This returns the collection of replacement objects.
   */
  public Collection<?> getCollection()
  {
    return collection;
  }

  /**
   * This returns the index at which to reinsert the replace objects during an undo so as to achieve the original list order.
   */ 
  public int getIndex()
  {
    return index;
  }

  @Override
  protected boolean prepare() 
  {
    // This can't execute if there is no owner list 
    // or the owner list doesn't contain the value being replaced or 
    // there are not replacements.
    //
    if (ownerList == null || !ownerList.contains(value) || collection == null || collection.isEmpty())
    {
      return false;
    }
    else if (owner != null && domain.isReadOnly(owner.eResource()))
    {
      return false;
    }
    else if (feature == null)
    {
      // An extent allows anything to be added.
      //
      return true;
    }
    else
    {
      // Make sure each object conforms to the type of the feature.
      //
      for (Object replacement : collection)
      {
        if (!feature.getEType().isInstance(replacement))
        {
          return false;
        }
      }

      return true;
    }
  }

  @Override
  public void doExecute() 
  {
    // Record the position of the value in the owner list.
    //
    index = ownerList.indexOf(value);
  
    // Simply remove the object from the owner list.
    //
    ownerList.remove(value);

    // Insert the collection at the right place.
    //
    ownerList.addAll(index, collection);
  
    // Update the containing map, if necessary.
    //
    updateEMap(owner, feature);

    // We'd like the collection of replacements selected after this replace completes.
    //
    affectedObjects = collection;
  }

  @Override
  public void doUndo() 
  {
    // Remove the collection of replacements.
    //
    ownerList.removeAll(collection);

    // Add the value back in the right place.
    //
    ownerList.add(index, value);

    // Update the containing map, if necessary.
    //
    updateEMap(owner, feature);

    // We'd like the replaced selected after this undo replace completes.
    //
    affectedObjects = Collections.singleton(value);
  }
  
  @Override
  public void doRedo()
  {
    // Simply remove the object from the owner list.
    //
    ownerList.remove(value);

    // Insert the collection at the right place.
    //
    ownerList.addAll(index, collection);
  
    // Update the containing map, if necessary.
    //
    updateEMap(owner, feature);

    // We'd like the collection of replacements selected after this replace completes.
    //
    affectedObjects = collection;
  }

  @Override
  public Collection<?> doGetResult()
  {
    return collection;
  }

  @Override
  public Collection<?> doGetAffectedObjects()
  {
    return affectedObjects;
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
    result.append(" (collection: " + collection + ")");
    result.append(" (index: " + index + ")");
    result.append(" (affectedObjects:" + affectedObjects + ")");

    return result.toString();
  }
}
