/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: ValidateAction.java,v 1.2 2004/05/08 13:37:02 emerks Exp $
 */
package org.eclipse.emf.edit.ui.action;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

import org.eclipse.emf.common.notify.AdapterFactory;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EValidator;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;

import org.eclipse.emf.edit.provider.IItemLabelProvider;

import org.eclipse.emf.edit.ui.EMFEditUIPlugin;

import org.eclipse.jface.action.Action;

import org.eclipse.jface.dialogs.ErrorDialog;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;

import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.IEditorPart;

import org.eclipse.ui.part.ISetSelectionTarget;


/**
 */
public class ValidateAction extends Action implements ISelectionChangedListener 
{
  protected ISelectionProvider selectionProvider;
  protected List selectedObjects;
  protected EditingDomain domain;

  /**
   * This contructs an instance.
   */
  public ValidateAction()
  {
    super(EMFEditUIPlugin.INSTANCE.getString("_UI_Validate_menu_item"));
    setDescription(EMFEditUIPlugin.INSTANCE.getString("_UI_Validate_simple_description"));
  }

  /**
   * This simply execute the command.
   */
  public void run()
  {
    final AdapterFactory adapterFactory = 
      domain instanceof AdapterFactoryEditingDomain ? ((AdapterFactoryEditingDomain)domain).getAdapterFactory() : null;

    Diagnostician diagnostician = 
      new Diagnostician()
      {
        public String getObjectLabel(EObject eObject)
        {
          if (adapterFactory != null)
          {
            IItemLabelProvider itemLabelProvider = (IItemLabelProvider)adapterFactory.adapt(eObject, IItemLabelProvider.class);
            if (itemLabelProvider != null)
            {
              return itemLabelProvider.getText(eObject);
            }
          }
  
          return super.getObjectLabel(eObject);
        }

/*
        public String getFeatureLabel(EStructuralFeature eStructuralFeature)
        {
          return eStructuralFeature.getName();
        }
*/
      };

    Diagnostic diagnostic = diagnostician.validate((EObject)selectedObjects.iterator().next());
    ErrorDialog.openError
      (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
       EMFEditUIPlugin.INSTANCE.getString("_UI_ValidationProblems_title"),
       EMFEditUIPlugin.INSTANCE.getString("_UI_ValidationProblems_message"),
       BasicDiagnostic.toIStatus(diagnostic));

    if (!diagnostic.getChildren().isEmpty())
    {
      List data = ((Diagnostic)diagnostic.getChildren().get(0)).getData();
      if (!data.isEmpty() && data.get(0) instanceof EObject)
      {
        Object part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
        if (part instanceof ISetSelectionTarget)
        {
          ((ISetSelectionTarget)part).selectReveal(new StructuredSelection(data.get(0)));
        }
      }
    }

    IFile file = getFile();
    try
    {
      file.deleteMarkers(EValidator.MARKER, true, IResource.DEPTH_ZERO);
    }
    catch (CoreException exception)
    {
      EMFEditUIPlugin.INSTANCE.log(exception);
    }

    for (Iterator i = diagnostic.getChildren().iterator(); i.hasNext(); )
    {
      Diagnostic childDiagnostic = (Diagnostic)i.next();
      createMarkers(file, childDiagnostic);
    }
  }

  protected static final IWorkspaceRoot WORKSPACE_ROOT = ResourcesPlugin.getWorkspace().getRoot();

  protected void createMarkers(IFile file, Diagnostic diagnostic)
  {
    EObject eObject = null;
    List data = diagnostic.getData();
    if (!data.isEmpty())
    {
      Object target = data.get(0);
      if (target instanceof EObject)
      {
        eObject = (EObject)target;
      }
    }

    if (diagnostic.getChildren().isEmpty())
    {
      try
      {
        IMarker marker = file.createMarker(EValidator.MARKER);
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
        if (eObject != null)
        {
          marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(eObject).toString());
        }
      }
      catch (CoreException exception)
      {
        EMFEditUIPlugin.INSTANCE.log(exception);
      }
    }
    else
    {
      String parentMessage = diagnostic.getMessage() + ". ";
      for (Iterator i = diagnostic.getChildren().iterator(); i.hasNext(); )
      {
        Diagnostic childDiagnostic = (Diagnostic)i.next();
        try
        {
          IMarker marker = file.createMarker(EValidator.MARKER);
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
          if (eObject != null)
          {
            marker.setAttribute(EValidator.URI_ATTRIBUTE, EcoreUtil.getURI(eObject).toString());
          }
        }
        catch (CoreException exception)
        {
          EMFEditUIPlugin.INSTANCE.log(exception);
        }
      }
    }
  }

  protected IFile getFile()
  {
    Resource resource = (Resource)domain.getResourceSet().getResources().get(0);
    if (resource != null)
    {
      URI uri = resource.getURI();
      uri = resource.getResourceSet().getURIConverter().normalize(uri);
      String scheme = uri.scheme();
      if ("platform".equals(scheme) && uri.segmentCount() > 1 && "resource".equals(uri.segment(0)))
      {
        StringBuffer platformResourcePath = new StringBuffer();
        for (int j = 1, size = uri.segmentCount(); j < size; ++j)
        {
          platformResourcePath.append('/');
          platformResourcePath.append(uri.segment(j));
        }
        return WORKSPACE_ROOT.getFile(new Path(platformResourcePath.toString()));
      }
    }
    return null;
  }

  public void selectionChanged(SelectionChangedEvent event) 
  {
    selectionProvider = event.getSelectionProvider();
    if (event.getSelection() instanceof IStructuredSelection)
    {
      setEnabled(updateSelection((IStructuredSelection)event.getSelection()));
    }
    else
    {
      setEnabled(false);
    }
  }

  public boolean updateSelection(IStructuredSelection selection)
  {
    selectedObjects = new ArrayList();
    for (Iterator objects = selection.iterator(); objects.hasNext(); )
    {
      selectedObjects.add(objects.next());
    }

    return 
      selectedObjects.size() == 1 && 
        selectedObjects.get(0) instanceof EObject;
  }

  public void setActiveEditor(IEditorPart editorPart)
  {
    if (editorPart instanceof IEditingDomainProvider)
    {
      domain = ((IEditingDomainProvider)editorPart).getEditingDomain();
    }
  }
}
