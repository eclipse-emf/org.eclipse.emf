/**
 * Copyright (c) 2018 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.core.ecore;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

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

  private static final Map<String, Set<String>> FORMAT_LITERALS;

  private static final Map<Integer, String> FAILURE_CODES;

  static
  {
    ResourceSet resourceSet = new ResourceSetImpl();
    URI logicalURI = URI.createPlatformPluginURI("platform:/plugin/org.eclipse.emf.test.core/data/DateConversion.ecore", false);
    URI physicalURI = URI.createFileURI(TestUtil.getPluginDirectory("org.eclipse.emf.test.core") + "/data/DateConversion.ecore");
    resourceSet.getURIConverter().getURIMap().put(logicalURI, physicalURI);
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
    Resource ecoreResource = resourceSet.getResource(logicalURI, true);

    Class<?>[] validClasses = new Class []{ long.class, Long.class, Date.class, /*java.sql.Date.class,*/ Calendar.class, GregorianCalendar.class, XMLGregorianCalendar.class };
    String[] formats = new String []{
      "//Long",
      "//SimpleDateFormat/yyyy-MM-dd'T'HH:mm:ss'.'SSSS",
      "//DateFormat/LONG/en/US",
      "//DateTimeFormat/LONG/LONG/en/US",
      "//TimeFormat/LONG/en/US" // 
    };

    Map<String, Set<String>> literals = new HashMap<String, Set<String>>();
    for (Date date : new Date []{ new java.sql.Date(0L), new java.sql.Date(System.currentTimeMillis()) })
    {
      for (String format : formats)
      {
        Set<String> formatters = literals.get(format);
        if (formatters == null)
        {
          formatters = new LinkedHashSet<String>();
          literals.put(format, formatters);
        }
        if ("//Long".equals(format))
        {
          formatters.add(Long.toString(date.getTime()));
        }
        else
        {
          DateFormat dateFormat = DateConversionDelegateFactory.getDateFormat(URI.createURI(format));
          String literal = dateFormat.format(date);
          formatters.add(literal);
        }
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
    testDataTypeValidation("BadCalendarType", "'bad-calendar-type'", DateConversionDelegateFactory.AnnotationValidator.INVALID_CALENDAR_TYPE);
  }

  @Test
  public void testBadSimpleDateFormatMissing()
  {
    testDataTypeValidation("BadSimpleDateFormatMissing", "", DateConversionDelegateFactory.AnnotationValidator.INVALID_MISSING_SIMPLE_DATE_FORMAT);
  }

  @Test
  public void testBadSimpleDateFormatEmpty()
  {
    testDataTypeValidation("BadSimpleDateFormatEmpty", "", DateConversionDelegateFactory.AnnotationValidator.INVALID_MISSING_SIMPLE_DATE_FORMAT);
  }

  @Test
  public void testBadDateFormatMissing()
  {
    testDataTypeValidation("BadDateFormatMissing", "", DateConversionDelegateFactory.AnnotationValidator.INVALID_MISSING_DATE_FORMAT);
  }

  @Test
  public void testBadDateFormatBadDateStyle()
  {
    testDataTypeValidation("BadDateFormatBadDateStyle", "'DEFAULT'", DateConversionDelegateFactory.AnnotationValidator.INVALID_DATE_STYLE);
  }

  @Test
  public void testBadTimeFormatMissing()
  {
    testDataTypeValidation("BadTimeFormatMissing", "", DateConversionDelegateFactory.AnnotationValidator.INVALID_MISSING_TIME_FORMAT);
  }

  @Test
  public void testBadDateFormatBadTimeStyle()
  {
    testDataTypeValidation("BadDateFormatBadTimeStyle", "'DEFAULT'", DateConversionDelegateFactory.AnnotationValidator.INVALID_TIME_STYLE);
  }

  @Test
  public void testBadDateTimeFormatMissing()
  {
    testDataTypeValidation("BadDateTimeFormatMissing", "", DateConversionDelegateFactory.AnnotationValidator.INVALID_MISSING_DATE_TIME_FORMAT);
  }

  @Test
  public void testBadDateTimeFormatBadDateStyle()
  {
    testDataTypeValidation("BadDateTimeFormatBadDateStyle", "'DEFAULT'", DateConversionDelegateFactory.AnnotationValidator.INVALID_DATE_STYLE);
  }

  @Test
  public void testBadDateTimeFormatBadTimeStyle()
  {
    testDataTypeValidation("BadDateTimeFormatBadTimeStyle", "'DEFAULT'", DateConversionDelegateFactory.AnnotationValidator.INVALID_TIME_STYLE);
  }

  @Test
  public void testBadStyle()
  {
    testDataTypeValidation("BadStyle", "'Bogus'", DateConversionDelegateFactory.AnnotationValidator.INVALID_STYLE);
  }

  @Test
  public void testBadLocaleMissing()
  {
    testDataTypeValidation("BadLocaleMissing", "DateFormat", DateConversionDelegateFactory.AnnotationValidator.INVALID_MISSING_LOCALE);
  }

  @Test
  public void testBadLocaleBadLanguage()
  {
    testDataTypeValidation("BadLocaleBadLanguage", "DateFormat", DateConversionDelegateFactory.AnnotationValidator.INVALID_LANGUAGE);
  }

  @Test
  public void testBadLocaleBadCountry()
  {
    testDataTypeValidation("BadLocaleBadCountry", "DateFormat", DateConversionDelegateFactory.AnnotationValidator.INVALID_COUNTRY);
  }

  @Test
  public void testBadLocaleBadVariant()
  {
    testDataTypeValidation("BadLocaleBadVariant", "'bogus'", DateConversionDelegateFactory.AnnotationValidator.INVALID_VARIANT);
  }

  @Test
  public void testBadSimpleDateFormatInvalidFormat()
  {
    testDataTypeValidation("BadSimpleDateFormatInvalidFormat", "", DateConversionDelegateFactory.AnnotationValidator.INVALID_SIMPLE_DATE_FORMAT);
  }

  @Test
  public void testBadInstanceType()
  {
    testDataTypeValidation("BadInstanceType", "'java.lang.String'", DateConversionDelegateFactory.AnnotationValidator.INVALID_INSTANCE_TYPE);
  }

  @Test
  public void testDateConversion()
  {
    for (EClassifier eClassifier : DATE_CONVERSION_PACKAGE.getEClassifiers())
    {
      if (eClassifier.getName().startsWith("Valid_"))
      {
        EDataType eDataType = (EDataType)eClassifier;
        String format = EcoreUtil.getAnnotation(eDataType, DateConversionDelegateFactory.ANNOTATION_URI, "format");
        Assert.assertNotNull(eDataType.getName(), format);
        for (String literal : FORMAT_LITERALS.get(format))
        {
          testDateConversion(eDataType, format, literal);
        }
      }
    }
  }

  private void testDateConversion(EDataType eDataType, String format, String literal)
  {
    String message = eDataType.getName();
    message += ": " + format;
    Assert.assertNotNull(message, literal);
    message += " -> " + literal;
    Object value = EcoreUtil.createFromString(eDataType, literal);
    message += " : " + value.getClass().getName();
    Class<?> instanceClass = eDataType.getInstanceClass();
    if (instanceClass.isPrimitive())
    {
      Assert.assertTrue(message + " -> " + value.getClass(), Long.class.isInstance(value));
    }
    else
    {
      Assert.assertTrue(message + " -> " + value.getClass(), instanceClass.isInstance(value));
    }
    String convertedLiteral = EcoreUtil.convertToString(eDataType, value);
    Assert.assertEquals(message, literal, convertedLiteral);
  }

  private void testDataTypeValidation(String dataTypeName, String expectedMessageSubstring, int expectedFailureCode)
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
