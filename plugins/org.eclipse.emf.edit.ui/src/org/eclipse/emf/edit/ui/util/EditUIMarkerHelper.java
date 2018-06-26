/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.ui.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import org.eclipse.emf.common.ui.CommonUIPlugin;
import org.eclipse.emf.common.ui.MarkerHelper;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
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
      if (uri == null)
      {
        return null;
      }
      else
      {
        ResourceSet resourceSet = resource.getResourceSet();
        if (resourceSet != null)
        {
          uri = resourceSet.getURIConverter().normalize(uri);
        }
        return getFile(uri);
      }
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

            try
            {
              Method getObjectMethod = resourceDiagnostic.getClass().getMethod("getObject");
              Object object = getObjectMethod.invoke(resourceDiagnostic);
              if (object instanceof EObject)
              {
                marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI((EObject)object).toString());
                Method getFeatureMethod = resourceDiagnostic.getClass().getMethod("getFeature");
                Object feature = getFeatureMethod.invoke(resourceDiagnostic);
                if (feature instanceof EObject)
                {
                  marker.setAttribute(EValidator.RELATED_URIS_ATTRIBUTE, EcoreUtil.getURI((EObject)feature).toString());
                }
              }
            }
            catch (Throwable throwable)
            {
              // Ignore.
            }

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
   * @since 2.11
   */
  @Override
  public List<?> getTargetObjects(Object object, IMarker marker, boolean wrap)
  {
    if (object instanceof AdapterFactoryEditingDomain)
    {
      ArrayList<Object> result = new ArrayList<Object>();
      AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain)object;
      String uriAttribute = marker.getAttribute(EValidator.URI_ATTRIBUTE, null);
      if (uriAttribute == null)
      {
        uriAttribute = marker.getAttribute("URI_KEY", null);
      }
      if (uriAttribute != null)
      {
        URI uri = URI.createURI(uriAttribute);
        try
        {
          EObject eObject = editingDomain.getResourceSet().getEObject(uri, true);
          if (eObject != null)
          {
            result.add(wrap ? editingDomain.getWrapper(eObject) : eObject);
          }
        }
        catch (Throwable throwable)
        {
          // Ignore if we can resolve the object.
        }
      }
      String relatedURIsAttribute = marker.getAttribute(EValidator.RELATED_URIS_ATTRIBUTE, null);
      if (relatedURIsAttribute != null)
      {
        for (String relatedURI : relatedURIsAttribute.split(" "))
        {
          URI uri = URI.createURI(URI.decode(relatedURI));
          try
          {
            EObject eObject = editingDomain.getResourceSet().getEObject(uri, true);
            if (eObject != null)
            {
              result.add(wrap ? editingDomain.getWrapper(eObject) : eObject);
            }
          }
          catch (Throwable throwable)
          {
            // Ignore if we can't resolve the object.
          }
        }
      }
      else
      {
        relatedURIsAttribute =  marker.getAttribute("DATA_KEY", null);
        if (relatedURIsAttribute != null)
        {
          int start = 0;
          int length = relatedURIsAttribute.length();
          for (int index = relatedURIsAttribute.indexOf(':'); start < length; index = relatedURIsAttribute.indexOf(':', start))
          {
            int itemLength = Integer.parseInt(relatedURIsAttribute.substring(start, index == -1 ? length : index));
            start = index + itemLength + 1;
            String uriLiteral = relatedURIsAttribute.substring(index + 1, start);
            try
            {
              URI uri = URI.createURI(uriLiteral);
              if (uri.hasFragment())
              {
                EObject eObject = editingDomain.getResourceSet().getEObject(uri, true);
                if (eObject != null)
                {
                  result.add(wrap ? editingDomain.getWrapper(eObject) : eObject);
                }
              }
            }
            catch (Throwable throwable)
            {
              // Ignore if it's not a URI or we can't load the object.
              
            }
          }
        }
      }
      return result;
    }
    else
    {
      return super.getTargetObjects(object, marker, wrap);
    }
  }

  /**
   * @since 2.11
   */
  @Override
  public Diagnostic getMarkerDiagnostics(Object object, IFile file, boolean wrap)
  {
    if (file == null)
    {
      file = getFile(object);
    }
    if (object instanceof Resource)
    {
      Resource resource = (Resource)object;
      EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(resource.getResourceSet());
      BasicDiagnostic diagnostic = new BasicDiagnostic(getDiagnosticSource(), 0, null, new Object[] { resource });
      if (file != null && file.isAccessible())
      {
        try
        {
          for (IMarker marker : file.findMarkers(null, true, IResource.DEPTH_ZERO))
          {
            String message = marker.getAttribute(IMarker.MESSAGE, "");
            int severity = marker.getAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO);
            String sourceID = marker.getAttribute(IMarker.SOURCE_ID, "");
            List<?> data = wrap ? getTargetObjects(editingDomain, marker) : getTargetObjects(editingDomain, marker, false);
            diagnostic.add
              (new BasicDiagnostic
                 (severity == IMarker.SEVERITY_ERROR ?
                    Diagnostic.ERROR : 
                    severity == IMarker.SEVERITY_WARNING ? 
                    Diagnostic.WARNING : 
                    Diagnostic.INFO, 
                  sourceID,
                  0, 
                  message, 
                  data.toArray(new Object[data.size()])));
          }
        }
        catch (CoreException exception)
        {
          CommonUIPlugin.INSTANCE.log(exception);
        }
      }
      for (Diagnostic intrinsicDiagnostic : getIntrinsicDiagnostics(resource, wrap))
      {
        diagnostic.add(intrinsicDiagnostic);
      }
      return diagnostic;
    }
    else
    {
      return super.getMarkerDiagnostics(object, file, wrap);
    }
  }

  @Override
  public List<? extends Diagnostic> getInstrinciDiagnostics(Object object, boolean wrap)
  {
    if (object instanceof Resource)
    {
      Resource resource = (Resource)object;
      List<Diagnostic> diagnostics = new ArrayList<Diagnostic>();
      for (Resource.Diagnostic resourceDiagnostic : resource.getWarnings())
      {
        diagnostics.add(new BasicDiagnostic(Diagnostic.WARNING, null, 0, resourceDiagnostic.getMessage(),  getDiagnosticData(resource, resourceDiagnostic, wrap)));
      }
      for (Resource.Diagnostic resourceDiagnostic : resource.getErrors())
      {
        diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, null, 0, resourceDiagnostic.getMessage(), getDiagnosticData(resource, resourceDiagnostic, wrap)));
      }
      return diagnostics;
    }
    else
    {
      return super.getInstrinciDiagnostics(object, wrap);
    }
  }

  /**
   * @since 2.13
   */
  protected Object[] getDiagnosticData(Resource resource, Resource.Diagnostic resourceDiagnostic, boolean wrap)
  {
    List<Object> result = new ArrayList<Object>();
    EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(resource.getResourceSet());
    try
    {
      Method getObjectMethod = resourceDiagnostic.getClass().getMethod("getObject");
      Object object = getObjectMethod.invoke(resourceDiagnostic);
      if (object instanceof EObject)
      {
        result.add(wrap && editingDomain instanceof AdapterFactoryEditingDomain ? ((AdapterFactoryEditingDomain)editingDomain).getWrapper(object) : object);

        Method getFeatureMethod = resourceDiagnostic.getClass().getMethod("getFeature");
        Object feature = getFeatureMethod.invoke(resourceDiagnostic);
        if (feature instanceof EObject)
        {
          result.add(wrap && editingDomain instanceof AdapterFactoryEditingDomain ? ((AdapterFactoryEditingDomain)editingDomain).getWrapper(feature) : feature);
          result.add(feature);
        }
      }
    }
    catch (Throwable throwable)
    {
      // Ignore.
    }

    return result.isEmpty() ? new Object[] { resource } : result.toArray();
  }
}
