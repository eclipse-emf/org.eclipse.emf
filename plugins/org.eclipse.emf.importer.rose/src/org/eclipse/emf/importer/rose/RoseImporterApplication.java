/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.importer.rose;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ModelImporterApplication;


public class RoseImporterApplication extends ModelImporterApplication
{
  public static class PackageInfo
  {
    public String name;
    public String nsPrefix;
    public String nsURI;
    public String base;
    public String prefix;
  }

  protected boolean noQualify;
  protected boolean unsettablePrimitive;
  protected Map<String, String> pathMap;
  protected Map<String, PackageInfo> nameToPackageInfo;
  protected Map<String, PackageInfo> nameToReferencedPackageInfo;

  public RoseImporter getRoseImporter()
  {
    return (RoseImporter)getModelImporter();
  }

  @Override
  protected ModelImporter createModelImporter()
  {
    return new RoseImporter();
  }

  @Override
  protected StringBuffer getUsage()
  {
    StringBuffer result = new StringBuffer();
    appendLine(result, "Usage: <model.mdl> [ <model.genmodel> [ -reload ] ] <OPTION>");
    appendLine(result, "<OPTION>          ::= [ <PROJECT-OPTION> ]  [ <PATHMAP> ]");
    appendLine(result, "                      { <PACKAGE> }+  { <REF-PACKAGE> }* { <REF-GEN-MODEL> }*");
    appendLine(result, "                      [ <TEMPLATE-PATH> ] [ <MODEL-PLUGIN-ID> ] [ <COPYRIGHT> ]");
    appendLine(result, "                      [ <SDO> ] [ <QUIET> ]");
    appendLine(result, "<PROJECT-OPTION>  ::= <MODEL-PROJECT> [ <EDIT-PROJECT> ] [ <EDITOR-PROJECT> ]");
    appendLine(result, "                      [ <TESTS-PROJECT> ]");
    appendLine(result, "<MODEL-PROJECT>   ::= -modelProject <model-directory> <fragment-path>");
    appendLine(result, "<EDIT-PROJECT>    ::= -editProject <edit-directory> <fragment-path>");
    appendLine(result, "<EDITOR-PROJECT>  ::= -editorProject <editor-directory> <fragment-path>");
    appendLine(result, "<TESTS-PROJECT>   ::= -testsProject <tests-directory> <fragment-path>");
    appendLine(result, "<PATHMAP>         ::= -pathMap { <symbol> <directory> }+");
    appendLine(result, "<PACKAGE>         ::= -package <name> [ <nsPrefix> <nsURI> <base> <prefix> ]");
    appendLine(result, "<REF-PACKAGE>     ::= <deprecated - use -refGenModel instead> -refPackage <name> [ <nsPrefix> <nsURI> <base> <prefix> ]");
    appendLine(result, "<REF-GEN-MODEL>   ::= -refGenModel <model.genmodel> { <nsURI> }+");
    appendLine(result, "<TEMPLATE-PATH>   ::= -templatePath <template-directory>");
    appendLine(result, "<MODEL-PLUGIN-ID> ::= -modelPluginID <plugin-ID>");    
    appendLine(result, "<COPYRIGHT>       ::= -copyright <copyright-string>");
    appendLine(result, "<JDK-LEVEL>       ::= -jdkLevel <jdk level: 1.4 5.0 6.0>");
    appendLine(result, "<VALIDATE-MODEL>  ::= -validateModel <true|false>");
    appendLine(result, "<SDO>             ::= -sdo");
    appendLine(result, "<QUIET>           ::= -quiet");
    appendLine(result, "");
    appendLine(result, "For example:");
    appendLine(result, "");
    appendLine(result, "");
    appendLine(result, "  rose2genmodel");
    appendLine(result, "    ../../etools.company/src/rose/model.mdl");
    appendLine(result, "    result/model/Extended.genmodel");
    appendLine(result, "    -modelProject result src");
    appendLine(result, "    -editProject result.edit src");
    appendLine(result, "    -editorProject result.editor src");
    appendLine(result, "    -pathMap VABASE_PLUGINS_PATH C:/sandbox/unpackage2/eclipse/plugins"); 
    appendLine(result, "    -package extended Extended Extended.ecore org.example Extended");
    appendLine(result, "    -refPackage company Company Company.ecore org.sample Company");
    return result;
  }

