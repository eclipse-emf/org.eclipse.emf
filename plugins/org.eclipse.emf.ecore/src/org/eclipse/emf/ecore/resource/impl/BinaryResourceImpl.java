/**
 * Copyright (c) 2007-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.resource.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.CommonUtil;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * An API for efficiently producing and consuming a compact binary serialization that's suitable for long term storage.
 * @since 2.4
 */
public class BinaryResourceImpl extends ResourceImpl
{
  /**
   * A save option to specify the {@link Version} to be used for the serialization.
   * @see Version
   * @since 2.7
   */
  public static final String OPTION_VERSION = "VERSION";

  /**
   * A Boolean save option to specify whether float and double values
   * are encoded using {@link Float#floatToIntBits(float)} and {@link Double#doubleToLongBits(double)} respectively,
   * rather than a string representation.
   * The default is true.
   * This style option is only supported for serializations with {@link Version#VERSION_1_1 version 1.1} or higher.
   * @see BinaryIO#STYLE_BINARY_FLOATING_POINT
   * @since 2.7
   */
  public static final String OPTION_STYLE_BINARY_FLOATING_POINT = "BINARY_FLOATING_POINT ";

  /**
   * A Boolean save option to specify whether {@link Date date} values will be serialized using {@link Date#getTime()} rather than a string representation.
   * This style option is only supported for serializations with {@link Version#VERSION_1_1 version 1.1} or higher.
   * The default is false.
   * @see BinaryIO#STYLE_BINARY_DATE
   * @since 2.7
   */
  public static final String OPTION_STYLE_BINARY_DATE = "BINARY_DATE";

  /**
   * A Boolean save option to specify whether serialized proxies will include the serialization of their attribute values.
   * This style option is only supported for serializations with {@link Version#VERSION_1_1 version 1.1} or higher.
   * The default is false.
   * @see BinaryIO#STYLE_PROXY_ATTRIBUTES
   * @since 2.7
   */
  public static final String OPTION_STYLE_PROXY_ATTRIBUTES = "PROXY_ATTRIBUTES";

  /**
   * A Boolean save option to specify whether {@link Enumerator enumerator} values will be serialized using {@link Enumerator#getValue()} rather than a string representation.
   * This style option is only supported for serializations with {@link Version#VERSION_1_1 version 1.1} or higher.
   * The default is false.
   * @see BinaryIO#STYLE_BINARY_ENUMERATOR
   * @since 2.8
   */
  public static final String OPTION_STYLE_BINARY_ENUMERATOR = "BINARY_ENUMERATOR";

  /**
   * A Boolean save option to specify whether values should be serialized using {@link DataConverter data converters}.
   * In general, a value will use a data converter only if the {@link EFactory factory} of the {@link EDataType data type}
   * implements its {@link DataConverter.Factory data converter factory} to return a non-null data converter.
   * If that data converter specifies that values should be {@link DataConverter.isTabulated() tabulated},
   * every {@link Object#equals(Object) unique} value will be serialized in full at most once.
   * Note that String values have specialized built-in data converter support and will hence also be tabulated if this option is specified.
   * This style option is only supported for serializations with {@link Version#VERSION_1_1 version 1.1} or higher.
   * The default value is false.
   * @since 2.9
   */
  public static final String OPTION_STYLE_DATA_CONVERTER = "DATA_CONVERTER";

  /**
   * A Boolean load option to specify whether proxies should be eagerly resolved during loading.
   * This can improve subsequent performance by saving the cost of repeatedly resolving the same proxy for each different use of that proxy.
   * It could also harm performance by forcing the loading of a large number of resources that might never otherwise be loaded.
   * Consider carefully the trade-offs involved in using this option.
   * @since 2.9
   */
  public static final String OPTION_EAGER_PROXY_RESOLUTION = "EAGER_PROXY_RESOLUTION";

  /**
   * Specify the capacity of the buffered stream
   * used when {@link #doSave(OutputStream, Map) saving} or {@link #doLoad(InputStream, Map) loading} the resource content.
   * The value must be an integer.
   * If not specified, {@link #DEFAULT_BUFFER_CAPACITY} is used.
   * A value less than one disables the cache.
   * @since 2.6
   */
  public static final String OPTION_BUFFER_CAPACITY = "BUFFER_CAPACITY";

  /**
   * The default {@link #OPTION_BUFFER_CAPACITY} capacity of the buffered stream
   * used when {@link #doSave(OutputStream, Map) saving} or {@link #doLoad(InputStream, Map) loading} the resource content.
   * @since 2.6
   */
  public static final int DEFAULT_BUFFER_CAPACITY = 1024;

  /**
   * Extract the {@link #OPTION_BUFFER_CAPACITY} from the options.
   * @param options a map of options.
   * @return the value associated with the {@link #OPTION_BUFFER_CAPACITY} key in the options map.
   * @since 2.6
   */
  public static int getBufferCapacity(Map<?, ?> options)
  {
    if (options != null)
    {
      Integer capacity = (Integer)options.get(OPTION_BUFFER_CAPACITY);
      if (capacity != null)
      {
        return capacity;
      }
    }
    return DEFAULT_BUFFER_CAPACITY;
  }

  /**
   * Specify the capacity of the internal byte array used in {@link EObjectInputStream} or {@link EObjectOutputStream} for buffering the reading and writing of bytes.
   * Note that this implies that {@link EObjectInputStream} will read more bytes from the input stream than it may actually consume,
   * and that {@link EObjectOutputStream } will write more bytes to the byte array than have actually been produced in the output stream.
   * In both cases it's necessary to call flush
   * either to put back the unused bytes, which is only possible if the wrapped input stream {@link InputStream#markSupported() supports marks}
   * or to emit the retained bytes.
   * A value less then 2 disables internal buffering; the default is 0.
   * @since 2.9
   */
  public static final String OPTION_INTERNAL_BUFFER_CAPACITY = "INTERNAL_BUFFER_CAPACITY";

  /**
   * Extract the {@link #OPTION_INTERNAL_BUFFER_CAPACITY} from the options.
   * @param options a map of options.
   * @return the value associated with the {@link #OPTION_INTERNAL_BUFFER_CAPACITY} key in the options map.
   * @since 2.9
   */
  public static int getInternalBufferCapacity(Map<?, ?> options)
  {
    if (options != null)
    {
      Integer capacity = (Integer)options.get(OPTION_INTERNAL_BUFFER_CAPACITY);
      if (capacity != null)
      {
        return capacity;
      }
    }
    return 0;
  }

  public BinaryResourceImpl()
  {
    super();
  }

  public BinaryResourceImpl(URI uri)
  {
    super(uri);
  }

  @Override
  protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException
  {
    if (outputStream instanceof URIConverter.Saveable)
    {
      ((URIConverter.Saveable)outputStream).saveResource(this);
    }
    else
    {
      boolean buffer = !(outputStream instanceof BufferedOutputStream);
      if (buffer)
      {
        int bufferCapacity = getBufferCapacity(options);
        if (bufferCapacity > 0)
        {
          outputStream = new BufferedOutputStream(outputStream, bufferCapacity);
        }
        else
        {
          buffer = false;
        }
      }

      try
      {
        EObjectOutputStream eObjectOutputStream = createEObjectOutputStream(outputStream, options);
        eObjectOutputStream.saveResource(this);
        eObjectOutputStream.flush();
      }
      finally
      {
        if (buffer)
        {
          outputStream.flush();
        }
      }
    }
  }

  /**
   * @since 2.9
   */
  protected EObjectOutputStream createEObjectOutputStream(OutputStream outputStream, Map<?, ?> options) throws IOException
  {
    return new EObjectOutputStream(outputStream, options);
  }

  @Override
  protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException
  {
    if (inputStream instanceof URIConverter.Loadable)
    {
      ((URIConverter.Loadable)inputStream).loadResource(this);
    }
    else
    {
      if (!(inputStream instanceof BufferedInputStream))
      {
        int bufferCapacity = getBufferCapacity(options);
        if (bufferCapacity > 0)
        {
          inputStream = new BufferedInputStream(inputStream, bufferCapacity);
        }
      }

      EObjectInputStream eObjectInputStream = createEObjectInputStream(inputStream, options);
      eObjectInputStream.loadResource(this);
      eObjectInputStream.flush();
    }
  }

  /**
   * @since 2.9
   */
  protected EObjectInputStream createEObjectInputStream(InputStream inputStream, Map<?, ?> options) throws IOException
  {
    return new EObjectInputStream(inputStream, options);
  }

  /**
   * Generally this abstract class is extended as a stateless singleton returned by a generated factory that implements the {@link DataConverter#Factory factory} interface.
   * The default implementation of {@link EFactoryImpl#create(EDataType)} returns <code>null</code>.
   * @since 2.9
   */
  public static abstract class DataConverter<T>
  {
    static final DataConverter<Object> NULL =
      new DataConverter<Object>()
      {
        @Override
        public Object read(EObjectInputStream eObjectInputStream) throws IOException
        {
          throw new UnsupportedOperationException();
        }

        @Override
        protected void doWrite(EObjectOutputStream eObjectOutputStream, Object value) throws IOException
        {
          throw new UnsupportedOperationException();
        }
      };

    /**
     * @see EFactoryImpl#create(EDataType)
     */
    public interface Factory
    {
      DataConverter<?> create(EDataType eDataType);
    }

    public boolean isTabulated()
    {
      return true;
    }

    public abstract T read(EObjectInputStream eObjectInputStream) throws IOException;

    @SuppressWarnings("unchecked")
    public void write(EObjectOutputStream eObjectOutputStream, Object value) throws IOException
    {
      doWrite(eObjectOutputStream, (T)value);
    }

    protected abstract void doWrite(EObjectOutputStream eObjectOutputStream, T value) throws IOException;
  }

  public static class BinaryIO
  {
    public enum Version
    {
      VERSION_1_0,

      /**
       * This version supports styles.
       * An extra integer value encoding the style is written after the version number so that deserialization will respect the styles used during serialization.
       * @since 2.7
       */
      VERSION_1_1
    }

    /**
     * @see BinaryResourceImpl#OPTION_STYLE_BINARY_FLOATING_POINT
     * @since 2.7
     */
    public static final int STYLE_BINARY_FLOATING_POINT = 1 << 0;

