/**
 * <copyright>
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: XSDValidateTest.java,v 1.28 2011/10/26 08:05:07 emerks Exp $
 */
package org.eclipse.emf.test.xml.xsdecore;


import java.io.File;
import java.io.IOException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.xml.AllSuites;

import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;
import org.eclipse.xsd.util.XSDResourceImpl;


/**
 * This test is designed to validate XML Schema files and compare those against expected output.
 */
public class XSDValidateTest extends TestCase
{

  final static String BASE_URI = TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/xsd/invalid/";
  
  // Print detailed messages to standard error.
  // 0: no debugging
  // 1: print failed comparisons only
  // 2: print all comparisons
  //
  final static int DEBUG = 0;
  final static boolean TRACE_FAILED_COMPARISON = DEBUG > 0;

  final static String xsdFile = "Bad.xsd";

  final static String[] xsdFiles = { "Bad.xsd", "BadMinExclusiveFacet.xsd", "BadAttributeDeclaration.xsd", "BadMinInclusiveFacet.xsd",
      "BadAttributeGroupDefinition.xsd", "BadMinLengthFacet.xsd", "BadComplexTypeDefinition.xsd", "BadModelGroup.xsd", "BadDataType.xsd",
      "BadModelGroupDefinition.xsd", "BadElementDeclaration.xsd", "BadNotationDeclaration.xsd", "BadEnumerationFacet.xsd",
      "BadPatternFacet.xsd", "BadFractionDigitsFacet.xsd", "BadSchema.xsd", "BadIdentityConstraintDefinition.xsd",
      "BadSimpleTypeDefinition.xsd", "BadLengthFacet.xsd", "BadTotalDigitsFacet.xsd", "BadMaxExclusiveFacet.xsd", "BadWhiteSpaceFacet.xsd",
      "BadMaxInclusiveFacet.xsd", "BadWildcard.xsd", "BadMaxLengthFacet.xsd", };

