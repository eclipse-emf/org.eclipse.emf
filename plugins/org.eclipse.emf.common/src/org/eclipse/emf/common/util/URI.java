/**
 * Copyright (c) 2002-2013 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.common.util;

import java.io.File;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * A representation of a Uniform Resource Identifier (URI), as specified by
 * <a href="http://www.ietf.org/rfc/rfc2396.txt">RFC 2396</a>, with certain
 * enhancements.  A <code>URI</code> instance can be created by specifying
 * values for its components, or by providing a single URI string, which is
 * parsed into its components.  Static factory methods whose names begin
 * with "create" are used for both forms of object creation.  No public or
 * protected constructors are provided; this class can not be subclassed.
 *
 * <p>Like <code>String</code>, <code>URI</code> is an immutable class;
 * a <code>URI</code> instance offers several by-value methods that return a
 * new <code>URI</code> object based on its current state.  Most useful,
 * a relative <code>URI</code> can be {@link #resolve(URI) resolve}d against
 * a base absolute <code>URI</code> -- the latter typically identifies the
 * document in which the former appears.  The inverse to this is {@link
 * #deresolve(URI) deresolve}, which answers the question, "what relative
 * URI will resolve, against the given base, to this absolute URI?"
 *
 * <p>In the <a href="http://www.ietf.org/rfc/rfc2396.txt">RFC</a>, much
 * attention is focused on a hierarchical naming system used widely to
 * locate resources via common protocols such as HTTP, FTP, and Gopher, and
 * to identify files on a local file system.  Accordingly, most of this
 * class's functionality is for handling such URIs, which can be identified
 * via {@link #isHierarchical isHierarchical}.
 *
 * <p><a name="device_explanation">
 * The primary enhancement beyond the RFC description is an optional
 * device component.  Instead of treating the device as just another segment
 * in the path, it can be stored as a separate component (almost a
 * sub-authority), with the root below it.  For example, resolving
 * <code>/bar</code> against <code>file:///c:/foo</code> would result in
 * <code>file:///c:/bar</code> being returned.  Also, you cannot take
 * the parent of a device, so resolving <code>..</code> against
 * <code>file:///c:/</code> would not yield <code>file:///</code>, as you
 * might expect.  This feature is useful when working with file-scheme
 * URIs, as devices do not typically occur in protocol-based ones.  A
 * device-enabled <code>URI</code> is created by parsing a string with
 * {@link #createURI(String) createURI}; if the first segment of the path
 * ends with the <code>:</code> character, it is stored (including the colon)
 * as the device, instead.  Alternately, either the {@link
 * #createHierarchicalURI(String, String, String, String, String) no-path}
 * or the {@link #createHierarchicalURI(String, String, String, String[],
 * String, String) absolute-path} form of <code>createHierarchicalURI()</code>
 * can be used, in which a non-null <code>device</code> parameter can be
 * specified.
 *
 * <p><a name="archive_explanation">
 * The other enhancement provides support for the almost-hierarchical
 * form used for files within archives, such as the JAR scheme, defined
 * for the Java Platform in the documentation for {@link
 * java.net.JarURLConnection}. By default, this support is enabled for
 * absolute URIs with scheme equal to "jar", "zip", or "archive" (ignoring case), and
 * is implemented by a hierarchical URI, whose authority includes the
 * entire URI of the archive, up to and including the <code>!</code>
 * character.  The URI of the archive must have no fragment.  The whole
 * archive URI must have no device and an absolute path.  Special handling
 * is supported for {@link #createURI(String) creating}, {@link
 * #validArchiveAuthority validating}, {@link #devicePath getting the path}
 * from, and {@link #toString() displaying} archive URIs. In all other
 * operations, including {@link #resolve(URI) resolving} and {@link
 * #deresolve(URI) deresolving}, they are handled like any ordinary URI.
 * The schemes that identify archive URIs can be changed from their default
 * by setting the <code>org.eclipse.emf.common.util.URI.archiveSchemes</code>
 * system property. Multiple schemes should be space separated, and the test
 * of whether a URI's scheme matches is always case-insensitive.
 *
 * <p>This implementation does not impose all of the restrictions on
 * character validity that are specified in the RFC.  Static methods whose
 * names begin with "valid" are used to test whether a given string is valid
 * value for the various URI components.  Presently, these tests place no
 * restrictions beyond what would have been required in order for {@link
 * #createURI(String) createURI} to have parsed them correctly from a single
 * URI string.  If necessary in the future, these tests may be made more
 * strict, to better conform to the RFC.
 *
 * <p>Another group of static methods, whose names begin with "encode", use
 * percent escaping to encode any characters that are not permitted in the
 * various URI components. Another static method is provided to {@link
 * #decode decode} encoded strings.  An escaped character is represented as
 * a percent symbol (<code>%</code>), followed by two hex digits that specify
 * the character code.  These encoding methods are more strict than the
 * validation methods described above.  They ensure validity according to the
 * RFC, with one exception: non-ASCII characters.
 *
 * <p>The RFC allows only characters that can be mapped to 7-bit US-ASCII
 * representations.  Non-ASCII, single-byte characters can be used only via
 * percent escaping, as described above.  This implementation uses Java's
 * Unicode <code>char</code> and <code>String</code> representations, and
 * makes no attempt to encode characters 0xA0 and above.  Characters in the
 * range 0x80-0x9F are still escaped.  In this respect, EMF's notion of a URI
 * is actually more like an IRI (Internationalized Resource Identifier), for
 * which an RFC is now in <href="http://www.w3.org/International/iri-edit/draft-duerst-iri-09.txt">draft
 * form</a>.
 *
 * <p>Finally, note the difference between a <code>null</code> parameter to
 * the static factory methods and an empty string.  The former signifies the
 * absence of a given URI component, while the latter simply makes the
 * component blank.  This can have a significant effect when resolving.  For
 * example, consider the following two URIs: <code>/bar</code> (with no
 * authority) and <code>///bar</code> (with a blank authority).  Imagine
 * resolving them against a base with an authority, such as
 * <code>http://www.eclipse.org/</code>.  The former case will yield
 * <code>http://www.eclipse.org/bar</code>, as the base authority will be
 * preserved.  In the latter case, the empty authority will override the
 * base authority, resulting in <code>http:///bar</code>!
 */
public abstract class URI
{
  protected static final boolean DEBUG = false;

  /**
   * The cached hash code of the URI.
   * This is always equal to the hash code of {@link #toString()}
   */
  protected final int hashCode;

  /**
   * A pool for caching URIs.
   */
  protected static class URIPool extends Pool<URI>
  {
    protected static final long serialVersionUID = 1L;

    /**
     * A reference queue for managing the {@link URI#toString} values.
     */
    protected final ReferenceQueue<String> cachedToStrings;

    public URIPool(ReferenceQueue<Object> queue)
    {
      super(1031, null, queue);

      // The string cache will be managed by either an internal or external cache as appropriate.
      //
      cachedToStrings = externalQueue == null ? new ReferenceQueue<String>() : null;
    }

    /**
     * A based access unit for this pool.
     */
    protected static class URIPoolAccessUnitBase extends AccessUnit<URI>
    {
      /**
       * A local access unit for exclusive use in {@link #intern(char[], int, int)}.
       */
      protected CommonUtil.StringPool.CharactersAccessUnit charactersAccessUnit =  new CommonUtil.StringPool.CharactersAccessUnit(null);

      /**
       * A local access unit for exclusive for normalizing the scheme in {@link #intern(String)}, {@link #intern(boolean, String)}, and {@link StringAccessUnit#parseIntoURI(String)}.
       */
      protected CommonUtil.StringPool.StringAccessUnit stringAccessUnit =  new CommonUtil.StringPool.StringAccessUnit(CommonUtil.STRING_POOL, null);

      /**
       * The string pool entry found during the most recent use of {@link #substringAccessUnit}.
       */
      protected CommonUtil.StringPool.StringPoolEntry stringPoolEntry;

      /**
       * A local access unit for exclusive use in {@link #intern(String, int, int)} and {@link #intern(String, int, int, int)}.
       * It {@link #stringPoolEntry} the string pool entry that was matched when {@link CommonUtil.StringPool.SubstringAccessUnit#reset(boolean)} is called.
       */
      protected CommonUtil.StringPool.SubstringAccessUnit substringAccessUnit =
        new CommonUtil.StringPool.SubstringAccessUnit(null)
        {
          @Override
          public void reset(boolean isExclusive)
          {
            stringPoolEntry = (CommonUtil.StringPool.StringPoolEntry)getEntry();
            super.reset(isExclusive);
          }
        };

      /**
       * An access unit for exclusive use in {@link #internArray(String[], int, int, int)}.
       */
      protected SegmentSequence.StringArrayPool.SegmentsAndSegmentCountAccessUnit stringArraySegmentsAndSegmentCountAccessUnit = new SegmentSequence.StringArrayPool.SegmentsAndSegmentCountAccessUnit(null);

      protected URIPoolAccessUnitBase(Pool.AccessUnit.Queue<URI> queue)
      {
        super(queue);
      }

      @Override
      protected URI getValue()
      {
        throw new UnsupportedOperationException();
      }

      @Override
      protected void setValue(URI value)
      {
        throw new UnsupportedOperationException();
      }

      @Override
      protected boolean setArbitraryValue(Object value)
      {
        throw new UnsupportedOperationException();
      }

      protected String intern(String string)
      {
        stringAccessUnit.setValue(string);
        return CommonUtil.STRING_POOL.doIntern(false, stringAccessUnit);
      }

      protected String intern(boolean toLowerCase, String string)
      {
        stringAccessUnit.setValue(toLowerCase, string);
        return CommonUtil.STRING_POOL.doIntern(false, stringAccessUnit);
      }

      protected String intern(String string, int offset, int count, int hashCode)
      {
        substringAccessUnit.setValue(string, offset, count, hashCode);
        return CommonUtil.STRING_POOL.doIntern(false, substringAccessUnit);
      }

      protected String intern(String string, int offset, int count)
      {
        substringAccessUnit.setValue(string, offset, count);
        return CommonUtil.STRING_POOL.doIntern(false, substringAccessUnit);
      }

      protected String intern(char[] characters, int offset, int count)
      {
        charactersAccessUnit.setValue(characters, offset, count);
        return CommonUtil.STRING_POOL.doIntern(false, charactersAccessUnit);
      }

      protected String intern(char[] characters, int offset, int count, int hashCode)
      {
        charactersAccessUnit.setValue(characters, offset, count, hashCode);
        return CommonUtil.STRING_POOL.doIntern(false, charactersAccessUnit);
      }

      protected String[] internArray(String[] segments, int offset, int segmentCount, int hashCode)
      {
        stringArraySegmentsAndSegmentCountAccessUnit.setValue(segments, offset, segmentCount, hashCode);
        return SegmentSequence.STRING_ARRAY_POOL.doIntern(false, stringArraySegmentsAndSegmentCountAccessUnit);
      }

      @Override
      public void reset(boolean isExclusive)
      {
        stringPoolEntry = null;
        super.reset(isExclusive);
      }
    }

    /**
     * Access units for basic string access.
     */
    protected final StringAccessUnit.Queue stringAccessUnits = new StringAccessUnit.Queue(this);

    /**
     * An access unit for basic string access.
     */
    protected static class StringAccessUnit extends URIPoolAccessUnitBase
    {
      protected static class Queue extends AccessUnit.Queue<URI>
      {
        private static final long serialVersionUID = 1L;

        final protected URIPool pool;

        public Queue(URIPool pool)
        {
          this.pool = pool;
        }

        @Override
        public StringAccessUnit pop(boolean isExclusive)
        {
          return (StringAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<URI> newAccessUnit()
        {
          return new StringAccessUnit(this, pool);
        }
      }

      /**
       * This unit's pool.
       */
      protected final URIPool pool;

      /**
       * The value being accessed.
       */
      protected String value;

      /**
       * The cached hash code computed by {@link #findMajorSeparator(int, String, int)} and {@link #findSegmentEnd(int, String, int)}.
       */
      protected int findHashCode;

      /**
       * The cached terminating character computed by {@link #findMajorSeparator(int, String, int)} and {@link #findSegmentEnd(int, String, int)}.
       */
      protected char findTerminatingCharacter;

      /**
       * An access unit for exclusive use in {@link #internArray(String, int, int, int)}.
       */
      protected SegmentSequence.StringArrayPool.SubstringAccessUnit stringArraySubstringAccessUnit = new SegmentSequence.StringArrayPool.SubstringAccessUnit(null);

      /**
       * An access unit for exclusive use in {@link #internArray(int, String[], int, String, int, int, int)}.
       */
      protected SegmentSequence.StringArrayPool.SegmentsAndSubsegmentAccessUnit stringArraySegmentsAndSubsegmentAccessUnit = new SegmentSequence.StringArrayPool.SegmentsAndSubsegmentAccessUnit(null);

      protected String[] internArray(String segment, int offset, int count, int hashCode)
      {
        stringArraySubstringAccessUnit.setValue(segment, offset, count, hashCode);
        return SegmentSequence.STRING_ARRAY_POOL.doIntern(false, stringArraySubstringAccessUnit);
      }

      protected String[] internArray(int hashCode, String[] segments, int segmentCount, String segment, int offset, int count, int segmentHashCode)
      {
        if (segmentCount == 0)
        {
          return internArray(segment, offset, count, segmentHashCode);
        }
        else
        {
          stringArraySegmentsAndSubsegmentAccessUnit.setValue(hashCode, segments, segmentCount, segment, offset, count, segmentHashCode);
          return SegmentSequence.STRING_ARRAY_POOL.doIntern(false, stringArraySegmentsAndSubsegmentAccessUnit);
        }
      }

      /**
       * Creates an instance managed by this queue and pool.
       */
      protected StringAccessUnit(Queue queue, URIPool pool)
      {
        super(queue);
        this.pool = pool;
      }

      /**
       * Caches the parameters.
       */
      protected void setValue(String value)
      {
        this.value = value;
        this.hashCode = value.hashCode();
      }

      /**
       * Caches the parameters.
       */
      protected void setValue(String value, int hashCode)
      {
        this.value = value;
        this.hashCode = hashCode;
      }

      @Override
      protected boolean matches(URI value)
      {
        return value.matches(this.value);
      }

      @Override
      public URI match()
      {
        // If we fail to match, use getInternalizedValue to parse and cache an instance.
        //
        URI result = super.match();
        return result == null ? getInternalizedValue() : result;
      }

      @Override
      public URI getInternalizedValue()
      {
        return parseIntoURI(value);
      }

      /**
       * A string-parsing implementation.
       * This method creates instances in the pool as a side-effect.
       * Note that we never pass in a string with a fragment separator to this method.
       */
      protected URI parseIntoURI(String uri)
      {
        // The initial values for what we'll compute.
        //
        boolean hasExpectedHashCode = true;
        boolean isSchemeNormal = true;
        String scheme = null;
        String authority = null;
        String device = null;
        boolean absolutePath = false;
        String[] segments = NO_SEGMENTS;
        int segmentsHashCode = 1;
        String query = null;
        boolean isArchiveScheme = false;
        boolean isPlatformScheme = false;

        // Look for the major separator, i.e., one of ":/?"
        //
        int length = uri.length();
        int i = 0;
        int j = findMajorSeparator(length, uri, i);

        // If we've found the scheme separator...
        //
        if (findTerminatingCharacter == SCHEME_SEPARATOR)
        {
          // Look if the scheme's hash code matches one of the most likely schemes we expect to find...
          //
          int findHashCode = this.findHashCode;
          if (findHashCode == SCHEME_PLATFORM_HASH_CODE)
          {
            scheme = SCHEME_PLATFORM;
            isPlatformScheme = true;
          }
          else if (findHashCode == SCHEME_FILE_HASH_CODE)
          {
            scheme = SCHEME_FILE;
          }
          else if (findHashCode == SCHEME_HTTP_HASH_CODE)
          {
            scheme = SCHEME_HTTP;
          }
          else if (findHashCode == SCHEME_JAR_HASH_CODE)
          {
            scheme = SCHEME_JAR;
            isArchiveScheme = true;
          }
          else if (findHashCode == SCHEME_ARCHIVE_HASH_CODE)
          {
            scheme = SCHEME_ARCHIVE;
            isArchiveScheme = true;
          }
          else if (findHashCode == SCHEME_ZIP_HASH_CODE)
          {
            scheme = SCHEME_ZIP;
            isArchiveScheme = true;
          }

          // If it isn't one of the expected schemes, or it is, then we need to make sure it's really equal to what's in the URI, not an accidential hash code collision...
          //
          if (scheme == null || !scheme.regionMatches(0, uri, 0, j))
          {
            // Intern the provided version of the scheme.
            //
            String unnormalizedScheme = intern(uri, 0, j, findHashCode);

            // Intern the lower case version of the scheme.
            //
            stringAccessUnit.setValue(true, unnormalizedScheme);
            stringAccessUnit.add(unnormalizedScheme, stringPoolEntry);
            scheme = stringAccessUnit.match();
            stringAccessUnit.reset(false);

            // Determine if the provided version is in normal form, i.e., already lower cased.
            //
            isSchemeNormal = unnormalizedScheme == scheme;

            // Check whether it's a different hash code; we'll need to compute the right hash code if we've lower cased the scheme.
            //
            hasExpectedHashCode = scheme.hashCode() == findHashCode;

            // Check if it's an archive scheme...
            //
            for (String archiveScheme : ARCHIVE_SCHEMES)
            {
              if (scheme == archiveScheme)
              {
                isArchiveScheme = true;
                break;
              }
            }

            isPlatformScheme = scheme == SCHEME_PLATFORM;
          }

          // Look for the end of the following segment.
          //
          i = j + 1;
          j = findSegmentEnd(length, uri, i);
        }

        if (isArchiveScheme)
        {
          // Look for the archive separator, which must be present.
          //
          j = uri.lastIndexOf(ARCHIVE_SEPARATOR);
          if (j == -1)
          {
            throw new IllegalArgumentException("no archive separator");
          }

          // In that case it's an absolute path and the authority is everything up to and including the ! of the archive separator.
          //
          absolutePath = true;
          authority = intern(uri, i, ++j - i);

          // Look for the end of the following segment starting after the / in the archive separator.
          //
          i = j + 1;
          j = findSegmentEnd(length, uri, i);
        }
        else if (i == j && findTerminatingCharacter == SEGMENT_SEPARATOR)
        {
          // If we're starting with a / so it's definitely hierarchical.
          // Look for the next segment end, and if we find a / as the next character...
          //
          j = findSegmentEnd(length, uri, ++i);
          if (j == i && findTerminatingCharacter == SEGMENT_SEPARATOR)
          {
            // Look for the segment that follows; it's the authority, even if it's empty.
            //
            j = findSegmentEnd(length, uri, ++i);
            authority = intern(uri, i,  j - i, findHashCode);
            i = j;

            // If the authority is followed by a /...
            //
            if (findTerminatingCharacter == SEGMENT_SEPARATOR)
            {
              // Then it's an absolute path so look for the end of the following segment.
              //
              absolutePath = true;
              j = findSegmentEnd(length, uri, ++i);
            }
          }
          else
          {
            // Because it started with a /, the current segment, which we'll capcture below, is the start of an absolute path.
            //
            absolutePath = true;
          }
        }
        else if (scheme != null)
        {
          // There's a scheme, but it's not followed immediately by a /, so it's an opaque URI.
          //
          authority = intern(uri, i, length - i);
          URI resultURI = pool.intern(false, URIPool.URIComponentsAccessUnit.VALIDATE_NONE, false, scheme, authority, null, false, null, null);

          // If something tries to add an entry for this access unit, we'd better be sure that the hash code is that of the transformed URI.
          //
          this.hashCode = resultURI.hashCode();

          return resultURI;
        }

        // Start analyzing the first segment...
        //
        boolean segmentsRemain = false;
        int start = i;
        int len =  j - i;
        i = j;
        if (len == 0)
        {
          // If we found a /, then we have one single empty segment so far.
          //
          if (findTerminatingCharacter != QUERY_SEPARATOR)
          {
            segments = ONE_EMPTY_SEGMENT;
            segmentsHashCode = 31;

            // Look for the next segment. There is one even if it's empty.
            //
            j = findSegmentEnd(length, uri, ++i);
            segmentsRemain = true;
          }
        }
        // If this first segment ends with a : and we're not processing an archive URI, then treat it as the device...
        //
        else if (!isArchiveScheme && !isPlatformScheme && uri.charAt(j - 1) == DEVICE_IDENTIFIER)
        {
          device = intern(uri, start, len, findHashCode);

          // If the device is at the end of the URI...
          //
          if (findTerminatingCharacter == QUERY_SEPARATOR)
          {
            // Then there's no absolute path and no segments remain.
            //
            absolutePath = false;
          }
          else
          {
            // Look for the segment that follows.
            //
            j = findSegmentEnd(length, uri, ++i);

            // If it's empty, then we ignore it because the empty segment is implicit from this being an absolute path.
            // Or, if there is another /, then we have another segment to process.
            //
            segmentsRemain = i != j || findTerminatingCharacter == SEGMENT_SEPARATOR;
          }
        }
        else
        {
          // Append the segment...
          //
          segments = internArray(uri, start, j - start, findHashCode);
          segmentsHashCode = 31 * segmentsHashCode + findHashCode;

          // If we're not already at the end...
          //
          if (findTerminatingCharacter != QUERY_SEPARATOR)
          {
            // Find the end of the following segment, and indicate that we should process it.
            //
            j = findSegmentEnd(length, uri, ++i);
            segmentsRemain = true;
          }
        }

        // If we have more segments to process...
        //
        if (segmentsRemain)
        {
          for (;;)
          {
            // Append that segment...
            //
            segments = internArray(segmentsHashCode, segments, segments.length, uri, i, j - i, findHashCode);
            segmentsHashCode = 31 * segmentsHashCode + findHashCode;
            i = j;

            // If the current segment is terminated by a /...
            //
            if (findTerminatingCharacter == SEGMENT_SEPARATOR)
            {
              // Find the end of the following segment.
              //
              j = findSegmentEnd(length, uri, ++i);
            }
            else
            {
              // Otherwise, we're done.
              //
              break;
            }
          }
        }

        // If we're not yet past the end of the string, what remains must be the query string.
        //
        if (i++ < length)  // implies uri.charAt(i) == QUERY_SEPARATOR
        {
          // Intern what's left to the end of the string.
          //
          query = intern(uri, i, length - i);
        }

        // If we're sure we have the right hash code (the scheme was not lower cased), we can use it, otherwise, we must compute a hash code.
        //
        URI resultURI;
        if (hasExpectedHashCode)
        {
          resultURI = pool.intern(true, true, scheme, authority, device, absolutePath, segments, query, hashCode);
        }
        else
        {
          resultURI = pool.intern(true, URIPool.URIComponentsAccessUnit.VALIDATE_NONE, true, scheme, authority, device, absolutePath, segments, query);

          // If something tries to add an entry for this access unit, we'd better be sure that the hash code is that of the transformed URI.
          //
          this.hashCode = resultURI.hashCode();
        }

        if (isSchemeNormal)
        {
          resultURI.cacheString(uri);
        }

        return resultURI;
      }

      /**
       * Looks for a '/', ':', or '?', computing the {@link #findHashCode hash code} and {@link #findTerminatingCharacter setting the character} that terminated the scan.
       */
      protected int findMajorSeparator(int length, String s, int i)
      {
        findTerminatingCharacter = QUERY_SEPARATOR;
        int hashCode = 0;
        for (; i < length; i++)
        {
          char c = s.charAt(i);
          if (c == SEGMENT_SEPARATOR || c == SCHEME_SEPARATOR || c == QUERY_SEPARATOR)
          {
            findTerminatingCharacter = c;
            break;
          }
          hashCode = 31 * hashCode + c;
        }
        findHashCode = hashCode;
        return i;
      }

      /**
       * Looks for a '/', or '?', computing the {@link #findHashCode hash code} and {@link #findTerminatingCharacter setting the character} that terminated the scan.
       */
      protected int findSegmentEnd(int length, String s, int i)
      {
        findTerminatingCharacter = QUERY_SEPARATOR;
        int hashCode = 0;
        for (; i < length; i++)
        {
          char c = s.charAt(i);
          if (c == SEGMENT_SEPARATOR || c == QUERY_SEPARATOR)
          {
            findTerminatingCharacter = c;
            break;
          }
          hashCode = 31 * hashCode + c;
        }
        findHashCode = hashCode;
        return i;
      }

      @Override
      public void reset(boolean isExclusive)
      {
        value = null;
        super.reset(isExclusive);
      }
    }

