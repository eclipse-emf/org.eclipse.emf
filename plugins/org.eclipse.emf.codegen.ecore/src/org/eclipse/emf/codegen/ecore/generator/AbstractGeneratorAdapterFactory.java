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
 * $Id: AbstractGeneratorAdapterFactory.java,v 1.4 2007/06/12 20:56:34 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.generator;

import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

/**
 * A base <code>GeneratorAdapterFactory</code> implementation. Classes that extend this implementation need only
 * implement {@link #createAdapter(Notifier)} and {@link #dispose()}.
 * 
 * <p>An alternate approach to implementing <code>GeneratorAdapterFactory</code> is to extend the adapter factory
 * generated for a package. <code>GeneratorAdapterFactory</code>'s overrides of {@link #isFactoryForType(Object)} and
 * {@link #adapt(Notifier, Object)} should be duplicated in classes that take such an approach.
 *  
 * @since 2.2.0
 */
public abstract class AbstractGeneratorAdapterFactory extends AdapterFactoryImpl implements GeneratorAdapterFactory
{
  protected Generator generator;

  public AbstractGeneratorAdapterFactory()
  {
    super();
  }

  /**
   * Returns <code>true</code> when the type is <code>GeneratorAdapter.class</code>.
   */
  @Override
  public boolean isFactoryForType(Object type)
  {
    return type == GeneratorAdapter.class;
  }

  /**
   * Does an {@link org.eclipse.emf.common.notify.impl.AdapterFactoryImpl#adapt(Notifier, Object) adapt(Notifier, Object)},
   * substituting <code>this</code> for the given <code>type</code>. This substitution is necessary because each of many
   * generator adapter factories can have its own generator adapter on a single object.
   */ 
  @Override
  public Adapter adapt(Notifier target, Object type)
  {
    return super.adapt(target, this);
  }

  /**
   * Calls {@link #createAdapter(Notifier)} to create an adapter for the given <code>Notifier</code> and sets its
   * {@link GeneratorAdapter#setAdapterFactory(GeneratorAdapterFactory) adapter factory} to <code>this</code>.
   */ 
  @Override
  protected Adapter createAdapter(Notifier target, Object type)
  {
    Adapter adapter = createAdapter(target);
    if (adapter != null)
    {
      ((GeneratorAdapter)adapter).setAdapterFactory(this);
    }
    return adapter;
  }

  /**
   * Override this to create the adapter for a <code>Notifier</code>. The adapter must implement
   * <code>GeneratorAdapter</code>.
   */
  @Override
  protected abstract Adapter createAdapter(Notifier target);

  /**
   * There's no general way to check if a non-<code>Notifier</code> already has an adapter associated with it, but in
   * cases where there is a way, override this method and do it here. This method parallels
   * {@link #adapt(Notifier, Object)} for plain Java objects, and is not usually needed when dealing only with
   * <code>EObject</code>s.
   */
  @Override
  protected Object resolve(Object object, Object type)
  {
    return createAdapter(object, type);
  }

  /**
   * Calls {@link #createAdapter(Object)} to create an adapter for the given object and sets its
   * {@link GeneratorAdapter#setAdapterFactory(GeneratorAdapterFactory) adapter factory} to <code>this</code>. This
   * method parallels {@link #createAdapter(Notifier, Object)} for plain Java objects, and is not usually needed when
   * dealing only with <code>EObject</code>s.
   */ 
  protected Object createAdapter(Object object, Object type)
  {
    GeneratorAdapter adapter = createAdapter(object);
    if (adapter != null)
    {
      adapter.setAdapterFactory(this);
    }
    return adapter;
  }

 /**
  * The given object is returned if it is, itself, a <code>GeneratorAdapter</code>. This can be overridden to create an
  * appropriate adapter for a object. It parallels {@link #createAdapter(Notifier)} for plain Java objects, and is and
  * is not usually needed when dealing only with <code>EObject</code>s.
  */
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

  /**
   * Performs initialization for the given model-level input object. This implementation does nothing; it should be
   * overridden when {@link Generator#getOptions() options} should be set on the generator, or other initialization
   * is required.
   */
  public void initialize(Object input)
  {
    // Subclasses may override
  }

  public abstract void dispose();
}