    /**
     * @see BinaryResourceImpl#OPTION_STYLE_BINARY_DATE
     * @since 2.7
     */
    public static final int STYLE_BINARY_DATE = 1 << 1;

    /**
     * @see BinaryResourceImpl#OPTION_STYLE_PROXY_ATTRIBUTES
     * @since 2.7
     */
    public static final int STYLE_PROXY_ATTRIBUTES = 1 << 2;

    /**
     * @see BinaryResourceImpl#OPTION_STYLE_BINARY_ENUMERATOR
     * @since 2.8
     */
    public static final int STYLE_BINARY_ENUMERATOR = 1 << 3;

    /**
     * @see BinaryResourceImpl#OPTION_STYLE_DATA_CONVERTER
     * @since 2.9
     */
    public static final int STYLE_DATA_CONVERTER = 1 << 4;

    protected Version version;

    /**
     * @since 2.7
     */
    protected int style;
    protected Resource resource;
    protected URI baseURI;
    protected Map<?, ?> options;
    protected char[] characters;
    protected InternalEObject[][] internalEObjectDataArrayBuffer = new InternalEObject[50][];
    protected int internalEObjectDataArrayBufferCount = -1;

    Map<EDataType, DataConverter<?>> dataConverterMap = new HashMap<EDataType, DataConverter<?>>();

    protected static int getStyle(Map<?, ?> options)
    {
      int result = STYLE_BINARY_FLOATING_POINT;
      if (options != null)
      {
        if (Boolean.FALSE.equals(options.get(OPTION_STYLE_BINARY_FLOATING_POINT)))
        {
          result &= ~STYLE_BINARY_FLOATING_POINT;
        }
        if (Boolean.TRUE.equals(options.get(OPTION_STYLE_BINARY_DATE)))
        {
          result |= STYLE_BINARY_DATE;
        }
        if (Boolean.TRUE.equals(options.get(OPTION_STYLE_PROXY_ATTRIBUTES)))
        {
          result |= STYLE_PROXY_ATTRIBUTES;
        }
        if (Boolean.TRUE.equals(options.get(OPTION_STYLE_BINARY_ENUMERATOR)))
        {
          result |= STYLE_BINARY_ENUMERATOR;
        }
        if (Boolean.TRUE.equals(options.get(OPTION_STYLE_DATA_CONVERTER)))
        {
          result |= STYLE_DATA_CONVERTER;
        }
      }
      return result;
    }

    protected URI resolve(URI uri)
    {
      return baseURI != null && uri.isRelative() && uri.hasRelativePath() ? uri.resolve(baseURI) : uri;
    }

    protected URI deresolve(URI uri)
    {
      if (baseURI != null && !uri.isRelative())
      {
        URI deresolvedURI = uri.deresolve(baseURI, true, true, false);
        if (deresolvedURI.hasRelativePath() && (!uri.isPlatform() || uri.segment(0).equals(baseURI.segment(0))))
        {
          uri = deresolvedURI;
        }
      }
      return uri;
    }

    protected InternalEObject [] allocateInternalEObjectArray(int length)
    {
      if (internalEObjectDataArrayBufferCount == -1)
      {
        return new InternalEObject[length];
      }
      else
      {
        InternalEObject [] buffer = internalEObjectDataArrayBuffer[internalEObjectDataArrayBufferCount];
        internalEObjectDataArrayBuffer[internalEObjectDataArrayBufferCount--] = null;
        return buffer.length >= length ? buffer :  new InternalEObject[length];
      }
    }

    protected void recycle(InternalEObject[] values)
    {
      if (++internalEObjectDataArrayBufferCount >= internalEObjectDataArrayBuffer.length)
      {
        InternalEObject [][] newInternalEObjectDataArrayBuffer = new InternalEObject[internalEObjectDataArrayBufferCount * 2][];
        System.arraycopy(internalEObjectDataArrayBuffer, 0, newInternalEObjectDataArrayBuffer, 0, internalEObjectDataArrayBufferCount);
        internalEObjectDataArrayBuffer = newInternalEObjectDataArrayBuffer;
      }
      internalEObjectDataArrayBuffer[internalEObjectDataArrayBufferCount] = values;
    }

    protected FeatureMap.Entry.Internal[][] featureMapEntryDataArrayBuffer = new FeatureMap.Entry.Internal[50][];
    protected int featureMapEntryDataArrayBufferCount = -1;

    protected FeatureMap.Entry.Internal [] allocateFeatureMapEntryArray(int length)
    {
      if (featureMapEntryDataArrayBufferCount == -1)
      {
        return new FeatureMap.Entry.Internal[length];
      }
      else
      {
        FeatureMap.Entry.Internal [] buffer = featureMapEntryDataArrayBuffer[featureMapEntryDataArrayBufferCount];
        featureMapEntryDataArrayBuffer[featureMapEntryDataArrayBufferCount--] = null;
        return buffer.length >= length ? buffer :  new FeatureMap.Entry.Internal[length];
      }
    }

    protected void recycle(FeatureMap.Entry.Internal[] values)
    {
      if (++featureMapEntryDataArrayBufferCount >= featureMapEntryDataArrayBuffer.length)
      {
        FeatureMap.Entry.Internal [][] newFeatureMapEntryDataArrayBuffer = new FeatureMap.Entry.Internal[featureMapEntryDataArrayBufferCount * 2][];
        System.arraycopy(featureMapEntryDataArrayBuffer, 0, newFeatureMapEntryDataArrayBuffer, 0, featureMapEntryDataArrayBufferCount);
        featureMapEntryDataArrayBuffer = newFeatureMapEntryDataArrayBuffer;
      }
      featureMapEntryDataArrayBuffer[featureMapEntryDataArrayBufferCount] = values;
    }

    protected enum FeatureKind
    {
      EOBJECT_CONTAINER,
      EOBJECT_CONTAINER_PROXY_RESOLVING,

      EOBJECT,
      EOBJECT_PROXY_RESOLVING,

      EOBJECT_LIST,
      EOBJECT_LIST_PROXY_RESOLVING,

      EOBJECT_CONTAINMENT,
      EOBJECT_CONTAINMENT_PROXY_RESOLVING,

      EOBJECT_CONTAINMENT_LIST,
      EOBJECT_CONTAINMENT_LIST_PROXY_RESOLVING,

      BOOLEAN,
      BYTE,
      CHAR,
      DOUBLE,
      FLOAT,
      INT,
      LONG,
      SHORT,
      STRING,

      /**
       * @since 2.7
       */
      DATE,

      /**
       * @since 2.8
       */
      ENUMERATOR,

      DATA,
      DATA_LIST,

      FEATURE_MAP;

      public static FeatureKind get(EStructuralFeature eStructuralFeature)
      {
        if (eStructuralFeature instanceof EReference)
        {
          EReference eReference = (EReference)eStructuralFeature;
          if (eReference.isContainment())
          {
            if (eReference.isResolveProxies())
            {
              if (eReference.isMany())
              {
                return EOBJECT_CONTAINMENT_LIST_PROXY_RESOLVING;
              }
              else
              {
                return EOBJECT_CONTAINMENT_PROXY_RESOLVING;
              }
            }
            else
            {
              if (eReference.isMany())
              {
                return EOBJECT_CONTAINMENT_LIST;
              }
              else
              {
                return EOBJECT_CONTAINMENT;
              }
            }
          }
          else if (eReference.isContainer())
          {
            if (eReference.isResolveProxies())
            {
              return EOBJECT_CONTAINER_PROXY_RESOLVING;
            }
            else
            {
              return EOBJECT_CONTAINER;
            }
          }
          else if (eReference.isResolveProxies())
          {
            if (eReference.isMany())
            {
              return EOBJECT_LIST_PROXY_RESOLVING;
            }
            else
            {
              return EOBJECT_PROXY_RESOLVING;
            }
          }
          else
          {
            if (eReference.isMany())
            {
              return EOBJECT_LIST;
            }
            else
            {
              return EOBJECT;
            }
          }
        }
        else
        {
          EAttribute eAttribute = (EAttribute)eStructuralFeature;
          EDataType eDataType = eAttribute.getEAttributeType();
          String instanceClassName = eDataType.getInstanceClassName();
          if (instanceClassName == "org.eclipse.emf.ecore.util.FeatureMap$Entry")
          {
            return FEATURE_MAP;
          }
          else if (eAttribute.isMany())
          {
            return DATA_LIST;
          }
          else if (instanceClassName == "java.lang.String")
          {
            return STRING;
          }
          else if (instanceClassName == "boolean")
          {
            return BOOLEAN;
          }
          else if (instanceClassName == "byte")
          {
            return BYTE;
          }
          else if (instanceClassName == "char")
          {
            return CHAR;
          }
          else if (instanceClassName == "double")
          {
            return DOUBLE;
          }
          else if (instanceClassName == "float")
          {
            return FLOAT;
          }
          else if (instanceClassName == "int")
          {
            return INT;
          }
          else if (instanceClassName == "long")
          {
            return LONG;
          }
          else if (instanceClassName == "short")
          {
            return SHORT;
          }
          else if (instanceClassName == "java.util.Date")
          {
            return DATE;
          }
          else if (eDataType instanceof EEnum)
          {
            return ENUMERATOR;
          }
          else
          {
            return DATA;
          }
        }
      }
    }

    static final int MAX_DELIMITER = 0xC0;
    static final String[] DELIMITERS = new String[MAX_DELIMITER];
    static final List<String> INTRINSIC_STRINGS = new ArrayList<String>();
    static final Map<String, Integer> INTRINSIC_STRING_TO_ID_MAP = new HashMap<String, Integer>();

    static
    {
      INTRINSIC_STRING_TO_ID_MAP.put("", 0);
      INTRINSIC_STRINGS.add("");

      for (char c = 0; c < MAX_DELIMITER; ++c)
      {
        if (Character.isDigit(c))
        {
          String string =  CommonUtil.javaIntern(Character.toString(c));
          INTRINSIC_STRING_TO_ID_MAP.put(string, INTRINSIC_STRINGS.size());
          INTRINSIC_STRINGS.add(string);
        }
        else if (!Character.isLetter(c))
        {
          String delimiter = CommonUtil.javaIntern(Character.toString(c));
          DELIMITERS[c] = delimiter;
          INTRINSIC_STRING_TO_ID_MAP.put(delimiter, INTRINSIC_STRINGS.size());
          INTRINSIC_STRINGS.add(delimiter);
        }
      }
    }
  }

