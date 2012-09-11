/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.scoping;


import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.xbase.featurecalls.IdentifiableSimpleNameProvider;


public class XcoreIdentifableSimpleNameProvider extends IdentifiableSimpleNameProvider
{
  @Override
  public String getSimpleName(JvmIdentifiableElement element)
  {
    if (element instanceof JvmDeclaredType)
    {
      return "this";
    }
    return super.getSimpleName(element);
  }
}
