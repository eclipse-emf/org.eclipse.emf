/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: StringSegment.java,v 1.8 2011/10/26 08:05:03 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.util.Iterator;
import java.util.ListIterator;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.ecore.xmi.XMIPlugin;


/**
 * A String Buffer that never reallocates
 */
public class StringSegment extends BasicEList<StringSegment.Element>
{
  private static final long serialVersionUID = 1L;

  protected final static int LIST_SIZE = 100;

  protected static final int ELEMENT_SIZE = 1000;

  protected static final int BUFFER_SIZE = 8192;

  protected int segmentCapacity;

  protected byte[] outputbytes;

  protected char[] outputchars;

  protected char[] buffer;

  protected Element cursor;

  protected int cursorIndex = 0;

  protected String lineSeparator = System.getProperty("line.separator");

  protected String temporaryFileName;

  protected Writer temporaryFile;

  protected int bufferPosition;

  protected String firstString;

  public StringSegment()
  {
    this(LIST_SIZE);
  }

  public StringSegment(int minimumCapacity)
  {
    this(minimumCapacity, ELEMENT_SIZE);
  }

  public StringSegment(int minimumCapacity, int segmentCapacity)
  {
    super(minimumCapacity);
    add(cursor = new Element(this.segmentCapacity = segmentCapacity));
    outputchars = new char [BUFFER_SIZE];
  }

  public StringSegment(String temporaryFileName)
  {
    this(LIST_SIZE, ELEMENT_SIZE);
    setTemporaryFileName(temporaryFileName);
  }

  public void setTemporaryFileName(String tempFile)
  {
    temporaryFileName = tempFile;
    if (temporaryFileName != null)
    {
      buffer = new char [BUFFER_SIZE];
    }
    else
    {
      buffer = null;
    }
  }

  public String getTemporaryFileName()
  {
    return temporaryFileName;
  }

  @Override
  protected Object[] newData(int capacity)
  {
    return new Element [capacity];
  }

  public void reset()
  {
    bufferPosition = 0;
    cursor = (Element)data[0];
    cursorIndex = 0;
    for (int i = 0; i < size; i++)
    {
      ((Element)data[i]).size = 0;
    }
  }

  public void add(String newString)
  {
    // System.err.println("add = ["+newString+"]");

    // If there is a temporary file...
    //
    if (temporaryFile != null)
    {
      if (firstString == null)
      {
        firstString = newString;
      }
      int length = newString.length();
      if (length + bufferPosition >= buffer.length)
      {
        try
        {
          temporaryFile.write(buffer, 0, bufferPosition);
        }
        catch (IOException exception)
        {
          XMIPlugin.INSTANCE.log(exception);
        }
        bufferPosition = 0;
        if (length > buffer.length)
        {
          buffer = new char [length];
        }
      }
      newString.getChars(0, length, buffer, bufferPosition);
      bufferPosition += length;
      return;
    }

    // This is the cheapest and most common case.
    //
    if (cursor.size < segmentCapacity)
    {
      cursor.add(newString);
      return;
    }

    Element oldCursor = cursor;
    int index = size - 1;
    if (cursorIndex < index)
    {
      cursor = (Element)data[++cursorIndex];
      if (cursor.size == 0)
      {
        cursor.add(newString);
        return;
      }
    }

    cursor = new Element(segmentCapacity);
    cursor.add(newString);

    // The first case is the most common case.
    // It is slightly cheaper to call add without an index since an index will be range checked.
    //  
    if (data[index] == oldCursor)
    {
      super.add(cursor);
      cursorIndex = ++index;
    }
    else
    {
      // This case can only happen if we are reset to a mark and we've got lots of XMLNS attributes to write.
      //
      int counter = 0;
      while (counter < index)
      {
        if (data[counter++] == oldCursor)
        {
          cursorIndex = counter;
          super.add(cursorIndex, cursor);
          break;
        }
      }
    }
  }

