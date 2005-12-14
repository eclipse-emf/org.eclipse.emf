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
 * $Id: XSDImporterWizard.java,v 1.5 2005/12/14 08:00:01 marcelop Exp $
 */
package org.eclipse.xsd.ecore.importer.ui;

import org.eclipse.emf.importer.ui.contribution.base.ModelImporterWizard;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterPackagePage;
import org.eclipse.emf.converter.ModelConverter;

import org.eclipse.xsd.ecore.importer.XSDImporter;


/**
 * @since 2.1.0
 */
public class XSDImporterWizard extends ModelImporterWizard
{
  protected ModelConverter createModelConverter()
  {
    return new XSDImporter();
  }

  public void addPages()
  {
    XSDDetailPage detailPage = new XSDDetailPage(getModelImporter(), "XSDModel");
    addPage(detailPage);

    ModelImporterPackagePage packagePage = new ModelImporterPackagePage(getModelImporter(), "XSDPackages");
    packagePage.setShowReferencedGenModels(true);
    addPage(packagePage);
  }
}