/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: QName.java,v 1.1 2004/05/21 22:13:38 elena Exp $
 */

package org.eclipse.emf.ecore.xml.type.internal;

import org.eclipse.emf.ecore.xml.type.InvalidDatatypeValueException;
import org.eclipse.emf.ecore.xml.type.internal.DataValue.XMLChar;

/**
 * A structure that holds the components of an XML Namespaces qualified
 * name.
 * Two QNames are equal iff they both have same namespaceURI and same localPart.
 * Note: prefix is not used in QName.equals(Object).
 * If not specified, the prefix is set to empty string ("").
 * If not specified, the namespace uri is set to empty string ("");
 * <p>
 * NOTE: this class is for internal use only.
 */
public final class QName
{

  private String prefix;

  private String localPart;

  private String namespaceURI;
  
  /**
   * Constructs a QName.
   * @param qname a <a href="http://www.w3.org/TR/REC-xml-names/#dt-qname">qualified name</a>
   * Throws Exception if value is not legal qualified name 
   */
  public QName (String qname)
  {
    String rawname = qname;
    int index = rawname.indexOf(":");

    String prefix = "";
    String localName = rawname;
    if (index != -1)
    {
      prefix    = rawname.substring(0, index);
      localName = rawname.substring(index + 1);
    }
    // both prefix (if any) a localpart must be valid NCName
    if (prefix.length() > 0 && !XMLChar.isValidNCName(prefix))
        throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1: invalid QName"+qname);

    if(!XMLChar.isValidNCName(localName))
      throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1: invalid QName"+qname);
     
    setPrefix(prefix);
    setLocalPart(localName);
    setNamespaceURI(null);
  }

  /** Constructs a QName with the specified values. */
  public QName(String namespaceUri, String localPart, String prefix)
  {
    setNamespaceURI(namespaceUri);
    setPrefix(prefix);
    setLocalPart(localPart);
  }

  /** Returns true if the two objects are equal. */
  public boolean equals(Object object)
  {
    if (object instanceof QName)
    {
      QName qname = (QName)object;
      return namespaceURI.equals(qname.getNamespaceURI()) && localPart.equals(qname.getLocalPart());
    }
    return false;
  }
  
  public int hashCode() 
  {
    return namespaceURI.hashCode() + localPart.hashCode();
  }

  /** Returns a string representation of this object. */
  public String toString()
  {
    return (prefix.length() >0) ? prefix + ":" + localPart : localPart;
  }

  /**
   * @return Returns the localpart.
   */
  public String getLocalPart()
  {
    return localPart;
  }

  /**
   * @param localpart The localpart to set.
   */
  public void setLocalPart(String localpart)
  {   
    if (localpart == null || localpart.length() == 0)
    {
      throw new IllegalArgumentException("QName localPart must have value.");
    }
    this.localPart = localpart;
  }

  /**
   * @return Returns the namespaceURI.
   */
  public String getNamespaceURI()
  {   
    return namespaceURI;
  }

  /**
   * @param namespaceUri The namespaceURI to set.
   */
  public void setNamespaceURI(String namespaceUri)
  {
    if (namespaceUri == null)
    {
      this.namespaceURI = "";
    }
    else 
    {
      this.namespaceURI = namespaceUri;
    }
    
  }

  /**
   * @return Returns the prefix.
   */
  public String getPrefix()
  {
    return prefix;
  }

  /**
   * @param prefix The prefix to set.
   */
  public void setPrefix(String prefix)
  {
    if (prefix == null)
    {
      this.prefix = "";
    }
    else
    {
      this.prefix = prefix;
    }
  }
} 
