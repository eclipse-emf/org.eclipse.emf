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
 * $Id: ModelExporter.java,v 1.16 2007/06/15 21:57:55 emerks Exp $
 */
package org.eclipse.emf.exporter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.converter.ConverterPlugin;
import org.eclipse.emf.converter.ModelConverter;
import org.eclipse.emf.converter.util.ConverterUtil;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.exporter.util.ExporterUtil;

/**
 * @since 2.2.0
 */
public abstract class ModelExporter extends ModelConverter
{
  public static final String GENANNOTATION_SOURCE_PREFIX = GenModelPackage.eNS_URI + "/exporter/";
  public static final String GENANNOTATION_KEY_DIRECTORY_URI = "directoryURI";
  // Nested GenAnnotations
  public static final String GENANNOTATION_SOURCE_SELECTED_EPACKAGES = "selectedPackages";
  public static final String GENANNOTATION_SOURCE_SELECTED_REFERENCES = "selectedReferencedPackages";
  
  public static class EPackageExportInfo extends ModelConverter.EPackageConvertInfo
  {
    public String getArtifactLocation()
    {
      return getConvertData();
    }

    public void setArtifactLocation(String artifactLocation)
    {
      setConvertData(artifactLocation);
    }
  }
    
  public static class ReferencedGenPackageExportInfo extends ModelConverter.ReferencedGenPackageConvertInfo
  {
    protected String modelExporterID;
    protected URI artifactURI;
    protected Map<String, List<URI>> nsURIToLocalArtifactURIs;

    public String getModelExporterID()
    {
      return modelExporterID;
    }

    public void setModelExporterID(String modelExporterID)
    {
      this.modelExporterID = modelExporterID;
    }

    public URI getArtifactURI()
    {
      return artifactURI;
    }

    public void setArtifactURI(URI artifactURI)
    {
      this.artifactURI = artifactURI;
    }
    
    public Map<String, List<URI>> getNSURIToLocalArtifactURIs()
    {
      if (nsURIToLocalArtifactURIs == null)
      {
        nsURIToLocalArtifactURIs = new HashMap<String, List<URI>>();
      }
      return nsURIToLocalArtifactURIs;
    }
  }
  
  public static class ExportData
  {
    public Map<GenPackage, URI> genPackageToArtifactURI;
    public Map<GenPackage, List<GenPackage>> genPackageToReferencedGenPackages;
    public Map<GenPackage, URI> referencedGenPackagesToArtifactURI;
  }
  
  protected static class GenPackagesTreeIterator extends AbstractTreeIterator<GenPackage>
  {
    private static final long serialVersionUID = 1L;

    public GenPackagesTreeIterator(GenModel genModel)
    {
      this(genModel.getGenPackages());
    }

    public GenPackagesTreeIterator(Collection<GenPackage> genPackages)
    {
      super(genPackages, false);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Iterator<GenPackage> getChildren(Object object)
    {
      return object instanceof Collection ? 
        ((Collection<GenPackage>)object).iterator() :
        ((GenPackage)object).getNestedGenPackages().iterator();
    }
  }
  
  protected Map<EPackage, GenPackage> ePackageToGenPackageMap;
  protected URI directoryURI;
  protected Map<GenBase, EMap<String, String>> genBaseToDetailsMap;
  
  protected boolean saveExporter = false;
  protected boolean saveEPackageArtifactURI = false;
  
  @Override
  public void dispose()
  {
    if (genBaseToDetailsMap != null)
    {
      genBaseToDetailsMap.clear();
      genBaseToDetailsMap = null;
    }
    
    if (ePackageToGenPackageMap != null)
    {
      ePackageToGenPackageMap.clear();
      ePackageToGenPackageMap = null;
    }
    
    super.dispose();
  }
  
  @Override
  protected String getConverterGenAnnotationSource()
  {
    return GENANNOTATION_SOURCE_PREFIX + getID();
  }    
  
  protected Map<EPackage, GenPackage> getEPackageToGenPackageMap()
  {
    if (ePackageToGenPackageMap == null)
    {
      ePackageToGenPackageMap = new HashMap<EPackage, GenPackage>();
    }
    return ePackageToGenPackageMap;
  }

  protected Map<GenBase, EMap<String, String>> getGenBaseToGenAnnotationDetailsMap()
  {
    if (genBaseToDetailsMap == null)
    {
      genBaseToDetailsMap = new HashMap<GenBase, EMap<String, String>>();
    }
    return genBaseToDetailsMap;
  }
  
