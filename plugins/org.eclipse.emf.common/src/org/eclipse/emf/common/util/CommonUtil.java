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
import java.util.Locale;

import org.eclipse.emf.common.CommonPlugin;


/**
 * Static utilities for string pooling.
 * <p>
 * As of EMF 2.9, the core runtime maintains a number of {@link Pool pools} managed by {@link WeakReference weak references},
 * specifically a {@link #STRING_POOL string pool}, 
 * a {@link SegmentSequence#STRING_ARRAY_POOL string array pool},
 * a {@link SegmentSequence#POOL segment sequence pool}, 
 * and a {@link URI#POOL URI pool}.
 * The weak referenced are optionally managed by a daemon thread.
 * The daemon thread is created by default only if there is no {@link System#setSecurityManager(SecurityManager) security manager},
 * but that default can be overridden by specifying a <code>true</code> or <code>false</code> for the {@link System#getProperty(String) system property} <code>"org.eclipse.emf.common.util.ReferenceClearingQueue"</code>.
 * </p>
 * @since 2.9
 */
public final class CommonUtil
{
  /**
   * Prevent instantiability.
   */
  private CommonUtil()
  {
  }

  /**
   * A reference queue monitored by a daemon thread that automatically calls {@link Reference#clear() clear} on each reference {@link ReferenceQueue#remove() removed} from the queue.
   */
  static final ReferenceQueue<Object>  REFERENCE_CLEARING_QUEUE;

  static
  {
    ReferenceQueue<Object> referenceClearingQueue = null;
    try
    {
      // A daemon thread is created only if there is no security manager.
      // The system property "org.eclipse.emf.common.util.ReferenceClearingQueue" can be used to override the default.
      //
      String hasReferenceClearingQueue = System.getProperty("org.eclipse.emf.common.util.ReferenceClearingQueue");
      if (System.getSecurityManager() == null ? !"false".equalsIgnoreCase(hasReferenceClearingQueue) : "true".equalsIgnoreCase(hasReferenceClearingQueue))
      {
        class ReferenceClearingQueuePollingThread extends Thread
        {
          protected final ReferenceQueue<Object> queue = new ReferenceQueue<Object>();

          @Override
          public void run()
          {
            try
            {
              // Call clear on each reference removed from the queue.
              //
              for (;;)
              {
                Reference<? extends Object> reference = queue.remove();
                reference.clear();
              }
            }
            catch (InterruptedException exception)
            {
              CommonPlugin.INSTANCE.log(exception);
            }
          }
        }

        // Start the daemon thread.
        //
        ReferenceClearingQueuePollingThread referenceClearingQueuePollingThread = new ReferenceClearingQueuePollingThread();
        referenceClearingQueuePollingThread.setDaemon(true);
        referenceClearingQueuePollingThread.setName("EMF Reference Cleaner");
        // referenceClearingQueuePollingThread.setPriority(Thread.MAX_PRIORITY);S
        referenceClearingQueuePollingThread.start();
        
        // If we successfully started the thread, initialize the queue
        //
        referenceClearingQueue = referenceClearingQueuePollingThread.queue;
      }
    }
    catch (Throwable throwable)
    {
      // Ignore any and all exceptions.

    }
    REFERENCE_CLEARING_QUEUE = referenceClearingQueue;
  }

  /**
   * A cached array for the 31^n.
   */
  private static volatile int[] POWERS_OF_31 = new int [0];

  /**
   * Returns 31^n.
   */
  static int powerOf31(int n)
  {
    // Check that the array is big enough to hold at least n values.
    //
    if (POWERS_OF_31.length <= n)
    {
      synchronized (CommonUtil.class)
      {
        // Check again now that we've synchronized.
        //
        if (POWERS_OF_31.length <= n)
        {
          // Create a larger array.
          //
          int[] result = new int [Math.max(n + 100, 200)];

          // Compute the power values.
          //
          int powerOf31 = 1;
          for (int i = 0; i < result.length; ++i)
          {
            result[i] = powerOf31;
            powerOf31 *= 31;
          }

          // Cache the result.
          //
          POWERS_OF_31 = result;
        }
      }
    }

    // Look up the result in the array.
    //
    return POWERS_OF_31[n];
  }

  /**
   * A cached pool of weakly referenced strings.
   * When adding a new string, except for the case of {@link #javaIntern(String)}, this pool will always cache a new string backed by a minimally sized character array,
   */
  static final class StringPool extends Pool<String>
  {
    private static final long serialVersionUID = 1L;

    protected static final String EMPTY_STRING = "";
    
    private static final Locale DEFAULT_LOCALE = Locale.getDefault();

    /**
     * A base access unit for accessing the string pool.
     */
    public static class AccessUnitBase extends AccessUnit<String>
    {
      /**
       * This is used to cache string characters for fast access.
       */
      protected char[] buffer = new char [100];

      protected AccessUnitBase(Queue<String> queue)
      {
        super(queue);
      }

      @Override
      protected String getValue()
      {
        throw new UnsupportedOperationException();
      }

      @Override
      protected void setValue(String value)
      {
        throw new UnsupportedOperationException();
      }

