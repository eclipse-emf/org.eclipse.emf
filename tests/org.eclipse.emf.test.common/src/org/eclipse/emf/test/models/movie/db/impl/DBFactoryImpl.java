/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.models.movie.db.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.test.models.movie.db.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DBFactoryImpl extends EFactoryImpl implements DBFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static DBFactory init()
  {
    try
    {
      DBFactory theDBFactory = (DBFactory)EPackage.Registry.INSTANCE.getEFactory("http:///org.eclipse.emf.test.models/MovieDB"); 
      if (theDBFactory != null)
      {
        return theDBFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new DBFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DBFactoryImpl()
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
      case DBPackage.CRITICS_REVIEW_TYPE: return createCriticsReviewType();
      case DBPackage.CUSTOMER_REVIEW_TYPE: return createCustomerReviewType();
      case DBPackage.DOCUMENT_ROOT: return createDocumentRoot();
      case DBPackage.MOVIE_DB_TYPE: return createMovieDBType();
      case DBPackage.MOVIE_TYPE: return createMovieType();
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
      case DBPackage.GENRE_TYPES:
        return createGenreTypesFromString(eDataType, initialValue);
      case DBPackage.ACTORS_LIST:
        return createActorsListFromString(eDataType, initialValue);
      case DBPackage.GENRE_TYPES_OBJECT:
        return createGenreTypesObjectFromString(eDataType, initialValue);
      case DBPackage.RATING_TYPE:
        return createRatingTypeFromString(eDataType, initialValue);
      case DBPackage.RATING_TYPE_OBJECT:
        return createRatingTypeObjectFromString(eDataType, initialValue);
      case DBPackage.RATING_VALUES:
        return createRatingValuesFromString(eDataType, initialValue);
      case DBPackage.RATING_VALUES_OBJECT:
        return createRatingValuesObjectFromString(eDataType, initialValue);
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
      case DBPackage.GENRE_TYPES:
        return convertGenreTypesToString(eDataType, instanceValue);
      case DBPackage.ACTORS_LIST:
        return convertActorsListToString(eDataType, instanceValue);
      case DBPackage.GENRE_TYPES_OBJECT:
        return convertGenreTypesObjectToString(eDataType, instanceValue);
      case DBPackage.RATING_TYPE:
        return convertRatingTypeToString(eDataType, instanceValue);
      case DBPackage.RATING_TYPE_OBJECT:
        return convertRatingTypeObjectToString(eDataType, instanceValue);
      case DBPackage.RATING_VALUES:
        return convertRatingValuesToString(eDataType, instanceValue);
      case DBPackage.RATING_VALUES_OBJECT:
        return convertRatingValuesObjectToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CriticsReviewType createCriticsReviewType()
  {
    CriticsReviewTypeImpl criticsReviewType = new CriticsReviewTypeImpl();
    return criticsReviewType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CustomerReviewType createCustomerReviewType()
  {
    CustomerReviewTypeImpl customerReviewType = new CustomerReviewTypeImpl();
    return customerReviewType;
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
  public MovieDBType createMovieDBType()
  {
    MovieDBTypeImpl movieDBType = new MovieDBTypeImpl();
    return movieDBType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MovieType createMovieType()
  {
    MovieTypeImpl movieType = new MovieTypeImpl();
    return movieType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenreTypes createGenreTypesFromString(EDataType eDataType, String initialValue)
  {
    GenreTypes result = GenreTypes.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertGenreTypesToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List<String> createActorsListFromString(EDataType eDataType, String initialValue)
  {
    if (initialValue == null) return null;
    List<String> result = new ArrayList<String>();
    for (StringTokenizer stringTokenizer = new StringTokenizer(initialValue); stringTokenizer.hasMoreTokens(); )
    {
      String item = stringTokenizer.nextToken();
      result.add((String)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.NC_NAME, item));
    }
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertActorsListToString(EDataType eDataType, Object instanceValue)
  {
    if (instanceValue == null) return null;
    List<?> list = (List<?>)instanceValue;
    if (list.isEmpty()) return "";
    StringBuffer result = new StringBuffer();
    for (Object item : list)
    {
      result.append(XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.NC_NAME, item));
      result.append(' ');
    }
    return result.substring(0, result.length() - 1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenreTypes createGenreTypesObjectFromString(EDataType eDataType, String initialValue)
  {
    return createGenreTypesFromString(DBPackage.Literals.GENRE_TYPES, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertGenreTypesObjectToString(EDataType eDataType, Object instanceValue)
  {
    return convertGenreTypesToString(DBPackage.Literals.GENRE_TYPES, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Integer createRatingTypeFromString(EDataType eDataType, String initialValue)
  {
    return (Integer)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.INT, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertRatingTypeToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.INT, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Integer createRatingTypeObjectFromString(EDataType eDataType, String initialValue)
  {
    return createRatingTypeFromString(DBPackage.Literals.RATING_TYPE, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertRatingTypeObjectToString(EDataType eDataType, Object instanceValue)
  {
    return convertRatingTypeToString(DBPackage.Literals.RATING_TYPE, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Integer createRatingValuesFromString(EDataType eDataType, String initialValue)
  {
    return (Integer)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.Literals.INT, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertRatingValuesToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.Literals.INT, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Integer createRatingValuesObjectFromString(EDataType eDataType, String initialValue)
  {
    return createRatingValuesFromString(DBPackage.Literals.RATING_VALUES, initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertRatingValuesObjectToString(EDataType eDataType, Object instanceValue)
  {
    return convertRatingValuesToString(DBPackage.Literals.RATING_VALUES, instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DBPackage getDBPackage()
  {
    return (DBPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static DBPackage getPackage()
  {
    return DBPackage.eINSTANCE;
  }

} //DBFactoryImpl
