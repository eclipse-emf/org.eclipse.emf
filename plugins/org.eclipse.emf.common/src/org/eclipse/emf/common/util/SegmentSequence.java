/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.common.util;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/**
 * A memory efficient structure to store a sequence of delimited string segments.
 * A segment sequence can only be created through one the create methods.
 * Pooling ensures that <code>==</code> can be used instead of {@link Object#equals(Object)} to test for equality,
 * i.e., at most one instance can exist for any given delimiter and sequence of segments.
 * The {@link #hashCode() hash code} implementation ensures that the hash code of a segment sequence is identical to the hash code of it's {@link #toString string} representation.
 * In addition, the delimiter and the segments use string and string array pooling.
 * Null segments are not permitted.
 * @since 2.9
 */
public final class SegmentSequence implements CharSequence
{
  /**
   * The instance used to represent no segments.
   */
  static final String[] EMPTY_ARRAY = new String [0];
  
  /**
   * The array containing only the empty string.
   */
  static final String[] EMPTY_STRING_ARRAY = { "" };

  /**
   * The cached pool for the segment sequence instances.
   */
  protected static final SegmentSequencePool POOL = new SegmentSequencePool(CommonUtil.REFERENCE_CLEARING_QUEUE);

  /**
   * The cached pool for string arrays.
   */
  protected static final StringArrayPool STRING_ARRAY_POOL = new StringArrayPool(CommonUtil.REFERENCE_CLEARING_QUEUE);

  /**
   * The cached hash code for this instance.
   */
  protected final int hashCode;

  /**
   * The delimiter for this instance.
   */
  protected final String delimiter;

  /**
   * The segments for this instance.
   */
  protected final String[] segments;

  /**
   * The cached value for {@link #toString()}.
   */
  protected WeakReference<String> toString;

  /**
   * Creates an instance with the given segments and hash code.
   */
  SegmentSequence(String delimiter, String[] segments, int hashCode)
  {
    this.delimiter = delimiter;
    this.segments = segments;
    this.hashCode = hashCode;
  }

  /**
   * Returns an instance with the given additional segments.
   * If this segment sequence's {@link #delimiter() delimiter} is different from that of the argument,
   * the segments of the argument will be split if they contain the delimiter.
   */
  public SegmentSequence append(SegmentSequence segmentSequence)
  {
    // If the segments are empty...
    //
    if (segments == EMPTY_ARRAY)
    {
      // Ensure that the result has the same delimiter...
      //
      if (delimiter != segmentSequence.delimiter)
      {
        // Intern the instance for the delimiter and the argument's segments, split if necessary.
        //
        String[] splitSegments = split(delimiter, segmentSequence.segments, segmentSequence.segments.length);
        return POOL.intern(false, false, delimiter, splitSegments, splitSegments.length);
      }
      else
      {
        // We can just return the argument.
        //
        return segmentSequence;
      }
    }
    else
    {
      if (delimiter != segmentSequence.delimiter)
      {
        // We must compose the segments with the arguments segments.
        //
        String[] splitSegments = split(delimiter, segmentSequence.segments, segmentSequence.segments.length);
        return POOL.intern(hashCode, delimiter, segments, splitSegments, false);
      }
      else
      {
        return POOL.intern(hashCode, delimiter, segments, segmentSequence.segments, false);
        
      }
    }
  }

  /**
   * Returns an instance with the additional segment or segments, if the given segment contains '/'.
   */
  public SegmentSequence append(String segment)
  {
    // If there are no segments...
    //
    if (segments == EMPTY_ARRAY)
    {
      // Create an instance for the segment, including a check with the segment contain delimiters.
      //
      return create(delimiter, segment);
    }
    // If the segment contains the delimiter...
    //
    else if (segment.indexOf(delimiter) != -1)
    {
      // Create a segment sequence for the segment and append that instead.
      //
      return append(create(delimiter, segment));
    }
    else
    {
      // Look in the pool for an instance with this one additional segment included.
      // The pool will create the new instance, if necessary.
      //
      return POOL.intern(hashCode, delimiter, segments, segment);
    }
  }

  public SegmentSequence append(String... segments)
  {
    // If there are no segments...
    //
    if (this.segments == EMPTY_ARRAY)
    {
      // Create an instance for the segment, including a check with the segment contain delimiters.
      //
      return create(delimiter, segments);
    }
    else
    {
      String[] splitSegments = split(delimiter, segments, segments.length);
      return POOL.intern(hashCode, delimiter, this.segments, splitSegments, true);
    }
  }

  /**
   * Returns the number of characters in the {@link #toString() string} representation.
   */
  public int length()
  {
    int segmentCount = segments.length;
    switch (segmentCount)
    {
      case 0:
      {
        // If there are no segments, it's the empty string, so return 0.
        //
        return 0;
      }
      case 1:
      {
        // If there is one segment, it's the length of that one segment.
        //
        return segments[0].length();
      }
      default:
      {
        // If we have a cached string...
        //
        WeakReference<String> toString = this.toString;
        if (toString != null)
        {
          // If it's garbage collected...
          //
          String result = toString.get();
          if (result == null)
          {
            // Clear it now.
            //
            toString.clear();
          }
          else
          {
            // Use the length of the cached result.
            //
            return result.length();
          }
        }

        // Compute the length of the final string.
        //
        int count = delimiter.length() * (segmentCount - 1);
        for (int i = 0; i < segmentCount; i++)
        {
          count += segments[i].length();
        }
        return count;
      }
    }
  }

  /**
   * Returns the specified character in the {@link #toString() string} representation of the sequence.
   */
  public char charAt(int index)
  {
    return toString().charAt(index);
  }

  /**
   * Returns the requested subsequence in the {@ink #toString() string} representation of the sequence.
   */
  public CharSequence subSequence(int start, int end)
  {
    return toString().subSequence(start, end);
  }

  /**
   * Returns the string representation for this sequence,
   * i.e., the concatenation of the {@link #delimiter() delimiter}-separated {@link #segments() segments}.
   */
  @Override
  public String toString()
  {
    int segmentCount = segments.length;
    switch (segmentCount)
    {
      case 0:
      {
        // If there are no segments, return the empty string;
        //
        return "";
      }
      case 1:
      {
        // If there is one segment, we can just return the segment itself.
        //
        return segments[0];
      }
      default:
      {
        // If we don't have a cached result...
        //
        WeakReference<String> toString = this.toString;
        if (toString != null)
        {
          String result = toString.get();
          if (result == null)
          {
            toString.clear();
          }
          else
          {
            return result;
          }
        }

        // Compute the length of the final string.
        //
        int count = delimiter.length() * (segmentCount - 1);
        for (int i = 0; i < segmentCount; i++)
        {
          count += segments[i].length();
        }

        // Allocate a buffer to hold the result characters.
        //
        char[] buffer = new char[count];

        // Copy the first segments characters to the buffer.
        //
        String segment = segments[0];
        int length = segment.length();
        segment.getChars(0, length, buffer, 0);

        // Iterate over the remaining segments...
        //
        for (int i = 1, index = length, delimiterLength = delimiter.length(); i < segmentCount; ++i)
        {
          // Copy the delimiter's characters into the buffer.
          //
          delimiter.getChars(0, delimiterLength, buffer, index);
          index += delimiterLength;

          // Copy the segment's characters into the buffer.
          //
          segment = segments[i];
          length = segment.length();
          segment.getChars(0, length, buffer, index);
          index += length;
        }

        // Intern the resulting characters.
        //
        String result = CommonUtil.intern(buffer, 0, count);

        // Cache the string for reuse on subsequent calls.
        //
        this.toString = POOL.newCachedToString(this, result);

        // Return the cached result.
        //
        return result;
      }
    }
  }

  /**
   * Returns the delimiter of the segment sequence.
   */
  public String delimiter()
  {
    return delimiter;
  }

  /**
   * Returns the segments of this segment sequence.
   */
  public String[] segments()
  {
    return segments.clone();
  }

  /**
   * Returns the segments from the given start.
   */
  public String[] subSegments(int start) 
  {
    return subSegments(start, segments.length);
  }

  /**
   * Returns the segments from the given start and before the given end.
   */
  public String[] subSegments(int start, int end) 
  {
    rangeCheck(segments, start, end);
    int count = end - start;
    String[] result = new String[count];
    System.arraycopy(segments, start, result, 0, count);
    return result;
  }
  
  /**
   * A list that's a read-only view of an array.
   */
  static class UnmodifiableArrayList<E> extends AbstractList<E> implements RandomAccess
  {
    final protected E[] array;

    public UnmodifiableArrayList(E[] array)
    {
      this.array = array;
    }
    
    @Override
    public E get(int index)
    {
      return array[index];
    }

    @Override
    public int size()
    {
      return array.length;
    }
  }

