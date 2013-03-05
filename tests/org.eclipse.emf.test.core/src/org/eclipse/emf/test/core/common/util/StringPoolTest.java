/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.core.common.util;


import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.emf.common.util.InterningSet;
import org.eclipse.emf.common.util.Pool;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class StringPoolTest extends TestCase
{
  public StringPoolTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite suite = new TestSuite("StringPoolTest");
    suite.addTest(new StringPoolTest("testWeakness"));
    suite.addTest(new StringPoolTest("testThreadSafetyWithoutCollisions"));
    suite.addTest(new StringPoolTest("testThreadSafetyWithCollisions"));
    suite.addTest(new StringPoolTest("testThreadSafetyWithCollisionsAndGarbageCollection"));
    return suite;
  }

  public void testWeakness()
  {
    InterningSet<String> set = newStringPool();
    int size = set.size();
    {
      String foo = new String("foo");
      set.add(foo);
      assertTrue(set.contains("foo"));
      assertEquals(size + 1, set.size());
      foo = null;
    }
    System.gc();
    assertFalse(set.contains("foo"));
    try
    {
      // If the garbage collector a chance to enqueue the stale entries.
      Thread.sleep(1000);
    }
    catch (InterruptedException e)
    {
      // Expected.
    }
    assertEquals(size, set.size());
  }

  public void testThreadSafetyWithoutCollisions()
  {
    testThreadSafety(1000000, 20, false);
  }

  public void testThreadSafetyWithCollisions()
  {
    testThreadSafety(1000000, 4, false);
  }

  public void testThreadSafetyWithCollisionsAndGarbageCollection()
  {
    testThreadSafety(1000000, 4, true);
  }

  public void testThreadSafety(final int count, int stringSize, boolean isGarbageCollectingWhileInterning)
  {
    // Create random strings.
    //
    final String[] data = new String[count];
    final Random random = new Random(0);
    final Set<String> allStrings = new HashSet<String>();
    for (int i = 0; i < count; ++i)
    {
      char[] characters = new char[stringSize];
      for (int j = 0; j < stringSize; ++j)
      {
        characters[j] = (char)(((0x7FFFFFFF & random.nextInt()) % 26) + 'a');
      }
      allStrings.add(data[i] = new String(characters));
    }

    final InterningSet<String> strings = newStringPool();
    int size = strings.size();
    int expectedSize = size + allStrings.size();

    // Record the interned results to avoid them being garbage collected.
    //
    final String[] dataCopy = isGarbageCollectingWhileInterning ? null : new String[count];

    // Spawn this many threads.
    //
    int threadCount = 10;
    Thread[] threads = new Thread[threadCount];
    for (int i = 0; i < threadCount; ++i)
    {
      threads[i] =
        new Thread()
        {
          @Override
          public void run()
          {
            // Intern all the random strings and store them.
            //
            for (int j = 0; j < count; ++j)
            {
              String value = data[j];
              String internedString = strings.intern(value);
              if (dataCopy != null)
              {
                dataCopy[j] = internedString;
              }
            }
          }
        };
    }

    // Start all the threads.
    //
    for (int i = 0; i < threadCount; ++i)
    {
      threads[i].start();
    }

    // Wait for all threads to finish.
    //
    for (int i = 0; i < threadCount; ++i)
    {
      try
      {
        threads[i].join();
      }
      catch (InterruptedException exception)
      {
        exception.printStackTrace();
        fail("Thread interupted");
      }
    }

    // Test that all the strings are added.
    //
    if (isGarbageCollectingWhileInterning)
    {
      assertTrue(expectedSize >= strings.size());
    }
    else
    {
      assertEquals(expectedSize, strings.size());
    }

    // Clean up references to the strings so they can be garbage collected.
    //
    allStrings.clear();
    for (int i = 0; i < count; ++i)
    {
      data[i] = null;
      if (dataCopy != null)
      {
        dataCopy[i] = null;
      }
    }

    // Do a garbage collection and wait for the cleaner thread to complete.
    //
    System.gc();
    try
    {
      Thread.sleep(10000);
    }
    catch (InterruptedException e)
    {
      // Expected.
    }

    // Test that all the strings have been cleaned up from the pool.
    //
    assertEquals(size, strings.size());
  }

  public static void main(String[] args) throws Exception
  {
    final int count = 10000000;
    final String[] data = new String[count];
    final String[] dataCopy = new String[count];
    final Random random = new Random(0);
    final Set<String> allStrings = new HashSet<String>();
    final int stringSize = 20;
    for (int i = 0; i < count; ++i)
    {
      char[] characters = new char[stringSize];
      for (int j = 0; j < stringSize; ++j)
      {
        characters[j] = (char)(((0x7FFFFFFF & random.nextInt()) % 26) + 'a');
      }
      allStrings.add(data[i] = new String(characters));
    }

      {
        int threadCount = 10;
        Thread[] threads = new Thread[threadCount];

        //final StringPool strings = new StringPool();
        final Pool<String> strings = new Pool<String>();
        class MySet extends WeakHashMap<String, WeakReference<String>>
        {
          public synchronized boolean add(String value)
          {
            if (containsKey(value))
            {
              return false;
            }
            else
            {
              put(value, new WeakReference<String>(value));
              return true;
            }
          }
        }
        final MySet mySet = new MySet();
        mySet.size();
        mySet.add(null);

        for (int i = 0; i < threadCount; ++i)
        {
          threads[i] =
            new Thread()
            {
              @Override
              public void run()
              {
                long start = System.currentTimeMillis();
                int max = 1000000;
                for (int j = 0; j < max; ++j)
                {
                  String value = data[j];
                  if (value == null)
                  {
                    System.err.println("bad value");
                  }
                  dataCopy[j] = strings.intern(value);
                }
                long end = System.currentTimeMillis();
                System.out.println("StringPool   {" + strings.size() + "} elapsed time: " + (end - start));
              }
            };
        }
        for (int i = 0; i < threadCount; ++i)
        {
          threads[i].start();
        }

    System.out.println("Hit enter to terminate");
    System.in.read();

        System.err.println("####?" + strings.size());
        // System.err.println("####?" + new HashSet<String>(strings).size());
      }

    boolean stop = false;

    if (!stop)
      return;

    System.err.println("Basic");
    final int counter = 10000000;

    for (int i = 0; i < 20; ++i)
    {
      new Runnable()
      {
        public void run()
        {
          {
            Set<String> synchronizedAllStrings = Collections.synchronizedSet(allStrings);
            long start = System.currentTimeMillis();
            int dummy = 0;
            for (int j = 0; j < counter; ++j)
            {
              String value = data[j];
              if (synchronizedAllStrings.contains(value))
              {
                ++dummy;
              }
            }
            long end = System.currentTimeMillis();
            System.out.println("HashSet.contains*{" + dummy + "} elapsed time: " + (end - start));
          }
          {
            long start = System.currentTimeMillis();
            int dummy = 0;

            for (int j = 0; j < counter; ++j)
            {
              String value = data[j];
              if (allStrings.contains(value))
              {
                ++dummy;
              }
            }
            long end = System.currentTimeMillis();
            System.out.println("HashSet.contains {" + dummy + "} elapsed time: " + (end - start));
          }
          System.gc();
          {
            long start = System.currentTimeMillis();
            int dummy = 0;
            for (int j = 0; j < counter; ++j)
            {
              dummy += new Object().hashCode();
            }
            long end = System.currentTimeMillis();
            System.out.println("new Object       {" + dummy + "} elapsed time: " + (end - start));
          }
          System.gc();
          {
            long start = System.currentTimeMillis();
            int dummy = 0;
            for (int j = 0; j < counter; ++j)
            {
              dummy += new String[10].hashCode();
            }
            long end = System.currentTimeMillis();
            System.out.println("new String[10]   {" + dummy + "} elapsed time: " + (end - start));
          }
          System.gc();
          {
            long start = System.currentTimeMillis();
            int dummy = 0;
            for (int j = 0; j < counter; ++j)
            {
              dummy += new String(data[j]).hashCode();
            }
            long end = System.currentTimeMillis();
            System.out.println("new String       {" + dummy + "} elapsed time: " + (end - start));
          }
          System.gc();
          {
            AtomicReference<String> atomicReference = new AtomicReference<String>();
            long start = System.currentTimeMillis();
            int dummy = 0;
            for (int j = 0; j < counter; ++j)
            {
              String value;
              for (;;)
              {
                value = atomicReference.get();
                if (atomicReference.compareAndSet(value, ""))
                {
                  break;
                }
              }
              dummy += value == null ? 0 : value.hashCode();
              for (;;)
              {
                if (atomicReference.compareAndSet("", value))
                {
                  break;
                }
              }
            }
            long end = System.currentTimeMillis();
            System.out.println("atomic           {" + dummy + "} elapsed time: " + (end - start));
          }
          System.gc();
          {
            long start = System.currentTimeMillis();
            int dummy = 0;
            for (int j = 0; j < counter; ++j)
            {
              dummy += data[j].hashCode();
            }
            long end = System.currentTimeMillis();
            System.out.println("hash code calls  {" + dummy + "} elapsed time: " + (end - start));
          }
          System.gc();
          {
            long start = System.currentTimeMillis();
            int dummy = 0;
            for (int j = 0; j < counter; ++j)
            {
              CharSequence value = data[j];
              int length = value.length();
              for (int k = 0; k < length; ++k)
              {
                dummy += value.charAt(k);
              }
            }
            long end = System.currentTimeMillis();
            System.out.println("char at          {" + dummy + "} elapsed time: " + (end - start));
          }
          System.gc();
          {
            char[] buffer = new char[stringSize];
            long start = System.currentTimeMillis();
            int dummy = 0;
            for (int j = 0; j < counter; ++j)
            {
              String value = data[j];
              int length = value.length();
              value.getChars(0, length, buffer, 0);

              for (int k = 0; k < length; ++k)
              {
                dummy += buffer[k];
              }
            }
            long end = System.currentTimeMillis();
            System.out.println("getChars         {" + dummy + "} elapsed time: " + (end - start));
          }
        }
      }.run();
    }

    int repeat = 5;
    System.err.println("Interning");
    for (int i = 0; i < repeat; ++i)
    {
      System.gc();
      {
        InterningSet<String> strings = newStringPool();
        long start = System.currentTimeMillis();
        for (int j = 0; j < 1000000; ++j)
        {
          String value = data[j];
          dataCopy[j] = strings.intern(new String(value));
        }
        long end = System.currentTimeMillis();
        System.out.println("StringPool UP{" + strings.size() + "} elapsed time: " + (end - start));
      }
      System.gc();
      {
        Set<String> strings = Collections.synchronizedSet(new HashSet<String>());
        long start = System.currentTimeMillis();
        for (int j = 0; j < 1000000; ++j)
        {
          String value = data[j];
          strings.add(dataCopy[j] = new String(value));
          new String(value);
          // dataCopy[j] = new String(value);
        }
        long end = System.currentTimeMillis();
        System.out.println("HashSet      {" + strings.size() + "} elapsed time: " + (end - start));
      }
      System.gc();
      {
        Map<String, Void> strings = Collections.synchronizedMap(new HashMap<String, Void>());
        long start = System.currentTimeMillis();
        for (int j = 0; j < 1000000; ++j)
        {
          String value = data[j];
          strings.put(dataCopy[j] = new String(value), null);
          new String(value);
          // dataCopy[j] = new String(value);
        }
        long end = System.currentTimeMillis();
        System.out.println("WeakHashMap  {" + strings.size() + "} elapsed time: " + (end - start));
      }
      System.gc();
      {
        // InterningSet<String> strings = new StringPool();
        InterningSet<String> strings = new Pool<String>();
        long start = System.currentTimeMillis();
        for (int j = 0; j < 1000000; ++j)
        {
          String value = data[j];
          dataCopy[j] = strings.intern(new String(value));
        }
        long end = System.currentTimeMillis();
        System.out.println("Pool<String> {" + strings.size() + "} elapsed time: " + (end - start));
      }
      System.gc();
      {
        InterningSet<String> strings = newStringPool();
        long start = System.currentTimeMillis();
        for (int j = 0; j < 1000000; ++j)
        {
          String value = data[j];
          dataCopy[j] = strings.intern(new String(value));
        }
        long end = System.currentTimeMillis();
        System.out.println("StringPool   {" + strings.size() + "} elapsed time: " + (end - start));
      }
    }

    System.err.println("Adding");
    for (int i = 0; i < repeat; ++i)
    {
      System.gc();
      {
        Set<String> strings = Collections.synchronizedSet(new HashSet<String>());
        // Set<String> strings = new HashSet<String>();
        long start = System.currentTimeMillis();
        for (int j = 0; j < 1000000; ++j)
        {
          String value = data[j];
          strings.add(new String(value));
        }
        long end = System.currentTimeMillis();
        System.out.println("HashSet       {" + strings.size() + "} elapsed time: " + (end - start));
      }
      System.gc();
      {
        // InterningSet<String> strings = new StringPool();
        // Set<String> strings = new WeakInterningHashSet<String>();
        Set<String> strings = new Pool<String>();
        long start = System.currentTimeMillis();
        for (int j = 0; j < 1000000; ++j)
        {
          String value = data[j];
          strings.add(new String(value));
        }
        long end = System.currentTimeMillis();
        System.out.println("Pool<String>   {" + strings.size() + "} elapsed time: " + (end - start));
      }
      System.gc();
      {
        Set<String> strings = newStringPool();
        long start = System.currentTimeMillis();
        for (int j = 0; j < 1000000; ++j)
        {
          String value = data[j];
          strings.add(new String(value));
        }
        long end = System.currentTimeMillis();
        System.out.println("StringPool   {" + strings.size() + "} elapsed time: " + (end - start));
      }
    }
  }

  private static final Class<InterningSet<String>> STRING_POOL_CLASS;

  static
  {
    Class<InterningSet<String>> stringPoolClass = null;
    try
    {
      @SuppressWarnings("unchecked")
      Class<InterningSet<String>> result = (Class<InterningSet<String>>)Class.forName("org.eclipse.emf.common.util.CommonUtil$StringPool");
      stringPoolClass = result;
    }
    catch (ClassNotFoundException e)
    {
      fail();
    }
    STRING_POOL_CLASS = stringPoolClass;
  }

  static InterningSet<String> newStringPool()
  {
    try
    {
      Constructor<InterningSet<String>> constructor = STRING_POOL_CLASS.getDeclaredConstructor();
      constructor.setAccessible(true);
      return constructor.newInstance();
    }
    catch (Throwable throwable)
    {
      fail();
      return null;
    }
  }

}
