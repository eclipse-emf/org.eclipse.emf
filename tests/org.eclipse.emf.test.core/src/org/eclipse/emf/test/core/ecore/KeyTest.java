/**
 * Copyright (c) 2004-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.core.ecore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.test.models.key.Item;
import org.eclipse.emf.test.models.key.KeyFactory;
import org.eclipse.emf.test.models.key.KeyPackage;
import org.eclipse.emf.test.models.key.Root;

public class KeyTest  extends TestCase
{
  private static final String TEMP_FILE_DIR = "/home/tmp/";
  private static final boolean SYSOUT = false;
  
  public KeyTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("KeyTest");
    ts.addTest(new KeyTest("testModel"));
    ts.addTest(new KeyTest("testSaveAndLoad"));
    ts.addTest(new KeyTest("testConstraint"));
    return ts;
  }
    
  public void testModel() throws Exception
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    
    resourceSet.getPackageRegistry().put(KeyPackage.eNS_URI, KeyPackage.eINSTANCE);
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
      (Resource.Factory.Registry.DEFAULT_EXTENSION, new XMLResourceFactoryImpl());
    
    Resource resource1 = resourceSet.createResource(URI.createURI("http:///My.key"));
    
    Root root = KeyFactory.eINSTANCE.createRoot();

    Item itemAXY = KeyFactory.eINSTANCE.createItem();
    itemAXY.setName("a");
    itemAXY.getSignature().add("org.eclipse.example.X");
    itemAXY.getSignature().add("org.eclipse.example.Y");
    itemAXY.getRelatedItems().add(itemAXY);
    root.getItems().add(itemAXY);
    
    {
      Item x = KeyFactory.eINSTANCE.createItem();
      x.setName("a b");
      x.getSignature().add("\'\"#, % {} []/ org.eclipse.example.X");
      x.getSignature().add("org.eclipse.example.Y");
      x.getRelatedItems().add(x);
      root.getItems().add(x);
    }
    
    {
      Item x = KeyFactory.eINSTANCE.createItem();
      x.getRelatedItems().add(x);
      root.getItems().add(x);
    }

    {
      Item x = KeyFactory.eINSTANCE.createItem();
      x.getRelatedItems().add(x);
      x.getSignature().add("org.eclipse.example.Y");
      root.getItems().add(x);
    }
    
    {
      Item x = KeyFactory.eINSTANCE.createItem();
      x.getRelatedItems().add(x);
      x.getSignature().add(null);
      x.getSignature().add("");
      x.getSignature().add(null);
      root.getItems().add(x);
    }
    
    resource1.getContents().add(root);
    if (SYSOUT) resource1.save(System.out, null);
    StringWriter out1 = new StringWriter();
    resource1.save(new URIConverter.WriteableOutputStream(out1, "UTF-8"), null);
    
    Resource resource2 = resourceSet.createResource(URI.createURI("http://My2.key"));
    resource2.load(new URIConverter.ReadableInputStream(out1.toString()), null);
    if (SYSOUT) resource2.save(System.err, null);
    StringWriter out2 = new StringWriter();
    resource2.save(new URIConverter.WriteableOutputStream(out2, "UTF-8"), null);
    
    assertEquals(out1.toString(), out2.toString());
  }
  
  public void testSaveAndLoad() throws Exception
  {
    EPackage pack = EcoreFactory.eINSTANCE.createEPackage();
    pack.setName("pack");
    pack.setNsURI("packNSURI");
    
    EClass class1 = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(class1);
    class1.setName("class1");

    EAttribute att0 = EcoreFactory.eINSTANCE.createEAttribute();
    class1.getEStructuralFeatures().add(att0);
    att0.setName("att0");
    att0.setEType(EcorePackage.Literals.ESTRING);
    
    EClass class2 = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(class2);
    class2.setName("class2");
    
    EAttribute att1 = EcoreFactory.eINSTANCE.createEAttribute();
    class2.getEStructuralFeatures().add(att1);
    att1.setName("att1");
    att1.setEType(EcorePackage.Literals.ESTRING);
    
    EAttribute att2 = EcoreFactory.eINSTANCE.createEAttribute();
    class2.getEStructuralFeatures().add(att2);
    att2.setName("att2");
    att2.setEType(EcorePackage.Literals.ESTRING);
    
    EClass class3 = EcoreFactory.eINSTANCE.createEClass();
    pack.getEClassifiers().add(class3);
    class3.getESuperTypes().add(class2);
    class3.setName("class3");

    EAttribute att3ID = EcoreFactory.eINSTANCE.createEAttribute();
    class3.getEStructuralFeatures().add(att3ID);
    att3ID.setName("att3");
    att3ID.setID(true);
    att3ID.setEType(EcorePackage.Literals.ESTRING);
    
    EReference singleReference = EcoreFactory.eINSTANCE.createEReference();
    class1.getEStructuralFeatures().add(singleReference);
    singleReference.setEType(class2);
    singleReference.setName("SingleReference1");
    
    EReference multiReference = EcoreFactory.eINSTANCE.createEReference();
    class1.getEStructuralFeatures().add(multiReference);
    multiReference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    multiReference.setEType(class2);
    multiReference.setName("multiReference1");
     
    EReference containmentMultiReference = EcoreFactory.eINSTANCE.createEReference();
    class2.getEStructuralFeatures().add(containmentMultiReference);
    containmentMultiReference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    containmentMultiReference.setEType(class2);
    containmentMultiReference.setName("containmentMultiReference1");
    containmentMultiReference.setContainment(true);

    EReference containmentMultiKeyReference = EcoreFactory.eINSTANCE.createEReference();
    class2.getEStructuralFeatures().add(containmentMultiKeyReference);
    containmentMultiKeyReference.setUpperBound(ETypedElement.UNBOUNDED_MULTIPLICITY);
    containmentMultiKeyReference.setEType(class2);
    containmentMultiKeyReference.setName("containmentMultiReference2");
    containmentMultiKeyReference.setContainment(true);
    containmentMultiKeyReference.getEKeys().add(att1);
    containmentMultiKeyReference.getEKeys().add(att2);
    
    EObject obj21 = pack.getEFactoryInstance().create(class2);
    obj21.eSet(att1, "obj21.att1");
    obj21.eSet(att2, "obj21.att2");
    EObject obj22 = pack.getEFactoryInstance().create(class2);
    obj22.eSet(att1, "obj22.att1 % / \\");
    EObject obj23 = pack.getEFactoryInstance().create(class2);
    obj23.eSet(att2, "obj23.att2");
    EObject obj24 = pack.getEFactoryInstance().create(class2);
    obj24.eSet(att1, "obj24.att1");
    obj24.eSet(att2, "obj24.att2");
    //
    EObject obj31 = pack.getEFactoryInstance().create(class3);
    obj31.eSet(att1, "obj31.att1");
    obj31.eSet(att2, "obj31.att2");
    obj31.eSet(att3ID, "obj31.att3ID");
    EObject obj32 = pack.getEFactoryInstance().create(class3);
    obj32.eSet(att3ID, "obj32.att3ID");
    EObject obj33 = pack.getEFactoryInstance().create(class3);
    obj33.eSet(att1, "obj33.att1");
    obj33.eSet(att2, "obj33.att2");
    EObject obj34 = pack.getEFactoryInstance().create(class3);
    obj34.eSet(att1, "obj34.att1");
    obj34.eSet(att3ID, "obj34.att3ID");
    EObject obj35 = pack.getEFactoryInstance().create(class3);
    obj35.eSet(att1, "obj35.att1");
    obj35.eSet(att2, "obj35.att2");
    EObject obj36 = pack.getEFactoryInstance().create(class3);
    obj36.eSet(att1, "obj36.att1");
         
    @SuppressWarnings("unchecked")
    List<EObject> list21MKR = ((List<EObject>)obj21.eGet(containmentMultiKeyReference));
    list21MKR.add(obj22);
    list21MKR.add(obj23);
    list21MKR.add(obj24);
    
    @SuppressWarnings("unchecked")
    List<EObject> list31MKR = ((List<EObject>)obj31.eGet(containmentMultiKeyReference));
    list31MKR.add(obj32);
    list31MKR.add(obj33);
    list31MKR.add(obj34);
    
    @SuppressWarnings("unchecked")
    List<EObject> list31MR = ((List<EObject>)obj31.eGet(containmentMultiReference));
    list31MR.add(obj35);
    list31MR.add(obj36);
    
    EObject obj11 = pack.getEFactoryInstance().create(class1);
    obj11.eSet(att0, "obj11.att0");
    obj11.eSet(singleReference, obj23);
    
    @SuppressWarnings("unchecked")
    List<EObject> list11MR = ((List<EObject>)obj11.eGet(multiReference));
    list11MR.add(obj21);
    list11MR.add(obj22);
    list11MR.add(obj23);
    list11MR.add(obj24);
    list11MR.add(obj31);
    list11MR.add(obj32);
    list11MR.add(obj33);
    list11MR.add(obj34);
    list11MR.add(obj35);
    list11MR.add(obj36);
    
    Resource resource1 = new XMLResourceImpl(URI.createFileURI(TEMP_FILE_DIR + "foo1.xml"));
    resource1.getContents().add(obj11);
    Resource resource2 = new XMIResourceImpl(URI.createFileURI(TEMP_FILE_DIR + "foo2.xmi"));
    resource2.getContents().add(obj21);
    resource2.getContents().add(obj31);
    
    String content1 = getResourceText(resource1);
    String content2 = getResourceText(resource2);
    if (SYSOUT)
    {
      System.out.println("======");
      System.out.println(content1);
      System.out.println("\n\n======\n");
      System.out.println(content2);
      System.out.println("\n\n======\n");
    }
    
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getPackageRegistry().put(pack.getNsURI(), pack);
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new XMLResourceFactoryImpl());
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", new XMIResourceFactoryImpl());
    
    Resource loadedResource1 = resourceSet.createResource(URI.createFileURI(TEMP_FILE_DIR + "foo1.xml"));   
    loadedResource1.load(new ByteArrayInputStream(content1.getBytes()), Collections.EMPTY_MAP);
    Resource loadedResource2 = resourceSet.createResource(URI.createFileURI(TEMP_FILE_DIR + "foo2.xmi"));
    loadedResource2.load(new ByteArrayInputStream(content2.getBytes()), Collections.EMPTY_MAP);
    
    EObject loadedObj21 = loadedResource2.getContents().get(0);
    assertEquals(obj21.eGet(att1), loadedObj21.eGet(att1));
    assertEquals(obj21.eGet(att2), loadedObj21.eGet(att2));
    EObject loadedObj22 = (EObject)((List<?>)loadedObj21.eGet(containmentMultiKeyReference)).get(0);
    assertEquals(obj22.eGet(att1), loadedObj22.eGet(att1));
    assertEquals(obj22.eGet(att2), loadedObj22.eGet(att2));
    EObject loadedObj23 = (EObject)((List<?>)loadedObj21.eGet(containmentMultiKeyReference)).get(1);
    assertEquals(obj23.eGet(att1), loadedObj23.eGet(att1));
    assertEquals(obj23.eGet(att2), loadedObj23.eGet(att2));
    EObject loadedObj24 = (EObject)((List<?>)loadedObj21.eGet(containmentMultiKeyReference)).get(2);
    assertEquals(obj24.eGet(att1), loadedObj24.eGet(att1));
    assertEquals(obj24.eGet(att2), loadedObj24.eGet(att2));
    EObject loadedObj31 = loadedResource2.getContents().get(1);
    assertEquals(obj31.eGet(att1), loadedObj31.eGet(att1));
    assertEquals(obj31.eGet(att2), loadedObj31.eGet(att2));
    assertEquals(obj31.eGet(att3ID), loadedObj31.eGet(att3ID));
    EObject loadedObj32 = (EObject)((List<?>)loadedObj31.eGet(containmentMultiKeyReference)).get(0);
    assertEquals(obj32.eGet(att1), loadedObj32.eGet(att1));
    assertEquals(obj32.eGet(att2), loadedObj32.eGet(att2));
    assertEquals(obj32.eGet(att3ID), loadedObj32.eGet(att3ID));
    EObject loadedObj33 = (EObject)((List<?>)loadedObj31.eGet(containmentMultiKeyReference)).get(1);
    assertEquals(obj33.eGet(att1), loadedObj33.eGet(att1));
    assertEquals(obj33.eGet(att2), loadedObj33.eGet(att2));
    assertEquals(obj33.eGet(att3ID), loadedObj33.eGet(att3ID));
    EObject loadedObj34 = (EObject)((List<?>)loadedObj31.eGet(containmentMultiKeyReference)).get(2);
    assertEquals(obj34.eGet(att1), loadedObj34.eGet(att1));
    assertEquals(obj34.eGet(att2), loadedObj34.eGet(att2));
    assertEquals(obj34.eGet(att3ID), loadedObj34.eGet(att3ID));
    
    EObject loadedObj35 = (EObject)((List<?>)loadedObj31.eGet(containmentMultiReference)).get(0);
    assertEquals(obj35.eGet(att1), loadedObj35.eGet(att1));
    assertEquals(obj35.eGet(att2), loadedObj35.eGet(att2));
    assertEquals(obj35.eGet(att3ID), loadedObj35.eGet(att3ID));
    EObject loadedObj36 = (EObject)((List<?>)loadedObj31.eGet(containmentMultiReference)).get(1);
    assertEquals(obj36.eGet(att1), loadedObj36.eGet(att1));
    assertEquals(obj36.eGet(att2), loadedObj36.eGet(att2));
    assertEquals(obj36.eGet(att3ID), loadedObj36.eGet(att3ID));
        
    EObject loadedObj11 = loadedResource1.getContents().get(0);
    assertEquals(obj11.eGet(att0), loadedObj11.eGet(att0));    
    assertEquals(loadedObj23, loadedObj11.eGet(singleReference));
    assertEquals(loadedObj21, ((List<?>)loadedObj11.eGet(multiReference)).get(0));
    assertEquals(loadedObj22, ((List<?>)loadedObj11.eGet(multiReference)).get(1));
    assertEquals(loadedObj23, ((List<?>)loadedObj11.eGet(multiReference)).get(2));
    assertEquals(loadedObj24, ((List<?>)loadedObj11.eGet(multiReference)).get(3));
    assertEquals(loadedObj31, ((List<?>)loadedObj11.eGet(multiReference)).get(4));
    assertEquals(loadedObj32, ((List<?>)loadedObj11.eGet(multiReference)).get(5));
    assertEquals(loadedObj33, ((List<?>)loadedObj11.eGet(multiReference)).get(6));
    assertEquals(loadedObj34, ((List<?>)loadedObj11.eGet(multiReference)).get(7));
    assertEquals(loadedObj35, ((List<?>)loadedObj11.eGet(multiReference)).get(8));
    assertEquals(loadedObj36, ((List<?>)loadedObj11.eGet(multiReference)).get(9));
  }
  
  protected String getResourceText(Resource resource) throws Exception
  {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    resource.save(outputStream, Collections.EMPTY_MAP);
    return new String(outputStream.toByteArray());
  }
  
  public void testConstraint()
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    
    resourceSet.getPackageRegistry().put(KeyPackage.eNS_URI, KeyPackage.eINSTANCE);
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put
      (Resource.Factory.Registry.DEFAULT_EXTENSION, new XMLResourceFactoryImpl());
    
    Resource resource = resourceSet.createResource(URI.createURI("http:///My.key"));
    
    Root root = KeyFactory.eINSTANCE.createRoot();

    {
      Item item = KeyFactory.eINSTANCE.createItem();
      root.getItems().add(item);
    }

    {
      Item itemA = KeyFactory.eINSTANCE.createItem();
      itemA.setName("a");
      root.getItems().add(itemA);
    }

    {
      Item itemAXY = KeyFactory.eINSTANCE.createItem();
      itemAXY.setName("a");
      itemAXY.getSignature().add("org.eclipse.example.X");
      itemAXY.getSignature().add("org.eclipse.example.Y");
      itemAXY.getRelatedItems().add(itemAXY);
      root.getItems().add(itemAXY);
    }

    {
      Item itemAX = KeyFactory.eINSTANCE.createItem();
      itemAX.setName("a");
      itemAX.getSignature().add("org.eclipse.example.X");
      itemAX.getRelatedItems().add(itemAX);
      root.getItems().add(itemAX);
    }

    {
      Item itemANull = KeyFactory.eINSTANCE.createItem();
      itemANull.setName("a");
      itemANull.getSignature().add(null);
      root.getItems().add(itemANull);
    }

    {
      Item itemA = KeyFactory.eINSTANCE.createItem();
      itemA.setName("a");
      root.getItems().add(itemA);
    }
    
    resource.getContents().add(root);
    Diagnostic diagnostic = Diagnostician.INSTANCE.validate(root);
    assertEquals(diagnostic.getSeverity(), Diagnostic.ERROR);
    assertEquals(diagnostic.getChildren().size(), 1);
    if (SYSOUT) System.err.println(diagnostic.getChildren().get(0));
  }
}
