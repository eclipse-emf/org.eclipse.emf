/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
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
 * $Id: BasicExtendedMetaData.java,v 1.4 2004/04/18 23:19:40 emerks Exp $
 */
package org.eclipse.emf.ecore.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;


/**
 *  This is a basic implementation of the extended metadata API.
 */
public class BasicExtendedMetaData implements ExtendedMetaData
{
  protected String annotationURI;
  protected EPackage.Registry registry;
  protected EPackage.Registry demandRegistry;

  public BasicExtendedMetaData()
  {
    this(ANNOTATION_URI, EPackage.Registry.INSTANCE);
  }

  public BasicExtendedMetaData(EPackage.Registry registry)
  {
    this(ANNOTATION_URI, registry);
  }

  public BasicExtendedMetaData(String annotationURI, EPackage.Registry registry)
  {
    this.annotationURI = annotationURI;
    this.registry = registry;
    this.demandRegistry = new org.eclipse.emf.ecore.impl.EPackageRegistryImpl();
    // init();
  }

  protected EAnnotation getAnnotation(EModelElement eModelElement, boolean demandCreate)
  {
    EAnnotation result = eModelElement.getEAnnotation(annotationURI);
    if (result == null && demandCreate)
    {
      result = EcoreFactory.eINSTANCE.createEAnnotation();
      result.setSource(annotationURI);
      eModelElement.getEAnnotations().add(result);
    }
    return result;
  }

  public EClassifier getType(EPackage ePackage, String name)
  {
    for (Iterator i = ePackage.getEClassifiers().iterator(); i.hasNext(); )
    {
      EClassifier eClassifier = (EClassifier)i.next();
      if (name.equals(getName(eClassifier)))
      {
        return eClassifier;
      }
    }
    return null;
  }

  public EPackage getPackage(String namespace)
  {
    EPackage ePackage = registry.getEPackage(namespace);
    if (ePackage == null)
    {
      ePackage = demandRegistry.getEPackage(namespace);
    }
    return ePackage;
  }

  public void putPackage(String namespace, EPackage ePackage)
  {
    registry.put(namespace, ePackage);
  }

  public EClass getDocumentRoot(EPackage ePackage)
  {
    return (EClass)getType(ePackage, "");
  }

  public void setDocumentRoot(EClass eClass)
  {
    setName(eClass, "");
    setContentKind(eClass, MIXED_CONTENT);
  }


  public EReference getXMLNSPrefixMapFeature(EClass eClass)
  {
    if (getContentKind(eClass) == MIXED_CONTENT)
    {
      for (Iterator i = eClass.getEAllReferences().iterator(); i.hasNext(); )
      {
        EReference eReference = (EReference)i.next();
        if ("xmlns:prefix".equals(getName(eReference)))
        {
          return eReference;
        }
      }
    }

    return null;
  }

  public EReference getXSISchemaLocationMapFeature(EClass eClass)
  {
    if (getContentKind(eClass) == MIXED_CONTENT)
    {
      for (Iterator i = eClass.getEAllReferences().iterator(); i.hasNext(); )
      {
        EReference eReference = (EReference)i.next();
        if ("xsi:schemaLocation".equals(getName(eReference)))
        {
          return eReference;
        }
      }
    }

    return null;
  }

  public boolean isQualified(EPackage ePackage)
  {
    EAnnotation eAnnotation = getAnnotation(ePackage, false);
    return eAnnotation == null || !"false".equals(eAnnotation.getDetails().get("qualified"));
  }

  public void setQualified(EPackage ePackage, boolean isQualified)
  {
    if (!isQualified)
    {
      EAnnotation eAnnotation = getAnnotation(ePackage, true);
      eAnnotation.getDetails().put("qualified", "false");
    }
    else
    {
      EAnnotation eAnnotation = getAnnotation(ePackage, false);
      if (eAnnotation != null)
      {
        eAnnotation.getDetails().remove("qualified");
      }
    }
  }

  public String getNamespace(EPackage ePackage)
  {
    if (isQualified(ePackage))
    {
      return (ePackage).getNsURI();
    }
    else
    {
      return null;
    }
  }

