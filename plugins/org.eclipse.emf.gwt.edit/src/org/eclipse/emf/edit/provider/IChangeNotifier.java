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
 * $Id: IChangeNotifier.java,v 1.1 2010/04/28 14:48:39 emerks Exp $
 */
package org.eclipse.emf.edit.provider;


import org.eclipse.emf.common.notify.Notification;


/**
 * This interface is implemented by something that can handle MOF-style change notifications.
 * See {@link org.eclipse.emf.common.notify.Adapter} and {@link org.eclipse.emf.common.notify.Notifier} for more details.
 */
public interface IChangeNotifier
{
  /**
   * This calls {@link org.eclipse.emf.edit.provider.INotifyChangedListener#notifyChanged notifyChanged} for each listener.
   */
  void fireNotifyChanged(Notification notification);

  /**
   * This adds another listener.
   */
  void addListener(INotifyChangedListener notifyChangedListener);

  /**
   * This removes a listener.
   */
  void removeListener(INotifyChangedListener notifyChangedListener);
}
