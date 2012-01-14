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
package org.eclipse.emf.importer.ecore.ui;

import org.eclipse.emf.importer.ecore.EcoreImporter;
import org.eclipse.emf.importer.ecore.EcoreImporterPlugin;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterDetailPage;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterWizard;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterPackagePage;
import org.eclipse.emf.converter.ModelConverter;


/**
 * @since 2.1.0
 */
public class EcoreImporterWizard extends ModelImporterWizard
{
  @Override
  protected ModelConverter createModelConverter()
  {
    return new EcoreImporter();
  }

  @Override
  public void addPages()
  {
    ModelImporterDetailPage detailPage = new ModelImporterDetailPage(getModelImporter(), "EcoreModel");
    detailPage.setTitle(EcoreImporterPlugin.INSTANCE.getString("_UI_EcoreImport_title"));
    detailPage.setDescription(EcoreImporterPlugin.INSTANCE.getString(detailPage.showGenModel() ?
      "_UI_EcoreImportNewProject_description" : "_UI_EcoreImportFile_description"));    
    addPage(detailPage);

    ModelImporterPackagePage packagePage = new ModelImporterPackagePage(getModelImporter(), "EcorePackages");
    packagePage.setShowReferencedGenModels(true);
    addPage(packagePage);
  }
}