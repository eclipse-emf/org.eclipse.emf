/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.common.ui;


import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.ui.viewer.IUndecoratingLabelProvider;
import org.eclipse.emf.common.util.CommonUtil;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ContentViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;


/**
 * Please don't use this class until the design is complete.
 */
public abstract class ViewerPane implements IPropertyListener, Listener
{
  protected IWorkbenchPage page;
  protected IWorkbenchPart part;
  protected Collection<Object> buddies = new ArrayList<Object>();
  protected Viewer viewer;
  protected Composite container;
  boolean isActive;
  Font normalTitleFont;
  Font activeTitleFont;
  protected CLabel titleLabel;
  protected ToolBar actionBar;
  protected ToolBarManager toolBarManager;
  protected MenuManager menuManager;
  protected Image pullDownImage;
  protected ToolBar systemBar;
  protected ViewForm control;

  protected MouseListener mouseListener = 
    new MouseAdapter() 
    {
      @Override
      public void mouseDown(MouseEvent e) 
      {
        requestActivation();
      }
      @Override
      public void mouseDoubleClick(MouseEvent e)
      {
        if (e.getSource() == titleLabel)
        {
          doMaximize();
        }
      } 
    };

  protected IPartListener partListener = 
    new IPartListener()
    {
      public void partActivated(IWorkbenchPart p) 
      {
        // Do nothing
      }
      
      public void partBroughtToTop(IWorkbenchPart p) 
      {
        // Do nothing
      }
      public void partClosed(IWorkbenchPart p)
      {
        // Do nothing
      }
      public void partDeactivated(IWorkbenchPart p)
      {
        if (p == ViewerPane.this.part)
        {
          showFocus(false);
        }
      }
      
      public void partOpened(IWorkbenchPart p)
      {
        // Do nothing
      }
    };


  /**
   * Constructs a view pane for a view part.
   */
  public ViewerPane(IWorkbenchPage page, IWorkbenchPart part) 
  {
    this.page = page;
    this.part = part;

    page.addPartListener(partListener);
  }

  abstract public Viewer createViewer(Composite parent);

  public Collection<Object> getBudies()
  {
    return buddies;
  }

  public void createControl(Composite parent) 
  {
    if (getControl() == null)
    {
      container = parent;

      // Create view form.    
      //control = new ViewForm(parent, getStyle());
      control = new ViewForm(parent, SWT.NONE);
      control.addDisposeListener
        (new DisposeListener()
         {
           public void widgetDisposed(DisposeEvent event)
           {
             dispose();
           }
         });
      control.marginWidth = 0;
      control.marginHeight = 0;

      // Create a title bar.
      createTitleBar();

      viewer = createViewer(control);
      control.setContent(viewer.getControl());

      control.setTabList(new Control [] { viewer.getControl() });
      
      // When the pane or any child gains focus, notify the workbench.
      control.addListener(SWT.Activate, this);
      hookFocus(control);
      hookFocus(viewer.getControl());
    }
  }

  public Viewer getViewer()
  {
    return viewer;
  }

  /**
   * Get the control.
   */
  public Control getControl() 
  {
    return control;
  }

  /**
   * Get the view form.
   */
  protected ViewForm getViewForm() 
  {
    return control;
  }

  /**
   * @see Listener
   */
  public void handleEvent(Event event) 
  {
    if (event.type == SWT.Activate)
    {
      requestActivation();
    }
  }

  /**
   * Hook focus on a control.
   */
  public void hookFocus(Control ctrl) 
  {
    ctrl.addMouseListener(mouseListener);
  }

  /**
   * Notify the workbook page that the part pane has
   * been activated by the user.
   */
  protected void requestActivation() 
  {
    control.getContent().setFocus();
    showFocus(true);
  }

  /**
   * Sets focus to this part.
   */
  public void setFocus() 
  {
    requestActivation();
    control.getContent().setFocus();
  }

  /**
   * Tool bar manager
   */
  class PaneToolBarManager extends ToolBarManager 
  {
    public PaneToolBarManager(ToolBar paneToolBar) 
    {
      super(paneToolBar);
    }

    /**
     *  EATM I have no idea how this is supposed to be called.
     */
    @Override
    protected void relayout(ToolBar toolBar, int oldCount, int newCount) 
    {
      // remove/add the action bar from the view so to avoid
      // having an empty action bar participating in the view's
      // layout calculation (and maybe causing an empty bar to appear)
      if (newCount < 1) 
      {
        if (control.getTopCenter() != null)
        {
          control.setTopCenter(null);
        }
      }
      else 
      {
        toolBar.layout();
        if (control.getTopCenter() == null)
        {
          control.setTopCenter(toolBar);
        }
      }
      Composite parent= toolBar.getParent();
      parent.layout();
      if (parent.getParent() != null)
      {
        parent.getParent().layout();
      }
    }    
  }