  /**
   * Returns the {@link GenBase} object's {@link GenAnnotation} Details map
   * associated with this Model Exporter or {@link ECollections#EMPTY_EMAP}
   */
  protected EMap<String, String> getExporterGenAnnotationDetails(GenBase genBase)
  {
    EMap<String, String> eMap = getGenBaseToGenAnnotationDetailsMap().get(genBase);
    if (eMap == null)
    {
      GenAnnotation genAnnotation = genBase.getGenAnnotation(getConverterGenAnnotationSource());
      if (genAnnotation != null)
      {
        eMap = genAnnotation.getDetails();
        getGenBaseToGenAnnotationDetailsMap().put(genBase, eMap);
      }
      else
      {
        eMap = ECollections.emptyEMap();
      }
    }
    return eMap;
  }
  
  protected List<GenAnnotation> getExporterNestedGenAnnotations(GenBase genBase)
  {
    if (genBase != null)
    {
      GenAnnotation exportAnnotation = genBase.getGenAnnotation(getConverterGenAnnotationSource());
      if (exportAnnotation != null && !exportAnnotation.getGenAnnotations().isEmpty())
      {
        List<GenAnnotation> nestedAnnotations = exportAnnotation.getGenAnnotations();
        if (nestedAnnotations != null)
        {
          return nestedAnnotations;
        }
      }
    }
    return Collections.emptyList();    
  }
    
  protected EMap<String, String> getExporterNestedGenAnnotationDetails(GenBase genBase, String nestedGenAnnotationSource)
  {
    GenAnnotation genAnnotation = genBase.getGenAnnotation(getConverterGenAnnotationSource());
    if (genAnnotation != null)
    {
      GenAnnotation nestedGenAnnotation = genAnnotation.getGenAnnotation(nestedGenAnnotationSource);
      if (nestedGenAnnotation != null)
      {
        return nestedGenAnnotation.getDetails();
      }
    }
    return ECollections.emptyEMap();
  }
  
  public List<URI> getArtifactURIs(GenPackage genPackage)
  {
    List<GenAnnotation> nestedAnnotations = getExporterNestedGenAnnotations(genPackage);
    if (!nestedAnnotations.isEmpty())
    {
      List<URI> uris = new UniqueEList<URI>(nestedAnnotations.size());
      URI genModelURI = genPackage.getGenModel().eResource() != null ?
        genPackage.getGenModel().eResource().getURI() :
        null;

      for (GenAnnotation uriAnnotation : nestedAnnotations)
      {
        URI uri = URI.createURI(uriAnnotation.getSource());
        if (genModelURI != null)
        {
          uri = uri.resolve(genModelURI);
        }
        uris.add(uri);          
      }
      
      for (GenPackage referencedGenPackage : getReferencedGenPackages())
      {
        ReferencedGenPackageExportInfo genPackageInfo = getReferencedGenPackageExportInfo(referencedGenPackage);
        if (genPackageInfo.getArtifactURI() != null)
        {
          List<URI> localURIs = genPackageInfo.getNSURIToLocalArtifactURIs().get(genPackage.getNSURI());
          if (localURIs != null)
          {
            uris.addAll(localURIs);
          }
        }
      }
      
      return uris; 
    }
    return Collections.emptyList();
  }
    
