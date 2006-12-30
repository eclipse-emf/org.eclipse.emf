/**
 * <copyright>
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * $Id$
 */
package org.eclipse.emf.test.performance.sdo;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.test.performance.TestUtil;

import org.eclipse.emf.ecore.sdo.impl.DynamicEDataObjectImpl;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;

import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;


public class DynamicIPOModel extends IPOModel
{
  public static final DynamicIPOModel INSTANCE = new DynamicIPOModel();

  protected static String DATA;

  protected static String DATA_URI;

  private DynamicIPOModel()
  {
    resourceSet = SDOUtil.createResourceSet();
    DATA = TestUtil.getPluginDirectory() + "/data/";
    DATA_URI = "file:///" + DATA;
    metaData = registerModel(resourceSet);
  }

  protected ExtendedMetaData registerModel(ResourceSet resourceSet)
  {
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());

    XSDEcoreBuilder xsdEcoreBuilder = new XSDEcoreBuilder();
    Collection<EObject> packageList = xsdEcoreBuilder.generate(URI.createURI(DATA_URI + "ipo.xsd"));
    Registry packageRegistry = resourceSet.getPackageRegistry();

    EPackage epackage = (EPackage)packageList.iterator().next();
    epackage.setEFactoryInstance(new DynamicEDataObjectImpl.FactoryImpl());
    String nsURI = epackage.getNsURI();
    packageRegistry.put(nsURI, epackage);

    List<EClassifier> classifiers = epackage.getEClassifiers();
    for (EClassifier eClassifier : classifiers)
    {
      if ("PurchaseOrderType".equals(eClassifier.getName()))
      {

        EClass eClass = (EClass)eClassifier;
        List<EStructuralFeature> features = eClass.getEAllStructuralFeatures();
        shipToProp = SDOUtil.adaptProperty(features.get(0));
        billToProp = SDOUtil.adaptProperty(features.get(1));
        commentProp = SDOUtil.adaptProperty(features.get(2));
        itemsProp = SDOUtil.adaptProperty(features.get(3));
        itemProp = itemsProp.getType().getProperty("item");
        orderDateProp = SDOUtil.adaptProperty(features.get(4));
      }
      if ("ItemType".equals(eClassifier.getName()))
      {
        EClass eClass = (EClass)eClassifier;
        List<EStructuralFeature> features = eClass.getEAllStructuralFeatures();
        productNameProp = SDOUtil.adaptProperty(features.get(0));
        quantityFeat = features.get(1);
        quantityProp = SDOUtil.adaptProperty(quantityFeat);
        usPriceFeat = features.get(2);
        usPriceProp = SDOUtil.adaptProperty(usPriceFeat);
        itemCommentProp = SDOUtil.adaptProperty(features.get(3));
        shipDateProp = SDOUtil.adaptProperty(features.get(4));
        partNumProp = SDOUtil.adaptProperty(features.get(5));
      }
      if ("USAddress".equals(eClassifier.getName()))
      {
        usAddressEClass = (EClass)eClassifier;
        List<EStructuralFeature> features = usAddressEClass.getEAllStructuralFeatures();
        usAddressNameFeat = features.get(0);
        usAddressStreetFeat = features.get(1);
        usAddressCityFeat = features.get(2);
        usAddressStateFeat = features.get(3);
        usAddressZipFeat = features.get(4);
      }
    }

    return new BasicExtendedMetaData(packageRegistry);
  }

}
