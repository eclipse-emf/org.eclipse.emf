/**
 * Copyright (c) 2014 Eclipse contributorsand others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   stefan schalomon, msg-systems.com - Initial API and implementation
 */
package org.eclipse.emf.test.xml;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;

import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.xmi.impl.XMLString;
import org.junit.Test;

public class XMLStringTest
{
  /**
   * A test that causes {@link ArrayIndexOutOfBoundsException}.
   */
  @Test
  public void testXMLString() throws Exception
  {
    XMLString doc = new XMLString();
    // write XML with default encoding - this will resize
    // doc.outputchars to a length greater default BUFFER_SIZE,
    // doc.outputbytes is not used during this call
    writeDoc( doc, false, 5, 10, 1000 );

    // now write a bigger XML with ASCII encoding - doc.outputbytes
    // will be initialized with default BUFFER_SIZE, that is,
    // doc.outputbytes.length < doc.outputchars.length which will
    // eventually trigger the ArrayIndexOutOfBoundsException
    writeDoc( doc, true, 20, 20, 1000 );
  }

  /**
   * A test with overridden {@link XMLString} that fixes the AIOOBE bug.
   */
  @Test
  public void testFixedXMLString() throws Exception
  {
    XMLString doc = new XMLString()
    {
      private static final long serialVersionUID = 1L;

      @Override
      public void writeAscii( OutputStream os, int flushThreshold ) throws IOException
      {
        int requiredLength = Math.max( BUFFER_SIZE, outputchars.length );
        // ensure the byte and character buffers have the same length
        if( outputbytes == null || outputbytes.length < requiredLength )
        {
          outputbytes = new byte[requiredLength];
        }

        super.writeAscii( os, flushThreshold );
      }
    };

    writeDoc( doc, false, 5, 10, 1000 );
    // with the fix above, the following call will work
    writeDoc( doc, true, 20, 20, 1000 );
  }

  private void writeDoc( XMLString doc,
                         boolean ascii,
                         int elementCount,
                         int attributeCount,
                         int contentSize ) throws Exception
  {
    doc.reset( null, null, Integer.MAX_VALUE, null );
    doc.startElement( "doc" );
    for( int e = 1; e <= elementCount; e++ )
    {
      StringBuilder content = new StringBuilder();
      for( int c = 1; c <= contentSize; c++ )
      {
        content.append( Long.toHexString( 0xBAADF00DDE7EC7EDL ) );
      }

      doc.startElement( "elem" + e );
      for( int a = 1; a <= attributeCount; a++ )
      {
        doc.startAttribute( "attr" + a );
        doc.addAttributeContent( "AttributeValue:" + content.toString() );
        doc.endAttribute();
      }
      doc.endContentElement( "ElementContent:" + content.toString() );
    }
    doc.endElement();

    StringWriter writer = new StringWriter();
    if( ascii )
    {
      doc.writeAscii( new URIConverter.WriteableOutputStream( writer, "ASCII"), Integer.MAX_VALUE );
    }
    else
    {
      doc.write( (Writer) writer, Integer.MAX_VALUE );
    }
  }
}
