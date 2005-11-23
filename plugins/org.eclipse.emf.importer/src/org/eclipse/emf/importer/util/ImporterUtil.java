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
 * ImporterUtil.java,v 1.7 2005/11/11 16:57:18 marcelop Exp
 */
package org.eclipse.emf.importer.util;

import java.util.Iterator;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.DiagnosticException;
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
  
  public static int computeActionCode(Diagnostic diagnostic)
  {
    if (ImporterPlugin.ID.equals(diagnostic.getSource()))
    {
      int actionCode = diagnostic.getCode();
      for (Iterator i = diagnostic.getChildren().iterator(); i.hasNext();)
      {
        Diagnostic child = (Diagnostic)i.next();
        actionCode |= computeActionCode(child);
      }
      return actionCode;
    }
    else
    {
      return ACTION_DEFAULT;
    }
  }
  
  public static Diagnostic createDiagnostic(Diagnostic baseDiagnostic, String source, int code)
  {
    BasicDiagnostic basicDiagnostic = new BasicDiagnostic(
      baseDiagnostic.getSeverity(), source, code, 
      baseDiagnostic.getMessage(), baseDiagnostic.getData().toArray());
    basicDiagnostic.addAll(baseDiagnostic);
    return basicDiagnostic;
  }
  
  public static Diagnostic mergeDiagnostic(Diagnostic baseDiagnostic, Diagnostic diagnosticToBeMerged)
  {
    if (diagnosticToBeMerged == null)
    {
      return baseDiagnostic;
    }
    else
    {
      if (baseDiagnostic == null)
      {
        return diagnosticToBeMerged;
      }
      else if (baseDiagnostic instanceof DiagnosticChain)
      {
        ((DiagnosticChain)baseDiagnostic).merge(diagnosticToBeMerged);
        return baseDiagnostic;
      }
      else
      {
        BasicDiagnostic basicDiagnostic = new BasicDiagnostic(
          baseDiagnostic.getSeverity(), baseDiagnostic.getSource(), baseDiagnostic.getCode(), 
          baseDiagnostic.getMessage(), baseDiagnostic.getData().toArray());
        basicDiagnostic.addAll(baseDiagnostic);
        basicDiagnostic.add(diagnosticToBeMerged);
        return basicDiagnostic;
      }
    }
  }
    
  public static Diagnostic createErrorDiagnostic(Throwable throwable, boolean showErrorDialog)
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
    
    Diagnostic diagnostic = null;
    if (throwable instanceof DiagnosticException)
    {
      diagnostic = ((DiagnosticException)throwable).getDiagnostic();
    }

    if (diagnostic == null)
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
  
      diagnostic = new BasicDiagnostic(Diagnostic.ERROR,
        ImporterPlugin.ID, showErrorDialog ? ACTION_DIALOG_SHOW_ERROR : ACTION_DEFAULT,
        message, new Object[]{throwable});
    }
    
    return diagnostic;
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
