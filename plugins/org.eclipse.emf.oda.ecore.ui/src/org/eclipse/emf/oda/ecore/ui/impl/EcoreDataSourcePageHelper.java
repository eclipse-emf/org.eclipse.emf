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
 * $Id: EcoreDataSourcePageHelper.java,v 1.1 2010/12/05 01:42:01 khussey Exp $
 */
package org.eclipse.emf.oda.ecore.ui.impl;

import java.util.List;
import java.util.Properties;

import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.oda.ecore.impl.Connection;
import org.eclipse.emf.oda.ecore.ui.ODAEcoreUIPlugin;
import org.eclipse.emf.oda.ecore.util.StringUtil;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


/**
 * A helper for Ecore data source property and wizard pages.
 */
public class EcoreDataSourcePageHelper
{
  protected static String DEFAULT_MESSAGE = ODAEcoreUIPlugin.INSTANCE.getString("_UI_DefaultDataSource_message"); //$NON-NLS-1$

  protected EcoreDataSourceWizardPage wizardPage = null;
  protected EcoreDataSourcePropertyPage propertyPage = null;

  protected Text resourceField = null;

  protected EcoreDataSourcePageHelper(EcoreDataSourceWizardPage page)
  {
    super();

    assert (page != null);

    wizardPage = page;
  }

  protected EcoreDataSourcePageHelper(EcoreDataSourcePropertyPage page)
  {
    super();

    assert (page != null);

    propertyPage = page;
  }

  /**
   * Returns the value of the resource property.
   * @return the resource
   */
  protected String getResource()
  {
    return resourceField == null ? null : resourceField.getText();
  }

  /**
   * Creates custom page control for user-defined data sources.
   */
  protected void createCustomControl(Composite parent)
  {
    Composite composite = new Composite(parent, SWT.NONE);

    GridLayoutFactory.fillDefaults().applyTo(composite);

    createResourceControl(composite);
  }

  /**
   * Creates control for resource property.
   */
  protected void createResourceControl(Composite parent)
  {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayout(new GridLayout(3, false));

    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(composite);

    Label resourceLabel = new Label(composite, SWT.NONE);
    resourceLabel.setText(ODAEcoreUIPlugin.INSTANCE.getString("_UI_Resource_label")); //$NON-NLS-1$

    resourceField = new Text(composite, SWT.BORDER);
    resourceField.addModifyListener(new ModifyListener()
      {
        public void modifyText(ModifyEvent me)
        {
          validateData();
        }
      });

    GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(resourceField);

    Button resourceButton = new Button(composite, SWT.PUSH);
    resourceButton.setText(ODAEcoreUIPlugin.INSTANCE.getString("_UI_Select_label")); //$NON-NLS-1$
    resourceButton.addSelectionListener(new SelectionAdapter()
      {
        @Override
        public void widgetSelected(SelectionEvent se)
        {
          ResourceDialog dialog = new ResourceDialog(getControl().getShell(), null, SWT.SINGLE);

          if (dialog.open() == Window.OK)
          {
            List<URI> uris = dialog.getURIs();

            if (uris.size() > 0)
            {
              resourceField.setText(uris.get(0).toString());
            }
          }
        }
      });
  }

  /**
   * Initializes the custom control with the specified properties.
   */
  protected void initCustomControl(Properties properties)
  {
    if (properties == null)
    {
      return; // nothing to initialize
    }

    String resource = properties.getProperty(Connection.RESOURCE_PROPERTY_NAME);

    if (!StringUtil.isEmpty(resource))
    {
      // initialize context type
      resourceField.setText(resource);
    }

    validateData();
  }

  /**
   * Collects the values of the specified properties.
   */
  protected Properties collectCustomProperties(Properties properties)
  {
    if (properties == null)
    {
      properties = new Properties();
    }

    String resource = getResource();

    if (!StringUtil.isEmpty(resource))
    {
      properties.setProperty(Connection.RESOURCE_PROPERTY_NAME, resource);
    }

    return properties;
  }

  /**
   * Validates that the user has specified values for the properties in the page control
   * and sets page message accordingly.
   */
  protected void validateData()
  {
    String resource = getResource();
    boolean isValid = !StringUtil.isEmpty(resource);

    if (isValid)
    {
      try
      {
        URI.createURI(resource);

        setMessage(DEFAULT_MESSAGE, IMessageProvider.NONE);
      }
      catch (Exception e)
      {
        setMessage(e.getLocalizedMessage(), IMessageProvider.ERROR);
      }
    }
    else
    {
      setMessage(ODAEcoreUIPlugin.INSTANCE.getString("_UI_ResourceCannotBeEmpty_message"), IMessageProvider.ERROR); //$NON-NLS-1$
    }

    setPageComplete(isValid);
  }

  /**
   * Returns the page for this helper.
   */
  protected Control getControl()
  {
    if (wizardPage != null)
    {
      return wizardPage.getControl();
    }
    else if (propertyPage != null)
    {
      return propertyPage.getControl();
    }

    return null;
  }

  /**
   * Sets whether the page for this helps is complete.
   */
  protected void setPageComplete(boolean complete)
  {
    if (wizardPage != null)
    {
      wizardPage.setPageComplete(complete);
    }
    else if (propertyPage != null)
    {
      propertyPage.setValid(complete);
    }
  }

  /**
   * Sets the message for this helper's page.
   */
  protected void setMessage(String newMessage, int newType)
  {
    if (wizardPage != null)
    {
      wizardPage.setMessage(newMessage, newType);
    }
    else if (propertyPage != null)
    {
      propertyPage.setMessage(newMessage, newType);
    }
  }
}