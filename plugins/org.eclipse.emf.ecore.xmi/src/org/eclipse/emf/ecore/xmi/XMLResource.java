/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: XMLResource.java,v 1.47 2011/01/26 17:26:40 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.impl.ConfigurationCache;
import org.eclipse.emf.ecore.xmi.impl.ResourceEntityHandlerImpl;
import org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/**
 * This interface represents an XML resource.
 * You can use it to load and save XML documents.
 */
public interface XMLResource extends Resource
{
  /**
   * Specify a parser pool to be used for loading XML documents from InputStream.
   * You need to provide a XMLParserPool as the value of this option.
   * <p>
   * This option can dramatically improve performance for deserialization (loading) of XML resource.
   * </p>
   * @see org.eclipse.emf.ecore.xmi.XMLParserPool
   */
  String OPTION_USE_PARSER_POOL = "USE_PARSER_POOL";
  
  /**
   * <p>
   * Specify a map {@link Map} to be used during the subsequent loading of XML documents.
   * This is mapping between XML name, namespace and an EClass to the corresponding EStructuralFeature.
   * </p>
   * <p>
   * This option can significantly improve performance for deserialization (loading) of multiple XML resources
   * which are based on the same model (XML Schema or Ecore). 
   * </p>
   * @see org.eclipse.emf.ecore.EClass
   * @see org.eclipse.emf.ecore.EStructuralFeature
   */
  String OPTION_USE_XML_NAME_TO_FEATURE_MAP = "USE_XML_NAME_TO_FEATURE_MAP";
  
  /**
   * Specify a place holder {@link List} for caching information during the subsequent saving of XML documents.
   * This option can improve performance for serialization (saving) of multiple XML resources. This option
   * is similar to the OPTION_USE_XML_NAME_TO_FEATURE_MAP which is used for deserialization (loading).
   */
  String OPTION_USE_CACHED_LOOKUP_TABLE = "USE_CACHED_LOOKUP_TABLE";
  
  /**
   * Use deprecated methods - the default is <code>true</code>. 
   * To improve deserialization performance turn this option to <code>false</code>. 
   * The methods affected are:
   * <ul>
   * <li>XMLHelper#createObject(org.eclipse.emf.ecore.EFactory, EClassifier)</li>, 
   * <li>XMLHander#handleNamespaceAttribs()</li>, 
   * <li>XMLHandler#createObjectFromFactory(EFactory factory, String typeName),</li> 
   * <li>XMLLoadImpl#getEncoding()</li>
   * </ul> 
   * <p>
   * Note: if you use this option the SAX parser used for parsing documents must be namespace-aware parser, 
   * i.e. the namespaces should be turned on for the parser used. The default parser implementation is not 
   * namespace-aware. To turn on namespaces, either use OPTION_USE_PARSER_POOL or overwrite XMLLoadImpl#makeParser()
   */
  String OPTION_USE_DEPRECATED_METHODS = "USE_DEPRECATED_METHODS";
  
  /**
   * Generic option for enable caching (during save and load) to improve performance.
   * This option uses {@link ConfigurationCache} to cache the following things:
   * <ul>
   *   <li>Escape class used during serialization</li>
   *   <li>Printer class used during serialization</li>
   *   <li>DocumentRoot class per EPackage - useful during loading of XML document</li>
   * </ul>
   * The default value is false.
   */
  String OPTION_CONFIGURATION_CACHE = "CONFIGURATION_CACHE";
  
  /**
   * Use XMLTypeInfo to determine whether type information (xsi:type/xmi:type) is
   * to be serialized for references.
   */
  String OPTION_SAVE_TYPE_INFORMATION = "SAVE_TYPE_INFORMATION";
  
  /**
   * Specify parser features with their corresponding values, 
   * i.e., <code>true</code> or <code>false</code> using {@link Map}.  
   * This option is applicable during loading of XML resources from an InputStream
   */
  String OPTION_PARSER_FEATURES = "PARSER_FEATURES";
  
  /**
   * Specify parser properties with their corresponding values using a {@link Map}.
   * This option is applicable during loading of XML resources from an InputStream.
   */
  String OPTION_PARSER_PROPERTIES = "PARSER_PROPERTIES";
  
