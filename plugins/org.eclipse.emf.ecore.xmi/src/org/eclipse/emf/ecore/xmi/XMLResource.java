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
 * $Id: XMLResource.java,v 1.2 2004/03/29 21:29:56 elena Exp $
 */
package org.eclipse.emf.ecore.xmi;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * This interface represents an XML resource. You can use it to load
 * and save XML files. This initial implementation of XML serialization
 * and deserialization is incomplete but will be completed shortly.
 */
public interface XMLResource extends Resource
{
  /**
   * Specify a parser pool to be used for loading XML documents.
   * You should provide an XMLParserPool as the value of this option.
   * @see org.eclipse.emf.ecore.xmi.XMLParserPool
   */
  String OPTION_USE_PARSER_POOL = "USE_PARSER_POOL";
  
  /**
   * Specify parser features with their corresponding values, 
   * i.e., <code>true</code> or <code>false</code> using {@link Map}.
   */
  String OPTION_PARSER_FEATURES = "PARSER_FEATURES";
  
  /**
   * Specify parser properties with their corresponding values using a {@link Map}.
   */
  String OPTION_PARSER_PROPERTIES = "PARSER_PROPERTIES";
  
  /**
   * Determines whether comments and CDATA will be preserved in any mixed text processing.
   */
  String OPTION_USE_LEXICAL_HANDLER = "USE_LEXICAL_HANDLER";

  /**
   * This option allows you to tailor the XML serialization of objects. 
   * You should provide an ExtendedMetaData as the value of this option.
   */
  String OPTION_EXTENDED_META_DATA = "EXTENDED_META_DATA";

  /**
   * This save option allows you to tailor the XML serialization of
   * objects. You should provide an XMLMap as the value of this option.
   */
  String OPTION_XML_MAP = "XML_MAP";

  /**
   * All EObject references will be uniformly encoded as one or more QName URI pairs,
   * where the QName is optional depending on whether the referenced object's type is not identicle to that of the feature.
   */
  String OPTION_USE_ENCODED_ATTRIBUTE_STYLE = "USE_ENCODED_ATTRIBUTE_STYLE";

  /**
   * Break lines and add indentation to produce a readable document.
   * The default is <code>Boolean.TRUE</code> unless set to <code>Boolean.FALSE</code> explicitly.
   */
  String OPTION_FORMATTED = "FORMATTED";

  /**
   * Attribute formatting that exceeds the specified width will cause a line break
   * so that formatting will continue indented on the next line.
   */
  String OPTION_LINE_WIDTH = "LINE_WIDTH";


  /**
   * Begin a save with this line:
   * <?xml version="1.0" encoding="encoding"?>
   */
  String OPTION_DECLARE_XML = "DECLARE_XML";

  /**
   * Skip processing for values that contain characters special to XML
   * Faster for large computer-generated files
   */
  String OPTION_SKIP_ESCAPE  = "SKIP_ESCAPE";

  /**
   * This can be one of "THROW", "DISCARD", "RECORD", where "THROW" is the default.
   */
  String OPTION_PROCESS_DANGLING_HREF          = "PROCESS_DANGLING_HREF";
  String OPTION_PROCESS_DANGLING_HREF_THROW    = "THROW";
  String OPTION_PROCESS_DANGLING_HREF_DISCARD  = "DISCARD";
  String OPTION_PROCESS_DANGLING_HREF_RECORD   = "RECORD";

  /**
   * Disable notifications during load, and enable them at the
   * end of a load.
   */
  String OPTION_DISABLE_NOTIFY = "DISABLE_NOTIFY";

  /**
   * Produce an xsi:schemaLocation/xsi:noNamespaceSchemaLocation in the saved result.
   */
  String OPTION_SCHEMA_LOCATION = "SCHEMA_LOCATION";

  /**
   * Specify the XML encoding to be used during save.
   */
  String OPTION_ENCODING  = "ENCODING";

  String HREF = "href";
  String NIL = "nil";
  String TYPE = "type";
  String SCHEMA_LOCATION = "schemaLocation";
  String NO_NAMESPACE_SCHEMA_LOCATION = "noNamespaceSchemaLocation";

  String XML_NS = "xmlns";

  String XSI_NS = "xsi";
  String XSI_URI = "http://www.w3.org/2001/XMLSchema-instance";