  public String getNamespace(EClassifier eClassifier)
  {
    return getNamespace(eClassifier.getEPackage());
  }

  public String getNamespace(EStructuralFeature eStructuralFeature)
  {
    EAnnotation eAnnotation = getAnnotation(eStructuralFeature, false);
    if (eAnnotation == null)
    {
      return null;
    }
    else
    {
      String result = (String)eAnnotation.getDetails().get("namespace");
      if ("##targetNamespace".equals(result))
      {
        return getNamespace(eStructuralFeature.getEContainingClass().getEPackage());
      }
      else
      {
        return result;
      }
    }
  }

  public void setNamespace(EStructuralFeature eStructuralFeature, String namespace)
  {
    String packageNamespace = getNamespace(eStructuralFeature.getEContainingClass().getEPackage());
    if (namespace == null ? packageNamespace == null : namespace.equals(packageNamespace))
    {
      namespace="##targetNamespace";
    }

    if (namespace != null)
    {
      EAnnotation eAnnotation = getAnnotation(eStructuralFeature, true);
      eAnnotation.getDetails().put("namespace", namespace);
    }
    else
    {
      EAnnotation eAnnotation = getAnnotation(eStructuralFeature, false);
      if (eAnnotation != null)
      {
        eAnnotation.getDetails().remove("namespace");
      }
    }
  }

  public String getName(ENamedElement eNamedElement)
  {
    EAnnotation eAnnotation = getAnnotation(eNamedElement, false);
    if (eAnnotation != null)
    {
      String result = (String)eAnnotation.getDetails().get("name");
      if (result != null)
      {
        return result;
      }
    }
    return eNamedElement.getName();
  }

  public void setName(ENamedElement eNamedElement, String name)
  {
    EAnnotation eAnnotation = getAnnotation(eNamedElement, true);
    eAnnotation.getDetails().put("name", name);
  }

  protected String getQualifiedName(String defaultNamespace, EClassifier eClassifier)
  {
    String namespace = getNamespace(eClassifier);
    String name = getName(eClassifier);
    if (namespace == null)
    {
      return namespace == defaultNamespace ? name : "#" + name;
    }
    else
    {
      return namespace.equals(defaultNamespace) ? name : namespace + "#" + name;
    }
  }

  protected String getQualifiedName(String defaultNamespace, EStructuralFeature eStructuralFeature)
  {
    String namespace = getNamespace(eStructuralFeature);
    String name = getName(eStructuralFeature);
    if (namespace == null)
    {
      return namespace == defaultNamespace ? name : "#" + name;
    }
    else
    {
      return namespace.equals(defaultNamespace) ? name : namespace + "#" + name;
    }
  }

  public EClassifier getType(String namespace, String name)
  {
    EPackage ePackage = getPackage(namespace);
    return ePackage == null ? null : getType(ePackage, name);
  }

  public EStructuralFeature getAttribute(String namespace, String name)
  {
    EPackage ePackage = getPackage(namespace);
    if (ePackage != null)
    {
      EClass documentRoot = getDocumentRoot(ePackage);
      if (documentRoot != null)
      {
        return getLocalAttribute(documentRoot, namespace, name);
      }
    }

    return null;
  }

  public EStructuralFeature getElement(String namespace, String name)
  {
    EPackage ePackage = getPackage(namespace);
    if (ePackage != null)
    {
      EClass documentRoot = getDocumentRoot(ePackage);
      if (documentRoot != null)
      {
        return getLocalElement(documentRoot, namespace, name);
      }
    }

    return null;
  }

  public int getFeatureKind(EStructuralFeature eStructuralFeature)
  {
    EAnnotation eAnnotation = getAnnotation(eStructuralFeature, false);
    if (eAnnotation != null)
    {
      Object kind = eAnnotation.getDetails().get("kind");
      if (kind != null)
      {
        for (int i = 1; i < FEATURE_KINDS.length; ++i)
        {
          if (FEATURE_KINDS[i].equals(kind))
          {
            return i;
          }
        }
      }
    }

    return 0;
  }

