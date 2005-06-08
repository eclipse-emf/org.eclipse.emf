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
 * $Id: RemoveCommand.java,v 1.3.2.1 2005/06/08 18:27:45 nickb Exp $
 */
package org.eclipse.emf.edit.command;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.ListIterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * The remove command logically acts upon an owner object that has a collection-type feature from which objects can be removed.
 * The static create methods delegate command creation to {@link EditingDomain#createCommand EditingDomain.createCommand},
 * which may or may not result in the actual creation of an instance of this class.
 *
 * <p>
 * The implementation of this class is low-level and EMF specific;
 * it allows one or more objects to be removed from a many-valued feature of an owner.
 * i.e., it is equivalent of the call
 * <pre>
 *   ((EList)((EObject)owner).eGet((EStructuralFeature)feature)).removeAll((Collection)collection);
 * </pre>
 *
 * <p>
 * It can also be used as an equivalent to the call
 * <pre>
 *   ((EList)extent).removeAll((Collection)collection);
 * </pre>
 * which is how root objects are removed from the contents of a resource.
 * Like all the low-level comands in this package, the remove command is undoable.
 *
 * <p>
 * A remove command is an {@link OverrideableCommand}.
 */
public class RemoveCommand extends AbstractOverrideableCommand
{
  /**
   * This creates a command to remove an object.
   */
  public static Command create(EditingDomain domain, Object value) 
  {
    return create(domain, Collections.singleton(value));
  }

  /**
   * This creates a command to remove a particular value from the specified feature of the owner.
   */
  public static Command create(EditingDomain domain, Object owner, Object feature, Object value) 
  {
    return create(domain, owner, feature, Collections.singleton(value));
  }

  /**
   * This creates a command to remove multiple objects.
   */
  public static Command create(final EditingDomain domain, final Collection collection) 
  {
    return create(domain, null, null, collection);
  }

  /**
   * This creates a command to remove a collection of values from the specified feature of the owner.
   */
  public static Command create(final EditingDomain domain, final Object owner, final Object feature, final Collection collection)
  {
    return domain.createCommand(RemoveCommand.class, new CommandParameter(owner, feature, collection));
  }

  /**
   * This caches the label.
   */
  protected static final String LABEL = EMFEditPlugin.INSTANCE.getString("_UI_RemoveCommand_label");

  /**
   * This caches the description.
   */
  protected static final String DESCRIPTION = EMFEditPlugin.INSTANCE.getString("_UI_RemoveCommand_description");

  /**
   * This caches the description for a list-based command.
   */
  protected static final String DESCRIPTION_FOR_LIST = EMFEditPlugin.INSTANCE.getString("_UI_RemoveCommand_description_for_list");

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
   * This is the list from which the command will remove.
   */
  protected EList ownerList;

  /**
   * This is the collection of objects being removed.
   */
  protected Collection collection;

  /**
   * These are the indices at which to reinsert the removed objects during an undo so as to achieve the original list order.
   */ 
  protected int[] indices;

  /**
   * The is the value returned by {@link Command#getAffectedObjects}.
   * The affected objects are different after an execute than after an undo, so we record it.
   */
  protected Collection affectedObjects;

  /**
   * This constructs a primitive command to remove a particular value from the specified feature of the owner.
   */
  public RemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value) 
  {
    this(domain, owner, feature, Collections.singleton(value));
  }

  /**
   * This constructs a primitive command to remove a collection of values from the specified feature of the owner.
   */
  public RemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection collection)
  {
    super(domain, LABEL, DESCRIPTION);

    // Initialize all the fields from the command parameter.
    //
    this.owner = owner;
    this.feature = feature;
    this.collection = collection == null ? null : new ArrayList(collection);

    ownerList = getOwnerList(this.owner, feature);
  }

  /**
   * This constructs a primitive command to remove a particular value from the specified extent.
   */
  public RemoveCommand(EditingDomain domain, EList list, Object value) 
  {
    this(domain, list, Collections.singleton(value));
  }

  /**
   * This constructs a primitive command to remove a collection of values from the specified extent.
   */
  public RemoveCommand(EditingDomain domain, EList list, Collection collection)
  {
    super(domain, LABEL, DESCRIPTION_FOR_LIST);

    // Initialize all the fields from the command parameter.
    //
    this.collection = collection == null ? null : new ArrayList(collection);

    ownerList = list;
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
   * This returns the list from which the command will remove.
   */
  public EList getOwnerList()
  {
    return ownerList;
  }

  /**
   * This returns the collection of objects being removed.
   */
  public Collection getCollection()
  {
    return collection;
  }

  /**
   * These returns the indices at which to reinsert the removed objects during an undo so as to achieve the original list order.
   */ 
  public int[] getIndices()
  {
    return indices;
  }

  protected boolean prepare() 
  {
    // This can execute if there is an owner list and a collection and the owner list contains all the objects of the collection.
    //
    boolean result =
      ownerList != null && 
        collection != null && 
        ownerList.containsAll(collection) && 
        (owner == null || !domain.isReadOnly(owner.eResource()));

    return result;
  }

  public void doExecute() 
  {
    // Record the position of each object in the collection.
    //
    indices = new int[collection.size()];
  
    // This records the position index of each item.
    // And, it reorders the collection objects to be in the owner list order!
    // This is very important for undo to interpret the indexes correctly.
    //
  
    // This is the index into the collection and hence index into indices.
    //
    int i = 0;
  
    // We iterate over the owner list itself, not the collection!
    //
    for (ListIterator ownedObjects = ownerList.listIterator(); ownedObjects.hasNext(); )
    {
      Object ownedObject = ownedObjects.next();
  
      // If this owned object is one from the collection...
      //
      if (collection.contains(ownedObject))
      {
        // Remove the object from the collection and then add it back in again.
        // This puts each object at the end in owner list order, achieving the affect we want.
        //
        collection.remove(ownedObject);
        collection.add(ownedObject);
  
        // Record the index.
        //
        indices[i++] =  ownedObjects.previousIndex();
      }
    }
  
    // Simply remove all the object in the collection from the list.
    //
    ownerList.removeAll(collection);
  
    // We'd like the owner selected after this remove completes.
    //
    affectedObjects = owner == null ? Collections.EMPTY_SET : Collections.singleton(owner);
  }

  public void doUndo() 
  {
    // Note that the way they are sorted, the values of index[i++] always increase,
    // so the objects are added from right to left in the list.
    //
    // EATM  TODO
    //
    // We could make this more efficient by grouping the adds when indices increment by one,
    // so that a single grouped notification would result.
    //
    int i = 0;
    for (Iterator objects = collection.iterator(); objects.hasNext(); )
    {
      ownerList.add(indices[i++], objects.next());
    }
  
    // We'd like the collection of things added to be selected after this command completes.
    //
    affectedObjects = collection;
  }
  
  public void doRedo()
  {
    // Simply remove all the object in the collection from the list.
    //
    ownerList.removeAll(collection);
  
    // We'd like the owner selected after this remove completes.
    //
    affectedObjects = owner == null ? (Collection)Collections.EMPTY_SET : Collections.singleton(owner);
  }

  public Collection doGetResult()
  {
    return collection;
  }

  public Collection doGetAffectedObjects()
  {
    return affectedObjects;
  }

  /**
   * This gives an abbreviated name using this object's own class' name, without package qualification,
   * followed by a space separated list of <tt>field:value</tt> pairs.
   */
  public String toString()
  {
    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (owner: " + owner + ")");
    result.append(" (feature: " + feature + ")");
    result.append(" (ownerList: " + ownerList + ")");
    result.append(" (collection: " + collection + ")");
    result.append(" (indices: " + indices + ")");
    result.append(" (affectedObjects: " + affectedObjects + ")");

    return result.toString();
  }
}
