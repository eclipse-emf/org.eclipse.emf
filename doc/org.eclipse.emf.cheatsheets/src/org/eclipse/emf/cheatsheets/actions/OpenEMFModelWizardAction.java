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
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.cheatsheets.ICheatSheetAction;
import org.eclipse.ui.cheatsheets.ICheatSheetManager;

import org.eclipse.emf.importer.ui.EMFModelWizard;


/**
 * <p>Action that opens the new EMF model wizard.
 * @since 2.2.0
 */
public class OpenEMFModelWizardAction extends AbstractOpenWizardAction implements ICheatSheetAction
{
  protected IPath path;
  protected String descriptorID;

  /**
   * Execute the action
   * @param params Array of parameters - index 0: path of the genmodel, index 1: model importer descriptor ID
   * @param manager Cheatsheet Manager
   */
  public void run(String[] params, ICheatSheetManager manager)
  {
    if (params.length > 0 && params[0] != null)
    {
      path = new Path(params[0]).makeAbsolute();
      descriptorID = params[1];
    }
    run();
  }

  /**
   * Create a new wizard
   * @exception CoreException
   */
  @Override
  protected final INewWizard createWizard() throws CoreException
  {
    EMFModelWizard wizard = createEMFModelWizard();
    if (path != null)
    {
      wizard.setDefaultPath(path);
      wizard.setDefaultModelImporterDescriptorID(descriptorID);
    }
    return wizard;
  }

  /**
   * Create a new EMF model wizard
   * @return EMF model wizard
   */
  protected EMFModelWizard createEMFModelWizard()
  {
    return new EMFModelWizard();
  }
}
