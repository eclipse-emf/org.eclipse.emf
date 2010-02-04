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
 * $Id: RoseImporter.java,v 1.15 2010/02/04 20:56:24 emerks Exp $
 */
package org.eclipse.emf.importer.rose;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.rose.builder.RoseUtil;
import org.eclipse.emf.importer.rose.builder.UnitTreeNode;
import org.eclipse.emf.converter.ConverterPlugin;
import org.eclipse.emf.converter.util.ConverterUtil;


/**
 * @since 2.1.0
 */
public class RoseImporter extends ModelImporter
{
  protected Map<String, String> pathMap;
  protected boolean noQualify = false;
  protected boolean unsettablePrimitive = false;
  
  protected RoseUtil roseUtil;
  protected UnitTreeNode unitTreeNode;
  
  protected Map<EPackage, List<String>> roseEPackageInformationMap;

  @Override
  public void dispose()
  {
    if (pathMap != null)
    {
      pathMap.clear();
      pathMap = null;
    }
    if (roseEPackageInformationMap != null)
    {
      roseEPackageInformationMap.clear();
      roseEPackageInformationMap = null;
    }

    super.dispose();
  }

  @Override
  public String getID()
  {
    return "org.eclipse.emf.importer.rose";
  }

  public Map<String, String> getPathMap()
  {
    if (pathMap == null)
    {
      pathMap = new HashMap<String, String>();
    }
    return pathMap;
  }
  