  public void setFeatureKind(EStructuralFeature eStructuralFeature, int kind)
  {
    if (kind > 0 && kind < FEATURE_KINDS.length)
    {
      EAnnotation eAnnotation = getAnnotation(eStructuralFeature, true);
      eAnnotation.getDetails().put("kind", FEATURE_KINDS[kind]);
    }
    else
    {
      EAnnotation eAnnotation = getAnnotation(eStructuralFeature, false);
      if (eAnnotation != null)
      {
        eAnnotation.getDetails().remove("kind");
      }
    }
  }

  public int getContentKind(EClass eClass)
  {
    EAnnotation eAnnotation = getAnnotation(eClass, false);
    if (eAnnotation != null)
    {
      Object kind = eAnnotation.getDetails().get("kind");
      if (kind != null)
      {
        for (int i = 1; i < CONTENT_KINDS.length; ++i)
        {
          if (CONTENT_KINDS[i].equals(kind))
          {
            return i;
          }
        }
      }
    }

    return 0;
  }

  public void setContentKind(EClass eClass, int kind)
  {
    if (kind > 0 && kind < CONTENT_KINDS.length)
    {
      EAnnotation eAnnotation = getAnnotation(eClass, true);
      eAnnotation.getDetails().put("kind", CONTENT_KINDS[kind]);
    }
    else
    {
      EAnnotation eAnnotation = getAnnotation(eClass, false);
      if (eAnnotation != null)
      {
        eAnnotation.getDetails().remove("kind");
      }
    }
  }

  public int getDerivationKind(EDataType eDataType)
  {
    EAnnotation eAnnotation = getAnnotation(eDataType, false);
    if (eAnnotation != null)
    {
      EMap details = eAnnotation.getDetails();
      Object kind = details.get("restriction");
      if (kind != null)
      {
        return RESTRICTION_DERIVATION;
      }
      kind = details.get("list");
      if (kind != null)
      {
        return LIST_DERIVATION;
      }
      kind = details.get("union");
      if (kind != null)
      {
        return UNION_DERIVATION;
      }
    }

    return 0;
  }

  public EDataType getBaseType(EDataType eDataType)
  {
    EAnnotation eAnnotation = getAnnotation(eDataType, false);
    if (eAnnotation != null)
    {
      EMap details = eAnnotation.getDetails();
      String baseType = (String)details.get("baseType");
      if (baseType != null)
      {
        int index = baseType.lastIndexOf("#");
        EClassifier type = 
          index == -1 ?
            getType(eDataType.getEPackage(), baseType) :
            index == 0 ?
              getType((String)null, baseType.substring(1)) :
              getType(baseType.substring(0, index), baseType.substring(index + 1));
        if (type instanceof EDataType)
        {
          return (EDataType)type;
        }
      }
    }

    return null;
  }

  public void setBaseType(EDataType eDataType, EDataType baseType)
  {
    if (baseType == null)
    {
      EAnnotation eAnnotation = getAnnotation(eDataType, false);
      if (eAnnotation != null)
      {
        eAnnotation.getDetails().remove("baseType");
      }
    }
    else
    {
      EAnnotation eAnnotation = getAnnotation(eDataType, true);
      eAnnotation.getDetails().put("baseType", getQualifiedName(getNamespace(eDataType), baseType));
    }
  }

  public EDataType getItemType(EDataType eDataType)
  {
    EAnnotation eAnnotation = getAnnotation(eDataType, false);
    if (eAnnotation != null)
    {
      EMap details = eAnnotation.getDetails();
      String itemType = (String)details.get("itemType");
      if (itemType != null)
      {
        int index = itemType.lastIndexOf("#");
        EClassifier type = 
          index == -1 ?
            getType(eDataType.getEPackage(), itemType) :
            index == 0 ?
              getType((String)null, itemType.substring(1)) :
              getType(itemType.substring(0, index), itemType.substring(index + 1));
        if (type instanceof EDataType)
        {
          return (EDataType)type;
        }
      }
    }

    return null;
  }

