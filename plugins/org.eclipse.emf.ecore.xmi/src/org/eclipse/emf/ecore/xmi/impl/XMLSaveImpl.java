/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: XMLSaveImpl.java,v 1.19 2004/10/20 14:31:01 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.xmi.DanglingHREFException;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.emf.ecore.xml.type.internal.DataValue.XMLChar;


/**
 * This implements the XML serializer, possibly using an XMLMap
 * if one is provided as a save option.
 */
public class XMLSaveImpl implements XMLSave
{
  protected XMLHelper helper;
  protected XMLString doc;
  protected boolean declareXSI;
  protected boolean useEncodedAttributeStyle;
  protected boolean declareXML;
  protected boolean saveTypeInfo;
  protected Escape escape;
  protected Escape escapeURI;
  protected Lookup featureTable;
  protected String encoding;
  protected String idAttributeName = "id";
  protected String processDanglingHREF;
  protected boolean declareSchemaLocation;
  protected boolean declareSchemaLocationImplementation;
  protected XMLResource.XMLMap map;
  protected ExtendedMetaData extendedMetaData;
  protected EClass anySimpleType;
  protected EClass anyType;
  protected Map eObjectToExtensionMap;
  protected EPackage xmlSchemaTypePackage = XMLTypePackage.eINSTANCE;
  protected int flushThreshold = Integer.MAX_VALUE;

  protected static final int SKIP = 0;
  protected static final int SAME_DOC = 1;
  protected static final int CROSS_DOC = 2;

  protected static final int TRANSIENT                              = 0;
  protected static final int DATATYPE_SINGLE                        = 1;
  protected static final int DATATYPE_ELEMENT_SINGLE                = 2;
  protected static final int DATATYPE_CONTENT_SINGLE                = 3;
  protected static final int DATATYPE_SINGLE_NILLABLE               = 4;
  protected static final int DATATYPE_MANY                          = 5;
  protected static final int OBJECT_CONTAIN_SINGLE                  = 6;
  protected static final int OBJECT_CONTAIN_MANY                    = 7;
  protected static final int OBJECT_HREF_SINGLE                     = 8;
  protected static final int OBJECT_HREF_MANY                       = 9;
  protected static final int OBJECT_CONTAIN_SINGLE_UNSETTABLE       = 10;
  protected static final int OBJECT_CONTAIN_MANY_UNSETTABLE         = 11;
  protected static final int OBJECT_HREF_SINGLE_UNSETTABLE          = 12;
  protected static final int OBJECT_HREF_MANY_UNSETTABLE            = 13;
  protected static final int OBJECT_ELEMENT_SINGLE                  = 14;
  protected static final int OBJECT_ELEMENT_SINGLE_UNSETTABLE       = 15;
  protected static final int OBJECT_ELEMENT_MANY                    = 16;
  protected static final int OBJECT_ELEMENT_IDREF_SINGLE            = 17;
  protected static final int OBJECT_ELEMENT_IDREF_SINGLE_UNSETTABLE = 18;
  protected static final int OBJECT_ELEMENT_IDREF_MANY              = 19;
  protected static final int ATTRIBUTE_FEATURE_MAP                  = 20;
  protected static final int ELEMENT_FEATURE_MAP                    = 21;

  protected static final String XML_VERSION = "1.0";

  protected static final String XSI_NIL             = XMLResource.XSI_NS+":"+XMLResource.NIL;             // xsi:nil
  protected static final String XSI_TYPE_NS         = XMLResource.XSI_NS+":"+XMLResource.TYPE;            // xsi:type
  protected static final String XSI_XMLNS           = XMLResource.XML_NS+":"+XMLResource.XSI_NS;          // xmlns:xsi
  protected static final String XSI_SCHEMA_LOCATION = XMLResource.XSI_NS+":"+XMLResource.SCHEMA_LOCATION; // xsi:schemaLocation
  protected static final String XSI_NO_NAMESPACE_SCHEMA_LOCATION = XMLResource.XSI_NS+":"+XMLResource.NO_NAMESPACE_SCHEMA_LOCATION; // xsi:noNamespaceSchemaLocation

  protected static final int EMPTY_ELEMENT = 1;
  protected static final int CONTENT_ELEMENT = 2;

  public XMLSaveImpl(XMLHelper helper)
  {
    this.helper = helper;
  }

  /**
   * Constructor for XMLSave.
   * @param options
   * @param helper
   * @param encoding
   */
  public XMLSaveImpl(Map options, XMLHelper helper, String encoding)
  {
    this.helper = helper;
    init(helper.getResource(), options);
    this.encoding = encoding;
  }

  public void save(XMLResource resource, OutputStream outputStream, Map options) throws IOException
  {
    init(resource, options);
    List contents = resource.getContents();
    traverse(contents);

    if (encoding.equals("US-ASCII") || encoding.equals("ASCII"))
    {
      writeAscii(outputStream);
    }
    else
    {
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, helper.getJavaEncoding(encoding));
      write(outputStreamWriter);
    }

    if (extendedMetaData != null && contents.size() >= 1)
    {
      EObject root = (EObject)contents.get(0);
      EClass eClass = root.eClass();

      EReference xmlnsPrefixMapFeature = extendedMetaData.getXMLNSPrefixMapFeature(eClass);
      if (xmlnsPrefixMapFeature != null)
      {
        EMap xmlnsPrefixMap = (EMap)root.eGet(xmlnsPrefixMapFeature);
        for (Iterator i = helper.getPrefixToNamespaceMap().iterator(); i.hasNext(); )
        {
          Map.Entry entry = (Map.Entry)i.next();
          Object key = entry.getKey();
          Object value = entry.getValue();
          Object currentValue = xmlnsPrefixMap.get(key);
          if (currentValue == null ? value != null : !currentValue.equals(value))
          {
            xmlnsPrefixMap.put(key, value);
          }
        }
      }
    }

    featureTable = null;
    doc = null;

    if (processDanglingHREF == null ||
        XMLResource.OPTION_PROCESS_DANGLING_HREF_THROW.equals(processDanglingHREF))
    {
      DanglingHREFException exception = helper.getDanglingHREFException();

      if (exception != null)
      {
        helper = null;
        throw new Resource.IOWrappedException(exception);
      }
    }

