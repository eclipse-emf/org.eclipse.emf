/**
 * <copyright>
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: DelegatingEList.java,v 1.11 2007/11/03 13:15:35 emerks Exp $
 */
package org.eclipse.emf.common.util;


import java.io.Serializable;
import java.util.AbstractList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;


/**
 * A highly extensible delegating list implementation.
 */
public abstract class DelegatingEList<E> extends AbstractList<E> implements EList<E>, Cloneable, Serializable 
{
  private static final long serialVersionUID = 1L;

  /**
   * Creates an empty instance.
   */
  public DelegatingEList() 
  {
    super();
  }

  /**
   * Creates an instance that is a copy of the collection.
   * @param collection the initial contents of the list.
   */
  public DelegatingEList(Collection<? extends E> collection) 
  {
    addAll(collection);
  }

  /**
   * Returns whether <code>equals</code> rather than <code>==</code> should be used to compare members.
   * The default is to return <code>true</code> but clients can optimize performance by returning <code>false</code>.
   * The performance difference is highly significant.
   * @return whether <code>equals</code> rather than <code>==</code> should be used.
   */
  protected boolean useEquals()
  {
    return true;
  }

  /**
   * Returns whether two objects are equal using the {@link #useEquals appropriate} comparison mechanism.
   * @return whether two objects are equal.
   */
  protected boolean equalObjects(Object firstObject, Object secondObject)
  {
    return
      useEquals() && firstObject != null ?
        firstObject.equals(secondObject) :
        firstObject == secondObject;
  }

  /**
   * Returns whether <code>null</code> is a valid object for the list.
   * The default is to return <code>true</code>, but clients can override this to exclude <code>null</code>.
   * @return whether <code>null</code> is a valid object for the list.
   */
  protected boolean canContainNull()
  {
    return true;
  }

  /**
   * Returns whether objects are constrained to appear at most once in the list.
   * The default is to return <code>false</code>, but clients can override this to ensure uniqueness of contents.
   * The performance impact is significant: operations such as <code>add</code> are O(n) as a result requiring uniqueness.
   * @return whether objects are constrained to appear at most once in the list.
   */
  protected boolean isUnique()
  {
    return false;
  }

  /**
   * Validates a new content object and returns the validated object.
   * This implementation checks for null, if {@link #canContainNull necessary} and returns the argument object.
   * Clients may throw additional types of runtime exceptions 
   * in order to handle constraint violations.
   * @param index the position of the new content.
   * @param object the new content.
   * @return the validated content.
   * @exception IllegalArgumentException if a constraint prevents the object from being added.
   */
  protected E validate(int index, E object)
  {
    if (!canContainNull() && object == null)
    {
      throw new IllegalArgumentException("The 'no null' constraint is violated");
    }

    return object;
  }

  /**
   * Resolves the object at the index and returns the result.
   * This implementation simply returns the <code>object</code>;
   * clients can use this to transform objects as they are fetched.
   * @param index the position of the content.
   * @param object the content.
   * @return the resolved object.
   */
  protected E resolve(int index, E object)
  {
    return object;
  }

  /**
   * Called to indicate that the backing store list has been set.
   * This implementation does nothing; 
   * clients can use this to monitor settings to the backing store list.
   * @param index the position that was set.
   * @param newObject the new object at the position.
   * @param oldObject the old object at the position.
   */
  protected void didSet(int index, E newObject, E oldObject)
  {
    // Do nothing.
  }

  /**
   * Called to indicate that an object has been added to the backing store list.
   * This implementation does nothing; 
   * clients can use this to monitor additions to the backing store list.
   * @param index the position object the new object.
   * @param newObject the new object at the position.
   */
  protected void didAdd(int index, E newObject)
  {
    // Do nothing.
  }

  /**
   * Called to indicate that an object has been removed from the backing store list.
   * This implementation does nothing; 
   * clients can use this to monitor removals from the backing store list.
   * @param index the position of the old object.
   * @param oldObject the old object at the position.
   */
  protected void didRemove(int index, E oldObject)
  {
    // Do nothing.
  }

  /**
   * Called to indicate that the backing store list has been cleared.
   * This implementation calls {@link #didRemove didRemove} for each object;
   * clients can use this to monitor clearing  of the backing store list.
   * @param size the original size of the list.
   * @param oldObjects the old backing store list being discarded.
   * @see #didRemove
   */
  protected void didClear(int size, Object [] oldObjects)
  {
    if (oldObjects != null)
    {
      for (int i = 0; i < size; ++i)
      {
        @SuppressWarnings("unchecked") E object = (E)oldObjects[i];
        didRemove(i, object);
      }
    }
  }

  /**
   * Called to indicate that an object has been moved in the backing store list.
   * This implementation does nothing; 
   * clients can use this to monitor movement in the backing store list.
   * @param index the position of the moved object.
   * @param movedObject the moved object at the position.
   * @param oldIndex the position the object was at before the move.
   */
  protected void didMove(int index, E movedObject, int oldIndex)
  {
    // Do nothing.
  }

  /**
   * Called to indicate that the backing store list has been changed.
   * This implementation does nothing; 
   * clients can use this to monitor change in the backing store list.
   */
  protected void didChange()
  {
    // Do nothing.
  }

  /**
   * Returns the list that acts as the backing store.
   * @return the list that acts as the backing store.
   */
  protected abstract List<E> delegateList();