  /**
   * Create the menu manager.
   */
  private void createMenuManager() 
  {
    menuManager = new MenuManager("Pane Menu");
    if (systemBar != null)
    {
      createPulldownMenu();
    }
  }

  /**
   * Create a pull-down menu on the action bar.
   */
  private void createPulldownMenu() 
  {
    if (systemBar != null)
    {
      ToolItem ti = new ToolItem(systemBar, SWT.PUSH, 0);
      try
      {
        pullDownImage = 
          ImageDescriptor.createFromURL
            (CommonUtil.newURL(CommonUIPlugin.INSTANCE.getImage("full/ctool16/ViewPullDown").toString())).createImage();
        ti.setImage(pullDownImage);
        ti.addSelectionListener
          (new SelectionAdapter() 
           {
             @Override
            public void widgetSelected(SelectionEvent e) 
             {
              showViewMenu();
             }
           });
      }
      catch (MalformedURLException exception)
      {
        // Do nothing
      }
    }
  }

  /**
   * Create a title bar for the pane which includes
   * the view icon and title to the far left, and
   * the close X icon to the far right.
   * The middle part is reserved for the view part to
   * add a menu and tools.
   */
  protected void createTitleBar() 
  {
    // Only do this once.
    if (titleLabel == null)
    {
      // Title.  
      titleLabel = new CLabel(control, SWT.SHADOW_NONE);
      normalTitleFont = titleLabel.getFont();
      activeTitleFont = getActiveFont(normalTitleFont);

      hookFocus(titleLabel);
      titleLabel.setAlignment(SWT.LEFT);
      titleLabel.setBackground(null, null);
      titleLabel.addMouseListener
        (new MouseAdapter() 
         {
           @Override
          public void mouseDown(MouseEvent e) 
           {
             if (e.button == 3)
             {
               showTitleLabelMenu(e);
             }
           }
         });

      updateTitles();
      control.setTopLeft(titleLabel);

      // Listen to title changes.
      // getViewPart().addPropertyListener(this);
      
      // Action bar.
      actionBar = new ToolBar(control, SWT.FLAT | SWT.WRAP);
      hookFocus(actionBar);
      control.setTopCenter(actionBar);
      
      // System bar.  
      systemBar = new ToolBar(control, SWT.FLAT | SWT.WRAP);
      hookFocus(systemBar);
      if (menuManager != null && !menuManager.isEmpty())
      {
        createPulldownMenu();
      }
      control.setTopRight(systemBar);
    }
  }

  protected void doMaximize()
  {
    Control child = control;
    for (Control parent = control.getParent(); parent instanceof SashForm || parent instanceof CTabFolder; parent = parent.getParent())
    {
      if (parent instanceof CTabFolder)
      {
        CTabFolder cTabFolder = (CTabFolder)parent;
        cTabFolder.setMaximized(!cTabFolder.getMaximized());
      }
      else if (parent instanceof SashForm)
      {
        SashForm sashForm = (SashForm)parent;
        if (sashForm.getMaximizedControl() == null)
        {
          sashForm.setMaximizedControl(child);
        }
        else
        {
          sashForm.setMaximizedControl(null);
        }
      }
      child = parent;
    }
  }

  public void dispose() 
  {
    if ((control != null) && (!control.isDisposed())) 
    {
      control.removeListener(SWT.Activate, this);
      control = null;
      page.removePartListener(partListener);
    }

    if (pullDownImage != null)
    {
      pullDownImage.dispose();
      pullDownImage = null;
    }
  }

  public MenuManager getMenuManager() 
  {
    if (menuManager == null)
    {
      createMenuManager();
    }
    return menuManager;
  }

  public ToolBarManager getToolBarManager() 
  {
    if (toolBarManager == null)
    {
      toolBarManager = new PaneToolBarManager(actionBar);
    }
    return toolBarManager;
  }

  /**
   * Indicates that a property has changed.
   *
   * @param source the object whose property has changed
   * @param propID the ID of the property which has changed; property IDs
   *   are generally defined as constants on the source class
   */
  public void propertyChanged(Object source, int propID) 
  {
    if (propID == IWorkbenchPart.PROP_TITLE)
    {
      updateTitles();
    }
  }

