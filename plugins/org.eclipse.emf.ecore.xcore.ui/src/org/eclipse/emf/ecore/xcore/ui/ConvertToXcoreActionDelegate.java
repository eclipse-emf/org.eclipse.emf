/**
 * Copyright (c) 2004-2012 IBM Corporation and others.
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.util.ImportManager;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xcore.XImportDirective;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcoreFactory;
import org.eclipse.emf.ecore.xcore.scoping.XcoreImportedNamespaceAwareScopeProvider;
import org.eclipse.emf.ecore.xcore.ui.internal.XcoreActivator;
import org.eclipse.emf.ecore.xcore.util.EcoreXcoreBuilder;
import org.eclipse.emf.ecore.xcore.util.XcoreGenModelBuilder;
import org.eclipse.emf.ecore.xcore.util.XcoreGenModelInitializer;
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
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.common.collect.Sets;
import com.google.inject.Inject;
import com.google.inject.Provider;


/**
 *
 */
public class ConvertToXcoreActionDelegate extends ActionDelegate
{
  @Inject
  Provider<EcoreXcoreBuilder> ecoreXcoreBuilderProvider;

  @Inject
  XcoreGenModelBuilder genModelBuilder;

  @Inject
  XcoreGenModelInitializer genModelInitializer;

  private static final Set<String> IMPLICIT_ALIASES = Sets.newHashSet();
  static
  {
    for (EDataType eDataType : XcoreImportedNamespaceAwareScopeProvider.IMPLICIT_ALIASES)
    {
      IMPLICIT_ALIASES.add("org.eclipse.emf.ecore." + eDataType.getName());
    }
  }

  protected EPackage getInputEPackage(IStructuredSelection structuredSelection)
  {
    Object element = structuredSelection.getFirstElement();
    if (element instanceof IFile)
    {
      IFile file = (IFile)element;
      XtextResourceSet resourceSet = new XtextResourceSet();
      IProject project = file.getProject();
      resourceSet.setClasspathURIContext(JavaCore.create(project));
      resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap());
      if ("ecore".equals(file.getFullPath().getFileExtension()))
      {
        return (EPackage)EcoreUtil.getObjectByType(
          resourceSet.getResource(URI.createPlatformResourceURI(file.getFullPath().toString(), true), true).getContents(),
          EcorePackage.Literals.EPACKAGE);
      }
      else if ("genmodel".equals(file.getFullPath().getFileExtension()))
      {
        GenModel genModel = (GenModel)EcoreUtil.getObjectByType(
          resourceSet.getResource(URI.createPlatformResourceURI(file.getFullPath().toString(), true), true).getContents(),
          GenModelPackage.Literals.GEN_MODEL);
        if (genModel.getGenPackages().size() == 1)
        {
          return genModel.getGenPackages().get(0).getEcorePackage();
        }
      }
    }

