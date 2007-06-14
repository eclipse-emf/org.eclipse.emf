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
 * $Id: XMLHelper.java,v 1.14 2007/06/14 18:32:40 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;

/**
 * Configuration class for XML. It holds the EPackage to
 * use when XML namespaces are not used in an XML file. It also
 * holds the XMLMap to be used when serializing an XML file.
 * The XML deserializer and serializer uses this class when
 * an XML file is loaded and saved.
 */
public interface XMLHelper
{
  /**
   * Sets various resource options on the helper
   */
  public void setOptions(Map<?, ?> options);
  
  /**
   * Sets the package to use when there is no XML namespace in an XML
   * file.
   */
  public void setNoNamespacePackage(EPackage pkg);

  /**
   * Gets the package to use when there is no XML namespace in an
   * XML file.
   */
  public EPackage getNoNamespacePackage();
  
  /**
   * Sets the any simple type class.
   * @see XMLResource#OPTION_ANY_SIMPLE_TYPE
   */
  public void setAnySimpleType(EClass type);

  /**
   * Sets the XMLMap to use when serializing an XML file.
   */
  public void setXMLMap(XMLResource.XMLMap map);

  /**
   * Gets the XMLMap to use when serializing an XML file.
   */
  public XMLResource.XMLMap getXMLMap();

  /**
   * Sets the ExtendedMetaData to use when serializing an XML file.
   */
  public void setExtendedMetaData(ExtendedMetaData extendedMetaData);

  /**
   * Gets the ExtendedMetaData to use when serializing an XML file.
   */
  public ExtendedMetaData getExtendedMetaData();

  /**
   * Returns the resource
   */
  XMLResource getResource();

  /**
   * Returns the XMI value of the EObject's feature
   */
  Object getValue(EObject eObject, EStructuralFeature eStructuralFeature);

  /**
   * Returns the XML name of the ENamedElement
   */
  String getName(ENamedElement eNamedElement);

  /**
   * Returns the nsName:name
   *  c.ePackage().nsName() :
   *  c.eName()
   *
   * If there is an XMLMap, and the target namespace is null
   * for this eClass, just the name is returned. If the map
   * has a target namespace specified, that target namespace is
   * used rather than the namespace from the EPackage.
   */
  String getQName(EClass eClass);

  /**
   * This method populates the given {@link NameInfo} with name information, resetting all the current
   * values in the <tt>nameInfo</tt>
   * @param nameInfo the name information to populate
   * @param eClass the EClass for which name has to be computed
   * @see XMLHelper#getQName(EClass)
   * @since 2.1.0
   */
  void populateNameInfo(NameInfo nameInfo, EClass eClass);

  /**
   * Returns the nsName:name
   *  c.ePackage().nsName() :
   *  c.eName()
   *
   * If there is an XMLMap, and the target namespace is null
   * for this eClass, just the name is returned. If the map
   * has a target namespace specified, that target namespace is
   * used rather than the namespace from the EPackage.
   */
  String getQName(EDataType eDataType);
  
  /**
   * This method populates the given {@link NameInfo} with name information, resetting all the current
   * values in the <tt>nameInfo</tt>
   * @param nameInfo the name information to fill in
   * @param eDataType the EDataType for which name has to be computed
   * @see XMLHelper#getQName(EDataType)
   * @since 2.1.0
   */
  void populateNameInfo(NameInfo nameInfo, EDataType eDataType);

  /**
   * By default, this method returns the name of the feature.
   * However, if there is an XMLMap, and the map specifies a
   * target namespace, the nsPrefix:name is returned.
   */
  String getQName(EStructuralFeature feature);

  /**
   * This method populates the given {@link NameInfo} with name information, resetting all the current
   * values in the <tt>nameInfo</tt> 
   * @param nameInfo the name information to fill in
   * @param feature the EStructuralFeature for which name has to be computed
   * @see #getQName(EStructuralFeature)
   * @since 2.1.0
   */
  void populateNameInfo(NameInfo nameInfo, EStructuralFeature feature);
  
  String getPrefix(String namespaceURI);

  /**
   * Return the prefix used for this package.
   */
  String getPrefix(EPackage ePackage);

  /**
   * Returns the namespaceURI in scope for the prefix.
   */
  String getNamespaceURI(String prefix);

  /**
   * Return all the prefixes used for this package.
   */
  List<String> getPrefixes(EPackage ePackage);

  /**
   * Returns the xmi:id or null to supress
   */
  String getID(EObject eObject);

  /**
   * Returns an IDREF to this object
   */
  String getIDREF(EObject eObject);

  /**
   * Returns an HREF to this object from this resource
   */
  String getHREF(EObject eObject);