  /**
   * Checks that the start and end are well formed.
   */
  static void rangeCheck(Object[] array, int start, int end)
  {
    if (start < 0) 
    {
      throw new IndexOutOfBoundsException("start=" + start);
    }
    if (start > array.length) 
    {
      throw new IndexOutOfBoundsException("end = " + end);
    }
    if (start > end) 
    {
      throw new IndexOutOfBoundsException("start=" + start + " > " + "end="+ end);
    }
  }

  /**
   * A list that's a read-only view of part of an array.
   */
  static class UnmodifiableArraySubList<E> extends AbstractList<E> implements RandomAccess
  {
    final int start;
    final int end;
    final protected E[] array;
    

    public UnmodifiableArraySubList(E[] array, int start, int end)
    {
      this.start = start;
      this.end = end;
      this.array = array;
    }

    @Override
    public E get(int index)
    {
      if (index < 0 || index > end - start)
      {
        throw new IndexOutOfBoundsException("index=" + index + ", size=" + size());
      }
      return array[start + index];
    }

    @Override
    public int size()
    {
      return end - start;
    }
  }

  /**
   * Returns a read-only list view of the segments.
   */
  public List<String> segmentsList()
  {
    return segments == EMPTY_ARRAY ? Collections.<String>emptyList() : new UnmodifiableArrayList<String>(segments);
  }

  /**
   * Returns a read-only list view of the segments from the given start.
   */
  public List<String> subSegmentsList(int start)
  {
    return subSegmentsList(start, segments.length);
  }

  /**
   * Returns a read-only list view of the segments from the given start and before the given end.
   */
  public List<String> subSegmentsList(int start, int end) 
  {
    rangeCheck(segments, start, end);

    return new UnmodifiableArraySubList<String>(segments, start, end);
  }

  /**
   * Returns the number of segments in the segment sequence.
   */
  public int segmentCount()
  {
    return segments.length;
  }

  /**
   * Returns the segment at the given index of the segment sequence.
   */
  public String segment(int index)
  {
    return segments[index];
  }

  /**
   * Returns the last segment of the segment sequence, or <code>null</code> if there are no segments.
   */
  public String lastSegment()
  {
    return segments == EMPTY_ARRAY ? null : segments[segments.length - 1];
  }

  /**
   * Returns the first segment of the segment sequence, or <code>null</code> if there are no segments.
   */
  public String firstSegment()
  {
    return segments == EMPTY_ARRAY ? null : segments[0];
  }

  /**
   * Although there is an override of hash code, there is no override of {@link #equals(Object)}
   * because the static methods for creation guarantee that there is at most one instance created for each unique segment sequence.
   */
  @Override
  public int hashCode()
  {
    return hashCode;
  }

  /**
   * Returns true if the delimiter is the same as this segment sequence's delimiter and the {@link #toString string representation} of this segment sequence is equal to the given string.
   */
  protected boolean matches(String delimiter, String string)
  {
    // If the delimiters are different, return false.
    //
    if (!this.delimiter.equals(delimiter))
    {
      return false;
    }
    else
    {
      // Consider the three cases: no segments, one segment, and more than one segments...
      //
      int segmentCount = segments.length;
      if (segmentCount == 0)
      {
        // This case can't match, except for the special case of the empty delimiter and the empty string.
        //
        return string.isEmpty() && delimiter.isEmpty();
      }
      else if (segmentCount == 1)
      {
        // The one segment must be equal.
        //
        return segments[0].equals(string);
      }
      else if (toString != null)
      {
        // If there is a cached string with a referent, it must be equal to the argument.
        //
        WeakReference<String> toString = this.toString;
        if (toString != null)
        {
          String stringValue = toString.get();
          if (stringValue == null)
          {
            toString.clear();
          }
          else
          {
            return stringValue.equals(string);
          }
        }
      }

      // We need to match against all the segments, so start with the first.
      //
      String segment = segments[0];

      // If the argument doesn't start with the segment, return false.
      //
      if (!string.startsWith(segment))
      {
        return false;
      }

      // Iterate over all the remaining segments...
      //
      for (int i = 1, index = segment.length(), length = string.length(), delimiterLength = delimiter.length(); i < segmentCount; ++i)
      {
        // If we've gone too far or there isn't a delimiter as expected at the current index, return false.
        //
        if (index >= length || delimiterLength != 0 && !string.startsWith(delimiter, index))
        {
          return false;
        }

        // Skip past the delimiter.
        //
        index += delimiterLength;

        // If the argument doesn't start with the segment at the current index, return false.
        //
        segment = segments[i];
        if (!string.startsWith(segment, index))
        {
          return false;
        }

        // Skip past this segment.
        //
        index += segment.length();
      }

      // The string matches, so given we don't have a cached toString, we may as well cache this one.
      // TODO Maybe we want to cache only interned strings...
      //
      toString = POOL.newCachedToString(this, string);

      // No contradiction was found, so it must match.
      //
      return true;
    }
  }

  /**
   * A specialized pool for caching string arrays.
   */
  protected static class StringArrayPool extends Pool<String[]>
  {
    private static final long serialVersionUID = 1L;

    /**
     * Creates an instance managed by the given queue.
     */
    public StringArrayPool(ReferenceQueue<Object> queue)
    {
      super(1031, new SegmentsAccessUnit.Queue(), queue);

      addEntry(1, newEntry(EMPTY_ARRAY, 1));
      addEntry(31, newEntry(EMPTY_STRING_ARRAY, 31));
      
      segmentsAccessUnits = (SegmentsAccessUnit.Queue)primaryAccessUnits;
    }

    /**
     * Returns the {@link Arrays#hashCode(Object[])}'s value for this object.
     */
    @Override
    protected int hashCode(String[] object)
    {
      return Arrays.hashCode(object);
    }
    
    /**
     * Returns the {@link Arrays#equals(Object[], Object[])}'s value for these two objects object.
     */
    @Override
    protected boolean equals(String[] object, String[] otherObject)
    {
      return Arrays.equals(object, otherObject);
    }
    
    /**
     * Checks whether the object is string array and casts it or return <code>null</code.
     */
    @Override
    protected String[] asInstance(Object object)
    {
      return object instanceof String[] ? (String[])object : null;
    }

    /**
     * A base class for all this pool's access units.
     */
    protected static class AccessUnitBase extends AccessUnit<String[]>
    {
      protected AccessUnitBase(AccessUnit.Queue<String[]> queue)
      {
        super(queue);
      }

      @Override
      protected String[] getValue()
      {
        throw new UnsupportedOperationException();
      }

      @Override
      protected void setValue(String[] value)
      {
        throw new UnsupportedOperationException();
      }

      @Override
      protected boolean setArbitraryValue(Object value)
      {
        throw new UnsupportedOperationException();
      }

      @Override
      protected boolean matches(String[] value)
      {
        throw new UnsupportedOperationException();
      }
    }

    /**
     * Access units for single segment access.
     */
    protected final StringAccessUnit.Queue stringAccessUnits = new StringAccessUnit.Queue();

    /**
     * An access unit for single segment access.
     *
     */
    protected static class StringAccessUnit extends AccessUnitBase
    {
      protected static class Queue extends AccessUnit.Queue<String[]>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public StringAccessUnit pop(boolean isExclusive)
        {
          return (StringAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<String[]> newAccessUnit()
        {
          return new StringAccessUnit(this);
        }
      }

      /**
       * The segment value being accessed.
       */
      protected String value;

      /**
       * Whether the value needs to be interned.
       */
      protected boolean needsInterning;

      /**
       * Creates an instance managed by the given queue.
       */
      protected StringAccessUnit(Queue queue)
      {
        super(queue);
      }

      /**
       * Caches the parameters and computes the hash code.
       * @param value
       * @param needsInterning
       */
      protected void setValue(String value, boolean needsInterning)
      {
        this.value = value;
        this.needsInterning = needsInterning;
        this.hashCode = 31 + value.hashCode();
      }

      @Override
      protected boolean matches(String[] value)
      {
        return value.length == 1 && this.value.equals(value[0]);
      }

      @Override
      public String[] getInternalizedValue()
      {
        return new String [] { needsInterning ? CommonUtil.STRING_POOL.intern(value) : value };
      }
      
      @Override
      public void reset(boolean isExclusive)
      {
        value = null;
        super.reset(isExclusive);
      }
    }

    /**
     * Access units for accessing a single segment that's a substring of a larger string.
     */
    protected final SubstringAccessUnit.Queue substringAccessUnits = new SubstringAccessUnit.Queue();
      
    /**
     * An Access units for accessing a single segment that's a substring of a larger string.
     */
    protected static class SubstringAccessUnit extends AccessUnitBase
    {
      protected static class Queue extends AccessUnit.Queue<String[]>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public SubstringAccessUnit pop(boolean isExclusive)
        {
          return (SubstringAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<String[]> newAccessUnit()
        {
          return new SubstringAccessUnit(this);
        }
      }

