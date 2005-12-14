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
 * $Id: IModelImporterPage.java,v 1.1 2005/12/14 07:48:49 marcelop Exp $
 */
package org.eclipse.emf.importer.ui.contribution.base;

import org.eclipse.jface.wizard.IWizardPage;

import org.eclipse.emf.importer.ModelImporter;


/**
 * @since 2.2.0
 */
public interface IModelImporterPage extends IWizardPage
{
  ModelImporter getModelImporter();
}