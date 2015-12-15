/**
 * Copyright (c) 2015 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.core.common.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.ECollections;
import org.junit.Test;

public class EMapTest
{
  @Test
  public void testEMapViewEquality()
  {
    assertTrue("An empty EMap's map view must be equal to an empty map", ECollections.emptyEMap().map().equals(Collections.emptyMap()));

    BasicEMap<Object, Object> basicEMap = new BasicEMap<Object, Object>();
    basicEMap.put(null, null);
    assertTrue("An EMap's map view must be equal to the same map", basicEMap.map().equals(Collections.singletonMap(null, null)));
    assertEquals("An EMap's map view hashCode must be equal to the same map's hashCode", basicEMap.map().hashCode(), Collections.singletonMap(null, null).hashCode());

    basicEMap.put("a", "b");
    Map<Object, Object> map = new HashMap<Object, Object>();
    map.put(null, null);
    map.put("a", "b");

    assertTrue("An EMap's map view must be equal to the same map", basicEMap.map().equals(map));
    assertEquals("An EMap's map view hashCode must be equal to the same map's hashCode", basicEMap.map().hashCode(), map.hashCode());

    assertTrue("An EMap's map view must be equal to it's own view", basicEMap.map().equals(basicEMap.map()));

    map.remove(null);
    basicEMap.remove(null);

    Map.Entry<Object, Object> mapEntry = map.entrySet().iterator().next();
    Map.Entry<Object, Object> mapViewEntry = basicEMap.entrySet().iterator().next();
    assertEquals("An EMap's map view's entry must be equal to entry of a norma map", mapViewEntry, mapEntry);
    assertEquals("An EMap's map view's entry hashCode must be equal to entry of a norma map", mapViewEntry.hashCode(), mapEntry.hashCode());
    
    Set<Map.Entry<Object, Object>> entrySet = basicEMap.map().entrySet();
    Iterator<Map.Entry<Object, Object>> iterator = entrySet.iterator();
    if (iterator.hasNext()) {
      Map.Entry<Object, Object> entry = iterator.next();
      try
      {
        Map.Entry<Object, Object> sameEntry = entrySet.iterator().next();
        assertEquals("Iterator should yield consistent results", entry, sameEntry);
      }
      catch (NoSuchElementException ex)
      {
        fail("Iterator is inconsistent");
      }
    }
  }
}
