/**
 * Copyright (c) 2018 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.util;


import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAnnotationValidator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EDataType.Internal.ConversionDelegate;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.plugin.EcorePlugin;


/**
 * A {@link org.eclipse.emf.ecore.EDataType.Internal.ConversionDelegate.Factory conversion delegate factory} 
 * for converting {@link Long#TYPE long}, {@link Long} {@link Date}, {@link java.sql.Date}, {@link Calendar}, {@link GregorianCalendar}, and {@link XMLGregorianCalendar} instances to and from a string representation.
 * <p>
 * The annotation detail for the {@code "format"} key of this annotation must be present and the value must be one of the following forms:
 * <p>
 * <pre>
 * {@code //Long}
 * {@code //SimpleDateFormat/<simple-date-format-pattern>/<language>/<country>/<variant>}
 * {@code //DateFormat/<date-style>/<language>/<country>/<variant>}
 * {@code //DateTimeFormat/<date-style>/<time-style>/<language>/<country>/<variant>}
 * {@code //TimeFormat/<time-style>/<language>/<country>/<variant>}
 * </pre>
 * <p>
 * The each of the value above have the following corresponding meaning:
 * </p>
 * <ul>
 *   <li>
 *   <b>Long</b> - The value will be converted to and from a long instant in time value using the lexical representation of {@link Long}.
 *   </li>
 *   <li>
 *   <b>SimpleDateFormat</b> - The value will be converted to and from a string representation using a {@link SimpleDateFormat} with the specified pattern.
 *   The {@link Locale locale} specified by {@code language}, {@code country}, and {@code variant} will be used to construct the SimpleDataFormat.
 *   All the locale segments are optional, but should be specified if the date format is locale-specific to ensure that the serialization is portable to any other locale.
 *   </li>
 *   <li>
 *   <b>DateFormat</b> - The value will be converted to and from a string representation using the format returned by {@link DateFormat#getDateInstance(int, Locale)}.
 *   The {@code date-style} must be one of {@code FULL}, {@code LONG}, {@code MEDIUM}, or {@code SHORT}.
 *   The {@link Locale locale} specified by {@code language}, {@code country}, and {@code variant} will be used to specify the DateFormat.
 *   The variant is optional, but the {@code language} and {@code country} must be specified to ensure that the serialization is portable to any other locale.
 *   </li>
 *   <li>
 *   <b>DateTimeFormat</b> - The value will be converted to and from a string representation using the format returned by {@link DateFormat#getDateTimeInstance(int, int, Locale)}.
 *   The {@code date-style} must be one of {@code FULL}, {@code LONG}, {@code MEDIUM}, or {@code SHORT}.
 *   The {@code time-style} must be one of {@code FULL}, {@code LONG}, {@code MEDIUM}, or {@code SHORT}.
 *   The {@link Locale locale} specified by {@code language}, {@code country}, and {@code variant} will be used to specify the DateFormat.
 *   The variant is optional, but the {@code language} and {@code country} must be specified to ensure that the serialization is portable to any other locale.
 *   </li>
 *   <li>
 *   <b>TimeFormat</b> - The value will be converted to and from a string representation using the format returned by {@link DateFormat#getTimeInstance(int, Locale)}.
 *   The {@code time-style} must be one of {@code FULL}, {@code LONG}, {@code MEDIUM}, or {@code SHORT}.
 *   The {@link Locale locale} specified by {@code language}, {@code country}, and {@code variant} will be used to specify the DateFormat.
 *   The variant is optional, but the {@code language} and {@code country} must be specified to ensure that the serialization is portable to any other locale.
 *   </li>
 * </ul>
 * <p>
 * In all cases, the {@code language}, {@code country}, and {@code variant}, when present, must specify an {@link Locale#getAvailableLocales() available} locale.
 * In the case that the data type is a {@link Calendar}, other than {@link GregorianCalendar}, 
 * the URI may include a query segment specifying the {@link Calendar#getCalendarType() calendar type}, e.g., 
 * <pre>
 * {@code //...?gregory}
 * {@code //...?japanese}
 * {@code //...?buddhist}
 * </pre>
 * When absent, {@code gregory} is the default.
 * </p>
 * <p>
 * To ensure that a serialized value is portable across all {@link TimeZone#getDefault() time zones}, 
 * for those formats based on a date format, i.e., all them except {@code //Long},
 * the associated format's {@link DateFormat#setTimeZone(TimeZone) time zone is set} to GMT .
 * </p>
 * @since 2.14
 */
public class DateConversionDelegateFactory implements EDataType.Internal.ConversionDelegate.Factory
{
  /**
   * The annotation URI of thing conversion delegate factory.
   */
  public static final String ANNOTATION_URI = "http:///org/eclipse/emf/ecore/util/DateConversionDelegate";

  /**
   * The time zone used to produce normalized portable serializations.
   */
  private static final TimeZone GMT = TimeZone.getTimeZone("GMT");

  /**
   * The date and time styles.
   * @see DateFormat#FULL
   * @see DateFormat#LONG
   * @see DateFormat#MEDIUM
   * @see DateFormat#SHORT
   */
  private static final List<String> STYLES = Collections.unmodifiableList(Arrays.asList(new String []{ "FULL", "LONG", "MEDIUM", "SHORT" }));

