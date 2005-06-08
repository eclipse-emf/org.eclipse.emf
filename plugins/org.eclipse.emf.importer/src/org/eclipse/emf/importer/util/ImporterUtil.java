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
 * ImporterUtil.java,v 1.1 2005/05/16 14:19:18 marcelop Exp
 */
package org.eclipse.emf.importer.util;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.importer.ImporterPlugin;

/**
 * Utility methods and classes.  This class cannot import UI code because it is used on headless
 * scenarios. 
 * 
 * @since 2.1.0
 */
public class ImporterUtil
{
  public static final int ACTION_DEFAULT                  = 0;
  public static final int ACTION_MESSAGE_NONE             = 1;      //0000 0000 0001
  public static final int ACTION_MESSAGE_SET              = 2;      //0000 0000 0010
  public static final int ACTION_MESSAGE_SET_TYPED        = 4;      //0000 0000 0100
  public static final int ACTION_MESSAGE_SET_ERROR        = 8;      //0000 0000 1000
  public static final int ACTION_DIALOG_NONE              = 1 << 8; //0001 0000 0000
  public static final int ACTION_DIALOG_SHOW_IF_HAS_CHILD = 2 << 8; //0010 0000 0000
  public static final int ACTION_DIALOG_SHOW              = 4 << 8; //0100 0000 0000
  public static final int ACTION_DIALOG_SHOW_ERROR        = 8 << 8; //1000 0000 0000
  
  private static final int ACTION_MESSAGE_MASK            = 0x00F;  //0000 0000 1111
  private static final int ACTION_DIALOG_MASK             = 0xF00;  //1111 0000 0000
  
  public static class DecodedAction
  {
    public int message;
    public int dialog; 
  }
  
  public static DecodedAction decodeAction(int actionCode)
  {
    DecodedAction decodedAction = new DecodedAction();
    
    decodedAction.message = actionCode & ACTION_MESSAGE_MASK;
    if (ACTION_MESSAGE_SET_ERROR == (decodedAction.message & ACTION_MESSAGE_SET_ERROR))
    {
      decodedAction.message = ACTION_MESSAGE_SET_ERROR;
    }
    else if (ACTION_MESSAGE_SET_TYPED == (decodedAction.message & ACTION_MESSAGE_SET_TYPED))
    {
      decodedAction.message = ACTION_MESSAGE_SET_TYPED;
    }
    else if (ACTION_MESSAGE_SET == (decodedAction.message & ACTION_MESSAGE_SET))
    {
      decodedAction.message = ACTION_MESSAGE_SET;
    }
    else if (ACTION_MESSAGE_NONE == (decodedAction.message & ACTION_MESSAGE_NONE))
    {
      decodedAction.message = ACTION_MESSAGE_NONE;
    }
    else
    {
      decodedAction.message = ACTION_DEFAULT;
    }
    
    decodedAction.dialog = actionCode & ACTION_DIALOG_MASK;
    if (ACTION_DIALOG_SHOW_ERROR == (decodedAction.dialog & ACTION_DIALOG_SHOW_ERROR))
    {
      decodedAction.dialog = ACTION_DIALOG_SHOW_ERROR;
    }
    else if(ACTION_DIALOG_SHOW == (decodedAction.dialog & ACTION_DIALOG_SHOW))
    {
      decodedAction.dialog = ACTION_DIALOG_SHOW;
    }
    else if(ACTION_DIALOG_SHOW_IF_HAS_CHILD == (decodedAction.dialog & ACTION_DIALOG_SHOW_IF_HAS_CHILD))
    {
      decodedAction.dialog = ACTION_DIALOG_SHOW_IF_HAS_CHILD;
    }    
    else if(ACTION_DIALOG_NONE == (decodedAction.dialog & ACTION_DIALOG_NONE))
    {
      decodedAction.dialog = ACTION_DIALOG_NONE;
    }    
    else
    {
      decodedAction.dialog = ACTION_DEFAULT;
    }
    
    return decodedAction;
  }
  
