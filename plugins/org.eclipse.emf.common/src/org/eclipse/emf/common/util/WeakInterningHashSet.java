/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.common.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.AbstractSet;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.WeakHashMap;

/**
 * An implementation of an {@link InterningSet interning set} that keeps weak references to its element.
 * This structure is particularly well suited for maintaining a cache of instances, e.g., a string pool.
 * All the caveats about the behavior of the garbage collector that apply for a {@link WeakHashMap#keySet() weak hash map} apply for this implementation as well.
 *
 * @since 2.9
 * @noextends This API is subject to binary incompatible changes until the API is fully stabilized, i.e., at least until the 2.10 release.
 */
public class WeakInterningHashSet<E>  extends AbstractSet<E> implements InterningSet<E>, Serializable
{
  protected static final long serialVersionUID = 1L;

  /**
   * A special entry used by the iterator to represent the need to yield the null value for the subsequent call to next().
   */
  protected static final Entry<Object> NULL_ENTRY = new Entry<Object>();

  /**
   * The capacity used for the {@link #entries} of the set will always be a prime number to help ensure uniform distribution of the hash codes.
   * Each of these prime numbers is the smallest prime larger than 2^n, except for the last, which is the largest prime < {@link Integer#MAX_VALUE}.
   */
  protected static final int[] PRIME_CAPACITIES =
    new int[]
    {
      // 1,
      // 2,
      // 5,
      // 11,
      17,
      37,
      67,
      131,
      257,
      521,
      1031,
      2053,
      4099,
      8209,
      16411,
      32771,
      65537,
      131101,
      262147,
      524309,
      1048583,
      2097169,
      4194319,
      8388617,
      16777259,
      33554467,
      67108879,
      134217757,
      268435459,
      536870923,
      1073741827,
      2147483629
    };

  /**
   * The current size of the set.
   */
  protected int size;

  /**
   * The current index within {@link #PRIME_CAPACITIES} for the length of the {@link #entries}.
   */
  transient protected int capacityIndex;

  /**
   * Whether or not this set contains the null value.
   */
  transient protected boolean containsNull;

  /**
   * The next size at which an {@link #rehash(Entry[]) rehash} should occur.
   */
  transient protected int threshold;

  /**
   * The modification count for fail fast iteration with {@link ConcurrentModificationException}.
   */
  transient protected int modCount;

  /**
   * The table of linked entries.
   */
  transient protected Entry<E>[] entries;

  /**
   * A queue used when {@link #newInternalEntry(Object, int) creating} internal entries and {@link #cleanup() cleaning} garbage collected references.
   * The set must have must have either an internal queue or an {@link #externalQueue external} queue.
   * All calls to {@link #cleanup()} are guarded by whether this value is <code>null</code>.
   */
  transient protected ReferenceQueue<E> internalQueue;

  /**
   * A queue used when {@link #newExternalEntry(Object, int) creating} external entries and subsequently for cleaning garbage collected references.
   * {@link SelfCleaningEntry#clear() Cleaning} garbage collected references is the responsibility of this external queue provider.
   * All calls to {@link #cleanup()} are guarded by whether {@link #internalQueue} value is <code>null</code>, so no cleanup takes plac when there is an external reference queue.
   */
  transient final protected ReferenceQueue<Object> externalQueue;

  /**
   * A weak reference holder that caches the hash code of the referent and is {@link #next chained} in the {@link WeakInterningHashSet#entries} to handle collisions.
   */
  protected static class Entry<E> extends WeakReference<E>
  {
    /**
     * The cached hash code.
     */
    public final int hashCode;

    /**
     * The next entry in the collision chain.
     */
    public Entry<E> next;

    /**
     * Used only to create the {@link WeakInterningHashSet#NULL_ENTRY}.
     */
    private Entry()
    {
      super(null);
      hashCode = 0;
    }