      /**
       * The value being accessed.
       */
      protected String value;
      
      /**
       * The offset within the value.
       */
      protected int offset;
      
      /**
       * The number of characters from the offset.
       */
      protected int count;

      /**
       * Creates an instance managed by the given queue.
       */
      protected SubstringAccessUnit(Queue queue)
      {
        super(queue);
      }

      /**
       * Caches the parameters.
       */
      protected void setValue(String value, int offset, int count, int hashCode)
      {
        this.value = value;
        this.offset = offset;
        this.count = count;
        this.hashCode = 31 + hashCode;
      }

      @Override
      protected boolean matches(String[] value)
      {
        return value.length == 1 && this.value.regionMatches(offset, value[0], 0, count);
      }

      @Override
      public String[] getInternalizedValue()
      {
        return new String [] { CommonUtil.STRING_POOL.intern(value, offset, count, hashCode - 31) };
      }

      @Override
      public void reset(boolean isExclusive)
      {
        value = null;
        super.reset(isExclusive);
      }
    }

    /**
     * Access units for accessing segments and one additional segment.
     */
    protected final SegmentsAndSegmentAccessUnit.Queue segmentsAndSegmentAccessUnits = new SegmentsAndSegmentAccessUnit.Queue();
      
    /**
     * An access units for accessing segments and one additional segment.
     */
    protected static class SegmentsAndSegmentAccessUnit extends AccessUnitBase
    {
      protected static class Queue extends AccessUnit.Queue<String[]>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public SegmentsAndSegmentAccessUnit pop(boolean isExclusive)
        {
          return (SegmentsAndSegmentAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<String[]> newAccessUnit()
        {
          return new SegmentsAndSegmentAccessUnit(this);
        }
      }

      /**
       * The segments being accessed.
       */
      protected String[] segments;
      
      /**
       * The number of those segments to include.
       */
      protected int segmentCount;
      
      /**
       * The one additional segment.
       */
      protected String segment;
      
      /**
       * Whether that additional segment needs interning.
       */
      protected boolean needsInterning;

      /**
       * Creates an instance managed by the given queue.
       */
      protected SegmentsAndSegmentAccessUnit(Queue queue)
      {
        super(queue);
      }

      /**
       * Caches the parameters and computes the hash code.
       */
      protected void setValue(String[] segments, int segmentCount, String segment, boolean needsInterning)
      {
        assert segments.length > 0 && segmentCount > 0;
        this.segments = segments;
        this.segmentCount = segmentCount;
        this.segment = segment;
        this.needsInterning = needsInterning;
        int hashCode = 1;
        for (int i = 0; i < segmentCount; ++i)
        {
          String value = segments[i];
          hashCode = 31 * hashCode + value.hashCode();
        }
        this.hashCode = 31 * hashCode + segment.hashCode();
      }

      @Override
      protected boolean matches(String[] value)
      {
        int length = segmentCount;
        if (length + 1 != value.length)
        {
          return false;
        }
        else
        {
          for (int i = 0; i < length; ++i)
          {
            if (value[i] != segments[i])
            {
              return false;
            }
          }

          return needsInterning ? segment.equals(value[length]) : segment == value[length];
        }
      }

      @Override
      public String[] getInternalizedValue()
      {
        int length = segmentCount;
        String[] newSegments = new String [length + 1];
        System.arraycopy(segments, 0, newSegments, 0, length);
        newSegments[length] = needsInterning ? CommonUtil.STRING_POOL.intern(segment) : segment;
        return newSegments;
      }

      @Override
      public void reset(boolean isExclusive)
      {
        segments = null;
        segment = null;
        super.reset(isExclusive);
      }
    }

    /**
     * Access units for accessing subsegments and a substring of one additional segment.
     */
    protected final SegmentsAndSubsegmentAccessUnit.Queue segmentsAndSubsegmentAccessUnits = new SegmentsAndSubsegmentAccessUnit.Queue();
    
    /**
     * An access unit for accessing segments and a substring of one additional segment.
     */
    protected static class SegmentsAndSubsegmentAccessUnit extends AccessUnitBase
    {
      protected static class Queue extends AccessUnit.Queue<String[]>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public SegmentsAndSubsegmentAccessUnit pop(boolean isExclusive)
        {
          return (SegmentsAndSubsegmentAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<String[]> newAccessUnit()
        {
          return new SegmentsAndSubsegmentAccessUnit(this);
        }
      }
      
      /**
       * The segments being accessed.
       */
      protected String[] segments;
      
      /**
       * The number of segments to consider.
       */
      protected int segmentCount;

      /**
       * The one additional segment.
       */
      protected String segment;

      /**
       * The offset within the segment.
       */
      protected int offset;

      /**
       * The number of characters from the offset.
       */
      protected int count;
      
      /**
       * Hash code of character range of the additional segment.
       */
      protected int segmentHashCode;

      /**
       * Creates an instance managed by the given queue.
       */
      protected SegmentsAndSubsegmentAccessUnit(Queue queue)
      {
        super(queue);
      }
      
      /**
       * Caches the parameters and computes the hash code.
       */
      protected void setValue(int hashCode, String[] segments, int segmentCount, String segment, int offset, int count, int segmentHashCode)
      {
        assert segments.length > 0;
        this.segments = segments;
        this.segmentCount = segmentCount;
        this.segment = segment;
        this.offset = offset;
        this.count = count;
        this.segmentHashCode = segmentHashCode;
        this.hashCode = 31 * hashCode + segmentHashCode;
      }

      /**
       * Caches the parameters and computes the hash code.
       */
      protected void setValue(String[] segments, int segmentCount, String segment, int offset, int count, int segmentHashCode)
      {
        assert segments.length > 0;
        this.segments = segments;
        this.segmentCount = segmentCount;
        this.segment = segment;
        this.offset = offset;
        this.count = count;
        this.segmentHashCode = segmentHashCode;
        int hashCode = 1;
        for (int i = 0; i < segmentCount; ++i)
        {
          String value = segments[i];
          hashCode = 31 * hashCode + value.hashCode();
        }
        this.hashCode = 31 * hashCode + segmentHashCode;
      }

      @Override
      protected boolean matches(String[] value)
      {
        int length = segmentCount;
        if (length + 1 != value.length)
        {
          return false;
        }
        else
        {
          for (int i = 0; i < length; ++i)
          {
            if (value[i] != segments[i])
            {
              return false;
            }
          }

          return segment.regionMatches(offset, value[length], 0, count);
        }
      }

      @Override
      public String[] getInternalizedValue()
      {
        int length = segmentCount;
        String[] newSegments = new String [length + 1];
        System.arraycopy(segments, 0, newSegments, 0, length);
        newSegments[length] = CommonUtil.STRING_POOL.intern(segment, offset, count, segmentHashCode);
        return newSegments;
      }

      @Override
      public void reset(boolean isExclusive)
      {
        segments = null;
        segment = null;
        super.reset(isExclusive);
      }
    }

    /**
     * Access units for accessing a range of segments.
     */
    protected final SegmentsAndSegmentCountAccessUnit.Queue segmentsAndSegmentCountAccessUnits = new SegmentsAndSegmentCountAccessUnit.Queue();
    
    /**
     * An Access units for accessing a range of segments.
     */
    protected static class SegmentsAndSegmentCountAccessUnit extends AccessUnitBase
    {
      protected static class Queue extends AccessUnit.Queue<String[]>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public SegmentsAndSegmentCountAccessUnit pop(boolean isExclusive)
        {
          return (SegmentsAndSegmentCountAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<String[]> newAccessUnit()
        {
          return new SegmentsAndSegmentCountAccessUnit(this);
        }
      }

      /**
       * The segments being accessed.
       */
      protected String[] segments;

      /**
       * The offset within the segments.
       */
      protected int offset;

      /**
       * The number of segments from the offset.
       */
      protected int segmentCount;

      protected SegmentsAndSegmentCountAccessUnit(Queue queue)
      {
        super(queue);
      }

      /**
       * Caches the parameters and computes the hash code.
       */
      protected void setValue(String[] segments, int offset, int segmentCount)
      {
        this.segments = segments;
        this.offset = offset;
        this.segmentCount = segmentCount;
        int hashCode = 1;
        for (int i = 0; i < segmentCount; ++i)
        {
          hashCode = 31 * hashCode + segments[offset + i].hashCode();
        }
        this.hashCode = hashCode;
      }

      /**
       * Caches the parameters and computes.
       */
      protected void setValue(String[] segments, int offset, int segmentCount, int hashCode)
      {
        this.segments = segments;
        this.offset = offset;
        this.segmentCount = segmentCount;
        this.hashCode = hashCode;
      }

