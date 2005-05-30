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


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

import commonj.sdo.Property;


public class IPOModel
{
  protected Property shipToProp;

  protected Property billToProp;

  protected Property commentProp;

  protected Property itemsProp;

  protected Property itemProp;

  protected Property orderDateProp;

  protected Property productNameProp;

  protected EStructuralFeature quantityFeat;

  protected EStructuralFeature usPriceFeat;

  protected Property quantityProp;

  protected Property usPriceProp;

  protected Property itemCommentProp;

  protected Property shipDateProp;

  protected Property partNumProp;

  protected EClass usAddressEClass;

  protected EStructuralFeature usAddressNameFeat;

  protected EStructuralFeature usAddressStreetFeat;

  protected EStructuralFeature usAddressCityFeat;

  protected EStructuralFeature usAddressStateFeat;

  protected EStructuralFeature usAddressZipFeat;

  protected ExtendedMetaData metaData;

  protected EPackage ePackage;

  protected ResourceSet resourceSet;

  public EStructuralFeature getUsPriceFeat()
  {
    return usPriceFeat;
  }

  public EStructuralFeature getQuantityFeat()
  {
    return quantityFeat;
  }

  public ResourceSet getResourceSet()
  {
    return resourceSet;
  }

  public Property getBillToProp()
  {
    return billToProp;
  }

  public Property getCommentProp()
  {
    return commentProp;
  }

  public EPackage getEPackage()
  {
    return ePackage;
  }

  public Property getItemCommentProp()
  {
    return itemCommentProp;
  }

  public Property getItemProp()
  {
    return itemProp;
  }

  public Property getItemsProp()
  {
    return itemsProp;
  }

  public ExtendedMetaData getMetaData()
  {
    return metaData;
  }

  public Property getOrderDateProp()
  {
    return orderDateProp;
  }

  public Property getPartNumProp()
  {
    return partNumProp;
  }

  public Property getProductNameProp()
  {
    return productNameProp;
  }

  public Property getQuantityProp()
  {
    return quantityProp;
  }

  public Property getShipDateProp()
  {
    return shipDateProp;
  }

  public Property getShipToProp()
  {
    return shipToProp;
  }

  public EStructuralFeature getUsAddressCityFeat()
  {
    return usAddressCityFeat;
  }

  public EClass getUsAddressEClass()
  {
    return usAddressEClass;
  }

  public EStructuralFeature getUsAddressNameFeat()
  {
    return usAddressNameFeat;
  }

  public EStructuralFeature getUsAddressStateFeat()
  {
    return usAddressStateFeat;
  }

  public EStructuralFeature getUsAddressStreetFeat()
  {
    return usAddressStreetFeat;
  }

  public EStructuralFeature getUsAddressZipFeat()
  {
    return usAddressZipFeat;
  }

  public Property getUsPriceProp()
  {
    return usPriceProp;
  }

}
