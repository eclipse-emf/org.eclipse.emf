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
package org.eclipse.xsd.ecore.exporter.ui;

import org.eclipse.emf.converter.ModelConverter;
import org.eclipse.emf.exporter.ui.contribution.base.ModelExporterDirectoryURIPage;
import org.eclipse.emf.exporter.ui.contribution.base.ModelExporterOptionsPage;
import org.eclipse.emf.exporter.ui.contribution.base.ModelExporterPackagePage;
import org.eclipse.emf.exporter.ui.contribution.base.ModelExporterWizard;

import org.eclipse.xsd.ecore.exporter.XSDExporter;
import org.eclipse.xsd.ecore.exporter.XSDExporterPlugin;

/**
 * @since 2.2.0
 */
public class XSDExporterWizard extends ModelExporterWizard
{
  public XSDExporterWizard()
  {
    super();
  }
  
  @Override
  protected ModelConverter createModelConverter()
  {
    return new XSDExporter();
  }
  
  @Override
  public void addPages()
  {
    ModelExporterDirectoryURIPage directoryURIPage = new ModelExporterDirectoryURIPage(getModelExporter(), "XSDExporterBaseLocationPage");
    directoryURIPage.setTitle(XSDExporterPlugin.INSTANCE.getString("_UI_XSDImport_title"));
    addPage(directoryURIPage);
    
    ModelExporterPackagePage packagePage = new ModelExporterPackagePage(getModelExporter(), "XSDExporterGenModelDetailPage");
    packagePage.setTitle(XSDExporterPlugin.INSTANCE.getString("_UI_XSDImport_title"));
    packagePage.setShowReferencedGenModels(true);
    addPage(packagePage);
    
    ModelExporterOptionsPage optionsPage = new ModelExporterOptionsPage(getModelExporter(), "XSDExporterOptionsPage");
    optionsPage.setTitle(XSDExporterPlugin.INSTANCE.getString("_UI_XSDImport_title"));
    addPage(optionsPage);
  }
}
