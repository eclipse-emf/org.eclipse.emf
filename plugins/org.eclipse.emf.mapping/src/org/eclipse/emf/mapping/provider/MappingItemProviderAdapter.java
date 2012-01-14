/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.provider;


import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;


/**
 * This is the base class for all our providers, in case we need to add common function across all our providers.
 */
public class MappingItemProviderAdapter extends ItemProviderAdapter 
{
  /**
   * This creates and instance from an adapter factory and a domain notifier.
   */
  protected MappingItemProviderAdapter(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }
}
