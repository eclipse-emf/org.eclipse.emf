/**
 * Copyright (c) 2005-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.xsd.ecore.importer.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterDetailPage;
import org.eclipse.jface.dialogs.MessageDialog;

import org.eclipse.xsd.ecore.importer.XSDImporter;
import org.eclipse.xsd.ecore.importer.XSDImporterPlugin;


/**
 * @since 2.1.0
 */
public class XSDDetailPage extends ModelImporterDetailPage
{
  protected Button createMapButton;

  /**
   * @since 2.9
   */
  protected Button sortAttributesButton;

  public XSDDetailPage(ModelImporter modelImporter, String pageName)
  {
    super(modelImporter, pageName);

    setTitle(XSDImporterPlugin.INSTANCE.getString("_UI_XSDImport_title"));
    setDescription(XSDImporterPlugin.INSTANCE.getString(showGenModel() ?
      "_UI_XSDImportNewProject_description": "_UI_XSDImportFile_description" ));
  }

  @Override
  public void dispose()
  {
    if (createMapButton != null)
    {
      createMapButton.removeListener(SWT.Selection, this);
      createMapButton = null;
    }
    if (sortAttributesButton != null)
    {
      sortAttributesButton.removeListener(SWT.Selection, this);
      sortAttributesButton = null;
    }

    super.dispose();
  }

  public XSDImporter getXSDImporter()
  {
    return (XSDImporter)getModelImporter();
  }

  @Override
  protected void addDetailControl(Composite parent)
  {
    XSDImporter xsdImporter = getXSDImporter();
    if (xsdImporter.canCreateEcoreMap())
    {
      createMapButton = new Button(parent, SWT.CHECK);
      if (xsdImporter.createEcoreMap())
      {
        createMapButton.setSelection(true);
      }
      createMapButton.setText(XSDImporterPlugin.INSTANCE.getString("_UI_Create_XML_Schema_to_Ecore_Map"));
      {
        GridData data = new GridData();
        data.horizontalSpan = 1;
        createMapButton.setLayoutData(data);
      }
      createMapButton.addListener(SWT.Selection, this);
    }

    sortAttributesButton = new Button(parent, SWT.CHECK);
    if (xsdImporter.sortAttributes())
    {
      sortAttributesButton.setSelection(true);
    }
    sortAttributesButton.setText(XSDImporterPlugin.INSTANCE.getString("_UI_Sort_Attributes"));
    {
      GridData data = new GridData();
      data.horizontalSpan = 1;
      sortAttributesButton.setLayoutData(data);
    }
    sortAttributesButton.addListener(SWT.Selection, this);
  }

  @Override
  protected void doHandleEvent(Event event)
  {
    if (event.type == SWT.Selection && (event.widget == createMapButton || event.widget == sortAttributesButton))
    {
      if (event.widget == createMapButton)
      {
        boolean enabled = createMapButton.getSelection();
        if (enabled)
        {
          MessageDialog.openWarning(getContainer().getShell(), XSDImporterPlugin.INSTANCE.getString("_UI_Create_XML_Schema_to_Ecore_Map").replace("&", ""), XSDImporterPlugin.INSTANCE.getString("_UI_Create_XML_Schema_to_Ecore_Map_message"));
        }
        getXSDImporter().setCreateEcoreMap(createMapButton.getSelection());
      }
      else
      {
        getXSDImporter().setSortAttributes(sortAttributesButton.getSelection());
      }
      if (uriText.getText().trim().length() > 0)
      {
        refreshModel();
        getContainer().updateButtons();
      }
    }
    else
    {
      super.doHandleEvent(event);
    }
  }
}