      @Override
      protected boolean matches(String[] value)
      {
        int length = value.length;
        if (length != segmentCount)
        {
          return false;
        }
        else
        {
          for (int i = 0, offset = this.offset; i < length; ++i)
          {
            if (value[i] != segments[offset + i])
            {
              return false;
            }
          }
          return true;
        }
      }

      @Override
      public String[] getInternalizedValue()
      {
        String[] newSegments = new String [segmentCount];
        System.arraycopy(segments, offset, newSegments, 0, segmentCount);
        return newSegments;
      }

      @Override
      public void reset(boolean isExclusive)
      {
        segments = null;
        super.reset(isExclusive);
      }
    }

    /**
     * Access units for accessing segments that may need copying or interning.
     */
    protected final SegmentsAccessUnit.Queue segmentsAccessUnits;

    /**
     * An Access unit for accessing segments that may need copying or interning.
     */
    protected static final class SegmentsAccessUnit extends AccessUnitBase
    {
      protected static class Queue extends AccessUnit.Queue<String[]>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public SegmentsAccessUnit pop(boolean isExclusive)
        {
          return (SegmentsAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<String[]> newAccessUnit()
        {
          return new SegmentsAccessUnit(this);
        }
      }

      /**
       * Whether the segments need copying when interned.
       */
      protected boolean needsCopying;
      
      /**
       * Whether the strings with in the segments needto be interned.
       */
      protected boolean needsToIntern;
      
      /**
       * The segments being access.
       */
      protected String[] segments;
      
      /**
       * The number of segments to consider.
       */
      protected int count;

      /**
       * Creates an instance managed by the given queue.
       * @param queue
       */
      protected SegmentsAccessUnit(Queue queue)
      {
        super(queue);
      }

      /**
       * Delegates to {@link #setValue(boolean, boolean, String[], int) setValue}<code>(true, true, value, value.length)</code>.
       */
      @Override
      protected void setValue(String[] value)
      {
        setValue(true, true, value, value.length);
      }

      /**
       * Checks of the value is a string array and delegates to {@link #setValue(String[])} if that's the case..
       */
      @Override
      protected boolean setArbitraryValue(Object value)
      {
        if (!(value instanceof String[]))
        {
          return false;
        }
        else
        {
          setValue((String[])value);
          return true;
        }
      }

      /**
       * Caches the parameters and computes the hash code.
       */
      protected void setValue(boolean needsCopying, boolean needsToIntern, String[] segments, int count)
      {
        this.needsCopying = needsCopying;
        this.needsToIntern = needsToIntern;
        this.segments = segments;
        this.count = count;
        int hashCode = 1;
        for (int i = 0; i < count; ++i)
        {
          String value = segments[i];
          hashCode = 31 * hashCode + value.hashCode();
        }
        this.hashCode = hashCode;
      }

      @Override
      protected boolean matches(String[] value)
      {
        int length = value.length;
        if (length != count)
        {
          return false;
        }
        else
        {
          if (needsToIntern)
          {
            for (int i = 0; i < length; ++i)
            {
              if (!value[i].equals(segments[i]))
              {
                return false;
              }
            }
          }
          else
          {
            for (int i = 0; i < length; ++i)
            {
              if (value[i] != segments[i])
              {
                return false;
              }
            }

          }
          return true;
        }
      }

      @Override
      public String[] getInternalizedValue()
      {
        int length = count;
        String[] newSegments = segments;
        if (needsCopying)
        {
          if (length == 0)
          {
            // We always should end up with this special constant for the case of no segments.
            //
            newSegments = EMPTY_ARRAY;
          }
          else
          {
            // Allocate a new array of the right size.
            //
            newSegments = new String [length];

            if (needsToIntern)
            {
              // Intern each segment...
              //
              for (int i = 0; i < length; ++i)
              {
                newSegments[i] = CommonUtil.STRING_POOL.intern(segments[i]);
              }
            }
            else
            {
              // Copy over the already interned segments.
              //
              System.arraycopy(segments, 0, newSegments, 0, length);
            }
          }
        }
        else if (needsToIntern)
        {
          // Intern each segment in the reusable array.
          //
          for (int i = 0; i < length; ++i)
          {
            segments[i] = CommonUtil.STRING_POOL.intern(segments[i]);
          }
        }

        return newSegments;
      }

      @Override
      public void reset(boolean isExclusive)
      {
        segments = null;
        super.reset(isExclusive);
      }
    }

    /**
     * Access units for accessing the composition of two sequences of segments.
     */
    protected final SegmentsAndSegmentsAccessUnit.Queue segmentsAndSegmentsAccessUnits = new SegmentsAndSegmentsAccessUnit.Queue();

    /**
     * An access units for accessing the composition of two sequences of segments.
     */
    protected static class SegmentsAndSegmentsAccessUnit extends AccessUnitBase
    {
      protected static class Queue extends AccessUnit.Queue<String[]>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public SegmentsAndSegmentsAccessUnit pop(boolean isExclusive)
        {
          return (SegmentsAndSegmentsAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<String[]> newAccessUnit()
        {
          return new SegmentsAndSegmentsAccessUnit(this);
        }
      }

      /**
       * The first segments.
       */
      protected String[] segments1;

      /**
       * The first segments.
       */
      protected String[] segments2;
      
      /**
       * Whether the second segment's string need to be interned.
       */
      protected boolean needsToIntern;

      /**
       * Creates an instance managed by the given queue.
       */
      protected SegmentsAndSegmentsAccessUnit(Queue queue)
      {
        super(queue);
      }

      /**
       * Caches the parameters and computes the hash code.
       */
      protected void setValue(String[] segments1, String[] segments2, boolean needsToIntern)
      {
        this.segments1 = segments1;
        this.segments2 = segments2;
        this.needsToIntern = needsToIntern;
        int hashCode = 1;
        for (String value : segments1)
        {
          hashCode = 31 * hashCode + value.hashCode();
        }
        for (String value : segments2)
        {
          hashCode = 31 * hashCode + value.hashCode();
        }
        this.hashCode = hashCode;
      }

      @Override
      protected boolean matches(String[] value)
      {
        int length1 = segments1.length;
        int length2 = segments2.length;
        if (length1 + length2 != value.length)
        {
          return false;
        }
        else
        {
          int i = 0;
          for ( ; i < length1; ++i)
          {
            if (segments1[i] != value[i])
            {
              return false;
            }
          }
          if (needsToIntern)
          {
            for (int j = 0; j < length2; ++j, ++i)
            {
              if (!segments2[j].equals(value[i]))
              {
                return false;
              }
            }
          }
          else 
          {
            for (int j = 0; j < length2; ++j, ++i)
            {
              if (segments2[j] != value[i])
              {
                return false;
              }
            }
          }
          return true;
        }
      }

      @Override
      public String[] getInternalizedValue()
      {
        int length1 = segments1.length;
        int length2 = segments2.length;
        int length = length1 + length2;
        String[] newSegments = new String [length];
        System.arraycopy(segments1, 0, newSegments, 0, length1);
        if (needsToIntern)
        {
          for (int i = 0, j = length1; i < length2; ++i, ++j)
          {
            newSegments[j] = CommonUtil.STRING_POOL.intern(segments2[i]);
          }
        }
        else
        {
          System.arraycopy(segments2, 0, newSegments, length1, length2);
        }
        return newSegments;
      }

      @Override
      public void reset(boolean isExclusive)
      {
        segments1 = null;
        segments2 = null;
        super.reset(isExclusive);
      }
    }

    /**
     * Access units for accessing the composition of two subsequences.
     */
    protected final SubsegmentsAndSubsegmentsAccessUnit.Queue subsegmentsAndSubsegmentsAccessUnits = new SubsegmentsAndSubsegmentsAccessUnit.Queue();
 
    /**
     * An access units for accessing the composition of two subsequences.
     */
    protected static class SubsegmentsAndSubsegmentsAccessUnit extends AccessUnitBase
    {
      protected static class Queue extends AccessUnit.Queue<String[]>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public SubsegmentsAndSubsegmentsAccessUnit pop(boolean isExclusive)
        {
          return (SubsegmentsAndSubsegmentsAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<String[]> newAccessUnit()
        {
          return new SubsegmentsAndSubsegmentsAccessUnit(this);
        }
      }

      /**
       * The first segments.
       */
      protected String[] segments1;
      
      /**
       * The offset within the first segments.
       */
      protected int offset1;
      
      /**
       * The number of segments from the offset of the first segments.
       */
      protected int count1;
      
      /**
       * The second segments.
       */
      protected String[] segments2;
      
      /**
       * The offset within the second segments.
       */
      protected int offset2;
      
      /**
       * The number of segments from the offset of the second segments.
       */
      protected int count2;

