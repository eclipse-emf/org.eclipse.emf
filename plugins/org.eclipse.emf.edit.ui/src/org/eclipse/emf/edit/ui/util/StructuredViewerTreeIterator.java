/*
 * Copyright (c) Eclipse contributors and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.edit.ui.util;


import org.eclipse.emf.common.util.AbstractTreeIterator;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;


/**
 * A tree iterator that iterates over the contents of a structured viewer.
 * As one would expect, this iterates over the root elements of the viewer and over any children as well, if the viewer's {@link StructuredViewer#getContentProvider() content provider} supports that.
 * <p>
 * Generally the {@link #create(StructuredViewer)} method is used to create an instance.
 * The {@code create} of this class is used by {@link FindAndReplaceTarget} to support find and replace in a {@link StructuredViewer structured viewer}.
 * Structured viewers may specialize the behavior of find and replace by using a {@link StructuredViewer#setContentProvider(IContentProvider) content provider} that implements {@link Provider StructuredViewerTreeIterator.Provider}.
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
  private transient StructuredViewer viewer;

  /**
   * The tree content provider of the viewer, if the viewer's {@link StructuredViewer#getContentProvider() content provider} is of that type.
   */
  private transient ITreeContentProvider treeContentProvider;

  /**
   * Creates an instance of a structured viewer tree iterator.
   * <p>
   * This implementation checks if the viewer's {@link StructuredViewer#getContentProvider() content provider} implements {@link Provider}i,
   * in which case it calls {@link Provider#create()} to create the structure viewer tree iterator.
   * This allows viewer to specialize the the iterator,
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
  }

  /**
   * If the viewer's {@link StructuredViewer#getContentProvider() content provider} implements {@link IStructuredContentProvider}, this will return an iterator over the {@link IStructuredContentProvider#getElements(Object) elements}.
   * If the viewer'content provider also implements {@link ITreeContentProvider}, this will return an iterator over the {@link ITreeContentProvider#getChildren(Object) children} as well.
   * Otherwise it yields only the empty iterator.
   */
  @Override
  protected Iterator<Object> getChildren(Object object)
  {
    if (object == viewer)
    {
      IContentProvider contentProvider = viewer.getContentProvider();
      if (contentProvider instanceof IStructuredContentProvider)
      {
        IStructuredContentProvider structuredContentProvider = (IStructuredContentProvider)contentProvider;
        if (structuredContentProvider instanceof ITreeContentProvider)
        {
          treeContentProvider = (ITreeContentProvider)structuredContentProvider;
        }

        return Arrays.asList(structuredContentProvider.getElements(viewer.getInput())).iterator();
      }

      return Collections.emptyList().iterator();
    }

    if (treeContentProvider != null)
    {
      return Arrays.asList(treeContentProvider.getChildren(object)).iterator();
    }

    return Collections.emptyList().iterator();
  }

  /**
   * A provider for creating a {@link StructuredViewerTreeIterator}.
   * @see FindAndReplaceTarget
   */
  public interface Provider
  {
    public StructuredViewerTreeIterator create();
  }
}
