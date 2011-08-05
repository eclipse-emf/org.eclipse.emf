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