      /**
       * Creates an instance managed by the given queue.
       * @param queue
       */
      protected SubsegmentsAndSubsegmentsAccessUnit(Queue queue)
      {
        super(queue);
      }
      
      /**
       * Caches the parameters and computes the hash code.
       */
      protected void setValue(String[] segments1, int offset1, int count1, String[] segments2, int offset2, int count2)
      {
        this.segments1 = segments1;
        this.offset1 = offset1;
        this.count1 = count1;
        this.segments2 = segments2;
        this.offset2 = offset2;
        this.count2 = count2;
        int hashCode = 1;
        for (int i = 0; i < count1; ++i)
        {
          hashCode = 31 * hashCode + segments1[offset1 + i].hashCode();
        }
        for (int i = 0; i < count2; ++i)
        {
          hashCode = 31 * hashCode + segments2[offset2 + i].hashCode();
        }
        this.hashCode = hashCode;
      }

      @Override
      protected boolean matches(String[] value)
      {
        int length1 = count1;
        int length2 = count2;
        if (length1 + length2 != value.length)
        {
          return false;
        }
        else
        {
          int i = 0;
          for (int offset1 = this.offset1; i < length1; ++i)
          {
            if (segments1[offset1 + i] != value[i])
            {
              return false;
            }
          }
          for (int j = 0, offset2 = this.offset2; j < length2; ++j, ++i)
          {
            if (segments2[offset2 + j] != value[i])
            {
              return false;
            }
          }
          return true;
        }
      }

      @Override
      public String[] getInternalizedValue()
      {
        int length1 = count1;
        int length2 = count2;
        int length = length1 + length2;
        String[] newSegments = new String [length];
        System.arraycopy(segments1, offset1, newSegments, 0, length1);
        System.arraycopy(segments2, offset2, newSegments, length1, length2);
        return newSegments;
      }

      @Override
      public void reset(boolean isExclusive)
      {
        segments1 = null;
        segments2 = null;
        super.reset(isExclusive);
      }
    }

    /**
     * Intern a single segment.
     */
    protected String[] intern(String segment, boolean needsInterning)
    {
      StringAccessUnit accessUnit = stringAccessUnits.pop(false);
      accessUnit.setValue(segment, needsInterning);
      return doIntern(false, accessUnit);
    }

    /**
     * Intern a single segment that's a substring of a larger string.
     */
    protected String[] intern(String segment, int offset, int count, int hashCode)
    {
      SubstringAccessUnit accessUnit = substringAccessUnits.pop(false);
      accessUnit.setValue(segment, offset, count, hashCode);
      return doIntern(false, accessUnit);
    }

    /**
     * Intern segments and one additional segment.
     */
    protected String[] intern(String[] segments, int segmentCount, String segment, boolean needsInterning)
    {
      if (segmentCount == 0)
      {
        return intern(segment, needsInterning);
      }
      else
      {
        SegmentsAndSegmentAccessUnit accessUnit = segmentsAndSegmentAccessUnits.pop(false);
        accessUnit.setValue(segments, segmentCount, segment, needsInterning);
        return doIntern(false, accessUnit);
      }
    }

    /**
     * Intern segments and a substring of one additional segment.
     */
    protected String[] intern(int hashCode, String[] segments, int segmentCount, String segment, int offset, int count, int segmentHashCode)
    {
      if (segments.length == 0)
      {
        return intern(segment, offset, count, segmentHashCode);
        
      }
      else
      {
        SegmentsAndSubsegmentAccessUnit accessUnit = segmentsAndSubsegmentAccessUnits.pop(false);
        accessUnit.setValue(hashCode, segments, segmentCount, segment, offset, count, segmentHashCode);
        return doIntern(false, accessUnit);
      }
    }
 
    /**
     * Intern subsegments and a substring of one additional segment.
     */
    protected String[] intern(String[] segments, int segmentCount, String segment, int offset, int count, int segmentHashCode)
    {
      if (segments.length == 0)
      {
        return intern(segment, offset, count, segmentHashCode);
        
      }
      else
      {
        SegmentsAndSubsegmentAccessUnit accessUnit = segmentsAndSubsegmentAccessUnits.pop(false);
        accessUnit.setValue(segments, segmentCount, segment, offset, count, segmentHashCode);
        return doIntern(false, accessUnit);
      }
    }

    /**
     * Intern a range of segments.
     */
    protected String[] intern(String[] segments, int offset, int segmentCount)
    {
      SegmentsAndSegmentCountAccessUnit accessUnit = segmentsAndSegmentCountAccessUnits.pop(false);
      accessUnit.setValue(segments, offset, segmentCount);
      return doIntern(false, accessUnit);
    }

    /**
     * Intern a range of segments.
     */
    protected String[] intern(String[] segments, int offset, int segmentCount, int hashCode)
    {
      SegmentsAndSegmentCountAccessUnit accessUnit = segmentsAndSegmentCountAccessUnits.pop(false);
      accessUnit.setValue(segments, offset, segmentCount, hashCode);
      return doIntern(false, accessUnit);
    }

    /**
     * Intern segments that may need copying or interning.
     */
    protected String[] intern(boolean needsCopying, boolean needsToIntern, String[] segments, int count)
    {
      if (segments == null)
      {
        return EMPTY_ARRAY;
      }
      else
      {
        SegmentsAccessUnit accessUnit = segmentsAccessUnits.pop(false);
        accessUnit.setValue(needsCopying, needsToIntern, segments, count);
        return doIntern(false, accessUnit);
      }
    }

    /**
     * Intern the composition of two sequences of segments.
     */
    protected String[] intern(String[] segments1, String[] segments2, boolean needsToIntern)
    {
      SegmentsAndSegmentsAccessUnit accessUnit = segmentsAndSegmentsAccessUnits.pop(false);
      accessUnit.setValue(segments1, segments2, needsToIntern);
      return doIntern(false, accessUnit);
    }

   /**
    * Intern the composition of two subsequences.
    */
    protected String[] intern(String[] segments1, int offset1, int count1, String[] segments2, int offset2, int count2)
    {
      SubsegmentsAndSubsegmentsAccessUnit accessUnit = subsegmentsAndSubsegmentsAccessUnits.pop(false);
      accessUnit.setValue(segments1, offset1, count1, segments2, offset2, count2);
      return doIntern(false, accessUnit);
    }
  }

  /**
   * A cached pool of weakly referenced segment sequences.
   * There are several ways of interning instances, all of which seek to avoid creating new objects whenever possible.
   * Note that we've carefully ensured that a segment sequence's hash code is the same as that of its string representation.
   * This is exploited to allow fast lookup of strings against their already segmented representation.
   */
  protected static class SegmentSequencePool extends Pool<SegmentSequence>
  {
    private static final long serialVersionUID = 1L;

    /**
     * A base class for all this pool's access units.
     */
    protected static class AccessUnitBase extends AccessUnit<SegmentSequence>
    {
      protected AccessUnitBase(Pool.AccessUnit.Queue<SegmentSequence> queue)
      {
        super(queue);
      }

      @Override
      protected SegmentSequence getValue()
      {
        throw new UnsupportedOperationException();
      }

      @Override
      protected void setValue(SegmentSequence value)
      {
        throw new UnsupportedOperationException();
      }

      @Override
      protected boolean setArbitraryValue(Object value)
      {
        throw new UnsupportedOperationException();
      }

      /**
       * A helper utility for computing the hash code starting with a base hash code and taking the given segments into account.
       * It emulates the computation done by {@link String#hashCode()}.
       */
      protected static int hashCode(int initialHashCode, int delimiterHashCode, int delimiterPowerOf31, String[] segments, int length)
      {
        // The hash code for no segments is just the initial hash code.
        //
        if (length == 0)
        {
          return initialHashCode;
        }
        else
        {
          // Factor in the first segment's hash code contribution.
          //
          String segment = segments[0];
          int hashCode = (initialHashCode == 0 ? 0 : initialHashCode * CommonUtil.powerOf31(segment.length())) + segment.hashCode();

          // Iterate over the remaining segments.
          //
          for (int i = 1; i < length; ++i)
          {
            // Factor in the delimiter's hash code contribution.
            //
            hashCode = (hashCode * delimiterPowerOf31) + delimiterHashCode;

            // Factor in the segment's hash code contribution.
            //
            segment = segments[i];
            hashCode = (hashCode * CommonUtil.powerOf31(segment.length())) + segment.hashCode();
          }

          return hashCode;
        }
      }
    }

    /**
     * Access units for basic string access.
     */
    protected final StringAccessUnit.Queue stringAccessUnits = new StringAccessUnit.Queue();
    
    /**
     * An access unit for basic string access.
     */
    protected static class StringAccessUnit extends AccessUnitBase
    {
      protected static class Queue extends AccessUnit.Queue<SegmentSequence>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public StringAccessUnit pop(boolean isExclusive)
        {
          return (StringAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<SegmentSequence> newAccessUnit()
        {
          return new StringAccessUnit(this);
        }
      }

