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
 * $Id: IPOSDOAccessorTest.java,v 1.2 2005/02/17 21:45:28 bportier Exp $
 */
package org.eclipse.emf.test.performance.sdo.accessor;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import commonj.sdo.DataObject;
import commonj.sdo.Property;


public class IPOSDOAccessorTest
{

  private static int ITERATIONS;

  private static final int NUM_ITEMS = 1;

  // model

  private Property shipToProp;

  private Property billToProp;

  private Property commentProp;

  private Property itemsProp;

  private Property itemProp;

  private Property orderDateProp;

  private Property productNameProp;

  private Property quantityProp;

  private Property usPriceProp;

  private Property itemCommentProp;

  private Property shipDateProp;

  private Property partNumProp;

  // instance data (used in get methods)

  private DataObject shipToValue;

  private DataObject billToValue;

  private String commentValue;

  private DataObject itemElementValue;

  private List itemValue;

  private Object orderDateValue;

  private String productNameValue;

  private BigInteger quantityValue;

  private BigDecimal usPriceValue;

  private String itemCommentValue;

  private Object shipDateValue;

  private String partNumValue;

  // instance data (used in set methods)

  private DataObject billToAddress;

  private DataObject shipToAddress;

  private DataObject itemElement;

  private List item;

  private Object orderDate;

  private String orderComment;

  private String productName;

  private BigInteger quantity;

  private BigDecimal usPrice;

  private String itemComment;

  private Object shipDate;

  private String partNum;

  public IPOSDOAccessorTest(
    int iterations,
    Property shipToProp,
    Property billToProp,
    Property commentProp,
    Property itemsProp,
    Property itemProp,
    Property orderDateProp,
    Property productNameProp,
    Property quantityProp,
    Property usPriceProp,
    Property itemCommentProp,
    Property shipDateProp,
    Property partNumProp)
  {
    ITERATIONS = iterations;
    this.shipToProp = shipToProp;
    this.billToProp = billToProp;
    this.commentProp = commentProp;
    this.itemsProp = itemsProp;
    this.itemProp = itemProp;
    this.orderDateProp = orderDateProp;
    this.productNameProp = productNameProp;
    this.quantityProp = quantityProp;
    this.usPriceProp = usPriceProp;
    this.itemCommentProp = itemCommentProp;
    this.shipDateProp = shipDateProp;
    this.partNumProp = partNumProp;
  }

