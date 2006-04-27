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
 * $Id: EditUIMarkerHelper.java,v 1.2 2006/04/27 18:33:30 marcelop Exp $
 */
package org.eclipse.emf.edit.ui.util;

import java.util.Iterator;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;

import org.eclipse.emf.common.ui.MarkerHelper;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;

/**
 * Extension of {@link MarkerHelper} that provides extra functionalities usefull when
 * using EMF classes such as {@link Resource}.
 *   
 * @since 2.2.0
 */
public class EditUIMarkerHelper extends MarkerHelper
{
  protected void adjustMarker(IMarker marker, Diagnostic diagnostic) throws CoreException
  {
    for (Iterator i = diagnostic.getData().iterator(); i.hasNext();)
    {
      Object element = (Object)i.next();
      if (element instanceof Resource.Diagnostic)
      {
        Resource.Diagnostic resourceDiagnostic = (Resource.Diagnostic)element;
        String location = resourceDiagnostic.getLocation();
        if (location != null)
        {
          if (location.startsWith("platform:/resource/"))
          {
            location = location.substring("platform:/resource/".length());
          }
          location = EMFEditUIPlugin.getPlugin().getString
            ("_UI_MarkerLocation", 
             new String[] { location, Integer.toString(resourceDiagnostic.getLine()), Integer.toString(resourceDiagnostic.getColumn())} );
          marker.setAttribute(IMarker.LOCATION, location);
          marker.setAttribute(IMarker.LINE_NUMBER, resourceDiagnostic.getLine());
          break;
        }
      }
    }
  }
  
}
