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
 * $Id: QName.java,v 1.1 2004/05/11 15:40:03 elena Exp $
 */

package org.eclipse.emf.ecore.xml.type;

/**
 * A structure that holds the components of an XML Namespaces qualified
 * name.
 * Two QNames are equal iff they both have same namespaceURI and same localPart.
 * Note: prefix is not used in QName.equals(Object).
 * If not specified, the prefix is set to empty string ("").
 * If not specified, the namespace uri is set to empty string ("");
 * <p>
 */
public final class QName
{

  private String prefix;

  private String localPart;

  private String namespaceURI;

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
