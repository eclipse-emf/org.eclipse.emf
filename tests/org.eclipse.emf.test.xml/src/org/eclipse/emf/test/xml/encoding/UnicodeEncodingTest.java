/**
 * <copyright>
 *
 * Copyright (c) 2006 Jesper Steen Møller 
 * 
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: UnicodeEncodingTest.java,v 1.7 2008/05/07 23:09:09 davidms Exp $
 */

package org.eclipse.emf.test.xml.encoding;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;


/**
 * @author jsm
 */
public class UnicodeEncodingTest extends TestCase
{
  public String encodingName = "ASCII";
  public String xmlVersion = "1.0";

  public UnicodeEncodingTest(String name, String encoding, String xmlVersion)
  {
    super(name);
    encodingName = encoding;
    this.xmlVersion = xmlVersion;
  }
  
  public UnicodeEncodingTest(String name, String encoding)
  {
    this(name, encoding, "1.0");
  }

  @Override
  public String getName()
  {
    return super.getName() + "(" + encodingName + ")";
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("UnicodeEncodingTests");
    ts.addTestSuite(ASCIIEncodingTest.class);
    ts.addTestSuite(ISO_8859_1EncodingTest.class);
    //ts.addTestSuite(ISO_8859_5EncodingTest.class);
    ts.addTestSuite(UTF16BEEncodingTest.class);
    ts.addTestSuite(UTF16LEEncodingTest.class);
    ts.addTestSuite(UTF8EncodingTest.class);
    ts.addTestSuite(ASCIIControlCharacterEncodingTest.class);
    return ts;
  }

  File tempFile;

  @Override
  protected void setUp() throws Exception
  {
    tempFile = File.createTempFile("EMF-encoding-test-" + encodingName, ".tmp.xml");
  }

  @Override
  protected void tearDown() throws Exception
  {
      tempFile.delete();
  }

  public void doEMFSaveAndLoad(String testString) throws IOException
  {
    URI fileURI = URI.createFileURI(tempFile.toString());
    String sourceValue = testString + " represented as XML in " + encodingName;
    EAnnotation eObject = EcoreFactory.eINSTANCE.createEAnnotation();

    eObject.setSource(sourceValue); // Including international characters 

    XMIResource resource = new XMIResourceImpl();
    resource.getContents().add(eObject);
    resource.setEncoding(encodingName);
    resource.setXMLVersion(xmlVersion);
    resource.setURI(fileURI);
    resource.save(new HashMap<String, Object>());

    XMIResource loadedResource = new XMIResourceImpl();
    loadedResource.setURI(fileURI);
    loadedResource.load(new HashMap<String, Object>());
    assertTrue("No errors should occur while loading", loadedResource.getErrors().isEmpty());

    EAnnotation loadedAnnotation = (EAnnotation)loadedResource.getContents().get(0);
    assertEquals("String read back by EMF was different from the one being saved", sourceValue, loadedAnnotation.getSource());
  }

  // From the Unicode spec
  public static final int MIN_SUPPLEMENTARY_CODE_POINT = 0x010000;

  public static final int MIN_CODE_POINT = 0x000000;

  public static final int MAX_CODE_POINT = 0x10ffff;

  public static final char MIN_HIGH_SURROGATE = '\uD800';

  public static final char MAX_HIGH_SURROGATE = '\uDBFF';

  public static final char MIN_LOW_SURROGATE = '\uDC00';

  public static final char MAX_LOW_SURROGATE = '\uDFFF';

  public static char[] toSurrogatePair(int codePoint)
  {
    if (codePoint < 0 || codePoint > MAX_CODE_POINT)
    {
      throw new IllegalArgumentException();
    }
    if (codePoint < MIN_SUPPLEMENTARY_CODE_POINT)
    {
      return new char []{ (char)codePoint };
    }
    char[] result = new char [2];

    int offset = codePoint - MIN_SUPPLEMENTARY_CODE_POINT;
    result[0] = (char)((offset >>> 10) + MIN_HIGH_SURROGATE);
    result[1] = (char)((offset & 0x3ff) + MIN_LOW_SURROGATE);

    return result;
  }

  public void testStraightASCII() throws Exception
  {

    // Test in the ASCII range
    doEMFSaveAndLoad("Just straight ASCII small < and (&) big >");
  }

  public void testCharactersIn8bit() throws Exception
  {
    // Test in the 8 bit range of Unicode
    doEMFSaveAndLoad("A name in Western Europe: Jesper Steen M\u00F8ller");
  }

  // Test outside the 8 bit range of Unicode
  public void testArabicLetter() throws Exception
  {
    // Test in the 16 bit range of Unicode
    doEMFSaveAndLoad("This is an arabic glyph: \uFECE. ");
  }

  // Test beyong the 16 bit range of Unicode
  public void testSupplementaryContent() throws Exception
  {
    /**
     * Test in the supplementary code point area (this will get tricky)
     * See <http://www.unicode.org/charts/PDF/U10330.pdf>
     */
    char kusma[] = toSurrogatePair(0x1033A); // GOTHIC LETTER KUSMA
    doEMFSaveAndLoad("This is a gothic letter: " + new String(kusma) + ". ");

    // XML saving and loading has errors, too!
  }
  
  public void testControlCharacters() throws Exception
  {
    if ("1.1".equals(xmlVersion))
    {
      StringBuffer text = new StringBuffer();
      for (char i = 1; i <= 0x1F; ++i)
      {
        text.append(i);
      }
      doEMFSaveAndLoad("These are control characters: " + text + ". ");
    }
  }

  static public class UTF8EncodingTest extends UnicodeEncodingTest
  {
    public UTF8EncodingTest(String n)
    {
      super(n, "UTF-8");
    }
  }

  static public class UTF16BEEncodingTest extends UnicodeEncodingTest
  {
    public UTF16BEEncodingTest(String n)
    {
      super(n, "UTF-16BE");
    }
  }

  static public class UTF16LEEncodingTest extends UnicodeEncodingTest
  {
    public UTF16LEEncodingTest(String n)
    {
      super(n, "UTF-16LE");
    }
  }

  static public class ASCIIEncodingTest extends UnicodeEncodingTest
  {
    public ASCIIEncodingTest(String n)
    {
      super(n, "ASCII");
    }
  }
  static public class ISO_8859_1EncodingTest extends UnicodeEncodingTest
  {
    public ISO_8859_1EncodingTest(String n)
    {
      super(n, "ISO-8859-1");
    }
  }

  static public class ISO_8859_5EncodingTest extends UnicodeEncodingTest
  {
    public ISO_8859_5EncodingTest(String n)
    {
      super(n, "ISO-8859-5");
    }
  }

  static public class ASCIIControlCharacterEncodingTest extends UnicodeEncodingTest
  {
    public ASCIIControlCharacterEncodingTest(String n)
    {
      super(n, "ASCII", "1.1");
    }
  }

}
