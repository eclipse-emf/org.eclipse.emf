/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.common.ui.viewer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

import org.eclipse.emf.common.ui.CommonUIPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.internal.text.InformationControlReplacer;
import org.eclipse.jface.internal.text.html.BrowserInformationControl;
import org.eclipse.jface.internal.text.html.BrowserInformationControlInput;
import org.eclipse.jface.internal.text.html.HTMLPrinter;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.text.AbstractHoverInformationControlManager;
import org.eclipse.jface.text.AbstractReusableInformationControlCreator;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IInformationControlExtension;
import org.eclipse.jface.text.IInformationControlExtension2;
import org.eclipse.jface.text.IInformationControlExtension3;
import org.eclipse.jface.text.IInformationControlExtension4;
import org.eclipse.jface.text.IInformationControlExtension5;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerRow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.LocationEvent;
import org.eclipse.swt.browser.LocationListener;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;


/**
 * A utility class for showing rich JDT-style HTML content in tool tip hovers.
 * This class is final to avoid long term API commitments.
 * If you feel the need to specialize it, please open a bugzilla to explain what your use case and requirements.
 *
 * @since 2.9
 */
@SuppressWarnings("restriction")
public final class ColumnViewerInformationControlToolTipSupport
{
  /**
   * The default font used within the hovers.
   * It will be the same as JDT's Javadoc font, if available, or the default {@link JFaceResources#DIALOG_FONT dialog} otherwise.
   */
  protected static final String DEFAULT_FONT = JFaceResources.getFontRegistry().hasValueFor("org.eclipse.jdt.ui.javadocfont") ? "org.eclipse.jdt.ui.javadocfont" : JFaceResources.DIALOG_FONT;

  /**
   * A listener that's "path URI" aware.
   * I.e., a URI of the form "path:{/&lt;index>}+" will be interpreted to
   * {@link StructuredViewer#setSelection(org.eclipse.jface.viewers.ISelection, boolean) select} and reveal the {@link StructuredViewer}'s content tree's corresponding object.
   */
  public static class PathLocationListener implements LocationListener
  {
    protected StructuredViewer viewer;

    public PathLocationListener(StructuredViewer viewer)
    {
      this.viewer = viewer;
    }

    public void changing(LocationEvent event)
    {
      URI uri = URI.createURI(event.location);
      if ("path".equals(uri.scheme()))
      {
        IContentProvider contentProvider = viewer.getContentProvider();
        if (contentProvider instanceof IStructuredContentProvider)
        {
          IStructuredContentProvider structuredContentProvider = (IStructuredContentProvider)contentProvider;
          traverse(structuredContentProvider, uri.segments(), 0);
        }
        event.doit = false;
      }
    }

    protected void traverse(IStructuredContentProvider structuredContentProvider, String[] segments, int index)
    {
      Object[] elements = structuredContentProvider.getElements(viewer.getInput());
      int i = Integer.parseInt(segments[index++]);
      Object object = elements[i];
      if (index != segments.length && structuredContentProvider instanceof ITreeContentProvider)
      {
        ITreeContentProvider treeContentProvider = (ITreeContentProvider)structuredContentProvider;
        traverse(treeContentProvider, object, segments, index);
      }
      else
      {
        setSelection(object);
      }
    }

    protected void traverse(ITreeContentProvider treeContentProvider, Object object, String[] segments, int index)
    {
      Object[] children = treeContentProvider.getChildren(object);
      int i = Integer.parseInt(segments[index++]);
      Object child = children[i];
      if (index != segments.length)
      {
        traverse(treeContentProvider, child, segments, index);
      }
      else
      {
        setSelection(child);
      }
    }

    protected void setSelection(Object object)
    {
      viewer.setSelection(new StructuredSelection(object), true);
      viewer.getControl().setFocus();
    }

    public void changed(LocationEvent event)
    {
      // Ignore.
    }
  }

  static protected String defaultStyleSheet;

  protected ColumnViewer viewer;
  protected Control control;
  protected LocationListener locationListener;
  protected ViewerCell currentCell;
  protected String text;
  protected Font font;
  protected Color backgroundColor;
  protected Color foregroundColor;

  /**
   * An interface that provides access to the underlying control so that we're able give it focus.
   */
  public interface AccessibleInformationControl extends IInformationControl, IInformationControlExtension, IInformationControlExtension2, IInformationControlExtension3, IInformationControlExtension4, IInformationControlExtension5
  {
    public Control getControl();
  }

