/**
 * <copyright>
 *
 * Copyright (c) 2010 Kenn Hussey and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Kenn Hussey - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: DataTypes.java,v 1.1 2010/12/05 01:42:04 khussey Exp $
 */
package org.eclipse.emf.oda.ecore.impl;

import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EcorePackage;


/**
 * Defines the data types that are supported by EMF ODA runtime driver.
 */
public final class DataTypes
{
  /**
   * Returns the display length of the specified type.
   * @param type the type
   * @return the column display length, or -1 if unknown
   * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnDisplayLength(int)
   */
  public static int getDisplayLength(EClassifier type)
  {
    // unknown
    return -1;
  }

  public static final int nullableUnknown = 0;

  public static final int noNulls = 1;

  public static final int nullable = 2;

  /**
  * Returns whether null values are allowed for the specified type.
  * @param type the type
  * @return the nullability of the type
  * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#isNullable(int)
  * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#isNullable(int)
  */
  public static int isNullable(EClassifier type)
  {
    if (type instanceof EClass)
    {
      return nullable;
    }
    else if (type instanceof EEnum)
    {
      return noNulls;
    }
    else
    {
      if (type.getEPackage() == EcorePackage.eINSTANCE)
      {
        switch (type.getClassifierID())
        {
          case EcorePackage.EBOOLEAN:
          case EcorePackage.EBYTE:
          case EcorePackage.ECHAR:
          case EcorePackage.EDOUBLE:
          case EcorePackage.EFLOAT:
          case EcorePackage.EINT:
          case EcorePackage.ELONG:
          case EcorePackage.ESHORT:
            return noNulls;
          default:
            return nullable;
        }
      }
      else
      {
        return nullableUnknown;
      }
    }
  }

  /**
   * Returns the maximum number of decimal digits of the specified type.  
   * @param type the type
   * @return the type precision, or -1 if not applicable
   * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getPrecision(int)
   * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getPrecision(int)
   */
  public static int getPrecision(EClassifier type)
  {
    // not applicable
    return -1;
  }

  /**
   * Returns the maximum number of digits to the right of the decimal 
   * point of the specified type.
   * @param type the type
   * @return the type scale, or -1 if not applicable
   * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getScale(int)
   * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getScale(int)
   */
  public static int getScale(EClassifier type)
  {
    // not applicable
    return -1;
  }

  /**
   * Returns the data provider specific code of the specified type.
   * @param type the type
   * @return the native data type code of the type
   * @throws OdaException if data source error occurs
   * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getParameterType(int)
   * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnType(int)
   */
  public static int getType(EClassifier type) throws OdaException
  {
    if (type instanceof EClass)
    {
      return EcorePackage.EJAVA_OBJECT;
    }
    else if (type instanceof EEnum)
    {
      return EcorePackage.EENUMERATOR;
    }
    else
    {
      return EcorePackage.eINSTANCE == type.getEPackage() ? type.getClassifierID() : EcorePackage.EJAVA_OBJECT;
    }
  }

  /**
   * Returns the data provider specific name of the specified type.
   * @param type the type
   * @return the native data type name of the type
   * @throws OdaException if data source error occurs
   * @see org.eclipse.datatools.connectivity.oda.IParameterMetaData#getParameterTypeName(int)
   * @see org.eclipse.datatools.connectivity.oda.IResultSetMetaData#getColumnTypeName(int)
   */
  public static String getTypeName(EClassifier type) throws OdaException
  {
    if (type instanceof EClass)
    {
      return EcorePackage.Literals.EJAVA_OBJECT.getName();
    }
    else if (type instanceof EEnum)
    {
      return EcorePackage.Literals.EENUMERATOR.getName();
    }
    else
    {
      return EcorePackage.eINSTANCE == type.getEPackage() ? type.getName() : EcorePackage.Literals.EJAVA_OBJECT.getName();
    }
  }
}