  public void addLine()
  {
    add(lineSeparator);
  }

  public Object mark()
  {
    Element result = cursor;
    if (cursor.size == 0)
    {
      result.add("");
    }
    int i = size - 1;
    if (cursorIndex < i)
    {
      cursor = (Element)data[++cursorIndex];
    }
    else
    {
      cursorIndex++;
      cursor = new Element(segmentCapacity);
      super.add(cursor);
    }
    return result;
  }

  public void startFileBuffering()
  {
    if (temporaryFileName != null && temporaryFile == null)
    {
      try
      {
        temporaryFile = new OutputStreamWriter(new FileOutputStream(temporaryFileName), "UTF8");
      }
      catch (IOException exception)
      {
        // If we can't create one, too bad.
      }
    }
  }

  public void resetToMark(Object mark)
  {
    if (temporaryFile != null)
    {
      cursor.add("");
      try
      {
        temporaryFile.write(buffer, 0, bufferPosition);
        temporaryFile.close();
      }
      catch (IOException exception)
      {
        XMIPlugin.INSTANCE.log(exception);
      }
      temporaryFile = null;
    }
    cursor = (Element)mark;
    for (int i = 0; i < data.length; i++)
    {
      if (data[i] == cursor)
      {
        cursorIndex = i;
        return;
      }
    }
  }

  public int getLength()
  {
    Element[] elements = (Element[])data;
    int length = 0;
    for (int i = 0; i < size; ++i)
    {
      Element element = elements[i];
      int segmentSize = element.size;
      for (int j = 0; j < segmentSize; ++j)
      {
        String s = element.data[j];
        length += s.length();
      }
    }
    return length;
  }

  public int getChars(char[] destination, int position)
  {
    Element[] elements = (Element[])data;
    for (int i = 0; i < size; ++i)
    {
      Element element = elements[i];
      int segmentSize = element.size;
      for (int j = 0; j < segmentSize; ++j)
      {
        String string = element.data[j];
        int length = string.length();
        string.getChars(0, length, destination, position);
        position += length;
      }
    }
    return position;
  }

  public void writeAscii(OutputStream os, int flushThreshold) throws IOException
  {
    if (outputbytes == null)
    {
      outputbytes = new byte [BUFFER_SIZE];
    }
    Element[] elements = (Element[])data;
    int position = 0;
    int count = 0;

    for (int i = 0; i < size; ++i)
    {
      Element element = elements[i];
      int segmentSize = element.size;
      for (int j = 0; j < segmentSize; ++j)
      {
        String string = element.data[j];
        int length = string.length();
        if (length + position >= outputchars.length)
        {
          for (int x = 0; x < position; x++)
          {
            outputbytes[x] = (byte)(outputchars[x] & 0xFF);
          }
          os.write(outputbytes, 0, position);
          position = 0;
          if (length > outputchars.length)
          {
            outputchars = new char [length];
            outputbytes = new byte [length];
          }
        }
        string.getChars(0, length, outputchars, position);
        position += length;
        count += length;
        if (count > flushThreshold)
        {
          os.flush();
          count = 0;
        }
      }
    }
    for (int x = 0; x < position; x++)
    {
      outputbytes[x] = (byte)(outputchars[x] & 0xFF);
    }

    os.write(outputbytes, 0, position);

    String temporaryFileName = this.temporaryFileName;
    if (temporaryFileName != null)
    {
      InputStream inputStream = new FileInputStream(temporaryFileName);
      for (int length = inputStream.read(outputbytes, 0, outputbytes.length); length > 0; length = inputStream.read(
        outputbytes,
        0,
        outputbytes.length))
      {
        os.write(outputbytes, 0, length);
        count += length;
        if (count > flushThreshold)
        {
          os.flush();
          count = 0;
        }
      }
      inputStream.close();
      new File(temporaryFileName).delete();
    }
  }
  
