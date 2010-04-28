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
 * $Id: Reflect.java,v 1.1 2010/04/28 14:49:26 emerks Exp $
 */
package org.eclipse.emf.common.util;

import java.util.HashMap;
import java.util.Map;

public final class Reflect
{
  static final Map<Class<?>, Helper> HELPER_REGISTRY = new HashMap<Class<?>, Reflect.Helper>();

  public interface Helper
  {
    boolean isInstance(Object instance);
    Object newArrayInstance(int size);
  }
  
  public static void register(Class<?> class_, Helper helper)
  {
    HELPER_REGISTRY.put(class_, helper);
  }

  public static boolean isInstance(Class<?> class_, Object instance)
  {
    Helper helper = HELPER_REGISTRY.get(class_);
    if (helper != null)
    {
      return helper.isInstance(instance);
    }
    else
    {
      throw new UnsupportedOperationException();
    }
  }
}
