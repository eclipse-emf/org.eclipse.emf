/**
 * Copyright (c) 2005-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.importer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.converter.ConverterPlugin;
import org.eclipse.emf.converter.ModelConverter;
import org.eclipse.emf.converter.util.ConverterUtil;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;


/**
 * @since 2.1.0
 */
public abstract class ModelImporter extends ModelConverter
{
  public static final String GENANNOTATION_SOURCE_PREFIX = GenModelPackage.eNS_URI + "/importer/";
  
  public static class EPackageImportInfo extends ModelConverter.EPackageConvertInfo
  {
    protected String basePackage;
    protected String prefix;

    public String getBasePackage()
    {
      return basePackage;
    }

    public void setBasePackage(String basePackage)
    {
      this.basePackage = basePackage;
    }

    public String getEcoreFileName()
    {
      return getConvertData();
    }

    public void setEcoreFileName(String ecoreFileName)
    {
      setConvertData(ecoreFileName);
    }

    public String getPrefix()
    {
      return prefix;
    }

    public void setPrefix(String prefix)
    {
      this.prefix = prefix;
    }
  }
  
  protected List<String> fileExtensions;

  protected IPath originalGenModelPath;
  protected GenModel originalGenModel;

  protected IPath genModelProjectLocation;
  protected IPath genModelContainerPath;
  protected String genModelFileName;
  protected IPath genModelPath;

  protected List<URI> modelLocationURIs;
  protected String modelLocation;

  protected String modelPluginID;
  protected String modelPluginDirectory;
  protected String projectName;

  protected boolean usePlatformURI = true;
  protected IWorkspaceRoot workspaceRoot;
  
  @Override
  public void dispose()
  {
    originalGenModel = null;
    workspaceRoot = null;

    super.dispose();
  }
  
  @Override
  protected String getConverterGenAnnotationSource()
  {
    return GENANNOTATION_SOURCE_PREFIX + getID();
  }
  
  public List<String> getFileExtensions()
  {
    if (fileExtensions == null)
    {
      fileExtensions = new ArrayList<String>();
    }
    return fileExtensions;
  }

  public boolean usePlatformURI()
  {
    return usePlatformURI;
  }

  public void setUsePlatformURI(boolean usePlatformURI)
  {
    this.usePlatformURI = usePlatformURI;
  }

  public void defineOriginalGenModelPath(IPath path) throws DiagnosticException
  {
    if (getOriginalGenModelPath() == null)
    {
      originalGenModelPath = path;
      if (getOriginalGenModelPath() != null)
      {
        URI genModelURI = createFileURI(getOriginalGenModelPath().toString());
        loadOriginalGenModel(genModelURI);
      }
    }
  }

  public IPath getOriginalGenModelPath()
  {
    return originalGenModelPath;
  }

  protected List<EPackage> computeEPackagesBeingReloaded()
  {
    if (getOriginalGenModel() != null)
    {
      List<EPackage> ePackages = new ConverterUtil.EPackageList();
      for (GenPackage genPackage : getOriginalGenModel().getGenPackages())
      {
        EPackage originalEPackage = genPackage.getEcorePackage();
        String nsURI = originalEPackage.getNsURI();
        if (nsURI != null)
        {
          for (EPackage ePackage : getEPackages())
          {
            if (nsURI.equals(ePackage.getNsURI()))
            {
              ePackages.add(ePackage);
              break;
            }
          }
        }
      }
      return ePackages;
    }
    else
    {
      return Collections.emptyList();
    }
  }

  public void setGenModelFileName(String name)
  {
    genModelFileName = name == null || name.trim().length() == 0 ? null : name;
    genModelPath = null;
  }

  public String getGenModelFileName()
  {
    return genModelFileName;
  }
  
  public String computeDefaultGenModelFileName()
  {
    List<URI> modelLocationURIs = getModelLocationURIs();
    if (!modelLocationURIs.isEmpty())
    {
      URI modelURI = modelLocationURIs.get(0);
      String name = URI.decode(modelURI.lastSegment());
      if (name != null)
      {
        int index = name.lastIndexOf('.');
        return (index >= 0 ? name.substring(0, index) : name) + ".genmodel";
      }
    }
    return genModelFileName;
  }

