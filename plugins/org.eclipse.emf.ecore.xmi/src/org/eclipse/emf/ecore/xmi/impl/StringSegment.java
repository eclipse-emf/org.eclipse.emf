/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: StringSegment.java,v 1.2.2.1 2005/06/08 18:27:42 nickb Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import java.util.Iterator;
import java.util.ListIterator;

import org.eclipse.emf.common.util.BasicEList;


/**
 * A String Buffer that never reallocates
 */
public class StringSegment extends BasicEList 
{
  protected int segmentCapacity;
  protected Element cursor;
  protected String lineSeparator = System.getProperty("line.separator");
  protected String temporaryFileName;
  protected Writer temporaryFile;

  public StringSegment() 
  {
    super(1000);
    this.segmentCapacity = 1000;
    add(cursor = new Element(1000));
  }

  public StringSegment(int minimumCapacity) 
  {
    super(minimumCapacity);
    this.segmentCapacity = 1000;
    add(cursor = new Element(1000));
  }

  public StringSegment(int minimumCapacity, int segmentCapacity) 
  {
    super(minimumCapacity);
    add(cursor = new Element(this.segmentCapacity = segmentCapacity));
  }

  public StringSegment(String temporaryFileName)
  {
    super(1000);
    this.segmentCapacity = 1000;
    add(cursor = new Element(1000));
    if (temporaryFileName != null)
    {
      this.temporaryFileName = temporaryFileName;
      buffer = new char [8192];
    }
  }

  public String getTemporaryFileName()
  {
    return temporaryFileName;
  }

  protected Object[] newData(int capacity) 
  {
    return new Element[capacity];
  }

  protected char [] buffer;
  protected int bufferPosition;

  public void add(String newString) 
  {
    // System.err.println("add = ["+newString+"]");

    // If there is a temporary file...
    //
    if (temporaryFile != null)
    {
      int length = newString.length();
      if (length + bufferPosition >= buffer.length)
      {
        try
        {
          temporaryFile.write(buffer, 0, bufferPosition);
        }
        catch (IOException exception)
        {
        }
        bufferPosition = 0;
        if (length > buffer.length) 
        {
          buffer = new char[length];
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

    cursor = new Element(segmentCapacity);
    cursor.add(newString);

    // The first case is the most common case.
    // It is slightly cheaper to call add without an index since an index will be range checked.
    //
    int i = size - 1;
    if (data[i] == oldCursor)
    {
      super.add(cursor);
    }
    else
    {
      // This case can only happen if we are reset to a mark and we've got lots of XMLNS attributes to write.
      //
      while (--i >= 0)
      {
        if (data[i] == oldCursor)
        {
          super.add(i + 1, cursor);
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
    cursor = new Element(segmentCapacity);
    super.add(cursor);

    if (temporaryFileName != null && temporaryFile == null)
    {
      try
      {
        temporaryFile = new OutputStreamWriter(new FileOutputStream(temporaryFileName), "UTF8");
      }
      catch (IOException exception)
      {
      }
    }

    return result;
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
      }
      temporaryFile = null;
    }
    cursor = (Element)mark;
  }

  public int getLength() 
  {
    Element [] elements = (Element [])data;
    int length = 0;
    for (int i = 0;  i < size; ++i) 
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
    Element [] elements = (Element [])data;
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

  protected static class Element 
  {
    int size;
    String [] data;
    
    Element(int capacity) 
    {
      data = new String[capacity];
    }

    void add(String newString) 
    {
      data[size++] = newString;
    }
  }

  public Iterator iterator() 
  {
    return new SegmentIterator();
  }
    
  public ListIterator listIterator() 
  {
    return new SegmentIterator();
  }
    
  protected class SegmentIterator implements ListIterator 
  {
    protected int outerIndex = 0;
    protected int innerIndex = 0;
    
    SegmentIterator() 
    {
    }

    public boolean hasNext() 
    {
      return 
        outerIndex < size - 1 || 
        (outerIndex == size-1 && innerIndex < ((Element) data[outerIndex]).size);
    }

    public boolean hasPrevious() 
    {
      return outerIndex > 0 || innerIndex > 0;
    }

    public Object next() 
    {
      Element element = (Element) data[outerIndex];
      if (innerIndex < element.size) 
      {
        return element.data[innerIndex++];
      }
      else
      {
        innerIndex = 1;
        return ((Element) data[++outerIndex]).data[0];
      }
    }

    public Object previous() 
    {
      if (innerIndex > 0) 
      {
        return ((Element) data[outerIndex]).data[--innerIndex];
      }
      else
      {
        Element element = (Element) data[--outerIndex];
        innerIndex = element.size-1;
        return element.data[innerIndex];
      }
    }
    
    public void add(Object newElement) 
    {
      throw new UnsupportedOperationException(SegmentIterator.class.toString());
    }

    public void remove() 
    {
      throw new UnsupportedOperationException(SegmentIterator.class.toString());
    }

    public void set(Object newElement) 
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
