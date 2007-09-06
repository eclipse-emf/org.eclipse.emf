/**
 * <copyright>
 *
 * Copyright (c) 2005-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ModelImporterApplication.java,v 1.21.2.1 2007/09/06 19:47:40 marcelop Exp $
 */
package org.eclipse.emf.importer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IPlatformRunnable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;

import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.codegen.util.CodeGenUtil.EclipseUtil.StreamProgressMonitor;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.importer.util.ImporterUtil;


/**
 * @since 2.1.0
 */
public abstract class ModelImporterApplication implements IPlatformRunnable
{
  protected ModelImporter modelImporter;

  protected String modelLocations;
  protected IPath genModelFullPath;
  
  protected boolean reload = false;
  protected IPath modelProjectLocationPath;
  protected IPath modelFragmentPath;
  protected IPath editProjectLocationPath;
  protected IPath editFragmentPath;
  protected IPath editorProjectLocationPath;
  protected IPath editorFragmentPath;
  protected IPath testsProjectLocationPath;
  protected IPath testsFragmentPath;
  protected String templatePath;
  protected String copyright;
  protected boolean sdo = false;
  protected String modelPluginID;

  protected List referencedEPackages;
  protected Map referencedGenModelURIToEPackageNSURIs;

  protected boolean quiet = false;

  public ModelImporter getModelImporter()
  {
    if (modelImporter == null)
    {
      modelImporter = createModelImporter();
    }
    return modelImporter;
  }

  protected abstract ModelImporter createModelImporter();

  public Object run(final Object args) throws Exception
  {
    try
    {
      IWorkspaceRunnable runnable = new IWorkspaceRunnable()
        {
          public void run(IProgressMonitor progressMonitor) throws CoreException
          {
            try
            {
              ModelImporterApplication.this.run(progressMonitor, (String[])args);
            }
            catch (Exception exception)
            {
              throw new CoreException(new Status(
                IStatus.ERROR,
                ImporterPlugin.ID,
                0,
                "Error",
                exception));
            }
            finally
            {
              progressMonitor.done();
            }
          }
        };

      ResourcesPlugin.getWorkspace().run(runnable, getProgressMonitor());
      return new Integer(0);
    }
    catch (Exception exception)
    {
      if (!quiet)
      {
        exception.printStackTrace();
      }
      ImporterPlugin.INSTANCE.log(exception);
      return new Integer(1);
    }
  }

  protected IProgressMonitor getProgressMonitor()
  {
    return quiet ? new NullProgressMonitor() : new StreamProgressMonitor(System.out);
  }
  
  public void run(IProgressMonitor progressMonitor, String[] arguments) throws Exception
  {
    run(BasicMonitor.toMonitor(progressMonitor), arguments); 
  }

  public void run(Monitor monitor, String[] arguments) throws Exception
  {
    try
    {
      monitor.beginTask("", 1);

      if (arguments.length == 0 || "-help".equalsIgnoreCase(arguments[0].toString()))
      {
        printUsage();
      }
      else
      {
        processArguments(arguments, "-pdelaunch".equals(arguments[0]) ? 1 : 0);
        execute(CodeGenUtil.createMonitor(monitor, 1));
      }
    }
    finally
    {
      monitor.done();
    }
  }
    
  public void printUsage()
  {
    System.out.println(getUsage());
  }

  protected abstract StringBuffer getUsage();

  protected static final String NL = System.getProperties().getProperty("line.separator");

  protected StringBuffer appendLine(StringBuffer buffer, String line)
  {
    if (line != null)
    {
      buffer.append(line);
    }
    buffer.append(NL);
    return buffer;
  }

  public void execute(Monitor monitor) throws Exception
  {
    try
    {
      monitor.beginTask("", 6);

      adjustAttributes(CodeGenUtil.createMonitor(monitor, 1));
      adjustModelImporter(CodeGenUtil.createMonitor(monitor, 1));
  
      computeEPackages(CodeGenUtil.createMonitor(monitor, 1));
      adjustEPackages(CodeGenUtil.createMonitor(monitor, 1));
      adjustGenModel(CodeGenUtil.createMonitor(monitor, 1));
  
      doExecute(CodeGenUtil.createMonitor(monitor, 1));
    }
    finally
    {
      monitor.done();
    }
  }