  public Diagnostic checkGenModelFileName()
  {
    String message = null;
    String name = getGenModelFileName();
    if (name == null || name.trim().length() == 0)
    {
      message = ImporterPlugin.INSTANCE.getString("_UI_GeneratorModelFileNameCannotBeEmpty_message");
    }
    else if (!name.endsWith(".genmodel"))
    {
      message = ImporterPlugin.INSTANCE.getString("_UI_GeneratorModelFileNameMustEndWithGenModel_message");
    }

    if (message == null)
    {
      return Diagnostic.OK_INSTANCE;
    }
    else
    {
      return new BasicDiagnostic(Diagnostic.ERROR, ConverterPlugin.ID, ConverterUtil.ACTION_DEFAULT, message, null);
    }
  }

  public Diagnostic checkEcoreModelFileName(String fileName, String packageName)
  {
    String message = null;

    if (fileName == null || fileName.equals(""))
    {
      message = packageName == null ?
        ImporterPlugin.INSTANCE.getString("_UI_EcoreModelFileNameCannotBeEmpty_message") :
        ImporterPlugin.INSTANCE.getString("_UI_EcoreModelFileNameForPackageCannotBeEmpty_message", new Object []{ packageName });
    }
    else if (!fileName.endsWith(".ecore") && !fileName.endsWith(".emof"))
    {
      message = packageName == null ?
        ImporterPlugin.INSTANCE.getString("_UI_EcoreModelFileNameMustEndWithEcore_message") :
        ImporterPlugin.INSTANCE.getString("_UI_EcoreModelFileNameForPackageMustEndWithEcore_message", new Object []{ packageName });
    }
    if (message == null)
    {
      return Diagnostic.OK_INSTANCE;
    }
    else
    {
      return new BasicDiagnostic(Diagnostic.ERROR, ConverterPlugin.ID, ConverterUtil.ACTION_DEFAULT, message, null);
    }
  }

  public void setGenModelProjectLocation(IPath genModelProjectLocation)
  {
    this.genModelProjectLocation = genModelProjectLocation;
  }

  public IPath getGenModelProjectLocation()
  {
    return genModelProjectLocation;
  }
  
  public void setGenModelContainerPath(IPath path)
  {
    genModelContainerPath = path;
    genModelPath = null;
  }

  public IPath getGenModelContainerPath()
  {
    return genModelContainerPath;
  }
  
  public IPath computeGenModelContainerPath(IPath projectPath)
  {
    return projectPath == null ? null : projectPath.append(getGenModelDefaultFolderPath());
  }

  protected IPath getGenModelDefaultFolderPath()
  {
    return new Path("model");
  }

  public IPath getGenModelPath()
  {
    if (genModelPath == null && getGenModelFileName() != null && getGenModelContainerPath() != null)
    {
      genModelPath = getGenModelContainerPath().append(getGenModelFileName());
    }
    return genModelPath;
  }

  @Override
  public GenModel getGenModel()
  {
    if (genModel == null)
    {
      genModel = getOriginalGenModel() == null ? GenModelFactory.eINSTANCE.createGenModel() : getOriginalGenModel().createGenModel();
      genModel.setImporterID(getID());
    }
    return genModel;
  }

  public boolean addGenModelToResource(boolean replace)
  {
    GenModel genModel = getGenModel();
    if (replace || genModel.eResource() == null)
    {
      IPath genModelPath = getGenModelPath();
      if (genModelPath != null)
      {
        URI genModelURI = createFileURI(genModelPath.toString());
        Resource genModelResource = createResourceSet().createResource(genModelURI);
        genModelResource.getContents().add(genModel);
        return true;
      }
    }
    return false;
  }

  public ResourceSet getGenModelResourceSet()
  {
    Resource resource = getGenModel().eResource();
    if (resource == null && addGenModelToResource(false))
    {
      resource = getGenModel().eResource();
    }

    if (resource != null)
    {
      return resource.getResourceSet();
    }
    else
    {
      return null;
    }
  }

  public GenModel getOriginalGenModel()
  {
    return originalGenModel;
  }

  public EPackageImportInfo getEPackageImportInfo(EPackage ePackage)
  {
    return (EPackageImportInfo)getEPackageConvertInfo(ePackage);
  }
  
