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
 * $Id: XMLTypePackageImpl.java,v 1.2 2004/04/10 20:30:57 emerks Exp $
 */
package org.eclipse.emf.ecore.xml.type.impl;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
import org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XMLTypePackageImpl extends EPackageImpl implements XMLTypePackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass anyTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass simpleAnyTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass xmlTypeDocumentRootEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType anySimpleTypeEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType anyURIEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType base64BinaryEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType booleanEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType booleanObjectEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType decimalEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType integerEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType intObjectEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType longEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType longObjectEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType intEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType shortEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType shortObjectEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType byteEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType byteObjectEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType dateEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType dateTimeEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType stringEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType doubleEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType doubleObjectEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType durationEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType entitiesBaseEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType normalizedStringEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType tokenEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType nameEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType ncNameEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType entityEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType entitiesEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType floatEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType floatObjectEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType gDayEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType gMonthEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType gMonthDayEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType gYearEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType gYearMonthEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType hexBinaryEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType idEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType idrefEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType idrefsBaseEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType idrefsEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType languageEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType nonPositiveIntegerEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType negativeIntegerEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType nmtokenEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType nmtokensBaseEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType nmtokensEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType nonNegativeIntegerEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType notationEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType positiveIntegerEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType qNameEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType timeEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType unsignedLongEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType unsignedIntEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType unsignedIntObjectEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType unsignedShortEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType unsignedShortObjectEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType unsignedByteEDataType = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType unsignedByteObjectEDataType = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.eclipse.emf.ecore.xml.type.XMLTypePackage#eNS_URI
   * @see #init()
   * @generated
   */
  private XMLTypePackageImpl()
  {
    super(eNS_URI, XMLTypeFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this
   * model, and for any others upon which it depends.  Simple
   * dependencies are satisfied by calling this method on all
   * dependent packages before doing anything else.  This method drives
   * initialization for interdependent packages directly, in parallel
   * with this package, itself.
   * <p>Of this package and its interdependencies, all packages which
   * have not yet been registered by their URI values are first created
   * and registered.  The packages are then initialized in two steps:
   * meta-model objects for all of the packages are created before any
   * are initialized, since one package's meta-model objects may refer to
   * those of another.
   * <p>Invocation of this method will not affect any packages that have
   * already been initialized.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static XMLTypePackage init()
  {
    if (isInited) return (XMLTypePackage)EPackage.Registry.INSTANCE.get(XMLTypePackage.eNS_URI);

    // Obtain or create and register package.
    XMLTypePackageImpl theXMLTypePackage = (XMLTypePackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EPackage ? EPackage.Registry.INSTANCE.get(eNS_URI) : new XMLTypePackageImpl());

    isInited = true;

    // Obtain or create and register interdependencies

    // Step 1: create meta-model objects
    theXMLTypePackage.createPackageContents();

    // Step 2: complete initialization
    theXMLTypePackage.initializePackageContents();

    return theXMLTypePackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAnyType()
  {
    return anyTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAnyType_Mixed()
  {
    return (EAttribute)anyTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAnyType_Any()
  {
    return (EAttribute)anyTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAnyType_AnyAttribute()
  {
    return (EAttribute)anyTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSimpleAnyType()
  {
    return simpleAnyTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSimpleAnyType_RawValue()
  {
    return (EAttribute)simpleAnyTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSimpleAnyType_Value()
  {
    return (EAttribute)simpleAnyTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSimpleAnyType_InstanceType()
  {
    return (EReference)simpleAnyTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getXMLTypeDocumentRoot()
  {
    return xmlTypeDocumentRootEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXMLTypeDocumentRoot_Mixed()
  {
    return (EAttribute)xmlTypeDocumentRootEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXMLTypeDocumentRoot_XMLNSPrefixMap()
  {
    return (EReference)xmlTypeDocumentRootEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getXMLTypeDocumentRoot_XSISchemaLocation()
  {
    return (EReference)xmlTypeDocumentRootEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXMLTypeDocumentRoot_CDATA()
  {
    return (EAttribute)xmlTypeDocumentRootEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXMLTypeDocumentRoot_Comment()
  {
    return (EAttribute)xmlTypeDocumentRootEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getXMLTypeDocumentRoot_Text()
  {
    return (EAttribute)xmlTypeDocumentRootEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getAnySimpleType()
  {
    return anySimpleTypeEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getAnyURI()
  {
    return anyURIEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getBase64Binary()
  {
    return base64BinaryEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getBoolean()
  {
    return booleanEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getBooleanObject()
  {
    return booleanObjectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getDecimal()
  {
    return decimalEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getInteger()
  {
    return integerEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getIntObject()
  {
    return intObjectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getLong()
  {
    return longEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getLongObject()
  {
    return longObjectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getInt()
  {
    return intEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getShort()
  {
    return shortEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getShortObject()
  {
    return shortObjectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getByte()
  {
    return byteEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getByteObject()
  {
    return byteObjectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getDate()
  {
    return dateEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getDateTime()
  {
    return dateTimeEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getString()
  {
    return stringEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getDouble()
  {
    return doubleEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getDoubleObject()
  {
    return doubleObjectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getDuration()
  {
    return durationEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getENTITIESBase()
  {
    return entitiesBaseEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getNormalizedString()
  {
    return normalizedStringEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getToken()
  {
    return tokenEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getName_()
  {
    return nameEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getNCName()
  {
    return ncNameEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getENTITY()
  {
    return entityEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getENTITIES()
  {
    return entitiesEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getFloat()
  {
    return floatEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getFloatObject()
  {
    return floatObjectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getGDay()
  {
    return gDayEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getGMonth()
  {
    return gMonthEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getGMonthDay()
  {
    return gMonthDayEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getGYear()
  {
    return gYearEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getGYearMonth()
  {
    return gYearMonthEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getHexBinary()
  {
    return hexBinaryEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getID()
  {
    return idEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getIDREF()
  {
    return idrefEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getIDREFSBase()
  {
    return idrefsBaseEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getIDREFS()
  {
    return idrefsEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getLanguage()
  {
    return languageEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getNonPositiveInteger()
  {
    return nonPositiveIntegerEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getNegativeInteger()
  {
    return negativeIntegerEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getNMTOKEN()
  {
    return nmtokenEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getNMTOKENSBase()
  {
    return nmtokensBaseEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getNMTOKENS()
  {
    return nmtokensEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getNonNegativeInteger()
  {
    return nonNegativeIntegerEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getNOTATION()
  {
    return notationEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getPositiveInteger()
  {
    return positiveIntegerEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getQName()
  {
    return qNameEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getTime()
  {
    return timeEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getUnsignedLong()
  {
    return unsignedLongEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getUnsignedInt()
  {
    return unsignedIntEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getUnsignedIntObject()
  {
    return unsignedIntObjectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getUnsignedShort()
  {
    return unsignedShortEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getUnsignedShortObject()
  {
    return unsignedShortObjectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getUnsignedByte()
  {
    return unsignedByteEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EDataType getUnsignedByteObject()
  {
    return unsignedByteObjectEDataType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XMLTypeFactory getXMLTypeFactory()
  {
    return (XMLTypeFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    anyTypeEClass = createEClass(ANY_TYPE);
    createEAttribute(anyTypeEClass, ANY_TYPE__MIXED);
    createEAttribute(anyTypeEClass, ANY_TYPE__ANY);
    createEAttribute(anyTypeEClass, ANY_TYPE__ANY_ATTRIBUTE);

    simpleAnyTypeEClass = createEClass(SIMPLE_ANY_TYPE);
    createEAttribute(simpleAnyTypeEClass, SIMPLE_ANY_TYPE__RAW_VALUE);
    createEAttribute(simpleAnyTypeEClass, SIMPLE_ANY_TYPE__VALUE);
    createEReference(simpleAnyTypeEClass, SIMPLE_ANY_TYPE__INSTANCE_TYPE);

    xmlTypeDocumentRootEClass = createEClass(XML_TYPE_DOCUMENT_ROOT);
    createEAttribute(xmlTypeDocumentRootEClass, XML_TYPE_DOCUMENT_ROOT__MIXED);
    createEReference(xmlTypeDocumentRootEClass, XML_TYPE_DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
    createEReference(xmlTypeDocumentRootEClass, XML_TYPE_DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
    createEAttribute(xmlTypeDocumentRootEClass, XML_TYPE_DOCUMENT_ROOT__CDATA);
    createEAttribute(xmlTypeDocumentRootEClass, XML_TYPE_DOCUMENT_ROOT__COMMENT);
    createEAttribute(xmlTypeDocumentRootEClass, XML_TYPE_DOCUMENT_ROOT__TEXT);

    // Create data types
    anySimpleTypeEDataType = createEDataType(ANY_SIMPLE_TYPE);
    anyURIEDataType = createEDataType(ANY_URI);
    base64BinaryEDataType = createEDataType(BASE64_BINARY);
    booleanEDataType = createEDataType(BOOLEAN);
    booleanObjectEDataType = createEDataType(BOOLEAN_OBJECT);
    byteEDataType = createEDataType(BYTE);
    byteObjectEDataType = createEDataType(BYTE_OBJECT);
    dateEDataType = createEDataType(DATE);
    dateTimeEDataType = createEDataType(DATE_TIME);
    decimalEDataType = createEDataType(DECIMAL);
    doubleEDataType = createEDataType(DOUBLE);
    doubleObjectEDataType = createEDataType(DOUBLE_OBJECT);
    durationEDataType = createEDataType(DURATION);
    entitiesEDataType = createEDataType(ENTITIES);
    entitiesBaseEDataType = createEDataType(ENTITIES_BASE);
    entityEDataType = createEDataType(ENTITY);
    floatEDataType = createEDataType(FLOAT);
    floatObjectEDataType = createEDataType(FLOAT_OBJECT);
    gDayEDataType = createEDataType(GDAY);
    gMonthEDataType = createEDataType(GMONTH);
    gMonthDayEDataType = createEDataType(GMONTH_DAY);
    gYearEDataType = createEDataType(GYEAR);
    gYearMonthEDataType = createEDataType(GYEAR_MONTH);
    hexBinaryEDataType = createEDataType(HEX_BINARY);
    idEDataType = createEDataType(ID);
    idrefEDataType = createEDataType(IDREF);
    idrefsEDataType = createEDataType(IDREFS);
    idrefsBaseEDataType = createEDataType(IDREFS_BASE);
    intEDataType = createEDataType(INT);
    integerEDataType = createEDataType(INTEGER);
    intObjectEDataType = createEDataType(INT_OBJECT);
    languageEDataType = createEDataType(LANGUAGE);
    longEDataType = createEDataType(LONG);
    longObjectEDataType = createEDataType(LONG_OBJECT);
    nameEDataType = createEDataType(NAME);
    ncNameEDataType = createEDataType(NC_NAME);
    negativeIntegerEDataType = createEDataType(NEGATIVE_INTEGER);
    nmtokenEDataType = createEDataType(NMTOKEN);
    nmtokensEDataType = createEDataType(NMTOKENS);
    nmtokensBaseEDataType = createEDataType(NMTOKENS_BASE);
    nonNegativeIntegerEDataType = createEDataType(NON_NEGATIVE_INTEGER);
    nonPositiveIntegerEDataType = createEDataType(NON_POSITIVE_INTEGER);
    normalizedStringEDataType = createEDataType(NORMALIZED_STRING);
    notationEDataType = createEDataType(NOTATION);
    positiveIntegerEDataType = createEDataType(POSITIVE_INTEGER);
    qNameEDataType = createEDataType(QNAME);
    shortEDataType = createEDataType(SHORT);
    shortObjectEDataType = createEDataType(SHORT_OBJECT);
    stringEDataType = createEDataType(STRING);
    timeEDataType = createEDataType(TIME);
    tokenEDataType = createEDataType(TOKEN);
    unsignedByteEDataType = createEDataType(UNSIGNED_BYTE);
    unsignedByteObjectEDataType = createEDataType(UNSIGNED_BYTE_OBJECT);
    unsignedIntEDataType = createEDataType(UNSIGNED_INT);
    unsignedIntObjectEDataType = createEDataType(UNSIGNED_INT_OBJECT);
    unsignedLongEDataType = createEDataType(UNSIGNED_LONG);
    unsignedShortEDataType = createEDataType(UNSIGNED_SHORT);
    unsignedShortObjectEDataType = createEDataType(UNSIGNED_SHORT_OBJECT);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    XMLTypePackageImpl theXMLTypePackage_1 = (XMLTypePackageImpl)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

    // Add supertypes to classes
    simpleAnyTypeEClass.getESuperTypes().add(this.getAnyType());

    // Initialize classes and features; add operations and parameters
    initEClass(anyTypeEClass, AnyType.class, "AnyType", !IS_ABSTRACT, !IS_INTERFACE);
    initEAttribute(getAnyType_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED);
    initEAttribute(getAnyType_Any(), ecorePackage.getEFeatureMapEntry(), "any", null, 0, -1, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED);
    initEAttribute(getAnyType_AnyAttribute(), ecorePackage.getEFeatureMapEntry(), "anyAttribute", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED);

    initEClass(simpleAnyTypeEClass, SimpleAnyType.class, "SimpleAnyType", !IS_ABSTRACT, !IS_INTERFACE);
    initEAttribute(getSimpleAnyType_RawValue(), theXMLTypePackage_1.getString(), "rawValue", null, 0, 1, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED);
    initEAttribute(getSimpleAnyType_Value(), theXMLTypePackage_1.getAnySimpleType(), "value", null, 0, 1, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED);
    initEReference(getSimpleAnyType_InstanceType(), ecorePackage.getEDataType(), null, "instanceType", null, 1, 1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED);

    initEClass(xmlTypeDocumentRootEClass, XMLTypeDocumentRoot.class, "XMLTypeDocumentRoot", !IS_ABSTRACT, !IS_INTERFACE);
    initEAttribute(getXMLTypeDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED);
    initEReference(getXMLTypeDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED);
    initEReference(getXMLTypeDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED);
    initEAttribute(getXMLTypeDocumentRoot_CDATA(), this.getString(), "cDATA", null, 0, -2, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED);
    initEAttribute(getXMLTypeDocumentRoot_Comment(), this.getString(), "comment", null, 0, -2, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED);
    initEAttribute(getXMLTypeDocumentRoot_Text(), this.getString(), "text", null, 0, -2, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED);

    // Initialize data types
    initEDataType(anySimpleTypeEDataType, Object.class, "AnySimpleType", IS_SERIALIZABLE);
    initEDataType(anyURIEDataType, String.class, "AnyURI", IS_SERIALIZABLE);
    initEDataType(base64BinaryEDataType, Object.class, "Base64Binary", IS_SERIALIZABLE);
    initEDataType(booleanEDataType, boolean.class, "Boolean", IS_SERIALIZABLE);
    initEDataType(booleanObjectEDataType, Boolean.class, "BooleanObject", IS_SERIALIZABLE);
    initEDataType(byteEDataType, byte.class, "Byte", IS_SERIALIZABLE);
    initEDataType(byteObjectEDataType, Byte.class, "ByteObject", IS_SERIALIZABLE);
    initEDataType(dateEDataType, Object.class, "Date", IS_SERIALIZABLE);
    initEDataType(dateTimeEDataType, Object.class, "DateTime", IS_SERIALIZABLE);
    initEDataType(decimalEDataType, BigDecimal.class, "Decimal", IS_SERIALIZABLE);
    initEDataType(doubleEDataType, double.class, "Double", IS_SERIALIZABLE);
    initEDataType(doubleObjectEDataType, Double.class, "DoubleObject", IS_SERIALIZABLE);
    initEDataType(durationEDataType, Object.class, "Duration", IS_SERIALIZABLE);
    initEDataType(entitiesEDataType, List.class, "ENTITIES", IS_SERIALIZABLE);
    initEDataType(entitiesBaseEDataType, List.class, "ENTITIESBase", IS_SERIALIZABLE);
    initEDataType(entityEDataType, String.class, "ENTITY", IS_SERIALIZABLE);
    initEDataType(floatEDataType, float.class, "Float", IS_SERIALIZABLE);
    initEDataType(floatObjectEDataType, Float.class, "FloatObject", IS_SERIALIZABLE);
    initEDataType(gDayEDataType, Object.class, "GDay", IS_SERIALIZABLE);
    initEDataType(gMonthEDataType, Object.class, "GMonth", IS_SERIALIZABLE);
    initEDataType(gMonthDayEDataType, Object.class, "GMonthDay", IS_SERIALIZABLE);
    initEDataType(gYearEDataType, Object.class, "GYear", IS_SERIALIZABLE);
    initEDataType(gYearMonthEDataType, Object.class, "GYearMonth", IS_SERIALIZABLE);
    initEDataType(hexBinaryEDataType, Object.class, "HexBinary", IS_SERIALIZABLE);
    initEDataType(idEDataType, String.class, "ID", IS_SERIALIZABLE);
    initEDataType(idrefEDataType, String.class, "IDREF", IS_SERIALIZABLE);
    initEDataType(idrefsEDataType, List.class, "IDREFS", IS_SERIALIZABLE);
    initEDataType(idrefsBaseEDataType, List.class, "IDREFSBase", IS_SERIALIZABLE);
    initEDataType(intEDataType, int.class, "Int", IS_SERIALIZABLE);
    initEDataType(integerEDataType, BigInteger.class, "Integer", IS_SERIALIZABLE);
    initEDataType(intObjectEDataType, Integer.class, "IntObject", IS_SERIALIZABLE);
    initEDataType(languageEDataType, String.class, "Language", IS_SERIALIZABLE);
    initEDataType(longEDataType, long.class, "Long", IS_SERIALIZABLE);
    initEDataType(longObjectEDataType, Long.class, "LongObject", IS_SERIALIZABLE);
    initEDataType(nameEDataType, String.class, "Name", IS_SERIALIZABLE);
    initEDataType(ncNameEDataType, String.class, "NCName", IS_SERIALIZABLE);
    initEDataType(negativeIntegerEDataType, BigInteger.class, "NegativeInteger", IS_SERIALIZABLE);
    initEDataType(nmtokenEDataType, String.class, "NMTOKEN", IS_SERIALIZABLE);
    initEDataType(nmtokensEDataType, List.class, "NMTOKENS", IS_SERIALIZABLE);
    initEDataType(nmtokensBaseEDataType, List.class, "NMTOKENSBase", IS_SERIALIZABLE);
    initEDataType(nonNegativeIntegerEDataType, BigInteger.class, "NonNegativeInteger", IS_SERIALIZABLE);
    initEDataType(nonPositiveIntegerEDataType, BigInteger.class, "NonPositiveInteger", IS_SERIALIZABLE);
    initEDataType(normalizedStringEDataType, String.class, "NormalizedString", IS_SERIALIZABLE);
    initEDataType(notationEDataType, Object.class, "NOTATION", IS_SERIALIZABLE);
    initEDataType(positiveIntegerEDataType, BigInteger.class, "PositiveInteger", IS_SERIALIZABLE);
    initEDataType(qNameEDataType, Object.class, "QName", IS_SERIALIZABLE);
    initEDataType(shortEDataType, short.class, "Short", IS_SERIALIZABLE);
    initEDataType(shortObjectEDataType, Short.class, "ShortObject", IS_SERIALIZABLE);
    initEDataType(stringEDataType, String.class, "String", IS_SERIALIZABLE);
    initEDataType(timeEDataType, Object.class, "Time", IS_SERIALIZABLE);
    initEDataType(tokenEDataType, String.class, "Token", IS_SERIALIZABLE);
    initEDataType(unsignedByteEDataType, short.class, "UnsignedByte", IS_SERIALIZABLE);
    initEDataType(unsignedByteObjectEDataType, Short.class, "UnsignedByteObject", IS_SERIALIZABLE);
    initEDataType(unsignedIntEDataType, long.class, "UnsignedInt", IS_SERIALIZABLE);
    initEDataType(unsignedIntObjectEDataType, Long.class, "UnsignedIntObject", IS_SERIALIZABLE);
    initEDataType(unsignedLongEDataType, BigInteger.class, "UnsignedLong", IS_SERIALIZABLE);
    initEDataType(unsignedShortEDataType, int.class, "UnsignedShort", IS_SERIALIZABLE);
    initEDataType(unsignedShortObjectEDataType, Integer.class, "UnsignedShortObject", IS_SERIALIZABLE);

    // Create resource
    createResource(eNS_URI);

    // Create annotations
    // http:///org/eclipse/emf/ecore/util/ExtendedMetaData
    createExtendedMetaDataAnnotations();
    // null
    createNullAnnotations();
  }

  /**
   * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void createExtendedMetaDataAnnotations()
  {
    String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";		
    addAnnotation
      (anySimpleTypeEDataType, 
       source, 
       new String[] 
       {
       "name", "anySimpleType"
       });		
    addAnnotation
      (anyTypeEClass, 
       source, 
       new String[] 
       {
       "name", "anyType",
       "kind", "mixed"
       });		
    addAnnotation
      (getAnyType_Mixed(), 
       source, 
       new String[] 
       {
       "kind", "elementWildcard",
       "name", ":mixed"
       });		
    addAnnotation
      (getAnyType_Any(), 
       source, 
       new String[] 
       {
       "kind", "elementWildcard",
       "wildcards", "##any",
       "name", ":1",
       "processing", "lax"
       });		
    addAnnotation
      (getAnyType_AnyAttribute(), 
       source, 
       new String[] 
       {
       "kind", "attributeWildcard",
       "wildcards", "##any",
       "name", ":2",
       "processing", "lax"
       });			
    addAnnotation
      (anyURIEDataType, 
       source, 
       new String[] 
       {
       "name", "anyURI"
       });			
    addAnnotation
      (base64BinaryEDataType, 
       source, 
       new String[] 
       {
       "name", "base64Binary",
       "baseType", "anySimpleType"
       });			
    addAnnotation
      (booleanEDataType, 
       source, 
       new String[] 
       {
       "name", "boolean"
       });		
    addAnnotation
      (booleanObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "boolean:Object",
       "baseType", "boolean"
       });		
    addAnnotation
      (byteEDataType, 
       source, 
       new String[] 
       {
       "name", "byte"
       });		
    addAnnotation
      (byteObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "byte:Object",
       "baseType", "byte"
       });			
    addAnnotation
      (dateEDataType, 
       source, 
       new String[] 
       {
       "name", "date",
       "baseType", "anySimpleType"
       });			
    addAnnotation
      (dateTimeEDataType, 
       source, 
       new String[] 
       {
       "name", "dateTime",
       "baseType", "anySimpleType"
       });			
    addAnnotation
      (decimalEDataType, 
       source, 
       new String[] 
       {
       "name", "decimal"
       });			
    addAnnotation
      (doubleEDataType, 
       source, 
       new String[] 
       {
       "name", "double"
       });		
    addAnnotation
      (doubleObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "double:Object",
       "baseType", "double"
       });			
    addAnnotation
      (durationEDataType, 
       source, 
       new String[] 
       {
       "name", "duration",
       "baseType", "anySimpleType"
       });			
    addAnnotation
      (entitiesEDataType, 
       source, 
       new String[] 
       {
       "name", "ENTITIES",
       "baseType", "ENTITIES_._base"
       });		
    addAnnotation
      (entitiesBaseEDataType, 
       source, 
       new String[] 
       {
       "name", "ENTITIES_._base",
       "itemType", "ENTITY"
       });		
    addAnnotation
      (entityEDataType, 
       source, 
       new String[] 
       {
       "name", "ENTITY",
       "baseType", "NCName"
       });			
    addAnnotation
      (floatEDataType, 
       source, 
       new String[] 
       {
       "name", "float"
       });		
    addAnnotation
      (floatObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "float:Object",
       "baseType", "float"
       });			
    addAnnotation
      (gDayEDataType, 
       source, 
       new String[] 
       {
       "name", "gDay",
       "baseType", "anySimpleType"
       });			
    addAnnotation
      (gMonthEDataType, 
       source, 
       new String[] 
       {
       "name", "gMonth",
       "baseType", "anySimpleType"
       });			
    addAnnotation
      (gMonthDayEDataType, 
       source, 
       new String[] 
       {
       "name", "gMonthDay",
       "baseType", "anySimpleType"
       });			
    addAnnotation
      (gYearEDataType, 
       source, 
       new String[] 
       {
       "name", "gYear",
       "baseType", "anySimpleType"
       });			
    addAnnotation
      (gYearMonthEDataType, 
       source, 
       new String[] 
       {
       "name", "gYearMonth",
       "baseType", "anySimpleType"
       });			
    addAnnotation
      (hexBinaryEDataType, 
       source, 
       new String[] 
       {
       "name", "hexBinary",
       "baseType", "anySimpleType"
       });		
    addAnnotation
      (idEDataType, 
       source, 
       new String[] 
       {
       "name", "ID",
       "baseType", "NCName"
       });		
    addAnnotation
      (idrefEDataType, 
       source, 
       new String[] 
       {
       "name", "IDREF",
       "baseType", "NCName"
       });			
    addAnnotation
      (idrefsEDataType, 
       source, 
       new String[] 
       {
       "name", "IDREFS",
       "baseType", "IDREFS_._base"
       });		
    addAnnotation
      (idrefsBaseEDataType, 
       source, 
       new String[] 
       {
       "name", "IDREFS_._base",
       "itemType", "IDREF"
       });		
    addAnnotation
      (intEDataType, 
       source, 
       new String[] 
       {
       "name", "int"
       });		
    addAnnotation
      (integerEDataType, 
       source, 
       new String[] 
       {
       "name", "integer"
       });		
    addAnnotation
      (intObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "int:Object",
       "baseType", "int"
       });		
    addAnnotation
      (languageEDataType, 
       source, 
       new String[] 
       {
       "name", "language",
       "baseType", "token"
       });			
    addAnnotation
      (longEDataType, 
       source, 
       new String[] 
       {
       "name", "long"
       });		
    addAnnotation
      (longObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "long:Object",
       "baseType", "long"
       });		
    addAnnotation
      (nameEDataType, 
       source, 
       new String[] 
       {
       "name", "Name",
       "baseType", "token"
       });		
    addAnnotation
      (ncNameEDataType, 
       source, 
       new String[] 
       {
       "name", "NCName",
       "baseType", "Name"
       });		
    addAnnotation
      (negativeIntegerEDataType, 
       source, 
       new String[] 
       {
       "name", "negativeInteger",
       "baseType", "nonPositiveInteger"
       });		
    addAnnotation
      (nmtokenEDataType, 
       source, 
       new String[] 
       {
       "name", "NMTOKEN",
       "baseType", "token"
       });			
    addAnnotation
      (nmtokensEDataType, 
       source, 
       new String[] 
       {
       "name", "NMTOKENS",
       "baseType", "NMTOKENS_._base"
       });		
    addAnnotation
      (nmtokensBaseEDataType, 
       source, 
       new String[] 
       {
       "name", "NMTOKENS_._base",
       "itemType", "NMTOKEN"
       });		
    addAnnotation
      (nonNegativeIntegerEDataType, 
       source, 
       new String[] 
       {
       "name", "nonNegativeInteger",
       "baseType", "integer"
       });		
    addAnnotation
      (nonPositiveIntegerEDataType, 
       source, 
       new String[] 
       {
       "name", "nonPositiveInteger",
       "baseType", "integer"
       });		
    addAnnotation
      (normalizedStringEDataType, 
       source, 
       new String[] 
       {
       "name", "normalizedString",
       "baseType", "string"
       });			
    addAnnotation
      (notationEDataType, 
       source, 
       new String[] 
       {
       "name", "NOTATION",
       "baseType", "anySimpleType"
       });		
    addAnnotation
      (positiveIntegerEDataType, 
       source, 
       new String[] 
       {
       "name", "positiveInteger",
       "baseType", "nonNegativeInteger"
       });			
    addAnnotation
      (qNameEDataType, 
       source, 
       new String[] 
       {
       "name", "QName",
       "baseType", "anySimpleType"
       });		
    addAnnotation
      (shortEDataType, 
       source, 
       new String[] 
       {
       "name", "short"
       });		
    addAnnotation
      (shortObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "short:Object",
       "baseType", "short"
       });		
    addAnnotation
      (simpleAnyTypeEClass, 
       source, 
       new String[] 
       {
       "name", "simpleAnyType",
       "kind", "simple"
       });		
    addAnnotation
      (getSimpleAnyType_RawValue(), 
       source, 
       new String[] 
       {
       "name", ":3",
       "kind", "simple"
       });		
    addAnnotation
      (getSimpleAnyType_Value(), 
       source, 
       new String[] 
       {
       "name", ":4",
       "kind", "simple"
       });		
    addAnnotation
      (getSimpleAnyType_InstanceType(), 
       source, 
       new String[] 
       {
       "name", ":5",
       "kind", "simple"
       });			
    addAnnotation
      (stringEDataType, 
       source, 
       new String[] 
       {
       "name", "string"
       });			
    addAnnotation
      (timeEDataType, 
       source, 
       new String[] 
       {
       "name", "time",
       "baseType", "anySimpleType"
       });		
    addAnnotation
      (tokenEDataType, 
       source, 
       new String[] 
       {
       "name", "token",
       "baseType", "normalizedString"
       });		
    addAnnotation
      (unsignedByteEDataType, 
       source, 
       new String[] 
       {
       "name", "unsignedByte"
       });		
    addAnnotation
      (unsignedByteObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "unsignedByte:Object",
       "baseType", "unsignedByte"
       });		
    addAnnotation
      (unsignedIntEDataType, 
       source, 
       new String[] 
       {
       "name", "unsignedInt"
       });		
    addAnnotation
      (unsignedIntObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "unsignedInt:Object",
       "baseType", "unsignedInt"
       });			
    addAnnotation
      (unsignedLongEDataType, 
       source, 
       new String[] 
       {
       "name", "unsignedLong",
       "baseType", "nonNegativeInteger"
       });		
    addAnnotation
      (unsignedShortEDataType, 
       source, 
       new String[] 
       {
       "name", "unsignedShort"
       });		
    addAnnotation
      (unsignedShortObjectEDataType, 
       source, 
       new String[] 
       {
       "name", "unsignedShort:Object",
       "baseType", "unsignedShort"
       });		
    addAnnotation
      (xmlTypeDocumentRootEClass, 
       source, 
       new String[] 
       {
       "name", "",
       "kind", "mixed"
       });		
    addAnnotation
      (getXMLTypeDocumentRoot_Mixed(), 
       source, 
       new String[] 
       {
       "kind", "elementWildcard",
       "name", ":mixed"
       });		
    addAnnotation
      (getXMLTypeDocumentRoot_XMLNSPrefixMap(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "xmlns:prefix"
       });		
    addAnnotation
      (getXMLTypeDocumentRoot_XSISchemaLocation(), 
       source, 
       new String[] 
       {
       "kind", "attribute",
       "name", "xsi:schemaLocation"
       });		
    addAnnotation
      (getXMLTypeDocumentRoot_CDATA(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "cDATA",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getXMLTypeDocumentRoot_Comment(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "comment",
       "namespace", "##targetNamespace"
       });		
    addAnnotation
      (getXMLTypeDocumentRoot_Text(), 
       source, 
       new String[] 
       {
       "kind", "element",
       "name", "text",
       "namespace", "##targetNamespace"
       });
  }

  /**
   * Initializes the annotations for <b>null</b>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void createNullAnnotations()
  {
  }
} //XMLTypePackageImpl
