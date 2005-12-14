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
 * $Id: GenModelExportActionDelegate.java,v 1.1 2005/12/14 08:06:32 marcelop Exp $
 */
package org.eclipse.emf.exporter.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;

import org.eclipse.emf.converter.ui.OpenWizardActionDelegate;


/**
 * Exports a GenModel file and the Ecore files behind it.
 * @since 2.2.0
 */
public class GenModelExportActionDelegate extends OpenWizardActionDelegate
{
  protected IWizard createWizard(IWorkbench workbench, Shell shell, IFile file)
  {
    EMFExportWizard wizard = new EMFExportWizard(file);
    wizard.init(workbench, new StructuredSelection(file));
    return wizard;
  }
}