      /**
       * A buffer for character level processing; it grows as needed.
       */
      protected char[] buffer = new char [100];

      /**
       * A buffer to hold the delimiter's characters; it grows as needed.
       */
      protected char[] delimiterBuffer = new char [2];

      /**
       * A buffer for building up string segments; it grows as needed.
       */
      protected String[] segmentBuffer = new String [100];

      /**
       * The delimiter of the value being accessed.
       */
      protected String delimiter;

      /**
       * The value being access.
       */
      protected String string;

      /**
       * Create an instance managed by the given queue.
       * @param queue
       */
      protected StringAccessUnit(Queue queue)
      {
        super(queue);
      }
      
      /**
       * Records the parameters and computes the hash code.
       */
      protected void setValue(String delimiter, String value)
      {
        this.delimiter = delimiter;
        this.string = value;
        this.hashCode = value.hashCode();
      }

      @Override
      public SegmentSequence getInternalizedValue()
      {
        // We need to split the string
        //
        int delimiterLength = delimiter.length();
        if (delimiterLength == 0)
        {
          if (string.isEmpty())
          {
            return new SegmentSequence(delimiter, EMPTY_ARRAY, hashCode);
          }
          else
          {
            // Create an array with the one interned segment, create and create segment sequence from it.
            //
            return new SegmentSequence(delimiter, STRING_ARRAY_POOL.intern(string, true), hashCode);
          }
        }
        else
        {
          // Prepare the buffer to hold enough characters.
          //
          int length = string.length();
          if (buffer.length < length)
          {
            buffer = new char [length];
          }

          // Prepare the buffer for the delimiter characters.
          //
          if (delimiterBuffer.length < delimiterLength)
          {
            delimiterBuffer = new char[delimiterLength];
          }

          // Fill the buffers with the characters; this avoids so many calls to charAt!
          //
          string.getChars(0, length, buffer, 0);
          delimiter.getChars(0, delimiterLength, delimiterBuffer, 0);

          // Keep track of how many segments we've slit out.
          //
          int segmentCount = 0;

          // Compute the hash code of each segment while we're already looking at each character anyway.
          //
          int segmentHashCode = 0;

          // Remember the starting index of the current segment.
          //
          int start = 0;

          // Prepare to match the first delimiter character.
          //
          char delimiterChar = delimiterBuffer[0];

          // Iterate over all the characters in the buffer.
          //
          for (int i = start, delimiterIndex = 0; i < length; ++i)
          {
            // If match we match the current delimiter character...
            //
            char c = buffer[i];
            if (c == delimiterChar)
            {
              // If we've matched the whole delimiter...
              //
              if (++delimiterIndex == delimiterLength)
              {
                // Ensure that our segment buffer is big enough.
                //
                ensureSegmentCapacity(segmentCount);

                // Put the interned segment into the segment buffer.
                // Note that we do this without needing to create a string.
                //
                segmentBuffer[segmentCount++] = CommonUtil.STRING_POOL.intern(buffer, start, i - start, segmentHashCode);

                // Mark the start of the next segment.
                //
                start = i + 1;

                // Reset the hash code for the segment.
                //
                segmentHashCode = 0;

                // Reset the delimiter index and delimiter character.
                //
                delimiterIndex = 0;
                delimiterChar = delimiterBuffer[0];

                // Continue at the start of the loop.
                //
                continue;
              }

              // Prepare to match the next delimiter character.
              //
              delimiterChar = delimiterBuffer[delimiterIndex];
            }

            // Compose the segment's hash code.
            //
            segmentHashCode = 31 * segmentHashCode + c;
          }

          // Add the interned final segment to the segment buffer as well.
          //
          ensureSegmentCapacity(segmentCount);
          segmentBuffer[segmentCount++] = CommonUtil.STRING_POOL.intern(buffer, start, length - start, segmentHashCode);

          // Allocate the new segments, fill them, and create an instance from that.
          // Note that there will always be at least one segment to store the value.
          //
          SegmentSequence segmentSequence = new SegmentSequence(delimiter, STRING_ARRAY_POOL.intern(segmentBuffer, 0, segmentCount), hashCode);
          if (segmentCount > 1)
          {
            // Cache the toString if it would need to be computed later.
            // TODO we may want to intern it.
            //
            segmentSequence.toString = POOL.newCachedToString(segmentSequence, string);
          }
          return segmentSequence;
        }
      }

      @Override
      protected boolean matches(SegmentSequence value)
      {
        // If the segment sequence of the entry matches the argument, return it.
        //
        return value.matches(delimiter, string);
      }

      /**
       * Ensures that the segment buffer is give enough to hold the given number of segments.
       */
      protected void ensureSegmentCapacity(int segmentCount)
      {
        if (segmentCount >= segmentBuffer.length)
        {
          // Create a new one copying over the existing contents.
          //
          String[] oldSegments = segmentBuffer;
          segmentBuffer = new String [2 * segmentBuffer.length];
          System.arraycopy(oldSegments, 0, segmentBuffer, 0, segmentCount);
        }
      }

      @Override
      public void reset(boolean isExclusive)
      {
        delimiter = null;
        string = null;
        super.reset(isExclusive);
      }
    }

    /**
     * Access units for accessing a delimiter and segments.
     */
    protected final SegmentsAccessUnit.Queue segmentsAccessUnits = new SegmentsAccessUnit.Queue();
    
    /**
     * An access unit for accessing a delimiter and segments.
     */
    protected static class SegmentsAccessUnit extends AccessUnitBase
    {
      protected static class Queue extends AccessUnit.Queue<SegmentSequence>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public SegmentsAccessUnit pop(boolean isExclusive)
        {
          return (SegmentsAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<SegmentSequence> newAccessUnit()
        {
          return new SegmentsAccessUnit(this);
        }
      }

      /**
       * Whether the segments need copying when interned.
       */
      protected boolean needsCopying;
      
      /**
       * Whether the string sin the segments need interning.
       */
      protected boolean needsToIntern;
      
      /**
       * The delimiter being accessed.
       */
      protected String delimiter;

      /**
       * The segments being accessed.
       */
      protected String[] segments;
      
      /**
       * The number of segments to consider.
       */
      protected int count;

      protected SegmentsAccessUnit(Queue queue)
      {
        super(queue);
      }

      /**
       * Caches the arguments the computes the hash code.
       */
      protected void setValue(boolean needsCopying, boolean needsToIntern, String delimiter, String[] segments, int count)
      {
        this.needsCopying = needsCopying;
        this.needsToIntern = needsToIntern;
        this.delimiter = delimiter;
        this.segments = segments;
        this.count = count;
        hashCode = hashCode(0, delimiter.hashCode(), CommonUtil.powerOf31(delimiter.length()), segments, count);
      }

      @Override
      public SegmentSequence getInternalizedValue()
      {
        return new SegmentSequence(delimiter, STRING_ARRAY_POOL.intern(needsCopying, needsToIntern, segments, count), hashCode);
      }

      @Override
      protected boolean matches(SegmentSequence value)
      {
        // If the delimiter and segments are the same, return this instance.
        //
        return delimiter.equals(value.delimiter) && equals(segments, count, value.segments);
      }

      /**
       * Returns true if the arrays are of the same length and have equal strings.
       */
      protected static boolean equals(String[] segments1, int length, String[] segments2)
      {
        if (segments2.length != length)
        {
          return false;
        }

        for (int i = 0; i < length; i++)
        {
          if (!segments1[i].equals(segments2[i]))
          {
            return false;
          }
        }

        return true;
      }

      @Override
      public void reset(boolean isExclusive)
      {
        delimiter = null;
        segments = null;
        super.reset(isExclusive);
      }
    }

    /**
     * Access units for accessing segments and one additional segment.
     */
    protected final SegmentsAndSegmentAccessUnit.Queue segmentsAndSegmentAccessUnits = new SegmentsAndSegmentAccessUnit.Queue();

    /**
     * An access unit for accessing segments and one additional segment.
     */
    protected static class SegmentsAndSegmentAccessUnit extends AccessUnitBase
    {
      protected static class Queue extends AccessUnit.Queue<SegmentSequence>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public SegmentsAndSegmentAccessUnit pop(boolean isExclusive)
        {
          return (SegmentsAndSegmentAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<SegmentSequence> newAccessUnit()
        {
          return new SegmentsAndSegmentAccessUnit(this);
        }
      }

      /**
       * The delimiters being accessed.
       */
      protected String delimiter;
      
      /**
       * The segments being accessed.
       */
      protected String[] segments;
      
      /**
       * The one additional segment being accessed
       */
      protected String segment;
      
      /**
       * Creates an instance managed by the given queue.
       * @param queue
       */
      protected SegmentsAndSegmentAccessUnit(Queue queue)
      {
        super(queue);
      }