  @Override
  protected EPackageConvertInfo createEPackageInfo(EPackage ePackage)
  {
    return new EPackageImportInfo();
  }

  protected GenPackage getGenPackage(EPackage ePackage)
  {
    if (getOriginalGenModel() != null && !getOriginalGenModel().getGenPackages().isEmpty())
    {
      for (GenPackage referencedGenPackage : getOriginalGenModel().getGenPackages())
      {
        if (referencedGenPackage.getEcorePackage() != null && 
             (referencedGenPackage.getEcorePackage().getNsURI() == null ? 
                ePackage.getNsURI() == null :
                referencedGenPackage.getEcorePackage().getNsURI().equals(ePackage.getNsURI())))
        {
          return referencedGenPackage;
        }
      }
    }
    return null;
  }

  public void setModelLocation(String location)
  {
    modelLocation = location == null || location.trim().length() == 0 ? null : location;
    modelLocationURIs = null;
  }

  public String getModelLocation()
  {
    return modelLocation;
  }

  public List<URI> getModelLocationURIs()
  {
    if (getModelLocation() == null)
    {
      return Collections.emptyList();
    }
    else if (modelLocationURIs == null)
    {
      modelLocationURIs = new ArrayList<URI>();
      for (StringTokenizer stringTokenizer = new StringTokenizer(getModelLocation()); stringTokenizer.hasMoreTokens();)
      {
        String uri = stringTokenizer.nextToken();
        modelLocationURIs.add(URI.createURI(uri));
      }
    }
    return modelLocationURIs;
  }
  
  public URI getFirstModelLocationURI(boolean resolve)
  {
    List<URI> modelLocationURIs = getModelLocationURIs();
    if (!modelLocationURIs.isEmpty())
    {
      URI modelLocationURI = modelLocationURIs.get(0);
      return resolve ? CommonPlugin.resolve(modelLocationURI) : modelLocationURI;
    }
    return null;
  }

  public void setModelFile(IFile file)
  {
    if (file != null)
    {
      URI modelURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
      setModelLocation(modelURI.toString());
    }
    else
    {
      setModelLocation(null);
    }
  }
    
  @Override
  protected ResourceSet createExternalGenModelResourceSet()
  {
    return getOriginalGenModel() != null ? 
      getOriginalGenModel().eResource().getResourceSet() : 
      super.createExternalGenModelResourceSet();
  }
  
  protected void loadOriginalGenModel(URI genModelURI) throws DiagnosticException
  {
    RuntimeException runtimeException = null;
    ResourceSet resourceSet = createResourceSet();
    Resource resource = null;
    try
    {
      resource = resourceSet.getResource(genModelURI, true);
    }
    catch (RuntimeException exception)
    {
      runtimeException = exception;
      resource = resourceSet.getResource(genModelURI, false);
    }

    if (resource != null && !resource.getContents().isEmpty() && resource.getContents().get(0) instanceof GenModel)
    {
      originalGenModel = (GenModel)resource.getContents().get(0);

      setGenModelFileName(getOriginalGenModelPath().lastSegment());
      setGenModelContainerPath(getOriginalGenModelPath().removeLastSegments(1));
      genModelPath = getOriginalGenModelPath();

      getOriginalGenModel().reconcile();
      for (GenPackage referencedGenPackage : getOriginalGenModel().getUsedGenPackages())
      {
        if (!referencedGenPackage.eIsProxy())
        {
          getReferencedGenPackages().add(referencedGenPackage);
        }
      }

      handleOriginalGenModel();

      Diagnostic diagnostic = getOriginalGenModel().diagnose();
      if (diagnostic.getSeverity() != Diagnostic.OK)
      {
        throw new DiagnosticException(diagnostic);
      }
    }
    else
    {
      Diagnostic diagnostic = runtimeException != null ?
        BasicDiagnostic.toDiagnostic(runtimeException) :
        new BasicDiagnostic(
          Diagnostic.ERROR, 
          ImporterPlugin.ID, 
          0, 
          ImporterPlugin.INSTANCE.getString("_UI_LoadProblem_title"),
          null);        
      throw new DiagnosticException(diagnostic);
    }
  }

  /**
   * Subclasses may overwrite this method to perform actions on the reloaded genmodel.
   * @throws DiagnosticException
   */
  protected void handleOriginalGenModel() throws DiagnosticException
  {
    // Subclasses may override
  }

