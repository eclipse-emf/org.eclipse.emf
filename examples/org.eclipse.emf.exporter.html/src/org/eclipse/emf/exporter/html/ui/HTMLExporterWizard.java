/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: HTMLExporterWizard.java,v 1.1 2006/03/15 21:21:30 marcelop Exp $
 */
package org.eclipse.emf.exporter.html.ui;

import org.eclipse.emf.converter.ModelConverter;
import org.eclipse.emf.exporter.html.HTMLExporterPlugin;
import org.eclipse.emf.exporter.html.HTMLExporter;
import org.eclipse.emf.exporter.ui.contribution.base.ModelExporterDirectoryURIPage;
import org.eclipse.emf.exporter.ui.contribution.base.ModelExporterOptionsPage;
import org.eclipse.emf.exporter.ui.contribution.base.ModelExporterPackagePage;
import org.eclipse.emf.exporter.ui.contribution.base.ModelExporterWizard;

/**
 * @since 2.2.0
 */
public class HTMLExporterWizard extends ModelExporterWizard
{
  public HTMLExporterWizard()
  {
    super();
  }
  
  protected ModelConverter createModelConverter()
  {
    return new HTMLExporter();
  }
  
  public void addPages()
  {
    ModelExporterDirectoryURIPage directoryURIPage = new ModelExporterDirectoryURIPage(getModelExporter(), "XSDExporterBaseLocationPage");
    directoryURIPage.setTitle(HTMLExporterPlugin.INSTANCE.getString("_UI_HTMLImport_title"));
    addPage(directoryURIPage);
    
    ModelExporterPackagePage packagePage = new ModelExporterPackagePage(getModelExporter(), "XSDExporterGenModelDetailPage");
    packagePage.setTitle(HTMLExporterPlugin.INSTANCE.getString("_UI_HTMLImport_title"));
    packagePage.setShowReferencedGenModels(true);
    addPage(packagePage);
    
    ModelExporterOptionsPage optionsPage = new ModelExporterOptionsPage(getModelExporter(), "XSDExporterOptionsPage");
    optionsPage.setTitle(HTMLExporterPlugin.INSTANCE.getString("_UI_HTMLImport_title"));
    addPage(optionsPage);
  }
}
