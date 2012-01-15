/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore;


import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.xbase.XbasePackage;

import com.google.inject.Injector;


/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class XcoreStandaloneSetup extends XcoreStandaloneSetupGenerated
{

  public static void doSetup()
  {
    new XcoreStandaloneSetup().createInjectorAndDoEMFRegistration();
  }

  @Override
  public void register(Injector injector)
  {
    EPackage.Registry packageRegistry = injector.getInstance(EPackage.Registry.class);
    packageRegistry.put(XcorePackage.eNS_URI, XcorePackage.eINSTANCE);
    packageRegistry.put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
    packageRegistry.put(XbasePackage.eNS_URI, XbasePackage.eINSTANCE);
    packageRegistry.put(TypesPackage.eNS_URI, TypesPackage.eINSTANCE);
    packageRegistry.put(GenModelPackage.eNS_URI, GenModelPackage.eINSTANCE);
    packageRegistry.put(XMLTypePackage.eNS_URI, XMLTypePackage.eINSTANCE);
    packageRegistry.put(XMLNamespacePackage.eNS_URI, XMLNamespacePackage.eINSTANCE);

    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("genmodel", new EcoreResourceFactoryImpl());

    super.register(injector);
  }
}
