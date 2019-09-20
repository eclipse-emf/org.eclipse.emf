/**
 * Copyright (c) 2018 Eclipse contributorsCorporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.edit.ui.provider;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.DateConversionDelegateFactory;
import org.eclipse.emf.ecore.util.DateConversionDelegateFactory.CalendarBuilder;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;


/**
 * A property editor factory that creates a cell editor that uses <a href="https://www.eclipse.org/nebula/widgets/cdatetime/cdatetime.php">Nebula's cool date/time widget<a/>.
 * <p>
 * To avoid a compile-time dependency on Nebula, this class is implemented using reflection.
 * The {@code org.eclipse.emf.edit.ui bundle} has an optional greedy requirement for {@code org.eclipse.nebula.widgets.cdatetime}.
 * When Nebula's {@code org.eclipse.nebula.widgets.cdatetime} bundle is not available, 
 * this factory will <b>not</b> create specialized cell editors.
 * However, it will continue to provide {@link #createLabelProvider(Object, IItemPropertyDescriptor) specialized} label providers.
 * </p>
 * <p>
 * Only single-valued features are currently supported for {@link #createEditor(Object, IItemPropertyDescriptor, Object) cell editing},
 * though {@link #createLabelProvider(Object, IItemPropertyDescriptor) specialized} label providers work also for multi-valued features.
 * </p>
 * <p>
 * Only data types that use {@link Long#TYPE long}, {@link Long}, {@link Date}, {@link java.sql.Date}, {@link Calendar}, {@link GregorianCalendar}, or {@link XMLGregorianCalendar} are supported.
 * </p>
 * <p>
 * URIs of the following form are supported:
 * </p>
 * <pre>
 *  {@code editor://org.eclipse.nebula.widgets.cdatetime/<simple-date-format-pattern>}
 *  {@code editor://org.eclipse.nebula.widgets.cdatetime/<simple-date-format-pattern>/CLOCK_DISCRETE}
 *  {@code editor://org.eclipse.nebula.widgets.cdatetime/<simple-date-format-pattern>?japanese}
 * </pre>
 * <p>
 * I.e., you must specify a valid {@link SimpleDateFormat},
 * you may optionally specify bit-wise |'d CDT styles,
 * and you may optionally, in the case of {@link Calendar} type, specify the {@link Calendar#getCalendarType() type of calendar} to build, with {@code gregory} as the default.
 * </p>
 * 
 * @see DateConversionDelegateFactory
 */
public class NebulaDatePropertyEditorFactory extends EMFEditUIPropertyEditorFactory
{
  /**
   * The property editor factory URI of this property editor factory.
   */
  public static final URI PROPERTY_EDITOR_FACTORY_URI = URI.createURI("editor://org.eclipse.nebula.widgets.cdatetime/");

  /**
   * The global singleton instance.
   */
  public static final NebulaDatePropertyEditorFactory INSTANCE = new NebulaDatePropertyEditorFactory();

  /**
   * An invalid missing format.
   * 
   * @see #validate(EModelElement, URI, DiagnosticChain, Map)
   */
  public static final int INVALID_MISSING_FORMAT = 1;

  /**
   * A format that causes {@link SimpleDateFormat#SimpleDateFormat(String)} to throw exceptions.
   * 
   * @see #validate(EModelElement, URI, DiagnosticChain, Map)
   */
  public static final int INVALID_FORMAT = 2;

  /**
   * A style elements that is nor recognized.
   * 
   * @see #validate(EModelElement, URI, DiagnosticChain, Map)
   */
  public static final int INVALID_STYLE_ELEMENT = 3;

  /**
   * An instance type that is not {@link #SUPPORTED_INSTANCE_CLASSES supported}.
   * 
   * @see #validate(EModelElement, URI, DiagnosticChain, Map)
   */
  public static final int INVALID_INSTANCE_TYPE = 4;

  /**
   * A {@link Calendar#getCalendarType() calendar type} that is not {@link Calendar#getAvailableCalendarTypes() available}.
   */
  public static final int INVALID_CALENDAR_TYPE = 4;

