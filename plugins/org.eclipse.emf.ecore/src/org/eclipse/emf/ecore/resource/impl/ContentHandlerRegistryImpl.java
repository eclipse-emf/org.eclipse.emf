/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
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
 * $Id: ContentHandlerRegistryImpl.java,v 1.1 2007/09/29 16:41:41 emerks Exp $
 */
package org.eclipse.emf.ecore.resource.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.eclipse.emf.ecore.resource.ContentHandler;

public class ContentHandlerRegistryImpl extends TreeMap<Integer, List<ContentHandler>> implements ContentHandler.Registry
{
  private static final long serialVersionUID = 1L;

  public void put(int priority, ContentHandler contentHandler)
  {
    Integer integerPriority = priority;
    List<ContentHandler> contentHandlers = get(integerPriority);
    if (contentHandlers == null)
    {
      put(integerPriority, contentHandlers = new ArrayList<ContentHandler>());
    }
    contentHandlers.add(contentHandler);
  }
  
  public List<ContentHandler> contentHandlers()
  {
    ArrayList<ContentHandler> result = new ArrayList<ContentHandler>();
    for (List<ContentHandler> contentHandlers : values())
    {
      result.addAll(contentHandlers);
    }
    return result;
  }
}
