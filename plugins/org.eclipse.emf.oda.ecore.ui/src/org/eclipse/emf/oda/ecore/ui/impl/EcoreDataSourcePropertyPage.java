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
 * $Id: EcoreDataSourcePropertyPage.java,v 1.1 2010/12/05 01:42:01 khussey Exp $
 */
package org.eclipse.emf.oda.ecore.ui.impl;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.design.DataSourceDesign;
import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceEditorPage;
import org.eclipse.swt.widgets.Composite;


/**
 * Property page for an Ecore data source.
 */
public class EcoreDataSourcePropertyPage extends DataSourceEditorPage
{
  private EcoreDataSourcePageHelper pageHelper = null;

  public EcoreDataSourcePropertyPage()
  {
    super();

    setMessage(EcoreDataSourcePageHelper.DEFAULT_MESSAGE);
  }

  @Override
  public Properties collectCustomProperties(Properties profileProperties)
  {
    if (pageHelper == null)
    {
      return profileProperties;
    }

    return pageHelper.collectCustomProperties(profileProperties);
  }

  /**
   * Returns the data source being edited by this property page.
   */
  DataSourceDesign getCurrentDataSource()
  {
    return getEditingDataSource();
  }

  @Override
  protected void createAndInitCustomControl(Composite parent, Properties profileProperties)
  {
    if (pageHelper == null)
    {
      pageHelper = createDataSourcePageHelper();
    }

    pageHelper.createCustomControl(parent);
    pageHelper.initCustomControl(profileProperties);
  }

  /**
   * Instantiates the page helper that provides core implementation
   * of this property page.
   */
  protected EcoreDataSourcePageHelper createDataSourcePageHelper()
  {
    return new EcoreDataSourcePageHelper(this);
  }

  /**
   * Returns the page helper that provides core implementation
   * for this property page.
   */
  protected EcoreDataSourcePageHelper getPageHelper()
  {
    return pageHelper;
  }

  @Override
  protected void refresh(Properties customConnectionProperties)
  {
    if (pageHelper != null)
    {
      pageHelper.initCustomControl(customConnectionProperties);
    }

    // enable/disable all controls on page in respect of the editable session state
    enableAllControls(getControl(), isSessionEditable());
  }
}