    /**
     * Access units for platform URI string-based access.
     */
    protected final PlatformAccessUnit.Queue platformAccessUnits = new PlatformAccessUnit.Queue();

    /**
     * An access units for platform URI string-based access.
     */
    protected static class PlatformAccessUnit extends URIPoolAccessUnitBase
    {
      protected static class Queue extends AccessUnit.Queue<URI>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public PlatformAccessUnit pop(boolean isExclusive)
        {
          return (PlatformAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<URI> newAccessUnit()
        {
          return new PlatformAccessUnit(this);
        }
      }

      /**
       * The hash code of <code>"platform:/resource/"</code>.
       */
      protected static final int PLATFORM_RESOURCE_BASE_FULL_HASH_CODE = "platform:/resource/".hashCode();

      /**
       * The hash code of <code>"platform:/plugin/"</code>.
       */
      protected static final int PLATFORM_PLUGIN_BASE_FULL_HASH_CODE = "platform:/plugin/".hashCode();

      /**
       * The hash code of <code>"platform:/resource"</code>.
       */
      protected static final int PLATFORM_RESOURCE_BASE_INITIAL_HASH_CODE = "platform:/resource".hashCode();

      /**
       * The hash code of <code>"platform:/plugin/"</code>.
       */
      protected static final int PLATFORM_PLUGIN_BASE_INITIAL_HASH_CODE = "platform:/plugin".hashCode();

      /**
       * The base that implicitly precedes the {@link #path}.
       */
      protected String base;

      /**
       * The path being accessed.
       */
      protected String path;

      /**
       * Whether the pathName needs encoding.
       */
      protected boolean encode;

      /**
       * A buffer uses for processing the path.
       */
      protected char[] characters = new char[100];

      /**
       * The accumulated segments pulled from the path.
       */
      protected String[] segments = new String[20];

      /**
       * The number of {@link #segments}.
       */
      protected int segmentCount;

      /**
       * The boundaries of the path segments.
       */
      protected int[] segmentBoundaries = new int[100];

      /**
       * The hash code of the path segments.
       */
      protected int[] segmentHashCodes = new int[100];

      /**
       * The path after it's been encoded.
       */
      protected String encodedPath;

      /**
       * Creates and instance managed by the given queue.
       */
      protected PlatformAccessUnit(Queue queue)
      {
        super(queue);
      }

      /**
       * Caches the parameters and computes the hash code, which can involve encoding the path.
       */
      protected void setValue(String base, String path, boolean encode)
      {
        this.base = base;
        this.path = path;
        this.encode = encode;

        int length = path.length();
        if (length == 0)
        {
          encodedPath = "/";
          segmentBoundaries[segmentCount] = 0;
          segmentBoundaries[segmentCount++] = 1;
          this.hashCode =  base == SEGMENT_RESOURCE ? PLATFORM_RESOURCE_BASE_FULL_HASH_CODE : PLATFORM_PLUGIN_BASE_FULL_HASH_CODE;
        }
        else
        {
          // At most each character could need encoding and that would triple the length.
          //
          int maxEncodedLength = encode ? 3 * length : length;
          if (characters.length <= maxEncodedLength)
          {
            // Leave room for one more character, i.e., the leading / that may need to be added.
            //
            characters = new char[maxEncodedLength + 1];
          }

          // There can be at most as many segments as characters.
          //
          if (segmentBoundaries.length < length)
          {
            segmentBoundaries = new int[length];
            segmentHashCodes = new int[length];
          }

          // Keep track of whether any characters were encoded.
          //
          boolean isModified = false;

          // Treat this character the same as a /.  In fact, on non-Wwindows systems this will be a / anyway.
          //
          char separatorchar = File.separatorChar;

          char character = path.charAt(0);
          if (character == SEGMENT_SEPARATOR)
          {
            // If the path starts with a /, copy over all the characters into the buffer.
            //
            path.getChars(0, length, characters, 0);
          }
          else if (character == separatorchar)
          {
            // If the path starts with a \, put a / at the start and copy over all the characters except the first into the buffer.
            //
            characters[0] = SEGMENT_SEPARATOR;
            if (length != 1)
            {
              path.getChars(1, length, characters, 1);
            }
            // Indicate that we've modified the original path.
            //
            isModified = true;
          }
          else
          {
            // It doesn't start with a separator character so put a / at the start and copy all the characters into the buffer after that.
            //
            characters[0] = SEGMENT_SEPARATOR;
            path.getChars(0, length, characters, 1);

            // The string is now one character longer and we've modified the path.
            //
            ++length;
            isModified = true;
          }

          // The first character in the buffer is a /, so that's the initial hash code.
          //
          int hashCode = SEGMENT_SEPARATOR;
          int segmentHashCode = 0;

          // If we're encoding...
          //
          if (encode)
          {
            // Iterate over all the characters...
            //
            for (int i = 1; i < length; ++i)
            {
              // If the character is one that needs encoding, including the path separators...
              //
              character = characters[i];
              if (character < 160 && !URI.matches(character, SEGMENT_CHAR_HI, SEGMENT_CHAR_LO))
              {
                if (character == SEGMENT_SEPARATOR)
                {
                  // If it's a /, cache the segment hash code, and boundary, reset the segment hash code, and compose the complete hash code.
                  //
                  segmentHashCodes[segmentCount] = segmentHashCode;
                  segmentBoundaries[segmentCount++] = i;
                  segmentHashCode = 0;
                  hashCode = 31 * hashCode + SEGMENT_SEPARATOR;
                }
                else if (character == separatorchar)
                {
                  // If it's a \, convert it to a /, cache the segment hash code, and boundary, reset the segment hash code, and compose the complete hash code, and indicate we've modified the original path.
                  //
                  characters[i] = SEGMENT_SEPARATOR;
                  segmentHashCodes[segmentCount] = segmentHashCode;
                  segmentBoundaries[segmentCount++] = i;
                  segmentHashCode = 0;
                  hashCode = 31 * hashCode + SEGMENT_SEPARATOR;
                  isModified = true;
                }
                else
                {
                  // Escape the character.
                  //
                  isModified = true;

                  // Shift the buffer to the right 3 characters.
                  //
                  System.arraycopy(characters, i + 1, characters, i + 3, length - i - 1);

                  // Add a % and compose the segment hashCode and the complete hash code.
                  //
                  characters[i] = ESCAPE;
                  hashCode = 31 * hashCode + ESCAPE;
                  segmentHashCode = 31 * segmentHashCode + ESCAPE;

                  // Add the first hex digit and compose the segment hashCode and the complete hash code.
                  //
                  char firstHexCharacter = characters[++i] = HEX_DIGITS[(character >> 4) & 0x0F];
                  hashCode = 31 * hashCode + firstHexCharacter;
                  segmentHashCode = 31 * segmentHashCode + firstHexCharacter;

                  // Add the second hex digit and compose the segment hashCode and the complete hash code.
                  //
                  char secondHexCharacter = characters[++i] = HEX_DIGITS[character & 0x0F];
                  hashCode = 31 * hashCode + secondHexCharacter;
                  segmentHashCode = 31 * segmentHashCode + secondHexCharacter;

                  // The length is two characters bigger than before.
                  //
                  length += 2;
                }
              }
              else
              {
                // No encoding required, so just compose the segment hash code and the complete hash code.
                //
                hashCode = 31 * hashCode + character;
                segmentHashCode = 31 * segmentHashCode + character;
              }
            }
          }
          else
          {
            // Don't encode any characters.
            //
            for (int i = 1; i < length; ++i)
            {
              character = characters[i];
              if (character == SEGMENT_SEPARATOR)
              {
                // If it's a /, cache the segment hash code, and boundary, reset the segment hash code, and compose the complete hash code.
                //
                segmentHashCodes[segmentCount] = segmentHashCode;
                segmentBoundaries[segmentCount++] = i;
                segmentHashCode = 0;
                hashCode = 31 * hashCode + SEGMENT_SEPARATOR;
              }
              else if (character == separatorchar)
              {
                // If it's a \, convert it to a /, cache the segment hash code, and boundary, reset the segment hash code, and compose the complete hash code, and indicate we've modified the original path.
                //
                characters[i] = SEGMENT_SEPARATOR;
                segmentHashCodes[segmentCount] = segmentHashCode;
                segmentBoundaries[segmentCount++] = i;
                segmentHashCode = 0;
                hashCode = 31 * hashCode + SEGMENT_SEPARATOR;
                isModified = true;
              }
              else
              {
                // No encoding required, so just compose the segment hash code and the complete hash code.
                //
                hashCode = 31 * hashCode + character;
                segmentHashCode = 31 * segmentHashCode + character;
              }
            }
          }

          // Set cache the final segment's hash code and boundary.
          //
          segmentHashCodes[segmentCount] = segmentHashCode;
          segmentBoundaries[segmentCount++] = length;

          // Compose the overall hash code to include the base's hash code.
          //
          this.hashCode = (base == SEGMENT_RESOURCE ? PLATFORM_RESOURCE_BASE_INITIAL_HASH_CODE : PLATFORM_PLUGIN_BASE_INITIAL_HASH_CODE) * CommonUtil.powerOf31(length) + hashCode;
          encodedPath = isModified ? intern(characters, 0, length, hashCode) : path;
        }
      }

      @Override
      protected boolean matches(URI value)
      {
        return value.matches(base, encodedPath);
      }

      @Override
      public URI getInternalizedValue()
      {
        // Ensure that there are enough segments to hold the results.
        //
        if (segments.length <= segmentCount)
        {
          segments = new String[segmentCount + 1];
        }

        // Start with the given base segment.
        //
        segments[0] = base;

        // Compute the hash code of the segments array.
        // The offset is the start of the segment within the character's buffer, which is initially at index 1, i.e., after the leading /.
        //
        int hashCode = 31 + base.hashCode();
        for (int i = 0, offset = 1, segmentCount = this.segmentCount; i < segmentCount; )
        {
          // Get the segment's hash code.
          //
          int segmentHashCode = segmentHashCodes[i];

          // Get the terminating boundary for this segment.
          //
          int end = segmentBoundaries[i++];

          // The number of characters in the segment.
          //
          int count = end - offset;

          // Intern that character range with the known segment hash code.
          //
          segments[i] = intern(characters, offset, count, segmentHashCode);

          // Compose the segment's hash code.
          //
          hashCode = 31 * hashCode + segmentHashCode;

          // Set the offset to be one character after the terminating /.
          offset = end + 1;
        }

        // Create a hierarchical platform-scheme URI from the interned segments.
        //
        return new Hierarchical(this.hashCode, true, SCHEME_PLATFORM, null, null, true, internArray(segments, 0, segmentCount + 1, hashCode), null);
      }

      @Override
      public void reset(boolean isExclusive)
      {
        for (int i = 1; i <= segmentCount; ++i)
        {
          segments[i] = null;
        }
        segmentCount = 0;
        encodedPath = null;
        path = null;

        super.reset(isExclusive);
      }
    }

    /**
     * Access units for file URI string-based access.
     */
    protected final FileAccessUnit.Queue fileAccessUnits = new FileAccessUnit.Queue();

    /**
     * An Access unit for file URI string-based access.
     */
    protected static class FileAccessUnit extends URIPoolAccessUnitBase
    {
      protected static class Queue extends AccessUnit.Queue<URI>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public FileAccessUnit pop(boolean isExclusive)
        {
          return (FileAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<URI> newAccessUnit()
        {
          return new FileAccessUnit(this);
        }
      }

      /**
       * The base URI for file scheme URIs.
       */
      protected static final String FILE_BASE = "file:/";

      /**
       * The length of the base URI for file scheme URIs.
       */
      protected static final int FILE_BASE_LENGTH = "file:/".length();

      /**
       * The hash code of the base URI for file scheme URIs.
       */
      protected static final int FILE_BASE_HASH_CODE = FILE_BASE.hashCode();

      /**
       * The file path being accessed.
       */
      protected String path;

      /**
       * The buffer for absolute file paths.
       */
      protected char[] absoluteCharacters = new char[100];

      /**
       * The buffer for relative file paths.
       */
      protected char[] relativeCharacters = new char[100];

      /**
       * The segments of the path.
       */
      protected String[] segments = new String[20];

      /**
       * The number of segments in the path.
       */
      protected int segmentCount;

      /**
       * The boundaries of the segments in the path.
       */
      protected int[] segmentBoundaries = new int[100];

      /**
       * The hash codes of the segments in the path.
       */
      protected int[] segmentHashCodes = new int[100];

      /**
       * The final encoded path.
       */
      protected String encodedPath;

      /**
       * Whether the file path represents an absolute file.
       */
      protected boolean isAbsoluteFile;

      /**
       * Whether the path itself is absolute.
       */
      protected boolean isAbsolutePath;

      /**
       * Creates an instance managed by the given queue.
       */
      public FileAccessUnit(Queue queue)
      {
        super(queue);

        // Caches the base absolute file path characters.
        //
        FILE_BASE.getChars(0, FILE_BASE_LENGTH, absoluteCharacters, 0);
      }

      /**
       * Caches the parameter and computes the hash code.
       */
      protected void setValue(String path)
      {
        this.path = path;

        int length = path.length();
        if (length == 0)
        {
          // It's just the empty string with the zero hash code.
          //
          encodedPath = "";
          this.hashCode =  0;
        }
        else
        {
          // Is this path considered an absolute file by the file system implementation?
          //
          isAbsoluteFile = new File(path).isAbsolute();

          // This will use either the absoluteCharacters or the relativeCharacters...
          //
          char[] characters;

          // Check the first character.
          //
          char character = path.charAt(0);

          // We're convert this character to a /.
          //
          char separatorchar = File.separatorChar;

          // Compose the hash code.
          //
          int hashCode;

          // Walk the path segments...
          //
          int i;

          // There can be at most as many boundaries as characters.
          //
          if (segmentBoundaries.length < length)
          {
            segmentBoundaries = new int[length];
            segmentHashCodes = new int[length];
          }

          if (isAbsoluteFile)
          {
            // If it's an absolute file then it must be an absolute path.
            //
            isAbsolutePath = true;

            // There can be at most as many encoded characters as three times the length, plus we need to account for the characters in the base.
            //
            int maxEncodedLength = 3 * length + FILE_BASE_LENGTH;
            if (absoluteCharacters.length <= maxEncodedLength)
            {
              // Allocate one slightly larger and copy in the base path.
              //
              absoluteCharacters = new char[maxEncodedLength + 1];
              FILE_BASE.getChars(0, FILE_BASE_LENGTH, absoluteCharacters, 0);
            }

            // Process the absolute characters.
            //
            characters = absoluteCharacters;

            if (character == SEGMENT_SEPARATOR || character == separatorchar)
            {
              // If the path starts with a separator, copy over the characters after that / to the buffer after the base.
              //
              path.getChars(1, length, characters, FILE_BASE_LENGTH);

              // The length is larger by one less than the base.
              //
              length += FILE_BASE_LENGTH - 1;
            }
            else
            {
              // If the path doesn't start with a /, copy over all the characters into the buffer after the base.
              //
              path.getChars(0, length, characters, FILE_BASE_LENGTH);

              // The length is larger by the base.
              //
              length += FILE_BASE_LENGTH;
            }

            // The first boundary is after the base and that's where we start processing the characters.
            //
            segmentBoundaries[0] = i = FILE_BASE_LENGTH;

            // The hash code so far is the base's hash code.
            //
            hashCode = FILE_BASE_HASH_CODE;
          }
          else
          {
            // There can be at most as many encoded characters as three times the length.
            //
            int maxEncodedLength = 3 * length;
            if (relativeCharacters.length <= maxEncodedLength)
            {
              // Allocate one slightly larger.
              //
              relativeCharacters = new char[maxEncodedLength + 1];
            }

            // Process the relative characters.
            //
            characters = relativeCharacters;

            if (character == SEGMENT_SEPARATOR || character == separatorchar)
            {
              // If the path starts with a separator, then it's an absolute path.
              //
              isAbsolutePath = true;

              // Set the leading / and   copy over the characters after the leading / or \ to the buffer after that.
              //
              characters[0] = SEGMENT_SEPARATOR;
              path.getChars(1, length, characters, 1);

              // The first boundary is after the / and that's where we start processing the characters.
              //
              segmentBoundaries[0] = i = 1;

              // The hash code so far is the /'s hash code.
              //
              hashCode = SEGMENT_SEPARATOR;
            }
            else
            {
              // No leading separator so it's a relative path.
              //
              isAbsolutePath = false;

              //  Copy over all the characters in the bufffer.
              //
              path.getChars(0, length, characters, 0);

              // The first boundary is at the start and that's where we start processing the characters.
              //
              segmentBoundaries[0] = i = 0;

              // The hash code so far is zero.
              //
              hashCode = 0;
            }
          }

          // Compose the segment hash code as we scan the characters.
          //
          int segmentHashCode = 0;
          for (; i < length; ++i)
          {
            // If the current character needs encoding (including the separator characters) or is the device identifier and we're processing the first segment of an absolute path...
            //
            character = characters[i];
            if (character < 160 && (!URI.matches(character, SEGMENT_CHAR_HI, SEGMENT_CHAR_LO) || character == DEVICE_IDENTIFIER && !isAbsolutePath && segmentCount == 0))
            {
              if (character == SEGMENT_SEPARATOR)
              {
                // If it's a /, cache the segment hash code and the segment boundary, reset the segment hash code, and compose the segments hash code.
                //
                segmentHashCodes[segmentCount] = segmentHashCode;
                segmentBoundaries[++segmentCount] = i;
                segmentHashCode = 0;
                hashCode = 31 * hashCode + SEGMENT_SEPARATOR;
              }
              else if (character == separatorchar)
              {
                // If it's a \, change it to a /, cache the segment hash code and the segment boundary, reset the segment hash code, and compose the segments hash code.
                //
                characters[i] = SEGMENT_SEPARATOR;
                segmentHashCodes[segmentCount] = segmentHashCode;
                segmentBoundaries[++segmentCount] = i;
                segmentHashCode = 0;
                hashCode = 31 * hashCode + SEGMENT_SEPARATOR;
              }
              else
              {
                // Shift the buffer to the right 3 characters.
                //
                System.arraycopy(characters, i + 1, characters, i + 3, length - i - 1);

                // Add a % and compose the segment hashCode and the complete hash code.
                //
                characters[i] = ESCAPE;
                hashCode = 31 * hashCode + ESCAPE;
                segmentHashCode = 31 * segmentHashCode + ESCAPE;

                // Add the first hex digit and compose the segment hashCode and the complete hash code.
                //
                char firstHexCharacter = characters[++i] = HEX_DIGITS[(character >> 4) & 0x0F];
                hashCode = 31 * hashCode + firstHexCharacter;
                segmentHashCode = 31 * segmentHashCode + firstHexCharacter;

                // Add the second hex digit and compose the segment hashCode and the complete hash code.
                //
                char secondHexCharacter = characters[++i] = HEX_DIGITS[character & 0x0F];
                hashCode = 31 * hashCode + secondHexCharacter;
                segmentHashCode = 31 * segmentHashCode + secondHexCharacter;

                // The length is two characters bigger than before.
                //
                length += 2;
              }
            }
            else
            {
              // No encoding required, so just compose the segment hash code and the complete hash code.
              //
              hashCode = 31 * hashCode + character;
              segmentHashCode = 31 * segmentHashCode + character;
            }
          }

          // Set cache the final segment's hash code and boundary.
          //
          segmentHashCodes[segmentCount] = segmentHashCode;
          segmentBoundaries[++segmentCount] = length;

          // Compose the overall hash code to include the base's hash code.
          //
          this.hashCode = hashCode;

          // Cache the encoded path.
          //
          encodedPath = intern(characters, 0, length, hashCode);
        }
      }

