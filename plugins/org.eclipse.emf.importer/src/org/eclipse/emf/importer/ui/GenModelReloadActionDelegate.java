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
package org.eclipse.emf.importer.ui;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;

import org.eclipse.emf.converter.ui.OpenWizardActionDelegate;


/**
 * Reloads a GenModel file and the Ecore files behind it.
 * @since 2.1.0
 */
public class GenModelReloadActionDelegate extends OpenWizardActionDelegate
{
  @Override
  protected IWizard createWizard(IWorkbench workbench, Shell shell, IFile file)
  {
    EMFModelWizard wizard = new EMFModelWizard(file);
    wizard.init(workbench, new StructuredSelection(file));
    return wizard;
  }
}
