/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: Adapter.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.common.notify;



/**
 * A receiver of notifications.
 * An adapter is typically associated with a {@link Notifier} via an {@link AdapterFactory}.
 */
public interface Adapter
{
  /**
   * Notifies that a change to some feature has occurred.
   * @param notification a description of the change.
   */
  void notifyChanged(Notification notification);

  /**
   * Returns the target from which the adapter receives notification.
   * In general, an adapter may be shared by more than one notifier.
   * @return the target notifier.
   * @see #setTarget
   */
  Notifier getTarget();

  /**
   * Sets the target from which the adapter will receive notification.
   * In general, an adapter may be shared by more than one notifier.
   * @param newTarget the new notifier.
   * @see #getTarget
   */
  void setTarget(Notifier newTarget);

  /**
   * Returns whether the adapter is of the given type.
   * In general, an adapter may be the adapter for many types.
   * @param type the type.
   * @return whether the adapter is of the given type.
   * @see AdapterFactory#isFactoryForType
   */
  boolean isAdapterForType(Object type);
}
