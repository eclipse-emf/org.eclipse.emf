/**
 * <copyright> 
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: ViewerNotification.java,v 1.1 2004/03/31 19:48:18 davidms Exp $
 */
package org.eclipse.emf.edit.provider;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationWrapper;


/**
 * A simple implementation of <code>IViewerNotification</code> that decorates an ordinary {@link
 * org.eclipse.emf.common.notify.Notification}.
 */
public class ViewerNotification extends NotificationWrapper implements IViewerNotification
{
  /**
   * The element to update or from which to refresh.  The whole viewer is indicated by the null value.
   * @see #getElement
   */
  protected Object element;

  /**
   * Whether the content under the element should be structurally refreshed.
   * @see #isContentRefresh
   */
  protected boolean contentRefresh;

  /**
   * Whether the label and icon for the element should be updated.
   * @see #isLabelUpdate
   */
  protected boolean labelUpdate;

  /**
   * Creates a notification to fully refresh a viewer.
   */
  public ViewerNotification(Notification decoratedNotification)
  {
    this(decoratedNotification, null, true, true);
  }

  /**
   * Creates a notification to update the label for the given element.
   */
  public ViewerNotification(Notification decoratedNotification, Object element)
  {
    this(decoratedNotification, element, false, true);
  }

  /**
   * Creates a notification to optionally refresh the content and update the label for and under the given element.
   */
  public ViewerNotification(Notification decoratedNotification, Object element, boolean contentRefresh, boolean labelUpdate)
  {
    super(decoratedNotification);
    this.element = element;
    this.contentRefresh = contentRefresh;
    this.labelUpdate = labelUpdate;
  }

  /**
   * Decorates an existing viewer notification to specify a different element.
   */
  public ViewerNotification(IViewerNotification viewerNotification, Object element)
  {
    this(viewerNotification, element, viewerNotification.isContentRefresh(), viewerNotification.isLabelUpdate());
  }

  /**
   * Returns the element to update or from which to refresh.
   */
  public Object getElement()
  {
    return element;
  }

  /**
   * Returns whether the content under the element should be structurally refreshed.
   */
  public boolean isContentRefresh()
  {
    return contentRefresh;
  }

  /**
   * Returns whether the label and icon for the element should be updated.
   */
  public boolean isLabelUpdate()
  {
    return labelUpdate;
  }
}
