/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: GeneratorAdapterFactory.java,v 1.1 2006/05/01 10:26:04 davidms Exp $
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
 * @since 2.2.0
 */
public interface GeneratorAdapterFactory extends AdapterFactory
{
  public interface Descriptor
  {
    GeneratorAdapterFactory createAdapterFactory();

    public interface Registry
    {
      public static final Registry INSTANCE = new DelegatingRegistry();

      Collection getDescriptors(String packageID);
      boolean addDescriptor(String packageID, Descriptor descriptor);
      boolean removeDescriptor(String packageID, Descriptor descriptor);
      boolean removeDescriptors(String packageID);
      void clear();
    }
    
    public static class DelegatingRegistry implements Registry
    {
      protected Registry delegateRegistry;
      protected Map map = new HashMap();

      public DelegatingRegistry()
      {
      }

      public DelegatingRegistry(Registry delegateRegistry)
      {
        this.delegateRegistry = delegateRegistry;
      }

      public Collection getDescriptors(String packageID)
      {
        List descriptors = getDescriptors(packageID, false);
        return descriptors != null ? new ArrayList(descriptors) : delegatedGetDescriptors(packageID);
      }

      protected Collection delegatedGetDescriptors(String packageID)
      {
        return delegateRegistry != null ? delegateRegistry.getDescriptors(packageID) : Collections.EMPTY_LIST;
      }

      public boolean addDescriptor(String packageID, Descriptor descriptor)
      {
        return getDescriptors(packageID, true).add(descriptor);
      }

      public boolean removeDescriptor(String packageID, Descriptor descriptor)
      {
        List list = getDescriptors(packageID, false);
        if (list != null)
        {
          return list.remove(descriptor);
        }
        return false;
      }

      public boolean removeDescriptors(String packageID)
      {
        List list = getDescriptors(packageID, false);
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

      protected List getDescriptors(String packageID, boolean forceCreate)
      {
        List result = (List)map.get(packageID);
        if (result == null && forceCreate)
        {
          result = new ArrayList(); //DMS? new UniqueEList.FastCompare();
          map.put(packageID, result);
        }
        return result;
      }
    }
  }

  Generator getGenerator();
  void setGenerator(Generator generator);
  void initialize(Object input);
  void dispose();
}