  public static class EObjectOutputStream extends BinaryIO
  {
    public enum Check
    {
      NOTHING,
      DIRECT_RESOURCE,
      RESOURCE,
      CONTAINER
    }

    protected static class EPackageData
    {
      public int id;
      public EClassData[] eClassData;

      public final int allocateEClassID()
      {
        for (int i = 0, length = eClassData.length; i < length; ++i)
        {
          EClassData eClassData = this.eClassData[i];
          if (eClassData == null)
          {
            return i;
          }
        }
        return -1;
      }
    }

    protected static class EClassData
    {
      public int ePackageID;
      public int id;
      public EStructuralFeatureData[] eStructuralFeatureData;
    }

    protected static class EStructuralFeatureData
    {
      public String name;
      public boolean isTransient;
      /**
       * @since 2.7
       */
      public boolean isProxyTransient;
      public FeatureKind kind;
      public EFactory eFactory;
      public EDataType eDataType;
      /**
       * @since 2.9
       */
      public DataConverter<?> dataConverter;
    }

    private byte[] bytes;
    private int index;

    protected OutputStream outputStream;
    protected Map<EPackage, EPackageData> ePackageDataMap = new HashMap<EPackage, EPackageData>();
    protected Map<EClass, EClassData> eClassDataMap = new HashMap<EClass, EClassData>();
    protected Map<EObject, Integer> eObjectIDMap = new HashMap<EObject, Integer>();
    protected Map<URI, Integer> uriToIDMap = new HashMap<URI, Integer>();

    private Map<String, Integer> segmentedStringToIDMap;
    private Map<String, Integer> segmentToIDMap;
    private String[] segments;

    public EObjectOutputStream(OutputStream outputStream, Map<?, ?> options) throws IOException
    {
      this(outputStream, options, options != null && options.containsKey(OPTION_VERSION)? (Version)options.get(OPTION_VERSION) : Version.VERSION_1_0);
    }

    public EObjectOutputStream(OutputStream outputStream, Map<?, ?> options, Version version) throws IOException
    {
      this(outputStream, options, version, version.ordinal() > 0 ? getStyle(options) : STYLE_BINARY_FLOATING_POINT);
    }

    /**
     * @since 2.7
     */
    public EObjectOutputStream(OutputStream outputStream, Map<?, ?> options, Version version, int style) throws IOException
    {
      this.outputStream = outputStream;
      this.options = options;
      this.version = version;
      this.style = style;

      int bufferCapacity = getInternalBufferCapacity(options);
      if (bufferCapacity > 1)
      {
       bytes = new byte[bufferCapacity];
      }

      writeSignature();
      writeVersion();
      if (version.ordinal() > 0)
      {
        writeStyle();
      }

      if ((style & STYLE_DATA_CONVERTER) != 0)
      {
        segmentedStringToIDMap = new HashMap<String, Integer>();
        segmentToIDMap = new HashMap<String, Integer>(INTRINSIC_STRING_TO_ID_MAP);
      }
    }

    protected void writeSignature() throws IOException
    {
      // Write a signature that will be obviously corrupt
      // if the binary contents end up being UTF-8 encoded
      // or altered by line feed or carriage return changes.
      //
      writeByte('\211');
      writeByte('e');
      writeByte('m');
      writeByte('f');
      writeByte('\n');
      writeByte('\r');
      writeByte('\032');
      writeByte('\n');
    }

    protected void writeVersion() throws IOException
    {
      writeByte(version.ordinal());
    }

    /**
     * @since 2.7
     */
    protected void writeStyle() throws IOException
    {
      writeInt(style);
    }

    protected EPackageData writeEPackage(EPackage ePackage) throws IOException
    {
      EPackageData ePackageData = ePackageDataMap.get(ePackage);
      if (ePackageData == null)
      {
        ePackageData = new EPackageData();
        int id = ePackageDataMap.size();
        ePackageData.id = id;
        ePackageData.eClassData = new EClassData[ePackage.getEClassifiers().size()];
        writeCompressedInt(id);
        writeSegmentedString(ePackage.getNsURI());
        writeURI(EcoreUtil.getURI(ePackage));
        ePackageDataMap.put(ePackage, ePackageData);
      }
      else
      {
        writeCompressedInt(ePackageData.id);
      }
      return ePackageData;
    }

    protected EClassData writeEClass(EClass eClass) throws IOException
    {
      EClassData eClassData = eClassDataMap.get(eClass);
      if (eClassData == null)
      {
        eClassData = new EClassData();
        EPackageData ePackageData = writeEPackage(eClass.getEPackage());
        eClassData.ePackageID = ePackageData.id;
        writeCompressedInt(eClassData.id = ePackageData.allocateEClassID());
        writeString(eClass.getName());
        int featureCount = eClass.getFeatureCount();
        EStructuralFeatureData [] eStructuralFeaturesData = eClassData.eStructuralFeatureData = new EStructuralFeatureData[featureCount];
        for (int i = 0; i < featureCount; ++i)
        {
          EStructuralFeatureData eStructuralFeatureData = eStructuralFeaturesData[i] = new EStructuralFeatureData();
          EStructuralFeature.Internal eStructuralFeature = (EStructuralFeature.Internal)eClass.getEStructuralFeature(i);
          eStructuralFeatureData.name = eStructuralFeature.getName();
          eStructuralFeatureData.isTransient = eStructuralFeature.isTransient() || eStructuralFeature.isContainer() && !eStructuralFeature.isResolveProxies();
          eStructuralFeatureData.kind = FeatureKind.get(eStructuralFeature);
          if (eStructuralFeature instanceof EAttribute)
          {
            EAttribute eAttribute = (EAttribute)eStructuralFeature;
            EDataType eDataType = eAttribute.getEAttributeType();
            eStructuralFeatureData.eDataType = eDataType;
            eStructuralFeatureData.eFactory = eDataType.getEPackage().getEFactoryInstance();
            eStructuralFeatureData.isProxyTransient = eStructuralFeatureData.kind == FeatureKind.FEATURE_MAP;
            if (segmentedStringToIDMap != null)
            {
              DataConverter<?> dataConverter = dataConverterMap.get(eDataType);
              if (dataConverter != DataConverter.NULL)
              {
                if (dataConverter == null)
                {
                  final DataConverter<?> rawDataConverter = ((DataConverter.Factory)eStructuralFeatureData.eFactory).create(eDataType);
                  if (rawDataConverter == null)
                  {
                    dataConverterMap.put(eDataType,  DataConverter.NULL);
                  }
                  else
                  {
                    if (rawDataConverter.isTabulated())
                    {
                      DataConverter<?> tabulatedDataConverter =
                        new DataConverter<Object>()
                        {
                          Map<Object, Integer> objectToIDMap = new HashMap<Object, Integer>();

                          @Override
                          public Object read(EObjectInputStream eObjectInputStream) throws IOException
                          {
                            throw new UnsupportedOperationException();
                          }

                          @Override
                          public void write(EObjectOutputStream eObjectOutputStream, Object value) throws IOException
                          {
                            if (value == null)
                            {
                              eObjectOutputStream.writeCompressedInt(-1);
                            }
                            else
                            {
                              Integer id = objectToIDMap.get(value);
                              if (id == null)
                              {
                                int idValue = objectToIDMap.size();
                                objectToIDMap.put(value, idValue);
                                eObjectOutputStream.writeCompressedInt(idValue);
                                super.write(eObjectOutputStream, value);
                              }
                              else
                              {
                                eObjectOutputStream.writeCompressedInt(id);
                              }
                            }
                          }

                          @Override
                          protected void doWrite(EObjectOutputStream eObjectOutputStream, Object value) throws IOException
                          {
                            rawDataConverter.write(eObjectOutputStream, value);
                          }
                        };
                      dataConverter = tabulatedDataConverter;
                    }
                    else
                    {
                      dataConverter = rawDataConverter;
                    }
                    dataConverterMap.put(eDataType, dataConverter);
                  }
                }

                eStructuralFeatureData.dataConverter = dataConverter;
              }
            }
          }
          else
          {
            eStructuralFeatureData.isProxyTransient = true;
          }
        }
        ePackageData.eClassData[eClassData.id] = eClassData;
        eClassDataMap.put(eClass, eClassData);
      }
      else
      {
        writeCompressedInt(eClassData.ePackageID);
        writeCompressedInt(eClassData.id);
      }
      return eClassData;
    }

    protected EStructuralFeatureData writeEStructuralFeature(EStructuralFeature eStructuralFeature) throws IOException
    {
      EClass eClass = eStructuralFeature.getEContainingClass();
      EClassData eClassData = writeEClass(eClass);
      int featureID = eClass.getFeatureID(eStructuralFeature);
      EStructuralFeatureData eStructuralFeatureData = eClassData.eStructuralFeatureData[featureID];
      writeCompressedInt(featureID);
      if (eStructuralFeatureData.name != null)
      {
        writeString(eStructuralFeatureData.name);
        if (segmentedStringToIDMap != null && eStructuralFeatureData.eDataType != null)
        {
          writeBoolean(eStructuralFeatureData.dataConverter != null);
        }
        eStructuralFeatureData.name = null;
      }
      return eStructuralFeatureData;
    }

    public void saveResource(Resource resource) throws IOException
    {
      this.resource = resource;
      URI uri = resource.getURI();
      if (uri != null && uri.isHierarchical() && !uri.isRelative())
      {
        baseURI = uri;
      }
      @SuppressWarnings("unchecked")
      InternalEList<? extends InternalEObject> internalEList = (InternalEList<? extends InternalEObject>)(InternalEList<?>)resource.getContents();
      saveEObjects(internalEList, Check.CONTAINER);
    }

    public void saveEObjects(InternalEList<? extends InternalEObject> internalEObjects, Check check) throws IOException
    {
      int size = internalEObjects.size();
      InternalEObject [] values = allocateInternalEObjectArray(size);
      internalEObjects.basicToArray(values);
      writeCompressedInt(size);
      for (int i = 0; i < size; ++i)
      {
        InternalEObject internalEObject = values[i];
        saveEObject(internalEObject, check);
      }
      recycle(values);
    }

