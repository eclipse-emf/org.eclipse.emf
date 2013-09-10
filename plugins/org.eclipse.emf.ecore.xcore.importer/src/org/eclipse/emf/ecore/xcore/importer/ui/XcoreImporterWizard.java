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
package org.eclipse.emf.ecore.xcore.importer.ui;


import org.eclipse.emf.converter.ModelConverter;
import org.eclipse.emf.ecore.xcore.importer.XcoreImporter;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterWizard;

import com.google.inject.Inject;
import com.google.inject.Provider;


public class XcoreImporterWizard extends ModelImporterWizard
{
  @Inject
  Provider<XcoreImporter> xcoreImporterProvider;
  
  @Override
  protected ModelConverter createModelConverter()
  {
    return xcoreImporterProvider.get();
  }

  @Override
  public void addPages()
  {
    XcoreDetailPage detailPage = new XcoreDetailPage(getModelImporter(), "XcoreModel");
    addPage(detailPage);

    XcorePackagePage packagePage = new XcorePackagePage(getModelImporter(), "XcorePackages");
    packagePage.setShowReferencedGenModels(true);
    addPage(packagePage);
  }
}