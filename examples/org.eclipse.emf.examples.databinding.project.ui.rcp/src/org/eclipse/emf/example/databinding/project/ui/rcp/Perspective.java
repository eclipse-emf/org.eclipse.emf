/**
 * <copyright>
 *
 * Copyright (c) 2009 Tom Schindl and others.
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
 * $Id: Perspective.java,v 1.1 2009/05/29 15:06:30 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import org.eclipse.emf.example.databinding.project.ui.rcp.views.ProjectAdminViewPart;


/**
 * Create the perspective layout
 */
public class Perspective implements IPerspectiveFactory
{

  public void createInitialLayout(IPageLayout layout)
  {
    layout.setEditorAreaVisible(false);
    layout.addStandaloneView(ProjectAdminViewPart.ID, true, IPageLayout.LEFT, 1.0f, layout.getEditorArea());
  }
}
