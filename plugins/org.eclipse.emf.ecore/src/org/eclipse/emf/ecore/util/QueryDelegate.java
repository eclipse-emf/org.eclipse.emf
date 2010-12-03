/**
 * <copyright>
 *
 * Copyright (c) 2010 Kenn Hussey and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Kenn Hussey - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: QueryDelegate.java,v 1.1 2010/12/03 01:23:45 khussey Exp $
 */
package org.eclipse.emf.ecore.util;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;


/**
 * An interface for delegating query execution.
 * 
 * @since 2.7
 */
public interface QueryDelegate
{

  /**
   * A factory for creating query delegates.
   */
  interface Factory
  {
    /**
     * Creates the query delegate for the specified <tt>expression</tt>. Implementors are expected to
     * parse the specified expression and throw a runtime exception in case of an error.
     * 
     * @param context the type of object against which the query can be executed
     * @param parameters a map of parameter names to types
     * @param expression the query text
     * @return a query delegate
     */
    QueryDelegate createQueryDelegate(EClassifier context, Map<String, EClassifier> parameters, String expression);

    /**
     * A <code>Factory</code> wrapper that is used by the {@link Factory.Registry}.
     */
    interface Descriptor
    {
      Factory getFactory();
    }

    /**
     * A registry of query delegate factories.
     */
    interface Registry extends Map<String, Object>
    {
      Registry INSTANCE = new Impl();

      Factory getFactory(String uri);

      class Impl extends HashMap<String, Object> implements Registry
      {
        private static final long serialVersionUID = 1L;

        @Override
        public Object get(Object key)
        {
          Object factory = super.get(key);
          if (factory instanceof Descriptor)
          {
            Descriptor factoryDescriptor = (Descriptor)factory;
            factory = factoryDescriptor.getFactory();
            put((String)key, factory);
            return factory;
          }
          else
          {
            return factory;
          }
        }

        public Factory getFactory(String uri)
        {
          return (Factory)get(uri);
        }
      }
    }
  }

  /**
   * Executes the query for the specified <tt>target</tt> object.
   * 
   * @param target the object on which to execute the query; this must be an instance of the context
   *    with which the delegate was created
   * @param arguments a map of parameter names to values; these must correspond to the parameters
   *    with which the delegate was created
   * @return the query's result
   * @throws InvocationTargetException in case of failure to execute the
   *    query, usually because of an exception
   */
  Object execute(Object target, Map<String, ?> arguments) throws InvocationTargetException;

}