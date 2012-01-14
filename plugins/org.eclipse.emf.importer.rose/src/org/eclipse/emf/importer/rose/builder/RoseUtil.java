/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.importer.rose.builder;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.importer.rose.RoseImporterPlugin;
import org.eclipse.emf.importer.rose.parser.RoseLexer;
import org.eclipse.emf.importer.rose.parser.RoseLoader;
import org.eclipse.emf.importer.rose.parser.RoseNode;
import org.eclipse.emf.importer.rose.parser.RoseParser;
import org.eclipse.emf.importer.rose.parser.Util;


/**
 * Provide functions to process a rose file.
 */
public class RoseUtil
{
  protected Map<String, Object> quidTable = new HashMap<String, Object>();
  protected Map<String, Object> nameTable = new HashMap<String, Object>();
  protected Map<Object, List<String>> superTable = new HashMap<Object, List<String>>();
  protected Map<EReference, String> refTable = new HashMap<EReference, String>();
  protected Map<EObject, String> typeTable = new LinkedHashMap<EObject, String>();
  protected Map<String, String> variableToDirectoryMap = new HashMap<String, String>();
  protected Map<String, String> packageNameToNSNameMap = new HashMap<String, String>();
  protected Map<String, String> packageNameToNSURIMap = new HashMap<String, String>();
  protected Map<EPackage, List<String>> ePackageToInformationMap = new HashMap<EPackage, List<String>>();
  protected URIConverter uriConverter;
  
  public RoseUtil(URIConverter uriConverter)
  {
    this.uriConverter = uriConverter;
  }

  BasicDiagnostic basicDiagnostic = new BasicDiagnostic(
    RoseImporterPlugin.getPlugin().getBundle().getSymbolicName(),
    0,
    RoseImporterPlugin.INSTANCE.getString("_UI_ProblemsWereEncounteredLoadingTheRoseModel_message"),
    null);

  protected RoseEcoreBuilder roseEcoreBuilder = new RoseEcoreBuilder(this);

  public RoseEcoreBuilder getRoseEcoreBuilder()
  {
    return roseEcoreBuilder;
  }

  public UnitTreeNode createRoseUnitTreeAndTable(String fileNameNodeValue, UnitTreeNode topNode) throws Exception
  {
    String fileName = resolveFileName(fileNameNodeValue);

    // Store the base name for relative .cat file references.
    //
    if (topNode == null)
    {
      int index = fileName.lastIndexOf(File.separator);
      if (index != -1)
      {
        variableToDirectoryMap.put(null, fileName.substring(0, index + 1));
      }
    }

    // read mdl file...
    RoseLoader loader = new RoseLoader(fileName, uriConverter);
    try
    {
      if (loader.isValid())
      {
        basicDiagnostic = new BasicDiagnostic(
          RoseImporterPlugin.getPlugin().getBundle().getSymbolicName(),
          1,
          RoseImporterPlugin.INSTANCE.getString("_UI_ProblemsWereEncounteredConvertingTheRoseModel_message"),
          null);

        RoseLexer lexer = new RoseLexer(loader);
        RoseParser parser = new RoseParser(lexer, true, true);
        parser.parse();
        RoseNode modelTree = parser.getModelTree();
        UnitTreeBuilder unitTreeBuilder = new UnitTreeBuilder(this);
        if (topNode == null)
        {
          String qualifier;
          // special case, traverse cat file or mdl file
          //
          UnitTreeNode unitTree = null;
          if (modelTree.getKey().equals("") && (Util.getType(modelTree.getValue())).equals(RoseStrings.CLASS_CATEGORY))
          {
            // this is the case that starting process rose file by passing cat file name
            // this is a special case.
            // normally, user should pass mdl file instead of cat file
            // 
            // file is a cat file
            // modelTree did contain quid info
            String quid = modelTree.getRoseId();
            quid = quid.substring(1, quid.length() - 1);
            unitTree = new UnitTreeNode(Util.getName(modelTree.getValue()), quid, fileName);
            String objName = Util.getName(modelTree.getValue());
            TableObject obj = new TableObject(objName, quid, unitTree);
            quidTable.put(quid, obj);
            nameTable.put(objName, obj);
            qualifier = objName;
          }
          else
          {
            // file is a mdl file
            //
            // get model name
            int ind_1 = fileName.lastIndexOf(System.getProperty("file.separator"));
            int ind_2 = fileName.lastIndexOf(".");
            String modelName;
            if (ind_2 != -1)
              modelName = fileName.substring(ind_1 + 1, ind_2);
            else
              modelName = fileName.substring(ind_1 + 1, fileName.length());
            //if (!modelName.toLowerCase().endsWith("model"))
            //    modelName = modelName + "model";
            String quid = modelTree.getRoseId();
            if (quid != null)
            {
              quid = quid.substring(1, quid.length() - 1);
              unitTree = new UnitTreeNode(modelName, quid, fileName);
            }
            else
            {
              unitTree = new UnitTreeNode(modelName, "", fileName);
            }
            qualifier = null;
          }
          // starting traverse file and build unit tree and table info
          unitTreeBuilder.traverse(qualifier, modelTree, unitTree);
          return unitTree;
        }
        else
        {
          // cat(unit) file referenced by mdl file
          String quid = modelTree.getRoseId();
          if (quid != null)
          {
            quid = quid.substring(1, quid.length() - 1);
            topNode.setQUID(quid);
          }
          String objName = Util.getName(modelTree.getValue());
          TableObject obj = new TableObject(objName, quid, topNode);
          quidTable.put(quid, obj);
          nameTable.put(objName, obj);
          unitTreeBuilder.traverse(objName, modelTree, topNode);
          return null;
        }
      }
      else
      {
        basicDiagnostic.add(
          new BasicDiagnostic(Diagnostic.INFO, RoseImporterPlugin.getPlugin().getBundle().getSymbolicName(), 0, 
            RoseImporterPlugin.INSTANCE.getString("_UI_TheUnitResolvesTo_message",
            new Object []{ Util.trimQuotes(fileNameNodeValue), fileName }), null));
        return null;
      }
    }
    finally
    {
      loader.close();
    }
  }