  @Override
  public void setModelLocation(String location)
  {
    boolean isEqual = location == null ? 
      getModelLocation() == null
      : location.equals(getModelLocation());
    if (!isEqual)
    {
      getPathMap().clear();
    }
    super.setModelLocation(location);
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
  
  @Override
  public void clearEPackagesCollections()
  {
    super.clearEPackagesCollections();
    roseEPackageInformationMap = null;
  }
  
  public Diagnostic loadPathMap(Monitor monitor) throws Exception
  {
    clearEPackagesCollections();
   
    Diagnostic diagnostic = null;
    
    File roseModelFile = computeRoseModelFile();
    if (roseModelFile == null)
    {
      diagnostic = new BasicDiagnostic(Diagnostic.ERROR, 
        ConverterPlugin.ID, ConverterUtil.ACTION_DIALOG_NONE | ConverterUtil.ACTION_MESSAGE_SET_ERROR,
        RoseImporterPlugin.INSTANCE.getString("_UI_SpecifyAValidRoseModel_message"), null);
    }
    else
    {
      String roseModelAbsolutePath = roseModelFile.getAbsolutePath();

      monitor.beginTask("", 2);
      monitor.subTask(RoseImporterPlugin.INSTANCE.getString("_UI_Loading_message", new Object []{ roseModelAbsolutePath }));

      Map<String, String> pathMap = getPathMap();
      for (;;)
      {
        roseUtil = createRoseUtil();
        roseUtil.getRoseEcoreBuilder().noQualify = noQualify;
        roseUtil.getRoseEcoreBuilder().unsettablePrimitive = unsettablePrimitive;

        roseUtil.getVariableToDirectoryMap().putAll(pathMap);
        unitTreeNode = roseUtil.createRoseUnitTreeAndTable(roseModelAbsolutePath, null);
        if (unitTreeNode == null)
        {
          diagnostic = new BasicDiagnostic(Diagnostic.ERROR, 
            ConverterPlugin.ID, ConverterUtil.ACTION_DIALOG_NONE | ConverterUtil.ACTION_MESSAGE_SET_ERROR,
            RoseImporterPlugin.INSTANCE.getString("_UI_SpecifyAValidRoseModel_message"), null);
        }
        else
        {
          boolean hasSymbolWithoutValue = adjustPathMap(roseUtil);
          if (adjustKnownPathMapSymbols(roseModelAbsolutePath))
          {
            continue;
          }
          if (!hasSymbolWithoutValue)
          {
            diagnostic = ConverterUtil.mergeDiagnostic(diagnostic, roseUtil.getDiagnostic());
          }
          
          if (getGenModelFileName() == null)
          {
            String fileName = unitTreeNode.getNodes().size() != 1
              ? roseModelFile.getName() : unitTreeNode.getNodes().get(0).getRoseFileName();

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
        }
        break;
      }
    }

    return diagnostic != null ? diagnostic : Diagnostic.OK_INSTANCE;
  }
  
  protected boolean adjustPathMap(RoseUtil roseUtil)
  {
    boolean hasSymbolWithoutValue = false;
    Map<String, String> currentPathMap = getPathMap();
    Map<String, String> pathMap = new HashMap<String, String>();
    for (Map.Entry<String, String> entry : roseUtil.getVariableToDirectoryMap().entrySet())
    {
      String symbol = entry.getKey();
      if (symbol != null)
      {
        String value = entry.getValue();
        if (value == null)
        {
          value = currentPathMap.get(symbol);
        }
        pathMap.put(symbol, value);
        hasSymbolWithoutValue = hasSymbolWithoutValue || (value == null);
      }
    }
    currentPathMap.clear();
    currentPathMap.putAll(pathMap);
    return hasSymbolWithoutValue;
  }
  
  protected boolean adjustKnownPathMapSymbols(String roseModelAbsolutePath)
  {
    return false;
  }
  
  /**
   * Returns whether the Rose model properties includes genmodel information about a given ePackage.  
   * This method should only be used after invoking 
   * {@link ModelImporter#computeEPackages(Monitor)}.
   * @param ePackage
   * @return boolean
   */
  public boolean hasRoseGenPackageProperties(EPackage ePackage)
  {
    return roseEPackageInformationMap != null && roseEPackageInformationMap.get(ePackage) != null;
  }

  @Override
  protected Diagnostic doComputeEPackages(Monitor monitor) throws Exception
  {
    monitor.beginTask("", 2);
    Diagnostic diagnostic = loadPathMap(monitor);
    if (diagnostic.getSeverity() == Diagnostic.OK)
    {
      if (getPathMap().values().contains(null) && roseUtil.getDiagnostic().getSeverity() != Diagnostic.OK)
      {
        diagnostic = new BasicDiagnostic(Diagnostic.ERROR, 
          ConverterPlugin.ID, ConverterUtil.ACTION_DIALOG_NONE | ConverterUtil.ACTION_MESSAGE_SET_ERROR,
          RoseImporterPlugin.INSTANCE.getString("_UI_SpecifyTheSymbolLocations_message"), null);        
      }
      else
      {
        roseUtil.createExtent4RoseUnitTree(unitTreeNode);
        roseUtil.processUnitTree(unitTreeNode);
        roseEPackageInformationMap = roseUtil.getEPackageToInformationMap();
        diagnostic = ConverterUtil.createDiagnostic(roseUtil.getDiagnostic(), ConverterPlugin.ID, ConverterUtil.ACTION_MESSAGE_NONE);
        
        for (Map.Entry<EPackage, List<String>> entry : roseUtil.getEPackageToInformationMap().entrySet())
        {
          List<String> information = entry.getValue();
          if (information != null)
          {
            EPackageImportInfo ePackageInfo = getEPackageImportInfo(entry.getKey());
            ePackageInfo.setBasePackage(information.get(0));
            ePackageInfo.setPrefix(information.get(1));
          }
        }
  
        traverseEPackages(unitTreeNode);
      }
    }
    monitor.done();
    return diagnostic;
  }

  protected void traverseEPackages(UnitTreeNode subNode)
  {
    @SuppressWarnings("unchecked")
    List<EPackage> ePackagesList = (List<EPackage>)(EList<?>)subNode.getExtent();
    
    getEPackages().addAll(ePackagesList);
    for (EPackage ePackage : ePackagesList)
    {
      EPackageImportInfo ePackageInfo = getEPackageImportInfo(ePackage);

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
      ePackageInfo.setEcoreFileName(ecoreFileName);
    }

    for (UnitTreeNode childTree : subNode.getNodes())
    {
      traverseEPackages(childTree);
    }
  }
  
  @Override
  protected void adjustGenModel(Monitor monitor)
  {
    super.adjustGenModel(monitor);

    GenModel genModel = getGenModel();
    String projectName = getModelProjectName();
    IPath genModelPath = getGenModelPath();
    URI modelURI = getModelLocationURIs().get(0);
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

    for (Map.Entry<String, String> entry : getPathMap().entrySet())
    {
      String value = entry.getValue();
      if (value != null)
      {
        genModel.getForeignModel().add(entry.getKey());
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
  }

  @Override
  protected void handleOriginalGenModel() throws DiagnosticException
  {
    try
    {
      Iterator<String> values = getOriginalGenModel().getForeignModel().iterator();
      if (values.hasNext())
      {
        URI modelURI = null;
        String modelFile = values.next();
        modelFile = modelFile.replace('\\', '/');
        if (modelFile.endsWith(".mdl"))
        {
          URI genModelFileURI = createFileURI(getOriginalGenModelPath().toString());
          modelURI = makeAbsolute(URI.createFileURI(modelFile), genModelFileURI);
          setModelLocation(modelURI.toString());
          
          while (values.hasNext())
          {
            String variable = values.next();            
            String value = values.next();
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
    RoseUtil roseUtil = new RoseUtil(createResourceSet().getURIConverter());
    roseUtil.getRoseEcoreBuilder().noQualify = noQualify;
    roseUtil.getRoseEcoreBuilder().unsettablePrimitive = unsettablePrimitive;
    return roseUtil;
  }
}