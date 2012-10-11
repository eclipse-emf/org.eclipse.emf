/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.importer.java;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ModelImporterApplication;

/**
 * @since 2.3.0
 */
public class JavaImporterApplication extends ModelImporterApplication
{
  public static class PackageInfo
  {
    public String nsURI;
    public String base;
    public String prefix;
  }

  protected Map<String, PackageInfo> nameToPackageInfo;
  protected IPath genModelLocation;

  public JavaImporter getJavaImporter()
  {
    return (JavaImporter)getModelImporter();
  }

  @Override
  protected ModelImporter createModelImporter()
  {
    return new JavaImporter();
  }

  @Override
  protected StringBuffer getUsage()
  {
    StringBuffer result = new StringBuffer();
    appendLine(result, "Usage: <workspace absolute path of model.genmodel> [ -reload ] <OPTION>");
    appendLine(result, "<OPTION>          ::= [ <PROJECT-OPTION> ]  [ <PATHMAP> ]");
    appendLine(result, "                      { <PACKAGE> }+  { <REF-PACKAGE> }* { <REF-GEN-MODEL> }*");
    appendLine(result, "                      [ <TEMPLATE-PATH> ] [ <MODEL-PLUGIN-ID> ] [ <COPYRIGHT> ]");
    appendLine(result, "                      [ <SDO> ] [ <QUIET> ]");
    appendLine(result, "<PROJECT-OPTION>  ::= <MODEL-PROJECT> [ <EDIT-PROJECT> ] [ <EDITOR-PROJECT> ]");
    appendLine(result, "                      [ <TESTS-PROJECT> ]");
    appendLine(result, "<MODEL-PROJECT>   ::= -modelProject <model-directory>");
    appendLine(result, "<EDIT-PROJECT>    ::= -editProject <edit-directory> <fragment-path>");
    appendLine(result, "<EDITOR-PROJECT>  ::= -editorProject <editor-directory> <fragment-path>");
    appendLine(result, "<TESTS-PROJECT>   ::= -testsProject <tests-directory> <fragment-path>");
    appendLine(result, "<PACKAGE>         ::= -package <nsURI> [ <base> <prefix> ]");
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
    appendLine(result, "  java2genmodel");
    appendLine(result, "    /library/model/lib.genmodel");
    appendLine(result, "    -modelProject result");
    appendLine(result, "    -editProject result.edit src");
    appendLine(result, "    -editorProject result.editor src");
    return result;
  }
  
  @Override
  protected int processModelAndGenModelLocationArguments(String[] arguments, int index)
  {
    if (arguments.length > 0)
    {
      String arg = arguments[index++];
      if (arg.endsWith(".genmodel"))
      {
        genModelFullPath = new Path(arg);
        return index;
      }
    }
    throw new IllegalArgumentException("Invalid arguments.  Run this application without arguments for the usage help.");
  }
  
  @Override
  protected int processModelProjectArguments(String[] arguments, int index)
  {
    modelProjectLocationPath = new Path(new File(arguments[++index]).getAbsolutePath());
    return index;
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
  protected boolean usePlatformURI()
  {
    return true;
  }
  
  @Override
  protected void adjustModelImporter(Monitor monitor)
  {
    super.adjustModelImporter(monitor);
    
    IFile genModelFile = getJavaImporter().getGenModelFile();
    if (!genModelFile.getProject().exists())
    {
      try
      {
        IProject project = genModelFile.getProject();
        IProjectDescription projectDescription = ResourcesPlugin.getWorkspace().newProjectDescription(project.getName());
        projectDescription.setLocation(modelProjectLocationPath);
        project.create(projectDescription, BasicMonitor.toIProgressMonitor(monitor));
      }
      catch (CoreException e)
      {
        JavaImporterPlugin.INSTANCE.log(e);
        throw new RuntimeException(e);
      }
    }
  }
  
  @Override
  protected void adjustModelImporterAfterPrepare()
  {
    super.adjustModelImporterAfterPrepare();
  }
  
  @Override
  protected void adjustEPackages(Monitor monitor)
  {
    try
    {
      monitor.beginTask("", 2);
      super.adjustEPackages(CodeGenUtil.createMonitor(monitor, 1));
      
      List<EPackage> ePackages = getJavaImporter().getEPackages();
      traverseEPackages(ePackages);
      getJavaImporter().adjustEPackages(CodeGenUtil.createMonitor(monitor, 1));
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
          
          ModelImporter.EPackageImportInfo ePackageInfo = getJavaImporter().getEPackageImportInfo(ePackage);        
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
