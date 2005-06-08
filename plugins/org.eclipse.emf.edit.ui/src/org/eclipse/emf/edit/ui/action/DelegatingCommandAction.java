/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: DelegatingCommandAction.java,v 1.1.2.1 2005/06/08 18:27:42 nickb Exp $
 */
package org.eclipse.emf.edit.ui.action;


import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;


/**
 * This class wraps an {@link IEditorActionDelegate}, e.g., a {@link CommandAction}, to make it into an {@link Action}.
 */
public class DelegatingCommandAction extends Action implements ISelectionListener, ISelectionChangedListener
{
  /**
   *  This is the delegate;
   */
  protected IEditorActionDelegate editorActionDelegate;

  /**
   * This is the current editor.
   */
  protected IEditorPart editorPart;

  /**
   * This constructs an instance.
   */
  public DelegatingCommandAction(IEditorActionDelegate editorActionDelegate)
  {
    this.editorActionDelegate = editorActionDelegate;
  }

  public void selectionChanged(SelectionChangedEvent event) 
  {
    handleSelection(event.getSelection());
  }

  public void selectionChanged(IWorkbenchPart part, ISelection selection)
  {
    handleSelection(selection);
  }

  protected void selectionChanged(ISelection selection) 
  {
    editorActionDelegate.selectionChanged(this, selection);
  }

  protected void handleSelection(ISelection selection) 
  {
    selectionChanged(selection);
  }

  protected void registerSelectionListener(IEditorPart editorPart) 
  {
    ISelectionProvider selectionProvider = editorPart.getSite().getSelectionProvider();
    if (selectionProvider != null) 
    {
      selectionProvider.addSelectionChangedListener(this);
      handleSelection(selectionProvider.getSelection());
    }
  }

  protected void unregisterSelectionListener(IEditorPart editorPart) 
  {
    ISelectionProvider selectionProvider = editorPart.getSite().getSelectionProvider();
    if (selectionProvider != null) 
    {
      selectionProvider.removeSelectionChangedListener(this);
    }
  }

  public void setActiveEditor(IEditorPart editorPart) 
  {
    if (this.editorPart != editorPart)
    {
      if (this.editorPart != null)
      {
        unregisterSelectionListener(this.editorPart);
      }
      this.editorPart = editorPart;
      editorActionDelegate.setActiveEditor(this, editorPart);
      if (editorPart != null)
      {
        registerSelectionListener(editorPart);
      }
    }
  }

  public void run()
  {
    editorActionDelegate.run(this);
  }
}
