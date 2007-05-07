/**
 * <copyright>
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * $Id: ModelConverter.java,v 1.5 2007/05/07 14:42:05 emerks Exp $
 */
package org.eclipse.emf.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.converter.util.ConverterUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * @since 2.2.0
 */
public abstract class ModelConverter
{
  public static class EPackageConvertInfo
  {
    protected boolean convert = false;
    protected String convertData;
    
    public boolean isConvert()
    {
      return convert;
    }

    public void setConvert(boolean convert)
    {
      this.convert = convert;
    }
    
    public String getConvertData()
    {
      return convertData;
    }
    
    public void setConvertData(String convertData)
    {
      this.convertData = convertData;
    }
  }
  
  public static class ReferencedGenPackageConvertInfo
  {
    protected boolean validReference = true;
    
    public boolean isValidReference()
    {
      return validReference;
    }

    public void setValidReference(boolean validReference)
    {
      this.validReference = validReference;
    }
  }
  
  protected class ReferencedEPackageFilter
  {
    public List<GenPackage> computeValidReferencedGenPackages()
    {
      List<GenPackage> genPackages = new ConverterUtil.GenPackageList();
      for (GenPackage genPackage : getReferencedGenPackages())
      {
        EPackage ePackage = getReferredEPackage(genPackage);
        if (ePackage != null)
        {
          genPackages.add(genPackage);
        }
      }
      return genPackages;
      
    }
    
    public List<EPackage> filterReferencedEPackages(Collection<EPackage> ePackages, List<GenPackage> referencedGenPackages)
    {
      if (referencedGenPackages == null)
      {
        referencedGenPackages = getReferencedGenPackages();
      }
      
      if (ePackages.isEmpty())
      {
        return Collections.emptyList();
      }
      else if (referencedGenPackages.isEmpty())
      {
        return new ArrayList<EPackage>(ePackages);
      }
      else
      {
        List<EPackage> filteredEPackages = new ConverterUtil.EPackageList(ePackages);
        for (GenPackage genPackage : referencedGenPackages)
        {
          EPackage ePackage = getReferredEPackage(genPackage);
          if (ePackage != null)
          {
            filteredEPackages.remove(ePackage);
          }
        }
        return filteredEPackages;
      }
    }
    
    protected EPackage getReferredEPackage(GenPackage genPackage)
    {
      return isValidReference(genPackage) ?
        ModelConverter.this.getReferredEPackage(genPackage) :
        null;
    }
    
    protected boolean isValidReference(GenPackage genPackage)
    {
      return getReferenceGenPackageConvertInfo(genPackage).isValidReference();
    }
  }

  protected GenModel genModel;
  protected List<EPackage> ePackages;
  protected Map<EPackage, EPackageConvertInfo> ePackageToInfoMap;
  protected List<GenPackage> referencedGenPackages;
  protected Map<GenPackage, ReferencedGenPackageConvertInfo> referencedGenPackageToInfoMap;
  
  protected ResourceSet externalGenModelResourceSet;
  protected List<GenModel> externalGenModelList;  
  protected ReferencedEPackageFilter referencedEPackageFilter;
  protected ReferencedEPackageFilter referencedEPackageFilterToConvert;

  public void dispose()
  {
    clearEPackagesCollections();

    if (referencedGenPackages != null)
    {
      referencedGenPackages.clear();
      referencedGenPackages = null;
    }

    if (referencedGenPackageToInfoMap != null)
    {
      referencedGenPackageToInfoMap.clear();
      referencedGenPackageToInfoMap = null;
    }

    genModel = null;
    referencedEPackageFilter = null;
    referencedEPackageFilterToConvert = null;
  }
  
  public abstract String getID();
  
  protected String getConverterGenAnnotationSource()
  {
    return GenModelPackage.eNS_URI + getID();
  }  
  
  public GenModel getGenModel()
  {
    return genModel;
  }
  
  public List<EPackage> getEPackages()
  {
    if (ePackages == null)
    {
      ePackages = createEPackagesList();
    }
    return ePackages;
  }
  
  protected List<EPackage> createEPackagesList()
  {
    return new ConverterUtil.EPackageList();
  }
  
  public List<GenPackage> getReferencedGenPackages()
  {
    if (referencedGenPackages == null)
    {
      referencedGenPackages = createReferencedGenPackagesList();
    }
    return referencedGenPackages;
  }
  
  protected List<GenPackage> createReferencedGenPackagesList()
  {
    return new ConverterUtil.GenPackageList();
  }
  
