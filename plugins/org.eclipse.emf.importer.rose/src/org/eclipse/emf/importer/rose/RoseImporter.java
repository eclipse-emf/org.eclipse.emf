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
 * $Id: RoseImporter.java,v 1.1 2005/05/10 17:40:33 davidms Exp $
 */
package org.eclipse.emf.importer.rose;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.rose.builder.RoseUtil;
import org.eclipse.emf.importer.rose.builder.UnitTreeNode;


/**
 * @since 2.1.0
 */
public class RoseImporter extends ModelImporter
{
  protected Map pathMap;
  protected boolean noQualify = false;
  protected boolean unsettablePrimitive = false;

  public void dispose()
  {
    if (pathMap != null)
    {
      pathMap.clear();
      pathMap = null;
    }

    super.dispose();
  }

  public String getID()
  {
    return "org.eclipse.emf.importer.rose";
  }

  public Map getPathMap()
  {
    if (pathMap == null)
    {
      pathMap = new HashMap();
    }
    return pathMap;
  }

  public boolean isNoQualify()
  {
    return noQualify;
  }

  public void setNoQualify(boolean noQualify)
  {
    this.noQualify = noQualify;
  }

  public boolean isUnsettablePrimitive()
  {
    return unsettablePrimitive;
  }

  public void setUnsettablePrimitive(boolean unsettablePrimitive)
  {
    this.unsettablePrimitive = unsettablePrimitive;
  }

  public File computeRoseModelFile()
  {
    URI modelURI = getFirstModelLocationURI(true);
    if (modelURI != null)
    {
      File modelFile = new File(modelURI.isFile() ? modelURI.toFileString() : modelURI.toString());
      if (modelFile.isFile())
      {
        return modelFile;
      }
    }
    return null;
  }

  protected IStatus createStatus(String errorMessage, IStatus childStatus)
  {
    if (errorMessage == null)
    {
      return Status.OK_STATUS;
    }
    else
    {
      IStatus status = null;
      if (childStatus == null || childStatus.isOK())
      {
        status = new Status(IStatus.ERROR, RoseImporterPlugin.getPlugin().getBundle().getSymbolicName(), 0, errorMessage, null);
      }
      else
      {
        status = new MultiStatus(RoseImporterPlugin.getPlugin().getBundle().getSymbolicName(), 0, errorMessage, null);
        ((MultiStatus)status).add(childStatus);
      }
      return status;
    }
  }