  /**
   * Returns the number of objects in the list.
   * @return the number of objects in the list.
   */
  @Override
  public int size() 
  {
    return delegateSize();
  }

  /**
   * Returns the number of objects in the backing store list.
   * @return the number of objects in the backing store list.
   */
  protected int delegateSize()
  {
    return delegateList().size();
  }

  /**
   * Returns whether the list has zero size.
   * @return whether the list has zero size.
   */
  @Override
  public boolean isEmpty() 
  {
    return delegateIsEmpty();
  }

  /**
   * Returns whether the backing store list has zero size.
   * @return whether the backing store list has zero size.
   */
  protected boolean delegateIsEmpty() 
  {
    return delegateList().isEmpty();
  }

  /**
   * Returns whether the list contains the object.
   * @param object the object in question.
   * @return whether the list contains the object.
   */
  @Override
  public boolean contains(Object object) 
  {
    return delegateContains(object);
  }

  /**
   * Returns whether the backing store list contains the object.
   * @param object the object in question.
   * @return whether the backing store list contains the object.
   */
  protected boolean delegateContains(Object object) 
  {
    return delegateList().contains(object);
  }

  /**
   * Returns whether the list contains each object in the collection.
   * @return whether the list contains each object in the collection.
   * @see #contains
   * @see #useEquals
   */
  @Override
  public boolean containsAll(Collection<?> collection) 
  {
    return delegateContainsAll(collection);
  }

  /**
   * Returns whether the backing store list contains each object in the collection.
   * @return whether the backing store list contains each object in the collection.
   * @see #contains
   * @see #useEquals
   */
  protected boolean delegateContainsAll(Collection<?> collection) 
  {
    return delegateList().containsAll(collection);
  }

  /**
   * Returns the position of the first occurrence of the object in the list.
   * @param object the object in question.
   * @return the position of the first occurrence of the object in the list.
   */
  @Override
  public int indexOf(Object object) 
  {
    return delegateIndexOf(object);
  }

  /**
   * Returns the position of the first occurrence of the object in the backing store list.
   * @param object the object in question.
   * @return the position of the first occurrence of the object in the backing store list.
   */
  protected int delegateIndexOf(Object object) 
  {
    return delegateList().indexOf(object);
  }

  /**
   * Returns the position of the last occurrence of the object in the list.
   * @param object the object in question.
   * @return the position of the last occurrence of the object in the list.
   */
  @Override
  public int lastIndexOf(Object object) 
  {
    return delegateLastIndexOf(object);
  }

  /**
   * Returns the position of the last occurrence of the object in the backing store list.
   * @param object the object in question.
   * @return the position of the last occurrence of the object in the backing store list.
   */
  protected int delegateLastIndexOf(Object object) 
  {
    return delegateList().lastIndexOf(object);
  }

  /**
   * Returns an array containing all the objects in sequence.
   * @return an array containing all the objects in sequence.
   */
  @Override
  public Object[] toArray() 
  {
    return delegateToArray();
  }

  /**
   * Returns an array containing all the objects in the backing store list in sequence.
   * @return an array containing all the objects in the backing store list in sequence.
   */
  protected Object[] delegateToArray() 
  {
    return delegateList().toArray();
  }

  /**
   * Returns an array containing all the objects in sequence.
   * @param array the array that will be filled and returned, if it's big enough;
   * otherwise, a suitably large array of the same type will be allocated and used instead.
   * @return an array containing all the objects in sequence.
   */
  @Override
  public <T> T[] toArray(T[] array) 
  {
    return delegateToArray(array);
  }

  /**
   * Returns an array containing all the objects in the backing store list in sequence.
   * @param array the array that will be filled and returned, if it's big enough;
   * otherwise, a suitably large array of the same type will be allocated and used instead.
   * @return an array containing all the objects in sequence.
   */
  protected <T> T[] delegateToArray(T[] array) 
  {
    return delegateList().toArray(array);
  }

  /**
   * Returns the object at the index.
   * This implementation delegates to {@link #resolve resolve} 
   * so that clients may transform the fetched object.
   * @param index the position in question.
   * @return the object at the index.
   * @exception IndexOutOfBoundsException if the index isn't within the size range.
   * @see #resolve
   * @see #basicGet
   */
  @Override
  public E get(int index) 
  {
    return resolve(index, delegateGet(index));
  }

  /**
   * Returns the object at the index in the backing store list.
   * @param index the position in question.
   * @return the object at the index.
   * @exception IndexOutOfBoundsException if the index isn't within the size range.
   */
  protected E delegateGet(int index) 
  {
    return delegateList().get(index);
  }

  /**
   * Returns the object at the index without {@link #resolve resolving} it.
   * @param index the position in question.
   * @return the object at the index.
   * @exception IndexOutOfBoundsException if the index isn't within the size range.
   * @see #resolve
   * @see #get
   */
  protected E basicGet(int index) 
  {
    return delegateGet(index);
  }

  /**
   * Sets the object at the index 
   * and returns the old object at the index.
   * This implementation delegates to {@link #setUnique setUnique} 
   * after range checking and after {@link #isUnique uniqueness} checking.
   * @param index the position in question.
   * @param object the object to set.
   * @return the old object at the index.
   * @exception IndexOutOfBoundsException if the index isn't within the size range.
   * @exception IllegalArgumentException if there is a constraint violation, e.g., non-uniqueness.
   * @see #setUnique
   */
  @Override
  public E set(int index, E object) 
  {
    if (isUnique())
    {
      int currentIndex = indexOf(object);
      if (currentIndex >= 0 && currentIndex != index)
      {
        throw new IllegalArgumentException("The 'no duplicates' constraint is violated");
      }
    }

    return setUnique(index, object);
  }

