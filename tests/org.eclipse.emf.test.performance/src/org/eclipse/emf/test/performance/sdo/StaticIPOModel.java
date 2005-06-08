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
 * $Id$
 */
package org.eclipse.emf.test.performance.sdo;


import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.sdo.util.SDOUtil;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

import com.example.sdo.ipo.IpoPackage;
import com.example.sdo.ipo.util.IpoResourceFactoryImpl;


public class StaticIPOModel extends IPOModel
{
  public static final StaticIPOModel INSTANCE = new StaticIPOModel();

  private StaticIPOModel()
  {
    resourceSet = SDOUtil.createResourceSet();
    metaData = registerModel(resourceSet);
  }

  private ExtendedMetaData registerModel(ResourceSet resourceSet)
  {
    IpoPackage ipoPackageInstance = IpoPackage.eINSTANCE;
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new IpoResourceFactoryImpl());

    usPriceFeat = ipoPackageInstance.getItemType_USPrice();
    usPriceProp = SDOUtil.adaptProperty(usPriceFeat);
    quantityFeat = ipoPackageInstance.getItemType_Quantity();
    quantityProp = SDOUtil.adaptProperty(quantityFeat);
    shipToProp = SDOUtil.adaptProperty(ipoPackageInstance.getPurchaseOrderType_ShipTo());
    billToProp = SDOUtil.adaptProperty(ipoPackageInstance.getPurchaseOrderType_BillTo());
    itemsProp = SDOUtil.adaptProperty(ipoPackageInstance.getPurchaseOrderType_Items());
    orderDateProp = SDOUtil.adaptProperty(ipoPackageInstance.getPurchaseOrderType_OrderDate());
    itemProp = SDOUtil.adaptProperty(ipoPackageInstance.getItems_Item());
    commentProp = SDOUtil.adaptProperty(ipoPackageInstance.getPurchaseOrderType_Comment());
    productNameProp = SDOUtil.adaptProperty(ipoPackageInstance.getItemType_ProductName());
    itemCommentProp = SDOUtil.adaptProperty(ipoPackageInstance.getItemType_Comment());
    shipDateProp = SDOUtil.adaptProperty(ipoPackageInstance.getItemType_ShipDate());
    partNumProp = SDOUtil.adaptProperty(ipoPackageInstance.getItemType_PartNum());

    return new BasicExtendedMetaData(resourceSet.getPackageRegistry());
  }

}
