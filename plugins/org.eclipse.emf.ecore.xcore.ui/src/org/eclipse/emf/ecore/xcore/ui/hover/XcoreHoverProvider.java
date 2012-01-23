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
import org.eclipse.emf.codegen.ecore.xtext.ui.GenModelHoverProvider;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.emf.ecore.xcore.ui.internal.XcoreActivator;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.xbase.ui.hover.XbaseHoverProvider;

import com.google.inject.Inject;


public class XcoreHoverProvider extends XbaseHoverProvider
{
  @Inject
  private IQualifiedNameProvider nameProvider;

  @Inject
  private IQualifiedNameConverter nameConverter;

  @Inject
  private XcoreMapper mapper;
  
  private GenModelHoverProvider.Helper helper;

  
  public GenModelHoverProvider.Helper getHelper()
  {
    if (helper == null)
    {
      helper = new GenModelHoverProvider.Helper(nameProvider, nameConverter);
    }
    return helper;
  }

  protected String getImage(EObject eObject)
  {
    if (eObject instanceof XAnnotationDirective)
    {
      return getHelper().toImage(XcoreActivator.getInstance().getBundle().getEntry("icons/full/obj16/annotation_obj.gif"));
    }
    else
    {
      return getHelper().getImage(eObject);
    }
  }

  @Override
  protected String getFirstLine(EObject eObject)
  {
    if (eObject instanceof XNamedElement)
    {
      QualifiedName qualifiedName = nameProvider.getFullyQualifiedName(eObject);
      String name = nameConverter.toString(qualifiedName);
      if (eObject instanceof XAnnotationDirective)
      {
        return getHelper().compose(getImage(eObject), "annotation \"" + ((XAnnotationDirective)eObject).getSourceURI() + "\" as " + name);
      }
      else
      {
        ENamedElement eNamedElement = mapper.getEcore((XNamedElement)eObject);
        if (eNamedElement != null)
        {
          String image = getImage(eNamedElement);
          if (image != null)
          {
            return getHelper().compose(image, name);
          }
        }
      }
      return "<b>" + name + "</b>";
    }
    else if (eObject instanceof GenBase || eObject instanceof EModelElement)
    {
      return getHelper().getFirstLine(eObject);
    }
    else
    {
      return super.getFirstLine(eObject);
    }
  }

  @Override
  protected boolean hasHover(EObject eObject)
  {
    return eObject instanceof GenFeature || super.hasHover(eObject);
  }
}
