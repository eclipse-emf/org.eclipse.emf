/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.hover;


import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.ui.hover.XbaseHoverProvider;


public class XcoreHoverProvider extends XbaseHoverProvider
{
  @Override
  protected boolean hasHover(EObject eObject)
  {
    return eObject instanceof GenFeature || super.hasHover(eObject);
  }
}
