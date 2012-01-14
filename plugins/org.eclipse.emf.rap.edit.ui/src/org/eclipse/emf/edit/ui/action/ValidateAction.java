/**
 * Copyright (c) 2004-2008 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.ui.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.Action;
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
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ISetSelectionTarget;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;


public class ValidateAction extends Action implements ISelectionChangedListener 
{  
  protected ISelectionProvider selectionProvider;
  protected List<EObject> selectedObjects;
  protected EditingDomain domain;

  public ValidateAction()
  {
    super(EMFEditUIPlugin.INSTANCE.getString("_UI_Validate_menu_item"));
    setDescription(EMFEditUIPlugin.INSTANCE.getString("_UI_Validate_simple_description"));
  }

  @Override
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
  protected Diagnostic validate(IProgressMonitor progressMonitor)
  {
    int selectionSize = selectedObjects.size();
    int count = selectionSize;
    for (EObject eObject : selectedObjects)
    {
      for (Iterator<?> i = eObject.eAllContents(); i.hasNext(); i.next())
      {
        ++count;
      }
    }

    progressMonitor.beginTask("", count);

    AdapterFactory adapterFactory = 
      domain instanceof AdapterFactoryEditingDomain ? ((AdapterFactoryEditingDomain)domain).getAdapterFactory() : null;
    Diagnostician diagnostician = createDiagnostician(adapterFactory, progressMonitor);
    
    BasicDiagnostic diagnostic;
    if (selectionSize == 1)
    {
      diagnostic = diagnostician.createDefaultDiagnostic(selectedObjects.get(0));
    }
    else
    {
      diagnostic =
        new BasicDiagnostic
          (EObjectValidator.DIAGNOSTIC_SOURCE,
           0,
           EMFEditUIPlugin.INSTANCE.getString("_UI_DiagnosisOfNObjects_message", new String[] { Integer.toString(selectionSize) }),
           selectedObjects.toArray() ); 
    }
    Map<Object, Object> context = diagnostician.createDefaultContext();
    for (EObject eObject : selectedObjects)
    {
      progressMonitor.setTaskName(EMFEditUIPlugin.INSTANCE.getString("_UI_Validating_message", new Object [] {diagnostician.getObjectLabel(eObject)}));
      diagnostician.validate(eObject, diagnostic, context);
    }
    return diagnostic;
  }

  protected Diagnostician createDiagnostician(final AdapterFactory adapterFactory, final IProgressMonitor progressMonitor)
  {
    return 
      new Diagnostician()
      {
        @Override
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
  
        @Override
        public boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map<Object, Object> context)
        {
          progressMonitor.worked(1);
          return super.validate(eClass, eObject, diagnostics, context);
        }
      };
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

    int result = 0;
    if (diagnostic.getSeverity() == Diagnostic.OK)
    {
      MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), title, message);
      result = Window.CANCEL;
    }
    else
    {
      result = DiagnosticDialog.open
        (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), title, message, diagnostic);
    }

    if (result == Window.OK)
    {
      if (!diagnostic.getChildren().isEmpty())
      {
        List<?> data = (diagnostic.getChildren().get(0)).getData();
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
    }
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
    selectedObjects = new ArrayList<EObject>();
    for (Iterator<?> objects = selection.iterator(); objects.hasNext(); )
    {
      Object object = AdapterFactoryEditingDomain.unwrap(objects.next());
      if (object instanceof EObject)
      {
        selectedObjects.add((EObject)object);
      }
      else if (object instanceof Resource)
      {
        selectedObjects.addAll(((Resource)object).getContents());
      }      
      else
      {
        return false;
      }
    }
    selectedObjects = EcoreUtil.filterDescendants(selectedObjects);
    return !selectedObjects.isEmpty();
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