  /**
   * Determines whether comments and CDATA will be preserved in any mixed text processing.
   * This option is applicable for loading XML resources (from DOM node or an InputStream)
   */
  String OPTION_USE_LEXICAL_HANDLER = "USE_LEXICAL_HANDLER";
  
  /**
   * This is option should be used when loading a resource from a DOM Element node, to specify
   * that load method should take into account all the namespaces declarations in scope for this node 
   * (by visiting parents of the node being loaded). 
   * Otherwise, user have to make sure that Element node being loaded has namespace well-formed content.
   * <p>
   * Note that loading will only succeed if the subset of DOM being loaded could be treated as a valid resource according
   * to the model. </p>
   */
  String OPTION_DOM_USE_NAMESPACES_IN_SCOPE = "DOM_USE_NAMESPACES_IN_SCOPE";

  /**
   * This option allows you to tailor the XML serialization of objects. 
   * You should provide an ExtendedMetaData as the value of this option.
   * @see org.eclipse.emf.ecore.util.BasicExtendedMetaData
   */
  String OPTION_EXTENDED_META_DATA = "EXTENDED_META_DATA";
  
  /** 
   * This option allows to specify an EClass for the implementation for anyType.
   * As an example, see <code>org.eclipse.emf.ecore.sdo.SDOPackage</code> <code>getEDataObjectAnyType()</code>.
   * @see #OPTION_ANY_SIMPLE_TYPE
   * 
   */
  String OPTION_ANY_TYPE = "ANY_TYPE";
  
  /** 
   * This option allows to specify an EClass for the implementation for anySimpleType.
   * As an example, see <code>org.eclipse.emf.ecore.sdo.SDOPackage</code> <code>getEDataObjectSimpleAnyType()</code> method.
   * @see #OPTION_ANY_TYPE
   */
  String OPTION_ANY_SIMPLE_TYPE ="ANY_SIMPLE_TYPE";

  /**
   * This save option allows you to tailor the XML serialization of
   * objects. You should provide an XMLMap as the value of this option.
   * <p>
   * It is strongly suggested to use instead of this option the ExtendedMetaData. 
   * </p>
   * @see #OPTION_EXTENDED_META_DATA
   */
  String OPTION_XML_MAP = "XML_MAP";

  /**
   * All EObject references will be uniformly encoded as one or more QName URI pairs,
   * where the QName is optional depending on whether the referenced object's type is not identicle to that of the feature.
   * This option on save and load means the same: if it is set to true, "href" attributes will be treated as actual data - 
   * i.e. as if your model had a feature named "href".
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
   * Keep default content ( e.g. default attributes). This applies to saving and converting contents to DOM.
   * By default the default content is discarded.
   * To save the default content, set this option to <code>Boolean.TRUE</code>.
   */
  String OPTION_KEEP_DEFAULT_CONTENT = "KEEP_DEFAULT_CONTENT";
  
  /** 
   * Save a <code>doctype</code> declaration using systemId and publicId values specified on the resource
   * @see #getSystemId() 
   * @see #getPublicId() 
   * @see #setDoctypeInfo(String,String)
   *  
   */
  String OPTION_SAVE_DOCTYPE = "SAVE_DOCTYPE";
  
  /**
   * A {@link ResourceEntityHandler} value that will be used during load to record entity definitions 
   * and during save to encode cross document reference URIs as entities;
   * the default value is null, in which case entities will not be defined at all.
   * DOM does not support creating entities in the document type declaration, 
   * so this option does nothing when converting directly to DOM.
   * @see ResourceEntityHandlerImpl
   */
  String OPTION_RESOURCE_ENTITY_HANDLER = "RESOURCE_ENTITY_HANDLER";

  /**
   * An interface for a resource entity handler.
   * It is used during load to record entities 
   * and is used during save to assign entity names to values representing cross resource URI references.
   * @see ResourceEntityHandlerImpl
   */
  interface ResourceEntityHandler
  {
    /**
     * Resets the state of the entity handler when a resource is first loaded or is reloaded.
     */
    void reset();
    
    /**
     * Records the entity name to entity value mapping.
     * @param entityName the name of the entity.
     * @param entityValue the associated value of the entity.
     */
    void handleEntity(String entityName, String entityValue);
    