  /**
   * The set of supported instance types, i.e.,
   * {@link Long#TYPE long}, {@link Long}, {@link Date}, {@link java.sql.Date}, {@link Calendar}, {@link GregorianCalendar}, or {@link XMLGregorianCalendar}.
   */
  public static Set<Class<?>> SUPPORTED_INSTANCE_CLASSES = Collections.unmodifiableSet(
    new LinkedHashSet<Class<?>>(
      Arrays.asList(new Class<?> []{ long.class, Long.class, Date.class, java.sql.Date.class, Calendar.class, GregorianCalendar.class, XMLGregorianCalendar.class })));

  /**
   * Creates an instance.
   */
  public NebulaDatePropertyEditorFactory()
  {
    super(PROPERTY_EDITOR_FACTORY_URI);
  }

  /**
   * {@inheritDoc}
   * <p>
   * This implementation creates a cell editor that uses a Nebula date/time widget.
   * </p>
   */
  @Override
  public CellEditor createEditor(Object object, IItemPropertyDescriptor propertyDescriptor, Composite composite)
  {
    URI propertyEditorURI = getPropertyEditorURI(object, propertyDescriptor);
    if (propertyEditorURI != null)
    {
      // There must be a least a first segment specifying the SimpleDateTime format pattern.
      //
      int segmentCount = propertyEditorURI.segmentCount();
      if (segmentCount >= 1)
      {
        // This is not implemented to support many-valued features.
        //
        EStructuralFeature eStructuralFeature = getEStructuralFeature(object, propertyDescriptor);
        if (eStructuralFeature != null && !eStructuralFeature.isMany())
        {
          String dateFormatPattern = URI.decode(propertyEditorURI.segment(0));

          // Compute the style from the style bits.
          //
          int style = 0;
          if (segmentCount > 1)
          {
            String styleSegment = propertyEditorURI.segment(1);
            style |= getStyle(styleSegment, DateCellEditor.CDT_STYLES);
          }

          String calendarType = null;
          Class<?> instanceClass = eStructuralFeature.getEType().getInstanceClass();
          if (instanceClass == Calendar.class)
          {
            // Determine the specified calendar type, defaulting to "gregory" if here isn't one.
            //
            calendarType = propertyEditorURI.query();
            if (calendarType == null)
            {
              String format = EcoreUtil.getAnnotation(eStructuralFeature.getEType(), DateConversionDelegateFactory.ANNOTATION_URI, "format");
              if (format != null)
              {
                URI formatURI = URI.createURI(format);
                calendarType = formatURI.query();
              }
            }
          }

          ValueConverter valueConverter = getValueConverter(instanceClass, calendarType);
          return DateCellEditor.create(composite, style, dateFormatPattern, valueConverter);
        }
      }
    }
    return null;
  }

  /**
   * Returns the value converter associated with a {@link #SUPPORTED_INSTANCE_CLASSES supported instance class}.
   * @param instanceClass an instance class.
   * @param calendarType a {@link Calendar#getCalendarType() calendar type} or {@code null}; this is only meaningful for a {@link Calendar}; it defaults to {@code gregory} when not specified.
   * @return the value converter associated with a supported instance class, or {@code null} for an unsupported class.
   */
  public static ValueConverter getValueConverter(Class<?> instanceClass, String calendarType)
  {
    if (instanceClass == long.class || instanceClass == Long.class)
    {
      return ValueConverter.LongDateConverter.INSTANCE;
    }
    else if (instanceClass == Date.class)
    {
      return ValueConverter.DateConverter.INSTANCE;
    }
    else if (instanceClass == java.sql.Date.class)
    {
      return ValueConverter.SQLDateConverter.INSTANCE;
    }
    else if (instanceClass == XMLGregorianCalendar.class)
    {
      return ValueConverter.XMLGregorianCalendarDateConverter.INSTANCE;
    }
    else if (instanceClass == GregorianCalendar.class)
    {
      DateConversionDelegateFactory.CalendarBuilder calendarBuilder = DateConversionDelegateFactory.getCalendarBuilder("gregory");
      return new ValueConverter.CalendarDateConverter(calendarBuilder);
    }
    else if (instanceClass == Calendar.class)
    {
      DateConversionDelegateFactory.CalendarBuilder calendarBuilder = DateConversionDelegateFactory.getCalendarBuilder(calendarType == null ? "gregory" : calendarType);
      return new ValueConverter.CalendarDateConverter(calendarBuilder);
    }
    else
    {
      return null;
    }
  }

