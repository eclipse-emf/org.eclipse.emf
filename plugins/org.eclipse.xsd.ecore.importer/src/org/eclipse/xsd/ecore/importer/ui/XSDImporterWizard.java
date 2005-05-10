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
 * $Id: XSDImporterWizard.java,v 1.1 2005/05/10 17:41:37 davidms Exp $
 */
package org.eclipse.xsd.ecore.importer.ui;

import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ui.wizard.base.ModelImporterWizard;
import org.eclipse.emf.importer.ui.wizard.base.ModelPackagePage;

import org.eclipse.xsd.ecore.importer.XSDImporter;


/**
 * @since 2.1.0
 */
public class XSDImporterWizard extends ModelImporterWizard
{
  protected ModelImporter createModelImporter()
  {
    return new XSDImporter();
  }

  public void addPages()
  {
    XSDDetailPage detailPage = new XSDDetailPage(getModelImporter(), "XSDModel");
    detailPage.setShowGenModel(getModelImporter().getGenModelFileName() == null);
    addPage(detailPage);

    ModelPackagePage packagePage = new ModelPackagePage(getModelImporter(), "XSDPackages");
    packagePage.setShowReferencedGenModels(true);
    addPage(packagePage);
  }
}