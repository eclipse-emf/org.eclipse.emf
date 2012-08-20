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


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;


/**
 * A highly functional and extensible URI converter implementation.
 * <p>
 * This implementation provides seamless transparent Eclipse integration
 * by supporting the <code>platform:/resource</code> mechanism both inside of Eclipse and outside of Eclipse.
 * Furthermore, although the implementation imports
 * both {@link org.eclipse.core.runtime} and {@link org.eclipse.core.resources},
 * and hence requires the Eclipse libraries at development time,
 * the implementation does <b>not</b> require them at runtime.
 * Clients of this implementation must be cautious if they wish to maintain this platform neutral behaviour.
 * </p>
 */
public class ExtensibleURIConverterImpl implements URIConverter
{
  /**
   * A map that remaps URIs.
   */
  public interface URIMap extends Map<URI, URI>
  {
    /**
     * Returns the remapped URI, or the URI itself.
     * @param uri the URI to remap.
     * @return the remapped URI, or the URI itself.
     */
    URI getURI(URI uri);

    /**
     * An interface implemented by a {@link URIMappingRegistryImpl.URIMapImpl URI map} that tracks changes by incrementing the {@link #modificationCount() modification count}.
     * @see ResourceSetImpl.MappedResourceLocator#modificationCount()
     * @since 2.8
     */
    interface Internal
    {
      int modificationCount();
    }
  }

  protected static class URIHandlerList extends BasicEList<URIHandler>
  {
    private static final long serialVersionUID = 1L;

    public URIHandlerList()
    {
      super();
    }

    @Override
    protected boolean canContainNull()
    {
      return false;
    }

    @Override
    protected Object [] newData(int capacity)
    {
      return new URIHandler [capacity];
    }

    @Override
    public URIHandler [] data()
    {
      return (URIHandler[])data;
    }
  }

  protected URIHandlerList uriHandlers;

  protected static class ContentHandlerList extends BasicEList<ContentHandler>
  {
    private static final long serialVersionUID = 1L;

    public ContentHandlerList()
    {
      super();
    }

    @Override
    protected boolean canContainNull()
    {
      return false;
    }

    @Override
    protected Object [] newData(int capacity)
    {
      return new ContentHandler [capacity];
    }

    @Override
    public ContentHandler [] data()
    {
      return (ContentHandler[])data;
    }
  }

  protected ContentHandlerList contentHandlers;

  /**
   * The URI map.
   */
  protected URIMap uriMap;

  /**
   * Creates an instance.
   */
  public ExtensibleURIConverterImpl()
  {
    this(URIHandler.DEFAULT_HANDLERS, ContentHandler.Registry.INSTANCE.contentHandlers());
  }

  /**
   * Creates an instance.
   */
  public ExtensibleURIConverterImpl(Collection<URIHandler> uriHandlers, Collection<ContentHandler> contentHandlers)
  {
    getURIHandlers().addAll(uriHandlers);
    getContentHandlers().addAll(contentHandlers);
  }

  public EList<URIHandler> getURIHandlers()
  {
    if (uriHandlers == null)
    {
      uriHandlers = new URIHandlerList();
    }
    return uriHandlers;
  }

  public URIHandler getURIHandler(URI uri)
  {
    int size = uriHandlers.size();
    if (size > 0)
    {
      URIHandler[] data = uriHandlers.data();
      for (int i = 0; i < size; ++i)
      {
        URIHandler uriHandler = data[i];
        if (uriHandler.canHandle(uri))
        {
          return uriHandler;
        }
      }
    }
    throw new RuntimeException("There is no URIHandler to handle " + uri);
  }

  public EList<ContentHandler> getContentHandlers()
  {
    if (contentHandlers == null)
    {
      contentHandlers = new ContentHandlerList();
    }
    return contentHandlers;
  }

  public OutputStream createOutputStream(URI uri) throws IOException
  {
    return createOutputStream(uri, null);
  }

