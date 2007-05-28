/**
 * <copyright>
 *
 * Copyright (c) 2005-2007 IBM Corporation and others.
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
 * $Id: EditUIUtil.java,v 1.5 2007/05/28 18:26:58 emerks Exp $
 */

package org.eclipse.emf.edit.ui.util;

import java.lang.reflect.Method;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.osgi.framework.Bundle;

/**
 * @since 2.2.0
 */
public class EditUIUtil
{
  /**
   * Opens the default editor for the resource that contains the specified
   * EObject.  This method only works if the resource's URI is a 
   * platform resource URI.
   */
  public static boolean openEditor(EObject eObject) throws PartInitException
  {
    if (eObject != null)
    {
      Resource resource = eObject.eResource();
      if (resource != null)
      {
        URI uri = resource.getURI();
        if (uri != null)
        {
          IEditorInput editorInput = null;
          if (uri.isPlatformResource())
          {
            String path = uri.toPlatformString(true);
            IResource workspaceResource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(path));
            if (workspaceResource instanceof IFile)
            {
              editorInput = EclipseUtil.createFileEditorInput((IFile)workspaceResource);
            }
          }
          else
          {
            editorInput = new URIEditorInput(uri);
          }
          if (editorInput != null)
          {
            IWorkbench workbench = PlatformUI.getWorkbench();
            IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
            IEditorPart editorPart = page.openEditor(editorInput, workbench.getEditorRegistry().getDefaultEditor(uri.lastSegment()).getId());
            return editorPart != null;
          }
        }
      }
    }
    return false;
  }

  public static URI getURI(IEditorInput editorInput)
  {
    URI result = null;
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      result = EclipseUtil.getURI(editorInput);
    }
    if (result == null)
    {
      if (editorInput instanceof URIEditorInput)
      {
        result = ((URIEditorInput)editorInput).getURI().trimFragment();
      }
      else
      {
        result = URI.createURI(editorInput.getName());
      }
    }
    return result;
  }
  
  private static class EclipseUtil
  {
    static final Class<?> FILE_CLASS;
    static
    {
      Class<?> fileClass = null;
      try
      {
        fileClass = IFile.class;
      }
      catch (Throwable exception)
      {
        // Ignore any exceptions and assume the class isn't available.
      }
      FILE_CLASS = fileClass;
    }

    static final Class<?> FILE_REVISION_CLASS;
    static final Method FILE_REVISION_GET_URI_METHOD;
    static 
    {
      Class<?> fileRevisionClass = null;
      Method fileRevisionGetURIMethod = null;
      Bundle bundle = Platform.getBundle("org.eclipse.team.core");
      if (bundle != null && (bundle.getState() & (Bundle.ACTIVE | Bundle.STARTING | Bundle.RESOLVED)) != 0)
      {
        try
        {
          fileRevisionClass = bundle.loadClass("org.eclipse.team.core.history.IFileRevision");
          fileRevisionGetURIMethod = fileRevisionClass.getMethod("getURI");
        }
        catch (Throwable exeption)
        {
          // Ignore any exceptions and assume the class isn't available.
        }
      }
      FILE_REVISION_CLASS = fileRevisionClass;
      FILE_REVISION_GET_URI_METHOD = fileRevisionGetURIMethod;
    }

    static final Class<?> URI_EDITOR_INPUT_CLASS;
    static
    {
      Class<?> uriEditorInputClass = null;
      try
      {
        uriEditorInputClass = IURIEditorInput.class;
      }
      catch (Throwable exception)
      {
        // The class is not available.
      }
      URI_EDITOR_INPUT_CLASS = uriEditorInputClass;
    }

    public static URI getURI(IEditorInput editorInput)
    {
      if (FILE_CLASS != null)
      {
        IFile file = (IFile)editorInput.getAdapter(FILE_CLASS);
        if (file != null)
        {
          return URI.createPlatformResourceURI(file.getFullPath().toString(), true);
        }
      }
      if (FILE_REVISION_CLASS != null)
      {
        Object fileRevision = editorInput.getAdapter(FILE_REVISION_CLASS);
        if (fileRevision != null)
        {
          try
          {
            return URI.createURI(((java.net.URI)FILE_REVISION_GET_URI_METHOD.invoke(fileRevision)).toString());
          }
          catch (Throwable exception)
          {
            EMFEditUIPlugin.INSTANCE.log(exception);
          }
        }
      }
      if (URI_EDITOR_INPUT_CLASS != null)
      {
        if (editorInput instanceof IURIEditorInput)
        {
          return URI.createURI(((IURIEditorInput)editorInput).getURI().toString()).trimFragment();
        }
      }

      return null;
    }
    
    public static IEditorInput createFileEditorInput(IFile file)
    {
      return new FileEditorInput(file);
    }
  }
}
