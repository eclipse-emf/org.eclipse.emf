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
 * $Id: XMLString.java,v 1.4 2004/09/01 20:11:47 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.util.List;

import org.eclipse.emf.common.util.BasicEList;


/*
 * Calling sequence is:
 * startElement()
 *   addAttrribute() 0 or more times
 *   startAttribute(), addAttributeContent(), and endAttribute(), 0 or more times
 *   endEmptyElement() or endContentElement()
 *   startElement()  0 or more times
 * endElement()
 */
public class XMLString extends StringSegment
{
  protected List elementNames;
  protected List mixed;
  protected boolean isUnformatted;
  protected boolean isMixed;
  protected List indents;
  protected int depth;
  protected int lineWidth;
  protected int markedLineWidth;
  protected int currentLineWidth;
  protected boolean lastElementIsStart;
  protected Object firstElementMark;
  protected boolean seenRoot;
  protected boolean saveDoctype;
  protected String publicId;
  protected String systemId;

  public XMLString(int lineWidth) 
  {
    this(lineWidth, null);
  }

  public XMLString(int lineWidth, String temporaryFileName) 
  {
    super(temporaryFileName);

    this.lineWidth = lineWidth;

    elementNames = new BasicEList();
    mixed = new BasicEList();
    indents = new BasicEList();
    indents.add("");
  }
  
  public XMLString(int lineWidth, String publicId, String systemId) 
  {
    this(lineWidth, publicId, systemId, null);
  }

  public XMLString(int lineWidth, String publicId, String systemId, String temporaryFileName) 
  {
    this(lineWidth, temporaryFileName);

    saveDoctype = true;
    this.publicId = publicId;
    this.systemId = systemId;   
  }

  public void startElement(String name) 
  {
    if (lastElementIsStart)
    {
      closeStartElement();
    }
    elementNames.add(name);
    if (name != null)
    {
      if (!seenRoot)
      {
        seenRoot = true;
        // write doctype
        if (saveDoctype)
        {
          add("<!DOCTYPE ");
          add(name);         
          if (publicId !=null)
          {
            add(" PUBLIC \"");
            add(publicId);
            add("\" ");
            add("\"");
            add(systemId);
            add("\">");
          }
          else if (systemId != null)
          {
            add(" SYSTEM \"");
            add(systemId);
            add("\">");
          }
          else
          {
            add(">");
          }
          
          addLine();
        }
      }
      ++depth;
      if (!isMixed)
      {
        add(getElementIndent());
      }
      add("<");
      add(name);
      if (firstElementMark == null)
      {
        firstElementMark = mark();
      }
      lastElementIsStart = true;
    }
    else if (!isMixed)
    {
      add(getElementIndent(1));
    }

    mixed.add(isMixed ? Boolean.TRUE : Boolean.FALSE);
    isMixed = isUnformatted;
  }

  public void setMixed(boolean isMixed)
  {
    this.isMixed = isMixed;
  }

  public void setUnformatted(boolean isUnformatted)
  {
    this.isUnformatted = isUnformatted;
  }

  public void addAttribute(String name, String value) 
  {
    if (currentLineWidth > lineWidth)
    {
      addLine();
      add(getAttributeIndent());
    }
    else
    {
      add(" ");
    }
    add(name);
    add("=\"");
    add(value);
    add("\"");
  }

  public void addAttributeNS(String prefix, String localName, String value) 
  {
    if (currentLineWidth > lineWidth)
    {
      addLine();
      add(getAttributeIndent());
    }
    else
    {
      add(" ");
    }
    add(prefix);
    add(":");
    add(localName);
    add("=\"");
    add(value);
    add("\"");
  }

  public void startAttribute(String name)
  {
    if (currentLineWidth > lineWidth)
    {
      addLine();
      add(getAttributeIndent());
    }
    else
    {
      add(" ");
    }
    add(name);
    add("=\"");
  }

  public void addAttributeContent(String content)
  {
    add(content);
  }

  public void endAttribute()
  {
    add("\"");
  }

  protected void closeStartElement() 
  {
    add(">");
    if (!isMixed)
    {
      addLine();
    }
    lastElementIsStart = false;
  }

