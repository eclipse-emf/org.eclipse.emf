/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */

package org.eclipse.emf.exporter.ui.contribution.base;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

import org.eclipse.emf.converter.ui.contribution.base.ModelConverterPage;
import org.eclipse.emf.exporter.ExporterPlugin;
import org.eclipse.emf.exporter.ModelExporter;

/**
 * @since 2.2.0
 */
public class ModelExporterOptionsPage extends ModelConverterPage implements IModelExporterPage
{
  protected Button saveEPackageURIButton;
  protected Button saveExporterButton;

  public ModelExporterOptionsPage(ModelExporter modelExporter, String pageName)
  {
    super(modelExporter, pageName);
    setPageComplete(true);
    setMessage(ExporterPlugin.getPlugin().getString("_UI_SaveWizardSettings_description"));
  }

  public ModelExporter getModelExporter()
  {
    return (ModelExporter)getModelConverter();
  }

  public void createControl(Composite parent)
  {
    Composite composite = new Composite(parent, SWT.NONE);
    GridLayout layout = new GridLayout();
    layout.verticalSpacing = 12;
    composite.setLayout(layout);
    composite.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL));

    createSaveSettingsControl(composite);
    
    setControl(composite);
  }
  
  protected void createSaveSettingsControl(Composite parent)
  {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    {
      GridLayout layout = new GridLayout();
      layout.marginLeft = -5;
      layout.marginRight = -5;
      layout.marginTop = -5;
      layout.marginBottom = -5;
      composite.setLayout(layout);
    }
    
    saveEPackageURIButton = new Button(composite, SWT.CHECK);
    saveEPackageURIButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    saveEPackageURIButton.setText(ExporterPlugin.INSTANCE.getString("_UI_SavePackageURI_label"));
    saveEPackageURIButton.addListener(SWT.Selection, this);
    
    saveExporterButton = new Button(composite, SWT.CHECK);
    saveExporterButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    saveExporterButton.setText(ExporterPlugin.INSTANCE.getString("_UI_SaveWizardSettings_label"));
    saveExporterButton.addListener(SWT.Selection, this);
  }
  
  @Override
  protected void pageActivated(boolean firstTime, int cause)
  {
    if (firstTime)
    {
      saveEPackageURIButton.setSelection(getModelExporter().isSaveEPackageArtifactURI());
      saveExporterButton.setSelection(getModelExporter().isSaveExporter());
    }
  }

  @Override
  protected void doHandleEvent(Event event)
  {
    if (event.type == SWT.Selection)
    {
      if (event.widget == saveEPackageURIButton) getModelExporter().setSaveEPackageArtifactURI(saveEPackageURIButton.getSelection());
      if (event.widget == saveExporterButton) getModelExporter().setSaveExporter(saveExporterButton.getSelection());
    }
  }
}
