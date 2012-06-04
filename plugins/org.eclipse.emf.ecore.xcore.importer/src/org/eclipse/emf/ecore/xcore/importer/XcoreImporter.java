/**
 * Copyright (c) 2005-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xcore.importer;

import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenRuntimePlatform;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.converter.ConverterPlugin;
import org.eclipse.emf.converter.util.ConverterUtil;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.xtext.resource.XtextResourceSet;


public class XcoreImporter extends ModelImporter
{
  protected ResourceSet resourceSet;

  public XcoreImporter()
  {
    resourceSet = new XtextResourceSet();
    resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap());
  }

  @Override
  public String getID()
  {
    return "org.eclipse.emf.ecore.xcore.importer";
  }

  @Override
  protected Diagnostic doComputeEPackages(Monitor monitor) throws Exception
  {
    BasicDiagnostic basicDiagnostic = null;

    List<URI> locationURIs = getModelLocationURIs();
    if (locationURIs.isEmpty())
    {
      basicDiagnostic =
        new BasicDiagnostic
          (Diagnostic.ERROR,
           ConverterPlugin.ID,
           ConverterUtil.ACTION_DIALOG_NONE | ConverterUtil.ACTION_MESSAGE_SET_ERROR,
           XcoreImporterPlugin.INSTANCE.getString("_UI_SpecifyAValidXcore_message"),
           null);
    }
    else
    {
      resourceSet.getResources().clear();
      monitor.beginTask("", 2);
      monitor.subTask(XcoreImporterPlugin.INSTANCE.getString("_UI_Loading_message", new Object []{ locationURIs }));

      for (URI locationURI : locationURIs)
      {
        Resource inputResource = resourceSet.getResource(locationURI, true);
        EcoreUtil.resolveAll(resourceSet);

        GenModel genModel =   (GenModel)EcoreUtil.getObjectByType(inputResource.getContents(), GenModelPackage.Literals.GEN_MODEL);
        EPackage ePackage =   (EPackage)EcoreUtil.getObjectByType(inputResource.getContents(), EcorePackage.Literals.EPACKAGE);
        List<EPackage> ePackages = getEPackages();
        if (ePackage != null)
        {
          inputResource.getContents().remove(ePackage);
          ePackages.add(ePackage);
        }

        EList<GenPackage> usedGenPackages = genModel.getUsedGenPackages();
        for (GenPackage usedGenPackage : usedGenPackages)
        {
          ePackages.add(usedGenPackage.getEcorePackage());
        }
        getReferencedGenPackages().addAll(usedGenPackages);
      }
    }

    if (basicDiagnostic == null)
    {
      return Diagnostic.OK_INSTANCE;
    }
    else
    {
      return basicDiagnostic;
    }
  }

  @Override
  protected void adjustGenModel(Monitor monitor)
  {
    super.adjustGenModel(monitor);

    IPath genModelFileFullPath = getGenModelPath();
    URI genModelURI = createFileURI(genModelFileFullPath.toString());

    for (URI uri : getModelLocationURIs())
    {
      getGenModel().getForeignModel().add(makeRelative(uri, genModelURI).toString());
    }
  }

  @Override
  protected void handleOriginalGenModel() throws DiagnosticException
  {
    URI genModelURI = getOriginalGenModel().eResource().getURI();
    StringBuffer text = new StringBuffer();
    for (String value : getOriginalGenModel().getForeignModel())
    {
      if (value.endsWith(".xcore"))
      {
        text.append(makeAbsolute(URI.createURI(value), genModelURI).toString());
        text.append(" ");
      }
    }
    setModelLocation(text.toString().trim());
  }

  @Override
  public void prepareGenModelAndEPackages(Monitor monitor)
  {
    super.prepareGenModelAndEPackages(monitor);

    // Make sure to pull in all the GenModel settings that were specified in the Xcore annotations.
    //
    GenModel oldOriginalGenModel = originalGenModel;
    if (oldOriginalGenModel == null)
    {
      GenModel mainGenModel = getGenModel();
      for (Resource xcoreResource : resourceSet.getResources())
      {
        GenModel genModel = (GenModel)EcoreUtil.getObjectByType(xcoreResource.getContents(), GenModelPackage.Literals.GEN_MODEL);
        if (genModel != null && EcoreUtil.getObjectByType(xcoreResource.getContents(), EcorePackage.Literals.EPACKAGE) == null)
        {
          mainGenModel.reconcile(genModel);
        }
      }
      GenRuntimePlatform runtimePlatform = mainGenModel.getRuntimePlatform();
      originalGenModel = null;
      adjustGenModel(monitor);
      originalGenModel = oldOriginalGenModel;
      mainGenModel.setRuntimePlatform(runtimePlatform);
    }
  }
}