/*
 * Copyright (c) Eclipse contributors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.edit.ui.util;


import org.eclipse.emf.common.util.AbstractTreeIterator;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.viewers.ViewerFilter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;


/**
 * A tree iterator that iterates over the contents of a structured viewer.
 * As one would expect, this iterates over the {@link IStructuredContentProvider#getElements(Object) root} elements of the viewer
 * and over any {@link ITreeContentProvider#getChildren(Object) children} as well,
 * if the viewer's {@link StructuredViewer#getContentProvider() content provider} supports that.
 * The implementation also accounts for the {@link StructuredViewer#getFilters() filters} and the {@link StructuredViewer#getComparator() comparator} of the structured viewer,
 * iterating over only the filtered content in sorted order.
 * <p>
 * Generally the {@link #create(StructuredViewer)} method is used to create an instance, because the {@link #StructuredViewerTreeIterator(StructuredViewer) constructor} is protected.
 * The {@code create} method of this class is used by {@link FindAndReplaceTarget} to support find and replace in a {@link StructuredViewer structured viewer}.
 * Structured viewers may specialize the behavior of this iterator and hence the behavior of find and replace
 * by using a {@link StructuredViewer#setContentProvider(IContentProvider) content provider} that implements {@link Provider StructuredViewerTreeIterator.Provider}.
 * </p>
 *
 * @see Provider
 * @see FindAndReplaceTarget
 *
 * @since 2.14
 */
public class StructuredViewerTreeIterator extends AbstractTreeIterator<Object>
{
  private static final long serialVersionUID = 1L;

  /**
   * The viewer providing the content.
   */
  private final transient StructuredViewer viewer;

  /**
   * The viewer's structured content provider, if the viewer's {@link StructuredViewer#getContentProvider() content provider} is of that type.
   */
  private final transient IStructuredContentProvider structuredContentProvider;

  /**
   * The viewer's tree content provider, if the viewer's {@link StructuredViewer#getContentProvider() content provider} is of that type.
   */
  private final transient ITreeContentProvider treeContentProvider;

  /**
   * The viewer's {@link StructuredViewer#getFilters() filters}.
   */
  private final transient ViewerFilter[] filters;

  /**
   * The viewer's {@link StructuredViewer#getComparator() comparator}.
   */
  private final transient ViewerComparator comparator;

  /**
   * Creates an instance of a structured viewer tree iterator.
   * <p>
   * This implementation checks if the viewer's {@link StructuredViewer#getContentProvider() content provider} implements {@link Provider},
   * in which case it calls {@link Provider#create()} to create the structure viewer tree iterator.
   * This allows viewer to specialize the iterator implementation,
   * which is particularly important for {@link FindAndReplaceTarget find and replace} support if the viewer's tree is potentially infinite.
   * Otherwise it simply creates an {@link StructuredViewerTreeIterator#StructuredViewerTreeIterator(StructuredViewer) new instance} for the viewer.
   * </p>
   * @param viewer the viewer over which to iterate.
   * @return a structured viewer tree iterator.
   * @see FindAndReplaceTarget
   */
  public static final StructuredViewerTreeIterator create(StructuredViewer viewer)
  {
    IContentProvider contentProvider = viewer.getContentProvider();
    if (contentProvider instanceof StructuredViewerTreeIterator.Provider)
    {
      return ((StructuredViewerTreeIterator.Provider)contentProvider).create();
    }
    else
    {
      return new StructuredViewerTreeIterator(viewer);
    }
  }

  /**
   * Creates an instance for the viewer.
   * @param viewer the viewer.
   */
  protected StructuredViewerTreeIterator(StructuredViewer viewer)
  {
    super(viewer, false);
    this.viewer = viewer;
    IContentProvider contentProvider = viewer.getContentProvider();
    structuredContentProvider = contentProvider instanceof IStructuredContentProvider ? (IStructuredContentProvider)contentProvider : null;
    treeContentProvider = contentProvider instanceof ITreeContentProvider ? (ITreeContentProvider)contentProvider : null;
    filters = viewer.getFilters();
    comparator = viewer.getComparator();
  }

  /**
   * If the viewer's {@link StructuredViewer#getContentProvider() content provider} implements {@link IStructuredContentProvider}, this will return an iterator over the {@link IStructuredContentProvider#getElements(Object) elements}.
   * If the viewer'content provider also implements {@link ITreeContentProvider}, this will return an iterator over the {@link ITreeContentProvider#getChildren(Object) children} as well.
   * Otherwise it yields only the empty iterator.
   * This implementation calls {@link #getFilteredSortedChildren(Object, Object[]) getFilteredSortedChildren} to account for filtering and sorting of the result.
   * @param object the object over which to iterator.
   * @return the iterator over the children.
   */
  @Override
  protected Iterator<Object> getChildren(Object object)
  {
    if (object == viewer)
    {
      if (structuredContentProvider != null)
      {
        Object parent = viewer.getInput();
        Object[] elements = structuredContentProvider.getElements(parent);
        return getFilteredSortedChildren(parent, elements);
      }
    }
    else if (treeContentProvider != null)
    {
      Object[] children = treeContentProvider.getChildren(object);
      return getFilteredSortedChildren(object, children);
    }

    return Collections.emptyList().iterator();
  }

  /**
   * Returns the filtered sorted result for the given children of the given parent.
   * @param parent the parent of the children.
   * @param children the children of the parent.
   * @return the filtered sorted result for the given children of the given parent.
   */
  protected Iterator<Object> getFilteredSortedChildren(Object parent, Object[] children)
  {
    Object[] result = children;
    if (result.length > 0)
    {
      if (filters.length > 0 || comparator != null)
      {
        Object[] filteredSortedResult = children;
        if (filters.length > 0)
        {
          for (ViewerFilter viewerFilter : filters)
          {
            filteredSortedResult = viewerFilter.filter(viewer, parent, filteredSortedResult);
          }
        }

        if (comparator == null)
        {
          result = filteredSortedResult;
        }
        else
        {
          // Ensure that sorting always modifies a copy of the original array.
          if (filteredSortedResult == result)
          {
            filteredSortedResult = filteredSortedResult.clone();
          }
          comparator.sort(viewer, filteredSortedResult);
          result = filteredSortedResult;
        }
      }
    }

    return Arrays.asList(result).iterator();
  }

  /**
   * A provider for creating a {@link StructuredViewerTreeIterator}.
   * @see FindAndReplaceTarget
   * @see #create()
   */
  public interface Provider
  {
    public StructuredViewerTreeIterator create();
  }
}
