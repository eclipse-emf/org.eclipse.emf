/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: SDOPerfUtil.java,v 1.1 2005/02/16 23:02:13 bportier Exp $
 */
package org.eclipse.emf.test.performance.sdo;


import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.sdo.impl.DynamicEDataObjectImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;


public class SDOPerfUtil
{
  public static ExtendedMetaData registerModel(ResourceSet resourceSet, String xmlSchemaURI)
  {
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());

    Registry packageRegistry = resourceSet.getPackageRegistry();
    XSDEcoreBuilder xsdEcoreBuilder = new XSDEcoreBuilder();
    Collection packageList = xsdEcoreBuilder.generate(URI.createURI(xmlSchemaURI));

    for (Iterator i = packageList.iterator(); i.hasNext();)
    {
      EPackage epackage = (EPackage)i.next();
      epackage.setEFactoryInstance(new DynamicEDataObjectImpl.FactoryImpl());
      String nsURI = epackage.getNsURI();
      packageRegistry.put(nsURI, epackage);
    }

    return new BasicExtendedMetaData(packageRegistry);
  }

}