    /**
     * Creates an entry thats part of the set's {@link WeakInterningHashSet#internalQueue} or {@link WeakInterningHashSet#externalQueue}.
     */
    public Entry(E object, int hashCode, ReferenceQueue<? super E> queue)
    {
      super(object, queue);
      this.hashCode = hashCode;
    }

    /**
     * Returns the next entry in the collision chain with the same {@link #hashCode}.
     */
    public Entry<E> getNextEntry()
    {
      for (Entry<E> entry = next; entry != null; entry = entry.next)
      {
        if (entry.hashCode == hashCode)
        {
          return entry;
        }
      }
      return null;
    }

    /**
     * Specialized to call {@link #doClear()}.
     * Derived classes should generally override this method only to impose synchronization.
     * All the cleaning up of the fields of the entry should be done in {@link #doClear()}.
     */
    @Override
    public void clear()
    {
      doClear();
    }

    protected void doClear()
    {
      next = null;
      super.clear();
    }

    /**
     * {@link WeakInterningHashSet#removeEntry(Entry) removes} this entry from the set and then calls {@link #doClear()}.
     */
    public final void clear(WeakInterningHashSet<E> set)
    {
      set.removeEntry(this);
      doClear();
    }

    /**
     * Returns the string value of the {@link #get() referent}.
     */
    @Override
    public String toString()
    {
      Object object = get();
      return object == null ? "null" : object.toString();
    }
  }

  /**
   * Creates an instance with a minimum capacity.
   */
  public WeakInterningHashSet()
  {
    this(0);
  }

  /**
   * Creates an instance with at least the given capacity.
   */
  public WeakInterningHashSet(int minimumCapacity)
  {
    this(minimumCapacity, null);
  }

  /**
   * Creates an instance with the given capacity and the given {@link #externalQueue external queue}, unless it's <code>null</code> in which case an {@link #internalQueue} is created as normal.
   */
  WeakInterningHashSet(int minimumCapacity, ReferenceQueue<Object> queue)
  {
    doGrow(minimumCapacity);
    externalQueue = queue;
    if (queue == null)
    {
      this.internalQueue = new ReferenceQueue<E>();
    }
  }

  @Override
  public int size()
  {
    if (internalQueue != null)
    {
      cleanup();
    }

    return size;
  }

  /**
   * Ensures that the set has at least the specified capacity.
   * Higher capacity ensures fewer collisions hence faster lookup.
   * This does nothing if the specified capacity is smaller than the current capacity.
   */
  public void grow(int minimumCapacity)
  {
    int currentCapacity = PRIME_CAPACITIES[capacityIndex];
    if (currentCapacity < minimumCapacity)
    {
      doGrow(minimumCapacity);
    }
  }

  /**
   * Does the actual work of increasing the capacity and calling {@link #rehash(Entry[])}.
   */
  private final void doGrow(int minimumCapacity)
  {
    for (int i = 0, length = PRIME_CAPACITIES.length; i < length; ++i)
    {
      int capacity = PRIME_CAPACITIES[i];
      if (capacity >= minimumCapacity)
      {
        capacityIndex = i;
        ++modCount;
        rehash(newEntries(capacity));
        break;
      }
    }
  }

  /**
   * Returns the {@link Object#hashCode() hash code} of the object.
   * This will never be called with <code>null</code>.
   * A derived class may specialize this to compute an alternative hash code and should generally specialize {@link #asInstance(Object)} and {@link #equals(Object, Object)}
   * The default implementation simply uses {@link Object#hashCode()}.
   */
  protected int hashCode(E object)
  {
    return object.hashCode();
  }

  /**
   * Returns whether the two objects are considered equal.
   * This will never be called with <code>null</code>.
   * The first argument will be an argument passed to one of the mutating methods of the set and the second will be a value already in the set.
   * A derived class may specialize this to check for structural equality and should generally specialize {@link #asInstance(Object)} and {@link #hashCode(Object)}.
   * The default implementation simply uses the {@link Object#equals(Object)}.
   */
  protected boolean equals(E object, E otherObject)
  {
    return object.equals(otherObject);
  }

