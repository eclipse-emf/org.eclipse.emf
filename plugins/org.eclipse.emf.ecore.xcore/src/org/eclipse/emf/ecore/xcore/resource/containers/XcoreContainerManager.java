/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.resource.containers;


import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.containers.StateBasedContainerManager;
import org.eclipse.xtext.resource.impl.AbstractContainer;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


public class XcoreContainerManager extends StateBasedContainerManager
{
  @Override
  protected List<IContainer> getVisibleContainers(List<String> handles, IResourceDescriptions resourceDescriptions)
  {
    if (handles.isEmpty())
    {
      return Collections.emptyList();
    }
    else
    {
      List<IContainer> result = Lists.newArrayListWithExpectedSize(handles.size());
      for (String handle : handles)
      {
        IContainer container = createContainer(handle, resourceDescriptions);
        if (!container.isEmpty() || result.isEmpty())
        {
          IContainer xCoreContainer = 
            new FilteringContainer
              (container,
               new Predicate<IResourceDescription>()
                {
                  public boolean apply(IResourceDescription input)
                  {
                    return "xcore".equals(input.getURI().fileExtension());
                  }
                });
          if (!xCoreContainer.isEmpty())
          {
            result.add(xCoreContainer);
          }
          IContainer otherContainer = 
            new FilteringContainer
              (container,
               new Predicate<IResourceDescription>()
                {
                  public boolean apply(IResourceDescription input)
                  {
                    return !"xcore".equals(input.getURI().fileExtension());
                  }
                });
          if (!otherContainer.isEmpty() || result.isEmpty())
          {
            result.add(otherContainer);
          }
        }
      }
      return result;
    }
  }

  class FilteringContainer extends AbstractContainer
  {
    protected IContainer container;
    protected Predicate<IResourceDescription> predicate;

    public FilteringContainer(IContainer container, Predicate<IResourceDescription> predicate)
    {
      this.container = container;
      this.predicate = predicate;
    }

    public Iterable<IResourceDescription> getResourceDescriptions()
    {
      return Iterables.filter(container.getResourceDescriptions(), predicate);
    }
    
    @Override
    public boolean hasResourceDescription(URI uri)
    {
      return super.hasResourceDescription(uri);
    }
    
    @Override
    public IResourceDescription getResourceDescription(final URI uri) 
    {
      return 
        Iterables.find
          (getResourceDescriptions(), 
           new Predicate<IResourceDescription>() 
           {
             public boolean apply(IResourceDescription input) 
             {
              return uri.equals(input.getURI());
              }
           },
           null);
    }
  }
}
