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
import java.util.List;


/**
 * @since 2.19
 */
public class JETCommentItem extends JETRootItem
{
  private List<JETSubItem> lineItems;

  JETCommentItem(JETMark start, JETMark stop)
  {
    super(start, stop);
  }

  void addLineItem(int start, int stop)
  {
    JETMark commentStart = getStart();

    JETReader reader = commentStart.reader;
    JETMark mark = reader.mark();
    reader.reset(commentStart);
    int index = 0;
    while (index < start)
    {
      reader.nextChar();
      ++index;
    }

    JETMark offsetStart = reader.mark();

    while (index < stop)
    {
      reader.nextChar();
      ++index;
    }

    JETMark offsetEnd = reader.mark();
    reader.reset(mark);

    if (lineItems == null)
    {
      lineItems = new ArrayList<JETSubItem>();
    }

    JETSubItem lineItem = new JETSubItem(offsetStart, offsetEnd);
    lineItem.setParent(this);
    lineItems.add(lineItem);
  }

  public List<JETSubItem> getLineItems()
  {
    return lineItems == null ? Collections.<JETSubItem> emptyList() : Collections.unmodifiableList(lineItems);

  }

  @Override
  public List<JETSubItem> getChildren()
  {
    return getLineItems();
  }
}