      @Override
      protected boolean matches(URI value)
      {
        return value.matches(encodedPath);
      }

      @Override
      public URI getInternalizedValue()
      {
        // Ensure that we have enough room for all the segments.
        //
        int segmentCount = this.segmentCount;
        if (segments.length <= segmentCount)
        {
          segments = new String[segmentCount + 1];
        }

        // Process the appropriate characters.
        //
        char[] characters = isAbsoluteFile ? absoluteCharacters : relativeCharacters;

        // Parse out the device and the authority...
        //
        String device = null;
        String authority = null;

        // The initial hash code for the over all final segments.
        //
        int segmentsHashCode = 1;

        // Where we expect the special device segment to appear.
        //
        int deviceIndex = 0;

        // An empty segment at this index will be igored.
        //
        int ignoredEmptySegmentIndex = -1;

        // Whether we ignored an empty segment.
        //
        boolean ignoredEmptySegment = false;

        // Process all the segments...
        //
        for (int i = 0, segmentIndex = 0, offset = segmentBoundaries[0]; segmentIndex < segmentCount; ++i)
        {
          // The end of the current segment's boundary.
          //
          int end = segmentBoundaries[i + 1];

          // The number of characters of the current segment.
          //
          int count = end - offset;

          // If this is an empty segment we wish to ignore...
          //
          if (i == ignoredEmptySegmentIndex && count == 0)
          {
            // Ignore it and indicate that we ignored a leading empty segment.
            //
            --segmentCount;
            ignoredEmptySegment = true;
          }
          else
          {
            // Retrieve the segment's hash code.
            //
            int segmentHashCode = segmentHashCodes[i];

            // Intern the segment characters...
            //
            String segment = intern(characters, offset, count, segmentHashCode);

            // If we're at a device index while processing an absolute file, and we have an empty segment that's not the only segment or the last character of the segment is the device identifier...
            //
            if (i == deviceIndex && isAbsoluteFile && (count == 0 && segmentCount > 1 || characters[end - 1] == DEVICE_IDENTIFIER))
            {
              // If the segment has zero length...
              //
              if (count == 0)
              {
                // Proceed to the next segment; there must be one because of the guard...
                //
                offset = end + 1;
                segmentHashCode = segmentHashCodes[++i];
                end = segmentBoundaries[i + 1];
                count = end - offset;

                // This segment is really the authority...
                //
                authority = intern(characters, offset, count, segmentHashCode);

                // There are now two fewer segments.
                //
                segmentCount -= 2;

                // We should still check for a device at index 2.
                //
                deviceIndex = 2;

                // We should still consider ignoring an empty segment if it's at index 2.
                //
                ignoredEmptySegmentIndex = 2;
              }
              else
              {
                // Otherwise the segment must end with a :, so it must be the device.
                //
                device = segment;

                // There's one fewer real segment.
                //
                --segmentCount;

                // We should ignore an empty segment if it comes next.
                //
                ignoredEmptySegmentIndex = deviceIndex + 1;
              }
            }
            else
            {
              // It's a normal segment so include it and compose the overall segments hash code.
              //
              segments[segmentIndex++] = segment;
              segmentsHashCode = 31 * segmentsHashCode + segmentHashCode;
            }
          }

          // Continue with the characters after the current segment's closing boundary.
          //
          offset = end + 1;
        }

        // Intern the segments array itself.
        //
        String[] internedSegments = internArray(segments, 0, segmentCount, segmentsHashCode);
        if (isAbsoluteFile)
        {
          // If it's absolute, we include the file scheme, and it has an absolute path, if there is one or more segments, or if we ignored an empty segment.
          //
          return new Hierarchical(this.hashCode, true, SCHEME_FILE, authority, device, segmentCount > 0 || ignoredEmptySegment, internedSegments, null);
        }
        else
        {
          // It's a relative URI...
          //
          return new Hierarchical(this.hashCode, true, null, null, null, isAbsolutePath, internedSegments, null);
        }
      }

      @Override
      public void reset(boolean isExclusive)
      {
        for (int i = 1; i <= segmentCount; ++i)
        {
          segments[i] = null;
        }
        segmentCount = 0;
        encodedPath = null;
        path = null;

        super.reset(isExclusive);
      }
    }

    /**
     * Access units for component-based access.
     */
    protected final URIComponentsAccessUnit.Queue uriComponentsAccessUnits = new URIComponentsAccessUnit.Queue();

    /**
     * An Access unit for component-based access.
     */
    protected static class URIComponentsAccessUnit extends URIPoolAccessUnitBase
    {
      /**
       * A value for {@link #validate} that implies no checking or interning of components is required.
       */
      protected static final int VALIDATE_NONE = -1;

      /**
       * A value for {@link #validate} that implies all components need to be validated.
       */
      protected static final int VALIDATE_ALL = -2;

      /**
       * A value for {@link #validate} that implies only the query componet needs validating.
       */
      protected static final int VALIDATE_QUERY = -3;

      protected static class Queue extends AccessUnit.Queue<URI>
      {
        private static final long serialVersionUID = 1L;

        @Override
        public URIComponentsAccessUnit pop(boolean isExclusive)
        {
          return (URIComponentsAccessUnit)super.pop(isExclusive);
        }

        @Override
        protected AccessUnit<URI> newAccessUnit()
        {
          return new URIComponentsAccessUnit(this);
        }
      }

      /**
       * One of the special values {@link #VALIDATE_NONE}, {@link #VALIDATE_ALL}, or {@link #VALIDATE_QUERY}, or the index in the {@link #segments} that need validation.
       */
      int validate;

      /**
       * Whether the components being accesses are for a hierarchical URI
       */
      boolean hierarchical;

      /**
       * The scheme being accessed.
       */
      String scheme;

      /**
       * The authority (or opaque part) being accessed.
       */
      String authority;

      /**
       * The device being accessed.
       */
      String device;
      /**
       * Whether the components being accesses are for an absolute path.
       */
      boolean absolutePath;

      /**
       * The segments being accessed.
       */
      String[] segments;

      /**
       * The query being accessed.
       */
      String query;

      /**
       * An access unit for exclusive use in {@link #internArray(String[], int)}.
       */
      SegmentSequence.StringArrayPool.SegmentsAccessUnit stringArraySegmentsAccessUnit = new SegmentSequence.StringArrayPool.SegmentsAccessUnit(null);

      /**
       * Creates an instance managed by the given queue.
       * @param queue
       */
      protected URIComponentsAccessUnit(Queue queue)
      {
        super(queue);
      }

      protected String[] internArray(String[] segments, int count)
      {
        if (segments == null)
        {
          return SegmentSequence.EMPTY_ARRAY;
        }
        else
        {
          stringArraySegmentsAccessUnit.setValue(true, true, segments, count);
          return SegmentSequence.STRING_ARRAY_POOL.doIntern(false, stringArraySegmentsAccessUnit);
        }
      }

      /**
       * Caches the parameters.
       */
      protected void setValue(boolean hierarchical, String scheme, String authority, String device, boolean absolutePath, String[] segments, String query, int hashCode)
      {
        this.validate = VALIDATE_NONE;
        this.hierarchical = hierarchical;
        this.scheme = scheme;
        this.authority = authority;
        this.device = device;
        this.absolutePath = absolutePath;
        this.segments = segments;
        this.query = query;
        this.hashCode = hashCode;
      }

      /**
       * Caches the parameters and computes the hash code.
       */
      protected void setValue(int validate, boolean hierarchical, String scheme, String authority, String device, boolean absolutePath, String[] segments, String query)
      {
        int hashCode = 0;
        if (scheme != null)
        {
          if (validate == VALIDATE_ALL)
          {
            scheme = intern(true, scheme);
          }
          hashCode = scheme.hashCode() * 31 + SCHEME_SEPARATOR;
        }

        this.validate = validate;
        this.hierarchical = hierarchical;
        this.scheme = scheme;
        this.authority = authority;
        this.device = device;
        this.absolutePath = absolutePath;
        this.segments = segments;
        this.query = query;

        if (hierarchical)
        {
          if (segments == null)
          {
            segments = NO_SEGMENTS;
          }

          this.segments = segments;

          if (authority != null)
          {
            if (!isArchiveScheme(scheme)) hashCode = hashCode * 961 + AUTHORITY_SEPARATOR_HASH_CODE;
            hashCode = hashCode * CommonUtil.powerOf31(authority.length()) + authority.hashCode();
          }

          if (device != null)
          {
            hashCode = hashCode * 31 + SEGMENT_SEPARATOR;
            hashCode = hashCode * CommonUtil.powerOf31(device.length()) + device.hashCode();
          }

          if (absolutePath) hashCode = hashCode * 31 + SEGMENT_SEPARATOR;

          for (int i = 0, len = segments.length; i < len; i++)
          {
            if (i != 0) hashCode = hashCode * 31 + SEGMENT_SEPARATOR;
            String segment = segments[i];
            if (segment == null)
            {
              throw new IllegalArgumentException("invalid segment: null");
            }
            hashCode = hashCode * CommonUtil.powerOf31(segment.length()) + segment.hashCode();
          }

          if (query != null)
          {
            hashCode = hashCode * 31 + QUERY_SEPARATOR;
            hashCode = hashCode * CommonUtil.powerOf31(query.length()) + query.hashCode();
          }
        }
        else
        {
          hashCode = hashCode * CommonUtil.powerOf31(authority.length()) + authority.hashCode();
        }

        this.hashCode = hashCode;
      }

      @Override
      protected boolean matches(URI value)
      {
        return value.matches(validate, hierarchical, scheme, authority, device, absolutePath, segments, query);
      }

      @Override
      public URI getInternalizedValue()
      {
        if (validate == VALIDATE_ALL)
        {
          // Validate all the components.
          //
          validateURI(hierarchical, scheme, authority, device, absolutePath, segments, query, null);

          // Intern the components.
          //
          if (authority != null)
          {
            authority = intern(authority);
          }
          if (device != null)
          {
            device = intern(device);
          }
          segments = internArray(segments, segments.length);
          if (query != null)
          {
            query = intern(query);
          }
        }
        else if (validate == VALIDATE_QUERY)
        {
          // Validate just the query.
          //
          if (!validQuery(query))
          {
            throw new IllegalArgumentException("invalid query portion: " + query);
          }

          // Intern the just the query.
          //
          if (query != null)
          {
            query = intern(query);
          }
        }
        else if (validate != VALIDATE_NONE)
        {
          // Validate the segments that need validation.
          //
          for (int i = validate, length = segments.length; i < length; ++i)
          {
            String segment = segments[i];
            if (!validSegment(segment))
            {
              throw new IllegalArgumentException("invalid segment: " + segment);
            }
          }
        }

        // Create the appropriate type of URI.
        //
        if (hierarchical)
        {
          return new Hierarchical(hashCode, hierarchical, scheme, authority, device, absolutePath, segments, query);
        }
        else
        {
          return new Opaque(hashCode, scheme, authority);
        }
      }

      @Override
      public void reset(boolean isExclusive)
      {
        this.scheme = null;
        this.authority = null;
        this.device = null;
        this.segments = null;
        this.query = null;
        super.reset(isExclusive);
      }
    }

    /**
     * Intern a URI from its string representation, parsing if necessary.
     * The string must not contain the fragment separator.
     */
    protected URI intern(String string)
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
        for (Entry<URI> entry = getEntry(hashCode); entry != null; entry = entry.getNextEntry())
        {
          // Check that the referent isn't garbage collected and then compare it.
          //
          URI uri = entry.get();
          if (uri != null && uri.matches(string))
          {
            // Return that already present value.
            //
            return uri;
          }
        }

        writeLock.lock();
        try
        {
          StringAccessUnit accessUnit = stringAccessUnits.pop(true);
          accessUnit.setValue(string, hashCode);

          // The implementation returns an internalized value that's already pooled as a side effect.
          //
          URI result = accessUnit.getInternalizedValue();

          accessUnit.reset(true);
          return result;
        }
        finally
        {
          writeLock.unlock();
        }
      }
    }

    /**
     * Intern a platform URI from its path representation, parsing if necessary.
     */
    protected URI intern(String base, String pathName, boolean encode)
    {
      PlatformAccessUnit accessUnit = platformAccessUnits.pop(false);
      accessUnit.setValue(base, pathName, encode);
      return doIntern(false, accessUnit);
    }

    /**
     * Intern a file URI from its path representation, parsing if necessary.
     */
    protected URI internFile(String pathName)
    {
      FileAccessUnit accessUnit = fileAccessUnits.pop(false);
      accessUnit.setValue(pathName);
      return doIntern(false, accessUnit);
    }

    /**
     * Intern a URI from its component parts.
     * If <code>isExclusive</code> is true, acquire the {@link #writeLock} first.
     * Use {@link #intern(boolean, boolean, String, String, String, boolean, String[], String, int)} if the hash code is known and no validation is required.
     */
    protected URI intern(boolean isExclusive, int validate, boolean hierarchical, String scheme, String authority, String device, boolean absolutePath, String[] segments, String query)
    {
      if (isExclusive)
      {
        writeLock.lock();
      }
      URI uri;
      try
      {
        URIComponentsAccessUnit accessUnit = uriComponentsAccessUnits.pop(isExclusive);
        accessUnit.setValue(validate, hierarchical, scheme, authority, device, absolutePath, segments, query);
        uri = doIntern(isExclusive, accessUnit);
      }
      finally
      {
        if (isExclusive)
        {
          writeLock.unlock();
        }
      }
      return uri;
    }

    /**
     * Intern a URI from its component parts.
     * If <code>isExclusive</code> is true, acquire the {@link #writeLock} first.
     */
    protected URI intern(boolean isExclusive, boolean hierarchical, String scheme, String authority, String device, boolean absolutePath, String[] segments, String query, int hashCode)
    {
      if (isExclusive)
      {
        writeLock.lock();
      }
      URI uri;
      try
      {
        URIComponentsAccessUnit accessUnit = uriComponentsAccessUnits.pop(isExclusive);
        accessUnit.setValue(hierarchical, scheme, authority, device, absolutePath, segments, query, hashCode);
        uri = doIntern(isExclusive, accessUnit);
      }
      finally
      {
        if (isExclusive)
        {
          writeLock.unlock();
        }
      }
      return uri;
    }

    /**
     * Specialized to manage the {@link #cachedToStrings}.
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
          cachedToString.clear();
        }
      }
    }

    /**
     * A specialized weak reference used by {@link URI#toString} that removes the URI's reference when {@link #clear()} is called.
     *
     */
    protected static class CachedToString extends WeakReference<String>
    {
      protected final URI uri;

      public CachedToString(URI uri, String string, ReferenceQueue<? super String> queue)
      {
        super(string, queue);
        this.uri = uri;
      }

      @Override
      public void clear()
      {
        uri.flushCachedString();

        super.clear();
      }
    }

    protected WeakReference<String> newCachedToString(URI uri, String string)
    {
      return
        cachedToStrings == null ?
          new CachedToString(uri, string, externalQueue) :
          new CachedToString(uri, string, cachedToStrings);
    }
  }

  /**
   * A pool for managing {@link URI} instances.
   */
  protected static final URIPool POOL = new URIPool(CommonUtil.REFERENCE_CLEARING_QUEUE);

  // The lower-cased schemes that will be used to identify archive URIs.
  protected static final String[] ARCHIVE_SCHEMES;

  // Identifies a file-type absolute URI.
  protected static final String SCHEME_FILE = "file";
  protected static final String SCHEME_JAR = "jar";
  protected static final String SCHEME_ZIP = "zip";
  protected static final String SCHEME_ARCHIVE = "archive";
  protected static final String SCHEME_PLATFORM = "platform";
  protected static final String SCHEME_HTTP = "http";

  protected static final int SCHEME_FILE_HASH_CODE = SCHEME_FILE.hashCode();
  protected static final int SCHEME_JAR_HASH_CODE = SCHEME_JAR.hashCode();
  protected static final int SCHEME_ZIP_HASH_CODE = SCHEME_ZIP.hashCode();
  protected static final int SCHEME_ARCHIVE_HASH_CODE = SCHEME_ARCHIVE.hashCode();
  protected static final int SCHEME_PLATFORM_HASH_CODE = SCHEME_PLATFORM.hashCode();
  protected static final int SCHEME_HTTP_HASH_CODE = SCHEME_HTTP.hashCode();

  // Special segment values interpreted at resolve and resolve time.
  protected static final String SEGMENT_EMPTY = "";
  protected static final String SEGMENT_SELF = ".";
  protected static final String SEGMENT_PARENT = "..";

  // Special segments used for platform URIs.
  protected static final String SEGMENT_PLUGIN = "plugin";
  protected static final String SEGMENT_RESOURCE = "resource";

  // Ensure that all the string constants used as components in URIs are directly in the string pool.
  //
  static
  {
    // Ensure that all the string constants are themselves Java interned in the string pool.
    //
    CommonUtil.STRING_POOL.javaIntern(SCHEME_FILE);
    CommonUtil.STRING_POOL.javaIntern(SCHEME_JAR);
    CommonUtil.STRING_POOL.javaIntern(SCHEME_ZIP);
    CommonUtil.STRING_POOL.javaIntern(SCHEME_ARCHIVE);
    CommonUtil.STRING_POOL.javaIntern(SCHEME_PLATFORM);
    CommonUtil.STRING_POOL.javaIntern(SCHEME_HTTP);

    CommonUtil.STRING_POOL.javaIntern(SEGMENT_EMPTY);
    CommonUtil.STRING_POOL.javaIntern(SEGMENT_SELF);
    CommonUtil.STRING_POOL.javaIntern(SEGMENT_PARENT);

    CommonUtil.STRING_POOL.javaIntern(SEGMENT_PLUGIN);
    CommonUtil.STRING_POOL.javaIntern(SEGMENT_RESOURCE);
  }

  // Special arrays uses for segments
  protected static final String[] NO_SEGMENTS = SegmentSequence.EMPTY_ARRAY;
  protected static final String[] ONE_EMPTY_SEGMENT = SegmentSequence.EMPTY_STRING_ARRAY;
  protected static final String[] ONE_SELF_SEGMENT = SegmentSequence.STRING_ARRAY_POOL.intern(SEGMENT_SELF, false);

  // Separators for parsing a URI string.
  protected static final char SCHEME_SEPARATOR = ':';
  protected static final String AUTHORITY_SEPARATOR = "//";
  protected static final int AUTHORITY_SEPARATOR_HASH_CODE = AUTHORITY_SEPARATOR.hashCode();
  protected static final char DEVICE_IDENTIFIER = ':';
  protected static final char SEGMENT_SEPARATOR = '/';
  protected static final char QUERY_SEPARATOR = '?';
  protected static final char FRAGMENT_SEPARATOR = '#';
  protected static final char USER_INFO_SEPARATOR = '@';
  protected static final char PORT_SEPARATOR = ':';
  protected static final char FILE_EXTENSION_SEPARATOR = '.';
  protected static final char ARCHIVE_IDENTIFIER = '!';
  protected static final String ARCHIVE_SEPARATOR = "!/";

  // Characters to use in escaping.
  protected static final char ESCAPE = '%';
  protected static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

  // Some character classes, as defined in RFC 2396's BNF for URI.
  // These are 128-bit bitmasks, stored as two longs, where the Nth bit is set
  // iff the ASCII character with value N is included in the set.  These are
  // created with the highBitmask() and lowBitmask() methods defined below,
  // and a character is tested against them using matches().
  //
  protected static final long ALPHA_HI = highBitmask('a', 'z') | highBitmask('A', 'Z');
  protected static final long ALPHA_LO = lowBitmask('a', 'z')  | lowBitmask('A', 'Z');
  protected static final long DIGIT_HI = highBitmask('0', '9');
  protected static final long DIGIT_LO = lowBitmask('0', '9');
  protected static final long ALPHANUM_HI = ALPHA_HI | DIGIT_HI;
  protected static final long ALPHANUM_LO = ALPHA_LO | DIGIT_LO;
  protected static final long HEX_HI = DIGIT_HI | highBitmask('A', 'F') | highBitmask('a', 'f');
  protected static final long HEX_LO = DIGIT_LO | lowBitmask('A', 'F')  | lowBitmask('a', 'f');
  protected static final long UNRESERVED_HI = ALPHANUM_HI | highBitmask("-_.!~*'()");
  protected static final long UNRESERVED_LO = ALPHANUM_LO | lowBitmask("-_.!~*'()");
  protected static final long RESERVED_HI = highBitmask(";/?:@&=+$,");
  protected static final long RESERVED_LO = lowBitmask(";/?:@&=+$,");
  protected static final long URIC_HI = RESERVED_HI | UNRESERVED_HI;  // | ucschar | escaped
  protected static final long URIC_LO = RESERVED_LO | UNRESERVED_LO;