  public void setItemType(EDataType eDataType, EDataType itemType)
  {
    if (itemType == null)
    {
      EAnnotation eAnnotation = getAnnotation(eDataType, false);
      if (eAnnotation != null)
      {
        eAnnotation.getDetails().remove("itemType");
      }
    }
    else
    {
      EAnnotation eAnnotation = getAnnotation(eDataType, true);
      eAnnotation.getDetails().put("itemType", getQualifiedName(getNamespace(eDataType), itemType));
    }
  }

  public List /*EDataType*/ getMemberTypes(EDataType eDataType)
  {
    EAnnotation eAnnotation = getAnnotation(eDataType, false);
    if (eAnnotation != null)
    {
      String memberTypes = (String)eAnnotation.getDetails().get("memberTypes");
      if (memberTypes != null)
      {
        List result = new ArrayList();
        for (StringTokenizer stringTokenizer = new StringTokenizer(memberTypes); stringTokenizer.hasMoreTokens(); )
        {
          String member = stringTokenizer.nextToken();
          int index = member.lastIndexOf("#");
          EClassifier type = 
            index == -1 ?
              getType(eDataType.getEPackage(), member) :
              index == 0 ?
                getType((String)null, member.substring(1)) :
                getType(member.substring(0, index), member.substring(index + 1));
          if (type instanceof EDataType)
          {
            result.add(type);
          }
        }
        return result;
      }
    }

    return Collections.EMPTY_LIST;
  }

  public void setMemberTypes(EDataType eDataType, List /*EDataType*/ memberTypes)
  {
    if (memberTypes.isEmpty())
    {
      EAnnotation eAnnotation = getAnnotation(eDataType, false);
      if (eAnnotation != null)
      {
        eAnnotation.getDetails().remove("memberTypes");
      }
    }
    else
    {
      EAnnotation eAnnotation = getAnnotation(eDataType, true);
      String namespace = getNamespace(eDataType);
      StringBuffer result = new StringBuffer();
      for (Iterator i = memberTypes.iterator(); i.hasNext(); )
      {
        result.append(getQualifiedName(namespace, (EDataType)i.next()));
        result.append(' ');
      }
      eAnnotation.getDetails().put("memberTypes", result.substring(0, result.length() - 1));
    }
  }

  public EStructuralFeature getLocalAttribute(EClass eClass, String namespace, String name)
  {
    for (Iterator i = getAllAttributes(eClass).iterator(); i.hasNext(); )
    {
      EStructuralFeature eStructuralFeature = (EStructuralFeature)i.next();
      if (name.equals(getName(eStructuralFeature)) &&
            (namespace == null ? getNamespace(eStructuralFeature) == null : namespace.equals(getNamespace(eStructuralFeature))))
      {
        return eStructuralFeature;
      }
    }

    return null;
  }

  public EStructuralFeature getAttribute(EClass eClass, String namespace, String name)
  {
    EStructuralFeature result = getLocalAttribute(eClass, namespace, name);
    if (result == null)
    {
      result = getAttribute(namespace, name);
      if (result != null && getAffiliation(eClass, result) == null)
      {
        return null;
      }
    }
    return result;
  }

  protected EStructuralFeature getLocalElement(EClass eClass, String namespace, String name)
  {
    for (Iterator i = getAllElements(eClass).iterator(); i.hasNext(); )
    {
      EStructuralFeature eStructuralFeature = (EStructuralFeature)i.next();
      if (name.equals(getName(eStructuralFeature)) &&
            (namespace == null ? getNamespace(eStructuralFeature) == null : namespace.equals(getNamespace(eStructuralFeature))))
      {
        return eStructuralFeature;
      }
    }

    return null;
  }

  public EStructuralFeature getElement(EClass eClass, String namespace, String name)
  {
    EStructuralFeature result = getLocalElement(eClass, namespace, name);
    if (result == null)
    {
      result = getElement(namespace, name);
      if (result != null && getAffiliation(eClass, result) == null)
      {
        return null;
      }
    }
    return result;
  }

  public List /*EStructuralFeature*/ getAllAttributes(EClass eClass)
  {
    List result = new ArrayList();
    for (Iterator i = eClass.getESuperTypes().iterator(); i.hasNext(); )
    {
      EClass eSuperType = (EClass)i.next();
      result.addAll(getAllAttributes(eSuperType));
    }

    result.addAll(getAttributes(eClass));
    return result;
  }

