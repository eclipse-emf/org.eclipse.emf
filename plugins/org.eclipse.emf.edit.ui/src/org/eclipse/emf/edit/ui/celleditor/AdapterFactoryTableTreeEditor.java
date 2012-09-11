/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.ui.celleditor;


import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.custom.TableTreeItem;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.celleditor.ExtendedTableTreeEditor;
import org.eclipse.emf.common.ui.viewer.ExtendedTableTreeViewer;
import org.eclipse.emf.common.ui.viewer.ExtendedTableTreeViewer.ExtendedTableTreeItem;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;


/**
 * This base class for implementing {@link org.eclipse.swt.custom.TableTreeEditor}s that 
 * delegate to adapters produced by an {@link AdapterFactory}.
 * This API is under construction; please do not use it for anything more than experimentation.
 * @deprecated 
 * @see AdapterFactoryTreeEditor
 */
@Deprecated
public class AdapterFactoryTableTreeEditor extends ExtendedTableTreeEditor
{
  protected AdapterFactory adapterFactory;
  protected AdapterFactoryItemDelegator itemDelegator;

  protected TableItem currentTableItem;
  protected TableTreeItem currentTableTreeItem;
  protected Object currentTableTreeItemData;
  protected int currentColumn;

  protected Image leftGradient;
  protected Image rightGradient;

  protected Composite canvas;

  protected KeyListener keyListener;
  protected PaintListener paintListener;

  protected Control activeEditor;

  protected boolean isDown;
  protected boolean hasDropDown;
  protected boolean hasLaunched;

  public AdapterFactoryTableTreeEditor(TableTree tableTree, AdapterFactory adapterFactory)
  {
    super(tableTree);
    this.adapterFactory = adapterFactory;
    this.itemDelegator = new AdapterFactoryItemDelegator(adapterFactory);

    keyListener = 
      new KeyAdapter()
      {
        @Override
        public void keyPressed(KeyEvent event)
        {
          if (event.character == ' ')
          {
            setDown(true);
            canvas.update();
            setDown(false);
            activate();
          }
          else if (event.character == '\r' || event.character == '\n')
          {
            setDown(true);
            canvas.update();
            setDown(false);
            activate();
          }
          else if (event.character == '\033')
          {
            dismiss();
          }
          else if (event.keyCode == SWT.ARROW_LEFT)
          {
            arrowLeft();
          }
          else if (event.keyCode == SWT.ARROW_RIGHT)
          {
            arrowRight();
          }
          else if (event.keyCode == SWT.ARROW_UP)
          {
            arrowUp();
          }
          else if (event.keyCode == SWT.ARROW_DOWN)
          {
           arrowDown();
          }
        }
      };

    paintListener =
     new PaintListener()
      {
        public void paintControl(PaintEvent event)
        {
          AdapterFactoryTableTreeEditor.this.paintControl(event);
        }
      };
  }

  public AdapterFactory getAdapterFactory()
  {
    return adapterFactory;
  }

  public void setAdapterFactory(AdapterFactory adapterFactory)
  {
    this.adapterFactory = adapterFactory;
  }

  public IItemPropertyDescriptor getColumnPropertyDescriptor(Object object, int column)
  {
    return null;
  }

  public boolean hasInPlaceEditor(Object object, int column)
  {
    return column == 0;
  }

  public boolean hasDropDownEditor(Object object, int column)
  {
    return column != 0;
  }

  public Control createDropDownEditor(Composite parent, Object object, int column)
  {
    return null;
  }

  public boolean hasLaunchedEditor(Object object, int column)
  {
    return false;
  }

  public void createLaunchedEditor(Composite parent, Object object, int column)
  {
    // Do nothing
  }

  protected void setDown(boolean isDown)
  {
    this.isDown = isDown;
    canvas.redraw();
  }

  protected boolean isDown()
  {
    return isDown;
  }