  public void setGenModel(GenModel genModel) throws DiagnosticException
  {
    dispose();
    this.genModel = genModel;
    genModel = getGenModel();
    
    if (genModel != null)
    {
      genModel.reconcile();
      genModel.setValidateModel(true);
      Diagnostic diagnostic = genModel.diagnose();
      if (diagnostic.getSeverity() != Diagnostic.OK)
      {
        throw new DiagnosticException(diagnostic);
      }
      
      if (getDirectoryURI() == null)
      {
        String location = getExporterGenAnnotationDetails(genModel).get(GENANNOTATION_KEY_DIRECTORY_URI);
        setDirectoryURI(location);
      }
      if (genModel.eResource() != null && getDirectoryURI() != null)
      {
        setDirectoryURI(getDirectoryURI().resolve(genModel.eResource().getURI()));
      }      
      
      for (Iterator<GenPackage> i = new GenPackagesTreeIterator(genModel); i.hasNext();)
      {
        GenPackage genPackage = i.next();
        if (isValidEPackage(genPackage))
        {
          EPackage ePackage = genPackage.getEcorePackage();
          
          getEPackageToGenPackageMap().put(ePackage, genPackage); 
          getEPackages().add(genPackage.getEcorePackage());
        }
      }
      
      List<GenPackage> exporterUsedGenPackages = new ConverterUtil.GenPackageList(genModel.getUsedGenPackages());
      GenAnnotation genModelAnnotation = genModel.getGenAnnotation(getConverterGenAnnotationSource());
      if (genModelAnnotation != null)
      {
        GenAnnotation referencedPackagesAnnotation = genModelAnnotation.getGenAnnotation(GENANNOTATION_SOURCE_SELECTED_REFERENCES);
        if (referencedPackagesAnnotation != null)
        {
          for (Object o : referencedPackagesAnnotation.getReferences())
          {
            if (o instanceof GenPackage)
            {
              exporterUsedGenPackages.add((GenPackage)o);              
            }
          }
        }
      }
      
      for (Iterator<GenPackage> i = new GenPackagesTreeIterator(exporterUsedGenPackages); i.hasNext();)
      {
        GenPackage genPackage = i.next();
        if (isValidEPackage(genPackage))
        {
          EPackage ePackage = genPackage.getEcorePackage();
          getEPackageToGenPackageMap().put(ePackage, genPackage);
          if (isValidReferencedGenPackage(genPackage))
          {
            getReferencedGenPackages().add(genPackage);
          }
          getEPackages().add(ePackage);
        }
      }      
    }
    
    adjustGenModel();
  }
  
  protected void adjustGenModel()
  {
    boolean defaultLocationSet = false;
    for (Map.Entry<EPackage, EPackageConvertInfo> entry : getEPackageToInfoMap().entrySet())
    {
      EPackage ePackage = entry.getKey();
      EPackageExportInfo packageInfo = (EPackageExportInfo)entry.getValue();
      if (packageInfo.getArtifactLocation() == null)
      {
        packageInfo.setArtifactLocation(getDefaultArtifactLocation(ePackage));
        defaultLocationSet |= packageInfo.getArtifactLocation() == null;
      }
    }
    if (defaultLocationSet)
    {
      makeEPackageConvertDataUnique();
    }
  }
  
  protected String getDefaultArtifactLocation(EPackage ePackage)
  {
    return null;
  }
  
  /**
   * Returns whether the EPackage associated with the specified GenPackage should be added 
   * to the ePackage list.  
   */
  protected boolean isValidEPackage(GenPackage genPackage)
  {
    return genPackage.getEcorePackage() != null && genPackage.hasClassifiers();
  }
  
  /**
   * Returns whether the specified GenPackage should be added to the referenced 
   * GenPackage list.  
   */  
  protected boolean isValidReferencedGenPackage(GenPackage genPackage)
  {
    return genPackage.getEcorePackage() != null && genPackage.hasClassifiers();
  }

  @Override
  protected List<EPackage> createEPackagesList()
  {
    return 
      new ConverterUtil.EPackageList()
      {
        private static final long serialVersionUID = 1L;

        @Override
        protected void didAdd(int index, EPackage newObject)
        {
          EPackageExportInfo packageInfo = getEPackageExportInfo(newObject);
          if (packageInfo.getArtifactLocation() == null)
          {
            String location = getExporterNestedGenAnnotationDetails(getGenModel(), GENANNOTATION_SOURCE_SELECTED_EPACKAGES).get(newObject.getNsURI());
            if (location != null)
            {
              packageInfo.setConvert(true);
              packageInfo.setArtifactLocation(location);
            }
          }
        }
      };
  }
    
