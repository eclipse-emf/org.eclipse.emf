/*******************************************************************************
 * Copyright (c) 2013 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.emf.ecore.xcore.scoping.types;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.collect.Maps;

public class CachingTypeScope extends AbstractXcoreScope
{
  private final AbstractXcoreScope parent;

  private final Map<QualifiedName, IEObjectDescription> cache;

  public CachingTypeScope(AbstractXcoreScope parent)
  {
    this.parent = parent;
    this.cache = Maps.newHashMapWithExpectedSize(50);
  }

  @Override
  public IEObjectDescription getSingleElement(QualifiedName name)
  {
    IEObjectDescription cached = cache.get(name);
    if (cached == null)
    {
      if (cache.containsKey(name))
      {
        return null;
      }
      cached = parent.getSingleElement(name);
      cache.put(name, cached);
    }
    return cached;
  }

  @Override
  public Iterable<IEObjectDescription> getElements(QualifiedName name)
  {
    IEObjectDescription element = getSingleElement(name);
    return element == null ? Collections.<IEObjectDescription>emptyList() : Collections.singletonList(element);
  }

  @Override
  protected void doGetElements(JvmType type, List<IEObjectDescription> result)
  {
    parent.doGetElements(type, result);
  }
}