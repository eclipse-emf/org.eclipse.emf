/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.mappings;


import org.eclipse.emf.common.notify.impl.AdapterImpl;


public class AbstractMapping extends AdapterImpl
{
  @Override
  public boolean isAdapterForType(Object type)
  {
    return type == getClass();
  }
}
