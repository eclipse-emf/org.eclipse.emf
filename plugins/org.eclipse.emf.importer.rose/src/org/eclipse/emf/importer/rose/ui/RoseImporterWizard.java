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
 * $Id: RoseImporterWizard.java,v 1.2 2005/05/12 17:09:34 marcelop Exp $
 */
package org.eclipse.emf.importer.rose.ui;

import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.rose.RoseImporter;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterWizard;
import org.eclipse.emf.importer.ui.contribution.base.ModelPackagePage;


/**
 * @since 2.1.0
 */
public class RoseImporterWizard extends ModelImporterWizard
{
  public RoseImporterWizard()
  {
    super();
  }

  protected ModelImporter createModelImporter()
  {
    return new RoseImporter();
  }

  public void addPages()
  {
    RoseDetailPage detailPage = new RoseDetailPage(getModelImporter(), "RoseModel");
    detailPage.setShowGenModel(getModelImporter().getGenModelFileName() == null);
    addPage(detailPage);

    ModelPackagePage packagePage = new ModelPackagePage(getModelImporter(), "RosePackages");
    packagePage.setShowReferencedGenModels(true);
    addPage(packagePage);
  }
}
