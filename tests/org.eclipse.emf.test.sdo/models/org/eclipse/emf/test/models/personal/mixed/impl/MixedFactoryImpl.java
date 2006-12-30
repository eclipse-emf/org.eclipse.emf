/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: MixedFactoryImpl.java,v 1.2 2006/12/30 03:44:07 marcelop Exp $
 */
package org.eclipse.emf.test.models.personal.mixed.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.test.models.personal.mixed.ContrType;
import org.eclipse.emf.test.models.personal.mixed.DocumentRoot;
import org.eclipse.emf.test.models.personal.mixed.LinkType;
import org.eclipse.emf.test.models.personal.mixed.MixedFactory;
import org.eclipse.emf.test.models.personal.mixed.MixedPackage;
import org.eclipse.emf.test.models.personal.mixed.NameType;
import org.eclipse.emf.test.models.personal.mixed.PersonType;
import org.eclipse.emf.test.models.personal.mixed.PersonnelType;
import org.eclipse.emf.test.models.personal.mixed.UrlType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MixedFactoryImpl extends EFactoryImpl implements MixedFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static MixedFactory init()
  {
    try
    {
      MixedFactory theMixedFactory = (MixedFactory)EPackage.Registry.INSTANCE.getEFactory("http://org/eclipse/emf/test/models/personalMixed"); 
      if (theMixedFactory != null)
      {
        return theMixedFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new MixedFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MixedFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case MixedPackage.DOCUMENT_ROOT: return (EObject)createDocumentRoot();
      case MixedPackage.LINK_TYPE: return (EObject)createLinkType();
      case MixedPackage.NAME_TYPE: return (EObject)createNameType();
      case MixedPackage.PERSONNEL_TYPE: return (EObject)createPersonnelType();
      case MixedPackage.PERSON_TYPE: return (EObject)createPersonType();
      case MixedPackage.URL_TYPE: return (EObject)createUrlType();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case MixedPackage.CONTR_TYPE:
        return createContrTypeFromString(eDataType, initialValue);
      case MixedPackage.CONTR_TYPE_OBJECT:
        return createContrTypeObjectFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case MixedPackage.CONTR_TYPE:
        return convertContrTypeToString(eDataType, instanceValue);
      case MixedPackage.CONTR_TYPE_OBJECT:
        return convertContrTypeObjectToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DocumentRoot createDocumentRoot()
  {
    DocumentRootImpl documentRoot = new DocumentRootImpl();
    return documentRoot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LinkType createLinkType()
  {
    LinkTypeImpl linkType = new LinkTypeImpl();
    return linkType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NameType createNameType()
  {
    NameTypeImpl nameType = new NameTypeImpl();
    return nameType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PersonnelType createPersonnelType()
  {
    PersonnelTypeImpl personnelType = new PersonnelTypeImpl();
    return personnelType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PersonType createPersonType()
  {
    PersonTypeImpl personType = new PersonTypeImpl();
    return personType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UrlType createUrlType()
  {
    UrlTypeImpl urlType = new UrlTypeImpl();
    return urlType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ContrType createContrTypeFromString(EDataType eDataType, String initialValue)
  {
    ContrType result = ContrType.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertContrTypeToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ContrType createContrTypeObjectFromString(EDataType eDataType, String initialValue)
  {
    return createContrTypeFromString(MixedPackage.Literals.CONTR_TYPE, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertContrTypeObjectToString(EDataType eDataType, Object instanceValue)
  {
    return convertContrTypeToString(MixedPackage.Literals.CONTR_TYPE, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MixedPackage getMixedPackage()
  {
    return (MixedPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static MixedPackage getPackage()
  {
    return MixedPackage.eINSTANCE;
  }

} //MixedFactoryImpl