      @Override
      protected boolean setArbitraryValue(Object value)
      {
        throw new UnsupportedOperationException();
      }

      /**
       * Ensures that the {@link #buffer} buffer is big enough to hold at least the specified number of characters.
       */
      protected final char[] ensureCapacity(int length)
      {
        // Ensure that the buffer is big enough...
        //
        if (buffer.length < length)
        {
           buffer = new char [length];
        }
        return buffer;
      }
    }

    /**
     * Access units for basic string access.
     */
    protected final StringAccessUnit.Queue stringAccessUnits;

    /**
     * An access unit for basic string access.
     */
    public static class StringAccessUnit extends AccessUnitBase
    {
      /**
       * The specialized queue for managing the reuse of these access units.
       */
      protected static class Queue extends AccessUnit.Queue<String>
      {
        private static final long serialVersionUID = 1L;

        /**
         * To pool for which these access units are managed.
         */
        protected final StringPool pool;

        /**
         * Creates an instance for this pool.
         */
        public Queue(StringPool pool)
        {
          this.pool = pool;
        }

        @Override
        public StringAccessUnit pop(boolean isExclusive)
        {
          return (StringAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<String> newAccessUnit()
        {
          return new StringAccessUnit(pool, this);
        }
      }

      /**
       * To pool to which to delegate upper and lower case access.
       */
      protected final StringPool pool;

      /**
       * The value being accessed.
       */
      protected String value;
      
      /**
       * A three state field for normal, lower case, or upper case access.
       */
      protected Boolean toLowerCase;

      /**
       * A helper access unit for finding an upper or lower case variant of the value being access.
       * It's not managed by a queue.
       */
      protected final ObjectAccessUnit<String> helperAccessUnit = new ObjectAccessUnit<String>(null);

      /**
       * Creates an instance for the given pool managed by the given queue.
       */
      protected StringAccessUnit(StringPool pool, Queue queue)
      {
        super(queue);
        this.pool = pool;
      }

      /**
       * Sets the value being accessed.
       */
      @Override
      protected void setValue(String value)
      {
        this.value = value;
        this.hashCode = value.hashCode();
      }

      /**
       * Sets the value being accessed.
       */
      protected void setValue(String value, int hashCode)
      {
        this.value = value;
        this.hashCode = hashCode;
      }

      @Override
      protected boolean setArbitraryValue(Object value)
      {
        if (value instanceof String)
        {
          setValue((String)value);
          return true;
        }
        else
        {
          return false;
        }
      }

      /**
       * Sets the case version of the value being accessed.
       */
      protected void setValue(boolean toLowerCase, String value)
      {
        this.value = value;
        this.toLowerCase = toLowerCase ? Boolean.TRUE : Boolean.FALSE;
        this.hashCode = value.hashCode();
      }

      @Override
      protected boolean matches(String value)
      {
        return this.value == value || this.value.equals(value);
      }

      @Override
      public String match()
      {
        String result = super.match();

        // If we're doing case conversion, we need special handling to find the lower or upper case version of the value.
        //
        if (toLowerCase != null)
        {
          // If we've failed to find a match...
          //
          StringPoolEntry entry;
          if (result == null)
          {
            // Use the helper to force the internalized version of the value to be added to the pool...
            //
            ObjectAccessUnit<String> helperAccessUnit = this.helperAccessUnit;

            // Internalize the value, i.e., convert it to a minimally sized string, and record it as if it were our matched result.
            //
            result = getInternalizedValue();

            // Prepare the helper to access the internalized value.
            //
            helperAccessUnit.setValue(result, hashCode);

            // Add the entry to the pool; this will double check that no other thread has already done this, and will return that result instead should it be the case.
            //
            result = pool.addEntry(false, result, helperAccessUnit);

            // Retrieve the corresponding entry in the pool holding this result.
            //
            entry = (StringPoolEntry)helperAccessUnit.getEntry();

            // Be sure to reset the helper for subsequent reuse.
            // Again note that it's not managed by a queue.
            //
            helperAccessUnit.reset(false);
          }
          else
          {
            // Continue processing based on the matched entry already in the pool.
            //
            entry = (StringPoolEntry)getEntry();
          }

          // Handle the two cases of lower or upper case conversion...
          //
          if (toLowerCase == Boolean.TRUE)
          {
            // Is there a weak reference to the lower case version...
            //
            StringPoolEntry lowerCaseEntry = entry.lowerCaseEntry;
            if (lowerCaseEntry != null)
            {
              // If the referent hasn't been garbage collected, return that value.
              //
              String lowerCaseString = lowerCaseEntry.get();
              if (lowerCaseString != null)
              {
                return lowerCaseString;
              }
            }

            // Convert the matched result to lower case, and check if that's a different string...
            //
            String lowerCaseString = result.toLowerCase(DEFAULT_LOCALE);
            if (lowerCaseString == result)
            {
              // If the string is its own lower case string, record that fact in the entry.
              //
              entry.lowerCaseEntry = entry;
            }
            else
            {
              // Otherwise, use the helper to intern the lower case version of the string.
              // It's possible that another thread did this all at the same time and we're getting back the result from that thread.
              //
              AccessUnit<String> helperAccessUnit = this.helperAccessUnit;
              helperAccessUnit.setValue(lowerCaseString);
              result = pool.addEntry(false, lowerCaseString, helperAccessUnit);
              
              // Retrieve the entry so we can set it as its own lower case entry and as the lower case entry for this original entry.
              //
              lowerCaseEntry = (StringPoolEntry)helperAccessUnit.getEntry();
              lowerCaseEntry.lowerCaseEntry = lowerCaseEntry;
              entry.lowerCaseEntry = lowerCaseEntry;

              // Be sure to reset the helper for subsequent reuse.
              // Again note that it's not managed by a queue.
              helperAccessUnit.reset(false);
            }
          }
          else
          {
            // Is there a weak reference to the upper case version...
            //
            StringPoolEntry upperCaseEntry = entry.upperCaseEntry;
            if (upperCaseEntry != null)
            {
              // If the referent hasn't been garbage collected, return that value.
              //
              String upperCaseString = upperCaseEntry.get();
              if (upperCaseString != null)
              {
                return upperCaseString;
              }
            }

            // Convert the matched result to upper case, and check if that's a different string...
            //
            String upperCaseString = result.toUpperCase(DEFAULT_LOCALE);
            if (upperCaseString == result)
            {
              // If the string is its own upper case string, record that fact in the entry.
              //
              entry.upperCaseEntry = entry;
            }
            else
            {
              // Otherwise, use the helper to intern the upper case version of the string.
              // It's possible that another thread did this all at the same time and we're getting back the result from that thread.
              //
              AccessUnit<String> helperAccessUnit = this.helperAccessUnit;
              helperAccessUnit.setValue(upperCaseString);
              result = pool.addEntry(false, upperCaseString, helperAccessUnit);
              
              // Retrieve the entry so we can set it as its own upper case entry and as the upper case entry for this original entry.
              //
              upperCaseEntry = (StringPoolEntry)helperAccessUnit.getEntry();
              upperCaseEntry.upperCaseEntry = upperCaseEntry;
              entry.upperCaseEntry = upperCaseEntry;

              // Be sure to reset the helper for subsequent reuse.
              // Again note that it's not managed by a queue.
              helperAccessUnit.reset(false);
            }
          }
        }
        return result;
      }

      @Override
      public String getInternalizedValue()
      {
        return new String(value);
      }

      @Override
      public void reset(boolean isExclusive)
      {
        value = null;
        toLowerCase = null;
        super.reset(isExclusive);
      }
    }

