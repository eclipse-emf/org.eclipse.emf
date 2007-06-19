/**
 * <copyright>
 *
 * Copyright (c) 2004-2007 IBM Corporation and others.
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
 * $Id: MapToEcoreActionDelegate.java,v 1.7 2007/06/19 17:31:46 marcelop Exp $
 */
package org.eclipse.emf.mapping.ecore2ecore.action;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
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


/**
 *  
 */
public class MapToEcoreActionDelegate extends ActionDelegate
{
  private static final List<String> FILE_EXTENSIONS = Arrays.asList(new String [] { "ecore", "emof" });
  private static final String ECORE2ECORE_FILE_EXTENSION = "ecore2ecore";

  protected EPackage getInputEPackage(IStructuredSelection structuredSelection)
  {
    if (IFile.class.isInstance(structuredSelection.getFirstElement()))
    {
      IFile file = (IFile)structuredSelection.getFirstElement();

      if (FILE_EXTENSIONS.contains(file.getFullPath().getFileExtension()))
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
  @Override
  public void run(IAction action)
  {
    final IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    Object selection = ((IStructuredSelection)workbenchWindow.getSelectionService().getSelection()).getFirstElement();
    final IFile selectedEcoreFile = 
      selection instanceof IFile && FILE_EXTENSIONS.contains(((IFile)selection).getFileExtension()) ? 
        (IFile)selection :
        null;

    ViewerFilter viewerFilter = new ViewerFilter()
    {
      @Override
      public boolean select(Viewer viewer, Object parentElement, Object element)
      {
        if (element instanceof IFile)
        {
          IFile file = (IFile)element;
          return 
            FILE_EXTENSIONS.contains(file.getFileExtension()) &&
            (selectedEcoreFile == null || !selectedEcoreFile.getFullPath().equals(file.getFullPath()));
        }
        return true;
      }
    };
    
    final IFile[] files = WorkspaceResourceDialog.openFileSelection(
      workbenchWindow.getShell(), 
      null, 
      Ecore2EcoreEditorPlugin.INSTANCE.getString("_UI_SelectOutputEcoreModels_label"), 
      true, 
      null, 
      Collections.singletonList(viewerFilter));
    
    if (files.length > 0)
    {
      final EPackage inputEPackage = getInputEPackage((IStructuredSelection)workbenchWindow.getSelectionService().getSelection());
      final Resource inputResource = inputEPackage.eResource();

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
                progressMonitor.beginTask("", files.length);

                for (int i = 0; i < files.length; i++)
                {
                  Resource outputResource = inputResource.getResourceSet().getResource(
                    URI.createPlatformResourceURI(files[i].getFullPath().toString(), true),
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
  @Override
  public void selectionChanged(IAction action, ISelection selection)
  {
    action.setEnabled(IStructuredSelection.class.isInstance(selection) && getInputEPackage((IStructuredSelection)selection) != null);
  }
}