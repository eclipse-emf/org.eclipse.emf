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
package org.eclipse.xsd.ecore.importer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ModelImporterApplication;


public class XSDImporterApplication extends ModelImporterApplication
{
  protected Map<String, String> nsURIToPackageName;
  protected Set<String> packages;

  @Override
  protected ModelImporter createModelImporter()
  {
    return new XSDImporter();
  }

  @Override
  protected StringBuffer getUsage()
  {
    StringBuffer result = new StringBuffer();
    appendLine(result, "Usage: { <model.xsd> | <model.wsdl> }+ [ <model.genmodel> [ -reload ] ] <OPTION>");
    appendLine(result, "<OPTION>            ::= [ <PROJECT-OPTION> ]  [ <PACKAGE-MAP> ] [ <PACKAGES> ]");
    appendLine(result, "                        { <REF-GEN-MODEL> }*");
    appendLine(result, "                        [ <TEMPLATE-PATH> ] [ <MODEL-PLUGIN-ID> ] [ <COPYRIGHT> ]");
    appendLine(result, "                        [ <SDO> ] [ <QUIET> ]");
    appendLine(result, "<PROJECT-OPTION>    ::= <MODEL-PROJECT> [ <EDIT-PROJECT> ] [ <EDITOR-PROJECT> ]");
    appendLine(result, "                        [ <TESTS-PROJECT> ]");
    appendLine(result, "<MODEL-PROJECT>     ::= -modelProject <model-directory> <fragment-path>");
    appendLine(result, "<EDIT-PROJECT>      ::= -editProject <edit-directory> <fragment-path>");
    appendLine(result, "<EDITOR-PROJECT>    ::= -editorProject <editor-directory> <fragment-path>");
    appendLine(result, "<TESTS-PROJECT>     ::= -testsProject <tests-directory> <fragment-path>");
    appendLine(result, "<PACKAGE-MAP>       ::= -packageMap { <nsURI> <qualified-package-name> }+");
    appendLine(result, "<PACKAGES>          ::= -packages { <nsURI> }+");
    appendLine(result, "<REF-GEN-MODEL>     ::= -refGenModel <model.genmodel> { <nsURI> }+");
    appendLine(result, "<TEMPLATE-PATH>     ::= -templatePath <template-directory>");
    appendLine(result, "<MODEL-PLUGIN-ID>   ::= -modelPluginID <plugin-ID>");
    appendLine(result, "<COPYRIGHT>         ::= -copyright <copyright-string>");
    appendLine(result, "<JDK-LEVEL>         ::= -jdkLevel <jdk level: 1.4 5.0 6.0>");
    appendLine(result, "<IMPORT-ORGANIZING> ::= -sdo");
    appendLine(result, "<VALIDATE-MODEL>    ::= -validateModel <true|false>");
    appendLine(result, "<SDO>               ::= -sdo");
    appendLine(result, "<QUIET>             ::= -quiet");
    appendLine(result, "");
    appendLine(result, "Specifying no -packages is the same as specifying them all");
    appendLine(result, "Use ##local to represent the null nsURI");
    appendLine(result, "");
    appendLine(result, "For example:");
    appendLine(result, "");
    appendLine(result, "  xsd2genmodel");
    appendLine(result, "    example.xsd");
    appendLine(result, "    result/model/Example.genmodel");
    appendLine(result, "    -modelProject result src");
    appendLine(result, "    -editProject result.edit src");
    appendLine(result, "    -editorProject result.editor src");
    appendLine(result, "    -packages http://www.example.com/Example1");
    return result;
  }

  @Override
  protected int processArgument(String[] arguments, int index)
  {
    if (arguments[index].equalsIgnoreCase("-packagemap"))
    {
      if (nsURIToPackageName == null)
      {
        nsURIToPackageName = new HashMap<String, String>();
      }

      do
      {
        String nsURI = interpretNsURI(arguments[++index]);
        nsURIToPackageName.put(nsURI, arguments[++index]);
      }
      while (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"));
    }
    else if (arguments[index].equalsIgnoreCase("-packages"))
    {
      if (packages == null)
      {
        packages = new HashSet<String>();
      }

      do
      {
        packages.add(interpretNsURI(arguments[++index]));
      }
      while (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"));
    }
    else
    {
      return super.processArgument(arguments, index);
    }
    return index + 1;
  }

  protected String interpretNsURI(String nsURI)
  {
    return "##local".equals(nsURI) ? null : nsURI;
  }

  @Override
  protected void adjustEPackages(Monitor monitor)
  {
    try
    {
      monitor.beginTask("", 2);

      super.adjustEPackages(CodeGenUtil.createMonitor(monitor, 1));

      for (EPackage ePackage : getModelImporter().getEPackages())
      {
        String nsURI = ExtendedMetaData.INSTANCE.getNamespace(ePackage);
        if (nsURIToPackageName != null)
        {
          String packageName = nsURIToPackageName.get(nsURI);
          if (packageName != null)
          {
            ePackage.setName(packageName);
          }          
        }
        
        handleQualifiedEPackageName(ePackage);
        boolean isNotReferencedEPackage = packages == null || packages.isEmpty() || packages.contains(nsURI);
        handleEPackage(ePackage, isNotReferencedEPackage);
      }
      
      getModelImporter().adjustEPackages(CodeGenUtil.createMonitor(monitor, 1));
    }
    finally
    {
      monitor.done();
    }
  }
}
