/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: EcoreImporterWizard.java,v 1.5 2005/06/09 14:01:02 davidms Exp $
 */
package org.eclipse.emf.importer.ecore.ui;

import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ecore.EcoreImporter;
import org.eclipse.emf.importer.ecore.EcoreImporterPlugin;
import org.eclipse.emf.importer.ui.contribution.base.ModelDetailPage;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterWizard;
import org.eclipse.emf.importer.ui.contribution.base.ModelPackagePage;


/**
 * @since 2.1.0
 */
public class EcoreImporterWizard extends ModelImporterWizard
{
  protected ModelImporter createModelImporter()
  {
    return new EcoreImporter();
  }

  public void addPages()
  {
    ModelDetailPage detailPage = new ModelDetailPage(getModelImporter(), "EcoreModel");
    detailPage.setShowGenModel(getModelImporter().getGenModelFileName() == null);
    detailPage.setTitle(EcoreImporterPlugin.INSTANCE.getString("_UI_EcoreImport_title"));
    detailPage.setDescription(EcoreImporterPlugin.INSTANCE.getString(detailPage.showGenModel() ?
      "_UI_EcoreImportNewProject_description" : "_UI_EcoreImportFile_description"));    
    addPage(detailPage);

    ModelPackagePage packagePage = new ModelPackagePage(getModelImporter(), "EcorePackages");
    packagePage.setShowReferencedGenModels(true);
    addPage(packagePage);
  }
}