  public EStructuralFeature getSimpleFeature(EClass eClass)
  {
    if (getContentKind(eClass) == SIMPLE_CONTENT)
    {
      for (Iterator i = eClass.getEAllStructuralFeatures().iterator(); i.hasNext(); )
      {
        EStructuralFeature eStructuralFeature = (EStructuralFeature)i.next();
        if (getFeatureKind(eStructuralFeature) == ExtendedMetaData.SIMPLE_FEATURE)
        {
          return eStructuralFeature;
        }
      }
    }

    return null;
  }

  public EAttribute getMixedFeature(EClass eClass)
  {
    switch (getContentKind(eClass))
    {
      case MIXED_CONTENT:
      case SIMPLE_CONTENT:
      {
        for (Iterator i = eClass.getEAllAttributes().iterator(); i.hasNext(); )
        {
          EAttribute eAttribute = (EAttribute)i.next();
          if (getFeatureKind(eAttribute) == ExtendedMetaData.ELEMENT_WILDCARD_FEATURE)
          {
            return eAttribute;
          }
        }
        break;
      }
    }

    return null;
  }

  public List /*EStructuralFeature*/ getAllElements(EClass eClass)
  {
    // REVISIT: this may affect performance. 
    // In general, there is no multiple inheritance. 
    // The multiple inheritance case handled here 
    // is for org.eclipse.emf.ecore.sdo.EDataObjectSimpleAnyType
    List result = new UniqueEList();
    for (Iterator i = eClass.getESuperTypes().iterator(); i.hasNext(); )
    {
      EClass eSuperType = (EClass)i.next();
      result.addAll(getAllElements(eSuperType));
    }

    result.addAll(getElements(eClass));
    return result;
  }

  public List /*EStructuralFeature*/ getAttributes(EClass eClass)
  {
    List result = new ArrayList();
    for (Iterator i = eClass.getEStructuralFeatures().iterator(); i.hasNext(); )
    {
      EStructuralFeature eStructuralFeature = (EStructuralFeature)i.next();
      switch (getFeatureKind(eStructuralFeature))
      {
        case ATTRIBUTE_FEATURE:
        case ATTRIBUTE_WILDCARD_FEATURE:
        {
          result.add(eStructuralFeature);
        }
      }
    }

    return result;
  }

  public List /*EStructuralFeature*/ getElements(EClass eClass)
  {
    List result = new ArrayList();
    for (Iterator i = eClass.getEStructuralFeatures().iterator(); i.hasNext(); )
    {
      EStructuralFeature eStructuralFeature = (EStructuralFeature)i.next();
      switch (getFeatureKind(eStructuralFeature))
      {
        case ELEMENT_FEATURE:
        case ELEMENT_WILDCARD_FEATURE:
        case GROUP_FEATURE:
        {
          result.add(eStructuralFeature);
          break;
        }
      }
    }

    return result;
  }

  public List /*String*/ getWildcards(EStructuralFeature eStructuralFeature)
  {
    EAnnotation eAnnotation = getAnnotation(eStructuralFeature, false);
    if (eAnnotation != null)
    {
      String wildcards = (String)eAnnotation.getDetails().get("wildcards");
      if (wildcards != null)
      {
        List result = new ArrayList();
        for (StringTokenizer stringTokenizer = new StringTokenizer(wildcards); stringTokenizer.hasMoreTokens(); )
        {
          String wildcard = stringTokenizer.nextToken();
          if (wildcard.equals("##other"))
          {
            result.add("!##" + getNamespace(eStructuralFeature.getEContainingClass().getEPackage()));
          }
          else if (wildcard.equals("##local"))
          {
            result.add(null);
          }
          else if (wildcard.equals("##targetNamespace"))
          {
            result.add(getNamespace(eStructuralFeature.getEContainingClass().getEPackage()));
          }
          else
          {
            result.add(wildcard);
          }
        }
        return result;
      }
    }

    return Collections.EMPTY_LIST;
  }