  public IStatus loadPathMap(IProgressMonitor progressMonitor) throws Exception
  {
    IStatus roseStatus = null;
    String errorMessage = null;

    File roseModelFile = computeRoseModelFile();
    if (roseModelFile == null)
    {
      errorMessage = RoseImporterPlugin.INSTANCE.getString("_UI_SpecifyAValidRoseModel_message");
    }
    else
    {
      String roseModelAbsolutePath = roseModelFile.getAbsolutePath();

      progressMonitor.beginTask("", 2);
      progressMonitor.subTask(RoseImporterPlugin.INSTANCE.getString("_UI_Loading_message", new Object []{ roseModelAbsolutePath }));

      Map pathMap = getPathMap();
      for (;;)
      {
        RoseUtil roseUtil = createRoseUtil();
        roseUtil.getRoseEcoreBuilder().noQualify = noQualify;
        roseUtil.getRoseEcoreBuilder().unsettablePrimitive = unsettablePrimitive;

        roseUtil.getVariableToDirectoryMap().putAll(pathMap);

        UnitTreeNode unitTreeNode = roseUtil.createRoseUnitTreeAndTable(roseModelAbsolutePath, null);
        if (unitTreeNode == null)
        {
          errorMessage = RoseImporterPlugin.INSTANCE.getString("_UI_SpecifyAValidRoseModel_message");
        }
        else
        {
          pathMap.putAll(roseUtil.getVariableToDirectoryMap());
          pathMap.remove(null);

          if (pathMap.containsKey("VABASE_PLUGINS_PATH") && pathMap.get("VABASE_PLUGINS_PATH") == null)
          {
            int index = roseModelAbsolutePath.indexOf(File.separator + "plugins" + File.separator);
            if (index != -1)
            {
              pathMap.put("VABASE_PLUGINS_PATH", roseModelAbsolutePath.substring(0, index + 8));
              continue;
            }
          }

          if (getGenModelFileName() == null)
          {
            String fileName = unitTreeNode.getNodes().size() != 1
              ? roseModelFile.getName() : ((UnitTreeNode)unitTreeNode.getNodes().get(0)).getRoseFileName();

            int index = fileName.lastIndexOf(File.separatorChar);
            if (index >= 0)
            {
              fileName = fileName.substring(index + 1);
            }
            index = fileName.lastIndexOf(".");
            if (index >= 0)
            {
              fileName = fileName.substring(0, index);
            }
            setGenModelFileName(fileName + ".genmodel");
          }

          for (Iterator entries = roseUtil.getVariableToDirectoryMap().entrySet().iterator(); entries.hasNext();)
          {
            Map.Entry entry = (Map.Entry)entries.next();
            if (entry.getValue() == null)
            {
              errorMessage = RoseImporterPlugin.INSTANCE.getString("_UI_SpecifyTheSymbolLocations_message");
              break;
            }
          }

          roseStatus = roseUtil.getStatus();
          if (errorMessage == null && !roseStatus.isOK())
          {
            errorMessage = RoseImporterPlugin.INSTANCE.getString("_UI_SpecifyTheSymbolLocations_message");
          }
        }
        break;
      }
    }

    return createStatus(errorMessage, roseStatus);
  }

  protected IStatus doComputeEPackages(IProgressMonitor progressMonitor) throws Exception
  {
    IStatus roseStatus = null;
    String errorMessage = null;

    File roseModelFile = computeRoseModelFile();
    if (roseModelFile == null)
    {
      errorMessage = RoseImporterPlugin.INSTANCE.getString("_UI_SpecifyAValidRoseModel_message");
    }
    else
    {
      String roseModelAbsolutePath = roseModelFile.getAbsolutePath();

      progressMonitor.beginTask("", 2);
      progressMonitor.subTask(RoseImporterPlugin.INSTANCE.getString(
        "_UI_CreatingPackagesFor_message",
        new Object []{ roseModelAbsolutePath }));

      RoseUtil roseUtil = createRoseUtil();
      roseUtil.getVariableToDirectoryMap().putAll(getPathMap());

      UnitTreeNode unitTreeNode = roseUtil.createRoseUnitTreeAndTable(roseModelAbsolutePath, null);
      roseUtil.createExtent4RoseUnitTree(unitTreeNode);
      roseUtil.processUnitTree(unitTreeNode);

      for (Iterator i = roseUtil.getEPackageToInformationMap().entrySet().iterator(); i.hasNext();)
      {
        Map.Entry entry = (Map.Entry)i.next();
        List information = (List)entry.getValue();
        if (information != null)
        {
          EPackageInfo ePackageInfo = getEPackageInfo((EPackage)entry.getKey());
          ePackageInfo.setBasePackage((String)information.get(0));
          ePackageInfo.setPrefix((String)information.get(1));
        }
      }

      traverseEPackages(unitTreeNode);

      roseStatus = roseUtil.getStatus();
      if (errorMessage == null && !roseStatus.isOK())
      {
        errorMessage = RoseImporterPlugin.INSTANCE.getString("_UI_UnableToCalculateEPackages_message");
      }
    }

    if (getEPackages().size() == 1)
    {
      getEPackageInfo((EPackage)getEPackages().get(0)).setGenerate(true);
    }
    return createStatus(errorMessage, roseStatus);
  }