  public Diagnostic computeEPackages(Monitor monitor) throws Exception
  {
    clearEPackagesCollections();
    Diagnostic diagnostic = doComputeEPackages(monitor);
    presetEPackagesToGenerate();
    return diagnostic;
  }
  
  protected void presetEPackagesToGenerate()
  {
    int size = getEPackages().size(); 
    if (size == 1)
    {
      getEPackageImportInfo(getEPackages().get(0)).setConvert(true);
    }
    else if (size > 1)
    {
      List<EPackage> ePackagesBeingReloaded = computeEPackagesBeingReloaded();
      for (EPackage ePackage : ePackagesBeingReloaded)
      {
        getEPackageImportInfo(ePackage).setConvert(true);
      }
    }    
  }

  protected Diagnostic doComputeEPackages(Monitor monitor) throws Exception
  {
    return Diagnostic.OK_INSTANCE;
  }

  public void adjustEPackages(Monitor monitor)
  {
    TreeIterator<EPackage> ePackagesIterator = 
      new AbstractTreeIterator<EPackage>(getEPackages(), false)
      {
        private static final long serialVersionUID = 1L;

        @SuppressWarnings("unchecked")
        @Override
        protected Iterator<EPackage> getChildren(Object object)
        {
          return object instanceof Collection<?> ? 
            ((Collection<EPackage>)object).iterator() :
            ((EPackage)object).getESubpackages().iterator();  
        }
      };
    
    while (ePackagesIterator.hasNext())
    {
      EPackage ePackage = ePackagesIterator.next();
      adjustEPackage(monitor, ePackage);
    }
    
    makeEPackageConvertDataUnique();
  }

  protected void adjustEPackage(Monitor monitor, EPackage ePackage)
  {
    EPackageImportInfo ePackageInfo = getEPackageImportInfo(ePackage);

    String name = ePackage.getName();
    if (name == null)
    {
      name = "null";
    }
    int index = name.lastIndexOf(".");
    if (index != -1)
    {
      String basePackage = ePackageInfo.getBasePackage();
      String namePackage = name.substring(0, index);
      name = name.substring(index + 1);
      
      if (basePackage != null && !namePackage.startsWith(basePackage))
      {
        namePackage = basePackage + "." + namePackage;
      }

      StringBuffer basePackageName = new StringBuffer();
      for (StringTokenizer stringTokenizer = new StringTokenizer(namePackage, "."); stringTokenizer.hasMoreTokens(); )
      {
        String packageName = stringTokenizer.nextToken();
        basePackageName.append(CodeGenUtil.safeName(packageName));
        if (stringTokenizer.hasMoreTokens())
        {
          basePackageName.append('.');
        }
      }

      ePackageInfo.setBasePackage(basePackageName.toString());
      ePackage.setName(name);
    }

    if (ePackageInfo.getPrefix() == null)
    {
      ePackageInfo.setPrefix(CodeGenUtil.capName(name));
    }

    if (ePackageInfo.getEcoreFileName() == null)
    {
      String ecoreFileName = null;
      GenPackage genPackage = getGenPackage(ePackage);      
      if (genPackage != null)
      {
        String ePackagePath = genPackage.getEcorePackage().eResource().getURI().lastSegment();
        ecoreFileName = URI.decode(ePackagePath);
      }
      else
      {
        ecoreFileName = ePackage.eResource() == null ? name + ".ecore" : ePackage.eResource().getURI().lastSegment();
      }
      ePackageInfo.setEcoreFileName(ecoreFileName);
    }
  }

  protected IWorkspaceRoot getWorkspaceRoot()
  {
    if (workspaceRoot == null)
    {
      workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
    }
    return workspaceRoot;
  }

