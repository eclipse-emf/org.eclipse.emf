/**
 * <copyright> 
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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
 * $Id: XSD2GenModel.java,v 1.6 2005/02/11 06:02:07 davidms Exp $
 */
package org.eclipse.emf.codegen.ecore;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;


/**
 * This implements the method {@link #run}, 
 * which is called just like main during headless workbench invocation.
 */
public class XSD2GenModel extends Generator
{
  protected Object xsdEcoreBuilder;
  protected Map packageInformationMap;
  protected List packages;
  protected List ePackageList;
  protected List generatedEPackageList;
  protected List referencedEPackageList;

  /**
   * This creates an instance.
   */
  public XSD2GenModel() 
  {
  }

  protected void printUsage()
  {
    System.out.println("Usage: { <model.xsd> | <model.wsdl> }+ [ <model.genmodel> ] <OPTION>");
    System.out.println("<OPTION>          ::= [ <PROJECT-OPTION> ]  [ <PACKAGE-MAP> ] [ <PACKAGES> ]");
    System.out.println("                      [ <TEMPLATE-PATH> ] [ <MODEL-PLUGIN-ID> ] [ <COPYRIGHT> ] [ <SDO> ]");
    System.out.println("<PROJECT-OPTION>  ::= <MODEL-PROJECT> [ <EDIT-PROJECT> ] [ <EDITOR-PROJECT> ]");
    System.out.println("<MODEL-PROJECT>   ::= -modelProject <model-directory> <fragment-path>");
    System.out.println("<EDIT-PROJECT>    ::= -editProject <edit-directory> <fragment-path>");
    System.out.println("<EDITOR-PROJECT>  ::= -editorProject <editor-directory> <fragment-path>");
    System.out.println("<PACKAGE-MAP>     ::= -packageMap { <nsURI> <qualified-package-name> }+");
    System.out.println("<PACKAGES>        ::= -packages { <nsURI> }+");
    System.out.println("<TEMPLATE-PATH>   ::= -templatePath <template-directory>");
    System.out.println("<MODEL-PLUGIN-ID> ::= -modelPluginID <plugin-ID>");    
    System.out.println("<COPYRIGHT>       ::= -copyright <copyright-string>");
    System.out.println("<SDO>             ::= -sdo");
    System.out.println("");
    System.out.println("Specifying no -packages is the same as specifying them all");
    System.out.println("Use ##local to represent the null nsURI");
    System.out.println("");
    System.out.println("For example:");
    System.out.println("");
    System.out.println("  xsd2genmodel");
    System.out.println("    example.xsd");
    System.out.println("    result/src/model/Example.genmodel");
    System.out.println("    -modelProject result src");
    System.out.println("    -editProject result.edit src");
    System.out.println("    -editorProject result.editor src");
    System.out.println("    -packages http://www.example.com/Example1");
    System.out.println("");
  }

  /**
   * This is called with the command line arguments of a headless workbench invocation.
   */
  public Object run(Object object) 
  {
    try
    {
      final String[] arguments = (String[])object;
      final IWorkspace workspace = ResourcesPlugin.getWorkspace();
      IWorkspaceRunnable runnable = 
        new IWorkspaceRunnable()
        {
          public void run(IProgressMonitor progressMonitor) throws CoreException
          {
            try
            {
              if (arguments.length == 0 || arguments[0].equalsIgnoreCase("-help"))
              {
                printUsage();
              }
              else
              {
                execute(progressMonitor, arguments);
              }
            }
            catch (Exception exception)
            {
              throw 
                new CoreException
                  (new Status
                    (IStatus.ERROR, CodeGenEcorePlugin.getPlugin().getBundle().getSymbolicName(), 0, "Error", exception));
            }
            finally
            {
              progressMonitor.done();
            }
          }
        };
      workspace.run(runnable, new StreamProgressMonitor(System.out));

      return new Integer(0);
    }
    catch (Exception exception)
    {
      exception.printStackTrace();
      CodeGenEcorePlugin.INSTANCE.log(exception);
      return new Integer(1);
    }
  }

