/**
 * <copyright>
 *
 * Copyright (c) 2010-2011 Kenn Hussey and others.
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
 * $Id: ResultSet.java,v 1.3 2011/09/01 02:07:34 khussey Exp $
 */
package org.eclipse.emf.oda.ecore.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import org.eclipse.datatools.connectivity.oda.IBlob;
import org.eclipse.datatools.connectivity.oda.IClob;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.IResultSetMetaData;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.oda.ecore.util.StringUtil;


/**
 * Implementation of IResultSet for EMF ODA runtime driver.
 */
public abstract class ResultSet implements IResultSet
{
  /**
   * An implementation of IBlog for byte arrays.
   */
  protected static class Blob implements IBlob
  {
    protected final byte[] data;

    protected Blob(byte[] data)
    {
      this.data = data;
    }

    public InputStream getBinaryStream() throws OdaException
    {
      return new ByteArrayInputStream(data);
    }

    public byte[] getBytes(long position, int length) throws OdaException
    {
      // let ODA consumer helper framework provide default implementation
      throw new UnsupportedOperationException();
    }

    public long length() throws OdaException
    {
      return data.length;
    }
  }

  /**
   * An implementation of IClob for strings.
   */
  protected static class Clob implements IClob
  {
    protected final String data;

    protected Clob(String data)
    {
      this.data = data;
    }

    public Reader getCharacterStream() throws OdaException
    {
      return new StringReader(data);
    }

    public String getSubString(long position, int length) throws OdaException
    {
      // let ODA consumer helper framework provide default implementation
      throw new UnsupportedOperationException();
    }

    public long length() throws OdaException
    {
      return data.length();
    }
  }

  /**
   * A wrapper for Java objects which works around the fact that EMF objects are not (Java)
   * serializable and which provides a formatted text representation.
   */
  public static class JavaObject implements Serializable
  {
    private static final long serialVersionUID = 1L;

    protected transient Object object = null;

    public JavaObject(Object object)
    {
      super();

      this.object = object;
    }

    public Object getObject()
    {
      return object;
    }

    public String toString()
    {
      return StringUtil.getText(object);
    }
  }

  /**
   * A set of results that contains instances of classes.
   */
  protected static class EClass extends ResultSet
  {
    protected final ResultSetMetaData.EClass metaData;

    protected EClass(org.eclipse.emf.ecore.EClass type, EList< ? > results)
    {
      super(results);

      metaData = new ResultSetMetaData.EClass(type);
    }

    public IResultSetMetaData getMetaData() throws OdaException
    {
      return metaData;
    }

    public int findColumn(String columnName) throws OdaException
    {
      if (ResultSetMetaData.EClass.FIRST_COLUMN_NAME.equals(columnName))
      {
        return 1;
      }
      else
      {
        EStructuralFeature feature = metaData.type.getEStructuralFeature(columnName);

        if (feature == null)
        {
          throw new OdaException(new IllegalArgumentException(String.valueOf(columnName)));
        }

        return metaData.type.getFeatureID(feature) + 1;
      }
    }

    @Override
    protected Object getValue(int index) throws OdaException
    {
      if (cursor < 1)
      {
        throw new OdaException(new IllegalStateException());
      }

      EObject self = (EObject)results.get(cursor - 1);

      if (index == 1)
      {
        wasNull = false;
        return self;
      }
      else
      {
        Object value = self.eGet(metaData.type.getEStructuralFeature(index - 2));
        wasNull = value == null;
        return value;
      }
    }
  }

  /**
   * A set of results that contains values of data types.
   */
  protected static class EDataType extends ResultSet
  {
    protected final ResultSetMetaData.EDataType metaData;

    protected EDataType(org.eclipse.emf.ecore.EDataType type, EList< ? > results)
    {
      super(results);

      metaData = new ResultSetMetaData.EDataType(type);
    }

