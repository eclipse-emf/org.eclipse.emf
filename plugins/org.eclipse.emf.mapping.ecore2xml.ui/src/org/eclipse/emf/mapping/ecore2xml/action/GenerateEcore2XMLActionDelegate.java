/**
 * <copyright>
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * $Id: GenerateEcore2XMLActionDelegate.java,v 1.11 2008/05/04 10:58:56 emerks Exp $
 */
package org.eclipse.emf.mapping.ecore2xml.action;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreSwitch;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.ecore2ecore.Ecore2EcoreMappingRoot;
import org.eclipse.emf.mapping.ecore2ecore.Ecore2EcorePackage;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLFactory;
import org.eclipse.emf.mapping.ecore2xml.ui.Ecore2XMLUIPlugin;
import org.eclipse.emf.mapping.ecore2xml.util.Ecore2XMLResource;
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


/**
 * A action that generates Ecore2XML mappings from Ecore2Ecore mappings.
 */
public class GenerateEcore2XMLActionDelegate extends ActionDelegate
{
  
  private static final String ECORE2ECORE_FILE_EXTENSION = "ecore2ecore";
  
  protected Ecore2EcoreMappingRoot getMappingRoot(IStructuredSelection structuredSelection)
  {
    
    if (IFile.class.isInstance(structuredSelection.getFirstElement()))
    {
      IFile file = (IFile)structuredSelection.getFirstElement();
      
      if (ECORE2ECORE_FILE_EXTENSION.equals(file.getFullPath().getFileExtension()))
      {
        return 
          (Ecore2EcoreMappingRoot)EcoreUtil.getObjectByType
            (new ResourceSetImpl().getResource(URI.createPlatformResourceURI(file.getFullPath().toString(), true), true).getContents(), 
             Ecore2EcorePackage.Literals.ECORE2_ECORE_MAPPING_ROOT);
      }
    }
    
    return null;
  }

  protected static int getXMLRepresentation(EStructuralFeature eStructuralFeature)
  {
    switch (ExtendedMetaData.INSTANCE.getFeatureKind(eStructuralFeature))
    {
      case ExtendedMetaData.ATTRIBUTE_FEATURE:
        return XMLResource.XMLInfo.ATTRIBUTE;
      case ExtendedMetaData.ELEMENT_FEATURE:
        return XMLResource.XMLInfo.ELEMENT;
      default:
        return XMLResource.XMLInfo.UNSPECIFIED;
    }
  }

  protected static XMLResource.XMLInfo createXMLInfo(EObject eObject)
  {
    final XMLResource.XMLInfo xmlInfo = Ecore2XMLFactory.eINSTANCE.createXMLInfo();

    if (eObject != null)
    {
      new EcoreSwitch<Object>()
        {
          @Override
          public Object caseEPackage(EPackage ePackage)
          {
            xmlInfo.setName(ePackage.getName());
            xmlInfo.setTargetNamespace(ExtendedMetaData.INSTANCE.getNamespace(ePackage));

            return xmlInfo;
          }

          @Override
          public Object caseEClassifier(EClassifier eClassifier)
          {
            xmlInfo.setName(ExtendedMetaData.INSTANCE.getName(eClassifier));
            xmlInfo.setTargetNamespace(ExtendedMetaData.INSTANCE.getNamespace(eClassifier));

            return xmlInfo;
          }

          @Override
          public Object caseEStructuralFeature(EStructuralFeature eStructuralFeature)
          {
            xmlInfo.setName(ExtendedMetaData.INSTANCE.getName(eStructuralFeature));
            xmlInfo.setTargetNamespace(ExtendedMetaData.INSTANCE.getNamespace(eStructuralFeature));
            xmlInfo.setXMLRepresentation(getXMLRepresentation(eStructuralFeature));

            return xmlInfo;
          }
        }.doSwitch(eObject);
    }

    return xmlInfo;
  }
  
  protected static XMLResource.XMLMap createXMLMap(Ecore2EcoreMappingRoot mappingRoot)
  {
    final XMLResource.XMLMap xmlMap = Ecore2XMLFactory.eINSTANCE.createXMLMap();

    for (TreeIterator<Mapping> mappings = mappingRoot.treeIterator(); mappings.hasNext();)
    {
      Mapping mapping = mappings.next();
      EList<EObject> inputs = mapping.getInputs();
      final EObject input = inputs.isEmpty() ? null : inputs.get(0);

      for (EObject output : mapping.getOutputs())
      {
        new EcoreSwitch<Object>()
          {
            @Override
            public Object caseEPackage(EPackage ePackage)
            {
              XMLResource.XMLInfo xmlInfo = createXMLInfo(input);
              xmlMap.add(ePackage, xmlInfo);
              return xmlInfo;
            }

            @Override
            public Object caseEClassifier(EClassifier eClassifier)
            {
              XMLResource.XMLInfo xmlInfo = createXMLInfo(input);
              xmlMap.add(eClassifier, xmlInfo);
              return xmlInfo;
            }

            @Override
            public Object caseEStructuralFeature(EStructuralFeature eStructuralFeature)
            {
              XMLResource.XMLInfo xmlInfo = createXMLInfo(input);

              if (input == null)
              {
                int xmlRepresentation = getXMLRepresentation(eStructuralFeature);

                if (xmlRepresentation == XMLResource.XMLInfo.UNSPECIFIED)
                {
                  xmlRepresentation = eStructuralFeature instanceof EReference && ((EReference)eStructuralFeature).isContainment()
                    ? XMLResource.XMLInfo.ELEMENT : XMLResource.XMLInfo.ATTRIBUTE;
                }

                xmlInfo.setXMLRepresentation(xmlRepresentation);
              }

              xmlMap.add(eStructuralFeature, xmlInfo);
              return xmlInfo;
            }
          }.doSwitch(output);
      }
    }

    return xmlMap;
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
    
    try
    {
      new ProgressMonitorDialog(workbenchWindow.getShell()).run(false, true, new WorkspaceModifyOperation()
        {
        
        @Override
        protected void execute(IProgressMonitor progressMonitor)
        {
          try
          {
            progressMonitor.beginTask("", IProgressMonitor.UNKNOWN);
            
            Ecore2EcoreMappingRoot mappingRoot = getMappingRoot((IStructuredSelection)workbenchWindow.getSelectionService().getSelection());
            
            if (mappingRoot != null)
            {
              Resource ecore2ecoreResource = mappingRoot.eResource();
              
              URI ecore2xmlURI = ecore2ecoreResource.getURI().trimFileExtension().appendFileExtension(Ecore2XMLResource.FILE_EXTENSION);
              Resource ecore2xmlResource = ecore2ecoreResource.getResourceSet().createResource(ecore2xmlURI);
              
              ecore2xmlResource.getContents().add((EObject)createXMLMap(mappingRoot));
              
              try
              {
                ecore2xmlResource.save(null);
                
                IFile file = getFile(ecore2xmlResource);
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
                  Ecore2XMLUIPlugin.INSTANCE.log(pie);                      
                }
              }
              catch (IOException ioe)
              {
                Ecore2XMLUIPlugin.INSTANCE.log(ioe);                      
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
      Ecore2XMLUIPlugin.INSTANCE.log(ite);                      
    }
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
    action.setEnabled(IStructuredSelection.class.isInstance(selection) && getMappingRoot((IStructuredSelection)selection) != null);
  }
  
}