    helper = null;
  }

  protected void init(XMLResource resource, Map options)
  {
    declareXSI = false;
    useEncodedAttributeStyle = Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE));
    declareXML = !Boolean.FALSE.equals(options.get(XMLResource.OPTION_DECLARE_XML));
    declareSchemaLocationImplementation = Boolean.TRUE.equals(options.get(XMLResource.OPTION_SCHEMA_LOCATION_IMPLEMENTATION));
    declareSchemaLocation = declareSchemaLocationImplementation || Boolean.TRUE.equals(options.get(XMLResource.OPTION_SCHEMA_LOCATION));
    Integer lineWidth = (Integer)options.get(XMLResource.OPTION_LINE_WIDTH);
    saveTypeInfo = Boolean.TRUE.equals(options.get(XMLResource.OPTION_SAVE_TYPE_INFORMATION));
        
    anyType = (EClass)options.get(XMLResource.OPTION_ANY_TYPE);
    anySimpleType = (EClass)options.get(XMLResource.OPTION_ANY_SIMPLE_TYPE);
    if (anyType == null)
    {
      anyType = XMLTypePackage.eINSTANCE.getAnyType();
      anySimpleType = XMLTypePackage.eINSTANCE.getSimpleAnyType();
    }
    
    if (options.get(XMLResource.OPTION_FLUSH_THRESHOLD) instanceof Integer)
    {
      flushThreshold  = ((Integer)options.get(XMLResource.OPTION_FLUSH_THRESHOLD)).intValue();
    }

    String temporaryFileName =  null;
    if (Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_FILE_BUFFER)))
    {
      try
      {
        temporaryFileName = File.createTempFile("XMLSave", null).getPath();
      }
      catch (IOException exception)
      {
        // If we can't create a temp file then we have to ignore the option.
      }
    }
    int effectiveLineWidth = lineWidth == null ? Integer.MAX_VALUE : lineWidth.intValue();
    if (Boolean.TRUE.equals(options.get(XMLResource.OPTION_SAVE_DOCTYPE)))
    {
      if (resource != null)
      {
        doc = new XMLString(effectiveLineWidth, resource.getPublicId(), resource.getSystemId(), temporaryFileName);
      }
      else
      {
        doc = new XMLString (effectiveLineWidth, null, null, temporaryFileName);
      }
    }
    else 
    {
      doc = new XMLString(effectiveLineWidth, temporaryFileName);
    }

    if (Boolean.FALSE.equals(options.get(XMLResource.OPTION_FORMATTED)))
    {
      doc.setUnformatted(true);
    }
    escape =
      Boolean.TRUE.equals(options.get(XMLResource.OPTION_SKIP_ESCAPE)) ?
        null :
        new Escape();
    
    escapeURI = Boolean.FALSE.equals(options.get(XMLResource.OPTION_SKIP_ESCAPE_URI)) ?
        escape :
        null;

    if (options.containsKey(XMLResource.OPTION_ENCODING))
    {
      encoding = (String)options.get(XMLResource.OPTION_ENCODING);
    }
    else if (resource != null)
    {
      encoding = resource.getEncoding();
    }

    processDanglingHREF = (String) options.get(XMLResource.OPTION_PROCESS_DANGLING_HREF);
    helper.setProcessDanglingHREF(processDanglingHREF);

    map = (XMLResource.XMLMap) options.get(XMLResource.OPTION_XML_MAP);
    if (map != null)
    {
      helper.setXMLMap(map);

      if (map.getIDAttributeName() != null)
      {
        idAttributeName = map.getIDAttributeName();
      }
    }

    Object extendedMetaDataOption = options.get(XMLResource.OPTION_EXTENDED_META_DATA);
    if (extendedMetaDataOption instanceof Boolean)
    {
      if (extendedMetaDataOption.equals(Boolean.TRUE))
      {
        extendedMetaData =
          resource == null || resource.getResourceSet() == null ?
            ExtendedMetaData.INSTANCE :
            new BasicExtendedMetaData(resource.getResourceSet().getPackageRegistry());
      }
    }
    else
    {
      extendedMetaData = (ExtendedMetaData)options.get(XMLResource.OPTION_EXTENDED_META_DATA);
    }

    if (resource != null)
    {
      eObjectToExtensionMap = resource.getEObjectToExtensionMap();
      if (eObjectToExtensionMap.isEmpty())
      {
        eObjectToExtensionMap = null;
      }
      else if (extendedMetaData == null)
      {
        extendedMetaData =
          resource.getResourceSet() == null ?
            ExtendedMetaData.INSTANCE :
            new BasicExtendedMetaData(resource.getResourceSet().getPackageRegistry());
      }
    }

    if (extendedMetaData != null)
    {
      helper.setExtendedMetaData(extendedMetaData);
      if (resource != null && resource.getContents().size() >=1)
      {
        EObject root = (EObject)resource.getContents().get(0);
        EClass eClass = root.eClass();

        EReference xmlnsPrefixMapFeature = extendedMetaData.getXMLNSPrefixMapFeature(eClass);
        if (xmlnsPrefixMapFeature != null)
        {
          EMap xmlnsPrefixMap = (EMap)root.eGet(xmlnsPrefixMapFeature);
          helper.setPrefixToNamespaceMap(xmlnsPrefixMap);
        }
      }
    }

    featureTable = new Lookup(map, extendedMetaData);
  }

  public void traverse(List contents)
  {
    if (declareXML)
    {
      doc.add("<?xml version=\"" + XML_VERSION + "\" encoding=\"" + encoding + "\"?>");
      doc.addLine();
    }

    int size = contents.size();

    // Reserve a place to insert xmlns declarations after we know what they all are.
    //
    Object mark;

    if (size == 1)
    {
      mark = writeTopObject((EObject) contents.get(0));
    }
    else
    {
      mark = writeTopObjects(contents);
    }

    // Go back and add all the XMLNS stuff.
    //
    doc.resetToMark(mark);
    addNamespaceDeclarations();
  }

  protected Object writeTopObject(EObject top)
  {
    EClass eClass = top.eClass();
    if (extendedMetaData == null || extendedMetaData.getDocumentRoot(eClass.getEPackage()) != eClass)
    {
      String name = helper.getQName(eClass);
      doc.startElement(name);
      Object mark = doc.mark();
      saveElementID(top);
      return mark;
    }
    else
    {
      doc.startElement(null);
      saveFeatures(top);
      return null;
    }
  }

  protected Object writeTopObjects(List contents)
  {
    return writeTopObject((EObject)contents.get(0));
  }

  protected void addNamespaceDeclarations()
  {
    EPackage noNamespacePackage = helper.getNoNamespacePackage();
    EPackage[] packages = helper.packages();
    StringBuffer xsiSchemaLocation = null;
    String xsiNoNamespaceSchemaLocation = null;
    if (declareSchemaLocation)
    {
      Map handledBySchemaLocationMap = Collections.EMPTY_MAP;

      if (extendedMetaData != null)
      {
        Resource resource = helper.getResource();
        if (resource != null && resource.getContents().size() >= 1)
        {
          EObject root = (EObject)resource.getContents().get(0);
          EClass eClass = root.eClass();

          EReference xsiSchemaLocationMapFeature = extendedMetaData.getXSISchemaLocationMapFeature(eClass);
          if (xsiSchemaLocationMapFeature != null)
          {
            EMap xsiSchemaLocationMap = (EMap)root.eGet(xsiSchemaLocationMapFeature);
            if (!xsiSchemaLocationMap.isEmpty())
            {
              handledBySchemaLocationMap = xsiSchemaLocationMap.map();
              declareXSI = true;
              for (Iterator i = xsiSchemaLocationMap.entrySet().iterator(); i.hasNext(); )
              {
                Map.Entry entry = (Map.Entry)i.next();
                String namespace = (String)entry.getKey();
                URI location = URI.createURI(entry.getValue().toString());
                if (namespace == null)
                {
                  xsiNoNamespaceSchemaLocation = helper.deresolve(location).toString();
                }
                else
                {
                  if (xsiSchemaLocation == null)
                  {
                    xsiSchemaLocation = new StringBuffer();
                  }
                  else
                  {
                    xsiSchemaLocation.append(' ');
                  }
                  xsiSchemaLocation.append(namespace);
                  xsiSchemaLocation.append(' ');
                  xsiSchemaLocation.append(helper.deresolve(location).toString());
                }
              }
            }
          }
        }
      }

      for (int i = 0; i < packages.length; i++)
      {
        EPackage ePackage = packages[i];

        String javaImplementationLocation = null;
        if (declareSchemaLocationImplementation)
        {
          // First try to see if this package's implementation class has an eInstance.
          //
          try
          {
            Field field = ePackage.getClass().getField("eINSTANCE");
            javaImplementationLocation = "java://" + field.getDeclaringClass().getName();
          }
          catch (Exception exception)
          {
          }
        }

        if (noNamespacePackage == ePackage)
        {
          if (ePackage.eResource() != null && !handledBySchemaLocationMap.containsKey(null))
          {
            declareXSI = true;
            if (javaImplementationLocation != null)
            {
              xsiNoNamespaceSchemaLocation = javaImplementationLocation;
            }
            else
            {
              xsiNoNamespaceSchemaLocation = helper.getHREF(ePackage);
              if (xsiNoNamespaceSchemaLocation.endsWith("#/"))
              {
                xsiNoNamespaceSchemaLocation = xsiNoNamespaceSchemaLocation.substring(0, xsiNoNamespaceSchemaLocation.length() - 2);
              }
            }
          }
        }
        else
        {
          Resource resource = ePackage.eResource();
          if (resource != null)
          {
            String nsURI = ePackage.getNsURI();
            if (!handledBySchemaLocationMap.containsKey(nsURI))
            {
              URI uri = resource.getURI();
              if (javaImplementationLocation != null || (uri == null ? nsURI != null : !uri.toString().equals(nsURI)))
              {
                declareXSI = true;
                if (xsiSchemaLocation == null)
                {
                  xsiSchemaLocation = new StringBuffer();
                }
                else
                {
                  xsiSchemaLocation.append(' ');
                }
                xsiSchemaLocation.append(nsURI);
                xsiSchemaLocation.append(' ');
                String location = javaImplementationLocation == null ? helper.getHREF(ePackage) : javaImplementationLocation;
                if (location.endsWith("#/"))
                {
                  location = location.substring(0, location.length() - 2);
                }
                xsiSchemaLocation.append(location);
              }
            }
          }
        }
      }
    }

    if (declareXSI)
    {
      doc.addAttribute(XSI_XMLNS, XMLResource.XSI_URI);
    }

    for (int i = 0; i < packages.length; i++)
    {
      EPackage ePackage = packages[i];
      if (ePackage != noNamespacePackage && ePackage != XMLNamespacePackage.eINSTANCE)
      {
        String nsURI = extendedMetaData == null ? ePackage.getNsURI() : extendedMetaData.getNamespace(ePackage);
        if (ePackage == xmlSchemaTypePackage)
        {
          nsURI = XMLResource.XML_SCHEMA_URI;
        }
        if (nsURI != null && !isDuplicateURI(nsURI))
        {
          List nsPrefixes = helper.getPrefixes(ePackage);
          for (Iterator j = nsPrefixes.iterator(); j.hasNext(); )
          {
            String nsPrefix = (String)j.next();
            if (nsPrefix != null && nsPrefix.length() > 0)
            {
              doc.addAttributeNS(XMLResource.XML_NS, nsPrefix, nsURI);
            }
            else
            {
              doc.addAttribute(XMLResource.XML_NS, nsURI);
            }
          }
        }
      }
    }

    if (xsiSchemaLocation != null)
    {
      doc.addAttribute(XSI_SCHEMA_LOCATION, xsiSchemaLocation.toString());
    }

    if (xsiNoNamespaceSchemaLocation != null)
    {
      doc.addAttribute(XSI_NO_NAMESPACE_SCHEMA_LOCATION, xsiNoNamespaceSchemaLocation);
    }
  }
  
  public boolean isDuplicateURI(String nsURI)
  {
    return false;
  }

  public void write(OutputStreamWriter os) throws IOException
  {
    int count = 0;
    final int BUFFER_SIZE = 8192;
    char[] buffer = new char[BUFFER_SIZE];
    int pos = 0;
    for (Iterator i = doc.iterator(); i.hasNext(); )
    {
      String s = (String)i.next();
      int slen = s.length();
      if (slen+pos >= buffer.length)
      {
        os.write(buffer, 0, pos);
        pos = 0;
        if (slen > buffer.length) {
            buffer = new char[slen];
        }
      }
      s.getChars(0, slen, buffer, pos);
      pos += slen;
      count += slen;
      if (count > flushThreshold)
      {
        os.flush();
        count = 0;
      }
    }
    os.write(buffer, 0, pos);

    String temporaryFileName = doc.getTemporaryFileName();
    if (temporaryFileName != null)
    {
      InputStreamReader reader = new InputStreamReader(new FileInputStream(temporaryFileName), "UTF8");
      for (int length = reader.read(buffer, 0, BUFFER_SIZE); length > 0; length = reader.read(buffer, 0, BUFFER_SIZE))
      {
        os.write(buffer, 0, length);
        count += length;
        if (count > flushThreshold)
        {
          os.flush();
          count = 0;
        }
      }
      reader.close();
      new File(temporaryFileName).delete();
    }
  }

  public void writeAscii(OutputStream os) throws IOException
  {
    int count = 0;
    final int BUFFER_SIZE = 8192;
    char[] buffer = new char[BUFFER_SIZE];
    byte[] bytes = new byte[BUFFER_SIZE];
    int pos = 0;
    for (Iterator i = doc.iterator(); i.hasNext(); )
    {
      String s = (String) i.next();
      int slen = s.length();
      if (slen + pos >= buffer.length)
      {
        for (int x = 0; x < pos; x++)
        {
          bytes[x] = (byte) (buffer[x] & 0xFF);
        }
        os.write(bytes, 0, pos);
        pos = 0;
        if (slen > buffer.length)
        {
          buffer = new char[slen];
          bytes = new byte[slen];
        }
      }
      s.getChars(0, slen, buffer, pos);
      pos += slen;
      count += slen;
      if (count > flushThreshold)
      {
        os.flush();
        count = 0;
      }
    }

    for (int x = 0; x < pos; x++)
    {
      bytes[x] = (byte) (buffer[x] & 0xFF);
    }

    os.write(bytes, 0, pos);

    String temporaryFileName = doc.getTemporaryFileName();
    if (temporaryFileName != null)
    {
      InputStream inputStream = new FileInputStream(temporaryFileName);
      for (int length = inputStream.read(bytes, 0, BUFFER_SIZE); length > 0; length = inputStream.read(bytes, 0, BUFFER_SIZE))
      {
        os.write(bytes, 0, length);
        count += length;
        if (count > flushThreshold)
        {
          os.flush();
          count = 0;
        }
      }
      inputStream.close();
      new File(temporaryFileName).delete();
    }
  }

  public char[] toChar()
  {
    int size = doc.getLength();
    char[] output = new char[size];
    doc.getChars(output, 0);
    return output;
  }

  protected void saveElement(EObject o, EStructuralFeature f)
  {
    EClass eClass = o.eClass();
    EClassifier eType = f.getEType();

    if (extendedMetaData != null && eClass != eType)
    {
      // Check if it's an anonymous type.
      //
      String name = extendedMetaData.getName(eClass);
      if (name.endsWith("_._type"))
      {
        String elementName = name.substring(0, name.indexOf("_._"));
        String prefix = helper.getPrefix(eClass.getEPackage());
        if (!"".equals(prefix))
        {
          elementName = prefix + ":" + elementName;
        }
        doc.startElement(elementName);
        saveElementID(o);
        return;
      }
    }

    if (map != null)
    {
      XMLResource.XMLInfo info = map.getInfo(eClass);
      if (info != null && info.getXMLRepresentation() == XMLResource.XMLInfo.ELEMENT)
      {
        String elementName = helper.getQName(eClass);
        doc.startElement(elementName);
        saveElementID(o);
        return;
      }
    }

    String featureName = helper.getQName(f);
    doc.startElement(featureName);

    if (eClass != eType && eClass != anyType)
    {
      if (eClass == anySimpleType)
      {
        saveTypeAttribute(((SimpleAnyType)o).getInstanceType());
      }
      else
      {
        saveTypeAttribute(eClass);
      }
    }

    saveElementID(o);
  }

  protected void saveTypeAttribute(EClass eClass)
  {
    declareXSI = true;
    doc.addAttribute(XSI_TYPE_NS, helper.getQName(eClass));
  }

  protected void saveTypeAttribute(EDataType eDataType)
  {
    declareXSI = true;
    doc.addAttribute(XSI_TYPE_NS, helper.getQName(eDataType));
  }

  protected boolean saveFeatures(EObject o)
  {
    EClass eClass = o.eClass();
    int contentKind = extendedMetaData == null ? ExtendedMetaData.UNSPECIFIED_CONTENT : extendedMetaData.getContentKind(eClass);
    switch (contentKind)
    {
      case ExtendedMetaData.MIXED_CONTENT:
      case ExtendedMetaData.SIMPLE_CONTENT:
      {
        doc.setMixed(true);
        break;
      }
    }

    EStructuralFeature[] features = featureTable.getFeatures(eClass);
    int[] featureKinds = featureTable.getKinds(eClass, features);
    int[] elementFeatures = null;
    int elementCount = 0;

    String content = null;

    // Process XML attributes
    LOOP:
    for (int i = 0; i < features.length; i++ )
    {
      int kind = featureKinds[i];
      EStructuralFeature f = features[i];
      if (kind != TRANSIENT && o.eIsSet(f))
      {
        switch (kind)
        {
          case DATATYPE_ELEMENT_SINGLE:
          {
            if (contentKind == ExtendedMetaData.SIMPLE_CONTENT)
            {
              content = getDataTypeElementSingleSimple(o, f);
              continue LOOP;
            }
            break;
          }
          case DATATYPE_SINGLE:
          {
            saveDataTypeSingle(o, f);
            continue LOOP;
          }
          case DATATYPE_SINGLE_NILLABLE:
          {
            if (!isNil(o, f))
            {
              saveDataTypeSingle(o, f);
              continue LOOP;
            }
            break;
          }
          case OBJECT_HREF_SINGLE_UNSETTABLE:
          {
            if (isNil(o, f))
            {
              break;
            }
            // it's intentional to keep going
          }
          case OBJECT_HREF_SINGLE:
          {
            if (useEncodedAttributeStyle)
            {
              saveEObjectSingle(o, f);
              continue LOOP;
            }
            else
            {
              switch (sameDocSingle(o, f))
              {
                case SAME_DOC:
                {
                  saveIDRefSingle(o, f);
                  continue LOOP;
                }
                case CROSS_DOC:
                {
                  break;
                }
                default:
                {
                  continue LOOP;
                }
              }
            }
            break;
          }
          case OBJECT_HREF_MANY_UNSETTABLE:
          {
            if (isEmpty(o, f))
            {
              saveManyEmpty(f);
              continue LOOP;
            }
            // It's intental to keep going.
          }
          case OBJECT_HREF_MANY:
          {
            if (useEncodedAttributeStyle)
            {
              saveEObjectMany(o, f);
              continue LOOP;
            }
            else
            {
              switch (sameDocMany(o, f))
              {
                case SAME_DOC:
                {
                  saveIDRefMany(o, f);
                  continue LOOP;
                }
                case CROSS_DOC:
                {
                  break;
                }
                default:
                {
                  continue LOOP;
                }
              }
            }
            break;
          }
          case OBJECT_ELEMENT_SINGLE_UNSETTABLE:
          case OBJECT_ELEMENT_SINGLE:
          {
            if (contentKind == ExtendedMetaData.SIMPLE_CONTENT)
            {
              content = getElementReferenceSingleSimple(o, f);
              continue LOOP;
            }
            break;
          }
          case OBJECT_ELEMENT_MANY:
          {
            if (contentKind == ExtendedMetaData.SIMPLE_CONTENT)
            {
              content = getElementReferenceManySimple(o, f);
              continue LOOP;
            }
            break;
          }
          case OBJECT_ELEMENT_IDREF_SINGLE_UNSETTABLE:
          case OBJECT_ELEMENT_IDREF_SINGLE:
          {
            if (contentKind == ExtendedMetaData.SIMPLE_CONTENT)
            {
              content = getElementIDRefSingleSimple(o, f);
              continue LOOP;
            }
            break;
          }
          case OBJECT_ELEMENT_IDREF_MANY:
          {
            if (contentKind == ExtendedMetaData.SIMPLE_CONTENT)
            {
              content = getElementIDRefManySimple(o, f);
              continue LOOP;
            }
            break;
          }
          case OBJECT_CONTAIN_MANY_UNSETTABLE:
          case DATATYPE_MANY:
          {
            if (isEmpty(o, f))
            {
              saveManyEmpty(f);
              continue LOOP;
            }
            break;
          }
          case OBJECT_CONTAIN_SINGLE_UNSETTABLE:
          case OBJECT_CONTAIN_SINGLE:
          case OBJECT_CONTAIN_MANY:
          case ELEMENT_FEATURE_MAP:
          {
            break;
          }
          case ATTRIBUTE_FEATURE_MAP:
          {
            saveAttributeFeatureMap(o, f);
            continue LOOP;
          }
          default:
          {
            continue LOOP;
          }
        }

        // We only get here if we should do this.
        //
        if (elementFeatures == null)
        {
          elementFeatures = new int[features.length];
        }
        elementFeatures[elementCount++] = i;
      }
    }

    processAttributeExtensions(o);

    if (elementFeatures == null)
    {
      if (content == null)
      {
        content = getContent(o, features);
      }

      if (content == null)
      {
        endSaveFeatures(o, EMPTY_ELEMENT, null);
        return false;
      }
      else
      {
        endSaveFeatures(o, CONTENT_ELEMENT, content);
        return true;
      }
    }

    // Process XML elements
    for (int i = 0; i < elementCount; i++ )
    {
      int kind = featureKinds[elementFeatures[i]];
      EStructuralFeature f = features[elementFeatures[i]];
      switch (kind)
      {
        case DATATYPE_SINGLE_NILLABLE:
        {
          saveNil(f);
          break;
        }
        case ELEMENT_FEATURE_MAP:
        {
          saveElementFeatureMap(o, f);
          break;
        }
        case DATATYPE_MANY:
        {
          saveDataTypeMany(o, f);
          break;
        }
        case DATATYPE_ELEMENT_SINGLE:
        {
          saveDataTypeElementSingle(o, f);
          break;
        }
        case OBJECT_CONTAIN_SINGLE_UNSETTABLE:
        {
          if (isNil(o, f))
          {
            saveNil(f);
            break;
          }
          // it's intentional to keep going
        }
        case OBJECT_CONTAIN_SINGLE:
        {
          saveContainedSingle(o, f);
          break;
        }
        case OBJECT_CONTAIN_MANY_UNSETTABLE:
        case OBJECT_CONTAIN_MANY:
        {
          saveContainedMany(o, f);
          break;
        }
        case OBJECT_HREF_SINGLE_UNSETTABLE:
        {
          if (isNil(o, f))
          {
            saveNil(f);
            break;
          }
          // it's intentional to keep going
        }
        case OBJECT_HREF_SINGLE:
        {
          saveHRefSingle(o, f);
          break;
        }
        case OBJECT_HREF_MANY_UNSETTABLE:
        case OBJECT_HREF_MANY:
        {
          saveHRefMany(o, f);
          break;
        }
        case OBJECT_ELEMENT_SINGLE_UNSETTABLE:
        {
          if (isNil(o, f))
          {
            saveNil(f);
            break;
          }
          // it's intentional to keep going
        }
        case OBJECT_ELEMENT_SINGLE:
        {
          saveElementReferenceSingle(o, f);
          break;
        }
        case OBJECT_ELEMENT_MANY:
        {
          saveElementReferenceMany(o, f);
          break;
        }
        case OBJECT_ELEMENT_IDREF_SINGLE_UNSETTABLE:
        {
          if (isNil(o, f))
          {
            saveNil(f);
            break;
          }
          // it's intentional to keep going
        }
        case OBJECT_ELEMENT_IDREF_SINGLE:
        {
          saveElementIDRefSingle(o, f);
          break;
        }
        case OBJECT_ELEMENT_IDREF_MANY:
        {
          saveElementIDRefMany(o, f);
          break;
        }
      }
    }
    endSaveFeatures(o, 0, null);
    return true;
  }

  protected void endSaveFeatures(EObject o, int elementType, String content)
  {
    if (processElementExtensions(o))
    {
      doc.endElement();
    }
    else
    {
      switch (elementType)
      {
        case EMPTY_ELEMENT:
        {
          doc.endEmptyElement();
          break;
        }
        case CONTENT_ELEMENT:
        {
          doc.endContentElement(content);
          break;
        }
        default:
        {
          doc.endElement();
          break;
        }
      }
    }
  }
  
  /**
   *  Returns true if there were extensions for the specified object.
   */
  protected boolean processElementExtensions(EObject object)
  {
    if (eObjectToExtensionMap != null)
    {
      AnyType anyType = (AnyType)eObjectToExtensionMap.get(object);
      return anyType != null && saveElementFeatureMap(anyType, XMLTypePackage.eINSTANCE.getAnyType_Mixed());
    }
    else
    {
      return false;
    }
  }

  /**
   */
  protected void processAttributeExtensions(EObject object)
  {
    if (eObjectToExtensionMap != null)
    {
      AnyType anyType = (AnyType)eObjectToExtensionMap.get(object);
      if (anyType != null)
      {
        saveAttributeFeatureMap(anyType, XMLTypePackage.eINSTANCE.getAnyType_AnyAttribute());
      }
    }
  }

  protected void saveDataTypeSingle(EObject o, EStructuralFeature f)
  {
    EDataType d = (EDataType)f.getEType();
    EPackage ePackage = d.getEPackage();
    EFactory fac = ePackage.getEFactoryInstance();
    Object value = helper.getValue(o, f);
    if (value != null)
    {
      String svalue = helper.convertToString(fac, d, value);
      if (escape != null)
      {
        svalue = escape.convert(svalue);
      }
      doc.addAttribute(helper.getQName(f), svalue);
    }
  }

  protected boolean isNil(EObject o, EStructuralFeature f)
  {
    return helper.getValue(o, f) == null;
  }

  protected boolean isEmpty(EObject o, EStructuralFeature f)
  {
    return ((List)helper.getValue(o, f)).isEmpty();
  }

  protected void saveNil(EStructuralFeature f)
  {
    String name = helper.getQName(f);
    doc.startElement(name);
    doc.addAttribute(XSI_NIL, "true");
    doc.endEmptyElement();
    declareXSI = true;
  }

  protected void saveManyEmpty(EStructuralFeature f)
  {
    doc.addAttribute(helper.getQName(f), "");
  }

  protected void saveDataTypeMany(EObject o, EStructuralFeature f)
  {
    EDataType d = (EDataType)f.getEType();
    EPackage ePackage = d.getEPackage();
    EFactory fac = ePackage.getEFactoryInstance();

    List values = (List)helper.getValue(o, f);
    int size = values.size();
    if (size > 0)
    {
      String name = helper.getQName(f);
      for (int i = 0; i < size; ++i)
      {
        Object value = values.get(i);
        if (value == null)
        {
          doc.startElement(name);
          doc.addAttribute(XSI_NIL, "true");
          doc.endEmptyElement();
          declareXSI = true;
        }
        else
        {
          doc.startElement(name);
          String svalue = helper.convertToString(fac, d, value);
          if (escape != null)
          {
            svalue = escape.convert(svalue);
          }
          doc.endContentElement(svalue);
        }
      }
    }
  }

  protected void saveEObjectSingle(EObject o, EStructuralFeature f)
  {
    EObject value = (EObject)helper.getValue(o, f);
    if (value != null)
    {
      String name = helper.getQName(f);
      String id = helper.getHREF(value);
      if (id != null)
      {
        if (escapeURI != null)
        {
          id = escapeURI.convert(id);
        } 
        doc.startAttribute(name);
        if (!id.startsWith("#"))
        {
          EClass eClass = value.eClass();
          EClass expectedType = (EClass) f.getEType();
          if (eClass != expectedType && (saveTypeInfo || expectedType.isAbstract()))
          {
            doc.addAttributeContent(helper.getQName(eClass));
            doc.addAttributeContent(" ");
          }
        }
        doc.addAttributeContent(id);
        doc.endAttribute();
      }
    }
  }

  protected void saveEObjectMany(EObject o, EStructuralFeature f)
  {
    InternalEList values = (InternalEList)helper.getValue(o, f);

    if (!values.isEmpty())
    {
      String name = helper.getQName(f);
      doc.startAttribute(name);

      for (Iterator i = values.basicIterator(); ; )
      {
        EObject value = (EObject)i.next();
        String id = helper.getHREF(value);
        if (id != null)
        {
          if (escapeURI != null)
          {
            id = escapeURI.convert(id);
          }
          if (!id.startsWith("#"))
          {
            EClass eClass = value.eClass();
            EClass expectedType = (EClass) f.getEType();
            if (eClass != expectedType && (saveTypeInfo || expectedType.isAbstract()))
            {
              doc.addAttributeContent(helper.getQName(eClass));
              doc.addAttributeContent(" ");
            }
          }
          doc.addAttributeContent(id);
        }
        if (i.hasNext())
        {
          doc.addAttributeContent(" ");
        }
        else
        {
          break;
        }
      }
      doc.endAttribute();
    }
  }

  protected void saveIDRefSingle(EObject o, EStructuralFeature f)
  {
    EObject value = (EObject)helper.getValue(o, f);
    if (value != null)
    {
      String name = helper.getQName(f);
      String id = helper.getIDREF(value);
      doc.addAttribute(name, id);
    }
  }

  protected void saveIDRefMany(EObject o, EStructuralFeature f)
  {
    InternalEList values = (InternalEList)helper.getValue(o, f);
    if (!values.isEmpty())
    {
      String name = helper.getQName(f);
      StringBuffer ids = new StringBuffer(values.size() * 10);
      for (Iterator i = values.basicIterator();;)
      {
        EObject value = (EObject)i.next();
        String id = helper.getIDREF(value);
        ids.append(id);
        if (i.hasNext())
        {
          ids.append(" ");
        }
        else
        {
          break;
        }
      }
      doc.addAttribute(name, ids.toString());
    }
  }

  protected void saveElementReference(EObject remote, EStructuralFeature f)
  {
    String name = helper.getQName(f);
    String href = helper.getHREF(remote);
    if (href != null)
    {
      if (escapeURI != null)
      {
        href = escapeURI.convert(href);
      }
      doc.startElement(name);
      EClass eClass = remote.eClass();
      EClass expectedType = (EClass)f.getEType();
      if (eClass != expectedType && (saveTypeInfo || expectedType.isAbstract()))
      {
        saveTypeAttribute(eClass);
      }
      doc.endContentElement(href);
    }
  }

  protected void saveElementReferenceSingle(EObject o, EStructuralFeature f)
  {
    EObject value = (EObject)helper.getValue(o, f);
    if (value != null)
    {
      saveElementReference(value, f);
    }
  }

  protected void saveElementReferenceMany(EObject o, EStructuralFeature f)
  {
    InternalEList values = (InternalEList)helper.getValue(o, f);
    int size = values.size();
    for (int i = 0; i < size; i++)
    {
      saveElementReference((EObject)values.basicGet(i), f);
    }
  }

  protected String getElementReferenceSingleSimple(EObject o, EStructuralFeature f)
  {
    EObject value = (EObject)helper.getValue(o, f);
    String href = helper.getHREF(value);
    if (escapeURI != null && href != null)
    {
      href = escapeURI.convert(href);
    }
    return href;
  }

  protected String getElementReferenceManySimple(EObject o, EStructuralFeature f)
  {
    InternalEList values = (InternalEList)helper.getValue(o, f);
    StringBuffer result = new StringBuffer();
    int size = values.size();
    String href = null;
    for (int i = 0; i < size; i++)
    {
      href = helper.getHREF(((EObject)values.basicGet(i)));
      if (escapeURI != null && href != null)
      {
        href = escapeURI.convert(href);
      }
      result.append(href);
      result.append(' ');
    }
    return result.substring(0, result.length() - 1);
  }

  protected void saveElementIDRef(EObject target, EStructuralFeature f)
  {
    String name = helper.getQName(f);
    String id = helper.getIDREF(target);
    doc.startElement(name);
    doc.endContentElement(id);
  }

  protected void saveElementIDRefSingle(EObject o, EStructuralFeature f)
  {
    EObject value = (EObject)helper.getValue(o, f);
    if (value != null)
    {
      saveElementIDRef(value, f);
    }
  }

  protected void saveElementIDRefMany(EObject o, EStructuralFeature f)
  {
    InternalEList values = (InternalEList)helper.getValue(o, f);
    int size = values.size();
    for (int i = 0; i < size; i++)
    {
      saveElementIDRef((EObject)values.basicGet(i), f);
    }
  }

  protected String getElementIDRefSingleSimple(EObject o, EStructuralFeature f)
  {
    EObject value = (EObject)helper.getValue(o, f);
    return helper.getIDREF(value);
  }

  protected String getElementIDRefManySimple(EObject o, EStructuralFeature f)
  {
    InternalEList values = (InternalEList)helper.getValue(o, f);
    StringBuffer result = new StringBuffer();
    for (int i = 0, size = values.size(); i < size; i++)
    {
      result.append(helper.getIDREF((EObject)values.basicGet(i)));
      result.append(' ');
    }
    return result.substring(0, result.length() - 1);
  }