  @Override
  protected void editItem(TableItem tableItem, TableTreeItem tableTreeItem, int column)
  {
    if (getEditor() != null)
    {
      getEditor().dispose();
    }

    currentTableItem = tableItem;
    currentTableTreeItem = tableTreeItem;
    currentTableTreeItemData = tableTreeItem.getData();
    currentColumn = column;
    
    horizontalAlignment = SWT.LEFT;
    grabHorizontal = true;
    minimumWidth = Math.max(50, currentTableTreeItem.getBounds(column).width);

    hasDropDown = hasDropDownEditor(currentTableTreeItemData, currentColumn);
    hasLaunched = !hasDropDown && hasLaunchedEditor(currentTableTreeItemData, currentColumn);

    canvas = createComposite();
    canvas.addKeyListener(keyListener);
    canvas.addPaintListener(paintListener);

    canvas.addMouseListener
      (new MouseAdapter()
       {
         @Override
        public void mouseDown(MouseEvent event)
         {
           if (event.button == 1)
           {
             setDown(true);
           }
         }
         @Override
        public void mouseUp(MouseEvent event)
         {
           if (event.button == 1 && isDown())
           {
             if (currentColumn == 0 && currentTableTreeItem.getItemCount() > 0)
             {
               Rectangle imageBounds = adjust(
                 getImageBounds(currentTableItem, 0),
                 currentTableItem.getBounds(currentColumn));

               if (event.x < imageBounds.x + imageBounds.width + 3)
               {
                 currentTableTreeItem.setExpanded(!currentTableTreeItem.getExpanded());
                 Event expandEvent = new Event();
                 expandEvent.item = currentTableTreeItem;
                 currentTableTreeItem.getParent().notifyListeners(SWT.Expand, expandEvent);
               }
               else
               {
                 activate();
               }
             }
             else
             {
               activate();
             }
           }
           setDown(false);
         }
       });

    Listener listener = 
      new Listener()
      {
        public void handleEvent(Event event)
        {
          if (event.type == SWT.Activate)
          {
            // System.out.println("Activate canvas");
          }
          else if (event.type == SWT.Deactivate)
          {
            // apply();
            // System.out.println("Deactivate canvas");
          }
        }
      };

    canvas.addListener(SWT.Activate, listener);
    canvas.addListener(SWT.Deactivate, listener);
 
    setEditor(canvas, currentTableTreeItem, currentColumn);
    canvas.setFocus();
  }

  protected Composite createComposite()
  {
    return new Canvas(currentTableItem.getParent(), SWT.NULL);
  }

  protected void activate()
  {
    Rectangle itemBounds = currentTableItem.getBounds(currentColumn);
    int x = 0;

    // In column 0, the drop-down is indented past the collapse/expand icon.
    //
    if (currentColumn == 0)
    {
      Rectangle imageBounds = adjust(
        getImageBounds(currentTableItem, currentColumn), itemBounds);
      x += imageBounds.x + imageBounds.width;
    }

    Point point = currentTableItem.getParent().toDisplay(
      new Point(itemBounds.x, itemBounds.y));

    final Shell dropDown = new Shell(
      currentTableItem.getParent().getShell(), SWT.ON_TOP | SWT.NO_TRIM);
    dropDown.setBackground(
      dropDown.getDisplay().getSystemColor(SWT.COLOR_BLACK));
    dropDown.setBounds(point.x + x, point.y + itemBounds.height,  itemBounds.width - x, itemBounds.height * 5);

    final Control control = 
      hasDropDownEditor(currentTableTreeItem.getData(), currentColumn) ?
      createDropDownEditor(dropDown, currentTableTreeItem.getData(), currentColumn) :
      null;

    if (control != null)
    {
      control.setBounds(1, 1, itemBounds.width - x - 2, itemBounds.height * 5 - 2);

      dropDown.setVisible(true);
      dropDown.layout();
      control.setFocus();

      Listener dropDownListener = 
        new Listener()
        {
          public void handleEvent(Event e)
          {
            switch (e.type)
            { 
              case SWT.Close:
              {
                // System.out.println("Close *");
                cancel();
                dropDown.dispose();
                break;
              }
              case SWT.Deactivate:
              {
                // System.out.println("Deactivate *");
                apply();
                dropDown.dispose();
                break;
              }
/*
              case SWT.Paint:
              {
System.out.println("paint");
                  // draw black rectangle around list
                Rectangle listRect = control.getBounds();
                Color black = currentTableItem.getParent().getDisplay().getSystemColor(SWT.COLOR_BLACK);
                e.gc.setForeground(black);
                e.gc.drawRectangle(0, 0, listRect.width + 1, listRect.height + 1);
                break;
              }
*/
            }
          }
        };

      dropDown.addListener(SWT.Close, dropDownListener);
      // dropDown.addListener(SWT.Paint, dropDownListener);
      dropDown.addListener(SWT.Deactivate, dropDownListener);

      control.addMouseListener
        (new MouseAdapter()
         {
           @Override
          public void mouseDoubleClick(MouseEvent event)
           {
             if (event.button == 1)
             {
               apply();
               dropDown.dispose();
             }
           }
         });

      control.addKeyListener
       (new KeyAdapter()
        {
          @Override
          public void keyPressed(KeyEvent e)
          {
            if (e.character == ' ' || e.character == '\r' || e.character == '\n')
            {
              apply();
              dropDown.dispose();
            }
            else if (e.character == '\033')
            {
              cancel();
              dropDown.dispose();
            }
          }
        });
    }
    else if (hasLaunchedEditor(currentTableTreeItem.getData(), currentColumn))
    {
      createLaunchedEditor(dropDown, currentTableTreeItem.getData(), currentColumn);
    }

    activeEditor = control;
  }