    /**
     * Returns the name associated with the entity value; 
     * a new name will be generated if there is not yet a name associated with the entity value.
     * @param entityValue the entity value for which a named entity is needed.
     * @return the name associated with the entity value.
     */
    String getEntityName(String entityValue);
    
    /**
     * Returns the map of entity names to entity values to be recorded in the document during save.
     * @return the map of entity names to entity values to be recorded in the document during save.
     */
    Map<String, String> getNameToValueMap();
  }

  /**
   * Skip processing for values that contain characters special to XML
   * Faster for large computer-generated files
   */
  String OPTION_SKIP_ESCAPE  = "SKIP_ESCAPE";
  
  /**
   * Skip escaping special characters, such as '&' char, in URIs during XML serialization.
   * The default is <code>Boolean.TRUE</code>. To enforce escaping special characters, set this 
   * option to <code>Boolean.FALSE</code>.
   */
  String OPTION_SKIP_ESCAPE_URI = "SKIP_ESCAPE_URI";

  /**
   * This can be one of "THROW", "DISCARD", "RECORD", where "THROW" is the default.
   */
  String OPTION_PROCESS_DANGLING_HREF          = "PROCESS_DANGLING_HREF";
  String OPTION_PROCESS_DANGLING_HREF_THROW    = "THROW";
  String OPTION_PROCESS_DANGLING_HREF_DISCARD  = "DISCARD";
  String OPTION_PROCESS_DANGLING_HREF_RECORD   = "RECORD";

  /**
   * This options allows you to record unknown features during deserialization/loading.
   * The default is <code>Boolean.FALSE</code> unless set to <code>Boolean.TRUE</code> explicitly. 
   * The unknown features and their values can be accessed via getEObjectToExtensionMap().
   * @see #getEObjectToExtensionMap()  
   */
  String OPTION_RECORD_UNKNOWN_FEATURE = "RECORD_UNKNOWN_FEATURE";
  
  /**
   * Turn this load option on to indicate that a feature should be populated 
   * from either an attribute or an element even if its feature kind is specified (e.g. element).
   * The default is <code>Boolean.FALSE</code> unless set to <code>Boolean.TRUE</code> explicitly.
   */
  String OPTION_LAX_FEATURE_PROCESSING = "LAX_FEATURE_PROCESSING";

  /**
   * This load option set to <code>Boolean.TRUE</code> will cause all wildcards to handled
   * as if they explicitly specified {@link ExtendedMetaData#LAX_PROCESSING lax processing}.
   * The default is <code>Boolean.FALSE</code>.
   */
  String OPTION_LAX_WILDCARD_PROCESSING = "LAX_WILDCARD_PROCESSING";

  /**
   * Allows the user to specify XML options 
   * {@link XMLOptions} and default implementation you can use {@link org.eclipse.emf.ecore.xmi.impl.XMLOptionsImpl}
   */
  String OPTION_XML_OPTIONS = "XML_OPTIONS";

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
   * Produce an xsi:schemaLocation/xsi:noNamespaceSchemaLocation in the saved result 
   * to encode the name of the Java interface that declares the eINSTANCE of the EPackage implementation
   * for those cases where OPTION_SCHEMA_LOCATION would not produce a physical location URI.
   */
  String OPTION_SCHEMA_LOCATION_IMPLEMENTATION = "SCHEMA_LOCATION_IMPLEMENTATION";

  /**
   * Specify the XML encoding to be used during save.
   */
  String OPTION_ENCODING  = "ENCODING";

  /**
   * Specify the XML version to be used during save.
   */
  String OPTION_XML_VERSION  = "XML_VERSION";

  /**
   * Enable preserving namespace declarations on elements of type <code>AnyType</code>
   */
  String OPTION_RECORD_ANY_TYPE_NAMESPACE_DECLARATIONS  = "RECORD_ANY_TYPE_NAMESPACE_DECLARATIONS";

  /**
   * Flush the output stream whenever the number of characters/bytes pending exceeds the specified Integer value. 
   * This helps to reduce memory requirements for serializing a large file, but it's slower.
   * The default value is Integer.MAX_VALUE.  
   * I.e., the stream is never flushed.
   */
  String OPTION_FLUSH_THRESHOLD = "FLUSH_THRESHOLD";