  public void prepareGenModelAndEPackages(Monitor monitor)
  {
    ResourceSet resourceSet = getGenModelResourceSet();
    
    // We don't need to encode here because the only platform resource URIs in the map are for projects
    // whose names are the same as corresponding plug-in IDs, and hence never need to be encoded
    // (see EcorePlugin.computePlatformResourceToPlatformPluginMap()).
    //
    resourceSet.getURIConverter().getURIMap().remove(URI.createPlatformResourceURI(getModelProjectName() + "/", false));

    // Create resources for all the root EPackages.
    //
    List<EPackage> ePackages = computeEPackagesToConvert();
    for (EPackage ePackage : ePackages)
    {
      addToResource(ePackage, resourceSet);
    }

    List<GenPackage> referencedGenPackages = computeValidReferencedGenPackages(); 

    Map<GenPackage, EPackage> genPackageToReferredEPackage = new LinkedHashMap<GenPackage, EPackage>();
    Map<String, GenPackage> referredEPackageNSURIToGenPackage = new HashMap<String, GenPackage>();
    for (GenPackage genPackage : referencedGenPackages)
    {
      EPackage referredEPackage = getReferredEPackage(genPackage);
      if (referredEPackage != null)
      {
        genPackageToReferredEPackage.put(genPackage, referredEPackage);
        referredEPackageNSURIToGenPackage.put(referredEPackage.getNsURI(), genPackage);
      }
    }
    
    // Create resources for all the referenced EPackages
    // The referencedEPackage is a "local" instance of the realEPackage.  We 
    // will add the former to a resource that has the same URI of the later.
    for (Map.Entry<GenPackage, EPackage> entry : genPackageToReferredEPackage.entrySet())
    {
      GenPackage genPackage = entry.getKey();
      EPackage referredEPackage = entry.getValue();
      EPackage realEPackage = genPackage.getEcorePackage();

      if (referredEPackage != realEPackage)
      {
        EPackage eSuperPackage = realEPackage.getESuperPackage();
        if (eSuperPackage == null)
        {
          URI ecoreURI = realEPackage.eResource().getURI();
          Resource resource = resourceSet.createResource(ecoreURI);
          resource.getContents().add(referredEPackage);
        }
        else
        {
          GenPackage genSuperPackage = referredEPackageNSURIToGenPackage.get(eSuperPackage.getNsURI());
          if (genSuperPackage != null)
          {
            EPackage referredESuperPackage = getReferredEPackage(genSuperPackage);
            referredESuperPackage.getESubpackages().add(referredEPackage);
            referencedGenPackages.remove(genPackage);
          }
        }
      }
    }

    // Initialize the GenModel with all the computed data.
    //
    getGenModel().initialize(ePackages);
    getGenModel().getUsedGenPackages().addAll(referencedGenPackages);
    traverseGenPackages(getGenModel().getGenPackages());
    adjustGenModel(monitor);
    adjustUsedGenPackages();

    // Restore all configured settings from the original.
    //
    getGenModel().reconcile(getOriginalGenModel());
  }
  
  public void addToResource(EPackage ePackage, ResourceSet resourceSet)
  {
    if (ePackage.eResource() == null)
    {
      EPackageImportInfo ePackageInfo = getEPackageImportInfo(ePackage);
      String fileName = ePackageInfo.getEcoreFileName();
      if (fileName != null)
      {
        String baseLocation = getGenModelPath().removeLastSegments(1).toString() + "/";
        String ecoreFileName = baseLocation + fileName;
        URI ecoreURI = createFileURI(ecoreFileName);
        Resource resource = resourceSet.getResource(ecoreURI, false);
        if (resource == null)
        {
          resource = resourceSet.createResource(ecoreURI);
        }
        resource.getContents().add(ePackage);
      }
    }
  }

  public void saveGenModelAndEPackages(Monitor monitor) throws Exception
  {
    String projectName = getModelProjectName();
    IProject project = getWorkspaceRoot().getProject(projectName);
    if (!project.exists())
    {
      Set<GenModel> referencedGenModels = new HashSet<GenModel>();
      for (GenPackage genPackage : getGenModel().getUsedGenPackages())
      {
        referencedGenModels.add(genPackage.getGenModel());
      }
      createProject(monitor, project, referencedGenModels);
    }

    List<Resource> resources = computeResourcesToBeSaved();    
    String readOnlyFiles = ConverterUtil.WorkspaceResourceValidator.validate(resources);
    if (readOnlyFiles != null)
    {
      throw new Exception(ImporterPlugin.INSTANCE.getString("_UI_ReadOnlyFiles_error", new String[]{readOnlyFiles})); 
    }
    
    for (Resource resource : resources)
    {
      resource.save(getGenModelSaveOptions());
    }
  }
  
