/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.scoping;

 
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
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
    if (eObject.eClass().getEPackage() == EcorePackage.eINSTANCE)
    {
      return ecoreResourceDescriptionStrategy.createEObjectDescriptions(eObject, acceptor);
    }
    else
    {
      return super.createEObjectDescriptions(eObject, acceptor);
    }
  }
}