  /**
   * Returns the result of casting the object to <code>E</code>, or <code>null</code> if the object is not an instance of <code>E</code>.
   * This method is called by {@link #remove(Object)} and {@link #contains(Object)} which use the returned <code>null</code> return <code>false</code>
   * because an object that isn't an instance of <code>E</code> can't be (shouldn't be in the set) and so can't be removed.
   * The default implementation cannot do a checked cast so it always returns the argument via an unchecked cast.
   * This method should be specialized if {@link #hashCode(Object)} or {@link #equals(Object, Object)} are specialized.
   */
  @SuppressWarnings("unchecked")
  protected E asInstance(Object object)
  {
    return (E)object;
  }

  /**
   * Creates a new array of {@link #entries} with the specified capacity.
   */
  @SuppressWarnings("unchecked")
  protected Entry<E>[] newEntries(int capacity)
  {
    Entry<E> [] newEntries = new Entry[capacity];
    return newEntries;
  }

  /**
   * Ensures that 3/4 of current capacity is larger than the current size, i.e., that the {@link #size} <= {@link #threshold}.
   * If not, it {@link #newEntries(int) reallocates} the entries to the next {@link #PRIME_CAPACITIES prime capacity},
   * i.e., it approximate doubles the capacity,
   * and then {@link #rehash(Entry[]) rehashes} the set.
   * The return value indicates whether or note the entries where rehashed.
   */
  protected boolean ensureCapacity()
  {
    // If the current size is more the threshold..
    //
    if (size > threshold)
    {
      rehash(newEntries(PRIME_CAPACITIES[++capacityIndex]));
      return true;
    }
    else
    {
      return false;
    }
  }

  /**
   * Rehashes the existing {@#entries} into the new entries.
   */
  final void rehash(Entry<E> [] newEntries)
  {
    Entry<E> [] oldEntries = entries;
    entries = newEntries;
    int newCapacity = newEntries.length;
    if (oldEntries != null)
    {
      for (int i = 0, length = oldEntries.length; i < length; ++i)
      {
        Entry<E> entry = oldEntries[i];
        while (entry != null)
        {
          Entry<E> nextEntry = entry.next;
          putEntry(index(entry.hashCode, newCapacity), entry);
          entry = nextEntry;
        }
      }
    }
    threshold = newCapacity * 3 / 4;
  }

