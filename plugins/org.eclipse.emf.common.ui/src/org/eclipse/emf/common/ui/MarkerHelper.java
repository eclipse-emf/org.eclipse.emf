/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: MarkerHelper.java,v 1.1 2006/04/27 16:17:09 marcelop Exp $
 */
package org.eclipse.emf.common.ui;

import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IFileEditorInput;

import org.eclipse.emf.common.util.Diagnostic;

/**
 * Provides methods to simplify the work with {@link IMarker}s.  The main goal is to
 * simplify the creation of markers using the information described in 
 * {@link Diagnostic}s. 
 * 
 * @since 2.2.0
 */
public class MarkerHelper
{
  public void createMarkers(IEditorInput editorInput, Diagnostic diagnostic) throws CoreException
  {
    if (editorInput instanceof IFileEditorInput)
    {
      createMarkers(((IFileEditorInput)editorInput).getFile(), diagnostic);
    }
    else
    {
      throw new CoreException
        (new Status
          (IStatus.ERROR, 
           "org.eclipse.emf.common.ui", 
           0,
           CommonUIPlugin.getPlugin().getString("_UI_CreateMarkerError_desc"),null));
    }
  }
  
  public void createMarkers(IFile file, Diagnostic diagnostic) throws CoreException
  {
    if (diagnostic.getChildren().isEmpty())
    {
      IMarker marker = file.createMarker(getMarkerID());
      int severity = diagnostic.getSeverity();
      if (severity < Diagnostic.WARNING)
      {
        marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO);
      }
      else if (severity < Diagnostic.ERROR)
      {
        marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
      }
      else
      {
        marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
      }
      marker.setAttribute(IMarker.MESSAGE, diagnostic.getMessage());
      adjustMarker(marker, diagnostic);
    }
    else
    {
      String parentMessage = diagnostic.getMessage() + ". ";
      for (Iterator i = diagnostic.getChildren().iterator(); i.hasNext(); )
      {
        Diagnostic childDiagnostic = (Diagnostic)i.next();
        IMarker marker = file.createMarker(getMarkerID());
        int severity = childDiagnostic.getSeverity();
        if (severity < Diagnostic.WARNING)
        {
          marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO);
        }
        else if (severity < Diagnostic.ERROR)
        {
          marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
        }
        else
        {
          marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
        }
        marker.setAttribute(IMarker.MESSAGE, parentMessage + childDiagnostic.getMessage());
        adjustMarker(marker, childDiagnostic);
      }
    }
  }
  
  public void deleteMarkers(IEditorInput editorInput)
  {
    if (editorInput instanceof IFileEditorInput)
    {
      deleteMarkers(((IFileEditorInput)editorInput).getFile(), true,  IResource.DEPTH_ZERO);
    }
  }

  public void deleteMarkers(IFile file)
  {
    deleteMarkers(file, true,  IResource.DEPTH_ZERO);
  }
  
  public void deleteMarkers(IFile file, boolean includeSubtypes, int depth)
  {
    try
    {
      file.deleteMarkers(getMarkerID(), includeSubtypes, depth);
    }
    catch (CoreException e)
    {
      CommonUIPlugin.INSTANCE.log(e);
    }
  }
  
  protected String getMarkerID()
  {
    return "org.eclipse.core.resources.problemmarker";
  }
  
  protected void adjustMarker(IMarker marker, Diagnostic diagnostic) throws CoreException
  {
  }
}