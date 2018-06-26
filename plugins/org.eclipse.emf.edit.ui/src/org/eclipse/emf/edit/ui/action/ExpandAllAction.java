/*
 * Copyright (c) Eclipse contributors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.edit.ui.action;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IWorkbenchPart;


/**
 * An action for expanding all items in a {@link TreeViewer tree viewer}.
 * It also provides a general utility method, {@link #expandAll(TreeViewer, long)} with specialized behavior for expanding large trees.
 *
 * @since 2.14
 */
public final class ExpandAllAction extends Action
{
  /**
   * The provider of the tree viewer.
   */
  private IViewerProvider viewerProvider;

  /**
   * How many milliseconds to spend expanding the tree.
   */
  private long timeout;

  /**
   * Creates an instance that will {@link #expandAll(TreeViewer, long) expand} for 2000ms.
   */
  public ExpandAllAction()
  {
    this(2000);
  }

  /**
   * Creates an instance that will {@link #expandAll(TreeViewer, long) expand} for {@code timeout} milliseconds.
   */
  public ExpandAllAction(long timeout)
  {
    super(
      EMFEditUIPlugin.INSTANCE.getString("_UI_ExpandAll_menu_item"),
      ExtendedImageRegistry.INSTANCE.getImageDescriptor(EMFEditUIPlugin.INSTANCE.getImage("full/ctool16/ExpandAll")));
    this.timeout = timeout;
    setToolTipText(EMFEditUIPlugin.INSTANCE.getString("_UI_ExpandAll_tool_tip"));
  }

  /**
   * {@link #expandAll(TreeViewer, long) Expands} the tree for the {@link #timeout} milliseconds.
   */
  @Override
  public void run()
  {
    Viewer viewer = viewerProvider.getViewer();
    if (viewer instanceof TreeViewer)
    {
      expandAll((TreeViewer)viewer, timeout);
    }
  }

