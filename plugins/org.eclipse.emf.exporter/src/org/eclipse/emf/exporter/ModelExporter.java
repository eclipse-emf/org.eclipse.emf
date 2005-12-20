/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: ModelExporter.java,v 1.7 2005/12/20 05:49:51 marcelop Exp $
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
    protected Map nsURIToLocalArtifactURIs;

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
    
    public Map getNSURIToLocalArtifactURIs()
    {
      if (nsURIToLocalArtifactURIs == null)
      {
        nsURIToLocalArtifactURIs = new HashMap();
      }
      return nsURIToLocalArtifactURIs;
    }
  }
  
  public static class ExportData
  {
    public Map genPackageToArtifactURI;
    public Map genPackageToReferencedGenPackages;
    public Map referencedGenPackagesToArtifactURI;
  }
  
  protected static class GenPackagesTreeIterator extends AbstractTreeIterator
  {
    public GenPackagesTreeIterator(GenModel genModel)
    {
      this(genModel.getGenPackages());
    }

    public GenPackagesTreeIterator(Collection genPackages)
    {
      super(genPackages, false);
    }

    protected Iterator getChildren(Object object)
    {
      return object instanceof Collection ? 
        ((Collection)object).iterator() :
        ((GenPackage)object).getNestedGenPackages().iterator();
    }
  }
  
  
  protected Map ePackageToGenPackageMap;
  protected URI directoryURI;
  protected Map genBaseToDetailsMap;
  
  protected boolean saveExporter = false;
  protected boolean saveEPackageArtifactURI = false;
  
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
    
    directoryURI = null;
      
    super.dispose();
  }
  
  protected String getConverterGenAnnotationSource()
  {
    return GENANNOTATION_SOURCE_PREFIX + getID();
  }    
  
  protected Map getEPackageToGenPackageMap()
  {
    if (ePackageToGenPackageMap == null)
    {
      ePackageToGenPackageMap = new HashMap();
    }
    return ePackageToGenPackageMap;
  }

  protected Map getGenBaseToGenAnnotationDetailsMap()
  {
    if (genBaseToDetailsMap == null)
    {
      genBaseToDetailsMap = new HashMap();
    }
    return genBaseToDetailsMap;
  }
  
  /**
   * Returns the {@link GenBase} object's {@link GenAnnotation} Details map
   * associated with this Model Exporter or {@link ECollections.EMPTY_EMAP}
   */
  protected EMap getExporterGenAnnotationDetails(GenBase genBase)
  {
    EMap eMap = (EMap)getGenBaseToGenAnnotationDetailsMap().get(genBase);
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
        eMap = ECollections.EMPTY_EMAP;
      }
    }
    return eMap;
  }
  
  protected List getExporterNestedGenAnnotations(GenBase genBase)
  {
    if (genBase != null)
    {
      GenAnnotation exportAnnotation = genBase.getGenAnnotation(getConverterGenAnnotationSource());
      if (exportAnnotation != null && !exportAnnotation.getGenAnnotations().isEmpty())
      {
        List nestedAnnotations = exportAnnotation.getGenAnnotations();
        if (nestedAnnotations != null)
        {
          return nestedAnnotations;
        }
      }
    }
    return Collections.EMPTY_LIST;    
  }
    
  protected EMap getExporterNestedGenAnnotationDetails(GenBase genBase, String nestedGenAnnotationSource)
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
    return ECollections.EMPTY_EMAP;
  }
  
  public List getArtifactURIs(GenPackage genPackage)
  {
    List nestedAnnotations = getExporterNestedGenAnnotations(genPackage);
    if (!nestedAnnotations.isEmpty())
    {
      List uris = new UniqueEList(nestedAnnotations.size());
      URI genModelURI = genPackage.getGenModel().eResource() != null ?
        genPackage.getGenModel().eResource().getURI() :
        null;

      for (Iterator i = nestedAnnotations.iterator(); i.hasNext();)
      {
        GenAnnotation uriAnnotation = (GenAnnotation)i.next();
        URI uri = URI.createURI(uriAnnotation.getSource());
        if (genModelURI != null)
        {
          uri = uri.resolve(genModelURI);
        }
        uris.add(uri);          
      }
      
      for (Iterator i = getReferencedGenPackages().iterator(); i.hasNext();)
      {
        GenPackage referencedGenPackage = (GenPackage)i.next();
        ReferencedGenPackageExportInfo genPackageInfo = getReferencedGenPackageExportInfo(referencedGenPackage);
        if (genPackageInfo.getArtifactURI() != null)
        {
          List localURIs = (List)genPackageInfo.getNSURIToLocalArtifactURIs().get(genPackage.getNSURI());
          if (localURIs != null)
          {
            uris.addAll(localURIs);
          }
        }
      }
      
      return uris; 
    }
    return Collections.EMPTY_LIST;
  }
    
  public void setGenModel(GenModel genModel) throws DiagnosticException
  {
    dispose();
    this.genModel = genModel;
    genModel = getGenModel();
    
    if (genModel != null)
    {
      Diagnostic diagnostic = genModel.diagnose();
      if (diagnostic.getSeverity() != Diagnostic.OK)
      {
        throw new DiagnosticException(diagnostic);
      }
      
      String location = (String)getExporterGenAnnotationDetails(genModel).get(GENANNOTATION_KEY_DIRECTORY_URI);
      setDirectoryURI(location);
      if (genModel.eResource() != null && getDirectoryURI() != null)
      {
        setDirectoryURI(getDirectoryURI().resolve(genModel.eResource().getURI()));
      }      
      
      for (Iterator i = new GenPackagesTreeIterator(genModel); i.hasNext();)
      {
        GenPackage genPackage = (GenPackage)i.next();
        if (isValidEPackage(genPackage))
        {
          EPackage ePackage = genPackage.getEcorePackage();
          
          getEPackageToGenPackageMap().put(ePackage, genPackage); 
          getEPackages().add(genPackage.getEcorePackage());
        }
      }
      
      List exporterUsedGenPackages = new ConverterUtil.GenPackageList(genModel.getUsedGenPackages());
      GenAnnotation genModelAnnotation = genModel.getGenAnnotation(getConverterGenAnnotationSource());
      if (genModelAnnotation != null)
      {
        GenAnnotation referencedPackagesAnnotation = genModelAnnotation.getGenAnnotation(GENANNOTATION_SOURCE_SELECTED_REFERENCES);
        if (referencedPackagesAnnotation != null)
        {
          for (Iterator i = referencedPackagesAnnotation.getReferences().iterator(); i.hasNext();)
          {
            Object o = i.next();
            if (o instanceof GenPackage)
            {
              exporterUsedGenPackages.add(o);              
            }
          }
        }
      }
      
      for (Iterator i = new GenPackagesTreeIterator(exporterUsedGenPackages); i.hasNext();)
      {
        GenPackage genPackage = (GenPackage)i.next();
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
    for (Iterator i = getEPackageToInfoMap().entrySet().iterator(); i.hasNext();)
    {
      Map.Entry entry = (Map.Entry)i.next();
      EPackage ePackage = (EPackage)entry.getKey();
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

  protected List createEPackagesList()
  {
    return new ConverterUtil.EPackageList()
    {
      protected void didAdd(int index, Object newObject)
      {
        EPackage ePackage = (EPackage)newObject;
        EPackageExportInfo packageInfo = getEPackageExportInfo(ePackage);
        if (packageInfo.getArtifactLocation() == null)
        {
          String location = (String)getExporterNestedGenAnnotationDetails(getGenModel(), GENANNOTATION_SOURCE_SELECTED_EPACKAGES).get(ePackage.getNsURI());
          if (location != null)
          {
            packageInfo.setConvert(true);
            packageInfo.setArtifactLocation(location);
          }
        }
      }
    };
  }
    
  protected List createReferencedGenPackagesList()
  {
    return new ConverterUtil.GenPackageList()
    {
      protected void didAdd(int index, Object newObject)
      {
        GenPackage genPackage = (GenPackage)newObject;
        URI genModelURI = genPackage.getGenModel().eResource() != null ?
          genPackage.getGenModel().eResource().getURI() :
          null;
        
        String stringURI = (String)getExporterNestedGenAnnotationDetails(getGenModel(), GENANNOTATION_SOURCE_SELECTED_REFERENCES).get(genPackage.getNSURI());
        if (stringURI != null)
        {
          ReferencedGenPackageExportInfo genPackageInfo = getReferencedGenPackageExportInfo(genPackage);
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
        
        List uriAnnotations = getExporterNestedGenAnnotations(genPackage);
        if (!uriAnnotations.isEmpty())
        {
          ReferencedGenPackageExportInfo genPackageInfo = null;
          for (Iterator i = uriAnnotations.iterator(); i.hasNext();)
          {
            GenAnnotation uriAnnotation = (GenAnnotation)i.next();
            if (!uriAnnotation.getDetails().isEmpty())
            {
              if (genPackageInfo == null)
              {
                genPackageInfo = getReferencedGenPackageExportInfo(genPackage);
              }
              for (Iterator j = uriAnnotation.getDetails().entrySet().iterator(); j.hasNext();)
              {
                Map.Entry entry = (Map.Entry)j.next();
                String packageNSURI = (String)entry.getKey();
                String stringArtifactURIs = (String)entry.getValue();
                if (stringArtifactURIs != null)
                {
                  String[] stringURIs = stringArtifactURIs.split(" ");

                  List uris = (List)genPackageInfo.getNSURIToLocalArtifactURIs().get(packageNSURI);
                  if (uris == null)
                  {
                    uris = new ArrayList(stringURIs.length);
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
  
  protected EPackageConvertInfo createEPackageInfo(EPackage ePackage)
  {
    return new EPackageExportInfo();
  }
  
  public ReferencedGenPackageExportInfo getReferencedGenPackageExportInfo(GenPackage genPackage)
  {
    return (ReferencedGenPackageExportInfo)getReferenceGenPackageConvertInfo(genPackage);
  }

  protected ReferencedGenPackageConvertInfo createGenPackageConvertInfo(GenPackage genPackage)
  {
    ReferencedGenPackageExportInfo genPackageInfo = new ReferencedGenPackageExportInfo();
    genPackageInfo.setValidReference(!getExporterNestedGenAnnotations(genPackage).isEmpty());
    return genPackageInfo;
  }
  
  protected ReferencedEPackageFilter createReferencedEPackageFilterToConvert()
  {
    return new ReferencedEPackageFilter()
    {
      protected boolean isValidReference(GenPackage genPackage)
      {
        return super.isValidReference(genPackage) && 
          getReferencedGenPackageExportInfo(genPackage).getArtifactURI() != null;
      }
    };
  }
  
  protected boolean canConvert(EPackage ePackage)
  {
    return super.canConvert(ePackage) && getEPackageExportInfo(ePackage).getArtifactLocation() != null;
  }
  
  public void export(Monitor monitor) throws Exception
  {
    Map nsURIToReferencedGenPackage = null;
    Map referencedGenPackageToArtifactURI = null; 
    if (referencedGenPackageToInfoMap != null)
    {
      referencedGenPackageToArtifactURI = new HashMap(referencedGenPackageToInfoMap.size());
      nsURIToReferencedGenPackage = new HashMap(referencedGenPackageToInfoMap.size());
      List referencedGenPackages = computeValidReferencedGenPackages();
      for (Iterator i = referencedGenPackages.iterator(); i.hasNext();)
      {
        GenPackage genPackage = (GenPackage)i.next();
        URI artifactURI = getReferencedGenPackageExportInfo(genPackage).getArtifactURI();
        referencedGenPackageToArtifactURI.put(genPackage, artifactURI);
        nsURIToReferencedGenPackage.put(genPackage.getNSURI(), genPackage);
      }
    }
    
    Map genPackageToArtifactURI = null;
    Map genPackageToReferencedGenPackages = null;
    if (ePackageToInfoMap != null && ePackageToGenPackageMap != null)
    {
      genPackageToArtifactURI = new HashMap(ePackageToInfoMap.size());
      genPackageToReferencedGenPackages = new HashMap(ePackageToInfoMap.size());
      
      List ePackages = computeEPackagesToConvert();
      for (Iterator i = ePackages.iterator(); i.hasNext();)
      {
        EPackage ePackage = (EPackage)i.next();
        GenPackage genPackage = (GenPackage)ePackageToGenPackageMap.get(ePackage);
        if (genPackage != null)
        {
          if (nsURIToReferencedGenPackage != null)
          {
            List requiredEPackages = ConverterUtil.computeRequiredPackages(ePackage);
            List requiredGenPackages = new ConverterUtil.GenPackageList(requiredEPackages.size());
            for (Iterator j = requiredEPackages.iterator(); j.hasNext();)
            {
              EPackage requiredEPackage = (EPackage)j.next();
              GenPackage referencedGenPackage = (GenPackage)nsURIToReferencedGenPackage.get(requiredEPackage.getNsURI());
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
        Collections.EMPTY_MAP;
      exportData.genPackageToReferencedGenPackages = genPackageToReferencedGenPackages != null && !genPackageToReferencedGenPackages.isEmpty() ? 
        genPackageToReferencedGenPackages : 
        Collections.EMPTY_MAP;
      
      doExport(monitor, exportData);
    }
  }
  
  protected void doExport(Monitor monitor, ExportData exportData) throws Exception
  {  
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
    boolean wasTrackingModification = genModelResource.isTrackingModification();
    genModelResource.setTrackingModification(true);
    
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
      if (ExporterPlugin.IS_ECLIPSE_RUNNING)
      {
        String readOnlyFiles = ConverterUtil.WorkspaceResourceValidator.validate(Collections.singletonList(genModelResource));
        if (readOnlyFiles != null)
        {
          throw new Exception(ExporterPlugin.INSTANCE.getString("_UI_ReadOnlyFiles_error", new String[]{readOnlyFiles})); 
        }        
      }
      
      genModelResource.save(getGenmodelSaveOptions());
    }
    genModelResource.setTrackingModification(wasTrackingModification);
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
    
    List ePackages = computeEPackagesToConvert();
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
      Set nsURIs = new HashSet();
      for (Iterator i = ePackages.iterator(); i.hasNext();)
      {
        EPackage ePackage = (EPackage)i.next();
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
    
    List referencedGenPackages = computeValidReferencedGenPackages();
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
      Set nsURIs = new HashSet();
      for (Iterator i = referencedGenPackages.iterator(); i.hasNext();)
      {
        GenPackage genPackage = (GenPackage)i.next();
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
    List localGenPackages = new ConverterUtil.GenPackageList();
    List externalEPackages = new ConverterUtil.EPackageList();

    boolean isLocalGePackage = false;
    List ePackages = computeEPackagesToConvert();
    for (Iterator i = ePackages.iterator(); i.hasNext();)
    {
      EPackage ePackage = (EPackage)i.next();
      for (Iterator j = new GenPackagesTreeIterator(getGenModel()); j.hasNext();)
      {
        GenPackage genPackage = (GenPackage)j.next();
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
      
      Map externalPackageToLocalURIMap = new HashMap(externalEPackages.size());
      for (Iterator i = externalEPackages.iterator(); i.hasNext();)
      {
        EPackage ePackage = (EPackage)i.next();
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
      
      for (Iterator i = localGenPackages.iterator(); i.hasNext();)
      {
        GenPackage genPackage = (GenPackage)i.next();
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
  
  /*
   * For debugging purposes.  May be removed in the future.
   */
  protected void printExportData(ExportData exportData)
  {
    System.out.println("\nExport Data =======================================");
    for (Iterator i = exportData.genPackageToArtifactURI.entrySet().iterator(); i.hasNext();)
    {
      Map.Entry entry = (Map.Entry)i.next();
      GenPackage genPackage = (GenPackage)entry.getKey();
      URI artifactURI = (URI)entry.getValue();
      List referencedGenPackages = (List)exportData.genPackageToReferencedGenPackages.get(genPackage);
      
      System.out.println("\nGenPackage: " + genPackage.getNSURI());
      System.out.println("Resource: " + genPackage.eResource().getURI());
      System.out.println("Artifact: " + artifactURI.toString());
      if (referencedGenPackages != null)
      {
        System.out.println("Referenced GenPackages:");
        for (Iterator j = referencedGenPackages.iterator(); j.hasNext();)
        {
          GenPackage referencedGenPackage = (GenPackage)j.next();
          System.out.println("\tGenPackage: " + referencedGenPackage.getNSURI());
          System.out.println("\tResource: " + referencedGenPackage.eResource().getURI());
        }
      }
    }
    
    if (!exportData.referencedGenPackagesToArtifactURI.isEmpty())
    {
      System.out.println("\n-Referenced GenPackages-----------------------------");
      for (Iterator i = exportData.referencedGenPackagesToArtifactURI.entrySet().iterator(); i.hasNext();)
      {
        Map.Entry entry = (Map.Entry)i.next();
        GenPackage genPackage = (GenPackage)entry.getKey();
        URI artifactURI = (URI)entry.getValue();
        
        System.out.println("\nReferenced GenPackage: " + genPackage.getNSURI());
        System.out.println("Resource: " + genPackage.eResource().getURI());
        System.out.println("Artifact: " + artifactURI.toString());
      }
    }
    
    System.out.println("\n====================================================");
  } 
}
