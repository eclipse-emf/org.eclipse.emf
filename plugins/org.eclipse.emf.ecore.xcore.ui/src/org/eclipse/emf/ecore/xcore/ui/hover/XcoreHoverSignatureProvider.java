/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.hover;


import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.ui.hover.XbaseDeclarativeHoverSignatureProvider;

import com.google.inject.Inject;


public class XcoreHoverSignatureProvider extends XbaseDeclarativeHoverSignatureProvider
{
  @Inject
  private IQualifiedNameProvider nameProvider;

  @Inject
  private IQualifiedNameConverter nameConverter;

  @Inject
  private XcoreMapper mapper;

  protected String _signature(XNamedElement xNamedElement, boolean typeAtEnd)
  {
    QualifiedName qualifiedName = nameProvider.getFullyQualifiedName(xNamedElement);
    String name = nameConverter.toString(qualifiedName);
    if (xNamedElement instanceof XAnnotationDirective)
    {
      return "annotation \"" + ((XAnnotationDirective)xNamedElement).getSourceURI() + "\" as " + name;
    }
    else
    {
      return name;
    }
  }

  protected String _signature(GenBase genBase, boolean typeAtEnd)
  {
    QualifiedName qualifiedName = 
      genBase instanceof GenFeature ?
        nameProvider.getFullyQualifiedName(genBase.eContainer()).append(((GenFeature)genBase).getName()) : 
        nameProvider.getFullyQualifiedName(genBase);
    return nameConverter.toString(qualifiedName);
  }

  protected String _signature(EModelElement eModelElement, boolean typeAtEnd)
  {
    QualifiedName qualifiedName = nameProvider.getFullyQualifiedName(eModelElement);
    return nameConverter.toString(qualifiedName);
  }
}