    /**
     * Access units for access via a character array.
     */
    protected final CharactersAccessUnit.Queue charactersAccessUnits = new CharactersAccessUnit.Queue();

    /**
     * An Access unit for access via a character array.
     */
    public static class CharactersAccessUnit extends AccessUnitBase
    {
      /**
       * A specialized queue for managing the reuse of these access units.
       */
      protected static class Queue extends AccessUnit.Queue<String>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public CharactersAccessUnit pop(boolean isExclusive)
        {
          return (CharactersAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<String> newAccessUnit()
        {
          return new CharactersAccessUnit(this);
        }
      }

      /**
       * The characters being {@link StringPool#intern(char[], int, int, int) accessed};
       */
      protected char[] characters;

      /**
       * The offset of the characters being {@link StringPool#intern(char[], int, int, int) accessed}.
       */
      protected int offset;

      /**
       * The count of the characters being {@link StringPool#intern(char[], int, int, int) accessed}.
       */
      protected int count;

      /**
       * Creates an access unit managed by the specified queue.
       */
      protected CharactersAccessUnit(Queue queue)
      {
        super(queue);
      }

      /**
       * Sets the {@link #characters}, {@link #offset}, {@link #count}, and {@link #hashCode()} of the value being {@link StringPool#intern(char[], int, int, int) accessed}.
       */
      public void setValue(char[] characters, int offset, int count, int hashCode)
      {
        this.hashCode = hashCode;
        this.characters = characters;
        this.offset = offset;
        this.count = count;
      }

      /**
       * Sets the {@link #characters}, {@link #offset}, and {@link #count}, and computes the {@link #hashCode()} of the value being {@link StringPool#intern(char[], int, int) accessed}.
       */
      public void setValue(char[] characters, int offset, int count)
      {
        this.characters = characters;
        this.offset = offset;
        this.count = count;

        int hashCode = 0;
        for (int i = offset, limit = offset + count; i < limit; ++i)
        {
          hashCode = 31 * hashCode + characters[i];
        }
        this.hashCode = hashCode;
      }

      @Override
      protected boolean matches(String value)
      {
        int length = value.length();
        if (length != count)
        {
          // If the length doesn't match, then it doesn't match.
          //
          return false;
        }
        else
        {
          char[] characters = this.characters;
          for (int i = 0, j = offset; i < length; ++i, ++j)
          {
            if (characters[j] != value.charAt(i))
            {
              // If any character isn't the same, then it doesn't match.
              //
              return false;
            }
          }

          // The value matches.
          //
          return true;
        }
      }

