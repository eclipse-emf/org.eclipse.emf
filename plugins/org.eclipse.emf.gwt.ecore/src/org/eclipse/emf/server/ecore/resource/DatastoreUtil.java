/**
 * <copyright> 
 *
 * Copyright (c) 2010-2011 Ed Merks and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Ed Merks - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: DatastoreUtil.java,v 1.3 2011/05/12 22:15:58 emerks Exp $
 */
package org.eclipse.emf.server.ecore.resource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;

final public class DatastoreUtil
{
  public static final String OPTION_SESSION = "SESSION";

  static
  {
    EcorePlugin.DEFAULT_URI_HANDLERS.add(new DatastoreURIHandlerImpl());
    EcorePlugin.DEFAULT_URI_HANDLERS.add(new URIHandlerImpl());
    
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put
      (Resource.Factory.Registry.DEFAULT_EXTENSION,
       new ResourceFactoryImpl()
       {
         @Override
         public Resource createResource(URI uri) 
         {
           return new BinaryResourceImpl(uri);
         }
       });
  }
  
  private DatastoreUtil()
  {
    // Do nothing.
  }

  public static class ResourceFilter
  {
    public boolean isIncluded(Entity resourceEntity)
    {
      String uri = (String)resourceEntity.getProperty("uri");
      return isIncluded(uri);
    }

    public boolean isIncluded(String resourceURI)
    {
      return true;
    }
  }

  public static List<Resource> getResources(ResourceSet resourceSet, ResourceFilter resourceFilter) throws IOException
  {
    return getResources(resourceSet, resourceFilter, null);
  }

  public static List<Resource> getResources(ResourceSet resourceSet, ResourceFilter resourceFilter, String sessionID) throws IOException
  {
    List<Resource> result = new ArrayList<Resource>();
    DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
    Entity session = getSession(datastoreService, sessionID);
    Key sessionKey = session == null ? null : session.getKey();
    Query query = new Query("org.eclipse.emf.ecore.resource", sessionKey);
    PreparedQuery preparedQuery = datastoreService.prepare(query);
    for (Entity entity : preparedQuery.asIterable())
    {
      if (resourceFilter.isIncluded(entity) && (sessionKey != null || entity.getParent() == null))
      {
        URI uri = URI.createURI((String)entity.getProperty("uri"));
        Resource resource = resourceSet.getResource(uri, false);
        if (resource == null)
        {
          resource = resourceSet.createResource(uri);
        }
        if (!resource.isLoaded())
        {
          resource.load(new ByteArrayInputStream(getBytes(entity, datastoreService)), resourceSet.getLoadOptions());
          resource.setTimeStamp((Long)entity.getProperty("timestamp"));
        }
        result.add(resource);
      }
    }
    return result;
  }

  public static List<URI> getResources()
  {
    return getResources(null);
  }

  public static List<URI> getResources(String sessionID)
  {
    List<URI> result = new ArrayList<URI>();
    DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
    Entity session = getSession(datastoreService, sessionID);
    Key sessionKey = session == null ? null : session.getKey();
    Query query = new Query("org.eclipse.emf.ecore.resource", sessionKey);
    PreparedQuery preparedQuery = datastoreService.prepare(query);
    for (Entity entity : preparedQuery.asIterable())
    {
      if (sessionKey != null || entity.getKey() == null)
      {
        URI uri = URI.createURI((String)entity.getProperty("uri"));
        result.add(uri);
      }
    }
    return result;
  }
  
  public static Entity getEntity(Key sessionKey, PreparedQuery query)
  {
    if (sessionKey == null)
    {
      for (Entity entity : query.asIterable())
      {
        if (entity.getParent() == null)
        {
          return entity;
        }
      }
      return null;
    }
    else
    {
      return query.asSingleEntity();
    }
  }

