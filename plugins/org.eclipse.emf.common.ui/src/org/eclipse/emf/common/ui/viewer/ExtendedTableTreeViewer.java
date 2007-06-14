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
 * $Id: ExtendedTableTreeViewer.java,v 1.4 2007/06/14 18:32:41 emerks Exp $
 */
package org.eclipse.emf.common.ui.viewer;


import java.util.LinkedList;

import org.eclipse.jface.viewers.TableTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.custom.TableTreeItem;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;


/**
 * This class extends a TableTreeViewer to draw images and tree lines in the tree column.
 */
@Deprecated
public class ExtendedTableTreeViewer extends TableTreeViewer
{
  public static final String ITEM_ID = "TableTreeItemID"; 

  public ExtendedTableTreeViewer(TableTree tableTree)
  {
    super(tableTree);
  }

  public ExtendedTableTreeViewer(Composite parent)
  {
    super(parent);
  }

  public ExtendedTableTreeViewer(Composite parent, int style)
  {
    super(parent, style);
  }

  @Override
  protected Item newItem(Widget parent, int flags, int index) 
  {
    TableTreeItem item = 
      index >= 0 ?
        parent instanceof TableTreeItem ?
          new ExtendedTableTreeItem((TableTreeItem) parent, flags, index) :
          new ExtendedTableTreeItem((TableTree) parent, flags, index) :
        parent instanceof TableTreeItem ?
          new ExtendedTableTreeItem((TableTreeItem) parent, flags) :
          new ExtendedTableTreeItem((TableTree) parent, flags);

    return item;
  }

  // We cache the dimensions of the expand/collapse icon as soon as we find
  // them.  They shouldn't ever change, but on GTK, they may be incorrectly reported
  // as 0 when the mouse pointer moves down into or up out of a leaf item.
  // Also on GTK, they're originally reported as 0, so we need the
  // default-value hack.  
  // See Bugzilla 42434.
  //
  protected Point interactorSize = new Point(12, 12);
  protected boolean interactorFound = false;