  public void initInstanceData(
    DataObject billToAddress,
    DataObject shipToAddress,
    String comment,
    Object orderDate,
    String productName,
    BigInteger quantity,
    BigDecimal usPrice,
    String itemComment,
    Object shipDate,
    String partNum)
  {
    this.billToAddress = billToAddress;
    this.shipToAddress = shipToAddress;
    this.orderComment = comment;
    this.orderDate = orderDate;
    this.productName = productName;
    this.quantity = quantity;
    this.usPrice = usPrice;
    this.itemComment = itemComment;
    this.shipDate = shipDate;
    this.partNum = partNum;
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the values of a PurchaseOrder DataObject.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>access API: reflective by Property</li>
   * </ul>
   * </p>
   */
  public final void getByProperty(DataObject po)
  {
    for (int i = 0; i < ITERATIONS; i++)
    {
      shipToValue = po.getDataObject(shipToProp);
      billToValue = po.getDataObject(billToProp);
      commentValue = po.getString(commentProp);

      itemValue = po.getDataObject(itemsProp).getList(itemProp);
      for (int j = 0; j < itemValue.size(); j++)
      {
        itemElementValue = (DataObject)itemValue.get(j);
        productNameValue = itemElementValue.getString(productNameProp);
        quantityValue = itemElementValue.getBigInteger(quantityProp);
        usPriceValue = itemElementValue.getBigDecimal(usPriceProp);
        itemCommentValue = itemElementValue.getString(itemCommentProp);
        shipDateValue = itemElementValue.get(shipDateProp);
        partNumValue = itemElementValue.getString(partNumProp);
      }

      orderDateValue = po.get(orderDateProp);
    }
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the values of a DataObject.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>access API: reflective by index</li>
   * </ul>
   * </p>
   */
  public final void getByIndex(DataObject po)
  {
    for (int i = 0; i < ITERATIONS; i++)
    {
      shipToValue = po.getDataObject(0);
      billToValue = po.getDataObject(1);
      commentValue = po.getString(2);

      itemValue = po.getDataObject(3).getList(0);
      for (int j = 0; j < itemValue.size(); j++)
      {
        itemElementValue = (DataObject)itemValue.get(j);
        productNameValue = itemElementValue.getString(0);
        quantityValue = itemElementValue.getBigInteger(1);
        usPriceValue = itemElementValue.getBigDecimal(2);
        itemCommentValue = itemElementValue.getString(3);
        shipDateValue = itemElementValue.get(4);
        partNumValue = itemElementValue.getString(5);
      }

      orderDateValue = po.get(4);
    }
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the values of a DataObject.
   * Test details:
   * <ul>
   * <li>get/set: get</li>
   * <li>access API: reflective by path</li>
   * </ul>
   * </p>
   */
  public final void getByPath(DataObject po)
  {
    for (int i = 0; i < ITERATIONS; i++)
    {
      shipToValue = po.getDataObject("shipTo");
      billToValue = po.getDataObject("billTo");
      commentValue = po.getString("comment");
      
      productNameValue = po.getString("items/item[1]/productName");
      quantityValue = po.getBigInteger("items/item[1]/quantity");
      usPriceValue = po.getBigDecimal("items/item[1]/uSPrice");
      itemCommentValue = po.getString("items/item[1]/comment");
      shipDateValue = po.get("items/item[1]/shipDate");
      partNumValue = po.getString("items/item[1]/partNum");

      orderDateValue = po.get("orderDate");
    }
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the values of a DataObject.
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>access API: reflective by Property</li>
   * </ul>
   * </p>
   */
  // TODO difference between containment and not.
  public final void setByProperty(DataObject po)
  {
    for (int i = 0; i < ITERATIONS; i++)
    {
      po.setDataObject(shipToProp, shipToAddress);
      po.setDataObject(billToProp, billToAddress);
      po.setString(commentProp, orderComment);

      item = po.getDataObject(itemsProp).getList(itemProp);
      for (int j = 0; j < NUM_ITEMS; j++)
      {
        itemElement = (DataObject)item.get(j);
        itemElement.setString(productNameProp, productName);
        itemElement.setBigInteger(quantityProp, quantity);
        itemElement.setBigDecimal(usPriceProp, usPrice);
        itemElement.setString(itemCommentProp, itemComment);
        itemElement.set(shipDateProp, shipDate);
        itemElement.setString(partNumProp, partNum);
      }
      po.set(orderDateProp, orderDate);
    }

  }

  /**
   * <p>
   * Uses the SDO reflective API to set the values of a DataObject.
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>access API: reflective by index</li>
   * </ul>
   * </p>
   */
  public final void setByIndex(DataObject po)
  {
    for (int i = 0; i < ITERATIONS; i++)
    {
      // shipTo
      po.set(0, shipToAddress);
      // billTo
      po.set(1, billToAddress);
      // comment
      po.set(2, orderComment);

      // items
      item = po.getDataObject(3).getList(0);
      for (int j = 0; j < NUM_ITEMS; j++)
      {
        itemElement = (DataObject)item.get(j);
        itemElement.setString(0, productName);
        itemElement.setBigInteger(1, quantity);
        itemElement.setBigDecimal(2, usPrice);
        itemElement.setString(3, itemComment);
        itemElement.set(4, shipDate);
        itemElement.setString(5, partNum);
      }

      // date
      po.set(4, orderDate);
    }
  }

  /**
   * <p>
   * Uses the SDO reflective API to get the values of a DataObject.
   * Test details:
   * <ul>
   * <li>get/set: set</li>
   * <li>access API: reflective by path</li>
   * </ul>
   * </p>
   */
  public final void setByPath(DataObject po)
  {
    for (int i = 0; i < ITERATIONS; i++)
    {
      po.set("shipTo", shipToAddress);
      po.set("billTo", billToAddress);
      po.set("comment", orderComment);
      
      po.setString("items/item[1]/productName", productName);
      po.setBigInteger("items/item[1]/quantity", quantity);
      po.setBigDecimal("items/item[1]/uSPrice", usPrice);
      po.setString("items/item[1]/comment", orderComment);
      po.set("items/item[1]/shipDate", shipDate);
      po.setString("items/item[1]/partNum", partNum);
      
      po.set("orderDate", orderDate);
    }
  }

}
