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
 * $Id: Rose2GenModel.java,v 1.7 2004/12/29 22:19:15 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore;


import java.io.File;
import java.util.ArrayList;
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
import org.eclipse.core.runtime.Status;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.rose2ecore.RoseUtil;
import org.eclipse.emf.codegen.ecore.rose2ecore.UnitTreeNode;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;


/**
 * This implements the method {@link #run}, 
 * which is called just like main during headless workbench invocation.
 */
public class Rose2GenModel extends Generator
{
  public static final int IS_GENERATED = 0;
  public static final int NS_PREFIX = 1;
  public static final int NS_URI = 2;
  public static final int BASE = 3;
  public static final int PREFIX = 4;
  public static final int ECORE = 5;

  protected RoseUtil roseUtil;
  protected Map packageInformationMap;
  protected List ePackageList;
  protected List generatedEPackageList;
  protected List referencedEPackageList;

  // EATM this is temporary.
  //
  public static boolean noQualify;

  // EATM this is temporary.
  //
  public static boolean unsettablePrimitive = "true".equals(System.getProperty("EMF_UNSETTABLE_PRIMITIVE"));

  /**
   * This creates an instance.
   */
  public Rose2GenModel() 
  {
  }

  protected void printUsage()
  {
    System.out.println("Usage: <model.mdl> [ <model.genmodel> ] <OPTION>");
    System.out.println("<OPTION>         ::= [ <PROJECT-OPTION> ]  [ <PATHMAP> ]");
    System.out.println("                     { <PACKAGE> }+  { <REF-PACKAGE> }*");
    System.out.println("                     [ <TEMPLATE-PATH> ] [ <COPYRIGHT> ] [ <SDO> ]");
    System.out.println("<PROJECT-OPTION> ::= <MODEL-PROJECT> [ <EDIT-PROJECT> ] [ <EDITOR-PROJECT> ]");
    System.out.println("<MODEL-PROJECT>  ::= -modelProject <mode-directory> <fragment-path>");
    System.out.println("<EDIT-PROJECT>   ::= -editProject <edit-directory> <fragment-path>");
    System.out.println("<EDITOR-PROJECT> ::= -editorProject <editor-directory> <fragment-path>");
    System.out.println("<PATHMAP>        ::= -pathMap { <symbol> <directory> }+");
    System.out.println("<PACKAGE>        ::= -package <name> [ <nsPrefix> <nsURI> <base> <prefix> ]");
    System.out.println("<REF-PACKAGE>    ::= -refPackage <name> [ <nsPrefix> <nsURI> <base> <prefix> ]");
    System.out.println("<TEMPLATE-PATH>  ::= -templatePath <template-directory>");
    System.out.println("<COPYRIGHT>      ::= -copyright <copyright-string>");
    System.out.println("<SDO>            ::= -sdo");
    System.out.println("");
    System.out.println("For example:");
    System.out.println("");
    System.out.println("  rose2genmodel");
    System.out.println("    ../../etools.company/src/rose/model.mdl");
    System.out.println("    result/src/model/Extended.genmodel");
    System.out.println("    -modelProject result src");
    System.out.println("    -editProject result.edit src");
    System.out.println("    -editorProject result.editor src");
    System.out.println("    -pathMap VABASE_PLUGINS_PATH C:/sandbox/unpackage2/eclipse/plugins"); 
    System.out.println("    -package extended Extended Extended.ecore org.example Extended");
    System.out.println("    -refPackage company Company Company.ecore org.sample Company");
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

  /**
   * Creates the genmodel.
   * @param progressMonitor
   * @param arguments
   * @throws Exception
   * @since 2.1.0
   */
  public void execute(IProgressMonitor progressMonitor, String [] arguments) throws Exception
  {
    roseUtil = new RoseUtil();
    packageInformationMap = new HashMap();
    ePackageList = new ArrayList();
    generatedEPackageList = new ArrayList();
    referencedEPackageList = new ArrayList();

    int index = 0;
    IPath roseModelPath = new Path(new File(arguments[index++]).getAbsolutePath());
    IPath genModelPath = 
      arguments.length > index && !arguments[index].startsWith("-") ?
        new Path(new File(arguments[index++]).getAbsolutePath()) :
        roseModelPath.removeFileExtension().addFileExtension("genmodel");

    IPath modelProjectLocationPath = null;
    IPath modelFragmentPath = null;
    IPath editProjectLocationPath = null;
    IPath editFragmentPath = null;
    IPath editorProjectLocationPath = null;
    IPath editorFragmentPath = null;
    String templatePath = null;
    String copyright = null;
    boolean sdo = false;

    for (; index < arguments.length; ++index)
    {
      if (arguments[index].equalsIgnoreCase("-noQualify"))
      {
        noQualify = true;
      }
      else if (arguments[index].equalsIgnoreCase("-unsettablePrimitive"))
      {
        unsettablePrimitive = true;
      }
      else if (arguments[index].equalsIgnoreCase("-modelProject"))
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
      else if (arguments[index].equalsIgnoreCase("-pathmap"))
      {
        do
        {
          String variable = arguments[++index];
          String directory = arguments[++index];
          roseUtil.getVariableToDirectoryMap().put(variable, directory);
        }
        while (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"));
      }
      else if (arguments[index].equalsIgnoreCase("-package") || arguments[index].equalsIgnoreCase("-refpackage"))
      {
        int start = index;
        List packageInformation = new ArrayList(5);
        packageInformation.add(arguments[index].equalsIgnoreCase("-package") ? Boolean.TRUE : Boolean.FALSE);
        if (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"))
        {
          String name = arguments[++index];
          if (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"))
          {
            packageInformation.add(arguments[++index]);
            if (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"))
            {
              packageInformation.add(arguments[++index]);
              if (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"))
              {
                packageInformation.add(arguments[++index]);
                if (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"))
                {
                  packageInformation.add(arguments[++index]);
                }
              }
            }
          }
          if (packageInformation.size() != 1 && packageInformation.size() != 5)
          {
            System.err.println("Error: Expecting either 1 or 5 arguments for " + arguments[start]);
            throw new RuntimeException();
          }
          else
          {
            packageInformationMap.put(name, packageInformation);
            packageInformationMap.put(name.toLowerCase(), packageInformation);
          }
        }
        else
        {
          System.err.println("Error: No package name was specified for " + arguments[start]);
          throw new RuntimeException();
        }
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

    UnitTreeNode unitTree = roseUtil.createRoseUnitTreeAndTable(roseModelPath.toOSString(), null);
    roseUtil.createExtent4RoseUnitTree(unitTree);
    // roseUtil.showRoseUnitTree(unitTree);
    roseUtil.processUnitTree(unitTree);

    traverseUnitTree(unitTree);

    traverseEPackages(ePackageList);

    ResourceSet resourceSet = new ResourceSetImpl();
    for (Iterator i = ePackageList.iterator(); i.hasNext(); )
    {
      EPackage ePackage = (EPackage)i.next();
      List packageInformation = (List)packageInformationMap.get(ePackage.getName());
      String ecoreFileName = 
        packageInformation == null ||
          ECORE >= packageInformation.size() ? ePackage.getName() + ".ecore" : (String)packageInformation.get(ECORE);
      URI ecoreURI = URI.createFileURI(ecoreFileName);
      Resource resource = resourceSet.getResource(ecoreURI, false);
      if (resource == null)
      {
        ecoreURI = URI.createFileURI(genModelPath.removeLastSegments(1).append(ecoreFileName).toString());
        resource = 
          Resource.Factory.Registry.INSTANCE.getFactory(ecoreURI).createResource(ecoreURI);
        resourceSet.getResources().add(resource);
      }
      resource.getContents().add(ePackage);
    }

    URI genModelURI = URI.createFileURI(genModelPath.toOSString());
    Resource genModelResource = 
      Resource.Factory.Registry.INSTANCE.getFactory(genModelURI).createResource(genModelURI);
    GenModelFactory genModelFactory = GenModelFactory.eINSTANCE;

    GenModel generatedGenModel = genModelFactory.createGenModel();
    String modelName = genModelURI.trimFileExtension().lastSegment();
    modelName = Character.toUpperCase(modelName.charAt(0)) + modelName.substring(1);
    generatedGenModel.setModelName(modelName);
    genModelResource.getContents().add(generatedGenModel);

    GenModel referencedGenModel = genModelFactory.createGenModel();
    genModelResource.getContents().add(referencedGenModel);

    resourceSet.getResources().add(genModelResource);

    // Initialize the GenModel with all the computed data.
    //
    generatedGenModel.initialize(generatedEPackageList);
    referencedGenModel.initialize(referencedEPackageList);
    generatedGenModel.getUsedGenPackages().addAll(referencedGenModel.getGenPackages());

    // generatedGenModel.setModelPluginID(modelProjectLocationPath.lastSegment());
    generatedGenModel.setModelPluginID
      (((GenPackage)generatedGenModel.getGenPackages().get(0)).getInterfacePackageName());
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

    generatedGenModel.getForeignModel().add(roseModelPath.toOSString());
    for (Iterator i = roseUtil.getVariableToDirectoryMap().entrySet().iterator(); i.hasNext(); )
    {
      Map.Entry entry = (Map.Entry)i.next();
      if (entry.getKey() != null && entry.getValue() != null)
      {
        generatedGenModel.getForeignModel().add(entry.getKey());
        generatedGenModel.getForeignModel().add(entry.getValue());
      }
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
      resource.save(null);
    }
  }

  protected void traverseUnitTree(UnitTreeNode unitTreeNode)
  {
    ePackageList.addAll(unitTreeNode.getExtent());

    for (Iterator i = unitTreeNode.getExtent().iterator(); i.hasNext(); )
    {
      EPackage ePackage = (EPackage)i.next();
      String roseFileName = unitTreeNode.getRoseFileName();
      int indexOfSlash = roseFileName.lastIndexOf(File.separator);
 
      String ecoreFileName;
      if (roseFileName.endsWith(".cat") && indexOfSlash != -1)
      {
        String baseName = roseFileName.substring(indexOfSlash + 1, roseFileName.length() - 4);
        int indexOfDot = baseName.lastIndexOf(".");
        if (indexOfDot != -1)
        {
          basePackage =  baseName.substring(0, indexOfDot);
          baseName = baseName.substring(indexOfDot + 1);
        }
        ecoreFileName = baseName + ".ecore";
      }
      else if (unitTreeNode.getExtent().size() == 1)
      {
        ecoreFileName = unitTreeNode.getName() + ".ecore";
      }
      else
      {
        ecoreFileName = ePackage.getName() + ".ecore";
      }

      List packageInformation = (List)packageInformationMap.get(ePackage.getName());
      if (packageInformation != null)
      {
        packageInformation.add(ecoreFileName);

        if (((Boolean)packageInformation.get(IS_GENERATED)).booleanValue())
        {
          generatedEPackageList.add(ePackage);
        }
        else
        {
          referencedEPackageList.add(ePackage);
        }
      }
      else 
      {
        boolean noGenerates = true;
        for (Iterator entries = packageInformationMap.entrySet().iterator(); entries.hasNext(); )
        {
          Map.Entry entry = (Map.Entry)entries.next();
          if (((Boolean)((List)entry.getValue()).get(IS_GENERATED)).booleanValue())
          {
            noGenerates = false;
            break;
          }
        }
        if (noGenerates)
        {
          generatedEPackageList.add(ePackage);
        }
      }
    }

    for (Iterator i = unitTreeNode.getNodes().iterator(); i.hasNext(); )
    {
      UnitTreeNode childUnitTreeNode = (UnitTreeNode)i.next();
      traverseUnitTree(childUnitTreeNode);
    }
  }

  protected void traverseEPackages(List ePackages)
  {
    for (Iterator i = ePackages.iterator(); i.hasNext(); )
    {
      EPackage ePackage = (EPackage)i.next();

      // EATM This should go.
      //
      List rosePackageInformation = (List)roseUtil.getEPackageToInformationMap().get(ePackage);
      if (ePackage.getNsPrefix() == null && ePackage.getNsURI() == null || rosePackageInformation == null)
      {
        List packageInformation = (List)packageInformationMap.get(ePackage.getName());
        if (packageInformation == null || NS_URI >= packageInformation.size())
        {
          String nsPrefix  = ePackage.getName();
          EPackage eSuperPackage = ePackage.getESuperPackage();
          if (eSuperPackage != null)
          {
            nsPrefix = eSuperPackage.getNsPrefix() + "." + nsPrefix;
          }

          if (ePackage.getNsPrefix() == null)
          {
            ePackage.setNsPrefix(nsPrefix);
          }
          if (ePackage.getNsURI() == null)
          {
            if (noQualify)
            {
              ePackage.setNsURI(nsPrefix + ".ecore");
            }
            else
            {
              ePackage.setNsURI("http:///" + nsPrefix + ".ecore");
            }
          }
        }
        else
        {
          ePackage.setNsPrefix((String)packageInformation.get(NS_PREFIX));
          ePackage.setNsURI((String)packageInformation.get(NS_URI));
        }
      }

      traverseEPackages(ePackage.getESubpackages());
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
      List rosePackageInformation = (List)roseUtil.getEPackageToInformationMap().get(ePackage);
      if (rosePackageInformation != null)
      {
        genPackage.setBasePackage((String)rosePackageInformation.get(0));
        genPackage.setPrefix((String)rosePackageInformation.get(1));
      }
      else
      {
        List packageInformation = (List)packageInformationMap.get(ePackage.getName());
        if (packageInformation != null && packageInformation.size() > PREFIX)
        {
          genPackage.setBasePackage((String)packageInformation.get(BASE));
          genPackage.setPrefix((String)packageInformation.get(PREFIX));
        }
        else
        {
          genPackage.setBasePackage("org.sample");
          String prefix = ePackage.getName();
          genPackage.setPrefix(prefix.substring(0, 1).toUpperCase() + prefix.substring(1));
        }
      }

      // Recursively deal with subpackages.
      //
      setGenPackageDetails(genPackage.getNestedGenPackages());
    }
  }
}
