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
 * $Id: ConverterUtil.java,v 1.5 2006/11/03 11:39:17 emerks Exp $
 */
package org.eclipse.emf.converter.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.converter.ConverterPlugin;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * @since 2.2.0
 */
public class ConverterUtil
{
  public static class EPackageList extends UniqueEList.FastCompare
  {
    public EPackageList()
    {
      super();
    }

    public EPackageList(Collection collection)
    {
      super(collection);
    }

    public EPackageList(int initialCapacity)
    {
      super(initialCapacity);
    }

    protected Object[] newData(int capacity)
    {
      return new EPackage [capacity];
    }
  }
  
  public static class GenPackageList extends UniqueEList.FastCompare
  {
    public GenPackageList()
    {
      super();
    }

    public GenPackageList(Collection collection)
    {
      super(collection);
    }

    public GenPackageList(int initialCapacity)
    {
      super(initialCapacity);
    }

    protected Object[] newData(int capacity)
    {
      return new GenPackage [capacity];
    }
  }
  
  public static class WorkspaceResourceValidator
  {
    /**
     * Invokes the Platform validateEdit method for all the read-only files 
     * referred by a given resource in the list.  Returns null if the resources
     * can be saved or a comma separated list of the files that are read-only.  
     * @param emfResources
     * @return String
     */
    public static String validate(List emfResources)
    {
      org.eclipse.core.resources.IWorkspace workspace = org.eclipse.core.resources.ResourcesPlugin.getWorkspace();
      org.eclipse.core.resources.IWorkspaceRoot workspaceRoot= workspace.getRoot();
      
      List workspaceFiles = new ArrayList(emfResources.size());
      List externalFiles = new ArrayList(emfResources.size());
      for (Iterator i = emfResources.iterator(); i.hasNext();)
      {
        Resource resource = (Resource)i.next();
        URI uri = resource.getURI().trimFragment();
        if (uri.isFile())
        {
          File file = new File(uri.toFileString());
          if (file.isFile() && !file.canWrite())
          {
            externalFiles.add(file);
          }        
        }
        else if (uri.isPlatformResource())
        {
          String path = uri.toPlatformString(true);
          org.eclipse.core.resources.IResource workspaceResource = workspaceRoot.findMember(new org.eclipse.core.runtime.Path(path));
          if (workspaceResource != null && workspaceResource.getType() == org.eclipse.core.resources.IResource.FILE && workspaceResource.getResourceAttributes().isReadOnly())
          {
            workspaceFiles.add(workspaceResource);
          }
        }
      }
      
      StringBuffer readOnlyFiles = new StringBuffer();
      if (!workspaceFiles.isEmpty())
      {
        Object context = null;
        if (org.eclipse.core.runtime.Platform.getBundle("org.eclipse.swt") != null)
        {
          context = ShellFinder.getActiveShell();
        }
        
        org.eclipse.core.resources.IFile[] files = (org.eclipse.core.resources.IFile[])workspaceFiles.toArray(new org.eclipse.core.resources.IFile [workspaceFiles.size()]);
        if (!workspace.validateEdit(files, context).isOK())
        {
          for (int i = 0; i < files.length; i++)
          {
            if (files[i].isReadOnly())
            {
              readOnlyFiles.append(", ").append(files[i].getFullPath().toString());
            }
          }
        }
      }
      if (!externalFiles.isEmpty())
      {
        for (Iterator i = externalFiles.iterator(); i.hasNext();)
        {
          File file = (File)i.next();
          readOnlyFiles.append(", ").append(file.getAbsolutePath());
        }
      }
      
      return readOnlyFiles.length() == 0 ? 
        null : 
        readOnlyFiles.deleteCharAt(0).deleteCharAt(0).toString();
    }    
  }
  
  public static class ShellFinder
  {
    public static Object getActiveShell()
    {
      if (org.eclipse.core.runtime.Platform.getBundle("org.eclipse.swt") != null)
      {
        try
        {
          return org.eclipse.swt.widgets.Display.getCurrent().getActiveShell();
        }
        catch (Throwable t)
        {
        }
      }      
      return null;
    }
  }
  
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
    if (ConverterPlugin.ID.equals(diagnostic.getSource()))
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
        message = ConverterPlugin.INSTANCE.getString("_UI_GenericException_message", new Object[]{exceptionName});
      }
  
      diagnostic = new BasicDiagnostic(Diagnostic.ERROR,
        ConverterPlugin.ID, showErrorDialog ? ACTION_DIALOG_SHOW_ERROR : ACTION_DEFAULT,
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
  
  public static List computeRequiredPackages(EPackage ePackage)
  {
    List referencedEPackages = new ConverterUtil.EPackageList();
    for (Iterator j = ePackage.eAllContents(); j.hasNext();)
    {
      EObject eObject = (EObject)j.next();
      for (Iterator k = eObject.eCrossReferences().iterator(); k.hasNext();)
      {
        Object o = k.next();
        if (o instanceof EClassifier)
        {
          EClassifier eClassifier = (EClassifier)o;
          referencedEPackages.add(eClassifier.getEPackage());
        }
      }
    }
    
    referencedEPackages.remove(ePackage);
    for (Iterator i = referencedEPackages.iterator(); i.hasNext();)
    {
      EPackage referencedEPackage = (EPackage)i.next();
      if (referencedEPackage.getNsURI().equals(ePackage.getNsURI()))
      {
        i.remove();
      }
    }
    return referencedEPackages.isEmpty() ? Collections.EMPTY_LIST : referencedEPackages;
  }
  
  public static String getQualifiedName(EPackage ePackage)
  {
    if (ePackage == null)
    {
      return null;
    }
    else
    {
      StringBuffer label = new StringBuffer(ePackage.getName());
      EPackage parentEPackage = ePackage.getESuperPackage();
      while (parentEPackage != null)
      {
        label.insert(0, ".").insert(0, parentEPackage.getName());
        parentEPackage = parentEPackage.getESuperPackage();
      }
      return label.toString();
    }
  }
}
