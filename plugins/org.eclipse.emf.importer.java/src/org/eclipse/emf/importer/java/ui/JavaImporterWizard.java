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
 * $Id: JavaImporterWizard.java,v 1.2 2005/05/12 17:09:33 marcelop Exp $
 */
package org.eclipse.emf.importer.java.ui;

import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.java.JavaImporter;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterWizard;


/**
 * @since 2.1.0
 */
public class JavaImporterWizard extends ModelImporterWizard
{
  public JavaImporterWizard()
  {
    super();
  }

  protected ModelImporter createModelImporter()
  {
    return new JavaImporter();
  }

  public void addPages()
  {
    JavaPackagePage packagePage = new JavaPackagePage(getModelImporter(), "RosePackages");
    addPage(packagePage);
  }
}