  /**
   * Sets the object at the index 
   * and returns the old object at the index;
   * it does no ranging checking or uniqueness checking.
   * This implementation delegates to {@link #didSet didSet} and {@link #didChange didChange}.
   * @param index the position in question.
   * @param object the object to set.
   * @return the old object at the index.
   * @see #set
   */
  public E setUnique(int index, E object)
  {
    E oldObject = delegateSet(index, validate(index, object));
    didSet(index, object, oldObject);
    didChange();
    return oldObject;
  }

  /**
   * Sets the object at the index in the backing store list
   * and returns the old object at the index.
   * @param object the object to set.
   * @return the old object at the index.
   */
  protected E delegateSet(int index, E object)
  {
    return delegateList().set(index, object);
  }

  /**
   * Adds the object at the end of the list 
   * and returns whether the object was added; 
   * if {@link #isUnique uniqueness} is required,
   * duplicates will be ignored and <code>false</code> will be returned.
   * This implementation delegates to {@link #addUnique(Object) addUnique(Object)} 
   * after uniqueness checking.
   * @param object the object to be added.
   * @return whether the object was added.
   * @see #addUnique(Object)
   */
  @Override
  public boolean add(E object)
  {
    if (isUnique() && contains(object))
    {
      return false;
    }
    else
    {
      addUnique(object);
      return true;
    }
  }

  /**
   * Adds the object at the end of the list;
   * it does no uniqueness checking.
   * This implementation delegates to {@link #didAdd didAdd} and {@link #didChange didChange}.
   * after uniqueness checking.
   * @param object the object to be added.
   * @see #add(Object)
   */
  public void addUnique(E object) 
  {
    ++modCount;

    int size = size();
    delegateAdd(validate(size, object));
    didAdd(size, object);
    didChange();
  }

  /**
   * Adds the object at the end of the backing store list.
   * @param object the object to be added.
   */
  protected void delegateAdd(E object) 
  {
    delegateList().add(object);
  }

  /**
   * Adds the object at the given index in the list.
   * If {@link #isUnique uniqueness} is required,
   * duplicates will be ignored.
   * This implementation delegates to {@link #addUnique(int, Object) addUnique(int, Object)} 
   * after uniqueness checking.
   * @param object the object to be added.
   * @exception IllegalArgumentException if {@link #isUnique uniqueness} is required,
   * and the object is a duplicate.
   * @see #addUnique(int, Object)
   */
  @Override
  public void add(int index, E object)
  {
    if (isUnique() && contains(object))
    {
      throw new IllegalArgumentException("The 'no duplicates' constraint is violated");
    }

    addUnique(index, object);
  }

  /**
   * Adds the object at the given index in the list;
   * it does no ranging checking or uniqueness checking.
   * This implementation delegates to {@link #didAdd didAdd} and {@link #didChange didChange}.
   * @param object the object to be added.
   * @see #add(int, Object)
   */
  public void addUnique(int index, E object) 
  {
    ++modCount;

    delegateAdd(index, validate(index, object));
    didAdd(index, object);
    didChange();
  }

  /**
   * Adds the object at the given index in the backing store list.
   * @param object the object to be added.
   */
  protected void delegateAdd(int index, E object) 
  {
    delegateList().add(index, object);
  }

  /**
   * Adds each object of the collection to the end of the list.
   * If {@link #isUnique uniqueness} is required,
   * duplicates will be {@link #getNonDuplicates removed} from the collection, 
   * which could even result in an empty collection.
   * This implementation delegates to {@link #addAllUnique(Collection) addAllUnique(Collection)} 
   * after uniqueness checking.
   * @param collection the collection of objects to be added.
   * @see #addAllUnique(Collection)
   */
  @Override
  public boolean addAll(Collection<? extends E> collection)
  {
    if (isUnique())
    {
      collection = getNonDuplicates(collection);
    }
    return addAllUnique(collection);
  }

  /**
   * Adds each object of the collection to the end of the list;
   * it does no uniqueness checking.
   * This implementation delegates to {@link #didAdd didAdd} and {@link #didChange didChange}.
   * @param collection the collection of objects to be added.
   * @see #addAll(Collection)
   */
  public boolean addAllUnique(Collection<? extends E> collection) 
  {
    ++modCount;

    if (collection.isEmpty())
    {
      return false;
    }
    else
    {
      int i = size();
      for (E object : collection)
      {
        delegateAdd(validate(i, object));
        didAdd(i, object);
        didChange();
        i++;
      }
  
      return true;
    }
  }

  /**
   * Adds each object of the collection at each successive index in the list 
   * and returns whether any objects were added.
   * If {@link #isUnique uniqueness} is required,
   * duplicates will be {@link #getNonDuplicates removed} from the collection, 
   * which could even result in an empty collection.
   * This implementation delegates to {@link #addAllUnique(int, Collection) addAllUnique(int, Collection)} 
   * after uniqueness checking.
   * @param index the index at which to add.
   * @param collection the collection of objects to be added.
   * @return whether any objects were added.
   * @see #addAllUnique(int, Collection)
   */
  @Override
  public boolean addAll(int index, Collection<? extends E> collection) 
  {
    if (isUnique())
    {
      collection = getNonDuplicates(collection);
    }
    return addAllUnique(index, collection);
  }

