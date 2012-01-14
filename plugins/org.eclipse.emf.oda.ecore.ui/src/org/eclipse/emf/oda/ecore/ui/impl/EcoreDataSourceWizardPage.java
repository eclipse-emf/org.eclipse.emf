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
package org.eclipse.emf.oda.ecore.ui.impl;

import java.util.Properties;

import org.eclipse.datatools.connectivity.oda.design.ui.wizards.DataSourceWizardPage;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.oda.ecore.ui.ODAEcoreUIPlugin;
import org.eclipse.swt.widgets.Composite;


/**
 * Wizard page for an Ecore data source.
 */
public class EcoreDataSourceWizardPage extends DataSourceWizardPage
{
  protected EcoreDataSourcePageHelper pageHelper = null;
  protected Properties properties = null;

  public EcoreDataSourceWizardPage(String pageName)
  {
    super(pageName);
    setMessage(EcoreDataSourcePageHelper.DEFAULT_MESSAGE);
    setImageDescriptor(ExtendedImageRegistry.INSTANCE.getImageDescriptor(ODAEcoreUIPlugin.INSTANCE.getImage("full/wizban/NewEcore"))); //$NON-NLS-1$
  }

  @Override
  public void createPageCustomControl(Composite parent)
  {
    if (pageHelper == null)
    {
      pageHelper = new EcoreDataSourcePageHelper(this);
    }

    pageHelper.createCustomControl(parent);
    pageHelper.initCustomControl(properties); // in case init was called before create 
  }

  @Override
  public void setInitialProperties(Properties dataSourceProperties)
  {
    properties = dataSourceProperties;

    if (pageHelper == null)
    {
      return; // ignore, wait until createPageCustomControl to initialize
    }

    pageHelper.initCustomControl(properties);
  }

  @Override
  public Properties collectCustomProperties()
  {
    if (pageHelper != null)
    {
      return pageHelper.collectCustomProperties(properties);
    }

    return (properties != null) ? properties : new Properties();
  }
}