  /**
   * A map from language to a map from countries to a set of variants.
   */
  private static final Map<String, Map<String, Set<String>>> LOCALES;

  static
  {
    Map<String, Map<String, Set<String>>> locales = new TreeMap<String, Map<String, Set<String>>>();
    Locale[] availableLocales = Locale.getAvailableLocales();
    for (Locale locale : availableLocales)
    {
      String language = locale.getLanguage();
      Map<String, Set<String>> countries = locales.get(language);
      if (countries == null)
      {
        countries = new TreeMap<String, Set<String>>();
        locales.put(language, countries);
      }

      String country = locale.getCountry();
      Set<String> variants = countries.get(country);
      if (variants == null)
      {
        variants = new TreeSet<String>();
        countries.put(country, variants);
      }

      String variant = locale.getVariant();
      variants.add(variant);
    }

    LOCALES = locales;
  }

  /**
   * A class that encapsulates the creation of a calendar from the instant in time or a {@link Date}.
   */
  public static class CalendarBuilder
  {
    /**
     * Creates a calendar from the instance in time.
     * @param timeInstant the instant in time.
     * @return the corresponding calendar instance.
     */
    public Calendar create(long timeInstant)
    {
      GregorianCalendar gregorianCalendar = new GregorianCalendar();
      gregorianCalendar.setTime(new Date(timeInstant));
      return gregorianCalendar;
    }

    /**
     * Creates a calendar from the date.
     * @param date the date.
     * @return the corresponding calendar instance.
     */
    public Calendar create(Date date)
    {
      return create(date.getTime());
    }
  }

  /**
   * A reflectively constructed map from {@link Calendar#getCalendarType() calendar type} to calendar builder.
   */
  private static final Map<String, CalendarBuilder> CALENDAR_BUILDERS;
  static
  {
    Map<String, CalendarBuilder> calendarBuilders = new HashMap<String, CalendarBuilder>();
    calendarBuilders.put("gregory", new CalendarBuilder());
    try
    {
      // Avoid hard dependencies on Java 8.
      // For pre-Java 8, only gregory is supported.
      //
      final Class<?> calendarBuilderClass = Class.forName("java.util.Calendar$Builder");
      final Method setCalendarTypeMethod = calendarBuilderClass.getMethod("setCalendarType", String.class);
      final Method setInstantMethod = calendarBuilderClass.getMethod("setInstant", long.class);
      final Method buildMethod = calendarBuilderClass.getMethod("build");
      @SuppressWarnings("unchecked")
      Set<String> availableCalendarTypes = (Set<String>)Calendar.class.getMethod("getAvailableCalendarTypes").invoke(null);
      for (final String calendarType : availableCalendarTypes)
      {
        try
        {
          // Test that the builder will actually function when used.
          //
          Object builder = calendarBuilderClass.getDeclaredConstructor().newInstance();
          setCalendarTypeMethod.invoke(builder, calendarType);
          setInstantMethod.invoke(builder, System.currentTimeMillis());
          buildMethod.invoke(builder);

          calendarBuilders.put(calendarType, new CalendarBuilder()
            {
              @Override
              public Calendar create(long timeInstant)
              {
                try
                {
                  Object builder = calendarBuilderClass.getDeclaredConstructor().newInstance();
                  setCalendarTypeMethod.invoke(builder, calendarType);
                  setInstantMethod.invoke(builder, timeInstant);
                  return (Calendar)buildMethod.invoke(builder);
                }
                catch (Exception exception)
                {
                  throw new IllegalArgumentException(exception);
                }
              }
            });
        }
        catch (Exception exception)
        {
          // Ignore.
        }
      }
    }
    catch (Throwable throwable)
    {
      // Only gregory will be supported.
    }
    CALENDAR_BUILDERS = calendarBuilders;
  }

  public static Set<String> CALENDAR_TYPES = Collections.unmodifiableSet(new TreeSet<String>(CALENDAR_BUILDERS.keySet()));

  /**
   * Returns the calendar builder for the given {@link Calendar#getCalendarType() calendar type}.
   * The type {@code gregory} is always present.
   * @param calendarType the calendar type.
   * @return the calendar builder for the given calendar type
   */
  public static CalendarBuilder getCalendarBuilder(String calendarType)
  {
    CalendarBuilder calendarBuilder = CALENDAR_BUILDERS.get(calendarType);
    return calendarBuilder;
  }

  /**
   * {@inheritDoc}
   * <p>
   * This implementation uses the {@code "format"} detail value to create a conversion delegate.
   * </p>
   */
  public ConversionDelegate createConversionDelegate(EDataType eDataType)
  {
    String format = EcoreUtil.getAnnotation(eDataType, ANNOTATION_URI, "format");
    if (format != null)
    {
      try
      {
        return createConversionDelegate(eDataType, format);
      }
      catch (Exception exception)
      {
        EcorePlugin.INSTANCE.log(exception);
        return null;
      }
    }

    return null;
  }

