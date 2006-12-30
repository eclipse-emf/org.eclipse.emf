/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: SDOPerfUtil.java,v 1.3 2006/12/30 03:43:52 marcelop Exp $
 */
package org.eclipse.emf.test.performance.sdo;


import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

import org.eclipse.emf.ecore.sdo.impl.DynamicEDataObjectImpl;

import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;


public class SDOPerfUtil
{
  public static ExtendedMetaData registerModel(ResourceSet resourceSet, String xmlSchemaURI)
  {
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());

    Registry packageRegistry = resourceSet.getPackageRegistry();
    XSDEcoreBuilder xsdEcoreBuilder = new XSDEcoreBuilder();
    @SuppressWarnings("unchecked")
    Collection<EPackage> packageList = (Collection)xsdEcoreBuilder.generate(URI.createURI(xmlSchemaURI));
    for (EPackage epackage : packageList)
    {
      epackage.setEFactoryInstance(new DynamicEDataObjectImpl.FactoryImpl());
      String nsURI = epackage.getNsURI();
      packageRegistry.put(nsURI, epackage);
    }

    return new BasicExtendedMetaData(packageRegistry);
  }

}
