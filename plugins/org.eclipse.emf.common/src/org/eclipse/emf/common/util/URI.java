/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: URI.java,v 1.4 2004/04/12 17:08:30 davidms Exp $
 */
package org.eclipse.emf.common.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
 * to identify files on a local file system.  Acordingly, most of this
 * class's functionality is for handling such URIs, which can be identified
 * via {@link #isHierarchical}.
 *
 * <p><a name="device_explaination">
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
 * {@link #createURI}; if the first segment of the path ends with the
 * <code>:</code> character, it is stored (including the colon) as the
 * device, instead.  Alternately, either the {@link
 * #createHierarchicalURI(String, String, String, String, String) no-path}
 * or the {@link #createHierarchicalURI(String, String, String, String[],
 * String, String) absolute-path} form of <code>createHierarchicalURI()</code>
 * can be used, in which a non-null <code>device</code> parameter can be
 * specified.
 *
 * <p><a name="jar_explaination"> 
 * The other enhancement provides support for the almost-hierarchical
 * JAR scheme, defined for the Java Platform in the documentation for {@link
 * java.net.JarURLConnection}.  This support is enabled for absolute URIs
 * with scheme equal to "jar" (ignoring case), and is implemented by a
 * hierarchical URI, whose authority includes the entire archive URI, up to
 * and including the <code>!</code> character.  The archive URI must be
 * absolute and have no fragment.  JAR-scheme URIs must have no device and
 * an absolute path.  Special handling is supported for {@link #createURI
 * creating}, {@link #validJarAuthority validating}, {@link #devicePath
 * getting the path} from, and {@link #toString displaying} JAR-scheme URIs.
 * In all other operations, including {@link #resolve(URI) resolving} and
 * {@link #deresolve(URI) deresolving}, they are handled like any ordinary
 * URI.
 *
 * <p>Compared to the RFC description, this implementation is quite relaxed
 * about validity.  Static methods whose names begin with "valid" test
 * whether a given string is a valid value for the various URI components.
 * Presently, these tests place no restrictions beyond what would have been
 * required in order for {@link #createURI} to
 * have parsed them correctly from a single URI string.  Note that all of
 * the static factory methods invoke the appropriate validation methods and
 * throw exceptions in response to a negative result, ensuring that
 * invalid URIs are never created.
 *
 * <p>Finally, note the difference between a <code>null</code> parameter to
 * the static factory methods and an empty string.  The former signifies the
 * absense of a given URI component, while the latter simply makes the
 * component blank.  This can have a significant effect when resolving.  For
 * example, consider the following two URIs: <code>/bar</code> (with no
 * authority) and <code>///bar</code> (with a blank authority).  Imagine
 * resolving them against a base with an authority, such as
 * <code>http://www.eclipse.org/</code>.  The former case will yield
 * <code>http://www.eclipse.org/bar</code>, as the base authority will be
 * preserved.  In the latter case, the empty authority will override the
 * base authority, resulting in <code>http:///bar</code>!
 */
public final class URI
{
  // Common to all URI types.
  private final int hashCode;
  private final boolean hierarchical;
  private final String scheme;  // null -> relative URI reference
  private final String authority;
  private final String fragment;
  private URI cachedTrimFragment;
  private String cachedToString;

  // Applicable only to a hierarchical URI.
  private final String device;
  private final boolean absolutePath;
  private final String[] segments; // empty last segment -> trailing separator
  private final String query;

  // Identifies a file-type absolute URI.
  private static final String SCHEME_FILE = "file";
  private static final String SCHEME_JAR = "jar";

  // Special segment values interpreted at resolve and resolve time.
  private static final String SEGMENT_EMPTY = "";
  private static final String SEGMENT_SELF = ".";
  private static final String SEGMENT_PARENT = "..";
  private static final String[] NO_SEGMENTS = new String[0];

  // Separators for parsing a URI string
  private static final char SCHEME_SEPARATOR = ':';
  private static final String AUTHORITY_SEPARATOR = "//";
  private static final char DEVICE_IDENTIFIER = ':';
  private static final char SEGMENT_SEPARATOR = '/';
  private static final char QUERY_SEPARATOR = '?';
  private static final char FRAGMENT_SEPARATOR = '#';
  private static final char USER_INFO_SEPARATOR = '@';
  private static final char PORT_SEPARATOR = ':';
  private static final char FILE_EXTENSION_SEPARATOR = '.';
  private static final char[] MAJOR_SEPARATORS = {
    SCHEME_SEPARATOR, SEGMENT_SEPARATOR, QUERY_SEPARATOR, FRAGMENT_SEPARATOR };
  private static final char[] SEGMENT_END = {
    SEGMENT_SEPARATOR, QUERY_SEPARATOR, FRAGMENT_SEPARATOR };
  private static final char JAR_IDENTIFIER = '!';
  private static final String JAR_SEPARATOR = "!/";

  /**
   * Static factory method for a generic, non-hierarchical URI.  There is no
   * concept of a relative non-hierarchical URI; such an object cannot be
   * created.
   *
   * @exception java.lang.IllegalArgumentException if <code>scheme</code> is
   * null, if <code>scheme</code> is "jar" by case-insensitive comparison,
   * or if <code>scheme</code>, <code>opaquePart</code>, or
   * <code>fragment</code> is not valid according to {@link #validScheme},
   * {@link #validOpaquePart}, or {@link #validFragment}, respectively.
   */
  public static URI createGenericURI(String scheme, String opaquePart,
                                     String fragment)
  {
    if (scheme == null)
    {
      throw new IllegalArgumentException("relative non-hierarchical URI");
    }

    if (SCHEME_JAR.equalsIgnoreCase(scheme))
    {
      throw new IllegalArgumentException("non-hierarchical JAR-scheme URI");
    }

    validateURI(false, scheme, opaquePart, null, false, NO_SEGMENTS, null, fragment);
    return new URI(false, scheme, opaquePart, null, false, NO_SEGMENTS, null, fragment);
  }

  /**
   * Static factory method for a hierarchical URI with no path.  The
   * URI will be relative if <code>scheme</code> is non-null, and absolute
   * otherwise.  An absolute URI with no path requires a non-null
   * <code>authority</code> and/or <code>device</code>.
   *
   * @exception java.lang.IllegalArgumentException if <code>scheme</code> is
   * non-null while <code>authority</code> and <code>device</code> are null,
   * if <code>scheme</code> is "jar" by case-insensitive comparison, or
   * if <code>scheme</code>, <code>authority</code>, <code>device</code>,
   * <code>query</code>, or <code>fragment</code> is not valid according to
   * {@link #validScheme}, {@link #validAuthority}, {@link #validDevice},
   * {@link #validQuery}, or {@link #validFragment}, respectively.
   */
  public static URI createHierarchicalURI(String scheme, String authority,
                                          String device, String query,
                                          String fragment)
  {
    if (scheme != null && authority == null && device == null)
    {
      throw new IllegalArgumentException(
        "absolute hierarchical URI without authority, device, path");
    }

    if (SCHEME_JAR.equalsIgnoreCase(scheme))
    {
      throw new IllegalArgumentException("JAR-scheme URI with no path");
    }

    validateURI(true, scheme, authority, device, false, NO_SEGMENTS, query, fragment);
    return new URI(true, scheme, authority, device, false, NO_SEGMENTS, query, fragment);
  }

  /**
   * Static factory method for a hierarchical URI with absolute path.
   * The URI will be relative if <code>scheme</code> is non-null, and
   * absolute otherwise. 
   *
   * @param segments an array of non-null strings, each representing one
   * segment of the path.  As an absolute path, it is automatically
   * preceeded by a <code>/</code> separator.  If desired, a trailing
   * separator should be represented by an empty-string segment as the last
   * element of the array. 
   *
   * @exception java.lang.IllegalArgumentException if <code>scheme</code> is
   * "jar" by case-insensitive comparison and <code>device</code> is
   * non-null, or if <code>scheme</code>, <code>authority</code>,
   * <code>device</code>, <code>segments</code> <code>query</code>, or
   * <code>fragment</code> is not valid according to {@link #validScheme},
   * {@link #validAuthority} or {@link #validJarAuthority}, {@link
   * #validDevice}, {@link #validSegments}, {@link #validQuery}, or {@link
   * #validFragment}, as appropriate.
   */
  public static URI createHierarchicalURI(String scheme, String authority,
                                          String device, String[] segments,
                                          String query, String fragment)
  {
    if (SCHEME_JAR.equalsIgnoreCase(scheme) && device != null)
    {
      throw new IllegalArgumentException("JAR-scheme URI with device");
    }

    segments = fix(segments);
    validateURI(true, scheme, authority, device, true, segments, query, fragment);
    return new URI(true, scheme, authority, device, true, segments, query, fragment);
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
   * {@link #validSegments}, {@link #validQuery}, or {@link #validFragment},
   * respectively.
   */
  public static URI createHierarchicalURI(String[] segments, String query,
                                          String fragment)
  {
    segments = fix(segments);
    validateURI(true, null, null, null, false, segments, query, fragment);
    return new URI(true, null, null, null, false, segments, query, fragment);
  }

  // Converts null to length-zero array, and clones array to ensure
  // immutability.
  private static String[] fix(String[] segments)
  {
    return segments == null ? NO_SEGMENTS : (String[])segments.clone();
  }
  
  /**
   * Static factory method based on parsing a URI string, with 
   * <a href="#device_explaination">explicit device support</a> and handling
   * for <a href="#jar_explaination">JAR-scheme URIs</a> enabled. The
   * specified string is parsed as described in <a
   * href="http://www.ietf.org/rfc/rfc2396.txt">RFC 2396</a>, and an
   * appropriate <code>URI</code> is created and returned.  Note that
   * validity testing is not as strict as in the RFC; essentially, only
   * separator characters are considered.  So, for example, non-Latin
   * alphabet characters appearing in the scheme would not be considered an
   * error.
   *
   * @exception java.lang.IllegalArgumentException if any component parsed
   * from <code>uri</code> is not valid according to {@link #validScheme},
   * {@link #validOpaquePart}, {@link #validAuthority}, {@link
   * #validJarAuthority}, {@link #validDevice}, {@link #validSegments},
   * {@link #validQuery}, or {@link #validFragment}, as appropriate.
   */
  public static URI createURI(String uri)
  {
    return parseIntoURI(uri);
  }

  /**
   * Static factory method based on parsing a URI string, with 
   * <a href="#device_explaination">explicit device support</a> enabled.  
   * Note that validity testing is not a strict as in the RFC; essentially,
   * only separator characters are considered.  So, for example, non-Latin
   * alphabet characters appearing in the scheme would not be considered an
   * error.
   *
   * @exception java.lang.IllegalArgumentException if any component parsed
   * from <code>uri</code> is not valid according to {@link #validScheme},
   * {@link #validOpaquePart}, {@link #validAuthority}, {@link
   * #validJarAuthority}, {@link #validDevice}, {@link #validSegments},
   * {@link #validQuery}, or {@link #validFragment}, as appropriate.
   *
   * @deprecated Use {@link #createURI}, which now has explicit device
   * support enabled. The two methods now operate identically.
   */
  public static URI createDeviceURI(String uri)
  {
    return parseIntoURI(uri);
  }

  // String-parsing implementation.
  private static URI parseIntoURI(String uri)
  {
    boolean hierarchical = true;
    String scheme = null;
    String authority = null;
    String device = null;
    boolean absolutePath = false;
    String[] segments = NO_SEGMENTS;
    String query = null;
    String fragment = null;

    int i = 0;
    int j = findSeparator(uri, i, MAJOR_SEPARATORS);

    if (j < uri.length() && uri.charAt(j) == SCHEME_SEPARATOR)
    {
      scheme = uri.substring(i, j);
      i = j + 1;
    }

    boolean jarScheme = SCHEME_JAR.equalsIgnoreCase(scheme);
    if (jarScheme)
    {
      j = uri.indexOf(JAR_SEPARATOR, i);
      if (j == -1)
      {
        throw new IllegalArgumentException("no JAR separator");
      }
      hierarchical = true;
      authority = uri.substring(i, ++j);
      i = j;
    }
    else if (uri.startsWith(AUTHORITY_SEPARATOR, i))
    {
      i += AUTHORITY_SEPARATOR.length();
      j = findSeparator(uri, i, SEGMENT_END);
      authority = uri.substring(i, j);
      i = j;
    }
    else if (scheme != null &&
             (i == uri.length() || uri.charAt(i) != SEGMENT_SEPARATOR))
    {
      hierarchical = false;
      j = findSeparator(uri, i, new char[] { FRAGMENT_SEPARATOR });
      authority = uri.substring(i, j);
      i = j;
    }

    if (!jarScheme && i < uri.length() && uri.charAt(i) == SEGMENT_SEPARATOR)
    {
      j = findSeparator(uri, i + 1, SEGMENT_END);
      String s = uri.substring(i + 1, j);
      
      if (s.length() > 0 && s.charAt(s.length() - 1) == DEVICE_IDENTIFIER)
      {
        device = s;
        i = j;
      }
    }

    if (i < uri.length() && uri.charAt(i) == SEGMENT_SEPARATOR)
    {
      i++;
      absolutePath = true;
    }

    if (segmentsRemain(uri, i))
    {
      List segmentList = new ArrayList();

      while (segmentsRemain(uri, i))
      {
        j = findSeparator(uri, i, SEGMENT_END);
        segmentList.add(uri.substring(i, j));
        i = j;

        if (i < uri.length() && uri.charAt(i) == SEGMENT_SEPARATOR)
        {
          if (!segmentsRemain(uri, ++i)) segmentList.add(SEGMENT_EMPTY);
        }
      }
      segments = new String[segmentList.size()];
      segmentList.toArray(segments);
    }

    if (i < uri.length() && uri.charAt(i) == QUERY_SEPARATOR)
    {
      j = findSeparator(uri, ++i, new char[] { FRAGMENT_SEPARATOR });
      query = uri.substring(i, j);
      i = j;
    }

    if (i < uri.length()) // && uri.charAt(i) == FRAGMENT_SEPARATOR (implied)
    {
      fragment = uri.substring(++i);
    }

    validateURI(hierarchical, scheme, authority, device, absolutePath, segments, query, fragment);
    return new URI(hierarchical, scheme, authority, device, absolutePath, segments, query, fragment);
  }

  /**
   * Static factory method based on parsing a {@link java.io.File} path
   * string.  The <code>pathName</code> is converted into an appropriate form
   * (platform-specific path separators are converted to to <code>/</code>;
   * a "file" scheme and, if missing, a leading <code>/</code> are added to
   * an absolute path) and then parsed using {@link #createURI}.
   *
   * <p>A relative path with a specified device (something like
   * <code>C:myfile.txt</code>) cannot be expressed as a valid URI.
   * 
   * @exception java.lang.IllegalArgumentException if <code>pathName</code>
   * specifies a device and a relative path, or if any component of the path
   * is not valid according to {@link #validAuthority}, {@link #validDevice},
   * or {@link #validSegments}, {@link #validQuery}, or {@link #validFragment}.
   */
  public static URI createFileURI(String pathName)
  {
    File file = new File(pathName);
    String uri = File.separatorChar != '/' ? pathName.replace(File.separatorChar, SEGMENT_SEPARATOR) : pathName;
    if (uri.indexOf(' ') != -1)
    {
      uri = uri.replaceAll(" ", "%20");
    }
    if (file.isAbsolute())
    {
      URI result = parseIntoURI((uri.charAt(0) == SEGMENT_SEPARATOR ? "file:" : "file:/") + uri);
      return result;
    }
    else
    {
      URI result = parseIntoURI(uri);
      if (result.scheme() != null)
      {
        throw new IllegalArgumentException("invalid relative pathName: " + pathName);
      }
      return result;
    }
  }

  /**
   * Static factory method based on parsing a platform-relative path string.
   *
   * <p>The <code>pathName</code> must be of the form:
   * <pre>
   *   /project-name/path</pre>
   *
   * <p>If not included, the leading path separator will be added.  The
   * result will be of this form, which is parsed using {@link #createURI}:
   * <pre>
   *   platform:/resource/project-name/path</pre>
   *
   * <p>This scheme supports relocatable projects in Eclipse and in
   * stand-alone EMF.
   *
   * @exception java.lang.IllegalArgumentException if any component parsed
   * from the path is not valid according to {@link #validDevice}, {@link
   * #validSegments}, {@link #validQuery}, or {@link #validFragment}.
   *
   * @see org.eclipse.core.runtime.Platform#resolve
   */
  public static URI createPlatformResourceURI(String pathName)
  {
    URI result = parseIntoURI((pathName.charAt(0) == SEGMENT_SEPARATOR ? "platform:/resource" : "platform:/resource/") + pathName);
    return result;
  }

  // Checks whether the string contains any more segments after the one that
  // starts at position i.
  private static boolean segmentsRemain(String uri, int i)
  {
    return i < uri.length() && uri.charAt(i) != QUERY_SEPARATOR &&
      uri.charAt(i) != FRAGMENT_SEPARATOR;
  }

  // Finds the next occurance of one of the characters specified in
  // separators in the given URI, beginning at index i.  The index of the
  // first separator, or uri.length() if there is no such character, is
  // returned.  Before searching, i is limited to be in the range 
  // [0, uri.length()].
  private static int findSeparator(String uri, int i, char[] separators)
  {
    int len = uri.length();
    if (i >= len) return len;

    outerLoop: for (i = i > 0 ? i : 0; i < len; i++)
    {
      for (int j = 0, slen = separators.length; j < slen; j++)
      {
        if (uri.charAt(i) == separators[j]) break outerLoop;
      }
    }
    return i;
  }

  // Private constructor for use of static factory methods.
  private URI(boolean hierarchical, String scheme, String authority,
              String device, boolean absolutePath, String[] segments,
              String query, String fragment)
  {
    int hashCode = 0;
    if (hierarchical)
    {
      ++hashCode;
    }
    if (absolutePath)
    {
      hashCode += 2;
    }
    if (scheme != null)
    {
      hashCode ^= scheme.toLowerCase().hashCode();
    }
    if (authority != null)
    {
      hashCode ^= authority.hashCode();
    }
    if (device != null)
    {
      hashCode ^= device.hashCode();
    }
    if (query != null)
    {
      hashCode ^= query.hashCode();
    }
    if (fragment != null)
    {
      hashCode ^= fragment.hashCode();
    }

    for (int i = 0, len = segments.length; i < len; i++)
    {
      hashCode ^= segments[i].hashCode();
    }

    this.hashCode = hashCode;
    this.hierarchical = hierarchical;
    this.scheme = scheme == null ? null : scheme.intern();
    this.authority = authority;
    this.device = device;
    this.absolutePath = absolutePath;
    this.segments = segments;
    this.query = query;
    this.fragment = fragment;
  }
  
  // Validates all of the URI components.  Factory methods should call this
  // before using the constructor, though they must ensure that the
  // inter-component requirements described in their own Javadocs are all
  // satisfied, themselves.  If a new URI is being constructed out of
  // an existing URI, this need not be called.  Instead, just the new
  // components may be validated individually.
  private static void validateURI(boolean hierarchical, String scheme,
                                    String authority, String device,
                                    boolean absolutePath, String[] segments,
                                    String query, String fragment)
  {
    boolean jarScheme = SCHEME_JAR.equalsIgnoreCase(scheme);

    if (!validScheme(scheme))
    {
      throw new IllegalArgumentException("invalid scheme: " + scheme);
    }
    if (!hierarchical && !validOpaquePart(authority))
    {
      throw new IllegalArgumentException("invalid opaquePart: " + authority);
    }
    if (hierarchical && !jarScheme && !validAuthority(authority))
    {
      throw new IllegalArgumentException("invalid authority: " + authority);
    }
    if (hierarchical && jarScheme && !validJarAuthority(authority))
    {
      throw new IllegalArgumentException("invalid authority: " + authority);
    }
    if (!validDevice(device))
    {
      throw new IllegalArgumentException("invalid device: " + device);
    }
    if (!validSegments(segments))
    {
      String s = segments == null ? "invalid segments: " + segments :
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
  }

  // Searches the specified string for any of the specified target
  // characters, and if any occur, returns true; false otherwise.
  private static boolean contains(String s, char[] targets)
  {
    for (int i = 0, len = s.length(); i < len; i++)
    {
      for (int j = 0, tlen = targets.length; j < tlen; j++)
      {
        if (s.charAt(i) == targets[j]) return true;
      }
    }
    return false;
  }

  /**
   * Returns <code>true</code> if the specified <code>value</code> would be
   * valid as the scheme component of a URI; <code>false</code> otherwise.
   *
   * <p>A valid scheme may be null or contain any characters except for the
   * following: <code>: / ? #</code>
   */
  public static boolean validScheme(String value)
  {
    return value == null || !contains(value, MAJOR_SEPARATORS);
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
    return value != null && value.indexOf(FRAGMENT_SEPARATOR) == -1 &&
      value.length() > 0 && value.charAt(0) != SEGMENT_SEPARATOR;
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
    return value == null || !contains(value, SEGMENT_END);
  }

  /**
   * Return <code>true</code> if the specified <code>value</code> would be
   * valid as the authority component of a <a
   * href="#jar_explaination">JAR-scheme URI</a>; <code>false</code>
   * otherwise.
   *
   * <p>To be valid, a JAR-scheme URI must have an authority that is,
   * itself, an absolute URI with no fragment, followed by the character
   * <code>!</code>.
   */
  public static boolean validJarAuthority(String value)
  {
    if (value != null && value.length() > 0 &&
        value.charAt(value.length() - 1) == JAR_IDENTIFIER)
    {
      try
      {
        URI jarURI = parseIntoURI(value.substring(0, value.length() - 1));
        return !jarURI.isRelative() && !jarURI.hasFragment();
      }
      catch (IllegalArgumentException e)
      {
      }
    }
    return false;
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
    return len > 0 && value.charAt(len - 1) == DEVICE_IDENTIFIER &&
      !contains(value, SEGMENT_END);
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
    return value != null && !contains(value, SEGMENT_END);
  }

  /**
   * Returns <code>true</code> if the specified <code>value</code> would be
   * a valid path segment array of a URI; <code>false</code> otherwise.
   *
   * <p>A valid path segment array must be non-null and contain only path
   * segements that are valid, according to {@link #validSegment}.
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

  // Returns null if the specicied value is null or would be a valid path
  // segment array of a URI; otherwise, the value of the first invalid
  // segment. 
  private static String firstInvalidSegment(String[] value)
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
  }

  /**
   * Returns <code>true</code> if this is a relative URI, or
   * <code>false</code> if it is an absolute URI.
   */
  public boolean isRelative()
  {
    return scheme == null;
  }

  /**
   * Returns <code>true</code> if this a a hierarchical URI, or
   * <code>false</code> if it is of the generic form.
   */
  public boolean isHierarchical()
  {
    return hierarchical;
  }

  /**
   * Returns <code>true</code> if this is a hierarcical URI with an authority
   * component; <code>false</code> otherwise. 
   */
  public boolean hasAuthority()
  {
    return hierarchical && authority != null;
  }

  /**
   * Returns <code>true</code> if this is a non-hierarchical URI with an
   * opaque part component; <code>false</code> otherwise.
   */
  public boolean hasOpaquePart()
  {
    // note: hierarchical -> authority != null
    return !hierarchical;
  }

  /**
   * Returns <code>true</code> if this is a hierarchical URI with a device
   * component; <code>false</code> otherwise.
   */
  public boolean hasDevice()
  {
    // note: device != null -> hierarchical
    return device != null;
  }

  /**
   * Returns <code>true</code> if this is a hierarchical URI with an
   * absolute or relative path; <code>false</code> otherwise.
   */
  public boolean hasPath()
  {
    // note: (absolutePath || authority == null) -> hierarchical
    // (authority == null && device == null && !absolutePath) -> scheme == null
    return absolutePath || (authority == null && device == null);
  }

  /**
   * Returns <code>true</code> if this is a hierarchical URI with an
   * absolute path, or <code>false</code> if it is non-hierarchical, has no
   * path, or has a relative path.
   */
  public boolean hasAbsolutePath()
  {
    // note: absolutePath -> hierarchical
    return absolutePath;
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
    return authority == null && device == null && !absolutePath;
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
    return authority == null && device == null && !absolutePath &&
      segments.length == 0;
  }

  /**
   * Returns <code>true</code> if this is a hierarchical URI with a query
   * component; <code>false</code> otherwise.
   */
  public boolean hasQuery()
  {
    // note: query != null -> hierarchical
    return query != null;
  }

  /**
   * Returns <code>true</code> if this URI has a fragment component;
   * <code>false</code> otherwise.
   */
  public boolean hasFragment()
  {
    return fragment != null;
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
    return authority == null && device == null && !absolutePath &&
      segments.length == 0 && query == null;
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
    return authority == null && device == null && !absolutePath &&
      segments.length == 0 && query == null && fragment == null;
  }

  /**
   * Returns <code>true</code> if this is a hierarchical URI that may refer
   * directly to a locally accessible file.  This is considered to be the
   * case for a file-scheme absolute URI, or for a relative URI with no query;
   * <code>false</code> is returned otherwise.
   */
  public boolean isFile()
  {
    return isHierarchical() &&
      ((isRelative() && !hasQuery()) || SCHEME_FILE.equalsIgnoreCase(scheme));
  }

  // Returns true if this is a JAR-scheme URI.  If so, we should expect that
  // it is also hierarchical, with an authority (consisting of an absolute
  // URI followed by "!"), no device, and an absolute path.
  private boolean isJar()
  {
    return SCHEME_JAR.equalsIgnoreCase(scheme);
  }

  /**
   * Returns the hash code.
   */
  public int hashCode()
  {
    return hashCode;
  }

  /**
   * Returns <code>true</code> if <code>obj</code> is an instance of
   * <code>URI</code> equal to this one; <code>false</code> otherwise.
   *
   * <p>Equality is determined strictly by comparing components, not by
   * attempting to interpret what resource is being identified.  The
   * comparison of schemes is case-insensitive.
   */
  public boolean equals(Object obj)
  {
    if (this == obj) return true;
    if (!(obj instanceof URI)) return false;
    URI uri = (URI) obj;

    return hashCode == uri.hashCode() &&
      hierarchical == uri.isHierarchical() &&
      absolutePath == uri.hasAbsolutePath() &&
      equals(scheme, uri.scheme(), true) &&
      equals(authority, hierarchical ? uri.authority() : uri.opaquePart()) &&
      equals(device, uri.device()) &&
      equals(query, uri.query()) && 
      equals(fragment, uri.fragment()) &&
      segmentsEqual(uri);
  }

  // Tests whether this URI's path segment array is equal to that of the
  // given uri.
  private boolean segmentsEqual(URI uri)
  {
    if (segments.length != uri.segmentCount()) return false;
    for (int i = 0, len = segments.length; i < len; i++)
    {
      if (!segments[i].equals(uri.segment(i))) return false;
    }
    return true;
  }

  // Tests two objects for equality, tolerating nulls; null is considered
  // to be a valid value that is only equal to itself.
  private static boolean equals(Object o1, Object o2)
  {
    return o1 == null ? o2 == null : o1.equals(o2);
  }

  // Tests two strings for equality, tolerating nulls and optionally
  // ignoring case.
  private static boolean equals(String s1, String s2, boolean ignoreCase)
  {
    return s1 == null ? s2 == null :
      ignoreCase ? s1.equalsIgnoreCase(s2) : s1.equals(s2);
  }

  /**
   * If this is an absolute URI, returns the scheme component;
   * <code>null</code> otherwise.
   */
  public String scheme()
  {
    return scheme;
  }

  /**
   * If this is a non-hierarchical URI, returns the opaque part component;
   * <code>null</code> otherwise.
   */
  public String opaquePart()
  {
    return isHierarchical() ? null : authority;
  }

  /**
   * If this is a hierarchical URI with an authority component, returns it;
   * <code>null</code> otherwise.
   */
  public String authority()
  {
    return isHierarchical() ? authority : null;
  }

  /**
   * If this is a hierarchical URI with an authority component that has a
   * user info portion, returns it; <code>null</code> otherwise.
   */
  public String userInfo()
  { 
    if (!hasAuthority()) return null;
   
    int i = authority.indexOf(USER_INFO_SEPARATOR);
    return i < 0 ? null : authority.substring(0, i);
  }

  /**
   * If this is a hierarchical URI with an authority component that has a
   * host portion, returns it; <code>null</code> otherwise.
   */
  public String host()
  {
    if (!hasAuthority()) return null;
    
    int i = authority.indexOf(USER_INFO_SEPARATOR);
    int j = authority.indexOf(PORT_SEPARATOR);
    return j < 0 ? authority.substring(i + 1) : authority.substring(i + 1, j);
  }

  /**
   * If this is a hierarchical URI with an authority component that has a
   * port portion, returns it; <code>null</code> otherwise.
   */
  public String port()
  {
    if (!hasAuthority()) return null;

    int i = authority.indexOf(PORT_SEPARATOR);
    return i < 0 ? null : authority.substring(i + 1);
  }

  /**
   * If this is a hierarchical URI with a device component, returns it;
   * <code>null</code> otherwise.
   */
  public String device()
  {
    return device;
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
    return (String[])segments.clone();
  }

  /**
   * Returns an unmodifiable list containing the same segments as the array
   * returned by {@link #segments}.
   */
  public List segmentsList()
  {
    return Collections.unmodifiableList(Arrays.asList(segments));
  }

  /**
   * Returns the number of elements in the segment array that would be
   * returned by {@link #segments}.
   */
  public int segmentCount()
  {
    return segments.length;
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
    return segments[i];
  }

  /**
   * Returns the last segment in the segment array, or <code>null</code>.
   */
  public String lastSegment()
  {
    int len = segments.length;
    if (len == 0) return null;
    return segments[len - 1];
  }

  /**
   * If this is a hierarchical URI with a path, returns a string
   * representation of the path; <code>null</code> otherwise.  The path
   * consists of a leading segment separator character (a slash), if the
   * path is absolute, followed by the slash-separated path segments.  If
   * this URI has a separate <a href="#device_explaination">device
   * component</a>, it is <em>not</em> included in the path.
   */
  public String path()
  {
    if (!hasPath()) return null;

    StringBuffer result = new StringBuffer();
    if (hasAbsolutePath()) result.append(SEGMENT_SEPARATOR);

    for (int i = 0, len = segments.length; i < len; i++)
    {
      if (i != 0) result.append(SEGMENT_SEPARATOR);
      result.append(segments[i]);
    }
    return result.toString();
  }

  /**
   * If this is a hierarchical URI with a path, returns a string
   * representation of the path, including the authority and the 
   * <a href="#device_explaination">device component</a>; 
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
   * <p>For a <a href="#jar_explaination">JAR-scheme URI</a>, it's just:
   * <pre>
   *   authority/pathSegment1/pathSegment2...</pre>
   */
  public String devicePath()
  {
    if (!hasPath()) return null;

    StringBuffer result = new StringBuffer();

    if (hasAuthority())
    {
      if (!isJar()) result.append(AUTHORITY_SEPARATOR);
      result.append(authority);

      if (hasDevice()) result.append(SEGMENT_SEPARATOR);
    }

    if (hasDevice()) result.append(device);
    if (hasAbsolutePath()) result.append(SEGMENT_SEPARATOR);

    for (int i = 0, len = segments.length; i < len; i++)
    {
      if (i != 0) result.append(SEGMENT_SEPARATOR);
      result.append(segments[i]);
    }
    return result.toString();
  }

  /**
   * If this is a hierarchical URI with a query component, returns it;
   * <code>null</code> otherwise.
   */
  public String query()
  {
    return query;
  }


  /**
   * Returns the URI formed from this URI and the given query.
   *
   * @exception java.lang.IllegalArgumentException if
   * <code>query</code> is not a valid query (portion) according
   * to {@link #validQuery}.
   */
  public URI appendQuery(String query)
  {
    if (!validQuery(query))
    {
      throw new IllegalArgumentException(
        "invalid query portion: " + query);
    }
    return new URI(hierarchical, scheme, authority, device, absolutePath, segments, query, fragment); 
  }

  /**
   * If this URI has a non-null {@link #query}, returns the URI
   * formed by removing it; this URI unchanged, otherwise.
   */
  public URI trimQuery()
  {
    if (query == null)
    {
      return this;
    }
    else
    {
      return new URI(hierarchical, scheme, authority, device, absolutePath, segments, null, fragment); 
    }
  }

  /**
   * If this URI has a fragment component, returns it; <code>null</code>
   * otherwise.
   */
  public String fragment()
  {
    return fragment;
  }

  /**
   * Returns the URI formed from this URI and the given fragment.
   *
   * @exception java.lang.IllegalArgumentException if
   * <code>fragment</code> is not a valid fragment (portion) according
   * to {@link #validFragment}.
   */
  public URI appendFragment(String fragment)
  {
    if (!validFragment(fragment))
    {
      throw new IllegalArgumentException(
        "invalid fragment portion: " + fragment);
    }
    URI result = new URI(hierarchical, scheme, authority, device, absolutePath, segments, query, fragment); 
    result.cachedTrimFragment = this;
    return result;
  }

  /**
   * If this URI has a non-null {@link #fragment}, returns the URI
   * formed by removing it; this URI unchanged, otherwise.
   */
  public URI trimFragment()
  {
    if (fragment == null)
    {
      return this;
    }
    else if (cachedTrimFragment == null)
    {
      cachedTrimFragment = new URI(hierarchical, scheme, authority, device, absolutePath, segments, query, null); 
    }

    return cachedTrimFragment;
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
   * discarded, please use the {@link #resolve(URI, boolean)} method.
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
   * <p>During resultion, empty segments, self references ("."), and parent
   * references ("..") are interpreted, so that they can be removed from the
   * path.  Step 6(g) gives a choice of how to handle the case where parent
   * references point to a path above the root: the offending segments can
   * be preserved or discarded.  This method can do either.
   *
   * @param preserveRootParent <code>true</code> if segments refering to the
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

    // an absolute URI needs no resolving
    if (!isRelative()) return this;

    // note: isRelative() -> hierarchical

    String newAuthority = authority;
    String newDevice = device;
    boolean newAbsolutePath = absolutePath;
    String[] newSegments = segments;
    String newQuery = query;
    // note: it's okay for two URIs to share a segments array, since
    // neither will ever modify it
    
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
          newSegments = base.segments();
          newQuery = base.query();
        }
        else if (hasRelativePath())
        {
          // relative path: merge with base and keep query (note: if the
          // base has no path and this a non-empty relative path, there is
          // an implied root in the resulting path) 
          newAbsolutePath = base.hasAbsolutePath() || !hasEmptyPath();
          newSegments = newAbsolutePath ? mergePath(base, preserveRootParents)
            : NO_SEGMENTS;
        }
        // else absolute path: keep it and query
      }
      // else keep device, path, and query
    }
    // else keep authority, device, path, and query
    
    // always keep fragment, even if null, and use scheme from base;
    // no validation needed since all components are from existing URIs
    return new URI(true, base.scheme(), newAuthority, newDevice,
                   newAbsolutePath, newSegments, newQuery, fragment);
  }

  // Merges this URI's relative path with the base non-relative path.  If
  // base has no path, treat it as the root absolute path, unless this has
  // no path either.
  private String[] mergePath(URI base, boolean preserveRootParents)
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
    // (i.e. skip trailing separator and anything following it), and of
    // relative path
    for (int i = 0; i < baseSegmentCount - 1; i++)
    {
      sp = accumulate(stack, sp, base.segment(i), preserveRootParents);
    }

    for (int i = 0; i < segmentCount; i++)
    {
      sp = accumulate(stack, sp, segments[i], preserveRootParents);
    }

    // if the relative path is empty or ends in an empty segment, a parent 
    // reference, or a self referenfce, add a trailing separator to a
    // non-empty path
    if (sp > 0 &&  (segmentCount == 0 ||
                    SEGMENT_EMPTY.equals(segments[segmentCount - 1]) ||
                    SEGMENT_PARENT.equals(segments[segmentCount - 1]) ||
                    SEGMENT_SELF.equals(segments[segmentCount - 1])))
    {
      stack[sp++] = SEGMENT_EMPTY;
    }

    // return a correctly sized result
    String[] result = new String[sp];
    System.arraycopy(stack, 0, result, 0, sp);
    return result;
  }

  // Adds a segment to a stack, skipping empty segments and self references,
  // and interpreting parent references.
  private static int accumulate(String[] stack, int sp, String segment,
                                boolean preserveRootParents)
  {
    if (SEGMENT_PARENT.equals(segment))
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
        if (SEGMENT_PARENT.equals(stack[sp - 1])) stack[sp++] = segment;
        else sp--;
      }
    }
    else if (!SEGMENT_EMPTY.equals(segment) && !SEGMENT_SELF.equals(segment))
    {
      // skip empty segments and self references; push everything else
      stack[sp++] = segment;
    }
    return sp;
  }

  /**
   * Finds the shortest relative or, if necessary, the absolute URI that,
   * when resolved against the given <code>base</code> absolute hierarchical
   * URI using {@link #resolve(URI)}, will yield this absolute URI.  
   *
   * @exception java.lang.IllegalArgumentException if <code>base</code> is
   * non-hierarchical or is relative.
   * @exception java.lang.IllegalStateException if <code>this</code> is
   * relative.
   */
  public URI deresolve(URI base)
  {
    return deresolve(base, true, false, true);
  }

  /**
   * Finds an absolute URI that, when resolved against the given
   * <code>base</code> absolute hierarchical URI using {@link #resolve(URI,
   * boolean)}, will yield this absolute URI.
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
   *
   * @exception java.lang.IllegalArgumentException if <code>base</code> is
   * non-hierarchical or is relative.
   * @exception java.lang.IllegalStateException if <code>this</code> is
   * relative.
   */
  public URI deresolve(URI base, boolean preserveRootParents,
                       boolean anyRelPath, boolean shorterRelPath)
  {
    if (!base.isHierarchical() || base.isRelative())
    {
      throw new IllegalArgumentException(
        "deresolve against non-hierarchical or relative base");
    }
    if (isRelative())
    {
      throw new IllegalStateException("deresolve relative URI");
    }

    // note: these assertions imply that neither this nor the base URI has a
    // relative path; thus, both have either an absolute path or no path
    
    // different scheme: need complete, absolute URI
    if (!scheme.equalsIgnoreCase(base.scheme())) return this;

    // since base must be hierarchical, and since a non-hierarchical URI
    // must have both scheme and opaque part, the complete absolute URI is
    // needed to resolve to a non-hierarchical URI
    if (!isHierarchical()) return this;

    String newAuthority = authority;
    String newDevice = device;
    boolean newAbsolutePath = absolutePath;
    String[] newSegments = segments;
    String newQuery = query;

    if (equals(authority, base.authority()) &&
        (hasDevice() || hasPath() || (!base.hasDevice() && !base.hasPath())))
    {
      // matching authorities and no device or path removal
      newAuthority = null;

      if (equals(device, base.device()) && (hasPath() || !base.hasPath()))
      {
        // matching devices and no path removal
        newDevice = null;

        // exception if (!hasPath() && base.hasPath())

        if (!anyRelPath && !shorterRelPath)
        {
          // user rejects a relative path: keep absolute or no path
        }
        else if (hasPath() == base.hasPath() && segmentsEqual(base) &&
                 equals(query, base.query()))
        {
          // current document reference: keep no path or query
          newAbsolutePath = false;
          newSegments = NO_SEGMENTS;
          newQuery = null;
        }
        else if (!hasPath() && !base.hasPath())
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
      // else keep device, path, and query
    }
    // else keep authority, device, path, and query

    // always include fragment, even if null;
    // no validation needed since all components are from existing URIs
    return new URI(true, null, newAuthority, newDevice, newAbsolutePath,
                   newSegments, newQuery, fragment);
  }

  // Returns true if the non-relative path includes segments that would be
  // collapsed when resolving; false otherwise.  If preserveRootParents is
  // true, collapsable segments include any empty segments, except for the
  // last segment, as well as and parent and self references.  If
  // preserveRootsParents is false, parent references are not collapsable if
  // they are the first segment or preceeded only by other parent
  // references.
  private boolean hasCollapsableSegments(boolean preserveRootParents)
  {
    if (hasRelativePath())
    {
      throw new IllegalStateException("test collapsability of relative path");
    }

    for (int i = 0, len = segments.length; i < len; i++)
    {
      String segment = segments[i];
      if ((i < len - 1 && SEGMENT_EMPTY.equals(segment)) ||
          SEGMENT_SELF.equals(segment) ||
          SEGMENT_PARENT.equals(segment) && (
            !preserveRootParents || (
              i != 0 && !SEGMENT_PARENT.equals(segments[i - 1]))))
      {
        return true;
      }
    }
    return false;
  }

  // Returns the shortest relative path between the the non-relative path of
  // the given base and this absolute path.  If the base has no path, it is
  // treated as the root absolute path.
  private String[] findRelativePath(URI base, boolean preserveRootParents)
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
    // last segment removed, all preceeding segments can be considered non-
    // empty and followed by a separator, while the last segment of endPath
    // will either be non-empty and not followed by a separator, or just empty
    for (int count = startCount < endCount ? startCount : endCount - 1;
         diff < count && startPath[diff].equals(endPath[diff]); diff++);

    int upCount = startCount - diff;
    int downCount = endCount - diff;

    // a single separator, possibly preceeded by some parent reference
    // segments, is redundant
    if (downCount == 1 && SEGMENT_EMPTY.equals(endPath[endCount - 1]))
    {
      downCount = 0;
    }

    // an empty path needs to be replaced by a single "." if there is no
    // query, to distinguish it from a current document reference
    if (upCount + downCount == 0)
    {
      if (query == null) return new String[] { SEGMENT_SELF };
      return NO_SEGMENTS;
    }

    // return a correctly sized result
    String[] result = new String[upCount + downCount];
    Arrays.fill(result, 0, upCount, SEGMENT_PARENT);
    System.arraycopy(endPath, diff, result, upCount, downCount);
    return result;
  }

  // Collapses non-ending empty segments, parent references, and self
  // references in a non-relative path, returning the same path that would
  // be produced from the base hierarchical URI as part of a resolve.
  String[] collapseSegments(boolean preserveRootParents)
  {
    if (hasRelativePath())
    {
      throw new IllegalStateException("collapse relative path");
    }

    if (!hasCollapsableSegments(preserveRootParents)) return segments();

    // use a stack to accumulate segments
    int segmentCount = segments.length;
    String[] stack = new String[segmentCount];
    int sp = 0;

    for (int i = 0; i < segmentCount; i++)
    {
      sp = accumulate(stack, sp, segments[i], preserveRootParents);
    }

    // if the path is non-empty and originally ended in an empty segment, a
    // parent reference, or a self reference, add a trailing separator
    if (sp > 0 && (SEGMENT_EMPTY.equals(segments[segmentCount - 1]) ||
                   SEGMENT_PARENT.equals(segments[segmentCount - 1]) ||
                   SEGMENT_SELF.equals(segments[segmentCount - 1])))
    {                   
      stack[sp++] = SEGMENT_EMPTY;
    }

    // return a correctly sized result
    String[] result = new String[sp];
    System.arraycopy(stack, 0, result, 0, sp);
    return result;
  }

  /**
   * Returns the string representation of this URI.  For a generic,
   * non-hierarchical URI, this looks like:
   * <pre>
   *   scheme:opaquePart#fragment</pre>
   * 
   * <p>For a hierarchical URI, it looks like:
   * <pre>
   *   scheme://authority/device/pathSegment1/pathSegment2...?query#fragment</pre>
   *
   * <p>For a <a href="#jar_explaination">JAR-scheme URI</a>, it's just:
   * <pre>
   *   scheme:authority/pathSegment1/pathSegment2...?query#fragment</pre>
   * <p>Of course, absent components and their separators will be omitted.
   */
  public String toString()
  {
    if (cachedToString == null)
    {
      StringBuffer result = new StringBuffer();
      if (!isRelative())
      {
        result.append(scheme);
        result.append(SCHEME_SEPARATOR);
      }

      if (isHierarchical())
      {
        if (hasAuthority())
        {
          if (!isJar()) result.append(AUTHORITY_SEPARATOR);
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
      }
      else
      {
        result.append(authority);
      }

      if (hasFragment())
      {
        result.append(FRAGMENT_SEPARATOR);
        result.append(fragment);
      }
      cachedToString = result.toString();
    }
    return cachedToString;
  }

  // Returns a string representation of this URI for debugging, explicitly
  // showing each of the components.
  String toString(boolean includeSimpleForm)
  {
    StringBuffer result = new StringBuffer();
    if (includeSimpleForm) result.append(toString());
    result.append("\n hierarchical: ");
    result.append(hierarchical);
    result.append("\n       scheme: ");
    result.append(scheme);
    result.append("\n    authority: ");
    result.append(authority);
    result.append("\n       device: ");
    result.append(device);
    result.append("\n absolutePath: ");
    result.append(absolutePath);
    result.append("\n     segments: ");
    if (segments.length == 0) result.append("<empty>");
    for (int i = 0, len = segments.length; i < len; i++)
    {
      if (i > 0) result.append("\n               ");
      result.append(segments[i]);
    }
    result.append("\n        query: ");
    result.append(query);
    result.append("\n     fragment: ");
    result.append(fragment);
    return result.toString();
  }

  /**
   * If this URI may refer directly to a locally accessible file, as
   * determined by {@link #isFile}, returns the URI formatted as a
   * pathname to that file; null otherwise.
   *
   * <p>If there is no authority, the format of this string is:
   * <pre>
   *   device/pathSegment1/pathSegment2...</pre>
   *
   * <p>If there is an authority, it is:
   * <pre>
   *   //authority/device/pathSegment1/pathSegment2...</pre>
   * 
   * <p>However, the character used as a separator is system-dependant and
   * obtained from {@link java.io.File#separatorChar}.
   */
  public String toFileString()
  {
    if (!isFile()) return null;

    StringBuffer result = new StringBuffer();
    char separator = File.separatorChar;

    if (hasAuthority())
    {
      result.append(separator);
      result.append(separator);
      result.append(authority);

      if (hasDevice()) result.append(separator);
    }

    if (hasDevice()) result.append(device);
    if (hasAbsolutePath()) result.append(separator);

    for (int i = 0, len = segments.length; i < len; i++)
    {
      if (i != 0) result.append(separator);
      result.append(segments[i]);
    }

    for (int i = result.indexOf("%20"); i != -1; i = result.indexOf("%20", i))
    {
      result.replace(i, i + 3, " ");
    }

    return result.toString();
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

    if (!isHierarchical()) return this;

    // absolute path or no path -> absolute path
    boolean newAbsolutePath = !hasRelativePath();

    int len = segments.length;
    String[] newSegments = new String[len + 1];
    System.arraycopy(segments, 0, newSegments, 0, len);
    newSegments[len] = segment;

    return new URI(true, scheme, authority, device, newAbsolutePath,
                   newSegments, query, fragment);
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
      String s = segments == null ? "invalid segments: " + segments :
        "invalid segment: " + firstInvalidSegment(segments);
      throw new IllegalArgumentException(s);
    }

    if (!isHierarchical()) return this;

    // absolute path or no path -> absolute path
    boolean newAbsolutePath = !hasRelativePath(); 

    int len = this.segments.length;
    int segmentsCount = segments.length;
    String[] newSegments = new String[len + segmentsCount];
    System.arraycopy(this.segments, 0, newSegments, 0, len);
    System.arraycopy(segments, 0, newSegments, len, segmentsCount);
    
    return new URI(true, scheme, authority, device, newAbsolutePath,
                   newSegments, query, fragment);
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
    if (!isHierarchical() || i < 1) return this;

    String[] newSegments = NO_SEGMENTS;
    int len = segments.length - i;
    if (len > 0)
    {
      newSegments = new String[len];
      System.arraycopy(segments, 0, newSegments, 0, len);
    }
    return new URI(true, scheme, authority, device, absolutePath,
                   newSegments, query, fragment);
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
    return segments.length > 0 && 
      SEGMENT_EMPTY.equals(segments[segments.length - 1]);
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
    int len = segments.length;
    if (len == 0) return null;

    String lastSegment = segments[len - 1];
    int i = lastSegment.lastIndexOf(FILE_EXTENSION_SEPARATOR);
    return i < 0 ? null : lastSegment.substring(i + 1);
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
      throw new IllegalArgumentException(
        "invalid segment portion: " + fileExtension);
    }

    int len = segments.length;
    if (len == 0) return this;

    String lastSegment = segments[len - 1];
    if (SEGMENT_EMPTY.equals(lastSegment)) return this;
    StringBuffer newLastSegment = new StringBuffer(lastSegment);
    newLastSegment.append(FILE_EXTENSION_SEPARATOR);
    newLastSegment.append(fileExtension);

    String[] newSegments = new String[len];
    System.arraycopy(segments, 0, newSegments, 0, len - 1);
    newSegments[len - 1] = newLastSegment.toString();
    
    // note: segments.length > 0 -> hierarchical
    return new URI(true, scheme, authority, device, absolutePath,
                   newSegments, query, fragment); 
  }

  /**
   * If this URI has a non-null {@link #fileExtension}, returns the URI
   * formed by removing it; this URI unchanged, otherwise.
   */
  public URI trimFileExtension()
  {
    int len = segments.length;
    if (len == 0) return this;

    String lastSegment = segments[len - 1];
    int i = lastSegment.lastIndexOf(FILE_EXTENSION_SEPARATOR);
    if (i < 0) return this;

    String newLastSegment = lastSegment.substring(0, i);
    String[] newSegments = new String[len];
    System.arraycopy(segments, 0, newSegments, 0, len - 1);
    newSegments[len - 1] = newLastSegment;

    // note: segments.length > 0 -> hierarchical
    return new URI(true, scheme, authority, device, absolutePath,
                   newSegments, query, fragment); 
  }

  /**
   * Returns <code>true</code> if this is a hierarchical URI that ends in a
   * slash; that is, it has a trailing path separator or is the root
   * absolute path, and has no query and no fragment; <code>false</code>
   * is returned otherwise.
   */
  public boolean isPrefix()
  {
    return hierarchical && query == null && fragment == null &&
      (hasTrailingPathSeparator() || (absolutePath && segments.length == 0));
  }

  /**
   * If this is a hierarchical URI reference and <code>oldPrefix</code> is a
   * prefix of it, this returns the URI formed by replacing it by
   * <code>newPrefix</code>; <code>null</code> otherwise.
   *
   * <p>In order to be a prefix, the <code>oldPrefix</code>'s
   * {@link #isPrefix} must return <code>true</code>, and it must match this
   * URI's scheme, authority, and device.  Also, the paths must match, up to
   * prefix's end.
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

    // Get what's left of the segments after trimming the prefix.
    String[] tailSegments = getTailSegments(oldPrefix);
    if (tailSegments == null) return null;

    // If the new prefix has segments, it is not the root absolute path,
    // and we need to drop the trailing empty segment and append the tail
    // segments.
    String[] mergedSegments = tailSegments;
    if (newPrefix.segmentCount() != 0)
    {
      int segmentsToKeep = newPrefix.segmentCount() - 1;
      mergedSegments = new String[segmentsToKeep + tailSegments.length];
      System.arraycopy(newPrefix.segments(), 0, mergedSegments, 0,
                       segmentsToKeep);

      if (tailSegments.length != 0)
      {
        System.arraycopy(tailSegments, 0, mergedSegments, segmentsToKeep,
                         tailSegments.length);
      }
    }

    // no validation needed since all components are from existing URIs
    return new URI(true, newPrefix.scheme(), newPrefix.authority(),
                   newPrefix.device(), newPrefix.hasAbsolutePath(),
                   mergedSegments, query, fragment);
  }

  // If this is a hierarchical URI reference and prefix is a prefix of it,
  // returns the portion of the path remaining after that prefix has been
  // trimmed; null otherwise.
  private String[] getTailSegments(URI prefix)
  {
    if (!prefix.isPrefix())
    {
      throw new IllegalArgumentException("non-prefix trim");
    }

    // Don't even consider it unless this is hierarchical and has scheme,
    // authority, device and path absoluteness equal to those of the prefix.
    if (!hierarchical ||
        !equals(scheme, prefix.scheme(), true) ||
        !equals(authority, prefix.authority()) ||
        !equals(device, prefix.device()) ||
        absolutePath != prefix.hasAbsolutePath())
    {
      return null;
    }

    // If the prefix has no segments, then it is the root absolute path, and
    // we know this is an absolute path, too.
    if (prefix.segmentCount() == 0) return segments;

    // This must have no fewer segments than the prefix.  Since the prefix
    // is not the root absolute path, its last segment is empty; all others
    // must match.
    int i = 0;
    int segmentsToCompare = prefix.segmentCount() - 1;
    if (segments.length <= segmentsToCompare) return null;

    for (; i < segmentsToCompare; i++)
    {
      if (!segments[i].equals(prefix.segment(i))) return null;
    }

    // The prefix really is a prefix of this.  If this has just one more,
    // empty segment, the paths are the same.
    if (i == segments.length - 1 && SEGMENT_EMPTY.equals(segments[i]))
    {
      return NO_SEGMENTS;
    }
    
    // Otherwise, the path needs only the remaining segments.
    String[] newSegments = new String[segments.length - i];
    System.arraycopy(segments, i, newSegments, 0, newSegments.length);
    return newSegments;
  }
}