  /**
   * This does all the real work for {@link #createConversionDelegate(EDataType)}.
   * It's also used for validation and throws an exception with a message explaining what's wrong in the format.
   */
  private static ConversionDelegate createConversionDelegate(EDataType eDataType, String format) throws Exception
  {
    if (format != null)
    {
      URI formatURI = URI.createURI(format);
      Class<?> instanceClass = eDataType.getInstanceClass();
      if (instanceClass == long.class || instanceClass == Long.class)
      {
        if ("Long".equals(formatURI.authority()))
        {
          return LongAsLongConversionDelegate.INSTANCE;
        }
        else
        {
          DateFormat dateFormat = getDateFormat(formatURI);
          return new LongAsDateConversionDelegate(dateFormat);
        }
      }
      else if (instanceClass == Date.class)
      {
        if ("Long".equals(formatURI.authority()))
        {
          return DateAsLongConversionDelegate.INSTANCE;
        }
        else
        {
          DateFormat dateFormat = getDateFormat(formatURI);
          return new DateConversionDelegate(dateFormat);
        }
      }
      else if (instanceClass == java.sql.Date.class)
      {
        if ("Long".equals(formatURI.authority()))
        {
          return SQLDateAsLongConversionDelegate.INSTANCE;
        }
        else
        {
          DateFormat dateFormat = getDateFormat(formatURI);
          return new SQLDateConversionDelegate(dateFormat);
        }
      }
      else if (instanceClass == Calendar.class)
      {
        String calendarType = formatURI.query();
        if (calendarType == null)
        {
          calendarType = "gregory";
        }

        CalendarBuilder calendarBuilder = CALENDAR_BUILDERS.get(calendarType);
        if (calendarBuilder == null)
        {
          AnnotationValidator.INSTANCE.throwIllegalArgumentException(AnnotationValidator.INVALID_CALENDAR_TYPE, calendarType, CALENDAR_BUILDERS.keySet());
        }

        if ("Long".equals(formatURI.authority()))
        {
          return new CalendarAsLongConversionDelegate(calendarBuilder);
        }
        else
        {
          DateFormat dateFormat = getDateFormat(formatURI);
          return new CalendarConversionDelegate(dateFormat, calendarBuilder);
        }
      }
      else if (instanceClass == GregorianCalendar.class)
      {
        CalendarBuilder calendarBuilder = CALENDAR_BUILDERS.get("gregory");
        if ("Long".equals(formatURI.authority()))
        {
          return new CalendarAsLongConversionDelegate(calendarBuilder);
        }
        else
        {
          DateFormat dateFormat = getDateFormat(formatURI);
          return new CalendarConversionDelegate(dateFormat, calendarBuilder);
        }
      }
      else if (instanceClass != null && XMLGregorianCalendar.class.isAssignableFrom(instanceClass) && XMLGregorianCalendarConversionDelegate.DATATYPE_FACTORY != null)
      {
        if ("Long".equals(formatURI.authority()))
        {
          return XMLGregorianCalendarAsLongConversionDelegate.INSTANCE;
        }
        else
        {
          DateFormat dateFormat = getDateFormat(formatURI);
          return new XMLGregorianCalendarConversionDelegate(dateFormat);
        }
      }
      else
      {
        AnnotationValidator.INSTANCE.throwIllegalArgumentException(
          AnnotationValidator.INVALID_INSTANCE_TYPE,
          eDataType.getInstanceTypeName(),
          Arrays.asList(new Object []{ long.class, Long.class, Date.class, java.sql.Date.class, Calendar.class, XMLGregorianCalendar.class }));
      }
    }

    return null;
  }