    public void saveFeatureMap(FeatureMap.Internal featureMap) throws IOException
    {
      int size = featureMap.size();
      FeatureMap.Entry.Internal [] values = allocateFeatureMapEntryArray(size);
      featureMap.toArray(values);
      writeCompressedInt(size);
      for (int i = 0; i < size; ++i)
      {
        FeatureMap.Entry.Internal entry = values[i];
        saveFeatureMapEntry(entry);
      }
      recycle(values);
    }

    public void saveFeatureMapEntry(FeatureMap.Entry.Internal entry) throws IOException
    {
      EStructuralFeatureData eStructuralFeatureData = writeEStructuralFeature(entry.getEStructuralFeature());
      Object value = entry.getValue();
      switch (eStructuralFeatureData.kind)
      {
        case EOBJECT:
        case EOBJECT_LIST:
        case EOBJECT_CONTAINMENT:
        case EOBJECT_CONTAINMENT_LIST:
        {
          saveEObject((InternalEObject)value, Check.NOTHING);
          break;
        }
        case EOBJECT_CONTAINMENT_PROXY_RESOLVING:
        case EOBJECT_CONTAINMENT_LIST_PROXY_RESOLVING:
        {
          saveEObject((InternalEObject)value, Check.DIRECT_RESOURCE);
          break;
        }
        case EOBJECT_PROXY_RESOLVING:
        case EOBJECT_LIST_PROXY_RESOLVING:
        {
          saveEObject((InternalEObject)value, Check.RESOURCE);
          break;
        }
        case BOOLEAN:
        {
          writeBoolean((Boolean)value);
          break;
        }
        case BYTE:
        {
          writeByte((Byte)value);
          break;
        }
        case CHAR:
        {
          writeChar((Character)value);
          break;
        }
        case DOUBLE:
        {
          writeDouble((Double)value);
          break;
        }
        case FLOAT:
        {
          writeFloat((Float)value);
          break;
        }
        case INT:
        {
          writeInt((Integer)value);
          break;
        }
        case LONG:
        {
          writeLong((Long)value);
          break;
        }
        case SHORT:
        {
          writeShort((Short)value);
          break;
        }
        case STRING:
        {
          writeSegmentedString((String)value);
          break;
        }
        case DATE:
        {
          if (eStructuralFeatureData.dataConverter != null)
          {
            eStructuralFeatureData.dataConverter.write(this, value);
          }
          else if ((style & STYLE_BINARY_DATE) != 0)
          {
            writeDate((Date)value);
          }
          else
          {
            writeSegmentedString(eStructuralFeatureData.eFactory.convertToString(eStructuralFeatureData.eDataType, value));
          }
          break;
        }
        case ENUMERATOR:
        {
          if (eStructuralFeatureData.dataConverter != null)
          {
            eStructuralFeatureData.dataConverter.write(this, value);
          }
          else if ((style & STYLE_BINARY_ENUMERATOR) != 0)
          {
            writeInt(((Enumerator)value).getValue());
          }
          else
          {
            writeString(eStructuralFeatureData.eFactory.convertToString(eStructuralFeatureData.eDataType, value));
          }
          break;
        }
        case DATA:
        case DATA_LIST:
        {
          if (eStructuralFeatureData.dataConverter != null)
          {
            eStructuralFeatureData.dataConverter.write(this, value);
          }
          else
          {
            String literal = eStructuralFeatureData.eFactory.convertToString(eStructuralFeatureData.eDataType, value);
            writeSegmentedString(literal);
          }
          break;
        }
        default:
        {
          throw new IOException("Unhandled case " + eStructuralFeatureData.kind);
        }
      }
    }

    public void saveEObject(InternalEObject internalEObject, Check check) throws IOException
    {
      if (internalEObject == null)
      {
        writeCompressedInt(-1);
      }
      else
      {
        Integer id = eObjectIDMap.get(internalEObject);
        if (id == null)
        {
          int idValue = eObjectIDMap.size();
          writeCompressedInt(idValue);
          eObjectIDMap.put(internalEObject, idValue);
          EClass eClass = internalEObject.eClass();
          EClassData eClassData = writeEClass(eClass);
          boolean checkIsTransientProxy = false;
          switch (check)
          {
            case DIRECT_RESOURCE:
            {
              Internal resource = internalEObject.eDirectResource();
              if (resource != null)
              {
                writeCompressedInt(-1);
                writeURI(resource.getURI(), resource.getURIFragment(internalEObject));
                if ((style & STYLE_PROXY_ATTRIBUTES) == 0)
                {
                  return;
                }
                checkIsTransientProxy = true;
              }
              else if (internalEObject.eIsProxy())
              {
                writeCompressedInt(-1);
                writeURI(internalEObject.eProxyURI());
                if ((style & STYLE_PROXY_ATTRIBUTES) == 0)
                {
                  return;
                }
                checkIsTransientProxy = true;
              }
              break;
            }
            case RESOURCE:
            {
              Resource resource = internalEObject.eResource();
              if (resource != this.resource && resource != null)
              {
                writeCompressedInt(-1);
                writeURI(resource.getURI(), resource.getURIFragment(internalEObject));
                if ((style & STYLE_PROXY_ATTRIBUTES) == 0)
                {
                  return;
                }
                checkIsTransientProxy = true;
              }
              else if (internalEObject.eIsProxy())
              {
                writeCompressedInt(-1);
                writeURI(internalEObject.eProxyURI());
                if ((style & STYLE_PROXY_ATTRIBUTES) == 0)
                {
                  return;
                }
                checkIsTransientProxy = true;
              }
              break;
            }
            case NOTHING:
            case CONTAINER:
            {
              break;
            }
          }
          EStructuralFeatureData [] eStructuralFeatureData = eClassData.eStructuralFeatureData;
          for (int i = 0, length = eStructuralFeatureData.length; i < length; ++i)
          {
            EStructuralFeatureData structuralFeatureData = eStructuralFeatureData[i];
            if (!structuralFeatureData.isTransient &&
                  (structuralFeatureData.kind != FeatureKind.EOBJECT_CONTAINER_PROXY_RESOLVING || check == Check.CONTAINER) &&
                  (!checkIsTransientProxy || !structuralFeatureData.isProxyTransient))
            {
              saveFeatureValue(internalEObject, i, structuralFeatureData);
            }
          }
          writeCompressedInt(0);
        }
        else
        {
          writeCompressedInt(id);
        }
      }
    }

    protected void saveFeatureValue(InternalEObject internalEObject, int featureID, EStructuralFeatureData eStructuralFeatureData) throws IOException
    {
      if (internalEObject.eIsSet(featureID))
      {
        writeCompressedInt(featureID + 1);
        if (eStructuralFeatureData.name != null)
        {
          writeString(eStructuralFeatureData.name);
          if (segmentedStringToIDMap != null && eStructuralFeatureData.eDataType != null)
          {
            writeBoolean(eStructuralFeatureData.dataConverter != null);
          }
          eStructuralFeatureData.name = null;
        }
        Object value = internalEObject.eGet(featureID, false, true);
        saveFeatureValue(internalEObject, value, featureID, eStructuralFeatureData);
      }
    }

    /**
     * @since 2.9
     */
    protected void saveFeatureValue(InternalEObject internalEObject, Object value, int featureID, EStructuralFeatureData eStructuralFeatureData) throws IOException
    {
      switch (eStructuralFeatureData.kind)
      {
        case EOBJECT:
        case EOBJECT_CONTAINMENT:
        {
          saveEObject((InternalEObject)value, Check.NOTHING);
          break;
        }
        case EOBJECT_CONTAINER_PROXY_RESOLVING:
        {
          saveEObject((InternalEObject)value, Check.RESOURCE);
          break;
        }
        case EOBJECT_CONTAINMENT_PROXY_RESOLVING:
        {
          saveEObject((InternalEObject)value, Check.DIRECT_RESOURCE);
          break;
        }
        case EOBJECT_PROXY_RESOLVING:
        {
          saveEObject((InternalEObject)value, Check.RESOURCE);
          break;
        }
        case EOBJECT_LIST:
        case EOBJECT_CONTAINMENT_LIST:
        {
          @SuppressWarnings("unchecked")
          InternalEList<? extends InternalEObject> internalEList = (InternalEList<? extends InternalEObject>)value;
          saveEObjects(internalEList, Check.NOTHING);
          break;
        }
        case EOBJECT_CONTAINMENT_LIST_PROXY_RESOLVING:
        {
          @SuppressWarnings("unchecked")
          InternalEList<? extends InternalEObject> internalEList = (InternalEList<? extends InternalEObject>)value;
          saveEObjects(internalEList, Check.DIRECT_RESOURCE);
          break;
        }
        case EOBJECT_LIST_PROXY_RESOLVING:
        {
          @SuppressWarnings("unchecked")
          InternalEList<? extends InternalEObject> internalEList = (InternalEList<? extends InternalEObject>)value;
          saveEObjects(internalEList, Check.RESOURCE);
          break;
        }
        case BOOLEAN:
        {
          writeBoolean((Boolean)value);
          break;
        }
        case BYTE:
        {
          writeByte((Byte)value);
          break;
        }
        case CHAR:
        {
          writeChar((Character)value);
          break;
        }
        case DOUBLE:
        {
          writeDouble((Double)value);
          break;
        }
        case FLOAT:
        {
          writeFloat((Float)value);
          break;
        }
        case INT:
        {
          writeInt((Integer)value);
          break;
        }
        case LONG:
        {
          writeLong((Long)value);
          break;
        }
        case SHORT:
        {
          writeShort((Short)value);
          break;
        }
        case STRING:
        {
          writeSegmentedString((String)value);
          break;
        }
        case FEATURE_MAP:
        {
          FeatureMap.Internal featureMap = (FeatureMap.Internal)value;
          saveFeatureMap(featureMap);
          break;
        }
        case DATE:
        {
          if (eStructuralFeatureData.dataConverter != null)
          {
            eStructuralFeatureData.dataConverter.write(this, value);
          }
          else if ((style & STYLE_BINARY_DATE) != 0)
          {
            writeDate((Date)value);
          }
          else
          {
            writeSegmentedString(eStructuralFeatureData.eFactory.convertToString(eStructuralFeatureData.eDataType, value));
          }
          break;
        }
        case ENUMERATOR:
        {
          if (eStructuralFeatureData.dataConverter != null)
          {
            eStructuralFeatureData.dataConverter.write(this, value);
          }
          else if ((style & STYLE_BINARY_ENUMERATOR) != 0)
          {
            writeInt(((Enumerator)value).getValue());
          }
          else
          {
            writeString(eStructuralFeatureData.eFactory.convertToString(eStructuralFeatureData.eDataType, value));
          }
          break;
        }
        case DATA:
        {
          if (eStructuralFeatureData.dataConverter != null)
          {
            eStructuralFeatureData.dataConverter.write(this, value);
          }
          else
          {
            String literal = eStructuralFeatureData.eFactory.convertToString(eStructuralFeatureData.eDataType, value);
            writeSegmentedString(literal);
          }
          break;
        }
        case DATA_LIST:
        {
          List<?> dataValues = (List<?>)value;
          int length = dataValues.size();
          writeCompressedInt(length);
          if (eStructuralFeatureData.dataConverter != null)
          {
            for (int j = 0; j < length; ++j)
            {
              eStructuralFeatureData.dataConverter.write(this, dataValues.get(j));
            }
          }
          else
          {
            for (int j = 0; j < length; ++j)
            {
              String literal = eStructuralFeatureData.eFactory.convertToString(eStructuralFeatureData.eDataType, dataValues.get(j));
              writeSegmentedString(literal);
            }
          }
          break;
        }
        default:
        {
          throw new IOException("Unhandled case " + eStructuralFeatureData.kind);
        }
      }
    }