  /**
   * {@inheritDoc}
   * <p>
   * This implementation produces labels using exactly the simple date format used by the cell editor.
   * </p>
   */
  @Override
  public IItemLabelProvider createLabelProvider(Object object, IItemPropertyDescriptor propertyDescriptor)
  {
    URI propertyEditorURI = getPropertyEditorURI(object, propertyDescriptor);
    if (propertyEditorURI != null)
    {
      int segmentCount = propertyEditorURI.segmentCount();
      if (segmentCount >= 1)
      {
        EStructuralFeature eStructuralFeature = getEStructuralFeature(object, propertyDescriptor);
        if (eStructuralFeature != null && !eStructuralFeature.isMany())
        {
          final String dateFormatPattern = URI.decode(propertyEditorURI.segment(0));
          return new IItemLabelProvider()
            {
              public String getText(Object object)
              {
                if (object instanceof Date)
                {
                  return new SimpleDateFormat(dateFormatPattern).format(object);
                }
                else if (object instanceof Calendar)
                {
                return new SimpleDateFormat(dateFormatPattern).format(((Calendar)object).getTime());
                }
                else if (object instanceof XMLGregorianCalendar)
                {
                  return new SimpleDateFormat(dateFormatPattern).format(((XMLGregorianCalendar)object).toGregorianCalendar().getTime());
                }
                else if (object instanceof Long)
                {
                  return new SimpleDateFormat(dateFormatPattern).format(new Date((Long)object));
                }
                else
                {
                  return "";
                }
              }
  
              public Object getImage(Object object)
              {
                return ItemPropertyDescriptor.GENERIC_VALUE_IMAGE;
              }
            };
        }
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   * <p>
   * This implementation validates that the property editor specification is well formed,
   * i.e., that there is a valid date format pattern,
   * that the style constants are recognized,
   * that the instance class is supported,
   * and that the specified calendar type is available.
   * </p>
   */
  @Override
  public boolean validate(EModelElement eModelElement, URI propertyEditorSpecification, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    int segmentCount = propertyEditorSpecification.segmentCount();
    if (segmentCount == 0)
    {
      if (diagnostics != null)
      {
        diagnostics.add(
          createDiagnostic(Diagnostic.ERROR, INVALID_MISSING_FORMAT, getString("_UI_NebulaInvalidMissingFormat_diagnostic"), eModelElement, propertyEditorSpecification));
      }
      return false;
    }

    String dateFormatPattern = URI.decode(propertyEditorSpecification.segment(0));
    try
    {
      new SimpleDateFormat(dateFormatPattern);
    }
    catch (IllegalArgumentException exception)
    {
      if (diagnostics != null)
      {
        diagnostics.add(
          createDiagnostic(
            Diagnostic.ERROR,
            INVALID_FORMAT,
            getString("_UI_NebulaInvalidFormat_diagnostic", dateFormatPattern, exception.getLocalizedMessage()),
            eModelElement,
            propertyEditorSpecification));
      }
      return false;
    }

    EModelElement effectiveEModelElement = eModelElement instanceof EStructuralFeature ? ((EStructuralFeature)eModelElement).getEType() : eModelElement;
    if (effectiveEModelElement instanceof EDataType)
    {
      EDataType eDataType = (EDataType)effectiveEModelElement;
      Class<?> instanceClass = eDataType.getInstanceClass();
      if (!SUPPORTED_INSTANCE_CLASSES.contains(instanceClass))
      {
        if (diagnostics != null)
        {
          diagnostics.add(
            createDiagnostic(
              Diagnostic.ERROR,
              INVALID_INSTANCE_TYPE,
              getString(
                "_UI_NebulaInvalidInstanceType_diagnostic",
                eDataType.getInstanceTypeName(),
                EObjectValidator.INSTANCE.getAvailableChoices(SUPPORTED_INSTANCE_CLASSES, false, "", 10)),
              eModelElement,
              propertyEditorSpecification));
        }
        return false;
      }

      if (instanceClass == Calendar.class)
      {
        String query = propertyEditorSpecification.query();
        if (query != null)
        {
          CalendarBuilder calendarBuilder = DateConversionDelegateFactory.getCalendarBuilder(query);
          if (calendarBuilder == null)
          {
            if (diagnostics != null)
            {
              diagnostics.add(
                createDiagnostic(
                  Diagnostic.ERROR,
                  INVALID_CALENDAR_TYPE,
                  getString(
                    "_UI_NebulaInvalidCalendarType_diagnostic",
                    query,
                    EObjectValidator.INSTANCE.getAvailableChoices(DateConversionDelegateFactory.CALENDAR_TYPES, false, "", 10)),
                  eModelElement,
                  propertyEditorSpecification));
            }
            return false;
          }
        }
      }
    }

    if (segmentCount > 1)
    {
      String style = propertyEditorSpecification.segment(1);
      if (style.length() > 0)
      {
        Set<String> styleElements = new LinkedHashSet<String>(getStyleElements(style));
        styleElements.removeAll(DateCellEditor.CDT_STYLES.keySet());
        if (!styleElements.isEmpty())
        {
          if (diagnostics != null)
          {
            diagnostics.add(
              createDiagnostic(
                Diagnostic.WARNING,
                INVALID_STYLE_ELEMENT,
                getString(
                  "_UI_NebulaInvalidStyleElements_diagnostic",
                  EObjectValidator.INSTANCE.getAvailableChoices(styleElements, true, "'", 5),
                  EObjectValidator.INSTANCE.getAvailableChoices(DateCellEditor.CDT_STYLES.keySet(), true, "", 10)),
                eModelElement,
                propertyEditorSpecification));
          }
          return false;
        }
      }
    }

    return true;
  }

  /**
   * {@inheritDoc}
   * <p>
   * This implementation is specialized to produce helpful suggestions that illustrate the URI styles available.
   * </p>
   */
  @Override
  public Set<String> getChoices(EModelElement eModelElement)
  {
    Set<String> result = new LinkedHashSet<String>();
    if (eModelElement instanceof EDataType)
    {
      EDataType eDataType = (EDataType)eModelElement;
      Class<?> instanceClass = eDataType.getInstanceClass();
      if (instanceClass == null)
      {
        result.add("");
      }
      else
      {
        // If the date type specifies format, use that format as the suggestion.
        String format = EcoreUtil.getAnnotation(eModelElement, DateConversionDelegateFactory.ANNOTATION_URI, "format");
        if (format != null)
        {
          URI formatURI = URI.createURI(format);
          try
          {
            DateFormat dateFormat = DateConversionDelegateFactory.getDateFormat(formatURI);
            if (dateFormat instanceof SimpleDateFormat)
            {
              SimpleDateFormat simpleDateFormat = (SimpleDateFormat)dateFormat;
              String pattern = URI.encodeSegment(simpleDateFormat.toPattern(), false).replace("%20", " ");
              result.add(pattern);
            }
          }
          catch (Exception exception)
          {
            // Ignore
          }
        }

        // Otherwise suggest these useful examples.
        if (result.isEmpty())
        {
          result.add("yyyy-MM-dd");
          result.add("yyyy%2FMM%2Fdd");
          result.add("yyyy-MM-dd'T'HH:mm");
          result.add("yyyy-MM-dd'T'HH:mm:ss");
          result.add("yyyy-MM-dd'T'HH:mm:ss'.'SSS");
          result.add("yyyy-MM-dd'T'HH:mm:ss'.'SSSZ");
        }
      }
    }
    else if (eModelElement instanceof EStructuralFeature)
    {
      EStructuralFeature eStructuralFeature = (EStructuralFeature)eModelElement;
      if (!eStructuralFeature.isMany())
      {
        return getChoices(eStructuralFeature.getEType());
      }
    }

    return result;
  }

  @Override
  protected ResourceLocator getResourceLocator()
  {
    return EMFEditUIPlugin.INSTANCE;
  }

  /**
   * A converter for mapping between an instance of a {@link NebulaDatePropertyEditorFactory#SUPPORTED_INSTANCE_CLASSES supported} instance class and a {@link Date}.
   */
  public static abstract class ValueConverter
  {
    /**
     * This class is not intended to be extended outside of this framework.
     */
    private ValueConverter()
    {
    }

    /**
     * Converts an instance value to a date.
     * @param value the instance value.
     * @return the corresponding date value.
     */
    public abstract Date convertToDate(Object value);

    /**
     * Creates an instance value from a date.
     * @param date the date.
     * @return the corresponding instance value.
     */
    public abstract Object createFromDate(Date date);

    /**
     * A no-op conversion between {@link Date} and {@link Date}.
     */
    private static class DateConverter extends ValueConverter
    {
      private static final DateConverter INSTANCE = new DateConverter();

      @Override
      public Date convertToDate(Object value)
      {
        return (Date)value;
      }

      @Override
      public Object createFromDate(Date date)
      {
        return date;
      }
    }

    /**
     * A conversion between {@link java.sql.Date} and {@link Date}.
     */
    private static class SQLDateConverter extends ValueConverter
    {
      private static final SQLDateConverter INSTANCE = new SQLDateConverter();

      @Override
      public Date convertToDate(Object value)
      {
        return (Date)value;
      }

      @Override
      public Object createFromDate(Date date)
      {
        return date == null ? null : new java.sql.Date(date.getTime());
      }
    }

    /**
     * A conversion between {@link Long} and {@link Date}.
     */
    private static class LongDateConverter extends ValueConverter
    {
      private static final LongDateConverter INSTANCE = new LongDateConverter();

      @Override
      public Date convertToDate(Object value)
      {
        return value == null ? null : new Date((Long)value);
      }

      @Override
      public Object createFromDate(Date date)
      {
        return date == null ? null : date.getTime();
      }
    }

    /**
     * A conversion between {@link Calendar} and {@link Date}.
     */
    private static class CalendarDateConverter extends ValueConverter
    {
      private final CalendarBuilder calendarBuilder;

      private CalendarDateConverter(CalendarBuilder calendarBuilder)
      {
        this.calendarBuilder = calendarBuilder;

      }

      @Override
      public Date convertToDate(Object value)
      {
        return value == null ? null : ((Calendar)value).getTime();
      }

      @Override
      public Object createFromDate(Date date)
      {
        return date == null ? null : calendarBuilder.create(date);
      }
    }

    /**
     * A conversion between {@link XMLGregorianCalendar} and {@link Date}.
     */
    private static class XMLGregorianCalendarDateConverter extends ValueConverter
    {
      private static final XMLGregorianCalendarDateConverter INSTANCE = new XMLGregorianCalendarDateConverter();

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

      @Override
      public Date convertToDate(Object value)
      {
        return value == null ? null : ((XMLGregorianCalendar)value).toGregorianCalendar().getTime();
      }

      @Override
      public Object createFromDate(Date date)
      {
        if (date == null)
        {
          return null;
        }
        else
        {
          GregorianCalendar gregorianCalendar = new GregorianCalendar();
          gregorianCalendar.setTime(date);
          return DATATYPE_FACTORY.newXMLGregorianCalendar(gregorianCalendar);
        }
      }
    }
  }

  /**
   * A cell editor that handles {@link Date} using a {@link ValueConverter} to handle all {@link NebulaDatePropertyEditorFactory#SUPPORTED_INSTANCE_CLASSES supported types}.
   * It reflectively uses {@code org.eclipse.nebula.widgets.cdatetime.CDateTime}, when available.
   */
  private static class DateCellEditor extends CellEditor
  {
    private static final Map<String, Integer> CDT_STYLES = new TreeMap<String, Integer>();

    protected static final boolean CDATETIME_IS_AVAILABLE;

    private static final Constructor<?> CDATE_TIME_CONSTRUCTOR;

    private static final Method GET_SELECTION_METHOD;

    private static final Method SET_SELECTION_METHOD;

    private static final Method SET_PATTERN_METHOD;

    private static final Method ADD_SELECTION_LISTENER_METHOD;

    static
    {
      Constructor<?> cDateTimeConstructor = null;
      Method getSelectionMethod = null;
      Method setSelectionMethod = null;
      Method setPatternMethod = null;
      Method addSelectionListenerMethod = null;
      boolean cDateTimeIsAvailable = false;
      try
      {
        Class<?> cdtClass = CommonPlugin.loadClass("org.eclipse.nebula.widgets.cdatetime", "org.eclipse.nebula.widgets.cdatetime.CDT");
        Pattern styleNamePattern = Pattern.compile("[A-Z124_]*");
        for (Field field : cdtClass.getFields())
        {
          String name = field.getName();
          if (styleNamePattern.matcher(name).matches())
          {
            int modifiers = field.getModifiers();
            if (field.getType() == int.class && Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isStatic(modifiers))
            {
              int value = field.getInt(null);
              CDT_STYLES.put(name, value);
              // String binaryString = Integer.toBinaryString(value);
              // binaryString.length();
              // String literal;
              // if (value == 0)
              // {
              //   literal = "0";
              // }
              // else if (value == 1)
              // {
              //   literal = "1";
              // }
              // else
              // {
              //   literal = "1 << " + binaryString.length();
              // }
              // System.out.println("CDT_STYLES.put(\"" + name + "\", " + literal + ");");
            }
          }
        }

        Class<?> cDateTimeClass = CommonPlugin.loadClass("org.eclipse.nebula.widgets.cdatetime", "org.eclipse.nebula.widgets.cdatetime.CDateTime");
        cDateTimeConstructor = cDateTimeClass.getConstructor(Composite.class, int.class);
        getSelectionMethod = cDateTimeClass.getMethod("getSelection");
        setSelectionMethod = cDateTimeClass.getMethod("setSelection", Date.class);
        setPatternMethod = cDateTimeClass.getMethod("setPattern", String.class);
        addSelectionListenerMethod = cDateTimeClass.getMethod("addSelectionListener", SelectionListener.class);

        cDateTimeIsAvailable = true;
      }
      catch (Throwable exception)
      {
        // Ignore;
      }

      if (CDT_STYLES.isEmpty())
      {
        CDT_STYLES.put("NONE", 0);
        CDT_STYLES.put("BORDER", 1);
        CDT_STYLES.put("DROP_DOWN", 1 << 2);
        CDT_STYLES.put("SIMPLE", 1 << 3);
        CDT_STYLES.put("BUTTON_LEFT", 1 << 8);
        CDT_STYLES.put("BUTTON_RIGHT", 1 << 9);
        CDT_STYLES.put("BUTTON_AUTO", 1 << 19);
        CDT_STYLES.put("TEXT_LEFT", 1 << 10);
        CDT_STYLES.put("TEXT_LEAD", 1 << 10);
        CDT_STYLES.put("TEXT_RIGHT", 1 << 11);
        CDT_STYLES.put("TEXT_TRAIL", 1 << 11);
        CDT_STYLES.put("HORIZONTAL", 1 << 12);
        CDT_STYLES.put("VERTICAL", 1 << 13);
        CDT_STYLES.put("READ_ONLY", 1 << 14);
        CDT_STYLES.put("DATE_SHORT", 1 << 21);
        CDT_STYLES.put("DATE_MEDIUM", 1 << 22);
        CDT_STYLES.put("DATE_LONG", 1 << 23);
        CDT_STYLES.put("TIME_SHORT", 1 << 24);
        CDT_STYLES.put("TIME_MEDIUM", 1 << 25);
        CDT_STYLES.put("COMPACT", 1 << 16);
        CDT_STYLES.put("ADD_ON_ROLL", 1 << 20);
        CDT_STYLES.put("TAB_FIELDS", 1 << 26);
        CDT_STYLES.put("SPINNER", 1 << 27);
        CDT_STYLES.put("CLOCK_DISCRETE", 1 << 28);
        CDT_STYLES.put("CLOCK_12_HOUR", 1 << 29);
        CDT_STYLES.put("CLOCK_24_HOUR", 1 << 30);
        CDT_STYLES.put("MULTI", 1 << 31);
      }

      CDATE_TIME_CONSTRUCTOR = cDateTimeConstructor;
      GET_SELECTION_METHOD = getSelectionMethod;
      SET_SELECTION_METHOD = setSelectionMethod;
      SET_PATTERN_METHOD = setPatternMethod;
      ADD_SELECTION_LISTENER_METHOD = addSelectionListenerMethod;
      CDATETIME_IS_AVAILABLE = cDateTimeIsAvailable;
    }

    private static CellEditor create(Composite composite, int style, String pattern, ValueConverter valueConverter)
    {
      if (CDATETIME_IS_AVAILABLE && valueConverter != null)
      {
        return new DateCellEditor(composite, style, pattern, valueConverter);
      }
      else
      {
        return null;
      }
    }

    private static Object invoke(Method method, Object target, Object... arguments)
    {
      try
      {
        return method.invoke(target, arguments);
      }
      catch (Exception exception)
      {
        throw new RuntimeException();
      }
    }

    private Composite cDateTime;

    private final ValueConverter valueConverter;

    public DateCellEditor(Composite parent, int style, String pattern, ValueConverter valueConverter)
    {
      super(parent, style);
      this.valueConverter = valueConverter;

      // cDateTime.setPattern(pattern);
      invoke(SET_PATTERN_METHOD, cDateTime, pattern);
    }

    private Composite createCDateTime(Composite composite, int style)
    {
      try
      {
        // cDateTime = new CDateTime(composite, style);
        return (Composite)CDATE_TIME_CONSTRUCTOR.newInstance(composite, style);
      }
      catch (Exception exception)
      {
        throw new RuntimeException(exception);
      }
    }

    @Override
    protected Control createControl(Composite parent)
    {
      cDateTime = createCDateTime(parent, CDT_STYLES.get("DROP_DOWN") | getStyle());
      invoke(ADD_SELECTION_LISTENER_METHOD, cDateTime, new SelectionAdapter()
        {
          @Override
          public void widgetSelected(SelectionEvent e)
          {
            markDirty();
          }

          @Override
          public void widgetDefaultSelected(SelectionEvent e)
          {
            markDirty();
            fireApplyEditorValue();
            deactivate();
          }
        });
      cDateTime.addTraverseListener(new TraverseListener()
        {
          public void keyTraversed(TraverseEvent e)
          {
            if (e.detail == SWT.TRAVERSE_ESCAPE)
            {
              deactivate();
            }
          }
        });
      cDateTime.setFont(parent.getFont());
      cDateTime.setBackground(parent.getBackground());
      return cDateTime;
    }

    @Override
    public void deactivate()
    {
      if (isDirty())
      {
        fireApplyEditorValue();
      }
      super.deactivate();
    }

    @Override
    protected Object doGetValue()
    {
      return valueConverter.createFromDate((Date)invoke(GET_SELECTION_METHOD, cDateTime));
    }

    @Override
    protected void doSetFocus()
    {
      cDateTime.setFocus();
    }

    @Override
    protected void doSetValue(Object value)
    {
      invoke(SET_SELECTION_METHOD, cDateTime, valueConverter.convertToDate(value));
    }
  }
}
