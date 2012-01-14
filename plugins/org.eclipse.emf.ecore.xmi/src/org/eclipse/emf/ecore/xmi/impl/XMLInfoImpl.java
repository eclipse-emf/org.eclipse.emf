/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xmi.impl;

import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * This class is used with the XMLMap class to describe how
 * to serialize objects and features. You can specify the name
 * to use instead of the model name, whether a feature will be
 * serialized as an XML attribute, XML element, or XML content,
 * and whether to use a namespace when serializing an object.
 * <p>
 * The XMLMap class maintains the association between XMLInfo
 * objects and the ecore constructs they describe.
 */
public class XMLInfoImpl implements XMLResource.XMLInfo
{
  protected int xmlRepresentation;
  protected String targetNamespace;
  protected String name;
  
  public XMLInfoImpl()
  {
    xmlRepresentation = UNSPECIFIED;
  }
  
  /**
   * Returns ELEMENT if the Ecore construct is to be serialized
   * as an XML element; ATTRIBUTE if the Ecore construct is
   * to be serialized as an XML attribute; and CONTENT if the
   * Ecore construct is to be serialized in element content.
   * By default, the value is UNSPECIFIED.
   */ 
  public int getXMLRepresentation()
  {
    return xmlRepresentation;
  }

  /**
   * Set attribute to true to serialize a feature as an
   * XML attribute.
   */   
  public void setXMLRepresentation(int representation)
  {
    xmlRepresentation = representation;
  }    
  
  public String getTargetNamespace()
  {
    return targetNamespace;
  }
  
  public void setTargetNamespace(String namespaceURI)
  {
    targetNamespace = namespaceURI;
  }    
  
  /**
   * Returns the name to use for the Ecore construct in an
   * XML file.
   */
  public String getName()
  {
    return name;
  }   
  
  /**
   * Set the name to be used in an XML file.
   */
  public void setName(String name)
  {
    this.name = name;
  }   
}