  protected void processArguments(String[] arguments, int index)
  {
    index = processModelAndGenModelLocationArguments(arguments, index);
    while (index < arguments.length)
    {
      index = processArgument(arguments, index); 
    }
  }
  
  protected int processModelAndGenModelLocationArguments(String[] arguments, int index)
  {
    StringBuffer locations = new StringBuffer();
    URI firstModelURI = null;
    
    while (arguments.length > index && arguments[index] != null && !arguments[index].startsWith("-"))
    {
      String location = arguments[index++];
      if (location.endsWith(".genmodel"))
      {
        genModelFullPath = new Path(new File(location).getAbsolutePath());
        break;
      }
      else
      {
        File file = new File(location);
        if (file.isFile())
        {
          try
          {
            location = URI.createFileURI(file.getCanonicalPath()).toString();
          }
          catch (IOException e)
          {
            location = URI.createFileURI(file.getAbsolutePath()).toString();
          }
        }
        
        if (firstModelURI == null)
        {
          firstModelURI = URI.createURI(location);
        }
        locations.append(" ").append(location);
      }
    }
    
    if (firstModelURI != null)      
    {
      modelLocations = locations.deleteCharAt(0).toString(); // removing the first space
      
      if (genModelFullPath == null)
      {
        genModelFullPath = 
          new Path(new File(firstModelURI.trimFileExtension().appendFileExtension("genmodel").lastSegment()).getAbsolutePath());
      }
    }
    
    return index;
  }
  