  public void cancel()
  {
    activeEditor = null;
  }

  public void apply()
  {
    activeEditor = null;
    if (canvas != null && canvas.isDisposed())
    {
      canvas.redraw();
    }
  }

  @Override
  public void dismiss()
  {
    if (canvas != null)
    {
      canvas.dispose();
      canvas = null;
      super.dismiss();
      // setEditor(null, null, -1);
    }
  }

  protected void arrowLeft()
  {
    if (currentColumn > 0)
    {
      editItem(currentTableItem, currentTableTreeItem, currentColumn - 1);
    }
    else
    {
      if (currentTableTreeItem.getItemCount() > 0)
      {
        currentTableTreeItem.setExpanded(!currentTableTreeItem.getExpanded());
        Event expandEvent = new Event();
        expandEvent.item = currentTableTreeItem;
        currentTableTreeItem.getParent().notifyListeners(SWT.Expand, expandEvent);
      }
    }
  }
  protected void arrowRight()
  {
    if (currentColumn + 1 < currentTableItem.getParent().getColumnCount())
    {
      editItem(currentTableItem, currentTableTreeItem, currentColumn + 1);
    }
  }

  protected void arrowUp()
  {
    int index = currentTableItem.getParent().indexOf(currentTableItem);
    if (index > 0)
    {
      TableItem newTableItem = currentTableItem.getParent().getItem(index - 1);
      currentTableItem.getParent().showItem(newTableItem);
      currentTableItem.getParent().setSelection(index - 1);
      editItem(newTableItem, (TableTreeItem)newTableItem.getData(ExtendedTableTreeViewer.ITEM_ID), currentColumn);
    }
  }

  protected void arrowDown()
  {
    int index = currentTableItem.getParent().indexOf(currentTableItem);
    if (index + 1 < currentTableItem.getParent().getItemCount())
    {
      TableItem newTableItem = currentTableItem.getParent().getItem(index + 1);
      currentTableItem.getParent().showItem(newTableItem);
      currentTableItem.getParent().setSelection(index + 1);
      editItem(newTableItem, (TableTreeItem)newTableItem.getData(ExtendedTableTreeViewer.ITEM_ID), currentColumn);
    }
  }

  protected static Rectangle adjust(Rectangle bounds, Rectangle baseBounds)
  {
    bounds.x -= baseBounds.x;
    bounds.y -= baseBounds.y;
    return bounds;
  }

  protected static Rectangle getImageBounds(TableItem item, int column)
  {
    return ExtendedTableTreeViewer.getImageBounds(item, column);
  }

  protected static void drawImage(GC gc, Image image, Rectangle bounds)
  {
    Rectangle imageBounds = image.getBounds();
    gc.drawImage(image,
      imageBounds.x, imageBounds.y, imageBounds.width, imageBounds.height,
      bounds.x, bounds.y, bounds.width, bounds.height);
  }