  public void setWildcards(EStructuralFeature eStructuralFeature, List wildcards)
  {
    if (wildcards.isEmpty())
    {
      EAnnotation eAnnotation = getAnnotation(eStructuralFeature, false);
      if (eAnnotation != null)
      {
        eAnnotation.getDetails().remove("wildcards");
        eAnnotation.getDetails().remove("name");
      }
    }
    else
    {
      String namespace = getNamespace(eStructuralFeature.getEContainingClass().getEPackage());
      EAnnotation eAnnotation = getAnnotation(eStructuralFeature, true);
      StringBuffer value = new StringBuffer();
      for (Iterator i = wildcards.iterator(); i.hasNext(); )
      {
        String wildcard = (String)i.next();
        if (wildcard == null)
        {
          if (namespace == null)
          {
            value.append("##targetNamespace");
          }
          else 
          {
            value.append("##local");
          }
        }
        else if (wildcard.startsWith("!##"))
        {
          if (namespace == null ? 
                wildcard.length() == 3 : 
                wildcard.endsWith(namespace) && wildcard.length() == namespace.length() + 3)
          {
            value.append("##other");
          }
          else
          {
            value.append(wildcard);
          }
        }
        else if (wildcard.equals(namespace))
        {
          value.append("##targetNamespace");
        }
        else
        {
          value.append(wildcard);
        }
      }
      eAnnotation.getDetails().put("wildcards", value.toString());
      eAnnotation.getDetails().put("name","");
    }
  }

  public int getProcessingKind(EStructuralFeature eStructuralFeature)
  {
    EAnnotation eAnnotation = getAnnotation(eStructuralFeature, false);
    if (eAnnotation != null)
    {
      Object kind = eAnnotation.getDetails().get("processing");
      if (kind != null)
      {
        for (int i = 1; i < PROCESSING_KINDS.length; ++i)
        {
          if (PROCESSING_KINDS[i].equals(kind))
          {
            return i;
          }
        }
      }
    }

    return 0;
  }

  public void setProcessingKind(EStructuralFeature eStructuralFeature, int kind)
  {
    if (kind > 0 && kind < PROCESSING_KINDS.length)
    {
      EAnnotation eAnnotation = getAnnotation(eStructuralFeature, true);
      eAnnotation.getDetails().put("processing", PROCESSING_KINDS[kind]);
    }
    else
    {
      EAnnotation eAnnotation = getAnnotation(eStructuralFeature, false);
      if (eAnnotation != null)
      {
        eAnnotation.getDetails().remove("processing");
      }
    }
  }

  public EStructuralFeature getGroup(EStructuralFeature eStructuralFeature)
  {
    EAnnotation eAnnotation = getAnnotation(eStructuralFeature, false);
    if (eAnnotation != null)
    {
      String qualifiedName = (String)eAnnotation.getDetails().get("group");
      if (qualifiedName != null)
      {
        int fragmentIndex = qualifiedName.lastIndexOf('#');
        if (fragmentIndex == -1)
        {
          return 
            getElement
              (eStructuralFeature.getEContainingClass(), 
               getNamespace(eStructuralFeature.getEContainingClass().getEPackage()), 
               qualifiedName);
        }
        else if (fragmentIndex == 0)
        {
          return 
            getElement
              (eStructuralFeature.getEContainingClass(), 
               null, 
               qualifiedName.substring(1));
        }
        else
        {
          return 
            getElement
              (eStructuralFeature.getEContainingClass(), 
               qualifiedName.substring(0, fragmentIndex), 
               qualifiedName.substring(fragmentIndex + 1));
        }
      }
    }
    return null;
  }

  public void setGroup(EStructuralFeature eStructuralFeature, EStructuralFeature group)
  {
    if (group == null)
    {
      EAnnotation eAnnotation = getAnnotation(eStructuralFeature, false);
      if (eAnnotation != null)
      {
        eAnnotation.getDetails().remove("group");
      }
    }
    else
    {
      EAnnotation eAnnotation = getAnnotation(eStructuralFeature, true);
      eAnnotation.getDetails().put
        ("group", getQualifiedName(getNamespace(eStructuralFeature.getEContainingClass().getEPackage()), group));
    }
  }

