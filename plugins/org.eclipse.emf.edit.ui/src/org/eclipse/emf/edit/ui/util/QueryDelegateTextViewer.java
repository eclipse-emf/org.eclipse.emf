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
 * $Id: QueryDelegateTextViewer.java,v 1.1 2010/12/21 18:00:24 khussey Exp $
 */
package org.eclipse.emf.edit.ui.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.swt.widgets.Composite;


/**
 * An interface for text viewers of query delegate expressions.
 * 
 * @since 2.7
 */
public interface QueryDelegateTextViewer extends ITextViewer
{
  /**
   * A factory for creating query delegate source viewers.
   */
  interface Factory
  {
    /**
     * Creates a query delegate for the specified <tt>parent</tt>.
     * 
     * @param parent the parent container for the text viewer
     * @param styles the styles to apply to the text viewer
     * @return a query delegate text viewer
     */
    QueryDelegateTextViewer createTextViewer(Composite parent, int styles);

    /**
     * A <code>Factory</code> wrapper that is used by the {@link Factory.Registry}.
     */
    interface Descriptor
    {
      Factory getFactory();
    }

    /**
     * A registry of query delegate text viewer factories.
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
   * Sets the context for the text viewer's query.
   * 
   * @param context the type of object against which the query will be executed
   */
  void setContext(EClassifier context);

  /**
   * Sets the parameters for the text viewer's query.
   * 
   * @param parameters a map of parameter names to types
   */
  void setParameters(Map<String, EClassifier> parameters);

}