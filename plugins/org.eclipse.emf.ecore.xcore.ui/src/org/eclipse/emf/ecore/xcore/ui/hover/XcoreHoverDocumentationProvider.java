/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.hover;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.xbase.ui.hover.XbaseHoverDocumentationProvider;

public class XcoreHoverDocumentationProvider extends XbaseHoverDocumentationProvider
{
  
  @Override
  protected List<EObject> getFilteredDerivedElements(EObject o, EClass type)
  {
    return super.getFilteredDerivedElements(o, type == TypesPackage.Literals.JVM_MEMBER ? TypesPackage.Literals.JVM_IDENTIFIABLE_ELEMENT : type);
  }
}