    public IResultSetMetaData getMetaData() throws OdaException
    {
      return metaData;
    }

    public int findColumn(String columnName) throws OdaException
    {
      if (ResultSetMetaData.EDataType.FIRST_COLUMN_NAME.equals(columnName))
      {
        return 1;
      }
      else
      {
        throw new OdaException(new IllegalArgumentException(String.valueOf(columnName)));
      }
    }

    @Override
    protected Object getValue(int index) throws OdaException
    {
      if (cursor < 1)
      {
        throw new OdaException(new IllegalStateException());
      }

      Object value = results.get(cursor - 1);
      wasNull = value == null;
      return value;
    }
  }

  protected final EList< ? > results;
  protected int maxRows = 0;

  protected int cursor = 0;
  protected boolean wasNull = false;

  /**
   * Creates a result set of the appropriate kind, depending on whether the specified
   * list of results contains instances of classes or values of data types.
   * @param type the type of objects in the list of results
   * @param results the list of results from executing a query
   * @return a result set of the appropriate kind
   */
  public static IResultSet create(EClassifier type, EList< ? > results)
  {
    if (type instanceof org.eclipse.emf.ecore.EClass)
    {
      return new EClass((org.eclipse.emf.ecore.EClass)type, results);
    }
    else
    {
      return new EDataType((org.eclipse.emf.ecore.EDataType)type, results);
    }
  }

  protected ResultSet(EList< ? > results)
  {
    super();

    this.results = results;
  }

  public void setMaxRows(int max) throws OdaException
  {
    if (maxRows < 0 || maxRows > results.size())
    {
      throw new OdaException(new IllegalArgumentException(String.valueOf(max)));
    }

    maxRows = max;
  }

  public boolean next() throws OdaException
  {
    if (cursor < results.size() && (maxRows == 0 || cursor < maxRows))
    {
      cursor++;
      return true;
    }

    return false;
  }

  public void close() throws OdaException
  {
    cursor = 0; // reset row counter
  }

  public int getRow() throws OdaException
  {
    return cursor;
  }

  /**
   * Returns the value (of the feature) at the specified index.
   * @param index the 1-based index of the desired value
   * @return the value
   * @throws OdaException if data source error occurs
   */
  protected abstract Object getValue(int index) throws OdaException;

  public String getString(int index) throws OdaException
  {
    switch (getMetaData().getColumnType(index))
    {
      default:
        return String.valueOf(getValue(index));
    }
  }

  public String getString(String columnName) throws OdaException
  {
    return getString(findColumn(columnName));
  }

  public int getInt(int index) throws OdaException
  {
    switch (getMetaData().getColumnType(index))
    {
      case EcorePackage.EBYTE:
      case EcorePackage.EBYTE_OBJECT:
        return new Integer((Byte)getValue(index));
      case EcorePackage.ECHAR:
      case EcorePackage.ECHARACTER_OBJECT:
        return new Integer((Character)getValue(index));
      case EcorePackage.EINT:
      case EcorePackage.EINTEGER_OBJECT:
        return (Integer)getValue(index);
      case EcorePackage.ESHORT:
      case EcorePackage.ESHORT_OBJECT:
        return new Integer((Short)getValue(index));
      case EcorePackage.ELONG:
      case EcorePackage.ELONG_OBJECT:
        return new Integer(((Long)getValue(index)).intValue());
      default:
        throw new OdaException(new IllegalArgumentException(String.valueOf(index)));
    }
  }

  public int getInt(String columnName) throws OdaException
  {
    return getInt(findColumn(columnName));
  }

  public double getDouble(int index) throws OdaException
  {
    switch (getMetaData().getColumnType(index))
    {
      case EcorePackage.EDOUBLE:
      case EcorePackage.EDOUBLE_OBJECT:
        return (Double)getValue(index);
      case EcorePackage.EFLOAT:
      case EcorePackage.EFLOAT_OBJECT:
        return new Double((Float)getValue(index));
      default:
        throw new OdaException(new IllegalArgumentException(String.valueOf(index)));
    }
  }

