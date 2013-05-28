/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.scoping;


import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.xtext.ecore.EcoreResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.xbase.resource.XbaseResourceDescriptionStrategy;

import com.google.inject.Inject;


public class XcoreResourceDescriptionStrategy extends XbaseResourceDescriptionStrategy
{
  @Inject
  private EcoreResourceDescriptionStrategy ecoreResourceDescriptionStrategy;

  @Override
  public boolean createEObjectDescriptions(EObject eObject, IAcceptor<IEObjectDescription> acceptor)
  {
    EClass eClass = eObject.eClass();
    EPackage ePackage = eClass.getEPackage();
    if (ePackage == EcorePackage.eINSTANCE)
    {
      return ecoreResourceDescriptionStrategy.createEObjectDescriptions(eObject, acceptor);
    }
    else if (ePackage == GenModelPackage.eINSTANCE)
    {
      if (eClass == GenModelPackage.Literals.GEN_MODEL || eClass == GenModelPackage.Literals.GEN_PACKAGE)
      {
        return true;
      }

      super.createEObjectDescriptions(eObject, acceptor);

      return !(eObject instanceof GenClassifier);
    }
    else if (ePackage == XcorePackage.eINSTANCE)
    {
      if (eClass == XcorePackage.Literals.XPACKAGE)
      {
        for (XAnnotationDirective xAnnotationDirective : ((XPackage)eObject).getAnnotationDirectives())
        {
          super.createEObjectDescriptions(xAnnotationDirective, acceptor);
        }
      }
      return false;
    }
    else
    {
      return super.createEObjectDescriptions(eObject, acceptor);
    }
  }
}