    public void writeByte(int value) throws IOException
    {
      if (bytes == null)
      {
        outputStream.write(value);
      }
      else
      {
        if (index == bytes.length)
        {
          doFlush();
        }
        bytes[index++] = (byte)value;
      }
    }

    /**
     * @since 2.9
     */
    public void flush() throws IOException
    {
      if (bytes != null && index > 0)
      {
        doFlush();
      }
    }

    private void doFlush() throws IOException
    {
      outputStream.write(bytes, 0, index);
      index = 0;
    }

    public void writeBoolean(boolean value) throws IOException
    {
      writeByte(value ? 1 : 0);
    }

    public void writeChar(int value) throws IOException
    {
      writeByte((byte)(value >> 8 & 0xFF));
      writeByte((byte)(value & 0xFF));
    }

    public void writeShort(int value) throws IOException
    {
      writeByte((byte)(value >> 8 & 0xFF));
      writeByte((byte)(value & 0xFF));
    }

    public void writeInt(int value) throws IOException
    {
      writeByte((byte)(value >> 24 & 0xFF));
      writeByte((byte)(value >> 16 & 0xFF));
      writeByte((byte)(value >> 8 & 0xFF));
      writeByte((byte)(value & 0xFF));
    }

    public void writeLong(long value) throws IOException
    {
      writeInt((int)(value >> 32));
      writeInt((int)value);
    }

    public void writeFloat(float value) throws IOException
    {
      if ((style & STYLE_BINARY_FLOATING_POINT) != 0)
      {
        writeInt(Float.floatToIntBits(value));
      }
      else
      {
        writeString(Float.toString(value));
      }
    }

    public void writeDouble(double value) throws IOException
    {
      if ((style & STYLE_BINARY_FLOATING_POINT) != 0)
      {
        writeLong(Double.doubleToLongBits(value));
      }
      else
      {
        writeString(Double.toString(value));
      }
    }

    public void writeCompressedInt(int value) throws IOException
    {
      ++value;
      if ( value < 0)
      {
        handleInvalidValue(value);
      }
      else if (value <= 0x3F)
      {
        writeByte(value);
      }
      else if (value <= 0x3FFF)
      {
        writeByte(value >> 8 | 0x40);
        writeByte(value & 0xFF);
      }
      else if (value <= 0x3FFFFF)
      {
        writeByte(value >> 16 | 0x80);
        writeByte(value >> 8 & 0xFF);
        writeByte(value & 0xFF);
      }
      else if (value <= 0x3FFFFFFF)
      {
        writeByte(value >> 24 | 0xC0);
        writeByte(value >> 16 & 0xFF);
        writeByte(value >> 8 & 0xFF);
        writeByte(value & 0xFF);
      }
      else
      {
        handleInvalidValue(value);
      }
    }

    private final void handleInvalidValue(int value) throws IOException
    {
      throw new IOException("Invalid value " + value);
    }

    private void ensureSegmentCapacity(int capacity)
    {
      if (segments == null)
      {
        segments = new String [Math.max(20, capacity)];
      }
      else if (segments.length < capacity)
      {
        String[] newSegments = new String [Math.max(2 * segments.length, capacity)];
        System.arraycopy(segments, 0, newSegments, 0, segments.length);
        segments = newSegments;
      }
    }

    /**
     * @since 2.9
     */
    public void writeSegmentedString(String value) throws IOException
    {
      if (segmentedStringToIDMap == null)
      {
        writeString(value);
      }
      else if (value == null)
      {
        writeCompressedInt(-1);
      }
      else
      {
        Integer id = segmentedStringToIDMap.get(value);
        if (id == null)
        {
          int idValue = segmentedStringToIDMap.size();
          segmentedStringToIDMap.put(value, idValue);
          writeCompressedInt(idValue);

          int segmentCount = 0;
          int start = 0;
          int end = value.length();
          for (int i = 0; i < end; ++i)
          {
            char c = value.charAt(i);
            if (c < MAX_DELIMITER)
            {
              String delimiter = DELIMITERS[c];
              if (delimiter != null)
              {
                ensureSegmentCapacity(segmentCount + 2);
                if (start < i)
                {
                  segments[segmentCount++] = value.substring(start, i);
                }
                segments[segmentCount++] = delimiter;
                start = i + 1;
              }
            }
          }
          if (start == 0 || segmentCount == 1 && start == end)
          {
            writeCompressedInt(0);
            writeString(value);
          }
          else
          {
            if (start < end)
            {
              ensureSegmentCapacity(segmentCount + 1);
              segments[segmentCount++] = value.substring(start, end);
            }

            writeCompressedInt(segmentCount);
            for (int i = 0; i < segmentCount; ++i)
            {
              writeString(segments[i]);
            }
          }
        }
        else
        {
          writeCompressedInt(id);
        }
      }
    }

    public void writeString(String value) throws IOException
    {
      if (value == null)
      {
        writeCompressedInt(-1);
      }
      else
      {
        if (segmentToIDMap != null)
        {
          Integer id = segmentToIDMap.get(value);
          if (id != null)
          {
            writeCompressedInt(id);
            return;
          }
          else
          {
            int idValue = segmentToIDMap.size();
            segmentToIDMap.put(value, idValue);
            writeCompressedInt(idValue);
          }
        }

        int length = value.length();
        writeCompressedInt(length);
        if (characters == null || characters.length < length)
        {
          characters = new char[length];
        }
        value.getChars(0, length, characters, 0);
        LOOP:
        for (int i = 0; i < length; ++i)
        {
          char character = characters[i];
          if (character == 0 || character > 0xFF)
          {
            writeByte((byte)0);
            writeChar(character);
            while (++i < length)
            {
              writeChar(characters[i]);
            }
            break LOOP;
          }
          else
          {
            writeByte((byte)character);
          }
        }
      }
    }

    public void writeDate(Date date) throws IOException
    {
      writeLong(date.getTime());
    }

    public void writeURI(URI uri) throws IOException
    {
      writeURI(uri.trimFragment(), uri.fragment());
    }

    public void writeURI(URI uri, String fragment) throws IOException
    {
      if (uri == null)
      {
        writeCompressedInt(-1);
      }
      else
      {
        assert uri.fragment() == null;
        Integer id = uriToIDMap.get(uri);
        if (id == null)
        {
          int idValue = uriToIDMap.size();
          uriToIDMap.put(uri, idValue);
          writeCompressedInt(idValue);
          writeSegmentedString(deresolve(uri).toString());
        }
        else
        {
          writeCompressedInt(id);
        }
        writeSegmentedString(fragment);
      }
    }
  }

  public static class EObjectInputStream extends BinaryIO
  {
    protected static class EPackageData
    {
      public EPackage ePackage;
      public EClassData[] eClassData;

      public final int allocateEClassID()
      {
        for (int i = 0, length = eClassData.length; i < length; ++i)
        {
          EClassData eClassData = this.eClassData[i];
          if (eClassData == null)
          {
            return i;
          }
        }
        return -1;
      }
    }

    protected static class EClassData
    {
      public EClass eClass;
      public EFactory eFactory;
      public EStructuralFeatureData[] eStructuralFeatureData;
    }

    protected static class EStructuralFeatureData
    {
      public int featureID;
      public EStructuralFeature eStructuralFeature;
      public FeatureKind kind;
      public EFactory eFactory;
      public EDataType eDataType;
      /**
       * @since 2.9
       */
      public DataConverter<?> dataConverter;
    }

    private static class InternalEObjectList extends BasicEList<InternalEObject>
    {
      private static final long serialVersionUID = 1L;

      public InternalEObject[] eObjects;

      public InternalEObjectList()
      {
        super(1000);
      }

      @Override
      protected Object[] newData(int capacity)
      {
        return eObjects = new InternalEObject[capacity];
      }

      @Override
      public final boolean add(InternalEObject object)
      {
        if (size == eObjects.length)
        {
          grow(size + 1);
        }
        eObjects[size++] = object;
        return true;
      }
    }

    private static class StringList extends BasicEList<String>
    {
      private static final long serialVersionUID = 1L;

      public String[] strings;

      public StringList()
      {
        super(1000);
      }

      @Override
      protected Object[] newData(int capacity)
      {
        return strings = new String[capacity];
      }

      @Override
      public final boolean add(String object)
      {
        if (size == strings.length)
        {
          grow(size + 1);
        }
        strings[size++] = object;
        return true;
      }
    }

    private static class URIList extends BasicEList<URI>
    {
      private static final long serialVersionUID = 1L;

      public URI[] uris;

      public URIList()
      {
        super(1000);
      }

      @Override
      protected Object[] newData(int capacity)
      {
        return uris = new URI[capacity];
      }

      @Override
      public final boolean add(URI object)
      {
        if (size == uris.length)
        {
          grow(size + 1);
        }
        uris[size++] = object;
        return true;
      }
    }

    private static class DataValueList extends BasicEList<Object>
    {
      private static final long serialVersionUID = 1L;

      public Object[] values;

      public DataValueList()
      {
        super(1000);
      }

      @Override
      protected Object[] newData(int capacity)
      {
        return values = new Object[capacity];
      }

