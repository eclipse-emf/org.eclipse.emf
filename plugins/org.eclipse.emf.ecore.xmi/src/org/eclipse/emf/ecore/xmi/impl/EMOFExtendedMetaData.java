/**
 * Copyright (c) 2003-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xmi.impl;

import java.util.HashMap;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;


public class EMOFExtendedMetaData extends BasicExtendedMetaData
{
  public static final String EMOF_PACKAGE_NS_PREFIX = "emof";
  /**
   * See <a href="http://www.omg.org/docs/html/06-01-01/Output/apndx_a.html#77093">OMG Issue 77093</a> but note that it has a typo and that "orb" should be "org".
   * @since 2.4
   */
  public static final String EMOF_PACKAGE_NS_URI_2_0 = "http://schema.omg.org/spec/MOF/2.0/emof.xml";
  public static final String EMOF_PACKAGE_NS_URI = "http://schema.omg.org/spec/mof/2.0/emof.xmi";

  public static final String EXTENSION = "Extension";
  public static final String XMI_EXTENSION_ELEMENT = XMIResource.XMI_NS + ":" + EXTENSION;
  public static final String XMI_EXTENDER_ATTRIBUTE = "extender";
  public static final String EMOF_XMI_EXTENDER = EcorePackage.eNS_URI;

  public static final String ECORE_EDATATYPE_HREF_PREFIX = EcorePackage.eNS_URI + "#//";
  public static final String UNMAPPED_EMOF_EDATATYPE_HREF_PREFIX = EcorePackage.eNS_URI + ".emof#ecore.";
  public static final String MAPPED_EMOF_EDATATYPE_HREF_PREFIX = EMOF_PACKAGE_NS_URI + "#";
  /**
   * @since 2.4
   */
  public static final String MAPPED_EMOF_EDATATYPE_HREF_PREFIX_2_0 = EMOF_PACKAGE_NS_URI_2_0 + "#";

  public static final String[] MAPPED_ECORE_EDATATYPES = { "EString", "EBoolean", "EInt", "EBigInteger" };
  public static final String[] MAPPED_EMOF_EDATATYPES = { "String", "Boolean", "Integer", "UnlimitedNatural" };

  public static final String TAG = "Tag";
  public static final String EMOF_TAG = EMOF_PACKAGE_NS_PREFIX + ":" + TAG;
  public static final String EMOF_TAG_NAME = "name";
  public static final String EMOF_TAG_VALUE = "value";
  public static final String EMOF_TAG_ELEMENT = "element";

  public static final String EMOF_OWNED_COMMENT = "ownedComment";
  public static final String EMOF_COMMENT_BODY = "body";
  public static final String EMOF_COMMENT_ANNOTATION_SOURCE = EMOF_PACKAGE_NS_URI_2_0 + "#Comment";

  public static final String EMOF_PROPERTY_CLASS_NAME = "Property";
  public static final String EMOF_PROPERTY_OPPOSITE_ROLE_NAME_ANNOTATION_SOURCE = EMOF_PACKAGE_NS_URI_2_0 + "#Property.oppositeRoleName";

  /**
   * The content type ID for EMOF.
   * @since 2.4
   */
  public static final String CONTENT_TYPE = "org.eclipse.emf.emof";

  protected XMLResource.XMLMap xmlMap;

  public EMOFExtendedMetaData(XMLResource.XMLMap xmlMap)
  {
    super();
    extendedMetaDataHolderCache = new HashMap<EModelElement, Object>();
    this.xmlMap = xmlMap;
  }

  @Override
  public String getNamespace(EPackage ePackage)
  {
    return ePackage == EcorePackage.eINSTANCE ? EMOF_PACKAGE_NS_URI_2_0 : super.getNamespace(ePackage);
  }

  @Override
  public EPackage getPackage(String namespace)
  {
    return EMOF_PACKAGE_NS_URI.equals(namespace) ||  EMOF_PACKAGE_NS_URI_2_0.equals(namespace) ? EcorePackage.eINSTANCE : super.getPackage(namespace);
  }

  @Override
  public String getName(EClassifier eClassifier)
  {
    XMLResource.XMLInfo info = xmlMap.getInfo(eClassifier);
    if (info != null)
    {
      String name = info.getName();
      if (name != null)
      {
        return info.getName();
      }
    }
    return super.getName(eClassifier);
  }
  
  @Override
  public String getName(EStructuralFeature eStructuralFeature)
  {
    XMLResource.XMLInfo info = xmlMap.getInfo(eStructuralFeature);
    if (info != null)
    {
      String name = info.getName();
      if (name != null)
      {
        return info.getName();
      }
    }
    return super.getName(eStructuralFeature);
  }

  @Override
  public EClassifier getType(EPackage ePackage, String name)
  {
    EClassifier eClassifier = super.getType(ePackage, name);
    if (eClassifier == null)
    {
      eClassifier = xmlMap.getClassifier(ePackage.getNsURI(), name);
    }
    return eClassifier;
  }

  @Override
  public int getFeatureKind(EStructuralFeature feature)
  {
    XMLResource.XMLInfo info = xmlMap.getInfo(feature);
    if (info != null)
    {
      switch (info.getXMLRepresentation())
      {
        case XMLResource.XMLInfo.ELEMENT:
          return ExtendedMetaData.ELEMENT_FEATURE;
        case XMLResource.XMLInfo.ATTRIBUTE:
          return ExtendedMetaData.ATTRIBUTE_FEATURE;
      }
    }
    return super.getFeatureKind(feature);
  }

  @Override
  protected EPackageExtendedMetaData createEPackageExtendedMetaData(EPackage ePackage)
  {
    return 
      new EPackageExtendedMetaDataImpl(ePackage)
      {
        @Override
        public EClassifier getType(String name)
        {
          if (ePackage == EcorePackage.eINSTANCE)
          {
            // Ensure that the map for Ecore is not repeatedly populated and that Property maps to EReference rather than EAttribute.
            //
            if (nameToClassifierMap == null)
            {
              super.getType(name);
              nameToClassifierMap.put("Property", EcorePackage.Literals.EREFERENCE);
            }
            return nameToClassifierMap.get(name);
          }
          else
          {
            return super.getType(name);
          }
        }
      };
  }
}
