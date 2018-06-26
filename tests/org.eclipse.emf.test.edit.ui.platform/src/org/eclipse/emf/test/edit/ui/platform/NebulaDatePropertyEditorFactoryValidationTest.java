/**
 * Copyright (c) 2018 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.edit.ui.platform;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.edit.ui.provider.NebulaDatePropertyEditorFactory;
import org.eclipse.emf.edit.ui.provider.NebulaDatePropertyEditorFactory.ValueConverter;
import org.eclipse.emf.test.common.TestUtil;
import org.junit.Assert;
import org.junit.Test;


public class NebulaDatePropertyEditorFactoryValidationTest
{
  private static final EPackage NEBULA_PACKAGE;

  private static final Map<Integer, String> FAILURE_CODES;

  static
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    URI logicalURI = URI.createPlatformPluginURI("platform:/plugin/org.eclipse.emf.test.edit.ui.platform/data/NebulaPropertyEditorFactory.ecore", false);
    URI physicalURI = URI.createFileURI(TestUtil.getPluginDirectory("org.eclipse.emf.test.edit.ui.platform") + "/data/NebulaPropertyEditorFactory.ecore");
    resourceSet.getURIConverter().getURIMap().put(logicalURI, physicalURI);
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    Resource ecoreResource = resourceSet.getResource(logicalURI, true);
    NEBULA_PACKAGE = (EPackage)ecoreResource.getContents().get(0);

    Map<Integer, String> failureCodes = new HashMap<Integer, String>();
    for (Field field : NebulaDatePropertyEditorFactory.class.getDeclaredFields())
    {
      int modifiers = field.getModifiers();
      if (field.getType() == int.class && Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isStatic(modifiers))
      {
        try
        {
          failureCodes.put(field.getInt(null), field.getName());
        }
        catch (Exception exception)
        {
          // Ignore
        }
      }

    }
    FAILURE_CODES = failureCodes;
  }

  @Test
  public void testNebulaMissingDateFormat()
  {
    testDataType("NebulaMissingDateFormat", "", Diagnostic.ERROR, NebulaDatePropertyEditorFactory.INVALID_MISSING_FORMAT);
  }

  @Test
  public void testNebulaInvalidDateFormat()
  {
    testDataType("NebulaInvalidDateFormat", "'x'", Diagnostic.ERROR, NebulaDatePropertyEditorFactory.INVALID_FORMAT);
  }

  @Test
  public void testNebulaInvalidStyleElements()
  {
    testDataType("NebulaInvalidStyleElements", "'MM'", Diagnostic.WARNING, NebulaDatePropertyEditorFactory.INVALID_STYLE_ELEMENT);
  }

  @Test
  public void testNebulaInvalidInstanceType()
  {
    testDataType("NebulaInvalidInstanceType", "'java.lang.String'", Diagnostic.ERROR, NebulaDatePropertyEditorFactory.INVALID_INSTANCE_TYPE);
  }

  @Test
  public void testNebulaInvalidCalendarType()
  {
    testDataType("NebulaInvalidCalendarType", "'bogus'", Diagnostic.ERROR, NebulaDatePropertyEditorFactory.INVALID_CALENDAR_TYPE);
  }

  @Test
  public void testConversion()
  {
    Date date = new Date();
    for (Class<?> instanceClass : NebulaDatePropertyEditorFactory.SUPPORTED_INSTANCE_CLASSES)
    {
      ValueConverter valueConverter = NebulaDatePropertyEditorFactory.getValueConverter(instanceClass, null);
      Object value = valueConverter.createFromDate(date);
      Assert.assertTrue("Test isInstance " + instanceClass + " for '" + value + "'", (instanceClass == long.class ? Long.class : instanceClass).isInstance(value));
      Date convertedDate = valueConverter.convertToDate(value);
      Assert.assertEquals(date, convertedDate);
    }
  }

  private void testDataType(String dataTypeName, String expectedMessageSubstring, int expectedSeverity, int expectedFailureCode)
  {
    EDataType eDataType = (EDataType)NEBULA_PACKAGE.getEClassifier(dataTypeName);
    String propertyEditorFactorySpecification = EcoreUtil.getAnnotation(eDataType, EcoreUtil.GEN_MODEL_ANNOTATION_URI, "propertyEditorFactory");

    BasicDiagnostic diagnostic = new BasicDiagnostic();

    boolean result = NebulaDatePropertyEditorFactory.INSTANCE.validate(eDataType, URI.createURI(propertyEditorFactorySpecification), diagnostic, new HashMap<Object, Object>());
    Assert.assertFalse(result);

    Assert.assertEquals(expectedSeverity, diagnostic.getSeverity());
    Assert.assertEquals(1, diagnostic.getChildren().size());
    Diagnostic child = diagnostic.getChildren().get(0);
    String badMessage = child.getMessage();
    Assert.assertTrue("Message does not contain '" + expectedMessageSubstring + "': \n" + badMessage, badMessage.contains(expectedMessageSubstring));
    int badWellFormedCode = child.getCode();
    Assert.assertTrue("Bad test case expected failure code " + expectedFailureCode + " but must be in " + FAILURE_CODES, FAILURE_CODES.containsKey(expectedFailureCode));
    Assert.assertEquals("Unexpected diagnostic code: ", getFailureCode(expectedFailureCode), getFailureCode(badWellFormedCode));
    Assert.assertEquals("Unexpected diagnostic code: " + eDataType.getName() + ": " + badMessage, getFailureCode(expectedFailureCode), getFailureCode(badWellFormedCode));
    System.out.println(badMessage);
  }

  private String getFailureCode(int code)
  {
    String result = FAILURE_CODES.get(code);
    return result == null ? "Unknown: " + code : result;
  }
}
