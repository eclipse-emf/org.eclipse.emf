/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: Rose2Ecore.java,v 1.2 2004/05/16 17:27:14 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.rose2ecore;


import org.eclipse.core.runtime.IPlatformRunnable;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;


/**
 * Converts a Rose model to an Ecore model.
 * <p> 
 * This will load a Rose .mdl file with Rose .cat files and generate multiple .ecore files.
 * Each Rose .mdl and Rose .cat file ---> one resource/extent ---> one ecore xmi file
 * 
 * <pre>
 * Mapping rules:
 *
 * Package          -----------> ePackage
 * Class            -----------> eClass/eNum/eInterface
 * Attribute        -----------> eAttribute
 * Operation        -----------> eOperation
 * Association (no mapping)
 * AssociationEnd   -----------> eReference
 * Inheritance      -----------> super/
 * Parameter        ----------->
 * Dependency (implement later)	
 * Realization (implement later)
 * </pre>
 * </p>
 */
public class Rose2Ecore implements IPlatformRunnable
{
  public static void main (String argv[]) 
  {
    Rose2Ecore rose2Ecore = new Rose2Ecore();
    rose2Ecore.run(argv);
  }

  protected RoseUtil roseUtil;
  protected UnitTreeNode unitTree;
  protected ResourceSet resourceSet;

  public Rose2Ecore()
  {
  }

  public Object run(Object object)
  {
    String[] arguments = (String[])object;

    roseUtil = new RoseUtil();
    unitTree = null;
    resourceSet = null;

    if (arguments.length == 0)
    {
      System.out.println("Usage: <model-file> { -package <name> <nsName> <nsURI> }+ -pathMap { <variable> <directory> }+");
      return new Integer(0);
    }
    else
    {
      for (int i = 0; i < arguments.length; ++i)
      {
        if (arguments[i].equalsIgnoreCase("-package"))
        {
          String packageName = arguments[++i];
          String nsName = arguments[++i];
          String nsURI = arguments[++i];
          roseUtil.packageNameToNSNameMap.put(packageName, nsName);
          roseUtil.packageNameToNSURIMap.put(packageName, nsURI);
        }
        else if (arguments[i].equalsIgnoreCase("-pathMap"))
        {
          do
          {
            String variable = arguments[++i];
            String directory = arguments[++i];
            roseUtil.variableToDirectoryMap.put(variable, directory);
          }
          while (i + 1 < arguments.length && !arguments[i + 1].startsWith("-"));
        }
      }
      convert(arguments[0]);
      return new Integer(0);
    }
  }

  protected void convert(String fileName)
  {
    try 
    {
      unitTree = roseUtil.createRoseUnitTreeAndTable(fileName, null);
      roseUtil.createExtent4RoseUnitTree(unitTree);
      // roseUtil.showRoseUnitTree(unitTree);
      roseUtil.processUnitTree(unitTree);
      resourceSet = new ResourceSetImpl();
      roseUtil.createResource(unitTree, resourceSet);
      roseUtil.saveEcoreFiles(resourceSet);
    } 
    catch (Exception e) 
    {
      e.printStackTrace();
      CodeGenEcorePlugin.INSTANCE.log(e);
    }
  }

  public ResourceSet getResourceSet()
  {
    return resourceSet;
  }

  protected UnitTreeNode getUnitTree()
  {
    return unitTree;
  }
}
