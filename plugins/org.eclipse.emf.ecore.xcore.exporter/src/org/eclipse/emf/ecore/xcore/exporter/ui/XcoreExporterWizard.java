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
package org.eclipse.emf.ecore.xcore.exporter.ui;

import org.eclipse.emf.converter.ModelConverter;
import org.eclipse.emf.ecore.xcore.exporter.XcoreExporter;
import org.eclipse.emf.ecore.xcore.exporter.XcoreExporterPlugin;
import org.eclipse.emf.exporter.ui.contribution.base.ModelExporterDirectoryURIPage;
import org.eclipse.emf.exporter.ui.contribution.base.ModelExporterOptionsPage;
import org.eclipse.emf.exporter.ui.contribution.base.ModelExporterPackagePage;
import org.eclipse.emf.exporter.ui.contribution.base.ModelExporterWizard;

import com.google.inject.Inject;
import com.google.inject.Provider;


public class XcoreExporterWizard extends ModelExporterWizard
{
  @Inject
  Provider<XcoreExporter> xcoreExporterProvider;

  public XcoreExporterWizard()
  {
    super();
  }

  @Override
  protected ModelConverter createModelConverter()
  {
    return xcoreExporterProvider.get();
  }

  @Override
  public void addPages()
  {
    ModelExporterDirectoryURIPage directoryURIPage = new ModelExporterDirectoryURIPage(getModelExporter(), "XcoreExporterBaseLocationPage");
    directoryURIPage.setTitle(XcoreExporterPlugin.INSTANCE.getString("_UI_XcoreImport_title"));
    addPage(directoryURIPage);

    ModelExporterPackagePage packagePage = new ModelExporterPackagePage(getModelExporter(), "XcoreExporterGenModelDetailPage");
    packagePage.setTitle(XcoreExporterPlugin.INSTANCE.getString("_UI_XcoreImport_title"));
    addPage(packagePage);

    ModelExporterOptionsPage optionsPage = new ModelExporterOptionsPage(getModelExporter(), "XcoreExporterOptionsPage");
    optionsPage.setTitle(XcoreExporterPlugin.INSTANCE.getString("_UI_XcoreImport_title"));
    addPage(optionsPage);
  }
}