  protected List<Resource> computeResourcesToBeSaved()
  {
    List<Resource> resources = new UniqueEList.FastCompare<Resource>();
    Resource genModelResource = getGenModel().eResource();
    resources.add(genModelResource);
    for (GenPackage genPackage : getGenModel().getGenPackages())
    {
      resources.add(genPackage.getEcorePackage().eResource());
    }
    
    // Handle application genmodel stub
    //
    for (GenPackage genPackage : getGenModel().getUsedGenPackages())
    {
      if (genPackage.eResource() == genModelResource)
      {
        resources.add(genPackage.getEcorePackage().eResource());
      }
    }
    
    return resources;
  }
  
  protected void createProject(Monitor monitor, IProject project, Collection<GenModel> referencedGenModels)
  {
    IWorkspaceRoot workspaceRoot = getWorkspaceRoot();

    // Determine which projects will need to be referenced.
    //
    List<IProject> referencedModelProjects = new ArrayList<IProject>();
    List<IProject> referencedEditProjects = new ArrayList<IProject>();
    for (GenModel referencedGenModel : referencedGenModels)
    {
      String modelDirectory = referencedGenModel.getModelDirectory();
      if (modelDirectory != null)
      {
        referencedModelProjects.add(workspaceRoot.getProject(new Path(modelDirectory).segment(0)));
        String editDirectory = referencedGenModel.getEditDirectory();
        if (editDirectory != null && !modelDirectory.equals(editDirectory) && !"".equals(editDirectory))
        {
          referencedEditProjects.add(workspaceRoot.getProject(new Path(editDirectory).segment(0)));
        }
      }
    }

    String projectName = project.getName();
    String path = getGenModel().getModelDirectory();
    int index = path.indexOf(projectName);
    if (index >= 0)
    {
      path = path.substring(index);
    }
    char firstChar = path.charAt(0);
    if (firstChar != '/' && firstChar != '\\')
    {
      path = "/" + path;
    }

    // Create the model project.
    //
    
    int style = Generator.EMF_MODEL_PROJECT_STYLE;
    if (getGenModel().hasPluginSupport())
    {
      style |= Generator.EMF_PLUGIN_PROJECT_STYLE;
    }
    if (getGenModel().hasXMLDependency())
    {
      style |= Generator.EMF_XML_PROJECT_STYLE;
    }
    
    List<IProject> referencedProjects = new ArrayList<IProject>(referencedModelProjects);
    Generator.createEMFProject
      (new Path(path),
       getGenModelProjectLocation(),
       referencedProjects,
       monitor,
       style);
  }