  public void showRoseUnitTree(UnitTreeNode unitTree)
  {
    if (unitTree != null)
    {
      System.out.println(" ");
      System.out.println("======= Unit Tree Info =============");
      System.out.println("[0]: " + unitTree.getName() + ",   " + unitTree.getQUID() + ", " + unitTree.getRoseFileName() + ",	"
        + unitTree.getEcoreFileName());
      int i = 1;
      traverseOut(unitTree, i);
    }

    if (quidTable.size() > 0)
    {
      System.out.println("=========== Class Info ============");
      Iterator<String> it = quidTable.keySet().iterator();
      while (it.hasNext())
      {
        Object key = it.next();
        TableObject obj = (TableObject)quidTable.get(key);
        System.out.println(key + ",\t" + obj.getName() + ",\t" + obj.getContainer().getEcoreFileName());
      }
    }
  }

  protected void traverseOut(UnitTreeNode tree, int index)
  {
    List<UnitTreeNode> nodes = tree.getNodes();
    if (nodes.size() > 0)
    {
      for (int i = 0; i < nodes.size(); i++)
      {
        UnitTreeNode node = nodes.get(i);
        System.out.println("[" + index + "]: " + node.getName() + ",  " + node.getQUID() + ", " + node.getEcoreFileName());
        traverseOut(node, index + 1);
      }
    }
  }

  public void createExtent4RoseUnitTree(UnitTreeNode unitTree)
  {
    if (unitTree != null)
    {
      checkConflictFileName(unitTree);
      createExtent(unitTree);
    }
    refTable.clear();
  }

  public void checkConflictFileName(UnitTreeNode unitTree)
  {
    String rootEcoreFileName = unitTree.getEcoreFileName();
    if (checkFileName(unitTree, rootEcoreFileName))
    {
      int index = rootEcoreFileName.lastIndexOf(".");
      if (index != -1)
      {
        rootEcoreFileName = rootEcoreFileName.substring(0, index) + "model"
          + rootEcoreFileName.substring(index, rootEcoreFileName.length());
      }
      unitTree.setEcoreFileName(rootEcoreFileName);
    }
  }

  public boolean checkFileName(UnitTreeNode unitTree, String name)
  {
    List<UnitTreeNode> nodes = unitTree.getNodes();
    for (int i = 0; i < nodes.size(); i++)
    {
      UnitTreeNode node = nodes.get(i);
      if (node.getEcoreFileName().equals(name) || checkFileName(node, name))
      {
        return true;
      }
    }

    return false;
  }

  public void createExtent(UnitTreeNode unitTree)
  {
    EList<EObject> ext = new BasicEList<EObject>();
    unitTree.setExtent(ext);
    List<UnitTreeNode> nodes = unitTree.getNodes();
    for (int i = 0; i < nodes.size(); i++)
    {
      createExtent(nodes.get(i));
    }
  }

  public void processUnitTree(UnitTreeNode unitTree) throws Exception
  {
    if (unitTree != null)
    {
      loadTree(null, unitTree);
      String packageName = unitTree.getEcoreFileName();
      int fileSeparatorIndex = packageName.lastIndexOf(File.separator);
      if (fileSeparatorIndex != -1)
      {
        packageName = packageName.substring(fileSeparatorIndex + 1);
      }
      int dotIndex = packageName.lastIndexOf(".");
      if (dotIndex != -1)
      {
        packageName = packageName.substring(0, dotIndex);
      }
      roseEcoreBuilder.createEPackageForRootClasses(unitTree.getExtent(), unitTree.getRoseNode(), packageName);
      roseEcoreBuilder.setEEnums();
      roseEcoreBuilder.setEReferences();
      roseEcoreBuilder.setSuper();
      roseEcoreBuilder.setETypeClassifier();
      setIDs(unitTree);
      validate(unitTree);
    }
  }