      /**
       * Creates a new string for adding to the pool.
       * This ensures that we do not keep the original argument string in the pool.
       * It's expected that pooled strings will are likely to be around for a long time,
       * and the argument string could be a substring of a much larger string that would lock into the heap the much larger underlying character array.
       * Note that the new string will share the underlying character array of the argument if the arguments' character array is exactly the size of the string,
       * so interning a String that's already interned by Java itself doesn't have so much memory impact.
       */
      @Override
      public String getInternalizedValue()
      {
        return new String(characters, offset, count);
      }

      @Override
      public void reset(boolean isExclusive)
      {
        characters = null;
        super.reset(isExclusive);
      }
    }

    /**
     * Access units for accessing a substring.
     */
    protected final SubstringAccessUnit.Queue substringAccessUnits = new SubstringAccessUnit.Queue();

    /**
     * An access unit for accessing a substring.
     */
    public static class SubstringAccessUnit extends AccessUnitBase
    {
      /**
       * A specialized queue for managing the reuse of these access units.
       */
      protected static class Queue extends AccessUnit.Queue<String>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public SubstringAccessUnit pop(boolean isExclusive)
        {
          return (SubstringAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<String> newAccessUnit()
        {
          return new SubstringAccessUnit(this);
        }
      }

      /**
       * The substring being {@link StringPool#intern(String, int, int, int) accessed}.
       */
      private String string;

      /**
       * The offset of the characters being {@link StringPool#intern(char[], int, int, int) accessed}.
       */
      private int offset;

      /**
       * The count of the characters being {@link StringPool#intern(char[], int, int, int) accessed}.
       */
      private int count;

      /**
       * Creates an instance managed by the given queue.
       */
      protected SubstringAccessUnit(Queue queue)
      {
        super(queue);
      }

      /**
       * Sets the {@link #characters}, {@link #offset}, {@link #count}, and {@link #hashCode()} of the value being {@link StringPool#intern(char[], int, int, int) accessed}.
       */
      public void setValue(String string, int offset, int count, int hashCode)
      {
        this.hashCode = hashCode;
        this.string = string;
        this.offset = offset;
        this.count = count;
      }

      /**
       * Sets the {@link #characters}, {@link #offset}, and {@link #count} and computes the {@link #hashCode()} of the value being {@link StringPool#intern(char[], int, int) accessed}.
       */
      public void setValue(String string, int offset, int count)
      {
        this.string = string;
        this.offset = offset;
        this.count = count;
        int hashCode = 0;
        for (int i = offset, limit = offset + count; i < limit; ++i)
        {
          hashCode = 31 * hashCode + string.charAt(i);
        }
        this.hashCode = hashCode;
      }

      @Override
      protected boolean matches(String value)
      {
        // If the region matches, it's a match.
        //
        return value.length() == count && value.regionMatches(0, string, offset, count);
      }

      /**
       * Creates a new string for adding to the pool.
       * This ensures that we do not keep the original argument string in the pool.
       * It's expected that pooled strings will are likely to be around for a long time,
       * and the argument string could be a substring of a much larger string that would lock into the heap the much larger underlying character array.
       * Note that the new string will share the underlying character array of the argument if the arguments' character array is exactly the size of the string,
       * so interning a String that's already interned by Java itself doesn't have so much memory impact.
       */
      @Override
      public String getInternalizedValue()
      {
        return new String(string.substring(offset, offset + count));
      }

      @Override
      public void reset(boolean isExclusive)
      {
        string = null;
        super.reset(isExclusive);
      }
    }

    /**
     * Access units for accessing a character sequence.
     */
    protected final CharSequenceAccessUnit.Queue charSequenceAccessUnits = new CharSequenceAccessUnit.Queue();

    /**
     * An access unit for accessing a character sequence.
     */
    public static class CharSequenceAccessUnit extends AccessUnitBase
    {
      /**
       * A specialized queue for managing the reuse of these access units.
       */
      protected static class Queue extends AccessUnit.Queue<String>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public CharSequenceAccessUnit pop(boolean isExclusive)
        {
          return (CharSequenceAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<String> newAccessUnit()
        {
          return new CharSequenceAccessUnit(this);
        }
      }

      /**
       * The character sequence being {@link StringPool#intern(CharSequence) accessed}.
       */
      protected CharSequence charSequence;

      /**
       * The count of the characters being accessed.
       */
      protected int count;

      /**
       * Creates an instance managed by the specified queue.
       */
      protected CharSequenceAccessUnit(Queue queue)
      {
        super(queue);
      }

      /**
       * Sets the {@link #charSequence}, and {@link #count} of the value being {@link StringPool#intern(CharSequence) accessed}, and computes the {@link #hashCode}.
       */
      public void setValue(CharSequence charSequence, int count)
      {
        this.charSequence = charSequence;
        this.count = count;

        Class<?> valueClass = charSequence.getClass();
        char[] buffer = ensureCapacity(count);
        if (valueClass == StringBuilder.class)
        {
          // Special case handling for string builder which can fetch the characters faster than calling charAt for each index.
          //
          ((StringBuilder)charSequence).getChars(0, count, buffer, 0);
        }
        else if (valueClass == StringBuffer.class)
        {
          // Special case handling for string buffer which can fetch the characters faster than calling charAt for each index.
          //
          ((StringBuilder)charSequence).getChars(0, count, buffer, 0);
        }
        else
        {
          // Failing the special cases, we must fetch all the characters one-by-one, caching them in the characters buffer as we go.
          //
          int hashCode = (buffer[0] = charSequence.charAt(0));
          for (int i = 1; i < count; ++i)
          {
            hashCode = 31 * hashCode + (buffer[i] = charSequence.charAt(i));
          }
          this.hashCode = hashCode;
          return;
        }

        // Compute the hash code for the characters in the buffer.
        //
        int hashCode = buffer[0];
        for (int i = 0; i < count; ++i)
        {
          hashCode = 31 * hashCode + buffer[i];
        }

        this.hashCode = hashCode;
      }

