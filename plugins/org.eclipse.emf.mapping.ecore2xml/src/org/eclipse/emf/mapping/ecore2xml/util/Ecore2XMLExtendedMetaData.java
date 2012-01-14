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
package org.eclipse.emf.mapping.ecore2xml.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLRegistry;

/**
 * Maps XML elements to Ecore named elements based on registered Ecore2XML mappings.
 */
public class Ecore2XMLExtendedMetaData extends BasicExtendedMetaData
{
  
  protected final Ecore2XMLRegistry ecore2xmlRegistry;
  
  protected final Map<String, XMLResource.XMLMap> xmlMaps = new HashMap<String, XMLResource.XMLMap>();
  
  public Ecore2XMLExtendedMetaData()
  {
    this(Ecore2XMLRegistry.INSTANCE);
  }
  
  public Ecore2XMLExtendedMetaData(Ecore2XMLRegistry ecore2xmlRegistry)
  {
    this(EPackage.Registry.INSTANCE, ecore2xmlRegistry);
  }
  
  public Ecore2XMLExtendedMetaData(EPackage.Registry ePackageRegistry, Ecore2XMLRegistry ecore2xmlRegistry)
  {
    this(ANNOTATION_URI, ePackageRegistry, ecore2xmlRegistry);
  }
  
  public Ecore2XMLExtendedMetaData(String annotationURI, EPackage.Registry ePackageRegistry, Ecore2XMLRegistry ecore2xmlRegistry)
  {
    super(annotationURI, ePackageRegistry);

    extendedMetaDataHolderCache = new HashMap<EModelElement, Object>();

    this.ecore2xmlRegistry = ecore2xmlRegistry;
  }
  
  protected XMLResource.XMLInfo getInfo(ENamedElement element)
  {
    XMLResource.XMLInfo xmlInfo = null;
    
    for (XMLResource.XMLMap xmlMap : xmlMaps.values())
    {
      xmlInfo = xmlMap.getInfo(element);
      if (xmlInfo != null)
      {
        break;
      }
    }
    
    return xmlInfo;
  }
  
  protected EClassifier getClassifier(String namespaceURI, String name)
  {
    EClassifier classifier = null;
    
    for (XMLResource.XMLMap xmlMap : xmlMaps.values())
    {
      classifier = xmlMap.getClassifier(namespaceURI, name);
      if (classifier != null)
      {
        break;
      }
    }
    
    return classifier;
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.util.ExtendedMetaData#getPackage(java.lang.String)
   */
  @Override
  public EPackage getPackage(String namespace)
  {
    XMLResource.XMLMap xmlMap = ecore2xmlRegistry.getXMLMap(namespace);
    
    if (xmlMap != null)
    {
      xmlMaps.put(namespace, xmlMap);
    }
    
    return super.getPackage(namespace);
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.util.ExtendedMetaData#getName(org.eclipse.emf.ecore.EClassifier)
   */
  @Override
  public String getName(EClassifier eClassifier)
  {
    XMLResource.XMLInfo xmlInfo = getInfo(eClassifier);
    
    if (xmlInfo != null)
    {
      return xmlInfo.getName();
    }
    
    return super.getName(eClassifier);
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.util.ExtendedMetaData#getName(org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  public String getName(EStructuralFeature eStructuralFeature)
  {
    XMLResource.XMLInfo xmlInfo = getInfo(eStructuralFeature);
    
    if (xmlInfo != null)
    {
      return xmlInfo.getName();
    }
    
    return super.getName(eStructuralFeature);
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.util.ExtendedMetaData#getNamespace(org.eclipse.emf.ecore.EPackage)
   */
  @Override
  public String getNamespace(EPackage ePackage)
  {
    XMLResource.XMLInfo xmlInfo = getInfo(ePackage);
    
    if (xmlInfo != null)
    {
      return xmlInfo.getTargetNamespace();
    }
    
    return super.getNamespace(ePackage);
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.util.ExtendedMetaData#getType(org.eclipse.emf.ecore.EPackage,
   *      java.lang.String)
   */
  @Override
  public EClassifier getType(EPackage ePackage, String name)
  {
    EClassifier type = super.getType(ePackage, name);
    
    if (type == null)
    {
      type = getClassifier(ePackage.getNsURI(), name);
    }
    
    return type;
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.util.ExtendedMetaData#getFeatureKind(org.eclipse.emf.ecore.EStructuralFeature)
   */
  @Override
  public int getFeatureKind(EStructuralFeature eStructuralFeature)
  {
    XMLResource.XMLInfo xmlInfo = getInfo(eStructuralFeature);
    
    if (xmlInfo != null)
    {
      switch (xmlInfo.getXMLRepresentation())
      {
        case XMLResource.XMLInfo.ELEMENT:
          return ExtendedMetaData.ELEMENT_FEATURE;
        case XMLResource.XMLInfo.ATTRIBUTE:
          return ExtendedMetaData.ATTRIBUTE_FEATURE;
      }
    }
    
    return super.getFeatureKind(eStructuralFeature);
  }
  
  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.util.BasicExtendedMetaData#isFeatureKindSpecific()
   */
  @Override
  protected boolean isFeatureKindSpecific()
  {
    return false;
  }
  
}
