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

import java.util.Map;
import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDataSetMetaData;
import org.eclipse.datatools.connectivity.oda.IQuery;
import org.eclipse.datatools.connectivity.oda.OdaException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.oda.ecore.util.StringUtil;

import com.ibm.icu.util.ULocale;


/**
 * Implementation of IConnection for EMF ODA runtime driver.
 */
public class Connection implements IConnection
{
  public static final String RESOURCE_PROPERTY_NAME = "resource"; //$NON-NLS-1$

  protected Map< ? , ? > appContext = null;
  protected ResourceSet resourceSet = null;

  public void open(Properties connProperties) throws OdaException
  {
    if (appContext != null)
    {
      try
      {
        resourceSet = (ResourceSet)appContext.get(ResourceSet.class.getName());
      }
      catch (Exception e)
      {
        throw new OdaException(e);
      }
    }

    if (resourceSet == null)
    {
      resourceSet = new ResourceSetImpl();

      String uri = connProperties.getProperty(RESOURCE_PROPERTY_NAME);

      if (!StringUtil.isEmpty(uri))
      {
        try
        {
          resourceSet.getResource(URI.createURI(uri), true);
        }
        catch (Exception e)
        {
          throw new OdaException(e);
        }
      }
    }
  }

  /**
   * Returns the resource set for this connection.
   * @return the resource set
   */
  public ResourceSet getResourceSet()
  {
    return resourceSet;
  }

  public void setAppContext(Object context) throws OdaException
  {
    if (context == null && appContext != null)
    {
      if (appContext.containsKey(ResourceSet.class.getName()))
      {
        resourceSet = null;
      }

      appContext = null;
    }
    else if (context instanceof Map< ? , ? >)
    {
      appContext = (Map< ? , ? >)context;
    }
  }

  public void close() throws OdaException
  {
    if (isOpen())
    {
      for (Resource resource : resourceSet.getResources())
      {
        resource.unload();
      }

      resourceSet = null;
    }
  }

  public boolean isOpen() throws OdaException
  {
    return resourceSet != null;
  }

  public IDataSetMetaData getMetaData(String dataSetType) throws OdaException
  {
    // only one type of data set supported
    return new DataSetMetaData(this);
  }

  public IQuery newQuery(String dataSetType) throws OdaException
  {
    if (!isOpen())
    {
      throw new OdaException(new IllegalStateException());
    }

    // only one type of data set supported
    return new Query(this);
  }

  public int getMaxQueries() throws OdaException
  {
    return 0; // no limit
  }

  public void commit() throws OdaException
  {
    // do nothing; no transaction support provided
  }

  public void rollback() throws OdaException
  {
    // do nothing; no transaction support provided
  }

  public void setLocale(ULocale locale) throws OdaException
  {
    // do nothing; no locale support provided
  }
}