  /**
   * Adds each object of the collection at each successive index in the list 
   * and returns whether any objects were added;
   * it does no ranging checking or uniqueness checking.
   * This implementation delegates to {@link #didAdd didAdd} and {@link #didChange didChange}.
   * @param index the index at which to add.
   * @param collection the collection of objects to be added.
   * @return whether any objects were added.
   * @see #addAll(int, Collection)
   */
  public boolean addAllUnique(int index, Collection<? extends E> collection) 
  {
    ++modCount;

    if (collection.isEmpty())
    {
      return false;
    }
    else
    {
      for (E object : collection)
      {
        delegateAdd(index, validate(index, object));
        didAdd(index, object);
        didChange();
        index++;
      }

      return true;
    }
  }

  /**
   * Adds each object from start to end of the array at the index of list 
   * and returns whether any objects were added;
   * it does no ranging checking or uniqueness checking.
   * This implementation delegates to {@link #delegateAdd(Object) delegatedAdd}, {@link #didAdd didAdd}, and {@link #didChange didChange}.
   * @param objects the objects to be added.
   * @param start the index of first object to be added.
   * @param end the index past the last object to be added.
   * @return whether any objects were added.
   * @see #addAllUnique(int, Object[], int, int)
   */
  public boolean addAllUnique(Object [] objects, int start, int end) 
  {
    int growth = end - start;

    ++modCount;
    
    if (growth == 0)
    {
      return false;
    }
    else
    {
      int index = size();
      for (int i = start; i < end; ++i, ++index)
      {
        @SuppressWarnings("unchecked") E object = (E)objects[i];
        delegateAdd(validate(index, object));
        didAdd(index, object);
        didChange();
      }
  
      return true;
    }
  }

  /**
   * Adds each object from start to end of the array at each successive index in the list 
   * and returns whether any objects were added;
   * it does no ranging checking or uniqueness checking.
   * This implementation delegates to {@link #delegateAdd(int, Object) delegatedAdd}, {@link #didAdd didAdd}, and {@link #didChange didChange}.
   * @param index the index at which to add.
   * @param objects the objects to be added.
   * @param start the index of first object to be added.
   * @param end the index past the last object to be added.
   * @return whether any objects were added.
   * @see #addAllUnique(Object[], int, int)
   */
  public boolean addAllUnique(int index, Object [] objects, int start, int end) 
  {
    int growth = end - start;

    ++modCount;
    
    if (growth == 0)
    {
      return false;
    }
    else
    {
      for (int i = start; i < end; ++i, ++index)
      {
        @SuppressWarnings("unchecked") E object = (E)objects[i];
        delegateAdd(validate(index, object));
        didAdd(index, object);
        didChange();
      }
  
      return true;
    }
  }

