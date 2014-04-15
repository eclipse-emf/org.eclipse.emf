/**
 * Copyright (c) 2002-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.resource.impl;


import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.NotifierImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.util.NotifyingInternalEListImpl;


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
  protected EList<Resource> resources;

  /**
   * The registered adapter factories.
   * @see #getAdapterFactories
   */
  protected EList<AdapterFactory> adapterFactories;

  /**
   * The load options.
   * @see #getLoadOptions
   */
  protected Map<Object, Object> loadOptions;

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
  protected Map<URI, Resource> uriResourceMap;

  /**
   * A resource locator used for efficiently {@link #getResource(URI, boolean) locating} resources within the resource set.
   * @since 2.8
   */
  protected ResourceLocator resourceLocator;

  /**
   * Creates an empty instance.
   */
  public ResourceSetImpl()
  {
    super();
  }

  /**
   * Returns the map used to cache the resource {@link #getResource(URI, boolean) associated} with a specific URI.
   * @return the map used to cache the resource associated with a specific URI.
   * @see #setURIResourceMap
   */
  public Map<URI, Resource> getURIResourceMap()
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
  public void setURIResourceMap(Map<URI, Resource> uriResourceMap)
  {
    this.uriResourceMap = uriResourceMap;
  }

  /*
   * Javadoc copied from interface.
   */
  public EList<Resource> getResources()
  {
    if (resources == null)
    {
      resources = new ResourcesEList<Resource>();
    }
    return resources;
  }

  /*
   * Javadoc copied from interface.
   */
  public TreeIterator<Notifier> getAllContents()
  {
    TreeIterator<Notifier> result = EcoreUtil.getAllContents(Collections.singleton(this));
    result.next();
    return result;
  }

  /*
   * Javadoc copied from interface.
   */
  public EList<AdapterFactory> getAdapterFactories()
  {
    if (adapterFactories == null)
    {
      adapterFactories =
        new BasicEList<AdapterFactory>()
        {
          private static final long serialVersionUID = 1L;

          @Override
          protected boolean useEquals()
          {
            return false;
          }

          @Override
          protected boolean isUnique()
          {
            return true;
          }

          @Override
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
  public Map<Object, Object> getLoadOptions()
  {
    if (loadOptions == null)
    {
      loadOptions = new HashMap<Object, Object>();
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
   * This implementation simply calls {@link #createResource(URI, String) createResource(URI)}.
   * Clients may extend this as appropriate.
   * @param uri the URI of the resource to create.
   * @return a new resource.
   * @see #getResource(URI, boolean)
   */
  protected Resource demandCreateResource(URI uri)
  {
    return createResource(uri, ContentHandler.UNSPECIFIED_CONTENT_TYPE);
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
    catch (IOException exception)
    {
      handleDemandLoadException(resource, exception);
    }
  }

  /**
   * Handles the exception thrown during demand load
   * by recording it as an error diagnostic
   * and throwing a wrapping runtime exception.
   * @param resource the resource that threw an exception while loading.
   * @param exception the exception thrown from the resource while loading.
   * @see #demandLoadHelper(Resource)
   */
  protected void handleDemandLoadException(Resource resource, IOException exception) throws RuntimeException
  {
    final String location = resource.getURI() == null ? null : resource.getURI().toString();
    class DiagnosticWrappedException extends WrappedException implements Resource.Diagnostic
    {
      private static final long serialVersionUID = 1L;

      public DiagnosticWrappedException(Exception exception)
      {
        super(exception);
      }

      public String getLocation()
      {
        return location;
      }

      public int getColumn()
      {
        return 0;
      }

      public int getLine()
      {
        return 0;
      }
    }

    Exception cause = exception instanceof Resource.IOWrappedException ? (Exception)exception.getCause() : exception;
    DiagnosticWrappedException wrappedException = new DiagnosticWrappedException(cause);

    if (resource.getErrors().isEmpty())
    {
      resource.getErrors().add(exception instanceof Resource.Diagnostic ? (Resource.Diagnostic)exception : wrappedException);
    }

    throw wrappedException;
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
    if (resourceLocator != null)
    {
      return resourceLocator.getResource(uri, loadOnDemand);
    }

    Map<URI, Resource> map = getURIResourceMap();
    if (map != null)
    {
      Resource resource = map.get(uri);
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
    for (Resource resource : getResources())
    {
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
    return createResource(uri, null);
  }

  /*
   * Javadoc copied from interface.
   */
  public Resource createResource(URI uri, String contentType)
  {
    Resource.Factory resourceFactory = getResourceFactoryRegistry().getFactory(uri, contentType);
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
          @Override
          protected Resource.Factory delegatedGetFactory(URI uri, String contentTypeIdentifier)
          {
            return
              convert
                (getFactory
                  (uri,
                   Resource.Factory.Registry.INSTANCE.getProtocolToFactoryMap(),
                   Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap(),
                   Resource.Factory.Registry.INSTANCE.getContentTypeToFactoryMap(),
                   contentTypeIdentifier,
                   false));
          }

          @Override
          protected URIConverter getURIConverter()
          {
            return ResourceSetImpl.this.getURIConverter();
          }

          @Override
          protected Map<?, ?> getContentDescriptionOptions()
          {
            return new ExtensibleURIConverterImpl.OptionsMap(ContentHandler.OPTION_REQUESTED_PROPERTIES, CONTENT_TYPE_REQUESTED_PROPERTIES, getLoadOptions());
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
      uriConverter = new ExtensibleURIConverterImpl();
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
  protected class ResourcesEList<E extends Object & Resource> extends NotifyingInternalEListImpl<E> implements InternalEList<E>
  {
    private static final long serialVersionUID = 1L;

    @Override
    protected boolean isNotificationRequired()
    {
      return ResourceSetImpl.this.eNotificationRequired();
    }

    @Override
    protected Object [] newData(int capacity)
    {
      return new Resource [capacity];
    }

    @Override
    public Object getNotifier()
    {
      return ResourceSetImpl.this;
    }

    @Override
    public int getFeatureID()
    {
      return RESOURCE_SET__RESOURCES;
    }

    @Override
    protected boolean useEquals()
    {
      return false;
    }

    @Override
    protected boolean hasInverse()
    {
      return true;
    }

    @Override
    protected boolean isUnique()
    {
      return true;
    }

    @Override
    protected NotificationChain inverseAdd(E object, NotificationChain notifications)
    {
      Resource.Internal resource = (Resource.Internal)object;
      return resource.basicSetResourceSet(ResourceSetImpl.this, notifications);
    }

    @Override
    protected NotificationChain inverseRemove(E object, NotificationChain notifications)
    {
      Resource.Internal resource = (Resource.Internal)object;
      Map<URI, Resource> map = getURIResourceMap();
      if (map != null)
      {
        for (Iterator<Resource> i = map.values().iterator(); i.hasNext();)
        {
          if (resource == i.next())
          {
            i.remove();
          }
        }
      }
      return resource.basicSetResourceSet(null, notifications);
    }

    @Override
    public boolean contains(Object object)
    {
      return size <= 4 ? super.contains(object) : object instanceof Resource && ((Resource)object).getResourceSet() == ResourceSetImpl.this;
    }
  }

  /**
   * Returns a standard label with the list of resources.
   * @return the string form.
   */
  @Override
  public String toString()
  {
    return
      getClass().getName() +  '@' + Integer.toHexString(hashCode()) +
        " resources=" + (resources == null ? "[]" : resources.toString());
  }

  /**
   * A utility class for efficiently {@link ResourceSet#getResource(URI, boolean) locating resources} in a resource set.
   * It provides utility methods for delegating to a {@link ResourceSetImpl}'s protected methods
   * so that derived classes have access to the resource set's full set of protected methods.
   * @since 2.8
   */
  public static abstract class ResourceLocator
  {
    /**
     * The resource set for which this acts as an efficient lookup mechanism.
     */
    protected final ResourceSetImpl resourceSet;

    /**
     * The locator of the resource set at the time this locator is created.
     * The previous and next locators for a chain that's managed during construction and with dispose.
     * @see #dispose()
     * @see #previousResourceLocator
     */
    private ResourceLocator previousResourceLocator;

    /**
     * The locator created at the time this locator was the resource set's locator.
     * The previous and next locators for a chain that's managed during construction and with dispose.
     * @see #dispose()
     * @see #previousResourceLocator
     */
    private ResourceLocator nextResourceLocator;
    
    /**
     * Creates an instance for the given resource set, and sets the resource set's {@link ResourceSetImpl#resourceLocator resource locator}.
     */
    public ResourceLocator(ResourceSetImpl resourceSet)
    {
      // Cache the resource set.
      //
      this.resourceSet = resourceSet;

      // Hook up a chain of resource locators so we know the previous one to which we must delegate.
      //
      previousResourceLocator = resourceSet.resourceLocator;
      if (previousResourceLocator != null)
      {
        // That base one knows this one so if it is disposed, it can redirect this locator's previous resource locator to its previous resource locator,
        // thereby maintaining the integrity of the chain.
        //
        previousResourceLocator.nextResourceLocator = this;
      }

      // Ensure that the resource set refers back to this as its resource locator.
      //
      resourceSet.resourceLocator = this;
    }

    /**
     * The utility method used by a resource set for {@link ResourceSet#getResource(URI, boolean) locating} a resource.
     * It must implement the full logic needed to locate a resource,
     * including {@link ResourceSetImpl#delegatedGetResource(URI, boolean) delegated lookup}
     * and {@link ResourceSetImpl#demandCreateResource(URI) demand creation}.
     */
    public abstract Resource getResource(URI uri, boolean loadOnDemand);

    /**
     * Removes this resource locator from the chain of resource locators as if it never existed.
     * @since 2.10
     */
    protected void dispose()
    {
      if (previousResourceLocator == null)
      {
        // We must be the first in the chain.
        // Find the last locator in the "next" chain.
        //
        ResourceLocator lastLocator = nextResourceLocator;
        if (lastLocator != null)
        {
          while (lastLocator.nextResourceLocator != null)
          {
            lastLocator = lastLocator.nextResourceLocator;
          }
        }

        // The resource set must point to the last in the chain.
        //
        resourceSet.resourceLocator = lastLocator;
      }
      else
      {
        // We must be later in the chain, or even at the end, so make the previous point at our next, which might even be null.
        //
        previousResourceLocator.nextResourceLocator = nextResourceLocator;
      }

      if (nextResourceLocator == null)
      {
        // We're at the end of the chain, so the resource set should point at the one before us, which might even be null.
        //
        resourceSet.resourceLocator = previousResourceLocator;
      }
      else
      {
        // We're not at the end of the chain, so the resource set should point at the one before us, which might even be null.
        //
        nextResourceLocator.previousResourceLocator = previousResourceLocator;
      }
    }

    /**
     * Delegates to the {@link #resourceSet resource set}'s {@link ResourceSetImpl#getResource(URI, boolean)}
     * making sure the resource set's locator is temporarily null to ensure it doesn't delegate back circularly.
     * or, if the resource set has a resource locator at the time this one was created, delegates to its {@link ResourceLocator#basicGetResource(URI, boolean)}.
     * @since 2.10
     */
    protected Resource basicGetResource(URI uri, boolean loadOnDemand)
    {
      if (previousResourceLocator != null)
      {
        return previousResourceLocator.getResource(uri, loadOnDemand);
      }
      else
      {
        ResourceLocator resourceLocator = resourceSet.resourceLocator;
        resourceSet.resourceLocator = null;
        try
        {
          return resourceSet.getResource(uri, loadOnDemand);
        }
        finally
        {
          resourceSet.resourceLocator = resourceLocator;
        }
      }
    }

    /**
     * Delegates to the {@link #resourceSet resource set}'s {@link ResourceSetImpl#demandCreateResource(URI)}
     * or, if the resource set has a resource locator at the time this one was created, delegates to its {@link ResourceLocator#demandCreateResource(URI)}.
     */
    protected Resource demandCreateResource(URI uri)
    {
      if (previousResourceLocator != null)
      {
        return previousResourceLocator.demandCreateResource(uri);
      }
      else
      {
        return resourceSet.demandCreateResource(uri);
      }
    }

    /**
     * Delegates to the {@link #resourceSet resource set}'s {@link ResourceSetImpl#demandLoad(Resource)}
     * or, if the resource set has a resource locator at the time this one was created, delegates to its {@link ResourceLocator#demandCreateResource(URI)}.
     */
    protected void demandLoad(Resource resource) throws IOException
    {
      if (previousResourceLocator != null)
      {
        previousResourceLocator.demandLoad(resource);
      }
      else
      {
        resourceSet.demandLoad(resource);
      }
    }

    /**
     * Delegates to the {@link #resourceSet resource set}'s {@link ResourceSetImpl#demandLoadHelper(Resource)}
     * or, if the resource set has a resource locator at the time this one was created, delegates to its {@link ResourceLocator#demandLoadHelper(Resource)}.
     */
    protected void demandLoadHelper(Resource resource)
    {
      if (previousResourceLocator != null)
      {
        previousResourceLocator.demandLoadHelper(resource);
      }
      else
      {
        resourceSet.demandLoadHelper(resource);
      }
    }

    /**
     * Delegates to the {@link #resourceSet resource set}'s {@link ResourceSetImpl#handleDemandLoadException(Resource, IOException)}
     * or, if the resource set has a resource locator at the time this one was created, delegates to its {@link ResourceLocator#handleDemandLoadException(Resource, IOException)}.
     */
    protected void handleDemandLoadException(Resource resource, IOException exception) throws RuntimeException
    {
      if (previousResourceLocator != null)
      {
        previousResourceLocator.handleDemandLoadException(resource, exception);
      }
      else
      {
      resourceSet.handleDemandLoadException(resource, exception);
      }
    }

    /**
     * Delegates to the {@link #resourceSet resource set}'s {@link ResourceSetImpl#delegatedGetResource(URI, boolean)}
     * or, if the resource set has a resource locator at the time this one was created, delegates to its {@link ResourceLocator#delegatedGetResource(URI, boolean)}.
     */
    protected Resource delegatedGetResource(URI uri, boolean loadOnDemand)
    {
      if (previousResourceLocator != null)
      {
        return previousResourceLocator.delegatedGetResource(uri, loadOnDemand);
      }
      else
      {
        return resourceSet.delegatedGetResource(uri, loadOnDemand);
      }
    }
  }

  /**
   * An implementation of a {@link ResourceLocator} that maintains cached mappings for
   * the {@link MappedResourceLocator#normalizationMap normalized URIs}
   * and the resource set's {@link MappedResourceLocator#resourceMap resources}.
   * @since 2.8
   */
  public static class MappedResourceLocator extends ResourceLocator
  {
    /**
     * The cached {@link ResourceSet#getURIConverter() URI converter} used to calculate the cached {@link #normalizationMap normalization} and {@link #resourceMap resource} mappings.
     */
    protected URIConverter cachedURIConverter;

    /**
     * The cached {@link ExtensibleURIConverterImpl.URIMap.Internal#modificationCount() modification count} of the {@link #cachedURIConverter cached URI converter}.
     */
    protected int expectedModificationCount;

    /**
     * The cached mappings from URIs to their {@link URIConverter#normalize(URI) normalized} form.
     */
    protected Map<URI, URI> normalizationMap = new HashMap<URI, URI>();

    /**
     *  The cached mapping from normalized URIs to their corresponding resources.
     *  If there is more than one resource corresponding to the a normalized URI,
     *  the value will be a list of all those resources and they will be ordered in the same order as they appear in the {@link ResourceLocator#resourceSet resource set}
     *  with any resources not in the resource set, i.e., those located by {@link ResourceSetImpl#delegatedGetResource(URI, boolean) delegation},
     *  appearing after those found in the resource set itself.
     */
    protected Map<URI, EList<Resource>> resourceMap = new HashMap<URI, EList<Resource>>();

    /**
     * A {@link EContentAdapter content adapter} that listens to the {@link ResourceLocator#resourceSet resource set}
     * for {@link ResourceSet#getResources() resources} being added and removed,
     * as well as to the resources in the resource set for changes to the resource's {@link Resource#getURI() URI}.
     */
    public class ResourceAdapter extends EContentAdapter
    {
      /**
       * Respond to changes to the resource set's {@link ResourceSet#getResources() resources} and the resource's {@link Resource#getURI() URI}.
       */
      @Override
      public void notifyChanged(Notification notification)
      {
        // If the notification is for this resource set...
        //
        Object notifier = notification.getNotifier();
        if (notifier == resourceSet)
        {
          // If the notification is for changes to the list of resources...
          //
          if (notification.getFeatureID(ResourceSet.class) == ResourceSet.RESOURCE_SET__RESOURCES)
          {
            // If we've only moved a resource...
            //
            if (notification.getEventType() == Notification.MOVE)
            {
              // If we didn't build a new map while preparing the cached URI converter...
              //
              if (cacheURIConverter())
              {
                // Update mapping for the moved resource's normalized URI...
                //
                Resource resource = (Resource)notification.getNewValue();
                URI normalizedURI = normalizationMap.get(resource.getURI());
                if (normalizedURI != null)
                {
                  map(normalizedURI, resource);
                }
              }
            }
            else
            {
              // Handle the notification as normal, i.e., add/remove this content adapter to/from the added/removed resources.
              //
              handleContainment(notification);
            }
          }
        }
        // If the notification, which must be for a resource, is for the resource's URI...
        //
        else if (notification.getFeatureID(Resource.class) == Resource.RESOURCE__URI)
        {
          // If we didn't build a new map while preparing the cached URI converter...
          //
          if (cacheURIConverter())
          {
            // Remove the entry in the resource map for the old normalized URI.
            //
            URI oldNormalizedURI = normalizationMap.get(notification.getOldValue());
            if (oldNormalizedURI != null)
            {
              EList<Resource> value = resourceMap.get(oldNormalizedURI);
              if (value != null)
              {
                // If the list will end up being empty, remove the entire entry.
                //
                if (value.size() == 1)
                {
                  resourceMap.remove(oldNormalizedURI);
                }
                else
                {
                  // Otherwise, just remove the resource from the list.
                  //
                  value.remove(notifier);
                }
              }
            }

            // Add an entry to the resource map for the new normalized URI, if it's not null.
            //
            URI newURI = (URI)notification.getNewValue();
            if (newURI != null)
            {
              URI normalizedURI = cachedURIConverter.normalize(newURI);
              normalizationMap.put(newURI, normalizedURI);
              map(normalizedURI, (Resource)notifier);
            }
          }
        }
      }

      @Override
      protected void setTarget(Resource target)
      {
        basicSetTarget(target);

        // If we didn't build a new map while preparing the cached URI converter...
        //
        if (cacheURIConverter())
        {
          // Add an entry to the resource map for the new normalized URI, if it's not null.
          //
          URI uri = target.getURI();
          if (uri != null)
          {
            URI normalizedURI = cachedURIConverter.normalize(uri);
            normalizationMap.put(uri, normalizedURI);
            map(normalizedURI, target);
          }
        }
      }

      @Override
      protected void unsetTarget(Resource target)
      {
        basicUnsetTarget(target);

        // If we didn't build a new map while preparing the cached URI converter...
        //
        if (cacheURIConverter())
        {
          // Add an entry to the resource map for the new normalized URI, if it's not null.
          //
          URI uri = target.getURI();
          if (uri != null)
          {
            URI normalizedURI = normalizationMap.get(uri);
            EList<Resource> value = resourceMap.get(normalizedURI);
            if (value.size() == 1)
            {
              // If the list would become empty, just remove the entry entirely.
              //
              resourceMap.remove(normalizedURI);
            }
            else
            {
              // Otherwise remove the resource from the list.
              //
              value.remove(target);
            }
          }
        }
      }
    }

    /**
     * Creates an instance of the given resource set.
     */
    public MappedResourceLocator(ResourceSetImpl resourceSet)
    {
      super(resourceSet);

      // Add the specialized content adapter to the resource set's adapter list.
      //
      resourceSet.eAdapters().add(new ResourceAdapter());
    }

    /**
     * Creates a {@link #resourceMap resource map} entry for the resource with the given normalized URI.
     */
    protected void map(URI normalizedURI, Resource resource)
    {
      // If there is no entry yet...
      //
      EList<Resource> value = resourceMap.get(normalizedURI);
      if (value == null)
      {
        // Create a mapping to a singleton list.
        //
        resourceMap.put(normalizedURI, ECollections.singletonEList(resource));
      }
      else
      {
        // Otherwise, we need to add to the value list.
        // Create new list if the list is a singleton, and add the resource to it.
        //
        if (value.size() == 1)
        {
          value = new UniqueEList.FastCompare<Resource>(value);
        }
        value.add(resource);

        // Ensure that the resources are ordered as they are in the resource set's list of resources.
        // Note that resources not in the resource set (i.e., those found by calling delegatedGetResource) come last.
        //
        int count = 0;
        for (Resource r : resourceSet.getResources())
        {
          int index = value.indexOf(r);
          if (index != -1)
          {
            value.move(count++, index);
          }
        }

        // Update the map with the new value.
        //
        resourceMap.put(normalizedURI, value);
      }
    }

    /**
     * Builds entries for the {@link ResourceLocator#resourceSet resource set's} resource in the {@link #normalizationMap normalized map} and the {@link #resourceMap resource map}.
     */
    protected void buildMaps()
    {
      // Clear the maps
      //
      normalizationMap.clear();
      resourceMap.clear();

      // Iterate over the resources.
      //
      for (Resource resource : resourceSet.getResources())
      {
        URI uri = resource.getURI();
        if (uri != null)
        {
          // Compute the normalized URI, cache it, and map it to the resource.
          //
          URI normalizedURI = cachedURIConverter.normalize(uri);
          normalizationMap.put(uri,  normalizedURI);
          map(normalizedURI, resource);
        }
      }
    }

    /**
     * Determines {@link ExtensibleURIConverterImpl.URIMap.Internal#modificationCount() modification count} of the {@link #cachedURIConverter cached URI converter}.
     */
    protected int modificationCount()
    {
      return ((ExtensibleURIConverterImpl.URIMap.Internal)cachedURIConverter.getURIMap()).modificationCount();
    }

    /**
     * {@link #cachedURIConverter Caches} the URI converter,
     * checking that it's the same as the {@link ResourceLocator#resourceSet's} current {@link ResourceSet#getURIConverter() URI converter}
     * and that the {@link #modificationCount() modification count} of the {@link URIConverter#getURIMap() URI map}
     * is the same as the {@link #expectedModificationCount expected modification count},
     * {@link #buildMaps() building} normalization and resource maps, if necessary.
     */
    protected boolean cacheURIConverter()
    {
      // Get the current URI converter and check if it matches the cached one...
      //
      URIConverter uriConverter = resourceSet.getURIConverter();
      if (uriConverter != cachedURIConverter)
      {
        // If not, cache the new one, update the expected modification count, build new maps, and indicate that we've not reused the cache.
        //
        cachedURIConverter = uriConverter;
        expectedModificationCount = modificationCount();
        buildMaps();
        return false;
      }
      else
      {
        // Otherwise check the modification count to see if it matches the expected one...
        //
        int newModificationCount = modificationCount();
        if (newModificationCount != expectedModificationCount)
        {
          // If not, update the expected modification count, build new maps, and indicate that we've not reused the cache.
          //
          expectedModificationCount = newModificationCount;
          buildMaps();
          return false;
        }
      }

      // Indicate that we're reusing the cache.
      //
      return true;
    }

    @Override
    public Resource getResource(URI uri, boolean loadOnDemand)
    {
      // Ensure that the cached URI converter and maps are up-to-date.
      //
      cacheURIConverter();

      // Check if the normalization map contains a mapping for the URI.
      //
      URI normalizedURI = normalizationMap.get(uri);
      if (normalizedURI == null)
      {
        // If not, use the cached URI converter to normalize the URI and store the result for reuse.
        //
        normalizedURI = cachedURIConverter.normalize(uri);
        normalizationMap.put(uri, normalizedURI);
      }

      // Determine the list of resources associated with the normalized URI.
      //
      EList<Resource> value = resourceMap.get(normalizedURI);
      if (value != null)
      {
        // If there is one, it mustn't be empty, so get the first one.
        //
        Resource resource = value.get(0);

        // If we're demand loading and the resource isn't loaded yet, demand load it.
        //
        if (loadOnDemand && !resource.isLoaded())
        {
          demandLoadHelper(resource);
        }
        return resource;
      }

      // Try to locate the resource via delegation.
      //
      Resource delegatedResource = delegatedGetResource(uri, loadOnDemand);
      if (delegatedResource != null)
      {
        map(normalizedURI, delegatedResource);
        return delegatedResource;
      }

      // Failing actually locating the resource, if we're demand loading, then demand create one and load it.
      //
      if (loadOnDemand)
      {
        Resource resource = demandCreateResource(uri);
        if (resource == null)
        {
          throw new RuntimeException("Cannot create a resource for '" + uri + "'; a registered resource factory is needed");
        }
        demandLoadHelper(resource);
        return resource;
      }

      // If all that fails, there' no corresponding resource to return.
      //
      return null;
    }
  }
}
