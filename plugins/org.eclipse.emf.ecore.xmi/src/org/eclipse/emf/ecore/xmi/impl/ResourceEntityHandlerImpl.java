/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: ResourceEntityHandlerImpl.java,v 1.1 2006/12/05 20:23:28 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 */
public class ResourceEntityHandlerImpl implements XMLResource.ResourceEntityHandler
{
  protected String entityName;
  protected int count = 1;
  protected Map<String, String> nameToValueMap = new LinkedHashMap<String, String>();
  protected Map<String, String> valueToNameMap = new LinkedHashMap<String, String>();
  
  public ResourceEntityHandlerImpl(String entityName)
  {
    this.entityName = entityName;
  }
  
  public void reset()
  {
    valueToNameMap.clear();
    nameToValueMap.clear();
    count = 1;
  }

  public String getEntityName(String entityValue)
  {
    String result = valueToNameMap.get(entityValue);
    if (result == null)
    {
      result = generateEntityName(entityValue);
      if (result != null)
      {
        valueToNameMap.put(entityValue, result);
        nameToValueMap.put(result, entityValue);
      }
    }
    return result;
  }
  
  protected String generateEntityName(String entityValue)
  {
    for (String result = entityName + count;; result = entityName + ++count)
    {
      if (!nameToValueMap.containsKey(result))
      {
        return result;
      }
    }
  }

  public void handleEntity(String entityName, String entityValue)
  {
    nameToValueMap.put(entityName, entityValue);
    valueToNameMap.put(entityValue, entityName);
  }
  
  public Map<String, String> getNameToValueMap()
  {
    return nameToValueMap;
  }
}