  public static Map<?, ?> fetch(String uri, Map<?, ?> options)
  {
    @SuppressWarnings("unchecked")
    Map<Object, Object> result = (Map<Object, Object>)options.get(URIConverter.OPTION_RESPONSE);

    DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
    Entity session = getSession(datastoreService, (String)options.get(OPTION_SESSION));
    Key sessionKey = session == null ? null : session.getKey();
    Query query = new Query("org.eclipse.emf.ecore.resource", sessionKey);
      
    if (uri.equals("datastore:/"))
    {
      Resource resource = new BinaryResourceImpl(URI.createURI(uri));
      EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
      resource.getContents().add(eAnnotation);
      PreparedQuery preparedQuery = datastoreService.prepare(query);
      for (Entity entity : preparedQuery.asIterable())
      {
        if (sessionKey != null || entity.getParent() == null)
        {
          eAnnotation.getDetails().put(entity.getProperty("uri").toString(), entity.getProperty("timestamp").toString());
        }
      }
      ByteArrayOutputStream out = new ByteArrayOutputStream();
      try
      {
        resource.save(out, null);
      }
      catch (IOException exception)
      {
        // TODO
      }
      result.put(URIConverter.RESPONSE_RESULT, out.toByteArray());
    }
    else
    {
      // Transaction transaction = datastoreService.beginTransaction();
  
      // Find the existing entity, if any.
      //
      query.addFilter("uri", Query.FilterOperator.EQUAL, uri);
      PreparedQuery preparedQuery = datastoreService.prepare(query);
      Entity entity = getEntity(sessionKey, preparedQuery);
      if (entity != null)
      {
        result.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, entity.getProperty("timestamp"));
        byte[] bytes = getBytes(entity, datastoreService);
        result.put(URIConverter.RESPONSE_RESULT, bytes);
      }
      else
      {
        result.put(URIConverter.RESPONSE_RESULT, null);
      }
    }
    
