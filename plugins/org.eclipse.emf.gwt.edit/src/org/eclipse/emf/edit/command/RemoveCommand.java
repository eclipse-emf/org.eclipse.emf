/**
 * <copyright> 
 *
 * Copyright (c) 2002-2010 IBM Corporation and others.
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
 * $Id: RemoveCommand.java,v 1.2 2010/04/28 20:38:34 khussey Exp $
 */
package org.eclipse.emf.edit.command;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
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
 * i.e., it is almost equivalent of the call
 * <pre>
 *   ((EList)((EObject)owner).eGet((EStructuralFeature)feature)).removeAll((Collection)collection);
 * </pre>
 *
 * <p>
 * It can also be used as a near-equivalent to the call
 * <pre>
 *   ((EList)extent).removeAll((Collection)collection);
 * </pre>
 * which is how root objects are removed from the contents of a resource.
 *
 * <p>
 * The one difference is that, while <code>EList.removeAll(Collection)</code> removes all values equal to a value in
 * the collection, this command will remove no more than one value per value in the collection. When duplicates are
 * allowed and present in the list, this command will first look for identical (<code>==</code>) values, in order, and
 * failing that, equal values (<code>.equals()</code>).
 *
 * <p>
 * Like all the low-level commands in this package, the remove command is undoable. 
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
  public static Command create(final EditingDomain domain, final Collection<?> collection) 
  {
    return create(domain, null, null, collection);
  }

  /**
   * This creates a command to remove a collection of values from the specified feature of the owner.
   */
  public static Command create(final EditingDomain domain, final Object owner, final Object feature, final Collection<?> collection)
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
  protected EList<Object> ownerList;

  /**
   * This is the collection of objects being removed.
   */
  protected Collection<Object> collection;

  /**
   * These are the indices at which to reinsert the removed objects during an undo so as to achieve the original list order.
   */ 
  protected int[] indices;

  /**
   * The is the value returned by {@link Command#getAffectedObjects}.
   * The affected objects are different after an execute than after an undo, so we record it.
   */
  protected Collection<?> affectedObjects;

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
  public RemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection<?> collection)
  {
    super(domain, LABEL, DESCRIPTION);

    // Initialize all the fields from the command parameter.
    //
    this.owner = owner;
    this.feature = feature;
    this.collection = collection == null ? null : new ArrayList<Object>(collection);

    ownerList = getOwnerList(this.owner, feature);
  }

  /**
   * This constructs a primitive command to remove a particular value from the specified extent.
   */
  public RemoveCommand(EditingDomain domain, EList<?> list, Object value) 
  {
    this(domain, list, Collections.singleton(value));
  }

  /**
   * This constructs a primitive command to remove a collection of values from the specified extent.
   */
  public RemoveCommand(EditingDomain domain, EList<?> list, Collection<?> collection)
  {
    super(domain, LABEL, DESCRIPTION_FOR_LIST);

    // Initialize all the fields from the command parameter.
    //
    this.collection = collection == null ? null : new ArrayList<Object>(collection);

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
   * This returns the list from which the command will remove.
   */
  public EList<Object> getOwnerList()
  {
    return ownerList;
  }

  /**
   * This returns the collection of objects being removed.
   */
  public Collection<?> getCollection()
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

  @Override
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

  @Override
  public void doExecute() 
  {
    // Iterate over the owner list twice, first matching objects from the collection by identity (==), then matching
    // objects by value equality (.equals()). The positions of matched objects in the owner list are recorded, and
    // the objects are stored in the same order. The lists are then merged to form a final, in-order list of objects
    // and corresponding indices in ownerList. This is very important for undo to interpret the indices correctly.
    // Also, this yields exactly one object removed for each object in the collection, with preference given to
    // identity over value equality.
    //
    List<Object> identity = new ArrayList<Object>(collection.size());
    int[] identityIndices = new int[collection.size()];

    int i = 0;

    for (ListIterator<Object> ownedObjects = ownerList.listIterator(); ownedObjects.hasNext(); )
    {
      Object ownedObject = ownedObjects.next();

      // If this owned object is one from the collection...
      //
      if (containsExact(collection, ownedObject))
      {
        // Remove the object from the collection and add it to the identity list.
        //
        removeExact(collection, ownedObject);
        identity.add(ownedObject);

        // Record the index.
        //
        identityIndices[i++] = ownedObjects.previousIndex();
      }
    }

    while (i < identityIndices.length)
    {
      identityIndices[i++] = -1;
    }

    // Second pass: match by value equality.
    //
    List<Object> equality = new ArrayList<Object>(collection.size());
    int[] equalityIndices = new int[collection.size()];
    i = 0;

    for (ListIterator<Object> ownedObjects = ownerList.listIterator(); ownedObjects.hasNext(); )
    {
      Object ownedObject = ownedObjects.next();
      int index = ownedObjects.previousIndex();

      // If this owned object is equal to one from the collection...
      //
      if (collection.contains(ownedObject) && !contains(identityIndices, index))
      {
        // Remove the object from the collection and add it to the equality list. 
        //
        collection.remove(ownedObject);
        equality.add(ownedObject);

        // Record the index.
        //
        equalityIndices[i++] = index;
      }
    }

    // Merge the lists.
    //
    merge(identity, identityIndices, equality, equalityIndices);

    // Remove objects from the owner list by index, starting from the end.
    //
    for (i = indices.length - 1; i >= 0; i--)
    {
      ownerList.remove(indices[i]);
    }

    // Update the containing map, if necessary.
    //
    updateEMap(owner, feature);

    // We'd like the owner selected after this remove completes.
    //
    affectedObjects = owner == null ? Collections.EMPTY_SET : Collections.singleton(owner);
  }

  /**
   * Returns whether the given collection contains the given target object itself (according to ==, not .equals()).
   */
  protected boolean containsExact(Collection<?> collection, Object target)
  {
    for (Object object : collection)
    {
      if (object == target) return true;
    }
    return false;
  }

  /**
   * Returns whether the given int array contains the given target value.
   */
  protected boolean contains(int[] values, int target)
  {
    for (int i = 0, len = values.length; i < len; i++)
    {
      if (values[i] == target) return true;
    }
    return false;
  }

  /**
   * Removes the first occurrence of the given target object, itself, from the collection.
   */
  protected boolean removeExact(Collection<?> collection, Object target)
  {
    for (Iterator<?> i = collection.iterator(); i.hasNext(); )
    {
      if (i.next() == target)
      {
        i.remove();
        return true;
      }
    }
    return false;
  }

  /**
   * Merges two sets of object lists and index arrays, such that both are ordered by increasing indices. The results
   * are stored as the {@link #collection} and {@link #indices}. The two input sets must already be in increasing index
   * order, with the corresponding object-index pairs in the same positions.
   */
  protected void merge(List<Object> objects1, int[] indices1, List<Object> objects2, int[] indices2)
  {
    // If either list is empty, the result is simply the other.
    //
    if (objects2.isEmpty())
    {
      collection = objects1;
      indices = indices1;
      return;
    }

    if (objects1.isEmpty())
    {
      collection = objects2;
      indices = indices2;
      return;
    }

    // Allocate list and array for objects and indices.
    //
    int size = objects1.size() + objects2.size();
    collection = new ArrayList<Object>(size);
    indices = new int[size];

    // Index counters into indices1, indices2, and indices. 
    //
    int i1 = 0;
    int i2 = 0;
    int i = 0;

    // Object iterators.
    //
    Iterator<Object> iter1 = objects1.iterator();
    Iterator<Object> iter2 = objects2.iterator();

    Object o1 = iter1.hasNext() ? iter1.next() : null;
    Object o2 = iter2.hasNext() ? iter2.next() : null;

    // Repeatedly select the lower index and corresponding object, and advance past the selected pair.
    //
    while (o1 != null && o2 != null)
    {
      if (indices1[i1] < indices2[i2])
      {
        indices[i++] = indices1[i1++];
        collection.add(o1);
        o1 = iter1.hasNext() ? iter1.next() : null;
      }
      else
      {
        indices[i++] = indices2[i2++];
        collection.add(o2);
        o2 = iter2.hasNext() ? iter2.next() : null;
      }
    }

    // Add any remaining object-index pairs from either set.
    //
    while (o1 != null)
    {
      indices[i++] = indices1[i1++];
      collection.add(o1);
      o1 = iter1.hasNext() ? iter1.next() : null;
    }

    while (o2 != null)
    {
      indices[i++] = indices2[i2++];
      collection.add(o2);
      o2 = iter2.hasNext() ? iter2.next() : null;
    }
  }

  @Override
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
    for (Object object : collection)
    {
      ownerList.add(indices[i++], object);
    }
  
    // Update the containing map, if necessary.
    //
    updateEMap(owner, feature);

    // We'd like the collection of things added to be selected after this command completes.
    //
    affectedObjects = collection;
  }
  
  @Override
  public void doRedo()
  {
    // Remove objects from the owner list by index, starting from the end.
    //
    for (int i = indices.length - 1; i >= 0; i--)
    {
      ownerList.remove(indices[i]);
    }

    // Update the containing map, if necessary.
    //
    updateEMap(owner, feature);

    // We'd like the owner selected after this remove completes.
    //
    affectedObjects = owner == null ? Collections.EMPTY_SET : Collections.singleton(owner);
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
    result.append(" (collection: " + collection + ")");
    result.append(" (indices: " + Arrays.toString(indices) + ")");
    result.append(" (affectedObjects: " + affectedObjects + ")");

    return result.toString();
  }
}
