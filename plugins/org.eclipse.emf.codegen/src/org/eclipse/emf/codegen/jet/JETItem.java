/**
 * Copyright (c) 2019 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.jet;


import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.Assert;


/**
 * @since 2.19
 */
public class JETItem
{
  private final JETMark start;

  private final JETMark stop;

  JETItem(JETMark start, JETMark stop)
  {
    this.start = start;
    this.stop = stop;

    Assert.isNotNull(start, "An item must have a start");
    Assert.isNotNull(start, "An item must have a stop");
    assertDoesNotSpanFiles();
  }

  protected void assertDoesNotSpanFiles()
  {
    Assert.isTrue(start.getFileId() == stop.getFileId(), "An item must not span differnt files");
  }

  public final int getFileID()
  {
    return start.getFileId();
  }

  public final JETMark getStart()
  {
    return start;
  }

  public final JETMark getStop()
  {
    return stop;
  }

  public final int getStartOffset()
  {
    return start.getCursor();
  }

  public final int getStopOffset()
  {
    return stop.getCursor();
  }

  public final int getLength()
  {
    return getStopOffset() - getStartOffset();
  }

  public String getText()
  {
    return new String(start.reader.getChars(start, stop));
  }

  public boolean isAncestor(JETItem jetItem)
  {
    for (JETItem parent = jetItem; parent != null; parent = parent.getParent())
    {
      if (parent == this)
      {
        return true;
      }
    }
    return false;
  }

  public JETItem getParent()
  {
    return null;
  }

  public List<JETSubItem> getChildren()
  {
    return Collections.emptyList();
  }

  public JETSubItem getChild(int fileID, int templateOffset)
  {
    for (JETSubItem child : getChildren())
    {
      if (child.getFileID() == fileID)
      {
        if (child.getStartOffset() <= templateOffset && templateOffset < child.getStopOffset())
        {
          return child;
        }
      }
    }
    return null;
  }

  public JETSubItem getLeaf(int fileID, int templateOffset)
  {
    JETSubItem child = getChild(fileID, templateOffset);
    return child == null ? asSubItem() : child.getLeaf(fileID, templateOffset);
  }

  protected JETSubItem asSubItem()
  {
    return null;
  }

  public JETItem getRoot()
  {
    return this;
  }

  @Override
  public String toString()
  {
    StringBuilder result = new StringBuilder();
    String file = start.getFile();
    if (file != null && file.length() != 0)
    {
      result.append(file).append(":").append(getFileID());
    }
    else
    {
      int fileID = getFileID();
      if (fileID != 0)
      {
        result.append(fileID);
      }
    }

    return result.append('[').append(getStartOffset()).append(',').append(getStopOffset()).append("] ").append(toString(getText())).toString();
  }

  private static final Pattern TEXT_REPLACEMENT_PATTERN = Pattern.compile("([\\u0000-\\u001f\\\\])");

  private static final String[] ESCAPES = {
    "\\\\u0000",
    "\\\\u0001",
    "\\\\u0002",
    "\\\\u0003",
    "\\\\u0004",
    "\\\\u0005",
    "\\\\u0006",
    "\\\\u0007",
    "\\\\b",
    "\\\\t",
    "\\\\n",
    "\\\\u000b",
    "\\\\f",
    "\\\\r",
    "\\\\u000e",
    "\\\\u000f",
    "\\\\u0010",
    "\\\\u0011",
    "\\\\u0012",
    "\\\\u0013",
    "\\\\u0014",
    "\\\\u0015",
    "\\\\u0016",
    "\\\\u0017",
    "\\\\u0018",
    "\\\\u0019",
    "\\\\u001a",
    "\\\\u001b",
    "\\\\u001c",
    "\\\\u001d",
    "\\\\u001e",
    "\\\\u001f", };

  public static String toString(String text)
  {
    if (text == null)
    {
      return "null";
    }

    Matcher matcher = TEXT_REPLACEMENT_PATTERN.matcher(text);
    if (matcher.find())
    {
      StringBuffer result = new StringBuffer(text.length() + 20);
      do
      {
        String group = matcher.group();
        matcher.appendReplacement(result, "\\".equals(group) ? "\\\\" : ESCAPES[group.charAt(0)]);
      }
      while (matcher.find());
      matcher.appendTail(result);
      return '\u00ab' + result.toString() + '\u00bb';
    }
    else
    {
      return '\u00ab' + text + '\u00bb';
    }
  }
}