  public static int computeActionCode(IStatus status)
  {
    if (ImporterPlugin.ID.equals(status.getPlugin()))
    {
      int actionCode = status.getCode();
      if (status.isMultiStatus())
      {
        IStatus[] children = status.getChildren();
        for (int i = 0; i < children.length; i++)
        {
          actionCode |= computeActionCode(children[i]);
        }
      }
      return actionCode;
    }
    else
    {
      return ImporterUtil.ACTION_DEFAULT;
    }
  }
  
  
  public static class MergedStatus extends MultiStatus
  {
    public MergedStatus(IStatus baseStatus)
    {
      super(baseStatus.getPlugin(), 
        baseStatus.getCode(), 
        baseStatus.getChildren(),
        baseStatus.getMessage(),
        baseStatus.getException());
      
      setSeverity(baseStatus.getSeverity());
    }
    
    public void setPlugin(String pluginId)
    {
      super.setPlugin(pluginId);
    }
    
    public void setCode(int code)
    {
      super.setCode(code);
    }
  }
  
  public static MultiStatus mergeStatus(IStatus baseStatus, IStatus statusToBeMerged)
  {
    if (baseStatus == null)
    {
      if (statusToBeMerged == null)
      {
        return null;
      }
      else
      {
        return new MergedStatus(statusToBeMerged);
      }
    }

    MultiStatus multiStatus = null; 
    if (baseStatus instanceof MultiStatus)
    {
      multiStatus = ((MultiStatus)baseStatus);
    }
    else
    {
      multiStatus = new MergedStatus(baseStatus);
    }

    if (statusToBeMerged != null)
    {
      multiStatus.merge(statusToBeMerged);
    }
    return multiStatus;
  }

  /**
   * Creates a new status based on the specified status, setting a new pluginID and
   * code.
   * @param baseStatus
   * @param pluginID
   * @param code
   * @return IStatus
   */
  public static IStatus createStatus(IStatus baseStatus, String pluginID, int code)
  {
    MergedStatus mergedStatus = new MergedStatus(baseStatus);
    mergedStatus.setPlugin(pluginID);
    mergedStatus.setCode(code);
    return mergedStatus;
  }
  
  public static IStatus createErrorStatus(Throwable throwable, boolean showErrorDialog)
  {
    while (true)
    {
      Throwable cause = 
        throwable instanceof WrappedException ? ((WrappedException)throwable).exception() :
        throwable.getCause() != null ? throwable.getCause() :
        null;
        
      if (cause != null && cause != throwable)
      {
        throwable = cause;
      }
      else
      {
        break;
      }
    }
    
    IStatus status = null;
    if (throwable instanceof CoreException)
    {
      IStatus originalStatus = ((CoreException)throwable).getStatus();
      if (originalStatus != null && originalStatus.getSeverity() == IStatus.ERROR)
      {
        status = originalStatus;
      }
    }

    if (status == null)
    {
      String message = throwable.getLocalizedMessage();
      if (message == null)
      {
        message = throwable.getMessage();
      }
      if (message == null)
      {
        String exceptionName = throwable.getClass().getName();
        int index = exceptionName.lastIndexOf('.');
        if (index >= 0)
        {
          exceptionName = exceptionName.substring(index+1);
        }
        message = ImporterPlugin.INSTANCE.getString("_UI_GenericException_message", new Object[]{exceptionName});
      }
  
      status = new Status(IStatus.ERROR,
        ImporterPlugin.ID, showErrorDialog ? ACTION_DIALOG_SHOW_ERROR : ACTION_DEFAULT,
        message, throwable);
    }
    
    return status;
  }
  
  public static ResourceSet createResourceSet()
  {
    ResourceSet result = new ResourceSetImpl();
    result.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap());
    return result;    
  }
  
  public static String validPluginID(String base)
  {
    StringBuffer sb = new StringBuffer(base);
    for (int i = sb.length() - 1; i >= 0; i--)
    {
      char c = sb.charAt(i);
      if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9') || c == '_' || c == '.')
      {
        //do nothing
      }
      else if (c == ' ')
      {
        sb.deleteCharAt(i);
      }
      else if (c == '-')
      {
        sb.setCharAt(i, '_');
      }
    }
    return sb.toString();
  }
}