      @Override
      public final boolean add(Object object)
      {
        if (size == data.length)
        {
          grow(size + 1);
        }
        data[size++] = object;
        return true;
      }
    }

    private static class EPackageDataList extends BasicEList<EPackageData>
    {
      private static final long serialVersionUID = 1L;

      public EPackageData[] ePackageData;

      public EPackageDataList()
      {
        super(20);
      }

      @Override
      protected Object[] newData(int capacity)
      {
        return ePackageData = new EPackageData[capacity];
      }

      @Override
      public final boolean add(EPackageData object)
      {
        if (size == data.length)
        {
          grow(size + 1);
        }
        ePackageData[size++] = object;
        return true;
      }

    }

    private boolean isMarkSupported;
    private byte[] bytes;
    private int index;
    private int count;

    protected ResourceSet resourceSet;
    protected InputStream inputStream;

    private EPackageDataList internalEPackageDataList = new EPackageDataList();
    protected List<EPackageData> ePackageDataList = internalEPackageDataList;

    private InternalEObjectList internalInternalEObjectList = new InternalEObjectList();
    protected List<InternalEObject> eObjectList = internalInternalEObjectList;

    private URIList internalURIList = new URIList();
    protected List<URI> uriList = internalURIList;

    protected BasicEList<InternalEObject> internalEObjectList = new BasicEList<InternalEObject>();
    protected BasicEList<Object> dataValueList = new BasicEList<Object>();

    private final StringList segmentedStringsList;
    private final StringList segmentsList;
    private char[] builder;

    /**
     * @since 2.9
     */
    protected boolean isEagerProxyResolution;

    public EObjectInputStream(InputStream inputStream, Map<?, ?> options) throws IOException
    {
      this.inputStream = inputStream;
      this.options = options;

      int bufferCapacity = getInternalBufferCapacity(options);
      if (bufferCapacity > 1)
      {
       bytes = new byte[bufferCapacity];
      }
      isMarkSupported = inputStream.markSupported();

      if (options != null)
      {
        isEagerProxyResolution = Boolean.TRUE.equals(options.get(OPTION_EAGER_PROXY_RESOLUTION));
      }

      readSignature();
      readVersion();
      if (version.ordinal() > 0)
      {
        readStyle();
        if ((style & STYLE_DATA_CONVERTER) != 0)
        {
          segmentedStringsList = new StringList();
          segmentsList = new StringList();
          segmentsList.addAllUnique(INTRINSIC_STRINGS);
          builder = new char[200];
        }
        else
        {
          segmentedStringsList = null;
          segmentsList = null;
        }
      }
      else
      {
        style = STYLE_BINARY_FLOATING_POINT;
        segmentedStringsList = null;
        segmentsList = null;
      }
    }

    protected void readSignature() throws IOException
    {
      if (readByte() != (byte)'\211' ||
           readByte() != 'e' ||
           readByte() != 'm' ||
           readByte() != 'f' ||
           readByte() != '\n' ||
           readByte() != '\r' ||
           readByte() != '\032' ||
           readByte() != '\n')
      {
        throw new IOException("Invalid signature for a binary EMF serialization");
      }
    }

    protected void readVersion() throws IOException
    {
      version = Version.values()[readByte()];
    }

    protected void readStyle() throws IOException
    {
      style = readInt();
    }

    protected int[][] intDataArrayBuffer = new int[50][];
    protected int intDataArrayBufferCount = -1;

    protected int [] allocateIntArray(int length)
    {
      if (intDataArrayBufferCount == -1)
      {
        return new int[length];
      }
      else
      {
        int[] buffer = intDataArrayBuffer[intDataArrayBufferCount];
        intDataArrayBuffer[intDataArrayBufferCount--] = null;
        return buffer.length >= length ? buffer :  new int[length];
      }
    }

    protected void recycle(int[] values)
    {
      if (++intDataArrayBufferCount >= intDataArrayBuffer.length)
      {
        int [][] newIntDataArrayBuffer = new int[intDataArrayBufferCount * 2][];
        System.arraycopy(intDataArrayBuffer, 0, newIntDataArrayBuffer, 0, intDataArrayBufferCount);
        intDataArrayBuffer = newIntDataArrayBuffer;
      }
      intDataArrayBuffer[intDataArrayBufferCount] = values;
    }

    protected EPackageData readEPackage() throws IOException
    {
      int id = readCompressedInt();
      if (internalEPackageDataList.size() <= id)
      {
        EPackageData ePackageData = new EPackageData();
        String nsURI = readSegmentedString();
        URI uri = readURI();
        if (resourceSet != null)
        {
          ePackageData.ePackage = resourceSet.getPackageRegistry().getEPackage(nsURI);
          if (ePackageData.ePackage == null)
          {
            ePackageData.ePackage = (EPackage)resourceSet.getEObject(uri, true);
          }
        }
        else
        {
          ePackageData.ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
        }
        ePackageData.eClassData = new EClassData [ePackageData.ePackage.getEClassifiers().size()];
        internalEPackageDataList.add(ePackageData);
        return ePackageData;
      }
      else
      {
        return internalEPackageDataList.ePackageData[id];
      }
    }

    protected EClassData readEClass() throws IOException
    {
      // This is a fast pat for the case that there is an internal buffer with at least two bytes,
      // which most likely represent the ID of the package and the ID of the class in that package.
      //
      if (index + 2 < count)
      {
        // If the first byte represents the entire compressed integer ID of the package and there are that many packages already read...
        //
        int packageID = bytes[index];
        if (packageID <= 0x3F && packageID >= 0 && --packageID < internalEPackageDataList.size())
        {
          // If the second byte represents the entire compressed integer ID of the class...
          //
          int classID = bytes[index + 1];
          if (classID <= 0x3F && classID >= 0)
          {
            // Increment the index as if we'd read the bytes...
            //
            index += 2;

            // Initialize the class data, if it's not already initialized.
            //
            EPackageData ePackageData =  internalEPackageDataList.ePackageData[packageID];
            EClassData eClassData = ePackageData.eClassData[--classID];
            return eClassData == null ? initEClassData(ePackageData, classID) : eClassData;
          }
        }
      }

      // Just process the data as normal.
      //
      EPackageData ePackageData = readEPackage();
      int id = readCompressedInt();
      EClassData eClassData = ePackageData.eClassData[id];
      return eClassData == null ? initEClassData(ePackageData, id) : eClassData;
    }

    private EClassData initEClassData(EPackageData ePackageData, int id) throws IOException
    {
      EClassData eClassData = ePackageData.eClassData[id] = new EClassData();
      String name = readString();
      eClassData.eClass = (EClass)ePackageData.ePackage.getEClassifier(name);
      eClassData.eFactory = ePackageData.ePackage.getEFactoryInstance();
      eClassData.eStructuralFeatureData = new EStructuralFeatureData [eClassData.eClass.getFeatureCount()];
      return eClassData;
    }

    protected EStructuralFeatureData readEStructuralFeature() throws IOException
    {
      EClassData eClassData = readEClass();
      int featureID = readCompressedInt();
      return getEStructuralFeatureData(eClassData, featureID);
    }

    protected EStructuralFeatureData getEStructuralFeatureData(EClassData eClassData, int featureID) throws IOException
    {
      EStructuralFeatureData eStructuralFeatureData = eClassData.eStructuralFeatureData[featureID];
      if (eStructuralFeatureData == null)
      {
        eStructuralFeatureData = eClassData.eStructuralFeatureData[featureID] = new EStructuralFeatureData();
        String name = readString();
        eStructuralFeatureData.eStructuralFeature = eClassData.eClass.getEStructuralFeature(name);
        eStructuralFeatureData.featureID = eClassData.eClass.getFeatureID(eStructuralFeatureData.eStructuralFeature);
        eStructuralFeatureData.kind = FeatureKind.get(eStructuralFeatureData.eStructuralFeature);
        if (eStructuralFeatureData.eStructuralFeature instanceof EAttribute)
        {
          EAttribute eAttribute = (EAttribute)eStructuralFeatureData.eStructuralFeature;
          eStructuralFeatureData.eDataType = eAttribute.getEAttributeType();
          eStructuralFeatureData.eFactory = eStructuralFeatureData.eDataType.getEPackage().getEFactoryInstance();
          if (segmentedStringsList != null && readBoolean())
          {
            DataConverter<?> dataConverter = dataConverterMap.get(eStructuralFeatureData.eDataType);
            if (dataConverter == null)
            {
              final DataConverter<?> rawDataConverter = ((DataConverter.Factory)eStructuralFeatureData.eFactory).create(eStructuralFeatureData.eDataType);
              if (rawDataConverter.isTabulated())
              {
                DataConverter<?> tabulatedDataConverter =
                  new DataConverter<Object>()
                  {
                    final DataValueList objects = new DataValueList();

                    @Override
                    public Object read(EObjectInputStream eObjectInputStream) throws IOException
                    {
                      int id = eObjectInputStream.readCompressedInt();
                      if (id == -1)
                      {
                        return null;
                      }
                      else if (objects.size() <= id)
                      {
                        Object value = rawDataConverter.read(eObjectInputStream);
                        objects.add(value);
                        return value;
                      }
                      else
                      {
                        return objects.values[id];
                      }
                    }

                    @Override
                    protected void doWrite(EObjectOutputStream eObjectOutputStream, Object value) throws IOException
                    {
                      throw new UnsupportedOperationException();
                    }
                  };
                dataConverter = tabulatedDataConverter;
              }
              else
              {
                dataConverter = rawDataConverter;
              }
              dataConverterMap.put(eStructuralFeatureData.eDataType, dataConverter);
            }
            eStructuralFeatureData.dataConverter = dataConverter;
          }
        }
      }
      return eStructuralFeatureData;
    }

    public void loadResource(Resource resource) throws IOException
    {
      this.resource = resource;
      this.resourceSet = resource.getResourceSet();
      URI uri = resource.getURI();
      if (uri != null && uri.isHierarchical() && !uri.isRelative())
      {
        baseURI = uri;
      }
      int size = readCompressedInt();
      InternalEObject[] values = allocateInternalEObjectArray(size);
      for (int i = 0; i < size; ++i)
      {
        values[i] = loadEObject();
      }
      internalEObjectList.setData(size, values);
      @SuppressWarnings("unchecked")
      InternalEList<InternalEObject> internalEObjects = (InternalEList<InternalEObject>)(InternalEList<?>)resource.getContents();
      internalEObjects.addAllUnique(internalEObjectList);
      recycle(values);
    }

