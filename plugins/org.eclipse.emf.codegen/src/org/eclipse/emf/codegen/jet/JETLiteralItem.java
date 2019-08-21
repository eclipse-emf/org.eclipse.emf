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

import org.eclipse.core.runtime.Assert;


/**
 * @since 2.19
 */
public class JETLiteralItem extends JETJavaItem
{
  private static final JETMark[] NO_TRANSITIONS = new JETMark [0];

  private final JETMark[] fileTransitionMarks;

  private List<JETLiteralItem> explodedItems;

  JETLiteralItem(JETMark start, JETMark stop, List<JETMark> fileTransitionMarks)
  {
    super(start, stop);
    this.fileTransitionMarks = fileTransitionMarks.isEmpty() ? NO_TRANSITIONS : fileTransitionMarks.toArray(new JETMark [fileTransitionMarks.size()]);
    assertHasConsistentTransitions();
  }

  JETLiteralItem(JETMark start, JETMark stop)
  {
    super(start, stop);
    fileTransitionMarks = NO_TRANSITIONS;
  }

  @Override
  protected void assertDoesNotSpanFiles()
  {
    // A literal item can span files, but then there must be file transition marks so we can explode this into multiple items.
  }

  @Override
  void setSkipped(boolean skipped)
  {
    super.setSkipped(skipped);
    if (fileTransitionMarks != NO_TRANSITIONS)
    {
      for (JETLiteralItem jetLiteralItem : explode())
      {
        jetLiteralItem.setSkipped(skipped);
      }
    }
  }

  protected List<JETLiteralItem> explode()
  {
    if (fileTransitionMarks == NO_TRANSITIONS)
    {
      return Collections.singletonList(this);
    }
    else
    {
      if (explodedItems == null)
      {
        List<JETLiteralItem> newExplodedItems = new ArrayList<JETLiteralItem>();
        JETMark previousStart = getStart();
        for (int i = 0; i < fileTransitionMarks.length; i += 2)
        {
          JETMark nextStop = fileTransitionMarks[i];
          newExplodedItems.add(new JETLiteralItem(previousStart, nextStop));
          previousStart = fileTransitionMarks[i + 1];
        }
        newExplodedItems.add(new JETLiteralItem(previousStart, getStop()));
        explodedItems = Collections.unmodifiableList(newExplodedItems);
      }

      return explodedItems;
    }
  }

  protected void assertHasConsistentTransitions()
  {
    Assert.isTrue(fileTransitionMarks.length % 2 == 0, "There must be an even number of transitions");
    if (fileTransitionMarks.length > 0)
    {
      Assert.isTrue(getStart().getFileId() == fileTransitionMarks[0].getFileId(), "A literal item's start and its first file transition must not span differnt files");

      for (int i = 1; i + 1 < fileTransitionMarks.length; i += 2)
      {
        Assert.isTrue(fileTransitionMarks[i].getFileId() == fileTransitionMarks[i + 1].getFileId(), "Intermediate file transitions must not span differnt files");
      }

      Assert.isTrue(
        getStop().getFileId() == fileTransitionMarks[fileTransitionMarks.length - 1].getFileId(),
        "A literal item's ttop and its last file transition must not span differnt files");
    }
  }
}
