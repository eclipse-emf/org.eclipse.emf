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
package org.eclipse.emf.importer.rose.ui;

import org.eclipse.emf.importer.rose.RoseImporter;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterWizard;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterPackagePage;
import org.eclipse.emf.converter.ModelConverter;


/**
 * @since 2.1.0
 */
public class RoseImporterWizard extends ModelImporterWizard
{
  @Override
  protected ModelConverter createModelConverter()
  {
    return new RoseImporter();
  }

  @Override
  public void addPages()
  {
    RoseDetailPage detailPage = new RoseDetailPage(getModelImporter(), "RoseModel");
    addPage(detailPage);

    ModelImporterPackagePage packagePage = new ModelImporterPackagePage(getModelImporter(), "RosePackages");
    packagePage.setShowReferencedGenModels(true);
    addPage(packagePage);
  }
}
