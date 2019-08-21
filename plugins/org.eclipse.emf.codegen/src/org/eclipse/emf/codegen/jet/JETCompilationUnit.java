/**
 * Copyright (c) 2019 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.jet;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * @since 2.19
 */
public class JETCompilationUnit
{
  private static boolean trace;

  public static final class JETJavaRange extends JETItem
  {
    private int javaOffset;

    private int javaLength;

    private JETItem jetItem;

    private JETJavaRange(JETMark start, JETMark stop, int javaOffset, int javaLength, JETItem jetItem)
    {
      super(start, stop);
      this.javaOffset = javaOffset;
      this.javaLength = javaLength;
      this.jetItem = jetItem;
    }

    public final int getJavaOffset()
    {
      return javaOffset;
    }

    public final int getJavaLength()
    {
      return javaLength;
    }

    public final JETItem getJETItem()
    {
      return jetItem;
    }

    public final JETJavaRange subrange(int startOffset)
    {
      return new JETJavaRange(getStart(), getStop(), javaOffset + startOffset, javaLength - startOffset, jetItem);
    }

    public boolean isDirectMapping()
    {
      return jetItem == null || !(jetItem instanceof JETLiteralItem);
    }
  }

  private final String className;

  private final String javaCompilationUnit;

  private final List<JETJavaRange> ranges = new ArrayList<JETJavaRange>();

  private final List<String> templateURIs;

  private final List<JETItem> jetItems;

  private final List<JETException> problems;

  public JETCompilationUnit(List<String> templateURIs, String className, String javaCompilationUnit, List<JETItem> jetItems, List<JETException> problems)
  {
    this.problems = problems;
    this.jetItems = Collections.unmodifiableList(jetItems);
    this.templateURIs = Collections.unmodifiableList(templateURIs);
    this.className = className;
    this.javaCompilationUnit = javaCompilationUnit;
  }

  public String getClassName()
  {
    return className;
  }

  public String getJavaCompilationUnit()
  {
    return javaCompilationUnit;
  }

  public List<JETJavaRange> getRanges()
  {
    return ranges;
  }

  public List<String> getTemplateURIs()
  {
    return templateURIs;
  }

  public List<JETItem> getItems()
  {
    return jetItems;
  }

  public List<JETException> getProblems()
  {
    return problems;
  }

  public JETDirectiveItem getJETJETDirectiveItem()
  {
    for (JETItem jetItem : jetItems)
    {
      if (jetItem instanceof JETDirectiveItem)
      {
        JETDirectiveItem jetDirectiveItem = (JETDirectiveItem)jetItem;
        JETItem nameItem = jetDirectiveItem.getNameItem();
        String name = nameItem.getText();
        if ("jet".equals(name))
        {
          return jetDirectiveItem;
        }
      }
    }
    return null;
  }

  public JETItem getJETItem(int fileID, int templateOffset, boolean leaf)
  {
    for (JETItem jetItem : jetItems)
    {
      if (jetItem.getFileID() == fileID)
      {
        if (jetItem.getStartOffset() <= templateOffset && jetItem.getStopOffset() >= templateOffset)
        {
          JETItem result = jetItem;
          if (leaf)
          {
            JETSubItem leafItem = jetItem.getLeaf(fileID, templateOffset);
            if (leafItem != null)
            {
              result = leafItem;
            }
          }
          return result;
        }
      }
    }

    return null;
  }