  public double getDouble(String columnName) throws OdaException
  {
    return getDouble(findColumn(columnName));
  }

  public BigDecimal getBigDecimal(int index) throws OdaException
  {
    switch (getMetaData().getColumnType(index))
    {
      case EcorePackage.EBIG_DECIMAL:
        return (BigDecimal)getValue(index);
      case EcorePackage.EBIG_INTEGER:
        return new BigDecimal((BigInteger)getValue(index));
      default:
        throw new OdaException(new IllegalArgumentException(String.valueOf(index)));
    }
  }

  public BigDecimal getBigDecimal(String columnName) throws OdaException
  {
    return getBigDecimal(findColumn(columnName));
  }

  public Date getDate(int index) throws OdaException
  {
    switch (getMetaData().getColumnType(index))
    {
      case EcorePackage.EDATE:
        return new Date(((java.util.Date)getValue(index)).getTime());
      default:
        throw new OdaException(new IllegalArgumentException(String.valueOf(index)));
    }
  }

  public Date getDate(String columnName) throws OdaException
  {
    return getDate(findColumn(columnName));
  }

  public Time getTime(int index) throws OdaException
  {
    switch (getMetaData().getColumnType(index))
    {
      case EcorePackage.EDATE:
        return new Time(((java.util.Date)getValue(index)).getTime());
      default:
        throw new OdaException(new IllegalArgumentException(String.valueOf(index)));
    }
  }

  public Time getTime(String columnName) throws OdaException
  {
    return getTime(findColumn(columnName));
  }

  public Timestamp getTimestamp(int index) throws OdaException
  {
    switch (getMetaData().getColumnType(index))
    {
      case EcorePackage.EDATE:
        return new Timestamp(((java.util.Date)getValue(index)).getTime());
      default:
        throw new OdaException(new IllegalArgumentException(String.valueOf(index)));
    }
  }

  public Timestamp getTimestamp(String columnName) throws OdaException
  {
    return getTimestamp(findColumn(columnName));
  }

  public IBlob getBlob(int index) throws OdaException
  {
    switch (getMetaData().getColumnType(index))
    {
      case EcorePackage.EBYTE_ARRAY:
        return new Blob((byte[])getValue(index));
      default:
        throw new OdaException(new IllegalArgumentException(String.valueOf(index)));
    }
  }

  public IBlob getBlob(String columnName) throws OdaException
  {
    return getBlob(findColumn(columnName));
  }

  public IClob getClob(int index) throws OdaException
  {
    switch (getMetaData().getColumnType(index))
    {
      case EcorePackage.ESTRING:
        return new Clob((String)getValue(index));
      default:
        throw new OdaException(new IllegalArgumentException(String.valueOf(index)));
    }
  }

  public IClob getClob(String columnName) throws OdaException
  {
    return getClob(findColumn(columnName));
  }

  public boolean getBoolean(int index) throws OdaException
  {
    switch (getMetaData().getColumnType(index))
    {
      case EcorePackage.EBOOLEAN:
      case EcorePackage.EBOOLEAN_OBJECT:
        return (Boolean)getValue(index);
      default:
        throw new OdaException(new IllegalArgumentException(String.valueOf(index)));
    }
  }

  public boolean getBoolean(String columnName) throws OdaException
  {
    return getBoolean(findColumn(columnName));
  }

  public Object getObject(int index) throws OdaException
  {
    switch (getMetaData().getColumnType(index))
    {
      case EcorePackage.EJAVA_OBJECT:
        return new JavaObject(getValue(index));
      default:
        return getValue(index);
    }
  }

  public Object getObject(String columnName) throws OdaException
  {
    return getObject(findColumn(columnName));
  }

  public boolean wasNull() throws OdaException
  {
    return wasNull;
  }
}