/**
 * <copyright>
 *
 * Copyright (c) 2010 Ed Merks and others.
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
 * $Id: URIServiceImpl.java,v 1.1 2010/12/12 20:29:38 emerks Exp $
 */
package org.eclipse.emf.server.ecore.resource;

import java.util.Map;

import org.eclipse.emf.ecore.resource.URIService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class URIServiceImpl extends RemoteServiceServlet implements URIService
{
  private static final long serialVersionUID = 1L;
  
  static
  {
    // For initialization of this class so its static initializer block is called.
    //
    DatastoreUtil.class.getName();
  }

  public URIServiceImpl()
  {
    super();
  }

  public Map<?, ?> fetch(String uri, Map<?, ?> options)
  {
    return DatastoreUtil.fetch(uri, options);
  }

  public Map<?, ?> store(String uri, byte[] bytes, Map<?, ?> options)
  {
    return DatastoreUtil.store(uri, bytes, options);
  }

  public Map<?, ?> delete(String uri, Map<?, ?> options)
  {
    return DatastoreUtil.delete(uri, options);
  }
  
  public boolean exists(String uri, Map<?, ?> options)
  {
    return DatastoreUtil.exists(uri, options);
  }

  public WhiteList whiteList(WhiteList whiteList)
  {
    return null;
  }
}
