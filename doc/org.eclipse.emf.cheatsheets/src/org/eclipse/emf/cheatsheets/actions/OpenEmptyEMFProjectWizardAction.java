/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.cheatsheets.actions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;

import org.eclipse.emf.codegen.ecore.ui.EmptyProjectWizard;


/**
 * <p>Action that opens the new EMF model wizard.
 * @since 2.2.0
 */
public class OpenEmptyEMFProjectWizardAction extends AbstractOpenWizardAction implements ICheatSheetAction
{
  protected String projectName;

  /**
   * Execute the action
   * @param params Array of parameters - index 0: path of the genmodel, index 1: model importer descriptor ID
   * @param manager Cheatsheet Manager
   */
  public void run(String[] params, ICheatSheetManager manager)
  {
    projectName = params[0];
    run();
  }

  /**
   * Create a new wizard
   * @exception CoreException
   */
  @Override
  protected final INewWizard createWizard() throws CoreException
  {
    EmptyProjectWizard wizard = new EmptyProjectWizard();
    if (projectName != null)
    {
      wizard.setInitialProjectName(projectName);
    }
    return wizard;
  }
}
