/**
 * <copyright> 
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: DelegatingCommandAction.java,v 1.1 2010/03/11 02:30:12 khussey Exp $
 */
package org.eclipse.emf.edit.ui.action;


import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IActionDelegate2;
import org.eclipse.ui.IEditorActionDelegate;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;


/**
 * This class wraps an {@link IActionDelegate}, e.g., a {@link CommandAction}, to make it into an {@link Action}.
 * Even if the action delegate implements {@link IActionDelegate2}, this class will still only use the older interface
 * (i.e. it will not call {@link IActionDelegate2#init(org.eclipse.jface.action.IAction) init}, {@link IActionDelegate2#runWithEvent
 * runWithEvent}, or {@link IActionDelegate2#dispose dispose}, since it does not have the information required
 * to do so).
 */
public class DelegatingCommandAction extends Action implements ISelectionListener, ISelectionChangedListener
{
  /**
   * This is the action delegate we're wrapping.
   * @since 2.1.0
   */
  protected IActionDelegate actionDelegate;

  /**
   * If the action delegate is associated with an editor, is also recorded here.
   * This field was retained for backwards compatibility.
   * @deprecated As of EMF 2.1.0, replaced by {@link #actionDelegate}.
   */
  @Deprecated
  protected IEditorActionDelegate editorActionDelegate;

  /**
   * This is the current workbench part.
   */
  protected IWorkbenchPart workbenchPart;

  /**
   * If the workbench part is an editor, it is also recorded here.
   * This field was retained for backwards compatibility.
   * @deprecated As of EMF 2.1.0, replaced by {@link #workbenchPart}.
   */
  @Deprecated
  protected IEditorPart editorPart;

  /**
   * This constructs an instance.
   * @since 2.1.0
   */
  public DelegatingCommandAction(IActionDelegate actionDelegate)
  {
    this.actionDelegate = actionDelegate;
    if (actionDelegate instanceof IEditorActionDelegate)
    {
      editorActionDelegate = (IEditorActionDelegate)actionDelegate;
    }
  }

  /**
   * This constructor is simply retained for binary compatibility.
   * It just calls the {@link #DelegatingCommandAction(IActionDelegate) new form}.
   */
  public DelegatingCommandAction(IEditorActionDelegate editorActionDelegate)
  {
    this((IActionDelegate)editorActionDelegate);
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
    if (actionDelegate != null)
    {
      // This is for backwards compatibility, since the constructor may have been overridden before it was expected
      // to set actionDelegate.
      //
      editorActionDelegate.selectionChanged(this, selection);
    }
    else
    {
      actionDelegate.selectionChanged(this, selection);
    }
  }

  protected void handleSelection(ISelection selection) 
  {
    selectionChanged(selection);
  }

  /**
   * @since 2.1.0
   */
  protected void registerSelectionListener(IWorkbenchPart workbenchPart)
  {
    ISelectionProvider selectionProvider = workbenchPart.getSite().getSelectionProvider();
    if (selectionProvider != null) 
    {
      selectionProvider.addSelectionChangedListener(this);
      handleSelection(selectionProvider.getSelection());
    }
  }

  /**
   * @deprecated As of EMF 2.1.0, replaced by {@link #registerSelectionListener(IWorkbenchPart) registerSelectionListener}.
   */
  @Deprecated
  protected void registerSelectionListener(IEditorPart editorPart)
  {
    registerSelectionListener((IWorkbenchPart)editorPart);
  }

  /**
   * @since 2.1.0
   */
  protected void unregisterSelectionListener(IWorkbenchPart workbenchPart)
  {
    ISelectionProvider selectionProvider = workbenchPart.getSite().getSelectionProvider();
    if (selectionProvider != null) 
    {
      selectionProvider.removeSelectionChangedListener(this);
    }
  }

  /**
   * @deprecated As of EMF 2.1.0, replaced by {@link #unregisterSelectionListener(IWorkbenchPart) unregisterSelectionListener}.
   */
  @Deprecated
  protected void unregisterSelectionListener(IEditorPart editorPart) 
  {
    unregisterSelectionListener((IWorkbenchPart)editorPart);
  }

  /**
   * @deprecated As of EMF 2.1.0, replaced by {@link #setActiveWorkbenchPart}.
   */
  @Deprecated
  public void setActiveEditor(IEditorPart editorPart) 
  {
    setActiveWorkbenchPart(editorPart);
    this.editorPart = editorPart;
  }

  public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart)
  {
    if (this.workbenchPart != workbenchPart)
    {
      if (this.workbenchPart != null)
      {
        unregisterSelectionListener(this.workbenchPart);
      }
      this.workbenchPart = workbenchPart;

      if (actionDelegate == null)
      {
        // This is for backwards compatibility, since the constructor may have been overridden before it was expected
        // to set actionDelegate.
        //
        editorActionDelegate.setActiveEditor(this, (IEditorPart)workbenchPart);
      }
      else if (actionDelegate instanceof IEditorActionDelegate)
      {
        ((IEditorActionDelegate)actionDelegate).setActiveEditor(this, (IEditorPart)workbenchPart);
      }
      else
      {
        ((IViewActionDelegate)actionDelegate).init((IViewPart)workbenchPart);        
      }

      if (workbenchPart != null)
      {
        registerSelectionListener(workbenchPart);
      }
    }
  }

  @Override
  public void run()
  {
    actionDelegate.run(this);
  }
}
