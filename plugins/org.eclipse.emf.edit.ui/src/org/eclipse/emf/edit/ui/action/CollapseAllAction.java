/*
 * Copyright (c) Eclipse contributors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.edit.ui.action;


import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IWorkbenchPart;


/**
 * An action for collapsing all items in a {@link TreeViewer tree viewer}.
 * 
 * @since 2.14
 */
public final class CollapseAllAction extends Action
{
  /**
   * The provider of the tree viewer.
   */
  private IViewerProvider viewerProvider;

  /**
   * Creates an instance.
   */
  public CollapseAllAction()
  {
    super(
      EMFEditUIPlugin.INSTANCE.getString("_UI_CollapseAll_menu_item"),
      ExtendedImageRegistry.INSTANCE.getImageDescriptor(EMFEditUIPlugin.INSTANCE.getImage("full/ctool16/CollapseAll")));
    setToolTipText(EMFEditUIPlugin.INSTANCE.getString("_UI_CollapseAll_tool_tip"));
  }

  /**
   * Calls {@link TreeViewer#collapseAll()} if the viewer provider yields a {@link TreeViewer}.
   */
  @Override
  public void run()
  {
    Viewer viewer = viewerProvider.getViewer();
    if (viewer instanceof TreeViewer)
    {
      ((TreeViewer)viewer).collapseAll();
    }
  }

  /**
   * Sets the current active workbench part.
   * The action is enabled only if the workbench part is a {@link IViewerProvider} that yields a {@link TreeViewer}.
   * 
   * @param workbenchPart the current active workbench part.
   * @see EditingDomainActionBarContributor#activate()
   * @see EditingDomainActionBarContributor#deactivate()
   */
  public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart)
  {
    if (workbenchPart instanceof IViewerProvider)
    {
      viewerProvider = (IViewerProvider)workbenchPart;
      setEnabled(viewerProvider.getViewer() instanceof TreeViewer);
    }
    else
    {
      viewerProvider = null;
      setEnabled(false);
    }
  }

  /**
   * Updates the action.
   * The action is enabled only if the workbench part is a {@link IViewerProvider} that yields a {@link TreeViewer}.
   * 
   * @see EditingDomainActionBarContributor#update()
   */
  public void update()
  {
    setEnabled(viewerProvider != null && viewerProvider.getViewer() instanceof TreeViewer);
  }
}