  protected void traverseEPackages(UnitTreeNode subNode)
  {
    getEPackages().addAll(subNode.getExtent());

    for (Iterator i = subNode.getExtent().iterator(); i.hasNext();)
    {
      EPackage ePackage = (EPackage)i.next();
      EPackageInfo ePackageInfo = getEPackageInfo(ePackage);

      String ecoreFileName = null;
      String roseFileName = subNode.getRoseFileName();
      int indexOfSlash = roseFileName.lastIndexOf(File.separator);

      GenPackage genPackage = getGenPackage(ePackage);
      if (genPackage != null)
      {
        String ePackagePath = genPackage.getEcorePackage().eResource().getURI().lastSegment();
        ecoreFileName = URI.decode(ePackagePath);
      }
      else if (roseFileName.endsWith(".cat") && indexOfSlash != -1)
      {
        String baseName = roseFileName.substring(indexOfSlash + 1, roseFileName.length() - 4);
        int indexOfDot = baseName.lastIndexOf(".");
        if (indexOfDot != -1)
        {
          baseName = baseName.substring(indexOfDot + 1);
        }
        ecoreFileName = baseName + ".ecore";
      }
      else if (subNode.getExtent().size() == 1)
      {
        ecoreFileName = subNode.getName() + ".ecore";
      }
      else
      {
        ecoreFileName = ePackage.getName() + ".ecore";
      }

      ePackageInfo.setEcoreFileName(new StringBuffer(ecoreFileName));
    }

    for (Iterator i = subNode.getNodes().iterator(); i.hasNext();)
    {
      UnitTreeNode childTree = (UnitTreeNode)i.next();
      traverseEPackages(childTree);
    }
  }
  
  protected void adjustEPackages()
  {
    //Rose has its own way of adjusting the EPackages
  }

  protected void adjustGenModel(IProgressMonitor progressMonitor)
  {
    super.adjustGenModel(progressMonitor);

    GenModel genModel = getGenModel();
    String projectName = getModelProjectName();
    IPath genModelPath = getGenModelPath();
    URI modelURI = (URI)getModelLocationURIs().get(0);
    URI genModelURI = getGenModel().eResource().getURI();

    genModel.getForeignModel().add(makeRelative(modelURI, genModelURI).toFileString());
    IPath projectLocation = getWorkspaceRoot().getProject(projectName).getLocation();
    if (projectLocation != null)
    {
      projectLocation = projectLocation.removeLastSegments(1);
    }
    else
    {
      projectLocation = getGenModelProjectLocation();
    }

    for (Iterator i = getPathMap().entrySet().iterator(); i.hasNext();)
    {
      Map.Entry entry = (Map.Entry)i.next();
      genModel.getForeignModel().add(entry.getKey());
      String value = (String)entry.getValue();
      if (new Path(value).equals(projectLocation))
      {
        value = "..";
        for (int depth = genModelPath.segmentCount(); depth > 2; --depth)
        {
          value += "/..";
        }
        genModel.getForeignModel().add(value);
      }
      else
      {
        genModel.getForeignModel().add(value);
      }
    }
  }

  protected void loadOriginalGenModel(URI genModelURI)
  {
    super.loadOriginalGenModel(genModelURI);

    try
    {
      Iterator values = getOriginalGenModel().getForeignModel().iterator();
      if (values.hasNext())
      {
        URI modelURI = null;
        String modelFile = (String)values.next();
        modelFile = modelFile.replace('\\', '/');
        if (modelFile.endsWith(".mdl"))
        {
          URI genModelFileURI = createFileURI(getOriginalGenModelPath().toString());
          modelURI = makeAbsolute(URI.createFileURI(modelFile), genModelFileURI);
          setModelLocation(modelURI.toString());
          
          while (values.hasNext())
          {
            String variable = (String)values.next();            
            String value = (String)values.next();
            getPathMap().put(variable, value);
          }
        }
      }
    }
    catch (Exception exception)
    {
      RoseImporterPlugin.INSTANCE.log(exception);
    }
  }

  protected RoseUtil createRoseUtil()
  {
    RoseUtil roseUtil = new RoseUtil();
    roseUtil.getRoseEcoreBuilder().noQualify = noQualify;
    roseUtil.getRoseEcoreBuilder().unsettablePrimitive = unsettablePrimitive;
    return roseUtil;
  }
}
