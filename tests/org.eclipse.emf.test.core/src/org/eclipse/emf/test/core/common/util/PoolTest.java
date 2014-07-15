/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.core.common.util;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.eclipse.emf.common.util.Pool;
import org.junit.Test;

public class PoolTest
{
  @Test
  public void testWeakness()
  {
    Pool<String> set = new Pool<String>();
    {
      String foo = new String("foo");
      set.add(foo);
      assertTrue(set.contains("foo"));
      assertEquals(1, set.size());
      foo = null;
    }
    System.gc();
    assertFalse(set.contains("foo"));
    try
    {
      // If the garbage collector a chance to enqueue the stale entries.
      Thread.sleep(1000);
    }
    catch (InterruptedException e)
    {
      // Expected.
    }
    assertEquals(0, set.size());
  }
}