      @Override
      protected boolean matches(String value)
      {
        return value.contentEquals(charSequence);
      }

      @Override
      public String getInternalizedValue()
      {
        return new String(buffer, 0, count);
      }

      @Override
      public void reset(boolean isExclusive)
      {
        charSequence = null;
        super.reset(isExclusive);
      }
    }

    /**
     * An access units for accessing Java {@link String#intern() interned} strings.
     */
    protected final JavaInterningAccessUnit.Queue javaInterningAccessUnits = new JavaInterningAccessUnit.Queue();

    /**
     * An access unit for accessing Java {@link String#intern() interned} strings.
     */
    public static class JavaInterningAccessUnit extends ObjectAccessUnit<String>
    {
      /**
       * A specialized queue for managing the reuse of these access units.
       */
      protected static class Queue extends AccessUnit.Queue<String>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public JavaInterningAccessUnit pop(boolean isExclusive)
        {
          return (JavaInterningAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<String> newAccessUnit()
        {
          return new JavaInterningAccessUnit(this);
        }
      }

      /**
       * Create an instance.
       */
      protected JavaInterningAccessUnit(Queue queue)
      {
        super(queue);
      }

      @Override
      public String getInternalizedValue()
      {
        // Specialized to Java intern the value being accessed.
        //
        return value.intern();
      }
    }

    /**
     * Access units for accessing a to-be-composed sequence of strings.
     */
    protected final StringsAccessUnit.Queue stringsAccessUnits = new StringsAccessUnit.Queue();

    /**
     * An access unit for accessing a to-be-composed sequence of strings.
     */
    static class StringsAccessUnit extends ObjectAccessUnit<String>
    {
      /**
       * The number of single character strings locally maintained.
       */
      protected static final int CHAR_STRINGS_COUNT = 256;
      
      /**
       * An cache for single character strings.
       */
      protected static final String CHAR_STRINGS[] = new String[CHAR_STRINGS_COUNT];

      static
      {
        // Java intern all the single character strings.
        //
        for (int i = 0; i < CHAR_STRINGS_COUNT; ++i)
        {
          CHAR_STRINGS[i] = CommonUtil.javaIntern(String.valueOf((char)i));
        }
      }

      /**
       * A specialized queue for managing the reuse of these access units.
       */
      protected static class Queue extends AccessUnit.Queue<String>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public StringsAccessUnit pop(boolean isExclusive)
        {
          return (StringsAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<String> newAccessUnit()
        {
          return new StringsAccessUnit(this);
        }
      }

      /**
       * The strings being {@link StringPool#getStringBuilder() accessed}.
       */
      protected String[] strings = new String[20];
      
      /**
       * A buffer of characters for composing the {@link #strings}.
       */
      protected char [] characters = new char [100];
      
      /**
       * The number of {@link #strings}.
       */
      protected int length;
      
      /**
       * The length of the composed string.
       */
      protected int stringLength;

      /**
       * Creates an instance managed by the specified queue.
       */
      protected StringsAccessUnit(Queue queue)
      {
        super(queue);
      }

      /**
       * Ensures that the {@link #strings} are big enough to hold one more string than the specified length.
       */
      protected void ensureCapacity(int length)
      {
        if (strings.length <= length)
        {
          String[] newStrings = new String[length * 2];
          System.arraycopy(strings, 0, newStrings, 0, this.length);
          strings = newStrings;
        }
      }

      /**
       * {@link #ensureCapacity(int) Ensures} that the strings is begin enough to hold one more string
       * and then {@link #basicAppend(String) appends} the string.
       * @param string
       */
      protected void doAppend(String string)
      {
        ensureCapacity(length);
        basicAppend(string);
      }

      /**
       * Appends the string to the {@link #strings}, if it's not the empty string, and computes the composed hash code and {@link #stringLength total composed string length}.
       * @param string
       */
      protected void basicAppend(String string)
      {
        int stringLength = string.length();
        if (stringLength != 0)
        {
          strings[length++] = string;
          hashCode = hashCode * powerOf31(stringLength) + string.hashCode();
          this.stringLength += stringLength;
        }
      }

      /**
       * Appends the string presentation for the single character.
       */
      public void append(char c)
      {
        if (c < CHAR_STRINGS_COUNT)
        {
          doAppend(CHAR_STRINGS[c]);
        }
        else
        {
          doAppend(String.valueOf(c));
        }
      }

      /**
       * Appends the string, replacing <code>null</code> with <code>"null"</code>.
       * @param string
       */
      public void append(String string)
      {
        doAppend(string == null ? "" : string);
      }

