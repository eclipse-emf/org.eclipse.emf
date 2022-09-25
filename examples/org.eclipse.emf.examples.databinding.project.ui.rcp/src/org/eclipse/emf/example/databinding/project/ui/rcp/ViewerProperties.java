/*******************************************************************************
 * Copyright (c) 2008, 2015 Matthew Hall and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 194734)
 *     Matthew Hall - bug 264286
 *     Ovidio Mallo - bug 270494
 ******************************************************************************/
package org.eclipse.emf.example.databinding.project.ui.rcp;


import org.eclipse.jface.databinding.viewers.IViewerListProperty;
import org.eclipse.jface.databinding.viewers.IViewerSetProperty;
import org.eclipse.jface.databinding.viewers.IViewerValueProperty;
import org.eclipse.jface.internal.databinding.viewers.SelectionProviderMultipleSelectionProperty;
import org.eclipse.jface.internal.databinding.viewers.SelectionProviderSingleSelectionProperty;
import org.eclipse.jface.internal.databinding.viewers.StructuredViewerFiltersProperty;
import org.eclipse.jface.internal.databinding.viewers.ViewerCheckedElementsProperty;
import org.eclipse.jface.internal.databinding.viewers.ViewerInputProperty;


/**
 * This class is a bogus copy of an old pre-generics version superseded by <code>org.eclipse.jface.databinding.viewers.typed.ViewerProperties</code>.
 * Better to update sample not to use this, but this would be the only reason why we can no longer build with Helios.
 */
@SuppressWarnings("all")
public class ViewerProperties
{
  public static IViewerSetProperty checkedElements(Object elementType)
  {
    return new ViewerCheckedElementsProperty(elementType);
  }

  public static IViewerSetProperty filters()
  {
    return new StructuredViewerFiltersProperty();
  }

  public static IViewerValueProperty input()
  {
    return new ViewerInputProperty();
  }

  public static IViewerListProperty multipleSelection()
  {
    return new SelectionProviderMultipleSelectionProperty(false);
  }

  public static IViewerListProperty multiplePostSelection()
  {
    return new SelectionProviderMultipleSelectionProperty(true);
  }

  public static IViewerValueProperty singleSelection()
  {
    return new SelectionProviderSingleSelectionProperty(false);
  }

  public static IViewerValueProperty singlePostSelection()
  {
    return new SelectionProviderSingleSelectionProperty(true);
  }
}
