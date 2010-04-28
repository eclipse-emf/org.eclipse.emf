/**
 * <copyright> 
 *
 * Copyright (c) 2008 IBM Corporation and others.
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
 * $Id: IChildCreationExtender.java,v 1.1 2010/04/28 14:48:40 emerks Exp $
 */
package org.eclipse.emf.edit.provider;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * An interface used by objects that can extend the results of
 * {@link IEditingDomainItemProvider#getNewChildDescriptors(Object, EditingDomain, Object) IEditingDomainItemProvider.getNewChildDescriptors}.
 */
public interface IChildCreationExtender
{
  /**
   * Returns a collection of objects describing the children that can be added
   * under the given object in the editing domain.
   */
  Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain);
  
  /**
   * Returns a resource locator than can locate resources related to the child descriptors.
   */
  ResourceLocator getResourceLocator();

  /**
   * A descriptor can create a child creation extender.
   * They are used as the values in a {@link Descriptor.Registry registry}.
   */
  interface Descriptor
  {
    /**
     * Creates a child creation extender.
     * @return a new child creation extender.
     */
    IChildCreationExtender createChildCreationExtender();

    /**
     * A registry is an index that takes a namespace and maps it to a collection of {@link Descriptor descriptor}s.
     */
    interface Registry
    {
      /**
       * The global registry typically populated by plugin registration.
       */
      Registry INSTANCE = EMFEditPlugin.getChildCreationExtenderDescriptorRegistry();

      /**
       * Returns collection of descriptors that can create a child creation extenders.
       * @param namespace a key which will typically be the namespace of the package for which to create child creation extenders.
       * @return a collection of descriptors that can create a child creation extender.
       */
      Collection<Descriptor> getDescriptors(String namespace);

      /**
       *  A simple registry implementation that supports delegation.
       */
      class Impl extends HashMap<String, Collection<Descriptor>> implements Registry
      {
        private static final long serialVersionUID = 1L;

        /**
         * The delegate registry should lookup fail locally.
         */
        protected Registry delegateRegistry;

        /**
         * Creates an instance.
         * @param delegateRegistry <code>null</code> or a registration that should act as the delegate.
         */
        public Impl(Registry delegateRegistry)
        {
          this.delegateRegistry = delegateRegistry;
        }

        public Collection<Descriptor> getDescriptors(String namespace)
        {
          Collection<Descriptor> descriptor = get(namespace);
          return descriptor == null ?  delegatedGetDescriptors(namespace) : descriptor;
        }

        /**
         * This is called when local lookup fails.
         */
        protected Collection<Descriptor> delegatedGetDescriptors(String namespace)
        {
          if (delegateRegistry != null)
          {
            return delegateRegistry.getDescriptors(namespace);
          }

          return Collections.emptyList();
        }
      }
    }
  }
}