      /**
       * Appends the character sequence, with special handing for {@link SegmentSequence}.
       * @param charSequence
       */
      public void append(CharSequence charSequence)
      {
        if (charSequence == null)
        {
          doAppend("null");
        }
        else
        {
          Class<?> charSequenceClass = charSequence.getClass();
          if (charSequenceClass == SegmentSequence.class)
          {
            // If we have a segment sequence, compose the result from its delimiter separated segments.
            //
            SegmentSequence segmentSequence = (SegmentSequence)charSequence;
            String delimiter = segmentSequence.delimiter;
            String[] segments = segmentSequence.segments;
            int segmentsLength = segments.length;
            if (segmentsLength > 0)
            {
              if (delimiter == "")
              {
                // Ignore the delimiters because they're the empty string.
                //
                ensureCapacity(length + segmentsLength);
                for (int i = 0; i < segmentsLength; ++i)
                {
                  basicAppend(segments[i]);
                }
              }
              else
              {
                // We expect a string for each segment and a delimiter between each segment.
                ensureCapacity(length + 2 * segmentsLength - 1);
                basicAppend(segments[0]);
                for (int i = 1; i < segmentsLength; ++i)
                {
                  basicAppend(delimiter);
                  basicAppend(segments[i]);
                }
              }
            }
          }
          else
          {
            // Otherwise append the string representation.
            //
            doAppend(charSequence.toString());
          }
        }
      }

      @Override
      protected boolean matches(String value)
      {
        if (value.length() != stringLength)
        {
          // If the length doesn't match the composed length, it's not a match.
          //
          return false;
        }
        else
        {
          String[] strings = this.strings;
          for (int i = 0, index = 0, length = this.length; i < length; ++i)
          {
            String string = strings[i];
            if (!value.startsWith(string, index))
            {
              // If the value doen't contain the string at the expected index, it's not a match.
              //
              return false;
            }
            index += string.length();
          }
          
          // No contradiction found, so it's a match.
          //
          return true;
        }
      }

      @Override
      public String getInternalizedValue()
      {
        // Ensure that the character buffer is big enough to hold all the characters.
        //
        if (characters.length < stringLength)
        {
          characters = new char[Math.max(stringLength, characters.length * 2)];
        }

        // Compose the result into the character buffer and create a string from that.
        //
        String[] strings = this.strings;
        for (int i = 0, index = 0, length = this.length; i < length; ++i)
        {
          String string = strings[i];
          int stringLength = string.length();
          string.getChars(0, stringLength, characters, index);
          index += string.length();
        }
        return new String(characters, 0, stringLength);
      }

      @Override
      public void reset(boolean isExclusive)
      {
        String[] strings = this.strings;
        for (int i = 0,length = this.length; i < length; ++i)
        {
          strings[i] = null;
        }

        hashCode = 0;
        length = 0;
        stringLength = 0;

        super.reset(isExclusive);
      }
    }

    /**
     * We maintain a single pool.
     */
    protected StringPool()
    {
      this(null);
    }

    /**
     * Create instance whose weak references are managed by the given external queue.
     */
    protected StringPool(ReferenceQueue<Object> queue)
    {
      super(1031, null, queue);

      // Ensure that null is initially in the pool.
      //
      containsNull = true;
      size = 1;

      // Ensure that the empty string is in the pool.
      //
      javaIntern(EMPTY_STRING);

      // We initialize this late because it needs a reference back to this pool.
      //
      this.stringAccessUnits = (StringAccessUnit.Queue)primaryAccessUnits;
    }

    @Override
    protected AccessUnit.Queue<String> newDefaultAccessUnits()
    {
      // We initialize this during the construction of the super type because it needs a reference back to this pool.
      //
      return new StringAccessUnit.Queue(this);
    }

    /**
     * A specialized string pool entry that maintains references to {@link StringPool#intern(boolean, String) corresponding} upper and lower case variants.
     */
    protected static class StringPoolEntry extends Entry<String>
    {
      /**
       * The lower case variant of this entry's string.
       */
      protected StringPoolEntry lowerCaseEntry;

      /**
       * The upper case variant of this entry's string.
       */
      protected StringPoolEntry upperCaseEntry;

      /**
       * Creates an instance for the given value with the specified hash code managed by the given queue.
       */
      public StringPoolEntry(String value, int hashCode, ReferenceQueue<? super String> queue)
      {
        super(value, hashCode, queue);
      }

      @Override
      public void doClear()
      {
        lowerCaseEntry = null;
        upperCaseEntry = null;

        super.doClear();
      }
    }

    /**
     * A specialized string pool entry that can be managed by an {@link CommonUtil#REFERENCE_CLEARING_QUEUE reference clearing queue}.
     */
    protected static class SelfCleaningStringPoolEntry extends StringPoolEntry
    {
      /**
       * The pool from which the entry should be {@link Pool#removeEntry(org.eclipse.emf.common.util.WeakInterningHashSet.Entry) removed} when {@link #clear() cleared}.
       */
      protected final StringPool pool;