  /**
   * Write the intermediate results of serialization to the file system rather to an in-memory buffer.
   * This helps to reduce memory requirements for serializing a large file, but it's slower.
   * The default is <code>Boolean.FALSE</code> unless set to <code>Boolean.TRUE</code> explicitly.
   */
  String OPTION_USE_FILE_BUFFER = "USE_FILE_BUFFER";

  /**
   * Defer resolution of same document references until the end of the document being loaded is reached.
   * The default value is false.
   */
  String OPTION_DEFER_IDREF_RESOLUTION = "DEFER_IDREF_RESOLUTION";
  
  /**
   * Write only the subtree starting at the specified list of EObjects, 
   * which must be objects contained by the resource.
   */
  String OPTION_ROOT_OBJECTS = "ROOT_OBJECTS";

  /**
   * A {@link ResourceHandler} value that can be registered to receive call backs 
   * for loading from an input stream or saving to an output stream.
   * @see org.eclipse.emf.ecore.xmi.XMLResource.ResourceHandler
   * @see org.eclipse.emf.ecore.xmi.impl.BasicResourceHandler
   */
  String OPTION_RESOURCE_HANDLER = "RESOURCE_HANDLER";

  /**
   * An interface for a resource handler that can be registered to receive call backs 
   * for loading from an input stream or for saving to an output stream.
   */
  interface ResourceHandler
  {
    /**
     * Called before loading begins.
     * @param resource the resource being loaded.
     * @param inputStream the stream being read.
     * @param options the load options.
     */
    void preLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options);

    /**
     * Called after loading is done.
     * @param resource the resource being loaded.
     * @param inputStream the stream being read.
     * @param options the load options.
     */
    void postLoad(XMLResource resource, InputStream inputStream, Map<?, ?> options);

    /**
     * Called before saving begins.
     * @param resource the resource being saved.
     * @param outputStream the stream being written.
     * @param options the save options.
     */
    void preSave(XMLResource resource, OutputStream outputStream, Map<?, ?> options);