/*
  protected void saveElementIDRefMany(EObject o, EStructuralFeature f)
  {
    InternalEList values = (InternalEList)helper.getValue(o, f);
    if (!values.isEmpty())
    {
      String name = helper.getQName(f);
      StringBuffer ids = new StringBuffer(values.size() * 10);
      for (Iterator i = values.basicIterator();;)
      {
        EObject value = (EObject)i.next();
        String id = helper.getIDREF(value);
        ids.append(id);
        if (i.hasNext())
        {
          ids.append(" ");
        }
        else
        {
          break;
        }
      }
      doc.startElement(name);
      doc.endContentElement(ids.toString());
    }
  }
*/

  protected void saveHref(EObject remote, EStructuralFeature f)
  {
    String name = helper.getQName(f);
    String href = helper.getHREF(remote);
    if (href != null)
    {
      if (escapeURI != null)
      {
        href = escapeURI.convert(href);
      }
      doc.startElement(name);
      EClass eClass = remote.eClass();

      EClass expectedType = (EClass) f.getEType();
      if (eClass != expectedType && (saveTypeInfo || expectedType.isAbstract()))
      {
        saveTypeAttribute(eClass);
      }

      doc.addAttribute(XMLResource.HREF, href);
      doc.endEmptyElement();
    }
  }

  protected void saveHRefSingle(EObject o, EStructuralFeature f)
  {
    EObject value = (EObject)helper.getValue(o, f);
    if (value != null)
    {
      saveHref(value, f);
    }
  }

  protected void saveHRefMany(EObject o, EStructuralFeature f)
  {
    InternalEList values = (InternalEList)helper.getValue(o, f);
    int size = values.size();
    for (int i = 0; i < size; i++)
    {
      saveHref((EObject)values.basicGet(i), f);
    }
  }

  protected void saveContainedSingle(EObject o, EStructuralFeature f)
  {
    EObject value = (EObject)helper.getValue(o, f);
    if (value != null)
    {
      saveElement(value, f);
    }
  }

  protected void saveContainedMany(EObject o, EStructuralFeature f)
  {
    List values = (List)helper.getValue(o, f);
    int size = values.size();
    for (int i = 0; i < size; i++)
    {
      EObject value = (EObject)values.get(i);
      if (value != null)
      {
        saveElement(value, f);
      }
    }
  }

  protected boolean saveElementFeatureMap(EObject o, EStructuralFeature f)
  {
    List values = (List)helper.getValue(o, f);
    int size = values.size();
    for (int i = 0; i < size; i++)
    {
      FeatureMap.Entry entry = (FeatureMap.Entry)values.get(i);
      EStructuralFeature entryFeature = entry.getEStructuralFeature();
      Object value = entry.getValue();
      if (entryFeature instanceof EReference)
      {
        if (value == null)
        {
          saveNil(entryFeature);
        }
        else 
        {
          EReference referenceEntryFeature = (EReference)entryFeature;
          if (referenceEntryFeature.isContainment())
          {
            saveElement((EObject)value, entryFeature);
          }
          else if (referenceEntryFeature.isResolveProxies())
          {
            saveElementReference((EObject)value, entryFeature);
          }
          else
          {
            saveElementIDRef((EObject)value, entryFeature);
          }
        }
      }
      else
      {
        if (entryFeature == XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text())
        {
          String stringValue = value.toString();
          if (escape != null)
          {
            stringValue = escape.convertText(stringValue);
          }
          doc.addText(stringValue);
        }
        else if (entryFeature == XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_CDATA())
        {
          String stringValue = value.toString();
          if (escape != null)
          {
            stringValue = escape.convertLines(stringValue);
          }
          doc.addCDATA(stringValue);
        }
        else if (entryFeature == XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Comment())
        {
          String stringValue = value.toString();
          if (escape != null)
          {
            stringValue = escape.convertLines(stringValue);
          }
          doc.addComment(stringValue);
        }
        else if (value == null)
        {
          saveNil(entryFeature);
        }
        else
        {
          doc.startElement(helper.getQName(entryFeature));
          EDataType d = (EDataType)entryFeature.getEType();
          EPackage ePackage = d.getEPackage();
          EFactory fac = ePackage.getEFactoryInstance();
          String svalue = helper.convertToString(fac, d, value);
          if (escape != null)
          {
            svalue = escape.convertText(svalue);
          }
          doc.endContentElement(svalue);
        }
      }
    }
    return size > 0;
  }

  protected void saveAttributeFeatureMap(EObject o, EStructuralFeature f)
  {
    List values = (List)helper.getValue(o, f);
    int size = values.size();
    for (int i = 0; i < size; i++)
    {
      FeatureMap.Entry entry = (FeatureMap.Entry)values.get(i);
      EStructuralFeature entryFeature = entry.getEStructuralFeature();
      Object value = entry.getValue();
      if (entryFeature instanceof EReference)
      {
        throw new UnsupportedOperationException();
        // saveElement((EObject)value, entryFeature);
      }
      else
      {
        EDataType d = (EDataType)entryFeature.getEType();
        EPackage ePackage = d.getEPackage();
        EFactory fac = ePackage.getEFactoryInstance();
        String svalue = helper.convertToString(fac, d, value);        
        if (escape != null)
        {
          svalue = escape.convertText(svalue);
        }
        doc.addAttribute(helper.getQName(entryFeature), svalue);
      }
    }
  }

  protected int sameDocSingle(EObject o, EStructuralFeature f)
  {
    InternalEObject value = (InternalEObject)helper.getValue(o, f);
    if (value == null)
    {
      return SKIP;
    }
    else if (value.eIsProxy())
    {
      return CROSS_DOC;
    }
    else
    {
      Resource res = value.eResource();
      return res == helper.getResource() ? SAME_DOC : CROSS_DOC;
    }
  }

  protected int sameDocMany(EObject o, EStructuralFeature f)
  {
    InternalEList values = (InternalEList)helper.getValue(o, f);
    if (values.isEmpty())
    {
      return SKIP;
    }

    for (Iterator i = values.basicIterator(); i.hasNext(); )
    {
      InternalEObject value = (InternalEObject)i.next();
      if (value.eIsProxy() || value.eResource() != helper.getResource()) {
        return CROSS_DOC;
      }
    }

    return SAME_DOC;
  }

  protected String getContent(EObject o, EStructuralFeature[] features)
  {
    if (map == null)
    {
      return null;
    }

    for (int i = 0; i < features.length; i++)
    {
      XMLResource.XMLInfo info = map.getInfo(features[i]);

      if (info != null && info.getXMLRepresentation() == XMLResource.XMLInfo.CONTENT)
      {
        Object value = helper.getValue(o, features[i]);
        if (value == null)
        {
          return null;
        }
        else
        {
          EDataType d = (EDataType) features[i].getEType();
          EPackage ePackage = d.getEPackage();
          EFactory fac = ePackage.getEFactoryInstance();
          String svalue = helper.convertToString(fac, d, value);         
          if (escape != null)
          {
            svalue = escape.convert(svalue);
          }
          return svalue;
        }
      }
    }

    return null;
  }

  protected void saveDataTypeElementSingle(EObject o, EStructuralFeature f)
  {
    String name = helper.getQName(f);
    Object value = helper.getValue(o, f);
    if (value == null)
    {
      doc.startElement(name);
      doc.addAttribute(XSI_NIL, "true");
      doc.endEmptyElement();
      declareXSI = true;
    }
    else
    {
      EDataType d = (EDataType)f.getEType();
      EPackage ePackage = d.getEPackage();
      EFactory fac = ePackage.getEFactoryInstance();
      doc.startElement(name);
      String svalue = helper.convertToString(fac, d, value);
      if (escape != null)
      {
        svalue = escape.convert(svalue);
      }

      doc.endContentElement(svalue);
    }
  }

  protected String getDataTypeElementSingleSimple(EObject o, EStructuralFeature f)
  {
    Object value = helper.getValue(o, f);
    if (value != null)
    {
      EDataType d = (EDataType)f.getEType();
      EPackage ePackage = d.getEPackage();
      EFactory fac = ePackage.getEFactoryInstance();
      String svalue = helper.convertToString(fac, d, value);
      if (escape != null)
      {
        svalue = escape.convert(svalue);
      }
      return svalue;
    }
    else
    {
      return null;
    }
  }

  protected void saveElementID(EObject o)
  {
    String id = helper.getID(o);
    if (id != null)
    {
      doc.addAttribute(idAttributeName, id);
    }

    saveFeatures(o);
  }

  protected static class Lookup
  {
    protected static final int SIZE = 4095;  // 2^N-1
    protected EClass[] classes;
    protected EStructuralFeature[][] features;
    protected int[][] featureKinds;
    protected XMLResource.XMLMap map;
    protected ExtendedMetaData extendedMetaData;

    public Lookup(XMLResource.XMLMap map)
    {
      this(map, null);
    }

    public Lookup(XMLResource.XMLMap map, ExtendedMetaData extendedMetaData)
    {
      this.map = map;
      this.extendedMetaData = extendedMetaData;
      classes = new EClass[SIZE + 1];
      features = new EStructuralFeature[SIZE + 1][];
      featureKinds = new int[SIZE + 1][];
    }

    public EStructuralFeature[] getFeatures(EClass cls)
    {
      int index = cls.hashCode() & SIZE;
      EClass c = classes[index];

      if (c == cls)
      {
        return features[index];
      }

      EStructuralFeature[] featureList = listFeatures(cls);
      if (c == null)
      {
        classes[index] = cls;
        features[index] = featureList;
        featureKinds[index] = listKinds(featureList);
      }
      return featureList;
    }

    public int[] getKinds(EClass cls, EStructuralFeature[] featureList)
    {
      int index = cls.hashCode() & SIZE;
      EClass c = classes[index];

      if (c == cls)
      {
        return featureKinds[index];
      }

      int[] kindsList = listKinds(featureList);
      if (c == null)
      {
        classes[index] = cls;
        features[index] = featureList;
        featureKinds[index] = kindsList;
      }
      return kindsList;
    }

    protected EStructuralFeature[] listFeatures(EClass cls)
    {
      if (extendedMetaData != null)
      {
        List f = new ArrayList();
        f.addAll(cls.getEAllStructuralFeatures());
        List orderedElements = extendedMetaData.getAllElements(cls);
        f.removeAll(orderedElements);
        f.addAll(orderedElements);
        return (EStructuralFeature[]) f.toArray(new EStructuralFeature[f.size()]);
      }
      else
      {
        List f = map == null ? cls.getEAllStructuralFeatures() : map.getFeatures(cls);
        return (EStructuralFeature[]) f.toArray(new EStructuralFeature[f.size()]);
      }
    }

    protected int[] listKinds(EStructuralFeature[] featureList)
    {
      int[] kinds = new int[featureList.length];
      for (int i = featureList.length-1; i >= 0; i--)
      {
        kinds[i] = featureKind(featureList[i]);
      }

      return kinds;
    }

    protected int featureKind(EStructuralFeature f)
    {
      if (f.isTransient())
      {
        return TRANSIENT;
      }

      boolean isMany = f.isMany();
      boolean isUnsettable = f.isUnsettable();

      if (f instanceof EReference)
      {
        EReference r = (EReference)f;
        if (r.isContainment())
        {
          return
            isMany ?
              isUnsettable ? OBJECT_CONTAIN_MANY_UNSETTABLE : OBJECT_CONTAIN_MANY :
              isUnsettable ? OBJECT_CONTAIN_SINGLE_UNSETTABLE : OBJECT_CONTAIN_SINGLE;
        }
        EReference opposite = r.getEOpposite();
        if (opposite != null && opposite.isContainment())
        {
          return TRANSIENT;
        }

        if (map != null)
        {
          XMLResource.XMLInfo info = map.getInfo(f);
          if (info != null && info.getXMLRepresentation() == XMLResource.XMLInfo.ELEMENT)
          {
            return
              isMany ?
                OBJECT_ELEMENT_MANY : 
                r.isUnsettable() ?
                  OBJECT_ELEMENT_SINGLE_UNSETTABLE :
                  OBJECT_ELEMENT_SINGLE;
          }
        }

        if (extendedMetaData != null)
        {
          switch (extendedMetaData.getFeatureKind(f))
          {
            case ExtendedMetaData.SIMPLE_FEATURE:
            case ExtendedMetaData.ELEMENT_FEATURE:
            {
              return
                  r.isResolveProxies() ?
                    isMany ?
                      OBJECT_ELEMENT_MANY : 
                      r.isUnsettable() ?
                        OBJECT_ELEMENT_SINGLE_UNSETTABLE :
                        OBJECT_ELEMENT_SINGLE :
                    isMany ?
                      OBJECT_ELEMENT_IDREF_MANY : 
                      r.isUnsettable() ?
                        OBJECT_ELEMENT_IDREF_SINGLE_UNSETTABLE :
                        OBJECT_ELEMENT_IDREF_SINGLE;
            }
          }
        }

        return
          isMany ?
            isUnsettable ? OBJECT_HREF_MANY_UNSETTABLE : OBJECT_HREF_MANY :
            isUnsettable ? OBJECT_HREF_SINGLE_UNSETTABLE : OBJECT_HREF_SINGLE;
      }
      else
      {
        // Attribute
        EDataType d = (EDataType) f.getEType();
        if (!d.isSerializable())
        {
          return TRANSIENT;
        }

        if (d.getInstanceClass() == FeatureMap.Entry.class)
        {
          return
            extendedMetaData != null && extendedMetaData.getFeatureKind(f) == ExtendedMetaData.ATTRIBUTE_WILDCARD_FEATURE ?
              ATTRIBUTE_FEATURE_MAP :
              ELEMENT_FEATURE_MAP;
        }

        if (isMany)
        {
          return DATATYPE_MANY;
        }

        if (extendedMetaData != null)
        {
          switch (extendedMetaData.getFeatureKind(f))
          {
            case ExtendedMetaData.SIMPLE_FEATURE:
            case ExtendedMetaData.ELEMENT_FEATURE:
            {
              return DATATYPE_ELEMENT_SINGLE;
            }
          }
        }

        if (isUnsettable && map == null)
        {
          return DATATYPE_SINGLE_NILLABLE;
        }

        if (map == null)
        {
          return DATATYPE_SINGLE;
        }
        else
        {
          XMLResource.XMLInfo info = map.getInfo(f);

          if (info != null && info.getXMLRepresentation() == XMLResource.XMLInfo.ELEMENT)
          {
            return DATATYPE_ELEMENT_SINGLE;
          }
          else if (info != null && info.getXMLRepresentation() == XMLResource.XMLInfo.CONTENT)
          {
            return DATATYPE_CONTENT_SINGLE;
          }
          else
          {
            if (isUnsettable)
              return DATATYPE_SINGLE_NILLABLE;
            else
              return DATATYPE_SINGLE;
          }
        }
      }
    }
  }

  protected static class Escape
  {
    protected char[] value;

    protected final char[] AMP   = { '&', 'a', 'm', 'p', ';' };
    protected final char[] LESS  = { '&', 'l', 't',';' };
    protected final char[] QUOTE = { '&', 'q', 'u', 'o', 't', ';' };
    protected final char[] LF    = { '&', '#', 'x', 'A', ';' };
    protected final char[] CR    = { '&', '#', 'x', 'D', ';' };
    protected final char[] TAB   = { '&', '#', 'x', '9', ';' };
    protected final char[] LINE_FEED = System.getProperty("line.separator").toCharArray();

    public Escape()
    {
      value = new char[100];
    }

    /*
     *  Convert:
     *  & to &amp;
     *  < to &lt;
     *  " to &quot;
     *  \t to &#x9;
     *  \n to &#xA;
     *  \r to &#xD;
     */
    public String convert(String input)
    {
      //TODO: performance could be improved (e.g. avoid copy characters on each string)
      boolean changed = false;
      int inputLength = input.length();
      grow(inputLength);
      input.getChars(0, inputLength, value, 0);
      int pos = 0;
      char ch = 0;
      while (inputLength-- > 0)
      {
        ch = value[pos];
        switch (ch)
        {
          case '&':
            pos = replace(pos, AMP, inputLength);
            changed = true;
            break;
          case '<':
            pos = replace(pos, LESS, inputLength);
            changed = true;
            break;
          case '"':
            pos = replace(pos, QUOTE, inputLength);
            changed = true;
            break;
          case '\n':
          {
            pos = replace(pos, LF, inputLength);
            changed = true;
            break;
          }
          case '\r':
          {
            pos = replace(pos, CR, inputLength);
            changed = true;
            break;
          }
          case '\t':
          {
            pos = replace(pos, TAB, inputLength);
            changed = true;
            break;
          }
          default:
            if (!XMLChar.isValid(ch))
            {
              throw new RuntimeException("An invalid XML character (Unicode: 0x" + Integer.toHexString((int)ch)+") was found in the element content:" +input);
            }
            pos++;
            break;
        }
      }

      return changed ? new String(value, 0, pos) : input;
    }

    /*
     *  Convert:
     *  & to &amp;
     *  < to &lt;
     *  " to &quot;
     *  \n to line separator
     */
    public String convertText(String input)
    {
      //TODO: performance could be improved (e.g. avoid copy characters on each string)
      boolean changed = false;
      int inputLength = input.length();
      grow(inputLength);
      input.getChars(0, inputLength, value, 0);
      int pos = 0;
      char ch;
      while (inputLength-- > 0)
      {
        ch = value[pos];
        switch (ch)
        {
          case '&':
            pos = replace(pos, AMP, inputLength);
            changed = true;
            break;
          case '<':
            pos = replace(pos, LESS, inputLength);
            changed = true;
            break;
          case '"':
            pos = replace(pos, QUOTE, inputLength);
            changed = true;
            break;
          case '\n':
          {
            pos = replace(pos, LINE_FEED, inputLength);
            changed = true;
            break;
          }
          default:
            if (!XMLChar.isValid(ch))
            {
              throw new RuntimeException("An invalid XML character (Unicode: 0x" + Integer.toHexString((int)ch)+") was found in the element content:" +input);
            }
            pos++;
            break;
        }
      }

      return changed ? new String(value, 0, pos) : input;
    }

    /*
     *  Convert:
     *  \n to line separator
     */
    public String convertLines(String input)
    {
      boolean changed = false;
      int inputLength = input.length();
      grow(inputLength);
      input.getChars(0, inputLength, value, 0);
      int pos = 0;
      while (inputLength-- > 0)
      {
        switch (value[pos])
        {
          case '\n':
          {
            pos = replace(pos, LINE_FEED, inputLength);
            changed = true;
            break;
          }
          default:
            pos++;
            break;
        }
      }

      return changed ? new String(value, 0, pos) : input;
    }

    protected int replace(int pos, char[] replacement, int inputLength)
    {
      int rlen = replacement.length;
      int newPos = pos + rlen;
      grow(newPos + inputLength);
      System.arraycopy(value, pos+1, value, newPos, inputLength);
      System.arraycopy(replacement, 0, value, pos, rlen);
      return newPos;
    }

    protected void grow(int newSize)
    {
      int vlen = value.length;
      if (vlen < newSize)
      {
        char[] newValue = new char[newSize + newSize/2];
        System.arraycopy(value, 0, newValue, 0, vlen);
        value = newValue;
      }
    }
  }
}
