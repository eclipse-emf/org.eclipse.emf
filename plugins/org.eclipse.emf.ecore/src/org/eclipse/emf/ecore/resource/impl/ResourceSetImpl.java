/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ResourceSetImpl.java,v 1.6 2004/10/20 15:27:19 marcelop Exp $
 */
package org.eclipse.emf.ecore.resource.impl;


import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.NotifierImpl;
import org.eclipse.emf.common.notify.impl.NotifyingListImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * An extensible resource set implementation.
 * <p>
 * The following configuration and control mechanisms are provided:
 * <ul>
 *   <li><b>Resolve</b></li>
 *   <ul>
 *     <li>{@link #delegatedGetResource(URI, boolean)}</li>
 *     <li>{@link #getEObject(URI, boolean)}</li>
 *   </ul>
 *   <li><b>Demand</b></li>
 *   <ul>
 *     <li>{@link #demandCreateResource(URI)}</li>
 *     <li>{@link #demandLoad(Resource)}</li>
 *     <li>{@link #demandLoadHelper(Resource)}</li>
 *   </ul>
 * </ul> 
 * </p>
 */
public class ResourceSetImpl extends NotifierImpl implements ResourceSet
{
  /**
   * The contained resources.
   * @see #getResources
   */
  protected EList resources;

  /**
   * The registered adapter factories.
   * @see #getAdapterFactories
   */
  protected EList adapterFactories;

  /**
   * The load options.
   * @see #getLoadOptions
   */
  protected Map loadOptions;

  /**
   * The local resource factory registry.
   * @see #getResourceFactoryRegistry
   */
  protected Resource.Factory.Registry resourceFactoryRegistry;

  /**
   * The URI converter.
   * @see #getURIConverter
   */
  protected URIConverter uriConverter;

  /**
   * The local package registry.
   * @see #getPackageRegistry
   */
  protected EPackage.Registry packageRegistry;
  
  /**
   * A map to cache the resource associated with a specific URI.
   * @see #setURIResourceMap(Map)
   */
  protected Map uriResourceMap;

  /**
   * Creates an empty instance.
   */
  public ResourceSetImpl()
  {
  }
  
  /**
   * Returns the map used to cache the resource {@link #getResource(URI, boolean) associated} with a specific URI.
   * @return the map used to cache the resource associated with a specific URI.
   * @see #setURIResourceMap
   */
  public Map getURIResourceMap()
  {
    return uriResourceMap;
  }

  /**
   * Sets the map used to cache the resource associated with a specific URI.
   * This cache is only activated if the map is not <code>null</code>.  
   * The map will be lazily loaded by the {@link #getResource(URI, boolean) getResource} method.
   * It is up to the client to clear the cache when it becomes invalid, 
   * e.g., when the URI of a previously mapped resource is changed.
   * @param uriResourceMap the new map or <code>null</code>.
   * @see #getURIResourceMap
   */
  public void setURIResourceMap(Map uriResourceMap)
  {
    this.uriResourceMap = uriResourceMap;
  }
  
  /*
   * Javadoc copied from interface.
   */
  public EList getResources()
  {
    if (resources == null)
    {
      resources = new ResourcesEList();
    }
    return resources;
  }

  /*
   * Javadoc copied from interface.
   */
  public TreeIterator getAllContents()
  {
    TreeIterator result = EcoreUtil.getAllContents(Collections.singleton(this));
    result.next();
    return result;
  }

  /*
   * Javadoc copied from interface.
   */
  public EList getAdapterFactories()
  {
    if (adapterFactories == null)
    {
      adapterFactories = 
        new BasicEList()
        {
          protected boolean useEquals()
          {
            return false;
          }

          protected boolean isUnique()
          {
            return true;
          }

          protected Object [] newData(int capacity)
          {
            return new AdapterFactory [capacity];
          }
        };
    }
    return adapterFactories;
  }

  /*
   * Javadoc copied from interface.
   */
  public Map getLoadOptions()
  {
    if (loadOptions == null)
    {
      loadOptions = new HashMap();
    }

    return loadOptions;
  }

  /*
   * Javadoc copied from interface.
   */
  public EObject getEObject(URI uri, boolean loadOnDemand)
  {
    Resource resource = getResource(uri.trimFragment(), loadOnDemand);
    if (resource != null)
    {
      return resource.getEObject(uri.fragment());
    }
    else
    {
      return null;
    }
  }

  /**
   * Creates a new resource appropriate for the URI.
   * It is called by {@link #getResource(URI, boolean) getResource(URI, boolean)} 
   * when a URI that doesn't exist as a resource is demand loaded.
   * This implementation simply calls {@link #createResource(URI) createResource(URI)}.
   * Clients may extend this as appropriate.
   * @param uri the URI of the resource to create.
   * @return a new resource.
   * @see #getResource(URI, boolean)
   */
  protected Resource demandCreateResource(URI uri)
  {
    return createResource(uri);
  }

  /**
   * Loads the given resource.
   * It is called by {@link #demandLoadHelper(Resource) demandLoadHelper(Resource)} 
   * to perform a demand load.
   * This implementation simply calls <code>resource.</code>{@link Resource#load(Map) load}({@link #getLoadOptions() getLoadOptions}()).
   * Clients may extend this as appropriate.
   * @param resource  a resource that isn't loaded.
   * @exception IOException if there are serious problems loading the resource.
   * @see #getResource(URI, boolean)
   * @see #demandLoadHelper(Resource)
   */
  protected void demandLoad(Resource resource) throws IOException
  {
    resource.load(getLoadOptions());
  }
  
  /**
   * Demand loads the given resource using {@link #demandLoad(Resource)} 
   * and {@link WrappedException wraps} any {@link IOException} as a runtime exception. 
   * It is called by {@link #getResource(URI, boolean) getResource(URI, boolean)} 
   * to perform a demand load.
   * @param resource a resource that isn't loaded.
   * @see #demandLoad(Resource)
   */
  protected void demandLoadHelper(Resource resource)
  {
    try
    {
      demandLoad(resource);
    }
    catch (Resource.IOWrappedException exception)
    {
      throw new WrappedException(exception.getWrappedException());
    }
    catch (IOException exception)
    {
      throw new WrappedException(exception);
    }    
  }

  /**
   * Returns a resolved resource available outside of the resource set.
   * It is called by {@link #getResource(URI, boolean) getResource(URI, boolean)} 
   * after it has determined that the URI cannot be resolved 
   * based on the existing contents of the resource set.
   * This implementation looks up the URI in the {#getPackageRegistry() local} package registry.
   * Clients may extend this as appropriate.
   * @param uri the URI
   * @param loadOnDemand whether demand loading is required.
   */
  protected Resource delegatedGetResource(URI uri, boolean loadOnDemand)
  {
    EPackage ePackage = getPackageRegistry().getEPackage(uri.toString());
    return ePackage == null ? null : ePackage.eResource();
  }

  /*
   * Javadoc copied from interface.
   */
  public Resource getResource(URI uri, boolean loadOnDemand)
  {
    Map map = getURIResourceMap();
    if (map != null)
    {
      Resource resource = (Resource)map.get(uri);
      if (resource != null)
      {
        if (loadOnDemand && !resource.isLoaded())
        {
          demandLoadHelper(resource);
        }        
        return resource;
      }
    }
    
    URIConverter theURIConverter = getURIConverter();
    URI normalizedURI = theURIConverter.normalize(uri);
    for (Iterator i = getResources().iterator(); i.hasNext(); )
    {
      Resource resource = (Resource)i.next();
      if (theURIConverter.normalize(resource.getURI()).equals(normalizedURI))
      {
        if (loadOnDemand && !resource.isLoaded())
        {
          demandLoadHelper(resource);
        }
        
        if (map != null)
        {
          map.put(uri, resource);
        } 
        return resource;
      }
    }
    
    Resource delegatedResource = delegatedGetResource(uri, loadOnDemand);
    if (delegatedResource != null)
    {
      if (map != null)
      {
        map.put(uri, delegatedResource);
      }
      return delegatedResource;
    }

    if (loadOnDemand)
    {
      Resource resource = demandCreateResource(uri);
      if (resource == null)
      {
        throw new RuntimeException("Cannot create a resource for '" + uri + "'; a registered resource factory is needed");
      }

      demandLoadHelper(resource);

      if (map != null)
      {
        map.put(uri, resource);
      }      
      return resource;
    }

    return null;
  }

  /*
   * Javadoc copied from interface.
   */
  public Resource createResource(URI uri)
  {
    Resource.Factory resourceFactory = getResourceFactoryRegistry().getFactory(uri);
    if (resourceFactory != null)
    {
      Resource result = resourceFactory.createResource(uri);
      getResources().add(result);
      return result;
    }
    else
    {
      return null;
    }
  }

  /*
   * Javadoc copied from interface.
   */
  public Resource.Factory.Registry getResourceFactoryRegistry()
  {
    if (resourceFactoryRegistry == null)
    {
      resourceFactoryRegistry =
        new ResourceFactoryRegistryImpl()
        {
          public Resource.Factory delegatedGetFactory(URI uri)
          {
            return Resource.Factory.Registry.INSTANCE.getFactory(uri);
          }
        };
    }
    return resourceFactoryRegistry;
  }

  /*
   * Javadoc copied from interface.
   */
  public void setResourceFactoryRegistry(Resource.Factory.Registry resourceFactoryRegistry)
  {
    this.resourceFactoryRegistry = resourceFactoryRegistry;
  }

  /*
   * Javadoc copied from interface.
   */
  public URIConverter getURIConverter()
  {
    if (uriConverter == null)
    {
      uriConverter = new URIConverterImpl();
    }
    return uriConverter;
  }

  /*
   * Javadoc copied from interface.
   */
  public void setURIConverter(URIConverter uriConverter)
  {
    this.uriConverter = uriConverter;
  }

  /*
   * Javadoc copied from interface.
   */
  public EPackage.Registry getPackageRegistry()
  {
    if (packageRegistry == null)
    {
      packageRegistry = new EPackageRegistryImpl(EPackage.Registry.INSTANCE);
    }
    return packageRegistry;
  }

  /*
   * Javadoc copied from interface.
   */
  public void setPackageRegistry(EPackage.Registry packageRegistry)
  {
    this.packageRegistry = packageRegistry;
  }


  /**
   * A notifying list implementation for supporting {@link ResourceSet#getResources}.
   */
  protected class ResourcesEList extends NotifyingListImpl implements InternalEList
  {
    protected boolean isNotificationRequired()
    {
      return ResourceSetImpl.this.eNotificationRequired();
    }

    protected Object [] newData(int capacity)
    {
      return new Resource [capacity];
    }

    public Object getNotifier()
    {
      return ResourceSetImpl.this;
    }

    public int getFeatureID()
    {
      return RESOURCE_SET__RESOURCES;
    }

    protected boolean useEquals()
    {
      return false;
    }

    protected boolean hasInverse()
    {
      return true;
    }

    protected boolean isUnique()
    {
      return true;
    }

    protected NotificationChain inverseAdd(Object object, NotificationChain notifications)
    {
      Resource.Internal resource = (Resource.Internal)object;
      return resource.basicSetResourceSet(ResourceSetImpl.this, notifications);
    }

    protected NotificationChain inverseRemove(Object object, NotificationChain notifications)
    {
      Resource.Internal resource = (Resource.Internal)object;
      Map map = getURIResourceMap();
      if (map != null)
      {
        for (Iterator i = map.values().iterator(); i.hasNext();)
        {
          if (resource == i.next())
          {
            i.remove();
          }
        }
      }
      return resource.basicSetResourceSet(null, notifications);
    }

    public Iterator basicIterator()
    {
      return super.basicIterator();
    }

    public ListIterator basicListIterator()
    {
      return super.basicListIterator();
    }
  
    public ListIterator basicListIterator(int index)
    {
      return super.basicListIterator(index);
    }

    public List basicList()
    {
      return super.basicList();
    }
  }

  /**
   * Returns a standard label with the list of resources.
   * @return the string form.
   */
  public String toString()
  {
    return 
      getClass().getName() +  '@' + Integer.toHexString(hashCode()) + 
        " resources=" + (resources == null ? "[]" : resources.toString());
  }
}
