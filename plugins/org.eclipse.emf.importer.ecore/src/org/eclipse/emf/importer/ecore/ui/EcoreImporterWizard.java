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
 * $Id: EcoreImporterWizard.java,v 1.1 2005/05/10 17:37:32 davidms Exp $
 */
package org.eclipse.emf.importer.ecore.ui;

import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ecore.EcoreImporter;
import org.eclipse.emf.importer.ui.wizard.base.ModelImporterWizard;
import org.eclipse.emf.importer.ui.wizard.base.ModelPackagePage;


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
    EcoreDetailPage detailPage = new EcoreDetailPage(getModelImporter(), "EcoreModel");
    detailPage.setShowGenModel(getModelImporter().getGenModelFileName() == null);
    addPage(detailPage);

    ModelPackagePage packagePage = new ModelPackagePage(getModelImporter(), "EcorePackages");
    packagePage.setShowReferencedGenModels(true);
    addPage(packagePage);
  }
}