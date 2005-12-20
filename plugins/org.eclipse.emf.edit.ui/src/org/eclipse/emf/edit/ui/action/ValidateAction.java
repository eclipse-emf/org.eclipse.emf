/**
 * <copyright>
 *
 * Copyright (c) 2004-2005 IBM Corporation and others.
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
 * $Id: ValidateAction.java,v 1.16 2005/12/20 15:59:43 marcelop Exp $
 */
package org.eclipse.emf.edit.ui.action;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyDelegatingOperation;
import org.eclipse.ui.part.ISetSelectionTarget;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
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


/**
 */
public class ValidateAction extends Action implements ISelectionChangedListener 
{  
  public static class EclipseResourcesUtil
  {
    public IRunnableWithProgress getWorkspaceModifyOperation(IRunnableWithProgress runnableWithProgress)
    {
      return new WorkspaceModifyDelegatingOperation(runnableWithProgress);
    }
    
    public void createMarkers(IFile file, Diagnostic diagnostic)
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
  }
  
  protected ISelectionProvider selectionProvider;
  protected List selectedObjects;
  protected EditingDomain domain;
  protected EclipseResourcesUtil eclipseResourcesUtil = 
    Platform.getBundle("org.eclipse.core.resources") != null ? 
      new EclipseResourcesUtil() : null;

  /**
   * This contructs an instance.
   */
  public ValidateAction()
  {
    super(EMFEditUIPlugin.INSTANCE.getString("_UI_Validate_menu_item"));
    setDescription(EMFEditUIPlugin.INSTANCE.getString("_UI_Validate_simple_description"));
  }

  public void run()
  {
    final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
    IRunnableWithProgress runnableWithProgress = new IRunnableWithProgress()
    {
      public void run(final IProgressMonitor progressMonitor) throws InvocationTargetException, InterruptedException
      {
        try
        {
          final Diagnostic diagnostic = validate(progressMonitor);
          shell.getDisplay().asyncExec 
            (new Runnable()
             {
               public void run()
               {
                 if (progressMonitor.isCanceled())
                 {
                   handleDiagnostic(Diagnostic.CANCEL_INSTANCE);
                 }
                 else
                 {
                   handleDiagnostic(diagnostic);
                 }
               }
             });
        }
        finally
        {
          progressMonitor.done();
        }    
      }
    };
    
    if(eclipseResourcesUtil != null)
    {
      runnableWithProgress = eclipseResourcesUtil.getWorkspaceModifyOperation(runnableWithProgress);
    }

    try
    {
      // This runs the operation, and shows progress.
      // (It appears to be a bad thing to fork this onto another thread.)
      //
      new ProgressMonitorDialog(shell).run(true, true, runnableWithProgress);
    }
    catch (Exception exception)
    {
      EMFEditUIPlugin.INSTANCE.log(exception);
    }
  }
  
  /**
   * This simply execute the command.
   */
  protected Diagnostic validate(final IProgressMonitor progressMonitor)
  {
    EObject eObject = (EObject)selectedObjects.iterator().next();
    int count = 0;
    for (Iterator i = eObject.eAllContents(); i.hasNext(); i.next())
    {
      ++count;
    }

    progressMonitor.beginTask("", count);

    final AdapterFactory adapterFactory = 
      domain instanceof AdapterFactoryEditingDomain ? ((AdapterFactoryEditingDomain)domain).getAdapterFactory() : null;

    Diagnostician diagnostician = 
      new Diagnostician()
      {
        public String getObjectLabel(EObject eObject)
        {
          if (adapterFactory != null && !eObject.eIsProxy())
          {
            IItemLabelProvider itemLabelProvider = (IItemLabelProvider)adapterFactory.adapt(eObject, IItemLabelProvider.class);
            if (itemLabelProvider != null)
            {
              return itemLabelProvider.getText(eObject);
            }
          }
  
          return super.getObjectLabel(eObject);
        }

        public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map context)
        {
          progressMonitor.worked(1);
          return super.validate(eClass, eObject, diagnostics, context);
        }
      };

    progressMonitor.setTaskName
      (EMFEditUIPlugin.INSTANCE.getString("_UI_Validating_message", new Object [] {diagnostician.getObjectLabel(eObject)}));

    return diagnostician.validate(eObject);
  }

  protected void handleDiagnostic(Diagnostic diagnostic)
  {
    int severity = diagnostic.getSeverity();
    String title = null;
    String message = null;

    if (severity == Diagnostic.ERROR || severity == Diagnostic.WARNING)
    {
      title = EMFEditUIPlugin.INSTANCE.getString("_UI_ValidationProblems_title");
      message = EMFEditUIPlugin.INSTANCE.getString("_UI_ValidationProblems_message");
    }
    else
    {
      title = EMFEditUIPlugin.INSTANCE.getString("_UI_ValidationResults_title");
      message = EMFEditUIPlugin.INSTANCE.getString(severity == Diagnostic.OK ? "_UI_ValidationOK_message" : "_UI_ValidationResults_message");
    }
    int result = ErrorDialog.openError
        (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), title, message, BasicDiagnostic.toIStatus(diagnostic));

    // No error dialog is displayed if the status severity is OK; pop up a dialog so the user knows something happened.
    //
    if (diagnostic.getSeverity() == Diagnostic.OK)
    {
      MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), title, message);
      result = Window.CANCEL;
    }

    if (Platform.getBundle("org.eclipse.core.resources") != null)
    {
      IFile file = getFile();
      if (file != null)
      {
        try
        {
          file.deleteMarkers(EValidator.MARKER, true, IResource.DEPTH_ZERO);
        }
        catch (CoreException exception)
        {
          EMFEditUIPlugin.INSTANCE.log(exception);
        }
      }
    
      if (result == Window.OK)
      {
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
            else if (part instanceof IViewerProvider)
            {
              Viewer viewer = ((IViewerProvider)part).getViewer();
              if (viewer != null)
              {
                viewer.setSelection(new StructuredSelection(data.get(0)), true);
              }
            }
          }
        }
    
        if (file != null)
        {
          for (Iterator i = diagnostic.getChildren().iterator(); i.hasNext(); )
          {
            Diagnostic childDiagnostic = (Diagnostic)i.next();
            createMarkers(file, childDiagnostic);
          }
        }
      }
    }
  }
  
  protected void createMarkers(IFile file, Diagnostic diagnostic)
  {
    eclipseResourcesUtil.createMarkers(file, diagnostic);
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
        return ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformResourcePath.toString()));
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
      selectedObjects.add(AdapterFactoryEditingDomain.unwrap(objects.next()));
    }

    return 
      selectedObjects.size() == 1 && 
        selectedObjects.get(0) instanceof EObject;
  }

  /**
   * @deprecated As of EMF 2.1.0, replaced by {@link #setActiveWorkbenchPart}.
   */
  public void setActiveEditor(IEditorPart editorPart)
  {
    setActiveWorkbenchPart(editorPart);
  }

  /**
   * @since 2.1.0
   */
  public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart)
  {
    if (workbenchPart instanceof IEditingDomainProvider)
    {
      domain = ((IEditingDomainProvider)workbenchPart).getEditingDomain();
    }
  }
}
