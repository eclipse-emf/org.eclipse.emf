/**
 * <copyright>
 * </copyright>
 *
 * $Id: MixedFactoryImpl.java,v 1.1 2005/06/01 22:28:12 elena Exp $
 */
package org.eclipse.emf.test.models.personal.mixed.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.test.models.personal.mixed.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MixedFactoryImpl extends EFactoryImpl implements MixedFactory
{
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
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case MixedPackage.CONTR_TYPE:
      {
        ContrType result = ContrType.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
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
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case MixedPackage.CONTR_TYPE:
        return instanceValue == null ? null : instanceValue.toString();
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
  public ContrType createContrTypeObjectFromString(EDataType eDataType, String initialValue)
  {
    return (ContrType)MixedFactory.eINSTANCE.createFromString(MixedPackage.eINSTANCE.getContrType(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertContrTypeObjectToString(EDataType eDataType, Object instanceValue)
  {
    return MixedFactory.eINSTANCE.convertToString(MixedPackage.eINSTANCE.getContrType(), instanceValue);
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
  public static MixedPackage getPackage()
  {
    return MixedPackage.eINSTANCE;
  }

} //MixedFactoryImpl
