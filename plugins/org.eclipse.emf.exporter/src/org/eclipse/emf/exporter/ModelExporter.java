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
 * $Id: ModelExporter.java,v 1.1 2005/12/14 08:06:32 marcelop Exp $
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
  public static final String GENANNOTATION_KEY_ARTIFACTS_URIS = "uris";
  // Nested GenAnnotations
  public static final String GENANNOTATION_SOURCE_SELECTED_EPACKAGES = "selectedEPackages";
  public static final String GENANNOTATION_SOURCE_SELECTED_REFERENCES = "selectedReferencesPackages";
  
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
  
  protected String getGenAnnotationSource()
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
  protected EMap getGenAnnotationDetails(GenBase genBase)
  {
    EMap eMap = (EMap)getGenBaseToGenAnnotationDetailsMap().get(genBase);
    if (eMap == null)
    {
      GenAnnotation genAnnotation = genBase.getGenAnnotation(getGenAnnotationSource());
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
  
  protected EMap getNestedGenAnnotationDetails(GenBase genBase, String nestedGenAnnotationSource)
  {
    GenAnnotation genAnnotation = genBase.getGenAnnotation(getGenAnnotationSource());
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
    List uris = Collections.EMPTY_LIST;
    String value = (String)getGenAnnotationDetails(genPackage).get(GENANNOTATION_KEY_ARTIFACTS_URIS);
    if (value != null)
    {
      String[] stringURIs = value.split(" ");
      int length = stringURIs.length;
      if (length > 0)
      {
        URI genModelURI = genPackage.getGenModel().eResource() != null ?
          genPackage.getGenModel().eResource().getURI() :
          null;
        
        uris = new ArrayList(length);
        for (int j = 0; j < length; j++)
        {
          URI uri = URI.createURI(stringURIs[j]);
          if (genModelURI != null)
          {
            uri = uri.resolve(genModelURI);
          }
          uris.add(uri);
        }
      }       
    }
    return uris; 
  }
  
  public void setGenModel(GenModel genModel)
  {
    dispose();
    this.genModel = genModel;
    
    genModel = getGenModel();
    if (genModel != null)
    {
      String location = (String)getGenAnnotationDetails(genModel).get(GENANNOTATION_KEY_DIRECTORY_URI);
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
      
      for (Iterator i = new GenPackagesTreeIterator(genModel.getUsedGenPackages()); i.hasNext();)
      {
        GenPackage genPackage = (GenPackage)i.next();
        if (isValidEPackage(genPackage))
        {
          EPackage ePackage = genPackage.getEcorePackage();
          getEPackageToGenPackageMap().put(ePackage, genPackage);
          getEPackages().add(ePackage);
          if (!getReferencedGenPackageToInfoMap().containsKey(genPackage))
          {
            if (isValidReferencedGenPackage(genPackage))
            {
              getReferencedGenPackages().add(genPackage);
            }
          }
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
          String location = (String)getNestedGenAnnotationDetails(getGenModel(), GENANNOTATION_SOURCE_SELECTED_EPACKAGES).get(ePackage.getNsURI());
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
        String stringURI = (String)getNestedGenAnnotationDetails(getGenModel(), GENANNOTATION_SOURCE_SELECTED_REFERENCES).get(genPackage.getNSURI());
        if (stringURI != null)
        {
          ReferencedGenPackageExportInfo genPackageInfo = getReferencedGenPackageExportInfo(genPackage);
          if (genPackageInfo.getArtifactURI() == null)
          {
            genPackageInfo.setModelExporterID(getID());
            URI uri = URI.createURI(stringURI);
            if (genPackage.getGenModel().eResource() != null)
            {
              uri = uri.resolve(genPackage.getGenModel().eResource().getURI());
            }
            genPackageInfo.setArtifactURI(uri);
          }
        }
      }
    };
  }
  
  public void loadGenModel(URI uri)
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
    genPackageInfo.setValidReference(getGenAnnotationDetails(genPackage).containsKey(GENANNOTATION_KEY_ARTIFACTS_URIS));
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
          List requiredPackages = ConverterUtil.computeRequiredPackages(ePackage);
          List requiredGenPackages = new ConverterUtil.GenPackageList(requiredPackages.size());
          for (Iterator j = requiredPackages.iterator(); j.hasNext();)
          {
            EPackage referencedEPackages = (EPackage)j.next();
            if (nsURIToReferencedGenPackage != null)
            {
              GenPackage referencedGenPackage = (GenPackage)nsURIToReferencedGenPackage.get(referencedEPackages.getNsURI());
              if (referencedGenPackage != null)
              {
                requiredGenPackages.add(referencedGenPackage); 
              }
            }
          } 
          if (requiredGenPackages.size() > 0)
          {
            genPackageToReferencedGenPackages.put(genPackage, requiredGenPackages);
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
      exportData.referencedGenPackagesToArtifactURI = referencedGenPackageToArtifactURI != null && referencedGenPackageToArtifactURI.size() > 0 ? 
        referencedGenPackageToArtifactURI : 
        Collections.EMPTY_MAP;
      exportData.genPackageToReferencedGenPackages = genPackageToReferencedGenPackages != null && genPackageToReferencedGenPackages.size() > 0 ? 
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
    GenAnnotation genModelAnnotation = getGenModel().getGenAnnotation(getGenAnnotationSource());
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
        genModelAnnotation = ExporterUtil.findOrCreateGenAnnotation(getGenModel(), getGenAnnotationSource());
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
        genModelAnnotation = ExporterUtil.findOrCreateGenAnnotation(getGenModel(), getGenAnnotationSource());
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
        genModelAnnotation = ExporterUtil.findOrCreateGenAnnotation(getGenModel(), getGenAnnotationSource());
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
    boolean changed = false;
    URI genModelURI = getGenModel().eResource() != null ?
      getGenModel().eResource().getURI() :
      null;
    List ePackages = computeEPackagesToConvert();
    for (Iterator i = ePackages.iterator(); i.hasNext();)
    {
      EPackage ePackage = (EPackage)i.next();
      for (Iterator j = new GenPackagesTreeIterator(getGenModel()); j.hasNext();)
      {
        GenPackage genPackage = (GenPackage)j.next();
        if (genPackage.getEcorePackage() == ePackage)
        {
          String location = getEPackageExportInfo(ePackage).getArtifactLocation();
          if (location != null)
          {
            GenAnnotation genAnnotation = genPackage.getGenAnnotation(getGenAnnotationSource());
            if (genAnnotation == null)
            {
              genAnnotation = ExporterUtil.findOrCreateGenAnnotation(genPackage, getGenAnnotationSource());
              changed = true;
            }
            
            URI uri = computeArtifactURI(location);
            if (genModelURI != null)
            {
              uri = uri.deresolve(genModelURI);
            }
            String stringURI = uri.toString();

            String uris = (String)genAnnotation.getDetails().get(GENANNOTATION_KEY_ARTIFACTS_URIS);
            if (uris == null)
            {
              uris = stringURI;
            }
            else if (!uris.startsWith(stringURI) && uris.indexOf(" " + stringURI) < 0)
            {
              uris = uris + " " + stringURI;
            }
            
            Object oldValue = genAnnotation.getDetails().put(GENANNOTATION_KEY_ARTIFACTS_URIS, uris);
            changed |= !uris.equals(oldValue);
          }
        }
      }
    }
    return changed;
  }    
}