      /**
       * Caches the parameters and computes the hash code.
       */
      protected void setValue(int hashCode, String delimiter, String[] segments, String segment)
      {
        this.delimiter = delimiter;
        this.segments = segments;
        this.segment = segment;

        // Factor in the additional delimiter and segment hash code contributions.
        //
        hashCode = hashCode * CommonUtil.powerOf31(delimiter.length()) + delimiter.hashCode();
        hashCode = hashCode * CommonUtil.powerOf31(segment.length()) + segment.hashCode();
        this.hashCode = hashCode;
      }

      @Override
      public SegmentSequence getInternalizedValue()
      {
        return new SegmentSequence(delimiter, STRING_ARRAY_POOL.intern(segments, segments.length, segment, true), hashCode);
      }

      @Override
      protected boolean matches(SegmentSequence value)
      {
        // If the delimiters match...
        //
        if (delimiter.equals(delimiter))
        {
          // If the segment count matches...
          //
          String[] entrySegments = value.segments;
          int length = segments.length;
          if (length + 1 == entrySegments.length)
          {
            // If the segments are not identical strings---remember, they're all interned, so we can use == to test them---then return false.
            //
            int i = 0;
            for (; i < length; ++i)
            {
              if (entrySegments[i] != segments[i])
              {
                return false;
              }
            }

            // If the last segment of the entry matches the additional segment, we've found our result, so return true.
            //
            if (segment.equals(entrySegments[i]))
            {
              return true;
            }
          }
        }
        return false;
      }

      @Override
      public void reset(boolean isExclusive)
      {
        delimiter = null;
        segments = null;
        segment = null;
        super.reset(isExclusive);
      }
    }

    /**
     * Access units for accessing the composition of two sequences of segments.
     */
    protected final SegmentsAndSegmentsAccessUnit.Queue segmentsAndSegmentsAccessUnits = new SegmentsAndSegmentsAccessUnit.Queue();

    /**
     * An access units for accessing the composition of two sets of segments.
     */
    protected static class SegmentsAndSegmentsAccessUnit extends AccessUnitBase
    {
      protected static class Queue extends AccessUnit.Queue<SegmentSequence>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public SegmentsAndSegmentsAccessUnit pop(boolean isExclusive)
        {
          return (SegmentsAndSegmentsAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<SegmentSequence> newAccessUnit()
        {
          return new SegmentsAndSegmentsAccessUnit(this);
        }
      }

      /**
       * The delimiter being accessed.
       */
      protected String delimiter;

      /**
       * The first segments being accessed.
       */
      protected String[] segments1;

      /**
       * The second segments being accessed.
       */
      protected String[] segments2;
      
      /**
       * Whether the strings in the second segments need interning.
       */
      protected boolean needsToIntern;

      protected SegmentsAndSegmentsAccessUnit(Queue queue)
      {
        super(queue);
      }
      
      /**
       * Caches the parameters and computes the hash code.
       */
      protected void setValue(int hashCode, String delimiter, String[] segments1, String[] segments2, boolean needsToIntern)
      {
        this.delimiter = delimiter;
        this.segments1 = segments1;
        this.segments2 = segments2;
        this.needsToIntern = needsToIntern;

        if (segments2.length > 0)
        {
          // Factor in the additional hash code contributions of one more separator and the additional segments.
          //
          int delimiterHashCode = delimiter.hashCode();
          int delimiterPowerOf31 = CommonUtil.powerOf31(delimiter.length());
          hashCode = (hashCode * delimiterPowerOf31) + delimiterHashCode;
          hashCode = hashCode(hashCode, delimiterHashCode, delimiterPowerOf31, segments2, segments2.length);
        }
        this.hashCode = hashCode;
      }

      @Override
      public SegmentSequence getInternalizedValue()
      {
        return new SegmentSequence(delimiter, STRING_ARRAY_POOL.intern(segments1, segments2, needsToIntern), hashCode);
      }

      @Override
      protected boolean matches(SegmentSequence value)
      {
        // If the delimiters match...
        //
        if (delimiter.equals(value.delimiter))
        {
          int length1 = segments1.length;
          int length2 = segments2.length;
          int length = length1 + length2;

          // If the entry segment count matches...
          //
          String[] entrySegments = value.segments;
          if (length == entrySegments.length)
          {
            // If the leading segments are not identical strings---remember, they're all interned, so we can use == to compare them---return false;
            //
            int i = 0;
            for (; i < length1; ++i)
            {
              if (entrySegments[i] != segments1[i])
              {
                return false;
              }
            }

            if (needsToIntern)
            {
              for (int j = 0; j < length2; ++i, ++j)
              {
                if (!entrySegments[i].equals(segments2[j]))
                {
                  return false;
                }
              }
            }
            else
            {
              // If the remaining segments are not identical strings, return false;
              //
              for (int j = 0; j < length2; ++i, ++j)
              {
                if (entrySegments[i] != segments2[j])
                {
                  return false;
                }
              }
            }

            // Otherwise it's a match, so return true.
            //
            return true;
          }
          else
          {
            return false;
          }
        }
        else
        {
          return false;
        }
      }

      @Override
      public void reset(boolean isExclusive)
      {
        delimiter = null;
        segments1 = null;
        segments2 = null;
        super.reset(isExclusive);
      }
    }

    /**
     * A reference queue for internally managing the collection of cached {@link SegmentSequence#toString}s.
     */
    protected final ReferenceQueue<String> cachedToStrings;

    /**
     * Creates an instance managed the given external queue.
     * If it's <codee>null</code>, and internal queue will be used to managed the cached strings.
     */
    public SegmentSequencePool(ReferenceQueue<Object> queue)
    {
      super(1031, null, queue);
      cachedToStrings = externalQueue == null ? new ReferenceQueue<String>() : null;
    }

    /**
     * Specialized to also clean up the {@link #cachedToStrings}.
     */
    @Override
    protected void doCleanup()
    {
      super.doCleanup();

      for (;;)
      {
        Reference<? extends String> cachedToString = cachedToStrings.poll();
        if (cachedToString == null)
        {
          return;
        }
        else
        {
          // A cached string's knows from which SegmentSequence to remove itself.
          //
          cachedToString.clear();
        }
      }
    }

    /**
     * A weak reference for {@link SegmentSequence#toString caching} a segment sequence's {@link SegmentSequence#toString() string} representation.
     */
    protected static class CachedToString extends WeakReference<String>
    {
      protected final SegmentSequence segmentSequence;
      public CachedToString(SegmentSequence segmentSequence, String string, ReferenceQueue<? super String> queue)
      {
        super(string, queue);
        this.segmentSequence = segmentSequence;
      }

      @Override
      public void clear()
      {
        segmentSequence.toString = null;
        super.clear();
      }
    }

    /**
     * Creates a new weak reference managed by the appropriate {@link #externalQueue external} or {@link #cachedToStrings internal} queue.
     * @param segmentSequence
     * @param string
     * @return
     */
    protected WeakReference<String> newCachedToString(SegmentSequence segmentSequence, String string)
    {
      return 
        cachedToStrings == null ?
          new CachedToString(segmentSequence, string, externalQueue) :
          new CachedToString(segmentSequence, string, cachedToStrings);
    }

    /**
     * Interns the string representation of a segment sequence, splitting it into appropriate segments based on the delimiter.
     */
    protected SegmentSequence intern(String delimiter, String value)
    {
      // Null isn't stored in the pool.
      //
      if (value == null)
      {
        return null;
      }
      else
      {
        // Retrieve an access unit for exclusive use in this call for the current thread thread.
        //
        StringAccessUnit accessUnit = stringAccessUnits.pop(false);
        accessUnit.setValue(delimiter, value);
        return doIntern(false, accessUnit);
      }
    }

    /**
     * Interns the segments, taking into account the delimiter,
     * copying the array, when needed,
     * and interning the segments themselves, when needed.
     */
    protected SegmentSequence intern(boolean needsCopying, boolean needsToIntern, String delimiter, String[] segments, int count)
    {
      // Retrieve an access unit for exclusive use in this call for the current thread thread.
      //
      SegmentsAccessUnit accessUnit = segmentsAccessUnits.pop(false);
      accessUnit.setValue(needsCopying, needsToIntern, delimiter, segments, count);
      return doIntern(false, accessUnit);
    }

    /**
     * Interns the segments along with the one additional segment, taking into account the delimiter and interning the additional segment. 
     * All callers of this method will already have interned the segments.
     */
    protected SegmentSequence intern(int hashCode, String delimiter, String[] segments, String segment)
    {
      // Retrieve an access unit for exclusive use in this call for the current thread thread.
      //
      SegmentsAndSegmentAccessUnit accessUnit = segmentsAndSegmentAccessUnits.pop(false);
      accessUnit.setValue(hashCode, delimiter, segments, segment);
      return doIntern(false, accessUnit);
    }