  public EStructuralFeature getAffiliation(EStructuralFeature eStructuralFeature)
  {
    EAnnotation eAnnotation = getAnnotation(eStructuralFeature, false);
    if (eAnnotation != null)
    {
      String qualifiedName = (String)eAnnotation.getDetails().get("affiliation");
      if (qualifiedName != null)
      {
        int fragmentIndex = qualifiedName.lastIndexOf('#');
        if (fragmentIndex == -1)
        {
          return getElement(getNamespace(eStructuralFeature.getEContainingClass().getEPackage()), qualifiedName);
        }
        else if (fragmentIndex == 0)
        {
          return getElement(null, qualifiedName.substring(1));
        }
        else
        {
          return getElement(qualifiedName.substring(0, fragmentIndex), qualifiedName.substring(fragmentIndex + 1));
        }
      }
    }
    return null;
  }

  public void setAffiliation(EStructuralFeature eStructuralFeature, EStructuralFeature affiliation)
  {
    if (affiliation == null)
    {
      EAnnotation eAnnotation = getAnnotation(eStructuralFeature, false);
      if (eAnnotation != null)
      {
        eAnnotation.getDetails().remove("affiliation");
      }
    }
    else
    {
      EAnnotation eAnnotation = getAnnotation(eStructuralFeature, true);
      eAnnotation.getDetails().put
        ("affiliation", getQualifiedName(getNamespace(eStructuralFeature.getEContainingClass().getEPackage()), affiliation));
    }
  }

  public EStructuralFeature getAffiliation(EClass eClass, EStructuralFeature eStructuralFeature)
  {
    List eAllStructuralFeatures = eClass.getEAllStructuralFeatures();
    if (eAllStructuralFeatures.contains(eStructuralFeature))
    {
      return eStructuralFeature;
    }

    switch (getFeatureKind(eStructuralFeature))
    {
      case ATTRIBUTE_FEATURE:
      {
        String namespace = getNamespace(eStructuralFeature);
        String name = getName(eStructuralFeature);
        EStructuralFeature result = getLocalAttribute(eClass, namespace, name);
        if (result != null)
        {
          return result;
        }

        for (Iterator i = getAllAttributes(eClass).iterator(); i.hasNext(); )
        {
          result = (EStructuralFeature)i.next();
          if (matches(getWildcards(result), namespace))
          {
            return result;
          }
        }

        return null;
      }
      case ELEMENT_FEATURE:
      {
        for (EStructuralFeature affiliation = eStructuralFeature; affiliation != null; affiliation = getAffiliation(affiliation))
        {
          String namespace = getNamespace(affiliation);
          String name = getName(affiliation);
          EStructuralFeature result = getLocalElement(eClass, namespace, name);
          if (result != null)
          {
            return result;
          }
        }

        String namespace = getNamespace(eStructuralFeature);
        for (Iterator i = getAllElements(eClass).iterator(); i.hasNext(); )
        {
          EStructuralFeature result = (EStructuralFeature)i.next();
          if (matches(getWildcards(result), namespace))
          {
            return result;
          }
        }
        return null;
      }
      default:
      {
        return null;
      }
    }
  }

  public EStructuralFeature getAttributeWildcardAffiliation(EClass eClass, String namespace, String name)
  {
    for (Iterator i = getAllAttributes(eClass).iterator(); i.hasNext(); )
    {
      EStructuralFeature result = (EStructuralFeature)i.next();
      if (matches(getWildcards(result), namespace))
      {
        return result;
      }
    }

    return null;
  }

  public EStructuralFeature getElementWildcardAffiliation(EClass eClass, String namespace, String name)
  {
    for (Iterator i = getAllElements(eClass).iterator(); i.hasNext(); )
    {
      EStructuralFeature result = (EStructuralFeature)i.next();
      if (matches(getWildcards(result), namespace))
      {
        return result;
      }
    }

    return null;
  }

  public boolean matches(List wildcards, String namespace)
  {
    if (!wildcards.isEmpty())
    {
      for (Iterator j = wildcards.iterator(); j.hasNext(); )
      {
        String wildcard = (String)j.next();
        if (matches(wildcard, namespace))
        {
          return true;
        }
      }
    }

    return false;
  }