  // Additional useful character classes, including characters valid in certain
  // URI components and separators used in parsing them out of a string.
  //
  protected static final long SEGMENT_CHAR_HI = UNRESERVED_HI | highBitmask(";:@&=+$,");  // | ucschar | escaped
  protected static final long SEGMENT_CHAR_LO = UNRESERVED_LO | lowBitmask(";:@&=+$,");
  protected static final long PATH_CHAR_HI = SEGMENT_CHAR_HI | highBitmask('/');  // | ucschar | escaped
  protected static final long PATH_CHAR_LO = SEGMENT_CHAR_LO | lowBitmask('/');
  protected static final long MAJOR_SEPARATOR_HI = highBitmask(":/?#");
  protected static final long MAJOR_SEPARATOR_LO = lowBitmask(":/?#");
  protected static final long SEGMENT_END_HI = highBitmask("/?#");
  protected static final long SEGMENT_END_LO = lowBitmask("/?#");

  // The intent of this was to switch over to encoding platform resource URIs
  // by default, but allow people to use a system property to avoid this.
  // However, that caused problems for people and we had to go back to not
  // encoding and introduce yet another factory method that explicitly enables
  // encoding.
  //
  protected static final boolean ENCODE_PLATFORM_RESOURCE_URIS =
    System.getProperty("org.eclipse.emf.common.util.URI.encodePlatformResourceURIs") != null &&
    !"false".equalsIgnoreCase(System.getProperty("org.eclipse.emf.common.util.URI.encodePlatformResourceURIs"));

  // Static initializer for archiveSchemes.
  static
  {
    // Initialize the archive schemes.
    //
    List<String> list = new UniqueEList<String>();

    String propertyValue = System.getProperty("org.eclipse.emf.common.util.URI.archiveSchemes");

    list.add(SCHEME_JAR);
    list.add(SCHEME_ZIP);
    list.add(SCHEME_ARCHIVE);

    if (propertyValue != null)
    {
      for (StringTokenizer t = new StringTokenizer(propertyValue); t.hasMoreTokens(); )
      {
        String token = t.nextToken().toLowerCase();
        if (validScheme(token))
        {
          String scheme = CommonUtil.javaIntern(token);
          list.add(scheme);
        }
      }
    }

    ARCHIVE_SCHEMES = list.toArray(new String[list.size()]);
  }

  // Returns the lower half bitmask for the given ASCII character.
  protected static long lowBitmask(char c)
  {
    return c < 64 ? 1L << c : 0L;
  }

  // Returns the upper half bitmask for the given ACSII character.
  protected static long highBitmask(char c)
  {
    return c >= 64 && c < 128 ? 1L << (c - 64) : 0L;
  }

  // Returns the lower half bitmask for all ASCII characters between the two
  // given characters, inclusive.
  protected static long lowBitmask(char from, char to)
  {
    long result = 0L;
    if (from < 64 && from <= to)
    {
      to = to < 64 ? to : 63;
      for (char c = from; c <= to; c++)
      {
        result |= (1L << c);
      }
    }
    return result;
  }

  // Returns the upper half bitmask for all AsCII characters between the two
  // given characters, inclusive.
  protected static long highBitmask(char from, char to)
  {
    return to < 64 ? 0 : lowBitmask((char)(from < 64 ? 0 : from - 64), (char)(to - 64));
  }

  // Returns the lower half bitmask for all the ASCII characters in the given
  // string.
  protected static long lowBitmask(String chars)
  {
    long result = 0L;
    for (int i = 0, len = chars.length(); i < len; i++)
    {
      char c = chars.charAt(i);
      if (c < 64) result |= (1L << c);
    }
    return result;
  }

  // Returns the upper half bitmask for all the ASCII characters in the given
  // string.
  protected static long highBitmask(String chars)
  {
    long result = 0L;
    for (int i = 0, len = chars.length(); i < len; i++)
    {
      char c = chars.charAt(i);
      if (c >= 64 && c < 128) result |= (1L << (c - 64));
    }
    return result;
  }

  // Returns whether the given character is in the set specified by the given
  // bitmask.
  protected static boolean matches(char c, long highBitmask, long lowBitmask)
  {
    if (c >= 128) return false;
    return c < 64 ?
      ((1L << c) & lowBitmask) != 0 :
      ((1L << (c - 64)) & highBitmask) != 0;
  }

  // Debugging method: converts the given long to a string of binary digits.
/*
  protected static String toBits(long l)
  {
    StringBuffer result = new StringBuffer();
    for (int i = 0; i < 64; i++)
    {
      boolean b = (l & 1L) != 0;
      result.insert(0, b ? '1' : '0');
      l >>= 1;
    }
    return result.toString();
  }
*/

  /**
   * Static factory method for a generic, non-hierarchical URI.  There is no
   * concept of a relative non-hierarchical URI; such an object cannot be
   * created.
   *
   * @exception java.lang.IllegalArgumentException if <code>scheme</code> is
   * null, if <code>scheme</code> is an <a href="#archive_explanation">archive
   * URI</a> scheme, or if <code>scheme</code>, <code>opaquePart</code>, or
   * <code>fragment</code> is not valid according to {@link #validScheme
   * validScheme}, {@link #validOpaquePart validOpaquePart}, or {@link
   * #validFragment validFragment}, respectively.
   */
  public static URI createGenericURI(String scheme, String opaquePart, String fragment)
  {
    if (scheme == null)
    {
      throw new IllegalArgumentException("relative non-hierarchical URI");
    }

    if (isArchiveScheme(scheme))
    {
      throw new IllegalArgumentException("non-hierarchical archive URI");
    }

    return POOL.intern(false, URIPool.URIComponentsAccessUnit.VALIDATE_ALL, false, scheme, opaquePart, null, false, NO_SEGMENTS, null).appendFragment(fragment);
  }

  /**
   * Static factory method for a hierarchical URI with no path.  The
   * URI will be relative if <code>scheme</code> is non-null, and absolute
   * otherwise.  An absolute URI with no path requires a non-null
   * <code>authority</code> and/or <code>device</code>.
   *
   * @exception java.lang.IllegalArgumentException if <code>scheme</code> is
   * non-null while <code>authority</code> and <code>device</code> are null,
   * if <code>scheme</code> is an <a href="#archive_explanation">archive
   * URI</a> scheme, or if <code>scheme</code>, <code>authority</code>,
   * <code>device</code>, <code>query</code>, or <code>fragment</code> is not
   * valid according to {@link #validScheme validSheme}, {@link
   * #validAuthority validAuthority}, {@link #validDevice validDevice},
   * {@link #validQuery validQuery}, or {@link #validFragment validFragment},
   * respectively.
   */
  public static URI createHierarchicalURI(String scheme, String authority, String device, String query, String fragment)
  {
    if (scheme != null && authority == null && device == null)
    {
      throw new IllegalArgumentException("absolute hierarchical URI without authority, device, path");
    }

    if (isArchiveScheme(scheme))
    {
      throw new IllegalArgumentException("archive URI with no path");
    }

    return POOL.intern(false, URIPool.URIComponentsAccessUnit.VALIDATE_ALL, true, scheme, authority, device, false, NO_SEGMENTS, query).appendFragment(fragment);
  }

  /**
   * Static factory method for a hierarchical URI with absolute path.
   * The URI will be relative if <code>scheme</code> is non-null, and
   * absolute otherwise.
   *
   * @param segments an array of non-null strings, each representing one
   * segment of the path.  As an absolute path, it is automatically
   * preceded by a <code>/</code> separator.  If desired, a trailing
   * separator should be represented by an empty-string segment as the last
   * element of the array.
   *
   * @exception java.lang.IllegalArgumentException if <code>scheme</code> is
   * an <a href="#archive_explanation">archive URI</a> scheme and
   * <code>device</code> is non-null, or if <code>scheme</code>,
   * <code>authority</code>, <code>device</code>, <code>segments</code>,
   * <code>query</code>, or <code>fragment</code> is not valid according to
   * {@link #validScheme validScheme}, {@link #validAuthority validAuthority}
   * or {@link #validArchiveAuthority validArchiveAuthority}, {@link
   * #validDevice validDevice}, {@link #validSegments validSegments}, {@link
   * #validQuery validQuery}, or {@link #validFragment validFragment}, as
   * appropriate.
   */
  public static URI createHierarchicalURI(String scheme, String authority, String device, String[] segments, String query, String fragment)
  {
    if (device != null)
    {
      if (isArchiveScheme(scheme))
      {
        throw new IllegalArgumentException("archive URI with device");
      }
      if (SCHEME_PLATFORM.equals(scheme))
      {
        throw new IllegalArgumentException("platform URI with device");
      }
    }

    return POOL.intern(false, URIPool.URIComponentsAccessUnit.VALIDATE_ALL, true, scheme, authority, device, true, segments, query).appendFragment(fragment);
  }

  /**
   * Static factory method for a relative hierarchical URI with relative
   * path.
   *
   * @param segments an array of non-null strings, each representing one
   * segment of the path.  A trailing separator is represented by an
   * empty-string segment at the end of the array.
   *
   * @exception java.lang.IllegalArgumentException if <code>segments</code>,
   * <code>query</code>, or <code>fragment</code> is not valid according to
   * {@link #validSegments validSegments}, {@link #validQuery validQuery}, or
   * {@link #validFragment validFragment}, respectively.
   */
  public static URI createHierarchicalURI(String[] segments, String query, String fragment)
  {
    return POOL.intern(false, URIPool.URIComponentsAccessUnit.VALIDATE_ALL, true, null, null, null, false, segments, query).appendFragment(fragment);
  }

  /**
   * Static factory method based on parsing a URI string, with
   * <a href="#device_explanation">explicit device support</a> and handling
   * for <a href="#archive_explanation">archive URIs</a> enabled. The
   * specified string is parsed as described in <a
   * href="http://www.ietf.org/rfc/rfc2396.txt">RFC 2396</a>, and an
   * appropriate <code>URI</code> is created and returned.  Note that
   * validity testing is not as strict as in the RFC; essentially, only
   * separator characters are considered.  This method also does not perform
   * encoding of invalid characters, so it should only be used when the URI
   * string is known to have already been encoded, so as to avoid double
   * encoding.
   *
   * @exception java.lang.IllegalArgumentException if any component parsed
   * from <code>uri</code> is not valid according to {@link #validScheme
   * validScheme}, {@link #validOpaquePart validOpaquePart}, {@link
   * #validAuthority validAuthority}, {@link #validArchiveAuthority
   * validArchiveAuthority}, {@link #validDevice validDevice}, {@link
   * #validSegments validSegments}, {@link #validQuery validQuery}, or {@link
   * #validFragment validFragment}, as appropriate.
   */
  public static URI createURI(String uri)
  {
    return createURIWithCache(uri);
  }

  /**
   * Static factory method that encodes and parses the given URI string.
   * Appropriate encoding is performed for each component of the URI.
   * If more than one <code>#</code> is in the string, the last one is
   * assumed to be the fragment's separator, and any others are encoded.
   * This method is the simplest way to safely parse an arbitrary URI string.
   *
   * @param ignoreEscaped <code>true</code> to leave <code>%</code> characters
   * unescaped if they already begin a valid three-character escape sequence;
   * <code>false</code> to encode all <code>%</code> characters.  This
   * capability is provided to allow partially encoded URIs to be "fixed",
   * while avoiding adding double encoding; however, it is usual just to
   * specify <code>false</code> to perform ordinary encoding.
   *
   * @exception java.lang.IllegalArgumentException if any component parsed
   * from <code>uri</code> is not valid according to {@link #validScheme
   * validScheme}, {@link #validOpaquePart validOpaquePart}, {@link
   * #validAuthority validAuthority}, {@link #validArchiveAuthority
   * validArchiveAuthority}, {@link #validDevice validDevice}, {@link
   * #validSegments validSegments}, {@link #validQuery validQuery}, or {@link
   * #validFragment validFragment}, as appropriate.
   */
  public static URI createURI(String uri, boolean ignoreEscaped)
  {
    return createURIWithCache(encodeURI(uri, ignoreEscaped, FRAGMENT_LAST_SEPARATOR));
  }

  /**
   * When specified as the last argument to {@link #createURI(String, boolean, int)
   * createURI}, indicates that there is no fragment, so any <code>#</code> characters
   * should be encoded.
   * @see #createURI(String, boolean, int)
   */
  public static final int FRAGMENT_NONE = 0;

  /**
   * When specified as the last argument to {@link #createURI(String, boolean, int)
   * createURI}, indicates that the first <code>#</code> character should be taken as
   * the fragment separator, and any others should be encoded.
   * @see #createURI(String, boolean, int)
   */
  public static final int FRAGMENT_FIRST_SEPARATOR = 1;

  /**
   * When specified as the last argument to {@link #createURI(String, boolean, int)
   * createURI}, indicates that the last <code>#</code> character should be taken as
   * the fragment separator, and any others should be encoded.
   * @see #createURI(String, boolean, int)
   */
  public static final int FRAGMENT_LAST_SEPARATOR = 2;

  /**
   * Static factory method that encodes and parses the given URI string.
   * Appropriate encoding is performed for each component of the URI.
   * Control is provided over which, if any, <code>#</code> should be
   * taken as the fragment separator and which should be encoded.
   * This method is the preferred way to safely parse an arbitrary URI string
   * that is known to contain <code>#</code> characters in the fragment or to
   * have no fragment at all.
   *
   * @param ignoreEscaped <code>true</code> to leave <code>%</code> characters
   * unescaped if they already begin a valid three-character escape sequence;
   * <code>false</code> to encode all <code>%</code> characters.  This
   * capability is provided to allow partially encoded URIs to be "fixed",
   * while avoiding adding double encoding; however, it is usual just to
   * specify <code>false</code> to perform ordinary encoding.
   *
   * @param fragmentLocationStyle one of {@link #FRAGMENT_NONE},
   * {@link #FRAGMENT_FIRST_SEPARATOR}, or {@link #FRAGMENT_LAST_SEPARATOR},
   * indicating which, if any, of the <code>#</code> characters should be
   * considered the fragment separator. Any others will be encoded.
   *
   * @exception java.lang.IllegalArgumentException if any component parsed
   * from <code>uri</code> is not valid according to {@link #validScheme
   * validScheme}, {@link #validOpaquePart validOpaquePart}, {@link
   * #validAuthority validAuthority}, {@link #validArchiveAuthority
   * validArchiveAuthority}, {@link #validDevice validDevice}, {@link
   * #validSegments validSegments}, {@link #validQuery validQuery}, or {@link
   * #validFragment validFragment}, as appropriate.
   */
  public static URI createURI(String uri, boolean ignoreEscaped, int fragmentLocationStyle)
  {
    return createURIWithCache(encodeURI(uri, ignoreEscaped, fragmentLocationStyle));
  }

  /**
   * Static factory method based on parsing a URI string, with
   * <a href="#device_explanation">explicit device support</a> enabled.
   * Note that validity testing is not a strict as in the RFC; essentially,
   * only separator characters are considered.  So, for example, non-Latin
   * alphabet characters appearing in the scheme would not be considered an
   * error.
   *
   * @exception java.lang.IllegalArgumentException if any component parsed
   * from <code>uri</code> is not valid according to {@link #validScheme
   * validScheme}, {@link #validOpaquePart validOpaquePart}, {@link
   * #validAuthority validAuthority}, {@link #validArchiveAuthority
   * validArchiveAuthority}, {@link #validDevice validDevice}, {@link
   * #validSegments validSegments}, {@link #validQuery validQuery}, or {@link
   * #validFragment validFragment}, as appropriate.
   *
   * @deprecated Use {@link #createURI(String) createURI}, which now has explicit
   * device support enabled. The two methods now operate identically.
   */
  @Deprecated
  public static URI createDeviceURI(String uri)
  {
    return createURIWithCache(uri);
  }

  // Uses the URI pool to speed up creation of a URI from a string.
  /**
   * This method was included in the public API by mistake.
   *
   * @deprecated Please use {@link #createURI(String) createURI} instead.
   */
  @Deprecated
  public static URI createURIWithCache(String uri)
  {
    int index = uri.indexOf(FRAGMENT_SEPARATOR);
    return
      index == -1 ?
        POOL.intern(uri) :
        POOL.intern(uri.substring(0, index)).appendFragment(uri.substring(index + 1));
  }

  /**
   * Static factory method based on parsing a {@link java.io.File} path
   * string.  The <code>pathName</code> is converted into an appropriate
   * form, as follows: platform specific path separators are converted to
   * <code>/<code>; the path is encoded; and a "file" scheme and, if missing,
   * a leading <code>/</code>, are added to an absolute path.  The result
   * is then parsed using {@link #createURI(String) createURI}.
   *
   * <p>The encoding step escapes all spaces, <code>#</code> characters, and
   * other characters disallowed in URIs, as well as <code>?</code>, which
   * would delimit a path from a query.  Decoding is automatically performed
   * by {@link #toFileString toFileString}, and can be applied to the values
   * returned by other accessors by via the static {@link #decode(String)
   * decode} method.
   *
   * <p>A relative path with a specified device (something like
   * <code>C:myfile.txt</code>) cannot be expressed as a valid URI.
   *
   * @exception java.lang.IllegalArgumentException if <code>pathName</code>
   * specifies a device and a relative path, or if any component of the path
   * is not valid according to {@link #validAuthority validAuthority}, {@link
   * #validDevice validDevice}, or {@link #validSegments validSegments},
   * {@link #validQuery validQuery}, or {@link #validFragment validFragment}.
   */
  public static URI createFileURI(String pathName)
  {
    return POOL.internFile(pathName);
  }

  /**
   * Static factory method based on parsing a workspace-relative path string.
   *
   * <p>The <code>pathName</code> must be of the form:
   * <pre>
   *   /project-name/path</pre>
   *
   * <p>Platform-specific path separators will be converted to slashes.
   * If not included, the leading path separator will be added.  The
   * result will be of this form, which is parsed using {@link #createURI(String)
   * createURI}:
   * <pre>
   *   platform:/resource/project-name/path</pre>
   *
   * <p>This scheme supports relocatable projects in Eclipse and in
   * stand-alone EMF.
   *
   * <p>Path encoding is performed only if the
   * <code>org.eclipse.emf.common.util.URI.encodePlatformResourceURIs</code>
   * system property is set to "true". Decoding can be performed with the
   * static {@link #decode(String) decode} method.
   *
   * @exception java.lang.IllegalArgumentException if any component parsed
   * from the path is not valid according to {@link #validDevice validDevice},
   * {@link #validSegments validSegments}, {@link #validQuery validQuery}, or
   * {@link #validFragment validFragment}.
   *
   * @see org.eclipse.core.runtime.Platform#resolve
   * @see #createPlatformResourceURI(String, boolean)
   * @deprecated Use {@link #createPlatformResourceURI(String, boolean)} instead.
   */
  @Deprecated
  public static URI createPlatformResourceURI(String pathName)
  {
    return createPlatformResourceURI(pathName, ENCODE_PLATFORM_RESOURCE_URIS);
  }

  /**
   * Static factory method based on parsing a workspace-relative path string,
   * with an option to encode the created URI.
   *
   * <p>The <code>pathName</code> must be of the form:
   * <pre>
   *   /project-name/path</pre>
   *
   * <p>Platform-specific path separators will be converted to slashes.
   * If not included, the leading path separator will be added.  The
   * result will be of this form, which is parsed using {@link #createURI(String)
   * createURI}:
   * <pre>
   *   platform:/resource/project-name/path</pre>
   *
   * <p>This scheme supports relocatable projects in Eclipse and in
   * stand-alone EMF.
   *
   * <p>Depending on the <code>encode</code> argument, the path may be
   * automatically encoded to escape all spaces, <code>#</code> characters,
   * and other characters disallowed in URIs, as well as <code>?</code>,
   * which would delimit a path from a query.  Decoding can be performed with
   * the static {@link #decode(String) decode} method. It is strongly
   * recommended to specify <code>true</code> to enable encoding, unless the
   * path string has already been encoded.
   *
   * @exception java.lang.IllegalArgumentException if any component parsed
   * from the path is not valid according to {@link #validDevice validDevice},
   * {@link #validSegments validSegments}, {@link #validQuery validQuery}, or
   * {@link #validFragment validFragment}.
   *
   * @see org.eclipse.core.runtime.Platform#resolve
   */
  public static URI createPlatformResourceURI(String pathName, boolean encode)
  {
    return POOL.intern(SEGMENT_RESOURCE, pathName, encode);
  }

  /**
   * Static factory method based on parsing a plug-in-based path string,
   * with an option to encode the created URI.
   *
   * <p>The <code>pathName</code> must be of the form:
   * <pre>
   *   /plugin-id/path</pre>
   *
   * <p>Platform-specific path separators will be converted to slashes.
   * If not included, the leading path separator will be added.  The
   * result will be of this form, which is parsed using {@link #createURI(String)
   * createURI}:
   * <pre>
   *   platform:/plugin/plugin-id/path</pre>
   *
   * <p>This scheme supports relocatable plug-in content in Eclipse.
   *
   * <p>Depending on the <code>encode</code> argument, the path may be
   * automatically encoded to escape all spaces, <code>#</code> characters,
   * and other characters disallowed in URIs, as well as <code>?</code>,
   * which would delimit a path from a query.  Decoding can be performed with
   * the static {@link #decode(String) decode} method. It is strongly
   * recommended to specify <code>true</code> to enable encoding, unless the
   * path string has already been encoded.
   *
   * @exception java.lang.IllegalArgumentException if any component parsed
   * from the path is not valid according to {@link #validDevice validDevice},
   * {@link #validSegments validSegments}, {@link #validQuery validQuery}, or
   * {@link #validFragment validFragment}.
   *
   * @see org.eclipse.core.runtime.Platform#resolve
   * @since org.eclipse.emf.common 2.3
   */
  public static URI createPlatformPluginURI(String pathName, boolean encode)
  {
    return POOL.intern(SEGMENT_PLUGIN, pathName, encode);
  }