      /**
       * Creates an instance for the given value with the specified hash code managed by the given queue and in the given pool.
       */
      public SelfCleaningStringPoolEntry(StringPool pool, String value, int hashCode, ReferenceQueue<Object> queue)
      {
        super(value, hashCode, queue);
        this.pool = pool;
      }

      @Override
      public void clear()
      {
        // Gain exclusive write access and then clear the entry from the pool. 
        //
        StringPool pool = this.pool;
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
    protected Entry<String> newInternalEntry(String object, int hashCode)
    {
      return new StringPoolEntry(object, hashCode, internalQueue);
    }

    @Override
    protected Entry<String> newExternalEntry(String object, int hashCode)
    {
      return new SelfCleaningStringPoolEntry(this, object, hashCode, externalQueue);
    }

    @Override
    public final String intern(String string)
    {
      if (string == null)
      {
        return null;
      }
      else
      {
        // Iterate over the entries with the matching hash code.
        //
        int hashCode = string.hashCode();
        for (Entry<String> entry = getEntry(hashCode); entry != null; entry = entry.getNextEntry())
        {
          // Check that the referent isn't garbage collected and then compare it.
          //
          String value = entry.get();
          if (value == string || string.equals(value))
          {
            // Return that already present value.
            //
            return value;
          }
        }

        writeLock.lock();
        try
        {
          StringAccessUnit accessUnit = stringAccessUnits.pop(true);
          accessUnit.setValue(string, hashCode);
          String result = addEntry(true, accessUnit.getInternalizedValue(), accessUnit);
          accessUnit.reset(true);
          return result;
        }
        finally
        {
          writeLock.unlock();
        }
      }
    }

    protected final String intern(boolean toLowerCase, String string)
    {
      // Retrieve an access unit for exclusive use in this call for the current thread thread.
      //
      StringAccessUnit accessUnit = stringAccessUnits.pop(false);
      accessUnit.setValue(toLowerCase, string);
      return doIntern(false, accessUnit);
    }

    /**
     * Returns a reusable string builder that can be {@link #intern(StringsAccessUnit) interned} to a string and will then be recycled.
     */
    StringsAccessUnit getStringBuilder()
    {
      return stringsAccessUnits.pop(false);
    }

    /**
     * Returns the interned contents of the builder and recycles the builder.
     */
    String intern(StringsAccessUnit stringBuilder)
    {
      return doIntern(false, stringBuilder);
    }

    /**
     * Returns the interned string for the given character range and its hash code, which must be equivalent to what's computed by {@link String#hashCode()}.
     */
    protected final String intern(char[] characters, int offset, int count, int hashCode)
    {
      // If there are no characters, we can just return the empty string, which we've ensured in the constructor is actually in the pool.
      //
      if (count == 0)
      {
        return EMPTY_STRING;
      }
      else
      {
        // Retrieve an access unit for exclusive use in this call for the current thread thread.
        //
        CharactersAccessUnit accessUnit = charactersAccessUnits.pop(false);
        accessUnit.setValue(characters, offset, count, hashCode);
        return doIntern(false, accessUnit);
      }
    }

    /**
     * Returns the interned string for the given character range and its hash code, which must be equivalent to what's computed by {@link String#hashCode()}.
     */
    protected final String intern(char[] characters, int offset, int count)
    {
      // If there are no characters, we can just return the empty string, which we've ensured in the constructor is actually in the pool.
      //
      if (count == 0)
      {
        return EMPTY_STRING;
      }
      else
      {
        // Retrieve an access unit for exclusive use in this call for the current thread thread.
        //
        CharactersAccessUnit accessUnit = charactersAccessUnits.pop(false);
        accessUnit.setValue(characters, offset, count);
        return doIntern(false, accessUnit);
      }
    }

    /**
     * Returns the interned string for the given character range; the hash code will be computed
     */
    protected final String intern(String string, int offset, int count)
    {
      // If there are no characters, we can just return the empty string, which we've ensured in the constructor is actually in the pool.
      //
      if (count == 0)
      {
        return EMPTY_STRING;
      }
      else
      {
        // Retrieve an access unit for exclusive use in this call for the current thread thread.
        //
        SubstringAccessUnit accessUnit = substringAccessUnits.pop(false);
        accessUnit.setValue(string, offset, count);
        return doIntern(false, accessUnit);
      }
    }

    /**
     * Returns the interned string for the given character range and its hash code, which must be equivalent to what's computed by {@link String#hashCode()}.
     */
    protected final String intern(String string, int offset, int count, int hashCode)
    {
      // If there are no characters, we can just return the empty string, which we've ensured in the constructor is actually in the pool.
      //
      if (count == 0)
      {
        return EMPTY_STRING;
      }
      else
      {
        // Retrieve an access unit for exclusive use in this call for the current thread thread.
        //
        SubstringAccessUnit accessUnit = substringAccessUnits.pop(false);
        accessUnit.setValue(string, offset, count, hashCode);
        return doIntern(false, accessUnit);
      }
    }

    /**
     * Returns the interned string for the given character sequence.
     */
    protected final String intern(CharSequence charSequence)
    {
      // Determine the length of the character sequence and if there are no characters, we can just return the empty string, which we've ensured in the constructor is actually in the pool.
      //
      int charSequenceLength = charSequence.length();
      if (charSequenceLength == 0)
      {
        return EMPTY_STRING;
      }
      else
      {
        // Retrieve an access unit for exclusive use in this call for the current thread thread.
        //
        CharSequenceAccessUnit accessUnit = charSequenceAccessUnits.pop(false);
        accessUnit.setValue(charSequence, charSequenceLength);
        return doIntern(false,accessUnit);
      }
    }

    /**
     * Returns a Java {@link String#intern() interned} version of the string.
     */
    protected final String javaIntern(String string)
    {
      // Retrieve an access unit for exclusive use in this call for the current thread thread.
      //
      AccessUnit<String> accessUnit = javaInterningAccessUnits.pop(false);
      accessUnit.setValue(string);
      return doIntern(false, accessUnit);
    }
  }

