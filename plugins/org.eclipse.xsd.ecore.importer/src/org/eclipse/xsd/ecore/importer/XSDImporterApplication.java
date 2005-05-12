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
 * $Id: XSDImporterApplication.java,v 1.2 2005/05/12 18:05:13 emerks Exp $
 */
package org.eclipse.xsd.ecore.importer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;

import org.eclipse.emf.codegen.CodeGen.ProgressMonitorPrinter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ModelImporterApplication;


public class XSDImporterApplication extends ModelImporterApplication
{
  protected String xsdModelURIs;
  protected IPath genModelFullPath;

  protected Map nsURIToPackageName;
  protected Set packages;

  protected ModelImporter createModelImporter()
  {
    return new XSDImporter();
  }

  protected StringBuffer getUsage()
  {
    StringBuffer result = new StringBuffer();
    appendLine(result, "Usage: { <model.xsd> | <model.wsdl> }+ [ <model.genmodel> [ -reload ] ] <OPTION>");
    appendLine(result, "<OPTION>          ::= [ <PROJECT-OPTION> ]  [ <PACKAGE-MAP> ] [ <PACKAGES> ]");
    appendLine(result, "                      { <REF-GEN-MODEL> }*");
    appendLine(result, "                      [ <TEMPLATE-PATH> ] [ <MODEL-PLUGIN-ID> ] [ <COPYRIGHT> ]");
    appendLine(result, "                      [ <SDO> ] [ <QUIET> ]");
    appendLine(result, "<PROJECT-OPTION>  ::= <MODEL-PROJECT> [ <EDIT-PROJECT> ] [ <EDITOR-PROJECT> ]");
    appendLine(result, "                      [ <TESTS-PROJECT> ]");
    appendLine(result, "<MODEL-PROJECT>   ::= -modelProject <model-directory> <fragment-path>");
    appendLine(result, "<EDIT-PROJECT>    ::= -editProject <edit-directory> <fragment-path>");
    appendLine(result, "<EDITOR-PROJECT>  ::= -editorProject <editor-directory> <fragment-path>");
    appendLine(result, "<TESTS-PROJECT>   ::= -testsProject <tests-directory> <fragment-path>");
    appendLine(result, "<PACKAGE-MAP>     ::= -packageMap { <nsURI> <qualified-package-name> }+");
    appendLine(result, "<PACKAGES>        ::= -packages { <nsURI> }+");
    appendLine(result, "<REF-GEN-MODEL>   ::= -refGenModel <model.genmodel> { <nsURI> }+");
    appendLine(result, "<TEMPLATE-PATH>   ::= -templatePath <template-directory>");
    appendLine(result, "<MODEL-PLUGIN-ID> ::= -modelPluginID <plugin-ID>");
    appendLine(result, "<COPYRIGHT>       ::= -copyright <copyright-string>");
    appendLine(result, "<SDO>             ::= -sdo");
    appendLine(result, "<QUIET>           ::= -quiet");
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

  protected void processArguments(ProgressMonitorPrinter printer, String[] arguments, int index)
  {
    StringBuffer uris = new StringBuffer();
    URI firstModelURI = null;
    
    for (String modelURI = arguments[index]; modelURI.endsWith(".wsdl") || modelURI.endsWith(".xsd"); modelURI = arguments[index])
    {
      // This let's us test whether the string exists as a file.
      // It not, we try as a URI.
      //
      URI uri = null;
      File file = new File(modelURI);
      if (file.isFile())
      {
        try
        {
          uri = URI.createFileURI(file.getCanonicalFile().toString());
        }
        catch (IOException e) {}
      }
      if (uri == null)
      {
        uri = URI.createURI(modelURI);
      }

      if (uris.length() > 0)
      {
        uris.append(' ');
      }
      else
      {
        firstModelURI = uri;
      }
      uris.append(uri);

      if (++index >= arguments.length)
      {
        break;
      }
    }

    xsdModelURIs = uris.toString();
    if (firstModelURI == null)
    {
      throw new IllegalArgumentException("Error: No schema file (.xsd or .wsdl) specified");
    }

    genModelFullPath = arguments.length > index && !arguments[index].startsWith("-") ?
        new Path(new File(arguments[index++]).getAbsolutePath()) :
        new Path(new File(firstModelURI.trimFileExtension().appendFileExtension("genmodel").lastSegment()).getAbsolutePath());

    super.processArguments(printer, arguments, index);
  }

  protected int processArgument(ProgressMonitorPrinter printer, String[] arguments, int index)
  {
    if (arguments[index].equalsIgnoreCase("-packagemap"))
    {
      if (nsURIToPackageName == null)
      {
        nsURIToPackageName = new HashMap();
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
        packages = new HashSet();
      }

      do
      {
        packages.add(interpretNsURI(arguments[++index]));
      }
      while (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"));
    }
    else
    {
      return super.processArgument(printer, arguments, index);
    }
    return index + 1;
  }

  protected String interpretNsURI(String nsURI)
  {
    return "##local".equals(nsURI) ? null : nsURI;
  }

  protected void adjustModelImporter(IProgressMonitor progressMonitor)
  {
    try
    {
      progressMonitor.beginTask("", 2);

      super.adjustModelImporter(new SubProgressMonitor(progressMonitor, 1));
      handleGenModelPath(genModelFullPath);
      getModelImporter().setModelLocation(xsdModelURIs);
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

      for (Iterator i = getModelImporter().getEPackages().iterator(); i.hasNext();)
      {
        EPackage ePackage = (EPackage)i.next();
        String nsURI = ExtendedMetaData.INSTANCE.getNamespace(ePackage);

        if (nsURIToPackageName != null)
        {
          String packageName = (String)nsURIToPackageName.get(nsURI);
          if (packageName != null)
          {
            ePackage.setName(packageName);
          }
          getModelImporter().adjustEPackage(ePackage);
        }

        handleEPackage(ePackage, packages == null || packages.isEmpty() || packages.contains(nsURI));
      }
    }
    finally
    {
      progressMonitor.done();
    }
  }
}