  /**
   * Returns the date format specified by the format URI.
   * @param formatURI the format URI as described in {@link DateConversionDelegate}.
   * @return the date format specified by the format URI.
   * @throws IllegalArgumentException if the format is ill-formed.
   */
  public static DateFormat getDateFormat(URI formatURI) throws IllegalArgumentException
  {
    String style = formatURI.authority();
    if ("SimpleDateFormat".equals(style))
    {
      int segmentCount = formatURI.segmentCount();
      String formatPattern = segmentCount == 0 ? "" : URI.decode(formatURI.segment(0));
      if (formatPattern.trim().length() == 0)
      {
        AnnotationValidator.INSTANCE.throwIllegalArgumentException(AnnotationValidator.INVALID_MISSING_SIMPLE_DATE_FORMAT);
      }

      Locale locale = getLocale(1, "SimpleDateFormat", formatURI);
      try
      {
        return normalizeTimeZone(new SimpleDateFormat(formatPattern, locale));
      }
      catch (IllegalArgumentException exception)
      {
        AnnotationValidator.INSTANCE.throwIllegalArgumentException(AnnotationValidator.INVALID_SIMPLE_DATE_FORMAT, formatPattern, exception);

        // Unreachable
        return null;
      }
    }
    else if ("DateFormat".equals(style))
    {
      int segmentCount = formatURI.segmentCount();
      if (segmentCount < 1)
      {
        AnnotationValidator.INSTANCE.throwIllegalArgumentException(AnnotationValidator.INVALID_MISSING_DATE_FORMAT);
      }

      String dateStyleValue = formatURI.segment(0);
      int dateStyle = STYLES.indexOf(dateStyleValue);
      if (dateStyle == -1)
      {
        AnnotationValidator.INSTANCE.throwIllegalArgumentException(AnnotationValidator.INVALID_DATE_STYLE, "DateFormat", dateStyleValue);
      }

      Locale locale = getLocale(1, "DateFormat", formatURI);
      return normalizeTimeZone(DateFormat.getDateInstance(dateStyle, locale));
    }
    else if ("TimeFormat".equals(style))
    {
      int segmentCount = formatURI.segmentCount();
      if (segmentCount < 1)
      {
        AnnotationValidator.INSTANCE.throwIllegalArgumentException(AnnotationValidator.INVALID_MISSING_TIME_FORMAT);
      }

      String timeStyleValue = formatURI.segment(0);
      int timeStyle = STYLES.indexOf(timeStyleValue);
      if (timeStyle == -1)
      {
        AnnotationValidator.INSTANCE.throwIllegalArgumentException(AnnotationValidator.INVALID_TIME_STYLE, "TimeFormat", timeStyleValue);
      }

      Locale locale = getLocale(1, "TimeFormat", formatURI);
      return normalizeTimeZone(DateFormat.getTimeInstance(timeStyle, locale));
    }
    else if ("DateTimeFormat".equals(style))
    {
      int segmentCount = formatURI.segmentCount();

      if (segmentCount < 2)
      {
        AnnotationValidator.INSTANCE.throwIllegalArgumentException(AnnotationValidator.INVALID_MISSING_DATE_TIME_FORMAT);
      }

      String dateStyleValue = formatURI.segment(0);
      int dateStyle = STYLES.indexOf(dateStyleValue);
      if (dateStyle == -1)
      {
        AnnotationValidator.INSTANCE.throwIllegalArgumentException(AnnotationValidator.INVALID_DATE_STYLE, "DateTimeFormat", dateStyleValue);
      }

      String timeStyleValue = formatURI.segment(1);
      int timeStyle = STYLES.indexOf(timeStyleValue);
      if (timeStyle == -1)
      {
        AnnotationValidator.INSTANCE.throwIllegalArgumentException(AnnotationValidator.INVALID_TIME_STYLE, "DateTimeFormat", timeStyleValue);
      }

      Locale locale = getLocale(2, "DateTimeFormat", formatURI);
      return normalizeTimeZone(DateFormat.getDateTimeInstance(dateStyle, timeStyle, locale));
    }
    else
    {
      AnnotationValidator.INSTANCE.throwIllegalArgumentException(
        AnnotationValidator.INVALID_STYLE,
        style,
        Arrays.asList(new String []{ "Long", "SimpleDataFormat", "DateFormat", "DateTimeFormat", "TimeFormat" }));

      // Unreachable.
      return null;
    }
  }

  private static DateFormat normalizeTimeZone(DateFormat dateFormat)
  {
    dateFormat.setTimeZone(GMT);
    return dateFormat;
  }

  private static Locale getLocale(int start, String style, URI formatURI) throws IllegalArgumentException
  {
    int segmentCount = formatURI.segmentCount();
    int size = segmentCount - start;
    if (size < 2 && !"SimpleDateFormat".equals(style))
    {
      AnnotationValidator.INSTANCE.throwIllegalArgumentException(AnnotationValidator.INVALID_MISSING_LOCALE, style);
    }

    String language;
    if (start < segmentCount)
    {
      language = formatURI.segment(start);
      Map<String, Set<String>> countries = LOCALES.get(language);
      if (countries == null)
      {
        AnnotationValidator.INSTANCE.throwIllegalArgumentException(AnnotationValidator.INVALID_LANGUAGE, language, LOCALES.keySet());
      }
    }
    else
    {
      language = "";
    }

    String country;

    if (start + 1 < segmentCount)
    {
      country = formatURI.segment(start + 1);
      Map<String, Set<String>> countries = LOCALES.get(language);
      if (!countries.containsKey(country))
      {
        Set<String> validCountries = new TreeSet<String>(countries.keySet());
        validCountries.remove("");
        AnnotationValidator.INSTANCE.throwIllegalArgumentException(AnnotationValidator.INVALID_COUNTRY, country, language, validCountries);
      }
    }
    else
    {
      country = "";
    }

    String variant;
    if (start + 2 < segmentCount)
    {
      variant = formatURI.segment(start + 2);

      Map<String, Set<String>> countries = LOCALES.get(language);
      Set<String> variants = countries.get(country);
      if (!variants.contains(variant) || "".equals(variant))
      {
        Set<String> validVariants = new TreeSet<String>(variants);
        validVariants.remove("");
        AnnotationValidator.INSTANCE.throwIllegalArgumentException(AnnotationValidator.INVALID_VARIANT, variant, language, country, validVariants);
      }
    }
    else
    {
      variant = "";
    }

    @SuppressWarnings("deprecation")
    Locale locale = new Locale(language, country, variant);
    return locale;
  }

