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
 * $Id: EditUIMarkerHelper.java,v 1.7 2006/05/02 12:43:47 emerks Exp $
 */
package org.eclipse.emf.edit.ui.util;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

import org.eclipse.emf.common.ui.MarkerHelper;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;

/**
 * Extension of {@link MarkerHelper} that provides extra functionalities usefull when
 * using EMF classes such as {@link Resource}.
 *   
 * @since 2.2.0
 */
public class EditUIMarkerHelper extends MarkerHelper
{
  protected IFile getFile(Object datum)
  {
    if (datum instanceof Resource)
    {
      Resource resource = (Resource)datum;
      URI uri = resource.getURI();
      uri = resource.getResourceSet().getURIConverter().normalize(uri);
      return getFile(uri);
    }
    else if (datum instanceof EObject)
    {
      return getFile(((EObject)datum).eResource());
    }
    else if (datum instanceof Resource.Diagnostic)
    {
      String location = ((Resource.Diagnostic)datum).getLocation();
      if (location != null)
      {
        return getFile(URI.createURI(location));
      }
    }
    
    return super.getFile(datum);
  }
  
  protected void adjustMarker(IMarker marker, Diagnostic diagnostic, Diagnostic parentDiagnostic) throws CoreException
  {
    if (!adjustMarker(marker, diagnostic) && parentDiagnostic != null)
    {
      adjustMarker(marker, parentDiagnostic);
    }
  }

  protected boolean adjustMarker(IMarker marker, Diagnostic diagnostic) throws CoreException
  {
    if (diagnostic.getData() != null)
    {
      for (Iterator i = diagnostic.getData().iterator(); i.hasNext();)
      {
        Object element = (Object)i.next();
        if (element instanceof Resource.Diagnostic)
        {
          Resource.Diagnostic resourceDiagnostic = (Resource.Diagnostic)element;
          if (resourceDiagnostic.getLocation() != null)
          {
            marker.setAttribute
             (IMarker.LOCATION, 
               EMFEditUIPlugin.getPlugin().getString
                 ("_UI_MarkerLocation", 
                  new String[] 
                  { 
                    Integer.toString(resourceDiagnostic.getLine()), 
                    Integer.toString(resourceDiagnostic.getColumn())
                  }));
            
            marker.setAttribute(IMarker.LINE_NUMBER, resourceDiagnostic.getLine());
            return true;
          }
        }
      }
    }
    return false;
  }  
  
  public boolean hasMarkers(Object object, boolean includeSubtypes, int depth)
  {
    if (object instanceof ResourceSet)
    {
      ResourceSet resourceSet = (ResourceSet)object;
      for (Iterator i = resourceSet.getResources().iterator(); i.hasNext(); )
      {
        if (hasMarkers(i.next(), includeSubtypes, depth))
        {
          return true;
        }
      }
      return false;
    }
    else
    {
      return super.hasMarkers(object, includeSubtypes, depth);
    }
  }

  public void deleteMarkers(Object object, boolean includeSubtypes, int depth)
  {
    if (object instanceof ResourceSet)
    {
      ResourceSet resourceSet = (ResourceSet)object;
      for (Iterator i = resourceSet.getResources().iterator(); i.hasNext(); )
      {
        deleteMarkers(i.next(), includeSubtypes, depth);
      }
    }
    else if (object instanceof Diagnostic)
    {
      List data = ((Diagnostic)object).getData();
      if (data != null)
      {
        for (Iterator i = data.iterator(); i.hasNext(); )
        {
          Object datum = i.next();
          if (datum instanceof ResourceSet)
          {
            deleteMarkers(datum, includeSubtypes, depth); 
            return;
          }
        }
      }
    }

    super.deleteMarkers(object, includeSubtypes, depth);
  }  
}