    // transaction.rollback();
    return options;
  }
  
  public static byte[] getBytes(Entity entity, DatastoreService datastoreService)
  {
    if (entity.hasProperty("content"))
    {
      Blob blob = (Blob)entity.getProperty("content");
      return blob.getBytes();
    }
    else
    {
      Query contentBlobsQuery = new Query("ChildBlob", entity.getKey());
      PreparedQuery preparedContentBlobsQuery = datastoreService.prepare(contentBlobsQuery);
      Map<Long, byte[]> contents = new TreeMap<Long, byte[]>();
      int length = 0;
      for (Entity contentBlobEntity : preparedContentBlobsQuery.asIterable())
      {
        byte[] childBytes = ((Blob)contentBlobEntity.getProperty("value")).getBytes();
        contents.put((Long)contentBlobEntity.getProperty("index"), childBytes);
        length += childBytes.length;
      }
      byte[] bytes = new byte[length];
      int offset = 0;
      for (byte[] childBytes : contents.values())
      {
        System.arraycopy(childBytes, 0, bytes, offset, childBytes.length);
        offset += childBytes.length;
      }
      return bytes;
    }
  }

  protected static final int MAX_BLOB_SIZE = 1000000;
  
  public static Entity getSession(DatastoreService datastoreService, String session)
  {
    Entity entity = null;
    if (session != null)
    {
      Query query = new Query("org.eclipse.emf.ecore.resource.session");
      query.addFilter("id", Query.FilterOperator.EQUAL, session);
      PreparedQuery preparedQuery = datastoreService.prepare(query);
      entity = preparedQuery.asSingleEntity();
      if (entity == null)
      {
        entity =  new Entity("org.eclipse.emf.ecore.resource.session");
        entity.setProperty("id", session);
        datastoreService.put(entity);
      }
    }
    return entity;
  }

  public static Map<?, ?> store(String uri, byte[] bytes, Map<?, ?> options)
  {
    @SuppressWarnings("unchecked")
    Map<Object, Object> result = (Map<Object, Object>)options.get(URIConverter.OPTION_RESPONSE);

    DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
    Transaction transaction = datastoreService.beginTransaction();

    Entity session = getSession(datastoreService, (String)options.get(OPTION_SESSION));
    Key sessionKey = session == null ? null : session.getKey();
    Query query = new Query("org.eclipse.emf.ecore.resource", sessionKey);
    query.addFilter("uri", Query.FilterOperator.EQUAL, uri);
    PreparedQuery preparedQuery = datastoreService.prepare(query);
    Entity entity = getEntity(sessionKey, preparedQuery);
    Long expectedTimestamp = (Long)options.get(URIConverter.OPTION_UPDATE_ONLY_IF_TIME_STAMP_MATCHES);
    if (entity == null)
    {
      // Return early without a timestamp in the response to indicate failure.
      //
      if (expectedTimestamp != null)
      {
        transaction.rollback();
        result.put(URIConverter.RESPONSE_RESULT, new IOException("The timestamp for '" + uri + "' doesn't match the expected time stamp"));
        return options;
      }
      // Create the entity if it doesn't exist.
      //
      entity =  session == null? new Entity("org.eclipse.emf.ecore.resource") : new Entity("org.eclipse.emf.ecore.resource", session.getKey());
      entity.setProperty("uri", uri);
    }
    // If it doesn't have a content property, it must have content blob children.
    //
    else 
    {
      // Return early without a timestamp in the response to indicate failure.
      //
      if (expectedTimestamp != null && expectedTimestamp.intValue() != ((Long)entity.getProperty("timestamp")).intValue())
      {
        transaction.rollback();
        result.put(URIConverter.RESPONSE_RESULT, new IOException("The timestamp for '" + uri + "' doesn't match the expected time stamp"));
        return options;
      }
      if (entity.hasProperty("content"))
      {
        entity.removeProperty("content");
      }
      else
      {
        // Delete all those children.
        //
        Query contentBlobsQuery = new Query("ChildBlob", entity.getKey());
        PreparedQuery preparedContentBlobsQuery = datastoreService.prepare(transaction, contentBlobsQuery);
        for (Entity contentBlobEntity : preparedContentBlobsQuery.asIterable())
        {
          datastoreService.delete(transaction, contentBlobEntity.getKey());
        }
      }
    }
    
    // Keep a timestamp property
    //
    long timestamp = System.currentTimeMillis();
    entity.setUnindexedProperty("timestamp", timestamp);
    
    // Determine if the bytes need to be factored into child blob entities.
    //
    if (bytes.length > MAX_BLOB_SIZE)
    {
      Key entityKey = datastoreService.put(transaction, entity);
      for (int i = 0, offset = 0; offset < bytes.length; offset += MAX_BLOB_SIZE, ++i)
      {
        int length = Math.min(bytes.length - offset, MAX_BLOB_SIZE);
        byte [] childBytes = new byte[length];
        System.arraycopy(bytes, offset, childBytes, 0, length);
        Entity childBlobEntity = new Entity("ChildBlob", entityKey);
        childBlobEntity.setProperty("index", i);
        childBlobEntity.setUnindexedProperty("value", new Blob(childBytes));
        datastoreService.put(transaction, childBlobEntity);
      }
    }
    else
    {
      entity.setUnindexedProperty("content", new Blob(bytes));
      datastoreService.put(transaction, entity);
    }


    transaction.commit();
    
    result.put(URIConverter.RESPONSE_TIME_STAMP_PROPERTY, timestamp);
    result.put(URIConverter.RESPONSE_RESULT, null);
    return options;
  }

  public static Map<?, ?> delete(String uri, Map<?, ?> options)
  {
    @SuppressWarnings("unchecked")
    Map<Object, Object> result = (Map<Object, Object>)options.get(URIConverter.OPTION_RESPONSE);
    DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();

    Entity session = getSession(datastoreService, (String)options.get(OPTION_SESSION));
    Key sessionKey = session == null ? null : session.getKey();
    Query query = new Query("org.eclipse.emf.ecore.resource", sessionKey);
    query.addFilter("uri", Query.FilterOperator.EQUAL, uri);
    PreparedQuery preparedQuery = datastoreService.prepare(query);
    Entity entity = getEntity(sessionKey, preparedQuery);
    if (entity != null)
    {
      Long expectedTimestamp = (Long)options.get(URIConverter.OPTION_UPDATE_ONLY_IF_TIME_STAMP_MATCHES);
      if (expectedTimestamp != null && expectedTimestamp.longValue() != ((Long)entity.getProperty("timestamp")).longValue())
      {
        result.put(URIConverter.RESPONSE_RESULT, new IOException("The timestamp for '" + uri + "' doesn't match the expected time stamp"));
        return options;
      }

      Transaction transaction = datastoreService.beginTransaction();
      if (!entity.hasProperty("content"))
      {
        // Delete all those children.
        //
        Query contentBlobsQuery = new Query("ChildBlob", entity.getKey());
        PreparedQuery preparedContentBlobsQuery = datastoreService.prepare(transaction, contentBlobsQuery);
        for (Entity contentBlobEntity : preparedContentBlobsQuery.asIterable())
        {
          datastoreService.delete(transaction, contentBlobEntity.getKey());
        }
      }
      datastoreService.delete(transaction, entity.getKey());
      transaction.commit();
      result.put(URIConverter.RESPONSE_RESULT, null);
    }
    else
    {
      result.put(URIConverter.RESPONSE_RESULT, new IOException("No stream found for '" + uri + "'"));
    }
    return options;
  }

  public static boolean exists(String uri, Map<?, ?> options)
  {
    DatastoreService datastoreService = DatastoreServiceFactory.getDatastoreService();
    Entity session = getSession(datastoreService, (String)options.get(OPTION_SESSION));
    Key sessionKey = session == null ? null : session.getKey();
    Query query = new Query("org.eclipse.emf.ecore.resource", sessionKey);
    query.addFilter("uri", Query.FilterOperator.EQUAL, uri);
    PreparedQuery preparedQuery = datastoreService.prepare(query);
    Entity entity = getEntity(sessionKey, preparedQuery);
    return entity != null;
  }
}