  /**
   * An annotation validator for validating {@link DateConversionDelegateFactory#ANNOTATION_URI http:///org/eclipse/emf/ecore/util/DateConversionDelegate} annotations.
   */
  public static class AnnotationValidator extends BasicEAnnotationValidator
  {
    /**
     * The singleton instance of the annotation validator.
     */
    public static final AnnotationValidator INSTANCE = new AnnotationValidator();

    /**
     * The source used for creating diagnostics.
     */
    public static final String DIAGNOSTIC_SOURCE = "org.eclipse.emf.ecore.annotation.date.time.conversion.delegate";

    /**
     * An uncategorized problem.
     */
    public static final int INVALID = 1;

    /**
     * The specified calendar type is not an available calendar type.
     */
    public static final int INVALID_CALENDAR_TYPE = 2;

    /**
     * The simple date format pattern is absent.
     */
    public static final int INVALID_MISSING_SIMPLE_DATE_FORMAT = 3;

    /**
     * The DateFormat is incompletely specified.
     */
    public static final int INVALID_MISSING_DATE_FORMAT = 4;

    /**
     * The date-style of a DateFormat for DateTime format is invalid.
     */
    public static final int INVALID_DATE_STYLE = 5;

    /**
     * The TimeFormat is incompletely specified.
     */
    public static final int INVALID_MISSING_TIME_FORMAT = 6;

    /**
     * The time-style of a TimeFormat for DateTimeFormat is invalid.
     */
    public static final int INVALID_TIME_STYLE = 7;

    /**
     * The DateTimeFormat is incompletely specified.
     */
    public static final int INVALID_MISSING_DATE_TIME_FORMAT = 8;

    /**
     * The format style is not recognized.
     */
    public static final int INVALID_STYLE = 9;

    /**
     * The require locale specification is absent.
     */
    public static final int INVALID_MISSING_LOCALE = 10;

    /**
     * The language isn't an available locale.
     */
    public static final int INVALID_LANGUAGE = 11;

    /**
     * The country for the language isn't an available locale.
     */
    public static final int INVALID_COUNTRY = 12;

    /**
     * The variant for the language and country isn't an available locale.
     */
    public static final int INVALID_VARIANT = 13;

    /**
     * The simple date format isn't valid.
     */
    public static final int INVALID_SIMPLE_DATE_FORMAT = 14;

    /**
     * The isntance type of the data type isn't supported.
     */
    public static final int INVALID_INSTANCE_TYPE = 15;

    static
    {
      // Check that the instance is registered properly...
      if (!EAnnotationValidator.Registry.INSTANCE.containsKey(ANNOTATION_URI))
      {
        // In a stand alone application, we'll need to explicitly register it.
        EAnnotationValidator.Registry.INSTANCE.put(ANNOTATION_URI, INSTANCE);
      }

      // Force eager initialization; in a stand alone application, the dynamic model won't be registered until this class is initialized.
      PropertySwitch.VALID_KEYS.isEmpty();
    }

    public AnnotationValidator()
    {
      super(ANNOTATION_URI, "DateConversionDelegate", DIAGNOSTIC_SOURCE);
    }

    private static class IllegalDateConversionArgumentException extends IllegalArgumentException
    {
      private static final long serialVersionUID = 1L;

      private final int code;

      public IllegalDateConversionArgumentException(String message, int code)
      {
        super(message);
        this.code = code;
      }

      public IllegalDateConversionArgumentException(String message, Throwable cause, int code)
      {
        super(message, cause);
        this.code = code;
      }
    }

    private String getAvailableChoices(Collection<?> choices, boolean inclusive, String quote, int limit)
    {
      return EObjectValidator.INSTANCE.getAvailableChoices(choices, inclusive, quote, limit);
    }