  public int[] getCorrespondingJavaPoint(int fileID, int templateOffset, int templateLength)
  {
    int templateStop = templateOffset + templateLength;
    for (JETJavaRange range : ranges)
    {
      if (trace)
      {
        String java = javaCompilationUnit.substring(range.getJavaOffset(), range.getJavaOffset() + range.getJavaLength());
        System.out.println("java > " + java);
      }

      if (range.getFileID() == fileID)
      {
        int rangeStartOffset = range.getStartOffset();
        int rangeStopOffset = range.getStopOffset();
        if (rangeStopOffset >= templateOffset && templateStop >= rangeStartOffset)
        {
          int javaOffset = range.getJavaOffset();
          int javaLength = range.getJavaLength();
          int javaStop = javaOffset + javaLength;
          if (templateLength == -1 || !range.isDirectMapping())
          {
            return new int []{ javaOffset, javaLength };
          }
          else
          {
            int delta = javaOffset - rangeStartOffset;
            int templateJavaStop = templateStop + delta;
            int javaStopBoundary = Math.min(javaStop, templateJavaStop);
            int javaStart = Math.max(javaOffset, templateOffset + delta);
            return new int []{ javaStart, javaStopBoundary - javaStart };
          }
        }
      }
    }

    return null;
  }

  public int getCorrespondingTemplateFileID(int javaOffset)
  {
    for (JETJavaRange range : ranges)
    {
      if (trace)
      {
        dump(range);
      }

      if (range.getJavaOffset() <= javaOffset && javaOffset <= range.getJavaOffset() + range.getJavaLength())
      {
        return range.getFileID();
      }
    }

    return -1;
  }

  public int[] getCorrespondingTemplatePoint(int fileID, int javaOffset, int javaLength)
  {
    for (JETJavaRange range : ranges)
    {
      int rangeJavaOffset = range.getJavaOffset();
      int rangeJavaLength = range.getJavaLength();
      if (rangeJavaOffset <= javaOffset + javaLength && javaOffset <= rangeJavaOffset + rangeJavaLength)
      {
        JETMark initialStart = range.getStart();
        for (JETMark start = initialStart; start != null; start = start.getParentMark())
        {
          if (start.getFileId() == fileID)
          {
            JETItem jetItem = start == initialStart ? range.getJETItem() : getJETItem(fileID, start.getCursor(), true);

            int templateStart = jetItem.getStartOffset();
            int templateStop = jetItem.getStopOffset();
            if (jetItem instanceof JETJavaItem && !(jetItem instanceof JETLiteralItem))
            {
              JETJavaItem jetJavaItem = (JETJavaItem)jetItem;
              int itemJavaOffset = jetJavaItem.getJavaOffset();
              int itemJavaLength = jetJavaItem.getJavaLength();

              int templateOffset = Math.max(0, javaOffset - itemJavaOffset);
              int length = Math.max(0, Math.min(itemJavaLength - templateOffset, javaLength));
              return new int []{ jetJavaItem.getStartOffset() + templateOffset, length };
            }
            else
            {
              return new int []{ templateStart, templateStop - templateStart };
            }
          }
        }
        break;
      }

      if (rangeJavaOffset + rangeJavaLength > javaOffset)
      {
        break;
      }
    }

    return null;
  }

