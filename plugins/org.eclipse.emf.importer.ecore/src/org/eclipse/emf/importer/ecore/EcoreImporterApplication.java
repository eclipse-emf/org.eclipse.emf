/**
 * Copyright (c) 2006-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.importer.ecore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ModelImporterApplication;


public class EcoreImporterApplication extends ModelImporterApplication
{
  public static class PackageInfo
  {
    public String nsURI;
    public String base;
    public String prefix;
  }

  protected Map<String, PackageInfo> nameToPackageInfo;

  public EcoreImporter getEcoreImporter()
  {
    return (EcoreImporter)getModelImporter();
  }

  @Override
  protected ModelImporter createModelImporter()
  {
    return new EcoreImporter();
  }

  @Override
  protected StringBuffer getUsage()
  {
    StringBuffer result = new StringBuffer();
    appendLine(result, "Usage: { <model.ecore> }+ [ <model.genmodel> [ -reload ] ] <OPTION>");
    appendLine(result, "<OPTION>            ::= [ <PROJECT-OPTION> ]  [ <PATHMAP> ]");
    appendLine(result, "                        { <PACKAGE> }+  { <REF-PACKAGE> }* { <REF-GEN-MODEL> }*");
    appendLine(result, "                        [ <TEMPLATE-PATH> ] [ <MODEL-PLUGIN-ID> ] [ <COPYRIGHT> ]");
    appendLine(result, "                        [ <SDO> ] [ <QUIET> ]");
    appendLine(result, "<PROJECT-OPTION>    ::= <MODEL-PROJECT> [ <EDIT-PROJECT> ] [ <EDITOR-PROJECT> ]");
    appendLine(result, "                        [ <TESTS-PROJECT> ]");
    appendLine(result, "<MODEL-PROJECT>     ::= -modelProject <model-directory> <fragment-path>");
    appendLine(result, "<EDIT-PROJECT>      ::= -editProject <edit-directory> <fragment-path>");
    appendLine(result, "<EDITOR-PROJECT>    ::= -editorProject <editor-directory> <fragment-path>");
    appendLine(result, "<TESTS-PROJECT>     ::= -testsProject <tests-directory> <fragment-path>");
    appendLine(result, "<PACKAGE>           ::= -package <nsURI> [ <base> <prefix> ]");
    appendLine(result, "<REF-GEN-MODEL>     ::= -refGenModel <model.genmodel> { <nsURI> }+");
    appendLine(result, "<TEMPLATE-PATH>     ::= -templatePath <template-directory>");
    appendLine(result, "<MODEL-PLUGIN-ID>   ::= -modelPluginID <plugin-ID>");    
    appendLine(result, "<COPYRIGHT>         ::= -copyright <copyright-string>");
    appendLine(result, "<JDK-LEVEL>         ::= -jdkLevel <jdk level: 1.4 5.0 6.0>");    
    appendLine(result, "<IMPORT-ORGANIZING> ::= -importOrganizing");
    appendLine(result, "<VALIDATE-MODEL>    ::= -validateModel <true|false>");
    appendLine(result, "<SDO>               ::= -sdo");
    appendLine(result, "<QUIET>             ::= -quiet");
    appendLine(result, "");
    appendLine(result, "For example:");
    appendLine(result, "");
    appendLine(result, "");
    appendLine(result, "  ecore2genmodel");
    appendLine(result, "    ../../etools.company/model.ecore");
    appendLine(result, "    result/model/Extended.genmodel");
    appendLine(result, "    -modelProject result src");
    appendLine(result, "    -editProject result.edit src");
    appendLine(result, "    -editorProject result.editor src");
    appendLine(result, "    -refGenModel company.genmodel http://org.sample.company");
    return result;
  }
  
  @Override
  protected int processArgument(String[] arguments, int index)
  {
    if (arguments[index].equalsIgnoreCase("-package"))
    {
      if (nameToPackageInfo == null)
      {
        nameToPackageInfo = new HashMap<String, PackageInfo>();
      }
      index = processPackageInformation(arguments, index, nameToPackageInfo);
    }
    else
    {
      return super.processArgument(arguments, index);
    }
    return index + 1;
  }

  protected int processPackageInformation(String[] arguments, int index, Map<String, PackageInfo> nsURIToPackageInfo)
  {
    int start = index;
    PackageInfo packageInfo = new PackageInfo();
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
      if (index - start != 1 && index - start != 3)
      {
        throw new IllegalArgumentException("Error: Expecting either 1 or 3 arguments for " + arguments[start]);
      }
      else
      {
        nsURIToPackageInfo.put(packageInfo.nsURI, packageInfo);
        nsURIToPackageInfo.put(packageInfo.nsURI.toLowerCase(), packageInfo);
        return index;
      }
    }
    else
    {
      throw new IllegalArgumentException("Error: No package name was specified for " + arguments[start]);
    }
  }

  @Override
  protected void adjustEPackages(Monitor monitor)
  {
    try
    {
      monitor.beginTask("", 2);
      super.adjustEPackages(CodeGenUtil.createMonitor(monitor, 1));
      
      List<EPackage> ePackages = getEcoreImporter().getEPackages();
      traverseEPackages(ePackages);
      getEcoreImporter().adjustEPackages(CodeGenUtil.createMonitor(monitor, 1));
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
      if (nameToPackageInfo != null)
      {
        PackageInfo packageInfo = nameToPackageInfo.get(ePackage.getNsURI());
        if (packageInfo != null)
        {
          handleEPackage(ePackage, true);
          
          ModelImporter.EPackageImportInfo ePackageInfo = getEcoreImporter().getEPackageImportInfo(ePackage);        
          if (ePackageInfo.getBasePackage() == null)
          {
            ePackageInfo.setBasePackage(packageInfo.base);
          }
          if (ePackageInfo.getPrefix() == null)
          {
            ePackageInfo.setPrefix(packageInfo.prefix);
          }
        }
      }
      
      handleQualifiedEPackageName(ePackage);
      traverseEPackages(ePackage.getESubpackages());
    }    
  }  
}