    return null;
  }

  protected IFile getFile(Resource resource)
  {
    URI uri = resource.getURI();
    uri = resource.getResourceSet().getURIConverter().normalize(uri);
    String platformResourceString = uri.toPlatformString(true);
    return platformResourceString != null ? ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformResourceString)) : null;
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

    final EPackage inputEPackage = getInputEPackage((IStructuredSelection)workbenchWindow.getSelectionService().getSelection());
    final Resource inputResource = inputEPackage.eResource();

    URI outputResourceURI = inputResource.getURI().trimFileExtension().appendFileExtension("xcore");
    final Resource outputResource = inputResource.getResourceSet().createResource(outputResourceURI);

    ProgressMonitorDialog dialog = new ProgressMonitorDialog(workbenchWindow.getShell());
    try
    {
      dialog.run(false, true, new WorkspaceModifyOperation()
        {
          @Override
          protected void execute(IProgressMonitor progressMonitor)
          {
            try
            {
              progressMonitor.beginTask("", 1);

              ResourceSet resourceSet = inputResource.getResourceSet();
              Resource genModelResource = resourceSet.getResources().get(0);
              GenModel inputGenModel = null;
              if (genModelResource != inputResource)
              {
                inputGenModel = (GenModel)genModelResource.getContents().get(0);
                inputGenModel.reconcile();
                final GenModel genModel = GenModelFactory.eINSTANCE.createGenModel();
                genModel.initialize(Collections.singleton(inputEPackage));
                genModelResource.getContents().add(genModel);
                genModelInitializer.initialize(genModel);
                new Object()
                  {
                    void visit(GenBase genBase1, GenBase genBase2)
                    {
                      if (genBase1.eClass() == genBase2.eClass())
                      {
                        for (EAttribute eAttribute : genBase1.eClass().getEAllAttributes())
                        {
                          // TODO
                          if (!eAttribute.isMany() && genBase1.eIsSet(eAttribute))
                          {
                            Object value1 = genBase1.eGet(eAttribute);
                            Object value2 = genBase2.eGet(eAttribute);
                            if (value1 == null ? value2 != null : !value1.equals(value2))
                            {
                              EModelElement eModelElement = genBase2.getEcoreModelElement();
                              if (eModelElement == null)
                              {
                                eModelElement = genModel.getGenPackages().get(0).getEcorePackage();
                              }
                              EcoreUtil.setAnnotation(
                                eModelElement,
                                GenModelPackage.eNS_URI,
                                eAttribute.getName(),
                                EcoreUtil.convertToString(eAttribute.getEAttributeType(), value1));
                            }
                            for (Iterator<EObject> i = genBase1.eContents().iterator(), j = genBase2.eContents().iterator(); i.hasNext()
                              && j.hasNext();)
                            {
                              EObject content1 = i.next();
                              EObject content2 = j.next();
                              if (content1 instanceof GenBase && content2 instanceof GenBase)
                              {
                                visit((GenBase)content1, (GenBase)content2);
                              }
                            }
                          }
                        }
                      }
                    }
                  }.visit(inputGenModel, genModel);
              }
              else
              {
                // TODO what about no GenModel?
                // We'd want to create one and use that...
                throw new RuntimeException("No GenModel");
              }

              EcoreXcoreBuilder ecoreXcoreBuilder = ecoreXcoreBuilderProvider.get();
              ecoreXcoreBuilder.initialize(inputGenModel);
              XPackage xPackage = ecoreXcoreBuilder.getXPackage(inputEPackage);
              outputResource.getContents().add(xPackage);
              outputResource.getContents().add(inputGenModel);
              outputResource.getContents().add(inputEPackage);
              GenPackage ecoreGenPackage = inputGenModel.getEcoreGenPackage();
              if (ecoreGenPackage != null)
              {
                Resource ecoreResource = resourceSet.createResource(URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/Ecore.genmodel"));
                ecoreResource.getContents().add(ecoreGenPackage.getGenModel());
              }
              GenPackage xmlTypeGenPackage = inputGenModel.getXMLTypeGenPackage();
              if (xmlTypeGenPackage != null)
              {
                Resource xmlTypeResource = resourceSet.createResource(URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/XMLType.genmodel"));
                xmlTypeResource.getContents().add(xmlTypeGenPackage.getGenModel());
              }
              GenPackage xmlNamespaceGenPackage = inputGenModel.getXMLNamespaceGenPackage();
              if (xmlNamespaceGenPackage != null)
              {
                Resource xmlNamespaceResource = resourceSet.createResource(URI.createURI("platform:/plugin/org.eclipse.emf.ecore/model/XMLNamespace.genmodel"));
                xmlNamespaceResource.getContents().add(xmlNamespaceGenPackage.getGenModel());
              }
              ecoreXcoreBuilder.link();
              genModelBuilder.buildMap(inputGenModel);

              ImportManager importManager = new ImportManager(inputGenModel.getGenPackages().get(0).getInterfacePackageName())
                {
                  @Override
                  protected boolean shouldImport(String packageName, String shortName, String importName)
                  {
                    return true;
                  }
                };
              for (Iterator<EObject> i = inputEPackage.eAllContents(); i.hasNext();)
              {
                EObject eObject = i.next();
                if (eObject instanceof EGenericType)
                {
                  EGenericType eGenericType = (EGenericType)eObject;
                  EClassifier eClassifier = eGenericType.getEClassifier();
                  if (eClassifier != null)
                  {
                    GenClassifier genClassifier = inputGenModel.findGenClassifier(eClassifier);
                    String qualifiedName = genClassifier.getGenPackage().getInterfacePackageName() + "." + eClassifier.getName();
                    if (!IMPLICIT_ALIASES.contains(qualifiedName))
                    {
                      importManager.addImport(qualifiedName);
                    }
                  }
                }
              }
              for (String qualifiedName : importManager.getImports())
              {
                XImportDirective xImportDirective = XcoreFactory.eINSTANCE.createXImportDirective();
                xImportDirective.setImportedNamespace(qualifiedName);
                xPackage.getImportDirectives().add(xImportDirective);
              }

              try
              {
                Map<Object, Object> options = new HashMap<Object, Object>();
                SaveOptions.newBuilder().format().noValidation().getOptions().addTo(options);
                outputResource.save(options);

                IFile file = getFile(outputResource);
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

                try
                {
                  workbenchPage.openEditor(
                    new FileEditorInput(file),
                    workbenchWindow.getWorkbench().getEditorRegistry().getDefaultEditor(
                      file.getFullPath().toString(),
                      Platform.getContentTypeManager().getContentType("org.eclipse.emf.ecore")).getId());
                }
                catch (PartInitException pie)
                {
                  XcoreActivator.getInstance().getLog().log(
                    new Status(IStatus.WARNING, "org.eclipse.emf.ecore.xcore.ui", 0, pie.getLocalizedMessage(), pie));
                }
              }
              catch (IOException ioe)
              {
                XcoreActivator.getInstance().getLog().log(
                  new Status(IStatus.WARNING, "org.eclipse.emf.ecore.xcore.ui", 0, ioe.getLocalizedMessage(), ioe));
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
      XcoreActivator.getInstance().getLog().log(
        new Status(IStatus.WARNING, "org.eclipse.emf.ecore.xcore.ui", 0, ite.getLocalizedMessage(), ite));
    }
  }

  @Override
  public void selectionChanged(IAction action, ISelection selection)
  {
    action.setEnabled(IStructuredSelection.class.isInstance(selection) && getInputEPackage((IStructuredSelection)selection) != null);
  }
}