/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: DoNothingAdapter.java,v 1.2 2005/06/08 06:16:23 nickb Exp $
 */
package com.example.sdo.library.util;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;

import com.example.sdo.library.Book;
import com.example.sdo.library.Writer;


public class DoNothingAdapter extends AdapterImpl
{

  public static DoNothingAdapter INSTANCE = new DoNothingAdapter();

  public void notifyChanged(Notification notification)
  {
    Object notifier = notification.getNotifier();
    if (notifier instanceof Book)
    {
    }
    else if (notifier instanceof Writer)
    {
    }
  }

}
