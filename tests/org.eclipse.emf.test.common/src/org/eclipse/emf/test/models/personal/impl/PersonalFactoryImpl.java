/**
 * <copyright>
 * </copyright>
 *
 * $Id: PersonalFactoryImpl.java,v 1.1 2007/01/18 15:50:15 marcelop Exp $
 */
package org.eclipse.emf.test.models.personal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.test.models.personal.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class PersonalFactoryImpl extends EFactoryImpl implements PersonalFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final PersonalFactoryImpl eINSTANCE = init();

  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static PersonalFactoryImpl init()
  {
    try
    {
      PersonalFactoryImpl thePersonalFactory = (PersonalFactoryImpl)EPackage.Registry.INSTANCE.getEFactory("http:///org.eclipse.emf.test.models/personal"); 
      if (thePersonalFactory != null)
      {
        return thePersonalFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new PersonalFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PersonalFactoryImpl()
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
      case PersonalPackageImpl.DOCUMENT_ROOT: return (EObject)createDocumentRoot();
      case PersonalPackageImpl.LINK_TYPE: return (EObject)createLinkType();
      case PersonalPackageImpl.NAME_TYPE: return (EObject)createNameType();
      case PersonalPackageImpl.PERSONNEL_TYPE: return (EObject)createPersonnelType();
      case PersonalPackageImpl.PERSON_TYPE: return (EObject)createPersonType();
      case PersonalPackageImpl.URL_TYPE: return (EObject)createUrlType();
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
      case PersonalPackageImpl.CONTR_TYPE:
        return createContrTypeFromString(eDataType, initialValue);
      case PersonalPackageImpl.CONTR_TYPE_OBJECT:
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
      case PersonalPackageImpl.CONTR_TYPE:
        return convertContrTypeToString(eDataType, instanceValue);
      case PersonalPackageImpl.CONTR_TYPE_OBJECT:
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
    return createContrTypeFromString(PersonalPackageImpl.Literals.CONTR_TYPE, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertContrTypeObjectToString(EDataType eDataType, Object instanceValue)
  {
    return convertContrTypeToString(PersonalPackageImpl.Literals.CONTR_TYPE, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PersonalPackageImpl getPersonalPackageImpl()
  {
    return (PersonalPackageImpl)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static PersonalPackageImpl getPackage()
  {
    return PersonalPackageImpl.eINSTANCE;
  }

} //PersonalFactoryImpl
