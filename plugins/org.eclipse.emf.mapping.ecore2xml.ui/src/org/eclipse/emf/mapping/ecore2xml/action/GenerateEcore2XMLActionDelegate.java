/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: GenerateEcore2XMLActionDelegate.java,v 1.2 2005/05/06 15:03:19 khussey Exp $
 */
package org.eclipse.emf.mapping.ecore2xml.action;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
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
import org.eclipse.emf.mapping.ecore2xml.util.Ecore2XMLResource;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;
import org.eclipse.ui.actions.WorkspaceModifyOperation;


/**
 * A action that generates Ecore2XML mappings from Ecore2Ecore mappings.
 */
public class GenerateEcore2XMLActionDelegate extends ActionDelegate
{

  private static final String ECORE2ECORE_FILE_EXTENSION = "ecore2ecore"; //$NON-NLS-1$

  protected Ecore2EcoreMappingRoot getMappingRoot(IStructuredSelection structuredSelection)
  {

    if (IFile.class.isInstance(structuredSelection.getFirstElement()))
    {
      IFile file = (IFile)structuredSelection.getFirstElement();

      if (ECORE2ECORE_FILE_EXTENSION.equals(file.getFullPath().getFileExtension()))
      {
        return (Ecore2EcoreMappingRoot)EcoreUtil.getObjectByType(new ResourceSetImpl().getResource(
          URI.createPlatformResourceURI(file.getFullPath().toString(), true),
          true).getContents(), Ecore2EcorePackage.eINSTANCE.getEcore2EcoreMappingRoot());
      }
    }

    return null;
  }

  protected static XMLResource.XMLInfo createXMLInfo(EObject eObject)
  {
    return (XMLResource.XMLInfo)new EcoreSwitch()
      {

        public Object caseEPackage(EPackage ePackage)
        {
          XMLResource.XMLInfo xmlInfo = Ecore2XMLFactory.eINSTANCE.createXMLInfo();

          xmlInfo.setName(ePackage.getName());
          xmlInfo.setTargetNamespace(ExtendedMetaData.INSTANCE.getNamespace(ePackage));

          return xmlInfo;
        }

        public Object caseEClassifier(EClassifier eClassifier)
        {
          XMLResource.XMLInfo xmlInfo = Ecore2XMLFactory.eINSTANCE.createXMLInfo();

          xmlInfo.setName(ExtendedMetaData.INSTANCE.getName(eClassifier));
          xmlInfo.setTargetNamespace(ExtendedMetaData.INSTANCE.getNamespace(eClassifier));

          return xmlInfo;
        }

        public Object caseEStructuralFeature(EStructuralFeature eStructuralFeature)
        {
          XMLResource.XMLInfo xmlInfo = Ecore2XMLFactory.eINSTANCE.createXMLInfo();

          xmlInfo.setName(ExtendedMetaData.INSTANCE.getName(eStructuralFeature));
          xmlInfo.setTargetNamespace(ExtendedMetaData.INSTANCE.getNamespace(eStructuralFeature));

          switch (ExtendedMetaData.INSTANCE.getFeatureKind(eStructuralFeature))
          {

            case ExtendedMetaData.ATTRIBUTE_FEATURE:
              xmlInfo.setXMLRepresentation(XMLResource.XMLInfo.ATTRIBUTE);
              break;
            case ExtendedMetaData.ELEMENT_FEATURE:
              xmlInfo.setXMLRepresentation(XMLResource.XMLInfo.ELEMENT);
              break;
          }

          return xmlInfo;
        }
      }.doSwitch(eObject);
  }

  protected static XMLResource.XMLMap createXMLMap(Ecore2EcoreMappingRoot mappingRoot)
  {
    XMLResource.XMLMap xmlMap = Ecore2XMLFactory.eINSTANCE.createXMLMap();

    for (TreeIterator mappings = mappingRoot.treeIterator(); mappings.hasNext();)
    {

      Mapping mapping = (Mapping)mappings.next();

      if (!mapping.getInputs().isEmpty())
      {
        EObject eObject = (EObject)mapping.getInputs().get(0);

        for (Iterator outputs = mapping.getOutputs().iterator(); outputs.hasNext();)
        {
          xmlMap.add((ENamedElement)outputs.next(), createXMLInfo(eObject));
        }
      }
    }

    return xmlMap;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(IAction action)
  {
    final IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

    try
    {
      new ProgressMonitorDialog(workbenchWindow.getShell()).run(false, true, new WorkspaceModifyOperation()
        {

          protected void execute(IProgressMonitor progressMonitor)
          {
            try
            {
              progressMonitor.beginTask("", IProgressMonitor.UNKNOWN); //$NON-NLS-1$

              Ecore2EcoreMappingRoot mappingRoot = getMappingRoot((IStructuredSelection)workbenchWindow.getSelectionService().getSelection());

              if (mappingRoot != null)
              {
                Resource ecore2ecoreResource = mappingRoot.eResource();

                URI ecore2xmlURI = ecore2ecoreResource.getURI().trimFileExtension().appendFileExtension(Ecore2XMLResource.FILE_EXTENSION);
                Resource ecore2xmlResource = ecore2ecoreResource.getResourceSet().createResource(ecore2xmlURI);

                ecore2xmlResource.getContents().add(createXMLMap(mappingRoot));

                try
                {
                  ecore2xmlResource.save(null);
                }
                catch (IOException ioe)
                {
                  ioe.printStackTrace(System.err);
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
      ite.printStackTrace(System.err);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
   *      org.eclipse.jface.viewers.ISelection)
   */
  public void selectionChanged(IAction action, ISelection selection)
  {
    action.setEnabled(IStructuredSelection.class.isInstance(selection) && getMappingRoot((IStructuredSelection)selection) != null);
  }

}