  public void execute(IProgressMonitor progressMonitor, String [] arguments) throws Exception
  {
    Class theGeneratorClass = Platform.getBundle("org.eclipse.xsd").loadClass("org.eclipse.xsd.ecore.XSDEcoreBuilder");
    xsdEcoreBuilder = theGeneratorClass.newInstance();

    packageInformationMap = new HashMap();
    packages = new ArrayList();
    ePackageList = new ArrayList();
    generatedEPackageList = new ArrayList();
    referencedEPackageList = new ArrayList();

    List modelURIs = new ArrayList();
    int index = 0;
    for (String modelURI = arguments[index]; modelURI.endsWith(".wsdl") || modelURI.endsWith(".xsd"); modelURI = arguments[index])
    {
      // This let's us test whether the string exists as a file.
      // It not, we try as a URI.
      //
      URI uri;
      File file = new File(modelURI);
      if (file.isFile())
      {
        uri = URI.createFileURI(file.getCanonicalFile().toString());
      }
      else
      {
        uri = URI.createURI(modelURI);
      }
      modelURIs.add(uri);
      if (++index >= arguments.length)
      {
        break;
      }
    }

    IPath genModelPath = 
      arguments.length > index && !arguments[index].startsWith("-") ?
        new Path(new File(arguments[index++]).getAbsolutePath()) :
        new Path(new File(((URI)modelURIs.get(0)).trimFileExtension().appendFileExtension("genmodel").lastSegment()).getAbsolutePath());

    URI genModelURI =  URI.createFileURI(genModelPath.toOSString());

    IPath modelProjectLocationPath = null;
    IPath modelFragmentPath = null;
    IPath editProjectLocationPath = null;
    IPath editFragmentPath = null;
    IPath editorProjectLocationPath = null;
    IPath editorFragmentPath = null;
    String templatePath = null;
    String copyright = null;
    boolean sdo = false;
    String modelPluginID = null;

    for (; index < arguments.length; ++index)
    {
      if (arguments[index].equalsIgnoreCase("-modelProject"))
      {
        modelProjectLocationPath = new Path(new File(arguments[++index]).getAbsolutePath());
        modelFragmentPath = new Path(arguments[++index]);
      }
      else if (arguments[index].equalsIgnoreCase("-editProject"))
      {
        editProjectLocationPath = new Path(new File(arguments[++index]).getAbsolutePath());
        editFragmentPath = new Path(arguments[++index]);
      }
      else if (arguments[index].equalsIgnoreCase("-editorProject"))
      {
        editorProjectLocationPath = new Path(new File(arguments[++index]).getAbsolutePath());
        editorFragmentPath = new Path(arguments[++index]);
      }
      else if (arguments[index].equalsIgnoreCase("-packagemap"))
      {
        do
        {
          String nsURI = arguments[++index];
          if ("##local".equals(nsURI))
          {
            nsURI = null;
          }
          String packageName = arguments[++index];
          packageInformationMap.put(nsURI, packageName);
        }
        while (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"));
      }
      else if (arguments[index].equalsIgnoreCase("-packages"))
      {
        do
        {
          String nsURI = arguments[++index];
          if ("##local".equals(nsURI))
          {
            nsURI = null;
          }
          packages.add(nsURI);
        }
        while (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"));
      }
      else if (arguments[index].equalsIgnoreCase("-modelPluginID"))
      {
        modelPluginID = arguments[++index];
      }      
      else if (arguments[index].equalsIgnoreCase("-copyright"))
      {
        copyright = arguments[++index];
      }
      else if (arguments[index].equalsIgnoreCase("-sdo"))
      {
        sdo = true;
      }
      else if (arguments[index].equalsIgnoreCase("-templatePath"))
      {
        do
        {
          templatePath = URI.createFileURI(new File(arguments[++index]).getAbsolutePath()).toString();
        }
        while (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"));
      }
      else
      {
        throw 
          new CoreException
            (new Status
              (IStatus.ERROR, 
               CodeGenEcorePlugin.getPlugin().getBundle().getSymbolicName(), 
               0, 
               "Unrecognized argument: '" + arguments[index] + "'", 
               null));
      }
    }

    if (modelProjectLocationPath == null)
    {
      modelProjectLocationPath = new Path(new File(".").getAbsolutePath());
    }

    if (modelFragmentPath == null)
    {
      modelFragmentPath = new Path(".");
    }

    ePackageList.addAll
      ((Collection)theGeneratorClass.getDeclaredMethod
        ("generate", new Class [] { Collection.class }).invoke(xsdEcoreBuilder, new Object [] { modelURIs }));

    if (!ePackageList.isEmpty() && ePackageList.get(ePackageList.size() - 1) instanceof List)
    {
      List diagnostics = (List)ePackageList.remove(ePackageList.size() - 1);
      if (!diagnostics.isEmpty())
      {
        System.err.println("Diagnostics were encountered processing the schemas.");
        for (Iterator i = diagnostics.iterator(); i.hasNext(); )
        {
          List information = (List)i.next();
          System.err.println(information.get(1));
        }
      }
    }

    ResourceSet resourceSet = new ResourceSetImpl();
    for (Iterator i = ePackageList.iterator(); i.hasNext(); )
    {
      EPackage ePackage = (EPackage)i.next();
      String namespace = ExtendedMetaData.INSTANCE.getNamespace(ePackage);
      if (packageInformationMap.containsKey(namespace))
      {
        ePackage.setName((String)packageInformationMap.get(namespace));
      }
      String ecoreFileName = ePackage.getName() + ".ecore";
      URI ecoreURI = URI.createURI(ecoreFileName).resolve(genModelURI);
      Resource resource = resourceSet.createResource(ecoreURI);
      resource.getContents().add(ePackage);
    }

    Resource genModelResource = resourceSet.createResource(genModelURI);
    GenModelFactory genModelFactory = GenModelFactory.eINSTANCE;

    GenModel generatedGenModel = genModelFactory.createGenModel();
    String modelName = genModelURI.trimFileExtension().lastSegment();
    modelName = Character.toUpperCase(modelName.charAt(0)) + modelName.substring(1);
    generatedGenModel.setModelName(modelName);
    genModelResource.getContents().add(generatedGenModel);

    GenModel referencedGenModel = genModelFactory.createGenModel();
    referencedGenModel.setModelName("References");
    genModelResource.getContents().add(referencedGenModel);

    if (packages.isEmpty())
    {
      generatedEPackageList.addAll(ePackageList);
    }
    else
    {
      for (Iterator i = packages.iterator(); i.hasNext(); )
      {
        String nsURI = (String)i.next();
        for (Iterator j = ePackageList.iterator(); j.hasNext(); )
        {
          EPackage ePackage = (EPackage)j.next();
          String namespace = ExtendedMetaData.INSTANCE.getNamespace(ePackage);
          if (nsURI == null ? namespace == null : namespace.equals(nsURI))
          {
            generatedEPackageList.add(ePackage);
          }
        }
      }
      referencedEPackageList.addAll(ePackageList);
      referencedEPackageList.removeAll(generatedEPackageList);
    }

    // Initialize the GenModel with all the computed data.
    //
    generatedGenModel.initialize(generatedEPackageList);
    referencedGenModel.initialize(referencedEPackageList);
    generatedGenModel.getUsedGenPackages().addAll(referencedGenModel.getGenPackages());

    if (modelPluginID == null)
    {
      generatedGenModel.setModelPluginID
      (((GenPackage)generatedGenModel.getGenPackages().get(0)).getInterfacePackageName());
    }
    else
    {
      generatedGenModel.setModelPluginID(modelPluginID);
    }
    
    generatedGenModel.setEditPluginClass
      (generatedGenModel.getModelPluginID() + ".provider." + 
         Generator.validName(generatedGenModel.getModelName()) + "EditPlugin");
    generatedGenModel.setEditorPluginClass
      (generatedGenModel.getModelPluginID() + ".presentation." + 
         Generator.validName(generatedGenModel.getModelName()) + "EditorPlugin");
    generatedGenModel.setModelDirectory(modelProjectLocationPath + "/./" + modelFragmentPath + "/.");
    if (editProjectLocationPath != null)
    {
      generatedGenModel.setEditDirectory(editProjectLocationPath + "/./" + editFragmentPath + "/.");
    }
    if (editorProjectLocationPath != null)
    {
      generatedGenModel.setEditorDirectory(editorProjectLocationPath + "/./" + editorFragmentPath + "/.");
    }

    if (templatePath != null)
    {
      generatedGenModel.setTemplateDirectory(templatePath);
      generatedGenModel.setDynamicTemplates(true);
    }

    if (copyright != null)
    {
      generatedGenModel.setCopyrightText(copyright);
    }

    for (Iterator i = modelURIs.iterator(); i.hasNext(); )
    {
      generatedGenModel.getForeignModel().add(i.next().toString());
    }
    referencedGenModel.getForeignModel().addAll(generatedGenModel.getForeignModel());

    // This walks the tree to set base packages and prefixes.
    //
    setGenPackageDetails(generatedGenModel.getGenPackages());
    setGenPackageDetails(referencedGenModel.getGenPackages());

    List missingPackages = generatedGenModel.getMissingPackages();
    for (Iterator i = missingPackages.iterator(); i.hasNext(); )
    {
      EPackage ePackage = (EPackage)i.next();
      System.err.println("The EPackage '" + ePackage.getName() + "' is used, but there's no GenPackage for it.");
    }

    if (sdo)
    {
      setSDODefaults(generatedGenModel);
    }

    for (Iterator resources = resourceSet.getResources().iterator(); resources.hasNext(); )
    {
      Resource resource = (Resource)resources.next();
      resource.save(Collections.EMPTY_MAP);
    }
  }

  protected void setGenPackageDetails(List genPackages)
  {
    // Set the package-specific base package and prefix information into the GenPackages.
    //
    for (Iterator i = genPackages.iterator(); i.hasNext(); )
    {
      GenPackage genPackage = (GenPackage)i.next();
      EPackage ePackage = genPackage.getEcorePackage();
      String name = ePackage.getName();
      int index = name.lastIndexOf(".");
      if (index != -1)
      {
        genPackage.setBasePackage(name.substring(0, index));
        name = name.substring(index + 1);
        ePackage.setName(name);
      }

      genPackage.setPrefix(Character.toUpperCase(name.charAt(0)) + name.substring(1));

      // Recursively deal with subpackages.
      //
      setGenPackageDetails(genPackage.getNestedGenPackages());
    }
  }
}
