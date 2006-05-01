/**
 * <copyright> 
 *
 * Copyright (c) 6 IBM Corporation and others.
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
 * $Id: AbstractGeneratorAdapterFactory.java,v 1.1 2006/05/01 10:24:15 davidms Exp $
 */
package org.eclipse.emf.codegen.ecore.generator;

import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

/**
 * @since 2.2.0
 */
public abstract class AbstractGeneratorAdapterFactory extends AdapterFactoryImpl implements GeneratorAdapterFactory
{
  protected Generator generator;

  public AbstractGeneratorAdapterFactory()
  {
  }

  public boolean isFactoryForType(Object type)
  {
    return type == GeneratorAdapter.class;
  }

  // Because each of many generator adapter factories can have its own generator adapter on a single object, we need 
  // to use the adapter factory instance as the type seen by the adapter.
  //
  public Adapter adapt(Notifier target, Object type)
  {
    return super.adapt(target, this);
  }

  // The adapter must implement GeneratorAdapter so we can associated it with this adapter factory.
  //
  protected Adapter createAdapter(Notifier target, Object type)
  {
    Adapter adapter = createAdapter(target);
    if (adapter != null)
    {
      ((GeneratorAdapter)adapter).setAdapterFactory(this);
    }
    return adapter;
  }

  // Override this to create the adapter for a notifier.
  //
  protected abstract Adapter createAdapter(Notifier target);

  //DMS the POJO case parallels the Notifier case, to automatically set the factory on the adapter. Overkill? 

  // There's no general way to check if a non-notifier already has an adapter associated with it, but in cases
  // where there is a way, override this method and do it here.
  //
  protected Object resolve(Object object, Object type)
  {
    return createAdapter(object, type);
  }

  // Associate the adapter with this adapter factory.
  //
  protected Object createAdapter(Object object, Object type)
  {
    GeneratorAdapter adapter = createAdapter(object);
    if (adapter != null)
    {
      adapter.setAdapterFactory(this);
    }
    return adapter;
  }

  // Override this to create the adapter for an arbitrary object.
  //
  protected GeneratorAdapter createAdapter(Object object)
  {
    return object instanceof GeneratorAdapter ? (GeneratorAdapter)object : null;
  }

  public Generator getGenerator()
  {
    return generator;
  }

  public void setGenerator(Generator generator)
  {
    this.generator = generator;
  }

  public void initialize(Object input)
  {
  }

  // Override this to dispose whatever adapters this factory has created.
  //
  public abstract void dispose();
}