  public int[] getClosestCorrespondingTemplatePoint(int fileID, int javaOffset, int javaLength)
  {
    JETJavaRange jetRange = getJETRange(javaOffset, javaLength);
    if (jetRange != null)
    {
      JETMark start = jetRange.getStart();
      for (JETMark jetMark = start; jetMark != null; jetMark = jetMark.getParentMark())
      {
        if (jetMark.getFileId() == fileID)
        {
          if (jetMark == start)
          {
            int rangeJavaOffset = jetRange.getJavaOffset();
            int offset = javaOffset - rangeJavaOffset;
            int templateOffset = start.getCursor();
            int templateJavaOffset = templateOffset + offset;
            int templateJavaLength = Math.min(javaLength, jetRange.getStopOffset() - templateJavaOffset);
            return new int []{ templateJavaOffset, templateJavaLength };
          }
          else
          {
            JETItem jetItem = getJETItem(fileID, jetMark.getCursor(), false);
            if (jetItem == null)
            {
              return new int []{ jetMark.getCursor(), 0 };
            }
            else
            {
              int templateOffset = jetItem.getStartOffset();
              return new int []{ templateOffset, jetItem.getStopOffset() - templateOffset };
            }
          }
        }
      }
    }

    if (javaCompilationUnit.length() >= javaOffset + javaLength)
    {
      if (trace)
      {
        System.out.println("java > " + javaCompilationUnit.substring(javaOffset, javaOffset + javaLength));
      }

      try
      {
        if (fileID != 0)
        {
          JETJavaRange startRange = null;
          JETJavaRange stopRange = null;
          for (JETJavaRange range : ranges)
          {
            if (range.getFileID() == fileID)
            {
              if (startRange == null)
              {
                startRange = range;
              }
              stopRange = range;
            }
          }

          if (startRange != null)
          {
            if (startRange.getJavaOffset() > javaOffset + javaLength)
            {
              // This file was processed after this content was produced.
              return null;
            }
            else
            {
              int stop = stopRange.getJavaOffset() + stopRange.getJavaLength();
              if (stop < javaOffset)
              {
                // This file was processed before this content was produced.
                return null;
              }
            }
          }
        }

        JETJavaRange previousRange = null;
        for (JETJavaRange range : ranges)
        {
          if (trace)
            dump(range);
          int rangeJavaOffset = range.getJavaOffset();
          if (previousRange == null && javaOffset < rangeJavaOffset)
          {
            return new int []{ 0, 0 };
          }

          int rangeJavaLength = range.getJavaLength();
          int offset = javaOffset - rangeJavaOffset;
          if (offset >= 0 && javaOffset <= rangeJavaOffset + rangeJavaLength)
          {
            if (range.getFileID() == fileID)
            {
              int templateOffset = range.getStartOffset();
              int templateJavaOffset = templateOffset + offset;
              int templateJavaLength = Math.min(javaLength, range.getStopOffset() - templateJavaOffset);
              if (templateJavaLength < 0)
              {
                templateJavaOffset += templateJavaLength;
                if (templateJavaOffset < 0)
                {
                  templateJavaOffset = 0;
                }
                templateJavaLength = 0;
              }
              return new int []{ templateJavaOffset, templateJavaLength };
            }
          }
          else if (offset < 0)
          {
            if (range.getFileID() == fileID)
            {
              // Mark the preceding character.
              int templateOffset = range.getStartOffset();
              return new int []{ templateOffset - 1, 1 };
            }
          }

          if (range.getFileID() == fileID)
          {
            previousRange = range;
          }
        }

        if (previousRange != null)
        {
          int templateOffsetOfStop = previousRange.getStopOffset();
          // Mark the closing %>
          return new int []{ templateOffsetOfStop - 2, 2 };
        }
      }
      catch (RuntimeException exception)
      {
        // If we have an error in processing, then just mark the start of template.
      }
    }

    return new int []{ 0, 0 };
  }

  public JETItem getJETItem(int javaOffset, int javaLength)
  {
    JETJavaRange jetRange = getJETRange(javaOffset, javaLength);
    return jetRange == null ? null : jetRange.getJETItem();
  }

  public JETJavaRange getJETRange(int javaOffset, int javaLength)
  {
    for (JETJavaRange range : ranges)
    {
      int rangeJavaOffset = range.getJavaOffset();
      int rangeJavaLength = range.getJavaLength();
      if (rangeJavaOffset <= javaOffset && javaOffset + javaLength <= rangeJavaOffset + rangeJavaLength)
      {
        return range;
      }
    }

    return null;
  }

  public List<JETJavaItem> getJavaItems(int fileID)
  {
    return getJavaItems(fileID, -1, -1);
  }

  public List<JETJavaItem> getJavaItems(int fileID, int templateOffset, int templateLength)
  {
    return accumulate(new JETItemIterator<JETJavaItem>(jetItems, JETJavaItem.class, fileID));
  }

  public List<JETRootItem> getSkippedItems(int fileID)
  {
    return getSkippedItems(fileID, -1, -1);
  }

