/**
 * <copyright>
 *
 * Copyright (c) 2009 BestSolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: CustomObservablesManager.java,v 1.4 2009/06/07 17:20:55 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.databinding;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.ObservablesManager;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.internal.databinding.IdentitySet;
import org.eclipse.core.internal.databinding.Pair;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.emf.example.databinding.project.ui.rcp.Activator;


/**
 * Because of a too late discovered bug in {@link ObservablesManager} it's not behaving
 * appropriately in some situations.
 * This class provides a temporary workaround until we get an upstream fix
 */
@SuppressWarnings("restriction")
class CustomObservablesManager extends ObservablesManager
{
  /**
   * Executes the specified runnable and adds to this manager all observables created while executing the runnable.
   */
  @Override
  public void runAndCollect(Runnable runnable)
  {
    super.runAndCollect(runnable);
  }

  @SuppressWarnings("unchecked")
  public void dispose()
  {
    try
    {
      Field fmanagedObservables = ObservablesManager.class.getDeclaredField("managedObservables");
      fmanagedObservables.setAccessible(true);

      Field fcontexts = ObservablesManager.class.getDeclaredField("contexts");
      fcontexts.setAccessible(true);

      Field fexcludedObservables = ObservablesManager.class.getDeclaredField("excludedObservables");
      fexcludedObservables.setAccessible(true);

      Set managedObservables = (Set)fmanagedObservables.get(this);
      Set excludedObservables = (Set)fexcludedObservables.get(this);
      Map contexts = (Map)fcontexts.get(this);

      Set observables = new IdentitySet();
      observables.addAll(managedObservables);
      for (Iterator it = contexts.keySet().iterator(); it.hasNext();)
      {
        DataBindingContext context = (DataBindingContext)it.next();
        Pair trackModelsOrTargets = (Pair)contexts.get(context);
        boolean disposeTargets = ((Boolean)trackModelsOrTargets.a).booleanValue();
        boolean disposeModels = ((Boolean)trackModelsOrTargets.b).booleanValue();
        for (Iterator it2 = context.getBindings().iterator(); it2.hasNext();)
        {
          Binding binding = (Binding)it2.next();
          if (disposeTargets)
          {
            observables.add(binding.getTarget());
          }
          if (disposeModels)
          {
            observables.add(binding.getModel());
          }
        }
      }
      observables.removeAll(excludedObservables);

      MultiStatus m = new MultiStatus(Activator.PLUGIN_ID, 0, "Failure of disposing observables.", null);

      for (Iterator it = observables.iterator(); it.hasNext();)
      {
        IObservable observable = (IObservable)it.next();
        if (!observable.isDisposed())
        {
          try
          {
            observable.dispose();
          }
          catch (Exception e)
          {
            m.add(new Status(IStatus.WARNING, Activator.PLUGIN_ID, "Failed to dispose observable.", e));
          }
        }
      }

      if ( Activator.getDefault().isDebugging() && !m.isOK())
      {
        Activator.getDefault().getLog().log(m);
      }
    }
    catch (Exception e)
    {
      Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Failed to dispose manager.", e));
    }
  }
}
