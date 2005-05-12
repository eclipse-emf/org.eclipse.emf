/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDDetailPage.java,v 1.3 2005/05/12 17:09:32 marcelop Exp $
 */
package org.eclipse.xsd.ecore.importer.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ui.contribution.base.ModelDetailPage;

import org.eclipse.xsd.ecore.importer.XSDImporter;
import org.eclipse.xsd.ecore.importer.XSDImporterPlugin;


/**
 * @since 2.1.0
 */
public class XSDDetailPage extends ModelDetailPage
{
  protected Button createMapButton;
  protected Button loadButton;

  public XSDDetailPage(ModelImporter modelImporter, String pageName)
  {
    super(modelImporter, pageName);

    setTitle(XSDImporterPlugin.INSTANCE.getString("_UI_XSDImport_title"));
    setDescription(XSDImporterPlugin.INSTANCE.getString(showGenModel()
      ? "_UI_XSDImportFile_description" : "_UI_XSDImportNewProject_description"));
  }

  public void dispose()
  {
    if (loadButton != null)
    {
      loadButton.removeListener(SWT.Selection, this);
      loadButton = null;
    }
    if (createMapButton != null)
    {
      createMapButton.removeListener(SWT.Selection, this);
      createMapButton = null;
    }

    super.dispose();
  }

  public XSDImporter getXSDImporter()
  {
    return (XSDImporter)getModelImporter();
  }

  protected boolean singleModelLocationSelection()
  {
    return false;
  }

  protected void addControl(Composite parent)
  {
    if (getXSDImporter().canCreateEcoreMap())
    {
      createMapButton = new Button(parent, SWT.CHECK);
      createMapButton.setText(XSDImporterPlugin.INSTANCE.getString("_UI_Create_XML_Schema_to_Ecore_Map"));
      {
        GridData data = new GridData();
        data.horizontalSpan = 1;
        createMapButton.setLayoutData(data);
      }
      createMapButton.addListener(SWT.Selection, this);
    }

    loadButton = new Button(parent, SWT.PUSH);
    loadButton.setText(XSDImporterPlugin.INSTANCE.getString("_UI_Load_label"));
    {
      GridData data = new GridData();
      data.horizontalSpan = getXSDImporter().canCreateEcoreMap() ? 1 : 2;
      data.horizontalAlignment = GridData.END;
      loadButton.setLayoutData(data);
    }
    loadButton.addListener(SWT.Selection, this);
  }

  protected void doHandleEvent(Event event)
  {
    if (event.type == SWT.Selection && event.widget == loadButton)
    {
      refreshModel();
    }
    else if (event.type == SWT.Selection && event.widget == createMapButton)
    {
      getXSDImporter().setCreateEcoreMap(createMapButton.getSelection());
      if (modelLocationText.getText().trim().length() > 0)
      {
        refreshModel();
      }
    }
    else
    {
      super.doHandleEvent(event);
    }
    setPageComplete(isPageComplete());
  }

  protected String[] getFilterExtensions()
  {
    return new String []{ "*.xsd;*.wsdl", "*.xsd", "*.wsdl" };
  }
}