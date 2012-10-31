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
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.celleditor.ExtendedTreeEditor;
import org.eclipse.emf.edit.provider.IUpdateableItemText;


/**
 * This base class for implementing {@link org.eclipse.swt.custom.TreeEditor}s that delegate 
 * to adapters produced by an {@link AdapterFactory}.
 */
public class AdapterFactoryTreeEditor extends ExtendedTreeEditor
{
  private static final long serialVersionUID = 1L;

  protected AdapterFactory adapterFactory;
  protected TreeItem currentTreeItem;

  public AdapterFactoryTreeEditor(Tree tree, AdapterFactory adapterFactory)
  {
    super(tree);
    this.adapterFactory = adapterFactory;
  }

  public AdapterFactory getAdapterFactory()
  {
    return adapterFactory;
  }

  public void setAdapterFactory(AdapterFactory adapterFactory)
  {
    this.adapterFactory = adapterFactory;
  }

  @Override
  protected void editItem(final TreeItem treeItem)
  {
    final Object object = treeItem.getData();
    final IUpdateableItemText updateableItemText = (IUpdateableItemText)adapterFactory.adapt(object, IUpdateableItemText.class);
    if (updateableItemText != null)
    {
      String string = updateableItemText.getUpdateableText(object);

      if (string != null)
      {
        horizontalAlignment = SWT.LEFT;
        // grabHorizontal = true;
        minimumWidth = Math.max(50, treeItem.getBounds().width);
    
        if (System.getProperty("EMF_COMBO_TEST") == null)
        {
          final Text text = new Text(tree, SWT.BORDER);
          setEditor(text, treeItem);
          text.setFocus();
          text.setText(string);
          text.setSelection(0, string.length());
      
          text.addFocusListener
           (new FocusAdapter()
            {
              private static final long serialVersionUID = 1L;

              @Override
              public void focusLost(FocusEvent event)
              {
                updateableItemText.setText(object, text.getText());
                text.setVisible(false);
              }
            });
          text.addKeyListener
           (new KeyAdapter()
            {
              private static final long serialVersionUID = 1L;

              @Override
              public void keyPressed(KeyEvent event)
              {
                if (event.character == '\r' || event.character == '\n')
                {
                  updateableItemText.setText(object, text.getText());
                  setEditor(null);
                  text.dispose();
                }
                else if (event.character == '\033')
                {
                  setEditor(null);
                  text.dispose();
                }
              }
            });
        }
        else
        {
          final Combo combo = new Combo(tree, SWT.BORDER);
          setEditor(combo, treeItem);
          combo.setFocus();
          combo.setText(string);
          combo.setSelection(new Point(0,string.length()));
          combo.add("Item 1");
          combo.add("Item 2");
          combo.add("Item 3");
          combo.add("Item 4");
      
          combo.addFocusListener
           (new FocusAdapter()
            {
              /**
               * 
               */
              private static final long serialVersionUID = 1L;

              @Override
              public void focusLost(FocusEvent event)
              {
                System.out.println("Combo lost focus");
                updateableItemText.setText(object, combo.getText());
                combo.setVisible(false);
              }
            });

          combo.addKeyListener
           (new KeyAdapter()
            {
              /**
               * 
               */
              private static final long serialVersionUID = 1L;

              @Override
              public void keyPressed(KeyEvent event)
              {
                System.out.println("Combo key event");
                if (event.character == '\r' || event.character == '\n')
                {
                  updateableItemText.setText(object, combo.getText());
                  setEditor(null);
                  combo.dispose();
                }
                else if (event.character == '\033')
                {
                  setEditor(null);
                  combo.dispose();
                }
              }
            });

          combo.addModifyListener
            (new ModifyListener()
             {
               /**
               * 
               */
              private static final long serialVersionUID = 1L;

              public void modifyText(ModifyEvent event)
               {
                 System.out.println("Combo modified");
               }
             });
        }
      }
    }
  }
}