  // Splits the fragment into a segment sequence if it starts with a /, i.e., if it's used as a fragment path by EMF's resource implementation.
  //
  protected static CharSequence splitInternFragment(String fragment)
  {
    if (fragment.length() > 0 && fragment.charAt(0) == SEGMENT_SEPARATOR)
    {
      return SegmentSequence.create("/", fragment);
    }
    else
    {
      return CommonUtil.intern(fragment);
    }
  }

  // Protected constructor for use of pool.
  //
  protected URI(int hashCode)
  {
    this.hashCode = hashCode;
  }

  // Validates all of the URI components.  Factory methods should call this
  // before using the constructor, though they must ensure that the
  // inter-component requirements described in their own Javadocs are all
  // satisfied, themselves.  If a new URI is being constructed out of
  // an existing URI, this need not be called.  Instead, just the new
  // components may be validated individually.
  protected static boolean validateURI(boolean hierarchical, String scheme, String authority, String device, boolean absolutePath, String[] segments, String query, String fragment)
  {
    if (!validScheme(scheme))
    {
      throw new IllegalArgumentException("invalid scheme: " + scheme);
    }

    if (!hierarchical)
    {
      if (!validOpaquePart(authority))
      {
        throw new IllegalArgumentException("invalid opaquePart: " + authority);
      }
    }
    else if (isArchiveScheme(scheme) ? !validArchiveAuthority(authority) : !validAuthority(authority))
    {
      throw new IllegalArgumentException("invalid authority: " + authority);
    }

    if (!validDevice(device))
    {
      throw new IllegalArgumentException("invalid device: " + device);
    }

    if (!validSegments(segments))
    {
      String s = segments == null ? "invalid segments: null" :
        "invalid segment: " + firstInvalidSegment(segments);
      throw new IllegalArgumentException(s);
    }

    if (!validQuery(query))
    {
      throw new IllegalArgumentException("invalid query: " + query);
    }

    if (!validFragment(fragment))
    {
      throw new IllegalArgumentException("invalid fragment: " + fragment);
    }

    return true;
  }

  // Alternate, stricter implementations of the following validation methods
  // are provided, commented out, for possible future use...

  /**
   * Returns <code>true</code> if the specified <code>value</code> would be
   * valid as the scheme component of a URI; <code>false</code> otherwise.
   *
   * <p>A valid scheme may be null or contain any characters except for the
   * following: <code>: / ? #</code>
   */
  public static boolean validScheme(String value)
  {
    return value == null || !contains(value, MAJOR_SEPARATOR_HI, MAJOR_SEPARATOR_LO);

  // <p>A valid scheme may be null, or consist of a single letter followed
  // by any number of letters, numbers, and the following characters:
  // <code>+ - .</code>

    //if (value == null) return true;
    //return value.length() != 0 &&
    //  matches(value.charAt(0), ALPHA_HI, ALPHA_LO) &&
    //  validate(value, SCHEME_CHAR_HI, SCHEME_CHAR_LO, false, false);
  }

  /**
   * Returns <code>true</code> if the specified <code>value</code> would be
   * valid as the opaque part component of a URI; <code>false</code>
   * otherwise.
   *
   * <p>A valid opaque part must be non-null, non-empty, and not contain the
   * <code>#</code> character.  In addition, its first character must not be
   * <code>/</code>
   */
  public static boolean validOpaquePart(String value)
  {
    return value != null && value.indexOf(FRAGMENT_SEPARATOR) == -1 && value.length() > 0 && value.charAt(0) != SEGMENT_SEPARATOR;

  // <p>A valid opaque part must be non-null and non-empty. It may contain
  // any allowed URI characters, but its first character may not be
  // <code>/</code>

    //return value != null && value.length() != 0 &&
    //  value.charAt(0) != SEGMENT_SEPARATOR &&
    //  validate(value, URIC_HI, URIC_LO, true, true);
  }

  /**
   * Returns <code>true</code> if the specified <code>value</code> would be
   * valid as the authority component of a URI; <code>false</code> otherwise.
   *
   * <p>A valid authority may be null or contain any characters except for
   * the following: <code>/ ? #</code>
   */
  public static boolean validAuthority(String value)
  {
    return value == null || !contains(value, SEGMENT_END_HI, SEGMENT_END_LO);

  // A valid authority may be null or contain any allowed URI characters except
  // for the following: <code>/ ?</code>

    //return value == null || validate(value, SEGMENT_CHAR_HI, SEGMENT_CHAR_LO, true, true);
  }

  /**
   * Returns <code>true</code> if the specified <code>value</code> would be
   * valid as the authority component of an <a
   * href="#archive_explanation">archive URI</a>; <code>false</code>
   * otherwise.
   *
   * <p>To be valid, the authority, itself, must be a URI with no fragment or query,
   * followed by the character <code>!</code>.
   */
  public static boolean validArchiveAuthority(String value)
  {
    if (value != null && value.length() > 0 && value.charAt(value.length() - 1) == ARCHIVE_IDENTIFIER)
    {
      try
      {
        URI archiveURI = createURI(value.substring(0, value.length() - 1));
        return !archiveURI.hasFragment();
      }
      catch (IllegalArgumentException e)
      {
        // Ignore the exception and return false.
      }
    }
    return false;
  }

  /**
   * Tests whether the specified <code>value</code> would be valid as the
   * authority component of an <a href="#archive_explanation">archive
   * URI</a>. This method has been replaced by {@link #validArchiveAuthority
   * validArchiveAuthority} since the same form of URI is now supported
   * for schemes other than "jar". This now simply calls that method.
   *
   * @deprecated As of EMF 2.0, replaced by {@link #validArchiveAuthority
   * validArchiveAuthority}.
   */
  @Deprecated
  public static boolean validJarAuthority(String value)
  {
    return validArchiveAuthority(value);
  }

  /**
   * Returns <code>true</code> if the specified <code>value</code> would be
   * valid as the device component of a URI; <code>false</code> otherwise.
   *
   * <p>A valid device may be null or non-empty, containing any characters
   * except for the following: <code>/ ? #</code>  In addition, its last
   * character must be <code>:</code>
   */
  public static boolean validDevice(String value)
  {
    if (value == null) return true;
    int len = value.length();
    return len > 0 && value.charAt(len - 1) == DEVICE_IDENTIFIER && !contains(value, SEGMENT_END_HI, SEGMENT_END_LO);
  }

  /**
   * Returns <code>true</code> if the specified <code>value</code> would be
   * a valid path segment of a URI; <code>false</code> otherwise.
   *
   * <p>A valid path segment must be non-null and not contain any of the
   * following characters: <code>/ ? #</code>
   */
  public static boolean validSegment(String value)
  {
    return value != null && !contains(value, SEGMENT_END_HI, SEGMENT_END_LO);

  // <p>A valid path segment must be non-null and may contain any allowed URI
  // characters except for the following: <code>/ ?</code>

    //return value != null && validate(value, SEGMENT_CHAR_HI, SEGMENT_CHAR_LO, true, true);
  }

  /**
   * Returns <code>true</code> if the specified <code>value</code> would be
   * a valid path segment array of a URI; <code>false</code> otherwise.
   *
   * <p>A valid path segment array must be non-null and contain only path
   * segments that are valid according to {@link #validSegment validSegment}.
   */
  public static boolean validSegments(String[] value)
  {
    if (value == null) return false;
    for (int i = 0, len = value.length; i < len; i++)
    {
      if (!validSegment(value[i])) return false;
    }
    return true;
  }

  // Returns null if the specified value is null or would be a valid path
  // segment array of a URI; otherwise, the value of the first invalid
  // segment.
  protected static String firstInvalidSegment(String[] value)
  {
    if (value == null) return null;
    for (int i = 0, len = value.length; i < len; i++)
    {
      if (!validSegment(value[i])) return value[i];
    }
    return null;
  }

  /**
   * Returns <code>true</code> if the specified <code>value</code> would be
   * valid as the query component of a URI; <code>false</code> otherwise.
   *
   * <p>A valid query may be null or contain any characters except for
   * <code>#</code>
   */
  public static boolean validQuery(String value)
  {
    return value == null || value.indexOf(FRAGMENT_SEPARATOR) == -1;

  // <p>A valid query may be null or contain any allowed URI characters.

    //return value == null || validate(value, URIC_HI, URIC_LO, true, true);
}

  /**
   * Returns <code>true</code> if the specified <code>value</code> would be
   * valid as the fragment component of a URI; <code>false</code> otherwise.
   *
   * <p>A fragment is taken to be unconditionally valid.
   */
  public static boolean validFragment(String value)
  {
    return true;

  // <p>A valid fragment may be null or contain any allowed URI characters.

    //return value == null || validate(value, URIC_HI, URIC_LO, true, true);
  }

  // Searches the specified string for any characters in the set represented
  // by the 128-bit bitmask.  Returns true if any occur, or false otherwise.
  //
  protected static boolean contains(String s, long highBitmask, long lowBitmask)
  {
    for (int i = 0, len = s.length(); i < len; i++)
    {
      if (matches(s.charAt(i), highBitmask, lowBitmask)) return true;
    }
    return false;
  }

  /**
   * A subclass for representing a hierarchical URI.
   */
  protected static final class Hierarchical extends URI
  {
    /**
     * The {@link #flags} bit for representing {@link URI#hasAbsolutePath()}.
     */
    protected static final int HAS_ABSOLUTE_PATH               = 1 << 0;

    /**
     * The {@link #flags} bit for representing {@link URI#hasRelativePath()}.
     */
    protected static final int HAS_RELATIVE_PATH               = 1 << 1;

    /**
     * The {@link #flags} bit for representing {@link URI#hasEmptyPath()}.
     */
    protected static final int HAS_EMPTY_PATH                  = 1 << 2;

    /**
     * The {@link #flags} bit for representing {@link URI#isCurrentDocumentReference()}.
     */
    protected static final int IS_CURRENT_DOCUMENT_REFERENCE   = 1 << 3;

    /**
     * The {@link #flags} bit for representing {@link URI#isFile()}.
     */
    protected static final int IS_FILE                         = 1 << 4;

    /**
     * The {@link #flags} bit for representing {@link URI#isPlatform()}.
     */
    protected static final int IS_PLATFORM                     = 1 << 5;

    /**
     * The {@link #flags} bit for representing {@link URI#isPlatformResource()}.
     */
    protected static final int IS_PLATFORM_RESOURCE            = 1 << 6;

    /**
     * The {@link #flags} bit for representing {@link URI#isPlatformPlugin()}.
     */
    protected static final int IS_PLATFORM_PLUGIN              = 1 << 7;

    /**
     * The {@link #flags} bit for representing {@link URI#isArchive()}.
     */
    protected static final int IS_ARCHIVE                      = 1 << 8;

    /**
     * The {@link #flags} bit for representing {@link URI#hasTrailingPathSeparator()}.
     */
    protected static final int HAS_TRAILING_PATH_SEPARATOR     = 1 << 9;

    /**
     * The {@link #flags} bit for representing {@link URI#isPrefix()}.
     */
    protected static final int IS_PREFIX                       = 1 << 10;

    /**
     * The {@link #flags} bits for representing {@link URI#hasPath()}.
     */
    protected static final int HAS_PATH = HAS_ABSOLUTE_PATH | HAS_RELATIVE_PATH;

    /**
     * Bit flags for the results of all the boolean no-argument methods.
     */
    protected final int flags;

    /**
     * The scheme of the hierarchical URIs.
     */
    protected final String scheme;  // null -> relative URI reference

    /**
     * The authority of the hierarchical URI.
     */
    protected final String authority;

    /**
     * The device of the hierarchical URI.
     */
    protected final String device;

    /**
     * The segments of the hierarchical URI.
     */
    protected final String[] segments; // empty last segment -> trailing separator

    /**
     * The query of the hierarchical URI.
     */
    protected final String query;

    /**
     * A weakly cached reference to the string representation.
     */
    protected WeakReference<String> toString;

    /**
     * Creates an instance from the components, computing the {@link #flags} bits.
     * Assertions are used to validate the integrity of the result.
     * I.e., all components must be interned and the hash code must be equal to the hash code of the {@link #toString()}.
     */
    protected Hierarchical(int hashCode, boolean hierarchical, String scheme, String authority, String device, boolean absolutePath, String[] segments, String query)
    {
      super(hashCode);

      int flags = 0;
      if (absolutePath)
      {
        flags |= HAS_ABSOLUTE_PATH;
      }
      else if (device == null && authority == null)
      {
        flags |= HAS_RELATIVE_PATH;
        if (segments == NO_SEGMENTS)
        {
          flags |= HAS_EMPTY_PATH;
          if (query == null)
          {
            flags |= IS_CURRENT_DOCUMENT_REFERENCE;
          }
        }

        if (query == null)
        {
          flags |= IS_FILE;
        }
      }

      if (scheme != null)
      {
        if (scheme == SCHEME_FILE)
        {
          flags |= IS_FILE;
        }
        else if (scheme == SCHEME_PLATFORM)
        {
          if (authority == null && device == null && segments.length >= 2)
          {
            flags |= IS_PLATFORM;
            String firstSegment = segments[0];
            if (firstSegment == SEGMENT_RESOURCE)
            {
              flags |= IS_PLATFORM_RESOURCE;
            }
            else if (firstSegment == SEGMENT_PLUGIN)
            {
              flags |= IS_PLATFORM_PLUGIN;
            }
          }
        }
        else
        {
          for (String archiveScheme : ARCHIVE_SCHEMES)
          {
            if (scheme == archiveScheme)
            {
              flags |= IS_ARCHIVE;
              break;
            }
          }
        }
      }

      if (segments == NO_SEGMENTS)
      {
        if (absolutePath && query == null)
        {
          flags |= IS_PREFIX;
        }
      }
      else if (segments[segments.length - 1] == SEGMENT_EMPTY)
      {
        flags |= HAS_TRAILING_PATH_SEPARATOR;
        if (query == null)
        {
          flags |= IS_PREFIX;
        }
      }

      this.flags = flags;
      this.scheme = scheme;
      this.authority = authority;
      this.device = device;
      this.segments = segments;
      this.query = query;

      // The segments array must be interned.
      //
      assert segments == SegmentSequence.STRING_ARRAY_POOL.intern(true, true, segments, segments.length);

      // The scheme must be interned and must be lower cased.
      //
      assert scheme == CommonUtil.internToLowerCase(scheme);

      // The authority must be interned.
      //
      assert authority == CommonUtil.intern(authority);

      // The query must be interned.
      //
      assert query == CommonUtil.intern(query);

      // The device must be interned.
      //
      assert device == CommonUtil.intern(device);

      // The components must be valid.
      //
      assert validateURI(true, scheme, authority, device, hasAbsolutePath(), segments, query, null);

      // The hash code must be the same as that of the string representation
      //
      assert hashCode == toString().hashCode();
    }

    @Override
    public boolean isRelative()
    {
      return scheme == null;
    }

    @Override
    protected boolean isBase()
    {
      return scheme != null;
    }

    @Override
    public boolean isHierarchical()
    {
      return true;
    }

    @Override
    public boolean hasAuthority()
    {
      return authority != null;
    }

    @Override
    public boolean hasDevice()
    {
      return device != null;
    }

    @Override
    public boolean hasPath()
    {
      // note: (absolutePath || authority == null) -> hierarchical
      // (authority == null && device == null && !absolutePath) -> scheme == null
      return (flags & HAS_PATH) != 0;
    }

    @Override
    protected boolean hasDeviceOrPath()
    {
      return (flags & HAS_PATH) != 0 || device != null;
    }

    @Override
    public boolean hasAbsolutePath()
    {
      // note: absolutePath -> hierarchical
      return (flags & HAS_ABSOLUTE_PATH) != 0;
    }

    @Override
    public boolean hasRelativePath()
    {
      // note: authority == null -> hierarchical
      // (authority == null && device == null && !absolutePath) -> scheme == null
      return (flags & HAS_RELATIVE_PATH) != 0;
    }

    @Override
    public boolean hasEmptyPath()
    {
      // note: authority == null -> hierarchical
      // (authority == null && device == null && !absolutePath) -> scheme == null
      return (flags & HAS_EMPTY_PATH) != 0;
    }

    @Override
    public boolean hasQuery()
    {
      // note: query != null -> hierarchical
      return query != null;
    }

    @Override
    public boolean isCurrentDocumentReference()
    {
      // note: authority == null -> hierarchical
      // (authority == null && device == null && !absolutePath) -> scheme == null
      return (flags & IS_CURRENT_DOCUMENT_REFERENCE) != 0;
    }

    @Override
    public boolean isEmpty()
    {
      // note: authority == null -> hierarchical
      // (authority == null && device == null && !absolutePath) -> scheme == null
      return (flags & IS_CURRENT_DOCUMENT_REFERENCE) != 0;
    }

    @Override
    public boolean isFile()
    {
      return (flags & IS_FILE) != 0;
    }

    @Override
    public boolean isPlatform()
    {
      return (flags & IS_PLATFORM) != 0;
    }

    @Override
    public boolean isPlatformResource()
    {
      return (flags & IS_PLATFORM_RESOURCE) != 0;
    }

    @Override
    public boolean isPlatformPlugin()
    {
      return (flags & IS_PLATFORM_PLUGIN) != 0;
    }

    @Override
    public boolean isArchive()
    {
      return (flags & IS_ARCHIVE) != 0;
    }

    @Override
    protected boolean segmentsEqual(URI uri)
    {
      String[] segments = this.segments;
      int length = segments.length;
      if (length != uri.segmentCount()) return false;
      for (int i = 0; i < length; i++)
      {
        if (segments[i] != uri.segment(i)) return false;
      }
      return true;
    }

    @Override
    public String scheme()
    {
      return scheme;
    }

    @Override
    public String authority()
    {
      return authority;
    }

    @Override
    public String userInfo()
    {
      if (!hasAuthority()) return null;

      int i = authority.indexOf(USER_INFO_SEPARATOR);
      return i < 0 ? null : authority.substring(0, i);
    }

    @Override
    public String host()
    {
      if (!hasAuthority()) return null;

      int i = authority.indexOf(USER_INFO_SEPARATOR);
      int j = authority.indexOf(PORT_SEPARATOR);
      return j < 0 ? authority.substring(i + 1) : authority.substring(i + 1, j);
    }

    @Override
    public String port()
    {
      if (!hasAuthority()) return null;

      int i = authority.indexOf(PORT_SEPARATOR);
      return i < 0 ? null : authority.substring(i + 1);
    }

    @Override
    public String device()
    {
      return device;
    }

    @Override
    public String[] segments()
    {
      return segments.clone();
    }

    @Override
    protected String[] rawSegments()
    {
      return segments;
    }

    @Override
    public List<String> segmentsList()
    {
      return Collections.unmodifiableList(Arrays.asList(segments));
    }

    @Override
    public int segmentCount()
    {
      return segments.length;
    }

    @Override
    public String segment(int i)
    {
      return segments[i];
    }

    @Override
    public String lastSegment()
    {
      int len = segments.length;
      if (len == 0) return null;
      return segments[len - 1];
    }

    @Override
    public String path()
    {
      if (!hasPath()) return null;

      CommonUtil.StringPool.StringsAccessUnit result = CommonUtil.STRING_POOL.getStringBuilder();
      if (hasAbsolutePath()) result.append(SEGMENT_SEPARATOR);

      String[] segments = this.segments;
      for (int i = 0, len = segments.length; i < len; i++)
      {
        if (i != 0) result.append(SEGMENT_SEPARATOR);
        result.append(segments[i]);
      }
      return CommonUtil.STRING_POOL.intern(result);
    }

    @Override
    public String devicePath()
    {
      if (!hasPath()) return null;

      CommonUtil.StringPool.StringsAccessUnit result = CommonUtil.STRING_POOL.getStringBuilder();

      if (hasAuthority())
      {
        if (!isArchive()) result.append(AUTHORITY_SEPARATOR);
        result.append(authority);

        if (hasDevice()) result.append(SEGMENT_SEPARATOR);
      }

      if (hasDevice()) result.append(device);
      if (hasAbsolutePath()) result.append(SEGMENT_SEPARATOR);

      String[] segments = this.segments;
      for (int i = 0, len = segments.length; i < len; i++)
      {
        if (i != 0) result.append(SEGMENT_SEPARATOR);
        result.append(segments[i]);
      }
      return CommonUtil.STRING_POOL.intern(result);
    }

    @Override
    public String query()
    {
      return query;
    }

    @Override
    public URI appendQuery(String query)
    {
      return POOL.intern(false, URIPool.URIComponentsAccessUnit.VALIDATE_QUERY, true, scheme, authority, device, hasAbsolutePath(), segments, query);
    }

    @Override
    public URI trimQuery()
    {
      if (query == null)
      {
        return this;
      }
      else
      {
        return POOL.intern(false, URIPool.URIComponentsAccessUnit.VALIDATE_NONE, true, scheme, authority, device, hasAbsolutePath(), segments, null);
      }
    }

    @Override
    public URI resolve(URI base, boolean preserveRootParents)
    {
      if (!base.isBase())
      {
        throw new IllegalArgumentException("resolve against non-hierarchical or relative base");
      }

      // an absolute URI needs no resolving
      if (!isRelative()) return this;

      String newAuthority = authority;
      String newDevice = device;
      boolean newAbsolutePath = hasAbsolutePath();
      String[] newSegments = segments;
      String newQuery = query;

      // note: it's okay for two URIs to share a segments array, since neither will ever modify it

      if (authority == null)
      {
        // no authority: use base's
        newAuthority = base.authority();

        if (device == null)
        {
          // no device: use base's
          newDevice = base.device();

          if (hasEmptyPath() && query == null)
          {
            // current document reference: use base path and query
            newAbsolutePath = base.hasAbsolutePath();
            newSegments = base.rawSegments();
            newQuery = base.query();
          }
          else if (hasRelativePath())
          {
            // relative path: merge with base and keep query.
            // note that if the base has no path and this a non-empty relative path, there is an implied root in the resulting path
            newAbsolutePath = base.hasAbsolutePath() || !hasEmptyPath();
            newSegments = newAbsolutePath ? mergePath(base, preserveRootParents) : NO_SEGMENTS;
          }
          // else absolute path: keep it and query
        }
        // else keep device, path, and query
      }
      // else keep authority, device, path, and query

      // Use scheme from base; no validation needed because all components are from existing URIs
      return POOL.intern(false, URIPool.URIComponentsAccessUnit.VALIDATE_NONE, true, base.scheme(), newAuthority, newDevice, newAbsolutePath, newSegments, newQuery);
    }

