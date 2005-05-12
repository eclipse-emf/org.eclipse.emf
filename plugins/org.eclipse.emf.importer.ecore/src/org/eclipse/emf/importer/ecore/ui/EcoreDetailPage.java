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
 * $Id: EcoreDetailPage.java,v 1.3 2005/05/12 17:09:31 marcelop Exp $
 */
package org.eclipse.emf.importer.ecore.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ecore.EcoreImporterPlugin;
import org.eclipse.emf.importer.ui.contribution.base.ModelDetailPage;


/**
 * @since 2.1.0
 */
public class EcoreDetailPage extends ModelDetailPage
{
  protected Button loadButton;

  public EcoreDetailPage(ModelImporter modelImporter, String pageName)
  {
    super(modelImporter, pageName);

    setTitle(EcoreImporterPlugin.INSTANCE.getString("_UI_EcoreImport_title"));
    setDescription(EcoreImporterPlugin.INSTANCE.getString(showGenModel()
      ? "_UI_EcoreImportFile_description" : "_UI_EcoreImportNewProject_description"));
  }

  public void dispose()
  {
    if (loadButton != null)
    {
      loadButton.removeListener(SWT.Selection, this);
      loadButton = null;
    }
    super.dispose();
  }

  protected boolean singleModelLocationSelection()
  {
    return false;
  }

  protected void addControl(Composite parent)
  {
    loadButton = new Button(parent, SWT.PUSH);
    loadButton.setText(EcoreImporterPlugin.INSTANCE.getString("_UI_Load_label"));
    {
      GridData data = new GridData();
      data.horizontalSpan = 2;
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
    else
    {
      super.doHandleEvent(event);
    }
    setPageComplete(isPageComplete());
  }

  protected String[] getFilterExtensions()
  {
    return new String []{ "*.ecore;*.emof", "*.ecore", "*.emof" };
  }
}