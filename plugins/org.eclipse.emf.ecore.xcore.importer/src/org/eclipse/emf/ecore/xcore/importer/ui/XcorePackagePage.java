/**
 * Copyright (c) 2007-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xcore.importer.ui;


import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterPackagePage;


public class XcorePackagePage extends ModelImporterPackagePage
{
  public XcorePackagePage(ModelImporter modelImporter, String pageName)
  {
    super(modelImporter, pageName);
  }

  @Override
  protected boolean supportsNestedPackages()
  {
    return true;
  }
}
