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
 * $Id: ExtendedMetaData.java,v 1.6 2004/12/23 19:33:00 elena Exp $
 */
package org.eclipse.emf.ecore.util;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * This class contains convenient static methods for working with EMF objects.
 */
public interface ExtendedMetaData
{
  String ANNOTATION_URI = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
  String XMLNS_URI = "http://www.w3.org/2000/xmlns/";
  String XML_URI = "http://www.w3.org/XML/1998/namespace";
  String XSI_URI = "http://www.w3.org/2001/XMLSchema-instance";
  String XML_SCHEMA_URI = "http://www.w3.org/2001/XMLSchema";
  String XMI_URI = "http://www.omg.org/XMI";
  String XMLNS_PREFIX = "xmlns";
  String XSI_PREFIX = "xsi";
  
  EPackage getPackage(String namespace);
  void putPackage(String namespace, EPackage ePackage);

  EClass getDocumentRoot(EPackage ePackage);
  void setDocumentRoot(EClass eClass);

  EReference getXMLNSPrefixMapFeature(EClass eClass);
  EReference getXSISchemaLocationMapFeature(EClass eClass);

  boolean isQualified(EPackage ePackage);
  void setQualified(EPackage ePackage, boolean isQualified);

  String getNamespace(EPackage ePackage);
  String getNamespace(EClassifier eClassifier);
  String getNamespace(EStructuralFeature eStructuralFeature);
  void setNamespace(EStructuralFeature eStructuralFeature, String namespace);

  String getName(EClassifier eClassifier);
  void setName(EClassifier eClassifier, String name);
  boolean isAnonymous(EClassifier eClassifier);

  String getName(EStructuralFeature eStructuralFeature);
  void setName(EStructuralFeature eStructuralFeature, String name);

  EClassifier getType(String namespace, String name);
  EStructuralFeature getAttribute(String namespace, String name);
  EStructuralFeature getElement(String namespace, String name);

  EClassifier getType(EPackage ePackage, String name);

  EStructuralFeature getAttribute(EClass eClass, String namespace, String name);
  EStructuralFeature getElement(EClass eClass, String namespace, String name);

  EStructuralFeature getSimpleFeature(EClass eClass);
  EAttribute getMixedFeature(EClass eClass);

  int UNSPECIFIED_FEATURE = 0;
  int SIMPLE_FEATURE = 1;
  int ATTRIBUTE_FEATURE = 2;
  int ATTRIBUTE_WILDCARD_FEATURE = 3;
  int ELEMENT_FEATURE = 4;
  int ELEMENT_WILDCARD_FEATURE = 5;
  int GROUP_FEATURE = 6;
  String [] FEATURE_KINDS = { "unspecified", "simple", "attribute", "attributeWildcard", "element", "elementWildcard", "group" };
  int getFeatureKind(EStructuralFeature eStructuralFeature);
  void setFeatureKind(EStructuralFeature eStructuralFeature, int kind);

  int UNSPECIFIED_CONTENT = 0;
  int EMPTY_CONTENT = 1;
  int SIMPLE_CONTENT = 2;
  int MIXED_CONTENT = 3;
  int ELEMENT_ONLY_CONTENT = 4;
  String [] CONTENT_KINDS = { "unspecified", "empty", "simple", "mixed", "elementOnly" };
  int getContentKind(EClass eClass);
  void setContentKind(EClass eClass, int kind);

  int UNSPECIFIED_DERIVATION = 0;
  int RESTRICTION_DERIVATION = 0;
  int LIST_DERIVATION = 0;
  int UNION_DERIVATION = 0;
  String [] DERIVATION_KINDS = { "unspecified", "restriction", "list", "union" };
  int getDerivationKind(EDataType eDataType);

  EDataType getBaseType(EDataType eDataType);
  void setBaseType(EDataType eDataType, EDataType baseType);

  EDataType getItemType(EDataType eDataType);
  void setItemType(EDataType eDataType, EDataType itemType);

