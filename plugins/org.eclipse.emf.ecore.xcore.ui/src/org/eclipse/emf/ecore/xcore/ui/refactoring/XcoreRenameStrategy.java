/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.refactoring;


import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.util.XcoreEcoreBuilder;
import org.eclipse.emf.ecore.xcore.util.XcoreJvmInferrer;
import org.eclipse.xtext.xbase.ui.jvmmodel.refactoring.DefaultJvmModelRenameStrategy;

import com.google.inject.Inject;


/**
 * Encapsulates the model changes of a rename refactoring.
 */
public class XcoreRenameStrategy extends DefaultJvmModelRenameStrategy
{
  @Inject
  private XcoreMapper mapper;

  @Override
  protected EObject setName(URI targetElementURI, final String newName, ResourceSet resourceSet)
  {
    EObject targetElement = super.setName(targetElementURI, newName, resourceSet);
    if (targetElement instanceof XNamedElement)
    {
      XNamedElement xNamedElement = (XNamedElement)targetElement;
      ENamedElement eNamedElement = mapper.getEcore(xNamedElement);
      if (eNamedElement instanceof EPackage)
      {
        XcoreEcoreBuilder.setQualifiedPackageName((EPackage)eNamedElement, newName);
      }
      else
      {
        eNamedElement.setName(newName);
      }
      GenBase genBase = mapper.getGen(xNamedElement);
      XcoreJvmInferrer.inferName(genBase);
    }
    return targetElement;
  }

  @Override
  protected void setInferredJvmElementName(String newName, ResourceSet resourceSet)
  {
    // Do nothing.
  }
}