  protected AbstractReusableInformationControlCreator replacementInformationControlCreator =
    new AbstractReusableInformationControlCreator()
    {
      @Override
      protected IInformationControl doCreateInformationControl(Shell parent)
      {
        IInformationControl informationControl;
        if (BrowserInformationControl.isAvailable(parent))
        {
          BrowserInformationControl browserInformationControl = new BrowserInformationControl(parent, getSymbolicFont(), true);
          browserInformationControl.addLocationListener(locationListener);
          informationControl = browserInformationControl;
        }
        else
        {
          informationControl = new DefaultInformationControl(parent, (ToolBarManager)null);
        }

        if (foregroundColor != null)
        {
          informationControl.setForegroundColor(foregroundColor);
        }
        if (backgroundColor != null)
        {
          informationControl.setBackgroundColor(backgroundColor);
        }

        return informationControl;
      }
    };

   protected AbstractReusableInformationControlCreator reusableInformationControlCreator =
     new AbstractReusableInformationControlCreator()
     {
       @Override
       protected IInformationControl doCreateInformationControl(Shell parent)
       {
         IInformationControl informationControl;
         if (BrowserInformationControl.isAvailable(parent))
         {
           class AccessibleBrowserInformationControl extends BrowserInformationControl implements AccessibleInformationControl
           {
             public AccessibleBrowserInformationControl(Shell parent, String symbolicFontName, String statusFieldText)
             {
               super(parent, symbolicFontName, statusFieldText);
             }

             @Override
             public IInformationControlCreator getInformationPresenterControlCreator()
             {
               return replacementInformationControlCreator;
             }

             public Control getControl()
             {
               return getShell();
             }
           }
           AccessibleBrowserInformationControl accessibleBrowserInformationControl = new AccessibleBrowserInformationControl(parent,  getSymbolicFont(), CommonUIPlugin.INSTANCE.getString("_UI_PressF2ForFocus_label"));
           informationControl = accessibleBrowserInformationControl;
         }
         else
         {
           class AccessibleDefaultInformationControl extends DefaultInformationControl implements AccessibleInformationControl
           {
             public AccessibleDefaultInformationControl(Shell parent, String statusFieldText)
             {
               super(parent, statusFieldText);
             }

             public void setInput(Object input)
             {
               if (input instanceof BrowserInformationControlInput)
               {
                 setInformation(((BrowserInformationControlInput)input).getHtml());
               }
               else
               {
                 setInformation((String)input);
               }
             }

             public Control getControl()
             {
               return getShell();
             }
           }
           informationControl = new AccessibleDefaultInformationControl(parent, CommonUIPlugin.INSTANCE.getString("_UI_PressF2ForFocus_label"));
         }

         if (foregroundColor != null)
         {
           informationControl.setForegroundColor(foregroundColor);
         }
         if (backgroundColor != null)
         {
           informationControl.setBackgroundColor(backgroundColor);
         }

         return informationControl;
       }
     };

