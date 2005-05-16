/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * ModelImporterPage.java,v 1.1 2005/05/12 17:10:24 marcelop Exp
 */
package org.eclipse.emf.importer.ui.contribution.base;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import org.eclipse.emf.importer.ImporterPlugin;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.util.ImporterUtil;


/**
 * @since 2.1.0
 */
public abstract class ModelImporterPage extends WizardPage implements Listener
{
  public static final int CAUSE_UNKNOWN = 0;
  public static final int CAUSE_BACK = 1;
  public static final int CAUSE_NEXT = 2;
  public static final int CAUSE_FINISH = 3;
  public static final int CAUSE_CANCEL = 4;
    
  protected ModelImporter modelImporter;
  protected boolean neverVisible = true;
  protected boolean forwardDirection = true;
  protected boolean handlingEvent = true;

  public ModelImporterPage(ModelImporter modelImporter, String pageName)
  {
    super(pageName);
    this.modelImporter = modelImporter;
    setPageComplete(false);
  }

  public void dispose()
  {
    modelImporter = null;
    super.dispose();
  }

  public ModelImporter getModelImporter()
  {
    return modelImporter;
  }

  protected void pageActivated(boolean firstTime, int cause)
  {
  }

  protected void pageDeactivated(int cause)
  {
  }
  
  public IWizardPage getNextPage()
  {
    forwardDirection = true;
    return super.getNextPage();
  }
  
  public IWizardPage getPreviousPage()
  {
    forwardDirection = false;
    return super.getPreviousPage();
  }

  public boolean isPageComplete()
  {
    return getErrorMessage() == null;
  }
  
  public boolean isHandlingEvent()
  {
    return handlingEvent;
  }
  
  public void setHandlingEvent(boolean handlingEvent)
  {
    this.handlingEvent = handlingEvent;
  }

  public void handleEvent(Event event)
  {
    if (isHandlingEvent())
    {
      doHandleEvent(event);
    }
  }
  
  protected void doHandleEvent(Event event)
  {
    
  }

  protected void handleStatus(IStatus status)
  {
    handleStatus(status, null, null, null);
  }
  
  protected void handleStatus(IStatus status, String message, String dialogTitle, String dialogMessage)
  {
    if (status.isOK())
    {
      handleOKStatus(status, message, dialogTitle, dialogMessage);
    }
    else
    {
      handleNotOKStatus(status, decodeAction(status), message, dialogTitle, dialogMessage);
    }
  }
  
  protected ImporterUtil.DecodedAction decodeAction(IStatus status)
  {
    int actionCode = ImporterUtil.computeActionCode(status);
    return ImporterUtil.decodeAction(actionCode);
  }
  
  protected void handleOKStatus(IStatus status, String message, String dialogTitle, String dialogMessage)
  {
    setMessage(null);
    setErrorMessage(null);    
  }
  
  protected void handleNotOKStatus(IStatus status, ImporterUtil.DecodedAction decodedAction, String message, String dialogTitle, String dialogMessage)
  {
    int messageType = 0;
    switch(status.getSeverity())
    {
      case IStatus.INFO:
      {
        messageType = IMessageProvider.INFORMATION;
        if (dialogTitle == null) ImporterPlugin.INSTANCE.getString("_UI_DialogInformation_title");
        break;
      }
      case IStatus.WARNING:
        messageType = IMessageProvider.WARNING;
        if (dialogTitle == null) dialogTitle = ImporterPlugin.INSTANCE.getString("_UI_DialogWarningtitle");
        break;
      case IStatus.ERROR:
        messageType = IMessageProvider.ERROR;
        if (dialogTitle == null) dialogTitle = ImporterPlugin.INSTANCE.getString("_UI_DialogError_title");
        break;
    }

    if (message == null) message = status.getMessage();
    setErrorMessage(null);
    setMessage(null);
    switch(decodedAction.message)
    {
      case ImporterUtil.ACTION_MESSAGE_SET:
      {
        setMessage(message);
        break;
      }
      case ImporterUtil.ACTION_DEFAULT:
      case ImporterUtil.ACTION_MESSAGE_SET_TYPED:
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
      case ImporterUtil.ACTION_MESSAGE_SET_ERROR:
      {
        setErrorMessage(message);
        break;
      }
    }

    if (dialogMessage == null) dialogMessage = status.getMessage();
    switch(decodedAction.dialog)
    {
      case ImporterUtil.ACTION_DEFAULT:
      case ImporterUtil.ACTION_DIALOG_SHOW_IF_HAS_CHILD:
      {
        if (status.getChildren().length > 0)
        {
          ErrorDialog.openError(getShell(), dialogTitle, dialogMessage, status);
        }
        break;
      }
      case ImporterUtil.ACTION_DIALOG_SHOW:
      {
        ErrorDialog.openError(getShell(), dialogTitle, dialogMessage, status);
        break;
      }
      case ImporterUtil.ACTION_DIALOG_SHOW_ERROR:
      {
        new ErrorDialog(getShell(),
          dialogTitle,
          dialogMessage,
          status, IStatus.INFO | IStatus.WARNING | IStatus.ERROR)
          {
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