  /**
   * Removes the object from the list and returns whether the object was actually contained by the list.
   * This implementation uses {@link #indexOf indexOf} to find the object
   * and delegates to {@link #remove(int) remove(int)} 
   * in the case that it finds the object.
   * @param object the object to be removed.
   * @return whether the object was actually contained by the list.
   */
  @Override
  public boolean remove(Object object) 
  {
    int index = indexOf(object);
    if (index >= 0)
    {
      remove(index);
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * Removes each object of the collection from the list and returns whether any object was actually contained by the list.
   * @param collection the collection of objects to be removed.
   * @return whether any object was actually contained by the list.
   */
  @Override
  public boolean removeAll(Collection<?> collection) 
  {
    boolean modified = false;
    for (ListIterator<?> i = listIterator(); i.hasNext(); )
    {
      if (collection.contains(i.next()))
      {
        i.remove();
        modified = true;
      }
    }

    return modified;
  }

  /**
   * Removes the object at the index from the list and returns it.
   * This implementation delegates to {@link #didRemove didRemove} and {@link #didChange didChange}.
   * @param index the position of the object to remove.
   * @return the removed object.
   * @exception IndexOutOfBoundsException if the index isn't within the size range.
   */
  @Override
  public E remove(int index) 
  {
    ++modCount;

    E oldObject = delegateRemove(index);
    didRemove(index, oldObject);
    didChange();

    return oldObject;
  }

  /**
   * Removes the object at the index from the backing store list and returns it.
   * @return the removed object.
   * @exception IndexOutOfBoundsException if the index isn't within the size range.
   */
  protected E delegateRemove(int index) 
  {
    return delegateList().remove(index);
  }

  /**
   * Removes from the list each object not contained by the collection
   * and returns whether any object was actually removed.
   * This delegates to {@link #remove(int) remove(int)} 
   * in the case that it finds an object that isn't retained.
   * @param collection the collection of objects to be retained.
   * @return whether any object was actually removed.
   */
  @Override
  public boolean retainAll(Collection<?> collection) 
  {
    boolean modified = false;
    for (ListIterator<?> i = listIterator(); i.hasNext(); )
    {
      if (!collection.contains(i.next()))
      {
        i.remove();
        modified = true;
      }
    }
    return modified;
  }

  /**
   * Clears the list of all objects.
   */
  @Override
  public void clear() 
  {
    doClear(size(), delegateToArray());
  }

  /**
   * Does the actual job of clearing all the objects.
   * @param oldSize the size of the list before it is cleared.
   * @param oldData old values of the list before it is cleared.
   */
  protected void doClear(int oldSize, Object [] oldData) 
  {
    ++modCount;

    delegateClear();

    didClear(oldSize, oldData);
    didChange();
  }

  /**
   * Clears the backing store list of all objects.
   */
  protected void delegateClear() 
  {
    delegateList().clear();
  }

  /**
   * Moves the object to the index of the list.
   * This implementation uses {@link #indexOf} of find the object
   * and delegates to {@link #move(int, int) move(int, int)}.
   * @param index the new position for the object in the list.
   * @param object the object to be moved.
   * @exception IndexOutOfBoundsException if the index isn't within the size range or the object isn't contained by the list.
   */
  public void move(int index, E object) 
  {
    move(index, indexOf(object));
  }

  /**
   * Moves the object at the source index of the list to the target index of the list
   * and returns the moved object.
   * This implementation delegates to {@link #didMove didMove} and {@link #didChange didChange}.
   * @param targetIndex the new position for the object in the list.
   * @param sourceIndex the old position of the object in the list.
   * @return the moved object.
   * @exception IndexOutOfBoundsException if either index isn't within the size range.
   */
  public E move(int targetIndex, int sourceIndex)
  {
    ++modCount;
    int size = size();
    if (targetIndex >= size || targetIndex < 0)
      throw new IndexOutOfBoundsException("targetIndex=" + targetIndex + ", size=" + size);

    if (sourceIndex >= size || sourceIndex < 0)
      throw new IndexOutOfBoundsException("sourceIndex=" + sourceIndex + ", size=" + size);

    E object;
    if (targetIndex != sourceIndex)
    {
      object = delegateMove(targetIndex, sourceIndex);
      didMove(targetIndex, object, sourceIndex);
      didChange();
    }
    else
    {
      object = delegateGet(sourceIndex);
    }
    return object;
  }

  /**
   * Moves the object at the source index in the backing store list by removing it and adding it at the new target index.
   * @param targetIndex the new position for the object in the list.
   * @param sourceIndex the old position of the object in the list.
   * @return the moved object.
   * @exception IndexOutOfBoundsException if either index isn't within the size range.
   * @since 2.3
   */
  protected E delegateMove(int targetIndex, int sourceIndex)
  {
    E result = delegateRemove(sourceIndex);
    delegateAdd(targetIndex, result);
    return result;
  }

  /**
   * Returns whether the object is a list with corresponding equal objects.
   * This implementation uses either <code>equals</code> or <code>"=="</code> depending on {@link #useEquals useEquals}.
   * @return whether the object is a list with corresponding equal objects.
   * @see #useEquals
   */
  @Override
  public boolean equals(Object object) 
  {
    return delegateEquals(object);
  }

  /**
   * Returns whether the object is a list with corresponding equal objects to those in the backing store list.
   * @return whether the object is a list with corresponding equal objects.
   */
  protected boolean delegateEquals(Object object) 
  {
    return delegateList().equals(object);
  }

  /**
   * Returns a hash code computed from each object's hash code.
   * @return a hash code.
   */
  @Override
  public int hashCode() 
  {
    return delegateHashCode();
  }

  /**
   * Returns the hash code of the backing store list.
   * @return a hash code.
   */
  protected int delegateHashCode() 
  {
    return delegateList().hashCode();
  }

  /**
   * Returns a string of the form <code>"[object1, object2]"</code>.
   * @return a string of the form <code>"[object1, object2]"</code>.
   */
  @Override
  public String toString() 
  {
    return delegateToString();
  }

  /**
   * Returns a the string form of the backing store list.
   * @return a the string form of the backing store list.
   */
  protected String delegateToString() 
  {
    return delegateList().toString();
  }

  /**
   * Returns an iterator.
   * This implementation allocates a {@link DelegatingEList.EIterator}.
   * @return an iterator.
   * @see DelegatingEList.EIterator
   */
  @Override
  public Iterator<E> iterator() 
  {
    return new EIterator<E>();
  }

  /**
   * Returns an iterator over the backing store list.
   * @return an iterator.
   */
  protected Iterator<E> delegateIterator() 
  {
    return delegateList().iterator();
  }

  /**
   * An extensible iterator implementation.
   */
  protected class EIterator<E1> implements Iterator<E1>
  {
    /**
     * The current position of the iterator.
     */
    protected int cursor = 0;

    /**
     * The previous position of the iterator.
     */
    protected int lastCursor = -1;

    /**
     * The modification count of the containing list.
     */
    protected int expectedModCount = modCount;

    /**
     * Returns whether there are more objects.
     * @return whether there are more objects.
     */
    public boolean hasNext() 
    {
      return cursor != size();
    }

    /**
     * Returns the next object and advances the iterator.
     * This implementation delegates to {@link #doNext doNext}.
     * @return the next object.
     * @exception NoSuchElementException if the iterator is done.
     */
    @SuppressWarnings("unchecked")
    public E1 next() 
    {
      return (E1)doNext();
    }

    /**
     * Returns the next object and advances the iterator.
     * This implementation delegates to {@link DelegatingEList#get get}.
     * @return the next object.
     * @exception NoSuchElementException if the iterator is done.
     */
    protected E doNext() 
    {
      try 
      {
        E next = DelegatingEList.this.get(cursor);
        checkModCount();
        lastCursor = cursor++;
        return next;
      } 
      catch (IndexOutOfBoundsException exception) 
      {
        checkModCount();
        throw new NoSuchElementException();
      }
    }
    /**
     * Removes the last object returned by {@link #next()} from the list,
     * it's an optional operation.
     * This implementation can also function in a list iterator 
     * to act upon on the object returned by calling <code>previous</code>.
     * @exception IllegalStateException
     * if <code>next</code> has not yet been called,
     * or <code>remove</code> has already been called after the last call to <code>next</code>.
     */
    public void remove() 
    {
      if (lastCursor == -1)
      {
        throw new IllegalStateException();
      }
      checkModCount();

      try 
      {
        DelegatingEList.this.remove(lastCursor);
        expectedModCount = modCount;
        if (lastCursor < cursor)
        {
          --cursor;
        }
        lastCursor = -1;
      } 
      catch (IndexOutOfBoundsException exception) 
      {
        throw new ConcurrentModificationException();
      }
    }

    /**
     * Checks that the modification count is as expected.
     * @exception ConcurrentModificationException if the modification count is not as expected.
     */
    protected void checkModCount() 
    {
      if (modCount != expectedModCount)
      {
        throw new ConcurrentModificationException();
      }
    }
  }

  /**
   * Returns a read-only iterator that does not {@link #resolve resolve} objects.
   * This implementation allocates a {@link NonResolvingEIterator}.
   * @return a read-only iterator that does not resolve objects.
   */
  protected Iterator<E> basicIterator()
  {
    return new NonResolvingEIterator<E>();
  }

  /**
   * An extended read-only iterator that does not {@link DelegatingEList#resolve resolve} objects.
   */
  protected class NonResolvingEIterator<E1> extends EIterator<E1> 
  {
    /**
     * Returns the next object and advances the iterator.
     * This implementation accesses the backing list directly.
     * @return the next object.
     * @exception NoSuchElementException if the iterator is done.
     */
    @Override
    public E doNext() 
    {
      try 
      {
        E next = delegateGet(cursor);
        checkModCount();
        lastCursor = cursor++;
        return next;
      } 
      catch (IndexOutOfBoundsException exception) 
      {
        checkModCount();
        throw new NoSuchElementException();
      }
    }

    /**
     * Throws and exception.
     * @exception UnsupportedOperationException always because it's not supported.
     */
    @Override
    public void remove()
    {
      throw new UnsupportedOperationException();
    }
  }

  /**
   * Returns a list iterator.
   * This implementation allocates a {@link DelegatingEList.EListIterator}.
   * @return a list iterator.
   * @see DelegatingEList.EListIterator
   */
  @Override
  public ListIterator<E> listIterator() 
  {
    return new EListIterator<E>();
  }

  /**
   * Returns a list iterator over the backing store list.
   * @return a list iterator.
   */
  protected ListIterator<E> delegateListIterator() 
  {
    return delegateList().listIterator();
  }

  /**
   * Returns a list iterator advanced to the given index.
   * This implementation allocates a {@link DelegatingEList.EListIterator}.
   * @param index the starting index.
   * @return a list iterator advanced to the index.
   * @see DelegatingEList.EListIterator
   * @exception IndexOutOfBoundsException if the index isn't within the size range.
   */
  @Override
  public ListIterator<E> listIterator(int index) 
  {
    int size = size();
    if (index < 0 || index > size())
      throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);

    return new EListIterator<E>(index);
  }

  /**
   * An extensible list iterator implementation.
   */
  protected class EListIterator<E1> extends EIterator<E1> implements ListIterator<E1>
  {
    /**
     * Creates an instance.
     */
    public EListIterator() 
    {
      super();
    }

    /**
     * Creates an instance advanced to the index.
     * @param index the starting index.
     */
    public EListIterator(int index) 
    {
      cursor = index;
    }

    /**
     * Returns whether there are more objects for {@link #previous}.
     * Returns whether there are more objects.
     */
    public boolean hasPrevious() 
    {
      return cursor != 0;
    }

    /**
     * Returns the previous object and advances the iterator.
     * This implementation delegates to {@link #doPrevious doPrevious}.
     * @return the previous object.
     * @exception NoSuchElementException if the iterator is done.
     */
    @SuppressWarnings("unchecked")
    public E1 previous() 
    {
      return (E1)doPrevious();
    }

    /**
     * Returns the previous object and advances the iterator.
     * This implementation delegates to {@link DelegatingEList#get get}.
     * @return the previous object.
     * @exception NoSuchElementException if the iterator is done.
     */
    protected E doPrevious() 
    {
      try 
      {
        E previous = DelegatingEList.this.get(--cursor);
        checkModCount();
        lastCursor = cursor;
        return previous;
      } 
      catch (IndexOutOfBoundsException exception) 
      {
        checkModCount();
        throw new NoSuchElementException();
      }
    }

    /**
     * Returns the index of the object that would be returned by calling {@link #next() next}.
     * @return the index of the object that would be returned by calling <code>next</code>.
     */
    public int nextIndex() 
    {
      return cursor;
    }

    /**
     * Returns the index of the object that would be returned by calling {@link #previous previous}.
     * @return the index of the object that would be returned by calling <code>previous</code>.
     */
    public int previousIndex() 
    {
      return cursor - 1;
    }

    /**
     * Sets the object at the index of the last call to {@link #next() next} or {@link #previous previous}.
     * This implementation delegates to {@link #doSet doSet}.
     * @param object the object to set.
     * @exception IllegalStateException
     * if <code>next</code> or <code>previous</code> have not yet been called,
     * or {@link #remove() remove} or {@link #add add} have already been called 
     * after the last call to <code>next</code> or <code>previous</code>.
     */
    @SuppressWarnings("unchecked")
    public void set(E1 object) 
    {
      doSet((E)object);
    }

    /**
     * Sets the object at the index of the last call to {@link #next() next} or {@link #previous previous}.
     * This implementation delegates to {@link DelegatingEList#set set}.
     * @param object the object to set.
     * @exception IllegalStateException
     * if <code>next</code> or <code>previous</code> have not yet been called,
     * or {@link #remove() remove} or {@link #add add} have already been called 
     * after the last call to <code>next</code> or <code>previous</code>.
     */
    protected void doSet(E object) 
    {
      if (lastCursor == -1)
      {
        throw new IllegalStateException();
      }
      checkModCount();

      try 
      {
        DelegatingEList.this.set(lastCursor, object);
      } 
      catch (IndexOutOfBoundsException exception) 
      {
        throw new ConcurrentModificationException();
      }
    }

    /**
     * Adds the object at the {@link #next() next} index and advances the iterator past it.
     * This implementation delegates to {@link #doAdd doAdd}.
     * @param object the object to add.
     */
    @SuppressWarnings("unchecked")
    public void add(E1 object) 
    {
      doAdd((E)object);
    }

    /**
     * Adds the object at the {@link #next() next} index and advances the iterator past it.
     * This implementation delegates to {@link DelegatingEList#add(int, Object) add(int, Object)}.
     * @param object the object to add.
     */
    protected void doAdd(E object) 
    {
      checkModCount();

      try 
      {
        DelegatingEList.this.add(cursor++, object);
        expectedModCount = modCount;
        lastCursor = -1;
      }
      catch (IndexOutOfBoundsException exception) 
      {
        throw new ConcurrentModificationException();
      }
    }
  }

  /**
   * Returns a read-only list iterator that does not {@link #resolve resolve} objects.
   * This implementation allocates a {@link NonResolvingEListIterator}.
   * @return a read-only list iterator that does not resolve objects.
   */
  protected ListIterator<E> basicListIterator() 
  {
    return new NonResolvingEListIterator<E>();
  }

  /**
   * Returns a read-only list iterator advanced to the given index that does not {@link #resolve resolve} objects.
   * This implementation allocates a {@link NonResolvingEListIterator}.
   * @param index the starting index.
   * @return a read-only list iterator advanced to the index.
   * @exception IndexOutOfBoundsException if the index isn't within the size range.
   */
  protected ListIterator<E> basicListIterator(int index) 
  {
    int size = size();
    if (index < 0 || index > size())
      throw new IndexOutOfBoundsException("index=" + index + ", size=" + size);

    return new NonResolvingEListIterator<E>(index);
  }

  /**
   * An extended read-only list iterator that does not {@link DelegatingEList#resolve resolve} objects.
   */
  protected class NonResolvingEListIterator<E1> extends EListIterator<E1>
  {
    /**
     * Creates an instance.
     */
    public NonResolvingEListIterator()
    {
      super();
    }

    /**
     * Creates an instance advanced to the index.
     * @param index the starting index.
     */
    public NonResolvingEListIterator(int index) 
    {
      super(index);
    }

    /**
     * Returns the next object and advances the iterator.
     * This implementation accesses the backing list directly.
     * @return the next object.
     * @exception NoSuchElementException if the iterator is done.
     */
    @Override
    protected E doNext()
    {
      try
      {
        E next = delegateGet(cursor);
        checkModCount();
        lastCursor = cursor++;
        return next;
      }
      catch (IndexOutOfBoundsException exception)
      {
        checkModCount();
        throw new NoSuchElementException();
      }
    }

    /**
     * Returns the previous object and advances the iterator.
     * This implementation accesses the backing list directly.
     * @return the previous object.
     * @exception NoSuchElementException if the iterator is done.
     */
    @Override
    protected E doPrevious()
    {
      try
      {
        E previous = delegateGet(--cursor);
        checkModCount();
        lastCursor = cursor;
        return previous;
      }
      catch (IndexOutOfBoundsException exception)
      {
        checkModCount();
        throw new NoSuchElementException();
      }
    }

    /**
     * Throws an exception.
     * @exception UnsupportedOperationException always because it's not supported.
     */
    @Override
    public void remove()
    {
      throw new UnsupportedOperationException();
    }

    /**
     * Throws an exception.
     * @exception UnsupportedOperationException always because it's not supported.
     */
    @Override
    public void set(E1 object)
    {
      throw new UnsupportedOperationException();
    }

    /**
     * Throws an exception.
     * @exception UnsupportedOperationException always because it's not supported.
     */
    @Override
    public void add(E1 object)
    {
      throw new UnsupportedOperationException();
    }
  }

  /**
   * An unmodifiable version of {@link DelegatingEList}.
   */
  public static class UnmodifiableEList<E> extends DelegatingEList<E>
  {
    private static final long serialVersionUID = 1L;

    protected List<E> underlyingList;

    /**
     * Creates an initialized instance.
     * @param underlyingList the backing store list.
     */
    public UnmodifiableEList(List<E> underlyingList) 
    {
      this.underlyingList = underlyingList;
    }

    @Override
    protected List<E> delegateList()
    {
      return underlyingList;
    }

    /**
     * Throws an exception.
     * @exception UnsupportedOperationException always because it's not supported.
     */
    @Override
    public E set(int index, E object) 
    {
      throw new UnsupportedOperationException();
    }

    /**
     * Throws an exception.
     * @exception UnsupportedOperationException always because it's not supported.
     */
    @Override
    public boolean add(E object) 
    {
      throw new UnsupportedOperationException();
    }

    /**
     * Throws an exception.
     * @exception UnsupportedOperationException always because it's not supported.
     */
    @Override
    public void add(int index, E object) 
    {
      throw new UnsupportedOperationException();
    }

    /**
     * Throws an exception.
     * @exception UnsupportedOperationException always because it's not supported.
     */
    @Override
    public boolean addAll(Collection<? extends E> collection) 
    {
      throw new UnsupportedOperationException();
    }

    /**
     * Throws an exception.
     * @exception UnsupportedOperationException always because it's not supported.
     */
    @Override
    public boolean addAll(int index, Collection<? extends E> collection) 
    {
      throw new UnsupportedOperationException();
    }

    /**
     * Throws an exception.
     * @exception UnsupportedOperationException always because it's not supported.
     */
    @Override
    public boolean remove(Object object) 
    {
      throw new UnsupportedOperationException();
    }

    /**
     * Throws an exception.
     * @exception UnsupportedOperationException always because it's not supported.
     */
    @Override
    public E remove(int index) 
    {
      throw new UnsupportedOperationException();
    }

    /**
     * Throws an exception.
     * @exception UnsupportedOperationException always because it's not supported.
     */
    @Override
    public boolean removeAll(Collection<?> collection) 
    {
      throw new UnsupportedOperationException();
    }

    /**
     * Throws an exception.
     * @exception UnsupportedOperationException always because it's not supported.
     */
    @Override
    public boolean retainAll(Collection<?> collection) 
    {
      throw new UnsupportedOperationException();
    }

    /**
     * Throws an exception.
     * @exception UnsupportedOperationException always because it's not supported.
     */
    @Override
    public void clear() 
    {
      throw new UnsupportedOperationException();
    }

    /**
     * Throws an exception.
     * @exception UnsupportedOperationException always because it's not supported.
     */
    @Override
    public void move(int index, E object) 
    {
      throw new UnsupportedOperationException();
    }

    /**
     * Throws an exception.
     * @exception UnsupportedOperationException always because it's not supported.
     */
    @Override
    public E move(int targetIndex, int sourceIndex)
    {
      throw new UnsupportedOperationException();
    }

    /**
     * Returns the {@link DelegatingEList#basicIterator basic iterator}.
     * @return the basic iterator.
     */
    @Override
    public Iterator<E> iterator() 
    {
      return basicIterator();
    }

    /**
     * Returns the {@link #basicListIterator() basic list iterator}.
     * @return the basic list iterator.
     */
    @Override
    public ListIterator<E> listIterator() 
    {
      return basicListIterator();
    }
  
    /**
     * Returns the {@link #basicListIterator(int) basic list iterator} advanced to the index.
     * @param index the starting index.
     * @return the basic list iterator.
     */
    @Override
    public ListIterator<E> listIterator(int index) 
    {
      return basicListIterator(index);
    }
  }

  /**
   * Returns an <b>unsafe</b> list that provides a {@link #resolve non-resolving} view of the backing store list.
   * @return an <b>unsafe</b> list that provides a non-resolving view of the backing store list.
   */
  protected List<E> basicList()
  {
    return delegateBasicList();
  }

  /**
   * Returns an <b>unsafe</b> list that provides a {@link #resolve non-resolving} view of the backing store list.
   * @return an <b>unsafe</b> list that provides a non-resolving view of the backing store list.
   */
  protected List<E> delegateBasicList()
  {
    return delegateList();
  }

  /**
   * Returns the collection of objects in the given collection that are also contained by this list.
   * @param collection the other collection.
   * @return the collection of objects in the given collection that are also contained by this list.
   */
  protected Collection<E> getDuplicates(Collection<?> collection)
  {
    if (collection.isEmpty())
    {
      return ECollections.emptyEList();
    }
    else
    {
      Collection<E> filteredResult = useEquals() ? new BasicEList<E>(collection.size()) : new BasicEList.FastCompare<E>(collection.size());
      for (E object : this)
      {
        if (collection.contains(object))
        {
          filteredResult.add(object);
        }
      }
      return filteredResult;
    }
  }

  /**
   * Returns the collection of objects in the given collection that are not also contained by this list.
   * @param collection the other collection.
   * @return the collection of objects in the given collection that are not also contained by this list.
   */
  protected Collection<E> getNonDuplicates(Collection<? extends E> collection)
  {
    Collection<E> result = useEquals() ?  new UniqueEList<E>(collection.size()) : new UniqueEList.FastCompare<E>(collection.size());
    for (E object : collection)
    {
      if (!contains(object))
      {
        result.add(object);
      }
    }
    return result;
  }
}