    public void loadEObjects(InternalEList<InternalEObject> internalEObjects) throws IOException
    {
      // Read all the values into an array.
      //
      int size = readCompressedInt();
      InternalEObject[] values = allocateInternalEObjectArray(size);
      for (int i = 0; i < size; ++i)
      {
        values[i] = loadEObject();
      }
      int existingSize = internalEObjects.size();

      // If the list is empty, we need to add all the objects,
      // otherwise, the reference is bidirectional and the list is at least partially populated.
      //
      if (existingSize == 0)
      {
        internalEObjectList.setData(size, values);
        internalEObjects.addAllUnique(0, internalEObjectList);
      }
      else
      {
        InternalEObject [] existingValues = allocateInternalEObjectArray(existingSize);
        internalEObjects.basicToArray(existingValues);
        int [] indices = allocateIntArray(existingSize);
        int duplicateCount = 0;
        LOOP:
        for (int i = 0; i < size; ++i)
        {
          InternalEObject internalEObject = values[i];
          for (int j = 0, count = 0; j < existingSize; ++j)
          {
            InternalEObject existingInternalEObject = existingValues[j];
            if (existingInternalEObject == internalEObject)
            {
              if (duplicateCount != count)
              {
                internalEObjects.move(duplicateCount, count);
              }
              indices[duplicateCount] = i;
              ++count;
              ++duplicateCount;
              existingValues[j] = null;
              continue LOOP;
            }
            else if (existingInternalEObject != null)
            {
              ++count;
            }
          }

          values[i - duplicateCount] = internalEObject;
        }

        size -= existingSize;
        internalEObjectList.setData(size, values);
        internalEObjects.addAllUnique(0, internalEObjectList);
        for (int i = 0; i < existingSize; ++i)
        {
          int newPosition = indices[i];
          int oldPosition = size + i;
          if (newPosition != oldPosition)
          {
            internalEObjects.move(newPosition, oldPosition);
          }
        }
        recycle(existingValues);
        recycle(indices);
      }
      recycle(values);
    }

    public void loadFeatureMap(FeatureMap.Internal featureMap) throws IOException
    {
      // Read all the values into an array.
      //
      int size = readCompressedInt();
      FeatureMap.Entry.Internal[] values = allocateFeatureMapEntryArray(size);
      for (int i = 0; i < size; ++i)
      {
        values[i] = loadFeatureMapEntry();
      }
      int existingSize = featureMap.size();

      // If the list is empty, we need to add all the objects,
      // otherwise, the reference is bidirectional and the list is at least partially populated.
      //
      if (existingSize == 0)
      {
        featureMap.addAllUnique(values, 0, size);
      }
      else
      {
        FeatureMap.Entry.Internal [] existingValues = allocateFeatureMapEntryArray(existingSize);
        featureMap.basicToArray(existingValues);
        int [] indices = allocateIntArray(existingSize);
        int duplicateCount = 0;
        LOOP:
        for (int i = 0; i < size; ++i)
        {
          FeatureMap.Entry.Internal entry = values[i];
          for (int j = 0, count = 0; j < existingSize; ++j)
          {
            FeatureMap.Entry.Internal existingEntry = existingValues[j];
            if (entry.equals(existingEntry))
            {
              if (duplicateCount != count)
              {
                featureMap.move(duplicateCount, count);
              }
              indices[duplicateCount] = i;
              ++count;
              ++duplicateCount;
              existingValues[j] = null;
              continue LOOP;
            }
            else if (existingEntry != null)
            {
              ++count;
            }
          }

          values[i - duplicateCount] = entry;
        }

        size -= existingSize;
        internalEObjectList.setData(size, values);
        featureMap.addAllUnique(0, values, 0, size);
        for (int i = 0; i < existingSize; ++i)
        {
          int newPosition = indices[i];
          int oldPosition = size + i;
          if (newPosition != oldPosition)
          {
            featureMap.move(newPosition, oldPosition);
          }
        }
        recycle(existingValues);
        recycle(indices);
      }
      recycle(values);
    }

    public FeatureMap.Entry.Internal loadFeatureMapEntry() throws IOException
    {
      EStructuralFeatureData eStructuralFeatureData = readEStructuralFeature();
      Object value;
      switch (eStructuralFeatureData.kind)
      {
        case EOBJECT_CONTAINER:
        case EOBJECT_CONTAINER_PROXY_RESOLVING:
        case EOBJECT:
        case EOBJECT_LIST:
        case EOBJECT_PROXY_RESOLVING:
        case EOBJECT_LIST_PROXY_RESOLVING:
        case EOBJECT_CONTAINMENT:
        case EOBJECT_CONTAINMENT_LIST:
        case EOBJECT_CONTAINMENT_PROXY_RESOLVING:
        case EOBJECT_CONTAINMENT_LIST_PROXY_RESOLVING:
        {
          value = loadEObject();
          break;
        }
        case STRING:
        {
          value = readSegmentedString();
          break;
        }
        case DATE:
        {
          if (eStructuralFeatureData.dataConverter != null)
          {
            value = eStructuralFeatureData.dataConverter.read(this);
          }
          else if ((style & STYLE_BINARY_DATE) != 0)
          {
            value = readDate();
          }
          else
          {
            value = eStructuralFeatureData.eFactory.createFromString(eStructuralFeatureData.eDataType, readSegmentedString());
          }
          break;
        }
        case ENUMERATOR:
        {
          if (eStructuralFeatureData.dataConverter != null)
          {
            value = eStructuralFeatureData.dataConverter.read(this);
          }
          else if ((style & STYLE_BINARY_ENUMERATOR) != 0)
          {
            value = ((EEnum)eStructuralFeatureData.eDataType).getEEnumLiteral(readInt()).getInstance();
          }
          else
          {
            value = eStructuralFeatureData.eFactory.createFromString(eStructuralFeatureData.eDataType, readString());
          }
          break;
        }
        case DATA:
        case DATA_LIST:
        {
          value =
            eStructuralFeatureData.dataConverter != null ?
              eStructuralFeatureData.dataConverter.read(this) :
              eStructuralFeatureData.eFactory.createFromString(eStructuralFeatureData.eDataType, readSegmentedString());
          break;
        }
        case BOOLEAN:
        {
          value = readBoolean();
          break;
        }
        case BYTE:
        {
          value = readByte();
          break;
        }
        case CHAR:
        {
          value = readChar();
          break;
        }
        case DOUBLE:
        {
          value = readDouble();
          break;
        }
        case FLOAT:
        {
          value = readFloat();
          break;
        }
        case INT:
        {
          value = readInt();
          break;
        }
        case LONG:
        {
          value = readLong();
          break;
        }
        case SHORT:
        {
          value = readShort();
          break;
        }
        default:
        {
          throw new IOException("Unhandled case " + eStructuralFeatureData.kind);
        }
      }
      return FeatureMapUtil.createRawEntry(eStructuralFeatureData.eStructuralFeature, value);
    }

    public InternalEObject loadEObject() throws IOException
    {
      int id = readCompressedInt();
      if (id == -1)
      {
        return null;
      }
      else
      {
        if (internalInternalEObjectList.size() <= id)
        {
          EClassData eClassData = readEClass();
          InternalEObject internalEObject =  (InternalEObject)eClassData.eFactory.create(eClassData.eClass);
          InternalEObject result = internalEObject;

          // Check if we have a "feature" representing the proxy URI...
          //
          int featureID = readCompressedInt() - 1;
          if (featureID == -2)
          {
            internalEObject.eSetProxyURI(readURI());
            if (isEagerProxyResolution)
            {
              result = (InternalEObject)EcoreUtil.resolve(internalEObject, resource);
              internalInternalEObjectList.add(result);
              if ((style & STYLE_PROXY_ATTRIBUTES) == 0)
              {
                return result;
              }
            }
            else
            {
              internalInternalEObjectList.add(internalEObject);
              if ((style & STYLE_PROXY_ATTRIBUTES) == 0)
              {
                return internalEObject;
              }
            }

            // We must process the proxy attributes even for the case of eager proxy resolution when we will immediately discard the proxy.
            //
            featureID = readCompressedInt() - 1;
          }
          else
          {
            internalInternalEObjectList.add(internalEObject);
          }

          for (; featureID != -1; featureID = readCompressedInt() - 1)
          {
            EStructuralFeatureData eStructuralFeatureData = getEStructuralFeatureData(eClassData, featureID);
            loadFeatureValue(internalEObject, eStructuralFeatureData);
          }

          return result;
        }
        else
        {
          return internalInternalEObjectList.eObjects[id];
        }
      }
    }

