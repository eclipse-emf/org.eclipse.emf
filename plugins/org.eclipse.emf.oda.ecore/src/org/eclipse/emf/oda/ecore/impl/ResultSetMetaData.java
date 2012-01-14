/**
 * Copyright (c) 2010 Kenn Hussey and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Kenn Hussey - Initial API and implementation
 */
package org.eclipse.emf.oda.ecore.impl;

import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.oda.ecore.util.StringUtil;


/**
 * Implementation of IResultSetMetaData for EMF ODA runtime driver.
 */
public abstract class ResultSetMetaData implements IResultSetMetaData
{
  /**
   * Metadata for sets of results that contain instances of classes.
   */
  protected static class EClass extends ResultSetMetaData
  {
    protected static final String FIRST_COLUMN_NAME = "@self"; //$NON-NLS-1$

    protected final org.eclipse.emf.ecore.EClass type;

    protected EClass(org.eclipse.emf.ecore.EClass type)
    {
      super();

      this.type = type;
    }

    public int getColumnCount() throws OdaException
    {
      return type.getEAllStructuralFeatures().size() + 1;
    }

    public String getColumnName(int index) throws OdaException
    {
      if (checkBounds(index) == 1)
      {
        return FIRST_COLUMN_NAME;
      }
      else
      {
        return type.getEStructuralFeature(index - 2).getName();
      }
    }

    @Override
    public String getColumnLabel(int index) throws OdaException
    {
      return checkBounds(index) > 1 ? StringUtil.getFeatureText(type.getEStructuralFeature(index - 2)) : super.getColumnLabel(index);
    }

    @Override
    protected EClassifier getColumnClassifier(int index) throws OdaException
    {
      return checkBounds(index) == 1 ? type : type.getEStructuralFeature(index - 2).getEType();
    }
  }

  /**
   * Metadata for sets of results that contain values of data types.
   */
  protected static class EDataType extends ResultSetMetaData
  {
    protected static final String FIRST_COLUMN_NAME = "@value"; //$NON-NLS-1$

    protected final org.eclipse.emf.ecore.EDataType type;

    protected EDataType(org.eclipse.emf.ecore.EDataType type)
    {
      super();

      this.type = type;
    }

    public int getColumnCount() throws OdaException
    {
      return 1;
    }

    public String getColumnName(int index) throws OdaException
    {
      return FIRST_COLUMN_NAME;
    }

    @Override
    protected EClassifier getColumnClassifier(int index) throws OdaException
    {
      return type;
    }
  }

  /**
   * Creates result set metadata of the appropriate kind, depending on whether the specified
   * type is a class or a data type.
   * @param type the type of objects result set described by the new metadata
   * @return result set metadata of the appropriate kind
   */
  public static IResultSetMetaData create(EClassifier type)
  {
    if (type instanceof org.eclipse.emf.ecore.EClass)
    {
      return new EClass((org.eclipse.emf.ecore.EClass)type);
    }
    else
    {
      return new EDataType((org.eclipse.emf.ecore.EDataType)type);
    }
  }

  protected ResultSetMetaData()
  {
    super();
  }

  /**
   * Checks that the specified index is within the appropriate bounds.
   * @param index the 1-based index
   * @return the index
   * @throws OdaException if index is not within the bounds
   */
  protected int checkBounds(int index) throws OdaException
  {
    if (index < 1 || index > getColumnCount())
    {
      throw new OdaException(new IndexOutOfBoundsException(String.valueOf(index)));
    }

    return index;
  }

  public String getColumnLabel(int index) throws OdaException
  {
    return getColumnName(index); // default
  }

  protected abstract EClassifier getColumnClassifier(int index) throws OdaException;

  public int getColumnType(int index) throws OdaException
  {
    return DataTypes.getType(getColumnClassifier(checkBounds(index)));
  }

  public String getColumnTypeName(int index) throws OdaException
  {
    return DataTypes.getTypeName(getColumnClassifier(checkBounds(index)));
  }

  public int getColumnDisplayLength(int index) throws OdaException
  {
    return DataTypes.getDisplayLength(getColumnClassifier(checkBounds(index)));
  }

  public int getPrecision(int index) throws OdaException
  {
    return DataTypes.getPrecision(getColumnClassifier(checkBounds(index)));
  }

  public int getScale(int index) throws OdaException
  {
    return DataTypes.getScale(getColumnClassifier(checkBounds(index)));
  }

  public int isNullable(int index) throws OdaException
  {
    switch (DataTypes.isNullable(getColumnClassifier(checkBounds(index))))
    {
      case DataTypes.noNulls:
        return IResultSetMetaData.columnNoNulls;
      case DataTypes.nullable:
        return IResultSetMetaData.columnNullable;
      default:
        return IResultSetMetaData.columnNullableUnknown;
    }
  }
}