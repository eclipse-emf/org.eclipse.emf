/**
 * <copyright>
 *
 * Copyright (c) 2004-2006 IBM Corporation and others.
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
 * $Id: QName.java,v 1.6 2008/07/07 18:58:04 davidms Exp $
 */

package org.eclipse.emf.ecore.xml.type.internal;

import org.eclipse.emf.ecore.xml.type.InvalidDatatypeValueException;
import org.eclipse.emf.ecore.xml.type.internal.DataValue.XMLChar;

/**
 * An internal extension of Java's QName that allows the prefix to be updated.
 * If not specified, the prefix is set to empty string ("").
 * If not specified, the namespace uri is set to empty string ("");
 * <p>
 * NOTE: this class is for internal use only.
 */
public final class QName extends javax.xml.namespace.QName
{
  private static final long serialVersionUID = 1L;

  private String prefix;

  /**
   * Constructs a QName.
   * @param qname a <a href="http://www.w3.org/TR/REC-xml-names/#dt-qname">qualified name</a>
   * Throws Exception if value is not legal qualified name 
   */
  public QName(String qname)
  {
    super(null, qname.indexOf(':') != -1 ? qname.substring(qname.indexOf(':') + 1) : qname, qname.indexOf(':') != -1 ? qname.substring(0, qname.indexOf(':')) : "");
    setPrefix(super.getPrefix());

    // both prefix (if any) a localpart must be valid NCName
    if (prefix.length() > 0 && !XMLChar.isValidNCName(prefix))
        throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1: invalid QName: "+qname);

    if(!XMLChar.isValidNCName(getLocalPart()))
      throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1: invalid QName: "+qname);
  }

  /** 
   * Constructs a QName with the specified values. 
   */
  public QName(String namespaceURI, String localPart, String prefix)
  {
    super(namespaceURI, localPart, prefix = prefix == null ? "" : prefix);
    setPrefix(prefix);

    if (prefix.length() > 0 && !XMLChar.isValidNCName(prefix))
      throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1: invalid QName: "+prefix);

    if (!XMLChar.isValidNCName(localPart))
       throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1: invalid QName: "+localPart);
  }

  /**
   * @return Returns the prefix.
   */
  @Override
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