  protected void setIDs(UnitTreeNode node) throws Exception
  {
    for (EObject eObject : node.getExtent())
    {
      roseEcoreBuilder.setIDs(null, eObject);
    }

    // Process the children of the UnitTreeNode recursively.
    //
    for (UnitTreeNode subNode : node.getNodes())
    {
      setIDs(subNode);
    }
  }

  protected void validate(UnitTreeNode node) throws Exception
  {
    // Process the contents of the extent
    //
    for (EObject eObject : node.getExtent())
    {
      roseEcoreBuilder.validate(eObject);
    }

    // Process the children of the UnitTreeNode recursively.
    //
    for (UnitTreeNode subNode : node.getNodes())
    {
      validate(subNode);
    }
  }

  protected void loadTree(RoseNode containingNode, UnitTreeNode node) throws Exception
  {
    // Load the Rose .mdl or .cat file, and create mappings for the objects.
    //
    String roseFile = node.getRoseFileName();
    RoseLoader loader = new RoseLoader(roseFile, uriConverter);
    try
    {
      if (loader.isValid())
      {
        RoseLexer lexer = new RoseLexer(loader);
        RoseParser parser = new RoseParser(lexer, true, true);
        parser.parse();
        RoseNode modelTree = parser.getModelTree();
        modelTree.setNode(node.getExtent());

        // This sets the parent so that the nodes can traverse to the root to find default eCore settings.
        //
        if (containingNode != null)
        {
          modelTree.setParent(containingNode);
        }

        containingNode = modelTree;

        // Start second traverse to create mapping objects in memory.
        //
        RoseWalker roseWalker = new RoseWalker(modelTree);
        roseWalker.traverse(roseEcoreBuilder);
      }
    }
    finally
    {
      loader.close();
    }

    // Process the children of the UnitTreeNode recursively.
    //
    for (UnitTreeNode subNode : node.getNodes())
    {
      loadTree(containingNode, subNode);
    }
  }

  public void saveEcoreFiles(ResourceSet resourceSet) throws Exception
  {
    for (Resource resource : resourceSet.getResources())
    {
      resource.save(Collections.EMPTY_MAP);
    }
  }

  public void createResource(UnitTreeNode tree, ResourceSet resourceSet)
  {
    EList<EObject> ext = tree.getExtent();
    if (ext.size() > 0)
    {
      String ecoreFileName = tree.getEcoreFileName();
      URI ecoreURI = URI.createURI(ecoreFileName);
      Resource res = Resource.Factory.Registry.INSTANCE.getFactory(ecoreURI).createResource(ecoreURI);
      res.getContents().addAll(tree.getExtent());
      resourceSet.getResources().add(res);
    }
    List<UnitTreeNode> nodes = tree.getNodes();
    for (int i = 0; i < nodes.size(); i++)
    {
      createResource(nodes.get(i), resourceSet);
    }
  }

  public String resolveFileName(String name)
  {
    name = Util.trimQuotes(name);
    name = Util.updateFileName(name, "\\\\");
    name = Util.updateFileName(name, "\\");
    name = Util.updateFileName(name, "/");

    String result = "";
    int index;
    while ((index = name.indexOf(File.separator)) != -1)
    {
      String directoryName = name.substring(0, index);
      if (directoryName.startsWith("$")) //directoryName.length() > 0 && directoryName.charAt(0) == '$') 
      {
        String variableName = directoryName.substring(1);
        directoryName = variableToDirectoryMap.get(variableName);
        if (directoryName == null)
        {
          variableToDirectoryMap.put(variableName, null);
          directoryName = "";
        }
      }
      result += directoryName + File.separator;
      name = name.substring(index + 1);
    }
    result += name;
    if (result.indexOf(":") == -1 && !result.startsWith(File.separator))
    {
      String baseName = variableToDirectoryMap.get(null);
      if (baseName != null)
      {
        result = baseName + result;
      }
    }
    return result;
  }

  public Map<String, String> getVariableToDirectoryMap()
  {
    return variableToDirectoryMap;
  }

  public Map<String, String> getPackageNameToNSNameMap()
  {
    return packageNameToNSNameMap;
  }

  public Map<String, String> getPackageNameToNSURIMap()
  {
    return packageNameToNSURIMap;
  }

  public Map<EPackage, List<String>> getEPackageToInformationMap()
  {
    return ePackageToInformationMap;
  }

  public Diagnostic getDiagnostic()
  {
    return basicDiagnostic;
  }

  public void addDiagnostic(Diagnostic diagnostic)
  {
    basicDiagnostic.add(diagnostic);
  }
}