  @Override
  protected List<GenPackage> createReferencedGenPackagesList()
  {
    return 
      new ConverterUtil.GenPackageList()
      {
        private static final long serialVersionUID = 1L;

        @Override
        protected void didAdd(int index, GenPackage newObject)
        {
          URI genModelURI = newObject.getGenModel().eResource() != null ?
            newObject.getGenModel().eResource().getURI() :
            null;
          
          String stringURI = getExporterNestedGenAnnotationDetails(getGenModel(), GENANNOTATION_SOURCE_SELECTED_REFERENCES).get(newObject.getNSURI());
          if (stringURI != null)
          {
            ReferencedGenPackageExportInfo genPackageInfo = getReferencedGenPackageExportInfo(newObject);
            if (genPackageInfo.getArtifactURI() == null)
            {
              genPackageInfo.setModelExporterID(getID());
              URI uri = URI.createURI(stringURI);
              if (genModelURI != null)
              {
                uri = uri.resolve(genModelURI);
              }
              genPackageInfo.setArtifactURI(uri);
            }
          }
          
          List<GenAnnotation> uriAnnotations = getExporterNestedGenAnnotations(newObject);
          if (!uriAnnotations.isEmpty())
          {
            ReferencedGenPackageExportInfo genPackageInfo = null;
            for (GenAnnotation uriAnnotation : uriAnnotations)
            {
              if (!uriAnnotation.getDetails().isEmpty())
              {
                if (genPackageInfo == null)
                {
                  genPackageInfo = getReferencedGenPackageExportInfo(newObject);
                }
                for (Map.Entry<String, String> entry : uriAnnotation.getDetails())
                {
                  String packageNSURI = entry.getKey();
                  String stringArtifactURIs = entry.getValue();
                  if (stringArtifactURIs != null)
                  {
                    String[] stringURIs = stringArtifactURIs.split(" ");
  
                    List<URI> uris = genPackageInfo.getNSURIToLocalArtifactURIs().get(packageNSURI);
                    if (uris == null)
                    {
                      uris = new ArrayList<URI>(stringURIs.length);
                      genPackageInfo.getNSURIToLocalArtifactURIs().put(packageNSURI, uris);
                    }
                    for (int k = 0; k < stringURIs.length; k++)
                    {
                      URI uri = URI.createURI(stringURIs[k]);
                      if (genModelURI != null)
                      {
                        uri = uri.resolve(genModelURI);
                      }
                      uris.add(uri);
                    }                    
                  }
                }
              }
            }
          }
        }      
      };
  }
  
  public void loadGenModel(URI uri) throws DiagnosticException
  {
    setGenModel(uri == null ? null :
      (GenModel)createResourceSet().getResource(uri, true).getContents().get(0));
  }
  
  public EPackageExportInfo getEPackageExportInfo(EPackage ePackage)
  {
    return (EPackageExportInfo)getEPackageConvertInfo(ePackage);
  }
  
  @Override
  protected EPackageConvertInfo createEPackageInfo(EPackage ePackage)
  {
    return new EPackageExportInfo();
  }
  
  public ReferencedGenPackageExportInfo getReferencedGenPackageExportInfo(GenPackage genPackage)
  {
    return (ReferencedGenPackageExportInfo)getReferenceGenPackageConvertInfo(genPackage);
  }

  @Override
  protected ReferencedGenPackageConvertInfo createGenPackageConvertInfo(GenPackage genPackage)
  {
    ReferencedGenPackageExportInfo genPackageInfo = new ReferencedGenPackageExportInfo();
    genPackageInfo.setValidReference(!getExporterNestedGenAnnotations(genPackage).isEmpty());
    return genPackageInfo;
  }
  
  @Override
  protected ReferencedEPackageFilter createReferencedEPackageFilterToConvert()
  {
    return new ReferencedEPackageFilter()
    {
      @Override
      protected boolean isValidReference(GenPackage genPackage)
      {
        return super.isValidReference(genPackage) && 
          getReferencedGenPackageExportInfo(genPackage).getArtifactURI() != null;
      }
    };
  }
  
  @Override
  protected boolean canConvert(EPackage ePackage)
  {
    return super.canConvert(ePackage) && getEPackageExportInfo(ePackage).getArtifactLocation() != null;
  }
  