  public void clearEPackagesCollections()
  {    
    if (ePackages != null)
    {
      ePackages.clear();
      ePackages = null;
    }
    
    if (ePackageToInfoMap != null)
    {
      ePackageToInfoMap.clear();
      ePackageToInfoMap = null;
    }  
    
    if (externalGenModelList != null)
    {
      externalGenModelList.clear();
      externalGenModelList = null;
    }    
  }
  
  public ResourceSet createResourceSet()
  {
    return ConverterUtil.createResourceSet();
  }
  
  protected Map<EPackage, EPackageConvertInfo> getEPackageToInfoMap()
  {
    if (ePackageToInfoMap == null)
    {
      ePackageToInfoMap = new HashMap<EPackage, EPackageConvertInfo>();
    }
    return ePackageToInfoMap;
  }

  public EPackageConvertInfo getEPackageConvertInfo(EPackage ePackage)
  {
    EPackageConvertInfo ePackageInfo = getEPackageToInfoMap().get(ePackage);
    if (ePackageInfo == null)
    {
      ePackageInfo = createEPackageInfo(ePackage);
      getEPackageToInfoMap().put(ePackage, ePackageInfo);
    }
    return ePackageInfo;
  }
  
  public void clearEPackageConvertInfoCache()
  {
    getEPackageToInfoMap().clear();
  }
  
  protected EPackageConvertInfo createEPackageInfo(EPackage ePackage)
  {
    return new EPackageConvertInfo();
  }
  
  protected Map<GenPackage, ReferencedGenPackageConvertInfo> getReferencedGenPackageToInfoMap()
  {
    if (referencedGenPackageToInfoMap == null)
    {
      referencedGenPackageToInfoMap = new HashMap<GenPackage, ReferencedGenPackageConvertInfo>();
    }
    return referencedGenPackageToInfoMap;
  }
 
  public ReferencedGenPackageConvertInfo getReferenceGenPackageConvertInfo(GenPackage genPackage)
  {
    ReferencedGenPackageConvertInfo genPackageConvertInfo = getReferencedGenPackageToInfoMap().get(genPackage);
    if (genPackageConvertInfo == null)
    {
      genPackageConvertInfo = createGenPackageConvertInfo(genPackage);
      getReferencedGenPackageToInfoMap().put(genPackage, genPackageConvertInfo);
    }
    return genPackageConvertInfo;
  }
  
  public void clearReferenceGenPackageConvertInfoCache()
  {
    getReferencedGenPackageToInfoMap().clear();
  }
  
  protected ReferencedGenPackageConvertInfo createGenPackageConvertInfo(GenPackage genPackage)
  {
    return new ReferencedGenPackageConvertInfo();
  }

  public List<EPackage> filterReferencedEPackages(Collection<EPackage> ePackages, List<GenPackage> referencedGenPackages)
  {
    if (referencedEPackageFilter == null)
    {
      referencedEPackageFilter = createReferencedEPackageFilter();
    }
    return referencedEPackageFilter.filterReferencedEPackages(ePackages, referencedGenPackages);
  }
  
  protected ReferencedEPackageFilter createReferencedEPackageFilter()
  {
    return new ReferencedEPackageFilter();
  }  

  protected List<EPackage> computeEPackagesToConvert()
  {
    List<EPackage> ePackages = new ConverterUtil.EPackageList();
    for (EPackage ePackage : getEPackages())
    {
      if (canConvert(ePackage))
      {
        ePackages.add(ePackage);
      }
    }
    return filterReferencedEPackagesToConvert(ePackages, null);
  }
    
  protected List<EPackage> filterReferencedEPackagesToConvert(Collection<EPackage> ePackages, List<GenPackage> referencedGenPackages)
  {
    if (referencedEPackageFilterToConvert == null)
    {
      referencedEPackageFilterToConvert = createReferencedEPackageFilterToConvert();
    }
    return referencedEPackageFilterToConvert.filterReferencedEPackages(ePackages, referencedGenPackages);
  }
  
  protected List<GenPackage> computeValidReferencedGenPackages()
  {
    if (referencedEPackageFilterToConvert == null)
    {
      referencedEPackageFilterToConvert = createReferencedEPackageFilterToConvert();
    }
    return referencedEPackageFilterToConvert.computeValidReferencedGenPackages();
  }  
  
  protected ReferencedEPackageFilter createReferencedEPackageFilterToConvert()
  {
    return new ReferencedEPackageFilter();
  }
  
