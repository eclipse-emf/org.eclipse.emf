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
 * $Id: Ecore2XMLExtendedMetaData.java,v 1.1 2005/03/18 21:02:01 khussey Exp $
 */
package org.eclipse.emf.mapping.ecore2xml.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;
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

  protected final Map xmlMaps = new HashMap();

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

    this.ecore2xmlRegistry = ecore2xmlRegistry;
  }

  protected XMLResource.XMLInfo getInfo(ENamedElement element)
  {
    XMLResource.XMLInfo xmlInfo = null;

    for (Iterator maps = xmlMaps.values().iterator(); xmlInfo == null && maps.hasNext();)
    {
      xmlInfo = ((XMLResource.XMLMap)maps.next()).getInfo(element);
    }

    return xmlInfo;
  }

  protected EClassifier getClassifier(String namespaceURI, String name)
  {
    EClassifier classifier = null;

    for (Iterator maps = xmlMaps.values().iterator(); classifier == null && maps.hasNext();)
    {
      classifier = ((XMLResource.XMLMap)maps.next()).getClassifier(namespaceURI, name);
    }

    return classifier;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.util.ExtendedMetaData#getPackage(java.lang.String)
   */
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
  public String getName(EClassifier eClassifier)
  {
    XMLResource.XMLInfo xmlInfo = getInfo(eClassifier);

    if (xmlInfo != null)
    {
      String name = xmlInfo.getName();

      if (name != null)
      {
        return name;
      }
    }

    return super.getName(eClassifier);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.util.ExtendedMetaData#getName(org.eclipse.emf.ecore.EStructuralFeature)
   */
  public String getName(EStructuralFeature eStructuralFeature)
  {
    XMLResource.XMLInfo xmlInfo = getInfo(eStructuralFeature);

    if (xmlInfo != null)
    {
      String name = xmlInfo.getName();

      if (name != null)
      {
        return name;
      }
    }

    return super.getName(eStructuralFeature);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.util.ExtendedMetaData#getNamespace(org.eclipse.emf.ecore.EPackage)
   */
  public String getNamespace(EPackage ePackage)
  {
    XMLResource.XMLInfo xmlInfo = getInfo(ePackage);

    if (xmlInfo != null)
    {
      String namespace = xmlInfo.getTargetNamespace();

      if (namespace != null)
      {
        return namespace;
      }
    }

    return super.getNamespace(ePackage);
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.emf.ecore.util.ExtendedMetaData#getType(org.eclipse.emf.ecore.EPackage,
   *      java.lang.String)
   */
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
  protected boolean isFeatureKindSpecific()
  {
    return false;
  }

}