  final static String[] expectedMsg =
    {
      "XSD: The element '#junk' is not permitted as constrained by 'XML Schema '; expecting schema",
      "XSD: The minExclusive facet is not permitted in a type based on 'http://www.w3.org/2001/XMLSchema#string'",
      "XSD: The minExclusive facet cannot be repeated",
      "XSD: The minExclusive facet cannot appear in the same type as a minInclusive facet",
      "XSD: The attribute 'bad' is not permitted",
      "XSD: The attribute 'value' is required",
      "XSD: The value 'a' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#decimal'",
      "XSD: The value 'bad' of attribute 'fixed' must be of type 'http://www.w3.org/2001/XMLSchema#boolean'",
      "XSD: The value '1' of attribute 'value' must be greater than '2' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetRestriction_._base'",
      "XSD: The value '1' is not greater than or equal to the value '2' of the minExclusive facet of 'http://www.example.com/Bad#badMinExclusiveFacetRestriction_._base'",
      "XSD: The value '2' of attribute 'value' must be less than '2' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetRestrictionWithBaseMaxExclusive_._base'",
      "XSD: The value '2' is not less than the value '2' of the maxExclusive facet of 'http://www.example.com/Bad#badMinExclusiveFacetRestrictionWithBaseMaxExclusive_._base'",
      "XSD: The value '2' is not less than the value '2' of the maxExclusive facet",
      "XSD: The value '2.001' is not less than or equal to the value '2' of the maxExclusive facet",
      "XSD: The value '3' is not equal to the fixed value '2' of the minExclusive facet of 'http://www.example.com/Bad#badMinExclusiveFacetRestrictionFixed_._base'",
      "XSD: The value '3' of attribute 'value' must be greater than '3' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToDecimal_._base'",
      "XSD: The value '3.0' of attribute 'value' must be less than '3.0' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToDouble_._base'",
      "XSD: The value '3.0' of attribute 'value' must be less than '3.0' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToFloat_._base'",
      "XSD: The value 'P1D' of attribute 'value' must be less than 'P1D' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToDuration_._base'",
      "XSD: The value '1999-10-26' of attribute 'value' must be less than '1999-10-26' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToDate_._base'",
      "XSD: The value '13:20:00-05:00' of attribute 'value' must be less than '13:20:00-05:00' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToTime_._base'",
      "XSD: The value '1999-05-31T13:20:00-05:00' of attribute 'value' must be less than '1999-05-31T13:20:00-05:00' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToDateTime_._base'",
      "XSD: The value '1999' of attribute 'value' must be less than '1999' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToGYear_._base'",
      "XSD: The value '--12' of attribute 'value' must be less than '--12' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToGMonth_._base'", // 23
      "XSD: The value '1999-12' of attribute 'value' must be less than '1999-12' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToGYearMonth_._base'",
      "XSD: The value '---31' of attribute 'value' must be less than '---31' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToGDay_._base'",
      "XSD: The value '--10-31' of attribute 'value' must be less than '--10-31' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToGMonthDay_._base'",
      "XSD: The value '3.0' of attribute 'value' must be greater than '3.0' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToDouble_._base'",
      "XSD: The value '3.0' of attribute 'value' must be greater than '3.0' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToFloat_._base'",
      "XSD: The value 'P1D' of attribute 'value' must be greater than 'P1D' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToDuration_._base'",
      "XSD: The value '1999-10-26' of attribute 'value' must be greater than '1999-10-26' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToDate_._base'",
      "XSD: The value '13:20:00-05:00' of attribute 'value' must be greater than '13:20:00-05:00' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToTime_._base'",
      "XSD: The value '1999-05-31T13:20:00-05:00' of attribute 'value' must be greater than '1999-05-31T13:20:00-05:00' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToDateTime_._base'",
      "XSD: The value '1999' of attribute 'value' must be greater than '1999' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToGYear_._base'",
      "XSD: The value '--12' of attribute 'value' must be greater than '--12' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToGMonth_._base'", // 34
      "XSD: The value '1999-12' of attribute 'value' must be greater than '1999-12' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToGYearMonth_._base'",
      "XSD: The value '---31' of attribute 'value' must be greater than '---31' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToGDay_._base'",
      "XSD: The value '--10-31' of attribute 'value' must be greater than '--10-31' as constrained by 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToGMonthDay_._base'",
      "XSD: The type may not have duplicate name and target namespace 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToDate'",
      "XSD: The type may not have duplicate name and target namespace 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToDateTime'",
      "XSD: The type may not have duplicate name and target namespace 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToDouble'",
      "XSD: The type may not have duplicate name and target namespace 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToDuration'",
      "XSD: The type may not have duplicate name and target namespace 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToFloat'",
      "XSD: The type may not have duplicate name and target namespace 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToGDay'",
      "XSD: The type may not have duplicate name and target namespace 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToGMonth'",
      "XSD: The type may not have duplicate name and target namespace 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToGMonthDay'",
      "XSD: The type may not have duplicate name and target namespace 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToGYear'",
      "XSD: The type may not have duplicate name and target namespace 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToGYearMonth'",
      "XSD: The type may not have duplicate name and target namespace 'http://www.example.com/Bad#badMinExclusiveFacetAppliedToTime'",
      "XSD: The type may not have duplicate name and target namespace 'http://www.example.com/Bad#badMinExclusiveFacetRepeat'",
      "XSD: The attribute 'name' is required",
      "XSD: The value 'bad:name' of attribute 'name' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The attribute 'use' is not permitted",
      "XSD: The attributes 'fixed' and 'default' may not both be present",
      "XSD: Type reference 'http://www.w3.org/2001/XMLSchema#badType' is unresolved",
      "XSD: The value 'id:id' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: An attribute may not have both a type and an anonymous type",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#xsimpleType' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#topLevelAttribute'; expecting simpleType | annotation | nothing",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#topLevelAttribute'; expecting simpleType | nothing",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#topLevelAttribute'; expecting nothing",
      "XSD: An attribute with an 'ID' type may not have a value constraint",
      "XSD: The 'use' must be 'optional' when a 'default' is present",
      "XSD: The value 'x' of attribute 'default' must conform to pattern '[\\-+]?[0-9]+' as constrained by 'http://www.w3.org/2001/XMLSchema#integer'",
      "XSD: The value 'x' of attribute 'default' must be of type 'http://www.w3.org/2001/XMLSchema#int'",
      "XSD: The attribute 'xuse' is not permitted",
      "XSD: The value 'xoptional' of attribute 'use' must be one of prohibited, optional, required as constrained by 'http://www.w3.org/2001/XMLSchema#use_._type'",
      "XSD: The value 'xqualified' of attribute 'form' must be one of qualified, unqualified as constrained by 'http://www.w3.org/2001/XMLSchema#formChoice'",
      "XSD: Attribute reference 'http://www.example.com/Bad#badAttributeReference' is unresolved",
      "XSD: An attribute reference may not have a name",
      "XSD: The 'fixed' value '2' must be equal to the 'fixed' value '1' of the referenced attribute",
      "XSD: An attribute reference may not have a form",
      "XSD: An attribute reference may not have a type",
      "XSD: An attribute reference may not have a type",
      "XSD: The attribute name 'http://www.example.com/Bad#okayFixedAttribute' collides with that of another",
      "XSD: The attribute name 'http://www.example.com/Bad#okayFixedAttribute' collides with that of another",
      "XSD: The attribute name 'http://www.example.com/Bad#okayFixedAttribute' collides with that of another",
      "XSD: The attribute name 'http://www.example.com/Bad#okayFixedAttribute' collides with that of another",
      "XSD: The attribute may not have duplicate name and target namespace 'http://www.example.com/Bad#badAnnotation'",
      "XSD: The minInclusive facet is not permitted in a type based on 'http://www.w3.org/2001/XMLSchema#string'",
      "XSD: The minInclusive facet cannot be repeated",
      "XSD: The attribute 'bad' is not permitted",
      "XSD: The attribute 'value' is required",
      "XSD: The value 'a' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#decimal'",
      "XSD: The value 'bad' of attribute 'fixed' must be of type 'http://www.w3.org/2001/XMLSchema#boolean'",
      "XSD: The value '1' of attribute 'value' must be greater than or equal to '2' as constrained by 'http://www.example.com/Bad#badMinInclusiveFacetRestriction_._base'",
      "XSD: The value '3' is not equal to the fixed value '2' of the minInclusive facet of 'http://www.example.com/Bad#badMinInclusiveFacetRestrictionFixed_._base'",
      "XSD: The value '2' of attribute 'value' must be greater than or equal to '3' as constrained by 'http://www.example.com/Bad#badMinInclusiveFacetAppliedToDecimal_._base'",
      "XSD: The value '2.9' of attribute 'value' must be greater than or equal to '3.0' as constrained by 'http://www.example.com/Bad#badMinInclusiveFacetAppliedToDouble_._base'",
      "XSD: The value '2.9' of attribute 'value' must be greater than or equal to '3.0' as constrained by 'http://www.example.com/Bad#badMinInclusiveFacetAppliedToFloat_._base'",
      "XSD: The value 'P1D' of attribute 'value' must be greater than or equal to 'P2D' as constrained by 'http://www.example.com/Bad#badMinInclusiveFacetAppliedToDuration_._base'",
      "XSD: The value '1999-10-25' of attribute 'value' must be greater than or equal to '1999-10-26' as constrained by 'http://www.example.com/Bad#badMinInclusiveFacetAppliedToDate_._base'",
      "XSD: The value '13:20:00-04:59' of attribute 'value' must be greater than or equal to '13:20:00-05:00' as constrained by 'http://www.example.com/Bad#badMinInclusiveFacetAppliedToTime_._base'",
      "XSD: The value '1999-05-31T13:20:00-04:59' of attribute 'value' must be greater than or equal to '1999-05-31T13:20:00-05:00' as constrained by 'http://www.example.com/Bad#badMinInclusiveFacetAppliedToDateTime_._base'",
      "XSD: The value '1998' of attribute 'value' must be greater than or equal to '1999' as constrained by 'http://www.example.com/Bad#badMinInclusiveFacetAppliedToGYear_._base'",
      "XSD: The value '--11' of attribute 'value' must be greater than or equal to '--12' as constrained by 'http://www.example.com/Bad#badMinInclusiveFacetAppliedToGMonth_._base'", // 94
      "XSD: The value '1999-11' of attribute 'value' must be greater than or equal to '1999-12' as constrained by 'http://www.example.com/Bad#badMinInclusiveFacetAppliedToGYearMonth_._base'",
      "XSD: The value '---30' of attribute 'value' must be greater than or equal to '---31' as constrained by 'http://www.example.com/Bad#badMinInclusiveFacetAppliedToGDay_._base'",
      "XSD: The value '--10-30' of attribute 'value' must be greater than or equal to '--10-31' as constrained by 'http://www.example.com/Bad#badMinInclusiveFacetAppliedToGMonthDay_._base'",
      "XSD: The location 'Other.xsd' has not been resolved",
      "XSD: The attribute 'name' is required",
      "XSD: The attribute 'ref' is not permitted",
      "XSD: The value 'bad:name' of attribute 'name' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The attribute 'use' is not permitted",
      "XSD: The value 'id:id' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#xattribute' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#namedAttributeGroup'; expecting anyAttribute | attribute | attributeGroup | annotation | nothing",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#namedAttributeGroup'; expecting anyAttribute | attribute | attributeGroup | nothing",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#namedAttributeGroup'; expecting anyAttribute | attribute | attributeGroup | nothing",
      "XSD: Attribute group reference 'http://www.example.com/Other#AG' is unresolved",
      "XSD: The attribute group may not be circularly defined",
      "XSD: Attribute group reference 'http://www.example.com/Bad#badRef' is unresolved",
      "XSD: The attribute 'name' is not permitted",
      "XSD: The attribute name '#a' collides with that of another",
      "XSD: The attribute '#c' may not be an ID because '#b' is the ID",
      "XSD: The attribute group may not have duplicate name and target namespace 'http://www.example.com/Bad#badAnnotation'",
      "XSD: The minLength facet is not permitted in a type based on 'http://www.w3.org/2001/XMLSchema#decimal'",
      "XSD: The minLength facet cannot be repeated",
      "XSD: The attribute 'bad' is not permitted",
      "XSD: The attribute 'value' is required",
      "XSD: The value '-1' of attribute 'value' must be greater than or equal to '0' as constrained by 'http://www.w3.org/2001/XMLSchema#nonNegativeInteger'",
      "XSD: The value 'bad' of attribute 'fixed' must be of type 'http://www.w3.org/2001/XMLSchema#boolean'",
      "XSD: The value '3' is not less than or equal to the value '2' of the maxlength facet",
      "XSD: The value '1' is not greater than or equal to the value '2' of the minLength facet of 'http://www.example.com/Bad#badMinLengthFacetRestriction_._base'",
      "XSD: The value '1' is not equal to the fixed value '2' of the minLength facet of 'http://www.example.com/Bad#badMinLengthFacetRestrictionFixed_._base'",
      "XSD: The value 'ab' of attribute 'value' must have length at least 3 as constrained by 'http://www.example.com/Bad#badMinLengthFacetAppliedToString_._base'",
      "XSD: The value 'a b' of attribute 'value' must have length at least 3 as constrained by 'http://www.example.com/Bad#badMinLengthFacetAppliedToList_._base'",
      "XSD: The value 'Aa11Bb22' of attribute 'value' must have length at least 9 as constrained by 'http://www.example.com/Bad#badMinLengthFacetAppliedToBase64_._base'",
      "XSD: The value 'a1b2' of attribute 'value' must have length at least 3 as constrained by 'http://www.example.com/Bad#badMinLengthFacetAppliedToHexBinary_._base'",
      "XSD: The location 'Other.xsd' has not been resolved",
      "XSD: The attribute 'name' is required",
      "XSD: The value 'bad:name' of attribute 'name' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The value 'fasle' of attribute 'abstract' must be of type 'http://www.w3.org/2001/XMLSchema#boolean'",
      "XSD: The value 'resiction' of attribute 'block' must be one of extension, restriction as constrained by 'http://www.w3.org/2001/XMLSchema#reducedDerivationControl'",
      "XSD: The value 'xtension' of attribute 'final' must be one of extension, restriction as constrained by 'http://www.w3.org/2001/XMLSchema#reducedDerivationControl'",
      "XSD: The value 'id:id' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The value 'fasle' of attribute 'mixed' must be of type 'http://www.w3.org/2001/XMLSchema#boolean'",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#bad' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#topLevelComplexType'; expecting group | attributeGroup | simpleContent | all | sequence | annotation | choice | attribute | complexContent | anyAttribute | nothing",
      "XSD: The content is incomplete as constrained by 'http://www.w3.org/2001/XMLSchema#complexContent_._type'; expecting restriction | extension | annotation",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#bad' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#complexContent_._type'; expecting restriction | extension | annotation",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#extensionType'; expecting choice | anyAttribute | sequence | attribute | group | attributeGroup | all | nothing",
      "XSD: Type reference '#badTypeReference' is unresolved",
      "XSD: The base type 'http://www.w3.org/2001/XMLSchema#string' must be complex to support complex content",
      "XSD: The base type must be the content type",
      "XSD: The base type 'http://www.w3.org/2001/XMLSchema#string' may not be simple to support a restriction",
      "XSD: The type 'http://www.w3.org/2001/XMLSchema#anySimpleType' is not a valid base type outside the schema for schemas",
      "XSD: The base content type must be mixed and emptiable to support simple content",
      "XSD: Attribute group reference 'http://www.example.com/Other#AG' is unresolved",
      "XSD: The attribute name '#a' collides with that of another ",
      "XSD: The attribute '#y' may not be an ID because '#x' is the ID ",
      "XSD: The type may not be circularly defined",
      "XSD: The base type 'http://www.example.com/Bad#okayFinalTrivialComplexType' is final for extension",
      "XSD: The attribute '#a' must have type 'http://www.w3.org/2001/XMLSchema#string' as in the base",
      "XSD: The basetype has 'mixed' content so this extended type must also",
      "XSD: The base type 'http://www.example.com/Bad#okayComplexTypeSimple' is final for restriction",
      "XSD: Attribute reference 'http://www.example.com/Other#A' is unresolved",
      "XSD: The type of attribute '#a' must derive from 'http://www.w3.org/2001/XMLSchema#string'",
      "XSD: The attribute '#b' must be 'required' as in the base ",
      "XSD: The attribute target namespace of '#B' must be allowed by the base type wildcard",
      "XSD: The attribute wildcard must be a subset of that of the base type",
      "XSD: The restricted content type many not be empty because the base isn't empty or emptiable",
      "XSD: The simple content type 'http://www.example.com/Bad#badSimpleBaseForRestrictionSimpleContent_._base' must derive from 'http://www.w3.org/2001/XMLSchema#int'",
      "XSD: The base content type must be mixed and emptiable to support simple content",
      "XSD: The restricted content type many not be empty because the base isn't empty or emptiable",
      "XSD: A type with complex content may not restrict a base type with simple or empty content",
      "XSD: A type with mixed content may only restrict a base type with mixed content ",
      "XSD: The restricted content type must accept a subset of the content accepted by the base type",
      "XSD: The restricted content type must accept a subset of the content accepted by the base type",
      "XSD: The element '#e1' must be 'nillable' as in the base",
      "XSD: The restricted content type must accept a subset of the content accepted by the base type",
      "XSD: The 'fixed' value 'xy' of element '#e' must be the same as in the base",
      "XSD: The restricted content type must accept a subset of the content accepted by the base type",
      "XSD: The type of element '#e' must derive from 'http://www.w3.org/2001/XMLSchema#token'",
      "XSD: The restricted content type must accept a subset of the content accepted by the base type",
      "XSD: The 'disallowed substitutions' of '#e' must be a superset of those in the base",
      "XSD: The type of element '#e' must derive from 'http://www.example.com/Bad#e_._type'",
      "XSD: The restricted content type must accept a subset of the content accepted by the base type",
      "XSD: The type may not have duplicate name and target namespace 'http://www.example.com/Bad#badComplexBaseForRestrictionSimpleContent'",
      "XSD: The type may not have duplicate name and target namespace 'http://www.example.com/Bad#badComplexExtension'",
      "XSD: The type may not have duplicate name and target namespace 'http://www.example.com/Bad#badSimpleBaseForRestrictionSimpleContent'",
      "XSD: The location 'Other.xsd' has not been resolved",
      "XSD: The attribute 'maxOccurs' is not permitted",
      "XSD: The attribute 'minOccurs' is not permitted",
      "XSD: The value 'id:id' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#sequence' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#all'; expecting annotation | element | nothing",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#explicitGroup'; expecting choice | sequence | element | any | group | nothing",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#all' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#explicitGroup'; expecting choice | sequence | element | any | group | annotation | nothing",
      "XSD: An <all> content model may not be nested within another content model",
      "XSD: The value 'unbinded' of attribute 'maxOccurs' must be of one of the members types of 'http://www.w3.org/2001/XMLSchema#allNNI'",
      "XSD: The value '-1' of attribute 'minOccurs' must be greater than or equal to '0' as constrained by 'http://www.w3.org/2001/XMLSchema#nonNegativeInteger'",
      "XSD: The value '2' of attribute 'maxOccurs' must be one of 1 as constrained by 'http://www.w3.org/2001/XMLSchema#maxOccurs_._type'",
      "XSD: The value '2' of attribute 'minOccurs' must be one of 0, 1 as constrained by 'http://www.w3.org/2001/XMLSchema#minOccurs_._type'",
      "XSD: The maxOccurs of a particle in an 'all' group must be 0 or 1",
      "XSD: The element '#e1' may not overlap with another element ",
      "XSD: Element reference 'http://www.example.com/Other#E' is unresolved",
      "XSD: A wildcard that allows 'http://www.example.com/Other' may not overlap with a wildcard allowing the same namespace",
      "XSD: The element '#e1' may not have both type 'http://www.w3.org/2001/XMLSchema#string' and type 'http://www.w3.org/2001/XMLSchema#int'",
      "XSD: The value of 'minOccurs' may not be greater than the value of 'maxOccurs'",
      "XSD: An <all> content model may not be nested within another content model",
      "XSD: An <all> content model may not be nested within another content model",
      "XSD: The value 'bad' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#boolean'",
      "XSD: The value 'Aa1' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#base64Binary'",
      "XSD: The value 'bad' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#decimal'",
      "XSD: The value 'bad' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#double'",
      "XSD: The value 'bad' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#float'",
      "XSD: The value 'ha' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#hexBinary'",
      "XSD: The value 'P-1347M' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#duration'",
      "XSD: The value '1999-10-32' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#date'",
      "XSD: The value '1999' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#date'",
      "XSD: The value '13:20:00-25:00' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#time'",
      "XSD: The value '1999' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#time'",
      "XSD: The value '1999-05-31T13:20:00-25:00' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#dateTime'",
      "XSD: The value '1999' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#dateTime'",
      "XSD: The value '199x' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#gYear'",
      "XSD: The value '1999-05-31T13:20:00-05:00' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#gYear'",
      "XSD: The value '--13' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#gMonth'",
      "XSD: The value '1999-05-31T13:20:00-05:00' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#gMonth'",
      "XSD: The value '1999-13' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#gYearMonth'", // 215
      "XSD: The value '1999-05-31T13:20:00-05:00' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#gYearMonth'",
      "XSD: The value '---32' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#gDay'",
      "XSD: The value '1999-05-31T13:20:00-05:00' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#gDay'",
      "XSD: The value '--10-32' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#gMonthDay'",
      "XSD: The value '1999-05-31T13:20:00-05:00' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#gMonthDay'",
      "XSD: The value ':##:' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#anyURI'",
      "XSD: The value 'xsd::string' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#QName'",
      "XSD: The value 'xsd2:string' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#QName'",
      "XSD: The value 'Notation:Bad:' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#NOTATION'",
      "XSD: The value 'Notation:Bad' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#NOTATION'",
      "XSD: The location 'Other.xsd' has not been resolved because the import is unused",
      "XSD: The attribute 'name' is required",
      "XSD: The value 'bad:name' of attribute 'name' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The attribute 'ref' is not permitted",
      "XSD: The value 'id:id' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The content is incomplete as constrained by 'http://www.w3.org/2001/XMLSchema#namedGroup'; expecting sequence | annotation | all | choice",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#bad' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#namedGroup'; expecting sequence | annotation | all | choice",
      "XSD: The group may not be circularly defined",
      "XSD: The attribute 'name' is required",
      "XSD: The value 'bad:name' of attribute 'name' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The value 'fasle' of attribute 'abstract' must be of type 'http://www.w3.org/2001/XMLSchema#boolean'",
      "XSD: The value 'fasle' of attribute 'nillable' must be of type 'http://www.w3.org/2001/XMLSchema#boolean'",
      "XSD: The value 'fasle' of attribute 'final' must be one of extension, restriction as constrained by 'http://www.w3.org/2001/XMLSchema#reducedDerivationControl'",
      "XSD: The value 'something' of attribute 'block' must be one of extension, restriction, substitution as constrained by 'http://www.w3.org/2001/XMLSchema#blockSet_._member_._1_._item'",
      "XSD: The attribute 'form' is not permitted",
      "XSD: The attributes 'fixed' and 'default' may not both be present",
      "XSD: Type reference 'http://www.w3.org/2001/XMLSchema#badType' is unresolved",
      "XSD: The value 'id:id' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: An element may not have both a type and an anonymous type",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#xsimpleType' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#topLevelElement'; expecting unique | key | annotation | complexType | simpleType | keyref | nothing",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#topLevelElement'; expecting unique | key | complexType | simpleType | keyref | nothing",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#topLevelElement'; expecting unique | key | keyref | nothing",
      "XSD: Element reference 'http://www.example.com/Bad#badElementReference' is unresolved",
      "XSD: An attribute with an 'ID' type may not have a value constraint",
      "XSD: The attribute 'use' is not permitted",
      "XSD: The value 'x' of attribute 'default' must conform to pattern '[\\-+]?[0-9]+' as constrained by 'http://www.w3.org/2001/XMLSchema#integer'",
      "XSD: The value 'x' of attribute 'default' must be of type 'http://www.w3.org/2001/XMLSchema#int'",
      "XSD: The base type 'http://www.w3.org/2001/XMLSchema#int' may not be simple to support a restriction",
      "XSD: The value '2' of attribute 'default' must conform to pattern '([\\-+]?[0-9]+) & (1*)' as constrained by 'http://www.example.com/Bad#badConstraintForComplexSimpleType_._type_._base'",
      "XSD: The attribute 'xuse' is not permitted",
      "XSD: The attribute 'use' is not permitted",
      "XSD: The value 'xqualified' of attribute 'form' must be one of qualified, unqualified as constrained by 'http://www.w3.org/2001/XMLSchema#formChoice'",
      "XSD: The value 'unbinded' of attribute 'maxOccurs' must be of one of the members types of 'http://www.w3.org/2001/XMLSchema#allNNI'",
      "XSD: The value '-1' of attribute 'minOccurs' must be greater than or equal to '0' as constrained by 'http://www.w3.org/2001/XMLSchema#nonNegativeInteger'",
      "XSD: Element reference 'http://www.example.com/Bad#badElementReference' is unresolved",
      "XSD: An element reference may not have a name",
      "XSD: An element reference may only have an id, minOccurs, or maxOccurs",
      "XSD: An element reference may only have an id, minOccurs, or maxOccurs",
      "XSD: An element reference may only have an id, minOccurs, or maxOccurs",
      "XSD: An element reference may only have an id, minOccurs, or maxOccurs",
      "XSD: An element reference may only have an id, minOccurs, or maxOccurs",
      "XSD: An element reference may only have an id, minOccurs, or maxOccurs",
      "XSD: An element reference may only contain an annotation",
      "XSD: An element with a value constraint must have simple content, or mixed, emptiable content",
      "XSD: The base type 'http://www.example.com/Bad#badSimpleRestriction_._type' has a derivation method excluded by the substitution group",
      "XSD: The base type 'http://www.w3.org/2001/XMLSchema#int' may not be simple to support a restriction",
      "XSD: The base type 'http://www.example.com/Bad#badComplexRestriction_._type' has a derivation method excluded by the substitution group",
      "XSD: The element's type does not derive from 'http://www.example.com/Bad#okayUnion_._type' as required by the substitution group",
      "XSD: The base type 'http://www.w3.org/2001/XMLSchema#string' may not be simple to support a restriction",
      "XSD: An element with a value constraint must have simple content, or mixed, emptiable content",
      "XSD: The element may not have duplicate name and target namespace 'http://www.example.com/Bad#badAnnotation'",
      "XSD: The attribute 'name' is required",
      "XSD: The attribute 'public' is required",
      "XSD: The attribute 'public' is required",
      "XSD: The value 'bad:name' of attribute 'name' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The attribute 'x' is not permitted",
      "XSD: The value 'id:id' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#bad' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#annotated'; expecting annotation | nothing",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#annotated'; expecting nothing",
      "XSD: The value '##' of attribute 'system' must be of type 'http://www.w3.org/2001/XMLSchema#anyURI'",
      "XSD: The notation may not have duplicate name and target namespace 'http://www.example.com/Bad#badAnnotation'",
      "XSD: The enumeration facet is not permitted in a type based on 'http://www.example.com/Bad#badFacetForType'",
      "XSD: The type 'http://www.w3.org/2001/XMLSchema#anySimpleType' is not a valid base type outside the schema for schemas",
      "XSD: The attribute 'fixed' is not permitted",
      "XSD: The value 'null' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#string'",
      "XSD: The value 'a' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#decimal'",
      "XSD: The value 'aa' of attribute 'value' must be one of a as constrained by 'http://www.example.com/Bad#badEnumerationFacetApplication_._base'",
      "XSD: The pattern facet is not permitted in a type based on 'http://www.example.com/Bad#badFacetForType'",
      "XSD: The type 'http://www.w3.org/2001/XMLSchema#anySimpleType' is not a valid base type outside the schema for schemas",
      "XSD: The attribute 'fixed' is not permitted",
      "XSD: The attribute 'value' is required",
      "XSD: The regular expression 'a\\' failed to validate at location 1: A character is required after \\.",
      "XSD: The value 'abcd' of attribute 'value' must conform to pattern 'a' as constrained by 'http://www.example.com/Bad#badPatternFacetApplication_._base'",
      "XSD: The value 'bcd' of attribute 'value' must conform to pattern 'a' as constrained by 'http://www.example.com/Bad#badPatternFacetApplication_._base'",
      "XSD: The fractionDigits facet is not permitted in a type based on 'http://www.w3.org/2001/XMLSchema#string'",
      "XSD: The fractionDigits facet cannot be repeated",
      "XSD: The attribute 'bad' is not permitted",
      "XSD: The attribute 'value' is required",
      "XSD: The value '-1' of attribute 'value' must be greater than or equal to '0' as constrained by 'http://www.w3.org/2001/XMLSchema#nonNegativeInteger'",
      "XSD: The value 'bad' of attribute 'fixed' must be of type 'http://www.w3.org/2001/XMLSchema#boolean'",
      "XSD: The value '2' is not less than or equal to the value '1' of the totalDigits facet",
      "XSD: The value '2' is not less than or equal to the value '1' of the fractionDigits facet of 'http://www.example.com/Bad#badFractionDigitsFacetRestriction_._base'",
      "XSD: The value '1' is not equal to the fixed value '2' of the fractionDigits facet of 'http://www.example.com/Bad#badFractionDigitsFacetRestrictionFixed_._base'",
      "XSD: The value '0.1234' of attribute 'value' must have at most 3 fraction digits as constrained by 'http://www.example.com/Bad#badFractionDigitsFacetAppliedToDecimal_._base'",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#bad' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#annotation_._type'; expecting appinfo | documentation | nothing",
      "XSD: The value 'http://www.example.com/Bad##' of attribute 'namespace' must be of type 'http://www.w3.org/2001/XMLSchema#anyURI'",
      "XSD: The namespace 'http://www.example.com/Bad##' conflicts with that of the importing schema",
      "XSD: The location '' has not been resolved because the import is unused",
      "XSD: The location 'Other.xsd' has not been resolved",
      "XSD: The location '' has not been resolved because the import is unused",
      "XSD: There is no xmlns declaration for namespace 'http://www.example.com/NotUsed'",
      "XSD: The location '' has not been resolved because the import is unused",
      "XSD: The attribute 'schemaLocation' is required",
      "XSD: The location '' has not been resolved",
      "XSD: The value 'id:include' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#annotated'; expecting nothing",
      "XSD: The location 'Other.xsd' has not been resolved",
      "XSD: The location 'Nothing.xsd' has not been resolved",
      "XSD: The location 'Bad.xsd' may not resolve to something other than a schema",
      "XSD: The attribute 'schemaLocation' is required",
      "XSD: The location '' has not been resolved",
      "XSD: The value 'id:redefine' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#bad' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#redefine_._type'; expecting complexType | group | attributeGroup | simpleType | annotation | nothing",
      "XSD: The location 'Other.xsd' has not been resolved",
      "XSD: The location 'Nothing.xsd' has not been resolved",
      "XSD: Type reference 'http://www.example.com/Bad###T' is unresolved",
      "XSD: The location 'Nothing.xsd' must resolve for the redefinitions to be meaningful",
      "XSD: The pattern facet is not permitted in a type based on 'http://www.example.com/Bad###S'",
      "XSD: Type reference 'http://www.example.com/Bad###S' is unresolved",
      "XSD: Type reference 'http://www.example.com/Bad###CT' is unresolved",
      "XSD: Model group reference 'http://www.example.com/Bad###G' is unresolved",
      "XSD: Model group reference 'http://www.example.com/Bad###G' is unresolved",
      "XSD: Attribute group reference 'http://www.example.com/Bad###AG' is unresolved",
      "XSD: Attribute group reference 'http://www.example.com/Bad###AG' is unresolved",
      "XSD: Attribute group reference 'http://www.example.com/Bad###AGG' is unresolved",
      "XSD: Attribute reference 'http://www.example.com/Bad###A' is unresolved",
      "XSD: The pattern facet is not permitted in a type based on 'http://www.example.com/Bad###ST'",
      "XSD: Type reference 'http://www.example.com/Bad###ST' is unresolved",
      "XSD: The location 'Okay.xsd' must resolve for the redefinitions to be meaningful",
      "XSD: The location 'Other.xsd' has not been resolved because the import is unused",
      "XSD: Type reference 'http://www.example.com/NotOther#Type' is unresolved",
      "XSD: The attribute 'bad' is not permitted",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#import' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#schema_._type'; expecting attributeGroup | notation | attribute | annotation | complexType | simpleType | element | group | nothing",
      "XSD: The value 'qalified' of attribute 'attributeFormDefault' must be one of qualified, unqualified as constrained by 'http://www.w3.org/2001/XMLSchema#formChoice'",
      "XSD: The value 'restiction' of attribute 'blockDefault' must be one of extension, restriction, substitution as constrained by 'http://www.w3.org/2001/XMLSchema#blockSet_._member_._1_._item'",
      "XSD: The value 'qalified' of attribute 'elementFormDefault' must be one of qualified, unqualified as constrained by 'http://www.w3.org/2001/XMLSchema#formChoice'",
      "XSD: The value 'exention' of attribute 'finalDefault' must be one of extension, restriction, list, union as constrained by 'http://www.w3.org/2001/XMLSchema#typeDerivationControl'",
      "XSD: The value 'id:schema' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The value 'http://www.example.com/Bad##' of attribute 'targetNamespace' must be of type 'http://www.w3.org/2001/XMLSchema#anyURI'",
      "XSD: The attribute may not have duplicate name and target namespace 'http://www.example.com/Bad###a'",
      "XSD: The attribute group may not have duplicate name and target namespace 'http://www.example.com/Bad###ag'",
      "XSD: The model group may not have duplicate name and target namespace 'http://www.example.com/Bad###g'",
      "XSD: The element may not have duplicate name and target namespace 'http://www.example.com/Bad###e'",
      "XSD: The element may not have duplicate name and target namespace 'http://www.example.com/Bad###e2'",
      "XSD: The type may not have duplicate name and target namespace 'http://www.example.com/Bad###t'",
      "XSD: The identity constraint may not have duplicate name and target namespace 'http://www.example.com/Bad###k'",
      "XSD: The notation may not have duplicate name and target namespace 'http://www.example.com/Bad###n'",
      "XSD: The element '#junk' is not permitted as constrained by 'XML Schema '; expecting schema",
      "XSD: The content is incomplete as constrained by 'http://www.w3.org/2001/XMLSchema#keybase'; expecting annotation | selector",
      "XSD: The attribute 'name' is required",
      "XSD: The attribute 'x' is not permitted",
      "XSD: The content is incomplete as constrained by 'http://www.w3.org/2001/XMLSchema#keybase'; expecting annotation | selector",
      "XSD: The content is incomplete as constrained by 'http://www.w3.org/2001/XMLSchema#keybase'; expecting annotation | selector",
      "XSD: The value 'id:badKeyID' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#keybase'; expecting selector",
      "XSD: The attribute 'xpath' is required",
      "XSD: The content is incomplete as constrained by 'http://www.w3.org/2001/XMLSchema#keybase'; expecting field",
      "XSD: The attribute 'x' is not permitted",
      "XSD: The attribute 'xpath' is required",
      "XSD: The attribute 'x' is not permitted",
      "XSD: The attribute 'xpath' is required",
      "XSD: The value '1' of attribute 'xpath' must conform to pattern '(\\.//)?(((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)(/(((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.))*(\\|(\\.//)?(((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)(/(((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.))*)*' as constrained by 'http://www.w3.org/2001/XMLSchema#xpath_._type'",
      "XSD: The value '2' of attribute 'xpath' must conform to pattern '(\\.//)?((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)/)*((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)|((attribute::|@)((\\i\\c*:)?(\\i\\c*|\\*))))(\\|(\\.//)?((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)/)*((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)|((attribute::|@)((\\i\\c*:)?(\\i\\c*|\\*)))))*' as constrained by 'http://www.w3.org/2001/XMLSchema#xpath_._type'",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#annotated'; expecting nothing",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#annotated'; expecting nothing",
      "XSD: The content is incomplete as constrained by 'http://www.w3.org/2001/XMLSchema#keybase'; expecting annotation | selector",
      "XSD: The attribute 'name' is required",
      "XSD: The attribute 'x' is not permitted",
      "XSD: The content is incomplete as constrained by 'http://www.w3.org/2001/XMLSchema#keybase'; expecting annotation | selector",
      "XSD: The content is incomplete as constrained by 'http://www.w3.org/2001/XMLSchema#keybase'; expecting annotation | selector",
      "XSD: The value 'id:badUniqueID' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#keybase'; expecting selector",
      "XSD: The attribute 'xpath' is required",
      "XSD: The content is incomplete as constrained by 'http://www.w3.org/2001/XMLSchema#keybase'; expecting field",
      "XSD: The attribute 'x' is not permitted",
      "XSD: The attribute 'xpath' is required",
      "XSD: The attribute 'x' is not permitted",
      "XSD: The attribute 'xpath' is required",
      "XSD: The value '1' of attribute 'xpath' must conform to pattern '(\\.//)?(((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)(/(((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.))*(\\|(\\.//)?(((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)(/(((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.))*)*' as constrained by 'http://www.w3.org/2001/XMLSchema#xpath_._type'",
      "XSD: The value '2' of attribute 'xpath' must conform to pattern '(\\.//)?((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)/)*((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)|((attribute::|@)((\\i\\c*:)?(\\i\\c*|\\*))))(\\|(\\.//)?((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)/)*((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)|((attribute::|@)((\\i\\c*:)?(\\i\\c*|\\*)))))*' as constrained by 'http://www.w3.org/2001/XMLSchema#xpath_._type'",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#annotated'; expecting nothing",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#annotated'; expecting nothing",
      "XSD: Key reference '' is unresolved",
      "XSD: The content is incomplete as constrained by 'http://www.w3.org/2001/XMLSchema#keyref_._type'; expecting annotation | selector",
      "XSD: The attribute 'name' is required",
      "XSD: The attribute 'x' is not permitted",
      "XSD: The content is incomplete as constrained by 'http://www.w3.org/2001/XMLSchema#keyref_._type'; expecting annotation | selector",
      "XSD: The content is incomplete as constrained by 'http://www.w3.org/2001/XMLSchema#keyref_._type'; expecting annotation | selector",
      "XSD: The value 'id:badKeyRefID' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#keyref_._type'; expecting selector",
      "XSD: The attribute 'xpath' is required",
      "XSD: The content is incomplete as constrained by 'http://www.w3.org/2001/XMLSchema#keyref_._type'; expecting field",
      "XSD: The attribute 'x' is not permitted",
      "XSD: The attribute 'xpath' is required",
      "XSD: The attribute 'x' is not permitted",
      "XSD: The attribute 'xpath' is required",
      "XSD: The value '1' of attribute 'xpath' must conform to pattern '(\\.//)?(((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)(/(((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.))*(\\|(\\.//)?(((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)(/(((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.))*)*' as constrained by 'http://www.w3.org/2001/XMLSchema#xpath_._type'",
      "XSD: The value '2' of attribute 'xpath' must conform to pattern '(\\.//)?((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)/)*((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)|((attribute::|@)((\\i\\c*:)?(\\i\\c*|\\*))))(\\|(\\.//)?((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)/)*((((child::)?((\\i\\c*:)?(\\i\\c*|\\*)))|\\.)|((attribute::|@)((\\i\\c*:)?(\\i\\c*|\\*)))))*' as constrained by 'http://www.w3.org/2001/XMLSchema#xpath_._type'",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#annotated'; expecting nothing",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#annotated'; expecting nothing",
      "XSD: The keyref must contain the same number of fields as the referenced key",
      "XSD: The identity constraint may not have duplicate name and target namespace 'http://www.example.com/Bad#badKeyRefs_._type'",
      "XSD: The identity constraint may not have duplicate name and target namespace 'http://www.example.com/Bad#badUniques_._type'",
      "XSD: The content is incomplete as constrained by 'http://www.w3.org/2001/XMLSchema#simpleType'; expecting restriction | union | annotation | list",
      "XSD: The attribute 'name' is required",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#bad' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#simpleType'; expecting restriction | union | annotation | list",
      "XSD: The value 'id:id' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The value 'id:list' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The 'itemType' attribute or a contained item type must be present",
      "XSD: Type reference '#undefined' is unresolved",
      "XSD: The value 'xtension' of attribute 'final' must be one of list, union, restriction as constrained by 'http://www.w3.org/2001/XMLSchema#simpleDerivationSet_._member_._1_._item'",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#bad' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#list_._type'; expecting annotation | simpleType | nothing",
      "XSD: The 'itemType' attribute or a contained item type must be present",
      "XSD: Type reference '#undefined' is unresolved",
      "XSD: The 'itemType' attribute conflicts with the contained item type",
      "XSD: The value 'id:union' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The 'memberTypes' attribute must be present or there must be contained member types",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#bad' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#union_._type'; expecting simpleType | annotation | nothing",
      "XSD: The 'memberTypes' attribute must be present or there must be contained member types",
      "XSD: The value 'id:restriction' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The 'base' attribute or a contained base type must be present",
      "XSD: The 'base' attribute or a contained base type must be present",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#bad' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#restriction_._type'; expecting minLength | length | simpleType | pattern | minInclusive | annotation | maxLength | minExclusive | fractionDigits | totalDigits | maxInclusive | enumeration | maxExclusive | whiteSpace | nothing",
      "XSD: The 'base' attribute or a contained base type must be present",
      "XSD: The 'base' attribute conflicts with the contained base type",
      "XSD: The maxExclusive facet cannot be repeated",
      "XSD: The 'itemType' attribute or a contained item type must be present",
      "XSD: Type reference '#undefined' is unresolved",
      "XSD: The item type of the list is not atomic or union",
      "XSD: The union type is circularly defined",
      "XSD: The type is circularly defined",
      "XSD: The type is circularly defined",
      "XSD: The union type is circularly defined",
      "XSD: The type is circularly defined",
      "XSD: The value 'xx:xx' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#pattern' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#simpleExtensionType'; expecting attribute | attributeGroup | anyAttribute | annotation | nothing",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#annotated'; expecting nothing",
      "XSD: The value 'xx:xx' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The type 'http://www.w3.org/2001/XMLSchema#anySimpleType' is not a valid base type outside the schema for schemas",
      "XSD: The type 'http://www.w3.org/2001/XMLSchema#anySimpleType' is not a valid item type ",
      "XSD: The length facet is not permitted in a type based on 'http://www.w3.org/2001/XMLSchema#decimal'",
      "XSD: The length facet cannot be repeated",
      "XSD: The attribute 'bad' is not permitted",
      "XSD: The attribute 'value' is required",
      "XSD: The value '-1' of attribute 'value' must be greater than or equal to '0' as constrained by 'http://www.w3.org/2001/XMLSchema#nonNegativeInteger'",
      "XSD: The value 'bad' of attribute 'fixed' must be of type 'http://www.w3.org/2001/XMLSchema#boolean'",
      "XSD: The length facet is not permitted in the same type as a minLength or maxLength facet",
      "XSD: The value '3' is not equal to the value '2' of the length facet of 'http://www.example.com/Bad#badLengthFacetRestriction_._base'",
      "XSD: The value '3' is not equal to the fixed value '2' of the length facet of 'http://www.example.com/Bad#badLengthFacetRestrictionFixed_._base'",
      "XSD: The value 'abcd' of attribute 'value' must have length 3 as constrained by 'http://www.example.com/Bad#badLengthFacetAppliedToString_._base'",
      "XSD: The value 'a b c d' of attribute 'value' must have length 3 as constrained by 'http://www.example.com/Bad#badLengthFacetAppliedToList_._base'",
      "XSD: The value 'Aa11Bb22Cc33Dd44' of attribute 'value' must have length 9 as constrained by 'http://www.example.com/Bad#badLengthFacetAppliedToBase64_._base'",
      "XSD: The value 'a1b2c3d4' of attribute 'value' must have length 3 as constrained by 'http://www.example.com/Bad#badLengthFacetAppliedToHexBinary_._base'",
      "XSD: The totalDigits facet is not permitted in a type based on 'http://www.w3.org/2001/XMLSchema#string'",
      "XSD: The totalDigits facet cannot be repeated",
      "XSD: The attribute 'bad' is not permitted",
      "XSD: The attribute 'value' is required",
      "XSD: The value '0' of attribute 'value' must be greater than or equal to '1' as constrained by 'http://www.w3.org/2001/XMLSchema#positiveInteger'",
      "XSD: The value 'bad' of attribute 'fixed' must be of type 'http://www.w3.org/2001/XMLSchema#boolean'",
      "XSD: The value '2' is not less than or equal to the value '1' of the totalDigits facet of 'http://www.example.com/Bad#badTotalDigitsFacetRestriction_._base'",
      "XSD: The value '1' is not equal to the fixed value '2' of the totalDigits facet of 'http://www.example.com/Bad#badTotalDigitsFacetRestrictionFixed_._base'",
      "XSD: The value '1234' of attribute 'value' must have at most 3 digits as constrained by 'http://www.example.com/Bad#badTotalDigitsFacetAppliedToDecimal_._base'",
      "XSD: The maxExclusive facet is not permitted in a type based on 'http://www.w3.org/2001/XMLSchema#string'",
      "XSD: The maxExclusive facet cannot be repeated",
      "XSD: The maxExclusive facet cannot appear in the same type as a maxInclusive facet",
      "XSD: The attribute 'bad' is not permitted",
      "XSD: The attribute 'value' is required",
      "XSD: The value 'a' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#decimal'",
      "XSD: The value 'bad' of attribute 'fixed' must be of type 'http://www.w3.org/2001/XMLSchema#boolean'",
      "XSD: The value '3' of attribute 'value' must be less than '2' as constrained by 'http://www.example.com/Bad#badMaxExclusiveFacetRestriction_._base'",
      "XSD: The value '3' is not less than or equal to the value '2' of the maxExclusive facet of 'http://www.example.com/Bad#badMaxExclusiveFacetRestriction_._base'",
      "XSD: The value '2' of attribute 'value' must be greater than '2' as constrained by 'http://www.example.com/Bad#badMaxExclusiveFacetRestrictionWithBaseMinExclusive_._base'",
      "XSD: The value '2' is not greater than the value '2' of the minExclusive facet of 'http://www.example.com/Bad#badMaxExclusiveFacetRestrictionWithBaseMinExclusive_._base'",
      "XSD: The value '2.000001' is not less than the value '2' of the maxExclusive facet",
      "XSD: The value '1' is not equal to the fixed value '2' of the maxExclusive facet of 'http://www.example.com/Bad#badMaxExclusiveFacetRestrictionFixed_._base'",
      "XSD: The value '3' of attribute 'value' must be less than '3' as constrained by 'http://www.example.com/Bad#badMaxExclusiveFacetAppliedToDecimal_._base'",
      "XSD: The value '3.0' of attribute 'value' must be less than '3.0' as constrained by 'http://www.example.com/Bad#badMaxExclusiveFacetAppliedToDouble_._base'",
      "XSD: The value '3.0' of attribute 'value' must be less than '3.0' as constrained by 'http://www.example.com/Bad#badMaxExclusiveFacetAppliedToFloat_._base'",
      "XSD: The value 'P1D' of attribute 'value' must be less than 'P1D' as constrained by 'http://www.example.com/Bad#badMaxExclusiveFacetAppliedToDuration_._base'",
      "XSD: The value '1999-10-26' of attribute 'value' must be less than '1999-10-26' as constrained by 'http://www.example.com/Bad#badMaxExclusiveFacetAppliedToDate_._base'",
      "XSD: The value '13:20:00-05:00' of attribute 'value' must be less than '13:20:00-05:00' as constrained by 'http://www.example.com/Bad#badMaxExclusiveFacetAppliedToTime_._base'",
      "XSD: The value '1999-05-31T13:20:00-05:00' of attribute 'value' must be less than '1999-05-31T13:20:00-05:00' as constrained by 'http://www.example.com/Bad#badMaxExclusiveFacetAppliedToDateTime_._base'",
      "XSD: The value '1999' of attribute 'value' must be less than '1999' as constrained by 'http://www.example.com/Bad#badMaxExclusiveFacetAppliedToGYear_._base'",
      "XSD: The value '--12' of attribute 'value' must be less than '--12' as constrained by 'http://www.example.com/Bad#badMaxExclusiveFacetAppliedToGMonth_._base'", // 497
      "XSD: The value '1999-12' of attribute 'value' must be less than '1999-12' as constrained by 'http://www.example.com/Bad#badMaxExclusiveFacetAppliedToGYearMonth_._base'",
      "XSD: The value '---31' of attribute 'value' must be less than '---31' as constrained by 'http://www.example.com/Bad#badMaxExclusiveFacetAppliedToGDay_._base'",
      "XSD: The value '--10-31' of attribute 'value' must be less than '--10-31' as constrained by 'http://www.example.com/Bad#badMaxExclusiveFacetAppliedToGMonthDay_._base'",
      "XSD: The type may not have duplicate name and target namespace 'http://www.example.com/Bad#badMaxExclusiveFacetRepeat'",
      "XSD: The whiteSpace facet is not permitted in a type based on 'http://www.example.com/Bad#badFacetForType'",
      "XSD: The type 'http://www.w3.org/2001/XMLSchema#anySimpleType' is not a valid base type outside the schema for schemas",
      "XSD: The whiteSpace facet cannot be repeated",
      "XSD: The attribute 'bad' is not permitted",
      "XSD: The attribute 'value' is required",
      "XSD: The value 'bad' of attribute 'value' must be one of preserve, replace, collapse as constrained by 'http://www.w3.org/2001/XMLSchema#value_._type'",
      "XSD: The value 'bad' of attribute 'fixed' must be of type 'http://www.w3.org/2001/XMLSchema#boolean'",
      "XSD: The value 'preserve' is less constraining than the value 'replace' of the whiteSpace facet of 'http://www.example.com/Bad#badWhiteSpaceFacetRestriction_._base'",
      "XSD: The value 'collapse' is not equal to the fixed value 'replace' of the whiteSpace facet of 'http://www.example.com/Bad#badWhiteSpaceFacetRestrictionFixed_._base'",
      "XSD: The value 'a a' of attribute 'value' must conform to pattern 'a      a' as constrained by 'http://www.example.com/Bad#badWhiteSpaceFacetAppliedToWithPatternWithCollapse_._base'",
      "XSD: The maxInclusive facet is not permitted in a type based on 'http://www.w3.org/2001/XMLSchema#string'",
      "XSD: The maxInclusive facet cannot be repeated",
      "XSD: The attribute 'bad' is not permitted",
      "XSD: The attribute 'value' is required",
      "XSD: The value 'a' of attribute 'value' must be of type 'http://www.w3.org/2001/XMLSchema#decimal'",
      "XSD: The value 'bad' of attribute 'fixed' must be of type 'http://www.w3.org/2001/XMLSchema#boolean'",
      "XSD: The value '3' of attribute 'value' must be less than or equal to '2' as constrained by 'http://www.example.com/Bad#badMaxInclusiveFacetRestriction_._base'",
      "XSD: The value '3' of attribute 'value' must be less than or equal to '2' as constrained by 'http://www.example.com/Bad#badMaxInclusiveFacetRestrictionFixed_._base'",
      "XSD: The value '3' is not equal to the fixed value '2' of the maxInclusive facet of 'http://www.example.com/Bad#badMaxInclusiveFacetRestrictionFixed_._base'",
      "XSD: The value '4' of attribute 'value' must be less than or equal to '3' as constrained by 'http://www.example.com/Bad#badMaxInclusiveFacetAppliedToDecimal_._base'",
      "XSD: The value '3.0' of attribute 'value' must be less than or equal to '2.9' as constrained by 'http://www.example.com/Bad#badMaxInclusiveFacetAppliedToDouble_._base'",
      "XSD: The value '3.0' of attribute 'value' must be less than or equal to '2.9' as constrained by 'http://www.example.com/Bad#badMaxInclusiveFacetAppliedToFloat_._base'",
      "XSD: The value 'P2D' of attribute 'value' must be less than or equal to 'P1D' as constrained by 'http://www.example.com/Bad#badMaxInclusiveFacetAppliedToDuration_._base'",
      "XSD: The value '1999-10-26' of attribute 'value' must be less than or equal to '1999-10-25' as constrained by 'http://www.example.com/Bad#badMaxInclusiveFacetAppliedToDate_._base'",
      "XSD: The value '13:20:00-05:01' of attribute 'value' must be less than or equal to '13:20:00-05:00' as constrained by 'http://www.example.com/Bad#badMaxInclusiveFacetAppliedToTime_._base'",
      "XSD: The value '1999-05-31T13:20:00-05:01' of attribute 'value' must be less than or equal to '1999-05-31T13:20:00-05:00' as constrained by 'http://www.example.com/Bad#badMaxInclusiveFacetAppliedToDateTime_._base'",
      "XSD: The value '1999' of attribute 'value' must be less than or equal to '1998' as constrained by 'http://www.example.com/Bad#badMaxInclusiveFacetAppliedToGYear_._base'",
      "XSD: The value '--12' of attribute 'value' must be less than or equal to '--11' as constrained by 'http://www.example.com/Bad#badMaxInclusiveFacetAppliedToGMonth_._base'", // 529
      "XSD: The value '1999-12' of attribute 'value' must be less than or equal to '1999-11' as constrained by 'http://www.example.com/Bad#badMaxInclusiveFacetAppliedToGYearMonth_._base'",
      "XSD: The value '---31' of attribute 'value' must be less than or equal to '---30' as constrained by 'http://www.example.com/Bad#badMaxInclusiveFacetAppliedToGDay_._base'",
      "XSD: The value '--10-31' of attribute 'value' must be less than or equal to '--10-30' as constrained by 'http://www.example.com/Bad#badMaxInclusiveFacetAppliedToGMonthDay_._base'",
      "XSD: The location 'Other.xsd' has not been resolved",
      "XSD: The attribute 'xnamespace' is not permitted",
      "XSD: The value 'id:id' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#wildcard'; expecting nothing",
      "XSD: The value 'lex' of attribute 'processContents' must be one of skip, lax, strict as constrained by 'http://www.w3.org/2001/XMLSchema#processContents_._type'",
      "XSD: The value 'http://www.example.com/##' of attribute 'namespace' must be of one of the members types of 'http://www.w3.org/2001/XMLSchema#namespaceList_._member_._1_._item'",
      "XSD: The attribute 'xnamespace' is not permitted",
      "XSD: The value 'id:id' of attribute 'id' must conform to pattern '(\\i\\c*) & ([\\i-[:]][\\c-[:]]*)' as constrained by 'http://www.w3.org/2001/XMLSchema#NCName'",
      "XSD: The element 'http://www.w3.org/2001/XMLSchema#annotation' is not permitted as constrained by 'http://www.w3.org/2001/XMLSchema#wildcard'; expecting nothing",
      "XSD: The value 'lex' of attribute 'processContents' must be one of skip, lax, strict as constrained by 'http://www.w3.org/2001/XMLSchema#processContents_._type'",
      "XSD: The value 'http://www.example.com/##' of attribute 'namespace' must be of one of the members types of 'http://www.w3.org/2001/XMLSchema#namespaceList_._member_._1_._item'",
      "XSD: The maxLength facet is not permitted in a type based on 'http://www.w3.org/2001/XMLSchema#decimal'",
      "XSD: The maxLength facet cannot be repeated",
      "XSD: The attribute 'bad' is not permitted",
      "XSD: The attribute 'value' is required",
      "XSD: The value '-1' of attribute 'value' must be greater than or equal to '0' as constrained by 'http://www.w3.org/2001/XMLSchema#nonNegativeInteger'",
      "XSD: The value 'bad' of attribute 'fixed' must be of type 'http://www.w3.org/2001/XMLSchema#boolean'",
      "XSD: The value '3' is not less than or equal to the value '2' of the maxLength facet of 'http://www.example.com/Bad#badMaxLengthFacetRestriction_._base'",
      "XSD: The value '1' is not equal to the fixed value '2' of the maxLength facet of 'http://www.example.com/Bad#badMaxLengthFacetRestrictionFixed_._base'",
      "XSD: The value 'abcd' of attribute 'value' must have length at most 3 as constrained by 'http://www.example.com/Bad#badMaxLengthFacetAppliedToString_._base'",
      "XSD: The value 'a b c d' of attribute 'value' must have length at most 3 as constrained by 'http://www.example.com/Bad#badMaxLengthFacetAppliedToList_._base'",
      "XSD: The value 'Aa11Bb22Cc33Dd44' of attribute 'value' must have length at most 9 as constrained by 'http://www.example.com/Bad#badMaxLengthFacetAppliedToBase64_._base'",
      "XSD: The value 'a1b2c3d4' of attribute 'value' must have length at most 3 as constrained by 'http://www.example.com/Bad#badMaxLengthFacetAppliedToHexBinary_._base'"
    };

