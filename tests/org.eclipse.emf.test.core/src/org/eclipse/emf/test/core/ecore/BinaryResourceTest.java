/**
 * <copyright>
 *
 * Copyright (c) 2010 IBM Corporation and others.
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
 * $Id: BinaryResourceTest.java,v 1.2 2011/04/07 23:41:08 emerks Exp $
 */
package org.eclipse.emf.test.core.ecore;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.test.models.ppo.Item;
import org.eclipse.emf.test.models.ppo.PPOFactory;
import org.eclipse.emf.test.models.ppo.PurchaseOrder;


/**
 * @since 2.6
 */
public class BinaryResourceTest extends TestCase
{
  private URI resourceURI;
  private List<EObject> rootObjects;
  private EcoreUtil.EqualityHelper equalityHelper;

  public BinaryResourceTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("BinaryResourceTest");
    ts.addTest(new BinaryResourceTest("testSaveAndLoad1"));
    ts.addTest(new BinaryResourceTest("testSaveAndLoad2"));
    ts.addTest(new BinaryResourceTest("testSaveAndLoadWithXMIResource"));
    ts.addTest(new BinaryResourceTest("testSaveAndLoadNoCache1"));
    ts.addTest(new BinaryResourceTest("testSaveAndLoadNoCache2"));
    return ts;
  }

  @Override
  protected void setUp() throws Exception
  {
    File tempDir = new File(System.getProperty("java.io.tmpdir"));
    if (!tempDir.exists())
    {
      assertTrue(tempDir.getAbsolutePath(), tempDir.mkdirs());
    }
    else
    {
      assertTrue(tempDir.getAbsolutePath(), tempDir.isDirectory());
      assertTrue(tempDir.getAbsolutePath(), tempDir.canWrite());
    }

    resourceURI = URI.createFileURI(tempDir.getAbsolutePath() + '/' + System.nanoTime() + ".dat");
    assertFalse(resourceURI.toString(), URIConverter.INSTANCE.exists(resourceURI, null));

    int numberORootObjects = 100;
    rootObjects = new ArrayList<EObject>(numberORootObjects);
    for (int i = 0; i < numberORootObjects; i++)
    {
      PurchaseOrder purchaseOrder = PPOFactory.eINSTANCE.createPurchaseOrder();
      rootObjects.add(purchaseOrder);
      purchaseOrder.setComment("purchase order " + i);
      purchaseOrder.setOrderDate(new Date());

      for (int j = 0, max = numberORootObjects / 2; j < max; j++)
      {
        Item item = PPOFactory.eINSTANCE.createItem();
        purchaseOrder.getItems().add(item);
        item.setProductName("item " + i + "-" + j);
        item.setQuantity(i * j);
      }
    }

    equalityHelper = new EcoreUtil.EqualityHelper();
  }

  @Override
  protected void tearDown() throws Exception
  {
    URIConverter.INSTANCE.delete(resourceURI, null);
    assertFalse(resourceURI.toString(), URIConverter.INSTANCE.exists(resourceURI, null));
  }

  public void testSaveAndLoad1() throws Exception
  {
    Resource resource = new BinaryResourceImpl(resourceURI);
    resource.getContents().addAll(rootObjects);
    resource.save(null);
    assertTrue(resourceURI.toString(), URIConverter.INSTANCE.exists(resourceURI, null));

    resource = new BinaryResourceImpl(resourceURI);
    resource.load(null);
    assertTrue(equalityHelper.equals(rootObjects, resource.getContents()));
  }

  public void testSaveAndLoad2() throws Exception
  {
    Resource resource = new BinaryResourceImpl();
    resource.getContents().addAll(rootObjects);
    OutputStream outputStream = URIConverter.INSTANCE.createOutputStream(resourceURI);
    try
    {
      resource.save(outputStream, null);
    }
    finally
    {
      outputStream.close();
    }
    assertTrue(resourceURI.toString(), URIConverter.INSTANCE.exists(resourceURI, null));

    resource = new BinaryResourceImpl();
    InputStream inputStream = URIConverter.INSTANCE.createInputStream(resourceURI);
    try
    {
      resource.load(inputStream, null);
    }
    finally
    {
      inputStream.close();
    }
    assertTrue(equalityHelper.equals(rootObjects, resource.getContents()));
  }

  public void testSaveAndLoadWithXMIResource() throws Exception
  {
    XMLResource savedResource = 
      new XMIResourceImpl(resourceURI)
      {
        @Override
        protected boolean useUUIDs()
        {
          return true;
        }
      };
    savedResource.getDefaultSaveOptions().put(XMLResource.OPTION_BINARY, Boolean.TRUE);
    savedResource.getContents().addAll(rootObjects);
    savedResource.save(null);
    assertTrue(resourceURI.toString(), URIConverter.INSTANCE.exists(resourceURI, null));

    XMLResource loadedResource = new XMIResourceImpl(resourceURI);
    loadedResource.getDefaultLoadOptions().put(XMLResource.OPTION_BINARY, Boolean.TRUE);
    loadedResource.load(null);
    assertTrue(equalityHelper.equals(rootObjects, loadedResource.getContents()));
    for (int i = 0, size = rootObjects.size(); i < size; ++i)
    {
      String loadedID = loadedResource.getID(loadedResource.getContents().get(i));
      assertNotNull(loadedID);
      assertEquals(savedResource.getID(rootObjects.get(i)), loadedID);
    }
  }

  public void testSaveAndLoadNoCache1() throws Exception
  {
    Map<String, Object> options = new HashMap<String, Object>();
    options.put(BinaryResourceImpl.OPTION_BUFFER_CAPACITY, 0);

    Resource resource = new BinaryResourceImpl(resourceURI);
    resource.getContents().addAll(rootObjects);
    resource.save(options);
    assertTrue(resourceURI.toString(), URIConverter.INSTANCE.exists(resourceURI, null));

    resource = new BinaryResourceImpl(resourceURI);
    resource.load(options);
    assertTrue(equalityHelper.equals(rootObjects, resource.getContents()));
  }

  public void testSaveAndLoadNoCache2() throws Exception
  {
    Resource resource = new BinaryResourceImpl();
    resource.getContents().addAll(rootObjects);
    OutputStream outputStream = new BufferedOutputStream(URIConverter.INSTANCE.createOutputStream(resourceURI));
    try
    {
      resource.save(outputStream, null);
    }
    finally
    {
      outputStream.close();
    }
    assertTrue(resourceURI.toString(), URIConverter.INSTANCE.exists(resourceURI, null));

    resource = new BinaryResourceImpl();
    InputStream inputStream = new BufferedInputStream(URIConverter.INSTANCE.createInputStream(resourceURI));
    try
    {
      resource.load(inputStream, null);
    }
    finally
    {
      inputStream.close();
    }
    assertTrue(equalityHelper.equals(rootObjects, resource.getContents()));
  }
}
