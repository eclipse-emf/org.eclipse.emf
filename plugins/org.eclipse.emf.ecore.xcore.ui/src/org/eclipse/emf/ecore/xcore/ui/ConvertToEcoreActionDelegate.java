/**
 * Copyright (c) 2004-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xcore.ui;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.emf.ecore.xcore.ui.internal.XcoreActivator;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;
import org.eclipse.xtext.resource.XtextResourceSet;


/**
 *
 */
public class ConvertToEcoreActionDelegate extends ActionDelegate
{
  protected XPackage getInputXPackage(IStructuredSelection structuredSelection)
  {
    Object element = structuredSelection.getFirstElement();
    if (element instanceof IFile)
    {
      IFile file = (IFile)element;
      if ("xcore".equals(file.getFullPath().getFileExtension()))
      {
        XtextResourceSet resourceSet = new XtextResourceSet();
        IProject project = file.getProject();
        resourceSet.setClasspathURIContext(JavaCore.create(project));
        return
          (XPackage)EcoreUtil.getObjectByType
            (resourceSet.getResource(URI.createPlatformResourceURI(file.getFullPath().toString(), true), true).getContents(),
             XcorePackage.Literals.XPACKAGE);
      }
    }

    return null;
  }

  protected IFile getFile(Resource resource)
  {
      URI uri = resource.getURI();
      uri = resource.getResourceSet().getURIConverter().normalize(uri);
      String platformResourceString = uri.toPlatformString(true);
      return platformResourceString != null ?
        ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformResourceString)) :
        null;
  }

  /*
   * (non-Javadoc)
   *
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  @Override
  public void run(IAction action)
  {
    final IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

    final XPackage inputXPackage = getInputXPackage((IStructuredSelection)workbenchWindow.getSelectionService().getSelection());
    final Resource inputResource = inputXPackage.eResource();

    URI ecoreOutputResourceURI = inputResource.getURI().trimFileExtension().appendFileExtension("ecore");
    final ResourceSet resourceSet = inputResource.getResourceSet();
    final Resource ecoreOutputResource = resourceSet.createResource(ecoreOutputResourceURI);
    URI genModelOutputResourceURI = inputResource.getURI().trimFileExtension().appendFileExtension("genmodel");
    final Resource genModelOutputResource = resourceSet.createResource(genModelOutputResourceURI);

    ProgressMonitorDialog dialog = new ProgressMonitorDialog(workbenchWindow.getShell());
    try
    {
      dialog.run(false, true,
        new WorkspaceModifyOperation()
        {
          @Override
          protected void execute(IProgressMonitor progressMonitor)
          {
            try
            {
              progressMonitor.beginTask("", 1);

              // outputResource.getContents().add(new XcoreEcoreBuilder().getEPackage(inputXPackage));
              Resource ecoreXcore = resourceSet.getResource(URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/Ecore.xcore"), false);
              if (ecoreXcore != null)
              {
                Resource ecore = resourceSet.createResource(URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/Ecore.ecore"));
                ecore.getContents().add(ecoreXcore.getContents().get(2));
              }

              ecoreOutputResource.getContents().add((EPackage)EcoreUtil.getObjectByType(inputResource.getContents(), EcorePackage.Literals.EPACKAGE));
              genModelOutputResource.getContents().add((GenModel)EcoreUtil.getObjectByType(inputResource.getContents(), GenModelPackage.Literals.GEN_MODEL));

              try
              {
                ecoreOutputResource.save(null);
                genModelOutputResource.save(null);

                IFile ecoreFile = getFile(ecoreOutputResource);
                IFile genModelFile = getFile(genModelOutputResource);
                IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();

                final IWorkbenchPart activePart = workbenchPage.getActivePart();
                if (activePart instanceof ISetSelectionTarget)
                {
                  final ISelection targetSelection = new StructuredSelection(ecoreFile);
                  workbenchWindow.getShell().getDisplay().asyncExec(new Runnable()
                    {
                      public void run()
                      {
                        ((ISetSelectionTarget)activePart).selectReveal(targetSelection);
                      }
                    });
                }

                try
                {
                  workbenchPage.openEditor
                    (new FileEditorInput(ecoreFile), 
                     workbenchWindow.getWorkbench().getEditorRegistry().getDefaultEditor
                       (ecoreFile.getFullPath().toString(), Platform.getContentTypeManager().getContentType(EcorePackage.eCONTENT_TYPE)).getId());
                  workbenchPage.openEditor
                    (new FileEditorInput(genModelFile), 
                     workbenchWindow.getWorkbench().getEditorRegistry().getDefaultEditor
                       (genModelFile.getFullPath().toString(), Platform.getContentTypeManager().getContentType(GenModelPackage.eCONTENT_TYPE)).getId());
                }
                catch (PartInitException pie)
                {
                  XcoreActivator.getInstance().getLog().log(new Status(IStatus.WARNING, "org.eclipse.emf.ecore.xcore.ui", 0, pie.getLocalizedMessage(), pie));
                }
              }
              catch (IOException ioe)
              {
                XcoreActivator.getInstance().getLog().log(new Status(IStatus.WARNING, "org.eclipse.emf.ecore.xcore.ui", 0, ioe.getLocalizedMessage(), ioe));
              }
            }
            finally
            {
              progressMonitor.done();
            }
          }
        });
    }
    catch (InterruptedException ie)
    {
      // ignore
    }
    catch (InvocationTargetException ite)
    {
      XcoreActivator.getInstance().getLog().log(new Status(IStatus.WARNING, "org.eclipse.emf.ecore.xcore.ui", 0, ite.getLocalizedMessage(), ite));
    }
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection)
  {
    action.setEnabled(IStructuredSelection.class.isInstance(selection) && getInputXPackage((IStructuredSelection)selection) != null);
  }
}