  /**
   * @deprecated since 2.2 - Instead use #write(Writer, int)
   * @param os
   * @param flushThreshold
   * @throws IOException
   */
  @Deprecated
  public void write(OutputStreamWriter os, int flushThreshold) throws IOException
  {
    write((Writer)os, flushThreshold);
  }

  public void write(Writer os, int flushThreshold) throws IOException
  {
    Element[] elements = (Element[])data;
    int position = 0;
    int count = 0;
    for (int i = 0; i < size; ++i)
    {
      Element element = elements[i];
      int segmentSize = element.size;
      for (int j = 0; j < segmentSize; ++j)
      {
        String string = element.data[j];
        int length = string.length();
        if (length + position >= outputchars.length)
        {
          os.write(outputchars, 0, position);
          position = 0;
          if (length > outputchars.length)
          {
            outputchars = new char [length];
          }
        }
        string.getChars(0, length, outputchars, position);
        position += length;
        count += length;
        if (count > flushThreshold)
        {
          os.flush();
          count = 0;
        }
      }
    }
    os.write(outputchars, 0, position);

    String temporaryFileName = this.temporaryFileName;
    if (temporaryFileName != null)
    {
      InputStreamReader reader = new InputStreamReader(new FileInputStream(temporaryFileName), "UTF8");
      for (int length = reader.read(outputchars, 0, outputchars.length); length > 0; length = reader.read(
        outputchars,
        0,
        outputchars.length))
      {
        os.write(outputchars, 0, length);
        count += length;
        if (count > flushThreshold)
        {
          os.flush();
          count = 0;
        }
      }
      reader.close();
      new File(temporaryFileName).delete();
    }

  }

  protected static class Element
  {
    int size;

    String[] data;

    Element(int capacity)
    {
      data = new String [capacity];
    }

    void add(String newString)
    {
      data[size++] = newString;
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public Iterator<Element> iterator()
  {
    // TODO This is really quite attrocious since there is code that will assume an iterator that returns strings!
    return (ListIterator<Element>)(ListIterator<?>)new SegmentIterator();
  }

  @SuppressWarnings("unchecked")
  @Override
  public ListIterator<Element> listIterator()
  {
    // TODO This is really quite attrocious since there is code that will assume an iterator that returns strings!
    return (ListIterator<Element>)(Iterator<?>)new SegmentIterator();
  }

  public Iterator<String> stringIterator()
  {
    return new SegmentIterator();
  }

  protected class SegmentIterator implements ListIterator<String>
  {
    protected int outerIndex = 0;

    protected int innerIndex = 0;

    SegmentIterator()
    {
      super();
    }

    public boolean hasNext()
    {
      return outerIndex < size - 1 || (outerIndex == size - 1 && innerIndex < ((Element)data[outerIndex]).size);
    }

    public boolean hasPrevious()
    {
      return outerIndex > 0 || innerIndex > 0;
    }

    public String next()
    {
      Element element = (Element)data[outerIndex];
      if (innerIndex < element.size)
      {
        return element.data[innerIndex++];
      }
      else
      {
        innerIndex = 1;
        return ((Element)data[++outerIndex]).data[0];
      }
    }

    public String previous()
    {
      if (innerIndex > 0)
      {
        return ((Element)data[outerIndex]).data[--innerIndex];
      }
      else
      {
        Element element = (Element)data[--outerIndex];
        innerIndex = element.size - 1;
        return element.data[innerIndex];
      }
    }

    public void add(String newElement)
    {
      throw new UnsupportedOperationException(SegmentIterator.class.toString());
    }

    public void remove()
    {
      throw new UnsupportedOperationException(SegmentIterator.class.toString());
    }

    public void set(String newElement)
    {
      throw new UnsupportedOperationException(SegmentIterator.class.toString());
    }

    public int nextIndex()
    {
      throw new UnsupportedOperationException(SegmentIterator.class.toString());
    }

    public int previousIndex()
    {
      throw new UnsupportedOperationException(SegmentIterator.class.toString());
    }
  }
}
