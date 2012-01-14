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

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IResultSet;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.emf.oda.ecore.ODAEcorePlugin;


/**
 * Implementation of IDataSetMetaData for EMF ODA runtime driver.
 */
public class DataSetMetaData implements IDataSetMetaData
{
  protected final IConnection connection;

  protected DataSetMetaData(IConnection connection)
  {
    super();

    this.connection = connection;
  }

  public IConnection getConnection() throws OdaException
  {
    return connection;
  }

  public IResultSet getDataSourceObjects(String catalog, String schema, String object, String version) throws OdaException
  {
    throw new UnsupportedOperationException();
  }

  public int getDataSourceMajorVersion() throws OdaException
  {
    return 1;
  }

  public int getDataSourceMinorVersion() throws OdaException
  {
    return 0;
  }

  public String getDataSourceProductName() throws OdaException
  {
    return ODAEcorePlugin.INSTANCE.getString("data.source.name"); //$NON-NLS-1$
  }

  public String getDataSourceProductVersion() throws OdaException
  {
    return String.valueOf(getDataSourceMajorVersion()) + '.' + String.valueOf(getDataSourceMinorVersion());
  }

  public int getSQLStateType() throws OdaException
  {
    throw new UnsupportedOperationException();
  }

  public boolean supportsMultipleResultSets() throws OdaException
  {
    return false;
  }

  public boolean supportsMultipleOpenResults() throws OdaException
  {
    return false;
  }

  public boolean supportsNamedResultSets() throws OdaException
  {
    return false;
  }

  public boolean supportsNamedParameters() throws OdaException
  {
    return true;
  }

  public boolean supportsInParameters() throws OdaException
  {
    return true;
  }

  public boolean supportsOutParameters() throws OdaException
  {
    return false;
  }

  public int getSortMode()
  {
    return IDataSetMetaData.sortModeNone;
  }
}