/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.core.common.util;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.InterningSet;
import org.eclipse.emf.common.util.WeakInterningHashSet;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class WeakInterningHashSetTest extends TestCase
{
  public WeakInterningHashSetTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite suite = new TestSuite("WeakInterningHashSetTest");
    suite.addTest(new WeakInterningHashSetTest("testBasic"));
    suite.addTest(new WeakInterningHashSetTest("testWeakness"));
    return suite;
  }

  public void testBasic()
  {
    WeakInterningHashSet<String> set = new WeakInterningHashSet<String>();
    set.add("foo");
    assertTrue(set.contains("foo"));
    assertEquals(1, set.size());
    assertEquals("[foo]", set.toString());
    {
      Iterator<String> iterator = set.iterator();
      assertEquals("foo", iterator.next());
      assertFalse(iterator.hasNext());
    }

    set.add(null);
    assertTrue(set.contains(null));
    assertEquals(2, set.size());

    set.remove(null);
    assertFalse(set.contains(null));
    assertEquals(1, set.size());
    
    set.clear();
    assertEquals(0, set.size());
    assertTrue(set.isEmpty());
    assertFalse(set.iterator().hasNext());
    
    set.add("bar");
    {
      Iterator<String> iterator = set.iterator();
      assertEquals("bar", iterator.next());
      iterator.remove();
    }
    assertEquals(0, set.size());
  }

  public void testWeakness()
  {
    WeakInterningHashSet<String> set = new WeakInterningHashSet<String>();
    {
      String foo = new String("foo");
      set.add(foo);
      assertTrue(set.contains("foo"));
      assertEquals(1, set.size());
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
    assertEquals(0, set.size());
  }

  
  @SuppressWarnings("unused")
  public static void main(String[] args) throws IOException, ClassNotFoundException
  {
    Set<Integer> numbers = new HashSet<Integer>();
    if (false)
    {
      int limit = Integer.MAX_VALUE;

      BitSet bitSet = new BitSet(limit);
      for (int i = 2; i < limit / 2; ++i)
      {
        if (!bitSet.get(i))
        {
          // System.out.println(">" + i);
          for (int j = i + i; j < limit && j > 0; j += i)
          {
            bitSet.set(j);
          }
        }
      }

      for (int i = 1; i < limit && i > 0; i <<= 1)
      {
        System.out.print(i);
        System.out.print(" -> ");
        for (int j = i; j < limit && j > 0; ++j)
        {
          if (!bitSet.get(j))
          {
            System.out.println(j);
            break;
          }
        }
      }

      System.out.print("[");
      for (int i = 1; i < limit && i > 0; i <<= 1)
      {
        for (int j = i; j < limit && j > 0; ++j)
        {
          if (!bitSet.get(j))
          {
            System.out.print(j);
            System.out.print(", ");
            break;
          }
        }
      }
      System.out.println("]");

      System.out.println("Largest positive integer prime:");
      for (int j = limit - 1; ; --j)
      {
        if (!bitSet.get(j))
        {
          System.out.println(j);
          break;
        }
      }

      if (true) return;
    }

    WeakInterningHashSet<String> set = new WeakInterningHashSet<String>();
    set.add("abc");
    set.dump();
    set.add("abc");
    set.dump();
    set.add("xyz");
    set.dump();
    set.add(null);
    set.dump();
    String result = set.intern(new String("xyz"));
    set.dump();
    if (result != "xyz")
    {
      System.out.println("BAD");
    }
    set.add(new String("foo"));
    set.dump();
    System.gc();
    set.size();
    set.dump();
    set.add(new String("foo"));
    set.dump();
    set.remove(new String("foo"));
    System.gc();
    set.size();
    set.dump();
    set.add(new String("bar"));
    set.remove(new String("bar"));
    System.gc();
    set.size();
    set.dump();
    set.remove(null);
    set.dump();

    {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
      objectOutputStream.writeObject(set);
      objectOutputStream.close();
      ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
      @SuppressWarnings("unchecked")
      WeakInterningHashSet<String> clone = (WeakInterningHashSet<String>)new ObjectInputStream(in).readObject();
      clone.dump();
    }
    {
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
      objectOutputStream.writeObject(ECollections.synchronizedInterningSet(set));
      objectOutputStream.close();
      ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
      @SuppressWarnings("unchecked")
      InterningSet<String> clone = (InterningSet<String>)new ObjectInputStream(in).readObject();
      System.out.println(clone);
    }

    WeakInterningHashSet<Integer> integers = new WeakInterningHashSet<Integer>();
    integers.add(null);
    Set<Integer> stash = new HashSet<Integer>();
    int count = 1000;
    Random random = new Random(100);
    for (int i = 0; i < count; ++i)
    {
      Integer integer = new Integer(random.nextInt() % (count / 4));
      stash.add(integer);
      if (random.nextBoolean())
      {
        integers.intern(integer);
      }
      else
      {
        integers.remove(integer);
      }
    }
    System.out.println("################");
    integers.dump();
    System.out.println("################");
    stash.clear();
    System.gc();
    integers.size();
    integers.dump();
    System.out.println("################");
    integers.dump();
    System.out.println("################" + integers);
    System.gc();
    System.out.println("################" + integers);


    count = 100000;
    WeakInterningHashSet<String> strings = new WeakInterningHashSet<String>();
    Set<String> stringStash = new HashSet<String>();
    for (int i = 0; i < count; ++i)
    {
      StringBuilder builder = new StringBuilder();
      for (int j = 0, size = (random.nextInt() >>> 1) % 10; j < size; ++j)
      {
        builder.append((char)('a' + (random.nextInt() >>> 1) % 26));
      }
      String string = builder.toString();
      stringStash.add(string);
      if (random.nextBoolean())
      {
        strings.intern(string);
      }
      else
      {
        strings.remove(string);
      }
    }
    System.out.println("################" + strings);
    strings.dump();

    String[] stuff = randomStrings(10000);

    int sum = 0;

    Set<String> hashSet = test1(stuff);
    Set<String> weakInterningHashSet = test2(stuff);
    Set<String> weashHashMapKeySet = test3(stuff);

    for (int x = 0; x < 4; ++x)
    {
      {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; ++i)
        {
          hashSet = test1(stuff);
          sum += hashSet.hashCode();
        }
        long end = System.currentTimeMillis();
        System.out.println("HashSet = " + (end - start));
      }

      {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; ++i)
        {
          weakInterningHashSet = test2(stuff);
          sum += weakInterningHashSet.hashCode();
        }
        long end = System.currentTimeMillis();
        System.out.println("WeakInterningHashSet = " + (end - start));
      }

      {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; ++i)
        {
          weashHashMapKeySet = test3(stuff);
          sum += weashHashMapKeySet.hashCode();
        }
        long end = System.currentTimeMillis();
        System.out.println("WeakHashMapKeySet = " + (end - start));
      }
    }

    for (int x = 0; x < 4; ++x)
    {
      {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; ++i)
        {
          for (String value : stuff)
          {
            if (hashSet.contains(value))
            {
              ++sum;
            }
          }
        }
        long end = System.currentTimeMillis();
        System.out.println("!HashSet = " + (end - start));
      }

      {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; ++i)
        {
          for (String value : stuff)
          {
            if (weakInterningHashSet.contains(value))
            {
              ++sum;
            }
          }
        }
        long end = System.currentTimeMillis();
        System.out.println("!WeakInterningHashSet = " + (end - start));
      }

      {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; ++i)
        {
          for (String value : stuff)
          {
            if (weashHashMapKeySet.contains(value))
            {
              ++sum;
            }
          }
        }
        long end = System.currentTimeMillis();
        System.out.println("!WeakHashMapKeySet = " + (end - start));
      }
    }
    System.out.println("###" + sum);
  }

  public static String[] randomStrings(int count)
  {
    String[] strings = new String[count];
    Random random = new Random();
    for (int i = 0; i < count; ++i)
    {
      StringBuilder builder = new StringBuilder();
      for (int j = 0, size = (random.nextInt() >>> 1) % 10; j < size; ++j)
      {
        builder.append((char)('a' + (random.nextInt() >>> 1) % 26));
      }
      String string = builder.toString();
      strings[i] = string;
    }
    return strings;
  }

  public static Set<String> test1(String[] strings)
  {
    Set<String> set = new HashSet<String>();
    for (int i = 0; i < strings.length; ++i)
    {
      set.add(strings[i]);
    }
    return set;
  }

  public static Set<String> test3(String[] strings)
  {
    Map<String, String> set = new WeakHashMap<String, String>();
    for (int i = 0; i < strings.length; ++i)
    {
      String value = strings[i];
      set.put(value, value);
    }
    return set.keySet();
  }

  public static Set<String> test2(String[] strings)
  {
    Set<String> set = new WeakInterningHashSet<String>();
    for (int i = 0; i < strings.length; ++i)
    {
      set.add(strings[i]);
    }
    return set;
  }
}
  