  /**
   * Returns true if the EPackage can be converted.  This
   * method doesn't check for dependencies or anything, being focused
   * in testing whether all the required information to generate an 
   * EPackage was gathered. 
   */
  protected boolean canConvert(EPackage ePackage)
  {
    return getEPackageConvertInfo(ePackage).isConvert();
  }
   
  public EPackage getReferredEPackage(GenPackage genPackage)
  {
    String nsURI = genPackage.getEcorePackage().getNsURI();
    if (nsURI != null)
    {
      for (EPackage ePackage : getEPackages())
      {
        if (nsURI.equals(ePackage.getNsURI()))
        {
          return ePackage;
        }
      }
    }
    return null;
  }
  
  public List<GenModel> getExternalGenModels()
  {
    if (externalGenModelList == null)
    {
      externalGenModelList = new UniqueEList.FastCompare<GenModel>();
      if (externalGenModelResourceSet == null)
      {
        externalGenModelResourceSet = createExternalGenModelResourceSet();
      }
      Map<String, URI> ePackageToGenModelMap = EcorePlugin.getEPackageNsURIToGenModelLocationMap();
      for (TreeIterator<EPackage> i = 
             new EcoreUtil.ContentTreeIterator<EPackage>(getEPackages())
             {
               private static final long serialVersionUID = 1L;

               @Override
               protected Iterator<? extends EObject> getEObjectChildren(EObject eObject)
               {
                 return ((EPackage)eObject).getESubpackages().iterator();
               }
             };
           i.hasNext(); )
      {
        EPackage ePackage = i.next();
        URI genModelURI = ePackageToGenModelMap.get(ePackage.getNsURI());
        if (genModelURI != null)
        {
          try
          {
            Resource genModelResource = externalGenModelResourceSet.getResource(genModelURI, false);
            if (genModelResource == null)
            {
              genModelResource = externalGenModelResourceSet.getResource(genModelURI, true);
              externalGenModelList.add((GenModel)genModelResource.getContents().get(0));
            }
          }
          catch (Exception exception)
          {
            ConverterPlugin.INSTANCE.log(exception);
          }
          i.prune();
        }
      }
    }
    return externalGenModelList;
  }
  
  protected ResourceSet createExternalGenModelResourceSet()
  {
    return createResourceSet();
  }  
  
  /**
   * This method was misnamed and will be removed in the next release.
   * @see #getGenModelSaveOptions()
   */
  @Deprecated
  protected Map<?, ?> getGenmodelSaveOptions()
  {
    Map<Object, Object> result = new HashMap<Object, Object>();
    result.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
    return result;
  }

  protected Map<?, ?> getGenModelSaveOptions()
  {
    return getGenmodelSaveOptions();
  }
  
  /**
   * Changes the existing EPackage Infos so that no duplicated convert data 
   * is used.
   */
  public void makeEPackageConvertDataUnique()
  {
    if (!getEPackageToInfoMap().isEmpty())
    {
      Map<String, Integer> dataToCounter = new HashMap<String, Integer>();
      List<EPackage> ePackages = filterReferencedEPackages(getEPackageToInfoMap().keySet(), null);
      if (!ePackages.isEmpty())
      {
        List<EPackageConvertInfo> packageInfos = new ArrayList<EPackageConvertInfo>(ePackages.size());
        for (EPackage ePackage : ePackages)
        {
          if (ePackage.getESuperPackage() == null || !ePackages.contains(ePackage.getESuperPackage()))
          {
            EPackageConvertInfo packageInfo = getEPackageToInfoMap().get(ePackage);
            if (packageInfo.isConvert())
            {
              packageInfos.add(0, packageInfo);
            }
            else
            {
              packageInfos.add(packageInfo);
            }
            String data = packageInfo.getConvertData();
            if (data != null)
            {
              dataToCounter.put(data, null);
            }
          }
        }
        
        for (EPackageConvertInfo packageInfo : packageInfos)
        {        
          String data = packageInfo.getConvertData();
          if (data != null)
          {
            Integer counterObject = dataToCounter.get(data);
            if (counterObject != null)
            {
              int counter = counterObject.intValue();
              int index = data.lastIndexOf(".");
              StringBuffer newValue = null;
              do
              {            
                newValue = new StringBuffer(data).insert(index, counter++);
              }
              while (dataToCounter.containsKey(newValue.toString()));
              
              packageInfo.setConvertData(newValue.toString());
              counterObject = new Integer(counter);
              dataToCounter.put(newValue.toString(), new Integer(1));
            }
            else
            {
              counterObject = new Integer(1);
            }        
            dataToCounter.put(data, counterObject);
          }
        }
      }
    }
  }  
}
