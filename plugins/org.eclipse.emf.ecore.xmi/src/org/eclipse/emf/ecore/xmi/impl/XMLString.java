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
 * $Id: XMLString.java,v 1.15 2011/10/26 08:05:03 emerks Exp $
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
  private static final long serialVersionUID = 1L;

  protected List<String> elementNames;

  protected List<Boolean> mixed;

  protected boolean isUnformatted;

  protected boolean isMixed;

  protected List<String> indents;

  protected int depth;

  protected int lineWidth;

  protected int markedLineWidth;

  protected int currentLineWidth;

  protected boolean lastElementIsStart;

  protected Object firstElementMark;

  protected boolean seenRoot;

  protected boolean saveDoctype;

  protected Object docTypeMark;

  protected String docTypeName;

  protected String publicId;

  protected String systemId;

  public XMLString()
  {
    this(80);
  }

  public XMLString(int lineWidth)
  {
    this(lineWidth, null);
  }

  public XMLString(int lineWidth, String temporaryFileName)
  {
    super(temporaryFileName);

    this.lineWidth = lineWidth;
    elementNames = new BasicEList<String>();
    mixed = new BasicEList<Boolean>();
    indents = new BasicEList<String>();
    indents.add("");
  }

  public XMLString(int lineWidth, String publicId, String systemId)
  {
    this(lineWidth, publicId, systemId, null);
  }

  public XMLString(int lineWidth, String publicId, String systemId, String temporaryFileName)
  {
    this(lineWidth, temporaryFileName);
    if (publicId != null || systemId != null)
    {
      saveDoctype = true;
      this.publicId = publicId;
      this.systemId = systemId;
    }
  }

  public void setLineWidth(int lineWidth)
  {
    this.lineWidth = lineWidth;
  }

  public void reset(String publicId, String systemId, int lineWidth, String temporaryFileName)
  {
    super.reset();
    setTemporaryFileName(temporaryFileName);
    elementNames.clear();
    mixed.clear();
    indents.clear();
    indents.add("");
    if (publicId != null || systemId != null)
    {
      saveDoctype = true;
      this.publicId = publicId;
      this.systemId = systemId;
    }
    else
    {
      saveDoctype = false;
    }
    seenRoot = false;
    lastElementIsStart = false;
    isMixed = false;
    isUnformatted = false;
    depth = 0;
    markedLineWidth = 0;
    this.lineWidth = lineWidth;
    currentLineWidth = 0;
    firstElementMark = null;
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
      saveDoctype(name);
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
        startFileBuffering();
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

  public void saveNilElement(String name)
  {
    if (lastElementIsStart)
    {
      closeStartElement();
    }
    saveDoctype(name);

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
      startFileBuffering();
    }
    if (currentLineWidth > lineWidth)
    {
      addLine();
      add(getAttributeIndent());
    }
    else
    {
      add(" ");
    }
    add("xsi:nil=\"true\"/>");

    --depth;

    if (!isUnformatted && !isMixed)
    {
      addLine();
    }
    lastElementIsStart = false;
  }

  public void saveDataValueElement(String name, String content)
  {
    if (lastElementIsStart)
    {
      closeStartElement();
    }
    saveDoctype(name);

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
      startFileBuffering();
    }

    add(">");
    add(content);
    add("</");

    --depth;
    add(name);
    add(">");
    if (!isUnformatted && !isMixed)
    {
      addLine();
    }
    lastElementIsStart = false;
  }

  public void addEntity(String name, String value)
  {
    if (docTypeMark != null)
    {
      resetToMark(docTypeMark);
      if (saveDoctype)
      {
        add(" [");
        addLine();
        docTypeMark = mark();
        add("]");
      }
      else
      {
        add("<!DOCTYPE ");
        add(docTypeName);
        add(" [");
        addLine();
        docTypeMark = mark();
        add("]>");
        addLine();
      }
      resetToMark(docTypeMark);
      docTypeMark = null;
    }

    add("<!ENTITY ");
    add(name);
    add(" \"");
    add(value);
    add("\">");
    addLine();
  }

  final protected void saveDoctype(String name)
  {
    if (!seenRoot)
    {
      seenRoot = true;
      // write doctype
      if (saveDoctype)
      {
        add("<!DOCTYPE ");
        add(name);
        if (publicId != null)
        {
          add(" PUBLIC \"");
          add(publicId);
          add("\" ");
          add("\"");
          add(systemId);
          add("\"");
          docTypeMark = mark();
          mark();
          add(">");
        }
        else if (systemId != null)
        {
          add(" SYSTEM \"");
          add(systemId);
          add("\"");
          docTypeMark = mark();
          mark();
          add(">");
        }
        else
        {
          docTypeMark = mark();
          mark();
          add(">");
        }

        addLine();
      }
      else
      {
        docTypeMark = mark();
        docTypeName = name;
        mark();
      }
    }
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
    isMixed = mixed.remove(end - 1);
    String result = elementNames.remove(end - 1);
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
    return indents.get(nesting);
  }

  protected String getAttributeIndent()
  {
    int nesting = depth + 1;
    for (int i = indents.size() - 1; i < nesting; ++i)
    {
      indents.add(indents.get(i) + "  ");
    }
    return indents.get(nesting);
  }

  public void addText(String newString)
  {
    if (lastElementIsStart)
    {
      closeStartElement();
    }

    if (lineWidth != Integer.MAX_VALUE)
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
    if (lineWidth != Integer.MAX_VALUE)
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

    if (firstElementMark != null && (elementNames.isEmpty() || elementNames.size() == 1 && elementNames.get(0) == null))
    {
      addLine();
    }
    add("<!--");
    if (lineWidth != Integer.MAX_VALUE)
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
  
  public void addProcessingInstruction(String target, String data)
  {
    if (lastElementIsStart)
    {
      closeStartElement();
    }

    if (firstElementMark != null && (elementNames.isEmpty() || (elementNames.size() == 1 && elementNames.get(0) == null)))
    {
      addLine();
    }
    add("<?");
    add(target == null ? "_" : target);

    if (data != null)
    {
      add(" ");
      if (lineWidth != Integer.MAX_VALUE)
      {
        currentLineWidth += data.length();
        LOOP: 
        for (int i = data.length() - 1; i >= 0; --i)
        {
          switch (data.charAt(i))
          {
            case '\n':
            case '\r':
            {
              currentLineWidth = data.length() - i;
              break LOOP;
            }
          }
        }
      }
      super.add(data);
    }
    add("?>");
    if (firstElementMark == null)
    {
      addLine();
    }
  }

  @Override
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

  @Override
  public void addLine()
  {
    super.addLine();
    currentLineWidth = 0;
  }

  /**
   * Once the document is complete, 
   * in particular once the namespace declarations have been added at the mark location,
   * we should try to insert a line break  after those declarations.
   */
  public void complete()
  {
    if (currentLineWidth > lineWidth)
    {
      if (" ".equals(firstString))
      {
        addLine();
        add("   ");
      }
      else if (cursorIndex + 1 < data.length)
      {
        Element element = (Element)data[cursorIndex + 1];
        if (element != null && element.size > 0 && " ".equals(element.data[0]))
        {
          addLine();
          add("   ");
        }
      }
    }
  }

  @Override
  public Object mark()
  {
    markedLineWidth = currentLineWidth;
    return super.mark();
  }

  @Override
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
