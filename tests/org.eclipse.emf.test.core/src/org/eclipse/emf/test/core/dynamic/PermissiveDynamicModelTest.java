/**
 * Copyright (c) 2022 Eclipse Contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.core.dynamic;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.eclipse.emf.test.core.AllSuites;
import org.eclipse.emf.test.models.dynamic.Admin;
import org.eclipse.emf.test.models.dynamic.Detail;
import org.eclipse.emf.test.models.dynamic.DynamicFactory;
import org.eclipse.emf.test.models.dynamic.DynamicPackage;
import org.eclipse.emf.test.models.dynamic.Provider;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class PermissiveDynamicModelTest
{
  private ResourceSet resourceSet;

  private EPackage dynamicExtensionPackage;

  @Before
  public void setUp() throws Exception
  {
    resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new XMLResourceFactoryImpl());
    URI dynamicExtensionEcoreURI = URI.createFileURI(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID) + "/data/DynamicExtension.ecore");
    URI developmentTimeDynamicEcore = URI.createURI("../../org.eclipse.emf.test.common/models/Dynamic/Dynamic.ecore").resolve(dynamicExtensionEcoreURI);
    resourceSet.getPackageRegistry().put(developmentTimeDynamicEcore.toString(), DynamicPackage.eINSTANCE);
    dynamicExtensionPackage = (EPackage)resourceSet.getEObject(dynamicExtensionEcoreURI.appendFragment("/"), true);
    EcoreUtil.resolveAll(dynamicExtensionPackage);
    resourceSet.getPackageRegistry().put(dynamicExtensionPackage.getNsURI(), dynamicExtensionPackage);
  }

  @After
  public void tearDown() throws Exception
  {
    resourceSet = null;
    dynamicExtensionPackage = null;
  }

  @Test
  public void testNonDynamicUseage()
  {
    Admin admin = DynamicFactory.eINSTANCE.createAdmin();
    assertNotNull(admin.eClass());
    admin.eSet(DynamicPackage.Literals.ADMIN__NAME, "admin");
    assertTrue(admin.eIsSet(DynamicPackage.Literals.ADMIN__NAME));
    assertEquals("admin", admin.eGet(DynamicPackage.Literals.ADMIN__NAME));
  }

  @Test
  public void testAll()
  {
    // Create an extended Admin instance which will necessarily be an Admin instance as well.
    EClass extendedAdmin = getEClass("ExtendedAdmin");
    Admin extendedAdminInstance = (Admin)EcoreUtil.create(extendedAdmin);

    // Set the attributes of the generated class.
    extendedAdminInstance.setName("name");
    extendedAdminInstance.setLocation("location");
    String extendedName = "extendedName";

    // Set the dynamic attribute.
    set(extendedAdminInstance, extendedName, extendedName);

    // Modify the set of features by adding super type with additional features.
    EClass mixin1 = getEClass("Mixin1");
    extendedAdmin.getESuperTypes().add(mixin1);

    // We should still be able to fetch the value we set earlier.
    assertEquals(extendedName, get(extendedAdminInstance, extendedName));

    // Set the dynamic features for a Mixin1
    String dataAttributeValue1 = "dataAttributeValue1";
    set(extendedAdminInstance, "dataAttribute1", dataAttributeValue1);

    // Populate the containment reference introduced by Mixin1.
    EList<EObject> dataReferences = get(extendedAdminInstance, "dataReferences1");
    EClass data = getEClass("Data");
    EObject dataInstance = EcoreUtil.create(data);
    dataReferences.add(dataInstance);
    assertCopyAndSerializationRoundTrip(extendedAdminInstance);

    // Dynamically modify the Data1 class by adding the Mixin1 class to its super types.
    data.getESuperTypes().add(mixin1);
    set(dataInstance, "dataAttribute1", dataAttributeValue1);
    assertCopyAndSerializationRoundTrip(extendedAdminInstance);

    // Dynamically modify the Data1 class by also adding the Mixin2 class to its super types.
    EClass mixin2 = getEClass("Mixin2");
    data.getESuperTypes().add(mixin2);

    // We can now set attributes from Mixin2.
    String dataAttributeValue2 = "dataAttributeValue2";
    set(dataInstance, "dataAttribute2", dataAttributeValue2);
    assertCopyAndSerializationRoundTrip(extendedAdminInstance);

    assertEquals(dataAttributeValue1, get(dataInstance, "dataAttribute1"));

    // We can even reorder things, preserving the settings.
    mixin2.getEStructuralFeatures().move(0, 1);
    assertEquals(dataAttributeValue1, get(dataInstance, "dataAttribute1"));
    assertEquals(dataAttributeValue2, get(dataInstance, "dataAttribute2"));
    assertCopyAndSerializationRoundTrip(extendedAdminInstance);

    // Create an extended provider and fully populate its features
    EClass extendedProvider = getEClass("ExtendedProvider");
    Provider extendedProviderInstance = (Provider)EcoreUtil.create(extendedProvider);
    extendedProviderInstance.setAdmin(extendedAdminInstance);
    extendedProviderInstance.setId("id");
    extendedProviderInstance.setAdmin(extendedAdminInstance);
    set(extendedProviderInstance, "extendedProviderId", "extendedProviderIdValue");
    assertCopyAndSerializationRoundTrip(extendedProviderInstance);

    extendedProvider.getESuperTypes().add(mixin1);
    populateMixin1Features(extendedProviderInstance);
    assertCopyAndSerializationRoundTrip(extendedProviderInstance);

    EClass extendedDetail = getEClass("ExtendedDetail");
    Detail extendedDetailInstance = (Detail)EcoreUtil.create(extendedDetail);
    extendedAdminInstance.getDetails().add(extendedDetailInstance);
    assertCopyAndSerializationRoundTrip(extendedProviderInstance);

    extendedDetail.getESuperTypes().add(mixin1);
    populateMixin1Features(extendedDetailInstance);
    assertCopyAndSerializationRoundTrip(extendedProviderInstance);

    extendedDetail.getESuperTypes().add(mixin2);
    populateMixin2Features(extendedDetailInstance);
    String xml1 = assertCopyAndSerializationRoundTrip(extendedProviderInstance).replace("\r", "");

    // @formatter:off
    String expected1 =
          "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
        + "<dynamic-extension:ExtendedProvider\n"
        + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
        + "    xmlns:dynamic-extension=\"platform:/resource/org.eclipse.emf.test.common/models/Dynamic/Dynamic.ecore-extension\"\n"
        + "    id=\"id\"\n"
        + "    dataAttribute1=\"dataAttributeValue1\"\n"
        + "    extendedProviderId=\"extendedProviderIdValue\">\n"
        + "  <admin xsi:type=\"dynamic-extension:ExtendedAdmin\"\n"
        + "      name=\"name\"\n"
        + "      location=\"location\"\n"
        + "      dataAttribute1=\"dataAttributeValue1\"\n"
        + "      extendedName=\"extendedName\">\n"
        + "    <details\n"
        + "        xsi:type=\"dynamic-extension:ExtendedDetail\"\n"
        + "        dataAttribute1=\"dataAttributeValue1\"\n"
        + "        dataAttribute2=\"dataAttributeValue2\">\n"
        + "      <dataReferences1/>\n"
        + "      <dataReferences2/>\n"
        + "    </details>\n"
        + "    <dataReferences1\n"
        + "        dataAttribute1=\"dataAttributeValue1\"\n"
        + "        dataAttribute2=\"dataAttributeValue2\"/>\n"
        + "  </admin>\n"
        + "  <dataReferences1/>\n"
        + "</dynamic-extension:ExtendedProvider>\n" ;
    // @formatter:on

    assertEquals(expected1, xml1);

    // We can even remove things.
    mixin2.getEStructuralFeatures().remove(mixin2.getEStructuralFeature("dataAttribute2"));
    String xml2 = assertCopyAndSerializationRoundTrip(extendedProviderInstance).replace("\r", "");

    // @formatter:off
    String expected2 =
          "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
        + "<dynamic-extension:ExtendedProvider\n"
        + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
        + "    xmlns:dynamic-extension=\"platform:/resource/org.eclipse.emf.test.common/models/Dynamic/Dynamic.ecore-extension\"\n"
        + "    id=\"id\"\n"
        + "    dataAttribute1=\"dataAttributeValue1\"\n"
        + "    extendedProviderId=\"extendedProviderIdValue\">\n"
        + "  <admin xsi:type=\"dynamic-extension:ExtendedAdmin\"\n"
        + "      name=\"name\"\n"
        + "      location=\"location\"\n"
        + "      dataAttribute1=\"dataAttributeValue1\"\n"
        + "      extendedName=\"extendedName\">\n"
        + "    <details\n"
        + "        xsi:type=\"dynamic-extension:ExtendedDetail\"\n"
        + "        dataAttribute1=\"dataAttributeValue1\">\n"
        + "      <dataReferences1/>\n"
        + "      <dataReferences2/>\n"
        + "    </details>\n"
        + "    <dataReferences1\n"
        + "        dataAttribute1=\"dataAttributeValue1\"/>\n"
        + "  </admin>\n"
        + "  <dataReferences1/>\n"
        + "</dynamic-extension:ExtendedProvider>\n";
    // @formatter:on

    assertEquals(expected2, xml2);
  }

  private void populateMixin1Features(EObject mixin1Instance)
  {
    // Set a dynamic attribute for an attribute from Mixin1
    String dataAttributeValue1 = "dataAttributeValue1";
    set(mixin1Instance, "dataAttribute1", dataAttributeValue1);

    // Populate the containment reference introduced by Mixin1.
    EList<EObject> dataReferences = get(mixin1Instance, "dataReferences1");
    EClass data = getEClass("Data");
    EObject dataInstance = EcoreUtil.create(data);
    dataReferences.add(dataInstance);
  }

  private void populateMixin2Features(EObject mixin2Instance)
  {
    // Set a dynamic attribute for an attribute from Mixin2
    String dataAttributeValue2 = "dataAttributeValue2";
    set(mixin2Instance, "dataAttribute2", dataAttributeValue2);

    // Populate the containment reference introduced by Mixin2.
    EList<EObject> dataReferences = get(mixin2Instance, "dataReferences2");
    EClass data1 = getEClass("Data");
    EObject data1Instance = EcoreUtil.create(data1);
    dataReferences.add(data1Instance);
  }

  private String assertCopyAndSerializationRoundTrip(EObject... eObjects)
  {
    String xml = null;
    try
    {
      xml = toXML(eObjects);
      List<? extends EObject> copy = fromXML(xml);
      Assert.assertTrue("Serializing and deserializing should create a structuraly equal copy.", EcoreUtil.equals(Arrays.asList(eObjects), copy));
    }
    catch (IOException e)
    {
      Assert.fail(e.getMessage());
    }

    List<EObject> copy = new ArrayList<EObject>(EcoreUtil.copyAll(Arrays.asList(eObjects)));
    Assert.assertTrue("Copying should create a structuraly equal copy.", EcoreUtil.equals(Arrays.asList(eObjects), copy));

    return xml;
  }

  private List<? extends EObject> fromXML(String xml) throws IOException
  {
    URI uri = URI.createURI("test.xml");
    Resource resource = resourceSet.createResource(uri);
    InputStream in = new URIConverter.ReadableInputStream(xml);
    resource.load(in, null);
    in.close();
    resourceSet.getResources().remove(resource);
    return resource.getContents();
  }

  private String toXML(EObject... eObjects) throws IOException
  {
    URI uri = URI.createURI("test.xml");
    Resource resource = resourceSet.createResource(uri);
    resource.getContents().addAll(Arrays.asList(eObjects));
    StringWriter writer = new StringWriter();
    OutputStream out = new URIConverter.WriteableOutputStream(writer, "UTF-8");
    resource.save(out, Collections.singletonMap(XMLResource.OPTION_LINE_WIDTH, 10));
    out.close();
    resourceSet.getResources().remove(resource);
    return writer.toString();
  }

  private void set(EObject eObject, String featureName, Object value)
  {
    eObject.eSet(eObject.eClass().getEStructuralFeature(featureName), value);
  }

  @SuppressWarnings("unchecked")
  private <T> T get(EObject eObject, String featureName)
  {
    return (T)eObject.eGet(eObject.eClass().getEStructuralFeature(featureName));
  }

  private EClass getEClass(String name)
  {
    return (EClass)dynamicExtensionPackage.getEClassifier(name);
  }
}