  static class OptionsMap implements Map<Object, Object>
  {
    private static final Object NO_KEY = new Object();

    protected Object key;
    protected Object value;
    protected Map<?, ?> options;
    protected Map<?, ?> defaultOptions;
    protected Map<Object, Object> mergedMap;

    public OptionsMap(Object key, Object value, Map<?, ?> options)
    {
      this(key, value, options, null);
    }

    public OptionsMap(Map<?, ?> options, Map<?, ?> defaultOptions)
    {
      this(NO_KEY, null, options, defaultOptions);
    }

    public OptionsMap(Object key, Object value, Map<?, ?> options, Map<?, ?> defaultOptions)
    {
      this.options = options;
      this.defaultOptions = defaultOptions;
      this.key = key;
      this.value = value;
    }

    protected Map<Object, Object> mergedMap()
    {
      if (mergedMap == null)
      {
        mergedMap =
          new LinkedHashMap<Object, Object>
          ((options == null ? 0 : options.size()) +
             (defaultOptions == null ? 0 : defaultOptions.size()) +
             (key == NO_KEY ? 0 : 1));
        if (defaultOptions != null)
        {
          mergedMap.putAll(defaultOptions);
        }
        if (options != null)
        {
          mergedMap.putAll(options);
        }
        if (key != NO_KEY)
        {
          mergedMap.put(key, value);
        }
      }
      return mergedMap;
    }

    public void clear()
    {
      throw new UnsupportedOperationException();
    }

    public boolean containsKey(Object key)
    {
      return
        mergedMap != null ?
          mergedMap.containsKey(key) :
          this.key == key ||
            this.key.equals(key) ||
            options != null && options.containsKey(key) ||
            defaultOptions != null && defaultOptions.containsKey(key);
    }

    public boolean containsValue(Object value)
    {
      return mergedMap().containsValue(value);
    }

    public Set<Map.Entry<Object, Object>> entrySet()
    {
      return mergedMap().entrySet();
    }

    public Object get(Object key)
    {
      return
        mergedMap != null ?
          mergedMap.get(key) :
          this.key == key ||
            this.key.equals(key) ?
              value :
              options != null && options.containsKey(key) ?
                options.get(key) :
                defaultOptions == null ?
                  null :
                  defaultOptions.get(key);
    }

    public boolean isEmpty()
    {
      return 
        mergedMap != null ? 
          mergedMap.isEmpty() : 
          key != NO_KEY || 
            options != null && !options.isEmpty() || 
            defaultOptions != null && !defaultOptions.isEmpty();
    }

    public Set<Object> keySet()
    {
      return mergedMap().keySet();
    }

    public Object put(Object key, Object value)
    {
      throw new UnsupportedOperationException();
    }

    public void putAll(Map<? extends Object, ? extends Object> t)
    {
      throw new UnsupportedOperationException();
    }

    public Object remove(Object key)
    {
      throw new UnsupportedOperationException();
    }

    public int size()
    {
      return mergedMap().size();
    }

    public Collection<Object> values()
    {
      return mergedMap().values();
    }

    @Override
    public int hashCode()
    {
      return mergedMap().hashCode();
    }

    @Override
    public boolean equals(Object o)
    {
      return mergedMap().equals(o);
    }
  }

  public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException
  {
    URI normalizedURI = normalize(uri);
    return getURIHandler(normalizedURI).createOutputStream(normalizedURI, new OptionsMap(OPTION_URI_CONVERTER, this, options));
  }

  public InputStream createInputStream(URI uri) throws IOException
  {
    return createInputStream(uri, null);
  }

  public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException
  {
    URI normalizedURI = normalize(uri);
    return getURIHandler(normalizedURI).createInputStream(normalizedURI, new OptionsMap(OPTION_URI_CONVERTER, this, options));
  }

