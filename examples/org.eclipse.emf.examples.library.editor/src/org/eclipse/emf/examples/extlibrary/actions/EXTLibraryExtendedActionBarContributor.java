/**
 * <copyright>
 *
 * Copyright (c) 2005-2007 IBM Corporation and others.
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
 * $Id: EXTLibraryExtendedActionBarContributor.java,v 1.3 2007/03/22 02:04:15 davidms Exp $
 */
package org.eclipse.emf.examples.extlibrary.actions;


import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;

import org.eclipse.emf.examples.extlibrary.Library;
import org.eclipse.emf.examples.extlibrary.presentation.EXTLibraryActionBarContributor;


/**
 * Extension to the generated action bar contributor to handle creation of
 * multi-rooted resources.
 */
public class EXTLibraryExtendedActionBarContributor extends EXTLibraryActionBarContributor
{

  /**
   * Constructor
   */
  public EXTLibraryExtendedActionBarContributor()
  {
    super();
  }

  /*
   * @see org.eclipse.emf.examples.library.presentation.EXTLibraryActionBarContributor#generateCreateChildActions(java.util.Collection,
   *      org.eclipse.jface.viewers.ISelection)
   */
  @Override
  protected java.util.Collection<IAction> generateCreateChildActions(Collection<?> descriptors, ISelection selection)
  {
    Collection<IAction> actions = new ArrayList<IAction>(super.generateCreateChildActions(descriptors, selection));
    if (selection instanceof IStructuredSelection && ((IStructuredSelection)selection).size() == 1)
    {
      Object object = ((IStructuredSelection)selection).getFirstElement();
      if (object instanceof Resource)
      {
        actions.add(generateResourceAction(((Resource)object)));
      }
    }

    return actions;
  }

  /*
   * @see org.eclipse.emf.examples.extlibrary.presentation.EXTLibraryActionBarContributor#generateCreateSiblingActions(java.util.Collection,
   *      org.eclipse.jface.viewers.ISelection)
   */
  @Override
  protected Collection<IAction> generateCreateSiblingActions(Collection<?> descriptors, ISelection selection)
  {
    Collection<IAction> actions = new ArrayList<IAction>(super.generateCreateSiblingActions(descriptors, selection));
    if (selection instanceof IStructuredSelection && ((IStructuredSelection)selection).size() == 1)
    {
      Object object = ((IStructuredSelection)selection).getFirstElement();
      if (object instanceof Library)
      {
        actions.add(generateResourceAction(((Library)object).eResource()));
      }
    }

    return actions;
  }

  /**
   * Generates a library action for a given resource
   * 
   * @param resource
   *            the containing resource
   * @return the action
   */
  protected Action generateResourceAction(Resource resource)
  {
    return new CreateLibraryAction(activeEditorPart, new StructuredSelection(resource));
  }

  /*
   * @see org.eclipse.ui.part.EditorActionBarContributor#init(org.eclipse.ui.IActionBars)
   */
  @Override
  public void init(IActionBars actionBars)
  {
    super.init(actionBars);

    ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
    this.deleteAction = new ExtendedDeleteAction();
    this.deleteAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE));
    actionBars.setGlobalActionHandler(ActionFactory.DELETE.getId(), this.deleteAction);

    actionBars.updateActionBars();
  }
}