  public void endEmptyElement() 
  {
    removeLast();
    add("/>");
    if (!isMixed)
    {
      addLine();
    }
    lastElementIsStart = false;
  }

  public void endContentElement(String content) 
  {
    add(">");
    add(content);
    add("</");
    String name = removeLast();
    add(name);
    add(">");
    if (!isMixed)
    {
      addLine();
    }
    lastElementIsStart = false;
  }
  
  public void endElement() 
  {
    if (lastElementIsStart)
    {
      endEmptyElement();
    }
    else
    {
      boolean wasMixed = isMixed;
      String name = removeLast();
      if (name != null)
      {
        if (!wasMixed)
        {
          add(getElementIndent(1));
        }
        add("</");
        add(name);
        add(">");
  
        if (!isMixed)
        {
          addLine();
        }
      }
    }
  }

  protected String removeLast() 
  {
    int end = elementNames.size();
    isMixed = ((Boolean)mixed.remove(end - 1)).booleanValue();
    String result = (String)elementNames.remove(end - 1);
    if (result != null)
    {
      --depth;
    }
    return result;
  }

  protected String getElementIndent() 
  {
    return getElementIndent(0);
  }

  protected String getElementIndent(int extra) 
  {
    int nesting = depth + extra - 1; 
    for (int i = indents.size() - 1; i < nesting; ++i)
    {
      indents.add(indents.get(i) + "  ");
    }
    return (String)indents.get(nesting);
  }

  protected String getAttributeIndent() 
  {
    int nesting = depth + 1; 
    for (int i = indents.size() - 1; i < nesting; ++i)
    {
      indents.add(indents.get(i) + "  ");
    }
    return (String)indents.get(nesting);
  }

  public void addText(String newString)
  {
    if (lastElementIsStart)
    {
      closeStartElement();
    }

    if (lineWidth  != Integer.MAX_VALUE)
    {
      currentLineWidth += newString.length();
      LOOP: for (int i = newString.length() - 1; i >= 0; --i)
      {
        switch (newString.charAt(i))
        {
          case '\n':
          case '\r':
          {
            currentLineWidth = newString.length() - i;
            break LOOP;
          }
        }
      }
    }

    super.add(newString);
  }

  public void addCDATA(String newString)
  {
    if (lastElementIsStart)
    {
      closeStartElement();
    }

    add("<![CDATA[");
    if (lineWidth  != Integer.MAX_VALUE)
    {
      currentLineWidth += newString.length();
      LOOP: for (int i = newString.length() - 1; i >= 0; --i)
      {
        switch (newString.charAt(i))
        {
          case '\n':
          case '\r':
          {
            currentLineWidth = newString.length() - i;
            break LOOP;
          }
        }
      }
    }

    super.add(newString);
    add("]]>");
  }

  public void addComment(String newString)
  {
    if (lastElementIsStart)
    {
      closeStartElement();
    }

    if (firstElementMark != null && elementNames.isEmpty())
    {
      addLine();
    }
    add("<!--");
    if (lineWidth  != Integer.MAX_VALUE)
    {
      currentLineWidth += newString.length();
      LOOP: for (int i = newString.length() - 1; i >= 0; --i)
      {
        switch (newString.charAt(i))
        {
          case '\n':
          case '\r':
          {
            currentLineWidth = newString.length() - i;
            break LOOP;
          }
        }
      }
    }

    super.add(newString);
    add("-->");
    if (firstElementMark == null)
    {
      addLine();
    }
  }

  public void add(String newString)
  {
    // Avoid a function call...
    //
    if (lineWidth != Integer.MAX_VALUE)
    {
      currentLineWidth += newString.length();
    }
    super.add(newString);
  }

  public void addLine()
  {
    super.addLine();
    currentLineWidth = 0;
  }

  public Object mark()
  {
    markedLineWidth = currentLineWidth;
    currentLineWidth = lineWidth - 2;
    return super.mark();
  }

  public void resetToMark(Object mark)
  {
    if (mark == null)
    {
      mark = firstElementMark;
    }

    super.resetToMark(mark);

    // Ensure correct indentation for a top level attribute.
    //
    // elementNames.add("mark");
    ++depth;

    currentLineWidth = markedLineWidth;
  }
}