  public void paintControl(PaintEvent event)
  {
    if (currentTableItem.isDisposed())
    {
      apply();
      dismiss();
      TableItem [] tableItems = table.getItems();
      for (int i = 0; i < tableItems.length; ++i)
      {
        TableTreeItem tableTreeItem = (TableTreeItem)tableItems[i].getData(ExtendedTableTreeViewer.ITEM_ID);
        if (tableTreeItem.getData() == currentTableTreeItemData)
        {
          editItem(tableItems[i], tableTreeItem, currentColumn);
        }
      }

      return;
    }

    Display display = currentTableItem.getDisplay();
    Rectangle itemBounds = currentTableItem.getBounds(currentColumn);

    String text = currentTableTreeItem.getText(currentColumn);
    Image image = currentTableItem.getImage(currentColumn);
    Rectangle imageBounds = adjust(
      getImageBounds(currentTableItem, currentColumn), itemBounds);

    // Get extra icon and bounds for first column of ExtendedTableTreeItem.
    //
    Image extraImage = null;
    Rectangle extraImageBounds = null;
    int paddingWidth = 0;

    if (currentColumn == 0 &&
        currentTableTreeItem instanceof ExtendedTableTreeItem)
    {
      ExtendedTableTreeItem item = (ExtendedTableTreeItem)currentTableTreeItem;
      extraImage =  item.getFirstImage();
      extraImageBounds = adjust(item.getFirstImageBounds(), itemBounds);
      paddingWidth = item.getImagePaddingWidth();
    }

    // Fill with background.
    //
    event.gc.setBackground(display.getSystemColor(SWT.COLOR_LIST_BACKGROUND));
    event.gc.fillRectangle(event.x, event.y, event.width, event.height);
      
    // Draw the icons.
    //
    if (image != null) drawImage(event.gc, image, imageBounds);
    if (extraImage != null) drawImage(event.gc, extraImage, extraImageBounds);

    // Draw the text.
    //
    if (text != null)
    {
      // This came from trial and error, yielding decent results on all
      // platforms.  It should be consistent with activate().
      //
      int x = paddingWidth == 0 ? 3 : paddingWidth;
      x += imageBounds.x + imageBounds.width + 3;

      int width = event.gc.stringExtent(text).x;
      int height = event.gc.stringExtent(text).y;
      int y = height < itemBounds.height ? 
        (itemBounds.height + 1 - height) / 2 : 0;

      event.gc.setForeground(display.getSystemColor(SWT.COLOR_LIST_SELECTION_TEXT));
      event.gc.setBackground(display.getSystemColor(SWT.COLOR_LIST_SELECTION));

      event.gc.fillRectangle(x - 3, 0, width + 8, itemBounds.height);
      event.gc.drawString(text, x, y, true);
    }

    // Draw background of right-side button.
    //
    int boxX = itemBounds.width - itemBounds.height;
    int boxY = itemBounds.height - 1;
    if (hasLaunched || hasDropDown)
    {
      event.gc.setBackground(display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
      event.gc.fillPolygon
        (new int [] 
         {
           boxX, boxY,
           boxX + itemBounds.height - 1, boxY,
           boxX + itemBounds.height - 1, 1,
           boxX, 1
         });
    }

    // Draw down arrow, offset if dropped.
    //
    if (hasDropDown)
    {
      event.gc.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
      int baseX = itemBounds.width - itemBounds.height / 2 - 1;
      int baseY = itemBounds.height - itemBounds.height / 2 + 3;
      if (isDown())
      {
        baseX += 1;
        baseY += 1;
      }
      event.gc.fillPolygon
        (new int [] 
         {
           baseX, baseY,
           baseX + 4, baseY - 4,
           baseX - 3, baseY - 4
         });
    }
    // Draw ellipses, offset if down.
    //
    else if (hasLaunched)
    {
      event.gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
      event.gc.setBackground(display.getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
      int baseY = 0;
      if (isDown())
      {
        baseY += 1;
      }
      event.gc.drawString("...", itemBounds.width - (itemBounds.height + event.gc.stringExtent("...").x) / 2, baseY);
    }

    // Draw outline.
    //
    event.gc.setForeground(display.getSystemColor(SWT.COLOR_DARK_GRAY));
    event.gc.drawRectangle(0, 0, itemBounds.width - 1, itemBounds.height - 1);

    // Draw 3D effect on right-side button.
    //
    if (hasLaunched || hasDropDown)
    {
      if (isDown())
      {
        event.gc.setForeground(display.getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        event.gc.drawLine(boxX, boxY - 1, boxX,  1);
        event.gc.drawLine(boxX, 1, boxX + itemBounds.height - 1, 1);

        event.gc.drawLine(boxX + 1, boxY - 1, boxX + itemBounds.height - 1,  boxY - 1);
        event.gc.drawLine(boxX + itemBounds.height - 1, boxY - 1, boxX + itemBounds.height - 1, 2);
      }
      else
      {
        event.gc.setForeground(display.getSystemColor(SWT.COLOR_WIDGET_HIGHLIGHT_SHADOW));
        event.gc.drawLine(boxX + 1, boxY - 1, boxX + 1,  2);
        event.gc.drawLine(boxX + 1, 2, boxX + itemBounds.height - 2, 2);

        event.gc.setForeground(display.getSystemColor(SWT.COLOR_WIDGET_NORMAL_SHADOW));
        event.gc.drawLine(boxX + 1, boxY - 2, boxX + itemBounds.height - 3,  boxY - 2);
        event.gc.drawLine(boxX + itemBounds.height - 3, boxY - 2, boxX + itemBounds.height - 3, 2);

        event.gc.setForeground(display.getSystemColor(SWT.COLOR_WIDGET_DARK_SHADOW));
        event.gc.drawLine(boxX, boxY - 1, boxX + itemBounds.height - 2,  boxY - 1);
        event.gc.drawLine(boxX + itemBounds.height - 2, boxY - 1, boxX + itemBounds.height - 2, 1);
      }
    }
  }

  protected Image getLeftGradient()
  {
    if (leftGradient == null)
    {
      getGradients();
    }

    return leftGradient;
  }

  protected Image getRightGradient()
  {
    if (rightGradient == null)
    {
      getGradients();
    }

    return rightGradient;
  }

  protected void getGradients()
  {
    int width = 20; 
    int height = 10;

    Display display = canvas.getDisplay();

    leftGradient = new Image(display, width, height);
    GC leftGC = new GC(leftGradient);

    rightGradient = new Image(display, width, height);
    GC rightGC = new GC(rightGradient);

    // EATM Color startColor = display.getSystemColor(SWT.COLOR_LIST_SELECTION);
    Color startColor = display.getSystemColor(SWT.COLOR_LIST_BACKGROUND);
    RGB rgb1 = startColor.getRGB();

    Color endColor = display.getSystemColor(SWT.COLOR_LIST_BACKGROUND);
    RGB rgb2 = endColor.getRGB();

    for (int k = 0; k < width; k++) 
    {
      int r = rgb1.red + k * (rgb2.red - rgb1.red) / width;
      r = (rgb2.red > rgb1.red) ? Math.min(r, rgb2.red) : Math.max(r, rgb2.red);
      int g = rgb1.green + k * (rgb2.green - rgb1.green) / width;
      g = (rgb2.green > rgb1.green) ? Math.min(g, rgb2.green) : Math.max(g, rgb2.green);
      int b = rgb1.blue + k * (rgb2.blue - rgb1.blue) / width;
      b = (rgb2.blue > rgb1.blue) ? Math.min(b, rgb2.blue) : Math.max(b, rgb2.blue);

      Color color = new Color(display, r, g, b);

      leftGC.setBackground(color);
      leftGC.fillRectangle(width - k - 1, 0, 1, height);

      rightGC.setBackground(color);
      rightGC.fillRectangle(k, 0, 1, height);

      color.dispose();
    }

    leftGC.dispose();
    rightGC.dispose();
  }
}


/*
    if (currentTableItem.getParent().getHorizontalBar() != null)
    {
      System.out.println("?? " + currentTableItem.getParent().getHorizontalBar().getSelection());
      System.out.println("?? " + currentTableItem.getParent().getHorizontalBar().getMinimum());
      System.out.println("?? " + currentTableItem.getParent().getHorizontalBar().getMaximum());
      System.out.println("?? " + currentTableItem.getParent().getHorizontalBar().getIncrement());
      System.out.println("?? " + currentTableItem.getParent().getHorizontalBar().getThumb());
      System.out.println("?? " + currentTableItem.getParent().getHorizontalBar().getPageIncrement());
    
      System.out.println("@@@" + itemBounds);

      if (itemBounds.x < currentTableItem.getParent().getHorizontalBar().getSelection())
      {
        System.out.println(">>");
        currentTableItem.getParent().getHorizontalBar().setSelection
          (Math.max(currentTableItem.getParent().getHorizontalBar().getMinimum(), itemBounds.x));
        currentTableItem.getParent().layout();
      }
      else if (itemBounds.x + itemBounds.width > currentTableItem.getParent().getHorizontalBar().getSelection() + theTable.getHorizontalBar().getThumb())
      {
        System.out.println("<<");
        currentTableItem.getParent().getHorizontalBar().setSelection(theTable.getHorizontalBar().getMaximum() - theTable.getHorizontalBar().getThumb());
        currentTableItem.getParent().layout();
      }  
    }
*/

