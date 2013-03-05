/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.common.util;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * A thread safe implementation of a {@link WeakInterningHashSet weak interning hash set} particularly well-suited for implementing a pool of instances.
 * All access is thread safe, guarded with a shared {@link #getReadLock() read} lock and an exclusive {@link #getWriteLock()} lock
 * to support multiple simultaneous readers while ensuring that writes are properly serial.
 * The locks are held for the minimal period to allow maximal concurrency.
 * Removals, i.e., {@link #remove(Object) remove}, {@link #removeAll(Collection) removeAll}, {@link #clear() clear}, and {@link #retainAll(Collection) retainAll}, are not supported.
 * 
 * @since 2.9
 * @noextends This API is subject to binary incompatible changes until the API is fully stabilized, i.e., at least until the 2.10 release.
 */
public class Pool<E> extends WeakInterningHashSet<E>
{
  private static final long serialVersionUID = 1L;

  /**
   * An access unit is used during access to the pool.
   * It contains a {@link #values buffer} for processing the collision values at a particular index in the {@link WeakInterningHashSet#entries}.
   * Access units are recycled for reuse in subsequent accesses.
   * Because of shared multi-threaded read access, there may be as many instances as there are threads simultaneously accessing the pool.
   */
  protected static abstract class AccessUnit<E>
  {
    protected abstract static class Queue<E> extends AtomicReference<AccessUnit<E>>
    {
      private static final long serialVersionUID = 1L;

      protected final AccessUnit<E> GUARD =
        new AccessUnit<E>(this)
        {
          @Override
          protected E getValue()
          {
            throw new UnsupportedOperationException(); 
          }

          @Override
          protected void setValue(E value)
          {
            throw new UnsupportedOperationException(); 
          }

          @Override
          protected boolean setArbitraryValue(Object value)
          {
            throw new UnsupportedOperationException(); 
          }

          @Override
          public void reset(boolean isExclusive) 
          {
            throw new UnsupportedOperationException(); 
          }
        };

      protected AccessUnit<E> exclusiveAccessUnit;

      public AccessUnit<E> pop(boolean isExclusive)
      {
        if (isExclusive)
        {
          AccessUnit<E> accessUnit = exclusiveAccessUnit;

          if (accessUnit == null)
          {
            return newAccessUnit();
          }
          else
          {
            exclusiveAccessUnit = accessUnit.next;
            return accessUnit;
          }
        }
        else
        {
          for (;;)
          {
            AccessUnit<E> accessUnit = get();
            if (accessUnit == null)
            {
              return newAccessUnit();
            }
            else if (accessUnit != GUARD && compareAndSet(accessUnit, GUARD))
            {
              set(accessUnit.next);
              return accessUnit;
            }
          }
        }
      }

      public void push(AccessUnit<E> accessUnit, boolean isExclusive)
      {
        if (isExclusive)
        {
          accessUnit.next = exclusiveAccessUnit;
          exclusiveAccessUnit = accessUnit;
        }
        else
        {
          for (;;)
          {
            AccessUnit<E> headAccessUnit = accessUnit.next = get();
            if (headAccessUnit != GUARD && compareAndSet(headAccessUnit, accessUnit))
            {
              break;
            }
          }
        }
      }

      protected abstract AccessUnit<E> newAccessUnit();
    }

    /**
     * Access units are maintained for recycled use in this queue.
     */
    protected final Queue<E> queue;

    /**
     * Access units are chained in {@link Pool#accessUnits} via this link.
     */
    protected AccessUnit<E> next;

    /**
     * The hash code of the object being accessed.
     */
    protected int hashCode;

    protected Object[] values = new Object[10];

    /**
     * This records the number of {@link #values} cached for this access.
     */
    protected int valuesLength;

    /**
     * This records during {@link #match()} the matching index.
     */
    protected int matchingIndex = -1;

    protected Entry<E> createdEntry;

    @SuppressWarnings("unchecked")
    protected Entry<E>[] entries = new Entry[10];

    protected AccessUnit(Queue<E> queue)
    {
      this.queue = queue;
    }

    protected abstract E getValue();
    protected abstract void setValue(E value);
    protected abstract boolean setArbitraryValue(Object value);

    // protected abstract E[] getValues();
    // protected abstract void setValues(E[] values);
    // protected abstract E[] newValues(int length);

    protected Entry<E> getEntry()
    {
      if (createdEntry != null)
      {
        return createdEntry;
      }
      else if (matchingIndex != -1)
      {
        return entries[matchingIndex];
      }
      else
      {
        return null;
      }
    }

    /**
     * Gets the value that should be added to the pool.
     * This can be specialized to internalized the value, e.g., to make a copy that uses minimal storage or is read only.
     */
    public E getInternalizedValue()
    {
      return getValue();
    }

    /**
     * Used to determine whether the given value from the pool is equal to the value being accessed.
     * The default implementation uses {@link Object#equals(Object)}.
     */
    protected boolean matches(E value)
    {
      E accessValue = getValue();
      return accessValue == value || accessValue.equals(value);
    }

    /**
     * Used to determine whether the given value from the pool is equal to the value being accessed.
     * It is called when {@link Pool#addEntry(boolean, Object, AccessUnit) double checking} the match after acquiring the {@link Pool#getWriteLock() write lock}
     * so it can safely any values previous cached.
     */
    public final boolean rematches(E value, Entry<E> entry)
    {
      // Ignore values previously considered.
      //
      Object[] values = this.values;
      for (int i = 0, valuesLength = this.valuesLength; i < valuesLength; ++i)
      {
        if (value == values[i])
        {
          return false;
        }
      }

      if (matches(value))
      {
        add(value, entry);
        matchingIndex = this.valuesLength - 1;
        return true;
      }
      else
      {
        return false;
      }
    }

    /**
     * Used to return a value from among the {@link #values} that {@link #matches(Object)} the value being accessed.
     * Returns <code>null</code> if there is no such matching value.
     */
    public E match()
    {
      Object[] values = this.values;
      for (int i = 0, valuesLength = this.valuesLength; i < valuesLength; ++i)
      {
        // If the value's are equal...
        //
        @SuppressWarnings("unchecked")
        E otherValue = (E)values[i];
        if (matches(otherValue))
        {
          matchingIndex = i;
          return otherValue;
        }
      }
      matchingIndex = -1;
      return null;
    }

    /**
     * Add a value to the {@link #values} incrementing the {@link #valuesLength}.
     */
    public void add(E value, Entry<E> entry)
    {
      // If the values array isn't big enough to hold one more value...
      //
      int length = values.length;
      if (valuesLength == length)
      {
        // Double the size and copy over the already-stored characters.
        //
        Object[] newValues = new Object[2 * length];
        System.arraycopy(values, 0, newValues, 0, valuesLength);
        values = newValues;
      }

      if (entries == null || valuesLength == entries.length)
      {
        @SuppressWarnings("unchecked")
        Entry<E>[] newEntries = new Entry[2 * length];
        if (entries != null)
        {
          System.arraycopy(entries, 0, newEntries, 0, valuesLength);
        }
        entries = newEntries;
      }

      // Add a reference to the value.
      //
      values[valuesLength] = value;
      entries[valuesLength++] = entry;
    }

    /**
     * Prepare the access unit for reuse.
     * In particular remove the hard references to each element in both the {@link #getValues()} and {@link #getEntries()}
     * and then reset the {@link #valuesLength} to 0
     */
    public void reset(boolean isExclusive)
    {
      // Clear out the references to values and entries so they are not strongly referenced and can be garbage collected.
      //
      int valuesLength = this.valuesLength;
      if (valuesLength > 0)
      {
        Object[] values = this.values;
        Entry<?>[] entries = this.entries;
        for (int i = 0; i < valuesLength; ++i)
        {
          values[i] = null;
          entries[i] = null;
        }
        this.valuesLength = 0;
      }

      matchingIndex = -1;
      createdEntry = null;

      if (queue != null)
      {
        queue.push(this, isExclusive);
      }
    }
  }

  protected static class ObjectAccessUnit<E> extends AccessUnit<E>
  {
    protected static class Queue<E> extends AccessUnit.Queue<E>
    {
      private static final long serialVersionUID = 1L;

      @Override
      protected AccessUnit<E> newAccessUnit()
      {
        return new ObjectAccessUnit<E>(this);
      }
    }

    /**
     * The object being accessed;
     * In the case of {@link Pool#contains(Object)} or {@link Pool#remove(Object)}, the value may not be an instance of <code>E</code>.
     */
    protected E value;

    public ObjectAccessUnit(AccessUnit.Queue<E> queue)
    {
      super(queue);
    }

    @Override
    protected E getValue()
    {
      return value;
    }

    @Override
    protected void setValue(E value)
    {
      this.value = value;
      this.hashCode = value.hashCode();
    }

    protected void setValue(E value, int hashCode)
    {
      this.value = value;
      this.hashCode = hashCode;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected boolean setArbitraryValue(Object value)
    {
      setValue((E)value);
      return true;
    }

    @Override
    public void reset(boolean isExclusive)
    {
      value = null;
      super.reset(isExclusive);
    }
  }

  /**
   * Record the number of accesses so that the {@link #cleanup()} can be called occasionally.
   * It's a state modifying operation, so it must hold the exclusive {@link #writeLock write lock} during execution.
   */
  protected int accessCount;

  /**
   * The number of {@link #access(AccessUnit, int) access} between each attempt to {@link #cleanup() clean up} garbage collected entries.
   * Garbage collecting entries requires the exclusive {@link #getWriteLock()} to be held, so it's best to do this infrequently.
   */
  protected int cleanupPeriod = 1000;

  protected final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

  /**
   * To support maximum concurrency, a pair of read and write locks is maintained; this is the {@link ReadWriteLock#readLock() read lock}.
   */
  protected final Lock readLock = readWriteLock.readLock();

  /**
   * To support maximum concurrency, a pair of read and write locks is maintained; this is the {@link ReadWriteLock#readLock() write lock}.
   */
  protected final Lock writeLock = readWriteLock.writeLock();

  protected final AccessUnit.Queue<E> primaryAccessUnits;

  /**
   * Creates an instance with a capacity of 1031.
   */
  public Pool()
  {
    this(1031, null);
  }

  /**
   * Creates an instance.
   */
  public Pool(int minimumCapacity)
  {
    this(minimumCapacity, null);
  }

  protected Pool(int minimumCapacity, AccessUnit.Queue<E> primaryAccessUnits)
  {
    super(minimumCapacity);
    this.primaryAccessUnits = primaryAccessUnits == null ? newDefaultAccessUnits() : primaryAccessUnits;
  }

  protected Pool(int minimumCapacity, AccessUnit.Queue<E> primaryAccessUnits, ReferenceQueue<Object> queue)
  {
    super(minimumCapacity, queue == null ? CommonUtil.REFERENCE_CLEARING_QUEUE : queue);
    this.primaryAccessUnits = primaryAccessUnits == null ? newDefaultAccessUnits() : primaryAccessUnits;
  }

  protected static class ExternalRehasher<E> extends WeakReference<Pool<E>>
  {
    public ExternalRehasher(Pool<E> pool, ReferenceQueue<Object> queue)
    {
      super(pool, queue);
    }

    @Override
    public void clear()
    {
      Pool<E> pool = get();
      if (pool != null)
      {
        int expectedCapacityIndex = pool.capacityIndex;
        int expectedSize = pool.size;
        int expectedCapacity = PRIME_CAPACITIES[expectedCapacityIndex + 1];
        Entry<E>[] oldEntries = pool.entries;

        @SuppressWarnings("unchecked")
        Entry<E>[] expectedEntries = new Entry[expectedSize];
        int[] newIndices = new int[expectedSize];
        Entry<E>[] newEntries = pool.newEntries(expectedCapacity);
        int entryCount = 0;
        LOOP:
        for (int i = 0, length = oldEntries.length; i < length; ++i)
        {
          for (Entry<E> entry = oldEntries[i]; entry != null; entry = entry.next)
          {
            expectedEntries[entryCount] = entry;
            int newIndex = index(entry.hashCode, expectedCapacity);
            if (newEntries[newIndex] == null)
            {
              // If there is no collision, put the entry where it's expected to end up.
              //
              newEntries[newIndex] = entry;
              newIndices[entryCount++] = newIndex;
            }
            else
            {
              // Set a negative index to indicate there will be a collision.
              //
              newIndices[entryCount++] = -1 - newIndex;
            }

            if (entryCount >= expectedSize)
            {
              break LOOP;
            }
          }
        }

        pool.writeLock.lock();
        try
        {
          if (pool.entries == oldEntries && pool.capacityIndex == expectedCapacityIndex)
          {
            int newCapacity = PRIME_CAPACITIES[pool.capacityIndex + 1];
            int newThreshold = newCapacity * 3 / 4;
            if (pool.size > newThreshold)
            {
              // It's growing so fast it's outstripped the capacity we were intending to provide.
              // So do a full rehash based on the current size while holding the write lock.
              //
              pool.grow(2 * pool.size);
            }
            else
            {
              ++pool.capacityIndex;
              int newSize = pool.containsNull ? 1 : 0;
              pool.entries = newEntries;

              // Our computations are useful and we'll apply them.
              //
              for (int i = 0, entryIndex = 0, length = oldEntries.length; i < length; ++i)
              {
                Entry<E> nextEntry = oldEntries[i];
                LOOP:
                for (Entry<E> entry = nextEntry; entry != null; entry = nextEntry)
                {
                  nextEntry = entry.next;
                  while (entryIndex < entryCount)
                  {
                    Entry<E> expectedEntry = expectedEntries[entryIndex];
                    if (expectedEntry == entry)
                    {
                      // If this is the entry we're expecting, put it back in the pool with index we computed earlier.
                      // It will be negative if it's expect to be a collision.
                      //
                      int newIndex = newIndices[entryIndex++];
                      if (newIndex < 0)
                      {
                        pool.putEntry(-1 - newIndex, entry);
                      }
                      else
                      {
                        // The entry is in the right place, but we'd better be sure the next pointer is set to null, because it will be the last entry in the collision chain.
                        //
                        entry.next = null;
                      }
                      // Increment the entry count so we're prepared to match the next expected entry.
                      //
                      ++newSize;
                      continue LOOP;
                    }
                    else if (expectedEntry.get() == null && !expectedEntry.isEnqueued())
                    {
                      // The referent is null and the entry isn't enqueued so it must be an entry that was removed.
                      //
                      ++entryIndex;
                    }
                    else
                    {
                      // If this entry isn't garbage collected, it must be a new one that was added since we did our initial computation, so put it back too.
                      // Don't increment the entry count.
                      //
                      if (!entry.isEnqueued())
                      {
                        pool.putEntry(index(entry.hashCode, newCapacity), entry);
                        ++newSize;
                      }
                      continue LOOP;
                    }
                  }

                  // Now there are only new entries we're not expecting because they were added since we did our initial computation.
                  // If it isn't garbage collected, put it back in.
                  //
                  if (entry.get() != null)
                  {
                    pool.putEntry(index(entry.hashCode, newCapacity), entry);
                    ++newSize;
                  }
                }
              }

              pool.size = newSize;
              pool.threshold = newThreshold;
              ++pool.modCount;
            }
          }
        }
        finally
        {
          pool.writeLock.unlock();
        }

        super.clear();
      }
    }
  }

  @Override
  protected boolean ensureCapacity()
  {
    // If the current size is more the threshold..
    //
    if (size > threshold)
    {
      if (externalQueue != null)
      {
        // Compute the new threshold.
        //
        int newThreshold = PRIME_CAPACITIES[capacityIndex + 1] * 3 / 4;
        if (newThreshold == threshold)
        {
          // If it's the same as the current threshold, then the external queue's thread has not been able to keep up with rehashing, so do the work on the main thread.
          //
          grow(2 * size);
          return true;
        }
        else
        {
          // Increase the threshold so the daemon is able to complete the rehash on the background thread without another request coming in.
          // which will happen when it polls the queue and removes this special reference we've enqueued.
          //
          threshold = newThreshold;
          new ExternalRehasher<E>(this, externalQueue).enqueue();
          return false;
        }
      }
      else
      {
        rehash(newEntries(PRIME_CAPACITIES[++capacityIndex]));
        return true;
      }
    }
    else
    {
      return false;
    }
  }

  protected static class PoolEntry<E> extends Entry<E>
  {
    protected final Pool<E> pool;

    public PoolEntry(Pool<E> pool, E object, int hashCode, ReferenceQueue<Object> queue)
    {
      super(object, hashCode, queue);
      this.pool = pool;
    }

    @Override
    public void clear()
    {
      Pool<E> pool = this.pool;
      pool.writeLock.lock();
      try
      {
        clear(pool);
      }
      finally
      {
        pool.writeLock.unlock();
      }
    }
  }

  @Override
  protected Entry<E> newExternalEntry(E object, int hashCode)
  {
    return new PoolEntry<E>(this, object, hashCode, externalQueue);
  }

  protected AccessUnit.Queue<E> newDefaultAccessUnits()
  {
    return new ObjectAccessUnit.Queue<E>();
  }

  /**
   * Returns this pool's {@link ReadWriteLock#readLock() read lock}.
   * This should be used only for thread-safe {@link #iterator() iteration} in which {@link Iterator#remove() remove} is not called.
   */
  public Lock getReadLock()
  {
    return readLock;
  }

  /**
   * Returns this pool's {@link ReadWriteLock#writeLock() write lock}.
   * This should be used only for thread-safe {@link #iterator() iteration} in which {@link Iterator#remove() remove} is called.
   */
  public Lock getWriteLock()
  {
    return writeLock;
  }

  /**
   * Collect all the values with a matching hash code.
   * If <code>isReadLocked</code> is <code>true</code> it's expected that the {@link #readLock read lock} is already locked.
   * In this case the access will reliably collect all the matching entries currently in the pool.
   * When <code>isReadLocked</code> is <code>false</code>, the access is done without locking and may spuriously fail to return any matches,
   * i.e., if the pool is currently rehashing, or another thread is currently adding the value.
   * Because {@link #remove(Object) removal} is not supported, there will never be a spurious match to a removed entry.
   * This also increments {@link #accessCount} and {@link #cleanupPeriod periodically} calls {@link #cleanup()} if it's not a read locked access.
   */
  protected final void access(boolean isReadLocked, AccessUnit<E> accessUnit)
  {
    // Consider the entries for this hash code's index...
    //
    Entry<E>[] entries = this.entries;
    int hashCode = accessUnit.hashCode;
    int index = index(hashCode, entries.length);
    for (Entry<E> entry = entries[index]; entry != null; entry = entry.next)
    {
      // Consider only entries with this exact hash code, avoiding entries that are simply collisions for values that can't possibly be interesting.
      //
      if (entry.hashCode == hashCode)
      {
        // It's possible the entry's value is null because it's garbage collected, so ignore those.
        //
        E value = entry.get();
        if (value != null)
        {
          // Record the value in the access unit.
          //
          accessUnit.add(value, entry);
        }
      }
    }

    // Periodically poll the queue to clean up garbage collected entries.
    // It doesn't matter that this access count increment isn't thread safe, it just means we might take a little longer to poll the queue.
    // Note that cleanup will acquire the write lock, so we mustn't call if if we're holding the read lock.
    //
    if (!isReadLocked && internalQueue != null && ++accessCount == cleanupPeriod)
    {
      cleanup();
    }
  }

  /**
   * Adds an entry to the pool,
   * but first checks if the entry has been added by another thread
   * since the time when the pool was {@link #access(AccessUnit, int) accessed} while without holding any locks.
   * The access unit is used to {@link AccessUnit#isEntryCreated record} whether an entry was really added.
   * This returns either the value added, or the value that was already added by another thread.
   * The <code>isExlusive</code> argument controls whether the write lock needs to be acquired (<code>false</code>) or is already acquired (<code>true</code>).
   */
  protected final E addEntry(boolean isExclusive, E internalizedValue, AccessUnit<E> accessUnit)
  {
    // Acquire exclusive update access.
    //
    if (!isExclusive)
    {
      writeLock.lock();
    }
    try
    {
      // We need to double check whether or not another thread has added the value since we originally checked while holding the shared read lock or no lock at all.
      //
      int hashCode = accessUnit.hashCode;
      int index = index(hashCode, entries.length);
      for (Entry<E> entry = entries[index]; entry != null; entry = entry.next)
      {
        if (hashCode == entry.hashCode)
        {
          // Check if the value matches
          //
          E entryValue = entry.get();
          if (entryValue != null && accessUnit.rematches(entryValue, entry))
          {
            // The value was added by another thread so return that interned result instead.
            //
            return entryValue;
          }
        }
      }

      // Create an entry and add it because we know for sure that no other thread has added an entry for this value since we originally checked.
      // Record the fact that we created an entry.
      //
      addEntry(index, accessUnit.createdEntry = newEntry(internalizedValue, hashCode));

      // Return the value we added.
      //
      return internalizedValue;
    }
    finally
    {
      // Release the write lock.
      //
      if (!isExclusive)
      {
        writeLock.unlock();
      }
    }
  }

  /**
   * Specialized to ensure that the write lock is held during cleanup.
   */
  @Override
  protected void cleanup()
  {
    // Acquire the exclusive write lock before attempting a cleanup.
    //
    writeLock.lock();
    try
    {
      // Reset the count.
      //
      accessCount = 0;

      // Perform any necessary garbage collection.
      //
      doCleanup();
    }
    finally
    {
      // Release the lock.
      //
      writeLock.unlock();
    }
  }

  /**
   * Calls <code>super.{@link WeakInterningHashSet#cleanup()}.
   */
  protected void doCleanup()
  {
    super.cleanup();
  }

  /**
   * Specialized to ensure that the exclusive write lock is held during growth.
   */
  @Override
  public void grow(int minimumCapacity)
  {
    // Acquire the exclusive write lock before attempting to grow.
    //
    writeLock.lock();
    try
    {
      // Reset the count.
      //
      accessCount = 0;

      // Grow the capacity.
      //
      super.grow(minimumCapacity);
    }
    finally
    {
      // Release the lock.
      //
      writeLock.unlock();
    }
  }


  /**
   * Specialized to ensure thread safety.
   */
  @Override
  public boolean add(E value)
  {
    // Retrieve an access unit for exclusive use in this call for the current thread thread.
    //
    AccessUnit<E> accessUnit = primaryAccessUnits.pop(false);

    // Cache the value, including its hash code.
    //
    accessUnit.setValue(value);

    try
    {
      // Retrieve all the values with this hash code.
      // This is done with no locking, so may fail to find matches.
      //
      access(false, accessUnit);

      E otherValue = accessUnit.match();
      if (otherValue != null)
      {
        return false;
      }

      // Internalize the new value and attempt to add it to the pool.
      //
      addEntry(false, accessUnit.getInternalizedValue(), accessUnit);

      // It might be the case that adding an entry detected that some other thread has already added the value, so return false if we didn't really create an entry in this thread.
      //
      return accessUnit.createdEntry != null;
    }
    finally
    {
      accessUnit.reset(false);
    }
  }

  /**
   * Specialized to ensure thread safety.
   * If the value needs to be added, an {@link AccessUnit#getInternalizedValue() internalized} version is added to pool.
   * This implementation delegates to {@link #doIntern(boolean, AccessUnit)}.
   */
  @Override
  public E intern(E value)
  {
    // Retrieve an access unit for exclusive use in this call for the current thread thread.
    //
    AccessUnit<E> accessUnit = primaryAccessUnits.pop(false);

    // Cache the value, including its hash code.
    //
    accessUnit.setValue(value);

    // Delegate.
    //
    return doIntern(false, accessUnit);
  }

  /**
   * Returns the interned version of the value accessed by this access unit
   * and {@link #setAccessUnit(AccessUnit) frees} the access unit for reuse.
   */
  protected E doIntern(boolean isExclusive, AccessUnit<E> accessUnit)
  {
    try
    {
      // Retrieve all the values with this hash code.
      // This is done with a shared read lock, that's exclusive to any writes.
      //
      access(isExclusive, accessUnit);

      E otherValue = accessUnit.match();
      if (otherValue != null)
      {
        return otherValue;
      }

      // Internalize the new value and attempt to add it to the pool.
      //
      return addEntry(isExclusive, accessUnit.getInternalizedValue(), accessUnit);
    }
    finally
    {
      accessUnit.reset(isExclusive);
    }
  }

  @Override
  public E get(E value)
  {
    // Retrieve an access unit for exclusive use in this call for the current thread thread.
    //
    AccessUnit<E> accessUnit = primaryAccessUnits.pop(false);

    // Cache the value, including its hash code.
    //
    accessUnit.setValue(value);

    readLock.lock();
    try
    {
      // Retrieve all the values with this hash code.
      // This is done with a shared read lock, that's exclusive to any writes.
      // This is done to ensure that a matching entry, if present, will be reliably found.
      //
      access(true, accessUnit);

      return accessUnit.match();
    }
    finally
    {
      readLock.unlock();

      // Release the access unit for reuse in subsequent calls, perhaps on other threads.
      //
      accessUnit.reset(false);
    }
  }

  /**
   * Specialized to ensure thread safety.
   * This access is done while holding only the shared {@link #getReadLock() read} lock.
   */
  @Override
  public boolean contains(Object value)
  {
    // Retrieve an access unit for exclusive use in this call for the current thread thread.
    //
    AccessUnit<E> accessUnit = primaryAccessUnits.pop(false);

    // Cache the value, including its hash code.
    //
    if (!accessUnit.setArbitraryValue(value))
    {
      return false;
    }

    readLock.lock();
    try
    {
      // Retrieve all the values with this hash code.
      // This is done with a shared read lock, that's exclusive to any writes.
      // This is done to ensure that a matching entry, if present, will be reliably found.
      //
      access(true, accessUnit);

      return accessUnit.match() != null;
    }
    finally
    {
      readLock.unlock();

      // Release the access unit for reuse in subsequent calls, perhaps on other threads.
      //
      accessUnit.reset(false);
    }
  }

  /**
   * Callers of the iterator must ensure that they hold the shared {@link #readLock read lock} for the lifetime of the iterator's usage.
   * {@link Iterator#remove() Remove} is not supported.
   */
  @Override
  public Iterator<E> iterator()
  {
    return super.iterator();
  }

  /**
   * Specialized to ensure thread safety.
   */
  @Override
  public boolean equals(Object o)
  {
    // Acquire the appropriate lock before proceeding.
    // Note that this will end up calling cleanup if there is an internal queue, so it's read only access only if there is no internal queue.
    //
    Lock lock = internalQueue == null ? readLock : writeLock;
    lock.lock();
    try
    {
      // Test for equality.
      //
      return super.equals(o);
    }
    finally
    {
      // Release the lock.
      //
      lock.unlock();
    }
  }

  /**
   * Specialized to ensure thread safety.
   */
  @Override
  public int hashCode()
  {
    // Acquire the appropriate lock before proceeding.
    // Note that this will end up calling cleanup if there is an internal queue, so it's read only access only if there is no internal queue.
    //
    Lock lock = internalQueue == null ? readLock : writeLock;
    lock.lock();
    try
    {
      // Compute the hash code.
      //
      return super.hashCode();
    }
    finally
    {
      // Release the lock.
      //
      lock.unlock();
    }
  }

  /**
   * Specialized to ensure thread safety.
   */
  @Override
  public Object[] toArray()
  {
    // Acquire the appropriate lock before proceeding.
    // Note that this will end up calling cleanup if there is an internal queue, so it's read only access only if there is no internal queue.
    //
    Lock lock = internalQueue == null ? readLock : writeLock;
    lock.lock();
    try
    {
      // Convert to an array.
      //
      return super.toArray();
    }
    finally
    {
      // Release the lock.
      //
      lock.unlock();
    }
  }

  /**
   * Specialized to ensure thread safety.
   */
  @Override
  public <T> T[] toArray(T[] a)
  {
    // Acquire the appropriate lock before proceeding.
    // Note that this will end up calling cleanup if there is an internal queue, so it's read only access only if there is no internal queue.
    //
    Lock lock = internalQueue == null ? readLock : writeLock;
    lock.lock();
    try
    {
      // Convert to an array.
      //
      return super.toArray(a);
    }
    finally
    {
      // Release the lock.
      //
      lock.unlock();
    }
  }

  /**
   * Specialized to ensure thread safety.
   */
  @Override
  public boolean containsAll(Collection<?> collection)
  {
    // Acquire the appropriate lock before proceeding.
    // Note that this will end up calling cleanup if there is an internal queue, so it's read only access only if there is no internal queue.
    //
    Lock lock = internalQueue == null ? readLock : writeLock;
    lock.lock();
    try
    {
      // Test for containment.
      //
      return super.containsAll(collection);
    }
    finally
    {
      // Release the lock.
      //
      lock.unlock();
    }
  }

  /**
   * Throws an {@link UnsupportedOperationException} because removal is not supported..
   */
  @Override
  public boolean remove(Object object)
  {
    throw new UnsupportedOperationException();
  }

  /**
   * Throws an {@link UnsupportedOperationException} because removal is not supported..
   */
  @Override
  public boolean removeAll(Collection<?> collection)
  {
    throw new UnsupportedOperationException();
  }

  /**
   * Throws an {@link UnsupportedOperationException} because removal is not supported..
   */
  @Override
  public boolean retainAll(Collection<?> collection)
  {
    throw new UnsupportedOperationException();
  }

  /**
   * Throws an {@link UnsupportedOperationException} because removal is not supported..
   */
  @Override
  public void clear()
  {
    throw new UnsupportedOperationException();
  }

  /**
   * Specialized to ensure thread safety.
   */
  @Override
  public String toString()
  {
    // Acquire the appropriate lock before proceeding.
    // Note that this will end up calling cleanup if there is an internal queue, so it's read only access only if there is no internal queue.
    //
    Lock lock = internalQueue == null ? readLock : writeLock;
    lock.lock();
    try
    {
      // compute the hash code.
      //
      return super.toString();
    }
    finally
    {
      // Release the lock.
      //
      lock.unlock();
    }
  }
}