    public void throwIllegalArgumentException(int code, Object... substitutions) throws IllegalArgumentException
    {
      switch (code)
      {
        case INVALID_CALENDAR_TYPE:
          throw new IllegalDateConversionArgumentException(
            getString(
              getResourceLocator(),
              "_UI_DateFormatInvalidCalendarType_diagnostic",
              substitutions[0],
              getAvailableChoices((Collection<?>)substitutions[1], false, "", Integer.MAX_VALUE)),
            code);
        case INVALID_MISSING_SIMPLE_DATE_FORMAT:
          throw new IllegalDateConversionArgumentException(getString(getResourceLocator(), "_UI_DateFormatInvalidMissingSimpleDateFormat_diagnostic"), code);
        case INVALID_MISSING_DATE_FORMAT:
          throw new IllegalDateConversionArgumentException(getString(getResourceLocator(), "_UI_DateFormatInvalidMissingDateFormat_diagnostic"), code);
        case INVALID_DATE_STYLE:
          throw new IllegalDateConversionArgumentException(
            getString(
              getResourceLocator(),
              "_UI_DateFormatInvalidDateStyle_diagnostic",
              substitutions[0],
              substitutions[1],
              getAvailableChoices(STYLES, false, "", Integer.MAX_VALUE)),
            code);
        case INVALID_MISSING_TIME_FORMAT:
          throw new IllegalDateConversionArgumentException(getString(getResourceLocator(), "_UI_DateFormatInvalidMissingTimeFormat_diagnostic"), code);
        case INVALID_TIME_STYLE:
          throw new IllegalDateConversionArgumentException(
            getString(
              getResourceLocator(),
              "_UI_DateFormatInvalidTimeStyle_diagnostic",
              substitutions[0],
              substitutions[1],
              getAvailableChoices(STYLES, false, "", Integer.MAX_VALUE)),
            code);
        case INVALID_MISSING_DATE_TIME_FORMAT:
          throw new IllegalDateConversionArgumentException(getString(getResourceLocator(), "_UI_DateFormatInvalidMissingDateTimeFormat_diagnostic"), code);
        case INVALID_STYLE:
          throw new IllegalDateConversionArgumentException(
            getString(
              getResourceLocator(),
              "_UI_DateFormatInvalidStyle_diagnostic",
              substitutions[0],
              getAvailableChoices((Collection<?>)substitutions[1], false, "", Integer.MAX_VALUE)),
            code);
        case INVALID_MISSING_LOCALE:
          throw new IllegalDateConversionArgumentException(getString(getResourceLocator(), "_UI_DateFormatInvalidMissingLocale_diagnostic", substitutions[0]), code);
        case INVALID_LANGUAGE:
          throw new IllegalDateConversionArgumentException(
            getString(getResourceLocator(), "_UI_DateFormatInvalidLanguage_diagnostic", substitutions[0], getAvailableChoices((Collection<?>)substitutions[1], false, "", 10)),
            code);
        case INVALID_COUNTRY:
          throw new IllegalDateConversionArgumentException(
            getString(
              getResourceLocator(),
              "_UI_DateFormatInvalidCountry_diagnostic",
              substitutions[0],
              substitutions[1],
              getAvailableChoices((Collection<?>)substitutions[2], false, "", 10)),
            code);
        case INVALID_VARIANT:
          throw new IllegalDateConversionArgumentException(
            getString(
              getResourceLocator(),
              "_UI_DateFormatInvalidVariant_diagnostic",
              substitutions[0],
              substitutions[1],
              substitutions[2],
              getAvailableChoices((Collection<?>)substitutions[3], false, "", 10)),
            code);
        case INVALID_SIMPLE_DATE_FORMAT:
          throw new IllegalDateConversionArgumentException(
            getString(getResourceLocator(), "_UI_DateFormatInvalidSimpleDateFormat_diagnostic", substitutions[0], ((Throwable)substitutions[1]).getLocalizedMessage()),
            ((Throwable)substitutions[1]),
            code);
        case INVALID_INSTANCE_TYPE:
          throw new IllegalDateConversionArgumentException(
            getString(getResourceLocator(), "_UI_DateFormatInvalidInstanceType_diagnostic", substitutions[0], getAvailableChoices((Collection<?>)substitutions[1], false, "'", 10)),
            code);
      }
    }

    @Override
    protected ResourceLocator getResourceLocator()
    {
      return getEcoreResourceLocator();
    }

    @Override
    protected boolean isValidLocation(EAnnotation eAnnotation, EModelElement eModelElement)
    {
      return eModelElement instanceof EDataType;
    }

    @Override
    protected List<EClass> getPropertyClasses(EModelElement eModelElement)
    {
      final List<EClass> result = new ArrayList<EClass>();
      new PropertySwitch()
        {

          @Override
          protected void addFeatures(EClass eClass)
          {
            result.add(eClass);
          }
        }.doSwitch(eModelElement);
      return result.isEmpty() ? Collections.<EClass> emptyList() : Collections.singletonList(result.get(0));
    }

    private static abstract class PropertySwitch extends EcoreSwitch<Void>
    {
      private static final String ANNOTATION_NS_URI = "http:///org/eclipse/emf/ecore/util/DateConversionDelegateAnnotation";

      private static final Map<EAttribute, Set<String>> VALID_KEYS = new HashMap<EAttribute, Set<String>>();

      private static final EClass ANNOTATION_DATA_TYPE_CLASS;