    protected void loadFeatureValue(InternalEObject internalEObject, EStructuralFeatureData eStructuralFeatureData) throws IOException
    {
      switch (eStructuralFeatureData.kind)
      {
        case EOBJECT_CONTAINER:
        case EOBJECT_CONTAINER_PROXY_RESOLVING:
        case EOBJECT:
        case EOBJECT_PROXY_RESOLVING:
        case EOBJECT_CONTAINMENT:
        case EOBJECT_CONTAINMENT_PROXY_RESOLVING:
        {
          internalEObject.eSet(eStructuralFeatureData.featureID, loadEObject());
          break;
        }
        case EOBJECT_LIST:
        case EOBJECT_LIST_PROXY_RESOLVING:
        case EOBJECT_CONTAINMENT_LIST:
        case EOBJECT_CONTAINMENT_LIST_PROXY_RESOLVING:
        {
          @SuppressWarnings("unchecked")
          InternalEList<InternalEObject> internalEList = (InternalEList<InternalEObject>)internalEObject.eGet(eStructuralFeatureData.featureID, false, true);
          loadEObjects(internalEList);
          break;
        }
        case STRING:
        {
          internalEObject.eSet(eStructuralFeatureData.featureID, readSegmentedString());
          break;
        }
        case FEATURE_MAP:
        {
          FeatureMap.Internal featureMap = (FeatureMap.Internal)internalEObject.eGet(eStructuralFeatureData.featureID, false, true);
          loadFeatureMap(featureMap);
          break;
        }
        case DATE:
        {
          if (eStructuralFeatureData.dataConverter != null)
          {
            internalEObject.eSet(eStructuralFeatureData.featureID,  eStructuralFeatureData.dataConverter.read(this));
          }
          else if ((style & STYLE_BINARY_DATE) != 0)
          {
            internalEObject.eSet(eStructuralFeatureData.featureID, readDate());
          }
          else
          {
            internalEObject.eSet(eStructuralFeatureData.featureID, eStructuralFeatureData.eFactory.createFromString(eStructuralFeatureData.eDataType, readSegmentedString()));
          }
          break;
        }
        case ENUMERATOR:
        {
          if (eStructuralFeatureData.dataConverter != null)
          {
            internalEObject.eSet(eStructuralFeatureData.featureID,  eStructuralFeatureData.dataConverter.read(this));
          }
          else if ((style & STYLE_BINARY_ENUMERATOR) != 0)
          {
            internalEObject.eSet(eStructuralFeatureData.featureID, ((EEnum)eStructuralFeatureData.eDataType).getEEnumLiteral(readInt()).getInstance());
          }
          else
          {
            internalEObject.eSet(eStructuralFeatureData.featureID, eStructuralFeatureData.eFactory.createFromString(eStructuralFeatureData.eDataType, readString()));
          }
          break;
        }
        case DATA:
        {
          Object value =
            eStructuralFeatureData.dataConverter != null ?
              eStructuralFeatureData.dataConverter.read(this) :
              eStructuralFeatureData.eFactory.createFromString(eStructuralFeatureData.eDataType, readSegmentedString());
          internalEObject.eSet(eStructuralFeatureData.featureID, value);
          break;
        }
        case DATA_LIST:
        {
          int size = readCompressedInt();
          dataValueList.grow(size);
          Object[] dataValues = dataValueList.data();
          if (eStructuralFeatureData.dataConverter != null)
          {
            for (int i = 0; i < size; ++i)
            {
              dataValues[i] = eStructuralFeatureData.dataConverter.read(this);
            }
          }
          else
          {
            for (int i = 0; i < size; ++i)
            {
              String literal = readSegmentedString();
              dataValues[i] = eStructuralFeatureData.eFactory.createFromString(eStructuralFeatureData.eDataType, literal);
            }
          }
          dataValueList.setData(size, dataValues);
          @SuppressWarnings("unchecked")
          InternalEList<Object> values = (InternalEList<Object>)internalEObject.eGet(eStructuralFeatureData.featureID, false, true);
          values.addAllUnique(dataValueList);
          break;
        }
        case BOOLEAN:
        {
          internalEObject.eSet(eStructuralFeatureData.featureID, readBoolean());
          break;
        }
        case BYTE:
        {
          internalEObject.eSet(eStructuralFeatureData.featureID, readByte());
          break;
        }
        case CHAR:
        {
          internalEObject.eSet(eStructuralFeatureData.featureID, readChar());
          break;
        }
        case DOUBLE:
        {
          internalEObject.eSet(eStructuralFeatureData.featureID, readDouble());
          break;
        }
        case FLOAT:
        {
          internalEObject.eSet(eStructuralFeatureData.featureID, readFloat());
          break;
        }
        case INT:
        {
          internalEObject.eSet(eStructuralFeatureData.featureID, readInt());
          break;
        }
        case LONG:
        {
          internalEObject.eSet(eStructuralFeatureData.featureID, readLong());
          break;
        }
        case SHORT:
        {
          internalEObject.eSet(eStructuralFeatureData.featureID, readShort());
          break;
        }
        default:
        {
          throw new IOException("Unhandled case " + eStructuralFeatureData.kind);
        }
      }
    }

    public byte readByte() throws IOException
    {
      return index < count ? bytes[index++] : fill();
    }

    private byte fill() throws IOException
    {
      if (bytes == null)
      {
        int result = inputStream.read();
        if (result == -1)
        {
          throw new IOException("Unexpected end of stream");
        }
        return (byte)result;
      }
      else
      {
        if (isMarkSupported)
        {
          inputStream.mark(bytes.length);
        }
        count = inputStream.read(bytes, 0, bytes.length);
        if (count == -1)
        {
          throw new IOException("Unexpected end of stream");
        }
        index = 1;
        return bytes[0];
      }
    }

    /**
     * @since 2.9
     */
    public void flush() throws IOException
    {
      if (bytes != null && index < count)
      {
        if (isMarkSupported)
        {
          // Put back all the bytes buffered since the last call to fill.
          //
          inputStream.reset();

          // Read back out again all the bytes that were consumed by readByte.
          //
          inputStream.read(bytes, 0, index);
        }
        else
        {
          throw new IOException("Unable to place " + index + "unconsumed bytes back into the input stream; specify an OPTION_BUFFER_CAPACITY to support this");
        }
      }
    }

    public boolean readBoolean() throws IOException
    {
      return readByte() != 0;
    }

    public char readChar() throws IOException
    {
      return (char)((readByte() << 8) & 0xFF00 | readByte() & 0xFF);
    }

    public short readShort() throws IOException
    {
      return (short)((readByte() << 8) & 0xFF00 | readByte() & 0xFF);
    }

    public int readInt() throws IOException
    {
      return (readByte() << 24) | (readByte() << 16) & 0xFF0000 | (readByte() << 8) & 0xFF00 | readByte() & 0xFF;
    }

    public long readLong() throws IOException
    {
      return (long)readInt() << 32 | readInt() & 0xFFFFFFFFL;
    }

    public float readFloat() throws IOException
    {
      if ((style & STYLE_BINARY_FLOATING_POINT) != 0)
      {
        return Float.intBitsToFloat(readInt());
      }
      else
      {
        return Float.parseFloat(readString());
      }
    }

    public double readDouble() throws IOException
    {
      if ((style & STYLE_BINARY_FLOATING_POINT) != 0)
      {
        return Double.longBitsToDouble(readLong());
      }
      else
      {
        return Double.parseDouble(readString());
      }
    }

    public int readCompressedInt() throws IOException
    {
      // If the internal buffer holds all the bytes we could potentially need, i.e., all 4 bytes...
      //
      if (index + 4 < count)
      {
        // Do all the processing directly from the buffered bytes rather than by calling readByte.
        //
        int initialByte = bytes[index++];
        int code = (initialByte >> 6) & 0x3;
        switch (code)
        {
          case 0:
          {
            return initialByte - 1;
          }
          case 1:
          {
            return (initialByte << 8 & 0x3F00 | bytes[index++] & 0xFF) - 1;
          }
          case 2:
          {
            return ((initialByte << 16) & 0x3F0000 | (bytes[index++] << 8) & 0xFF00 | bytes[index++] & 0xFF) - 1;
          }
          default:
          {
            return ((initialByte << 24) & 0x3F000000 | (bytes[index++] << 16) & 0xFF0000 | (bytes[index++] << 8) & 0xFF00 | bytes[index++] & 0xFF) - 1;
          }
        }
      }
      else
      {
        // Do the processing by reading each individual byte as needed.
        //
        int initialByte = readByte();
        int code = (initialByte >> 6) & 0x3;
        switch (code)
        {
          case 0:
          {
            return initialByte - 1;
          }
          case 1:
          {
            return (initialByte << 8 & 0x3F00 | readByte() & 0xFF) - 1;
          }
          case 2:
          {
            return ((initialByte << 16) & 0x3F0000 | (readByte() << 8) & 0xFF00 | readByte() & 0xFF) - 1;
          }
          default:
          {
            return ((initialByte << 24) & 0x3F000000 | (readByte() << 16) & 0xFF0000 | (readByte() << 8) & 0xFF00 | readByte() & 0xFF) - 1;
          }
        }
      }
    }

    /**
     * @since 2.9
     */
    public String readSegmentedString() throws IOException
    {
      if (segmentedStringsList == null)
      {
        return basicReadString();
      }
      else
      {
        int id = readCompressedInt();
        if (id == -1)
        {
          return null;
        }
        else if (segmentedStringsList.size() <= id)
        {
          int segmentCount = readCompressedInt();
          String value;
          if (segmentCount == 0)
          {
            value = readString();
          }
          else
          {
            int length = 0;
            for (int i = 0; i < segmentCount; ++i)
            {
              String segment = readString();

              int segmentLength = segment.length();
              int newLength = length + segmentLength;
              if (builder.length < newLength)
              {
                char[] newBuilder = new char [Math.max(2 * builder.length, newLength)];
                System.arraycopy(builder, 0, newBuilder, 0, builder.length);
                builder = newBuilder;
              }

              if (segmentLength == 1)
              {
                builder[length] = segment.charAt(0);
              }
              else
              {
                segment.getChars(0, segmentLength, builder, length);
              }

              length = newLength;
            }
            value = new String(builder, 0, length);
          }
          segmentedStringsList.add(value);
          return value;
        }
        else
        {
          return segmentedStringsList.strings[id];
        }
      }
    }

    public String readString() throws IOException
    {
      if (segmentsList != null)
      {
        int id = readCompressedInt();
        if (id == -1)
        {
          return null;
        }
        else if (segmentsList.size() <= id)
        {
          String value = basicReadString();
          segmentsList.add(value);
          return value;
        }
        else
        {
          return segmentsList.strings[id];
        }
      }
      else
      {
        return basicReadString();
      }
    }

    private String basicReadString() throws IOException
    {
      int length = readCompressedInt();
      if (length == -1)
      {
        return null;
      }
      else
      {
        if (characters == null || characters.length < length)
        {
          characters = new char[length];
        }
        LOOP:
        for (int i = 0; i < length; ++i)
        {
          byte value = readByte();
          if (value == 0)
          {
            do
            {
              characters[i] = readChar();
            }
            while (++i < length);
            break LOOP;
          }
          else
          {
            characters[i] = (char)(value & 0xFF);
          }
        }
        return new String(characters, 0, length);
      }
    }

    public Date readDate() throws IOException
    {
      long time = readLong();
      return new Date(time);
    }

    public URI readURI() throws IOException
    {
      int id = readCompressedInt();
      if (id == -1)
      {
        return null;
      }
      else
      {
        URI uri;
        if (internalURIList.size() <= id)
        {
          String value = readSegmentedString();
          uri = resolve(URI.createURI(value));
          internalURIList.add(uri);
        }
        else
        {
          uri = internalURIList.uris[id];
        }
        String fragment = readSegmentedString();
        if (fragment != null)
        {
          uri = uri.appendFragment(fragment);
        }
        return uri;
      }
    }
  }
}
