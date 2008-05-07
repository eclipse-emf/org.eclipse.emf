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
 * $Id: EditUIMarkerHelper.java,v 1.12 2008/05/07 19:08:40 emerks Exp $
 */
package org.eclipse.emf.edit.ui.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

import org.eclipse.emf.common.ui.MarkerHelper;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;

/**
 * Extension of {@link MarkerHelper} that provides extra functionalities useful when
 * using EMF classes such as {@link Resource}.
 *   
 * @since 2.2.0
 */
public class EditUIMarkerHelper extends MarkerHelper
{
  @Override
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
  
  @Override
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
      for (Object element : diagnostic.getData())
      {
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
  
  @Override
  public boolean hasMarkers(Object object, boolean includeSubtypes, int depth)
  {
    if (object instanceof ResourceSet)
    {
      ResourceSet resourceSet = (ResourceSet)object;
      List<Resource> resources = resourceSet.getResources();
      for (int i=0, size=resources.size(); i<size; i++)
      {
        if (hasMarkers(resources.get(i), includeSubtypes, depth))
        {
          return true;
        }
      }
      return false;
    }
    else if (object instanceof Diagnostic)
    {
      List<?> data = ((Diagnostic)object).getData();
      if (data != null)
      {
        for (Object datum : data)
        {
          if (datum instanceof ResourceSet)
          {
            return hasMarkers(datum, includeSubtypes, depth); 
          }
        }
      }
    }
    
    return super.hasMarkers(object, includeSubtypes, depth);
  }

  @Override
  public void deleteMarkers(Object object, boolean includeSubtypes, int depth)
  {
    if (object instanceof ResourceSet)
    {
      ResourceSet resourceSet = (ResourceSet)object;
      List<Resource> resources = resourceSet.getResources();
      for (int i=0, size=resources.size(); i<size; i++)
      {
        deleteMarkers(resources.get(i), includeSubtypes, depth);
      }
    }
    else if (object instanceof Diagnostic)
    {
      List<?> data = ((Diagnostic)object).getData();
      if (data != null)
      {
        for (Object datum : data)
        {
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

  /**
   * @since 2.3
   */
  @Override
  public List<?> getTargetObjects(Object object, IMarker marker)
  {
    if (object instanceof AdapterFactoryEditingDomain)
    {
      ArrayList<Object> result = new ArrayList<Object>();
      AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain)object;
      String uriAttribute = marker.getAttribute(EValidator.URI_ATTRIBUTE, null);
      if (uriAttribute != null)
      {
        URI uri = URI.createURI(uriAttribute);
        EObject eObject = editingDomain.getResourceSet().getEObject(uri, true);
        if (eObject != null)
        {
          result.add(editingDomain.getWrapper(eObject));
        }
      }
      String relatedURIsAttribute = marker.getAttribute(EValidator.RELATED_URIS_ATTRIBUTE, null);
      if (relatedURIsAttribute != null)
      {
        for (String relatedURI : relatedURIsAttribute.split(" "))
        {
          URI uri = URI.createURI(URI.decode(relatedURI));
          EObject eObject = editingDomain.getResourceSet().getEObject(uri, true);
          if (eObject != null)
          {
            result.add(editingDomain.getWrapper(eObject));
          }
        }
      }
      return result;
    }
    else
    {
      return super.getTargetObjects(object, marker);
    }
  }
}