  public void delete(URI uri, Map<?, ?> options) throws IOException
  {
    URI normalizedURI = normalize(uri);
    getURIHandler(normalizedURI).delete(normalizedURI, new OptionsMap(OPTION_URI_CONVERTER, this, options));
  }

  public Map<String, ?> contentDescription(URI uri, Map<?, ?> options) throws IOException
  {
    URI normalizedURI = normalize(uri);
    return getURIHandler(normalizedURI).contentDescription(normalizedURI, new OptionsMap(OPTION_URI_CONVERTER, this, options));
  }

  public boolean exists(URI uri, Map<?, ?> options)
  {
    URI normalizedURI = normalize(uri);
    return getURIHandler(normalizedURI).exists(normalizedURI, new OptionsMap(OPTION_URI_CONVERTER, this, options));
  }

  public Map<String, ?> getAttributes(URI uri, Map<?, ?> options)
  {
    URI normalizedURI = normalize(uri);
    return getURIHandler(normalizedURI).getAttributes(normalizedURI, new OptionsMap(OPTION_URI_CONVERTER, this, options));
  }

  public void setAttributes(URI uri, Map<String, ?> attributes, Map<?, ?> options) throws IOException
  {
    URI normalizedURI = normalize(uri);
    getURIHandler(normalizedURI).setAttributes(normalizedURI, attributes, new OptionsMap(OPTION_URI_CONVERTER, this, options));
  }

  private static IWorkspaceRoot workspaceRoot = EcorePlugin.getWorkspaceRoot();

  /**
   * Returns the normalized form of the URI.
   * <p>
   * This implementation does precisely and only the {@link URIConverter#normalize typical} thing.
   * It calls itself recursively so that mapped chains are followed.
   * </p>
   * @param uri the URI to normalize.
   * @return the normalized form.
   * @see org.eclipse.emf.ecore.plugin.EcorePlugin#getPlatformResourceMap
   */
  public URI normalize(URI uri)
  {
    String fragment = uri.fragment();
    String query = uri.query();
    URI result = getInternalURIMap().getURI(uri.trimFragment().trimQuery());
    String scheme = result.scheme();
    if (scheme == null)
    {
      if (workspaceRoot != null)
      {
        if (result.hasAbsolutePath())
        {
          result = URI.createPlatformResourceURI(result.toString(), false);
        }
      }
      else
      {
        if (result.hasAbsolutePath())
        {
          result = URI.createURI("file:" + result);
        }
        else
        {
          result = URI.createFileURI(new File(result.toString()).getAbsolutePath());
        }
      }
    }
    if (fragment != null)
    {
      result = result.appendFragment(fragment);
    }
    if (query != null)
    {
      result = result.appendQuery(query);
    }
    if (result.equals(uri))
    {
      return uri;
    }
    else
    {
      return normalize(result);
    }
  }

  /*
   * Javadoc copied from interface.
   */
  public Map<URI, URI> getURIMap()
  {
    return getInternalURIMap();
  }

  /**
   * Returns the internal version of the URI map.
   * @return the internal version of the URI map.
   */
  protected URIMap getInternalURIMap()
  {
    if (uriMap == null)
    {
      URIMappingRegistryImpl mappingRegistryImpl =
        new URIMappingRegistryImpl()
        {
          private static final long serialVersionUID = 1L;

          @Override
          protected URI delegatedGetURI(URI uri)
          {
            return URIMappingRegistryImpl.INSTANCE.getURI(uri);
          }

          /**
           * Specialize the modification count to include the modification count for this map
           * as well as the one for the map to which this one {@link URIMappingRegistryImpl#INSTANCE delegates}.
           * @since 2.8
           */
          @Override
          protected int modificationCount()
          {
            return super.modificationCount() + URIMappingRegistryImpl.INSTANCE.modificationCount();
          }
        };

      uriMap = (URIMap)mappingRegistryImpl.map();
    }

    return uriMap;
  }
}