    // Merges this URI's relative path with the base non-relative path.
    // If base has no path, treat it as the root absolute path, unless this has  no path either.
    //
    protected String[] mergePath(URI base, boolean preserveRootParents)
    {
      if (base.hasRelativePath())
      {
        throw new IllegalArgumentException("merge against relative path");
      }
      if (!hasRelativePath())
      {
        throw new IllegalStateException("merge non-relative path");
      }

      int baseSegmentCount = base.segmentCount();
      int segmentCount = segments.length;
      String[] stack = new String[baseSegmentCount + segmentCount];
      int sp = 0;

      // use a stack to accumulate segments of base, except for the last
      // (i.e. skip trailing separator and anything following it), and of relative path
      //
      for (int i = 0; i < baseSegmentCount - 1; i++)
      {
        sp = accumulate(stack, sp, base.segment(i), preserveRootParents);
      }

      for (int i = 0; i < segmentCount; i++)
      {
        sp = accumulate(stack, sp, segments[i], preserveRootParents);
      }

      // if the relative path is empty or ends in an empty segment, a parent
      // reference, or a self reference, add a trailing separator to a
      // non-empty path
      if (sp > 0)
      {
        if (segmentCount == 0)
        {
          stack[sp++] = SEGMENT_EMPTY;
        }
        else
        {
          String segment = segments[segmentCount - 1];
          if (segment == SEGMENT_EMPTY || segment == SEGMENT_PARENT || segment == SEGMENT_SELF)
          {
            stack[sp++] = SEGMENT_EMPTY;
          }
        }
      }

      // return a correctly sized result
      return SegmentSequence.STRING_ARRAY_POOL.intern(stack, 0, sp);
    }

    // Adds a segment to a stack, skipping empty segments and self references,
    // and interpreting parent references.
    protected static int accumulate(String[] stack, int sp, String segment, boolean preserveRootParents)
    {
      if (SEGMENT_PARENT == segment)
      {
        if (sp == 0)
        {
          // special care must be taken for a root's parent reference: it is
          // either ignored or the symbolic reference itself is pushed
          if (preserveRootParents) stack[sp++] = segment;
        }
        else
        {
          // unless we're already accumulating root parent references,
          // parent references simply pop the last segment descended
          if (SEGMENT_PARENT == stack[sp - 1]) stack[sp++] = segment;
          else sp--;
        }
      }
      else if (SEGMENT_EMPTY != segment && SEGMENT_SELF != segment)
      {
        // skip empty segments and self references; push everything else
        stack[sp++] = segment;
      }
      return sp;
    }

    @Override
    public URI deresolve(URI base, boolean preserveRootParents, boolean anyRelPath, boolean shorterRelPath)
    {
      if (!base.isBase() || isRelative()) return this;

      // note: these assertions imply that neither this nor the base URI has a
      // relative path; thus, both have either an absolute path or no path

      // different scheme: need complete, absolute URI
      if (scheme != base.scheme()) return this;

      String newAuthority = authority;
      String newDevice = device;
      boolean newAbsolutePath = hasAbsolutePath();
      String[] newSegments = segments;
      String newQuery = query;

      if (authority == base.authority() && (hasDeviceOrPath() || !base.hasDeviceOrPath()))
      {
        // matching authorities and no device or path removal
        newAuthority = null;

        if (device == base.device())
        {
          boolean hasPath = hasPath();
          boolean baseHasPath = base.hasPath();
          if (hasPath || !baseHasPath)
          {
            // matching devices and no path removal
            newDevice = null;

            // exception if (!hasPath() && base.hasPath())

            if (!anyRelPath && !shorterRelPath)
            {
              // user rejects a relative path: keep absolute or no path
            }
            else if (hasPath == baseHasPath && segmentsEqual(base) && query == base.query())
            {
              // current document reference: keep no path or query
              newAbsolutePath = false;
              newSegments = NO_SEGMENTS;
              newQuery = null;
            }
            else if (hasPath && !baseHasPath)
            {
              // no paths: keep query only
              newAbsolutePath = false;
              newSegments = NO_SEGMENTS;
            }
            // exception if (!hasAbsolutePath())
            else if (hasCollapsableSegments(preserveRootParents))
            {
              // path form demands an absolute path: keep it and query
            }
            else
            {
              // keep query and select relative or absolute path based on length
              String[] rel = findRelativePath(base, preserveRootParents);
              if (anyRelPath || segments.length > rel.length)
              {
                // user demands a relative path or the absolute path is longer
                newAbsolutePath = false;
                newSegments = rel;
              }
              // else keep shorter absolute path
            }
          }
        }
        // else keep device, path, and query
      }
      // else keep authority, device, path, and query

      // always include fragment, even if null;
      // no validation needed since all components are from existing URIs
      return POOL.intern(false, URIPool.URIComponentsAccessUnit.VALIDATE_NONE, true, null, newAuthority, newDevice, newAbsolutePath, newSegments, newQuery);
    }

    // Returns true if the non-relative path includes segments that would be
    // collapsed when resolving; false otherwise.  If preserveRootParents is
    // true, collapsible segments include any empty segments, except for the
    // last segment, as well as and parent and self references.  If
    // preserveRootsParents is false, parent references are not collapsible if
    // they are the first segment or preceded only by other parent
    // references.
    protected boolean hasCollapsableSegments(boolean preserveRootParents)
    {
      if (hasRelativePath())
      {
        throw new IllegalStateException("test collapsability of relative path");
      }

      String[] segments = this.segments;
      for (int i = 0, len = segments.length; i < len; i++)
      {
        String segment = segments[i];
        if ((i < len - 1 && SEGMENT_EMPTY == segment) ||
              SEGMENT_SELF == segment ||
              SEGMENT_PARENT == segment && (!preserveRootParents || (i != 0 && SEGMENT_PARENT != segments[i - 1])))
        {
          return true;
        }
      }
      return false;
    }

    // Returns the shortest relative path between the the non-relative path of
    // the given base and this absolute path.  If the base has no path, it is
    // treated as the root absolute path.
    protected String[] findRelativePath(URI base, boolean preserveRootParents)
    {
      if (base.hasRelativePath())
      {
        throw new IllegalArgumentException(
          "find relative path against base with relative path");
      }
      if (!hasAbsolutePath())
      {
        throw new IllegalArgumentException(
          "find relative path of non-absolute path");
      }

      // treat an empty base path as the root absolute path
      String[] startPath = base.collapseSegments(preserveRootParents);
      String[] endPath = segments;

      // drop last segment from base, as in resolving
      int startCount = startPath.length > 0 ? startPath.length - 1 : 0;
      int endCount = endPath.length;

      // index of first segment that is different between endPath and startPath
      int diff = 0;

      // if endPath is shorter than startPath, the last segment of endPath may
      // not be compared: because startPath has been collapsed and had its
      // last segment removed, all preceding segments can be considered non-
      // empty and followed by a separator, while the last segment of endPath
      // will either be non-empty and not followed by a separator, or just empty
      for (int count = startCount < endCount ? startCount : endCount - 1; diff < count && startPath[diff] == endPath[diff]; diff++)
      {
        // Empty statement.
      }

      int upCount = startCount - diff;
      int downCount = endCount - diff;

      // a single separator, possibly preceded by some parent reference
      // segments, is redundant
      if (downCount == 1 && SEGMENT_EMPTY == endPath[endCount - 1])
      {
        downCount = 0;
      }

      // an empty path needs to be replaced by a single "." if there is no
      // query, to distinguish it from a current document reference
      int length = upCount + downCount;
      if (length == 0)
      {
        if (query == null) return ONE_SELF_SEGMENT;
        return NO_SEGMENTS;
      }

      // return a correctly sized result
      String[] result = new String[length];
      Arrays.fill(result, 0, upCount, SEGMENT_PARENT);
      System.arraycopy(endPath, diff, result, upCount, downCount);
      return SegmentSequence.STRING_ARRAY_POOL.intern(false, false, result, length);
    }

    @Override
    protected String[] collapseSegments(boolean preserveRootParents)
    {
      if (hasRelativePath())
      {
        throw new IllegalStateException("collapse relative path");
      }

      if (!hasCollapsableSegments(preserveRootParents)) return rawSegments();

      // use a stack to accumulate segments
      String[] segments = this.segments;
      int segmentCount = segments.length;
      String[] stack = new String[segmentCount];
      int sp = 0;

      for (int i = 0; i < segmentCount; i++)
      {
        sp = accumulate(stack, sp, segments[i], preserveRootParents);
      }

      // if the path is non-empty and originally ended in an empty segment, a
      // parent reference, or a self reference, add a trailing separator
      if (sp > 0)
      {
        String segment = segments[segmentCount - 1];
        if (segment == SEGMENT_EMPTY || segment == SEGMENT_PARENT || segment == SEGMENT_SELF)
        {
          stack[sp++] = SEGMENT_EMPTY;
        }
      }

      // return a correctly sized result
      return SegmentSequence.STRING_ARRAY_POOL.intern(stack, 0, sp);
    }

    @Override
    protected void cacheString(String string)
    {
      toString = POOL.newCachedToString(this, string);
    }

    @Override
    protected void flushCachedString()
    {
      toString = null;
    }

    @Override
    protected String getCachedString()
    {
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
      return null;
    }

    @Override
    public String toString()
    {
      String cachedToString = getCachedString();
      if (cachedToString != null)
      {
        return cachedToString;
      }

      CommonUtil.StringPool.StringsAccessUnit result = CommonUtil.STRING_POOL.getStringBuilder();

      if (!isRelative())
      {
        result.append(scheme);
        result.append(SCHEME_SEPARATOR);
      }

      if (hasAuthority())
      {
        if (!isArchive()) result.append(AUTHORITY_SEPARATOR);
        result.append(authority);
      }

      if (hasDevice())
      {
        result.append(SEGMENT_SEPARATOR);
        result.append(device);
      }

      if (hasAbsolutePath()) result.append(SEGMENT_SEPARATOR);

      for (int i = 0, len = segments.length; i < len; i++)
      {
        if (i != 0) result.append(SEGMENT_SEPARATOR);
        result.append(segments[i]);
      }

      if (hasQuery())
      {
        result.append(QUERY_SEPARATOR);
        result.append(query);
      }

      String string = CommonUtil.STRING_POOL.intern(result);
      this.toString = POOL.newCachedToString(this, string);
      return string;
    }

    @Override
    protected boolean matches(String string)
    {
      String cachedString = getCachedString();
      if (cachedString != null)
      {
        return cachedString.equals(string);
      }

      int length = string.length();

      int index = 0;
      if (!isRelative())
      {
        if (!string.startsWith(scheme))
        {
          return false;
        }
        index += scheme.length();
        if (index >= length || string.charAt(index) != SCHEME_SEPARATOR)
        {
          return false;
        }
        ++index;
      }

      if (hasAuthority())
      {
        if (!isArchive())
        {
          if (!string.startsWith(AUTHORITY_SEPARATOR, index))
          {
            return false;
          }
          index += 2;
        }
        if (!string.startsWith(authority, index))
        {
          return false;
        }
        index += authority.length();
      }

      if (hasDevice())
      {
        if (index >= length || string.charAt(index) != SEGMENT_SEPARATOR)
        {
          return false;
        }
        ++index;
        if (!string.startsWith(device, index))
        {
          return false;
        }
        index += device.length();
      }

      if (hasAbsolutePath())
      {
        if (index >= length || string.charAt(index) != SEGMENT_SEPARATOR)
        {
          return false;
        }
        ++index;
      }

      String[] segments = this.segments;
      for (int i = 0, len = segments.length; i < len; i++)
      {
        if (i != 0)
        {
          if (index >= length || string.charAt(index) != SEGMENT_SEPARATOR)
          {
            return false;
          }
          ++index;
        }
        String segment = segments[i];
        if (!string.startsWith(segment, index))
        {
          return false;
        }
        index += segment.length();
      }

      if (hasQuery())
      {
        if (index >= length || string.charAt(index) != QUERY_SEPARATOR)
        {
          return false;
        }
        ++index;
        if (!string.startsWith(query, index))
        {
          return false;
        }
        index += query.length();
      }

      return index == length;
    }

    @Override
    protected boolean matches(int validate, boolean hierarchical, String scheme, String authority, String device, boolean absolutePath, String[] segments, String query)
    {
      return
        hierarchical &&
          hasAbsolutePath() == absolutePath &&
          (validate >= URIPool.URIComponentsAccessUnit.VALIDATE_NONE ?
             this.segments == segments && this.scheme == scheme && this.authority == authority && this.device == device && this.query == query :
             Arrays.equals(this.segments, segments) && equals(this.scheme, scheme) && equals(this.authority, authority) && equals(this.device, device) && equals(this.query, query));
    }

    @Override
    protected boolean matches(String base, String path)
    {
      if (!isPlatform() || segments[0] != base)
      {
        return false;
      }

      String[] segments = this.segments;
      int length = path.length();
      for (int i = 1, len = segments.length, index = 1; i < len; i++)
      {
        if (i != 1)
        {
          if (index >= length || path.charAt(index) != SEGMENT_SEPARATOR)
          {
            return false;
          }
          ++index;
        }
        String segment = segments[i];
        if (!path.startsWith(segment, index))
        {
          return false;
        }
        index += segment.length();
      }
      return true;
    }

    @Override
    public String toFileString()
    {
      if (!isFile()) return null;

      CommonUtil.StringPool.StringsAccessUnit result = CommonUtil.STRING_POOL.getStringBuilder();
      char separator = File.separatorChar;
      boolean hasDevice = hasDevice();

      if (hasAuthority())
      {
        result.append("//");
        result.append(authority);

        if (hasDevice) result.append(separator);
      }

      if (hasDevice) result.append(device);
      if (hasAbsolutePath()) result.append(separator);

      String[] segments = this.segments;
      for (int i = 0, len = segments.length; i < len; i++)
      {
        if (i != 0) result.append(separator);
        result.append(segments[i]);
      }

      return decode(CommonUtil.STRING_POOL.intern(result));
    }

    @Override
    public String toPlatformString(boolean decode)
    {
      if (isPlatform())
      {
        CommonUtil.StringPool.StringsAccessUnit result = CommonUtil.STRING_POOL.getStringBuilder();
        String[] segments = this.segments;
        for (int i = 1, len = segments.length; i < len; i++)
        {
          result.append('/');
          result.append(decode ? URI.decode(segments[i]) : segments[i]);
        }
        return CommonUtil.STRING_POOL.intern(result);
      }
      return null;
    }

    @Override
    public URI appendSegment(String segment)
    {
      // Do preliminary checking now but full checking later.
      //
      if (segment == null)
      {
        throw new IllegalArgumentException("invalid segment: null");
      }

      // absolute path or no path -> absolute path
      boolean newAbsolutePath = !hasRelativePath();

      String[] newSegments = SegmentSequence.STRING_ARRAY_POOL.intern(segments, segments.length, segment, true);
      return POOL.intern(false, segments.length, true, scheme, authority, device, newAbsolutePath, newSegments, query);
    }

    @Override
    public URI appendSegments(String[] segments)
    {
      // Do preliminary checking now but full checking later.
      //
      if (segments == null)
      {
        throw new IllegalArgumentException("invalid segments: null");
      }
      else
      {
        for (String segment : segments)
        {
          if (segment == null)
          {
            throw new IllegalArgumentException("invalid segment: null");
          }
        }
      }

      // absolute path or no path -> absolute path
      boolean newAbsolutePath = !hasRelativePath();

      String[] newSegments = SegmentSequence.STRING_ARRAY_POOL.intern(this.segments, segments, true);
      return POOL.intern(false, this.segments.length, true, scheme, authority, device, newAbsolutePath, newSegments, query);
    }

    @Override
    public URI trimSegments(int i)
    {
      if (i < 1) return this;

      String[] newSegments;
      int len = segments.length - i;
      if (len > 0)
      {
        newSegments = SegmentSequence.STRING_ARRAY_POOL.intern(segments, 0, len);
      }
      else
      {
        newSegments = NO_SEGMENTS;
      }

      return POOL.intern(false, URIPool.URIComponentsAccessUnit.VALIDATE_NONE, true, scheme, authority, device, hasAbsolutePath(), newSegments, query);
    }

    @Override
    public boolean hasTrailingPathSeparator()
    {
      return (flags & HAS_TRAILING_PATH_SEPARATOR) != 0;
    }

    @Override
    public String fileExtension()
    {
      int len = segments.length;
      if (len == 0) return null;

      String lastSegment = segments[len - 1];
      int i = lastSegment.lastIndexOf(FILE_EXTENSION_SEPARATOR);
      return i < 0 ? null : lastSegment.substring(i + 1);
    }

    @Override
    public URI appendFileExtension(String fileExtension)
    {
      // Do preliminary checking now and full validation later.
      if (fileExtension == null)
      {
        throw new IllegalArgumentException("invalid segment portion: null");
      }

      int len = segments.length;
      if (len == 0)
      {
        if (!validSegment(fileExtension))
        {
          throw new IllegalArgumentException("invalid segment portion: " + fileExtension);
        }
        return this;
      }

      String lastSegment = segments[len - 1];
      if (SEGMENT_EMPTY == lastSegment)
      {
        if (!validSegment(fileExtension))
        {
          throw new IllegalArgumentException("invalid segment portion: " + fileExtension);
        }
        return this;
      }

      CommonUtil.StringPool.StringsAccessUnit newLastSegment = CommonUtil.STRING_POOL.getStringBuilder();
      newLastSegment.append(lastSegment);
      newLastSegment.append(FILE_EXTENSION_SEPARATOR);
      newLastSegment.append(fileExtension);

      String[] newSegments = SegmentSequence.STRING_ARRAY_POOL.intern(segments, segments.length - 1, CommonUtil.STRING_POOL.intern(newLastSegment), false);

      // note: segments.length > 0 -> hierarchical
      return POOL.intern(false, len, true, scheme, authority, device, hasAbsolutePath(), newSegments, query);
    }

    @Override
    public URI trimFileExtension()
    {
      int len = segments.length;
      if (len == 0) return this;

      String lastSegment = segments[len - 1];
      int i = lastSegment.lastIndexOf(FILE_EXTENSION_SEPARATOR);
      if (i < 0) return this;

      String newLastSegment = lastSegment.substring(0, i);
      String[] newSegments = SegmentSequence.STRING_ARRAY_POOL.intern(segments, len - 1, newLastSegment, true);

      // note: segments.length > 0 -> hierarchical
      return POOL.intern(false, URIPool.URIComponentsAccessUnit.VALIDATE_NONE, true, scheme, authority, device, hasAbsolutePath(), newSegments, query);
    }

    @Override
    public boolean isPrefix()
    {
      return (flags & IS_PREFIX) != 0;
    }

    @Override
    public URI replacePrefix(URI oldPrefix, URI newPrefix)
    {
      if (!oldPrefix.isPrefix() || !newPrefix.isPrefix())
      {
        String which = oldPrefix.isPrefix() ? "new" : "old";
        throw new IllegalArgumentException("non-prefix " + which + " value");
      }

      // Don't even consider it unless this is hierarchical and has scheme,
      // authority, device and path absoluteness equal to those of the prefix.
      if (scheme != oldPrefix.scheme() || authority != oldPrefix.authority() || device != oldPrefix.device() || hasAbsolutePath() != oldPrefix.hasAbsolutePath())
      {
        return null;
      }

      String[] segments = this.segments;
      int segmentsLength = segments.length;

      // If the prefix has no segments, then it is the root absolute path, and
      // we know this is an absolute path, too.
      // Get what's left of the segments after trimming the prefix.
      String [] oldPrefixSegments = oldPrefix.rawSegments();
      int oldPrefixSegmentCount = oldPrefixSegments.length;
      int tailSegmentCount;
      if (oldPrefixSegmentCount == 0)
      {
        tailSegmentCount = segmentsLength;
      }
      else
      {
        // This must have no fewer segments than the prefix.  Since the prefix
        // is not the root absolute path, its last segment is empty; all others
        // must match.
        int i = 0;
        int segmentsToCompare = oldPrefixSegmentCount - 1;
        if (segmentsLength <= segmentsToCompare) return null;

        for (; i < segmentsToCompare; i++)
        {
          if (segments[i] != oldPrefixSegments[i]) return null;
        }

        // The prefix really is a prefix of this.  If this has just one more,
        // empty segment, the paths are the same.
        if (i == segmentsLength - 1 && segments[i] == SEGMENT_EMPTY)
        {
          return newPrefix;
        }
        else
        {
          tailSegmentCount = segmentsLength - i;
        }
      }

      // If the new prefix has segments, it is not the root absolute path,
      // and we need to drop the trailing empty segment and append the tail
      // segments.
      String [] newPrefixSegments = newPrefix.rawSegments();
      int newPrefixSegmentCount = newPrefixSegments.length;
      String[] mergedSegments;
      if (newPrefixSegmentCount == 0)
      {
        mergedSegments = tailSegmentCount == segmentsLength ? segments : SegmentSequence.STRING_ARRAY_POOL.intern(segments, segmentsLength - tailSegmentCount, tailSegmentCount);
      }
      else
      {
        mergedSegments = SegmentSequence.STRING_ARRAY_POOL.intern(newPrefixSegments, 0, newPrefixSegmentCount - 1, segments, segmentsLength - tailSegmentCount, tailSegmentCount);
      }

      // no validation needed since all components are from existing URIs
      return POOL.intern(false, URIPool.URIComponentsAccessUnit.VALIDATE_NONE, true, newPrefix.scheme(), newPrefix.authority(), newPrefix.device(), newPrefix.hasAbsolutePath(), mergedSegments, query);
    }
  }