  public List<JETRootItem> getSkippedItems(int fileID, int templateOffset, int templateLength)
  {
    return accumulate(new JETItemIterator<JETRootItem>(jetItems, JETRootItem.class, fileID, templateOffset, templateLength)
      {
        @Override
        protected boolean isIncluded(JETRootItem item)
        {
          return item.isSkipped();
        }
      });
  }

  JETJavaRange addRange(JETMark start, JETMark stop, int javaOffset, int javaLength, JETItem jetItem)
  {
    JETJavaRange jetRange = new JETJavaRange(start, stop, javaOffset, javaLength, jetItem);
    ranges.add(jetRange);
    return jetRange;
  }

  private <E extends JETItem> List<E> accumulate(Iterator<E> contents)
  {
    List<E> result = new ArrayList<E>();
    while (contents.hasNext())
    {
      result.add(contents.next());
    }
    return result;
  }

  private void dump(JETJavaRange range)
  {
    String java = javaCompilationUnit.substring(range.getJavaOffset(), range.getJavaOffset() + range.getJavaLength());
    System.out.println("scanning > " + java);
  }

  @SuppressWarnings("unused")
  private void dumpRanges()
  {
    for (JETJavaRange range : ranges)
    {
      String text = range.getText();
      System.out.println(">>" + text.replaceAll("\r?\n", "<<\n>>") + "<<\n");
      String javaText = javaCompilationUnit.substring(range.getJavaOffset(), range.getJavaOffset() + range.getJavaLength());
      System.out.println("%>" + javaText.replaceAll("\r?\n", "<%\n%>") + "<%\n");

      if (!text.equals(javaText))
      {
        System.err.println("####mismatch");
      }
    }
  }

  private class JETItemIterator<E extends JETItem> implements Iterator<E>
  {
    private final Iterator<JETItem> delegate;

    private final Class<E> type;

    private final int fileID;

    private final int offset;

    private final int endOffset;

    private E nextItem;

    private boolean isPrepared;

    public JETItemIterator(Iterable<JETItem> delegate, Class<E> type, int fileID)
    {
      this(delegate.iterator(), type, fileID);
    }

    public JETItemIterator(Iterator<JETItem> delegate, Class<E> type, int fileID)
    {
      this(delegate, type, fileID, -1, -1);
    }

    public JETItemIterator(Iterable<JETItem> delegate, Class<E> type, int fileID, int offset, int length)
    {
      this(delegate.iterator(), type, fileID, offset, length);
    }

    public JETItemIterator(Iterator<JETItem> delegate, Class<E> type, int fileID, int offset, int length)
    {
      this.delegate = delegate;
      this.type = type;
      this.fileID = fileID;
      this.offset = offset;
      this.endOffset = offset + length;
    }

    private boolean basicIsIncluded(E item)
    {
      if (fileID == -1)
      {
        return isIncluded(item);
      }

      if (item.getFileID() != fileID)
      {
        return false;
      }

      if (offset < 0)
      {
        return isIncluded(item);
      }

      if (item.getStartOffset() > endOffset)
      {
        return false;
      }

      JETMark stop = item.getStop();
      int stopCursor = stop.getCursor();
      if (stopCursor < offset)
      {
        return false;
      }

      return isIncluded(item);
    }

    protected boolean isIncluded(E item)
    {
      return true;
    }

    public boolean hasNext()
    {
      while (delegate.hasNext())
      {
        JETItem item = delegate.next();
        if (type.isInstance(item))
        {
          E typedItem = type.cast(item);
          if (basicIsIncluded(typedItem))
          {
            nextItem = typedItem;
            isPrepared = true;
            return true;
          }
        }
      }
      return false;
    }

    public E next()
    {
      if (!isPrepared && !hasNext())
      {
        throw new NoSuchElementException();
      }

      return nextItem;
    }

    public void remove()
    {
      throw new UnsupportedOperationException("remove");
    }
  }
}