  /**
   * A static singleton for the string pool.
   */
  static final StringPool STRING_POOL = new StringPool(REFERENCE_CLEARING_QUEUE);

  /**
   * Returns an interned value for the string.
   * If the value needs to be added to the pool, a {@link String#String(String) copy} will be added.
   * This ensures that we pool only instances with minimally-sized underlying character arrays.
   * Note that the {{@link String#String(String) copy constructor} shares the underlying character array of the original if it's already minimally sized,
   * so the memory impact of interning a string already {@link String#intern() interned} by Java is minimal.
   * Note that you should use {@link #javaIntern(String)} for interning string literals.
   * This method is thread safe; the underlying representation supports shared read access and exclusive write access, holding locks for the shorted time possible to support maximal concurrency.
   * The interned strings are weakly referenced so are only maintained only as long as there remain strong references.
   */
  public static String intern(String value)
  {
    // Filter out the null value...
    //
    return value == null ? null : STRING_POOL.intern(value);
  }

  /**
   * Returns the {@link #intern(String) interned} version of the lower case version of this string.
   * It is equivalent to calling <code>intern(value.toLowerCase(Locale.getDefaultLocale()))</code>,
   * however, it's much faster, because the pool entry for the <code>value</code> itself weakly records the lower case variant.
   * Please note that the default locale at the time this class was initialized applies.
   * This method is thread safe; the underlying representation supports shared read access and exclusive write access, holding locks for the shorted time possible to support maximal concurrency.
   * The interned strings are weakly referenced so are only maintained only as long as there remain strong references.
   */
  public static String internToLowerCase(String value)
  {
    // Filter out the null value...
    //
    return value == null ? null : STRING_POOL.intern(true, value);
  }

  /**
   * Returns the {@link #intern(String) interned} version of the upper case version of this string.
   * It is equivalent to calling <code>intern(value.toUpperCase(Locale.getDefaultLocale()))</code>,
   * however, it's much faster, because the pool entry for the <code>value</code> itself weakly records the upper case variant.
   * Please note that the default locale at the time this class was initialized applies.
   * This method is thread safe; the underlying representation supports shared read access and exclusive write access, holding locks for the shorted time possible to support maximal concurrency.
   * The interned strings are weakly referenced so are only maintained only as long as there remain strong references.
   */
  public static String internToUpperCase(String value)
  {
    // Filter out the null value...
    //
    return value == null ? null : STRING_POOL.intern(false, value);
  }

  /**
   * Returns an interned value for the string.
   * If the value needs to be added to the pool, a {@link String#intern() Java interned version} will be added.
   * It is <b>highly recommend</b> that you only call this with string constants, i.e., with values that are Java interned already, because you will exhaust permgen space if you do this haphazardly.
   * This method is thread safe; the underlying representation supports shared read access and exclusive write access, holding locks for the shorted time possible to support maximal concurrency.
   * The interned strings are weakly referenced but because they are Java interned, unless already in the pool, they will <b>never</b> be garbage collected.
   */
  public static String javaIntern(String value)
  {
    // Filter out the null value...
    //
    return value == null ? null : STRING_POOL.javaIntern(value);
  }

  /**
   * Returns an interned string value for the subsequence of characters.
   * This method is thread safe; the underlying representation supports shared read access and exclusive write access, holding locks for the shorted time possible to support maximal concurrency.
   * The interned strings are weakly referenced so are only maintained only as long as there remain strong references.
   */
  public static String intern(char[] characters, int offset, int count)
  {
    return STRING_POOL.intern(characters, offset, count);
  }

  /**
   * Returns an interned string value for the subsequence of characters.
   * This method is thread safe; the underlying representation supports shared read access and exclusive write access, holding locks for the shorted time possible to support maximal concurrency.
   * The interned strings are weakly referenced so are only maintained only as long as there remain strong references.
   */
  public static String intern(String string, int offset, int count)
  {
    return STRING_POOL.intern(string, offset, count);
  }

  /**
   * Returns an interned string value for the char sequence.
   * This method is thread safe; the underlying representation supports shared read access and exclusive write access, holding locks for the shorted time possible to support maximal concurrency.
   * The interned strings are weakly referenced so are only maintained only as long as there remain strong references.
   */
  public static String intern(CharSequence value)
  {
    return value == null ? null : STRING_POOL.intern(value);
  }
}