  /**
   * A subclass for representing an opaque URI.
   */
  protected final static class Opaque extends URI
  {
    /**
     * The scheme of the opaque URI.
     */
    protected final String scheme;

    /**
     * The opaque part of the opaque URI.
     */
    protected final String opaquePart;

    /**
     * A weakly cached reference to the string representation.
     */
    protected WeakReference<String> toString;

    /**
     * Creates an instance from the components.
     * Assertions are used to validate the integrity of the result.
     * I.e., both components must be interned and the hash code must be equal to the hash code of the {@link #toString()}.
     */
    protected Opaque(int hashCode, String scheme, String opaquePart)
    {
      super(hashCode);

      this.scheme = scheme;
      this.opaquePart = opaquePart;

      // The scheme must be interned and must be lower cased.
      //
      assert scheme == CommonUtil.internToLowerCase(scheme);

      // The authority must be interned.
      //
      assert opaquePart == CommonUtil.intern(opaquePart);

      // The components must be valid.
      //
      assert validateURI(false, scheme, opaquePart, null, false, NO_SEGMENTS, null, null);

      // The hash code must be the same as that of the string representation
      //
      assert hashCode == toString().hashCode();
    }

    @Override
    public boolean hasOpaquePart()
    {
      return true;
    }

    @Override
    public String scheme()
    {
      return scheme;
    }

    @Override
    public String opaquePart()
    {
      return opaquePart;
    }

    @Override
    protected void cacheString(String string)
    {
      toString = POOL.newCachedToString(this, string);
    }

    @Override
    protected void flushCachedString()
    {
      toString = null;
    }

    @Override
    protected String getCachedString()
    {
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
      return null;
    }

    @Override
    public String toString()
    {
      String cachedString = getCachedString();
      if (cachedString != null)
      {
        return cachedString;
      }

      CommonUtil.StringPool.StringsAccessUnit result = CommonUtil.STRING_POOL.getStringBuilder();
      result.append(scheme);
      result.append(SCHEME_SEPARATOR);
      result.append(opaquePart);

      String string = CommonUtil.STRING_POOL.intern(result);
      this.toString = POOL.newCachedToString(this, string);
      return string;
    }

    @Override
    protected boolean matches(String string)
    {
      String cachedString = getCachedString();
      if (cachedString != null)
      {
        return cachedString.equals(string);
      }

      int index = 0;
      if (!string.startsWith(scheme))
      {
        return false;
      }

      int length = string.length();
      index += scheme.length();
      if (index >= length || string.charAt(index) != SCHEME_SEPARATOR)
      {
        return false;
      }
      ++index;

      if (!string.startsWith(opaquePart, index))
      {
        return false;
      }
      index += opaquePart.length();

      return index == length;
    }

    @Override
    protected boolean matches(int validate, boolean hierarchical, String scheme, String authority, String device, boolean absolutePath, String[] segments, String query)
    {
      return
        !hierarchical &&
          !absolutePath &&
          segments == null &&
          query == null &&
          (validate >= URIPool.URIComponentsAccessUnit.VALIDATE_NONE ?
             this.scheme == scheme && this.opaquePart == authority :
             equals(this.scheme, scheme) && equals(this.opaquePart, authority));
    }
  }

  /**
   * A subclass for representing a URI with a fragment.
   * Most methods simply delegate to the {@link #trimFragment() base} URI.
   */
  protected static final class Fragment extends URI
  {
    /**
     * The {@link #trimFragment() base} URI.
     */
    protected final URI uri;

    /**
     * The representation of the fragment.
     * The fragment is {@link #splitInternFragment(String) split interned}.
     */
    protected final CharSequence fragment;

    /**
     * Creates an instance from the components.
     * Assertions are used to validate the integrity of the result.
     * I.e., the fragment must be non-null and {@link #splitInternFragment(String) split interned} and the hash code must be equal to the hash code of the {@link #toString()}.
     */
    protected Fragment(int hashCode, URI uri, CharSequence fragment)
    {
      super(hashCode);

      this.uri = uri;
      this.fragment = fragment;

      // There must be a fragment.
      //
      assert fragment != null;

      // The fragment must be split and interned.
      //
      assert fragment == splitInternFragment(fragment.toString());

      // The hash code must be the same as that of the string representation
      //
      assert hashCode == toString().hashCode();
    }

    @Override
    public boolean isRelative()
    {
      return uri.isRelative();
    }

    @Override
    protected boolean isBase()
    {
      return uri.isBase();
    }

    @Override
    public boolean isHierarchical()
    {
      return uri.isHierarchical();
    }

    @Override
    public boolean hasAuthority()
    {
      return uri.hasAuthority();
    }

    @Override
    public boolean hasOpaquePart()
    {
      return uri.hasOpaquePart();
    }

    @Override
    public boolean hasDevice()
    {
      return uri.hasDevice();
    }

    @Override
    public boolean hasPath()
    {
      return uri.hasPath();
    }

    @Override
    protected boolean hasDeviceOrPath()
    {
      return uri.hasDeviceOrPath();
    }

    @Override
    public boolean hasAbsolutePath()
    {
      return uri.hasAbsolutePath();
    }

    @Override
    public boolean hasRelativePath()
    {
      return uri.hasRelativePath();
    }

    @Override
    public boolean hasEmptyPath()
    {
      return uri.hasEmptyPath();
    }

    @Override
    public boolean hasQuery()
    {
      return uri.hasQuery();
    }

    @Override
    public boolean hasFragment()
    {
      return true;
    }

    @Override
    public boolean isCurrentDocumentReference()
    {
      return uri.isCurrentDocumentReference();
    }

    @Override
    public boolean isEmpty()
    {
      return false;
    }

    @Override
    public boolean isFile()
    {
      return uri.isFile();
    }

    @Override
    public boolean isPlatform()
    {
      return uri.isPlatform();
    }

    @Override
    public boolean isPlatformResource()
    {
      return uri.isPlatformResource();
    }

    @Override
    public boolean isPlatformPlugin()
    {
      return uri.isPlatformPlugin();
    }

    @Override
    public boolean isArchive()
    {
      return uri.isArchive();
    }

    @Override
    protected boolean segmentsEqual(URI uri)
    {
      return uri.segmentsEqual(uri);
    }

    @Override
    public String scheme()
    {
      return uri.scheme();
    }

    @Override
    public String opaquePart()
    {
      return uri.opaquePart();
    }

    @Override
    public String authority()
    {
      return uri.authority();
    }

    @Override
    public String userInfo()
    {
      return uri.userInfo();
    }

    @Override
    public String host()
    {
      return uri.host();
    }

    @Override
    public String port()
    {
      return uri.port();
    }

    @Override
    public String device()
    {
      return uri.device();
    }

    @Override
    public String[] segments()
    {
      return uri.segments();
    }

    @Override
    protected String[] rawSegments()
    {
      return uri.rawSegments();
    }

    @Override
    public List<String> segmentsList()
    {
      return uri.segmentsList();
    }

    @Override
    public int segmentCount()
    {
      return uri.segmentCount();
    }

    @Override
    public String segment(int i)
    {
      return uri.segment(i);
    }

    @Override
    public String lastSegment()
    {
      return uri.lastSegment();
    }

    @Override
    public String path()
    {
      return uri.path();
    }

    @Override
    public String devicePath()
    {
      return uri.devicePath();
    }

    @Override
    public String query()
    {
      return uri.query();
    }

    @Override
    public URI appendQuery(String query)
    {
      return uri.appendQuery(query).rawAppendFragment(fragment);
    }

    @Override
    public URI trimQuery()
    {
      URI result = uri.trimQuery();
      return result == uri ? this : result.rawAppendFragment(fragment);
    }

    @Override
    public String fragment()
    {
      return fragment.toString();
    }

    @Override
    public URI appendFragment(String fragment)
    {
      return uri.appendFragment(fragment);
    }

    @Override
    public URI trimFragment()
    {
      return uri;
    }

    @Override
    public URI resolve(URI base, boolean preserveRootParents)
    {
      URI result = uri.resolve(base, preserveRootParents);
      return result == uri ? this : result.rawAppendFragment(fragment);
    }

    @Override
    public URI deresolve(URI base, boolean preserveRootParents, boolean anyRelPath, boolean shorterRelPath)
    {
      URI result = uri.deresolve(base, preserveRootParents, anyRelPath, shorterRelPath);
      return result == uri ? this : result.rawAppendFragment(fragment);
    }

    @Override
    protected String[] collapseSegments(boolean preserveRootParents)
    {
      return uri.collapseSegments(preserveRootParents);
    }

    @Override
    public String toString()
    {
      CommonUtil.StringPool.StringsAccessUnit result = CommonUtil.STRING_POOL.getStringBuilder();
      result.append(uri.toString());
      result.append(FRAGMENT_SEPARATOR);
      result.append(fragment);
      return CommonUtil.STRING_POOL.intern(result);
    }

    @Override
    public boolean equals(Object object)
    {
      if (object == this)
      {
        return true;
      }
      if (!(object instanceof Fragment))
      {
        return false;
      }
      Fragment that = (Fragment)object;
      return uri == that.uri && fragment == that.fragment;
    }

    @Override
    public String toFileString()
    {
      return uri.toFileString();
    }

    @Override
    public String toPlatformString(boolean decode)
    {
      return uri.toPlatformString(decode);
    }

    @Override
    public URI appendSegment(String segment)
    {
      URI result = uri.appendSegment(segment);
      return result == uri ? this : result.rawAppendFragment(fragment);
    }

    @Override
    public URI appendSegments(String[] segments)
    {
      URI result = uri.appendSegments(segments);
      return result == uri ? this : result.rawAppendFragment(fragment);
    }

    @Override
    public URI trimSegments(int i)
    {
      URI result = uri.trimSegments(i);
      return result == uri ? this : result.rawAppendFragment(fragment);
    }

    @Override
    public boolean hasTrailingPathSeparator()
    {
      return uri.hasTrailingPathSeparator();
    }

    @Override
    public String fileExtension()
    {
      return uri.fileExtension();
    }

    @Override
    public URI appendFileExtension(String fileExtension)
    {
      URI result = uri.appendFileExtension(fileExtension);
      return result == uri ? this : result.rawAppendFragment(fragment);
    }

    @Override
    public URI trimFileExtension()
    {
      URI result = uri.trimFileExtension();
      return result == uri ? this : result.rawAppendFragment(fragment);
    }

    @Override
    public URI replacePrefix(URI oldPrefix, URI newPrefix)
    {
      URI result = uri.replacePrefix(oldPrefix, newPrefix);
      return result == uri ? this : result == null ? null : result.rawAppendFragment(fragment);
    }
  }

  protected void flushCachedString()
  {
    // Do nothing.
  }

  protected void cacheString(String string)
  {
    // Do nothing.
  }

  protected String getCachedString()
  {
    return null;
  }

  /**
   * Returns <code>true</code> if this is a relative URI, or
   * <code>false</code> if it is an absolute URI.
   */
  public boolean isRelative()
  {
    return false;
  }

  // Whether this this URI valid has a base URI against which to resolve.
  //
  protected boolean isBase()
  {
    return false;
  }

  /**
   * Returns <code>true</code> if this a a hierarchical URI, or
   * <code>false</code> if it is of the generic form.
   */
  public boolean isHierarchical()
  {
    return false;
  }

  /**
   * Returns <code>true</code> if this is a hierarchical URI with an authority
   * component; <code>false</code> otherwise.
   */
  public boolean hasAuthority()
  {
    return false;
  }

  /**
   * Returns <code>true</code> if this is a non-hierarchical URI with an
   * opaque part component; <code>false</code> otherwise.
   */
  public boolean hasOpaquePart()
  {
    // note: hierarchical -> authority != null
    return false;
  }

  /**
   * Returns <code>true</code> if this is a hierarchical URI with a device
   * component; <code>false</code> otherwise.
   */
  public boolean hasDevice()
  {
    // note: device != null -> hierarchical
    return false;
  }

  protected boolean hasDeviceOrPath()
  {
    return false;
  }

  /**
   * Returns <code>true</code> if this is a hierarchical URI with an
   * absolute or relative path; <code>false</code> otherwise.
   */
  public boolean hasPath()
  {
    // note: (absolutePath || authority == null) -> hierarchical
    // (authority == null && device == null && !absolutePath) -> scheme == null
    return false;
  }

  /**
   * Returns <code>true</code> if this is a hierarchical URI with an
   * absolute path, or <code>false</code> if it is non-hierarchical, has no
   * path, or has a relative path.
   */
  public boolean hasAbsolutePath()
  {
    // note: absolutePath -> hierarchical
    return false;
  }

  /**
   * Returns <code>true</code> if this is a hierarchical URI with a relative
   * path, or <code>false</code> if it is non-hierarchical, has no path, or
   * has an absolute path.
   */
  public boolean hasRelativePath()
  {
    // note: authority == null -> hierarchical
    // (authority == null && device == null && !absolutePath) -> scheme == null
    return false;
  }

  /**
   * Returns <code>true</code> if this is a hierarchical URI with an empty
   * relative path; <code>false</code> otherwise.
   *
   * <p>Note that <code>!hasEmpty()</code> does <em>not</em> imply that this
   * URI has any path segments; however, <code>hasRelativePath &&
   * !hasEmptyPath()</code> does.
   */
  public boolean hasEmptyPath()
  {
    // note: authority == null -> hierarchical
    // (authority == null && device == null && !absolutePath) -> scheme == null
    return false;
  }

  /**
   * Returns <code>true</code> if this is a hierarchical URI with a query
   * component; <code>false</code> otherwise.
   */
  public boolean hasQuery()
  {
    // note: query != null -> hierarchical
    return false;
  }

  /**
   * Returns <code>true</code> if this URI has a fragment component;
   * <code>false</code> otherwise.
   */
  public boolean hasFragment()
  {
    return false;
  }

  /**
   * Returns <code>true</code> if this is a current document reference; that
   * is, if it is a relative hierarchical URI with no authority, device or
   * query components, and no path segments; <code>false</code> is returned
   * otherwise.
   */
  public boolean isCurrentDocumentReference()
  {
    // note: authority == null -> hierarchical
    // (authority == null && device == null && !absolutePath) -> scheme == null
    return false;
  }

  /**
   * Returns <code>true</code> if this is a {@link
   * #isCurrentDocumentReference() current document reference} with no
   * fragment component; <code>false</code> otherwise.
   *
   * @see #isCurrentDocumentReference()
   */
  public boolean isEmpty()
  {
    // note: authority == null -> hierarchical
    // (authority == null && device == null && !absolutePath) -> scheme == null
    return false;
  }

  /**
   * Returns <code>true</code> if this is a hierarchical URI that may refer
   * directly to a locally accessible file.  This is considered to be the
   * case for a file-scheme absolute URI, or for a relative URI with no query;
   * <code>false</code> is returned otherwise.
   */
  public boolean isFile()
  {
    return false;
  }

  /**
   * Returns <code>true</code> if this is a platform URI, that is, an absolute,
   * hierarchical URI, with "platform" scheme, no authority, and at least two
   * segments; <code>false</code> is returned otherwise.
   * @since org.eclipse.emf.common 2.3
   */
  public boolean isPlatform()
  {
    return false;
  }

  /**
   * Returns <code>true</code> if this is a platform resource URI, that is,
   * a {@link #isPlatform platform URI} whose first segment is "resource";
   * <code>false</code> is returned otherwise.
   * @see #isPlatform
   * @since org.eclipse.emf.common 2.3
   */
  public boolean isPlatformResource()
  {
    return false;
  }

  /**
   * Returns <code>true</code> if this is a platform plug-in URI, that is,
   * a {@link #isPlatform platform URI} whose first segment is "plugin";
   * <code>false</code> is returned otherwise.
   * @see #isPlatform
   * @since org.eclipse.emf.common 2.3
   */
  public boolean isPlatformPlugin()
  {
    return false;
  }

  /**
   * Returns <code>true</code> if this is an archive URI.  If so, it is also
   * hierarchical, with an authority (consisting of an absolute URI followed
   * by "!"), no device, and an absolute path.
   */
  public boolean isArchive()
  {
    return false;
  }

