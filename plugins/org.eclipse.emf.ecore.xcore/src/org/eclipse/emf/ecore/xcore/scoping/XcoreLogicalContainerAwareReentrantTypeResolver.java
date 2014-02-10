/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.scoping;

import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.xbase.typesystem.internal.LogicalContainerAwareReentrantTypeResolver;

public class XcoreLogicalContainerAwareReentrantTypeResolver extends LogicalContainerAwareReentrantTypeResolver
{
  @Override
  public JvmTypeReference getExtendedClass(JvmDeclaredType type)
  {
    boolean ignoreSupperInterfaces = type.getIdentifier().startsWith("$");
    JvmTypeReference result = null;
    for (JvmTypeReference candidate: type.getSuperTypes())
    {
      JvmType jvmType = candidate.getType();
      if (jvmType instanceof JvmGenericType)
      {
        if (!((JvmGenericType) jvmType).isInterface())
        {
          return candidate;
        }
        else if (result == null && !ignoreSupperInterfaces)
        {
          result = candidate;
        }
      }
    }
    return result;
  }

}
