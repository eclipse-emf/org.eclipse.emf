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
 * $Id: Driver.java,v 1.1 2010/12/05 01:42:04 khussey Exp $
 */
package org.eclipse.emf.oda.ecore.impl;

import org.eclipse.datatools.connectivity.oda.IConnection;
import org.eclipse.datatools.connectivity.oda.IDriver;
import org.eclipse.datatools.connectivity.oda.LogConfiguration;
import org.eclipse.datatools.connectivity.oda.OdaException;


/**
 * Implementation of IDriver for EMF ODA runtime driver.
 */
public class Driver implements IDriver
{
  public IConnection getConnection(String dataSourceType) throws OdaException
  {
    // only one type of data source supported
    return new Connection();
  }

  public void setLogConfiguration(LogConfiguration logConfig) throws OdaException
  {
    // do nothing; driver has no logging
  }

  public int getMaxConnections() throws OdaException
  {
    return 0; // no limit
  }

  public void setAppContext(Object context) throws OdaException
  {
    // do nothing; no support for pass-through context
  }
}