  /**
   * Returns a relative URI if necessary and if possible.
   */
  URI deresolve(URI uri);

  /**
   * Returns the packages in getQName()
   */
  EPackage[] packages();

  /**
   * Create an object given an EFactory and a type name.
   * @deprecated since 2.2. Instead use #createObject(EFactory, EClassifier) and #getType(EFactory, String)
   */
  @Deprecated
  EObject createObject(EFactory eFactory, String name);
  
  /** Create an object given an EFactory and a type 
   * @since 2.2
   * @param eFactory
   * @param type
   * @return the created object.
   * @see #createObject(EFactory, String)
   */
  EObject createObject(EFactory eFactory, EClassifier type);
  
  /**
   * Given an EFactory and a type name, find and return the type
   * @since 2.2
   * @param eFactory
   * @param typeName
   * @see #createObject(EFactory, String)
   */
  public EClassifier getType(EFactory eFactory, String typeName);

  /**
   * Set the value of the feature for the object.
   */
  void setValue(EObject eObject, EStructuralFeature eStructuralFeature, Object value, int position);

  /**
   * Returns the structural feature for the XML element/attribute with the given namespaceURI and name.
   */
  EStructuralFeature getFeature(EClass eClass, String namespaceURI, String name);

  /**
   * Returns the structural feature for the XML element/attribute with the given namespaceURI and name.
   */
  EStructuralFeature getFeature(EClass eClass, String namespaceURI, String name, boolean isElement);

  /**
   * These are the kinds of features that are important
   * when loading XMI files.
   */
  int DATATYPE_SINGLE = 1;
  int DATATYPE_IS_MANY = 2;
  int IS_MANY_ADD = 3;
  int IS_MANY_MOVE = 4;
  int OTHER = 5;

  /**
   * Return the kind of feature.
   */
  int getFeatureKind(EStructuralFeature feature);

  /**
   * Return an XML encoding corresponding to the given Java encoding.
   * By default, the Java encoding is returned.
   */
  String getXMLEncoding(String javaEncoding);

  /**
   * Return a Java encoding corresponding to the given XML encoding.
   * By default, the XML encoding is returned.
   */
  String getJavaEncoding(String xmlEncoding);

  interface ManyReference
  {
    EObject getObject();
    EStructuralFeature getFeature();
    Object[] getValues();
    int[] getPositions();
    int getLineNumber();
    int getColumnNumber();
  }

  List<XMIException> setManyReference(ManyReference reference, String location);

  void setCheckForDuplicates(boolean checkForDuplicates);
  
  void setProcessDanglingHREF(String value);

  DanglingHREFException getDanglingHREFException();

  URI resolve(URI relative, URI base);
  
  /** 
   * Records the prefix to URI mapping while loading an XML document.
   */
  void addPrefix(String prefix, String uri);
  
  /**
   * Returns a map of prefixes to URI mapping for elements with any content
   */
  Map<String, String> getAnyContentPrefixToURIMapping();
  
  /**
   * Must be called during endDocument to record all the prefix to URI mappings that have
   * been seen in the document during loading
   */
  void recordPrefixToURIMapping();
  
  /** 
   * Returns the namespace prefix that's in effect while loading an XML document.
   */
  String getURI(String prefix);

  /**
   * Starts a new namespace context while loading an XML document.
   * <p>
   * A new context should be pushed at the beginning of each XML element.
   * The new context will automatically inherit the declarations of its parent context, 
   * but it will also keep track of which declarations were made within this context.
   * </p>
   */
  public void pushContext();

  /**
   * Reverts to the previous namespace context while loading XML document.
   * <p>
   * The context should be popped at the end of each XML element.  
   * After popping the context, all namespace prefix mappings that were previously in scope are restored.
   * </p>
   */
  public void popContext();

  /**
   * Reverts to the previous namespace context while loading XML document and removes from the map, prefixes that have gone out of scope.
   * <p>
   * The context should be popped at the end of each XML element.  
   * After popping the context, all namespace prefix mappings that were previously in scope are restored.
   * </p>
   */
  public void popContext(Map<String, EFactory> prefixesToFactories);
  
  /** 
   * Converts the given data value to a string while saving an XML document. 
   */
  public String convertToString(EFactory factory, EDataType dataType, Object data);

  EMap<String, String> getPrefixToNamespaceMap();
  void setPrefixToNamespaceMap(EMap<String, String> prefixToNamespaceMap);
  
  /**
   * Specify if qualified names must have a prefix or could use default namespace (if possible)
   * @param mustHavePrefix
   */
  void setMustHavePrefix(boolean mustHavePrefix);

}
