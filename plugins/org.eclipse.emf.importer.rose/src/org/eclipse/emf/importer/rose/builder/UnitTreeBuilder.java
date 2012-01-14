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

import java.util.List;

import org.eclipse.emf.importer.rose.parser.RoseNode;
import org.eclipse.emf.importer.rose.parser.Util;


/**
 * Traverses the Rose files the first time to build unit tree and class information.
 *
 * Unit tree contains tree UnitTreeNodes.
 * Each tree node has name, quid and cat file name
 * 
 * Table information is a map from String quid to TableObjects.
 * Each table info has name, quid, xml file, uuid, model id and it parent model id.
 */
public class UnitTreeBuilder
{
  protected UnitTreeNode topNode;
  protected RoseUtil roseUtil;

  public UnitTreeBuilder(RoseUtil roseUtil)
  {
    this.roseUtil = roseUtil;
  }

  public void traverse(String qualifier, RoseNode tree, UnitTreeNode unitNode) throws Exception
  {
    topNode = unitNode;
    List<RoseNode> nodes = tree.getNodes();
    for (int i = 0; i < nodes.size(); i++)
    {
      RoseNode node = nodes.get(i);
      if (node.getRoseNodeType() == RoseNode.OBJECT)
      {
        traverseObject(qualifier, node, unitNode);
      }
      else if (node.getRoseNodeType() == RoseNode.LIST)
      {
        traverseList(qualifier, node, unitNode);
      }
    }
  }

  private void traverseObject(String qualifier, RoseNode tree, UnitTreeNode unitNode) throws Exception
  {
    String objKey = tree.getKey();
    String objType = Util.getType(tree.getValue());
    String objName = Util.getName(tree.getValue());

    if (objKey.equals(RoseStrings.ROOT_CATEGORY) && objType.equals(RoseStrings.CLASS_CATEGORY))
    {
      // For the model information.
      //
      String quid = unitNode.getQUID();
      if (quid.equals(""))
      {
        quid = tree.getRoseId();
        if (quid != null)
        {
          quid = quid.substring(1, quid.length() - 1);
        }
        else
        {
          quid = "";
        }
      }
      if (!quid.equals(""))
      {
        unitNode.setQUID(quid);
      }
      unitNode.setRoseNode(tree);
      traverse(qualifier, tree, unitNode);
    }
    else if (objKey.equals("") && objType.equals(RoseStrings.CLASS_CATEGORY))
    {
      // This is package, 
      // so check the package information if this is in cat file.
      // If it is, then load the .cat file into the tree thereby traversing it;
      // otherwise, just traverse the tree.
      //
      RoseNode loadingNode = tree.findNodeWithKey(RoseStrings.IS_LOADED);

      String quid = tree.getRoseId();
      if (quid != null)
      {
        quid = quid.substring(1, quid.length() - 1);
      }
      if (loadingNode != null)
      {
        // The package is in a .cat file.
        //
        RoseNode fileNameNode = tree.findNodeWithKey(RoseStrings.FILE_NAME);
        if (fileNameNode != null)
        {
          String fileNameNodeValue = fileNameNode.getValue();
          String fileName = roseUtil.resolveFileName(fileNameNodeValue);
          UnitTreeNode unitTreeNode = new UnitTreeNode(objName, quid, fileName);
          unitNode.addNode(unitTreeNode);
          roseUtil.createRoseUnitTreeAndTable(fileNameNodeValue, unitTreeNode);
        }
      }
      else
      {
        // The package not in a .cat file.
        //
        String qualifiedName = objName;
        if (qualifier != null)
        {
          qualifiedName = qualifier + "." + objName;
        }
        TableObject obj = new TableObject(qualifiedName, quid, topNode);
        roseUtil.quidTable.put(quid, obj);
        if (qualifier != null)
        {
          roseUtil.nameTable.put(qualifiedName, obj);
        }
        roseUtil.nameTable.put(objName, obj);
        unitNode.setRoseNode(tree);
        traverse(qualifiedName, tree, unitNode);
      }
    }
    else if (objType.equals(RoseStrings.CLASS))
    {
      String quid = tree.getRoseId();
      if (quid != null)
      {
        quid = quid.substring(1, quid.length() - 1);
      }
      String qualifiedName = objName;
      if (qualifier != null)
      {
        qualifiedName = qualifier + "." + objName;
      }
      TableObject obj = new TableObject(qualifiedName, quid, topNode);
      roseUtil.quidTable.put(quid, obj);
      roseUtil.nameTable.put(objName, obj);
      if (qualifiedName != null)
      {
        roseUtil.nameTable.put(qualifiedName, obj);
      }
      traverse(qualifiedName, tree, unitNode);
    }
    else if (objType.equals(RoseStrings.OPERATION) || objType.equals(RoseStrings.CLASSATTRIBUTE)
      || objType.equals(RoseStrings.INHERITANCE_RELATIONSHIP) || objType.equals(RoseStrings.ASSOCIATION)
      || objType.equals(RoseStrings.ROLE) || objType.equals(RoseStrings.VISIBILITY_RELATIONSHIP)
      || objType.equals(RoseStrings.USES_RELATIONSHIP))
    {
      traverse(qualifier, tree, unitNode);
    }
  }

  private void traverseList(String qualifier, RoseNode tree, UnitTreeNode unitNode) throws Exception
  {
    traverse(qualifier, tree, unitNode);
  }
}