      static
      {
        EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(ANNOTATION_NS_URI);
        if (ePackage == null)
        {
          // If the package isn't registered, as will be the case in a stand alone application, try to load it dynamically.
          // This will ensure that the package is registered as well.
          ePackage = loadEPackage(EcorePlugin.INSTANCE.getBaseURL().toString() + "model/DateConversionDelegateFactory.ecore");
        }

        if (ePackage == null)
        {
          ANNOTATION_DATA_TYPE_CLASS = null;
        }
        else
        {
          ANNOTATION_DATA_TYPE_CLASS = (EClass)ePackage.getEClassifier("DataType");
          VALID_KEYS.put((EAttribute)ANNOTATION_DATA_TYPE_CLASS.getEStructuralFeature("format"), EStructuralFeature.Internal.SettingDelegate.Factory.Registry.INSTANCE.keySet());

          final EDataType conversionFormatType = (EDataType)ePackage.getEClassifier("WellFormedConversionFormat");

          EValidator.Registry.INSTANCE.put(ePackage, new EObjectValidator()
            {
              @Override
              public boolean validate(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map<Object, Object> context)
              {
                boolean result = super.validate(eDataType, value, diagnostics, context);
                if (result)
                {
                  if (eDataType == conversionFormatType)
                  {
                    if (value != null)
                    {
                      ValidationContext validationContext = (ValidationContext)context.get(ValidationContext.CONTEXT_KEY);
                      if (validationContext != null)
                      {
                        result = false;
                        String reason = null;
                        int code = 0;
                        try
                        {
                          createConversionDelegate((EDataType)validationContext.getEModelElement(), value.toString());
                          result = true;
                        }
                        catch (IllegalDateConversionArgumentException exception)
                        {
                          reason = exception.getLocalizedMessage();
                          code = exception.code;
                        }
                        catch (Exception exception)
                        {
                          reason = exception.getLocalizedMessage();
                          code = INVALID;
                        }

                        if (!result && diagnostics != null)
                        {
                          diagnostics.add(
                            createDiagnostic(
                              Diagnostic.ERROR,
                              DIAGNOSTIC_SOURCE,
                              code,
                              "_UI_DateFormatNotWellFormed_diagnostic",
                              new Object []{ value, reason },
                              new Object []{ value, eDataType },
                              context));
                        }
                      }
                    }
                  }
                }
                return result;
              }
            });
        }
      }

      protected abstract void addFeatures(EClass eClass);

      @Override
      public Void caseEDataType(EDataType eDataTYpe)
      {
        if (ANNOTATION_DATA_TYPE_CLASS != null)
        {
          addFeatures(ANNOTATION_DATA_TYPE_CLASS);
        }
        return null;
      }
    }
  }

  private static class LongAsLongConversionDelegate implements EDataType.Internal.ConversionDelegate
  {
    private static final LongAsLongConversionDelegate INSTANCE = new LongAsLongConversionDelegate();

    private LongAsLongConversionDelegate()
    {
    }

    public String convertToString(Object value)
    {
      if (value == null)
      {
        return null;
      }
      else
      {
        return Long.toString((Long)value);
      }
    }

    public Object createFromString(String literal)
    {
      if (literal == null)
      {
        return null;
      }
      else
      {
        return Long.parseLong(literal);
      }
    }
  }

  private static class LongAsDateConversionDelegate implements EDataType.Internal.ConversionDelegate
  {
    private final DateFormat dateFormat;

    public LongAsDateConversionDelegate(DateFormat dateFormat)
    {
      this.dateFormat = dateFormat;
    }

    public synchronized String convertToString(Object value)
    {
      if (value == null)
      {
        return null;
      }
      else
      {
        dateFormat.setTimeZone(GMT);
        return dateFormat.format(new Date((Long)value));
      }
    }

    public synchronized Object createFromString(String literal)
    {
      if (literal == null)
      {
        return null;
      }
      else
      {
        try
        {
          dateFormat.setTimeZone(GMT);
          return dateFormat.parse(literal).getTime();
        }
        catch (ParseException exception)
        {
          throw new IllegalArgumentException(exception);
        }
      }
    }
  }

  private static class DateAsLongConversionDelegate implements EDataType.Internal.ConversionDelegate
  {
    private static final DateAsLongConversionDelegate INSTANCE = new DateAsLongConversionDelegate();

    private DateAsLongConversionDelegate()
    {
    }

    public String convertToString(Object value)
    {
      if (value == null)
      {
        return null;
      }
      else
      {
        return Long.toString(((Date)value).getTime());
      }
    }

    public Object createFromString(String literal)
    {
      if (literal == null)
      {
        return null;
      }
      else
      {
        return new Date(Long.parseLong(literal));
      }
    }
  }

  private static class DateConversionDelegate implements EDataType.Internal.ConversionDelegate
  {
    private final DateFormat dateFormat;

    public DateConversionDelegate(DateFormat dateFormat)
    {
      this.dateFormat = dateFormat;
    }

    public synchronized String convertToString(Object value)
    {
      if (value == null)
      {
        return null;
      }
      else
      {
        dateFormat.setTimeZone(GMT);
        return dateFormat.format(value);
      }
    }

    public synchronized Object createFromString(String literal)
    {
      if (literal == null)
      {
        return null;
      }
      else
      {
        try
        {
          dateFormat.setTimeZone(GMT);
          return dateFormat.parse(literal);
        }
        catch (ParseException exception)
        {
          throw new IllegalArgumentException(exception);
        }
      }
    }
  }

  private static class SQLDateAsLongConversionDelegate implements EDataType.Internal.ConversionDelegate
  {
    private static final SQLDateAsLongConversionDelegate INSTANCE = new SQLDateAsLongConversionDelegate();

    private SQLDateAsLongConversionDelegate()
    {
    }

    public String convertToString(Object value)
    {
      if (value == null)
      {
        return null;
      }
      else
      {
        return Long.toString(((Date)value).getTime());
      }
    }

    public Object createFromString(String literal)
    {
      if (literal == null)
      {
        return null;
      }
      else
      {
        return new java.sql.Date(Long.parseLong(literal));
      }
    }
  }

  private static class SQLDateConversionDelegate implements EDataType.Internal.ConversionDelegate
  {
    private final DateFormat dateFormat;