  public boolean matches(String wildcard, String namespace)
  {
    return
      wildcard == null ?
        namespace == null :
        wildcard.startsWith("!##") ?
           namespace == null ? 
             wildcard.length() != 3 : 
             !wildcard.endsWith(namespace) || wildcard.length() != namespace.length() + 3 :
           wildcard.equals("##any") || wildcard.equals(namespace);
  }

  public EPackage demandPackage(String namespace)
  {
    EPackage ePackage = demandRegistry.getEPackage(namespace);
    if (ePackage == null)
    {
      ePackage = EcoreFactory.eINSTANCE.createEPackage();
      ePackage.setNsURI(namespace);
      setQualified(ePackage, namespace != null);
      if (namespace != null)
      {
        ePackage.setNsPrefix("_");
      }
      demandRegistry.put(namespace, ePackage);

      // demandDocumentRoot(ePackage);

      EClass documentRootEClass = EcoreFactory.eINSTANCE.createEClass();
      documentRootEClass.getESuperTypes().add(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot());
      documentRootEClass.setName("DocumentRoot");
      ePackage.getEClassifiers().add(documentRootEClass);
      setDocumentRoot(documentRootEClass);
    }
    return ePackage;
  }

  public EClassifier demandType(String namespace, String name)
  {
    EPackage ePackage = demandPackage(namespace);
    EClassifier eClassifier = getType(ePackage, name);
    if (eClassifier != null)
    {
      return eClassifier;
    }
    else
    {
      EClass eClass = EcoreFactory.eINSTANCE.createEClass();
      eClass.setName(name);
      eClass.getESuperTypes().add(XMLTypePackage.eINSTANCE.getAnyType());
      setContentKind(eClass, MIXED_CONTENT);
      ePackage.getEClassifiers().add(eClass);
      return eClass;
    }
  }

  public EStructuralFeature demandFeature(String namespace, String name, boolean isElement)
  {
    return demandFeature(namespace, name, isElement, isElement);
  }

  public EStructuralFeature demandFeature(String namespace, String name, boolean isElement, boolean isReference)
  {
    EPackage ePackage = demandPackage(namespace);
    EClass documentRootEClass = getDocumentRoot(ePackage);
    EStructuralFeature eStructuralFeature = 
      isElement ? 
        getLocalElement(documentRootEClass, namespace, name) : 
        getLocalAttribute(documentRootEClass, namespace, name);
    if (eStructuralFeature != null)
    {
      return eStructuralFeature;
    }
    else
    {
      if (isReference)
      {
        EReference eReference = EcoreFactory.eINSTANCE.createEReference();
        eReference.setContainment(isElement);
        eReference.setEType(EcorePackage.eINSTANCE.getEObject());
        eReference.setName(name);
        eReference.setDerived(true);
        eReference.setTransient(true);
        eReference.setVolatile(true);
        documentRootEClass.getEStructuralFeatures().add(eReference);

        setFeatureKind(eReference, isElement ? ELEMENT_FEATURE : ATTRIBUTE_FEATURE);
        setNamespace(eReference, namespace);

        // Mark the bound as unspecified so that it won't be considered many
        // but can nevertheless be recognized as being unspecified and perhaps still be treat as many.
        //
        eReference.setUpperBound(-2);

        return eReference;
      }
      else
      {
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eAttribute.setName(name);
        eAttribute.setEType(XMLTypePackage.eINSTANCE.getAnySimpleType());
        eAttribute.setDerived(true);
        eAttribute.setTransient(true);
        eAttribute.setVolatile(true);
        documentRootEClass.getEStructuralFeatures().add(eAttribute);

        setFeatureKind(eAttribute, isElement ? ELEMENT_FEATURE : ATTRIBUTE_FEATURE);
        setNamespace(eAttribute, namespace);

        // Mark the bound as unspecified so that it won't be considered many
        // but can nevertheless be recognized as being unspecified and perhaps still be treat as many.
        //
        eAttribute.setUpperBound(-2);

        return eAttribute;
      }
    }
  }

  public Collection demandedPackages()
  {
    return demandRegistry.values();
  }
}
