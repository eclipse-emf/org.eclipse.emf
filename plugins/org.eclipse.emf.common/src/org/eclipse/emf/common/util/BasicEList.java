/**
 * Copyright (c) 2002-2009 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.common.util;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;


/**
 * A highly extensible list implementation.
 */
public class BasicEList<E> extends AbstractEList<E> implements RandomAccess, Cloneable, Serializable 
{
  private static final long serialVersionUID = 1L;

  /**
   * The size of the list.
   */
  protected int size;

  /**
   * The underlying data storage of the list.
   */
  protected transient Object [] data;

  /**
   * Creates an empty instance with no initial capacity.
   * The data storage will be null.
   */
  public BasicEList() 
  {
    super();
  }

  /**
   * Creates an empty instance with the given capacity.
   * @param initialCapacity the initial capacity of the list before it must grow.
   * @exception IllegalArgumentException if the <code>initialCapacity</code> is negative.
   */
  public BasicEList(int initialCapacity) 
  {
    if (initialCapacity < 0)
    {
      throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);  
    }

    data = newData(initialCapacity); 
  }

  /**
   * Creates an instance that is a copy of the collection.
   * @param collection the initial contents of the list.
   */
  public BasicEList(Collection<? extends E> collection) 
  {
    size = collection.size();

    // Conditionally create the data.
    //
    if (size > 0)
    { 
      // Allow for a bit-shift of growth.
      //
      data = newData(size + size / 8 + 1); 
      collection.toArray(data);
    }
  }

  /**
   * Creates an initialized instance that directly uses the given arguments.
   * @param size the size of the list.
   * @param data the underlying storage of the list.
   */
  protected BasicEList(int size, Object [] data) 
  {
    this.size = size; 
    this.data = data;
  }

  /**
   * Returns new allocated data storage.
   * Clients may override this to create typed storage.
   * The cost of type checking via a typed array is negligible.
   * @return new data storage.
   */
  protected Object [] newData(int capacity)
  {
    return new Object [capacity];
  }

  /**
   * Assigns the object into the data storage at the given index and returns the object that's been stored.
   * Clients can monitor access to the storage via this method.
   * @param index the position of the new content.
   * @param object the new content.
   * @return the object that's been stored.
   */
  protected E assign(int index, E object)
  {
    data[index] = object;
    return object;
  }

  /**
   * Returns the number of objects in the list.
   * @return the number of objects in the list.
   */
  @Override
  public int size() 
  {
    return size;
  }

  /**
   * Returns whether the list has zero size.
   * @return whether the list has zero size.
   */
  @Override
  public boolean isEmpty() 
  {
    return size == 0;
  }

  /**
   * Returns whether the list contains the object.
   * This implementation uses either <code>equals</code> or <code>"=="</code> depending on {@link #useEquals useEquals}.
   * @param object the object in question.
   * @return whether the list contains the object.
   * @see #useEquals
   */
  @Override
  public boolean contains(Object object) 
  {
    if (useEquals() && object != null)
    {
      for (int i = 0; i < size; ++i)
      {
        if (object.equals(data[i]))
        {
          return true;
        }
      }
    }
    else
    {
      for (int i = 0; i < size; ++i)
      {
        if (data[i] == object)
        {
          return true;
        }
      }
    }

    return false;
  }

  /**
   * Returns the position of the first occurrence of the object in the list.
   * This implementation uses either <code>equals</code> or <code>"=="</code> depending on {@link #useEquals useEquals}.
   * @param object the object in question.
   * @return the position of the first occurrence of the object in the list.
   */
  @Override
  public int indexOf(Object object) 
  {
    if (useEquals() && object != null)
    {
      for (int i = 0; i < size; ++i)
      {
        if (object.equals(data[i]))
        {
          return i;
        }
      }
    }
    else
    {
      for (int i = 0; i < size; ++i)
      {
        if (data[i] == object)
        {
          return i;
        }
      }
    }
    return -1;
  }

  /**
   * Returns the position of the last occurrence of the object in the list.
   * This implementation uses either <code>equals</code> or <code>"=="</code> depending on {@link #useEquals useEquals}.
   * @param object the object in question.
   * @return the position of the last occurrence of the object in the list.
   */
  @Override
  public int lastIndexOf(Object object) 
  {
    if (useEquals() && object != null) 
    {
      for (int i = size - 1; i >= 0; --i)
      {
        if (object.equals(data[i]))
        {
          return i;
        }
      }
    }
    else
    {
      for (int i = size - 1; i >= 0; --i)
      {
        if (data[i] == object)
        {
          return i;
        }
      }
    } 
    return -1;
  }

  /**
   * Returns an array containing all the objects in sequence.
   * Clients may override {@link #newData newData} to create typed storage in this case.
   * @return an array containing all the objects in sequence.
   * @see #newData
   */
  @Override
  public Object[] toArray() 
  {
    Object[] result = newData(size);

    // Guard for no data.
    //
    if (size > 0)
    {
      System.arraycopy(data, 0, result, 0, size);
    }
    return result;
  }

  /**
   * Returns an array containing all the objects in sequence.
   * @param array the array that will be filled and returned, if it's big enough;
   * otherwise, a suitably large array of the same type will be allocated and used instead.
   * @return an array containing all the objects in sequence.
   * @see #newData
   */
  @Override
  public <T> T[] toArray(T[] array) 
  {
    // Guard for no data.
    //
    if (size > 0)
    {
      if (array.length < size)
      {
        @SuppressWarnings("unchecked") T [] newArray = (T[])Array.newInstance(array.getClass().getComponentType(), size);
        array  = newArray;
      }
  
      System.arraycopy(data, 0, array, 0, size);
    }

    if (array.length > size)
    {
      array[size] = null;
    }

    return array;
  }

  /**
   * Returns direct <b>unsafe</b> access to the underlying data storage.
   * Clients may <b>not</b> modify this 
   * and may <b>not</b> assume that the array remains valid as the list is modified.
   * @return direct <b>unsafe</b> access to the underlying data storage.
   */
  public Object [] data()
  {
    return data;
  }

  /**
   * Updates directly and <b>unsafely</b> the underlying data storage.
   * Clients <b>must</b> be aware that this subverts all callbacks 
   * and hence possibly the integrity of the list. 
   */
  public void setData(int size, Object [] data)
  {
    this.size = size;
    this.data = data;
    ++modCount;
  }

  /**
   * An IndexOutOfBoundsException that constructs a message from the argument data.
   * Having this avoids having the byte code that computes the message repeated/inlined at the creation site.
   */
  protected static class BasicIndexOutOfBoundsException extends AbstractEList.BasicIndexOutOfBoundsException
  {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an instance with a message based on the arguments.
     */
    public BasicIndexOutOfBoundsException(int index, int size)
    {
      super(index, size);
    }
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
  @SuppressWarnings("unchecked")
  @Override
  public E get(int index) 
  {
    if (data == null || index >= size)
      throw new BasicIndexOutOfBoundsException(index, size);

    return resolve(index, (E)data[index]);
  }

  /**
   * Returns the object at the index without {@link #resolve resolving} it.
   * @param index the position in question.
   * @return the object at the index.
   * @exception IndexOutOfBoundsException if the index isn't within the size range.
   * @see #resolve
   * @see #get
   */
  @Override
  public E basicGet(int index)
  {
    if (data == null || index >= size)
      throw new BasicIndexOutOfBoundsException(index, size);

    return primitiveGet(index);
  }

  /**
   * Returns the object at the index without {@link #resolve resolving} it and without range checking the index.
   * @param index the position in question.
   * @return the object at the index.
   * @see #resolve
   * @see #get
   * @see #basicGet(int)
   */
  @SuppressWarnings("unchecked")
  @Override
  protected E primitiveGet(int index)
  {
    return (E)data[index];
  }

  /**
   * Sets the object at the index 
   * and returns the old object at the index;
   * it does no ranging checking or uniqueness checking.
   * This implementation delegates to {@link #assign assign}, {@link #didSet didSet}, and {@link #didChange didChange}.
   * @param index the position in question.
   * @param object the object to set.
   * @return the old object at the index.
   * @see #set
   */
  @Override
  public E setUnique(int index, E object)
  {
    @SuppressWarnings("unchecked") E oldObject = (E)data[index];
    assign(index, validate(index, object));
    didSet(index, object, oldObject);
    didChange();
    return oldObject;
  }

  /**
   * Adds the object at the end of the list;
   * it does no uniqueness checking.
   * This implementation delegates to {@link #assign assign}, {@link #didAdd didAdd}, and {@link #didChange didChange}.
   * @param object the object to be added.
   * @see #add(Object)
   */
  @Override
  public void addUnique(E object) 
  {
    //  ++modCount
    //
    grow(size + 1);  

    assign(size, validate(size, object));
    didAdd(size++, object);
    didChange();
  }

  /**
   * Adds the object at the given index in the list;
   * it does no ranging checking or uniqueness checking.
   * This implementation delegates to {@link #assign assign}, {@link #didAdd didAdd}, and {@link #didChange didChange}.
   * @param object the object to be added.
   * @see #add(int, Object)
   */
  @Override
  public void addUnique(int index, E object) 
  {
    // ++modCount
    //
    grow(size + 1);
    
    E validatedObject = validate(index, object);
    if (index != size)
    {
      System.arraycopy(data, index, data, index + 1, size - index);
    }
    assign(index, validatedObject);
    ++size;
    didAdd(index, object);
    didChange();
  }

  /**
   * Adds each object of the collection to the end of the list;
   * it does no uniqueness checking.
   * This implementation delegates to {@link #assign assign}, {@link #didAdd didAdd}, and {@link #didChange didChange}.
   * @param collection the collection of objects to be added.
   * @see #addAll(Collection)
   */
  @Override
  public boolean addAllUnique(Collection<? extends E> collection) 
  {
    int growth = collection.size();

    // ++modCount
    //
    grow(size + growth);

    Iterator<? extends E> objects = collection.iterator();
    int oldSize = size;
    size += growth;
    for (int i = oldSize; i < size; ++i)
    {
      E object = objects.next();
      assign(i, validate(i, object));
      didAdd(i, object);
      didChange();
    }

    return growth != 0;
  }

  /**
   * Adds each object of the collection at each successive index in the list 
   * and returns whether any objects were added;
   * it does no ranging checking or uniqueness checking.
   * This implementation delegates to {@link #assign assign}, {@link #didAdd didAdd}, and {@link #didChange didChange}.
   * @param index the index at which to add.
   * @param collection the collection of objects to be added.
   * @return whether any objects were added.
   * @see #addAll(int, Collection)
   */
  @Override
  public boolean addAllUnique(int index, Collection<? extends E> collection) 
  {
    int growth = collection.size();

    // ++modCount
    //
    grow(size + growth);  

    int shifted = size - index;
    if (shifted > 0)
    {
      System.arraycopy(data, index, data, index + growth, shifted);
    }

    Iterator<? extends E> objects = collection.iterator();
    size += growth;
    for (int i = 0; i < growth; ++i)
    {
      E object = objects.next();
      assign(index, validate(index, object));
      didAdd(index, object);
      didChange();
      ++index;
    }

    return growth != 0;
  }

  /**
   * Adds each object from start to end of the array at the index of list 
   * and returns whether any objects were added;
   * it does no ranging checking or uniqueness checking.
   * This implementation delegates to {@link #assign assign}, {@link #didAdd didAdd}, and {@link #didChange didChange}.
   * @param objects the objects to be added.
   * @param start the index of first object to be added.
   * @param end the index past the last object to be added.
   * @return whether any objects were added.
   * @see #addAllUnique(Object[], int, int)
   */
  @Override
  public boolean addAllUnique(Object [] objects, int start, int end) 
  {
    int growth = end - start;

    // ++modCount
    //
    grow(size + growth);  

    size += growth;
    int index = size;
    for (int i = start; i < end; ++i)
    {
      @SuppressWarnings("unchecked") E object = (E)objects[i];
      assign(index, validate(index, object));
      didAdd(index, object);
      didChange();
      ++index;
    }

    return growth != 0;
  }

  /**
   * Adds each object from start to end of the array at each successive index in the list 
   * and returns whether any objects were added;
   * it does no ranging checking or uniqueness checking.
   * This implementation delegates to {@link #assign assign}, {@link #didAdd didAdd}, and {@link #didChange didChange}.
   * @param index the index at which to add.
   * @param objects the objects to be added.
   * @param start the index of first object to be added.
   * @param end the index past the last object to be added.
   * @return whether any objects were added.
   * @see #addAllUnique(Object[], int, int)
   */
  @Override
  public boolean addAllUnique(int index, Object [] objects, int start, int end) 
  {
    int growth = end - start;

    // ++modCount
    //
    grow(size + growth);  

    int shifted = size - index;
    if (shifted > 0)
    {
      System.arraycopy(data, index, data, index + growth, shifted);
    }

    size += growth;
    for (int i = start; i < end; ++i)
    {
      @SuppressWarnings("unchecked") E object = (E)objects[i];
      assign(index, validate(index, object));
      didAdd(index, object);
      didChange();
      ++index;
    }

    return growth != 0;
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
    if (index >= size)
      throw new BasicIndexOutOfBoundsException(index, size);

    ++modCount;
    @SuppressWarnings("unchecked") E oldObject = (E)data[index];

    int shifted = size - index - 1;
    if (shifted > 0)
    {
      System.arraycopy(data, index+1, data, index, shifted);
    }

    // Don't hold onto a duplicate reference to the last object.
    //
    data[--size] = null; 
    didRemove(index, oldObject);
    didChange();

    return oldObject;
  }

  /**
   * Clears the list of all objects.
   * This implementation discards the data storage without modifying it
   * and delegates to {@link #didClear didClear} and {@link #didChange didChange}.
   */
  @Override
  public void clear() 
  {
    ++modCount;

    Object [] oldData = data;
    int oldSize = size;

    // Give it all back to the garbage collector.
    //
    data = null;
    size = 0;

    didClear(oldSize, oldData);
    didChange();
  }

  /**
   * Moves the object at the source index of the list to the target index of the list
   * and returns the moved object.
   * This implementation delegates to {@link #assign assign}, {@link #didMove didMove}, and {@link #didChange didChange}.
   * @param targetIndex the new position for the object in the list.
   * @param sourceIndex the old position of the object in the list.
   * @return the moved object.
   * @exception IndexOutOfBoundsException if either index isn't within the size range.
   */
  @Override
  public E move(int targetIndex, int sourceIndex)
  {
    ++modCount;
    if (targetIndex >= size)
      throw new IndexOutOfBoundsException("targetIndex=" + targetIndex + ", size=" + size);

    if (sourceIndex >= size)
      throw new IndexOutOfBoundsException("sourceIndex=" + sourceIndex + ", size=" + size);

    @SuppressWarnings("unchecked") E object = (E)data[sourceIndex];
    if (targetIndex != sourceIndex)
    {
      if (targetIndex < sourceIndex)
      {
        System.arraycopy(data, targetIndex, data, targetIndex + 1, sourceIndex - targetIndex);
      }
      else
      {
        System.arraycopy(data, sourceIndex + 1, data, sourceIndex, targetIndex - sourceIndex);
      }
      assign(targetIndex, object);
      didMove(targetIndex, object, sourceIndex);
      didChange();
    }
    return object;
  }

  /**
   * Shrinks the capacity of the list to the minimal requirements.
   * @see #grow
   */
  public void shrink() 
  {
    ++modCount;

    // Conditionally create the data.
    //
    if (size == 0)
    {
      // Give it all back to the garbage collector.
      //
      data = null;
    }
    else if (size < data.length) 
    {
      Object [] oldData = data;
      data = newData(size);
      System.arraycopy(oldData, 0, data, 0, size);
    }
  }

  /**
   * Grows the capacity of the list 
   * to ensure that no additional growth is needed until the size exceeds the specified minimum capacity.
   * @see #shrink
   */
  public void grow(int minimumCapacity) 
  {
    ++modCount;
    int oldCapacity = data == null ? 0 : data.length;
    if (minimumCapacity > oldCapacity)
    {
      Object oldData[] = data;

      // This seems to be a pretty sweet formula that supports good growth.
      // Adding an object to a list will create a list of capacity 4, 
      // which is just about the average list size.
      //
      int newCapacity = oldCapacity + oldCapacity / 2 + 4;
      if (newCapacity < minimumCapacity)
      {
        newCapacity = minimumCapacity;
      }
      data = newData(newCapacity);
      if (oldData != null)
      {
        System.arraycopy(oldData, 0, data, 0, size);
      }
    }
  }

  private synchronized void writeObject(ObjectOutputStream objectOutputStream) throws IOException
  {
    objectOutputStream.defaultWriteObject();
    if (data == null)
    {
      objectOutputStream.writeInt(0);
    }
    else
    {
      objectOutputStream.writeInt(data.length);
      for (int i = 0; i < size; ++i)
      {
        objectOutputStream.writeObject(data[i]);
      }
    }
  }

  private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException
  {
    objectInputStream.defaultReadObject();
    int arrayLength = objectInputStream.readInt();
    if (arrayLength > 0)
    {
      try
      {
        data = newData(arrayLength);
      }
      catch (Throwable exception)
      {
        data = new Object[arrayLength];
      }

      for (int i = 0; i < size; ++i)
      {
        @SuppressWarnings("unchecked") E object = (E)objectInputStream.readObject();
        didAdd(i, assign(i, object));
      }
    }
  }

  /**
   * Returns a shallow copy of this list.
   * @return a shallow copy of this list.
   */
  @Override
  public Object clone() 
  {
    try 
    {
      @SuppressWarnings("unchecked") BasicEList<E> clone = (BasicEList<E>)super.clone();
      if (size > 0)
      {
        clone.size = size;
        clone.data = newData(size); 
        System.arraycopy(data, 0, clone.data, 0, size);
      }
      return clone;
    } 
    catch (CloneNotSupportedException exception) 
    { 
      throw new InternalError();
    }
  }

  /**
   * An extensible iterator implementation.
   * @deprecated 
   * @see AbstractEList.EIterator
   */
  @Deprecated
  protected class EIterator<E1> extends AbstractEList<E>.EIterator<E1>
  {
    // Pointless extension
  }

  /**
   * An extended read-only iterator that does not {@link BasicEList#resolve resolve} objects.
   * @deprecated
   * @see AbstractEList.NonResolvingEIterator
   */
  @Deprecated
  protected class NonResolvingEIterator<E1> extends AbstractEList<E>.NonResolvingEIterator<E1>
  {
    // Pointless extension
  }

  /**
   * An extensible list iterator implementation.
   * @deprecated
   * @see AbstractEList.EListIterator
   */
  @Deprecated
  protected class EListIterator<E1> extends AbstractEList<E>.EListIterator<E1>
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
      super(index);
      cursor = index;
    }
  }

  /**
   * An extended read-only list iterator that does not {@link BasicEList#resolve resolve} objects.
   * @deprecated
   * @see AbstractEList.NonResolvingEListIterator
   */
  @Deprecated
  protected class NonResolvingEListIterator<E1> extends AbstractEList<E>.NonResolvingEListIterator<E1>
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
  }

  /**
   * An unmodifiable version of {@link BasicEList}.
   */
  public static class UnmodifiableEList<E> extends BasicEList<E>
  {
    private static final long serialVersionUID = 1L;

    /**
     * Creates an initialized instance.
     * @param size the size of the list.
     * @param data the underlying storage of the list.
     */
    public UnmodifiableEList(int size, Object [] data) 
    {
      this.size = size;
      this.data = data;
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
     * Throws an exception.
     * @exception UnsupportedOperationException always because it's not supported.
     */
    @Override
    public void shrink() 
    {
      throw new UnsupportedOperationException();
    }

    /**
     * Throws an exception.
     * @exception UnsupportedOperationException always because it's not supported.
     */
    @Override
    public void grow(int minimumCapacity) 
    {
      throw new UnsupportedOperationException();
    }

    /**
     * Returns the {@link BasicEList#basicIterator basic iterator}.
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
   * Returns an <b>unsafe</b> list that provides a {@link #resolve non-resolving} view of the underlying data storage.
   * @return an <b>unsafe</b> list that provides a non-resolving view of the underlying data storage.
   */
  @Override
  protected List<E> basicList()
  {
    if (size == 0)
    {
      return ECollections.emptyEList();
    }
    else
    {
      return new UnmodifiableEList<E>(size, data);
    }
  }

  /**
   * A <code>BasicEList</code> that {@link #useEquals uses} <code>==</code> instead of <code>equals</code> to compare members.
   */
  public static class FastCompare<E> extends BasicEList<E>
  {
    private static final long serialVersionUID = 1L;

    /**
     * Creates an empty instance with no initial capacity.
     */
    public FastCompare()
    {
      super();
    }

    /**
     * Creates an empty instance with the given capacity.
     * @param initialCapacity the initial capacity of the list before it must grow.
     * @exception IllegalArgumentException if the <code>initialCapacity</code> is negative.
     */
    public FastCompare(int initialCapacity)
    {
      super(initialCapacity);
    }

    /**
     * Creates an instance that is a copy of the collection.
     * @param collection the initial contents of the list.
     */
    public FastCompare(Collection<? extends E> collection)
    {
      super(collection.size());
      addAll(collection);
    }

    /**
     * Returns <code>false</code> because this list uses <code>==</code>.
     * @return <code>false</code>.
     */
    @Override
    protected boolean useEquals()
    {
      return false;
    }
  }
}