    public SQLDateConversionDelegate(DateFormat dateFormat)
    {
      this.dateFormat = dateFormat;
    }

    public synchronized String convertToString(Object value)
    {
      if (value == null)
      {
        return null;
      }
      else
      {
        dateFormat.setTimeZone(GMT);
        return dateFormat.format(value);
      }
    }

    public synchronized Object createFromString(String literal)
    {
      if (literal == null)
      {
        return null;
      }
      else
      {
        try
        {
          dateFormat.setTimeZone(GMT);
          Date date = dateFormat.parse(literal);
          return new java.sql.Date(date.getTime());
        }
        catch (ParseException exception)
        {
          throw new IllegalArgumentException(exception);
        }
      }
    }
  }

  private static class CalendarAsLongConversionDelegate implements EDataType.Internal.ConversionDelegate
  {
    private final CalendarBuilder calendarBuilder;

    public CalendarAsLongConversionDelegate(CalendarBuilder calendarBuilder)
    {
      this.calendarBuilder = calendarBuilder;
    }

    public String convertToString(Object value)
    {
      if (value == null)
      {
        return null;
      }
      else
      {
        return Long.toString(((Calendar)value).getTime().getTime());
      }
    }

    public Object createFromString(String literal)
    {
      if (literal == null)
      {
        return null;
      }
      else
      {
        long time = Long.parseLong(literal);
        return calendarBuilder.create(time);
      }
    }
  }

  private static class CalendarConversionDelegate implements EDataType.Internal.ConversionDelegate
  {
    private final DateFormat dateFormat;

    private final CalendarBuilder calendarBuilder;

    public CalendarConversionDelegate(DateFormat dateFormat, CalendarBuilder calendarBuilder)
    {
      this.dateFormat = dateFormat;
      this.calendarBuilder = calendarBuilder;
    }

    public synchronized String convertToString(Object value)
    {
      if (value == null)
      {
        return null;
      }
      else
      {
        dateFormat.setTimeZone(GMT);
        return dateFormat.format(((Calendar)value).getTime());
      }
    }

    public synchronized Object createFromString(String literal)
    {
      if (literal == null)
      {
        return null;
      }
      else
      {
        try
        {
          dateFormat.setTimeZone(GMT);
          Date date = dateFormat.parse(literal);
          return calendarBuilder.create(date.getTime());
        }
        catch (ParseException exception)
        {
          throw new IllegalArgumentException(exception);
        }
      }
    }
  }

  private static class XMLGregorianCalendarAsLongConversionDelegate implements EDataType.Internal.ConversionDelegate
  {
    private static final XMLGregorianCalendarAsLongConversionDelegate INSTANCE = new XMLGregorianCalendarAsLongConversionDelegate();

    private static final DatatypeFactory DATATYPE_FACTORY;

    static
    {
      DatatypeFactory datatypeFactory = null;
      try
      {
        datatypeFactory = DatatypeFactory.newInstance();
      }
      catch (DatatypeConfigurationException e)
      {
      }
      DATATYPE_FACTORY = datatypeFactory;
    }

    public XMLGregorianCalendarAsLongConversionDelegate()
    {
    }

    public String convertToString(Object value)
    {
      if (value == null)
      {
        return null;
      }
      else
      {
        return Long.toString(((XMLGregorianCalendar)value).toGregorianCalendar().getTime().getTime());
      }
    }

    public Object createFromString(String literal)
    {
      if (literal == null)
      {
        return null;
      }
      else
      {
        long time = Long.parseLong(literal);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(new Date(time));
        return DATATYPE_FACTORY.newXMLGregorianCalendar(gregorianCalendar);
      }
    }
  }

  private static class XMLGregorianCalendarConversionDelegate implements EDataType.Internal.ConversionDelegate
  {
    private static final DatatypeFactory DATATYPE_FACTORY;

    static
    {
      DatatypeFactory datatypeFactory = null;
      try
      {
        datatypeFactory = DatatypeFactory.newInstance();
      }
      catch (DatatypeConfigurationException exception)
      {
        // Unavailable.
      }
      DATATYPE_FACTORY = datatypeFactory;
    }

    private final DateFormat dateFormat;

    public XMLGregorianCalendarConversionDelegate(DateFormat dateFormat)
    {
      this.dateFormat = dateFormat;
    }

    public synchronized String convertToString(Object value)
    {
      if (value == null)
      {
        return null;
      }
      else
      {
        dateFormat.setTimeZone(GMT);
        return dateFormat.format(((XMLGregorianCalendar)value).toGregorianCalendar().getTime());
      }
    }

    public synchronized Object createFromString(String literal)
    {
      if (literal == null)
      {
        return null;
      }
      else
      {
        try
        {
          dateFormat.setTimeZone(GMT);
          Date date = dateFormat.parse(literal);
          GregorianCalendar gregorianCalendar = new GregorianCalendar();
          gregorianCalendar.setTime(date);
          return DATATYPE_FACTORY.newXMLGregorianCalendar(gregorianCalendar);
        }
        catch (ParseException exception)
        {
          throw new IllegalArgumentException(exception);
        }
      }
    }
  }
}
