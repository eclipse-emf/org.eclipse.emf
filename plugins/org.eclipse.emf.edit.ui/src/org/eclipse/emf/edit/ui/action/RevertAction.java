/*
 * Copyright (c) Eclipse contributors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.edit.ui.action;


import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.util.IRevertablePart;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPart;


/**
 * An action for {@link IRevertablePart#doRevert() reverting} a workbench part to its saved state.
 * 
 * @since 2.14
 */
public final class RevertAction extends Action
{
  /**
   * The current associated revertable part.
   * @see #setActiveWorkbenchPart(IWorkbenchPart)
   */
  private IRevertablePart revertablePart;

  public RevertAction()
  {
    super(EMFEditUIPlugin.INSTANCE.getString("_UI_Revert_menu_item"));
  }

  @Override
  public void run()
  {
    if (revertablePart != null)
    {
      revertablePart.doRevert();
    }
  }

  /**
   * Sets the current active workbench part.
   * @param workbenchPart the current active workbench part.
   * @see EditingDomainActionBarContributor#activate()
   * @see EditingDomainActionBarContributor#deactivate()
   */
  public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart)
  {
    if (workbenchPart instanceof IRevertablePart)
    {
      this.revertablePart = (IRevertablePart)workbenchPart;
      setEnabled(revertablePart.isDirty());
    }
    else
    {
      this.revertablePart = null;
      setEnabled(false);
    }
  }

  /**
   * Updates the action.
   * 
   * @see EditingDomainActionBarContributor#update()
   */
  public void update()
  {
    setEnabled(revertablePart != null && revertablePart.isDirty());
  }
}