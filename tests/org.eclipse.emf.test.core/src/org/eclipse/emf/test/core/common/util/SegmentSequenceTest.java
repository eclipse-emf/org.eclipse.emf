/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.core.common.util;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.eclipse.emf.common.util.InterningSet;
import org.eclipse.emf.common.util.SegmentSequence;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;


public class SegmentSequenceTest extends TestCase
{
  public SegmentSequenceTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite suite = new TestSuite("SegmentSequenceTest");
    suite.addTest(new SegmentSequenceTest("test"));
    return suite;
  }
  
  public void test()
  {
    {
      SegmentSequence s1 = SegmentSequence.create("/", "");
      assertEquals(1, s1.segmentCount());
      assertSame("", s1.firstSegment());
      assertSame("", s1.lastSegment());
      assertSame("", s1.segment(0));
      assertEquals("", s1.toString());
      assertEquals("/", s1.delimiter());

      SegmentSequence s2 = SegmentSequence.create("/", "");
      assertSame(s1, s2);
      
      SegmentSequence s3 = SegmentSequence.create("/", new String[] { "" });
      assertSame(s2, s3);
      
      SegmentSequence s4 = SegmentSequence.create("/", new String[0]);
      assertNotSame(s3, s4);
      assertEquals(0, s4.segmentCount());
      assertEquals("", s4.toString());
      
      SegmentSequence s5 = SegmentSequence.create("/", new String[0]);
      assertSame(s4, s5);
      
      SegmentSequence s6 = SegmentSequence.create("/", new String[0]);
      assertSame(s5, s6);
      
      SegmentSequence s7 = SegmentSequence.create("/");
      assertSame(s6, s7);
      
      SegmentSequence s8 = SegmentSequence.create("/").append("");
      assertSame(s3, s8);
    }
    {
      SegmentSequence s1 = SegmentSequence.create("", "");
      assertEquals(0, s1.segmentCount());
      assertEquals(null, s1.firstSegment());
      assertEquals(null, s1.lastSegment());
      assertEquals("", s1.toString());
      assertSame("", s1.delimiter());

      SegmentSequence s2 = SegmentSequence.create("", "");
      assertSame(s1, s2);
      
      SegmentSequence s3 = SegmentSequence.create("", new String[] { "" });
      assertSame(s2, s3);
      
      SegmentSequence s4 = SegmentSequence.create("", new String[0]);
      assertSame(s3, s4);
      
      SegmentSequence s5 = SegmentSequence.create("", new String[0]);
      assertSame(s4, s5);
      
      SegmentSequence s6 = SegmentSequence.create("", new String[0]);
      assertSame(s5, s6);
      
      SegmentSequence s7 = SegmentSequence.create("");
      assertSame(s6, s7);

      SegmentSequence s8 = SegmentSequence.create("").append("");
      assertSame(s7, s8);

      SegmentSequence s9 = SegmentSequence.create("", "", "");
      assertSame(s8, s9);
    }

    {
      SegmentSequence s1 = SegmentSequence.create("/", "/");
      assertEquals(2, s1.segmentCount());
      assertEquals("/", s1.toString());
      assertEquals("/", s1.delimiter());

      SegmentSequence s2 = SegmentSequence.create("/", "/");
      assertSame(s1, s2);

      SegmentSequence s3 = SegmentSequence.create("/", new String[] { "", ""});
      assertSame(s2, s3);

      SegmentSequence s4 = SegmentSequence.create("/", "", "");
      assertSame(s3, s4);

      SegmentSequence s5 = SegmentSequence.create("/").append("").append("");
      assertSame(s4, s5);
    }

    {
      SegmentSequence s1 = SegmentSequence.create("/", "a");
      assertEquals(1, s1.segmentCount());
      assertEquals("a", s1.toString());
      
      SegmentSequence s2 = SegmentSequence.create("/", "a");
      assertSame(s1, s2);
      
      SegmentSequence s3 = SegmentSequence.create("/").append("a");
      assertSame(s2, s3);
      
      SegmentSequence s4 = SegmentSequence.create("/").append("a").append("a");
      assertEquals(2, s4.segmentCount());
      assertSame(s4.segment(0), s4.segment(1));
    }


    {
      SegmentSequence s1 = SegmentSequence.create("/", "/a");
      assertEquals(2, s1.segmentCount());
      assertEquals("/a", s1.toString());

      SegmentSequence s2 = SegmentSequence.create("/", "/a");
      assertSame(s1, s2);

      SegmentSequence s3 = SegmentSequence.create("/").append("/a");
      assertSame(s2, s3);

      SegmentSequence s4 = SegmentSequence.create("/", "").append("a");
      assertSame(s3, s4);
    }

    {
      SegmentSequence s1 = SegmentSequence.create("", "a");
      assertEquals(1, s1.segmentCount());
      assertEquals("a", s1.toString());
      
      SegmentSequence s2 = SegmentSequence.create("", "a");
      assertSame(s1, s2);
      
      SegmentSequence s3 = SegmentSequence.create("").append("a");
      assertSame(s2, s3);
    }

    {
      SegmentSequence s1 = SegmentSequence.create("", "/a");
      assertEquals(1, s1.segmentCount());
      assertEquals("/a", s1.toString());

      SegmentSequence s2 = SegmentSequence.create("", "/a");
      assertSame(s1, s2);

      SegmentSequence s3 = SegmentSequence.create("").append("/a");
      assertSame(s2, s3);

      SegmentSequence s4 = SegmentSequence.create("", "/").append("a");
      assertNotSame(s3, s4);
    }

    {
      SegmentSequence s1 = SegmentSequence.create("/", "/a/b");
      assertEquals(3, s1.segmentCount());
      assertEquals("/a/b", s1.toString());

      SegmentSequence s2 = SegmentSequence.create("/", "").append("a").append("b");
      assertSame(s1, s2);

      SegmentSequence s3 = SegmentSequence.create("/", "", "a", "b");
      assertSame(s2, s3);

      SegmentSequence s4 = SegmentSequence.create("/", "/a").append("b");
      assertSame(s3, s4);
    }

    {
      SegmentSequence s1 = SegmentSequence.create("/", "a", "b", "c");
      assertEquals("a/b/c", s1.toString());

      SegmentSequence s2 = SegmentSequence.create("/", "a", "b", "c", "d");
      assertEquals("a/b/c/d", s2.toString());
      
      assertSame(s1.delimiter(), s2.delimiter());
      assertSame(s1.segment(0), s2.segment(0));
      assertSame(s1.segment(1), s2.segment(1));
      assertSame(s1.segment(2), s2.segment(2));
    }

    {
      SegmentSequence s1 = SegmentSequence.create("/", "x", "y", "z");
      SegmentSequence s2 = SegmentSequence.create("/").append("x/y/z");
      assertSame(s1, s2);

      SegmentSequence s3 = SegmentSequence.create("/").append("x", "y", "z");
      assertSame(s2, s3);
    }

    {
      SegmentSequence s1 = SegmentSequence.create("");
      SegmentSequence s2 = SegmentSequence.create("/");
      assertSame(getSegments(s1), getSegments(s2));

      SegmentSequence s3 = SegmentSequence.create("", "a");
      SegmentSequence s4 = SegmentSequence.create("/").append("a");
      assertSame(getSegments(s3), getSegments(s4));
      
      SegmentSequence s5 = SegmentSequence.create("", "b");
      SegmentSequence s6 = SegmentSequence.create("/", "b");
      assertSame(getSegments(s5), getSegments(s6));

      SegmentSequence s7 = s5.append("c");
      SegmentSequence s8 = s6.append("c");
      assertSame(getSegments(s7), getSegments(s8));
      
      SegmentSequence s9 = s7.append(SegmentSequence.create("", "d", "e"));
      SegmentSequence s10 = s8.append(SegmentSequence.create("/", "d/e"));
      assertEquals("bcde", s9.toString());
      assertEquals("b/c/d/e", s10.toString());
      assertSame(getSegments(s9), getSegments(s10));
    }
  }
  
  private static final Field SEGMENT_SEQUENCE_SEGMENTS;
  static
  {
    Field field = null;
    try
    {
      field = SegmentSequence.class.getDeclaredField("segments");
      field.setAccessible(true);
    }
    catch (Throwable throwable)
    {
      // Ignore
    }
    SEGMENT_SEQUENCE_SEGMENTS = field;
  }

  private String[] getSegments(SegmentSequence segmentSequence)
  {
    try
    {
      return (String[])SEGMENT_SEQUENCE_SEGMENTS.get(segmentSequence);
    }
    catch (Throwable throwable)
    {
      fail("No access to segments");
      return null;
    }
  }
  
  public static void main(String[] args)
  {
    int count = 1000000;
    String[] data = new String[count];
    // String[] dataCopy = new String[count];
    Random random = new Random(0);
    Set<String> allStrings = new HashSet<String>();
    int stringSize = 5;
    for (int i = 0; i < count; ++i)
    {
      char[] characters = new char[stringSize];
      for (int j = 0; j < stringSize; ++j)
      {
        characters[j] = (char)(((0x7FFFFFFF & random.nextInt()) % 26) + 'a');
      }
      allStrings.add(data[i] = new String(characters));
    }

    int segmentCount = 1000000;
    Set<String> segments = new HashSet<String>();
    String[] segmentData = new String[segmentCount];
    SegmentSequence[] segmentDataCopy = new SegmentSequence[segmentCount];
    int segmentSize = 4;
    for (int i = 0; i < segmentCount; ++i)
    {
      StringBuilder builder = new StringBuilder();
      for (int j = 0; j < segmentSize; ++j)
      {
        builder.append("/");
        builder.append(data[((0x7FFFFFFF & random.nextInt()) % count)]);
      }
      segments.add(segmentData[i] = builder.toString());
    }

    int repeat = 20;
    System.err.println("Creating Segments");
    for (int i = 0; i < repeat; ++i)
    {
      System.gc();
      {
        long start = System.currentTimeMillis();
        for (int j = 0; j < segmentCount; ++j)
        {
          String value = segmentData[j];
          segmentDataCopy[j] = SegmentSequence.create("/", value);
        }
        long end = System.currentTimeMillis();
        System.out.println("Pool      {" + POOL.size() + "} elapsed time: " + (end - start));
        if (new HashSet<SegmentSequence>(POOL).size() != POOL.size())
        {
          System.err.println("###" + new HashSet<SegmentSequence>(POOL).size() + " <=>" + POOL.size());
          System.err.println("###" + new HashSet<SegmentSequence>(POOL).size() + " <=>" + POOL.size());
          // POOL.dump();
          System.err.println("###" + new HashSet<SegmentSequence>(POOL));
        }
      }
      System.gc();
      {
        long start = System.currentTimeMillis();
        Set<SegmentSequence> set = new HashSet<SegmentSequence>();
        for (int j = 0; j < segmentCount; ++j)
        {
          SegmentSequence value = segmentDataCopy[j];
          set.add(value);
        }
        long end = System.currentTimeMillis();
        System.out.println("Set       {" + POOL.size() + "} elapsed time: " + (end - start));
      }
      System.gc();
      {
        String[][] arrays = new String[segmentCount][];
        long start = System.currentTimeMillis();
        for (int j = 0; j < segmentCount; ++j)
        {
          String value = segmentData[j];
          arrays[j] = value.split("/");
        }
        long end = System.currentTimeMillis();
        System.out.println("String spl{" + POOL.size() + "} elapsed time: " + (end - start));
      }
    }
  }
  
  private static  final InterningSet<SegmentSequence> POOL;

  static
  {
    InterningSet<SegmentSequence> segmentSequencePool = null;
    try
    {
      @SuppressWarnings("unchecked")
      Class<InterningSet<String>> segmentSequenceClass = (Class<InterningSet<String>>)Class.forName("org.eclipse.emf.common.util.SegmentSequence");
      Field pool = segmentSequenceClass.getDeclaredField("POOL");
      pool.setAccessible(true);
      @SuppressWarnings("unchecked")
      InterningSet<SegmentSequence> result = (InterningSet<SegmentSequence>)pool.get(null);
      segmentSequencePool = result;
    }
    catch (Throwable throwable)
    {
      fail();
    }
    POOL = segmentSequencePool;
  }
}