  /**
   * Does essentially the same thing as {@link TreeViewer#expandAll()}
   * but it limits the amount of time spent expanding to the specified number of milliseconds.
   * This allows the operation to be applied to very large trees, and even to infinite trees.
   * Because the operation time is limited, the expansion is done in a specific order.
   * I.e., expansion starts with the selection or, if there isn't one, with the {@link Tree#getTopItem() top} visible item.
   * This ensures that what the user has selected or actually sees is expanded first.
   * Time permitting, the siblings around these items are expanded first.
   * Given sufficient time, this will expand the full tree, but will generally expand the part of the tree the user sees.
   * Redraw is disabled during the expansion process.
   * 
   * @param treeViewer the tree viewer to operate upon.
   * @param timeout the number of milliseconds to spend on expanding items before timing out.
   */
  public static void expandAll(TreeViewer treeViewer, final long timeout)
  {
    final Tree tree = treeViewer.getTree();
    final TreeItem[] selection = tree.getSelection();
    final TreeItem topItem = tree.getTopItem();

    // We're most interested in expanding the selection if there is one.
    // Failing that, it makes the most sense to start expanding to top most visible element instead.
    //
    TreeItem[] itemsOfInterest;
    if (selection.length == 0 && topItem != null)
    {
      itemsOfInterest = new TreeItem []{ topItem };

    }
    else
    {
      itemsOfInterest = selection;
    }

    // We need the root items repeatedly because these are the siblings of items with a null parent.
    List<TreeItem> rootItems = Arrays.asList(tree.getItems());

    // We'll keep a list of lists because we'd first like to expand all the selected elements,
    // then their closest siblings, then the closest siblings of the parent, and so on.
    // The first list in this list will be for the items of interest directly.
    //
    List<List<TreeItem>> prioritizedItems = new ArrayList<List<TreeItem>>();
    prioritizedItems.add(new ArrayList<TreeItem>());

    // Process each item of interest.
    //
    for (TreeItem treeItem : itemsOfInterest)
    {
      // Add it to the first list.
      prioritizedItems.get(0).add(treeItem);

      // Populate the list of each parent, walking up the tree.
      // The count keeps track of which list to populate during each iteration to an parent.
      //
      int count = 1;
      for (TreeItem parentItem = treeItem.getParentItem(), childItem = treeItem;; childItem = parentItem, parentItem = parentItem.getParentItem(), ++count)
      {
        // If there is no list at the count yet, add one, otherwise reuse it.
        //
        List<TreeItem> list;
        if (count >= prioritizedItems.size())
        {
          list = new ArrayList<TreeItem>();
          prioritizedItems.add(list);
        }
        else
        {
          list = prioritizedItems.get(count);
        }

        // The siblings are either the items of the parent, or the root items.
        //
        List<TreeItem> siblingItems;
        if (parentItem != null)
        {
          siblingItems = Arrays.asList(parentItem.getItems());
        }
        else
        {
          siblingItems = rootItems;
        }

        // Add all the siblings after the child item to the list.
        //
        int index = siblingItems.indexOf(childItem);
        for (int i = index + 1, size = siblingItems.size(); i < size; ++i)
        {
          // Ignore siblings that don't have items (aren't expandable).
          //
          TreeItem siblingItem = siblingItems.get(i);
          if (siblingItem.getItemCount() > 0)
          {
            list.add(siblingItem);
          }
        }

        // Add all the siblings before the child item to the list in reverse order.
        //
        for (int i = index - 1; i >= 0; --i)
        {
          // Ignore siblings that don't have items (aren't expandable).
          TreeItem siblingItem = siblingItems.get(i);
          if (siblingItem.getItemCount() > 0)
          {
            list.add(siblingItem);
          }
        }

        // When we've reached the root siblings, so we're done.
        //
        if (parentItem == null)
        {
          break;
        }
      }
    }

    // Add all the items to a set, in prioritized order.
    //
    final Set<TreeItem> itemsToBeExpanded = new LinkedHashSet<TreeItem>();
    for (List<TreeItem> list : prioritizedItems)
    {
      itemsToBeExpanded.addAll(list);
    }

    // Do the expansion while showing a busy cursor.
    //
    BusyIndicator.showWhile(tree.getDisplay(), new Runnable()
      {
        public void run()
        {
          try
          {
            // Don't paint until we're done.
            //
            tree.setRedraw(false);

            // Create a queue for prioritized processing.
            //
            LinkedList<TreeItem> queue = new LinkedList<TreeItem>(itemsToBeExpanded);

            // Only process for a fixed period of time.
            //
            long start = System.currentTimeMillis();
            while (!queue.isEmpty())
            {
              TreeItem treeItem = queue.poll();

              // Force the creation of children, and then expand the item, if it's not already expanded.
              //
              if (!treeItem.getExpanded())
              {
                Event expandEvent = new Event();
                expandEvent.item = treeItem;
                tree.notifyListeners(SWT.Expand, expandEvent);
                treeItem.setExpanded(true);
              }

              // Add the children in reverse order for processing in the queue.
              //
              TreeItem[] childItems = treeItem.getItems();
              for (int i = childItems.length - 1; i >= 0; --i)
              {
                TreeItem childItem = childItems[i];

                // If it's not a placeholder item and we haven't already added this item to the queue, 
                // then add it at the front for high priority processing.
                //
                if (childItem.getData() != null && itemsToBeExpanded.add(childItem))
                {
                  queue.addFirst(childItem);
                }
              }

              // If we've been busy for more than timeout milliseconds, stop expanding.
              //
              if (System.currentTimeMillis() - start > timeout)
              {
                break;
              }
            }
          }
          finally
          {
            // Scroll to the previous top item.
            //
            tree.setTopItem(topItem);
            if (selection.length != 0)
            {
              // Force the selection to scroll into view.
              //
              tree.setSelection(selection);
            }

            // Enable drawing again.
            //
            tree.setRedraw(true);
          }
        }
      });
  }

  /**
   * Sets the current active workbench part.
   * The action is enabled only if the workbench part is a {@link IViewerProvider} that yields a {@link TreeViewer}.
   * @param workbenchPart the current active workbench part.
   * @see EditingDomainActionBarContributor#activate()
   * @see EditingDomainActionBarContributor#deactivate()
   */
  public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart)
  {
    if (workbenchPart instanceof IViewerProvider)
    {
      viewerProvider = (IViewerProvider)workbenchPart;
      setEnabled(viewerProvider.getViewer() instanceof TreeViewer);
    }
    else
    {
      viewerProvider = null;
      setEnabled(false);
    }
  }

  /**
   * Updates the action.
   * The action is enabled only if the workbench part is a {@link IViewerProvider} that yields a {@link TreeViewer}.
   * 
   * @see EditingDomainActionBarContributor#update()
   */
  public void update()
  {
    setEnabled(viewerProvider != null && viewerProvider.getViewer() instanceof TreeViewer);
  }
}