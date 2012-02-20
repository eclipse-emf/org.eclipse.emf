/**
 * Copyright (c) 2005-2012 IBM Corporation and others
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xcore.importer.ui;


import org.eclipse.emf.ecore.xcore.importer.XcoreImporter;
import org.eclipse.emf.ecore.xcore.importer.XcoreImporterPlugin;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterDetailPage;
import org.eclipse.swt.widgets.Composite;


/**
 */
public class XcoreDetailPage extends ModelImporterDetailPage
{
  public XcoreDetailPage(ModelImporter modelImporter, String pageName)
  {
    super(modelImporter, pageName);

    setTitle(XcoreImporterPlugin.INSTANCE.getString("_UI_XcoreImport_title"));
    setDescription(XcoreImporterPlugin.INSTANCE.getString(showGenModel() ? "_UI_XcoreImportNewProject_description": "_UI_XcoreImportFile_description" ));
  }

  @Override
  protected void createURIControl(Composite parent)
  {
    // We can't handle with Xcore resources that aren't in a Java project.
    //
    super.createURIControl(parent);
    browseFileSystemButton.setVisible(false);
  }

  public XcoreImporter getXcoreImporter()
  {
    return (XcoreImporter)getModelImporter();
  }
}