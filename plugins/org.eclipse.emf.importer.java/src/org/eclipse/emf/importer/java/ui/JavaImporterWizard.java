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
package org.eclipse.emf.importer.java.ui;

import org.eclipse.emf.importer.java.JavaImporter;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterWizard;
import org.eclipse.emf.converter.ModelConverter;


/**
 * @since 2.1.0
 */
public class JavaImporterWizard extends ModelImporterWizard
{
  @Override
  protected ModelConverter createModelConverter()
  {
    return new JavaImporter();
  }

  @Override
  public void addPages()
  {
    JavaPackagePage packagePage = new JavaPackagePage(getModelImporter(), "JavaPackages");
    addPage(packagePage);
  }
}
