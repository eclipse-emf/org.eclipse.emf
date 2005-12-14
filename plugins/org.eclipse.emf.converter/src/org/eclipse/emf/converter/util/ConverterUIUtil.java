/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: ConverterUIUtil.java,v 1.1 2005/12/14 07:45:42 marcelop Exp $
 */

package org.eclipse.emf.converter.util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

/**
 * @since 2.2.0
 */
public class ConverterUIUtil
{
  public static class TreeCheckStateManager implements Listener
  {
    protected Tree tree;
    protected boolean internalChange = false;
    
    public TreeCheckStateManager(Tree tree)
    {
      this.tree = tree;
      tree.addListener(SWT.Selection, this);
      tree.addListener(SWT.Dispose, this);
    }
    
    public void handleEvent(Event event)
    {
      if (!internalChange)
      {
        if (event.widget == tree && event.detail == SWT.CHECK)
        {
          switch (event.type)
          {
            case SWT.Selection:
              try
              {
                internalChange = true;
                checkStateChanged((TreeItem)event.item, true);
              }
              finally
              {
                internalChange = false;
              }
              break;
              
            case SWT.Dispose:
              treeDisposed();
              break;
          }
        }
      }
    }
    
    protected void checkStateChanged(TreeItem item, boolean changeChildren)
    {
      setGray(item, false);
      
      TreeItem[] sibilings = item.getParentItem() != null ? 
        item.getParentItem().getItems() :
        item.getParent().getItems();

      boolean checked = item.getChecked();
      if (changeChildren) checkChildren(item, checked);
      
      boolean grayParent = false;
      for (int i = 0; i < sibilings.length; i++)
      {
        TreeItem sibiling = sibilings[i];
        if (checked ? !sibiling.getChecked() : sibiling.getChecked())
        {
          grayParent = true;
          break;
        }
      }
      
      if (grayParent)
      {
        grayParents(item);
      }
      else
      {
        TreeItem parent = item.getParentItem();
        if (parent != null)
        {
          setCheck(parent, checked);
          setGray(parent, false);          
          checkStateChanged(parent, false);
        }
      }
    }
    
    protected void treeDisposed()
    {
      if (!tree.isDisposed())
      {
        tree.removeListener(SWT.Selection, this);
        tree.removeListener(SWT.Dispose, this);
      }
      tree = null;
    }
    
    protected void grayParents(TreeItem item)
    {
      item = item.getParentItem();
      while (item != null)
      {
        setCheck(item, true);
        setGray(item, true);        
        item = item.getParentItem();
      }      
    }
    
    protected void checkChildren(TreeItem item, boolean check)
    {
      TreeItem[] items = item.getItems();
      for (int i = 0; i < items.length; i++)
      {
        TreeItem child = items[i];
        setCheck(child, check);
        setGray(child, false);
        checkChildren(child, check);
      }
    }
    
    protected void setCheck(TreeItem item, boolean check)
    {
      if (item.getChecked() != check)
      {
        item.setChecked(check);        
        tree.notifyListeners(SWT.Selection, createCheckEvent(item));
      }
    }
    
    protected Event createCheckEvent(TreeItem item)
    {
      Event event = new Event();
      event.type = SWT.Selection;
      event.widget = item.getParent();
      event.item = item;
      event.detail = SWT.CHECK;
      event.display = item.getDisplay();
      return event;
    }

    protected void setGray(TreeItem item, boolean gray)
    {
      if (item.getGrayed() != gray) item.setGrayed(gray);
    }
  }  
}