  public XSDValidateTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("XSDValidateTest");
    ts.addTestSuite(XSDValidateTest.class);
    // add this test to print error messages for 'xsdFile'
    //ts.addTest(new XSDValidateTest("printValidationErrors"));
    return ts;
  }

  /**
   * @see junit.framework.TestCase#setUp()
   */
  @Override
  protected void setUp() throws Exception
  {
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("xml", new XMLResourceFactoryImpl());
  }

  /** Use this method to print all validation error messages for a given 'xsdFile'
   * 
   * @throws Exception
   */
  public void printValidationErrors() throws Exception
  {
    ResourceSet resourceSet = loadXSDResource(xsdFile);
    
    // Iterate over all the resources, i.e., the main resource and those that have been included or imported.
    //
    for (Object resource : resourceSet.getResources())
    {
      if (resource instanceof XSDResourceImpl)
      {
        XSDResourceImpl xsdResource = (XSDResourceImpl)resource;
        XSDSchema xsdSchema = xsdResource.getSchema();
        xsdSchema.validate();

        for (XSDDiagnostic xsdDiagnostic : xsdSchema.getAllDiagnostics())
        {
          System.err.println('"' + xsdDiagnostic.getMessage() + '"' + ',');
        }
      }
    }
  }

  public void testValidateXSD() throws Exception
  {
    // If printing debug output, we don't want to fail until we've finished, so we'll count the problems.
    //
    int failures = 0;
    int msgIndex = 0;
    for (int c = 0; c < xsdFiles.length; c++)
    {
      ResourceSet resourceSet = loadXSDResource(xsdFiles[c]);

      // Iterate over all the resources, i.e., the main resource and those that have been included or imported.
      //
      for (Object resource : resourceSet.getResources())
      {
        if (resource instanceof XSDResourceImpl)
        {
          XSDResourceImpl xsdResource = (XSDResourceImpl)resource;
          if (TRACE_FAILED_COMPARISON)
          {
            System.err.println("--> " + xsdResource.getURI().lastSegment());
          }
          XSDSchema xsdSchema = xsdResource.getSchema();
          xsdSchema.validate();
          EList<XSDDiagnostic> diagnostics = xsdSchema.getAllDiagnostics();
          
          assertFalse(diagnostics.isEmpty());
          
          for (XSDDiagnostic xsdDiagnostic : diagnostics)
          {
            String expected = expectedMsg[msgIndex++];
            String actual = xsdDiagnostic.getMessage();
            String position = xsdDiagnostic.getLine() + ", " + xsdDiagnostic.getColumn();
            int index = actual.indexOf("; expecting");
            int index2 = actual.indexOf("The identity constraint");
            if (index > 0)
            {
              // Fix invalid content messages "; expecting".
              //
              String substring = actual.substring(0, index);
              boolean result = expected.startsWith(substring);
              if (TRACE_FAILED_COMPARISON)
              {
                failures += handleResult(result, expected, actual, "match up to ;", position);
              }
              else
              {
                assertTrue("'"+ expected + "' does not start with '" + substring, result);
              }
            }
            else if (index2 > 0)
            {
              // Fix identity constraints.
              //
              String substring = actual.substring(0, actual.indexOf("http:/"));
              boolean result = expected.startsWith(substring);
              if (TRACE_FAILED_COMPARISON)
              {
                failures += handleResult(result, expected, actual, "match up to http", position);
              }
              else
              {
                assertTrue("'" + expected + "' does not start with '" + substring, result);
              }
            }
            else 
            {
              boolean result = expected.equals(actual);
              if (TRACE_FAILED_COMPARISON)
              {
                failures += handleResult(result, expected, actual, "equal", position);
              }
              else
              {
                assertEquals(expected, actual);
              }
            }
          }
        }
      }
    }

    if (failures > 0)
    {
      fail(failures + " comparison(s) failed. See output for details.");
    }
  }

  protected ResourceSet loadXSDResource(String xsd) throws IOException
  {
    File file = new File(BASE_URI + xsd);
    ResourceSet resourceSet = new ResourceSetImpl();
    URI uri = URI.createFileURI(file.getCanonicalFile().toString());

    // Create a resource set, create a schema resource, and load the main schema file into it.
    //
    resourceSet.getLoadOptions().put(XSDResourceImpl.XSD_TRACK_LOCATION, Boolean.TRUE);
    XSDResourceImpl xsdMainResource = (XSDResourceImpl)resourceSet.createResource(URI.createURI("*.xsd"));
    xsdMainResource.setURI(uri);
    xsdMainResource.load(resourceSet.getLoadOptions());
    return resourceSet;
  }

  protected int handleResult(boolean result, String expected, String actual, String test, String position)
  {
    if (DEBUG == 2 || !result)
    {
      System.err.println("Expected: " + expected);
      System.err.println("Actual:   " + actual);
      System.err.println("Result (" + test +"): " + result);
      System.err.println("Position: " + position);
    }
    return result ? 0 : 1;    
  }
}