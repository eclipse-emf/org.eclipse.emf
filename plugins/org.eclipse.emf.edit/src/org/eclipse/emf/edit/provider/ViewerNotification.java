/**
 * Copyright (c) 2004-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
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
   * Wraps the given notification to make the given element be operated on by the viewer that receives it. If the 
   * notification is an {@link IViewerNotification}, it is {@link #ViewerNotification(IViewerNotification, Object)
   * wrapped} in a <code>ViewerNotification</code>, with that element. Otherwise, it is wrapped in a {@link
   * org.eclipse.emf.common.notify.NotificationWrapper} that returns the element as its <code>notifier</code>.
   */
  public static Notification wrapNotification(Notification notification, Object element)
  {
    if (notification instanceof IViewerNotification)
    {
      return new ViewerNotification((IViewerNotification)notification, element);
    }
    return new NotificationWrapper(element, notification);
  }

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
   * Creates a notification to refresh the content under and update the label for the given element.
   */
  public ViewerNotification(Notification decoratedNotification, Object element)
  {
    this(decoratedNotification, element, true, true);
  }

  /**
   * Creates a notification to optionally refresh the content under and update the label for the given element.
   */
  public ViewerNotification(Notification decoratedNotification, Object element, boolean contentRefresh, boolean labelUpdate)
  {
    super(decoratedNotification);
    this.element = element;
    this.contentRefresh = contentRefresh;
    this.labelUpdate = labelUpdate;
  }

  /**
   * Wraps an existing viewer notification to specify a different element.
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

  @Override
  public String toString()
  {
    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (");
    if (contentRefresh) result.append("refresh ");
    if (labelUpdate) result.append("update ");
    result.append("element: ");
    result.append(element);
    result.append(')');
    return result.toString();
  }
}