  protected AbstractHoverInformationControlManager hoverInformationControlManager =
    new AbstractHoverInformationControlManager(reusableInformationControlCreator)
    {
      protected IInformationControlCloser closer;
      protected InformationControlReplacer replacer;

      {
        replacer =
          new InformationControlReplacer(replacementInformationControlCreator)
          {
            {
              this.setCloser(new Closer());
            }

            class Closer implements IInformationControlCloser, ControlListener, MouseListener, KeyListener, FocusListener, Listener
            {
              protected boolean isActive;
              protected Display display;
              protected Control subjectControl;
              protected IInformationControl informationControl;

              public void setSubjectControl(Control control)
              {
                subjectControl = control;
              }

              public void setInformationControl(IInformationControl control)
              {
                this.informationControl = control;
              }

              public void start(Rectangle informationArea)
              {
                if (!isActive)
                {
                  isActive = true;

                  if (subjectControl != null && !subjectControl.isDisposed())
                  {
                    subjectControl.addControlListener(this);
                    subjectControl.addMouseListener(this);
                    subjectControl.addKeyListener(this);
                  }

                  if (informationControl != null)
                  {
                    informationControl.addFocusListener(this);
                  }

                  display = subjectControl.getDisplay();
                  if (!display.isDisposed())
                  {
                    display.addFilter(SWT.MouseMove, this);
                    display.addFilter(SWT.FocusOut, this);
                  }
                }
              }

              public void stop()
              {
                if (isActive)
                {
                  isActive = false;

                  if (subjectControl != null && !subjectControl.isDisposed())
                  {
                    subjectControl.removeControlListener(this);
                    subjectControl.removeMouseListener(this);
                    subjectControl.removeKeyListener(this);
                  }

                  if (informationControl != null)
                  {
                    informationControl.removeFocusListener(this);
                  }

                  if (display != null && !display.isDisposed())
                  {
                    display.removeFilter(SWT.MouseMove, this);
                    display.removeFilter(SWT.FocusOut, this);
                  }
                  display = null;
                }
              }

              public void controlResized(ControlEvent event)
              {
                hideInformationControl();
              }

              public void controlMoved(ControlEvent event)
              {
                hideInformationControl();
              }

              public void mouseDown(MouseEvent event)
              {
                hideInformationControl();
              }

              public void mouseUp(MouseEvent event)
              {
                // Ignore.
              }

              public void mouseDoubleClick(MouseEvent event)
              {
                hideInformationControl();
              }

              public void keyPressed(KeyEvent event)
              {
                hideInformationControl();
              }

              public void keyReleased(KeyEvent event)
              {
                // Ignore.
              }

              public void focusGained(FocusEvent event)
              {
                // Ignore.
              }

              public void focusLost(FocusEvent event)
              {
                if (display != null && !display.isDisposed())
                {
                  display.asyncExec
                    (new Runnable()
                     {
                       public void run()
                       {
                         hideInformationControl();
                       }
                     });
                }
              }

              public void handleEvent(Event event)
              {
                if (event.type == SWT.MouseMove)
                {
                  if (event.widget instanceof Control && event.widget.isDisposed())
                  {
                    if (informationControl != null && !informationControl.isFocusControl() && informationControl instanceof IInformationControlExtension3)
                    {
                      Rectangle controlBounds =  ((IInformationControlExtension3)informationControl).getBounds();
                      if (controlBounds != null)
                      {
                        Point mouseLocation = event.display.map((Control)event.widget, null, event.x, event.y);
                        if (!controlBounds.contains(mouseLocation))
                        {
                          hideInformationControl();
                        }
                      }
                    }
                    else
                    {
                      if (display != null && !display.isDisposed())
                      {
                        display.removeFilter(SWT.MouseMove, this);
                      }
                    }
                  }
                }
                else if (event.type == SWT.FocusOut)
                {
                  if (informationControl != null && !informationControl.isFocusControl())
                  {
                    hideInformationControl();
                  }
                }
              }
            }
          };

        getInternalAccessor().setInformationControlReplacer(replacer);
      }

      @Override
      protected void setCloser(IInformationControlCloser closer)
      {
        this.closer = closer;
        super.setCloser(closer);
      }

      @Override
      protected boolean canClearDataOnHide()
      {
        return false;
      }

      @Override
      protected void computeInformation()
      {
        MouseEvent hoverEvent = getHoverEvent();
        Event event = new Event();
        event.x = hoverEvent.x;
        event.y = hoverEvent.y;
        if (shouldCreateToolTip(event))
        {
          StringBuffer buffer = new StringBuffer(text);
          String styleSheet = getStyleSheet();
          FontData fontData = JFaceResources.getFontRegistry().getFontData(getSymbolicFont())[0];
          styleSheet = HTMLPrinter.convertTopLevelFont(styleSheet, fontData);
          HTMLPrinter.insertPageProlog(buffer, 0, foregroundColor == null ? null : foregroundColor.getRGB(), backgroundColor == null ? null : backgroundColor.getRGB(), styleSheet);
          HTMLPrinter.addPageEpilog(buffer);
          final String html = buffer.toString();

          setInformation
            (new BrowserInformationControlInput(null)
             {
               @Override
               public String getHtml()
               {
                 return html;
               }

               @Override
               public String getInputName()
               {
                 return "";
               }

               @Override
               public Object getInputElement()
               {
                 return text;
               }
             },
             currentCell.getBounds());

          currentCell = null;
        }
        else
        {
          setInformation(null, null);
        }
      }

      protected KeyListener keyListener =
        new KeyListener()
        {
          public void keyReleased(KeyEvent event)
          {
            if (event.keyCode == SWT.F2)
            {
              IInformationControl informationControl = getInformationControl();
              if (informationControl instanceof AccessibleInformationControl)
              {
                Control myControl = ((AccessibleInformationControl)informationControl).getControl();
                Event mouseEvent = new Event();
                mouseEvent.display = control.getDisplay();
                mouseEvent.widget = myControl;
                mouseEvent.type = SWT.MouseUp;
                ((Listener)closer).handleEvent(mouseEvent);
                event.doit = false;
              }
            }
          }

          public void keyPressed(KeyEvent event)
          {
            // Ignore.
          }
        };

      @Override
      public void install(Control subjectControl)
      {
        Control oldSubjectControl = getSubjectControl();

        if (oldSubjectControl != null && !oldSubjectControl.isDisposed())
        {
          oldSubjectControl.removeKeyListener(keyListener);
        }

        if (subjectControl != null)
        {
          subjectControl.addKeyListener(keyListener);
        }

        super.install(subjectControl);
        getInternalAccessor().getInformationControlReplacer().install(subjectControl);
      }

      @Override
      public void dispose()
      {
        Control subjectControl = getSubjectControl();
        if (subjectControl != null && !subjectControl.isDisposed())
        {
          subjectControl.removeKeyListener(keyListener);
        }
        replacer.dispose();
        super.dispose();
      }
    };