  /**
   * Indicate focus in part.
   */
  public void showFocus(boolean inFocus) 
  {
    if (inFocus != isActive)
    {
      isActive = inFocus;

      if (titleLabel != null)
      {
        titleLabel.setFont(inFocus ? activeTitleFont : normalTitleFont);
        titleLabel.update();
        titleLabel.redraw();
      }
    }
  }

  /**
   * Show the context menu for this window.
   */
  private void showViewMenu() 
  {
    Menu aMenu = menuManager.createContextMenu(getControl());
    Point topLeft = new Point(0, 0);
    topLeft.y += systemBar.getBounds().height;
    topLeft = systemBar.toDisplay(topLeft);
    aMenu.setLocation(topLeft.x, topLeft.y);
    aMenu.setVisible(true);
  }

  @Override
  public String toString() 
  {
    String label = "disposed";
    if((titleLabel != null) && (!titleLabel.isDisposed()))
      label = titleLabel.getText();
    
    return getClass().getName() + "@" + Integer.toHexString(hashCode()) + 
    "(" + label + ")";
  }

  public void updateActionBars() 
  {
    if (menuManager != null)
    {
      menuManager.updateAll(false);
    }
  
    if (toolBarManager != null)
    {
      getToolBarManager().update(false);
    }
  }

  /**
   * Update the title attributes.
   */
  public void updateTitles() 
  {
    titleLabel.update();
  }

  public void setTitle(Object object)
  {
    if (viewer != null)
    {
      if (viewer instanceof ContentViewer)
      {
        IBaseLabelProvider labelProvider = ((ContentViewer)viewer).getLabelProvider();
        if (labelProvider instanceof ILabelProvider)
        {
          if (object == null)
          {
            titleLabel.setImage(null);
            titleLabel.setText("");
          }
          else if (labelProvider instanceof IUndecoratingLabelProvider)
          {
            titleLabel.setImage(((IUndecoratingLabelProvider)labelProvider).getUndecoratedImage(object));
            titleLabel.setText(((IUndecoratingLabelProvider)labelProvider).getUndecoratedText(object));
          }
          else
          {
            titleLabel.setImage(((ILabelProvider)labelProvider).getImage(object));
            titleLabel.setText(((ILabelProvider)labelProvider).getText(object));
          }
        }
      }
    }
  }

  public void setTitle(String title, Image image)
  {
    if (titleLabel != null)
    {
      titleLabel.setImage(image);
      titleLabel.setText(title);
    }
  }

  private void showTitleLabelMenu(MouseEvent e) 
  {
    Menu menu = new Menu(titleLabel);

    boolean isMaximized = 
        control.getParent() instanceof SashForm ? 
          ((SashForm)control.getParent()).getMaximizedControl() != null :
          control.getParent() instanceof CTabFolder && ((CTabFolder)control.getParent()).getMaximized();

    MenuItem restoreItem = new MenuItem(menu, SWT.NONE);
    restoreItem.setText(CommonUIPlugin.INSTANCE.getString("_UI_Restore_menu_item"));
    restoreItem.addSelectionListener
      (new SelectionAdapter() 
       {
         @Override
        public void widgetSelected(SelectionEvent selectionEvent) 
         {
           doMaximize();
         }
       });
    restoreItem.setEnabled(isMaximized);

    MenuItem maximizeItem = new MenuItem(menu, SWT.NONE);
    maximizeItem.setText(CommonUIPlugin.INSTANCE.getString("_UI_Maximize_menu_item"));
    maximizeItem.addSelectionListener
      (new SelectionAdapter() 
       {
         @Override
        public void widgetSelected(SelectionEvent selectionEvent) 
         {
           doMaximize();
         }
       });
    maximizeItem.setEnabled(!isMaximized);

    Point point = new Point(e.x, e.y);
    point = titleLabel.toDisplay(point);
    menu.setLocation(point.x, point.y);
    menu.setVisible(true);
  }
  
  private static final Map<Font, Font> FONTS = new HashMap<Font, Font>();

  private static Font getActiveFont(Font font)
  {
    Font result = FONTS.get(font);
    if (result == null)
    {
      Device device = font.getDevice();
      FontDescriptor fontDescriptor = FontDescriptor.createFrom(font);
      result = fontDescriptor.setStyle(SWT.ITALIC).createFont(device);
      FONTS.put(font, result);
    }
    return result;
  }
}