/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: EcoreImporter.java,v 1.2 2005/05/24 18:02:40 marcelop Exp $
 */
package org.eclipse.emf.importer.ecore;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.importer.ModelImporter;


public class EcoreImporter extends ModelImporter
{
  public String getID()
  {
    return "org.eclipse.emf.importer.ecore";
  }

  protected IStatus doComputeEPackages(IProgressMonitor progressMonitor) throws Exception
  {
    IStatus status = Status.OK_STATUS;

    List locationURIs = getModelLocationURIs();
    if (locationURIs.isEmpty())
    {
      status = new Status(
        IStatus.ERROR,
        EcoreImporterPlugin.getPlugin().getBundle().getSymbolicName(),
        0,
        EcoreImporterPlugin.INSTANCE.getString("_UI_SpecifyAValidCoreModel_message"),
        null);
    }
    else
    {
      progressMonitor.beginTask("", 2);
      progressMonitor.subTask(EcoreImporterPlugin.INSTANCE.getString("_UI_Loading_message", new Object []{ locationURIs }));

      ResourceSet ecoreResourceSet = createResourceSet();
      for (Iterator i = locationURIs.iterator(); i.hasNext(); )
      {
        URI ecoreModelLocation = (URI)i.next();
        Resource ecoreResource = ecoreResourceSet.getResource(ecoreModelLocation, true);
        for (Iterator j = ecoreResourceSet.getAllContents(); j.hasNext(); )
        {
          Object content = j.next();
          if (content instanceof EObject)
          {
            for (Iterator k = ((EObject)content).eCrossReferences().iterator(); k.hasNext(); )
            {
              Object referencedObject = k.next();
            }
          }
        }
      }

      for (Iterator i = ecoreResourceSet.getResources().iterator(); i.hasNext(); )
      {
        Resource resource = (Resource)i.next();
        getEPackages().addAll(EcoreUtil.getObjectsByType(resource.getContents(), EcorePackage.eINSTANCE.getEPackage()));
      }
    }
    return status;
  }
  
  public void addToResource(EPackage ePackage, ResourceSet resourceSet)
  {
    if (ePackage.eResource() != null && getGenModel().eResource() != null)
    {
      URI ePackageURI = ePackage.eResource().getURI();
      URI genModelURI = getGenModel().eResource().getURI();
      
      if (!ePackageURI.trimSegments(1).equals(genModelURI.trimSegments(1)))
      {
        ePackage.eResource().getContents().remove(ePackage);
      }
    }
    super.addToResource(ePackage, resourceSet);
  }

  protected void adjustGenModel(IProgressMonitor progressMonitor)
  {
    super.adjustGenModel(progressMonitor);

    URI genModelURI = createFileURI(getGenModelPath().toString());
    for (Iterator i = getModelLocationURIs().iterator(); i.hasNext();)
    {
      getGenModel().getForeignModel().add(makeRelative((URI)i.next(), genModelURI).toString());
    }
  }

  protected void loadOriginalGenModel(URI genModelURI)
  {
    super.loadOriginalGenModel(genModelURI);

    StringBuffer text = new StringBuffer();
    for (Iterator i = getOriginalGenModel().getForeignModel().iterator(); i.hasNext();)
    {
      String value = (String)i.next();
      if (value.endsWith(".ecore") || value.endsWith(".emof"))
      {
        text.append(makeAbsolute(URI.createURI(value), genModelURI).toString());
        text.append(" ");
      }
    }
    setModelLocation(text.toString().trim());
  }
}