/**
 * <copyright>
 * </copyright>
 *
 * $Id: DbFactoryImpl.java,v 1.1.2.1 2005/01/14 22:56:18 nickb Exp $
 */
package org.eclipse.emf.test.models.movie.db.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.eclipse.emf.test.models.movie.db.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DbFactoryImpl extends EFactoryImpl implements DbFactory
{
  /**
   * Creates and instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DbFactoryImpl()
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
      case DbPackage.CRITICS_REVIEW_TYPE: return createCriticsReviewType();
      case DbPackage.CUSTOMER_REVIEW_TYPE: return createCustomerReviewType();
      case DbPackage.DOCUMENT_ROOT: return createDocumentRoot();
      case DbPackage.MOVIE_DB_TYPE: return createMovieDBType();
      case DbPackage.MOVIE_TYPE: return createMovieType();
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
      case DbPackage.GENRE_TYPES:
      {
        GenreTypes result = GenreTypes.get(initialValue);
        if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
      }
      case DbPackage.ACTORS_LIST:
        return createActorsListFromString(eDataType, initialValue);
      case DbPackage.GENRE_TYPES_OBJECT:
        return createGenreTypesObjectFromString(eDataType, initialValue);
      case DbPackage.RATING_TYPE:
        return createRatingTypeFromString(eDataType, initialValue);
      case DbPackage.RATING_TYPE_OBJECT:
        return createRatingTypeObjectFromString(eDataType, initialValue);
      case DbPackage.RATING_VALUES:
        return createRatingValuesFromString(eDataType, initialValue);
      case DbPackage.RATING_VALUES_OBJECT:
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
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case DbPackage.GENRE_TYPES:
        return instanceValue == null ? null : instanceValue.toString();
      case DbPackage.ACTORS_LIST:
        return convertActorsListToString(eDataType, instanceValue);
      case DbPackage.GENRE_TYPES_OBJECT:
        return convertGenreTypesObjectToString(eDataType, instanceValue);
      case DbPackage.RATING_TYPE:
        return convertRatingTypeToString(eDataType, instanceValue);
      case DbPackage.RATING_TYPE_OBJECT:
        return convertRatingTypeObjectToString(eDataType, instanceValue);
      case DbPackage.RATING_VALUES:
        return convertRatingValuesToString(eDataType, instanceValue);
      case DbPackage.RATING_VALUES_OBJECT:
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
  public List createActorsListFromString(EDataType eDataType, String initialValue)
  {
    if (initialValue == null) return null;
    List result = new ArrayList();
    for (StringTokenizer stringTokenizer = new StringTokenizer(initialValue); stringTokenizer.hasMoreTokens(); )
    {
      String item = stringTokenizer.nextToken();
      result.add(XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getNCName(), item));
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
    List list = (List)instanceValue;
    if (list.isEmpty()) return "";
    StringBuffer result = new StringBuffer();
    for (Iterator i = list.iterator(); i.hasNext(); )
    {
      result.append(XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getNCName(), i.next()));
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
    return (GenreTypes)DbFactory.eINSTANCE.createFromString(DbPackage.eINSTANCE.getGenreTypes(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertGenreTypesObjectToString(EDataType eDataType, Object instanceValue)
  {
    return DbFactory.eINSTANCE.convertToString(DbPackage.eINSTANCE.getGenreTypes(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Integer createRatingTypeFromString(EDataType eDataType, String initialValue)
  {
    return (Integer)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInt(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertRatingTypeToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInt(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Integer createRatingTypeObjectFromString(EDataType eDataType, String initialValue)
  {
    return (Integer)DbFactory.eINSTANCE.createFromString(DbPackage.eINSTANCE.getRatingType(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertRatingTypeObjectToString(EDataType eDataType, Object instanceValue)
  {
    return DbFactory.eINSTANCE.convertToString(DbPackage.eINSTANCE.getRatingType(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Integer createRatingValuesFromString(EDataType eDataType, String initialValue)
  {
    return (Integer)XMLTypeFactory.eINSTANCE.createFromString(XMLTypePackage.eINSTANCE.getInt(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertRatingValuesToString(EDataType eDataType, Object instanceValue)
  {
    return XMLTypeFactory.eINSTANCE.convertToString(XMLTypePackage.eINSTANCE.getInt(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Integer createRatingValuesObjectFromString(EDataType eDataType, String initialValue)
  {
    return (Integer)DbFactory.eINSTANCE.createFromString(DbPackage.eINSTANCE.getRatingValues(), initialValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertRatingValuesObjectToString(EDataType eDataType, Object instanceValue)
  {
    return DbFactory.eINSTANCE.convertToString(DbPackage.eINSTANCE.getRatingValues(), instanceValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DbPackage getDbPackage()
  {
    return (DbPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  public static DbPackage getPackage()
  {
    return DbPackage.eINSTANCE;
  }

} //DbFactoryImpl
