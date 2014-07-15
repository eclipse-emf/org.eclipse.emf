/**
 * Copyright (c) 2006 Jesper Steen Møller
 *
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */

package org.eclipse.emf.test.xml.encoding;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * @author jsm
 */
@RunWith(Parameterized.class)
public class UnicodeEncodingTest
{
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

  protected File tempFile;
  protected final String encodingName;
  protected final String xmlVersion;

  public UnicodeEncodingTest(String encoding, String xmlVersion)
  {
    encodingName = encoding;
    this.xmlVersion = xmlVersion;
  }

  @Parameterized.Parameters(name="Encoding {0} for XML {1}")
  public static Collection<Object[]> parameters()
  {
    return
      Arrays.asList
        (new Object[][]
         {
           { "UTF-8", "1.0" },
           { "UTF-16BE", "1.0" },
           { "UTF-16LE", "1.0" },
           { "ASCII", "1.0" },
           { "ISO-8859-1", "1.0" },
       //  { "ISO-8859-5", "1.0" },
           { "ASCII", "1.1" },
         });
  }

  @Before
  public void setUp() throws Exception
  {
    tempFile = File.createTempFile("EMF-encoding-test-" + encodingName, ".tmp.xml");
  }

  @After
  public void tearDown() throws Exception
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


  @Test
  public void testStraightASCII() throws Exception
  {
    // Test in the ASCII range
    doEMFSaveAndLoad("Just straight ASCII small < and (&) big >");
  }

  @Test
  public void testCharactersIn8bit() throws Exception
  {
    // Test in the 8 bit range of Unicode
    doEMFSaveAndLoad("A name in Western Europe: Jesper Steen M\u00F8ller");
  }

  // Test outside the 8 bit range of Unicode
  @Test
  public void testArabicLetter() throws Exception
  {
    // Test in the 16 bit range of Unicode
    doEMFSaveAndLoad("This is an arabic glyph: \uFECE. ");
  }

  // Test beyond the 16 bit range of Unicode
  @Test
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

  @Test
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
}