  /**
   * Returns <code>true</code> if the specified <code>value</code> would be
   * valid as the scheme of an <a
   * href="#archive_explanation">archive URI</a>; <code>false</code>
   * otherwise.
   */
  public static boolean isArchiveScheme(String value)
  {
    // Returns true if the given value is an archive scheme, as defined by
    // the org.eclipse.emf.common.util.URI.archiveSchemes system property.
    // By default, "jar", "zip", and "archive" are considered archives.
    for (String scheme : ARCHIVE_SCHEMES)
    {
      if (scheme.equals(value))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns the hash code.
   */
  @Override
  public final int hashCode()
  {
    return hashCode;
  }

  // Tests whether this URI's path segment array is equal to that of the given uri.
  protected boolean segmentsEqual(URI uri)
  {
    return false;
  }

  // Tests two objects for equality, tolerating nulls; null is considered
  // to be a valid value that is only equal to itself.
  protected static boolean equals(Object o1, Object o2)
  {
    return o1 == o2 || o1 != null && o1.equals(o2);
  }

  /**
   * If this is an absolute URI, returns the scheme component;
   * <code>null</code> otherwise.
   */
  public String scheme()
  {
    return null;
  }

  /**
   * If this is a non-hierarchical URI, returns the opaque part component;
   * <code>null</code> otherwise.
   */
  public String opaquePart()
  {
    return null;
  }

  /**
   * If this is a hierarchical URI with an authority component, returns it;
   * <code>null</code> otherwise.
   */
  public String authority()
  {
    return null;
  }

  /**
   * If this is a hierarchical URI with an authority component that has a
   * user info portion, returns it; <code>null</code> otherwise.
   */
  public String userInfo()
  {
    return null;
  }

  /**
   * If this is a hierarchical URI with an authority component that has a
   * host portion, returns it; <code>null</code> otherwise.
   */
  public String host()
  {
    return null;
  }

  /**
   * If this is a hierarchical URI with an authority component that has a
   * port portion, returns it; <code>null</code> otherwise.
   */
  public String port()
  {
    return null;
  }

  /**
   * If this is a hierarchical URI with a device component, returns it;
   * <code>null</code> otherwise.
   */
  public String device()
  {
    return null;
  }

  /**
   * If this is a hierarchical URI with a path, returns an array containing
   * the segments of the path; an empty array otherwise.  The leading
   * separator in an absolute path is not represented in this array, but a
   * trailing separator is represented by an empty-string segment as the
   * final element.
   */
  public String[] segments()
  {
    return NO_SEGMENTS;
  }

  // Directly returns the underlying segments without cloning them.
  //
  protected String[] rawSegments()
  {
    return NO_SEGMENTS;
  }

  /**
   * Returns an unmodifiable list containing the same segments as the array
   * returned by {@link #segments segments}.
   */
  public List<String> segmentsList()
  {
    return Collections.emptyList();
  }

  /**
   * Returns the number of elements in the segment array that would be
   * returned by {@link #segments segments}.
   */
  public int segmentCount()
  {
    return 0;
  }

  /**
   * Provides fast, indexed access to individual segments in the path
   * segment array.
   *
   * @exception java.lang.IndexOutOfBoundsException if <code>i < 0</code> or
   * <code>i >= segmentCount()</code>.
   */
  public String segment(int i)
  {
    throw new IndexOutOfBoundsException("No such segment: " + i);
  }

  /**
   * Returns the last segment in the segment array, or <code>null</code>.
   */
  public String lastSegment()
  {
    return null;
  }

  /**
   * If this is a hierarchical URI with a path, returns a string
   * representation of the path; <code>null</code> otherwise.  The path
   * consists of a leading segment separator character (a slash), if the
   * path is absolute, followed by the slash-separated path segments.  If
   * this URI has a separate <a href="#device_explanation">device
   * component</a>, it is <em>not</em> included in the path.
   */
  public String path()
  {
    return null;
  }

  /**
   * If this is a hierarchical URI with a path, returns a string
   * representation of the path, including the authority and the
   * <a href="#device_explanation">device component</a>;
   * <code>null</code> otherwise.
   *
   * <p>If there is no authority, the format of this string is:
   * <pre>
   *   device/pathSegment1/pathSegment2...</pre>
   *
   * <p>If there is an authority, it is:
   * <pre>
   *   //authority/device/pathSegment1/pathSegment2...</pre>
   *
   * <p>For an <a href="#archive_explanation">archive URI</a>, it's just:
   * <pre>
   *   authority/pathSegment1/pathSegment2...</pre>
   */
  public String devicePath()
  {
    return null;
  }

  /**
   * If this is a hierarchical URI with a query component, returns it;
   * <code>null</code> otherwise.
   */
  public String query()
  {
    return null;
  }

  /**
   * Returns the URI formed from this URI and the given query.
   *
   * @exception java.lang.IllegalArgumentException if
   * <code>query</code> is not a valid query (portion) according
   * to {@link #validQuery validQuery}.
   */
  public URI appendQuery(String query)
  {
    if (!validQuery(query))
    {
      throw new IllegalArgumentException("invalid query portion: " + query);
    }
    return this;
  }

  /**
   * If this URI has a non-null {@link #query query}, returns the URI
   * formed by removing it; this URI unchanged, otherwise.
   */
  public URI trimQuery()
  {
    return this;
  }

  /**
   * If this URI has a fragment component, returns it; <code>null</code> otherwise.
   */
  public String fragment()
  {
    return null;
  }

  /**
   * Returns the URI formed from this URI and the given fragment.
   *
   * @exception java.lang.IllegalArgumentException if
   * <code>fragment</code> is not a valid fragment (portion) according
   * to {@link #validFragment validFragment}.
   */
  public URI appendFragment(String fragment)
  {
    if (fragment == null)
    {
      return this;
    }
    else
    {
      int hashCode = ((this.hashCode * 31) + FRAGMENT_SEPARATOR) * CommonUtil.powerOf31(fragment.length()) + fragment.hashCode();
      return new Fragment(hashCode, this, splitInternFragment(fragment));
    }
  }

  // Returns the URI formed from this uri and the already properly interned fragment representation.
  //
  protected URI rawAppendFragment(CharSequence fragment)
  {
    if (fragment == null)
    {
      return this;
    }
    else
    {
      int hashCode = ((this.hashCode * 31) + FRAGMENT_SEPARATOR) * CommonUtil.powerOf31(fragment.length()) + fragment.hashCode();
      return new Fragment(hashCode, this, fragment);
    }
  }

  /**
   * If this URI has a non-null {@link #fragment fragment}, returns the URI
   * formed by removing it; this URI unchanged, otherwise.
   */
  public URI trimFragment()
  {
    return this;
  }

  /**
   * Resolves this URI reference against a <code>base</code> absolute
   * hierarchical URI, returning the resulting absolute URI.  If already
   * absolute, the URI itself is returned.  URI resolution is described in
   * detail in section 5.2 of <a href="http://www.ietf.org/rfc/rfc2396.txt">RFC
   * 2396</a>, "Resolving Relative References to Absolute Form."
   *
   * <p>During resolution, empty segments, self references ("."), and parent
   * references ("..") are interpreted, so that they can be removed from the
   * path.  Step 6(g) gives a choice of how to handle the case where parent
   * references point to a path above the root: the offending segments can
   * be preserved or discarded.  This method preserves them.  To have them
   * discarded, please use the two-parameter form of {@link
   * #resolve(URI, boolean) resolve}.
   *
   * @exception java.lang.IllegalArgumentException if <code>base</code> is
   * non-hierarchical or is relative.
   */
  public URI resolve(URI base)
  {
    return resolve(base, true);
  }

  /**
   * Resolves this URI reference against a <code>base</code> absolute
   * hierarchical URI, returning the resulting absolute URI.  If already
   * absolute, the URI itself is returned.  URI resolution is described in
   * detail in section 5.2 of <a href="http://www.ietf.org/rfc/rfc2396.txt">RFC
   * 2396</a>, "Resolving Relative References to Absolute Form."
   *
   * <p>During resolution, empty segments, self references ("."), and parent
   * references ("..") are interpreted, so that they can be removed from the
   * path.  Step 6(g) gives a choice of how to handle the case where parent
   * references point to a path above the root: the offending segments can
   * be preserved or discarded.  This method can do either.
   *
   * @param preserveRootParents <code>true</code> if segments referring to the
   * parent of the root path are to be preserved; <code>false</code> if they
   * are to be discarded.
   *
   * @exception java.lang.IllegalArgumentException if <code>base</code> is
   * non-hierarchical or is relative.
   */
  public URI resolve(URI base, boolean preserveRootParents)
  {
    if (!base.isHierarchical() || base.isRelative())
    {
      throw new IllegalArgumentException(
        "resolve against non-hierarchical or relative base");
    }
    return this;
  }

  /**
   * Finds the shortest relative or, if necessary, the absolute URI that,
   * when resolved against the given <code>base</code> absolute hierarchical
   * URI using {@link #resolve(URI) resolve}, will yield this absolute URI.
   * If <code>base</code> is non-hierarchical or is relative,
   * or <code>this</code> is non-hierarchical or is relative,
   * <code>this</code> will be returned.
   */
  public URI deresolve(URI base)
  {
    return deresolve(base, true, false, true);
  }

  /**
   * Finds an absolute URI that, when resolved against the given
   * <code>base</code> absolute hierarchical URI using {@link
   * #resolve(URI, boolean) resolve}, will yield this absolute URI.
   * If <code>base</code> is non-hierarchical or is relative,
   * or <code>this</code> is non-hierarchical or is relative,
   * <code>this</code> will be returned.
   *
   * @param preserveRootParents the boolean argument to <code>resolve(URI,
   * boolean)</code> for which the returned URI should resolve to this URI.
   * @param anyRelPath if <code>true</code>, the returned URI's path (if
   * any) will be relative, if possible.  If <code>false</code>, the form of
   * the result's path will depend upon the next parameter.
   * @param shorterRelPath if <code>anyRelPath</code> is <code>false</code>
   * and this parameter is <code>true</code>, the returned URI's path (if
   * any) will be relative, if one can be found that is no longer (by number
   * of segments) than the absolute path.  If both <code>anyRelPath</code>
   * and this parameter are <code>false</code>, it will be absolute.
   */
  public URI deresolve(URI base, boolean preserveRootParents, boolean anyRelPath, boolean shorterRelPath)
  {
    return this;
  }

  protected String[] collapseSegments(boolean preserveRootParents)
  {
    return NO_SEGMENTS;
  }

  // Returns whether the string representation of the URI fully matches the given string.
  //
  protected boolean matches(String string)
  {
    return false;
  }

  // Used to match a URI against the specified components.
  //
  protected boolean matches(int validate, boolean hierarchical, String scheme, String authority, String device, boolean absolutePath, String[] segments, String query)
  {
    return false;
  }

  // Used to match a platform URI composed from these two components.
  //
  protected boolean matches(String base, String path)
  {
    return false;
  }

  /**
   * If this URI may refer directly to a locally accessible file, as
   * determined by {@link #isFile isFile}, {@link #decode decodes} and formats
   * the URI as a pathname to that file; returns null otherwise.
   *
   * <p>If there is no authority, the format of this string is:
   * <pre>
   *   device/pathSegment1/pathSegment2...</pre>
   *
   * <p>If there is an authority, it is:
   * <pre>
   *   //authority/device/pathSegment1/pathSegment2...</pre>
   *
   * <p>However, the character used as a separator is system-dependent and
   * obtained from {@link java.io.File#separatorChar}.
   */
  public String toFileString()
  {
    return null;
  }

  /**
   * If this is a platform URI, as determined by {@link #isPlatform}, returns
   * the workspace-relative or plug-in-based path to the resource, optionally
   * {@link #decode decoding} the segments in the process.
   * @see #createPlatformResourceURI(String, boolean)
   * @see #createPlatformPluginURI
   * @since org.eclipse.emf.common 2.3
   */
  public String toPlatformString(boolean decode)
  {
    return null;
  }

  /**
   * Returns the URI formed by appending the specified segment on to the end
   * of the path of this URI, if hierarchical; this URI unchanged,
   * otherwise.  If this URI has an authority and/or device, but no path,
   * the segment becomes the first under the root in an absolute path.
   *
   * @exception java.lang.IllegalArgumentException if <code>segment</code>
   * is not a valid segment according to {@link #validSegment}.
   */
  public URI appendSegment(String segment)
  {
    if (!validSegment(segment))
    {
      throw new IllegalArgumentException("invalid segment: " + segment);
    }

    return this;
  }

  /**
   * Returns the URI formed by appending the specified segments on to the
   * end of the path of this URI, if hierarchical; this URI unchanged,
   * otherwise.  If this URI has an authority and/or device, but no path,
   * the segments are made to form an absolute path.
   *
   * @param segments an array of non-null strings, each representing one
   * segment of the path.  If desired, a trailing separator should be
   * represented by an empty-string segment as the last element of the
   * array.
   *
   * @exception java.lang.IllegalArgumentException if <code>segments</code>
   * is not a valid segment array according to {@link #validSegments}.
   */
  public URI appendSegments(String[] segments)
  {
    if (!validSegments(segments))
    {
      String s = segments == null ? "invalid segments: null" : "invalid segment: " + firstInvalidSegment(segments);
      throw new IllegalArgumentException(s);
    }

    return this;
  }

  /**
   * Returns the URI formed by trimming the specified number of segments
   * (including empty segments, such as one representing a trailing
   * separator) from the end of the path of this URI, if hierarchical;
   * otherwise, this URI is returned unchanged.
   *
   * <p>Note that if all segments are trimmed from an absolute path, the
   * root absolute path remains.
   *
   * @param i the number of segments to be trimmed in the returned URI.  If
   * less than 1, this URI is returned unchanged; if equal to or greater
   * than the number of segments in this URI's path, all segments are
   * trimmed.
   */
  public URI trimSegments(int i)
  {
    return this;
  }

  /**
   * Returns <code>true</code> if this is a hierarchical URI that has a path
   * that ends with a trailing separator; <code>false</code> otherwise.
   *
   * <p>A trailing separator is represented as an empty segment as the
   * last segment in the path; note that this definition does <em>not</em>
   * include the lone separator in the root absolute path.
   */
  public boolean hasTrailingPathSeparator()
  {
    return false;
  }

  /**
   * If this is a hierarchical URI whose path includes a file extension,
   * that file extension is returned; null otherwise.  We define a file
   * extension as any string following the last period (".") in the final
   * path segment.  If there is no path, the path ends in a trailing
   * separator, or the final segment contains no period, then we consider
   * there to be no file extension.  If the final segment ends in a period,
   * then the file extension is an empty string.
   */
  public String fileExtension()
  {
    return null;
  }

  /**
   * Returns the URI formed by appending a period (".") followed by the
   * specified file extension to the last path segment of this URI, if it is
   * hierarchical with a non-empty path ending in a non-empty segment;
   * otherwise, this URI is returned unchanged.

   * <p>The extension is appended regardless of whether the segment already
   * contains an extension.
   *
   * @exception java.lang.IllegalArgumentException if
   * <code>fileExtension</code> is not a valid segment (portion) according
   * to {@link #validSegment}.
   */
  public URI appendFileExtension(String fileExtension)
  {
    if (!validSegment(fileExtension))
    {
      throw new IllegalArgumentException("invalid segment portion: " + fileExtension);
    }

    return this;
  }

  /**
   * If this URI has a non-null {@link #fileExtension fileExtension},
   * returns the URI formed by removing it; this URI unchanged, otherwise.
   */
  public URI trimFileExtension()
  {
    return this;
  }

  /**
   * Returns <code>true</code> if this is a hierarchical URI that ends in a
   * slash; that is, it has a trailing path separator or is the root
   * absolute path, and has no query and no fragment; <code>false</code>
   * is returned otherwise.
   */
  public boolean isPrefix()
  {
    return false;
  }

  /**
   * If this is a hierarchical URI reference and <code>oldPrefix</code> is a
   * prefix of it, this returns the URI formed by replacing it by
   * <code>newPrefix</code>; <code>null</code> otherwise.
   *
   * <p>In order to be a prefix, the <code>oldPrefix</code>'s
   * {@link #isPrefix isPrefix} must return <code>true</code>, and it must
   * match this URI's scheme, authority, and device.  Also, the paths must
   * match, up to prefix's end.
   *
   * @exception java.lang.IllegalArgumentException if either
   * <code>oldPrefix</code> or <code>newPrefix</code> is not a prefix URI
   * according to {@link #isPrefix}.
   */
  public URI replacePrefix(URI oldPrefix, URI newPrefix)
  {
    if (!oldPrefix.isPrefix() || !newPrefix.isPrefix())
    {
      String which = oldPrefix.isPrefix() ? "new" : "old";
      throw new IllegalArgumentException("non-prefix " + which + " value");
    }

    return null;
  }

  /**
   * Encodes a string so as to produce a valid opaque part value, as defined
   * by the RFC.  All excluded characters, such as space and <code>#</code>,
   * are escaped, as is <code>/</code> if it is the first character.
   *
   * @param ignoreEscaped <code>true</code> to leave <code>%</code> characters
   * unescaped if they already begin a valid three-character escape sequence;
   * <code>false</code> to encode all <code>%</code> characters.  Note that
   * if a <code>%</code> is not followed by 2 hex digits, it will always be
   * escaped.
   */
  public static String encodeOpaquePart(String value, boolean ignoreEscaped)
  {
    String result = encode(value, URIC_HI, URIC_LO, ignoreEscaped);
    return
      result != null && result.length() > 0 && result.charAt(0) == SEGMENT_SEPARATOR ? "%2F" + result.substring(1) : result;
  }

  /**
   * Encodes a string so as to produce a valid authority, as defined by the
   * RFC.  All excluded characters, such as space and <code>#</code>,
   * are escaped, as are <code>/</code> and <code>?</code>
   *
   * @param ignoreEscaped <code>true</code> to leave <code>%</code> characters
   * unescaped if they already begin a valid three-character escape sequence;
   * <code>false</code> to encode all <code>%</code> characters.  Note that
   * if a <code>%</code> is not followed by 2 hex digits, it will always be
   * escaped.
   */
  public static String encodeAuthority(String value, boolean ignoreEscaped)
  {
    return encode(value, SEGMENT_CHAR_HI, SEGMENT_CHAR_LO, ignoreEscaped);
  }

  /**
   * Encodes a string so as to produce a valid segment, as defined by the
   * RFC.  All excluded characters, such as space and <code>#</code>,
   * are escaped, as are <code>/</code> and <code>?</code>
   *
   * @param ignoreEscaped <code>true</code> to leave <code>%</code> characters
   * unescaped if they already begin a valid three-character escape sequence;
   * <code>false</code> to encode all <code>%</code> characters.  Note that
   * if a <code>%</code> is not followed by 2 hex digits, it will always be
   * escaped.
   */
  public static String encodeSegment(String value, boolean ignoreEscaped)
  {
    return encode(value, SEGMENT_CHAR_HI, SEGMENT_CHAR_LO, ignoreEscaped);
  }

  /**
   * Encodes a string so as to produce a valid query, as defined by the RFC.
   * Only excluded characters, such as space and <code>#</code>, are escaped.
   *
   * @param ignoreEscaped <code>true</code> to leave <code>%</code> characters
   * unescaped if they already begin a valid three-character escape sequence;
   * <code>false</code> to encode all <code>%</code> characters.  Note that
   * if a <code>%</code> is not followed by 2 hex digits, it will always be
   * escaped.
   */
  public static String encodeQuery(String value, boolean ignoreEscaped)
  {
    return encode(value, URIC_HI, URIC_LO, ignoreEscaped);
  }

  /**
   * Encodes a string so as to produce a valid fragment, as defined by the
   * RFC.  Only excluded characters, such as space and <code>#</code>, are
   * escaped.
   *
   * @param ignoreEscaped <code>true</code> to leave <code>%</code> characters
   * unescaped if they already begin a valid three-character escape sequence;
   * <code>false</code> to encode all <code>%</code> characters.  Note that
   * if a <code>%</code> is not followed by 2 hex digits, it will always be
   * escaped.
   */
  public static String encodeFragment(String value, boolean ignoreEscaped)
  {
    return encode(value, URIC_HI, URIC_LO, ignoreEscaped);
  }

  // Encodes a complete URI, optionally leaving % characters unescaped when
  // beginning a valid three-character escape sequence.  We can either treat
  // the first or # as a fragment separator, or encode them all.
  protected static String encodeURI(String uri, boolean ignoreEscaped, int fragmentLocationStyle)
  {
    if (uri == null) return null;

    StringBuffer result = new StringBuffer();

    int i = uri.indexOf(SCHEME_SEPARATOR);
    if (i != -1)
    {
      String scheme = uri.substring(0, i);
      result.append(scheme);
      result.append(SCHEME_SEPARATOR);
    }

    int j =
      fragmentLocationStyle == FRAGMENT_FIRST_SEPARATOR ?
        uri.indexOf(FRAGMENT_SEPARATOR) :
        fragmentLocationStyle == FRAGMENT_LAST_SEPARATOR ?
          uri.lastIndexOf(FRAGMENT_SEPARATOR) :
          -1;

    if (j != -1)
    {
      String sspart = uri.substring(++i, j);
      result.append(encode(sspart, URIC_HI, URIC_LO, ignoreEscaped));
      result.append(FRAGMENT_SEPARATOR);

      String fragment = uri.substring(++j);
      result.append(encode(fragment, URIC_HI, URIC_LO, ignoreEscaped));
    }
    else
    {
      String sspart = uri.substring(++i);
      result.append(encode(sspart, URIC_HI, URIC_LO, ignoreEscaped));
    }

    return result.toString();
  }

  // Encodes the given string, replacing each ASCII character that is not in
  // the set specified by the 128-bit bitmask and each non-ASCII character
  // below 0xA0 by an escape sequence of % followed by two hex digits.  If
  // % is not in the set but ignoreEscaped is true, then % will not be encoded
  // iff it already begins a valid escape sequence.
  protected static String encode(String value, long highBitmask, long lowBitmask, boolean ignoreEscaped)
  {
    if (value == null) return null;

    StringBuffer result = null;

    for (int i = 0, len = value.length(); i < len; i++)
    {
      char c = value.charAt(i);

      if (!matches(c, highBitmask, lowBitmask) && c < 160 && (!ignoreEscaped || !isEscaped(value, i)))
      {
        if (result == null)
        {
          result = new StringBuffer(value.substring(0, i));
        }
        appendEscaped(result, (byte)c);
      }
      else if (result != null)
      {
        result.append(c);
      }
    }
    return result == null ? value : result.toString();
  }

  // Tests whether an escape occurs in the given string, starting at index i.
  // An escape sequence is a % followed by two hex digits.
  protected static boolean isEscaped(String s, int i)
  {
    return s.charAt(i) == ESCAPE && s.length() > i + 2 && matches(s.charAt(i + 1), HEX_HI, HEX_LO) && matches(s.charAt(i + 2), HEX_HI, HEX_LO);
  }

  // Computes a three-character escape sequence for the byte, appending
  // it to the StringBuffer.  Only characters up to 0xFF should be escaped;
  // all but the least significant byte will be ignored.
  protected static void appendEscaped(StringBuffer result, byte b)
  {
    result.append(ESCAPE);

    // The byte is automatically widened into an int, with sign extension,
    // for shifting.  This can introduce 1's to the left of the byte, which
    // must be cleared by masking before looking up the hex digit.
    //
    result.append(HEX_DIGITS[(b >> 4) & 0x0F]);
    result.append(HEX_DIGITS[b & 0x0F]);
  }

  /**
   * Decodes the given string by interpreting three-digit escape sequences as the bytes of a UTF-8 encoded character
   * and replacing them with the characters they represent.
   * Incomplete escape sequences are ignored and invalid UTF-8 encoded bytes are treated as extended ASCII characters.
   */
  public static String decode(String value)
  {
    if (value == null) return null;

    int i = value.indexOf('%');
    if (i < 0)
    {
      return value;
    }
    else
    {
      StringBuilder result = new StringBuilder(value.substring(0, i));
      byte [] bytes = new byte[4];
      int receivedBytes = 0;
      int expectedBytes = 0;
      for (int len = value.length(); i < len; i++)
      {
        if (isEscaped(value, i))
        {
          char character = unescape(value.charAt(i + 1), value.charAt(i + 2));
          i += 2;

          if (expectedBytes > 0)
          {
            if ((character & 0xC0) == 0x80)
            {
              bytes[receivedBytes++] = (byte)character;
            }
            else
            {
              expectedBytes = 0;
            }
          }
          else if (character >= 0x80)
          {
            if ((character & 0xE0) == 0xC0)
            {
              bytes[receivedBytes++] = (byte)character;
              expectedBytes = 2;
            }
            else if ((character & 0xF0) == 0xE0)
            {
              bytes[receivedBytes++] = (byte)character;
              expectedBytes = 3;
            }
            else if ((character & 0xF8) == 0xF0)
            {
              bytes[receivedBytes++] = (byte)character;
              expectedBytes = 4;
            }
          }

          if (expectedBytes > 0)
          {
            if (receivedBytes == expectedBytes)
            {
              switch (receivedBytes)
              {
                case 2:
                {
                  result.append((char)((bytes[0] & 0x1F) << 6 | bytes[1] & 0x3F));
                  break;
                }
                case 3:
                {
                  result.append((char)((bytes[0] & 0xF) << 12 | (bytes[1] & 0X3F) << 6 | bytes[2] & 0x3F));
                  break;
                }
                case 4:
                {
                  result.appendCodePoint(((bytes[0] & 0x7) << 18 | (bytes[1] & 0X3F) << 12 | (bytes[2] & 0X3F) << 6 | bytes[3] & 0x3F));
                  break;
                }
              }
              receivedBytes = 0;
              expectedBytes = 0;
            }
          }
          else
          {
            for (int j = 0; j < receivedBytes; ++j)
            {
              result.append((char)bytes[j]);
            }
            receivedBytes = 0;
            result.append(character);
          }
        }
        else
        {
          for (int j = 0; j < receivedBytes; ++j)
          {
            result.append((char)bytes[j]);
          }
          receivedBytes = 0;
          result.append(value.charAt(i));
        }
      }
      return result.toString();
    }
  }

  // Returns the character encoded by % followed by the two given hex digits,
  // which is always 0xFF or less, so can safely be casted to a byte.  If
  // either character is not a hex digit, a bogus result will be returned.
  protected static char unescape(char highHexDigit, char lowHexDigit)
  {
    return (char)((valueOf(highHexDigit) << 4) | valueOf(lowHexDigit));
  }

  // Returns the int value of the given hex digit.
  protected static int valueOf(char hexDigit)
  {
    if (hexDigit <= '9')
    {
      if (hexDigit >= '0')
      {
        return hexDigit - '0';
      }
    }
    else if (hexDigit <= 'F')
    {
      if (hexDigit >= 'A')
      {
        return hexDigit - 'A' + 10;
      }
    }
    else if (hexDigit >= 'a' && hexDigit <= 'f')
    {
      return hexDigit - 'a' + 10;
    }

    return 0;
  }
}