    /**
     * Interns the composed segments, taking into account the delimiter. 
     * All callers of this method will already have interned the segments.
     */
    protected SegmentSequence intern(int hashCode, String delimiter, String[] segments1, String[] segments2, boolean needsToIntern)
    {
      // Retrieve an access unit for exclusive use in this call for the current thread thread.
      //
      SegmentsAndSegmentsAccessUnit accessUnit = segmentsAndSegmentsAccessUnits.pop(false);
      accessUnit.setValue(hashCode, delimiter, segments1, segments2, needsToIntern);
      return doIntern(false, accessUnit);
    }

  }

  /**
   * Returns the segment sequence for the value, splitting it into segments as appropriate, or <code>null</code> if the value is null.
   */
  public static SegmentSequence create(String delimiter, String value)
  {
    return POOL.intern(delimiter, value);
  }

  /**
   * Returns the empty segment sequence for the given delimiter.
   */
  public static SegmentSequence create(String delimiter)
  {
    return POOL.intern(false, false, delimiter, EMPTY_ARRAY, 0);
  }

  /**
   * Returns the segment sequence for the given segments,
   * splitting individual segments as necessary, i.e., if they contain the delimiter character,
   * or omitting empty segments in the case of the empty delimiter.
   * @throws NullPointerException if the segments itself or any individual segment among the segments is <code>null</code>.
   */
  public static SegmentSequence create(String delimiter, String... segments)
  {
    String[] splitSegments = split(delimiter, segments, segments.length);
    return POOL.intern(segments == splitSegments && segments != EMPTY_ARRAY, true, delimiter, splitSegments, splitSegments.length);
  }

  protected static SegmentSequence create(String delimiter, String[] segments, int count)
  {
    String[] splitSegments = split(delimiter, segments, count);
    return POOL.intern(segments == splitSegments, true, delimiter, splitSegments, count);
  }

  /**
   * Splits individual segments as necessary, i.e., if they contain the delimiter character,
   * or omits empty segments in the case of the empty delimiter.
   * If no segment contains the delimiter, or in the case of the empty delimiter, no segments are empty, the original argument is returned.
   * @throws NullPointerException if the segments itself or any individual segments among the segments is <code>null</code>.
   */
  protected static String[] split(String delimiter, String[] segments, int length)
  {
    // The empty array is mapped to the special constant.
    //
    if (length == 0)
    {
      return EMPTY_ARRAY;
    }
    // The empty delimiter is not used for splitting.
    //
    else if (delimiter.isEmpty())
    {
      // Iterate over the segments and create a new list of segments only when needed for omitting empty segments.
      //
      List<String> collapsedSegments = null;
      for (int i = 0; i < length; ++i)
      {
        // If the segment is empty...
        //
        String segment = segments[i];
        if (segment.isEmpty())
        {
          // If we haven't already created a list for the result...
          //
          if (collapsedSegments == null)
          {
            // Create one with a suitable capacity and add all the segments we've looked at so far, omitting the empty segment.
            //
            collapsedSegments = new ArrayList<String>(length - 1);
            for (int j = 0; j < i; ++j)
            {
              collapsedSegments.add(segments[j]);
            }
          }
        }
        else if (collapsedSegments != null)
        {
          // If we're building a new result, add this non-empty segment.
          //
          collapsedSegments.add(segment);
        }
      }

      // Return either original argument, or the array from the new result, or the original argument.
      //
      return collapsedSegments == null ? segments : collapsedSegments.isEmpty() ? EMPTY_ARRAY : collapsedSegments.toArray(new String [collapsedSegments.size()]);
    }
    else
    {
      // Iterate over the segments and create a new list of segments only when needed.
      //
      List<String> expandedSegments = null;
      for (int i = 0; i < length; ++i)
      {
        // If the segment contains the delimiter...
        //
        String segment = segments[i];
        if (segment.indexOf(delimiter) != -1)
        {
          // Create a segment sequence for the segment, to reuse the efficient logic for splitting a name, and pull out the segments from that result.
          //
          String[] subsegments = create(delimiter, segment).segments;

          // If we haven't already created a list for the result...
          //
          if (expandedSegments == null)
          {
            // Create one with a suitable capacity and add all the segments we've looked at so far...
            //
            expandedSegments = new ArrayList<String>(length + subsegments.length);
            for (int j = 0; j < i; ++j)
            {
              expandedSegments.add(segments[j]);
            }
          }
          // Add the subsegments of this segment as well.
          //
          for (int j = 0, subsegmentsLength = subsegments.length; j < subsegmentsLength; ++j)
          {
            expandedSegments.add(subsegments[j]);
          }
        }
        else if (expandedSegments != null)
        {
          // If we're building a new result, add this segment.
          //
          expandedSegments.add(segment);
        }
      }

      // Return either original argument, or the array from the new result, or the original argument.
      //
      return expandedSegments == null ? segments : expandedSegments.toArray(new String [expandedSegments.size()]);
    }
  }

  /**
   * A builder for creating a {@link #toString() composed string} or a {@link #toSegmentSequence()}.
   * It may be a more efficient alternative to {@link StringBuilder} because the strings are not copied to a character-based buffer until {@link #toString()} is called.
   * At that time, the total number of characters needed is known, so a character array can be allocated and used direct to create a string.
   * Using the empty string as the delimiter is particularly useful for that purpose.
   */
  public static final class Builder
  {
    /**
     * The delimiter of the builder.
     */
    protected final String delimiter;
    
    /**
     * The number of strings in the builder.
     */
    protected int size;
    
    /**
     * The string sin the builder.
     */
    protected String[] strings;
    
    /**
     * Creates an instance with the given capacity.
     */
    protected Builder(String delimiter, int capacity)
    {
      if (delimiter == null)
      {
        throw new NullPointerException("delimiter=null");
      }
      this.delimiter = delimiter == null ? "" : delimiter;
      strings = new String[capacity];
    }

    /**
     * Appends a string to the builder and returns the builder itself.
     */
    public Builder append(String string)
    {
      if (string == null)
      {
        string = "null";
      }
      if (size == strings.length)
      {
        String[] newStrings = new String[2 * size];
        System.arraycopy(strings, 0, newStrings, 0, size);
        strings = newStrings;
      }
      strings[size++] = string;
      return this;
    }

    /**
     * Appends a string representation of the character to the builder and returns the builder itself.
     */
    public Builder append(char c)
    {
      if (c < CommonUtil.StringPool.StringsAccessUnit.CHAR_STRINGS_COUNT)
      {
        append(CommonUtil.StringPool.StringsAccessUnit.CHAR_STRINGS[c]);
      }
      else
      {
        append(String.valueOf(c));
      }
      return this;
    }

    /**
     * Reverses the strings in the builder and returns the builder itself.
     */
    public Builder reverse()
    {
      for (int i = 0, j = size - 1; i < j; ++i, --j)
      {
        String string = strings[i];
        strings[i] = strings[j];
        strings[j] = string;
      }
      return this;
    }

    /**
     * Converts the builder to a corresponding segment sequence.
     */
    public SegmentSequence toSegmentSequence()
    {
      return create(delimiter, strings, size);
    }

    /**
     * Converts the builder to a string representation, i.e,. the delimiter-separated composition of the appended strings.
     */
    @Override
    public String toString()
    {
      int size = this.size;
      if (size == 0)
      {
        return "";
      }
      else if (size == 1)
      {
        return strings[0];
      }
      else
      {
        String[] strings = this.strings;
        int delimiterLength = delimiter.length();
        String firstString = strings[0];
        int offset = firstString.length();
        int count = offset + (size - 1) * delimiterLength;
        for (int i = 1; i < size; ++i)
        {
          count += strings[i].length();
        }
        char[] result = new char[count];
        firstString.getChars(0, offset, result, 0);
        for (int i = 1; i < size; ++i)
        {
          delimiter.getChars(0, delimiterLength, result, offset);
          offset += delimiterLength;

          String string = strings[i];
          int stringLength = string.length();
          string.getChars(0, stringLength, result, offset);
          offset += stringLength;
        }
        return new String(result);
      }
    }
  }

  /**
   * Creates a new builder for the given delimiter.
   * The delimiter may be the empty string.
   * @throws NullPointerException if the delimiter is null.
   */
  public static Builder newBuilder(String delimiter)
  {
    return new Builder(delimiter, 10);
  }

  /**
   * Creates a new builder for the given delimiter with the given capacity of strings before the buffer needs to grow.
   * The delimiter may be the empty string.
   * @throws NullPointerException if the delimiter is null.
   */
  public static Builder newBuilder(String delimiter, int capacity)
  {
    return new Builder(delimiter, capacity);
  }
}