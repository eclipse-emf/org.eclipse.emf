/**
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.core.common.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;

public class URITest extends TestCase
{
  public URITest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite suite = new TestSuite("URITest");
    suite.addTest(new URITest("testParse"));
    suite.addTest(new URITest("testResolve"));
    suite.addTest(new URITest("testDeresolve"));
    suite.addTest(new URITest("testAuthorityParse"));
    suite.addTest(new URITest("testJARParse"));
    suite.addTest(new URITest("testFragmentAppendAndTrim"));
    suite.addTest(new URITest("testEncodeAndDecode"));
    suite.addTest(new URITest("testPlatformURI"));
    return suite;
  }

  protected static final String URN = "mailto:me@yahoo.com";

  protected static final String[] ABSOLUTE_URLS = {
    "file:/",
    "file:/bar",
    "file:/bar/",
    "file:/bar/baz",
    "file:/bar/baz/",
    "file:/c:",
    "file:/c:/",
    "file:/c:/bar",
    "file:/c:/bar/",
    "file:/c:/bar/baz",
    "file:/c:/bar/baz/",
    "file://foo",
    "file://foo/",
    "file://foo/bar",
    "file://foo/bar/",
    "file://foo/bar/baz",
    "file://foo/bar/baz/",
    "file://foo/c:",
    "file://foo/c:/",
    "file://foo/c:/bar",
    "file://foo/c:/bar/",
    "file://foo/c:/bar/baz",
    "file://foo/c:/bar/baz/"
  };

  protected static final String[] RELATIVE_URLS = {
    "",
    "nif",
    "nif/",
    "nif/phi",
    "nif/phi/",
    "/",
    "/nif",
    "/nif/",
    "/nif/phi",
    "/nif/phi/",
    "/d:",
    "/d:/nif",
    "/d:/nif/",
    "/d:/nif/phi",
    "/d:/nif/phi/",
    "//sig",
    "//sig/",
    "//sig/nif",
    "//sig/nif/",
    "//sig/nif/phi",
    "//sig/nif/phi/",
    "//sig/d:",
    "//sig/d:/",
    "//sig/d:/nif",
    "//sig/d:/nif/",
    "//sig/d:/nif/phi",
    "//sig/d:/nif/phi/"
  };

  protected static final String[] QUERIES = { "", "?q=huh" };
  
  protected static final String[] FRAGMENTS = { "", "#toc" };

  protected static final String BASE_URI = "http://a/b/c/d;p?q";

  protected static final String[] UNRESOLVED_URIS = {
    "g:h",
    "g",
    "./g",
    "g/",
    "/g",
    "//g",
    "?y",
    "g?y",
    "#s",
    "g#s",
    "g?y#s",
    ";x",
    "g;x",
    "g;x?y#s",
    ".",
    "./",
    "..",
    "../",
    "../g",
    "../..",
    "../../",
    "../../g",
    "",
    "/./g",
    "/../g",
    "g.",
    ".g",
    "g..",
    "..g",
    "./../g",
    "./g/.",
    "g/./h",
    "g/../h",
    "g;x=1/./y",
    "g;x=1/../y",
    "g?y/./x",
    "g?y/../x",
    "g#s/./x",
    "g#s/../x",
    "http:g"
  };
    
  protected static final String[] RESOLVED_URIS = {
    "g:h",
    "http://a/b/c/g",
    "http://a/b/c/g",
    "http://a/b/c/g/",
    "http://a/g",
    "http://g",
    "http://a/b/c/?y",
    "http://a/b/c/g?y",
    "http://a/b/c/d;p?q#s",
    "http://a/b/c/g#s",
    "http://a/b/c/g?y#s",
    "http://a/b/c/;x",
    "http://a/b/c/g;x",
    "http://a/b/c/g;x?y#s",
    "http://a/b/c/",
    "http://a/b/c/",
    "http://a/b/",
    "http://a/b/",
    "http://a/b/g",
    "http://a/",
    "http://a/",
    "http://a/g",
    "http://a/b/c/d;p?q",
    "http://a/./g",
    "http://a/../g",
    "http://a/b/c/g.",
    "http://a/b/c/.g",
    "http://a/b/c/g..",
    "http://a/b/c/..g",
    "http://a/b/g",
    "http://a/b/c/g/",
    "http://a/b/c/g/h",
    "http://a/b/c/h",
    "http://a/b/c/g;x=1/y",
    "http://a/b/c/y",
    "http://a/b/c/g?y/./x",
    "http://a/b/c/g?y/../x",
    "http://a/b/c/g#s/./x",
    "http://a/b/c/g#s/../x",
    "http:g"
  };

  protected static final String[] UNRESOLVED_ABOVE_ROOT_URIS = { "../../../g", "../../../../g" };

  protected static final String[] RESOLVED_PRESERVE_ABOVE_ROOT_URIS = { "http://a/../g", "http://a/../../g" };

  protected static final String[] RESOLVED_NO_PRESERVE_ABOVE_ROOT_URIS = { "http://a/g", "http://a/g" };

  protected static final String[] NON_CANONICAL_UNRESOLVED_URIS = {
    "./../g",
    "./g/.",
    "g/./h",
    "g/../h",
    "g;x=1/./y",
    "g;x=1/../y"
  };

  protected static final String[] NON_CANONICAL_PRESERVE_ABOVE_ROOT_UNRESOLVED_URIS = { };
  
  protected static final String[] NON_CANONICAL_NO_PRESERVE_ABOVE_ROOT_UNRESOLVED_URIS = { "../../../g", "../../../../g" };    
  
  protected static final String[] AUTHORITY_PARSE_URIS = {
    "not/here",
    "//myhost/",
    "//me@myhost/",
    "//myhost:1234/",
    "//me@myhost:1234",
    "//me@:1234",
    "//@:"
  };

  protected static final String[] AUTHORITY_PARSE_USER_INFOS = {
    null,
    null,
    "me",
    null,
    "me",
    "me",
    ""
  };

  protected static final String[] AUTHORITY_PARSE_HOSTS = {
    null,
    "myhost",
    "myhost",
    "myhost",
    "myhost",
    "",
    ""
  };

  protected static final String[] AUTHORITY_PARSE_PORTS = {
    null,
    null,
    null,
    "1234",
    "1234",
    "1234",
    ""
  };

  protected static final String[] JAR_URIS = {
    "jar:file:/home/dave/myapp.jar!/",
    "jar:file:/dave/myapp.jar!/schema.xsd",
    "jar:file:/dave/myapp.jar!/support/schema.xsd",
    "jar:file:/dave/myapp.jar!/support/xml/schema.xsd",
    "JAR:http://www.eclipse.org/myapp.jar!/schema.xsd",
    "jar:http://www.eclipse.org/jar-server?some-jar!/support/xml/schema.xsd",
    "jar:http://www.eclipse.org/jar-server?some-jar!/support/xml/schema?myquery#top",
    "jar:dave/myapp.jar!/schema.xsd",
    "jar:/home/dave/myapp.jar!/schema.xsd",
    "zip://capilano/home/dave/myapp.jar!/schema.xsd",
  };

  protected static final String[] BAD_JAR_URIS = {
    "jar:",
    "jar:file:/dave/myapp.jar",
    "jar:file:/dave/myapp.jar!",
    "jar:http://www.eclipse.org/jar-server?some-jar#foo!/schema?myquery"
  };

  protected static final String[] UNENCODED_URIS = {
    "http://www.eclipse.org/foo",
    "http://server#1.eclipse.org/foo bar/baz#toc",
    "myscheme:my name",
    "file:/C:/My Documents/me/50%+1.txt",
    "My Documents/me/50%50.txt"
  };

  protected static final String[] ENCODED_URIS = {
    "http://www.eclipse.org/foo",
    "http://server%231.eclipse.org/foo%20bar/baz#toc",
    "myscheme:my%20name",
    "file:/C:/My%20Documents/me/50%25+1.txt",
    "My%20Documents/me/50%2550.txt"
  };

  protected static final String[] ENCODED_URIS_IGNORE_ESCAPED = {
    "http://www.eclipse.org/foo",
    "http://server%231.eclipse.org/foo%20bar/baz#toc",
    "myscheme:my%20name",
    "file:/C:/My%20Documents/me/50%25+1.txt",
    "My%20Documents/me/50%50.txt"
  };

  protected static final String[] UNENCODED_PLATFORM_PATHS = {
    "/project/myfile.txt",
    "My Project #1/My File.txt",
    "are you there?"
  };

  protected static final String[] ENCODED_PLATFORM_PATH_URIS = {
    "platform:/resource/project/myfile.txt",
    "platform:/resource/My%20Project%20%231/My%20File.txt",
    "platform:/resource/are%20you%20there%3F"    
  };
  
  protected String[] getURNs()
  {
    return new String[] { URN + FRAGMENTS[0], URN + FRAGMENTS[1] };
  }

  protected String[] getAbsoluteURLs()
  {
    String[] result = new String[ABSOLUTE_URLS.length * QUERIES.length * FRAGMENTS.length];
    for (int i = 0, x = 0; x < FRAGMENTS.length; x++)
      for (int y = 0; y < QUERIES.length; y++)
        for (int z = 0; z < ABSOLUTE_URLS.length; z++)
          result[i++] = ABSOLUTE_URLS[z] + QUERIES[y] + FRAGMENTS[x];

    return result;
  }

  protected String[] getRelativeURLs()
  {
    String[] result = new String[RELATIVE_URLS.length * QUERIES.length * FRAGMENTS.length];
    for (int i = 0, x = 0; x < FRAGMENTS.length; x++)
      for (int y = 0; y < QUERIES.length; y++)
        for (int z = 0; z < RELATIVE_URLS.length; z++)
          result[i++] = RELATIVE_URLS[z] + QUERIES[y] + FRAGMENTS[x];

    return result;
  }

  protected String[] getAllURLs()
  {
    String[] result = new String[(ABSOLUTE_URLS.length + RELATIVE_URLS.length) * QUERIES.length * FRAGMENTS.length];
    int i = 0;

    for (int x = 0; x < FRAGMENTS.length; x++)
      for (int y = 0; y < QUERIES.length; y++)
        for (int z = 0; z < ABSOLUTE_URLS.length; z++)
          result[i++] = ABSOLUTE_URLS[z] + QUERIES[y] + FRAGMENTS[x];

    for (int x = 0; x < FRAGMENTS.length; x++)
      for (int y = 0; y < QUERIES.length; y++)
        for (int z = 0; z < RELATIVE_URLS.length; z++)
          result[i++] = RELATIVE_URLS[z] + QUERIES[y] + FRAGMENTS[x];

    return result;
  }

  protected String[] getUnresolvedURIs()
  {
    String[] result = new String[UNRESOLVED_URIS.length + UNRESOLVED_ABOVE_ROOT_URIS.length];
    
    System.arraycopy(UNRESOLVED_URIS, 0, result, 0, UNRESOLVED_URIS.length);
    System.arraycopy(UNRESOLVED_ABOVE_ROOT_URIS, 0, result, RESOLVED_URIS.length, UNRESOLVED_ABOVE_ROOT_URIS.length);    
    return result;
  }
  
  protected String[] getResolvedURIs(boolean preserve)
  {
    String[] aboveRoot = preserve ? RESOLVED_PRESERVE_ABOVE_ROOT_URIS : RESOLVED_NO_PRESERVE_ABOVE_ROOT_URIS;
    String[] result = new String[RESOLVED_URIS.length + aboveRoot.length];
    
    System.arraycopy(RESOLVED_URIS, 0, result, 0, RESOLVED_URIS.length);
    System.arraycopy(aboveRoot, 0, result, RESOLVED_URIS.length, aboveRoot.length);    
    return result;
  }
  
  protected String[] getNonCanonicalUnresolvedURIs(boolean preserve)
  {
    String[] aboveRoot = preserve ? NON_CANONICAL_PRESERVE_ABOVE_ROOT_UNRESOLVED_URIS : NON_CANONICAL_NO_PRESERVE_ABOVE_ROOT_UNRESOLVED_URIS;
    String[] result = new String[NON_CANONICAL_UNRESOLVED_URIS.length + aboveRoot.length];
    
    System.arraycopy(NON_CANONICAL_UNRESOLVED_URIS, 0, result, 0, NON_CANONICAL_UNRESOLVED_URIS.length);
    System.arraycopy(aboveRoot, 0, result, NON_CANONICAL_UNRESOLVED_URIS.length, aboveRoot.length);    
    return result;
  }

  /**
   * Parses URIs and converts them back to strings, comparing with the originals.
   *
   */
  public void testParse()
  {
    String[] uriStrings = getAllURLs();
    for (int i = 0, len = uriStrings.length; i < len; i++)
    {
      String s = uriStrings[i];
      URI u = URI.createURI(s);
      assertEquals("Bad URL parse", s, u.toString());
    }

    uriStrings = getURNs();
    for (int i = 0, len = uriStrings.length; i < len; i++)
    {
      String s = uriStrings[i];
      URI u = URI.createURI(s);
      assertEquals("Bad URN parse", s, u.toString());
    }
  }    

  /**
   * Resolves URIs against a base, comparing with the known correct results.
   * This tests both preserving and not preserving path segments above root.
   */
  public void testResolve()
  {
    URI base = URI.createURI(BASE_URI);

    for (int i = 0; i < 2; i++)
    {
      boolean preserve = i == 0;
      
      String[] uriStrings = getUnresolvedURIs();
      String[] resolvedStrings = getResolvedURIs(preserve);

      for (int j = 0, len = uriStrings.length; j < len; j++)
      {
        URI uri = URI.createURI(uriStrings[j]);
        URI resolved = URI.createURI(resolvedStrings[j]);
        URI myResolved = uri.resolve(base, preserve);
        assertEquals("Bad resolve: " + uri, resolved, myResolved);
      }
    }
  }

  /**
   * Deresolves URIs against a base, comparing with the known correct results.
   * This tests both preserving and no preserving path segments above roots, and skips cases where the unresolved URI
   * is non-canonical.
   */
  public void testDeresolve()
  {
    URI base = URI.createURI(BASE_URI);

    for (int i = 0; i < 2; i++)
    {
      boolean preserve = i == 0;

      String[] uriStrings = getResolvedURIs(preserve);
      String[] deresolvedStrings = getUnresolvedURIs();
      List<String> skipStrings = Arrays.asList(getNonCanonicalUnresolvedURIs(preserve));

      for (int j = 0, len = uriStrings.length; j < len; j++)
      {
        URI uri = URI.createURI(uriStrings[j]);
  
        if ((j > 0 && uriStrings[j].equals(uriStrings[j - 1])) ||
            skipStrings.contains(deresolvedStrings[j]))
          continue;
  
        URI deresolved = URI.createURI(deresolvedStrings[j]);
        URI myDeresolved = uri.deresolve(base, preserve, deresolved.hasRelativePath(), false);
        assertEquals("Bad deresolve: " + uri, deresolved, myDeresolved);
      }
    }
  }

  /**
   * Parses URIs and calls the authority sub-part accessors, comparing with known results. 
   */
  public void testAuthorityParse()
  {
    String[] uriStrings = AUTHORITY_PARSE_URIS;
    String[] userInfos = AUTHORITY_PARSE_USER_INFOS;
    String[] hosts = AUTHORITY_PARSE_HOSTS;
    String[] ports = AUTHORITY_PARSE_PORTS;
    
    for (int i = 0, len = uriStrings.length; i < len; i++)
    {
      URI uri = URI.createURI(uriStrings[i]);
      assertEquals("Bad user info parse: " + uriStrings[i], userInfos[i], uri.userInfo()); 
      assertEquals("Bad host parse: " + uriStrings[i], hosts[i], uri.host()); 
      assertEquals("Bad port parse: " + uriStrings[i], ports[i], uri.port()); 
    }
  }

  /*
   * Parses URIs with JAR scheme and converts them back to strings, comparing with the originals.  Parses invalid
   * JAR-scheme URIs, checking to ensure that the correct exceptions are thrown.
   */
  public void testJARParse()
  {
    String[] uriStrings = JAR_URIS;

    for (int i = 0, len = uriStrings.length; i < len; i++)
    {
      String s = uriStrings[i];
      URI u = URI.createURI(s);
      assertEquals("Bad JAR-scheme URI parse", s, u.toString());
    }

    uriStrings = BAD_JAR_URIS;

    for (int i = 0, len = uriStrings.length; i < len; i++)
    {
      String s = uriStrings[i];
      try
      {
        URI.createURI(s);
        fail("Parse of bad JAR-scheme URI failed to throw IllegalArgumentException: " + s);
      }
      catch (IllegalArgumentException e)
      {
        // Ignore
      }
    }
  }
  
  /**
   * Parses a URI with a fragment, appends a fragment to a URI, replaces that fragment with another, then trims the
   * three fragments, comparing the results to the base.
   */
  public void testFragmentAppendAndTrim()
  {
    String base = "http://download.eclipse.org/tools/emf/scripts/home.php";
    String fragment1 = "top";
    String fragment2 = "quicknav";
    String fragment3 = "over2";

    URI fragment1URI = URI.createURI(base + "#" + fragment1);
    assertEquals("Bad URI parse", base + "#" + fragment1, fragment1URI.toString());

    URI baseURI = URI.createURI(base);
    URI fragment2URI = baseURI.appendFragment(fragment2);
    assertEquals("Bad fragment append: " + fragment2, base + "#" + fragment2, fragment2URI.toString());

    URI fragment3URI = fragment2URI.appendFragment(fragment3);
    assertEquals("Bad fragment replace: " + fragment3, base + "#" + fragment3, fragment3URI.toString());

    URI trimmedFragment1URI = fragment1URI.trimFragment();
    assertEquals("Bad parsed fragment trim: " + fragment1URI, base, trimmedFragment1URI.toString());

    URI trimmedFragment2URI = fragment2URI.trimFragment();
    assertEquals("Bad appended fragment trim: " + fragment2URI, base, trimmedFragment2URI.toString());

    URI trimmedFragment3URI = fragment3URI.trimFragment();
    assertEquals("Bad replaced fragment trim: " + fragment3URI, base, trimmedFragment3URI.toString());
  }

  /**
   * Performs automatic encoding of general URIs and platform resource URIs, and decodes the former back, comparing the
   * result to known encoded versions.
   */
  public void testEncodeAndDecode()
  {
    String[] unencodedURIStrings = UNENCODED_URIS;
    String[] encodedURIStrings = ENCODED_URIS;

    for (int i = 0, len = unencodedURIStrings.length; i < len; i++)
    {
      String unencoded = unencodedURIStrings[i];
      URI encodedURI = URI.createURI(unencoded, false);
      assertEquals("Bad URI encode: " + unencoded, URI.createURI(encodedURIStrings[i]), encodedURI);
      assertEquals("Bad URI decode: " + encodedURI, unencoded, URI.decode(encodedURI.toString()));
    }

    encodedURIStrings = ENCODED_URIS_IGNORE_ESCAPED;

    for (int i = 0, len = unencodedURIStrings.length; i < len; i++)
    {
      String unencoded = unencodedURIStrings[i];
      URI encodedURI = URI.createURI(unencoded, true);
      assertEquals("Bad URI encode: " + unencoded, URI.createURI(encodedURIStrings[i]), encodedURI);
    }

    //As of Bugzilla 72731, this behaviour requires a system property to be set.
    //
    //String[] paths = UNENCODED_PLATFORM_PATHS;
    //encodedURIStrings = ENCODED_PLATFORM_PATH_URIS;
    //
    //for (int i = 0, len = paths.length; i < len; i++)
    //{
    //  String path = paths[i];
    //  URI uri = URI.createPlatformResourceURI(path);
    //  assertEquals("Bad platform resource encode: " + path, encodedURIStrings[i], uri.toString());
    //}

    // Bugzilla 116074
    String unencoded = "platform://resource/a#b/c#d#e";
    String encodedWithNoFragment = "platform://resource/a%23b/c%23d%23e";
    String encodedWithFragmentFirst = "platform://resource/a#b/c%23d%23e";
    String encodedWithFragmentLast = "platform://resource/a%23b/c%23d#e";

    assertEquals("Bad URI encode: " + unencoded, encodedWithNoFragment, URI.createURI(unencoded, false, URI.FRAGMENT_NONE).toString());
    assertEquals("Bad URI decode: " + encodedWithNoFragment, unencoded, URI.decode(encodedWithNoFragment.toString()));

    assertEquals("Bad URI encode: " + unencoded, encodedWithFragmentFirst, URI.createURI(unencoded, false, URI.FRAGMENT_FIRST_SEPARATOR).toString());
    assertEquals("Bad URI decode: " + encodedWithFragmentFirst, unencoded, URI.decode(encodedWithFragmentFirst.toString()));

    assertEquals("Bad URI encode: " + unencoded, encodedWithFragmentLast, URI.createURI(unencoded, false, URI.FRAGMENT_LAST_SEPARATOR).toString());
    assertEquals("Bad URI decode: " + encodedWithFragmentLast, unencoded, URI.decode(encodedWithFragmentLast.toString()));
  }
  
  public void testPlatformURI() throws Exception
  {
    {
      String resource = "platform:/resource/myProject/foo.txt";
      URI uri = URI.createURI(resource);
      assertTrue(uri.isPlatform());
      assertEquals("platform:/resource/myProject/foo.txt", uri.toString());
      assertEquals("/myProject/foo.txt", uri.toPlatformString(true));
    }    
    {
      String resource = "myProject/foo.txt";
      URI uri = URI.createPlatformResourceURI(resource, true);
      assertTrue(uri.isPlatform());
      assertFalse(uri.isFile());
      assertEquals("platform:/resource/myProject/foo.txt", uri.toString());
      assertEquals("/myProject/foo.txt", uri.toPlatformString(true));
    }
    {
      String resource = "platform:/resource/myProject/foo.txt";
      URI uri = URI.createPlatformResourceURI(resource, true);
      assertTrue(uri.isPlatform());
      assertFalse(uri.isFile());
      assertEquals("platform:/resource/platform:/resource/myProject/foo.txt", uri.toString());
      assertEquals("/platform:/resource/myProject/foo.txt", uri.toPlatformString(true));
    }
    {
      String resource = new File("myProject/foo.txt").getAbsolutePath();
      URI uri = URI.createFileURI(resource);
      assertFalse(uri.isPlatform());
      assertTrue(uri.isFile());
      
      resource = resource.replace('\\', '/');   
      if (resource.charAt(0) != '/') resource = "/" + resource;
      
      assertEquals("file:" + resource, uri.toString());
      assertNull(uri.toPlatformString(true));
    }
    {
      String resource = "myProject/foo.txt";
      URI uri = URI.createFileURI(resource);
      assertFalse(uri.isPlatform());
      assertTrue(uri.isFile());
      assertEquals("myProject/foo.txt", uri.toString());
      assertNull(uri.toPlatformString(true));
    }

    String[] paths = UNENCODED_PLATFORM_PATHS;
    String[] encodedURIStrings = ENCODED_PLATFORM_PATH_URIS;
    for (int i = 0, len = paths.length; i < len; i++)
    {
      String path = paths[i];
      URI uri = URI.createPlatformResourceURI(path, true);
      assertEquals("Bad platform resource encode: " + path, encodedURIStrings[i], uri.toString());
      assertEquals(encodedURIStrings[i].substring("platform:/resource".length()), uri.toPlatformString(false));      
    }
  }
}