  @Override
  protected int processArgument(String[] arguments, int index)
  {
    if (arguments[index].equalsIgnoreCase("-pathmap"))
    {
      if (pathMap == null)
      {
        pathMap = new HashMap<String, String>();
      }
      do
      {
        String variable = arguments[++index];
        String directory = arguments[++index];
        pathMap.put(variable, directory);
      }
      while (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"));
    }
    else if (arguments[index].equalsIgnoreCase("-package"))
    {
      if (nameToPackageInfo == null)
      {
        nameToPackageInfo = new HashMap<String, PackageInfo>();
      }
      index = processPackageInformation(arguments, index, nameToPackageInfo);
    }
    else if (arguments[index].equalsIgnoreCase("-refPackage"))
    {
      System.out.println("**** Instead of -refPackage you should be using -refGenModel");
      
      if (nameToReferencedPackageInfo == null)
      {
        nameToReferencedPackageInfo = new HashMap<String, PackageInfo>();
      }
      index = processPackageInformation(arguments, index, nameToReferencedPackageInfo);
    }
    else if (arguments[index].equalsIgnoreCase("-unsettablePrimitive"))
    {
      unsettablePrimitive = true;
    }
    else if (arguments[index].equalsIgnoreCase("-noQualify"))
    {
      noQualify = true;
    }
    else
    {
      return super.processArgument(arguments, index);
    }
    return index + 1;
  }

  protected int processPackageInformation(String[] arguments, int index, Map<String, PackageInfo> nameToPackageInfo)
  {
    int start = index;
    PackageInfo packageInfo = new PackageInfo();
    if (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"))
    {
      packageInfo.name = arguments[++index];
      if (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"))
      {
        packageInfo.nsPrefix = arguments[++index];
        if (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"))
        {
          packageInfo.nsURI = arguments[++index];
          if (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"))
          {
            packageInfo.base = arguments[++index];
            if (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"))
            {
              packageInfo.prefix = arguments[++index];
            }
          }
        }
      }
      if (index - start != 1 && index - start != 5)
      {
        throw new IllegalArgumentException("Error: Expecting either 1 or 5 arguments for " + arguments[start]);
      }
      else
      {
        nameToPackageInfo.put(packageInfo.name, packageInfo);
        nameToPackageInfo.put(packageInfo.name.toLowerCase(), packageInfo);
        return index;
      }
    }
    else
    {
      throw new IllegalArgumentException("Error: No package name was specified for " + arguments[start]);
    }
  }

  @Override
  protected void adjustModelImporter(Monitor monitor)
  {
    try
    {
      monitor.beginTask("", 2);

      super.adjustModelImporter(CodeGenUtil.createMonitor(monitor, 1));
      
      RoseImporter roseImporter = getRoseImporter();
      if (pathMap != null)
      {
        roseImporter.getPathMap().putAll(pathMap);
      }  
      roseImporter.setNoQualify(noQualify);
      roseImporter.setUnsettablePrimitive(unsettablePrimitive);
    }
    finally
    {
      monitor.done();
    }
  }

  @Override
  protected void adjustEPackages(Monitor monitor)
  {
    try
    {
      monitor.beginTask("", 2);
      super.adjustEPackages(CodeGenUtil.createMonitor(monitor, 1));
      
      List<EPackage> ePackages = getRoseImporter().getEPackages();
      traverseEPackages(ePackages);
      getRoseImporter().adjustEPackages(CodeGenUtil.createMonitor(monitor, 1));
    }
    finally
    {
      monitor.done();
    }
  }
  
  protected void traverseEPackages(List<EPackage> ePackages)
  {
    for (EPackage ePackage : ePackages)
    {
      String packageName = ePackage.getName();
      boolean isReferencedEPackage = false;
      PackageInfo packageInfo = nameToPackageInfo == null ? null : 
        (PackageInfo)nameToPackageInfo.get(packageName);
      if (packageInfo == null && nameToReferencedPackageInfo != null)
      {
        packageInfo = nameToReferencedPackageInfo.get(packageName);
        isReferencedEPackage = packageInfo != null;
      }
      
      if (packageInfo != null || nameToPackageInfo == null)
      {
        handleEPackage(ePackage, !isReferencedEPackage);
      }

      if (packageInfo != null)
      {
        if (!getRoseImporter().hasRoseGenPackageProperties(ePackage))
        {
          ePackage.setNsPrefix(packageInfo.nsPrefix);
          ePackage.setNsURI(packageInfo.nsURI);
        }
        
        ModelImporter.EPackageImportInfo ePackageInfo = getRoseImporter().getEPackageImportInfo(ePackage);        
        if (ePackageInfo.getBasePackage() == null)
        {
          ePackageInfo.setBasePackage(packageInfo.base);
        }
        if (ePackageInfo.getPrefix() == null)
        {
          ePackageInfo.setPrefix(packageInfo.prefix);
        }
      }
      
      handleQualifiedEPackageName(ePackage);
      traverseEPackages(ePackage.getESubpackages());
    }    
  }  
}
