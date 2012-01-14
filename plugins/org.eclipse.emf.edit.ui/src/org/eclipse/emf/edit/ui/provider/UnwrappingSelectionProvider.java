/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.ui.provider;

import java.util.List;

import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * @since 2.3
 */
public class UnwrappingSelectionProvider implements ISelectionProvider
{
  protected ISelection selection;
  protected List<ISelectionChangedListener> listeners = new UniqueEList.FastCompare<ISelectionChangedListener>();
  protected ISelectionChangedListener selectionChangedListener = 
    new ISelectionChangedListener()
    {
      public void selectionChanged(SelectionChangedEvent event)
      {
        setSelection(event.getSelection());
      }
    };

  public UnwrappingSelectionProvider(ISelectionProvider selectionProvider)
  {
    selectionProvider.addSelectionChangedListener(selectionChangedListener);
    setSelection(selectionProvider.getSelection());
  }

  public void addSelectionChangedListener(ISelectionChangedListener listener)
  {
    listeners.add(listener);
  }

  public ISelection getSelection()
  {
    return selection;
  }

  public void removeSelectionChangedListener(ISelectionChangedListener listener)
  {
    listeners.remove(listener);
  }

  public void setSelection(ISelection selection)
  {
    if (selection instanceof IStructuredSelection)
    {
      Object [] objects = ((IStructuredSelection)selection).toArray();
      for (int i = 0; i < objects.length; ++i)
      {
        objects[i] = unwrap(objects[i]);
      }
      this.selection = new StructuredSelection(objects);
    }
    else
    {
      this.selection = selection;
    }
    fireSelectionChanged();
  }
  
  protected Object unwrap(Object object)
  {
    return AdapterFactoryEditingDomain.unwrap(object);
  }
  
  protected void fireSelectionChanged()
  {
    for (ISelectionChangedListener selectionChangedListener : listeners)
    {
      selectionChangedListener.selectionChanged(new SelectionChangedEvent(this, selection));
    }
  }
}
