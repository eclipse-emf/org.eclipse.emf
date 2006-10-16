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
 * $Id: MapToEcoreActionDelegate.java,v 1.5 2006/10/16 03:33:51 davidms Exp $
 */
package org.eclipse.emf.mapping.ecore2ecore.action;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.mapping.domain.PluginAdapterFactoryMappingDomain;
import org.eclipse.emf.mapping.ecore2ecore.Ecore2EcoreFactory;
import org.eclipse.emf.mapping.ecore2ecore.Ecore2EcoreMappingRoot;
import org.eclipse.emf.mapping.ecore2ecore.presentation.Ecore2EcoreEditorPlugin;
import org.eclipse.emf.mapping.provider.MappingItemProviderAdapterFactory;
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
import org.eclipse.ui.dialogs.ResourceSelectionDialog;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;


/**
 *  
 */
public class MapToEcoreActionDelegate extends ActionDelegate
{
  private static final String ECORE_FILE_EXTENSION = "ecore";

  private static final String ECORE2ECORE_FILE_EXTENSION = "ecore2ecore";

  protected EPackage getInputEPackage(IStructuredSelection structuredSelection)
  {
    if (IFile.class.isInstance(structuredSelection.getFirstElement()))
    {
      IFile file = (IFile)structuredSelection.getFirstElement();

      if (ECORE_FILE_EXTENSION.equals(file.getFullPath().getFileExtension()))
      {
        return (EPackage)EcoreUtil.getObjectByType(new ResourceSetImpl().getResource(
          URI.createPlatformResourceURI(file.getFullPath().toString(), true),
          true).getContents(), EcorePackage.eINSTANCE.getEPackage());
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
  public void run(IAction action)
  {
    final IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

    ResourceSelectionDialog resourceSelectionDialog = new ResourceSelectionDialog(
      workbenchWindow.getShell(),
      ResourcesPlugin.getWorkspace().getRoot(),
      Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_SelectOutputEcoreModels_label"));
    resourceSelectionDialog.open();

    final Object[] result = resourceSelectionDialog.getResult();

    if (result != null)
    {
      final EPackage inputEPackage = getInputEPackage((IStructuredSelection)workbenchWindow.getSelectionService().getSelection());
      final Resource inputResource = inputEPackage.eResource();

      ProgressMonitorDialog dialog = new ProgressMonitorDialog(workbenchWindow.getShell());

      try
      {
        dialog.run(false, true,
          new WorkspaceModifyOperation()
          {
            protected void execute(IProgressMonitor progressMonitor)
            {
              try
              {
                progressMonitor.beginTask("", result.length);

                for (int i = 0; i < result.length; i++)
                {
                  IResource resource = (IResource)result[i];

                  if (resource.getType() == IResource.FILE && ECORE_FILE_EXTENSION.equals(resource.getFullPath().getFileExtension()))
                  {
                    Resource outputResource = inputResource.getResourceSet().getResource(
                      URI.createPlatformResourceURI(resource.getFullPath().toString(), true),
                      true);
                    EPackage outputEPackage = (EPackage)EcoreUtil.getObjectByType(
                      outputResource.getContents(),
                      EcorePackage.eINSTANCE.getEPackage());

                    String base = inputResource.getURI().trimFileExtension().lastSegment() + "_2_" +
                      outputResource.getURI().trimFileExtension().lastSegment();
                    URI mappingURI = outputResource.getURI().trimSegments(1).appendSegment(base).appendFileExtension(ECORE2ECORE_FILE_EXTENSION);

                    Resource mappingResource = inputResource.getResourceSet().createResource(mappingURI);

                    progressMonitor.subTask(MessageFormat.format(
                      Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_BuildingMappingFromTo_message"),
                      new Object[] { inputResource.getURI().lastSegment(), outputResource.getURI().lastSegment() }));

                    mappingResource.getContents().add(createMappingRoot(inputEPackage, outputEPackage));
                    
                    try
                    {
                      mappingResource.save(null);
                      
                      IFile file = getFile(mappingResource);
                      IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();
                      
                      final IWorkbenchPart activePart = workbenchPage.getActivePart();
                      if (activePart instanceof ISetSelectionTarget)
                      {
                        final ISelection targetSelection = new StructuredSelection(file);
                        workbenchWindow.getShell().getDisplay().asyncExec(new Runnable()
                          {
                            public void run()
                            {
                              ((ISetSelectionTarget)activePart).selectReveal(targetSelection);
                            }
                          });
                      }

                      try {
                        workbenchPage.openEditor(new FileEditorInput(file), workbenchWindow.getWorkbench().getEditorRegistry().getDefaultEditor(file.getFullPath().toString()).getId());                      
                      } catch (PartInitException pie) {
                        Ecore2EcoreEditorPlugin.INSTANCE.log(pie);                      
                      }
                    }
                    catch (IOException ioe)
                    {
                      Ecore2EcoreEditorPlugin.INSTANCE.log(ioe);
                    }
                  }
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
        Ecore2EcoreEditorPlugin.INSTANCE.log(ite);
      }
    }
  }

  protected static Ecore2EcoreMappingRoot createMappingRoot(EPackage inputEPackage, EPackage outputEPackage)
  {
    Ecore2EcoreMappingRoot mappingRoot = Ecore2EcoreFactory.eINSTANCE.createEcore2EcoreMappingRoot();
    mappingRoot.setTopToBottom(true);

    AdapterFactory ecoreAdapterFactory = new EcoreItemProviderAdapterFactory();
    AdapterFactory composedAdapterFactory = new ComposedAdapterFactory(
      new AdapterFactory []
      {
        new ResourceItemProviderAdapterFactory(),
        new MappingItemProviderAdapterFactory(),
        ecoreAdapterFactory
      });
    mappingRoot.setDomain(new PluginAdapterFactoryMappingDomain(composedAdapterFactory, ecoreAdapterFactory, null, null));
    
    mappingRoot.getInputs().add(inputEPackage);
    mappingRoot.getOutputs().add(outputEPackage);
    
    return mappingRoot;
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
   *      org.eclipse.jface.viewers.ISelection)
   */
  public void selectionChanged(IAction action, ISelection selection)
  {
    action.setEnabled(IStructuredSelection.class.isInstance(selection) && getInputEPackage((IStructuredSelection)selection) != null);
  }
}