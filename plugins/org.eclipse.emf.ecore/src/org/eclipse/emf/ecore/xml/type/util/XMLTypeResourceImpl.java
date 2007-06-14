/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: XMLTypeResourceImpl.java,v 1.6 2007/06/14 18:32:46 emerks Exp $
 */
package org.eclipse.emf.ecore.xml.type.util;

import java.math.BigDecimal;
import java.math.BigInteger;


import java.util.List;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.resource.impl.ResourceImpl;

import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.ecore.xml.type.ProcessingInstruction;
import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
import org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
// import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * Provides access to the generated frame factory for the built-in XML types.
 * @generated
 */
public class XMLTypeResourceImpl extends ResourceImpl
{
  /**
   * Creates an instance of the resource.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param uri the URI of the new resource.
   * @generated
   */
  public XMLTypeResourceImpl(URI uri)
  {
    super(uri);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public final static class FrameFactory
  {
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final FrameFactory INSTANCE = new FrameFactory();
  
    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected AnyTypeStackFrame anyType;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ProcessingInstructionStackFrame processingInstruction;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected SimpleAnyTypeStackFrame simpleAnyType;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeDocumentRootStackFrame xmlTypeDocumentRoot;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame anySimpleType;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame anyURI;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame base64Binary;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame boolean_;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame booleanObject;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame byte_;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame byteObject;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame date;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame dateTime;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame decimal;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame double_;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame doubleObject;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame duration;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame entities;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame entitiesBase;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame entity;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame float_;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame floatObject;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame gDay;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame gMonth;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame gMonthDay;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame gYear;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame gYearMonth;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame hexBinary;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame id;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame idref;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame idrefs;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame idrefsBase;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame int_;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame integer;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame intObject;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame language;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame long_;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame longObject;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame name;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame ncName;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame negativeInteger;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame nmtoken;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame nmtokens;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame nmtokensBase;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame nonNegativeInteger;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame nonPositiveInteger;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame normalizedString;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame notation;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame positiveInteger;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame qName;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame short_;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame shortObject;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame string;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame time;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame token;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame unsignedByte;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame unsignedByteObject;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame unsignedInt;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame unsignedIntObject;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame unsignedLong;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame unsignedShort;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected XMLTypeResourceImpl.DataFrame unsignedShortObject;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AnyTypeStackFrame pushAnyType(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       AnyTypeStackFrame resultAnyType = anyType == null ? new AnyTypeStackFrame() : anyType;
       anyType = null;
       resultAnyType.pushOnto(previous);
       resultAnyType.handleAttributes(attributes);
       return resultAnyType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public AnyType popAnyType(AnyTypeStackFrame anyType)
    {
      AnyType resultAnyTypeValue = anyType.popAnyType();
      this.anyType = anyType;
      return resultAnyTypeValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static class AnyTypeStackFrame extends XMLTypeResourceImpl.StackFrame
    {
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      protected AnyType theAnyType;
    
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      @Override
      public void handleAttributes(Attributes attributes)
      {
        // There are attributes to handle.
      }
    
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      @Override
      public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException
      {
        return super.startElement(namespace, localName, qName, attributes);
      }

      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      @Override
      public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException
      {
        super.endElement(child);
      }

      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      @Override
      public void create()
      {
        theAnyType = XMLTypeFactory.eINSTANCE.createAnyType();
      }
    
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      protected AnyType popAnyType()
      {
        pop();
        AnyType resultAnyTypeValue = theAnyType;
        theAnyType = null;
        return resultAnyTypeValue;
      }
    
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ProcessingInstructionStackFrame pushProcessingInstruction(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       ProcessingInstructionStackFrame resultProcessingInstruction = processingInstruction == null ? new ProcessingInstructionStackFrame() : processingInstruction;
       processingInstruction = null;
       resultProcessingInstruction.pushOnto(previous);
       resultProcessingInstruction.handleAttributes(attributes);
       return resultProcessingInstruction;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ProcessingInstruction popProcessingInstruction(ProcessingInstructionStackFrame processingInstruction)
    {
      ProcessingInstruction resultProcessingInstructionValue = processingInstruction.popProcessingInstruction();
      this.processingInstruction = processingInstruction;
      return resultProcessingInstructionValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static class ProcessingInstructionStackFrame extends XMLTypeResourceImpl.StackFrame
    {
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      protected ProcessingInstruction theProcessingInstruction;
    
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      protected XMLTypeResourceImpl.DataFrame data;
    
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      protected XMLTypeResourceImpl.DataFrame target;
    
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      @Override
      public void handleAttributes(Attributes attributes)
      {
        String theValue = attributes.getValue("", "data");
        if (theValue != null)
        {
          theProcessingInstruction.setData(XMLTypeFactory.eINSTANCE.createString(theValue));
        }
        theValue = attributes.getValue("", "target");
        if (theValue != null)
        {
          theProcessingInstruction.setTarget(XMLTypeFactory.eINSTANCE.createString(theValue));
        }
      }
    
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      @Override
      public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException
      {
        return super.startElement(namespace, localName, qName, attributes);
      }

      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      @Override
      public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException
      {
        super.endElement(child);
      }

      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      @Override
      public void create()
      {
        theProcessingInstruction = XMLTypeFactory.eINSTANCE.createProcessingInstruction();
      }
    
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      protected ProcessingInstruction popProcessingInstruction()
      {
        pop();
        ProcessingInstruction resultProcessingInstructionValue = theProcessingInstruction;
        theProcessingInstruction = null;
        return resultProcessingInstructionValue;
      }
    
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SimpleAnyTypeStackFrame pushSimpleAnyType(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       SimpleAnyTypeStackFrame resultSimpleAnyType = simpleAnyType == null ? new SimpleAnyTypeStackFrame() : simpleAnyType;
       simpleAnyType = null;
       resultSimpleAnyType.pushOnto(previous);
       resultSimpleAnyType.handleAttributes(attributes);
       return resultSimpleAnyType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SimpleAnyType popSimpleAnyType(SimpleAnyTypeStackFrame simpleAnyType)
    {
      SimpleAnyType resultSimpleAnyTypeValue = simpleAnyType.popSimpleAnyType();
      this.simpleAnyType = simpleAnyType;
      return resultSimpleAnyTypeValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static class SimpleAnyTypeStackFrame extends XMLTypeResourceImpl.StackFrame
    {
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      protected SimpleAnyType theSimpleAnyType;
    
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      @Override
      public void handleAttributes(Attributes attributes)
      {
        // There are attributes to handle.
      }
    
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      @Override
      public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException
      {
        return super.startElement(namespace, localName, qName, attributes);
      }

      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      @Override
      public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException
      {
        super.endElement(child);
      }

      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      @Override
      public void create()
      {
        theSimpleAnyType = XMLTypeFactory.eINSTANCE.createSimpleAnyType();
      }
    
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      protected SimpleAnyType popSimpleAnyType()
      {
        pop();
        SimpleAnyType resultSimpleAnyTypeValue = theSimpleAnyType;
        theSimpleAnyType = null;
        return resultSimpleAnyTypeValue;
      }
    
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeDocumentRootStackFrame pushXMLTypeDocumentRoot(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeDocumentRootStackFrame resultXMLTypeDocumentRoot = xmlTypeDocumentRoot == null ? new XMLTypeDocumentRootStackFrame() : xmlTypeDocumentRoot;
       xmlTypeDocumentRoot = null;
       resultXMLTypeDocumentRoot.pushOnto(previous);
       resultXMLTypeDocumentRoot.handleAttributes(attributes);
       return resultXMLTypeDocumentRoot;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeDocumentRoot popXMLTypeDocumentRoot(XMLTypeDocumentRootStackFrame xmlTypeDocumentRoot)
    {
      XMLTypeDocumentRoot resultXMLTypeDocumentRootValue = xmlTypeDocumentRoot.popXMLTypeDocumentRoot();
      this.xmlTypeDocumentRoot = xmlTypeDocumentRoot;
      return resultXMLTypeDocumentRootValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static class XMLTypeDocumentRootStackFrame extends XMLTypeResourceImpl.StackFrame
    {
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      protected XMLTypeDocumentRoot theXMLTypeDocumentRoot;
    
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      protected XMLTypeResourceImpl.DataFrame cDATA;
    
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      protected XMLTypeResourceImpl.DataFrame comment;
    
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      protected XMLTypeResourceImpl.FrameFactory.ProcessingInstructionStackFrame processingInstruction;

      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      protected XMLTypeResourceImpl.DataFrame text;
    
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      @Override
      public void handleAttributes(Attributes attributes)
      {
        // There are attributes to handle.
      }
    
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated NOT
       */
      @Override
      public XMLTypeResourceImpl.StackFrame startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException
      {
        throw new UnsupportedOperationException();
      }

      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated NOT
       */
      @Override
      public void endElement(XMLTypeResourceImpl.StackFrame child) throws SAXException
      {
        throw new UnsupportedOperationException();
      }

      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      @Override
      public void create()
      {
        theXMLTypeDocumentRoot = XMLTypeFactory.eINSTANCE.createXMLTypeDocumentRoot();
      }
    
      /**
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      protected XMLTypeDocumentRoot popXMLTypeDocumentRoot()
      {
        pop();
        XMLTypeDocumentRoot resultXMLTypeDocumentRootValue = theXMLTypeDocumentRoot;
        theXMLTypeDocumentRoot = null;
        return resultXMLTypeDocumentRootValue;
      }
    
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushAnySimpleType(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultAnySimpleType = anySimpleType == null ? new XMLTypeResourceImpl.DataFrame() : anySimpleType;
       anySimpleType = null;
       resultAnySimpleType.pushOnto(previous);
       resultAnySimpleType.handleAttributes(attributes);
       return resultAnySimpleType;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Object popAnySimpleType(XMLTypeResourceImpl.DataFrame anySimpleType)
    {
      Object resultAnySimpleTypeValue = XMLTypeFactory.eINSTANCE.createAnySimpleType(anySimpleType.popValue());
      this.anySimpleType = anySimpleType;
      return resultAnySimpleTypeValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushAnyURI(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultAnyURI = anyURI == null ? new XMLTypeResourceImpl.DataFrame() : anyURI;
       anyURI = null;
       resultAnyURI.pushOnto(previous);
       resultAnyURI.handleAttributes(attributes);
       return resultAnyURI;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String popAnyURI(XMLTypeResourceImpl.DataFrame anyURI)
    {
      String resultAnyURIValue = XMLTypeFactory.eINSTANCE.createAnyURI(anyURI.popValue());
      this.anyURI = anyURI;
      return resultAnyURIValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushBase64Binary(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultBase64Binary = base64Binary == null ? new XMLTypeResourceImpl.DataFrame() : base64Binary;
       base64Binary = null;
       resultBase64Binary.pushOnto(previous);
       resultBase64Binary.handleAttributes(attributes);
       return resultBase64Binary;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public byte[] popBase64Binary(XMLTypeResourceImpl.DataFrame base64Binary)
    {
      byte[] resultBase64BinaryValue = XMLTypeFactory.eINSTANCE.createBase64Binary(base64Binary.popValue());
      this.base64Binary = base64Binary;
      return resultBase64BinaryValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushBoolean(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultBoolean = boolean_ == null ? new XMLTypeResourceImpl.DataFrame() : boolean_;
       boolean_ = null;
       resultBoolean.pushOnto(previous);
       resultBoolean.handleAttributes(attributes);
       return resultBoolean;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean popBoolean(XMLTypeResourceImpl.DataFrame boolean_)
    {
      boolean resultBooleanValue = XMLTypeFactory.eINSTANCE.createBoolean(boolean_.popValue());
      this.boolean_ = boolean_;
      return resultBooleanValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushBooleanObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultBooleanObject = booleanObject == null ? new XMLTypeResourceImpl.DataFrame() : booleanObject;
       booleanObject = null;
       resultBooleanObject.pushOnto(previous);
       resultBooleanObject.handleAttributes(attributes);
       return resultBooleanObject;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Boolean popBooleanObject(XMLTypeResourceImpl.DataFrame booleanObject)
    {
      Boolean resultBooleanObjectValue = XMLTypeFactory.eINSTANCE.createBooleanObject(booleanObject.popValue());
      this.booleanObject = booleanObject;
      return resultBooleanObjectValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushByte(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultByte = byte_ == null ? new XMLTypeResourceImpl.DataFrame() : byte_;
       byte_ = null;
       resultByte.pushOnto(previous);
       resultByte.handleAttributes(attributes);
       return resultByte;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public byte popByte(XMLTypeResourceImpl.DataFrame byte_)
    {
      byte resultByteValue = XMLTypeFactory.eINSTANCE.createByte(byte_.popValue());
      this.byte_ = byte_;
      return resultByteValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushByteObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultByteObject = byteObject == null ? new XMLTypeResourceImpl.DataFrame() : byteObject;
       byteObject = null;
       resultByteObject.pushOnto(previous);
       resultByteObject.handleAttributes(attributes);
       return resultByteObject;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Byte popByteObject(XMLTypeResourceImpl.DataFrame byteObject)
    {
      Byte resultByteObjectValue = XMLTypeFactory.eINSTANCE.createByteObject(byteObject.popValue());
      this.byteObject = byteObject;
      return resultByteObjectValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushDate(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultDate = date == null ? new XMLTypeResourceImpl.DataFrame() : date;
       date = null;
       resultDate.pushOnto(previous);
       resultDate.handleAttributes(attributes);
       return resultDate;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLGregorianCalendar popDate(XMLTypeResourceImpl.DataFrame date)
    {
      XMLGregorianCalendar resultDateValue = XMLTypeFactory.eINSTANCE.createDate(date.popValue());
      this.date = date;
      return resultDateValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushDateTime(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultDateTime = dateTime == null ? new XMLTypeResourceImpl.DataFrame() : dateTime;
       dateTime = null;
       resultDateTime.pushOnto(previous);
       resultDateTime.handleAttributes(attributes);
       return resultDateTime;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLGregorianCalendar popDateTime(XMLTypeResourceImpl.DataFrame dateTime)
    {
      XMLGregorianCalendar resultDateTimeValue = XMLTypeFactory.eINSTANCE.createDateTime(dateTime.popValue());
      this.dateTime = dateTime;
      return resultDateTimeValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushDecimal(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultDecimal = decimal == null ? new XMLTypeResourceImpl.DataFrame() : decimal;
       decimal = null;
       resultDecimal.pushOnto(previous);
       resultDecimal.handleAttributes(attributes);
       return resultDecimal;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigDecimal popDecimal(XMLTypeResourceImpl.DataFrame decimal)
    {
      BigDecimal resultDecimalValue = XMLTypeFactory.eINSTANCE.createDecimal(decimal.popValue());
      this.decimal = decimal;
      return resultDecimalValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushDouble(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultDouble = double_ == null ? new XMLTypeResourceImpl.DataFrame() : double_;
       double_ = null;
       resultDouble.pushOnto(previous);
       resultDouble.handleAttributes(attributes);
       return resultDouble;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public double popDouble(XMLTypeResourceImpl.DataFrame double_)
    {
      double resultDoubleValue = XMLTypeFactory.eINSTANCE.createDouble(double_.popValue());
      this.double_ = double_;
      return resultDoubleValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushDoubleObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultDoubleObject = doubleObject == null ? new XMLTypeResourceImpl.DataFrame() : doubleObject;
       doubleObject = null;
       resultDoubleObject.pushOnto(previous);
       resultDoubleObject.handleAttributes(attributes);
       return resultDoubleObject;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Double popDoubleObject(XMLTypeResourceImpl.DataFrame doubleObject)
    {
      Double resultDoubleObjectValue = XMLTypeFactory.eINSTANCE.createDoubleObject(doubleObject.popValue());
      this.doubleObject = doubleObject;
      return resultDoubleObjectValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushDuration(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultDuration = duration == null ? new XMLTypeResourceImpl.DataFrame() : duration;
       duration = null;
       resultDuration.pushOnto(previous);
       resultDuration.handleAttributes(attributes);
       return resultDuration;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Duration popDuration(XMLTypeResourceImpl.DataFrame duration)
    {
      Duration resultDurationValue = XMLTypeFactory.eINSTANCE.createDuration(duration.popValue());
      this.duration = duration;
      return resultDurationValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushENTITIES(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultENTITIES = entities == null ? new XMLTypeResourceImpl.DataFrame() : entities;
       entities = null;
       resultENTITIES.pushOnto(previous);
       resultENTITIES.handleAttributes(attributes);
       return resultENTITIES;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<String> popENTITIES(XMLTypeResourceImpl.DataFrame entities)
    {
      List<String> resultENTITIESValue = XMLTypeFactory.eINSTANCE.createENTITIES(entities.popValue());
      this.entities = entities;
      return resultENTITIESValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushENTITIESBase(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultENTITIESBase = entitiesBase == null ? new XMLTypeResourceImpl.DataFrame() : entitiesBase;
       entitiesBase = null;
       resultENTITIESBase.pushOnto(previous);
       resultENTITIESBase.handleAttributes(attributes);
       return resultENTITIESBase;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<String> popENTITIESBase(XMLTypeResourceImpl.DataFrame entitiesBase)
    {
      List<String> resultENTITIESBaseValue = XMLTypeFactory.eINSTANCE.createENTITIESBase(entitiesBase.popValue());
      this.entitiesBase = entitiesBase;
      return resultENTITIESBaseValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushENTITY(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultENTITY = entity == null ? new XMLTypeResourceImpl.DataFrame() : entity;
       entity = null;
       resultENTITY.pushOnto(previous);
       resultENTITY.handleAttributes(attributes);
       return resultENTITY;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String popENTITY(XMLTypeResourceImpl.DataFrame entity)
    {
      String resultENTITYValue = XMLTypeFactory.eINSTANCE.createENTITY(entity.popValue());
      this.entity = entity;
      return resultENTITYValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushFloat(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultFloat = float_ == null ? new XMLTypeResourceImpl.DataFrame() : float_;
       float_ = null;
       resultFloat.pushOnto(previous);
       resultFloat.handleAttributes(attributes);
       return resultFloat;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public float popFloat(XMLTypeResourceImpl.DataFrame float_)
    {
      float resultFloatValue = XMLTypeFactory.eINSTANCE.createFloat(float_.popValue());
      this.float_ = float_;
      return resultFloatValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushFloatObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultFloatObject = floatObject == null ? new XMLTypeResourceImpl.DataFrame() : floatObject;
       floatObject = null;
       resultFloatObject.pushOnto(previous);
       resultFloatObject.handleAttributes(attributes);
       return resultFloatObject;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Float popFloatObject(XMLTypeResourceImpl.DataFrame floatObject)
    {
      Float resultFloatObjectValue = XMLTypeFactory.eINSTANCE.createFloatObject(floatObject.popValue());
      this.floatObject = floatObject;
      return resultFloatObjectValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushGDay(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultGDay = gDay == null ? new XMLTypeResourceImpl.DataFrame() : gDay;
       gDay = null;
       resultGDay.pushOnto(previous);
       resultGDay.handleAttributes(attributes);
       return resultGDay;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLGregorianCalendar popGDay(XMLTypeResourceImpl.DataFrame gDay)
    {
      XMLGregorianCalendar resultGDayValue = XMLTypeFactory.eINSTANCE.createGDay(gDay.popValue());
      this.gDay = gDay;
      return resultGDayValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushGMonth(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultGMonth = gMonth == null ? new XMLTypeResourceImpl.DataFrame() : gMonth;
       gMonth = null;
       resultGMonth.pushOnto(previous);
       resultGMonth.handleAttributes(attributes);
       return resultGMonth;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLGregorianCalendar popGMonth(XMLTypeResourceImpl.DataFrame gMonth)
    {
      XMLGregorianCalendar resultGMonthValue = XMLTypeFactory.eINSTANCE.createGMonth(gMonth.popValue());
      this.gMonth = gMonth;
      return resultGMonthValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushGMonthDay(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultGMonthDay = gMonthDay == null ? new XMLTypeResourceImpl.DataFrame() : gMonthDay;
       gMonthDay = null;
       resultGMonthDay.pushOnto(previous);
       resultGMonthDay.handleAttributes(attributes);
       return resultGMonthDay;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLGregorianCalendar popGMonthDay(XMLTypeResourceImpl.DataFrame gMonthDay)
    {
      XMLGregorianCalendar resultGMonthDayValue = XMLTypeFactory.eINSTANCE.createGMonthDay(gMonthDay.popValue());
      this.gMonthDay = gMonthDay;
      return resultGMonthDayValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushGYear(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultGYear = gYear == null ? new XMLTypeResourceImpl.DataFrame() : gYear;
       gYear = null;
       resultGYear.pushOnto(previous);
       resultGYear.handleAttributes(attributes);
       return resultGYear;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLGregorianCalendar popGYear(XMLTypeResourceImpl.DataFrame gYear)
    {
      XMLGregorianCalendar resultGYearValue = XMLTypeFactory.eINSTANCE.createGYear(gYear.popValue());
      this.gYear = gYear;
      return resultGYearValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushGYearMonth(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultGYearMonth = gYearMonth == null ? new XMLTypeResourceImpl.DataFrame() : gYearMonth;
       gYearMonth = null;
       resultGYearMonth.pushOnto(previous);
       resultGYearMonth.handleAttributes(attributes);
       return resultGYearMonth;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLGregorianCalendar popGYearMonth(XMLTypeResourceImpl.DataFrame gYearMonth)
    {
      XMLGregorianCalendar resultGYearMonthValue = XMLTypeFactory.eINSTANCE.createGYearMonth(gYearMonth.popValue());
      this.gYearMonth = gYearMonth;
      return resultGYearMonthValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushHexBinary(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultHexBinary = hexBinary == null ? new XMLTypeResourceImpl.DataFrame() : hexBinary;
       hexBinary = null;
       resultHexBinary.pushOnto(previous);
       resultHexBinary.handleAttributes(attributes);
       return resultHexBinary;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public byte[] popHexBinary(XMLTypeResourceImpl.DataFrame hexBinary)
    {
      byte[] resultHexBinaryValue = XMLTypeFactory.eINSTANCE.createHexBinary(hexBinary.popValue());
      this.hexBinary = hexBinary;
      return resultHexBinaryValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushID(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultID = id == null ? new XMLTypeResourceImpl.DataFrame() : id;
       id = null;
       resultID.pushOnto(previous);
       resultID.handleAttributes(attributes);
       return resultID;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String popID(XMLTypeResourceImpl.DataFrame id)
    {
      String resultIDValue = XMLTypeFactory.eINSTANCE.createID(id.popValue());
      this.id = id;
      return resultIDValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushIDREF(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultIDREF = idref == null ? new XMLTypeResourceImpl.DataFrame() : idref;
       idref = null;
       resultIDREF.pushOnto(previous);
       resultIDREF.handleAttributes(attributes);
       return resultIDREF;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String popIDREF(XMLTypeResourceImpl.DataFrame idref)
    {
      String resultIDREFValue = XMLTypeFactory.eINSTANCE.createIDREF(idref.popValue());
      this.idref = idref;
      return resultIDREFValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushIDREFS(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultIDREFS = idrefs == null ? new XMLTypeResourceImpl.DataFrame() : idrefs;
       idrefs = null;
       resultIDREFS.pushOnto(previous);
       resultIDREFS.handleAttributes(attributes);
       return resultIDREFS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<String> popIDREFS(XMLTypeResourceImpl.DataFrame idrefs)
    {
      List<String> resultIDREFSValue = XMLTypeFactory.eINSTANCE.createIDREFS(idrefs.popValue());
      this.idrefs = idrefs;
      return resultIDREFSValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushIDREFSBase(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultIDREFSBase = idrefsBase == null ? new XMLTypeResourceImpl.DataFrame() : idrefsBase;
       idrefsBase = null;
       resultIDREFSBase.pushOnto(previous);
       resultIDREFSBase.handleAttributes(attributes);
       return resultIDREFSBase;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<String> popIDREFSBase(XMLTypeResourceImpl.DataFrame idrefsBase)
    {
      List<String> resultIDREFSBaseValue = XMLTypeFactory.eINSTANCE.createIDREFSBase(idrefsBase.popValue());
      this.idrefsBase = idrefsBase;
      return resultIDREFSBaseValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushInt(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultInt = int_ == null ? new XMLTypeResourceImpl.DataFrame() : int_;
       int_ = null;
       resultInt.pushOnto(previous);
       resultInt.handleAttributes(attributes);
       return resultInt;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int popInt(XMLTypeResourceImpl.DataFrame int_)
    {
      int resultIntValue = XMLTypeFactory.eINSTANCE.createInt(int_.popValue());
      this.int_ = int_;
      return resultIntValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushInteger(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultInteger = integer == null ? new XMLTypeResourceImpl.DataFrame() : integer;
       integer = null;
       resultInteger.pushOnto(previous);
       resultInteger.handleAttributes(attributes);
       return resultInteger;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger popInteger(XMLTypeResourceImpl.DataFrame integer)
    {
      BigInteger resultIntegerValue = XMLTypeFactory.eINSTANCE.createInteger(integer.popValue());
      this.integer = integer;
      return resultIntegerValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushIntObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultIntObject = intObject == null ? new XMLTypeResourceImpl.DataFrame() : intObject;
       intObject = null;
       resultIntObject.pushOnto(previous);
       resultIntObject.handleAttributes(attributes);
       return resultIntObject;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Integer popIntObject(XMLTypeResourceImpl.DataFrame intObject)
    {
      Integer resultIntObjectValue = XMLTypeFactory.eINSTANCE.createIntObject(intObject.popValue());
      this.intObject = intObject;
      return resultIntObjectValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushLanguage(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultLanguage = language == null ? new XMLTypeResourceImpl.DataFrame() : language;
       language = null;
       resultLanguage.pushOnto(previous);
       resultLanguage.handleAttributes(attributes);
       return resultLanguage;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String popLanguage(XMLTypeResourceImpl.DataFrame language)
    {
      String resultLanguageValue = XMLTypeFactory.eINSTANCE.createLanguage(language.popValue());
      this.language = language;
      return resultLanguageValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushLong(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultLong = long_ == null ? new XMLTypeResourceImpl.DataFrame() : long_;
       long_ = null;
       resultLong.pushOnto(previous);
       resultLong.handleAttributes(attributes);
       return resultLong;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public long popLong(XMLTypeResourceImpl.DataFrame long_)
    {
      long resultLongValue = XMLTypeFactory.eINSTANCE.createLong(long_.popValue());
      this.long_ = long_;
      return resultLongValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushLongObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultLongObject = longObject == null ? new XMLTypeResourceImpl.DataFrame() : longObject;
       longObject = null;
       resultLongObject.pushOnto(previous);
       resultLongObject.handleAttributes(attributes);
       return resultLongObject;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Long popLongObject(XMLTypeResourceImpl.DataFrame longObject)
    {
      Long resultLongObjectValue = XMLTypeFactory.eINSTANCE.createLongObject(longObject.popValue());
      this.longObject = longObject;
      return resultLongObjectValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushName(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultName = name == null ? new XMLTypeResourceImpl.DataFrame() : name;
       name = null;
       resultName.pushOnto(previous);
       resultName.handleAttributes(attributes);
       return resultName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String popName(XMLTypeResourceImpl.DataFrame name)
    {
      String resultNameValue = XMLTypeFactory.eINSTANCE.createName(name.popValue());
      this.name = name;
      return resultNameValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushNCName(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultNCName = ncName == null ? new XMLTypeResourceImpl.DataFrame() : ncName;
       ncName = null;
       resultNCName.pushOnto(previous);
       resultNCName.handleAttributes(attributes);
       return resultNCName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String popNCName(XMLTypeResourceImpl.DataFrame ncName)
    {
      String resultNCNameValue = XMLTypeFactory.eINSTANCE.createNCName(ncName.popValue());
      this.ncName = ncName;
      return resultNCNameValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushNegativeInteger(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultNegativeInteger = negativeInteger == null ? new XMLTypeResourceImpl.DataFrame() : negativeInteger;
       negativeInteger = null;
       resultNegativeInteger.pushOnto(previous);
       resultNegativeInteger.handleAttributes(attributes);
       return resultNegativeInteger;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger popNegativeInteger(XMLTypeResourceImpl.DataFrame negativeInteger)
    {
      BigInteger resultNegativeIntegerValue = XMLTypeFactory.eINSTANCE.createNegativeInteger(negativeInteger.popValue());
      this.negativeInteger = negativeInteger;
      return resultNegativeIntegerValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushNMTOKEN(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultNMTOKEN = nmtoken == null ? new XMLTypeResourceImpl.DataFrame() : nmtoken;
       nmtoken = null;
       resultNMTOKEN.pushOnto(previous);
       resultNMTOKEN.handleAttributes(attributes);
       return resultNMTOKEN;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String popNMTOKEN(XMLTypeResourceImpl.DataFrame nmtoken)
    {
      String resultNMTOKENValue = XMLTypeFactory.eINSTANCE.createNMTOKEN(nmtoken.popValue());
      this.nmtoken = nmtoken;
      return resultNMTOKENValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushNMTOKENS(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultNMTOKENS = nmtokens == null ? new XMLTypeResourceImpl.DataFrame() : nmtokens;
       nmtokens = null;
       resultNMTOKENS.pushOnto(previous);
       resultNMTOKENS.handleAttributes(attributes);
       return resultNMTOKENS;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<String> popNMTOKENS(XMLTypeResourceImpl.DataFrame nmtokens)
    {
      List<String> resultNMTOKENSValue = XMLTypeFactory.eINSTANCE.createNMTOKENS(nmtokens.popValue());
      this.nmtokens = nmtokens;
      return resultNMTOKENSValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushNMTOKENSBase(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultNMTOKENSBase = nmtokensBase == null ? new XMLTypeResourceImpl.DataFrame() : nmtokensBase;
       nmtokensBase = null;
       resultNMTOKENSBase.pushOnto(previous);
       resultNMTOKENSBase.handleAttributes(attributes);
       return resultNMTOKENSBase;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public List<String> popNMTOKENSBase(XMLTypeResourceImpl.DataFrame nmtokensBase)
    {
      List<String> resultNMTOKENSBaseValue = XMLTypeFactory.eINSTANCE.createNMTOKENSBase(nmtokensBase.popValue());
      this.nmtokensBase = nmtokensBase;
      return resultNMTOKENSBaseValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushNonNegativeInteger(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultNonNegativeInteger = nonNegativeInteger == null ? new XMLTypeResourceImpl.DataFrame() : nonNegativeInteger;
       nonNegativeInteger = null;
       resultNonNegativeInteger.pushOnto(previous);
       resultNonNegativeInteger.handleAttributes(attributes);
       return resultNonNegativeInteger;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger popNonNegativeInteger(XMLTypeResourceImpl.DataFrame nonNegativeInteger)
    {
      BigInteger resultNonNegativeIntegerValue = XMLTypeFactory.eINSTANCE.createNonNegativeInteger(nonNegativeInteger.popValue());
      this.nonNegativeInteger = nonNegativeInteger;
      return resultNonNegativeIntegerValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushNonPositiveInteger(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultNonPositiveInteger = nonPositiveInteger == null ? new XMLTypeResourceImpl.DataFrame() : nonPositiveInteger;
       nonPositiveInteger = null;
       resultNonPositiveInteger.pushOnto(previous);
       resultNonPositiveInteger.handleAttributes(attributes);
       return resultNonPositiveInteger;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger popNonPositiveInteger(XMLTypeResourceImpl.DataFrame nonPositiveInteger)
    {
      BigInteger resultNonPositiveIntegerValue = XMLTypeFactory.eINSTANCE.createNonPositiveInteger(nonPositiveInteger.popValue());
      this.nonPositiveInteger = nonPositiveInteger;
      return resultNonPositiveIntegerValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushNormalizedString(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultNormalizedString = normalizedString == null ? new XMLTypeResourceImpl.DataFrame() : normalizedString;
       normalizedString = null;
       resultNormalizedString.pushOnto(previous);
       resultNormalizedString.handleAttributes(attributes);
       return resultNormalizedString;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String popNormalizedString(XMLTypeResourceImpl.DataFrame normalizedString)
    {
      String resultNormalizedStringValue = XMLTypeFactory.eINSTANCE.createNormalizedString(normalizedString.popValue());
      this.normalizedString = normalizedString;
      return resultNormalizedStringValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushNOTATION(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultNOTATION = notation == null ? new XMLTypeResourceImpl.DataFrame() : notation;
       notation = null;
       resultNOTATION.pushOnto(previous);
       resultNOTATION.handleAttributes(attributes);
       return resultNOTATION;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QName popNOTATION(XMLTypeResourceImpl.DataFrame notation)
    {
      QName resultNOTATIONValue = XMLTypeFactory.eINSTANCE.createNOTATION(notation.popValue());
      this.notation = notation;
      return resultNOTATIONValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushPositiveInteger(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultPositiveInteger = positiveInteger == null ? new XMLTypeResourceImpl.DataFrame() : positiveInteger;
       positiveInteger = null;
       resultPositiveInteger.pushOnto(previous);
       resultPositiveInteger.handleAttributes(attributes);
       return resultPositiveInteger;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger popPositiveInteger(XMLTypeResourceImpl.DataFrame positiveInteger)
    {
      BigInteger resultPositiveIntegerValue = XMLTypeFactory.eINSTANCE.createPositiveInteger(positiveInteger.popValue());
      this.positiveInteger = positiveInteger;
      return resultPositiveIntegerValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushQName(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultQName = qName == null ? new XMLTypeResourceImpl.DataFrame() : qName;
       qName = null;
       resultQName.pushOnto(previous);
       resultQName.handleAttributes(attributes);
       return resultQName;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public QName popQName(XMLTypeResourceImpl.DataFrame qName)
    {
      QName resultQNameValue = XMLTypeFactory.eINSTANCE.createQName(qName.popValue());
      this.qName = qName;
      return resultQNameValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushShort(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultShort = short_ == null ? new XMLTypeResourceImpl.DataFrame() : short_;
       short_ = null;
       resultShort.pushOnto(previous);
       resultShort.handleAttributes(attributes);
       return resultShort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public short popShort(XMLTypeResourceImpl.DataFrame short_)
    {
      short resultShortValue = XMLTypeFactory.eINSTANCE.createShort(short_.popValue());
      this.short_ = short_;
      return resultShortValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushShortObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultShortObject = shortObject == null ? new XMLTypeResourceImpl.DataFrame() : shortObject;
       shortObject = null;
       resultShortObject.pushOnto(previous);
       resultShortObject.handleAttributes(attributes);
       return resultShortObject;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Short popShortObject(XMLTypeResourceImpl.DataFrame shortObject)
    {
      Short resultShortObjectValue = XMLTypeFactory.eINSTANCE.createShortObject(shortObject.popValue());
      this.shortObject = shortObject;
      return resultShortObjectValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushString(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultString = string == null ? new XMLTypeResourceImpl.DataFrame() : string;
       string = null;
       resultString.pushOnto(previous);
       resultString.handleAttributes(attributes);
       return resultString;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated NOT
     */
    public String popString(XMLTypeResourceImpl.DataFrame string)
    {
      String resultStringValue = string.popValue();
      this.string = string;
      return resultStringValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushTime(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultTime = time == null ? new XMLTypeResourceImpl.DataFrame() : time;
       time = null;
       resultTime.pushOnto(previous);
       resultTime.handleAttributes(attributes);
       return resultTime;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLGregorianCalendar popTime(XMLTypeResourceImpl.DataFrame time)
    {
      XMLGregorianCalendar resultTimeValue = XMLTypeFactory.eINSTANCE.createTime(time.popValue());
      this.time = time;
      return resultTimeValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushToken(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultToken = token == null ? new XMLTypeResourceImpl.DataFrame() : token;
       token = null;
       resultToken.pushOnto(previous);
       resultToken.handleAttributes(attributes);
       return resultToken;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String popToken(XMLTypeResourceImpl.DataFrame token)
    {
      String resultTokenValue = XMLTypeFactory.eINSTANCE.createToken(token.popValue());
      this.token = token;
      return resultTokenValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushUnsignedByte(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultUnsignedByte = unsignedByte == null ? new XMLTypeResourceImpl.DataFrame() : unsignedByte;
       unsignedByte = null;
       resultUnsignedByte.pushOnto(previous);
       resultUnsignedByte.handleAttributes(attributes);
       return resultUnsignedByte;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public short popUnsignedByte(XMLTypeResourceImpl.DataFrame unsignedByte)
    {
      short resultUnsignedByteValue = XMLTypeFactory.eINSTANCE.createUnsignedByte(unsignedByte.popValue());
      this.unsignedByte = unsignedByte;
      return resultUnsignedByteValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushUnsignedByteObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultUnsignedByteObject = unsignedByteObject == null ? new XMLTypeResourceImpl.DataFrame() : unsignedByteObject;
       unsignedByteObject = null;
       resultUnsignedByteObject.pushOnto(previous);
       resultUnsignedByteObject.handleAttributes(attributes);
       return resultUnsignedByteObject;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Short popUnsignedByteObject(XMLTypeResourceImpl.DataFrame unsignedByteObject)
    {
      Short resultUnsignedByteObjectValue = XMLTypeFactory.eINSTANCE.createUnsignedByteObject(unsignedByteObject.popValue());
      this.unsignedByteObject = unsignedByteObject;
      return resultUnsignedByteObjectValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushUnsignedInt(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultUnsignedInt = unsignedInt == null ? new XMLTypeResourceImpl.DataFrame() : unsignedInt;
       unsignedInt = null;
       resultUnsignedInt.pushOnto(previous);
       resultUnsignedInt.handleAttributes(attributes);
       return resultUnsignedInt;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public long popUnsignedInt(XMLTypeResourceImpl.DataFrame unsignedInt)
    {
      long resultUnsignedIntValue = XMLTypeFactory.eINSTANCE.createUnsignedInt(unsignedInt.popValue());
      this.unsignedInt = unsignedInt;
      return resultUnsignedIntValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushUnsignedIntObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultUnsignedIntObject = unsignedIntObject == null ? new XMLTypeResourceImpl.DataFrame() : unsignedIntObject;
       unsignedIntObject = null;
       resultUnsignedIntObject.pushOnto(previous);
       resultUnsignedIntObject.handleAttributes(attributes);
       return resultUnsignedIntObject;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Long popUnsignedIntObject(XMLTypeResourceImpl.DataFrame unsignedIntObject)
    {
      Long resultUnsignedIntObjectValue = XMLTypeFactory.eINSTANCE.createUnsignedIntObject(unsignedIntObject.popValue());
      this.unsignedIntObject = unsignedIntObject;
      return resultUnsignedIntObjectValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushUnsignedLong(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultUnsignedLong = unsignedLong == null ? new XMLTypeResourceImpl.DataFrame() : unsignedLong;
       unsignedLong = null;
       resultUnsignedLong.pushOnto(previous);
       resultUnsignedLong.handleAttributes(attributes);
       return resultUnsignedLong;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BigInteger popUnsignedLong(XMLTypeResourceImpl.DataFrame unsignedLong)
    {
      BigInteger resultUnsignedLongValue = XMLTypeFactory.eINSTANCE.createUnsignedLong(unsignedLong.popValue());
      this.unsignedLong = unsignedLong;
      return resultUnsignedLongValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushUnsignedShort(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultUnsignedShort = unsignedShort == null ? new XMLTypeResourceImpl.DataFrame() : unsignedShort;
       unsignedShort = null;
       resultUnsignedShort.pushOnto(previous);
       resultUnsignedShort.handleAttributes(attributes);
       return resultUnsignedShort;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int popUnsignedShort(XMLTypeResourceImpl.DataFrame unsignedShort)
    {
      int resultUnsignedShortValue = XMLTypeFactory.eINSTANCE.createUnsignedShort(unsignedShort.popValue());
      this.unsignedShort = unsignedShort;
      return resultUnsignedShortValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public XMLTypeResourceImpl.DataFrame pushUnsignedShortObject(XMLTypeResourceImpl.StackFrame previous, Attributes attributes)
    {
       XMLTypeResourceImpl.DataFrame resultUnsignedShortObject = unsignedShortObject == null ? new XMLTypeResourceImpl.DataFrame() : unsignedShortObject;
       unsignedShortObject = null;
       resultUnsignedShortObject.pushOnto(previous);
       resultUnsignedShortObject.handleAttributes(attributes);
       return resultUnsignedShortObject;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public Integer popUnsignedShortObject(XMLTypeResourceImpl.DataFrame unsignedShortObject)
    {
      Integer resultUnsignedShortObjectValue = XMLTypeFactory.eINSTANCE.createUnsignedShortObject(unsignedShortObject.popValue());
      this.unsignedShortObject = unsignedShortObject;
      return resultUnsignedShortObjectValue;
    }

  }

  public static abstract class StackFrame
  {
    private StackFrame previous;
    
    final public void pushOnto(StackFrame previous)
    {
      this.previous = previous;
      create();
    }
    
    final public void pop()
    {
      this.previous = null;
    }
    
    public void handleAttributes(Attributes attributes)
    {
      // Do nothing.
    }
    
    public StackFrame startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
      throw new SAXException("Unexpected start element");
    }
    
    public void endElement(StackFrame child) throws SAXException
    {
      throw new SAXException("Unexpected end element");
    }
    
    final public StackFrame endElement() throws SAXException
    {
      StackFrame result = previous;
      previous.endElement(this);
      return result;
    }
    
    public void characters(char[] text, int start, int length) throws SAXException
    {
      // Do nothing.
    }
    
    public void create()
    {
      // Do nothing.
    }
  }

  public static class DataFrame extends StackFrame
  {
    protected StringBuffer stringBuffer;
    
    @Override
    public void characters(char[] text, int start, int length) throws SAXException
    {
      if (stringBuffer == null)
      {
        stringBuffer = new StringBuffer();
      }
      stringBuffer.append(text, start, length);
    }
    
    public String popValue()
    {
      if (stringBuffer == null)
      {
        pop();
        return null;
      }
      else
      {
        String result = stringBuffer.toString();
        stringBuffer.setLength(0);
        pop();
        return result;
      }
    }
  }
  
  public static class Handler extends DefaultHandler
  {
    protected StackFrame stackFrame = null;
    
    public Handler(StackFrame stackFrame)
    {
      this.stackFrame = stackFrame;
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
      stackFrame = stackFrame.startElement(uri, localName, qName, attributes);
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
      stackFrame = stackFrame.endElement();
    }
    
    @Override
    public void characters(char[] text, int start, int length) throws SAXException
    {
      stackFrame.characters(text, start, length);
    }

    @Override
    public void error(SAXParseException exception) throws SAXException
    {
      // Ignore.
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException
    {
      // Ignore.
    }
  }
 
} //XMLTypeResourceImpl