    /**
     * Called before saving begins.
     * @param resource the resource being saved.
     * @param outputStream the stream being written.
     * @param options the save options.
     */
    void postSave(XMLResource resource, OutputStream outputStream, Map<?, ?> options);
  }
  
  /**
   * Defer adding the root object to the resource until the end of the load when the tree is complete.
   * The default value is Boolean.FALSE.
   * It's often a good idea to set OPTION_DEFER_IDREF_RESOLUTION true Boolean.TRUE if this option is set to Boolean.TRUE.
   */
  String OPTION_DEFER_ATTACHMENT = "DEFER_ATTACHMENT";

  /**
   * A {@link URIHandler} value that will be used to control how URIs are {@link URI#resolve(URI) resolved} during load 
   * and {@link URI#deresolve(URI) deresolved} during save.
   * @see URI
   * @see URIHandler
   * @see XMLHelper#resolve(URI, URI)
   * @see XMLHelper#deresolve(URI)
   * @see URIHandlerImpl
   */
  String OPTION_URI_HANDLER = "URI_HANDLER";

  /**
   * An interface for a URI handler that is used to {@link URI#resolve(URI) resolve} and {@link URI#deresolve(URI) deresolve} URIs.
   * Before being used by either load or save, {@link #setBaseURI(URI)} will be called with the URI of the resource being loaded or saved.
   * During load, {@link #resolve(URI)} is called to resolve each URI against the URI of the containing resource; 
   * this provides an opportunity to turn relative URIs into absolute URIs.
   * During save, {@link #deresolve(URI)} is called to resolve each URI against the URI of the containing resource; 
   * this provide an opportunity to turn absolute URIs into relative URIs
   * It should be the case that <code>uriHandler.resolve(uriHandler.deresolve(uri)).equals(uri)</code>, 
   * i.e., resolving the deresolved URI should yield the original URI.
   * @see URIHandlerImpl
   */
  interface URIHandler
  {
    /**
     * Sets base URI used by the handler.
     * It will be called before load or save begins.
     * @param uri the new base URI.
     */
    void setBaseURI(URI uri);

    /**
     * Returns the URI {@link URI#resolve(URI) resolved} against the base URI.
     * @param uri the URI to resolve.
     * @return the URI resolved against the base URI.
     * @see URI#resolve(URI)
     */
    URI resolve(URI uri);

    /**
     * Returns the URI {@link URI#deresolve(URI) deresolved} against the base URI.
     * @param uri the URI to resolve.
     * @return the URI resolved against the base URI.
     * @see URI#deresolve(URI)
     */
    URI deresolve(URI uri);
  }

  /**
   * This option is used to specify an {@link ElementHandler} for deducing the feature used to serialize a specific type of value.
   * @see ElementHandler
   * @since 2.4
   */
  String OPTION_ELEMENT_HANDLER = "ELEMENT_HANDLER";

  /**
   * An interface for an element handle that is used to deduce an appropriate feature when serializing a value of a specific type.
   * The {@link #getRoot(ExtendedMetaData, EClassifier) getRoot} method is used to determine an appropriate feature to serialize a value of the given type.
   * The {@link #getSubstitutionGroup(ExtendedMetaData, EStructuralFeature, EClassifier) getSubstitutionGroup} method 
   * is used to determine a feature 
   * related by {@link ExtendedMetaData#getAffiliation(EStructuralFeature) substitution group affiliation} to the given feature
   * for serializing a value of the given type.
   * @since 2.4
   */
  interface ElementHandler
  {
    /**
     * Returns an appropriate feature for serializing a value of the give type.
     * @param extendedMetaData the extended meta data in which to look up type information.
     * @param eClassifier the type of value to serialize.
     * @return an appropriate feature for serializing a value of the give type or <code>null</code>.
     */
    EStructuralFeature getRoot(ExtendedMetaData extendedMetaData, EClassifier eClassifier);

    /**
     * Returns an feature, related by {@link ExtendedMetaData#getAffiliation(EStructuralFeature) substitution group affiliation} to the given feature, 
     * for serializing a value of the give type.
     * @param extendedMetaData the extended meta data in which to look up type information.
     * @param eStructuralFeature the feature that will be used if this method returns null.
     * @param eClassifier the type of value to serialize.
     * @return an appropriate feature for serializing a value of the give type or <code>null</code>.
     */
    EStructuralFeature getSubstitutionGroup(ExtendedMetaData extendedMetaData, EStructuralFeature eStructuralFeature, EClassifier eClassifier);
  }

  /**
   * When {@link #OPTION_EXTENDED_META_DATA} is used, 
   * this load option set to Boolean.TRUE will direct the deserializer to suppress creating a document root instance.
   * This option is typically used in combination with {@link #OPTION_ELEMENT_HANDLER}.
   * @since 2.4
   */
  String OPTION_SUPPRESS_DOCUMENT_ROOT = "SUPPRESS_DOCUMENT_ROOT";
  
  /**
   * Serialized element content that needs escaping and doesn't contain <code>"]]>"</code>, will be escaped using CDATA.
   * The default value is false.
   * @since {@link #OPTION_SKIP_ESCAPE}
   * @since 2.4
   */
  String OPTION_ESCAPE_USING_CDATA = "ESCAPE_USING_CDATA";


  /**
   * A load or save option that when set to Boolean.TRUE, directs the resource to produce or consume a {@link BinaryResourceImpl binary serialization}.
   * @since 2.7
   */
  String OPTION_BINARY = "BINARY";

  String HREF = "href";
  String NIL = "nil";
  String TYPE = "type";
  String SCHEMA_LOCATION = "schemaLocation";
  String NO_NAMESPACE_SCHEMA_LOCATION = "noNamespaceSchemaLocation";

  String XML_NS = ExtendedMetaData.XMLNS_PREFIX;
  String XSI_NS = ExtendedMetaData.XSI_PREFIX;
  String XSI_URI = ExtendedMetaData.XSI_URI;
  String XML_SCHEMA_URI = ExtendedMetaData.XML_SCHEMA_URI;

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
  Map<Object, Object> getDefaultSaveOptions();

  /**
   * Returns the map of options that, in addition to the overriding options specified during load,
   * are used to to control load behavior.
   */
  Map<Object, Object> getDefaultLoadOptions();

  /**
   * Returns <a href='http://www.w3.org/TR/2004/REC-xml-20040204/#NT-PubidLiteral'>public identifier</a> specified on the doctype.
   */
  String getPublicId();

  /**
   * Returns <a href='http://www.w3.org/TR/2004/REC-xml-20040204/#NT-SystemLiteral'>system identifier</a> specified on the doctype. 
   */
  String getSystemId();

  /**
   * Sets the values of <a href='http://www.w3.org/TR/2004/REC-xml-20040204/#NT-SystemLiteral'>system</a> and 
   * <a href='http://www.w3.org/TR/2004/REC-xml-20040204/#NT-PubidLiteral'>public</a> identifiers on this resource.
   * @param publicId
   * @param systemId
   */
  void setDoctypeInfo(String publicId, String systemId);

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
   * Returns the XML version for this resource.
   * The default is 1.0.
   * @return the XML version.
   */
  String getXMLVersion();
  
  /**
   * Set the XML version for this resource
   */
  void setXMLVersion(String version);

  /**
   * Returns the Map with IDs as keys and EObjects as values.
   * @deprecated since 2.1.0 This map should not be manipulated directly.  
   * Use {@link #setID(EObject, String)} and {@link #getID(EObject)} or 
   * {@link Resource#getEObject(String)} instead.  This method may be removed from
   * this interface.
   */
  @Deprecated
  Map<String, EObject> getIDToEObjectMap();

  /**
   * Returns the Map of EObjects as keys and IDs as values.
   * @deprecated since 2.1.0 This map should not be manipulated directly.  
   * Use {@link #setID(EObject, String)} and {@link #getID(EObject)} or 
   * {@link Resource#getEObject(String)} instead.  This method may be removed from
   * this interface.
   */
  @Deprecated
  Map<EObject, String> getEObjectToIDMap();

  /**
   * Returns the ID that was assigned with {@link #setID(EObject, String)}; if there is
   * no ID, it returns null.
   */
  String getID(EObject eObject);
  
  /**
   * Sets the ID for the given object. If you are storing the ID
   * with the object itself, you should override this method.  You can remove the
   * ID of a given eObject by using <code>null</code> as the id value.
   */
  void setID(EObject eObject, String id);

  /**
   * Returns the map with {@link EObject} as keys and corresponding {@link org.eclipse.emf.ecore.xml.type.AnyType}s as the values.
   * It's used to record unrecognized elements and attributes.
   */
  Map<EObject, AnyType> getEObjectToExtensionMap();
   
  /**
   * Create a DOM tree representing contents of this resource.
   * @param document an empty {@link org.w3c.dom.Document} to use or null. If no document is specified, the 
   * new {@link org.w3c.dom.Document} will be created using JAXP API.
   * @param options the "save" options
   * @param handler the {@link org.eclipse.emf.ecore.xmi.DOMHandler} to record mappings or null. 
   * If no DOMHandler is passed, the default DOMHandler will be created.
   * @return the {@link org.w3c.dom.Document}. In the case the document is specified as a parameter, 
   * the returned document is the same as the one specified, otherwise the newly created document is returned.
   * @since 2.1.0
   */
  Document save(Document document, Map<?, ?> options, DOMHandler handler);
  
  /**
   * Saves the resource to the writer using the specified options.
   * @param writer the writer
   * @param options the save options.
   */
  void save(Writer writer, Map<?, ?> options) throws IOException;
    
  /**
   * Returns the {@link DOMHelper} 
   * @since 2.1.0
   */
  DOMHelper getDOMHelper();
  
  /**
   * Loads the resource from the DOM node, either an Element or Document, using the specified options. 
   * <p>
   * This method assumes that no namespace fixup needs to be done.
   * To process comments and CDATA section nodes, please set XMLResource.OPTION_USE_LEXICAL_HANDLER option to Boolean.TRUE.
   * </p>
   * @param node DOM Element or Document node.
   * @param options the load options.
   * @see #save(Document, Map, DOMHandler)
   * @since 2.1.0
   */
  void load(Node node, Map<?, ?> options) throws IOException;
  
  void load(InputSource inputSource, Map<?, ?> options) throws IOException;

  /**
   * This interface represents a mapping from Ecore constructs to the
   * XML representation of those constructs. It is used by the
   * XML serializer and deserializer to load and save XML files.
   */
  interface XMLMap
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
    List<EStructuralFeature> getFeatures(EClass eClass);
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
  interface XMLInfo
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
