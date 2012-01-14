/**
 * Copyright (c) 2005-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */

package org.eclipse.emf.converter.util;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import org.eclipse.emf.common.ui.DiagnosticComposite;
import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.converter.ConverterPlugin;
import org.eclipse.emf.converter.util.ConverterUtil.ShellFinder;

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
  
  public static class DiagnosticHandler
  {
    public void handleDiagnostic(Diagnostic diagnostic)
    {
      handleDiagnostic(diagnostic, null, null, null);
    }
    
    public void handleDiagnostic(Diagnostic diagnostic, String message, String dialogTitle, String dialogMessage)
    {
      if (diagnostic.getSeverity() == Diagnostic.OK)
      {
        handleOKDiagnostic(diagnostic, message, dialogTitle, dialogMessage);
      }
      else
      {
        handleNotOKDiagnostic(diagnostic, decodeAction(diagnostic), message, dialogTitle, dialogMessage);
      }
    }
    
    protected boolean doMessages()
    {
      return false;
    }
    
    protected void setMessage(String message)
    {
      // Subclasses may override
    }
    
    protected void setMessage(String message, int messageType)
    {
      // Subclasses may override
    }

    protected void setErrorMessage(String message)
    {
      // Subclasses may override
    }
    
    protected boolean doDialog()
    {
      return true;
    }
    
    protected Shell getShell()
    {
      return (Shell)ShellFinder.getActiveShell();
    }
    
    protected ConverterUtil.DecodedAction decodeAction(Diagnostic diagnostic)
    {
      int actionCode = ConverterUtil.computeActionCode(diagnostic);
      return ConverterUtil.decodeAction(actionCode);
    }
    
    protected void handleOKDiagnostic(Diagnostic diagnostic, String message, String dialogTitle, String dialogMessage)
    {
      setMessage(null);
      setErrorMessage(null);    
    }
    
    protected void handleNotOKDiagnostic(Diagnostic diagnostic, ConverterUtil.DecodedAction decodedAction, String message, String dialogTitle, String dialogMessage)
    {
      int messageType = 0;
      switch(diagnostic.getSeverity())
      {
        case Diagnostic.INFO:
        {
          messageType = IMessageProvider.INFORMATION;
          if (dialogTitle == null) dialogTitle = ConverterPlugin.INSTANCE.getString("_UI_DialogInformation_title");
          break;
        }
        case Diagnostic.WARNING:
          messageType = IMessageProvider.WARNING;
          if (dialogTitle == null) dialogTitle = ConverterPlugin.INSTANCE.getString("_UI_DialogWarning_title");
          break;
        case Diagnostic.ERROR:
          messageType = IMessageProvider.ERROR;
          if (dialogTitle == null) dialogTitle = ConverterPlugin.INSTANCE.getString("_UI_DialogError_title");
          break;
      }

      if (doMessages())
      {
        if (message == null) message = diagnostic.getMessage();
        setErrorMessage(null);
        setMessage(null);
        switch(decodedAction.message)
        {
          case ConverterUtil.ACTION_MESSAGE_SET:
          {
            setMessage(message);
            break;
          }
          case ConverterUtil.ACTION_DEFAULT:
          case ConverterUtil.ACTION_MESSAGE_SET_TYPED:
          {
            if (messageType == IMessageProvider.ERROR)
            {
              setErrorMessage(message);
            }
            else
            {
              setMessage(message, messageType);
            }
            break;
          }
          case ConverterUtil.ACTION_MESSAGE_SET_ERROR:
          {
            setErrorMessage(message);
            break;
          }
        }
      }

      if (doDialog())
      {
        switch(decodedAction.dialog)
        {
          case ConverterUtil.ACTION_DEFAULT:
          case ConverterUtil.ACTION_DIALOG_SHOW_IF_HAS_CHILD:
          {
            if (!diagnostic.getChildren().isEmpty())
            {
              DiagnosticDialog.openProblem(getShell(), dialogTitle, dialogMessage, diagnostic);
            }
            break;
          }
          case ConverterUtil.ACTION_DIALOG_SHOW:
          {
            DiagnosticDialog.openProblem(getShell(), dialogTitle, dialogMessage, diagnostic);
            break;
          }
          case ConverterUtil.ACTION_DIALOG_SHOW_ERROR:
          {
            new DiagnosticDialog(getShell(),
              dialogTitle,
              dialogMessage,
              diagnostic,
              DiagnosticComposite.ERROR_WARNING_MASK)
              {
                @Override
                protected Image getImage()
                {
                  return  getErrorImage();
                }
              }.open();
            break;
          }
        }
      }
    }
  }
}