  /**
   * <p>Creates the {@link ExportData} instance and delegates the export work to
   * the {@link #doExport(Monitor, ModelExporter.ExportData)} method.</p>
   * <p>The {@link Diagnostic} returned by this method should be used to provide
   * the user some information regarding a <b>successful</b> export.  If the
   * export fails, an exception should be thrown.<p>
   * @param monitor
   * @return {@link Diagnostic}
   * @throws Exception
   */  
  public Diagnostic export(Monitor monitor) throws Exception
  {
    Map<String, GenPackage> nsURIToReferencedGenPackage = null;
    Map<GenPackage, URI> referencedGenPackageToArtifactURI = null; 
    if (referencedGenPackageToInfoMap != null)
    {
      referencedGenPackageToArtifactURI = new HashMap<GenPackage, URI>(referencedGenPackageToInfoMap.size());
      nsURIToReferencedGenPackage = new HashMap<String, GenPackage>(referencedGenPackageToInfoMap.size());
      List<GenPackage> referencedGenPackages = computeValidReferencedGenPackages();
      for (GenPackage genPackage : referencedGenPackages)
      {
        URI artifactURI = getReferencedGenPackageExportInfo(genPackage).getArtifactURI();
        referencedGenPackageToArtifactURI.put(genPackage, artifactURI);
        nsURIToReferencedGenPackage.put(genPackage.getNSURI(), genPackage);
      }
    }
    
    Map<GenPackage, URI> genPackageToArtifactURI = null;
    Map<GenPackage, List<GenPackage>> genPackageToReferencedGenPackages = null;
    if (ePackageToInfoMap != null && ePackageToGenPackageMap != null)
    {
      genPackageToArtifactURI = new HashMap<GenPackage, URI>(ePackageToInfoMap.size());
      genPackageToReferencedGenPackages = new HashMap<GenPackage, List<GenPackage>>(ePackageToInfoMap.size());
      
      List<EPackage> ePackages = computeEPackagesToConvert();
      for (EPackage ePackage : ePackages)
      {
        GenPackage genPackage = ePackageToGenPackageMap.get(ePackage);
        if (genPackage != null)
        {
          if (nsURIToReferencedGenPackage != null)
          {
            List<EPackage> requiredEPackages = ConverterUtil.computeRequiredPackages(ePackage);
            List<GenPackage> requiredGenPackages = new ConverterUtil.GenPackageList(requiredEPackages.size());
            for (EPackage requiredEPackage : requiredEPackages)
            {
              GenPackage referencedGenPackage = nsURIToReferencedGenPackage.get(requiredEPackage.getNsURI());
              if (referencedGenPackage != null)
              {
                requiredGenPackages.add(referencedGenPackage); 
              }
            } 
            if (!requiredGenPackages.isEmpty())
            {
              genPackageToReferencedGenPackages.put(genPackage, requiredGenPackages);
            }
          }
          
          EPackageExportInfo exportInfo = getEPackageExportInfo(ePackage);
          String artifactLocation = exportInfo.getArtifactLocation();
          if (artifactLocation != null)
          {
            genPackageToArtifactURI.put(genPackage, computeArtifactURI(artifactLocation));
          }          
        }
      }
    }
    
    if (genPackageToArtifactURI == null || genPackageToArtifactURI.isEmpty())
    {
      throw new DiagnosticException(new BasicDiagnostic(Diagnostic.ERROR, ExporterPlugin.ID, 0, ExporterPlugin.INSTANCE.getString("_UI_NoPackagesToExport_error"), null));
    }
    else
    {
      ExportData exportData = new ExportData();
      exportData.genPackageToArtifactURI = genPackageToArtifactURI;
      exportData.referencedGenPackagesToArtifactURI = referencedGenPackageToArtifactURI != null && !referencedGenPackageToArtifactURI.isEmpty() ? 
        referencedGenPackageToArtifactURI : 
        Collections.<GenPackage, URI>emptyMap();
      exportData.genPackageToReferencedGenPackages = genPackageToReferencedGenPackages != null && !genPackageToReferencedGenPackages.isEmpty() ? 
        genPackageToReferencedGenPackages : 
        Collections.<GenPackage, List<GenPackage>>emptyMap();
      
      return doExport(monitor, exportData);
    }
  }
  
  /**
   * <p>Subclasses should overwrite this method, adding the code that performs the
   * actions required to export the model.</p>
   * <p>The {@link Diagnostic} returned by this method should be used to provide
   * the user some information regarding a <b>successfull</b> export.  If the
   * export fails, an exception should be thrown.<p>
   * @param monitor
   * @return {@link Diagnostic}
   * @throws Exception
   */  
  protected Diagnostic doExport(Monitor monitor, ExportData exportData) throws Exception
  {  
    return Diagnostic.OK_INSTANCE;
  }
  