  /**
   * Returns whether the contents will be zipped.
   */
  boolean useZip();

  /**
   * Set whether the contents will be zipped.
   */
  void setUseZip(boolean useZip);

  /**
   * Returns the map of options that, in addition to the overriding options specified during save,
   * are used to to control save behavior.
   */
  Map getDefaultSaveOptions();

  /**
   * Returns the map of options that, in addition to the overriding options specified during load,
   * are used to to control load behavior.
   */
  Map getDefaultLoadOptions();

  /**
   * Get the XML encoding for this resource. The default is
   * ASCII.
   */
  String getEncoding();

  /**
   * Set the XML encoding for this resource.
   */
  void setEncoding(String encoding);

  /**
   * Returns the Map with IDs as keys and EObjects as values.
   */
  Map getIDToEObjectMap();

  /**
   * Returns the Map of EObjects as keys and IDs as values.
   */
  Map getEObjectToIDMap();

  /**
   * Returns the ID if there is one for the given object; if there is
   * no ID, it returns null.
   */
  String getID(EObject eObject);

  /**
   * Sets the ID for the given object. If you are storing the ID
   * with the object itself, you should override this method.
   */
  void setID(EObject eObject, String id);

  /**
   * This interface represents a mapping from Ecore constructs to the
   * XML representation of those constructs. It is used by the
   * XML serializer and deserializer to load and save XML files.
   */
  public interface XMLMap
  {
    /**
     * Add an XMLInfo object for an Ecore construct to
     * the map.
     */
    void add(ENamedElement element, XMLInfo info);

    /**
     * Returns the XMLInfo object for the given Ecore
     * construct, if there is one.
     */
    XMLInfo getInfo(ENamedElement element);

    /**
     * Sets the package to use when namespaces are not used in an
     * XML file; this affects loading only.
     */
    void setNoNamespacePackage(EPackage pkg);

    /**
     * Gets the package to use when namespaces are not used.
     */
    EPackage getNoNamespacePackage();

    /**
     * Sets the name of the XML attribute that holds IDs.
     */
    void setIDAttributeName(String name);

    /**
     * Gets the name of the XML attribute that holds IDs.
     */
    String getIDAttributeName();

    /**
     * Returns the EClassifier with the given namespace URI and
     * name.
     */
    EClassifier getClassifier(String namespaceURI, String name);

    /**
     * Returns the feature for the given class with the given
     * namespace URI and name.
     */
    EStructuralFeature getFeature(EClass eClass, String namespaceURI, String name);

    /**
     * Returns the list of features for the given class in the order in which they should be saved.
     */
    List getFeatures(EClass eClass);
  }

  /**
   * This interface is used with the XMLMap interface to describe how
   * to serialize objects and features. You can specify the name
   * to use instead of the model name, whether a feature will be
   * serialized as an XML attribute, XML element, or XML content,
   * and whether to use a namespace when serializing an object.
   * <p>
   * The XMLMap interface maintains the association between XMLInfo
   * objects and the ecore constructs they describe.
   */
  public interface XMLInfo
  {
    /**
     * These constants are used to specify the XML representation
     * of an Ecore construct.
     */
    int UNSPECIFIED = -1;
    int ELEMENT = 0;
    int ATTRIBUTE = 1;
    int CONTENT = 2;

    /**
     * Returns ELEMENT if the Ecore construct is to be serialized
     * as an XML element; ATTRIBUTE if the Ecore construct is
     * to be serialized as an XML attribute; and CONTENT if the
     * Ecore construct is to be serialized in element content.
     * By default, the value is UNSPECIFIED.
     */
    int getXMLRepresentation();

    /**
     * Set attribute to true to serialize a feature as an
     * XML attribute.
     */
    void setXMLRepresentation(int representation);

    /**
     * Set the target namespace for the Ecore construct. By
     * default, this is null. A package with the namespaceURI
     * must be registered with the EPackage.Registry.
     */
    void setTargetNamespace(String namespaceURI);

    /**
     * Gets the target namespace for the Ecore construct.
     */
    String getTargetNamespace();

    /**
     * Returns the name to use for the Ecore construct in an
     * XML file.
     */
    String getName();

    /**
     * Set the name to be used in an XML file.
     */
    void setName(String name);
  }
}
