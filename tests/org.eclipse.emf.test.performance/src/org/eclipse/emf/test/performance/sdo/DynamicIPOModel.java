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
 * $Id$
 */
package org.eclipse.emf.test.performance.sdo;


import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.sdo.impl.DynamicEDataObjectImpl;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.test.performance.TestUtil;
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
    List packageList = (List)xsdEcoreBuilder.generate(URI.createURI(DATA_URI + "ipo.xsd"));
    Registry packageRegistry = resourceSet.getPackageRegistry();

    EPackage epackage = (EPackage)packageList.get(0);
    epackage.setEFactoryInstance(new DynamicEDataObjectImpl.FactoryImpl());
    String nsURI = epackage.getNsURI();
    packageRegistry.put(nsURI, epackage);

    List classifiers = epackage.getEClassifiers();
    for (Iterator i = classifiers.iterator(); i.hasNext();)
    {
      EClassifier eClassifier = (EClassifier)i.next();
      if ("PurchaseOrderType".equals(eClassifier.getName()))
      {

        EClass eClass = (EClass)eClassifier;
        List features = eClass.getEAllStructuralFeatures();
        shipToProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(0));
        billToProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(1));
        commentProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(2));
        itemsProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(3));
        itemProp = itemsProp.getType().getProperty("item");
        orderDateProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(4));
      }
      if ("ItemType".equals(eClassifier.getName()))
      {
        EClass eClass = (EClass)eClassifier;
        List features = eClass.getEAllStructuralFeatures();
        productNameProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(0));
        quantityFeat = (EStructuralFeature)features.get(1);
        quantityProp = SDOUtil.adaptProperty(quantityFeat);
        usPriceFeat = (EStructuralFeature)features.get(2);
        usPriceProp = SDOUtil.adaptProperty(usPriceFeat);
        itemCommentProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(3));
        shipDateProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(4));
        partNumProp = SDOUtil.adaptProperty((EStructuralFeature)features.get(5));
      }
      if ("USAddress".equals(eClassifier.getName()))
      {
        usAddressEClass = (EClass)eClassifier;
        List features = usAddressEClass.getEAllStructuralFeatures();
        usAddressNameFeat = (EStructuralFeature)features.get(0);
        usAddressStreetFeat = (EStructuralFeature)features.get(1);
        usAddressCityFeat = (EStructuralFeature)features.get(2);
        usAddressStateFeat = (EStructuralFeature)features.get(3);
        usAddressZipFeat = (EStructuralFeature)features.get(4);
      }
    }

    return new BasicExtendedMetaData(packageRegistry);
  }

}