  public ColumnViewerInformationControlToolTipSupport(ColumnViewer viewer, LocationListener locationListener)
  {
    this.viewer = viewer;
    this.control = viewer.getControl();
    this.locationListener = locationListener == null ? new PathLocationListener(viewer) : locationListener;
    hoverInformationControlManager.install(control);
  }

  protected String getSymbolicFont()
  {
    if (font == null)
    {
      return DEFAULT_FONT;
    }
    else
    {
      FontData[] fontData = font.getFontData();
      FontRegistry fontRegistry = JFaceResources.getFontRegistry();
      for (Object name : fontRegistry.getKeySet())
      {
        FontData[] registerFontData = fontRegistry.getFontData((String)name);
        if (Arrays.equals(fontData, registerFontData))
        {
          return (String)name;
        }
      }
      return DEFAULT_FONT;
    }
  }

  public void showInformation()
  {
    hoverInformationControlManager.showInformation();
  }

  public String getStyleSheet()
  {
    if (defaultStyleSheet == null)
    {
      URL styleSheetURL = CommonUIPlugin.getPlugin().getBundle().getEntry("EMFCommonUIHoverStyleSheet.css");
      if (styleSheetURL != null)
      {
        BufferedReader reader = null;
        try
        {
          reader = new BufferedReader(new InputStreamReader(styleSheetURL.openStream()));
          StringBuffer buffer = new StringBuffer(1500);
          String line = reader.readLine();
          while (line != null)
          {
            buffer.append(line);
            buffer.append('\n');
            line = reader.readLine();
          }
          defaultStyleSheet = buffer.toString();
        }
        catch (IOException ex)
        {
          return "";
        }
        finally
        {
          try
          {
            if (reader != null)
              reader.close();
          }
          catch (IOException exception)
          {
            // Ignore
          }
        }
      }
    }
    return defaultStyleSheet;
  }

  protected void toolTipShow(Shell tip, Event event)
  {
    if (!tip.isDisposed())
    {
      currentCell = getToolTipArea(event);
      tip.setVisible(true);
    }
  }

  protected ViewerCell getToolTipArea(Event event)
  {
    return viewer.getCell(new Point(event.x, event.y));
  }

  protected boolean shouldCreateToolTip(Event event)
  {
    ViewerCell cell = getToolTipArea(event);
    if (cell != null && !cell.equals(currentCell))
    {
      control.setToolTipText("");
      currentCell = cell;
      ViewerRow row = cell.getViewerRow();
      if (row != null)
      {
        Object element = row.getItem().getData();
        CellLabelProvider labelProvider = viewer.getLabelProvider(cell.getColumnIndex());
        text = labelProvider.getToolTipText(element);
        boolean useNative = labelProvider.useNativeToolTip(element);
        if (useNative || text == null)
        {
          control.setToolTipText(text);
        }
        else
        {
          foregroundColor = labelProvider.getToolTipForegroundColor(element);
          backgroundColor = labelProvider.getToolTipBackgroundColor(element);
          font = labelProvider.getToolTipFont(element);
          return text != null;
        }
      }
    }
    else
    {
      currentCell = cell;
    }

    return false;
  }

  public void dispose()
  {
    hoverInformationControlManager.dispose();
  }
}
