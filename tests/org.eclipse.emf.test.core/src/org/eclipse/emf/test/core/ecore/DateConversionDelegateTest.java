/**
 * Copyright (c) 2018 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.core.ecore;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.DateConversionDelegateFactory;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.emf.test.common.TestUtil;
import org.junit.Assert;
import org.junit.Test;


public class DateConversionDelegateTest
{
  private static final EPackage DATE_CONVERSION_PACKAGE;

  private static final Map<String, String> FORMAT_LITERALS;

  private static final Map<Integer, String> FAILURE_CODES;

  static
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    URI logicalURI = URI.createPlatformPluginURI("platform:/plugin/org.eclipse.emf.test.core/data/DateConversion.ecore", false);
    URI physicalURI = URI.createFileURI(TestUtil.getPluginDirectory("org.eclipse.emf.test.core") + "/data/DateConversion.ecore");
    resourceSet.getURIConverter().getURIMap().put(logicalURI, physicalURI);
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    Resource ecoreResource = resourceSet.getResource(logicalURI, true);

    Class<?>[] validClasses = new Class []{ Date.class, /*java.sql.Date.class,*/ Calendar.class, GregorianCalendar.class, XMLGregorianCalendar.class };
    String[] formats = new String []{
      "//Long",
      "//SimpleDateFormat/yyyy-MM-dd'T'HH:mm:ss'.'SSSS",
      "//DateFormat/LONG/en/US",
      "//DateTimeFormat/LONG/LONG/en/US",
      "//TimeFormat/LONG/en/US" };

    Date date = new java.sql.Date(System.currentTimeMillis());
    Map<String, String> literals = new HashMap<String, String>();
    for (String format : formats)
    {
      if ("//Long".equals(format))
      {
        literals.put(format, Long.toString(date.getTime()));
      }
      else
      {
        DateFormat dateFormat = DateConversionDelegateFactory.getDateFormat(URI.createURI(format));
        String literal = dateFormat.format(date);
        literals.put(format, literal);
      }
    }

    FORMAT_LITERALS = literals;
    DATE_CONVERSION_PACKAGE = (EPackage)ecoreResource.getContents().get(0);

    EList<EClassifier> eClassifiers = DATE_CONVERSION_PACKAGE.getEClassifiers();
    for (Class<?> validClass : validClasses)
    {
      for (String format : formats)
      {
        EDataType eDataType = EcoreFactory.eINSTANCE.createEDataType();
        eDataType.setName("Valid_" + validClass.getName().replace('.', '_'));
        eDataType.setInstanceClass(validClass);
        EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
        eAnnotation.setSource(DateConversionDelegateFactory.ANNOTATION_URI);
        eAnnotation.getDetails().put("format", format);
        eDataType.getEAnnotations().add(eAnnotation);
        eClassifiers.add(eDataType);
      }
    }

    Map<Integer, String> failureCodes = new HashMap<Integer, String>();
    for (Field field : DateConversionDelegateFactory.AnnotationValidator.class.getDeclaredFields())
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
  public void testBadCalendarType()
  {
    testDataType("BadCalendarType", "'bad-calendar-type'", DateConversionDelegateFactory.AnnotationValidator.INVALID_CALENDAR_TYPE);
  }

  @Test
  public void testBadSimpleDateFormatMissing()
  {
    testDataType("BadSimpleDateFormatMissing", "", DateConversionDelegateFactory.AnnotationValidator.INVALID_MISSING_SIMPLE_DATE_FORMAT);
  }

  @Test
  public void testBadSimpleDateFormatEmpty()
  {
    testDataType("BadSimpleDateFormatEmpty", "", DateConversionDelegateFactory.AnnotationValidator.INVALID_MISSING_SIMPLE_DATE_FORMAT);
  }

  @Test
  public void testBadDateFormatMissing()
  {
    testDataType("BadDateFormatMissing", "", DateConversionDelegateFactory.AnnotationValidator.INVALID_MISSING_DATE_FORMAT);
  }

  @Test
  public void testBadDateFormatBadDateStyle()
  {
    testDataType("BadDateFormatBadDateStyle", "'DEFAULT'", DateConversionDelegateFactory.AnnotationValidator.INVALID_DATE_STYLE);
  }

  @Test
  public void testBadTimeFormatMissing()
  {
    testDataType("BadTimeFormatMissing", "", DateConversionDelegateFactory.AnnotationValidator.INVALID_MISSING_TIME_FORMAT);
  }

  @Test
  public void testBadDateFormatBadTimeStyle()
  {
    testDataType("BadDateFormatBadTimeStyle", "'DEFAULT'", DateConversionDelegateFactory.AnnotationValidator.INVALID_TIME_STYLE);
  }

  @Test
  public void testBadDateTimeFormatMissing()
  {
    testDataType("BadDateTimeFormatMissing", "", DateConversionDelegateFactory.AnnotationValidator.INVALID_MISSING_DATE_TIME_FORMAT);
  }

  @Test
  public void testBadDateTimeFormatBadDateStyle()
  {
    testDataType("BadDateTimeFormatBadDateStyle", "'DEFAULT'", DateConversionDelegateFactory.AnnotationValidator.INVALID_DATE_STYLE);
  }

  @Test
  public void testBadDateTimeFormatBadTimeStyle()
  {
    testDataType("BadDateTimeFormatBadTimeStyle", "'DEFAULT'", DateConversionDelegateFactory.AnnotationValidator.INVALID_TIME_STYLE);
  }

  @Test
  public void testBadStyle()
  {
    testDataType("BadStyle", "'Bogus'", DateConversionDelegateFactory.AnnotationValidator.INVALID_STYLE);
  }

  @Test
  public void testBadLocaleMissing()
  {
    testDataType("BadLocaleMissing", "DateFormat", DateConversionDelegateFactory.AnnotationValidator.INVALID_MISSING_LOCALE);
  }

  @Test
  public void testBadLocaleBadLanguage()
  {
    testDataType("BadLocaleBadLanguage", "DateFormat", DateConversionDelegateFactory.AnnotationValidator.INVALID_LANGUAGE);
  }

  @Test
  public void testBadLocaleBadCountry()
  {
    testDataType("BadLocaleBadCountry", "DateFormat", DateConversionDelegateFactory.AnnotationValidator.INVALID_COUNTRY);
  }

  @Test
  public void testBadLocaleBadVariant()
  {
    testDataType("BadLocaleBadVariant", "'bogus'", DateConversionDelegateFactory.AnnotationValidator.INVALID_VARIANT);
  }

  @Test
  public void testBadSimpleDateFormatInvalidFormat()
  {
    testDataType("BadSimpleDateFormatInvalidFormat", "", DateConversionDelegateFactory.AnnotationValidator.INVALID_SIMPLE_DATE_FORMAT);
  }

  @Test
  public void testBadInstanceType()
  {
    testDataType("BadInstanceType", "'java.lang.String'", DateConversionDelegateFactory.AnnotationValidator.INVALID_INSTANCE_TYPE);
  }

  @Test
  public void testDateConversion()
  {
    for (EClassifier eClassifier : DATE_CONVERSION_PACKAGE.getEClassifiers())
    {
      if (eClassifier.getName().startsWith("Valid_"))
      {
        EDataType eDataType = (EDataType)eClassifier;
        String message = eDataType.getName();
        String format = EcoreUtil.getAnnotation(eDataType, DateConversionDelegateFactory.ANNOTATION_URI, "format");
        Assert.assertNotNull(message, format);
        message += ": " + format;
        String literal = FORMAT_LITERALS.get(format);
        Assert.assertNotNull(message, literal);
        message += " -> " + literal;
        Object value = EcoreUtil.createFromString(eDataType, literal);
        message += " : " + value.getClass().getName();
        Assert.assertTrue(message, eDataType.getInstanceClass().isInstance(value));
        String convertedLiteral = EcoreUtil.convertToString(eDataType, value);
        Assert.assertEquals(message, literal, convertedLiteral);
      }
    }
  }

  private void testDataType(String dataTypeName, String expectedMessageSubstring, int expectedFailureCode)
  {
    EDataType eDataType = (EDataType)DATE_CONVERSION_PACKAGE.getEClassifier(dataTypeName);
    EAnnotation eAnnotation = eDataType.getEAnnotation(DateConversionDelegateFactory.ANNOTATION_URI);
    Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eAnnotation);
    Assert.assertEquals(Diagnostic.ERROR, diagnostic.getSeverity());
    Assert.assertEquals(1, diagnostic.getChildren().size());
    Diagnostic child = diagnostic.getChildren().get(0);
    String badKeyMessage = child.getMessage();
    Assert.assertTrue("Message does not complain about 'format' key: \"" + badKeyMessage + "\"", badKeyMessage.contains("'format'"));
    Assert.assertEquals(1, child.getChildren().size());
    Diagnostic grandChild = child.getChildren().get(0);
    String badWellFormedMessage = grandChild.getMessage();
    Assert.assertTrue("Message does not contain '" + expectedMessageSubstring + "': \n" + badWellFormedMessage, badWellFormedMessage.contains(expectedMessageSubstring));
    int badWellFormedCode = grandChild.getCode();
    Assert.assertTrue("Bad test case expected failure code " + expectedFailureCode + " but must be in " + FAILURE_CODES, FAILURE_CODES.containsKey(expectedFailureCode));
    Assert.assertEquals("Unexpected diagnostic code: ", getFailureCode(expectedFailureCode), getFailureCode(badWellFormedCode));
    Assert.assertEquals("Unexpected diagnostic code: " + eDataType.getName() + ": " + badWellFormedMessage, getFailureCode(expectedFailureCode), getFailureCode(badWellFormedCode));
    // System.out.println(badWellFormedMessage);
  }

  private String getFailureCode(int code)
  {
    String result = FAILURE_CODES.get(code);
    return result == null ? "Unknown: " + code : result;
  }
}