  @Override
  protected void hookControl(Control control)
  {
    super.hookControl(control);

/*
    getTableTree().getTable().addPaintListener
      (new PaintListener()
       {
         public void paintControl(PaintEvent event)
         {
           if (event.count > 0)
           {
             Thread.dumpStack();
           }
         }
       });
*/
    getTableTree().getTable().addPaintListener
      (new PaintListener()
       {
         protected boolean isStarted;
         protected TableTreeItem firstTableTreeItem;
         protected TableTreeItem lastTableTreeItem;
         protected LinkedList<TableTreeItem> chain; 
         protected int scrollX;

         /**
          * On GTK, PaintEvent.gc has its origin below the Table's header,
          * instead of above it, as on other platforms.  This adjusts for
          * that, mutating and returning the given Rectangle.
          * See Bugzilla 42416.
          */
         protected Rectangle fixForGC(Rectangle bounds)
         {
           if (isGTK() && bounds != null)
           {
             bounds.x -= 2;
             bounds.y -= getTableTree().getTable().getHeaderHeight();
           }
           return bounds;
         }

         public void paintControl(PaintEvent event)
         {
           // System.out.println("Painting....." + event + " x=" + event.x + " y=" + event.y + " width=" + event.width + " height=" + event.height);
           // if (true) return;

           Table table = (Table)event.getSource();
           TableItem[] items = table.getItems();

           firstTableTreeItem = null;
           lastTableTreeItem = null;

           for (int i = table.getTopIndex(); i < items.length; i++)
           {
             TableItem tableItem = items[i];
             ExtendedTableTreeItem tableTreeItem = (ExtendedTableTreeItem)tableItem.getData(ITEM_ID);
             if (!tableTreeItem.isDisposed())
             {
               if (firstTableTreeItem == null)
               {
                 firstTableTreeItem = tableTreeItem;
               }
               lastTableTreeItem = tableTreeItem;
  
               if (!interactorFound)
               {
                 Rectangle bounds = tableItem.getImageBounds(0);
                 if (bounds.width != 0)
                 {
                   interactorFound = true;
                   interactorSize = new Point(bounds.width, bounds.height);
                 }
               }

               Rectangle itemBounds = tableTreeItem.getBounds(0);
               if (itemBounds != null)
               {
                 Image image = tableTreeItem.getFirstImage();
                 if (image != null)
                 {
                   // Paint over the selected padding spaces with the
                   // background colour.  On GTK, the whole item, not just
                   // the text, is selected, so we don't do this.
                   //
                   if (!isGTK())
                   {
                     // On Motif, selection color may be set as background.
                     //
                     Display display = tableItem.getDisplay();
                     event.gc.setBackground(
                       display.getSystemColor(SWT.COLOR_LIST_BACKGROUND));

                     Rectangle bounds = tableTreeItem.getImageBounds(tableItem, 0);
                     bounds.x += bounds.width;
                     bounds.y = itemBounds.y;
                     bounds.width = imagePaddingWidth - 1;
                     bounds.height = itemBounds.height;
                     event.gc.fillRectangle(fixForGC(bounds));
                   }

                   // Draw the extra first-column image.
                   //
                   Rectangle sourceBounds = image.getBounds();
                   Rectangle targetBounds =
                     fixForGC(tableTreeItem.getFirstImageBounds(tableItem));

                   event.gc.drawImage(image, sourceBounds.x, sourceBounds.y, sourceBounds.width, sourceBounds.height, targetBounds.x, targetBounds.y, targetBounds.width, targetBounds.height);
                 }

                 // Stop if the next item will be out the event bounds.
                 // The event bounds values are also misaligned on GTK.
                 //
                 fixForGC(itemBounds);
                 if (itemBounds.y + itemBounds.height > event.y + event.height)
                 {

                   break;
                 }
               }
             }
           }

           // If the table is indenting, draw tree lines.
           //
           if (firstTableTreeItem != null && isIndenting())
           {
             isStarted = false;
             chain = new LinkedList<TableTreeItem>();
             event.gc.setForeground(table.getDisplay().getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
             scrollX = items[0].getBounds(0).x;
             paintLines(event.gc, getTableTree().getItems());
           }
         }

         private boolean indenting = false;
         private boolean indentingTested = false;
         
         /**
          * Tests whether the table is drawing child items indented compared
          * to their parents.  This will return false while the tree is still
          * totally collapsed.
          */
         protected boolean isIndenting()
         {
           if (!indentingTested)
           {
             TableItem[] items = getTableTree().getTable().getItems();
             if (items.length > 1)
             {
               int x = items[0].getBounds(0).x;
               for (int i = 1, len = items.length; i < len; i++)
               {
                 if (items[i].getBounds(0).x != x)
                 {
                   indenting = true;
                 }

                 TableTreeItem item = (TableTreeItem)items[i].getData(ITEM_ID);
                 if (item.getParentItem() != null)
                 {
                   indentingTested = true;
                   break;
                 }
               }
             }
           }
           return indenting;
         }

         protected boolean paintLines(GC gc, TableTreeItem [] tableTreeItems)
         {
           int offset = interactorSize.x;
           int indent = Math.min(6, (offset - 8) / 2);

           if (tableTreeItems != null)
           {
             for (int i = 0; i < tableTreeItems.length; ++i)
             {
               TableTreeItem tableTreeItem = tableTreeItems[i];
               if (!isStarted && tableTreeItem == firstTableTreeItem)
               {
                 isStarted = true;
               }
               if (isStarted)
               {
                 Rectangle bounds = tableTreeItem.getBounds(0);
                 int x = 1 + scrollX;

                 for (TableTreeItem ancestor : chain)
                 {
                   if (ancestor != null)
                   {
                     gc.drawLine(x + offset/2, bounds.y, x + offset/2, bounds.y + bounds.height);
                   }
                   x += offset;
                 }

                 if (i + 1 == tableTreeItems.length)
                 {
                   if (i != 0 || !chain.isEmpty())
                   {
                     gc.drawLine
                       (x + offset/2, bounds.y, 
                        x + offset/2, bounds.y + (tableTreeItem.getItemCount() > 0 ? indent - 1 : bounds.height/2));
                   }
                 }
                 else
                 {
                   if (tableTreeItem.getItemCount() > 0)
                   {
                     gc.drawLine
                       (x + offset/2, bounds.y, x + offset/2, bounds.y + indent - 1);
                     gc.drawLine
                       (x + offset/2, bounds.y + bounds.height - indent + 2, x + offset/2, bounds.y + bounds.height);
                   }
                   else
                   {
                     gc.drawLine(x + offset/2, bounds.y, x + offset/2, bounds.y + bounds.height);
                   }
                 }

                 gc.drawLine
                   (x + (tableTreeItem.getItemCount() > 0 ? offset - indent + 1 : offset/2), bounds.y + (bounds.height + 1)/2, 
                    x + offset + 2, bounds.y + (bounds.height + 1)/2);

               }
               if (tableTreeItem.getExpanded())
               {
                 chain.addLast(i + 1 == tableTreeItems.length ? null : tableTreeItem);
                 if (!paintLines(gc, tableTreeItem.getItems()))
                 {
                   return false;
                 }
                 chain.removeLast();
               }
               if (isStarted && tableTreeItem == lastTableTreeItem)
               {
                 return false;
               }
             }
           }

           return true;
         }
       });
  }

  protected String imagePadding;
  protected int imagePaddingWidth;

  protected void createImagePadding(int width)
  {
    GC gc = new GC(getTableTree().getTable());
    imagePadding = " ";
    while ((imagePaddingWidth = gc.stringExtent(imagePadding).x) < width + 6)
    {
      imagePadding += " ";
    }
    gc.dispose();

    TableItem [] tableItems = getTableTree().getTable().getItems();
    for (int i = 0; i < tableItems.length; ++i)
    {
      TableTreeItem tableTreeItem = (TableTreeItem)tableItems[i].getData(ITEM_ID);
      tableTreeItem.setText(0, tableTreeItem.getText(0));
    }
  }

  /**
   * Returns whether GTK is the current platform.  Special treatment is
   * needed for GTK in drawing on the table.
   */
  protected static boolean isGTK()
  {
    return "gtk".equals(SWT.getPlatform());
  }

  /**
   * This is a convenient way to get image bound values that are corrected
   * on GTK.  If the given TableItem underlies an ExtendedTableTreeItem,
   * getImageBounds() is called on that ExtendedTableTreeItem.  Otherwise,
   * it is called directory on the TableItem.
   * See Bugzilla 42434.
   */
  public static Rectangle getImageBounds(TableItem tableItem, int column)
  {
    Object item = tableItem.getData(ITEM_ID);
    return item instanceof ExtendedTableTreeItem ?
      ((ExtendedTableTreeItem)item).getImageBounds(tableItem, column) :
      tableItem.getImageBounds(column);
  }

  /**
   * Centers the Rectangle vertically, within a surrounding space of
   * the given height.  The given Rectangle is changed and returned.
   */
  protected static Rectangle center(Rectangle bounds, int maxHeight)
  {
    if (bounds.height < maxHeight)
    {
      bounds.y += (maxHeight - bounds.height) / 2;
    }      
    return bounds;
  }

  /**
   * Scales the Rectangle, mainting its aspect, such that it fits within the
   * given height.  The given Rectangle is changed and returned.
   */
  protected static Rectangle scale(Rectangle bounds, int maxHeight)
  {
    if (bounds.height > maxHeight)
    {
      float sf = (float)bounds.width / (float)bounds.height;
      bounds.width = Math.round(sf * maxHeight);
      bounds.height = maxHeight;
    }
    return bounds;
  }

  public class ExtendedTableTreeItem extends TableTreeItem
  {
    protected Image firstImage;

    public ExtendedTableTreeItem(TableTree parent, int style)
    {
      super(parent, style);
    }

    public ExtendedTableTreeItem(TableTree parent, int style, int index)
    {
      super(parent, style, index);
    }

    public ExtendedTableTreeItem(TableTreeItem parent, int style)
    {
      super(parent, style);
    }

    public ExtendedTableTreeItem(TableTreeItem parent, int style, int index)
    {
      super(parent, style, index);
    }

    @Override
    public void setText(int index, String text)
    {
      // System.out.println("setting the text " + index + " " + text + " " + getImage(index));
      if (index == 0 && imagePadding != null)
      {
        if (text != null && text.indexOf(imagePadding) == 0)
        {
          super.setText(0, text);
        }
        else
        {
          super.setText(0, imagePadding + text);
        }
      }
      else
      {
        super.setText(index, text);
      }
    }

    @Override
    public String getText(int index)
    {
      String result = super.getText(index);
      if (index == 0 && result != null && imagePadding != null && result.indexOf(imagePadding) == 0)
      {
        result = result.substring(imagePadding.length());
      }

      return result;
    }

    @Override
    public void setImage(int index, Image image)
    {
      if (index == 0)
      {
        firstImage = image;
        if (image != null && imagePadding == null) 
        {
          createImagePadding(image.getBounds().width);
        }
      }
      else
      {
        super.setImage(index, image);
      }
    }

    /**
     * Returns the additional first image, which would have been set
     * by setImage(..., 0).
     */
    public Image getFirstImage()
    {
      return firstImage;
    }

    public int getImagePaddingWidth()
    {
      return imagePaddingWidth;
    }

    /**
     * This is equivalent to TableItem.getImageBounds(), except that it
     * gives corrected values on GTK.
     * See Bugzilla 42434.
     */
    public Rectangle getImageBounds(int column)
    {
      return getImageBounds(getTableItem(), column);
    }

    /**
     * Because getImageBounds() needs to obtain the underlying TableItem,
     * this form is provided for speedy internal use when we've already
     * got a handle on it.
     */
    private Rectangle getImageBounds(TableItem tableItem, int column)
    {
      if (isGTK())
      {
        Rectangle result = tableItem.getBounds(column);
        int itemHeight = result.height;
        
        if (column == 0)
        {
          result.width = interactorSize.x;
          result.height = interactorSize.y;
        }
        else
        {
          Image image = tableItem.getImage(column);
          if (image == null)
          {
            result.width = 0;
            result.height = 0;
          }
          else
          {
            Rectangle imageBounds = image.getBounds();
            result.width = imageBounds.width;
            result.height = imageBounds.height;
          }
        }
        center(result, itemHeight);
        result.x += 3;
        
        return result;
      }
      
      return tableItem.getImageBounds(column);
    }

    /**
     * Returns the bounds of the additional first image, which would have
     * been set by setImage(..., 0).
     */
    public Rectangle getFirstImageBounds()
    {
      return getFirstImageBounds(getTableItem());
    }

    /**
     * Because getFirstImageBounds() needs to obtain the underlying
     * TableItem, this form is provided for speedy internal use when we've
     * already got a handle on it.
     */
    private Rectangle getFirstImageBounds(TableItem tableItem)
    {
      Rectangle result = new Rectangle(0, 0, 0, 0);
      
      if (tableItem != null)
      {
        Rectangle itemBounds = tableItem.getBounds(0);
        Rectangle interactorBounds = getImageBounds(tableItem, 0);

        result.x = interactorBounds.x + interactorBounds.width + 5;
        result.y = itemBounds.y;

        if (firstImage != null)
        {
          Rectangle imageBounds = firstImage.getBounds();
          result.width = imageBounds.width;
          result.height = imageBounds.height;
        }

        scale(result, itemBounds.height);
        center(result, itemBounds.height);
      }

      return result;
    }

    /**
     * Returns the underlying TableItem.
     */
    protected TableItem getTableItem()
    {
      TableItem[] items = getTableTree().getTable().getItems();
      for (int i = 0; i < items.length; i++)
      {
        if (items[i].getData(ITEM_ID) == this) return items[i];
      }
      return null;
    }
  }
}