  List /*EDataType*/ getMemberTypes(EDataType eDataType);
  void setMemberTypes(EDataType eDataType, List /*EDataType*/memberTypes);

  List /*EStructuralFeature*/ getAllAttributes(EClass eClass);
  List /*EStructuralFeature*/ getAllElements(EClass eClass);
 
  List /*EStructuralFeature*/ getAttributes(EClass eClass);
  List /*EStructuralFeature*/ getElements(EClass eClass);

  boolean matches(List /*String*/ wildcards, String namespace);
  boolean matches(String wildcard, String namespace);

  List /*String*/ getWildcards(EStructuralFeature eStructuralFeature);
  void setWildcards(EStructuralFeature eStructuralFeature, List wildcards);

  int UNSPECIFIED_PROCESSING = 0;
  int STRICT_PROCESSING = 1;
  int LAX_PROCESSING = 2;
  int SKIP_PROCESSING = 3;
  String [] PROCESSING_KINDS = { "unspecified", "strict", "lax", "skip" };
  int getProcessingKind(EStructuralFeature eStructuralFeature);
  void setProcessingKind(EStructuralFeature eStructuralFeature, int processingKind);

  EStructuralFeature getAffiliation(EStructuralFeature eStructuralFeature);
  void setAffiliation(EStructuralFeature eStructuralFeature, EStructuralFeature affiliation);

  EStructuralFeature getGroup(EStructuralFeature eStructuralFeature);
  void setGroup(EStructuralFeature eStructuralFeature, EStructuralFeature group);

  EStructuralFeature getAffiliation(EClass eClass, EStructuralFeature eStructuralFeature);

  EStructuralFeature getAttributeWildcardAffiliation(EClass eClass, String namespace, String name);
  EStructuralFeature getElementWildcardAffiliation(EClass eClass, String namespace, String name);

  int UNSPECIFIED_WHITE_SPACE = 0;
  int PRESERVE_WHITE_SPACE = 1;
  int REPLACE_WHITE_SPACE = 2;
  int COLLAPSE_WHITE_SPACE = 3;
  String [] WHITE_SPACE_KINDS = { "unspecified", "preserve", "replace", "collapse" };

  int getWhiteSpaceFacet(EDataType eDataType);
  void setWhiteSpaceFacet(EDataType eDataType, int whiteSpace);

  List getEnumerationFacet(EDataType eDataType);
  void setEnumerationFacet(EDataType eDataType, List literals);

  List getPatternFacet(EDataType eDataType);
  void setPatternFacet(EDataType eDataType, List pattern);

  int getTotalDigitsFacet(EDataType eDataType);
  void setTotalDigitsFacet(EDataType eDataType, int digits);

  int getFractionDigitsFacet(EDataType eDataType);
  void setFractionDigitsFacet(EDataType eDataType, int digits);

  int getLengthFacet(EDataType eDataType);
  void setLengthFacet(EDataType eDataType, int length);

  int getMinLengthFacet(EDataType eDataType);
  void setMinLengthFacet(EDataType eDataType, int length);

  int getMaxLengthFacet(EDataType eDataType);
  void setMaxLengthFacet(EDataType eDataType, int length);

  String getMinExclusiveFacet(EDataType eDataType);
  void setMinExclusiveFacet(EDataType eDataType, String literal);

  String getMaxExclusiveFacet(EDataType eDataType);
  void setMaxExclusiveFacet(EDataType eDataType, String literal);

  String getMinInclusiveFacet(EDataType eDataType);
  void setMinInclusiveFacet(EDataType eDataType, String literal);

  String getMaxInclusiveFacet(EDataType eDataType);
  void setMaxInclusiveFacet(EDataType eDataType, String literal);

  EPackage demandPackage(String namespace);
  EClassifier demandType(String namespace, String name);
  EStructuralFeature demandFeature(String namespace, String name, boolean isElement);
  EStructuralFeature demandFeature(String namespace, String name, boolean isElement, boolean isReference);

  Collection demandedPackages();

  ExtendedMetaData INSTANCE = new BasicExtendedMetaData();
}