  protected int processArgument(String[] arguments, int index)
  {
    if (arguments[index].equalsIgnoreCase("-reload"))
    {
      reload = true;
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
    else if (arguments[index].equalsIgnoreCase("-testsProject"))
    {
      testsProjectLocationPath = new Path(new File(arguments[++index]).getAbsolutePath());
      testsFragmentPath = new Path(arguments[++index]);
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
    else if (arguments[index].equalsIgnoreCase("-quiet"))
    {
      quiet = true;
    }
    else if (arguments[index].equalsIgnoreCase("-templatePath"))
    {
      templatePath = URI.createFileURI(new File(arguments[++index]).getAbsolutePath()).toString();
    }
    else if (arguments[index].equalsIgnoreCase("-refGenModel"))
    {
      if (referencedGenModelURIToEPackageNSURIs == null)
      {
        referencedGenModelURIToEPackageNSURIs = new HashMap();
      }

      URI genModelURI = URI.createURI(arguments[++index]);
      Set ePackageNSURIs = (Set)referencedGenModelURIToEPackageNSURIs.get(genModelURI);
      if (ePackageNSURIs == null)
      {
        ePackageNSURIs = new HashSet();
        referencedGenModelURIToEPackageNSURIs.put(genModelURI, ePackageNSURIs);
      }

      do
      {
        ePackageNSURIs.add(arguments[++index]);
      }
      while (index + 1 < arguments.length && !arguments[index + 1].startsWith("-"));
    }
    else
    {
      handleUnrecognizedArgument(arguments[index]);
    }
    return index + 1;
  }

  protected void handleUnrecognizedArgument(String argument)
  {
    throw new IllegalArgumentException("Unrecognized argument: '" + argument + "'");
  }

  protected void adjustAttributes(Monitor monitor)
  {
    try
    {
      monitor.beginTask("", 1);

      if (modelProjectLocationPath == null)
      {
        modelProjectLocationPath = new Path(new File(".").getAbsolutePath());
      }
  
      if (modelFragmentPath == null)
      {
        modelFragmentPath = new Path(".");
      }
    }
    finally
    {
      monitor.done();
    }
  }

  protected void adjustModelImporter(Monitor monitor)
  {
    try
    {
      monitor.beginTask("", 1);

      ModelImporter modelImporter = getModelImporter();
      modelImporter.setUsePlatformURI(false);
      modelImporter.setGenModelProjectLocation(modelProjectLocationPath);
      if (modelPluginID != null)
      {
        modelImporter.setModelPluginID(modelPluginID);
      }
      if (modelProjectLocationPath != null)
      {
        modelImporter.setModelPluginDirectory(modelProjectLocationPath + "/./" + modelFragmentPath + "/.");
      }
      
      handleGenModelPath(genModelFullPath);
      modelImporter.setModelLocation(modelLocations);
    }
    finally
    {
      monitor.done();
    }
  }

  protected void handleGenModelPath(IPath genModelFullPath)
  {
    ModelImporter modelImporter = getModelImporter();
    if (reload)
    {
      try
      {
        modelImporter.defineOriginalGenModelPath(genModelFullPath);
      }
      catch (DiagnosticException exception)
      {
        throw new RuntimeException(exception);
      }
    }
    else
    {
      modelImporter.setGenModelContainerPath(genModelFullPath.removeLastSegments(1));
      modelImporter.setGenModelFileName(genModelFullPath.lastSegment());
    }
  }

  protected final void computeEPackages(Monitor monitor) throws Exception
  {
    try
    {
      monitor.beginTask("", 1);
      getModelImporter().computeEPackages(CodeGenUtil.createMonitor(monitor, 1));
    }
    finally
    {
      monitor.done();
    }
  }

  protected void adjustEPackages(Monitor monitor)
  {
    try
    {
      monitor.beginTask("", 1);

      if (referencedGenModelURIToEPackageNSURIs != null)
      {
        ResourceSet resourceSet = getModelImporter().createResourceSet();
        for (Iterator i = referencedGenModelURIToEPackageNSURIs.entrySet().iterator(); i.hasNext();)
        {
          Map.Entry entry = (Map.Entry)i.next();
          URI genModelURI = (URI)entry.getKey();
          Set ePackageNSURIs = (Set)entry.getValue();
          
          File genModelFile = new File(genModelURI.toString());
          if (genModelFile.isFile())
          {
            IPath genModelPath = new Path(genModelFile.getAbsolutePath());
            genModelURI = URI.createFileURI(genModelPath.toOSString());
          }
          
          Resource resource = resourceSet.getResource(genModelURI, true);
          GenModel referencedGenModel = (GenModel)resource.getContents().get(0);
          for (Iterator j = referencedGenModel.getGenPackages().iterator(); j.hasNext();)
          {
            GenPackage genPackage = (GenPackage)j.next();
            if (ePackageNSURIs.contains(genPackage.getEcorePackage().getNsURI()))
            {
              getModelImporter().getReferencedGenPackages().add(genPackage);
            }
          }
        }
      }
    }
    finally
    {
      monitor.done();
    }
  }
  
  protected void handleQualifiedEPackageName(EPackage ePackage)
  {
    String packageName = ePackage.getName();
    int index = packageName.lastIndexOf(".");
    if (index != -1)
    {
      getModelImporter().getEPackageImportInfo(ePackage).setBasePackage(packageName.substring(0, index));
      ePackage.setName(packageName.substring(index + 1));
    }
  }  

  protected void handleEPackage(EPackage ePackage, boolean generate)
  {
    getModelImporter().getEPackageImportInfo(ePackage).setConvert(generate);
    if (!generate)
    {
      // The referencedEPackages list is used to track the packages for
      // which is necessary to create the stub GenModel.  So if the ePackage
      // is referenced by an existing GenPackage, it doesn't need to be added to
      // referencedEPackages.
      
      for (Iterator i = getModelImporter().getReferencedGenPackages().iterator(); i.hasNext();)
      {
        GenPackage genPackage = (GenPackage)i.next();
        if (genPackage.getEcorePackage() == ePackage || (genPackage.getNSURI() != null && genPackage.getNSURI().equals(ePackage.getNsURI())))
        {
          return;
        }
      }
      
      if (referencedEPackages == null)
      {
        referencedEPackages = new ArrayList();
      }
      referencedEPackages.add(ePackage);
    }
  }

  protected void adjustGenModel(Monitor monitor)
  {
    try
    {
      monitor.beginTask("", 1);

      GenModel genModel = getModelImporter().getGenModel();
      if (editProjectLocationPath != null)
      {
        genModel.setEditDirectory(editProjectLocationPath + "/./" + editFragmentPath + "/.");
      }
      if (editorProjectLocationPath != null)
      {
        genModel.setEditorDirectory(editorProjectLocationPath + "/./" + editorFragmentPath + "/.");
      }
      if (testsProjectLocationPath != null)
      {
        genModel.setTestsDirectory(testsProjectLocationPath + "/./" + testsFragmentPath + "/.");
      }
      if (templatePath != null)
      {
        genModel.setTemplateDirectory(templatePath);
        genModel.setDynamicTemplates(true);
      }
      if (copyright != null)
      {
        genModel.setCopyrightText(copyright);
      }
      if (sdo)
      {
        setSDODefaults(genModel);
      }
    }
    finally
    {
      monitor.done();
    }
  }

  protected void setSDODefaults(GenModel genModel)
  {
    Generator.setSDODefaults(genModel);
  }

  protected void doExecute(Monitor monitor) throws Exception
  {
    try
    {
      monitor.beginTask("", 3);
      getModelImporter().prepareGenModelAndEPackages(CodeGenUtil.createMonitor(monitor, 1));
      handleReferencedEPackages();
      getModelImporter().saveGenModelAndEPackages(CodeGenUtil.createMonitor(monitor, 1));
    }
    finally
    {
      monitor.done();
    }
  }
  
  /**
   * Handles the referencedEPackages contributed by the -refPackage argument option.
   */
  protected void handleReferencedEPackages()
  {
    // Add a dummy GenModel for referenced packages.
    //
    if (referencedEPackages != null && !referencedEPackages.isEmpty())
    {
      ModelImporter modelImporter = getModelImporter();
      GenModel genModel = modelImporter.getGenModel();
      Resource genModelResource = genModel.eResource();

      GenModel referencedGenModel = (GenModel)EcoreUtil.create(genModel.eClass()); 
      genModelResource.getContents().add(referencedGenModel);
      referencedGenModel.initialize(referencedEPackages);
      ImporterUtil.addUniqueGenPackages(genModel.getUsedGenPackages(), referencedGenModel.getGenPackages());
      referencedGenModel.getForeignModel().addAll(genModel.getForeignModel());
      modelImporter.traverseGenPackages(referencedGenModel.getGenPackages());

      for (Iterator i = referencedGenModel.getGenPackages().iterator(); i.hasNext();)
      {
        GenPackage genPackage = (GenPackage)i.next();
        EPackage ePackage = genPackage.getEcorePackage();
        if (ePackage.eResource() == null)
        {
           modelImporter.addToResource(ePackage, genModelResource.getResourceSet());
        }       
        
        // Special case for a reference to Ecore to ensure that flag settings are respected and are set only for Ecore itself.
        //
        if (EcorePackage.eNS_URI.equals(ePackage.getNsURI()))
        {
          if (referencedGenModel.getGenPackages().size() == 1)
          {
            referencedGenModel.setModelName("ecore");
            referencedGenModel.setBooleanFlagsField("eFlags");
            referencedGenModel.setBooleanFlagsReservedBits(8);
          }
          else
          {
            i.remove();
            ImporterUtil.addUniqueGenPackages(referencedGenModel.getUsedGenPackages(), Collections.singletonList(genPackage));
            GenModel ecoreGenModel = (GenModel)EcoreUtil.create(genModel.eClass());
            genModel.eResource().getContents().add(ecoreGenModel);
            ecoreGenModel.getGenPackages().add(genPackage);
            ecoreGenModel.setBooleanFlagsField("eFlags");
            ecoreGenModel.setBooleanFlagsReservedBits(8);
            ecoreGenModel.getForeignModel().addAll(genModel.getForeignModel());
            ecoreGenModel.setModelName("ecore");
          }
        }
        else if (referencedGenModel.getModelName() == null)
        {
          referencedGenModel.setModelName(genPackage.getPackageName());
        }
      }
    }    
  }
}
