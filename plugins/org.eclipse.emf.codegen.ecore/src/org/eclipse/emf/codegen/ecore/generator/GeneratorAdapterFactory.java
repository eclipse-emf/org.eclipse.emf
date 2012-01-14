/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;

/**
 * A factory for adapters that perform code generation for a {@link Generator}.
 * 
 * <p>A single <code>GeneratorAdapterFactory</code> is associated with an EMF or Java package, and can return an adapter
 * that implements {@link GeneratorAdapter} for instances of any or all of the classes within that package. Multiple
 * factories can be associated can be associated with a single package, with all of their adapters contributing
 * functionality to code generation for an object.
 * 
 * <p>A type of <code>GeneratorAdapterFactory</code> is registered, via a {@link Descriptor descriptor}, against a
 * package ID in a {@link Descriptor.Registry registry}, in order to create adapters for that package. Typically, this
 * is done via the <code>org.eclipse.emf.codegen.ecore.generatorAdapters</code> extension point, using an
 * <code>adapterFactory</code> element.
 * 
 * <p>A <code>GeneratorAdapterFactory</code> is a factory for type <code>GeneratorAdapter.class</code>; that is, its
 * {@link org.eclipse.emf.common.notify.AdapterFactory#isFactoryForType(Object) isFactoryForType(Object)} method should
 * return <code>true</code> for this type. However, because EMF's adapter framework supports only one adapter of a given
 * type on any object and because each of many generator adapter factories needs to be able to have a generator on a
 * single object, <code>GeneratorAdapter.class</code> cannot be used as the type of the adapters. Instead, each
 * generator adapter factory should use itself as the type of its adapters.
 *
 * <p>This interface is typically implemented by extending either {@link AbstractGeneratorAdapterFactory} or the
 * generated adapter factory for a package. The latter approach provides efficient, package-specific type switching for
 * <code>createAdapter(Notifier)</code>, while the former provides simple implementations for several other required
 * methods.
 * 
 * <p>There is also a generic implementation provided by the framework that can create a single adapter for just one
 * type of object. This implementation is used under the covers for adapters registered via the above extension point
 * with an <code>adapter</code> element.
 * 
 * @since 2.2.0
 */
public interface GeneratorAdapterFactory extends AdapterFactory
{
  /**
   * In essence, a factory for generator adapter factories. <code>Descriptor</code>s are stored in a
   * {@link Registry Registry} and create a particular type of {@link GeneratorAdapterFactory}, providing an additional
   * level of indirection that allows multiple {@link Generator}s to share a single registry and supports
   * extension-point-based registration with deferred class loading.
   */
  public interface Descriptor
  {
    /**
     * Creates and returns an instance of a particular type of <code>GeneratorAdapterFactory</code>. A new instance
     * should be returned on each invocation. 
     */
    GeneratorAdapterFactory createAdapterFactory();

    /**
     * A registry of {@link GeneratorAdapterFactory.Descriptor Descriptor}s. Descriptors are keyed by package ID, and
     * multiple descriptors can be associated with each package ID.
     */
    public interface Registry
    {
      /**
       * The global generator adapter factory descriptor registry.
       */
      public static final Registry INSTANCE = new DelegatingRegistry();

      /**
       * Returns the descriptors registered against the given package ID.
       */
      Collection<Descriptor> getDescriptors(String packageID);

      /**
       * Registers the given descriptor against the specified package ID.
       * @return whether the descriptor was added to the collection of descriptors for that package ID.
       */
      boolean addDescriptor(String packageID, Descriptor descriptor);

      /**
       * Removes the given descriptor from the collection registered against the given package ID.
       * @return whether the descriptor was deregistered (this should be true if it was previously registered against
       * that package ID).
       */
      boolean removeDescriptor(String packageID, Descriptor descriptor);

      /**
       * Deregisters all descriptors for a given package ID.
       * @return whether any descriptors were deregistered (this should be true if any descriptor was previously
       * registered against it).  
       */
      boolean removeDescriptors(String packageID);

      /**
       * Clears the registry of all descriptor registrations.
       */
      void clear();
    }

    /**
     * A simple <code>Registry</code> implementation, in which {@link #getDescriptors(String) getDescriptors(String)}
     * can delegate to another <code>Registry</code> if no descriptors are locally registered against the given package
     * ID. This implementation does not prevent duplicate registrations.
     */
    public static class DelegatingRegistry implements Registry
    {
      protected Registry delegateRegistry;
      protected Map<String, List<Descriptor>> map = new HashMap<String, List<Descriptor>>();

      public DelegatingRegistry()
      {
        super();
      }

      public DelegatingRegistry(Registry delegateRegistry)
      {
        this.delegateRegistry = delegateRegistry;
      }

      public Collection<Descriptor> getDescriptors(String packageID)
      {
        List<Descriptor> descriptors = getDescriptors(packageID, false);
        return descriptors != null && !descriptors.isEmpty() ? new ArrayList<Descriptor>(descriptors) : delegatedGetDescriptors(packageID);
      }

      protected Collection<Descriptor> delegatedGetDescriptors(String packageID)
      {
        return delegateRegistry != null ? delegateRegistry.getDescriptors(packageID) : Collections.<Descriptor>emptyList();
      }

      public boolean addDescriptor(String packageID, Descriptor descriptor)
      {
        return getDescriptors(packageID, true).add(descriptor);
      }

      public boolean removeDescriptor(String packageID, Descriptor descriptor)
      {
        List<Descriptor> list = getDescriptors(packageID, false);
        if (list != null)
        {
          return list.remove(descriptor);
        }
        return false;
      }

      public boolean removeDescriptors(String packageID)
      {
        List<Descriptor> list = getDescriptors(packageID, false);
        if (list != null && !list.isEmpty())
        {
          map.remove(packageID);
          return true;
        }
        return false;
      }

      public void clear()
      {
        map.clear();
      }

      protected List<Descriptor> getDescriptors(String packageID, boolean forceCreate)
      {
        List<Descriptor> result = map.get(packageID);
        if (result == null && forceCreate)
        {
          result = new ArrayList<Descriptor>();
          map.put(packageID, result);
        }
        return result;
      }
    }
  }

  /**
   * Returns the <code>Generator</code> associated with this adapter factory.
   * 
   * @see #setGenerator(Generator)
   */
  Generator getGenerator();

  /**
   * Sets the <code>Generator</code> associated with this adapter factory.
   * 
   * @see #getGenerator()
   */
  void setGenerator(Generator generator);

  /**
   * Performs initialization for the given model-level input object. Typically, this involves setting
   * {@link Generator#getOptions() options} on the associated {@link Generator}.
   */
  void initialize(Object input);

  /**
   * Disposes this adapter factory and all of the adapters it has created.
   */
  void dispose();
}
