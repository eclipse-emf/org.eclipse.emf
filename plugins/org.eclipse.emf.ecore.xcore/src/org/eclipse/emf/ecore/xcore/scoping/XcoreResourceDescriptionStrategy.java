/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.scoping;


import java.util.Collections;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.xtext.common.types.JvmEnumerationType;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.TypesFactory;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.EObjectDescription;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionStrategy;
import org.eclipse.xtext.util.IAcceptor;

import com.google.inject.Inject;


public class XcoreResourceDescriptionStrategy extends DefaultResourceDescriptionStrategy
{

  @Inject(optional = true)
  private TypesFactory typesFactory = TypesFactory.eINSTANCE;

  @Inject(optional = true)
  private EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;

  @Inject(optional = true)
  private GenModelFactory genFactory = GenModelFactory.eINSTANCE;

  @Inject
  private LazyCreationProxyURIConverter proxyTool;

  @Inject
  private IQualifiedNameProvider nameProvider;

  @Override
  public boolean createEObjectDescriptions(EObject eObject, IAcceptor<IEObjectDescription> acceptor)
  {
    if (eObject instanceof EClass)
    {
      QualifiedName name = nameProvider.getFullyQualifiedName(eObject);
      if (name != null)
      {
        URI uri = eObject.eResource().getURI();
        EClass eClass = ecoreFactory.createEClass();
        proxyTool.installProxyURI(uri, eClass, name);
        acceptor.accept(EObjectDescription.create(name, eClass));
      }
      return false;
    }
    else if (eObject instanceof GenClass)
    {
      QualifiedName name = nameProvider.getFullyQualifiedName(eObject);
      if (name != null)
      {
        URI uri = eObject.eResource().getURI();
        GenClass genClass = genFactory.createGenClass();
        proxyTool.installProxyURI(uri, genClass, name);
        acceptor.accept(EObjectDescription.create(name, genClass));
      }
      return false;
    }
    else if (eObject instanceof GenDataType)
    {
      QualifiedName name = nameProvider.getFullyQualifiedName(eObject);
      if (name != null)
      {
        URI uri = eObject.eResource().getURI();
        GenDataType genDataType = genFactory.createGenDataType();
        proxyTool.installProxyURI(uri, genDataType, name);
        acceptor.accept(EObjectDescription.create(name, genDataType));
      }
      return false;
    }
    else if (eObject instanceof JvmGenericType)
    {
      QualifiedName name = nameProvider.getFullyQualifiedName(eObject);
      if (name != null)
      {
        URI uri = eObject.eResource().getURI();
        JvmGenericType jvmGenericType = typesFactory.createJvmGenericType();
        proxyTool.installProxyURI(uri, jvmGenericType, name);
        acceptor.accept(EObjectDescription.create(name, jvmGenericType));
      }
      return false;
    }
    else if (eObject instanceof JvmEnumerationType)
    {
      QualifiedName name = nameProvider.getFullyQualifiedName(eObject);
      if (name != null)
      {
        URI uri = eObject.eResource().getURI();
        JvmEnumerationType jvmEnumerationType = typesFactory.createJvmEnumerationType();
        proxyTool.installProxyURI(uri, jvmEnumerationType, name);
        acceptor.accept(EObjectDescription.create(name, jvmEnumerationType));
      }
      return false;
    }
    else if (eObject instanceof XAnnotationDirective)
    {
      QualifiedName name = nameProvider.getFullyQualifiedName(eObject);
      if (name != null)
      {
        acceptor.accept(EObjectDescription.create(name, eObject));
      }
      return false;
    }
    else if (eObject instanceof EPackage)
    {
      QualifiedName name = nameProvider.getFullyQualifiedName(eObject);
      if (name != null)
      {
        URI uri = eObject.eResource().getURI();
        EPackage ePackage = ecoreFactory.createEPackage();
        proxyTool.installProxyURI(uri, ePackage, name);
        acceptor.accept(EObjectDescription.create(name, ePackage, Collections.singletonMap("nsURI", "true")));
      }
      return true;
    }
    else if (eObject instanceof XPackage || eObject instanceof GenModel || eObject instanceof GenPackage)
    {
      return true;
    }
    else 
    {
      return false;
    }
  }
}
