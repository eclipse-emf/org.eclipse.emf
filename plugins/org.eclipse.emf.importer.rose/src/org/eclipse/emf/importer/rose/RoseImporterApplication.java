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
 * $Id: RoseImporterApplication.java,v 1.6 2005/05/17 05:39:10 marcelop Exp $
 */
package org.eclipse.emf.importer.rose;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;

import org.eclipse.emf.common.util.URI;
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

  protected IPath roseModelFullPath;
  protected IPath genModelFullPath;

  protected boolean noQualify;
  protected boolean unsettablePrimitive;
  protected Map pathMap;
  protected Map nameToPackageInfo;
  protected Map nameToReferencedPackageInfo;

  public RoseImporter getRoseImporter()
  {
    return (RoseImporter)getModelImporter();
  }

  protected ModelImporter createModelImporter()
  {
    return new RoseImporter();
  }

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
    appendLine(result, "<REF-PACKAGE>     ::= -refPackage <name> [ <nsPrefix> <nsURI> <base> <prefix> ]");
    appendLine(result, "<REF-GEN-MODEL>   ::= -refGenModel <model.genmodel> { <nsURI> }+");
    appendLine(result, "<TEMPLATE-PATH>   ::= -templatePath <template-directory>");
    appendLine(result, "<MODEL-PLUGIN-ID> ::= -modelPluginID <plugin-ID>");    
    appendLine(result, "<COPYRIGHT>       ::= -copyright <copyright-string>");
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

  protected void processArguments(String[] arguments, int index)
  {
    roseModelFullPath = new Path(new File(arguments[index++]).getAbsolutePath());

    String nextArgument = index < arguments.length ? arguments[index++] : null;
    genModelFullPath = nextArgument != null && !nextArgument.startsWith("-") ?
      new Path(new File(nextArgument).getAbsolutePath()) :
      roseModelFullPath.removeFileExtension().addFileExtension("genmodel");

    super.processArguments(arguments, index);
  }

  protected int processArgument(String[] arguments, int index)
  {
    if (arguments[index].equalsIgnoreCase("-pathmap"))
    {
      if (pathMap == null)
      {
        pathMap = new HashMap();
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
        nameToPackageInfo = new HashMap();
      }
      index = processPackageInformation(arguments, index, nameToPackageInfo);
    }
    else if (arguments[index].equalsIgnoreCase("-refPackage"))
    {
      if (nameToReferencedPackageInfo == null)
      {
        nameToReferencedPackageInfo = new HashMap();
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

  protected int processPackageInformation(String[] arguments, int index, Map nameToPackageInfo)
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

  protected void adjustModelImporter(IProgressMonitor progressMonitor)
  {
    try
    {
      progressMonitor.beginTask("", 2);

      super.adjustModelImporter(new SubProgressMonitor(progressMonitor, 1));
      RoseImporter roseImporter = getRoseImporter();
      handleGenModelPath(genModelFullPath);
      roseImporter.setModelLocation(URI.createFileURI(roseModelFullPath.toOSString()).toString());
  
      if (pathMap != null)
      {
        roseImporter.getPathMap().putAll(pathMap);
      }  
      roseImporter.setNoQualify(noQualify);
      roseImporter.setUnsettablePrimitive(unsettablePrimitive);
    }
    finally
    {
      progressMonitor.done();
    }
  }

  protected void adjustEPackages(IProgressMonitor progressMonitor)
  {
    try
    {
      progressMonitor.beginTask("", 2);

      super.adjustEPackages(new SubProgressMonitor(progressMonitor, 1));
  
      List ePackages = getRoseImporter().getEPackages();
      for (Iterator i = ePackages.iterator(); i.hasNext();)
      {
        EPackage ePackage = (EPackage)i.next();
  
        String packageName = ePackage.getName();
        PackageInfo packageInfo = nameToReferencedPackageInfo == null ? null : 
          (PackageInfo)nameToReferencedPackageInfo.get(packageName);
        
        if (packageInfo != null)
        {
          handleEPackage(ePackage, false);
        }
        else
        {
          handleEPackage(ePackage, true);
          if (nameToPackageInfo != null)
          {
            packageInfo = (PackageInfo)nameToPackageInfo.get(packageName);
          }
        }
  
        if (packageInfo != null)
        {
          ModelImporter.EPackageInfo ePackageInfo = getRoseImporter().getEPackageInfo(ePackage);

          if (packageInfo.nsURI != null)
          {
            ePackage.setNsURI(packageInfo.nsURI);
          }
          if (packageInfo.nsPrefix != null)
          {
            ePackage.setNsPrefix(packageInfo.nsPrefix);
          }
          if (packageInfo.base != null)
          {
            ePackageInfo.setBasePackage(packageInfo.base);
          }
          if (packageInfo.prefix != null)
          {
            ePackageInfo.setPrefix(packageInfo.prefix);
          }
        }
      }
    }
    finally
    {
      progressMonitor.done();
    }
  }
}
