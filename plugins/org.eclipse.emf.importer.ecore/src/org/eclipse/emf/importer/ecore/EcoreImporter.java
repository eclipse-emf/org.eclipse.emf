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
 * $Id: EcoreImporter.java,v 1.7 2005/11/23 19:07:05 emerks Exp $
 */
package org.eclipse.emf.importer.ecore;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
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

  protected Diagnostic doComputeEPackages(Monitor monitor) throws Exception
  {
    Diagnostic diagnostic = Diagnostic.OK_INSTANCE;

    List locationURIs = getModelLocationURIs();
    if (locationURIs.isEmpty())
    {
      diagnostic = new BasicDiagnostic(
        Diagnostic.ERROR,
        EcoreImporterPlugin.getPlugin().getBundle().getSymbolicName(),
        0,
        EcoreImporterPlugin.INSTANCE.getString("_UI_SpecifyAValidCoreModel_message"),
        null);
    }
    else
    {
      monitor.beginTask("", 2);
      monitor.subTask(EcoreImporterPlugin.INSTANCE.getString("_UI_Loading_message", new Object []{ locationURIs }));

      ResourceSet ecoreResourceSet = createResourceSet();
      for (Iterator i = locationURIs.iterator(); i.hasNext(); )
      {
        URI ecoreModelLocation = (URI)i.next();
        ecoreResourceSet.getResource(ecoreModelLocation, true);
      }
      EcoreUtil.resolveAll(ecoreResourceSet);

      for (Iterator i = ecoreResourceSet.getResources().iterator(); i.hasNext(); )
      {
        Resource resource = (Resource)i.next();
        getEPackages().addAll(EcoreUtil.getObjectsByType(resource.getContents(), EcorePackage.eINSTANCE.getEPackage()));
      }
    }
    return diagnostic;
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

  protected void adjustGenModel(Monitor monitor)
  {
    super.adjustGenModel(monitor);

    URI genModelURI = createFileURI(getGenModelPath().toString());
    for (Iterator i = getModelLocationURIs().iterator(); i.hasNext();)
    {
      getGenModel().getForeignModel().add(makeRelative((URI)i.next(), genModelURI).toString());
    }
  }

  protected void loadOriginalGenModel(URI genModelURI) throws DiagnosticException
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