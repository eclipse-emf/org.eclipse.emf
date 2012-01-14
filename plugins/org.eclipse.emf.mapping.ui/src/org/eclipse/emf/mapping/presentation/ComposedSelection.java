/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.presentation;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;


public class ComposedSelection implements IStructuredSelection, IComposedSelection
{
  protected ISelection [] selections;
  protected ISelection primarySelection;

  public ComposedSelection(ISelection primarySelection, ISelection [] selections)
  {
    this.primarySelection = primarySelection;
    this.selections = selections;
  }

  public boolean isEmpty()
  {
    return primarySelection == null || primarySelection.isEmpty();
  }

  /**
   * @deprecated
   */
  @Deprecated
  public Iterator<?>  getElements()
  {
    return primarySelection instanceof IStructuredSelection ? ((IStructuredSelection)primarySelection).iterator() : null;
  }
  
  public Iterator<?> iterator()
  {
    return primarySelection instanceof IStructuredSelection ? ((IStructuredSelection)primarySelection).iterator() : null;
  }

  public Object [] toArray()
  {
    return primarySelection instanceof IStructuredSelection ? ((IStructuredSelection)primarySelection).toArray() : null;
  }

  public List<?> toList()
  {
    return primarySelection instanceof IStructuredSelection ? ((IStructuredSelection)primarySelection).toList() : null;
  }

  public Object getFirstElement()
  {
    return primarySelection instanceof IStructuredSelection ? ((IStructuredSelection)primarySelection).getFirstElement() : null;
  }

  public Object getPrimaryItem()
  {
    return primarySelection instanceof IStructuredSelection ? ((IStructuredSelection)primarySelection).getFirstElement() : null;
  }

  public int size()
  {
    return primarySelection instanceof IStructuredSelection ? ((IStructuredSelection)primarySelection).size() : 0;
  }

  public ISelection getSelection()
  {
    return primarySelection;
  }

  public ISelection [] getSelections()
  {
    return selections;
  }

  public IStructuredSelection getCombinedSelection()
  {
    List<Object> result = new ArrayList<Object>();
    for (int i = 0; i < selections.length; ++i)
    {
      ISelection selection = selections[i];
      if (selection instanceof IStructuredSelection)
      {
        List<?> list = ((IStructuredSelection)selection).toList();
        result.addAll(list);
      }
    }

    return new StructuredSelection(result);
  }

  @Override
  public boolean equals(Object that)
  {
    if (this == that)
    {
      return true;
    }
    else if (!(that instanceof IComposedSelection))
    {
      return false;
    }
    else 
    {
      IComposedSelection thatComposedSelection = (IComposedSelection)that;
      ISelection thatPrimarySelection = thatComposedSelection.getSelection();
      ISelection [] thatSelections = thatComposedSelection.getSelections();

      if (selections.length == thatSelections.length)
      {
        for (int i = 0; i < selections.length; ++i)
        {
          if (selections[i] == primarySelection)
          {
            if (thatSelections[i] != thatPrimarySelection)
            {
              return false;
            }
          }

          if (!selections[i].equals(thatSelections[i]))
          {
            return false;
          }
        }

        return true;
      }
      else
      {
        return false;
      }
    }
  }

  @Override
  public String toString()
  {
    StringBuffer result = new StringBuffer();
    result.append("ComposedSection(");
    if (selections != null) 
    {
      for (int i = 0; i < selections.length; ++i)
      {
        if (selections[i] == primarySelection)
        {
          result.append('(');
          result.append(selections[i]);
          result.append(')');
        }
        else
        {
          result.append(selections[i]);
        }
      }
    }
    result.append(')');
    return result.toString();
  }
}