  protected void adjustGenModel(Monitor monitor)
  {
    String modelName = URI.decode(getGenModelPath().removeFileExtension().lastSegment());
    int index = modelName.lastIndexOf('.');
    if (index != -1)
    {
      modelName = modelName.substring(0, index);
    }
    modelName = CodeGenUtil.capName(modelName); 

    GenModel genModel = getGenModel();
    genModel.setModelName(modelName);
    genModel.setModelPluginID(getModelPluginID());
    genModel.setModelDirectory(getModelPluginDirectory());
    genModel.reconcile();

    if (getOriginalGenModel() == null)
    {
      URI genModelURI = genModel.eResource().getURI();
      if (genModelURI.isPlatformResource())
      {
        IFile genModelFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(genModelURI.toPlatformString(true)));
        IProject project = genModelFile.getProject();
        String complianceLevel = CodeGenUtil.EclipseUtil.getJavaComplianceLevel(project);
        if ("1.5".equals(complianceLevel))
        {
          genModel.setComplianceLevel(GenJDKLevel.JDK50_LITERAL);
        }
        else if ("1.6".equals(complianceLevel))
        {
          genModel.setComplianceLevel(GenJDKLevel.JDK60_LITERAL);
        }
        else if ("1.7".equals(complianceLevel))
        {
          genModel.setComplianceLevel(GenJDKLevel.JDK70_LITERAL);
        }
        else if ("1.4".equals(complianceLevel))
        {
          genModel.setComplianceLevel(GenJDKLevel.JDK14_LITERAL);
        }
      }
    }
  }
  
  protected void adjustUsedGenPackages()
  {
    if (getOriginalGenModel() != null && !getOriginalGenModel().getUsedGenPackages().isEmpty())
    {
      GenModel genModel = getGenModel();
      List<GenPackage> usedGenPackages = new ArrayList<GenPackage>(genModel.getUsedGenPackages());
      usedGenPackages.removeAll(getOriginalGenModel().getUsedGenPackages());
      if (!usedGenPackages.isEmpty())
      {
        Map<String, GenPackage> nsURIOriginalUsedGenPackageMap = new HashMap<String, GenPackage>();
        for (GenPackage genPackage : getOriginalGenModel().getUsedGenPackages())
        {
          nsURIOriginalUsedGenPackageMap.put(genPackage.getNSURI(), genPackage);
        }
        
        for (GenPackage genPackage : usedGenPackages)
        {
          GenPackage originalUsedGenPackage = nsURIOriginalUsedGenPackageMap.get(genPackage.getNSURI());
          if (originalUsedGenPackage != null)
          {
            genModel.getUsedGenPackages().remove(originalUsedGenPackage);
          }
        }
      }
    }
  }

  @Override
  protected boolean canConvert(EPackage ePackage)
  {
    return super.canConvert(ePackage) && getEPackageImportInfo(ePackage).getEcoreFileName() != null;
  }

  public void traverseGenPackages(List<GenPackage> genPackages)
  {
    for (GenPackage genPackage : genPackages)
    {
      EPackage ePackage = genPackage.getEcorePackage();
      EPackageImportInfo ePackageInfo = getEPackageImportInfo(ePackage);

      genPackage.setBasePackage(ePackageInfo.getBasePackage());
      genPackage.setPrefix(ePackageInfo.getPrefix());

      adjustGenPackageDuringTraverse(genPackage);
      traverseGenPackages(genPackage.getNestedGenPackages());
    }
  }

  protected void adjustGenPackageDuringTraverse(GenPackage genPackage)
  {
    // Subclasses may override
  }

  protected URI makeRelative(URI uri, URI relativeTo)
  {
    if ("file".equals(uri.scheme()))
    {
      IFile file = getWorkspaceRoot().getFileForLocation(new Path(uri.toFileString()));
      if (file != null)
      {
        URI platformURI = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
        URI result = platformURI.deresolve(relativeTo, false, true, false);
        if (result.isRelative())
        {
          return result;
        }
      }
    }

    URI result = uri.deresolve(relativeTo, true, true, false);
    if (result.isRelative())
    {
      return result;
    }

    return uri;
  }

  protected URI makeAbsolute(URI uri, URI relativeTo)
  {
    if (uri.isRelative())
    {
      return uri.resolve(relativeTo);
    }
    return uri;
  }

  public URI createFileURI(String pathName)
  {
    return usePlatformURI() ? URI.createPlatformResourceURI(pathName, true) : URI.createFileURI(pathName);
  }

  public String getModelPluginID()
  {
    return modelPluginID == null ? CodeGenUtil.validPluginID(getModelProjectName()) : modelPluginID;
  }

  public void setModelPluginID(String modelPluginID)
  {
    this.modelPluginID = modelPluginID;
  }

  public String getModelPluginDirectory()
  {
    if (modelPluginDirectory == null)
    {
      String result = getModelProjectName();
      if (result != null)
      {
        if (result.charAt(0) != '/')
        {
          result = "/" + result;
        }
        return result + "/src";
      }
    }
    return modelPluginDirectory;
  }

  public void setModelPluginDirectory(String modelPluginDirectory)
  {
    this.modelPluginDirectory = modelPluginDirectory;
  }
  
  public void setModelProjectName(String projectName)
  {
    this.projectName = projectName;
  }

  public String getModelProjectName()
  {
    return projectName != null ? projectName : computeModelProjectName();
  }
  
  protected String computeModelProjectName()
  {
    IPath path = getGenModelProjectLocation();
    if (path != null)
    {
      return URI.decode(path.lastSegment().toString());
    }

    IPath genModelPath = getGenModelPath();
    if (genModelPath != null)
    {
      return URI.decode(genModelPath.segment(0).toString());
    }

    return null;
  }
      
  protected Map<?, ?> getEcoreSaveOptions()
  {
    return Collections.emptyMap();
  }
}
