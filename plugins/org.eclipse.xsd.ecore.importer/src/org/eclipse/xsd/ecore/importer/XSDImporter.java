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
 * $Id: XSDImporter.java,v 1.3 2005/05/19 16:47:39 marcelop Exp $
 */
package org.eclipse.xsd.ecore.importer;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.importer.ImporterPlugin;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.util.ImporterUtil;

import org.eclipse.xsd.ecore.XSDEcoreBuilder;


public class XSDImporter extends ModelImporter
{
  public static class MapHelper
  {
    public void setNewMapper(XSDEcoreBuilder ecoreBuilder)
    {
      try
      {
        org.eclipse.emf.mapping.xsd2ecore.XSD2EcoreMapper mapper = new org.eclipse.emf.mapping.xsd2ecore.XSD2EcoreMapper();
        ecoreBuilder.setMapper(mapper);
      }
      catch (Exception e)
      {
        XSDImporterPlugin.INSTANCE.log(e);
      }
    }
  }

  protected boolean createEcoreMap = false;
  protected EObject mappingRoot;

  public void dispose()
  {
    mappingRoot = null;
    super.dispose();
  }

  public String getID()
  {
    return "org.eclipse.xsd.ecore.importer";
  }

  public boolean canCreateEcoreMap()
  {
    return Platform.getBundle("org.eclipse.emf.mapping.xsd2ecore") != null;
  }

  public void setCreateEcoreMap(boolean createEcoreMap)
  {
    this.createEcoreMap = createEcoreMap;
  }

  public boolean createEcoreMap()
  {
    return createEcoreMap && canCreateEcoreMap();
  }

  public void setMappingRoot(EObject mappingRoot)
  {
    this.mappingRoot = mappingRoot;
  }

  public EObject getMappingRoot()
  {
    return mappingRoot;
  }

  protected IStatus doComputeEPackages(IProgressMonitor progressMonitor) throws Exception
  {
    IStatus xsdStatus = null;

    List locationURIs = getModelLocationURIs();
    if (locationURIs.isEmpty())
    {
      xsdStatus = new Status(
        IStatus.ERROR,
        ImporterPlugin.ID,
        ImporterUtil.ACTION_DIALOG_NONE | ImporterUtil.ACTION_MESSAGE_SET_ERROR,
        XSDImporterPlugin.INSTANCE.getString("_UI_SpecifyAValidXMLSchema_message"),
        null);
    }
    else
    {
      progressMonitor.beginTask("", 2);
      progressMonitor.subTask(XSDImporterPlugin.INSTANCE.getString("_UI_Loading_message", new Object []{ locationURIs }));

      XSDEcoreBuilder ecoreBuilder = new XSDEcoreBuilder();
      if (createEcoreMap())
      {
        new MapHelper().setNewMapper(ecoreBuilder);
      }
      List result = (List)ecoreBuilder.generate(locationURIs);

      Object lastElement = removeNonEPackageFromTheEnd(result);
      if (lastElement instanceof List)
      {
        List diagnostics = (List)lastElement;
        if (!diagnostics.isEmpty())
        {
          MultiStatus status = new MultiStatus(
            ImporterPlugin.ID,
            ImporterUtil.ACTION_MESSAGE_NONE,
            XSDImporterPlugin.INSTANCE.getString("_UI_ErrorsWereDetectedXMLSchema_message"),
            null);

          for (Iterator i = diagnostics.iterator(); i.hasNext();)
          {
            List information = (List)i.next();
            status.add(new Status(
              "error".equals(information.get(0)) ? IStatus.ERROR : "warning".equals(information.get(0)) ? IStatus.WARNING : IStatus.INFO,
              XSDImporterPlugin.getPlugin().getBundle().getSymbolicName(),
              0,
              (String)information.get(1),
              null));
          }
          xsdStatus = status;
        }

        lastElement = removeNonEPackageFromTheEnd(result);
      }

      if (lastElement instanceof EObject)
      {
        setMappingRoot((EObject)lastElement);
      }

      getEPackages().addAll(result);
    }

    if (xsdStatus == null)
    {
      return Status.OK_STATUS;
    }
    else
    {
      return xsdStatus;
    }
  }

  protected Object removeNonEPackageFromTheEnd(List list)
  {
    int lastIndex = list.size() - 1;
    if (lastIndex >= 0 && !(list.get(lastIndex) instanceof EPackage))
    {
      return list.remove(lastIndex);
    }
    else
    {
      return null;
    }
  }

  protected void adjustGenPackageDuringTraverse(GenPackage genPackage)
  {
    genPackage.setResource(GenResourceKind.XML_LITERAL);
  }

  protected void adjustGenModel(IProgressMonitor progressMonitor)
  {
    super.adjustGenModel(progressMonitor);

    IPath genModelFileFullPath = getGenModelPath();
    URI genModelURI = createFileURI(genModelFileFullPath.toString());

    for (Iterator i = getModelLocationURIs().iterator(); i.hasNext();)
    {
      getGenModel().getForeignModel().add(makeRelative((URI)i.next(), genModelURI).toString());
    }

    if (getMappingRoot() != null)
    {
      IPath mappingPath = genModelFileFullPath.removeFileExtension().addFileExtension("xsd2ecore");
      URI mappingModelURI = createFileURI(mappingPath.toString());
      Resource mappingModelResource = getGenModelResourceSet().createResource(mappingModelURI);
      mappingModelResource.getContents().add(getMappingRoot());
    }
  }

  protected List computeResourcesToBeSaved()
  {
    List resources = super.computeResourcesToBeSaved();
    if (getMappingRoot() != null)
    {
      resources.add(getMappingRoot().eResource());
    }
    return resources;
  }

  protected void loadOriginalGenModel(URI genModelURI)
  {
    super.loadOriginalGenModel(genModelURI);

    StringBuffer text = new StringBuffer();
    for (Iterator i = getOriginalGenModel().getForeignModel().iterator(); i.hasNext();)
    {
      String value = (String)i.next();
      if (value.endsWith(".xsd") || value.endsWith(".wsdl"))
      {
        text.append(makeAbsolute(URI.createURI(value), genModelURI).toString());
        text.append(" ");
      }
    }
    setModelLocation(text.toString().trim());
  }
}