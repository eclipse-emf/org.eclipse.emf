/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: Util.java,v 1.2 2009/06/07 17:45:50 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.databinding;

import org.eclipse.core.databinding.AggregateValidationStatus;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.ObservablesManager;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.IStatus;


/**
 * Utility to work around databinding problems
 */
public class Util
{
  /**
   * In a master detail scenario there's a problem when the validation status of a binding
   * is not OK and you change the master selection (See bug 278301) 
   * 
   * @param ctx the databinding context
   * @param master the observable master
   */
  public static void masterDetailFixup(final DataBindingContext ctx, IObservableValue master)
  {
    final AggregateValidationStatus s = new AggregateValidationStatus(ctx, AggregateValidationStatus.MAX_SEVERITY);

    master.addChangeListener(new IChangeListener()
      {

        public void handleChange(ChangeEvent event)
        {
          IStatus status = (IStatus)s.getValue();
          if (status != null && !status.isOK())
          {
            ctx.updateTargets();
          }
        }
      });
  }

  /**
   * @return an observable manager where {@link ObservablesManager#runAndCollect(Runnable)} is working
   */
  public static ObservablesManager getObservableManager()
  {
    return new EMFObservablesManager();
  }
}