  /**
   * Polls the {@link #internalQueue} and {@link #removeEntry(Entry) removes} any garbage collected entries.
   */
  protected void cleanup()
  {
    for (;;)
    {
      Reference<? extends E> reference = internalQueue.poll();
      if (reference == null)
      {
        return;
      }
      else
      {
        @SuppressWarnings("unchecked")
        Entry<E> entry = (Entry<E>)reference;
        removeEntry(entry);
        entry.clear();
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean remove(Object object)
  {
    if (internalQueue != null)
    {
      cleanup();
    }

    if (object == null)
    {
      // The presence of null is encoded in containsNull.
      //
      if (!containsNull)
      {
        return false;
      }
      else
      {
        containsNull = false;
        ++modCount;
        --size;
        return true;
      }
    }
    else
    {
      // First check if the object is of type E.
      //
      E instance = asInstance(object);
      if (instance == null)
      {
        return false;
      }
      else
      {
        // Iterate over the entries for the instance's hash code.
        //
        int hashCode = hashCode(instance);
        int index = index(hashCode, entries.length);
        for (Entry<E> entry = entries[index]; entry != null; entry = entry.next)
        {
          if (hashCode == entry.hashCode)
          {
            // Check that the referent isn't garbage collected and then compare it.
            //
            E otherObject = entry.get();
            if (instance == otherObject || equals(instance, otherObject))
            {
              if (internalQueue != null)
              {
                // Remove the entry directly, if it's managed by our internal queue, and clear the reference so it's not enqueued.
                //
                removeEntry(entry);
                entry.clear();
              }
              else
              {
                // Call the entry's clear method which is specialized to remove the entry.
                //
                entry.clear();
              }
              return true;
            }
          }
        }
      }
    }

    return false;
  }

  /**
   * Specialized to be more efficient.
   */
  @Override
  public void clear()
  {
    containsNull = false;
    size = 0;
    for (int i = 0; i < entries.length; ++i)
    {
      entries[i] = null;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean add(E object)
  {
    if (internalQueue != null)
    {
      cleanup();
    }

    if (object == null)
    {
      // The presence of null is encoded containsNull.
      //
      if (!containsNull)
      {
        containsNull = true;
        ++modCount;
        ++size;
        return true;
      }
      else
      {
        return false;
      }
    }
    else
    {
      // Iterate over the entries with the matching hash code.
      //
      int hashCode = hashCode(object);
      int index = index(hashCode, entries.length);
      for (Entry<E> entry = entries[index]; entry != null; entry = entry.next)
      {
        if (hashCode == entry.hashCode)
        {
          // Check that the referent isn't garbage collected and then compare it.
          //
          E otherObject = entry.get();
          if (object == otherObject || equals(object, otherObject))
          {
            // If it's present, return false;
            //
            return false;
          }
        }
      }

      // Add the entry because it's not already in the set.
      //
      addEntry(index, newEntry(object, hashCode));
      return true;
    }
  }

  /**
   * {@inheritDoc}
   */
  public E intern(E object)
  {
    if (internalQueue != null)
    {
      cleanup();
    }

    if (object == null)
    {
      // The presence of null is encoded containsNull.
      //
      if (!containsNull)
      {
        containsNull = true;
        ++modCount;
        ++size;
      }
      return null;
    }
    else
    {
      // Iterate over the entries with the matching hash code.
      //
      int hashCode = hashCode(object);
      int index = index(hashCode, entries.length);
      for (Entry<E> entry = entries[index]; entry != null; entry = entry.next)
      {
        if (hashCode == entry.hashCode)
        {
          // Check that the referent isn't garbage collected and then compare it.
          //
          E otherObject = entry.get();
          if (object == otherObject || equals(object, otherObject))
          {
            // Return that already present value.
            //
            return otherObject;
          }
        }
      }

      // Add the entry because it's not already in the set, and return the value.
      //
      addEntry(index, newEntry(object, hashCode));
      return object;
    }
  }

  /**
   * {@inheritDoc}
   */
  public E get(E object)
  {
    if (internalQueue != null)
    {
      cleanup();
    }

    if (object == null)
    {
      // Whether null is present or not, we always return null.
      //
      return null;
    }
    else
    {
      // Iterate over the entries with the matching hash code.
      //
      int hashCode = hashCode(object);
      int index = index(hashCode, entries.length);
      for (Entry<E> entry = entries[index]; entry != null; entry = entry.next)
      {
        if (hashCode == entry.hashCode)
        {
          // Check that the referent isn't garbage collected and then compare it.
          //
          E otherObject = entry.get();
          if (object == otherObject || equals(object, otherObject))
          {
            // Return the already present value.
            //
            return otherObject;
          }
        }
      }

      // Return null when becase the value isn't in the set.
      //
      return null;
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean contains(Object object)
  {
    if (internalQueue != null)
    {
      cleanup();
    }

    if (object == null)
    {
      // The presence of null is encoded in the modCount.
      //
      return containsNull;
    }
    else
    {
      // First check if the object is of type E.
      //
      E instance = asInstance(object);
      if (instance == null)
      {
        return false;
      }
      else
      {
        // Iterate over the entries with the matching hash code.
        //
        int hashCode = hashCode(instance);
        int index = index(hashCode, entries.length);
        for (Entry<E> entry = entries[index]; entry != null; entry = entry.next)
        {
          if (hashCode == entry.hashCode)
          {
            // Check that the referent isn't garbage collected and then compare it.
            //
            E otherObject = entry.get();
            if (instance == otherObject || equals(instance, otherObject))
            {
              // Return true because it's present.
              //
              return true;
            }
          }
        }

        // Return false because it's not present.
        //
        return false;
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterator<E> iterator()
  {
    if (internalQueue != null)
    {
      cleanup();
    }

    // A specialized iterator for walking the entries.
    //
    return
      new Iterator<E>()
      {
        /**
         * The expected modCount for fail fast concurrent modification testing.
         */
        int expectedModCount = modCount;

        /**
         * The current index in the {@link WeakInterningHashSet#entries}.
         */
        int index;

        /**
         * Keep a hard reference to the object in the {@link #nextEntry} to ensure it's not garbage collected.
         */
        E nextObject;

        /**
         * The entry for yielding the value of {@link #next}.
         */
        Entry<E> nextEntry;

        /**
         * Keep a hard reference to the object in the {@link #removeEntry} to ensure it's not garbage collected.
         */
        @SuppressWarnings("unused")
        E removeObject;

        /**
         * The entry for which the next call to {@link #remove} applies.
         */
        Entry<E> removeEntry;

        {
          // Set up the initial next entry...
          //
          if (size > 0)
          {
            if (containsNull)
            {
              // If null is in the set, prepare to yield it.
              //
              index = -1;
              nextEntry = nullEntry();
            }
            else
            {
              // Scan the entries for a non-null entry.
              //
              for (;;)
              {
                Entry<E> entry = entries[index];
                if (entry != null)
                {
                  // If the referent isn't null, prepare to yield its value.
                  //
                  E object = entry.get();
                  if (object != null)
                  {
                    nextObject = object;
                    nextEntry = entry;
                    break;
                  }
                }
                // If we get to the end of the entries, terminate the loop; the set is empty.
                //
                if (++index == entries.length)
                {
                  break;
                }
              }
            }
          }
        }

        public boolean hasNext()
        {
          if (modCount != expectedModCount)
          {
            throw new ConcurrentModificationException();
          }

          // The preparation has already been done; so if there is an entry prepared we can return true.
          //
          return nextEntry != null;
        }

        public E next()
        {
          // If there is no entry prepared, the caller is iterating past the end of the set.
          //
          if (nextEntry == null)
          {
            throw new NoSuchElementException();
          }

          // Keep a hard reference to the remove entry's referent so polling can't remove the entry.
          //
          removeObject = nextObject;
          removeEntry = nextEntry;

          // Ensure that there's a hard reference to the object we're about to yield.
          //
          E result = nextObject;

          // Prepare for the next call to hasNext.
          // Continue with the collision chain for the current entry.
          //
          for (Entry<E> entry = nextEntry.next;;)
          {
            if (entry != null)
            {
              E object = entry.get();
              if (object != null)
              {
                // If the referent isn't null, prepare to yield it; we're done the loop.
                //
                nextObject = object;
                nextEntry = entry;
                break;
              }
              else
              {
                // Continue the loop with the next entry in the collision chain.
                //
                entry = entry.next;
                continue;
              }
            }

            // If we proceed beyond the end of the entries, prepare to yield nothing.
            //
            if (++index == entries.length)
            {
              nextEntry = null;
              nextObject = null;
              break;
            }

            // Consider the next entry in the table.
            //
            entry = entries[index];
          }

          // Yield the cached result.
          //
          return result;
        }

        public void remove()
        {
          if (modCount != expectedModCount)
          {
            throw new ConcurrentModificationException();
          }

          // If there is no remove entry, then it's invalid to try to remove something.
          //
          if (removeEntry == null)
          {
            throw new IllegalStateException();
          }

          // Clean up the entry and update the expected modCount to it's value after the removal.
          //
          if (internalQueue != null)
          {
            // Remove the entry direct because it's managed by our internal queue and then clear the reference so it's not enqueued.
            //
            WeakInterningHashSet.this.removeEntry(removeEntry);
            removeEntry.clear();
          }
          else
          {
            // It's managed by an external queue, so call clear which is specialized to remove the entry and clear the reference.
            //
            removeEntry.clear();
          }
          expectedModCount = WeakInterningHashSet.this.modCount;

          // Forget the remove entry and its referent.
          //
          removeObject = null;
          removeEntry = null;
        }
      };
  }

  /**
   * Returns the index in the {@link #entries} for the given hash code and capacity.
   */
  protected static int index(int hashCode, int capacity)
  {
    return (hashCode & 0x7FFFFFFF) % capacity;
  }

  /**
   * Gets the first entry in the table with exactly the given hash code.
   * It's very useful to call {@link Entry#getNextEntry()} to yield the next entry with exactly this same hash code.
   */
  protected Entry<E> getEntry(int hashCode)
  {
    if (internalQueue != null)
    {
      cleanup();
    }

    int index = index(hashCode, entries.length);
    for (Entry<E> entry = entries[index]; entry != null; entry = entry.next)
    {
      if (hashCode == entry.hashCode)
      {
        return entry;
      }
    }

    return null;
  }

  /**
   * Returns the {@link #NULL_ENTRY singleton entry} representing the <code>null</code> value's entry during iteration.
   */
  @SuppressWarnings("unchecked")
  protected Entry<E> nullEntry()
  {
    return (Entry<E>)NULL_ENTRY;
  }

  /**
   * Creates a new entry for the given referent and the given hash code.
   * Depending on whether there's an {@link #internalQueue} or {@link #externalQueue} it calls {@link #newInternalEntry(Object, int)} or {@link #newExternalEntry(Object, int)} respectively.
   */
  protected final Entry<E> newEntry(E object, int hashCode)
  {
    assert hashCode(object) == hashCode;
    return internalQueue != null ? newInternalEntry(object, hashCode) : newExternalEntry(object, hashCode);
  }

  /**
   * Creates a new entry for the given referent and the given hash code and the {@link #internalQueue}.
   */
  protected Entry<E> newInternalEntry(E object, int hashCode)
  {
    return new Entry<E>(object, hashCode, internalQueue);
  }

  /**
   * A specialized external entry managed by the {@link WeakInterningHashSet#externalQueue external queue} that calls {@link #clear()} to remove this entry from its set.
   */
  protected static class SelfCleaningEntry<E> extends Entry<E>
  {
    protected final WeakInterningHashSet<E> set;

    /**
     * Creates an entry for the given set, for the object with the given hash code, managed by the given queue.
     */
    public SelfCleaningEntry(WeakInterningHashSet<E> set, E object, int hashCode, ReferenceQueue<? super E> queue)
    {
      super(object, hashCode, queue);
      this.set = set;
    }

    @Override
    public void clear()
    {
      clear(set);
    }
  }

  /**
   * Creates a new entry for the given referent and the given hash code managed by the {@link #externalQueue}.
   */
  protected Entry<E> newExternalEntry(E object, int hashCode)
  {
    return new SelfCleaningEntry<E>(this, object, hashCode, externalQueue);
  }

  /**
   * Puts the entry into the {@link #entries} linking up the {@link Entry#next chain} for collision handling.
   * @param index
   * @param entry
   */
  protected void putEntry(int index, Entry<E> entry)
  {
    Entry<E> otherEntry = entries[index];
    entries[index] = entry;
    entry.next = otherEntry;
  }

  /**
   * Adds a new entry to the set at the given given index in the {@link #entries}.
   * It {@link #ensureCapacity() ensures} the capacity is sufficient, 
   * increases the {@link #size} of the set, increments the {@link #modCount},
   * and {@link #putEntry(int, Entry) puts} the entry into the set.
   */
  protected void addEntry(int index, Entry<E> entry)
  {
    if (ensureCapacity())
    {
      index = index(entry.hashCode, entries.length);
    }
    ++size;
    ++modCount;
    putEntry(index, entry);
  }

  /**
   * {@link #removeEntry(int, Entry) Remove} an existing entry from the set.
   * If successful, it decreases the {@link #size} of the set and increments the {@link #modCount}.
   */
  protected void removeEntry(Entry<E> entry)
  {
    if (removeEntry(index(entry.hashCode, entries.length), entry))
    {
      --size;
      ++modCount;
    }
  }

  /**
   * Finds the entry at the given index the {@link #entries table} and prune if from the collision chain.
   * Returns whether or not the entry was actually removed.
   */
  protected boolean removeEntry(int index, Entry<E> entry)
  {
    Entry<E> otherEntry = entries[index];
    if (entry == otherEntry)
    {
      entries[index] = entry.next;
      return true;
    }
    else if (otherEntry != null)
    {
      for (Entry<E> nextOtherEntry = otherEntry.next; nextOtherEntry != null; otherEntry = nextOtherEntry, nextOtherEntry = nextOtherEntry.next)
      {
        if (nextOtherEntry == entry)
        {
          otherEntry.next = entry.next;
          return true;
        }
      }
    }

    // We don't generally expect to get here unless an entry that we've removed still ends up being polled form the queue.
    //
    return false;
  }

  /**
   * Dumps information about the current state of the set.
   */
  public void dump()
  {
    int[] collisions = new int[size + 1];
    int maxCollisions = 0;
    System.out.println("size = " + size);
    System.out.println("null = " + containsNull);
    for (int i = 0; i < entries.length; ++i)
    {
      System.out.print(i);
      System.out.print(": ");
      int count = 0;
      for (Entry<E> entry = entries[i]; entry != null; entry = entry.next, ++count)
      {
        System.out.print("(" + entry.hashCode + ", " + entry.get() + ")");
        if (entry.next != null)
        {
          System.out.print(" -> ");
        }
      }
      ++collisions[count];
      if (count > maxCollisions)
      {
        maxCollisions = count;
      }
      System.out.println();
    }
    System.out.print("Collisions % {");
    NumberFormat percentInstance = new DecimalFormat("##0.0000");
    for (int i = 0; i <= maxCollisions; ++i)
    {
      if (i != 0)
      {
        System.out.print(", ");
      }
      System.out.print(percentInstance.format(100.0 * collisions[i] / entries.length));
      System.out.print("% ");
    }
    System.out.println("}");
    System.out.print("Utilization % {");
    for (int i = 1; i <= maxCollisions; ++i)
    {
      if (i != 1)
      {
        System.out.print(", ");
      }
      System.out.print(percentInstance.format(100.0 * i * collisions[i] / size));
      System.out.print("% ");
    }
    System.out.println("}");
  }

  /**
   * Writes this set to the output stream.
   */
  private synchronized void writeObject(ObjectOutputStream objectOutputStream) throws IOException
  {
    objectOutputStream.defaultWriteObject();
    objectOutputStream.writeByte(capacityIndex);
    if (size > 0)
    {
      for (Object object : this)
      {
        objectOutputStream.writeObject(object);
      }
    }
  }

  /**
   * Reads the set from the input stream.
   */
  private synchronized void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException
  {
    objectInputStream.defaultReadObject();
    internalQueue = new ReferenceQueue<E>();
    capacityIndex = objectInputStream.readByte();
    int capacity = PRIME_CAPACITIES[capacityIndex];
    entries = newEntries(capacity);
    threshold = capacity * 3 / 4;
    if (size > 0)
    {
      for (int i = 0; i < size; ++i)
      {
        @SuppressWarnings("unchecked") E object = (E)objectInputStream.readObject();
        if (object == null)
        {
          containsNull = true;
        }
        else
        {
          int hashCode = hashCode(object);
          putEntry(index(hashCode, capacity), newEntry(object, hashCode));
        }
      }
    }
  }
}