  public Diagnostic checkEPackageArtifactLocation(String location, String packageName)
  {
    String message = null;
    if (location == null || location.equals(""))
    {
      message = packageName == null ?
        ExporterPlugin.INSTANCE.getString("_UI_ArtifactFileNameCannotBeEmpty_message") :
        ExporterPlugin.INSTANCE.getString("_UI_ArtifactFileNameForPackageCannotBeEmpty_message", new Object []{packageName});
    }
    else
    {
      message = doCheckEPackageArtifactLocation(location, packageName);
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
  
  protected String doCheckEPackageArtifactLocation(String location, String packageName)
  {
    return null;
  }
  
  public URI getDirectoryURI()
  {
    return directoryURI;
  }
  
  public void setDirectoryURI(URI directoryURI)
  {
    this.directoryURI = directoryURI;
  }
  
  public void setDirectoryURI(String location)
  {
    directoryURI = createEncodedURI(location);
  }
  
  /**
   * Returns the artifact URI for a given artifact location.  The
   * URI is resolved against the directory URI. 
   */
  protected URI computeArtifactURI(String location)
  {
    URI artifactURI = createEncodedURI(location);
    URI directoryURI = getDirectoryURI();
    if (directoryURI != null)
    {
      artifactURI = artifactURI.resolve(directoryURI);
    }
        
    return artifactURI;
  }
    
  protected URI createEncodedURI(String location)
  {
    return location != null ?
      URI.createURI(location, true, URI.FRAGMENT_NONE) :
      null;
  }
  
  public Diagnostic validateDirectoryURI(String uri)
  {
    if (uri != null && uri.length() > 0 &&  !uri.endsWith("/"))
    {
      return new BasicDiagnostic(Diagnostic.ERROR, 
        ConverterPlugin.ID, ConverterUtil.ACTION_MESSAGE_SET_ERROR, 
        ExporterPlugin.INSTANCE.getString("_UI_DirectoryURI_error"), null);      
    }
    else
    {
      return Diagnostic.OK_INSTANCE;
    }
  }

  public boolean isSaveEPackageArtifactURI()
  {
    return saveEPackageArtifactURI;
  }

  public void setSaveEPackageArtifactURI(boolean saveEPackageArtifactURI)
  {
    this.saveEPackageArtifactURI = saveEPackageArtifactURI;
  }

  public boolean isSaveExporter()
  {
    return saveExporter;
  }

  public void setSaveExporter(boolean saveExporter)
  {
    this.saveExporter = saveExporter;
  }

  /**
   * Saves this exporters settings in the selected GenModel.
   */
  public void save() throws Exception
  {
    Resource genModelResource = getGenModel().eResource();
    
    boolean changed = false;
    if (isSaveExporter())
    {
      changed |= saveExporter();
    }
    if (isSaveEPackageArtifactURI())
    {
      changed |= saveEPackageArtifactURI();
    }
    changed |= genModelResource.isModified(); 
    
    if (changed)
    {
      if (EMFPlugin.IS_ECLIPSE_RUNNING)
      {
        String readOnlyFiles = ConverterUtil.WorkspaceResourceValidator.validate(Collections.singletonList(genModelResource));
        if (readOnlyFiles != null)
        {
          throw new Exception(ExporterPlugin.INSTANCE.getString("_UI_ReadOnlyFiles_error", new String[]{readOnlyFiles})); 
        }        
      }
      
      genModelResource.save(getGenModelSaveOptions());
    }
  }

  /**
   * Saves this exporter settings.
   * @return Whether this method has changed the GenModel instance
   */
  protected boolean saveExporter()
  {
    boolean changed = false;
    GenAnnotation genModelAnnotation = getGenModel().getGenAnnotation(getConverterGenAnnotationSource());
    URI directoryURI = getDirectoryURI();
    if (directoryURI == null)
    {
      if (genModelAnnotation != null)
      {
        changed |= genModelAnnotation.getDetails().remove(GENANNOTATION_KEY_DIRECTORY_URI);
      }
    }
    else
    {
      if (getGenModel().eResource() != null)
      {
        directoryURI = directoryURI.deresolve(getGenModel().eResource().getURI());
      }
      
      if (genModelAnnotation == null)
      {
        genModelAnnotation = ExporterUtil.findOrCreateGenAnnotation(getGenModel(), getConverterGenAnnotationSource());
        changed = true;
      }
      
      String stringURI = directoryURI.toString();
      Object oldValue = genModelAnnotation.getDetails().put(GENANNOTATION_KEY_DIRECTORY_URI, stringURI);
      changed |= !stringURI.equals(oldValue);
    }
    
    List<EPackage> ePackages = computeEPackagesToConvert();
    if (ePackages.isEmpty())
    {
      if (genModelAnnotation != null)
      {
        GenAnnotation nestedGenAnnotation = genModelAnnotation.getGenAnnotation(GENANNOTATION_SOURCE_SELECTED_EPACKAGES);
        if (nestedGenAnnotation != null)
        {
          genModelAnnotation.getGenAnnotations().remove(nestedGenAnnotation);
          changed = true;
        }
      }      
    }
    else
    {
      if (genModelAnnotation == null)
      {
        genModelAnnotation = ExporterUtil.findOrCreateGenAnnotation(getGenModel(), getConverterGenAnnotationSource());
        changed = true;
      }
      GenAnnotation nestedGenAnnotation = ExporterUtil.findOrCreateGenAnnotation(genModelAnnotation, GENANNOTATION_SOURCE_SELECTED_EPACKAGES);
      Set<String> nsURIs = new HashSet<String>();
      for (EPackage ePackage : ePackages)
      {
        String location = getEPackageExportInfo(ePackage).getArtifactLocation();
        if (location != null)
        {
          nsURIs.add(ePackage.getNsURI());
          Object oldValue = nestedGenAnnotation.getDetails().put(ePackage.getNsURI(), location);
          changed |= !location.equals(oldValue);
        }
      }
      changed |= nestedGenAnnotation.getDetails().keySet().retainAll(nsURIs);
    }
    
    List<GenPackage> referencedGenPackages = computeValidReferencedGenPackages();
    if (referencedGenPackages.isEmpty())
    {
      if (genModelAnnotation != null)
      {
        GenAnnotation nestedGenAnnotation = genModelAnnotation.getGenAnnotation(GENANNOTATION_SOURCE_SELECTED_REFERENCES);
        if (nestedGenAnnotation != null)
        {
          changed |= genModelAnnotation.getGenAnnotations().remove(nestedGenAnnotation);
        }
      }      
    }
    else
    {
      if (genModelAnnotation == null)
      {
        genModelAnnotation = ExporterUtil.findOrCreateGenAnnotation(getGenModel(), getConverterGenAnnotationSource());
        changed = true;
      }      
      
      GenAnnotation nestedGenAnnotation = ExporterUtil.findOrCreateGenAnnotation(genModelAnnotation, GENANNOTATION_SOURCE_SELECTED_REFERENCES);
      Set<String> nsURIs = new HashSet<String>();
      for (GenPackage genPackage : referencedGenPackages)
      {
        nsURIs.add(genPackage.getNSURI());
        URI uri = getReferencedGenPackageExportInfo(genPackage).getArtifactURI();
        if (genPackage.getGenModel().eResource() != null)
        {
          uri = uri.deresolve(genPackage.getGenModel().eResource().getURI());
        }
        String stringURI = uri.toString();
        Object oldValue = nestedGenAnnotation.getDetails().put(genPackage.getNSURI(), stringURI);
        changed |= !stringURI.equals(oldValue);
        
        if (getGenModel().getUsedGenPackages().contains(genPackage))
        {
          changed |= nestedGenAnnotation.getReferences().remove(genPackage);
        }
        else
        {
          changed |= nestedGenAnnotation.getReferences().add(genPackage);
        }
      }
      changed |= nestedGenAnnotation.getDetails().keySet().retainAll(nsURIs);
    }
    
    return changed;
  }
  
  /**
   * Saves the exported artifact URI as annotations on the respective GenPackage.
   * @return Whether this method has changed the GenModel instance
   */
  protected boolean saveEPackageArtifactURI()
  {
    List<GenPackage> localGenPackages = new ConverterUtil.GenPackageList();
    List<EPackage> externalEPackages = new ConverterUtil.EPackageList();

    boolean isLocalGePackage = false;
    List<EPackage> ePackages = computeEPackagesToConvert();
    for (EPackage ePackage : ePackages)
    {
      for (Iterator<GenPackage> j = new GenPackagesTreeIterator(getGenModel()); j.hasNext();)
      {
        GenPackage genPackage = j.next();
        if (genPackage.getEcorePackage() == ePackage)
        {
          isLocalGePackage = localGenPackages.add(genPackage);
          break;
        }
      }
      if (!isLocalGePackage)
      {
        externalEPackages.add(ePackage);
      }
      else
      {
        isLocalGePackage = false;
      }
    }
    
    boolean changed = false;
    if (!localGenPackages.isEmpty())
    {
      URI genModelURI = getGenModel().eResource() != null ?
        getGenModel().eResource().getURI() :
        null;
      
      Map<String, String> externalPackageToLocalURIMap = new HashMap<String, String>(externalEPackages.size());
      for (EPackage ePackage : externalEPackages)
      {
        String location = getEPackageExportInfo(ePackage).getArtifactLocation();
        if (location != null)
        {
          URI uri = computeArtifactURI(location);
          if (genModelURI != null)
          {
            uri = uri.deresolve(genModelURI);
          }
          String stringURI = uri.toString();
          externalPackageToLocalURIMap.put(ePackage.getNsURI(), stringURI);
        }
      }
      
      for (GenPackage genPackage : localGenPackages)
      {
        EPackage ePackage = genPackage.getEcorePackage();        
        String location = getEPackageExportInfo(ePackage).getArtifactLocation();
        if (location != null)
        {
          GenAnnotation genPackageAnnotation = genPackage.getGenAnnotation(getConverterGenAnnotationSource());
          if (genPackageAnnotation == null)
          {
            genPackageAnnotation = ExporterUtil.findOrCreateGenAnnotation(genPackage, getConverterGenAnnotationSource());
            changed = true;
          }
          
          URI uri = computeArtifactURI(location);
          if (genModelURI != null)
          {
            uri = uri.deresolve(genModelURI);
          }
          String stringURI = uri.toString();
          
          GenAnnotation uriAnnotation = genPackageAnnotation.getGenAnnotation(stringURI);
          if (uriAnnotation == null)
          {
            uriAnnotation = ExporterUtil.findOrCreateGenAnnotation(genPackageAnnotation, stringURI);
            if (!externalPackageToLocalURIMap.isEmpty())
            {
              uriAnnotation.getDetails().putAll(externalPackageToLocalURIMap);
            }
            changed = true;
          }
          else
          {
            if (externalPackageToLocalURIMap.isEmpty())
            {
              if (!uriAnnotation.getDetails().isEmpty())
              {
                uriAnnotation.getDetails().clear();
                changed = true;
              }
            }
            else
            {
              if (!externalPackageToLocalURIMap.equals(uriAnnotation.getDetails().map()))
              {
                uriAnnotation.getDetails().clear();
                uriAnnotation.getDetails().putAll(externalPackageToLocalURIMap);
                changed = true;
              }
            }
          }
        }
      }
    }
    return changed;
  }
  
  protected String getDefaultArtifactFileName(EPackage ePackage)
  {
    if (ePackage.eResource() != null && ePackage.getESuperPackage() == null)
    {
      String fileName = ePackage.eResource().getURI().lastSegment();
      int lastIndex = fileName.lastIndexOf('.');
      if (lastIndex > 0)
      {
        return fileName.substring(0, lastIndex);
      }
    }
    return ConverterUtil.getQualifiedName(ePackage);
  }
    
  /*
   * For debugging purposes.  May be removed in the future.
   */
  protected void printExportData(ExportData exportData)
  {
    System.out.println("\nExport Data =======================================");
    for (Map.Entry<GenPackage, URI> entry : exportData.genPackageToArtifactURI.entrySet())
    {
      GenPackage genPackage = entry.getKey();
      URI artifactURI = entry.getValue();
      List<GenPackage> referencedGenPackages = exportData.genPackageToReferencedGenPackages.get(genPackage);
      
      System.out.println("\nGenPackage: " + genPackage.getNSURI());
      System.out.println("Resource: " + genPackage.eResource().getURI());
      System.out.println("Artifact: " + artifactURI.toString());
      if (referencedGenPackages != null)
      {
        System.out.println("Referenced GenPackages:");
        for (GenPackage referencedGenPackage : referencedGenPackages)
        {
          System.out.println("\tGenPackage: " + referencedGenPackage.getNSURI());
          System.out.println("\tResource: " + referencedGenPackage.eResource().getURI());
        }
      }
    }
    
    if (!exportData.referencedGenPackagesToArtifactURI.isEmpty())
    {
      System.out.println("\n-Referenced GenPackages-----------------------------");
      for (Map.Entry<GenPackage, URI> entry : exportData.referencedGenPackagesToArtifactURI.entrySet())
      {
        GenPackage genPackage = entry.getKey();
        URI artifactURI = entry.getValue();
        
        System.out.println("\nReferenced GenPackage: " + genPackage.getNSURI());
        System.out.println("Resource: " + genPackage.eResource().getURI());
        System.out.println("Artifact: " + artifactURI.toString());
      }
    }
    
    System.out.println("\n====================================================");
  } 
}
