/**
 * Copyright (c) 2004-2010 IBM Corporation and others.
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


/**
 * A description of viewer changes required by an EMF notification. The EMF change is described through the base
 * <code>Notification</code> interface.
 */
public interface IViewerNotification extends Notification
{
  /**
   * The element to update or from which to refresh. The whole viewer is indicated by the null value.
   */
  Object getElement();

  /**
   * Whether the content under the element should be structurally refreshed.
   */
  boolean isContentRefresh();

  /**
   * Whether the label and icon for the element should be updated.
   */
  boolean isLabelUpdate();
}
