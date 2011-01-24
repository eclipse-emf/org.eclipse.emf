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
 * $Id: URIServiceImpl.java,v 1.2 2011/01/24 23:34:17 emerks Exp $
 */
package org.eclipse.emf.server.ecore.resource;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.resource.URIService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class URIServiceImpl extends RemoteServiceServlet implements URIService
{
  private static final long serialVersionUID = 1L;

  private static class SafeSimpleDateFormat extends SimpleDateFormat implements EFactoryImpl.InternalEDateTimeFormat
  {
    private static final long serialVersionUID = 1L;

    public SafeSimpleDateFormat(String pattern)
    {
      super(pattern, Locale.ENGLISH);
    }

    @Override
    public synchronized Date parse(String source)
    {
      try
      {
        return super.parse(source);
      }
      catch (ParseException parseException)
      {
        throw new RuntimeException(parseException);
      }
    }

    @Override
    public synchronized StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition)
    {
      return super.format(date, toAppendTo, fieldPosition);
    }
  }

  static
  {
    // For initialization of this class so its static initializer block is called.
    //
    DatastoreUtil.class.getName();

    // The client side implementation for date/time formatting doesn't work on the server, so use the regular Java runtime support for it on the server.
    //
    EFactoryImpl.EDATE_FORMATS =
      new EFactoryImpl.InternalEDateTimeFormat[]
      {
        new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSSZ"),
        new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSS"),
        new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"),
        new SafeSimpleDateFormat("yyyy-MM-dd'T'HH:mm"),
        new SafeSimpleDateFormat("yyyy-MM-dd")
      };
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
